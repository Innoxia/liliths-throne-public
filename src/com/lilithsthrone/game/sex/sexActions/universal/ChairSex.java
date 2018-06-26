package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMChair;
import com.lilithsthrone.game.sex.managers.universal.SMChairOral;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class ChairSex {

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
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()) != SexPositionSlot.CHAIR_KNEELING;
		}
		
		@Override
		public String getActionTitle() {
			return "Kneel";
		}

		@Override
		public String getActionDescription() {
			return "Kneel down in front of [npc2.name] and perform oral on [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to perform oral on [npc2.name], [npc.name] [npc.verb(kneel)] down in front of [npc2.herHim]."
					+ " Bringing [npc.her] mouth up to [npc2.namePos] groin, [npc.she] [npc.moansVerb], "
					+ "[npc.speech(You just sit back and enjoy this!)]";
		}

		@Override
		public void applyEffects() {
			if(Sex.isDom(Sex.getCharacterPerformingAction())) {
				Sex.setSexManager(new SMChairOral(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.CHAIR_KNEELING)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.CHAIR_ORAL_SITTING))));
			} else {
				Sex.setSexManager(new SMChairOral(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.CHAIR_ORAL_SITTING)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.CHAIR_KNEELING))));
			}
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction SWITCH_TO_RECEIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()) != SexPositionSlot.CHAIR_ORAL_SITTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Receive oral";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to kneel down in front of you and perform oral.";
		}

		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to get some oral sex, [npc.name] [npc.verb(get)] [npc2.name] to kneel down in front of [npc.herHim]."
					+ " Looking down into [npc2.her] [npc2.eyes], [npc.name] [npc.moansVerb], "
					+ "[npc.speech(Time to put your mouth to use!)]";
		}

		@Override
		public void applyEffects() {
			if(Sex.isDom(Sex.getCharacterPerformingAction())) {
				Sex.setSexManager(new SMChairOral(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.CHAIR_ORAL_SITTING)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.CHAIR_KNEELING))));
				
			} else {
				Sex.setSexManager(new SMChairOral(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.CHAIR_KNEELING)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.CHAIR_ORAL_SITTING))));
			}
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction SWITCH_SITTING_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()) != SexPositionSlot.CHAIR_BOTTOM;
		}
		
		@Override
		public String getActionTitle() {
			return "Sit (bottom)";
		}

		@Override
		public String getActionDescription() {
			return "Switch position so that you're the one sitting down, with [npc2.name] sitting in your lap.";
		}

		@Override
		public String getDescription() {
			return "[npc.Name] [npc.verb(decide)] to switch positions, and, getting [npc2.name] to stand up, [npc.she] [npc.verb(sit)] down on the chair and [npc.verb(pull)] [npc2.herHim] down onto [npc.her] lap."
					+ " Looking up into [npc2.her] [npc2.eyes], [npc.she] [npc.moansVerb], "
					+ "[npc.speech(Good [npc2.girl]!)]";
		}

		@Override
		public void applyEffects() {
			if(Sex.isDom(Sex.getCharacterPerformingAction())) {
				Sex.setSexManager(new SMChair(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.CHAIR_BOTTOM)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.CHAIR_TOP))));
				
			} else {
				Sex.setSexManager(new SMChair(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.CHAIR_TOP)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.CHAIR_BOTTOM))));
			}
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction SWITCH_SITTING_TOP = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()) != SexPositionSlot.CHAIR_TOP;
		}
		
		@Override
		public String getActionTitle() {
			return "Sit (top)";
		}

		@Override
		public String getActionDescription() {
			return "Switch position so that [npc2.name] is the one sitting on the chair, with you sitting in [npc2.her] lap.";
		}

		@Override
		public String getDescription() {
			return "[npc.Name] [npc.verb(decide)] to switch positions, and, getting [npc2.name] to sit down on the chair, [npc.she] [npc.verb(sit)] down on [npc2.her] lap."
					+ " Looking down into [npc2.her] [npc2.eyes], [npc.she] [npc.moansVerb], "
					+ "[npc.speech(Good [npc2.girl]!)]";
		}

		@Override
		public void applyEffects() {
			if(Sex.isDom(Sex.getCharacterPerformingAction())) {
				Sex.setSexManager(new SMChair(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.CHAIR_TOP)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.CHAIR_BOTTOM))));
				
			} else {
				Sex.setSexManager(new SMChair(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.CHAIR_BOTTOM)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.CHAIR_TOP))));
			}
			
			
			SexFlags.resetRequests();
		}
	};
	
}
