package com.lilithsthrone.game.character.race;

/**
 * These enum values are used instead of defining a float so that the relative spawn frequencies can easily be tweaked later on.
 * The loss of precision by using these enum values was considered to be worth it to make sure that spawn frequencies can easily be changed if need be.
 * 
 * @since 0.3.1
 * @version 0.4.6
 * @author Innoxia
 */
public enum SubspeciesSpawnRarity {

	// These three values should probably not be used unless you know what you're doing:
	NEGATIVE_THREE_ZERO(0f),
	NEGATIVE_TWO_MYTHICAL(0.001f),
	NEGATIVE_ONE_LEGENDARY(0.01f),
	
	// These are the 'safe' values to use. Commented values correspond to those which were present in versions prior to 0.4.6:
	ONE(0.1f), // Should be used for 'extremely rare' spawns
	TWO(0.2f), // Should be used for 'very rare' spawns
	THREE(0.3f), // Should be used for 'rare' spawns
	FOUR(0.4f),
	FIVE(0.5f), // Should be used for 'uncommon' spawns
	SIX(0.6f),
	SEVEN(0.7f),
	EIGHT(0.8f),
	NINE(0.9f),
	TEN(1), // Should be used for 'common' spawns
	
	// These should be used very sparingly, and only if a subspecies really should be over-represented in an area:
	TWENTY(2),
	FORTY(4),
	EIGHTY(8);

	private float chanceMultiplier;

	private SubspeciesSpawnRarity(float chanceMultiplier) {
		this.chanceMultiplier = chanceMultiplier;
	}

	public static SubspeciesSpawnRarity getSubspeciesSpawnRarityFromString(String rarity) {
		if(rarity.equalsIgnoreCase("ZERO_EXTREMELY_RARE")) {
			return ONE;
		} else if(rarity.equalsIgnoreCase("ONE_VERY_RARE")) {
			return TWO;
		} else if(rarity.equalsIgnoreCase("TWO_RARE")) {
			return THREE;
		} else if(rarity.equalsIgnoreCase("THREE_UNCOMMON")) {
			return FIVE;
		} else if(rarity.equalsIgnoreCase("FOUR_COMMON")) {
			return TEN;
		}
		
		return SubspeciesSpawnRarity.valueOf(rarity);
	}
	
	public float getChanceMultiplier() {
		return chanceMultiplier;
	}
}
