package com.lilithsthrone.game.character.effects;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.53
 * @version 0.2.1
 * @author Innoxia
 */
public enum PerkCategory {
	
	JOB(Colour.BASE_BROWN),
	
	PHYSICAL(Colour.ATTRIBUTE_PHYSIQUE),
	
	BOTH(Colour.ATTRIBUTE_CORRUPTION),
	
	ARCANE(Colour.ATTRIBUTE_ARCANE);

	private Colour colour;

	private PerkCategory(Colour colour) {
		this.colour = colour;
	}

	public Colour getColour() {
		return colour;
	}
}
