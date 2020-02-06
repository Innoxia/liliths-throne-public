package com.lilithsthrone.game.inventory.item;

import com.lilithsthrone.game.inventory.enchanting.PossibleItemEffect;

import java.util.List;

/**
 * Container class for potions
 *
 * @since 0.3.6.4
 * @version 0.3.6.4
 * @author Stadler76
 */
public abstract class AbstractPotion {
	private final AbstractItemType itemType;
	private final List<PossibleItemEffect> effects;

	public AbstractPotion(AbstractItemType itemType, List<PossibleItemEffect> effects) {
		this.itemType = itemType;
		this.effects = effects;
	}

	public AbstractItemType getItemType() {
		return itemType;
	}

	public List<PossibleItemEffect> getEffects() {
		return effects;
	}
}
