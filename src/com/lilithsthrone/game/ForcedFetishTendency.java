package com.lilithsthrone.game;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author FeiFongWong
 */
public enum ForcedFetishTendency {
	
	FEMININE_HEAVY("Feminine (Heavy)", "There is a strong chance that forced transformations will make you more feminine regardless of NPC tastes.", Colour.FEMININE),
	
	FEMININE("Feminine", "While NPC tastes still matter, forced transformations will often make you more feminine.", Colour.FEMININE),
	
	NEUTRAL("Neutral", "Gender effects of forced trasformations will be determined solely by the tastes and whims of the controlling NPC.", Colour.ANDROGYNOUS),
	
	MASCULINE("Masculine", "While NPC tastes still matter, forced transformations will often make you more masculine.", Colour.MASCULINE),
	
	MASCULINE_HEAVY("Masculine (Heavy)", "There is a strong chance that forced transformations will make you more masculine regardless of NPC tastes.", Colour.MASCULINE);


	private String name;
	private String description;
	private Colour colour;

	
	private ForcedFetishTendency(String name, String description, Colour colour) {
		this.name = name;
		this.description = description;
		this.colour = colour;

	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public Colour getColour() {
		return colour;
	}


}
