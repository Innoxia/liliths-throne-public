package com.lilithsthrone.game.sex.managers.dominion.cultist;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionNew;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.88
 * @version 0.1.97
 * @author Innoxia
 */
public class SMAltarMissionarySealed extends SexManagerDefault {

	public SMAltarMissionarySealed(Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionNew.MISSIONARY_ALTAR_SEALED_CULTIST,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return Sex.isDom(Main.game.getPlayer());
	}
	
	@Override
	public boolean isPlayerCanRemoveOwnClothes(){
		return false;
	}
	
	@Override
	public boolean isPlayerCanRemovePartnersClothes(){
		return false;
	}
	
	@Override
	public boolean isPartnerCanRemoveOwnClothes(){
		return true;
	}
	
	@Override
	public boolean isPartnerCanRemovePlayersClothes(){
		return true;
	}

}
