package com.base.game.dialogue.places.dominion.shoppingArcade;

import com.base.game.character.attributes.Attribute;
import com.base.game.character.npc.dominion.Pix;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseSex;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.managers.dominion.SMPixShowerTime;
import com.base.main.Main;
import com.base.utils.Colour;

/**
 * @since 0.1.66
 * @version 0.1.78
 * @author Innoxia
 */
public class SPGym {
	
	private static Response getResponseGym(int index) {
		if (index == 1) {
			if (Main.game.getPlayer().getStaminaPercentage() < 0.4f) {
				return new Response("Cardio", "You are too tired to do any more exercise!", null);
				
			} else {
				return new Response("Cardio", "Use the running and cycling machines to build up your fitness.", GYM_CARDIO){
					@Override
					public void effects(){
						Main.game.getPlayer().incrementStamina(-Main.game.getPlayer().getAttributeValue(Attribute.STAMINA_MAXIMUM) * 0.4f);
						Main.game.getPlayer().incrementAttribute(Attribute.FITNESS, 0.5f);
					}
				};
			}

		} else if (index == 2) {
			if (Main.game.getPlayer().getStaminaPercentage() < 0.4f) {
				return new Response("Weights", "You are too tired to do any more exercise!", null);
				
			} else {
				return new Response("Weights", "Use the free weights and exercise machines to build up your strength.", GYM_WEIGHTS){
					@Override
					public void effects(){
						Main.game.getPlayer().incrementStamina(-Main.game.getPlayer().getAttributeValue(Attribute.STAMINA_MAXIMUM) * 0.4f);
						Main.game.getPlayer().incrementAttribute(Attribute.STRENGTH, 0.5f);
					}
				};
			}

		} else if (index == 3) {
				if(!Main.game.getDialogueFlags().gymIsMember) {
					return new Response("Pix", "Only lifetime members can get personal training from Pix.", null);
					
				} else if (Main.game.getPlayer().getStaminaPercentage() < 0.8f) {
					return new Response("Pix", "You are too tired to do Pix's exhausting workout routine!", null);
					
				} else {
					return new Response("Pix",
							"Pix is hovering close by, bouncing up and down on the spot while glancing your way. She obviously wants you to ask her for a personal training session. Call her over and grant her wish.", GYM_PIX_TRAINING){
						@Override
						public void effects(){

							Main.game.getPlayer().incrementStamina(-Main.game.getPlayer().getAttributeValue(Attribute.STAMINA_MAXIMUM) * 0.8f);
							Main.game.getPlayer().incrementAttribute(Attribute.FITNESS, 0.75f);
							Main.game.getPlayer().incrementAttribute(Attribute.STRENGTH, 0.75f);
						}
					};
				}
				
		} else if (index == 0) {
			return new Response("Leave", "Decide to leave the gym.", ShoppingArcadeDialogue.ARCADE);
			
		} else
			return null;
	}
	

	public static final DialogueNodeOld GYM = new DialogueNodeOld("City gym", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if (!Main.game.getDialogueFlags().gymIntroduced)
				return "<p>"
						+ "You make your way over to where the city gym is located."
						+ " Pushing open the door, you step into the lobby, where you're immediately greeted by an extremely energetic greater dog-girl."
						+ "</p>"
						+ "<p>"
						+ UtilText.parseSpeech("Heya! Hi! How're ya doin'!", Main.game.getPix())
						+ " she exclaims, reaching forwards to give your hand an enthusiastic shake with both of her paw-like hands."
						+ "</p>"
						+ "<p>"
						+ UtilText.parsePlayerSpeech("Hello-")
						+ " you start to say, but the dog-girl quickly starts to speak over you."
						+ "</p>"
						+ "<p>"
						+ UtilText.parseSpeech("Wohoo! So, you've decided to come get fit, huh, huh, huh?", Main.game.getPix())
						+ " she asks, but before you can reply, she continues speaking, "
						+ UtilText.parseSpeech("So don't just stand there! Oh, I'm Pix by the way! Come on, I'll give you a tour!", Main.game.getPix())
						+ "</p>"
						+ "<p>"
						+ "Pix releases your hand, which she's been enthusiastically shaking this entire time, before starting to bound away into the gym, motioning for you to follow her."
						+ " You notice that this entire time, her tail hasn't stopped wagging, and she's quite possibly the most energetic person you've ever met."
						+ "</p>";
			else
				return "<p>"
						+ "You make your way over to where the city gym is located."
						+ " Pushing open the door, you step into the lobby, where you're immediately greeted by Pix, who's looking as energetic as ever."
						+ "</p>"
						+ "<p>"
						+ UtilText.parseSpeech("Heya, great to see you again! How're ya doin'!", Main.game.getPix())
						+ " she exclaims, bounding over to you with a huge grin on her face."
						+ "</p>"
						+ "<p>"
						+ UtilText.parsePlayerSpeech("Hello aga-")
						+ " you start to say, but the hyperactive dog-girl quickly starts to speak over you."
						+ "</p>"
						+ "<p>"
						+ UtilText.parseSpeech("Wohoo! So, you've decided to come have that tour, huh, huh, huh?", Main.game.getPix())
						+ " she asks, but before you can reply, she continues speaking, "
						+ UtilText.parseSpeech("So don't just stand there! Come on, follow me!", Main.game.getPix())
						+ "</p>"
						+ "<p>"
						+ "Pix happily laughs and turns away before starting to bound into the gym, motioning for you to follow her."
						+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Follow", "Follow Pix and let her give you a tour of the gym.", GYM_FOLLOW){
					@Override
					public void effects(){
						Main.game.getDialogueFlags().gymIntroduced=true;
						Main.game.getDialogueFlags().gymHadTour=true;
					}
				};
				
			} else if (index == 0) {
				return new Response("Leave", "Tell Pix that you don't have time right now, but you might be back later.", ShoppingArcadeDialogue.ARCADE){
					@Override
					public void effects(){
						Main.game.getDialogueFlags().gymIntroduced=true;
						Main.game.getTextStartStringBuilder().append("<p>" + "You quickly shout to Pix that you don't have time for her tour right now, but you might come back later."
								+ " She doesn't seem fazed at all by your reluctant response, and calls out after you as you leave, "
								+ UtilText.parseSpeech("No worries! Come back at any time, I'll be waiting to see you again!", Main.game.getPix()) + "</p>");
					}
				};

			} else {
				return null;
			}
		}

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};
	public static final DialogueNodeOld GYM_FOLLOW = new DialogueNodeOld("City gym", "-", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return "<p>" + "You decide to follow the excitable dog-girl, and hurry to catch up with her as she sets off." + " Following Pix through a large glass door, you find yourself standing in the gym proper." + "</p>" + "<p>"
					+ UtilText.parseSpeech("So, like, here's all the weights and machines and stuff!", Main.game.getPix()) + " she shouts, bouncing happily around the room as you follow in her footsteps, "
					+ UtilText.parseSpeech("It's usually not much busier than this, so when you sign up, you'll find that you never have to wait for any of the equipment!", Main.game.getPix()) + "</p>" + "<p>"
					+ "As you're wondering if Pix is even capable of speaking at a normal volume, you look around at your surroundings."
					+ " The gym's main area, which you're currently standing in, is large and spacious, and is filled with all sorts of exercise machines, yoga mats, and free weight sections."
					+ " Despite its impressive size and array of arcane-powered machinery, there doesn't seem to be more than a handful of people in here."
					+ " You wonder if Pix's overbearing hyperactive personality has put people off from signing up." + "</p>" + "<p>" + UtilText.parseSpeech("Follow me! Follow me!", Main.game.getPix())
					+ " she shouts, interrupting your thoughts as she continues her tour." + "</p>" + "<p>" + "Pix continues to show you around the gym, and you're surprised to find that there's an indoor olympic-sized swimming pool in the back."
					+ " Outside, situated behind the shopping promenade, there's a large running track and football field, which Pix happily states are exclusively for use by the gym's members."
					+ " She then shows you some other miscellaneous facilities, such as the changing rooms, showers and lockers."
					+ " Before long, you find yourself stepping back into the gym's lobby, and Pix turns around and beams at you as she finishes her tour." + "</p>" + "<p>"
					+ UtilText.parseSpeech("So, what'ya think?! Pretty awesome, huh?!", Main.game.getPix()) + " she shouts, before moving onto the business-side of things, "
					+ UtilText.parseSpeech("Now, life-time membership is one thousand flames, or you can just pay each time you come here, which is ten flames per entry!"
							+ " So, what'll be?! If you get the life-time membership, that includes personal workout sessions with none other than your new friend, Pix!"
							+ " You know, I don't do this often, but I can see that you're kinda special and I'd really like to get the chance to work with that body of yours, so just for you,"
							+ " I'll say life-time membership is only eight hundred flames! Pretty good, huh? So, what'ya want?!", Main.game.getPix())
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getMoney() < 10)
					return new Response("Single (" + Main.game.getCurrencySymbol() + " 10)", "You don't have enough money!", null);
				else
					return new Response("Single Payment (<span style='color:" + Colour.CURRENCY() + ";'>" + Main.game.getCurrencySymbol() + "</span> 10)",
							"Tell Pix that you'd like to pay for a single entry to the gym. <b>This will cost </b><b style='color:" + Colour.CURRENCY() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>10</b>", GYM_SINGLE_PAYMENT){
					@Override
					public void effects(){
						Main.game.getPlayer().incrementMoney(-10);
					}
				};
				
			} else if (index == 2) {
				if (Main.game.getPlayer().getMoney() < 800)
					return new Response("Membership (" + Main.game.getCurrencySymbol() + " 800)", "You don't have enough money!", null);
				else
					return new Response("Membership (<span style='color:" + Colour.CURRENCY() + ";'>" + Main.game.getCurrencySymbol() + "</span> 800)",
							"Tell Pix that you'd like to sign up for the lifetime membership option. <b>This will cost </b><b style='color:" + Colour.CURRENCY() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>800</b>",
							GYM_LIFETIME_PAYMENT){
					@Override
					public void effects(){
						Main.game.getPlayer().incrementMoney(-800);
						Main.game.getDialogueFlags().gymIsMember=true;
					}
				};
				
			} else if (index == 0) {
				return new Response("Leave", "Tell Pix that you'll think about it and be back later.", ShoppingArcadeDialogue.ARCADE);

			} else {
				return null;
			}
		}

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};
	public static final DialogueNodeOld GYM_RETURNING = new DialogueNodeOld("City gym", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "You make your way over to where the city gym is located."
					+ " Pushing open the door, you step into the lobby, where you're immediately greeted by the familiar bouncing figure of Pix."
					+ "</p>"
					+ "<p>"
					+ UtilText.parseSpeech("Heya "
							+ Main.game.getPlayer().getName()
							+ "! How're ya doin'?!", Main.game.getPix())
					+ " she asks, beaming at you."
					+ "</p>"
					+ "<p>"
					+ "You start to greet the energetic dog-girl, but, once again, she immediately cuts you off, "
					+ (Main.game.getDialogueFlags().gymIsMember
							? UtilText.parseSpeech("You gonna want some personal training today?! I'm ready for it whenever you are! You go ahead and get changed, I'll see you in there!", Main.game.getPix())
							: UtilText.parseSpeech("The eight hundred flames deal is still on y'know! Only for you!"
									+ " So, you ready to sign up?!", Main.game.getPix()))
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (!Main.game.getDialogueFlags().gymIsMember) {
				if (index == 1) {
					if (Main.game.getPlayer().getMoney() < 10)
						return new Response("Single Payment (" + Main.game.getCurrencySymbol() + " 10)", "You don't have enough money!", null);
					else
						return new Response("Single Payment (<span style='color:" + Colour.CURRENCY() + ";'>" + Main.game.getCurrencySymbol() + "</span> 10)",
								"Tell Pix that you'd like to pay for a single entry to the gym. <b>This will cost </b><b style='color:" + Colour.CURRENCY() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>10</b>", GYM_SINGLE_PAYMENT){
						@Override
						public void effects(){
							Main.game.getPlayer().incrementMoney(-10);
						}
					};
					
				} else if (index == 2) {
					if (Main.game.getPlayer().getMoney() < 800)
						return new Response("Membership (" + Main.game.getCurrencySymbol() + " 800)", "You don't have enough money!", null);
					else
						return new Response("Membership (<span style='color:" + Colour.CURRENCY() + ";'>" + Main.game.getCurrencySymbol() + "</span> 800)",
								"Tell Pix that you'd like to sign up for the lifetime membership option. <b>This will cost </b><b style='color:" + Colour.CURRENCY() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>800</b>",
								GYM_LIFETIME_PAYMENT){
						@Override
						public void effects(){
							Main.game.getPlayer().incrementMoney(-800);
							Main.game.getDialogueFlags().gymIsMember=true;
						}
					};
					
				} else if (index == 0) {
					return new Response("Leave", "Tell Pix that you'll think about it and be back later.", ShoppingArcadeDialogue.ARCADE);

				} else {
					return null;
				}

			} else {
				if (index == 1) {
					return new Response("Enter", "Enter the gym and get changed.", GYM_MEMBER_ENTER);
					
				} else if (index == 0) {
					return new Response("Leave", "Tell Pix that you've changed your mind, and that you'll be back another time.", ShoppingArcadeDialogue.ARCADE);
					
				} else {
					return null;
				}
			}
		}

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};
	public static final DialogueNodeOld GYM_SINGLE_PAYMENT = new DialogueNodeOld("City gym", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>" + "You tell Pix that you just want to pay for a single entry today, and, handing over ten flames, she smiles at you and lets you enter." + "</p>" + "<p>"
					+ UtilText.parseSpeech("Remember, if you get a life-time membership, I can help you train!", Main.game.getPix()) + " she shouts after you while bouncing up and down on the spot." + "</p>" + "<p>"
					+ "You head over to the changing rooms, where you discover that the gym provides clean exercise clothes for people who haven't brought any." + " Quickly getting changed, you head out into the gym and decide what to do." + "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			return getResponseGym(index);
		}

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};
	public static final DialogueNodeOld GYM_LIFETIME_PAYMENT = new DialogueNodeOld("City gym", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>" + "You tell Pix that you want to sign up for a lifetime membership." + " A huge smile spreads over her face, and she leaps up, punching the air and letting out a triumphant shout." + "</p>" + "<p>"
					+ UtilText.parseSpeech("Wohoo! That's, like, so amazing! Now I can help you train and stuff, and you can, like, come and go anytime without paying anything extra!", Main.game.getPix())
					+ " she explains, and without warning, she leaps forwards and gives you a hug." + "</p>" + "<p>"
					+ "You're somewhat taken aback by her sudden move, but before you can react, she releases you and runs off to fetch a contract from behind the front desk."
					+ " After grabbing the piece of paper and a pen, she practically sprints back over to you and thrusts them into your hands." + "</p>" + "<p>"
					+ UtilText.parseSpeech("Ok! So, like, sign here, here, and here!", Main.game.getPix())
					+ " she points to different places on the page, and you do as she says before handing over eight hundred flames, completing the contract." + "</p>" + "<p>"
					+ UtilText.parseSpeech("Awesome! So, that's all done! Congratulations! You can go ahead and get changed and stuff, just call me over whenever you're ready for some personal training!", Main.game.getPix())
					+ " she exclaims, before running off to fill in her side of the paperwork." + "</p>" + "<p>" + "You head over to the changing rooms, where you see that the gym provides clean exercise clothes for people who haven't brought any."
					+ " Quickly getting changed, you head out into the gym and decide what to do." + "</p>";
		}

		@Override
		public Response getResponse(int index) {
			return getResponseGym(index);
		}

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};
	public static final DialogueNodeOld GYM_MEMBER_ENTER = new DialogueNodeOld("City gym", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>" + "You make your way over to where the city gym is located." + " Pushing open the door, you step into the lobby, where you're immediately greeted by the familiar bouncing figure of Pix." + "</p>" + "<p>"
					+ UtilText.parseSpeech("Heya " + Main.game.getPlayer().getName() + "! How're ya doin'?! Gonna have a personal session today?!", Main.game.getPix()) + " she asks, beaming at you." + "</p>" + "<p>"
					+ "You start to reply to the energetic dog-girl, but, once again, she immediately cuts you off, "
					+ UtilText.parseSpeech("Y'know, just any time you want it, call me over and we can get started! You go ahead and get changed, I'll see you in there!", Main.game.getPix()) + "</p>" + "<p>"
					+ "You do as Pix says and head over to the changing rooms, where you quickly get changed." + " Heading out into the gym, you decide what to do." + "</p>";
		}

		@Override
		public Response getResponse(int index) {
			return getResponseGym(index);
		}

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};
	public static final DialogueNodeOld GYM_CARDIO = new DialogueNodeOld("City gym", "-", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 60;
		}

		@Override
		public String getContent() {
			return "<p>" + "You head over to the cardio section of the gym and spend some time warming up before using the running and cycling machines." + " After an hour of intense workout, you're left panting and covered in sweat."
					+ " You feel like you've definitely improved your level of fitness, even if only by a tiny amount." + "</p>" + "<p>"
					+ (Main.game.getPlayer().getStaminaPercentage() < 0.4f ? "You feel completely exhausted, and are far too tired to do any more exercise."
							: (Main.game.getDialogueFlags().gymIsMember ? "You feel as though you have enough energy left to do another workout if you wanted to, but you're too tired to do Pix's intense routine."
									: "You feel as though you have enough energy left to do another workout if you wanted to."))
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			return getResponseGym(index);
		}

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};
	public static final DialogueNodeOld GYM_WEIGHTS = new DialogueNodeOld("City gym", "-", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 60;
		}

		@Override
		public String getContent() {
			return "<p>" + "You head over to the free weights section of the gym and spend some time warming up before using the equipment." + " After an hour of intense workout, you're left feeling very pleased with yourself."
					+ " You feel like you've definitely improved your strength, even if only by a tiny amount." + "</p>" + "<p>"
					+ (Main.game.getPlayer().getStaminaPercentage() < 0.4f ? "You feel completely exhausted, and are far too tired to do any more exercise."
							: (Main.game.getDialogueFlags().gymIsMember ? "You feel as though you have enough energy left to do another workout if you wanted to, but you're too tired to do Pix's intense workout routine."
									: "You feel as though you have enough energy left to do another workout if you wanted to."))
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			return getResponseGym(index);
		}

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};
	public static final DialogueNodeOld GYM_PIX_TRAINING = new DialogueNodeOld("City gym", "-", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 120;
		}


		@Override
		public String getContent() {
			return "<p>"
					+ "You look over to where Pix is trying, very badly, to act nonchalant as she watches you."
					+ " Beckoning her over, she immediately breaks her composure, smiling happily as she bounces her way over to you."
					+ "</p>"
					+ "<p>"
					+ UtilText.parseSpeech("Heya! How're ya doin'?!", Main.game.getPix())
					+ " she squeals, obviously extremely pleased that you've asked her to come over."
					+ "</p>"
					+ "<p>"
					+ "You start to say that you'd like her to show you a good routine to follow, but before you're even halfway through your first sentence, she hastily interrupts you in her eagerness to respond, "
					+ UtilText.parseSpeech("Wohoo! So, like, you want to have a good workout, huh?! Let's start over here!", Main.game.getPix())
					+ "</p>"
					+ "<p>"
					+ "You follow Pix over to the free weights section, where she begins to show you her special routine."
					+ " She gets you to do several sets of weights, working her way through every set of muscles on your body."
					+ " By the time you're done, you're left panting and feeling thoroughly worn out."
					+ "</p>"
					+ "<p>"
					+ UtilText.parseSpeech("Come on now! No slacking!", Main.game.getPix())
					+ " she exclaims, before bounding over to the cardio section."
					+ "</p>"
					+ "<p>"
					+ "You let out a groan as you realise that she wants you to exercise on the running machines."
					+ " Pix's tail, which up until now hasn't stopped wagging, flicks upright for a second, before continuing its seemingly uncontrollable motion."
					+ "</p>"
					+ "<p>"
					+ UtilText.parseSpeech("Hey! I heard that! Come on now, if you give me an hour, I'll make it worth your while!", Main.game.getPix())
					+ " she winks, pushing you onto one of the machines and turning it on."
					+ "</p>"
					+ "<p>"
					+ "With her excitable manner of talking over you, combined with the fact that you've been breathing heavily throughout Pix's exercise routine, you've been completely unable to get out a single sentence."
					+ " You wonder what she means by making it 'worth your while', but as you run, you start to get a clear idea of what she means."
					+ " Pix moves round behind you as you run, and you can almost feel her lustful gaze resting on your ass."
					+ "</p>"
					+ "<p>"
					+ UtilText.parseSpeech("Mmmm! Y'know, you've got one hot ass!", Main.game.getPix())
					+ " she giggles."
					+ "</p>"
					+ "<p>"
					+ "As focused as you are on having to keep up with the treadmill's movement, you're unable to respond to her pervy comment, and just keep running."
					+ " While you exercise, Pix uses the opportunity to check you out, making inappropriate comments every now and then about the state of your body."
					+ " After what feels like an eternity, Pix leans over and dials the difficulty back, instructing you to take a few minutes to do a cooldown walk."
					+ " As you pant heavily, Pix leans over and uses a towel to wipe the sweat from your forehead."
					+ " She brushes her hand over your body as she retracts, and reminds you of what she promised earlier."
					+ "</p>"
					+ "<p>"
					+ UtilText.parseSpeech("You're like, my star pupil, y'know! I said I'd make it worth your while, remember?!"
							+ " So come meet me in the showers if you want a little one-to-one cooldown exercise...", Main.game.getPix())
					+ " she says, enticingly sliding her paw-like hands over her breasts and giggling before bouncing away towards the showers."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Pix's reward",
						"You have a good idea of what Pix means when she says she wants to give you a 'one-to-one cooldown exercise'...", // OR DO YOU?! :D
						GYM_PIX_RAPE);
				
			} else 
				if (index == 2) {
				return new Response("Leave", "You're far too tired to deal with Pix right now. Get changed and leave the gym, avoiding Pix in the showers as you do so.", ShoppingArcadeDialogue.ARCADE);
				
			} else {
				return null;
			}
		}

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};
	
	public static final DialogueNodeOld GYM_PIX_RAPE = new DialogueNodeOld("City gym", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "Heading off into the changing rooms, a wave of exhaustion suddenly crashes over you."
					+ " Pix's exercise routine seems to have been designed specifically to completely wear you out, and you quickly decide that you should turn down the energetic dog-girl's offer of a 'one-to-one cooldown exercise'."
					+ " After all, in your current state, you barely even have the energy required to stay standing, and you wouldn't want to let her down with a sub-par performance."
				+ "</p>"

				+ "<p>"
					+ "Quickly stripping out of your clothes, you head over to the showers, scanning around for any sign of your over-enthusiastic personal trainer."
					+ " The place looks to be completely deserted, and, assuming that Pix must have got distracted elsewhere, you step into the shower and start to clean yourself off."
				+ "</p>"
				
				+ "<p>"
					+ "Over the crashing noise of the shower, you're unable to hear the dog-girl as she sneaks up on you."
					+ " Facing the wall, you don't even have the chance to spot her approach, or to see the predatory grin that she fails to conceal upon sighting your naked body."
					+ " In fact, you're completely oblivious to Pix's presence, right up until the moment she pounces forwards and pins you up against the wall."
				+ "</p>"
					
				+ "<p>"
					+ "You start to let out a yell in surprise, but the dog-girl quickly clasps a paw-like hand over your mouth as she leans in, pressing her naked body against yours as she growls in your ear, "
					+ UtilText.parseSpeech("Haha! So, like, were you trying to avoid me or something?! 'Cause that kinda makes me sad!"
							+ " And y'know, it also kinda makes me think that you need a little punishment! So, whattya got to say for yourself, huh, huh, huh?!", Main.game.getPix())
				+ "</p>"
				+ "<p>"
				+ "As she lifts her hand from over your mouth, you finally get the chance to respond."
				+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if(index==1) {
				return new ResponseSex("Too tired",
						"Tell Pix that you're far too tired to do any more physical exercise right now.",
						GYM,
						Main.game.getPix(), new SMPixShowerTime(), Pix.PIX_POST_SEX,
						"<p>"
							+ "You're far too tired to do any more physical exercise right now, and tell Pix as such, "
							+UtilText.parsePlayerSpeech("I wasn't avoiding you, I'm just too tired for this right now. Maybe we can carry on in about half an hour or something?")
						+ "</p>"
						+ "<p>"
						+ "Although you can't see her reaction, you feel her grip on your wrists tighten as you try to make an excuse."
						+ " As Pix leans into your back, she growls into your ear once more, sending a shiver down your spine as you hear the icy tone in her voice."
						+ "</p>"
						+ "<p>"
						+ UtilText.parseSpeech("Y'know, that's not what I wanted to hear. Not what I wanted to hear at all.", Main.game.getPix())
						+ "</p>"
						+ "<p>"
						+ "As she speaks, she releases the grip on one of your wrists, and as she crushes you up against the wall of the shower, you feel her hand slip around and down between your legs."
						+ " You let out a gasp as she starts stroking your crotch, and as her three pairs of naked breasts squish into your back, her growls start to soften down a little."
						+ "</p>"
						+ "<p>"
						+ UtilText.parseSpeech("Didya notice how empty the gym was?"
								+ " Y'know, most of them stop coming after I have a little fun with them in here."
								+ " I can see that you're made of tougher stuff though, so I'm sure you won't mind helping me have a little fun!", Main.game.getPix())
						+ "</p>");
				
			} else if(index==2) {
				return new ResponseSex("Make it up to her",
						"Tell Pix that you can make it up to her right now.",
						GYM,
						Main.game.getPix(), new SMPixShowerTime(), Pix.PIX_POST_SEX,
						"<p>"
							+ "As tired as you are, Pix turns you on far too much to refuse her advances, and you turn your head to one side as you eagerly respond, "
							+UtilText.parsePlayerSpeech("Perhaps you'll let me make it up to you?")
						+ "</p>"
						+ "<p>"
							+ "Although you can't see her reaction, you feel her grip on your wrists tighten as you try to turn to face her."
							+ " Leaning into your back, Pix growls into your ear once more, sending a shiver down your spine as you hear the icy tone in her voice."
						+ "</p>"
						+ "<p>"
						+ UtilText.parseSpeech("Y'know, I don't think you're in any position to take charge here.", Main.game.getPix())
						+ "</p>"
						+ "<p>"
						+ "As she speaks, she releases the grip on one of your wrists, and as she crushes you up against the wall of the shower, you feel her hand slip around and down between your legs."
						+ " You let out a gasp as she starts stroking your crotch, and as her three pairs of naked breasts squish into your back, her growls start to soften down a little."
						+ "</p>"
						+ "<p>"
						+ UtilText.parseSpeech("Didya notice how empty the gym was?"
								+ " Y'know, most of them stop coming after I have a little fun with them in here."
								+ " I can see that you're made of tougher stuff though, so I'm sure you won't mind helping me have a little fun!", Main.game.getPix())
						+ "</p>");
				
			} else if(index==3) {
				return new ResponseSex("Apologise",
						"Apologise to Pix and accept her punishment.",
						GYM,
						Main.game.getPix(), new SMPixShowerTime(), Pix.PIX_POST_SEX,
						"<p>"
							+ "You don't want Pix to think that you've been avoiding her, and you turn your head to one side as you apologise, "
							+UtilText.parsePlayerSpeech("Sorry Pix, I wasn't trying to avoid you, I just didn't see you anywhere nearby!")
						+ "</p>"
						+ "<p>"
							+ "Although you can't see her reaction, you feel her grip on your wrists tighten as you say you're sorry."
							+ " Leaning into your back, Pix growls into your ear once more, sending a shiver down your spine as you hear the icy tone in her voice."
						+ "</p>"
						+ "<p>"
						+ UtilText.parseSpeech("Y'know, that ain't gonna cut it! No, I think you still need a little punishment.", Main.game.getPix())
						+ "</p>"
						+ "<p>"
						+ "As she speaks, she releases the grip on one of your wrists, and as she crushes you up against the wall of the shower, you feel her hand slip around and down between your legs."
						+ " You let out a gasp as she starts stroking your crotch, and as her three pairs of naked breasts squish into your back, her growls start to soften down a little."
						+ "</p>"
						+ "<p>"
						+ UtilText.parseSpeech("Didya notice how empty the gym was?"
								+ " Y'know, most of them stop coming after I have a little fun with them in here."
								+ " I can see that you're made of tougher stuff though, so I'm sure you won't mind helping me have a little fun!", Main.game.getPix())
						+ "</p>");
				
			} else {
				return null;
			}
		}
	};
}
