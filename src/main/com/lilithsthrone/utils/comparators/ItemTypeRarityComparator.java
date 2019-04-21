package com.lilithsthrone.utils.comparators;

import java.util.Comparator;

import com.lilithsthrone.game.inventory.item.AbstractItemType;

/**
 * Compares by rarity.
 * 
 * @since 0.1.2
 * @version 0.3.2
 * @author Innoxia
 */
public class ItemTypeRarityComparator implements Comparator<AbstractItemType> {
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
