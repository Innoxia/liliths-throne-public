package com.lilithsthrone.game.sex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.dominion.CultistSexActions;
import com.lilithsthrone.game.sex.sexActions.dominion.GloryHole;
import com.lilithsthrone.game.sex.sexActions.dominion.MasturbationPanties;
import com.lilithsthrone.game.sex.sexActions.dominion.ToiletStall;
import com.lilithsthrone.game.sex.sexActions.dominion.VickyDominating;
import com.lilithsthrone.game.sex.sexActions.submission.BreedingStallBack;
import com.lilithsthrone.game.sex.sexActions.submission.BreedingStallFront;
import com.lilithsthrone.game.sex.sexActions.universal.BackToWall;
import com.lilithsthrone.game.sex.sexActions.universal.ChairSex;
import com.lilithsthrone.game.sex.sexActions.universal.Cowgirl;
import com.lilithsthrone.game.sex.sexActions.universal.DoggyStyle;
import com.lilithsthrone.game.sex.sexActions.universal.FaceSitting;
import com.lilithsthrone.game.sex.sexActions.universal.FaceToWall;
import com.lilithsthrone.game.sex.sexActions.universal.HandHolding;
import com.lilithsthrone.game.sex.sexActions.universal.KneelingOral;
import com.lilithsthrone.game.sex.sexActions.universal.Masturbation;
import com.lilithsthrone.game.sex.sexActions.universal.MilkingStall;
import com.lilithsthrone.game.sex.sexActions.universal.Missionary;
import com.lilithsthrone.game.sex.sexActions.universal.PetMounting;
import com.lilithsthrone.game.sex.sexActions.universal.PetOral;
import com.lilithsthrone.game.sex.sexActions.universal.PixShower;
import com.lilithsthrone.game.sex.sexActions.universal.RalphOral;
import com.lilithsthrone.game.sex.sexActions.universal.SixtyNine;
import com.lilithsthrone.game.sex.sexActions.universal.Standing;
import com.lilithsthrone.game.sex.sexActions.universal.StocksSex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * Enum values that determine what actions are available for each slot.<br/><br/>
 * 
 * Each value holds a map, <i>slotTargets</i>, which maps SexPositionSlots to a map of SexPositionSlots, which in turn maps to positions available.
 *  By providing a character's position in sex, along with the position of the partner they're targeting, this map is used to fetch available actions.<br/><br/>
 *  
 *  <b>Example:</b><br/>
 *  <i>getSexInteractions(character1SexPositionSlot, character2SexPositionSlot)</i><br/>returns the <i>SexActionPresetPair</i> which holds all available actions.<br/><br/>
 *  
 *  If character1SexPositionSlot is SexPositionSlot.DOGGY_ON_ALL_FOURS, and character2SexPositionSlot is SexPositionSlot.DOGGY_BEHIND, then the returned actions would be those that
 *   are available for the character on all fours, in relation to a character kneeling behind them.
 * 
 * @since 0.1.97
 * @version 0.2.11
 * @author Innoxia
 */
public enum SexPositionType {

	STANDING("Standing",
			true,
			true,
			Util.newArrayListOfValues(Standing.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.STANDING_DOMINANT,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.STANDING_SUBMISSIVE,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas,
													SexActionPresets.kissing,
													SexActionPresets.mouthToBreasts,
													SexActionPresets.breastsToMouth),
											Util.newArrayListOfValues(
													OrgasmCumTarget.STOMACH,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.LEGS,
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.STANDING_SUBMISSIVE,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.STANDING_DOMINANT,
									new SexActionInteractions(
													SexActionPresets.appendagesToAllAreas,
													Util.newArrayListOfValues(
															OrgasmCumTarget.STOMACH,
															OrgasmCumTarget.GROIN,
															OrgasmCumTarget.LEGS,
															OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.STANDING_DOMINANT), Sex.getCharacterInPosition(SexPositionSlot.STANDING_SUBMISSIVE),
					"[npc.NameIsFull] standing face-to-face with [npc2.name].");
		}
	},
	
	STANDING_SIZE_DIFFERENCE("Standing",
			true,
			true,
			Util.newArrayListOfValues(Standing.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.STANDING_SD_TALLER,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.STANDING_SD_SMALLER,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.vaginaToMouth,
													SexActionPresets.penisToMouth,
													SexActionPresets.penisToBreasts),
												Util.newArrayListOfValues(
														OrgasmCumTarget.FACE,
														OrgasmCumTarget.BREASTS,
														OrgasmCumTarget.HAIR,
														OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.STANDING_SD_SMALLER,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.STANDING_SD_TALLER,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.fingerToLowerHalf,
													SexActionPresets.penisToFeet),
												Util.newArrayListOfValues(
														OrgasmCumTarget.SELF_STOMACH,
														OrgasmCumTarget.SELF_GROIN,
														OrgasmCumTarget.SELF_LEGS,
														OrgasmCumTarget.FLOOR,
														OrgasmCumTarget.FEET))))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.STANDING_SD_TALLER), Sex.getCharacterInPosition(SexPositionSlot.STANDING_SD_SMALLER),
					"[npc.NameIsFull] and [npc2.name] are standing in front of one another, but due to [npc2.namePos] smaller stature, [npc2.sheIs] facing [npc.namePos] crotch...");
		}
	},
	
	BACK_TO_WALL("Back-to-wall",
			true,
			true,
			Util.newArrayListOfValues(BackToWall.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.BACK_TO_WALL_AGAINST_WALL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.BACK_TO_WALL_FACING_TARGET,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas,
													SexActionPresets.groinToGroin,
													SexActionPresets.kissing,
													SexActionPresets.mouthToBreasts,
													SexActionPresets.breastsToMouth),
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.STOMACH,
													OrgasmCumTarget.LEGS))))),
					new Value<>(
							SexPositionSlot.BACK_TO_WALL_FACING_TARGET,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.BACK_TO_WALL_AGAINST_WALL,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas),
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.STOMACH,
													OrgasmCumTarget.LEGS))))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.BACK_TO_WALL_AGAINST_WALL), Sex.getCharacterInPosition(SexPositionSlot.BACK_TO_WALL_FACING_TARGET),
					"[npc2.NameIsFull] pinning [npc1.name] back against the wall, ready to step forwards and start having some fun...");
		}
	},
	
	FACING_WALL("Facing wall",
			true,
			true,
			Util.newArrayListOfValues(FaceToWall.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.FACE_TO_WALL_AGAINST_WALL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.FACE_TO_WALL_FACING_TARGET,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToAllAreas,
													SexActionPresets.tentacleToAllAreas,
													SexActionPresets.vaginaToPenis,
													SexActionPresets.assToPenis,
													SexActionPresets.kissing),
											Util.newArrayListOfValues(
													OrgasmCumTarget.WALL))))),
					new Value<>(
							SexPositionSlot.FACE_TO_WALL_FACING_TARGET,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.FACE_TO_WALL_AGAINST_WALL,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas),
											Util.newArrayListOfValues(
													OrgasmCumTarget.ASS,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.LEGS,
													OrgasmCumTarget.BACK,
													OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.FACE_TO_WALL_AGAINST_WALL), Sex.getCharacterInPosition(SexPositionSlot.FACE_TO_WALL_FACING_TARGET),
					"[npc2.NameIsFull] pinning [npc1.name] up against the wall, ready to step forwards and start having some fun...");
		}
	},
	
	COWGIRL("Cowgirl",
			true,
			true,
			Util.newArrayListOfValues(Cowgirl.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.COWGIRL_ON_BACK,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.COWGIRL_RIDING,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas,
													SexActionPresets.penisToVagina,
													SexActionPresets.penisToPenis,
													SexActionPresets.penisToAss,
													SexActionPresets.penisToThighs,
													SexActionPresets.kissing,
													SexActionPresets.mouthToBreasts,
													SexActionPresets.breastsToMouth),
											Util.newArrayListOfValues(
													OrgasmCumTarget.SELF_STOMACH,
													OrgasmCumTarget.SELF_BREASTS,
													OrgasmCumTarget.SELF_FACE,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.COWGIRL_RIDING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.COWGIRL_ON_BACK,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas,
													SexActionPresets.penisToBreasts),
											Util.newArrayListOfValues(
													OrgasmCumTarget.STOMACH,
													OrgasmCumTarget.BREASTS,
													OrgasmCumTarget.FACE,
													OrgasmCumTarget.HAIR))))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.COWGIRL_ON_BACK), Sex.getCharacterInPosition(SexPositionSlot.COWGIRL_RIDING),
					"[npc.NameIsFull] lying down on [npc.her] back as [npc2.name] [npc2.verb(straddle)] [npc.her] stomach in the cowgirl position.");
		}
	},
	
	FACE_SITTING("Face-sitting",
			true,
			true,
			Util.newArrayListOfValues(FaceSitting.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.FACE_SITTING_ON_FACE,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.FACE_SITTING_ON_BACK,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.fingerToUpperTorso,
													SexActionPresets.fingerToLowerHalf,
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.vaginaToMouth,
													SexActionPresets.penisToMouth,
													SexActionPresets.assToMouth), Util.newArrayListOfValues(
															OrgasmCumTarget.STOMACH,
															OrgasmCumTarget.BREASTS,
															OrgasmCumTarget.FACE,
															OrgasmCumTarget.HAIR))))),
					new Value<>(
							SexPositionSlot.FACE_SITTING_ON_BACK,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.FACE_SITTING_ON_FACE,
									new SexActionInteractions(
											Util.mergeMaps(
												SexActionPresets.fingerToLowerHalf,
												SexActionPresets.penisToFeet),
											Util.newArrayListOfValues(
														OrgasmCumTarget.SELF_GROIN,
														OrgasmCumTarget.SELF_BREASTS,
														OrgasmCumTarget.SELF_LEGS,
														OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.FACE_SITTING_ON_BACK), Sex.getCharacterInPosition(SexPositionSlot.FACE_SITTING_ON_FACE),
					"[npc.NameIs] lying on [npc.her] back as [npc2.name] [npc2.verb(sit)] on [npc.her] [npc.face+].");
		}
	},
	
	DOGGY_STYLE("Doggy-style",
			true,
			true,
			Util.newArrayListOfValues(DoggyStyle.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.DOGGY_ON_ALL_FOURS,
							Util.newHashMapOfValues(
								new Value<>(
										SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToAllAreas,
														SexActionPresets.tentacleToAllAreas,
														SexActionPresets.kissing),
												Util.newArrayListOfValues(
																OrgasmCumTarget.FLOOR))),
								new Value<>(
										SexPositionSlot.DOGGY_BEHIND,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToAllAreas,
														SexActionPresets.tentacleToAllAreas),
												Util.newArrayListOfValues(
														OrgasmCumTarget.FLOOR))),
								new Value<>(
										SexPositionSlot.DOGGY_BEHIND_ORAL,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToUpperTorso,
														SexActionPresets.tentacleToUpperTorso,
														SexActionPresets.vaginaToMouth,
														SexActionPresets.assToMouth,
														SexActionPresets.penisToMouth),
												Util.newArrayListOfValues(
														OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
							Util.newHashMapOfValues(
									new Value<>(
											SexPositionSlot.DOGGY_ON_ALL_FOURS,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToAllAreas,
															SexActionPresets.tentacleToAllAreas,
															SexActionPresets.kissing),
													Util.newArrayListOfValues(
															OrgasmCumTarget.FLOOR))),
									new Value<>(
											SexPositionSlot.DOGGY_ON_ALL_FOURS_THIRD,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToAllAreas,
															SexActionPresets.tentacleToAllAreas,
															SexActionPresets.kissing),
													Util.newArrayListOfValues(
															OrgasmCumTarget.FLOOR))),
									new Value<>(
											SexPositionSlot.DOGGY_BEHIND,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToAllAreas,
															SexActionPresets.tentacleToAllAreas),
													Util.newArrayListOfValues(
															OrgasmCumTarget.FLOOR))),
									new Value<>(
											SexPositionSlot.DOGGY_BEHIND_ORAL,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToUpperTorso,
															SexActionPresets.tentacleToUpperTorso,
															SexActionPresets.vaginaToMouth,
															SexActionPresets.assToMouth,
															SexActionPresets.penisToMouth),
													Util.newArrayListOfValues(
															OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_ON_ALL_FOURS_THIRD,
							Util.newHashMapOfValues(
									new Value<>(
											SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToAllAreas,
															SexActionPresets.tentacleToAllAreas,
															SexActionPresets.kissing),
													Util.newArrayListOfValues(
															OrgasmCumTarget.FLOOR))),
									new Value<>(
											SexPositionSlot.DOGGY_ON_ALL_FOURS_FOURTH,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToAllAreas,
															SexActionPresets.tentacleToAllAreas,
															SexActionPresets.kissing),
													Util.newArrayListOfValues(
															OrgasmCumTarget.FLOOR))),
									new Value<>(
											SexPositionSlot.DOGGY_BEHIND,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToAllAreas,
															SexActionPresets.tentacleToAllAreas),
													Util.newArrayListOfValues(
															OrgasmCumTarget.FLOOR))),
									new Value<>(
											SexPositionSlot.DOGGY_BEHIND_ORAL,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToUpperTorso,
															SexActionPresets.tentacleToUpperTorso,
															SexActionPresets.vaginaToMouth,
															SexActionPresets.assToMouth,
															SexActionPresets.penisToMouth),
													Util.newArrayListOfValues(
															OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_ON_ALL_FOURS_FOURTH,
							Util.newHashMapOfValues(
									new Value<>(
											SexPositionSlot.DOGGY_ON_ALL_FOURS_THIRD,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToAllAreas,
															SexActionPresets.tentacleToAllAreas,
															SexActionPresets.kissing),
													Util.newArrayListOfValues(
															OrgasmCumTarget.FLOOR))),
									new Value<>(
											SexPositionSlot.DOGGY_BEHIND,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToAllAreas,
															SexActionPresets.tentacleToAllAreas),
													Util.newArrayListOfValues(
															OrgasmCumTarget.FLOOR))),
									new Value<>(
											SexPositionSlot.DOGGY_BEHIND_ORAL,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToUpperTorso,
															SexActionPresets.tentacleToUpperTorso,
															SexActionPresets.vaginaToMouth,
															SexActionPresets.assToMouth,
															SexActionPresets.penisToMouth),
													Util.newArrayListOfValues(
															OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_BEHIND,
							Util.newHashMapOfValues(
								new Value<>(
									SexPositionSlot.DOGGY_INFRONT,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.fingerToUpperTorso,
														SexActionPresets.kissing),
												null)),
								new Value<>(
										SexPositionSlot.DOGGY_INFRONT_TWO,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.fingerToUpperTorso,
															SexActionPresets.kissing),
													null)),
								new Value<>(
										SexPositionSlot.DOGGY_ON_ALL_FOURS,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.appendagesToAllAreas,
														SexActionPresets.penisToAss,
														SexActionPresets.penisToVagina),
												Util.newArrayListOfValues(
														OrgasmCumTarget.BACK,
														OrgasmCumTarget.ASS,
														OrgasmCumTarget.GROIN,
														OrgasmCumTarget.LEGS,
														OrgasmCumTarget.FEET))))),
					new Value<>(
							SexPositionSlot.DOGGY_BEHIND_SECOND,
							Util.newHashMapOfValues(
									new Value<>(
											SexPositionSlot.DOGGY_INFRONT_SECOND,
												new SexActionInteractions(
														Util.mergeMaps(
																SexActionPresets.fingerToUpperTorso,
																SexActionPresets.kissing),
														null)),
										new Value<>(
												SexPositionSlot.DOGGY_INFRONT_SECOND_TWO,
													new SexActionInteractions(
															Util.mergeMaps(
																	SexActionPresets.fingerToUpperTorso,
																	SexActionPresets.kissing),
															null)),
								new Value<>(
										SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.appendagesToAllAreas,
														SexActionPresets.penisToAss,
														SexActionPresets.penisToVagina),
												Util.newArrayListOfValues(
														OrgasmCumTarget.BACK,
														OrgasmCumTarget.ASS,
														OrgasmCumTarget.GROIN,
														OrgasmCumTarget.LEGS,
														OrgasmCumTarget.FEET))))),
					new Value<>(
							SexPositionSlot.DOGGY_BEHIND_ORAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionInteractions(
											Util.mergeMaps(
												SexActionPresets.fingerToLowerHalf,
												SexActionPresets.penisToFeet),
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_BEHIND_ORAL_SECOND,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
									new SexActionInteractions(
											SexActionPresets.fingerToLowerHalf,
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_INFRONT,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_BEHIND,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.fingerToUpperTorso,
													SexActionPresets.kissing),
											null)),
							new Value<>(
									SexPositionSlot.DOGGY_INFRONT_TWO,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.fingerToUpperTorso,
													SexActionPresets.kissing),
											null)),
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.fingerToUpperTorso,
													SexActionPresets.vaginaToMouth,
													SexActionPresets.penisToMouth,
													SexActionPresets.lowerHalfToAppendages),
											Util.newArrayListOfValues(
															OrgasmCumTarget.FACE,
															OrgasmCumTarget.BREASTS,
															OrgasmCumTarget.HAIR,
															OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_INFRONT_TWO,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_BEHIND,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.fingerToUpperTorso,
													SexActionPresets.kissing),
											null)),
							new Value<>(
									SexPositionSlot.DOGGY_INFRONT,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.fingerToUpperTorso,
													SexActionPresets.kissing),
											null)),
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.fingerToUpperTorso,
													SexActionPresets.vaginaToMouth,
													SexActionPresets.penisToMouth,
													SexActionPresets.lowerHalfToAppendages),
											Util.newArrayListOfValues(
															OrgasmCumTarget.FACE,
															OrgasmCumTarget.BREASTS,
															OrgasmCumTarget.HAIR,
															OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_INFRONT_SECOND,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_BEHIND_SECOND,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.fingerToUpperTorso,
													SexActionPresets.kissing),
											null)),
							new Value<>(
									SexPositionSlot.DOGGY_INFRONT_SECOND_TWO,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.fingerToUpperTorso,
													SexActionPresets.kissing),
											null)),
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.fingerToUpperTorso,
													SexActionPresets.vaginaToMouth,
													SexActionPresets.penisToMouth,
													SexActionPresets.lowerHalfToAppendages),
											Util.newArrayListOfValues(
															OrgasmCumTarget.FACE,
															OrgasmCumTarget.BREASTS,
															OrgasmCumTarget.HAIR,
															OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_INFRONT_SECOND_TWO,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_BEHIND_SECOND,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.fingerToUpperTorso,
													SexActionPresets.kissing),
											null)),
							new Value<>(
									SexPositionSlot.DOGGY_INFRONT_SECOND,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.fingerToUpperTorso,
													SexActionPresets.kissing),
											null)),
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.fingerToUpperTorso,
													SexActionPresets.vaginaToMouth,
													SexActionPresets.penisToMouth,
													SexActionPresets.lowerHalfToAppendages),
											Util.newArrayListOfValues(
															OrgasmCumTarget.FACE,
															OrgasmCumTarget.BREASTS,
															OrgasmCumTarget.HAIR,
															OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_INFRONT_ANAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.assToMouth),
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_INFRONT_ANAL_TWO,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.assToMouth),
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_INFRONT_ANAL_SECOND,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.assToMouth),
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_INFRONT_ANAL_SECOND_TWO,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.assToMouth),
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_SD_HUMPING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas,
													SexActionPresets.penisToAss,
													SexActionPresets.penisToVagina),
											Util.newArrayListOfValues(
													OrgasmCumTarget.BACK,
													OrgasmCumTarget.ASS,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.LEGS,
													OrgasmCumTarget.FEET))))),
					new Value<>(
							SexPositionSlot.DOGGY_SD_HUMPING_SECOND,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas,
													SexActionPresets.penisToAss,
													SexActionPresets.penisToVagina),
											Util.newArrayListOfValues(
													OrgasmCumTarget.BACK,
													OrgasmCumTarget.ASS,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.LEGS,
													OrgasmCumTarget.FEET))))),
					new Value<>(
							SexPositionSlot.DOGGY_SD_UNDER,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.mouthToBreasts,
													SexActionPresets.fingerToUpperTorso),
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_SD_UNDER_SECOND,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.mouthToBreasts,
													SexActionPresets.fingerToUpperTorso),
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_FEET,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.penisToFeet),
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.DOGGY_FEET_SECOND,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.penisToFeet),
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR))))))) {
		@Override
		public SexActionInteractions getSexInteractions(SexPositionSlot performer, SexPositionSlot target) {

			// Check conflicts between humping & fucking, and Solo behind/in front:
			if(Main.game.isInSex()) {
				
				// If there are more than two doggy subs, then second behind can only interact with 3 and 4, and the first can only interact with 1 and 2:
				if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_THIRD)!=null
						&& Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND_SECOND)!=null) {
					if(performer==SexPositionSlot.DOGGY_BEHIND_SECOND) {
						if((target==SexPositionSlot.DOGGY_ON_ALL_FOURS_THIRD
								|| target==SexPositionSlot.DOGGY_ON_ALL_FOURS_FOURTH)) {
							return super.getSexInteractions(SexPositionSlot.DOGGY_BEHIND, SexPositionSlot.DOGGY_ON_ALL_FOURS);
							
						} else if(target==SexPositionSlot.DOGGY_BEHIND) {
							return super.getSexInteractions(SexPositionSlot.DOGGY_BEHIND, SexPositionSlot.DOGGY_BEHIND_SECOND);
							
						} else {
							return new SexActionInteractions(
									null,
									Util.newArrayListOfValues(OrgasmCumTarget.FLOOR));
						}
						
					} else if(performer==SexPositionSlot.DOGGY_BEHIND) {
						if((target==SexPositionSlot.DOGGY_ON_ALL_FOURS
								|| target==SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND)) {
							return super.getSexInteractions(SexPositionSlot.DOGGY_BEHIND, SexPositionSlot.DOGGY_ON_ALL_FOURS);
							
						} else if(target==SexPositionSlot.DOGGY_BEHIND_SECOND) {
							return super.getSexInteractions(SexPositionSlot.DOGGY_BEHIND, SexPositionSlot.DOGGY_BEHIND_SECOND);
							
						} else {
							return new SexActionInteractions(
									null,
									Util.newArrayListOfValues(OrgasmCumTarget.FLOOR));
						}
						
					} else if((performer==SexPositionSlot.DOGGY_ON_ALL_FOURS_THIRD || performer==SexPositionSlot.DOGGY_ON_ALL_FOURS_FOURTH)) {
						if(target==SexPositionSlot.DOGGY_BEHIND_SECOND) {
							return super.getSexInteractions(SexPositionSlot.DOGGY_ON_ALL_FOURS, SexPositionSlot.DOGGY_BEHIND);
						} else if(target==SexPositionSlot.DOGGY_BEHIND) {
							return new SexActionInteractions(
									null,
									Util.newArrayListOfValues(OrgasmCumTarget.FLOOR));
						} else {
							return super.getSexInteractions(performer, target);
						}
						
					} else if((performer==SexPositionSlot.DOGGY_ON_ALL_FOURS || performer==SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND)) {
						if(target==SexPositionSlot.DOGGY_BEHIND) {
							return super.getSexInteractions(SexPositionSlot.DOGGY_ON_ALL_FOURS, SexPositionSlot.DOGGY_BEHIND);
						} else if(target==SexPositionSlot.DOGGY_BEHIND_SECOND) {
							return new SexActionInteractions(
									null,
									Util.newArrayListOfValues(OrgasmCumTarget.FLOOR));
						} else {
							return super.getSexInteractions(performer, target);
						}
					}
				}
				
				// If there is nobody behind the second doggy target, allow all actions on second, third, and fourth doggy targets.
				if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND_SECOND)==null
						&& performer==SexPositionSlot.DOGGY_BEHIND) {
					if(target==SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND) {
						// If there is no humper, allow all actions, but if there is, restrict to prohibit anal:
						if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_SD_HUMPING_SECOND)==null) {
							return super.getSexInteractions(performer, SexPositionSlot.DOGGY_ON_ALL_FOURS);
							
						} else {
							return new SexActionInteractions(
									Util.mergeMaps(
											SexActionPresets.appendagesToAllAreas,
											SexActionPresets.penisToVagina),
									Util.newArrayListOfValues(
											OrgasmCumTarget.GROIN,
											OrgasmCumTarget.LEGS,
											OrgasmCumTarget.FEET));
						}
						
					} else if(target==SexPositionSlot.DOGGY_ON_ALL_FOURS_THIRD) {
						return super.getSexInteractions(performer, SexPositionSlot.DOGGY_ON_ALL_FOURS);
						
					} else if(target==SexPositionSlot.DOGGY_ON_ALL_FOURS_FOURTH) {
						return super.getSexInteractions(performer, SexPositionSlot.DOGGY_ON_ALL_FOURS);
					}
				}
				
				// If there is nobody in front of the second doggy target, allow all oral actions on second doggy target.
				if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_SECOND)==null
						&& (performer==SexPositionSlot.DOGGY_INFRONT || performer==SexPositionSlot.DOGGY_INFRONT_TWO)
						&& target==SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND) {
					return super.getSexInteractions(performer, SexPositionSlot.DOGGY_ON_ALL_FOURS);
				}
				
				// If there is a humper on either doggy target, restrict the people standing behind to no anal actions:
				if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_SD_HUMPING)!=null
						&& performer==SexPositionSlot.DOGGY_BEHIND
						&& target==SexPositionSlot.DOGGY_ON_ALL_FOURS) {
					return new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.appendagesToAllAreas,
									SexActionPresets.penisToVagina),
							Util.newArrayListOfValues(
									OrgasmCumTarget.GROIN,
									OrgasmCumTarget.LEGS,
									OrgasmCumTarget.FEET));
				}
				if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_SD_HUMPING_SECOND)!=null
						&& performer==SexPositionSlot.DOGGY_BEHIND_SECOND
						&& target==SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND) {
					return new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.appendagesToAllAreas,
									SexActionPresets.penisToVagina),
							Util.newArrayListOfValues(
									OrgasmCumTarget.GROIN,
									OrgasmCumTarget.LEGS,
									OrgasmCumTarget.FEET));
				}
				
				// If there are people behind, restrict humpers to just anal:
				if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND)!=null
						&& performer==SexPositionSlot.DOGGY_SD_HUMPING
						&& target==SexPositionSlot.DOGGY_ON_ALL_FOURS) {
					return new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.appendagesToBreasts,
									SexActionPresets.penisToAss),
							Util.newArrayListOfValues(
									OrgasmCumTarget.BACK,
									OrgasmCumTarget.ASS));
				}
				if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND_SECOND)!=null
						&& performer==SexPositionSlot.DOGGY_SD_HUMPING_SECOND
						&& target==SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND) {
					return new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.appendagesToBreasts,
									SexActionPresets.penisToAss),
							Util.newArrayListOfValues(
									OrgasmCumTarget.BACK,
									OrgasmCumTarget.ASS));
				}
			}
			
			return super.getSexInteractions(performer, target);
		}
		
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			boolean twoDoggies = false;
			boolean playerSub = (Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS)!=null && Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS).isPlayer())
					|| (Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND)!=null && Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND).isPlayer());
			
			// Subs on all fours:
			if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_FOURTH)!=null) {
				twoDoggies = true;
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND),
						"[npc.Name], [npc2.name],"));
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_THIRD), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_FOURTH),
						" [npc.name], and [npc2.name] are down on all fours, side-by-side, submissively presenting "+(playerSub?"yourselves":"themselves")+" to get fucked in the doggy-style position."));
			
			} else if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_THIRD)!=null) {
				twoDoggies = true;
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND),
						"[npc.Name], [npc2.name], and"));
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_THIRD),
						" [npc.name] are down on all fours, side-by-side, submissively presenting "+(playerSub?"yourselves":"themselves")+" to get fucked in the doggy-style position."));
			
			} else if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND)!=null) {
				twoDoggies = true;
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND),
						"[npc.Name] and [npc2.name] are down on all fours, side-by-side, submissively presenting "+(playerSub?"yourselves":"themselves")+" to get fucked in the doggy-style position."));
			
			} else {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS),
						"[npc.NameIsFull] down on all fours, submissively presenting [npc.herself] as [npc.she] [npc.verb(prepare)] to get fucked in the doggy-style position."));
			}
			
			// Behind:
			if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND_SECOND)!=null) {
				if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_THIRD)!=null) {
						sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND),
								" [npc.NameIsFull] "+(SexPositionSlot.DOGGY_BEHIND.isStanding(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND))?"standing":"kneeling")));
						sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND),
								" close behind [npc.name] and [npc2.name], while"));
						sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND_SECOND),
								" [npc.nameIsFull] "+(SexPositionSlot.DOGGY_BEHIND_SECOND.isStanding(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND_SECOND))?"standing":"kneeling")));
						if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_FOURTH)!=null) {
							sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_THIRD), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_FOURTH),
									" just behind [npc.name] and [npc2.name]"));
						} else {
							sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_THIRD),
									" just behind [npc.name]"));
						}
						sb.append("; the two of "+(playerSub?"them":"you")+" ready to have some fun with the subs before "+(playerSub?"them":"you")+".");
					
				} else {
					sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS),
							" [npc.NameIsFull] "+(SexPositionSlot.DOGGY_BEHIND.isStanding(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND))?"standing":"kneeling")
							+" close behind [npc2.name], while"));
					sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND_SECOND), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND),
							" [npc.nameIsFull] "+(SexPositionSlot.DOGGY_BEHIND_SECOND.isStanding(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND_SECOND))?"standing":"kneeling")+" just behind [npc2.name]."));
				}
			
			} else if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND)!=null) {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND),
						" [npc.NameIsFull] "+(SexPositionSlot.DOGGY_BEHIND.isStanding(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND))?"standing":"kneeling")
							+" close behind, ready to have some fun with "+(twoDoggies?(playerSub?"the two of you":"the two subs before [npc.herHim]"):(playerSub?"you":"the sub before [npc.herHim]"))+"."));
			}
			
			// Humping:
			if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_SD_HUMPING)!=null) {

				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_SD_HUMPING), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS),
						" [npc.NameHasFull] climbed up on top of [npc2.namePos] [npc2.ass], and, with a firm grip on [npc2.her] [npc2.hips+], is ready to start humping [npc2.herHim]."));
				
				if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_SD_HUMPING_SECOND)!=null) {
					sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_SD_HUMPING_SECOND), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND),
							" In much the same way, [npc.nameHasFull] mounted [npc2.name], and [npc.is] preparing to start humping [npc2.herHim] as well."));
				}
				
			} else if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_SD_HUMPING_SECOND)!=null) {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_SD_HUMPING_SECOND), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND),
						" [npc.NameHasFull] climbed up on top of [npc2.namePos] [npc2.ass], and, with a firm grip on [npc2.her] [npc2.hips+], is ready to start humping [npc2.herHim]."));
			}
			
			// Oral:
			if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT)!=null) {
				if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_SECOND)!=null) {
					if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_TWO)!=null) {
							sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_TWO),
									" [npc.Name] and [npc2.name] are positioned in front of"));
							sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS),
									" [npc.name], while "));

							if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_SECOND_TWO)!=null) {
								sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_SECOND), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_SECOND_TWO),
										" [npc.name] and [npc2.name] are before "));
							} else {
								sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_SECOND), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_SECOND_TWO),
										" [npc.nameIsFull] before "));
								
							}
							sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND),
									" [npc.name]; each of them ready to receive some oral sex."));
							
					} else {
						sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS),
								" [npc.NameIsFull] "+(SexPositionSlot.DOGGY_INFRONT.isStanding(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT))?"standing":"kneeling")+" in front of [npc2.name], and "));
						sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_SECOND), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND),
								" [npc.nameIsFull] similarly "+(SexPositionSlot.DOGGY_INFRONT_SECOND.isStanding(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_SECOND))?"standing":"kneeling")
									+" in front of [npc2.name]; the two of "+(playerSub?"them":"you")+" ready to receive some oral sex."));
					}
					
				} else {
					if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_TWO)!=null) {
						sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_TWO),
								" [npc.Name] and [npc2.name] are positioned in front, ready to receive some oral sex."));
					} else {
						sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT),
								" [npc.NameIsFull] "+(SexPositionSlot.DOGGY_INFRONT.isStanding(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT))?"standing":"kneeling")+" in front, ready to receive some oral sex."));
					}
				}
			}
			
			// Oral anilingus:
			if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL)!=null) {
				if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL_SECOND)!=null) {
					if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL_TWO)!=null) {
							sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL_TWO),
									" [npc.Name] and [npc2.name] have turned around and are positioned in front of"));
							sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS),
									" [npc.name], while "));

							if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL_SECOND_TWO)!=null) {
								sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL_SECOND), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL_SECOND_TWO),
										" [npc.name] and [npc2.name] have taken similar positions before "));
							} else {
								sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL_SECOND), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL_SECOND_TWO),
										" [npc.nameIsFull] before "));
								
							}
							sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND),
									" [npc.name]; each of them ready to receive a rimjob."));
					} else {
						sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS),
								" [npc.NameHasFull] turned around and [npc.is] positioned "+(SexPositionSlot.DOGGY_INFRONT_ANAL.isStanding(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL))?"standing":"kneeling")
								+" in front of [npc2.name], and "));
						sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL_SECOND), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND),
								" [npc.nameIsFull] similarly "+(SexPositionSlot.DOGGY_INFRONT_ANAL_SECOND.isStanding(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL_SECOND))?"standing":"kneeling")
									+" in front of [npc2.name]; the two of "+(playerSub?"them":"you")+" ready to receive rimjobs."));
					}
					
				} else {
					if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL_TWO)!=null) {
						sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL_TWO),
								" [npc.Name] and [npc2.name] have turned around and are positioned in front, ready to receive rimjobs."));
					} else {
						sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL),
								" [npc.NameHasFull] turned around and [npc.is] "+(SexPositionSlot.DOGGY_INFRONT.isStanding(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT))?"standing":"kneeling")
									+" in front, presenting [npc.her] [npc.ass+] in anticipation of getting a rimjob."));
					}
				}
			}
			
			// Feet:
			if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_FEET)!=null) {
				if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_FEET_SECOND)!=null) {
					sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_FEET), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_FEET_SECOND),
							" [npc.Name] and [npc2.name] are positioned well behind, ready to use"));
					
					sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND),
							" [npc.namePos] and [npc2.namePos] feet, respectively."));
					
				} else {
					sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_FEET), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS),
							" [npc.Name] is positioned well behind, ready to use [npc2.namePos] [npc2.feet]."));
				}
			}
			
			// Under:
			if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_SD_UNDER)!=null) {
				if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_SD_UNDER_SECOND)!=null) {
					sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_SD_UNDER), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_SD_UNDER_SECOND),
							" [npc.Name] and [npc2.name] are kneeling beneath"));
					
					sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND),
							" [npc.name] and [npc2.name], ready to have some fun with their chests."));
					
				} else {
					sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_SD_UNDER), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS),
							" [npc.Name] is kneeling beneath [npc2.name], ready to have some fun with [npc2.her] [npc2.breasts]."));
				}
			}
			
			
			
			return sb.toString();
		}
		
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			if(Sex.getSexPositionSlot(performer)==SexPositionSlot.DOGGY_BEHIND_ORAL
					&& action.getActionType()==SexActionType.START_ONGOING
					&& action.getSexAreaInteractions().keySet().contains(SexAreaOrifice.MOUTH)
					&& action.getSexAreaInteractions().values().contains(SexAreaPenetration.PENIS)) {
				return true;
			}
			
			return super.isActionBlocked(performer, target, action);
		}

		@Override
		public int getMaximumSlots() {
			return 8;
		}
	},
	
	SIXTY_NINE("Sixty-nine",
			true,
			true,
			Util.newArrayListOfValues(SixtyNine.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.SIXTY_NINE_TOP,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.SIXTY_NINE_BOTTOM,
									new SexActionInteractions(
										Util.mergeMaps(
												SexActionPresets.fingerToLowerHalf,
												SexActionPresets.tailToUpperTorso,
												SexActionPresets.tentacleToUpperTorso,
												SexActionPresets.vaginaToMouth,
												SexActionPresets.assToMouth,
												SexActionPresets.penisToMouth,
												SexActionPresets.penisToBreasts),
										Util.newArrayListOfValues(
												OrgasmCumTarget.FACE,
												OrgasmCumTarget.BREASTS,
												OrgasmCumTarget.HAIR,
												OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.SIXTY_NINE_BOTTOM,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.SIXTY_NINE_TOP,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.fingerToLowerHalf,
											SexActionPresets.tailToUpperTorso,
											SexActionPresets.tentacleToUpperTorso,
											SexActionPresets.vaginaToMouth,
											SexActionPresets.assToMouth,
											SexActionPresets.penisToMouth,
											SexActionPresets.penisToBreasts),
										Util.newArrayListOfValues(
												OrgasmCumTarget.SELF_STOMACH,
												OrgasmCumTarget.SELF_BREASTS,
												OrgasmCumTarget.FACE,
												OrgasmCumTarget.BREASTS,
												OrgasmCumTarget.HAIR,
												OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.SIXTY_NINE_TOP), Sex.getCharacterInPosition(SexPositionSlot.SIXTY_NINE_BOTTOM),
					"[npc.NameIsFull] on all fours over the top of [npc2.name], with [npc.her] crotch being positioned over [npc2.namePos] [npc2.face+]."
							+ " [npc.NameIsFull] looking down at [npc2.namePos] groin, ready to perform oral even as [npc.she] [npc.verb(receive)] it.");
		}
	},
	
	KNEELING_ORAL("Kneeling",
			true,
			true,
			Util.newArrayListOfValues(KneelingOral.class),
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.KNEELING_RECEIVING_ORAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_PERFORMING_ORAL,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.tailToUpperTorso,
											SexActionPresets.tentacleToUpperTorso,
											SexActionPresets.vaginaToMouth,
											SexActionPresets.penisToMouth,
											SexActionPresets.penisToBreasts),
										Util.newArrayListOfValues(
												OrgasmCumTarget.FACE,
												OrgasmCumTarget.BREASTS,
												OrgasmCumTarget.HAIR,
												OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.KNEELING_RECEIVING_ORAL_TWO,
							Util.newHashMapOfValues(new Value<>(SexPositionSlot.KNEELING_PERFORMING_ORAL, new SexActionInteractions( null, null)))),
					new Value<>(
							SexPositionSlot.KNEELING_RECEIVING_ORAL_THREE,
							Util.newHashMapOfValues(new Value<>(SexPositionSlot.KNEELING_PERFORMING_ORAL, new SexActionInteractions( null, null)))),
					new Value<>(
							SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND,
							Util.newHashMapOfValues(new Value<>(SexPositionSlot.KNEELING_PERFORMING_ORAL, new SexActionInteractions( null, null)))),
					new Value<>(
							SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_TWO,
							Util.newHashMapOfValues(new Value<>(SexPositionSlot.KNEELING_PERFORMING_ORAL, new SexActionInteractions( null, null)))),
					new Value<>(
							SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_THREE,
							Util.newHashMapOfValues(new Value<>(SexPositionSlot.KNEELING_PERFORMING_ORAL, new SexActionInteractions( null, null)))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_RECEIVING_ORAL,
									new SexActionInteractions(
											Util.mergeMaps(
												SexActionPresets.fingerToLowerHalf,
												SexActionPresets.penisToFeet),
											Util.newArrayListOfValues(
													OrgasmCumTarget.SELF_STOMACH,
													OrgasmCumTarget.SELF_GROIN,
													OrgasmCumTarget.SELF_LEGS,
													OrgasmCumTarget.FLOOR,
													OrgasmCumTarget.FEET))))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL_TWO,
							Util.newHashMapOfValues(new Value<>(SexPositionSlot.KNEELING_RECEIVING_ORAL, new SexActionInteractions( null, null)))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL_THREE,
							Util.newHashMapOfValues(new Value<>(SexPositionSlot.KNEELING_RECEIVING_ORAL, new SexActionInteractions( null, null)))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND,
							Util.newHashMapOfValues(new Value<>(SexPositionSlot.KNEELING_RECEIVING_ORAL, new SexActionInteractions( null, null)))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_TWO,
							Util.newHashMapOfValues(new Value<>(SexPositionSlot.KNEELING_RECEIVING_ORAL, new SexActionInteractions( null, null)))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_THREE,
							Util.newHashMapOfValues(new Value<>(SexPositionSlot.KNEELING_RECEIVING_ORAL, new SexActionInteractions( null, null)))))) {
		@Override
		public String getDescription() {
			StringBuilder descriptionSB = new StringBuilder();
			
			if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_TWO)==null && Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND)==null) { // Up to 6 subs on 1 dom
				
				List<String> performers = new ArrayList<>();
				boolean playerSub = !Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL).isPlayer();
				performers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL), "[npc.Name]"));
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_TWO)!=null) {
					performers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_TWO), "[npc.name]"));
				}
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_THREE)!=null) {
					performers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_THREE), "[npc.name]"));
				}
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND)!=null) {
					performers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND), "[npc.name]"));
				}
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_TWO)!=null) {
					performers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_TWO), "[npc.name]"));
				}
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_THREE)!=null) {
					performers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_THREE), "[npc.name]"));
				}
				
				descriptionSB.append(Util.stringsToStringList(performers, false));
				if(performers.size()>2) {
					descriptionSB.append(" are all");
				} else {
					descriptionSB.append(" are");
				}
				descriptionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL),
						" kneeling on the floor in front of [npc.name]; "+(playerSub?"your":"their")+" faces just [unit.sizes] away from [npc.her] groin..."));
				
			} else if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_TWO)==null && Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND)==null) { // Up to 6 doms on 1 sub
				
				List<String> receivers = new ArrayList<>();
				receivers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL), "[npc.Name]"));
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_TWO)!=null) {
					receivers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_TWO), "[npc.name]"));
				}
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_THREE)!=null) {
					receivers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_THREE), "[npc.name]"));
				}
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND)!=null) {
					receivers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND), "[npc.name]"));
				}
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_TWO)!=null) {
					receivers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_TWO), "[npc.name]"));
				}
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_THREE)!=null) {
					receivers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_THREE), "[npc.name]"));
				}
				
				descriptionSB.append(Util.stringsToStringList(receivers, false));
				if(receivers.size()>2) {
					descriptionSB.append(" are surrounding");
				} else {
					descriptionSB.append(" are standing to either side of");
				}
				descriptionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL),
						" [npc.name] as [npc.she] [npc.verb(kneel)] on the floor before them; their groins just [unit.sizes] away from [npc.her] face..."));
				
			} else {
				
				List<String> performers = new ArrayList<>();
				performers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL), "[npc.Name]"));
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_TWO)!=null) {
					performers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_TWO), "[npc.name]"));
				}
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_THREE)!=null) {
					performers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_THREE), "[npc.name]"));
				}
				
				descriptionSB.append(Util.stringsToStringList(performers, false));
				if(performers.size()>2) {
					descriptionSB.append(" are all");
				} else if(performers.size()>1) {
					descriptionSB.append(" are");
				} else if(performers.size()>1) {
					descriptionSB.append(" is");
				}
				descriptionSB.append(" kneeling on the floor in front of ");
				
				List<String> receivers = new ArrayList<>();
				receivers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL), "[npc.Name]"));
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_TWO)!=null) {
					receivers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_TWO), "[npc.name]"));
				}
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_THREE)!=null) {
					receivers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_THREE), "[npc.name]"));
				}
				
				descriptionSB.append(Util.stringsToStringList(receivers, false));
				if(receivers.size()>1) {
					descriptionSB.append(", who are positioning their groins just [unit.sizes] away from");
					if(performers.size()>1) {
						descriptionSB.append(" the faces before them.");
					} else {
						descriptionSB.append(" the face before them.");
					}
				} else {
					if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL).isPlayer()) {
						descriptionSB.append(", and you're positioning your groin just [unit.sizes] away from");
					} else {
						descriptionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL), ", who is positioning [npc.her] groin just [unit.sizes] away from"));
					}
					if(performers.size()>1) {
						descriptionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL), " the faces before [npc.herHim]."));
					} else {
						descriptionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL), " the face before [npc.herHim]."));
					}
				}
				
				descriptionSB.append("</br>"
						+ "Just to the side of this, ");
				
				performers = new ArrayList<>();
				performers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND), "[npc.name]"));
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_TWO)!=null) {
					performers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_TWO), "[npc.name]"));
				}
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_THREE)!=null) {
					performers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_THREE), "[npc.name]"));
				}
				
				descriptionSB.append(Util.stringsToStringList(performers, false));
				if(performers.size()>2) {
					descriptionSB.append(" are all");
				} else if(performers.size()>1) {
					descriptionSB.append(" are");
				} else if(performers.size()>1) {
					descriptionSB.append(" is");
				}
				descriptionSB.append(" similarly kneeling on the floor in front of ");
				
				receivers = new ArrayList<>();
				receivers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND), "[npc.Name]"));
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_TWO)!=null) {
					receivers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_TWO), "[npc.name]"));
				}
				if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_THREE)!=null) {
					receivers.add(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_THREE), "[npc.name]"));
				}
				
				descriptionSB.append(Util.stringsToStringList(receivers, false));
				if(receivers.size()>1) {
					descriptionSB.append(", who are preparing to receive oral");
					if(performers.size()>1) {
						descriptionSB.append(" from those before them.");
					} else {
						descriptionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND)," from the [npc.race] before them."));
					}
					
				} else {
					if(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND).isPlayer()) {
						descriptionSB.append(", and you're preparing to receive oral");
					} else {
						descriptionSB.append(", who is preparing to receive oral");
					}
					if(performers.size()>1) {
						descriptionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND), " from those before [npc.herHim]."));
					} else {
						descriptionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND), Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND),
								" from the [npc.race] before [npc2.herHim]."));
					}
				}
			}
			
			return descriptionSB.toString();
		}

		@Override
		public SexActionInteractions getSexInteractions(SexPositionSlot performer, SexPositionSlot target) {
			if(Main.game.isInSex()) {
				if((Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_TWO)==null && Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND)==null)
						|| (Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_TWO)==null && Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND)==null)) { // Up to 6 subs on 1 dom or 6 doms on 1 sub
					if(((performer==SexPositionSlot.KNEELING_PERFORMING_ORAL && target!=SexPositionSlot.KNEELING_RECEIVING_ORAL)
							|| performer==SexPositionSlot.KNEELING_PERFORMING_ORAL_TWO
							|| performer==SexPositionSlot.KNEELING_PERFORMING_ORAL_THREE
							|| performer==SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND
							|| performer==SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_TWO
							|| performer==SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_THREE)
							&& (target!=SexPositionSlot.KNEELING_PERFORMING_ORAL
									&& target!=SexPositionSlot.KNEELING_PERFORMING_ORAL_TWO
									&& target!=SexPositionSlot.KNEELING_PERFORMING_ORAL_THREE
									&& target!=SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND
									&& target!=SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_TWO
									&& target!=SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_THREE)) {
						return getSexInteractions(SexPositionSlot.KNEELING_PERFORMING_ORAL, SexPositionSlot.KNEELING_RECEIVING_ORAL);
					}
					if(((performer==SexPositionSlot.KNEELING_RECEIVING_ORAL && target!=SexPositionSlot.KNEELING_PERFORMING_ORAL)
							|| performer==SexPositionSlot.KNEELING_RECEIVING_ORAL_TWO
							|| performer==SexPositionSlot.KNEELING_RECEIVING_ORAL_THREE
							|| performer==SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND
							|| performer==SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_TWO
							|| performer==SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_THREE)
							&& (target!=SexPositionSlot.KNEELING_RECEIVING_ORAL
									&& target!=SexPositionSlot.KNEELING_RECEIVING_ORAL_TWO
									&& target!=SexPositionSlot.KNEELING_RECEIVING_ORAL_THREE
									&& target!=SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND
									&& target!=SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_TWO
									&& target!=SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_THREE)) {
						return getSexInteractions(SexPositionSlot.KNEELING_RECEIVING_ORAL, SexPositionSlot.KNEELING_PERFORMING_ORAL);
					}
					
				} else {
					if((performer==SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND
							|| performer==SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_TWO
							|| performer==SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_THREE)
							&& (target==SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND
									|| target==SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_TWO
									|| target==SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_THREE)) {
						return getSexInteractions(SexPositionSlot.KNEELING_PERFORMING_ORAL, SexPositionSlot.KNEELING_RECEIVING_ORAL);
					}
					if(((performer==SexPositionSlot.KNEELING_PERFORMING_ORAL && target!=SexPositionSlot.KNEELING_RECEIVING_ORAL)
							|| performer==SexPositionSlot.KNEELING_PERFORMING_ORAL_TWO
							|| performer==SexPositionSlot.KNEELING_PERFORMING_ORAL_THREE)
							&& (target==SexPositionSlot.KNEELING_RECEIVING_ORAL
									|| target==SexPositionSlot.KNEELING_RECEIVING_ORAL_TWO
									|| target==SexPositionSlot.KNEELING_RECEIVING_ORAL_THREE)) {
						return getSexInteractions(SexPositionSlot.KNEELING_PERFORMING_ORAL, SexPositionSlot.KNEELING_RECEIVING_ORAL);
					}
					if((target==SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND
							|| target==SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_TWO
							|| target==SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_THREE)
							&& (performer==SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND
									|| performer==SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_TWO
									|| performer==SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_THREE)) {
						return getSexInteractions(SexPositionSlot.KNEELING_RECEIVING_ORAL, SexPositionSlot.KNEELING_PERFORMING_ORAL);
					}
					if(((target==SexPositionSlot.KNEELING_PERFORMING_ORAL && performer!=SexPositionSlot.KNEELING_RECEIVING_ORAL)
							|| target==SexPositionSlot.KNEELING_PERFORMING_ORAL_TWO
							|| target==SexPositionSlot.KNEELING_PERFORMING_ORAL_THREE)
							&& (performer==SexPositionSlot.KNEELING_RECEIVING_ORAL
									|| performer==SexPositionSlot.KNEELING_RECEIVING_ORAL_TWO
									|| performer==SexPositionSlot.KNEELING_RECEIVING_ORAL_THREE)) {
						return getSexInteractions(SexPositionSlot.KNEELING_RECEIVING_ORAL, SexPositionSlot.KNEELING_PERFORMING_ORAL);
					}
				}
			}
			return super.getSexInteractions(performer, target);
		}
		
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			return false;
		}
	},
	
	MISSIONARY("Missionary",
			true,
			true,
			Util.newArrayListOfValues(Missionary.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ON_BACK,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas,
													SexActionPresets.groinToGroin,
													SexActionPresets.groinToAss,
													SexActionPresets.kissing,
													SexActionPresets.mouthToBreasts,
													SexActionPresets.breastsToMouth,
													SexActionPresets.penisToFeet),
											Util.newArrayListOfValues(
													OrgasmCumTarget.HAIR,
													OrgasmCumTarget.FACE,
													OrgasmCumTarget.BREASTS,
													OrgasmCumTarget.STOMACH,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.LEGS,
													OrgasmCumTarget.FEET,
													OrgasmCumTarget.FLOOR))),
							new Value<>(
									SexPositionSlot.MISSIONARY_SD_HUMPING,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas),
											Util.newArrayListOfValues(
													OrgasmCumTarget.ASS,
													OrgasmCumTarget.BACK,
													OrgasmCumTarget.LEGS,
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.MISSIONARY_SD_HUMPING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ON_BACK,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToUpperHalf,
													SexActionPresets.groinToGroin,
													SexActionPresets.assToPenis,
													SexActionPresets.kissing,
													SexActionPresets.mouthToBreasts,
													SexActionPresets.breastsToMouth),
											Util.newArrayListOfValues(
													OrgasmCumTarget.HAIR,
													OrgasmCumTarget.FACE,
													OrgasmCumTarget.BREASTS,
													OrgasmCumTarget.STOMACH,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.MISSIONARY_SD_PAIZURI,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ON_BACK,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToUpperHalf,
													SexActionPresets.penisToBreasts,
													SexActionPresets.kissing,
													SexActionPresets.mouthToBreasts,
													SexActionPresets.breastsToMouth),
											Util.newArrayListOfValues(
													OrgasmCumTarget.BREASTS,
													OrgasmCumTarget.STOMACH,
													OrgasmCumTarget.FLOOR))),
							new Value<>(
									SexPositionSlot.MISSIONARY_FACE_SITTING,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.kissing),
											Util.newArrayListOfValues(
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.STOMACH,
													OrgasmCumTarget.LEGS,
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.MISSIONARY_FACE_SITTING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ON_BACK,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToUpperHalf,
													SexActionPresets.vaginaToMouth,
													SexActionPresets.penisToMouth,
													SexActionPresets.assToMouth),
											Util.newArrayListOfValues(
															OrgasmCumTarget.BREASTS,
															OrgasmCumTarget.FACE,
															OrgasmCumTarget.HAIR))))),
					new Value<>(
							SexPositionSlot.MISSIONARY_ON_BACK,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas,
													SexActionPresets.groinToAss,
													SexActionPresets.groinToGroin),
											Util.newArrayListOfValues(
													OrgasmCumTarget.SELF_STOMACH,
													OrgasmCumTarget.SELF_BREASTS,
													OrgasmCumTarget.SELF_FACE,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.STOMACH,
													OrgasmCumTarget.FLOOR))),
							new Value<>(
									SexPositionSlot.MISSIONARY_KNEELING_BESIDE,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas),
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR))),
									new Value<>(
											SexPositionSlot.MISSIONARY_KNEELING_BESIDE_TWO,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.appendagesToAllAreas),
													Util.newArrayListOfValues(
															OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.MISSIONARY_ON_BACK_SECOND,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_KNEELING_BESIDE_SECOND,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas),
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR))),
									new Value<>(
											SexPositionSlot.MISSIONARY_KNEELING_BESIDE_SECOND_TWO,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.appendagesToAllAreas),
													Util.newArrayListOfValues(
															OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {
			StringBuilder positionSB = new StringBuilder();
			
			if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND)!=null) {
				positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK),
						"[npc.NameIsFull] kneeling before [npc2.name]"));
				
				if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_THIRD)!=null) {
					if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_FOURTH)!=null) {
						positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_SECOND),
								" and [npc2.NameIsFull], and [npc.is] ready to choose who to fuck first."));
						
						positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_THIRD),
								" In much the same way, [npc.NameIsFull] looking down upon [npc2.name]"));
						positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_FOURTH),
								" and [npc2.name], and [npc.is] deciding which one to have some fun with first."));
						
						
					} else {
						positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_SECOND), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_THIRD),
								", [npc.name], and [npc2.name]"));
						positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS),
								", and [npc.is] ready to choose who to fuck first..."));
					}
					
				} else {
					positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_SECOND),
							", while [npc.nameIsFull] similarly kneeling between [npc2.namePos] spread [npc2.legs]."));

					if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_SD_HUMPING)!=null) {
						positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_SD_HUMPING), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK),
								" [npc.nameHasFull] climbed up on top of [npc2.namePos] groin in anticipation of humping [npc2.herHim]."));
					}
					if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_SD_PAIZURI)!=null) {
						positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_SD_PAIZURI), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK),
								" Tempted by [npc2.her] [npc2.breasts+], [npc.nameHasFull] straddled [npc2.namePos] chest, and [npc.is] greedily feeling [npc2.herHim] up."));
					}
					if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_FACE_SITTING)!=null) {
						positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_FACE_SITTING), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK),
								" Wanting to receive some oral attention, [npc.name] has planted [npc.herself] on [npc2.namePos] face, and [npc.is] grinding [npc.her] groin down against [npc2.namePos] mouth."));
					}
					if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BESIDE)!=null) {
						if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BESIDE_TWO)!=null) {
							positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BESIDE), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BESIDE_TWO),
									" [npc.Name] and [npc2.name] are kneeling to either side of"));
							positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK),
									" [npc.name], ready to put [npc.her] [npc.hands] to use."));
						} else {
							positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BESIDE), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK),
									" [npc.NameIsFull] kneeling to one side of [npc2.name], ready to put [npc2.her] [npc2.hands] to use."));
						}
					}
					
					positionSB.append("</br>");
					
					if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_SD_HUMPING_SECOND)!=null) {
						positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_SD_HUMPING_SECOND), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_SECOND),
								" [npc.nameHasFull] climbed up on top of [npc2.namePos] groin in anticipation of humping [npc2.herHim]."));
					}
					if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_SD_PAIZURI_SECOND)!=null) {
						positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_SD_PAIZURI_SECOND), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_SECOND),
								" Tempted by [npc2.her] [npc2.breasts+], [npc.nameHasFull] straddled [npc2.namePos] chest, and [npc.is] greedily feeling [npc2.herHim] up."));
					}
					if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_FACE_SITTING_SECOND)!=null) {
						positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_FACE_SITTING_SECOND), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_SECOND),
								" Wanting to receive some oral attention, [npc.name] has planted [npc.herself] on [npc2.namePos] face, and [npc.is] grinding [npc.her] groin down against [npc2.namePos] mouth."));
					}
					if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BESIDE_SECOND)!=null) {
						if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BESIDE_SECOND_TWO)!=null) {
							positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BESIDE_SECOND), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BESIDE_SECOND_TWO),
									" [npc.Name] and [npc2.name] are kneeling to either side of"));
							positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_SECOND),
									" [npc.name], ready to put [npc.her] [npc.hands] to use."));
						} else {
							positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BESIDE_SECOND), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_SECOND),
									" [npc.NameIsFull] kneeling to one side of [npc2.name], ready to put [npc2.her] [npc2.hands] to use."));
						}
					}
					
				}

			} else {
				if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_SECOND)!=null) {
					positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK),
							"[npc.NameIsFull] kneeling before [npc2.name]"));
					
					if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_THIRD)!=null) {
						if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_FOURTH)!=null) {
							positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_SECOND), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_THIRD),
									", [npc.name], [npc2.name]"));
							positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_FOURTH),
									", and [npc2.NameIsFull], and [npc.is] ready to choose who to fuck first..."));
							
						} else {
							positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_SECOND), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_THIRD),
									", [npc.name], and [npc2.name]"));
							positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS),
									", and [npc.is] ready to choose who to fuck first..."));
						}
						
					} else {
						positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_SECOND),
								" and [npc.name], and [npc.is] ready to choose who to fuck first..."));
					}
					
				} else {
					positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK),
									"[npc.NameIsFull] kneeling between [npc2.namePos] [npc2.legs], looking down at [npc2.herHim] as [npc.she] [npc.verb(prepare)] to have sex in the missionary position."));
					
					if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_SD_HUMPING)!=null) {
						positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_SD_HUMPING), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK),
								" [npc.NameHasFull] climbed up on top of [npc2.namePos] groin, and with a firm grip on [npc2.her] [npc2.hips+], is preparing to start humping [npc2.herHim]."));
					}
					if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_SD_PAIZURI)!=null) {
						positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_SD_PAIZURI), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK),
								" Tempted by [npc2.her] [npc2.breasts+], [npc.nameHasFull] straddled [npc2.namePos] chest, and [npc.is] greedily feeling [npc2.herHim] up."));
					}
					if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_FACE_SITTING)!=null) {
						positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_FACE_SITTING), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK),
								" Wanting to receive some oral attention, [npc.name] has planted [npc.herself] on [npc2.namePos] face, and [npc.is] grinding [npc.her] groin down against [npc2.namePos] mouth."));
					}
					if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BESIDE)!=null) {
						if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BESIDE_TWO)!=null) {
							positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BESIDE), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BESIDE_TWO),
									" [npc.Name] and [npc2.name] are kneeling to either side of"));
							positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK),
									" [npc.name], ready to put [npc.her] [npc.hands] to use."));
						} else {
							positionSB.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BESIDE), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK),
									" [npc.NameIsFull] kneeling to one side of [npc2.name], ready to put [npc2.her] [npc2.hands] to use."));
						}
					}
				}
			}
			
			return positionSB.toString();
		}
		
		private boolean isTargetOnBack(GameCharacter target) {
			return Sex.getSexPositionSlot(target) == SexPositionSlot.MISSIONARY_ON_BACK || Sex.getSexPositionSlot(target) == SexPositionSlot.MISSIONARY_ON_BACK_SECOND
					|| Sex.getSexPositionSlot(target) == SexPositionSlot.MISSIONARY_ON_BACK_THIRD || Sex.getSexPositionSlot(target) == SexPositionSlot.MISSIONARY_ON_BACK_FOURTH;
		}

		@Override
		public SexActionInteractions getSexInteractions(SexPositionSlot performer, SexPositionSlot target) {
			
			// Add basic orgasms for all kneeling besides:
			if(performer == SexPositionSlot.MISSIONARY_KNEELING_BESIDE
					|| performer == SexPositionSlot.MISSIONARY_KNEELING_BESIDE_TWO
					|| performer == SexPositionSlot.MISSIONARY_KNEELING_BESIDE_SECOND
					|| performer == SexPositionSlot.MISSIONARY_KNEELING_BESIDE_SECOND_TWO) {
				return new SexActionInteractions(
						null,
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR));
			}
			
			
			// Remove kissing and other interactions if there are other positions blocking:
			if((performer == SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS && target == SexPositionSlot.MISSIONARY_ON_BACK)
					|| (performer == SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND && target == SexPositionSlot.MISSIONARY_ON_BACK_SECOND)) {
				
				List<SexPositionSlot> interferenceSlots = Util.newArrayListOfValues(SexPositionSlot.MISSIONARY_SD_HUMPING, SexPositionSlot.MISSIONARY_SD_PAIZURI, SexPositionSlot.MISSIONARY_FACE_SITTING);
				if(performer == SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND) {
					interferenceSlots = Util.newArrayListOfValues(SexPositionSlot.MISSIONARY_SD_HUMPING_SECOND, SexPositionSlot.MISSIONARY_SD_PAIZURI_SECOND, SexPositionSlot.MISSIONARY_FACE_SITTING_SECOND);
				}
				
				Map<SexAreaInterface, List<SexAreaInterface>> interactions = Util.mergeMaps(
						SexActionPresets.appendagesToAllAreas,
						SexActionPresets.groinToGroin,
						SexActionPresets.groinToAss,
						SexActionPresets.kissing,
						SexActionPresets.mouthToBreasts,
						SexActionPresets.breastsToMouth,
						SexActionPresets.penisToFeet);
				
				List<OrgasmCumTarget> orgasmTargets = Util.newArrayListOfValues(
						OrgasmCumTarget.ASS,
						OrgasmCumTarget.GROIN,
						OrgasmCumTarget.BREASTS,
						OrgasmCumTarget.STOMACH,
						OrgasmCumTarget.FACE,
						OrgasmCumTarget.LEGS,
						OrgasmCumTarget.FEET,
						OrgasmCumTarget.FLOOR);
				
				if(Sex.getCharacterInPosition(interferenceSlots.get(0))!=null) {
					interactions = Util.mergeMaps(
							SexActionPresets.appendagesToAss,
							SexActionPresets.groinToAss,
							SexActionPresets.penisToFeet);
					orgasmTargets = Util.newArrayListOfValues(
							OrgasmCumTarget.ASS,
							OrgasmCumTarget.LEGS,
							OrgasmCumTarget.FEET,
							OrgasmCumTarget.FLOOR);
					
				} else if(Sex.getCharacterInPosition(interferenceSlots.get(1))!=null) {
					interactions = Util.mergeMaps(
							SexActionPresets.appendagesToLowerHalf,
							SexActionPresets.groinToGroin,
							SexActionPresets.groinToAss,
							SexActionPresets.penisToFeet);
					orgasmTargets = Util.newArrayListOfValues(
							OrgasmCumTarget.ASS,
							OrgasmCumTarget.GROIN,
							OrgasmCumTarget.LEGS,
							OrgasmCumTarget.FEET,
							OrgasmCumTarget.FLOOR);
					
				} else if(Sex.getCharacterInPosition(interferenceSlots.get(2))!=null) {
					interactions = Util.mergeMaps(
							SexActionPresets.appendagesToLowerHalf,
							SexActionPresets.appendagesToBreasts,
							SexActionPresets.groinToGroin,
							SexActionPresets.groinToAss,
							SexActionPresets.mouthToBreasts,
							SexActionPresets.breastsToMouth,
							SexActionPresets.penisToFeet);
					orgasmTargets = Util.newArrayListOfValues(
							OrgasmCumTarget.ASS,
							OrgasmCumTarget.GROIN,
							OrgasmCumTarget.BREASTS,
							OrgasmCumTarget.STOMACH,
							OrgasmCumTarget.LEGS,
							OrgasmCumTarget.FEET,
							OrgasmCumTarget.FLOOR);
				}
				
				return new SexActionInteractions(
						interactions,
						orgasmTargets);
			}

			// Humping blocks:
			if((performer == SexPositionSlot.MISSIONARY_SD_HUMPING && target == SexPositionSlot.MISSIONARY_ON_BACK)
					|| (performer == SexPositionSlot.MISSIONARY_SD_HUMPING_SECOND && target == SexPositionSlot.MISSIONARY_ON_BACK_SECOND)) {
				
				List<SexPositionSlot> interferenceSlots = Util.newArrayListOfValues(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS, SexPositionSlot.MISSIONARY_SD_PAIZURI, SexPositionSlot.MISSIONARY_FACE_SITTING);
				if(performer == SexPositionSlot.MISSIONARY_SD_HUMPING_SECOND) {
					interferenceSlots = Util.newArrayListOfValues(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND, SexPositionSlot.MISSIONARY_SD_PAIZURI_SECOND, SexPositionSlot.MISSIONARY_FACE_SITTING_SECOND);
				}
				
				Map<SexAreaInterface, List<SexAreaInterface>> interactions = new HashMap<>();
				
				Util.mergeMaps(
						SexActionPresets.appendagesToUpperHalf,
						SexActionPresets.groinToGroin,
						SexActionPresets.assToPenis,
						SexActionPresets.kissing,
						SexActionPresets.mouthToBreasts,
						SexActionPresets.breastsToMouth);
				
				List<OrgasmCumTarget> orgasmTargets = Util.newArrayListOfValues(
						OrgasmCumTarget.GROIN,
						OrgasmCumTarget.FLOOR);
				
				if(Sex.getCharacterInPosition(interferenceSlots.get(0))!=null) {
					
					interactions = Util.mergeMaps(interactions, SexActionPresets.penisToVagina);
					interactions = Util.mergeMaps(interactions, SexActionPresets.assToPenis);
					interactions = Util.mergeMaps(interactions, SexActionPresets.vaginaToPenis);
					
				} else {
					interactions = Util.mergeMaps(interactions, SexActionPresets.groinToGroin);
					interactions = Util.mergeMaps(interactions, SexActionPresets.assToPenis);
				}
				
				if(Sex.getCharacterInPosition(interferenceSlots.get(1))!=null) {
					interactions = Util.mergeMaps(interactions, SexActionPresets.mouthToBreasts);
					interactions = Util.mergeMaps(interactions, SexActionPresets.appendagesToUpperHalf);
					
					if(Sex.getCharacterInPosition(interferenceSlots.get(2))!=null) {
						interactions = Util.mergeMaps(interactions, SexActionPresets.kissing);
						interactions = Util.mergeMaps(interactions, SexActionPresets.breastsToMouth);
					}
				} else {
					orgasmTargets.add(OrgasmCumTarget.BREASTS);
					orgasmTargets.add(OrgasmCumTarget.STOMACH);
					
					if(Sex.getCharacterInPosition(interferenceSlots.get(2))!=null) {
						orgasmTargets.add(OrgasmCumTarget.FACE);
						orgasmTargets.add(OrgasmCumTarget.HAIR);
					}
				}
				
				return new SexActionInteractions(
						interactions,
						orgasmTargets);
			}
			
			// Paizuri blocks:
			if((performer == SexPositionSlot.MISSIONARY_SD_PAIZURI && target == SexPositionSlot.MISSIONARY_ON_BACK)
					|| (performer == SexPositionSlot.MISSIONARY_SD_PAIZURI_SECOND && target == SexPositionSlot.MISSIONARY_ON_BACK_SECOND)) {
				
				List<SexPositionSlot> interferenceSlots = Util.newArrayListOfValues(SexPositionSlot.MISSIONARY_FACE_SITTING);
				if(performer == SexPositionSlot.MISSIONARY_SD_PAIZURI_SECOND) {
					interferenceSlots = Util.newArrayListOfValues(SexPositionSlot.MISSIONARY_FACE_SITTING_SECOND);
				}
				
				Map<SexAreaInterface, List<SexAreaInterface>> interactions = new HashMap<>();
				
				Util.mergeMaps(
						SexActionPresets.appendagesToUpperHalf,
						SexActionPresets.penisToBreasts,
						SexActionPresets.kissing,
						SexActionPresets.mouthToBreasts,
						SexActionPresets.breastsToMouth);
				
				List<OrgasmCumTarget> orgasmTargets = Util.newArrayListOfValues(
						OrgasmCumTarget.BREASTS,
						OrgasmCumTarget.STOMACH,
						OrgasmCumTarget.FLOOR);
				
				if(Sex.getCharacterInPosition(interferenceSlots.get(0))!=null) {
					interactions = Util.mergeMaps(
							SexActionPresets.appendagesToBreasts,
							SexActionPresets.penisToBreasts);
					
				} else {
					interactions = Util.mergeMaps(
							SexActionPresets.appendagesToUpperHalf,
							SexActionPresets.penisToBreasts,
							SexActionPresets.kissing,
							SexActionPresets.mouthToBreasts,
							SexActionPresets.breastsToMouth);
				}
				
				return new SexActionInteractions(
						interactions,
						orgasmTargets);
			}
			
			// Face-sitting blocks:
			if((performer == SexPositionSlot.MISSIONARY_FACE_SITTING && target == SexPositionSlot.MISSIONARY_ON_BACK)
					|| (performer == SexPositionSlot.MISSIONARY_FACE_SITTING_SECOND && target == SexPositionSlot.MISSIONARY_ON_BACK_SECOND)) {
				
				List<SexPositionSlot> interferenceSlots = Util.newArrayListOfValues(SexPositionSlot.MISSIONARY_SD_PAIZURI, SexPositionSlot.MISSIONARY_SD_HUMPING);
				if(performer == SexPositionSlot.MISSIONARY_SD_PAIZURI_SECOND) {
					interferenceSlots = Util.newArrayListOfValues(SexPositionSlot.MISSIONARY_SD_PAIZURI_SECOND, SexPositionSlot.MISSIONARY_SD_HUMPING_SECOND);
				}
				
				Map<SexAreaInterface, List<SexAreaInterface>> interactions = Util.mergeMaps(
						SexActionPresets.appendagesToLowerHalf,
						SexActionPresets.appendagesToUpperHalf,
						SexActionPresets.vaginaToMouth,
						SexActionPresets.penisToMouth,
						SexActionPresets.assToMouth);
				
				List<OrgasmCumTarget> orgasmTargets = Util.newArrayListOfValues(
						OrgasmCumTarget.BREASTS,
						OrgasmCumTarget.STOMACH,
						OrgasmCumTarget.FACE,
						OrgasmCumTarget.HAIR,
						OrgasmCumTarget.FLOOR);
				
				if(Sex.getCharacterInPosition(interferenceSlots.get(0))!=null) {
					interactions = Util.mergeMaps(
							SexActionPresets.vaginaToMouth,
							SexActionPresets.penisToMouth,
							SexActionPresets.assToMouth);
					orgasmTargets.remove(OrgasmCumTarget.BREASTS);
					orgasmTargets.remove(OrgasmCumTarget.STOMACH);
					
				} else if(Sex.getCharacterInPosition(interferenceSlots.get(1))!=null) {
					interactions = Util.mergeMaps(
							SexActionPresets.appendagesToUpperHalf,
							SexActionPresets.vaginaToMouth,
							SexActionPresets.penisToMouth,
							SexActionPresets.assToMouth);
				}
				
				return new SexActionInteractions(
						interactions,
						orgasmTargets);
			}
			
			
			// If on back, set correct restrictions and orgasm areas:
			if(performer==SexPositionSlot.MISSIONARY_ON_BACK || performer==SexPositionSlot.MISSIONARY_ON_BACK_SECOND) {
				SexActionInteractions interactions = super.getSexInteractions(performer, target);
				
				List<SexPositionSlot> interferenceSlots = Util.newArrayListOfValues(
						SexPositionSlot.MISSIONARY_SD_HUMPING,
						SexPositionSlot.MISSIONARY_SD_PAIZURI,
						SexPositionSlot.MISSIONARY_FACE_SITTING,
						SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS);
				
				if(performer == SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND) {
					interferenceSlots = Util.newArrayListOfValues(
							SexPositionSlot.MISSIONARY_SD_HUMPING_SECOND,
							SexPositionSlot.MISSIONARY_SD_PAIZURI_SECOND,
							SexPositionSlot.MISSIONARY_FACE_SITTING_SECOND,
							SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND);
				}
				
				Map<SexAreaInterface, List<SexAreaInterface>> sexActionInteractions = null;

				// Restrictions:
				if(target == interferenceSlots.get(3)) { // Kneeling between legs
					if(Sex.getCharacterInPosition(interferenceSlots.get(0))!=null) { // Humping restricts pussy:
						sexActionInteractions = Util.mergeMaps(
										SexActionPresets.assToGroin);
					} 
				}
				
				
				
				List<OrgasmCumTarget> orgasmTargets = Util.newArrayListOfValues(
													OrgasmCumTarget.SELF_STOMACH,
													OrgasmCumTarget.SELF_BREASTS,
													OrgasmCumTarget.SELF_FACE,
													OrgasmCumTarget.FLOOR);
				
				if(target == interferenceSlots.get(0)) {
					orgasmTargets = Util.newArrayListOfValues(
							OrgasmCumTarget.ASS,
							OrgasmCumTarget.GROIN,
							OrgasmCumTarget.STOMACH,
							OrgasmCumTarget.BACK,
							OrgasmCumTarget.SELF_STOMACH,
							OrgasmCumTarget.SELF_GROIN,
							OrgasmCumTarget.FLOOR);
					
				} else if(Sex.getCharacterInPosition(interferenceSlots.get(0))!=null || Sex.getCharacterInPosition(interferenceSlots.get(1))!=null) {
					orgasmTargets.remove(OrgasmCumTarget.SELF_STOMACH);
					orgasmTargets.remove(OrgasmCumTarget.SELF_BREASTS);
					orgasmTargets.remove(OrgasmCumTarget.SELF_FACE);
					
				} else if(Sex.getCharacterInPosition(interferenceSlots.get(2))!=null) {
					orgasmTargets.remove(OrgasmCumTarget.SELF_FACE);
				}

				if(performer==SexPositionSlot.MISSIONARY_ON_BACK
						?target == SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS
						:target == SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND) {
					orgasmTargets.add(OrgasmCumTarget.GROIN);
					orgasmTargets.add(OrgasmCumTarget.STOMACH);
					orgasmTargets.add(OrgasmCumTarget.LEGS);
				}
				
				if(sexActionInteractions!=null) {
					interactions.setInteractions(sexActionInteractions);
				}
				interactions.setAvailableCumTargets(orgasmTargets);
				return interactions;
			}
			
			// Add normal kneeler/back interactions for all back slots:
			SexActionInteractions kneelerBackInteractions = new SexActionInteractions(
					Util.mergeMaps(
							SexActionPresets.appendagesToAllAreas,
							SexActionPresets.groinToGroin,
							SexActionPresets.groinToAss,
							SexActionPresets.kissing,
							SexActionPresets.mouthToBreasts,
							SexActionPresets.breastsToMouth,
							SexActionPresets.penisToFeet),
					Util.newArrayListOfValues(
							OrgasmCumTarget.HAIR,
							OrgasmCumTarget.FACE,
							OrgasmCumTarget.BREASTS,
							OrgasmCumTarget.STOMACH,
							OrgasmCumTarget.GROIN,
							OrgasmCumTarget.LEGS,
							OrgasmCumTarget.FEET,
							OrgasmCumTarget.FLOOR));
			
			if(performer == SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS) {
				if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND)==null) {
					if(target==SexPositionSlot.MISSIONARY_ON_BACK
							|| target==SexPositionSlot.MISSIONARY_ON_BACK_SECOND
							|| target==SexPositionSlot.MISSIONARY_ON_BACK_THIRD
							|| target==SexPositionSlot.MISSIONARY_ON_BACK_FOURTH) {
						return kneelerBackInteractions;
					}
				} else {
					if(target==SexPositionSlot.MISSIONARY_ON_BACK
							|| (Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_THIRD)!=null && target==SexPositionSlot.MISSIONARY_ON_BACK_SECOND)) {
						return kneelerBackInteractions;
					}
				}
			}
			if(performer == SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND) {
				if(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK_THIRD)!=null) {
					if(target==SexPositionSlot.MISSIONARY_ON_BACK_THIRD || target==SexPositionSlot.MISSIONARY_ON_BACK_FOURTH) {
						return kneelerBackInteractions;
					}
				} else {
					if(target==SexPositionSlot.MISSIONARY_ON_BACK_SECOND) {
						return kneelerBackInteractions;
					}
				}
			}
			
			return super.getSexInteractions(performer, target);
		}
		
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			// Restrict penis actions if there is already an ongoing penis action between on back and between legs slots:
			if(((Sex.getSexPositionSlot(performer) == SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS || Sex.getSexPositionSlot(performer) == SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND) && isTargetOnBack(target))
					|| ((Sex.getSexPositionSlot(target) == SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS || Sex.getSexPositionSlot(target) == SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND) && isTargetOnBack(performer))) {
				if((action.getActionType()==SexActionType.START_ONGOING
						|| action.getActionType()==SexActionType.REQUIRES_EXPOSED)
						&& action.getSexAreaInteractions().keySet().contains(SexAreaPenetration.PENIS)) {
					for(SexAreaInterface sa : action.getSexAreaInteractions().values()) {
						if(sa.isOrifice()
								&& (Sex.getCharactersHavingOngoingActionWith(performer, SexAreaPenetration.PENIS).contains(target) || Sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.PENIS).contains(performer))) {
							return true;
						}
					}
				}
				if((action.getActionType()==SexActionType.START_ONGOING
						|| action.getActionType()==SexActionType.REQUIRES_EXPOSED)
						&& action.getSexAreaInteractions().values().contains(SexAreaPenetration.PENIS)) {
					for(SexAreaInterface sa : action.getSexAreaInteractions().keySet()) {
						if(sa.isOrifice()
								&& (Sex.getCharactersHavingOngoingActionWith(performer, SexAreaPenetration.PENIS).contains(target) || Sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.PENIS).contains(performer))) {
							return true;
						}
					}
				}
			}
			
			return super.isActionBlocked(performer, target, action);
		}

		@Override
		public int getMaximumSlots() {
			return 8;
		}
	},

	CHAIR_SEX("Chair sex",
			true,
			false,
			Util.newArrayListOfValues(ChairSex.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.CHAIR_TOP,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.CHAIR_BOTTOM,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas,
													SexActionPresets.vaginaToPenis,
													SexActionPresets.assToPenis,
													SexActionPresets.penisToPenis,
													SexActionPresets.kissing,
													SexActionPresets.mouthToBreasts,
													SexActionPresets.breastsToMouth),
											Util.newArrayListOfValues(
													OrgasmCumTarget.HAIR,
													OrgasmCumTarget.FACE,
													OrgasmCumTarget.BREASTS,
													OrgasmCumTarget.STOMACH,
													OrgasmCumTarget.GROIN))))),
					new Value<>(
							SexPositionSlot.CHAIR_BOTTOM,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.CHAIR_TOP,
									new SexActionInteractions(
											SexActionPresets.appendagesToAllAreas,
											Util.newArrayListOfValues(
													OrgasmCumTarget.SELF_STOMACH,
													OrgasmCumTarget.SELF_BREASTS,
													OrgasmCumTarget.SELF_FACE,
													OrgasmCumTarget.STOMACH,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.CHAIR_TOP), Sex.getCharacterInPosition(SexPositionSlot.CHAIR_BOTTOM),
					"[npc2.NameIs] sitting down on the chair, looking up at [npc.name] as [npc.she] [npc.verb(smile)] down at [npc2.herHim].");
		}
	},
	
	CHAIR_SEX_ORAL("Chair sex (oral)",
			true,
			false,
			Util.newArrayListOfValues(ChairSex.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.CHAIR_KNEELING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.CHAIR_ORAL_SITTING,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.fingerToLowerHalf,
													SexActionPresets.penisToFeet),
											Util.newArrayListOfValues(
													OrgasmCumTarget.SELF_STOMACH,
													OrgasmCumTarget.SELF_GROIN,
													OrgasmCumTarget.SELF_LEGS,
													OrgasmCumTarget.FEET,
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.CHAIR_ORAL_SITTING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.CHAIR_KNEELING,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.tailToUpperTorso,
											SexActionPresets.tentacleToUpperTorso,
											SexActionPresets.vaginaToMouth,
											SexActionPresets.penisToMouth,
											SexActionPresets.penisToBreasts,
											SexActionPresets.assToMouth),
										Util.newArrayListOfValues(
												OrgasmCumTarget.SELF_STOMACH,
												OrgasmCumTarget.SELF_GROIN,
												OrgasmCumTarget.SELF_LEGS,
												OrgasmCumTarget.SELF_FEET,
												OrgasmCumTarget.FLOOR,
												OrgasmCumTarget.FACE,
												OrgasmCumTarget.BREASTS,
												OrgasmCumTarget.HAIR,
												OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.CHAIR_KNEELING), Sex.getCharacterInPosition(SexPositionSlot.CHAIR_ORAL_SITTING),
					"[npc2.NameIs] sitting down on the chair, looking down at [npc.name] as [npc.she] [npc.verb(kneel)] before [npc2.herHim], ready to perform oral.");
		}
	},
	
	MASTURBATION("Kneeling",
			true,
			false,
			Util.newArrayListOfValues(Masturbation.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.MASTURBATING_KNEELING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MASTURBATING_KNEELING,
									new SexActionInteractions(
											null,
											Util.newArrayListOfValues(
													OrgasmCumTarget.SELF_STOMACH,
													OrgasmCumTarget.SELF_GROIN,
													OrgasmCumTarget.SELF_LEGS,
													OrgasmCumTarget.SELF_FEET,
													OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MASTURBATING_KNEELING),
					"[npc.NameIs] kneeling on the floor, ready to masturbate.");
		}
	},
	
	PANTY_MASTURBATION("Kneeling",
			true,
			false,
			Util.newArrayListOfValues(MasturbationPanties.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.MASTURBATING_KNEELING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MASTURBATING_KNEELING,
									new SexActionInteractions(
											null,
											Util.newArrayListOfValues(
													OrgasmCumTarget.SELF_STOMACH,
													OrgasmCumTarget.SELF_GROIN,
													OrgasmCumTarget.SELF_LEGS,
													OrgasmCumTarget.SELF_FEET,
													OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MASTURBATING_KNEELING),
					"[npc.NameIs] kneeling on the floor, ready to masturbate with the panties clenched in [npc.her] [npc.hand].");
		}
	},
	
	STOCKS_SEX("Stocks sex",
			true,
			false,
			Util.newArrayListOfValues(StocksSex.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.STOCKS_LOCKED_IN_STOCKS,
							Util.newHashMapOfValues(
								new Value<>(
										SexPositionSlot.STOCKS_RECEIVING_ORAL,
										new SexActionInteractions(
												null,
												Util.newArrayListOfValues(
														OrgasmCumTarget.FLOOR))),
								new Value<>(
										SexPositionSlot.STOCKS_PERFORMING_ORAL,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToUpperTorso,
														SexActionPresets.tentacleToUpperTorso,
														SexActionPresets.vaginaToMouth,
														SexActionPresets.penisToMouth,
														SexActionPresets.assToMouth),
												Util.newArrayListOfValues(
														OrgasmCumTarget.FLOOR))),
								new Value<>(
										SexPositionSlot.STOCKS_FUCKING,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToUpperTorso,
														SexActionPresets.tentacleToUpperTorso),
												Util.newArrayListOfValues(
														OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.STOCKS_RECEIVING_ORAL,
							Util.newHashMapOfValues(
								new Value<>(
										SexPositionSlot.STOCKS_LOCKED_IN_STOCKS,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToUpperTorso,
														SexActionPresets.tentacleToUpperTorso,
														SexActionPresets.fingerToUpperTorso,
														SexActionPresets.vaginaToMouth,
														SexActionPresets.penisToMouth),
												Util.newArrayListOfValues(
																OrgasmCumTarget.FACE,
																OrgasmCumTarget.HAIR,
																OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.STOCKS_PERFORMING_ORAL,
							Util.newHashMapOfValues(
								new Value<>(
										SexPositionSlot.STOCKS_LOCKED_IN_STOCKS,
										new SexActionInteractions(
												null,
												Util.newArrayListOfValues(
														OrgasmCumTarget.SELF_STOMACH,
														OrgasmCumTarget.SELF_GROIN,
														OrgasmCumTarget.SELF_LEGS,
														OrgasmCumTarget.SELF_FEET,
														OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.STOCKS_FUCKING,
							Util.newHashMapOfValues(
								new Value<>(
										SexPositionSlot.STOCKS_LOCKED_IN_STOCKS,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.fingerToLowerHalf,
														SexActionPresets.tailToLowerHalf,
														SexActionPresets.tentacleToLowerHalf,
														SexActionPresets.penisToAss,
														SexActionPresets.penisToVagina,
														SexActionPresets.penisToThighs),
												Util.newArrayListOfValues(
														OrgasmCumTarget.ASS,
														OrgasmCumTarget.BACK,
														OrgasmCumTarget.GROIN,
														OrgasmCumTarget.LEGS,
														OrgasmCumTarget.FEET,
														OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.STOCKS_LOCKED_IN_STOCKS),
					"[npc.NameIsFull] locked into the stocks, ready for breeding."));

			if(Sex.getCharacterInPosition(SexPositionSlot.STOCKS_RECEIVING_ORAL)!=null) {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.STOCKS_RECEIVING_ORAL), Sex.getCharacterInPosition(SexPositionSlot.STOCKS_LOCKED_IN_STOCKS),
						" [npc.NameIsFull] standing in front of [npc2.her] [npc2.face], ready to put [npc2.her] mouth to good use."));
			}

			if(Sex.getCharacterInPosition(SexPositionSlot.STOCKS_PERFORMING_ORAL)!=null) {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.STOCKS_PERFORMING_ORAL), Sex.getCharacterInPosition(SexPositionSlot.STOCKS_LOCKED_IN_STOCKS),
						" [npc.NameIsFull] kneeling behind [npc2.name], ready to perform oral on [npc2.herHim]."));
			}

			if(Sex.getCharacterInPosition(SexPositionSlot.STOCKS_FUCKING)!=null) {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.STOCKS_FUCKING), Sex.getCharacterInPosition(SexPositionSlot.STOCKS_LOCKED_IN_STOCKS),
						" [npc.NameIsFull] standing behind [npc2.name], ready to take advantage of [npc2.her] compromising position."));
			}
			
			return sb.toString();
		}
		
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			
			if(Sex.getSexPositionSlot(performer) == SexPositionSlot.STOCKS_LOCKED_IN_STOCKS) {
				if((action.getActionType()==SexActionType.ONGOING
						|| action.getActionType()==SexActionType.START_ONGOING
						|| action.getActionType()==SexActionType.REQUIRES_NO_PENETRATION
						|| action.getActionType()==SexActionType.REQUIRES_EXPOSED
						|| action.getActionType()==SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED)
							&& action.getParticipantType()==SexParticipantType.SELF
							&& (action.getSexAreaInteractions()==null
								|| action.getSexAreaInteractions().containsKey(SexAreaPenetration.TAIL)
								|| action.getSexAreaInteractions().containsKey(SexAreaPenetration.TENTACLE))) {
					return true;
				}
			}
			
			return super.isActionBlocked(performer, target, action);
		}
	},
	
	MILKING_STALL("Milking Stall",
			true,
			false,
			Util.newArrayListOfValues(MilkingStall.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.MILKING_STALL_LOCKED_IN_MILKING_STALL,
							Util.newHashMapOfValues(
								new Value<>(
										SexPositionSlot.MILKING_STALL_RECEIVING_ORAL,
										new SexActionInteractions(
												null,
												Util.newArrayListOfValues(
														OrgasmCumTarget.FLOOR))),
								new Value<>(
										SexPositionSlot.MILKING_STALL_PERFORMING_ORAL,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToUpperTorso,
														SexActionPresets.tentacleToUpperTorso,
														SexActionPresets.vaginaToMouth,
														SexActionPresets.penisToMouth,
														SexActionPresets.assToMouth),
												Util.newArrayListOfValues(
														OrgasmCumTarget.FLOOR))),
								new Value<>(
										SexPositionSlot.MILKING_STALL_FUCKING,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToUpperTorso,
														SexActionPresets.tentacleToUpperTorso),
												Util.newArrayListOfValues(
														OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.MILKING_STALL_RECEIVING_ORAL,
							Util.newHashMapOfValues(
								new Value<>(
										SexPositionSlot.MILKING_STALL_LOCKED_IN_MILKING_STALL,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToUpperTorso,
														SexActionPresets.tentacleToUpperTorso,
														SexActionPresets.fingerToUpperTorso,
														SexActionPresets.vaginaToMouth,
														SexActionPresets.penisToMouth),
												Util.newArrayListOfValues(
														OrgasmCumTarget.FACE,
														OrgasmCumTarget.HAIR,
														OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.MILKING_STALL_PERFORMING_ORAL,
							Util.newHashMapOfValues(
								new Value<>(
										SexPositionSlot.MILKING_STALL_LOCKED_IN_MILKING_STALL,
										new SexActionInteractions(
												null,
												Util.newArrayListOfValues(
														OrgasmCumTarget.SELF_STOMACH,
														OrgasmCumTarget.SELF_GROIN,
														OrgasmCumTarget.SELF_LEGS,
														OrgasmCumTarget.SELF_FEET,
														OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.MILKING_STALL_FUCKING,
							Util.newHashMapOfValues(
								new Value<>(
										SexPositionSlot.MILKING_STALL_LOCKED_IN_MILKING_STALL,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.fingerToLowerHalf,
														SexActionPresets.tailToLowerHalf,
														SexActionPresets.tentacleToLowerHalf,
														SexActionPresets.penisToAss,
														SexActionPresets.penisToVagina,
														SexActionPresets.penisToThighs),
												Util.newArrayListOfValues(
														OrgasmCumTarget.ASS,
														OrgasmCumTarget.BACK,
														OrgasmCumTarget.GROIN,
														OrgasmCumTarget.LEGS,
														OrgasmCumTarget.FEET,
														OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MILKING_STALL_LOCKED_IN_MILKING_STALL),
					"[npc.NameIsFull] locked into the stocks, ready for breeding."));

			if(Sex.getCharacterInPosition(SexPositionSlot.MILKING_STALL_RECEIVING_ORAL)!=null) {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MILKING_STALL_RECEIVING_ORAL), Sex.getCharacterInPosition(SexPositionSlot.MILKING_STALL_LOCKED_IN_MILKING_STALL),
						" [npc.NameIsFull] standing in front of [npc2.her] [npc2.face], ready to put [npc2.her] mouth to good use."));
			}

			if(Sex.getCharacterInPosition(SexPositionSlot.MILKING_STALL_PERFORMING_ORAL)!=null) {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MILKING_STALL_PERFORMING_ORAL), Sex.getCharacterInPosition(SexPositionSlot.MILKING_STALL_LOCKED_IN_MILKING_STALL),
						" [npc.NameIsFull] kneeling behind [npc2.name], ready to perform oral on [npc2.herHim]."));
			}

			if(Sex.getCharacterInPosition(SexPositionSlot.MILKING_STALL_FUCKING)!=null) {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MILKING_STALL_FUCKING), Sex.getCharacterInPosition(SexPositionSlot.MILKING_STALL_LOCKED_IN_MILKING_STALL),
						" [npc.NameIsFull] standing behind [npc2.name], ready to take advantage of [npc2.her] compromising position."));
			}
			
			return sb.toString();
		}
		
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			
			if(Sex.getSexPositionSlot(performer) == SexPositionSlot.MILKING_STALL_LOCKED_IN_MILKING_STALL) {
				if((action.getActionType()==SexActionType.ONGOING
						|| action.getActionType()==SexActionType.START_ONGOING
						|| action.getActionType()==SexActionType.REQUIRES_NO_PENETRATION
						|| action.getActionType()==SexActionType.REQUIRES_EXPOSED
						|| action.getActionType()==SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED)
							&& action.getParticipantType()==SexParticipantType.SELF
							&& (action.getSexAreaInteractions()==null
								|| action.getSexAreaInteractions().containsKey(SexAreaPenetration.TAIL)
								|| action.getSexAreaInteractions().containsKey(SexAreaPenetration.TENTACLE))) {
					return true;
				}
			}
			
			return super.isActionBlocked(performer, target, action);
		}
	},
	
	/* UNIQUE */
	
	PET_MOUNTING("Mounted",
			true,
			false,
			Util.newArrayListOfValues(PetMounting.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.PET_MOUNTING_ON_ALL_FOURS,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.PET_MOUNTING_HUMPING,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToAllAreas,
													SexActionPresets.tentacleToAllAreas,
													SexActionPresets.vaginaToPenis,
													SexActionPresets.assToPenis,
													SexActionPresets.kissing),
											DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_ON_ALL_FOURS, SexPositionSlot.DOGGY_BEHIND).getAvailableCumTargets())))),
					new Value<>(
							SexPositionSlot.PET_MOUNTING_HUMPING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.PET_MOUNTING_ON_ALL_FOURS,
									new SexActionInteractions(
											SexActionPresets.appendagesToAllAreas,
											DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_BEHIND, SexPositionSlot.DOGGY_ON_ALL_FOURS).getAvailableCumTargets())))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.PET_MOUNTING_ON_ALL_FOURS), Sex.getCharacterInPosition(SexPositionSlot.PET_MOUNTING_HUMPING),
					"[npc.NameIs] down on all fours, and [npc.has] been mounted by [npc2.name], who's desperate to penetrate and start humping [npc.herHim].");
		}
	},
	
	PET_ORAL("Pet Oral",
			true,
			false,
			Util.newArrayListOfValues(PetOral.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.PET_ORAL_ON_ALL_FOURS,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.PET_ORAL_COCKED_LEG,
									new SexActionInteractions(
											SexActionPresets.fingerToLowerHalf,
											KNEELING_ORAL.getSexInteractions(SexPositionSlot.KNEELING_PERFORMING_ORAL, SexPositionSlot.KNEELING_RECEIVING_ORAL).getAvailableCumTargets())))),
					new Value<>(
							SexPositionSlot.PET_ORAL_COCKED_LEG,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.PET_ORAL_ON_ALL_FOURS,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.tailToUpperTorso,
											SexActionPresets.tentacleToUpperTorso,
											SexActionPresets.vaginaToMouth,
											SexActionPresets.penisToMouth),
										KNEELING_ORAL.getSexInteractions(SexPositionSlot.KNEELING_RECEIVING_ORAL, SexPositionSlot.KNEELING_PERFORMING_ORAL).getAvailableCumTargets())))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.PET_ORAL_ON_ALL_FOURS), Sex.getCharacterInPosition(SexPositionSlot.PET_ORAL_COCKED_LEG),
					"[npc.NameIs] down on all fours, with [npc2.namePos] [npc2.leg] hooked over [npc.her] neck, leaving [npc.her] face just [unit.sizes] away from [npc2.namePos] [npc2.cock+].");
		}
	},
	
	UNDER_DESK_RALPH("Under desk",
			false,
			false,
			Util.newArrayListOfValues(RalphOral.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.KNEELING_RECEIVING_ORAL_RALPH,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_PERFORMING_ORAL_RALPH,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.tailToUpperTorso,
											SexActionPresets.tentacleToUpperTorso,
											SexActionPresets.vaginaToMouth,
											SexActionPresets.penisToMouth,
											SexActionPresets.groinToGroin,
											SexActionPresets.penisToAss),
										KNEELING_ORAL.getSexInteractions(SexPositionSlot.KNEELING_RECEIVING_ORAL, SexPositionSlot.KNEELING_PERFORMING_ORAL).getAvailableCumTargets())))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL_RALPH,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_RECEIVING_ORAL_RALPH,
									new SexActionInteractions(
											Util.mergeMaps(
											SexActionPresets.fingerToLowerHalf,
											SexActionPresets.penisToFeet),
											KNEELING_ORAL.getSexInteractions(SexPositionSlot.KNEELING_PERFORMING_ORAL, SexPositionSlot.KNEELING_RECEIVING_ORAL).getAvailableCumTargets())))))) {
		@Override
		public String getDescription() {
			return "You're kneeling under Ralph's counter, with your face just [unit.size] away from his crotch.";
		}
	},
	
	SHOWER_TIME_PIX("Shower sex",
			false,
			false,
			Util.newArrayListOfValues(PixShower.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.FACE_TO_WALL_FACING_TARGET_SHOWER_PIX,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX,
									new SexActionInteractions(
										Util.mergeMaps(
												SexActionPresets.tailToAllAreas,
												SexActionPresets.tentacleToAllAreas,
												SexActionPresets.vaginaToPenis,
												SexActionPresets.assToPenis,
												SexActionPresets.kissing),
										FACING_WALL.getSexInteractions(SexPositionSlot.FACE_TO_WALL_FACING_TARGET, SexPositionSlot.FACE_TO_WALL_AGAINST_WALL).getAvailableCumTargets())))),
					new Value<>(
							SexPositionSlot.FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.FACE_TO_WALL_FACING_TARGET_SHOWER_PIX,
									new SexActionInteractions(
											SexActionPresets.appendagesToAllAreas,
										FACING_WALL.getSexInteractions(SexPositionSlot.FACE_TO_WALL_AGAINST_WALL, SexPositionSlot.FACE_TO_WALL_FACING_TARGET).getAvailableCumTargets())))))) {
		@Override
		public String getDescription() {
			return "You're standing with your face pressed up against one wall of the shower, and behind you, Pix is growling hungrily into your ear.";
		}
	},
	
	HANDS_ROSE("Hand-holding",
			false,
			false,
			Util.newArrayListOfValues(HandHolding.class, GenericOrgasms.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.HAND_SEX_DOM_ROSE,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.HAND_SEX_SUB_ROSE,
									new SexActionInteractions(
											Util.newHashMapOfValues(
													new Value<>(SexAreaPenetration.FINGER, Util.newArrayListOfValues(SexAreaPenetration.FINGER)),
													new Value<>(SexAreaPenetration.FINGER, Util.newArrayListOfValues(SexAreaOrifice.MOUTH)),
													new Value<>(SexAreaOrifice.MOUTH, Util.newArrayListOfValues(SexAreaPenetration.FINGER))),
											STANDING.getSexInteractions(SexPositionSlot.STANDING_DOMINANT, SexPositionSlot.STANDING_SUBMISSIVE).getAvailableCumTargets())))),
					new Value<>(
							SexPositionSlot.HAND_SEX_SUB_ROSE,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.HAND_SEX_DOM_ROSE,
									new SexActionInteractions(
											null,
											STANDING.getSexInteractions(SexPositionSlot.STANDING_SUBMISSIVE, SexPositionSlot.STANDING_DOMINANT).getAvailableCumTargets())))))) {
		@Override
		public String getDescription() {
			return "You and the cat-girl maid, Rose, are standing facing one another, ready to perform lewd acts with one another's hands.";
		}
	},
	
	MISSIONARY_DESK_VICKY("Missionary on counter",
			true,
			false,
			Util.newArrayListOfValues(VickyDominating.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.MISSIONARY_DESK_DOM_VICKY,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_DESK_SUB_VICKY,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas,
													SexActionPresets.groinToGroin,
													SexActionPresets.groinToAss,
													SexActionPresets.kissing,
													SexActionPresets.mouthToBreasts,
													SexActionPresets.breastsToMouth),
											Util.newArrayListOfValues(
													OrgasmCumTarget.HAIR,
													OrgasmCumTarget.FACE,
													OrgasmCumTarget.BREASTS,
													OrgasmCumTarget.STOMACH,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.LEGS,
													OrgasmCumTarget.FEET,
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.MISSIONARY_DESK_SUB_VICKY,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_DESK_DOM_VICKY,
									new SexActionInteractions(
											SexActionPresets.appendagesToAllAreas,
											Util.newArrayListOfValues(
														OrgasmCumTarget.SELF_STOMACH,
														OrgasmCumTarget.SELF_BREASTS,
														OrgasmCumTarget.SELF_FACE,
														OrgasmCumTarget.GROIN,
														OrgasmCumTarget.LEGS,
														OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {
			return "You're lying back on top of Arcane Arts' front desk, with the aggressive owner, Vicky, standing between your [pc.legs]."
					+ " She lets out a menacing growl as she steps forwards, preparing to fuck you in the missionary position.";
		}
	},
	
	OVER_TABLE_KRUGER_THREESOME("Coffee table threesome", //TODO
			true,
			false,
			Util.newArrayListOfValues(DoggyStyle.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.DOGGY_ON_ALL_FOURS,
							Util.newHashMapOfValues(
								new Value<>(
										SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToAllAreas,
														SexActionPresets.tentacleToAllAreas,
														SexActionPresets.kissing),
												DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_ON_ALL_FOURS, SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND).getAvailableCumTargets())),
								new Value<>(
										SexPositionSlot.DOGGY_BEHIND,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToAllAreas,
														SexActionPresets.tentacleToAllAreas,
														SexActionPresets.vaginaToPenis,
														SexActionPresets.assToPenis),
												DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_ON_ALL_FOURS, SexPositionSlot.DOGGY_BEHIND).getAvailableCumTargets())),
								new Value<>(
										SexPositionSlot.DOGGY_BEHIND_ORAL,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToUpperTorso,
														SexActionPresets.tentacleToUpperTorso,
														SexActionPresets.vaginaToMouth,
														SexActionPresets.assToMouth,
														SexActionPresets.penisToMouth),
												DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_ON_ALL_FOURS, SexPositionSlot.DOGGY_BEHIND_ORAL).getAvailableCumTargets())),
								new Value<>(
										SexPositionSlot.DOGGY_INFRONT,
										new SexActionInteractions(null,
												DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_ON_ALL_FOURS, SexPositionSlot.DOGGY_INFRONT).getAvailableCumTargets())),
								new Value<>(
										SexPositionSlot.DOGGY_INFRONT_ANAL,
										new SexActionInteractions(null,
												DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_ON_ALL_FOURS, SexPositionSlot.DOGGY_INFRONT_ANAL).getAvailableCumTargets())))),
					new Value<>(
							SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
							Util.newHashMapOfValues(
									new Value<>(
											SexPositionSlot.DOGGY_ON_ALL_FOURS,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToAllAreas,
															SexActionPresets.tentacleToAllAreas,
															SexActionPresets.kissing),
													DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND, SexPositionSlot.DOGGY_ON_ALL_FOURS).getAvailableCumTargets())),
									new Value<>(
											SexPositionSlot.DOGGY_BEHIND,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToAllAreas,
															SexActionPresets.tentacleToAllAreas,
															SexActionPresets.vaginaToPenis,
															SexActionPresets.assToPenis),
													DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND, SexPositionSlot.DOGGY_BEHIND).getAvailableCumTargets())),
									new Value<>(
											SexPositionSlot.DOGGY_BEHIND_ORAL,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToUpperTorso,
															SexActionPresets.tentacleToUpperTorso,
															SexActionPresets.vaginaToMouth,
															SexActionPresets.assToMouth,
															SexActionPresets.penisToMouth),
													DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND, SexPositionSlot.DOGGY_BEHIND_ORAL).getAvailableCumTargets())),
									new Value<>(
											SexPositionSlot.DOGGY_INFRONT,
											new SexActionInteractions(null,
													DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND, SexPositionSlot.DOGGY_INFRONT).getAvailableCumTargets())),
									new Value<>(
											SexPositionSlot.DOGGY_INFRONT_ANAL,
											new SexActionInteractions(null,
													DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND, SexPositionSlot.DOGGY_INFRONT_ANAL).getAvailableCumTargets())))),
					new Value<>(
							SexPositionSlot.DOGGY_BEHIND,
							Util.newHashMapOfValues(
								new Value<>(
									SexPositionSlot.DOGGY_INFRONT,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.fingerToUpperTorso,
														SexActionPresets.kissing),
												DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_BEHIND, SexPositionSlot.DOGGY_INFRONT).getAvailableCumTargets())),
								new Value<>(
										SexPositionSlot.DOGGY_INFRONT_ANAL,
										new SexActionInteractions(null,
												DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_BEHIND, SexPositionSlot.DOGGY_INFRONT_ANAL).getAvailableCumTargets())),
								new Value<>(
										SexPositionSlot.DOGGY_ON_ALL_FOURS,
										new SexActionInteractions(
												SexActionPresets.appendagesToAllAreas,
												DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_BEHIND, SexPositionSlot.DOGGY_ON_ALL_FOURS).getAvailableCumTargets())),
								new Value<>(
										SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
										new SexActionInteractions(
												SexActionPresets.appendagesToAllAreas,
												DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_BEHIND, SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND).getAvailableCumTargets())))),
					new Value<>(
							SexPositionSlot.DOGGY_BEHIND_ORAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionInteractions(
											SexActionPresets.fingerToLowerHalf,
											DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_BEHIND_ORAL, SexPositionSlot.DOGGY_ON_ALL_FOURS).getAvailableCumTargets())),
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
									new SexActionInteractions(
											SexActionPresets.fingerToLowerHalf,
											DOGGY_STYLE.getSexInteractions(SexPositionSlot.DOGGY_BEHIND_ORAL, SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND).getAvailableCumTargets())))))) {
		@Override
		public String getDescription() {
			return "You're lying back on top of Arcane Arts' front desk, with the aggressive owner, Vicky, standing between your [pc.legs]."
					+ " She lets out a menacing growl as she steps forwards, preparing to fuck you in the missionary position.";
		}
	},
	
	KNEELING_ORAL_CULTIST("Kneeling",
			true,
			false,
			Util.newArrayListOfValues(CultistSexActions.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.KNEELING_RECEIVING_ORAL_CULTIST,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_PERFORMING_ORAL_CULTIST,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.vaginaToMouth,
													SexActionPresets.penisToMouth),
											KNEELING_ORAL.getSexInteractions(SexPositionSlot.KNEELING_RECEIVING_ORAL, SexPositionSlot.KNEELING_PERFORMING_ORAL).getAvailableCumTargets())))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL_CULTIST,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_RECEIVING_ORAL_CULTIST,
									new SexActionInteractions(
											SexActionPresets.fingerToLowerHalf,
											KNEELING_ORAL.getSexInteractions(SexPositionSlot.KNEELING_PERFORMING_ORAL, SexPositionSlot.KNEELING_RECEIVING_ORAL).getAvailableCumTargets())))))) {
		@Override
		public String getDescription() {
			return "You're kneeling at the feet of [npc.name], with your [pc.face+] hovering just [unit.sizes] away from [npc.her] groin.";
		}
	},
	
	MISSIONARY_ALTAR_CULTIST("Missionary on altar",
			true,
			false,
			Util.newArrayListOfValues(CultistSexActions.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.MISSIONARY_ALTAR_LYING_ON_ALTAR,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.tailToAllAreas,
											SexActionPresets.tentacleToAllAreas),
										Util.newArrayListOfValues(
												OrgasmCumTarget.SELF_STOMACH,
												OrgasmCumTarget.SELF_BREASTS,
												OrgasmCumTarget.SELF_FACE,
												OrgasmCumTarget.GROIN,
												OrgasmCumTarget.LEGS,
												OrgasmCumTarget.FLOOR))),
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.vaginaToMouth,
													SexActionPresets.penisToMouth,
													SexActionPresets.assToMouth),
											Util.newArrayListOfValues(
													OrgasmCumTarget.SELF_STOMACH,
													OrgasmCumTarget.SELF_BREASTS,
													OrgasmCumTarget.SELF_FACE,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.LEGS,
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_LYING_ON_ALTAR,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas,
													SexActionPresets.groinToVagina,
													SexActionPresets.groinToAss,
													SexActionPresets.kissing,
													SexActionPresets.mouthToBreasts,
													SexActionPresets.breastsToMouth),
											Util.newArrayListOfValues(
													OrgasmCumTarget.HAIR,
													OrgasmCumTarget.FACE,
													OrgasmCumTarget.BREASTS,
													OrgasmCumTarget.STOMACH,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.LEGS,
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_LYING_ON_ALTAR,
									new SexActionInteractions(
											SexActionPresets.fingerToLowerHalf,
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {//TODO
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.MISSIONARY_ALTAR_LYING_ON_ALTAR) {
				if(Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS) {
					return "You're lying back on top of the chapel's altar, and [npc.namePos] standing between your [pc.legs], ready to have some fun with you in the missionary position.";
				} else {
					return "You're lying back on top of the chapel's altar, and [npc.namePos] kneeling down between your [pc.legs], ready to have some oral fun with you in the missionary position.";
				}
				
			} else if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS) {
				return "[npc.Name] is lying back on top of the chapel's altar, and you're standing between [npc.her] [npc.legs], ready to have some fun in the missionary position.";
				
			} else {
				return "[npc.Name] is lying back on top of the chapel's altar, and you're kneeling down between [npc.her] [npc.legs], ready to have some oral fun in the missionary position.";
			}
		}
	},
	
	MISSIONARY_ALTAR_SEALED_CULTIST("Missionary on altar",
			true,
			false,
			Util.newArrayListOfValues(CultistSexActions.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.tailToAllAreas,
											SexActionPresets.tentacleToAllAreas),
										Util.newArrayListOfValues(
												OrgasmCumTarget.SELF_STOMACH,
												OrgasmCumTarget.SELF_BREASTS,
												OrgasmCumTarget.SELF_FACE,
												OrgasmCumTarget.GROIN,
												OrgasmCumTarget.LEGS,
												OrgasmCumTarget.FLOOR))),
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.vaginaToMouth,
													SexActionPresets.penisToMouth,
													SexActionPresets.assToMouth),
											Util.newArrayListOfValues(
													OrgasmCumTarget.SELF_STOMACH,
													OrgasmCumTarget.SELF_BREASTS,
													OrgasmCumTarget.SELF_FACE,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.LEGS,
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas,
													SexActionPresets.groinToVagina,
													SexActionPresets.groinToAss,
													SexActionPresets.kissing,
													SexActionPresets.mouthToBreasts,
													SexActionPresets.breastsToMouth),
											Util.newArrayListOfValues(
													OrgasmCumTarget.HAIR,
													OrgasmCumTarget.FACE,
													OrgasmCumTarget.BREASTS,
													OrgasmCumTarget.STOMACH,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.LEGS,
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR,
									new SexActionInteractions(
											SexActionPresets.fingerToLowerHalf,
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {//TODO
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR) {
				return "You're lying back on top of the chapel's altar, and [npc.namePos] standing between your [pc.legs], ready to have some fun with you in the missionary position.";
			} else if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS) {
				return "[npc.Name] is lying back on top of the chapel's altar, and you're standing between [npc.her] [npc.legs], ready to have some fun in the missionary position.";
			} else {
				return "[npc.Name] is lying back on top of the chapel's altar, and you're kneeling down between [npc.her] [npc.legs], ready to have some oral fun in the missionary position.";
			}
		}
		
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			
			if(Sex.getSexPositionSlot(performer) == SexPositionSlot.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR) {
				if((action.getActionType()==SexActionType.ONGOING
						|| action.getActionType()==SexActionType.START_ONGOING
						|| action.getActionType()==SexActionType.REQUIRES_NO_PENETRATION
						|| action.getActionType()==SexActionType.REQUIRES_EXPOSED
						|| action.getActionType()==SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED)) {
					return true;
				}
			}
			
			return super.isActionBlocked(performer, target, action);
		}
	},
	
	BREEDING_STALL_FRONT("Breeding Stall",
			true,
			false,
			Util.newArrayListOfValues(BreedingStallFront.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.BREEDING_STALL_FRONT,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.BREEDING_STALL_FUCKING,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToAllAreas,
													SexActionPresets.tentacleToAllAreas),
											Util.newArrayListOfValues(
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.BREEDING_STALL_FUCKING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.BREEDING_STALL_FRONT,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToLowerHalf,
													SexActionPresets.groinToGroin,
													SexActionPresets.groinToAss),
											Util.newArrayListOfValues(
													OrgasmCumTarget.ASS,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.LEGS,
													OrgasmCumTarget.FEET,
													OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.BREEDING_STALL_FRONT) {
				return "You're lying on your front on the padded bench, with your legs and lower abdomen projecting out of the hole in the wall, exposing your pussy to the breeders beyond."
						+ (Main.game.getPlayer().hasTail()
								?" As you get into position, someone on the other side of the wall fastens your "
									+(Main.game.getPlayer().getTailCount()>1?"[pc.tailCount] [pc.tails] to the wall by means of metal clamps":" [pc.tail] to the wall by means of a metal clamp")
									+ ", in order to prevent you from using"+(Main.game.getPlayer().getTailCount()>1?"them":"it")+" to block your [pc.pussy+]."
								:"");
			} else {
				GameCharacter character = Sex.getCharacterInPosition(SexPositionSlot.BREEDING_STALL_FRONT);
				if(character!=null) {
					return "[npc.Name] is lying on [npc.her] front on the padded bench, with [npc.her] legs and lower abdomen projecting out of the hole in the wall. [npc.Her] pussy is completely exposed to you, ready for breeding."
							+ (character.hasTail()
									?" As [npc.she] gets into position, Epona steps forwards and fastens [npc.her] "
										+(character.getTailCount()>1?"[npc.tailCount] [npc.tails] to the wall by means of metal clamps":" [npc.tail] to the wall by means of a metal clamp")
										+ ", in order to prevent [npc.herHim] from using"+(character.getTailCount()>1?"them":"it")+" to block [npc.her] [npc.pussy+]."
									:"");
				} else {
					return "";
				}
			}
		}

		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			if((Sex.getSexPositionSlot(performer)==SexPositionSlot.BREEDING_STALL_FRONT
					&& action.getSexAreaInteractions().keySet().contains(SexAreaPenetration.TAIL))
				|| (Sex.getSexPositionSlot(performer)==SexPositionSlot.BREEDING_STALL_FUCKING
						&& action.getSexAreaInteractions().values().contains(SexAreaPenetration.TAIL)
						&& action.getParticipantType()!=SexParticipantType.SELF)) {
				return true;
			}
			
			return super.isActionBlocked(performer, target, action);
		}
	},
	
	BREEDING_STALL_BACK("Breeding Stall",
			true,
			false,
			Util.newArrayListOfValues(BreedingStallBack.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.BREEDING_STALL_BACK,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.BREEDING_STALL_FUCKING,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToAllAreas,
													SexActionPresets.tentacleToAllAreas),
											Util.newArrayListOfValues(
													OrgasmCumTarget.SELF_STOMACH,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.STOMACH,
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.BREEDING_STALL_FUCKING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.BREEDING_STALL_BACK,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToLowerHalf,
													SexActionPresets.groinToGroin,
													SexActionPresets.groinToAss),
											Util.newArrayListOfValues(
													OrgasmCumTarget.ASS,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.LEGS,
													OrgasmCumTarget.FEET,
													OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.BREEDING_STALL_FRONT) {
				return "You're lying on your back on the padded bench, with your legs and lower abdomen projecting out of the hole in the wall, exposing your pussy to the breeders beyond."
						+ (Main.game.getPlayer().hasTail()
								?" As you get into position, someone on the other side of the wall fastens your "
									+(Main.game.getPlayer().getTailCount()>1?"[pc.tailCount] [pc.tails] to the wall by means of metal clamps":" [pc.tail] to the wall by means of a metal clamp")
									+ ", in order to prevent you from using"+(Main.game.getPlayer().getTailCount()>1?"them":"it")+" to block your [pc.pussy+]."
								:"");
			} else {
				GameCharacter character = Sex.getCharacterInPosition(SexPositionSlot.BREEDING_STALL_FRONT);
				if(character!=null) {
					return "[npc.Name] is lying on [npc.her] back on the padded bench, with [npc.her] legs and lower abdomen projecting out of the hole in the wall. [npc.Her] pussy is completely exposed to you, ready for breeding."
							+ (character.hasTail()
									?" As [npc.she] gets into position, Epona steps forwards and fastens [npc.her] "
										+(character.getTailCount()>1?"[npc.tailCount] [npc.tails] to the wall by means of metal clamps":" [npc.tail] to the wall by means of a metal clamp")
										+ ", in order to prevent [npc.herHim] from using"+(character.getTailCount()>1?"them":"it")+" to block [npc.her] [npc.pussy+]."
									:"");
				} else {
					return "";
				}
			}
		}

		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			if((Sex.getSexPositionSlot(performer)==SexPositionSlot.BREEDING_STALL_BACK
					&& action.getSexAreaInteractions().keySet().contains(SexAreaPenetration.TAIL))
				|| (Sex.getSexPositionSlot(performer)==SexPositionSlot.BREEDING_STALL_FUCKING
						&& action.getSexAreaInteractions().values().contains(SexAreaPenetration.TAIL)
						&& action.getParticipantType()!=SexParticipantType.SELF)) {
				return true;
			}
			
			return super.isActionBlocked(performer, target, action);
		}
	},
	
	STANDING_STALL("Standing",
			true,
			false,
			Util.newArrayListOfValues(ToiletStall.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.STANDING_DOMINANT,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.STANDING_SUBMISSIVE,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas,
													SexActionPresets.kissing,
													SexActionPresets.mouthToBreasts,
													SexActionPresets.breastsToMouth),
											STANDING.getSexInteractions(SexPositionSlot.STANDING_DOMINANT, SexPositionSlot.STANDING_SUBMISSIVE).getAvailableCumTargets())))),
					new Value<>(
							SexPositionSlot.STANDING_SUBMISSIVE,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.STANDING_DOMINANT,
									new SexActionInteractions(
												SexActionPresets.appendagesToAllAreas,
												STANDING.getSexInteractions(SexPositionSlot.STANDING_SUBMISSIVE, SexPositionSlot.STANDING_DOMINANT).getAvailableCumTargets())))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.STANDING_DOMINANT), Sex.getCharacterInPosition(SexPositionSlot.STANDING_SUBMISSIVE),
					"[npc.NameIsFull] standing face-to-face with [npc2.name] in one of the toilet's stalls.");
		}
	},
	
	BACK_TO_WALL_STALL("Back-to-wall",
			true,
			false,
			Util.newArrayListOfValues(ToiletStall.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.BACK_TO_WALL_AGAINST_WALL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.BACK_TO_WALL_FACING_TARGET,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas,
													SexActionPresets.groinToGroin,
													SexActionPresets.kissing,
													SexActionPresets.mouthToBreasts,
													SexActionPresets.breastsToMouth),
											BACK_TO_WALL.getSexInteractions(SexPositionSlot.BACK_TO_WALL_AGAINST_WALL, SexPositionSlot.BACK_TO_WALL_FACING_TARGET).getAvailableCumTargets())))),
					new Value<>(
							SexPositionSlot.BACK_TO_WALL_FACING_TARGET,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.BACK_TO_WALL_AGAINST_WALL,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas),
											BACK_TO_WALL.getSexInteractions(SexPositionSlot.BACK_TO_WALL_FACING_TARGET, SexPositionSlot.BACK_TO_WALL_AGAINST_WALL).getAvailableCumTargets())))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.BACK_TO_WALL_AGAINST_WALL), Sex.getCharacterInPosition(SexPositionSlot.BACK_TO_WALL_FACING_TARGET),
					"[npc2.NameIsFull] pinning [npc1.name] back against the wall of the toilet, ready to step forwards and start having some fun...");
		}
	},
	
	FACING_WALL_STALL("Facing wall",
			true,
			false,
			Util.newArrayListOfValues(ToiletStall.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.FACE_TO_WALL_AGAINST_WALL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.FACE_TO_WALL_FACING_TARGET,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToAllAreas,
													SexActionPresets.tentacleToAllAreas,
													SexActionPresets.vaginaToPenis,
													SexActionPresets.assToPenis,
													SexActionPresets.kissing),
											FACING_WALL.getSexInteractions(SexPositionSlot.FACE_TO_WALL_AGAINST_WALL, SexPositionSlot.FACE_TO_WALL_FACING_TARGET).getAvailableCumTargets())))),
					new Value<>(
							SexPositionSlot.FACE_TO_WALL_FACING_TARGET,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.FACE_TO_WALL_AGAINST_WALL,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas),
											FACING_WALL.getSexInteractions(SexPositionSlot.FACE_TO_WALL_FACING_TARGET, SexPositionSlot.FACE_TO_WALL_AGAINST_WALL).getAvailableCumTargets())))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.FACE_TO_WALL_AGAINST_WALL), Sex.getCharacterInPosition(SexPositionSlot.FACE_TO_WALL_FACING_TARGET),
					"[npc2.NameIsFull] pinning [npc1.name] up against the wall of the toilet, ready to step forwards and start having some fun...");
		}
	},
	
	KNEELING_ORAL_STALL("Kneeling",
			true,
			false,
			Util.newArrayListOfValues(ToiletStall.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.KNEELING_RECEIVING_ORAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_PERFORMING_ORAL,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.tailToUpperTorso,
											SexActionPresets.tentacleToUpperTorso,
											SexActionPresets.vaginaToMouth,
											SexActionPresets.penisToMouth,
											SexActionPresets.penisToBreasts),
										KNEELING_ORAL.getSexInteractions(SexPositionSlot.KNEELING_RECEIVING_ORAL, SexPositionSlot.KNEELING_PERFORMING_ORAL).getAvailableCumTargets())))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_RECEIVING_ORAL,
									new SexActionInteractions(
											Util.mergeMaps(
												SexActionPresets.fingerToLowerHalf,
												SexActionPresets.penisToFeet),
											KNEELING_ORAL.getSexInteractions(SexPositionSlot.KNEELING_PERFORMING_ORAL, SexPositionSlot.KNEELING_RECEIVING_ORAL).getAvailableCumTargets())))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL), Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL),
					"[npc2.NameIsFull] kneeling on the floor of the toilet in front of [npc.name], with [npc2.her] [npc2.face+] hovering just [unit.sizes] away from [npc.her] groin.");
		}
	},
	
	GLORY_HOLE("Glory hole oral",
			true,
			false,
			Util.newArrayListOfValues(GloryHole.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.GLORY_HOLE_RECEIVING_ORAL_ONE,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.GLORY_HOLE_KNEELING,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.vaginaToMouth,
											SexActionPresets.penisToMouth),
										Util.newArrayListOfValues(
												OrgasmCumTarget.BREASTS,
												OrgasmCumTarget.HAIR,
												OrgasmCumTarget.FACE,
												OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.GLORY_HOLE_RECEIVING_ORAL_TWO,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.GLORY_HOLE_KNEELING,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.vaginaToMouth,
											SexActionPresets.penisToMouth),
										Util.newArrayListOfValues(
												OrgasmCumTarget.BREASTS,
												OrgasmCumTarget.HAIR,
												OrgasmCumTarget.FACE,
												OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.GLORY_HOLE_KNEELING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.GLORY_HOLE_RECEIVING_ORAL_ONE,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.fingerToPenis,
											SexActionPresets.fingerToVagina),
										Util.newArrayListOfValues(
												OrgasmCumTarget.SELF_STOMACH,
												OrgasmCumTarget.SELF_GROIN,
												OrgasmCumTarget.SELF_LEGS,
												OrgasmCumTarget.FLOOR))),
							new Value<>(
									SexPositionSlot.GLORY_HOLE_RECEIVING_ORAL_TWO,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.fingerToPenis,
											SexActionPresets.fingerToVagina),
										Util.newArrayListOfValues(
												OrgasmCumTarget.SELF_STOMACH,
												OrgasmCumTarget.SELF_GROIN,
												OrgasmCumTarget.SELF_LEGS,
												OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {
			List<GameCharacter> characters = new ArrayList<>();
			characters.add(Sex.getCharacterInPosition(SexPositionSlot.GLORY_HOLE_KNEELING));
			characters.add(Sex.getCharacterInPosition(SexPositionSlot.GLORY_HOLE_RECEIVING_ORAL_ONE));
			
			if(Sex.getTotalParticipantCount(false)==3) {
				characters.add(Sex.getCharacterInPosition(SexPositionSlot.GLORY_HOLE_RECEIVING_ORAL_TWO));
				return UtilText.parse(characters,
						"[npc.NameIsFull] kneeling on the floor of the toilet, ready to serve [npc2.namePos] [npc2.cock+] on one side, an [npc3.namePos] [npc3.cock+] on the other.");
			} else {
				return UtilText.parse(characters,
						"[npc.NameIsFull] kneeling on the floor of the toilet, [npc.her] mouth up against the glory hole in preparation to serve whatever set of genitals [npc2.name] [npc2.has].");
			}
		}
	},
	
	GLORY_HOLE_SEX("Glory hole sex",
			true,
			false,
			Util.newArrayListOfValues(GloryHole.class), Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.GLORY_HOLE_RECEIVING_ORAL_ONE,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.GLORY_HOLE_FUCKED,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.vaginaToMouth,
											SexActionPresets.penisToMouth),
										Util.newArrayListOfValues(
												OrgasmCumTarget.BREASTS,
												OrgasmCumTarget.HAIR,
												OrgasmCumTarget.FACE,
												OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.GLORY_HOLE_FUCKING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.GLORY_HOLE_FUCKED,
									new SexActionInteractions(
											SexActionPresets.penisToVagina,
											Util.newArrayListOfValues(
													OrgasmCumTarget.ASS,
													OrgasmCumTarget.GROIN,
													OrgasmCumTarget.LEGS,
													OrgasmCumTarget.FLOOR))))),
					new Value<>(
							SexPositionSlot.GLORY_HOLE_FUCKED,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.GLORY_HOLE_RECEIVING_ORAL_ONE,
									new SexActionInteractions(
											Util.mergeMaps(
												SexActionPresets.fingerToPenis,
												SexActionPresets.fingerToVagina),
											Util.newArrayListOfValues(
													OrgasmCumTarget.SELF_LEGS,
													OrgasmCumTarget.FLOOR))),
							new Value<>(
									SexPositionSlot.GLORY_HOLE_FUCKING,
									new SexActionInteractions(
											SexActionPresets.anusToPenis,
											Util.newArrayListOfValues(
													OrgasmCumTarget.SELF_LEGS,
													OrgasmCumTarget.FLOOR))))))) {
		@Override
		public String getDescription() {
			List<GameCharacter> characters = new ArrayList<>();
			characters.add(Sex.getCharacterInPosition(SexPositionSlot.GLORY_HOLE_FUCKED));
			characters.add(Sex.getCharacterInPosition(SexPositionSlot.GLORY_HOLE_FUCKING));

			if(Sex.getTotalParticipantCount(false)==3) {
				characters.add(Sex.getCharacterInPosition(SexPositionSlot.GLORY_HOLE_RECEIVING_ORAL_ONE));
				return UtilText.parse(characters,
						"[npc.NameIsFull] pressing [npc.her] [npc.ass+] up against [npc2.namePos] glory hole, ready to get penetrated by [npc2.her] [npc2.cock+],"
								+ " while bringing [npc.her] mouth down to [npc3.namePos] [npc3.cock+] on the other side of the narrow toilet stall.");
				
			} else {
				return UtilText.parse(characters,
						"[npc.NameIsFull] pressing [npc.her] [npc.ass+] up against [npc2.namePos] glory hole, ready to get penetrated by [npc2.her] [npc2.cock+].");
			}
		}
	},
	;
	
	private String name;
	private boolean addStandardActions;
	private boolean addStandardPositioning;
	
	/**Key is role position. Value is list of all slots that this slot can interact with.*/
	private Map<SexPositionSlot, Map<SexPositionSlot, SexActionInteractions>> slotTargets;
	
	private List<Class<?>> specialClasses;
	
	private SexPositionType(String name,
			boolean addStandardActions,
			boolean addStandardPositioning,
			List<Class<?>> specialClasses,
			Map<SexPositionSlot, Map<SexPositionSlot, SexActionInteractions>> slotTargets) {
		this.name = name;
		this.addStandardActions = addStandardActions;
		this.addStandardPositioning = addStandardPositioning;
		this.specialClasses = specialClasses;
		this.slotTargets = slotTargets;

		List<SexPositionSlot> slots = new ArrayList<>(this.slotTargets.keySet());
		
		for(SexPositionSlot slot : slots) {
			this.slotTargets.get(slot).put(SexPositionSlot.MISC_WATCHING,
					new SexActionInteractions(
							null,
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_FEET,
								OrgasmCumTarget.FLOOR)));
		}
		this.slotTargets.put(SexPositionSlot.MISC_WATCHING, new HashMap<>());
		for(SexPositionSlot slot : slots) {
			this.slotTargets.get(SexPositionSlot.MISC_WATCHING).put(slot,
					new SexActionInteractions(
							null,
							Util.newArrayListOfValues(
									OrgasmCumTarget.SELF_STOMACH,
									OrgasmCumTarget.SELF_GROIN,
									OrgasmCumTarget.SELF_LEGS,
									OrgasmCumTarget.SELF_FEET,
									OrgasmCumTarget.FLOOR)));
		}
	}
	
	public String getName() {
		return name;
	}

	public boolean isAddStandardActions() {
		return addStandardActions;
	}

	public boolean isAddStandardPositioning() {
		return addStandardPositioning;
	}

	public abstract String getDescription();
	
	public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
		if(action.getActionType()==SexActionType.START_ONGOING) {
			// Block penis+non-appendage actions if target's penis is already in use:
			if(this!=SexPositionType.SIXTY_NINE) {
				if(action.getSexAreaInteractions().containsKey(SexAreaPenetration.PENIS)
						&& Collections.disjoint(action.getSexAreaInteractions().values(), SexActionPresets.appendageAreas)
						&& Sex.getOngoingActionsMap(target).containsKey(SexAreaPenetration.PENIS)
						&& Sex.getOngoingActionsMap(target).get(SexAreaPenetration.PENIS).containsKey(performer)
						&& Collections.disjoint(Sex.getOngoingActionsMap(target).get(SexAreaPenetration.PENIS).get(performer), SexActionPresets.appendageAreas)) {
					return true;
				}
				if(action.getSexAreaInteractions().containsValue(SexAreaPenetration.PENIS)
						&& Collections.disjoint(action.getSexAreaInteractions().keySet(), SexActionPresets.appendageAreas)
						&& Sex.getOngoingActionsMap(performer).containsKey(SexAreaPenetration.PENIS)
						&& Sex.getOngoingActionsMap(performer).get(SexAreaPenetration.PENIS).containsKey(target)
						&& Collections.disjoint(Sex.getOngoingActionsMap(performer).get(SexAreaPenetration.PENIS).get(target), SexActionPresets.appendageAreas)) {
					return true;
				}
			}
			
			// Block oral + groin actions if there is any groin action going on:
			if(this!=SexPositionType.SIXTY_NINE
					&& ((!Collections.disjoint(action.getSexAreaInteractions().keySet(), SexActionPresets.groinAreas)
							&& !Collections.disjoint(action.getSexAreaInteractions().values(), SexActionPresets.mouthAreas))
						|| (!Collections.disjoint(action.getSexAreaInteractions().values(), SexActionPresets.groinAreas)
							&& !Collections.disjoint(action.getSexAreaInteractions().keySet(), SexActionPresets.mouthAreas)))) {
				for(SexAreaInterface sArea : SexActionPresets.groinAreas) {
					if((Sex.getOngoingActionsMap(target).containsKey(sArea)
							&& Sex.getOngoingActionsMap(target).get(sArea).containsKey(performer))
						|| (Sex.getOngoingActionsMap(performer).containsKey(sArea)
							&& Sex.getOngoingActionsMap(performer).get(sArea).containsKey(target))) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public int getMaximumSlots() {
		return slotTargets.size()-1; //-1 to remove watching slot
	}

	public Set<SexPositionSlot> getAllAvailableSexPositions() {
		return slotTargets.keySet();
	}
	
	public SexActionInteractions getSexInteractions(SexPositionSlot performer, SexPositionSlot target) {
		if(slotTargets.get(performer).containsKey(target)) {
			return slotTargets.get(performer).get(target);
		}
		
		// If the targeted sex position is not defined, allow cumming on floor:
		return new SexActionInteractions(null, Util.newArrayListOfValues(OrgasmCumTarget.FLOOR));
	}

	public List<Class<?>> getSpecialClasses() {
		return specialClasses;
	}

}
