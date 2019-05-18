package com.lilithsthrone.game.character.persona;

import com.lilithsthrone.utils.Colour;

import java.util.HashMap;
import java.util.Map;

import com.lilithsthrone.game.settings.ContentPreferenceValue;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.79
 * @version 0.2.7
 * @author Innoxia
 */
public enum SexualOrientation {
	ANDROPHILIC("androphilic", Colour.MASCULINE, ContentPreferenceValue.THREE_AVERAGE),

	GYNEPHILIC("gynephilic",Colour.FEMININE, ContentPreferenceValue.THREE_AVERAGE),

	AMBIPHILIC("ambiphilic", Colour.ANDROGYNOUS, ContentPreferenceValue.THREE_AVERAGE);

	private String name;
	private Colour colour;
	private ContentPreferenceValue orientationPreferenceDefault;

	private SexualOrientation(String name, Colour colour, ContentPreferenceValue orientationPreferenceDefault) {
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
	
	public ContentPreferenceValue getOrientationPreferenceDefault() {
		return orientationPreferenceDefault;
	}
	
	public static SexualOrientation getSexualOrientationFromUserPreferences(int gynephilicWeight, int ambiphilicWeight, int androphilicWeight) {
		Map<SexualOrientation, Integer> weightsMap = new HashMap<SexualOrientation, Integer>();
		weightsMap.put(GYNEPHILIC,gynephilicWeight);
		weightsMap.put(AMBIPHILIC,ambiphilicWeight);
		weightsMap.put(ANDROPHILIC,androphilicWeight);
		return ContentPreferenceValue.getValueFromPreferences(
				Main.getProperties().orientationPreferencesMap,
				weightsMap,
				SexualOrientation.AMBIPHILIC);
	}
}
