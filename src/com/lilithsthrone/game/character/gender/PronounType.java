package com.lilithsthrone.game.character.gender;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.86
 * @version 0.1.86
 * @author Innoxia
 */
public enum PronounType {
	FEMININE("feminine", Colour.FEMININE),
	NEUTRAL("androgynous", Colour.ANDROGYNOUS),
	MASCULINE("masculine", Colour.MASCULINE);
	
	private String name;
	private Colour colour;
	
	private PronounType(String name, Colour colour) {
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
