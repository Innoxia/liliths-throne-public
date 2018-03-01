package com.lilithsthrone.game.dialogue.places.dominion.zaranixHome;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Amber;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKatherine;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKelly;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMAmberDoggyFucked;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMZaranixCockSucking;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.89
 * @version 0.1.90
 * @author Innoxia
 */
public class ZaranixHomeGroundFloor {
	
	public static void resetHouseAfterLeaving() {
		// Maids:
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixAmberSubdued, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKatherineSubdued, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKellySubdued, false);
		
		Main.game.getAmber().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, true);
		
		((Zaranix)Main.game.getZaranix()).resetBody();
		((Amber)Main.game.getAmber()).resetBody();
		((ZaranixMaidKatherine)Main.game.getKatherine()).resetBody();
		((ZaranixMaidKelly)Main.game.getKelly()).resetBody();
	}
	
	public static final DialogueNodeOld OUTSIDE = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Zaranix's Home";
		}

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixDiscoveredHome)) {
				return "<p>"
							+ "Once more you find yourself standing outside Zaranix's home."
							+ " Much like all the other buildings in this area of the city, the incubi's residence takes the form of a grand, regency-style townhouse."
							+ " Unlike the majority of the other buildings, however, his home has what appears to be a modestly-sized private garden adjoining the property to the right-hand side."
							+ " A cast-iron fence, standing at around eight-feet tall, separates the garden from the street in which you're standing, but despite its height, you're sure that you could climb over it if you wanted to try and sneak inside."
						+ "</p>"
						+ "<p>"
							+ (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixMaidsHostile)
									? "As Zaranix's demonic maids are sure to recognise you on sight, you decide that knocking on the door would be an extremely bad idea."
										+ " You'll have to either sneak in through the garden, or try to kick in the door and storm the place before the maids can call for help."
									:"Then again, you could always just knock on the door like a regular visitor."
										+ " Perhaps you could convince whoever answers to let you see Arthur?")
						+ "</p>";
				
			} else {
				return "<p>"
							+ "Keen to put an end to your lengthy pursuit of Arthur, you once again find yourself travelling through the district of Dominion known as 'Demon Home'."
							+ " Following the directions that you received from Scarlett, you soon locate Zaranix's house, which lies only a little way down the street from your elusive quarry's apartment building."
							+ " With a wry grin, you take a moment to reflect on the fact that Arthur was right under your nose this whole time, before focusing your attention on the building in front of you."
						+ "</p>"
						+ "<p>"
							+ "Much like all the other buildings in this area of the city, Zaranix's home takes the form of a grand, regency-style townhouse."
							+ " Unlike the majority of the other buildings, however, his home has what appears to be a modestly-sized private garden adjoining the property to the right-hand side."
							+ " A cast-iron fence, standing at around eight-feet tall, separates the garden from the street in which you're standing, but despite its height, you're sure that you could climb over it if you wanted to try and sneak inside."
						+ "</p>"
						+ "<p>"
							+ "Then again, you could always just knock on the door like a regular visitor."
							+ " Perhaps you could convince whoever answers to let you see Arthur?"
						+ "</p>";
					
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixMaidsHostile)) {
					return new Response("Knock door", "Zaranix's maids will recognise you on sight, and won't let you in. You'll have to find another way to get inside.", null);
				}
				return new Response("Knock door", "Knock on the door and wait for someone to answer.", OUTSIDE_KNOCK_ON_DOOR) {
					@Override
					public void effects() {
						Main.game.getAmber().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
					}
				};

			} else if (index == 2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly over fence", "Fly over the garden's fence and see if there's a way in through there.", GARDEN_ENTRY) {
						@Override
						public void effects() {
							Main.mainController.moveGameWorld(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_GARDEN_ENTRY, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "A small fence like the one before you is no obstacle for someone who can fly."
										+ " Spreading your wings, you take a little run up before launching yourself into the air."
										+ " Quickly gaining altitude, you wheel around and swoop down into the garden adjoining Zaranix's home."
									+ "</p>");
						}
					};
				}
				return new Response("Climb fence", "Climb over the garden's fence and see if there's a way in through there.", GARDEN_ENTRY) {
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_GARDEN_ENTRY, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
						Main.game.getTextStartStringBuilder().append(
							"<p>"
								+ "Deciding to try and sneak your way into Zaranix's home, you loiter about the fence separating his garden from the public street, waiting for an opportunity to scale the obstacle."
								+ " Once you're sure that nobody is looking your way, you quickly climb up the iron bars of the fence, and, swinging your [pc.legs] over the top, you jump down into the private garden."
							+ "</p>");
					}
				};

			} else if (index == 3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKickedDownDoor)) {
					return new Response("Kick down door", "After your last entrance, the front door has been reinforced. You're unable to enter like this again.", null);
					
				} else if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE) >= PhysiqueLevel.THREE_POWERFUL.getMinimumValue()) {
					return new Response("Kick down door", "Kick down the front door.", ENTRANCE_KICK_DOWN_DOOR) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getAmber().setPlayerKnowsName(true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKickedDownDoor, true);
							Main.mainController.moveGameWorld(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, true);
							Main.game.getAmber().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
						}
					};
				} else {
					return new Response("Kick down door", "You don't think you're strong enough to kick down such a sturdy-looking door. (Requires 35 physique.)", null);
				}
				
			} else if (index == 0) {
				return new Response("Leave", "Turn around and walk away.", DebugDialogue.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_KNOCK_ON_DOOR = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Home";
		}

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor)) {
				return "<p>"
							+ "You decide to try knocking on the door again."
							+ " After all, maybe a more reasonable maid will answer this time?"
							+ " With that hope in your mind, you step forwards and take hold of the ornate brass door knocker, before delivering a sharp rap to alert those inside of your presence."
						+ "</p>"
						+ "<p>"
							+ "After just a few moments, you hear the familiar sound of a lock being slid back, before the heavy oak door swings inwards once again."
							+ " There, standing in the opening, is the same fiery-haired maid that greeted you last time."
						+ "</p>"
						+ "<p>"
							+ "[amber.speech(Eugh! It's <i>you</i> again?! Don't you know when to take a hint? Fuck off already!)]"
							+ " She shouts, before stepping back and grabbing the edge of the door, ready to slam it in your face."
						+ "</p>";
				
			} else {
				return "<p>"
							+ "You decide that the most appropriate course of action is to simply knock on the door and ask to see Arthur."
							+ " Stepping forwards, you take hold of the ornate brass door knocker and deliver a sharp rap to alert those inside of your presence."
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
							+" [amber.speech(And who are <i>you</i> supposed to be? We're not expecting any visitors, and you're wasting my valuable time, so this had better be good!)]"
						+ "</p>"
						+ "<p>"
							+ "You're pretty sure that servants aren't ordinarily supposed to greet visitors like that, but this particular maid doesn't exactly seem to be ordinary."
							+ " As her little wings flutter in impatience, she narrows her piercing, luminescent-amber eyes and glares at you in an intimidating manner."
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor)) {
				if (index == 1) {
					return new Response("Step back", "Step back from the door and think about finding another way in.", OUTSIDE) {
						@Override
						public void effects() {
							Main.game.getAmber().returnToHome();
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "You step back just in time to avoid the door slamming into your face."
										+ " With such a rude and unwelcoming maid in charge of receiving visitors, you're sure that you'll have to think of another way to get into Zaranix's home and find Arthur."
									+ "</p>");
						}
					};

				} else if (index == 2) {
					return new Response("Beg", "Beg the maid to let you in.", OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR,
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null);

				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Arthur", "Ask to see Arthur.", OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKnockedOnDoor, true);
						}
					};
					
				} else if (index == 0) {
					return new Response("Leave", "Say that you've got the wrong house and take your leave.", DebugDialogue.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							Main.game.getAmber().returnToHome();
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKnockedOnDoor, true);
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Deciding that it would be best not to reveal your intentions to someone such as this, you tell the rude succubus that you must have got the wrong house."
										+ " With just an angry click of her tongue as a response, the demon slams the door shut on you."
										+ " With such a rude and unwelcoming maid in charge of receiving visitors, you're sure that you'll have to think of another way to get into Zaranix's home and find Arthur."
									+ "</p>");
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Home";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(I'm here to see Zaranix; he's got a slave by the name of Arthur who I need to talk to,)]"
						+ " you explain, noting how the maid's expression turns from one of annoyance to mild curiosity."
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(My master doesn't receive uninvited guests, so you're not only wasting my time, you're wasting your own as well.)]"
					+ "</p>"
					+ "<p>"
						+ "With that, the maid steps back and grabs the edge of the door, ready to slam it in your face."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Step back", "Step back from the door and think about finding another way in.", OUTSIDE) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "You step back just in time to avoid the door slamming into your face."
									+ " With such a rude and unwelcoming maid in charge of receiving visitors, you're sure that you'll have to think of another way to get into Zaranix's home and find Arthur."
								+ "</p>");
					}
				};

			} else if (index == 2) {
				return new Response("Beg", "Beg the maid to let you in.", OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Home";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Doing the first thing that comes to your mind, you drop down onto your knees and look up at the inhospitable maid."
						+ " The amber glow of her hair and eyes seems to grow even fiercer as her face contorts into one of rage, but before she can open her mouth, you blurt out an apology,"
						+ " [pc.speech(I'm sorry for wasting your time! Please, <i>please</i> can I see Arthur? I've been searching for him for forever! Please!)]"
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(How pathetic,)] the maid sneers down at you, her voice sharp enough to cause you to let out a pleading little whimper,"
						+ " [amber.speech(begging like a dog just to see a slave.)]"
					+ "</p>"
					+ "<p>"
						+ "The demon's voice takes on a different tone as she steps forwards,"
						+ " [amber.speech(I <i>do</i> deserve to have a little fun though."
						+ " I'll let you see my master, who'll then decide if you can meet with Arthur or not, but you'll have to show me that you're prepared to be on your best behaviour."
						+ " Get down on all fours and lick my shoes like the dog you are.)]"
					+ "</p>"
					+ "<p>"
						+ "Still looking up at the sadistic maid, you see a wicked snarl break out across her face."
						+ " She snaps her fingers at you and points to her feet, clearly serious about the demand that she's making."
						+ " It's not too late to stand up and take your leave, but if you wanted to see Arthur without resorting to a more clandestine or violent approach, it looks like you'll have to do as the succubus asks."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back out", "Stand up and step back. You're definitely not willing to do anything like <i>that</i>.", OUTSIDE) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "The thought of licking someone else's shoes is too much for you."
									+ " Standing up, you see the maid's expression turn into one of furious disgust, and you're only just able to step back in time to avoid the door slamming into your face."
									+ " With such a rude and unwelcoming maid in charge of receiving visitors, you're sure that you'll have to think of another way to get into Zaranix's home and find Arthur."
								+ "</p>");
					}
				};

			} else if (index == 1) {
				return new Response("Reluctant lick", "If this is what it's going to take to finally meet Arthur, you suppose that you'll do it, even though you're quite reluctant about the whole thing.",
						OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_RELUCTANT_LICK,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST)), CorruptionLevel.TWO_HORNY, null, null, null) {
					@Override
					public void effects() {
						Main.game.getAmber().setPlayerKnowsName(true);
					}
				};

			} else if (index == 2) {
				return new Response("Eager lick", "Immediately drop down onto all fours and enthusiastically lick the maid's shoes.",
						OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_EAGER_LICK,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST)), CorruptionLevel.FOUR_LUSTFUL, null, null, null) {
					@Override
					public void effects() {
						Main.game.getAmber().setPlayerKnowsName(true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_RELUCTANT_LICK = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Home";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Not seeing any alternative, you hesitantly drop down onto all fours as the maid commands."
						+ " Sensing your reluctance, the succubus growls down to you,"
						+ " [amber.speech(Come on bitch, I haven't got all day!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Ok...)] you mumble, dropping your face down towards her feet."
					+ "</p>"
					+ "<p>"
						+ "[amber.speech('Ok'?!)]"
						+ " the succubus angrily shouts,"
						+ " [amber.speech(Don't you <i>dare</i> 'ok' me again! 'Yes Miss Amber!' is the correct response, now let's try it again shall we?!"
						+ " 'Come on bitch, I haven't got all day!')]"
					+ "</p>"
					+ "<p>"
						+ "You let out a humiliated whimper as you do as you're told, and answer Amber in the proper manner,"
						+ " [pc.speech(Yes Miss Amber!)]"
					+ "</p>"
					+ "<p>"
						+ "An evil, mocking laugh drifts down to you, and, still in full sight of the street behind you, you drop your mouth down to Amber's feet."
						+ " The black high heels that she's wearing are in keeping with the rest of her maid's uniform, with a little lacy white bow adorning each one."
						+ " Deciding to choose her right foot, you bring your [pc.lips+] down to the black leather, breathing in the faint, warm scent of sweat as your nose draws near to her white cotton stockings."
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(Lick it bitch,)] Amber snarls, eager to see you humiliate yourself,"
						+ " [amber.speech(or would you rather not see your little friend after all?)]"
					+ "</p>"
					+ "<p>"
						+ "Deciding that it's best to do this quickly and get it over with, you press your [pc.lips] down against the tip of Amber's shoe, before pushing forwards and dragging your [pc.tongue+] up and over her feminine footwear."
						+ " The bitter, tangy taste of leather, dirt, and shoe polish overwhelms your taste buds, and you struggle to stop yourself from gagging as you draw back,"
							+ " wiping your tongue on your shoulder as you try to remove the horrible taste from your mouth."
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(Hahaha! How pathetic!)] Amber laughs, lifting her foot and using your arm to wipe the saliva clean from her shoe,"
						+ " [amber.speech(Stay there bitch, I'll be back shortly. Oh, and don't even <i>think</i> about standing up, or else the deal's off!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, Amber steps back and slams the door in your face, leaving you down on all fours outside Zaranix's home."
						+(Main.game.getCurrentWeather()==Weather.MAGIC_STORM
							?" Despite the ongoing arcane-storm, it appears as though your display has attracted the attention of a group of passing succubi, and as you find yourself shut out on the doorstep,"
									+ " a cacophony of giggles and mocking comments bursts out behind you."
								+ " Looking to the floor as you do your best to ignore their words, you remind yourself that you're doing all of this in order to finally get hold of Arthur."
							:" It appears as though your display has attracted the attention of several passersby, and as you find yourself shut out on the doorstep,"
									+ " a cacophony of giggles and mocking comments bursts out behind you."
								+ " Looking to the floor as you do your best to ignore their words, you remind yourself that you're doing all of this in order to finally get hold of Arthur.")
						+ " Thankfully, you're not subjected to this humiliation for long, as after just a few moments the door swings open again; the sight of the fiery-haired succubus quickly dispelling your tormentors."
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(Heel.)]"
						+ " Amber commands, snapping her fingers and pointing to her side."
					+ "</p>"
					+ "<p>"
						+ "[pc.thought(Well, I've come this far...)] you think, crawling forwards into the house and allowing Amber to shut the door behind you."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Inside", "Crawl alongside Amber as she leads you into the house.", MEETING_ZARANIX) {
					@Override
					public void effects() {
						Main.game.getAmber().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_EAGER_LICK = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Home";
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "Eager to show your submission to the dominant maid, you immediately drop down on all fours as she commands."
					+ " Pleased with your quick response, the succubus encourages you to continue,"
					+ " [amber.speech(What an obedient bitch! Come on now, get started! I haven't got all day!)]"
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(Yes, right away!)] you respond, moving your face down towards her feet."
				+ "</p>"
				+ "<p>"
					+ "[amber.speech(That's 'Right away Miss Amber' to you!)]"
					+ " the succubus angrily shouts,"
					+ " [amber.speech(Don't you <i>dare</i> talk to me as though you're my equal! Let's try it again shall we?!)]"
				+ "</p>"
				+ "<p>"
					+ "You let out a humiliated whimper as you do as you're told, and answer Amber in the proper manner,"
					+ " [pc.speech(Right away Miss Amber!)]"
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
				return new Response("Wait", "Do as Amber says and wait for her return.", MEETING_ZARANIX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "You remain still as Amber steps back and slams the door in your face, leaving you down on all fours outside Zaranix's home."
									+(Main.game.getCurrentWeather()==Weather.MAGIC_STORM
									?" Despite the ongoing arcane-storm, it appears as though your display has attracted the attention of a group of passing succubi, and as you find yourself shut out on the doorstep,"
											+ " a cacophony of giggles and mocking comments bursts out behind you."
									:" It appears as though your display has attracted the attention of several passersby, and as you find yourself shut out on the doorstep,"
											+ " a cacophony of giggles and mocking comments bursts out behind you.")
									+ " You're not subjected to their humiliating words for long, however, as after just a few moments the door swings open again; the sight of the fiery-haired succubus quickly dispelling your tormentors."
								+ "</p>"
								+ "<p>"
									+ "[amber.speech(Heel.)]"
									+ " Amber commands, snapping her fingers and pointing to her side."
								+ "</p>"
								+ "<p>"
									+ "You immediately crawl forwards into the house, allowing Amber to shut the door behind you."
								+ "</p>");
						Main.game.getAmber().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};

			} else if (index == 2) {
				return new Response("Lick soles", "Don't let Amber get away just yet! You still haven't cleaned the soles of her shoes!",
						OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_EAGER_LICK_SOLES,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST)), CorruptionLevel.FIVE_CORRUPT, null, null, null);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_EAGER_LICK_SOLES = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Home";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You can't help but feel like you haven't done a good enough job, and before Amber can step back, you drop your head to the ground and try to lick at the sides of her shoe's soles,"
						+ " [pc.speech(Please Miss Amber! I haven't finished!)]"
					+ "</p>"
					+ "<p>"
						+ "The sadistic maid lets out another mocking laugh, and, lifting her foot up, pushes the filthy sole of her heel into your face,"
						+ " [amber.speech(Stupid bitch! You'd better make this quick!)]"
					+ "</p>"
					+ "<p>"
						+ "With the angry succubus ramming her heel down against your [pc.lips], you once more obediently do as she commands."
						+ " Trying your best to do the job quickly, you eagerly lick and kiss the undersides of Amber's shoes."
						+ " The salty, grimy taste of dirt fills your mouth as you submissively clean the heel's soles, moaning in delight as their owner continues to sneer and call you degrading names."
					+ "</p>"
					+ "<p>"
						+ "After you've finished with the first shoe, Amber quickly switches you to the next."
						+ " Pushing the dirty underside of her other heel into your face, she snarls,"
						+ " [amber.speech(Pathetic bitch! Come on, you can do better than that!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Yes Miss Amber!)]"
						+ " you reply, letting out whimpering little moans as you obediently lick all the dirt from the soles of her shoes."
					+ "</p>"
					+ "<p>"
						+ "Once she's satisfied that you've done enough, Amber lifts her foot a little higher, before forcing the heel into your mouth."
						+ " After obediently sucking it clean, you lick your lips as Amber pulls back, before forcing you to perform the same treatment on the heel of her other shoe."
					+ "</p>"
					+ "<p>"
						+ "Finally, you're done, and as Amber steps back, you feel thoroughly pleased with your work."
						+ " Both of her shoes are now spotlessly clean, but despite the amount of effort that you put in, the tone of Amber's voice remains as cold as ice as she speaks down to you,"
						+ " [amber.speech(Stay there bitch!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, she steps back and slams the door in your face, leaving you down on all fours outside Zaranix's home."
						+(Main.game.getCurrentWeather()==Weather.MAGIC_STORM
							?" Despite the ongoing arcane-storm, it appears as though your display has attracted the attention of a group of passing succubi, and as you find yourself shut out on the doorstep,"
									+ " a cacophony of giggles and mocking comments bursts out behind you."
							:" It appears as though your display has attracted the attention of several passersby, and as you find yourself shut out on the doorstep,"
									+ " a cacophony of giggles and mocking comments bursts out behind you.")
						+ " You're not subjected to their humiliating words for long, however, as after just a few moments the door swings open again; the sight of the fiery-haired succubus quickly dispelling your tormentors."
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(Heel.)]"
						+ " Amber commands, snapping her fingers and pointing to her side."
					+ "</p>"
					+ "<p>"
						+ "You immediately crawl forwards into the house, allowing Amber to shut the door behind you."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Inside", "Crawl alongside Amber as she leads you into the house.", MEETING_ZARANIX) {
					@Override
					public void effects() {
						Main.game.getAmber().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MEETING_ZARANIX = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Wasting no time, Amber clicks her fingers once more, pointing down at her side as she motions for you to follow her on all fours."
						+ " Eager to get this whole business with Arthur settled once and for all, you obediently do as you're instructed, and crawl alongside the succubus maid as she sets off into the house."
					+ "</p>"
					+ "<p>"
						+ "Your hands and knees are thankfully cushioned by the immaculate red carpet that lines the wide corridor you find yourself travelling down, and as you shuffle along beside Amber, you glance around at your surroundings."
						+ " The house appears to be quite similar to Lilaya's; the opulent decor of gold-framed paintings, marble busts, and crystal chandeliers obviously a trend amongst the wealthy demon elite."
						+ " Although it's quite clear that Zaranix is very well-off, his house is nevertheless a little less grand than Lilaya's, and you once again find yourself marvelling at how fortunate you are to have an aunt like her."
					+ "</p>"
					+ "<p>"
						+ "You're suddenly snapped out of your musings by the sound of a high-pitched giggle."
						+ " Looking across towards the origin of the sound, you see yet another succubus maid; this one with black hair and ivory skin, and wearing a pink maid's uniform."
						+ " She covers her mouth with one hand to stifle her laughter, but too late to avoid attracting the ire of Amber."
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(Be silent Katherine!)]"
						+ " Amber snaps, reprimanding the subordinate maid for her outburst,"
						+ " [amber.speech(If you have time to fritter away by giggling at this pathetic bitch, then you surely have time to go and help Kelly clean the kitchen."
							+ " Once I've finished with bitch here, I'll be coming to inspect you and your sister's work, and if I find so much as a single speck of dust in there, you'll both be spending a night in the kennels."
							+ " <i>Understood?</i>)]"
					+ "</p>"
					+ "<p>"
						+ "[katherine.speech(Yes Miss Amber! Sorry Miss Amber!)] the maid exclaims, before hurrying off in what you assume to be the direction of the kitchen."
					+ "</p>"
					+ "<p>"
						+ "After that brief encounter, you follow Amber a little further down the corridor, before turning off and being led through a heavy oaken door into a grand drawing room."
						+ " Several sofas and chairs are tastefully arranged around a low table in the centre of the room, while numerous bookcases and cabinets line the floral-wallpapered walls."
						+ " The high ceiling is home to yet another extravagant chandelier, and you suddenly feel very small as you crawl alongside the succubus maid."
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(Sit.)] Amber commands, snapping her fingers at the floor beside the central coffee table."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Sit on floor", "Do as Amber commands and sit on the floor.", MEETING_ZARANIX_SIT_FLOOR) {
					@Override
					public void effects() {
						Main.game.getZaranix().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else if (index == 2) {
				return new Response("Sit on sofa", "Disobey Amber and sit on one of the sofas.", MEETING_ZARANIX_SIT_SOFA) {
					@Override
					public void effects() {
						Main.game.getZaranix().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MEETING_ZARANIX_SIT_FLOOR = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(Yes Miss Amber!)] you say, quickly crawling over to the spot that the succubus is pointing at, before sitting down and waiting for her next command."
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(Good bitch,)] Amber says, walking over to the door through which you entered,"
						+ " [amber.speech(Now stay.)]"
					+ "</p>"
					+ "<p>"
						+ "Doing as you're told, your remain in place, the two of you waiting in silence as you anticipate the arrival of Zaranix."
						+ " Afforded a moment in which to take a closer look at Amber, you glance over in her direction."
						+ " Her luminescent hair and eyes are quite obviously the features from which she gets her name, and combined with her almost pitch-black ebony skin, you find yourself comparing her appearance to that of a volcano;"
							+ " an image all the more appropriate considering her fiery personality."
					+ "</p>"
					+ "<p>"
						+ "Obviously hearing her master's approach, she suddenly leans forwards and opens the door."
						+ " As the maid steps aside, the figure of Zaranix strides forwards into the room."
						+ " At well over six feet tall, well dressed in a grey shirt and smart trousers, and with a broad chest and purple-skinned, muscular body, the incubus cuts an impressive figure."
						+ " He strides over towards the sofa opposite to where you're sitting, and, clearly having missed you at first, he does a double-take as he catches sight of you."
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(Hah!)] he cries, not at all in a mean-spirited manner, but in one more of mild surprise,"
						+ " [zaranix.speech(I'll wager this is Amber's doing! Well, if you're comfortable down there, no need to move!)]"
					+ "</p>"
					+ "<p>"
						+ "The huge incubus bounds over to the sofa and collapses down onto it, before throwing you such a disarmingly-friendly smile that you suddenly find yourself a little flustered."
						+ " You were half-expecting Amber's master to be just as harsh as she is, but, much to your surprise, Zaranix seems to be the complete opposite, and cheerily booms,"
						+ " [zaranix.speech(Anyone who manages to get past my little inferno over there is worth every moment of my time! Now, what can Zaranix do for you, "+(Main.game.getPlayer().isFeminine()?"Miss...":"Mr...")+"?)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Hold back", "Simply say that Lilaya wants Arthur back, and avoid telling Zaranix anything about why.", MEETING_ZARANIX_HOLD_BACK);
				
			} else if (index == 2) {
				return new Response("Explain everything", "Tell Zaranix all about your appearance in this world, and how Lilaya needs Arthur's help in order to find out what's going on.", MEETING_ZARANIX_EXPLAIN_EVERYTHING);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MEETING_ZARANIX_SIT_SOFA = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "[pc.speech(I'd rather sit down if I'm going to meet Zaranix,)] you say, quickly standing up and disobediently taking a seat on one of the sofas."
				+ "</p>"
				+ "<p>"
					+ "[amber.speech(Why you <i>insolent bitch</i>,)] Amber shouts, her luminous eyes positively burning with rage as she furiously storms towards you,"
					+ " [amber.speech(how <i>dare</i> yo-)]"
				+ "</p>"
				+ "<p>"
					+ "She suddenly cuts her sentence off and freezes mid-stride."
					+ " Her luminescent hair and eyes still burning with the same fury as that of her volcanic personality, Amber throws one last scowl your way before turning around and rushing over to the door through which you entered."
				+ "</p>"
				+ "<p>"
					+ "Obviously having heard her master's approach, she leans forwards and opens the door just in time to allow the figure of Zaranix to stride forwards into the room."
					+ " At well over six feet tall, well dressed in a grey shirt and smart trousers, and with a broad chest and purple-skinned, muscular body, the incubus cuts an impressive figure."
					+ " He strides over towards the sofa opposite to the one upon which you're sitting, and as he approaches, he looks over in your direction."
				+ "</p>"
				+ "<p>"
					+ "[zaranix.speech(Greetings!)] he cries in a booming voice,"
					+ " [zaranix.speech(I hope Amber hasn't been too much trouble for you!)]"
				+ "</p>"
				+ "<p>"
					+ "The huge incubus bounds over towards you, throwing you such a disarmingly-friendly smile that you suddenly find yourself a little flustered."
					+ " You were half-expecting Amber's master to be just as harsh as she is, but, much to your surprise, Zaranix seems to be the complete opposite, and cheerily booms,"
					+ " [zaranix.speech(Anyone who manages to get past my little inferno over there is worth every moment of my time! Now, what can Zaranix do for you, "+(Main.game.getPlayer().isFeminine()?"Miss...":"Mr...")+"?)]"
				+ "</p>"
				+ "<p>"
					+ "Zaranix steps back and collapses down onto the sofa behind him, eagerly awaiting your response."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Hold back", "Simply say that Lilaya wants Arthur back, and avoid telling Zaranix anything about why.", MEETING_ZARANIX_HOLD_BACK);
				
			} else if (index == 2) {
				return new Response("Explain everything", "Tell Zaranix all about your appearance in this world, and how Lilaya needs Arthur's help in order to find out what's going on.", MEETING_ZARANIX_EXPLAIN_EVERYTHING);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MEETING_ZARANIX_HOLD_BACK = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech([pc.Name],)] you reply, seeing no harm in giving Zaranix your first name, even though you're not planning on telling him everything."
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(Pleased to make your acquaintance [pc.name],)] the incubus says, leaning back on the sofa."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(The reason I'm here is to find Arthur, who I believe is a slave of yours."
							+ " My aunt really needs to see him again in order to carry out some important research,)]"
						+ " you explain, careful to leave out the part about you coming from another dimension,"
						+ " [pc.speech(so if I could buy him from you, both my aunt and I would be extremely grateful.)]"
					+ "</p>"
					+ "<p>"
						+ "Zaranix smiles, but you can tell from the look in his eyes what his words will be before he even says them,"
						+ " [zaranix.speech(I'm afraid Arthur isn't for sale. He's crucial to my own research you see. In fact, that's the whole reason I had him ensl- *Ahem* I mean, that's the whole reason I bought him in the first place."
							+ " After all, his expertise in the field of arcane research is unrivalled, and I'm sure that you'll be the first of many that I have to turn down like this."
							+ " So, I'm afraid my answer is no."
							+ " I'm sorry to disappoint you and your aunt, but unless there's anything else, Amber will show you out now.)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Would you at least be willing to talk to my aunt Lilaya about this?)]"
						+ " you ask, desperate for this not to be the end of your quest."
					+ "</p>"
					+ "<p>"
						+ "Zaranix had started to stand up, but as you ask that question, he immediately sits back down, and you see a flash of concern pass over his handsome face."
						+ " [zaranix.speech(She's called Lilaya? It's a dangerous game, naming yourself after a Lilin's favourite child like that. You should advise your aunt to change it, before it brings down Lyssieth's wrath.)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Erm, that <i>is</i> my aunt,)]"
						+ " you explain,"
						+ " [pc.speech(she used to work with Arthur, and now she needs his help again. I've been trying to track him down for her for quite a while now...)]"
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(Hah!)] zaranix laughs again, but you notice that his cry sounds rather more deflated than before,"
						+ " [zaranix.speech(So your aunt's Lilaya herself, huh? I suppose <i>you're</i> the [pc.race] who's living with her? Yes, I've heard all about you. I may not get out much, but I still keep my ear close to the grapevine.)]"
					+ "</p>"
					+ "<p>"
						+ "With a deep sigh, Zaranix stands up and calls out to Amber,"
						+ " [zaranix.speech(Bring Arthur down here would you, Amber?)]"
					+ "</p>"
					+ "<p>"
						+ "The succubus quickly makes her exit, leaving you alone with the imposing incubus as he starts pacing back and forth,"
						+ " [zaranix.speech(Today's your lucky day I suppose,)]"
						+ " he says,"
						+ " [zaranix.speech(you'll be getting what you're after. I may have wealth and a modicum of power in this city, but having never been recognised by my Lilin mother, I'd be a fool to stand against the wishes of a demon who has."
							+ " And it's not just any demon! Hah! Lyssieth's precious little half-demon herself!)]"
					+ "</p>"
					+ "<p>"
						+ "Clearly a little annoyed by this sudden turn of events, Zaranix can't help but ramble on as you both wait for Arthur to arrive."
						+ " Thankfully, you don't have to listen to too much of the incubus' disgruntled monologue, as after just a couple of minutes, there's a short knock, before the door swings open."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Arthur", "You finally come face-to-face with your elusive quarry.", MEETING_ZARANIX_ARTHUR) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE));
						Main.game.getArthur().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MEETING_ZARANIX_EXPLAIN_EVERYTHING = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech([pc.Name]"+(Main.game.getPlayer().getSurname()!=null && !Main.game.getPlayer().getSurname().isEmpty()?" [pc.surname]":"")+",)]"
						+ " you quickly introduce yourself."
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(Pleased to make your acquaintance,)] the incubus says, leaning back on the sofa as he awaits your reason for visiting."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(It's a bit of a long story, but I'm here to find Arthur, who I believe is a slave of yours."
							+ " It might be a little hard to believe, but I'm not actually from this world...)]"
					+ "</p>"
					+ "<p>"
						+ "You go on to explain everything that's happened to you up until this point, noticing that Zaranix's cheery expression fades away more and more each time you mention anything to do with Lilaya."
						+ " After a short while you come to the end of your tale, and after telling the incubus how you managed to find out where Arthur was from Scarlett, you bring your story to an end,"
						+ " [pc.speech(And so if I could buy Arthur from you, both my aunt and I would be extremely grateful. Without him, we're not going to be able to find out a way for me to get back to my world.)]"
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(Hah!)] zaranix laughs again, but you notice that his cry sounds rather more deflated than the first time you heard it,"
						+ " [zaranix.speech(So <i>you're</i> the [pc.race] who's moved in with Lilaya? Yes, I know who you are. I may not get out much, but I still keep my ear close to the grapevine."
							+ " Your story is fascinating, but whether I chose to believe it or not makes no difference.)]"
					+ "</p>"
					+ "<p>"
						+ "With a deep sigh, Zaranix stands up and calls out to Amber,"
						+ " [zaranix.speech(Bring Arthur down here would you, Amber?)]"
					+ "</p>"
					+ "<p>"
						+ "The succubus quickly makes her exit, leaving you alone with the imposing incubus as he starts pacing back and forth,"
						+ " [zaranix.speech(Today's your lucky day I suppose,)]"
						+ " he says,"
						+ " [zaranix.speech(you'll be getting what you're after. I may have wealth and a modicum of power in this city, but having never been recognised by my Lilin mother, I'd be a fool to stand against the wishes of a demon who has."
							+ " And it's not just any demon! Hah! Lyssieth's precious little half-demon herself!)]"
					+ "</p>"
					+ "<p>"
						+ "Clearly a little annoyed by this sudden turn of events, Zaranix can't help but ramble on as you both wait for Arthur to arrive."
						+ " Thankfully, you don't have to listen to too much of the incubus' disgruntled monologue, as after just a couple of minutes, there's a short knock, before the door swings open."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Arthur", "You finally come face-to-face with your elusive quarry.", MEETING_ZARANIX_ARTHUR) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE));
						Main.game.getArthur().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MEETING_ZARANIX_ARTHUR = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "[arthur.speech(Alright, alright! Bloody hell Amber, would it kill for you to learn some manners?)]"
						+ " the distinctive voice of Arthur, very familiar to you after having met him numerous times at Lily's museum, precedes his entrance into the room."
					+ "</p>"
					+ "<p>"
						+ "As you look over to the door, you can't help but let out a gasp."
						+ " This world's version of Arthur looks <i>exactly</i> the same as the one you used to know."
						+ " His lanky, pale-skinned body, his bright blue eyes, and his mop of messy, dark-brown hair are all exactly as you remember them."
						+ " In fact, the only difference between this Arthur and the one from your world appears to be in his choice of clothing."
						+ " Instead of his usual tweed suit, this Arthur is wearing a lab coat and a pair of safety goggles, revealing the fact that, just like Lilaya, he's not a historian in this world, but an arcane researcher."
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Well, here I am, summoned at your convenience! And, I might add, not at all to mine! How am I ever going to prove to you that making a demonic transformative is impossible if I'm going to be interrupted on a whim?!)]"
						+ " Arthur exclaims, very much in tone that would never suggest he was Zaranix's slave."
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(You won't need to trouble yourself with proving that to me anymore,)]"
						+ " Zaranix replies,"
						+ " [zaranix.speech(if truth be told, you'd already convinced me that it wasn't going to happen after your first day here."
						+ " It's a moot point now anyway. Apparently, Lilaya wants you back. So go on, you're free. Oh, and you have [pc.name] here to thank for tracking you down.)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Bloody hell! And here I was, all ready to resign myself to a life of misery and servitude. But if it's <i>Lilaya</i> who's after me, then maybe the life of a slave doesn't sound so bad after all...)]"
						+ " Arthur looks across at you and smiles,"
						+ " [arthur.speech(If she doesn't blast me to pieces the moment she sees me, then I'll owe you my thanks for my freedom."
							+ " I suppose I'll see you over at her place; I wouldn't want to overstay my welcome here, so I'd best be on my way!"
							+ " Thank you Zaranix for a delightful stay, and, my dear Amber, do try to allow yourself to smile now and again!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, Arthur turns about and practically skips away through the door, promising the momentarily-flustered Amber that he'll show himself out as he makes his exit."
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(That insolent-)] Amber starts, but is quickly cut off by her master."
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(Let him go, Amber, it's not worth getting caught up in the affairs of Lyssieth's favourite child.)]"
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(Yes master,)] Amber replies, before turning her attention towards you,"
						+ " [amber.speech(but <i>this one</i> should at least show [pc.her] gratitude.)]"
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(Well, I can't really demand anything of someone so close to a demon as important as Lilaya, but I <i>did</i> go though a lot of trouble to get hold of Arthur...)]"
						+ " Zaranix smiles, before sitting back down on the sofa and suggestively spreading his legs."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Refuse to perform any sexual favours for Zaranix or Amber and take your leave.",  PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "You're not really too keen on performing any sexual acts with Zaranix or Amber, and so you quickly stand up, before stepping over towards the door,"
									+ " [pc.speech(Thank you for being so understanding, but I really do need to get going now...)]"
								+ "</p>"
								+ "<p>"
									+ "[zaranix.speech(Hrmph... Very well. Amber, please show [pc.name] out,)]"
									+ " Zaranix responds,"
									+ " [zaranix.speech(perhaps we'll meet again some time.)]"
								+ "</p>"
								+ "<p>"
									+ "Amber opens the door and steps out of the room, beckoning for you to follow her."
									+ " As soon as you step back outside into the corridor, she closes the door behind you, before snapping her fingers and pointing to the floor,"
									+ " [amber.speech(You'll leave the same way you entered bitch. Now heel.)]"
								+ "</p>"
								+ "<p>"
									+ "Eager to get this business finished without causing a scene, you do as the fiery maid commands, and obediently drop down onto all fours next to her."
									+ " Amber lets out a derisive laugh, before setting off down the hallway."
									+ " Crawling alongside her, you soon find yourself back at the front door, which Amber opens before you."
								+ "</p>"
								+ "<p>"
									+ "[amber.speech(Good bitch, you were pretty fun,)] she says, her voice softening for the first time since you met her."
									+ " As you crawl back out into the street, Amber leans down to deliver a surprisingly playful slap to your rear end, and with a little laugh, she slams the door shut behind you."
								+ "</p>"
								+ "<p>"
									+ "Standing up, you ignore the amused looks of several passersby as you brush yourself down and continue on your way, letting out a deep sigh of relief now that you've finally got all this business with Arthur resolved."
								+ "</p>");
						Main.game.getArthur().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
					}
				};
				
			} else if (index == 2) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("'Thank' Zaranix", "You're unable to suck Zaranix's cock, as you can't get access to your mouth!", null);
					
				} else {
					return new Response("'Thank' Zaranix", "Show Zaranix how grateful you are. (Suck his cock.)", MEETING_ZARANIX_ARTHUR_THANK_ZARANIX) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getZaranix().isAbleToBeDisplaced(Main.game.getZaranix().getClothingInSlot(InventorySlot.LEG), DisplacementType.PULLS_DOWN, true, true, Main.game.getAmber());
							Main.game.getZaranix().isAbleToBeDisplaced(Main.game.getZaranix().getClothingInSlot(InventorySlot.GROIN), DisplacementType.SHIFTS_ASIDE, true, true, Main.game.getAmber());
							Main.game.getArthur().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
				}
				
			} else if (index == 3) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
						&& (Main.game.getPlayer().hasVagina()
								?!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
								:true)) {
					return new Response("'Thank' Amber", "You're unable to get fucked by Amber, as you can't get access to your asshole"+(Main.game.getPlayer().hasVagina()?" or vagina":"")+"!", null);
					
				} else {
					return new Response("'Thank' Amber", "Show Amber how grateful you are. (Get fucked by Amber, doggy-style.)", MEETING_ZARANIX_ARTHUR_THANK_AMBER) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getAmber().unequipClothingIntoVoid(Main.game.getAmber().getClothingInSlot(InventorySlot.TORSO_UNDER), true, Main.game.getAmber());
							Main.game.getArthur().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MEETING_ZARANIX_ARTHUR_THANK_ZARANIX = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You weren't really expecting it to be so easy to convince Zaranix to part with Arthur, and, feeling more than a little grateful for how reasonable he's been, you decide that you'd better repay him."
						+ " Looking down at his crotch and biting your [pc.lip], you let out a pleading little whine,"
						+ " [pc.speech(I'd like to show you my thanks...)]"
					+ "</p>"
					+ "<p>"
						+ "Zaranix spreads his legs a little wider, grinning at you as he calls out,"
						+ " [zaranix.speech(Amber, why don't you show your new bitch how to thank me in the proper manner!)]"
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(Yes master,)]"
						+ " you hear Amber reply from behind you, and before you can say or do anything else, the firm grip of the succubus' hand closes around the back of your neck."
						+ " With a determined push, Amber forces you down onto your hands and knees, and, not loosening her grip on you for a moment, starts to drag you towards Zaranix."
					+ "</p>"
					+ "<p>"
						+ "You obediently crawl alongside Amber as she leads you between Zaranix's legs, and with one last push, forces your head down into his crotch."
						+ " Letting go of you, Amber then reaches down to her master's groin, before unfastening his trousers and shifting aside his black briefs."
						+ " You can't help but let out a little whine as you suddenly find your face just inches away from Zaranix's huge, purple demon-cock."
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(Open wide, bitch,)]"
						+ " Amber commands, stroking her master's ribbed, tentacle-lined cock to attention, before kneeling down behind you and holding you in place."
						+ " Left with little option but to do as you're told, you prepare to show Zaranix how thankful you are..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Suck cock", "Show Zaranix how good you are at sucking cock.",
						true, true,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getZaranix(), SexPositionSlot.KNEELING_RECEIVING_ORAL_ZARANIX)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.KNEELING_PERFORMING_ORAL_ZARANIX))),
						AFTER_SEX_THANKING_ZARANIX,
						"<p>"
						+ "</p>");
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MEETING_ZARANIX_ARTHUR_THANK_AMBER = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Although Zaranix was ultimately the one responsible for freeing Arthur, you feel like you owe his maid, Amber, a debt of gratitude for allowing you to meet with him in the first place."
						+ " Turning your head to look up at the fiery-haired succubus, you thank her,"
						+ " [pc.speech(Thank you for letting me see Zaranix, Miss Amber!)]"
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(How <i>dare</i> you!)]"
						+ " Amber shouts, her eyes and hair seeming to glow ever brighter as she gets angrier,"
						+ " [amber.speech(Turn around <i>this instant</i> and thank my master instead!)]"
					+ "</p>"
					+ "<p>"
						+ "Before you have a chance to react, Zaranix lets out a laugh,"
						+ " [zaranix.speech(Hah! If you want to thank Amber, then that's fine with me, but I doubt she'll be content with just your words!)]"
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(Master, may I punish this insolent bitch?)]"
						+ " Amber asks, glaring down at you as she speaks."
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(See what I mean?)]"
						+ " Zaranix says to you, before leaning back on the sofa and addressing Amber,"
						+ " [zaranix.speech(You have my permission, my little inferno.)]"
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(<i>Present yourself.</i>)]"
						+ " Amber growls, her voice as cold as ice."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Yes Miss Amber!)]"
						+ " you cry, dropping down onto all fours and shuffling about to present your rear end to her."
						+ " Glancing back over your shoulder, you see Amber pull off her maid dress, revealing her underwear to consist of a red, cupless bra, a tight black underbust corset, and a red, crotchless thong."
					+ "</p>"
					+ "<p>"
						+ "As your [pc.eyes] wander down to her groin, you let out a little gasp."
						+ " Quickly standing to attention as she steps towards you, Amber's huge, ebony, demonic cock is already dripping precum, and as she sees you looking back at her, the succubus growls,"
						+ " [amber.speech(Face-forwards, bitch! And lift that ass! I'll teach you what happens to insolent sluts!)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Lift ass", "Do as Amber commands and lift your ass towards her.",
						true, true,
						new SMAmberDoggyFucked(
								Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.DOGGY_BEHIND_AMBER)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS_AMBER))),
						AFTER_SEX_THANKING_AMBER,
						"<p>"
							+ "You obediently lift your ass towards Amber, letting out a little cry as you suddenly feel the sharp slap of her hand across your right cheek, before she growls out,"
							+ " [amber.speech(Squeal all you want bitch, <i>you're mine now!</i>)]"
						+ "</p>");
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNodeOld AFTER_SEX_THANKING_ZARANIX = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix is finished";
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "Amber pulls you back from Zaranix's lap, grinning down at you,"
					+ " [amber.speech(Good bitch.)]"
				+ "</p>"
				+ "<p>"
					+ "[zaranix.speech(Well, I can't say that I'm too happy at having lost Arthur, but that performance certainly makes me feel a little better about it. Amber, please show [pc.name] out now,)]"
					+ " Zaranix says, before looking down at you,"
					+ " [zaranix.speech(perhaps we'll meet again some time.)]"
				+ "</p>"
				+ "<p>"
					+ "With that, Amber snaps her fingers and points to her side once more,"
					+ " [amber.speech(You'll leave the same way you entered bitch. Now heel.)]"
				+ "</p>"
				+ "<p>"
					+ "Eager to get this business finished without causing a scene, you do as the fiery maid commands, and obediently crawl over next to her."
					+ " Amber lets out a derisive laugh, before leading you back outside into the corridor, and then off down the hallway."
					+ " Crawling alongside her, you soon find yourself back at the front door, which Amber opens before you."
				+ "</p>"
				+ "<p>"
					+ "[amber.speech(Good bitch, you were pretty fun,)] she says, her voice softening for the first time since you met her."
					+ " As you crawl back out into the street, Amber leans down to deliver a surprisingly playful slap to your rear end, and with a little laugh, she slams the door shut behind you."
				+ "</p>"
				+ "<p>"
					+ "Standing up, you ignore the amused looks of several passersby as you brush yourself down and continue on your way, letting out a deep sigh of relief now that you've finally got all this business with Arthur resolved."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue on your journey.",  PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_THANKING_AMBER = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Amber is finished";
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "Amber pulls back from you, letting out a little laugh, as she delivers one last slap to your exposed ass,"
					+ " [amber.speech(Good bitch, you were a pretty good fuck!)]"
				+ "</p>"
				+ "<p>"
					+ "[zaranix.speech(Good job Amber,)]"
					+ " Zaranix calls out from his place on the sofa,"
					+ " [zaranix.speech(Now why don't you show our guest out, then perhaps I'll take you for a ride.)]"
				+ "</p>"
				+ "<p>"
					+ "[amber.speech(~Mmm~ Yes Master!)]"
					+ " Amber replies, standing up and snapping her fingers to her side once again,"
					+ " [amber.speech(You'll leave the same way you entered bitch. Now heel.)]"
				+ "</p>"
				+ "<p>"
					+ "Eager to get this business finished without causing a scene, you quickly gather your things and hurry to do as the fiery maid commands."
					+ " Amber lets out a derisive laugh as you obediently crawl over next to her, before leading you back outside into the corridor, and then off down the hallway."
					+ " Crawling alongside her, you soon find yourself back at the front door, which Amber opens before you."
				+ "</p>"
				+ "<p>"
					+ "[amber.speech(Good bitch, you were pretty fun,)] she says, her voice softening for the first time since you met her."
					+ " As you crawl back out into the street, Amber leans down to deliver a surprisingly playful slap to your rear end, and with a little laugh, she slams the door shut behind you."
				+ "</p>"
				+ "<p>"
					+ "Standing up, you ignore the amused looks of several passersby as you brush yourself down and continue on your way, letting out a deep sigh of relief now that you've finally got all this business with Arthur resolved."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue on your journey.",  PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	
	// Combat route:
	
	public static final DialogueNodeOld ENTRANCE_KICK_DOWN_DOOR = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Entrance Hall";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Glancing around to make sure there aren't any enforcers nearby, you walk up to the front door of Zaranix's home."
						+ " Paying no heed to the fact that it looks to be made of sturdy, solid oak, you lift your [pc.leg] up, and deliver a powerful kick just to one side of the handle."
						+ " A loud, splintering crash accompanies your blow, and the door swings wide open before you."
					+ "</p>"
					+ "<p>"
						+ "Quickly stepping inside, you shut the door behind you, keen to avoid the attention of any passersby."
						+ " You find yourself in a large entrance hall, similar to the one in Lilaya's home, but on a slightly smaller scale."
						+ " A crystal chandelier hangs from the high ceiling, and a little way in front of you, a wide staircase leads to the upper floor."
						+ " To either side of that, a pair of corridors lead off to other parts of the house, and it's from one of those that you hear shouting."
					+ "</p>"
					+ "<p>"
						+ "[katherine.speech(Miss Amber! There was a loud noise at the door!)]"
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(Be quiet Katherine! Get on with your work, I'll deal with this!)]"
					+ "</p>"
					+ (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor)
							?"<p>"
								+ "Before you have the chance to take any action, the fiery-haired succubus maid who answered the door to you enters the hall."
								+ " As her glowing-amber eyes fixate themselves onto yours, 'Miss Amber' screams,"
								+" [amber.speech(You picked the wrong house to try and rob, bitch!)]"
							+ "</p>"
							:"<p>"
								+ "Before you have the chance to take any action, one of the most striking succubi you've ever seen, who you assume to be 'Miss Amber', enters the hall."
								+ " Her gorgeous face and perfectly-proportioned body are covered in pitch-black ebony skin, but it's not her voluptuous figure which your eyes are drawn to."
								+ " Styled into a sidecut, and giving off a luminescent, yellowy-orange glow, it's her extraordinary amber hair that you find yourself gazing at."
								+ " As her eyes fixate themselves onto yours, you see that her irises are the exact same glowing-amber shade as her hair, giving this demon a truly remarkable appearance."
							+ "</p>"
							+ "<p>"
								+ "Despite the fact that her maid's dress quite clearly identifies her as a servant in this household, her attitude is not at all one of subservience, and as she sees you standing inside her master's home, she screams,"
								+" [amber.speech(You picked the wrong house to try and rob, bitch!)]"
							+ "</p>")
					+ "<p>"
						+ "Not even giving you a chance to offer an explanation, the maid launches herself at you in a blind fury!"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight", "Defend yourself against the furious maid!", Main.game.getAmber()) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, true);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	
	// General places:
	
	public static final DialogueNodeOld ENTRANCE = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Entrance Hall";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "The entrance hall to Zaranix's home, while nothing compared to that of Lilaya's, is nonetheless very impressive."
						+ " A huge crystal chandelier casts its bright, arcane-powered light over the entire area, and fine paintings in golden frames hang on each of the surrounding walls."
					+ "</p>"
					+ "<p>"
						+ "The main door to Zaranix's home is made from thick, sturdy oak, and there's nothing stopping you from opening it and taking your leave."
						+ " <b>It looks like the door will be locked behind you if you do this, so be prepared to gain entry all over again if you choose to leave now!</b>"
					+ "</p>");
			
			if(Main.game.getAmber().getLocationPlace().getPlaceType()==PlaceType.ZARANIX_GF_ENTRANCE) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "<i>"
							+ "Amber is slumped down against one wall, and you see her hand furiously pumping away between her legs."
							+ " Her intense lust doesn't look to be in any danger of dying down, and you wonder if you should have a little more fun with her while you have the chance..."
							+ "</i>"
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Exit", "Leave Zaranix's house and head back out into Demon Home. <b>You will have to gain entry all over again if you choose to leave now!</b>", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
						resetHouseAfterLeaving();
					}
				};

			} else if(index==2 && Main.game.getAmber().getLocationPlace().getPlaceType()==PlaceType.ZARANIX_GF_ENTRANCE) {
				return new ResponseSex("Use Amber", "Have some fun with this fiery maid.",
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.STANDING_SUBMISSIVE))),
						Amber.AFTER_SEX_VICTORY,
						"<p>"
							+ "It doesn't look like any of the other maids of the household will interrupt you, so you decide to take this opportunity to have a little fun with Amber."
							+ " Stepping over to where she's sunk down against the wall, you reach forwards and take hold of her arm, before pulling her to her feet."
							+ " Denied the freedom to get herself off, Amber pitifully looks up into your eyes, and instead of fury, you see them filled with burning lust."
						+ "</p>"
						+ "<p>"
							+ "She pushes herself off from the wall, wrapping her arms around your back and desperately pressing her [amber.lips+] against yours."
							+ " You reciprocate the gesture, and after spending a few moments of sliding your tongues into one another's mouths, you pull back, grinning..."
						+ "</p>");
				
			} else if(index==3 && Main.game.getAmber().getLocationPlace().getPlaceType()==PlaceType.ZARANIX_GF_ENTRANCE) {
				return new ResponseSex("Submit",
						"Amber's fiery personality is seriously turning you on. You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with her. Perhaps if you submitted, she'd be willing to fuck you?",
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null, null,
						true, true,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						Amber.AFTER_SEX_VICTORY,
						"<p>"
							+ "Despite her current state, you find yourself incredibly turned on by Amber's dominant, fiery personality."
							+ " Not willing to take the dominant role, but with a deep desire to have sex with the now-very-horny succubus, you walk up to where she's collapsed against the wall, and sigh,"
							+ " [pc.speech(Miss Amber... Erm... If you're feeling a little horny, perhaps you could use me? I mean, I-)]"
						+ "</p>"
						+ "<p>"
							+ "Despite the fact that you're a stranger in her master's house, Amber looks up at you with an intense, burning passion in her eyes."
							+ " Sliding her hand out from under her dress, she jumps to her feet, interrupting your sentence as she grabs your head and pulls you into a desperate kiss."
						+ "</p>"
						+ "<p>"
							+ "You reciprocate the gesture, but only spend a few moments sliding your tongues into one another's mouths before Amber pulls back, moaning,"
							+ " [amber.speech(Good bitch! Fuck... I'm so fucking horny! I <i>need</i> you!)]"
						+ "</p>");
				
			} else if (index == 4 && Main.game.getAmber().getLocationPlace().getPlaceType()==PlaceType.ZARANIX_GF_ENTRANCE) {
				return new Response("Transformations",
						"Get Amber to use [amber.her] demonic powers to transform [amber.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getAmber());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld STAIRS = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Staircase";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<p>"
						+ "Before you, a wide, red-carpeted staircase leads up to the first floor of Zaranix's house."
						+ " On either side, a corridor leads to other parts of the house, and behind you, the entrance hall offers an opportunity to make your exit."
					+ "</p>"
					+ "<p>"
						+ "Much like the rest of the house, the walls surrounding you are home to several fine paintings."
						+ " Well-crafted cabinets, cushioned chairs, and little wooden tables are strategically dotted about here and there, but not so much so as to make the area seem cluttered."
					+ "</p>"
					+ "<p>"
						+ "You could use the stairs here to gain access to the first floor, which is where you're sure to find Zaranix."
					+ "</p>");
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				UtilText.nodeContentSB.append(
						"<p style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"
							+ "<i>"
							+ "You can hear the faint sound of humming coming from down the corridor to your right, and you realise that one of Zaranix's maids must be busy cleaning down there!"
							+ " If you wanted to avoid confrontation, it would be best not to head over there."
							+ "</i>"
						+ "</p>");
			}
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Upstairs", "Head upstairs to the first floor of Zaranix's house.", PlaceType.ZARANIX_FF_STAIRS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_STAIRS, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CORRIDOR = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Corridor";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "You find yourself comparing the corridors of Zaranix's home to those of Lilaya's."
						+ " While they're just as well-furnished as those of your aunt's house, being home to numerous fine painting, cushioned chairs, and well-crafted cabinets, they're not quite as wide nor high."
						+ " Where once you might have been deeply impressed by the extravagant luxury of this incubus's mansion, you now find yourself thinking that it's not quite as grand as the one you live in."
					+ "</p>");
			
			if(Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1) != null
					&& Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1).getPlace().getPlaceType()==PlaceType.ZARANIX_GF_MAID
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				UtilText.nodeContentSB.append(
						"<p style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"
							+ "<i>"
							+ "You can hear the faint sound of humming coming from further down the corridor, and you realise that one of Zaranix's maids must be busy cleaning down there!"
							+ " If you wanted to avoid confrontation, it would be best not to head over there."
							+ "</i>"
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld GARDEN_ENTRY = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Zaranix's Garden";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "A gravel path snakes through the entirety of Zaranix's garden, but, finding that the stones crunch loudly underfoot, you opt to walk on the small grass verge instead so as to remain as quiet as possible."
						+ " To either side of the path, numerous flowerbeds and small glass structures are home to an incredible variety of plant-life, much of which is quite clearly alien to your old world."
					+ "</p>"
					+ "<p>"
						+ "Although small enough not to pose a threat, you see several, tentacle-like varieties squirming and twisting about with a mind of their own; the brightly-coloured flower in the centre of each one looking unmistakably like a vagina."
						+ " Inspecting yet more of the flora as you move along, you notice that some of these tentacled plants have penis-like endings,"
							+ " and you spend a moment reflecting on the fact that even the plantlife in this world is sexual in nature."
						+ " From the variety of herbs, plants, and flowers, and the evidence of careful cultivation techniques, it appears as though this garden is less for show, and more for the practical purpose of gathering arcane ingredients."
					+ "</p>"
					+ "<p>"
						+ "Looking over towards the side of Zaranix's house, you see a couple of glass doors connecting a pair of rooms to the garden in which you find yourself standing."
						+ " You could either make your way over to them and use them to gain entry to the house, or climb back over the fence and return to the street behind you."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly over fence", "Fly over the garden's fence and back out into Demon Home. <b>If you leave, all progress you've made through Zaranix's home will be reset!</b>", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
						@Override
						public void effects() {
							resetHouseAfterLeaving();
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Deciding that you'll come back another time, you take a little run-up down the garden path, before launching yourself into the air."
										+ " Swooping down over the fence, you very quickly find yourself back out in Demon Home once again..."
									+ "</p>");
						}
					};
				}
				return new Response("Climb fence", "Climb over the garden's fence and head back out into Demon Home. <b>If you leave, all progress you've made through Zaranix's home will be reset!</b>", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
					@Override
					public void effects() {
						resetHouseAfterLeaving();
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that you'll come back another time, you climb up and over the fence, quickly finding yourself back out in Demon Home once again..."
								+ "</p>");
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld GARDEN = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Zaranix's Garden";
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "A gravel path snakes through the entirety of Zaranix's garden, but, finding that the stones crunch loudly underfoot, you opt to walk on the small grass verge instead so as to remain as quiet as possible."
					+ " To either side of the path, numerous flowerbeds and small glass structures are home to an incredible variety of plant-life, much of which is quite clearly alien to your old world."
				+ "</p>"
				+ "<p>"
					+ "Although small enough not to pose a threat, you see several, tentacle-like varieties squirming and twisting about with a mind of their own; the brightly-coloured flower in the centre of each one looking unmistakably like a vagina."
					+ " Inspecting yet more of the flora as you move along, you notice that some of these tentacled plants have penis-like endings,"
						+ " and you spend a moment reflecting on the fact that even the plantlife in this world is sexual in nature."
					+ " From the variety of herbs, plants, and flowers, and the evidence of careful cultivation techniques, it appears as though this garden is less for show, and more for the practical purpose of gathering arcane ingredients."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld GARDEN_ROOM = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Garden Room";
		}

		@Override
		public String getContent() { //TODO maid to left
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Connected to the garden by means of a pair of sliding glass doors, the room you find yourself standing in appears to be some sort of utility room."
						+ " A row of cupboards and cabinets line one of the walls, while a long counter-top runs the length of the opposite one."
					+ "</p>"
					+ "<p>"
						+ "A wooden door, opposite to the glass ones that provide access to the garden, connects this room to the rest of the house, and you wonder if you should proceed into the main building itself, or go back out into the garden."
					+ "</p>");
			
			if(Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX()-1, Main.game.getPlayer().getLocation().getY()) != null
					&& Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX()-1, Main.game.getPlayer().getLocation().getY()).getPlace().getPlaceType()==PlaceType.ZARANIX_GF_MAID
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				UtilText.nodeContentSB.append(
						"<p style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"
							+ "<i>"
							+ "You can hear the faint sound of humming coming from the other side of the wooden door, and you realise that one of Zaranix's maids must be out there cleaning the corridor!"
							+ " If you wanted to avoid confrontation, it would be best to head back out into the garden and look for another way in."
							+ "</i>"
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld CORRIDOR_MAID = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Corridor";
		}

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				return "<p>"
							+ "The ivory-skinned succubus, Katherine, is slumped down against the wall."
							+ " Her pink maid's dress is hiked up around her waist, and she lets out moan after lewd moan as with one hand she fingers herself, while the other strokes up and down the length of her throbbing cock."
						+ "</p>"
						+ "<p>"
							+ "It doesn't look like there are any other maids around to interrupt, so you could go ahead and have a little fun with her..."
						+ "</p>";
			
			} else if(Main.game.getKatherine().getFoughtPlayerCount()!=0) {
				return "<p>"
							+ "As you walk down the corridor, an ivory-skinned succubus, wearing a light pink maid's uniform, suddenly stands up from behind a cabinet."
							+ " You instantly recognise her as Katherine, and, once again, she doesn't notice you at first, and continues to swipe her little feather-duster over the top of the wooden furniture, happily humming away as she works."
						+ "</p>"
						+ "<p>"
							+ "Before you have a chance to take any action, she looks up and catches sight of you,"
							+ " [katherine.speech(It's you again! How do you keep on getting in here?! You'll pay for this, you thief! Thief! Thief!)]"
						+ "</p>"
						+ "<p>"
							+ "Although she cries out in alarm, her shouts seem to be more of an accusation than a call for help, and she suddenly launches herself at you, all too eager to show you what she does to uninvited guests in her master's house."
						+ "</p>";
			
			} else {
				return "<p>"
							+ "As you walk down the corridor, an ivory-skinned succubus, wearing a light pink maid's uniform, suddenly stands up from behind a cabinet."
							+ " She doesn't notice you at first, and continues to swipe her little feather-duster over the top of the wooden furniture, happily humming away as she works."
						+ "</p>"
						+ "<p>"
							+ "Before you have a chance to take any action, however, she looks up and catches sight of you,"
							+ " [katherine.speech(Ah! Sorry [pc.miss]! I didn't know we were expecting guests! My name is Katherine, at your service!)]"
						+ "</p>"
						+ "<p>"
							+ "The maid curtsies as she says this, but as she stands up, the initial surprise of finding you wandering the corridors of her master's home wears off, and her eyes narrow down as she looks you up and down,"
							+ " [katherine.speech(Wait a moment... I would have been told if guests were expected...)]"
						+ "</p>"
						+ "<p>"
							+ "The realisation that you're an intruder suddenly dawns on her, and she gasps,"
							+ " [katherine.speech(Eek! That must mean you're a thief! Thief! Thief!)]"
						+ "</p>"
						+ "<p>"
							+ "Although she cries out in alarm, her shouts seem to be more of an accusation than a call for help, and she suddenly launches herself at you, all too eager to show you what she does to uninvited guests in her master's house."
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				if(index==1) {
					return new ResponseSex("Use Katherine", "Have some fun with this maid.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getKatherine(), SexPositionSlot.STANDING_SUBMISSIVE))),
							ZaranixMaidKatherine.AFTER_SEX_VICTORY,
							"<p>"
								+ "It doesn't look like any of the other maids of the household will interrupt you, so you decide to take this opportunity to have a little fun with Katherine."
								+ " Stepping over to where she's sunk down against the wall, you reach forwards and take hold of her arm, before pulling her to her feet."
								+ " Denied the freedom to get herself off, the horny maid looks up into your eyes, and you see them filled with a desperate, burning lust."
							+ "</p>"
							+ "<p>"
								+ "She pushes herself off from the wall, wrapping her arms around your back and desperately pressing her [katherine.lips+] against yours."
								+ " You reciprocate the gesture, and after spending a few moments of sliding your tongues into one another's mouths, you pull back, grinning..."
							+ "</p>");
					
				} else if(index==2) {
					return new ResponseSex("Submit",
							"You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with Katherine. Perhaps if you submitted, she'd be willing to fuck you?",
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null, null,
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getKatherine(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							ZaranixMaidKatherine.AFTER_SEX_VICTORY,
							"<p>"
								+ "Not willing to take the dominant role, but with a deep desire to have sex with the now-very-horny succubus, you walk up to where Katherine's collapsed against the wall, and sigh,"
								+ " [pc.speech(Katherine... Erm... If you're feeling a little horny, perhaps you could use me? I mean, I-)]"
							+ "</p>"
							+ "<p>"
								+ "Despite the fact that you're a stranger in her master's house, Katherine looks up at you with an intense, burning passion in her eyes."
								+ " Sliding her hand out from under her dress, she jumps to her feet, interrupting your sentence as she grabs your head and pulls you into a desperate kiss."
							+ "</p>"
							+ "<p>"
								+ "You reciprocate the gesture, but only spend a few moments sliding your tongues into one another's mouths before Katherine pulls back, moaning,"
								+ " [katherine.speech(Oh yes! Fuck... I'm so fucking horny! I <i>need</i> you!)]"
							+ "</p>");
					
				} else if (index == 3) {
					return new Response("Transformations",
							"Get Katherine to use [katherine.her] demonic powers to transform [katherine.herself]...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(Main.game.getKatherine());
						}
					};
					
				} else {
					return null;
				}
			} else {
				if(index==1) {
					return new ResponseCombat("Fight", "Defend yourself against the furious maid!", Main.game.getKatherine()) {
						@Override
						public void effects() {
							Main.game.getKatherine().setPlayerKnowsName(true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, true);
						}
					};
				} else {
					return null;
				}
			}
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued);
		}
	};
	
	public static final DialogueNodeOld ROOM = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Room";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "The door to this room is locked, and there's no sound of anyone within."
						+ " Knowing that it's sure to just be a dull and uninteresting dead-end, you decide against trying to force the door open, and turn around to continue on your exploration of Zaranix's home."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld LOUNGE = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			if(Main.game.getAmber().getLocationPlace().getPlaceType()!=PlaceType.ZARANIX_GF_LOUNGE) {
				return "<p>"
						+ "You find yourself standing in Zaranix's grand drawing room."
						+ " Several sofas and chairs are tastefully arranged around a low table in the centre of the room, while numerous bookcases and cabinets line the floral-wallpapered walls."
						+ " The high ceiling is home to an exceptionally extravagant chandelier, while the floor underfoot is covered in thick, cream-coloured carpet."
					+ "</p>"
					+ "<p>"
						+ "There doesn't appear to be anyone here right now, so you should probably continue on your way..."
					+ "</p>";
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixAmberSubdued)) {
				return "<p>"
						+ "You find yourself standing in Zaranix's grand drawing room."
						+ " Several sofas and chairs are tastefully arranged around a low table in the centre of the room, while numerous bookcases and cabinets line the floral-wallpapered walls."
						+ " The high ceiling is home to an exceptionally extravagant chandelier, while the floor underfoot is covered in thick, cream-coloured carpet."
					+ "</p>"
					+ "<p>"
						+ "<i>"
						+ "Amber is slumped down against one wall, and you see her hand furiously pumping away between her legs."
						+ " Her intense lust doesn't look to be in any danger of dying down, and you wonder if you should have a little more fun with her while you have the chance..."
						+ "</i>"
					+ "</p>";
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor) || Main.game.getAmber().getFoughtPlayerCount()!=0) {
				return "<p>"
						+ "Carefully opening a heavy oaken door, you find yourself stepping into a grand drawing room."
						+ " Several sofas and chairs are tastefully arranged around a low table in the centre of the room, while numerous bookcases and cabinets line the floral-wallpapered walls."
						+ " The high ceiling is home to an exceptionally extravagant chandelier, while the floor underfoot is covered in thick, cream-coloured carpet."
					+ "</p>"
					+ "<p>"
						+ "The features of this room aren't quite the focus of your attention, however, as near the right-hand wall you see the back of "
							+(Main.game.getAmber().getFoughtPlayerCount()!=0?"Amber, the succubus maid who you've fought here before.":"the same amber-haired succubus who answered the front door.")
						+ " She appears to be busily dusting and polishing several ornamental objects in a display cabinet, and hasn't noticed your entry."
						+ " It might be wise to carefully continue on your way, but if you really wanted to, you could approach the maid and get her notice you..."
					+ "</p>";
				
			} else {
				return "<p>"
							+ "Carefully opening a heavy oaken door, you find yourself stepping into a grand drawing room."
							+ " Several sofas and chairs are tastefully arranged around a low table in the centre of the room, while numerous bookcases and cabinets line the floral-wallpapered walls."
							+ " The high ceiling is home to an exceptionally extravagant chandelier, while the floor underfoot is covered in thick, cream-coloured carpet."
						+ "</p>"
						+ "<p>"
							+ "The features of this room aren't quite the focus of your attention, however, as near the right-hand wall you see the back of a succubus maid."
							+ " Her dark ebony skin matches the black of her maid's uniform, and atop her head, she has the most amazing hair you've ever seen."
							+ " Reaching down to her shoulders, and styled in a sidecut, the maid's amber-coloured hair gives of a bright glow."
						+ "</p>"
						+ "<p>"
							+ "She appears to be busily dusting and polishing several ornamental objects in a display cabinet, and hasn't noticed your entry."
							+ " It might be wise to carefully continue on your way, but if you really wanted to, you could approach the maid and get her notice you..."
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getAmber().getLocationPlace().getPlaceType()!=PlaceType.ZARANIX_GF_LOUNGE) {
				return null;
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixAmberSubdued)) {
				if(index==1) {
					return new ResponseSex("Use Amber", "Have some fun with this fiery maid.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.STANDING_SUBMISSIVE))),
							Amber.AFTER_SEX_VICTORY,
							"<p>"
								+ "It doesn't look like any of the other maids of the household will interrupt you, so you decide to take this opportunity to have a little fun with Amber."
								+ " Stepping over to where she's sunk down against the wall, you reach forwards and take hold of her arm, before pulling her to her feet."
								+ " Denied the freedom to get herself off, Amber pitifully looks up into your eyes, and instead of fury, you see them filled with burning lust."
							+ "</p>"
							+ "<p>"
								+ "She pushes herself off from the wall, wrapping her arms around your back and desperately pressing her [amber.lips+] against yours."
								+ " You reciprocate the gesture, and after spending a few moments of sliding your tongues into one another's mouths, you pull back, grinning..."
							+ "</p>");
					
				} else if(index==2) {
					return new ResponseSex("Submit",
							"Amber's fiery personality is seriously turning you on. You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with her. Perhaps if you submitted, she'd be willing to fuck you?",
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null, null,
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							Amber.AFTER_SEX_VICTORY,
							"<p>"
								+ "Despite her current state, you find yourself incredibly turned on by Amber's dominant, fiery personality."
								+ " Not willing to take the dominant role, but with a deep desire to have sex with the now-very-horny succubus, you walk up to where she's collapsed against the wall, and sigh,"
								+ " [pc.speech(Miss Amber... Erm... If you're feeling a little horny, perhaps you could use me? I mean, I-)]"
							+ "</p>"
							+ "<p>"
								+ "Despite the fact that you're a stranger in her master's house, Amber looks up at you with an intense, burning passion in her eyes."
								+ " Sliding her hand out from under her dress, she jumps to her feet, interrupting your sentence as she grabs your head and pulls you into a desperate kiss."
							+ "</p>"
							+ "<p>"
								+ "You reciprocate the gesture, but only spend a few moments sliding your tongues into one another's mouths before Amber pulls back, moaning,"
								+ " [amber.speech(Good bitch! Fuck... I'm so fucking horny! I <i>need</i> you!)]"
							+ "</p>");
					
				} else if (index == 3) {
					return new Response("Transformations",
							"Get Amber to use [amber.her] demonic powers to transform [amber.herself]...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(Main.game.getAmber());
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Approach the maid", "Walk up behind the maid. <b>She's sure to notice your approach, which will most likely result in you having to fight her!</b>", LOUNGE_AMBER) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getAmber().setPlayerKnowsName(true);
						}
					};
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld LOUNGE_AMBER = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append("<p>"
						+ "Deciding that you'd like to say hello to the succubus, you being to walk towards her."
						+ " As you draw near, she turns her head to one side, obviously having heard your approach as she calls out,"
						+ " [amber.speech(If I have to tell you or your sister to announce your presence with 'Excuse me Miss Amber' <i>one</i> more time, I swear I'll-)]"
					+ "</p>"
					+ "<p>"
						+ "Having mistaken you for one of the other household maids, Amber turns around to face you."
						+ " As she sees who you really are, her sentence is suddenly cut off, and an angry scowl covers her face."
						+ " Her eyes, glowing amber just like her hair, lock onto yours, and she shouts, ");
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor) || Main.game.getAmber().getFoughtPlayerCount()!=0) {
				UtilText.nodeContentSB.append("[amber.speech(You again?! How the fuck did you get in here?! You're in big trouble now!)]");
			} else {
				UtilText.nodeContentSB.append("[amber.speech(Who the fuck are you?! How the fuck did you get in here?! Whoever you are, you're in big trouble now!)]");
			}
			UtilText.nodeContentSB.append("</p>"
					+ "<p>"
						+ "Not even giving you a chance to answer her questions, Amber launches herself at you in a blind fury!"
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight", "Defend yourself against the furious maid!", Main.game.getAmber()) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, true);
					}
				};
			} else {
				return null;
			}
		}
	};
}
