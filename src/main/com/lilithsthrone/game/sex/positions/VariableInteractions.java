package com.lilithsthrone.game.sex.positions;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public abstract class VariableInteractions {
	
	private static GameCharacter characterForPositionTesting;

	public static void setCharacterForPositionTesting(GameCharacter characterForPositionTesting) {
		VariableInteractions.characterForPositionTesting = characterForPositionTesting;
	}
	
	protected static GameCharacter getCharacter(SexSlot slot) {
		GameCharacter performer = Sex.getCharacterInPosition(slot);
		if(performer==null) {
			performer = characterForPositionTesting==null?Main.game.getPlayer():characterForPositionTesting;
		}
		return performer;
	}
	
	public abstract Value<SexSlot, Map<SexSlot, SexActionInteractions>> getSexActionInteractions(SexSlot performerSlot, SexSlot targetSlot);
	
}
