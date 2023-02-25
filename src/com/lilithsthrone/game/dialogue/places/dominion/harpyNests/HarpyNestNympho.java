package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.npc.dominion.HarpyNympho;
import com.lilithsthrone.game.character.npc.dominion.HarpyNymphoCompanion;
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
public class HarpyNestNympho {

	public static final DialogueNode HARPY_NEST_NYMPHO_TALK = new DialogueNode("Harpy nest", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyNympho.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(What I want,)] you say,"
						+ " [pc.speech(is for you to get your nest under control. The Enforcers are struggling to keep the peace, and it's members of your nest who are responsible!)]"
					+ "</p>"
					+ "<p>"
						+ "[harpyNympho.speech(Well it's not <i>my</i> fault if some of my playthings want to have a bit of extra fun elsewhere!)] [harpyNympho.name] replies."
						+ " [harpyNympho.speech(And anyway, you're kind of interrupting things here, so how about you turn around and go bother someone else! [harpyNymphoCompanion.Name]! Why don't you show this rude [pc.race] the way out!)]"
					+ "</p>"
					+ "<p>"
						+ "As [harpyNymphoCompanion.name] moves forwards, you realise that [harpyNympho.name] isn't going to listen to you."
						+ " You'll either have to think of another way to convince these harpies to calm down, or make them calm down by using force."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Nympho Queen", "You feel sorry for this matriarch, only getting to have sex with the same males over and over again. Tell her how a real nympho behaves!", HARPY_NEST_NYMPHO_QUEEN,
						null, null, Util.newArrayListOfValues(Perk.NYMPHOMANIAC), Femininity.FEMININE_STRONG, null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.nymphoPacified);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.HARPY_MATRIARCH_NYMPHO_LOLLIPOP), false, true));
						
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
						HARPY_NEST_NYMPHO_FIGHT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
					
			} else if (index == 0) {
				return new Response("Leave", "Tell [harpyNympho.name] that you'll be back later.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_nympho_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that now isn't the best time to start a confrontation, you turn around and take your leave."
									+ " You hear [harpyNympho.name] complaining to [harpyNymphoCompanion.name] as you walk away,"
									+ " [harpyNympho.speech(Well that was a waste of our time! Come on [harpyNymphoCompanion.name], this time I want you to use my mouth!)]"
								+ "</p>"
								+ "<p>"
									+ "Ignoring her words, you continue back across the platform, quickly finding yourself on the outskirts of the nest once again."
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_NYMPHO_UGLY = new DialogueNode("Harpy nest", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyNympho.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "Realising that you're going to have to force [harpyNympho.name] to get her nest under control, you decide to draw her into a confrontation,"
						+ " [pc.speech(Well, if you aren't going to calm your nest down, can you at least agree to put up some walls around your nest?"
						+ " It's not nice having to see a harpy as ugly as you every time I walk past your nest.)]" //Innoxia's next-level witticisms!
					+ "</p>"
					+ "<p>"
						+ "[harpyNympho.Name] lets out a furious scream, and, flapping her wings, she shouts out to her companion, "
						+ "[harpyNympho.speech([harpyNymphoCompanion.Name]! Get [pc.herHim]! Get [pc.herHim]! That fucker! [pc.She] said I was ugly!)]"
					+ "</p>"
					+ "<p>"
						+ "Looking almost as furious as [harpyNympho.name] does, [harpyNymphoCompanion.name] jumps forwards, eager to please her matriarch as she launches into a furious assault."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight",
						"[harpyNymphoCompanion.Name] rushes to do her matriarch's bidding!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(BAD END:)] If you lose this fight, the harpies won't ever let you leave!"
								:""),
						Main.game.getNpc(HarpyNymphoCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_NYMPHO_QUEEN = new DialogueNode("Harpy nest", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyNympho.NamePos] nest";
		}
		
		@Override
		public String getContent() { // Mock her for only having one partner at once, she gets flustered, you describe multiple harpies fucking her, she breaks and falls to her knees admitting that you're better than she is
			return "<p>"
					+ "[pc.speech(So, do you only fuck one of your little boy-toys here at a time?)] you ask in a derisory tone."
					+ " [pc.speech(Because you know you could be doing a lot more than that, right?)]"
				+ "</p>"
				+ "<p>"
					+ "For a moment, [harpyNympho.name] seems to be caught off guard, and stumbles over her words as she responds,"
					+ " [harpyNympho.speech(W-Well, I mean, I like to make each harpy feel special!)]"
				+ "</p>"
				+ "<p>"
					+ "The murmurs of conversation that had been surrounding you up until this point start to fade away, and you realise that the crowds of male harpies all around you are listening in closely."
					+ " From the moment you stepped foot inside [harpyNympho.namePos] nest, you could feel her flock's gaze resting on your beautiful figure, and now, loudly critiquing their matriarch's sexual skills,"
						+ " you're getting their full attention."
				+ "</p>"
				+ "<p>"
					+ "Striking the most sensual pose you can muster, you continue,"
					+ " [pc.speech(You know, you're hoarding all these gorgeous harpies here for your own personal use, but you're only willing to fuck them one at a time?!"
					+ " That's kind of unfair, don't you all agree?)]"
				+ "</p>"
				+ "<p>"
					+ "Turning to address the crowd as you ask that last question, the entire flock responds by calling out in agreement."
					+ " Your beautiful features and potent arcane aura are obviously having a strong effect on these harpies, which, combined with talking about sex in such an open manner, has turned them over to your side."
				+ "</p>"
				+ "<p>"
					+ "[harpyNympho.speech(But, w-what should I be doing then?)] [harpyNympho.name] nervously asks."
					+ " [harpyNympho.speech(I didn't realise I was being unfair...)]"
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(Oh, there's lots of things you could be doing,)] you say, walking closer to her."
					+ " [pc.speech(like having your pussy, ass and mouth all used at the same time. Or allowing your nest to run a train on your slutty little cunt here.)]"
				+ "</p>"
				+ "<p>"
					+ "As you say that, you reach down and grab [harpyNympho.namePos] exposed pussy."
					+ " She lets out a little squeak, and, leaning into you, moans,"
					+ " [harpyNympho.speech(Yes... I'll do that... What else?)]"
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(I think it's obvious by now what you need to do,)] you moan into her ear."
					+ " [pc.speech(Kneel down and accept that I'm the one in charge here.)]"
				+ "</p>"
				+ "<p>"
					+ "Realising that her flock has been mesmerised by your words, [harpyNympho.name] falls down onto her knees before you."
					+ " Looking up with big, lust-filled eyes, she moans, [harpyNympho.speech("+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+", please teach me!"
							+ " I'll calm the flock down for you! Please "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+", tell me what to do!)]"
				+ "</p>"
				+ "<p>"
					+ "You can't help but let out a little laugh as the matriarch submits to you."
					+ " [pc.speech(Good girl! You'll do exactly as I say from now on, understood?!)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyNympho.Name] shuffles closer, continuing to let out little moaning noises as she responds,"
					+ " [harpyNympho.speech(Yes "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+"! I'll do what you say!"
							+ " Please, "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+", I have something for you!)]"
				+ "</p>"
				+ "<p>"
					+ "Producing a cock-shaped lollipop, [harpyNympho.name] holds it out towards you."
					+ " [harpyNympho.speech("+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+", this will make you look like me! I-If you want to look like me, that is...)]"
					+ " You take the lollipop in recognition of [harpyNympho.namePos] submission, but you're unsure whether you'll actually use it..."
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(Good girl!)] you say. [pc.speech(Now, you're going to get your nest to calm down, aren't you?)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyNympho.speech(Yes "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+"! I'll be able to relieve all their stress if I start letting them use me all at once!)]"
				+ "</p>"
				+ "<p>"
					+ "Thanks to your own obsession with sex, your powerful arcane aura, and your good looks, you've been able to subdue [harpyNympho.namePos] nest without fighting!"
					+ " Looking down at the still-moaning matriarch, you wonder if you should give her some one-to-one tuition..."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("Sex", "Have dominant sex with [harpyNympho.name].",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyNympho.class)),
						null,
						null), HARPY_NEST_NYMPHO_AFTER_SEX, "<p>"
							+ "Eager to help satisfy [harpyNympho.namePos] craving for sex, you reach down and grab her wings."
							+ " Pulling her to her feet, you step forwards, before planting a deep kiss on her [harpyNympho.lips+]."
						+ "</p>"
						+ "<p>"
							+ "[harpyNympho.Name] responds to your dominant move by letting out an excited squeal, and, enthusiastically wrapping her wings around your back, she passionately starts returning your kiss..."
						+ "</p>");
						
			} else if (index == 0) {
				return new Response("Leave", "Decide to take your leave.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_nympho_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that your work here is done, you turn around and start to walk back the way you came."
									+ " You hear [harpyNympho.name] calling out to her harpies as you leave,"
									+ " [harpyNympho.speech(You heard our new leader! Let's try starting with three of you at once!)]"
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
	
	public static final DialogueNode HARPY_NEST_NYMPHO_FIGHT = new DialogueNode("Harpy nest", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyNympho.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "Realising that [harpyNympho.name] isn't going to listen to reason, you make your final demand,"
					+ " [pc.speech(You're either going to get your nest to calm down right now, or I'm going to make you!)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyNympho.Name] lets out an angry cry, and she shouts out to her companion,"
					+ " [harpyNympho.speech([harpyNymphoCompanion.Name], throw this [pc.race] out already! <i>Nobody</i> talks to me like that and gets away with it!)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyNymphoCompanion.name] immediately runs forwards, eager to please her matriarch as she launches into a furious assault."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight",
						"[harpyNymphoCompanion.Name] rushes to do her matriarch's bidding!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(BAD END:)] If you lose this fight, the harpies won't ever let you leave!"
								:""),
						Main.game.getNpc(HarpyNymphoCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_NYMPHO_FIGHT_BEAT_BF = new DialogueNode("Harpy nest", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyNympho.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "[harpyNympho.Name] lets out an angry wail as [harpyNymphoCompanion.name] falls to the floor, defeated."
					+ " You notice a lot of the surrounding harpies glancing nervously at each other, and a few start to shuffle around to your side of the platform."
					+ " It looks as though they're getting ready to support you if you manage to defeat their matriarch."
				+ "</p>"
				+ "<p>"
					+ "You don't have too much time to ponder on these harpies' fickle nature, as [harpyNympho.Name] suddenly rushes forwards, shouting,"
					+ " [harpyNympho.speech(You're going to pay for this!)]"
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight",
						"[harpyNympho.Name] looks furious as she launches her attack on you!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(BAD END:)] If you lose this fight, the harpies won't ever let you leave!"
								:""),
						Main.game.getNpc(HarpyNympho.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_NYMPHO_FIGHT_BEAT_NYMPHO = new DialogueNode("Harpy nest", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyNympho.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "[harpyNympho.NamePos] flock, just a moment ago cheering and shouting support for their matriarch, falls completely silent as they see her slump to the floor, defeated."
					+ " Walking forwards, you tower over the panting mass of pink feathers, and you hear an erotic moan slip out from between [harpyNympho.namePos] lips as she pushes herself up onto her knees."
					+ " [harpyNympho.speech(Y-You're so... powerful...)]"
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(That's right,)] you respond,"
					+ " [pc.speech(I don't care how you normally determine a flock's leader up here in the Nests; I'm in charge now!)]"
				+ "</p>"
				+ "<p>"
					+ "Your arcane aura is clearly having a strong effect on [harpyNympho.name], and you hear her letting out another lewd moan as she responds,"
					+ " [harpyNympho.speech(Y-Yes, "+(Main.game.getPlayer().isFeminine()?"mistress":"master")+"! I'll do as you ask!)]"
				+ "</p>"
				+ "<p>"
					+ "Seeing their matriarch submit to you, the rest of the harpies fall to their knees, bowing down to you as you issue your orders,"
					+ " [pc.speech(You're all going to calm down, understand?! No more feuds, and no more attacking travellers through the Harpy Nests!)]"
				+ "</p>"
				+ "<p>"
					+ "A chorus of eager agreements rise up from the surrounding crowd, and you find yourself smirking as you look back down at the defeated matriarch at your feet."
					+ " Shuffling closer, [harpyNympho.name] holds up a pink cock-shaped lollipop."
					+ " [harpyNympho.speechNoEffects("+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+"! Please, have one of my special lollipops! This will make you look like me! I-If you want to look like me, that is...)]"
					+ " You take the lollipop in recognition of [harpyNympho.namePos] submission, but you're unsure whether you'll actually use it..."
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(Good girl!)] you say. [pc.speech(Now, you're going to get your nest to calm down, aren't you?)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyNympho.speech(Yes, "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+"! I'll keep them under control!)]"
				+ "</p>"
				+ "<p>"
					+ "Thanks to your victory over [harpyNympho.name], and the power of your arcane aura, you've been able to subdue this once-troublesome nest!"
					+ " Looking down at the matriarch, you wonder if you should publicly prove to all these harpies who's in control here..."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("Sex", "Have dominant sex with [harpyNympho.name].",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyNympho.class)),
						null,
						null), HARPY_NEST_NYMPHO_AFTER_SEX, "<p>"
							+ "Eager to show this nest who's in charge, you reach down and grab [harpyNympho.namePos] wings."
							+ " Pulling her to her feet, you step forwards, before planting a deep kiss on her [harpyNympho.lips+]."
						+ "</p>"
						+ "<p>"
							+ "[harpyNympho.Name] responds to your dominant move by letting out an excited squeal, and, enthusiastically wrapping her wings around your back, she passionately starts returning your kiss..."
						+ "</p>");
							
				} else if (index == 0) {
					return new Response("Leave", "Tell [harpyNympho.name] that you'll be back later.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_nympho_exterior")) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that your work here is done, you turn around and start to walk back the way you came."
									+ " You hear [harpyNympho.name] calling out to her harpies as you leave,"
									+ " [harpyNympho.speech(That's our new leader now! Show some respect!)]"
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
	
	public static final DialogueNode HARPY_NEST_NYMPHO_AFTER_SEX = new DialogueNode("Harpy nest", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyNympho.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(HarpyNympho.class)) >= Main.game.getNpc(HarpyNympho.class).getOrgasmsBeforeSatisfied()) {
				return "<p>"
							+ "As you step back from [harpyNympho.name], she sinks to the floor, totally worn out from her orgasm"+(Main.sex.getNumberOfOrgasms(Main.game.getNpc(HarpyNympho.class)) > 1?"s":"")+"."
							+ " The surrounding harpies, having watched the whole thing, kneel in submission as you finish with their matriarch."
						+ "</p>";
			} else {
				return "<p>"
							+ "As you step back from [harpyNympho.name], she sinks to the floor, letting out a desperate whine as she realises that you've finished with her."
							+ " Her feathered hands dart down between her legs, and she frantically starts masturbating as she seeks to finish what you started."
							+ " The surrounding harpies, having watched the whole thing, kneel in submission as you finish with their matriarch."
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Leave", "Having had your fun, you decide to leave.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_nympho_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Having put [harpyNympho.name] in her place, you walk back across the platform, quickly finding yourself on the outskirts of the nest once again."
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
}
