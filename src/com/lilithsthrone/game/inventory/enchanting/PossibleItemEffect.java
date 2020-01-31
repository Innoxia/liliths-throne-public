package com.lilithsthrone.game.inventory.enchanting;

/**
 * Container class for TF effects
 *
 * @since 0.3.6.4
 * @version 0.3.6.4
 * @author Stadler76
 */
public class PossibleItemEffect {
	private final ItemEffect effect;
	private String message = "";
	private int chance = 0;

	public PossibleItemEffect(ItemEffect effect, String message) {
		this.effect = effect;
		this.message = message;
	}

	public PossibleItemEffect(ItemEffect effect, int chance) {
		this.effect = effect;
		this.chance = chance;
	}

	public ItemEffect getEffect() {
		return effect;
	}

	public String getMessage() {
		return message;
	}

	public int getChance() {
		return chance;
	}
}
