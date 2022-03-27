package com.lilithsthrone.game.dialogue.places.submission.dicePoker;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.GamblingDenPatron;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.6
 * @version 0.3.5.5
 * @author Innoxia
 */
public class DicePoker {
	
	private static String dialoguePath;
	private static DialogueNode endingNode;
	private static int moneyPool;
	private static DicePokerTable table;
	private static NPC gambler;
	private static List<Dice> playerDice = new ArrayList<>();
	private static List<Dice> gamblerDice = new ArrayList<>();
	private static List<Dice> diceToReroll = new ArrayList<>();
	private static String[] progressDescriptions = new String[] {"Roll", "Betting", "Re-roll", "Payment"};
	public static int progress = 0;
	private static String responseContent;
	private static String buyInDescription;
	
	static {
		for(int i=0; i<5; i++) {
			playerDice.add(new Dice());
			gamblerDice.add(new Dice());
		}
	}
	
	public static DialogueNode initDicePoker(NPC gambler, DicePokerTable table, DialogueNode endingNode, String dialoguePath) {
		DicePoker.dialoguePath = dialoguePath;
		DicePoker.endingNode = endingNode;
		DicePoker.table = table;
		
		progress = 0;
		moneyPool = table.getInitialBet()*2;
		
		buyInDescription = Main.game.getPlayer().incrementMoney(-table.getInitialBet());
		
		DicePoker.gambler = gambler;
		if(DicePoker.gambler.getDice()!=null) {
			gamblerDice = new ArrayList<>(gambler.getDice());
			
		} else {
			gamblerDice = new ArrayList<>();
			for(int i=0; i<5; i++) {
				gamblerDice.add(new Dice());
			}
		}
		
		for(Dice d : playerDice) {
			d.setFace(DiceFace.ONE);
		}
		
		for(Dice d : gamblerDice) {
			d.setFace(DiceFace.ONE);
		}
		
		diceToReroll.clear();
		diceToReroll.addAll(playerDice);
		diceToReroll.addAll(gamblerDice);
		
//		gambler.setPlayerKnowsName(true);
		
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
				UtilText.nodeContentSB.append("<div class='modifier-icon' style='width:18%; margin:0 1%; border:3px solid "+(diceToReroll.contains(playerDice.get(i))?PresetColour.GENERIC_MINOR_GOOD.toWebHexString():"")+";'>"
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
				UtilText.nodeContentSB.append("<div class='modifier-icon' style='width:18%; margin:0 1%; border:3px solid "+(diceToReroll.contains(gamblerDice.get(i))?PresetColour.GENERIC_MINOR_GOOD.toWebHexString():"")+";'>"
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
	
	/**
	 * For use in external dialogue files.
	 */
	public static final DialogueNode GAMBLING = new DialogueNode("Dice Poker Tables", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<NPC> gamblers = Main.game.getNonCompanionCharactersPresent();
			
			if(index==0) {
				return null;
				
			} else if(index==gamblers.size()+1){
				return new Response("Rules", "Take a look at a nearby sign which displays the rules of dice poker.", GAMBLING_RULES);
				
			} else {
				try {
					gamblers.sort((g1, g2) -> ((GamblingDenPatron) g1).getTable().compareTo(((GamblingDenPatron) g2).getTable()));
				} catch(Exception ex) {
				}
				
				if(index-1<gamblers.size()) {
					NPC gambler = gamblers.get(index-1);
					DicePokerTable table = 
							(gambler instanceof GamblingDenPatron && ((GamblingDenPatron) gambler).getTable()!=null)
								?((GamblingDenPatron) gambler).getTable()
								:DicePokerTable.COPPER;
					int buyIn = table.getInitialBet()+table.getRaiseAmount();
					if(Main.game.getPlayer().getMoney()>=buyIn) {
						return new ResponseEffectsOnly(
								"<span style='color:"+table.getColour().toWebHexString()+";'>"+UtilText.parse(gambler, "[npc.Name(a)]")+"</span> ("+UtilText.formatAsMoney(buyIn, "span")+")",
								UtilText.parse(gambler,
										"Start playing dice poker with [npc.name]. The buy-in amount is "+UtilText.formatAsMoney(table.getInitialBet(), "span")
										+", but you'll also need "+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+" for any raises.")) {
							@Override
							public void effects() {
								Main.game.setContent(new Response("", "", DicePoker.initDicePoker(gambler, table, Main.game.getDefaultDialogue(), "misc/dicePoker")));
							}
						};
						
					} else {
						return new Response(gambler.getName(true)+" ("+UtilText.formatAsMoneyUncoloured(buyIn, "span")+")",
								"The buy-in amount is "+UtilText.formatAsMoney(table.getInitialBet(), "span")
								+", but you'll also need "+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+" for any raises. As a result, you don't have enough money to play at this table!",
								null);
					}
					
				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNode GAMBLING_RULES = new DialogueNode("Dice Poker Tables", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "GAMBLING_RULES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Back", "Finish reading the rules and [pc.step] back from the sign.", Main.game.getDefaultDialogue());
			}
			return null;
		}
	};
	
	private static final DialogueNode START = new DialogueNode("Dice Poker", "", true) {
		@Override
		public void applyPreParsingEffects() {
			UtilText.addSpecialParsingString(table.getName(), true);
			UtilText.addSpecialParsingString(UtilText.formatAsMoney(table.getInitialBet(), "span"), false);
			
			Main.game.appendToTextStartStringBuilder(getGamblingFormat(buyInDescription
					+UtilText.parseFromXMLFile(dialoguePath, "START", gambler)));
			
			gambler.setPlayerKnowsName(true);
		}
		@Override
		public String getLabel() {
			return "Dice Poker - <b style='color:"+table.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(table.getName())+" Table</b>";
		}
		
		@Override
		public String getContent() {
			return "";
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
	
	private static final DialogueNode ROLL = new DialogueNode("Dice Poker", "", true) {
		
		@Override
		public String getContent() {
			if(Hand.compareHands(playerDice, gamblerDice)>0) {
				return getGamblingFormat(UtilText.parseFromXMLFile(dialoguePath, "ROLL_WINNING", gambler));
					
			} else if(Hand.compareHands(playerDice, gamblerDice)==0) {
				return getGamblingFormat(UtilText.parseFromXMLFile(dialoguePath, "ROLL_DRAWING", gambler));
					
			} else {
				return getGamblingFormat(UtilText.parseFromXMLFile(dialoguePath, "ROLL_LOSING", gambler));
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly("Call", "Don't increase the bet.") {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(UtilText.formatAsMoney(getRaiseAmount(), "span"), true);
						if(isGamblerRaising()) {
							moneyPool+=getRaiseAmount();
							responseContent = UtilText.parseFromXMLFile(dialoguePath, "ROLL_CALL_OPPONENT_RAISES", gambler);
							Main.game.setContent(new Response("", "", BET_NEED_REACT));
							
						} else {
							responseContent = UtilText.parseFromXMLFile(dialoguePath, "ROLL_CALL_OPPONENT_CALLS", gambler);
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
						UtilText.addSpecialParsingString(UtilText.formatAsMoney(getRaiseAmount(), "span"), true);
						if(isGamblerFolding()) {
							String moneyChange = Main.game.getPlayer().incrementMoney(moneyPool);
							UtilText.addSpecialParsingString(moneyChange, false);
							responseContent = UtilText.parseFromXMLFile(dialoguePath, "ROLL_RAISE_OPPONENT_FOLDS", gambler);
							progress++;
							progress++;
							Main.game.setContent(new Response("", "", END_WIN));
							
						} else {
							moneyPool+=getRaiseAmount()*2;
							String moneyChange = Main.game.getPlayer().incrementMoney(-getRaiseAmount());
							UtilText.addSpecialParsingString(moneyChange, false);
							responseContent = UtilText.parseFromXMLFile(dialoguePath, "ROLL_RAISE_OPPONENT_CALLS", gambler);
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
	
	private static final DialogueNode BET_NEED_REACT = new DialogueNode("Dice Poker", "", true) {
		
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
						UtilText.addSpecialParsingString(UtilText.formatAsMoney(getRaiseAmount(), "span"), true);
						moneyPool+=getRaiseAmount();
						String moneyChange = Main.game.getPlayer().incrementMoney(-getRaiseAmount());
						UtilText.addSpecialParsingString(moneyChange, false);
						responseContent = UtilText.parseFromXMLFile(dialoguePath, "BET_NEED_REACT_CALL", gambler);
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
						UtilText.addSpecialParsingString(UtilText.formatAsMoney(moneyPool, "span"), true);
						responseContent = UtilText.parseFromXMLFile(dialoguePath, "BET_NEED_REACT_FOLD", gambler);
						progress++;
						progress++;
						Main.game.setContent(new Response("", "", END_LOSS));
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNode REROLL = new DialogueNode("Dice Poker", "", true) {
		
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
				return new ResponseEffectsOnly(
						reroll
							?"Roll"
							:"No Roll",
						reroll
							?"Roll your dice."
							:"Choose not to re-roll any of your dice. (Click on your dice to select them for re-roll.)") {
					@Override
					public void effects() {
						boolean diceRerolled = !diceToReroll.isEmpty();
						rollRice();
						
						if(Hand.compareHands(playerDice, gamblerDice)==0) {
							UtilText.addSpecialParsingString(UtilText.formatAsMoney(moneyPool), true);
							UtilText.addSpecialParsingString(Main.game.getPlayer().incrementMoney(moneyPool/2), false);
							if(diceRerolled) {
								responseContent = UtilText.parseFromXMLFile(dialoguePath, "REROLL_DRAW_WITH_ROLL", gambler);
							} else {
								responseContent = UtilText.parseFromXMLFile(dialoguePath, "REROLL_DRAW_WITHOUT_ROLL", gambler);
							}
							progress++;
							Main.game.setContent(new Response("", "", END_DRAW));
							
						} else if(Hand.compareHands(playerDice, gamblerDice)>0) {
							String moneyChange = Main.game.getPlayer().incrementMoney(moneyPool);
							UtilText.addSpecialParsingString(moneyChange, true);
							if(diceRerolled) {
								responseContent = UtilText.parseFromXMLFile(dialoguePath, "REROLL_WIN_WITH_ROLL", gambler);
							} else {
								responseContent = UtilText.parseFromXMLFile(dialoguePath, "REROLL_WIN_WITHOUT_ROLL", gambler);
							}
							progress++;
							Main.game.setContent(new Response("", "", END_WIN));
						
						} else {
							UtilText.addSpecialParsingString(UtilText.formatAsMoney(moneyPool, "span"), true);
							if(diceRerolled) {
								responseContent = UtilText.parseFromXMLFile(dialoguePath, "REROLL_LOSS_WITH_ROLL", gambler);
							} else {
								responseContent = UtilText.parseFromXMLFile(dialoguePath, "REROLL_LOSS_WITHOUT_ROLL", gambler);
							}
							progress++;
							Main.game.setContent(new Response("", "", END_LOSS));
						}
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNode END_WIN = new DialogueNode("Dice Poker", "", true) {
		
		@Override
		public String getContent() {
			return getGamblingFormat(responseContent);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Step away from the table.", endingNode) {
					@Override
					public void effects() {
						progress = 0;
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNode END_DRAW = new DialogueNode("Dice Poker", "", true) {
		
		@Override
		public String getContent() {
			return getGamblingFormat(responseContent);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Step away from the table.", endingNode) {
					@Override
					public void effects() {
						progress = 0;
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNode END_LOSS = new DialogueNode("Dice Poker", "", true) {
		
		@Override
		public String getContent() {
			return getGamblingFormat(responseContent);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Accept loss", "Step away from the table.", endingNode) {
					@Override
					public void effects() {
						progress = 0;
					}
				};
				
			} else if(index==2) {
				if(!gambler.isAttractedTo(Main.game.getPlayer())) {
					return new Response("Offer body", UtilText.parse(gambler, "[npc.Name] is not attracted to you, so you can't hope to get your money back by offering your body to [npc.herHim]."), null);
				}
				return new Response("Offer body",
						UtilText.parse(gambler, "Offer [npc.name] use of your body if [npc.she]'ll give you your money back."),
						END_LOSS_OFFER_BODY);
			}
			return null;
		}
	};
	
	private static final DialogueNode END_LOSS_OFFER_BODY = new DialogueNode("Dice Poker", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(dialoguePath, "END_LOSS_OFFER_BODY", gambler);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Decline", "Step away from the table.", endingNode) {
					@Override
					public void effects() {
						progress = 0;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "END_LOSS_OFFER_BODY_DECLINE", gambler));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Accept",
						UtilText.parse(gambler, "Allow [npc.name] to publicly fuck you in order to get your money back."),
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(gambler),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						END_LOSS_AFTER_SEX,
						UtilText.parseFromXMLFile(dialoguePath, "END_LOSS_OFFER_BODY_ACCEPT", gambler)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(moneyPool/2));
					}
				};
			}
			return null;
		}
	};

	private static final DialogueNode END_LOSS_AFTER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(gambler, "[npc.Name] has had [npc.her] fun, and so brings an end to the sex...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(dialoguePath, "END_LOSS_AFTER_SEX", gambler);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Step away from the table.", endingNode) {
					@Override
					public void effects() {
						progress = 0;
					}
				};
			}
			return null;
		}
	};
}
