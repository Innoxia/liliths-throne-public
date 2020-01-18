package com.lilithsthrone.game.sex.sexActions.universal;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMasturbation;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.3.4
 * @author Innoxia
 */
public class Masturbation {
	
	public static final SexAction SWITCH_TO_STANDING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return SexPosition.MASTURBATION.isSlotUnlocked(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.STANDING, Main.sex.getAllOccupiedSlots(true)).getKey()
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.KNEELING_PANTIES
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.STANDING;
		}
		@Override
		public String getActionTitle() {
			return "Standing";
		}
		@Override
		public String getActionDescription() {
			return "Decide to get to your [npc.feet] and continue masturbating while standing upright.";
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Deciding that it would be better to continue masturbating while standing upright, [npc.name] [npc.verb(push)] [npc.herself] up onto [npc.her] [npc.feet],"
						+ " before dropping [npc.her] [npc.hands] down between [npc.her] [npc.legs] and preparing to continue where [npc.she] left off...";
			} else {
				return "Deciding that it would be better to continue masturbating while standing upright, [npc.name] [npc.verb(push)] [npc.herself] up onto [npc.her] [npc.feet],"
						+ " before looking back over [npc.her] shoulder at [npc.her] feral [npc.legRace]'s body and letting out a frustrated whine...";
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.MASTURBATION,
					Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.STANDING))
						:null,
					!Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.STANDING))
						:null){
			});
		}
	};
	
	public static final SexAction SWITCH_TO_SITTING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return SexPosition.MASTURBATION.isSlotUnlocked(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.SITTING, Main.sex.getAllOccupiedSlots(true)).getKey()
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.KNEELING_PANTIES
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.SITTING;
		}
		@Override
		public String getActionTitle() {
			return "Sitting";
		}
		@Override
		public String getActionDescription() {
			return "Decide to sit down on a nearby surface and continue masturbating in that position.";
		}
		@Override
		public String getDescription() {
			return "Deciding that it would be better to continue masturbating in a seated position, [npc.name] [npc.verb(find)] a suitable surface nearby,"
					+ " before sitting down and moving [npc.her] [npc.hands] between [npc.her] [npc.legs]...";
		}
		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.MASTURBATION,
					Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.SITTING))
						:null,
					!Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.SITTING))
						:null){
			});
		}
	};

	public static final SexAction SWITCH_TO_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return SexPosition.MASTURBATION.isSlotUnlocked(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.KNEELING, Main.sex.getAllOccupiedSlots(true)).getKey()
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.KNEELING_PANTIES
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.KNEELING;
		}
		@Override
		public String getActionTitle() {
			return "Kneeling";
		}
		@Override
		public String getActionDescription() {
			return "Decide to drop down onto your knees and continue masturbating in a kneeling position.";
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Deciding that it would be better to continue masturbating while kneeling down on the ground, [npc.name] [npc.verb(drop)] down into such a position,"
						+ " before sliding [npc.her] [npc.hands] down between [npc.her] [npc.legs] and preparing to continue where [npc.she] left off...";
			} else {
				return "Deciding that it would be better to continue masturbating while kneeling down on the ground, [npc.name] [npc.verb(drop)] down into such a position,"
						+ " before looking back over [npc.her] shoulder at [npc.her] feral [npc.legRace]'s body and letting out a frustrated whine...";
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.MASTURBATION,
					Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.KNEELING))
						:null,
					!Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.KNEELING))
						:null){
			});
		}
	};
	
}
