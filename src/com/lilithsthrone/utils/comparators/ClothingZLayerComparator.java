package com.lilithsthrone.utils.comparators;

import java.util.Comparator;

import com.lilithsthrone.game.inventory.clothing.AbstractClothing;

/**
 * Compares by zLayer.
 * 
 * @since 0.1.0
 * @version 0.1.0
 * @author Innoxia
 */
public class ClothingZLayerComparator implements Comparator<AbstractClothing> {
	@Override
	public int compare(AbstractClothing o1, AbstractClothing o2) {
		if (o2.getClothingType().getzLayer() > o1.getClothingType().getzLayer())
			return 1;
		else if (o2.getClothingType().getzLayer() == o1.getClothingType().getzLayer())
			return 0;
		else
			return -1;
	}
}