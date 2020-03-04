package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.CityPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
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
	
	public static final DialogueNode ANTIQUES = new DialogueNode("Antiques Shop", "-", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index==1) {
					if(!Main.game.isExtendedWorkTime()) {
						return new Response("Enter", "The antiques shop is currently closed; you'll have to return at another time if you want to take a look inside.", null);
					}
					return new Response("Enter", "Step inside the antiques shop and take a look around.", ANTIQUES_INTERIOR);
				}
			}
			return getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR = new DialogueNode("Antiques Shop", "-", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR"));
			
			if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_END"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
				if(index==1) {
					return new Response("Scarlett", "Head over to the store's employee and ask them about Scarlett.", ANTIQUES_INTERIOR_SCARLETT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_START"));
							if(Main.game.getNpc(Scarlett.class).getRace()!=Race.HARPY || Main.game.getNpc(Scarlett.class).getGender()!=Gender.F_P_TRAP) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_TRANSFORMED"));
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT"));
							}
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_END"));
							
							Main.game.getNpc(Scarlett.class).setBody(Gender.M_P_MALE, RacialBody.HARPY, RaceStage.LESSER, false);
							Main.game.getNpc(Scarlett.class).setStartingBody(true);
							Main.game.getNpc(Scarlett.class).equipClothing();
							Main.game.getNpc(Scarlett.class).endPregnancy(true);
							Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), true);
							Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_bdsm_metal_collar")), true, Main.game.getNpc(Scarlett.class));
						}
					};
				}
			}
			if(index==0) {
				return new Response("Exit", "Head back out into the Shopping Arcade.", ANTIQUES);
			}
			return null;
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR_SCARLETT = new DialogueNode("", "", true) {
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
				return new Response("Explain", "Explain that while you're not here from the business which the squirrel-boy is talking about, you <i>are</i> here to take Scarlett off his hands.", ANTIQUES_INTERIOR_SCARLETT_EXPLAIN);
			}
			return null;
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR_SCARLETT_EXPLAIN = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_EXPLAIN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<10_000) {
					return new Response("Pay ("+UtilText.formatAsMoney(10_000, "span")+")", "You cannot afford to pay the squirrel-boy the ten thousand flames he's asking for!", null);
					
				} else {
					return new Response("Pay ("+UtilText.formatAsMoney(10_000, "span")+")", "Pay the squirrel-boy the ten thousand flames he's asking for.", ANTIQUES_INTERIOR_SCARLETT_PURCHASED) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-10_000));
							Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
							Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.NECK).setSealed(false);
							Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.NECK), true, Main.game.getNpc(Scarlett.class));
						}
					};
				}
				
			} else if(index==2) {
				return new Response("Haggle", "Haggle with the squirrel-boy in an attempt to drive down the price which he's asking for Scarlett.", ANTIQUES_INTERIOR_SCARLETT_HAGGLE) {
					@Override
					public void effects() {
						Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR_SCARLETT_PURCHASED = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_PURCHASED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ANTIQUES.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR_SCARLETT_HAGGLE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_HAGGLE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ANTIQUES.getResponse(responseTab, index);
		}
	};
	
	
}
