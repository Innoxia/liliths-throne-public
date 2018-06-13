package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;

/**
 * @since 0.2.5
 * @version 0.2.5
 * @author Innoxia
 */
public class PartnerMasturbation {
	
	public static final SexAction PARTNER_GENERIC_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.MISC) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Orgasm";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer.";
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.LOW;
		}

		@Override
		public String getDescription() {
			return GenericOrgasms.getGenericOrgasmDescription(Sex.getActivePartner(), Sex.getSexPositionSlot(Sex.getActivePartner()).getAvailableCumTargets().get(0));
		}
		
		@Override
		public void applyEffects() {
			if (!Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.PENIS) && !Sex.getActivePartner().isWearingCondom()) {
				Sex.getActivePartner().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
			}
		}
	};
}
