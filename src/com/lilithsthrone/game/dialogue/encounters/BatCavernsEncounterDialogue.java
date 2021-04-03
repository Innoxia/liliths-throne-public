package com.lilithsthrone.game.dialogue.encounters;

import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.4
 * @version 0.3.21
 * @author Innoxia, DSG
 */
public class BatCavernsEncounterDialogue {

	public static final DialogueNode FIND_ITEM = new DialogueNode("Discarded Item", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR) || Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.BAT_CAVERN_LIGHT)) {
				if(((AbstractItem) Encounter.getRandomItem()).getItemType()==ItemType.MUSHROOM) {
					return UtilText.parseFromXMLFile("places/submission/batCaverns", "FIND_MUSHROOMS_LIGHT")
							+ "<p style='text-align:center;'>"
								+ "<b>"+ Encounter.getRandomItem().getDisplayName(true)+ "</b>"
							+ "</p>";
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/batCaverns", "FIND_ITEM_LIGHT")
							+ "<p style='text-align:center;'>"
								+ "<b>"+ Encounter.getRandomItem().getDisplayName(true)+ "</b>"
							+ "</p>";
				}
				
			} else {
				if(((AbstractItem) Encounter.getRandomItem()).getItemType()==ItemType.MUSHROOM) {
					return UtilText.parseFromXMLFile("places/submission/batCaverns", "FIND_MUSHROOMS_DARK")
							+ "<p style='text-align:center;'>"
								+ "<b>"+ Encounter.getRandomItem().getDisplayName(true)+ "</b>"
							+ "</p>";
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/batCaverns", "FIND_ITEM_DARK")
							+ "<p style='text-align:center;'>"
								+ "<b>"+ Encounter.getRandomItem().getDisplayName(true)+ "</b>"
							+ "</p>";
				}
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Take", "Add the " + Encounter.getRandomItem().getName() + " to your inventory.", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem((AbstractItem) Encounter.getRandomItem(), true, true));
					}
				};
				
			} else if (index == 2) {
				return new Response("Leave", "Leave the " + Encounter.getRandomItem().getName() + " on the floor.", Main.game.getDefaultDialogue(false));
				
			} else {
				return null;
			}
		}
	};
		
	public static final DialogueNode REBEL_BASE_DISCOVERED = new DialogueNode("Strange Handle", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE);
			Main.game.getPlayerCell().getPlace().setName(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE.getName());
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_HANDLE_REFUSED));
		}
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DISCOVERED");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Pull the handle", "What could possibly go wrong?", REBEL_BASE_DOOR_NO_PASS);

			} else if (index == 2) {
				return new Response("Leave it alone", "Nothing good ever came of pulling strange handles in caves.", PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE.getDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode REBEL_BASE_DOOR_NO_PASS = new DialogueNode("Strange Handle", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_ONE));
		}
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_NO_PASS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Pull harder", "The handle won't budge. Looks like you really do need the password.", null);
					
			} else if (index == 2) {
				return new Response("Leave it alone", "Go look for the password instead.", PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE.getDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode REBEL_BASE_PASSWORD_ONE = new DialogueNode("Journal Fragment", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_TWO));
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.BAT_CAVERN_DARK) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rebelBaseDarkPassFound, true);
			} else {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rebelBaseLightPassFound, true);
			}
		}
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.BAT_CAVERN_LIGHT) {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_LIGHT"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_DARK"));
			}
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_ONE"));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "This is only one part of the password, you need to find the other", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode REBEL_BASE_PASSWORD_TWO = new DialogueNode("Another Journal Fragment", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_COMPLETE));
		}
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.BAT_CAVERN_LIGHT) {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_LIGHT"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_DARK"));
			}
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_TWO"));
			
			return sb.toString();	   
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "You've found both parts of the password, you can head back to the mysterious handle when you're ready.", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
}
