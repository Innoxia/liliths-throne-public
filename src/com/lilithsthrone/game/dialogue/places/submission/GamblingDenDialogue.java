package com.lilithsthrone.game.dialogue.places.submission;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.effects.Addiction;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.submission.SMRoxyPussyLicker;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
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
				Addiction ratGCumAdd = Main.game.getPlayer().getAddiction(FluidType.GIRL_CUM_RAT_MORPH);
				if(ratGCumAdd!=null && ratGCumAdd.getProviderIDs().contains(Main.game.getRoxy().getId())) {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new ResponseSex("Lick for fix", "Desperate to get another fix of her addictive girl cum, you agree to serve as Roxy's 'pussy licker' for an hour.",
								Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMRoxyPussyLicker(
										Util.newHashMapOfValues(new Value<>(Main.game.getRoxy(), SexPositionSlot.SITTING_ON_FACE_ROXY)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.LICKING_PUSSY_ROXY))),
								AFTER_ROXY_SEX_ADDICT,
								UtilText.parseFromXMLFile("places/submission/gamblingDen", "ROXY_SEX_START_ADDICT")){
							@Override
							public void effects() {
								Main.game.getRoxy().displaceClothingForAccess(CoverableArea.VAGINA);
								Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
							}
						};
						
					} else {
						return new Response("Lick for fix", "You can only service Roxy if you're able to gain access to your mouth!", null);
					}
					
				} else {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new ResponseSex("Lick for item", "Agree to serve as Roxy's 'pussy licker' for an hour, in exchange for a random item from her shop.",
								Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMRoxyPussyLicker(
										Util.newHashMapOfValues(new Value<>(Main.game.getRoxy(), SexPositionSlot.SITTING_ON_FACE_ROXY)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.LICKING_PUSSY_ROXY))),
								AFTER_ROXY_SEX,
								UtilText.parseFromXMLFile("places/submission/gamblingDen", "ROXY_SEX_START")){
							@Override
							public void effects() {
								Main.game.getRoxy().displaceClothingForAccess(CoverableArea.VAGINA);
								Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
							}
						};
						
					} else {
						return new Response("Lick for item", "You can only service Roxy if you're able to gain access to your mouth!", null);
					}
				}

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
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "GAMBLING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return null;
				
			} else if(index==1) {
				return new Response("Rules", "Read the rules of how to play dice poker.", GAMBLING_RULES);
				
			} else {
				List<NPC> gamblers = Main.game.getNonCompanionCharactersPresent();
				
				if(index-2<gamblers.size()) {
					NPC gambler = gamblers.get(index-2);
					if(Main.game.getPlayer().getMoney()>=300) {
						return new Response("<span style='color:"+gambler.getFemininity().getColour().toWebHexString()+";'>"+gambler.getName()+"</span> ("+UtilText.formatAsMoney(300, "span")+")",
								"Start playing dice poker with "+gambler.getName()+".",
								GAMBLING_START) {
							@Override
							public void effects() {
								//TODO set npc
							}
						};
						
					} else {
						return new Response("<b style='color:"+gambler.getFemininity().getColour().toWebHexString()+";'>"+gambler.getName()+"</b> ("+UtilText.formatAsMoneyUncoloured(300, "span")+")",
								"You need at least 300 flames in order to start a game of dice poker!",
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
	
	public static final DialogueNodeOld GAMBLING_START = new DialogueNodeOld("Dice Poker Tables", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "GAMBLING_START");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Dice Poker", "Start playing dice poker.", GAMBLING_START);
				
			} else {
				return null;
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
