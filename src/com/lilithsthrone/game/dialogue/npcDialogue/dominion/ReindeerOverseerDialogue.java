package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.96
 * @version 0.1.96
 * @author Innoxia
 */
public class ReindeerOverseerDialogue {
	
	private static NPC reindeer() {
		return Main.game.getActiveNPC();
	}
	
	private static Response getDefaultResponses(int index) {
		if(index == 1) {
			return new ResponseTrade("Trade", "Ask [npc.name] what Yuletide presents [npc.sheIs] selling.", reindeer()) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().addReindeerEncountered(reindeer().getId());
				}
			};
			
		} else if(index == 2) {
			if(Main.game.getDialogueFlags().hasWorkedForReindeer(reindeer().getId())) {
				return new Response("Work", "You've already helped [npc.name] to finish all of the work [npc.she] had today, so you'll need to come back tomorrow if you wanted to work for [npc.herHim] again.", null);
				
			} else {
				return new Response("Work", "Offer to work with the reindeer-morphs.", ENCOUNTER_WORK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().addReindeerEncountered(reindeer().getId());
						Main.game.getDialogueFlags().addReindeerDailyWorkedFor(reindeer().getId());
					}
				};
			}
			
		} else if(index == 3) {
			if(!Main.game.getDialogueFlags().hasWorkedForReindeer(reindeer().getId())) {
				return new Response("Relieve stress", "[npc.Name] is far too busy to take any time off work right now. Perhaps if you helped out first, [npc.she]'d have time to have sex with you...", null);
				
			} else {
				return new ResponseSex("Relieve stress",
					"Ask [npc.name] if [npc.she]'d like to blow off some steam with you.",
					true, true,
					new SMStanding(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
							Util.newHashMapOfValues(new Value<>(reindeer(), SexPositionSlot.STANDING_SUBMISSIVE))),
					AFTER_SEX,
					"<p>"
						+ "Putting on your most seductive voice, you step close to [npc.name] and ask,"
						+ " [pc.speech(You know, if you're feeling stressed from all this work, maybe I could help you to blow off some steam?)]"
					+ "</p>"
					+ "<p>"
						+ "A desperate little whine escapes from [npc.her] mouth, and before you can say anything else, the [npc.race] grabs you by the [pc.arm] and pulls you towards where [npc.her] cart is parked."
						+ " Covering the distance in just a few long strides, [npc.name] leads you around to the other side of the vehicle,"
							+ " where you discover that the many wooden boxes which constitute the cart's cargo blocks the view down a narrow alley from the rest of the street."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(Ah, fuck! I'm so horny!)]"
						+ " [npc.name] whines, pulling you a little way down the alley before turning and wrapping [npc.her] arms around you."
						+ " [npc.speech(I was told Dominion was the place to go for an easy fuck, but I've been so caught up in work that I haven't met anyone but you! Please, let's just fuck already!)]"
					+ "</p>"
					+ "<p>"
						+ "You can't say no to that, and as [npc.name] presses [npc.her] [npc.lips] against yours, you return the [npc.race]'s passionate embrace..."
					+ "</p>") {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().addReindeerEncountered(reindeer().getId());
						}
					};
			}
			
		} else if(index==0) {
			return new Response("Leave", "Tell [npc.name] that you might come back another time, before taking your leave.", ENCOUNTER_START){
				@Override
				public void effects() {
					Main.game.getDialogueFlags().addReindeerEncountered(reindeer().getId());
				}
				@Override
				public DialogueNodeOld getNextDialogue(){
					return Main.game.getDefaultDialogueNoEncounter();
				}
			};
			
		} else {
			return null;
		}
	}
	
	public static final DialogueNodeOld ENCOUNTER_START = new DialogueNodeOld("Reindeer Overseer", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getDialogueFlags().hasEncounteredReindeer(reindeer().getId())) {
				UtilText.nodeContentSB.append("<p>"
							+ "Wanting to speak with [npc.name] again, you look around for a little while, before eventually spotting [npc.herHim] issuing orders to some of [npc.her] workers."
							+ " Walking up to [npc.herHim], you call out a greeting,"
							+ " [pc.speech(Hi, [npc.name]!)]"
						+ "</p>"
						+ "<p>"
							+ "[npc.Name] turns around to see who's calling [npc.herHim], and you see a smile break out across [npc.her] face as [npc.she] sees that it's you."
							+ " [npc.speech(Hello again, [pc.name]!)]"
						+ "</p>"
						+ "<p>"
							+ "You spend a little while making small talk and getting to know [npc.name] a little better."
							+ " Most of [npc.her] interests seem to be related to snow in one way or another, and you're a little relieved when [npc.she] finally brings the conversation to a close,"
							+ " [npc.speech(I really should be getting on with work, but if you wanted to take a look at the goods I've got for sale, I could spare some time for that.)]"
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append("<p>"
							+ "You notice that there are quite a few reindeer-morphs in this part of Dominion; each one of them busily shovelling snow in order to keep the streets clear."
							+ " Deciding that you'd like to have a chat, you walk up to several of them and say hello."
							+ " You're somewhat discouraged, however, when you discover that they're so engrossed in their work that they pay no heed to your attempts to strike up a conversation."
							+ " Just as you're about to give up, a [npc.feminine] voice rings out from behind you,"
							+ " [npc.speech(Can I help?)]"
						+ "</p>"
						+ "<p>"
							+ "Turning around, you find yourself face-to-face with a particularly imposing [npc.race]."
							+ " [npc.She] crosses [npc.her] arms and starts tapping [npc.her] foot on the ground as [npc.she] impatiently continues,"
							+ " [npc.speech(I'm [npc.name], the overseer for the workers in this area. If you're looking for something to buy, we've brought some goods with us, just like we do every year. You fancy a look?)]"
						+ "</p>");
				
				if(Main.game.getDialogueFlags().hasEncounteredAnyReindeers()) {
					UtilText.nodeContentSB.append("<p>"
							+ "Feeling happy now that you've found the overseer for this particular group, you reply,"
							+ " [pc.speech(What sort of goods do you have? I've spoken with another overseer already, and they mostly had Yuletide-related clothing, food and drink, as well as some clothing from the Kitsune forest.)]"
						+ "</p>"
						+ "<p>"
							+ "The [npc.race] smiles."
							+ " [npc.speech(Well, all of us reindeer-morphs travel together, so we've got the same goods as any of the other groups."
								+ " Still, if you'd like to take a look, just let me know.)]"
						+ "</p>");
					
				} else {
					UtilText.nodeContentSB.append("<p>"
							+ "Feeling happy now that you've got an opportunity to talk to one of these reindeer-morphs, you reply,"
							+ " [pc.speech(What sort of goods do you have? And where did all of you come from? I haven't seen reindeer-morphs here in Dominion before.)]"
						+ "</p>"
						+ "<p>"
							+ "The [npc.race] smiles as [npc.she] answers,"
							+ " [npc.speech(We travel here from our homeland out in the frozen tundra every winter."
								+ " We stay here to work until the end of February, which is when it stops snowing, and then we migrate back to the tundra for the rest of the year, which is why you haven't seen us before."
								+ " As to what goods I can offer, we've brought plenty of the food, drink, and clothing that we make in our homeland."
								+ " We travelled through the Kitsune's forest this year, so I also have some of their traditional clothing which we traded for.)]"
						+ "</p>");
				}
			}
			
			if(Main.game.getDialogueFlags().hasWorkedForReindeer(reindeer().getId())) {
				if(Main.game.getDialogueFlags().hasFuckedReindeer(reindeer().getId())) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "[npc.Name] smiles fondly at you as [npc.she] steps closer."
								+ " [npc.speech(Thanks for your help today, [pc.name]; I really appreciate it. You know... I could do with letting off some steam... You want to have a little fun again?)]"
							+ "</p>");
					
				} else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "[npc.Name] smiles fondly at you as [npc.she] steps closer."
								+ " [npc.speech(Thanks for your help today, [pc.name]; I really appreciate it."
									+ " It can get pretty stressful trying to keep all these streets clear, especially when we're so undermanned.)]"
							+ "</p>");
					
				}
				
			} else {
				UtilText.nodeContentSB.append(
					"<p>"
						+ "[npc.Name] lets out a little sigh."
						+ " [npc.speech(We're a little undermanned this year, so if you're looking for work, I can offer a part-time job."
							+ " If not, and if you're not interested in trading, then I really need to get back to work; we've got so much to get done today!)]"
					+ "</p>");
				
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getDefaultResponses(index);
		}
	};
	
	public static final DialogueNodeOld ENCOUNTER_WORK = new DialogueNodeOld("Reindeer Overseer", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Deciding that you'd like to take up [npc.namePos] offer of a part-time job, you ask [npc.herHim],"
						+ " [pc.speech(I'd be willing to work for you for a while, what do you need help with?)]"
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(Almost everything!)]"
						+ " [npc.name] replies, laughing,"
						+ " [npc.speech(but it'd be a lot easier to manage if you choose just one thing to focus on."
							+ " If you're strong, you could help out directly by joining in and shovelling snow."
							+ " If you're any good with the arcane, we've got a few heat-staves on the cart;"
								+ " you can use them to channel your power into a beam of arcane fire, which is very useful for helping to break up sections of densely-packed snow."
							+ " And, finally, if you're better with people than you are with physical or arcane work, then going around between the workers and delivering drinks and words of encouragement would be a great help.)]"
					+ "</p>"
					+ "<p>"
						+ "As you're wondering which task to perform, [npc.name] finishes,"
						+ " [npc.speech(Oh, and I'll pay you based on how well you work, so it'd be best to stick with what you're good at.)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Shovel snow", "Tell [npc.name] that you'll shovel snow with the rest of the workers.", ENCOUNTER_WORK_FINISHED) {
					@Override
					public void effects() {
						int money = (int)(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)*1.5f);
						Main.game.getPlayer().incrementMoney(money);
						
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that you'd be best at shovelling snow, you tell [npc.name] that that's what you'd like to do."
									+ " [npc.She] then leads you over to a cart that's parked on one side of the street, where [npc.she] grabs a shovel and hands it over to you."
									+ " [npc.speech(With your help, we should be finished in a few more hours. Thanks for the assistance!)]"
								+ "</p>"
								+ "<p>"
									+ "Setting off to where the other reindeer-morphs are working, you get stuck in and start shovelling snow.");
						switch(PhysiqueLevel.getPhysiqueLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE))) {
							case ZERO_WEAK: case ONE_AVERAGE:
								Main.game.getTextStartStringBuilder().append(
										" Although it didn't seem too hard at first, you're not able to keep up with the stamina or strength of the reindeer-morphs, and before long you find yourself having to take a break."
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(Don't worry, [pc.name],)]"
											+ " the voice of [npc.name] calls out, and you jump to your feet as you see [npc.herHim] approach,"
											+ " [npc.speech(everyone else here is used to this sort of work, so don't be discouraged if you can't keep up!"
												+ " Just make sure to take a rest if you're feeling tired.)]"
										+ "</p>"
										+ "<p>"
											+ "Knowing that your poor performance will undoubtedly result in a reduction to your wages, you resign yourself to the fact that you're not quite as strong as you thought you were."
											+ " The next few hours pass quickly enough, and by the time the day's work is done, you find yourself completely exhausted."
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(Hi, [pc.name]! Good work today!)]"
											+ " [npc.name] calls out, mercifully not mentioning your sub-par performance as [npc.she] pays you."
											+ " [npc.speech(Here's the "+Util.intToString(money)+" flames you've earned today!)]"
										+ "</p>");
								break;
							case TWO_STRONG: case THREE_POWERFUL:
								Main.game.getTextStartStringBuilder().append(
											" Although it's harder work than you first though it would be, you're able to keep up with the reindeer-morphs, and even manage to shovel a little more snow than a couple of them."
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(Great work, [pc.name],)]"
											+ " the voice of [npc.name] calls out, and you turn around to see [npc.herHim] approach,"
											+ " [npc.speech(everyone else here is used to this sort of work, and you're managing to keep up with them!"
												+ " Just make sure to take a rest if you're feeling tired.)]"
										+ "</p>"
										+ "<p>"
											+ "Knowing that your good performance will undoubtedly result in an increase to your wages, you keep working at the same pace."
											+ " The next few hours pass quickly enough, and by the time the day's work is done, you find yourself satisfied with your performance."
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(Hi, [pc.name]! Great work today!)]"
											+ " [npc.name] calls out, sounding genuinely pleased as [npc.she] pays you."
											+ " [npc.speech(Here's the "+Util.intToString(money)+" flames you've earned today!)]"
										+ "</p>");
								break;
							case FOUR_MIGHTY: case FIVE_HERCULEAN:
								Main.game.getTextStartStringBuilder().append(
										" The work turns out to be far easier than you thought it'd be, and not only are you able to keep up with the reindeer-morphs, the amount of snow that you're able to shovel soon puts them to shame."
									+ "</p>"
									+ "<p>"
										+ "[npc.speech(Wow, [pc.name],)]"
										+ " the voice of [npc.name] calls out, and you turn around to see [npc.herHim] approach,"
										+ " [npc.speech(I've never seen anyone work like that before; you're amazing!"
											+ " Just make sure to take a rest if you're feeling tired.)]"
									+ "</p>"
									+ "<p>"
										+ "Knowing that your excellent performance will undoubtedly result in an increase to your wages, you keep working at the same pace."
										+ " The next few hours pass quickly enough, and by the time the day's work is done, you've cleared the entire street of snow."
									+ "</p>"
									+ "<p>"
										+ "[npc.speech(Hi, [pc.name]! Fantastic work today!)]"
										+ " [npc.name] calls out, sounding extremely happy as [npc.she] pays you."
										+ " [npc.speech(Here's the "+Util.intToString(money)+" flames you've earned!)]"
									+ "</p>");
								break;
						}
					}
				};
				
			} else if(index==2) {
				return new Response("Use heat-stave", "Tell [npc.name] that you'd like to use one of the heat-staves to score out lines in the snow.", ENCOUNTER_WORK_FINISHED) {
					@Override
					public void effects() {
						int money = (int)(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_ARCANE)*1.5f);
						Main.game.getPlayer().incrementMoney(money);

						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that you'd work best by using your arcane power to harness [npc.namePos] 'heat-stave', you tell the [npc.race] that that's what you'd like to do."
									+ " [npc.She] then leads you over to a cart that's parked on one side of the street, where [npc.she] grabs a tall, wooden staff with a curious orange gemstone fixed into one end, before handing it over to you."
									+ " [npc.speech(Just focus your arcane energy into the stave while pointing the bottom at the ground."
										+ " With your help, we should be finished in a few more hours."
										+ " Thanks for the assistance!)]"
								+ "</p>"
								+ "<p>"
									+ "Setting off to the area where the reindeer-morphs will need to clear next, you do as [npc.name] instructed, and point the bottom of the staff at the mounds of snow while focusing your arcane energy into it.");
						
						switch(IntelligenceLevel.getIntelligenceLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_ARCANE))) {
							case ZERO_AIRHEAD: case ONE_AVERAGE:
								Main.game.getTextStartStringBuilder().append(
										" A little jet of hot air shoots out of the staff, and by slowly tracing a path over the snow, you're able to melt it into pre-cut sections, ready for the reindeer-morphs to shovel."
									+ "<p>"
										+ "[npc.speech(Looking good, [pc.name],)]"
										+ " the voice of [npc.name] calls out, and you turn around to see [npc.herHim] approach."
										+ " [npc.speech(With you helping out like this, the rest of the day's work shouldn't take too long.)]"
									+ "</p>"
									+ "<p>"
										+ "Despite [npc.namePos] encouraging words, you can't help but feel as though you're not really harnessing the staff's true power."
										+ " Nevertheless, the next few hours pass quickly enough, and before long the entire street is clear of snow."
									+ "</p>"
									+ "<p>"
										+ "[npc.speech(Hi [pc.name]! Good work today!)]"
										+ " [npc.name] says as [npc.she] pays you."
										+ " [npc.speech(Here's the "+Util.intToString(money)+" flames you've earned!)]"
									+ "</p>");
								break;
							case TWO_SMART: case THREE_BRAINY: 
								Main.game.getTextStartStringBuilder().append(
										" A small jet of flames shoots out of the staff, and by tracing a path over the snow, you're able to quickly melt it into pre-cut sections, ready for the reindeer-morphs to shovel."
									+ "<p>"
										+ "[npc.speech(Looking great, [pc.name],)]"
										+ " the voice of [npc.name] calls out, and you turn around to see [npc.herHim] approach."
										+ " [npc.speech(With you helping out like this, the rest of the day's work shouldn't take too long.)]"
									+ "</p>"
									+ "<p>"
										+ "Encouraged by [npc.namePos] words, you set about harnessing the staff's power to break up the snow."
										+ " The next few hours pass quickly enough, and before long the entire street is clear."
									+ "</p>"
									+ "<p>"
										+ "[npc.speech(Hi, [pc.name]! Great work today!)]"
										+ " [npc.name] beams at you as [npc.she] pays you."
										+ " [npc.speech(Here's the "+Util.intToString(money)+" flames you've earned!)]"
									+ "</p>");
								break;
							case FOUR_GENIUS: case FIVE_POLYMATH:
								Main.game.getTextStartStringBuilder().append(
										" An intense jet of flames blasts out of the staff, and by tracing a path over the snow, you're able to almost-instantly melt it into pre-cut sections, ready for the reindeer-morphs to shovel."
									+ "<p>"
										+ "[npc.speech(Wow, [pc.name]!)]"
										+ " The voice of [npc.name] calls out, and you turn around to see [npc.herHim] approach,"
										+ " [npc.speech(I didn't even realise the heat-stave had that much power in it! I've never seen anyone use it quite like that! With your help, we should be finished in no time!)]"
									+ "</p>"
									+ "<p>"
										+ "Encouraged by [npc.namePos] words, you set about harnessing the staff's power to break up the snow."
										+ " The next few hours pass quickly enough, and before long the entire street is clear."
									+ "</p>"
									+ "<p>"
										+ "[npc.speech(Hi, [pc.name]! Amazing work today!)]"
										+ " [npc.name] beams at you as [npc.she] pays you."
										+ " [npc.speech(Here's the "+Util.intToString(money)+" flames you've earned!)]"
									+ "</p>");
								break;
						}
					}
				};
				
			} else if(index==3) {
				return new Response("Encouragement", "Tell [npc.name] that you'd be best suited for delivering drinks and encouraging the workers.", ENCOUNTER_WORK_FINISHED) {
					@Override
					public void effects() {
						int money = (int)(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)*1.5f);
						Main.game.getPlayer().incrementMoney(money);

						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that you'd work best by delivering drinks and encouraging the workers, you tell the [npc.race] that that's what you'd like to do."
									+ " [npc.She] then leads you over to a cart that's parked on one side of the street, where [npc.she] shows you where the drinks are stored."
									+ " [npc.speech(Just go around asking the workers if they need a drink, and if they do, fetch them one from here."
										+ " With your help, we should be finished in a few more hours. Thanks for the assistance!)]"
								+ "</p>"
								+ "<p>"
									+ "Setting off to the area where the reindeer-morphs are working, you do as [npc.name] instructed, and start asking each one if they'd like a drink.");
						
						switch(PhysiqueLevel.getPhysiqueLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE))) {
							case ZERO_WEAK: case ONE_AVERAGE:
								Main.game.getTextStartStringBuilder().append(
										" The workers are far more demanding than you'd expected, and before long you find yourself struggling to keep up with their requests."
									+ "<p>"
										+ "[npc.speech(Good work, [pc.name],)]"
										+ " the voice of [npc.name] calls out, and you turn around to see [npc.herHim] approach."
										+ " [npc.speech(With you helping out like this, the rest of the day's work shouldn't take too long.)]"
									+ "</p>"
									+ "<p>"
										+ "Despite [npc.namePos] encouraging words, you can't help but feel as though you're not doing very well."
										+ " Nevertheless, the next few hours pass quickly enough, and before long the entire street is clear of snow."
									+ "</p>"
									+ "<p>"
										+ "[npc.speech(Hi, [pc.name]! Good work today!)]"
										+ " [npc.name] says as [npc.she] pays you."
										+ " [npc.speech(Here's the "+Util.intToString(money)+" flames you've earned!)]"
									+ "</p>");
								break;
							case THREE_POWERFUL: case TWO_STRONG:
								Main.game.getTextStartStringBuilder().append(
										" The workers are far more demanding than you'd expected, but despite this, you're still able to keep up with their requests."
									+ "<p>"
										+ "[npc.speech(Great work, [pc.name],)]"
										+ " the voice of [npc.name] calls out, and you turn around to see [npc.herHim] approach."
										+ " [npc.speech(With you helping out like this, the rest of the day's work shouldn't take too long.)]"
									+ "</p>"
									+ "<p>"
										+ "Encouraged by [npc.namePos] words, you set about delivering the drinks that you're carrying, before hurrying off to fetch the next set of orders."
										+ " The next few hours pass quickly enough, and before long the entire street is clear of snow."
									+ "</p>"
									+ "<p>"
										+ "[npc.speech(Hi, [pc.name]! Great work today!)]"
										+ " [npc.name] says as [npc.she] pays you."
										+ " [npc.speech(Here's the "+Util.intToString(money)+" flames you've earned!)]"
									+ "</p>");
								break;
							case FOUR_MIGHTY: case FIVE_HERCULEAN:
								Main.game.getTextStartStringBuilder().append(
										" The workers are far more demanding than you'd expected, but despite this, you're easily able to keep up their requests, and even find time to stop and chat with several of them."
									+ "<p>"
										+ "[npc.speech(Fantastic work, [pc.name],)]"
										+ " the voice of [npc.name] calls out, and you turn around to see [npc.herHim] approach."
										+ " [npc.speech(With you helping out like this, the rest of the day's work shouldn't take too long.)]"
									+ "</p>"
									+ "<p>"
										+ "Encouraged by [npc.namePos] words, you continue delivering drinks to the perpetually-thirsty reindeer-morphs."
										+ " The next few hours pass quickly enough, and before long the entire street is clear of snow."
									+ "</p>"
									+ "<p>"
										+ "[npc.speech(Hi, [pc.name]! Brilliant work today!)]"
										+ " [npc.name] says as [npc.she] pays you."
										+ " [npc.speech(Here's the "+Util.intToString(money)+" flames you've earned!)]"
									+ "</p>");
								break;
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ENCOUNTER_WORK_FINISHED = new DialogueNodeOld("Reindeer Overseer", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed() {
			return 60 * 4;
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(Thanks, [npc.name],)]"
						+ " you respond,"
						+ " [pc.speech(will there be any more work available?)]"
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(Well, we're all finished for today, but come back tomorrow and there's sure to be more to do!)]"
						+ " [npc.name] cheerily responds, and as [npc.she] talks, you can't help but notice that [npc.she] keeps on glancing hungrily down at your body."
						+ " [npc.speech(All this work is really stressful sometimes. You know, since I arrived here in Dominion, you're the first person outside of my work group who I've really talked to!"
							+ " I never get the time to meet new people, or to have any fun...)]"
					+ "</p>"
					+ "<p>"
						+ "It's quite clear that your strong aura is having an arousing effect on [npc.name], and you imagine that all it would take would be the slightest suggestion of sex, and [npc.she]'d sure to be all over you..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getDefaultResponses(index);
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX = new DialogueNodeOld("Reindeer Overseer", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "With a satisfied sigh, [npc.name] begins to get [npc.her] clothing in order."
							+ " [npc.speech(Fuck... That was good...)]"
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "With an annoyed huff, [npc.name] begins to get [npc.her] clothing in order."
							+ " [npc.speech(Fuck... I didn't even get to cum! That was pretty disappointing...)]"
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Once you've both got your things in order, you step back out into the street."
						+ " You wonder if you should ask to see what goods [npc.name] has on offer, or simply take your leave."
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getDefaultResponses(index);
		}
	};
}
