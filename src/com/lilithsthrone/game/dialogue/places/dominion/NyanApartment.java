package com.lilithsthrone.game.dialogue.places.dominion;

import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class NyanApartment {

	private static int dateBill;
	
	private static Nyan getNyan() {
		return ((Nyan)Main.game.getNpc(Nyan.class));
	}
	
	private static void travelTo(AbstractWorldType worldType, AbstractPlaceType placeType) {
		Main.game.getPlayer().setLocation(worldType, placeType);
		getNyan().setLocation(Main.game.getPlayer(), false);
	}

	public static final DialogueNode DATE_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			dateBill = 0;
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_HALLWAY);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyanApartment", "DATE_START");
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
			return UtilText.parseFromXMLFile("places/dominion/nyanApartment", "DATE_START_LOUNGE");
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
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			getNyan().wearDress();
			getNyan().wearCoat(true, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyanApartment", "DATE_START_LOUNGE_NYAN_DRESS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Restaurant", "Accompany Nyan to the restaurant.", DATE_START_RESTAURANT);
			}
			return null;
		}
	};

	public static final DialogueNode DATE_START_RESTAURANT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyanApartment", "DATE_START_RESTAURANT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Feline ("+UtilText.formatAsMoney(350)+")", "Order the cheapest wine on the menu, which is nevertheless still a very good vintage.", null) {
					@Override
					public void effects() {
						dateBill+=350;
					}
				};
				
			} else if(index==2) {
				return new Response("Vulpine ("+UtilText.formatAsMoney(600)+")", "Order one of the more expensive wines on the menu.", null) {
					@Override
					public void effects() {
						dateBill+=600;
					}
				};
				
			} else if(index==3) {
				return new Response("Youko ("+UtilText.formatAsMoney(950)+")", "Order the more expensive wine on the menu.", null) {
					@Override
					public void effects() {
						dateBill+=950;
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_END = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyanApartment", "DATE_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("", "", DATE_END_RETURN) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(-dateBill);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_END_RETURN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NYAN_APARTMENT);
			getNyan().setLocation(Main.game.getPlayer(), false);
			((Nyan)getNyan()).wearCoat(true, false);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyanApartment", "DATE_END_RETURN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				//TODO inside
				
			} else if(index==2) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Goodbye kiss", "As you are unable to access your mouth, you cannot kiss Nyan!", null);
					
				} else {
					return new Response("Goodbye kiss", "Kiss Nyan goodbye and tell her that you'll see her soon.", DATE_END_RETURN_GOODBYE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyanApartment", "DATE_END_RETURN_GOODBYE_KISS")); //TODO
							((Nyan)getNyan()).wearCoat(false, true);
						}
					};
				}
				
			} else if(index==3) {
				return new Response("Say goodbye", "Say goodbye to Nyan and tell her that you'll see her soon.", DATE_END_RETURN_GOODBYE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyanApartment", "DATE_END_RETURN_GOODBYE")); //TODO
						((Nyan)getNyan()).wearCoat(false, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_END_RETURN_GOODBYE = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_NYAN_BEDROOM);
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

	public static final DialogueNode VISIT_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_HALLWAY);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyanApartment", "VISIT_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				//TODO
			}
			return null;
		}
	};
	
	public static final DialogueNode HALLWAY = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyanApartment", "HALLWAY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode ENTRANCE_HALL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyanApartment", "ENTRANCE_HALL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode NYAN_BEDROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyanApartment", "NYAN_BEDROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode ENSUITE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyanApartment", "ENSUITE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode SPARE_BEDROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyanApartment", "SPARE_BEDROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode BATHROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyanApartment", "BATHROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode KITCHEN = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyanApartment", "KITCHEN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode DINING_ROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyanApartment", "DINING_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode LOUNGE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyanApartment", "LOUNGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
}
