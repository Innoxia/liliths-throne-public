package com.lilithsthrone.game.sex.sexActions.dominion.milkingStall;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.managers.dominion.SMMilkingStall;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.3
 * @version 0.2.3
 * @author Innoxia
 */
public class SADomMilkingStallPerformingOral {

	public static final SexAction PLAYER_SWITCH_TO_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.MISC) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !(Sex.getPosition() == SexPositionType.MILKING_STALL && Sex.getSexPositionSlot(Main.game.getPlayer()) == SexPositionSlot.MILKING_STALL_RECEIVING_ORAL)
					&& Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Move to front";
		}

		@Override
		public String getActionDescription() {
			return "Decide to use [npc.name]'s mouth.";
		}

		@Override
		public String getDescription() {
			return "Deciding that you want to use [npc.name]'s mouth, you step back, before moving around in front of [npc.her] [npc.face]."
					+ " Bringing your groin up to [npc.her] mouth, you [pc.moan], "
					+ "[pc.speech(You're going to love this!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MILKING_STALL_RECEIVING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.MILKING_STALL_LOCKED_IN_MILKING_STALL))));
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PLAYER_SWITCH_TO_BEHIND = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.MISC) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !(Sex.getPosition() == SexPositionType.MILKING_STALL && Sex.getSexPositionSlot(Main.game.getPlayer()) == SexPositionSlot.MILKING_STALL_FUCKING)
					&& Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Move behind";
		}

		@Override
		public String getActionDescription() {
			return "Stop using [npc.name] orally and move around behind [npc.herHim].";
		}

		@Override
		public String getDescription() {
			return "Deciding that you've had enough of using [npc.name]'s mouth, you step back, before moving around behind [npc.herHim]."
					+ " Grinding your groin up against [npc.her] [npc.ass+], you [pc.moan], "
					+ "[pc.speech(Be a good [npc.girl] and hold still while I fuck you!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MILKING_STALL_FUCKING)),
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.MILKING_STALL_LOCKED_IN_MILKING_STALL))));
			
			SexFlags.resetRequests();
		}
	};
}
