package com.lilithsthrone.game.sex.managers.dominion;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;

/**
 * @since 0.1.97
 * @version 0.3
 * @author Innoxia
 */
public class SMVickyOverDesk extends SexManagerDefault {

	public SMVickyOverDesk(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.MISSIONARY_DESK,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}
	
	@Override
	public SexPace getForcedSexPace(GameCharacter character) {
		if(!character.isPlayer()) {
			return SexPace.DOM_ROUGH;
		}
		return null;
	}
}
