package com.lilithsthrone.game.dialogue.places.submission;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.gender.GenderPreference;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.GamblingDenPatron;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.2
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
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription());
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
							Main.game.addNPC(new GamblingDenPatron(GenderPreference.getGenderFromUserPreferences(false, false), DicePokerTable.COPPER, false), false);
							Main.game.addNPC(new GamblingDenPatron(GenderPreference.getGenderFromUserPreferences(false, false), DicePokerTable.COPPER, false), false);
							Main.game.addNPC(new GamblingDenPatron(GenderPreference.getGenderFromUserPreferences(false, false), DicePokerTable.SILVER, false), false);
							Main.game.addNPC(new GamblingDenPatron(GenderPreference.getGenderFromUserPreferences(false, false), DicePokerTable.SILVER, false), false);
							Main.game.addNPC(new GamblingDenPatron(GenderPreference.getGenderFromUserPreferences(false, false), DicePokerTable.GOLD, false), false);
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
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE");
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Step back", "Do as the guard says and step back.") {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
						Main.game.setContent(new Response("", "", LILIN_PALACE_CAVERN));
					}
				};

			} else if (index == 2) {
				return new Response("Uniforms", "Ask Elizabeth why she and her troops are wearing Victorian uniforms.", LILIN_PALACE_GATE_UNIFORMS);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LILIN_PALACE_GATE_UNIFORMS = new DialogueNodeOld("Lyssieth's Palace Gate", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_UNIFORMS");
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 2) {
				return new Response("Uniforms", "You are already asking Elizabeth about her uniform.", null);

			} else {
				return LILIN_PALACE_GATE.getResponse(responseTab, index);
			}
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
			return null;
		}
	};
	

//	TODO: A large underground cave, and in the middle, a crude fortress has been built out of old wooden planks and pieces of sheet metal
//	Each of the three imp fortresses will be a repeatable quest (clearing the fortress), which, once cleared, will pacify the surrounding tunnels for a week or so.
	
	public static final DialogueNodeOld IMP_FORTRESS_1 = new DialogueNodeOld("Imp Fortress", "", false) {
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
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Approach", "Approach the fortress. (Not yet implemented!)", null);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld IMP_FORTRESS_2 = new DialogueNodeOld("Imp Fortress", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS"));

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_GROUP"));
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Approach", "Approach the fortress. (Not yet implemented!)", null);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld IMP_FORTRESS_3 = new DialogueNodeOld("Imp Fortress", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS"));

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_SEDUCERS"));
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Approach", "Approach the fortress. (Not yet implemented!)", null);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld IMP_FORTRESS_4 = new DialogueNodeOld("Imp Fortress", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS"));

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_BRAWLERS"));
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Approach", "Approach the fortress. (Not yet implemented!)", null);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld IMP_FORTRESS_5 = new DialogueNodeOld("Imp Fortress", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS"));

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_SLIMES"));
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Approach", "Approach the fortress. (Not yet implemented!)", null);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld IMP_FORTRESS_6 = new DialogueNodeOld("Imp Fortress", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS"));

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_BALANCED"));
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Approach", "Approach the fortress. (Not yet implemented!)", null);
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
