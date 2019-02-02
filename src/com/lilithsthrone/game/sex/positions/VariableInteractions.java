package com.lilithsthrone.game.sex.positions;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public abstract class VariableInteractions {
	
	protected GameCharacter getCharacter(SexSlot performerSlot) {
		return Sex.getCharacterInPosition(performerSlot);
	}
	
	public abstract Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot);
	
}
