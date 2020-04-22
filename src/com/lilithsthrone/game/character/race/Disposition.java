package com.lilithsthrone.game.character.race;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.1.0
 * @author Innoxia
 */
public enum Disposition {

	CIVILIZED("civilized", PresetColour.CLOTHING_GREEN),
	NEUTRAL("neutral", PresetColour.CLOTHING_BLUE),
	UNPREDICTABLE("unpredictable", PresetColour.CLOTHING_ORANGE),
	SAVAGE("savage", PresetColour.CLOTHING_RED);

	private String name;
	private Colour colour;

	private Disposition(String name, Colour colour) {
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
