package com.lilithsthrone.game.occupantManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.eventLog.SlaveryEventLogEntry;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobFlag;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.occupantManagement.slave.TestSubjectTransformation;
import com.lilithsthrone.game.occupantManagement.slaveEvent.SlaveEvent;
import com.lilithsthrone.game.occupantManagement.slaveEvent.SlaveEventTag;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * A class to handle all occupant-related turn mechanics and persistent variables. Deals with moving slaves to/from jobs and generating events for them. Also sends friendly occupants to/from jobs.
 * 
 * @since 0.1.87
 * @version 0.3.9
 * @author Innoxia
 */
public class OccupancyUtil implements XMLSaving {
	
	/** Maps job to ids of slaves who are currently at that job. */
	private Map<SlaveJob, List<String>> charactersAtJob;
	private List<NPC> charactersResting;
	
	private int generatedIncome;
	private int generatedUpkeep;
	
	private List<MilkingRoom> milkingRooms;
	
	// Settings:
	private List<SlavePermissionSetting> enabledByDefaultPermissionSettings;
	private Map<SlaveJob, List<SlaveJobSetting>> enabledByDefaultJobSettings;
	
	// Slave income:
	private Map<NPC, Integer> dailyIncome;
	
	public OccupancyUtil() {
		charactersAtJob = new HashMap<>();
		for(SlaveJob job : SlaveJob.values()) {
			charactersAtJob.put(job, new ArrayList<>());
		}
		
		charactersResting = new ArrayList<>();
		generatedIncome = 0;
		generatedUpkeep = 0;
		
		milkingRooms = new ArrayList<>();
		
		dailyIncome = new HashMap<>();
		
		enabledByDefaultPermissionSettings = new ArrayList<>();
		for(SlavePermissionSetting setting : SlavePermissionSetting.values()) {
			if(setting.isDefaultValue()) {
				enabledByDefaultPermissionSettings.add(setting);
			}
		}
		
		enabledByDefaultJobSettings = new HashMap<>();
		for(SlaveJob job : SlaveJob.values()) {
			enabledByDefaultJobSettings.put(job, new ArrayList<>());
			for(SlaveJobSetting setting : job.getDefaultMutualSettings()) {
				enabledByDefaultJobSettings.get(job).add(setting);
			}
		}
	}

	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("slavery");
		parentElement.appendChild(element);
		
		XMLUtil.addAttribute(doc, element, "generatedIncome", String.valueOf(Main.game.getOccupancyUtil().getGeneratedIncome()));
		XMLUtil.addAttribute(doc, element, "generatedUpkeep", String.valueOf(Main.game.getOccupancyUtil().getGeneratedUpkeep()));
		
		Element slavesAtJobElement = doc.createElement("slavesAtJob");
		element.appendChild(slavesAtJobElement);
		for(Entry<SlaveJob, List<String>> entry : charactersAtJob.entrySet()) {
			if(!entry.getValue().isEmpty()) {
				Element jobElement = doc.createElement("slaves");
				XMLUtil.addAttribute(doc, jobElement, "job", entry.getKey().toString());
				slavesAtJobElement.appendChild(jobElement);
				for(String id : entry.getValue()) {
					Element slaveIdElement = doc.createElement("id");
					jobElement.appendChild(slaveIdElement);
					slaveIdElement.setTextContent(id);
				}
			}
		}
		
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
			
			NodeList slavesAtJobElement = parentElement.getElementsByTagName("slavesAtJob");
			if(slavesAtJobElement.item(0)!=null) {
				Element e = ((Element)slavesAtJobElement.item(0));
				
				NodeList jobs = e.getElementsByTagName("slaves");
				for(int i=0; i<jobs.getLength(); i++) {
					Element jobElement = ((Element)jobs.item(i));
					SlaveJob job = SlaveJob.valueOf(jobElement.getAttribute("job"));
					slaveryUtil.charactersAtJob.put(job, new ArrayList<>());
					NodeList slaveIds = jobElement.getElementsByTagName("id");
					for(int j=0; j<slaveIds.getLength(); j++) {
						Element slaveId = ((Element)slaveIds.item(j));
						slaveryUtil.charactersAtJob.get(job).add(slaveId.getTextContent());
					}
				}
			}
			
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
	
	public void handleSlaveRemoval(GameCharacter character) {
		charactersAtJob.values().forEach(list->list.remove(character.getId()));
		charactersResting.remove(character);
	}
	
	private void clearJobTracking() {
		for(SlaveJob job : SlaveJob.values()) {
			charactersAtJob.get(job).clear();
		}
		charactersResting.clear();
	}

	/**
	 * Should only be used at the end of a game import to populate the slavesResting list.
	 */
	public void updateSlavesResting(int hour) {
		for(String id : Main.game.getPlayer().getSlavesOwned()) {
			try {
				NPC slave = (NPC) Main.game.getNPCById(id);

				SlaveJob currentJob = slave.getSlaveJob(hour);
				
				if(Main.game.getPlayer().hasCompanion(slave)) {
					continue;
				}
				if(currentJob==SlaveJob.IDLE) {
					charactersResting.add(slave);
				}
			} catch (Exception e) {
				Util.logGetNpcByIdError("updateSlavesResting()", id);
			}
		}
	}
	
	/**
	 * Resets daily dialogue flags and searches for a job (if the occupant is still marked as a lowlife).
	 */
	public void dailyOccupantUpdate(NPC occupant) {
		occupant.resetOccupantFlags();
		
		if(!occupant.getDesiredJobs().isEmpty()) {
			if(Math.random()<0.2) {
				occupant.assignNewJob();
				occupant.setFlag(NPCFlagValue.occupantHasNewJob, true);
			}
		}
	}
	
	/**
	 * @return A List of all slaves owner by the player, plus all friendly occupants.
	 */
	public List<String> getAllCharacters() {
		return Util.mergeLists(Main.game.getPlayer().getFriendlyOccupants(), Main.game.getPlayer().getSlavesOwned());
	}
	
	public int getCharactersWorkingJob(int hour, SlaveJob job) {
		int i=0;
		for(String id : this.getAllCharacters()) {
			try {
				if(Main.game.getNPCById(id).getSlaveJob(hour)==job) {
					i++;
				}
			} catch (Exception e) {
				Util.logGetNpcByIdError("getSlavesWorkingJob()", id);
			}
		}
		return i;
	}
	
	public int getTotalCharactersWorkingJob(SlaveJob job) {
		int i=0;
		for(String id : this.getAllCharacters()) {
			try {
				for(int hour=0; hour<24; hour++) {
					if(Main.game.getNPCById(id).getSlaveJob(hour)==job) {
						i++;
						break;
					}
				}
			} catch (Exception e) {
				Util.logGetNpcByIdError("getTotalSlavesWorkingJob()", id);
			}
		}
		return i;
	}
	
	/**
	 * @return A List of ids of characters who are currently working at the job.
	 * <br/>Modifications to this List will not affect the underlying List.
	 */
	public List<String> getCharactersCurrentlyAtJob(SlaveJob job) {
		if(!charactersAtJob.containsKey(job) || charactersAtJob.get(job)==null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(charactersAtJob.get(job));
	}
	
	public void performHourlyUpdate(int day, int hour) {
		// Non-slave occupants:
		for(String id : Main.game.getPlayer().getFriendlyOccupants()) {
			try {
				NPC occupant = (NPC) Main.game.getNPCById(id);
				if(!Main.game.getPlayer().isActive() || !Main.game.getCharactersPresent().contains(occupant)) { // If the player isn't interacting with them, then move them:
//					if(!occupant.getHistory().getOccupationTags().contains(OccupationTag.LOWLIFE)) {
						if(occupant.getHistory().isAtWork(hour)) {
							occupant.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
						} else {
							occupant.setLocation(occupant.getHomeWorldLocation(), occupant.getHomeLocation(), false);
						}
//					}
				}
			} catch (Exception e) {
				Util.logGetNpcByIdError("performHourlyUpdate(), getFriendlyOccupants() section.", id);
			}
		}
		
		
		// Slaves:
		Map<String, SlaveJob> previousJobs = new HashMap<>();
		for(Entry<SlaveJob, List<String>> entry : charactersAtJob.entrySet()) {
			for(String npcId : entry.getValue()) {
				previousJobs.put(npcId, entry.getKey());
			}
		}
		
		clearJobTracking(); // Reset slavesAtJob and slavesResting
		
		// First need to set correct jobs and handle miscellaneous updates:
		List<NPC> charactersToSendToWork = new ArrayList<>();
		for(String id : this.getAllCharacters()) {
			try {
				NPC character = (NPC) Main.game.getNPCById(id);

				SlaveJob currentJob = character.getSlaveJob(hour);
				
				if(Main.game.getPlayer().hasCompanion(character)) {
					continue;
				}
				
				if(character.isVisiblyPregnant() && !character.hasStatusEffect(StatusEffect.PREGNANT_3) && character.getSlavePermissionSettings().get(SlavePermission.PREGNANCY).contains(SlavePermissionSetting.PREGNANCY_MOTHERS_MILK)) {
					ItemEffectType.MOTHERS_MILK.applyEffect(null, null, null, 0, character, character, null);
				}

				charactersAtJob.get(currentJob).add(character.getId());
				
				if(!Main.game.getPlayer().isActive() || !Main.game.getCharactersPresent().contains(character) // If the player isn't interacting with them, then move them
						|| Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_PLAYER) { // Also move slaves who are in bedroom but have elsewhere to be
					charactersAtJob.get(currentJob).add(character.getId());
					
					if(character.getSlaveJob((hour-1<0?23:hour-1))==SlaveJob.PROSTITUTE) {
						// Remove client before leaving:
						List<NPC> charactersPresent = Main.game.getCharactersPresent(character.getWorldLocation(), character.getLocation());
						charactersPresent.removeAll(Main.game.getPlayer().getCompanions());
						for(NPC npc : charactersPresent) {
							if(npc instanceof GenericSexualPartner) {
								Main.game.banishNPC(npc);
							}
						}
					}

					if(currentJob==SlaveJob.IDLE) {
						charactersResting.add(character);
					} else {
						charactersToSendToWork.add(character);
					}

				}
				if(Main.game.getCurrentDialogueNode()==RoomPlayer.AUNT_HOME_PLAYERS_ROOM_SLEEP) {
					Main.game.updateResponses();
				}
			} catch (Exception e) {
				Util.logGetNpcByIdError("performHourlyUpdate(), all npcs section.", id);
			}
		}

		
		// a slave's room always has enough room, so do this first
		for(NPC slave : charactersResting) {
			updateJob(slave, hour, previousJobs);
		}

		// Send slaves to work after others have left, so that job rooms are emptied before trying to fill them:
		for(NPC slave : charactersToSendToWork) {
			updateJob(slave, hour, previousJobs);
		}
		
		// Now can apply changes and generate events based on who else is present in the job:
		for(String id : Main.game.getPlayer().getSlavesOwned()) {
			NPC slave;
			
			try {
				slave = (NPC) Main.game.getNPCById(id);
			} catch (Exception e) {
				Util.logGetNpcByIdError("performHourlyUpdate(), getSlavesOwned() section second instance.", id);
				continue;
			}
			
			if(Main.game.getPlayer().hasCompanion(slave)) {
				continue;
			}
			
			SlaveJob currentJob = slave.getSlaveJob(hour);
			
			slave.incrementAffection(slave.getOwner(), slave.getHourlyAffectionChange(hour));
			slave.incrementObedience(slave.getHourlyObedienceChange(hour), true);
			
			boolean isAtWork = slave.isAtWork();
			
			// If at work:
			if(isAtWork) {
				float workQuality = 1f;
				
				// Get paid for hour's work:
				if(currentJob!=SlaveJob.IDLE) {
					int income = currentJob.getFinalHourlyIncomeAfterModifiers(slave);
					generatedIncome += income;
					incrementSlaveDailyIncome(slave, income);
				}
				
				// Overworked effect:
				if(slave.hasStatusEffect(StatusEffect.OVERWORKED_1)) {
					slave.incrementAffection(slave.getOwner(), -0.5f);
					workQuality *= 0.75f; // If overworked, they have a lowered chance to gain experience.
					
				} else if(slave.hasStatusEffect(StatusEffect.OVERWORKED_2)) {
					slave.incrementAffection(slave.getOwner(), -1f);
					workQuality *= 0.5f; // If overworked, they have a lowered chance to gain experience.
					
				} else if(slave.hasStatusEffect(StatusEffect.OVERWORKED_3)) {
					slave.incrementAffection(slave.getOwner(), -2f);
					workQuality *= 0.25f; // If overworked, they have a lowered chance to gain experience.
				}
				
				// chance to gain experience based on profits
				if(currentJob.hasFlag(SlaveJobFlag.EXPERIENCE_GAINS)
						&& workQuality>Math.random()*4) {
					slave.incrementExperience(5, false);
				}
				
			} else {
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_MASTURBATE)
						&& (slave.getLastTimeOrgasmedSeconds()+(60*60*(24+12))<Main.game.getSecondsPassed())) { // Give them a 12-hour period of pent up, so they will have the chance to ambush the player or have sex with other slaves
					slave.setLastTimeHadSex((day*24*60l) + hour*60l, true);
				}
			}
			
			
			
			// ***** EVENTS: ***** //
			
			// Washing body:
			if(slave.hasSlavePermissionSetting(SlavePermissionSetting.CLEANLINESS_WASH_BODY)
					&& !isAtWork
					&& !slave.getDirtySlots().isEmpty()
					&& !Main.game.getCharactersPresent().contains(slave)) {
				SlaveryEventLogEntry entry = new SlaveryEventLogEntry(hour,
						slave,
						null,
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
				
				Main.game.addSlaveryEvent(day, entry);
			}
			
			// Washing clothes:
			if((slave.hasStatusEffect(StatusEffect.CLOTHING_CUM) || !slave.getDirtySlots().isEmpty())
					&& !isAtWork
					&& slave.hasSlavePermissionSetting(SlavePermissionSetting.CLEANLINESS_WASH_CLOTHES)
					&& !Main.game.getCharactersPresent().contains(slave)) {
				Main.game.addSlaveryEvent(day, new SlaveryEventLogEntry(hour,
						slave,
						null,
						SlaveEvent.WASHED_CLOTHES,
						Util.newArrayListOfValues(SlaveEventTag.WASHED_CLOTHES),
						true));
			}
			
			// Events:
			boolean eventAdded = false;
			SlaveryEventLogEntry entry = null;
			// Interaction events:
			if(charactersAtJob.get(currentJob).size()>1 || (currentJob==SlaveJob.IDLE && slave.getLocationPlace().getPlaceType()!=PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION)) {
				if(Math.random()<0.25f
						&& !Main.game.getCharactersPresent().contains(slave) // Do not generate sex events if the player is present.
						&& !slave.getLocationPlace().getPlaceUpgrades().stream().anyMatch(upgrade -> upgrade.getImmobilisationType()!=null)) { // Do not generate sex events if the slave is in a place that applies immobilisation.
					List<NPC> slavesPresent = new ArrayList<>();
					for(String npcId : charactersAtJob.get(currentJob)) {
						try {
							NPC slaveAtJob = (NPC) Main.game.getNPCById(npcId);
							if(slaveAtJob.getLocationPlace().getPlaceType()!=PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION) {
								slavesPresent.add(slaveAtJob);
							}
						} catch(Exception ex) {
						}
					}
					entry = generateNPCInteractionEvent(day, hour, slave, slavesPresent);
					if(entry!=null) {
						Main.game.addSlaveryEvent(day, entry);
						eventAdded = true;
					}
				}
			}
			// Standard events:
			if(!eventAdded) {
				if(currentJob==SlaveJob.PROSTITUTE) {
					// Remove client:
					List<NPC> charactersPresent = Main.game.getCharactersPresent(slave.getWorldLocation(), slave.getLocation());
					charactersPresent.removeAll(Main.game.getPlayer().getCompanions());
					for(NPC npc : charactersPresent) {
						if(npc instanceof GenericSexualPartner) {
//							System.out.println("partner removed for "+slave.getName());
							Main.game.banishNPC(npc);
						}
					}
				}
				
				if(Math.random()<=currentJob.getHourlyEventChance()) {
					List<SlaveryEventLogEntry> entries = generateEvents(hour, slave);
					for(SlaveryEventLogEntry e : entries) {
						Main.game.addSlaveryEvent(day, e);
						eventAdded = true;
					}
				}
			}
			
			if(hour%24==0) { // At the start of a new day:
				SlaveryEventLogEntry dailyEntry = new SlaveryEventLogEntry(hour,
						slave,
						null,
						SlaveEvent.DAILY_UPDATE,
						null,
						true);
				
				// Payments:
				if(dailyIncome.containsKey(slave)) {
					dailyEntry.addExtraEffect("[style.boldGood(Earned)] "+UtilText.formatAsMoney(dailyIncome.get(slave)));
				}
				
				// Muscle:
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_FORBIDDEN)) {
					if(slave.getMuscleValue()>0) {
						dailyEntry.addTag(SlaveEventTag.DAILY_MUSCLE_LOSS_LARGE, slave, true);
					}
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_REST)) {
					if(slave.getMuscleValue()>Muscle.ONE_LIGHTLY_MUSCLED.getMaximumValue()) {
						dailyEntry.addTag(SlaveEventTag.DAILY_MUSCLE_LOSS, slave, true);
						
					} else if(slave.getMuscleValue()<Muscle.ONE_LIGHTLY_MUSCLED.getMinimumValue()) {
						dailyEntry.addTag(SlaveEventTag.DAILY_MUSCLE_GAIN, slave, true);
					}
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_NORMAL)) {
					if(slave.getMuscleValue()>Muscle.TWO_TONED.getMaximumValue()) {
						dailyEntry.addTag(SlaveEventTag.DAILY_MUSCLE_LOSS, slave, true);
						
					} else if(slave.getMuscleValue()<Muscle.TWO_TONED.getMinimumValue()) {
						dailyEntry.addTag(SlaveEventTag.DAILY_MUSCLE_GAIN, slave, true);
					}
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_TRAINING)) {
					if(slave.getMuscleValue()>Muscle.THREE_MUSCULAR.getMaximumValue()) {
						dailyEntry.addTag(SlaveEventTag.DAILY_MUSCLE_LOSS, slave, true);
						
					} else if(slave.getMuscleValue()<Muscle.THREE_MUSCULAR.getMinimumValue()) {
						dailyEntry.addTag(SlaveEventTag.DAILY_MUSCLE_GAIN, slave, true);
					}
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_BODY_BUILDING)) {
					if(slave.getMuscleValue()<100) {
						dailyEntry.addTag(SlaveEventTag.DAILY_MUSCLE_GAIN_LARGE, slave, true);
					}
				}
				
				// Body size:
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_DIET_EXTREME)) {
					if(slave.getBodySizeValue()>0) {
						dailyEntry.addTag(SlaveEventTag.DAILY_BODY_SIZE_LOSS_LARGE, slave, true);
					}
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_DIET)) {
					if(slave.getBodySizeValue()>BodySize.ONE_SLENDER.getMaximumValue()) {
						dailyEntry.addTag(SlaveEventTag.DAILY_BODY_SIZE_LOSS, slave, true);
						
					} else if(slave.getBodySizeValue()<BodySize.ONE_SLENDER.getMinimumValue()) {
						dailyEntry.addTag(SlaveEventTag.DAILY_BODY_SIZE_GAIN, slave, true);
					}
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_NORMAL)) {
					if(slave.getBodySizeValue()>BodySize.TWO_AVERAGE.getMaximumValue()) {
						dailyEntry.addTag(SlaveEventTag.DAILY_BODY_SIZE_LOSS, slave, true);
						
					} else if(slave.getBodySizeValue()<BodySize.TWO_AVERAGE.getMinimumValue()) {
						dailyEntry.addTag(SlaveEventTag.DAILY_BODY_SIZE_GAIN, slave, true);
					}
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_PLUS)) {
					if(slave.getBodySizeValue()>BodySize.THREE_LARGE.getMaximumValue()) {
						dailyEntry.addTag(SlaveEventTag.DAILY_BODY_SIZE_LOSS, slave, true);
						
					} else if(slave.getBodySizeValue()<BodySize.THREE_LARGE.getMinimumValue()) {
						dailyEntry.addTag(SlaveEventTag.DAILY_BODY_SIZE_GAIN, slave, true);
					}
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_LAVISH)) {
					if(slave.getBodySizeValue()<100) {
						dailyEntry.addTag(SlaveEventTag.DAILY_BODY_SIZE_GAIN_LARGE, slave, true);
					}
				}
				
				if(dailyEntry.getTags()!=null || dailyEntry.getExtraEffects()!=null) {
					Main.game.addSlaveryEvent(day, dailyEntry);
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
				try {
					NPC occupant = (NPC) Main.game.getNPCById(id);
					if(occupant.hasJob()) {
						generatedIncome += PlaceUpgrade.LILAYA_GUEST_ROOM.getUpkeep();
					}
				} catch (Exception e) {
					Util.logGetNpcByIdError("performHourlyUpdate(), getFriendlyOccupants() section second instance.", id);
				}
			}
		}
		
	}

	/**
	 * @param slave The slave whose job gets updated.
	 * @param hour Time at which this event is happening.
	 * @param previousJobs Map with the previous job of all slaves
	 */
	private void updateJob(NPC slave, int hour, Map<String, SlaveJob> previousJobs) {
		SlaveJob currentJob = slave.getSlaveJob(hour);
		SlaveJob previousJob = previousJobs.get(slave.getId());
		// System.out.println(slave.getName()+": "+previousJob+" -> "+currentJob);
		if(previousJob!=null && previousJob!=currentJob) {
			previousJob.applyJobEndEffects(slave);
		}
		currentJob.sendToWorkLocation(slave);
		currentJob.applyJobStartEffects(slave);
	}

	/**
	 * @param hour Time at which this event is happening.
	 * @param slave The slave to calculate an event for.
	 */
	private List<SlaveryEventLogEntry> generateEvents(int hour, NPC slave) {
		
		SlaveJob currentJob = slave.getSlaveJob(hour);

		StringBuilder effectDescriptions = new StringBuilder();
		List<String> effects = new ArrayList<>();
		List<SlaveJobSetting> settingsEnabled = new ArrayList<>();
		
		List<SlaveryEventLogEntry> events = new ArrayList<>();
		//TODO this should be handled in the job itself
		if(charactersAtJob.get(currentJob).contains(slave.getId()) && currentJob!=SlaveJob.IDLE) { // Slave is working:
			switch (currentJob) {
				case CLEANING:
					//TODO
//					events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_CLEANING, true));
					return events;
					
				case KITCHEN:
					//TODO
//					events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_COOKING, true));
					return events;

				case GARDEN:
					//TODO
//					events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_GARDEN, true));
					return events;
					
				case SECURITY:
					//TODO
//					events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_SECURITY, true));
					return events;
					
				case LAB_ASSISTANT:
					//TODO
//					events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_LAB_ASSISTANT, true));
					return events;
					
				case LIBRARY:
					//TODO
//					events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_LIBRARIAN, true));
					return events;

				case OFFICE:
					//TODO
//					if(slave.getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_OFFICE)) {
//						events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_OFFICE, true));
//					}
					return events;

				case BEDROOM:
					//TODO
					return events;

				case SPA:
					//TODO
					return events;

				case SPA_RECEPTIONIST:
					//TODO
					return events;
					
				case DINING_HALL:
					//TODO
					return events;
					
				case MILKING:
					int income = 0;
					List<String> milkingSold = new ArrayList<>();
					List<String> milkingStored = new ArrayList<>();
					
					Cell c = MilkingRoom.getMilkingCell(slave, false);
					if(c!=null) {
						MilkingRoom room = this.getMilkingRoom(c.getType(), c.getLocation());
						
						if(slave.getClothingInSlot(InventorySlot.NIPPLE)!=null
								&& slave.getClothingInSlot(InventorySlot.NIPPLE).isMilkingEquipment()) {
							float milked = MilkingRoom.getActualMilkPerHour(slave);
							if(milked < slave.getBreastRawStoredMilkValue() && milked < MilkingRoom.getMaximumMilkPerHour(slave)) {
								milked = Math.min(slave.getBreastRawStoredMilkValue(), MilkingRoom.getMaximumMilkPerHour(slave));
							}
							slave.incrementBreastStoredMilk(-milked);
							
							if(milked>0) {
								if(slave.hasSlaveJobSetting(currentJob, SlaveJobSetting.MILKING_MILK_AUTO_SELL)) {
									income += Math.max(1, (int) (milked * slave.getMilk().getValuePerMl()));
									milkingSold.add("[style.colourMilk("+ Units.fluid(milked) +")] [npc.milk] sold: +"+UtilText.formatAsMoney(income, "bold"));
									
								} else {
									room.incrementFluidStored(new FluidStored(slave.getId(), slave.getMilk(), milked), milked);
									milkingStored.add("[style.colourMilk("+ Units.fluid(milked) +")] [npc.milk] stored.");
								}
							}
						}
						if(slave.getClothingInSlot(InventorySlot.STOMACH)!=null
								&& slave.getClothingInSlot(InventorySlot.STOMACH).isMilkingEquipment()) {
							float milked = MilkingRoom.getActualCrotchMilkPerHour(slave);
							if(milked < slave.getBreastCrotchRawStoredMilkValue() && milked < MilkingRoom.getMaximumMilkPerHour(slave)) {
								milked = Math.min(slave.getBreastCrotchRawStoredMilkValue(), MilkingRoom.getMaximumMilkPerHour(slave));
							}
							slave.incrementBreastCrotchStoredMilk(-milked);
							
							if(milked>0) {
								if(slave.hasSlaveJobSetting(currentJob, SlaveJobSetting.MILKING_MILK_CROTCH_AUTO_SELL)) {
									income += Math.max(1, (int) (milked * slave.getMilkCrotch().getValuePerMl()));
									milkingSold.add("[style.colourMilk("+ Units.fluid(milked) +")] [npc.crotchMilk] sold: +"+UtilText.formatAsMoney(income, "bold"));
									
								} else {
									room.incrementFluidStored(new FluidStored(slave.getId(), slave.getMilkCrotch(), milked), milked);
									milkingStored.add("[style.colourMilk("+ Units.fluid(milked) +")] [npc.crotchMilk] stored.");
								}
							}
						}
						if(slave.getClothingInSlot(InventorySlot.PENIS)!=null
								&& slave.getClothingInSlot(InventorySlot.PENIS).isMilkingEquipment()) {
							int milked = MilkingRoom.getActualCumPerHour(slave);
							
							if(milked>0) {
								if(slave.hasSlaveJobSetting(currentJob, SlaveJobSetting.MILKING_CUM_AUTO_SELL)) {
									income += Math.max(1, (int) (milked * slave.getCum().getValuePerMl()));
									milkingSold.add("[style.colourCum("+ Units.fluid(milked) +")] [npc.cum] sold: +"+UtilText.formatAsMoney(income, "bold"));
								
								} else {
									room.incrementFluidStored(new FluidStored(slave, slave.getCum(), milked), milked);
									milkingStored.add("[style.colourCum("+ Units.fluid(milked) +")] [npc.cum] stored.");
								}
								slave.setLastTimeOrgasmedSeconds((((Main.game.getDayNumber()-1)*24)+hour)*60*60);
							}
						}
						if(slave.getClothingInSlot(InventorySlot.VAGINA)!=null
								&& slave.getClothingInSlot(InventorySlot.VAGINA).isMilkingEquipment()) {
							int milked = MilkingRoom.getActualGirlcumPerHour(slave);
							
							if(milked>0) {
								if(slave.hasSlaveJobSetting(currentJob, SlaveJobSetting.MILKING_GIRLCUM_AUTO_SELL)) {
									income += Math.max(1, (int) (milked * slave.getGirlcum().getValuePerMl()));
									milkingSold.add("[style.colourGirlCum("+ Units.fluid(milked) +")] [npc.girlcum] sold: +"+UtilText.formatAsMoney(income, "bold"));
								
								} else {
									room.incrementFluidStored(new FluidStored(slave.getId(), slave.getGirlcum(), milked), milked);
									milkingStored.add("[style.colourGirlCum("+ Units.fluid(milked) +")] [npc.girlcum] stored.");
								}
								slave.setLastTimeOrgasmedSeconds((((Main.game.getDayNumber()-1)*24)+hour)*60*60);
							}
						}
						generatedIncome += income;
						if(!milkingSold.isEmpty()) {
							events.add(new SlaveryEventLogEntry(hour, slave, null,
									SlaveEvent.JOB_MILK_MILKED,
									Util.newArrayListOfValues(
											SlaveEventTag.JOB_MILK_SOLD),
									milkingSold,
									true));
						}
						if(!milkingStored.isEmpty()) {
							events.add(new SlaveryEventLogEntry(hour, slave, null,
									SlaveEvent.JOB_MILK_MILKED,
									Util.newArrayListOfValues(
											SlaveEventTag.JOB_MILK_STORED),
									milkingStored,
									true));
						}
					}
					return events;
					
				case TEST_SUBJECT:
					if(slave.getSlaveJobSettings(currentJob).isEmpty()) {
						if(slave.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
							slave.incrementAffection(Main.game.getPlayer(), 1);
							slave.incrementAffection(Main.game.getNpc(Lilaya.class), 5);
							events.add(new SlaveryEventLogEntry(hour, slave, null,
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
							slave.incrementAffection(Main.game.getNpc(Lilaya.class), -5);
							events.add(new SlaveryEventLogEntry(hour, slave, null,
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
						switch(new ArrayList<>(slave.getSlaveJobSettings(currentJob)).get(Util.random.nextInt(slave.getSlaveJobSettings(currentJob).size()))) {
							case TEST_SUBJECT_ALLOW_TRANSFORMATIONS_FEMALE:
								List<String> list = new ArrayList<>();
								if(slave.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
									slave.incrementAffection(Main.game.getPlayer(), 1);
									slave.incrementAffection(Main.game.getNpc(Lilaya.class), 5);
									list.add("[style.boldGood(+1)] [style.boldAffection(Affection)]");
									list.add("[style.boldGood(+5)] [style.boldAffection(Affection towards Lilaya)]");
								} else {
									slave.incrementAffection(Main.game.getPlayer(), -1);
									slave.incrementAffection(Main.game.getNpc(Lilaya.class), -5);
									list.add("[style.boldBad(-1)] [style.boldAffection(Affection)]");
									list.add("[style.boldBad(-5)] [style.boldAffection(Affection towards Lilaya)]");
								}
								
								String tf = "";
								SlaveEventTag tag = SlaveEventTag.JOB_LILAYA_FEMININE_TF;
								if(slave.hasSlaveJobSetting(currentJob, SlaveJobSetting.TEST_SUBJECT_ALLOW_TRANSFORMATIONS_MALE)) {
									tf = getTestSubjectFutanariTransformation(slave);
									tag = SlaveEventTag.JOB_LILAYA_INTRUSIVE_TESTING;
								} else {
									tf = getTestSubjectFeminineTransformation(slave);
								}
								if(!tf.isEmpty()) {
									list.add(tf);
								}
								events.add(new SlaveryEventLogEntry(hour, slave, null,
										SlaveEvent.JOB_TEST_SUBJECT,
										Util.newArrayListOfValues(tag),
										list,
										true));
								return events;
								
							case TEST_SUBJECT_ALLOW_TRANSFORMATIONS_MALE:
								List<String> list2 = new ArrayList<>();
								if(slave.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
									slave.incrementAffection(Main.game.getPlayer(), 1);
									slave.incrementAffection(Main.game.getNpc(Lilaya.class), 5);
									list2.add("[style.boldGood(+1)] [style.boldAffection(Affection)]");
									list2.add("[style.boldGood(+5)] [style.boldAffection(Affection towards Lilaya)]");
								} else {
									slave.incrementAffection(Main.game.getPlayer(), -1);
									slave.incrementAffection(Main.game.getNpc(Lilaya.class), -5);
									list2.add("[style.boldBad(-1)] [style.boldAffection(Affection)]");
									list2.add("[style.boldBad(-5)] [style.boldAffection(Affection towards Lilaya)]");
								}
								
								String tf2 = "";
								SlaveEventTag mascTag = SlaveEventTag.JOB_LILAYA_MASCULINE_TF;
								if(slave.hasSlaveJobSetting(currentJob, SlaveJobSetting.TEST_SUBJECT_ALLOW_TRANSFORMATIONS_FEMALE)) {
									tf2 = getTestSubjectFutanariTransformation(slave);
									mascTag = SlaveEventTag.JOB_LILAYA_INTRUSIVE_TESTING;
								} else {
									tf2 = getTestSubjectMasculineTransformation(slave);
								}
								if(!tf2.isEmpty()) {
									list2.add(tf2);
								}
								events.add(new SlaveryEventLogEntry(hour, slave, null,
										SlaveEvent.JOB_TEST_SUBJECT,
										Util.newArrayListOfValues(mascTag),
										list2,
										true));
								return events;
								
							default:
								break;
						}
					}
					break;
					
				case PUBLIC_STOCKS:
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleySlavesFreed)) {
						effectDescriptions = new StringBuilder();
						effects = new ArrayList<>();
						settingsEnabled = getSexSettingsEnabled(currentJob, slave);
						
						Gender gender = Gender.getGenderFromUserPreferences(false, true);
						Map<AbstractSubspecies, Integer> availableRaces = AbstractSubspecies.getGenericSexPartnerSubspeciesMap(gender);
						
						AbstractSubspecies subspecies = Subspecies.HUMAN;
						AbstractSubspecies halfDemonSubspecies = null;
						if(!availableRaces.isEmpty()) {
							subspecies = Util.getRandomObjectFromWeightedMap(availableRaces);
						}
						String name;
						if(gender.isFeminine()) {
							name = subspecies.getSingularFemaleName(null);
						} else {
							name = subspecies.getSingularMaleName(null);
						}
						if(Math.random()<0.05f) {
							halfDemonSubspecies = subspecies;
							subspecies = Subspecies.HALF_DEMON;
							if(gender.isFeminine()) {
								name = halfDemonSubspecies.getHalfDemonName(null)[3];
							} else {
								name = halfDemonSubspecies.getHalfDemonName(null)[2];
							}
						}
						name = Util.capitaliseSentence(UtilText.generateSingularDeterminer(name)) +" "+ name;
						
						// If no settings are able to be used, or if a random roll is greater than 0.8, just add a groping event:
						if(settingsEnabled.isEmpty() || Math.random()>0.8f) {
								effectDescriptions.append(UtilText.parse(slave,
										UtilText.returnStringAtRandom(
												name+" groped and molested [npc.namePos] exposed body!",
												name+" roughly molested [npc.namePos] vulnerable body!",
												name+" spent some time groping and fondling every part of [npc.namePos] body!")));
	
							effects.add("<span style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>Molested:</span> "+effectDescriptions.toString());
							effectDescriptions.setLength(0);
							
						} else {
							SlaveJobSetting eventGenerated = settingsEnabled.get(Util.random.nextInt(settingsEnabled.size()));
							
							switch(eventGenerated) {
								case SEX_ANAL:
									effectDescriptions.append(UtilText.parse(slave,
											UtilText.returnStringAtRandom(
													name+" came deep inside [npc.namePos] [npc.asshole+]!",
													name+" fucked [npc.namePos] [npc.asshole+], before filling [npc.herHim] with [npc.cum+]!",
													name+" filled [npc.namePos] [npc.asshole+] with cum!")));
		
									effects.add("<span style='color:"+PresetColour.CUM.toWebHexString()+";'>Anal Creampie:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
									slave.calculateGenericSexEffects(false, true, null, subspecies, halfDemonSubspecies, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
									break;
									
								case SEX_ORAL:
									effectDescriptions.append(UtilText.parse(slave,
											UtilText.returnStringAtRandom(
													name+" came deep down [npc.namePos] throat!",
													name+" face-fucked [npc.name], before filling [npc.her] stomach with hot cum!",
													name+" filled [npc.namePos] stomach with cum!")));
		
									effects.add("<span style='color:"+PresetColour.CUM.toWebHexString()+";'>Swallowed Cum:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
	
									slave.calculateGenericSexEffects(false, true, null, subspecies, halfDemonSubspecies, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
									break;
									
								case SEX_NIPPLES:
									effectDescriptions.append(UtilText.parse(slave,
											UtilText.returnStringAtRandom(
													name+" came deep inside [npc.namePos] [npc.nipples+]!",
													name+" fucked [npc.namePos] [npc.nipples+], before filling [npc.her] [npc.breasts+] with hot cum!",
													name+" filled [npc.namePos] [npc.nipples+] with cum!")));
									
									effects.add("<span style='color:"+PresetColour.CUM.toWebHexString()+";'>Nipple Creampie:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
	
									slave.calculateGenericSexEffects(false, true, null, subspecies, halfDemonSubspecies, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS));
									break;
									
								case SEX_VAGINAL:
									effectDescriptions.append(UtilText.parse(slave,
											UtilText.returnStringAtRandom(
													name+" came deep inside [npc.namePos] [npc.pussy+]!",
													name+" fucked [npc.namePos] [npc.pussy+], before filling [npc.herHim] with [npc.cum+]!",
													name+" filled [npc.namePos] [npc.pussy+] with cum!")));
	
									slave.calculateGenericSexEffects(false, true, null, subspecies, halfDemonSubspecies, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
		
									if(slave.isVisiblyPregnant()) {
										effectDescriptions.append(UtilText.parse(slave, "but as [npc.sheIs] already pregnant, the only result is a fresh creampie..."));
										effects.add("<span style='color:"+PresetColour.CUM.toWebHexString()+";'>Pussy Creampie:</span> "+effectDescriptions.toString());
										effectDescriptions.setLength(0);
										
									} else if(!slave.getSlavePermissionSettings().get(SlavePermission.PREGNANCY).contains(SlavePermissionSetting.PILLS_PROMISCUITY_PILLS)) {
										effectDescriptions.append(UtilText.parse(slave, "resulting in a risk of pregnancy!"));
										effects.add("<span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Pregnancy Risk:</span> "+effectDescriptions.toString());
										effectDescriptions.setLength(0);
										
									} else {
										if(slave.isHasAnyPregnancyEffects()) {
											effectDescriptions.append(UtilText.parse(slave,
													"but as [npc.sheIs] on [#ITEM_innoxia_pills_sterility.getNamePlural(false)], there's no chance of [npc.herHim] getting pregnant."
													+ " ([npc.She] already has a risk of pregnancy from a previous encounter, however...)"));
										} else {
											effectDescriptions.append(UtilText.parse(slave, "but as [npc.sheIs] on [#ITEM_innoxia_pills_sterility.getNamePlural(false)], there's no chance of [npc.herHim] getting pregnant."));
										}
										effects.add("<span style='color:"+PresetColour.CUM.toWebHexString()+";'>Pussy Creampie:</span> "+effectDescriptions.toString());
										effectDescriptions.setLength(0);
									}
									
									break;
									
								default:
									break;
							
							}
						}
						
						events.add(new SlaveryEventLogEntry(hour, slave, null,
								SlaveEvent.JOB_PUBLIC_STOCKS,
								Util.newArrayListOfValues(
										SlaveEventTag.JOB_STOCKS_USED),
								effects,
								true));
					}
					
					return events;
					
				case PROSTITUTE:
					effectDescriptions = new StringBuilder();
					effects = new ArrayList<>();
					settingsEnabled = getSexSettingsEnabled(currentJob, slave);
					
					boolean usingRealPartner = true;
					if(hour!=Main.game.getHourOfDay()) {
						usingRealPartner = false;
					}
					
					GenericSexualPartner partner = null;
					String partnerName = "";
					
					Gender partnerGender = null;
					Map<AbstractSubspecies, Integer> availablePartnerRaces = null;
					AbstractSubspecies partnerSubspecies = Subspecies.HUMAN;
					AbstractSubspecies partnerHalfDemonSubspecies = null;
					
					if(usingRealPartner) {
						if(Math.random()<0.25f) {
							partner = new GenericSexualPartner(Gender.F_P_V_B_FUTANARI, slave.getWorldLocation(), slave.getLocation(), false);
						} else {
							partner = new GenericSexualPartner(Gender.M_P_MALE, slave.getWorldLocation(), slave.getLocation(), false);
						}
						try {
							partnerName = UtilText.parse(partner, "[npc.A_race]");
							Main.game.addNPC(partner, false, true);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					} else {
						partnerGender = Gender.getGenderFromUserPreferences(false, true);
						availablePartnerRaces = AbstractSubspecies.getGenericSexPartnerSubspeciesMap(partnerGender);
						
						if(!availablePartnerRaces.isEmpty()) {
							partnerSubspecies = Util.getRandomObjectFromWeightedMap(availablePartnerRaces);
						}
						if(partnerGender.isFeminine()) {
							partnerName = partnerSubspecies.getSingularFemaleName(null);
						} else {
							partnerName = partnerSubspecies.getSingularMaleName(null);
						}
						if(Math.random()<0.05f) {
							partnerHalfDemonSubspecies = partnerSubspecies;
							partnerSubspecies = Subspecies.HALF_DEMON;
							if(partnerGender.isFeminine()) {
								partnerName = partnerHalfDemonSubspecies.getHalfDemonName(null)[3];
							} else {
								partnerName = partnerHalfDemonSubspecies.getHalfDemonName(null)[2];
							}
						}
						partnerName = Util.capitaliseSentence(UtilText.generateSingularDeterminer(partnerName)) +" "+ partnerName;
					}
//					System.out.println("partner spawned for "+slave.getName()+": "+partner.getWorldLocation()+" "+partner.getLocation().getX()+", "+partner.getLocation().getY());
					
					// If no settings are able to be used, or if a random roll is less than 0.05, just add a groping event:
					if(settingsEnabled.isEmpty() || Math.random()<0.05f) {
						effectDescriptions.append(UtilText.parse(slave,
								UtilText.returnStringAtRandom(
										partnerName+" groped and molested [npc.namePos] exposed body!",
										partnerName+" molested [npc.namePos] vulnerable body!",
										partnerName+" spent some time groping and fondling every part of [npc.namePos] body!")));

						effects.add("<span style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>Molested:</span> "+effectDescriptions.toString());
						effectDescriptions.setLength(0);
						
					} else {
						SlaveJobSetting eventGenerated = settingsEnabled.get(Util.random.nextInt(settingsEnabled.size()));
						
						switch(eventGenerated) {
							case SEX_ANAL:
								if(usingRealPartner) {
									effectDescriptions.append(UtilText.parse(partner,
											UtilText.returnStringAtRandom(
													partnerName+" came deep inside "+UtilText.parse(slave, "[npc.namePos] [npc.asshole+]!"),
													partnerName+" roughly fucked "+UtilText.parse(slave, "[npc.namePos] [npc.asshole+], before filling [npc.herHim] with")+" [npc.cum+]!",
													partnerName+" filled "+UtilText.parse(slave, "[npc.namePos] [npc.asshole+]")+" with [npc.her] [npc.cum+]!")));
										
									effects.add("<span style='color:"+PresetColour.CUM.toWebHexString()+";'>Anal Creampie:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
									slave.calculateGenericSexEffects(false, true, partner, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
									
								} else {
									effectDescriptions.append(UtilText.parse(slave,
											UtilText.returnStringAtRandom(
													partnerName+" came deep inside [npc.namePos] [npc.asshole+]!",
													partnerName+" fucked [npc.namePos] [npc.asshole+], before filling [npc.herHim] with [npc.cum+]!",
													partnerName+" filled [npc.namePos] [npc.asshole+] with cum!")));
		
									effects.add("<span style='color:"+PresetColour.CUM.toWebHexString()+";'>Anal Creampie:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
									slave.calculateGenericSexEffects(false, true, null, partnerSubspecies, partnerHalfDemonSubspecies, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
								}
								break;
								
							case SEX_ORAL:
								if(usingRealPartner) {
									effectDescriptions.append(UtilText.parse(partner,
											UtilText.returnStringAtRandom(
													partnerName+" came deep down "+UtilText.parse(slave, "[npc.namePos] throat!"),
													partnerName+" roughly face-fucked "+UtilText.parse(slave, "[npc.name], before filling [npc.her] stomach with"+UtilText.parse(partner," [npc.cum+]!")),
													partnerName+" filled "+UtilText.parse(slave, "[npc.namePos] stomach")+UtilText.parse(partner," with [npc.her] [npc.cum+]!"))));
		
									effects.add("<span style='color:"+PresetColour.CUM.toWebHexString()+";'>Swallowed Cum:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
									slave.calculateGenericSexEffects(false, true, partner, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
									
								} else {
									effectDescriptions.append(UtilText.parse(slave,
											UtilText.returnStringAtRandom(
													partnerName+" came deep down [npc.namePos] throat!",
													partnerName+" face-fucked [npc.name], before filling [npc.her] stomach with hot cum!",
													partnerName+" filled [npc.namePos] stomach with cum!")));
		
									effects.add("<span style='color:"+PresetColour.CUM.toWebHexString()+";'>Swallowed Cum:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
									slave.calculateGenericSexEffects(false, true, null, partnerSubspecies, partnerHalfDemonSubspecies, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
								}
								break;
								
							case SEX_NIPPLES:
								if(usingRealPartner) {
									effectDescriptions.append(UtilText.parse(partner,
											UtilText.returnStringAtRandom(
													partnerName+" came deep inside "+UtilText.parse(slave, "[npc.namePos] [npc.nipples+]!"),
													partnerName+" roughly fucked "+UtilText.parse(slave, "[npc.namePos] [npc.nipples+], before filling [npc.her] [npc.breasts+] with"+UtilText.parse(partner," [npc.cum+]!")),
													partnerName+" filled "+UtilText.parse(slave, "[npc.namePos] [npc.nipples+]")+UtilText.parse(partner," with [npc.her] [npc.cum+]!"))));
										
									effects.add("<span style='color:"+PresetColour.CUM.toWebHexString()+";'>Nipple Creampie:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
									slave.calculateGenericSexEffects(false, true, partner, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS));
									
								} else {
									effectDescriptions.append(UtilText.parse(slave,
											UtilText.returnStringAtRandom(
													partnerName+" came deep inside [npc.namePos] [npc.nipples+]!",
													partnerName+" fucked [npc.namePos] [npc.nipples+], before filling [npc.her] [npc.breasts+] with hot cum!",
													partnerName+" filled [npc.namePos] [npc.nipples+] with cum!")));
									
									effects.add("<span style='color:"+PresetColour.CUM.toWebHexString()+";'>Nipple Creampie:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
									slave.calculateGenericSexEffects(false, true, null, partnerSubspecies, partnerHalfDemonSubspecies, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS));
								}
								break;
								
							case SEX_VAGINAL:
								if(usingRealPartner) {
									effectDescriptions.append(UtilText.parse(partner,
											UtilText.returnStringAtRandom(
													partnerName+" came deep inside "+UtilText.parse(slave, "[npc.namePos] [npc.pussy+], "),
													partnerName+" roughly fucked "+UtilText.parse(slave, "[npc.namePos] [npc.pussy+], "),
													partnerName+" filled "+UtilText.parse(slave, "[npc.namePos] [npc.pussy+]")+UtilText.parse(partner," with [npc.her] [npc.cum+], "))));
	
									slave.calculateGenericSexEffects(false, true, partner, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
									
									if(slave.isVisiblyPregnant()) {
										effectDescriptions.append(UtilText.parse(slave, "but as [npc.sheIs] already pregnant, the only result is a fresh creampie..."));
										effects.add("<span style='color:"+PresetColour.CUM.toWebHexString()+";'>Pussy Creampie:</span> "+effectDescriptions.toString());
										effectDescriptions.setLength(0);
										
									} else if(!slave.getSlavePermissionSettings().get(SlavePermission.PREGNANCY).contains(SlavePermissionSetting.PILLS_PROMISCUITY_PILLS)) {
										effectDescriptions.append(UtilText.parse(slave, "resulting in a risk of pregnancy!"));
										effects.add("<span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Pregnancy Risk:</span> "+effectDescriptions.toString());
										effectDescriptions.setLength(0);
										
									} else {
										if(slave.isHasAnyPregnancyEffects()) {
											effectDescriptions.append(UtilText.parse(slave, "but as [npc.sheIs] on [#ITEM_innoxia_pills_sterility.getNamePlural(false)], there's no chance of [npc.herHim] getting pregnant."
														+ " ([npc.She] already has a risk of pregnancy from a previous encounter, however...)"));
										} else {
											effectDescriptions.append(UtilText.parse(slave, "but as [npc.sheIs] on [#ITEM_innoxia_pills_sterility.getNamePlural(false)], there's no chance of [npc.herHim] getting pregnant."));
										}
										effects.add("<span style='color:"+PresetColour.CUM.toWebHexString()+";'>Pussy Creampie:</span> "+effectDescriptions.toString());
										effectDescriptions.setLength(0);
									}
								} else {
									effectDescriptions.append(UtilText.parse(slave,
											UtilText.returnStringAtRandom(
													partnerName+" came deep inside [npc.namePos] [npc.pussy+]!",
													partnerName+" fucked [npc.namePos] [npc.pussy+], before filling [npc.herHim] with [npc.cum+]!",
													partnerName+" filled [npc.namePos] [npc.pussy+] with cum!")));

									slave.calculateGenericSexEffects(false, true, null, partnerSubspecies, partnerHalfDemonSubspecies, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
		
									if(slave.isVisiblyPregnant()) {
										effectDescriptions.append(UtilText.parse(slave, "but as [npc.sheIs] already pregnant, the only result is a fresh creampie..."));
										effects.add("<span style='color:"+PresetColour.CUM.toWebHexString()+";'>Pussy Creampie:</span> "+effectDescriptions.toString());
										effectDescriptions.setLength(0);
										
									} else if(!slave.getSlavePermissionSettings().get(SlavePermission.PREGNANCY).contains(SlavePermissionSetting.PILLS_PROMISCUITY_PILLS)) {
										effectDescriptions.append(UtilText.parse(slave, "resulting in a risk of pregnancy!"));
										effects.add("<span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Pregnancy Risk:</span> "+effectDescriptions.toString());
										effectDescriptions.setLength(0);
										
									} else {
										if(slave.isHasAnyPregnancyEffects()) {
											effectDescriptions.append(UtilText.parse(slave,
													"but as [npc.sheIs] on [#ITEM_innoxia_pills_sterility.getNamePlural(false)], there's no chance of [npc.herHim] getting pregnant."
													+ " ([npc.She] already has a risk of pregnancy from a previous encounter, however...)"));
										} else {
											effectDescriptions.append(UtilText.parse(slave, "but as [npc.sheIs] on [#ITEM_innoxia_pills_sterility.getNamePlural(false)], there's no chance of [npc.herHim] getting pregnant."));
										}
										effects.add("<span style='color:"+PresetColour.CUM.toWebHexString()+";'>Pussy Creampie:</span> "+effectDescriptions.toString());
										effectDescriptions.setLength(0);
									}
								}
								break;
								
							default:
								break;
						
						}
					}

					events.add(new SlaveryEventLogEntry(hour, slave, null,
							SlaveEvent.JOB_PROSTITUTE,
							Util.newArrayListOfValues(
									SlaveEventTag.JOB_PROSTITUTE_USED),
							effects,
							true));
					return events;
					
					
				case IDLE:
					// This shouldn't be able to be reached.
					break;
			}
			
		} else { // Slave is resting:
			//TODO
//			events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_IDLE, true));
		}

		return events;
	}
	
	private List<SlaveJobSetting> getSexSettingsEnabled(SlaveJob currentJob, NPC slave) {
		List<SlaveJobSetting> settingsEnabled = new ArrayList<>();
		if(slave.hasVagina() && slave.hasSlaveJobSetting(currentJob, SlaveJobSetting.SEX_VAGINAL) && slave.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
			settingsEnabled.add(SlaveJobSetting.SEX_VAGINAL);
		}
		if(slave.hasSlaveJobSetting(currentJob, SlaveJobSetting.SEX_ANAL) && slave.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
			settingsEnabled.add(SlaveJobSetting.SEX_ANAL);
		}
		if(slave.hasSlaveJobSetting(currentJob, SlaveJobSetting.SEX_ORAL) && slave.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
			settingsEnabled.add(SlaveJobSetting.SEX_ORAL);
		}
		if(slave.isBreastFuckableNipplePenetration() && slave.hasSlaveJobSetting(currentJob, SlaveJobSetting.SEX_NIPPLES) && slave.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
			settingsEnabled.add(SlaveJobSetting.SEX_NIPPLES);
		}
		return settingsEnabled;
	}
	
	private String getTestSubjectFeminineTransformation(NPC slave) {
		List<TestSubjectTransformation> effects = new ArrayList<>(); 
		
		if(slave.hasPenis() || !slave.hasVagina()) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					List<String> effects = new ArrayList<>();
					if(slave.hasPenis()) {
						slave.setPenisType(PenisType.NONE);
						effects.add("[style.boldShrink(Lost penis)]");
						
					}
					if(!slave.hasVagina()) {
						slave.setVaginaType(RacialBody.valueOfRace(slave.getRace()).getVaginaType());
						effects.add(UtilText.parse(slave, "[style.boldGrow(Gained [npc.vaginaRace] vagina)]"));
					}
					return Util.stringsToStringList(effects, false);
				}
			});
		}
		
		if(slave.getFemininityValue()<100) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					int increment = Util.random.nextInt(5)+1;
					slave.incrementFemininity(increment);
					return "[style.boldGrow(+"+increment+")] [style.boldFeminine(Femininity)]";
				}
			});
		}
		
		if(slave.getBreastSize().getMeasurement() < CupSize.GG.getMeasurement()) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					int increment = Util.random.nextInt(1)+1;
					slave.incrementBreastSize(increment);
					return "[style.boldGrow(Gained "+Util.capitaliseSentence(slave.getBreastSize().getCupSizeName())+"-cup breasts)]";
				}
			});
		}
		
		if(slave.getHipSize().getValue()<HipSize.FOUR_WOMANLY.getValue()) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					int increment = Util.random.nextInt(1)+1;
					slave.incrementHipSize(increment);
					return UtilText.parse(slave, "[style.boldGrow(Hips expanded to '[npc.hipSize]')]");
				}
			});
		}
		
		if(slave.getAssSize().getValue()<AssSize.FOUR_LARGE.getValue()) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					int increment = Util.random.nextInt(1)+1;
					slave.incrementAssSize(increment);
					return UtilText.parse(slave, "[style.boldGrow(Ass expanded to '[npc.assSize]')]");
				}
			});
		}
		
		if(slave.getLipSize().getValue()<LipSize.THREE_PLUMP.getValue()) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					slave.incrementLipSize(1);
					return UtilText.parse(slave, "[style.boldGrow([npc.Lips] grown to '[npc.lipSize]')]");
				}
			});
		}
		
		if(!effects.isEmpty()) {
			TestSubjectTransformation tst = Util.randomItemFrom(effects);
			return tst.applyEffects();
		}
		
		return "";
	}
	
	private String getTestSubjectMasculineTransformation(NPC slave) {
		List<TestSubjectTransformation> effects = new ArrayList<>(); 
		
		if(!slave.hasPenis() || slave.hasVagina()) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					List<String> effects = new ArrayList<>();
					if(slave.hasVagina()) {
						slave.setVaginaType(VaginaType.NONE);
						effects.add("[style.boldShrink(Lost vagina)]");
						
					}
					if(!slave.hasPenis()) {
						slave.setPenisType(RacialBody.valueOfRace(slave.getRace()).getPenisType());
						effects.add(UtilText.parse(slave, "[style.boldGrow(Gained [npc.penisRace] penis)]"));
					}
					return Util.stringsToStringList(effects, false);
				}
			});
		}
		
		if(slave.getFemininityValue()>0) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					int increment = Util.random.nextInt(5)+1;
					slave.incrementFemininity(-increment);
					return "[style.boldShrink("+increment+")] [style.boldFeminine(Femininity)]";
				}
			});
		}
		
		if(slave.getBreastSize().getMeasurement() > 0) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					int increment = Util.random.nextInt(1)+1;
					slave.incrementBreastSize(-increment);
					return "[style.boldShrink(Shrunk breasts to "+Util.capitaliseSentence(slave.getBreastSize().getCupSizeName())+"-cup)]";
				}
			});
		}
		
		if(slave.getHipSize().getValue()>HipSize.TWO_NARROW.getValue()) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					int increment = Util.random.nextInt(1)+1;
					slave.incrementHipSize(-increment);
					return UtilText.parse(slave, "[style.boldShrink(Hips shrunk to '[npc.hipSize]')]");
				}
			});
		}
		
		if(slave.getAssSize().getValue()<AssSize.FOUR_LARGE.getValue()) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					int increment = Util.random.nextInt(1)+1;
					slave.incrementAssSize(-increment);
					return UtilText.parse(slave, "[style.boldShrink(Ass shrunk to '[npc.assSize]')]");
				}
			});
		}
		
		if(slave.getLipSize().getValue()>LipSize.ONE_AVERAGE.getValue()) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					slave.incrementLipSize(-1);
					return UtilText.parse(slave, "[style.boldGrow([npc.Lips] shrunk to '[npc.lipSize]')]");
				}
			});
		}
		
		if(!effects.isEmpty()) {
			TestSubjectTransformation tst = Util.randomItemFrom(effects);
			return tst.applyEffects();
		}
		
		return "";
	}
	
	private String getTestSubjectFutanariTransformation(NPC slave) {
		List<TestSubjectTransformation> effects = new ArrayList<>(); 
		
		if(!slave.hasPenis() || !slave.hasVagina()) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					List<String> effects = new ArrayList<>();
					if(!slave.hasPenis()) {
						slave.setPenisType(RacialBody.valueOfRace(slave.getRace()).getPenisType());
						effects.add("[style.boldGrow(Gained [npc.penisRace] penis)]");
						
					}
					if(!slave.hasVagina()) {
						slave.setVaginaType(RacialBody.valueOfRace(slave.getRace()).getVaginaType());
						effects.add(UtilText.parse(slave, "[style.boldGrow(Gained [npc.vaginaRace] vagina)]"));
					}
					return Util.stringsToStringList(effects, false);
				}
			});
		}
		
		if(slave.getFemininityValue()<100) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					int increment = Util.random.nextInt(5)+1;
					slave.incrementFemininity(increment);
					return "[style.boldGrow(+"+increment+")] [style.boldFeminine(Femininity)]";
				}
			});
		}
		
		if(slave.getBreastSize().getMeasurement() < CupSize.GG.getMeasurement()) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					int increment = Util.random.nextInt(1)+1;
					slave.incrementBreastSize(increment);
					return "[style.boldGrow(Gained "+Util.capitaliseSentence(slave.getBreastSize().getCupSizeName())+"-cup breasts)]";
				}
			});
		}
		
		if(slave.getHipSize().getValue()<HipSize.FOUR_WOMANLY.getValue()) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					int increment = Util.random.nextInt(1)+1;
					slave.incrementHipSize(increment);
					return UtilText.parse(slave, "[style.boldGrow(Hips expanded to '[npc.hipSize]')]");
				}
			});
		}
		
		if(slave.getAssSize().getValue()<AssSize.FOUR_LARGE.getValue()) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					int increment = Util.random.nextInt(1)+1;
					slave.incrementAssSize(increment);
					return UtilText.parse(slave, "[style.boldGrow(Ass expanded to '[npc.assSize]')]");
				}
			});
		}
		
		if(slave.getLipSize().getValue()<LipSize.THREE_PLUMP.getValue()) {
			effects.add(new TestSubjectTransformation() {
				@Override
				public String applyEffects() {
					slave.incrementLipSize(1);
					return UtilText.parse(slave, "[style.boldGrow([npc.Lips] grown to '[npc.lipSize]')]");
				}
			});
		}
		
		if(!effects.isEmpty()) {
			TestSubjectTransformation tst = Util.randomItemFrom(effects);
			return tst.applyEffects();
		}
		
		return "";
	}
	
	public static SlaveryEventLogEntry applySlaveSexWithOtherSlave(int day, int hourOfDay, NPC slave, NPC npc) {
		SlaveJob currentJob = slave.getSlaveJob(hourOfDay);
		
		List<String> descriptions = null;
		boolean canImpregnate = slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATE) && npc.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATED);
		boolean canBeImpregnated = slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATED) && npc.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATE);
		
		slave.setLastTimeHadSex((day*24*60l) + hourOfDay*60l, true);
		
		slave.generateSexChoices(false, npc);
		String sexDescription = UtilText.parse(slave, npc, "[npc.Name] spent some time kissing and groping [npc2.name].");
		if(slave.getMainSexPreference(npc)!=null) {
			SexType sexType = slave.getMainSexPreference(npc);
			if(sexType.getPerformingSexArea()==SexAreaPenetration.PENIS && sexType.getTargetedSexArea()==SexAreaOrifice.VAGINA && !canImpregnate) {
				sexDescription = slave.calculateGenericSexEffects(true, true, npc, sexType, GenericSexFlag.PREVENT_CREAMPIE);
				
			} else if(sexType.getPerformingSexArea()==SexAreaOrifice.VAGINA && sexType.getTargetedSexArea()==SexAreaPenetration.PENIS && !canBeImpregnated) {
				sexDescription = slave.calculateGenericSexEffects(true, true, npc, sexType, GenericSexFlag.PREVENT_CREAMPIE);
				
			} else {
				sexDescription = slave.calculateGenericSexEffects(true, true, npc, sexType);
			}
		}
		
		String paceName = slave.getTheoreticalSexPaceDomPreference().getName();
		if(paceName.equals("normal")) {
			paceName = "";
		}
		
		switch(currentJob) {
			case CLEANING:
				descriptions = Util.newArrayListOfValues(UtilText.parse(slave, npc,
								"While dusting one of the corridors, [npc1.name] caught sight of [npc2.name],"
								+ " and couldn't resist pulling [npc2.herHim] into an empty room for some "+paceName+" sex.")
								+ "<br/>[style.italicsSex("+sexDescription+")]");
				break;
			case SECURITY:
				descriptions = Util.newArrayListOfValues(UtilText.parse(slave, npc,
								"While patrolling one of the corridors, [npc1.name] caught sight of [npc2.name],"
								+ " and couldn't resist pulling [npc2.herHim] into an empty room for some "+paceName+" sex.")
								+ "<br/>[style.italicsSex("+sexDescription+")]");
				break;
			case IDLE:
				descriptions = Util.newArrayListOfValues(UtilText.parse(slave, npc,
								"[npc1.name] had some "+paceName+" fun with [npc2.name].")
								+ "<br/>[style.italicsSex("+sexDescription+")]");
				break;
			case KITCHEN:
				descriptions = Util.newArrayListOfValues(UtilText.parse(slave, npc,
								"While working in the kitchen, [npc1.name] saw [npc2.name] enter the pantry alone,"
										+ " and couldn't resist following [npc2.herHim] inside, before locking the door and having some "+paceName+" sex with [npc2.herHim].")
								+ "<br/>[style.italicsSex("+sexDescription+")]");
			case GARDEN:
				descriptions = Util.newArrayListOfValues(UtilText.parse(slave, npc,
								"[npc1.Name] pulled [npc2.name] behind one of the bushes in the garden, before having some "+paceName+" sex with [npc2.herHim].")
								+ "<br/>[style.italicsSex("+sexDescription+")]");
				break;
			case LAB_ASSISTANT: case TEST_SUBJECT:
				descriptions = Util.newArrayListOfValues(UtilText.parse(slave, npc,
								"When Lilaya left to take a break, [npc1.name] used the opportunity to have some "+paceName+" sex with [npc2.name] on one of the lab's tables.")
								+ "<br/>[style.italicsSex("+sexDescription+")]");
				break;
			case LIBRARY:
				descriptions = Util.newArrayListOfValues(UtilText.parse(slave, npc,
								"[npc1.Name] pulled [npc2.name] behind one of the shelves in the Library, before having some "+paceName+" sex with [npc2.herHim].")
								+ "<br/>[style.italicsSex("+sexDescription+")]");
				break;
			case OFFICE:
				descriptions = Util.newArrayListOfValues(UtilText.parse(slave, npc,
								"Taking a small break from the paperwork assigned to [npc.herHim],"
								+ " [npc.name] pushed [npc2.name] down over [npc.her] desk and had some "+paceName+" sex with [npc2.herHim].")
								+ "<br/>[style.italicsSex("+sexDescription+")]");
				break;
			case BEDROOM:
				descriptions = Util.newArrayListOfValues(UtilText.parse(slave, npc,
								"[npc1.Name] took advantage of being in your bedroom with [npc2.name], and had some "+paceName+" sex with [npc2.herHim].")
								+ "<br/>[style.italicsSex("+sexDescription+")]");
				break;
			case SPA:
				descriptions = Util.newArrayListOfValues(UtilText.parse(slave, npc,
								"[npc1.Name] took advantage of being in the spa with [npc2.name], and had some "+paceName+" sex with [npc2.herHim].")
								+ "<br/>[style.italicsSex("+sexDescription+")]");
				break;
			case SPA_RECEPTIONIST:
				descriptions = Util.newArrayListOfValues(UtilText.parse(slave, npc,
								"[npc1.Name] took advantage of being assigned to the spa's reception desk with [npc2.name], and had some "+paceName+" sex with [npc2.herHim].")
								+ "<br/>[style.italicsSex("+sexDescription+")]");
				break;
			case DINING_HALL:
				descriptions = Util.newArrayListOfValues(UtilText.parse(slave, npc,
								"[npc1.Name] pushed [npc2.name] over the dining hall's table, before having some "+paceName+" sex with [npc2.herHim].")
								+ "<br/>[style.italicsSex("+sexDescription+")]");
				break;
			case PUBLIC_STOCKS:
			case MILKING:
			case PROSTITUTE:
				break;
		}
		if(descriptions!=null) {
			if(npc.isAttractedTo(slave)) {
				descriptions.add(slave.incrementAffection(npc, 10));
				descriptions.add(npc.incrementAffection(slave, 10));
				
			} else if(npc.getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive()) {
				descriptions.add(slave.incrementAffection(npc, 10));
				descriptions.add(UtilText.parse(slave, npc, "Due to the fact that [npc2.name] enjoys being raped, [npc2.she] gained some affection towards [npc.name]!")
						+ "<br/>"+npc.incrementAffection(slave, 25));
				
			} else {
				descriptions.add(UtilText.parse(slave, npc, "[npc2.Name] despises [npc.name] for having raped [npc2.herHim]!")
						+ "<br/>"+npc.incrementAffection(slave, -200));
			}
			return new SlaveryEventLogEntry(hourOfDay,
					slave,
					Util.newArrayListOfValues(npc.getId()),
					SlaveEvent.SLAVE_SEX,
					null,
					descriptions,
					true);
		}
		return null;
	}
	
	/**
	 * 
	 * @param hour Pass in hour of the day
	 * @param slave
	 * @param otherSlavesPresent
	 * @return
	 */
	public static SlaveryEventLogEntry generateNPCInteractionEvent(int day, int hour, NPC slave, List<NPC> otherSlavesPresent) {
		SlaveJob currentJob = slave.getSlaveJob(hour);
		
		Collections.shuffle(otherSlavesPresent);
		for(NPC npc : otherSlavesPresent) {
			if(!npc.equals(slave)) {
				List<String> descriptions = null;
				if(slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE)) { // They only want sex once a day, to stop the logs from being flooded
					if(currentJob.hasFlag(SlaveJobFlag.INTERACTION_SEX)
							&& slave.isAttractedTo(npc)
							&& (npc.isAttractedTo(slave) || slave.isWillingToRape(npc))
							&& npc.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RECEIVE_SLAVES)
							&& slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_SLAVES)) {
						
						// Can reach each other:
						if(npc.getSlaveJob(hour)==SlaveJob.IDLE) {
							if(!Main.game.getCharactersPresent(slave.getCell()).contains(npc) && !slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_HOUSE_FREEDOM)) {
								continue;
							}
						}
						
						SlaveryEventLogEntry sexEvent = applySlaveSexWithOtherSlave(day, hour, slave, npc);
						if(sexEvent!=null) {
							return sexEvent;
						}
					}
				}
				
				if(currentJob.hasFlag(SlaveJobFlag.INTERACTION_BONDING)) {
					// Generic affection event:
					descriptions = new ArrayList<>();
					
					float chanceToBond = 0.2f;
					// Negative modifiers:
					if(slave.isShy()) {
						chanceToBond -= 0.075f;
					}
					if(npc.isShy()) {
						chanceToBond -= 0.075f;
					}
					// Positive modifiers:
					if(slave.isConfident()) {
						chanceToBond += 0.05f;
					}
					if(npc.isConfident()) {
						chanceToBond += 0.05f;
					}
					if(slave.isRelatedTo(npc)) {
						chanceToBond += 0.1f;
					}
					
					float chanceForPositiveOutcome = 1f;
					// Negative modifiers:
					if(slave.isSelfish()) {
						chanceForPositiveOutcome -= 0.5f;
					}
					if(npc.isSelfish()) {
						chanceForPositiveOutcome -= 0.5f;
					}
					// Positive modifiers:
					if(slave.isKind()) {
						chanceForPositiveOutcome += 0.5f;
					}
					if(npc.isKind()) {
						chanceForPositiveOutcome += 0.5f;
					}
					if(slave.isRelatedTo(npc)) {
						chanceForPositiveOutcome += 0.25f;
					}
					
					if(Math.random()<chanceToBond) {
						if(Math.random()<chanceForPositiveOutcome) {
							if(slave.getAffection(npc)<100 || npc.getAffection(slave)<100) {
								descriptions.add(UtilText.parse(slave, npc, "[npc.Name] and [npc2.name] spent some time getting to know one another a little better."));
								descriptions.add(slave.incrementAffection(npc, 5));
								descriptions.add(npc.incrementAffection(slave, 5));
							}
							
						} else {
							if(slave.getAffection(npc)>-100 || npc.getAffection(slave)>-100) {
								descriptions.add(UtilText.parse(slave, npc,"[npc.Name] and [npc2.name] spent some time arguing with one another."));
								descriptions.add(slave.incrementAffection(npc, -5));
								descriptions.add(npc.incrementAffection(slave, -5));
							}
						}
						if(!descriptions.isEmpty()) {
							return new SlaveryEventLogEntry(hour,
									slave,
									Util.newArrayListOfValues(npc.getId()),
									SlaveEvent.SLAVE_BONDING,
									null,
									descriptions,
									true);
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
	
	
	public MilkingRoom getMilkingRoom(AbstractWorldType worldType, Vector2i location) {
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
	
	public List<NPC> getCharactersResting() {
		return charactersResting;
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
				if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_GUEST_ROOM) && Main.game.getCharactersTreatingCellAsHome(c).isEmpty()) {
					return c;
				}
			}
		}
		
		return null;
	}

	public List<SlavePermissionSetting> getEnabledByDefaultPermissionSettings() {
		return enabledByDefaultPermissionSettings;
	}

	public void setEnabledByDefaultPermissionSettings(List<SlavePermissionSetting> enabledByDefaultPermissionSettings) {
		this.enabledByDefaultPermissionSettings = enabledByDefaultPermissionSettings;
	}

	public Map<SlaveJob, List<SlaveJobSetting>> getEnabledByDefaultJobSettings() {
		return enabledByDefaultJobSettings;
	}

	public void setEnabledByDefaultJobSettings(Map<SlaveJob, List<SlaveJobSetting>> enabledByDefaultJobSettings) {
		this.enabledByDefaultJobSettings = enabledByDefaultJobSettings;
	}

}
