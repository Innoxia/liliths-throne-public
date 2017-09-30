package com.lilithsthrone.game.character.effects;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.53
 * @version 0.1.69
 * @author Innoxia
 */
public enum PerkCategory {
	PHYSICAL(Colour.ATTRIBUTE_STRENGTH),
	ARCANE(Colour.ATTRIBUTE_INTELLIGENCE),
	FITNESS(Colour.ATTRIBUTE_FITNESS),
	FETISH(Colour.ATTRIBUTE_CORRUPTION);

	private Colour colour;

	private PerkCategory(Colour colour) {
		this.colour = colour;
	}

	public Colour getColour() {
		return colour;
	}
}
