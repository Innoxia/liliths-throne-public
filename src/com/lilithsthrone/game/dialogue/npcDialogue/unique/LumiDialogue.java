package com.lilithsthrone.game.dialogue.npcDialogue.unique;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMMissionary;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.5
 * @version 0.2.6
 * @author Nnxx, Innoxia
 */
public class LumiDialogue {
	
	public static final DialogueNodeOld LUMI_APPEARS = new DialogueNodeOld("Alleyways", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getAuthor() {
			return "Nnxx";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_APPEARS");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lumiMet)) {
					return new Response("Stay", "Decide to stay and see who approaches.", LUMI_APPEARS_REPEAT_ENCOUNTER);
				} else {
					return new Response("Stay", "Decide to stay and see who approaches.", LUMI_APPEARS_FIRST_ENCOUNTER);
				}
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Evade", "Quickly duck away down a narrow passage.") {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_APPEARS_EVADE"));
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogueNoEncounter()));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	private static int moneyStolen = 0;
	
	public static final DialogueNodeOld LUMI_APPEARS_FIRST_ENCOUNTER = new DialogueNodeOld("Alleyways", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor() {
			return "Nnxx";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_APPEARS_FIRST_ENCOUNTER");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Wait", "Keep hiding behind the pile of junk.", LUMI_APPEARS_FIRST_ENCOUNTER_WAITING) {
					@Override
					public void effects() {
						moneyStolen = Main.game.getPlayer().getMoney()>=700?700:Main.game.getPlayer().getMoney();
						Main.game.getPlayer().incrementMoney(-moneyStolen);
						
						if(moneyStolen==700) {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/lumi", "MAX_MONEY_STOLEN"));
						} else {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/lumi", "ALL_MONEY_STOLEN"));
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LUMI_APPEARS_FIRST_ENCOUNTER_WAITING = new DialogueNodeOld("Alleyways", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor() {
			return "Nnxx";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_APPEARS_FIRST_ENCOUNTER_WAITING");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Chase them", "You're not going to let this thief escape!", LUMI_CHASE);
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Let them go", "You don't have time to be chasing after petty thieves. Let them go.") {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_LET_THEM_ESCAPE"));
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogueNoEncounter()));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LUMI_CHASE = new DialogueNodeOld("Alleyways", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor() {
			return "Nnxx";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_CHASE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue pursuit", "You're not going to let this thief escape!", LUMI_CHASE_CONTINUE) {
					@Override
					public void effects() {
						Main.game.getLumi().setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), true);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LUMI_CHASE_CONTINUE = new DialogueNodeOld("Alleyways", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor() {
			return "Nnxx";
		}
		
		@Override
		public int getMinutesPassed() {
			return 10;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_CHASE_CONTINUE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Talk", "Decide to try talking to her. After all, violence is never the solution!", LUMI_CAUGHT_TALK);
				
			} else if (index == 2) {
				return new ResponseCombat("Fight",
						"You're not going to let this thief escape!",
						Main.game.getLumi(),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_COMBAT_PC_OPENING")),
								new Value<>(Main.game.getLumi(), UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_COMBAT_LUMI_OPENING")))) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lumiDisabled, true);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld COMBAT_PLAYER_LOSS = new DialogueNodeOld("Alleyways", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor() {
			return "Nnxx";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "COMBAT_PLAYER_LOSS");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Carry on your way...", null){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.getLumi().setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld COMBAT_PLAYER_WIN = new DialogueNodeOld("Alleyways", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor() {
			return "Nnxx";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "COMBAT_PLAYER_WIN");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Recover money", "Just pick your money up and leave without making a fuss.", COMBAT_PLAYER_WIN_RECOVER_MONEY) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(moneyStolen);
					}
				};
				
			} else if (index == 2 && Main.getProperties().hasValue(PropertyValue.nonConContent)) {
				return new ResponseSex("Take advantage",
						"Now that she's been subdued, it's time to have some fun with this helpless wolf-girl!",
						false, false,
						new SMMissionary(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getLumi(), SexPositionSlot.MISSIONARY_ON_BACK))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.DOM_ROUGH;
								}
								return SexPace.SUB_RESISTING;
							}
						},
						null,
						AFTER_SEX, UtilText.parseFromXMLFile("characters/dominion/lumi", "COMBAT_PLAYER_WIN_TAKE_ADVANTAGE")) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementKarma(-1000);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX = new DialogueNodeOld("Alleyways", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor() {
			return "Nnxx";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "AFTER_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Carry on your way...", null){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.getLumi().setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld COMBAT_PLAYER_WIN_RECOVER_MONEY = new DialogueNodeOld("Alleyways", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor() {
			return "Nnxx";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "COMBAT_PLAYER_WIN_RECOVER_MONEY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Carry on your way...", null){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.getLumi().setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld COMBAT_PLAYER_WIN_TAKE_ADVANTAGE = new DialogueNodeOld("Alleyways", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor() {
			return "Nnxx";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "COMBAT_PLAYER_WIN_TAKE_ADVANTAGE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Carry on your way...", null){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.getLumi().setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LUMI_CAUGHT_TALK = new DialogueNodeOld("Alleyways", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor() {
			return "Nnxx";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_CAUGHT_TALK");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Give", "Let Lumi keep the money that she stole from you, and tell her how to make the most of it.", LUMI_CAUGHT_TALK_GIVE_MONEY) {
					@Override
					public void effects() {
						Main.game.getLumi().setPlayerKnowsName(true);
						Main.game.getTextEndStringBuilder().append(Main.game.getLumi().incrementAffection(Main.game.getPlayer(), 10));
					}
				};
				
			} else if(index == 2) {
				return new Response("Ask", "Ask Lumi to return the money that she stole from you.", LUMI_CAUGHT_TALK_ASK_FOR_MONEY_BACK) {
					@Override
					public void effects() {
						Main.game.getLumi().setPlayerKnowsName(true);
						Main.game.getPlayer().incrementMoney(moneyStolen);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lumiPromisedDinner, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getLumi().incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index == 3) {
				return new Response("Threaten", "Discipline this thief! You get the feeling that if you were to do this, you'd never see her again.", LUMI_CAUGHT_TALK_THREATEN){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lumiDisabled, true);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LUMI_CAUGHT_TALK_GIVE_MONEY = new DialogueNodeOld("Alleyways", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor() {
			return "Nnxx";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_CAUGHT_TALK_GIVE_MONEY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Carry on your way...", null){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LUMI_CAUGHT_TALK_ASK_FOR_MONEY_BACK = new DialogueNodeOld("Alleyways", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor() {
			return "Nnxx";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_CAUGHT_TALK_ASK_FOR_MONEY_BACK");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Carry on your way...", null){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LUMI_CAUGHT_TALK_THREATEN = new DialogueNodeOld("Alleyways", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor() {
			return "Nnxx";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_CAUGHT_TALK_THREATEN");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Carry on your way...", null){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.getLumi().setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	
	
	public static final DialogueNodeOld LUMI_APPEARS_REPEAT_ENCOUNTER = new DialogueNodeOld("Alleyways", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor() {
			return "Nnxx";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_APPEARS_REPEAT_ENCOUNTER");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
}
