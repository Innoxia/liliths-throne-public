package com.lilithsthrone.game.sex.managers.submission;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;

/**
 * @since 0.3
 * @version 0.3
 * @author Innoxia
 */
public class SMLyssiethSex extends SexManagerDefault {

	public SMLyssiethSex(Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionType.MISSIONARY_DESK,
				dominants,
				submissives);
	}

	@Override
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		return Sex.getNumberOfOrgasms(partner)>0; // PLayer orgasms with Lyssieth, so this is fine
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}
}
