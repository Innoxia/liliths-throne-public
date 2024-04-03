package com.lilithsthrone.game.dialogue.places.dominion.nyansApartment;

import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
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
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class NyanFirstDoubleDate {

	private static int dateBill;
	
	private static float playerAlcoholIncrement = 0;
	
	private static boolean kinky = false;
	
	private static Nyan getNyan() {
		return ((Nyan)Main.game.getNpc(Nyan.class));
	}

	private static NyanMum getNyanMum() {
		return ((NyanMum)Main.game.getNpc(NyanMum.class));
	}
	
	private static void travelTo(AbstractWorldType worldType, AbstractPlaceType placeType) {
		travelTo(worldType, placeType, true);
	}
	
	private static void travelTo(AbstractWorldType worldType, AbstractPlaceType placeType, boolean includeNyanMum) {
		Main.game.getPlayer().setLocation(worldType, placeType);
		getNyan().setLocation(Main.game.getPlayer(), false);
		if(includeNyanMum) {
			getNyanMum().setLocation(Main.game.getPlayer(), false);
		}
	}
	
	public static final DialogueNode DATE_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			dateBill = 0;
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_NYAN_BEDROOM);
			getNyanMum().setLocation(getNyan(), false);
			getNyanMum().equipClothing();
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanWeekendDated, true);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumDateCompleted, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "Wait in the lounge while Nyan and [nyanmum.name] get ready.", DATE_START_WAIT);
			}
			return null;
		}
	};

	public static final DialogueNode DATE_START_WAIT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().wearDress();
			getNyan().wearCoat(true, true);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_START_WAIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Restaurant", "Accompany Nyan and [nyanmum.name] to the restaurant.", DATE_RESTAURANT_1_ARRIVED);
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_1_ARRIVED = new DialogueNode("", "", true) {
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_RESTAURANT_1_ARRIVED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Share bottle", "Agree to share the bottle with Nyan and [nyanmum.name].", DATE_RESTAURANT_2_PLAYER_DRINK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_RESTAURANT_2_SHARE"));
						playerAlcoholIncrement = 0.1f;
					}
				};
				
			} else if(index==2) {
				return new Response("Water", "Order a bottle of water for yourself.", DATE_RESTAURANT_2_PLAYER_DRINK) {
					@Override
					public void effects() {
						dateBill+=25;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_RESTAURANT_2_WATER"));
						playerAlcoholIncrement = 0f;
					}
				};
				
			} else if(index==3) {
				return new Response("Beer", "Order a beer for yourself.", DATE_RESTAURANT_2_PLAYER_DRINK) {
					@Override
					public void effects() {
						dateBill+=50;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_RESTAURANT_2_BEER"));
						playerAlcoholIncrement = 0.05f;
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_2_PLAYER_DRINK = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_RESTAURANT_2_PLAYER_DRINK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Order", "Place your orders and wait for the starters to arrive.", DATE_RESTAURANT_3_STARTERS) {
					@Override
					public void effects() {
						dateBill+=1100;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_RESTAURANT_3_ORDER"));
					}
				};
				
			} else if(index==2) {
				return new Response("Order (candle)", "Place your orders and wait for the starters to arrive. Also ask if you could have a romantic candle lit on your table.", DATE_RESTAURANT_3_STARTERS) {
					@Override
					public void effects() {
						dateBill+=1100;
						dateBill+=10;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_RESTAURANT_3_ORDER_CANDLE"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 1, 60, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_3_STARTERS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().incrementAlcoholLevel(0.1f);
			getNyanMum().incrementAlcoholLevel(0.1f);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_RESTAURANT_3_STARTERS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Respond", "Tell [nyanmum.name] all about yourself.", DATE_RESTAURANT_4_MAIN_MEAL);
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_4_MAIN_MEAL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().incrementAlcoholLevel(0.1f);
			getNyanMum().incrementAlcoholLevel(0.1f);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement);
			dateBill+=950;
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_RESTAURANT_4_MAIN_MEAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Agree",
						"Agree to be both Nyan's and [nyanmum.namepos] [pc.girlfriend]."
								+ "<br/>[style.italicsMinorGood(This will permanently result in both Nyan and [nyanmum.name] being your girlfriends...)]",
						DATE_5_END) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_MINOR_GOOD;
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(getNyanMum().setAffection(Main.game.getPlayer(), 50));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumGirlfriend, true);
					}
				};
				
			} else if(index==2) {
				return new Response("Refuse",
						"Tell [nyanmum.name] that you're only interested in dating her daughter."
						+ "<br/>[style.italicsMinorBad(This is a permanent choice, and you will not get the opportunity to date [nyanmum.name] again...)]",
						DATE_5_END) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_MINOR_BAD;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumGirlfriend, false);
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_5_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanRestaurantDateCompleted, true);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(dateBill), true);
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_5_END_NYANMUM_GF");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_5_END");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave ("+UtilText.formatAsMoney(dateBill, "span")+")",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)
							?"Pay the bill and accompany Nyan and [nyanmum.name] back to their apartment."
							:"Pay the bill and accompany Nyan back to her apartment building.",
						DATE_END_RETURN) {
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
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, true);
			}
			((Nyan)getNyan()).wearCoat(false, true);
			AbstractClothing shoes = getNyan().getClothingInSlot(InventorySlot.FOOT);
			if(shoes!=null) {
				getNyan().unequipClothingIntoVoid(shoes, true, getNyan());
			}
			Main.game.getPlayer().applyFoodConsumed(10);
			Main.game.getPlayer().applyDrinkConsumed(10);
			getNyan().applyFoodConsumed(10);
			getNyan().applyDrinkConsumed(10);
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().applyFoodConsumed(10);
				getNyanMum().applyDrinkConsumed(10);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_END_RETURN_NYANMUM_GF");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_END_RETURN");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
					if(index==1) {
						return new Response("Lounge", "Do as [nyanmum.name] says and wait in the lounge...", POST_DATE_APARTMENT_LOUNGE) {
							@Override
							public void effects() {
								kinky = false;
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_END_RETURN_LOUNGE"));
							}
						};
						
					} else if(index==2) {
						return new Response("Lounge (kinky)", "Tell [nyanmum.name] that you like the sound of her 'kinky idea', before doing as she says and waiting in the lounge...", POST_DATE_APARTMENT_LOUNGE) {
							@Override
							public void effects() {
								kinky = true;
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "DATE_END_RETURN_LOUNGE_KINKY"));
							}
						};
					}
					
				} else {
					if(index==1) {
						return new Response("Bedroom", "Head into Nyan's bedroom...", POST_DATE_APARTMENT_BEDROOM) {
							@Override
							public int getSecondsPassed() {
								return 5*60;
							}
						};
					}
				}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_LOUNGE = new DialogueNode("", "", true, true) {
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "POST_DATE_APARTMENT_LOUNGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Bedroom", "Head into [nyanmum.namePos] bedroom...", POST_DATE_APARTMENT_BEDROOM);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyan().wearLingerie(kinky);
				getNyanMum().wearLingerie(kinky);
				travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_SPARE_BEDROOM);
				getNyan().setFetishDesire(Fetish.FETISH_INCEST, FetishDesire.THREE_LIKE);
				getNyanMum().setFetishDesire(Fetish.FETISH_INCEST, FetishDesire.THREE_LIKE);
				
			} else {
				NyanApartment.setActiveSexPartner(getNyan());
				getNyan().wearLingerie(false);
				travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_NYAN_BEDROOM, false);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				StringBuilder sb = new StringBuilder();
				sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "POST_DATE_APARTMENT_BEDROOM_NYANMUM_GF_START"));
				if(kinky) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "POST_DATE_APARTMENT_BEDROOM_NYANMUM_GF_KINKY"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "POST_DATE_APARTMENT_BEDROOM_NYANMUM_GF"));
				}
				sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "POST_DATE_APARTMENT_BEDROOM_NYANMUM_GF_END"));
				return sb.toString();
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDoubleDate", "POST_DATE_APARTMENT_BEDROOM");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				return NyanApartment.DOUBLE_SEX_FOREPLAY.getResponse(responseTab, index);
				
			} else {
				return NyanApartment.SOLO_SEX_FOREPLAY.getResponse(responseTab, index);
			}
		}
	};

}
