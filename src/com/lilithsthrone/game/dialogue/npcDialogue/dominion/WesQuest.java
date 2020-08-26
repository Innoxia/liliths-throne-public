package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.List;

import com.lilithsthrone.game.character.npc.dominion.Elle;
import com.lilithsthrone.game.character.npc.dominion.Wes;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Pathing;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.9.4
 * @version 0.3.9.4
 * @author Innoxia, concept by DSG
 */
public class WesQuest {

	public static final DialogueNode WES_QUEST_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Wes.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Wes.class).equipClothing();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_QUEST_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Agree", "Agree to meet this mysterious Enforcer near 'Pix's Playground'.", WES_QUEST_START_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/wes", "WES_QUEST_AGREE"));
					}
				};
				
			} else if(index==2) {
				return new Response("Question", "Ask the mysterious Enforcer why he wants to see you.", WES_QUEST_START_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/wes", "WES_QUEST_QUESTION"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode WES_QUEST_START_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Wes.class).returnToHome();
			Main.game.getNpc(Wes.class).equipClothing();
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_WES));
		}
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way through Dominion...", Main.game.getPlayer().getLocationPlace().getPlaceType().getDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode WES_QUEST_SHOPPING_ARCADE_MEETING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Wes.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Wes.class).equipClothing();
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Wes.class).incrementAffection(Main.game.getPlayer(), 5));
			Main.game.getNpc(Wes.class).setPlayerKnowsName(true);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_QUEST_SHOPPING_ARCADE_MEETING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Question", "Ask Wes for more details on what he wants you to do.", WES_QUEST_SHOPPING_ARCADE_MEETING_QUESTION);
				
			} else if(index==2) {
				return new Response("Refuse",
						"Tell Wes that you have absolutely no interest in helping him."
								+ "<br/>[style.italicsBad(This will fail the quest, and as such, you will permanently lose the opportunity to gain access to an Enforcer equipment store!)]",
								WES_QUEST_SHOPPING_ARCADE_MEETING_FAIL) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_BAD;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/wes", "WES_QUEST_SHOPPING_ARCADE_MEETING_FAIL_1"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode WES_QUEST_SHOPPING_ARCADE_MEETING_QUESTION = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_QUEST_SHOPPING_ARCADE_MEETING_QUESTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Agree", "Agree to do as Wes asks and collect evidence of his superior officer's corruption...", WES_QUEST_SHOPPING_ARCADE_MEETING_QUESTION_END);
				
			} else if(index==2) {
				return new Response("Refuse",
						"Tell Wes that you have absolutely no interest in helping him."
								+ "<br/>[style.italicsBad(This will fail the quest, and as such, you will permanently lose the opportunity to gain access to an Enforcer equipment store!)]",
								WES_QUEST_SHOPPING_ARCADE_MEETING_FAIL) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_BAD;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/wes", "WES_QUEST_SHOPPING_ARCADE_MEETING_FAIL_2"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode WES_QUEST_SHOPPING_ARCADE_MEETING_FAIL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Wes.class).returnToHome();
			Main.game.getNpc(Wes.class).equipClothing();
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestFailed(QuestLine.SIDE_WES, Quest.WES_FAIL));
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Wes.class).setAffection(Main.game.getPlayer(), -50));
		}
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
				return new Response("Continue", "Continue on your way through the Shopping Arcade...", Main.game.getPlayer().getLocationPlace().getPlaceType().getDialogue(false));
			}
			return null;
		}
	};

	public static final DialogueNode WES_QUEST_SHOPPING_ARCADE_MEETING_QUESTION_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Wes.class).returnToHome();
			Main.game.getNpc(Wes.class).equipClothing();
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_WES, Quest.WES_1));
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Wes.class).incrementAffection(Main.game.getPlayer(), 10));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_quest_recorder"), false));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_QUEST_SHOPPING_ARCADE_MEETING_QUESTION_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way through the Shopping Arcade...", Main.game.getPlayer().getLocationPlace().getPlaceType().getDialogue(false));
			}
			return null;
		}
	};
	

	public static final DialogueNode ELLE_SEARCH = new DialogueNode("Finding Elle", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Vector2i playerLoc = Main.game.getPlayer().getLocation();
			Main.game.getPlayer().setRandomLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_LIGHT, false);
			List<Cell> cells = Pathing.aStarPathing(Main.game.getWorlds().get(WorldType.BAT_CAVERNS).getCellGrid(), playerLoc, Main.game.getPlayer().getLocation(), true);
			for(Cell c : cells) {
				c.setDiscovered(true);
				c.setTravelledTo(true);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "ELLE_SEARCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Cave", "Take a look inside the cave...", ELLE_SEARCH_CAVE);
			}
			return null;
		}
	};
	
	public static final DialogueNode ELLE_SEARCH_CAVE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Elle.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "ELLE_SEARCH_CAVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Start recording", "Remain hidden behind the crates and start recording Elle's dealings...", ELLE_SEARCH_CAVE_RECORDING);
			}
			return null;
		}
	};
	
	public static final DialogueNode ELLE_SEARCH_CAVE_RECORDING = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Elle.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "ELLE_SEARCH_CAVE_RECORDING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave your hiding place and set out into the Bat Caverns...", ELLE_SEARCH_CAVE_LEAVE);
			}
			return null;
		}
	};
	
	public static final DialogueNode ELLE_SEARCH_CAVE_LEAVE = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_WES, Quest.WES_2));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "ELLE_SEARCH_CAVE_LEAVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getPlayer().getLocationPlace().getPlaceType().getDialogue(false).getResponse(responseTab, index);
		}
	};
	
}
