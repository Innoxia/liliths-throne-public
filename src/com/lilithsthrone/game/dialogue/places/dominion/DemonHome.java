package com.lilithsthrone.game.dialogue.places.dominion;

import com.lilithsthrone.game.character.Quest;
import com.lilithsthrone.game.character.QuestLine;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.1.85
 * @author Innoxia
 */
public class DemonHome {
	public static final DialogueNodeOld DEMON_HOME_STREET = new DialogueNodeOld("Demon Home", "Demon Home", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "As you find yourself walking through the district of Demon Home, you see that it's quite clearly the more upmarket area of Dominion."
					+ " Statues line the wide, marble-paved streets, and the impressive buildings on either side represent the pinnacle of neoclassical design."
					+ " Here and there, little fenced-off parks break up the monotony of the marble facades, and you find yourself duly impressed with this area's aesthetics."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)) {
					return null;
				} else {
					if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME)
						return new Response("Arthur's Apartment", "Find Arthur's apartment using the instructions Lilaya gave to you.", DEMON_HOME_ARTHURS_APARTMENT);
					else
						return new Response("Arthur's Apartment", "Head over to Arthur's apartment building.", DEMON_HOME_ARTHURS_APARTMENT);
				}

			} else if (index == 2) {
				if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_G_SLAVERY)) {
					return new Response("Zaranix's Home", "This is as far as the main quest goes for now! I'll add more in the next couple of versions! Thank you for playing! ^^", null);
				}
				return null;

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld DEMON_HOME_ARTHURS_APARTMENT = new DialogueNodeOld("", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Arthur's Apartment Building";
		}

		@Override
		public String getContent() {
			if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME)
				return "<p>" + "Following Lilaya's instructions, you soon find yourself standing outside the building which houses Arthur's apartment."
						+ " Although it's just as impressive as most of the other buildings in Demon Home, it's nothing compared to Lilaya's house, and as you walk up to the entrance,"
						+ " you find yourself reflecting on how lucky you were to have ended up living with this universe's version of your aunt Lily." + "</p>" + "<p>"
						+ "The front door is unlocked, and as you step into the foyer, you see that this place looks more like a five-star hotel than an apartment building."
						+ " The luxurious carpeting, fine paintings and crystal chandeliers all contribute to giving the impression that the apartments here are very exclusive, and very expensive."
						+ " Although there's a front desk, the place seems to be deserted, but fortunately there's a sign on a nearby wall that lists the building's occupants" + " and their respective room numbers."
						+ " You see that Arthur lives on the first floor, in room five." + "</p>";
			else
				return "<p>" + "You soon find yourself standing outside the building which houses Arthur's apartment."
						+ " Although it's just as impressive as most of the other buildings in Demon Home, it's nothing compared to Lilaya's house, and as you walk up to the entrance,"
						+ " you find yourself reflecting on how lucky you were to have ended up living with this universe's version of your aunt Lily." + "</p>" + "<p>"
						+ "The front door is unlocked, and as you step into the foyer, you once again take note that this place looks more like a five-star hotel than an apartment building."
						+ " The luxurious carpeting, fine paintings and crystal chandeliers all contribute to giving the impression that the apartments here are very exclusive, and very expensive."
						+ " The front desk is unmanned yet again, and you wonder what to do now that you're here." + "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME) {
					return new Response("Arthur's Room", "Head up to Arthur's room.", DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM){
						@Override
						public QuestLine getQuestLine() {
							if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME)
								return QuestLine.MAIN;
							else
								return null;
						}
					};
					
				} else {
					return null;
				}
				
			} else if (index == 2) {
				if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)) {
					return new Response("Felicity's room", "Head up to Felicity's room.", DEMON_HOME_ARTHURS_APARTMENT_FELICITYS_ROOM);
				} else {
					return null;
				}
				
			} else if (index == 0) {
				return new Response("Leave", "Leave the building and head back out into Demon Home.", DEMON_HOME_STREET);
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM = new DialogueNodeOld("", "-", true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Arthur's Room";
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "You head over to the staircase and make your way up to the first floor."
					+ " Turning down a long, straight corridor, you walk along until you find room number five."
					+ " As you approach, you see that a note has been stuck to the door, and, coming to a halt in front of it, you let out a sigh."
					+ "</p>"
					+ "<p>"
					+ "The note reads:"
					+ "</p>"
					+ "<h6 style='text-align:center;'>Dominion Enforcer Department</h6>"
					+ "<h5 style='text-align:center;'>NOTICE OF ARREST</h5>"
					+ "<p style='text-align:center;'>The occupant of this residence, namely the person of <i>Arthur Fairbanks</i>, has been issued with an arrest warrant.</br>"
					+ "These premises are therefore under investigation by Dominion's Enforcer Department, and any unauthorised entry beyond this point is in violation of the law."
					+ "</br></br>"
					+ "Officer in charge of issuing the warrant: <i>Brax</i>"
					+ "</br></br>"
					+ "Any complaints or inquiries should be made in person at Dominion's Enforcer HQ. Thank you for your understanding.</p>"
					+ "</br>"
					+ "</p>"
					+ "<p>"
					+ UtilText.parsePlayerThought("Great... So he's been arrested...")
					+ " you think, letting out another sigh."
					+ "</p>"
					+ "<p>"
					+ "As you're reading the notice for a second time, you hear a soft scuffing noise close behind you."
					+ " Spinning round, you see a partial dog-girl standing in the doorway behind you, with a curious look on her face."
					+ " As you turn to face her, she lets out a little eek in surprise, and quickly steps back into her apartment."
					+ " Or, at least, that's what she tries to do, but in her excitement, she manages to trip over her own feet, falling backwards into the doorway."
					+ "</p>"
					+ "<p>"
					+ UtilText.parseNPCSpeech("Ow!", Femininity.FEMININE)
					+ " she cries, as she lands on her bottom."
					+ "</p>"
					+ "<p>"
					+ "Sensing a source of information, you step over to the dog-girl and offer her a hand up."
					+ " She glances up at you with a worried look in her eyes, "
					+ UtilText.parseNPCSpeech("Y-you're not an enforcer... are you?", Femininity.FEMININE)
					+ " she asks."
					+ "</p>"
					+ "<p>"
					+ UtilText.parsePlayerSpeech("No,")
					+ " you reply, "
					+ UtilText.parsePlayerSpeech("I'm just here to check in on a friend, but it looks like he's not here anymore...")
					+ "</p>"
					+ "<p>"
					+ UtilText.parseNPCSpeech("Ah, well, in that case, I'm Felicity, Arthur's friend,", Femininity.FEMININE)
					+ " she says, reaching up to take your offered hand. You quickly help her"
					+ " to her feet as you introduce yourself, and she smiles and thanks you for your help before continuing, "
					+ UtilText.parseNPCSpeech("If you're looking to find out what happened, I'm afraid I don't really know much."
							+ " Late last night, the enforcers showed up, banging on Arthur's door."
							+ " When he answered, I heard them say something about plotting against Lilith, then they arrested him and dragged him away."
							+ " I don't know what he did wrong... He's such a nice guy...", Femininity.FEMININE)
					+ "</p>"
					+ "<p>"
					+ "It seems like she can't offer you any useful information, so you thank her for what she's told you before continuing on your way."
					+ " The arrest warrant mentioned that any inquiries need to be made in person at the Enforcer's HQ, so you'll need to make that your next stop."
					+ " You couldn't help but notice that Felicity seemed to be glancing hungrily at your body the whole time she was talking to you, and you wonder if you should pay her a visit sometime later..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Leave", "You've done all you can here. Head back outside to Demon Home.", DEMON_HOME_STREET);
				
			} else {
				return null;
			}
		}

	};

	public static final DialogueNodeOld DEMON_HOME_ARTHURS_APARTMENT_FELICITYS_ROOM = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Felicity's Room";
		}

		@Override
		public String getContent() {
			return "<p>" + "You head over to the staircase and make your way up to the first floor." + " Turning down a long, straight corridor, you walk along until you're standing outside Felicity's room."
					+ " Reaching up, you deliver a quick knock before standing back and waiting for the dog-girl to answer." + " After a few moments of silence, you decide to knock again, but there doesn't seem to be anyone home at the moment."
					+ " Sighing, you give up and decide to come back another time." + "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Leave", "You've done all you can here. Head back outside to Demon Home.</br>" + "<b>There is no further content for Felicity in this version.</b>", DEMON_HOME_STREET);
				
			} else {
				return null;
			}
		}
	};
}
