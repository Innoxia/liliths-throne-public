package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
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
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.universal.SMChair;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.SexSlotOther;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.75
 * @version 0.3.2
 * @author Innoxia
 */
public class Lab {
	
	private static boolean isLilayaAngryAtPlayerDemonTF() {
		return Main.game.getPlayer().getSubspeciesOverride()!=null
				&& Main.game.getPlayer().getSubspeciesOverride().getRace()==Race.DEMON
				&& Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN);
	}
	
	public static final DialogueNode LAB = new DialogueNode("Lilaya's Laboratory", "", false) {

		@Override
		public String getContent() {
			if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
				if(Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0)) {
					return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_PREGNANCY_RISK");
				
				} else if(Main.game.getNpc(Lilaya.class).isPregnant() && Main.game.getNpc(Lilaya.class).isCharacterReactedToPregnancy(Main.game.getPlayer())) {
					return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_PREGNANT");
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaBirthNews)) {
					return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_PREGNANCY_RESOLVED");
				}
			}
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					if(Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0)) {
						return new Response("Enter", "The door to Lilaya's laboratory is firmly shut. You'd better come back later.", null);
						
					} else if((Main.game.getNpc(Lilaya.class).isPregnant() && Main.game.getNpc(Lilaya.class).isCharacterReactedToPregnancy(Main.game.getPlayer()))) {
						return new Response("Enter", "The door to Lilaya's laboratory is firmly shut. You're not going to be able to get back in until her pregnancy is resolved.", null);
					}
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
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Entrance hall", "Fast travel to the entrance hall."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), PlaceType.LILAYA_HOME_ENTRANCE_HALL, true);
					}
				};
	
			} else if (index == 7) {
				return new ResponseEffectsOnly("Your Room", "Fast travel up to your room."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR), PlaceType.LILAYA_HOME_ROOM_PLAYER, true);
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
					generatedResponses.add(new Response("Pregnancy", "Speak to Lilaya about your pregnancy.", LILAYA_ASSISTS_PREGNANCY){
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_PREGNANCY, Quest.SIDE_PREGNANCY_LILAYA_THE_MIDWIFE));
						}
					});
					
				} else {
					generatedResponses.add(new Response("Pregnancy", "Speak to Lilaya about your pregnancy.", LILAYA_ASSISTS_PREGNANCY_REPEAT){
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
		//TODO add a scene where convincing her, then dinner scene
		if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_ACCEPTED) {
			if(Daddy.isAvailable()) {
				generatedResponses.add(new Response("Meeting [daddy.name]", "Convince Lilaya to go out for dinner with you and [daddy.name].", DaddyDialogue.CONVINCING_LILAYA) {
					@Override
					public void effects() {
						setEntryFlags();
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_LILAYA_MEETING));
					}
				});
				
			} else {
				generatedResponses.add(new Response("Meeting [daddy.name]", Daddy.getAvailabilityText(), null));
			}

		} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_DADDY, Quest.DADDY_LILAYA_MEETING)) {
			if(Daddy.isAvailable()) {
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
					}
				});
				
			} else {
				generatedResponses.add(new Response("Visit [daddy.name]", Daddy.getAvailabilityText(), null));
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
						Main.game.getPlayer().getLegConfiguration().isBipedalPositionedGenitals()
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
				if(Main.game.getPlayer().getLegConfiguration().isBipedalPositionedGenitals()) {
					return new ResponseSex("Sex",
							"Start having sex with Lilaya.",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							true, true,
							new SMChair(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotOther.SITTING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lilaya.class), SexSlotOther.SITTING_IN_LAP))),
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
				if(Main.game.getPlayer().getLegConfiguration().isBipedalPositionedGenitals()) {
					return new ResponseSex("Let it happen",
							Main.game.getPlayer().hasFetish(Fetish.FETISH_INCEST)
								?"You know that this can only end one way, and the fact that Lilaya reminds you of your aunt Lily only makes it all the more exciting..."
								:"You know that this can only end one way. Although Lilaya reminds you of your [lilaya.relation(pc)] Lily, you don't think it will get in the way of you enjoying this...",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							true, true,
							new SMChair(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotOther.SITTING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lilaya.class), SexSlotOther.SITTING_IN_LAP))),
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

	public static final DialogueNode LILAYA_ASSISTS_PREGNANCY = new DialogueNode("", "", true, true) {

		@Override
		public String getContent() {
			PlayerCharacter player = Main.game.getPlayer();
			GameCharacter lilaya = Main.game.getNpc(Lilaya.class);
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Stepping forwards, you smile at your demonic [lilaya.relation(pc)], before looking down and rubbing your belly."
						+ " [pc.speech(Erm... Lilaya...)]"
					+ "</p>"
					+"<p>"
						+ "[lilaya.speech(Wait... Are you <i>pregnant</i>?!)] she asks."
					+ "</p>"
					+"<p>"
						+ "You aren't quite sure what to say, and look sheepishly at the floor as you hear Lilaya's footsteps drawing near."
						+ " Before you know what's happening, her hands are rubbing all over your belly, and you let out a little gasp at the sudden feeling of someone else touching your pregnant bump."
					+ "</p>");
			
			// Player has had sex with Lilaya before:
			if(player.getSexPartnerStats(lilaya) != null) {
				if(player.getPotentialPartnersAsMother().stream().anyMatch(x -> x.getFather() == lilaya)) {
					if(player.getPotentialPartnersAsMother().stream().anyMatch(x -> x.getFather() != lilaya)) {
						// Lilaya might be the 'father':
						UtilText.nodeContentSB.append(
							"<p>"
								+"[pc.speech(Erm... Lilaya? Just so you know, you <i>might</i> be the one who got me pregnant...)] you explain, looking down at your demonic [lilaya.relation(pc)] as she carries on feeling your stomach."
							+ "</p>"
							+ "<p>"
								+"[lilaya.speech(Well, you're the one who wanted me to grow a cock, remember?)] Lilaya laughs, clearly not at all fazed by the fact that she might be the other parent to your children."
							+ "</p>"
							+"<p>"
								+ "You feel very conscious of the fact that you just admitted to having had other sexual partners to Lilaya."
								+ " As you glance over to the nearby chair that you both had sex on, the feeling of guilt in the back of your mind causes you to start explaining yourself to your demonic [lilaya.relation(pc)], "
								+ UtilText.parsePlayerSpeech("Well... You don't mind do you? I mean, I still like you and everything...")
							+ "</p>"
							+ "<p>"
								+ "You look up to see a puzzled expression on Lilaya's face, and she momentarily stops feeling your stomach, leaving her hands to rest on your belly as she replies, "
								+ UtilText.parseSpeech("Mind about what? Oh!", lilaya)
								+ " Her cheeks go red as she starts to blush. "
								+ UtilText.parseSpeech("D-Don't worry about it! I-I mean... I still like you too... It's just that Rose and I have this special sort of relationship, y-you see?", lilaya)
							+ "</p>"
							+ "<p>"
								+ "For a moment, you're not quite sure what she's talking about, but then you realise that she completely misinterpreted what you were saying."
								+ " She seems to have thought that you were apologising about interrupting her and Rose, and you see that she isn't even thinking twice about you having sex with other people."
								+ " After all, you suppose, she's quite happy to carry on pursuing her relationship with Rose, and it puts your mind at ease knowing that whatever you've got going with Lilaya isn't an exclusive deal."
							+ "</p>");
						
					} else {
						// Lilaya is definitely the 'father':
						UtilText.nodeContentSB.append(
								"<p>"
									+"[pc.speech(Erm... Lilaya? Just so you know, you're definitely the one that got me pregnant...)] you explain, looking down at your demonic [lilaya.relation(pc)] as she carries on feeling your stomach."
								+ "</p>"
								+ "<p>"
								+"[lilaya.speech(Well, you're the one who wanted me to grow a cock, remember?)] Lilaya laughs, clearly not at all fazed by the fact that she's the 'father' to your children."
							+ "</p>");
					}
					
				} else {
					// Lilaya is definitely not the 'father':
					UtilText.nodeContentSB.append(
							"<p>"
								+ "You feel very conscious of the fact that you're presenting clear evidence of having other sexual partners to Lilaya."
								+ " As you glance over to the nearby chair that you both had sex on, the feeling of guilt in the back of your mind causes you to start explaining yourself to your demonic [lilaya.relation(pc)], "
								+ UtilText.parsePlayerSpeech("Erm... Lilaya... You don't mind do you? I mean, I still like you and everything...")
							+ "</p>"
							+ "<p>"
								+ "You look up to see a puzzled expression on Lilaya's face, and she momentarily stops feeling your stomach, leaving her hands to rest on your belly as she replies, "
								+ UtilText.parseSpeech("Mind about what? Oh!", lilaya)
								+ " Her cheeks go red as she starts to blush. "
								+ UtilText.parseSpeech("D-Don't worry about it! I-I mean... I still like you too... It's just that Rose and I have this special sort of relationship, y-you see?", lilaya)
							+ "</p>"
							+ "<p>"
								+ "For a moment, you're not quite sure what she's talking about, but then you realise that she completely misinterpreted what you were saying."
								+ " She seems to have thought that you were apologising about interrupting her and Rose, and you see that she isn't even thinking twice about you having sex with other people."
								+ " After all, you suppose, she's quite happy to carry on pursuing her relationship with Rose, and it puts your mind at ease knowing that whatever you've got going with Lilaya isn't an exclusive deal."
							+ "</p>");
				}
				
			}

			UtilText.nodeContentSB.append(
					"<p>"
						+ "She suddenly breaks off from fondling your abdomen, and, grabbing your wrist, starts pulling you further into her lab."
						+ " You look around to see that Rose has vacated the area, leaving you alone with your demonic [lilaya.relation(pc)]."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(This is so exciting! I wonder how your body's going to react, what with you coming from another universe and everything!)]"
						+ " she cries, pushing you down into a chair before running off to rummage around in some nearby boxes. As she does so, she shouts back to you over her shoulder, "
						+ "[lilaya.speech(I'm guessing you're not aware of how pregnancies work here, huh?"
								+ " Well, seeing as the arcane has a massive effect on it, I'm guessing it's pretty different than it is in your world!"
								+ " Hmm... Where did it go?)]"
					+ "</p>"
					+ "<p>"
						+ "Lilaya stops talking for a moment as she concentrates on searching through the boxes."
						+ " You aren't given much time to think about what she's saying before you see her straighten up and turn around, and you see that she's holding another of those wand-like instruments in one hand as she"
							+ " starts walking back over towards you."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(So, I'm guessing you've already realised that the arcane has a big influence on pregnancy speed around here,)]"
						+ " she laughs, stroking your belly once more before stepping back and tinkering with some small dials on the instrument she's just retrieved. "
						+ UtilText.parseSpeech("I've actually done a fair bit of research on the arcane's influence on pregnancies, so I know that without its presence, pregnancies should really be taking about nine months...", lilaya)
					+ "</p>"
					+ "<p>"
						+ "As you try to assimilate the information Lilaya's giving you, the instrument in her hand starts giving off a faint pink glow, and, letting out a satisfied hum, she brings it down to your pregnant bump."
						+ " Running the little wand up and down over your swollen belly, she starts making thoughtful humming sounds, and just as you're starting to worry about the puzzled expression that's covering her face,"
							+ " the instrument suddenly releases a bright flash of pink light, and Lilaya lets out a relieved sigh."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Well, that's a relief! Oh, right, I need to explain what's going on!)]"
						+ " she says, putting the instrument down on a nearby table before turning back to face you. "
						+ "[lilaya.speech(So, it turns out that your body has completely adapted to this world, and you're going to be obeying the same sort of rules for pregnancy as the rest of us!"
								+ " What I'm trying to say is that you're not going to be pregnant for the better part of a year; it's going to be more like a few weeks."
								+ " Oh, and also, the races of the children you're going to give birth to will be a split between your race, and the race of the father."
								+ " Pretty exciting, huh?!)]"
					+ "</p>"
					+ "<p>"
						+ "You look down at your pregnant belly, stroking it absent-mindedly as Lilaya's words sink in."
						+ " As your thoughts turn to the process of bringing up a child in this strange world, you feel yourself starting to become overwhelmed by emotion, and you blurt out to Lilaya, "
						+ "[pc.speech(B-but what do I do?! How am I meant to raise a child here?! A-And, what about the whole part of actually giving birth?! This is too much!)]"
					+ "</p>"
					+ "<p>"
						+ "Much to your surprise, Lilaya's face momentarily scrunches up into a look of confusion, as though she doesn't quite know what you're talking about."
						+ " Thankfully, after just a moment, she lets out an understanding sigh, and quickly moves to put your fears to rest. "
						+ "[lilaya.speech(Aah, of course, the arcane is the thing responsible for the rapid growth as well..."
								+ " I'm sure this will come as a major relief, but you don't have to worry about raising your children here; thanks to the arcane, they'll reach full maturity in just a couple of hours."
								+ " The arcane grants them their mother's general knowledge about things, so they get all the information they need to make their own way in the world."
								+ " There's no need for any 'raising' or anything, don't worry."
								+ " Honestly, I don't even know how people would be able to cope without the arcane!)]"
					+ "</p>"
					+ "<p>"
						+ "As Lilaya finishes speaking, a huge wave of relief washes over you."
						+ " You sink back into the chair, sighing deeply, before you remember your last concern about this whole process; the part about actually giving birth."
						+ " Before you can ask Lilaya about it again, she continues, "
					+ (player.hasStatusEffect(StatusEffect.PREGNANT_3)
							? "[lilaya.speech(Oh, and don't worry about the whole giving birth part."
									+ " I see that your belly has already finished growing, so I can help you to give birth whenever you're ready!)]"
								+ "</p>"
								+ "<p>"
									+ "With your final question answered, you stand up from the chair, and thank Lilaya for all her help."
									+ " She tells you not to worry about it, and to remember to come back to see her when you're ready to give birth."
								+ "</p>"

							: "[lilaya.speech(Oh, and don't worry about the whole giving birth part."
									+ " When you're ready, just come back here and I'll talk you through it!)]"
								+ "</p>"
								+ "<p>"
									+ "With your final question answered, you stand up from the chair, and thank Lilaya for all her help."
									+ " She tells you not to worry about it, and to remember to come back to see her once your belly has finished growing."
								+ "</p>"));
					
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)) {
					return new Response("Give birth", "Tell Lilaya that you're ready to give birth.", LILAYA_DETECTS_BIRTHING_TYPE){
						@Override
						public void effects() {
							if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.reactedToPregnancyLilaya)) {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, true);
							}
						}
					};
				} else {
					return new Response("Give birth", "You need to wait until your belly has finished growing before you're able to give birth.", null){
						@Override
						public void effects() {
							if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.reactedToPregnancyLilaya)) {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, true);
							}
						}
					};
				}

			} else if (index == 0) {
				return new Response("Back", "Tell Lilaya that you need a moment to think.", LAB_EXIT){
					@Override
					public void effects() {
						if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.reactedToPregnancyLilaya)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, true);
						}
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "[pc.speech(Thanks for your help, Lilaya, but can I just have a moment to think?)] you ask, smiling at your demonic [lilaya.relation(pc)]."
								+ "</p>"
								+ "<p>"
									+ "[lilaya.speech(Sure, just let me know if you need anything else!)] she says, before backing off a little to give you some space."
								+ "</p>");
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_ASSISTS_PREGNANCY_REPEAT = new DialogueNode("", "", true, true) {

		@Override
		public String getContent() {
			PlayerCharacter player = Main.game.getPlayer();
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Stepping forwards, you smile at your demonic [lilaya.relation(pc)], before looking down and rubbing your belly."
						+ " [pc.speech(Erm... Lilaya...)]"
					+ "</p>");

			if(player.isVisiblyPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.reactedToPregnancyLilaya)) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[lilaya.speech(Wait... Are you <i>pregnant</i>?!)] she asks."
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[lilaya.speech(So, you're ready to do something about your little pregnancy problem, huh?)] she asks."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "You aren't quite sure what to say, and look sheepishly at the floor as you hear Lilaya's footsteps drawing near."
						+ " Before you know what's happening, her hands are rubbing all over your belly, and you let out a little gasp at the sudden feeling of someone else touching your pregnant bump."
					+ "</p>");

			if(player.hasStatusEffect(StatusEffect.PREGNANT_3)) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[lilaya.speech(I see your belly's already finished growing, so I can help you to give birth whenever you're ready!)]"
							+ " she says, smiling at you as she carries on fondling your swollen bump."
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[lilaya.speech(You're not quite ready to give birth just yet... Come back when your belly's really nice and round!)]"
							+ " she says, smiling at you as she carries on fondling your swollen bump."
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LILAYA_ASSISTS_PREGNANCY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LILAYA_DETECTS_BIRTHING_TYPE = new DialogueNode("", "", true) {

		@Override
		public String getLabel() {
			return "Giving birth";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Cradling your swollen belly with both hands, you look up at Lilaya. "
						+ "[pc.speech(I'm ready to give birth now...)]"
					+ "</p>");

			if (!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "A delighted smile crosses her face, but despite her reassuring look, as well as knowing that you're in competent hands, you're still incredibly nervous about what comes next."
							+ " Lilaya must have noticed the worried look on your face, as she moves up close to you and makes a soothing hushing sound."
							+ " Her hands move down to gently stroke over your pregnant bump, and you start blushing at the thought of what's about to happen."
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "A delighted smile crosses her face, and despite the fact that you've done this before, you're still pretty nervous about what comes next."
							+ " She must have noticed the worried look on your face, as she suddenly steps close to you and makes a soothing hushing sound."
							+ " Her hands stroke over your pregnant bump, and you start blushing at the thought of what's about to happen."
						+ "</p>");
			}

			if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "DETECT_PREGNANCY_TYPE_SLIME"));
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[lilaya.speech(Don't worry, I'm here for you,)]"
							+ " she says, pulling you into a loving hug for a moment before stepping back,"
							+ " [lilaya.speech(but first, I need to know what sort of race you're going to be giving birth to.)]"
						+ "</p>"
							
						+ "<p>"
							+ (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA)
								? "As she speaks, Lilaya starts stripping off your clothes, and as she gets access to your vagina, she lets out a little humming noise, "
								: "Your vagina is already exposed, and, after looking down at it for a moment, Lilaya lets out a little humming noise, "));
				
				switch(Main.game.getPlayer().getVaginaType()) {
					case HARPY:
						UtilText.nodeContentSB.append("[lilaya.speech(Ooh, alright, you're going to be laying some eggs, how exciting!"
								+ " I'm sure you're already feeling it, but some incredibly strong maternal instincts are going to be kicking in pretty soon, and you're only going to feel comfortable doing this in a very personal area."
								+ " I think using your room would be the best bet, follow me!)]"
								+ "</p>");
						break;
					default:
						UtilText.nodeContentSB.append("[lilaya.speech(Alright, so you're going to be giving birth to live young."
								+ " I've got a room set up for just that purpose, follow me!)]"
								+ "</p>");
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				switch(Main.game.getPlayer().getVaginaType()) {
					case HARPY:
						return new Response("Follow Lilaya", "Allow Lilaya to lead you up to your room.", LILAYA_ASSISTS_EGG_LAYING) {
							@Override
							public void effects() {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, false);
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, false);
							}
						};
					default:
						return new Response("Follow Lilaya", "Allow Lilaya to lead you to the birthing room.", LILAYA_ASSISTS_BIRTHING) {
							@Override
							public void effects() {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_BIRTHING_ROOM, false);
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_BIRTHING_ROOM, false);
							}
						};
				}
				

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_ASSISTS_BIRTHING = new DialogueNode("", "", true) {

		@Override
		public String getLabel() {
			return "Birthing Room";
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "ASSIST_BIRTHING_SLIME");
			}
			
			if (!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "ASSIST_BIRTHING_FIRST_TIME");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "ASSIST_BIRTHING");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Start", "Tell Lilaya that you're ready to give birth now.", LILAYA_ASSISTS_BIRTHING_DELIVERS){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, false);
						Main.game.getPlayer().endPregnancy(true);
						Main.game.getPlayer().setMana(0);
						
						if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
							Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
							Main.game.getPlayer().incrementVaginaCapacity(
									(Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier(),
									false);
						}
					}
				};

			} else if (index == 2) {
				return new Response("Knock out", "Ask Lilaya if she could give you something to knock you out. After all, she said you didn't need to be conscious for this.", LILAYA_ASSISTS_BIRTHING_KNOCK_OUT){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, false);
						Main.game.getPlayer().endPregnancy(true);
						Main.game.getPlayer().setMana(0);

						if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
							Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
							Main.game.getPlayer().incrementVaginaCapacity(
									(Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier(),
									false);
						}
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode LILAYA_ASSISTS_BIRTHING_DELIVERS = new DialogueNode("", "", true, true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<p>"
					+ "You nod to Lilaya, "
					+ UtilText.parsePlayerSpeech("I'm ready...")
					+ "</p>"

					+ "<p>"
					+ UtilText.parseSpeech("Right, here we go!", Main.game.getNpc(Lilaya.class))
					+ " she says, and you see sparks of purple energy start to run down her arms."
					+ "</p>");

			if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
				UtilText.nodeContentSB.append("<p>"
						+ "You strain your neck to look down, and see Lilaya bring her hands up to hover over your pregnant belly."
						+ " A huge wave of exhaustion crashes over you, and you remember what she said about how she was going to draw out your aura for this."
						+ " You struggle to keep your eyes open, but as you feel an intense pushing sensation deep with your slimy body, you're almost glad that you can't see what's happening."
						+ " You start to slip in and out of consciousness, and you find yourself having a strange dream."
					+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append("<p>"
							+ "You strain your neck to look down, and see Lilaya bring her hands up to hover over your pregnant belly."
							+ " A huge wave of exhaustion crashes over you, and you remember what she said about how she was going to draw out your aura for this."
							+ " You struggle to keep your eyes open, but as you feel an intense pushing sensation deep within your vagina, you're almost glad that you can't see what's happening."
							+ " You start to slip in and out of consciousness, and you find yourself having a strange dream."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append("<p>"
					+ "<i>"
					+ "You hear Lilaya speaking from somewhere beneath you, but you can't make out what she's saying..."
					+ "<br/><br/>");

			if(Main.game.getPlayer().getBreastRawMilkStorageValue() > 0
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)
					&& Main.game.getPlayer().getNippleShape()==NippleShape.NORMAL
					&& Main.getProperties().hasValue(PropertyValue.lactationContent)) {
				UtilText.nodeContentSB.append("You feel a desperate suckling at your nipples, and you're vaguely aware of something greedily drinking down mouthfuls of your [pc.milk]...");
			} else {
				UtilText.nodeContentSB.append("You feel a weight on your chest, and you're vaguely aware of something greedily drinking a bottle of milk as you cradle it in your arms...");
			}
			
			String offspringId = Util.randomItemFrom(Main.game.getPlayer().getLastLitterBirthed().getOffspring());
			try {
				GameCharacter offspring = Main.game.getNPCById(offspringId);
				if(offspring.isFeminine()) {
					UtilText.nodeContentSB.append("<br/><br/>Some time later, you imagine seeing a strangely familiar "
								+offspring.getSubspecies().getSingularFemaleName(offspring)
								+" bending down over you, who gives you a loving hug and a kiss on your cheek before departing...");
				} else {
					UtilText.nodeContentSB.append("<br/><br/>Some time later, you imagine seeing a strangely familiar "
								+offspring.getSubspecies().getSingularMaleName(offspring)
								+" bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
				}
				
			} catch(Exception ex) {
			}
			
			UtilText.nodeContentSB.append("</i>"
					+ "</p>");
					
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Pass out", "You have no energy left, and can't stay conscious any longer...", LILAYA_ASSISTS_BIRTHING_FINISHED){
					@Override
					public void effects() {
						if (Main.game.getPlayer().getQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY) == Quest.SIDE_PREGNANCY_LILAYA_THE_MIDWIFE) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_PREGNANCY, Quest.SIDE_UTIL_COMPLETE));
						}
						
						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));

						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_ASSISTS_BIRTHING_KNOCK_OUT = new DialogueNode("Your room", "", true, true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "[pc.speech(Have you got anything to drink? You know, so maybe I don't have to be awake for this...)]"
						+ " you ask, looking up at Lilaya hopefully."
					+ "</p>"
					+ "<p>"
						+ "She laughs and walks over to the drinks cabinet. "
						+ "[Lilaya.speech(You know, I thought you might ask for that! I prepared this for you earlier!)]"
					+ "</p>");

			if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
				UtilText.nodeContentSB.append("<p>"
							+ " She hands you a glass of what looks like ordinary sparkling water, but as you bring it to your lips and quickly down the liquid, you notice that it has a strong taste of peppermint."
							+ " Almost instantly, you feel your eyelids grow heavy, and as you lie back on the bed, you vaguely see Lilaya bringing her hands up to hover over your pregnant belly."
							+ " Sparks of purple energy start to run down her arms, and as you start to feel an intense pushing sensation deep within your slimy body, you're glad that you're not going to be awake for this."
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append("<p>"
						+ " She hands you a glass of what looks like ordinary sparkling water, but as you bring it to your lips and quickly down the liquid, you notice that it has a strong taste of peppermint."
						+ " Almost instantly, you feel your eyelids grow heavy, and as you lie back on the bed, you vaguely see Lilaya bringing her hands up to hover over your pregnant belly."
						+ " Sparks of purple energy start to run down her arms, and as you start to feel an intense pushing sensation deep within your vagina, you're glad that you're not going to be awake for this."
					+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Pass out", "The drink Lilaya gave you goes straight to your head, and you collapse back onto the bed as you lose consciousness.", LILAYA_ASSISTS_BIRTHING_FINISHED){
					@Override
					public void effects() {
						if (Main.game.getPlayer().getQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY) == Quest.SIDE_PREGNANCY_LILAYA_THE_MIDWIFE) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_PREGNANCY, Quest.SIDE_UTIL_COMPLETE));
						}
						
						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));

						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_ASSISTS_EGG_LAYING = new DialogueNode("", "", true) {

		@Override
		public String getLabel() {
			return "Your room";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
							+ "Taking your hands in hers, Lilaya leads you out of the lab and up to your room."
							+ " Following her in as she pushes open the door, you see that Rose has laid a soft protective covering over your bed, and a few bottles of milk have been placed on your bedside cabinet."
						+ "</p>");

			if (!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
				UtilText.nodeContentSB.append("<p>"
								+ "As Lilaya leads you over to the bed, she explains the situation, "
								+ "[lilaya.speech(Ok, so I know you haven't done anything like this before, so I'll quickly explain what's going to happen."
										+ " In order for you to start laying your eggs, I need to focus a special kind of arcane spell into your nice little bump here."
										+ " This isn't as intense as giving birth to live young, so you're going to stay conscious through all of this.)]"
							+ "</p>");
			} else {
				UtilText.nodeContentSB.append("<p>"
						+ "As Lilaya leads you over to the bed, she explains the situation, "
						+ "[lilaya.speech(Ok, so I know we've done this before, but I'll just quickly remind you of what's going to happen."
								+ " In order for you to start laying your eggs, I need to focus a special kind of arcane spell into your nice little bump here."
								+ " This isn't as intense as giving birth to live young, so you're going to stay conscious through all of this.)]"
					+ "</p>");
			}

			UtilText.nodeContentSB.append("<p>"
							+ "You glance across worriedly at Lilaya as she says this, but she squeezes your hand in hers and reassures you, "
							+ "[lilaya.speech(Don't worry, it doesn't hurt or anything!"
									+ " Now, after I've helped you lay your eggs, you're probably going to get aggressive if I hang around for too long, so I'll leave as quickly as possible."
									+ " Oh, and don't worry about your behaviour, I understand that your maternal instinct is going to get pretty intense.)]"
						+ "</p>"
						+ "<p>"
							+ "You fidget about uncomfortably a little, but Lilaya makes a soothing shushing noise as she pushes you down into the middle of your bed, before shuffling around to sit behind you. "
							+ "[lilaya.speech(It's alright, don't worry!"
								+ " So, after you've laid your eggs, you're going to incubate them for roughly twenty-four hours, during which time you'll find that you'll be unable to sleep or get any rest."
								+ " Once the eggs start hatching, all that built-up tiredness will suddenly wash over you, and you might collapse from exhaustion."
								+ " I'll be waiting to come in and help your children, so don't worry if you end up passing out!"
								+ " Oh, and remember that they're going to grow pretty rapidly once they've hatched, so they might have already flown the nest by the time you wake up.)]"
						+ "</p>"

						+ "<p>"
							+ "As she's been speaking, Lilaya has been shuffling around into a comfortable position behind you."
							+ " Kneeling down on the bed, she presses herself into your back, before reaching around to rub her hands up and down over your belly."
							+ " Moving down to apply a gentle pressure to your inner-thighs, Lilaya gets you to spread your legs, so that you're ready to start laying your eggs."
						+ "</p>"

						+ "<p>"
							+ "[lilaya.speech(Ok, all set?)]"
						+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Lay eggs", "Tell Lilaya that you're ready to lay your eggs now.", LILAYA_ASSISTS_EGG_LAYING_DELIVERS){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, false);
						Main.game.getPlayer().endPregnancy(true);
						Main.game.getPlayer().setMana(0);

						if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
							Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
							Main.game.getPlayer().incrementVaginaCapacity(
									(Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier(),
									false);
						}
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_ASSISTS_EGG_LAYING_DELIVERS = new DialogueNode("", "", true, true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "You nod as you look down at Lilaya's hands resting on your swollen stomach. "
						+ "[pc.speech(I'm ready...)]"
					+ "</p>"

					+ "<p>"
						+ "[lilaya.speech(Right, just try and relax...)]"
						+ " she whispers into your ear, and as she does so, you see a soft pink glow start to run down her arms."
					+ "</p>"

					+ "<p>"
						+ "Sliding her hands up and down over your pregnant belly, you feel her start to apply a gentle pressure with her thumbs, as though she's trying to push out the eggs within you."
						+ " As she does this, you start to feel an intense pressure building up deep within your vagina, and, collapsing back into Lilaya, you spread your legs and let out a desperate whine."
					+ "</p>"
						
					+ "<p>"
						+ "[lilaya.speech(That's right, now push... Go on, you can do it...)]"
						+ " Lilaya softly speaks into your ear, and, obeying her instructions, you try to push the pressure out from between your legs."
					+ "</p>"
					
					+ "<p>"
						+ "Looking down, you see the tip of your first egg peeking out from between your legs, and with a determined cry, you push with all your might."
						+ " You feel the egg stretching you out as you continue to apply pressure, but just as you feel that you can't keep going any longer, the egg pops free and slides out onto the bed beneath you."
					+ "</p>"
						
					+ "<p>"
						+ "[pc.speech(I-I did it,)]"
						+ " you sigh, looking down at the egg you've just laid."
						+ " It looks to be about the same size as an emu's egg, and as you marvel at its pale pink colouring and smooth shell, you feel yourself subconsciously reaching out to pick it up and cradle in your arms."
					+ "</p>"
						
					+ "<p>"
						+ "[lilaya.speech(Good [pc.girl], [pc.name]! But wait, just leave it for a moment, you've still got more to lay!)]"
						+ " Lilaya pulls you back into her, continuing to apply the gentle pressure on your stomach as she makes soothing shushing noises into your ear."
					+ "</p>"
						
					+ "<p>"
						+ "Doing as Lilaya says, you allow her to pull you back, but as she does so, a strange feeling suddenly washes over you,"
							+ " and it takes all of your willpower to prevent yourself from pushing Lilaya away and attempting to grab your egg."
						+ " Almost as though she sensed what you were thinking, Lilaya reminds you to try and overcome your maternal instincts, at least until you've finished laying all your eggs."
						+ " After promising to do your best, you set about pushing out the rest of your eggs, and within half an hour, a complete clutch of "
							+Util.intToString(Main.game.getPlayer().getLastLitterBirthed().getTotalLitterCount())+" eggs lie between your legs."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Protect the eggs!", "Why is Lilaya sitting so close behind you?! Maybe she wants to take your eggs for herself!", LILAYA_ASSISTS_EGG_LAYING_PROTECT_THE_EGGS) {
					@Override
					public void effects() {
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_ASSISTS_EGG_LAYING_PROTECT_THE_EGGS = new DialogueNode("", "", true, true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<p>"
						+ "As you look down at your clutch of eggs, you find yourself burning with an overwhelming need to protect them at all costs."
						+ " Throwing Lilaya's arms off of you, you collapse forwards, wrapping your [pc.arms] around your precious brood as you turn your head back and shout,"
						+ " [pc.speech(Get away! Don't hurt them!)]"
					+ "</p>"
					+ "<p>"
						+ "Lilaya instantly stands up and starts backing off towards the door,"
						+ " [lilaya.speech(Ok, ok, don't worry, I'm leaving. You'll be safe in here...)]"
					+ "</p>"
					+ "<p>"
						+ "Still making soothing shushing noises, she opens the door to your room and slips out, before closing it firmly behind her."
						+ " Having driven off the only possible threat to your nest, you shuffle around into a more comfortable position, pressing the heat of your body up against your eggs as you continue to cover them with your [pc.arms]."
						+ " You make sure to position yourself so that you're looking at the only entrance to your room, and, once satisfied that you're in a good position, you settle down to your vigilant watch..."
					+ "</p>"
					+ "<p>"
						+ "For the next twenty hours, you barely move a muscle, but despite the fact that you're lying on a warm, comfortable bed, you don't even consider for a moment to get any rest."
						+ " Your eyes only occasionally drift away from the door, but only to check your surroundings for any sign of danger."
					+ "</p>"
					+ "<p>"
						+ "As you enter the twenty-first hour of your incubation, you feel one of your eggs start to move."
						+ " Letting out a delighted cry, you shift position slightly to look down at your clutch."
						+ " A feeling of overwhelming joy rushes through you as you see each and every one of your eggs trembling and shifting about."
					+ "</p>"
					+ "<p>"
						+ "You watch, fascinated, as the first egg starts to crack.");

			String offspringId = Util.randomItemFrom(Main.game.getPlayer().getLastLitterBirthed().getOffspring());
			try {
				GameCharacter offspring = Main.game.getNPCById(offspringId);
				if(offspring.isFeminine()) {
					UtilText.nodeContentSB.append(
							" Within moments, a little head bursts through the top, and your eyes open wide as you see a tiny "
									+offspring.getSubspecies().getSingularFemaleName(offspring)
								+" crawling out."
							+ " A little egg-tooth is still attached to her forehead, but after a quick shake, she drops it off onto the bed beneath her.");
				} else {
					UtilText.nodeContentSB.append(
							" Within moments, a little head bursts through the top, and your eyes open wide as you see a tiny "
									+offspring.getSubspecies().getSingularMaleName(offspring)
								+" crawling out."
							+ " A little egg-tooth is still attached to his forehead, but after a quick shake, he drops it off onto the bed beneath him.");
				}
				
			} catch(Exception ex) {
			}
			
						
			UtilText.nodeContentSB.append(" As all the other eggs start cracking in turn, you feel a wave of exhaustion washing over you, and with what little strength you have left, you feebly call out for Lilaya."
					+ "</p>"
					+ "<p>"
						+ "As your head collapses down onto the bed, you vaguely see Lilaya rushing into the room."
						+ " A delighted smile breaks out across her face as she sees that your eggs are hatching, and just before you pass out, you see her bending down in order to start taking care of your children."
					+ "</p>");
			

				if(Main.game.getPlayer().getBreastRawMilkStorageValue() > 0
						&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)
						&& Main.game.getPlayer().getNippleShape()==NippleShape.NORMAL
						&& Main.getProperties().hasValue(PropertyValue.lactationContent)) {
					UtilText.nodeContentSB.append("<p>"
								+ "Some time later, you drift back into consciousness for a moment as you feel a desperate suckling at your nipples,"
									+ " and you're vaguely aware that it's your children who are eagerly drinking down mouthfuls of your [pc.milk]."
								+ " Letting your eyelids close again, you soon drift off into an exhausted sleep."
							+ "</p>");
				} else {
					UtilText.nodeContentSB.append("<p>"
								+ "Some time later, you drift back into consciousness for a moment as you feel a weight on your chest,"
									+ " and you're vaguely aware of cradling your children in your arms as they eagerly drink down bottle after bottle of the milk that Lilaya has provided for you."
								+ " Letting your eyelids close again, you soon drift off into an exhausted sleep."
							+ "</p>");
				}
			 
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Some time later", "You eventually wake up from your exhausted slumber...", LILAYA_ASSISTS_BIRTHING_FINISHED){
					@Override
					public void effects() {
						if (Main.game.getPlayer().getQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY) == Quest.SIDE_PREGNANCY_LILAYA_THE_MIDWIFE) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_PREGNANCY, Quest.SIDE_UTIL_COMPLETE));
						}
						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	
	private static String getOffspringDescriptor(GameCharacter offspring) {
		List<String> descriptors = new ArrayList<>();
		descriptors.add(offspring.getBodyShape().getName(false));
		descriptors.add(offspring.getHeight().getDescriptor());
		descriptors.add(offspring.getFemininity().getName(false));
		return Util.randomItemFrom(descriptors);
	}
	
	private static StringBuilder litterSB;
	public static final DialogueNode LILAYA_ASSISTS_BIRTHING_FINISHED = new DialogueNode("Your room", "", true, true) {

		@Override
		public String getContent() {
			litterSB = new StringBuilder();
			litterSB.append(
					"<p>"
						+ "You wake up some time later, lying in your bed."
						+ " With a groan, you sit up, pushing the covers back as you rub sleep from your eyes."
					+ "</p>"
					+ "<p>"
						+ "The first thing you notice is how easy it is to move, and you let out a little gasp as you start running your hands over your now-flat stomach."
						+ " As you do so, an unexpected twinge of sadness runs through you, and for a moment you find yourself deeply missing the reassuring feeling of having a big swollen belly."
					+ "</p>"
					+ "<p>"
						+ "As you move, you discover the second thing that's changed, and you let out a surprised gasp at the feeling between your legs."
						+ " Quickly moving your fingers down to your groin, you discover that your vagina has been stretched out considerably, and you let out an uncomfortable groan as you become aware of the dull ache within your "
						+ Capacity.getCapacityFromValue(Main.game.getPlayer().getVaginaStretchedCapacity()).getDescriptor()
						+ " pussy."
					+ "</p>"
					+ "<p>"
						+ "Glancing across to your bedside cabinet, the final thing you discover is a note from Lilaya."
						+ " Picking it up, you glance over the contents:"
					+ "</p>"

					+ "<p style='text-align:center;'>"
						+ "<i>"
						+ "Hey [pc.name], congratulations! Everything went perfectly!<br/>"
						+ "I hope you don't mind, but I collected a lot of data about your aura while we were doing this!"
						+ " I had to start analysing it straight away, and you were fast asleep anyway, so I hope you don't mind me leaving you there to rest."
						+ "<br/><br/>"
						+ "If you need anything, or perhaps if you'd like some more \"tests\", then you know where to find me!"
						+ "<br/><br/>"
						+ (Main.game.getPlayer().getLastLitterBirthed().getTotalLitterCount() > 1
								?"P.S. I managed to get your kids together for a picture before they left, it's under this note!"
								:"P.S. I got a picture of your kid before they left, it's under this note!")
						+ "</i>"
					+ "</p>"

					+ "<p>"
						+ "You put down the piece of paper and see the picture lying where Lilaya said it would be."
						+ " Picking it up, you feel tears welling up in your eyes as you see the result of your pregnancy smiling back at you."
					+ "</p>"
					+ "<p style='text-align:center;'>"
					+ "In the picture you see:");
			
			for(String id : Main.game.getPlayer().getLastLitterBirthed().getOffspring()) {
				try {
					GameCharacter offspring = Main.game.getNPCById(id);
					String descriptor = getOffspringDescriptor(offspring);
					litterSB.append("<br/><i style='color:"+offspring.getSubspecies().getColour(offspring).toWebHexString()+";'>"
							+ UtilText.parse(offspring, Util.capitaliseSentence(UtilText.generateSingularDeterminer(descriptor))+" "+descriptor+" [npc.race]")
							+ "</i>");
				} catch(Exception ex) {
				}
			}
			
			litterSB.append("</p>"
					+ "<p>"
					+ "After taking a minute to get your emotions under control, you put the picture away for safe-keeping, and think about what to do next."
					+ "</p>");

			return litterSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Get up", "Get out of bed, ready for a new day.", RoomPlayer.ROOM);

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
						+ " [arthur.speech(Anyway, when I returned, I discovered that Amber had already called for the enforcers."
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
