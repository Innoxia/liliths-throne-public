package com.lilithsthrone.game.dialogue.places.dominion.zaranixHome;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKelly;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPositionSlot;
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
public class ZaranixHomeFirstFloor {
	
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
			return "<p>"
						+ "The wide, red-carpeted staircase behind you leads down to the ground floor of Zaranix's house."
						+ " To either side of you, a corridor leads off into other parts of the building, and you wonder which way you should go..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Downstairs", "Head downstairs to the ground floor of Zaranix's house.", PlaceType.ZARANIX_GF_STAIRS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_STAIRS, false);
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
						+ "Much like those found on the ground floor, these corridors are home to numerous fine paintings, cushioned chairs, and well-crafted cabinets."
						+ " Where once you might have been deeply impressed by the extravagant luxury of this incubus's mansion, you now find yourself thinking that it's not quite as grand as Lilaya's."
					+ "</p>");
			
			if(((Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1) != null
					&& Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1).getPlace().getPlaceType()==PlaceType.ZARANIX_FF_MAID)
					
						|| (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1) != null
								&& Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1).getPlace().getPlaceType()==PlaceType.ZARANIX_FF_MAID))
					
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKellySubdued)) {
				UtilText.nodeContentSB.append(
						"<p style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"
							+ "<i>"
							+ "You can hear the faint sound of humming coming from further down the corridor, and you realise that one of Zaranix's maids must be busy cleaning down there!"
							+ " If you wanted to avoid confrontation, it would be best to look for another route to take."
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
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKellySubdued)) {
				return "<p>"
							+ "The ivory-skinned succubus, Kelly, is slumped down against the wall."
							+ " Her pink maid's dress is hiked up around her waist, and she lets out moan after lewd moan as with one hand she fingers herself, while the other strokes up and down the length of her throbbing cock."
						+ "</p>"
						+ "<p>"
							+ "It doesn't look like there are any other maids around to interrupt, so you could go ahead and have a little fun with her..."
						+ "</p>";
			
			} else if(Main.game.getKelly().getFoughtPlayerCount()!=0) {
				return "<p>"
							+ "As you walk down the corridor, an ivory-skinned succubus, wearing a light pink maid's uniform, suddenly stands up from behind a cabinet."
							+ " You instantly recognise her as Kelly, and, once again, she doesn't notice you at first, and continues to swipe her little feather-duster over the top of the wooden furniture, happily humming away as she works."
						+ "</p>"
						+ "<p>"
							+ "Before you have a chance to take any action, she looks up and catches sight of you,"
							+ " [kelly.speech(It's you again! How do you keep on getting in here?! You'll pay for this, you thief! Thief! Thief!)]"
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
							+ " [kelly.speech(Ah! Sorry [pc.miss]! I didn't know we were expecting guests! My name is Kelly, at your service!)]"
						+ "</p>"
						+ "<p>"
							+ "The maid curtsies as she says this, but as she stands up, the initial surprise of finding you wandering the corridors of her master's home wears off, and her eyes narrow down as she looks you up and down,"
							+ " [kelly.speech(Wait a moment... I would have been told if guests were expected...)]"
						+ "</p>"
						+ "<p>"
							+ "The realisation that you're an intruder suddenly dawns on her, and she gasps,"
							+ " [kelly.speech(Eek! That must mean you're a thief! Thief! Thief!)]"
						+ "</p>"
						+ "<p>"
							+ "Although she cries out in alarm, her shouts seem to be more of an accusation than a call for help, and she suddenly launches herself at you, all too eager to show you what she does to uninvited guests in her master's house."
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKellySubdued)) {
				if(index==1) {
					return new ResponseSex("Use Kelly", "Have some fun with this maid.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getKelly(), SexPositionSlot.STANDING_SUBMISSIVE))),
							ZaranixMaidKelly.AFTER_SEX_VICTORY,
							"<p>"
								+ "It doesn't look like any of the other maids of the household will interrupt you, so you decide to take this opportunity to have a little fun with Kelly."
								+ " Stepping over to where she's sunk down against the wall, you reach forwards and take hold of her arm, before pulling her to her feet."
								+ " Denied the freedom to get herself off, the horny maid looks up into your eyes, and you see them filled with a desperate, burning lust."
							+ "</p>"
							+ "<p>"
								+ "She pushes herself off from the wall, wrapping her arms around your back and desperately pressing her [kelly.lips+] against yours."
								+ " You reciprocate the gesture, and after spending a few moments of sliding your tongues into one another's mouths, you pull back, grinning..."
							+ "</p>");
					
				} else if(index==2) {
					return new ResponseSex("Submit",
							"You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with Kelly. Perhaps if you submitted, she'd be willing to fuck you?",
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null, null,
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getKelly(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							ZaranixMaidKelly.AFTER_SEX_VICTORY,
							"<p>"
								+ "Not willing to take the dominant role, but with a deep desire to have sex with the now-very-horny succubus, you walk up to where Kelly's collapsed against the wall, and sigh,"
								+ " [pc.speech(Kelly... Erm... If you're feeling a little horny, perhaps you could use me? I mean, I-)]"
							+ "</p>"
							+ "<p>"
								+ "Despite the fact that you're a stranger in her master's house, Kelly looks up at you with an intense, burning passion in her eyes."
								+ " Sliding her hand out from under her dress, she jumps to her feet, interrupting your sentence as she grabs your head and pulls you into a desperate kiss."
							+ "</p>"
							+ "<p>"
								+ "You reciprocate the gesture, but only spend a few moments sliding your tongues into one another's mouths before Kelly pulls back, moaning,"
								+ " [kelly.speech(Oh yes! Fuck... I'm so fucking horny! I <i>need</i> you!)]"
							+ "</p>");
					
				} else if (index == 3) {
					return new Response("Transformations",
							"Get Kelly to use [kelly.her] demonic powers to transform [kelly.herself]...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(Main.game.getKelly());
						}
					};
					
				} else {
					return null;
				}
			} else {
				if(index==1) {
					return new ResponseCombat("Fight", "Defend yourself against the furious maid!", Main.game.getKelly()) {
						@Override
						public void effects() {
							Main.game.getKelly().setPlayerKnowsName(true);
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
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKellySubdued);
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
	
	public static final DialogueNodeOld ZARANIX_ROOM = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Lab";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "All the other doors that you've passed so far have been pretty nondescript and uninteresting, but as you come to the end of this particular corridor, you see a curious purple flash shine out from beneath the one in front of you."
						+ " Moments later, you hear the voices of two people within, with the first being particularly loud and booming,"
						+ " [zaranix.speech(Damn it Arthur! How many times is this now?! If I wanted a standard transformative potion, I'd go and buy one!)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Bloody hell Zaranix, I mean, how many times do I have to repeat myself? It's just not possible to make a demonic transformation!)]"
					+ "</p>"
					+ "<p>"
						+ "You feel your heart skip a beat as you hear that reply."
						+ " Even if it wasn't for the fact that Zaranix addressed him by name, you've met Arthur numerous times at Lily's museum, and that second voice is undoubtedly his."
						+ " Stepping closer to the door, you hear Zaranix continue,"
						+ " [zaranix.speech(Bah! There must be a way! If anyone can find it, it's you!)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Well, I don't see why you couldn't have just hired me, instead of going to all that trouble to set me up and get me enslaved! Honestly, I don't know why I try at all!)]"
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(As if you'd have agreed to work for me! Hah! And just to remind you, if you don't try your best, I'll have to hand you over to Amber. I'm sure she'd put you to good use!)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Bloody hell. I'm afraid that she's far more than I can handle. No, you keep that delightful little succubus to yourself, and I'll carry on trying to make the impossible, possible.)]"
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(Good.)]"
						+ " Zaranix grunts, signalling the end of their conversation."
					+ "</p>"
					+ "<p>"
						+ "It doesn't sound like either one of them is going to leave this room any time soon, so, reaching out to take hold of the door's handle, you push it open and step inside..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Zaranix's lab", "You find yourself stepping into a laboratory much like that of Lilaya's.", ZARANIX_ROOM_ENTRY) {
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
	
	public static final DialogueNodeOld ZARANIX_ROOM_ENTRY = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Lab";
		}

		@Override
		public String getContent() {
			if(Main.game.getZaranix().getFoughtPlayerCount()!=0) {
				return "<p>"
							+ "Well-lit by a series of arcane-powered lights, and with a series of ceiling-height windows set into the opposite wall, the large room you find yourself stepping into is particularly well-illuminated."
							+ " Numerous bookcases and shelves line the walls to either side of you, and within the main area itself, quite a number of long tables have been covered in all manner of books, bottles, and raw ingredients."
							+ " In fact, the room looks very much like a small-scale version of Lilaya's laboratory, and, again similar to that of your aunt's lab, the sole occupants are this house's master and slave."
						+ "</p>"
						+ "<p>"
							+ "Upon seeing you enter the room, the tall, purple-skinned figure of Zaranix turns towards you, and he shouts out,"
							+ " [zaranix.speech(You again?! Back for more, cock-sucker?!)]"
						+ "</p>"
						+ "<p>"
							+ "This world's version of Arthur is, once again, standing right beside the imposing incubus, and looks <i>exactly</i> the same as the one you used to know."
							+ " His lanky, pale-skinned body, his bright blue eyes, and his mop of messy, dark-brown hair are all exactly as you remember them."
							+ " In fact, the only difference between this Arthur and the one from your world appears to be in his choice of clothing."
							+ " Instead of his usual tweed suit, this Arthur is wearing a lab coat and a pair of safety goggles, revealing the fact that, just like Lilaya, he's not a historian in this world, but an arcane researcher."
						+ "</p>"
						+ "<p>"
							+ "[zaranix.speech(So, you've got a taste for my cock, have you?! Don't worry, I'll be giving you what you want right after I teach you another lesson!)]"
							+ " Zaranix calls out once more, striding towards you with an angry look on his face."
						+ "</p>";
			} else {
				return "<p>"
							+ "Well-lit by a series of arcane-powered lights, and with a series of ceiling-height windows set into the opposite wall, the large room you find yourself stepping into is particularly well-illuminated."
							+ " Numerous bookcases and shelves line the walls to either side of you, and within the main area itself, quite a number of long tables have been covered in all manner of books, bottles, and raw ingredients."
							+ " In fact, the room looks very much like a small-scale version of Lilaya's laboratory, and, again similar to that of your aunt's lab, the sole occupants are this house's master and slave."
						+ "</p>"
						+ "<p>"
							+ "Upon seeing you enter the room, the tall, purple-skinned figure of Zaranix turns towards you, and he shouts out,"
							+ " [zaranix.speech(Who the hell are you?!)]"
						+ "</p>"
						+ "<p>"
							+ "As you look over towards him, you can't help but let out a gasp."
							+ " This world's version of Arthur, standing right beside the imposing incubus, looks <i>exactly</i> the same as the one you used to know."
							+ " His lanky, pale-skinned body, his bright blue eyes, and his mop of messy, dark-brown hair are all exactly as you remember them."
							+ " In fact, the only difference between this Arthur and the one from your world appears to be in his choice of clothing."
							+ " Instead of his usual tweed suit, this Arthur is wearing a lab coat and a pair of safety goggles, revealing the fact that, just like Lilaya, he's not a historian in this world, but an arcane researcher."
						+ "</p>"
						+ "<p>"
							+ "[zaranix.speech(Well?! Explain yourself!)]"
							+ " Zaranix calls out once more, striding towards you with an angry look on his face."
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getZaranix().getFoughtPlayerCount()==0) {
				if(index==1) {
					return new Response("Demand Arthur", "Refuse to tell Zaranix why you're here, and instead simply demand that he hand over Arthur to you.", ZARANIX_ROOM_NO_EXPLANATION) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
					
				} else if(index==2) {
					return new Response("Explain everything", "Tell Zaranix that Lilaya needs Arthur in order to help her unravel the mystery of inter-dimensional travel.", ZARANIX_ROOM_EXPLANATION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE));
							Main.game.getArthur().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
					
				} else {
					return null;
				}
			} else {
				if(index==1) {
					return new ResponseCombat("Fight", "Defend yourself against Zaranix's attack!", Main.game.getZaranix());
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld ZARANIX_ROOM_NO_EXPLANATION = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Lab";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Deciding that it's probably better not to tell this strange demon about Lilaya, you simply stand your ground and demand,"
						+ " [pc.speech(I'm here for Arthur. Hand him over, and I'll be on my way.)]"
					+ "</p>"
					+ "<p>"
						+ "Zaranix lets out a short, booming laugh,"
						+ " [zaranix.speech(Hah! I think not! And how did you even get up here?! I would have been informed if there was a guest, which means you've forced your way in here!)]"
					+ "</p>"
					+ "<p>"
						+ "The incubus's tone grows angrier and angrier, and you see him clench his fist as he comes to a halt in front of you,"
						+ " [zaranix.speech(I'll show you what I do to trespassers!)]"
					+ "</p>"
					+ "<p>"
						+ "Before you have an opportunity to respond, Zaranix launches himself at you, forcing you to defend yourself!"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight", "Defend yourself against Zaranix's attack!", Main.game.getZaranix());
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ZARANIX_ROOM_EXPLANATION = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Lab";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Deciding that it's probably best if you can resolve all of this without resorting to violence, you explain how your aunt is the half-demon Lilaya,"
							+ " and, deciding to leave out the part where you were transported into this world, you tell him how she's in need of Arthur's help."
						+ " You recite your journey up until this point, noticing that each time you mention the name 'Lilaya', Zaranix's angry expression transforms more and more into one of defeat."
						+ " After a short while, you come to the end of your tale, and after telling the incubus how you managed to find out where Arthur was from Scarlett, you bring your story to an end,"
						+ " [pc.speech(And so if I could buy Arthur from you, both my aunt and I would be extremely grateful. Without him, she's not going to be able to solve this problem of hers.)]"
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(Hah!)] Zaranix half-laughs, half-sighs,"
						+ " [zaranix.speech(So <i>you're</i> the [pc.race] who's moved in with Lilaya? Yes, I know who you are. I may not get out much, but I still keep my ear close to the grapevine.)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Bloody hell! Lilaya wants my help does she?)]"
						+ " Arthur interjects,"
						+ " [arthur.speech(The last I heard, oh, what was it now... Oh yes! She was going to 'blast me into a million pieces, or banish me into an alternate dimension' the next time she saw me."
							+ " I mean, considering that nobody's managed to travel between dimensions before, I couldn't quite take her threat seriously, but she <i>did</i> seem to be pretty angry."
							+ " Maybe I'd be better off staying he-)]"
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(Be quiet Arthur!)]"
						+ " Zaranix shouts,"
						+ " [zaranix.speech(You really do drone on!)]"
					+ "</p>"
					+ "<p>"
						+ "Turning back towards you, the incubus continues,"
						+ " [zaranix.speech(Today's your lucky day I suppose... You'll be getting what you're after."
							+ " I may have wealth and a modicum of power in this city, but having never been recognised by my Lilin mother, I'd be a fool to stand against the wishes of a demon who has."
							+ " And it's not just any demon! Hah! Lyssieth's precious little half-demon herself!)]"
					+ "</p>"
					+ "<p>"
						+ "With a defeated sigh, Zaranix steps over to a nearby chair, before sinking down into it and looking over to Arthur,"
						+ " [zaranix.speech(You won't need to trouble yourself with these experiments anymore, I suppose."
							+ " If truth be told, you'd already convinced me making a demonic transformative wasn't going to happen after your first day here."
							+ " It's a moot point now anyway. Lilaya wants you back, so go on, you're free.)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Well, bloody hell! Maybe I'll see you around Zaranix. It wasn't too bad living here, all things considered!)]"
						+ " Arthur says, before turning to you,"
						+ " [arthur.speech(I'll go on ahead, so I suppose I'll see you over at Lilaya's place!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, he steps past you and makes his exit, leaving you alone in the lab with the gloomy-looking incubus."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Zaranix's house.", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "You thank Zaranix for being so reasonable, before turning around and exiting his lab."
									+ " Making your way back through his house, you soon find yourself stepping back out into Demon Home."
									+ " Letting out a deep sigh of relief now that you've finally got all this business with Arthur resolved, you wonder if you should head over to Lilaya's house straight away..."
								+ "</p>");
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
					}
				};
				
			} else if(index==2) {
				return new Response("'Thank' Zaranix", "You feel a little sorry for Zaranix. Perhaps you could offer to give him a blowjob as thanks...", ZARANIX_ROOM_EXPLANATION_THANK_ZARANIX,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING)), CorruptionLevel.TWO_HORNY, null, null, null) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ZARANIX_ROOM_EXPLANATION_THANK_ZARANIX = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Lab";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You weren't really expecting it to be so easy to convince Zaranix to part with Arthur, and, feeling more than a little grateful for how reasonable he's been, you decide that you'd better repay him."
						+ " Looking down at his crotch and biting your [pc.lip], you let out a pleading little whine,"
						+ " [pc.speech(I'd like to show you my thanks...)]"
					+ "</p>"
					+ "<p>"
						+ "Looking up at you with a grin, Zaranix spreads his legs a little wider,"
						+ " [zaranix.speech(Well, if I can get at least a little compensation for losing my slave, I'll gladly take it!)]"
					+ "</p>"
					+ "<p>"
						+ "Letting out a happy little sigh, you drop to your knees in front of the huge incubus, looking up into his eyes as you moan,"
						+ " [pc.speech(Thank you Zaranix!)]"
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
	
	public static final DialogueNodeOld AFTER_SEX_THANKING_ZARANIX = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix is finished";
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "You shuffle back from Zaranix's lap, grinning up at him,"
					+ " [pc.speech(That was fun!)]"
				+ "</p>"
				+ "<p>"
					+ "[zaranix.speech(Well, I can't say that I'm too happy at having lost Arthur, but that performance certainly makes me feel a little better about it,)]"
					+ " Zaranix says, looking down at you,"
					+ " [zaranix.speech(perhaps we'll meet again some time. You'd better be on your way.)]"
				+ "</p>"
				+ "<p>"
					+ "Standing up, you thank Zaranix one last time, before turning around and exiting his lab."
					+ " Making your way back through his house, you soon find yourself stepping back out into Demon Home."
					+ " Letting out a deep sigh of relief now that you've finally got all this business with Arthur resolved, you wonder if you should head over to Lilaya's house straight away..."
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
}
