package com.lilithsthrone.game.inventory.item;

import com.lilithsthrone.game.inventory.enchanting.PossibleItemEffect;

import java.util.List;

/**
 * Container class for fetish potions
 *
 * @since 0.3.6.4
 * @version 0.3.6.4
 * @author Stadler76
 */
public class FetishPotion extends AbstractPotion {

	public FetishPotion(AbstractItemType itemType, List<PossibleItemEffect> effects) {
		super(itemType, effects);
	}
}
