package com.lilithsthrone.game.dialogue.places.submission;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.GamblingDenPatron;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public class SubmissionGenericPlaces {

	public static final DialogueNodeOld WALKWAYS = new DialogueNodeOld("Walkways", "", false) {
		private static final long serialVersionUID = 1L;
		
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
	
	public static final DialogueNodeOld TUNNEL = new DialogueNodeOld("Tunnels", "", false) {
		private static final long serialVersionUID = 1L;

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
								DialogueNodeOld dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true, true);
								Main.game.setContent(new Response("", "", dn));
							}
						};
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld BAT_CAVERNS = new DialogueNodeOld("Bat Caverns", "", false) {
		private static final long serialVersionUID = 1L;

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
	
	public static final DialogueNodeOld RAT_WARREN = new DialogueNodeOld("The Rat Warren", "", false) {
		private static final long serialVersionUID = 1L;

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

	public static final DialogueNodeOld GAMBLING_DEN = new DialogueNodeOld("Gambling Den", "", false) {
		private static final long serialVersionUID = 1L;

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

	public static final DialogueNodeOld LILIN_PALACE_CAVERN = new DialogueNodeOld("Cavern", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_CAVERN");
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld LILIN_PALACE_GATE = new DialogueNodeOld("Lyssieth's Palace Gate", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL);
		}
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE"));
			
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_GRANTED"));
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_B_SIRENS_CALL) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_BLOCKED_QUEST_GAINED"));
			
			} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_C_SIRENS_FALL) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_BLOCKED_QUEST_COMPLETED"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_BLOCKED"));
			}
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
				if (index == 1) {
					return new Response("Uniforms", "Ask the succubus why she and her troops are wearing Victorian-era uniforms.", LILIN_PALACE_GATE_UNIFORMS);
				}
				
			} else {
				if (index == 1) {
					return new ResponseEffectsOnly("Step back", "Do as the guard says and step back.") {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
							Main.game.setContent(new Response("", "", LILIN_PALACE_CAVERN));
						}
					};
	
				} else if (index == 2) {
					return new Response("Uniforms", "Ask the succubus why she and her troops are wearing Victorian-era uniforms.", LILIN_PALACE_GATE_UNIFORMS);
	
				} else if (index == 3) {
					if(Main.game.getFortressDemonLeader().isSlave()) {
						return new Response("Audience", "Ask the guard how you can earn an audience with Lyssieth.", LILIN_PALACE_GATE_AUDIENCE_SKIP) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.FINGER_LYSSIETHS_RING), false));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN));
							}
						};
						
					} else {
						if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_A_INTO_THE_DEPTHS) {
							return new Response("Audience", "Ask the guard how you can earn an audience with Lyssieth.", LILIN_PALACE_GATE_AUDIENCE) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.LYSSIETHS_RING), false));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL));
								}
							};
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_B_SIRENS_CALL) {
							return new Response("Audience", "You have already asked the guard how to earn an audience with Lyssieth. You need to find, and then enslave, Lyssieth's daughter; 'The Dark Siren'.", null);
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_C_SIRENS_FALL) {
							return new Response("Report back", "Report to the guard that you have done as she asked and enslaved 'The Dark Siren'.", LILIN_PALACE_GATE_AUDIENCE_GRANTED) {
								@Override
								public void effects() {
									if(Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_AUDIENCE_GRANTED_HAS_RING"));
										Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.LYSSIETHS_RING));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().removedItemFromInventoryText(ItemType.LYSSIETHS_RING));
										
									} else {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_AUDIENCE_GRANTED_HAS_NO_RING"));
									}
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.FINGER_LYSSIETHS_RING), false));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN));
								}
							};					
						}
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNodeOld LILIN_PALACE_GATE_UNIFORMS = new DialogueNodeOld("Lyssieth's Palace Gate", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_UNIFORMS");
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			int indexForUniforms = 2;
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
				indexForUniforms =1;
			}
			
			if (index == indexForUniforms) {
				return new Response("Uniforms", "You are already asking the guard about her uniform.", null);

			} else {
				return LILIN_PALACE_GATE.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNodeOld LILIN_PALACE_GATE_AUDIENCE = new DialogueNodeOld("Lyssieth's Palace Gate", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_AUDIENCE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LILIN_PALACE_GATE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld LILIN_PALACE_GATE_AUDIENCE_GRANTED = new DialogueNodeOld("Lyssieth's Palace Gate", "", false, true) {
		private static final long serialVersionUID = 1L;

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
	
	public static final DialogueNodeOld LILIN_PALACE_GATE_AUDIENCE_SKIP = new DialogueNodeOld("Lyssieth's Palace Gate", "", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_AUDIENCE_SKIP");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LILIN_PALACE_GATE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld LILIN_PALACE = new DialogueNodeOld("Lyssieth's Palace", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Knock", "KNock on the palace gates, and wait for one of the servants inside to answer. (Will be added for v0.3!)", null);
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld IMP_FORTRESS_ALPHA = new DialogueNodeOld("Crude Fortress", "", false) {
		private static final long serialVersionUID = 1L;

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
	
	public static final DialogueNodeOld IMP_FORTRESS_FEMALES = new DialogueNodeOld("Crude Fortress", "", false) {
		private static final long serialVersionUID = 1L;

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
	
	public static final DialogueNodeOld IMP_FORTRESS_MALES = new DialogueNodeOld("Crude Fortress", "", false) {
		private static final long serialVersionUID = 1L;

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
	
	public static final DialogueNodeOld IMP_FORTRESS_DEMON = new DialogueNodeOld("Stone Citadel", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelEncountered, true);
			
			UtilText.nodeContentSB.setLength(0);

			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_DEFEATED"));
				
			} else if((Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY)
							&& Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_2)
							&& Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_3))
						|| Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEYS"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL"));
			}
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Enter", "<i>This will be in the next version, I promise!</i>", null);
//				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
//					return new Response("Enter", "Enter the ruins of the Dark Siren's citadel.", PlaceType.FORTRESS_DEMON_ENTRANCE.getDialogue(false)) {
//						@Override
//						public void effects() {
//							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_ENTRANCE);
//						}
//					};
//					
//				} else if((Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY)
//								&& Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_2)
//								&& Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_3))
//							|| Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
//					return new Response("Enter", "Enter the citadel.", PlaceType.FORTRESS_DEMON_ENTRANCE.getDialogue(false)) {
//						@Override
//						public void effects() {
//							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEY_ENTRY"));
//							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_ENTRANCE);
//
//							if(!Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
//								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain")), false));
//								Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY));
//								Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2));
//								Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3));
//							}
//						}
//					};
//					
//				} else {
//					return new Response("Enter", "You don't have a key...", null);
//				}
				
			} else {
				return null;
			}
		}
	};

	// Entrance and exits:

	public static final DialogueNodeOld SEWER_ENTRANCE = new DialogueNodeOld("Enforcer Checkpoint", "", false) {
		private static final long serialVersionUID = 1L;

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
	
	public static final DialogueNodeOld CLAIRE_INFO_REPORT_BACK = new DialogueNodeOld("Enforcer Checkpoint", "", true, true) {
		private static final long serialVersionUID = 1L;

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

	public static final DialogueNodeOld CLAIRE_INFO_SLIME_QUEEN_REPORT_BACK = new DialogueNodeOld("Enforcer Checkpoint", "", true, true) {
		private static final long serialVersionUID = 1L;

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
	
	public static final DialogueNodeOld CLAIRE_INFO_SUBMISSION_SOCIETY = new DialogueNodeOld("Enforcer Checkpoint", "", false) {
		private static final long serialVersionUID = 1L;

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
	
	public static final DialogueNodeOld CLAIRE_INFO_LYSSIETH = new DialogueNodeOld("Enforcer Checkpoint", "", false) {
		private static final long serialVersionUID = 1L;

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
	
	public static final DialogueNodeOld CLAIRE_INFO_TELEPORTATION = new DialogueNodeOld("Enforcer Checkpoint", "", false) {
		private static final long serialVersionUID = 1L;

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
