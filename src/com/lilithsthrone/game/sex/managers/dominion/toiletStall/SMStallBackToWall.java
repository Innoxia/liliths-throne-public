package com.lilithsthrone.game.sex.managers.dominion.toiletStall;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class SMStallBackToWall extends SexManagerDefault {

	public SMStallBackToWall(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.BACK_TO_WALL_STALL,
				dominants,
				submissives);
	}
	
}
