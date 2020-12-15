package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.WesQuest;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Vector2i;
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
				return new Response("Entrance", "Fast travel to the Shopping Arcade's main entrance.", PlaceType.SHOPPING_ARCADE_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ENTRANCE, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 2) {
				return new Response("Ralph's Snacks", "Fast travel to Ralph's Snacks.", PlaceType.SHOPPING_ARCADE_RALPHS_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RALPHS_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 3) {
				return new Response("Nyan's Clothing Emporium", "Fast travel to Nyan's Clothing Emporium.", PlaceType.SHOPPING_ARCADE_NYANS_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_NYANS_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 4) {
				return new Response("Arcane Arts", "Fast travel to Arcane Arts.", PlaceType.SHOPPING_ARCADE_VICKYS_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_VICKYS_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 5) {
				return new Response("Succubi's Secrets", "Fast travel to Succubi's Secrets.", PlaceType.SHOPPING_ARCADE_KATES_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_KATES_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 6) {
				return new Response("Pix's Playground", "Fast travel to the gym, 'Pix's Playground'.", PlaceType.SHOPPING_ARCADE_PIXS_GYM.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_PIXS_GYM, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 7) {
				return new Response("Dream Lover", "Fast travel to Dream Lover.", PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP, false);
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
			return DominionPlaces.TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "OUTSIDE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Enter", "Step through the entrance and enter the shopping arcade.", PlaceType.SHOPPING_ARCADE_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ENTRANCE, false);
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
					return new Response("Exit", "Leave the Shopping Arcade.", PlaceType.DOMINION_SHOPPING_ARCADE.getDialogue(false)){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_SHOPPING_ARCADE, false);
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
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ARCADE"));
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_START
					&& Vector2i.getDistance(Main.game.getPlayer().getLocation(), Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(PlaceType.SHOPPING_ARCADE_ANTIQUES).getLocation())==1) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ARCADE_WES_REMINDER"));
			}
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index==1) {
					if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_START) {
						if(Vector2i.getDistance(Main.game.getPlayer().getLocation(), Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(PlaceType.SHOPPING_ARCADE_ANTIQUES).getLocation())==1) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestMet)) {
								return new Response("Meet Enforcer", "You've already met Wes today, and so will be unable to meet with him again until tomorrow at the earliest...", null);
								
							} else if(Main.game.getHourOfDay()!=13) {
								return new Response("Meet Enforcer", "The mysterious Enforcer told you to meet him between the hours of [units.time(13)] and [units.time(14)], so you'll have to come back then...", null);
								
							} else {
								return new Response("Meet Enforcer", "Loiter around the area and wait for the mysterious Enforcer to contact you...", WesQuest.WES_QUEST_SHOPPING_ARCADE_MEETING);
							}
						}
					}
				}
			}
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
			
			if(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).contains(Main.game.getNpc(Scarlett.class))) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_END"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).contains(Main.game.getNpc(Scarlett.class))) {
				if(index==1) {
					return new Response("Scarlett", "Head over to the store's employee and ask them about Scarlett.", ANTIQUES_INTERIOR_SCARLETT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_START"));
							if(Main.game.getNpc(Scarlett.class).getRace()!=Race.HARPY || (Main.game.getNpc(Scarlett.class).getGender()!=Gender.F_P_TRAP && Main.game.getNpc(Scarlett.class).getGender()!=Gender.F_P_B_SHEMALE)) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_TRANSFORMED"));
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT"));
							}
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_END"));
							
							((Scarlett)Main.game.getNpc(Scarlett.class)).completeBodyReset();
							Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), true);
							Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_bdsm_metal_collar")), true, Main.game.getNpc(Scarlett.class));
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
	
	public static final DialogueNode ANTIQUES_INTERIOR_SCARLETT_EXPLAIN = new DialogueNode("", "", true, true) {
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
					return new Response("Pay ("+UtilText.formatAsMoney(10_000, "span")+")", "Pay the squirrel-boy the ten thousand flames he's asking for.", ANTIQUES_INTERIOR_SCARLETT_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_PURCHASED"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-10_000));
						}
					};
				}
				
			} else if(index==2) {
				return new Response("Haggle", "Haggle with the squirrel-boy in an attempt to drive down the price which he's asking for Scarlett.", ANTIQUES_INTERIOR_SCARLETT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_HAGGLE"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR_SCARLETT_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
			Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.NECK).setSealed(false);
			Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.NECK), true, Main.game.getNpc(Scarlett.class));
			Main.game.getNpc(Scarlett.class).getOwner().removeSlave(Main.game.getNpc(Scarlett.class));
			Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX()+1, Main.game.getPlayer().getLocation().getY()));
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaScarlettToldToReturn, true);
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
				return new Response("Continue",
						"Continue on your way out into the Shopping Arcade.",
						ARCADE);
			}
			return null;
		}
	};
}
