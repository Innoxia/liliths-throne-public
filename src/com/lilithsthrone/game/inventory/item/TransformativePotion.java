package com.lilithsthrone.game.inventory.item;

import java.util.List;

import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.inventory.enchanting.PossibleItemEffect;

/**
 * Container class for TF potions
 *
 * @since 0.3.6.4
 * @version 0.3.6.4
 * @author Stadler76
 */
public class TransformativePotion extends AbstractPotion {
	private final Body body;

	public TransformativePotion(AbstractItemType itemType, List<PossibleItemEffect> effects, Body body) {
		super(itemType, effects);
		this.body = body;
	}

	public TransformativePotion(AbstractItemType itemType, List<PossibleItemEffect> effects) {
		this(itemType, effects, null);
	}

	public Body getBody() {
		return body;
	}
}
