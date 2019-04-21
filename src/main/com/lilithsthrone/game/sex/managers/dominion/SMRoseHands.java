package com.lilithsthrone.game.sex.managers.dominion;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;

/**
 * @since 0.1.7?
 * @version 0.1.97
 * @author Innoxia
 */
public class SMRoseHands extends SexManagerDefault {

	public SMRoseHands(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.HANDS_ROSE,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return true;
	}
	
	@Override
	public boolean isItemUseAvailable() {
		return false;
	}
	
	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character){
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
