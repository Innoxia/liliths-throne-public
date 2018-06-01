package com.lilithsthrone.game.dialogue.places.submission;

import java.util.List;

import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.effects.Addiction;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.GamblingDenPatron;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePoker;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public class GamblingDenDialogue {

	public static final DialogueNodeOld ENTRANCE = new DialogueNodeOld("Entrance", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced);
		}
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced)) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen", "ENTRANCE_REPEAT");
			} else {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen", "ENTRANCE");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced)) {
				if(index==1) {
					return new Response("Leave", "Push open the doors and step back out into Submission.", SubmissionGenericPlaces.GAMBLING_DEN){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_GAMBLING_DEN);
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Understood", "Tell Axel that you understand the rules.", AXEL_INTRO){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelIntroduced, true);
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld AXEL_INTRO = new DialogueNodeOld("Entrance", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "AXEL_INTRO");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld CORRIDOR = new DialogueNodeOld("Gambling Den", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "CORRIDOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld TRADER = new DialogueNodeOld("Roxy's Shop", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyIntroduced)) {
				Addiction ratGCumAdd = Main.game.getPlayer().getAddiction(FluidType.GIRL_CUM_RAT_MORPH);
				
				if(ratGCumAdd!=null && ratGCumAdd.getProviderIDs().contains(Main.game.getRoxy().getId())) {
					return UtilText.parseFromXMLFile("places/submission/gamblingDen", "TRADER_REPEAT_ADDICT");
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/gamblingDen", "TRADER_REPEAT");
				}
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen", "TRADER");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseTrade("Trade", "Trade with Roxy.", Main.game.getRoxy()){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
					}
				};
				
			} else if (index == 2) {

				return new Response("Lick for item", "(Will be added in 0.2.6.1!) Agree to serve as Roxy's 'pussy licker' for an hour, in exchange for a random item from her shop.", null);
//				Addiction ratGCumAdd = Main.game.getPlayer().getAddiction(FluidType.GIRL_CUM_RAT_MORPH);
//				if(ratGCumAdd!=null && ratGCumAdd.getProviderIDs().contains(Main.game.getRoxy().getId())) {
//					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
//						return new ResponseSex("Lick for fix", "Desperate to get another fix of her addictive girl cum, you agree to serve as Roxy's 'pussy licker' for an hour.",
//								Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING), null, CorruptionLevel.TWO_HORNY, null, null, null,
//								true, false,
//								new SMRoxyPussyLicker(
//										Util.newHashMapOfValues(new Value<>(Main.game.getRoxy(), SexPositionSlot.SITTING_ON_FACE_ROXY)),
//										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.LICKING_PUSSY_ROXY))),
//								AFTER_ROXY_SEX_ADDICT,
//								UtilText.parseFromXMLFile("places/submission/gamblingDen", "ROXY_SEX_START_ADDICT")){
//							@Override
//							public void effects() {
//								Main.game.getRoxy().displaceClothingForAccess(CoverableArea.VAGINA);
//								Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
//							}
//						};
//						
//					} else {
//						return new Response("Lick for fix", "You can only service Roxy if you're able to gain access to your mouth!", null);
//					}
//					
//				} else {
//					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
//						return new ResponseSex("Lick for item", "Agree to serve as Roxy's 'pussy licker' for an hour, in exchange for a random item from her shop.",
//								Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING), null, CorruptionLevel.TWO_HORNY, null, null, null,
//								true, false,
//								new SMRoxyPussyLicker(
//										Util.newHashMapOfValues(new Value<>(Main.game.getRoxy(), SexPositionSlot.SITTING_ON_FACE_ROXY)),
//										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.LICKING_PUSSY_ROXY))),
//								AFTER_ROXY_SEX,
//								UtilText.parseFromXMLFile("places/submission/gamblingDen", "ROXY_SEX_START")){
//							@Override
//							public void effects() {
//								Main.game.getRoxy().displaceClothingForAccess(CoverableArea.VAGINA);
//								Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
//							}
//						};
//						
//					} else {
//						return new Response("Lick for item", "You can only service Roxy if you're able to gain access to your mouth!", null);
//					}
//				}

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_ROXY_SEX = new DialogueNodeOld("Roxy stands up", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "AFTER_ROXY_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return TRADER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld AFTER_ROXY_SEX_ADDICT = new DialogueNodeOld("Roxy stands up", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "AFTER_ROXY_SEX_ADDICT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return TRADER.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNodeOld GAMBLING = new DialogueNodeOld("Dice Poker Tables", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "GAMBLING"); //TODO rules
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return null;
				
			} else {
				List<NPC> gamblers = Main.game.getNonCompanionCharactersPresent();

				try {
					gamblers.sort((g1, g2) -> ((GamblingDenPatron) g1).getTable().compareTo(((GamblingDenPatron) g2).getTable()));
				} catch(Exception ex) {
				}
				
				if(index-1<gamblers.size()) {
					DicePokerTable table = DicePokerTable.COPPER;
					NPC gambler = gamblers.get(index-1);
					try {
						table = ((GamblingDenPatron) gambler).getTable();
					} catch(Exception ex) {
					}
					int buyIn = table.getInitialBet()+table.getRaiseAmount();
					if(Main.game.getPlayer().getMoney()>=buyIn) {
						return new ResponseEffectsOnly("<span style='color:"+table.getColour().toWebHexString()+";'>"+gambler.getName()+"</span> ("+UtilText.formatAsMoney(buyIn, "span")+")",
								"Start playing dice poker with "+gambler.getName()+". The buy-in amount is "+UtilText.formatAsMoney(table.getInitialBet(), "span")
									+", but you'll also need "+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+" for any raises.") {
							@Override
							public void effects() {
								Main.game.setContent(new Response("", "", DicePoker.initDicePoker(gambler)));
							}
						};
						
					} else {
						return new Response("<span style='color:"+table.getColour().toWebHexString()+";'>"+gambler.getName()+"</span> ("+UtilText.formatAsMoney(buyIn, "span")+")",
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
	
	public static final DialogueNodeOld GAMBLING_RULES = new DialogueNodeOld("Dice Poker Tables", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "GAMBLING_RULES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Rules", "You are already reading the rules!", null);
				
			} else {
				return GAMBLING.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE = new DialogueNodeOld("Pregnancy Roulette Counter", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_NORMAL = new DialogueNodeOld("Breeding Stalls", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_NORMAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_FUTA = new DialogueNodeOld("Futa Breeding Stalls", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_FUTA");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
}
