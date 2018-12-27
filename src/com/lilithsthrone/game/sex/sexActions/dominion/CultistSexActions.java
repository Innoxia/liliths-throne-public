package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionary;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionarySealed;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.88
 * @version 0.2.9
 * @author Innoxia
 */
public class CultistSexActions {

	public static final SexAction SEALED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "[style.boldArcane(Sealed!)]";
		}

		@Override
		public String getActionDescription() {
			return "The Witch's Seal that [npc2.name] cast on you is preventing you from making a move!";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterPerformingAction()) && ((Cultist)Sex.getActivePartner()).isSealedSex();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(try)] to make a move, but the Witch's Seal is too strong, and [npc.she] [npc.verb(collapse)] back onto the altar, stunned.",
					"The purple glow of a pentagram materialises beneath [npc.namePos] body as [npc.she] [npc.verb(try)] to make a move; proof that the Witch's Seal is still keeping [npc.herHim] bound in place.",
					"[npc.Name] [npc.verb(try)] to sit up on the altar, but [npc.sheIs] only able to squirm about a little under the immobilising effects of the Witch's Seal.",
					"The soft purple glow of the Witch's Seal can be seen all around [npc.name] as [npc.she] [npc.verb(struggle)] to make a move.",
					"[npc.speech(~Mmm!~)] [npc.name] [npc.verb(moan)], struggling in vain against the Witch's Seal.",
					"[npc.speech(~Aah!~)] [npc.name] [npc.verb(whimper)], squirming about on the altar as the With's Seal keeps [npc.herHim] locked in place.");
		}
	};
	
	public static final SexAction FORCE_POSITION_MISSIONARY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()) != SexPositionSlot.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()) != SexPositionSlot.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS
					&& Sex.isDom(Sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Missionary";
		}

		@Override
		public String getActionDescription() {
			return "Stand up so that you're positioned between [npc2.namePos] [npc2.legs].";
		}

		@Override
		public String getDescription() {
			return "Having had enough of pleasuring [npc2.name] with [npc.her] mouth, [npc.name] [npc.verb(stand)] up and [npc.verb(step)] forwards, bringing [npc.her] groin up against [npc2.hers]."
					+ " Grabbing hold of [npc2.her] [npc2.legs+], [npc.she] [npc.verb(push)] them apart, grinning as [npc.she] [npc.moanVerb],"
					+ " [npc.speech(Time to have some real fun!)]";
		}

		@Override
		public void applyEffects() {
			if(((Cultist)Sex.getActivePartner()).isSealedSex()) {
				Sex.setSexManager(new SMAltarMissionarySealed(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))));
				
			} else {
				Sex.setSexManager(new SMAltarMissionary(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.MISSIONARY_ALTAR_LYING_ON_ALTAR))));
				
			}
			
			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction FORCE_POSITION_MISSIONARY_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()) != SexPositionSlot.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()) != SexPositionSlot.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS
					&& Sex.isDom(Sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Missionary Oral";
		}

		@Override
		public String getActionDescription() {
			return "Drop down onto your knees and position your face between [npc2.namePos] legs.";
		}

		@Override
		public String getDescription() {
			return "Dropping down to [npc.her] knees, [npc.name] [npc.verb(position)] [npc.her] head between [npc2.namePos] [npc2.legs], ready to pleasure [npc2.herHim] with [npc.her] mouth."
					+ " As [npc2.namePos] [npc2.scent+] washes over [npc.herHim], [npc.name] [npc.moanVerb],"
					+ " [npc.speech(Oh, this is going to be <i>so</i> much fun!)]";
		}

		@Override
		public void applyEffects() {
			if(((Cultist)Sex.getActivePartner()).isSealedSex()) {
				Sex.setSexManager(new SMAltarMissionarySealed(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))));
				
			} else {
				Sex.setSexManager(new SMAltarMissionary(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.MISSIONARY_ALTAR_LYING_ON_ALTAR))));
			}
			
			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
}
