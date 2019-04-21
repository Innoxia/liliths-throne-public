package com.lilithsthrone.game.sex.managers.universal;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;

/**
 * @since 0.1.98
 * @version 0.1.98
 * @author Innoxia
 */
public class SMMissionary extends SexManagerDefault {

	public SMMissionary(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.MISSIONARY,
				dominants,
				submissives);
	}
}
