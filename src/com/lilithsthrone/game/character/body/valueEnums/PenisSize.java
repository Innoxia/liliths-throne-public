package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Colour;

/**
 * Sizes in inches.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum PenisSize {
	/**Utility*/
	NEGATIVE_UTILITY_VALUE("N/A", -1, -1, Colour.GENERIC_SIZE_ONE),

	/**Barely even anything there. (0-1 inches)*/
	ZERO_MICROSCOPIC("tiny", 0, 2, Colour.GENERIC_SIZE_TWO),

	/**Average size for a harpy. (2-3 inches)*/
	ONE_TINY("small", 2, 4, Colour.GENERIC_SIZE_TWO),

	/**Average size for all regular morphs. (4-7 inches)*/
	TWO_AVERAGE("average-sized", 4, 8, Colour.GENERIC_SIZE_THREE),

	/**Average size for a wolf morph. (8-11 inches)*/
	THREE_LARGE("large", 8, 12, Colour.GENERIC_SIZE_FOUR),

	/**Average size for a horse morph. (12-15 inches)*/
	FOUR_HUGE("huge", 12, 16, Colour.GENERIC_SIZE_FIVE),

	/**Straying into the bounds of "world record for a human". Large for a horse morph. (16-19 inches)*/
	FIVE_ENORMOUS("enormous", 16, 20, Colour.GENERIC_SIZE_SIX),

	/**This is just ridiculous... World record for a horse-morph. (20-24 inches)*/
	SIX_GIGANTIC("gigantic", 20, 25, Colour.GENERIC_SIZE_SEVEN),

	/**And this is for "extreme proportion" content. (25-40 inches)*/
	SEVEN_STALLION("stallion-sized", 25, 40, Colour.GENERIC_SIZE_EIGHT);

	private int minimumValue, maximumValue;
	private String descriptor;
	private Colour colour;

	private PenisSize(String descriptor, int minimumValue, int maximumValue, Colour colour) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour=colour;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}

	public int getMedianValue() {
		return minimumValue + ((maximumValue - minimumValue) / 2);
	}

	public static PenisSize getPenisSizeFromInt(int inches) {
		for(PenisSize ps : PenisSize.values()) {
			if(inches>=ps.getMinimumValue() && inches<ps.getMaximumValue()) {
				return ps;
			}
		}
		return SEVEN_STALLION;
	}

	/**
	 * To fit into a sentence: "Your cock is "+getDescriptor()+"." "Your "
	 * getDescriptor()+" cock is dribbling precum."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public Colour getColour() {
		return colour;
	}
}
