package com.lilithsthrone.game.character.effects;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.53
 * @version 0.2.11
 * @author Innoxia
 */
public enum PerkCategory {
	
	JOB(PresetColour.BASE_BROWN),
	
	PHYSICAL(PresetColour.ATTRIBUTE_PHYSIQUE),
	
	LUST(PresetColour.ATTRIBUTE_CORRUPTION),
	
	ARCANE(PresetColour.ATTRIBUTE_ARCANE),
	
	// Just for colouring:
	PHYSICAL_EARTH(PresetColour.SPELL_SCHOOL_EARTH),
	PHYSICAL_WATER(PresetColour.SPELL_SCHOOL_WATER),
	ARCANE_FIRE(PresetColour.SPELL_SCHOOL_FIRE),
	ARCANE_AIR(PresetColour.SPELL_SCHOOL_AIR);

	private Colour colour;

	private PerkCategory(Colour colour) {
		this.colour = colour;
	}

	public Colour getColour() {
		return colour;
	}
}
