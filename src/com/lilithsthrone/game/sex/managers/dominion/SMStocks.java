package com.lilithsthrone.game.sex.managers.dominion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStocks;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.95
 * @version 0.3.4
 * @author Innoxia
 */
public class SMStocks extends SexManagerDefault {

	public SMStocks(boolean vaginalAllowed, boolean analAllowed, boolean oralAllowed, Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPosition.STOCKS,
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
	public List<AbstractSexPosition> getAllowedSexPositions() {
		return Util.newArrayListOfValues(
				SexPosition.STOCKS);
	}
	
	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}
	
	@Override
	public SexControl getSexControl(GameCharacter character) {
		if(Sex.getSexPositionSlot(character)==SexSlotStocks.LOCKED_IN_STOCKS
				|| Sex.getSexPositionSlot(character)==SexSlotStocks.LOCKED_IN_STOCKS_TWO
				|| Sex.getSexPositionSlot(character)==SexSlotStocks.LOCKED_IN_STOCKS_THREE
				|| Sex.getSexPositionSlot(character)==SexSlotStocks.LOCKED_IN_STOCKS_FOUR) {
			return SexControl.NONE;
		}
		return super.getSexControl(character);
	}
}
