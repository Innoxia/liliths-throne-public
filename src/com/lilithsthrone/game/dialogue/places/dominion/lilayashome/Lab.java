package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DaddyDialogue;
import com.lilithsthrone.game.dialogue.places.submission.LyssiethPalaceDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.75
 * @version 0.3.5.5
 * @author Innoxia
 */
public class Lab {
	
	private static boolean isLilayaAngryAtPlayerDemonTF() {
		return Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON
				&& Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN);
	}
	
	public static final DialogueNode LAB = new DialogueNode("Lilaya's Laboratory", "", false) {

		@Override
		public String getContent() {
			if(Main.game.isExtendedWorkTime()) {
				if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					if(Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0)) {
						return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_PREGNANCY_RISK");
					
					} else if(Main.game.getNpc(Lilaya.class).isPregnant() && Main.game.getNpc(Lilaya.class).isCharacterReactedToPregnancy(Main.game.getPlayer())) {
						return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_PREGNANT");
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaBirthNews)) {
						return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_PREGNANCY_RESOLVED");
					}
				}
			}
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB");
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
			if(index==1) {
				if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					if(Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0)) {
						return new Response("Enter", "The door to Lilaya's laboratory is firmly shut. You'd better come back later.", null);
						
					} else if((Main.game.getNpc(Lilaya.class).isPregnant() && Main.game.getNpc(Lilaya.class).isCharacterReactedToPregnancy(Main.game.getPlayer()))) {
						return new Response("Enter", "The door to Lilaya's laboratory is firmly shut. You're not going to be able to get back in until her pregnancy is resolved.", null);
					}
				}
				
				if(!Main.game.isExtendedWorkTime()) {
					return new Response("Enter", "The door to Lilaya's laboratory is firmly shut, and, considering the hour, she's probably sleeping upstairs.", null);
				}
				
				return new Response("Enter", "Step through the door and enter Lilaya's laboratory.", LAB_ENTRY) {
					@Override
					public void effects() {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roseToldOnYou)
								&& Main.game.getPlayer().getQuest(QuestLine.MAIN) != Quest.MAIN_1_I_ARTHURS_TALE
								&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults)
								&& Main.game.getNpc(Lilaya.class).getAffection(Main.game.getPlayer())>0) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), -10));
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	private static void setEntryFlags() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, false);
		if(Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults, false);
			if(Main.game.getNpc(Lilaya.class).isPregnant()) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaBirthNews, true);
			} else {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaBirthNews, false);
			}
		} else {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaBirthNews, false);
		}
		if(Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
			Main.game.getNpc(Lilaya.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
	}
	
	private static List<Response> getLabEntryGeneratedResponses() {
		List<Response> generatedResponses = new ArrayList<>();
		
		if (Main.game.getPlayer().isVisiblyPregnant()) {
			if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS)) {
				generatedResponses.add(new Response("Pregnancy", "You'll need to complete Lilaya's initial tests before she'll agree to help you deal with your pregnancy.", null));
				
			} else {
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY) == Quest.SIDE_PREGNANCY_CONSULT_LILAYA) {
					generatedResponses.add(new Response("Pregnancy", "Speak to Lilaya about your pregnancy.", LilayaBirthing.LILAYA_ASSISTS_PREGNANCY){
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_PREGNANCY, Quest.SIDE_PREGNANCY_LILAYA_THE_MIDWIFE));
						}
					});
					
				} else {
					generatedResponses.add(new Response("Pregnancy", "Speak to Lilaya about your pregnancy.", LilayaBirthing.LILAYA_ASSISTS_PREGNANCY_REPEAT){
						@Override
						public void effects() {
							setEntryFlags();
						}
					});
				}
			}
		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
				if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS)) {
					generatedResponses.add(new Response("Essences & Jinxes", "You'll need to complete Lilaya's initial tests before you're able to ask her about that strange energy you absorbed.", null));
					
				} else {
					generatedResponses.add(new Response("Essences & Jinxes", "Ask Lilaya about that strange energy you absorbed.", LILAYA_EXPLAINS_ESSENCES){
						@Override
						public void effects() {
							setEntryFlags();
						}
					});
				}
				
			} else {
				generatedResponses.add(new Response("Extract Essences",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.essenceExtractionKnown)
								?"Ask Lilaya if you can use her equipment to extract some essences."
								:"Ask Lilaya if there's any way to extract essences you've absorbed",
							ESSENCE_EXTRACTION){
					@Override
					public void effects() {
						setEntryFlags();
					}
				});
			}
		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLAVERY)) {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLAVERY) == Quest.SIDE_SLAVER_NEED_RECOMMENDATION) {
				if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS)) {
					generatedResponses.add(new Response("Slaver", "You'll need to complete Lilaya's initial tests before you can ask her for a letter of recommendation.", null));
					
				} else {
					generatedResponses.add(new Response("Slaver", "Ask Lilaya for a letter of recommendation in order to obtain a slaver license.", LILAYA_SLAVER_RECOMMENDATION){
						@Override
						public void effects() {
							setEntryFlags();
						}
					});
				}
			}
		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION) && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
			if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS)) {
				generatedResponses.add(new Response("Accommodation", "You'll need to complete Lilaya's initial tests before you can ask her about inviting friends home!", null));
				
			} else {
				generatedResponses.add(new Response("Accommodation", "Ask Lilaya about inviting your new friend to live in one of the many spare rooms in the mansion.", LILAYA_FRIEND_ACCOMMODATION){
					@Override
					public void effects() {
						setEntryFlags();
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_ACCOMMODATION, Quest.SIDE_UTIL_COMPLETE));
					}
				});
			}
		}
		
		if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaDateTalk)
				&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.knowsDate)) {
			generatedResponses.add(new Response("Current Date", "Ask Lilaya why the calendar in your room is three years ahead of the correct date.", LILAYA_CURRENT_DATE_TALK) {
				@Override
				public void effects() {
					setEntryFlags();
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaDateTalk, true);
				}
			});
		}
		
		if(Main.game.getPlayer().hasItemType(ItemType.PRESENT) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent3)) {
			if(isLilayaAngryAtPlayerDemonTF() && Main.game.getNpc(Lilaya.class).getRaceStage()!=RaceStage.GREATER) {
				generatedResponses.add(new Response("Give Present", "Although you have a present in your inventory, Lilaya is not interested in receiving it, due to her resentment towards you for being a full demon, while she is not.", null));
				
			} else {
				generatedResponses.add(new Response("Give Present", "Give the present in your inventory to Lilaya.", LILAYA_PRESENT) {
					@Override
					public void effects() {
						setEntryFlags();
						Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.PRESENT));
						
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent2)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.givenLilayaPresent3, true);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent1)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.givenLilayaPresent2, true);
							
						} else {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.givenLilayaPresent1, true);
						}
					}
				});
			}
		}
		
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent3)) {
			if(isLilayaAngryAtPlayerDemonTF() && Main.game.getNpc(Lilaya.class).getRaceStage()!=RaceStage.GREATER) {
				generatedResponses.add(new Response("Geisha Lilaya", "Lilaya is not interested in showing off her kimono, nor having sex with you, until she's a full demon as well.", null));
				
			} else {
				generatedResponses.add(new Response("Geisha Lilaya", "Ask Lilaya if she'd like to wear the gifts you got for her.", LILAYA_GEISHA) {
					@Override
					public void effects() {
						setEntryFlags();
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_LILAYA, true);
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_LILAYA, true);
						
						((Lilaya) Main.game.getNpc(Lilaya.class)).applyGeishaChange();
					}
				});
			}
		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_DADDY)) {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_ACCEPTED) {
				if(!Daddy.isAvailable()) {
					generatedResponses.add(new Response("Meeting [daddy.name]", Daddy.getAvailabilityText(), null));
					
				} else if(Main.game.getPlayer().hasCompanions()) {
					generatedResponses.add(new Response("Meeting [daddy.name]",
							"[style.italicsBad(You cannot ask Lilaya to go with you and meet [daddy.name] while you have companions in your party!)]",
							null));
					
				} else {
					generatedResponses.add(new Response("Meeting [daddy.name]", "Convince Lilaya to go out for dinner with you and [daddy.name].", DaddyDialogue.CONVINCING_LILAYA) {
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_LILAYA_MEETING));
						}
					});
					
				}
	
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_DADDY, Quest.DADDY_LILAYA_MEETING)) {
				if(!Daddy.isAvailable()) {
					generatedResponses.add(new Response("Visit [daddy.name]", Daddy.getAvailabilityText(), null));
					
				} else if(Main.game.getPlayer().hasCompanions()) {
					generatedResponses.add(new Response("Visit [daddy.name]",
							"[style.italicsBad(You cannot ask Lilaya to go with you and vist [daddy.name] while you have companions in your party!)]",
							null));
					
				} else  {
					generatedResponses.add(new Response("Visit [daddy.name]", "Ask Lilaya if she'd like to join you in paying [daddy.name] a visit.", DaddyDialogue.MEETING) {
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							setEntryFlags();
							((Lilaya) Main.game.getNpc(Lilaya.class)).applyDinnerDateChange();
							
							Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_ENTRANCE);
							Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
	
							if(Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getPlayer().isCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class))) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(500));
							}
							if(Main.game.getNpc(Lilaya.class).isVisiblyPregnant() && !Main.game.getNpc(Lilaya.class).isCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class))) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementMoney(500));
							}
						}
					});
					
				}
			}
		}
		
		
		return generatedResponses;
	}
	
	public static final DialogueNode LAB_ENTRY = new DialogueNode("Lilaya's Laboratory", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_I_ARTHURS_TALE) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ARTHUR_PREGNANCY_BASE"));
					
					if(Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ARTHUR_PREGNANT"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ARTHUR_NOT_PREGNANT"));
					}
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ARTHUR_BASE"));
				}

				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ARTHUR"));
				
			} else {
				if(Main.game.getNpc(Lilaya.class).getRaceStage()==RaceStage.GREATER) { // Lilaya a full demon:
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roseToldOnYou)) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ROSE_TOLD_ON_YOU"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_BASE_END"));
					}
					
				} else { // Lilaya is not a full demon:
					if(isLilayaAngryAtPlayerDemonTF() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaBirthNews)) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_DEMON_REACTION_PREGNANCY_RESOLVED"));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_DEMON_REACTION_NO_PREGNANCY"));
						}
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_DEMON_REACTION"));
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults)) {
						if(Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_PREGNANT"));
							
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_NOT_PREGNANT"));
						}
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaBirthNews)) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_PREGNANCY_RESOLVED"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_BASE"));
	
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_NAUGHTY_ROSE"));
						
						if(isLilayaAngryAtPlayerDemonTF() && Main.game.getNpc(Lilaya.class).getRaceStage()!=RaceStage.GREATER) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roseToldOnYou)) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ROSE_TOLD_ON_YOU_DEMON"));
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_BASE_END_DEMON"));
							}
							
						} else {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roseToldOnYou)) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ROSE_TOLD_ON_YOU"));
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_BASE_END"));
							}
						}
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isLilayaAngryAtPlayerDemonTF() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
				if(index == 1) {
					return new Response("Agree",
							"Tell Lilaya that you'll help her convince Lyssieth to turn her into a full demon.<br/>[style.italicsDemon(This will end with Lilaya being permanently transformed into a demon!)]",
							LAB_DEMON_TF_AGREE){
						@Override
						public Colour getHighlightColour() {
							return Colour.RACE_DEMON;
						}
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_DEMON_TF_AGREE"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 50));
						}
					};
					
				} else if(index == 2) {
					return new Response("Refuse",
							"Try and convince Lilaya that she should stay as a half-demon.<br/>[style.italics(You can always change your mind later and tell Lilaya you'll help her to become a full demon.)]",
							LAB_DEMON_TF_REFUSE){
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon, true);
						}
					};
				}
				return null;
					
			} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_I_ARTHURS_TALE) {
				if(index == 1) {
					return new Response("Agree", "Knowing how fierce your [lilaya.relation(pc)] can get when she's in one of these moods, you realise that you don't really have much of a choice...", LAB_ARTHURS_TALE){
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_J_ARTHURS_ROOM));
						}
					};
				}
				return null;
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults)
					&& Main.game.getNpc(Lilaya.class).isVisiblyPregnant()
					&& Main.game.getNpc(Lilaya.class).getRaceStage()!=RaceStage.GREATER) {
				if(index==0) {
					return new Response("Thrown out", "Lilaya is clearly not interested in talking to you until her pregnancy has been resolved...", LAB_EXIT_THROWN_OUT) {
						@Override
						public void effects() {
							setEntryFlags();
						}
					};
				}
				return null;
				
			} else {
				List<Response> generatedResponses = getLabEntryGeneratedResponses();
				
				// Return responses:
				if(index==0) {
					return new Response("Leave", "Say goodbye to Lilaya and exit her lab.", LAB) {
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "You tell Lilaya that you've got to get going, and, after saying goodbye, you head over to the lab's door and make your exit."
									+ "</p>");
						}
					};
					
				} else if (index == 1) {
					if(isLilayaAngryAtPlayerDemonTF() && Main.game.getNpc(Lilaya.class).getRaceStage()!=RaceStage.GREATER) {
						return new Response("Full demon",
								"Tell Lilaya that you'll help her convince her mother to turn her into a full demon.<br/>[style.italicsDemon(This will end with Lilaya being permanently transformed into a full demon!)]",
								LAB_DEMON_TF_AGREE) {
							@Override
							public Colour getHighlightColour() {
								return Colour.RACE_DEMON;
							}
							@Override
							public void effects() {
								setEntryFlags();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_DEMON_TF_AGREE_AFTER_REPEAT"));
								Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 50));
							}
						};
						
					} else {
						if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_A_LILAYAS_TESTS) {
							return new Response("Tests", "Let Lilaya know that you're here to let her run her tests on you.", AUNT_HOME_LABORATORY_TESTING){
								@Override
								public void effects() {
									setEntryFlags();
								}
							};
							
						} else {
							if(Main.game.getNpc(Arthur.class).getLocationPlace().getPlaceType().equals(PlaceType.LILAYA_HOME_LAB)) {
								return new Response("\"Tests\"", "Lilaya can't run any \"tests\" on you while Arthur is still present in her lab. Find him a suitable room first.", null);
								
							} else if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hadSexWithLilaya)) {
								return new Response("\"Tests\"", "Let Lilaya know that you're here to let her run more of her \"tests\" on you.", AUNT_HOME_LABORATORY_TESTING_MORE_SEX){
									@Override
									public void effects() {
										setEntryFlags();
									}
								};
							} else {
								return new Response("Tests", "Tell Lilaya that you want her to run more of her 'tests' on you.", AUNT_HOME_LABORATORY_TESTING_REPEAT){
									@Override
									public void effects() {
										setEntryFlags();
									}
								};
							}
						}
					}

				} else if(index<10) {
					if(index-2 < generatedResponses.size()) {
						return generatedResponses.get(index-2);
					} else {
						return null;
					}
					
				} else if(index==11 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) {
					// Teleport
					return new Response("Lyssieth's office", "Ask Lilaya to use the resonance stone to contact Lyssieth, and ask her to teleport you to her office.", LyssiethPalaceDialogue.LYSSIETH_OFFICE_TALK){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
							if(Main.game.getPlayer().hasCompanions()) { //TODO test
								for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
									companion.setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE, false);
								}
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_TELEPORT_TO_LYSSIETHS_OFFICE_COMPANION", Main.game.getPlayer().getMainCompanion()));
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_TELEPORT_TO_LYSSIETHS_OFFICE"));
							}
						}
					};
				}
				
				return null;
			}
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNode LILAYA_END_SEX = new DialogueNode("Get up", "Lilaya really needs to get back to work.", true) {

		@Override
		public String getDescription() {
			if(Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
				return "Lilaya looks pretty angry, maybe you should have pulled out...";
			} else {
				return "Lilaya really needs to get back to work.";
			}
		}

		@Override
		public String getContent() {
			if(Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_END_SEX_CREAMPIE");
			} else {
				if(Sex.getNumberOfOrgasms(Main.game.getNpc(Lilaya.class))==0) {
					return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_END_SEX_NO_ORGASM");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_END_SEX");
				}
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)
						&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
						&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
					return new Response("Thrown out", "Maybe it's best to leave Lilaya to cool down for a while.", Lab.LAB_EXIT_THROWN_OUT){
						@Override
						public void effects() {
							if(Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) && !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults, true);
							}
							Main.game.getNpc(Lilaya.class).washAllOrifices(true);
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
							
							Main.game.getNpc(Lilaya.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
					
				} else {
					return new Response("Continue", "Leave the lab and let Lilaya carry on with her work.", Lab.LAB_EXIT) {
						@Override
						public void effects() {
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
							Main.game.getNpc(Lilaya.class).washAllOrifices(true);
							
							Main.game.getNpc(Lilaya.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LAB_EXIT = new DialogueNode("Lilaya's Laboratory", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_EXIT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB.getResponse(0, index);
		}
	};
	
	public static final DialogueNode LAB_EXIT_THROWN_OUT = new DialogueNode("Lilaya's Laboratory", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_EXIT_THROWN_OUT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode LAB_DEMON_TF_AGREE = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_DEMON_TF_AGREE_CORE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lyssieth", "Let Lilaya use the resonance stone to contact Lyssieth.", LyssiethPalaceDialogue.LILAYA_DEMON_TF_START){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
						if(Main.game.getPlayer().hasCompanions()) { //TODO test
							for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
								companion.setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
							}
						}
						Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Lyssieth.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_DEMON_TF_REFUSE = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_DEMON_TF_REFUSE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_ENTRY.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode LILAYA_PRESENT = new DialogueNode("Lilaya's Laboratory", "", false) {
		
		@Override
		public String getContent() {

			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent3)) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_PRESENT_3");
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent2)) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_PRESENT_2");
					
			} else {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_PRESENT_1");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB.getResponse(0, index);
		}
	};
	
	public static final DialogueNode LILAYA_GEISHA = new DialogueNode("Lilaya's Bedroom", "", true) {
		

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_GEISHA");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex", "Start having sex with Lilaya.",
						Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Lilaya.class)),
						null,
						null),
						END_SEX_GEISHA,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_GEISHA_SEX_START")){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
					}
				};

			} if (index == 2) {
				return new ResponseSex("Submissive Sex", "Start having submissive sex with Lilaya.",
						Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Lilaya.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						END_SEX_GEISHA,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_GEISHA_SUBMISSIVE_SEX_START")){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
					}
				};

			} else if (index == 3) {
				return new Response("Leave",
						"Tell Lilaya that she looks stunning, but you don't want to have sex with her.",
						RoomPlayer.ROOM){
					@Override public void effects() {

						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						Main.game.getNpc(Lilaya.class).resetInventory(false);
						
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, true);
						
						Main.game.getNpc(Lilaya.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
						
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_GEISHA_SEX_REFUSED"));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode END_SEX_GEISHA = new DialogueNode("Finished", "Lilaya collapses back on her bed with a satisfied sigh.", true) {
		
		@Override
		public String getDescription() {
			if(Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
				return "Lilaya looks pretty angry, maybe you should have pulled out...";
			} else {
				return "Lilaya collapses back on her bed with a satisfied sigh.";
			}
		}

		@Override
		public String getContent() {
			if(Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "END_SEX_GEISHA_CREAMPIE");
				
			} else {
				if(Sex.getNumberOfOrgasms(Main.game.getNpc(Lilaya.class))==0) {
					return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "END_SEX_GEISHA_NO_ORGASM");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "END_SEX_GEISHA");
				}
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)
						&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
						&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
					return new Response("Thrown out", "Maybe it's best to leave Lilaya to cool down for a while.", RoomPlayer.ROOM){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, true);
							if(Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) && !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults, true);
							}
							Main.game.getNpc(Lilaya.class).washAllOrifices(true);

							Main.game.getNpc(Lilaya.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
					
				} else {
					return new Response("To your room", "Head back to your room.", RoomPlayer.ROOM){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, true);
							Main.game.getNpc(Lilaya.class).washAllOrifices(true);
							
							Main.game.getNpc(Lilaya.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNode LILAYA_EXPLAINS_ESSENCES = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			if(getJinxedClothingExample() != null) {
				UtilText.addSpecialParsingString(getJinxedClothingExample().getName(), true);
				UtilText.addSpecialParsingString((getJinxedClothingExample().getClothingType().isPlural()?"them":"it"), false);
				UtilText.addSpecialParsingString((getJinxedClothingExample().getClothingType().isPlural()?"these":"this"), false);
			} else {
				UtilText.addSpecialParsingString("false", true);
			}

			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_EXPLAINS_ESSENCES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("What's wrong?", "Ask Lilaya what's wrong.", LILAYA_EXPLAINS_ESSENCES_2) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.essenceBottledDiscovered)
								&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.essenceOrgasmDiscovered)
								&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.essencePostCombatDiscovered)) {
							Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 1, false);
						}
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_EXPLAINS_ESSENCES_2 = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_EXPLAINS_ESSENCES_2");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Enchantments & Jinxes", "Let Lilaya show you how to use your stored essences in order to enchant items or remove jinxes.", LILAYA_EXPLAINS_ESSENCES_3);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_EXPLAINS_ESSENCES_3 = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_EXPLAINS_ESSENCES_3");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Thank her", "Thank Lilaya for showing you how to enchant items.", LAB_EXIT){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_EXPLAINS_ESSENCES_END")
								+ Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_ENCHANTMENT_DISCOVERY, Quest.SIDE_UTIL_COMPLETE));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ESSENCE_EXTRACTION = new DialogueNode("Lilaya's Lab", "-", true, false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "ESSENCE_EXTRACTION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			
			if(index == 1) {
				if((!Main.game.getPlayer().isInventoryFull() || Main.game.getPlayer().hasItem(AbstractItemType.generateItem(TFEssence.essenceToItem(TFEssence.ARCANE))))) {
					if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE)>=1) {
						return new Response("Extract (1)", "Extract one of your "+TFEssence.ARCANE.getName()+" essences.", ESSENCE_EXTRACTION_BOTTLED) {
							@Override
							public void effects() {
								Main.game.getPlayer().addItem(AbstractItemType.generateItem(TFEssence.essenceToItem(TFEssence.ARCANE)), false, false);
								int count = Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(TFEssence.essenceToItem(TFEssence.ARCANE)));
								
								Main.game.getTextEndStringBuilder().append(
										"<p style='text-align:center;'>"
											+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Item added to inventory:</b> <b>" + (TFEssence.essenceToItem(TFEssence.ARCANE)).getDisplayName(true) + "</b>"
										+ "</p>"
										+ "<p>"
											+ "You now have <b>"+count+" "+(count>1?TFEssence.essenceToItem(TFEssence.ARCANE).getNamePlural(true):TFEssence.essenceToItem(TFEssence.ARCANE).getName(true))+"</b> in your inventory."
										+ "</p>");
								Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -1, false);
							}
						};
						
					} else {
						return new Response("Extract (1)", "You don't have any "+TFEssence.ARCANE.getName()+" essences!", null);
					}
				} else {
					return new Response("Extract (1)", "You don't have any free space in your inventory!", null);
				}
				
				
			} else if(index == 2) {
				if((!Main.game.getPlayer().isInventoryFull() || Main.game.getPlayer().hasItem(AbstractItemType.generateItem(TFEssence.essenceToItem(TFEssence.ARCANE))))) {
					if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE)>=5) {
						return new Response("Extract (5)", "Extract five of your "+TFEssence.ARCANE.getName()+" essences.", ESSENCE_EXTRACTION_BOTTLED) {
							@Override
							public void effects() {
								for(int i =0; i<5; i++) {
									Main.game.getPlayer().addItem(AbstractItemType.generateItem(TFEssence.essenceToItem(TFEssence.ARCANE)), false, false);
								}
								int count = Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(TFEssence.essenceToItem(TFEssence.ARCANE)));
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "Grabbing another vial, you set about repeating the process several times..."
										+ "</p>"
										+ "<p style='text-align:center;'>"
											+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Items added to inventory:</b> <b>5x</b> <b>" + TFEssence.essenceToItem(TFEssence.ARCANE).getDisplayName(true) + "</b>"
										+ "</p>"
										+ "<p>"
											+ "You now have <b>"+count+" "+(count>1?TFEssence.essenceToItem(TFEssence.ARCANE).getNamePlural(true):TFEssence.essenceToItem(TFEssence.ARCANE).getName(true))+"</b> in your inventory."
										+ "</p>");
								Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -5, false);
							}
						};
						
					} else {
						return new Response("Extract (5)", "You don't have enough "+TFEssence.ARCANE.getName()+" essences!", null);
					}
				} else {
					return new Response("Extract (5)", "You don't have any free space in your inventory!", null);
				}
				
			} else if(index == 3) {
				if((!Main.game.getPlayer().isInventoryFull() || Main.game.getPlayer().hasItem(AbstractItemType.generateItem(TFEssence.essenceToItem(TFEssence.ARCANE))))) {
					if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE)>=25) {
						return new Response("Extract (25)", "Extract twenty-five of your "+TFEssence.ARCANE.getName()+" essences.", ESSENCE_EXTRACTION_BOTTLED) {
							@Override
							public void effects() {
								for(int i =0; i<25; i++) {
									Main.game.getPlayer().addItem(AbstractItemType.generateItem(TFEssence.essenceToItem(TFEssence.ARCANE)), false, false);
								}
								int count = Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(TFEssence.essenceToItem(TFEssence.ARCANE)));
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "Grabbing another vial, you set about repeating the process several times..."
										+ "</p>"
										+ "<p style='text-align:center;'>"
											+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Items added to inventory:</b> <b>25x</b> <b>" + TFEssence.essenceToItem(TFEssence.ARCANE).getDisplayName(true) + "</b>"
										+ "</p>"
										+ "<p>"
											+ "You now have <b>"+count+" "+(count>1?TFEssence.essenceToItem(TFEssence.ARCANE).getNamePlural(true):TFEssence.essenceToItem(TFEssence.ARCANE).getName(true))+"</b> in your inventory."
										+ "</p>");
								Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -25, false);
							}
						};
						
					} else {
						return new Response("Extract (25)", "You don't have enough "+TFEssence.ARCANE.getName()+" essences!", null);
					}
				} else {
					return new Response("Extract (25)", "You don't have any free space in your inventory!", null);
				}
				
			} else if(index == 4) {
				if((!Main.game.getPlayer().isInventoryFull() || Main.game.getPlayer().hasItem(AbstractItemType.generateItem(TFEssence.essenceToItem(TFEssence.ARCANE))))) {
					if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE)>=1) {
						return new Response("Extract (all)", "Extract all of your "+TFEssence.ARCANE.getName()+" essences.", ESSENCE_EXTRACTION_BOTTLED) {
							@Override
							public void effects() {
								for(int i =0; i<Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE); i++) {
									Main.game.getPlayer().addItem(AbstractItemType.generateItem(TFEssence.essenceToItem(TFEssence.ARCANE)), false, false);
								}
								int count = Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(TFEssence.essenceToItem(TFEssence.ARCANE)));
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "Grabbing another vial, you set about repeating the process several times..."
										+ "</p>"
										+ "<p style='text-align:center;'>"
											+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Items added to inventory:</b> <b>"+Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE)+"x</b> <b>"
												+ TFEssence.essenceToItem(TFEssence.ARCANE).getDisplayName(true) + "</b>"
										+ "</p>"
										+ "<p>"
											+ "You now have <b>"+count+" "+(count>1?TFEssence.essenceToItem(TFEssence.ARCANE).getNamePlural(true):TFEssence.essenceToItem(TFEssence.ARCANE).getName(true))+"</b> in your inventory."
										+ "</p>");
								Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE), false);
								
							}
						};
						
					} else {
						return new Response("Extract (all)", "You don't have any essences!", null);
					}
				} else {
					return new Response("Extract (all)", "You don't have any free space in your inventory!", null);
				}
				
			} else if (index == 0) {
				return new Response("Back", "Stop extracting essences.", LAB_ENTRY) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.essenceExtractionKnown, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ESSENCE_EXTRACTION_BOTTLED = new DialogueNode("Lilaya's Lab", "-", true, false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "ESSENCE_EXTRACTION_BOTTLED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ESSENCE_EXTRACTION.getResponse(0, index);
		}
	};
	
	
	public static final DialogueNode LILAYA_CURRENT_DATE_TALK = new DialogueNode("Lilaya's Lab", "-", true, false) {
		
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_CURRENT_DATE_TALK_KNOW_TRUTH");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_CURRENT_DATE_TALK");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Thank her", "Thank Lilaya for her information (or lack thereof), and think about asking her something else.", LAB_EXIT);
			} else {
				return null;
			}
		}
	};
	
	
	//----------------------------------

	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Returning home", "Ask Lilaya if she's found a way to send you back home.", AUNT_HOME_LABORATORY_TESTING_ARTHUR){
					@Override
					public void effects() {
						if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_A_LILAYAS_TESTS) {
							Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME);
							((Arthur) Main.game.getNpc(Arthur.class)).generateNewTile();
						}
						if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.reactedToPregnancyLilaya))
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING_REPEAT = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_REPEAT");
			
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response(
						!Main.game.getPlayer().isTaur()
							?"Sit down"
							:"Step forwards",
						"You know exactly why Lilaya seems embarrassed about these 'tests'...",
						AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA){
					@Override
					public void effects() {
						if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.reactedToPregnancyLilaya))
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, true);
					}
				};

			} else if (index == 2) {
				return new Response("Decline", "Tell Lilaya that you've changed your mind. While she'll probably be a little disappointed, you can always come back later to take up her offer if you should change your mind.", LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_LEAVE"));
						if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.reactedToPregnancyLilaya)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, true);
						}
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING_MORE_SEX = new DialogueNode("", "", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_MORE_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getPlayer().isTaur()) {
					return new ResponseSex("Sex",
							"Start having sex with Lilaya.",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							true, true,
							new SMSitting(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lilaya.class), SexSlotSitting.SITTING_IN_LAP))),
							null,
							null,
							LILAYA_END_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_MORE_SEX_START")){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE, false);
						}
					};
				} else {
					return new ResponseSex("Sex",
							"Start having sex with Lilaya.",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(Lilaya.class)),
									null,
									null,
									ResponseTag.PREFER_ORAL),
							null,
							null,
							LILAYA_END_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_MORE_SEX_START_TAUR")){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE, false);
						}
					};
				}


			} else if (index == 2) {
				return new Response("Stop", "Tell Lilaya that you need to get going. While she'll definitely be disappointed that you're stopping so soon, you can always come back later if you should change your mind.",
						LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_MORE_SEX_STOP"));
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING_ARTHUR = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_ARTHUR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response(
						"'Tests'",
						"Accept Lilaya's offer of more 'tests'. You're not sure what her intentions really are, but you're confident that you'll be able to stop her if she tries any funny business.",
						AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA);

			} else if (index == 2) {
				return new Response("Decline",
						"Tell Lilaya that you're not up for this sort of thing. While she'll probably be a little disappointed, you can always come back later to take up her offer if you should change your mind.",
						LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_ARTHUR_DECLINED"));
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Open your mouth",
						"Let Lilaya push her finger into your mouth. After all, maybe this is just part of the test?",
						AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX);

			} else if (index == 2) {
				return new Response("Stop this",
						"Stand up and tell Lilaya that this is going too far. While she'll undoubtedly be upset at this sudden end to her advances, you're sure that she'd try to hit on you again if you changed your mind in the future.",
						LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_DECLINED"));
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getPlayer().isTaur()) {
					return new ResponseSex("Let it happen",
							Main.game.getPlayer().hasFetish(Fetish.FETISH_INCEST)
								?"You know that this can only end one way, and the fact that Lilaya reminds you of your aunt Lily only makes it all the more exciting..."
								:"You know that this can only end one way. Although Lilaya reminds you of your [lilaya.relation(pc)] Lily, you don't think it will get in the way of you enjoying this...",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							true, true,
							new SMSitting(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lilaya.class), SexSlotSitting.SITTING_IN_LAP))),
							null,
							null,
							LILAYA_END_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX_START")){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE, false);
						}
					};
					
				} else {
					return new ResponseSex("Let it happen",
							Main.game.getPlayer().hasFetish(Fetish.FETISH_INCEST)
								?"You know that this can only end one way, and the fact that Lilaya reminds you of your aunt Lily only makes it all the more exciting..."
								:"You know that this can only end one way. Although Lilaya reminds you of your [lilaya.relation(pc)] Lily, you don't think it will get in the way of you enjoying this...",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(Lilaya.class)),
									null,
									null,
									ResponseTag.PREFER_ORAL),
							null,
							null,
							LILAYA_END_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX_START_TAUR")){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE, false);
						}
					};
				}

			} else if (index == 2) {
				return new Response("Stop this",
						"Stand up and tell Lilaya that this has gone too far. While she'll undoubtedly be upset at this sudden end, you're sure that she'd try to hit on you again if you changed your mind in the future.",
						LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX_DECLINED"));
					}
				};

			} else {
				return null;
			}
		}
	};

	private static AbstractClothing getJinxedClothingExample() {
		for (AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
			if(c.isSealed()) {
				return c;
			}
		}
		return null;
	}
	
	public static final DialogueNode LILAYA_FRIEND_ACCOMMODATION = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_FRIEND_ACCOMMODATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "You've now got Lilaya's permission to invite friends back home!", LAB_EXIT);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_SLAVER_RECOMMENDATION = new DialogueNode("", "", true, true) {


		@Override
		public String getContent() {
				return "<p>"
							+ "[pc.speech(I was told that in order to get a slaver license, I'd need a letter of recommendation from someone who's already got one,)]"
							+ " you explain to Lilaya,"
							+ " [pc.speech(so I was wondering if you could write one for me?)]"
						+ "</p>"
						+ "<p>"
							+ "Lilaya lets out a little sigh and raises her eyebrow."
							+ " [lilaya.speech(I'm fine with writing a letter for you, but have you thought about where you're going to keep your slaves?)]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Erm... Well, no...)]"
							+ " you mumble, realising that you hadn't thought about the logistics of owning slaves."
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech(I thought not,)] Lilaya responds."
						+ "</p>"
						+ "<p>"
							+ "Walking over to a nearby desk, she sits down and grabs a piece of paper and a pen."
							+ " As she starts writing your letter, she calls out to you,"
							+ " [lilaya.speech(You're going to need a place to keep your slaves."
								+ " No doubt they'll tell you all this when you receive your license, but the Slavery Administration is only for holding your new slaves until their relocation."
								+ " It's <i>not</i> a permanent place to keep them.)]"
						+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Accommodation", "Agree with Lilaya's observation that you'll need somewhere to keep your slaves.", LILAYA_SLAVER_RECOMMENDATION_SLAVE_ACCOMMODATION) {
					@Override
					public void effects() {
						if (Main.game.getPlayer().getQuest(QuestLine.SIDE_SLAVERY) == Quest.SIDE_SLAVER_NEED_RECOMMENDATION) {
							Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLAVERY, Quest.SIDE_SLAVER_RECOMMENDATION_OBTAINED);
						}
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_SLAVER_RECOMMENDATION_SLAVE_ACCOMMODATION = new DialogueNode("", "", true, true) {

		@Override
		public String getContent() {
				return "<p>"
							+ "[pc.speech(You're right, Lilaya, I didn't think about that...)]"
							+ " you admit, feeling like you're on the receiving end of yet another of your [lilaya.relation(pc)]'s scoldings."
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech(Well, luckily for you, I think I can help,)]"
							+ " Lilaya responds, standing up from behind her desk with the completed letter of recommendation in her hand."
							+ " [lilaya.speech(I'm sure you've noticed, but this house is extremely large, which also means that it's an extremely time-consuming job to keep clean."
								+ " Isn't that right Rose?)]"
						+ "</p>"
						+ "<p>"
							+ "[rose.speech(Yes, Mistress!)]"
							+ " Rose's voice calls out from one side of the room."
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech(So, I've got a deal to make. I only ever use my lab and my bedroom, and as you've probably seen, all the other rooms in this house are left empty and unused."
								+ " I'm willing to let you use those empty rooms as accommodation for your slaves, but only on the condition that you either pay a daily upkeep for each room that you use, or you assign some of your slaves to help with the cleaning."
								+ " Deal?)]"
						+ "</p>"
						+ "<p>"
							+ "Lilaya holds the letter of recommendation behind her back, smiling as she awaits your answer."
							+ " You think that her deal sounds fair enough, and, nodding in agreement, you reply,"
							+ " [pc.speech(That sounds more than reasonable. Thanks, Lilaya, it's a deal.)]"
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech(Great!)]"
							+ " Lilaya responds, beaming at you as she gives you the letter."
							+ " [lilaya.speech(If you want to use any of the rooms to house slaves, just go into the one you'd like and ring the bell pull."
								+ " Rose will handle all the necessary arrangements."
								+ " Oh, and if you'd like to get Rose to manage your slaves for you, she's more than capable!"
								+ " Just ring the little bell beside her room's door, and she'll come running, won't you Rose?!)]"
						+ "</p>"
						+ "<p>"
							+ "[rose.speech(Yes, Mistress! I'd love to help!)]"
							+ " Rose calls out yet again."
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Thanks, Lilaya,)]"
							+ " you say, smiling at your demonic [lilaya.relation(pc)]."
							+ " She cheerily returns your smile, before backing off to give you some space."
						+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Now that you've got Lilaya's letter of recommendation, you should head back to Slaver Alley and talk to [finch.name].", LAB_EXIT);

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode LAB_ARTHURS_TALE = new DialogueNode("Lilaya's Laboratory", "", true, true) {
		
		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(Ok, Lilaya, I'll find a room for Arthur,)]"
						+ " you answer, trying to get your [lilaya.relation(pc)] to calm down as you agree to do as she asks."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Thank you, [pc.name], I would have asked Rose to do it for me, but as I agreed to let you use the empty rooms already, I thought it best that you should be the one to decide where Arthur goes,)]"
						+ " Lilaya says, before stepping to one side and turning to face Arthur,"
						+ " [lilaya.speech(You should let [pc.name] know how you ended up as Zaranix's slave; after all, [pc.she] was the one who went through all that trouble to free you.)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Yes, Lilaya,)]"
						+ " Arthur responds, before turning to address you."
						+ " [arthur.speech(We can discuss it a little more some other time, but the basic gist of it is that I decided to do a little research into arcane teleportation.)]"
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Which is illegal,)]"
						+ " Lilaya interjects."
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Yes, thank you, Lilaya. Which is illegal."
							+ " Anyway, thanks to a certain amber-haired demon, who I <i>assumed</i> was no more than a succubus looking to make some quick cash, I ended up creating a teleportation device that actually worked!"
							+ " Well, it sort of worked... It didn't quite take me to where I wanted to go...)]"
					+ "</p>"
					+ "<p>"
						+ "You notice Arthur glance across to Lilaya, and the pair of them exchange a slightly worried look, before he continues,"
						+ " [arthur.speech(Anyway, when I returned, I discovered that Amber had already called for the Enforcers."
							+ " After immediately being enslaved for 'treason', I was quickly shunted through the legal process, and, through a series of bribes, Zaranix quickly gained possession of me."
							+ " It turned out that he'd been watching me for months, waiting for me to make a mistake so that he could get me enslaved."
							+ " He was under the rather deluded impression that I'd be able to make a demonic transformative, among other things, and that I'd make him rich.)]"
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Which could actually be possible, by the way,)]"
						+ " Lilaya interrupts again, leaning back against a desk and crossing her arms."
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Erm, no. It isn't possible. You can harness arcane power to create transformatives for other races, but demonic essence is actually-)]"
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Oh please, give me a break. Who was it that said inter-dimensional travel wasn't possible, even when the entire rest of the research community said it was?"
							+ " Oh, that'd be you. And look what's sitting right next to you. Proof that it is possible!)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(L-Lilaya, we can discuss that another time. What I'm talking about it demonic essences being, by their very nature, incompatible with transformative consumables, or indeed items of any sort!)]"
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(I honestly can't believe how thick you are sometimes, Arthur. But then again, it is to be expected of someone who'd go behind their lover's back to fuck-)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(How many times do I have to apologise?)]"
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Oh, I'm sorry, I thought <i>I</i> was talking."
							+ " But that's ok."
							+ " Seeing as you want to interrupt me, you're obviously very eager to talk about how much you liked fucking her behind my back, so please, do go on!)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Leave", "Head out to find a suitable room for Arthur to stay in.", LAB_LEAVE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LAB_LEAVE = new DialogueNode("Lilaya's Laboratory", "", false, true) {
		
		@Override
		public String getContent() {
			return "<p>"
						+ "Sensing that Lilaya's temper is about to flare up again, you stand up and move to make your exit."
						+ " Sure enough, by the time you've reached the door to the lab and stepped out into the corridor beyond, you start to hear Lilaya's voice raise into shouts and screams again."
						+ " Finding yourself glad to not be in Arthur's position right now, you set off to find a suitable room for him..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
}
