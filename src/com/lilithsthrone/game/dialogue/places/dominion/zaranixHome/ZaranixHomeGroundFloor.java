package com.lilithsthrone.game.dialogue.places.dominion.zaranixHome;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Amber;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKatherine;
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
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMAmberDoggyFucked;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMZaranixCockSucking;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.89
 * @version 0.2.3
 * @author Innoxia
 */
public class ZaranixHomeGroundFloor {
	
	public static void resetHouseAfterLeaving() {
		// Maids:
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixAmberSubdued, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKatherineSubdued, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKellySubdued, false);
		
		Main.game.getAmber().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, true);
		
		((Zaranix)Main.game.getZaranix()).resetBody();
		((Amber)Main.game.getAmber()).resetBody();
		((ZaranixMaidKatherine)Main.game.getKatherine()).resetBody();
		((ZaranixMaidKelly)Main.game.getKelly()).resetBody();
	}
	
	public static final DialogueNodeOld OUTSIDE = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Zaranix's Home";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixDiscoveredHome)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "OUTSIDE_REPEAT"));
				
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixMaidsHostile)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "OUTSIDE_REPEAT_HOSTILE_MAIDS"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "OUTSIDE_REPEAT_NON_HOSTILE_MAIDS"));
				}
				
				return UtilText.nodeContentSB.toString();
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "OUTSIDE");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixMaidsHostile)) {
					return new Response("Knock door", "Zaranix's maids will recognise you on sight, and won't let you in. You'll have to find another way to get inside.", null);
				}
				return new Response("Knock door", "Knock on the door and wait for someone to answer.", OUTSIDE_KNOCK_ON_DOOR) {
					@Override
					public void effects() {
						Main.game.getAmber().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
					}
				};

			} else if (index == 2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly over fence", "Fly over the garden's fence and see if there's a way in through there.", GARDEN_ENTRY) {
						@Override
						public void effects() {
							Main.mainController.moveGameWorld(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_GARDEN_ENTRY, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "A small fence like the one before you is no obstacle for someone who can fly."
										+ " Spreading your wings, you take a little run up before launching yourself into the air."
										+ " Quickly gaining altitude, you wheel around and swoop down into the garden adjoining Zaranix's home."
									+ "</p>");
						}
					};
				}
				return new Response("Climb fence", "Climb over the garden's fence and see if there's a way in through there.", GARDEN_ENTRY) {
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_GARDEN_ENTRY, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
						Main.game.getTextStartStringBuilder().append(
							"<p>"
								+ "Deciding to try and sneak your way into Zaranix's home, you loiter about the fence separating his garden from the public street, waiting for an opportunity to scale the obstacle."
								+ " Once you're sure that nobody is looking your way, you quickly climb up the iron bars of the fence, and, swinging your [pc.legs] over the top, you jump down into the private garden."
							+ "</p>");
					}
				};

			} else if (index == 3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKickedDownDoor)) {
					return new Response("Kick down door", "After your last entrance, the front door has been reinforced. You're unable to enter like this again.", null);
					
				} else if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE) >= PhysiqueLevel.THREE_POWERFUL.getMinimumValue()) {
					return new Response("Kick down door", "Kick down the front door.", ENTRANCE_KICK_DOWN_DOOR) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getAmber().setPlayerKnowsName(true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKickedDownDoor, true);
							Main.mainController.moveGameWorld(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, true);
							Main.game.getAmber().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
						}
					};
				} else {
					return new Response("Kick down door", "You don't think you're strong enough to kick down such a sturdy-looking door. (Requires 35 physique.)", null);
				}
				
			} else if (index == 0) {
				return new Response("Leave", "Turn around and walk away.", Main.game.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_KNOCK_ON_DOOR = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Home";
		}

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_REPEAT");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor)) {
				if (index == 1) {
					return new Response("Step back", "Step back from the door and think about finding another way in.", OUTSIDE) {
						@Override
						public void effects() {
							Main.game.getAmber().returnToHome();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_SLAMMED_IN_FACE"));
						}
					};

				} else if (index == 2) {
					return new Response("Beg", "Beg the maid to let you in.", OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), CorruptionLevel.THREE_DIRTY, null, null, null);

				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Arthur", "Ask to see Arthur.", OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKnockedOnDoor, true);
						}
					};
					
				} else if (index == 0) {
					return new Response("Leave", "Say that you've got the wrong house and take your leave.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							Main.game.getAmber().returnToHome();
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKnockedOnDoor, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_WRONG_HOUSE"));
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Home";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Step back", "Step back from the door and think about finding another way in.", OUTSIDE) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_SLAMMED_IN_FACE"));
					}
				};

			} else if (index == 2) {
				return new Response("Beg", "Beg the maid to let you in.", OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG,
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), CorruptionLevel.THREE_DIRTY, null, null, null);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Home";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back out", "Stand up and step back. You're definitely not willing to do anything like <i>that</i>.", OUTSIDE) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG_REFUSE"));
					}
				};

			} else if (index == 1) {
				return new Response("Reluctant lick", "If this is what it's going to take to finally meet Arthur, you suppose that you'll do it, even though you're quite reluctant about the whole thing.",
						OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_RELUCTANT_LICK,
						Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_FOOT_RECEIVING), CorruptionLevel.TWO_HORNY, null, null, null) {
					@Override
					public void effects() {
						Main.game.getAmber().setPlayerKnowsName(true);
					}
				};

			} else if (index == 2) {
				return new Response("Eager lick", "Immediately drop down onto all fours and enthusiastically lick the maid's shoes.",
						OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_EAGER_LICK,
						Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_FOOT_RECEIVING), CorruptionLevel.FOUR_LUSTFUL, null, null, null) {
					@Override
					public void effects() {
						Main.game.getAmber().setPlayerKnowsName(true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_RELUCTANT_LICK = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Home";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG_RELUCTANT_LICK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Inside", "Crawl alongside Amber as she leads you into the house.", MEETING_ZARANIX) {
					@Override
					public void effects() {
						Main.game.getAmber().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_EAGER_LICK = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Home";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG_EAGER_LICK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Wait", "Do as Amber says and wait for her return.", MEETING_ZARANIX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG_WAITING"));
						Main.game.getAmber().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};

			} else if (index == 2) {
				return new Response("Lick soles", "Don't let Amber get away just yet! You still haven't cleaned the soles of her shoes!",
						OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_EAGER_LICK_SOLES,
						Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_FOOT_RECEIVING), CorruptionLevel.FIVE_CORRUPT, null, null, null);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_EAGER_LICK_SOLES = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Home";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG_EAGER_LICK_SOLES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Inside", "Crawl alongside Amber as she leads you into the house.", MEETING_ZARANIX) {
					@Override
					public void effects() {
						Main.game.getAmber().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MEETING_ZARANIX = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Sit on floor", "Do as Amber commands and sit on the floor.", MEETING_ZARANIX_SIT_FLOOR) {
					@Override
					public void effects() {
						Main.game.getZaranix().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else if (index == 2) {
				return new Response("Sit on sofa", "Disobey Amber and sit on one of the sofas.", MEETING_ZARANIX_SIT_SOFA) {
					@Override
					public void effects() {
						Main.game.getZaranix().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MEETING_ZARANIX_SIT_FLOOR = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_SIT_FLOOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Hold back", "Simply say that Lilaya wants Arthur back, and avoid telling Zaranix anything about why.", MEETING_ZARANIX_HOLD_BACK);
				
			} else if (index == 2) {
				return new Response("Explain everything", "Tell Zaranix all about your appearance in this world, and how Lilaya needs Arthur's help in order to find out what's going on.", MEETING_ZARANIX_EXPLAIN_EVERYTHING);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MEETING_ZARANIX_SIT_SOFA = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_SIT_SOFA");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Hold back", "Simply say that Lilaya wants Arthur back, and avoid telling Zaranix anything about why.", MEETING_ZARANIX_HOLD_BACK);
				
			} else if (index == 2) {
				return new Response("Explain everything", "Tell Zaranix all about your appearance in this world, and how Lilaya needs Arthur's help in order to find out what's going on.", MEETING_ZARANIX_EXPLAIN_EVERYTHING);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MEETING_ZARANIX_HOLD_BACK = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_HOLD_BACK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Arthur", "You finally come face-to-face with your elusive quarry.", MEETING_ZARANIX_ARTHUR) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE));
						Main.game.getArthur().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MEETING_ZARANIX_EXPLAIN_EVERYTHING = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_EXPLAIN_EVERYTHING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Arthur", "You finally come face-to-face with your elusive quarry.", MEETING_ZARANIX_ARTHUR) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE));
						Main.game.getArthur().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MEETING_ZARANIX_ARTHUR = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_ARTHUR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Refuse to perform any sexual favours for Zaranix or Amber and take your leave.",  PlaceType.DOMINION_DEMON_HOME_ARTHUR.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_ARTHUR_REFUSE_SEX"));
						Main.game.getArthur().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR, false);
					}
				};
				
			} else if (index == 2) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("'Thank' Zaranix", "You're unable to suck Zaranix's cock, as you can't get access to your mouth!", null);
					
				} else {
					return new Response("'Thank' Zaranix", "Show Zaranix how grateful you are. (Suck his cock.)", MEETING_ZARANIX_ARTHUR_THANK_ZARANIX) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getZaranix().displaceClothingForAccess(CoverableArea.PENIS);
							Main.game.getArthur().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
				}
				
			} else if (index == 3) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
						&& (Main.game.getPlayer().hasVagina()
								?!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
								:true)) {
					return new Response("'Thank' Amber", "You're unable to get fucked by Amber, as you can't get access to your asshole"+(Main.game.getPlayer().hasVagina()?" or vagina":"")+"!", null);
					
				} else {
					return new Response("'Thank' Amber", "Show Amber how grateful you are. (Get fucked by Amber, doggy-style.)", MEETING_ZARANIX_ARTHUR_THANK_AMBER) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getAmber().unequipClothingIntoVoid(Main.game.getAmber().getClothingInSlot(InventorySlot.TORSO_UNDER), true, Main.game.getAmber());
							Main.game.getArthur().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MEETING_ZARANIX_ARTHUR_THANK_ZARANIX = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_ARTHUR_THANK_ZARANIX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Suck cock", "Show Zaranix how good you are at sucking cock.",
						true, true,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getZaranix(), SexPositionSlot.CHAIR_ORAL_SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_KNEELING))),
						null,
						AFTER_SEX_THANKING_ZARANIX, "<p>"
						+ "</p>");
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MEETING_ZARANIX_ARTHUR_THANK_AMBER = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_ARTHUR_THANK_AMBER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Lift ass", "Do as Amber commands and lift your ass towards her.",
						true, true,
						new SMAmberDoggyFucked(
								Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.DOGGY_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
						null,
						AFTER_SEX_THANKING_AMBER, "<p>"
							+ "You obediently lift your ass towards Amber, letting out a little cry as you suddenly feel the sharp slap of her hand across your right cheek, before she growls out,"
							+ " [amber.speech(Squeal all you want, bitch, <i>you're mine now!</i>)]"
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
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "AFTER_SEX_THANKING_ZARANIX");
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
	
	public static final DialogueNodeOld AFTER_SEX_THANKING_AMBER = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Amber is finished";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "AFTER_SEX_THANKING_AMBER");
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
	
	
	// Combat route:
	
	public static final DialogueNodeOld ENTRANCE_KICK_DOWN_DOOR = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Entrance Hall";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_KICK_DOWN_DOOR"));
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_KICK_DOWN_DOOR_MAIDS_MET"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_KICK_DOWN_DOOR_MAIDS_NOT_MET"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight", "Defend yourself against the furious maid!", Main.game.getAmber()) {
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
	
	
	// General places:
	
	public static final DialogueNodeOld ENTRANCE = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Entrance Hall";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE"));
			
			if(Main.game.getAmber().getLocationPlace().getPlaceType()==PlaceType.ZARANIX_GF_ENTRANCE) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_AMBER_PRESENT"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Exit", "Leave Zaranix's house and head back out into Demon Home. <b>You will have to gain entry all over again if you choose to leave now!</b>", PlaceType.DOMINION_DEMON_HOME_ARTHUR.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR, false);
						resetHouseAfterLeaving();
					}
				};

			} else if(index==2 && Main.game.getAmber().getLocationPlace().getPlaceType()==PlaceType.ZARANIX_GF_ENTRANCE) {
				return new ResponseSex("Use Amber", "Have some fun with this fiery maid.",
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.STANDING_SUBMISSIVE))),
						null,
						Amber.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_AMBER_SEX"));
				
			} else if(index==3 && Main.game.getAmber().getLocationPlace().getPlaceType()==PlaceType.ZARANIX_GF_ENTRANCE) {
				return new ResponseSex("Submit",
						"Amber's fiery personality is seriously turning you on. You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with her. Perhaps if you submitted, she'd be willing to fuck you?",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						null,
						Amber.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_AMBER_SEX_SUBMIT"));
				
			} else if (index == 4 && Main.game.getAmber().getLocationPlace().getPlaceType()==PlaceType.ZARANIX_GF_ENTRANCE) {
				return new Response("Transformations",
						"Get Amber to use [amber.her] demonic powers to transform [amber.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getAmber());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
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
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "STAIRS"));
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "STAIRS_KATHERINE_NOT_SUBDUED"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Upstairs", "Head upstairs to the first floor of Zaranix's house.", PlaceType.ZARANIX_FF_STAIRS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_STAIRS, false);
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

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR"));
			
			if(Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1) != null
					&& Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1).getPlace().getPlaceType()==PlaceType.ZARANIX_GF_MAID
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_KATHERINE_NOT_SUBDUED"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld GARDEN_ENTRY = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Zaranix's Garden";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "GARDEN_ENTRY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly over fence",
							"Fly over the garden's fence and back out into Demon Home. <b>If you leave, all progress you've made through Zaranix's home will be reset!</b>",
							PlaceType.DOMINION_DEMON_HOME_ARTHUR.getDialogue(false)) {
						@Override
						public void effects() {
							resetHouseAfterLeaving();
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR, false);
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Deciding that you'll come back another time, you take a little run-up down the garden path, before launching yourself into the air."
										+ " Swooping down over the fence, you very quickly find yourself back out in Demon Home once again..."
									+ "</p>");
						}
					};
				}
				return new Response("Climb fence",
						"Climb over the garden's fence and head back out into Demon Home. <b>If you leave, all progress you've made through Zaranix's home will be reset!</b>",
						PlaceType.DOMINION_DEMON_HOME_ARTHUR.getDialogue(false)) {
					@Override
					public void effects() {
						resetHouseAfterLeaving();
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR, false);
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Deciding that you'll come back another time, you climb up and over the fence, quickly finding yourself back out in Demon Home once again..."
								+ "</p>");
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld GARDEN = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Zaranix's Garden";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "GARDEN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld GARDEN_ROOM = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Garden Room";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "GARDEN_ROOM"));
			
			if(Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX()-1, Main.game.getPlayer().getLocation().getY()) != null
					&& Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX()-1, Main.game.getPlayer().getLocation().getY()).getPlace().getPlaceType()==PlaceType.ZARANIX_GF_MAID
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "GARDEN_ROOM_KATHERINE_NOT_SUBDUED"));
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
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_SUBDUED");
			
			} else if(Main.game.getKatherine().getFoughtPlayerCount()==0) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_ENCOUNTER");
			
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_ENCOUNTER_REPEAT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				if(index==1) {
					return new ResponseSex("Use Katherine", "Have some fun with this maid.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getKatherine(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							ZaranixMaidKatherine.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_SEX"));
					
				} else if(index==2) {
					return new ResponseSex("Submit",
							"You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with Katherine. Perhaps if you submitted, she'd be willing to fuck you?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getKatherine(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							ZaranixMaidKatherine.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_SEX_SUBMIT"));
					
				} else if (index == 3) {
					return new Response("Transformations",
							"Get Katherine to use [katherine.her] demonic powers to transform [katherine.herself]...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(Main.game.getKatherine());
						}
					};
					
				} else {
					return null;
				}
			} else {
				if(index==1) {
					return new ResponseCombat("Fight", "Defend yourself against the furious maid!", Main.game.getKatherine()) {
						@Override
						public void effects() {
							Main.game.getKatherine().setPlayerKnowsName(true);
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
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued);
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
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld LOUNGE = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			if(Main.game.getAmber().getLocationPlace().getPlaceType()!=PlaceType.ZARANIX_GF_LOUNGE) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_EMPTY");
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixAmberSubdued)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_SUBDUED");
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor) || Main.game.getAmber().getFoughtPlayerCount()!=0) {
				UtilText.nodeContentSB.setLength(0);
				
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_PRESENT_ALREADY_MET"));
				
				if(Main.game.getAmber().getFoughtPlayerCount()!=0) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_PRESENT_ALREADY_MET_KNOWS_NAME"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_PRESENT_ALREADY_MET_DOES_NOT_KNOW_NAME"));
				}
				
				return UtilText.nodeContentSB.toString();
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_PRESENT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getAmber().getLocationPlace().getPlaceType()!=PlaceType.ZARANIX_GF_LOUNGE) {
				return null;
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixAmberSubdued)) {
				if(index==1) {
					return new ResponseSex("Use Amber", "Have some fun with this fiery maid.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							Amber.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_SEX")
							);
					
				} else if(index==2) {
					return new ResponseSex("Submit",
							"Amber's fiery personality is seriously turning you on. You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with her. Perhaps if you submitted, she'd be willing to fuck you?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							Amber.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_SEX_SUBMIT")
							);
					
				} else if (index == 3) {
					return new Response("Transformations",
							"Get Amber to use [amber.her] demonic powers to transform [amber.herself]...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(Main.game.getAmber());
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Approach the maid", "Walk up behind the maid. <b>She's sure to notice your approach, which will most likely result in you having to fight her!</b>", LOUNGE_AMBER) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getAmber().setPlayerKnowsName(true);
						}
					};
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld LOUNGE_AMBER = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_GREETING"));
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor) || Main.game.getAmber().getFoughtPlayerCount()!=0) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_GREETING_ALREADY_FOUGHT"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_GREETING_NEVER_FOUGHT"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight", "Defend yourself against the furious maid!", Main.game.getAmber()) {
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
}
