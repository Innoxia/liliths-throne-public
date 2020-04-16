package com.lilithsthrone.game.character.gender;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.69
 * @version 0.1.69
 * @author Innoxia
 */
public enum AndrogynousIdentification {
	FEMININE("feminine", PresetColour.FEMININE),
	CLOTHING_FEMININE("clothing feminine", PresetColour.ANDROGYNOUS),
	CLOTHING_MASCULINE("clothing masculine", PresetColour.ANDROGYNOUS),
	MASCULINE("masculine", PresetColour.MASCULINE);
	
	private String name;
	private Colour colour;
	
	private AndrogynousIdentification(String name, Colour colour) {
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
