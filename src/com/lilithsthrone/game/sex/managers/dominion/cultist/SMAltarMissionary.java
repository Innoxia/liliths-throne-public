package com.lilithsthrone.game.sex.managers.dominion.cultist;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionNew;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.dominion.cultist.SASpecialCultist;

/**
 * @since 0.1.88
 * @version 0.1.97
 * @author Innoxia
 */
public class SMAltarMissionary extends SexManagerDefault {

	public SMAltarMissionary(Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionNew.MISSIONARY_ALTAR_CULTIST,
				dominants,
				submissives);
	}
	
	@Override
	public SexActionInterface getPartnerSexAction(SexActionInterface sexActionPlayer) {
		// If orgasming, use an orgasm action:
		if (ArousalLevel.getArousalLevelFromValue(Sex.getActivePartner().getArousal()) == ArousalLevel.FIVE_ORGASM_IMMINENT) {
			return super.getPartnerSexAction(sexActionPlayer);
		}
		
		if(((Cultist)Sex.getActivePartner()).isSealedSex()) {
			return SASpecialCultist.PARTNER_SEALED;
			
		} else {
			return super.getPartnerSexAction(sexActionPlayer);
		}
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return true;
	}
	
	@Override
	public boolean isPlayerCanRemoveOwnClothes(){
		return true;
	}
	
	@Override
	public boolean isPlayerCanRemovePartnersClothes(){
		return true;
	}
	
	@Override
	public boolean isPartnerCanRemoveOwnClothes(){
		return true;
	}
	
	@Override
	public boolean isPartnerCanRemovePlayersClothes(){
		return false;
	}
}
