package com.lilithsthrone.game.sex.managers.universal;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;

/**
 * @since 0.1.69
 * @version 0.3.4
 * @author Innoxia
 */
public class SMAgainstWall extends SexManagerDefault {

	public SMAgainstWall(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPosition.AGAINST_WALL,
				dominants,
				submissives);
	}
	
}
