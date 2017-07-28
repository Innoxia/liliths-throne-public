package com.base.game.character.body.valueEnums;

/**
 * Sizes in inches.
 * 
 * @since 0.1.0
 * @version 0.1.67
 * @author Innoxia
 */
public enum PenisSize {
	/**Utility*/
	NEGATIVE_UTILITY_VALUE("N/A", -1, -1),

	/**Barely even anything there. (0-1 inches)*/
	ZERO_MICROSCOPIC("microscopic", 0, 1),

	/**Average size for a harpy. (2-3 inches)*/
	ONE_TINY("tiny", 2, 3),

	/**Average size for a cat-morph or human. (4-7 inches)*/
	TWO_AVERAGE("average-sized", 4, 7),

	/**Average size for a dog or wolf morph. (8-11 inches)*/
	THREE_LARGE("large", 8, 11),

	/**Average size for a horse morph. (12-15 inches)*/
	FOUR_HUGE("huge", 12, 15),

	/**Straying into the bounds of "world record for a human". Large for a horse morph. (16-19 inches)*/
	FIVE_ENORMOUS("enormous", 16, 19),

	/**This is just ridiculous... World record for a horse-morph. (20-24 inches)*/
	SIX_GIGANTIC("gigantic", 20, 24),

	/**And this is for "extreme proportion" content. (25-40 inches)*/
	SEVEN_STALLION("stallion-sized", 25, 40);

	private int minimumValue, maximumValue;
	private String descriptor;

	private PenisSize(String descriptor, int minimumValue, int maximumValue) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
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
		if (inches <= ZERO_MICROSCOPIC.maximumValue)
			return ZERO_MICROSCOPIC;
		else if (inches <= ONE_TINY.maximumValue)
			return ONE_TINY;
		else if (inches <= TWO_AVERAGE.maximumValue)
			return TWO_AVERAGE;
		else if (inches <= THREE_LARGE.maximumValue)
			return THREE_LARGE;
		else if (inches <= FOUR_HUGE.maximumValue)
			return FOUR_HUGE;
		else if (inches <= FIVE_ENORMOUS.maximumValue)
			return FIVE_ENORMOUS;
		else if (inches <= SIX_GIGANTIC.maximumValue)
			return SIX_GIGANTIC;
		else
			return SEVEN_STALLION;
	}

	/**
	 * To fit into a sentence: "Your cock is "+getDescriptor()+"." "Your "
	 * getDescriptor()+" cock is dribbling pre-cum."
	 */
	public String getDescriptor() {
		return descriptor;
	}
}
