package com.lilithsthrone.utils.comparators;

import java.util.Comparator;

import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;

/**
 * Compares by rarity.
 * 
 * @since 0.1.2
 * @version 0.3.2
 * @author Innoxia
 */
public class WeaponTypeRarityComparator implements Comparator<AbstractWeaponType> {
	@Override
	public int compare(AbstractWeaponType first, AbstractWeaponType second) {
		int result = first.getRarity().compareTo(second.getRarity());
		if (result != 0) {
			return result;
		} else {
			return (int) (first.getDamage() - second.getDamage());
		}
	}
}
