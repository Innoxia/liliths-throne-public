package com.lilithsthrone.game.sex.managers.dominion.zaranix;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;

/**
 * @since 0.1.95
 * @version 0.3.4
 * @author Innoxia
 */
public class SMAmberDoggyFucked extends SexManagerDefault {

	public SMAmberDoggyFucked(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPosition.ALL_FOURS,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}
	
//	@Override
//	public boolean isPositionChangingAllowed(GameCharacter character) {
//		return false;
//	}
}
