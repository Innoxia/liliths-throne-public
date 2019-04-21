package com.lilithsthrone.game.character.gender;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.69
 * @version 0.1.69
 * @author Innoxia
 */
public enum AndrogynousIdentification {
	FEMININE("feminine", Colour.FEMININE),
	CLOTHING_FEMININE("clothing feminine", Colour.ANDROGYNOUS),
	CLOTHING_MASCULINE("clothing masculine", Colour.ANDROGYNOUS),
	MASCULINE("masculine", Colour.MASCULINE);
	
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
