package com.lilithsthrone.game.sex.sexActions.dominion.zaranix;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public class SAZaranixCockSucking {
	public static final SexAction PARTNER_STOP_SEX = new SexAction(
			SexActionType.PARTNER_SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getNumberOfPartnerOrgasms()>=1;
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getDescription() {
			return "With a satisfied sigh, [npc.name] pushes your head back, saying that [npc.she]'s had enough.";
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
}
