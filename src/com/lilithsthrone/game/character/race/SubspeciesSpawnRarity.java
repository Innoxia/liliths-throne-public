package com.lilithsthrone.game.character.race;

/**
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public enum SubspeciesSpawnRarity {

	ZERO_EXTREMELY_RARE(0.1f),
	ONE_VERY_RARE(0.2f),
	TWO_RARE(0.3f),
	THREE_UNCOMMON(0.5f),
	FOUR_COMMON(1);

	private float chanceMultiplier;

	private SubspeciesSpawnRarity(float chanceMultiplier) {
		this.chanceMultiplier = chanceMultiplier;
	}

	public float getChanceMultiplier() {
		return chanceMultiplier;
	}
}
