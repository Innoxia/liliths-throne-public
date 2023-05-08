package com.lilithsthrone.game.sex.positions;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotBreedingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * A collection of standard SexActionInteractions classes.
 * 
 * @since 0.3.1
 * @version 0.3.4
 * @author Innoxia
 */
public class StandardSexActionInteractions {
	
	
	// Misc:
	
	public static SexActionInteractions spectator = new SexActionInteractions(
		null,
		Util.newArrayListOfValues(
				OrgasmCumTarget.SELF_STOMACH,
				OrgasmCumTarget.SELF_GROIN,
				OrgasmCumTarget.SELF_LEGS,
				OrgasmCumTarget.SELF_FEET,
				OrgasmCumTarget.SELF_HANDS,
				OrgasmCumTarget.SELF_FACE,
				OrgasmCumTarget.SELF_BREASTS,
				OrgasmCumTarget.FLOOR),
		null);
	
	public static VariableInteractions masturbation = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						null,
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_FEET,
								OrgasmCumTarget.SELF_HANDS,
								OrgasmCumTarget.SELF_FACE,
								OrgasmCumTarget.SELF_BREASTS,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues()))));
			}
	};
	
	
	
	// Sex:
	
	public static VariableInteractions faceToFace = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				GameCharacter target = getCharacter(targetSlot);
				
				if(performer.isSizeDifferenceShorterThan(target)) { // If significantly shorter, they're basically standing to perform oral.
					return performingOral.getSexActionInteractions(performerSlot, targetSlot);
				}
				if(target.isSizeDifferenceShorterThan(performer)) { // If significantly shorter, they're basically standing to perform oral.
					return performingOral.getSexActionInteractions(targetSlot, performerSlot);
				}
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								!target.isTaur() && !performer.isTaur()
									?SexActionPresets.groinToGroin
									:null,
								!target.isTaur()
									?SexActionPresets.appendagesToAllAreas
									:(target.getLegConfiguration().isBipedalPositionedCrotchBoobs()
											?SexActionPresets.appendagesToCrotchBoobs
											:null),
								!performer.isTaur()
									?SexActionPresets.allAreasToAppendages
									:(performer.getLegConfiguration().isBipedalPositionedCrotchBoobs()
											?SexActionPresets.crotchBoobsToAppendages
											:null),
								SexActionPresets.appendagesToUpperHalf,
								SexActionPresets.upperHalfToAppendages,
								SexActionPresets.kissing,
								SexActionPresets.mouthToBreasts,
								SexActionPresets.breastsToMouth),
						Util.newArrayListOfValues(
								OrgasmCumTarget.STOMACH,
								!target.isTaur() && !performer.isTaur()
									?OrgasmCumTarget.GROIN
									:null,
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.STOMACH,
								!target.isTaur() && !performer.isTaur()
									?OrgasmCumTarget.GROIN
									:null,
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FLOOR)))));
			}
	};

	public static VariableInteractions kneelingBeside = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				GameCharacter target = getCharacter(targetSlot);
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								!target.isTaur()
									?SexActionPresets.appendagesToAllAreas
									:(target.getLegConfiguration().isBipedalPositionedCrotchBoobs()
											?SexActionPresets.appendagesToCrotchBoobs
											:null),
								!performer.isTaur()
									?SexActionPresets.allAreasToAppendages
									:(performer.getLegConfiguration().isBipedalPositionedCrotchBoobs()
											?SexActionPresets.crotchBoobsToAppendages
											:null),
								SexActionPresets.appendagesToUpperHalf,
								SexActionPresets.upperHalfToAppendages,
								SexActionPresets.kissing,
								SexActionPresets.mouthToBreasts,
								SexActionPresets.breastsToMouth),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR)))));
			}
	};
	
	public static VariableInteractions standingBehind = new VariableInteractions() {
		@Override
		public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
			GameCharacter performer = getCharacter(performerSlot);
			GameCharacter target = getCharacter(targetSlot);
			
			if(performer.isSizeDifferenceShorterThan(target)) { // If significantly shorter, they're basically standing to perform oral.
				return performingOralBehind.getSexActionInteractions(performerSlot, targetSlot);
			}
			
			return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
					new SexActionInteractions(
					Util.mergeMaps(
							!performer.isTaur()
								?(!target.isTaur()
									?Util.mergeMaps(
											SexActionPresets.kissing,
											SexActionPresets.groinToAss,
											SexActionPresets.penisToVagina,
											SexActionPresets.appendagesToAllAreas,
											SexActionPresets.allAreasToTailAndTentacle,
											SexActionPresets.lowerHalfToFinger)
									:Util.mergeMaps(
											SexActionPresets.allAreasToTailAndTentacle,
											SexActionPresets.appendagesToLowerHalf,
											SexActionPresets.groinToGroin))
								:!target.isTaur()
									?Util.mergeMaps(
											SexActionPresets.kissing,
											SexActionPresets.fingerToUpperTorso,
											SexActionPresets.fingerToLowerHalf,
											SexActionPresets.upperHalfToAppendages)
									:Util.mergeMaps(
											SexActionPresets.appendagesToLowerHalf,
											SexActionPresets.allAreasToTailAndTentacle)),
					Util.newArrayListOfValues(
							OrgasmCumTarget.BACK,
							target.isTaur() && !performer.isTaur()
								?OrgasmCumTarget.GROIN
								:null,
							OrgasmCumTarget.ASS,
							OrgasmCumTarget.LEGS,
							OrgasmCumTarget.FLOOR),
					Util.newArrayListOfValues(
							OrgasmCumTarget.FLOOR)))));
		}
	};

	public static VariableInteractions performingOral = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter target = getCharacter(targetSlot);
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.mouthToPenis,
								SexActionPresets.breastsToPenis,
								!target.isTaur()
									?SexActionPresets.mouthToVagina
									:null,
								SexActionPresets.mouthToCrotchBoobs,
								SexActionPresets.fingerToLowerHalf,
								!target.isTaur()
									?SexActionPresets.mouthToAppendages
									:null,
								SexActionPresets.groinToFeet,
								SexActionPresets.mouthToFeet),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FEET,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.SELF_FEET,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.HAIR,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.FLOOR,
								OrgasmCumTarget.ARMPITS)))));
			}
	};

	public static VariableInteractions performingOralBehind = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter target = getCharacter(targetSlot);
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.mouthToAss,
								SexActionPresets.mouthToVagina,
								SexActionPresets.fingerToLowerHalf,
								!target.isTaur()
									?SexActionPresets.mouthToAppendages
									:SexActionPresets.mouthToTailAndTentacle,
								SexActionPresets.groinToFeet,
								SexActionPresets.mouthToFeet),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FEET,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.SELF_FEET,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR)))));
			}
	};

	public static VariableInteractions standingBehindCharacterFacingWall = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				GameCharacter target = getCharacter(targetSlot);
				
				if(performer.isSizeDifferenceShorterThan(target)) { // If significantly shorter, they're basically standing to perform oral on ass.
					return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
							new SexActionInteractions(
								Util.mergeMaps(
										SexActionPresets.mouthToAss,
										SexActionPresets.mouthToVagina,
										SexActionPresets.appendagesToLowerHalf,
										!target.isTaur()
											?SexActionPresets.mouthToAppendages
											:SexActionPresets.mouthToTailAndTentacle),
								Util.newArrayListOfValues(
										OrgasmCumTarget.LEGS,
										OrgasmCumTarget.FEET,
										OrgasmCumTarget.SELF_LEGS,
										OrgasmCumTarget.SELF_GROIN,
										OrgasmCumTarget.SELF_FEET,
										OrgasmCumTarget.WALL,
										OrgasmCumTarget.FLOOR),
								Util.newArrayListOfValues(
										OrgasmCumTarget.WALL,
										OrgasmCumTarget.FLOOR)))));
				}
				if(target.isSizeDifferenceShorterThan(performer)) { // If significantly shorter, not much can be done...
					return new Value<>(targetSlot, Util.newHashMapOfValues(new Value<>(performerSlot,
							new SexActionInteractions(
								Util.mergeMaps(
										!performer.isTaur()
											?SexActionPresets.lowerHalfToAppendages
											:null,
										SexActionPresets.appendagesToUpperHalf),
								Util.newArrayListOfValues(
										OrgasmCumTarget.FACE,
										OrgasmCumTarget.HAIR,
										OrgasmCumTarget.BREASTS,
										OrgasmCumTarget.ARMPITS,
										OrgasmCumTarget.WALL,
										OrgasmCumTarget.WALL,
										OrgasmCumTarget.FLOOR),
								Util.newArrayListOfValues(
										OrgasmCumTarget.WALL,
										OrgasmCumTarget.FLOOR)))));
				}
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								!target.isTaur()
									?Util.mergeMaps(
											SexActionPresets.kissing,
											SexActionPresets.appendagesToAllAreas)
									:(target.getLegConfiguration().isBipedalPositionedCrotchBoobs()
											?SexActionPresets.appendagesToLowerHalf
											:Util.mergeMaps(
													SexActionPresets.appendagesToCrotchBoobs,
													SexActionPresets.appendagesToLowerHalf)),
									
								!performer.isTaur()
									?Util.mergeMaps(
											!target.isTaur()
												?Util.mergeMaps(
														SexActionPresets.allAreasToTailAndTentacle,
														SexActionPresets.lowerHalfToFinger)
												:null,
											SexActionPresets.groinToAss,
											SexActionPresets.penisToVagina,
											SexActionPresets.penisToThighs)
									:(performer.getLegConfiguration().isBipedalPositionedCrotchBoobs() && !target.isTaur()
											?Util.mergeMaps(
												SexActionPresets.crotchBoobsToAppendages)
											:null)),
						Util.newArrayListOfValues(
								OrgasmCumTarget.ASS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.WALL,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.WALL,
								OrgasmCumTarget.FLOOR)))));
			}
	};

	public static VariableInteractions standingBehindCharacterBackToWall = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				GameCharacter target = getCharacter(targetSlot);

				if(performer.isSizeDifferenceShorterThan(target)) { // If significantly shorter, they're basically standing to perform oral.
					return performingOral.getSexActionInteractions(performerSlot, targetSlot);
				}
				if(target.isSizeDifferenceShorterThan(performer)) { // If significantly shorter, they're basically standing to perform oral.
					return performingOral.getSexActionInteractions(targetSlot, performerSlot);
				}
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								!target.isTaur()
									?SexActionPresets.appendagesToAllAreas
									:(target.getLegConfiguration().isBipedalPositionedCrotchBoobs()
										?Util.mergeMaps(
											SexActionPresets.appendagesToUpperHalf,
											SexActionPresets.appendagesToCrotchBoobs)
										:SexActionPresets.appendagesToUpperHalf),
								!performer.isTaur()
									?SexActionPresets.allAreasToAppendages
									:(performer.getLegConfiguration().isBipedalPositionedCrotchBoobs()
											?Util.mergeMaps(
												SexActionPresets.upperHalfToAppendages,
												SexActionPresets.crotchBoobsToAppendages)
											:SexActionPresets.upperHalfToAppendages),
								SexActionPresets.kissing,
								SexActionPresets.mouthToBreasts,
								SexActionPresets.breastsToMouth,
								!performer.isTaur()
									?(!target.isTaur()
										?SexActionPresets.groinToGroin
										:(target.getLegConfiguration().isBipedalPositionedCrotchBoobs()
											?SexActionPresets.groinToCrotchBoobs
											:null))
									:null),
						Util.newArrayListOfValues(
								OrgasmCumTarget.STOMACH,
								!target.isTaur() && !performer.isTaur()
									?OrgasmCumTarget.GROIN
									:null,
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.WALL,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.STOMACH,
								!target.isTaur() && !performer.isTaur()
									?OrgasmCumTarget.GROIN
									:null,
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FLOOR)))));
			}
	};

	public static VariableInteractions standingBeforeDeskBack = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								!performer.isTaur()
									?Util.mergeMaps(
											SexActionPresets.kissing,
											SexActionPresets.mouthToBreasts,
											SexActionPresets.appendagesToAllAreas,
											SexActionPresets.upperHalfToAppendages)
									:null,
								SexActionPresets.groinToGroin,
								SexActionPresets.groinToCrotchBoobs,
								SexActionPresets.penisToAss),
						Util.newArrayListOfValues(
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.FLOOR)))));
			}
	};

	public static VariableInteractions standingBeforeDeskFront = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								!performer.isTaur()
									?Util.mergeMaps(
											SexActionPresets.appendagesToAllAreas,
											SexActionPresets.allAreasToTailAndTentacle)
									:null,
								SexActionPresets.groinToAss,
								SexActionPresets.vaginaToVagina,
								SexActionPresets.penisToVagina,
								SexActionPresets.penisToThighs),
						Util.newArrayListOfValues(
								OrgasmCumTarget.BACK,
								OrgasmCumTarget.ASS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR)))));
			}
	};
	
	public static VariableInteractions lyingOnDeskPerformingOral = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								!performer.isTaur()
									?SexActionPresets.mouthToLowerHalf
									:Util.mergeMaps(
											SexActionPresets.kissing,
											SexActionPresets.mouthToBreasts,
											SexActionPresets.breastsToMouth),
								SexActionPresets.fingerToLowerHalf,
								SexActionPresets.mouthToAppendages,
								SexActionPresets.upperHalfToAppendages),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.HAIR,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.FLOOR,
								OrgasmCumTarget.ARMPITS)))));
			}
	};
	
	public static VariableInteractions standingDeskToReceivingOral = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.kissing,
								SexActionPresets.appendagesToUpperHalf,
								SexActionPresets.upperHalfToAppendages),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR)))));
			}
	};
	
	
	public static VariableInteractions fuckingCharacterInStocks = new VariableInteractions() {
		@Override
		public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
			GameCharacter performer = getCharacter(performerSlot);
			
			return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
					new SexActionInteractions(
					Util.mergeMaps(
							SexActionPresets.penisToThighs,
							SexActionPresets.penisToAss,
							SexActionPresets.penisToVagina,
							SexActionPresets.appendagesToLowerHalf,
							SexActionPresets.appendagesToUpperHalf,
							!performer.isTaur()
								?SexActionPresets.vaginaToVagina
								:null),
					Util.newArrayListOfValues(
							OrgasmCumTarget.ASS,
							OrgasmCumTarget.GROIN,
							OrgasmCumTarget.BACK,
							OrgasmCumTarget.LEGS,
							OrgasmCumTarget.FLOOR),
					Util.newArrayListOfValues(
							OrgasmCumTarget.FLOOR)))));
		}
	};

	public static VariableInteractions humpingCharacterInStocks = new VariableInteractions() {
		@Override
		public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
			GameCharacter performer = getCharacter(performerSlot);
			
			return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
					new SexActionInteractions(
					Util.mergeMaps(
							SexActionPresets.penisToThighs,
							SexActionPresets.penisToAss,
							SexActionPresets.penisToVagina,
							SexActionPresets.appendagesToUpperHalf,
							!performer.isTaur()
								?SexActionPresets.vaginaToVagina
								:null),
					Util.newArrayListOfValues(
							OrgasmCumTarget.ASS,
							OrgasmCumTarget.GROIN,
							OrgasmCumTarget.BACK,
							OrgasmCumTarget.LEGS,
							OrgasmCumTarget.FLOOR),
					Util.newArrayListOfValues(
							OrgasmCumTarget.FLOOR)))));
		}
	};
	
	public static VariableInteractions fuckedByCharacterInStocks = new VariableInteractions() {
		@Override
		public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
			return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
					new SexActionInteractions(
					Util.mergeMaps(
							SexActionPresets.vaginaToPenis,
							SexActionPresets.assToPenis),
					Util.newArrayListOfValues(
							OrgasmCumTarget.FLOOR),
					Util.newArrayListOfValues(
							OrgasmCumTarget.ASS,
							OrgasmCumTarget.GROIN,
							OrgasmCumTarget.BACK,
							OrgasmCumTarget.LEGS,
							OrgasmCumTarget.FLOOR)))));
		}
	};
	

	public static VariableInteractions performingOralOnStocks = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter target = getCharacter(targetSlot);
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.mouthToLowerHalf,
								SexActionPresets.breastsToPenis,
								(!target.isTaur()==target.getLegConfiguration().isBipedalPositionedCrotchBoobs()
									?Util.mergeMaps(
											SexActionPresets.mouthToCrotchBoobs,
											SexActionPresets.appendagesToCrotchBoobs)
									:null),
								SexActionPresets.appendagesToLowerHalf,
								SexActionPresets.groinToFeet,
								SexActionPresets.mouthToFeet),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FEET,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.SELF_FEET,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.HAIR,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.FLOOR,
								OrgasmCumTarget.ARMPITS)))));
			}
	};

	public static VariableInteractions stocksCharacterPerformingOral = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.mouthToLowerHalf,
								SexActionPresets.mouthToCrotchBoobs,
								SexActionPresets.upperHalfToAppendages),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.HAIR,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.FLOOR,
								OrgasmCumTarget.ARMPITS)))));
			}
	};

	
	public static VariableInteractions allFoursBehind = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				GameCharacter target = getCharacter(targetSlot);
				boolean isAbleToReachTargetUpperTorso = performer.isTaur()==target.isTaur();
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.penisToThighs,
									SexActionPresets.penisToAss,
									SexActionPresets.penisToVagina,
									SexActionPresets.vaginaToVagina,
									SexActionPresets.tailToLowerHalf,
									SexActionPresets.tentacleToLowerHalf,
									SexActionPresets.lowerHalfToTailTentacle,
									(!performer.isTaur()
										?SexActionPresets.fingerToLowerHalf
										:null),
									(!performer.isTaur()
										?SexActionPresets.upperHalfToTailTentacle
										:null),
									(isAbleToReachTargetUpperTorso
										?SexActionPresets.fingerToUpperTorso
										:null)),
							Util.newArrayListOfValues(
									OrgasmCumTarget.ASS,
									OrgasmCumTarget.GROIN,
									OrgasmCumTarget.BACK,
									OrgasmCumTarget.LEGS,
									OrgasmCumTarget.FLOOR),
							Util.newArrayListOfValues(
									OrgasmCumTarget.FLOOR)))));
			}
	};
	
	public static VariableInteractions allFoursBehindToHumping = new VariableInteractions() {
		@Override
		public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
			GameCharacter performer = getCharacter(performerSlot);
			GameCharacter target = getCharacter(targetSlot);
			boolean isAbleToReachTargetUpperTorso = performer.isTaur()==target.isTaur();
			
			return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
					new SexActionInteractions(
					Util.mergeMaps(
							SexActionPresets.penisToThighs,
							SexActionPresets.penisToAss,
							SexActionPresets.penisToVagina,
							SexActionPresets.vaginaToVagina,
							SexActionPresets.tailToLowerHalf,
							SexActionPresets.tentacleToLowerHalf,
							!performer.isTaur()
								?SexActionPresets.fingerToLowerHalf
								:null,
							isAbleToReachTargetUpperTorso
								?SexActionPresets.fingerToUpperTorso
								:null),
					Util.newArrayListOfValues(
							OrgasmCumTarget.ASS,
							OrgasmCumTarget.GROIN,
							OrgasmCumTarget.BACK,
							OrgasmCumTarget.LEGS,
							OrgasmCumTarget.FLOOR),
					Util.newArrayListOfValues(
							OrgasmCumTarget.FLOOR)))));
		}
	};
	
	public static VariableInteractions allFoursCharacterBehindToCharactersFront = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.fingerToUpperTorso,
								SexActionPresets.kissing,
								SexActionPresets.upperHalfToFinger),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR)))));
			}
	};

	public static VariableInteractions allFoursHumping = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				GameCharacter target = getCharacter(targetSlot);
				boolean isAbleToReachTargetUpperTorso = !performer.isTaur() || target.isTaur();
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.penisToThighs,
								SexActionPresets.penisToAss,
								SexActionPresets.penisToVagina,
								SexActionPresets.tailToLowerHalf,
								SexActionPresets.tentacleToLowerHalf,
								SexActionPresets.lowerHalfToAppendages,
								!performer.isTaur()
									?SexActionPresets.fingerToLowerHalf
									:null,
								(!performer.isTaur()
									?SexActionPresets.upperHalfToAppendages
									:null),
								isAbleToReachTargetUpperTorso
									?SexActionPresets.fingerToUpperTorso
									:null),
						Util.newArrayListOfValues(
								OrgasmCumTarget.ASS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.BACK,
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR)))));
			}
	};
	
	
	public static VariableInteractions allFoursFeet = new VariableInteractions() {
		@Override
		public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
			return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
					new SexActionInteractions(
					Util.mergeMaps(
							SexActionPresets.groinToFeet,
							SexActionPresets.mouthToFeet),
					Util.newArrayListOfValues(
							OrgasmCumTarget.LEGS,
							OrgasmCumTarget.FEET,
							OrgasmCumTarget.FLOOR),
					Util.newArrayListOfValues(
							OrgasmCumTarget.FLOOR)))));
		}
	};

	public static VariableInteractions allFoursPerformingOral = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				GameCharacter target = getCharacter(targetSlot);
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.mouthToPenis,
								SexActionPresets.mouthToCrotchBoobs,
								target.isTaur()
									?SexActionPresets.mouthToTailAndTentacle
									:Util.mergeMaps(
											SexActionPresets.mouthToVagina,
											SexActionPresets.mouthToAppendages),
								performer.isTaur()
									?Util.mergeMaps(
											SexActionPresets.breastsToPenis,
											SexActionPresets.fingerToLowerHalf)
									:null,
								SexActionPresets.mouthToFeet),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.HAIR,
								OrgasmCumTarget.BACK,
								OrgasmCumTarget.FLOOR,
								OrgasmCumTarget.ARMPITS)))));
			}
	};

	public static VariableInteractions allFoursPerformingOralBehind = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				GameCharacter target = getCharacter(targetSlot);
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.mouthToAss,
								target.isTaur()
									?Util.mergeMaps(
											SexActionPresets.mouthToVagina,
											SexActionPresets.mouthToTailAndTentacle)
									:SexActionPresets.mouthToAppendages,
								performer.isTaur()
									?SexActionPresets.fingerToLowerHalf
									:null,
								SexActionPresets.mouthToFeet),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR)))));
			}
	};
	
	public static VariableInteractions allFoursToAllFours = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.kissing),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR),
						null))));
			}
	};
	
	public static VariableInteractions fuckingTaur = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				GameCharacter target = getCharacter(targetSlot);
				boolean isAbleToReachTargetUpperTorso = performer.getLegConfiguration()==target.getLegConfiguration();
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.penisToThighs,
								SexActionPresets.penisToAss,
								SexActionPresets.penisToVagina,
								SexActionPresets.vaginaToVagina,
								SexActionPresets.tailToLowerHalf,
								isAbleToReachTargetUpperTorso
									?SexActionPresets.fingerToUpperTorso
									:null),
						Util.newArrayListOfValues(
								OrgasmCumTarget.ASS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.BACK,
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FLOOR),
						null))));
			}
	};

	public static VariableInteractions allFoursFrontInteraction = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				
				if(performerSlot.isStanding(performer)) {
					return faceToFace.getSexActionInteractions(performerSlot, targetSlot);
				} else {
					return performingOral.getSexActionInteractions(performerSlot, targetSlot);
				}
			}
	};

	
	
	public static VariableInteractions sittingInLap = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				
				if(!performer.isTaur()) {
					return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
							new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.appendagesToAllAreas,
									SexActionPresets.allAreasToAppendages,
									SexActionPresets.kissing,
									SexActionPresets.breastsToMouth,
									SexActionPresets.mouthToBreasts,
									SexActionPresets.vaginaToPenis,
									SexActionPresets.assToGroin),
							Util.newArrayListOfValues(
									OrgasmCumTarget.LEGS,
									OrgasmCumTarget.GROIN,
									OrgasmCumTarget.STOMACH,
									OrgasmCumTarget.BREASTS,
									OrgasmCumTarget.SELF_LEGS,
									OrgasmCumTarget.SELF_GROIN),
							Util.newArrayListOfValues(
									OrgasmCumTarget.LEGS,
									OrgasmCumTarget.ASS,
									OrgasmCumTarget.GROIN,
									OrgasmCumTarget.STOMACH,
									OrgasmCumTarget.BREASTS,
									OrgasmCumTarget.SELF_LEGS,
									OrgasmCumTarget.SELF_GROIN)))));
				} else {
					return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
							new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.vaginaToPenis,
									SexActionPresets.vaginaToVagina,
									SexActionPresets.assToGroin),
							Util.newArrayListOfValues(
									OrgasmCumTarget.SELF_LEGS,
									OrgasmCumTarget.FLOOR),
							Util.newArrayListOfValues(
									OrgasmCumTarget.LEGS,
									OrgasmCumTarget.ASS,
									OrgasmCumTarget.GROIN,
									OrgasmCumTarget.STOMACH,
									OrgasmCumTarget.SELF_LEGS,
									OrgasmCumTarget.FLOOR)))));
					
				}
			}
	};

	public static VariableInteractions sittingBetweenLegs = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.kissing,
								SexActionPresets.appendagesToAllAreas,
								SexActionPresets.allAreasToAppendages,
								SexActionPresets.vaginaToPenis),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_GROIN),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_GROIN)))));
				
			}
	};

	public static VariableInteractions sittingBetweenLegsCharacterSitting = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.appendagesToAllAreas,
								SexActionPresets.allAreasToAppendages,
								SexActionPresets.assToPenis),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_GROIN),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.BACK,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_GROIN)))));
				
			}
	};
	
	
	/*--- Lying down presets: ---*/


	public static VariableInteractions cowgirlRiding = new VariableInteractions() {
		@Override
		public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
			GameCharacter performer = getCharacter(performerSlot);
			GameCharacter target = getCharacter(targetSlot);
			
			if(!performer.isTaur()) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								!target.isTaur()
									?SexActionPresets.kissing
									:null,
								!target.isTaur()
									?SexActionPresets.breastsToMouth
									:null,
								SexActionPresets.appendagesToAllAreas,
								SexActionPresets.allAreasToAppendages,
								SexActionPresets.thighsToPenis,
								SexActionPresets.vaginaToPenis,
								SexActionPresets.assToGroin,
								SexActionPresets.penisToBreasts,
								SexActionPresets.feetToMouth),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_GROIN),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.ASS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_GROIN)))));
			} else {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.vaginaToPenis,
								SexActionPresets.vaginaToVagina,
								SexActionPresets.assToGroin),
						Util.newArrayListOfValues(
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.ARMPITS),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.ASS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR)))));
			}
		}
	};

	public static VariableInteractions cowgirlReverseRiding = new VariableInteractions() {
		@Override
		public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
			GameCharacter performer = getCharacter(performerSlot);
			
			if(!performer.isTaur()) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.appendagesToLowerHalf,
								SexActionPresets.allAreasToAppendages,
								SexActionPresets.thighsToPenis,
								SexActionPresets.vaginaToPenis,
								SexActionPresets.assToGroin),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_GROIN),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.ASS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_GROIN)))));
			} else {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.vaginaToPenis,
								SexActionPresets.vaginaToVagina,
								SexActionPresets.assToGroin),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.SELF_LEGS),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.ASS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR)))));
			}
		}
	};
	
	public static VariableInteractions faceSittingRiding = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				
				if(!performer.isTaur()) {
					return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
							new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.assToMouth,
									SexActionPresets.groinToMouth,
									SexActionPresets.appendagesToUpperHalf),
							Util.newArrayListOfValues(
									OrgasmCumTarget.FACE,
									OrgasmCumTarget.HAIR,
									OrgasmCumTarget.SELF_GROIN,
									OrgasmCumTarget.SELF_LEGS,
									OrgasmCumTarget.FLOOR,
									OrgasmCumTarget.ARMPITS),
							Util.newArrayListOfValues(
									OrgasmCumTarget.SELF_STOMACH,
									OrgasmCumTarget.SELF_LEGS,
									OrgasmCumTarget.FLOOR)))));
				} else {
					return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
							new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.assToMouth,
									SexActionPresets.groinToMouth),
							Util.newArrayListOfValues(
									OrgasmCumTarget.FACE,
									OrgasmCumTarget.HAIR,
									OrgasmCumTarget.SELF_LEGS,
									OrgasmCumTarget.FLOOR,
									OrgasmCumTarget.ARMPITS),
							Util.newArrayListOfValues(
									OrgasmCumTarget.SELF_STOMACH,
									OrgasmCumTarget.SELF_LEGS,
									OrgasmCumTarget.FLOOR)))));
				}
			}
	};

	public static VariableInteractions faceSittingReverseRiding = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				
				if(!performer.isTaur()) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.assToMouth,
								SexActionPresets.groinToMouth,
								SexActionPresets.appendagesToAllAreas,
								SexActionPresets.feetToGroin),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR,
								OrgasmCumTarget.ARMPITS),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR)))));
				} else {
					return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
							new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.assToMouth,
									SexActionPresets.groinToMouth),
							Util.newArrayListOfValues(
									OrgasmCumTarget.FACE,
									OrgasmCumTarget.BREASTS,
									OrgasmCumTarget.STOMACH,
									OrgasmCumTarget.SELF_LEGS,
									OrgasmCumTarget.FLOOR,
									OrgasmCumTarget.ARMPITS),
							Util.newArrayListOfValues(
									OrgasmCumTarget.SELF_STOMACH,
									OrgasmCumTarget.SELF_LEGS,
									OrgasmCumTarget.FLOOR)))));
				}
			}
	};

	public static VariableInteractions sixtyNine = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.mouthToVagina,
								SexActionPresets.mouthToPenis,
								performerSlot.hasTag(SexSlotTag.LYING_DOWN)
									?SexActionPresets.mouthToAss
									:null,
								SexActionPresets.breastsToPenis,
								SexActionPresets.mouthToCrotchBoobs,
								SexActionPresets.tailToUpperTorso,
								SexActionPresets.tentacleToUpperTorso,
								SexActionPresets.fingerToLowerHalf),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.HAIR,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.FLOOR,
								OrgasmCumTarget.ARMPITS),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.HAIR,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.FLOOR,
								OrgasmCumTarget.ARMPITS)))));
			}
	};
	
	public static VariableInteractions missionary = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				GameCharacter target = getCharacter(targetSlot);
				
				if(!performer.isTaur() && !target.isTaur()) {
					return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
							new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.kissing,
									SexActionPresets.breastsToMouth,
									SexActionPresets.mouthToBreasts,
									SexActionPresets.groinToAss,
									SexActionPresets.groinToGroin,
									SexActionPresets.tailToLowerHalf,
									SexActionPresets.tentacleToLowerHalf,
									SexActionPresets.appendagesToAllAreas,
									SexActionPresets.mouthToFeet),
							Util.newArrayListOfValues(
									OrgasmCumTarget.ASS,
									OrgasmCumTarget.GROIN,
									OrgasmCumTarget.STOMACH,
									OrgasmCumTarget.BREASTS,
									OrgasmCumTarget.LEGS,
									OrgasmCumTarget.FLOOR),
							Util.newArrayListOfValues(
									OrgasmCumTarget.GROIN,
									OrgasmCumTarget.SELF_FACE,
									OrgasmCumTarget.SELF_BREASTS,
									OrgasmCumTarget.SELF_STOMACH,
									OrgasmCumTarget.FLOOR)))));
				} else {
					return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
							new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.groinToAss,
									SexActionPresets.groinToGroin,
									SexActionPresets.tailToLowerHalf,
									SexActionPresets.tentacleToLowerHalf,
									performer.isTaur()
										?null
										:SexActionPresets.appendagesToLowerHalf),
							Util.newArrayListOfValues(
									OrgasmCumTarget.ASS,
									OrgasmCumTarget.GROIN,
									OrgasmCumTarget.STOMACH,
									OrgasmCumTarget.LEGS,
									OrgasmCumTarget.FLOOR),
							Util.newArrayListOfValues(
									OrgasmCumTarget.GROIN,
									OrgasmCumTarget.SELF_FACE,
									OrgasmCumTarget.SELF_BREASTS,
									OrgasmCumTarget.SELF_STOMACH,
									OrgasmCumTarget.FLOOR)))));
				}
			}
	};

	public static VariableInteractions matingPress = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter target = getCharacter(targetSlot);
				
				if(!target.isTaur()) {
					return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
							new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.kissing,
									SexActionPresets.groinToAss,
									SexActionPresets.groinToGroin,
									SexActionPresets.tailToLowerHalf,
									SexActionPresets.tentacleToLowerHalf,
									SexActionPresets.appendagesToAllAreas),
							Util.newArrayListOfValues(
									OrgasmCumTarget.ASS,
									OrgasmCumTarget.GROIN,
									OrgasmCumTarget.STOMACH,
									OrgasmCumTarget.BREASTS,
									OrgasmCumTarget.LEGS,
									OrgasmCumTarget.FLOOR),
							Util.newArrayListOfValues(
									OrgasmCumTarget.GROIN,
									OrgasmCumTarget.SELF_FACE,
									OrgasmCumTarget.SELF_BREASTS,
									OrgasmCumTarget.SELF_STOMACH,
									OrgasmCumTarget.FLOOR)))));
				} else {
					return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
							new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.kissing,
									SexActionPresets.groinToAss,
									SexActionPresets.groinToGroin,
									SexActionPresets.tailToLowerHalf,
									SexActionPresets.tentacleToLowerHalf,
									SexActionPresets.appendagesToLowerHalf),
							Util.newArrayListOfValues(
									OrgasmCumTarget.ASS,
									OrgasmCumTarget.GROIN,
									OrgasmCumTarget.STOMACH,
									OrgasmCumTarget.LEGS,
									OrgasmCumTarget.FLOOR),
							Util.newArrayListOfValues(
									OrgasmCumTarget.GROIN,
									OrgasmCumTarget.SELF_FACE,
									OrgasmCumTarget.SELF_BREASTS,
									OrgasmCumTarget.SELF_STOMACH,
									OrgasmCumTarget.FLOOR)))));
				}
			}
	};

	public static VariableInteractions scissoring = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.groinToGroin,
								SexActionPresets.appendagesToLowerHalf,
								SexActionPresets.lowerHalfToAppendages,
								SexActionPresets.mouthToFeet,
								SexActionPresets.feetToMouth),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR)))));
			}
	};

	public static VariableInteractions lapPillow = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.appendagesToAllAreas,
								SexActionPresets.upperHalfToAppendages,
								SexActionPresets.breastsToMouth),
						Util.newArrayListOfValues(
								OrgasmCumTarget.HAIR,
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR,
								OrgasmCumTarget.ARMPITS),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.FLOOR)))));
			}
	};
	
	public static VariableInteractions performingOralToLyingDown = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter target = getCharacter(targetSlot);
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.mouthToPenis,
								SexActionPresets.breastsToPenis,
								SexActionPresets.mouthToVagina,
								SexActionPresets.mouthToAss,
								target.getLegConfiguration().isBipedalPositionedCrotchBoobs()!=target.isTaur()
									?SexActionPresets.mouthToCrotchBoobs
									:null,
								SexActionPresets.fingerToLowerHalf,
								!target.isTaur()
									?SexActionPresets.mouthToAppendages
									:SexActionPresets.mouthToTailAndTentacle,
								SexActionPresets.groinToFeet,
								SexActionPresets.mouthToFeet),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FEET,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.SELF_FEET,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.FLOOR)))));
			}
	};
	
	public static VariableInteractions performingOralToSixtyNine = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.kissing,
								SexActionPresets.upperHalfToAppendages,
								SexActionPresets.appendagesToUpperHalf),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR),
						null))));
			}
	};
	
	public static VariableInteractions characterToCharactersBack = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				
				if(!performer.isTaur()) {
					return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
							new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.fingerToLowerHalf,
									SexActionPresets.fingerToUpperTorso),
							Util.newArrayListOfValues(
									OrgasmCumTarget.FLOOR),
							Util.newArrayListOfValues(
									OrgasmCumTarget.FLOOR)))));
				} else {
					return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
							new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.appendagesToLowerHalf),
							Util.newArrayListOfValues(
									OrgasmCumTarget.FLOOR),
							Util.newArrayListOfValues(
									OrgasmCumTarget.FLOOR)))));
				}
			}
	};

	public static VariableInteractions characterToCharactersBackSex = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				GameCharacter target = getCharacter(targetSlot);
				
				if(performer.isTaur() == target.isTaur()) {
					return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
							new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.fingerToLowerHalf,
									SexActionPresets.fingerToUpperTorso,
									SexActionPresets.groinToAss,
									SexActionPresets.penisToVagina),
							Util.newArrayListOfValues(
									OrgasmCumTarget.FLOOR),
							Util.newArrayListOfValues(
									OrgasmCumTarget.FLOOR)))));
				} else {
					return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
							new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.groinToAss,
									SexActionPresets.penisToVagina),
							Util.newArrayListOfValues(
									OrgasmCumTarget.FLOOR),
							Util.newArrayListOfValues(
									OrgasmCumTarget.FLOOR)))));
				}
			}
	};

	public static VariableInteractions characterToCharactersFront = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter target = getCharacter(targetSlot);
				
				if(!target.isTaur()) {
					return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
							new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.fingerToLowerHalf,
									SexActionPresets.fingerToUpperTorso,
									SexActionPresets.kissing,
									SexActionPresets.lowerHalfToFinger,
									SexActionPresets.upperHalfToFinger),
							Util.newArrayListOfValues(
									OrgasmCumTarget.FLOOR),
							Util.newArrayListOfValues(
									OrgasmCumTarget.FLOOR)))));
				} else {
					return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
							new SexActionInteractions(
							Util.mergeMaps(
									SexActionPresets.appendagesToLowerHalf),
							Util.newArrayListOfValues(
									OrgasmCumTarget.FLOOR),
							Util.newArrayListOfValues(
									OrgasmCumTarget.FLOOR)))));
				}
			}
	};
	
	public static VariableInteractions besideOneAnotherOnDesk = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.kissing,
								SexActionPresets.upperHalfToAppendages,
								SexActionPresets.appendagesToUpperHalf),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR)))));
			}
	};
	
	public static VariableInteractions besideOneAnother = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.kissing,
								SexActionPresets.allAreasToAppendages,
								SexActionPresets.appendagesToAllAreas),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR),
						null))));
			}
	};
	
	public static VariableInteractions besideLyingDown = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.lowerHalfToAppendages),
						Util.newArrayListOfValues(
								OrgasmCumTarget.HAIR,
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FEET,
								OrgasmCumTarget.ARMPITS),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.FLOOR)))));
			}
	};

	public static VariableInteractions besideKneeling = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.groinToMouth,
								SexActionPresets.lowerHalfToAppendages),
						Util.newArrayListOfValues(
								OrgasmCumTarget.HAIR,
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FEET,
								OrgasmCumTarget.ARMPITS),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.FLOOR)))));
			}
	};
	

	
	public static VariableInteractions handHolding = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.handHolding),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR)))));
			}
	};
	
	public static VariableInteractions breedingStallFucking = new VariableInteractions() {
		@Override
		public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
			return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
					new SexActionInteractions(
					Util.mergeMaps(
							SexActionPresets.appendagesToLowerHalf,
							SexActionPresets.groinToGroin,
							SexActionPresets.groinToAss,
							SexActionPresets.allAreasToTailAndTentacle),
					Util.newArrayListOfValues(
							OrgasmCumTarget.ASS,
							OrgasmCumTarget.GROIN,
							OrgasmCumTarget.LEGS,
							OrgasmCumTarget.FEET,
							OrgasmCumTarget.FLOOR),
					targetSlot==SexSlotBreedingStall.BREEDING_STALL_FRONT
						?Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR)
						:Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.FLOOR)))));
		}
	};
	
	/**
	 * Contains sex actions as well due to how his scene is set up.
	 */
	public static VariableInteractions performingOralRalph = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter target = getCharacter(targetSlot);
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.mouthToPenis,
								SexActionPresets.breastsToPenis,
								!target.isTaur()
									?SexActionPresets.mouthToVagina
									:null,
								SexActionPresets.mouthToCrotchBoobs,
								SexActionPresets.fingerToLowerHalf,
								SexActionPresets.mouthToAppendages,
								SexActionPresets.groinToFeet,
								SexActionPresets.assToGroin,
								SexActionPresets.groinToGroin),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FEET,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.SELF_FEET,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.HAIR,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.FLOOR,
								OrgasmCumTarget.ARMPITS)))));
			}
	};
	

	public static VariableInteractions performingOralGloryHole = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter target = getCharacter(targetSlot);
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								!target.isTaur()
									?SexActionPresets.breastsToPenis
									:null,
								!target.isTaur()
									?SexActionPresets.mouthToPenis
									:null,
								!target.isTaur()
									?SexActionPresets.fingerToPenis
									:null,
								SexActionPresets.mouthToVagina,
								SexActionPresets.fingerToVagina
								),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR),
						target.isTaur()
							?Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR)
							:Util.newArrayListOfValues(
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.HAIR,
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.FLOOR,
								OrgasmCumTarget.ARMPITS)))));
			}
	};
	
	public static VariableInteractions gettingFuckedGloryHole = new VariableInteractions() {
		@Override
		public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
			GameCharacter target = getCharacter(targetSlot);
			
			return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
					new SexActionInteractions(
					Util.mergeMaps(
							!target.isTaur()
								?SexActionPresets.vaginaToPenis
								:SexActionPresets.vaginaToAppendages
							),
					Util.newArrayListOfValues(
							OrgasmCumTarget.SELF_LEGS,
							OrgasmCumTarget.FLOOR),
					Util.newArrayListOfValues(
							OrgasmCumTarget.ASS,
							OrgasmCumTarget.GROIN,
							OrgasmCumTarget.LEGS,
							OrgasmCumTarget.FLOOR)))));
		}
	};
	
	public static VariableInteractions gettingAnallyFuckedGloryHole = new VariableInteractions() {
		@Override
		public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
			GameCharacter target = getCharacter(targetSlot);
			
			return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
					new SexActionInteractions(
					Util.mergeMaps(
							!target.isTaur()
								?SexActionPresets.assToPenis
								:SexActionPresets.assToAppendages
							),
					Util.newArrayListOfValues(
							OrgasmCumTarget.SELF_LEGS,
							OrgasmCumTarget.FLOOR),
					Util.newArrayListOfValues(
							OrgasmCumTarget.ASS,
							OrgasmCumTarget.GROIN,
							OrgasmCumTarget.LEGS,
							OrgasmCumTarget.FLOOR)))));
		}
	};

}
