package com.base.utils;

import java.util.Comparator;

import com.base.game.inventory.clothing.ClothingType;

/**
 * Compares by rarity.
 * 
 * @since 0.1.2
 * @version 0.1.2
 * @author Innoxia
 */
public class ClothingRarityComparator implements Comparator<ClothingType> {
	@Override
	public int compare(ClothingType first, ClothingType second) {
		int result = first.getRarity().compareTo(second.getRarity());
		if (result != 0) {
			return result;
		} else {
			return first.getzLayer() - second.getzLayer();
		}
	}
}
