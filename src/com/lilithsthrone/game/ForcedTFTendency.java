package com.lilithsthrone.game;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author FeiFongWong
 */
public enum ForcedTFTendency {
	
	FEMININE_HEAVY("Feminine (Heavy)", "There is a strong chance that forced transformations will make you more feminine regardless of NPC tastes."),
	
	FEMININE("Feminine", "While NPC tastes still matter, forced transformations will often make you more feminine."),
	
	NEUTRAL("Neutral", "Gender effects of forced transformations will be determined solely by the tastes and whims of the controlling NPC, and the inherent randomness of the universe."),
	
	MASCULINE("Masculine", "While NPC tastes still matter, forced transformations will often make you more masculine."),
	
	MASCULINE_HEAVY("Masculine (Heavy)", "There is a strong chance that forced transformations will make you more masculine regardless of NPC tastes.");


	private String name;
	private String description;

	
	private ForcedTFTendency(String name, String description) {
		this.name = name;
		this.description = description;

	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}



}
