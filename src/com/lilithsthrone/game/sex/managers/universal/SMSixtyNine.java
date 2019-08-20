package com.lilithsthrone.game.sex.managers.universal;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;

/**
 * @since 0.1.69
 * @version 0.1.97
 * @author Innoxia
 */
public class SMSixtyNine extends SexManagerDefault {

	public SMSixtyNine(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.SIXTY_NINE,
				dominants,
				submissives);
	}
	
}
