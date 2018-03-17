package com.lilithsthrone.game.combat;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.1
 * @version 0.2.1
 * @author Innoxia
 */
public enum SpellSchool {
	
	// Maximum damage
	FIRE("fire",
			"The school of fire is concerned with spells which allow the caster to manipulate the destructive force of fire.",
			Colour.BASE_ORANGE),

	// Slightly biased towards attack
	WATER("water",
			"The school of water is concerned with both spells which involve the manipulation of water and other liquids.",
			Colour.BASE_AQUA),
	
	// Slightly biased towards defence
	AIR("air",
			"The school of air is concerned with spells that summon forth gusts of wind, clouds of poisonous fumes, or flashes of lightning.",
			Colour.BASE_BLUE_LIGHT),
	
	// Maximum defence
	EARTH("earth",
			"The school of earth is concerned spells that rely on pure force to either defend the caster, or perform overwhelming attacks.",
			Colour.BASE_BROWN),
	
	// Lust
	ARCANE("arcane",
			"The school of the arcane offers spells which focus purely on increasing a target's arousal and lust.",
			Colour.GENERIC_ARCANE),
	
	// Default
	DEFAULT("natural",
			"Spells which have no particular school association are defined as 'natural' spells.",
			Colour.BASE_GREY);
	
	
	private String name;
	private String description;
	private Colour colour;
	
	private SpellSchool(String name, String description, Colour colour) {
		this.name = name;
		this.description = description;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Colour getColour() {
		return colour;
	}
	
}
