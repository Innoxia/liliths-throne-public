package com.lilithsthrone.game.slavery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.SlaveryManagementDialogue;
import com.lilithsthrone.game.dialogue.eventLog.SlaveryEventLogEntry;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.87
 * @version 0.2.3
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
	
	public SlaveryUtil() {
		slavesAtJob = new HashMap<>();
		for(SlaveJob job : SlaveJob.values()) {
			slavesAtJob.put(job, new ArrayList<>());
		}
		
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
	
	public void performHourlyUpdate(int day, int hour) {
		clearSlavesJobTracking();
		
		// First need to set correct jobs:
		for(String id : Main.game.getPlayer().getSlavesOwned()) {
			NPC slave = (NPC) Main.game.getNPCById(id);
			if(Main.game.getPlayer().hasCompanion(slave)) {
				continue;
			}
			
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
		
		// Now can apply changes and generate events based on who else is present in the job:
		for(String id : Main.game.getPlayer().getSlavesOwned()) {
			NPC slave = (NPC) Main.game.getNPCById(id);
			if(Main.game.getPlayer().hasCompanion(slave)) {
				continue;
			}
			
			slave.incrementAffection(slave.getOwner(), slave.getHourlyAffectionChange(hour));
			slave.incrementObedience(slave.getHourlyObedienceChange(hour), false);
			
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
					slave.setLastTimeHadSex((day*24*60l) + hour*60l, true);
				}
			}
			
			// ***** EVENTS: ***** //
			
			// Washing body:
			if(slave.hasSlavePermissionSetting(SlavePermissionSetting.CLEANLINESS_WASH_BODY)
					&& !slave.getWorkHours()[hour]
					&& (slave.hasStatusEffect(StatusEffect.CREAMPIE_ANUS) || slave.hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || slave.hasStatusEffect(StatusEffect.CREAMPIE_NIPPLES))) {
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
						Util.newArrayListOfValues(new ListValue<>(SlaveEventTag.WASHED_CLOTHES)),
						true));
			}
			
			// Events:
			boolean eventAdded = false;
			SlaveryEventLogEntry entry = null;
			// Interaction events:
			if(slavesAtJob.get(slave.getSlaveJob()).size()>1) {
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
					entry = generateEvent(hour, slave);
					if(entry!=null) {
						Main.game.addSlaveryEvent(day, slave, entry);
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
				if(slaveDailyIncome.containsKey(slave)) {
					dailyEntry.addExtraEffect("[style.boldGood(Earned)] "+UtilText.formatAsMoney(slaveDailyIncome.get(slave)));
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
			slaveDailyIncome.clear();
			// Rooms:
			for(Cell c : SlaveryManagementDialogue.getImportantCells()) {
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

		StringBuilder effectDescriptions = new StringBuilder();
		List<String> effects = new ArrayList<>();
		List<SlaveJobSetting> settingsEnabled = new ArrayList<>();
		
		if(slave.getWorkHours()[hour] && job != SlaveJob.IDLE) { // Slave is working:
			switch (job) { //TODO
				case CLEANING:
					return new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_CLEANING, true);
				case KITCHEN:
					return new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_COOKING, true);
				case LAB_ASSISTANT:
					return new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_LAB_ASSISTANT, true);
				case LIBRARY:
					return new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_LIBRARIAN, true);
				case MILKING:
					int cumMilked = 25;
					int milkMilked = 500;
					int income = 0;
					if(slave.getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_ARTISAN_MILKERS)) {
						cumMilked = 15;
						milkMilked = 250;
					} else if(slave.getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS)) {
						cumMilked = 150;
						milkMilked = 1000;
					}
					
					if(slave.getBreastRawStoredMilkValue()>0) {
						int milked = Math.min(slave.getBreastRawStoredMilkValue(), milkMilked);
						slave.incrementBreastStoredMilk(-milkMilked);
						income = milked * (1 + slave.getMilk().getFluidModifiers().size());
						generatedIncome += income;
						
						return new SlaveryEventLogEntry(hour, slave,
								SlaveEvent.JOB_MILK_MILKED,
								Util.newArrayListOfValues(
										new ListValue<>(SlaveEventTag.JOB_LILAYA_MILK_SOLD)),
								Util.newArrayListOfValues(
										new ListValue<>("[style.boldGood("+milked+"ml)] milked: +"+UtilText.formatAsMoney(income, "bold"))),
								true);
						
					} else if(slave.hasPenis() && slave.getPenisRawCumProductionValue()>0) {
						int milked = Math.min(slave.getPenisRawCumProductionValue(), cumMilked);
						slave.incrementBreastStoredMilk(-cumMilked);
						income = milked * (1 + slave.getCum().getFluidModifiers().size());
						generatedIncome += income;
						
						return new SlaveryEventLogEntry(hour, slave,
								SlaveEvent.JOB_CUM_MILKED,
								Util.newArrayListOfValues(
										new ListValue<>(SlaveEventTag.JOB_LILAYA_CUM_SOLD)),
								Util.newArrayListOfValues(
										new ListValue<>("[style.boldGood("+milked+"ml)] milked: +"+UtilText.formatAsMoney(income, "bold"))),
								true);
					}
					break;
				case TEST_SUBJECT:
					if(slave.getSlaveJobSettings().isEmpty()) {
						if(slave.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
							slave.incrementAffection(Main.game.getPlayer(), 1);
							slave.incrementAffection(Main.game.getLilaya(), 5);
							return new SlaveryEventLogEntry(hour, slave,
									SlaveEvent.JOB_TEST_SUBJECT,
									Util.newArrayListOfValues(
											new ListValue<>(SlaveEventTag.JOB_LILAYA_INTRUSIVE_TESTING)),
									Util.newArrayListOfValues(
											new ListValue<>("[style.boldGood(+1)] [style.boldAffection(Affection)]"),
											new ListValue<>("[style.boldGood(+5)] [style.boldAffection(Affection towards Lilaya)]")),
									true);
							
						} else {
							slave.incrementAffection(Main.game.getPlayer(), -1);
							slave.incrementAffection(Main.game.getLilaya(), -5);
							return new SlaveryEventLogEntry(hour, slave,
									SlaveEvent.JOB_TEST_SUBJECT,
									Util.newArrayListOfValues(
											new ListValue<>(SlaveEventTag.JOB_LILAYA_INTRUSIVE_TESTING)),
									Util.newArrayListOfValues(
											new ListValue<>("[style.boldBad(-1)] [style.boldAffection(Affection)]"),
											new ListValue<>("[style.boldBad(-5)] [style.boldAffection(Affection towards Lilaya)]")),
									true);
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
								return new SlaveryEventLogEntry(hour, slave,
										SlaveEvent.JOB_TEST_SUBJECT,
										Util.newArrayListOfValues(
												new ListValue<>(SlaveEventTag.JOB_LILAYA_FEMININE_TF)),
										list,
										true);
								
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
								return new SlaveryEventLogEntry(hour, slave,
										SlaveEvent.JOB_TEST_SUBJECT,
										Util.newArrayListOfValues(
												new ListValue<>(SlaveEventTag.JOB_LILAYA_MASCULINE_TF)),
										list2,
										true);
								
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
											"[npc.A_race] groped and molested "+UtilText.parse(slave, "[npc.name]'s exposed body!"),
											"[npc.A_race] roughly molested "+UtilText.parse(slave, "[npc.name]'s vulnerable body!"),
											"[npc.A_race] spent some time groping and fondling every part of "+UtilText.parse(slave, "[npc.name]'s body!"))));

						effects.add("<span style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Molested:</span> "+effectDescriptions.toString());
						effectDescriptions.setLength(0);
						
					} else {
						SlaveJobSetting eventGenerated = settingsEnabled.get(Util.random.nextInt(settingsEnabled.size()));
						
						switch(eventGenerated) {
							case SEX_ANAL:
								effectDescriptions.append(UtilText.parse(stocksPartner,
										UtilText.returnStringAtRandom(
												"[npc.A_race] came deep inside "+UtilText.parse(slave, "[npc.name]'s [npc.asshole+]!"),
												"[npc.A_race] roughly fucked "+UtilText.parse(slave, "[npc.name]'s [npc.asshole+], before filling [npc.herHim] with"+UtilText.parse(Main.game.getGenericFemaleNPC()," [npc.cum+]!")),
												"[npc.A_race] filled "+UtilText.parse(slave, "[npc.name]'s [npc.asshole+]")+UtilText.parse(Main.game.getGenericFemaleNPC()," with [npc.her] [npc.cum+], "))));
	
								effects.add("<span style='color:"+Colour.CUMMED.toWebHexString()+";'>Anal Creampie:</span> "+effectDescriptions.toString());
								effectDescriptions.setLength(0);
								slave.calculateGenericSexEffects(false, stocksPartner, new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.ANUS), true);
								
								break;
								
							case SEX_ORAL:
								effectDescriptions.append(UtilText.parse(stocksPartner,
										UtilText.returnStringAtRandom(
												"[npc.A_race] came deep down "+UtilText.parse(slave, "[npc.name]'s throat!"),
												"[npc.A_race] roughly face-fucked "+UtilText.parse(slave, "[npc.name], before filling [npc.her] stomach with"+UtilText.parse(Main.game.getGenericFemaleNPC()," [npc.cum+]!")),
												"[npc.A_race] filled "+UtilText.parse(slave, "[npc.name]'s stomach")+UtilText.parse(Main.game.getGenericFemaleNPC()," with [npc.her] [npc.cum+]!"))));
	
								effects.add("<span style='color:"+Colour.CUMMED.toWebHexString()+";'>Swallowed Cum:</span> "+effectDescriptions.toString());
								effectDescriptions.setLength(0);

								slave.calculateGenericSexEffects(false, stocksPartner, new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.MOUTH), true);
								break;
								
							case SEX_NIPPLES:
								effectDescriptions.append(UtilText.parse(stocksPartner,
										UtilText.returnStringAtRandom(
												"[npc.A_race] came deep inside "+UtilText.parse(slave, "[npc.name]'s [npc.nipples+]!"),
												"[npc.A_race] roughly fucked "+UtilText.parse(slave, "[npc.name]'s [npc.nipples+], before filling [npc.her] [npc.breasts+] with"+UtilText.parse(Main.game.getGenericFemaleNPC()," [npc.cum+]!")),
												"[npc.A_race] filled "+UtilText.parse(slave, "[npc.name]'s [npc.nipples+]")+UtilText.parse(Main.game.getGenericFemaleNPC()," with [npc.her] [npc.cum+], "))));
	
								effects.add("<span style='color:"+Colour.CUMMED.toWebHexString()+";'>Nipple Creampie:</span> "+effectDescriptions.toString());
								effectDescriptions.setLength(0);

								slave.calculateGenericSexEffects(false, stocksPartner, new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.NIPPLE), true);
								break;
								
							case SEX_VAGINAL:
								effectDescriptions.append(UtilText.parse(stocksPartner,
										UtilText.returnStringAtRandom(
												"[npc.A_race] came deep inside "+UtilText.parse(slave, "[npc.name]'s [npc.pussy+], "),
												"[npc.A_race] roughly fucked "+UtilText.parse(slave, "[npc.name]'s [npc.pussy+], "),
												"[npc.A_race] filled "+UtilText.parse(slave, "[npc.name]'s [npc.pussy+]")+UtilText.parse(Main.game.getGenericFemaleNPC()," with [npc.her] [npc.cum+], "))));

								slave.calculateGenericSexEffects(false, stocksPartner, new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.VAGINA), !slave.getSlaveJobSettings().contains(SlaveJobSetting.SEX_PROMISCUITY_PILLS));
	
								if(slave.isVisiblyPregnant()) {
									effectDescriptions.append(UtilText.parse(slave, "but as [npc.she]'s already pregnant, the only result is a fresh creampie..."));
									effects.add("<span style='color:"+Colour.CUMMED.toWebHexString()+";'>Pussy Creampie:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
									
								} else if(!slave.getSlaveJobSettings().contains(SlaveJobSetting.SEX_PROMISCUITY_PILLS)) {
									effectDescriptions.append(UtilText.parse(slave, "resulting in a risk of pregnancy!"));
									effects.add("<span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Pregnancy Risk:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
									
								} else {
									if(slave.isHasAnyPregnancyEffects()) {
										effectDescriptions.append(UtilText.parse(slave, "but as [npc.she]'s on promiscuity pills, there's no chance of [npc.herHim] getting pregnant. ([npc.She] already has a risk of pregnancy from a previous encounter, however...)"));
									} else {
										effectDescriptions.append(UtilText.parse(slave, "but as [npc.she]'s on promiscuity pills, there's no chance of [npc.herHim] getting pregnant."));
									}
									effects.add("<span style='color:"+Colour.CUMMED.toWebHexString()+";'>Pussy Creampie:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
								}
								
								break;
								
							default:
								break;
						
						}
					}

					return new SlaveryEventLogEntry(hour, slave,
							SlaveEvent.JOB_PUBLIC_STOCKS,
							Util.newArrayListOfValues(
									new ListValue<>(SlaveEventTag.JOB_STOCKS_USED)),
							effects,
							true);
					
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
										"[npc.A_race] groped and molested "+UtilText.parse(slave, "[npc.name]'s exposed body!"),
										"[npc.A_race] roughly molested "+UtilText.parse(slave, "[npc.name]'s vulnerable body!"),
										"[npc.A_race] spent some time groping and fondling every part of "+UtilText.parse(slave, "[npc.name]'s body!"))));

						effects.add("<span style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Molested:</span> "+effectDescriptions.toString());
						effectDescriptions.setLength(0);
						
					} else {
						SlaveJobSetting eventGenerated = settingsEnabled.get(Util.random.nextInt(settingsEnabled.size()));
						
						switch(eventGenerated) {
							case SEX_ANAL:
								effectDescriptions.append(UtilText.parse(partner,
										UtilText.returnStringAtRandom(
												"[npc.A_race] came deep inside "+UtilText.parse(slave, "[npc.name]'s [npc.asshole+]!"),
												"[npc.A_race] roughly fucked "+UtilText.parse(slave, "[npc.name]'s [npc.asshole+], before filling [npc.herHim] with")+" [npc.cum+]!",
												"[npc.A_race] filled "+UtilText.parse(slave, "[npc.name]'s [npc.asshole+]")+" with [npc.her] [npc.cum+]!")));
									
								effects.add("<span style='color:"+Colour.CUMMED.toWebHexString()+";'>Anal Creampie:</span> "+effectDescriptions.toString());
								effectDescriptions.setLength(0);

								slave.calculateGenericSexEffects(false, partner, new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.ANUS), true);
								break;
								
							case SEX_ORAL:
								effectDescriptions.append(UtilText.parse(partner,
										UtilText.returnStringAtRandom(
												"[npc.A_race] came deep down "+UtilText.parse(slave, "[npc.name]'s throat!"),
												"[npc.A_race] roughly face-fucked "+UtilText.parse(slave, "[npc.name], before filling [npc.her] stomach with"+UtilText.parse(partner," [npc.cum+]!")),
												"[npc.A_race] filled "+UtilText.parse(slave, "[npc.name]'s stomach")+UtilText.parse(partner," with [npc.her] [npc.cum+]!"))));
	
								effects.add("<span style='color:"+Colour.CUMMED.toWebHexString()+";'>Swallowed Cum:</span> "+effectDescriptions.toString());
								effectDescriptions.setLength(0);

								slave.calculateGenericSexEffects(false, partner, new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.MOUTH), true);
								break;
								
							case SEX_NIPPLES:
								effectDescriptions.append(UtilText.parse(partner,
										UtilText.returnStringAtRandom(
												"[npc.A_race] came deep inside "+UtilText.parse(slave, "[npc.name]'s [npc.nipples+]!"),
												"[npc.A_race] roughly fucked "+UtilText.parse(slave, "[npc.name]'s [npc.nipples+], before filling [npc.her] [npc.breasts+] with"+UtilText.parse(partner," [npc.cum+]!")),
												"[npc.A_race] filled "+UtilText.parse(slave, "[npc.name]'s [npc.nipples+]")+UtilText.parse(partner," with [npc.her] [npc.cum+]!"))));
									
	
								effects.add("<span style='color:"+Colour.CUMMED.toWebHexString()+";'>Nipple Creampie:</span> "+effectDescriptions.toString());
								effectDescriptions.setLength(0);
								slave.calculateGenericSexEffects(false, partner, new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.NIPPLE), true);
								break;
								
							case SEX_VAGINAL:
								effectDescriptions.append(UtilText.parse(partner,
										UtilText.returnStringAtRandom(
												"[npc.A_race] came deep inside "+UtilText.parse(slave, "[npc.name]'s [npc.pussy+], "),
												"[npc.A_race] roughly fucked "+UtilText.parse(slave, "[npc.name]'s [npc.pussy+], "),
												"[npc.A_race] filled "+UtilText.parse(slave, "[npc.name]'s [npc.pussy+]")+UtilText.parse(partner," with [npc.her] [npc.cum+], "))));

								slave.calculateGenericSexEffects(false, partner, new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.VAGINA), !slave.getSlaveJobSettings().contains(SlaveJobSetting.SEX_PROMISCUITY_PILLS));
								
								if(slave.isVisiblyPregnant()) {
									effectDescriptions.append(UtilText.parse(slave, "but as [npc.she]'s already pregnant, the only result is a fresh creampie..."));
									effects.add("<span style='color:"+Colour.CUMMED.toWebHexString()+";'>Pussy Creampie:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
									
								} else if(!slave.getSlaveJobSettings().contains(SlaveJobSetting.SEX_PROMISCUITY_PILLS)) {
									effectDescriptions.append(UtilText.parse(slave, "resulting in a risk of pregnancy!"));
									effects.add("<span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Pregnancy Risk:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
									
								} else {
									if(slave.isHasAnyPregnancyEffects()) {
										effectDescriptions.append(UtilText.parse(slave, "but as [npc.she]'s on promiscuity pills, there's no chance of [npc.herHim] getting pregnant. ([npc.She] already has a risk of pregnancy from a previous encounter, however...)"));
									} else {
										effectDescriptions.append(UtilText.parse(slave, "but as [npc.she]'s on promiscuity pills, there's no chance of [npc.herHim] getting pregnant."));
									}
									effects.add("<span style='color:"+Colour.CUMMED.toWebHexString()+";'>Pussy Creampie:</span> "+effectDescriptions.toString());
									effectDescriptions.setLength(0);
								}
								
								break;
								
							default:
								break;
						
						}
					}

					return new SlaveryEventLogEntry(hour, slave,
							SlaveEvent.JOB_PROSTITUTE,
							Util.newArrayListOfValues(
									new ListValue<>(SlaveEventTag.JOB_PROSTITUTE_USED)),
							effects,
							true);
					
					
				case IDLE:
					// Can not reach :3
					break;
			}
			
		} else { // Slave is resting:
			return new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_IDLE, true);
		}
		
		return null;
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
//						System.out.println("x");
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
							npc.setVaginaVirgin(false);
							impregnationAttempt = true;
						}
						if(canBeImpregnated) {
							slave.rollForPregnancy(npc);
							if(npc.getPenisRawCumProductionValue()>0) {
								slave.addStatusEffect(StatusEffect.CREAMPIE_VAGINA, 240);
							}
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
										Util.newArrayListOfValues(new ListValue<>(UtilText.parse(slave, npc,
												"While dusting one of the first-floor corridors, [npc1.name] caught sight of [npc2.name],"
												+ " and couldn't resist pulling [npc2.herHim] into an empty room and giving [npc2.herHim] a "+slave.getSexPaceDomPreference().getName()+" fucking."
												+ (impregnationAttempt?"</br>[style.colourSex([npc2.Name] might have gotten pregnant!)]":"")
												+ (gettingPregnantAttempt?"</br>[style.colourSex([npc1.Name] might have gotten pregnant!)]":"")))),
										true);
								
							case IDLE: //TODO
								return new SlaveryEventLogEntry(hour,
										slave,
										SlaveEvent.SLAVE_SEX,
										null,
										Util.newArrayListOfValues(new ListValue<>(UtilText.parse(slave, npc,
												"[npc1.name] gave [npc2.name] a "+slave.getSexPaceDomPreference().getName()+" fucking."
												+ (impregnationAttempt?"</br>[style.colourSex([npc2.Name] might have gotten pregnant!)]":"")
												+ (gettingPregnantAttempt?"</br>[style.colourSex([npc1.Name] might have gotten pregnant!)]":"")))),
										true);
							case KITCHEN:
								return new SlaveryEventLogEntry(hour,
										slave,
										SlaveEvent.SLAVE_SEX,
										null,
										Util.newArrayListOfValues(new ListValue<>(UtilText.parse(slave, npc,
												"While working in the kitchen, [npc1.name] saw [npc2.name] enter the pantry alone,"
														+ " and couldn't resist following [npc2.herHim] inside, before locking the door and giving [npc2.herHim] a "+slave.getSexPaceDomPreference().getName()+" fucking."
												+ (impregnationAttempt?"</br>[style.colourSex([npc2.Name] might have gotten pregnant!)]":"")
												+ (gettingPregnantAttempt?"</br>[style.colourSex([npc1.Name] might have gotten pregnant!)]":"")))),
										true);
								
							case LAB_ASSISTANT: case TEST_SUBJECT:
								return new SlaveryEventLogEntry(hour,
										slave,
										SlaveEvent.SLAVE_SEX,
										null,
										Util.newArrayListOfValues(new ListValue<>(UtilText.parse(slave, npc,
												"When Lilaya left the lab to take a break, [npc1.name] used the opportunity to give [npc2.name] a "+slave.getSexPaceDomPreference().getName()+" fucking on one of the lab's tables."
												+ (impregnationAttempt?"</br>[style.colourSex([npc2.Name] might have gotten pregnant!)]":"")
												+ (gettingPregnantAttempt?"</br>[style.colourSex([npc1.Name] might have gotten pregnant!)]":"")))),
										true);
								
							case LIBRARY:
								return new SlaveryEventLogEntry(hour,
										slave,
										SlaveEvent.SLAVE_SEX,
										null,
										Util.newArrayListOfValues(new ListValue<>(UtilText.parse(slave, npc,
												"[npc1.Name] pulled [npc2.name] behind one of the shelves in the Library, before giving [npc2.herHim] a "+slave.getSexPaceDomPreference().getName()+" fucking."
												+ (impregnationAttempt?"</br>[style.colourSex([npc2.Name] might have gotten pregnant!)]":"")
												+ (gettingPregnantAttempt?"</br>[style.colourSex([npc1.Name] might have gotten pregnant!)]":"")))),
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
