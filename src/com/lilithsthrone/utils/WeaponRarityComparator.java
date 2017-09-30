package com.lilithsthrone.utils;

import java.util.Comparator;

import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;

/**
 * Compares by rarity.
 * 
 * @since 0.1.2
 * @version 0.1.84
 * @author Innoxia
 */
public class WeaponRarityComparator implements Comparator<AbstractWeaponType> {
	@Override
	public int compare(AbstractWeaponType first, AbstractWeaponType second) {
		int result = first.getRarity().compareTo(second.getRarity());
		if (result != 0) {
			return result;
		} else {
			return (int) (first.getDamageLevel().getDamageModifier() - second.getDamageLevel().getDamageModifier());
		}
	}
}
