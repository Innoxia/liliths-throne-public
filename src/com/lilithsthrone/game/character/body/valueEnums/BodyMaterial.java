package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.83
 * @version 0.2.1
 * @author Innoxia
 */
public enum BodyMaterial {

	FLESH("flesh", Colour.BASE_PINK_LIGHT),
	SLIME("slime", Colour.RACE_SLIME),
	FIRE("fire", Colour.BASE_ORANGE),
	ICE("ice", Colour.BASE_BLUE_LIGHT),
	RUBBER("rubber", Colour.BASE_BLACK);
	
	private String descriptor;
	private Colour colour;
	
	private BodyMaterial(String descriptor, Colour colour) {
		this.descriptor = descriptor;
		this.colour = colour;
	}

	public String getName() {
		return descriptor;
	}
	
	public Colour getColour() {
		return colour;
	}
}
