package com.lilithsthrone.game.sex.managers.dominion;

import java.util.ArrayList;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;

/**
 * @since 0.1.95
 * @version 0.3.1
 * @author Innoxia
 */
public class SMStocks extends SexManagerDefault {

	public SMStocks(boolean vaginalAllowed, boolean analAllowed, boolean oralAllowed, Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.STOCKS_SEX,
				dominants,
				submissives);
		
		for(GameCharacter character : submissives.keySet()) {
			getAreasBannedMap().put(character, new ArrayList<>());
		}
		
		if(!vaginalAllowed) {
			for(GameCharacter character : submissives.keySet()) {
				getAreasBannedMap().get(character).add(SexAreaOrifice.VAGINA);
			}
		}
		
		if(!analAllowed) {
			for(GameCharacter character : submissives.keySet()) {
				getAreasBannedMap().get(character).add(SexAreaOrifice.ANUS);
			}
		}
		
		if(!oralAllowed) {
			for(GameCharacter character : submissives.keySet()) {
				getAreasBannedMap().get(character).add(SexAreaOrifice.MOUTH);
			}
		}
	}

	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character){
		return getDominants().containsKey(character);
	}
	
	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
		return getDominants().containsKey(character);
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}
}
