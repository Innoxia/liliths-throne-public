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
import com.lilithsthrone.main.Main;

/**
 * @since 0.3.7.5
 * @version 0.4.2.2
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
		if(!Main.sex.isDom(character)) {
			return SexControl.ONGOING_ONLY; // So the player can't start anything else.
		}
		return super.getSexControl(character);
	}
	@Override
	public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
		return clothingToEquip.isCondom();
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
