package com.lilithsthrone.world;

/**
 * Regions of the world map, used to help the game decide what races to spawn where.
 * 
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public enum WorldRegion {
	
	// Misc:
	MISC,
	OLD_WORLD,
    FELICIA_APARTMENT,
	
	// Cities
	DOMINION,
	HARPY_NESTS,
	SUBMISSION,
	FIELD_CITY,
	DESERT_CITY,
	JUNGLE_CITY,
	SEA_CITY,
	
	// Foloi Fields:
	FIELDS,
	WOODLAND,
	RIVER,

	// Desert:
	SAVANNAH,
	DESERT,
	VOLCANO,
	
	// Jungle:
	JUNGLE,
	
	// Sea:
	SEA,
	
	// Mountains:
	YOUKO_FOREST,
	MOUNTAINS,
	SNOW;
	
//	private final String offspringTextFilePath;
//
//	private WorldRegion() {
//		this.offspringTextFilePath = "characters/offspring/dominionAlleyway";
//	}
//
//	private WorldRegion(String offspringTextFilePath) {
//		this.offspringTextFilePath = offspringTextFilePath;
//	}
//
//	public String getOffspringTextFilePath() {
//		return offspringTextFilePath;
//	}
}
