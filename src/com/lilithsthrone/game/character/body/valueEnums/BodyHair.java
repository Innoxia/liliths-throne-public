package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.83
 * @version 0.1.85
 * @author Innoxia
 */
public enum BodyHair {

	NONE(0, "none", Colour.GENERIC_SIZE_ONE),
	STUBBLE(1, "stubble", Colour.GENERIC_SIZE_TWO),
	MANICURED(2, "manicured", Colour.GENERIC_SIZE_THREE),
	TRIMMED(3, "trimmed", Colour.GENERIC_SIZE_FOUR),
	NATURAL(4, "natural", Colour.GENERIC_SIZE_FIVE),
	UNKEMPT(6, "unkempt", Colour.GENERIC_SIZE_SIX),
	BUSHY(7, "bushy", Colour.GENERIC_SIZE_SEVEN),
	WILD(8, "wild", Colour.GENERIC_SIZE_EIGHT);
	
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
	
	public static BodyHair getBodyHairFromValue(int value) {
		for(BodyHair bh : BodyHair.values()) {
			if(bh.getValue() == value) {
				return bh;
			}
		}
		return NONE;
	}
}
