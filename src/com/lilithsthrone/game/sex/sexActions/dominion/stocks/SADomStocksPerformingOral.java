package com.lilithsthrone.game.sex.sexActions.dominion.stocks;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.managers.dominion.SMStocks;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.slavery.SlaveJobSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.95
 * @version 0.1.97
 * @author Innoxia
 */
public class SADomStocksPerformingOral {

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
					&& !(Sex.getPosition() == SexPositionType.STOCKS_SEX && Sex.getSexPositionSlot(Main.game.getPlayer()) == SexPositionSlot.STOCKS_RECEIVING_ORAL)
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
			Sex.setSexManager(new SMStocks(
					Sex.getActivePartner().getSlaveJobSettings().contains(SlaveJobSetting.SEX_VAGINAL),
					Sex.getActivePartner().getSlaveJobSettings().contains(SlaveJobSetting.SEX_ANAL),
					Sex.getActivePartner().getSlaveJobSettings().contains(SlaveJobSetting.SEX_ORAL),
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STOCKS_RECEIVING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.STOCKS_LOCKED_IN_STOCKS))));
			
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
					&& !(Sex.getPosition() == SexPositionType.STOCKS_SEX && Sex.getSexPositionSlot(Main.game.getPlayer()) == SexPositionSlot.STOCKS_FUCKING)
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
			Sex.setSexManager(new SMStocks(
					Sex.getActivePartner().getSlaveJobSettings().contains(SlaveJobSetting.SEX_VAGINAL),
					Sex.getActivePartner().getSlaveJobSettings().contains(SlaveJobSetting.SEX_ANAL),
					Sex.getActivePartner().getSlaveJobSettings().contains(SlaveJobSetting.SEX_ORAL),
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STOCKS_FUCKING)),
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.STOCKS_LOCKED_IN_STOCKS))));
			
			SexFlags.resetRequests();
		}
	};
}
