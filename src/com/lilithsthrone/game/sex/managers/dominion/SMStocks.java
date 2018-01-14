package com.lilithsthrone.game.sex.managers.dominion;

import java.util.ArrayList;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.SexPositionNew;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;

/**
 * @since 0.1.95
 * @version 0.1.97
 * @author Innoxia
 */
public class SMStocks extends SexManagerDefault {

	public SMStocks(boolean vaginalAllowed, boolean analAllowed, boolean oralAllowed, Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionNew.STOCKS_SEX,
				dominants,
				submissives);
		
		for(GameCharacter character : submissives.keySet()) {
			orificesBannedMap.put(character, new ArrayList<>());
		}
		
		if(!vaginalAllowed) {
			for(GameCharacter character : submissives.keySet()) {
				orificesBannedMap.get(character).add(OrificeType.VAGINA_PARTNER);
			}
		}
		
		if(!analAllowed) {
			for(GameCharacter character : submissives.keySet()) {
				orificesBannedMap.get(character).add(OrificeType.ANUS_PARTNER);
			}
		}
		
		if(!oralAllowed) {
			for(GameCharacter character : submissives.keySet()) {
				orificesBannedMap.get(character).add(OrificeType.MOUTH_PARTNER);
			}
		}
	}

}
