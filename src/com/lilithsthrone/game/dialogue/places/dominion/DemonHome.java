package com.lilithsthrone.game.dialogue.places.dominion;

import java.time.Month;

import com.lilithsthrone.game.Season;
import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloorRepeat;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.2.2
 * @author Innoxia
 */
public class DemonHome {
	
	public static final DialogueNode DEMON_HOME_GATE = new DialogueNode("Demon Home (Gates)", "Demon Home", false) {
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<p>"
						+ "A set of huge, iron gates have been built across the street here, separating the regular areas of Dominion from that known as 'Demon Home' beyond."
						+ " Half a dozen elite demon Enforcers are stationed here, keeping a close eye on anyone who comes and goes."
					+ "</p>"
					+ "<p>"
						+ "As you walk forwards to pass through the gates, you see one of these demonic guards staring at you."
						+ " Ignoring their penetrating gaze, you stride forwards, breathing a sigh of relief as you get through to the other side without being stopped."
					+ "</p>");
			
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "<b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Arcane Storm:</b><br/>"
							+ "The arcane storm that's raging overhead has brought out a heavy presence of demon Enforcers in this area."
							+ " Unaffected by the arousing power of the storm's thunder, these elite Enforcers keep a close watch on you as you pass through the all-but-deserted area known as Demon Home."
							+ " There's no way anyone would be able to assault you while under their watchful gaze, allowing you continue on your way in peace..."
						+ "</p>");
			}

			if(Main.game.getDateNow().getMonth()==Month.OCTOBER) {
				UtilText.nodeContentSB.append(
					"<p>"
						+ "<b style='color:"+Colour.BASE_ORANGE.toWebHexString()+";'>October;</b> <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Lilith's Month:</b><br/>"
						+ "Orange, black, and purple flags fly from almost every window, and you look up to see that large banners have been hung across the street, each one bearing a different slogan celebrating Lilith's rule."
						+ " The occasional demon that you see is usually dressed up in a Halloween-esque costume for the occasion, which does nothing to help alleviate the eerie atmosphere."
					+ "</p>");
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter) && Main.game.getSeason()==Season.WINTER) {
				UtilText.nodeContentSB.append(
					"<p>"
						+ "<b style='color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>Snow:</b><br/>"
						+ "The reindeer-morph workers are doing a good job of keeping Dominion's streets clear from the snow, but the rooftops, trees, and tops of lamp posts are all home to a thick layer of white."
						+ " You see your breath exiting your mouth in a little cloud of condensation, but despite the clear evidence of the air's freezing temperature, your arcane aura protects your body from feeling the cold."
					+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode DEMON_HOME_STREET = new DialogueNode("Demon Home", "Demon Home", false) {
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<p>"
						+ "From the wide, marble-paved streets, to the immaculate frontages of the regency-style buildings, it's quite clear that this district of 'Demon Home' is one of the more upmarket areas of Dominion."
						+ " Numerous masterfully-carved statues, the vast majority of which depict some form of demon or another, are dotted around the area, and, considering their subject matter,"
							+ " you assume that these sculptures are what gives this area its name."
					+ "</p>"
					+ "<p>"
						+ "As you walk down the street, you pass several fenced-off private gardens; their lush splash of greenery helping to break up the monotony of the surrounding building's creamy-white stone facades."
						+ " Despite the fact that Demon Home is a little quieter than most of the other areas of Dominion, you notice that there are slightly more enforcers patrolling the streets;"
									+ " evidence that the wealthy and influential residents of the city are afforded extra protection."
					+ "</p>");
			
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "<b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Arcane Storm:</b><br/>"
							+ "The arcane storm that's raging overhead has brought out a heavy presence of demon Enforcers in this area."
							+ " Unaffected by the arousing power of the storm's thunder, these elite Enforcers keep a close watch on you as you pass through the all-but-deserted area known as Demon Home."
							+ " There's no way anyone would be able to assault you while under their watchful gaze, allowing you continue on your way in peace..."
						+ "</p>");
			}

			if(Main.game.getDateNow().getMonth()==Month.OCTOBER) {
				UtilText.nodeContentSB.append(
					"<p>"
						+ "<b style='color:"+Colour.BASE_ORANGE.toWebHexString()+";'>October;</b> <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Lilith's Month:</b><br/>"
						+ "Orange, black, and purple flags fly from almost every window, and you look up to see that large banners have been hung across the street, each one bearing a different slogan celebrating Lilith's rule."
						+ " The occasional demon that you see is usually dressed up in a Halloween-esque costume for the occasion, which does nothing to help alleviate the eerie atmosphere."
					+ "</p>");
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter) && Main.game.getSeason()==Season.WINTER) {
				UtilText.nodeContentSB.append(
					"<p>"
						+ "<b style='color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>Snow:</b><br/>"
						+ "The reindeer-morph workers are doing a good job of keeping Dominion's streets clear from the snow, but the rooftops, trees, and tops of lamp posts are all home to a thick layer of white."
						+ " You see your breath exiting your mouth in a little cloud of condensation, but despite the clear evidence of the air's freezing temperature, your arcane aura protects your body from feeling the cold."
					+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode DEMON_HOME_STREET_ARTHUR = new DialogueNode("Demon Home", "Demon Home", false) {
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return DEMON_HOME_STREET.getContent();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME) {
					return new Response("Arthur's Apartment", "Find Arthur's apartment using the instructions Lilaya gave to you.", DEMON_HOME_ARTHURS_APARTMENT);
					
				} else if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)) {
					return new Response("Arthur's Apartment", "Head over to Arthur's apartment building.", DEMON_HOME_ARTHURS_APARTMENT);
					
				} else {
					return null;
				}

			} else if (index == 2) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_H_THE_GREAT_ESCAPE) {
					return new Response("Zaranix's Home", "A little way down the road from Arthur's apartment building stands the home of Zaranix; the demon that Scarlett told you about.", ZaranixHomeGroundFloor.OUTSIDE);
					
				} else if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
					return new Response("Zaranix's Home", "Pay Zaranix another visit.", ZaranixHomeGroundFloorRepeat.OUTSIDE);
				}
				return null;

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT = new DialogueNode("", "-", true) {

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
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME) {
					return new Response("Arthur's Room", "Head up to Arthur's room.", DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM){
						@Override
						public void effects() {
							if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN));
							}
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
				return new Response("Leave", "Leave the building and head back out into Demon Home.", DEMON_HOME_STREET_ARTHUR);
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM = new DialogueNode("", "-", true) {
		/**
		 */

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
					+ "<p style='text-align:center;'>The occupant of this residence, namely the person of <i>Arthur Fairbanks</i>, has been issued with an arrest warrant.<br/>"
					+ "These premises are therefore under investigation by Dominion's Enforcer Department, and any unauthorised entry beyond this point is in violation of the law."
					+ "<br/><br/>"
					+ "Officer in charge of issuing the warrant: <i>Brax</i>"
					+ "<br/><br/>"
					+ "Any complaints or inquiries should be made in person at Dominion's Enforcer HQ. Thank you for your understanding.</p>"
					+ "<br/>"
					+ "</p>"
					+ "<p>"
					+ UtilText.parsePlayerThought("Great... So he's been arrested...")
					+ " you think, letting out another sigh."
					+ "</p>"
					+ "<p>"
					+ "As you're reading the notice for a second time, you hear a soft scuffling noise close behind you."
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
					+ " She glances up at you with a worried look in her eyes. "
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
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Leave", "You've done all you can here. Head back outside to Demon Home.", DEMON_HOME_STREET_ARTHUR);
				
			} else {
				return null;
			}
		}

	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT_FELICITYS_ROOM = new DialogueNode("", "", true) {

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
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Leave", "You've done all you can here. Head back outside to Demon Home.<br/>" + "<b>There is no further content for Felicity in this version.</b>", DEMON_HOME_STREET_ARTHUR);
				
			} else {
				return null;
			}
		}
	};
}
