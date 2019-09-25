package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevelBasic;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevelBasic;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.OccupantDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.nightlife.NightlifeDistrict;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.SlaveJob;
import com.lilithsthrone.game.occupantManagement.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.SlavePermissionSetting;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.75
 * @version 0.3.5
 * @author Innoxia
 */
public class RoomPlayer {
	
	private static int sleepTimer = 240;
	
	public static void applyWellRestedStatusEffect() {
		Main.game.getPlayer().removeStatusEffect(StatusEffect.WELL_RESTED);
		Main.game.getPlayer().removeStatusEffect(StatusEffect.WELL_RESTED_BOOSTED);
		Main.game.getPlayer().removeStatusEffect(StatusEffect.WELL_RESTED_BOOSTED_EXTRA);
		
		boolean neet = Main.game.getPlayer().hasTrait(Perk.JOB_UNEMPLOYED, true);
		boolean emperorBed = Main.game.getPlayerCell().getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_PLAYER_ROOM_BED);
		StatusEffect restedEffect = StatusEffect.WELL_RESTED;
		if(neet) {
			if(emperorBed) {
				restedEffect = StatusEffect.WELL_RESTED_BOOSTED_EXTRA;
			} else {
				restedEffect = StatusEffect.WELL_RESTED_BOOSTED;
			}
		} else if(emperorBed) {
			restedEffect = StatusEffect.WELL_RESTED_BOOSTED;
		}
		Main.game.getPlayer().addStatusEffect(restedEffect, ((neet?8:6)*60*60) + 240);
	}

	private static int getSecondsUntilMorningOrEvening() {
		int minutesPassed = (int) (Main.game.getMinutesPassed() % (24 * 60));
		
		return (Main.game.isDayTime()
				? (int) ((60 * 21) - minutesPassed)
				: (int) ((60 * (minutesPassed<(60*7)?7:31)) - minutesPassed));
	}
	
	private static Response getResponseRoom(int responseTab, int index) {
		if(responseTab==1) {
			return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
		}
		
		if(index==0) {
			return null;
			
		} else if (index == 1) {
			return new Response("Rest",
					"Rest for four hours. As well as replenishing your "+Attribute.HEALTH_MAXIMUM.getName()+" and "+Attribute.MANA_MAXIMUM.getName()+", you will also get the 'Well Rested' status effect.",
					AUNT_HOME_PLAYERS_ROOM_SLEEP){
				@Override
				public void effects() {
					sleepTimer = 240;
					
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					Main.game.getPlayer().setLustNoText(0);
					
					applyWellRestedStatusEffect();
				}
			};

		} else if (index == 2) {
			int timeUntilChange = getSecondsUntilMorningOrEvening();
			return new Response("Rest until " + (Main.game.isDayTime() ? "Evening" : "Morning"),
					"Rest for " + (timeUntilChange >= 60 ?timeUntilChange / 60 + " hours " : " ")
						+ (timeUntilChange % 60 != 0 ? timeUntilChange % 60 + " minutes" : "")
						+ " until " + (Main.game.isDayTime() ? "evening (21:00)." : "morning (07:00).")
						+ " As well as replenishing your "+Attribute.HEALTH_MAXIMUM.getName()+" and "+Attribute.MANA_MAXIMUM.getName()+", you will also get the 'Well Rested' status effect.",
						AUNT_HOME_PLAYERS_ROOM_SLEEP){
				@Override
				public void effects() {
					sleepTimer = getSecondsUntilMorningOrEvening();
					
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					Main.game.getPlayer().setLustNoText(0);
					
					applyWellRestedStatusEffect();
				}
			};

		} else if (index == 3) {
			return new Response("Quick wash",
					"Use your room's ensuite to take a bath or shower. Rose will come and clean your clothes while you wash yourself."
							+ " [style.italicsGood(This will clean <b>a maximum of "+Units.fluid(500)+"</b> of fluids out of all your orifices.)]"
									+ " [style.italicsExcellent(This will clean all of your equipped clothing.)]",
					AUNT_HOME_PLAYERS_ROOM_WASH){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					
					
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().washAllOrifices(false));
					Main.game.getPlayer().calculateStatusEffects(0);
					Main.game.getPlayer().cleanAllDirtySlots();
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().cleanAllClothing(false));
				}
				@Override
				public int getSecondsPassed() {
					return 10*60;
				}
			};

		} else if (index == 4) {
			return new Response("Thorough wash",
					"Use your room's en-suite to take a bath or shower, in which you will thoroughly clean yourself. Rose will come and clean your clothes while you wash yourself."
							+ " [style.italicsExcellent(This will clean <b>all</b> fluids out of all your orifices.)]"
							+ " [style.italicsExcellent(This will clean <b>all</b> clothing in your inventory.)]",
					AUNT_HOME_PLAYERS_ROOM_WASH){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().washAllOrifices(true));
					Main.game.getPlayer().calculateStatusEffects(0);
					Main.game.getPlayer().cleanAllDirtySlots();
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().cleanAllClothing(true));
				}
				@Override
				public int getSecondsPassed() {
					return 30*60;
				}
			};

		} else if (index == 5) {
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.knowsDate)) {
				return new Response("Calendar", "Take another look at the enchanted calendar that's pinned up on one wall.", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
			} else {
				return new Response("<span style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Calendar</span>", "There's a calendar pinned up on one wall. Take a closer look at it.", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
			}
			
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
						return OccupantManagementDialogue.getSlaveryRoomListDialogue(null);
					}
				};
			} else {
				return new Response("Manage people", "You'll either need a slaver license, or permission from Lilaya to house your friends, before you can access this menu!",  null);
			}
			
		}
		
		List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
		
		int indexPresentStart = 8;
		if(index-indexPresentStart<charactersPresent.size()) {
			NPC character = charactersPresent.get(index-indexPresentStart);
			return new Response(
					UtilText.parse(character, "[npc.Name]"),
					UtilText.parse(character, "Interact with [npc.name]."),
					character.isSlave()
						?SlaveDialogue.SLAVE_START
						:OccupantDialogue.OCCUPANT_START) {
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
		return null;
	}

	public static final DialogueNode ROOM = new DialogueNode("Your Room", "", false) {

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			GenericPlace place = Main.game.getPlayerCell().getPlace();
			
			sb.append("<p>"
					+ "Your bedroom is positioned close to the main staircase linking the entrance hall to the first-floor corridor, and is one of the largest chambers in the entire mansion."
					+ " Opposite the room's main doorway, a set of four large, sach windows provide an excellent view of the courtyard garden below, while off to the left, another door leads through into your private ensuite bathroom."
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
			
			sb.append(LilayaHomeGeneric.getRoomModificationsDescription());

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
					sb.append(" Having been instructed to greet you upon your arrival, "+(soloSlave?UtilText.parse(charactersPresent.get(0), "[npc.she] steps forwards"):Util.stringsToStringList(names, false)+" step forwards")+" and welcome you back."
							+ "</p>");

					List<NPC> greetingsNice = greetings.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
					for(NPC npc : greetingsNice) {
						sb.append("<p>");
						if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
							sb.append(UtilText.parse(npc, "[npc.speech(Welcome back, [pc.name],)] [npc.name] says, "+(npc.isFeminine()?"curtsying respectfully towards you.":"bowing respectfully towards you.")));
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
							sb.append(UtilText.parse(npc, "[npc.speech(Back so soon, [pc.name]?)] [npc.name] coyly asks, "+(npc.isFeminine()?"before biting [npc.her] lip and winking at you.":"before flashing you a charming smile.")));
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
							sb.append(UtilText.parse(npc, "[npc.speech(You're back to fuck, right, [pc.name]?)] [npc.name] bluntly asks, "+(npc.isFeminine()?"[npc.speech(I'm always horny for you...)]":"[npc.speech(I want you so badly right now.)]")));
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)) {
							if(npc.isShy()) {
								sb.append(UtilText.parse(npc, "[npc.speech(Hello, [pc.name],)] [npc.name] says, looking at the floor and shyly shuffling [npc.her] [npc.feet]."));
							} else if(npc.isKind()) {
								sb.append(UtilText.parse(npc, "[npc.speech(Welcome back, [pc.name],)] [npc.name] says, [npc.speech(How are you? Is there anything I can do for you?)]"));
							} else {
								sb.append(UtilText.parse(npc, "[npc.speech(Hello, [pc.name],)] [npc.name] says, [npc.speech(I hope everything in here is to your liking.)]"));
							}
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
							if(npc.isShy()) {
								sb.append(UtilText.parse(npc,
										"[npc.speech(Hello, [pc.name],)] [npc.name] says, looking at the floor and shyly shuffling [npc.her] [npc.feet], before continuing, [npc.speech(I'm so happy that you're back; I really missed you...)]"));
							} else if(npc.isKind()) {
								sb.append(UtilText.parse(npc,
										"[npc.speech(Welcome back, [pc.name],)] [npc.name] says, smiling happily at you for a moment, before continuing, [npc.speech(It's so good to see you again; it felt like you were gone forever...)]"));
							} else {
								sb.append(UtilText.parse(npc,
										"[npc.speech(Welcome back, [pc.name],)] [npc.name] cheerfully says, before continuing, [npc.speech(I'm so happy to see you again!)]"));
							}
						}
						sb.append("</p>");
					}
					
					List<NPC> greetingsRude = new ArrayList<>(greetings);
					greetingsRude.removeAll(greetingsNice);
					for(NPC npc : greetingsRude) {
						sb.append("<p>");
							if(npc.isShy()) {
								sb.append(UtilText.parse(npc,
										"[npc.speech(Oh, it's just you,)] [npc.name] says, looking at the floor and cursing under [npc.her] breath, [npc.speech(Why did [pc.she] have to come back...)]"));
							} else if(npc.isSelfish()) {
								sb.append(UtilText.parse(npc,
										"[npc.speech(Eugh, what do you want now, [pc.name]?)] [npc.name] asks."));
							} else {
								sb.append(UtilText.parse(npc,
										"[npc.speech(Just do whatever it is you're here to do, [pc.name],)] [npc.name] says, rolling [npc.her] [npc.eyes] in annoyance."));
							}
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
	
	private static List<GameCharacter> slavesWantingToSexPlayer() {
		List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
		return charactersPresent.stream().filter((slave) -> 
			slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER)
					&& slave.isAttractedTo(Main.game.getPlayer())
					&& slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE)).collect(Collectors.toList());
	}
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_SLEEP = new DialogueNode("Your Room", "", false) {

		@Override
		public boolean isTravelDisabled() {
			return !slavesWantingToSexPlayer().isEmpty();
		}
		
		@Override
		public int getSecondsPassed() {
			return sleepTimer*60;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			
			if(!charactersPresent.isEmpty()) {
				boolean soloSlave = charactersPresent.size()==1;
				List<NPC> slavesToWakePlayer = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_WAKE_UP)).collect(Collectors.toList());
				List<String> names = new ArrayList<>();
				charactersPresent.stream().forEach((npc) -> names.add(npc.getName()));

				sb.append("<p>"
						+ "Feeling tired and in need of some rest, you head over to your bed and collapse down onto the mattress, before pulling back the covers and slipping beneath them."
						+ " Realising that you want to go to sleep, "+Util.stringsToStringList(names, false)+(soloSlave?" quickly draws the curtains":" quickly draw the curtains")+", before stepping back over towards you."
					+ "</p>");

				// Sleeping arrangements:
				List<NPC> floorSlaves = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_SLEEP_FLOOR)).collect(Collectors.toList());
				List<NPC> onBedSlaves = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_SLEEP_ON_BED)).collect(Collectors.toList());
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

				List<NPC> inBedSlaves = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_SLEEP_IN_BED)).collect(Collectors.toList());
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
					
					List<NPC> niceSlaves = inBedSlaves.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
					for(NPC npc : niceSlaves) {
						sb.append("<p>");
						if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
							sb.append(UtilText.parse(npc, "[npc.speech(Allow me to keep you warm, [pc.name],)] [npc.name] offers, before snuggling up against you."));
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
							sb.append(UtilText.parse(npc, "[npc.speech(Perhaps we could do something else in this bed after you wake up?)] [npc.name] teases, pressing [npc.herself] against you."));
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
							if(npc.hasBreasts()) {
								sb.append(UtilText.parse(npc, "[npc.speech(You can use my tits as a pillow if you want,)] [npc.name] offers, pressing [npc.her] [npc.breastSize] breasts in against you."));
							} else {
								sb.append(UtilText.parse(npc, "[npc.speech(We can have a good fuck after you've slept, right?)] [npc.name] bluntly asks, reaching down under the covers to stroke your [pc.leg]."));
							}
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)) {
							if(npc.isShy()) {
								sb.append(UtilText.parse(npc, "[npc.speech(Erm, [pc.name], just let me know if I'm taking up too much room,)] [npc.name] says, blushing."));
							} else {
								sb.append(UtilText.parse(npc, "[npc.speech(Let me get a little closer,)] [npc.name] says, nestling in against your [pc.chest]."));
							}
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
							if(npc.isShy()) {
								sb.append(UtilText.parse(npc,
										"[npc.speech(I love getting to sleep with you, [pc.name],)] [npc.name] says, blushing profusely in the process."));
							} else if(npc.isKind()) {
								sb.append(UtilText.parse(npc,
										"[npc.speech(If there's anything you need, don't hesitate to ask,)] [npc.name] says, smiling happily as [npc.she] snuggles up against you."));
							} else {
								sb.append(UtilText.parse(npc,
										"[npc.speech(This really is the best,)] [npc.name] says, smiling at you as [npc.she] hugs you under the covers."));
							}
						}
						if(npc.getTailType().isPrehensile()) {
							sb.append(UtilText.parse(npc, " With a satisfied sigh, the [npc.race] moves [npc.her] [npc.tail+] around beneath the covers, before laying it over your body and using it to give you a loving tail-hug."));
						}
						sb.append("</p>");
					}
					
					List<NPC> rudeSlaves = new ArrayList<>(inBedSlaves);
					rudeSlaves.removeAll(niceSlaves);
					for(NPC npc : rudeSlaves) {
						sb.append("<p>");
							if(npc.isShy()) {
								sb.append(UtilText.parse(npc,
										"[npc.speech(Please respect my side of the bed,)] [npc.name] nervously requests, before rolling over and turning [npc.her] back to you."));
							} else if(npc.isSelfish()) {
								sb.append(UtilText.parse(npc,
										"[npc.speech(Don't roll over into my personal space in your sleep,)] [npc.name] warns, letting out an annoying huff as [npc.she] shuffles away from you.."));
							} else {
								sb.append(UtilText.parse(npc,
										"[npc.speech(I'm just going to keep to my part of the bed,)] [npc.name] sighs, shuffling away a little before rolling over and turning [npc.her] back on you."));
							}
						sb.append("</p>");
					}
				}

				
				if(!slavesToWakePlayer.isEmpty()) {
					NPC slaveWaking = Util.randomItemFrom(slavesToWakePlayer);
					sb.append("<p>"
							+ (soloSlave
								?UtilText.parse(slaveWaking,
									"With you and [npc.name] now in your respective positions, you ask [npc.herHim] to wake you at the time that you'd like to be getting up."
										+ " After [npc.sheHas] reassured you that [npc.she] won't let you sleep in too late, you let out a contented sigh, close your eyes, and start to drift off to sleep...")
								:"With your slaves now being settled into their respective positions, you ask [npc.name] to wake you at the time that you'd like to be getting up."
										+ " After [npc.sheHas] reassured you that [npc.she] won't let you sleep in too late, you let out a contented sigh, close your eyes, and start to drift off to sleep...")
						+ "</p>"
						+ "<p>"
							+ "[style.italics(...)]"
						+ "</p>");

					if(slavesWantingToSexPlayer().isEmpty()) {
						sb.append("<p>");
						if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
							sb.append(UtilText.parse(slaveWaking,
									"[npc.speech([pc.Name]? It's the appointed hour for you to be waking up,)] you hear a voice calling out to you, and as you slowly open your [pc.eyes], you see [npc.name] smiling down at you."
									+ " Seeing that you're awake, [npc.she] withdraws to open the curtains, calling over [npc.her] shoulder as [npc.she] does so, [npc.speech(Good [style.morning], [npc.name]!)]"));
						} else if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
							sb.append(UtilText.parse(slaveWaking, 
									"[npc.speech(Come on, [pc.name], it's time to wake up,)] you hear a voice seductively whispering into your [pc.ear], and as you slowly open your [pc.eyes], you see [npc.name] smiling down at you and biting [npc.her] lip."
									+ " Seeing that you're awake, [npc.she] runs a hand over your [pc.chest], before withdrawing to open the curtains. Calling over [npc.her] shoulder as [npc.she] does so, [npc.she] teases,"
									+ " [npc.speech(Good [style.morning], [npc.name]... So, is there anything <i>special</i> that you wanted to do today?)]"));
						} else if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
							sb.append(UtilText.parse(slaveWaking,
									"[npc.speech(If you get up now, you can make time for a quick fuck, can't you?)] you hear a voice asking, and as you slowly open your [pc.eyes], you see [npc.name] hungrily gazing down at you."
									+ " Seeing that you're awake, [npc.she] leans down to kiss you fully on the [pc.lips], before withdrawing to open the curtains. Calling over [npc.her] shoulder as [npc.she] does so, [npc.she] teases,"
									+ " [npc.speech(Good [style.morning], [npc.name]! So, you want to fuck me now, right?)]"));
						} else if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)) {
							if(slaveWaking.isShy()) {
								sb.append(UtilText.parse(slaveWaking,
										"[npc.speech([pc.Name]? Erm, excuse me, [pc.name]? It's time to wake up,)] you hear a voice nervously calling out to you, and as you slowly open your [pc.eyes], you see [npc.name] worriedly looking down at you."
											+ " Seeing that you're awake, [npc.she] breaths a little sigh of relief and hurries off to open the curtains, calling out as [npc.she] does so, [npc.speech(Good [style.morning], [npc.name]!)]"));
							} else {
								sb.append(UtilText.parse(slaveWaking, 
										"[npc.speech([pc.Name]? Come on, [pc.name], it's time to wake up,)] you hear a voice calling out to you, and as you slowly open your [pc.eyes], you see [npc.name] smiling down at you."
												+ " Seeing that you're awake, [npc.she] withdraws to open the curtains, calling over [npc.her] shoulder as [npc.she] does so, [npc.speech(Good [style.morning], [npc.name]!)]"));
							}
						} else if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
							if(slaveWaking.isShy()) {
								sb.append(UtilText.parse(slaveWaking,
										"[npc.speech([pc.Name]? Come on, sleepy... Erm, it's time to wake up,)] you hear a voice shyly calling out to you, and as you slowly open your [pc.eyes], you see [npc.name] looking down at you."
												+ " Seeing that you're awake, [npc.she] breaths a little sigh of relief and hurries off to open the curtains, quietly calling out as [npc.she] does so,"
												+ " [npc.speech(Good [style.morning], [npc.name]... I hope you have a wonderful day today...)]"));
							} else if(slaveWaking.isKind()) {
								sb.append(UtilText.parse(slaveWaking,
										"[npc.speech([pc.Name]? Come on, sleepy-head! You don't want to stay in bed forever do you?)] you hear a voice calling out to you, and as you slowly open your [pc.eyes], you see [npc.name] smiling down at you."
												+ " Seeing that you're awake, [npc.she] gives your face a gentle, loving stroke, before hurrying off to open the curtains, calling out over [npc.her] shoulder as [npc.she] does so,"
												+ " [npc.speech(Good [style.morning], [npc.name]! I hope you have a wonderful day today!)]"));
							} else {
								sb.append(UtilText.parse(slaveWaking,
										"[npc.speech([pc.Name]? Come on, sleepy-head! You don't want to stay in bed forever do you?)] you hear a voice calling out to you, and as you slowly open your [pc.eyes], you see [npc.name] smiling down at you."
												+ " Seeing that you're awake, [npc.she] stands up and hurries off to open the curtains, calling out over [npc.her] shoulder as [npc.she] does so,"
												+ " [npc.speech(Good [style.morning], [npc.name]! I hope you have a wonderful day today!)]"));
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

					if(slavesWantingToSexPlayer().isEmpty()) {
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

				List<GameCharacter> hornySlaves = slavesWantingToSexPlayer();
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
								+ " [npc.speech(Good [style.morning], [pc.name]! I hope you're ready to fuck!)]"
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
			
			if(!slavesWantingToSexPlayer().isEmpty()) {
				sb.append("<p style='text-align:center;'>"
							+ "[style.italicsGood(You feel completely refreshed!)]"
						+ "</p>");
			}
			
			return sb.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(!slavesWantingToSexPlayer().isEmpty()) {
				return null;
			}
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<GameCharacter> hornySlaves = slavesWantingToSexPlayer();
			if(!hornySlaves.isEmpty()) {
				if(index==1) {
					boolean soloSex = hornySlaves.size()==1;
					List<String> names = new ArrayList<>();
					hornySlaves.stream().forEach((npc) -> names.add(npc.getName()));
					
					List<GameCharacter> spectators = new ArrayList<>(LilayaHomeGeneric.getSlavesAndOccupantsPresent());
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
			
			List<GameCharacter> hornySlaves = new ArrayList<>(Sex.getDominantParticipants(false).keySet());
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
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_WASH = new DialogueNode("Your Room", "", false) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<p>"
						+ "You step into the en-suite, marvelling at its extravagant design. Leaving your dirty clothes on the other side of the door, you take a long, relaxing shower."
					+ "</p>");
			
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

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR = new DialogueNode("Your Room", "", false) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "You step over to one side of your room, where a calendar has been pinned to the wall."
						+ " It's quite obviously enchanted, for as you flick through the pages, you discover that each month's picture changes based on your current train of thought.");
						
			if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
				UtilText.nodeContentSB.append(" As you think about each month, a thematically-dressed man, incubus, or some kind of animal-boy appears on the page.");
			} else {
				UtilText.nodeContentSB.append(" As you think about each month, a thematically-dressed woman, succubus, or some kind of animal-girl appears on the page.");
			}
			
			if(Main.game.getPlayer().getCorruptionLevel()==CorruptionLevel.ZERO_PURE) {
				UtilText.nodeContentSB.append(" The more you flick back and forth through the calendar, the more scantily-dressed the subject of each picture becomes, until you suddenly realise what you're doing and step back, shocked.");
			} else {
				UtilText.nodeContentSB.append(" The more you flick back and forth through the calendar, the more scantily-dressed the subject of each picture becomes, and you find yourself getting a little turned on...");
			}
			UtilText.nodeContentSB.append("</p>");
			
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.knowsDate)) {
				UtilText.nodeContentSB.append("<p>"
						+ "Suddenly remembering what it was that you wanted to look at, you scan through the calendar to find the current date,");
			} else {
				UtilText.nodeContentSB.append("<p>"
						+ "You were so distracted by the changing pictures that you momentarily forgot what it was that you wanted to check."
						+ " Shaking your head, you flip back through the calendar to find out what the current date is,");
			}
			
			UtilText.nodeContentSB.append(" and see that it's the <b style='color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>"
						+ Units.date(Main.game.getDateNow(), Units.DateType.LONG)
					+"</b>. From a quick calculation "+(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_ARCANE)<IntelligenceLevel.ONE_AVERAGE.getMaximumValue()?"(with some help from your phone's calculator)":"")
					+ ", you figure out that it's been <b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>"+Main.game.getDayNumber()+" day"+(Main.game.getDayNumber()>1?"s":"")+"</b> since you appeared in this world."
					+ "</p>");
			
			if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.knowsDate)) {
				UtilText.nodeContentSB.append("<p>"
						+ "[pc.thought(Wait... "+Main.game.getDateNow().format(DateTimeFormatter.ofPattern("yyyy", Locale.ENGLISH))+"?! I need to check in with Lilaya about that...)]"
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append("<p>"
					+ "You notice that on each page of the calendar, there's a few paragraphs detailing the events that occur during that month."
					+ "</p>");
			
			// TODO probably not the best place to put it?
			Main.game.getDialogueFlags().values.add(DialogueFlagValue.knowsDate);
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			if (index == 0) {
				return new Response("Back", "Step away from the calendar.", ROOM);
			} else if(index==1) {
				return new Response("January", "Read the information on January's page. [style.italicsMinorBad(There are currently no special events during January.)]", null);
			} else if(index==2) {
				return new Response("February", "Read the information on February page. [style.italicsMinorBad(There are currently no special events during February.)]", null);
			} else if(index==3) {
				return new Response("March", "Read the information on March's page. [style.italicsMinorBad(There are currently no special events during March.)]", null);
			} else if(index==4) {
				return new Response("April", "Read the information on April's page. [style.italicsMinorBad(There are currently no special events during April.)]", null);
			} else if(index==5) {
				return new Response("May", "Read the information on May's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_MAY);
			} else if(index==6) {
				return new Response("June", "Read the information on June's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_JUNE);
			} else if(index==7) {
				return new Response("July", "Read the information on July's page. [style.italicsMinorBad(There are currently no special events during July.)]", null);
			} else if(index==8) {
				return new Response("August", "Read the information on August's page. [style.italicsMinorBad(There are currently no special events during August.)]", null);
			} else if(index==9) {
				return new Response("September", "Read the information on September's page. [style.italicsMinorBad(There are currently no special events during September.)]", null);
			} else if(index==10) {
				return new Response("October", "Read the information on October's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_OCTOBER);
			} else if(index==11) {
				return new Response("November", "Read the information on November's page. [style.italicsMinorBad(There are currently no special events during November.)]", null);
			} else if(index==12) {
				return new Response("December", "Read the information on December's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_DECEMBER);
			} else {
				return null;
			}
		}
	};
	
	/** Calendar's associated animal-morphs are based on the twelve animals of the Chinese zodiac, with the Monkey being replaced with a demon, the Rooster with a harpy, and the Snake with a lamia.
	 *  The ordering of the demon and harpy have also been switched, so that October has demons.<br/>
	 *  There is also a 20% chance of giving a different, random animal-morph for each month.<br/>
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
						sb.append("a fierce-looking "+Subspecies.CAT_MORPH_TIGER.getSingularMaleName(null)+"."
								+ " Striking a dominant pose, he's flashing you a toothy grin, clearly excited by the fact that his huge feline cock is fully on display.");
					} else {
						sb.append("a fierce-looking "+Subspecies.CAT_MORPH_TIGER.getSingularFemaleName(null)+"."
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
						sb.append("a fit "+Subspecies.HORSE_MORPH.getPluralFemaleName(null)+", who's leaning against a fence, flicking her tail to one side in order to present her animalistic-pussy to you.");
					}
					break;
				case AUGUST:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a sheep-boy and goat-boy, standing side-by-side and presenting their erect cocks as they wink playfully at you.");
					} else {
						sb.append("a wooly sheep-girl and goat-girl, who are lying back and spreading their legs, presenting you with their tight, wet pussies.");
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
						sb.append("an excited-looking "+Subspecies.DOG_MORPH.getPluralFemaleName(null)+", who's down on all fours, raising her hips in order to present you with her wet pussy.");
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
	
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_MAY = new DialogueNode("Your Room", "", false) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.MAY));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
							+ "<span style='color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>May</span>"
							+ "<br/>"
							+ "<span style='color:"+Colour.BASE_PINK_LIGHT.toWebHexString()+";'>Mother's Week</span>"
					+ "</h4>"
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
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_JUNE = new DialogueNode("Your Room", "", false) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.JUNE));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
							+ "<span style='color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>June</span>"
							+ "<br/>"
							+ "<span style='color:"+Colour.BASE_BLUE.toWebHexString()+";'>Father's Week</span>"
					+ "</h4>"
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
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_OCTOBER = new DialogueNode("Your Room", "", false) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.OCTOBER));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
							+ "<span style='color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>October</span>"
							+ "<br/>"
							+ "<span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Lilith's Month</span>"
					+ "</h4>"
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
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_DECEMBER = new DialogueNode("Your Room", "", false) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.DECEMBER));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
							+ "<span style='color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>December</span>"
							+ "<br/>"
							+ "<span style='color:"+Colour.BASE_GOLD.toWebHexString()+";'>Yuletide</span>"
					+ "</h4>"
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
			if(Sex.getNumberOfOrgasms(NightlifeDistrict.getPartner())>=NightlifeDistrict.getPartner().getOrgasmsBeforeSatisfied()) {
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
