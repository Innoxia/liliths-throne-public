package com.lilithsthrone.game.sex.managers.dominion;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class SMStallFaceToWall extends SexManagerDefault {

	public SMStallFaceToWall(Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionType.FACING_WALL_STALL,
				dominants,
				submissives);
		
	}

}
