package com.lilithsthrone.game.sex.managers.dominion.zaranix;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPositionUnique;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;

/**
 * @since 0.2.3
 * @version 0.3.4
 * @author Innoxia
 */
public class SMPetOral extends SexManagerDefault {

	public SMPetOral(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionUnique.PET_ORAL,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}

	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return false;
	}
	
	@Override
	public boolean isPartnerUsingForeplayActions() {
		return false;
	}
	
	@Override
	public boolean isPublicSex() {
		return true;
	}

	@Override
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		return Main.sex.getNumberOfOrgasms(partner)>=1;
	}
}
