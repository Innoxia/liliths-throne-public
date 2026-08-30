package com.lilithsthrone.utils.comparators;

import java.util.Comparator;

import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;

/**
 * @since 0.4.10.10
 * @version 0.4.10.10
 * @author Innoxia
 */
public class ItemEffectComparator implements Comparator<ItemEffect> {
	@Override
	public int compare(ItemEffect first, ItemEffect second) {
		AbstractItemEffectType firstType = first.getItemEffectType();
		boolean firstTypeImportant = firstType==ItemEffectType.CLOTHING || firstType==ItemEffectType.WEAPON;

		AbstractItemEffectType secondType = second.getItemEffectType();
		boolean secondTypeImportant = secondType==ItemEffectType.CLOTHING || secondType==ItemEffectType.WEAPON;
		
		if(firstTypeImportant && !secondTypeImportant) {
			return 1;
		} else if(!firstTypeImportant && secondTypeImportant) {
			return -1;
		} else {
			// First compare primary modifiers
			int primaryComparison = first.getPrimaryModifier().compareTo(second.getPrimaryModifier());
			if(primaryComparison != 0) {
				return primaryComparison;
				
			} else {
				// If primary modifiers are equal, compare secondary modifiers
				int secondaryComparison = first.getSecondaryModifier().compareTo(second.getSecondaryModifier());
				if(secondaryComparison != 0) {
					return secondaryComparison;
					
				} else {
					// If secondary modifiers are equal, compare potencies
					int potencyComparison = first.getPotency().compareTo(second.getPotency());
					if(potencyComparison != 0) {
						return potencyComparison;
						
					} else {
						// If potencies are equal, compare limits
						int limitComparison = first.getLimit() - second.getLimit();
						if(potencyComparison != 0) {
							return limitComparison;
							
						} else {
							return 0;
						}
					}
				}
			}
		}
		
	}
}
