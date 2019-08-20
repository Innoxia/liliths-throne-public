package com.lilithsthrone.game.sex.managers.universal;

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
public class SMFaceSitting extends SexManagerDefault {
	
	public SMFaceSitting(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.FACE_SITTING,
				dominants,
				submissives);
	}
	
}
