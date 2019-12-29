package com.lilithsthrone.game.settings;

/**
 * @since 0.1.95
 * @version 0.3.5.5
 * @author FeiFongWong, Innoxia
 */
public enum ForcedTFTendency {
	
	FEMININE_HEAVY(true, false, "Feminine+", "There is a strong chance that forced transformations will make you more feminine regardless of NPC tastes."),
	
	FEMININE(true, false, "Feminine", "While NPC tastes still matter, forced transformations will often make you more feminine."),
	
	NEUTRAL(false, false, "Neutral", "Gender effects of forced transformations will be determined solely by the tastes and whims of the controlling NPC, and the inherent randomness of the universe."),
	
	MASCULINE(false, true, "Masculine", "While NPC tastes still matter, forced transformations will often make you more masculine."),
	
	MASCULINE_HEAVY(false, true, "Masculine+", "There is a strong chance that forced transformations will make you more masculine regardless of NPC tastes.");


	private String name;
	private String description;
	private boolean feminine;
	private boolean masculine;
	
	private ForcedTFTendency(boolean feminine, boolean masculine, String name, String description) {
		this.name = name;
		this.description = description;
		this.feminine = feminine;
		this.masculine = masculine;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public boolean isFeminine() {
		return feminine;
	}

	public boolean isMasculine() {
		return masculine;
	}
}
