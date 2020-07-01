package com.lilithsthrone.game.character.effects;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.8.2
 * @version 0.3.8.2
 * @author Innoxia
 */
public enum EffectBenefit {

	BENEFICIAL(PresetColour.GENERIC_GOOD),
	
	NEUTRAL(PresetColour.GENERIC_NEUTRAL),
	
	DETRIMENTAL(PresetColour.GENERIC_BAD);
	
	private Colour colour;

	private EffectBenefit(Colour colour) {
		this.colour = colour;
	}

	public Colour getColour() {
		return colour;
	}
	
}
