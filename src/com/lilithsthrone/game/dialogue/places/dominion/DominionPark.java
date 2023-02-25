package com.lilithsthrone.game.dialogue.places.dominion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.dominion.SMDominionExpress;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4.7.1
 * @version 0.4.7.1
 * @author Innoxia
 */
public class DominionPark {
	
	public static final DialogueNode PARK = new DialogueNode("Park", ".", false) {
		@Override
		public String getAuthor() {
			return "Kumiko";
		}
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return "<p>"
						+ "This area of Dominion is taken up by a gigantic park, filled with a lush amount of foliage, which makes the air here feel very fresh compared to the rest of the city."
						+ " The park consists of several alternating areas of open lawn and woodland, connected by a series of winding paths."
						+ " There's a small lake situated in one corner of the park, and adjacent to that, there's a small field of wild flowers."
						+ " A couple of food stands have been set up in one area for people that didn't come prepared with a picnic."
					+ "</p>"
					+ "<p>"
						+ "The most noteworthy feature is at the very centre of the park, and takes the form of a huge statue of Lilith herself."
						+ " The sultry smile carved into the white marble almost feels at though it's mocking you, and you can't help but feel as though you don't want to stick around here for long."
					+ "</p>"
					+ "<p>"
						+ "For now, you don't have much reason to wander through this park, but if you had someone else with you, it would be a nice place to spend an afternoon; if you can ignore the statue, that is..."
					+ "</p>";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Rose Garden", "There's a beautiful rose garden just off to your right. Walk over to it and take a closer look.", PARK_ROSE_GARDEN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_hair_rose", false), false));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PARK_ROSE_GARDEN = new DialogueNode("Park", ".", false, true) {
		@Override
		public String getAuthor() {
			return "Innoxia";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return "<p>"
					+ "You find your attention drawn towards a small rose garden that's positioned near the park's entrance."
					+ " Walking over towards it, you see that someone's placed a little sign just in front of the border, which reads:"
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+ "<i>"
						+ "<b>William's Rose Garden</b><br/>"
						+ "Please feel free to help yourself to these roses!"
						+ " I hope you or your partner gets as much happiness out of them as I do from growing them.<br/>"
						+ "- William"
					+ "</i>"
				+ "</p>"
				+ "<p>"
					+ "You look around, but don't see anyone nearby who could be this 'William' character."
					+ " Focusing your attention back to his rose garden, you decide to do as his sign says, and after [pc.stepping] forwards, you pluck a single rose from the nearest bush."
				+ "</p>";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Rose Garden", "You've already taken a rose from the garden.", null);
			} else {
				return null;
			}
		}
	};
	
	
	// Natalya encounter:
//	#IF(pc.getClothingInSlot(IS_NECK)!=null && pc.getClothingInSlot(IS_NECK).getClothingType().getId().equals('innoxia_neck_filly_choker'))
	
	public static final DialogueNode NATALYA_ENCOUNTER_START = new DialogueNode("Suddenly Centaurs", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaParkEncounter, true);
			Main.game.getNpc(Natalya.class).setLocation(Main.game.getPlayer(), false);
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
				if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.PENIS, null);
				} else {
					Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.ANUS, null);
				}
				
			} else {
				Main.game.getNpc(Natalya.class).setPlayerKnowsName(false);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
				return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_FILLY_START");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_START");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
				Natalya natalya = (Natalya) Main.game.getNpc(Natalya.class);
				if(index==1) {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new ResponseSex(
								"Anilingus",
								UtilText.parse(natalya, "Drop down behind Natalya and give her a rimjob."),
								true,
								false,
								// This sex manager should be suitable
								new SMDominionExpress(SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(natalya, SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
										Util.newHashMapOfValues(new Value<>(natalya, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
										Util.newHashMapOfValues(
												new Value<>(natalya, Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								AFTER_FILLY_SEX_ANAL,
								UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_START_FILLY_ANILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), natalya, TongueAnus.ANILINGUS_START, false, true));
							}
						};
						
					} else {
						return new ResponseSex(
								"Handjob",
								UtilText.parse(natalya, "Drop down onto your knees beside Natalya and give her a handjob."),
								true,
								false,
								// This sex manager should be suitable
								new SMDominionExpress(SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(natalya, SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
										Util.newHashMapOfValues(new Value<>(natalya, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER))),
										Util.newHashMapOfValues(
												new Value<>(natalya, Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								AFTER_FILLY_SEX_HANDJOB,
								UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_START_FILLY_HANDJOB")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), natalya, FingerPenis.COCK_MASTURBATING_START, false, true));
							}
						};
					}
				}
				
			} else {
				if(index==2) {
					return new Response("Leave",
							"You don't really want to see any more of this, and so turn around and continue on your way."
								+ "<br/><i>You might encounter this character again somewhere else...</i>",
								NATALYA_ENCOUNTER_END_EARLY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_START_END_EARLY"));
						}
					};
					
				} else if(index==1) {
					if(!Main.game.isAnalContentEnabled()) {
						return new Response("Watch her",
								"You don't really want to see any more of this..."
										+ "<br/>[style.italicsMinorBad(This character's scenes involve anal content, and as such will be disabled for as long as your 'Anal Content' setting is turned off.)]",
								null);
					}
					return new Response("Watch her", "Watch the centauress as she grinds her asshole up against the edge of the cart...", NATALYA_ENCOUNTER_CAUGHT);
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode NATALYA_ENCOUNTER_CAUGHT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_CAUGHT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("Leave",
						"You don't think that you need to give this centauress an apology, so decide to simply turn around and continue on your way."
							+ "<br/><i>You might encounter Natalya again somewhere else...</i>",
							NATALYA_ENCOUNTER_END_EARLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_CAUGHT_END_EARLY"));
					}
				};
				
			} else if(index==1) {
				return new Response("Apologise", "Tell Natalya that you're sorry.", NATALYA_ENCOUNTER_APOLOGY);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode NATALYA_ENCOUNTER_APOLOGY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_APOLOGY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("Leave",
						"You've already given this centauress an apology and don't want to give another, so decide to simply turn around and continue on your way."
							+ "<br/><i>You might encounter Natalya again somewhere else...</i>",
						NATALYA_ENCOUNTER_END_EARLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_APOLOGY_END_EARLY"));
					}
				};
				
			} else if(index==1) {
				return new Response("Follow", "Do as Natalya says and follow her behind the bushes.<br/>[style.italicsSex(You get the feeling that you might see something quite lewd...)]", NATALYA_ENCOUNTER_BUSHES) {
					@Override
					public void effects() {
						Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.PENIS, null);
						Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.ANUS, null);
						Main.game.getNpc(Natalya.class).setPlayerKnowsName(true);
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode NATALYA_ENCOUNTER_BUSHES = new DialogueNode("Natalya's Demand", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("Leave", "Turn around and leave Natalya to get herself off without your help.", NATALYA_ENCOUNTER_END_EARLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES_END_EARLY"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
				
			} else if(index==1) {
				return new Response("Submit",
						"Do as the dominant [natalya.race] commands, and after calling her 'Mistress', kneel down behind her and shove the dildo into her puffy asshole...",
						NATALYA_ENCOUNTER_BUSHES_SUBMIT) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.playerSubmittedToNatalyaInPark, true); //TODO text variation in interview & Helena quest encounter
						((Natalya)Main.game.getNpc(Natalya.class)).insertDildo();
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode NATALYA_ENCOUNTER_BUSHES_SUBMIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Start stroking", "Do as Mistress Natalya commands and start stroking her thick horse-like cock.",
						true,
						false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))) {
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.ONGOING_ONLY;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isAbleToSkipSexScene() {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								return false;
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER);
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return getForeplayPreference(character, targetedCharacter);
								}
								return character.getMainSexPreference(targetedCharacter);
							}
							@Override
							public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
								Map<GameCharacter, List<SexAreaInterface>> map = new HashMap<>();
								map.put(Main.game.getPlayer(), Util.newArrayListOfValues(SexAreaOrifice.MOUTH, SexAreaOrifice.NIPPLE, SexAreaOrifice.BREAST));
								map.put(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(SexAreaOrifice.ANUS, SexAreaPenetration.FOOT));
								return map;
							}
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								return OrgasmBehaviour.PULL_OUT;
							}
							@Override
							public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
								if(!character.isPlayer()) {
									if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerReceivedNatalyaFacial)) {
										return OrgasmCumTarget.FACE;
									} else {
										return OrgasmCumTarget.FLOOR;
									}
								}
								return super.getCharacterPullOutOrgasmCumTarget(character, target);
							}
						},
						null,
						null,
						NATALYA_ENCOUNTER_BUSHES_SUBMIT_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES_SUBMIT_START_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), FingerPenis.COCK_MASTURBATING_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode NATALYA_ENCOUNTER_BUSHES_SUBMIT_POST_SEX = new DialogueNode("Finished", "Mistress Natalya has finished with you for now.", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES_SUBMIT_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Follow", "Follow Mistress Natalya to see if her centaurs have finished their work yet.", NATALYA_ENCOUNTER_END) {
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES_SUBMIT_POST_SEX_END"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode NATALYA_ENCOUNTER_END_EARLY = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Natalya.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return PARK.getContent();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PARK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode NATALYA_ENCOUNTER_END = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Natalya.class).returnToHome();
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.NATALYA_BUSINESS_CARD), false));
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
				return new Response("Continue", "Now that Mistress Natalya has left, all there is for you to do is continue on your way through the park...", PARK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_END_PARK"));
					}
				};
			}
			return null;
		}
	};
	
	// Filly:

	public static final DialogueNode AFTER_FILLY_SEX_ANAL = new DialogueNode("Finished", "Mistress Natalya has finished with you for now.", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "AFTER_FILLY_SEX_ANAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Follow", "Follow Mistress Natalya to see if her centaurs have finished their work yet.", NATALYA_ENCOUNTER_FILLY_END) {
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_FILLY_END"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_FILLY_SEX_HANDJOB = new DialogueNode("Finished", "Mistress Natalya has finished with you for now.", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "AFTER_FILLY_SEX_HANDJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return AFTER_FILLY_SEX_ANAL.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode NATALYA_ENCOUNTER_FILLY_END = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Natalya.class).returnToHome();
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
				return new Response("Continue", "Now that Mistress Natalya has left, all there is for you to do is continue on your way through the park...", PARK);
			}
			return null;
		}
	};
}
