package com.lilithsthrone.game.sex.managers.dominion;

import java.util.ArrayList;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;

/**
 * @since 0.1.95
 * @version 0.1.99
 * @author Innoxia
 */
public class SMStocks extends SexManagerDefault {

	public SMStocks(boolean vaginalAllowed, boolean analAllowed, boolean oralAllowed, Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionType.STOCKS_SEX,
				dominants,
				submissives);
		
		for(GameCharacter character : submissives.keySet()) {
			orificesBannedMap.put(character, new ArrayList<>());
		}
		
		if(!vaginalAllowed) {
			for(GameCharacter character : submissives.keySet()) {
				orificesBannedMap.get(character).add(SexAreaOrifice.VAGINA);
			}
		}
		
		if(!analAllowed) {
			for(GameCharacter character : submissives.keySet()) {
				orificesBannedMap.get(character).add(SexAreaOrifice.ANUS);
			}
		}
		
		if(!oralAllowed) {
			for(GameCharacter character : submissives.keySet()) {
				orificesBannedMap.get(character).add(SexAreaOrifice.MOUTH);
			}
		}
	}

	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character){
		return getDominants().containsKey(character);
	}
	
	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character){
		return getDominants().containsKey(character);
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}
}
