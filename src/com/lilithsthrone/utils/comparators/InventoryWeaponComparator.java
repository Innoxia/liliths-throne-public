package com.lilithsthrone.utils.comparators;

import java.util.Comparator;

import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;

/**
 * @since 0.1.66
 * @version 0.3.8
 * @author Innoxia
 */
public class InventoryWeaponComparator implements Comparator<AbstractWeapon> {

	@Override
	public int compare(AbstractWeapon first, AbstractWeapon second) {
		int result = first.getRarity().compareTo(second.getRarity());
		
		if (result != 0) {
			return result;
			
		} else {
			// If rarity is equal, sort by set type:
			int resultSet = first.getWeaponType().getClothingSet()==second.getWeaponType().getClothingSet()
					?0
					:first.getWeaponType().getClothingSet()==null
						?-1
						:second.getWeaponType().getClothingSet()==null
							?1
							:first.getWeaponType().getClothingSet().getName().compareTo(second.getWeaponType().getClothingSet().getName());
			
			if(resultSet!=0) {
				return resultSet;
			}
			
			result = first.getWeaponType().toString().compareTo(second.getWeaponType().toString());
			
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