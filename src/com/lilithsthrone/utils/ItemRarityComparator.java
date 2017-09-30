package com.lilithsthrone.utils;

import java.util.Comparator;

import com.lilithsthrone.game.inventory.item.AbstractItemType;

/**
 * Compares by rarity.
 * 
 * @since 0.1.2
 * @version 0.1.84
 * @author Innoxia
 */
public class ItemRarityComparator implements Comparator<AbstractItemType> {
	@Override
	public int compare(AbstractItemType first, AbstractItemType second) {
		int result = first.getRarity().compareTo(second.getRarity());
		if (result != 0) {
			return result;
		} else {
			return first.getValue() - second.getValue();
		}
	}
}
