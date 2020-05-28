package com.lilithsthrone.game.sex.managers.dominion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;

/**
 * @since 0.3.7.5
 * @version 0.3.7.5
 * @author Innoxia
 */
public class SMDominionExpress extends SexManagerDefault {
	
	private Map<GameCharacter, SexType> preferences;
	private Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap;
	
	public SMDominionExpress(AbstractSexPosition position,
			Map<GameCharacter, SexSlot> dominants,
			Map<GameCharacter, SexSlot> submissives,
			Map<GameCharacter, SexType> preferences,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		super(position,
				dominants,
				submissives);
		this.preferences = preferences;
		this.exposeAtStartOfSexMap = exposeAtStartOfSexMap;
	}
	
	@Override
	public boolean isPublicSex() {
		return false;
	}
	@Override
	public SexControl getSexControl(GameCharacter character) {
		return SexControl.ONGOING_ONLY; // So Natalya doesn't start anything else.
	}
	@Override
	public boolean isAbleToEquipSexClothing(GameCharacter character){
		return false;
	}
	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character){
		return true;
	}
	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
		return false;
	}
	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return false;
	}
	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		return exposeAtStartOfSexMap;
	}
	@Override
	public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
		return new ArrayList<>();
	}
	@Override
	public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
		if(preferences.containsKey(character)) {
			return preferences.get(character);
		}
		return super.getForeplayPreference(character, targetedCharacter);
	}
	@Override
	public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
		if(!character.isPlayer()) {
			return getForeplayPreference(character, targetedCharacter);
		}
		return character.getMainSexPreference(targetedCharacter);
	}
}
