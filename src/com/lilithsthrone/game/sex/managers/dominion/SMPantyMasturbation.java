package com.lilithsthrone.game.sex.managers.dominion;

import java.util.HashMap;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayasRoom;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;
import com.lilithsthrone.main.Main;

/**
 * @since 0.2.5
 * @version 0.3.3.10
 * @author Innoxia
 */
public class SMPantyMasturbation extends SexManagerDefault {

	public SMPantyMasturbation(Map<GameCharacter, SexSlot> dominants) {
		super(SexPositionBipeds.PANTY_MASTURBATION,
				dominants,
				new HashMap<>());
	}
	
	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}

	@Override
	public String applyEndSexEffects() {
		return Main.game.getPlayer().addClothing(LilayasRoom.lilayasPanties, false);
	}
}
