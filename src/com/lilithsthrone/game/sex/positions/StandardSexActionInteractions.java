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
				OrgasmCumTarget.FLOOR));
	
	
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
								SexActionPresets.appendagesToBreasts,
								SexActionPresets.kissing,
								SexActionPresets.mouthToBreasts),
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
								SexActionPresets.fingerToLowerHalf),
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
								OrgasmCumTarget.FLOOR)))));
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
								OrgasmCumTarget.FLOOR)))));
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
	
}
