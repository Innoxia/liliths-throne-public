package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimbo;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimboCompanion;
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
public class HarpyNestBimbo {

	public static final DialogueNode HARPY_NEST_BIMBO_TALK = new DialogueNode("Harpy nest", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(I'm here to talk to you about all the recent unrest around here. The Enforcers are having a hard time keeping the peace, and it would be really helpful if you could get your flock to calm down a little,)]"
						+ " you try to explain, but [bimboHarpy.name] simply rolls her eyes and makes an annoyed tutting sound in response."
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.speechNoEffects(Like, that is sooo not my problem!)]"
						+ " she says, making a dismissive gesture with her hand before continuing,"
						+ " [bimboHarpy.speechNoEffects(How about, like, you fuck off with your silly little moaning! I'm, like, sooo tempted to get [bimboHarpyCompanion.name] here to teach you a lesson right now!)]"
					+ "</p>"
					+ "<p>"
						+ "It doesn't look like [bimboHarpy.name] is going to listen to reason."
						+ " You'll either have to think of another way to convince the harpies to calm down, or make them calm down with force."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Bimbo queen", "Well, you, like, tried to talk and stuff, but this bitch isn't listening! You should totally convince the nest that you should be their queen!", HARPY_NEST_BIMBO_QUEEN,
						Util.newArrayListOfValues(Fetish.FETISH_BIMBO), null, null, Femininity.FEMININE_STRONG, null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.bimboPacified);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.HARPY_MATRIARCH_BIMBO_LOLLIPOP), false, true));
						
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
						HARPY_NEST_BIMBO_FIGHT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
					
			} else if (index == 0) {
				return new Response("Leave", "Tell [bimboHarpy.name] that you'll be back later.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_bimbo_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that now isn't the best time to be doing this, you turn around and head back down the stairs."
									+ " You hear [bimboHarpy.name] complaining to her harpies as you leave,"
									+ " [bimboHarpy.speechNoEffects(Eugh! Like, what was all that about?!)]"
								+ "</p>"
								+ "<p>"
									+ "Ignoring her words, you continue down the steps and back across the main platform, quickly finding yourself back on the outskirts of the nest."
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_UGLY = new DialogueNode("Harpy nest", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "Annoyed by how this stupid bimbo is treating you, you decide to give her a taste of her own medicine."
						+ " [pc.speech(How about you stop offending everyone, and put a bag over that ugly face of yours?)]"
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.NamePos] face goes bright red, and with a furious scream, she cries out to her companion,"
						+ " [bimboHarpy.speechNoEffects([bimboHarpyCompanion.Name]! Get [pc.herHim]! Get [pc.herHim]! Nobody talks to me like that!)]"
					+ "</p>"
					+ "<p>"
						+ "Looking almost as angry as [bimboHarpy.name] does, [bimboHarpyCompanion.name] runs forwards, eager to please her matriarch as she launches into a furious assault."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight",
						"[bimboHarpyCompanion.Name] rushes to do her matriarch's bidding!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(BAD END:)] If you lose this fight, the harpies won't ever let you leave!"
								:""),
						Main.game.getNpc(HarpyBimboCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_QUEEN = new DialogueNode("Harpy nest", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "Having had enough of this bitch's attitude, you step forwards, looking at [bimboHarpyCompanion.name] as you speak,"
						+ " [pc.speechNoEffects(Like, do you seriously have to listen to this all day? You're, like, gonna start doin' what I say now, got it?! Now, like, come kneel for your new queen!)]"
					+ "</p>"
					+ "<p>"
						+ "The combination of your beautiful features and potent arcane aura seems to be having a strong effect on these harpies."
						+ " Speaking in the same manner as their matriarch seems to be just enough to start to sway [bimboHarpy.namePos] followers to your side."
						+ " Before she knows what she's doing, [bimboHarpyCompanion.name] is rushing forwards to obey your command."
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.speechNoEffects([bimboHarpyCompanion.Name]?! Like, what the hell are you doin'? Get back here!)] [bimboHarpy.name] shouts."
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpyCompanion.Name] stops in between the two of you, looking back and forth in confusion."
						+ " You notice that the rest of the flock haven't come to the defence of their matriarch, and are watching, waiting to see what [bimboHarpyCompanion.name] decides to do."
					+ "</p>"
					+"<p>"
						+ "[pc.speechNoEffects([bimboHarpyCompanion.Name]!)] you shout. [pc.speechNoEffects(Come over here and kneel to me, like, <i>right this instant</i>!)]"
					+ "</p>"
					+ "<p>"
						+ "Not quite knowing how to react, [bimboHarpy.name] watches in disbelief as her companion rushes forwards and obediently drops to her knees in front of you."
						+ " The rest of the harpies quickly follow suit; recognising you as a more powerful and, crucially, more attractive female than their current matriarch."
					+ "</p>"
					+ "<p>"
						+ "As what's just happened starts to sink in, [bimboHarpy.name] quickly runs forwards, pushing [bimboHarpyCompanion.name] out of the way and taking her place as she kneels in front of you."
						+ " Shuffling forwards, she looks up into your eyes."
						+ " [bimboHarpy.speechNoEffects(Like, <i>I'm</i> the prettiest one here! A-Apart from you, of course! Let me be your personal pet, please!)]"
					+ "</p>"
					+ "<p>"
						+ "Smirking down at your new pet harpy, you tell her what she wants to hear,"
						+ " [pc.speechNoEffects(Good pet! You're, like, so super smart! I knew you'd understand your new place!)]"
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.Name] shuffles closer, letting out submissive little mewling sounds and looking up at you as you continue,"
						+ " [pc.speechNoEffects(Now, like, I don't have the time and stuff to run this nest, so you're gonna, like carry on doin' all that stuff."
						+ " You just gotta remember who's really in charge! Now, your first order is to, like, get all these harpies to chill out and stuff!"
						+ " If I hear they've been trouble, you're gonna, like, be demoted from being my pet, got it?!)]"
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.speechNoEffects(Yes, mistress!)] [bimboHarpy.name] cries out. [bimboHarpy.speechNoEffects(I'll get them under control! Ooh! Ooh! Also! Mistress! I got a special lollipop for you!)]"
					+ "</p>"
					+ "<p>"
						+ "Producing a pink-and-white swirly lollipop, [bimboHarpy.name] holds it out towards you."
						+ " [bimboHarpy.speechNoEffects(This is, like, what I give to all the new members of the flock! It'll, like turn you into one of us!)]"
						+ " You take the lollipop in recognition of [bimboHarpy.namePos] submission, but you're unsure whether you'll actually use it..."
					+ "</p>"
					+ "<p>"
						+ "Thanks to your bimbo personality and good looks, you've been able to subdue [bimboHarpy.namePos] nest without fighting!"
						+ " Looking down at the bimbo matriarch, you wonder if you should publicly prove to all these harpies who's in control here..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("Sex", "Have dominant sex with [bimboHarpy.name] in front of her flock.",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyBimbo.class)),
						null,
						null), HARPY_NEST_BIMBO_AFTER_SEX, "<p>"
							+ "Eager to put [harpyBimbo.name] in her place in front of her inner-circle, you reach down and grab her by her wings."
							+ " Pulling her to her feet, you step forwards, planting a deep kiss on her [harpyBimbo.lips+] and drawing a series of excited giggles from the surrounding bimbo harpies."
						+ "</p>"
						+ "<p>"
							+ "[harpyBimbo.Name] responds to your dominant move by wrapping her wings around your back, grinding herself up against you as she passionately returns your kiss..."
						+ "</p>");
						
			} else if (index == 0) {
				return new Response("Leave", "Tell [bimboHarpy.name] that you'll be back later.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_bimbo_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that your work here is done, you turn around and head back down the stairs."
									+ " You hear [bimboHarpy.name] shouting at the harpies as you leave,"
									+ " [bimboHarpy.speechNoEffects(Like, be quiet! <i>[pc.SheIs]</i> the new leader around here, got it?!)]"
								+ "</p>"
								+ "<p>"
									+ "Smirking as you hear that, you continue down the steps and back across the main platform, quickly finding yourself back on the outskirts of the nest."
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_FIGHT = new DialogueNode("Harpy nest", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "Having had enough of [bimboHarpy.namePos] attitude, you make your final demand,"
						+ " [pc.speech(You're going to get your nest to calm down, or I'm going to make you!)]"
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.NamePos] face goes bright red, and she shouts out to her companion,"
						+ " [bimboHarpy.speechNoEffects([bimboHarpyCompanion.Name], teach this [pc.race] a lesson! Like, <i>nobody</i> talks to me like that!)]"
					+ "</p>"
					+ "<p>"
						+ "With a shout, [bimboHarpyCompanion.name] runs forwards, eager to please her matriarch as she launches into a furious assault."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight",
						"[bimboHarpyCompanion.Name] rushes to do her matriarch's bidding!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(BAD END:)] If you lose this fight, the harpies won't ever let you leave!"
								:""),
						Main.game.getNpc(HarpyBimboCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_FIGHT_BEAT_GF = new DialogueNode("Harpy nest", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "[bimboHarpy.Name] lets out a furious scream as [bimboHarpyCompanion.name] falls to the floor, defeated."
						+ " You notice a lot of the surrounding harpies glancing nervously at each other, and a few start to shuffle around to your side of the platform."
						+ " It looks as though they're trying to hedge their bets, and are getting ready to support you if you manage to prove your strength."
					+ "</p>"
					+ "<p>"
						+ "You don't have too much time to ponder on these harpies' fickle nature, as [bimboHarpy.Name] suddenly darts forwards, screaming,"
						+ " [bimboHarpy.speech(You're gonna pay for this!)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight",
						"[bimboHarpy.Name] looks furious as she launches her attack on you!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(BAD END:)] If you lose this fight, the harpies won't ever let you leave!"
								:""),
						Main.game.getNpc(HarpyBimbo.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_FIGHT_BEAT_BIMBO = new DialogueNode("Harpy nest", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "[bimboHarpy.NamePos] inner-circle of bimbo harpies, cheering and shouting just moments ago, falls completely silent as they see their matriarch slump to the floor."
						+ " Walking forwards, you look down on her pitiful form, and you hear the bimbo let out an erotic moan as she pushes herself up onto her knees,"
						+ " [bimboHarpy.speechNoEffects(Aah! Like, you're so powerful and stuff! W-Who are you?!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Your new leader,)] you respond."
						+ " [pc.speech(I don't care how you normally determine a flock's leader; I'm in charge now!)]"
					+ "</p>"
					+ "<p>"
						+ "Your arcane aura is clearly having a strong effect on [bimboHarpy.name], as she lets out another lewd moan as she responds,"
						+ " [bimboHarpy.speechNoEffects(Y-Yes "+(Main.game.getPlayer().isFeminine()?"mistress":"master")+"! I-I'll be, like, super good for you!)]"
					+ "</p>"
					+ "<p>"
						+ "Seeing their matriarch submit to you, the rest of the harpies fall to their knees, bowing down to you as you issue your orders,"
						+ " [pc.speech(You're all going to calm down, got it?! No more feuds, and no more attacking travellers through the Harpy Nests!)]"
					+ "</p>"
					+ "<p>"
						+ "Your words seem to sink in, and are met by a chorus of eager agreements."
						+ " Shuffling ever closer to you, [bimboHarpy.name] holds up a swirly pink-and-white lollipop."
						+ " [bimboHarpy.speechNoEffects("+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+"! If you, like, wanted to look like us, give this a lick!"
								+ " I promise to keep everything under control for you! We'll all be good, won't we, girls?!)]"
					+ "</p>"
					+ "<p>"
						+ "As the bimbo harpies cry out in the affirmative, you bend down and take the lollipop in recognition of [bimboHarpy.namePos] submission, but you're unsure whether you'll actually use it..."
					+ "</p>"
					+ "<p>"
						+ "Thanks to your victory over [bimboHarpy.name], and the power of your arcane aura, you've been able to subdue [bimboHarpy.namePos] nest!"
						+ " Looking down at the bimbo matriarch, you wonder if you should publicly prove to all these harpies who's in control here..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("Sex", "Have dominant sex with [bimboHarpy.name].",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyBimbo.class)),
						null,
						null), HARPY_NEST_BIMBO_AFTER_SEX, "<p>"
							+ "Eager to put [harpyBimbo.name] in her place in front of her inner-circle, you reach down and grab her by her wings."
							+ " Pulling her to her feet, you step forwards, planting a deep kiss on her [harpyBimbo.lips+] and drawing a series of excited giggles from the surrounding bimbo harpies."
						+ "</p>"
						+ "<p>"
							+ "[harpyBimbo.Name] responds to your dominant move by wrapping her wings around your back, grinding herself up against you as she passionately returns your kiss..."
						+ "</p>");
						
			} else if (index == 0) {
				return new Response("Leave", "Tell [bimboHarpy.name] that you'll be back later.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_bimbo_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that your work here is done, you turn around and head back down the stairs."
									+ " You hear [bimboHarpy.name] shouting at the harpies as you leave,"
									+ " [bimboHarpy.speechNoEffects(Like, be quiet! <i>[pc.SheIs]</i> the new leader around here, got it?!)]"
								+ "</p>"
								+ "<p>"
									+ "Smirking as you hear that, you continue down the steps and back across the main platform, quickly finding yourself back on the outskirts of the nest."
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_AFTER_SEX = new DialogueNode("Harpy nest", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(HarpyBimbo.class)) >= Main.game.getNpc(HarpyBimbo.class).getOrgasmsBeforeSatisfied()) {
				return "<p>"
							+ "As you step back from [bimboHarpy.name], she sinks to the floor, totally worn out from her orgasm"+(Main.sex.getNumberOfOrgasms(Main.game.getNpc(HarpyBimbo.class)) > 1?"s":"")+"."
							+ " The surrounding harpies, having watched the whole thing, kneel in submission as you finish with their matriarch."
						+ "</p>";
			} else {
				return "<p>"
							+ "As you step back from [bimboHarpy.name], she sinks to the floor, letting out a desperate whine as she realises that you've finished with her."
							+ " Her feathered hands dart down between her legs, and she frantically starts masturbating as she seeks to finish what you started."
							+ " The surrounding harpies, having watched the whole thing, kneel in submission as you finish with their matriarch."
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Leave", "Having had your fun, you decide to leave.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_bimbo_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Having shown [bimboHarpy.name] her place, you set off down the staircase, and, walking back across the lower platform, you quickly find yourself on the outskirts of [bimboHarpy.namePos] nest once again."
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
}
