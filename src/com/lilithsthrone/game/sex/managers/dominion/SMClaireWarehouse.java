package com.lilithsthrone.game.sex.managers.dominion;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.5
 * @version 0.3.5
 * @author Innoxia
 */
public class SMClaireWarehouse extends SexManagerDefault {

	public SMClaireWarehouse(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPosition.AGAINST_WALL,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
	}
	
	@Override
	public List<AbstractSexPosition> getAllowedSexPositions() {
		return Util.newArrayListOfValues(
				SexPosition.AGAINST_WALL);
	}
}
