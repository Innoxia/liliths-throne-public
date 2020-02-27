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
import com.lilithsthrone.game.sex.positions.SexPositionUnique;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;

/**
 * @since 0.2.9
 * @version 0.3.4
 * @author Innoxia
 */
public class SMGloryHoleSex extends SexManagerDefault {

	public SMGloryHoleSex(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionUnique.GLORY_HOLE_SEX,
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
	public boolean isAbleToRemoveSelfClothing(GameCharacter character){
		return character.isPlayer();
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
		
		if(Sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_FUCKED)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.MOUTH);
			concealedSlots.remove(InventorySlot.PENIS);
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.GROIN);
			concealedSlots.remove(InventorySlot.LEG);
			
		} else if(Sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_FUCKING)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.PENIS);
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.GROIN);
			concealedSlots.remove(InventorySlot.LEG);
			
		} else if(Sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.PENIS);
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.GROIN);
			concealedSlots.remove(InventorySlot.LEG);
			
		}
		
		return concealedSlots;
	}

}
