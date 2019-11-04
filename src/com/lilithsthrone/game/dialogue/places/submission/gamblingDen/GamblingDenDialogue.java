package com.lilithsthrone.game.dialogue.places.submission.gamblingDen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.GamblingDenPatron;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePoker;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.3.5.5
 * @author Innoxia
 */
public class GamblingDenDialogue {

	public static final DialogueNode ENTRANCE = new DialogueNode("Entrance", "", false) {
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced);
		}
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced)) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "ENTRANCE_REPEAT");
			} else {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "ENTRANCE");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced)) {
					return new Response("Continue", "Set off to explore the Gambling Den.", ENTRANCE){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelIntroduced, true);
						}
					};
					
				} else {
					return new Response("Leave", "Push open the doors and step back out into Submission.", SubmissionGenericPlaces.GAMBLING_DEN){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelIntroduced, true);
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_GAMBLING_DEN);
						}
					};
				}
				
			} else if(index==2) {
				return new Response("Axel", "Walk over to Axel and say hello.", AXEL){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelIntroduced, true);
					}
				};
			
			}
			else {
				return null;
			}
		}
	};

	public static final DialogueNode OFFICE = new DialogueNode("Axel's Office", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	
	public static final DialogueNode AXEL = new DialogueNode("Talking to Axel", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Business", "Talk to Axel about his business.", AXEL_BUSINESS);
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyIntroduced)) {
					return new Response("Roxy", "Ask Axel about Roxy.", AXEL_ROXY) {
						@Override
						public void effects() {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelMentionedVengar)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_ROXY_REPEAT"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_ROXY"));
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelMentionedVengar, true);
						}
					};
				} else {
					return new Response("Roxy", "You'd need to talk with Roxy before asking Axel about her.", null);
				}
				
			} else if(index==3
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelMentionedVengar)
					&& !Main.game.getPlayer().hasQuest(QuestLine.SIDE_VENGAR)) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelExplainedVengar)) {
					return new Response("Offer help", "Tell Axel that you'd like to help him deal with Vengar.", AXEL_VENGAR) {
						@Override
						public Colour getHighlightColour() {
							return Colour.QUEST_SIDE;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_OFFER_HELP"));
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_VENGAR));
						}
					};
					
				} else {
					return new Response("Vengar", "Ask Axel about Vengar. [style.italicsMinorGood(This will be added for the next version!)]", null);
//					return new Response("Vengar", "Ask Axel about Vengar.", AXEL_VENGAR) {
//						@Override
//						public void effects() {
//							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR"));
//							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelExplainedVengar, true);
//						}
//					};
				}
				
			} else if(index==0) {
				return new Response("Back", "Say goodbye and walk back out into the entrance hall.", ENTRANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AXEL_BUSINESS = new DialogueNode("Talking to Axel", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_BUSINESS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Talk", "You're already talking to Axel.", null);
			}
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AXEL_ROXY = new DialogueNode("Talking to Axel", "", true) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("Roxy", "You're already talking with Axel about Roxy.", null);
			}
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AXEL_VENGAR = new DialogueNode("Talking to Axel", "", true) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode CORRIDOR = new DialogueNode("Gambling Den", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "CORRIDOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<10) {
					return new Response("Slot Machine ("+UtilText.formatAsMoney(10, "span")+")", "You don't have enough money to play on the slot machines!", null);
				} else {
					return new Response("Slot Machine ("+UtilText.formatAsMoney(10, "span")+")", "Put 10 flames into the nearest slot machine and pull the handle.", SLOT_MACHINE) {
						@Override
						public void effects() {
							
							Map<Subspecies, Integer> slotMachineValues = Util.newHashMapOfValues(
									new Value<>(Subspecies.HUMAN, 5),
									new Value<>(Subspecies.IMP, 10),
									new Value<>(Subspecies.DOG_MORPH, 25),
									new Value<>(Subspecies.CAT_MORPH, 25),
									new Value<>(Subspecies.COW_MORPH, 50),
									new Value<>(Subspecies.DEMON, 100),
									new Value<>(Subspecies.ELEMENTAL_ARCANE, 500));
							
							Map<Subspecies, Integer> slotMachineValueProbabilities = Util.newHashMapOfValues(
									new Value<>(Subspecies.HUMAN, 16),
									new Value<>(Subspecies.IMP, 8),
									new Value<>(Subspecies.DOG_MORPH, 2),
									new Value<>(Subspecies.CAT_MORPH, 2),
									new Value<>(Subspecies.COW_MORPH, 2),
									new Value<>(Subspecies.DEMON, 1),
									new Value<>(Subspecies.ELEMENTAL_ARCANE, 1));
							
							Main.game.getTextEndStringBuilder().append(
									"<p>"
										+ "Deciding to try your luck at one of the slot machines, you walk up to the nearest one and place ten flames in the coin slot."
									+ "</p>"
									+ Main.game.getPlayer().incrementMoney(-10)
									+"<p>"
										+ "Pulling the handle, you watch as the three reels on the front rapidly spin round and round, before slowly stopping on three pictures..."
									+ "</p>");
							

							Main.game.getTextEndStringBuilder().append("<div class='container-full-width'>");
							
								Main.game.getTextEndStringBuilder().append("<div class='container-half-width' style='position:relative; text-align:center;'>"
										+ "<p style='width:100%'><b>Slot Machine Results:</b></p>");
								
								List<Subspecies> races = new ArrayList<>(slotMachineValues.keySet());
								
								List<Subspecies> results = new ArrayList<>();
	
								boolean winner = false;
								if(Math.random()<0.32f) {
									Subspecies s = Util.getRandomObjectFromWeightedMap(slotMachineValueProbabilities);
									for(int i=0; i<3; i++) {
										results.add(s);
									}
									winner=true;
									
								} else {
									for(int i=0; i<3; i++) {
										Subspecies s = races.get(Util.random.nextInt(races.size()));
										results.add(s);
										if(i==0) {
											races.remove(s);
										}
									}
									Collections.shuffle(results);
								}
								
								for(Subspecies r : results) {
									Main.game.getTextEndStringBuilder().append(
											"<div class='modifier-icon' style='width:31.3%; margin:0 1%; border:3px solid "+(winner?Colour.GENERIC_EXCELLENT.toWebHexString():"")+"; display:inline-block;'>"
													+"<div class='modifier-icon-content'>"+r.getSVGString(null)+"</div>"
											+ "</div>");
								}
								if(winner) {
									Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>"
													+ "[style.colourExcellent(You won!)]<br/>Three "+results.get(0).getNamePlural(null)+" pay out "+UtilText.formatAsMoney(slotMachineValues.get(results.get(0)), "span")+"!"
											+ "</p>");
								} else {
									Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>"
													+ "[style.colourTerrible(You lost!)]"
											+ "</p>");
								}
								
								Main.game.getTextEndStringBuilder().append("</div>");
								Main.game.getTextEndStringBuilder().append("<div class='container-half-width' style='position:relative; text-align:center;'>"
										+"<p style='text-align:center;'>");
							
									for(Entry<Subspecies, Integer> entry : slotMachineValues.entrySet()) {
										Main.game.getTextEndStringBuilder().append("<span style='color:"+entry.getKey().getColour(null).toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getNamePlural(null))+"</span>: "
												+UtilText.formatAsMoney(entry.getValue(), "span")+"<br/>");
									}
												
									Main.game.getTextEndStringBuilder().append("</p>");
								Main.game.getTextEndStringBuilder().append("</div>");
							Main.game.getTextEndStringBuilder().append("</div>");
								
							if(winner) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(slotMachineValues.get(results.get(0))));
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "Having won "+Util.intToString(slotMachineValues.get(results.get(0)))+" flames, you wonder if you should pocket your money and continue on your way, or have another go and try for another win..."
										+ "</p>");
							} else {
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "Having lost your ten flames, you wonder if you should cut your losses and continue on your way, or have another go and try for a win..."
										+ "</p>");
							}
						}
					};
				}
			}
			return null;
		}
	};
	

	public static final DialogueNode SLOT_MACHINE = new DialogueNode("Gambling Den", "", false) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return CORRIDOR.getResponse(responseTab, index);
		}
		
	};
	
	
	public static final DialogueNode GAMBLING = new DialogueNode("Dice Poker Tables", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "GAMBLING");
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
						return new ResponseEffectsOnly("<span style='color:"+table.getColour().toWebHexString()+";'>"+gambler.getName(true)+"</span> ("+UtilText.formatAsMoney(buyIn, "span")+")",
								"Start playing dice poker with "+gambler.getName(true)+". The buy-in amount is "+UtilText.formatAsMoney(table.getInitialBet(), "span")
									+", but you'll also need "+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+" for any raises.") {
							@Override
							public void effects() {
								Main.game.setContent(new Response("", "", DicePoker.initDicePoker(gambler, table, GAMBLING)));
							}
						};
						
					} else {
						return new Response("<span style='color:"+table.getColour().toWebHexString()+";'>"+gambler.getName(true)+"</span> ("+UtilText.formatAsMoney(buyIn, "span")+")",
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
				return new Response("Back", "Finish reading the rules and step back from the sign.", GAMBLING);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MALE_STALLS = new DialogueNode("Male Breeding Stalls", "", false) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_MALE_STALLS"));
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsBreeder)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsMother)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_STALLS_KNOWLEDGE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_STALLS_NO_KNOWLEDGE"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_FUTA_STALLS = new DialogueNode("Futa Breeding Stalls", "", false) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_FUTA_STALLS"));
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsBreeder)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsMother)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_STALLS_KNOWLEDGE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_STALLS_NO_KNOWLEDGE"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
}
