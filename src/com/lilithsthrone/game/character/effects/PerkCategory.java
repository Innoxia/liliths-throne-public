package com.lilithsthrone.game.character.effects;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.53
 * @version 0.2.11
 * @author Innoxia
 */
public enum PerkCategory {
	
	JOB(Colour.BASE_BROWN),
	
	PHYSICAL(Colour.ATTRIBUTE_PHYSIQUE),
	
	LUST(Colour.ATTRIBUTE_CORRUPTION),
	
	ARCANE(Colour.ATTRIBUTE_ARCANE),
	
	// Just for colouring:
	PHYSICAL_EARTH(Colour.SPELL_SCHOOL_EARTH),
	PHYSICAL_WATER(Colour.SPELL_SCHOOL_WATER),
	ARCANE_FIRE(Colour.SPELL_SCHOOL_FIRE),
	ARCANE_AIR(Colour.SPELL_SCHOOL_AIR);

	private Colour colour;

	private PerkCategory(Colour colour) {
		this.colour = colour;
	}

	public Colour getColour() {
		return colour;
	}
}
