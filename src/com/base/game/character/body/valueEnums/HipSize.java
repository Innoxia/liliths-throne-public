package com.base.game.character.body.valueEnums;

import com.base.utils.Colour;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum HipSize {
	
	ZERO_NO_HIPS("completely straight", 0, Colour.GENERIC_SIZE_ONE),
	
	ONE_VERY_NARROW("very narrow", 1, Colour.GENERIC_SIZE_TWO),
	
	TWO_NARROW("narrow", 2, Colour.GENERIC_SIZE_THREE),
	
	THREE_GIRLY("girly", 3, Colour.GENERIC_SIZE_FOUR),
	
	FOUR_WOMANLY("womanly", 4, Colour.GENERIC_SIZE_FIVE),
	
	FIVE_VERY_WIDE("very wide", 5, Colour.GENERIC_SIZE_SIX),
	
	SIX_EXTREMELY_WIDE("extremely wide", 6, Colour.GENERIC_SIZE_SEVEN),
	
	SEVEN_ABSURDLY_WIDE("absurdly wide", 7, Colour.GENERIC_SIZE_EIGHT);

	
	private String descriptor;
	private int size;
	private Colour colour;

	private HipSize(String descriptor, int hipSize, Colour colour) {
		this.descriptor = descriptor;
		this.size = hipSize;
		this.colour=colour;
	}

	public static HipSize getHipSizeFromInt(int hipSize) {
		for(HipSize hs : HipSize.values()) {
			if(hipSize == hs.getValue()) {
				return hs;
			}
		}
		return SEVEN_ABSURDLY_WIDE;
	}

	/**
	 * To fit into a sentence: "You have "+getDescriptor()+" hips."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public int getValue() {
		return size;
	}

	public Colour getColour() {
		return colour;
	}
}
