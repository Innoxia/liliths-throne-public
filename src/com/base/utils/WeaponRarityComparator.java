package com.base.utils;

import java.util.Comparator;

import com.base.game.inventory.weapon.WeaponType;

/**
 * Compares by rarity.
 * 
 * @since 0.1.2
 * @version 0.1.63
 * @author Innoxia
 */
public class WeaponRarityComparator implements Comparator<WeaponType> {
	@Override
	public int compare(WeaponType first, WeaponType second) {
		int result = first.getRarity().compareTo(second.getRarity());
		if (result != 0) {
			return result;
		} else {
			return (int) (first.getDamageLevel().getDamageModifier() - second.getDamageLevel().getDamageModifier());
		}
	}
}
