package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloorRepeat;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMZaranixCockSucking;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class ZaranixDialogue {

	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Victory", "", true) {
		@Override
		public String getDescription() {
			return "[zaranix.Name] is completely defeated, and with a weary groan, [zaranix.she] staggers back and surrenders to you...";
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_VICTORY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Zaranix's house.", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Use Zaranix", "Have some fun with this incubus.",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Zaranix.class)),
						null,
						null),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_VICTORY_USE_HIM"));
				
			} else if(index==3) {
				return new ResponseSex("Submit",
						"Allow Zaranix to fuck you.",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Zaranix.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_VICTORY_SUBMIT"));
				
			} else if (index == 4) {
				return new Response("Transformations",
						"Get Zaranix to use [zaranix.her] demonic powers to transform [zaranix.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getNpc(Zaranix.class));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Continue", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_SEX_VICTORY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your journey.", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
						ZaranixHomeGroundFloorRepeat.resetHouseAfterLeaving();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Defeated", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				if(index==1) {
					return new Response("Thrown out", "As he's unable to gain access to your mouth, Zaranix cannot make use of you and so simply throws out into the street.", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_NO_MOUTH"));
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
							ZaranixHomeGroundFloor.resetHouseAfterLeaving();
						}
					};
				}
				
			} else if(!Main.game.isNonConEnabled()) {
				if(index==1) {
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Refuse",
								"Due to your <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> fetish, you love being transformed so much that you can't bring yourself to refuse the transformative liquid!",
								null);
					} else {
						return new Response("Refuse", "Refuse to drink the transformative potion. This will result in Zaranix having you thrown out into the street.", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_REFUSE"));
								Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
								ZaranixHomeGroundFloor.resetHouseAfterLeaving();
							}
						};
					}
					
				} else if(index==2) {
					return new Response("Drink", "Agree to drink the transformative potion.", AFTER_COMBAT_DEFEAT_SWALLOW);
				}
				
			} else {
				if(index==1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					}
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Spit",
								"Due to your <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> fetish, you love being transformed so much that you can't bring yourself to spit out the transformative liquid!",
								null);
					} else {
						return new Response("Spit", "Spit out the potion.", AFTER_COMBAT_DEFEAT_SPIT);
					}
					
				} else if(index==2) {
					return new Response("Swallow", "Swallow the potion.", AFTER_COMBAT_DEFEAT_SWALLOW);
				}
			}
			
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT_SPIT = new DialogueNode("Defeated", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixTransformedPlayer, false);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SPIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Obey", "Zaranix forces you to orally service him.",
						false, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SPIT_SEX_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Eagerly obey", "Zaranix forces you to orally service him.",
						false, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_EAGER;
								}
								return null;
							}
						},
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SPIT_SEX_START_EAGER")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==3) {
				return new ResponseSex("Resist", "Zaranix forces you to orally service him.",
						false, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_RESISTING;
								}
								return null;
							}
						},
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SPIT_SEX_START_RESIST")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Zaranix.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT_SWALLOW = new DialogueNode("Defeated", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixTransformedPlayer, true);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SWALLOW");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Need... Cock...", "Zaranix's potion has had quite a strong effect... You really need to suck his cock, then maybe you'll be able to think clearly again?",
						true, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_EAGER;
								}
								return null;
							}
						},
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SWALLOW_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
			}
			if(!Main.game.getPlayer().getSexualOrientation().isAttractedToMasculine()) {
				if(index==2) {
					return new Response("Feminine form?",
							"You want to suck Zaranix's cock, but you aren't really attracted to masculine people. Maybe if you were to ask nicely, he could use his demonic powers to transform into a succubus for you?",
							AFTER_COMBAT_DEFEAT_SWALLOW_ZARANIX_TF);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT_SWALLOW_ZARANIX_TF = new DialogueNode("Used", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SWALLOW_ZARANIX_TF_START"));
			((Zaranix)Main.game.getNpc(Zaranix.class)).transformFeminine();
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SWALLOW_ZARANIX_TF_END"));
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Need... Cock...", "Having got what you wanted, all you can now think about is sucking Zaranix's cock...",
						true, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SWALLOW_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("Used", "Zaranix has had enough of experiencing your cock-sucking skills...", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_SEX_DEFEAT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Thrown out", "Zaranix has thrown you out into the street.", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
						ZaranixHomeGroundFloor.resetHouseAfterLeaving();
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
