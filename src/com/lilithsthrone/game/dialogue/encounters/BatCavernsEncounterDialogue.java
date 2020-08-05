package com.lilithsthrone.game.dialogue.encounters;

import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.4
 * @version 0.3.7.3
 * @author Innoxia
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
                            return new Response("Pull the handle", "What could possibly go wrong?", REBEL_BASE_DOOR_OPENED){
                            };

                    } else if (index == 2) {
                            return new Response("Leave it", "Nothing good ever came of pulling strange handles in caves.", Main.game.getDefaultDialogue(false));
                    } else {
                            return null;
                    }
            }

                @Override
                public String getAuthor() {
		return "DSG";
                }
            
        };
        
        public static final DialogueNode REBEL_BASE_DOOR_OPENED = new DialogueNode("Hidden Doorway", "", true) {
            
            @Override
            public int getSecondsPassed() {
                return 30;
            }
                
            @Override
            public String getContent() {
                return UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_OPENED");
            }
            
            @Override
            public Response getResponse(int responseTab, int index) {
                    if (index == 1) {
                            return new ResponseEffectsOnly("Continue", "This cave is not a natural formation. Someone built it, so it must lead somewhere."){
                                @Override
                                public void effects() {
                                        Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_EXPLORATION));
                                        Main.mainController.moveGameWorld(WorldType.REBEL_BASE, PlaceType.REBEL_BASE_ENTRANCE, true);
                                }
                            };
                    } else {
                            return null;
                    }
            }

                @Override
                public String getAuthor() {
		return "DSG";
                }
            
        };
}
