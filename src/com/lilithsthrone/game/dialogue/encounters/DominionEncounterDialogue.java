package com.lilithsthrone.game.dialogue.encounters;

import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.GenericDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;
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
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Take", "Add the " + Encounter.getRandomItem().getName() + " to your inventory.", GenericDialogue.getDefaultDialogueNoEncounter()){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem(Encounter.getRandomItem(), true));
					}
				};
				
			} else if (index == 2) {
				return new Response("Leave", "Leave the " + Encounter.getRandomItem().getName() + " on the floor.", GenericDialogue.getDefaultDialogueNoEncounter());
				
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
					+ " There's no way to find out who the package was originally intended for, so, not wanting it to go to waste, you gingerly peel back the brown paper to reveal:" + "</p>" + "<p style='text-align:center;'>" + "<b>"
					+ Util.capitaliseSentence(Encounter.getRandomClothing().getClothingType().getDeterminer()) + " " + Encounter.getRandomClothing().getDisplayName(true) + "</b>" + "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Take", "Add the " + Encounter.getRandomClothing().getName() + " to your inventory.", GenericDialogue.getDefaultDialogueNoEncounter()){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addClothing(Encounter.getRandomClothing(), true));
					}
				};
				
			} else if (index == 2) {
				return new Response("Leave", "Leave the " + Encounter.getRandomClothing().getName() + " on the floor.", GenericDialogue.getDefaultDialogueNoEncounter());
				
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
					+ Util.capitaliseSentence(Encounter.getRandomWeapon().getWeaponType().getDeterminer()) + " " + Encounter.getRandomWeapon().getDisplayName(true) + "</b>" + "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Take", "Add the " + Encounter.getRandomWeapon().getName() + " to your inventory.", GenericDialogue.getDefaultDialogueNoEncounter()){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addWeapon(Encounter.getRandomWeapon(), true));
					}
				};
				
			} else if (index == 2) {
				return new Response("Leave", "Leave the " + Encounter.getRandomWeapon().getName() + " on the floor.", GenericDialogue.getDefaultDialogueNoEncounter());
				
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
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Take", "Add the " + Encounter.getRandomItem().getName() + " to your inventory.", GenericDialogue.getDefaultDialogueNoEncounter()){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem(Encounter.getRandomItem(), true));
					}
				};
				
			} else if (index == 2) {
				return new Response("Leave", "Leave the " + Encounter.getRandomItem().getName() + " on the floor.", GenericDialogue.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};

}
