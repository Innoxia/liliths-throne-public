package com.lilithsthrone.game.dialogue.places.dominion.zaranixHome;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKelly;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMZaranixCockSucking;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.2
 * @version 0.2.3
 * @author Innoxia
 */
public class ZaranixHomeFirstFloorRepeat {

	public static final DialogueNode STAIRS = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getLabel() {
			return "Staircase";
		}

		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered) && Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class));
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "STAIRS"));
			
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class))) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_PRESENT"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_PRESENT_REPEAT"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class))) {
				if (index == 1) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered)) {
						return new Response("Downstairs", "You need to respond to Kelly first!", null);
						
					} else {
						return new Response("Downstairs", "Head downstairs to the ground floor of Zaranix's house.", PlaceType.ZARANIX_GF_STAIRS.getDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_STAIRS, false);
							}
						};
					}
	
				} else if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class))) {
					if(index==2) {
						return new ResponseSex("Sex", "Have some fun with Kelly.",
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
								null,
								null), AFTER_KELLY_SEX, UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
							}
						};
						
					} else if(index==3) {
						return new ResponseSex("Submit",
								"You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with Kelly. Perhaps if you submitted, [kelly.she]'d be willing to fuck you?",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
										Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null), AFTER_KELLY_SEX, UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX_SUB")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
							}
						};
						
					} else if (index == 4) {
						return new Response("Transformations",
								"Get Kelly to use [kelly.her] demonic powers to transform [kelly.herself]...",
								BodyChanging.BODY_CHANGING_CORE){
							@Override
							public void effects() {
								Main.game.saveDialogueNode();
								BodyChanging.setTarget(Main.game.getNpc(ZaranixMaidKelly.class));
							}
						};
						
					} else if(index == 5 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered)) {
						return new Response("Decline",
								"Say no to the horny demon and continue on your way.",
								STAIRS){
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX_DECLINED"));
							}
						};
						
					} else {
						return null;
					}
				} else {
					return null;
				}
			} else {
				if(index == 1) {
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
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getLabel() {
			return "Corridor";
		}

		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered) && Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class));
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "CORRIDOR"));

			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class))) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_PRESENT"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_PRESENT_REPEAT"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class))) {
				if(index==1) {
					return new ResponseSex("Sex", "Have some fun with Kelly.",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
							null,
							null), AFTER_KELLY_SEX, UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
						}
					};
					
				} else if(index==2) {
					return new ResponseSex("Submit",
							"You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with Kelly. Perhaps if you submitted, [kelly.she]'d be willing to fuck you?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_KELLY_SEX, UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX_SUB")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
						}
					};
					
				} else if (index == 3) {
					return new Response("Transformations",
							"Get Kelly to use [kelly.her] demonic powers to transform [kelly.herself]...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(Main.game.getNpc(ZaranixMaidKelly.class));
						}
					};
					
				} else if(index == 4 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered)) {
					return new Response("Decline",
							"Say no to the horny demon and continue on your way.",
							KELLY_DECLINE){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
						}
					};
					
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode KELLY_DECLINE = new DialogueNode("", "", false, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX_DECLINED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_FF_STAIRS)) {
				if(index==1) {
					return new Response("Downstairs", "Head downstairs to the ground floor of Zaranix's house.", PlaceType.ZARANIX_GF_STAIRS.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_STAIRS, false);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_KELLY_SEX = new DialogueNode("", "Kelly lets out a deep sigh as she steps back.", false) {

		@Override
		public String getLabel() {
			return "Finished";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(ZaranixMaidKelly.class)) == 0) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "AFTER_KELLY_SEX_NO_ORGASM"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "AFTER_KELLY_SEX"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_FF_STAIRS)) {
				if(index==1) {
					return new Response("Downstairs", "Head downstairs to the ground floor of Zaranix's house.", PlaceType.ZARANIX_GF_STAIRS.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_STAIRS, false);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getLabel() {
			return "Room";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_OUTSIDE"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Enter", "Knock on Zaranix's door and step inside.", ZARANIX_ROOM_ENTER);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_EXIT = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_EXIT"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Enter", "Knock on Zaranix's door and step inside.", ZARANIX_ROOM_ENTER);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_ENTER = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixRepeatEncountered)) {
				return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_ENTER"));
			} else {
				return(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_ENTER_REPEAT"));
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 0) {
				return new Response("Leave", "Say goodbye and step outside into the first-floor corridor once again.", ZARANIX_ROOM_EXIT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixRepeatEncountered, true);
					}
				};
				
			} else if(index == 1) {
				return new Response("Maids", "Ask Zaranix about his maids.", ZARANIX_ROOM_MAIDS) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixRepeatEncountered, true);
					}
				};
				
			} else if(index == 2) {
				return new Response("Experiments", "Ask Zaranix about the experiments he's running, and what he was having Arthur work on.", ZARANIX_ROOM_EXPERIMENTS) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixRepeatEncountered, true);
					}
				};
				
			} else if(index == 3) {
				return new Response("Incubus form", "Ask Zaranix about his incubus form. After all, most demons that you've seen choose to stay as females.", ZARANIX_ROOM_INCUBUS_FORM) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixRepeatEncountered, true);
					}
				};
				
			} else if(index == 4) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Blowjob", "As you are unable to get access to your mouth, you cannot offer to suck Zaranix's cock!", null);
				}
				return new ResponseSex("Blowjob", "Ask Zaranix if he'd like a blowjob...",
						true, true,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX_ZARANIX_BLOWJOB,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_BLOWJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index == 5) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Blowjob (succubus)", "As you are unable to get access to your mouth, you cannot offer to suck Zaranix's cock!", null);
				}
				return new Response("Blowjob (succubus)", "Tell Zaranix that if he transforms into his succubus form then you'd be happy to give 'her' a blowjob...", ZARANIX_ROOM_BLOWJOB_TF) {
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
	
	public static final DialogueNode ZARANIX_ROOM_MAIDS = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_MAIDS"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Maids", "You're already asking Zaranix about his maids!", null);
			} else {
				return ZARANIX_ROOM_ENTER.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_EXPERIMENTS = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_EXPERIMENTS"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 2) {
				return new Response("Experiments", "You're already asking Zaranix about his experiments!", null);
			} else {
				return ZARANIX_ROOM_ENTER.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_INCUBUS_FORM = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_INCUBUS_FORM"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 3) {
				return new Response("Incubus form", "You're already asking Zaranix about his incubus form!", null);
			} else {
				return ZARANIX_ROOM_ENTER.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_BLOWJOB_TF = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_BLOWJOB_TF_START"));
			((Zaranix)Main.game.getNpc(Zaranix.class)).transformFeminine();
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_BLOWJOB_TF_END"));
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Suck cock", "Get started on sucking Zaranix's cock...",
						true, true,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX_ZARANIX_BLOWJOB,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_BLOWJOB_TF_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SEX_ZARANIX_BLOWJOB = new DialogueNode("Finished", "Zaranix has had enough of experiencing your cock-sucking skills for now...", true) {
		@Override
		public String getLabel() {
			return "Finished";
		}
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "AFTER_SEX_ZARANIX_BLOWJOB"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Continue on your way through Zaranix's mansion...", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_CORRIDOR);
						if(Main.game.getNpc(Zaranix.class).isFeminine()) {
							Main.game.getNpc(Zaranix.class).setStartingBody(false);
						}
					}
				};
			}
			return null;
		}
	};
}
