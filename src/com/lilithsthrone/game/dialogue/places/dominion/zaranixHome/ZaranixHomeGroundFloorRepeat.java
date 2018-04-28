package com.lilithsthrone.game.dialogue.places.dominion.zaranixHome;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderPreference;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMAmberDoggyFucked;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMPetMounting;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMPetOral;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.2
 * @version 0.2.3
 * @author Innoxia
 */
public class ZaranixHomeGroundFloorRepeat {
	
	private static NPC pet;
	private static NPC owner;
	
	public static final DialogueNodeOld OUTSIDE = new DialogueNodeOld("Zaranix's Home", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "OUTSIDE"));
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.amberRepeatEncountered)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "OUTSIDE_RETURN");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.amberRepeatEncountered)
						&& (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor) || Main.game.getAmber().getFoughtPlayerCount()!=0)) {
					return new Response("Knock door", "Knock on the door and wait for someone to answer.", OUTSIDE_KNOCK_ON_DOOR_KNOWS_AMBER) {
						@Override
						public void effects() {
							Main.game.getAmber().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR, false);
							Main.game.getAmber().setPlayerKnowsName(true);
							Main.game.getZaranix().returnToHome();
						}
					};
					
				} else {
					return new Response("Knock door", "Knock on the door and wait for someone to answer.", OUTSIDE_KNOCK_ON_DOOR) {
						@Override
						public void effects() {
							Main.game.getAmber().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR, false);
							Main.game.getAmber().setPlayerKnowsName(true);
							Main.game.getZaranix().returnToHome();
						}
					};
				}

			} else if (index == 0) {
				return new Response("Leave", "Turn around and walk away.", DebugDialogue.getDefaultDialogueNoEncounter());

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_KNOCK_ON_DOOR_KNOWS_AMBER = new DialogueNodeOld("Zaranix's Home", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR_KNOWS_AMBER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return OUTSIDE_KNOCK_ON_DOOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_KNOCK_ON_DOOR = new DialogueNodeOld("Zaranix's Home", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.amberRepeatEncountered)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR_REPEAT");
			}
			
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Enter", "Step past Amber and enter Zaranix's home.", ENTRANCE) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.amberRepeatEncountered, true);
						Main.game.getAmber().returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "ENTRY"));
					}
				};

			} else if (index == 2) {
				return new Response("Apologise", "Do as Amber says and get down on your knees to apologise to her.", OUTSIDE_APOLOGY,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), CorruptionLevel.THREE_DIRTY, null, null, null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.amberRepeatEncountered, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), 2.5f));
					}
				};

			} else if (index == 3) {
				return new Response("Step back", "Make an excuse and step back from the door, before taking your leave.", OUTSIDE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.amberRepeatEncountered, true);
						Main.game.getAmber().returnToHome();
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "[pc.speech(I just remembered that I need to be elsewhere right now,)]"
									+ " you say, stepping back from the door."
								+ "</p>"
								+ "<p>"
									+ "[amber.speech(Don't waste my time again, bitch.)]"
									+ " Amber snarls, before slamming the door in your face."
								+ "</p>");
					}
				};

			} else {
				return null;
			}
		}
	};
	
	
	
	public static final DialogueNodeOld OUTSIDE_APOLOGY = new DialogueNodeOld("Zaranix's Home", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR_APOLOGISE"));

			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR_APOLOGISE_MOUTH_FREE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR_APOLOGISE_MOUTH_BLOCKED"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				if(index == 1) {
					return new Response("Enter", "Refuse to lick Amber's heels, and step past her into Zaranix's home.", ENTRANCE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), -2.5f));
							Main.game.getAmber().returnToHome();
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "ENTRY_APOLOGISE"));
						}
					};
					
				} else if (index == 2) {
					return new Response("Reluctantly Lick", "Reluctantly do as Amber says and start obediently licking her heels.", OUTSIDE_LICKING_RELUCTANT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), 2.5f));
						}
					};
	
				} else if (index == 3) {
					return new Response("Eagerly Lick", "Eagerly do as Amber says and start enthusiastically licking her heels.", OUTSIDE_LICKING_EAGER) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), 2.5f));
						}
					};
	
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Enter", "Amber will now let you enter Zaranix's home.", ENTRANCE) {
						@Override
						public void effects() {
							Main.game.getAmber().returnToHome();
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "ENTRY_MOUTH_BLOCKED"));
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_LICKING_RELUCTANT = new DialogueNodeOld("Zaranix's Home", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "OUTSIDE_LICKING_RELUCTANT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Enter", "Finally, Amber will let you enter Zaranix's home...", ENTRANCE) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "ENTRY_RELUCTANT_LICKING"));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OUTSIDE_LICKING_EAGER = new DialogueNodeOld("Zaranix's Home", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR_EAGER_LICKING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Refuse", "Refuse to lick Amber's soles.", ENTRANCE) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "ENTRY_EAGER_LICKING"));
					}
				};

			} else if(index == 2) {
				return new Response("Obey", "Do as Amber says and start cleaning the soles of her heels with your [pc.tongue].", OUTSIDE_LICKING_SOLES) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNodeOld OUTSIDE_LICKING_SOLES = new DialogueNodeOld("Zaranix's Home", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR_SOLES_LICKING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Enter", "Amber will now let you enter Zaranix's home.", ENTRANCE) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "ENTRY_SOLES_LICKING"));
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

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "ENTRANCE"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Exit", "Leave Zaranix's house and head back out into Demon Home.", PlaceType.DOMINION_DEMON_HOME_ARTHUR.getDialogue(false)) {
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
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.katherineRepeatEncountered) && Main.game.getCharactersPresent().contains(Main.game.getKatherine());
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "STAIRS"));
			
			if(Main.game.getCharactersPresent().contains(Main.game.getKatherine())) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.katherineRepeatEncountered)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_PRESENT"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_PRESENT_REPEAT"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.katherineRepeatEncountered)) {
					return new Response("Upstairs", "You need to respond to Katherine first!", null);
					
				} else {
					return new Response("Upstairs", "Head upstairs to the first floor of Zaranix's house.", PlaceType.ZARANIX_FF_STAIRS.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_STAIRS, false);
						}
					};
				}

			} else if(Main.game.getCharactersPresent().contains(Main.game.getKatherine())) {
				if(index==2) {
					return new ResponseSex("Sex", "Have some fun with Katherine.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getKatherine(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_KATHERINE_SEX,
							UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_SEX")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.katherineRepeatEncountered, true);
						}
					};
					
				} else if(index==3) {
					return new ResponseSex("Submit",
							"You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with Katherine. Perhaps if you submitted, [katherine.she]'d be willing to fuck you?",
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getKatherine(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_KATHERINE_SEX,
							UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_SEX_SUB")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.katherineRepeatEncountered, true);
						}
					};
					
				} else if (index == 4) {
					return new Response("Transformations",
							"Get Katherine to use [katherine.her] demonic powers to transform [katherine.herself]...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(Main.game.getKatherine());
						}
					};
					
				} else if(index == 5 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.katherineRepeatEncountered)) {
					return new Response("Decline",
							"Say no to the horny demon and continue on your way.",
							KATHERINE_DECLINE){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.katherineRepeatEncountered, true);
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
	
	public static final DialogueNodeOld AFTER_KATHERINE_SEX = new DialogueNodeOld("", "Katherine lets out a deep sigh as she steps back.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 15;
		}

		@Override
		public String getLabel() {
			return "Finished";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(Sex.getNumberOfOrgasms(Main.game.getKatherine()) == 0) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "AFTER_KATHERINE_SEX_NO_ORGASM"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "AFTER_KATHERINE_SEX"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.ZARANIX_GF_STAIRS) {
				if(index==1) {
					return new Response("Upstairs", "Head upstairs to the first floor of Zaranix's house.", PlaceType.ZARANIX_FF_STAIRS.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_STAIRS, false);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld KATHERINE_DECLINE = new DialogueNodeOld("", "", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_SEX_DECLINED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.ZARANIX_GF_STAIRS) {
				if(index==1) {
					return new Response("Upstairs", "Head upstairs to the first floor of Zaranix's house.", PlaceType.ZARANIX_FF_STAIRS.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_STAIRS, false);
						}
					};
				}
			}
			return null;
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
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.katherineRepeatEncountered) && Main.game.getCharactersPresent().contains(Main.game.getKatherine());
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "CORRIDOR"));

			if(Main.game.getCharactersPresent().contains(Main.game.getKatherine())) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.katherineRepeatEncountered)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_PRESENT"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_PRESENT_REPEAT"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getCharactersPresent().contains(Main.game.getKatherine())) {
				if(index==1) {
					return new ResponseSex("Sex", "Have some fun with Katherine.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getKatherine(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_KATHERINE_SEX,
							UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_SEX")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.katherineRepeatEncountered, true);
						}
					};
					
				} else if(index==2) {
					return new ResponseSex("Submit",
							"You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with Katherine. Perhaps if you submitted, [katherine.she]'d be willing to fuck you?",
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getKatherine(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_KATHERINE_SEX,
							UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_SEX_SUB")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.katherineRepeatEncountered, true);
						}
					};
					
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
					
				} else if(index == 4 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.katherineRepeatEncountered)) {
					return new Response("Decline",
							"Say no to the horny demon and continue on your way.",
							KATHERINE_DECLINE){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.katherineRepeatEncountered, true);
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
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "GARDEN_ENTRY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
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
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "GARDEN");
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

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "GARDEN_ROOM"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
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
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "ROOM");
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
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Amber", "Walk over to Amber and say hello.", LOUNGE_AMBER_GREETING);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LOUNGE_AMBER_GREETING = new DialogueNodeOld("", "", true, true) {
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
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_GREETING"));
			
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM
					|| Main.game.getCurrentWeather()==Weather.RAIN) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_GREETING_BAD_WEATHER"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_GREETING_GOOD_WEATHER"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM
					|| Main.game.getCurrentWeather()==Weather.RAIN) {
				if(index==1) {
					return new Response("Submit", "Do as Amber says and get down on all fours, ready to act as a foot stall for her.", LOUNGE_AMBER_FOOT_STALL) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), 2.5f));
						}
					};
					
				} else if(index==2) {
					return new Response("Decline", "Tell Amber that you're really not interested in that sort of thing.", LOUNGE_AMBER_DECLINED_FOOT_STALL) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), -5));
						}
					};
					
				} else if(index==3) {
					if(Main.game.getAmber().getAffection(Main.game.getPlayer())<100) {
						return new Response("Submissive sex",
								"There's no way Amber will have sex with you if you were to simply ask for it. Maybe you could try it if you impressed her enough first... (Requires Amber to have maximum affection towards you.)",
								null);
						
					} else {
						return new ResponseSex("Submissive sex", "Ask Mistress Amber to fuck you. As you've been a good bitch for her, you think that she'll say yes.",
								Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST)), null, CorruptionLevel.THREE_DIRTY, null, null, null,
								true, false,
								new SMAmberDoggyFucked(
										Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.DOGGY_BEHIND_AMBER)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS_AMBER))),
								AMBER_LOUNGE_POST_CONSENSUAL_SEX,
								UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_BEG_FOR_SEX"));
					}
					
				} else if(index==4) {
					return new Response("Fuck off", "Tell Amber to fuck off.", LOUNGE_AMBER_FURIOUS) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), -25));
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Submit", "Do as Amber says and get down on all fours, ready to go for a walk.", LOUNGE_AMBER_WALKIES) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK)==null || Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK).getClothingType()!=ClothingType.AMBERS_BITCH_CHOKER) {
								AbstractClothing collar = AbstractClothingType.generateClothing(ClothingType.AMBERS_BITCH_CHOKER);
								
								if(Main.game.getPlayer().isAbleToEquip(collar, true, Main.game.getAmber())) {
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_WALKIES_ATTACHES_COLLAR"));
									Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().equipClothingFromNowhere(collar, true, Main.game.getAmber())+"</p>");
								} else {
									UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_WALKIES_WITHOUT_COLLAR"));
								}
								
							} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK)!=null && Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK).getClothingType()==ClothingType.AMBERS_BITCH_CHOKER) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_WALKIES_WITH_COLLAR"));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_WALKIES_WITHOUT_COLLAR"));
							}
							
							Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), 2.5f));
						}
					};
					
				} else if(index==2) {
					return new Response("Decline", "Tell Amber that you're really not interested in that sort of thing.", LOUNGE_AMBER_DECLINED_WALK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), -5));
						}
					};
					
				} else if(index==3) {
					if(Main.game.getAmber().getAffection(Main.game.getPlayer())<100) {
						return new Response("Submissive sex",
								"There's no way Amber will have sex with you if you were to simply ask for it. Maybe you could try it if you impressed her enough first... (Requires Amber to have maximum affection towards you.)",
								null);
						
					} else {
						return new ResponseSex("Submissive sex", "Ask Mistress Amber to fuck you. As you've been a good bitch for her, you think that she'll say yes.",
								Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST)), null, CorruptionLevel.THREE_DIRTY, null, null, null,
								true, false,
								new SMAmberDoggyFucked(
										Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.DOGGY_BEHIND_AMBER)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS_AMBER))),
								AMBER_LOUNGE_POST_CONSENSUAL_SEX,
								UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_BEG_FOR_SEX"));
					}
					
				}  else if(index==4) {
					return new Response("Fuck off", "Tell Amber to fuck off.", LOUNGE_AMBER_FURIOUS) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), -25));
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld LOUNGE_AMBER_FOOT_STALL = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FOOT_STALL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Massage feet", "Do as Amber says and take off her shoes and stockings to massage her feet.", LOUNGE_AMBER_FOOT_MASSAGE) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), 2.5f));
					}
				};
				
			} else if(index==2) {
				return new Response("Decline", "Stay still and refuse to massage Amber's feet.", LOUNGE_AMBER_FOOT_MASSAGE_REFUSED) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), -5));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LOUNGE_AMBER_DECLINED_FOOT_STALL = new DialogueNodeOld("", "", false, true) {
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
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_DECLINED_FOOT_STALL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld LOUNGE_AMBER_FOOT_MASSAGE_REFUSED = new DialogueNodeOld("", "", false, true) {
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
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FOOT_MASSAGE_REFUSED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld LOUNGE_AMBER_FOOT_MASSAGE = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public boolean isTravelDisabled() {
			return Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true);
		}
		
		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FOOT_MASSAGE"));
			
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FOOT_MASSAGE_MOUTH_FREE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FOOT_MASSAGE_MOUTH_BLOCKED"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				if(index==1) {
					return new Response("Suck toes", "Do as Amber says and start giving her feet some oral attention.", LOUNGE_AMBER_FOOT_MASSAGE_ORAL) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), 10));
						}
					};
					
				} else if(index==2) {
					return new Response("Decline", "Stay still and refuse to lick Amber's feet.", LOUNGE_AMBER_FOOT_MASSAGE_ORAL_REFUSED) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), -5));
						}
					};
					
				} else {
					return null;
				}
			} else {
				return LOUNGE.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNodeOld LOUNGE_AMBER_FOOT_MASSAGE_ORAL = new DialogueNodeOld("", "", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FOOT_MASSAGE_ORAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld LOUNGE_AMBER_FOOT_MASSAGE_ORAL_REFUSED = new DialogueNodeOld("", "", false, true) {
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
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FOOT_MASSAGE_ORAL_REFUSED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld LOUNGE_AMBER_DECLINED_WALK = new DialogueNodeOld("", "", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_DECLINED_WALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld LOUNGE_AMBER_FURIOUS = new DialogueNodeOld("Lounge", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FURIOUS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Refuse", "Refuse to let Amber fuck you.", LOUNGE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FURIOUS_NO_APOLOGY"));
						Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), -10));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Submit",
						"Do as Amber says and drop down on all fours, ready for her to fuck you.",
						null, null, null, null, null, null,
						!Main.getProperties().hasValue(PropertyValue.nonConContent), false,
						new SMAmberDoggyFucked(
								Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.DOGGY_BEHIND_AMBER)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS_AMBER))),
						AMBER_LOUNGE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FURIOUS_APOLOGY")) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), 15));
					}
				};
				
			} if(index==3) {
				return new Response("Insult her", "Push your luck and insult Amber again.", LOUNGE_AMBER_LOSES_IT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FURIOUS_NO_APOLOGY"));
						Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), -25));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AMBER_LOUNGE_POST_CONSENSUAL_SEX = new DialogueNodeOld("Collapse", "Amber is finished with you.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 15;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "AMBER_LOUNGE_POST_CONSENSUAL_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld AMBER_LOUNGE_POST_SEX = new DialogueNodeOld("Collapse", "Amber is finished with you.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 15;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "AMBER_LOUNGE_POST_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld LOUNGE_AMBER_LOSES_IT = new DialogueNodeOld("Lounge", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_LOSES_IT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseCombat("Fight", "Defend yourself against Amber!", Main.game.getAmber());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld COMBAT_LOSS = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "COMBAT_LOSS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Used", "Amber starts fucking you.",
						false, false,
						new SMAmberDoggyFucked(
								Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.DOGGY_BEHIND_AMBER)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS_AMBER))),
						AFTER_AMBER_SEX_LOSS,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "COMBAT_LOSS_SEX"));
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_AMBER_SEX_LOSS = new DialogueNodeOld("Used", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 15;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "AFTER_AMBER_SEX_LOSS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld COMBAT_VICTORY = new DialogueNodeOld("", "", true) {
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
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "COMBAT_VICTORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Refuse", "Refuse to have sex with Amber and leave her to think about how rude she's been to you.", COMBAT_VICTORY_LEAVE);
				
			} if(index==2) {
				return new ResponseSex("Sex", "Have some fun with Amber.",
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.STANDING_SUBMISSIVE))),
						AFTER_AMBER_SEX_VICTORY,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "COMBAT_VICTORY_SEX"));
				
			} else if(index==3) {
				return new ResponseSex("Submit",
						"Amber's fiery personality is seriously turning you on. You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with her. Perhaps if you submitted, she'd be willing to fuck you?",
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null, null,
						true, true,
						new SMAmberDoggyFucked(
								Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.DOGGY_BEHIND_AMBER)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS_AMBER))),
						AFTER_AMBER_SEX_SUB_VICTORY,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "COMBAT_VICTORY_SEX_SEB"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld COMBAT_VICTORY_LEAVE = new DialogueNodeOld("Finished", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 15;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "COMBAT_VICTORY_LEAVE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld AFTER_AMBER_SEX_VICTORY = new DialogueNodeOld("Finished", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 15;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "AFTER_AMBER_SEX_VICTORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld AFTER_AMBER_SEX_SUB_VICTORY = new DialogueNodeOld("Used", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 15;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "AFTER_AMBER_SEX_SUB_VICTORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld LOUNGE_AMBER_WALKIES = new DialogueNodeOld("Lounge", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_WALKIES"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Heel", "Crawl alongside Amber as she leads you out into Demon Home.", WALKIES_HUMILIATION) {
					@Override
					public DialogueNodeOld getNextDialogue() {
						double rnd = Math.random();
//						if(rnd>0.01f) { // Testing:
//							return WALKIES_PET_FUCKS;
//						}
						if(rnd<0.3) {
							return WALKIES_PEACEFUL;
						} else if(rnd<0.5) {
							return WALKIES_HUMILIATION;
						} else if(rnd<0.7) {
							return WALKIES_PUNISHMENT;
						} else if(rnd<0.85
								&& (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) || (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)))) {
							return WALKIES_AMBER_FUCKS;
						} else {
							return WALKIES_PET_FUCKS;
						}
					}
					@Override
					public void effects() {
						Cell c = Main.game.getWorlds().get(WorldType.DOMINION).getRandomCell(PlaceType.DOMINION_DEMON_HOME);
						Main.game.getAmber().setLocation(WorldType.DOMINION, c.getLocation(), false);
						Main.game.getPlayer().setLocation(WorldType.DOMINION, c.getLocation(), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_WALKIES_BEGIN"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WALKIES_HUMILIATION = new DialogueNodeOld("Demon Home", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 15;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_HUMILIATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Don't Lick", "Refuse to lick Amber's shoes in front of her friends.", WALKIES_HUMILIATION_REFUSE) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), -10));
					}
				};
				
			} else if(index==2) {
				return new Response("Reluctant Lick", "Reluctantly lick Amber's shoes in front of her friends.", WALKIES_HUMILIATION_RELUCTANT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), -2.5f));
					}
				};
				
			} else if(index==3) {
				return new Response("Eager Lick", "Eagerly lick Amber's shoes in front of her friends.", WALKIES_HUMILIATION_EAGER) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), 5f));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WALKIES_HUMILIATION_REFUSE = new DialogueNodeOld("Demon Home", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_HUMILIATION_REFUSE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Home", "Crawl alongside Amber as she leads you back to Zaranix's Home.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WALKIES_HUMILIATION_RELUCTANT = new DialogueNodeOld("Demon Home", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_HUMILIATION_RELUCTANT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Home", "Crawl alongside Amber as she leads you back to Zaranix's Home.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WALKIES_HUMILIATION_EAGER = new DialogueNodeOld("Demon Home", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_HUMILIATION_EAGER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Home", "Crawl alongside Amber as she leads you back to Zaranix's Home.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WALKIES_PEACEFUL = new DialogueNodeOld("Demon Home", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PEACEFUL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Home", "Crawl alongside Amber as she leads you back to Zaranix's Home.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WALKIES_PUNISHMENT = new DialogueNodeOld("Demon Home", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PUNISHMENT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Home", "Crawl alongside Amber as she leads you back to Zaranix's Home.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WALKIES_AMBER_FUCKS = new DialogueNodeOld("Demon Home", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_AMBER_FUCKS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Refuse", "Refuse to let Amber publicly fuck you.", WALKIES_AMBER_FUCKS_REFUSED) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), -15f));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Publicly fucked",
						"Amber starts fucking you right there in full view of the public!",
						null, null, null, null, null, null,
						true, false,
						new SMAmberDoggyFucked(
								Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.DOGGY_BEHIND_AMBER)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS_AMBER))) {
							@Override
							public boolean isPublicSex() {
								return true;
							}
						},
						WALKIES_AMBER_FUCKS_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_AMBER_FUCKS_START")) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), 10f));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WALKIES_AMBER_FUCKS_REFUSED = new DialogueNodeOld("Demon Home", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_AMBER_FUCKS_REFUSED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Home", "Crawl alongside Amber as she leads you back to Zaranix's Home.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WALKIES_AMBER_FUCKS_POST_SEX = new DialogueNodeOld("Used", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_AMBER_FUCKS_POST_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Home", "Crawl alongside Amber as she leads you back to Zaranix's Home.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WALKIES_PET_FUCKS = new DialogueNodeOld("Demon Home", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Amber's friend", "You stay by Mistress Amber's side as the two of you head over to what must be Mistress's friend.", WALKIES_PET_FUCKS_SNIFFING) {
					@Override
					public void effects() {
						Gender petGender = GenderPreference.getGenderFromUserPreferences();
						
						pet = new GenericSexualPartner(
								petGender.getGenderName().isHasPenis()?petGender:(petGender.isFeminine()?Gender.F_P_V_B_FUTANARI:Gender.M_P_MALE), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
						pet.setPlayerKnowsName(true);
						owner = new GenericSexualPartner(Gender.F_P_V_B_FUTANARI, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
						owner.setBody(GenderPreference.getGenderFromUserPreferences(), RacialBody.DEMON, RaceStage.GREATER);
						owner.setName(Name.getRandomTriplet(owner.getRace()));
						
						try {
							Main.game.addNPC(pet, false);
							Main.game.addNPC(owner, false);
							
							pet.setAffection(owner, (float) (75 + Math.random()*25));
							pet.setObedience(100);
							switch(Main.game.getPlayer().getFemininity()) {
							case ANDROGYNOUS:
								pet.setSexualOrientation(SexualOrientation.AMBIPHILIC);
								break;
							case FEMININE:
							case FEMININE_STRONG:
								pet.setSexualOrientation(SexualOrientation.GYNEPHILIC);
								break;
							case MASCULINE:
							case MASCULINE_STRONG:
								pet.setSexualOrientation(SexualOrientation.ANDROPHILIC);
								break;
							}
							owner.addSlave(pet);
							pet.unequipAllClothingIntoVoid();
							if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
								if(Math.random()<0.33f) {
									pet.addFetish(Fetish.FETISH_ANAL_GIVING);
								}
								pet.addFetish(Fetish.FETISH_VAGINAL_GIVING);
							} else {
								pet.addFetish(Fetish.FETISH_ANAL_GIVING);
							}
							pet.addFetish(Fetish.FETISH_ORAL_RECEIVING);
							pet.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR), true, owner);
							if(Math.random()>0.5f) {
								pet.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.BDSM_BALLGAG), true, owner);
							}
							owner.setAffection(pet, (float) (10 + Math.random()*90));
							owner.setPlayerKnowsName(true);
							
							Main.game.setActiveNPC(pet);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WALKIES_PET_FUCKS_SNIFFING = new DialogueNodeOld("Demon Home", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_SNIFFING", Util.newArrayListOfValues(new ListValue<>(owner), new ListValue<>(pet))));
			
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) || (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_SNIFFING_MOUNTING", Util.newArrayListOfValues(new ListValue<>(owner), new ListValue<>(pet))));
			} else if (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_SNIFFING_ORAL", Util.newArrayListOfValues(new ListValue<>(owner), new ListValue<>(pet))));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_SNIFFING_NO_SEX", Util.newArrayListOfValues(new ListValue<>(owner), new ListValue<>(pet))));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			// If no area is available, just return home:
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
					&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				if(index==1) {
					return new Response("Home", "Crawl alongside Amber as she leads you back to Zaranix's Home.", WALKIES_HOME) {
						@Override
						public void effects() {
							Main.game.getAmber().returnToHome();
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
							Main.game.banishNPC(owner);
							Main.game.banishNPC(pet);
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Refuse", "Refuse to present yourself to [npc.name].", WALKIES_PET_FUCKS_REFUSE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), -10f));
						}
					};
					
				} else if(index==2) {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) || (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
						// Mounting player:
						return new ResponseSex("Present yourself",
								"Do as Amber commands and present yourself, ready for [npc.name] to mount you!",
								null, null, null, null, null, null,
								true, false,
								new SMPetMounting(
										Util.newHashMapOfValues(new Value<>(pet, SexPositionSlot.PET_MOUNTING_HUMPING)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.PET_MOUNTING_ON_ALL_FOURS))),
								WALKIES_PET_FUCKS_POST_SEX,
								UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_START", Util.newArrayListOfValues(new ListValue<>(owner), new ListValue<>(pet)))
								+ (Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.ANUS) || (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA))
										?UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_START_EXPOSED", Util.newArrayListOfValues(new ListValue<>(owner), new ListValue<>(pet)))
										:UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_START_CLOTHING", Util.newArrayListOfValues(new ListValue<>(owner), new ListValue<>(pet))))
								) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), 10f));
								
								Main.game.getPlayer().displaceClothingForAccess(CoverableArea.ANUS);
								if((Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
									Main.game.getPlayer().displaceClothingForAccess(CoverableArea.VAGINA);
								}
							}
						};
					
					} else {
						// Player performing oral:
						return new ResponseSex("Perform oral",
								"Do as Amber commands and get ready to suck [npc.name]'s [npc.cock+].",
								null, null, null, null, null, null,
								true, false,
								new SMPetOral(
										Util.newHashMapOfValues(new Value<>(pet, SexPositionSlot.PET_ORAL_COCKED_LEG)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.PET_ORAL_ON_ALL_FOURS))),
								WALKIES_PET_FUCKS_POST_SEX_ORAL,
								UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_START_ORAL", Util.newArrayListOfValues(new ListValue<>(owner), new ListValue<>(pet)))) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getAmber().incrementAffection(Main.game.getPlayer(), 10f));
							}
						};
					}
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld WALKIES_PET_FUCKS_POST_SEX = new DialogueNodeOld("Used", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_POST_SEX", Util.newArrayListOfValues(new ListValue<>(owner), new ListValue<>(pet)));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Home", "Crawl alongside Amber as she leads you back to Zaranix's Home.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
						Main.game.banishNPC(owner);
						owner.removeSlave(pet); //To stop issues with banisNPC() TODO fix this
						Main.game.banishNPC(pet);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WALKIES_PET_FUCKS_POST_SEX_ORAL = new DialogueNodeOld("Used", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_POST_SEX_ORAL", Util.newArrayListOfValues(new ListValue<>(owner), new ListValue<>(pet)));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Home", "Crawl alongside Amber as she leads you back to Zaranix's Home.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
						Main.game.banishNPC(owner);
						owner.removeSlave(pet); //To stop issues with banisNPC() TODO fix this
						Main.game.banishNPC(pet);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WALKIES_PET_FUCKS_REFUSE = new DialogueNodeOld("Demon Home", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_REFUSE", Util.newArrayListOfValues(new ListValue<>(owner), new ListValue<>(pet)));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Home", "Crawl alongside Amber as she leads you back to Zaranix's Home.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getAmber().returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
						Main.game.banishNPC(owner);
						Main.game.banishNPC(pet);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WALKIES_HOME = new DialogueNodeOld("", "", false) {
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

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_HOME"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	
	
}
