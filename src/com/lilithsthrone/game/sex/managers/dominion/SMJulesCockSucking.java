package com.lilithsthrone.game.sex.managers.dominion;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Jules;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.3.5.1
 * @author Innoxia
 */
public class SMJulesCockSucking extends SexManagerDefault {

	public SMJulesCockSucking(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPosition.STANDING,
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
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		return Main.sex.getNumberOfOrgasms(Main.game.getNpc(Jules.class))>=Main.game.getNpc(Jules.class).getOrgasmsBeforeSatisfied();
	}

	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
		return !character.isPlayer();
	}
	
	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		return Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Jules.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
	}
	
	@Override
	public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
		return new ArrayList<>();
	}
}
