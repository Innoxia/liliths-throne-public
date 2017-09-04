package com.base.game.character.race;

import com.base.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.1.0
 * @author Innoxia
 */
public enum Disposition {

	CIVILIZED("civilized", Colour.CLOTHING_GREEN), NEUTRAL("neutral", Colour.CLOTHING_BLUE), UNPREDICTABLE("unpredictable", Colour.CLOTHING_ORANGE), SAVAGE("savage", Colour.CLOTHING_RED);

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
