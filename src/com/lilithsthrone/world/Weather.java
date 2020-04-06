package com.lilithsthrone.world;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.1.96
 * @author Innoxia
 */
public enum Weather {
	
	CLOUD("cloudy", PresetColour.GENERIC_GOOD),
	
	CLEAR("clear", PresetColour.GENERIC_GOOD),
	
	RAIN("raining", PresetColour.GENERIC_BAD),

	SNOW("snowing", PresetColour.GENERIC_BAD),
	
	MAGIC_STORM_GATHERING("stormy sky", PresetColour.GENERIC_ARCANE),
	
	MAGIC_STORM("arcane storm", PresetColour.GENERIC_ARCANE);

	private String name;
	private Colour colour;

	private Weather(String name, Colour colour) {
		this.name = name;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}
}
