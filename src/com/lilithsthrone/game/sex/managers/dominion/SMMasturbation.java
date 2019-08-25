package com.lilithsthrone.game.sex.managers.dominion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPositionOther;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.5
 * @version 0.3.4
 * @author Innoxia
 */
public class SMMasturbation extends SexManagerDefault {

	public SMMasturbation(Map<GameCharacter, SexSlot> dominants) {
		super(SexPositionOther.MASTURBATION,
				dominants,
				new HashMap<>());
	}
	
	@Override
	public List<AbstractSexPosition> getAllowedSexPositions() {
		return Util.newArrayListOfValues(
				SexPositionOther.MASTURBATION);
	}
	
	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}
	
}
