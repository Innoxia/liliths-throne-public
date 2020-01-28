package com.lilithsthrone.world;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.1.96
 * @author Innoxia
 */
public enum Weather {
	
	CLOUD("cloudy", Colour.GENERIC_GOOD),
	
	CLEAR("clear", Colour.GENERIC_GOOD),
	
	RAIN("raining", Colour.GENERIC_BAD),

	SNOW("snowing", Colour.GENERIC_BAD),
	
	MAGIC_STORM_GATHERING("stormy sky", Colour.GENERIC_ARCANE),
	
	MAGIC_STORM("arcane storm", Colour.GENERIC_ARCANE);

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
