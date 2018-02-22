package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public class HarpyNestAlexa {
	
	public static final DialogueNodeOld ALEXAS_NEST_EXTERIOR = new DialogueNodeOld("Alexa's nest", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				return "<p>"
						+ "Making your way down one of the the many walkways that criss-cross Dominion's rooftops, you find yourself approaching an exceptionally large Harpy Nest."
						+ " Spanning several buildings, and with an elaborate system of multi-level platforms built on top of each one, you instantly recognise this as a nest of particular importance."
					+ "</p>"
					+ "<p>"
						+ "Due to the ongoing arcane storm, the nest is completed deserted; its residents having retreated down into the safety of the buildings below."
						+ " As yet another flash of arcane lightning illuminates the platforms before you, you notice that there are little signs dotted around the edges of the nest."
						+ " Walking up to the nearest one, you read, in flowing, ornate script, the following words;"
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "<i><b>~Alexa's Paradise~</b></br>"
						+ "Visitors must be accompanied at all times.</br>"
						+ "Meeting with Alexa by appointment only.</i>"
					+ "</p>"
					+ "<p>"
						+ (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_E_REPORT_TO_ALEXA
							?"You do have business with Alexa, and you're sure that she'd want to hear about Scarlett as soon as possible, but you'll have to come back after the ongoing storm has passed."
							:(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_ALEXA)
									?"There's not much point in waiting around for the storm to pass, as Alexa's not even here at the moment."
									:"There's not much point in waiting around for the storm to pass, as you don't have any business with Alexa."))
					+ "</p>";
				
			} else {
				return "<p>"
						+ "Making your way down one of the the many walkways that criss-cross Dominion's rooftops, you find yourself approaching an exceptionally large Harpy Nest."
						+ " Spanning several buildings, and with an elaborate system of multi-level platforms built on top of each one, you instantly recognise this as a nest of particular importance."
					+ "</p>"
					+ "<p>"
						+ (Main.game.isDayTime()
								?"The platforms are covered in countless groups of harpies; lounging around and talking amongst one another, their lazy behaviour is in stark contrast to those flying around in the skies above."
									+ " Looking up, you see a multitude of colours dashing through the sky as yet more of this nest's residents swoop and dart around in the "
										+(Main.game.isMorning()
											?"morning's cool air"
											:"afternoon's warm air")
									+", their energetic dives and loops threatening to make your head spin if you try to watch them for too long."
								:"The platforms are covered in countless groups of harpies; illuminated under soft white lights, you see them lounging around and talking amongst one another,"
										+ " their lazy behaviour in stark contrast to those flying around in the skies above."
									+ " Looking up, you see a multitude of colours dashing through the sky as yet more of this nest's residents swoop and dart around in the cool night air;"
										+ " their feathers lit up by the arcane-powered lights that are covering this nest.")
					+ "</p>"
					+ "<p>"
						+ "Looking back down, you notice that there are little signs dotted around the edges of the platform."
						+ " Walking up to the nearest one, you read, in flowing, ornate script, the following words;"
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "<i><b>~Alexa's Paradise~</b></br>"
						+ "Visitors must be accompanied at all times.</br>"
						+ "Meeting with Alexa by appointment only.</i>"
					+ "</p>"
					+ "<p>"
						+ (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_E_REPORT_TO_ALEXA
							?"Stepping back, you wonder if you should call over one of the nearby harpies."
								+ " After all, you do have business with Alexa, and you're sure that she'd want to hear about Scarlett as soon as possible."
							:"Stepping back, you decide against calling over one of the nearby harpies."
								+(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_ALEXA)
									?" After all, she's not even here at the moment."
									:" After all, you don't have any business with her."))
					+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
					return new Response("Meet with Alexa", "Alexa's flock is taking shelter in the buildings below her nest. You'll have to come back after the arcane storm has passed.", null);
					
				} else {
					if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_E_REPORT_TO_ALEXA) {
						return new Response("Meet with Alexa", "Walk over to the tall platform.", ALEXAS_NEST);
						
					} else if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_ALEXA)) {
						if(Main.game.getAlexa().getLocation().equals(Main.game.getPlayer().getLocation())) {
							return new Response("Meet with Alexa", "You'll be able to interact with Alexa again later! :3", null);
							
						} else {
							return new Response("Meet with Alexa", "Alexa has flown off to Slaver Alley! You'll have to find her there.", null);
						}
						
					}else {
						return new Response("Meet with Alexa", "You have no reason to talk to Alexa.", null);
					}
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALEXAS_NEST = new DialogueNodeOld("Alexa's nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "Stepping forwards, you look around for a harpy who can take you to Alexa."
					+ " You don't need to look for long, however, as the moment you take a step into the nest, a flustered-looking harpy runs up to you."
				+ "</p>"
				+ "<p>"
					+ "[femaleNPC.speech(Stop! Wait! You can't come in here!)]"
					+ " she shouts, waving her pink-feathered wings in front of your face,"
					+ " [femaleNPC.speech(You need an appointment if you want to see Alexa!)]"
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(It's kind of urgent,)]"
					+ " you reply, standing your ground,"
					+ " [pc.speech(I need to talk to Alexa about Scarlett, now.)]"
				+ "</p>"
				+ "<p>"
					+ "A few of the surrounding harpies, obviously listening in to your conversation, suddenly start whispering to one another."
					+ " The harpy that you're dealing with trips over her words as she hurries to respond, obviously caught off-guard at the mention of Scarlett's name,"
					+ " [femaleNPC.speech(W-Wait, Scarlett?! As in, <i>the</i> Scarlett? She's meant to be banished to Slaver Alley!)]"
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(Yeah, that's the one,)]"
					+ " you reply,"
					+ " [pc.speech(so can you take me to Alexa now please?)]"
				+ "</p>"
				+ "<p>"
					+ "[femaleNPC.speech(Yes! Of course!)]"
					+ " the harpy exclaims,"
					+ " [femaleNPC.speech(Thank goodness Alexa's in a good mood... Follow me!)]"
				+ "</p>"
				+ "<p>"
					+ "With that, the harpy hurries off into the nest, and you quickly follow in her footsteps."
					+ " As you pass through the crowds of harpies, you see that you're being led to the tallest platform in the nest, and at the very top, you see a white-feathered harpy sitting on what appears to be some sort of throne."
					+ " You're led up a flight of stairs, and, as you reach the top, you find yourself walking out into the midst of small, and very special, group of harpies."
				+ "</p>"
				+ "<p>"
					+ "As your guide bows down to her superiors, you take a good look at what must be Alexa's inner-circle."
					+ " Each of the harpies before you are jaw-droppingly beautiful; their feminine features and vivid, well-preened feathers leaving you with no doubt that they'd each be capable of being a matriarch of their own nest, if they so pleased."
					+ " Your vision doesn't linger on them for long, however, as your gaze is irresistibly drawn to this nest's matriarch."
				+ "</p>"
				+ "<p>"
					+ "Sitting upright on an ornate golden throne, you find yourself looking upon what surely must be the most beautiful creature to ever have existed."
					+ " Alexa's feathers are the first thing that your eyes are drawn to; their pure white brilliance seems to almost radiate a heavenly glow, and each and every one is perfectly preened and spotlessly clean."
					+ " Her pale skin, much like her feathers, is absolutely flawless, and there isn't the slightest hint of a blemish anywhere on her perfect, hourglass-shaped body."
					+ " The white bikini-top that she's wearing reveals a large portion of her D-cup cleavage, and you can't help but marvel at how perfectly-formed her breasts are."
					+ " As your eyes finally reach the top of her body, you find yourself looking upon the perfectly-symmetrical face of Aphrodite reborn;"
						+ " with high cheekbones, full lips, and, revealed as she takes off her gold-framed aviators, vividly-blue eyes, Alexa is without doubt the most beautiful harpy in the entire nests."
				+ "</p>"
				+ "<p>"
					+ "You barely hear your guide introducing you as the bearer of Scarlett-related news, but as she mentions that name, you pick up on a twitch in Alexa's right eye."
					+ " As you're ushered forwards, Alexa stands up, and, just like her beauty would suggest, she holds herself with bearing and poise befitting a queen."
					+ " She smiles the most beautiful smile you've ever seen, and as she opens her mouth to speak, you discover that her voice is gentle and melodic, but there's a faint, icy undertone that sends a shiver up your spine."
				+ "</p>"
				+ "<p>"
					+ "[alexa.speech(Welcome to my nest, visitor. If I were not so pressed with prior engagements, I would insist upon giving you my full hospitality, but, alas, my time is limited and in short supply."
					+ " If you have news of my <i>dear Scarlett</i>,)]"
					+ " you notice the chill in her voice as she says that name,"
					+ " [alexa.speech(then pray do tell, and I shall be in your debt.)]"
				+ "</p>"
				+ "<p>"
					+ "Alexa bows her head a little as she finishes speaking, and you notice a slight twitch in her right wing; a clear indication that she's impatient to hear what news you have."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Scarlett's woe", "Tell Alexa about Scarlett's failure to run her slavery business.", ALEXAS_NEST_SCARLETT);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALEXAS_NEST_SCARLETT = new DialogueNodeOld("Alexa's nest", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(She wanted me to tell you that her slavery business has failed. She's run out of money, and she has no slaves left.)]"
						+ " you explain, noticing that Alexa's pale cheeks start flushing red as you talk."
						+ " Before you have an opportunity to relay Scarlett's request that you take her punishment for her, Alexa speaks."
					+ "</p>"
					+ "<p>"
						+ "[alexa.speech(That... <i>bitch</i>!)] she exclaims, losing her noble composure as she starts pacing back and forth,"
						+ " [alexa.speech(That <i>stupid slut</i>! I told her this was her last chance!)]"
					+ "</p>"
					+ "<p>"
						+ "The harpy closest to Alexa's throne moves towards her matriarch, seeking to calm her down,"
						+ " [femaleNPC.speech(Alexa, please! Don't get angry, she's not worth it!)]"
					+ "</p>"
					+ "<p>"
						+ "[alexa.speech(Be quiet!)]"
						+ " Alexa screeches, quickly working herself up into a fit as she continues to curse,"
						+ " [alexa.speech(That whore's going to pay for this! How many chances have I given her?! I've had enough!)]"
					+ "</p>"
					+ "<p>"
						+ "It seems as though Alexa's forgotten about your existence, and, ignoring her follower's attempts to calm her down, she moves off to the edge of the platform, spreading her wings as she prepares to take flight."
						+ " You only have a moment in which to try and stop her, and you wonder if you should use this opportunity to offer to take Scarlett's punishment on her behalf..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("No punishment", "Don't take Scarlett's punishment for her.", ALEXAS_NEST_NO_PUNISHMENT) {
					@Override
					public void effects() {
						Main.game.getAlexa().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
					}
				};
				
			} else if(index==2) {
				return new Response("Take punishment", "Offer to take Scarlett's punishment for her.", ALEXAS_NEST_TAKE_PUNISHMENT,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST)),
						CorruptionLevel.THREE_DIRTY,
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.punishedByAlexa);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALEXAS_NEST_NO_PUNISHMENT = new DialogueNodeOld("Alexa's nest", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Deciding that it would be best to avoid Alexa's wrath, you say nothing as she spreads her white-feathered wings, and look on as she launches herself off the platform, flying off in the direction of Slaver Alley."
						+ " You're confident that you did the right thing; after all, who knows what horrific punishment Alexa would have given you."
					+ "</p>"
					+ "<p>"
						+ "Alexa's inner-circle of harpies pay you no heed as their matriarch soars off into the "+(Main.game.isDayTime()?"sky.":"night.")
						+ " They're far too concerned with speculating as to what Alexa is going to do to Scarlett, and as you overhear some of their more frightful ideas,"
							+ " you suddenly remember that you need the abrasive harpy to tell you what happened to Arthur."
					+ "</p>"
					+ "<p>"
						+ "It doesn't look like anyone's going to prevent you from leaving, so you quickly move to make your exit."
						+ " You should probably hurry back to Scarlett's shop and find out what Alexa had in store for her..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Alexa's nest.", ALEXAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Heading down the staircase, you leave your guide to hurry after you, fretting and flapping her wings as she asks you to try and calm Alexa down."
									+ " Going back the same way that you came, it only takes a few moments before you're exiting Alexa's nest, and, leaving the worrisome harpy guide behind, you resolve to travel to Slaver Alley as soon as you can."
								+ "</p>");
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly after her", "Take off and fly after Alexa.", ALEXAS_NEST_TAKE_FLIGHT);
					
				} else {
					return new Response("Fly after her", "You can't fly, so you'll have to travel to Slaver Alley by foot.", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALEXAS_NEST_TAKE_PUNISHMENT = new DialogueNodeOld("Alexa's nest", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(Wait!)]"
						+ " you cry out,"
						+ " [pc.speech(There's something else!)]"
					+ "</p>"
					+ "<p>"
						+ "Alexa lowers her wings and turns towards you,"
						+ " [alexa.speech(What is it?!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Erm, well, I told Scarlett that I'd take her punishment for her...)]"
						+ " you explain, your voice trailing off as Alexa strides towards you."
					+ "</p>"
					+ "<p>"
						+ "[alexa.speech(Oh, well in that case, everything's forgiven!)]"
						+ " she responds, her voice dripping with sarcasm,"
						+ " [alexa.speech(I'll just let Scarlett get away with everything! After all, <i>you're</i> going to take her punishment for her!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(It's just that I-)]"
						+ " you start to speak, but Alexa cuts you off."
					+ "</p>"
					+ "<p>"
						+ "[alexa.speech(Silence! Scarlett's not getting out of this that easily, but seeing as you're so <i>desperate</i> for attention, it's only fair that I deal with you first!)]"
					+ "</p>"
					+ "<p>"
						+ "A deep red glow starts to pulsate around Alexa's wings, and before you get the chance to react to her apparent ability to harness the arcane, she sweeps her arms down."
						+ " Caught off-guard, the powerful wave of force slams you to the ground, then, as you try to struggle to your feet, it pulls you roughly over towards the furious matriarch."
					+ "</p>"
					+ "<p>"
						+ "The sound of mocking laughter echoes all around you as you suddenly find yourself in a rather compromising position."
						+ " Alexa has sat back down on her throne, and, bent over her knee, you feel your exposed [pc.ass] pointing up into the air."
						+ " Her arcane mastery is quite impressive, and you feel your [pc.arms] and [pc.legs] bound by an ethereal force as Alexa prepares to administer her punishment."
					+ "</p>"
					+ "<p>"
						+ "[alexa.speech(You've got my full attention now, sweetheart,)]"
						+ " she whispers down into your [pc.ear],"
						+ " [alexa.speech(let's see if this is really what you wanted!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, Alexa raises her right wing high in the air, and, with a swift thwack, she slaps down on your naked [pc.ass]."
						+ " Normally, a harpy's touch would be quite weak; their delicate wings well-suited for swift, mid-flight movements rather than for administering forceful blows, but Alexa's strike is anything but light."
						+ " A crackle of arcane force accompanies her blow, and you let out an alarmed cry as you feel the stinging pain in your left cheek."
						+ " Your exclamation is met by a chorus of mocking laughter from Alexa's inner-circle, and you hear them encouraging their matriarch to show you no mercy as she raises her wing to strike again..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Endure it", "Try and keep quiet and endure your punishment.", ALEXAS_NEST_TAKE_PUNISHMENT_ENDURE) {
					@Override
					public void effects() {
						Main.game.getAlexa().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
					}
				};
				
			} else if(index==2) {
				return new Response("Struggle", "Start struggling and crying out in discomfort.", ALEXAS_NEST_TAKE_PUNISHMENT_STRUGGLE) {
					@Override
					public void effects() {
						Main.game.getAlexa().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
					}
				};
				
			} else if(index==3) {
				return new Response("Beg for more", "Beg to be punished.", ALEXAS_NEST_TAKE_PUNISHMENT_ENJOY,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST)),
						CorruptionLevel.THREE_DIRTY,
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getAlexa().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALEXAS_NEST_TAKE_PUNISHMENT_ENDURE = new DialogueNodeOld("Alexa's nest", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You scrunch your [pc.eyes] shut and do your best to endure your punishment without complaint."
						+ " Despite your best efforts to stay quiet, however, Alexa's sharp blows occasionally force a little whimper out from between your [pc.lips], much to the amusement of the harpy on-lookers."
					+ "</p>"
					+ "<p>"
						+ "Your public spanking continues for some time; Alexa's wing-like hand alternating from check to cheek with each of her ruthless slaps."
						+ " Eventually, however, she decides that you've had enough, and with a rough shove, pushes you off of her lap."
						+ " The arcane bonds restraining your [pc.arms] and [pc.legs] break, and you reach back to rub at your tender [pc.ass]."
					+ "</p>"
					+ "<p>"
						+ "[alexa.speech(Silly [pc.girl],)]"
						+ " Alexa says, looking down at you,"
						+ " [alexa.speech(but I must commend you for your resolve. I wonder if Scarlett will be even half as well-behaved as you were. Knowing her, I doubt it...)]"
					+ "</p>"
					+ "<p>"
						+ "With that, she once again walks off to the edge of the platform, and, spreading her white-feathered wings, she launches herself into the air, before flying off in the direction of Slaver Alley."
						+ " You feel both sets of your cheeks burning red as you stand up; one from pain, the other from humiliation."
						+ " Despite your discomfort, and although Alexa still seems intent on punishing Scarlett, you're confident that you did the right thing by upholding your end of the bargain."
					+ "</p>"
					+ "<p>"
						+ "It doesn't look like anyone's going to prevent you from leaving, so you quickly move to make your exit."
						+ " You should probably hurry back to Scarlett's shop and find out what Alexa had in store for her..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Alexa's nest.", ALEXAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Heading down the staircase, you leave your guide to hurry after you, fretting and flapping her wings as she asks you to try and calm Alexa down."
									+ " Going back the same way that you came, it only takes a few moments before you're exiting Alexa's nest, and, leaving the worrisome harpy guide behind, you resolve to travel to Slaver Alley as soon as you can."
								+ "</p>");
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly after her", "Take off and fly after Alexa.", ALEXAS_NEST_TAKE_FLIGHT);
					
				} else {
					return new Response("Fly after her", "You can't fly, so you'll have to travel to Slaver Alley by foot.", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALEXAS_NEST_TAKE_PUNISHMENT_STRUGGLE = new DialogueNodeOld("Alexa's nest", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You let out a loud cry as Alexa's wing smacks down yet again on your exposed ass."
						+ " Not giving you a moment's respite, she lifts her wing and delivers a third stinging blow to your [pc.ass+], causing you to squeal in pain."
						+ " Each of her smacks draw out a desperate wail from between your [pc.lips], and, much to the amusement of the harpy on-lookers, you fruitlessly kick and struggle about in the matriarch's lap."
					+ "</p>"
					+ "<p>"
						+ "Your public spanking continues for some time; Alexa's wing-like hand alternating from check to cheek as you continue struggling and shouting."
						+ " Eventually, however, she decides that you've had enough, and with a rough shove, pushes you off of her lap."
						+ " The arcane bonds restraining your [pc.arms] and [pc.legs] break, and with a little whimper, your [pc.hands] dart back to rub at your tender [pc.ass]."
					+ "</p>"
					+ "<p>"
						+ "[alexa.speech(Silly [pc.girl],)]"
						+ " Alexa says, looking down at you,"
						+ " [alexa.speech(I wonder if Scarlett will struggle as much as you did. Knowing her, I'd bet on it...)]"
					+ "</p>"
					+ "<p>"
						+ "With that, she once again walks off to the edge of the platform, and, spreading her white-feathered wings, she launches herself into the air, before flying off in the direction of Slaver Alley."
						+ " You feel both sets of your cheeks burning red as you stand up; one from pain, the other from humiliation."
						+ " Despite your discomfort, and although Alexa still seems intent on punishing Scarlett, you're confident that you did the right thing by upholding your end of the bargain."
					+ "</p>"
					+ "<p>"
						+ "It doesn't look like anyone's going to prevent you from leaving, so you quickly move to make your exit."
						+ " You should probably hurry back to Scarlett's shop and find out what Alexa had in store for her..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Alexa's nest.", ALEXAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Heading down the staircase, you leave your guide to hurry after you, fretting and flapping her wings as she asks you to try and calm Alexa down."
									+ " Going back the same way that you came, it only takes a few moments before you're exiting Alexa's nest, and, leaving the worrisome harpy guide behind, you resolve to travel to Slaver Alley as soon as you can."
								+ "</p>");
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly after her", "Take off and fly after Alexa.", ALEXAS_NEST_TAKE_FLIGHT);
					
				} else {
					return new Response("Fly after her", "You can't fly, so you'll have to travel to Slaver Alley by foot.", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALEXAS_NEST_TAKE_PUNISHMENT_ENJOY = new DialogueNodeOld("Alexa's nest", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You let out a lewd cry as Alexa's wing smacks down yet again on your exposed ass."
						+ " Not giving you a moment's respite, she lifts her wing and delivers a third stinging blow to your [pc.ass+], causing you to squeal in a delightful mixture of pleasure and pain,"
						+ " [pc.speech(Yes! Punish me!)]"
					+ "</p>"
					+ "<p>"
						+ "Alexa leans down, and you hear her icy voice whisper into your ear,"
						+ " [alexa.speech(You horny little thing, are you getting off to this?)]"
					+ "</p>"
					+ "<p>"
						+ "You whimper in the affirmative, and Alexa sits back up, laughing."
						+ " The next blow that lands on your ass is far harsher than the ones before, and, despite your burning arousal, you can't help but let out a whimpering scream."
						+ " Encouraged by the knowledge that you're enjoying this, each of Alexa's smacks grow harder and harder, drawing out desperate wails and pleasurable moans from between your [pc.lips]."
					+ "</p>"
					+ "<p>"
						+ "Your public spanking continues for some time; Alexa's wing-like hand alternating from check to cheek as the harpy onlookers giggle and point at your submissive form."
						+ " Eventually, however, the matriarch decides that you've had enough, and with a rough shove, she pushes you off of her lap."
						+ " The arcane bonds restraining your [pc.arms] and [pc.legs] break, and with a last little whimper, your [pc.hands] dart back to rub at your tender [pc.ass]."
					+ "</p>"
					+ "<p>"
						+ "[alexa.speech(Silly [pc.girl],)]"
						+ " Alexa says, looking down at you,"
						+ " [alexa.speech(I wonder if Scarlett will enjoy her punishment as much as you did. Knowing her, I doubt it...)]"
					+ "</p>"
					+ "<p>"
						+ "With that, she once again walks off to the edge of the platform, and, spreading her white-feathered wings, she launches herself into the air, before flying off in the direction of Slaver Alley."
						+ " You feel both sets of your cheeks burning red as you stand up; one from pain, the other from humiliation."
						+ " Despite your discomfort, and although Alexa still seems intent on punishing Scarlett, you're confident that you did the right thing by upholding your end of the bargain."
					+ "</p>"
					+ "<p>"
						+ "It doesn't look like anyone's going to prevent you from leaving, so you quickly move to make your exit."
						+ " You should probably hurry back to Scarlett's shop and find out what Alexa had in store for her..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Alexa's nest.", ALEXAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Heading down the staircase, you leave your guide to hurry after you, fretting and flapping her wings as she asks you to try and calm Alexa down."
									+ " Going back the same way that you came, it only takes a few moments before you're exiting Alexa's nest, and, leaving the worrisome harpy guide behind, you resolve to travel to Slaver Alley as soon as you can."
								+ "</p>");
						
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly after her", "Take off and fly after Alexa.", ALEXAS_NEST_TAKE_FLIGHT);
					
				} else {
					return new Response("Fly after her", "You can't fly, so you'll have to travel to Slaver Alley by foot.", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALEXAS_NEST_TAKE_FLIGHT = new DialogueNodeOld("Alexa's nest", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "Following in Alexa's footsteps, you hurry to edge of the platform."
					+ " With a flap of your wings, you leap off and take flight, following the speck of white in the distance as Alexa leads you back to Scarlett's shop."
				+ "</p>"
				+ "<p>"
					+ "Somewhere along the way, you lose sight of the harpy matriarch, and it takes you a little while to get your bearings again."
					+ " Soon enough, however, you spot the familiar pattern of Slaver Alley's passages beneath you, and swoop down to land in front of Scarlett's shop."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly("Scarlett's Shop", "You arrive at Scarlett's Shop.") {
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY), PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
}
