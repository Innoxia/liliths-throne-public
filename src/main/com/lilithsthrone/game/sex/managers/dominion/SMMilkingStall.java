package com.lilithsthrone.game.sex.managers.dominion;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;

/**
 * @since 0.2.3
 * @version 0.2.3
 * @author Innoxia
 */
public class SMMilkingStall extends SexManagerDefault {

	public SMMilkingStall(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.MILKING_STALL,
				dominants,
				submissives);
	}

	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character){
		return getDominants().containsKey(character);
	}
	
	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
		return getDominants().containsKey(character);
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}
}
