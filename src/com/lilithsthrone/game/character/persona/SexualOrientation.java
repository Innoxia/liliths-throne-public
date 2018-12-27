package com.lilithsthrone.game.character.persona;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.79
 * @version 0.2.7
 * @author Innoxia
 */
public enum SexualOrientation {
	ANDROPHILIC("androphilic", Colour.MASCULINE, SexualOrientationPreference.THREE_AVERAGE),

	GYNEPHILIC("gynephilic",Colour.FEMININE, SexualOrientationPreference.THREE_AVERAGE),

	AMBIPHILIC("ambiphilic", Colour.ANDROGYNOUS, SexualOrientationPreference.THREE_AVERAGE);

	private String name;
	private Colour colour;
	private SexualOrientationPreference orientationPreferenceDefault;

	private SexualOrientation(String name, Colour colour, SexualOrientationPreference orientationPreferenceDefault) {
		this.name = name;
		this.colour = colour;
		this.orientationPreferenceDefault = orientationPreferenceDefault;
	}

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}
	
	public SexualOrientationPreference getOrientationPreferenceDefault() {
		return orientationPreferenceDefault;
	}
}
