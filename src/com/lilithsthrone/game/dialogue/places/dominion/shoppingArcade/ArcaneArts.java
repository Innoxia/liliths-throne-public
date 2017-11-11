package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.82
 * @version 0.1.82
 * @author Innoxia
 */
public class ArcaneArts {
	
	public static final DialogueNodeOld EXTERIOR = new DialogueNodeOld("Arcane Arts (Exterior)", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You find yourself standing in front of a rather peculiar shop."
						+ " Although there's a sign declaring 'Arcane Arts' to be open, the interior is very dimly-lit, and you notice that most of the Arcade's shoppers are giving this particular store a wide berth."
						+ " Wanting to take a closer look, you walk up to the entrance, and, looking through the glass into the shop's gloomy interior, you see signs of movement behind the counter."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Enter", "Step inside Arcane Arts.", SHOP_WEAPONS);
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Arcade Entrance", "Fast travel to the entrance to the arcade."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_ENTRANCE, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SHOP_WEAPONS = new DialogueNodeOld("Arcane Arts", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Pushing open the door, your arrival is announced by the sound of a little bell ringing above you."
						+ " There's only one other person inside; a greater wolf-girl, who's standing behind the shop's counter."
					+ "</p>"
					+ "<p>"
						+ "As the door swings shut behind you, the wolf-girl's yellow eyes lock onto yours, and her mouth turns up into an unsettling predatory grin."
						+ " Even though she's only a few metres away, and you're the only person in the shop, she doesn't utter a single word of greeting, and you find that the shop's already-gloomy atmosphere has quickly become extremely oppressive."
						+ " You glance around, seeing that there are only odd trinkets and pieces of 'enchanted' junk littering the messy shelves."
					+ "</p>"
					+ "<p>"
						+ "You start to back off towards the door, but as you do, the wolf-girl finally speaks, "
						+ "[vicky.speech(If you aren't here to waste my time, I keep the good stuff under the counter here. I'm Vicky by the way, welcome to my shop...)]"
					+ "</p>"
					+ "<p>"
						+ "You look across to the wolf-girl to see that she's beckoning you over to her."
						+ " The toothy grin on her wolf-like face is extremely worrying, but surely she isn't going to attack a customer..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseTrade("Trade with Vicky", "Walk over to the counter and see what crystals and feathers Vicky has in stock.", Main.game.getVicky());
				
			} else if (index == 0) {
				return new Response("Leave", "Leave Arcane Arts and head back out into the arcade.", EXTERIOR);

			} else {
				return null;
			}
		}
	};
}
