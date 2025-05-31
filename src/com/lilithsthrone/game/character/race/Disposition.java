package com.lilithsthrone.game.character.race;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.1.0
 * @author Innoxia
 */
public enum Disposition {

	CIVILIZED("civilised", PresetColour.CLOTHING_GREEN, 1.0f),
	
	NEUTRAL("neutral", PresetColour.CLOTHING_BLUE, 1.5f),
	
	UNPREDICTABLE("unpredictable", PresetColour.CLOTHING_ORANGE, 2.0f),
	
	SAVAGE("savage", PresetColour.CLOTHING_RED, 4.0f);

	private String name;
	private Colour colour;
	private float bodyHairModifier;

	private Disposition(String name, Colour colour, float bodyHairModifier) {
		this.name = name;
		this.colour = colour;
		this.bodyHairModifier = bodyHairModifier;
	}

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}

	public float getBodyHairModifier() {
		return bodyHairModifier;
	}
}
