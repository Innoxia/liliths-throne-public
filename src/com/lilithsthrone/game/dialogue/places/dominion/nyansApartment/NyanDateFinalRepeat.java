package com.lilithsthrone.game.dialogue.places.dominion.nyansApartment;

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
public class NyanDateFinalRepeat {

	private static boolean kinky = false;
	
	private static boolean doubleDate = false;
	
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
		if(doubleDate) {
			getNyanMum().setLocation(Main.game.getPlayer(), false);
		}
	}

	public static final DialogueNode SOLO_DATE_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			doubleDate = false;
			dateBill = 0;
			getNyan().wearDress();
			((Nyan)getNyan()).wearCoat(true, true);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanWeekendDated, true);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Restaurant", "Accompany Nyan to the restaurant.", SOLO_DATE_RESTAURANT_1_ARRIVED);
			}
			return null;
		}
	};

	public static final DialogueNode SOLO_DATE_RESTAURANT_1_ARRIVED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			((Nyan)getNyan()).wearCoat(false, false);
			travelTo(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RESTAURANT);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_1_ARRIVED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				int cost = 350;
				return new Response("Vulpine's Vineyard ("+UtilText.formatAsMoney(cost, "span")+")", "Order the cheapest wine on the menu, which is nevertheless still a very good vintage.", SOLO_DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("Vulpine's Vineyard", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_2_VULPINE"));
					}
				};
				
			} else if(index==2) {
				int cost = 600;
				return new Response("Alicorn's Finest ("+UtilText.formatAsMoney(cost, "span")+")", "Order one of the more expensive wines on the menu.", SOLO_DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("Alicorn's Finest", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_2_ALICORN"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 1, 60, 100));
					}
				};
				
			} else if(index==3) {
				int cost = 950;
				return new Response("Youko's Reserve ("+UtilText.formatAsMoney(cost, "span")+")", "Order the most expensive wine on the menu.", SOLO_DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("Youko's Reserve", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_2_YOUKO"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 2, 60, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SOLO_DATE_RESTAURANT_2_WINE_ORDER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_2_WINE_ORDER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Share bottle", "Say that you'll share the bottle with Nyan.", SOLO_DATE_RESTAURANT_3_MAIN_DATE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_3_SHARE"));
						playerAlcoholIncrement = 0.1f;
					}
				};
				
			} else if(index==2) {
				return new Response("Water", "Order a bottle of water for yourself.", SOLO_DATE_RESTAURANT_3_MAIN_DATE) {
					@Override
					public void effects() {
						dateBill+=25;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_3_WATER"));
						playerAlcoholIncrement = 0f;
					}
				};
				
			} else if(index==3) {
				return new Response("Beer", "Order a beer for yourself.", SOLO_DATE_RESTAURANT_3_MAIN_DATE) {
					@Override
					public void effects() {
						dateBill+=50;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_3_BEER"));
						playerAlcoholIncrement = 0.05f;
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SOLO_DATE_RESTAURANT_3_MAIN_DATE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().incrementAlcoholLevel(0.1f*2);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement*2);
			dateBill+=1400;
		}
		@Override
		public int getSecondsPassed() {
			return 100*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(dateBill), true);
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_3_MAIN_DATE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave ("+UtilText.formatAsMoney(dateBill, "span")+")", "Pay the bill and accompany Nyan back to her apartment building.", SOLO_DATE_END_RETURN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-dateBill));
						Main.game.getTextEndStringBuilder().append(getNyan().incrementAffection(Main.game.getPlayer(), 10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SOLO_DATE_END_RETURN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_END_RETURN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Inside", "Accept Nyan's invitation and enter her apartment.", SOLO_DATE_END_RETURN_INSIDE);
			}
			return null;
		}
	};
	
	public static final DialogueNode SOLO_DATE_END_RETURN_INSIDE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			NyanApartment.setActiveSexPartner(getNyan());
			getNyan().wearLingerie(false);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_NYAN_BEDROOM);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_END_RETURN_INSIDE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return NyanApartment.SOLO_SEX_FOREPLAY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOUBLE_DATE_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			doubleDate = true;
			dateBill = 0;
			getNyanMum().equipClothing();
			getNyan().wearDress();
			((Nyan)getNyan()).wearCoat(true, true);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanWeekendDated, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Restaurant", "Accompany Nyan and [nyanmum.name] to the restaurant.", DOUBLE_DATE_RESTAURANT_1_ARRIVED);
			}
			return null;
		}
	};

	public static final DialogueNode DOUBLE_DATE_RESTAURANT_1_ARRIVED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RESTAURANT);
			getNyan().wearCoat(false, false);
			dateBill+=950;
		}
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_RESTAURANT_1_ARRIVED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Share bottle", "Agree to share the bottle with Nyan and [nyanmum.name].", DOUBLE_DATE_RESTAURANT_2_MAIN_DATE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_RESTAURANT_2_SHARE"));
						playerAlcoholIncrement = 0.1f;
					}
				};
				
			} else if(index==2) {
				return new Response("Water", "Order a bottle of water for yourself.", DOUBLE_DATE_RESTAURANT_2_MAIN_DATE) {
					@Override
					public void effects() {
						dateBill+=25;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_RESTAURANT_2_WATER"));
						playerAlcoholIncrement = 0f;
					}
				};
				
			} else if(index==3) {
				return new Response("Beer", "Order a beer for yourself.", DOUBLE_DATE_RESTAURANT_2_MAIN_DATE) {
					@Override
					public void effects() {
						dateBill+=50;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_RESTAURANT_2_BEER"));
						playerAlcoholIncrement = 0.05f;
					}
				};
			}
			return null;
		}
	};
	

	public static final DialogueNode DOUBLE_DATE_RESTAURANT_2_MAIN_DATE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().incrementAlcoholLevel(0.1f*2);
			getNyanMum().incrementAlcoholLevel(0.1f*2);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement*2);
			dateBill+=1200;
		}
		@Override
		public int getSecondsPassed() {
			return 100*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(dateBill), true);
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_RESTAURANT_2_MAIN_DATE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave ("+UtilText.formatAsMoney(dateBill, "span")+")", "Pay the bill and accompany the cat-girls back to their apartment building.", DOUBLE_DATE_END_RETURN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-dateBill));
						Main.game.getTextEndStringBuilder().append(getNyan().incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getTextEndStringBuilder().append(getNyanMum().incrementAffection(Main.game.getPlayer(), 10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DOUBLE_DATE_END_RETURN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
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
			getNyanMum().applyFoodConsumed(10);
			getNyanMum().applyDrinkConsumed(10);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_END_RETURN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lounge", "Do as [nyanmum.name] says and wait in the lounge...", POST_DOUBLE_DATE_APARTMENT_LOUNGE) {
					@Override
					public void effects() {
						kinky = false;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_END_RETURN_LOUNGE"));
					}
				};
				
			} else if(index==2) {
				return new Response("Lounge (kinky)", "Tell [nyanmum.name] that you like the sound of her 'kinky idea', before doing as she says and waiting in the lounge...", POST_DOUBLE_DATE_APARTMENT_LOUNGE) {
					@Override
					public void effects() {
						kinky = true;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_END_RETURN_LOUNGE_KINKY"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DOUBLE_DATE_APARTMENT_LOUNGE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_SPARE_BEDROOM);
			Main.game.getPlayer().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "POST_DOUBLE_DATE_APARTMENT_LOUNGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Bedroom", "Head into [nyanmum.namePos] bedroom...", POST_DOUBLE_DATE_APARTMENT_BEDROOM);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DOUBLE_DATE_APARTMENT_BEDROOM = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().wearLingerie(kinky);
			getNyanMum().wearLingerie(kinky);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_SPARE_BEDROOM);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "POST_DOUBLE_DATE_APARTMENT_BEDROOM_START"));
			if(kinky) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "POST_DOUBLE_DATE_APARTMENT_BEDROOM_KINKY"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "POST_DOUBLE_DATE_APARTMENT_BEDROOM"));
			}
			sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "POST_DOUBLE_DATE_APARTMENT_BEDROOM_END"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return NyanApartment.DOUBLE_SEX_FOREPLAY.getResponse(responseTab, index);
		}
	};

}
