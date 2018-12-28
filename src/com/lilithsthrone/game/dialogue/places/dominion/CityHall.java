package com.lilithsthrone.game.dialogue.places.dominion;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.2.12
 * @author Innoxia
 */
public class CityHall {

	public static final DialogueNode OUTSIDE = new DialogueNode("City Hall", "-", false) {

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
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Enter", "The doors are wide open, inviting you to step inside Dominion's City Hall.", CITY_HALL_ENTER);

			} else {
				return null;
			}
		}
	};
	public static final DialogueNode CITY_HALL_ENTER = new DialogueNode("City Hall ", "-", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "You find yourself standing in a huge entrance hall, with a polished marble floor and huge chandeliers giving the impression of obvious wealth and power."
						+ " There are a series of rich mahogany desks scattered around the place, with each one seeming to be responsible for rather specific and mundane problems."
						+ " A helpful map that you find near to the entrance shows the way to some different departments that might be of more use to you."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Demographics", "Follow the map's directions to the 'Bureau of Demographics' office.", CITY_HALL_PUBLIC_ENQUIRIES);

			} else if (index == 0) {
				return new Response("Leave", "Head back outside.", OUTSIDE);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_HALL_PUBLIC_ENQUIRIES = new DialogueNode("Bureau of Demographics", "-", true) {

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
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Name change", "Ask the cat-girl about changing your name.", CITY_HALL_NAME_CHANGE_FORM);
				
			} else if (index == 0) {
				return new Response("Back", "Return to the entrance hall.", CITY_HALL_ENTER);

			} else {
				return null;
			}
		}
	};

	private static boolean unsuitableName = false;
	private static boolean unsuitableSurname = false;
	
	public static final DialogueNode CITY_HALL_NAME_CHANGE_FORM = new DialogueNode("Bureau of Demographics", "-", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "The cat-girl looks up as you approach her desk, and lets out a tired sigh, before asking, "
						+ "[genericFemale.speech(What can I help you with?)]"
					+ "</p>"
					+ "<p>"
						+ "You ask about changing your name, and with a sigh, she leans down and starts rummaging around under her desk."
						+ " After a moment she seems to find what she's looking for and sits back upright, handing you a pink form before looking back down at the papers on her desk."
					+ "</p>"
					+ "<p>"
						+"[genericFemale.speech(Fill in what you'd like to change your name to, then you have to pay the admin fee of one hundred flames,)]"
						+ " she drones, not bothering to look up from the papers in front of her."
						+" [genericFemale.speech(If you want all of your offspring to have their surnames changed to your new one, then that'll be five thousand flames. It's not easy tracking them all down in our records, you know.)]"
					+ "</p>"
					+"<br/>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<div style='position:relative; display:inline-block; padding-bottom:0; margin 0 auto; vertical-align:middle; width:100%; text-align:center;'>"
							+ "<p style='display:inline-block; padding:0; margin:0; height:32px; line-height:32px; width:100px;'>First name: </p>"
							+ "<form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' id='nameInput' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getName())+ "'></form>"
							+ "<br/>"
							+ "<p style='display:inline-block; padding:0; margin:0; height:32px; line-height:32px; width:100px;'>Surname: </p>"
							+ "<form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' id='surnameInput' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getSurname())+ "'></form>"
						+ "</div>"
						+ "<br/>"
						+ "<i>Your name must be between 2 and 16 characters long. You cannot use the square bracket characters or full stops. (Surname may be left blank.)</i>"
						+ (unsuitableName ? "<p style='text-align:center;padding-top:0;'><b style=' color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Invalid name.</b></p>" : "")
						+ (unsuitableSurname ? "<p style='text-align:center;padding-top:0;'><b style=' color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Invalid Surname.</b></p>" : "")
					+ "</div>"
					
					+ "<p id='hiddenFieldName' style='display:none;'></p>"
					+ "<p id='hiddenFieldSurname' style='display:none;'></p>";
			
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getMoney() < 100) {
					return new Response("Confirm ("+UtilText.formatAsMoneyUncoloured(100, "span")+")",
							"Have your name changed.<br/>[style.italicsBad(You cannot afford this!)]",
							null);
					
				} else {
					return new ResponseEffectsOnly("Confirm ("+UtilText.formatAsMoney(100, "span")+")", "Have your name changed.<br/>This will cost "+UtilText.formatAsMoney(100)+"."){
						@Override
						public void effects() {
							applyNameChange(false);
						}
					};
				}
				
			} else if(index==2) {
				if(Main.game.getPlayer().getAllCharactersOfRelationType(Relationship.PARENT).isEmpty()) {
					return new Response("Offspring ("+UtilText.formatAsMoneyUncoloured(5000, "span")+")",
							"Change your name, and also have all lines of your offspring update their surnames to your surname.<br/>[style.italicsBad(You do not have any children, so you can't do this!)]", null);
					
				} else if (Main.game.getPlayer().getMoney() < 5000) {
					return new Response("Offspring ("+UtilText.formatAsMoneyUncoloured(5000, "span")+")",
							"Change your name, and also have all lines of your offspring update their surnames to your surname.<br/>[style.italicsBad(You cannot afford this!)]", null);
					
				} else {
					return new ResponseEffectsOnly("Offspring ("+UtilText.formatAsMoney(5000, "span")+")",
							"Change your name, and also have all lines of your offspring update their surnames to your surname.<br/>This will cost "+UtilText.formatAsMoney(5000)+"."){
						@Override
						public void effects() {
							applyNameChange(true);
						}
					};
				}
				
			} else if (index == 0) {
				return new Response("Back", "Decide not to change your name.", CITY_HALL_PUBLIC_ENQUIRIES);

			} else {
				return null;
			}
		}
	};
	
	private static void applyNameChange(boolean applyOffspringSurnames) {
		Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('nameInput').value;");
		if(Main.mainController.getWebEngine().getDocument()!=null) {
			if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 2
					|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32
					|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().matches("[^\\[\\]\\.]+"))
				unsuitableName = true;
			else {
				unsuitableName = false;
			}
		}
		Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldSurname').innerHTML=document.getElementById('surnameInput').value;");
		if(Main.mainController.getWebEngine().getDocument()!=null) {
			if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()==0
					&& (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length() > 32
							|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().matches("[^\\[\\]\\.]+")))
				unsuitableSurname = true;
			else {
				unsuitableSurname = false;
			}
		}
		
		if(applyOffspringSurnames && Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()==0) {
			unsuitableSurname = true;
		}
		
		if (unsuitableName || unsuitableSurname)  {
			Main.game.setContent(new Response("" ,"", CITY_HALL_NAME_CHANGE_FORM));
			
		} else {
			Main.game.getPlayer().setName(new NameTriplet(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent()));
			Main.game.getPlayer().setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent());
			
			if(applyOffspringSurnames && Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()>=1) {
				for(NPC npc : Main.game.getAllNPCs()) {
					GameCharacter mother = npc.getMother();
					if(mother!=null) {
						while(mother.getMother()!=null) {
							mother = mother.getMother();
						}
						if(mother.isPlayer()) {
							npc.setSurname(Main.game.getPlayer().getSurname());
						}
					}
				}
			}
			if(applyOffspringSurnames) {
				Main.game.getTextEndStringBuilder().append(
						"<p style='text-align:center;'>"
							+ "You fill out the forms and hand over the required fee, officially changing your name to:<br/>"
							+ "<b>[pc.Name]"+(!Main.game.getPlayer().getSurname().isEmpty()?" [pc.Surname]":"")+"</b><br/>"
							+ "All of your offspring, all the way down your family tree, have had their surnames legally changed to:<br/>"
							+ "<b>[pc.Surname]</b><br/>"
						+ "</p>"
						+Main.game.getPlayer().incrementMoney(-5000));
			} else {
				Main.game.getTextEndStringBuilder().append(
						"<p style='text-align:center;'>"
							+ "You fill out the forms and hand over the required fee, officially changing your name to:<br/>"
							+ "<b>[pc.Name]"+(!Main.game.getPlayer().getSurname().isEmpty()?" [pc.Surname]":"")+"</b>"
						+ "</p>"
						+Main.game.getPlayer().incrementMoney(-100));
			}
			Main.game.setContent(new Response("" ,"", CITY_HALL_NAME_CHANGE_FORM));
		}
	}
	
}
