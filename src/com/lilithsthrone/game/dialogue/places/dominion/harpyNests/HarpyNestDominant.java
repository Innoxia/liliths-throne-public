package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.8
 * @version 0.1.97
 * @author Innoxia
 */
public class HarpyNestDominant {

	public static final DialogueNodeOld HARPY_NEST_DOMINANT = new DialogueNodeOld("Harpy nest", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}
		
		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos] nest";
		}

		@Override
		public String getContent() {
			if (Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.dominantEncountered)) {
					return "<p>"
								+ "Due to the ongoing arcane storm, [harpyDominant.namePos] nest is completely deserted."
								+ " Her entire flock has retreated into the safety of the upper-floor of the building below, leaving you with no choice but to return at another time if you wanted to speak to her."
							+ "</p>";
				} else {
					return "<p>"
							+ "Due to the ongoing arcane storm, this nest is completely deserted."
							+ " The entire flock has retreated into the safety of the upper-floor of the building below, leaving you with no choice but to return at another time if you wanted to speak to the matriarch of this particular nest."
						+ "</p>";
				}
				
			} else {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.dominantEncountered)) {
					return "<p>"
								+ "You find yourself standing on the outskirts of [harpyDominant.namePos] nest; one of the largest and most populous of all the nests in Dominion."
								+ " A single, gigantic platform spans the roof-tops of several buildings, with little raised podiums scattered about its surface."
								+ (Main.game.isDayTime()
										?""
										:" A series of bright, arcane-powered lights illuminate the entire nest, revealing that the harpies here are just as active at night as they are during daylight hours.")
							+ "</p>"
							+ "<p>"
								+ "Looking closer at the nest's peculiar podiums, you see that there's a red-and-black feathered harpy perched on top of each one."
								+ " Around the base of each one of these podiums, a crowd of harpies can be seen huddling together, and upon further inspection you notice that they're all bowing down in submission."
								+ " Curiously, although you can see harpies of almost every colour, the only ones with red or black feathers seem to be those who are sitting on top of those podiums,"
									+ " leading you to believe that those particular colours designate the important members of this particular flock."
							+ "</p>"
							+ "<p>"
								+ "At the very centre of the platform, you see a podium that's higher than all the rest, and, lounging about on top of it, you see the familiar shape of [harpyDominant.name]."
								+ " The group surrounding her is unlike all the others, as it's made up solely of the same red-and-black feathered harpies that you can see on top of the other podiums."
								+ " Despite their somewhat aggressive appearance, the harpies of this flock don't seem to be too bothered by your presence, allowing you to approach [harpyDominant.name] if you had any business with her."
							+ "</p>";
					
				} else {
					return "<p>"
						+ "You find yourself standing on the outskirts of one of the largest and most populous of all the nests in Dominion."
						+ " A single, gigantic platform spans the roof-tops of several buildings, with little raised podiums scattered about its surface."
						+ (Main.game.isDayTime()
								?""
								:" A series of bright, arcane-powered lights illuminate the entire nest, revealing that the harpies here are just as active at night as they are during daylight hours.")
					+ "</p>"
					+ "<p>"
						+ "Looking closer at the nest's peculiar podiums, you see that there's a red-and-black feathered harpy perched on top of each one."
						+ " Around the base of each one of these podiums, a crowd of harpies can be seen huddling together, and upon further inspection you notice that they're all bowing down in submission."
						+ " Curiously, although you can see harpies of almost every colour, the only ones with red or black feathers seem to be those who are sitting on top of those podiums,"
							+ " leading you to believe that those particular colours designate the important members of this particular flock."
					+ "</p>"
					+ "<p>"
						+ "At the very centre of the platform, you see a podium that's higher than all the rest, and, lounging about on top of it, you see what must be this nest's matriarch."
						+ " The group surrounding her is unlike all the others, as it's made up solely of the same red-and-black feathered harpies that you can see on top of the other podiums."
						+ " Despite their somewhat aggressive appearance, the harpies of this flock don't seem to be too bothered by your presence, allowing you to approach their matriarch if you had any business with her."
					+ "</p>";
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_HARPY_PACIFICATION)) {
					return new Response("Approach [harpyDominant.name]", "You have no need to talk to the matriarch of this nest.", null);
					
				} else if (Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.dominantEncountered)) {
						return new Response("Approach [harpyDominant.name]", "If you want to talk to [harpyDominant.name], you'll have to come back after the arcane storm has passed.", null);
					} else {
						return new Response("Approach matriarch", "If you want to talk to the matriarch, you'll have to come back after the arcane storm has passed.", null);
					}
					
				} else {
					if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.dominantEncountered)) {
						return new Response("Approach [harpyDominant.name]", "Walk to the centre of the nest and talk to [harpyDominant.name].", HARPY_NEST_DOMINANT_APPROACH);
					} else {
						return new Response("Approach matriarch", "Walk to the centre of the nest and talk to the matriarch.", HARPY_NEST_DOMINANT_APPROACH);
					}
				}
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_DOMINANT_APPROACH = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.dominantEncountered)) {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.dominantPacified)) {
					//TODO ordered to stop abuse
					return "<p>"
							+ "Deciding to pay [harpyDominant.Name] another visit, you set off towards the centre podium."
							+ " As you make your way towards the dominant matriarch, you get a good look at the harpies that make up her flock."
							+ " Just as you've seen before, there's a red-and-black-feathered harpy sitting on top of each of the small podiums that are scattered about the area."
						+ "</p>"
						+ "<p>"
							+ "The groups that are huddled around each one of these podiums are, unlike their apparent leaders, made up of all sorts of differently coloured harpies."
							+ " Most of them are on their knees, preening each other's feathers, or talking in hushed tones to one another."
							+ " Those that aren't on their knees are found at the very base of these podiums, and as you pass, you get yet another look at these unfortunate few."
						+ "</p>"
						+ "<p>"
							+ "Obviously having displeased their leader in some way, these harpies have been stripped naked and locked into wooden stocks."
							+ " Some of the flock are taking full advantage of their compromised position, and as you pass each podium, you see all manner of public humiliation taking place."
							+ " From light spanking and tickling, all the way up to rough group sex, these harpies are being used and abused by rest of their flock."
						+ "</p>"
						+ "<p>"
							+ "You don't have much time to dwell on any thoughts about this, however, as before you know it, you've reached the centre of the platform."
							+ " Although larger and made up solely of the red-and-black 'leader' harpies, the group surrounding [harpyDominant.NamePos] podium is similar to all those that you've previously passed."
							+ " Most are on their knees, talking to one another, but there are several others, not all of them red-and-black, who are locked up in this podium's stocks."
						+ "</p>"
						+ "<p>"
							+ "As you approach [harpyDominant.Name], your eyes are drawn to the latest of [harpyDominantCompanion.namePos] victims in the stocks in front of you."
							+ " She's moaning and squealing out loud; the cause of her erotic noises taking the form of a very feminine-looking harpy thrusting away at her exposed rear end."
							+ " Coming to a halt in front of the podium, you hear [harpyDominantCompanion.name] growling out,"
							+ " [harpyDominantCompanion.speech(Fucking slut! That's right! Moan for me, whore!)]"
						+ "</p>"
						+ "<p>"
							+ "Just as you're about to say something, [harpyDominant.Name], only just noticing your presence, calls out,"
							+ " [harpyDominant.speech("+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+"! You're back!)]"
						+ "</p>"
						+ "<p>"
							+ "Jumping down off of her podium, [harpyDominant.name] falls to her knees before you."
							+ " Noticing what's going on, [harpyDominantCompanion.name] lets out a little squeak, and, pulling out of her fuck-toy, jumps down onto her knees beside her matriarch."
							+ " Shuffling forwards, [harpyDominant.Name] bows her head, before asking,"
							+ " [harpyDominant.speech(What can I do for you "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+"?!)]"
						+ "</p>";
					
				} else {
					return "<p>"
								+ "Deciding to pay [harpyDominant.Name] another visit, you set off towards the centre podium."
								+ " As you make your way towards the dominant matriarch, you get a good look at the harpies that make up her flock."
								+ " Just as you've seen before, there's a red-and-black-feathered harpy sitting on top of each of the small podiums that are scattered about the area."
							+ "</p>"
							+ "<p>"
								+ "The groups that are huddled around each one of these podiums are, unlike their apparent leaders, made up of all sorts of differently coloured harpies."
								+ " Most of them are on their knees, preening each other's feathers, or talking in hushed tones to one another."
								+ " Those that aren't on their knees are found at the very base of these podiums, and as you pass, you get yet another look at these unfortunate few."
							+ "</p>"
							+ "<p>"
								+ "Obviously having displeased their leader in some way, these harpies have been stripped naked and locked into wooden stocks."
								+ " Some of the flock are taking full advantage of their compromised position, and as you pass each podium, you see all manner of public humiliation taking place."
								+ " From light spanking and tickling, all the way up to rough group sex, these harpies are being used and abused by rest of their flock."
							+ "</p>"
							+ "<p>"
								+ "You don't have much time to dwell on any thoughts about this, however, as before you know it, you've reached the centre of the platform."
								+ " Although larger and made up solely of the red-and-black 'leader' harpies, the group surrounding [harpyDominant.NamePos] podium is similar to all those that you've previously passed."
								+ " Most are on their knees, talking to one another, but there are several others, not all of them red-and-black, who are locked up in this podium's stocks."
							+ "</p>"
							+ "<p>"
								+ "As you approach [harpyDominant.Name], your eyes are drawn to the latest of [harpyDominantCompanion.namePos] victims in the stocks in front of you."
								+ " She's moaning and squealing out loud; the cause of her erotic noises taking the form of a very feminine-looking harpy thrusting away at her exposed rear end."
								+ " Coming to a halt in front of the podium, you hear [harpyDominantCompanion.name] growling out,"
								+ " [harpyDominantCompanion.speech(Fucking slut! That's right! Moan for me, whore!)]"
							+ "</p>"
							+ "<p>"
								+ "Just as you're about to say something, [harpyDominant.Name], up until now having ignored your presence, calls out,"
								+ (Main.game.getPlayer().getFemininityValue()>Femininity.FEMININE_STRONG.getMinimumFemininity()
										?" [harpyDominant.speech([harpyDominantCompanion.Name]! That hungry slut is back for more of our attention!)]"
										:" [harpyDominant.speech([harpyDominantCompanion.Name]! That bitch is back for more of our attention!)]")
							+ "</p>"
							+ "<p>"
								+ "[harpyDominantCompanion.speech(Yes, [harpyDominant.name]!)] the harpy calls out, before growling at her victim. [harpyDominantCompanion.speech(Once I'm done with this, I'm using your ass next!)]"
							+ "</p>"
							+ "<p>"
								+ "Standing up on top of her podium, [harpyDominant.name] growls down at you, "
								+ (Main.game.getPlayer().getFemininityValue()>Femininity.FEMININE_STRONG.getMinimumFemininity()
										?"[harpyDominant.speech(So?! What do you want, whore?!)]"
										:"[harpyDominant.speech(So?! What do you want, bitch?!)]")
							+ "</p>";
				}
			
			} else {
				return "<p>"
							+ "Recognising this as one of the nests that you agreed to pacify, you start walking towards the centre podium."
							+ " As you make your way towards the matriarch, you get a good look at the harpies that reside in this particular nest."
							+ " Just as you saw from the outskirts, there's a red-feathered harpy sitting on top of each of the small podiums that are scattered about the area."
							+ " Each one has a plume of jet-black feathers on top of their heads, and the skin that covers their human parts is exclusively a dark ebony colour."
						+ "</p>"
						+ "<p>"
							+ "The groups that are huddled around each one of these podiums are, unlike their apparent leaders, made up of all sorts of differently coloured harpies."
							+ " Most of them are on their knees, preening each other's feathers, or talking in hushed tones to one another."
							+ " Those that aren't on their knees are found at the very base of these podiums, and as you get closer, you find yourself staring, wide-eyed, at these unfortunate few."
						+ "</p>"
						+ "<p>"
							+ "Obviously having displeased their leader in some way, these harpies have been stripped naked and locked into wooden stocks."
							+ " Some of the flock are taking full advantage of their compromised position, and as you pass each podium, you see all manner of public humiliation taking place."
							+ " From light spanking and tickling, all the way up to rough group sex, these harpies are being used and abused by rest of their flock."
						+ "</p>"
						+ "<p>"
							+ "You don't have much time to dwell on any thoughts about this, however, as before you know it, you've reached the centre of the platform."
							+ " Although larger and made up solely of the red-and-black 'leader' harpies, the group surrounding the matriarch's podium is similar to all those that you've previously passed."
							+ " Most are on their knees, talking to one another, but there are several others, not all of them red-and-black, who are locked up in this podium's stocks."
						+ "</p>"
						+ "<p>"
							+ "As you approach the matriarch, your eyes are drawn to a particular pink-feathered harpy in the stocks in front of you."
							+ " She's moaning and squealing out loud; the cause of her erotic noises taking the form of a very feminine-looking harpy thrusting away at her exposed rear end."
							+ " Coming to a halt in front of the podium, you hear the dominant harpy growling out,"
							+ " [harpyDominantCompanion.speech(That's right, bitch! I knew you'd end up loving it eventually!)]"
						+ "</p>"
						+ "<p>"
							+ "Just as you're about to say something, the matriarch, up until now having ignored your presence, calls out,"
							+ (Main.game.getPlayer().getFemininityValue()>Femininity.FEMININE_STRONG.getMinimumFemininity()
									?" [harpyDominant.speech([harpyDominantCompanion.Name]! You can finish with your fuck-toy later! There's another hungry slut right here that needs our attention!)]"
									:" [harpyDominant.speech([harpyDominantCompanion.Name]! You can finish with your fuck-toy later! There's some bitch here that needs our attention!)]")
						+ "</p>"
						+ "<p>"
							+ "[harpyDominantCompanion.speech(Yes, [harpyDominant.name]!)] the harpy calls out, before growling at her victim. [harpyDominantCompanion.speech(Don't think this is over whore! I'm gonna use your ass next time!)]"
						+ "</p>"
						+ "<p>"
							+ "As the matriarch, [harpyDominant.Name], stands up on top of her podium, you finally get a good look at her."
							+ " Just like the other harpy leaders that you've seen, the feathers covering [harpyDominant.NamePos] wings and tail-feathers are bright red,"
								+ " and instead of hair, she's got a long plume of jet-black feathers on top of her head."
							+ " Her face, one the most beautiful that you've ever seen, is covered in dark, ebony skin, just like the rest of her human body parts."
							+ " She has average-sized breasts for a harpy, being B-cups, which are covered by her silky black plunge dress."
						+ "</p>"
						+ "<p>"
							+ "As you're taking in her gorgeous appearance, she growls down at you, "
							+ (Main.game.getPlayer().getFemininityValue()>Femininity.FEMININE_STRONG.getMinimumFemininity()
									?"[harpyDominant.speech(So?! What do you want, whore?!)]"
									:"[harpyDominant.speech(So?! What do you want, bitch?!)]")
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.dominantPacified)) {
				if (index == 1) {
					return new ResponseSex("Sex", "Have dominant sex with [harpyDominant.name].",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getHarpyDominant(), SexPositionSlot.STANDING_SUBMISSIVE))),
							HARPY_NEST_DOMINANT_AFTER_SEX,
							"<p>"
								+ "Eager to put [harpyDominant.name] in her place in front of her flock, you reach down and grab her wings."
								+ " Pulling her to her feet, you step forwards, before planting a deep kiss on her [harpyDominant.lips+]."
							+ "</p>"
							+ "<p>"
								+ "[harpyDominant.Name] responds to your dominant move by letting out a submissive little whine, and, wrapping her wings around your back, she passionately starts returning your kiss..."
							+ "</p>");
						
				} else if (index == 0) {
					return new Response("Leave", "Decide to leave [harpyDominant.namePos] nest.", HARPY_NEST_DOMINANT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Deciding that you don't want to talk to [harpyDominant.name] right now, you turn around and take your leave."
										+ " You hear [harpyDominant.name] sighing to [harpyDominantCompanion.name] as you walk away,"
										+ " [harpyDominant.speechNoEffects(Aww! I was hoping for some fun...)]"
									+ "</p>"
									+ "<p>"
										+ "Ignoring her words, you continue back across the platform, quickly finding yourself once again on the outskirts of the nest."
									+ "</p>");
						}
					};
						
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Talk", "Try to convince [harpyDominant.name] to calm down.", HARPY_NEST_DOMINANT_TALK) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.dominantEncountered);
						}
					};
						
				} else if (index == 2) {
					return new Response("Usurp throne", "How <i>dare</i> she speak to you like that! It's time to show her who's really in control here!", HARPY_NEST_DOMINANT_QUEEN,
							Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null, null, Femininity.FEMININE_STRONG, null) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.dominantEncountered);
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.dominantPacified);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.HARPY_MATRIARCH_DOMINANT_PERFUME), false, true));
							
							if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_ONE) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_TWO));
								
							} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_TWO) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_THREE));
								
							} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_THREE) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_REWARD));
							}
						}
					};
						
				} else if (index == 3) {
					return new Response("Call her ugly", "You know that this would be a terrible idea...", HARPY_NEST_DOMINANT_UGLY) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.dominantEncountered);
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
	
				} else if (index == 0) {
					return new Response("Leave", "Decide to leave [harpyDominant.namePos] nest.", HARPY_NEST_DOMINANT) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.dominantEncountered);
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Deciding that now isn't the best time to be confronting [harpyDominant.name], you turn around and hurry away from the podium."
										+ " You hear [harpyDominant.name] growling to [harpyDominantCompanion.name] as you leave,"
										+ " [harpyDominant.speechNoEffects(What the fuck was that all about?! Whatever! [harpyDominantCompanion.Name], you can carry on playing with your fuck-toy now...)]"
									+ "</p>"
									+ "<p>"
										+ "Ignoring her words, you continue back across the platform, quickly finding yourself once again on the outskirts of the nest."
									+ "</p>");
						}
					};
						
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_DOMINANT_TALK = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "[pc.speech(I just want to talk to you about the recent unrest in the Harpy Nests. The enforcers are having a hard time keeping the peace, and it appears as though some of your flock are to blame,)]"
					+ " you try to explain, but [dominantHarpy.name] stomps her clawed foot on the ground, before cutting you off with an angry grunt."
				+ "</p>"
				+ "<p>"
					+ "[dominantHarpy.speech(What the fuck?! How <i>dare</i> you come here and talk to me like that!)]"
					+ " she shouts, flapping her wings in irritation as she continues,"
					+ " [dominantHarpy.speech(Unless you want to become [dominantHarpyCompanion.namePos] next fuck-toy, you'd better fuck off right now! And be thankful that I'm in a good mood!)]"
				+ "</p>"
				+ "<p>"
					+ "It doesn't look like [dominantHarpy.name] is going to listen to you."
					+ " You'll either have to think of another way to convince these harpies to calm down, or make them calm down by using force."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Usurp throne", "Your patience has run out. It's time to show this bitch who's really in control here!", HARPY_NEST_DOMINANT_QUEEN,
						Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null, null, Femininity.FEMININE_STRONG, null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.dominantPacified);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.HARPY_MATRIARCH_DOMINANT_PERFUME), false, true));
						
						if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_ONE) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_TWO));
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_TWO) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_THREE));
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_THREE) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_REWARD));
						}
					}
				};
					
			} else if (index == 2) {
				return new Response("Force compliance", "If you want these harpies to chill out, it looks as though you'll have to do it by force...", HARPY_NEST_DOMINANT_FIGHT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
					
			} else if (index == 0) {
				return new Response("Leave", "Decide to leave [harpyDominant.namePos] nest.", HARPY_NEST_DOMINANT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that now isn't the best time to be confronting [harpyDominant.name], you turn around and hurry away from the podium."
									+ " You hear [harpyDominant.name] growling to [harpyDominantCompanion.name] as you leave,"
									+ " [harpyDominant.speechNoEffects(What the fuck was that all about?! Whatever! [harpyDominantCompanion.Name], you can carry on playing with your fuck-toy now...)]"
								+ "</p>"
								+ "<p>"
									+ "Ignoring her words, you continue back across the platform, quickly finding yourself once again on the outskirts of the nest."
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_DOMINANT_UGLY = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "A devious retort suddenly jumps into your head, and before you know what you're doing, you're speaking out loud,"
					+ " [pc.speech(What I want is to never have to look at your ugly face ever again!)]" //Innoxia's next-level witticisms!
				+ "</p>"
				+ "<p>"
					+ "[harpyDominant.Name] lets out a furious scream, and, flapping her wings, she shouts out to her companion, "
					+ "[harpyDominant.speech([harpyDominantCompanion.Name]! Fuck [pc.herHim] up! Beat [pc.herHim] into submission! That fucking bitch! Get [pc.herHim]!)]"
				+ "</p>"
				+ "<p>"
					+ "Looking almost as furious as [harpyDominant.name] does, [harpyDominantCompanion.name] jumps forwards, eager to please her matriarch as she launches into a furious assault."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "[harpyDominantCompanion.Name] rushes to do her matriarch's bidding!", Main.game.getHarpyDominantCompanion());
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_DOMINANT_QUEEN = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(If you <i>ever</i> talk to me like that again, I'll have your little pet here use you as her next toy! And, speaking of you,)]"
						+ " you shout, looking at [harpyDominantCompanion.Name],"
						+ " [pc.speech(you'd better have a <i>damn</i> good reason why you aren't kneeling to your new queen!)]"
					+ "</p>"
					+ "<p>"
						+ "From the moment you set foot in [harpyDominant.namePos] nest, you could tell that your beautiful features and potent arcane aura were having a strong effect on her flock."
						+ " Now, speaking in the same dominant manner as their matriarch, you hear the murmurs of unrest rising up around you."
						+ " Not wanting to anger you, [dominantHarpyCompanion.name] suddenly rushes forwards, before dropping to her knees before you."
					+ "</p>"
					+ "<p>"
						+ "[harpyDominant.speech([harpyDominantCompanion.Name]! You insolent bitch! Get back here, <i>right this instant</i>!)] [harpyDominant.name] screams."
					+ "</p>"
					+ "<p>"
						+ "[harpyDominantCompanion.Name] shuffles closer to you, letting out a little whine as she bows her head in submission."
						+ " [harpyDominantCompanion.speech(M-"+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+", I'll do whatever you want!)]"
					+ "</p>"
					+ "<p>"
						+ "Not quite knowing how to react, [harpyDominant.name] watches in disbelief as the rest of the harpies follow [harpyDominantCompanion.namePos] lead."
						+ " As one, they flock towards you, bowing their heads in recognition that you're a more powerful and, crucially, more attractive leader than their current matriarch."
					+ "</p>"
					+ "<p>"
						+ "Witnessing the loss of her flock, [harpyDominant.name] suddenly jumps down from her podium."
						+ " Storming towards you, she angrily grabs the neck of [harpyDominantCompanion.namePos] dress, and with a violent yank, pulls her away from you."
						+ " Just as you're about to order your new followers to defend you, [harpyDominant.name] jumps forwards, throwing herself down onto her knees before you."
					+ "</p>"
					+ "<p>"
						+ "[harpyDominant.speech("+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+"!)] [harpyDominant.name] shouts."
						+ " [harpyDominant.speech(I'll keep your flock in-line for you! Please, "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+", let me be of use to you!)]"
					+ "</p>"
					+ "<p>"
						+ "You can't help but let out a mocking laugh as the matriarch submits to you."
						+ " Having the choice between building up a new flock, or submitting to your rule, [harpyDominant.name] has obviously chosen the latter, and you reward her obedience by telling her what she wants to hear,"
						+ " [pc.speech(Good girl! You'll do exactly as I say from now on, understood?!)]"
					+ "</p>"
					+ "<p>"
						+ "[harpyDominant.Name] shuffles closer, letting out a submissive little mewling sound as she responds,"
						+ " [harpyDominant.speech(Yes "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+"! I'll do as you command!"
								+ " I'll be your good girl! Please, "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+", take special perfume as a sign of my submission to you!)]"
					+ "</p>"
					+ "<p>"
						+ "Producing a dark red bottle of perfume, [harpyDominant.name] holds it out towards you."
						+ " [harpyDominant.speech(If you want, "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+", this will turn you into one of us!)]"
						+ " You take the perfume in recognition of [harpyDominant.namePos] submission, but you're unsure whether you'll actually use it..."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Good girl!)] you say. [pc.speech(Now, you're going to get this nest to calm down! If I hear just <i>one</i> report of these harpies misbehaving, you're going to be in some serious trouble!)]"
					+ "</p>"
					+ "<p>"
						+ "[harpyDominant.speech(Yes, "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+"! I'll discipline them! Don't worry about them any more!)]"
					+ "</p>"
					+ "<p>"
						+ "Thanks to your dominant personality and good looks, you've been able to subdue [harpyDominant.namePos] nest without fighting!"
						+ " Looking down at the now-submissive matriarch, you wonder if you should publicly prove to all these harpies who's in control here..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("Sex", "Have dominant sex with [harpyDominant.name].",
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getHarpyDominant(), SexPositionSlot.STANDING_SUBMISSIVE))),
						HARPY_NEST_DOMINANT_AFTER_SEX,
						"<p>"
							+ "Eager to put [harpyDominant.name] in her place in front of your new flock, you reach down and grab her wings."
							+ " Pulling her to her feet, you step forwards, before planting a deep kiss on her [harpyDominant.lips+]."
						+ "</p>"
						+ "<p>"
							+ "[harpyDominant.Name] responds to your dominant move by letting out a submissive little whine, and, wrapping her wings around your back, she passionately starts returning your kiss..."
						+ "</p>");
						
			} else if (index == 0) {
				return new Response("Leave", "Decide to take your leave.", HARPY_NEST_DOMINANT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that your work here is done, you turn around and start to walk back the way you came."
									+ " You hear [harpyDominant.name] shouting at one of the harpies as you leave,"
									+ " [harpyDominant.speech(<i>[pc.sheIs</i> the new leader around here, you worthless bitch! [harpyDominantCompanion.Name]! Looks like you've got a new fuck-toy!)]"
								+ "</p>"
								+ "<p>"
									+ "Smirking as you hear that, you continue back across the main platform, quickly finding yourself on the outskirts of the nest once again."
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_DOMINANT_FIGHT = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "Left with no other choice, you look up at [harpyDominant.name] as you make your final demand,"
					+ " [pc.speech(You're either going to get your nest to calm down right now, or I'm going to make you!)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyDominant.Name] lets out a furious scream, and she shouts out to her companion,"
					+ " [harpyDominant.speechNoEffects([harpyDominantCompanion.Name], teach this insolent [pc.race] a lesson! <i>Nobody</i> talks to me like that and gets away with it!)]"
				+ "</p>"
				+ "<p>"
					+ "With a shout, [harpyDominantCompanion.name] runs forwards, eager to please her matriarch as she launches into a furious assault."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "[harpyDominantCompanion.Name] rushes to do her matriarch's bidding!", Main.game.getHarpyDominantCompanion());
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_DOMINANT_FIGHT_LOSE = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "You fall to the floor, totally beaten."
						+ " As you collapse, [harpyDominantCompanion.name] jumps down on top of you, pinning you to the floor as she calls out to her matriarch,"
						+ " [harpyDominantCompanion.speechNoEffects([harpyDominant.Name]! The stupid bitch is ready for [pc.her] punishment now!)]"
					+ "</p>"
					+ "<p>"
						+ "[harpyDominant.speech(Good girl, [harpyDominantCompanion.name]!)] you hear her respond."
						+ " [harpyDominant.speech(I think it's time to teach this insolent little [pc.race] a lesson!)]"
					+ "</p>"
					+ "<p>"
						+ "As [harpyDominantCompanion.name] continues holding you down, [harpyDominant.name] calls out for the rest of the surrounding harpies to help."
						+ " Rushing to obey their mistress, several of the red-and-black harpies dash forwards and help to pin your [pc.arms] and [pc.legs] to the floor."
					+ "</p>"
					+ "<p>"
						+ "[harpyDominant.speech(That's right, hold [pc.herHim] still!)] [harpyDominant.name] calls out, stepping down from her podium before strutting over to you."
						+ " [harpyDominant.speech(Fucking bitch! I know just what you need! Coming into <i>my</i> nest, then trying to act all tough?! You just want to be a cute little matriarch yourself, don't you?!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speechNoEffects(Let me go!)] you shout, struggling against the harpies holding you down."
						+ " You're too weak to shake them off, however, and they easily continue to pin you to the floor, holding you quite still as [harpyDominant.name] stoops down next to your face."
					+ "</p>"
					+ "<p>"
						+ "[harpyDominant.speech(That's the spirit!)]"
						+ " she laughs."
						+ " [harpyDominant.speech(You're so feisty! It's better to channel all that pent up energy elsewhere though. Don't worry, <i>this</i> will help you with that!)]"
					+ "</p>"
					+ "<p>"
						+ "As [bimboHarpyCompanion.name] sits on your chest, laughing at your fruitless attempts to pull free, [harpyDominant.name] brings a dark red perfume bottle up to your face."
						+ " Grinning evilly, she leans in to you, ready to spray the bottle's contents onto your helpless body..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Smash bottle", "Don't let [harpyDominant.Name] spray you with that strange perfume...", HARPY_NEST_DOMINANT_FIGHT_LOSE_PUNISHMENT_NO_TF);
					
			} else if (index == 2) {
				return new Response("Stay still",
						"Allow [harpyDominant.Name] to spray you with the strange perfume... [style.boldBad(Warning:)] <b>Due to the nature of harpies needing a special form, this transformation bypasses TF preferences!</b>",
						HARPY_NEST_DOMINANT_FIGHT_LOSE_PUNISHMENT,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING),
						Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append("<p>"
								+ "You stay still, allowing [harpyDominant.name] to spray the bottle's contents onto your face and neck as she taunts you,"
								+ " [harpyDominant.speech(You want to act like an angry, bitchy little harpy? You'd better have the body to match it!)]"
							+ "</p>"
							+ "<p>"
								+ "You try to make a response, but as you open your mouth, the choking perfume enters your airways, leaving you coughing and spluttering on the floor."
								+ " Before you know what you're doing, you're breathing in deeply, letting out little whining noises as you find yourself desperate to inhale as much of the sweet-smelling perfume as you possibly can..."
							+ "</p>"
							+ItemEffectType.DOMINANT_PERFUME.applyEffect(null, null, null, 0, Main.game.getHarpyDominant(), Main.game.getPlayer(), null));
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_DOMINANT_FIGHT_BEAT_PET = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "[harpyDominant.Name] lets out a furious scream as [harpyDominantCompanion.name] falls to the floor, defeated."
					+ " You notice a lot of the surrounding harpies glancing nervously at each other, and a few start to shuffle around to your side of the platform."
					+ " It looks as though they're trying to hedge their bets, and are getting ready to support you if you manage to prove your strength."
				+ "</p>"
				+ "<p>"
					+ "You don't have too much time to ponder on these harpies' fickle nature, as [harpyDominant.Name] suddenly leaps down off her podium, screaming,"
					+ " [harpyDominant.speech(You fucking bitch! You're going to pay for this!)]"
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "[harpyDominant.Name] looks furious as she launches her attack on you!", Main.game.getHarpyDominant());
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_DOMINANT_FIGHT_LOSE_TO_MATRIARCH = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "You fall to the floor, totally beaten."
					+ " As you collapse, [dominantHarpy.name] orders her flock to hold you still, and, rushing to obey their matriarch, you're quickly pinned to the floor."
				+ "</p>"
				+ "<p>"
					+ "[dominantHarpy.speech([dominantHarpyCompanion.name], get up!)] you hear [dominantHarpy.name] shouting to her companion."
					+ " [dominantHarpy.speech(It's time to teach this insolent [pc.race] a lesson!)]"
				+ "</p>"
				+ "<p>"
					+ "As the members of [dominantHarpy.namePos] inner-circle continue to hold you down, you hear the dominant matriarch and her companion walking towards you."
					+ " The harpies start laughing as you try to wriggle free, but you're too weak from the fight to offer any real resistance."
				+ "</p>"
				+ "<p>"
					+ "[dominantHarpy.speech(Time for some revenge, [bimboHarpyCompanion.name]!)]"
					+ " [dominantHarpy.name] shouts out, and, as her companion moves to help the other harpies hold you down, she laughs,"
					+ " [harpyDominant.speech(Stupid bitch! I know just what you need! Coming into <i>my</i> nest, then trying to act all tough?! You just want to be a cute little matriarch yourself, don't you?!)]"
				+ "</p>"
				+ "<p>"
					+ "[pc.speechNoEffects(Let me go!)] you shout, struggling against the harpies holding you down."
					+ " You're too weak to shake them off, however, and they easily continue to pin you to the floor, holding you quite still as [harpyDominant.name] stoops down next to your face."
				+ "</p>"
				+ "<p>"
					+ "[harpyDominant.speech(That's the spirit!)]"
					+ " she laughs."
					+ " [harpyDominant.speech(You're so feisty! It's better to channel all that pent up energy elsewhere though. Don't worry, <i>this</i> will help you with that!)]"
				+ "</p>"
				+ "<p>"
					+ "As [bimboHarpyCompanion.name] sits on your chest, laughing at your fruitless attempts to pull free, [harpyDominant.name] brings a dark red perfume bottle up to your face."
					+ " Grinning evilly, she leans in to you, ready to spray the bottle's contents onto your helpless body..."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Smash bottle", "Don't let [harpyDominant.Name] spray you with that strange perfume...", HARPY_NEST_DOMINANT_FIGHT_LOSE_PUNISHMENT_NO_TF);
					
			} else if (index == 2) {
				return new Response("Stay still",
						"Allow [harpyDominant.Name] to spray you with the strange perfume... [style.boldBad(Warning:)] <b>Due to the nature of harpies needing a special form, this transformation bypasses TF preferences!</b>",
						HARPY_NEST_DOMINANT_FIGHT_LOSE_PUNISHMENT,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING),
						Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append("<p>"
								+ "You stay still, allowing [harpyDominant.name] to spray the bottle's contents onto your face and neck as she taunts you,"
								+ " [harpyDominant.speech(You want to act like an angry, bitchy little harpy? You'd better have the body to match it!)]"
							+ "</p>"
							+ "<p>"
								+ "You try to make a response, but as you open your mouth, the choking perfume enters your airways, leaving you coughing and spluttering on the floor."
								+ " Before you know what you're doing, you're breathing in deeply, letting out little whining noises as you find yourself desperate to inhale as much of the sweet-smelling perfume as you possibly can..."
							+ "</p>"
							+ItemEffectType.DOMINANT_PERFUME.applyEffect(null, null, null, 0, Main.game.getHarpyDominant(), Main.game.getPlayer(), null));
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_DOMINANT_FIGHT_BEAT_DOMINANT = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "[harpyDominant.NamePos] inner-circle of harpies look on in silence as they watch their matriarch slump to the floor."
					+ " Walking forwards, you look down on her pitiful form, and you hear a low moan escape from between her lips as she pushes herself up onto her knees."
					+ " [harpyDominant.speechNoEffects(Y-You're so strong... Who are you?!)]"
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(Your new "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+",)] you respond, turning to face the surrounding harpies."
					+ " [pc.speech(Any of you got a problem with that?!)]"
				+ "</p>"
				+ "<p>"
					+ "Your arcane aura is clearly having a strong effect on [harpyDominant.name], as she lets out another lewd moan as she calls up to you."
					+ " [harpyDominant.speech(M-"+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+"! My girls will be good, don't worry!)]"
				+ "</p>"
				+ "<p>"
					+ "Seeing their matriarch submit to you, the rest of the harpies bow their heads down as you issue your orders,"
					+ " [pc.speech(You're all going to calm down, got it?! No more feuds, and no more attacking travellers through the Harpy Nests!)]"
				+ "</p>"
				+ "<p>"
					+ "You can't help but grin as the harpies all call out in agreement."
					+ " With her flock now firmly under your control, [harpyDominant.Name] shuffles closer, letting out a submissive little mewling sound."
					+ " [harpyDominant.speech(M-"+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+"! I'll do as you command!"
							+ " I'll be your good girl! Please, take special perfume as a sign of my submission to you!)]"
				+ "</p>"
				+ "<p>"
					+ "Producing a dark red bottle of perfume, [harpyDominant.name] holds it out towards you."
					+ " [harpyDominant.speech(If you want, "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+", this will turn you into one of us!)]"
					+ " You take the perfume in recognition of [harpyDominant.namePos] submission, but you're unsure whether you'll actually use it..."
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(Good girl!)] you say. [pc.speech(Now, you're going to get the rest of this nest to calm down! If I hear just <i>one</i> report of these harpies misbehaving, you're going to be in some serious trouble!)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyDominant.speech(Yes, "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+"! I'll discipline them! Don't worry about them any more!)]"
				+ "</p>"
				+ "<p>"
					+ "Thanks to your victory over [harpyDominant.name], and the power of your arcane aura, you've been able to subdue [harpyDominant.namePos] nest!"
					+ " Looking down at the now-submissive matriarch, you wonder if you should publicly prove to all these harpies who's in control here..."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("Sex", "Have dominant sex with [harpyDominant.name].",
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getHarpyDominant(), SexPositionSlot.STANDING_SUBMISSIVE))),
						HARPY_NEST_DOMINANT_AFTER_SEX,
						"<p>"
							+ "Eager to put [harpyDominant.name] in her place in front of your new flock, you reach down and grab her wings."
							+ " Pulling her to her feet, you step forwards, before planting a deep kiss on her [harpyDominant.lips+]."
						+ "</p>"
						+ "<p>"
							+ "[harpyDominant.Name] responds to your dominant move by letting out a submissive little whine, and, wrapping her wings around your back, she passionately starts returning your kiss..."
						+ "</p>");
						
			} else if (index == 0) {
				return new Response("Leave", ".", HARPY_NEST_DOMINANT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that your work here is done, you turn around and start to walk back the way you came."
									+ " You hear [harpyDominant.name] shouting at one of the harpies as you leave,"
									+ " [harpyDominant.speech(<i>[pc.sheIs</i> the new leader around here, you worthless bitch! [harpyDominantCompanion.Name]! Looks like you've got a new fuck-toy!)]"
								+ "</p>"
								+ "<p>"
									+ "Smirking as you hear that, you continue back across the main platform, quickly finding yourself on the outskirts of the nest once again."
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_DOMINANT_FIGHT_LOSE_PUNISHMENT_NO_TF = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "Unwilling to be transformed into one of [harpyDominant.namePos] harpies, you furiously yank your arm free of their grip."
						+ " In the seconds you have before you're restrained again, you reach up to grab the bottle of perfume from [harpyDominant.namePos] feathered had,"
							+ " before hurling it across the nest to smash into pieces against the hard wooden floor."
					+ "</p>"
					+ "<p>"
						+ "[harpyDominant.speech(You fucking bitch!)]"
						+ " [harpyDominant.name] screams, grabbing your chin and squeezing cruelly down as she scowls at you."
						+ " [harpyDominant.speech(You're going to fucking pay for that! [harpyDominantCompanion.Name]! This bitch wants my full attention! How about you help [pc.herHim] entertain me?!)]"
					+ "</p>"
					+ "<p>"
						+ "Any protests or objections that you might have are muffled as [harpyDominantCompanion.namePos] feathered hand clamps down over your mouth."
						+ " Leaning down to stare into your eyes, she chuckles,"
						+ " [harpyDominantCompanion.speech(Let's put on a show!)]"
					+ "</p>"
					+ "<p>"
						+ "For the next few hours, you're forced to act as [harpyDominantCompanion.namePos] play-thing."
						+ " Tying you up in bondage, publicly spanking you in front of [harpyDominant.name], and being walked around the nest on a leash are amongst the least humiliating things you suffer."
						+ " Thankfully, the harpies don't show any interest in forcing you to perform any penetrative sexual acts, and they eventually all lose interest."
					+ "</p>"
					+ "<p>"
						+ "As [harpyDominantCompanion.name] declares that she's bored of playing with you, [harpyDominant.name] finally sets you free."
						+ " [harpyDominant.speech(Alright bitch! I think you've had enough! Now get the fuck out of my nest!)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
					return new Response("Thrown out", "Having had their fun, you're quickly thrown out of the nest.", HARPY_NEST_DOMINANT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Finally given an opportunity to leave, you do as [harpyDominant.name] commands."
										+ " Running away from the harpies' mocking laughter, you dash across the platform, and before you know it, you find yourself back on the outskirts of the nest..."
									+ "</p>");
						}
					};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_DOMINANT_FIGHT_LOSE_PUNISHMENT = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "As the perfume's transformative effects come to an end, the harpies' grip on your [pc.arms] and [pc.legs] loosens."
					+ " Blinking slowly a few times, you let out an exhausted moan,"
					+ " [pc.speech(Ahhh... You bitch...)]"
				+ "</p>"
				+ "<p>"
					+ "The flock of harpies bursts out into laughter, and the ones who were holding you down finally release you before backing off."
					+ " Scrambling to your knees, you find yourself looking up at [harpyDominant.Name] as she continues towering over you."
				+ "</p>"
				+ "<p>"
					+ "[harpyDominant.speech(Good little harpy! Now, after you apologise, you can fuck off and make your own flock!)]"
				+ "</p>"
				+ "<p>"
					+ "With the effects of the perfume still lingering in your mind, you find yourself blurting out,"
					+ " [pc.speech(I'm not apologising to you! Fucking bitch, I'm out of here!)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyDominant.Name] grins down at you, and, to the background noise of dozens of harpies' laughter, she shouts,"
					+ " [harpyDominant.speech([harpyDominantCompanion.Name]! This bitch wants my full attention! How about you help [pc.herHim] entertain me?!)]"
				+ "</p>"
				+ "<p>"
					+ "Any protests or objections that you might have are muffled as [harpyDominantCompanion.namePos] feathered hand clamps down over your mouth."
					+ " Leaning down to stare into your eyes, she chuckles,"
					+ " [harpyDominantCompanion.speech(Let's put on a show!)]"
				+ "</p>"
				+ "<p>"
					+ "For the next few hours, you're forced to act as [harpyDominantCompanion.namePos] play-thing."
					+ " Tying you up in bondage, publicly spanking you in front of [harpyDominant.name], and being walked around the nest on a leash are amongst the least humiliating things you suffer."
					+ " Thankfully, the harpies don't show any interest in forcing you to perform any penetrative sexual acts, and they eventually all lose interest."
				+ "</p>"
				+ "<p>"
					+ "As [harpyDominantCompanion.name] declares that she's bored of playing with you, [harpyDominant.name] finally sets you free."
					+ " [harpyDominant.speech(Alright, bitch! I think you've had enough! Now get the fuck out of my nest!)]"
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
					return new Response("Thrown out", "Having had their fun, you're quickly thrown out of the nest.", HARPY_NEST_DOMINANT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Finally given an opportunity to leave, you do as [harpyDominant.name] commands."
										+ " Running away from the harpies' mocking laughter, you dash across the platform, and before you know it, you find yourself back on the outskirts of the nest..."
									+ "</p>");
						}
					};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_DOMINANT_AFTER_SEX = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				return "<p>"
							+ "As you step back from [harpyDominant.name], she sinks to the floor, totally worn out from her orgasm"+(Sex.getNumberOfOrgasms(Sex.getActivePartner()) > 1?"s":"")+"."
							+ " The surrounding harpies, having watched the whole thing, kneel in submission as you finish with their matriarch."
						+ "</p>";
			} else {
				return "<p>"
							+ "As you step back from [harpyDominant.name], she sinks to the floor, letting out a desperate whine as she realises that you've finished with her."
							+ " Her feathered hands dart down between her legs, and she frantically starts masturbating as she seeks to finish what you started."
							+ " The surrounding harpies, having watched the whole thing, kneel in submission as you finish with their matriarch."
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Leave", "Having had your fun, you decide to leave.", HARPY_NEST_DOMINANT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Having shown [harpyDominant.name] her place, you set off across the platform, and before you know it, you find yourself back on the outskirts of the nest once again..."
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
}
