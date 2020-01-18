package com.lilithsthrone.game.dialogue.places.dominion.cityHall;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.CityPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.2
 * @author Innoxia
 */
public class CityHall {

	public static final DialogueNode OUTSIDE = new DialogueNode("City Hall", "-", false) {

		@Override
		public int getSecondsPassed() {
			return CityPlaces.TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "OUTSIDE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getHourOfDay()>=9 && Main.game.getHourOfDay()<=16) {
					return new Response("Enter", "Dominion's city hall is currently open to the public, so you could head inside if you wanted to.", CITY_HALL_FOYER) {
						@Override
						public void effects() {
							Main.mainController.moveGameWorld(WorldType.CITY_HALL, PlaceType.CITY_HALL_ENTRANCE, true);
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "ENTRY"));
						}
					};
					
				} else {
					return new Response("Enter", "Dominion's city hall is currently closed to the public, so if you had any business to conduct, you'll have to return between the hours of nine in the morning, and four in the afternoon.", null);
				}

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_HALL_FOYER = new DialogueNode("Foyer", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_FOYER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Exit", "Head out through the revolving glass doors back into Dominion.", OUTSIDE) {
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.DOMINION, PlaceType.DOMINION_CITY_HALL, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_HALL_INFORMATION_DESK = new DialogueNode("Information Desk", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_INFORMATION_DESK");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Name change", "Ask one of the receptionists about changing your name.", CITY_HALL_INFORMATION_DESK_NAME_CHANGE);
				
			} else if(index == 2) {
				return new Response("Property", "Ask one of the receptionists about buying or renting property in Dominion.", CITY_HALL_INFORMATION_DESK_PROPERTY);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_HALL_INFORMATION_DESK_NAME_CHANGE = new DialogueNode("Information Desk", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_INFORMATION_DESK_NAME_CHANGE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Name change", "You've just asked about changing your name.", null);
				
			} else if(index == 2) {
				return new Response("Property", "Ask one of the receptionists about buying or renting property in Dominion.", CITY_HALL_INFORMATION_DESK_PROPERTY);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_HALL_INFORMATION_DESK_PROPERTY = new DialogueNode("Information Desk", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_INFORMATION_DESK_PROPERTY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Name change", "Ask one of the receptionists about changing your name.", CITY_HALL_INFORMATION_DESK_NAME_CHANGE);
				
			} else if(index == 2) {
				return new Response("Property", "You've just asked about buying or renting property in Dominion.", null);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_HALL_CORRIDOR = new DialogueNode("Corridor", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 15;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_CORRIDOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode CITY_HALL_WAITING_AREA = new DialogueNode("Waiting Room", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_WAITING_AREA");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode CITY_HALL_OFFICE = new DialogueNode("Private Office", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_OFFICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode CITY_HALL_STAIRS = new DialogueNode("Staircase", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_STAIRS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
}
