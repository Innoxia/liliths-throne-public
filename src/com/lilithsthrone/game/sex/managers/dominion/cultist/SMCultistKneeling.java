package com.lilithsthrone.game.sex.managers.dominion.cultist;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;

/**
 * @since 0.1.69
 * @version 0.2.8
 * @author Innoxia
 */
public class SMCultistKneeling extends SexManagerDefault {

	public SMCultistKneeling(Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionType.KNEELING_ORAL_CULTIST,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}

	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return false;
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
	}
}
