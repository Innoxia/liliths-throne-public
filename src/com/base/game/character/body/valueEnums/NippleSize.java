package com.base.game.character.body.valueEnums;

import com.base.utils.Colour;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum NippleSize {

	ZERO_TINY(0, "tiny", Colour.GENERIC_SIZE_ONE),
	ONE_SMALL(1, "small", Colour.GENERIC_SIZE_TWO),
	TWO_BIG(2, "big", Colour.GENERIC_SIZE_THREE),
	THREE_LARGE(3, "large", Colour.GENERIC_SIZE_FOUR),
	FOUR_MASSIVE(4, "massive", Colour.GENERIC_SIZE_FIVE);
	
	
	private int value;
	private String descriptor;
	private Colour colour;

	private NippleSize(int value, String descriptor, Colour colour) {
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
	
	public static NippleSize getNippleSizeFromInt(int inches) {
		for(NippleSize as : NippleSize.values()) {
			if(inches == as.getValue()) {
				return as;
			}
		}
		return ZERO_TINY;
	}

	public Colour getColour() {
		return colour;
	}
}
