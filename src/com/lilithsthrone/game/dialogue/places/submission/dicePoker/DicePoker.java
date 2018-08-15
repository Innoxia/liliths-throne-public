package com.lilithsthrone.game.dialogue.places.submission.dicePoker;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.GamblingDenPatron;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.places.submission.GamblingDenDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public class DicePoker {
	
	private static int moneyPool;
	private static DicePokerTable table;
	private static NPC gambler;
	private static List<Dice> playerDice = new ArrayList<>();
	private static List<Dice> gamblerDice = new ArrayList<>();
	private static List<Dice> diceToReroll = new ArrayList<>();
	private static String[] progressDescriptions = new String[] {"Roll", "Betting", "Re-roll", "Payment"};
	private static int progress = 0;
	private static String responseContent;
	private static String buyInDescription;
	
	static {
		for(int i=0; i<5; i++) {
			playerDice.add(new Dice());
			gamblerDice.add(new Dice());
		}
	}
	
	public static DialogueNodeOld initDicePoker(NPC gambler) {
		if(gambler instanceof GamblingDenPatron) {
			DicePoker.table = ((GamblingDenPatron)gambler).getTable();
		} else {
			DicePoker.table = DicePokerTable.SILVER;
		}
		
		progress = 0;
		moneyPool = table.getInitialBet()*2;
		
		buyInDescription = Main.game.getPlayer().incrementMoney(-table.getInitialBet());
		
		DicePoker.gambler = gambler;
		
		for(Dice d : playerDice) {
			d.setFace(DiceFace.ONE);
		}
		
		for(Dice d : gamblerDice) {
			d.setFace(DiceFace.ONE);
		}
		
		diceToReroll.clear();
		diceToReroll.addAll(playerDice);
		diceToReroll.addAll(gamblerDice);
		
		return START;
	}
	
	public static List<Dice> getPlayerDice() {
		return playerDice;
	}
	
	public static boolean isAbleToSelectReroll() {
		return !Main.game.getCurrentDialogueNode().equals(START)
				&& !Main.game.getCurrentDialogueNode().equals(END_WIN)
				&& !Main.game.getCurrentDialogueNode().equals(END_DRAW)
				&& !Main.game.getCurrentDialogueNode().equals(END_LOSS);
	}
	
	public static void setReroll(Dice d) {
		if(diceToReroll.contains(d)) {
			diceToReroll.remove(d);
		} else {
			diceToReroll.add(d);
		}
		Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
	}

	private static String getGamblingFormat(String turnText) {
		UtilText.nodeContentSB.setLength(0);
		
		int comparingHands = Hand.compareHands(playerDice, gamblerDice);
		
		UtilText.nodeContentSB.append("<div class='container-half-width'>");
			for(int i=0; i<playerDice.size(); i++) {
				UtilText.nodeContentSB.append("<div class='modifier-icon' style='width:18%; margin:0 1%; border:3px solid "+(diceToReroll.contains(playerDice.get(i))?Colour.GENERIC_MINOR_GOOD.toWebHexString():"")+";'>"
													+(Hand.getDiceInHand(playerDice).contains(playerDice.get(i))
															?"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>"
																	+SVGImages.SVG_IMAGE_PROVIDER.getDiceGlow()
																+ "</div>"
															:"")
													+"<div class='modifier-icon-content'>"+playerDice.get(i).getFace().getSVGString()+"</div>"
													+ "<div class='overlay' id='DICE_PLAYER_"+i+"'></div>"
											+ "</div>");
			}
			Hand playerHand = Hand.getHand(playerDice);
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ "<b>"+playerHand.getRanking()+". "+playerHand.getName()+"</b> | [style.colourDisabled(Value: "+Hand.getValue(playerDice)+")]<br/>"
					+(comparingHands==0
						?"[style.colourDisabled(You "+(progress==3?"have drawn":"are drawing")+"...)]"
						:(comparingHands<0
							?"[style.colourTerrible(You "+(progress==3?"have lost":"are losing")+"!)]"
							:"[style.colourExcellent(You "+(progress==3?"have won":"are winning")+"!)]"))
						+ "</div>");
		UtilText.nodeContentSB.append("</div>");
		
		UtilText.nodeContentSB.append("<div class='container-half-width'>");
			for(int i=0; i<gamblerDice.size(); i++) {
				UtilText.nodeContentSB.append("<div class='modifier-icon' style='width:18%; margin:0 1%; border:3px solid "+(diceToReroll.contains(gamblerDice.get(i))?Colour.GENERIC_MINOR_GOOD.toWebHexString():"")+";'>"
													+(Hand.getDiceInHand(gamblerDice).contains(gamblerDice.get(i))
															?"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>"
																	+SVGImages.SVG_IMAGE_PROVIDER.getDiceGlow()
																+ "</div>"
															:"")
													+"<div class='modifier-icon-content'>"+gamblerDice.get(i).getFace().getSVGString()+"</div>"
											+ "</div>");
			}
			Hand gamblerHand = Hand.getHand(gamblerDice);
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ "<b>"+gamblerHand.getRanking()+". "+gamblerHand.getName()+"</b> | [style.colourDisabled(Value: "+Hand.getValue(gamblerDice)+")]<br/>"
					+UtilText.parse(gambler,(comparingHands==0
						?"[style.colourDisabled([npc.Name] "+(progress==3?"has drawn":"is drawing")+"...)]"
						:(comparingHands>0
							?"[style.colourTerrible([npc.Name] "+(progress==3?"has lost":"is losing")+"!)]"
							:"[style.colourExcellent([npc.Name] "+(progress==3?"has won":"is winning")+"!)]")))
					+ "</div>");
		UtilText.nodeContentSB.append("</div>");
		
		
		UtilText.nodeContentSB.append("<div class='container-full-width'>");
		UtilText.nodeContentSB.append("<div class='container-quarter-width' style='width:13%; margin:0 2%; text-align:center;'>"
				+ "Progress:"
				+ "</div>");
		for(int i=0; i<progressDescriptions.length; i++) {
			UtilText.nodeContentSB.append(
					(i!=0
						?"<div class='container-quarter-width inner' style='width:5%; margin:0; text-align:center;'>&gt;</div>"
						:"")
					+ "<div class='container-quarter-width inner' style='width:13%; margin:0 2%; text-align:center;'>"
					+ (progress==i
						?"[style.boldGood("+progressDescriptions[i]+")]"
						:progress>i
							?progressDescriptions[i]
							:"[style.colourDisabled("+progressDescriptions[i]+")]")
					+ "</div>");
		}
		UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
				+ "Total pot: "+UtilText.formatAsMoney(moneyPool, "span")
				+ "</div>");
		UtilText.nodeContentSB.append("</div>");
		
		UtilText.nodeContentSB.append(UtilText.parse(gambler,turnText));
					
		return UtilText.nodeContentSB.toString();
	}
	
	private static void rollRice() {
		for(Dice dice : diceToReroll) {
			dice.roll();
		}
		diceToReroll.clear();
	}
	
	private static int getRaiseAmount() {
		if(Main.game.getPlayer().getMoney()<table.getRaiseAmount()) {
			return Main.game.getPlayer().getMoney();
		} else {
			return table.getRaiseAmount();
		}
	}
	
	private static boolean isGamblerRaising() {
		int differenceWillingToRaiseAt = -Util.random.nextInt(3)-1;
		return Hand.getHand(playerDice)!=Hand.getHand(gamblerDice) && Hand.compareHands(playerDice, gamblerDice) < differenceWillingToRaiseAt;
	}
	
	private static boolean isGamblerFolding() {
		int differenceWillingToFoldAt = Util.random.nextInt(6)+4;
		return Hand.getHand(playerDice)!=Hand.getHand(gamblerDice) && Hand.compareHands(playerDice, gamblerDice) > differenceWillingToFoldAt;
	}
	
	private static void calculateGamblerRerolls() {
		List<Dice> rerollDice = new ArrayList<>(gamblerDice);
		
		if(Hand.getHand(gamblerDice)!=Hand.NINE_RUNT) {
			rerollDice.removeAll(Hand.getDiceInHand(gamblerDice));
		}
		
		diceToReroll.addAll(rerollDice);
	}
	
	private static final DialogueNodeOld START = new DialogueNodeOld("Dice Poker", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Dice Poker - <b style='color:"+table.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(table.getName())+" Table</b>";
		}
		
		@Override
		public String getContent() {
			return getGamblingFormat(buyInDescription
					+"<p>"
						+ "Walking up to one of the free tables in the "+table.getName()+" section, you sit down opposite [npc.a_race]."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(You think you can take me on, huh?)] [npc.she] taunts. [npc.speech(Don't start crying when I take all your cash!)]"
					+ "</p>"
					+ "<p>"
						+ "You place the buy-in of "+UtilText.formatAsMoney(table.getInitialBet(), "span")+" on the table, and the [npc.race] does the same."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(The name's [npc.name], by the way. Now let's play.)]"
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Roll", "Roll your dice.", ROLL) {
					@Override
					public void effects() {
						rollRice();
						progress++;
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNodeOld ROLL = new DialogueNodeOld("Dice Poker", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			if(Hand.compareHands(playerDice, gamblerDice)>0) {
				return getGamblingFormat(
						"<p>"
							+ "Both you and [npc.name] roll all five of your dice to get the game started."
							+ " As they clatter to a halt, your opponent lets out an annoyed sigh as [npc.she] sees that you have a better hand."
							+ " [npc.speech(Damn it... Well, this is just the start! You gonna' call, or you want to raise the stakes?)]"
						+ "</p>");
					
			} else if(Hand.compareHands(playerDice, gamblerDice)==0) {
				return getGamblingFormat(
						"<p>"
							+ "Both you and [npc.name] roll all five of your dice to get the game started."
							+ " As they clatter to a halt, your opponent lets out a surprised hum as [npc.she] sees that you're drawing."
							+ " [npc.speech(Well, would you look at that... You gonna' call, or you want to raise the stakes?)]"
						+ "</p>");
					
			} else {
				return getGamblingFormat(
						"<p>"
							+ "Both you and [npc.name] roll all five of your dice to get the game started."
							+ " As they clatter to a halt, your opponent lets out a triumphant laugh as [npc.she] sees that [npc.she] has a better hand."
							+ " [npc.speech(Hah! This is just the start, and I'm already winning! Now, you gonna' call, or you want to raise the stakes?)]"
						+ "</p>");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly("Call", "Don't increase the bet.") {
					@Override
					public void effects() {
						if(isGamblerRaising()) {
							moneyPool+=getRaiseAmount();
							responseContent = "<p>"
										+ "[pc.speech(I call,)] you say, leaning back in your chair."
									+ "</p>"
									+ "<p>"
										+ "[npc.speech(Well, no surprise there,)] [npc.name] laughs. [npc.speech(We both know I'm going to win.)]"
									+ "</p>"
									+ "<p>"
										+ "Leaning forwards, [npc.name] places "+UtilText.formatAsMoney(getRaiseAmount(), "span")+" on the table, before locking [npc.her] gaze with yours and letting out another mocking laugh."
											+ " [npc.speech(Go on, just fold. You're as good as finished.)]"
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ "<i>[npc.Name] <b>raised</b> by "+UtilText.formatAsMoney(getRaiseAmount(), "span")+"!</i>"
									+ "</p>";
							Main.game.setContent(new Response("", "", BET_NEED_REACT));
							
						} else {
							responseContent = "<p>"
									+ "[pc.speech(I call,)] you say, leaning back in your chair."
								+ "</p>"
								+ "<p>"
									+ "[npc.speech(Yeah, I'm feeling the same,)] [npc.name] sighs. [npc.speech(I call too. Now let's finish this.)]"
								+ "</p>"
								+ "<p style='text-align:center;'>"
									+ "<i>[npc.Name] <b>called</b> as well!<br/>"
									+ "Click the dice you want to reroll, then press 'Roll'.</i>"
								+ "</p>";
							calculateGamblerRerolls();
							progress++;
							Main.game.setContent(new Response("", "", REROLL));
						}
					}
				};
				
			} else if(index==2) {
				return new ResponseEffectsOnly("Raise", "Increase the bet.") {
					@Override
					public void effects() {
						if(isGamblerFolding()) {
							String moneyChange = Main.game.getPlayer().incrementMoney(moneyPool);
							responseContent = "<p>"
												+ "[pc.speech(I think I'll raise,)] you say, placing "+UtilText.formatAsMoney(getRaiseAmount(), "span")+" on the table."
											+ "</p>"
											+ "<p>"
												+ "[npc.speech(Damn it... You got me,)] [npc.name] sighs. [npc.speech(I fold.)]"
											+ "</p>"
											+ "<p style='text-align:center;'>"
												+ "<i>[npc.Name] <b>folded</b>! [style.colourExcellent(You won!)]</i>"
											+ "</p>"
											+moneyChange;
							progress++;
							progress++;
							Main.game.setContent(new Response("", "", END_WIN));
							
						} else {
							moneyPool+=getRaiseAmount()*2;
							int raise = getRaiseAmount();
							String moneyChange = Main.game.getPlayer().incrementMoney(-getRaiseAmount());
							responseContent = moneyChange
									+"<p>"
										+ "[pc.speech(I think I'll raise,)] you say, placing "+UtilText.formatAsMoney(raise, "span")+" on the table."
									+ "</p>"
									+ "<p>"
										+ "[npc.speech(That's fine with me,)] [npc.name] replies. [npc.speech(I'll call that.)]"
									+ "</p>"
									+ "<p>"
										+ "[npc.Name] places "+UtilText.formatAsMoney(raise, "span")+" on the table, before grinning at you. [npc.speech(Now, let's finish this!)]"
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ "<i>[npc.Name] <b>called</b> your raise!<br/>"
										+ "Click the dice you want to reroll, then press 'Roll'.</i>"
									+ "</p>";
							calculateGamblerRerolls();
							progress++;
							Main.game.setContent(new Response("", "", REROLL));
						}
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNodeOld BET_NEED_REACT = new DialogueNodeOld("Dice Poker", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return getGamblingFormat(responseContent);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly("Call ("+UtilText.formatAsMoney(getRaiseAmount(), "span")+")", UtilText.parse(gambler, "Match [npc.namePos] raise of "+UtilText.formatAsMoney(getRaiseAmount(), "span")+".")) {
					@Override
					public void effects() {
						moneyPool+=getRaiseAmount();
						int raise = getRaiseAmount();
						String moneyChange = Main.game.getPlayer().incrementMoney(-getRaiseAmount());
						responseContent = moneyChange
							+"<p>"
								+ "[pc.speech(I'll call that,)] you say, placing "+UtilText.formatAsMoney(raise, "span")+" on the table."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Fine,)] [npc.name] huffs, [npc.speech(that's only going to be more money for me! Now, let's finish this!)]"
							+ "</p>"
							+ "<p style='text-align:center;'>"
								+ "<i>You <b>called</b> [npc.namePos] raise!<br/>"
								+ "Click the dice you want to reroll, then press 'Roll'.</i>"
							+ "</p>";
						calculateGamblerRerolls();
						progress++;
						Main.game.setContent(new Response("", "", REROLL));
					}
				};
				
			} else if(index==2) {
				return new ResponseEffectsOnly("Fold", UtilText.parse(gambler, "Surrender to [npc.namePos] and let [npc.herHim] take the pool of "+UtilText.formatAsMoney(moneyPool, "span")+".")) {
					@Override
					public void effects() {
						moneyPool+=getRaiseAmount();
						responseContent = "<p>"
											+ "[pc.speech(That's too steep for me,)] you sigh. [pc.speech(I fold.)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(Hah!)] [npc.name] laughs in triumph. [npc.speech(You never stood a chance!)]"
										+ "</p>"
										+ "<p>"
											+ "With that, [npc.she] moves to take the pool of "+UtilText.formatAsMoney(moneyPool, "span")+"."
											+ (gambler.isAttractedTo(Main.game.getPlayer())
													?" You could take your leave, or perhaps try to use your body to convince [npc.herHim] to let you keep your money..."
													:" You can tell that [npc.name] isn't attracted to you, so you should just take your leave...")
										+ "</p>"
										+ "<p style='text-align:center;'>"
											+ "<i>You <b>folded</b>! [style.colourTerrible(You lost!)]</i>"
										+ "</p>";
						progress++;
						progress++;
						Main.game.setContent(new Response("", "", END_LOSS));
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNodeOld REROLL = new DialogueNodeOld("Dice Poker", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return getGamblingFormat(responseContent);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			boolean reroll = false;
			for(Dice d : playerDice) {
				if(diceToReroll.contains(d)) {
					reroll=true;
					break;
				}
			}
			
			if(index==1) {
				return new ResponseEffectsOnly(reroll?"Roll":"No Roll", reroll?"Roll your dice.":"Choose not to re-roll any of your dice. (Click on your dice to select them for re-roll.)") {
					@Override
					public void effects() {
						boolean diceRerolled = !diceToReroll.isEmpty();
						rollRice();
						
						if(Hand.compareHands(playerDice, gamblerDice)==0) {
							String moneyChange = Main.game.getPlayer().incrementMoney(moneyPool/2);
							responseContent = "<p>"
												+ (diceRerolled
														?"As the dice come clattering to a halt, both you and [npc.name] sigh in unison as you see that your hands are identical in value."
														:"As both you and [npc.name] choose not to reroll any dice, you sigh in unison as you resign yourselves to having identically valued hands.")
													+ " With the game being a draw, you split the money pool in half and take your leave..."
												+ "</p>"
												+ "<p style='text-align:center;'>"
													+ "<i>You <b>drew</b>! You split the pool of "+UtilText.formatAsMoney(moneyPool, "span")+" 50/50.</i>"
												+ "</p>"
												+moneyChange;
							progress++;
							Main.game.setContent(new Response("", "", END_DRAW));
							
						} else if(Hand.compareHands(playerDice, gamblerDice)>0) {
							String moneyChange = Main.game.getPlayer().incrementMoney(moneyPool);
							responseContent = "<p>"
												+ (diceRerolled
														?"As the dice come clattering to a halt, [npc.name] lets out a defeated sigh as [npc.she] sees that you've won."
														:"As both you and [npc.name] choose not to reroll any dice, [npc.sheIs] already resigned [npc.herself] to a loss, and lets out a frustrated little sigh.")
													+ " [npc.speech(Damn it... Well, good game...)]"
												+ "</p>"
												+ "<p>"
													+ "You collect your winnings and return [npc.namePos] polite remark, before moving off and taking your leave..."
												+ "</p>"
												+ "<p style='text-align:center;'>"
													+ "[style.colourExcellent(You won!)]</i>"
												+ "</p>"
												+moneyChange;
							progress++;
							Main.game.setContent(new Response("", "", END_WIN));
						
						} else {
							responseContent =  "<p>"
												+ (diceRerolled
														?"As the dice come clattering to a halt, [npc.name] lets out a triumphant laugh as [npc.she] sees that [npc.sheIs] won."
														:"As both you and [npc.name] choose not to reroll any dice, you've already resigned yourself to a loss, and try not to feel too unhappy as your opponent lets out a triumphant laugh.")
													+ " [npc.speech(Hah! Good game, but you never stood a chance!)]"
												+ "</p>"
												+ "<p>"
												+ "With that, [npc.she] moves to take the pool of "+UtilText.formatAsMoney(moneyPool, "span")+"."
													+ (gambler.isAttractedTo(Main.game.getPlayer())
															?" You could take your leave, or perhaps try to use your body to convince [npc.herHim] to let you keep your money..."
															:" You can tell that [npc.name] isn't attracted to you, so you should just take your leave...")
												+ "</p>"
												+ "<p style='text-align:center;'>"
													+ "[style.colourTerrible(You lost!)]</i>"
												+ "</p>";
							progress++;
							Main.game.setContent(new Response("", "", END_LOSS));
						}
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNodeOld END_WIN = new DialogueNodeOld("Dice Poker", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return getGamblingFormat(responseContent);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Step away from the table.", GamblingDenDialogue.GAMBLING);
			}
			return null;
		}
	};
	
	private static final DialogueNodeOld END_DRAW = new DialogueNodeOld("Dice Poker", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return getGamblingFormat(responseContent);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Step away from the table.", GamblingDenDialogue.GAMBLING);
			}
			return null;
		}
	};
	
	private static final DialogueNodeOld END_LOSS = new DialogueNodeOld("Dice Poker", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return getGamblingFormat(responseContent);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Accept loss", "Step away from the table.", GamblingDenDialogue.GAMBLING);
				
			} else if(index==2) {
				return new Response("Offer body",
						UtilText.parse(gambler, "Offer [npc.name] use of your body if [npc.she]'ll give you your money back."),
						END_LOSS_OFFER_BODY);
			}
			return null;
		}
	};
	
	private static final DialogueNodeOld END_LOSS_OFFER_BODY = new DialogueNodeOld("Dice Poker", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parse(gambler,
					"<p>"
						+ "[pc.speech(Wait!)] you cry out, desperate not to lose your money."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(Huh? What is it?)] [npc.name] asks, narrowing [npc.her] eyes in suspicion as [npc.she] looks up from gathering the money taht's on the table."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Well... I mean... Is there no way I can get my money back?)] you ask, putting on your most innocent look as you submissively cast your eyes to the floor."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(Hmm, well, there is one way you could get your half of the pool back,)] [npc.name] replies, with an unmistakable hint of lust in [npc.her] voice."
						+ " [npc.speech(I'll give you your flames back, but only if you let me fuck you, right here, right now.)]"
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Decline", "Step away from the table.", GamblingDenDialogue.GAMBLING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "[pc.speech(On second thoughts, I don't need my money back,)] you say, before turning around and quickly taking your leave."
								+ "</p>");
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Accept",
						UtilText.parse(gambler, "Allow [npc.name] to publicly fuck you in order to get your money back."),
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(gambler, SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						null,
						END_LOSS_SEX, UtilText.parse(gambler,"<p>"
							+ "[pc.speech(Ok...)] you reply. [pc.speech(You can use me however you like...)]"
						+ "</p>"
						+ "<p>"
							+ "[npc.Name] lets out a lustful [npc.moan], before stepping forwards and wrapping [npc.her] [npc.arms] around your back."
							+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], before growling, [npc.speech(This is gonna' be good!)]"
						+ "</p>")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(moneyPool/2));
					}
				};
			}
			return null;
		}
	};

	private static final DialogueNodeOld END_LOSS_SEX = new DialogueNodeOld("Finished", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parse(gambler,
					"<p>"
						+ "[npc.Name] steps away from you, sighing, [npc.speech(You're a pretty good fuck. Perhaps you could make a living out of whoring yourself out? You're no good at dice poker, that's for sure.)]"
					+ "</p>"
					+ "<p>"
						+ "As the crowd laughs along with [npc.name] as [npc.she] gives you that advice, you quickly gather your things and hurry off, determined to do better at dice poker the next time you play..."
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Step away from the table.", GamblingDenDialogue.GAMBLING);
			}
			return null;
		}
	};
}
