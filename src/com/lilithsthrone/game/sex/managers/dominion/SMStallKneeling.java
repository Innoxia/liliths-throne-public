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
public class SMStallKneeling extends SexManagerDefault {

	public SMStallKneeling(Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionType.KNEELING_ORAL_STALL,
				dominants,
				submissives);
	}

}
