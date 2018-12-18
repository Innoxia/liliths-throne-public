package com.lilithsthrone.game.dialogue.places.submission;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.character.npc.submission.FortressDemonLeader;
import com.lilithsthrone.game.character.npc.submission.FortressFemalesLeader;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.character.npc.submission.GamblingDenPatron;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.story.LyssiethReveal;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public class SubmissionGenericPlaces {

	public static final DialogueNode WALKWAYS = new DialogueNode("Walkways", "", false) {
		
		@Override
		public String getAuthor() {
			return "Duner & Innoxia";
		}
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "WALKWAYS")
					+ (Math.random()<0.2f
							?UtilText.parseFromXMLFile("places/submission/submissionPlaces", "WALKWAYS_EXTRA")
							:"");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode TUNNEL = new DialogueNode("Tunnels", "", false) {

		@Override
		public String getAuthor() {
			return "Duner";
		}

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "TUNNEL"));

			boolean pacified = true;
			BaseColour colour = Main.game.getPlayer().getLocationPlace().getPlaceType().getColour();
			switch(Main.game.getPlayer().getLocationPlace().getPlaceType()) {
				case SUBMISSION_IMP_TUNNELS_ALPHA:
					pacified = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated);
					break;
				case SUBMISSION_IMP_TUNNELS_DEMON:
					pacified = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated);
					break;
				case SUBMISSION_IMP_TUNNELS_FEMALES:
					pacified = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated);
					break;
				case SUBMISSION_IMP_TUNNELS_MALES:
					pacified = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated);
					break;
				default:
					break;
			}
			if(!pacified) {
				UtilText.nodeContentSB.append(
						"<span color:"+colour.toWebHexString()+";>"
						+ UtilText.parseFromXMLFile("places/submission/submissionPlaces", "TUNNEL_IMP_CONTROL")
						+"</span>");
			}
			
			for(GameCharacter npc : Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell())) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription(!pacified));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the tunnels. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
							@Override
							public void effects() {
								DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true, true);
								Main.game.setContent(new Response("", "", dn));
							}
						};
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode BAT_CAVERNS = new DialogueNode("Bat Caverns", "", false) {

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "BAT_CAVERNS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Bat Caverns", "Enter the bat caverns.") {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "BAT_CAVERNS_ENTRY"));
						Main.mainController.moveGameWorld(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_ENTRANCE, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode RAT_WARREN = new DialogueNode("The Rat Warren", "", false) {

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "RAT_WARREN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Knock", "Knock on the door. <b>Not yet added!</b> (This will be a mini-area, which will be related to a large side-quest.)", null);

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode GAMBLING_DEN = new DialogueNode("Gambling Den", "", false) {

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "GAMBLING_DEN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Gambling Den", "Enter the Gambling Den.", GamblingDenDialogue.ENTRANCE) {
					@Override
					public void effects() {
						List<NPC> gamblersPresent = Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.GAMBLING_DEN).getCell(PlaceType.GAMBLING_DEN_GAMBLING));
						
						for(NPC npc : gamblersPresent) {
							if(npc instanceof GamblingDenPatron) {
								Main.game.removeNPC(npc);
							}
						}
						
						try {
							Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.COPPER, false), false);
							Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.COPPER, false), false);
							Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.SILVER, false), false);
							Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.SILVER, false), false);
							Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.GOLD, false), false);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode LILIN_PALACE_CAVERN = new DialogueNode("Cavern", "", false) {

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_CAVERN"));
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethIntroduced)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_CAVERN_MET_ELIZABETH"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_CAVERN_NOT_MET_ELIZABETH"));
			}
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode LILIN_PALACE_GATE = new DialogueNode("Lyssieth's Palace Gate", "", true) {

		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL);
		}
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public String getContent() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutUniforms, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutSurname, false);
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE"));
			
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_GRANTED"));
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_C_SIRENS_FALL) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_BLOCKED_QUEST_COMPLETED"));
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_B_SIRENS_CALL) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_BLOCKED_QUEST_GAINED"));
			
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethIntroduced)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_BLOCKED_INTRODUCED"));
					
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_BLOCKED"));
			}
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			
			// First time visiting this tile:
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethIntroduced)) {
				if(index==1) {
					return new Response("Introductions", "Tell the succubus and her troops who you are.", LILIN_PALACE_GATE_GENERIC_TALK) {
						@Override
						public void effects() {
							Main.game.getNpc(Elizabeth.class).setPlayerKnowsName(true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethIntroduced, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_INTRODUCTION"));
						}
					};
				}
				return null;
			}
			
			// Completed the Siren's quest:
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
				if (index == 1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethAskedAboutUniforms)) {
						return new Response("Uniforms", "You've just asked ELizabeth about her uniforms...", null);
					}
					return new Response("Uniforms", "Ask Elizabeth why she and her troops are wearing historical uniforms.", LILIN_PALACE_GATE_GENERIC_TALK) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutUniforms, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_UNIFORMS"));
						}
					};
					
				} else if (index == 2) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethAskedAboutSurname)) {
						return new Response("Surname", "You've just asked ELizabeth about her surname...", null);
					}
					return new Response("Surname", "Ask Elizabeth why she didn't want to be addressed by her surname.", LILIN_PALACE_GATE_GENERIC_TALK) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutSurname, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_SURNAME"));
						}
					};
				}
				return null;
			}

			// Handing in the Siren's quest:
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_C_SIRENS_FALL) {
				if (index == 1) {
					return new Response("Report", "Tell Elizabeth how you handled the Siren.", LILIN_PALACE_GATE_GENERIC_TALK) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_COMPLETED_QUEST_WITH_COMBAT"));
								Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.LYSSIETHS_RING));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().removedItemFromInventoryText(ItemType.LYSSIETHS_RING));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_COMPLETED_QUEST_WITH_TRICKERY"));
							}
							if(!Main.game.getPlayer().hasClothingType(ClothingType.FINGER_LYSSIETHS_RING, true)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.FINGER_LYSSIETHS_RING), false));
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN));
						}
					};
					
				}
				return null;
			}
			
			// Normal dialogue, for before completing the Siren's quest:
			if (index == 1) {
				return new ResponseEffectsOnly("Step back", "Decide to step away from Elizabeth and the royal guard, and head back into Submission.") {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
						Main.game.setContent(new Response("", "", LILIN_PALACE_CAVERN));
					}
				};

			} else if (index == 2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethAskedAboutUniforms)) {
					return new Response("Uniforms", "You've just asked ELizabeth about her uniforms...", null);
				}
				return new Response("Uniforms", "Ask Elizabeth why she and her troops are wearing historical uniforms.", LILIN_PALACE_GATE_GENERIC_TALK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutUniforms, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_UNIFORMS"));
					}
				};

			} else if (index == 3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethAskedAboutSurname)) {
					return new Response("Surname", "You've just asked ELizabeth about her surname...", null);
				}
				return new Response("Surname", "Ask Elizabeth why she didn't want to be addressed by her surname.", LILIN_PALACE_GATE_GENERIC_TALK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutSurname, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_SURNAME"));
					}
				};
			} else if (index == 4) {
				if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_A_INTO_THE_DEPTHS) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
						return new Response("Audience", "Ask Elizabeth how you can earn an audience with Lyssieth.", LILIN_PALACE_GATE_GENERIC_TALK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_AUDIENCE_SKIP"));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.FINGER_LYSSIETHS_RING), false));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN));
							}
						};
						
					} else {
						return new Response("Audience", "Ask Elizabeth how you can earn an audience with Lyssieth.", LILIN_PALACE_GATE_GENERIC_TALK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_AUDIENCE"));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.LYSSIETHS_RING), false));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL));
							}
						};
					}
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_B_SIRENS_CALL) {
					return new Response("Audience", "You have already asked Elizabeth how to earn an audience with Lyssieth. You need to find, and then enslave, Lyssieth's daughter; the 'dark siren'.", null);
					
				} else {
					return new Response("Audience",
							"Elizabeth said that Lyssieth isn't receiving visitors, and, for the moment at least, you don't have a particular reason for needing to see her."
								+ " Perhaps you'll need to ask for an audience with Lyssieth at some point in the future...",
							null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode LILIN_PALACE_GATE_GENERIC_TALK = new DialogueNode("Lyssieth's Palace Gate", "", true, true) {

		@Override
		public boolean isTravelDisabled() {
			return LILIN_PALACE_GATE.isTravelDisabled();
		}
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LILIN_PALACE_GATE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LILIN_PALACE = new DialogueNode("Lyssieth's Palace", "", false) {

		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE"));
			
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_D_MEETING_A_LILIN) {
				if(Main.game.getPlayer().hasCompanions()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_ELIZABETH_LEADS_COMPANIONS", Main.game.getPlayer().getMainCompanion()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_ELIZABETH_LEADS"));
				}
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_ELIZABETH_ESCORT"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_D_MEETING_A_LILIN) {
					return new Response("Enter",
							"Enter the palace with Elizabeth, who will then proceed to lead you to the throne room.<br/>[style.italicsMinorBad(This will dismiss all of your companions, who will be returned home.)]",
							LyssiethReveal.ENTRANCE_WITH_ELIZABETH) {
						@Override
						public void effects() {
							Main.game.getPlayer().removeAllCompanions(true);
							Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_ENTRANCE);
							Main.game.getNpc(Elizabeth.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_ENTRANCE);
							// Make sure siren is placed properly:
							GameCharacter siren = Main.game.getNpc(FortressDemonLeader.class);
							siren.setObedience(ObedienceLevel.NEGATIVE_ONE_DISOBEDIENT.getMedianValue());
							siren.setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_THRONE_ROOM);
							siren.setNearestLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_CORRIDOR, false);
							siren.setHomeLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_ROOM_SIREN);
							siren.unequipAllClothingIntoVoid(true);
							siren.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_LACY_PANTIES, Colour.CLOTHING_PURPLE_VERY_DARK, false), true, siren);
							siren.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_LACY_PLUNGE_BRA, Colour.CLOTHING_PURPLE_VERY_DARK, false), true, siren);
							siren.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_TRAINER_SOCKS, Colour.CLOTHING_WHITE, false), true, siren);
							siren.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_BLACK, false), true, siren);
							siren.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_PENCIL_SKIRT, Colour.CLOTHING_BLACK, false), true, siren);
							siren.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_torso_feminine_short_sleeve_shirt", Colour.CLOTHING_PINK_LIGHT, false), true, siren);
							siren.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_WOMENS_WATCH, Colour.CLOTHING_PINK_LIGHT, false), true, siren);
							siren.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_BLACK_STEEL, false), true, siren);
							siren.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_darkSiren_siren_seal", false), true, siren);
						}
					};
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) {
					return new Response("Enter", "Tell Elizabeth that you'd like to enter the palace, and have her unlock the doors for you.", PlaceType.LYSSIETH_PALACE_ENTRANCE.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_ENTRANCE);
							Main.game.getNpc(Elizabeth.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_GATE);
						}
					};
					
				} else {
					return new Response("Enter", "The doors are locked...", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode IMP_FORTRESS_ALPHA = new DialogueNode("Crude Fortress", "", false) {

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS"));

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ALPHA"));

			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_DEFEATED"));
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaPacified)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_PACIFIED"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			boolean canEnter = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaPacified);
			
			if(index==1) {
				return new Response(canEnter?"Enter":"Approach",
						canEnter?"Enter the fortress.":"Approach the guards at the entrance to the fortress.",
								PlaceType.FORTRESS_ALPHA_ENTRANCE.getDialogue(false)) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
							if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_ALPHA, false).isEmpty()) {
								ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_ALPHA);
							} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_ALPHA, false).contains(Main.game.getNpc(FortressAlphaLeader.class))) {
								Main.game.getNpc(FortressAlphaLeader.class).setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP, true);
							} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_ALPHA).isEmpty()) {
								ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_ALPHA);
							}
						}
						Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_ENTRANCE);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("Fly",
							canEnter
								?"As you're able to simply walk through the front gate, there's no need to do this unless you were just trying to show off..."
								:"Fly over the wall of the fortress, thereby avoiding a confrontation with the imp guards at the gate.",
							PlaceType.FORTRESS_ALPHA_COURTYARD.getDialogue(false)) {
						@Override
						public void effects() {
							if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
								if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_ALPHA, false).isEmpty()) {
									ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_ALPHA);
								} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_ALPHA, false).contains(Main.game.getNpc(FortressAlphaLeader.class))) {
									Main.game.getNpc(FortressAlphaLeader.class).setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP, true);
								} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_ALPHA).isEmpty()) {
									ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_ALPHA);
								}
							}
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_FLY_ENTRY"));
							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_COURTYARD);
						}
					};
				} else {
					return new Response("Fly", "Either you or a member of your party is unable to fly, so you're unable to access the fortress by flying over the walls!", null);
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode IMP_FORTRESS_FEMALES = new DialogueNode("Crude Fortress", "", false) {

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS"));

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_FEMALES"));

			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_DEFEATED"));
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesPacified)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_PACIFIED"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY"));
			}
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			boolean canEnter = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesPacified);
			
			if(index==1) {
				return new Response(canEnter?"Enter":"Approach",
						canEnter?"Enter the fortress.":"Approach the guards at the entrance to the fortress.",
								PlaceType.FORTRESS_FEMALES_ENTRANCE.getDialogue(false)) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
							if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_FEMALES, false).isEmpty()) {
								ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_FEMALES);
							} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_FEMALES, false).contains(Main.game.getNpc(FortressFemalesLeader.class))) {
								Main.game.getNpc(FortressFemalesLeader.class).setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP, true);
							} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_FEMALES).isEmpty()) {
								ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_FEMALES);
							}
						}
						Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_ENTRANCE);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("Fly",
							canEnter
								?"As you're able to simply walk through the front gate, there's no need to do this unless you were just trying to show off..."
								:"Fly over the wall of the fortress, thereby avoiding a confrontation with the imp guards at the gate.",
							PlaceType.FORTRESS_FEMALES_COURTYARD.getDialogue(false)) {
						@Override
						public void effects() {
							if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
								if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_FEMALES, false).isEmpty()) {
									ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_FEMALES);
								} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_FEMALES, false).contains(Main.game.getNpc(FortressFemalesLeader.class))) {
									Main.game.getNpc(FortressFemalesLeader.class).setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP, true);
								} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_FEMALES).isEmpty()) {
									ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_FEMALES);
								}
							}
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_FLY_ENTRY"));
							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_COURTYARD);
						}
					};
				} else {
					return new Response("Fly", "Either you or a member of your party is unable to fly, so you're unable to access the fortress by flying over the walls!", null);
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode IMP_FORTRESS_MALES = new DialogueNode("Crude Fortress", "", false) {

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS"));

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_MALES"));

			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_DEFEATED"));
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesPacified)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_PACIFIED"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY"));
			}
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			boolean canEnter = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesPacified);
			
			if(index==1) {
				return new Response(canEnter?"Enter":"Approach",
						canEnter?"Enter the fortress.":"Approach the guards at the entrance to the fortress.",
								PlaceType.FORTRESS_MALES_ENTRANCE.getDialogue(false)) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
							if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_MALES, false).isEmpty()) {
								ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_MALES);
							} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_MALES, false).contains(Main.game.getNpc(FortressMalesLeader.class))) {
								Main.game.getNpc(FortressMalesLeader.class).setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP, true);
							} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_MALES).isEmpty()) {
								ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_MALES);
							}
						}
						Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_ENTRANCE);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("Fly",
							canEnter
								?"As you're able to simply walk through the front gate, there's no need to do this unless you were just trying to show off..."
								:"Fly over the wall of the fortress, thereby avoiding a confrontation with the imp guards at the gate.",
							PlaceType.FORTRESS_MALES_COURTYARD.getDialogue(false)) {
						@Override
						public void effects() {
							if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
								if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_MALES, false).isEmpty()) {
									ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_MALES);
								} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_MALES, false).contains(Main.game.getNpc(FortressMalesLeader.class))) {
									Main.game.getNpc(FortressMalesLeader.class).setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP, true);
								} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_MALES).isEmpty()) {
									ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_MALES);
								}
							}
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_FLY_ENTRY"));
							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_COURTYARD);
						}
					};
				} else {
					return new Response("Fly", "Either you or a member of your party is unable to fly, so you're unable to access the fortress by flying over the walls!", null);
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode IMP_FORTRESS_DEMON = new DialogueNode("Stone Citadel", "", false) {

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
				return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_DEFEATED");
			}
			
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelEncountered, true);
			
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_BASE"));

			if((Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY)
							&& Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_2)
							&& Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_3))
						|| Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEYS"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_NO_KEYS"));
			}
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
					return new Response("Enter", "Enter the ruins of the Dark Siren's citadel.", PlaceType.FORTRESS_DEMON_ENTRANCE.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_ENTRANCE);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_RUINS_ENTRY"));
						}
					};
					
				} else if((Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY)
								&& Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_2)
								&& Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_3))
							|| Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
					return new Response("Enter", "Enter the citadel.", FORTRESS_DEMON_ENTRANCE_KEY_ENTRY) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_ENTRANCE);
							ImpCitadelDialogue.applyEntry();

							if(!Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEY_ENTRY"));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain")), false));
								Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY));
								Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2));
								Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEY_ENTRY_REPEAT"));
								if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonImpsDefeated)) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEY_ENTRY_REPEAT_IMPS"));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEY_ENTRY_REPEAT_NO_IMPS"));
								}
							}
						}
					};
					
				} else {
					return new Response("Enter", "You don't have the three keys required to open the gates...", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode FORTRESS_DEMON_ENTRANCE_KEY_ENTRY = new DialogueNode("", "", false) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ImpCitadelDialogue.ENTRANCE.getResponse(responseTab, index);
		}
	};

	// Entrance and exits:

	public static final DialogueNode SEWER_ENTRANCE = new DialogueNode("Enforcer Checkpoint", "", false) {

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "SEWER_ENTRANCE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Dominion", "Head back up to Dominion."){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.DOMINION, PlaceType.DOMINION_EXIT_TO_SUBMISSION, true);
					}
				};

			} else if (index == 2) {
				return new Response("Information", "Ask Claire about Submission society.", CLAIRE_INFO_SUBMISSION_SOCIETY);

			} else if (index == 3) {
				return new Response("Lyssieth", "Ask Claire about Lyssieth.", CLAIRE_INFO_LYSSIETH);

			} else if (index == 4) {
				return new Response("Teleportation", "Ask Claire about teleportation.", CLAIRE_INFO_TELEPORTATION);

			} else if(index==5) {
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN)==Quest.SLIME_QUEEN_TWO) {
					return new Response("Report Back", "Report what the slime said about a 'Slime Queen'.", CLAIRE_INFO_REPORT_BACK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(1000));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_THREE));
						}
					};
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN)==Quest.SLIME_QUEEN_SIX_SUBMIT
							|| Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN)==Quest.SLIME_QUEEN_SIX_FORCE
							|| Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN)==Quest.SLIME_QUEEN_SIX_CONVINCE) {
					return new Response("Report Back", "Report to Claire that you've defeated the Slime Queen.", CLAIRE_INFO_SLIME_QUEEN_REPORT_BACK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(20000));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SIDE_UTIL_COMPLETE));
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CLAIRE_INFO_REPORT_BACK = new DialogueNode("Enforcer Checkpoint", "", true, true) {

		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_REPORT_BACK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Let Claire get back on with her work, and continue on your way.", SEWER_ENTRANCE);
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode CLAIRE_INFO_SLIME_QUEEN_REPORT_BACK = new DialogueNode("Enforcer Checkpoint", "", true, true) {

		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeQueenHelped)) {
				return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_SLIME_QUEEN_REPORT_BACK_LIE");
			} else {
				return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_SLIME_QUEEN_REPORT_BACK");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Let Claire get back on with her work, and continue on your way.", SEWER_ENTRANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CLAIRE_INFO_SUBMISSION_SOCIETY = new DialogueNode("Enforcer Checkpoint", "", false) {

		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_SUBMISSION_SOCIETY"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("Information", "You are already asking Claire about Submission society!", null);
			}
			return SEWER_ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CLAIRE_INFO_LYSSIETH = new DialogueNode("Enforcer Checkpoint", "", false) {

		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_LYSSIETH"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==3) {
				return new Response("Lyssieth", "You are already asking Claire about Lyssieth!", null);
			}
			return SEWER_ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CLAIRE_INFO_TELEPORTATION = new DialogueNode("Enforcer Checkpoint", "", false) {

		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_TELEPORTATION"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==4) {
				return new Response("Teleportation", "You are already asking Claire about Teleportation!", null);
			}
			return SEWER_ENTRANCE.getResponse(responseTab, index);
		}
	};

}
