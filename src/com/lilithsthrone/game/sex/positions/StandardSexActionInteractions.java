package com.lilithsthrone.game.sex.positions;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * A collection of standard SexActionInteractions classes.
 * 
 * @since 0.3.1
 * @version 0.3.1
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
				OrgasmCumTarget.FLOOR),
		null);
	
	
	// Sex:
	
	public static VariableInteractions faceToFace = new VariableInteractions() { //TODO size difference?
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter performer = getCharacter(performerSlot);
				GameCharacter target = getCharacter(targetSlot);
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								target.getLegConfiguration().isBipedalPositionedGenitals()
									?SexActionPresets.appendagesToAllAreas
									:(target.getLegConfiguration().isBipedalPositionedCrotchBoobs()
											?SexActionPresets.appendagesToCrotchBoobs
											:null),
								performer.getLegConfiguration().isBipedalPositionedGenitals()
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
								target.getLegConfiguration().isBipedalPositionedGenitals() && performer.getLegConfiguration().isBipedalPositionedGenitals()
									?OrgasmCumTarget.GROIN
									:null,
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.STOMACH,
								target.getLegConfiguration().isBipedalPositionedGenitals() && performer.getLegConfiguration().isBipedalPositionedGenitals()
									?OrgasmCumTarget.GROIN
									:null,
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FLOOR)))));
			}
	};

	public static VariableInteractions performingOral = new VariableInteractions() { //TODO size difference?
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter target = getCharacter(targetSlot);
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.mouthToPenis,
								SexActionPresets.breastsToPenis,
								target.getLegConfiguration().isBipedalPositionedGenitals()
									?SexActionPresets.mouthToVagina
									:null,
								SexActionPresets.mouthToCrotchBoobs,
								SexActionPresets.fingerToLowerHalf,
								SexActionPresets.mouthToAppendages,
								SexActionPresets.groinToFeet),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.SELF_FEET,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.HAIR,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.FLOOR)))));
			}
	};

	public static VariableInteractions performingOralBehind = new VariableInteractions() { //TODO size difference?
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter target = getCharacter(targetSlot);
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.mouthToAss,
								target.getLegConfiguration().isBipedalPositionedGenitals()
									?null
									:SexActionPresets.mouthToVagina,
								(target.getLegConfiguration().isBipedalPositionedCrotchBoobs()
										?null
										:SexActionPresets.mouthToCrotchBoobs),
								SexActionPresets.fingerToLowerHalf),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.SELF_FEET,
								OrgasmCumTarget.FLOOR),
						null))));
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
	
	public static VariableInteractions fuckingTaur = new VariableInteractions() { //TODO size difference?
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

	public static VariableInteractions allFoursFrontInteraction = new VariableInteractions() { //TODO size difference?
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
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.appendagesToAllAreas,
								SexActionPresets.allAreasToAppendages,
								SexActionPresets.kissing,
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
				
			}
	};

	public static VariableInteractions sittingInLapTaur = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
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
	};

	public static VariableInteractions sittingBetweenLegs = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
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
	
	
	/*--- Lying down presets: ---*/


	public static VariableInteractions cowgirlRiding = new VariableInteractions() {
		@Override
		public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
			return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
					new SexActionInteractions(
					Util.mergeMaps(
							SexActionPresets.appendagesToAllAreas,
							SexActionPresets.allAreasToAppendages,
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
		}
	};

	public static VariableInteractions cowgirlTaurRiding = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
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
								OrgasmCumTarget.SELF_LEGS),
						Util.newArrayListOfValues(
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.ASS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR)))));
				
			}
	};

	public static VariableInteractions faceSittingRiding = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.groinToMouth,
								SexActionPresets.fingerToMouth),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.HAIR,
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR)))));
			}
	};

	public static VariableInteractions faceSittingTaurRiding = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.groinToMouth),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.HAIR,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR)))));
			}
	};

	public static VariableInteractions groinCharacterInteractingWithFaceSitting = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.fingerToLowerHalf,
								SexActionPresets.fingerToUpperTorso),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR)))));
			}
	};
	
	public static VariableInteractions groinCharacterInteractingWithFaceSittingReversed = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
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
			}
	};

	public static VariableInteractions groinCharacterInteractingWithFaceSittingTaur = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.appendagesToLowerHalf),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FLOOR)))));
			}
	};

	public static VariableInteractions faceSittingReverseRiding = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.groinToMouth,
								SexActionPresets.appendagesToAllAreas),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.SELF_GROIN,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR)))));
			}
	};

	public static VariableInteractions faceSittingReverseTaurRiding = new VariableInteractions() {
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.groinToMouth),
						Util.newArrayListOfValues(
								OrgasmCumTarget.FACE,
								OrgasmCumTarget.BREASTS,
								OrgasmCumTarget.STOMACH,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR),
						Util.newArrayListOfValues(
								OrgasmCumTarget.SELF_STOMACH,
								OrgasmCumTarget.SELF_LEGS,
								OrgasmCumTarget.FLOOR)))));
			}
	};
}
