package com.base.game.character.body.valueEnums;

/**
 * Anything over ZERO_AVERAGE is basically a pseudo penis
 * 
 * 
 * @since 0.1.0
 * @version 0.1.63
 * @author Innoxia
 */
public enum ClitorisSize {
	ZERO_AVERAGE("normal-sized",
			0,
			1),
	ONE_BIG("big",
			2,
			3),
	TWO_LARGE("large",
			4,
			8),
	THREE_HUGE("huge",
			9,
			14),
	FOUR_MASSIVE("massive",
			15,
			21),
	FIVE_ENORMOUS("enormous",
			22,
			29),
	SIX_GIGANTIC("gigantic",
			30,
			38),
	SEVEN_STALLION("stallion-sized",
			39,
			40);

	private int minimumValue, maximumValue;
	private String descriptor;

	private ClitorisSize(String descriptor, int minimumValue, int maximumValue) {
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
		return minimumValue + (maximumValue - minimumValue) / 2;
	}

	public static ClitorisSize getClitorisSizeFromInt(int inches) {
		if (inches <= ZERO_AVERAGE.maximumValue)
			return ZERO_AVERAGE;
		else if (inches <= ONE_BIG.maximumValue)
			return ONE_BIG;
		else if (inches <= TWO_LARGE.maximumValue)
			return TWO_LARGE;
		else if (inches <= THREE_HUGE.maximumValue)
			return THREE_HUGE;
		else if (inches <= FOUR_MASSIVE.maximumValue)
			return FOUR_MASSIVE;
		else if (inches <= FIVE_ENORMOUS.maximumValue)
			return FIVE_ENORMOUS;
		else if (inches <= SIX_GIGANTIC.maximumValue)
			return SIX_GIGANTIC;
		else
			return SEVEN_STALLION;
	}

	/**
	 * To fit into a sentence: "Your clitoris is "+getDescriptor()+"." "Your "
	 * getDescriptor()+" cock is dribbling pre-cum."
	 */
	public String getDescriptor() {
		return descriptor;
	}
}
