package com.base.game.character.body.valueEnums;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum HipSize {
	ZERO_NO_HIPS("completely straight", 0),
	
	ONE_VERY_NARROW("very narrow", 1),
	
	TWO_NARROW("narrow", 2),
	
	THREE_GIRLY("girly", 3),
	
	FOUR_WOMANLY("womanly", 4),
	
	FIVE_VERY_WIDE("very wide", 5),
	
	SIX_EXTREMELY_WIDE("extremely wide", 6),
	
	SEVEN_ABSURDLY_WIDE("absurdly wide", 7);

	
	private String descriptor;
	private int hipSize;

	private HipSize(String descriptor, int hipSize) {
		this.descriptor = descriptor;
		this.hipSize = hipSize;
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
		return hipSize;
	}
}
