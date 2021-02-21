package com.lilithsthrone.game.character.race;

/**
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public enum SubspeciesSpawnRarity {

	// These three values should probably not be used unless you know what you're doing:
	NEGATIVE_THREE_ZERO(0f),
	NEGATIVE_TWO_MYTHICAL(0.001f),
	NEGATIVE_ONE_LEGENDARY(0.01f),
	
	// These are the 'safe' values to use:
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
