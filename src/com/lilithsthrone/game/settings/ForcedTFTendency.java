package com.lilithsthrone.game.settings;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.95
 * @version 0.3.5.5
 * @author FeiFongWong, Innoxia
 */
public enum ForcedTFTendency {
	
	FEMININE_HEAVY(true, false, "Feminine+", "There is a strong chance that forced transformations will make you more feminine regardless of NPC tastes.", PresetColour.FEMININE_PLUS),
	
	FEMININE(true, false, "Feminine", "While NPC tastes still matter, forced transformations will often make you more feminine.", PresetColour.FEMININE),
	
	NEUTRAL(false, false, "Neutral", "Gender effects of forced transformations will be determined solely by the tastes and whims of the controlling NPC, and the inherent randomness of the universe.", PresetColour.ANDROGYNOUS),
	
	MASCULINE(false, true, "Masculine", "While NPC tastes still matter, forced transformations will often make you more masculine.", PresetColour.MASCULINE),
	
	MASCULINE_HEAVY(false, true, "Masculine+", "There is a strong chance that forced transformations will make you more masculine regardless of NPC tastes.", PresetColour.MASCULINE_PLUS);
	
	
	private final String name;
	private final String description;
	private final boolean feminine;
	private final boolean masculine;
	private final Colour colour;
	
	ForcedTFTendency(boolean feminine, boolean masculine, String name, String description, Colour colour) {
		this.name = name;
		this.description = description;
		this.feminine = feminine;
		this.masculine = masculine;
		this.colour = colour;
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
	
	public Colour getColour() {
		return colour;
	}
}
