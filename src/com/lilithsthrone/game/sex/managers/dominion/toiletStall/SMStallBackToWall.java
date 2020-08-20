package com.lilithsthrone.game.sex.managers.dominion.toiletStall;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.8
 * @version 0.3.4
 * @author Innoxia
 */
public class SMStallBackToWall extends SexManagerDefault {

	public SMStallBackToWall(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPosition.AGAINST_WALL,
				dominants,
				submissives);
	}
	
	@Override
	public List<AbstractSexPosition> getAllowedSexPositions() {
		return Util.newArrayListOfValues(
				SexPosition.STANDING,
				SexPosition.AGAINST_WALL);
	}
}
