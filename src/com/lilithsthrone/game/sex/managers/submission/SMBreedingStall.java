package com.lilithsthrone.game.sex.managers.submission;

import java.util.ArrayList;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;

/**
 * @since 0.2.7
 * @version 0.3.4.5
 * @author Innoxia
 */
public class SMBreedingStall extends SexManagerDefault {

	public SMBreedingStall(boolean vaginalAllowed, boolean analAllowed, boolean oralAllowed, Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPosition.BREEDING_STALL,
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
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}
	
	@Override
	public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
		if(!character.isPlayer()) {
			return OrgasmBehaviour.CREAMPIE;
		}
		return super.getCharacterOrgasmBehaviour(character);
	}
	
	@Override
	public SexControl getSexControl(GameCharacter character) {
		if(!Sex.isDom(character)) {
			return SexControl.ONGOING_ONLY;
		}
		return super.getSexControl(character);
	}
}
