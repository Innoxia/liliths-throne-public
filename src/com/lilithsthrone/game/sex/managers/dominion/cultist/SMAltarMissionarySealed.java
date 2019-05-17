package com.lilithsthrone.game.sex.managers.dominion.cultist;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.88
 * @version 0.1.97
 * @author Innoxia
 */
public class SMAltarMissionarySealed extends SexManagerDefault {

	public SMAltarMissionarySealed(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.MISSIONARY_ALTAR_SEALED_CULTIST,
				dominants,
				submissives);
	}

	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character){
		if(character.isPlayer()) {
			return !((Cultist)Sex.getActivePartner()).isSealedSex() || Sex.isDom(Main.game.getPlayer());
		} else {
			return !((Cultist)Sex.getActivePartner()).isSealedSex() || Sex.isDom(Sex.getActivePartner());
		}
	}

	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
		if(character.isPlayer()) {
			return !((Cultist)Sex.getActivePartner()).isSealedSex() || Sex.isDom(Main.game.getPlayer());
		} else {
			return !((Cultist)Sex.getActivePartner()).isSealedSex() || Sex.isDom(Sex.getActivePartner());
		}
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}
}
