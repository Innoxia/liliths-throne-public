package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.CityPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.5.5
 * @author Innoxia
 */
public class ShoppingArcadeDialogue {
	
	public static String getCoreResponseTab(int index) {
		if(index==0) {
			return "Actions";
		} else if(index==1) {
			return "Fast travel";
		}
		return null;
	}
	
	public static Response getFastTravelResponses(int responseTab, int index) {
		if(responseTab==1) {
			if (index == 1) {
				return new ResponseEffectsOnly("Entrance", "Fast travel to the Shopping Arcade's main entrance."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_ENTRANCE, true);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 2) {
				return new ResponseEffectsOnly("Ralph's Snacks", "Fast travel to Ralph's Snacks."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_RALPHS_SHOP, true);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 3) {
				return new ResponseEffectsOnly("Nyan's Clothing Emporium", "Fast travel to Nyan's Clothing Emporium."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_NYANS_SHOP, true);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 4) {
				return new ResponseEffectsOnly("Arcane Arts", "Fast travel to Arcane Arts."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_VICKYS_SHOP, true);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 5) {
				return new ResponseEffectsOnly("Succubi's Secrets", "Fast travel to Succubi's Secrets."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_KATES_SHOP, true);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 6) {
				return new ResponseEffectsOnly("Pix's Playground", "Fast travel to the gym, 'Pix's Playground'."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_PIXS_GYM, true);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 7) {
				return new ResponseEffectsOnly("Dream Lover", "Fast travel to Dream Lover."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP, true);
						Main.game.setResponseTab(0);
					}
				};
			}
		}
		return null;
	}
	
	public static final DialogueNode OUTSIDE = new DialogueNode("Shopping arcade", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return CityPlaces.TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "OUTSIDE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Enter", "Step through the entrance and enter the shopping arcade."){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ENTRANCE, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENTRY = new DialogueNode("Entrance to the arcade", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ENTRY");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new ResponseEffectsOnly("Exit", "Leave the Shopping Arcade."){
						@Override
						public void effects() {
							Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.DOMINION), PlaceType.DOMINION_SHOPPING_ARCADE, true);
						}
					};
				}
			}
			return getFastTravelResponses(responseTab, index);
		}
		
	};
	
	public static final DialogueNode ARCADE = new DialogueNode("Shopping arcade", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ARCADE");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getFastTravelResponses(responseTab, index);
		}
		
	};
	
	public static final DialogueNode GENERIC_SHOP = new DialogueNode("Shop", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "GENERIC_SHOP");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getFastTravelResponses(responseTab, index);
		}
		
	};
}
