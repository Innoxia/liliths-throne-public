package com.lilithsthrone.utils.comparators;

import java.util.Comparator;

import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;

/**
 * @since 0.1.66
 * @version 0.3.8
 * @author Innoxia
 */
public class InventoryClothingComparator implements Comparator<AbstractClothing> {

	/**
	 * @param slot is compared in level of priority
	 * @return a number for that order hat->top clothing->lower clothing->shoes/watches/gloves->accessories->feet->less common accessories->groin->privates->piercings
	 */
	public int getClothingOrder(InventorySlot slot){
		switch (slot){
			case HEAD:
				return 14;
			case TORSO_OVER:
				return 13;
			case TORSO_UNDER:
				return 12;
			case LEG:
				return 11;
			case CHEST:
				return 10;
			case STOMACH:
				return 9;
			case FOOT:
				return 8;
			case HAND:
			case WRIST:
			case FINGER:
				return 7;
			case HAIR:
			case NECK:
			case HIPS:
				return 6;
			case SOCK:
			case ANKLE:
				return 5;
			case EYES:
			case HORNS:
			case WINGS:
			case TAIL:
				return 4;
			case GROIN:
				return 3;
			case MOUTH:
			case NIPPLE:
			case ANUS:
			case PENIS:
			case VAGINA:
				return 2;
			case PIERCING_EAR:
			case PIERCING_NOSE:
			case PIERCING_TONGUE:
			case PIERCING_LIP:
			case PIERCING_STOMACH:
			case PIERCING_NIPPLE:
			case PIERCING_VAGINA:
			case PIERCING_PENIS:
				return 1;
		}
		return 4;
	}

	@Override
	public int compare(AbstractClothing first, AbstractClothing second) {

		if(first.equals(second)){
			return 0;
		}

		//overhauled comparation:
		//!enchant>z-layer>rarity>alphabetical

		if(first.isEnchantmentKnown() && !second.isEnchantmentKnown()) {
			return 1;
		} else if(!first.isEnchantmentKnown() && second.isEnchantmentKnown()) {
			return -1;
		}
		//both are known/unknown

		int result = (int) Math.signum(getClothingOrder(second.getClothingType().getEquipSlots().get(0)) - getClothingOrder(first.getClothingType().getEquipSlots().get(0)));

		if(result == 0){

			result = first.getRarity().compareTo(second.getRarity());
			if(result == 0){

				result = first.getName().compareTo(second.getName());
				if(result == 0){

					result = first.getClothingType().toString().compareTo(second.getClothingType().toString());

					if(result==0) {
						if(first.getColour(0)!=null) {
							if(second.getColour(0)!=null) {

								result = first.getColour(0).getName().compareTo(second.getColour(0).getName());
								if(result == 0){
									//should be 0 but for the sake or brevity let's leave it at that
									return (int) -Math.signum(first.hashCode()-second.hashCode());
									//return 0;
								} else {
									return result;
								}
							} else {
								return 1;
							}
						}
						return -1;
					} else {
						return result;
					}
				} else {
					return result;
				}
			} else {
				return result;
			}
		} else {
			return result;
		}
	}
}
