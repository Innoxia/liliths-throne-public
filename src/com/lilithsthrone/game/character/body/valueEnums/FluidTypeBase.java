package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.0
 * @version 0.3.2
 * @author Innoxia
 */
public enum FluidTypeBase {
	
	CUM(PresetColour.CUM),
	
	GIRLCUM(PresetColour.GIRLCUM),
	
	MILK(PresetColour.MILK);
	
	
	private Colour colour;

	private FluidTypeBase(Colour colour) {
		this.colour = colour;
	}

	public Colour getColour() {
		return colour;
	}
}
