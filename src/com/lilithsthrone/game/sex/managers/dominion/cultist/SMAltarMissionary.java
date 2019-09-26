package com.lilithsthrone.game.sex.managers.dominion.cultist;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPositionUnique;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.dominion.CultistSexActions;

/**
 * @since 0.1.88
 * @version 0.3.4
 * @author Innoxia
 */
public class SMAltarMissionary extends SexManagerDefault {

	public SMAltarMissionary(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionUnique.MISSIONARY_ALTAR_CULTIST,
				dominants,
				submissives);
	}
	
	@Override
	public SexActionInterface getPartnerSexAction(SexActionInterface sexActionPlayer) {
		// If orgasming, use an orgasm action:
		if (ArousalLevel.getArousalLevelFromValue(Sex.getCharacterPerformingAction().getArousal()) == ArousalLevel.FIVE_ORGASM_IMMINENT) {
			return super.getPartnerSexAction(sexActionPlayer);
		}
		
		if(Sex.isCharacterSealed(Sex.getCharacterPerformingAction())) {
			return CultistSexActions.SEALED;
			
		} else {
			return super.getPartnerSexAction(sexActionPlayer);
		}
	}
	
	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
		return !Sex.isCharacterSealed(character);
	}

	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
		return !Sex.isCharacterSealed(character);
	}
	
	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}

	@Override
	public SexControl getSexControl(GameCharacter character) {
		if(!Sex.isDom(character) && character.isPlayer()) {
			return SexControl.ONGOING_ONLY;
		}
		return super.getSexControl(character);
	}
}
