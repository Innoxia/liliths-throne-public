package com.lilithsthrone.game.sex.managers.universal;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class SMChairOral extends SexManagerDefault {

	public SMChairOral(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.CHAIR_SEX_ORAL,
				dominants,
				submissives);
	}

}
