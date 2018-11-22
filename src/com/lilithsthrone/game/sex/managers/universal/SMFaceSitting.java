package com.lilithsthrone.game.sex.managers.universal;

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
public class SMFaceSitting extends SexManagerDefault {
	
	public SMFaceSitting(Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionType.FACE_SITTING,
				dominants,
				submissives);
	}
	
}
