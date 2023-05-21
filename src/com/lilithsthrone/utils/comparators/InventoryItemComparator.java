package com.lilithsthrone.utils.comparators;

import java.util.Comparator;

import com.lilithsthrone.game.inventory.item.AbstractItem;

/**
 * @since 0.1.66
 * @version 0.3.8
 * @author Innoxia
 */
public class InventoryItemComparator implements Comparator<AbstractItem> {

	@Override
	public int compare(AbstractItem first, AbstractItem second) {
		if(first.equals(second)){
			return 0;
		}
		int result = first.getRarity().compareTo(second.getRarity());
		
		if (result != 0) {
			return result;
			
		} else {
			result = first.getItemType().toString().compareTo(second.getItemType().toString());
			
			if(result!=0) {
				return result;
			} else {
				if(first.getColour(0)!=null) {
					if(second.getColour(0)!=null) {
						result = first.getColour(0).getName().compareTo(second.getColour(0).getName());

						if(result == 0){


							if(!first.getEffects().isEmpty()) {
								if(!second.getEffects().isEmpty()) {

									if(second.getEffects().size()>first.getEffects().size()){
										return 1;
									} else if(first.getEffects().size()>second.getEffects().size()){
										return -1;
									}
									result = 0;
									int n = 0;
									while(first.getEffects().get(n) != null && second.getEffects().get(n) != null){
										result += first.getEffects().get(n).getSecondaryModifier().getName()
												.compareTo(second.getEffects().get(n).getSecondaryModifier().getName());
										n++;
									}
									result = Math.min(1,Math.max(-1, result));

									if(result == 0){
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

						} else {
							return result;
						}
					} else {
						return 1;
					}
				}
				return -1;
			}
		}
	}
}