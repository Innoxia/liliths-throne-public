package com.lilithsthrone.game.sex.managers.submission;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public class SMRoxyPussyLicker extends SexManagerDefault {
	
	public SMRoxyPussyLicker(Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionType.FACE_SITTING,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
	}

	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character){
		return false;
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
	public boolean isPartnerWantingToStopSex() {
		return Sex.getNumberOfOrgasms(Sex.getActivePartner())>=1;
	}
	
}
