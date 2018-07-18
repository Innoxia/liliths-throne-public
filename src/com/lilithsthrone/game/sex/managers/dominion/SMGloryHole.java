package com.lilithsthrone.game.sex.managers.dominion;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;

/**
 * @since 0.2.9
 * @version 0.2.9
 * @author Innoxia
 */
public class SMGloryHole extends SexManagerDefault {

	public SMGloryHole(Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionType.GLORY_HOLE,
				dominants,
				submissives);
	}
	
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}
	
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return Sex.isDom(character);
	}

}
