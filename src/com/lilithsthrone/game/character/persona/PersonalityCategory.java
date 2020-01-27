package com.lilithsthrone.game.character.persona;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.3.5
 * @version 0.3.5
 * @author Innoxia
 */
public enum PersonalityCategory {

	CORE("core", Colour.GENERIC_EXCELLENT),
	
	COMBAT("combat", Colour.GENERIC_COMBAT),
	
	SEX("sex", Colour.GENERIC_SEX),
	
	SPEECH("speech", Colour.BASE_PURPLE_LIGHT);

	private String name;
	private Colour colour;
	
	private PersonalityCategory( String name, Colour colour) {
		this.colour = colour;
		this.name = name;
	}

	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}
}
