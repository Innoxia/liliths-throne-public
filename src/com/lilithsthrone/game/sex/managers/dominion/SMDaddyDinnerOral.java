package com.lilithsthrone.game.sex.managers.dominion;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPositionOther;
import com.lilithsthrone.game.sex.positions.SexSlot;

/**
 * @since 0.3.3.10
 * @version 0.3.3.10
 * @author Innoxia
 */
public class SMDaddyDinnerOral extends SexManagerDefault {

	public SMDaddyDinnerOral(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionOther.SITTING,
				dominants,
				submissives);
	}

	@Override
	public boolean isPublicSex() {
		return false;
	}
	
	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return false;
	}
	
	@Override
	public boolean isAbleToEquipSexClothing(GameCharacter character){
		return false;
	}
	
	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character){
		return false;
	}
	
	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
		return false;
	}
	
	@Override
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		return (partner instanceof Daddy)
				&& Sex.getNumberOfOrgasms(partner)>=1;
	}
	
	@Override
	public boolean isAppendStartingExposedDescriptions(GameCharacter character) {
		return false;
	}
}
