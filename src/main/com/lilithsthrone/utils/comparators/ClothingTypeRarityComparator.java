package com.lilithsthrone.utils.comparators;

import java.util.Comparator;

import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;

/**
 * Compares by rarity.
 * 
 * @since 0.1.2
 * @version 0.1.84
 * @author Innoxia
 */
public class ClothingTypeRarityComparator implements Comparator<AbstractClothingType> {
	@Override
	public int compare(AbstractClothingType first, AbstractClothingType second) {
		int result = first.getRarity().compareTo(second.getRarity());
		if (result != 0) {
			return result;
		} else {
			return first.getzLayer() - second.getzLayer();
		}
	}
}
