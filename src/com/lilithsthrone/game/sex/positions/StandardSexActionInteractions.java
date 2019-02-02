package com.lilithsthrone.game.sex.positions;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.main.Main;
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
	
	private static GameCharacter characterForPositionTesting;
	
	public static void setCharacterForPositionTesting(GameCharacter characterForPositionTesting) {
		StandardSexActionInteractions.characterForPositionTesting = characterForPositionTesting;
	}
	
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
				GameCharacter target = Sex.getCharacterInPosition(targetSlot);
				if(target==null) {
					target = characterForPositionTesting==null?Main.game.getPlayer():characterForPositionTesting;
				}
				
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
								target.getLegConfiguration().isBipedalPositionedGenitals()?OrgasmCumTarget.GROIN:null,
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FLOOR)))));
			}
	};

	public static VariableInteractions performingOral = new VariableInteractions() { //TODO size difference?
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter target = Sex.getCharacterInPosition(targetSlot);
				if(target==null) {
					target = characterForPositionTesting==null?Main.game.getPlayer():characterForPositionTesting;
				}
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.mouthToPenis,
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
								OrgasmCumTarget.FLOOR)))));
			}
	};

	public static VariableInteractions allFoursFrontInteraction = new VariableInteractions() { //TODO size difference?
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter target = Sex.getCharacterInPosition(targetSlot);
				if(target==null) {
					target = characterForPositionTesting==null?Main.game.getPlayer():characterForPositionTesting;
				}
				
				if(target.getLegConfiguration().isBipedalPositionedGenitals()) {
					return performingOral.getSexActionInteractions(performerSlot, targetSlot);
				} else {
					return faceToFace.getSexActionInteractions(performerSlot, targetSlot);
				}
			}
	};

	public static VariableInteractions performingOralBehind = new VariableInteractions() { //TODO size difference?
			@Override
			public Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot) {
				GameCharacter target = Sex.getCharacterInPosition(targetSlot);
				if(target==null) {
					target = characterForPositionTesting==null?Main.game.getPlayer():characterForPositionTesting;
				}
				
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
				GameCharacter target = Sex.getCharacterInPosition(targetSlot);
				if(target==null) {
					target = characterForPositionTesting==null?Main.game.getPlayer():characterForPositionTesting;
				}
				
				return new Value<>(performerSlot, Util.newHashMapOfValues(new Value<>(targetSlot,
						new SexActionInteractions(
						Util.mergeMaps(
								SexActionPresets.penisToAss,
								SexActionPresets.penisToVagina,
								SexActionPresets.tailToLowerHalf,
								SexActionPresets.fingerToUpperTorso),
						Util.newArrayListOfValues(
								OrgasmCumTarget.ASS,
								OrgasmCumTarget.GROIN,
								OrgasmCumTarget.BACK,
								OrgasmCumTarget.LEGS,
								OrgasmCumTarget.FLOOR)))));
			}
	};
	
}
