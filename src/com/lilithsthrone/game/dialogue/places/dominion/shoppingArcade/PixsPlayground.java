package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.dominion.SMPixShowerTime;
import com.lilithsthrone.game.sex.managers.universal.SMFaceToWall;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.66
 * @version 0.1.85
 * @author Innoxia
 */
public class PixsPlayground {
	
	private static final int SINGLE_PAYMENT_VALUE = 100;
	private static final int MEMBERSHIP_VALUE = 8000;
	
	private static Response getResponseGym(int index) {
		if (index == 1) {
			if (Main.game.getPlayer().getHealthPercentage() < 0.4f) {
				return new Response("Cardio", "You are too tired to do any more exercise!", null);
				
			} else {
				return new Response("Cardio", "Use the running and cycling machines to burn off some of your body size.", GYM_CARDIO){
					@Override
					public void effects(){
						Main.game.getPlayer().incrementHealth(-Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM) * 0.4f);
						Main.game.getTextEndStringBuilder().append(
//								Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_PHYSIQUE, 0.25f)+ 
								"<p style='text-align:center'>[style.boldBad(-5)] <b style='color:"+Colour.BODY_SIZE_THREE.toWebHexString()+";'>Body Size</b></p>"
								+Main.game.getPlayer().incrementBodySize(-5));
					}
				};
			}

		} else if (index == 2) {
			if (Main.game.getPlayer().getHealthPercentage() < 0.4f) {
				return new Response("Weights", "You are too tired to do any more exercise!", null);
				
			} else {
				return new Response("Weights", "Use the free weights and exercise machines to build up your strength.", GYM_WEIGHTS){
					@Override
					public void effects(){
						Main.game.getPlayer().incrementHealth(-Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM) * 0.4f);
						Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center'>[style.boldGood(+5)] <b style='color:"+Colour.MUSCLE_THREE.toWebHexString()+";'>Muscle Definition</b></p>"
								+Main.game.getPlayer().incrementMuscle(5));
					}
				};
			}

		} else if (index == 3) {
				if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.gymIsMember)) {
					return new Response("Pix", "Only lifetime members can get personal training from Pix.", null);
					
				} else if (Main.game.getPlayer().getHealthPercentage() < 0.8f) {
					return new Response("Pix", "You are too tired to do Pix's exhausting workout routine!", null);
					
				} else {
					return new Response("Pix",
							"Pix is hovering close by, bouncing up and down on the spot while glancing your way. She obviously wants you to ask her for a personal training session. Call her over and grant her wish.", GYM_PIX_TRAINING){
						@Override
						public void effects(){
							Main.game.getPlayer().incrementHealth(-Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM) * 0.1f);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center'>[style.boldGood(+4)] <b style='color:"+Colour.MUSCLE_THREE.toWebHexString()+";'>Muscle Definition</b></p>"
									+Main.game.getPlayer().incrementMuscle(4));
						}
					};
				}
				
		} else if (index == 0) {
			return new Response("Leave", "Decide to leave the gym.", GYM_EXTERIOR);
			
		} else
			return null;
	}
	
	public static final DialogueNodeOld GYM_EXTERIOR = new DialogueNodeOld("Pix's Playground (Exterior)", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if (!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.gymIntroduced)) {
				return "<p>"
							+ "You find yourself standing before one of the largest frontages in the Shopping Arcade."
							+ " The words 'Pix's Playground' are emblazoned in bright red lettering above the entrance, and beneath, you read the words 'Dominions #1 Gym!'."
						+ "</p>"
						+ "<p>"
							+ "Most of the gym's frontage is made up of large glass panels, allowing you to take a good look inside."
							+ " From what you can see, the interior consists of a wide open space, filled with an impressive variety of gym equipment."
							+ " Looking through the glass doors of the entrance, you see a dog-girl sitting behind the lobby's front desk."
						+ "</p>";
			} else {
				return "<p>"
						+ "You find yourself standing before one of the largest frontages in the Shopping Arcade."
						+ " The words 'Pix's Playground' are emblazoned in bright red lettering above the entrance, and beneath, you read the words 'Dominions #1 Gym!'."
					+ "</p>"
					+ "<p>"
						+ "Most of the gym's frontage is made up of large glass panels, allowing you to take a good look inside."
						+ " From what you can see, the interior consists of a wide open space, filled with an impressive variety of gym equipment."
						+ " Looking through the glass doors of the entrance, you see Pix sitting behind the lobby's front desk."
					+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.gymHadTour)) {
					return new Response("Enter", "Step inside the gym.", GYM_RETURNING);
				} else {
					return new Response("Enter", "Step inside the gym.", GYM);
				}
				
				
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

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};

	public static final DialogueNodeOld GYM = new DialogueNodeOld("Pix's Playground", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if (!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.gymIntroduced))
				return "<p>"
							+ "Pushing open the door, you step into the gym's lobby, where you're immediately greeted by an extremely energetic dog-girl."
							+ " Her fur is black with white markings, which, in combination with her folded ears, makes her look very much like an anthropomorphised border-collie."
						+ "</p>"
						+ "<p>"
							+ "[pix.speech(Heya! Hi! How're ya doin'!)]"
							+ " she exclaims, reaching forwards to give your hand an enthusiastic shake with both of her paw-like hands."
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Hello-)]"
							+ " you start to say, but the dog-girl quickly starts to speak over you."
						+ "</p>"
						+ "<p>"
							+ "[pix.speech(Wohoo! So, you've decided to come get fit, huh, huh, huh?)]"
							+ " she asks, but before you can reply, she continues speaking, "
							+ "[pix.speech(So don't just stand there! Oh, I'm Pix by the way! Come on, I'll give you a tour!)]"
						+ "</p>"
						+ "<p>"
							+ "Pix releases your hand, which she's been enthusiastically shaking this entire time, before starting to bound away into the gym, motioning for you to follow her."
							+ " You notice that this entire time, her tail hasn't stopped wagging, and she's quite possibly the most energetic person you've ever met."
						+ "</p>";
			else
				return "<p>"
							+ " Pushing open the door, you step into the gym's lobby, where you're immediately greeted by Pix, who's looking as energetic as ever."
						+ "</p>"
						+ "<p>"
							+ "[pix.speech(Heya, great to see you again! How're ya doin'!)]"
							+ " she exclaims, bounding over to you with a huge grin on her face."
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Hello aga-)]"
							+ " you start to say, but the hyperactive dog-girl quickly starts to speak over you."
						+ "</p>"
						+ "<p>"
							+ "[pix.speech(Wohoo! So, you've decided to come have that tour, huh, huh, huh?)]"
							+ " she asks, but before you can reply, she continues speaking,"
							+ " [pix.speech(So don't just stand there! Come on, follow me!)]"
						+ "</p>"
						+ "<p>"
							+ "Pix happily laughs and turns away before starting to bound into the gym, motioning for you to follow her."
						+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Follow", "Follow Pix and let her give you a tour of the gym.", GYM_FOLLOW){
					@Override
					public void effects(){
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.gymIntroduced);
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.gymHadTour);
					}
				};
				
			} else if (index == 0) {
				return new Response("Leave", "Tell Pix that you don't have time right now, but you might be back later.", GYM_EXTERIOR){
					@Override
					public void effects(){
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.gymIntroduced);
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "You quickly shout to Pix that you don't have time for her tour right now, but you might come back later."
									+ " She doesn't seem fazed at all by your reluctant response, and calls out after you as you leave,"
									+ " [pix.speech(No worries! Come back at any time, I'll be waiting to see you again!)]"
								+ "</p>");
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
	
	public static final DialogueNodeOld GYM_FOLLOW = new DialogueNodeOld("Pix's Playground", "-", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return "<p>"
						+ "You decide to follow the excitable dog-girl, and hurry to catch up with her as she sets off."
						+ " Following Pix through a large glass door, you find yourself standing in the gym proper."
					+ "</p>"
					+ "<p>"
						+ "[pix.speech(So, like, here's all the weights and machines and stuff!)]"
						+ " she shouts, bouncing happily around the room as you follow in her footsteps, "
						+ "[pix.speech(It's usually not much busier than this, so when you sign up, you'll find that you never have to wait for any of the equipment!)]"
					+ "</p>"
					+ "<p>"
						+ "As you're wondering if Pix is even capable of speaking at a normal volume, you look around at your surroundings."
						+ " The gym's main area, which you're currently standing in, is large and spacious, and is filled with all sorts of exercise machines, yoga mats, and free weight sections."
						+ " Despite its impressive size and array of arcane-powered machinery, there doesn't seem to be more than a handful of people in here."
						+ " You wonder if Pix's overbearing hyperactive personality has put people off from signing up."
					+ "</p>"
					+ "<p>"
						+ "[pix.speech(Follow me! Follow me!)]"
						+ " she shouts, interrupting your thoughts as she continues her tour."
					+ "</p>"
					+ "<p>"
						+ "Pix continues to show you around the gym, and you're surprised to find that there's an indoor olympic-sized swimming pool in the back."
						+ " Outside, situated behind the Shopping Arcade, there's a large running track and football field, which Pix happily states are exclusively for use by the gym's members."
						+ " She then shows you some other miscellaneous facilities, such as the changing rooms, showers and lockers."
						+ " Before long, you find yourself stepping back into the gym's lobby, and Pix turns around and beams at you as she finishes her tour."
					+ "</p>"
					+ "<p>"
						+ "[pix.speech(So, what'ya think?! Pretty awesome, huh?!)]"
						+ " she shouts, before moving onto the business-side of things,"
						+ " [pix.speech(Now, life-time membership is ten-thousand flames, or you can just pay each time you come here, which is one-hundred flames per entry!"
								+ " So, what'll be?! If you get the life-time membership, that includes personal workout sessions with none other than your new friend, Pix!"
								+ " You know, I don't do this often, but I can see that you're kinda special and I'd really like to get the chance to work with that body of yours, so just for you,"
								+ " I'll say life-time membership is only eight-thousand flames! Pretty good, huh? So, what'ya want?!)]"
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.gymIsMember)) {
				if (index == 1) {
					if (Main.game.getPlayer().getMoney() < SINGLE_PAYMENT_VALUE)
						return new Response("Single (" + UtilText.formatAsMoneyUncoloured(SINGLE_PAYMENT_VALUE, "span") + ")", "You don't have enough money!", null);
					else
						return new Response("Single (" + UtilText.formatAsMoney(SINGLE_PAYMENT_VALUE, "span") + ")",
								"Tell Pix that you'd like to pay for a single entry to the gym. <b>This will cost</b> "+UtilText.formatAsMoney(SINGLE_PAYMENT_VALUE, "b")+".", GYM_SINGLE_PAYMENT){
						@Override
						public void effects(){
							Main.game.getPlayer().incrementMoney(-SINGLE_PAYMENT_VALUE);
						}
					};
					
				} else if (index == 2) {
					if (Main.game.getPlayer().getMoney() < MEMBERSHIP_VALUE)
						return new Response("Membership (" + UtilText.formatAsMoneyUncoloured(MEMBERSHIP_VALUE, "span") + ")", "You don't have enough money!", null);
					else
						return new Response("Membership ("+ UtilText.formatAsMoney(MEMBERSHIP_VALUE, "span") +")",
								"Tell Pix that you'd like to sign up for the lifetime membership option. <b>This will cost</b> "+UtilText.formatAsMoney(MEMBERSHIP_VALUE, "b")+".",
								GYM_LIFETIME_PAYMENT){
						@Override
						public void effects(){
							Main.game.getPlayer().incrementMoney(-MEMBERSHIP_VALUE);
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.gymIsMember);
						}
					};
					
				} else if (index == 0) {
					return new Response("Leave", "Tell Pix that you'll think about it and be back later.", GYM_EXTERIOR);
	
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Enter", "Enter the gym and get changed.", GYM_MEMBER_ENTER);
					
				} else if (index == 0) {
					return new Response("Leave", "Tell Pix that you've changed your mind, and that you'll be back another time.", GYM_EXTERIOR);
					
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
	public static final DialogueNodeOld GYM_RETURNING = new DialogueNodeOld("Pix's Playground", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You make your way over to where the city gym is located."
						+ " Pushing open the door, you step into the lobby, where you're immediately greeted by the familiar bouncing figure of Pix."
					+ "</p>"
					+ "<p>"
						+ "[pix.speech(Heya [pc.name]! How're ya doin'?!)]"
						+ " she asks, beaming at you."
					+ "</p>"
					+ "<p>"
						+ "You start to greet the energetic dog-girl, but, once again, she immediately cuts you off, "
						+ (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.gymIsMember)
								? "[pix.speech(You gonna want some personal training today?! I'm ready for it whenever you are! You go ahead and get changed, I'll see you in there!)]"
								: "[pix.speech(The eight-thousand flames deal is still on y'know! Only for you! So, you ready to sign up?!)]")
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return GYM_FOLLOW.getResponse(responseTab, index);
		}

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};
	public static final DialogueNodeOld GYM_SINGLE_PAYMENT = new DialogueNodeOld("Pix's Playground", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You tell Pix that you just want to pay for a single entry today, and, handing over one-hundred flames, she smiles at you and lets you enter."
					+ "</p>"
					+ "<p>"
						+ "[pix.speech(Remember, if you get a life-time membership, I can help you train!)]"
						+ " she shouts after you while bouncing up and down on the spot."
					+ "</p>"
					+ "<p>"
						+ "You head over to the changing rooms, where you discover that the gym provides clean exercise clothes for people who haven't brought any."
						+ " Quickly getting changed, you head out into the gym and decide what to do."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseGym(index);
		}

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};
	public static final DialogueNodeOld GYM_LIFETIME_PAYMENT = new DialogueNodeOld("Pix's Playground", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You tell Pix that you want to sign up for a lifetime membership."
						+ " A huge smile spreads over her face, and she leaps up, punching the air and letting out a triumphant shout."
					+ "</p>"
					+ "<p>"
						+ "[pix.speech(Wohoo! That's, like, so amazing! Now I can help you train and stuff, and you can, like, come and go anytime without paying anything extra!)]"
						+ " she explains, and without warning, she leaps forwards and gives you a hug."
					+ "</p>"
					+ "<p>"
						+ "You're somewhat taken aback by her sudden move, but before you can react, she releases you and runs off to fetch a contract from behind the front desk."
						+ " After grabbing the piece of paper and a pen, she practically sprints back over to you and thrusts them into your hands."
					+ "</p>"
					+ "<p>"
						+ "[pix.speech(Ok! So, like, sign here, here, and here!)]"
						+ " she points to different places on the page, and you do as she says before handing over eight-thousand flames, completing the contract."
					+ "</p>"
					+ "<p>"
						+ "[pix.speech(Awesome! So, that's all done! Congratulations! You can go ahead and get changed and stuff, just call me over whenever you're ready for some personal training!)]"
						+ " she exclaims, before running off to fill in her side of the paperwork."
					+ "</p>"
					+ "<p>"
						+ "You head over to the changing rooms, where you see that the gym provides clean exercise clothes for people who haven't brought any."
						+ " Quickly getting changed, you head out into the gym and decide what to do."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseGym(index);
		}

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};
	public static final DialogueNodeOld GYM_MEMBER_ENTER = new DialogueNodeOld("Pix's Playground", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You make your way over to where the city gym is located."
						+ " Pushing open the door, you step into the lobby, where you're immediately greeted by the familiar bouncing figure of Pix."
					+ "</p>"
					+ "<p>"
						+ "[pix.speech(Heya [pc.name]! How're ya doin'?! Gonna have a personal session today?!)]"
						+ " she asks, beaming at you."
					+ "</p>"
					+ "<p>"
						+ "You start to reply to the energetic dog-girl, but, once again, she immediately cuts you off, "
						+ "[pix.speech(Y'know, just any time you want it, call me over and we can get started! You go ahead and get changed, I'll see you in there!)]"
					+ "</p>"
					+ "<p>"
						+ "You do as Pix says and head over to the changing rooms, where you quickly get changed."
						+ " Heading out into the gym, you decide what to do."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseGym(index);
		}

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};
	public static final DialogueNodeOld GYM_CARDIO = new DialogueNodeOld("Pix's Playground", "-", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 60;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You head over to the cardio section of the gym and spend some time warming up before using the running and cycling machines."
						+ " After an hour of intense workout, you're left panting and covered in sweat."
						+ " You feel like you've definitely improved your level of fitness, even if only by a tiny amount."
					+ "</p>"
					+ "<p>"
						+ (Main.game.getPlayer().getHealthPercentage() < 0.4f
								? "You feel completely exhausted, and are far too tired to do any more exercise."
								: (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.gymIsMember)
										? "You feel as though you have enough energy left to do another workout if you wanted to, but you're too tired to do Pix's intense routine."
										: "You feel as though you have enough energy left to do another workout if you wanted to."))
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseGym(index);
		}

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};
	public static final DialogueNodeOld GYM_WEIGHTS = new DialogueNodeOld("Pix's Playground", "-", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 60;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You head over to the free weights section of the gym and spend some time warming up before using the equipment."
						+ " After an hour of intense workout, you're left feeling very pleased with yourself."
						+ " You feel like you've definitely improved your strength, even if only by a tiny amount."
					+ "</p>"
					+ "<p>"
						+ (Main.game.getPlayer().getHealthPercentage() < 0.4f
								? "You feel completely exhausted, and are far too tired to do any more exercise."
								: (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.gymIsMember)
										? "You feel as though you have enough energy left to do another workout if you wanted to, but you're too tired to do Pix's intense workout routine."
										: "You feel as though you have enough energy left to do another workout if you wanted to."))
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseGym(index);
		}

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};
	public static final DialogueNodeOld GYM_PIX_TRAINING = new DialogueNodeOld("Pix's Playground", "-", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 20;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You look over to where Pix is trying, very badly, to act nonchalant as she watches you."
						+ " Beckoning her over, she immediately breaks her composure, smiling happily as she bounces her way over to you."
					+ "</p>"
					+ "<p>"
						+ "[pix.speech(Heya! How're ya doin'?!)]"
						+ " she squeals, obviously extremely pleased that you've asked her to come over."
					+ "</p>"
					+ "<p>"
						+ "You start to say that you'd like her to show you a good routine to follow, but before you're even halfway through your first sentence, she hastily interrupts you in her eagerness to respond, "
						+ "[pix.speech(Wohoo! So, like, you want to have a good workout, huh?! Let's start over here!)]"
					+ "</p>"
					+ "<p>"
						+ "You follow Pix over to the free weights section, where she begins to show you her special routine."
						+ " She gets you to do several sets of weights, working your way through every set of muscles on your body."
					+ "</p>"
					+ "<p>"
						+ "[pix.speech(C'mon! You can do better than that, can'tcha?!)]"
						+ " Pix exclaims, energetically jumping up and down on the spot."
					+ "</p>"
					+ "<p>"
						+ "You feel as though you're already working quite hard, and if you were to push yourself any further, you'd quickly get very fatigued."
						+ " Then again, you <i>are</i> trying to get fitter, and Pix is a professional..."
						+ " Maybe you should give it your all?"
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Full effort", "Put all of your effort into the weights routine.", GYM_PIX_TRAINING_CARDIO){
					@Override
					public void effects(){
						Main.game.getPlayer().incrementHealth(-Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM) * 0.4f);
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that it'd be best to give Pix one hundred percent of your effort, you fully commit to the exercise."
									+ " Following Pix's instructions, you work your way through several more sets of weights, and by the time you're done, you're left panting and feeling thoroughly worn out."
								+ "</p>"
								+ "<p>"
									+ "The routine isn't over just yet, however, and, once you're done with the weights, Pix leads you over towards the cardio section."
								+ "</p>"
								+ "<p style='text-align:center'>[style.boldGood(+6)] <b style='color:"+Colour.MUSCLE_THREE.toWebHexString()+";'>Muscle Definition</b></p>"
								+Main.game.getPlayer().incrementMuscle(6));
						Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center'>[style.boldBad(-4)] <b style='color:"+Colour.BODY_SIZE_THREE.toWebHexString()+";'>Body Size</b></p>"
								+Main.game.getPlayer().incrementBodySize(-4));
					}
				};
				
			} else if (index == 2) {
				return new Response("Hold back", "Hold back and conserve your energy...", GYM_PIX_TRAINING_CARDIO){
					@Override
					public void effects(){
						Main.game.getPlayer().incrementHealth(-Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM) * 0.15f);
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Not wanting to exhaust yourself, you decide to hold back a little."
									+ " Following Pix's instructions, you work your way through several more sets of weights, but,"
										+ " while still maintaining the impression that you're putting one-hundred percent of your effort into it, you don't fully commit yourself to the exercise."
								+ "</p>"
								+ "<p>"
									+ "Pix doesn't seem to pick up on the fact that you're holding back, and, once you're done with the weights, she leads you over towards the cardio section."
								+ "</p>"
								+ "<p style='text-align:center'>[style.boldGood(+2)] <b style='color:"+Colour.MUSCLE_THREE.toWebHexString()+";'>Muscle Definition</b></p>"
								+Main.game.getPlayer().incrementMuscle(2));
						Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center'>[style.boldBad(-4)] <b style='color:"+Colour.BODY_SIZE_THREE.toWebHexString()+";'>Body Size</b></p>"
								+Main.game.getPlayer().incrementBodySize(-4));
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
	
	public static final DialogueNodeOld GYM_PIX_TRAINING_CARDIO = new DialogueNodeOld("Pix's Playground", "-", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 30;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "[pix.speech(Come on now! No slacking!)]"
						+ " she exclaims as she leads you over towards the running machines."
					+ "</p>"
					+ "<p>"
						+ "You let out a groan as you see what Pix has got in store for you."
						+ " The dog-girl's tail, which up until now hasn't stopped wagging, flicks upright for a second, before continuing its seemingly uncontrollable motion."
					+ "</p>"
					+ "<p>"
						+ "[pix.speech(Hey! I heard that! Come on now, if you give me thirty more minutes, I'll make it worth your while!)]"
						+ " she winks, pushing you onto one of the machines and turning it on."
					+ "</p>"
					+ "<p>"
						+ "With her excitable manner of talking over you, combined with the fact that you've been breathing heavily throughout Pix's exercise routine, you've been completely unable to get out a single sentence."
						+ " You wonder what she means by making it 'worth your while', but as you start running, you're given a clear idea of what she means."
						+ " Pix moves around behind you as you run, and you can almost feel her lustful gaze resting on your rear end."
					+ "</p>"
					+ "<p>"
						+ "[pix.speech(Mmm! Y'know, you've got one hot ass!)]"
						+ " she giggles,"
						+ "[pix.speech(Now show me what you're made of!)]"
					+ "</p>"
					+ "<p>"
						+ "Once again, you find yourself with a choice to make."
						+ " Should you put in your full amount of effort, or hold back and conserve your energy?"
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Steady run", "Set the running machine's speed to a steady pace.", GYM_PIX_TRAINING_FINISH){
					@Override
					public void effects(){
						Main.game.getPlayer().incrementHealth(-Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM) * 0.4f);
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Wanting to show Pix what you can do, you decide to set the machine for a steady run."
									+ " Pix lets out an impressed hum as she sees what speed you're setting the machine to,"
									+ " [pix.speech(Mmm... You're eager to show off for me, aren't ya?)]"
								+ "</p>"
								+ "<p style='text-align:center'>[style.boldBad(-6)] <b style='color:"+Colour.BODY_SIZE_THREE.toWebHexString()+";'>Body Size</b></p>"
								+Main.game.getPlayer().incrementBodySize(-6));
					}
				};
				
			} else if (index == 2) {
				return new Response("Slow jog", "Set the running machine's speed to a slow jog in order to conserve energy.", GYM_PIX_TRAINING_FINISH){
					@Override
					public void effects(){
						Main.game.getPlayer().incrementHealth(-Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM) * 0.15f);
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Unwilling to completely exhaust yourself on the running machine, you decide to settle for a slow jog."
									+ " Pix lets out a mildly annoyed huff as she sees what speed you're setting the machine to,"
									+ " [pix.speech(Huh... You're more exhausted than I thought! You're gonna need to get fitter, aren't ya?)]"
								+ "</p>"
								+ "<p style='text-align:center'>[style.boldBad(-2)] <b style='color:"+Colour.BODY_SIZE_THREE.toWebHexString()+";'>Body Size</b></p>"
								+Main.game.getPlayer().incrementBodySize(-2));
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
	
	public static final DialogueNodeOld GYM_PIX_TRAINING_FINISH = new DialogueNodeOld("Pix's Playground", "-", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 30;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "As you exercise, Pix uses the opportunity to check you out, making inappropriate comments every now and then about the state of your body,"
						+ " [pix.speech(That's right, keep working that ass for me... Mmm, yeah, that's good...)]"
					+ "</p>"
					+ "<p>"
						+ "As focused as you are on having to keep up with the treadmill's movement, you're unable to respond to the pervy comments, and just focus on what you're doing instead."
					+ "</p>"
					+ "<p>"
						+ "After what feels like an eternity, the dog-girl trainer finally leans over and dials the difficulty back, instructing you to take a few minutes to do a cooldown walk."
						+ " As you heavily pant, Pix leans over and uses a towel to wipe the sweat from your forehead."
						+ " She brushes her hand over your body as she retracts, and reminds you of what she promised earlier,"
						+ "[pix.speech(You're like, my star pupil, y'know! I said I'd make it worth your while, remember?!"
								+ " So come meet me in the showers if you want a little one-to-one cooldown exercise...)]"
					+"</p>"
					+ "<p>"
						+ "Enticingly sliding her paw-like hands over her breasts, Pix lets out one final giggle before bouncing away towards the showers..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Pix's reward",
						"You have a good idea of what Pix means when she says she wants to give you a 'one-to-one cooldown exercise'...", // OR DO YOU?! :D
						GYM_PIX_RAPE);
				
			} else 
				if (index == 2) {
				return new Response("Leave", "You're far too tired to deal with Pix right now. Get changed and leave the gym, avoiding Pix in the showers as you do so.", GYM_EXTERIOR);
				
			} else {
				return null;
			}
		}

		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
	};
	
	public static final DialogueNodeOld GYM_PIX_RAPE = new DialogueNodeOld("Pix's Playground", "-", true) {
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
					+ "[pix.speech(Haha! So, like, were you trying to avoid me or something?!"
							+ " 'Cause that kinda makes me sad!"
							+ " And y'know, it also kinda makes me think that you need a little punishment!"
							+ " So, whattya got to say for yourself, huh, huh, huh?!)]"
					+ " As she lifts her hand from over your mouth, you're given a chance to respond."
				+ "</p>"
				+ "<p>"
				+ (Main.game.getPlayer().getHealthPercentage()<0.4f
						?"[style.italicsBad(You are completely exhausted from Pix's exercise routine, which, looking back at it, might have been exactly what she was after all this time...)]"
						:(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)<Main.game.getPix().getAttributeValue(Attribute.MAJOR_PHYSIQUE)
							?"[style.italicsGood(As you decided to hold back a little, you think you have enough energy to break free, but, seeing as Pix is stronger and fitter than you, you don't think you could turn the tables on her...)]"
							:"[style.italicsExcellent(As you decided to hold back a little, you think you have enough energy to break free, and, seeing as you're stronger and fitter than Pix, you think you could even turn the tables on her...)]"))
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Too tired",
						"Tell Pix that you're far too tired to do any more physical exercise right now.",
						false, false,
						new SMPixShowerTime(
								Util.newHashMapOfValues(new Value<>(Main.game.getPix(), SexPositionSlot.FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.FACE_TO_WALL_FACING_TARGET_SHOWER_PIX))),
						PIX_POST_SEX,
						"<p>"
							+ "You're far too tired to do any more physical exercise right now, and tell Pix as such, "
							+"[pc.speech(I wasn't avoiding you, I'm just too tired for this right now. Maybe we can carry on in about half an hour or something?)]"
						+ "</p>"
						+ "<p>"
						+ "Although you can't see her reaction, you feel her grip on your wrists tighten as you try to make an excuse."
						+ " As Pix leans into your back, she growls into your ear once more, sending a shiver down your spine as you hear the icy tone in her voice."
						+ "</p>"
						+ "<p>"
						+ "[pix.speech(Y'know, that's not what I wanted to hear. Not what I wanted to hear at all.)]"
						+ "</p>"
						+ "<p>"
						+ "As she speaks, she releases the grip on one of your wrists, and as she crushes you up against the wall of the shower, you feel her hand slip around and down between your legs."
						+ " You let out a gasp as she starts stroking your crotch, and as her three pairs of naked breasts squish into your back, her growls start to soften down a little."
						+ "</p>"
						+ "<p>"
						+ "[pix.speech(Didya notice how empty the gym was?"
								+ " Y'know, most of them stop coming after I have a little fun with them in here."
								+ " I can see that you're made of tougher stuff though, so I'm sure you won't mind helping me have a little fun!)]"
						+ "</p>");
				
			} else if(index==2) {
				return new ResponseSex("Offer sex",
						"Tell Pix that you can make it up to her right now...",
						false, false,
						new SMPixShowerTime(
								Util.newHashMapOfValues(new Value<>(Main.game.getPix(), SexPositionSlot.FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.FACE_TO_WALL_FACING_TARGET_SHOWER_PIX))),
						PIX_POST_SEX,
						"<p>"
							+ "As tired as you are, Pix turns you on far too much to refuse her advances, and you turn your head to one side as you eagerly respond, "
							+"[pc.speech(Perhaps you'll let me make it up to you?)]"
						+ "</p>"
						+ "<p>"
							+ "Although you can't see her reaction, you feel her grip on your wrists tighten as you try to turn to face her."
							+ " Leaning into your back, Pix growls into your ear once more, sending a shiver down your spine as you hear the icy tone in her voice."
						+ "</p>"
						+ "<p>"
						+ "[pix.speech(Y'know, I don't think you're in any position to take charge here.)]"
						+ "</p>"
						+ "<p>"
						+ "As she speaks, she releases the grip on one of your wrists, and as she crushes you up against the wall of the shower, you feel her hand slip around and down between your legs."
						+ " You let out a gasp as she starts stroking your crotch, and as her three pairs of naked breasts squish into your back, her growls start to soften down a little."
						+ "</p>"
						+ "<p>"
						+ "[pix.speech(Didya notice how empty the gym was?"
								+ " Y'know, most of them stop coming after I have a little fun with them in here."
								+ " I can see that you're made of tougher stuff though, so I'm sure you won't mind helping me have a little fun!)]"
						+ "</p>");
				
			} else if(index==3) {
				return new ResponseSex("Accept punishment",
						"Apologise to Pix and accept her punishment.",
						false, false,
						new SMPixShowerTime(
								Util.newHashMapOfValues(new Value<>(Main.game.getPix(), SexPositionSlot.FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.FACE_TO_WALL_FACING_TARGET_SHOWER_PIX))),
						PIX_POST_SEX,
						"<p>"
							+ "You don't want Pix to think that you've been avoiding her, and you turn your head to one side as you apologise, "
							+"[pc.speech(Sorry Pix, I wasn't trying to avoid you, I just didn't see you anywhere nearby!)]"
						+ "</p>"
						+ "<p>"
							+ "Although you can't see her reaction, you feel her grip on your wrists tighten as you say you're sorry."
							+ " Leaning into your back, Pix growls into your ear once more, sending a shiver down your spine as you hear the icy tone in her voice."
						+ "</p>"
						+ "<p>"
						+ "[pix.speech(Y'know, that ain't gonna cut it! No, I think you still need a little punishment.)]"
						+ "</p>"
						+ "<p>"
						+ "As she speaks, she releases the grip on one of your wrists, and as she crushes you up against the wall of the shower, you feel her hand slip around and down between your legs."
						+ " You let out a gasp as she starts stroking your crotch, and as her three pairs of naked breasts squish into your back, her growls start to soften down a little."
						+ "</p>"
						+ "<p>"
						+ "[pix.speech(Didya notice how empty the gym was?"
								+ " Y'know, most of them stop coming after I have a little fun with them in here."
								+ " I can see that you're made of tougher stuff though, so I'm sure you won't mind helping me have a little fun!)]"
						+ "</p>");
				
			} else if(index==4) {
				if(Main.game.getPlayer().getHealthPercentage()<0.4f) {
					return new Response("Break free",
							"You simply don't have enough energy left to try and break free! You need at least 40% energy for this ("+(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.4)+")",
							null);
					
				} else {
					return new Response("Break free",
							"Use the energy that you've saved by holding back during your exercise to break free from Pix.",
							PIX_BREAK_FREE);
				}
				
			} else if(index==5) {
				if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)<Main.game.getPix().getAttributeValue(Attribute.MAJOR_PHYSIQUE)) {
					return new Response("Turn Tables",
							"Pix is both stronger and fitter than you (her physique of "+Main.game.getPix().getAttributeValue(Attribute.MAJOR_PHYSIQUE)+" is greater than your physique of "
									+Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)+"), so you don't stand a chance of turning the tables on her in this situation.",
							null);
					
				} else if(Main.game.getPlayer().getHealthPercentage()<0.4f) {
					return new Response("Turn Tables",
							"You simply don't have enough energy left to try and turn the tables on Pix! You need at least 40% energy for this ("+(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.4)+")",
							null);
					
				} else {
					return new ResponseSex("Turn Tables",
							"Use the energy that you've saved by holding back during your exercise to break free from Pix, and then turn the tables on her...",
							false, false,
							new SMFaceToWall(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.FACE_TO_WALL_FACING_TARGET)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPix(), SexPositionSlot.FACE_TO_WALL_AGAINST_WALL))) {
								@Override
								public boolean isPlayerStartNaked() {
									return true;
								}
		
								@Override
								public boolean isPartnerStartNaked() {
									return true;
								}
							},
							PIX_POST_SEX_TABLES_TURNED,
							"<p>"
								+ "Summoning the energy that you've saved by holding back during Pix's exercise, you push yourself off from the shower wall, causing Pix to stumble backwards."
								+ " Twisting to one side, you break free of her grip, before spinning around and grabbing the startled dog-girl."
								+ " In one swift move, you're behind her, and, locking her arms behind her back, you completely turn the tables as you end up pinning the dog-girl up against the shower wall."
							+ "</p>"
							+ "<p>"
								+ "[pix.speech(Aaah!)]"
								+ " Pix cries, with more than a little excitement in her voice,"
								+ " [pix.speech(Like, nobody's ever managed to do <i>this</i> before!)]"
							+ "</p>"
							+ "<p>"
								+ "Leaning in over her shoulder, you can't help but grin as you growl into Pix's ear,"
								+ " [pc.speech(It looks like it's <i>you</i> who's the one in need of some punishment...)]"
							+ "</p>");
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld PIX_BREAK_FREE = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return "<p>"
						+ "Summoning the energy that you've saved by holding back during Pix's exercise, you push yourself off from the shower wall, causing Pix to stumble backwards."
						+ " Twisting to one side, you break free of her grip, before spinning around to face the naked dog-girl."
					+ "</p>"
					+ "<p>"
						+ "[pix.speech(Hah!)]"
						+ " she cries,"
						+ " [pix.speech(I knew ya were holdin' back! Well, I guess I'll just have ta get ya another time!)]"
					+ "</p>"
					+ "<p>"
						+ "Throwing you a mischievous grin, she bolts off towards the exit, calling out behind her as she goes,"
						+ " [pix.speech(Come back soon! You're, like, still my star pupil!)]"
					+ "</p>"
					+ "<p>"
						+ "Shaking your head, you continue with your shower, making sure to keep an eye on the entrance."
						+ " After you're done, you head back into the changing rooms, and without further incident, dry yourself off, get changed, and leave the gym."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Exit gym", "Leave the gym and carry on your way.", GYM_EXTERIOR);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld PIX_POST_SEX_TABLES_TURNED = new DialogueNodeOld("Changing Rooms", "Carry Pix out to the changing rooms.", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 60;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Hooking your arms under Pix's, you drag the exhausted dog-girl out from the showers, and into the changing rooms."
						+ " Lying her down on one of the wooden benches, you grab a nearby towel, before quickly drying her body off."
						+ " You can't help but grin as she sighs,"
						+ " [pix.speech(Ahh... Thanks [pc.name]... That sure was fun, y'know...)]"
					+ "</p>"
					+ "<p>"
						+ "You let out a satisfied sigh of your own in response, and, as the dog-girl lies back on the bench, you dry yourself off and get changed."
						+ " Knowing that she's going to need some time to recover from her special 'one-to-one cooldown exercise', you pat the dog-girl on the head, before setting off out of the gym."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Exit gym", "Leave the gym and carry on your way.", GYM_EXTERIOR);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld PIX_POST_SEX = new DialogueNodeOld("Pix dresses you", "You're too tired to complain as Pix starts dressing you.", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 60;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "Pix hooks her arms under yours and drags your almost-comatose body out from the showers."
					+ " You worry for a moment that she's going to continue with her 'fun', but instead, she quickly darts of to fetch a towel, before starting to gently dry your body off."
					+ "</p>"
					+ "<p>"
					+ "[pix.speech(You're, like, the best, y'know! Thanks for letting me have some fun, I hope you enjoyed it too!)]"
					+ "</p>"
					+ "<p>"
					+ "You're far too tired to respond, and instead simply let out a satisfied groan as the dog-girl quickly dresses you."
					+ " After making sure that you're lying down comfortably on one of the benches, she gives you one last passionate kiss before giggling and running off."
					+ "</p>"
					+ "<p>"
					+ "It takes some time before you finally recover enough energy to get up and leave the gym."
					+ " Unusually, Pix is nowhere to be seen, and you wonder if she's avoiding you until you've had some time to fully recover from her 'one-to-one cooldown exercise'..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Exit gym", "Leave the gym and carry on your way.", GYM_EXTERIOR);
			} else {
				return null;
			}
		}
	};
}
