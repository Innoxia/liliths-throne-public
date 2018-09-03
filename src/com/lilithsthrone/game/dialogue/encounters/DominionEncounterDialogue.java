package com.lilithsthrone.game.dialogue.encounters;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.75
 * @author Innoxia
 */
public class DominionEncounterDialogue {

	public static final DialogueNodeOld ALLEY_FIND_ITEM = new DialogueNodeOld("Abandoned package", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>As you walk down yet another narrow passage, you notice a brown paper package lying innocently beside a metal bin."
					+ " It looks as though it's still sealed, and even from a casual glance you can tell that it isn't any old rubbish."
					+ " You can't see any signs of a trap, and the package looks to be completely abandoned, so you decide to take a closer look.</p>"

					+ "<p>You bend down to see that it's a square, medium-sized box, covered in brown packaging paper and sealed with sticky tape." + " The label on the side has been smeared by some sort of liquid, leaving it totally illegible."
					+ " A musky smell suddenly fills your nostrils, and you realise that the liquid is in fact sticky white cum."
					+ " As you wonder what series of events led to this package being abandoned here, you notice that there's splatters of cum all over the floor and up part of the wall."
					+ " There's no way to find out who the package was originally intended for, so, not wanting it to go to waste, you gingerly peel back the brown paper to reveal:" + "</p>" + "<p style='text-align:center;'>" + "<b>"
					+ Encounter.getRandomItem().getDisplayName(true) + "</b>" + "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Take", "Add the " + Encounter.getRandomItem().getName() + " to your inventory.", Main.game.getDefaultDialogueNoEncounter()){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem(Encounter.getRandomItem(), true, true));
					}
				};
				
			} else if (index == 2) {
				return new Response("Leave", "Leave the " + Encounter.getRandomItem().getName() + " on the floor.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALLEY_FIND_CLOTHING = new DialogueNodeOld("Abandoned package", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>As you walk down yet another narrow passage, you notice a brown paper package lying innocently beside a metal bin."
					+ " It looks as though it's still sealed, and even from a casual glance you can tell that it isn't any old rubbish."
					+ " You can't see any signs of a trap, and the package looks to be completely abandoned, so you decide to take a closer look.</p>"

					+ "<p>You bend down to see that it's a square, medium-sized box, covered in brown packaging paper and sealed with sticky tape." + " The label on the side has been smeared by some sort of liquid, leaving it totally illegible."
					+ " A musky smell suddenly fills your nostrils, and you realise that the liquid is in fact sticky white cum."
					+ " As you wonder what series of events led to this package being abandoned here, you notice that there's splatters of cum all over the floor and up part of the wall."
					+ " There's no way to find out who the package was originally intended for, so, not wanting it to go to waste, you gingerly peel back the brown paper to reveal:" + "</p>" + "<p style='text-align:center;'>"
					+ "<b>"
					+ Util.capitaliseSentence(Encounter.getRandomClothing().getName(true, true)) + "</b>" + "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Take", "Add the " + Encounter.getRandomClothing().getName() + " to your inventory.", Main.game.getDefaultDialogueNoEncounter()){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addClothing(Encounter.getRandomClothing(), true));
					}
				};
				
			} else if (index == 2) {
				return new Response("Leave", "Leave the " + Encounter.getRandomClothing().getName() + " on the floor.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALLEY_FIND_WEAPON = new DialogueNodeOld("Abandoned package", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>As you walk down yet another narrow passage, you notice a brown paper package lying innocently beside a metal bin."
					+ " It looks as though it's still sealed, and even from a casual glance you can tell that it isn't any old rubbish."
					+ " You can't see any signs of a trap, and the package looks to be completely abandoned, so you decide to take a closer look.</p>"

					+ "<p>You bend down to see that it's a square, medium-sized box, covered in brown packaging paper and sealed with sticky tape." + " The label on the side has been smeared by some sort of liquid, leaving it totally illegible."
					+ " A musky smell suddenly fills your nostrils, and you realise that the liquid is in fact sticky white cum."
					+ " As you wonder what series of events led to this package being abandoned here, you notice that there's splatters of cum all over the floor and up part of the wall."
					+ " There's no way to find out who the package was originally intended for, so, not wanting it to go to waste, you gingerly peel back the brown paper to reveal:" + "</p>" + "<p style='text-align:center;'>" + "<b>"
					+ Util.capitaliseSentence(Encounter.getRandomWeapon().getName(true, true)) + "</b>" + "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Take", "Add the " + Encounter.getRandomWeapon().getName() + " to your inventory.", Main.game.getDefaultDialogueNoEncounter()){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addWeapon(Encounter.getRandomWeapon(), true));
					}
				};
				
			} else if (index == 2) {
				return new Response("Leave", "Leave the " + Encounter.getRandomWeapon().getName() + " on the floor.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NESTS_FIND_ITEM = new DialogueNodeOld("Dropped item", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "As you travel down the winding walkways, you suddenly catch sight of a flash of pink on the path ahead."
						+ " Walking up to the splash of colour, you look down to see:"
					+ "</p>"
					
					+ "<p style='text-align:center;'><b>"
						+ Encounter.getRandomItem().getDisplayName(true)
					+ "</b></p>"
					+ "<p>"
						+ "Either due to being dropped by a harpy flying overhead, or perhaps having rolled off the edge of the platform above you, the "+Encounter.getRandomItem().getName()+" seems to be completely abandoned,"
								+ " with no clue as to who its original owner might be."
						+ " You suppose that there'd be no harm in taking it for yourself..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Take", "Add the " + Encounter.getRandomItem().getName() + " to your inventory.", Main.game.getDefaultDialogueNoEncounter()){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem(Encounter.getRandomItem(), true, true));
					}
				};
				
			} else if (index == 2) {
				return new Response("Leave", "Leave the " + Encounter.getRandomItem().getName() + " on the floor.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld DOMINION_STREET_FIND_HAPPINESS = new DialogueNodeOld("Finding Happiness", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				return "<p>"
							+ "Making your way down the wide, deserted streets of Dominion, you make sure to keep a look-out for any sign of danger."
							+ " The bright flash of purple-hued arcane lightning occasionally illuminates your surroundings, and it's just as one of these happens to strike that you spot something strange up ahead."
						+ "</p>"
						+ "<p>"
							+ "About [unit.lSizes(5000)] in front of you, standing alone in the middle of the road, is a small, silver-furred fox."
							+ " It appears to have spotted you at the exact same moment that you saw it, and, raising its head, it turns to face in your direction."
						+ "</p>"
						+ "<p>"
							+ "For a moment, the two of you remain motionless as you size one another up."
							+ " It doesn't look as though the arcane storm is having any effect on the small animal, and, given the nature of this world, you can't help but wonder if it possesses some sort of heightened intellect, or arcane power."
						+ "</p>"
						+ "<p>"
							+ "You don't have much time to ponder this matter further, however, as the fox suddenly starts trotting towards you."
							+ " It doesn't display any malice or hint of aggression in its stride, and, not one to be intimidated by such a small, cute creature, you stand your ground and wait for it to approach."
						+ "</p>"
						+ "<p>"
							+ "As it gets closer, you see that its eyes are a marvellous shade of bright blue, and, kneeling down to encourage it to approach, you can't help but smile as it walks right up to you and nudges its head into your hands."
							+ " Letting out a little laugh, you playfully stroke and scratch the silver fox's ears, before moving down to ruffle the soft fur around its neck."
							+ " As you do so, you feel that there's a band of soft fabric around the animal's neck, and, tilting your head to take a closer look, you see that the fox is wearing a cute little collar."
							+ " A round, metallic tag is attached to the front, and, tilting it to the light, you see that it reads:"
						+ "</p>"
						+ "<p style='text-align:center'>"
							+ "<i>Happiness;<br/>Please take good care of me!</i>"
						+ "</p>"
						+ "<p>"
							+ "After a little while of stroking and playing with the silver fox, you stand back up and get ready to carry on your way."
							+ " Much to your surprise, Happiness instantly moves to follow you, and, as you continue walking down Dominion's empty streets, the fox shows no sign of leaving your side..."
						+ "</p>"
						+ "<p style='text-align:center'>"
							+ "<i style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>You've found Happiness!</i>"
						+ "</p>";
			
			} else {
				return "<p>"
						+ "Making your way down the bustling, ever-busy streets of Dominion, you make sure to keep a look-out for anything of interest that may cross your path."
						+ " Weaving your way through the throngs of shoppers and passersby, you suddenly spot something strange through a gap in the crowds up ahead."
					+ "</p>"
					+ "<p>"
						+ "About [unit.lSizes(5000)] in front of you, standing alone in the middle of the road, is a small, silver-furred fox."
						+ " It appears to have spotted you at the exact same moment that you saw it, and, raising its head, it turns to face in your direction."
					+ "</p>"
					+ "<p>"
						+ "For a moment, the two of you remain motionless as you size one another up."
						+ " It doesn't look as though the small animal is in any way perturbed by the people hurrying all around it, and,"
							+ " given the nature of this world, you can't help but wonder if it possesses some sort of heightened intellect, or arcane power."
					+ "</p>"
					+ "<p>"
						+ "You don't have much time to ponder this matter, however, as the fox suddenly starts trotting towards you."
						+ " It doesn't display any malice or hint of aggression in its stride, and, not one to be intimidated by such a small, cute creature, you stand your ground and wait for it to approach."
					+ "</p>"
					+ "<p>"
						+ "As it gets closer, you see that its eyes are a marvellous shade of bright blue, and, kneeling down to encourage it to approach, you can't help but smile as it walks right up to you and nudges its head into your hands."
						+ " Letting out a little laugh, you playfully stroke and scratch the silver fox's ears, before moving down to ruffle the soft fur around its neck."
						+ " As you do so, you feel that there's a band of soft fabric around the animal's neck, and, tilting your head to take a closer look, you see that the fox is wearing a cute little collar."
						+ " A round, metallic tag is attached to the front, and, tilting it to the light, you see that it reads:"
					+ "</p>"
					+ "<p style='text-align:center'>"
						+ "<i>Happiness;<br/>Please take good care of me!</i>"
					+ "</p>"
					+ "<p>"
						+ "After a little while of stroking and playing with the silver fox, you stand back up and get ready to carry on your way."
						+ " Much to your surprise, Happiness instantly moves to follow you, and, as you continue walking down Dominion's bustling streets, the fox shows no sign of leaving your side..."
					+ "</p>"
					+ "<p style='text-align:center'>"
						+ "<i style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>You've found Happiness!</i>"
					+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue on your way.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};

}
