package com.lilithsthrone.game.sex.managers.dominion;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.dominion.RalphOral;

/**
 * @since 0.1.6?
 * @version 0.1.97
 * @author Innoxia
 */
public class SexManagerRalphDiscount extends SexManagerDefault {
	
	public SexManagerRalphDiscount(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.UNDER_DESK_RALPH,
				dominants,
				submissives);
	}
	
	@Override
	public SexActionInterface getPartnerSexAction(SexActionInterface sexActionPlayer) {
		if(Sex.getAvailableSexActionsPartner().contains(RalphOral.PARTNER_PENETRATES)) {
			return RalphOral.PARTNER_PENETRATES;
		} else if(Sex.getAvailableSexActionsPartner().contains(RalphOral.PARTNER_PENETRATES_ANUS)) {
			return RalphOral.PARTNER_PENETRATES_ANUS;
		}
		
		return super.getPartnerSexAction(sexActionPlayer);
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
	}

	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
		return false;
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}
	
}
