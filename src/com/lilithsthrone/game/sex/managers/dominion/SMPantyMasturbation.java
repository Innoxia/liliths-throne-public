package com.lilithsthrone.game.sex.managers.dominion;

import java.util.HashMap;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayasRoom;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.main.Main;

/**
 * @since 0.2.5
 * @version 0.2.5
 * @author Innoxia
 */
public class SMPantyMasturbation extends SexManagerDefault {

	public SMPantyMasturbation(Map<GameCharacter, SexPositionSlot> dominants) {
		super(SexPositionType.PANTY_MASTURBATION,
				dominants,
				new HashMap<>());
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}

	@Override
	public String applyEndSexEffects() {
		return Main.game.getPlayer().addClothing(LilayasRoom.lilayasPanties, false);
	}
}
