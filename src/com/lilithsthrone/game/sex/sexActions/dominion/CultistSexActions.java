package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionary;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionarySealed;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.88
 * @version 0.3.4
 * @author Innoxia
 */
public class CultistSexActions {
	
	public static final SexAction FORCE_POSITION_MISSIONARY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()) != SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()) != SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction());
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
			return "Having had enough of pleasuring [npc2.name] with [npc.her] mouth, [npc.name] [npc.verb(stand)] up and #IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF forwards, bringing [npc.her] groin up against [npc2.hers]."
					+ " Grabbing hold of [npc2.her] [npc2.legs+], [npc.she] [npc.verb(push)] them apart, grinning as [npc.she] [npc.moanVerb],"
					+ " [npc.speech(Time to have some real fun!)]";
		}

		@Override
		public void applyEffects() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterPerformingAction());
			Value<ImmobilisationType, GameCharacter> value2 = Main.sex.getImmobilisationType(Main.sex.getCharacterTargetedForSexAction(this));
			
			if((value!=null && value.getKey()==ImmobilisationType.WITCH_SEAL)
					|| (value2!=null && value2.getKey()==ImmobilisationType.WITCH_SEAL)) {
				Main.sex.setSexManager(new SMAltarMissionarySealed(
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))));
				
			} else {
				Main.sex.setSexManager(new SMAltarMissionary(
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR))));
				
			}
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
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()) != SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()) != SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction());
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
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterPerformingAction());
			Value<ImmobilisationType, GameCharacter> value2 = Main.sex.getImmobilisationType(Main.sex.getCharacterTargetedForSexAction(this));
			
			if((value!=null && value.getKey()==ImmobilisationType.WITCH_SEAL)
					|| (value2!=null && value2.getKey()==ImmobilisationType.WITCH_SEAL)) {
				Main.sex.setSexManager(new SMAltarMissionarySealed(
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))));
				
			} else {
				Main.sex.setSexManager(new SMAltarMissionary(
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR))));
			}
		}
	};
	
}
