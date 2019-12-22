package com.lilithsthrone.game.dialogue.places.submission.gamblingDen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.Axel;
import com.lilithsthrone.game.character.npc.submission.GamblingDenPatron;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.npc.submission.Vengar;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePoker;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.submission.SMAxel;
import com.lilithsthrone.game.sex.managers.submission.SMVengarDominantSex;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
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

	public static final DialogueNode ENTRANCE = new DialogueNode("Entrance", "", false) { //TODO
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced)
					|| Main.game.getPlayer().getQuest(QuestLine.SIDE_VENGAR)==Quest.VENGAR_THREE_END;
		}
		
		@Override
		public String getContent() {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_VENGAR)==Quest.VENGAR_THREE_END) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "ENTRANCE_END_VENGAR_QUEST");
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced)) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "ENTRANCE_REPEAT");
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "ENTRANCE");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if(Main.game.getPlayer().getQuest(QuestLine.SIDE_VENGAR)==Quest.VENGAR_THREE_END) {
				if(index==1) {
					return new Response("Continue", "[axel.Name] lets out a deep sigh and turns back towards you.", AXEL_VENGAR_RESOLUTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.SIDE_UTIL_COMPLETE));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(50_000));
						}
					};
				}
					
			} else {
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
					return new Response("[axel.name]", "Walk over to [axel.name] and say hello.", AXEL){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelIntroduced, true);
						}
					};
				
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_RESOLUTION = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_RESOLUTION", Main.game.getPlayer().getMainCompanion());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("[axel.name]'s Office", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode OFFICE_WITH_AXEL = new DialogueNode("[axel.name]'s Office", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_OFFICE);
			Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_OFFICE);
			for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
				companion.setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
			}
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelSissified)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelFeminised)) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA");
			}
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_AXEL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelSissified)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelFeminised)) {
				boolean attracted = Main.game.getNpc(Axel.class).isAttractedTo(Main.game.getPlayer());
				if(index==1) {
					if(!attracted) {
						return new Response("Dominate", "[axel.name] is not willing to have sex with you, as [axel.she] is only attracted to masculine people.", null);
					}
					return new ResponseSex(
							"Dominate",
							"Tell [axel.name] that you're going to dominate [axel.herHim]...",
							true,
							true,
							new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.STANDING_SUBMISSIVE))) {
							},
							null,
							null,
							AFTER_LEXA_SEX,
							UtilText.parseFromXMLFile("places/submission/submissionPlaces", "START_LEXA_DOMINATE"));
					
					
				} else if(index==2) {
					if(!attracted) {
						return new Response("Submit", "[axel.name] is not willing to have sex with you, as [axel.she] is only attracted to masculine people.", null);
					}
					return new ResponseSex(
							"Submit",
							"Tell [axel.name] that [axel.sheIs] going to do [axel.her] best at dominating you...",
							true,
							true,
							new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))) {
							},
							null,
							null,
							AFTER_LEXA_SEX,
							UtilText.parseFromXMLFile("places/submission/submissionPlaces", "START_LEXA_SUBMIT"));
					
					
				} else if(index==3) {
					// Cage on/off
					if(Main.game.getNpc(Axel.class).getClothingInSlot(InventorySlot.PENIS)!=null) {
						return new Response("Uncage",
								"Take off [axel.namePos] chastity cage."
										+ "<br/>[style.italicsMinorGood(You can put [axel.namePos] cage back on at any time.)]",
								OFFICE_WITH_LEXA_CAGE) {
							@Override
							public void effects() {
								((Axel)Main.game.getNpc(Axel.class)).applyCage(false);
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CAGE_OFF"));
							}
						};
						
					} else {
						return new Response("Cage",
								"Get [axel.name] to put [axel.her] chastity cage back on."
										+ "<br/>[style.italicsMinorGood(You can take [axel.namePos] cage off at any time.)]",
								OFFICE_WITH_LEXA_CAGE) {
							@Override
							public void effects() {
								((Axel)Main.game.getNpc(Axel.class)).applyCage(true);
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CAGE_ON"));
							}
						};
					}
					
				} else if(index==6) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelFeminised)) {
						return new Response("Clothing",
								"Tell [axel.name] that [axel.she] should change the sort of outfit [axel.she] chooses to wear..",
								OFFICE_WITH_LEXA_CLOTHING);
					}
					return new Response("Feminise",
							"Tell [axel.name] that you're going to help [axel.herHim] realise [axel.her] full potential and fully feminise [axel.herHim]."
									+ "<br/>[style.italicsArcane(This is a permanent, non-reversible transformation!)]",
							OFFICE_WITH_LEXA_FEMINISE);
					
				} else if(index==7 && !Main.game.getNpc(Axel.class).getSexualOrientation().isAttractedToFeminine()) {
					return new Response("Hypno-watch",
							"Use your hypno-watch to make [axel.name] ambiphilic."
									+ "<br/>[style.italicsArcane(This is a permanent, non-reversible change!)]",
							OFFICE_WITH_LEXA_HYPNO) {
						@Override
						public void effects() {
							Main.game.getNpc(Axel.class).setSexualOrientation(SexualOrientation.AMBIPHILIC);
						}
					};
					
				} else if(index==0) {
					return new Response("Leave", "Decide against doing anything with [axel.name] and head back into the gambling den.", ENTRANCE) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
							Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_LEAVE"));
						}
					};
					
				}
				
			} else {
				if(index==1) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Give blowjob", "You are unable to gain access to your mouth, so cannot suck [axel.namePos] cock...", null);
					}
					return new ResponseSex(
							"Give blowjob",
							"Get down on your knees and prepare to suck [axel.namePos] cock...",
							true,
							false,
							new SMAxel(
									SexPosition.STANDING,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
									Util.newArrayListOfValues(CoverableArea.PENIS),
									Util.newArrayListOfValues(CoverableArea.MOUTH),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))),
							null,
							null,
							AFTER_AXEL_ORAL,
							UtilText.parseFromXMLFile("places/submission/submissionPlaces", "START_AXEL_ORAL_PERFORMING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
							return list;
						}
					};
					
				} else if(index==2) {
					if((!Main.game.getPlayer().hasPenisIgnoreDildo() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true))
							&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
						return new Response("Receive oral", "You do not have any genitalia which [axel.name] can access in order to perform oral on you...", null);
					}
					return new ResponseSex(
							"Receive oral",
							"Get [axel.name] to perform oral on you...",
							true,
							false,
							new SMAxel(
									SexPosition.STANDING,
									Main.game.getPlayer().hasPenisIgnoreDildo()
										?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)
										:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
									Util.newArrayListOfValues(CoverableArea.MOUTH),
									Main.game.getPlayer().hasPenisIgnoreDildo()
										?Util.newArrayListOfValues(CoverableArea.PENIS)
										:Util.newArrayListOfValues(CoverableArea.VAGINA),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))),
							null,
							null,
							AFTER_AXEL_ORAL,
							UtilText.parseFromXMLFile("places/submission/submissionPlaces", "START_AXEL_ORAL_RECEIVING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
							return list;
						}
					};
					
				} else if(index==3 && Main.game.isAnalContentEnabled()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Perform anilingus", "You are unable to gain access to your mouth, so cannot perform anilingus on [axel.name]...", null);
					}
					return new ResponseSex(
							"Perform anilingus",
							"Get [axel.name] to bend over his desk so that you can perform anilingus on him...",
							true,
							false,
							new SMAxel(
									SexPosition.OVER_DESK,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
									Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS),
									Util.newArrayListOfValues(CoverableArea.MOUTH),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotDesk.OVER_DESK_ON_FRONT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.PERFORMING_ORAL))),
							null,
							null,
							AFTER_AXEL_ORAL,
							UtilText.parseFromXMLFile("places/submission/submissionPlaces", "START_AXEL_ANILINGUS_PERFORMING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), TongueAnus.RECEIVING_ANILINGUS_START, false, true));
							list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Axel.class), FingerPenis.COCK_MASTURBATING_START, false, true));
							return list;
						}
					};
					
				} else if(index==4 && Main.game.isAnalContentEnabled()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
						return new Response("Receive anilingus", "You cannot gain access to your asshole, so [axel.name] cannot perform anilingus on you...", null);
					}
					return new ResponseSex(
							"Receive anilingus",
							"Get [axel.name] to perform anilingus on you...",
							true,
							false,
							new SMAxel(
									SexPosition.AGAINST_WALL,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS),
									Util.newArrayListOfValues(CoverableArea.MOUTH),
									Main.game.getPlayer().hasPenis()
										?Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS)
										:(Main.game.getPlayer().hasVagina()
											?Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.VAGINA)
											:Util.newArrayListOfValues(CoverableArea.ANUS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotAgainstWall.PERFORMING_ORAL_WALL)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.FACE_TO_WALL))),
							null,
							null,
							AFTER_AXEL_ORAL,
							UtilText.parseFromXMLFile("places/submission/submissionPlaces", "START_AXEL_ANILINGUS_RECEIVING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), TongueAnus.ANILINGUS_START, false, true));
							if(Main.game.getPlayer().hasPenis()) {
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Axel.class), FingerPenis.COCK_MASTURBATED_START, false, true));
							} else if(Main.game.getPlayer().hasVagina()) {
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Axel.class), FingerVagina.FINGERED_START, false, true));
							}
							return list;
						}
					};
					
				} else if(index==0) {
					return new Response("Leave", "Decide against doing anything with [axel.name] and head back into the gambling den.", ENTRANCE) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
							Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_AXEL_LEAVE"));
						}
					};
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_AXEL_ORAL = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLust(50);
			Main.game.getNpc(Axel.class).setLust(50);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "AFTER_AXEL_ORAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> previousWetAreas = new HashMap<>(Sex.getAllWetAreas()); // Starting lube from foreplay
			
			if(index==1
					&& (Main.game.isAnalContentEnabled() || (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)))) {
				if(!Main.game.isAnalContentEnabled() && !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response(
							"Get fucked", "You cannot gain access to your pussy, so you cannot get fucked by [axel.name]...", null);
				}
				if(Main.game.isAnalContentEnabled() && !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
					return new Response(
							"Get fucked", "You cannot gain access to your asshole, so you cannot get fucked by [axel.name]...", null);
				}
				return new ResponseSex(
						"Get fucked",
						"Tell [axel.name] that [axel.she] can fuck you over [axel.her] desk...",
						true,
						false,
						new SMAxel(
								SexPosition.OVER_DESK,
								!Main.game.isAnalContentEnabled()
									?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)
									:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								!Main.game.isAnalContentEnabled()
									?Util.newArrayListOfValues(CoverableArea.VAGINA)
									:Util.newArrayListOfValues(CoverableArea.ANUS),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotDesk.BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								return previousWetAreas;
							}
						},
						null,
						null,
						AFTER_AXEL_SEX,
						UtilText.parseFromXMLFile("places/submission/submissionPlaces", "START_AXEL_SEX_DESK")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = new ArrayList<>();
						if(!Main.game.isAnalContentEnabled()) {
							list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
						} else {
							list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
						}
						return list;
					}
				};
				
			} else if(index==2
					&& (Main.game.isAnalContentEnabled() || (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)))) {
				if(!Main.game.isAnalContentEnabled() && !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response(
							"Ride him", "You cannot gain access to your pussy, so you cannot ride [axel.namePos] cock...", null);
				}
				if(Main.game.isAnalContentEnabled() && !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
					return new Response(
							"Ride him", "You cannot gain access to your asshole, so you cannot get fucked by [axel.name]...", null);
				}
				return new ResponseSex(
						"Ride him",
						"Tell [axel.name] to sit down so that you can ride [axel.her] cock...",
						true,
						false,
						new SMAxel(
								SexPosition.SITTING,
								!Main.game.isAnalContentEnabled()
									?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)
									:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								!Main.game.isAnalContentEnabled()
									?Util.newArrayListOfValues(CoverableArea.VAGINA)
									:Util.newArrayListOfValues(CoverableArea.ANUS),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								return previousWetAreas;
							}
						},
						null,
						null,
						AFTER_AXEL_SEX,
						UtilText.parseFromXMLFile("places/submission/submissionPlaces", "START_AXEL_SEX_CHAIR")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								List<InitialSexActionInformation> list = new ArrayList<>();
								if(!Main.game.isAnalContentEnabled()) {
									list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
								} else {
									list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
								}
								return list;
							}
						};
				
			} else if(index==3 && Main.game.isAnalContentEnabled()) { //TODO taur test
				if(!Main.game.getPlayer().hasPenis()) {
					return new Response("Ridden", "You do not have a cock that [axel.name] could ride...", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("Ridden", "You cannot gain access to your cock, so [axel.name] cannot ride it...", null);
				}
				return new ResponseSex(
						"Ridden",
						"Lie down on the rug and get [axel.name] to ride your cock...",
						true,
						false,
						new SMAxel(
								SexPosition.LYING_DOWN,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
								Util.newArrayListOfValues(CoverableArea.ANUS),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotLyingDown.COWGIRL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								return previousWetAreas;
							}
						},
						null,
						null,
						AFTER_AXEL_SEX,
						UtilText.parseFromXMLFile("places/submission/submissionPlaces", "START_AXEL_SEX_RIDDEN")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = new ArrayList<>();
						list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), PenisAnus.USING_PENIS_START, false, true));
						return list;
					}
				};
				
			} else if(index==0) {
				return new Response("Leave", "Decide against doing anything else with [axel.name] and head back into the gambling den.", ENTRANCE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
						Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AFTER_AXEL_ORAL_LEAVE"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_AXEL_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "AFTER_AXEL_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave [axel.namePos] office and head back into the gambling den.", ENTRANCE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
						Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AFTER_AXEL_SEX_LEAVE"));
					}
				};
			}
			return null;
		}
	};
	public static final DialogueNode AFTER_LEXA_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "AFTER_LEXA_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave [axel.namePos] office and head back into the gambling den.", ENTRANCE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
						Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AFTER_LEXA_SEX_LEAVE"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_CAGE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_WITH_AXEL.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_HYPNO = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "OFFICE_WITH_LEXA_HYPNO");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_WITH_AXEL.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_FEMINISE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "OFFICE_WITH_LEXA_FEMINISE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Drink (No supplement)",
						"Get [axel.name] to drink the potion and none of the supplement, feminising [axel.herHim] and allowing [axel.her] cock to shrink to a tiny little clit-dick.",
						OFFICE_WITH_LEXA_FEMINISE_APPLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "OFFICE_WITH_LEXA_FEMINISE_APPLY_ZERO"));
						((Axel)Main.game.getNpc(Axel.class)).applyFeminisation(PenisGirth.ONE_THIN, PenisSize.ONE_TINY, TesticleSize.ZERO_VESTIGIAL, CumProduction.ONE_TRICKLE);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelFeminised, true);
					}
				};
				
			} else if(index==2) {
				return new Response("Drink (Half supplement)",
						"Get [axel.name] to drink both the potion and half of the supplement, feminising [axel.herHim] while also keeping [axel.her] cock at an average size.",
						OFFICE_WITH_LEXA_FEMINISE_APPLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "OFFICE_WITH_LEXA_FEMINISE_APPLY_HALF"));
						((Axel)Main.game.getNpc(Axel.class)).applyFeminisation(PenisGirth.TWO_AVERAGE, PenisSize.TWO_AVERAGE, TesticleSize.TWO_AVERAGE, CumProduction.ONE_TRICKLE);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelFeminised, true);
					}
				};
				
			} else if(index==2) {
				return new Response("Drink (Full supplement)",
						"Get [axel.name] to drink both the potion and the supplement, feminising [axel.herHim] while also growing [axel.her] cock back to how it used to be.",
						OFFICE_WITH_LEXA_FEMINISE_APPLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "OFFICE_WITH_LEXA_FEMINISE_APPLY_FULL"));
						((Axel)Main.game.getNpc(Axel.class)).applyFeminisation(PenisGirth.FOUR_FAT, PenisSize.FOUR_HUGE, TesticleSize.FOUR_HUGE, CumProduction.FOUR_LARGE);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelFeminised, true);
					}
				};
				
			} else if(index==0) {
				return new Response("Back out",
						"Decide against feminising [axel.name] and decide upon something else to do in [axel.her] office.",
						OFFICE_WITH_LEXA_FEMINISE_BACK_OUT);
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_FEMINISE_BACK_OUT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "OFFICE_WITH_LEXA_FEMINISE_BACK_OUT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_WITH_AXEL.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_FEMINISE_APPLY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Feminine",
						"Tell [axel.name] to start wearing feminine dresses.",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END_FEMININE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingFeminine, true);
						Main.game.getNpc(Axel.class).equipClothing(null);
					}
				};
				
			} else if(index==2) {
				return new Response("Maid",
						"Tell [axel.name] to start wearing a maid dress.",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END_MAID"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingMaid, true);
						Main.game.getNpc(Axel.class).equipClothing(null);
					}
				};
				
			} else if(index==3) {
				return new Response("Whore",
						"Tell [axel.name] to start wearing extremely provocative clothing so that everyone knows [axel.sheIs] your slutty whore.",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END_WHORE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingWhore, true);
						Main.game.getNpc(Axel.class).equipClothing(null);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_WITH_AXEL.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_CLOTHING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelClothingFeminine)) {
					return new Response("Feminine", "[axel.Name] is already wearing a feminine dress.", null);
				}
				return new Response("Feminine",
						"Tell [axel.name] to wear a feminine dress.",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "OFFICE_WITH_LEXA_CLOTHING_FEMININE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingFeminine, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingMaid, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingWhore, false);
						Main.game.getNpc(Axel.class).equipClothing(null);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelClothingMaid)) {
					return new Response("Maid", "[axel.Name] is already wearing a maid uniform.", null);
				}
				return new Response("Maid",
						"Tell [axel.name] to start wearing a maid dress.",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "OFFICE_WITH_LEXA_CLOTHING_MAID"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingFeminine, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingMaid, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingWhore, false);
						Main.game.getNpc(Axel.class).equipClothing(null);
					}
				};
				
			} else if(index==3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelClothingWhore)) {
					return new Response("Whore", "[axel.Name] is already wearing a whorish outfit.", null);
				}
				return new Response("Whore",
						"Tell [axel.name] to start wearing extremely provocative clothing so that everyone knows [axel.sheIs] your slutty whore.",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "OFFICE_WITH_LEXA_CLOTHING_WHORE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingFeminine, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingMaid, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingWhore, true);
						Main.game.getNpc(Axel.class).equipClothing(null);
					}
				};
				
			} else if(index==0) {
				return new Response("Change mind",
						"Decide against a change of outfit for [axel.name] and decide upon something else to do in [axel.her] office.",
						OFFICE_WITH_LEXA_CLOTHING_BACK_OUT);
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_WITH_LEXA_CLOTHING_BACK_OUT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "OFFICE_WITH_LEXA_CLOTHING_BACK_OUT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_WITH_AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AXEL = new DialogueNode("Talking to [axel.name]", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Business", "Talk to [axel.name] about his business.", AXEL_BUSINESS);
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyIntroduced)) {
					return new Response("Roxy", "Ask [axel.name] about Roxy.", AXEL_ROXY) {
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
					return new Response("Roxy", "You'd need to talk with Roxy before asking [axel.name] about her.", null);
				}
				
			} else if(index==3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelMentionedVengar) && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_VENGAR)) { // Initial asking/quest start:
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelExplainedVengar)) {
						return new Response("Offer help", "Tell [axel.name] that you'd like to help [axel.herHim] deal with Vengar.", AXEL_VENGAR) {
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
						return new Response("Vengar", "Ask [axel.name] about Vengar.", AXEL_VENGAR) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR"));
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelExplainedVengar, true);
							}
						};
					}
					
				} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_VENGAR)
						&& Main.game.getPlayer().getQuest(QuestLine.SIDE_VENGAR)==Quest.VENGAR_TWO_COOPERATION) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelToldSubmit)) {
						return new Response("Visit Vengar", "Tell [axel.name] that you're ready to go with [axel.herHim] to visit Vengar now.", AXEL_VENGAR_VISIT) {
							@Override
							public Colour getHighlightColour() {
								return Colour.QUEST_SIDE;
							}
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
								Main.game.getNpc(Axel.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
								Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
								Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
							}
						};
						
					} else {
						return new Response("Vengar's demand", "Tell [axel.name] about Vengar's demand.", AXEL_VENGAR_SUBMIT) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelToldSubmit, true);
							}
						};
					}
					
				} else if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelSissified)
							|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelFeminised)) {
						return new Response("Office", "Tell Lexa that you want to have a private 'discussion' with [lexa.herHim] in [lexa.her] office.", OFFICE_WITH_AXEL);
						
					} else {
						if(Main.game.getPlayer().isFeminine()) {
							return new Response("Office", "You can tell that Axel isn't sexually interested in someone as feminine as you, and would therefore be unwilling to spend some time with you in his office.", null);
						}
						return new Response("Office", "Ask Axel if the two of you can have a private 'discussion' in his office about how thankful he is for your help.", OFFICE_WITH_AXEL) {
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
				}
				
			} else if(index==0) {
				return new Response("Back", "Say goodbye and walk back out into the entrance hall.", ENTRANCE);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_BUSINESS = new DialogueNode("Talking to [axel.name]", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_BUSINESS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Talk", "You're already talking to [axel.name].", null);
			}
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AXEL_ROXY = new DialogueNode("Talking to [axel.name]", "", true) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("Roxy", "You're already talking with [axel.name] about Roxy.", null);
			}
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AXEL_VENGAR = new DialogueNode("Talking to [axel.name]", "", true) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_SUBMIT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Visit Vengar", "Tell [axel.name] that you're ready to go with [axel.herHim] to visit Vengar now.", AXEL_VENGAR_VISIT) {
					@Override
					public Colour getHighlightColour() {
						return Colour.QUEST_SIDE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Axel.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
					}
				};
				
			} else if(index==2) {
				return new Response("Later", "Tell [axel.name] that you're not ready to go with [axel.herHim] to visit Vengar right now, and that you'll let [axel.herHim] know when you are.", AXEL_VENGAR);
				
			}
			return null;
		}
	};

	public static final DialogueNode AXEL_VENGAR_VISIT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Think about Shadow's warning as you follow [axel.name] and Silence to Vengar's hall.", AXEL_VENGAR_VISIT_KNEEL) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Axel.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						
						Main.game.getNpc(Axel.class).unequipAllClothingIntoVoid(true, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AXEL_VENGAR_VISIT_KNEEL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_KNEEL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Stop [axel.name]", "Stop [axel.name] from drinking the potion-laced rum.", AXEL_VENGAR_VISIT_STOPPED);
				
			} else if(index==2) {
				return new Response("Say nothing", "Say nothing to stop [axel.name] from drinking the potion-laced rum, allowing [axel.herHim] to be transformed into a sissified version of himself.", AXEL_VENGAR_VISIT_SISSIFIED) {
					@Override
					public void effects() {
						((Axel)Main.game.getNpc(Axel.class)).applySissification();
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelSissified, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AXEL_VENGAR_VISIT_STOPPED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_STOPPED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Head back to the gambling den with [axel.name].", AXEL_VENGAR_VISIT_RETURN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_STOPPED_RETURN"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_COOPERATION_END));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AXEL_VENGAR_VISIT_SISSIFIED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_SISSIFIED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Stop him", "Stop Vengar from going any further, and take [axel.name] back to the gambling den.", AXEL_VENGAR_VISIT_RETURN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_SISSIFIED_RETURN"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_COOPERATION_END));
					}
				};
				
			} else if(index==2) {
				return new Response("Watch", "Let Vengar take it a little further...", AXEL_VENGAR_VISIT_SISSIFIED_FINISH);
			}
			return null;
		}
	};

	public static final DialogueNode AXEL_VENGAR_VISIT_SISSIFIED_FINISH = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_SISSIFIED_FINISH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Enough", "Tell Vengar that enough's enough, and take [axel.name] back to the gambling den.", AXEL_VENGAR_VISIT_RETURN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_SISSIFIED_FINISH_STOPPED_RETURN"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_COOPERATION_END));
					}
				};
				
			} else if(index==2) {
				if(Main.game.isAnalContentEnabled()) {
					if(Main.game.getPlayer().isFeminine()) {
						return new Response("Fuck Lexa", "'Lexa' doesn't seem to be interested in having sex with someone as feminine as you...", null);
					}
					if(!Main.game.getPlayer().hasPenis()) {
						return new Response("Fuck Lexa", "As you don't have a penis, you can't fuck 'Lexa'...", null);
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						return new Response("Fuck Lexa", "As you can't get access to your penis, you can't fuck 'Lexa'...", null);
					}
					return new ResponseSex(
							"Fuck Lexa",
							"Do as Vengar suggests and fuck Lexa in front of everyone in the hall.",
							true,
							false,
							new SMAllFours(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Axel.class), SexSlotAllFours.ALL_FOURS))) {
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									return Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)));
								}
							},
							Util.newArrayListOfValues(
									Main.game.getNpc(Vengar.class),
									Main.game.getNpc(Shadow.class),
									Main.game.getNpc(Silence.class)),
							null,
							AFTER_AXEL_SISSIFIED_SEX,
							UtilText.parseFromXMLFile("places/submission/submissionPlaces", "AXEL_VENGAR_VISIT_SISSIFIED_FINISH_PLAYER_FUCK")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Axel.class), PenisAnus.PENIS_FUCKING_START, false, true));
						}
					};
					
				} else {
					if(Main.game.getPlayer().isFeminine()) {
						return new Response("Receive oral", "'Lexa' doesn't seem to be interested in performing oral on someone as feminine as you...", null);
					}
					if(!Main.game.getPlayer().hasPenis()) {
						if(Main.game.getPlayer().hasVagina()) {
							if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
								return new Response("Cunnilingus", "As you can't get access to your pussy, you can't receive cunnilingus from 'Lexa'...", null);
							}
							return new ResponseSex(
									"Cunnilingus",
									"Do as Vengar suggests and get Lexa to eat you out in front of everyone in the hall.",
									true,
									false,
									new SMStanding(
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.PERFORMING_ORAL))) {
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
										}
									},
									Util.newArrayListOfValues(
											Main.game.getNpc(Vengar.class),
											Main.game.getNpc(Shadow.class),
											Main.game.getNpc(Silence.class)),
									null,
									AFTER_AXEL_SISSIFIED_SEX,
									UtilText.parseFromXMLFile("places/submission/submissionPlaces", "AXEL_VENGAR_VISIT_SISSIFIED_FINISH_PLAYER_CUNNILINGUS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
								}
							};
						}
						return new Response("Get blowjob", "As you don't have a penis, you can't get a blowjob from 'Lexa'...", null);
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						return new Response("Get blowjob", "As you can't get access to your penis, you can't get a blowjob from 'Lexa'...", null);
					}
					return new ResponseSex(
							"Get blowjob",
							"Do as Vengar suggests and get Lexa to give you a blowjob in front of everyone in the hall.",
							true,
							false,
							new SMStanding(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.PERFORMING_ORAL))) {
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									return Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)));
								}
							},
							Util.newArrayListOfValues(
									Main.game.getNpc(Vengar.class),
									Main.game.getNpc(Shadow.class),
									Main.game.getNpc(Silence.class)),
							null,
							AFTER_AXEL_SISSIFIED_SEX,
							UtilText.parseFromXMLFile("places/submission/submissionPlaces", "AXEL_VENGAR_VISIT_SISSIFIED_FINISH_PLAYER_BLOWJOB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
						}
					};
					
				}
				
			} else if(index==3) {
				if(Main.game.isAnalContentEnabled()) {
					return new ResponseSex(
							"Watch Vengar",
							"Join everyone else in the hall in watching 'Lexa' get fucked by Vengar.",
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Axel.class), SexSlotAllFours.ALL_FOURS))),
							Util.newArrayListOfValues(
									Main.game.getPlayer(),
									Main.game.getNpc(Shadow.class),
									Main.game.getNpc(Silence.class)),
							null,
							AFTER_AXEL_SISSIFIED_SEX,
							UtilText.parseFromXMLFile("places/submission/submissionPlaces", "AXEL_VENGAR_VISIT_SISSIFIED_FINISH_VENGAR_FUCK")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getNpc(Axel.class), PenisAnus.PENIS_FUCKING_START, false, true));
						}
					};
					
				} else {
					return new ResponseSex(
							"Watch Vengar",
							"Join everyone else in the hall in watching 'Lexa' suck Vengar's cock.",
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.STANDING,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.PERFORMING_ORAL))),
							Util.newArrayListOfValues(
									Main.game.getPlayer(),
									Main.game.getNpc(Shadow.class),
									Main.game.getNpc(Silence.class)),
							null,
							AFTER_AXEL_SISSIFIED_SEX,
							UtilText.parseFromXMLFile("places/submission/submissionPlaces", "AXEL_VENGAR_VISIT_SISSIFIED_FINISH_VENGAR_BLOWJOB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getNpc(Axel.class), PenisMouth.BLOWJOB_START, false, true));
						}
					};
					
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_AXEL_SISSIFIED_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "AFTER_AXEL_SISSIFIED_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Escort Lexa back to the gambling den.", AXEL_VENGAR_VISIT_RETURN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AFTER_AXEL_SISSIFIED_SEX_RETURN"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_COOPERATION_END));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_VISIT_RETURN = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
			Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Reward",
						"Wait for Lexa to fetch your reward from [axel.her] office.",
						AXEL_VENGAR_VISIT_RETURN_FINISH) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.SIDE_UTIL_COMPLETE));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(10_000));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_VISIT_RETURN_FINISH = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "AXEL_VENGAR_VISIT_STOPPED_RETURN_FINISH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Claire",
						"Tell Lexa that you'll come and visit [lexa.herHim] another time, after you've done as [lexa.she]'s asked and informed Claire of what's happened.",
						REPORT_TO_CLAIRE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_GAMBLING_DEN);
						Main.game.getPlayer().setNearestLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_ENTRANCE, false);
						((Axel)Main.game.getNpc(Axel.class)).applyFeminisationCosmetics();
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.SIDE_UTIL_COMPLETE));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(50_000));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode REPORT_TO_CLAIRE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "REPORT_TO_CLAIRE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.SUBMISSION_ENTRANCE.getDialogue(false).getResponse(responseTab, index);
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
								Main.game.setContent(new Response("", "", DicePoker.initDicePoker(gambler, table, GAMBLING, "misc/dicePoker")));
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
