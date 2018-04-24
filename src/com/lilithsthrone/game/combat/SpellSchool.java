package com.lilithsthrone.game.combat;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.1
 * @version 0.2.4
 * @author Innoxia
 */
public enum SpellSchool {
	
	// Maximum damage
	FIRE("fire",
			"Enables you to summon a floating ball of fire at will.",
			"No longer affected by 'darkness' effect.",
			Colour.BASE_ORANGE),

	// Slightly biased towards attack
	WATER("water",
			"Allows you to manipulate the temperature and movement of fluids.",
			"Fluid enchantments are free.",
			Colour.BASE_AQUA),
	
	// Slightly biased towards defence
	AIR("air",
			"Allow you to control the temperature and movement of the surrounding air.",
			"Doubles passive energy regeneration.",
			Colour.BASE_BLUE_LIGHT),
	
	// Maximum defence
	EARTH("earth",
			"Allows you to manipulate the properties of physical objects.",
			"Free transmutation of clothing colours.",
			Colour.BASE_BROWN),
	
	// Lust
	ARCANE("arcane",
			"Allows you to sense the arcane currents that are woven throughout the world.",
			"Know the exact time until the next arcane storm breaks.",
			Colour.GENERIC_ARCANE);
	
	
	private String name;
	private String description;
	private Colour colour;
	
	private SpellSchool(String name, String description, String passiveBuff, Colour colour) {
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
