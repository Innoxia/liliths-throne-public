package com.base.game.character.body.valueEnums;

/**
 * Measured in inches.
 * 
 * @since 0.1.0
 * @version 0.1.0
 * @author Innoxia
 */
public enum HairLength {
	/** Bald */
	ZERO_BALD("bald", 0, 0),
	/** Very short */
	ONE_VERY_SHORT("very short", 1, 2),
	/** Short */
	TWO_SHORT("short", 3, 5),
	/** above the shoulders */
	THREE_SHOULDER_LENGTH("shoulder-length", 6, 12),
	/** Reaching down to mid-back */
	FOUR_MID_BACK("long", 13, 18),
	/** Reaching down to just above the ass */
	FIVE_ABOVE_ASS("very long", 19, 24),
	/** Reaching down to below the ass */
	SIX_BELOW_ASS("incredibly long", 25, 30),
	/** Hair so long that it reaches the floor */
	SEVEN_TO_FLOOR("extremely long", 31, 40);

	private int minimumValue, maximumValue;
	private String descriptor;

	private HairLength(String descriptor, int minimumValue, int maximumValue) {
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
	
	public static HairLength getShorter(int currentHairLength){
		if (currentHairLength <= ZERO_BALD.maximumValue)
			return ZERO_BALD;
		else if (currentHairLength <= ONE_VERY_SHORT.maximumValue)
			return ZERO_BALD;
		else if (currentHairLength <= TWO_SHORT.maximumValue)
			return ONE_VERY_SHORT;
		else if (currentHairLength <= THREE_SHOULDER_LENGTH.maximumValue)
			return TWO_SHORT;
		else if (currentHairLength <= FOUR_MID_BACK.maximumValue)
			return THREE_SHOULDER_LENGTH;
		else if (currentHairLength <= FIVE_ABOVE_ASS.maximumValue)
			return FOUR_MID_BACK;
		else if (currentHairLength <= SIX_BELOW_ASS.maximumValue)
			return FIVE_ABOVE_ASS;
		else
			return SIX_BELOW_ASS;
	}
	
	public static HairLength getLonger(int currentHairLength){
		if (currentHairLength <= ZERO_BALD.maximumValue)
			return ONE_VERY_SHORT;
		else if (currentHairLength <= ONE_VERY_SHORT.maximumValue)
			return TWO_SHORT;
		else if (currentHairLength <= TWO_SHORT.maximumValue)
			return THREE_SHOULDER_LENGTH;
		else if (currentHairLength <= THREE_SHOULDER_LENGTH.maximumValue)
			return FOUR_MID_BACK;
		else if (currentHairLength <= FOUR_MID_BACK.maximumValue)
			return FIVE_ABOVE_ASS;
		else if (currentHairLength <= FIVE_ABOVE_ASS.maximumValue)
			return SIX_BELOW_ASS;
		else if (currentHairLength <= SIX_BELOW_ASS.maximumValue)
			return SEVEN_TO_FLOOR;
		else
			return SEVEN_TO_FLOOR;
	}

	public static HairLength getHairLengthFromInt(int hairLength) {
		if (hairLength <= ZERO_BALD.maximumValue)
			return ZERO_BALD;
		else if (hairLength <= ONE_VERY_SHORT.maximumValue)
			return ONE_VERY_SHORT;
		else if (hairLength <= TWO_SHORT.maximumValue)
			return TWO_SHORT;
		else if (hairLength <= THREE_SHOULDER_LENGTH.maximumValue)
			return THREE_SHOULDER_LENGTH;
		else if (hairLength <= FOUR_MID_BACK.maximumValue)
			return FOUR_MID_BACK;
		else if (hairLength <= FIVE_ABOVE_ASS.maximumValue)
			return FIVE_ABOVE_ASS;
		else if (hairLength <= SIX_BELOW_ASS.maximumValue)
			return SIX_BELOW_ASS;
		else
			return SEVEN_TO_FLOOR;
	}

	/**
	 * An indication of hair length.
	 */
	public String getDescriptor() {
		return descriptor;
	}
}
