package com.lilithsthrone.game.sex.managers.universal;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPositionOther;
import com.lilithsthrone.game.sex.positions.SexSlot;

/**
 * @since 0.1.69.9
 * @version 0.3.3.10
 * @author Innoxia
 */
public class SMChair extends SexManagerDefault {

	public SMChair(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionOther.SITTING,
				dominants,
				submissives);
	}

}
