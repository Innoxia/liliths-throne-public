package com.lilithsthrone.game.sex.managers.dominion.gloryHole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;
import com.lilithsthrone.game.sex.positions.SexSlotBipeds;

/**
 * @since 0.2.9
 * @version 0.3.3.10
 * @author Innoxia
 */
public class SMGloryHoleSex extends SexManagerDefault {

	public SMGloryHoleSex(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.GLORY_HOLE_SEX,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}

	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return Sex.isDom(character);
	}

	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
		return false;
	}

	@Override
	public boolean isPlayerAbleToStopSex() {
		return true;
	}
	
	@Override
	public List<InventorySlot> getSlotsConcealed(GameCharacter character) {
		List<InventorySlot> concealedSlots = new ArrayList<>();
		
		if(Sex.getSexPositionSlot(character).equals(SexSlotBipeds.GLORY_HOLE_FUCKED)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.MOUTH);
			concealedSlots.remove(InventorySlot.PENIS);
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.GROIN);
			concealedSlots.remove(InventorySlot.LEG);
			
		} else if(Sex.getSexPositionSlot(character).equals(SexSlotBipeds.GLORY_HOLE_FUCKING)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.PENIS);
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.GROIN);
			concealedSlots.remove(InventorySlot.LEG);
			
		} else if(Sex.getSexPositionSlot(character).equals(SexSlotBipeds.GLORY_HOLE_RECEIVING_ORAL_ONE)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.PENIS);
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.GROIN);
			concealedSlots.remove(InventorySlot.LEG);
			
		}
		
		return concealedSlots;
	}

}
