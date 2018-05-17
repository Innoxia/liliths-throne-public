package com.lilithsthrone.utils;

import java.io.Serializable;
import java.util.Comparator;

import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;

/**
 * @since 0.1.66
 * @version 0.2.5
 * @author Innoxia
 */
public class InventoryWeaponComparator implements Comparator<AbstractWeapon>, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(AbstractWeapon first, AbstractWeapon second) {
		int result = first.getRarity().compareTo(second.getRarity());
		
		if (result != 0) {
			return result;
			
		} else {
			result = first.getWeaponType().toString().compareTo(second.getName());
			
			if(result!=0) {
				return result;
			} else {
				return first.getColour().getName().compareTo(second.getColour().getName());
			}
		}
	}
}