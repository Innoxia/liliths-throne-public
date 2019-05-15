package com.lilithsthrone.game.sex.managers.universal;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;

/**
 * @since 0.1.83
 * @version 0.1.97
 * @author Innoxia
 */
public class SMCowgirl extends SexManagerDefault {

	public SMCowgirl(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.COWGIRL,
				dominants,
				submissives);
	}

}
