package com.lilithsthrone.utils;

import java.io.Serializable;
import java.util.Comparator;

import com.lilithsthrone.game.inventory.item.AbstractItem;

/**
 * @since 0.1.66
 * @version 0.1.7
 * @author Innoxia
 */
public class InventoryItemComparator implements Comparator<AbstractItem>, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(AbstractItem first, AbstractItem second) {
		int result = first.getRarity().compareTo(second.getRarity());
		
		if (result != 0) {
			return result;
		} else {
			result = first.getName().compareTo(second.getName());
			
			if(result!=0)
				return result;
			else
				return first.getColour().getName().compareTo(second.getColour().getName());
		}
	}
}