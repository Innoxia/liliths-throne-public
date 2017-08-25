package com.base.game.character.body.valueEnums;

import com.base.utils.Colour;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum LipSize {

	ZERO_THIN(0, "thin", Colour.GENERIC_SIZE_ONE),
	ONE_AVERAGE(1, "average-sized", Colour.GENERIC_SIZE_TWO),
	TWO_FULL(2, "full", Colour.GENERIC_SIZE_THREE),
	THREE_PLUMP(3, "plump", Colour.GENERIC_SIZE_FOUR),
	FOUR_HUGE(4, "huge", Colour.GENERIC_SIZE_FIVE);
	
	
	private int value;
	private String descriptor;
	private Colour colour;

	private LipSize(int value, String descriptor, Colour colour) {
		this.value = value;
		this.descriptor = descriptor;
		this.colour = colour;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return descriptor;
	}
	
	public static LipSize getLipSizeFromInt(int inches) {
		for(LipSize ls : LipSize.values()) {
			if(inches == ls.getValue()) {
				return ls;
			}
		}
		return ZERO_THIN;
	}

	public Colour getColour() {
		return colour;
	}
}
