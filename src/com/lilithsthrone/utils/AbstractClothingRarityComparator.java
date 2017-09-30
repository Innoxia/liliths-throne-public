package com.lilithsthrone.utils;

import java.util.Comparator;

import com.lilithsthrone.game.inventory.clothing.AbstractClothing;

/**
 * Compares by rarity.
 * 
 * @since 0.1.0
 * @version 0.1.0
 * @author Innoxia
 */
public class AbstractClothingRarityComparator implements Comparator<AbstractClothing> {
	@Override
	public int compare(AbstractClothing first, AbstractClothing second) {
		if (!first.isEnchantmentKnown() && !second.isEnchantmentKnown())
			return first.getClothingType().getSlot().compareTo(second.getClothingType().getSlot());
		else if (!first.isEnchantmentKnown())
			return -1;
		else if (!second.isEnchantmentKnown())
			return 1;

		int result = first.getRarity().compareTo(second.getRarity());
		if (result != 0) {
			return result;
		} else {
			return first.getClothingType().getSlot().compareTo(second.getClothingType().getSlot());
		}
	}
}
