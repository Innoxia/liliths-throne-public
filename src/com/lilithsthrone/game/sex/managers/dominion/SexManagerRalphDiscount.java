package com.lilithsthrone.game.sex.managers.dominion;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionNew;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.dominion.ralph.SexActionRalphDiscount;

/**
 * @since 0.1.6?
 * @version 0.1.97
 * @author Innoxia
 */
public class SexManagerRalphDiscount extends SexManagerDefault {
	
	public SexManagerRalphDiscount(Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionNew.UNDER_DESK_RALPH,
				dominants,
				submissives);
	}
	
	@Override
	public SexActionInterface getPartnerSexAction(SexActionInterface sexActionPlayer) {
		if(Sex.getAvailableSexActionsPartner().contains(SexActionRalphDiscount.PARTNER_PENETRATES)) {
			return SexActionRalphDiscount.PARTNER_PENETRATES;
		} else if(Sex.getAvailableSexActionsPartner().contains(SexActionRalphDiscount.PARTNER_PENETRATES_ANUS)) {
			return SexActionRalphDiscount.PARTNER_PENETRATES_ANUS;
		}
		
		return super.getPartnerSexAction(sexActionPlayer);
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
	}
	
}
