package com.lilithsthrone.game.sex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Each value holds a map, <i>slotTargets</i>, which maps SexPositionSlots to a map of SexPositionSlots, which in turn maps to [npc.verb(position)] available.
 *  By providing a character's position in sex, along with the position of the partner they're targeting, this map is used to fetch available actions.<br/><br/>
 *  
 *  <b>Example:</b><br/>
 *  <i>getSlotTargets().get(character1SexPositionSlot).get(character2SexPositionSlot)</i><br/>returns the <i>SexActionPresetPair</i> which holds all available actions.<br/><br/>
 *  
 *  If character1SexPositionSlot is SexPositionSlot.DOGGY_ON_ALL_FOURS, and character2SexPositionSlot is SexPositionSlot.DOGGY_BEHIND, then the returned actions would be those that
 *   are available for the character on all fours, in relation to a character kneeling behind them.
 * 
 * @since 0.1.97
 * @version 0.2.8
 * @author Innoxia
 */
public enum SexPositionType {
	
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
													SexActionPresets.breastsToMouth))))),
					new Value<>(
							SexPositionSlot.BACK_TO_WALL_FACING_TARGET,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.BACK_TO_WALL_AGAINST_WALL,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas))))))) {
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
													SexActionPresets.kissing))))),
					new Value<>(
							SexPositionSlot.FACE_TO_WALL_FACING_TARGET,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.FACE_TO_WALL_AGAINST_WALL,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas))))))) {
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
													SexActionPresets.breastsToMouth))))),
					new Value<>(
							SexPositionSlot.COWGIRL_RIDING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.COWGIRL_ON_BACK,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas,
													SexActionPresets.penisToBreasts))))))) {
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
//													SexActionPresets.penisToMouth,
													SexActionPresets.assToMouth))))),
					new Value<>(
							SexPositionSlot.FACE_SITTING_ON_BACK,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.FACE_SITTING_ON_FACE,
									new SexActionInteractions(
											Util.mergeMaps(
												SexActionPresets.fingerToLowerHalf,
												SexActionPresets.penisToFeet))))))) {
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
														SexActionPresets.kissing))),
								new Value<>(
										SexPositionSlot.DOGGY_BEHIND,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToAllAreas,
														SexActionPresets.tentacleToAllAreas,
														SexActionPresets.vaginaToPenis,
														SexActionPresets.assToPenis))),
								new Value<>(
										SexPositionSlot.DOGGY_BEHIND_ORAL,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToUpperTorso,
														SexActionPresets.tentacleToUpperTorso,
														SexActionPresets.vaginaToMouth,
														SexActionPresets.assToMouth,
														SexActionPresets.penisToMouth))),
								new Value<>(
										SexPositionSlot.DOGGY_INFRONT,
										new SexActionInteractions(null)),
								new Value<>(
										SexPositionSlot.DOGGY_INFRONT_ANAL,
										new SexActionInteractions(null)))),
					new Value<>(
							SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
							Util.newHashMapOfValues(
									new Value<>(
											SexPositionSlot.DOGGY_ON_ALL_FOURS,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToAllAreas,
															SexActionPresets.tentacleToAllAreas,
															SexActionPresets.kissing))),
									new Value<>(
											SexPositionSlot.DOGGY_BEHIND,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToAllAreas,
															SexActionPresets.tentacleToAllAreas,
															SexActionPresets.vaginaToPenis,
															SexActionPresets.assToPenis))),
									new Value<>(
											SexPositionSlot.DOGGY_BEHIND_ORAL,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToUpperTorso,
															SexActionPresets.tentacleToUpperTorso,
															SexActionPresets.vaginaToMouth,
															SexActionPresets.assToMouth,
															SexActionPresets.penisToMouth))),
									new Value<>(
											SexPositionSlot.DOGGY_INFRONT,
											new SexActionInteractions(null)),
									new Value<>(
											SexPositionSlot.DOGGY_INFRONT_ANAL,
											new SexActionInteractions(null)))),
					new Value<>(
							SexPositionSlot.DOGGY_BEHIND,
							Util.newHashMapOfValues(
								new Value<>(
									SexPositionSlot.DOGGY_INFRONT,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.fingerToUpperTorso,
														SexActionPresets.kissing))),
								new Value<>(
										SexPositionSlot.DOGGY_INFRONT_ANAL,
										new SexActionInteractions(null)),
								new Value<>(
										SexPositionSlot.DOGGY_ON_ALL_FOURS,
										new SexActionInteractions(
												SexActionPresets.appendagesToAllAreas)),
								new Value<>(
										SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
										new SexActionInteractions(
												SexActionPresets.appendagesToAllAreas)))),
					new Value<>(
							SexPositionSlot.DOGGY_BEHIND_ORAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionInteractions(
											SexActionPresets.fingerToLowerHalf)),
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
									new SexActionInteractions(
											SexActionPresets.fingerToLowerHalf)))),
					new Value<>(
							SexPositionSlot.DOGGY_INFRONT,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_BEHIND,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.fingerToUpperTorso,
													SexActionPresets.kissing))),
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.fingerToUpperTorso,
													SexActionPresets.vaginaToMouth,
													SexActionPresets.penisToMouth))),
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.fingerToUpperTorso,
													SexActionPresets.vaginaToMouth,
													SexActionPresets.penisToMouth))))),
					new Value<>(
							SexPositionSlot.DOGGY_INFRONT_ANAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.assToMouth))),
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.assToMouth))))))) {
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS),
					"[npc.NameIsFull] down on all fours, submissively presenting [npc.herself] as [npc.she] [npc.verb(prepare)] to get fucked in the doggy-style position."));

			boolean twoDoggies = false;
			if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND)!=null) {
				twoDoggies = true;
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS), Sex.getCharacterInPosition(SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND),
						" [npc2.NameIsFull] on all fours beside [npc.herHim], presenting [npc2.herself] in the same way."));
			}
			
			if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND)!=null) {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND),
						" [npc.NameIsFull] kneeling behind, ready to have some fun with the "+(twoDoggies?"two lots of ":"")+"goods presented to [npc.herHim]."));
			}
			
			if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND_ORAL)!=null) {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_BEHIND_ORAL),
						" [npc.NameIsFull] down on all fours behind, ready to have some oral fun with the "+(twoDoggies?"two lots of ":"")+"goods presented to [npc.herHim]."));
			}
			
			if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT)!=null) {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT),
						" [npc.NameIsFull] kneeling infront, ready to have some oral fun with the "+(twoDoggies?"two mouths ":"mouth ")+"on offer."));
			}
			
			if(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL)!=null) {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.DOGGY_INFRONT_ANAL),
						" [npc.NameIsFull] down on all fours, presenting [npc.her] [npc.ass+] to the "+(twoDoggies?"two mouths ":"mouth ")+"on offer."));
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
												SexActionPresets.penisToBreasts))))),
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
											SexActionPresets.penisToBreasts))))))) {
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
			Util.newArrayListOfValues(KneelingOral.class), Util.newHashMapOfValues(
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
											SexActionPresets.penisToBreasts))))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_RECEIVING_ORAL,
									new SexActionInteractions(
											Util.mergeMaps(
												SexActionPresets.fingerToLowerHalf,
												SexActionPresets.penisToFeet))))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL), Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL),
					"[npc2.NameIsFull] kneeling on the floor in front of [npc.name], with [npc2.her] [npc2.face+] hovering just inches away from [npc.her] groin.");
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
													SexActionPresets.penisToFeet))))),
					new Value<>(
							SexPositionSlot.MISSIONARY_ON_BACK,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas,
													SexActionPresets.groinToAss))))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS), Sex.getCharacterInPosition(SexPositionSlot.MISSIONARY_ON_BACK),
					"[npc.NameIsFull] kneeling between [npc2.namePos] [npc2.legs], looking down at [npc2.herHim] as [npc.she] [npc.verb(prepare)] to have sex in the missionary position.");
		}
	},
	
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
													SexActionPresets.breastsToMouth))))),
					new Value<>(
							SexPositionSlot.STANDING_SUBMISSIVE,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.STANDING_DOMINANT,
									new SexActionInteractions(
													SexActionPresets.appendagesToAllAreas)))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.STANDING_DOMINANT), Sex.getCharacterInPosition(SexPositionSlot.STANDING_SUBMISSIVE),
					"[npc.NameIsFull] standing face-to-face with [npc2.name].");
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
													SexActionPresets.breastsToMouth))))),
					new Value<>(
							SexPositionSlot.CHAIR_BOTTOM,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.CHAIR_TOP,
									new SexActionInteractions(
											SexActionPresets.appendagesToAllAreas)))))) {
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
													SexActionPresets.penisToFeet))))),
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
											SexActionPresets.assToMouth))))))) {
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
											null)))))) {
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
											null)))))) {
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
												null)),
								new Value<>(
										SexPositionSlot.STOCKS_PERFORMING_ORAL,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToUpperTorso,
														SexActionPresets.tentacleToUpperTorso,
														SexActionPresets.vaginaToMouth,
														SexActionPresets.penisToMouth,
														SexActionPresets.assToMouth))),
								new Value<>(
										SexPositionSlot.STOCKS_FUCKING,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToUpperTorso,
														SexActionPresets.tentacleToUpperTorso))))),
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
														SexActionPresets.penisToMouth))))),
					new Value<>(
							SexPositionSlot.STOCKS_PERFORMING_ORAL,
							Util.newHashMapOfValues(
								new Value<>(
										SexPositionSlot.STOCKS_LOCKED_IN_STOCKS,
										new SexActionInteractions(
												null)))),
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
														SexActionPresets.penisToThighs))))))) {
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
												null)),
								new Value<>(
										SexPositionSlot.MILKING_STALL_PERFORMING_ORAL,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToUpperTorso,
														SexActionPresets.tentacleToUpperTorso,
														SexActionPresets.vaginaToMouth,
														SexActionPresets.penisToMouth,
														SexActionPresets.assToMouth))),
								new Value<>(
										SexPositionSlot.MILKING_STALL_FUCKING,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToUpperTorso,
														SexActionPresets.tentacleToUpperTorso))))),
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
														SexActionPresets.penisToMouth))))),
					new Value<>(
							SexPositionSlot.MILKING_STALL_PERFORMING_ORAL,
							Util.newHashMapOfValues(
								new Value<>(
										SexPositionSlot.MILKING_STALL_LOCKED_IN_MILKING_STALL,
										new SexActionInteractions(
												null)))),
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
														SexActionPresets.penisToThighs))))))) {
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
													SexActionPresets.kissing))))),
					new Value<>(
							SexPositionSlot.PET_MOUNTING_HUMPING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.PET_MOUNTING_ON_ALL_FOURS,
									new SexActionInteractions(
											SexActionPresets.appendagesToAllAreas)))))) {
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
											SexActionPresets.fingerToLowerHalf)))),
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
											SexActionPresets.penisToMouth))))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.PET_ORAL_ON_ALL_FOURS), Sex.getCharacterInPosition(SexPositionSlot.PET_ORAL_COCKED_LEG),
					"[npc.NameIs] down on all fours, with [npc2.namePos] [npc2.leg] hooked over [npc.her] neck, leaving [npc.her] face just inches away from [npc2.namePos] [npc2.cock+].");
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
											SexActionPresets.penisToAss))))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL_RALPH,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_RECEIVING_ORAL_RALPH,
									new SexActionInteractions(
											Util.mergeMaps(
											SexActionPresets.fingerToLowerHalf,
											SexActionPresets.penisToFeet))))))) {
		@Override
		public String getDescription() {
			return "You're kneeling under Ralph's counter, with your face just inches away from his crotch.";
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
												SexActionPresets.kissing))))),
					new Value<>(
							SexPositionSlot.FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.FACE_TO_WALL_FACING_TARGET_SHOWER_PIX,
									new SexActionInteractions(
											SexActionPresets.appendagesToAllAreas)))))) {
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
													new Value<>(SexAreaOrifice.MOUTH, Util.newArrayListOfValues(SexAreaPenetration.FINGER))))))),
					new Value<>(
							SexPositionSlot.HAND_SEX_SUB_ROSE,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.HAND_SEX_DOM_ROSE,
									new SexActionInteractions(
											null)))))) {
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
													SexActionPresets.breastsToMouth))))),
					new Value<>(
							SexPositionSlot.MISSIONARY_DESK_SUB_VICKY,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_DESK_DOM_VICKY,
									new SexActionInteractions(
											SexActionPresets.appendagesToAllAreas)))))) {
		@Override
		public String getDescription() {
			return "You're lying back on top of Arcane Arts' front desk, with the aggressive owner, Vicky, standing between your [pc.legs]."
					+ " She lets out a menacing growl as she steps forwards, preparing to fuck you in the missionary position.";
		}
	},
	
	OVER_TABLE_KRUGER_THREESOME("Coffee table threesome",
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
														SexActionPresets.kissing))),
								new Value<>(
										SexPositionSlot.DOGGY_BEHIND,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToAllAreas,
														SexActionPresets.tentacleToAllAreas,
														SexActionPresets.vaginaToPenis,
														SexActionPresets.assToPenis))),
								new Value<>(
										SexPositionSlot.DOGGY_BEHIND_ORAL,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.tailToUpperTorso,
														SexActionPresets.tentacleToUpperTorso,
														SexActionPresets.vaginaToMouth,
														SexActionPresets.assToMouth,
														SexActionPresets.penisToMouth))),
								new Value<>(
										SexPositionSlot.DOGGY_INFRONT,
										new SexActionInteractions(null)),
								new Value<>(
										SexPositionSlot.DOGGY_INFRONT_ANAL,
										new SexActionInteractions(null)))),
					new Value<>(
							SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
							Util.newHashMapOfValues(
									new Value<>(
											SexPositionSlot.DOGGY_ON_ALL_FOURS,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToAllAreas,
															SexActionPresets.tentacleToAllAreas,
															SexActionPresets.kissing))),
									new Value<>(
											SexPositionSlot.DOGGY_BEHIND,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToAllAreas,
															SexActionPresets.tentacleToAllAreas,
															SexActionPresets.vaginaToPenis,
															SexActionPresets.assToPenis))),
									new Value<>(
											SexPositionSlot.DOGGY_BEHIND_ORAL,
											new SexActionInteractions(
													Util.mergeMaps(
															SexActionPresets.tailToUpperTorso,
															SexActionPresets.tentacleToUpperTorso,
															SexActionPresets.vaginaToMouth,
															SexActionPresets.assToMouth,
															SexActionPresets.penisToMouth))),
									new Value<>(
											SexPositionSlot.DOGGY_INFRONT,
											new SexActionInteractions(null)),
									new Value<>(
											SexPositionSlot.DOGGY_INFRONT_ANAL,
											new SexActionInteractions(null)))),
					new Value<>(
							SexPositionSlot.DOGGY_BEHIND,
							Util.newHashMapOfValues(
								new Value<>(
									SexPositionSlot.DOGGY_INFRONT,
										new SexActionInteractions(
												Util.mergeMaps(
														SexActionPresets.fingerToUpperTorso,
														SexActionPresets.kissing))),
								new Value<>(
										SexPositionSlot.DOGGY_INFRONT_ANAL,
										new SexActionInteractions(null)),
								new Value<>(
										SexPositionSlot.DOGGY_ON_ALL_FOURS,
										new SexActionInteractions(
												SexActionPresets.appendagesToAllAreas)),
								new Value<>(
										SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
										new SexActionInteractions(
												SexActionPresets.appendagesToAllAreas)))),
					new Value<>(
							SexPositionSlot.DOGGY_BEHIND_ORAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionInteractions(
											SexActionPresets.fingerToLowerHalf)),
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
									new SexActionInteractions(
											SexActionPresets.fingerToLowerHalf)))))) {
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
													SexActionPresets.penisToMouth))))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL_CULTIST,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_RECEIVING_ORAL_CULTIST,
									new SexActionInteractions(
											SexActionPresets.fingerToLowerHalf)))))) {
		@Override
		public String getDescription() {
			return "You're kneeling at the feet of [npc.name], with your [pc.face+] hovering just inches away from [npc.her] groin.";
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
											SexActionPresets.tentacleToAllAreas))),
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.vaginaToMouth,
													SexActionPresets.penisToMouth,
													SexActionPresets.assToMouth))))),
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
													SexActionPresets.breastsToMouth))))),
					new Value<>(
							SexPositionSlot.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_LYING_ON_ALTAR,
									new SexActionInteractions(
											SexActionPresets.fingerToLowerHalf)))))) {
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
											SexActionPresets.tentacleToAllAreas))),
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.tailToUpperTorso,
													SexActionPresets.tentacleToUpperTorso,
													SexActionPresets.vaginaToMouth,
													SexActionPresets.penisToMouth,
													SexActionPresets.assToMouth))))),
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
													SexActionPresets.breastsToMouth))))),
					new Value<>(
							SexPositionSlot.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR,
									new SexActionInteractions(
											SexActionPresets.fingerToLowerHalf)))))) {
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
													SexActionPresets.tentacleToAllAreas))))),
					new Value<>(
							SexPositionSlot.BREEDING_STALL_FUCKING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.BREEDING_STALL_FRONT,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToLowerHalf,
													SexActionPresets.groinToGroin,
													SexActionPresets.groinToAss))))))) {
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
													SexActionPresets.tentacleToAllAreas))))),
					new Value<>(
							SexPositionSlot.BREEDING_STALL_FUCKING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.BREEDING_STALL_BACK,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToLowerHalf,
													SexActionPresets.groinToGroin,
													SexActionPresets.groinToAss))))))) {
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
													SexActionPresets.breastsToMouth))))),
					new Value<>(
							SexPositionSlot.STANDING_SUBMISSIVE,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.STANDING_DOMINANT,
									new SexActionInteractions(
													SexActionPresets.appendagesToAllAreas)))))) {
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
													SexActionPresets.breastsToMouth))))),
					new Value<>(
							SexPositionSlot.BACK_TO_WALL_FACING_TARGET,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.BACK_TO_WALL_AGAINST_WALL,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas))))))) {
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
													SexActionPresets.kissing))))),
					new Value<>(
							SexPositionSlot.FACE_TO_WALL_FACING_TARGET,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.FACE_TO_WALL_AGAINST_WALL,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.appendagesToAllAreas))))))) {
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
											SexActionPresets.penisToBreasts))))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_RECEIVING_ORAL,
									new SexActionInteractions(
											Util.mergeMaps(
												SexActionPresets.fingerToLowerHalf,
												SexActionPresets.penisToFeet))))))) {
		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterInPosition(SexPositionSlot.KNEELING_RECEIVING_ORAL), Sex.getCharacterInPosition(SexPositionSlot.KNEELING_PERFORMING_ORAL),
					"[npc2.NameIsFull] kneeling on the floor of the toilet in front of [npc.name], with [npc2.her] [npc2.face+] hovering just inches away from [npc.her] groin.");
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
											SexActionPresets.penisToMouth))))),
					new Value<>(
							SexPositionSlot.GLORY_HOLE_RECEIVING_ORAL_TWO,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.GLORY_HOLE_KNEELING,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.vaginaToMouth,
											SexActionPresets.penisToMouth))))),
					new Value<>(
							SexPositionSlot.GLORY_HOLE_KNEELING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.GLORY_HOLE_RECEIVING_ORAL_ONE,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.fingerToPenis,
											SexActionPresets.fingerToVagina))),
							new Value<>(
									SexPositionSlot.GLORY_HOLE_RECEIVING_ORAL_TWO,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.fingerToPenis,
											SexActionPresets.fingerToVagina))))))) {
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
											SexActionPresets.penisToMouth))))),
					new Value<>(
							SexPositionSlot.GLORY_HOLE_FUCKING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.GLORY_HOLE_FUCKED,
									new SexActionInteractions(
										Util.mergeMaps(
											SexActionPresets.penisToVagina))))),
					new Value<>(
							SexPositionSlot.GLORY_HOLE_FUCKED,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.GLORY_HOLE_RECEIVING_ORAL_ONE,
									new SexActionInteractions(
											Util.mergeMaps(
												SexActionPresets.fingerToPenis,
												SexActionPresets.fingerToVagina))),
							new Value<>(
									SexPositionSlot.GLORY_HOLE_FUCKING,
									new SexActionInteractions(
											Util.mergeMaps(
													SexActionPresets.anusToPenis))))))) {
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
			this.slotTargets.get(slot).put(SexPositionSlot.MISC_WATCHING, new SexActionInteractions(null));
		}
		this.slotTargets.put(SexPositionSlot.MISC_WATCHING, new HashMap<>());
		for(SexPositionSlot slot : slots) {
			this.slotTargets.get(SexPositionSlot.MISC_WATCHING).put(slot, new SexActionInteractions(null));
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

	public Map<SexPositionSlot, Map<SexPositionSlot, SexActionInteractions>> getSlotTargets() {
		return slotTargets;
	}

	public List<Class<?>> getSpecialClasses() {
		return specialClasses;
	}

}
