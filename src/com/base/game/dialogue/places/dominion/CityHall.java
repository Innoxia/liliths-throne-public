package com.base.game.dialogue.places.dominion;

import com.base.game.character.Name;
import com.base.game.character.NameTriplet;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.utils.UtilText;
import com.base.main.Main;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public class CityHall {

	public static final DialogueNodeOld OUTSIDE = new DialogueNodeOld("City Hall", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "Before you stands City Hall; the centre of administration for the city of Dominion."
					+ " A sign near the entrance of the impressive building informs you that it's open twenty-four hours a day."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Enter", "The doors are wide open, inviting you to step inside Dominion's City Hall.", CITY_HALL_ENTER);

			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld CITY_HALL_ENTER = new DialogueNodeOld("City Hall ", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You find yourself standing in a huge entrance hall, with a polished marble floor and huge chandeliers giving the impression of obvious wealth and power."
						+ " There are a series of rich mahogany desks scattered around the place, with each one seeming to be responsible for rather specific and mundane problems."
						+ " A helpful map that you find near to the entrance shows the way to some different departments that might be of more use to you."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Demographics", "Follow the map's directions to the 'Bureau of Demographics' office.", CITY_HALL_PUBLIC_ENQUIRIES);

			} else if (index == 0) {
				return new Response("Leave", "Head back outside.", OUTSIDE);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CITY_HALL_PUBLIC_ENQUIRIES = new DialogueNodeOld("Bureau of Demographics", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Following the directions you obtained from the map in the entrance hall, you soon find yourself entering the office marked 'Bureau of Demographics'."
						+ " Shelves fill the stuffy and dimly-lit room, which looks to be more of an archive than a regular office."
						+ " A bored-looking lesser cat-girl sits behind the front desk, her eyes cast down as she looks over a pile of papers and forms."
						+ " If she noticed your arrival, she doesn't show it, and seems to be content to carry on reading what's in front of her as she ignores your presence."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Name change", "Ask the cat-girl about changing your name.", CITY_HALL_NAME_CHANGE_FORM);
				
			} else if (index == 0) {
				return new Response("Back", "Return to the entrance hall.", CITY_HALL_ENTER);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CITY_HALL_NAME_CHANGE_FORM = new DialogueNodeOld("Bureau of Demographics", "-", true) {
		private static final long serialVersionUID = 1L;

		private String nameChangeChoice = "";

		@Override
		public String getContent() {
			return "<p>"
					+ "As you walk up to the desk, the cat-girl looks up, "
					+ UtilText.parseNPCSpeech("What can I help you with?", Femininity.FEMININE)
					+ "</p>"
					+ "<p>You ask about changing your name, and with a sigh, she leans down and starts rummaging around under her desk."
					+ " After a moment she seems to find what she's looking for and sits back upright, handing you a pink form before looking back down at the papers on her desk."
					+ "</p>"
					+ "</p>"
					+ UtilText.parseNPCSpeech("Fill in what you'd like to change your name to, then you have to pay the admin fee of 100 flames,", Femininity.FEMININE)
					+ " she drones, not bothering to look up from the papers in front of her."
					+ "</p>"
					+ "<p>"
					+ "Your new name must be between 2 and 16 characters long. It can only include the standard letters a-z and their respective capitals"
					+ "<form><input type='text' id='nameInput' value='"
					+ (nameChangeChoice.length() == 0 ? Main.game.getPlayer().getName() : nameChangeChoice)
					+ "'></form>"
					+ "</p>"
					+ "<p id='hiddenPField' style='display:none;'></p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getMoney() < 100)
					return new Response("Confirm", "Change name for 100 flames. (You can't afford this!)", null);
				else
					return new Response("Confirm", "Change name for 100 flames.", CITY_HALL_NAME_CHANGE_FORM){
						@Override
						public void effects() {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('nameInput').value;");
							
							if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent().length() >= 2
									&& Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent().length() < 16
									&& Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent().matches("[a-zA-Z]+")){
								
								Main.game.getPlayer().setName(new NameTriplet(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent()));
								Main.game.getPlayer().incrementMoney(-100);
								
							}
						}
					};

			} else if (index == 2) {
				return new Response("Random", "Randomly generate a name (but won't be applied until you confirm).", CITY_HALL_NAME_CHANGE_FORM){
						@Override
						public void effects() {
							nameChangeChoice = Name.getRandomName(Main.game.getPlayer());
						}
					};

			} else if (index == 0) {
				return new Response("Back", "Decide not to change your name.", CITY_HALL_PUBLIC_ENQUIRIES);

			} else {
				return null;
			}
		}
	};
	
	
}
