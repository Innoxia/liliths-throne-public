package com.base.game.dialogue.places.dominion.shoppingArcade;

import com.base.game.Weather;
import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.effects.Fetish;
import com.base.game.character.npc.dominion.Nyan;
import com.base.game.character.npc.dominion.Ralph;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.dialogue.responses.ResponseSex;
import com.base.game.dialogue.responses.ResponseTrade;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.inventory.clothing.DisplacementType;
import com.base.game.sex.managers.dominion.SexManagerRalphDiscount;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;
import com.base.world.WorldType;
import com.base.world.places.ShoppingArcade;

/**
 * @since 0.1.0
 * @version 0.1.82
 * @author Innoxia
 */
public class ShoppingArcadeDialogue {
	private static StringBuilder descriptionSB = new StringBuilder();

	
	public static final DialogueNodeOld OUTSIDE = new DialogueNodeOld("Shopping arcade", "-", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
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
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Enter", "Step through the entrance and enter the shopping arcade."){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(true);
					}
				};

			} else {
				return null;
			}
		}
		
	};
	
	public static final DialogueNodeOld ENTRY = new DialogueNodeOld("Entrance to the arcade", "-", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
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
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Exit", "Leave the Shopping Arcade."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(true);
					}
				};

			} if (index == 6) {
				return new ResponseEffectsOnly("Ralph's Snacks", "Fast travel to Ralph's Snacks."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), ShoppingArcade.RALPHS_SHOP_ITEMS, true);
					}
				};

			} if (index == 7) {
				return new ResponseEffectsOnly("Nyan's Clothing Emporium", "Fast travel to Nyan's Clothing Emporium."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), ShoppingArcade.NYANS_SHOP_CLOTHING, true);
					}
				};

			} if (index == 8) {
				return new ResponseEffectsOnly("Arcane Arts", "Fast travel to Arcane Arts."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), ShoppingArcade.VICKYS_SHOP_WEAPONS, true);
					}
				};

			} if (index == 9) {
				return new ResponseEffectsOnly("Succubi's Secrets", "Fast travel to Succubi's Secrets."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), ShoppingArcade.KATES_SHOP_BEAUTY, true);
					}
				};

			} if (index == 10) {
				return new ResponseEffectsOnly("City Gym", "Fast travel to the City Gym."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), ShoppingArcade.PIXS_GYM, true);
					}
				};

			} else {
				return null;
			}
		}
		
	};
	
	//TODO
	public static final DialogueNodeOld ARCADE = new DialogueNodeOld("Shopping arcade", "-", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
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
					+ " Their chatter reverberates off the windows and walls on either side of you to fill the arcade with a loud, energetic atmosphere."
				+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
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
	public static final DialogueNodeOld GENERIC_SHOP = new DialogueNodeOld("Shop", "-", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
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
		public Response getResponse(int index) {
			return null;
		}
		
	};

	public static final DialogueNodeOld SHOP_CONSUMABLES = new DialogueNodeOld("Ralph's Snacks", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			
			if(!Main.game.getDialogueFlags().ralphIntroduced)
				return "<p>"
							+ "You make your way over to the only place in the shopping arcade that sells food to go."
							+ " From the outside, it looks like an old-fashioned sweet shop, with large glass windows displaying all manner of exotic-looking food and drink."
							+ " The words 'Ralph's Snacks' are painted in gold cursive letters above the entrance, and as you push the door open and step inside, a little bell rings to announce your arrival."
						+ "</p>"
						+ "<p>"
							+ UtilText.parseSpeech("Hey there! If you need any help, just ask!", Main.game.getRalph()) + " a lesser horse-boy shouts to you from behind the counter."
						+ "</p>"
						+ "<p>"
							+ "You thank the horse-boy, who you assume to be Ralph, and start to have a look around his shop."
							+ " Most of the goods aren't anything special, and are just the typical sorts of food and drink that you could pick up almost anywhere."
							+ " What sets this shop apart, however, is a special display of arcane-imbued consumables."
							+ " The prices aren't listed, and instead, a little label reads 'Please ask Ralph for assistance with these items'."
						+ "</p>";
			else{
				descriptionSB = new StringBuilder();
				
				descriptionSB.append("<p>"
							+ "You make your way over, once again, to the only place in the shopping arcade that sells food to go."
							+ " From the outside, it looks like an old-fashioned sweet shop, with large glass windows displaying all manner of exotic-looking food and drink."
							+ " The words 'Ralph's Snacks' are painted in gold cursive letters above the entrance, and as you push the door open and step inside, a little bell rings to announce your arrival."
						+ "</p>"
						+ "<p>"
						+ (Main.game.getDialogueFlags().ralphDiscountStartTime>0
								?"As you enter the shop, Ralph winks at you and calls out from behind the very-familiar counter, "
								+UtilText.parseSpeech("Ah, well if it isn't my favourite regular! If you need any help, you know how to ask!", Main.game.getRalph())
								:UtilText.parseSpeech("Hello again! If you need any help, just ask!", Main.game.getRalph()) + " the familiar lesser horse-boy shouts to you from behind the counter.")
						+ "</p>"
						+ "<p>"
							+ "You thank Ralph, and start to have a look around his shop."
							+ " Most of the goods aren't anything special, and are just the typical sorts of food and drink that you could pick up anywhere."
							+ " What sets this shop apart, however, is a special display of arcane-imbued consumables."
							+ " The prices aren't listed, and instead, a little label reads 'Please ask Ralph for assistance with these items'."
						+ "</p>");
					
				if(((Ralph)Main.game.getRalph()).isDiscountActive()){
					descriptionSB.append("<p>"
							+ "<b>Ralph is giving you a</b> <b style='color:" + Colour.GENERIC_GOOD + ";'>"+Main.game.getDialogueFlags().ralphDiscount+"%</b> <b>discount!</b>"
								+ "</p>");
				}
				
				return descriptionSB.toString();
				
			}
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseTrade("Trade with Ralph", "Go and ask Ralph about the special consumables on display.", Main.game.getRalph()){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().ralphIntroduced=true;
						// if 3 days have passed, reset discount:
						if((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().ralphDiscountStartTime) >= (60*24*3)){
							Main.game.getDialogueFlags().ralphDiscount=0;
							Main.game.getRalph().setSellModifier(1.5f);
						}
					}
				};
				
			} else if (index == 2) {
				return new Response("Discount", "Ask Ralph if there's anything you can do to get a discount.", SHOP_CONSUMABLES_DISCOUNT){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().ralphIntroduced=true;
						// if 3 days have passed, reset discount:
						if((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().ralphDiscountStartTime) >= (60*24*3)){
							Main.game.getDialogueFlags().ralphDiscount=0;
							Main.game.getRalph().setSellModifier(1.5f);
						}
					}
				};

			} else if (index == 0) {
				return new Response("Leave", "Leave Ralph's shop.", ARCADE){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().ralphIntroduced=true;
						// if 3 days have passed, reset discount:
						if((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().ralphDiscountStartTime) >= (60*24*3)){
							Main.game.getDialogueFlags().ralphDiscount=0;
							Main.game.getRalph().setSellModifier(1.5f);
						}
					}
				};

			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld SHOP_CONSUMABLES_DISCOUNT = new DialogueNodeOld("Ralph's Snacks", "-", true, true) {
		private static final long serialVersionUID = 1L;


		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().ralphDiscountStartTime>0){
				return "<p>"
						+"Glancing over to the other side of the shop, you see Ralph giving you a cheerful wave from behind the counter."
						+ " Images of his massive equine cock flash across your mind, and you let out a tiny moan as you remember it sliding deep down your throat..."
						+ " His wide, flared head ramming its way past your lips... And the taste of his salty precum as saliva drools from your mouth..."
					+ "</p>"
					+ "<p>"
						+"A familiar voice suddenly snaps you out of your daydream, "
						+ UtilText.parseSpeech("Hey, are you alright?", Main.game.getRalph())
					+ "</p>"
					+ "<p>"
					 	+ "You were so engrossed in your fantasy that you didn't notice Ralph walking up to you, and you're taken completely off-guard as you blurt out a reply, " 
						+ UtilText.parsePlayerSpeech("Oh! Yeah... Thanks! I was just thinking... Erm...")
					+ "</p>"
					+ "<p>"
						+ "You notice that his eyes have started to roam up and down your body, and you get the feeling that he knows exactly what you were imagining."
						+ " Glancing down, you see a distinctive bulge forming between his legs."
						+ " As you struggle to contain a happy gasp at the sight of his clear erection, he steps forwards, and you find yourself with your back to the wall as you feel his hot breath on your face once again." 
					+ "</p>"
					+ "<p>"
						+ UtilText.parseSpeech("Want to have another taste?", Main.game.getRalph()) + " he asks, grinning deviously, "
						+ UtilText.parseSpeech("Let me blow a load down that pretty little throat of yours, and I'll give you twenty-five percent off everything I have in stock for a few days.", Main.game.getRalph())
					+ "</p>"
					+ "<p>"
						+ "As he moves even closer, you struggle to get the image of his massive, throbbing black horse-cock out of your mind, and as the tent in his trousers comes into contact with your leg, you blurt out your reply."
					+ "</p>";
			}else{
			
				return "<p>"
							+ "You get the feeling that some of these items may be out of your price range."
							+ " Glancing over to the other side of the shop, you see Ralph giving you a cheerful wave from behind the counter."
							+ " Encouraged by his disarming smile and friendly face, you decide to ask him if there's any way you can get a discount on some of these transformative items, and start to walk over to him."
						+ "</p>"
						+ "<p>"
							+ "As he sees you approaching, Ralph calls out to you, " + UtilText.parseSpeech("Hello! Is there anything I can help you with?", Main.game.getRalph())
						+ "</p>"
						+ "<p>"
							+ UtilText.parsePlayerSpeech("Hi there, I was wondering if you could show me the prices of some of these items,") + " you reply." 
						+ "</p>"
						+ "<p>"
							+ "Ralph cheerfully leads you back over to the special display, and as he informs you of some of the prices, your suspicions prove to be correct."
							+ " While some of the items are a reasonable price, the bottles of various transformative liquids are quite expensive."
							+ " Looking across at the friendly shopkeeper, you ask him if there's any way he'd drop the prices a little."
						+ "</p>"
						+ "<p>"
							+ UtilText.parseSpeech("Hmm,", Main.game.getRalph()) + " he says, glancing over at you, "
							+ UtilText.parseSpeech("I suppose we could work something out...", Main.game.getRalph())
						+ "</p>"
						+ "<p>"
							+ "You notice that his smile has suddenly lost its friendly appearance, and as his eyes roam up and down your body, you get the feeling that your arcane aura is giving him some dirty thoughts."
							+ " Glancing down, you see a distinctive bulge forming between his legs."
							+ " As your eyes open wide at the sight of his clear erection, he steps forwards, and you find yourself with your back to the wall as you feel his hot breath on your face." 
						+ "</p>"
						+ "<p>"
							+ UtilText.parseSpeech("Like what you see?", Main.game.getRalph()) + " he asks, grinning deviously, "
							+ UtilText.parseSpeech("Let me blow a load down that pretty little throat of yours, and I'll give you twenty-five percent off everything I have in stock for a few days.", Main.game.getRalph())
						+ "</p>"
						+ "<p>"
							+ "You gulp as he moves even closer, and as the tent in his trousers comes into contact with your leg, you blurt out your reply."
						+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseSex("Agree", "Agree to do as Ralph says and suck his cock.", SHOP_CONSUMABLES_DISCOUNT,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL)), null, CorruptionLevel.TWO_HORNY,
						null, null, null,
						Main.game.getRalph(), new SexManagerRalphDiscount(), Ralph.AFTER_SEX, ""){
					@Override
					public void effects() {
						Main.game.getRalph().getPlayerKnowsAreasMap().put(CoverableArea.PENIS, true);
						Main.game.getRalph().isAbleToBeDisplaced(Main.game.getRalph().getHighestZLayerCoverableArea(CoverableArea.PENIS),
								DisplacementType.PULLS_DOWN, true, true, Main.game.getRalph());
						Main.game.getRalph().isAbleToBeDisplaced(Main.game.getRalph().getHighestZLayerCoverableArea(CoverableArea.PENIS),
								DisplacementType.SHIFTS_ASIDE, true, true, Main.game.getRalph());
					}
				};
				
			} else if (index == 2) {
				return new Response("Refuse", "Tell him that you're not willing to do that.", SHOP_CONSUMABLES_DISCOUNT_REFUSE);

			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld SHOP_CONSUMABLES_DISCOUNT_REFUSE = new DialogueNodeOld("Ralph's Snacks", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().ralphDiscountStartTime>0){
				return "<p>"
						+ UtilText.parsePlayerSpeech("No thanks,") + " you force yourself to say, stepping to one side and moving away from the horny shopkeeper."
					+ "</p>"
					+ "<p>"
						+ UtilText.parseSpeech("Well, if you get a craving, I'm always ready to satisfy it!", Main.game.getRalph()) + " he laughs, turning around and heading back to his counter, "
						+ UtilText.parseSpeech("If you need anything else, just let me know.", Main.game.getRalph()) 
					+ "</p>"
					+ "<p>"
						+ "You go back to browsing the shop's wares, the images of Ralph's massive black horse-cock refusing to budge from your head."
					+ "</p>";
				
			}else{
				return "<p>"
							+ UtilText.parsePlayerSpeech("No thanks,") + " you say, stepping to one side and moving away from the horny shopkeeper."
						+ "</p>"
						+ "<p>"
							+ UtilText.parseSpeech("Well, no harm in either of us asking!", Main.game.getRalph()) + " he laughs, turning around and heading back to his counter, "
							+ UtilText.parseSpeech("If you need anything else, just let me know.", Main.game.getRalph()) 
						+ "</p>"
						+ "<p>"
							+ "You go back to browsing the shop's wares, but you find it hard to shake the image of Ralph's massive bulge from your head."
						+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseTrade("Trade with Ralph", "Go and ask Ralph about the special transformative consumables on display.", Main.game.getRalph()){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().ralphIntroduced=true;
						// if 3 days have passed, reset discount:
						if((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().ralphDiscountStartTime) >= (60*24*3)){
							Main.game.getDialogueFlags().ralphDiscount=0;
							Main.game.getRalph().setSellModifier(1.5f);
						}
					}
				};

			} else if (index == 2) {
				return new Response("Discount", "Ask Ralph if there's anything you can do to get a discount.", SHOP_CONSUMABLES_DISCOUNT){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().ralphIntroduced=true;
						// if 3 days have passed, reset discount:
						if((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().ralphDiscountStartTime) >= (60*24*3)){
							Main.game.getDialogueFlags().ralphDiscount=0;
							Main.game.getRalph().setSellModifier(1.5f);
						}
					}
				};

			} else if (index == 0) {
				return new Response("Leave", "Head back outside to the shopping arcade.", ENTRY){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().ralphIntroduced=true;
						// if 3 days have passed, reset discount:
						if((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().ralphDiscountStartTime) >= (60*24*3)){
							Main.game.getDialogueFlags().ralphDiscount=0;
							Main.game.getRalph().setSellModifier(1.5f);
						}
					}
				};

			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNodeOld SHOP_CLOTHING = new DialogueNodeOld("Nyan's Clothing Emporium", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Most of the clothing stores in the shopping arcade are small, one-story affairs, with a limited selection of goods on offer."
						+ " The establishment before you appears to be the exception to this rule, and takes the form of a huge, two-story clothing department bearing the sign 'Nyan's Clothing Emporium'."
					+ "</p>"
					+ "<p>"
						+ "Stepping through the open glass doors, you find yourself in a large, modern-looking clothing store."
						+ " As you walk through the building, you discover that not only is this the largest shop in the arcade, but that it's also the busiest."
						+ " Crowds of shoppers block many of the aisles that you'd like to walk down, and every member of staff that you see is busily occupied with other customers."
					+ "</p>"
					+ "<p>"
						+ "Deciding that it might be best to check out one of the smaller, quieter clothing stores, you turn around and start to head for the exit."
						+ " As you do so, you hear a nervous voice calling out from behind you, "
						+ "[nyan.speech(E-erm, excuse me? C-can I help you?)]"
					+ "</p>"
					+ "<p>"
						+ "Turning once more, this time in the direction that the timid voice came from, you find yourself looking at a lesser cat-girl."
						+ " As you look into her bright blue eyes, her cheeks instantly turn a deep shade of red, and she glances down at the floor and starts shuffling her feet, "
						+ "[nyan.speech(H-hello... T-there's nobody else available, you see, s-so I thought maybe I could help. Oh, erm, I-I'm Nyan... I probably should have said that first... W-welcome to my store...)]"
					+ "</p>"
					+ "<p>"
						+ "If it wasn't for the fact that she's wearing a staff uniform, and has a little name tag pinned to her blouse reading 'Nyan, Store Manager', you're not sure if you would have believed that she's the store's owner."
						+ " In an attempt to put the anxious cat-girl at ease, you ignore the fact that she's visibly trembling and explain your situation to her, "
						+ "[pc.speech(Pleased to meet you Nyan, it's a nice place you've got here! I was just having a little difficulty finding anything in amongst all these crowds...)]"
					+ "</p>"
					+ "<p>"
						+ "[nyan.speech(O-oh, sorry! E-everything we have on display can also b-be found back in our stockroom. F-follow me and I'll show you!)]"
					+ "</p>"
					+ "<p>"
						+ "In what appears to be an almost desperate attempt to escape further conversation, Nyan hurries past you and heads off towards a door bearing the sign 'Staff Only'."
						+ " Doing as she instructed, you follow her through the door into the store's stockroom."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Stockroom", "Nyan has shown you into the stockroom.", SHOP_CLOTHING_STOCK_ROOM) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().nyanIntroduced=true;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SHOP_CLOTHING_STOCK_ROOM = new DialogueNodeOld("Nyan's Clothing Emporium", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "The stockroom of Nyan's Clothing Emporium looks pretty much exactly as you'd expected it be."
						+ " Ceiling-height shelving units are stocked full of clothes, shoes, and accessories of all different sorts, and there's a distinctive 'new clothing' smell lingering in the air."
					+ "</p>"
					+ "<p>"
						+ "Nyan's heels clatter across the laminate flooring as she hurries to retrieve a notepad that she uses to track what's currently in stock."
						+ " Rushing back over to you, she fumbles with the little pad and almost drops it on the floor, "
						+ "[nyan.speech(A-ah! S-sorry, I don't usually interact with customers, s-so sorry if I'm a little unprofessional... A-Anyway, let me know what you'd like t-to see, and I'll show y-you what I've got in stock!)]"
					+ "</p>"
					+ "<p>"
						+ "You start to thank the nervous cat-girl, but she suddenly squeaks out and cuts you off, "
						+ "[nyan.speech(O-oh! A-and I forgot! I-I was going to do a t-trial of offering to buy old clothes from customers, s-so if you wanted to, I-I could buy some old clothing from you..."
							+ " O-oh, I interrupted you... S-sorry...)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(index);
		}
	};
	
	public static final DialogueNodeOld SHOP_CLOTHING_REPEAT = new DialogueNodeOld("Nyan's Clothing Emporium", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Heading back over to 'Nyan's Clothing Emporium', you find it just as busy as it always is."
						+ " Crowds of shoppers block almost every aisle, and if it wasn't for Nyan's offer to personally show you around her stockroom, you don't think you'd ever manage to get any shopping done in here."
					+ "</p>"
					+ "<p>"
						+ "Scanning around for any sign of the store's nervous owner, you suddenly hear a familiar squeak coming from behind you, "
						+ "[nyan.speech(A-ah! H-hello again!)]"
					+ "</p>"
					+ "<p>"
						+ "You turn around to see Nyan looking down at her feet as she shuffles them about on the shop's laminate flooring, "
						+ "[pc.speech(Hi Nyan, how are y-)]"
					+ "</p>"
					+ "<p>"
						+ "[nyan.speech(I-I'm fine! T-thanks for asking,)] Nyan bursts out, interrupting you in her haste to answer, "
						+ "[nyan.speech(I-I can show you the stockroom again if y-you'd like! F-follow me!)]"
					+ "</p>"
					+ "<p>"
						+ "Following Nyan into her store's stockroom once again, you wonder what to take a look at this time..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseTrade("Female Clothing", "Call over the cat-girl and ask her what female clothing is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonFemaleClothing())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 2) {
				return new ResponseTrade("Female Lingerie", "Call over the cat-girl and ask her what female lingerie is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonFemaleLingerie())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 3) {
				return new ResponseTrade("Female Accessories", "Call over the cat-girl and ask her what female accessories are available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonFemaleAccessories())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 4) {
				return new ResponseTrade("Male Clothing", "Call over the cat-girl and ask her what male clothing is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonMaleClothing())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 5) {
				return new ResponseTrade("Male Underwear", "Call over the cat-girl and ask her what male underwear is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonMaleLingerie())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 6) {
				return new ResponseTrade("Male Accessories", "Call over the cat-girl and ask her what male accessories are is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonMaleAccessories())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 7) {
				return new ResponseTrade("Unisex Clothing", "Call over the cat-girl and ask her what unisex clothing is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonAndrogynousClothing())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 8) {
				return new ResponseTrade("Unisex Underwear", "Call over the cat-girl and ask her what unisex underwear is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonAndrogynousLingerie())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 9) {
				return new ResponseTrade("Unisex Accessories", "Call over the cat-girl and ask her what unisex accessories are is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonAndrogynousAccessories())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 0) {
				return new Response("Leave", "Tell Nyan that you've got to get going.", SHOP_CLOTHING_EXIT);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SHOP_CLOTHING_EXIT = new DialogueNodeOld("Nyan's Clothing Emporium", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Thanking Nyan for her time, you explain to her that you need to get going now."
						+ " Hurrying to put her little notepad away, she stutters, "
						+ "[nyan.speech(W-well, thanks for choosing to s-shop here! If you ever want t-to come back, just find me and I-I'll show you what's in stock back here!)]"
					+ "</p>"
					+ "<p>"
						+ "Before you can respond, Nyan turns around and runs off deeper into the stockroom."
						+ " You're pretty sure that there aren't any other exits in that direction, and that she was just unable to continue the conversation any longer."
					+ "</p>"
					+ "<p>"
						+ "You step out of the stockroom, and, after pushing your way through a crowd of customers that's loitering around the store's exit, you head back out into the shopping arcade."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			return null;
		}
	};
	
	
	public static final DialogueNodeOld SHOP_WEAPONS = new DialogueNodeOld("Arcane Arts", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "'Arcane Arts' is situated in the quieter end of the shopping arcade."
						+ " Pushing open the door, your arrival is announced by the sound of a little bell ringing above you."
						+ " There's only one other person inside; a greater wolf-girl, who's standing behind the shop's counter."
					+ "</p>"
					+ "<p>"
						+ "As the door swings shut behind you, the wolf-girl's yellow eyes lock onto yours, and her mouth turns up into an unsettling predatory grin."
						+ " Even though she's only a few metres away, and you're the only person in the shop, she doesn't utter a single word of greeting, and you find that the shop's atmosphere has quickly become extremely oppressive."
						+ " You glance around, seeing that there are only odd trinkets and pieces of 'enchanted' junk littering the messy shelves."
					+ "</p>"
					+ "<p>"
						+ "You start to back off towards the door, but as you do, the wolf-girl finally speaks, "
						+ UtilText.parseSpeech("If you aren't here to waste my time, I keep the good stuff under the counter here. I'm Vicky by the way, welcome to my shop...", Main.game.getVicky())
					+ "</p>"
					+ "<p>"
						+ "You look across to the wolf-girl to see that she's beckoning you over to her."
						+ " The toothy grin on her wolf-like face is extremely worrying, but surely she isn't going to attack a customer..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseTrade("Trade with Vicky", "Walk over to the counter and see what crystals and feathers Vicky has in stock.", Main.game.getVicky());
				
			} else if (index == 0) {
				return new Response("Leave", "Leave Arcane Arts and head back out into the arcade.", ARCADE);

			} else {
				return null;
			}
		}
	};
	
	
	
}
