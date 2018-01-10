package com.lilithsthrone.game.sex.sexActions.dominion.stocks;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.managers.dominion.SMDomStocksBehind;
import com.lilithsthrone.game.sex.managers.dominion.SMDomStocksOral;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.slavery.SlaveJobSetting;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public class SADomStocksPerformingOral {

	public static final SexAction PLAYER_SWITCH_TO_ORAL = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getPosition() != SexPositionType.STOCKS_PARTNER_BEING_USED_ORAL
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
			Sex.setSexManager(new SMDomStocksOral(Sex.getActivePartner().getSlaveJobSettings().contains(SlaveJobSetting.SEX_VAGINAL),
					Sex.getActivePartner().getSlaveJobSettings().contains(SlaveJobSetting.SEX_ANAL),
					Sex.getActivePartner().getSlaveJobSettings().contains(SlaveJobSetting.SEX_ORAL)));
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PLAYER_SWITCH_TO_BEHIND = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getPosition() != SexPositionType.STOCKS_PARTNER_BEING_USED
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
			Sex.setSexManager(new SMDomStocksBehind(
					Sex.getActivePartner().getSlaveJobSettings().contains(SlaveJobSetting.SEX_VAGINAL),
					Sex.getActivePartner().getSlaveJobSettings().contains(SlaveJobSetting.SEX_ANAL)));
			
			SexFlags.resetRequests();
		}
	};
}
