package com.lilithsthrone.game.occupantManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.OccupationTag;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.eventLog.SlaveryEventLogEntry;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * A class to handle all occupant-related turn mechanics. Deals with moving slaves to/from jobs and generating events for them. Also sends friendly occupants to/from jobs.
 * 
 * @since 0.1.87
 * @version 0.2.10
 * @author Innoxia
 */
public class OccupancyUtil implements XMLSaving {
	
	private Map<SlaveJob, List<NPC>> slavesAtJob;
	private List<NPC> slavesResting;
	private int generatedIncome;
	private int generatedUpkeep;
	
	private List<MilkingRoom> milkingRooms;
	
	// Slave income:
	private Map<NPC, Integer> dailyIncome;
	
	public OccupancyUtil() {
		slavesAtJob = new HashMap<>();
		for(SlaveJob job : SlaveJob.values()) {
			slavesAtJob.put(job, new ArrayList<>());
		}
		
		slavesResting = new ArrayList<>();
		generatedIncome = 0;
		generatedUpkeep = 0;
		
		milkingRooms = new ArrayList<>();
		
		dailyIncome = new HashMap<>();
	}

	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("slavery");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "generatedIncome", String.valueOf(Main.game.getOccupancyUtil().getGeneratedIncome()));
		CharacterUtils.addAttribute(doc, element, "generatedUpkeep", String.valueOf(Main.game.getOccupancyUtil().getGeneratedUpkeep()));
		
		for(MilkingRoom room : this.getMilkingRooms()) {
			room.saveAsXML(element, doc);
		}
		
		return element;
	}
	
	public static OccupancyUtil loadFromXML(Element parentElement, Document doc) {
		try {
			OccupancyUtil slaveryUtil = new OccupancyUtil();
			
			slaveryUtil.setGeneratedIncome(Integer.valueOf(parentElement.getAttribute("generatedIncome")));
			slaveryUtil.setGeneratedUpkeep(Integer.valueOf(parentElement.getAttribute("generatedUpkeep")));
			
			NodeList milkingRoomElements = parentElement.getElementsByTagName("milkingRoom");
			for(int i=0; i<milkingRoomElements.getLength(); i++){
				Element e = ((Element)milkingRoomElements.item(i));
				
				MilkingRoom room = MilkingRoom.loadFromXML(e, doc);
				
				if(slaveryUtil.getMilkingRoom(room.getWorldType(), room.getLocation())==null) {
					slaveryUtil.addMilkingRoom(room);
				}
			}
			
			return slaveryUtil;
			
		} catch(Exception ex) {
			System.err.println("Warning: SlaveryUtil failed to import!");
			return null;
		}
	}
	
	
	private void clearSlavesJobTracking() {
		for(SlaveJob job : SlaveJob.values()) {
			slavesAtJob.get(job).clear();
		}
		slavesResting.clear();
	}
	
	/**
	 * Resets daily dialogue flags and searches for a job (if the occupant is still marked as a lowlife).
	 */
	public void dailyOccupantUpdate(NPC occupant) {
		occupant.resetOccupantFlags();
		
		if(!Main.game.getCharactersPresent().contains(occupant)) { // Don't give them a new job if the player is present...
			if(occupant.getHistory().getOccupationTags().contains(OccupationTag.LOWLIFE)) {
				System.out.println(occupant.getName());
				if(Math.random()<0.1) {
					List<Occupation> occupations = new ArrayList<>();
					for(Occupation occ : Occupation.values()) {
						if(!occ.isAvailableToPlayer() && !occ.getOccupationTags().contains(OccupationTag.HAS_PREREQUISITES) && occ.isAvailable(occupant) && occ!=Occupation.NPC_UNEMPLOYED) {
							occupations.add(occ);
						}
					}
					occupant.setHistory(Util.randomItemFrom(occupations));
					occupant.setFlag(NPCFlagValue.occupantHasNewJob, true);
					System.out.println(occupant.getHistory().getName());
				}
			}
		}
	}
	
	public void performHourlyUpdate(int day, int hour) {
		
		// Non-slave occupants:
		
		for(String id : Main.game.getPlayer().getFriendlyOccupants()) {
			NPC occupant = (NPC) Main.game.getNPCById(id);

			if(!Main.game.getCharactersPresent().contains(occupant)) { // If the player isn't interacting with them, then move them:
				if(!occupant.getHistory().getOccupationTags().contains(OccupationTag.LOWLIFE)) {
					if(occupant.getHistory().isAtWork(hour)) {
						occupant.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
						
					} else {
						occupant.setLocation(occupant.getHomeWorldLocation(), occupant.getHomeLocation(), false);
					}
				}
			}
		}
		
		
		// Slaves:
		
		clearSlavesJobTracking();
		
		// First need to set correct jobs:
		for(String id : Main.game.getPlayer().getSlavesOwned()) {
			NPC slave = (NPC) Main.game.getNPCById(id);
			if(Main.game.getPlayer().hasCompanion(slave)) {
				continue;
			}
			
			if(!Main.game.getCharactersPresent().contains(slave)) { // If the player isn't interacting with them, then move them:
				if(slave.getWorkHours()[hour]) {
					slave.getSlaveJob().sendToWorkLocation(slave);
					slavesAtJob.get(slave.getSlaveJob()).add(slave);
					
				} else {
					if(slave.getSlaveJob()==SlaveJob.PROSTITUTE) {
						// Remove client before leaving:
						List<NPC> charactersPresent = Main.game.getCharactersPresent(slave.getWorldLocation(), slave.getLocation());
						for(NPC npc : charactersPresent) {
							if(npc instanceof GenericSexualPartner) {
								Main.game.banishNPC(npc);
							}
						}
					}
					slave.setLocation(slave.getHomeWorldLocation(), slave.getHomeLocation(), false);
					slavesResting.add(slave);
				}
			}
		}
		
		// Now can apply changes and generate events based on who else is present in the job:
		for(String id : Main.game.getPlayer().getSlavesOwned()) {
			NPC slave = (NPC) Main.game.getNPCById(id);
			
			if(slave==null || Main.game.getPlayer().hasCompanion(slave)) {
				continue;
			}
			
			slave.incrementAffection(slave.getOwner(), slave.getHourlyAffectionChange(hour));
			slave.incrementObedience(slave.getHourlyObedienceChange(hour), false);
			
			// If at work:
			if(slave.getWorkHours()[hour]) {
				// Get paid for hour's work:
				if(slave.getSlaveJob()!=SlaveJob.MILKING) {
					int income = slave.getSlaveJob().getFinalHourlyIncomeAfterModifiers(slave);
					generatedIncome += income;
					incrementSlaveDailyIncome(slave, income);
				}
				// Overworked effect:
				if(slave.hasStatusEffect(StatusEffect.OVERWORKED)) {
					slave.incrementAffection(slave.getOwner(), -0.1f);
				}
				
			} else {
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_MASTURBATE)) {
					slave.setLastTimeHadSex((day*24*60l) + hour*60l, true);
				}
			}
			
			// ***** EVENTS: ***** //
			
			// Washing body:
			if(slave.hasSlavePermissionSetting(SlavePermissionSetting.CLEANLINESS_WASH_BODY)
					&& !slave.getWorkHours()[hour]
					&& !slave.getDirtySlots().isEmpty()) {
				SlaveryEventLogEntry entry = new SlaveryEventLogEntry(hour,
						slave,
						SlaveEvent.WASHED_BODY,
						null,
						true);
				
				if(slave.hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
					entry.addTag(SlaveEventTag.WASHED_BODY_ANAL_CREAMPIE, slave, true);
				}
				if(slave.hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
					entry.addTag(SlaveEventTag.WASHED_BODY_VAGINAL_CREAMPIE, slave, true);
				}
				if(slave.hasStatusEffect(StatusEffect.CREAMPIE_NIPPLES)) {
					entry.addTag(SlaveEventTag.WASHED_BODY_NIPPLE_CREAMPIE, slave, true);
				}
				
				Main.game.addSlaveryEvent(day, slave, entry);
			}
			
			// Washing clothes:
			if((slave.hasStatusEffect(StatusEffect.CLOTHING_CUM) || !slave.getDirtySlots().isEmpty())
					&& !slave.getWorkHours()[hour]
					&& slave.hasSlavePermissionSetting(SlavePermissionSetting.CLEANLINESS_WASH_CLOTHES)) {
				Main.game.addSlaveryEvent(day, slave, new SlaveryEventLogEntry(hour,
						slave,
						SlaveEvent.WASHED_CLOTHES,
						Util.newArrayListOfValues(SlaveEventTag.WASHED_CLOTHES),
						true));
			}
			
			// Events:
			boolean eventAdded = false;
			SlaveryEventLogEntry entry = null;
			// Interaction events:
			if(slavesAtJob.get(slave.getSlaveJob()).size()>1 || slave.getSlaveJob()==SlaveJob.IDLE) {
				if(Math.random()<0.25f) {
					entry = generateNPCInteractionEvent(day, hour, slave, slavesAtJob.get(slave.getSlaveJob()));
					if(entry!=null) {
						Main.game.addSlaveryEvent(day, slave, entry);
						eventAdded = true;
					}
				}
			}
			// Standard events:
			if(!eventAdded) {
				if(slave.getSlaveJob()==SlaveJob.PROSTITUTE) {
					// Remove client:
					List<NPC> charactersPresent = Main.game.getCharactersPresent(slave.getWorldLocation(), slave.getLocation());
					for(NPC npc : charactersPresent) {
						if(npc instanceof GenericSexualPartner) {
//							System.out.println("partner removed for "+slave.getName());
							Main.game.banishNPC(npc);
						}
					}
				}
				
				if(Math.random()<0.05f || slave.getSlaveJob()==SlaveJob.MILKING || (Math.random()<0.5f && (slave.getSlaveJob()==SlaveJob.PUBLIC_STOCKS || slave.getSlaveJob()==SlaveJob.PROSTITUTE))) {
					List<SlaveryEventLogEntry> entries = generateEvents(hour, slave);
					for(SlaveryEventLogEntry e : entries) {
						Main.game.addSlaveryEvent(day, slave, e);
						eventAdded = true;
					}
				}
			}
			
			if(hour%24==0) { // At the start of a new day:
				
				SlaveryEventLogEntry dailyEntry = new SlaveryEventLogEntry(hour,
						slave,
						SlaveEvent.DAILY_UPDATE,
						null,
						true);
				
				// Payments:
				if(dailyIncome.containsKey(slave)) {
					dailyEntry.addExtraEffect("[style.boldGood(Earned)] "+UtilText.formatAsMoney(dailyIncome.get(slave)));
				}
				
				// Muscle:
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_FORBIDDEN) && slave.getMuscleValue()>0) {
					dailyEntry.addTag(SlaveEventTag.DAILY_MUSCLE_LOSS_LARGE, slave, true);
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_REST) && slave.getMuscleValue()>0) {
					dailyEntry.addTag(SlaveEventTag.DAILY_MUSCLE_LOSS, slave, true);
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_TRAINING) && slave.getMuscleValue()<100) {
					dailyEntry.addTag(SlaveEventTag.DAILY_MUSCLE_GAIN, slave, true);
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_BODY_BUILDING) && slave.getMuscleValue()<100) {
					dailyEntry.addTag(SlaveEventTag.DAILY_MUSCLE_GAIN_LARGE, slave, true);
				}
				
				// Body size:
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_DIET_EXTREME) && slave.getBodySizeValue()>0) {
					dailyEntry.addTag(SlaveEventTag.DAILY_BODY_SIZE_LOSS_LARGE, slave, true);
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_DIET) && slave.getMuscleValue()>0) {
					dailyEntry.addTag(SlaveEventTag.DAILY_BODY_SIZE_LOSS, slave, true);
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_PLUS) && slave.getMuscleValue()<100) {
					dailyEntry.addTag(SlaveEventTag.DAILY_BODY_SIZE_GAIN, slave, true);
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_LAVISH) && slave.getMuscleValue()<100) {
					dailyEntry.addTag(SlaveEventTag.DAILY_BODY_SIZE_GAIN_LARGE, slave, true);
				}
				
				if(dailyEntry.getTags()!=null || dailyEntry.getExtraEffects()!=null) {
					Main.game.addSlaveryEvent(day, slave, dailyEntry);
				}
				
				slave.resetSlaveFlags();
			}
		}
		
		if(hour%24==0) { // Reset daily income tracking:
			dailyIncome.clear();
			// Rooms:
			for(Cell c : OccupantManagementDialogue.getImportantCells()) {
				generatedUpkeep += c.getPlace().getUpkeep();
			}
			for(String id : Main.game.getPlayer().getFriendlyOccupants()) {
				NPC occupant = (NPC) Main.game.getNPCById(id);
				if(!occupant.getHistory().getOccupationTags().contains(OccupationTag.LOWLIFE)) {
					generatedIncome += PlaceUpgrade.LILAYA_GUEST_ROOM.getUpkeep();
				}
			}
		}
		
	}

	/**
	 * @param minute Time at which this event is happening.
	 * @param slave The slave to calculate an event for.
	 */
	private List<SlaveryEventLogEntry> generateEvents(int hour, NPC slave) {
		
		SlaveJob job = slave.getSlaveJob();

		StringBuilder effectDescriptions = new StringBuilder();
		List<String> effects = new ArrayList<>();
		List<SlaveJobSetting> settingsEnabled = new ArrayList<>();
		
		List<SlaveryEventLogEntry> events = new ArrayList<>();
		
		if(slave.getWorkHours()[hour] && job != SlaveJob.IDLE) { // Slave is working:
			switch (job) { //TODO
				case CLEANING:
					events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_CLEANING, true));
					return events;
					
				case KITCHEN:
					events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_COOKING, true));
					return events;
					
				case LAB_ASSISTANT:
					events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_LAB_ASSISTANT, true));
					return events;
					
				case LIBRARY:
					events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_LIBRARIAN, true));
					return events;
					
				case MILKING:
					int income = 0;

					Cell c = MilkingRoom.getMilkingCell(slave, false);
					MilkingRoom room = this.getMilkingRoom(c.getType(), c.getLocation());
					
					if(slave.getBreastRawStoredMilkValue()>0 && !slave.getSlaveJobSettings().contains(SlaveJobSetting.MILKING_MILK_DISABLE)) {
						float milked = MilkingRoom.getActualMilkPerHour(slave);
						if(milked < slave.getBreastRawStoredMilkValue() && milked < MilkingRoom.getMaximumMilkPerHour(slave)) {
							milked = Math.min(slave.getBreastRawStoredMilkValue(), MilkingRoom.getMaximumMilkPerHour(slave));
						}
						slave.incrementBreastStoredMilk(-milked);
						
						if(milked>0) {
							if(room.isAutoSellMilk()) {
								income = Math.max(1, (int) (milked * slave.getMilk().getValuePerMl()));
								generatedIncome += income;
								
								events.add(new SlaveryEventLogEntry(hour, slave,
										SlaveEvent.JOB_MILK_MILKED,
										Util.newArrayListOfValues(
												SlaveEventTag.JOB_MILK_SOLD),
										Util.newArrayListOfValues("[style.boldGood("+milked+"ml)] milked: +"+UtilText.formatAsMoney(income, "bold")),
										true));
								
							} else {
								room.incrementMilkStorage(slave.getMilk(), milked);
								
								events.add(new SlaveryEventLogEntry(hour, slave,
										SlaveEvent.JOB_MILK_MILKED,
										Util.newArrayListOfValues(
												SlaveEventTag.JOB_MILK_MILKED),
										Util.newArrayListOfValues("[style.boldGood("+milked+"ml)] added to storage."),
										true));
							}
						}
					}
					if(slave.hasPenisIgnoreDildo() && slave.getPenisRawStoredCumValue()>0 && !slave.getSlaveJobSettings().contains(SlaveJobSetting.MILKING_CUM_DISABLE)) {
						int milked = MilkingRoom.getActualCumPerHour(slave);

						if(milked>0) {
							if(room.isAutoSellCum()) {
								income = Math.max(1, (int) (milked * slave.getCum().getValuePerMl()));
								generatedIncome += income;
								
								events.add(new SlaveryEventLogEntry(hour, slave,
										SlaveEvent.JOB_CUM_MILKED,
										Util.newArrayListOfValues(
												SlaveEventTag.JOB_CUM_SOLD),
										Util.newArrayListOfValues("[style.boldGood("+milked+"ml)] milked: +"+UtilText.formatAsMoney(income, "bold")),
										true));
							
							} else {
								room.incrementCumStorage(slave.getCum(), milked);
								
								events.add(new SlaveryEventLogEntry(hour, slave,
										SlaveEvent.JOB_CUM_MILKED,
										Util.newArrayListOfValues(
												SlaveEventTag.JOB_CUM_MILKED),
										Util.newArrayListOfValues("[style.boldGood("+milked+"ml)] added to storage."),
										true));
							}
						}
					}
					if(slave.hasVagina() && !slave.getSlaveJobSettings().contains(SlaveJobSetting.MILKING_GIRLCUM_DISABLE)) {
						int milked = MilkingRoom.getActualGirlcumPerHour(slave);
						
						if(milked>0) {
							if(room.isAutoSellGirlcum()) {
								income = Math.max(1, (int) (milked * slave.getGirlcum().getValuePerMl()));
								generatedIncome += income;
								
								events.add(new SlaveryEventLogEntry(hour, slave,
										SlaveEvent.JOB_GIRLCUM_MILKED,
										Util.newArrayListOfValues(
												SlaveEventTag.JOB_GIRLCUM_SOLD),
										Util.newArrayListOfValues("[style.boldGood("+milked+"ml)] milked: +"+UtilText.formatAsMoney(income, "bold")),
										true));
							
							} else {
								room.incrementGirlcumStorage(slave.getGirlcum(), milked);
								
								events.add(new SlaveryEventLogEntry(hour, slave,
										SlaveEvent.JOB_GIRLCUM_MILKED,
										Util.newArrayListOfValues(
												SlaveEventTag.JOB_GIRLCUM_MILKED),
										Util.newArrayListOfValues("[style.boldGood("+milked+"ml)] added to storage."),
										true));
							}
						}
					}
					return events;
					
				case TEST_SUBJECT:
					if(slave.getSlaveJobSettings().isEmpty()) {
						if(slave.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
							slave.incrementAffection(Main.game.getPlayer(), 1);
							slave.incrementAffection(Main.game.getLilaya(), 5);
							events.add(new SlaveryEventLogEntry(hour, slave,
									SlaveEvent.JOB_TEST_SUBJECT,
									Util.newArrayListOfValues(
											SlaveEventTag.JOB_LILAYA_INTRUSIVE_TESTING),
									Util.newArrayListOfValues(
                      "[style.boldGood(+1)] [style.boldAffection(Affection)]",
											"[style.boldGood(+5)] [style.boldAffection(Affection towards Lilaya)]"),
									true));

							return events;
							
						} else {
							slave.incrementAffection(Main.game.getPlayer(), -1);
							slave.incrementAffection(Main.game.getLilaya(), -5);
							events.add(new SlaveryEventLogEntry(hour, slave,
									SlaveEvent.JOB_TEST_SUBJECT,
									Util.newArrayListOfValues(
											SlaveEventTag.JOB_LILAYA_INTRUSIVE_TESTING),
									Util.newArrayListOfValues(
											"[style.boldBad(-1)] [style.boldAffection(Affection)]",
											"[style.boldBad(-5)] [style.boldAffection(Affection towards Lilaya)]"),
									true));
							return events;

						}
						
					} else {
						switch(slave.getSlaveJobSettings().get(Util.random.nextInt(slave.getSlaveJobSettings().size()))) {
							case TEST_SUBJECT_ALLOW_TRANSFORMATIONS_FEMALE:
								List<String> list = new ArrayList<>();
								if(slave.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
									slave.incrementAffection(Main.game.getPlayer(), 1);
									slave.incrementAffection(Main.game.getLilaya(), 5);
									list.add("[style.boldGood(+1)] [style.boldAffection(Affection)]");
									list.add("[style.boldGood(+5)] [style.boldAffection(Affection towards Lilaya)]");
								} else {
									slave.incrementAffection(Main.game.getPlayer(), -1);
									slave.incrementAffection(Main.game.getLilaya(), -5);
									list.add("[style.boldBad(-1)] [style.boldAffection(Affection)]");
									list.add("[style.boldBad(-5)] [style.boldAffection(Affection towards Lilaya)]");
								}

								String tf = "";
								if(slave.getSlaveJobSettings().contains(SlaveJobSetting.TEST_SUBJECT_ALLOW_TRANSFORMATIONS_MALE)) {
									tf = getTestSubjectFutanariTransformation(slave);
								} else {
									tf = getTestSubjectFeminineTransformation(slave);
								}
								if(!tf.isEmpty()) {
									list.add(tf);
								}
								events.add(new SlaveryEventLogEntry(hour, slave,
										SlaveEvent.JOB_TEST_SUBJECT,
										Util.newArrayListOfValues(
												SlaveEventTag.JOB_LILAYA_FEMININE_TF),
										list,
										true));
								return events;
								
							case TEST_SUBJECT_ALLOW_TRANSFORMATIONS_MALE:
								List<String> list2 = new ArrayList<>();
								if(slave.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
									slave.incrementAffection(Main.game.getPlayer(), 1);
									slave.incrementAffection(Main.game.getLilaya(), 5);
									list2.add("[style.boldGood(+1)] [style.boldAffection(Affection)]");
									list2.add("[style.boldGood(+5)] [style.boldAffection(Affection towards Lilaya)]");
								} else {
									slave.incrementAffection(Main.game.getPlayer(), -1);
									slave.incrementAffection(Main.game.getLilaya(), -5);
									list2.add("[style.boldBad(-1)] [style.boldAffection(Affection)]");
									list2.add("[style.boldBad(-5)] [style.boldAffection(Affection towards Lilaya)]");
								}
								
								String tf2 = "";
								if(slave.getSlaveJobSettings().contains(SlaveJobSetting.TEST_SUBJECT_ALLOW_TRANSFORMATIONS_FEMALE)) {
									tf2 = getTestSubjectFutanariTransformation(slave);
								} else {
									tf2 = getTestSubjectMasculineTransformation(slave);
								}
								if(!tf2.isEmpty()) {
									list2.add(tf2);
								}
								events.add(new SlaveryEventLogEntry(hour, slave,
										SlaveEvent.JOB_TEST_SUBJECT,
										Util.newArrayListOfValues(
												SlaveEventTag.JOB_LILAYA_MASCULINE_TF),
										list2,
										true));
								return events;
								
							default:
								break;
						}
					}
					break;
					
				case PUBLIC_STOCKS:
					effectDescriptions = new StringBuilder();
					effects = new ArrayList<>();
					settingsEnabled = getSexSettingsEnabled(slave);
					
					GenericSexualPartner stocksPartner;
					
					if(Math.random()<0.25f) {
						stocksPartner = new GenericSexualPartner(Gender.F_P_V_B_FUTANARI, WorldType.ANGELS_KISS_FIRST_FLOOR, slave.getLocation(), false);
					} else {
						stocksPartner = new GenericSexualPartner(Gender.M_P_MALE, WorldType.ANGELS_KISS_FIRST_FLOOR, slave.getLocation(), false);
					}
					
					// If no settings are able to be used, or if a random roll is greater than 0.8, just add a groping event:
					if(settingsEnabled.isEmpty() || Math.random()>0.8f) {
							effectDescriptions.append(UtilText.parse(stocksPartner,
									UtilText.returnStringAtRandom(
											"[npc.A_race] groped and molested "+UtilText.parse(slave, "[npc.namePos] exposed body!"),
											"[npc.A_race] roughly molested "+UtilText.parse(slave, "[npc.namePos] vulnerable body!"),
											"[npc.A_race] spent some time groping and fondling every part of "+UtilText.parse(slave, "[npc.namePos] body!"))));

						effects.add("<span style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Molested:</span> "+effectDescriptions.toString());
						effectDescriptions.setLength(0);
						
					} else {
						SlaveJobSetting eventGenerated = settingsEnabled.get(Util.random.nextInt(settingsEnabled.size()));
						
						switch(eventGenerated) {
							case SEX_ANAL:
								effectDescriptions.append(UtilText.parse(stocksPartner,
										UtilText.returnStringAtRandom(
												"[npc.A_race] came deep inside "+UtilText.parse(slave, "[npc.namePos] [npc.asshole+]!"),
												"[npc.A_race] roughly fucked "+UtilText.parse(slave, "[npc.namePos] [npc.asshole+], before filling [npc.herHim] with"+UtilText.parse(Main.game.getGenericFemaleNPC()," [npc.cum+]!")),
												"[npc.A_race] filled "+UtilText.parse(slave, "[npc.namePos] [npc.asshole+]")+UtilText.parse(Main.game.getGenericFemaleNPC()," with [npc.her] [npc.cum+], "))));
	
								effects.add("<span style='color:"+Colour.CUM.toWebHexString()+";'>Anal Creampie:</span> "+effectDescriptions.toString());
								effectDescriptions.setLength(0);
								slave.calculateGenericSexEffects(false, stocksPartner, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
								
								break;
								
							case SEX_ORAL:
								effectDescriptions.append(UtilText.parse(stocksPartner,
										UtilText.returnStringAtRandom(
												"[npc.A_race] came deep down "+UtilText.parse(slave, "[npc.namePos] throat!"),
												"[npc.A_race] roughly face-fucked "+UtilText.parse(slave, "[npc.name], before filling [npc.her] stomach with"+UtilText.parse(Main.game.getGenericFemaleNPC()," [npc.cum+]!")),
												"[npc.A_race] filled "+UtilText.parse(slave, "[npc.namePos] stomach")+UtilText.parse(Main.game.getGenericFemaleNPC()," with [npc.her] [npc.cum+]!"))));
	
								effects.add("<span style='color:"+Colour.CUM.toWebHexString()+";'>Swallowed Cum:</span> "+effectDescriptions.toString());
								effectDescriptions.setLength(0);

								slave.calculateGenericSexEffects(false, stocksPartner, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
								break;
								
							case SEX_NIPPLES:
								effectDescriptions.append(UtilText.parse(stocksPartner,
										UtilText.returnStringAtRandom(
												"[npc.A_race] came deep inside "+UtilText.parse(slave, "[npc.namePos] [npc.nipples+]!"),
												"[npc.A_race] roughly fucked "+UtilText.parse(slave, "[npc.namePos] [npc.nipples+], before filling [npc.her] [npc.breasts+] with"+UtilText.parse(Main.game.getGenericFemaleNPC()," [npc.cum+]!")),
												"[npc.A_race] filled "+UtilText.parse(slave, "[npc.namePos] [npc.nipples+]")+UtilText.parse(Main.game.getGenericFemaleNPC()," with [npc.her] [npc.cum+], "))));
	
								effects.add("<span style='color:"+Colour.CUM.toWebHexString()+";'>Nipple Creampie:</span> "+effectDescriptions.toString());
								effectDescriptions.setLength(0);

								slave.calculateGenericSexEffects(false, stocksPartner, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS));
								break;
								
							case SEX_VAGINAL:
								effectDescriptions.append(UtilText.parse(stocksPartner,
										UtilText.returnStringAtRandom(
												"[npc.A_race] came deep inside "+UtilText.parse(slave, "[npc.namePos] [npc.pussy+], "),
												"[npc.A_race] roughly fucked "+UtilText.parse(slave, "[npc.namePos] [npc.pussy+], "),
												"[npc.A_race] filled "+UtilText.parse(slave, "[npc.namePos] [npc.pussy+]")+UtilText.parse(Main.game.getGenericFemaleNPC()," with [npc.her] [npc.cum+], "))));

								slave.calculateGenericSexEffects(false, stocksPartner, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
	
								if(slave.isVisiblyPregnant()) {
									effectDescriptions.append(UtilText.parse(slave, "but as [npc.sheIs] already pregnant, the only result is a fresh creampie..."));
									effects.add("<span style='color:"+Colour.CUM.toWebHexString()+";'>Pussy Creampie:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
									
								} else if(!slave.getSlavePermissionSettings().get(SlavePermission.PREGNANCY).contains(SlavePermissionSetting.PREGNANCY_PROMISCUITY_PILLS)) {
									effectDescriptions.append(UtilText.parse(slave, "resulting in a risk of pregnancy!"));
									effects.add("<span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Pregnancy Risk:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
									
								} else {
									if(slave.isHasAnyPregnancyEffects()) {
										effectDescriptions.append(UtilText.parse(slave, "but as [npc.sheIs] on promiscuity pills, there's no chance of [npc.herHim] getting pregnant. ([npc.She] already has a risk of pregnancy from a previous encounter, however...)"));
									} else {
										effectDescriptions.append(UtilText.parse(slave, "but as [npc.sheIs] on promiscuity pills, there's no chance of [npc.herHim] getting pregnant."));
									}
									effects.add("<span style='color:"+Colour.CUM.toWebHexString()+";'>Pussy Creampie:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
								}
								
								break;
								
							default:
								break;
						
						}
					}

					events.add(new SlaveryEventLogEntry(hour, slave,
							SlaveEvent.JOB_PUBLIC_STOCKS,
							Util.newArrayListOfValues(
									SlaveEventTag.JOB_STOCKS_USED),
							effects,
							true));
					return events;
					
				case PROSTITUTE:
					effectDescriptions = new StringBuilder();
					effects = new ArrayList<>();
					settingsEnabled = getSexSettingsEnabled(slave);
					GenericSexualPartner partner;
					
					
					if(Math.random()<0.25f) {
						partner = new GenericSexualPartner(Gender.F_P_V_B_FUTANARI, WorldType.ANGELS_KISS_FIRST_FLOOR, slave.getLocation(), false);
					} else {
						partner = new GenericSexualPartner(Gender.M_P_MALE, WorldType.ANGELS_KISS_FIRST_FLOOR, slave.getLocation(), false);
					}
					try {
						Main.game.addNPC(partner, false);
					} catch (Exception e) {
						e.printStackTrace();
					}
//					System.out.println("partner spawned for "+slave.getName()+": "+partner.getWorldLocation()+" "+partner.getLocation().getX()+", "+partner.getLocation().getY());
					
					// If no settings are able to be used, or if a random roll is greater than 0.8, just add a groping event:
					if(settingsEnabled.isEmpty() || Math.random()>0.8f) {
						effectDescriptions.append(UtilText.parse(partner,
								UtilText.returnStringAtRandom(
										"[npc.A_race] groped and molested "+UtilText.parse(slave, "[npc.namePos] exposed body!"),
										"[npc.A_race] roughly molested "+UtilText.parse(slave, "[npc.namePos] vulnerable body!"),
										"[npc.A_race] spent some time groping and fondling every part of "+UtilText.parse(slave, "[npc.namePos] body!"))));

						effects.add("<span style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Molested:</span> "+effectDescriptions.toString());
						effectDescriptions.setLength(0);
						
					} else {
						SlaveJobSetting eventGenerated = settingsEnabled.get(Util.random.nextInt(settingsEnabled.size()));
						
						switch(eventGenerated) {
							case SEX_ANAL:
								effectDescriptions.append(UtilText.parse(partner,
										UtilText.returnStringAtRandom(
												"[npc.A_race] came deep inside "+UtilText.parse(slave, "[npc.namePos] [npc.asshole+]!"),
												"[npc.A_race] roughly fucked "+UtilText.parse(slave, "[npc.namePos] [npc.asshole+], before filling [npc.herHim] with")+" [npc.cum+]!",
												"[npc.A_race] filled "+UtilText.parse(slave, "[npc.namePos] [npc.asshole+]")+" with [npc.her] [npc.cum+]!")));
									
								effects.add("<span style='color:"+Colour.CUM.toWebHexString()+";'>Anal Creampie:</span> "+effectDescriptions.toString());
								effectDescriptions.setLength(0);

								slave.calculateGenericSexEffects(false, partner, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
								break;
								
							case SEX_ORAL:
								effectDescriptions.append(UtilText.parse(partner,
										UtilText.returnStringAtRandom(
												"[npc.A_race] came deep down "+UtilText.parse(slave, "[npc.namePos] throat!"),
												"[npc.A_race] roughly face-fucked "+UtilText.parse(slave, "[npc.name], before filling [npc.her] stomach with"+UtilText.parse(partner," [npc.cum+]!")),
												"[npc.A_race] filled "+UtilText.parse(slave, "[npc.namePos] stomach")+UtilText.parse(partner," with [npc.her] [npc.cum+]!"))));
	
								effects.add("<span style='color:"+Colour.CUM.toWebHexString()+";'>Swallowed Cum:</span> "+effectDescriptions.toString());
								effectDescriptions.setLength(0);

								slave.calculateGenericSexEffects(false, partner, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
								break;
								
							case SEX_NIPPLES:
								effectDescriptions.append(UtilText.parse(partner,
										UtilText.returnStringAtRandom(
												"[npc.A_race] came deep inside "+UtilText.parse(slave, "[npc.namePos] [npc.nipples+]!"),
												"[npc.A_race] roughly fucked "+UtilText.parse(slave, "[npc.namePos] [npc.nipples+], before filling [npc.her] [npc.breasts+] with"+UtilText.parse(partner," [npc.cum+]!")),
												"[npc.A_race] filled "+UtilText.parse(slave, "[npc.namePos] [npc.nipples+]")+UtilText.parse(partner," with [npc.her] [npc.cum+]!"))));
									
	
								effects.add("<span style='color:"+Colour.CUM.toWebHexString()+";'>Nipple Creampie:</span> "+effectDescriptions.toString());
								effectDescriptions.setLength(0);
								slave.calculateGenericSexEffects(false, partner, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS));
								break;
								
							case SEX_VAGINAL:
								effectDescriptions.append(UtilText.parse(partner,
										UtilText.returnStringAtRandom(
												"[npc.A_race] came deep inside "+UtilText.parse(slave, "[npc.namePos] [npc.pussy+], "),
												"[npc.A_race] roughly fucked "+UtilText.parse(slave, "[npc.namePos] [npc.pussy+], "),
												"[npc.A_race] filled "+UtilText.parse(slave, "[npc.namePos] [npc.pussy+]")+UtilText.parse(partner," with [npc.her] [npc.cum+], "))));

								slave.calculateGenericSexEffects(false, partner, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
								
								if(slave.isVisiblyPregnant()) {
									effectDescriptions.append(UtilText.parse(slave, "but as [npc.sheIs] already pregnant, the only result is a fresh creampie..."));
									effects.add("<span style='color:"+Colour.CUM.toWebHexString()+";'>Pussy Creampie:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
									
								} else if(!slave.getSlavePermissionSettings().get(SlavePermission.PREGNANCY).contains(SlavePermissionSetting.PREGNANCY_PROMISCUITY_PILLS)) {
									effectDescriptions.append(UtilText.parse(slave, "resulting in a risk of pregnancy!"));
									effects.add("<span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Pregnancy Risk:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
									
								} else {
									if(slave.isHasAnyPregnancyEffects()) {
										effectDescriptions.append(UtilText.parse(slave, "but as [npc.sheIs] on promiscuity pills, there's no chance of [npc.herHim] getting pregnant. ([npc.She] already has a risk of pregnancy from a previous encounter, however...)"));
									} else {
										effectDescriptions.append(UtilText.parse(slave, "but as [npc.sheIs] on promiscuity pills, there's no chance of [npc.herHim] getting pregnant."));
									}
									effects.add("<span style='color:"+Colour.CUM.toWebHexString()+";'>Pussy Creampie:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
								}
								
								break;
								
							default:
								break;
						
						}
					}

					events.add(new SlaveryEventLogEntry(hour, slave,
							SlaveEvent.JOB_PROSTITUTE,
							Util.newArrayListOfValues(
									SlaveEventTag.JOB_PROSTITUTE_USED),
							effects,
							true));
					return events;
					
					
				case IDLE:
					// Can not reach :3
					break;
			}
			
		} else { // Slave is resting:
			events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_IDLE, true));
		}

		return events;
	}
	
	private List<SlaveJobSetting> getSexSettingsEnabled(NPC slave) {
		List<SlaveJobSetting> settingsEnabled = new ArrayList<>();
		if(slave.hasVagina() && slave.getSlaveJobSettings().contains(SlaveJobSetting.SEX_VAGINAL) && slave.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
			settingsEnabled.add(SlaveJobSetting.SEX_VAGINAL);
		}
		if(slave.getSlaveJobSettings().contains(SlaveJobSetting.SEX_ANAL) && slave.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
			settingsEnabled.add(SlaveJobSetting.SEX_ANAL);
		}
		if(slave.getSlaveJobSettings().contains(SlaveJobSetting.SEX_ORAL) && slave.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
			settingsEnabled.add(SlaveJobSetting.SEX_ORAL);
		}
		if(slave.isBreastFuckableNipplePenetration() && slave.getSlaveJobSettings().contains(SlaveJobSetting.SEX_NIPPLES) && slave.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
			settingsEnabled.add(SlaveJobSetting.SEX_NIPPLES);
		}
		return settingsEnabled;
	}
	
	private String getTestSubjectFeminineTransformation(NPC slave) {
		if(slave.hasPenis()) {
			slave.setPenisType(PenisType.NONE);
			if(!slave.hasVagina()) {
				slave.setVaginaType(RacialBody.valueOfRace(slave.getRace()).getVaginaType());
			}
			return "[style.boldShrink(Lost penis)], [style.boldGrow(gained vagina)]";
		}
		
		if(!slave.hasVagina()) {
			slave.setVaginaType(RacialBody.valueOfRace(slave.getRace()).getVaginaType());
			return "[style.boldGrow(Gained vagina)]";
		}
		
		if(Math.random()>0.5f) {
			if(slave.getFemininityValue()<100) {
				int increment = Util.random.nextInt(5)+1;
				slave.incrementFemininity(increment);
				return "[style.boldGrow(+"+increment+")] [style.boldFeminine(Femininity)]";
			}
		}
		
		if(slave.getBreastSize().getMeasurement() < CupSize.GG.getMeasurement()) {
			int increment = Util.random.nextInt(1)+1;
			slave.incrementBreastSize(increment);
			return "[style.boldGrow(Gained "+Util.capitaliseSentence(slave.getBreastSize().getCupSizeName())+"-cup breasts)]";
		}
		
		return "";
	}
	
	private String getTestSubjectMasculineTransformation(NPC slave) {
		if(slave.hasVagina()) {
			slave.setVaginaType(VaginaType.NONE);
			if(!slave.hasPenis()) {
				slave.setPenisType(RacialBody.valueOfRace(slave.getRace()).getPenisType());
			}
			return "[style.boldShrink(Lost vagina)], [style.boldGrow(gained penis)]";
		}
		
		if(!slave.hasPenis()) {
			slave.setPenisType(RacialBody.valueOfRace(slave.getRace()).getPenisType());
			return "[style.boldGrow(Gained penis)]";
		}
		
		if(Math.random()>0.5f) {
			if(slave.getFemininityValue()>0) {
				int increment = Util.random.nextInt(5)+1;
				slave.incrementFemininity(-increment);
				return "[style.boldShrink(-"+increment+")] [style.boldFeminine(Femininity)]";
			}
		}
		
		if(slave.getBreastSize().getMeasurement() > 0) {
			int increment = Util.random.nextInt(1)+1;
			slave.incrementBreastSize(-increment);
			return "[style.boldShrink(Breasts shrunk to "+Util.capitaliseSentence(slave.getBreastSize().getCupSizeName())+"-cups)]";
		}
		
		return "";
	}
	
	private String getTestSubjectFutanariTransformation(NPC slave) {
		
		if(!slave.hasVagina()) {
			slave.setVaginaType(RacialBody.valueOfRace(slave.getRace()).getVaginaType());
			return "[style.boldGrow(Gained vagina)]";
		}
		
		if(!slave.hasPenis()) {
			slave.setPenisType(RacialBody.valueOfRace(slave.getRace()).getPenisType());
			return "[style.boldGrow(Gained penis)]";
		}
		
		if(Math.random()>0.5f) {
			if(slave.getFemininityValue()<100) {
				int increment = Util.random.nextInt(5)+1;
				slave.incrementFemininity(increment);
				return "[style.boldGrow(+"+increment+")] [style.boldFeminine(Femininity)]";
			}
		}
		
		if(slave.getBreastSize().getMeasurement() < CupSize.GG.getMeasurement()) {
			int increment = Util.random.nextInt(1)+1;
			slave.incrementBreastSize(increment);
			return "[style.boldGrow(Gained "+Util.capitaliseSentence(slave.getBreastSize().getCupSizeName())+"-cup breasts)]";
		}
		
		return "";
	}
	
	/**
	 * 
	 * @param hour Pass in hour of the day
	 * @param slave
	 * @param otherSlavesPresent
	 * @return
	 */
	public static SlaveryEventLogEntry generateNPCInteractionEvent(int day, int hour, NPC slave, List<NPC> otherSlavesPresent) {
		
		if(slave.getSlaveJob()==SlaveJob.PUBLIC_STOCKS) {
			return null;
		}
		
		Collections.shuffle(otherSlavesPresent);
		for(NPC npc : otherSlavesPresent) {
			if(!npc.equals(slave)) {
				if(slave.getLastTimeHadSex()+24*60<Main.game.getMinutesPassed()) { // They only want sex once a day, to stop the logs from being flooded
					if(slave.isAttractedTo(npc) && npc.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RECEIVE_SLAVES) && slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_SLAVES)) {
						
						boolean canImpregnate =
								slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATE)
								&& npc.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATED)
								&& slave.hasPenis()
								&& slave.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
								&& npc.hasVagina()
								&& npc.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
						
						boolean canBeImpregnated = 
								!slave.isVaginaVirgin()
								&& slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATED)
								&& npc.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATE)
								&& npc.hasPenis()
								&& npc.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
								&& slave.hasVagina()
								&& slave.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
						
						boolean impregnationAttempt = false, gettingPregnantAttempt = false;
						
						// Apply sex effects:
						if(canImpregnate) {
							npc.ingestFluid(slave, slave.getCumType(), SexAreaOrifice.VAGINA, slave.getPenisRawOrgasmCumQuantity(), slave.getCum().getFluidModifiers());
							slave.applyOrgasmCumEffect();
							npc.setVaginaVirgin(false);
							impregnationAttempt = true;
						}
						if(canBeImpregnated) {
							slave.ingestFluid(npc, npc.getCumType(), SexAreaOrifice.VAGINA, npc.getPenisRawOrgasmCumQuantity(), npc.getCum().getFluidModifiers());
							npc.applyOrgasmCumEffect();
							slave.setVaginaVirgin(false);
							gettingPregnantAttempt = true;
						}
						slave.setLastTimeHadSex((day*24*60l) + hour*60l, true);
						
						
						switch(slave.getSlaveJob()) {
							case CLEANING:
								return new SlaveryEventLogEntry(hour,
										slave,
										SlaveEvent.SLAVE_SEX,
										null,
										Util.newArrayListOfValues(UtilText.parse(slave, npc,
												"While dusting one of the first-floor corridors, [npc1.name] caught sight of [npc2.name],"
												+ " and couldn't resist pulling [npc2.herHim] into an empty room and giving [npc2.herHim] a "+slave.getTheoreticalSexPaceDomPreference().getName()+" fucking."
												+ (impregnationAttempt?"<br/>[style.colourSex([npc2.Name] might have gotten pregnant!)]":"")
												+ (gettingPregnantAttempt?"<br/>[style.colourSex([npc1.Name] might have gotten pregnant!)]":""))),
										true);
								
							case IDLE: //TODO
								return new SlaveryEventLogEntry(hour,
										slave,
										SlaveEvent.SLAVE_SEX,
										null,
										Util.newArrayListOfValues(UtilText.parse(slave, npc,
												"[npc1.name] gave [npc2.name] a "+slave.getTheoreticalSexPaceDomPreference().getName()+" fucking."
												+ (impregnationAttempt?"<br/>[style.colourSex([npc2.Name] might have gotten pregnant!)]":"")
												+ (gettingPregnantAttempt?"<br/>[style.colourSex([npc1.Name] might have gotten pregnant!)]":""))),
										true);
							case KITCHEN:
								return new SlaveryEventLogEntry(hour,
										slave,
										SlaveEvent.SLAVE_SEX,
										null,
										Util.newArrayListOfValues(UtilText.parse(slave, npc,
												"While working in the kitchen, [npc1.name] saw [npc2.name] enter the pantry alone,"
														+ " and couldn't resist following [npc2.herHim] inside, before locking the door and giving [npc2.herHim] a "+slave.getTheoreticalSexPaceDomPreference().getName()+" fucking."
												+ (impregnationAttempt?"<br/>[style.colourSex([npc2.Name] might have gotten pregnant!)]":"")
												+ (gettingPregnantAttempt?"<br/>[style.colourSex([npc1.Name] might have gotten pregnant!)]":""))),
										true);
								
							case LAB_ASSISTANT: case TEST_SUBJECT:
								return new SlaveryEventLogEntry(hour,
										slave,
										SlaveEvent.SLAVE_SEX,
										null,
										Util.newArrayListOfValues(UtilText.parse(slave, npc,
												"When Lilaya left the lab to take a break, [npc1.name] used the opportunity to give [npc2.name] a "+slave.getTheoreticalSexPaceDomPreference().getName()+" fucking on one of the lab's tables."
												+ (impregnationAttempt?"<br/>[style.colourSex([npc2.Name] might have gotten pregnant!)]":"")
												+ (gettingPregnantAttempt?"<br/>[style.colourSex([npc1.Name] might have gotten pregnant!)]":""))),
										true);
								
							case LIBRARY:
								return new SlaveryEventLogEntry(hour,
										slave,
										SlaveEvent.SLAVE_SEX,
										null,
										Util.newArrayListOfValues(UtilText.parse(slave, npc,
												"[npc1.Name] pulled [npc2.name] behind one of the shelves in the Library, before giving [npc2.herHim] a "+slave.getTheoreticalSexPaceDomPreference().getName()+" fucking."
												+ (impregnationAttempt?"<br/>[style.colourSex([npc2.Name] might have gotten pregnant!)]":"")
												+ (gettingPregnantAttempt?"<br/>[style.colourSex([npc1.Name] might have gotten pregnant!)]":""))),
										true);
							case PUBLIC_STOCKS:
								//TODO 
							case MILKING:
								//TODO 
							case PROSTITUTE:
								//TODO 
								break;
						}
					}
				}
			}
		}
		
//		TODO
//		boolean silence = slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
//		boolean crawling = slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING);
		
		return null;
	}
	
	
	public MilkingRoom getMilkingRoom(WorldType worldType, Vector2i location) {
		for(MilkingRoom room : getMilkingRooms()) {
			if(room.getWorldType() == worldType && room.getLocation().equals(location)) {
				return room;
			}
		}
		return null;
	}
	
	public List<MilkingRoom> getMilkingRooms() {
		return milkingRooms;
	}
	
	public boolean addMilkingRoom(MilkingRoom milkingRoom) {
		return milkingRooms.add(milkingRoom);
	}
	
	public boolean removeMilkingRoom(MilkingRoom milkingRoom) {
		return milkingRooms.remove(milkingRoom);
	}

	public void payOutBalance() {
		Main.game.getPlayer().incrementMoney(getGeneratedBalance());
		
		generatedIncome = 0;
		generatedUpkeep = 0;
	}
	
	public int getGeneratedBalance() {
		return generatedIncome - generatedUpkeep;
	}
	
	public int getGeneratedIncome() {
		return generatedIncome;
	}
	
	public void setGeneratedIncome(int generatedIncome) {
		this.generatedIncome = generatedIncome;
	}

	public void setGeneratedUpkeep(int generatedUpkeep) {
		this.generatedUpkeep = generatedUpkeep;
	}

	public int getGeneratedUpkeep() {
		return generatedUpkeep;
	}
	
	private void incrementSlaveDailyIncome(NPC slave, int increment) {
		dailyIncome.putIfAbsent(slave, 0);
		dailyIncome.put(slave, dailyIncome.get(slave)+increment);
	}

	public Map<SlaveJob, List<NPC>> getSlavesAtJob() {
		return slavesAtJob;
	}

	public List<NPC> getSlavesResting() {
		return slavesResting;
	}
	
	public static boolean isFreeRoomAvailableForOccupant() {
		return getFreeRoomForOccupant()!=null;
	}
	
	public static Cell getFreeRoomForOccupant() {
		Cell[][] grid = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCellGrid();
		
		for(int i=0; i< grid.length; i++) {
			for(int j=0; j< grid[0].length; j++) {
				Cell c = grid[i][j];
				if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_GUEST_ROOM) && Main.game.getCharactersTreatingCellAsHome(c).isEmpty()) {
					return c;
				}
			}
		}
		
		grid = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCellGrid();
		
		for(int i=0; i< grid.length; i++) {
			for(int j=0; j< grid[0].length; j++) {
				Cell c = grid[i][j];
				if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_GUEST_ROOM) && Main.game.getCharactersPresent(c).isEmpty()) {
					return c;
				}
			}
		}
		
		return null;
	}
}
