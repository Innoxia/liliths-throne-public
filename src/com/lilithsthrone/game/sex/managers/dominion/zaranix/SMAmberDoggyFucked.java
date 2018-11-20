package com.lilithsthrone.game.sex.managers.dominion.zaranix;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.95
 * @version 0.2.8
 * @author Innoxia
 */
public class SMAmberDoggyFucked extends SexManagerDefault {

	public SMAmberDoggyFucked(Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionType.DOGGY_STYLE,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}

	@Override
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		return Sex.getNumberOfOrgasms(Main.game.getAmber())>0;
	}
}
