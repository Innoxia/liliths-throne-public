package com.lilithsthrone.game.sex.managers.dominion.zaranix;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.95
 * @version 0.2.8
 * @author Innoxia
 */
public class SMZaranixCockSucking extends SexManagerDefault {

	public SMZaranixCockSucking(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.CHAIR_SEX_ORAL,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}

	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return false;
	}
	
	@Override
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		return Sex.getNumberOfOrgasms(Main.game.getNpc(Zaranix.class))>=1;
	}

}
