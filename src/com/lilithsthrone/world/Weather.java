package com.lilithsthrone.world;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public enum Weather {
	
	CLOUD("cloudy", PresetColour.BASE_GREY),
	
	CLEAR("clear", PresetColour.BASE_YELLOW_PALE),
	
	RAIN("raining", PresetColour.BASE_BLUE),

	SNOW("snowing", PresetColour.BASE_BLUE_LIGHT),
	
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
