package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.dominion.Elle;
import com.lilithsthrone.game.character.npc.dominion.Wes;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.EnforcerHQDialogue;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Pathing;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.9.4
 * @version 0.3.9.5
 * @author DSG (concept and characters), Innoxia
 */
public class WesQuest {

	public static final String QUEST_COMPLETION_MINUTES_TIMER_ID = "wes_completion_timer";
	
	private static List<String> backgroundTalkIds = new ArrayList<>();
	private static List<String> workTalkIds = new ArrayList<>();
	
	static {
		resetBackgroundIds();
		resetWorkTalkIds();
	}
	
	private static void resetBackgroundIds() {
		backgroundTalkIds = Util.newArrayListOfValues("1", "2", "3", "4", "5");
	}

	private static void resetWorkTalkIds() {
		workTalkIds = Util.newArrayListOfValues("1", "2", "3", "4", "5");
	}
	
	public static final DialogueNode WES_QUEST_START = new DialogueNode("A Sudden Interruption", "", true) {
		@Override
		public void applyPreParsingEffects() {
			((Wes)Main.game.getNpc(Wes.class)).applyDisguise();
			Main.game.getNpc(Wes.class).setLocation(Main.game.getPlayer(), false);
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
				return new Response("Agree", "Agree to meet this mysterious Enforcer outside of the Shopping Arcade's antiques shop.", WES_QUEST_START_END) {
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
			((Wes)Main.game.getNpc(Wes.class)).applyDisguise();
			Main.game.getNpc(Wes.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Wes.class).setPlayerKnowsName(true);
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Wes.class).incrementAffection(Main.game.getPlayer(), 5));
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
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestRefused)) {
				return WES_QUEST_SHOPPING_ARCADE_MEETING_QUESTION.getResponse(responseTab, index);
				
			} else {
				if(index==1) {
					return new Response("Question", "Ask Wes for more details on what he wants you to do.", WES_QUEST_SHOPPING_ARCADE_MEETING_QUESTION);
				}
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
						"Tell Wes that you don't want to get involved in something like this."
								+ "<br/>[style.italicsMinorGood(You will be able to change your mind and seek out Wes to help him again later.)]",
								WES_QUEST_SHOPPING_ARCADE_MEETING_FAIL);
			}
			return null;
		}
	};

	public static final DialogueNode WES_QUEST_SHOPPING_ARCADE_MEETING_FAIL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Wes.class).returnToHome();
			Main.game.getNpc(Wes.class).equipClothing();
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestRefused)) {
				Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Wes.class).setAffection(Main.game.getPlayer(), -10));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_QUEST_SHOPPING_ARCADE_MEETING_FAIL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way through the Shopping Arcade...", Main.game.getPlayer().getLocationPlace().getPlaceType().getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestMet, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestRefused, true);
					}
				};
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
				return new Response("Enter cave", "Enter the cave and take a look around...", ELLE_SEARCH_CAVE);
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

	public static final DialogueNode CLAIRE_ELLE_EVIDENCE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_WES, Quest.WES_3_WES));
			Main.game.getPlayer().removeItemByType(ItemType.getItemTypeFromId("innoxia_quest_recorder"));
			Main.game.getDialogueFlags().setSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID, Main.game.getMinutesPassed());
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "CLAIRE_ELLE_EVIDENCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SubmissionGenericPlaces.CLAIRE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CANDI_ELLE_EVIDENCE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_WES, Quest.WES_3_WES));
			Main.game.getPlayer().removeItemByType(ItemType.getItemTypeFromId("innoxia_quest_recorder"));
			Main.game.getDialogueFlags().setSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID, Main.game.getMinutesPassed());
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "CANDI_ELLE_EVIDENCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getPlayer().getLocationPlace().getPlaceType().getDialogue(false).getResponse(responseTab, index);
		}
	};

	public static final DialogueNode APPROACH_ELLE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Elle.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 45*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "APPROACH_ELLE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Explain", "Reveal to Elle everything that Wes told you and show her the evidence on the arcane recorder.", APPROACH_ELLE_EXPLAIN);
			}
			return null;
		}
	};

	public static final DialogueNode APPROACH_ELLE_EXPLAIN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_WES, Quest.WES_3_ELLE));
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Elle.class).incrementAffection(Main.game.getPlayer(), 25));
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Wes.class).setAffection(Main.game.getPlayer(), -100));
			Main.game.getPlayer().removeItemByType(ItemType.getItemTypeFromId("innoxia_quest_recorder"));
			Main.game.getNpc(Elle.class).returnToHome();
			Main.game.getDialogueFlags().setSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID, Main.game.getMinutesPassed());
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "APPROACH_ELLE_EXPLAIN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way...", Main.game.getPlayer().getLocationPlace().getPlaceType().getDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode INTRO_HQ_WES = new DialogueNode("Meeting Wes", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "INTRO_HQ_WES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "Wait for Wes to show up.", INTRO_HQ_WES_ARRIVE);
			}
			return null;
		}
	};

	public static final DialogueNode INTRO_HQ_WES_ARRIVE = new DialogueNode("Meeting Wes", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Wes.class).addSlave(Main.game.getNpc(Elle.class));
			Main.game.getNpc(Elle.class).equipClothing();
			Main.game.getNpc(Wes.class).equipClothing();
			Main.game.getNpc(Wes.class).dailyUpdate(); // To stock items for sale
			Main.game.getNpc(Wes.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Elle.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "INTRO_HQ_WES_ARRIVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Follow", "Follow Wes into the Enforcer HQ.", INTRO_HQ_WES_OFFICE);
			}
			return null;
		}
	};
	
	public static final DialogueNode INTRO_HQ_WES_OFFICE = new DialogueNode("Meeting Wes", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Vector2i playerLoc = Main.game.getPlayer().getLocation();
			
			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_OFFICE_QUARTERMASTER);
			Main.game.getNpc(Wes.class).setLocation(Main.game.getPlayer(), false);
			
			List<Cell> cells = Pathing.aStarPathing(Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCellGrid(), playerLoc, Main.game.getPlayer().getLocation(), true);
			for(Cell c : cells) {
				c.setDiscovered(true);
				c.setTravelledTo(true);
			}
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_WES, Quest.SIDE_UTIL_COMPLETE));
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "INTRO_HQ_WES_OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Requisitions", "Head over to the requisitions area to see what Wes can offer you.", INTRO_HQ_WES_REQUISITIONS);
			}
			return null;
		}
	};

	public static final DialogueNode INTRO_HQ_WES_REQUISITIONS = new DialogueNode("Meeting Wes", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, false);
			Main.game.getNpc(Wes.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_quest_special_pass"), false));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "INTRO_HQ_WES_REQUISITIONS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return REQUISITIONS_INTERACTION.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode INTRO_HQ_ELLE = new DialogueNode("Meeting Elle", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "INTRO_HQ_ELLE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "Wait for Elle to show up.", INTRO_HQ_ELLE_ARRIVE);
			}
			return null;
		}
	};

	public static final DialogueNode INTRO_HQ_ELLE_ARRIVE = new DialogueNode("Meeting Elle", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Elle.class).addSlave(Main.game.getNpc(Wes.class));
			Main.game.getNpc(Wes.class).equipClothing();
			Main.game.getNpc(Elle.class).equipClothing();
			Main.game.getNpc(Elle.class).dailyUpdate(); // To stock items for sale
			Main.game.getNpc(Elle.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Wes.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);

			Main.game.getNpc(Wes.class).addFetish(Fetish.FETISH_ORAL_RECEIVING);
			Main.game.getNpc(Wes.class).addFetish(Fetish.FETISH_VAGINAL_GIVING);
			Main.game.getNpc(Wes.class).setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.ONE_DISLIKE);
			Main.game.getNpc(Wes.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.ONE_DISLIKE);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "INTRO_HQ_ELLE_ARRIVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Follow", "Follow Elle into the Enforcer HQ.", INTRO_HQ_ELLE_OFFICE);
			}
			return null;
		}
	};

	public static final DialogueNode INTRO_HQ_ELLE_OFFICE = new DialogueNode("Meeting Elle", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Vector2i playerLoc = Main.game.getPlayer().getLocation();

			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_OFFICE_QUARTERMASTER);
			Main.game.getNpc(Elle.class).setLocation(Main.game.getPlayer(), false);
			
			List<Cell> cells = Pathing.aStarPathing(Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCellGrid(), playerLoc, Main.game.getPlayer().getLocation(), true);
			for(Cell c : cells) {
				c.setDiscovered(true);
				c.setTravelledTo(true);
			}
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_WES, Quest.SIDE_UTIL_COMPLETE));
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "INTRO_HQ_ELLE_OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Requisitions", "Head over to the requisitions area to see what Elle can offer you.", INTRO_HQ_ELLE_REQUISITIONS);
			}
			return null;
		}
	};

	public static final DialogueNode INTRO_HQ_ELLE_REQUISITIONS = new DialogueNode("Meeting Elle", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, false);
			Main.game.getNpc(Elle.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_quest_special_pass_elle"), false));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "INTRO_HQ_ELLE_REQUISITIONS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return REQUISITIONS_INTERACTION.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode REQUISITIONS_INTERACTION = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_INTERACTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Elle.class))) {
				return null;
				
			} else {
				if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_WES, Quest.WES_3_ELLE)) {
					if(index==0) {
						return new Response("Leave", "Tell Elle that you've got to be going now and head back out into the corridor.", PlaceType.ENFORCER_HQ_CELLS_CORRIDOR.getDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_INTERACTION_LEAVE"));
								Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_CORRIDOR, false);
							}
						};
						
					} else if(index==1) {
						return new ResponseTrade("Trade", "Buy some gear from Elle.", Main.game.getNpc(Elle.class));
						
					} else if(index==2) {
						if(Main.game.getCurrentDialogueNode()==REQUISITIONS_BACKGROUND) {
							return new Response("Background", "You're already asking Elle about herself!", null);
						}
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestTalked)) {
							return new Response("Background", "You've already spent some time asking Elle about herself...<br/>[style.italicsMinorBad(You can only do this once per day.)]", null);
						}
						return new Response("Background", "Ask Elle about herself.<br/>[style.italicsMinorGood(You can do this once per day.)]", REQUISITIONS_BACKGROUND) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestTalked, true);
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Elle.class).incrementAffection(Main.game.getPlayer(), 10));
							}
						};
						
					} else if(index==3) {
						if(Main.game.getCurrentDialogueNode()==REQUISITIONS_WORK) {
							return new Response("Work", "You're already asking Elle about how her work is going!", null);
						}
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestTalkedAlt)) {
							return new Response("Work", "You've already spent some time asking Elle about how her work is going...<br/>[style.italicsMinorBad(You can only do this once per day.)]", null);
						}
						return new Response("Work", "Ask Elle about how her work is going.<br/>[style.italicsMinorGood(You can do this once per day.)]", REQUISITIONS_WORK) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestTalkedAlt, true);
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Elle.class).incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
					} else if(index==4) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestFlirted)) {
							return new Response("Flirt", "You've already spent some time flirting with Elle today...<br/>[style.italicsMinorBad(You can only do this once per day.)]", null);
						}
						return new Response("Flirt", "Flirt with Elle.<br/>[style.italicsMinorGood(You can do this once per day.)]", REQUISITIONS_FLIRT_ELLE) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestFlirted, true);
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Elle.class).incrementAffection(Main.game.getPlayer(), 10));
							}
						};
					}
					
				} else {
					if(index==0) {
						return new Response("Leave", "Tell Wes that you've got to be going now and head back out into the corridor.", PlaceType.ENFORCER_HQ_CELLS_CORRIDOR.getDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_INTERACTION_LEAVE"));
								Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_CORRIDOR, false);
								if(Main.game.getPlayer().isVisiblyPregnant()) {
									Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Wes.class), true);
								}
							}
						};
						
					} else if(index==1) {
						return new ResponseTrade("Trade", "Buy some gear from Wes.", Main.game.getNpc(Wes.class));
						
					} else if(index==2) {
						if(Main.game.getCurrentDialogueNode()==REQUISITIONS_BACKGROUND) {
							return new Response("Background", "You're already asking Wes about himself!", null);
						}
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestTalked)) {
							return new Response("Background", "You've already spent some time asking Wes about himself...<br/>[style.italicsMinorBad(You can only do this once per day.)]", null);
						}
						return new Response("Background", "Ask Wes about himself.<br/>[style.italicsMinorGood(You can do this once per day.)]", REQUISITIONS_BACKGROUND) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestTalked, true);
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Wes.class).incrementAffection(Main.game.getPlayer(), 10));
								if(Main.game.getPlayer().isVisiblyPregnant()) {
									Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Wes.class), true);
								}
							}
						};
						
					} else if(index==3) {
						if(Main.game.getCurrentDialogueNode()==REQUISITIONS_WORK) {
							return new Response("Work", "You're already asking Wes about how his work is going!", null);
						}
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestTalkedAlt)) {
							return new Response("Work", "You've already spent some time asking Wes about how his work is going...<br/>[style.italicsMinorBad(You can only do this once per day.)]", null);
						}
						return new Response("Work", "Ask Wes about how his work is going.<br/>[style.italicsMinorGood(You can do this once per day.)]", REQUISITIONS_WORK) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestTalkedAlt, true);
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Wes.class).incrementAffection(Main.game.getPlayer(), 5));
								if(Main.game.getPlayer().isVisiblyPregnant()) {
									Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Wes.class), true);
								}
							}
						};
						
					} else if(index==4) {
						if(!Main.game.getNpc(Wes.class).isAttractedTo(Main.game.getPlayer())) {
							return new Response("Flirt", "As Wes is not attracted to you, you can tell that trying to flirt with him wouldn't end well...", null);
						}
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestFlirted)) {
							return new Response("Flirt", "You've already spent some time flirting with Wes today...<br/>[style.italicsMinorBad(You can only do this once per day.)]", null);
						}
						return new Response("Flirt", "Flirt with Wes.<br/>[style.italicsMinorGood(You can do this once per day.)]", REQUISITIONS_FLIRT_WES) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestFlirted, true);
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Wes.class).incrementAffection(Main.game.getPlayer(), 5));
								if(Main.game.getPlayer().isVisiblyPregnant()) {
									Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Wes.class), true);
								}
							}
						};
						
					} else if(index==5) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestSex)) {
							return new Response("Elle", "You've already spent some time with Elle today...<br/>[style.italicsMinorBad(You can only do this once per day.)]", null);
						}
						return new Response("Elle", "Ask Wes if you can spend some time with Elle.<br/>[style.italicsMinorGood(You can do this once per day.)]", WES_ELLE_OFFICE) {
							@Override
							public boolean isSexHighlight() {
								return true;
							}
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestSex, true);
								Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_OFFICE_QUARTERMASTER);
								Main.game.getNpc(Elle.class).setLocation(Main.game.getPlayer(), false);
								if(Main.game.getPlayer().isVisiblyPregnant()) {
									Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Wes.class), true);
								}
							}
						};
					}
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode REQUISITIONS_BACKGROUND = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			String dialogueId;
			if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_WES, Quest.WES_3_ELLE)) {
				dialogueId = "REQUISITIONS_BACKGROUND_ELLE_";
				if(Main.game.getDialogueFlags().hasSavedLong("elle_background_progress")) {
					Main.game.getDialogueFlags().incrementSavedLong("elle_background_progress", 1);
				} else {
					Main.game.getDialogueFlags().setSavedLong("elle_background_progress", 1);
				}
				
				int progress = (int) Main.game.getDialogueFlags().getSavedLong("elle_background_progress");
				if(progress>5) { // If completed, random background:
					if(backgroundTalkIds.isEmpty()) {
						resetBackgroundIds();
					}
					String idAddition = Util.randomItemFrom(backgroundTalkIds);
					dialogueId = dialogueId + idAddition;
					backgroundTalkIds.remove(idAddition);
					
				} else {
					dialogueId += progress;
				}
				
			} else {
				dialogueId = "REQUISITIONS_BACKGROUND_WES_";
				if(Main.game.getDialogueFlags().hasSavedLong("wes_background_progress")) {
					Main.game.getDialogueFlags().incrementSavedLong("wes_background_progress", 1);
				} else {
					Main.game.getDialogueFlags().setSavedLong("wes_background_progress", 1);
				}
				int progress = (int) Main.game.getDialogueFlags().getSavedLong("wes_background_progress");
				if(progress==4) {
					Main.game.getNpc(Wes.class).addFetish(Fetish.FETISH_ORAL_RECEIVING);
					Main.game.getNpc(Wes.class).addFetish(Fetish.FETISH_VAGINAL_GIVING);
					Main.game.getNpc(Wes.class).setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.ONE_DISLIKE);
					Main.game.getNpc(Wes.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.ONE_DISLIKE);
				}
				if(progress>5) { // If completed, random background:
					if(backgroundTalkIds.isEmpty()) {
						resetBackgroundIds();
					}
					String idAddition = Util.randomItemFrom(backgroundTalkIds);
					dialogueId = dialogueId + idAddition;
					backgroundTalkIds.remove(idAddition);
					
				} else {
					dialogueId += progress;
				}
			}
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/wes", dialogueId));
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return REQUISITIONS_INTERACTION.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode REQUISITIONS_WORK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			String dialogueId;
			if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_WES, Quest.WES_3_ELLE)) {
				dialogueId = "REQUISITIONS_WORK_ELLE_";
			} else {
				dialogueId = "REQUISITIONS_WORK_WES_";
			}
			if(workTalkIds.isEmpty()) {
				resetWorkTalkIds();
			}
			String idAddition = Util.randomItemFrom(workTalkIds);
			dialogueId = dialogueId + idAddition;
			workTalkIds.remove(idAddition);

			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_WORK"));
			sb.append(UtilText.parseFromXMLFile("characters/dominion/wes", dialogueId));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return REQUISITIONS_INTERACTION.getResponse(responseTab, index);
		}
	};
	
	
	// Elle interactions
	
	
	public static final DialogueNode REQUISITIONS_FLIRT_ELLE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_ELLE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Office tour", "Tell Elle that you'd love to take a 'tour' around her office...", REQUISITIONS_FLIRT_ELLE_OFFICE) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_OFFICE_QUARTERMASTER);
						Main.game.getNpc(Elle.class).setLocation(Main.game.getPlayer(), false);
					}
				};
				
			} if(index==2) {
				return new Response("Decline", "Tell Elle that you don't have time to take a tour around her office...", REQUISITIONS_FLIRT_ELLE_DECLINED) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Elle.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode REQUISITIONS_FLIRT_ELLE_DECLINED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_ELLE_DECLINED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return REQUISITIONS_INTERACTION.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode REQUISITIONS_FLIRT_ELLE_OFFICE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_ELLE_OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Take charge", "Dominantly have sex with Elle.",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Elle.class)),
								null,
								null) {
							@Override
							public boolean isSelfTransformDisabled(GameCharacter character) {
								return character.equals(Main.game.getNpc(Elle.class));
							}
						},
						ELLE_END_SEX,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_ELLE_OFFICE_SEX_START_AS_DOM"));
				
			} else if(index==2) {
				return new ResponseSex("Submit", "Let Elle take charge and be dominantly fucked by her.",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Elle.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null) {
							@Override
							public boolean isSelfTransformDisabled(GameCharacter character) {
								return character.equals(Main.game.getNpc(Elle.class));
							}
						},
						ELLE_END_SEX,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_ELLE_OFFICE_SEX_START_AS_SUB"));
			}
			return null;
		}
	};

	public static final DialogueNode ELLE_END_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Elle.class).applyWash(false, true, null, 0);
			Main.game.getNpc(Elle.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "ELLE_END_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Step out into the corridor and continue on your way...", EnforcerHQDialogue.CORRIDOR_PLAIN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	
	
	// Wes interactions:
	
	

	public static final DialogueNode REQUISITIONS_FLIRT_WES = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Office tour", "Suggest to Wes that he give you a 'tour' around his office...", REQUISITIONS_FLIRT_WES_OFFICE) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_OFFICE_QUARTERMASTER);
						Main.game.getNpc(Wes.class).setLocation(Main.game.getPlayer(), false);
					}
				};
				
			} else if(index==2) {
				int progress = (int) Main.game.getDialogueFlags().getSavedLong("wes_background_progress");
				if(progress<5) {
					return new Response("Group tour",
							"You need to get to know Wes better before he'd feel comfortable bringing Elle along for a 'tour' around his office...",
							null);
				}
				return new Response("Group tour", "Suggest to Wes that he bring Elle along for a 'tour' around his office...", REQUISITIONS_FLIRT_WES_OFFICE_THREESOME) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_OFFICE_QUARTERMASTER);
						Main.game.getNpc(Wes.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Elle.class).setLocation(Main.game.getPlayer(), false);
					}
				};
				
			} else if(index==3) {
				return new Response("Finish", "Having had your fun with Wes, decide not to take things any further with him...", REQUISITIONS_FLIRT_WES_FINISH);
				
			}
			return null;
		}
	};

	public static final DialogueNode REQUISITIONS_FLIRT_WES_FINISH = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_FINISH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return REQUISITIONS_INTERACTION.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode REQUISITIONS_FLIRT_WES_OFFICE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Take charge", "Dominantly have sex with Wes.",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Wes.class)),
								null,
								null),
						WES_END_SEX,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE_SEX_START_AS_DOM"));
				
			} else if(index==2) {
				return new ResponseSex("Submit", "Let Wes take charge and be dominantly fucked by him.",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Wes.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
						WES_END_SEX,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE_SEX_START_AS_SUB"));
			}
			return null;
		}
	};

	public static final DialogueNode WES_END_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Wes.class).applyWash(false, true, null, 0);
			Main.game.getNpc(Wes.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_END_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Step out into the corridor and continue on your way...", EnforcerHQDialogue.CORRIDOR_PLAIN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode REQUISITIONS_FLIRT_WES_OFFICE_THREESOME = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE_THREESOME");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Take charge", "Dominantly have sex with Wes and Elle.",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getPlayer()),
								Util.newArrayListOfValues(
										Main.game.getNpc(Wes.class),
										Main.game.getNpc(Elle.class)),
								null,
								null) {
							@Override
							public boolean isSelfTransformDisabled(GameCharacter character) {
								return character.equals(Main.game.getNpc(Elle.class));
							}
						},
						WES_END_SEX_THREESOME,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE_THREESOME_AS_DOM"));
				
			} if(index==2) {
				return new ResponseSex("Dominate Elle", "Join Wes in dominantly fucking Elle.",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getPlayer(),
										Main.game.getNpc(Wes.class)),
								Util.newArrayListOfValues(
										Main.game.getNpc(Elle.class)),
								null,
								null) {
							@Override
							public boolean isSelfTransformDisabled(GameCharacter character) {
								return character.equals(Main.game.getNpc(Elle.class));
							}
						},
						WES_END_SEX_THREESOME,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE_THREESOME_AS_JOINT_DOM"));
				
			} else if(index==3) {
				return new ResponseSex("Join Elle", "Let Wes take charge and join Elle in being dominantly fucked by him.",
						true, true,
						new SMAllFours(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Wes.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS),
										new Value<>(Main.game.getNpc(Elle.class), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public boolean isSelfTransformDisabled(GameCharacter character) {
								return character.equals(Main.game.getNpc(Elle.class));
							}
						},
						null,
						null,
						WES_END_SEX_THREESOME,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE_THREESOME_AS_JOINT_SUB"));
				
			} else if(index==4) {
				return new ResponseSex("Submit", "Let Elle and Wes take charge and be dominantly fucked by them.",
						true, true,
						Main.game.getPlayer().isTaur()
							?new SMAllFours(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Wes.class), SexSlotAllFours.BEHIND),
											new Value<>(Main.game.getNpc(Elle.class), SexSlotAllFours.IN_FRONT)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)))
							:new SMLyingDown(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Wes.class), SexSlotLyingDown.MISSIONARY),
											new Value<>(Main.game.getNpc(Elle.class), SexSlotLyingDown.FACE_SITTING_REVERSE)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public boolean isSelfTransformDisabled(GameCharacter character) {
									return character.equals(Main.game.getNpc(Elle.class));
								}
							},
						null,
						null,
						WES_END_SEX_THREESOME,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE_THREESOME_AS_SUB"));
				
			} else if(index==5) {
				return new ResponseSex("Elle's revenge", "Let Elle take charge and join Wes in being dominantly fucked by her.",
						true, true,
						new SMLyingDown(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Elle.class), SexSlotLyingDown.COWGIRL)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN),
										new Value<>(Main.game.getNpc(Wes.class), SexSlotLyingDown.LYING_DOWN_TWO))) {
							@Override
							public boolean isSelfTransformDisabled(GameCharacter character) {
								return character.equals(Main.game.getNpc(Elle.class));
							}
						},
						null,
						null,
						WES_END_SEX_THREESOME,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE_THREESOME_ELLE_REVENGE"));
			}
			return null;
		}
	};

	public static final DialogueNode WES_END_SEX_THREESOME = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Wes.class).applyWash(false, true, null, 0);
			Main.game.getNpc(Wes.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
			Main.game.getNpc(Elle.class).applyWash(false, true, null, 0);
			Main.game.getNpc(Elle.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_END_SEX_THREESOME");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Step out into the corridor and continue on your way...", EnforcerHQDialogue.CORRIDOR_PLAIN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode WES_ELLE_OFFICE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_ELLE_OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Take charge", "Dominantly have sex with Elle.",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Elle.class)),
								null,
								null) {
							@Override
							public boolean isSelfTransformDisabled(GameCharacter character) {
								return character.equals(Main.game.getNpc(Elle.class));
							}
						},
						WES_END_SEX_ELLE,
						UtilText.parseFromXMLFile("characters/dominion/wes", "WES_ELLE_OFFICE_START_AS_DOM"));
				
			} else if(index==2) {
				return new ResponseSex("Submit", "Let Elle take charge and be dominantly fucked by her.",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Elle.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null) {
							@Override
							public boolean isSelfTransformDisabled(GameCharacter character) {
								return character.equals(Main.game.getNpc(Elle.class));
							}
						},
						WES_END_SEX_ELLE,
						UtilText.parseFromXMLFile("characters/dominion/wes", "WES_ELLE_OFFICE_START_AS_SUB"));
			}
			return null;
		}
	};

	public static final DialogueNode WES_END_SEX_ELLE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Elle.class).applyWash(false, true, null, 0);
			Main.game.getNpc(Elle.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_END_SEX_ELLE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Step out into the corridor and continue on your way...", EnforcerHQDialogue.CORRIDOR_PLAIN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
}
