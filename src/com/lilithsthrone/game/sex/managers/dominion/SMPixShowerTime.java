package com.lilithsthrone.game.sex.managers.dominion;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;

/**
 * @since 0.1.69.9
 * @version 0.1.97
 * @author Innoxia
 */
public class SMPixShowerTime extends SexManagerDefault {

	public SMPixShowerTime(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.SHOWER_TIME_PIX,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
	}
	
	@Override
	public boolean isItemUseAvailable() {
		return false;
	}
	
	@Override
	public boolean isPlayerStartNaked() {
		return true;
	}

	@Override
	public boolean isPartnerStartNaked() {
		return true;
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}

}
