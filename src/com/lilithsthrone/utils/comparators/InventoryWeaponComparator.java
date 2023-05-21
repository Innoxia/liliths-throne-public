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
		if(first.equals(second)){
			return 0;
		}
		//overhauled comparation:
		//rarity->alphabetical->type->color->enchants
		int result = -first.getRarity().compareTo(second.getRarity());
		
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
						result = -first.getColour(0).getName().compareTo(second.getColour(0).getName());
						if(result!=0){
							return result;
						} else {

							if(!first.getEffects().isEmpty()) {
								if(!second.getEffects().isEmpty()) {

									if(second.getEffects().size()>first.getEffects().size()){
										return 1;
									} else if(first.getEffects().size()>second.getEffects().size()){
										return -1;
									}
									result = 0;
									int n = 0;
									while(first.getEffects().size() < n && second.getEffects().size() < n){
										result += first.getEffects().get(n).getSecondaryModifier().getName()
												.compareTo(second.getEffects().get(n).getSecondaryModifier().getName());
										n++;
									}
									result = Math.min(1,Math.max(-1, result));

									if(result == 0){
										//return (int) -Math.signum(first.hashCode()-second.hashCode()); if you're never too sure
										return 0;
									} else {
										return result;
									}
								} else {
									return 1;
								}
							} else {
								return -1;
							}
						}
					} else {
						return 1;
					}
				} else {
					return -1;
				}
			}
		}
	}
}