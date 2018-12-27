package com.lilithsthrone.utils;

import java.util.Comparator;

import com.lilithsthrone.game.inventory.item.AbstractItem;

/**
 * @since 0.1.66
 * @version 0.2.5
 * @author Innoxia
 */
public class InventoryItemComparator implements Comparator<AbstractItem> {

	@Override
	public int compare(AbstractItem first, AbstractItem second) {
		int result = first.getRarity().compareTo(second.getRarity());
		
		if (result != 0) {
			return result;
			
		} else {
			result = first.getItemType().toString().compareTo(second.getItemType().toString());
			
			if(result!=0) {
				return result;
			} else {
				return first.getColour().getName().compareTo(second.getColour().getName());
			}
		}
	}
}