package com.lilithsthrone.game.settings;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.95
 * @version 0.2.3
 * @author FeiFongWong
 */
public enum ForcedFetishTendency {
	
	BOTTOM_HEAVY("Bottoming+", "Forced fetishes will almost always add bottoming and remove topping activities and behaviors.", PresetColour.BASE_PINK_LIGHT),
	BOTTOM("Bottoming", "While NPC tastes still matter, forced fetishes will usually add bottoming and remove topping activities and behaviors.", PresetColour.BASE_PINK),
	NEUTRAL("Neutral", "Forced fetishes will be determined solely by the tastes and whims of the controlling NPC, and the inherent randomness of the universe.", PresetColour.ANDROGYNOUS),
	TOP ("Topping", "While NPC tastes still matter, forced fetishes will usually add topping and remove bottoming activities and behaviors.", PresetColour.BASE_PURPLE_LIGHT),
	TOP_HEAVY("Topping+", "Forced fetishes will almost always add topping and remove bottoming activities and behaviors.", PresetColour.BASE_PURPLE);

	private final String name;
	private final String description;
	private final Colour colour;

	ForcedFetishTendency(String name, String description, Colour colour) {
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
