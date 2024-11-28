package com.lilithsthrone.game.sex.managers.dominion.cultist;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPositionUnique;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.88
 * @version 0.4
 * @author Innoxia
 */
public class SMAltarMissionary extends SexManagerDefault {

	public SMAltarMissionary(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionUnique.MISSIONARY_ALTAR_CULTIST,
				dominants,
				submissives);
	}
	
	@Override
	public SexActionInterface getPartnerSexAction(NPC partner, SexActionInterface sexActionPlayer) {
		// If orgasming, use an orgasm action:
		if (ArousalLevel.getArousalLevelFromValue(Main.sex.getCharacterPerformingAction().getArousal()) == ArousalLevel.FIVE_ORGASM_IMMINENT) {
			return super.getPartnerSexAction(partner, sexActionPlayer);
		}
		
		if(Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.WITCH_SEAL)) {
			return GenericActions.WITCH_SEALED;
			
		} else {
			return super.getPartnerSexAction(partner, sexActionPlayer);
		}
	}
	
	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
		return !Main.sex.isCharacterImmobilised(character);
	}

	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
		return !Main.sex.isCharacterImmobilised(character);
	}
	
	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}

	@Override
	public SexControl getSexControl(GameCharacter character) {
		if(!Main.sex.isDom(character) && character.isPlayer()) {
			return SexControl.ONGOING_ONLY;
		}
		return super.getSexControl(character);
	}
}
