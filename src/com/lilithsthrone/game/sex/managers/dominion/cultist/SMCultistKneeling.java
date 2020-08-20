package com.lilithsthrone.game.sex.managers.dominion.cultist;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPositionUnique;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.69
 * @version 0.3.4
 * @author Innoxia
 */
public class SMCultistKneeling extends SexManagerDefault {

	public SMCultistKneeling(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionUnique.KNEELING_ORAL_CULTIST,
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
	public boolean isPlayerAbleToStopSex() {
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
