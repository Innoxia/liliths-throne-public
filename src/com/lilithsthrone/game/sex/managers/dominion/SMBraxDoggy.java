package com.lilithsthrone.game.sex.managers.dominion;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;

/**
 * @since 0.2.8
 * @version 0.3.3.10
 * @author Innoxia
 */
public class SMBraxDoggy extends SexManagerDefault {

	public SMBraxDoggy(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.DOGGY_STYLE,
				dominants,
				submissives);
	}

	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}
	
	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return false;
	}
}
