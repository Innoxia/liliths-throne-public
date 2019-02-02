package com.lilithsthrone.game.dialogue.places.dominion;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Jules;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.3.1
 * @author Innoxia
 */
public class SMJulesCockSucking extends SexManagerDefault {

	public SMJulesCockSucking(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.KNEELING_ORAL,
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
	public boolean isPublicSex() {
		return true;
	}
	
	@Override
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		return Sex.getNumberOfOrgasms(Main.game.getNpc(Jules.class))>0;
	}

	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
		return !character.isPlayer();
	}
	
	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		return Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Jules.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
	}
}
