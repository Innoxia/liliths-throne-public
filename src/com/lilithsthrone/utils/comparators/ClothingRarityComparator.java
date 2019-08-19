package com.lilithsthrone.utils.comparators;

import java.util.Comparator;

import com.lilithsthrone.game.inventory.clothing.AbstractClothing;

/**
 * Compares by rarity, using the InventorySlot in index 0 of available equip slots.
 * 
 * @since 0.1.0
 * @version 0.3.4
 * @author Innoxia
 */
public class ClothingRarityComparator implements Comparator<AbstractClothing> {
	@Override
	public int compare(AbstractClothing first, AbstractClothing second) {
		if (!first.isEnchantmentKnown() && !second.isEnchantmentKnown()) {
			return first.getClothingType().getEquipSlots().get(0).compareTo(second.getClothingType().getEquipSlots().get(0));
			
		} else if (!first.isEnchantmentKnown()) {
			return -1;
			
		} else if (!second.isEnchantmentKnown()) {
			return 1;
		}
		
		int result = first.getRarity().compareTo(second.getRarity());
		
		if (result != 0) {
			return result;
		} else {
			return first.getClothingType().getEquipSlots().get(0).compareTo(second.getClothingType().getEquipSlots().get(0));
		}
	}
}
