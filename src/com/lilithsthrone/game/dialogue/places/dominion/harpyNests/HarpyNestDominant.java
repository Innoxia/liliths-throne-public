package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominant;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominantCompanion;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.8
 * @version 0.4.3.2
 * @author Innoxia
 */
public class HarpyNestDominant {

	public static final DialogueNode HARPY_NEST_DOMINANT_TALK = new DialogueNode("Harpy nest", "", true) {

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "[pc.speech(I just want to talk to you about the recent unrest in the Harpy Nests. The Enforcers are having a hard time keeping the peace, and it appears as though some of your flock are to blame,)]"
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
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.HARPY_MATRIARCH_DOMINANT_PERFUME), false, true));
						
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
				return new Response("Force compliance",
						"If you want these harpies to chill out, it looks as though you'll have to do it by force..."
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(BAD END:)] If you lose this fight, the harpies won't ever let you leave!"
								:""),
						HARPY_NEST_DOMINANT_FIGHT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
					
			} else if (index == 0) {
				return new Response("Leave", "Decide to leave [harpyDominant.namePos] nest.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_dominant_exterior")) {
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
	
	public static final DialogueNode HARPY_NEST_DOMINANT_UGLY = new DialogueNode("Harpy nest", "", true) {

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
				return new ResponseCombat("Fight",
						"[harpyDominantCompanion.Name] rushes to do her matriarch's bidding!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(BAD END:)] If you lose this fight, the harpies won't ever let you leave!"
								:""),
						Main.game.getNpc(HarpyDominantCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_DOMINANT_QUEEN = new DialogueNode("Harpy nest", "", true) {

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
								+ " I'll be your good girl! Please, "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+", take this special perfume as a sign of my submission to you!)]"
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
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyDominant.class)),
						null,
						null), HARPY_NEST_DOMINANT_AFTER_SEX, "<p>"
							+ "Eager to put [harpyDominant.name] in her place in front of your new flock, you reach down and grab her wings."
							+ " Pulling her to her feet, you step forwards, before planting a deep kiss on her [harpyDominant.lips+]."
						+ "</p>"
						+ "<p>"
							+ "[harpyDominant.Name] responds to your dominant move by letting out a submissive little whine, and, wrapping her wings around your back, she passionately starts returning your kiss..."
						+ "</p>");
						
			} else if (index == 0) {
				return new Response("Leave", "Decide to take your leave.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_dominant_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that your work here is done, you turn around and start to walk back the way you came."
									+ " You hear [harpyDominant.name] shouting at one of the harpies as you leave,"
									+ " [harpyDominant.speech(<i>[pc.SheIs]</i> the new leader around here, you worthless bitch! [harpyDominantCompanion.Name]! Looks like you've got a new fuck-toy!)]"
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
	
	public static final DialogueNode HARPY_NEST_DOMINANT_FIGHT = new DialogueNode("Harpy nest", "", true) {

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
				return new ResponseCombat("Fight",
						"[harpyDominantCompanion.Name] rushes to do her matriarch's bidding!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(BAD END:)] If you lose this fight, the harpies won't ever let you leave!"
								:""),
						Main.game.getNpc(HarpyDominantCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_DOMINANT_FIGHT_BEAT_PET = new DialogueNode("Harpy nest", "", true) {

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
				return new ResponseCombat("Fight",
						"[harpyDominant.Name] looks furious as she launches her attack on you!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(BAD END:)] If you lose this fight, the harpies won't ever let you leave!"
								:""),
						Main.game.getNpc(HarpyDominant.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_DOMINANT_FIGHT_BEAT_DOMINANT = new DialogueNode("Harpy nest", "", true) {

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
							+ " I'll be your good girl! Please, take this special perfume as a sign of my submission to you!)]"
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
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyDominant.class)),
						null,
						null), HARPY_NEST_DOMINANT_AFTER_SEX, "<p>"
							+ "Eager to put [harpyDominant.name] in her place in front of your new flock, you reach down and grab her wings."
							+ " Pulling her to her feet, you step forwards, before planting a deep kiss on her [harpyDominant.lips+]."
						+ "</p>"
						+ "<p>"
							+ "[harpyDominant.Name] responds to your dominant move by letting out a submissive little whine, and, wrapping her wings around your back, she passionately starts returning your kiss..."
						+ "</p>");
						
			} else if (index == 0) {
				return new Response("Leave", "Tell [harpyDominant.name] that you'll be back later.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_dominant_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that your work here is done, you turn around and start to walk back the way you came."
									+ " You hear [harpyDominant.name] shouting at one of the harpies as you leave,"
									+ " [harpyDominant.speech(<i>[pc.SheIs]</i> the new leader around here, you worthless bitch! [harpyDominantCompanion.Name]! Looks like you've got a new fuck-toy!)]"
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
	
	public static final DialogueNode HARPY_NEST_DOMINANT_AFTER_SEX = new DialogueNode("Harpy nest", "", true) {

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(HarpyDominant.class)) >= Main.game.getNpc(HarpyDominant.class).getOrgasmsBeforeSatisfied()) {
				return "<p>"
							+ "As you step back from [harpyDominant.name], she sinks to the floor, totally worn out from her orgasm"+(Main.sex.getNumberOfOrgasms(Main.game.getNpc(HarpyDominant.class)) > 1?"s":"")+"."
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
				return new Response("Leave", "Having had your fun, you decide to leave.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_dominant_exterior")) {
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
