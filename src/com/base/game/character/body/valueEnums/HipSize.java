package com.base.game.character.body.valueEnums;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.0
 * @version 0.1.0
 * @author Innoxia
 */
public enum HipSize {
	ZERO_NO_HIPS("completely straight",
			0),
	ONE_VERY_NARROW("very narrow",
			1),
	TWO_NARROW("narrow",
			2),
	THREE_GIRLY("girly",
			3),
	FOUR_WOMANLY("womanly",
			4),
	FIVE_VERY_WIDE("very wide",
			5),
	SIX_EXTREMELY_WIDE("extremely wide",
			6),
	SEVEN_ABSURDLY_WIDE("absurdly wide",
			7);

	private String descriptor;
	private int hipSize;

	private HipSize(String descriptor, int hipSize) {
		this.descriptor = descriptor;
		this.hipSize = hipSize;
	}

	public static HipSize getHipSizeFromInt(int hipSize) {
		if (hipSize <= ZERO_NO_HIPS.hipSize)
			return ZERO_NO_HIPS;
		else if (hipSize <= ONE_VERY_NARROW.hipSize)
			return ONE_VERY_NARROW;
		else if (hipSize <= TWO_NARROW.hipSize)
			return TWO_NARROW;
		else if (hipSize <= THREE_GIRLY.hipSize)
			return THREE_GIRLY;
		else if (hipSize <= FOUR_WOMANLY.hipSize)
			return FOUR_WOMANLY;
		else if (hipSize <= FIVE_VERY_WIDE.hipSize)
			return FIVE_VERY_WIDE;
		else if (hipSize <= SIX_EXTREMELY_WIDE.hipSize)
			return SIX_EXTREMELY_WIDE;
		else
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
