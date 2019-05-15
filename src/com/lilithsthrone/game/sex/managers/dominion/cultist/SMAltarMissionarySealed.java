package com.lilithsthrone.game.sex.managers.dominion.cultist;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;

/**
 * @since 0.1.88
 * @version 0.3.2
 * @author Innoxia
 */
public class SMAltarMissionarySealed extends SexManagerDefault {

	public SMAltarMissionarySealed(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.MISSIONARY_ALTAR_SEALED_CULTIST,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
		return !Sex.isCharacterSealed(character);
	}

	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
		return !Sex.isCharacterSealed(character);
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}
}
