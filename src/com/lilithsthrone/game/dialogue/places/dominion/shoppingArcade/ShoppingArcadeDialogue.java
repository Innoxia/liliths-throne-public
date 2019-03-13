package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public class ShoppingArcadeDialogue {

	
	public static final DialogueNode OUTSIDE = new DialogueNode("Shopping arcade", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				return "<p>"
							+ "The fear of falling prey to the ongoing arcane storm's effects has caused the vast majority of Dominion's residents to take shelter."
							+ " As a result, the streets that you find yourself walking down are all eerily quiet, with only the occasional demon to be seen wandering about."
						+ "</p>"
						+ "<p>"
							+ "Travelling further up the road, you notice that there a curious amount of demons walking around in this area, and, what's more, you notice that most of them are carrying shopping bags of some sort or another."
							+ " The cause of this unusual gathering is soon made apparent, as up ahead you see a huge shopping centre, with yet more demons loitering around the ornate entranceway."
						+ "</p>"
						+ "<p>"
							+ "As you approach, you take a good look at the massive building, noting that its style is very reminiscent of that of a grand Victorian shopping arcade."
							+ " From what you can see through the entrance's glass doors, it looks as though the shops inside are all still open for business, despite the storm currently raging overhead."
							+ " You wonder if you should take this opportunity to go and do some shopping..."
						+ "</p>";
				
			} else {
				return "<p>"
						+ "The streets in this part of Dominion seem to be busier than usual, and you notice that many of the people surrounding you are holding shopping bags of all shapes and sizes."
						+ " The cause of this unusual increase in traffic is soon made apparent, as up ahead you see a huge shopping centre, with yet more people loitering around the ornate entranceway."
					+ "</p>"
					+ "<p>"
						+ "As you approach, you take a good look at the massive building, noting that its style is very reminiscent of that of a grand Victorian shopping arcade."
						+ " From what you can see through the entrance's glass doors, it looks as though the shops inside are all open for business, and you wonder if you should take this opportunity to go and do some shopping..."
					+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Enter", "Step through the entrance and enter the shopping arcade."){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ENTRANCE, true);
					}
				};

			} else {
				return null;
			}
		}
		
	};
	
	public static final DialogueNode ENTRY = new DialogueNode("Entrance to the arcade", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You find yourself standing in the entrance to the shopping arcade."
						+ " A wide, marble-floored walkway stretches out before you, and on either side, huge glass windows proudly display a wide variety of different goods for sale."
						+ " Looking up to the two-story-high ceiling, you see that the entire arcade is enclosed by a long, glass archway,"
							+ " offering protection not only from the wind and rain, but also shielding anyone below from the effects of an arcane storm."
					+ "</p>"
					+ "<p>"
						+ "Busy crowds of shoppers jostle amongst each other as they travel between the many shops found within the arcade."
							+ " Their chatter reverberates off the windows and walls on either side to create a loud, energetic atmosphere all around you."
					+ "</p>"
					+ "<p>"
						+ "There are several touristy-looking information boards placed nearby, and, after consulting one, you feel confident that you could easily find your way to any of the more interesting stores."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Exit", "Leave the Shopping Arcade."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.DOMINION), PlaceType.DOMINION_SHOPPING_ARCADE, true);
					}
				};

			} else if (index == 6) {
				return new ResponseEffectsOnly("Ralph's Snacks", "Fast travel to Ralph's Snacks."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_RALPHS_SHOP, true);
					}
				};

			} else if (index == 7) {
				return new ResponseEffectsOnly("Nyan's Clothing Emporium", "Fast travel to Nyan's Clothing Emporium."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_NYANS_SHOP, true);
					}
				};

			} else if (index == 8) {
				return new ResponseEffectsOnly("Arcane Arts", "Fast travel to Arcane Arts."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_VICKYS_SHOP, true);
					}
				};

			} else if (index == 9) {
				return new ResponseEffectsOnly("Succubi's Secrets", "Fast travel to Succubi's Secrets."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_KATES_SHOP, true);
					}
				};

			} else if (index == 10) {
				return new ResponseEffectsOnly("Pix's Playground", "Fast travel to the gym, 'Pix's Playground'."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_PIXS_GYM, true);
					}
				};

			} else if (index == 11) {
				return new ResponseEffectsOnly("Dream Lover", "Fast travel to Dream Lover."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP, true);
					}
				};

			} else if (index == 12) {
				return new ResponseEffectsOnly("Servants' Hall", "Fast travel to the Servants' Hall."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_SERVANTS_HALL, true);
					}
				};	
			} else {
				return null;
			}
		}
		
	};
	
	public static final DialogueNode ARCADE = new DialogueNode("Shopping arcade", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "You walk down the wide, marble-floored walkway which loops around and through the shopping arcade."
					+ " On either side of you, huge glass windows proudly display a wide variety of different goods for sale."
					+ " Glancing up at the two-story-high ceiling, you see that the entire arcade is enclosed by a long, glass archway,"
						+ " offering protection not only from the wind and rain, but also shielding anyone below from the effects of an arcane storm."
				+ "</p>"
				+ "<p>"
					+ "Busy throngs of the arcade's patrons jostle to and fro, and you often find yourself having to push your way through idling crowds that inexplicably gather right in the middle of your path."
					+ " Their chatter reverberates off the windows and walls on either side of you to create a loud, energetic atmosphere all the way through the arcade."
				+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
		
	};
	
	private static String[] boringItems = new String[]{
			"an egg-whisk",
			"a wooden spoon",
			"a mop and bucket",
			"a bedside lamp",
			"a set of silver cutlery",
			"an ironing board",
			"a little heart-shaped plushie",
			"a cheese-board",
			"a little book of cheat-codes for some obscene text-based RPG",
			"a fruit bowl"};
	public static final DialogueNode GENERIC_SHOP = new DialogueNode("Shop", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Most of the shops in the arcade, like the one you find yourself entering, are relatively uninteresting, and you can't imagine yourself ever having a use for any of the mundane items that are for sale."
						+ " Despite an enthusiastic attempt from one of the shop's attendants to get you to buy "+boringItems[Util.random.nextInt(boringItems.length)]
								+", you leave the premises empty-handed, wondering why you even bothered to take a look inside..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
		
	};
}
