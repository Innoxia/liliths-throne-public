package com.lilithsthrone.game.dialogue.places.dominion.nyansApartment;

import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.NyanMum;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ClothingEmporium;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class NyanRepeatDate {

	private static int dateBill;
	
	private static float playerAlcoholIncrement = 0;
	
	private static Nyan getNyan() {
		return ((Nyan)Main.game.getNpc(Nyan.class));
	}

	private static NyanMum getNyanMum() {
		return ((NyanMum)Main.game.getNpc(NyanMum.class));
	}

	private static void travelTo(AbstractWorldType worldType, AbstractPlaceType placeType) {
		Main.game.getPlayer().setLocation(worldType, placeType);
		getNyan().setLocation(Main.game.getPlayer(), false);
	}

	public static final DialogueNode DATE_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			dateBill = 0;
			getNyan().wearDress();
			((Nyan)getNyan()).wearCoat(true, true);
			getNyanMum().setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true); // So Nyanmum isn't visible
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanWeekendDated, true);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Restaurant", "Accompany Nyan to the restaurant.", DATE_RESTAURANT_1_ARRIVED);
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_1_ARRIVED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			((Nyan)getNyan()).wearCoat(false, false);
			travelTo(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RESTAURANT);
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_SPARE_BEDROOM, true); // Return Nyanmum to bedroom
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_1_ARRIVED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				int cost = 350;
				return new Response("Vulpine's Vineyard ("+UtilText.formatAsMoney(cost, "span")+")", "Order the cheapest wine on the menu, which is nevertheless still a very good vintage.", DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("Vulpine's Vineyard", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_2_VULPINE"));
					}
				};
				
			} else if(index==2) {
				int cost = 600;
				return new Response("Alicorn's Finest ("+UtilText.formatAsMoney(cost, "span")+")", "Order one of the more expensive wines on the menu.", DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("Alicorn's Finest", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_2_ALICORN"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 1, 60, 100));
					}
				};
				
			} else if(index==3) {
				int cost = 950;
				return new Response("Youko's Reserve ("+UtilText.formatAsMoney(cost, "span")+")", "Order the most expensive wine on the menu.", DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("Youko's Reserve", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_2_YOUKO"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 2, 60, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_2_WINE_ORDER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_2_WINE_ORDER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Share bottle", "Say that you'll share the bottle with Nyan.", DATE_RESTAURANT_3_MAIN_DATE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_3_SHARE"));
						playerAlcoholIncrement = 0.1f;
					}
				};
				
			} else if(index==2) {
				return new Response("Water", "Order a bottle of water for yourself.", DATE_RESTAURANT_3_MAIN_DATE) {
					@Override
					public void effects() {
						dateBill+=25;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_3_WATER"));
						playerAlcoholIncrement = 0f;
					}
				};
				
			} else if(index==3) {
				return new Response("Beer", "Order a beer for yourself.", DATE_RESTAURANT_3_MAIN_DATE) {
					@Override
					public void effects() {
						dateBill+=50;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_3_BEER"));
						playerAlcoholIncrement = 0.05f;
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_3_MAIN_DATE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			dateBill+=1200;
			getNyan().incrementAlcoholLevel(0.1f*2);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement*2);
		}
		@Override
		public int getSecondsPassed() {
			return 100*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(dateBill), true);
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_3_MAIN_DATE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave ("+UtilText.formatAsMoney(dateBill, "span")+")", "Pay the bill and accompany Nyan back to her apartment building.", DATE_END_RETURN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-dateBill));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_END_RETURN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			((Nyan)getNyan()).wearCoat(false, true);
			AbstractClothing shoes = getNyan().getClothingInSlot(InventorySlot.FOOT);
			if(shoes!=null) {
				getNyan().unequipClothingIntoVoid(shoes, true, getNyan());
			}
			Main.game.getPlayer().applyFoodConsumed(10);
			Main.game.getPlayer().applyDrinkConsumed(10);
			getNyan().applyFoodConsumed(10);
			getNyan().applyDrinkConsumed(10);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_END_RETURN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("[nyanmum.Name]", "It's time to meet [nyanmum.name] again...", POST_DATE_APARTMENT_INTERVIEW_2_FOOD);
			}
			return null;
		}
	};
	

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_2_FOOD = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_2_FOOD");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_2_FOOD_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -1, 60, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Salad", "Tell [nyanmum.name] that Nyan's favourite food is salad.", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() { failEffects("salad"); }
				};
			} else if(index==2) {
				return new Response("Tsipoura", "Tell [nyanmum.name] that Nyan's favourite food is the fish dish, 'tsipoura'.", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_2_FOOD_SUCCESS"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 1, -20, 20));
					}
				};
			} else if(index==3) {
				return new Response("Pizza", "Tell [nyanmum.name] that Nyan's favourite food is pizza.", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() { failEffects("pizza"); }
				};
			} else if(index==4) {
				return new Response("Catnip", "Tell [nyanmum.name] that Nyan's favourite food is the plant 'nepeta cataria', known by the common name of catnip.", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() { failEffects("catnip"); }
				};
			} else if(index==5) {
				return new Response("Ice cream", "Tell [nyanmum.name] that Nyan's favourite food is ice cream.", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() { failEffects("ice cream"); }
				};
			}
			return null;
		}
	};

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_3_HISTORY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_3_HISTORY");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_3_HISTORY_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -1, 60, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Organise day", "Tell [nyanmum.name] that Nyan used to organise her day for her every morning.", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() { failEffects("organise her day for her"); }
				};
			} else if(index==2) {
				return new Response("Kiss her", "Tell [nyanmum.name] that Nyan used to kiss her every morning.", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() { failEffects("kiss her"); }
				};
			} else if(index==3) {
				return new Response("Breakfast in bed", "Tell [nyanmum.name] that Nyan used to make breakfast in bed for her every morning.", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() { failEffects("make breakfast in bed for her"); }
				};
			} else if(index==4) {
				return new Response("Bake pastries", "Tell [nyanmum.name] that Nyan used to bake pastries for her every morning.", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_3_HISTORY_SUCCESS"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 2, -20, 20));
					}
				};
			} else if(index==5) {
				return new Response("Play piano", "Tell [nyanmum.name] that Nyan used to play the piano for her every morning.", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() { failEffects("play the piano for her"); }
				};
			}
			return null;
		}
	};

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_4_STORE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_4_STORE");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_4_STORE_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -1, 60, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Space", "Tell [nyanmum.name] that Nyan's store used to be called 'Space'.", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() { failEffects("Space"); }
				};
			} else if(index==2) {
				return new Response("Dapper Duds", "Tell [nyanmum.name] that Nyan's store used to be called 'Dapper Duds'.", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() { failEffects("Dapper Duds"); }
				};
			} else if(index==3) {
				return new Response("The Dress Shop", "Tell [nyanmum.name] that Nyan's store used to be called 'The Dress Shop'.", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() { failEffects("The Dress Shop"); }
				};
			} else if(index==4) {
				return new Response("[nyanmum.NamePos] Finest", "Tell [nyanmum.name] that Nyan's store used to be called '[nyanmum.NamePos] Finest'.", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() { failEffects("[nyanmum.NamePos] Finest"); }
				};
			} else if(index==5) {
				return new Response("Golden Threads", "Tell [nyanmum.name] that Nyan's store used to be called 'Golden Threads'.", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_4_STORE_SUCCESS"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 3, -20, 20));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_5_FLOWER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_5_FLOWER");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_5_FLOWER_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -1, 60, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Daisies", "Tell [nyanmum.name] that Nyan's favourite flowers are daisies.", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_5_FLOWER_SUCCESS"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 4, -20, 20));
					}
				};
			} else if(index==2) {
				return new Response("Tulips", "Tell [nyanmum.name] that Nyan's favourite flowers are tulips.", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() { failEffects("tulips"); }
				};
			} else if(index==3) {
				return new Response("Roses", "Tell [nyanmum.name] that Nyan's favourite flowers are roses.", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() { failEffects("roses"); }
				};
			} else if(index==4) {
				return new Response("Fuchsias", "Tell [nyanmum.name] that Nyan's favourite flowers are fuchsias.", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() { failEffects("fuchsias"); }
				};
			} else if(index==5) {
				return new Response("Hydrangeas", "Tell [nyanmum.name] that Nyan's favourite flowers are hydrangeas.", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() { failEffects("hydrangeas"); }
				};
			}
			return null;
		}
	};

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_6_BOOK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setKnowsCharacterArea(CoverableArea.VAGINA, getNyanMum(), true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_6_BOOK");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_6_BOOK_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -1, 60, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Nightmare Chapel", "Tell [nyanmum.name] that Nyan's favourite book is called 'Nightmare Chapel'.", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() { failEffects("Nightmare Chapel"); }
				};
			} else if(index==2) {
				return new Response("The Peacock's Progress", "Tell [nyanmum.name] that Nyan's favourite book is called 'The Peacock's Progress'.", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() { failEffects("The Peacock's Progress"); }
				};
			} else if(index==3) {
				return new Response("One Day in June", "Tell [nyanmum.name] that Nyan's favourite book is called 'One Day in June'.", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_6_BOOK_SUCCESS"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 5, -20, 20));
					}
				};
			} else if(index==4) {
				return new Response("The Sign of Eight", "Tell [nyanmum.name] that Nyan's favourite book is called 'The Sign of Eight'.", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() { failEffects("The Sign of Eight"); }
				};
			} else if(index==5) {
				return new Response("Lord of the Hats", "Tell [nyanmum.name] that Nyan's favourite book is called 'Lord of the Hats'.", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() { failEffects("Lord of the Hats"); }
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_PASS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumInterviewPassed, true);
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_SPARE_BEDROOM);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_PASS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) { 
				return new Response("Hug", "Nyan looks like she could really use a reassuring hug right about now.", NyanFirstDate.POST_DATE_APARTMENT_BEDROOM);
			}
			return null;
		}
	};
	
}
