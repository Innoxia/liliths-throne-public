package com.base.game.character.body.valueEnums;

import com.base.utils.Colour;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum BodyHair {

	NONE(0, "no", Colour.GENERIC_SIZE_ONE),
	MANICURED(1, "manicured", Colour.GENERIC_SIZE_THREE),
	TRIMMED(2, "trimmed", Colour.GENERIC_SIZE_FIVE),
	BUSHY(3, "bushy", Colour.GENERIC_SIZE_SEVEN);
	
	private int value;
	private String descriptor;
	private Colour colour;

	private BodyHair(int value, String descriptor, Colour colour) {
		this.value = value;
		this.descriptor = descriptor;
		this.colour=colour;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return descriptor;
	}

	public Colour getColour() {
		return colour;
	}
}
