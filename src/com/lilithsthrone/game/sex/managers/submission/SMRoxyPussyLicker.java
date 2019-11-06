package com.lilithsthrone.game.sex.managers.submission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.submission.Roxy;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.6
 * @version 0.3.5.5
 * @author Innoxia
 */
public class SMRoxyPussyLicker extends SexManagerDefault {
	
	public SMRoxyPussyLicker(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPosition.LYING_DOWN,
				dominants,
				submissives);
	}

	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
		map.put(Main.game.getNpc(Roxy.class),
				Util.newArrayListOfValues(
						CoverableArea.VAGINA));
		return map;
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
	}

	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
		return false;
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
		return Sex.getNumberOfOrgasms(Main.game.getNpc(Roxy.class))>=1;
	}

	@Override
	public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
		return new ArrayList<>();
	}
}
