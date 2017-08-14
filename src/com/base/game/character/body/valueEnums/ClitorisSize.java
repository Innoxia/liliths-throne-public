package com.base.game.character.body.valueEnums;

/**
 * Anything over ZERO_AVERAGE is basically a pseudo penis
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum ClitorisSize {
	ZERO_AVERAGE("normal-sized",
			0,
			1),
	ONE_BIG("big",
			1,
			3),
	TWO_LARGE("large",
			3,
			5),
	THREE_HUGE("huge",
			5,
			11),
	FOUR_MASSIVE("massive",
			11,
			22),
	FIVE_ENORMOUS("enormous",
			22,
			30),
	SIX_GIGANTIC("gigantic",
			30,
			38),
	SEVEN_STALLION("stallion-sized",
			38,
			50);

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
		for(ClitorisSize cs : ClitorisSize.values()) {
			if(inches>=cs.getMinimumValue() && inches<cs.getMaximumValue()) {
				return cs;
			}
		}
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
