package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.npc.dominion.Pix;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.managers.dominion.SMPixShowerTime;
import com.lilithsthrone.game.sex.managers.universal.SMFaceToWall;
import com.lilithsthrone.game.sex.positions.SexSlotBipeds;
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
	
	public static final DialogueNode GYM_EXTERIOR = new DialogueNode("Pix's Playground (Exterior)", "-", false) {

		@Override
		public String getContent() {
			if (!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.gymIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_EXTERIOR");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_EXTERIOR_REPEAT");
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

	public static final DialogueNode GYM = new DialogueNode("Pix's Playground", "-", true) {

		@Override
		public String getContent() {
			if (!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.gymIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_ENTRY");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_ENTRY_REPEAT");
			}
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
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_EXIT"));
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
	
	public static final DialogueNode GYM_FOLLOW = new DialogueNode("Pix's Playground", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_FOLLOW");
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
	public static final DialogueNode GYM_RETURNING = new DialogueNode("Pix's Playground", "-", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_RETURNING"));
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.gymIsMember)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_RETURNING_MEMBER"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_RETURNING_NOT_MEMBER"));
			}
			
			return UtilText.nodeContentSB.toString();
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
	public static final DialogueNode GYM_SINGLE_PAYMENT = new DialogueNode("Pix's Playground", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_SINGLE_PAYMENT");
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
	public static final DialogueNode GYM_LIFETIME_PAYMENT = new DialogueNode("Pix's Playground", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_LIFETIME_PAYMENT");
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
	public static final DialogueNode GYM_MEMBER_ENTER = new DialogueNode("Pix's Playground", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_MEMBER_ENTER");
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
	public static final DialogueNode GYM_CARDIO = new DialogueNode("Pix's Playground", "-", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_CARDIO"));
			
			if(Main.game.getPlayer().getHealthPercentage() < 0.4f) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_EXHAUSTED"));
			} else {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.gymIsMember)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_POST_WORKOUT_MEMBER"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_POST_WORKOUT"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
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
	public static final DialogueNode GYM_WEIGHTS = new DialogueNode("Pix's Playground", "-", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_WEIGHTS"));
			
			if(Main.game.getPlayer().getHealthPercentage() < 0.4f) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_EXHAUSTED"));
			} else {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.gymIsMember)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_POST_WORKOUT_MEMBER"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_POST_WORKOUT"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
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
	public static final DialogueNode GYM_PIX_TRAINING = new DialogueNode("Pix's Playground", "-", true) {
		
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_PIX_TRAINING");
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
	
	public static final DialogueNode GYM_PIX_TRAINING_CARDIO = new DialogueNode("Pix's Playground", "-", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_PIX_TRAINING_CARDIO");
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
	
	public static final DialogueNode GYM_PIX_TRAINING_FINISH = new DialogueNode("Pix's Playground", "-", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_PIX_TRAINING_FINISH");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.getProperties().hasValue(PropertyValue.nonConContent)) {
					return new Response("Pix's reward",
							"You have a good idea of what Pix means when she says she wants to give you a 'one-to-one cooldown exercise'...", // OR DO YOU?! :D
							GYM_PIX_ASSAULT);
				} else {
					return new Response("Pix's reward",
							"You have a good idea of what Pix means when she says she wants to give you a 'one-to-one cooldown exercise'...",
							GYM_PIX_ASSAULT_CONSENSUAL);
				}
				
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
	
	public static final DialogueNode GYM_PIX_ASSAULT = new DialogueNode("Pix's Playground", "-", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_PIX_ASSAULT"));
			
			if(Main.game.getPlayer().getHealthPercentage() < 0.4f) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_PIX_ASSAULT_EXHAUSTED"));
			} else {
				if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)<Main.game.getNpc(Pix.class).getAttributeValue(Attribute.MAJOR_PHYSIQUE)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_PIX_ASSAULT_WEAKER"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_PIX_ASSAULT_STRONGER"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Too tired",
						"Tell Pix that you're far too tired to do any more physical exercise right now.",
						false, false,
						new SMPixShowerTime(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Pix.class), SexSlotBipeds.FACE_TO_WALL_FACING_TARGET_SHOWER_PIX)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBipeds.FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX))),
						null,
						null, PIX_POST_SEX, UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_PIX_ASSAULT_TOO_TIRED"));
				
			} else if(index==2) {
				return new ResponseSex("Offer sex",
						"Tell Pix that you can make it up to her right now...",
						false, false,
						new SMPixShowerTime(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Pix.class), SexSlotBipeds.FACE_TO_WALL_FACING_TARGET_SHOWER_PIX)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBipeds.FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX))),
						null,
						null, PIX_POST_SEX, UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_PIX_ASSAULT_OFFER_SEX"));
				
			} else if(index==3) {
				return new ResponseSex("Accept punishment",
						"Apologise to Pix and accept her punishment.",
						false, false,
						new SMPixShowerTime(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Pix.class), SexSlotBipeds.FACE_TO_WALL_FACING_TARGET_SHOWER_PIX)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBipeds.FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX))),
						null,
						null, PIX_POST_SEX, UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_PIX_ASSAULT_ACCEPT_PUNISHMENT"));
				
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
				if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)<Main.game.getNpc(Pix.class).getAttributeValue(Attribute.MAJOR_PHYSIQUE)) {
					return new Response("Turn Tables",
							"Pix is both stronger and fitter than you (her physique of "+Main.game.getNpc(Pix.class).getAttributeValue(Attribute.MAJOR_PHYSIQUE)+" is greater than your physique of "
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
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBipeds.FACE_TO_WALL_FACING_TARGET)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Pix.class), SexSlotBipeds.FACE_TO_WALL_AGAINST_WALL))) {
								@Override
								public boolean isPlayerStartNaked() {
									return true;
								}
		
								@Override
								public boolean isPartnerStartNaked() {
									return true;
								}
							},
							null,
							null, PIX_POST_SEX_TABLES_TURNED, UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_PIX_ASSAULT_TURN_TABLES"));
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PIX_BREAK_FREE = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "PIX_BREAK_FREE");
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
	
	public static final DialogueNode PIX_POST_SEX_TABLES_TURNED = new DialogueNode("Changing Rooms", "Carry Pix out to the changing rooms.", true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "PIX_POST_SEX_TABLES_TURNED");
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
	
	public static final DialogueNode PIX_POST_SEX = new DialogueNode("Pix dresses you", "You're too tired to complain as Pix starts dressing you.", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "PIX_POST_SEX");
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
	
	public static final DialogueNode GYM_PIX_ASSAULT_CONSENSUAL = new DialogueNode("Pix's Playground", "-", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_PIX_ASSAULT_CONSENSUAL"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Accept",
						"Let Pix have her fun with you.",
						true, false,
						new SMPixShowerTime(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Pix.class), SexSlotBipeds.FACE_TO_WALL_FACING_TARGET_SHOWER_PIX)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBipeds.FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX))),
						null,
						null, PIX_POST_SEX_CONSENSUAL, UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_PIX_ASSAULT_CONSENSUAL_START"));
				
			} else if(index==2) {
				return new Response("Decline",
						"Tell Pix that you're too tired for this right now.",
						GYM_PIX_ASSAULT_CONSENSUAL_DECLINED);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode GYM_PIX_ASSAULT_CONSENSUAL_DECLINED = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_PIX_ASSAULT_CONSENSUAL_DECLINED");
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
	
	public static final DialogueNode PIX_POST_SEX_CONSENSUAL = new DialogueNode("Pix dresses you", "Pix helps you to get dressed.", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "PIX_POST_SEX_CONSENSUAL");
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
