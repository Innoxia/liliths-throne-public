package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.SMMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.3.1
 * @author Innoxia
 */
public class MilkingStall {

	public static final SexAction SWITCH_TO_BEHIND = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					&& !Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BEHIND_STOCKS)
					&& !Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Sex.isDom(Sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Move behind";
		}

		@Override
		public String getActionDescription() {
			return "Move around behind [npc2.name] and get ready to fuck [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to fuck [npc2.name] while [npc2.sheIs] locked in the stocks,"
						+ " [npc.name] [npc.verb(step)] up behind [npc2.herHim] and [npc.verb(start)] grinding [npc.her] groin up against [npc2.her] [npc2.ass+]."
					+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.moanVerb], "
					+ "[npc.speech(Be a good [npc2.girl] and hold still while I fuck you!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
			
		}
	};
	
	public static final SexAction SWITCH_TO_GIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					&& !Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).hasTag(SexSlotTag.PERFORMING_ORAL_STOCKS)
					&& !Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Sex.isDom(Sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Perform oral";
		}

		@Override
		public String getActionDescription() {
			return "Kneel behind [npc2.name] and perform oral on [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to perform oral on [npc2.name], [npc.name] [npc.verb(kneel)]down behind [npc2.herHim]."
					+ " Bringing [npc.her] mouth up to [npc2.her] groin, [npc.she] [npc.moanVerb],"
					+ " [npc.speech(You're going to love this!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexSlotMilkingStall.PERFORMING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
			
		}
	};
	
	public static final SexAction SWITCH_TO_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					&& !Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).hasTag(SexSlotTag.RECEIVING_ORAL_STOCKS)
					&& !Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Sex.isDom(Sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Move to front";
		}

		@Override
		public String getActionDescription() {
			return "Decide to use [npc2.namePos] mouth.";
		}

		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to use [npc2.namePos] mouth, [npc.name] [npc.verb(step)] back, before moving around in front of [npc2.her] [npc2.face]."
					+ " Bringing [npc.her] groin up to [npc2.her] mouth, [npc.she] [npc.moanVerb],"
					+ " [npc.speech(You're going to love this!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexSlotMilkingStall.RECEIVING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
			
		}
	};

}
