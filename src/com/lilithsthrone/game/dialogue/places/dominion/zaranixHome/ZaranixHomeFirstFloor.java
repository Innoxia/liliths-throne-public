package com.lilithsthrone.game.dialogue.places.dominion.zaranixHome;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKelly;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMZaranixCockSucking;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.89
 * @version 0.2.2
 * @author Innoxia
 */
public class ZaranixHomeFirstFloor {
	
	public static final DialogueNodeOld STAIRS = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Staircase";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "STAIRS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Downstairs", "Head downstairs to the ground floor of Zaranix's house.", PlaceType.ZARANIX_GF_STAIRS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_STAIRS, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CORRIDOR = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Corridor";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "CORRIDOR"));
			
			if(((Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1) != null
					&& Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1).getPlace().getPlaceType()==PlaceType.ZARANIX_FF_MAID)
					
						|| (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1) != null
								&& Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1).getPlace().getPlaceType()==PlaceType.ZARANIX_FF_MAID))
					
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKellySubdued)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "CORRIDOR_KELLY_NOT_SUBDUED"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld CORRIDOR_MAID = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Corridor";
		}

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKellySubdued)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "CORRIDOR_MAID_KELLY_SUBDUED");
			
			} else if(Main.game.getKelly().getFoughtPlayerCount()==0) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "CORRIDOR_MAID_KELLY_ENCOUNTER");
			
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "CORRIDOR_MAID_KELLY_ENCOUNTER_REPEAT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKellySubdued)) {
				if(index==1) {
					return new ResponseSex("Use Kelly", "Have some fun with this maid.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getKelly(), SexPositionSlot.STANDING_SUBMISSIVE))),
							ZaranixMaidKelly.AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "CORRIDOR_MAID_KELLY_SEX"));
					
				} else if(index==2) {
					return new ResponseSex("Submit",
							"You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with Kelly. Perhaps if you submitted, she'd be willing to fuck you?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getKelly(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							ZaranixMaidKelly.AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "CORRIDOR_MAID_KELLY_SEX_SUBMIT"));
					
				} else if (index == 3) {
					return new Response("Transformations",
							"Get Kelly to use [kelly.her] demonic powers to transform [kelly.herself]...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(Main.game.getKelly());
						}
					};
					
				} else {
					return null;
				}
			} else {
				if(index==1) {
					return new ResponseCombat("Fight", "Defend yourself against the furious maid!", Main.game.getKelly()) {
						@Override
						public void effects() {
							Main.game.getKelly().setPlayerKnowsName(true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, true);
						}
					};
				} else {
					return null;
				}
			}
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKellySubdued);
		}
	};
	
	public static final DialogueNodeOld ROOM = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Room";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld ZARANIX_ROOM = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Lab";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Zaranix's lab", "You find yourself stepping into a laboratory much like that of Lilaya's.", ZARANIX_ROOM_ENTRY) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, true);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ZARANIX_ROOM_ENTRY = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Lab";
		}

		@Override
		public String getContent() {
			if(Main.game.getZaranix().getFoughtPlayerCount()==0) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM_ENTRY");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM_ENTRY_REPEAT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getZaranix().getFoughtPlayerCount()==0) {
				if(index==1) {
					return new Response("Demand Arthur", "Refuse to tell Zaranix why you're here, and instead simply demand that he hand over Arthur to you.", ZARANIX_ROOM_NO_EXPLANATION) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
					
				} else if(index==2) {
					return new Response("Explain everything", "Tell Zaranix that Lilaya needs Arthur in order to help her unravel the mystery of inter-dimensional travel.", ZARANIX_ROOM_EXPLANATION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE));
							Main.game.getArthur().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
					
				} else {
					return null;
				}
			} else {
				if(index==1) {
					return new ResponseCombat("Fight", "Defend yourself against Zaranix's attack!", Main.game.getZaranix());
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld ZARANIX_ROOM_NO_EXPLANATION = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Lab";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM_NO_EXPLANATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight", "Defend yourself against Zaranix's attack!", Main.game.getZaranix());
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ZARANIX_ROOM_EXPLANATION = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Lab";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM_EXPLANATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Zaranix's house.", PlaceType.DOMINION_DEMON_HOME_ARTHUR.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM_EXPLANATION_THANKS"));
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR, false);
					}
				};
				
			} else if(index==2) {
				return new Response("'Thank' Zaranix", "You feel a little sorry for Zaranix. Perhaps you could offer to give him a blowjob as thanks...", ZARANIX_ROOM_EXPLANATION_THANK_ZARANIX,
						Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING), CorruptionLevel.TWO_HORNY, null, null, null) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ZARANIX_ROOM_EXPLANATION_THANK_ZARANIX = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Lab";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM_EXPLANATION_SEXY_THANKS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Suck cock", "Show Zaranix how good you are at sucking cock.",
						true, true,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getZaranix(), SexPositionSlot.CHAIR_ORAL_SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_KNEELING))),
						AFTER_SEX_THANKING_ZARANIX,
						"<p>"
						+ "</p>");
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_THANKING_ZARANIX = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix is finished";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM_EXPLANATION_SEXY_THANKS_POST_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue on your journey.",  PlaceType.DOMINION_DEMON_HOME_ARTHUR.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR, false);
					}
				};
			} else {
				return null;
			}
		}
	};
}
