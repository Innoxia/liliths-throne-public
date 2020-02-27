package com.lilithsthrone.game.dialogue.places.submission;

import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.3
 * @version 0.2.6
 * @author Innoxia
 */
public class BatCaverns {

	public static final DialogueNode STAIRCASE = new DialogueNode("Winding Staircase", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "STAIRCASE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Submission", "Head back up to Submission."){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.SUBMISSION, PlaceType.SUBMISSION_BAT_CAVERNS, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CAVERN_DARK = new DialogueNode("Dark Cavern", "", false) {
		
		@Override
		public String getAuthor() {
			return "Duner & Innoxia";
		}
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "CAVERN_DARK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the cavern's dark depths. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
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
	
	public static final DialogueNode CAVERN_LIGHT = new DialogueNode("Bioluminescent Cavern", "", false) {
		
		@Override
		public String getAuthor() {
			return "Duner & Innoxia";
		}
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "CAVERN_LIGHT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the bioluminescent forest. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
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
	
	public static final DialogueNode RIVER = new DialogueNode("Underground River", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "RIVER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the sides of the river. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
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
	
	public static final DialogueNode RIVER_BRIDGE = new DialogueNode("Mushroom Bridge", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "RIVER_BRIDGE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the bridge's surroundings. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
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
	
	public static final DialogueNode RIVER_END = new DialogueNode("Underground River", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "RIVER_END");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the sides of the river. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
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
	
	public static final DialogueNode SLIME_LAKE = new DialogueNode("Slime Lake", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE"));
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLIME_QUEEN)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_QUEEN_UNKNOWN"));
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_THREE)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_QUEEN_KNOWLEDGE"));
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_QUEEN_GUESS"));
				
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the sides of the lake. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
							@Override
							public void effects() {
								DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true, true);
								Main.game.setContent(new Response("", "", dn));
							}
						};
						
			} else if(index==2 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				return new Response("Island by boat", "You could use the boat to travel across the lake and reach the island.", SLIME_LAKE_ISLAND) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_BOAT"));
						if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_PACIFIED"));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_READY_FIGHT"));
						}
					}
				};
				
			} else if(index==3 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly to island", "Fly across to the island.", SLIME_LAKE_ISLAND) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_FLY"));
							if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_PACIFIED"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_READY_FIGHT"));
							}
						}
					};
				} else {
					return new Response("Fly to island", "You aren't able to fly. It looks like you'll have to use the boat...", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SLIME_LAKE_ISLAND = new DialogueNode("Slime Lake", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Explore", "You'd need to be on the other side of the lake in order to explore this area!", null);
						
			} else if(index==2 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				return new Response("Return by boat", "Return to the other side of the lake by boat.", SLIME_LAKE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_BOAT_RETURN"));
					}
				};
				
			} else if(index==3 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Return by flying", "Return to the other side of the lake by flying.", SLIME_LAKE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_FLY_RETURN"));
						}
					};
					
				} else {
					return new Response("Return by flying", "You aren't able to fly. It looks like you'll have to use the boat...", null);
				}
				
			} else if (index==4) {
				return new ResponseEffectsOnly(
						"Open Door",
						"Push open the tower's front door and step inside."){
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.SLIME_QUEENS_LAIR_GROUND_FLOOR, PlaceType.SLIME_QUEENS_LAIR_ENTRANCE);
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "Walking up to the base of the stone tower, you place [pc.a_hand] on the iron-barred oaken door, and with a firm shove, push it open and step inside."
										+ "</p>");
								if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FOUR)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FOUR));
								}
								Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
							}
						};
						
			} else {
				return null;
			}
		}
	};
}
