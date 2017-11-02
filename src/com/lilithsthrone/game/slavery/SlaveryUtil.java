package com.lilithsthrone.game.slavery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.eventLog.SlaveryEventLogEntry;
import com.lilithsthrone.game.dialogue.utils.MiscDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.CoverableArea;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.world.Cell;

/**
 * @since 0.1.87
 * @version 0.1.87
 * @author Innoxia
 */
public class SlaveryUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Map<SlaveJob, List<NPC>> slavesAtJob;
	private List<NPC> slavesResting;
	private int generatedIncome;
	private int generatedUpkeep;
	
	// Slave income:
	private Map<NPC, Integer> slaveDailyIncome;
	
	// Slave Log:
	private Map<NPC, List<SlaveryEventLogEntry>> slaveryEventLog;
	private Map<Integer, List<SlaveryEventLogEntry>> slaveryEventLogDay;
	
	public SlaveryUtil() {
		slavesAtJob = new HashMap<>();
		for(SlaveJob job : SlaveJob.values()) {
			slavesAtJob.put(job, new ArrayList<>());
		}
		
		slaveryEventLog = new HashMap<>();
		slaveryEventLogDay = new HashMap<>();
		
		slavesResting = new ArrayList<>();
		generatedIncome = 0;
		generatedUpkeep = 0;
		
		slaveDailyIncome = new HashMap<>();
	}

	private void clearSlavesJobTracking() {
		for(SlaveJob job : SlaveJob.values()) {
			slavesAtJob.get(job).clear();
		}
		slavesResting.clear();
	}
	
	private StringBuilder newDayStringBuilder = new StringBuilder();
	private List<String> newDayEvents = new ArrayList<>();
	public void performHourlyUpdate(int day, int hour) {
		clearSlavesJobTracking();
		
		// First need to set correct jobs:
		for(NPC slave : Main.game.getPlayer().getSlavesOwned()) {
			if(slave.getWorkHours()[hour]) {
				slave.getSlaveJob().sendToWorkLocation(slave);
				slavesAtJob.get(slave.getSlaveJob()).add(slave);
			} else {
				slave.setLocation(slave.getHomeWorldLocation(), slave.getHomeLocation(), false);
				slavesResting.add(slave);
			}
		}
		
		// Now can apply changes and generate events based on who else is present in the job:
		for(NPC slave : Main.game.getPlayer().getSlavesOwned()) {
			
			slave.incrementAffection(slave.getOwner(), slave.getHourlyAffectionChange(hour));
			slave.incrementObedience(slave.getHourlyObedienceChange(hour));
			
			// If at work:
			if(slave.getWorkHours()[hour]) {
				// Get paid for hour's work:
				int income = slave.getSlaveJob().getFinalHourlyIncomeAfterModifiers(slave);
				generatedIncome += income;
				incrementSlaveDailyIncome(slave, income);
				// Overworked effect:
				if(slave.hasStatusEffect(StatusEffect.OVERWORKED)) {
					slave.incrementAffection(slave.getOwner(), -0.1f);
				}
				
			} else {
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_MASTURBATE)) {
					slave.setGetLastTimeHadSex((day*24*60l) + hour*60l);
				}
			}
			
			// ***** EVENTS: ***** //
			
			// Washing:
			if(slave.hasStatusEffect(StatusEffect.CLOTHING_CUM) && slave.hasSlavePermissionSetting(SlavePermissionSetting.CLEANLINESS_WASH_CLOTHES)) {
				slave.cleanAllClothing();
				for (AbstractClothing c : slave.getClothingCurrentlyEquipped()) {
					c.setDirty(false);
				}
				slave.calculateStatusEffects(0);
				addSlaveryEvent(day, slave, new SlaveryEventLogEntry(hour,
						slave,
						"Washed Clothes",
						UtilText.parse(slave, "[npc.Name] washed [npc.her] clothes."),
						Util.newArrayListOfValues(new ListValue<>("<span style='color:"+Colour.BASE_AQUA.toWebHexString()+";'>Clean Clothes</span>"))));
			}
			
			// Washing clothes:
			if(slave.hasSlavePermissionSetting(SlavePermissionSetting.CLEANLINESS_WASH_BODY)
					&& (slave.hasStatusEffect(StatusEffect.CREAMPIE_ANUS) || slave.hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || slave.hasStatusEffect(StatusEffect.CREAMPIE_NIPPLES) || slave.hasStatusEffect(StatusEffect.CREAMPIE_PENIS))) {
				List<String> cleanedParts = new ArrayList<>();
				
				if(slave.hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
					slave.removeStatusEffect(StatusEffect.CREAMPIE_ANUS);
					cleanedParts.add("<span style='color:"+Colour.BASE_AQUA.toWebHexString()+";'>Cleaned Anal Creampie</span>");
				}
				if(slave.hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
					slave.removeStatusEffect(StatusEffect.CREAMPIE_VAGINA);
					cleanedParts.add("<span style='color:"+Colour.BASE_AQUA.toWebHexString()+";'>Cleaned Pussy Creampie</span>");
				}
				if(slave.hasStatusEffect(StatusEffect.CREAMPIE_NIPPLES)) {
					slave.removeStatusEffect(StatusEffect.CREAMPIE_NIPPLES);
					cleanedParts.add("<span style='color:"+Colour.BASE_AQUA.toWebHexString()+";'>Cleaned Nipple Creampie</span>");
				}
				if(slave.hasStatusEffect(StatusEffect.CREAMPIE_PENIS)) {
					slave.removeStatusEffect(StatusEffect.CREAMPIE_PENIS);
					cleanedParts.add("<span style='color:"+Colour.BASE_AQUA.toWebHexString()+";'>Cleaned Urethra Creampie</span>");
				}
				
				addSlaveryEvent(day, slave, new SlaveryEventLogEntry(hour,
						slave,
						"Washed Body",
						UtilText.parse(slave, "[npc.Name] had a wash and cleaned [npc.her] body."),
						cleanedParts));
			}
			
			// Events:
			boolean eventAdded = false;
			SlaveryEventLogEntry entry = null;
			// Interaction events:
			if(slavesAtJob.get(slave.getSlaveJob()).size()>1) {
				if(Math.random()>0.5f) {
					entry = generateNPCInteractionEvent(day, hour, slave, slavesAtJob.get(slave.getSlaveJob()));
					if(entry!=null) {
						addSlaveryEvent(day, slave, entry);
						eventAdded = true;
					}
				}
			}
			// Standard events:
			if(!eventAdded) {
				if(Math.random()>0.9f) {
					entry = generateEvent(hour, slave);
					if(entry!=null) {
						addSlaveryEvent(day, slave, entry);
						eventAdded = true;
					}
				}
			}
			
			if(hour%24==0) { // At the start of a new day:
				newDayStringBuilder.setLength(0);
				newDayEvents.clear();
				
				// Payments:
				if(slaveDailyIncome.containsKey(slave)) {
					newDayStringBuilder.append("[npc.Name] was paid for yesterday's work!");
					newDayEvents.add("[style.boldGood(Earned)] "+UtilText.formatAsMoney(slaveDailyIncome.get(slave)));
				}
				
				// Muscle:
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_FORBIDDEN) && slave.getMuscleValue()>0) {
					slave.incrementMuscle(-5);
					newDayStringBuilder.append((newDayStringBuilder.length()>0?"</br>":"")+"[npc.Name] lost a lot of [npc.her] muscle!");
					newDayEvents.add("[style.boldShrink(-5)] [style.boldMuscleZero(Muscle Size)]");
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_REST) && slave.getMuscleValue()>0) {
					slave.incrementMuscle(-1);
					newDayStringBuilder.append((newDayStringBuilder.length()>0?"</br>":"")+"[npc.Name]'s muscles got a little smaller!");
					newDayEvents.add("[style.boldShrink(-1)] [style.boldMuscleOne(Muscle Size)]");
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_TRAINING) && slave.getMuscleValue()<100) {
					slave.incrementMuscle(1);
					newDayStringBuilder.append((newDayStringBuilder.length()>0?"</br>":"")+"[npc.Name]'s muscles got a little bigger!");
					newDayEvents.add("[style.boldGrow(+1)] [style.boldMuscleThree(Muscle Size)]");
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_BODY_BUILDING) && slave.getMuscleValue()<100) {
					slave.incrementMuscle(5);
					newDayStringBuilder.append((newDayStringBuilder.length()>0?"</br>":"")+"[npc.Name] gained a lot of muscle!");
					newDayEvents.add("[style.boldGrow(+5)] [style.boldMuscleFour(Muscle Size)]");
				}
				
				// Body size:
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_DIET_EXTREME) && slave.getBodySizeValue()>0) {
					slave.incrementBodySize(-5);
					newDayStringBuilder.append((newDayStringBuilder.length()>0?"</br>":"")+"[npc.Name] lost a lot of weight!");
					newDayEvents.add("[style.boldShrink(-5)] [style.boldBodySizeZero(Body Size)]");
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_DIET) && slave.getMuscleValue()>0) {
					slave.incrementBodySize(-1);
					newDayStringBuilder.append((newDayStringBuilder.length()>0?"</br>":"")+"[npc.Name] lost some weight!");
					newDayEvents.add("[style.boldShrink(-1)] [style.boldBodySizeOne(Body Size)]");
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_PLUS) && slave.getMuscleValue()<100) {
					slave.incrementBodySize(1);
					newDayStringBuilder.append((newDayStringBuilder.length()>0?"</br>":"")+"[npc.Name] gained some weight!");
					newDayEvents.add("[style.boldGrow(+1)] [style.boldBodySizeThree(Body Size)]");
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_LAVISH) && slave.getMuscleValue()<100) {
					slave.incrementBodySize(5);
					newDayStringBuilder.append((newDayStringBuilder.length()>0?"</br>":"")+"[npc.Name] put on a lot of weight!");
					newDayEvents.add("[style.boldGrow(+5)] [style.boldBodySizeFour(Body Size)]");
				}
				
				if(!newDayEvents.isEmpty()) {
					addSlaveryEvent(day, slave, new SlaveryEventLogEntry(hour,
							slave,
							"Daily Events",
							UtilText.parse(slave, newDayStringBuilder.toString()),
							new ArrayList<>(newDayEvents)));
				}
				
				slave.resetSlaveFlags();
			}
		}
		
		if(hour%24==0) { // Reset daily income tracking:
			slaveDailyIncome.clear();
			// Rooms:
			for(Cell c : MiscDialogue.importantCells) {
				generatedUpkeep += c.getPlace().getUpkeep();
			}
		}
		
	}

	/**
	 * @param minute Time at which this event is happening.
	 * @param slave The slave to calculate an event for.
	 */
	private SlaveryEventLogEntry generateEvent(int hour, NPC slave) {
		
		SlaveJob job = slave.getSlaveJob();
		
		if(slave.getWorkHours()[hour] && job != SlaveJob.IDLE) { // Slave is working:
			switch (job) { //TODO
				case CLEANING:
					return new SlaveryEventLogEntry(hour, slave, "Cleaning fun", "", Util.newArrayListOfValues(new ListValue<>("")));
				case KITCHEN:
					return new SlaveryEventLogEntry(hour, slave, "Cooking fun", "", Util.newArrayListOfValues(new ListValue<>("")));
				case LAB_ASSISTANT:
					return new SlaveryEventLogEntry(hour, slave, "Lilaya fun", "", Util.newArrayListOfValues(new ListValue<>("")));
				case LIBRARY:
					return new SlaveryEventLogEntry(hour, slave, "Book fun", "", Util.newArrayListOfValues(new ListValue<>("")));
				case TEST_SUBJECT:
					if(slave.getSlaveJobSettings().isEmpty()) {
						slave.incrementAffection(Main.game.getPlayer(), -1);
						slave.incrementAffection(Main.game.getLilaya(), -5);
						return new SlaveryEventLogEntry(hour, slave, "Testing", "Lilaya ran some rather intrusive tests on [npc.name].",
								Util.newArrayListOfValues(
										new ListValue<>("[style.boldBad(-1)] [style.boldAffection(Affection)]"),
										new ListValue<>("[style.boldBad(-5)] [style.boldAffection(Affection towards Lilaya)]")));
						
					} else {
						switch(slave.getSlaveJobSettings().get(Util.random.nextInt(slave.getSlaveJobSettings().size()))) {
							case TEST_SUBJECT_ALLOW_TRANSFORMATIONS_FEMALE:
								slave.incrementAffection(Main.game.getPlayer(), -1);
								slave.incrementAffection(Main.game.getLilaya(), -5);
								String tf = getTestSubjectFeminineTransformation(slave);
								List<String> list = Util.newArrayListOfValues(
										new ListValue<>("[style.boldBad(-1)] [style.boldAffection(Affection)]"),
										new ListValue<>("[style.boldBad(-5)] [style.boldAffection(Affection towards Lilaya)]"));
								if(!tf.isEmpty()) {
									list.add(tf);
								}
								return new SlaveryEventLogEntry(hour, slave, "Feminine Testing", "Lilaya tested some very intrusive feminine transformations on [npc.name].",list);
								
							case TEST_SUBJECT_ALLOW_TRANSFORMATIONS_MALE:
								slave.incrementAffection(Main.game.getPlayer(), -1);
								slave.incrementAffection(Main.game.getLilaya(), -5);
								String tf2 = getTestSubjectMasculineTransformation(slave);
								List<String> list2 = Util.newArrayListOfValues(
										new ListValue<>("[style.boldBad(-1)] [style.boldAffection(Affection)]"),
										new ListValue<>("[style.boldBad(-5)] [style.boldAffection(Affection towards Lilaya)]"));
								if(!tf2.isEmpty()) {
									list2.add(tf2);
								}
								return new SlaveryEventLogEntry(hour, slave, "Masculine Testing", "Lilaya tested some very intrusive masculine transformations on [npc.name].",list2);
								
							default:
								break;
						}
					}
					break;
				case IDLE:
					// Can not reach :3
					break;
			}
			
		} else { // Slave is resting:
			return new SlaveryEventLogEntry(hour, slave, "Resting", "", Util.newArrayListOfValues(new ListValue<>("")));
		}
		
		return null;
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
			slave.incrementBreastSize(increment);
			return "[style.boldShrink(Gained "+Util.capitaliseSentence(slave.getBreastSize().getCupSizeName())+"-cup breasts)]";
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
		
		Collections.shuffle(otherSlavesPresent);
		for(NPC npc : otherSlavesPresent) {
			if(!npc.equals(slave)) {
				if(slave.getGetLastTimeHadSex()+24*60<Main.game.getMinutesPassed()) { // They only want sex once a day, to stop the logs from being flooded
					if(slave.isAttractedTo(npc) && npc.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RECEIVE_SLAVES) && slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_SLAVES)) {
						System.out.println("x");
						boolean canImpregnate = slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATE) && npc.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATED)
								&& slave.hasPenis() && slave.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
								&& npc.hasVagina() && npc.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
						boolean canBeImpregnated = slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATED) && npc.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATE)
								&& npc.hasPenis() && npc.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
								&& slave.hasVagina() && slave.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
						
						boolean impregnationAttempt = false, gettingPregnantAttempt = false;
						
						// Apply sex effects:
						if(canImpregnate) {
							npc.rollForPregnancy(slave);
							if(slave.getPenisRawCumProductionValue()>0) {
								npc.addStatusEffect(StatusEffect.CREAMPIE_VAGINA, 240);
							}
							impregnationAttempt = true;
						}
						if(canBeImpregnated) {
							slave.rollForPregnancy(npc);
							if(npc.getPenisRawCumProductionValue()>0) {
								slave.addStatusEffect(StatusEffect.CREAMPIE_VAGINA, 240);
							}
							gettingPregnantAttempt = true;
						}
						slave.setGetLastTimeHadSex((day*24*60l) + hour*60l);
						
						
						switch(slave.getSlaveJob()) {
							case CLEANING:
								return new SlaveryEventLogEntry(hour,
										slave,
										"[style.boldSex(Sex)]",
										UtilText.parse(npc,
												"While dusting one of the first-floor corridors, "+slave.getName()+" caught sight of [npc.name],"
														+ " and couldn't resist pulling [npc.herHim] into an empty room and giving [npc.herHim] a "+slave.getSexPaceDomPreference().getName()+" fucking."),
														Util.newArrayListOfValues(
																new ListValue<>(impregnationAttempt?UtilText.parse(npc,"[style.colourSex([npc.Name] might have gotten pregnant!)]"):""),
																new ListValue<>(gettingPregnantAttempt?"[style.colourSex("+slave.getName()+" might have gotten pregnant!)]":"")));
								
							case IDLE: //TODO
								return new SlaveryEventLogEntry(hour,
										slave,
										"[style.boldSex(Sex)]",
										UtilText.parse(npc,
												slave.getName()+" gave [npc.name] a "+slave.getSexPaceDomPreference().getName()+" fucking."),
										Util.newArrayListOfValues(
												new ListValue<>(impregnationAttempt?UtilText.parse(npc,"[style.colourSex([npc.Name] might have gotten pregnant!)]"):""),
												new ListValue<>(gettingPregnantAttempt?"[style.colourSex("+slave.getName()+" might have gotten pregnant!)]":"")));
							case KITCHEN:
								return new SlaveryEventLogEntry(hour,
										slave,
										"[style.boldSex(Sex)]",
										UtilText.parse(npc,
												"While working in the kitchen, "+slave.getName()+" saw [npc.name] enter the pantry alone,"
														+ " and couldn't resist following [npc.herHim] inside, before locking the door and giving [npc.herHim] a "+slave.getSexPaceDomPreference().getName()+" fucking."),
										Util.newArrayListOfValues(
												new ListValue<>(impregnationAttempt?UtilText.parse(npc,"[style.colourSex([npc.Name] might have gotten pregnant!)]"):""),
												new ListValue<>(gettingPregnantAttempt?"[style.colourSex("+slave.getName()+" might have gotten pregnant!)]":"")));
								
							case LAB_ASSISTANT: case TEST_SUBJECT:
								return new SlaveryEventLogEntry(hour,
										slave,
										"[style.boldSex(Sex)]",
										UtilText.parse(npc,
												"When Lilaya went to her room to take a break, "+slave.getName()+" used the opportunity to give [npc.name] a "+slave.getSexPaceDomPreference().getName()+" fucking on one of the lab's tables."),
										Util.newArrayListOfValues(
												new ListValue<>(impregnationAttempt?UtilText.parse(npc,"[style.colourSex([npc.Name] might have gotten pregnant!)]"):""),
												new ListValue<>(gettingPregnantAttempt?"[style.colourSex("+slave.getName()+" might have gotten pregnant!)]":"")));
								
							case LIBRARY:
								return new SlaveryEventLogEntry(hour,
										slave,
										"[style.boldSex(Sex)]",
										UtilText.parse(npc,
												slave.getName()+" pulled [npc.name] behind one of the shelves in the Library, before giving [npc.herHim] a "+slave.getSexPaceDomPreference().getName()+" fucking."),
										Util.newArrayListOfValues(
												new ListValue<>(impregnationAttempt?UtilText.parse(npc,"[style.colourSex([npc.Name] might have gotten pregnant!)]"):""),
												new ListValue<>(gettingPregnantAttempt?"[style.colourSex("+slave.getName()+" might have gotten pregnant!)]":"")));
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
	
	
	public Map<NPC, List<SlaveryEventLogEntry>> getSlaveryEventLog() {
		return slaveryEventLog;
	}
	
	public Map<Integer, List<SlaveryEventLogEntry>> getSlaveryEventLogDay() {
		return slaveryEventLogDay;
	}
	
	public void addSlaveryEvent(int day, NPC slave, SlaveryEventLogEntry event) {
		slaveryEventLog.putIfAbsent(slave, new ArrayList<>());
		slaveryEventLogDay.putIfAbsent(day, new ArrayList<>());
		
		slaveryEventLog.get(slave).add(event);
		slaveryEventLogDay.get(day).add(event);
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
	
	public int getGeneratedUpkeep() {
		return generatedUpkeep;
	}
	
	private void incrementSlaveDailyIncome(NPC slave, int increment) {
		slaveDailyIncome.putIfAbsent(slave, 0);
		slaveDailyIncome.put(slave, slaveDailyIncome.get(slave)+increment);
	}

	public Map<SlaveJob, List<NPC>> getSlavesAtJob() {
		return slavesAtJob;
	}

	public List<NPC> getSlavesResting() {
		return slavesResting;
	}
	
}
