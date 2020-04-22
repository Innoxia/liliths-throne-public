package com.lilithsthrone.game.character.persona;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.5
 * @version 0.3.5
 * @author Innoxia
 */
public enum PersonalityCategory {

	CORE("core", PresetColour.GENERIC_EXCELLENT),
	
	COMBAT("combat", PresetColour.GENERIC_COMBAT),
	
	SEX("sex", PresetColour.GENERIC_SEX),
	
	SPEECH("speech", PresetColour.BASE_PURPLE_LIGHT);

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
