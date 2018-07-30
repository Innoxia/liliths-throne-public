package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.86
 * @version 0.2.9
 * @author Innoxia
 */
public enum Height {
	
	// Need to standardise to 1.5 each category
	
	/**2' to 3'6"*/
	NEGATIVE_TWO_MIMIMUM("tiny", 24, 42, Colour.GENERIC_SIZE_ONE),
	
	/**3'6" to 4'*/
	NEGATIVE_ONE_TINY("tiny", 42, 48, Colour.GENERIC_SIZE_ONE),
	
	/**4' to 5'*/
	ZERO_TINY("small", 48, 60, Colour.GENERIC_SIZE_ONE),
	
	/**5' to 5'6"*/
	ONE_SHORT("short", 60, 66, Colour.GENERIC_SIZE_TWO),

	/**5'6" to 6'*/
	TWO_AVERAGE("average height", 66, 72, Colour.GENERIC_SIZE_THREE),

	/**6' to 6'6"*/
	THREE_TALL("tall", 72, 78, Colour.GENERIC_SIZE_FOUR),

	/**6'6" to 7'*/
	FOUR_VERY_TALL("very tall", 78, 84, Colour.GENERIC_SIZE_FIVE),

	/**7' to 7'6"*/
	FIVE_ENORMOUS("towering", 84, 90, Colour.GENERIC_SIZE_SIX),

	/**7'6" to 9'"*/
	SIX_GIANT("gigantic", 90, 108, Colour.GENERIC_SIZE_SEVEN),

	/**9' to 12'*/
	SEVEN_COLOSSAL("colossal", 108, 144, Colour.GENERIC_SIZE_EIGHT);
	
	private float minimumValue, maximumValue;
	private String descriptor;
	private Colour colour;

	private Height(String descriptor, float minimumValue, float maximumValue, Colour colour) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
	}

	public float getMinimumValue() {
		return minimumValue;
	}

	public float getMaximumValue() {
		return maximumValue;
	}

	public float getMedianValue() {
		return minimumValue + (maximumValue - minimumValue) / 2;
	}
	
	/**
	 * If height is less than this value, then that height is short stature.
	 */
	public static int getShortStatureCutOff() {
		return ZERO_TINY.getMinimumValue();
	}

	public static Height getHeightFromFloat(float inches) {
		for(Height cs : Height.values()) {
			if(inches >= cs.getMinimumValue() && inches < cs.getMaximumValue()) {
				return cs;
			}
		}
		return SEVEN_COLOSSAL;
	}
	
	public String getDescriptor() {
		return descriptor;
	}

	public Colour getColour() {
		return colour;
	}
}
