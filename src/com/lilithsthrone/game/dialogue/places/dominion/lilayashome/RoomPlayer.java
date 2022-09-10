package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevelBasic;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevelBasic;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantDialogue;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.nightlife.NightlifeDistrict;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.MiscDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.managers.universal.SMBath;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMShower;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.time.DateAndTime;
import com.lilithsthrone.utils.time.SolarElevationAngle;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.75
 * @version 0.3.5.5
 * @author Innoxia
 */
public class RoomPlayer {
	
	private static int sleepTimeInMinutes = 240;

    private static GameCharacter makeupTarget;
    
    private static List<GameCharacter> slavesWashing;
    
    public static GameCharacter getMakeupTarget() {
        if(makeupTarget==null) {
            return Main.game.getPlayer();
        }
        return makeupTarget;
    }
    
    /**
     * @param sleepTimeInMinutes Calls an endTurn(sleepTimeInMinutes*60) so that NPCs have their status effects updated before the next scene is parsed.
     */
	public static void applySleep(int sleepTimeInMinutes) {
		List<GameCharacter> charactersPresent = new ArrayList<>(LilayaHomeGeneric.getSlavesAndOccupantsPresent());
		charactersPresent.addAll(Main.game.getPlayer().getCompanions());
		charactersPresent.add(Main.game.getPlayer());

		for(GameCharacter character : charactersPresent) {
			character.applySleep(sleepTimeInMinutes);
		}

		Main.game.getPlayer().setActive(false);
		Main.game.endTurn(sleepTimeInMinutes*60);
		Main.game.getPlayer().setActive(true);
	}
	
	private static Response getResponseRoom(int responseTab, int index) {
		if(responseTab==1) {
			return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			
		} else if(responseTab==0) {
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				return new Response("Rest (1 hour)",
						"Rest for an hour. As well as replenishing your "+Attribute.HEALTH_MAXIMUM.getName()+" and "+Attribute.MANA_MAXIMUM.getName()+", you will also get the 'Well Rested' status effect.",
						AUNT_HOME_PLAYERS_ROOM_SLEEP){
					@Override
					public void effects() {
						sleepTimeInMinutes = 60;
						applySleep(sleepTimeInMinutes);
					}
				};

			} else if (index == 2) {
				return new Response("Rest (4 hours)",
						"Rest for four hours. As well as replenishing your "+Attribute.HEALTH_MAXIMUM.getName()+" and "+Attribute.MANA_MAXIMUM.getName()+", you will also get the 'Well Rested' status effect.",
						AUNT_HOME_PLAYERS_ROOM_SLEEP){
					@Override
					public void effects() {
						sleepTimeInMinutes = 60 * 4;
						applySleep(sleepTimeInMinutes);
					}
				};

            } else if (index == 3) {
                return new Response("Rest (8 hours)",
                        "Rest for eight hours. As well as replenishing your "+Attribute.HEALTH_MAXIMUM.getName()+" and "+Attribute.MANA_MAXIMUM.getName()+", you will also get the 'Well Rested' status effect.",
                        AUNT_HOME_PLAYERS_ROOM_SLEEP){
                    @Override
                    public void effects() {
                        sleepTimeInMinutes = 60 * 8;
                        applySleep(sleepTimeInMinutes);
                    }
                };

            } else if (index == 4) {
				return new Response("Rest (12 hours)",
						"Rest for twelve hours. As well as replenishing your "+Attribute.HEALTH_MAXIMUM.getName()+" and "+Attribute.MANA_MAXIMUM.getName()+", you will also get the 'Well Rested' status effect.",
						AUNT_HOME_PLAYERS_ROOM_SLEEP){
					@Override
					public void effects() {
						sleepTimeInMinutes = 60 * 12;
						applySleep(sleepTimeInMinutes);
					}
				};
	
			} else if (index == 5) {
				int timeUntilChange = Main.game.getMinutesUntilNextMorningOrEvening() + 5; // Add 5 minutes so that if the days are drawing in, you don't get stuck in a loop of always sleeping to sunset/sunrise
				LocalDateTime[] sunriseSunset = DateAndTime.getTimeOfSolarElevationChange(Main.game.getDateNow(), SolarElevationAngle.SUN_ALTITUDE_SUNRISE_SUNSET, Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
				return new Response("Rest until " + (Main.game.isDayTime() ? "Sunset" : "Sunrise"),
						"Rest for " + (timeUntilChange >= 60 ?timeUntilChange / 60 + " hours " : " ")
							+ (timeUntilChange % 60 != 0 ? timeUntilChange % 60 + " minutes" : "")
							+ (Main.game.isDayTime()
									? " until five minutes past sunset ("+Units.time(sunriseSunset[1].plusMinutes(5))+")."
									: " until five minutes past sunrise ("+Units.time(sunriseSunset[0].plusMinutes(5))+").")
							+ " As well as replenishing your "+Attribute.HEALTH_MAXIMUM.getName()+" and "+Attribute.MANA_MAXIMUM.getName()+", you will also get the 'Well Rested' status effect.",
							AUNT_HOME_PLAYERS_ROOM_SLEEP){
					@Override
					public void effects() {
						sleepTimeInMinutes = timeUntilChange;
						applySleep(sleepTimeInMinutes);
					}
				};
				
			} else if (index == 6) {
				return new Response("Manage room", "Enter the management screen for this particular room.", OccupantManagementDialogue.ROOM_UPGRADES) {
					@Override
					public void effects() {
						OccupantManagementDialogue.cellToInspect = Main.game.getPlayerCell();
					}
				};
				
			}  else if (index == 7) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Manage people", "Enter the management screen for your slaves and friendly occupants.", ROOM) {
						@Override
						public DialogueNode getNextDialogue() {
							return OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null);
						}
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
						}
					};
				} else {
					return new Response("Manage people", "You'll either need a slaver license, or permission from Lilaya to house your friends, before you can access this menu!",  null);
				}
				
			} else if (index == 8) {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.knowsDate)) {
					return new Response("Calendar", "Take another look at the enchanted calendar that's pinned up on one wall.", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
				} else {
					return new Response("<span style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>Calendar</span>", "There's a calendar pinned up on one wall. Take a closer look at it.", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
				}
				
			} else if (index == 9) {
				return new Response("Set alarm", "Set the alarm on your phone, so that you can wake at a specific time.", RoomPlayer.ROOM_SET_ALARM) {
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
					}
				};

			} else if (index == 10) {
				long alarmTime = Main.game.getDialogueFlags().getSavedLong("player_phone_alarm");
				if(alarmTime >= 0) {
					String alarmTimeStr = Main.game.getDisplayTime(LocalTime.ofSecondOfDay(alarmTime*60));
					int timeUntilAlarm = Main.game.getMinutesUntilTimeInMinutes((int)alarmTime-1)+1; // -1+1 is so we get 1440 instead of 0
					return new Response("Rest until alarm (" + alarmTimeStr + ")",
							"Rest for " + (timeUntilAlarm >= 60 ? timeUntilAlarm / 60 + " hours, " : "")
									+ (timeUntilAlarm % 60 != 0 ? timeUntilAlarm % 60 + " minutes, " : "")
									+ "until your alarm goes off. As well as replenishing your " + Attribute.HEALTH_MAXIMUM.getName() + " and " + Attribute.MANA_MAXIMUM.getName() + ", you will also get the 'Well Rested' status effect.",
							AUNT_HOME_PLAYERS_ROOM_SLEEP) {
						@Override
						public void effects() {
							sleepTimeInMinutes = timeUntilAlarm;
							RoomPlayer.applySleep(sleepTimeInMinutes);
						}
					};
				} else {
					return new Response("Rest until alarm (unset)", "<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Your alarm is unset!</span>", null);
				}
			}
			
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			
			int indexPresentStart = 11;
			if(index-indexPresentStart<charactersPresent.size() && index-indexPresentStart>=0) {
				NPC character = charactersPresent.get(index-indexPresentStart);
				return new Response(
						UtilText.parse(character, "[npc.Name]"),
						UtilText.parse(character, "Interact with [npc.name]."),
						character.isSlave()
							?SlaveDialogue.SLAVE_START
							:OccupantDialogue.OCCUPANT_START) {
					@Override
					public Colour getHighlightColour() {
						return character.getFemininity().getColour();
					}
					@Override
					public void effects() {
						if(character.isSlave()) {
							SlaveDialogue.initDialogue(character, false);
						} else {
							OccupantDialogue.initDialogue(character, false, false);
						}
					}
				};
			}
			
		} else if(responseTab==2) {
			if (index == 1) {
				return new Response("Quick shower",
						"Use your room's ensuite to take a quick shower."
								+ "<br/>[style.italicsGood(Cleans <b>a maximum of "+Units.fluid(500)+"</b> of fluids from all orifices.)]"
								+ "<br/>[style.italicsGood(This will clean <b>only</b> your currently equipped clothing.)]",
//								+ "<br/>[style.italicsMinorBad(This does <b>not</b> clean companions.)]",
						AUNT_HOME_PLAYERS_ROOM_QUICK_SHOWER){
					@Override
					public void effects() {
						List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
						slavesWashing = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_HELP_WASH)).collect(Collectors.toList());
						for(GameCharacter npc : slavesWashing) {
							npc.applyWash(true, true, StatusEffect.CLEANED_SHOWER, 120+30);
						}

						Main.game.getTextEndStringBuilder().append("<p style='text-align:center'><i>You leave your clothes outside of your bathroom so that they can be cleaned while you wash yourself...</i></p>");
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(false, false, null, 240+30));
					}
					@Override
					public int getSecondsPassed() {
						return 10*60;
					}
				};
				
			} else if (index == 2) {
				return new Response("Thorough shower",
						"Use your room's en-suite to take a shower, and spend some time thoroughly cleaning yourself."
								+ "<br/>[style.italicsExcellent(This will clean <b>all</b> fluids out of all your orifices.)]"
								+ "<br/>[style.italicsGood(This will clean <b>only</b> your currently equipped clothing.)]",
//								+ "<br/>[style.italicsMinorBad(This does <b>not</b> clean companions.)]",
						AUNT_HOME_PLAYERS_ROOM_THOROUGH_SHOWER){
					@Override
					public void effects() {
						List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
						slavesWashing = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_HELP_WASH)).collect(Collectors.toList());
						for(GameCharacter npc : slavesWashing) {
							npc.applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
						}
						
						Main.game.getTextEndStringBuilder().append("<p style='text-align:center'><i>You leave your clothes outside of your bathroom so that they can be cleaned while you wash yourself...</i></p>");
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, false, StatusEffect.CLEANED_SHOWER, 240+30));
					}
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
				};
				
			} else if(index==3) {
				return new Response("Bath time",
						"Use your room's en-suite to take a bath, and spend some time thoroughly cleaning yourself."
								+ "<br/>[style.italicsExcellent(This will clean <b>all</b> fluids out of all your orifices.)]"
								+ "<br/>[style.italicsExcellent(This will clean <b>all</b> clothing in your inventory.)]",
//								+ "<br/>[style.italicsMinorGood(This <b>does</b> clean companions.)]",
						AUNT_HOME_PLAYERS_ROOM_BATH){
					@Override
					public void effects() {
						List<GameCharacter> charactersPresent = new ArrayList<>(LilayaHomeGeneric.getSlavesAndOccupantsPresent());
						slavesWashing = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_HELP_WASH)).collect(Collectors.toList());
						for(GameCharacter npc : slavesWashing) {
							npc.applyWash(true, true, StatusEffect.CLEANED_BATH, 240+30);
						}
						
						Main.game.getTextEndStringBuilder().append("<p style='text-align:center'><i>You leave your clothes outside of your bathroom so that they can be cleaned while you wash yourself...</i></p>");
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_BATH, 240+30));
					}
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
				};
				
			} else if(index==11) {
				return new ResponseEffectsOnly(
						UtilText.parse(getMakeupTarget(), "Target: <b style='color:"+getMakeupTarget().getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
						"Cycle the targeted character for applying makeup to.") {
					@Override
					public void effects() {
						List<GameCharacter> companions = Util.newArrayListOfValues(Main.game.getPlayer());
						companions.addAll(Main.game.getCharactersPresent());
//						companions.removeIf((c) -> !c.isPlayer() && (!c.isSlave() || !c.getOwner().isPlayer()));
						if(!companions.isEmpty()) {
							for(int i=0; i<companions.size();i++) {
								if(companions.get(i).equals(getMakeupTarget())) {
									if(i==companions.size()-1) {
										makeupTarget = companions.get(0);
										break;
										
									} else {
										makeupTarget = companions.get(i+1);
										break;
									}
								}
							}
						}
						Main.game.updateResponses();
					}
				};
				
			} else if(index==12) {
				return new Response("Hairstyle & Makeup",
						UtilText.parse(getMakeupTarget(), "There's an impressive assortment of makeup and hair-styling tools in one of your bathroom's cabinets. If you wanted to, you could spend some time improving [npc.namePos] appearance..."),
						AUNT_HOME_PLAYERS_ROOM_MAKEUP){
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
				};
				
			}
		}
		return null;
	}
	
	private static String getShowerSlavesDescription(List<GameCharacter> slavesWashing) {
		StringBuilder sb = new StringBuilder();
		
		boolean soloSlave = slavesWashing.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesWashing) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append(UtilText.parse(slavesWashing,
						"Having been instructed to assist you in washing yourself, your slave, "+Util.stringsToStringList(names, false)+", similarly leaves [npc.her] clothes by the door before following you into the bathroom."));
					sb.append(UtilText.parse(slavesWashing,
							" Thankfully, your luxurious shower is spacious enough that [npc.name] can quite comfortably fit in alongside you."));
				
			} else {
				sb.append("Having been instructed to assist you in washing yourself, your slaves, "+Util.stringsToStringList(names, false)+", similarly leave their clothes by the door before following you into the bathroom.");
					sb.append(UtilText.parse(slavesWashing,
							" Thankfully, your luxurious shower is spacious enough that your slaves can quite comfortably fit in alongside you."));
			}
		sb.append("</p>");
		
		// Slave reactions while helping wash:
		
		List<GameCharacter> washingNice = slavesWashing.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
		boolean firstWashing = true;
		for(GameCharacter npc : washingNice) {
			sb.append("<p>");
			List<String> start = new ArrayList<>();
			List<String> speech = new ArrayList<>();
			
			if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
				if(firstWashing) {
					if(Main.game.getPlayer().hasHair()) {
						start.add("Turning on the taps, [npc.name] dutifully starts to help you in washing your [pc.hair(true)] and body. Raising [npc.her] voice so as to be heard over the sound of running water, [npc.she] says,");
					} else {
						start.add("Turning on the taps, [npc.name] dutifully starts to help you in washing your body. Raising [npc.her] voice so as to be heard over the sound of running water, [npc.she] says,");
					}
				} else {
					start.add("Picking up a bar of soap, [npc.name] assists [npc.her] fellow "+(washingNice.size()>2?"slaves":"slave")+" in cleaning your body. As [npc.she] sets about [npc.her] task, [npc.she] says,");
					start.add("Stepping forwards, with a bar of soap in [npc.hand], [npc.name] sets about cleaning your body, saying as [npc.she] does so,");
					start.add("Stepping up beside [npc.her] fellow "+(washingNice.size()>2?"slaves":"slave")+", [npc.name] starts cleaning your body, saying,");
				}
				speech.add("[npc.speech(I hope this is to your satisfaction, [pc.name].)]");
				speech.add("[npc.speech(Please let me know if you need me to do anything differently, [pc.name].)]");
				speech.add("[npc.speech(I'll be sure to do a good job in cleaning you, [pc.name].)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
				if(firstWashing) {
					if(npc.hasBreasts()) {
						start.add("Turning on the taps, [npc.name] moves up close behind you, before soaping your back down and starting to clean you."
								+ " Suddenly, [npc.she] steps forwards, and, pressing [npc.her] [npc.breasts+] against your back, [npc.she] seductively [npc.moans],");
					} else {
						start.add("Turning on the taps, [npc.name] moves up close behind you, before soaping your back down and starting to clean you."
								+ " Suddenly, [npc.she] steps forwards, and, pressing [npc.herself] against your back, [npc.she] seductively [npc.moans],");
					}
				} else {
					if(npc.hasBreasts()) {
						start.add("Stepping forwards, [npc.name] presses [npc.her] [npc.breasts+] against your back, before seductively [npc.moaning],");
						start.add("Rubbing a bar of soap over your back, [npc.name] suddenly steps forwards, pressing [npc.her] [npc.breasts+] against you as [npc.she] seductively [npc.moans],");
						start.add("Deciding to start by cleaning your back, [npc.name] steps around behind you, before leaning forwards and pressing [npc.her] [npc.breasts+] against you, [npc.moaning],");
					} else {
						start.add("Stepping forwards, [npc.name] presses [npc.herself] against your back, before seductively [npc.moaning],");
						start.add("Rubbing a bar of soap over your back, [npc.name] suddenly steps forwards, pressing [npc.herself] against you as [npc.she] seductively [npc.moans],");
						start.add("Deciding to start by cleaning your back, [npc.name] steps around behind you, before leaning forwards and pressing [npc.herself] against you, [npc.moaning],");
					}
				}
				speech.add("[npc.speech(You don't mind if I get this close, do you?)]");
				speech.add("[npc.speech(You like the feeling of me being this close, don't you?)]");
				speech.add("[npc.speech(That's right, relax and let me take care of you...)]");
				speech.add("[npc.speech(Perhaps once you're clean, you'd like to do something dirty with me...)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
				if(firstWashing) {
					if(npc.hasBreasts()) {
						start.add("Turning on the taps, [npc.name] moves up close behind you, before immediately pressing [npc.her] [npc.breasts+] against your back."
								+ " Obviously seeing this as an opportunity to have some fun with you, [npc.she] reaches around to start groping your body, seductively [npc.moaning] into your [pc.ear] as [npc.she] does this,");
					} else {
						start.add("Turning on the taps, [npc.name] moves up close behind you, before immediately pressing [npc.her] body in against your back."
								+ " Obviously seeing this as an opportunity to have some fun with you, [npc.she] reaches around to start groping your body, seductively [npc.moaning] into your [pc.ear] as [npc.she] does this,");
					}
				} else {
					if(npc.hasBreasts()) {
						start.add("Stepping forwards, [npc.name] presses [npc.her] [npc.breasts+] against your back, before seductively [npc.moaning],");
						start.add("Rubbing a bar of soap over your back, [npc.name] suddenly steps forwards, pressing [npc.her] [npc.breasts+] against you as [npc.she] seductively [npc.moans],");
						start.add("Deciding to start by cleaning your back, [npc.name] steps around behind you, before leaning forwards and pressing [npc.her] [npc.breasts+] against you, [npc.moaning],");
					} else {
						start.add("Stepping forwards, [npc.name] presses [npc.herself] against your back, before seductively [npc.moaning],");
						start.add("Rubbing a bar of soap over your back, [npc.name] suddenly steps forwards, pressing [npc.herself] against you as [npc.she] seductively [npc.moans],");
						start.add("Deciding to start by cleaning your back, [npc.name] steps around behind you, before leaning forwards and pressing [npc.herself] against you, [npc.moaning],");
					}
				}
				speech.add("[npc.speech(Oh yeah, you like me feeling you up like this, don't you?)]");
				speech.add("[npc.speech(All this close contact is really turning me on; you wanna fuck after this?)]");
				speech.add("[npc.speech(This is making me so horny... You're up for having a good fuck after this, aren't you?)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)
					|| npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
				if(npc.isShy()) {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("Turning on the taps, [npc.name] nervously shuffles forwards, before starting to help you wash your [pc.hair(true)] and body."
									+ " Doing [npc.her] best to be heard over the sound of running water, [npc.she] raises [npc.her] voice a little and says,");
						} else {
							start.add("Turning on the taps, [npc.name] nervously shuffles forwards, before starting to help you wash your body."
									+ " Doing [npc.her] best to be heard over the sound of running water, [npc.she] raises [npc.her] voice a little and says,");
						}
					} else {
						start.add("Clutching a bar of soap, [npc.name] nervously shuffles forwards, before helping [npc.her] fellow "+(washingNice.size()>2?"slaves":"slave")+" to clean you. Raising [npc.her] voice a little, [npc.she] says,");
						start.add("Stepping forwards with a bar of soap in [npc.hand], [npc.name] nervously sets about cleaning your body. Squeaking above the noise of running water, [npc.she] says,");
						start.add("Stepping up beside [npc.her] fellow "+(washingNice.size()>2?"slaves":"slave")+", [npc.name] blushes as [npc.she] starts to clean your body, saying,");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(I love being so close to you, [pc.name]?)]");
						speech.add("[npc.speech(I'm so happy to be able to help you like this...)]");
						speech.add("[npc.speech(Getting to help you like this makes me so happy...)]");
					} else {
						speech.add("[npc.speech(I'm not doing this wrong, am I, [pc.name]?)]");
						speech.add("[npc.speech(Just let me know if I'm doing this wrong...)]");
						speech.add("[npc.speech(I hope this is how you wanted me to wash you...)]");
					}
					
				} else if(npc.isKind()) {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("Turning on the taps, [npc.name] happily steps forwards, before gently starting to help you wash your [pc.hair(true)] and body."
									+ " Doing [npc.her] best to be heard over the sound of running water, [npc.she] raises [npc.her] voice and says,");
						} else {
							start.add("Turning on the taps, [npc.name] happily steps forwards, before gently starting to help you wash your body."
									+ " Doing [npc.her] best to be heard over the sound of running water, [npc.she] raises [npc.her] voice and says,");
						}
					} else {
						start.add("Clutching a bar of soap, [npc.name] happily steps forwards, before helping [npc.her] fellow "+(washingNice.size()>2?"slaves":"slave")+" to clean you. Raising [npc.her] voice, [npc.she] says,");
						start.add("Stepping forwards with a bar of soap in [npc.hand], [npc.name] happily sets about cleaning your body. Raising [npc.her] voice above the noise of running water, [npc.she] says,");
						start.add("Stepping up beside [npc.her] fellow "+(washingNice.size()>2?"slaves":"slave")+", [npc.name] smiles as [npc.she] starts to clean your body, saying,");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(I love being so close to you, [pc.name]. Please let me know if you want me to anything differently.)]");
						speech.add("[npc.speech(I'm so happy to be able to help you like this; I'm so lucky to be owned by you.)]");
						speech.add("[npc.speech(Let me take care of you, [pc.name]; I want to show you how much you mean to me.)]");
					} else {
						speech.add("[npc.speech(Let me know if you need me to do anything differently, ok, [pc.name]?)]");
						speech.add("[npc.speech(Please tell me if you need me to do anything differently.)]");
						speech.add("[npc.speech(Let me take care of you, [pc.name].)]");
					}
					
				} else {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("Turning on the taps, [npc.name] steps forwards, before starting to help you wash your [pc.hair(true)] and body."
									+ " Doing [npc.her] best to be heard over the sound of running water, [npc.she] raises [npc.her] voice and says,");
						} else {
							start.add("Turning on the taps, [npc.name] steps forwards, before starting to help you wash your body."
									+ " Doing [npc.her] best to be heard over the sound of running water, [npc.she] raises [npc.her] voice and says,");
						}
					} else {
						start.add("Clutching a bar of soap, [npc.name] steps forwards, before helping [npc.her] fellow "+(washingNice.size()>2?"slaves":"slave")+" to clean you. Raising [npc.her] voice, [npc.she] says,");
						start.add("Stepping forwards with a bar of soap in [npc.hand], [npc.name] sets about cleaning your body. Raising [npc.her] voice above the noise of running water, [npc.she] says,");
						start.add("Stepping up beside [npc.her] fellow "+(washingNice.size()>2?"slaves":"slave")+", [npc.name] smiles as [npc.she] starts to clean your body, saying,");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(I love being so close to you, [pc.name]. Please let me know if you want me to anything differently.)]");
						speech.add("[npc.speech(I'm so happy to be able to help you like this; I'm so lucky to be owned by you.)]");
						speech.add("[npc.speech(Let me take care of you, [pc.name]; I want to show you how much you mean to me.)]");
					} else {
						speech.add("[npc.speech(Let me know if you need me to do anything differently.)]");
						speech.add("[npc.speech(Just tell me if you need me to do anything differently.)]");
						speech.add("[npc.speech(Let me take care of you, [pc.name].)]");
					}
				}
			}
			sb.append(UtilText.parse(npc,Util.randomItemFrom(start)));
			sb.append(" ");
			sb.append(UtilText.parse(npc,Util.randomItemFrom(speech)));
			sb.append("</p>");
			firstWashing = false;
		}
		
		List<GameCharacter> washingRude = new ArrayList<>(slavesWashing);
		washingRude.removeAll(washingNice);
		for(GameCharacter npc : washingRude) {
			sb.append("<p>");
			List<String> start = new ArrayList<>();
			List<String> speech = new ArrayList<>();
			
				if(npc.isShy()) {
					if(firstWashing) {
						start.add("Reluctantly turning on the taps, [npc.name] steps forwards, before half-heartedly starting to help you wash yourself."
								+ " Trying to use the sound of the running water to mask [npc.her] comments, [npc.she] mutters,");
					} else {
						start.add("Reluctantly stepping forwards, [npc.name] casts [npc.her] gaze to the floor as [npc.she] half-heartedly starts to help you wash yourself. Letting out a weary sigh, [npc.she] mutters,");
						start.add("Looking down at the floor, [npc.name] steps forwards and reluctantly starts to help you wash yourself. With an exasperated sigh, [npc.she] mutters under [npc.her] breath,");
						start.add("Clearly not happy about it, [npc.name] nevertheless steps forwards and half-heartedly starts to help you wash yourself. Muttering under [npc.her] breath, [npc.she] sighs,");
					}
					speech.add("[npc.speech(I hate this...)]");
					speech.add("[npc.speech(How did I ever end up having to do things like this...)]");
					speech.add("[npc.speech(This sucks...)]");
					
				} else if(npc.isSelfish()) {
					if(firstWashing) {
						start.add("Reluctantly turning on the taps, [npc.name] steps forwards, before half-heartedly starting to help you wash yourself."
								+ " With a clear tone of animosity in [npc.her] voice, [npc.she] complains,");
					} else {
						start.add("Reluctantly stepping forwards, [npc.name] half-heartedly sets about helping you wash yourself. Letting out a weary sigh, [npc.she] complains,");
						start.add("Rolling [npc.her] [npc.eyes], [npc.name] steps forwards and reluctantly starts to help you wash yourself, complaining as [npc.she] does so,");
						start.add("With an annoyed sigh, [npc.name] steps forwards and half-heartedly starts to help you wash yourself. Not even trying to hide [npc.her] annoyance, [npc.she] angrily says,");
					}
					speech.add("[npc.speech(I really hate doing this, you know? Just get some other slave to help you next time!)]");
					speech.add("[npc.speech(Why do you insist on getting me to do this?! Isn't it clear that I hate helping you out like this?!)]");
					speech.add("[npc.speech(This really fucking sucks. How about you get some other slave to wash you next time?)]");
					
				} else {
					if(firstWashing) {
						start.add("Reluctantly turning on the taps, [npc.name] steps forwards, before half-heartedly starting to help you wash yourself. In a clear sign of displeasure, [npc.she] sighs,");
					} else {
						start.add("Reluctantly stepping forwards, [npc.name] half-heartedly sets about helping you wash yourself. Letting out a weary sigh, [npc.she] sighs,");
						start.add("Rolling [npc.her] [npc.eyes], [npc.name] steps forwards and reluctantly starts to help you wash yourself, complaining as [npc.she] does so,");
						start.add("With an annoyed sigh, [npc.name] steps forwards and half-heartedly starts to help you wash yourself. Not even trying to hide [npc.her] annoyance, [npc.she] sighs,");
					}
					speech.add("[npc.speech(Is there nobody else you can ask to do this?)]");
					speech.add("[npc.speech(I really wish you didn't make me do this...)]");
					speech.add("[npc.speech(This sucks...)]");
					speech.add("[npc.speech(I really hate having to do this, you know?)]");
				}

			sb.append(UtilText.parse(npc,Util.randomItemFrom(start)));
			sb.append(" ");
			sb.append(UtilText.parse(npc,Util.randomItemFrom(speech)));
			sb.append("</p>");
			firstWashing = false;
		}
		
		return sb.toString();
	}
	
	private static String getBathSlavesDescription(List<GameCharacter> slavesWashing) {
		StringBuilder sb = new StringBuilder();
		
		boolean soloSlave = slavesWashing.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesWashing) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append(UtilText.parse(slavesWashing,
						"Having been instructed to assist you in washing yourself, your slave, "+Util.stringsToStringList(names, false)+", similarly leaves [npc.her] clothes by the door before following you into the bathroom."));
					sb.append(UtilText.parse(slavesWashing,
							" Thankfully, your luxurious bathroom is spacious enough that the room doesn't feel overcrowded with [npc.name] in here as well."));
				
			} else {
				sb.append("Having been instructed to assist you in washing yourself, your slaves, "+Util.stringsToStringList(names, false)+", similarly leave their clothes by the door before following you into the bathroom.");
					sb.append(UtilText.parse(slavesWashing,
							" Thankfully, your luxurious bathroom is spacious enough that the room doesn't feel overcrowded with your slaves in here as well."));
			}
		sb.append("</p>");
		
		// Slave reactions while helping wash:
		
		List<GameCharacter> washingNice = slavesWashing.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
		boolean firstWashing = true;
		for(GameCharacter npc : washingNice) {
			sb.append("<p>");
			List<String> start = new ArrayList<>();
			List<String> speech = new ArrayList<>();
			
			if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
				if(firstWashing) {
					if(Main.game.getPlayer().hasHair()) {
						start.add("Turning on the taps and running you a bath, [npc.name] dutifully starts to help you in washing your [pc.hair(true)] and body. Raising [npc.her] voice so as to be heard over the sound of running water, [npc.she] says,");
					} else {
						start.add("Turning on the taps and running you a bath, [npc.name] dutifully starts to help you in washing your body. Raising [npc.her] voice so as to be heard over the sound of running water, [npc.she] says,");
					}
				} else {
					start.add("Picking up a bar of soap, [npc.name] assists [npc.her] fellow "+(washingNice.size()>2?"slaves":"slave")+" in cleaning your body. As [npc.she] sets about [npc.her] task, [npc.she] says,");
					start.add("Stepping forwards, with a bar of soap in [npc.hand], [npc.name] sets about cleaning your body, saying as [npc.she] does so,");
					start.add("Stepping up beside [npc.her] fellow "+(washingNice.size()>2?"slaves":"slave")+", [npc.name] starts cleaning your body, saying,");
				}
				speech.add("[npc.speech(I hope this is to your satisfaction, [pc.name].)]");
				speech.add("[npc.speech(Please let me know if you need me to do anything differently, [pc.name].)]");
				speech.add("[npc.speech(I'll be sure to do a good job in cleaning you, [pc.name].)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
				if(firstWashing) {
					if(npc.hasBreasts()) {
						start.add("Turning on the taps and running you a bath, [npc.name] moves up close behind you, before soaping your back down and starting to clean you."
								+ " Suddenly, [npc.she] steps forwards, and, pressing [npc.her] [npc.breasts+] against your back, [npc.she] seductively [npc.moans],");
					} else {
						start.add("Turning on the taps and running you a bath, [npc.name] moves up close behind you, before soaping your back down and starting to clean you."
								+ " Suddenly, [npc.she] steps forwards, and, pressing [npc.herself] against your back, [npc.she] seductively [npc.moans],");
					}
				} else {
					if(npc.hasBreasts()) {
						start.add("Stepping forwards, [npc.name] presses [npc.her] [npc.breasts+] against your back, before seductively [npc.moaning],");
						start.add("Rubbing a bar of soap over your back, [npc.name] suddenly steps forwards, pressing [npc.her] [npc.breasts+] against you as [npc.she] seductively [npc.moans],");
						start.add("Deciding to start by cleaning your back, [npc.name] steps around behind you, before leaning forwards and pressing [npc.her] [npc.breasts+] against you, [npc.moaning],");
					} else {
						start.add("Stepping forwards, [npc.name] presses [npc.herself] against your back, before seductively [npc.moaning],");
						start.add("Rubbing a bar of soap over your back, [npc.name] suddenly steps forwards, pressing [npc.herself] against you as [npc.she] seductively [npc.moans],");
						start.add("Deciding to start by cleaning your back, [npc.name] steps around behind you, before leaning forwards and pressing [npc.herself] against you, [npc.moaning],");
					}
				}
				speech.add("[npc.speech(You don't mind if I get this close, do you?)]");
				speech.add("[npc.speech(You like the feeling of me being this close, don't you?)]");
				speech.add("[npc.speech(That's right, relax and let me take care of you...)]");
				speech.add("[npc.speech(Perhaps once you're clean, you'd like to do something dirty with me...)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
				if(firstWashing) {
					if(npc.hasBreasts()) {
						start.add("Turning on the taps and running you a bath, [npc.name] moves up close behind you, before immediately pressing [npc.her] [npc.breasts+] against your back."
								+ " Obviously seeing this as an opportunity to have some fun with you, [npc.she] reaches around to start groping your body, seductively [npc.moaning] into your [pc.ear] as [npc.she] does this,");
					} else {
						start.add("Turning on the taps and running you a bath, [npc.name] moves up close behind you, before immediately pressing [npc.her] body in against your back."
								+ " Obviously seeing this as an opportunity to have some fun with you, [npc.she] reaches around to start groping your body, seductively [npc.moaning] into your [pc.ear] as [npc.she] does this,");
					}
				} else {
					if(npc.hasBreasts()) {
						start.add("Stepping forwards, [npc.name] presses [npc.her] [npc.breasts+] against your back, before seductively [npc.moaning],");
						start.add("Rubbing a bar of soap over your back, [npc.name] suddenly steps forwards, pressing [npc.her] [npc.breasts+] against you as [npc.she] seductively [npc.moans],");
						start.add("Deciding to start by cleaning your back, [npc.name] steps around behind you, before leaning forwards and pressing [npc.her] [npc.breasts+] against you, [npc.moaning],");
					} else {
						start.add("Stepping forwards, [npc.name] presses [npc.herself] against your back, before seductively [npc.moaning],");
						start.add("Rubbing a bar of soap over your back, [npc.name] suddenly steps forwards, pressing [npc.herself] against you as [npc.she] seductively [npc.moans],");
						start.add("Deciding to start by cleaning your back, [npc.name] steps around behind you, before leaning forwards and pressing [npc.herself] against you, [npc.moaning],");
					}
				}
				speech.add("[npc.speech(Oh yeah, you like me feeling you up like this, don't you?)]");
				speech.add("[npc.speech(All this close contact is really turning me on; you wanna fuck after this?)]");
				speech.add("[npc.speech(This is making me so horny... You're up for having a good fuck after this, aren't you?)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)
					|| npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
				if(npc.isShy()) {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("Turning on the taps and running you a bath, [npc.name] nervously shuffles forwards, before starting to help you wash your [pc.hair(true)] and body."
									+ " Doing [npc.her] best to be heard over the sound of running water, [npc.she] raises [npc.her] voice a little and says,");
						} else {
							start.add("Turning on the taps and running you a bath, [npc.name] nervously shuffles forwards, before starting to help you wash your body."
									+ " Doing [npc.her] best to be heard over the sound of running water, [npc.she] raises [npc.her] voice a little and says,");
						}
					} else {
						start.add("Clutching a bar of soap, [npc.name] nervously shuffles forwards, before helping [npc.her] fellow "+(washingNice.size()>2?"slaves":"slave")+" to clean you. Raising [npc.her] voice a little, [npc.she] says,");
						start.add("Stepping forwards with a bar of soap in [npc.hand], [npc.name] nervously sets about cleaning your body. Squeaking above the noise of running water, [npc.she] says,");
						start.add("Stepping up beside [npc.her] fellow "+(washingNice.size()>2?"slaves":"slave")+", [npc.name] blushes as [npc.she] starts to clean your body, saying,");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(I love being so close to you, [pc.name]?)]");
						speech.add("[npc.speech(I'm so happy to be able to help you like this...)]");
						speech.add("[npc.speech(Getting to help you like this makes me so happy...)]");
					} else {
						speech.add("[npc.speech(I'm not doing this wrong, am I, [pc.name]?)]");
						speech.add("[npc.speech(Just let me know if I'm doing this wrong...)]");
						speech.add("[npc.speech(I hope this is how you wanted me to wash you...)]");
					}
					
				} else if(npc.isKind()) {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("Turning on the taps and running you a bath, [npc.name] happily steps forwards, before gently starting to help you wash your [pc.hair(true)] and body."
									+ " Doing [npc.her] best to be heard over the sound of running water, [npc.she] raises [npc.her] voice and says,");
						} else {
							start.add("Turning on the taps and running you a bath, [npc.name] happily steps forwards, before gently starting to help you wash your body."
									+ " Doing [npc.her] best to be heard over the sound of running water, [npc.she] raises [npc.her] voice and says,");
						}
					} else {
						start.add("Clutching a bar of soap, [npc.name] happily steps forwards, before helping [npc.her] fellow "+(washingNice.size()>2?"slaves":"slave")+" to clean you. Raising [npc.her] voice, [npc.she] says,");
						start.add("Stepping forwards with a bar of soap in [npc.hand], [npc.name] happily sets about cleaning your body. Raising [npc.her] voice above the noise of running water, [npc.she] says,");
						start.add("Stepping up beside [npc.her] fellow "+(washingNice.size()>2?"slaves":"slave")+", [npc.name] smiles as [npc.she] starts to clean your body, saying,");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(I love being so close to you, [pc.name]. Please let me know if you want me to anything differently.)]");
						speech.add("[npc.speech(I'm so happy to be able to help you like this; I'm so lucky to be owned by you.)]");
						speech.add("[npc.speech(Let me take care of you, [pc.name]; I want to show you how much you mean to me.)]");
					} else {
						speech.add("[npc.speech(Let me know if you need me to do anything differently, ok, [pc.name]?)]");
						speech.add("[npc.speech(Please tell me if you need me to do anything differently.)]");
						speech.add("[npc.speech(Let me take care of you, [pc.name].)]");
					}
					
				} else {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("Turning on the taps and running you a bath, [npc.name] steps forwards, before starting to help you wash your [pc.hair(true)] and body."
									+ " Doing [npc.her] best to be heard over the sound of running water, [npc.she] raises [npc.her] voice and says,");
						} else {
							start.add("Turning on the taps and running you a bath, [npc.name] steps forwards, before starting to help you wash your body."
									+ " Doing [npc.her] best to be heard over the sound of running water, [npc.she] raises [npc.her] voice and says,");
						}
					} else {
						start.add("Clutching a bar of soap, [npc.name] steps forwards, before helping [npc.her] fellow "+(washingNice.size()>2?"slaves":"slave")+" to clean you. Raising [npc.her] voice, [npc.she] says,");
						start.add("Stepping forwards with a bar of soap in [npc.hand], [npc.name] sets about cleaning your body. Raising [npc.her] voice above the noise of running water, [npc.she] says,");
						start.add("Stepping up beside [npc.her] fellow "+(washingNice.size()>2?"slaves":"slave")+", [npc.name] smiles as [npc.she] starts to clean your body, saying,");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(I love being so close to you, [pc.name]. Please let me know if you want me to anything differently.)]");
						speech.add("[npc.speech(I'm so happy to be able to help you like this; I'm so lucky to be owned by you.)]");
						speech.add("[npc.speech(Let me take care of you, [pc.name]; I want to show you how much you mean to me.)]");
					} else {
						speech.add("[npc.speech(Let me know if you need me to do anything differently.)]");
						speech.add("[npc.speech(Just tell me if you need me to do anything differently.)]");
						speech.add("[npc.speech(Let me take care of you, [pc.name].)]");
					}
				}
			}
			sb.append(UtilText.parse(npc,Util.randomItemFrom(start)));
			sb.append(" ");
			sb.append(UtilText.parse(npc,Util.randomItemFrom(speech)));
			sb.append("</p>");
			firstWashing = false;
		}
		
		List<GameCharacter> washingRude = new ArrayList<>(slavesWashing);
		washingRude.removeAll(washingNice);
		for(GameCharacter npc : washingRude) {
			sb.append("<p>");
			List<String> start = new ArrayList<>();
			List<String> speech = new ArrayList<>();
			
				if(npc.isShy()) {
					if(firstWashing) {
						start.add("Reluctantly turning on the taps, [npc.name] steps forwards, before half-heartedly starting to help you wash yourself."
								+ " Trying to use the sound of the running water to mask [npc.her] comments, [npc.she] mutters,");
					} else {
						start.add("Reluctantly stepping forwards, [npc.name] casts [npc.her] gaze to the floor as [npc.she] half-heartedly starts to help you wash yourself. Letting out a weary sigh, [npc.she] mutters,");
						start.add("Looking down at the floor, [npc.name] steps forwards and reluctantly starts to help you wash yourself. With an exasperated sigh, [npc.she] mutters under [npc.her] breath,");
						start.add("Clearly not happy about it, [npc.name] nevertheless steps forwards and half-heartedly starts to help you wash yourself. Muttering under [npc.her] breath, [npc.she] sighs,");
					}
					speech.add("[npc.speech(I hate this...)]");
					speech.add("[npc.speech(How did I ever end up having to do things like this...)]");
					speech.add("[npc.speech(This sucks...)]");
					
				} else if(npc.isSelfish()) {
					if(firstWashing) {
						start.add("Reluctantly turning on the taps, [npc.name] steps forwards, before half-heartedly starting to help you wash yourself."
								+ " With a clear tone of animosity in [npc.her] voice, [npc.she] complains,");
					} else {
						start.add("Reluctantly stepping forwards, [npc.name] half-heartedly sets about helping you wash yourself. Letting out a weary sigh, [npc.she] complains,");
						start.add("Rolling [npc.her] [npc.eyes], [npc.name] steps forwards and reluctantly starts to help you wash yourself, complaining as [npc.she] does so,");
						start.add("With an annoyed sigh, [npc.name] steps forwards and half-heartedly starts to help you wash yourself. Not even trying to hide [npc.her] annoyance, [npc.she] angrily says,");
					}
					speech.add("[npc.speech(I really hate doing this, you know? Just get some other slave to help you next time!)]");
					speech.add("[npc.speech(Why do you insist on getting me to do this?! Isn't it clear that I hate helping you out like this?!)]");
					speech.add("[npc.speech(This really fucking sucks. How about you get some other slave to wash you next time?)]");
					
				} else {
					if(firstWashing) {
						start.add("Reluctantly turning on the taps, [npc.name] steps forwards, before half-heartedly starting to help you wash yourself. In a clear sign of displeasure, [npc.she] sighs,");
					} else {
						start.add("Reluctantly stepping forwards, [npc.name] half-heartedly sets about helping you wash yourself. Letting out a weary sigh, [npc.she] sighs,");
						start.add("Rolling [npc.her] [npc.eyes], [npc.name] steps forwards and reluctantly starts to help you wash yourself, complaining as [npc.she] does so,");
						start.add("With an annoyed sigh, [npc.name] steps forwards and half-heartedly starts to help you wash yourself. Not even trying to hide [npc.her] annoyance, [npc.she] sighs,");
					}
					speech.add("[npc.speech(Is there nobody else you can ask to do this?)]");
					speech.add("[npc.speech(I really wish you didn't make me do this...)]");
					speech.add("[npc.speech(This sucks...)]");
					speech.add("[npc.speech(I really hate having to do this, you know?)]");
				}

			sb.append(UtilText.parse(npc,Util.randomItemFrom(start)));
			sb.append(" ");
			sb.append(UtilText.parse(npc,Util.randomItemFrom(speech)));
			sb.append("</p>");
			firstWashing = false;
		}
		
		return sb.toString();
	}
	
	/** Calendar's associated animal-morphs are based on the twelve animals of the Chinese zodiac, with the Monkey being replaced with a demon, the Rooster with a harpy, and the Snake with a lamia.
	 *  The ordering of the demon and harpy have also been switched, so that October has demons.<br/>
	 *  There is also a 15% chance of giving a different, random animal-morph for each month.<br/>
	 * Animals are:<br/>
	 * Rat, Cow, Tiger, Rabbit, Dragon, Lamia (Snake), Horse, Sheep/Goat, Harpy (Rooster), Demon (Monkey), Dog, Pig
	 */
	private static String getCalendarImageDescription(Month month) {
		StringBuilder sb = new StringBuilder();

		sb.append("<p>"
				+ "Flicking through the calendar until you're looking at the page for "+month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)+", you see that this month's image is now of ");
		
		if(Util.random.nextInt()<15) {
			if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
				sb.append(UtilText.returnStringAtRandom(
						"a handsome merman, who's busily flexing his muscles while perched on a wave-swept rock.",
						"muscular reindeer-boy, who's grinning as he presents his huge cock to you."));
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"a beautiful mermaid, who's happily showing off her exposed breasts while perched on a wave-swept rock.",
						"a curvy reindeer-girl, who's bending over a wooden table and presenting her wet pussy to you."));
			}
			
		} else {
			switch(month) {
				case JANUARY:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a toned "+Subspecies.RAT_MORPH.getSingularMaleName(null)+", who's grinning mischievously at you while stroking his fat, erect cock.");
					} else {
						sb.append("a horny "+Subspecies.RAT_MORPH.getSingularFemaleName(null)+", who's bent over a table in order to present her dripping pussy to you.");
					}
					break;
				case FEBRUARY:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a topless "+Subspecies.COW_MORPH.getSingularMaleName(null)+"."
								+ " His huge muscles are flexing as he carries a felled tree over one shoulder, while between his legs, you can't help but notice that he's got a massive bulge pressing out against the fabric of his shorts.");
					} else {
						sb.append("a black-and-white "+Subspecies.COW_MORPH.getSingularFemaleName(null)+", who's sitting on a small milking stool."
								+ " With a happy smile on her face, she's busily pinching and tugging at at her engorged nipples, causing a stream of milk to flow out into a metal bucket.");
					}
					break;
				case MARCH:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a fierce-looking "+Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_tiger").getSingularMaleName(null)+"."
								+ " Striking a dominant pose, he's flashing you a toothy grin, clearly excited by the fact that his huge feline cock is fully on display.");
					} else {
						sb.append("a fierce-looking "+Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_tiger").getSingularFemaleName(null)+"."
								+ " Striking a dominant pose, she's flashing you a toothy grin, clearly excited by the fact that her large breasts and tight pussy are fully on display.");
					}
					break;
				case APRIL:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a handsome "+Subspecies.RABBIT_MORPH.getSingularMaleName(null)+", who's holding his massive cock in one hand while giving you a suggestive wink.");
					} else {
						sb.append("three blushing "+Subspecies.RABBIT_MORPH.getPluralFemaleName(null)+", who are down on all fours, side-by-side, presenting their pussies to you.");
					}
					break;
				case MAY:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a powerful dragon, who's sitting on a golden throne perched on the top of a huge pile of treasure."
								+ " His huge, scaly cock is fully on display, and with a grin on his face, he's giving you an expectant look, as though he's waiting for you to climb up and get a taste of it.");
					} else {
						sb.append("a powerful dragoness, who's sitting on a golden throne perched on the top of a huge pile of treasure."
								+ " Her wet, scaly pussy is fully on display, and with a grin on her face, she's giving you an expectant look, as though she's waiting for you to climb up and get a taste of it.");
					}
					break;
				case JUNE:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("an exotic-looking male lamia."
								+ " He's quite clearly turned on and eager to have sex with someone, for his twin-cocks have pushed out from his cloaca; their heads already glistening in the sun from the slimy precum they're starting to exude.");
					} else {
						sb.append("an exotic-looking female lamia."
								+ " She's quite clearly turned on and eager to have sex with someone, for she's reaching down to spread her cloaca and present her dripping-wet pussy to you.");
					}
					break;
				case JULY:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("an impressively-endowed "+Subspecies.HORSE_MORPH.getSingularMaleName(null)+", who's flexing his muscles as he presents his fully-erect flared cock to you.");
					} else {
						sb.append("a fit "+Subspecies.HORSE_MORPH.getSingularFemaleName(null)+", who's leaning against a fence, flicking her tail to one side in order to present her animalistic-pussy to you.");
					}
					break;
				case AUGUST:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a sheep-boy and goat-boy, standing side-by-side and presenting their erect cocks as they wink playfully at you.");
					} else {
						sb.append("a woolly sheep-girl and goat-girl, who are lying back and spreading their legs, presenting you with their tight, wet pussies.");
					}
					break;
				case SEPTEMBER:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("an unusually-masculine harpy."
								+ " Although the size of his cock is nothing to write home about, he's extremely handsome, and you feel your heart beating faster as you see him winking at you.");
					} else {
						sb.append("a beautiful female harpy."
								+ " Although she's willingly presenting her wet pussy to you, the look on her face is one of condescending superiority,"
									+ " and you get the impression that she'd make some kind of outrageous demand in exchange for allowing you to have sex with her.");
					}
					break;
				case OCTOBER:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a fit, handsome "+Subspecies.DEMON.getSingularMaleName(null)+", who's suggestively winking at you as he runs his fingers over his huge, erect cock.");
					} else {
						sb.append("a fit, beautiful "+Subspecies.DEMON.getSingularFemaleName(null)+", wearing nothing but a witch's hat, who's suggestively winking at you as she runs her fingers over her wet pussy and huge breasts.");
					}
					break;
				case NOVEMBER:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("an energetic-looking "+Subspecies.DOG_MORPH.getSingularMaleName(null)+", who's smiling at you as he strokes his erect, knotted dog-cock.");
					} else {
						sb.append("an excited-looking "+Subspecies.DOG_MORPH.getSingularFemaleName(null)+", who's down on all fours, raising her hips in order to present you with her wet pussy.");
					}
					break;
				case DECEMBER:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a muscular boar-boy, who's grinning at you in anticipation as he strokes his huge cock and pair of massive, cum-filled balls.");
					} else {
						sb.append("a pretty, blushing pig-girl, who's leaning back against a wall and reaching down to spread her puffy pink pussy to you.");
					}
					break;
			}
		}
		
		sb.append(" After gazing at the picture for a few moments, you force yourself to look away and read the information that's written beneath:"
				+ "</p>");
		
		return sb.toString();
	}

	public static final DialogueNode ROOM = new DialogueNode("Your Room", "", false) {
		@Override
		public void applyPreParsingEffects() {
			makeupTarget = Main.game.getPlayer();
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			GenericPlace place = Main.game.getPlayerCell().getPlace();
			
			sb.append("<p>"
					+ "Your bedroom is positioned close to the main staircase linking the entrance hall to the first-floor corridor, and is one of the largest chambers in the entire mansion."
					+ " Opposite the room's main doorway, a set of four large, sash windows provide an excellent view of the courtyard garden below, while off to the left, another door leads through into your private ensuite bathroom."
				+ "</p>");
			
			if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_PLAYER_ROOM_BED)) {
				sb.append("<p>"
							+ "Your room is well furnished, and contains both two sets of drawers and a full-height wardrobe, which provide you with all the storage space you'd ever need."
							+ " Other than those items of furniture, you also have a sofa, a writing desk complete with matching chair, and a full-height free-standing mirror."
						+ "</p>");
			} else {
				sb.append("<p>"
						+ "A king-sized bed sits against the right-hand wall, while two sets of drawers and a full-height wardrobe provide you with all the storage space you'd ever need."
						+ " Other than those items of furniture, you also have a sofa, a writing desk complete with matching chair, and a full-height free-standing mirror."
					+ "</p>");
			}
			
			sb.append(
					"<p>"
						+ "Like everything else that normally would have run on electricity in your world, the lighting, radiators, and plumbing all appear to be powered by the arcane."
					+ "</p>");
			
			sb.append(LilayaHomeGeneric.getRoomModificationsDescription(false));

			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			if(!charactersPresent.isEmpty()) {
				List<String> names = new ArrayList<>();
				boolean soloSlave = charactersPresent.size()==1;
				for(NPC npc : charactersPresent) {
					names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
				}
				sb.append("<p>"
							+ "Assigned to your room "+(soloSlave?"is your slave":"are your slaves")+"; "+Util.stringsToStringList(names, false)+".");
				
				List<NPC> greetings = charactersPresent.stream().filter(npc -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_GREETING)).collect(Collectors.toList());
				names = new ArrayList<>();
				for(NPC npc : greetings) {
					names.add(npc.getName());
				}
				soloSlave = greetings.size()==1;
				if(!greetings.isEmpty()) {
					sb.append(" Having been instructed to greet you upon your arrival, "
								+(soloSlave
										?UtilText.parse(charactersPresent.get(0), "[npc.she] steps forwards and welcomes you back.")
										:Util.stringsToStringList(names, false)+" step forwards and welcome you back.")
							+ "</p>");

					List<NPC> greetingsNice = greetings.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
					for(NPC npc : greetingsNice) {
						sb.append("<p>");
						List<String> speechGreetings = new ArrayList<>();
						List<String> endGreetings = new ArrayList<>();
						List<String> endSpeechGreetings = new ArrayList<>();
						
						if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
							speechGreetings.add("[npc.speech(Welcome back, [pc.name],)]");
							speechGreetings.add("[npc.speech(Good [style.morning], [pc.name],)]");
							speechGreetings.add("[npc.speech(Welcome home, [pc.name],)]");
							
							if(npc.isFeminine()) {
								endGreetings.add("[npc.name] says, curtsying respectfully towards you.");
								endGreetings.add("[npc.name] greets you, curtsying respectfully as [npc.she] does so.");
								endGreetings.add("[npc.name] addresses you, curtsying in the process.");
							} else {
								endGreetings.add("[npc.name] says, bowing respectfully towards you.");
								endGreetings.add("[npc.name] greets you, bowing respectfully as [npc.she] does so.");
								endGreetings.add("[npc.name] addresses you, bowing in the process.");
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
							speechGreetings.add("[npc.speech(Back so soon, [pc.name]?)]");
							speechGreetings.add("[npc.speech(Back already, [pc.name]?)]");
							speechGreetings.add("[npc.speech(You're back to see me so soon, [pc.name]?)]");
							
							if(npc.isFeminine()) {
								endGreetings.add("[npc.name] coyly asks, before biting [npc.her] lip and winking at you.");
								endGreetings.add("[npc.name] coyly asks, before turning slightly to one side and flashing you a seductive smile.");
								endGreetings.add("[npc.name] coyly asks, winking at you before blowing you a little kiss.");
							} else {
								endGreetings.add("[npc.name] flirtatiously asks, before flashing you a charming smile.");
								endGreetings.add("[npc.name] flirtatiously asks, before puffing his chest up and flashing you a winning smile.");
								endGreetings.add("[npc.name] flirtatiously asks, smiling at you in a charming manner.");
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
							speechGreetings.add("[npc.speech(You're back to fuck, right, [pc.name]?)]");
							speechGreetings.add("[npc.speech(Oh, [pc.name], you're here for a good fuck, aren't you?)]");
							speechGreetings.add("[npc.speech(Hey, [pc.name], you wanna fuck, right?)]");

							endGreetings.add("[npc.name] bluntly asks,");
							endGreetings.add("[npc.name] tactlessly asks,");
							endGreetings.add("[npc.name] gracelessly asks,");
							
							if(npc.isFeminine()) {
								endSpeechGreetings.add("[npc.speech(You know I'm always horny for you...)]");
								endSpeechGreetings.add("[npc.speech(I can't stop fantasising about you...)]");
								endSpeechGreetings.add("[npc.speech(I get so horny just thinking about you...)]");
							} else {
								endSpeechGreetings.add("[npc.speech(I want you so badly right now.)]");
								endSpeechGreetings.add("[npc.speech(I can't stop fantasising about you.)]");
								endSpeechGreetings.add("[npc.speech(You know I'm always ready whenever you are.)]");
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)) {
							speechGreetings.add("[npc.speech(Hello, [pc.name],)]");
							speechGreetings.add("[npc.speech(Good [style.morning], [pc.name],)]");
							speechGreetings.add("[npc.speech(Welcome back, [pc.name],)]");
							
							if(npc.isShy()) {
								endGreetings.add("[npc.name] says, looking at the floor and shyly shuffling [npc.her] [npc.feet].");
								endGreetings.add("[npc.name] says in a quiet voice, before blushing and looking down at the floor.");
								endGreetings.add("[npc.name] quietly greets you while shyly shuffling [npc.her] [npc.feet].");
								
							} else if(npc.isKind()) {
								endGreetings.add("[npc.name] says, smiling kindly at you,");
								endGreetings.add("[npc.name] says in a kind voice, before gently smiling at you and continuing,");
								endGreetings.add("[npc.name] happily greets you in a loving tone,");
								
								endSpeechGreetings.add("[npc.speech(Is there anything I can do for you?)]");
								endSpeechGreetings.add("[npc.speech(How are you this [style.morning]?)]");
								endSpeechGreetings.add("[npc.speech(Please let me know if there's anything I can do for you!)]");
								
							} else {
								endGreetings.add("[npc.name] calls out, before smiling happily at you.");
								endGreetings.add("[npc.name] happily greets you, before smiling and waiting for your orders.");
								endGreetings.add("[npc.name] says, smiling at you.");
								endGreetings.add("[npc.name] happily says, before smiling at you.");
								endGreetings.add("[npc.name] addresses you, before smiling and waiting to see what it is you'll do next.");
								endGreetings.add("[npc.name] greets you with a smile.");
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
							speechGreetings.add("[npc.speech(Hello, [pc.name],)]");
							speechGreetings.add("[npc.speech(Good [style.morning], [pc.name],)]");
							speechGreetings.add("[npc.speech(Welcome home, [pc.name],)]");
							
							if(npc.isShy()) {
								endGreetings.add("[npc.name] says, looking at the floor and shyly shuffling [npc.her] [npc.feet], before continuing,");
								endGreetings.add("[npc.name] says in a quiet voice, before blushing and shyly continuing,");
								endGreetings.add("[npc.name] quietly greets you while shyly shuffling [npc.her] [npc.feet], before blushing and continuing,");
								
								endSpeechGreetings.add("[npc.speech(I'm so happy that you're back; I really missed you...)]");
								endSpeechGreetings.add("[npc.speech(It really makes me happy to see you again...)]");
								endSpeechGreetings.add("[npc.speech(I really missed you while you were gone...)]");
								
							} else if(npc.isKind()) {
								endGreetings.add("[npc.name] says, smiling happily at you for a moment, before continuing,");
								endGreetings.add("[npc.name] says in a kind voice, before smiling at you in a loving manner and continuing,");
								endGreetings.add("[npc.name] greets you in a kind tone, before throwing you a loving smile and continuing,");

								endSpeechGreetings.add("[npc.speech(It's so good to see you again; it felt like you were gone forever!)]");
								endSpeechGreetings.add("[npc.speech(I'm so happy to see you again; I hope that you won't be gone for so long next time!)]");
								endSpeechGreetings.add("[npc.speech(I really missed you, you know; thank goodness you're back now!)]");
								
							} else {
								endGreetings.add("[npc.name] cheerfully says, before continuing,");
								endGreetings.add("[npc.name] happily says, before smiling at you and continuing,");
								endGreetings.add("[npc.name] greets you in an exited tone, before flashing you a happy smile and continuing,");

								endSpeechGreetings.add("[npc.speech(I'm so happy to see you again!)]");
								endSpeechGreetings.add("[npc.speech(It really makes me happy to see you again!)]");
								endSpeechGreetings.add("[npc.speech(I hope you aren't gone for so long next time! I miss you, you know?)]");
							}
						}
						sb.append(UtilText.parse(npc,Util.randomItemFrom(speechGreetings)));
						sb.append(" ");
						sb.append(UtilText.parse(npc,Util.randomItemFrom(endGreetings)));
						if(!endSpeechGreetings.isEmpty()) {
							sb.append(" ");
							sb.append(UtilText.parse(npc,Util.randomItemFrom(endSpeechGreetings)));
						}
						sb.append("</p>");
					}
					
					List<NPC> greetingsRude = new ArrayList<>(greetings);
					greetingsRude.removeAll(greetingsNice);
					for(NPC npc : greetingsRude) {
						sb.append("<p>");
						List<String> speechGreetings = new ArrayList<>();
						List<String> endGreetings = new ArrayList<>();
						
							if(npc.isShy()) {
								speechGreetings.add("[npc.speech(Oh, it's just you,)]");
								speechGreetings.add("[npc.speech(Damn, I was hoping it was someone else,)]");
								speechGreetings.add("[npc.speech(Oh no, not you,)]");
								speechGreetings.add("[npc.speech(Why did [pc.she] have to come back,)]");
								
								endGreetings.add("[npc.name] mutters under [npc.her] breath, before looking at the floor and shuffling [npc.her] [npc.feet].");
								endGreetings.add("[npc.name] mumbles, before casting [npc.her] gaze to the floor and refusing to look up at you.");
								endGreetings.add("[npc.name] mutters in annoyance, before shuffling [npc.her] [npc.feet] and letting out another quiet curse.");
								
							} else if(npc.isSelfish()) {
								speechGreetings.add("[npc.speech(Eugh, what do you want now, [pc.name]?)]");
								speechGreetings.add("[npc.speech(And what exactly do you want <i>this</i> time, [pc.name]?)]");
								speechGreetings.add("[npc.speech(So, why are you back?)]");
								speechGreetings.add("[npc.speech(Why did you have to come back?)]");
								
								endGreetings.add("[npc.name] angrily asks, not even trying to conceal [npc.her] animosity towards you.");
								endGreetings.add("[npc.name] questions, before narrowing [npc.her] [npc.eyes] and glaring angrily at you.");
								endGreetings.add("[npc.name] snaps, before glaring at you with resentment in [npc.her] [npc.eyes].");
								
							} else {
								speechGreetings.add("[npc.speech(Just do whatever it is you're here to do, [pc.name],)]");
								speechGreetings.add("[npc.speech(Go on then, [pc.name], do whatever it is you're here to do,)]");
								speechGreetings.add("[npc.speech(Let's just get this over with,)]");
								speechGreetings.add("[npc.speech(Go on then, tell me what it is you want this time,)]");
								
								endGreetings.add("[npc.name] sighs, before rolling [npc.her] [npc.eyes] in annoyance.");
								endGreetings.add("[npc.name] sighs in annoyance, crossing [npc.her] [npc.arms] and waiting for you to make the next move.");
								endGreetings.add("[npc.name] sighs, clearly not at all happy with being assigned to your room.");
								endGreetings.add("[npc.name] says, before letting out a weary sigh and tapping [npc.her] [npc.foot] on the floor.");
								endGreetings.add("[npc.name] says, before sighing and rolling [npc.her] [npc.eyes] in a clear sign of displeasure.");
								endGreetings.add("[npc.name] says, crossing [npc.her] [npc.arms] and letting out an annoyed sigh.");
							}

						sb.append(UtilText.parse(npc,Util.randomItemFrom(speechGreetings)));
						sb.append(" ");
						sb.append(UtilText.parse(npc,Util.randomItemFrom(endGreetings)));
						sb.append("</p>");
					}
				}
			}
			
			return sb.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseRoom(responseTab, index);
		}
	};

	private static List<GameCharacter> slavesInRoom(int hour) {
		List<GameCharacter> charactersPresent = new ArrayList<>();
		
		for(String slaveId : Main.game.getPlayer().getSlavesOwned()) {
			try {
				GameCharacter slave = Main.game.getNPCById(slaveId);
				if(slave.getSlaveJob(hour)==SlaveJob.BEDROOM) {
					charactersPresent.add(slave);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return charactersPresent;
	}
	
	private static List<GameCharacter> slavesWantingToSexPlayer(int hour) {
		List<GameCharacter> charactersPresent = new ArrayList<>();
		
		for(String slaveId : Main.game.getPlayer().getSlavesOwned()) {
			try {
				GameCharacter slave = Main.game.getNPCById(slaveId);
				if(slave.getSlaveJob(hour)==SlaveJob.BEDROOM
						&& slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER)
						&& slave.isAttractedTo(Main.game.getPlayer())
						&& slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE)) {
					charactersPresent.add(slave);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return charactersPresent;
	}
	
	public static final DialogueNode ROOM_SET_ALARM = new DialogueNode("Set Alarm", "", true) {
		@Override
		public void applyPreParsingEffects() {
			super.applyPreParsingEffects();
			if(Main.game.getDialogueFlags().getSavedLong("player_phone_alarm") < 0) {
				// If unset, default to 8:00 AM
				Main.game.getDialogueFlags().setSavedLong("player_phone_alarm", 8*60);
			}
		}
		@Override
		public String getContent() {
			long alarmTime = Main.game.getDialogueFlags().getSavedLong("player_phone_alarm");
			String alarmTimeStr = Units.time(LocalTime.ofSecondOfDay(alarmTime*60));
			return "<div><p style='text-align:center;'>Taking out your phone, you open the alarm app and prepare to set a time for it to go off...</p></div>"
					+ "<div class='cosmetics-inner-container' style='margin:1% 10%; width:78%; padding:1%; box-sizing:border-box; position:relative;'>"
						+ "<p style='margin:0; padding:0;'>"
							+ "<b>Set Alarm</b>"
						+"</p>"
						+ "<div class='container-full-width' style='width:35%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"
							+ "<div id='PLAYER_ALARM_DECREASE_LARGE' class='normal-button' style='width:48%; margin:1%; padding:0;'>"
								+ "[style.boldBad(-1 hour)]"
							+ "</div>"
							+ "<div id='PLAYER_ALARM_DECREASE' class='normal-button' style='width:48%; margin:1%; padding:0;'>"
								+ "[style.boldBadMinor(-5 minutes)]"
							+ "</div>"
						+ "</div>"
						+ "<div class='container-full-width' style='width:28%; margin:1%; padding:0; text-align:center; float:left; position:relative;'>"
							+ alarmTimeStr
						+ "</div>"
						+ "<div class='container-full-width' style='width:35%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"
							+ "<div id='PLAYER_ALARM_INCREASE' class='normal-button' style='width:48%; margin:1%; padding:0;'>"
								+ "[style.boldGoodMinor(+5 minutes)]"
							+ "</div>"
							+ "<div id='PLAYER_ALARM_INCREASE_LARGE' class='normal-button' style='width:48%; margin:1%; padding:0;'>"
								+ "[style.boldGood(+1 hour)]"
							+ "</div>"
						+ "</div>"
					+ "</div>";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Set alarm", "Your alarm will be set to the time that you've entered.", Main.game.getSavedDialogueNode());
				
			} else if(index == 2) {
				return new Response("Delete alarm", "Delete your alarm, leaving it unset.", Main.game.getSavedDialogueNode()) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().removeSavedLong("player_phone_alarm");
					}
				};
				
			}
			
			return null;
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
//	private static int getHourPlusSleep() {
//		return (Main.game.getHourOfDay() + (sleepTimeInMinutes/60))%24;
//	}
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_SLEEP = new DialogueNode("Your Room", "", false) {

		@Override
		public boolean isTravelDisabled() {
			return !slavesWantingToSexPlayer(Main.game.getHourOfDay()).isEmpty();
		}
		
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			List<GameCharacter> charactersPresent = slavesInRoom(Main.game.getHourOfDay());
			
			if(!charactersPresent.isEmpty()) {
				boolean soloSlave = charactersPresent.size()==1;
				List<GameCharacter> slavesToWakePlayer = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_WAKE_UP)).collect(Collectors.toList());
				List<String> names = new ArrayList<>();
				charactersPresent.stream().forEach((npc) -> names.add(npc.getName()));

				sb.append("<p>"
						+ "Feeling tired and in need of some rest, you head over to your bed and collapse down onto the mattress, before pulling back the covers and slipping beneath them."
						+ " Realising that you want to go to sleep, "+Util.stringsToStringList(names, false)+(soloSlave?" quickly draws the curtains":" quickly draw the curtains")+", before stepping back over towards you."
					+ "</p>");

				// Sleeping arrangements:
				List<GameCharacter> floorSlaves = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_SLEEP_FLOOR)).collect(Collectors.toList());
				List<GameCharacter> onBedSlaves = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_SLEEP_ON_BED)).collect(Collectors.toList());
				if(!floorSlaves.isEmpty() || !onBedSlaves.isEmpty()) {
					sb.append("<p>");
					boolean soloFloor = floorSlaves.size()==1;
					List<String> floorNames = new ArrayList<>();
					floorSlaves.stream().forEach((npc) -> floorNames.add(npc.getName()));
					if(!floorSlaves.isEmpty()) {
						sb.append(soloFloor
								?UtilText.parse(floorSlaves.get(0), "Knowing that [npc.sheIs] not allowed to sleep on your bed, [npc.name] lies down on the floor and prepares to try to get some sleep.")
								:"Knowing that they aren't allowed to sleep on your bed, "+Util.stringsToStringList(floorNames, false)+" lie down on the floor and prepare to try to get some sleep.");
					}
					if(!onBedSlaves.isEmpty()) {
						boolean soloOnBed = onBedSlaves.size()==1;
						List<String> onBedNames = new ArrayList<>();
						onBedSlaves.stream().forEach((npc) -> onBedNames.add(npc.getName()));
						if(!floorSlaves.isEmpty()) {
							sb.append(soloOnBed
									?UtilText.parse(onBedSlaves.get(0),  "Stepping past "+Util.stringsToStringList(floorNames, false)+", [npc.name] climbs up onto your bed, before letting out a relaxed sigh and curling up on your covers.")
									:"Stepping past "+(soloFloor?UtilText.parse(floorSlaves.get(0), "[npc.name]"):"your slaves on the floor")
										+", "+Util.stringsToStringList(onBedNames, false)+" climb up onto your bed, before each letting out relaxed sighs and curling up on your covers.");
						} else {
							sb.append(soloOnBed
									?UtilText.parse(onBedSlaves.get(0), "Knowing that [npc.sheIs] allowed to sleep on the covers, [npc.name] climbs up onto your bed, before letting out a relaxed sigh and curling up, ready to sleep.")
									:"Knowing that they're allowed to sleep on the covers, "+Util.stringsToStringList(onBedNames, false)+" climb up onto your bed, before each letting out relaxed sighs and curling up, ready to sleep.");
						}
					}
					sb.append("</p>");
				}

				List<GameCharacter> inBedSlaves = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_SLEEP_IN_BED)).collect(Collectors.toList());
				if(!inBedSlaves.isEmpty()) {
					sb.append("<p>");
					boolean soloInBed = inBedSlaves.size()==1;
					List<String> inBedNames = new ArrayList<>();
					inBedSlaves.stream().forEach((npc) -> inBedNames.add(npc.getName()));
					sb.append(soloInBed
							?UtilText.parse(inBedSlaves.get(0), "Having been given your explicit permission to do so, [npc.name] pulls back a corner of the duvet, and then quickly slips in under the covers.")
							:"Having been given your explicit permission to do so, "+Util.stringsToStringList(inBedNames, false)
								+" pull back the top two corners of the duvet, and then "+(inBedSlaves.size()==2?"both":"each")+" of them quickly slip in under the covers.");
					sb.append("</p>");
					
					List<GameCharacter> niceSlaves = inBedSlaves.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
					for(GameCharacter npc : niceSlaves) {
						sb.append("<p>");
						List<String> speechGreetings = new ArrayList<>();
						List<String> endGreetings = new ArrayList<>();
						List<String> endSpeechGreetings = new ArrayList<>();
						
						if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(Allow me to keep you warm, [pc.name],)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(Please let me provide you with some extra comfort, [pc.name],)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(Please allow me to keep you warm, [pc.name],)]"));
							
							endGreetings.add(UtilText.parse(npc, "[npc.name] offers, before snuggling up against you."));
							endGreetings.add(UtilText.parse(npc, "[npc.name] offers, moving up beneath the sheets to snuggle in alongside you."));
							endGreetings.add(UtilText.parse(npc, "[npc.name] says, before shuffling up against you and pressing [npc.her] body against yours."));
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(Perhaps we could do something else in this bed after you wake up?)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(After you've got some rest, perhaps the two of us could spend some energy together?)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(Would you like to have some fun with me after you've woken up, [pc.name]?)]"));
							
							endGreetings.add(UtilText.parse(npc, "[npc.name] teases, pressing [npc.herself] against you."));
							endGreetings.add(UtilText.parse(npc, "[npc.name] seductively suggests, before moving up beneath the sheets to press [npc.herself] against you."));
							endGreetings.add(UtilText.parse(npc, "[npc.name] teases, before sliding up beneath the sheets to press [npc.her] body against you."));
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
							if(npc.hasBreasts()) {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(You can use my tits as a pillow if you want,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Give my tits a good grope; it'll help you sleep better,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Come on, play with my tits a bit before you sleep,)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name] offers, pressing [npc.her] [npc.breastSize] breasts in against you."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] cheekily suggests, before sliding up under the covers and pressing [npc.her] [npc.breastSize] breasts into you."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] suggests, before shuffling up and pressing [npc.her] [npc.breastSize] breasts against you."));
								
							} else {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(We can have a good fuck after you've slept, right?)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(After you've got some rest, you'll be up for having a good fuck, won't you?)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(When you get up, we can have a good fuck, right?)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name] bluntly asks, reaching down under the covers to stroke your [pc.leg]."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] cheekily suggests, before sliding up under the covers and pressing [npc.herself] against you."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] suggests, before shuffling up and pressing [npc.herself] against you."));
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)) {
							if(npc.isShy()) {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Erm, [pc.name], just let me know if I'm taking up too much room,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Just let me know if I'm getting in your way,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(If I'm getting too close, just let me know,)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name] shyly says, using the top edge of the sheets to hide [npc.her] blushing [npc.face]."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] says, nervously clutching the sheets as [npc.she] shuffles up alongside you."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] mutters, furiously blushing as [npc.she] moves up alongside you beneath the sheets."));
								
							} else {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Let me get a little closer,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(I just need to get comfortable,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(There we go, that's better,)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name] says, shuffling up alongside you before nestling in against your [pc.breasts]."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] says, before sliding up under the covers and pressing [npc.herself] against you."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] says, smiling as [npc.she] presses [npc.herself] against you."));
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(I love getting to sleep with you, [pc.name],)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(This is the best; getting to sleep with you like this,)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(Getting to sleep with you is the best part of the day,)]"));
							
							if(npc.isShy()) {
								endGreetings.add(UtilText.parse(npc, "[npc.name] shyly says, using the top edge of the sheets to hide [npc.her] blushing [npc.face] as [npc.she] shuffles up to press [npc.herself] against you."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] says, nervously clutching the sheets as [npc.she] shuffles up alongside you."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] nervously mutters, furiously blushing as [npc.she] moves up alongside you beneath the sheets."));
								
							} else {
								endGreetings.add(UtilText.parse(npc, "[npc.name] says, smiling happily as [npc.she] snuggles up against you."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] says, before sliding up under the covers and lovingly pressing [npc.herself] against you."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] lovingly says, before shuffling up and pressing [npc.herself] against you."));
							}
						}
						if(npc.getTailType().isSuitableForSleepHugging()) {
							endSpeechGreetings.add(UtilText.parse(npc, "With a satisfied sigh, the [npc.race] moves [npc.her] [npc.tail+] around beneath the covers, before laying it over your body and using it to give you a loving tail-hug."));
							endSpeechGreetings.add(UtilText.parse(npc, "Moving [npc.her] [npc.tail+] around beneath the covers, the [npc.race] wraps it around your [pc.leg], before moving in even closer against you."));
							endSpeechGreetings.add(
									UtilText.parse(npc, "Displaying an impressive level of control over [npc.her] [npc.tail+], the [npc.race] swiftly moves it up and wraps it around your lower body, before snuggling in even closer against you."));
						}
						
						sb.append(UtilText.parse(npc,Util.randomItemFrom(speechGreetings)));
						sb.append(" ");
						sb.append(UtilText.parse(npc,Util.randomItemFrom(endGreetings)));
						if(!endSpeechGreetings.isEmpty()) {
							sb.append(" ");
							sb.append(UtilText.parse(npc,Util.randomItemFrom(endSpeechGreetings)));
						}
						sb.append("</p>");
					}
					
					List<GameCharacter> rudeSlaves = new ArrayList<>(inBedSlaves);
					rudeSlaves.removeAll(niceSlaves);
					for(GameCharacter npc : rudeSlaves) {
						sb.append("<p>");
							List<String> speechGreetings = new ArrayList<>();
							List<String> endGreetings = new ArrayList<>();
							if(npc.isShy()) {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Please respect my side of the bed,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(I really would prefer it if you didn't get too close,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(I'd really like it if you didn't touch me,)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name] nervously requests, before rolling over and turning [npc.her] back to you."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] mutters, before shuffling away from you."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] shyly requests, before shuffling away under the covers and turning [npc.her] back to you."));
								
							} else if(npc.isSelfish()) {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Don't roll over into my personal space in your sleep,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Keep to your side of the bed,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(There's plenty of room on your side of the bed; don't come into mine,)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name] warns, letting out an annoyed huff as [npc.she] shuffles away from you."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] bluntly states, before shuffling away from you and letting out a frustrated sigh."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] says, before turning [npc.her] back to you."));
								
							} else {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(I'm just going to keep to my part of the bed,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(I'll just stay over here on my side,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(I'll leave you plenty of room, don't worry,)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name] says, letting out a weary sigh as [npc.she] shuffles away from you."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] sighs, shuffling away a little before rolling over and turning [npc.her] back on you."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] sighs, before turning [npc.her] back to you and shuffling over to the edge of the bed."));
							}
							sb.append(UtilText.parse(npc,Util.randomItemFrom(speechGreetings)));
							sb.append(" ");
							sb.append(UtilText.parse(npc,Util.randomItemFrom(endGreetings)));
						sb.append("</p>");
					}
				}
				
				int hour = Main.game.getHourOfDay();
				String morningString = "evening";
				if(hour<4) {
					morningString = "evening";
				} else if(hour<12) {
					morningString = "morning";
				}else if(hour<17) {
					morningString = "afternoon";
				}

				List<GameCharacter> hornySlaves = slavesWantingToSexPlayer(Main.game.getHourOfDay());
				
				if(!slavesToWakePlayer.isEmpty()) {
					GameCharacter slaveWaking = Util.randomItemFrom(slavesToWakePlayer);
					sb.append("<p>"
							+ (soloSlave
								?UtilText.parse(slaveWaking,
									"With you and [npc.name] now in your respective positions, you ask [npc.herHim] to wake you at the time that you'd like to be getting up."
										+ " After [npc.sheHas] reassured you that [npc.she] won't let you sleep in too late, you let out a contented sigh, close your eyes, and start to drift off to sleep...")
								:UtilText.parse(slaveWaking,
									"With your slaves now being settled into their respective positions, you ask [npc.name] to wake you at the time that you'd like to be getting up."
										+ " After [npc.sheHas] reassured you that [npc.she] won't let you sleep in too late, you let out a contented sigh, close your eyes, and start to drift off to sleep..."))
						+ "</p>"
						+ "<p>"
							+ "[style.italics(...)]"
						+ "</p>");
					
					if(hornySlaves.isEmpty()) {
						sb.append("<p>");
						if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
							sb.append(UtilText.parse(slaveWaking,
									"[npc.speech([pc.Name]? It's the appointed hour for you to be waking up,)] you hear a voice calling out to you, and as you slowly open your [pc.eyes], you see [npc.name] smiling down at you."
									+ " Seeing that you're awake, [npc.she] withdraws to open the curtains, calling over [npc.her] shoulder as [npc.she] does so, [npc.speech(Good "+morningString+", [pc.name]!)]"));
						} else if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
							sb.append(UtilText.parse(slaveWaking, 
									"[npc.speech(Come on, [pc.name], it's time to wake up,)] you hear a voice seductively whispering into your [pc.ear], and as you slowly open your [pc.eyes], you see [npc.name] smiling down at you and biting [npc.her] lip."
									+ " Seeing that you're awake, [npc.she] runs a hand over your [pc.chest], before withdrawing to open the curtains. Calling over [npc.her] shoulder as [npc.she] does so, [npc.she] teases,"
									+ " [npc.speech(Good "+morningString+", [pc.name]... So, is there anything <i>special</i> that you wanted to do today?)]"));
						} else if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
							sb.append(UtilText.parse(slaveWaking,
									"[npc.speech(If you get up now, you can make time for a quick fuck, can't you?)] you hear a voice asking, and as you slowly open your [pc.eyes], you see [npc.name] hungrily gazing down at you."
									+ " Seeing that you're awake, [npc.she] leans down to kiss you fully on the [pc.lips], before withdrawing to open the curtains. Calling over [npc.her] shoulder as [npc.she] does so, [npc.she] teases,"
									+ " [npc.speech(Good "+morningString+", [pc.name]! So, you want to fuck me now, right?)]"));
						} else if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)) {
							if(slaveWaking.isShy()) {
								sb.append(UtilText.parse(slaveWaking,
										"[npc.speech([pc.Name]? Erm, excuse me, [pc.name]? It's time to wake up,)] you hear a voice nervously calling out to you, and as you slowly open your [pc.eyes], you see [npc.name] worriedly looking down at you."
											+ " Seeing that you're awake, [npc.she] breaths a little sigh of relief and hurries off to open the curtains, calling out as [npc.she] does so, [npc.speech(Good "+morningString+", [pc.name]!)]"));
							} else {
								sb.append(UtilText.parse(slaveWaking, 
										"[npc.speech([pc.Name]? Come on, [pc.name], it's time to wake up,)] you hear a voice calling out to you, and as you slowly open your [pc.eyes], you see [npc.name] smiling down at you."
												+ " Seeing that you're awake, [npc.she] withdraws to open the curtains, calling over [npc.her] shoulder as [npc.she] does so, [npc.speech(Good "+morningString+", [pc.name]!)]"));
							}
						} else if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
							if(slaveWaking.isShy()) {
								sb.append(UtilText.parse(slaveWaking,
										"[npc.speech([pc.Name]? Um... Come on, sleepy... It's time to wake up,)] you hear a voice shyly calling out to you, and as you slowly open your [pc.eyes], you see [npc.name] looking down at you."
												+ " Seeing that you're awake, [npc.she] breaths a little sigh of relief and hurries off to open the curtains, quietly calling out as [npc.she] does so,"
												+ " [npc.speech(Good "+morningString+", [pc.name]... I hope you have a wonderful day today...)]"));
							} else if(slaveWaking.isKind()) {
								sb.append(UtilText.parse(slaveWaking,
										"[npc.speech([pc.Name]? Come on, sleepy-head! You don't want to stay in bed forever do you?)] you hear a voice calling out to you, and as you slowly open your [pc.eyes], you see [npc.name] smiling down at you."
												+ " Seeing that you're awake, [npc.she] gives your face a gentle, loving stroke, before hurrying off to open the curtains, calling out over [npc.her] shoulder as [npc.she] does so,"
												+ " [npc.speech(Good "+morningString+", [pc.name]! I hope you have a wonderful day today!)]"));
							} else {
								sb.append(UtilText.parse(slaveWaking,
										"[npc.speech([pc.Name]? Come on, sleepy-head! You don't want to stay in bed forever do you?)] you hear a voice calling out to you, and as you slowly open your [pc.eyes], you see [npc.name] smiling down at you."
												+ " Seeing that you're awake, [npc.she] stands up and hurries off to open the curtains, calling out over [npc.her] shoulder as [npc.she] does so,"
												+ " [npc.speech(Good "+morningString+", [pc.name]! I hope you have a wonderful day today!)]"));
							}
						}
						sb.append("</p>");

						sb.append(UtilText.parse(slaveWaking,
								"<p>"
									+ "As comfortable as you are, you decide that you'd better do as [npc.name] says and get out of bed, and so, after a minute of lying beneath the covers, you do just that."
									+ " With a satisfied yawn and stretch of your [pc.arms], you allow "+ Util.stringsToStringList(names, false)+" to assist you as you gather your things, and with one final stretch, you get ready to set out once more..."
								+ "</p>"));
					}
					
				} else {
					sb.append("<p>"
								+ (soloSlave
									?UtilText.parse(charactersPresent.get(0),
											"With you and [npc.name] now in your respective positions, you set your phone's alarm and place it on the bedside cabinet beside you."
											+ " Letting out a contented sigh, you close your eyes and start to drift off to sleep...")
									:"With your slaves now being settled into their respective positions, you set your phone's alarm and place it on the bedside cabinet beside you."
										+ " Letting out a contented sigh, you close your eyes and start to drift off to sleep...")
							+ "</p>"
							+ "<p>"
								+ "[style.italics(...)]"
							+ "</p>");

					if(hornySlaves.isEmpty()) {
						sb.append(
								"<p>"
									+ "<i>Beep-beep... beep-beep... bee-</i>"
								+ "</p>"
								+ "<p>"
									+ "Rolling over, you fumble for your phone, turning off the alarm before sinking back onto your bed with a sigh."
									+ " As comfortable as you are, you decide that you'd better get out of bed, and so, after a minute of lying beneath the covers, you do just that."
									+ " With a satisfied yawn and stretch of your [pc.arms], you allow "+ Util.stringsToStringList(names, false)+" to assist you as you gather your things, and with one final stretch, you get ready to set out once more..."
								+ "</p>");
					}
				}

				if(!hornySlaves.isEmpty()) {
					Collections.shuffle(hornySlaves);
					boolean soloHornySex = hornySlaves.size()==1;
					List<String> hornyNames = new ArrayList<>();
					hornySlaves.stream().forEach((npc) -> hornyNames.add(npc.getName()));
					sb.append(UtilText.parse(hornySlaves,
							"<p>"
								+ "[npc.speech(~Mmm!~ That's right... I've got you now...)]"
							+ "</p>"
							+ "<p>"
								+ "For a moment, you aren't quite sure if you're dreaming or not, but then you suddenly feel a strange weight shifting on top of you, which instantly jolts you awake."
								+ (soloHornySex
										?" Opening your eyes, you see [npc.name] sitting on your chest, grinning hungrily down at you."
										:" Opening your eyes, you see "+Util.stringsToStringList(hornyNames, false)+" leaning over you, each of them with a hungry grin on their faces.")
								+ " With a horny [npc.moan], [npc.name] is the first to speak again, and says,"
								+ " [npc.speech(Good "+morningString+", [pc.name]! I hope you're ready to fuck!)]"
							+ "</p>"));
					return sb.toString();
				}
				
			} else {
				sb.append("<p>"
						+ "You set your phone's alarm before drawing the curtains, lying on your bed and closing your eyes."
						+ " You feel extremely safe and comfortable here in Lilaya's home, and soon drift off to sleep, thinking about all the things that have happened to you recently..."
					+ "</p>"
					+ "<p>"
						+ "<i>Beep-beep... beep-beep... bee-</i>"
					+ "</p>"
					+ "<p>"
						+ "Rolling over, you fumble for your phone, turning off the alarm before sinking back onto your bed with a sigh."
						+ " As comfortable as you are, you decide that you'd better get out of bed, and so, after a minute of lying beneath the covers, you do just that."
						+ " Opening the curtains, you set about gathering your things, and then with one final stretch, you get ready to set out once more..."
					+ "</p>");
			}
			
			if(!slavesWantingToSexPlayer(Main.game.getHourOfDay()).isEmpty()) {
				sb.append("<p style='text-align:center;'>"
							+ "[style.italicsGood(You feel completely refreshed!)]"
						+ "</p>");
			}
			
			return sb.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(!slavesWantingToSexPlayer(Main.game.getHourOfDay()).isEmpty()) {
				return null;
			}
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<GameCharacter> hornySlaves = slavesWantingToSexPlayer(Main.game.getHourOfDay());
			if(!hornySlaves.isEmpty()) {
				if(index==1) {
					boolean soloSex = hornySlaves.size()==1;
					List<String> names = new ArrayList<>();
					hornySlaves.stream().forEach((npc) -> names.add(npc.getName()));
					
					List<GameCharacter> spectators = new ArrayList<>(slavesInRoom(Main.game.getHourOfDay()));
					spectators.removeAll(hornySlaves);
					
					return new ResponseSex("Sex",
							(soloSex
								?UtilText.parse(hornySlaves.get(0), "[npc.Name] forces [npc.herself] on you...")
								:Util.stringsToStringList(names, false)+" force themselves on you..."),
							false,
							false,
							new SMGeneric(
									hornySlaves,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									spectators,
									null,
									ResponseTag.PREFER_MISSIONARY),
							POST_WAKE_UP_SEX,
							"<p>"
								+ UtilText.parse(hornySlaves,
									(soloSex
										?"Before you can react, [npc.name] lowers [npc.herself] down over the top of you, pressing [npc.her] [npc.lips+] against yours and pulling you into a passionate kiss."
											+ " Breaking it off a moment later, [npc.her] [npc.hands] start to roam down and grope your body as your horny slave [npc.moans],"
											+ " [npc.speech(Don't worry, [pc.name], I'll make this good for you as well...)]"
										:Util.stringsToStringList(names, false)+" had obviously conspired to wait until you'd gone to sleep before pinning you down and having their way with you."
												+ " Before you can react to the situation, [npc.name] lowers [npc.herself] down over the top of you, pressing [npc.her] [npc.lips+] against yours and pulling you into a passionate kiss."
												+ " Leaning in on one side of you, [npc2.name] [npc2.moans], [npc2.speech(Don't worry, [pc.name], we'll make this good for you as well...)]"))
							+ "</p>");
				}
				return null;
			}
			return getResponseRoom(responseTab, index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNode POST_WAKE_UP_SEX = new DialogueNode("Finished", "You collapse back onto your bed...", false) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			List<GameCharacter> hornySlaves = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
			boolean soloSex = hornySlaves.size()==1;
			List<String> names = new ArrayList<>();
			hornySlaves.stream().forEach((npc) -> names.add(npc.getName()));
			
			
			if(soloSex) {
				UtilText.nodeContentSB.append(
						UtilText.parse(hornySlaves,
						"<p>"
							+ "Having had [npc.her] fun with you, [npc.name] allows you to collapse back onto your bed."
							+ " Letting out a very satisfied sigh, [npc.she] [npc.moans],"
							+ " [npc.speech(See, that was fun for both of us, wasn't it?)]"
						+ "</p>"
						+ "<p>"
							+ "Interpreting your heavy breathing as a positive sign, [npc.name] smiles and asks,"
							+ " [npc.speech(Weren't you meant to be waking up at this time anyway?)]"
						+ "</p>"
						+ "<p>"
							+ "Realising that [npc.sheIs] right, you decide that you'd better get out of bed, and so, after a minute of lying back and catching your breath, you do just that."
							+ " With a sigh and stretch of your [pc.arms], you allow "+ Util.stringsToStringList(names, false)+" to assist you as you gather your things, and with one final stretch, you get ready to set out once more..."
						+ "</p>"));
				
			} else {
				UtilText.nodeContentSB.append(
						UtilText.parse(hornySlaves,
						"<p>"
							+ "Having had their fun with you, "+ Util.stringsToStringList(names, false)+" allow you to collapse back onto your bed."
							+ " Letting out a very satisfied sigh, [npc.name] [npc.moans],"
							+ " [npc.speech(See, that was fun for all of us, wasn't it?)]"
						+ "</p>"
						+ "<p>"
							+ "Interpreting your heavy breathing as a positive sign, [npc2.name] smiles and asks,"
							+ " [npc2.speech(Weren't you meant to be waking up at this time anyway?)]"
						+ "</p>"
						+ "<p>"
							+ "Realising that [npc2.sheIs] right, you decide that you'd better get out of bed, and so, after a minute of lying back and catching your breath, you do just that."
							+ " With a sigh and stretch of your [pc.arms], you allow "+ Util.stringsToStringList(names, false)+" to assist you as you gather your things, and with one final stretch, you get ready to set out once more..."
						+ "</p>"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseRoom(responseTab, index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_QUICK_SHOWER = new DialogueNode("Your Room", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Make sure that the washing slaves don't disappear during this scene:
			for(GameCharacter slave : slavesWashing) {
				slave.setLocation(Main.game.getPlayer(), false);
			}
		}
		@Override
		public String getContent() {
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<GameCharacter> slavesWashing = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_HELP_WASH)).collect(Collectors.toList());
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>");
				UtilText.nodeContentSB.append(
						"Wanting to clean yourself, but not spend too much time doing so, you step into your large ensuite bathroom and decide upon taking a quick shower."
								+ " Disrobing, you leave your clothing by the door, before stepping over to your luxurious marble-and-glass walk-in shower.");
			UtilText.nodeContentSB.append("</p>");
			
			if(!slavesWashing.isEmpty()) {
				UtilText.nodeContentSB.append(getShowerSlavesDescription(slavesWashing));
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Turning on the tap, you let out a relaxed sigh as you feel the warm water flowing down over your naked body."
							+ " Not wanting to spend too much time in the shower, you focus on quickly cleaning yourself..."
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			SexSlot[] showerSlots = new SexSlot[] {
					SexSlotStanding.STANDING_SUBMISSIVE,
					SexSlotStanding.STANDING_SUBMISSIVE_BEHIND,
					SexSlotStanding.STANDING_SUBMISSIVE_TWO,
					SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_TWO};
			
			if(index==1) {
				return new Response("Finish", "Finish having a shower and return to your room.", ROOM) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append("<p>");
							if(!slavesWashing.isEmpty()) {
									if(slavesWashing.size()==1) {
										Main.game.getTextStartStringBuilder().append(UtilText.parse(slavesWashing,
												"Not wanting to spend too much time in the shower, you soon tell [npc.name] to turn off the tap and help you to dry off."
													+ " After doing this, the two of you get dressed and step back into your room..."));
									} else {
										Main.game.getTextStartStringBuilder().append(
												"Not wanting to spend too much time in the shower, you soon tell your slaves to turn off the tap and help you to dry off."
													+ " After doing this, you all get dressed and step back into your room...");
									}
								
							} else {
								Main.game.getTextStartStringBuilder().append(
										"After a while, you feel as though you've spent enough time in the shower, and after turning off the tap and drying yourself with a fluffy towel, you get dressed and step back into your room...");
							}
						Main.game.getTextStartStringBuilder().append("</p>");
					}
				};
				
			} else if(index==2) { // If you change this, be aware that it is called in AUNT_HOME_PLAYERS_ROOM_THOROUGH_SHOWER
				if(slavesWashing.isEmpty()) {
					return new Response("Sex", "You do not have any slaves assigned to your bedroom with the washing permission, so there's nobody for you to have sex with...", null);
				}
				
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				for(int i=0 ; i<slavesWashing.size() && i<4; i++) {
					slaveSlots.put(slavesWashing.get(i), showerSlots[i]);
				}
				UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
				return new ResponseSex("Sex",
						slavesWashing.size()==1
							?UtilText.parse(slavesWashing, "Have dominant sex with [npc.name] in the shower.")
							:"Have dominant sex with your slaves in the shower.",
						true, false,
						new SMShower(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								slaveSlots),
						null,
						null,
						AFTER_SHOWER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "START_SHOWER_SEX_AS_DOM", slavesWashing));
				
			} else if(index==3) {  // If you change this, be aware that it is called in AUNT_HOME_PLAYERS_ROOM_THOROUGH_SHOWER
				if(slavesWashing.isEmpty()) {
					return new Response("Submissive sex", "You do not have any slaves assigned to your bedroom with the washing permission, so there's nobody for you to have submissive sex with...", null);
				}
				if(!slavesWashing.stream().anyMatch(s->s.isAttractedTo(Main.game.getPlayer()))) {
					return new Response("Submissive sex",
							slavesWashing.size()==1
								?UtilText.parse(slavesWashing, "As [npc.name] isn't attracted to you, [npc.she] is unwilling to be the dominant partner in sex...")
								:"Have dominant sex with your slaves in the shower.",
							null);
				}
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				List<GameCharacter> attractedSlaves = slavesWashing.stream().filter(s->s.isAttractedTo(Main.game.getPlayer())).collect(Collectors.toList());
				for(int i=0 ; i<attractedSlaves.size(); i++) {
					slaveSlots.put(attractedSlaves.get(i), showerSlots[i]);
				}
				UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
				return new ResponseSex("Submissive sex",
						attractedSlaves.size()==1
								?UtilText.parse(attractedSlaves, "Let [npc.name] dominantly fuck you in the shower.")
										:"Let your slaves dominantly fuck you in the shower.",
						true, true,
						new SMShower(SexPosition.STANDING,
								slaveSlots,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT))),
						null,
						null,
						AFTER_SHOWER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "START_SHOWER_SEX_AS_SUB", attractedSlaves));
			}
			
			return null;
		
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_THOROUGH_SHOWER = new DialogueNode("Your Room", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Make sure that the washing slaves don't disappear during this scene:
			for(GameCharacter slave : slavesWashing) {
				slave.setLocation(Main.game.getPlayer(), false);
			}
		}
		@Override
		public String getContent() {
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<GameCharacter> slavesWashing = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_HELP_WASH)).collect(Collectors.toList());
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>");
				UtilText.nodeContentSB.append(
						"Wanting to spend some time thoroughly cleaning yourself, you step into your large ensuite bathroom and decide upon taking a long shower."
						+ " Disrobing, you leave your clothing by the door, before stepping over to your luxurious marble-and-glass walk-in shower.");
			UtilText.nodeContentSB.append("</p>");
			
			if(!slavesWashing.isEmpty()) {
				UtilText.nodeContentSB.append(getShowerSlavesDescription(slavesWashing));
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Turning on the tap, you let out a contented sigh as you feel the warm water flowing down over your naked body."
							+ " Deciding that it wouldn't be so bad to take some time out in order to relax, you don't rush as you set about thoroughly cleaning yourself."
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Finish", "Finish having a shower and return to your room.", ROOM) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append("<p>");
							if(!slavesWashing.isEmpty()) {
									if(slavesWashing.size()==1) {
										Main.game.getTextStartStringBuilder().append(UtilText.parse(slavesWashing,
												"After making sure that every last inch of your body is sparkling clean, you tell [npc.name] to turn off the tap and help you to dry off."
													+ " After doing this, the two of you get dressed and step back into your room..."));
									} else {
										Main.game.getTextStartStringBuilder().append(
												"After making sure that every last inch of your body is sparkling clean, you tell your slaves to turn off the tap and help you to dry off."
													+ " After doing this, you all get dressed and step back into your room...");
									}
								
							} else {
								Main.game.getTextStartStringBuilder().append(
										"After a while, you feel as though you've spent enough time in the shower, and after turning off the tap and drying yourself with a fluffy towel, you get dressed and step back into your room...");
							}
						Main.game.getTextStartStringBuilder().append("</p>");
					}
				};
				
			} if(index==2) {
				return AUNT_HOME_PLAYERS_ROOM_QUICK_SHOWER.getResponse(responseTab, index);
				
			} else if(index==3) {
				return AUNT_HOME_PLAYERS_ROOM_QUICK_SHOWER.getResponse(responseTab, index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SHOWER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
		}
		@Override
		public String getDescription() {
			return "Having had their fun, your slaves remind you that you have other things you need to be getting on with...";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "AFTER_SHOWER_SEX", slavesWashing);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Now that you've had your fun, it's time to return to your room.", ROOM);
			}
			return null;
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_BATH = new DialogueNode("Your Room", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Make sure that the washing slaves don't disappear during this scene:
			for(GameCharacter slave : slavesWashing) {
				slave.setLocation(Main.game.getPlayer(), false);
			}
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>");
				UtilText.nodeContentSB.append(
						"Wanting to spend some time relaxing and getting cleaned, you step into your large ensuite bathroom and decide upon taking a long bath."
						+ " Disrobing, you leave your clothing by the door, before stepping over to your luxurious marble bathtub.");
			UtilText.nodeContentSB.append("</p>");
			
			if(!slavesWashing.isEmpty()) {
				UtilText.nodeContentSB.append(getBathSlavesDescription(slavesWashing));
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Turning on the taps and running yourself a bath, you slip down into the hot water and let out a contented sigh."
							+ " Deciding that it wouldn't be so bad to take some time out in order to relax, you don't rush as you set about thoroughly cleaning yourself..."
						+"</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			SexSlot[] bathSlots = new SexSlot[] {
					SexSlotLyingDown.MISSIONARY,
					SexSlotLyingDown.BESIDE,
					SexSlotLyingDown.BESIDE_TWO,
					SexSlotLyingDown.BESIDE_THREE};
			
			if(index==1) {
				return new Response("Finish", "Finish having a bath and return to your room.", ROOM) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append("<p>");
							if(!slavesWashing.isEmpty()) {
									if(slavesWashing.size()==1) {
										Main.game.getTextStartStringBuilder().append(UtilText.parse(slavesWashing,
												"After making sure that every last inch of your body is sparkling clean, you pull the plug, [pc.step] out of the bath, and tell [npc.name] to help you to dry off."
														+ " After doing this, the two of you get dressed and step back into your room..."));
									} else {
										Main.game.getTextStartStringBuilder().append(
												"After making sure that every last inch of your body is sparkling clean, you pull the plug, you [pc.step] out of the bath, and tell your slaves to help you to dry off."
														+ " After doing this, you all get dressed and step back into your room...");
									}
								
							} else {
								Main.game.getTextStartStringBuilder().append(
										"After having a nice relaxing soak, you feel as though you've spent enough time in the bath."
										+ " Pulling the plug and drying yourself off, you quickly get dressed and step back into your room...");
							}
						Main.game.getTextStartStringBuilder().append("</p>");
					}
				};
				
			} else if(index==2) {
				if(slavesWashing.isEmpty()) {
					return new Response("Sex", "You do not have any slaves assigned to your bedroom with the washing permission, so there's nobody for you to have sex with...", null);
				}
				
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				for(int i=0 ; i<slavesWashing.size() && i<4; i++) {
					slaveSlots.put(slavesWashing.get(i), i==0?SexSlotLyingDown.LYING_DOWN:bathSlots[i]);
				}
				UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
				return new ResponseSex("Sex",
						slavesWashing.size()==1
							?UtilText.parse(slavesWashing, "Have dominant sex with [npc.name] in the bath.")
							:"Have dominant sex with your slaves in the bath.",
						true, false,
						new SMBath(SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
								slaveSlots),
						null,
						null,
						AFTER_BATH_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "START_BATH_SEX_AS_DOM", slavesWashing));
				
			} else if(index==3) {
				if(slavesWashing.isEmpty()) {
					return new Response("Submissive sex", "You do not have any slaves assigned to your bedroom with the washing permission, so there's nobody for you to have submissive sex with...", null);
				}
				if(!slavesWashing.stream().anyMatch(s->s.isAttractedTo(Main.game.getPlayer()))) {
					return new Response("Submissive sex",
							slavesWashing.size()==1
								?UtilText.parse(slavesWashing, "As [npc.name] isn't attracted to you, [npc.she] is unwilling to be the dominant partner in sex...")
								:"Have dominant sex with your slaves in the bath.",
							null);
				}
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				List<GameCharacter> attractedSlaves = slavesWashing.stream().filter(s->s.isAttractedTo(Main.game.getPlayer())).collect(Collectors.toList());
				for(int i=0 ; i<attractedSlaves.size(); i++) {
					slaveSlots.put(attractedSlaves.get(i), bathSlots[i]);
				}
				UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
				return new ResponseSex("Submissive sex",
						attractedSlaves.size()==1
								?UtilText.parse(attractedSlaves, "Let [npc.name] dominantly fuck you in the bath.")
										:"Let your slaves dominantly fuck you in the bath.",
						true, true,
						new SMBath(SexPosition.LYING_DOWN,
								slaveSlots,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
						null,
						null,
						AFTER_BATH_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "START_BATH_SEX_AS_SUB", attractedSlaves));
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_BATH_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_BATH, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_BATH, 240+30);
		}
		@Override
		public String getDescription() {
			return "Having had their fun, your slaves remind you that you have other things you need to be getting on with...";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "AFTER_BATH_SEX", slavesWashing);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Now that you've had your fun, it's time to return to your room.", ROOM);
			}
			return null;
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_MAKEUP = new DialogueNode("Hairstyle & Makeup", "", true) {
		@Override
		public void applyPreParsingEffects() {
			BodyChanging.setTarget(getMakeupTarget());
		}
		@Override
		public String getHeaderContent() {
			return MiscDialogue.getMakeupDialogue(true,
					BodyChanging.getTarget().isPlayer()
						?"You sit down in front of the mirror and prepare to get started on improving your appearance..."
						:UtilText.parse(BodyChanging.getTarget(), "You get [npc.name] to sit down in front of the mirror and prepare to get started on improving [npc.her] appearance...")).getHeaderContent();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Finish", "Finish doing your makeup in the mirror and return to your bedroom.", ROOM);
			}
			return null;
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR = new DialogueNode("Calendar", "", true) {
		@Override
		public void applyPreParsingEffects() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
					+ "You step over to one side of your room, where a calendar has been pinned to the wall."
					+ " It's quite obviously enchanted, for as you flick through the pages, you discover that each month's picture changes based on your current train of thought.");
						
			if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
				sb.append(" As you think about each month, a thematically-dressed man, incubus, or some kind of animal-boy appears on the page.");
			} else {
				sb.append(" As you think about each month, a thematically-dressed woman, succubus, or some kind of animal-girl appears on the page.");
			}
			
			if(Main.game.getPlayer().getCorruptionLevel()==CorruptionLevel.ZERO_PURE) {
				sb.append(" The more you flick back and forth through the calendar, the more scantily-dressed the subject of each picture becomes, until you suddenly realise what you're doing and step back, shocked.");
			} else {
				sb.append(" The more you flick back and forth through the calendar, the more scantily-dressed the subject of each picture becomes, and you find yourself getting a little turned on...");
			}
			sb.append("</p>");
			
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.knowsDate)) {
				sb.append("<p>"
						+ "Suddenly remembering what it was that you wanted to look at, you scan through the calendar to find the current date,");
			} else {
				sb.append("<p>"
						+ "You were so distracted by the changing pictures that you momentarily forgot what it was that you wanted to check."
						+ " Shaking your head, you flip back through the calendar to find out what the current date is,");
			}
			
			sb.append(" and see that it's the <b style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>"
						+ Units.date(Main.game.getDateNow(), Units.DateType.LONG)
					+"</b>. From a quick calculation "+(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_ARCANE)<IntelligenceLevel.ONE_AVERAGE.getMaximumValue()?"(with some help from your phone's calculator)":"")
					+ ", you figure out that it's been <b style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>"+Main.game.getDayNumber()+" day"+(Main.game.getDayNumber()>1?"s":"")+"</b> since you appeared in this world."
					+ "</p>");
			
			if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.knowsDate)) {
				sb.append("<p>"
						+ "[pc.thought(Wait... "+Main.game.getDateNow().format(DateTimeFormatter.ofPattern("yyyy", Locale.ENGLISH))+"?! I need to check in with Lilaya about that...)]"
						+ "</p>");
			}
			
			sb.append("<p>"
					+ "You notice that on each page of the calendar, there's a few paragraphs detailing the events that occur during that month."
					+ "</p>");
			
			Main.game.getTextStartStringBuilder().append(sb.toString());
			
			Main.game.getDialogueFlags().values.add(DialogueFlagValue.knowsDate);
		}
		@Override
		public String getContent() {
			return "";
		}

//		@Override
//		public String getResponseTabTitle(int index) {
//			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
//		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
//			if(responseTab==1) {
//				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
//			}
			if (index == 0) {
				return new Response("Back", "Step away from the calendar.", ROOM);
			} else if(index==1) {
				return new Response("January", "Read the information on January's page. [style.italicsMinorBad(There are currently no special events during January.)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_JANUARY);
			} else if(index==2) {
				return new Response("February", "Read the information on February page. [style.italicsMinorBad(There are currently no special events during February.)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_FEBRUARY);
			} else if(index==3) {
				return new Response("March", "Read the information on March's page. [style.italicsMinorBad(There are currently no special events during March.)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_MARCH);
			} else if(index==4) {
				return new Response("April", "Read the information on April's page. [style.italicsMinorBad(There are currently no special events during April.)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_APRIL);
			} else if(index==5) {
				return new Response("May", "Read the information on May's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_MAY);
			} else if(index==6) {
				return new Response("June", "Read the information on June's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_JUNE);
			} else if(index==7) {
				return new Response("July", "Read the information on July's page. [style.italicsMinorBad(There are currently no special events during July.)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_JULY);
			} else if(index==8) {
				return new Response("August", "Read the information on August's page. [style.italicsMinorBad(There are currently no special events during August.)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_AUGUST);
			} else if(index==9) {
				return new Response("September", "Read the information on September's page. [style.italicsMinorBad(There are currently no special events during September.)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_SEPTEMBER);
			} else if(index==10) {
				return new Response("October", "Read the information on October's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_OCTOBER);
			} else if(index==11) {
				return new Response("November", "Read the information on November's page. [style.italicsMinorBad(There are currently no special events during November.)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_NOVEMBER);
			} else if(index==12) {
				return new Response("December", "Read the information on December's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_DECEMBER);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_JANUARY = new DialogueNode("Calendar", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.JANUARY));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>"
							+ "[style.italicsMinorBad(There are currently no special events during January.)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("January", "You are already reading the calendar's page concerning the month of January.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_FEBRUARY = new DialogueNode("Calendar", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.FEBRUARY));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>"
							+ "[style.italicsMinorBad(There are currently no special events during February.)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("February", "You are already reading the calendar's page concerning the month of February.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_MARCH = new DialogueNode("Calendar", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.MARCH));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>"
							+ "[style.italicsMinorBad(There are currently no special events during March.)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==3) {
				return new Response("March", "You are already reading the calendar's page concerning the month of March.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_APRIL = new DialogueNode("Calendar", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.APRIL));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>"
							+ "[style.italicsMinorBad(There are currently no special events during April.)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==4) {
				return new Response("April", "You are already reading the calendar's page concerning the month of April.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_MAY = new DialogueNode("Calendar", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.MAY));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
						+ "<span style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>May</span>"
					+ "</h4>"
					+ "<h6 style='text-align:center;'>"
						+ "<span style='color:"+PresetColour.BASE_PINK_LIGHT.toWebHexString()+";'>Mother's Week</span>"
						+ "<br/>"
						+ "8th-14th May"
					+ "</h6>"
					+ "<p><i>"
						+ "The second week of May is a time in which to celebrate mothers, motherhood, and the nature of the maternal bond between mother and child."
						+ " During this time, fertility-enhancing consumables are generously provided free of charge for all residents of Dominion, and are handed out by volunteers down the main boulevards."
						+ " In this way, Lilith shows her love for mothers, and ensures that many more will be made!"
					+ "</i></p>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==5) {
				return new Response("May", "You are already reading the calendar's page concerning the month of May.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_JUNE = new DialogueNode("Calendar", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.JUNE));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
						+ "<span style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>June</span>"
					+ "</h4>"
					+"<h6 style='text-align:center;'>"
						+ "<span style='color:"+PresetColour.BASE_BLUE.toWebHexString()+";'>Father's Week</span>"
						+ "<br/>"
						+ "15th-21st June"
					+ "</h6>"
					+ "<p><i>"
						+ "The third week of June is a time in which to celebrate fathers, fatherhood, and the nature of the paternal bond between father and child."
						+ " During this time, fertility-enhancing consumables are generously provided free of charge for all residents of Dominion, and are handed out by volunteers down the main boulevards."
						+ " In this way, Lilith shows her love for fathers, and ensures that many more will be made!"
					+ "</i></p>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==6) {
				return new Response("June", "You are already reading the calendar's page concerning the month of June.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_JULY = new DialogueNode("Calendar", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.JULY));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>"
							+ "[style.italicsMinorBad(There are currently no special events during July.)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==7) {
				return new Response("July", "You are already reading the calendar's page concerning the month of July.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_AUGUST = new DialogueNode("Calendar", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.AUGUST));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>"
							+ "[style.italicsMinorBad(There are currently no special events during August.)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==8) {
				return new Response("August", "You are already reading the calendar's page concerning the month of August.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_SEPTEMBER = new DialogueNode("Calendar", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.SEPTEMBER));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>"
							+ "[style.italicsMinorBad(There are currently no special events during September.)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==9) {
				return new Response("September", "You are already reading the calendar's page concerning the month of September.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_OCTOBER = new DialogueNode("Calendar", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.OCTOBER));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
							+ "<span style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>October</span>"
					+ "</h4>"
					+"<h6 style='text-align:center;'>"
						+ "<span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Lilith's Month</span>"
						+ "<br/>"
						+ "All Month"
					+ "</h6>"
					+ "<p><i>"
						+ "October was chosen by Lilith herself to be the month in which all of Dominion shows their devotion towards their glorious queen!"
						+ " Banners and ribbons, typically in Lilith's traditional colours of orange, purple, and black, are proudly flown from every building, in order to show our queen just how devoted her subjects are!"
						+ " While all citizens are expected to celebrate Lilith's rule, the most devout of her followers dress up in traditional demonic costumes in order to prove their loyalty."
					+ "</p>"
					+ "<p>"
						+ "The officially sanctioned 'Cult of Lilith' is the most fanatical group of our queen's supporters, and are very easy to spot during October,"
							+ " as they refuse to wear anything but traditional witch's outfits, of the sort worn by Lilith herself in centuries past."
						+ " While content to carry out their acts of devotion in private for the rest of the year, these cultists can get quite zealous during October,"
							+ " and will sometimes even go so far as to approach members of the public and demand a display of loyalty from them!"
					+ "</i></p>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==10) {
				return new Response("October", "You are already reading the calendar's page concerning the month of October.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_NOVEMBER = new DialogueNode("Calendar", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.NOVEMBER));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>"
							+ "[style.italicsMinorBad(There are currently no special events during November.)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==11) {
				return new Response("November", "You are already reading the calendar's page concerning the month of November.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_DECEMBER = new DialogueNode("Calendar", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.DECEMBER));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
							+ "<span style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>December</span>"
					+ "</h4>"
					+ "<h6 style='text-align:center;'>"
						+ "<span style='color:"+PresetColour.BASE_GOLD.toWebHexString()+";'>Yuletide</span>"
						+ "<br/>"
						+ "All Month"
					+ "</h6>"
					+ "<i>"
					+ "<p>"
						+ "The celebration of Yuletide is held throughout the month of December, and sometimes even drags on through January and February!"
						+ " Giving gifts, holding feasts, and throwing parties are the ways in which Yuletide is celebrated."
						+ " As this celebration coincides with the arrival of the reindeer-morphs in Dominion, it has become tradition for the gifts given during Yuletide to be items purchased from these reindeer-morphs."
					+ "</p>"
					+ "<p>"
						+ "The figure associated with this season is the Lilin 'J&oacute;lnir' (meaning 'the Yule one', or 'Yule figure')."
						+ " Not much is known about this Lilin, other than the obvious fact that their name breaks with the tradition of all Lilin's names beginning with an 'L', and that they are the leader of the 'Wild Hunt'."
					+ "</p>"
					+ "<p>"
						+ "Consisting of a wandering horde of summoned arcane elementals, the 'Wild Hunt' was driven away from Dominion many years ago, and is now only found during Yuletide out in the Foloi Fields and the forests nearby."
					+ "</p>"
					+ "</i>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==12) {
				return new Response("December", "You are already reading the calendar's page concerning the month of December.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};
	

	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME = new DialogueNode("Your Room", "", true) {

		@Override
		public int getSecondsPassed() {
			return 30*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME", NightlifeDistrict.getClubbersPresent());
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Sex (dom)", UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Have dominant sex with [npc.name]."),
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(NightlifeDistrict.getClubbersPresent().get(0)),
						null,
						null), BACK_HOME_AFTER_CLUBBER_SEX, UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEX_AS_DOM", NightlifeDistrict.getClubbersPresent()));
				
			} else if(index==2) {
				return new ResponseSex("Sex (sub)", UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Have submissive sex with [npc.name]."),
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(NightlifeDistrict.getClubbersPresent().get(0)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null), BACK_HOME_AFTER_CLUBBER_SEX, UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEX_AS_SUB", NightlifeDistrict.getClubbersPresent()));
				
			} else if(index==4) {
				return new Response("Say goodbye",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that you've changed your mind, sending [npc.herHim] home with the promise of seeing [npc.herHim] at the club another time."
								+ "</br>[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
						AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEND_HOME) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_CHANGE_MIND", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==5) {
				return new Response("Send home",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that you've changed your mind and abruptly send [npc.herHim] home."
								+ "</br>[style.italicsBad(Removes this character from the game.)]"),
						AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEND_HOME) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_CHANGE_MIND_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode BACK_HOME_AFTER_CLUBBER_SEX = new DialogueNode("Your Room", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(NightlifeDistrict.getPartner())>=NightlifeDistrict.getPartner().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_CLUBBER_SEX", NightlifeDistrict.getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_CLUBBER_SEX_NO_ORGASM", NightlifeDistrict.getClubbersPresent());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("See again",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that you hope to see [npc.herHim] again.</br>"
								+ "[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
						BACK_HOME_AFTER_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_SEX_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.saveClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==2) {
				return new Response("Hope not (gentle)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Make a non-committal response, secretly hoping that you won't see [npc.name] again.</br>[style.italicsBad(Removes this character from the game.)]"),
						BACK_HOME_AFTER_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_SEX_DO_NOT_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==3) {
				return new Response("Hope not (harsh)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Crudely tell [npc.name] that you were only interested in fucking [npc.herHim].</br>[style.italicsBad(Removes this character from the game.)]"),
						BACK_HOME_AFTER_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_SEX_DO_NOT_SEE_AGAIN_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode BACK_HOME_AFTER_SEX = new DialogueNode("Your Room", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROOM.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEND_HOME = new DialogueNode("Your Room", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROOM.getResponse(responseTab, index);
		}
	};
}
