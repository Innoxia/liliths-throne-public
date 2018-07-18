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
public class HarpyNestBimbo {

	public static final DialogueNodeOld HARPY_NEST_BIMBO = new DialogueNodeOld("Harpy nest", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}
		
		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos] nest";
		}

		@Override
		public String getContent() {
			if (Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimboEncountered)) {
					return "<p>"
								+ "Due to the ongoing arcane storm, [bimboHarpy.namePos] nest is completely deserted."
								+ " Her entire flock has retreated into the safety of the upper-floor of the building below, leaving you with no choice but to return at another time if you wanted to speak to her."
							+ "</p>";
				} else {
					return "<p>"
							+ "Due to the ongoing arcane storm, this nest is completely deserted."
							+ " The entire flock has retreated into the safety of the upper-floor of the building below, leaving you with no choice but to return at another time if you wanted to speak to the matriarch of this particular nest."
						+ "</p>";
				}
				
			} else {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimboEncountered)) {
					return "<p>"
							+ "You find yourself standing on the outskirts of [bimboHarpy.namePos] nest; one of the largest and most populous of all the nests in Dominion."
							+ " Huge, multi-level platforms extend across the roofs of several buildings, with colourful canvas awnings erected to shield the flock from the elements."
							+ (Main.game.isDayTime()
									?""
									:" A series of bright, arcane-powered lights illuminate the entire area, and from what you can see, the harpies here are just as active at night as they are during daylight hours.")
						+ "</p>"
						+ "<p>"
							+ "Whereas most nests contain a variety of differently-coloured harpies, the overwhelming majority of [bimboHarpy.namePos] flock have, in an attempt to copy their matriarch, had their feathers dyed bleach-blonde."
							+ " Looking closer, you see that their proportions are also similar to [bimboHarpy.namePos]."
							+ " From those that you've seen in other nests, you know that the average breast size for a harpy is about a B-cup, but the ones in this particular nest seem to all be at least a D-cup or bigger."
							+ " Their hips and asses are also far larger than the average, leading you to the obvious conclusion that this particular nest is devoted to imitating their leader."
						+ "</p>"
						+ "<p>"
							+ "Up on one of the highest platforms, you see a large gathering of harpies surrounding [bimboHarpy.name]."
							+ " It seems as though the residents of this particular nest aren't too bothered about outsiders, and if you had any business with her, it would be quite easy to approach her platform."
						+ "</p>";
				} else {
					return "<p>"
							+ "You find yourself standing on the outskirts of one of the largest harpy nests in Dominion."
							+ " Huge, multi-level platforms extend across the roofs of several buildings, with colourful canvas awnings erected to shield the flock from the elements."
							+ (Main.game.isDayTime()
									?""
									:" A series of bright, arcane-powered lights illuminate the entire area, and from what you can see, the harpies here are just as active at night as they are during daylight hours.")
						+ "</p>"
						+ "<p>"
							+ "Whereas most nests contain a variety of differently-coloured harpies, the overwhelming majority of this particular flock have bleach-blonde feathers."
							+ " Looking closer, you see that their proportions are rather unusual as well."
							+ " From those that you've seen in other nests, you know that the average breast size for a harpy is about a B-cup, but the ones in this particular nest seem to all be at least a D-cup or bigger."
							+ " Their hips and asses are also far larger than the average, leading you to the obvious conclusion that this particular nest prizes large proportions."
						+ "</p>"
						+ "<p>"
							+ "Up on one of the highest platforms, you see a large gathering of harpies surrounding what must be this nest's matriarch."
							+ " It seems as though the residents of this particular nest aren't too bothered about outsiders, and if you had any business with her, it would be quite easy to approach the matriarch's platform."
						+ "</p>";
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_HARPY_PACIFICATION)) {
					return new Response("Approach [bimboHarpy.name]", "You have no need to talk to the matriarch of this nest.", null);
					
				} else if (Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimboEncountered)) {
						return new Response("Approach [bimboHarpy.name]", "If you want to talk to [bimboHarpy.name], you'll have to come back after the arcane storm has passed.", null);
					} else {
						return new Response("Approach matriarch", "If you want to talk to the matriarch, you'll have to come back after the arcane storm has passed.", null);
					}
					
				} else {
					if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimboEncountered)) {
						return new Response("Approach [bimboHarpy.name]", "Walk to the centre of the nest and talk to [bimboHarpy.name].", HARPY_NEST_BIMBO_APPROACH);
					} else {
						return new Response("Approach matriarch", "Walk to the centre of the nest and talk to the matriarch.", HARPY_NEST_BIMBO_APPROACH);
					}
				}
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_BIMBO_APPROACH = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimboEncountered)) {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimboPacified)) {
					return "<p>"
								+ "Deciding that you should go and pay [bimboHarpy.name] another visit, you set off across the nest."
								+ " Most of the bimbo harpies recognise you as [bimboHarpy.namePos] "+(Main.game.getPlayer().isFeminine()?"mistress":"master")+", and submissively bow their heads or drop to their knees as you pass."
								+ " Reaching the staircase that leads to the upper platform, you quickly scale the stairs, and as you reach the top, you're greeted by a familiar sight."
							+ "</p>"
							+ "<p>"
								+ "[bimboHarpy.Name] is lying down on a sunbed, surrounded by her inner-circle of harpy bimbos."
								+ " [bimboHarpyCompanion.Name] is obediently grooming her matriarch's beautiful bleach-blonde feathers, giggling and whispering with her mistress as she works."
								+ " As you approach, she suddenly notices you, and cries out,"
								+ " [bimboHarpyCompanion.speechNoEffects(Mistress! Like, [pc.name] is back!)]"
							+ "</p>"
							+ "<p>"
								+ "[bimboHarpy.Name] instantly jumps up off the sunbed, and, rushing forwards, she throws herself to her knees at your [pc.feet]."
								+ " Looking up with her big [bimboHarpy.eyeColour] eyes, she pushes out her chest and bites her lip."
							+ "</p>"
							+ "<p>"
								+ "[bimboHarpy.speechNoEffects(Ah! "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+"! I've been, like, super good and stuff!)] she happily squeals."
										+ " [bimboHarpy.speechNoEffects(We've all been, like, super chilled out and stuff, right girls?!)]"
							+ "</p>"
							+ "<p>"
								+ "A chorus of eager voices cry out in agreement, and you can't help but smile down at [bimboHarpy.name] as she shuffles a little closer to you."
							+ "</p>"
							+ "<p>"
								+ "[bimboHarpy.speechNoEffects(Like, erm, I wanna, like, show my girls how good I am!)] she moans, barely able to contain the excitement in her voice."
										+ " [bimboHarpy.speechNoEffects(I mean, like, only if you want to and stuff!)]"
							+ "</p>"
							+ "<p>"
								+ "One of her feathered hands sinks down between her legs, and she leans back on her knees, presenting her panties to you as she starts rubbing at the fabric."
								+ " Several members of her inner-circle start mimicking her behaviour, and, shuffling ever closer, [bimboHarpy.name] moans out loud,"
								+ " [bimboHarpy.speechNoEffects(Ah! Please! Fuck me!)]"
							+ "</p>";
					
				} else {
					return "<p>"
							+ "Deciding that you should go and pay [bimboHarpy.name] another visit, you set off across the nest."
							+ " A couple of the bimbo harpies that you pass recognise you from before, and start whispering to one another as you pass."
							+ " Reaching the staircase that leads to the upper platform, you quickly scale the stairs, and as you reach the top, you're greeted by a familiar sight."
						+ "</p>"
						+ "<p>"
							+ "[bimboHarpy.Name] is lying down on a sunbed, surrounded by her inner-circle of harpy bimbos."
							+ " [bimboHarpyCompanion.Name] is obediently grooming her matriarch's beautiful bleach-blonde feathers, giggling and whispering with her mistress as she works."
							+ " As you approach, she suddenly notices you, and cries out,"
							+ " [bimboHarpyCompanion.speechNoEffects(Mistress! Like, that [pc.race] is back!)]"
						+ "</p>"
						+ "<p>"
						+ (Main.game.getPlayer().getFemininityValue()>=Femininity.FEMININE_STRONG.getMinimumFemininity()
							?"Letting out an annoyed sigh, [bimboHarpy.name] props herself up on the sunbed, and, raising her aviators to look you up-and-down, she makes a dismissive gesture."
								+ " [bimboHarpy.speechNoEffects(Eugh! Like, what are <i>you</i> doin' here again? Why don't you, like go get your own flock or something!"
								+ " You're, like, being super rude and stuff comin' here and flaunting yourself in front of my girls!)]"
							:"Letting out an annoyed sigh, [bimboHarpy.name] props herself up on the sunbed, and, raising her aviators to look you up-and-down, she makes a dismissive gesture."
								+ " [bimboHarpy.speechNoEffects(Eugh! Like, what are <i>you</i> doin' here again? Y'know, you're like, super annoying and stuff!)]")
						+ "</p>";
				}
			
			} else {
				return "<p>"
						+ "This nest looks like the home of one of the matriarchs you agreed to try and calm down."
						+ " Deciding that you should do as the enforcer sergeant asked, you set off across the nest towards the staircase leading to the upper platform."
					+ "</p>"
					+ "<p>"
						+ "As you walk through the nest, you take a closer look at this peculiar, bleach-blonde flock."
						+ " Just as you saw from the outskirts, most of these harpies have larger-than-average breasts, hips, and asses, but it's the snippets of conversation that you overhear that reveal their true nature."
					+ "</p>"
					+ "<p>"
						+ "[style.speechFeminine(...yeah, and, like, she was soooo not pretty...)]<br/>"
						+ "[style.speechFeminine(...uh-huh, and, like, her feathers were like, totally blech!...)]<br/>"
						+ "[style.speechFeminine(...and he was, like, 'I'll do whatever you say!', and I was like, 'Uhh, duh!'...)]<br/>"
					+ "</p>"
					+ "<p>"
						+ "Combined with their large assets and choice of feather colour, you're left with no doubt as to what's going on here."
						+ " Every single one of this nest's occupants is a bimbo."
						+ " Before you know it, you've reached the staircase that leads to the upper platform, and, taking one look back at the crowd of bimbos, you set off up the the stairs, and quickly reach the top."
					+ "</p>"
					+ "<p>"
						+ "You'd already seen a picture of [bimboHarpy.name] in the enforcer's papers, but even if you hadn't, it's clearly obvious who the matriarch is."
						+ " With large, perfectly shaped breasts, the body of a super-model, and one of the most gorgeous faces you've ever seen, [bimboHarpy.name] is the centre of attention."
						+ " Her inner-circle of bimbo harpies are gathered around her, vying for their leader's attention as they giggle and agree with every word she speaks."
						+ " One harpy in particular seems to be the closest to her, and it's this particular bimbo who's the first one to notice your approach."
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpyCompanion.speechNoEffects([bimboHarpy.Name]! There's, like, some [pc.race] over there!)]"
					+ "</p>"
					+ "<p>"
						+ "Instantly, all eyes are on you, and [bimboHarpy.Name] stand up from the sunbed upon which she was lying."
						+ " Stepping forwards, she rests one of her feathered hands on her hips, striking a pose as she speaks,"
						+ (Main.game.getPlayer().getFemininityValue()>=Femininity.FEMININE_STRONG.getMinimumFemininity()
							?" [bimboHarpy.speechNoEffects(Like, who do you think you are?! I hope you're not, like, thinkin' of tryin' to steal girls from my flock!"
									+ " You, like, might have the looks, but they aren't gonna listen to some stupid slut like you! What do you, like, even want? Coming up here uninvited is, like, super rude y'know?!)]"
							:" [bimboHarpy.speechNoEffects(Like, who do you think you are?! Coming up here uninvited is, like, super rude y'know?!)]")
					+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimboPacified)) {
				if (index == 1) {
					return new ResponseSex("Sex", "Have dominant sex with [bimboHarpy.name].",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getHarpyBimbo(), SexPositionSlot.STANDING_SUBMISSIVE))),
							HARPY_NEST_BIMBO_AFTER_SEX,
							"<p>"
								+ "Eager to put [harpyBimbo.name] in her place in front of her inner-circle, you reach down and grab her by her wings."
								+ " Pulling her to her feet, you step forwards, planting a deep kiss on her [harpyBimbo.lips+] and drawing a series of excited giggles from the surrounding bimbo harpies."
							+ "</p>"
							+ "<p>"
								+ "[harpyBimbo.Name] responds to your dominant move by wrapping her wings around your back, grinding herself up against you as she passionately returns your kiss..."
							+ "</p>");
						
				} else if (index == 0) {
					return new Response("Leave", "Tell [bimboHarpy.name] that you'll be back later.", HARPY_NEST_BIMBO) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Deciding that now isn't the best time to be doing this, you turn around and head back down the stairs."
										+ " You hear [bimboHarpy.name] moaning to her harpies as you leave,"
										+ " [bimboHarpy.speechNoEffects(Awww! Like, I just wanna get fucked in front of you all!)]"
									+ "</p>"
									+ "<p>"
										+ "Ignoring her words, you continue down the steps and back across the main platform, quickly finding yourself back on the outskirts of the nest."
									+ "</p>");
						}
					};
						
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Talk", "Try to convince [bimboHarpy.name] to calm down.", HARPY_NEST_BIMBO_TALK) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.bimboEncountered);
						}
					};
						
				} else if (index == 2) {
					return new Response("Bimbo queen", "This bitch is, like, super not cool. You should totally convince the nest that you should be their queen!", HARPY_NEST_BIMBO_QUEEN,
							Util.newArrayListOfValues(Fetish.FETISH_BIMBO), null, null, Femininity.FEMININE_STRONG, null) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.bimboEncountered);
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.bimboPacified);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.HARPY_MATRIARCH_BIMBO_LOLLIPOP), false, true));
							
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
					return new Response("Call her ugly", "You know that this would be a terrible idea...", HARPY_NEST_BIMBO_UGLY) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.bimboEncountered);
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
	
				} else if (index == 0) {
					return new Response("Leave", "Tell [bimboHarpy.name] that you'll be back later.", HARPY_NEST_BIMBO) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.bimboEncountered);
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
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_BIMBO_TALK = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(I'm here to talk to you about all the recent unrest around here. The enforcers are having a hard time keeping the peace, and it would be really helpful if you could get your flock to calm down a little,)]"
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
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.HARPY_MATRIARCH_BIMBO_LOLLIPOP), false, true));
						
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
				return new Response("Force compliance", "If you want these harpies to chill out, it looks as though you'll have to do it by force...", HARPY_NEST_BIMBO_FIGHT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
					
			} else if (index == 0) {
				return new Response("Leave", "Tell [bimboHarpy.name] that you'll be back later.", HARPY_NEST_BIMBO) {
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
	
	public static final DialogueNodeOld HARPY_NEST_BIMBO_UGLY = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

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
				return new ResponseCombat("Fight", "[bimboHarpyCompanion.Name] rushes to do her matriarch's bidding!", Main.game.getHarpyBimboCompanion());
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_BIMBO_QUEEN = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

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
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getHarpyBimbo(), SexPositionSlot.STANDING_SUBMISSIVE))),
						HARPY_NEST_BIMBO_AFTER_SEX,
						"<p>"
							+ "Eager to put [harpyBimbo.name] in her place in front of her inner-circle, you reach down and grab her by her wings."
							+ " Pulling her to her feet, you step forwards, planting a deep kiss on her [harpyBimbo.lips+] and drawing a series of excited giggles from the surrounding bimbo harpies."
						+ "</p>"
						+ "<p>"
							+ "[harpyBimbo.Name] responds to your dominant move by wrapping her wings around your back, grinding herself up against you as she passionately returns your kiss..."
						+ "</p>");
						
			} else if (index == 0) {
				return new Response("Leave", "Tell [bimboHarpy.name] that you'll be back later.", HARPY_NEST_BIMBO) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that your work here is done, you turn around and head back down the stairs."
									+ " You hear [bimboHarpy.name] shouting at the harpies as you leave,"
									+ " [bimboHarpy.speechNoEffects(Like, be quiet! <i>[pc.sheIs</i> the new leader around here, got it?!)]"
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
	
	public static final DialogueNodeOld HARPY_NEST_BIMBO_FIGHT = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

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
				return new ResponseCombat("Fight", "[bimboHarpyCompanion.Name] rushes to do her matriarch's bidding!", Main.game.getHarpyBimboCompanion());
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_BIMBO_FIGHT_LOSE = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "You fall to the floor, totally beaten."
						+ " As you collapse, [bimboHarpyCompanion.name] jumps down on top of you, pinning you to the floor as she calls out to her matriarch,"
						+ " [bimboHarpyCompanion.speechNoEffects([bimboHarpy.Name]! Like, I did it! I got [pc.herHim]!)]"
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.speechNoEffects(Good girl, [bimboHarpyCompanion.name]!)] you hear her respond."
						+ " [bimboHarpy.speechNoEffects(Like, help her out, girls! It's time to teach this little [pc.race] a lesson!)]"
					+ "</p>"
					+ "<p>"
						+ "As [bimboHarpyCompanion.name] continues holding you down, the rest of [bimboHarpy.NamePos] inner-circle moves in."
						+ " Several of them help to pin your [pc.arms] and [pc.legs] to the floor, giggling all the while."
					+ "</p>"
					+"<p>"
						+ "[bimboHarpy.speechNoEffects(Y'know, I think I understand why, like, you're so angry and rude and stuff!)]"
						+ " [bimboHarpy.name] giggles, stepping forwards to tower over you."
						+ " [bimboHarpy.speechNoEffects(You're just, like, frustrated and jealous of how super hot all my girls are!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speechNoEffects(Let me go!)] you shout, struggling against the bimbo harpies."
						+ " You're too weak to shake them off, however, and they easily continue to pin you to the floor, holding you quite still as [bimboHarpy.name] stoops down next to your face."
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.speechNoEffects(Like, <i>you're</i> the one who needs to calm down now!)]"
						+ " she laughs."
						+ " [bimboHarpy.speechNoEffects(Hold [pc.her] mouth open [bimboHarpyCompanion.name]! I think this little troublemaker needs one of our special lollipops!)]"
					+ "</p>"
					+ "<p>"
						+ "Quickly grabbing your [pc.face+], [bimboHarpyCompanion.name] tries to pull your mouth open, laughing at your fruitless attempt to shake your head free."
						+ " Out of the corner of your eye, you see [bimboHarpy.name] pull out a pink-and-white swirly lollipop, and, leaning in, she forcefully tries to shove it into your mouth."
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.speechNoEffects(This is gonna, like, totally chill you out!)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Lips sealed", "Don't let [bimboHarpy.Name] get that strange lollipop into your mouth...", HARPY_NEST_BIMBO_FIGHT_LOSE_PUNISHMENT_NO_TF);
					
			} else if (index == 2) {
				return new Response("Open wide",
						"Allow [bimboHarpy.Name] to push the lollipop into your mouth... [style.boldBad(Warning:)] <b>Due to the nature of harpies needing a special form, this transformation bypasses TF preferences!</b>",
						HARPY_NEST_BIMBO_FIGHT_LOSE_PUNISHMENT,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING),
						Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append( "<p>"
								+ "Obediently doing as you're told, you open your mouth and let the lollipop slide past your [pc.lips+]."
								+ " An intense, sweet flavour hits your tongue, and you find that it's quite unlike anything you've ever tasted before."
								+ " Before you know what you're doing, you're wrapping your [pc.lips] around the delicious candy, letting out little whining noises as you find yourself unable to stop sucking and licking it..."
							+ "</p>"
							+ItemEffectType.BIMBO_LOLLIPOP.applyEffect(null, null, null, 0, Main.game.getHarpyBimbo(), Main.game.getPlayer(), null));
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_BIMBO_FIGHT_BEAT_GF = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

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
				return new ResponseCombat("Fight", "[bimboHarpy.Name] looks furious as she launches her attack on you!", Main.game.getHarpyBimbo());
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_BIMBO_FIGHT_LOSE_TO_MATRIARCH = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "You fall to the floor, totally beaten."
					+ " As you collapse, [bimboHarpy.name] orders her flock to hold you still, and, quickly jumping down on top of you, you're quickly pinned to the floor by several bimbo harpies."
				+ "</p>"
				+ "<p>"
					+ "[bimboHarpy.speechNoEffects(Like, [bimboHarpyCompanion.name], are you ok?!)] you hear [bimboHarpy.name] softly calling out to her friend."
					+ " [bimboHarpy.speechNoEffects(Come on, it's time to teach this little [pc.race] a lesson!)]"
				+ "</p>"
				+ "<p>"
					+ "As the members of [bimboHarpy.namePos] inner-circle continue to hold you down, you hear the bimbo matriarch and her companion walking towards you."
					+ " The bimbo harpies start giggling as you try to wriggle free, but you're too weak from the fight to offer any real resistance."
				+ "</p>"
				+ "<p>"
					+ "[bimboHarpy.speechNoEffects(Time for some revenge [bimboHarpyCompanion.name]! We're gonna, like, have to punish this one!)]"
					+ " [bimboHarpy.name] shouts out, and, as her companion moves to help the other bimbos hold you down, she laughs,"
					+ " [bimboHarpy.speechNoEffects(Time for some fun!)]"
				+ "</p>"
				+"<p>"
					+ "[bimboHarpy.speechNoEffects(Y'know, I think I understand why, like, you're so angry and rude and stuff!)]"
					+ " [bimboHarpy.name] giggles, stepping forwards to tower over you."
					+ " [bimboHarpy.speechNoEffects(You're just, like, frustrated and jealous of how super hot all my girls are!)]"
				+ "</p>"
				+ "<p>"
					+ "[pc.speechNoEffects(Let me go!)] you shout, struggling against the bimbo harpies."
					+ " You're too weak to shake them off, however, and they easily continue to pin you to the floor, holding you quite still as [bimboHarpy.name] stoops down next to your face."
				+ "</p>"
				+ "<p>"
					+ "[bimboHarpy.speechNoEffects(Like, <i>you're</i> the one who needs to calm down now!)]"
					+ " she laughs."
					+ " [bimboHarpy.speechNoEffects(Hold [pc.her] mouth open [bimboHarpyCompanion.name]! I think this little troublemaker needs one of our special lollipops!)]"
				+ "</p>"
				+ "<p>"
					+ "Quickly grabbing your [pc.face+], [bimboHarpyCompanion.name] tries to pull your mouth open, laughing at your fruitless attempt to shake your head free."
					+ " Out of the corner of your eye, you see [bimboHarpy.name] pull out a pink-and-white swirly lollipop, and, leaning in, she forcefully tries to shove it into your mouth."
				+ "</p>"
				+ "<p>"
					+ "[bimboHarpy.speechNoEffects(This is gonna, like, totally chill you out!)]"
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Lips sealed", "Don't let [bimboHarpy.Name] get that strange lollipop into your mouth...", HARPY_NEST_BIMBO_FIGHT_LOSE_PUNISHMENT_NO_TF);
					
			} else if (index == 2) {
				return new Response("Open wide",
						"Allow [bimboHarpy.Name] to push the lollipop into your mouth... [style.boldBad(Warning:)] <b>Due to the nature of harpies needing a special form, this transformation bypasses TF preferences!</b>",
						HARPY_NEST_BIMBO_FIGHT_LOSE_PUNISHMENT,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING),
						Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append( "<p>"
								+ "Obediently doing as you're told, you open your mouth and let the lollipop slide past your [pc.lips+]."
								+ " An intense, sweet flavour hits your tongue, and you find that it's quite unlike anything you've ever tasted before."
								+ " Before you know what you're doing, you're wrapping your [pc.lips] around the delicious candy, letting out little whining noises as you find yourself unable to stop sucking and licking it..."
							+ "</p>"
							+ItemEffectType.BIMBO_LOLLIPOP.applyEffect(null, null, null, 0, Main.game.getHarpyBimbo(), Main.game.getPlayer(), null));
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_BIMBO_FIGHT_BEAT_BIMBO = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

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
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getHarpyBimbo(), SexPositionSlot.STANDING_SUBMISSIVE))),
						HARPY_NEST_BIMBO_AFTER_SEX,
						"<p>"
							+ "Eager to put [harpyBimbo.name] in her place in front of her inner-circle, you reach down and grab her by her wings."
							+ " Pulling her to her feet, you step forwards, planting a deep kiss on her [harpyBimbo.lips+] and drawing a series of excited giggles from the surrounding bimbo harpies."
						+ "</p>"
						+ "<p>"
							+ "[harpyBimbo.Name] responds to your dominant move by wrapping her wings around your back, grinding herself up against you as she passionately returns your kiss..."
						+ "</p>");
						
			} else if (index == 0) {
				return new Response("Leave", "Tell [bimboHarpy.name] that you'll be back later.", HARPY_NEST_BIMBO) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that your work here is done, you turn around and head back down the stairs."
									+ " You hear [bimboHarpy.name] shouting at the harpies as you leave,"
									+ " [bimboHarpy.speechNoEffects(Like, be quiet! <i>[pc.sheIs</i> the new leader around here, got it?!)]"
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
	
	public static final DialogueNodeOld HARPY_NEST_BIMBO_FIGHT_LOSE_PUNISHMENT_NO_TF = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos] nest";
		}
		
		@Override
		public String getContent() {
				return "<p>"
							+ "With a determined yank, you finally manage to pull one of your [pc.arms] free from the harpy's clutches."
							+ " Before they're able to restrain you again, you grab the lollipop out of [bimboHarpy.namePos] feathered hand, before smashing it on the floor beneath you."
						+ "</p>"
						+ "<p>"
							+ "[bimboHarpy.speechNoEffects(Aaah! You stupid whore!)]"
							+ " [bimboHarpy.name] screams, jumping to her feet and towering over you."
							+ " [bimboHarpy.speechNoEffects(You're gonna, like, pay for wasting that! You're gonna, like, do <i>exactly</i> what we say, or else you're gonna be our little pet forever!)]"
						+ "</p>"
						+"<p>"
							+ "After flapping her wings and jumping around in anger for a little while, [bimboHarpy.name] storms off to elsewhere in the nest."
							+ " Only a few moments later, she returns and kneels down beside you, setting a makeup bag down next to your face before letting out a giggle."
							+ " [bimboHarpy.speechNoEffects(So, like, first, how about us girls give you a makeover?!)]"
						+ "</p>"
						+ "<p>"
							+ "Any protests or objections that leave your mouth are drowned out by a chorus of laughter, and as one, the group of bimbo harpies descend upon you..."
						+ "</p>"
						+ "<p>"
							+ "For the next few hours, you're the centre of the harpies' attention."
							+ " Applying heavy layers of makeup, dressing you up in frilly clothes, and parading you around in front of one another are amongst the least humiliating things you suffer."
							+ " Eventually, however, [bimboHarpy.name] loses interest, and one-by-one, the rest of the harpies move onto other things."
						+ "</p>"
						+ "<p>"
							+ "As the last bimbo declares that she's bored of playing with you, the matriarch walks over to you."
							+ " [bimboHarpy.speechNoEffects(I hope you, like, learned your lesson! Now get out of my nest!)]"
						+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Thrown out", "Having had their fun, you're quickly thrown out of the nest.", HARPY_NEST_BIMBO) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Finally given an opportunity to escape, you do as [bimboHarpy.name] commands."
									+ " Running down the staircase, you leave the harpies' mocking laughter behind, and, dashing across the lower platform, you quickly find yourself back on the outskirts of [bimboHarpy.namePos] nest..."
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_BIMBO_FIGHT_LOSE_PUNISHMENT = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			return
				"<p>"
					+ "As the lollipop's transformative effects come to an end, the harpies' grip on your [pc.arms] and [pc.legs] loosens."
					+ " Blinking slowly a few times, you let out an exhausted little moan."
					+ " [pc.speechNoEffects(Like, I feel all bubbly inside! That was, like, super intense!)]"
				+ "</p>"
				+ "<p>"
					+ "The flock of bimbo harpies bursts out into laughter, and the ones who were holding you down finally release you before backing off."
					+ " Scrambling to your knees, you find yourself looking up at [bimboHarpy.Name] as she continues towering over you."
				+ "</p>"
				+ "<p>"
					+ "[harpyBimbo.speechNoEffects(Lookin' good! Now, before you get out of my nest, you're gonna tell me how sorry you are!)]"
				+ "</p>"
				+ "<p>"
					+ "With the effects of the lollipop still lingering in your mind, you find yourself blurting out,"
					+ " [pc.speechNoEffects(Like, I'm so super sorry for causing you so much trouble and stuff! Please, [harpyBimbo.name], forgive me!)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyBimbo.Name] grins down at you, and, to the background noise of dozens of bimbo harpies laughing at you, she shouts,"
					+ " [harpyBimbo.speechNoEffects(That's better! Now, like, I can't have you leaving lookin' so, like, plain! Us girls need to give you a makeover!)]"
				+ "</p>"
				+ "<p>"
					+ "For the next few hours, you're the centre of the harpies' attention."
					+ " Applying heavy layers of makeup, dressing you up in frilly clothes, and parading you around in front of one another are amongst the least humiliating things you suffer."
					+ " Eventually, however, [bimboHarpy.name] loses interest, and one-by-one, the rest of the harpies move onto other things."
				+ "</p>"
				+ "<p>"
					+ "As the last bimbo declares that she's bored of playing with you, the matriarch walks over to you."
					+ " [bimboHarpy.speechNoEffects(I hope you, like, learned your lesson! Now get out of my nest!)]"
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Thrown out", "Having had their fun, you're quickly thrown out of the nest.", HARPY_NEST_BIMBO) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Finally given an opportunity to escape, you do as [bimboHarpy.name] commands."
									+ " Running down the staircase, you leave the harpies' mocking laughter behind, and, dashing across the lower platform, you quickly find yourself back on the outskirts of [bimboHarpy.namePos] nest..."
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HARPY_NEST_BIMBO_AFTER_SEX = new DialogueNodeOld("Harpy nest", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos] nest";
		}
		
		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				return "<p>"
							+ "As you step back from [bimboHarpy.name], she sinks to the floor, totally worn out from her orgasm"+(Sex.getNumberOfOrgasms(Sex.getActivePartner()) > 1?"s":"")+"."
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
				return new Response("Leave", "Having had your fun, you decide to leave.", HARPY_NEST_BIMBO) {
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
