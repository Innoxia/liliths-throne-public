package com.lilithsthrone.game.dialogue.places.dominion.zaranixHome;

import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.2
 * @version 0.2.3
 * @author Innoxia
 */
public class ZaranixHomeGroundFloorRepeat {
	
	public static final DialogueNodeOld OUTSIDE = new DialogueNodeOld("Zaranix's Home", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Once more you find yourself standing outside Zaranix's home."
						+ " Much like all the other buildings in this area of the city, the incubi's residence takes the form of a grand, regency-style townhouse."
						+ " Unlike the majority of the other buildings, however, his home has what appears to be a modestly-sized private garden adjoining the property to the right-hand side."
						+ " A cast-iron fence, standing at around eight-feet tall, separates the garden from the street in which you're standing."
					+ "</p>"
					+ "<p>"
						+ "Since your last visit, the security seems to have been increased, and you can see the faint shimmer of a magical barrier above the garden's fence, making any entry from that direction impossible."
						+ " Similarly, the front door has been replaced by a far sturdier-looking one, and you imagine that even the strongest of demons would have difficulty in breaking it down."
					+ "</p>"
					+ "<p>"
						+ "As you left Zaranix on relatively-amicable terms, you don't think that there would be any trouble in simply knocking the front door..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Knock door", "Knock on the door and wait for someone to answer. <b>Added soon!</b>", null) {
					@Override
					public void effects() {
						Main.game.getAmber().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR, false);
					}
				};

			} else if (index == 0) {
				return new Response("Leave", "Turn around and walk away.", DebugDialogue.getDefaultDialogueNoEncounter());

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_KNOCK_ON_DOOR = new DialogueNodeOld("Zaranix's Home", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You decide to try knocking on the door again."
						+ " After all, maybe a more reasonable maid will answer this time?"
						+ " With that hope in your mind, you step forwards and take hold of the ornate brass door knocker, before delivering a sharp rap to alert those inside of your presence."
					+ "</p>"
					+ "<p>"
						+ "After just a few moments, you hear the sound of a lock being slid back, before the heavy oak door swings inwards to reveal one of the most striking succubi you've ever seen."
						+ " Her gorgeous face and perfectly-proportioned body are covered in pitch-black ebony skin, but it's not her voluptuous figure which your eyes are drawn to."
						+ " Styled into a sidecut, and giving off a luminescent, yellowy-orange glow, it's her extraordinary amber hair that you find yourself gazing at."
						+ " As her eyes fixate themselves onto yours, you see that her irises are the exact same glowing-amber shade as her hair, giving this demon a truly remarkable appearance."
					+ "</p>"
					+ "<p>"
						+ "Despite the fact that her maid's dress quite clearly identifies her as a servant in this household, her attitude is not at all one of subservience, and as she crosses her arms and leans against the door frame,"
							+ " she makes a disapproving clicking noise with her tongue,"
						+" [amber.speech(So, it's <i>you</i> again!"
							+ " You've got some nerve, showing up here after taking my master's slave like that."
							+ " He told me to let you in if you ever showed up again, but if you have any sense, you'd get down on your knees right now and beg for me to forgive you."
							+ " If the next words out of your mouth aren't 'Sorry mistress Amber', then I'll be royally pissed.)]"
					+ "</p>"
					+ "<p>"
						+ "As her little wings flutter in impatience, she narrows her piercing, luminescent-amber eyes and glares at you in an intimidating manner."
						+ " You could ignore Amber's demands and enter the house, or prehaps it would be best to apologise..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Enter", "Step past Amber and enter Zaranix's home.", PlaceType.ZARANIX_GF_ENTRANCE.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
					}
				};

			} else if (index == 2) {
				return new Response("Apologise", "Do as Amber says and get down on your knees to apologise to her.", OUTSIDE_APOLOGY,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null);

			} else if (index == 3) {
				return new Response("Step back", "Step back from the door and think about finding another way in.", OUTSIDE) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "[pc.speech(I just remembered that I need to be elsewhere right now,)]"
									+ " you say, stepping back from the door."
								+ "</p>"
								+ "<p>"
									+ "[amber.speech(Don't waste my time again, bitch.)]"
									+ " Amber snarls, before slamming the door in your face."
								+ "</p>");
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_APOLOGY = new DialogueNodeOld("Zaranix's Home", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Amber's threatening manner is too much for you to bear, and, dropping down on your knees in front of her, you cry out,"
						+ " [pc.speech(Sorry mistress Amber! I didn't mean to get you angry... Please forgive me!)]"
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(How pathetic,)]"
						+ " Amber snarls, lifting up her right foot and pushing the heel into your chest."
						+ " [amber.speech(You're such a little bitch. I can't believe you thought you'd get away with taking my master's slave.)]"
					+ "</p>"
					+ "<p>"
						+ "With that, the fiery-haired maid suddenly pushes her leg forwards, and, unable to keep your balance, you find yourself being kicked backwards down the couple of steps that lead up to the front door."
						+ " Laughing out loud, Amber steps forwards, reaching down to grab "+(Main.game.getPlayer().getHairRawLengthValue()>HairLength.THREE_SHOULDER_LENGTH.getMinimumValue()?"a fistful of your [pc.hair+]":"the back of your neck")
							+", before dragging you out into the street in front of her master's home."
						+ " Glaring down at you as you shuffle about on your knees, Amber sneers,"
						+ " [amber.speech(Now, you're going to show everyone here what a useless bitch you are!)]"
					+ "</p>"
					+ "<p>"
						+ "Letting you go, Amber moves around in front of you, before raising her foot and pushing her heel into your face,"
						+ " [amber.speech(Lick it, bitch.)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Lick", "Obediently do as Amber says and start licking her heels.", OUTSIDE_LICKING);

			} else if (index == 0) {
				return new Response("Refuse", "Stand up and refuse to lick Amber's heels.", DebugDialogue.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ ""//TODO
								+ "</p>");
					}
				};

			} else {
				return null;
			}
		}
	};
	//TODO
	public static final DialogueNodeOld OUTSIDE_LICKING = new DialogueNodeOld("Zaranix's Home", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You let out a humiliated whimper as you obediently move to do as you're told,"
						+ " [pc.speech(Yes mistress Amber!)]"
					+ "</p>"
					+ "<p>"
						+ "An evil, mocking laugh drifts down to you, and, still in full sight of the street behind you, you drop your mouth down to Amber's feet."
						+ " The black high heels that she's wearing are in keeping with the rest of her maid's uniform, with a little lacy white bow adorning each one."
						+ " Deciding to start with her right foot, you bring your [pc.lips+] down to the black leather, letting out a little moan in anticipation as you breathe in the warm, faintly-sweaty scent of her white cotton stockings."
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(You're enjoying this, aren't you bitch?)] Amber mocks, clearly amused by the fact that you're so eager to humiliate yourself."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Yes Miss Amber!)]"
						+ " you enthusiastically reply, before pressing your [pc.lips] down against the tip of her shoe."
						+ " Pushing forwards, you eagerly drag your [pc.tongue+] up and over her feminine footwear, relishing the bitter, tangy taste of leather, dirt, and shoe polish that immediately strikes your taste buds."
						+ " Pulling back, you start once more from the tip of Amber's shoe, licking up and down as you seek to clean every inch of the delicious black leather."
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(Hahaha! How pathetic!)] Amber laughs, tilting her foot to one side to present the un-licked areas of her shoe to your eager [pc.tongue],"
						+ " [amber.speech(That's right bitch, keep on going!!)]"
					+ "</p>"
					+ "<p>"
						+ "You continue licking and kissing Amber's shoe, the sadistic succubus encouraging you to keep on going until it's spotlessly clean."
						+ " Pulling her foot back, she then pushes her left shoe towards your face, and you submissively set about licking that one clean as well."
					+ "</p>"
					+ "<p>"
						+ "After a short while, you've polished both of Amber's heels to a shiny finish, and she lets out a spiteful, derisive laugh,"
						+ " [amber.speech(Hahaha! I can't believe how pathetic you are! Stay there bitch, I'll be back shortly. Oh, and don't even <i>think</i> about standing up, or else the deal's off!)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Enter", "Enter Zaranix's home.", DebugDialogue.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ ""//TODO
								+ "</p>");
					}
				};

			} else if(index == 2) {
				return new Response("Leave", "Do as Amber suggests and head back out into Demon Home.", DebugDialogue.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ ""//TODO
								+ "</p>");
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	// General places:
	
//	public static final DialogueNodeOld ENTRANCE = new DialogueNodeOld("", "", false) {
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public int getMinutesPassed() {
//			return 1;
//		}
//
//		@Override
//		public String getLabel() {
//			return "Entrance Hall";
//		}
//
//		@Override
//		public String getContent() {
//			UtilText.nodeContentSB.setLength(0);
//
//			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE"));
//			
//			return UtilText.nodeContentSB.toString();
//		}
//
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if (index == 1) {
//				return new Response("Exit", "Leave Zaranix's house and head back out into Demon Home. <b>You will have to gain entry all over again if you choose to leave now!</b>", PlaceType.DOMINION_DEMON_HOME_ARTHUR.getDialogue(false)) {
//					@Override
//					public void effects() {
//						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR, false);
//						resetHouseAfterLeaving();
//					}
//				};
//
//			} else {
//				return null;
//			}
//		}
//	};
//	
//	public static final DialogueNodeOld STAIRS = new DialogueNodeOld("", "", false) {
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public int getMinutesPassed() {
//			return 1;
//		}
//
//		@Override
//		public String getLabel() {
//			return "Staircase";
//		}
//
//		@Override
//		public String getContent() {
//			UtilText.nodeContentSB.setLength(0);
//
//			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "STAIRS"));
//			
//			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
//				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "STAIRS_KATHERINE_NOT_SUBDUED"));
//			}
//			
//			return UtilText.nodeContentSB.toString();
//		}
//
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if (index == 1) {
//				return new Response("Upstairs", "Head upstairs to the first floor of Zaranix's house.", PlaceType.ZARANIX_FF_STAIRS.getDialogue(false)) {
//					@Override
//					public void effects() {
//						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_STAIRS, false);
//					}
//				};
//
//			} else {
//				return null;
//			}
//		}
//	};
//	
//	public static final DialogueNodeOld CORRIDOR = new DialogueNodeOld("", "", false) {
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public int getMinutesPassed() {
//			return 1;
//		}
//
//		@Override
//		public String getLabel() {
//			return "Corridor";
//		}
//
//		@Override
//		public String getContent() {
//			UtilText.nodeContentSB.setLength(0);
//
//			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR"));
//			
//			if(Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1) != null
//					&& Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1).getPlace().getPlaceType()==PlaceType.ZARANIX_GF_MAID
//					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
//				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_KATHERINE_NOT_SUBDUED"));
//			}
//			
//			return UtilText.nodeContentSB.toString();
//		}
//
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			return null;
//		}
//	};
//	
//	public static final DialogueNodeOld GARDEN_ENTRY = new DialogueNodeOld("", "", false) {
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public int getMinutesPassed() {
//			return 1;
//		}
//
//		@Override
//		public String getLabel() {
//			return "Zaranix's Garden";
//		}
//
//		@Override
//		public String getContent() {
//			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "GARDEN_ENTRY");
//		}
//
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if (index == 1) {
//				if(Main.game.getPlayer().isAbleToFly()) {
//					return new Response("Fly over fence",
//							"Fly over the garden's fence and back out into Demon Home. <b>If you leave, all progress you've made through Zaranix's home will be reset!</b>",
//							PlaceType.DOMINION_DEMON_HOME_ARTHUR.getDialogue(false)) {
//						@Override
//						public void effects() {
//							resetHouseAfterLeaving();
//							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR, false);
//							Main.game.getTextStartStringBuilder().append(
//									"<p>"
//										+ "Deciding that you'll come back another time, you take a little run-up down the garden path, before launching yourself into the air."
//										+ " Swooping down over the fence, you very quickly find yourself back out in Demon Home once again..."
//									+ "</p>");
//						}
//					};
//				}
//				return new Response("Climb fence",
//						"Climb over the garden's fence and head back out into Demon Home. <b>If you leave, all progress you've made through Zaranix's home will be reset!</b>",
//						PlaceType.DOMINION_DEMON_HOME_ARTHUR.getDialogue(false)) {
//					@Override
//					public void effects() {
//						resetHouseAfterLeaving();
//						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR, false);
//						Main.game.getTextStartStringBuilder().append(
//								"<p>"
//									+ "Deciding that you'll come back another time, you climb up and over the fence, quickly finding yourself back out in Demon Home once again..."
//								+ "</p>");
//					}
//				};
//
//			} else {
//				return null;
//			}
//		}
//	};
//	
//	public static final DialogueNodeOld GARDEN = new DialogueNodeOld("", "", false) {
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public int getMinutesPassed() {
//			return 1;
//		}
//
//		@Override
//		public String getLabel() {
//			return "Zaranix's Garden";
//		}
//
//		@Override
//		public String getContent() {
//			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "GARDEN");
//		}
//
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			return null;
//		}
//	};
//	
//	public static final DialogueNodeOld GARDEN_ROOM = new DialogueNodeOld("", "", false) {
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public int getMinutesPassed() {
//			return 1;
//		}
//
//		@Override
//		public String getLabel() {
//			return "Garden Room";
//		}
//
//		@Override
//		public String getContent() {
//			UtilText.nodeContentSB.setLength(0);
//
//			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "GARDEN_ROOM"));
//			
//			if(Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX()-1, Main.game.getPlayer().getLocation().getY()) != null
//					&& Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX()-1, Main.game.getPlayer().getLocation().getY()).getPlace().getPlaceType()==PlaceType.ZARANIX_GF_MAID
//					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
//				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "GARDEN_ROOM_KATHERINE_NOT_SUBDUED"));
//			}
//			
//			return UtilText.nodeContentSB.toString();
//		}
//
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			return null;
//		}
//	};
//	
//	public static final DialogueNodeOld CORRIDOR_MAID = new DialogueNodeOld("", "", false) {
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public int getMinutesPassed() {
//			return 1;
//		}
//
//		@Override
//		public String getLabel() {
//			return "Corridor";
//		}
//
//		@Override
//		public String getContent() {
//			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
//				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_SUBDUED");
//			
//			} else if(Main.game.getKatherine().getFoughtPlayerCount()==0) {
//				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_ENCOUNTER");
//			
//			} else {
//				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_ENCOUNTER_REPEAT");
//			}
//		}
//
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
//				if(index==1) {
//					return new ResponseSex("Use Katherine", "Have some fun with this maid.",
//							true, false,
//							new SMStanding(
//									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
//									Util.newHashMapOfValues(new Value<>(Main.game.getKatherine(), SexPositionSlot.STANDING_SUBMISSIVE))),
//							ZaranixMaidKatherine.AFTER_SEX_VICTORY,
//							UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_SEX"));
//					
//				} else if(index==2) {
//					return new ResponseSex("Submit",
//							"You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with Katherine. Perhaps if you submitted, she'd be willing to fuck you?",
//							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null, null,
//							true, true,
//							new SMStanding(
//									Util.newHashMapOfValues(new Value<>(Main.game.getKatherine(), SexPositionSlot.STANDING_DOMINANT)),
//									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
//							ZaranixMaidKatherine.AFTER_SEX_VICTORY,
//							UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_SEX_SUBMIT"));
//					
//				} else if (index == 3) {
//					return new Response("Transformations",
//							"Get Katherine to use [katherine.her] demonic powers to transform [katherine.herself]...",
//							BodyChanging.BODY_CHANGING_CORE){
//						@Override
//						public void effects() {
//							Main.game.saveDialogueNode();
//							BodyChanging.setTarget(Main.game.getKatherine());
//						}
//					};
//					
//				} else {
//					return null;
//				}
//			} else {
//				if(index==1) {
//					return new ResponseCombat("Fight", "Defend yourself against the furious maid!", Main.game.getKatherine()) {
//						@Override
//						public void effects() {
//							Main.game.getKatherine().setPlayerKnowsName(true);
//							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, true);
//						}
//					};
//				} else {
//					return null;
//				}
//			}
//		}
//		
//		@Override
//		public boolean isTravelDisabled() {
//			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued);
//		}
//	};
//	
//	public static final DialogueNodeOld ROOM = new DialogueNodeOld("", "", false) {
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public int getMinutesPassed() {
//			return 1;
//		}
//
//		@Override
//		public String getLabel() {
//			return "Room";
//		}
//
//		@Override
//		public String getContent() {
//			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ROOM");
//		}
//
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			return null;
//		}
//	};
//	
//	public static final DialogueNodeOld LOUNGE = new DialogueNodeOld("", "", false) {
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public int getMinutesPassed() {
//			return 1;
//		}
//
//		@Override
//		public String getLabel() {
//			return "Lounge";
//		}
//
//		@Override
//		public String getContent() {
//			if(Main.game.getAmber().getLocationPlace().getPlaceType()!=PlaceType.ZARANIX_GF_LOUNGE) {
//				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_EMPTY");
//				
//			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixAmberSubdued)) {
//				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_SUBDUED");
//				
//			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor) || Main.game.getAmber().getFoughtPlayerCount()!=0) {
//				UtilText.nodeContentSB.setLength(0);
//				
//				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_PRESENT_ALREADY_MET"));
//				
//				if(Main.game.getAmber().getFoughtPlayerCount()!=0) {
//					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_PRESENT_ALREADY_MET_KNOWS_NAME"));
//				} else {
//					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_PRESENT_ALREADY_MET_DOES_NOT_KNOW_NAME"));
//				}
//				
//				return UtilText.nodeContentSB.toString();
//				
//			} else {
//				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_PRESENT");
//			}
//		}
//
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if(Main.game.getAmber().getLocationPlace().getPlaceType()!=PlaceType.ZARANIX_GF_LOUNGE) {
//				return null;
//				
//			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixAmberSubdued)) {
//				if(index==1) {
//					return new ResponseSex("Use Amber", "Have some fun with this fiery maid.",
//							true, false,
//							new SMStanding(
//									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
//									Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.STANDING_SUBMISSIVE))),
//							Amber.AFTER_SEX_VICTORY,
//							UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_SEX")
//							);
//					
//				} else if(index==2) {
//					return new ResponseSex("Submit",
//							"Amber's fiery personality is seriously turning you on. You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with her. Perhaps if you submitted, she'd be willing to fuck you?",
//							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null, null,
//							true, true,
//							new SMStanding(
//									Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.STANDING_DOMINANT)),
//									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
//							Amber.AFTER_SEX_VICTORY,
//							UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_SEX_SUBMIT")
//							);
//					
//				} else if (index == 3) {
//					return new Response("Transformations",
//							"Get Amber to use [amber.her] demonic powers to transform [amber.herself]...",
//							BodyChanging.BODY_CHANGING_CORE){
//						@Override
//						public void effects() {
//							Main.game.saveDialogueNode();
//							BodyChanging.setTarget(Main.game.getAmber());
//						}
//					};
//					
//				} else {
//					return null;
//				}
//				
//			} else {
//				if(index==1) {
//					return new Response("Approach the maid", "Walk up behind the maid. <b>She's sure to notice your approach, which will most likely result in you having to fight her!</b>", LOUNGE_AMBER) {
//						@Override
//						public boolean isCombatHighlight() {
//							return true;
//						}
//						@Override
//						public void effects() {
//							Main.game.getAmber().setPlayerKnowsName(true);
//						}
//					};
//				} else {
//					return null;
//				}
//			}
//		}
//	};
//	
//	public static final DialogueNodeOld LOUNGE_AMBER = new DialogueNodeOld("", "", true) {
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public String getLabel() {
//			return "Lounge";
//		}
//
//		@Override
//		public String getContent() {
//			UtilText.nodeContentSB.setLength(0);
//			
//			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_GREETING"));
//			
//			return UtilText.nodeContentSB.toString();
//		}
//
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			return null;
//		}
//	};
	
}
