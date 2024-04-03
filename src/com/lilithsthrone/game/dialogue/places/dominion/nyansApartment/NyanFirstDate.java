package com.lilithsthrone.game.dialogue.places.dominion.nyansApartment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.NyanMum;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ClothingEmporium;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.nyan.SMNyanSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class NyanFirstDate {

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
			getNyanMum().setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true); // So Nyanmum isn't visible
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanWeekendDated, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lounge", "Follow Nyan through into her lounge.", DATE_START_LOUNGE);
			}
			return null;
		}
	};

	public static final DialogueNode DATE_START_LOUNGE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_NYAN_BEDROOM);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_START_LOUNGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Nyan", "Nyan makes her return...", DATE_START_LOUNGE_NYAN_DRESS);
			}
			return null;
		}
	};

	public static final DialogueNode DATE_START_LOUNGE_NYAN_DRESS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().wearDress();
			getNyan().wearCoat(true, true);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_START_LOUNGE_NYAN_DRESS");
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
			travelTo(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RESTAURANT);
			getNyan().wearCoat(false, false);
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_SPARE_BEDROOM, true); // Return Nyanmum to bedroom
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_1_ARRIVED");
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
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_2_VULPINE"));
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
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_2_ALICORN"));
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
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_2_YOUKO"));
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_2_WINE_ORDER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Share bottle", "Say that you'll share the bottle with Nyan.", DATE_RESTAURANT_3_PLAYER_DRINK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_3_SHARE"));
						playerAlcoholIncrement = 0.1f;
					}
				};
				
			} else if(index==2) {
				return new Response("Water", "Order a bottle of water for yourself.", DATE_RESTAURANT_3_PLAYER_DRINK) {
					@Override
					public void effects() {
						dateBill+=25;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_3_WATER"));
						playerAlcoholIncrement = 0f;
					}
				};
				
			} else if(index==3) {
				return new Response("Beer", "Order a beer for yourself.", DATE_RESTAURANT_3_PLAYER_DRINK) {
					@Override
					public void effects() {
						dateBill+=50;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_3_BEER"));
						playerAlcoholIncrement = 0.05f;
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_3_PLAYER_DRINK = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_3_PLAYER_DRINK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Order", "Place your orders and wait for the starters to arrive.", DATE_RESTAURANT_4_STARTERS) {
					@Override
					public void effects() {
						dateBill+=700;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_4_ORDER"));
					}
				};
				
			} else if(index==2) {
				return new Response("Order (candle)", "Place your orders and wait for the starters to arrive. Also ask if you could have a romantic candle lit on your table.", DATE_RESTAURANT_4_STARTERS) {
					@Override
					public void effects() {
						dateBill+=700;
						dateBill+=10;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_4_ORDER_CANDLE"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 1, 60, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_4_STARTERS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().incrementAlcoholLevel(0.1f);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_4_STARTERS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Talk", "Make small talk with Nyan about her day went.", DATE_RESTAURANT_5_MAIN_MEAL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_5_TALK"));
					}
				};
				
			} else if(index==2) {
				return new Response("Compliments", "Compliment Nyan on how pretty she looks.", DATE_RESTAURANT_5_MAIN_MEAL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_5_COMPLIMENT"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 2, 60, 100));
					}
				};
				
			} else if(index==3) {
				return new Response("Dirty talk", "Make it clear to Nyan that you want to get into her panties tonight.<br/>[style.italicsMinorBad(You can't imagine that she'll react well to this...)]", DATE_RESTAURANT_5_MAIN_MEAL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_5_DIRTY"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -2, 60, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_5_MAIN_MEAL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().incrementAlcoholLevel(0.1f);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_5_MAIN_MEAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Food talk", "Make small talk with Nyan about the quality of your meal.", DATE_RESTAURANT_6_DESSERT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_6_TALK"));
					}
				};
				
			} else if(index==2) {
				return new Response("Flirt", "Flirt with Nyan and insinuate that you'd like to do more than just have dinner with her this evening.", DATE_RESTAURANT_6_DESSERT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_6_FLIRT"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 2, 60, 100));
					}
				};
				
			} else if(index==3) {
				return new Response("Footsie", "Slide your [pc.foot] up Nyan's leg and tell her that you can't wait to fuck her.<br/>[style.italicsBad(You really can't imagine that she'll react well to this at all!)]", DATE_RESTAURANT_6_DESSERT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_6_FOOTSIE"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -5, 60, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_6_DESSERT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().incrementAlcoholLevel(0.05f);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement/2);
		}
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_6_DESSERT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Order desserts", "Order individual desserts for both you and Nyan.", DATE_7_END) {
					@Override
					public void effects() {
						dateBill+=500;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_7_INDIVIDUAL"));
					}
				};
				
			} else if(index==2) {
				return new Response("Order to share", "Order a single dessert to share with Nyan.", DATE_7_END) {
					@Override
					public void effects() {
						dateBill+=250;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_7_SHARE"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 2, 60, 100));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_7_END = new DialogueNode("", "", true, true) {
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_7_END");
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
			travelTo(WorldType.DOMINION, PlaceType.DOMINION_NYAN_APARTMENT);
			((Nyan)getNyan()).wearCoat(true, false);
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_END_RETURN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Inside", "Accept Nyan's invitation to come into her apartment.", POST_DATE_APARTMENT);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			((Nyan)getNyan()).wearCoat(false, true);
			AbstractClothing shoes = getNyan().getClothingInSlot(InventorySlot.FOOT);
			if(shoes!=null) {
				getNyan().unequipClothingIntoVoid(shoes, true, getNyan());
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lounge", "Enter the lounge...", POST_DATE_APARTMENT_LOUNGE);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_LOUNGE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_LOUNGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Introduction", "Introduce yourself to Nyan's mum.", POST_DATE_APARTMENT_INTERVIEW_1_SUPPORT);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_1_SUPPORT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyanMum().setPlayerKnowsName(true);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumIntroduced, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_1_SUPPORT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<1_000_000) {
					return new Response("Wealth", "You require at least one million flames in order to impress [nyanmum.name] with your wealth!", null);
				}
				return new Response("Wealth", "Tell [nyanmum.name] that you have a considerable amount of wealth, and so supporting Nyan would be easy.", POST_DATE_APARTMENT_INTERVIEW_2_FOOD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_WEALTH"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_LILAYA_REVEAL"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 5, -20, 100));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getSlavesOwned().size()<20) {
					return new Response("Slaves", "You require at least twenty slaves in order to impress [nyanmum.name]!", null);
				}
				return new Response("Slaves",
						"Tell [nyanmum.name] that you own a considerable amount of slaves, and so money will never be a problem for you or Nyan.",
						POST_DATE_APARTMENT_INTERVIEW_2_FOOD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_SLAVES"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_LILAYA_REVEAL"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 5, -20, 100));
					}
				};
				
			} else if(index==3) {
				if(Main.game.getPlayer().getSubspeciesOverrideRace()!=Race.DEMON) {
					return new Response("Demon", "You are not a demon, and so cannot impress [nyanmum.name] in this manner...", null);
				}
				return new Response("[#pc.getLilinMother().getName()]'s [pc.daughter]",
						"Reveal to [nyanmum.name] that you're a direct [pc.daughter] of a lilin, and so you'll never struggle to provide for Nyan.",
						POST_DATE_APARTMENT_INTERVIEW_2_FOOD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_DEMON"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 10, -20, 100));
					}
				};
				
			} else if(index==4) {
				if(!Util.newArrayListOfValues(
						Occupation.CHEF,
						Occupation.MUSICIAN,
						Occupation.ARISTOCRAT
						).contains(Main.game.getPlayer().getOccupation())) {
					return new Response("Occupation", "You do not have a suitable occupation for impressing [nyanmum.name]...", null);
				}
				return new Response("Occupation",
						Main.game.getPlayer().getOccupation()==Occupation.ARISTOCRAT
							?"Tell [nyanmum.name] that you are a member of a highly prestigious aristocratic family, and so your wealth and status will be more than enough to support Nyan."
							:"Tell [nyanmum.name] that you are an accomplished [pc.job], and so you'll always be able to find work in order to support Nyan.",
						POST_DATE_APARTMENT_INTERVIEW_2_FOOD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_OCCUPATION"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_LILAYA_REVEAL"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 1, -20, 100));
					}
				};
				
			} else if(index==5) {
				return new Response("Do your best", "Tell [nyanmum.name] that you'll do your best...", POST_DATE_APARTMENT_INTERVIEW_2_FOOD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_DO_BEST"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_LILAYA_REVEAL"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_2_FOOD = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_FOOD");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_FOOD_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Salad", "Tell [nyanmum.name] that Nyan's favourite food is salad.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("salad"); }
				};
			} else if(index==2) {
				return new Response("Tsipoura", "Tell [nyanmum.name] that Nyan's favourite food is the fish dish, 'tsipoura'.", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 1, -20, 20));
					}
				};
			} else if(index==3) {
				return new Response("Pizza", "Tell [nyanmum.name] that Nyan's favourite food is pizza.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("pizza"); }
				};
			} else if(index==4) {
				return new Response("Catnip", "Tell [nyanmum.name] that Nyan's favourite food is the plant 'nepeta cataria', known by the common name of catnip.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("catnip"); }
				};
			} else if(index==5) {
				return new Response("Ice cream", "Tell [nyanmum.name] that Nyan's favourite food is ice cream.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_3_HISTORY");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_3_HISTORY_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Organise day", "Tell [nyanmum.name] that Nyan used to organise her day for her every morning.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("organise her day for her"); }
				};
			} else if(index==2) {
				return new Response("Kiss her", "Tell [nyanmum.name] that Nyan used to kiss her every morning.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("kiss her"); }
				};
			} else if(index==3) {
				return new Response("Breakfast in bed", "Tell [nyanmum.name] that Nyan used to make breakfast in bed for her every morning.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("make breakfast in bed for her"); }
				};
			} else if(index==4) {
				return new Response("Bake pastries", "Tell [nyanmum.name] that Nyan used to bake pastries for her every morning.", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 2, -20, 20));
					}
				};
			} else if(index==5) {
				return new Response("Play piano", "Tell [nyanmum.name] that Nyan used to play the piano for her every morning.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_4_STORE");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_4_STORE_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Space", "Tell [nyanmum.name] that Nyan's store used to be called 'Space'.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("Space"); }
				};
			} else if(index==2) {
				return new Response("Dapper Duds", "Tell [nyanmum.name] that Nyan's store used to be called 'Dapper Duds'.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("Dapper Duds"); }
				};
			} else if(index==3) {
				return new Response("The Dress Shop", "Tell [nyanmum.name] that Nyan's store used to be called 'The Dress Shop'.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("The Dress Shop"); }
				};
			} else if(index==4) {
				return new Response("[nyanmum.NamePos] Finest", "Tell [nyanmum.name] that Nyan's store used to be called '[nyanmum.NamePos] Finest'.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("[nyanmum.NamePos] Finest"); }
				};
			} else if(index==5) {
				return new Response("Golden Threads", "Tell [nyanmum.name] that Nyan's store used to be called 'Golden Threads'.", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() {
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_5_FLOWER");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_5_FLOWER_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Daisies", "Tell [nyanmum.name] that Nyan's favourite flowers are daisies.", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 4, -20, 20));
					}
				};
			} else if(index==2) {
				return new Response("Tulips", "Tell [nyanmum.name] that Nyan's favourite flowers are tulips.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("tulips"); }
				};
			} else if(index==3) {
				return new Response("Roses", "Tell [nyanmum.name] that Nyan's favourite flowers are roses.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("roses"); }
				};
			} else if(index==4) {
				return new Response("Fuchsias", "Tell [nyanmum.name] that Nyan's favourite flowers are fuchsias.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("fuchsias"); }
				};
			} else if(index==5) {
				return new Response("Hydrangeas", "Tell [nyanmum.name] that Nyan's favourite flowers are hydrangeas.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_6_BOOK");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_6_BOOK_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Nightmare Chapel", "Tell [nyanmum.name] that Nyan's favourite book is called 'Nightmare Chapel'.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("Nightmare Chapel"); }
				};
			} else if(index==2) {
				return new Response("The Peacock's Progress", "Tell [nyanmum.name] that Nyan's favourite book is called 'The Peacock's Progress'.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("The Peacock's Progress"); }
				};
			} else if(index==3) {
				return new Response("One Day in June", "Tell [nyanmum.name] that Nyan's favourite book is called 'One Day in June'.", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 5, -20, 20));
					}
				};
			} else if(index==4) {
				return new Response("The Sign of Eight", "Tell [nyanmum.name] that Nyan's favourite book is called 'The Sign of Eight'.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("The Sign of Eight"); }
				};
			} else if(index==5) {
				return new Response("Lord of the Hats", "Tell [nyanmum.name] that Nyan's favourite book is called 'Lord of the Hats'.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("Lord of the Hats"); }
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_FAIL = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_FAIL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "As you don't want to risk destroying your relationship with Nyan, there really isn't any other option but to do as [nyanmum.name] says and leave...", POST_DATE_APARTMENT_INTERVIEW_FAIL_LEAVE);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_FAIL_LEAVE = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_NYAN_BEDROOM);
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NYAN_APARTMENT);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_FAIL_LEAVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index); //TODO test
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_PASS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) { 
				return new Response("Hug", "Nyan looks like she could really use a reassuring hug right about now.", POST_DATE_APARTMENT_BEDROOM);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_NYAN_BEDROOM);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Kiss", "Give Nyan what she wants...", POST_DATE_APARTMENT_BEDROOM_KISS);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_KISS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			AbstractClothing dress = getNyan().getClothingInSlot(InventorySlot.TORSO_UNDER);
			if(dress!=null) {
				getNyan().unequipClothingIntoVoid(dress, true, getNyan());
			}
			getNyan().removePersonalityTrait(PersonalityTrait.STUTTER);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_KISS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Cunnilingus", "Give the horny cat-girl what she wants by eating her out.",
						true, true,
						new SMNyanSex(
								SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotSitting.SITTING))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
								} else {
									return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
								}
							}
						},
						null,
						null,
						POST_DATE_APARTMENT_BEDROOM_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_KISS_CUNNILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Fingering", "Gently push Nyan down on her bed and finger her while kissing her.",
						true, true,
						new SMNyanSex(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN_TWO)),
								Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA);
								} else {
									return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
								}
							}
							@Override
							public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						POST_DATE_APARTMENT_BEDROOM_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_KISS_FINGERING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueMouth.KISS_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), FingerVagina.FINGERING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_POST_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> receiveOralResponses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			if(Main.game.getPlayer().hasPenisIgnoreDildo()) {
				if(!penisAccess) {
					receiveOralResponses.add(new Response("Sixty-nine"+(Main.game.getPlayer().hasVagina()?" (blowjob)":""), "As you are unable to access your penis, you cannot sixty-nine with Nyan!", null));

				} else if(Main.game.getPlayer().isTaur()) {
					receiveOralResponses.add(new Response("Sixty-nine"+(Main.game.getPlayer().hasVagina()?" (blowjob)":""), "Due to your [pc.legRace]'s lower body, you cannot use this position!", null));
						
				} else {
					receiveOralResponses.add(
							new ResponseSex("Sixty-nine"+(Main.game.getPlayer().hasVagina()?" (blowjob)":""), "Get Nyan to straddle your face and bend down to suck your cock while you're eating her out for a second time.",
									true, true,
									new SMNyanSex(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotLyingDown.SIXTY_NINE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)),
													new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.NIPPLES)));
										}
									},
									null,
									null,
									POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_SIXTY_NINE_BLOWJOB")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisMouth.BLOWJOB_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					receiveOralResponses.add(new Response("Sixty-nine"+(Main.game.getPlayer().hasPenisIgnoreDildo()?" (cunnilingus)":""), "As you are unable to access your vagina, you cannot sixty-nine with Nyan!", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					receiveOralResponses.add(new Response("Sixty-nine"+(Main.game.getPlayer().hasVagina()?" (cunnilingus)":""), "Due to your [pc.legRace]'s lower body, you cannot use this position!", null));
						
				} else {
					receiveOralResponses.add(
							new ResponseSex("Sixty-nine"+(Main.game.getPlayer().hasPenisIgnoreDildo()?" (cunnilingus)":""), "Get Nyan to straddle your face and bend down to eat you out while you're returning the favour.",
									true, true,
									new SMNyanSex(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotLyingDown.SIXTY_NINE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.NIPPLES)));
										}
									},
									null,
									null,
									POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_SIXTY_NINE_CUNNILINGUS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
			}
			
			if(Main.game.getPlayer().hasPenisIgnoreDildo()) {
				if(!penisAccess) {
					receiveOralResponses.add(new Response("Receive blowjob", "As you are unable to access your penis, you cannot get Nyan to suck your cock!", null)); 
				} else {
					receiveOralResponses.add(
							new ResponseSex("Receive blowjob", "Swap positions with Nyan and get her to suck your cock.",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotLyingDown.MISSIONARY_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)),
													new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.NIPPLES)));
										}
									},
									null,
									null,
									POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_BLOWJOB")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisMouth.BLOWJOB_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					receiveOralResponses.add(new Response("Receive cunnilingus", "As you are unable to access your vagina, you cannot get Nyan to perform cunnilingus on you!", null)); 
				} else {
					receiveOralResponses.add(
							new ResponseSex("Receive cunnilingus", "Swap positions with Nyan and get her to return the favour.",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotLyingDown.MISSIONARY_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.NIPPLES)));
										}
									},
									null,
									null,
									POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_CUNNILINGUS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
			}
			
			if(index==1) {
				return new Response("Sleep", "Let Nyan rest and go sleep with her in her bed.", POST_DATE_APARTMENT_BEDROOM_POST_SEX_SLEEP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_SLEEP_START"));
						getNyan().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
						Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Face-sitting", "Get Nyan to sit on your face and eat her out for a second time.", 
								true, true,
								new SMNyanSex(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
										Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotLyingDown.FACE_SITTING))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
										}
									}
									@Override
									public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
										return Util.newHashMapOfValues(
												new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.NIPPLES)));
									}
								},
								null,
								null,
								POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX,
								UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_FACE_SITTING")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true));
							}
						};
			
			} else if(index>0 && index-3<receiveOralResponses.size()) {
				return receiveOralResponses.get(index-3);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX = new DialogueNode("Finished", "Nyan seems to be completely exhausted...", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Sleep", "Let Nyan rest and go sleep with her in her bed.", POST_DATE_APARTMENT_BEDROOM_POST_SEX_SLEEP){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX_SLEEP_START"));// These names...
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_POST_SEX_SLEEP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
			Main.game.getPlayer().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			getNyan().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			getNyanMum().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_SLEEP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Kitchen", "Head into the kitchen to find Nyan.", POST_DATE_APARTMENT_BEDROOM_MORNING);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_MORNING = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().equipClothing();
			getNyan().wearApron(true);
			getNyanMum().wearCasual();
			Main.game.getPlayer().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_MORNING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Pastries", "Eat the pastries which Nyan has made for you and [nyanmum.name].", POST_DATE_APARTMENT_BEDROOM_MORNING_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_MORNING_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_MORNING_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Goodbye", "Kiss Nyan goodbye and tell her that you'll see her soon.", POST_DATE_NO_CONTENT_END) {
					@Override
					public void effects() {
						getNyan().wearApron(false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_MORNING_END_LEAVE"));
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_NO_CONTENT_END = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NYAN_APARTMENT);
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
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
}
