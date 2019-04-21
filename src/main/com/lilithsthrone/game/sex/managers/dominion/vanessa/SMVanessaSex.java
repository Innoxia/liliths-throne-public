package com.lilithsthrone.game.sex.managers.dominion.vanessa;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Vanessa;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.2
 * @version 0.3.2
 * @author Innoxia
 */
public class SMVanessaSex extends SexManagerDefault {
	
	public SMVanessaSex(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.MISSIONARY_DESK,
				dominants,
				submissives);
	}

	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		return Util.newHashMapOfValues(
				new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)),
				new Value<>(Main.game.getNpc(Vanessa.class), Util.newArrayListOfValues(CoverableArea.VAGINA)));
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}
	
	@Override
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		// She stops after player is satisfied:
		return Sex.getNumberOfOrgasms(Main.game.getPlayer())>=Main.game.getPlayer().getOrgasmsBeforeSatisfied();
	}
	
}
