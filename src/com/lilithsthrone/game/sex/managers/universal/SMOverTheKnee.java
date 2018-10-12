package com.lilithsthrone.game.sex.managers.universal;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Lionna
 */
public class SMOverTheKnee extends SexManagerDefault {

	public SMOverTheKnee(Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionType.OVERTHEKNEE,
				dominants,
				submissives);
	}

}
