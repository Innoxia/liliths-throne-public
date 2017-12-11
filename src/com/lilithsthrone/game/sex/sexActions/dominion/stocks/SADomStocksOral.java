package com.lilithsthrone.game.sex.sexActions.dominion.stocks;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPosition;
import com.lilithsthrone.game.sex.managers.dominion.SMDomStocksBehind;
import com.lilithsthrone.game.sex.managers.dominion.SMDomStocksPerformOral;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.slavery.SlaveJobSetting;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public class SADomStocksOral {

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
					&& Sex.getPosition() != SexPosition.STOCKS_PARTNER_BEING_USED
					&& Sex.isPlayerDom();
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
					Sex.getPartner().getSlaveJobSettings().contains(SlaveJobSetting.SEX_VAGINAL),
					Sex.getPartner().getSlaveJobSettings().contains(SlaveJobSetting.SEX_ANAL)));
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PLAYER_SWITCH_TO_GIVING_ORAL = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getPosition() != SexPosition.STOCKS_PARTNER_PLAYER_PERFORMING_ORAL
					&& Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Perform oral";
		}

		@Override
		public String getActionDescription() {
			return "Kneel behind [npc.name] and perform oral on [npc.herHim].";
		}

		@Override
		public String getDescription() {
			return "Deciding that you want to perform oral on [npc.name], you step back, before moving around to kneel down behind [npc.herHim]."
					+ " Bringing your mouth up to [npc.her] groin, you [pc.moan], "
					+ "[pc.speech(You're going to love this!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMDomStocksPerformOral(Sex.getPartner().getSlaveJobSettings().contains(SlaveJobSetting.SEX_VAGINAL),
					Sex.getPartner().getSlaveJobSettings().contains(SlaveJobSetting.SEX_ANAL)));
			
			SexFlags.resetRequests();
		}
	};
}
