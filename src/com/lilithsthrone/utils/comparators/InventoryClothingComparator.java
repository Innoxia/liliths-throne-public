package com.lilithsthrone.utils.comparators;

import java.util.Comparator;

import com.lilithsthrone.game.inventory.clothing.AbstractClothing;

/**
 * @since 0.1.66
 * @version 0.3.8
 * @author Innoxia
 */
public class InventoryClothingComparator implements Comparator<AbstractClothing> {

	@Override
	public int compare(AbstractClothing first, AbstractClothing second) {
		if(!first.isEnchantmentKnown() && !second.isEnchantmentKnown()) {
			return 0;
		} else if(first.isEnchantmentKnown() && !second.isEnchantmentKnown()) {
			return -1;
		} else if(!first.isEnchantmentKnown() && second.isEnchantmentKnown()) {
			return 1;
		}
		
		int result = first.getRarity().compareTo(second.getRarity());
		
		if (result != 0) {
			return result;
			
		} else {
			result = first.getClothingType().toString().compareTo(second.getClothingType().toString());
			
			if(result!=0) {
				return result;
			} else {
				if(first.getColour(0)!=null) {
					if(second.getColour(0)!=null) {
						return first.getColour(0).getName().compareTo(second.getColour(0).getName());
					} else {
						return 1;
					}
				}
				return 0;
			}
		}
	}
}
