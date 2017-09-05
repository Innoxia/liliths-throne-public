package com.base.game.character.body.valueEnums;

import com.base.utils.Colour;

/**
 * Anything over ZERO_AVERAGE is basically a pseudo penis
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum ClitorisSize {
	
	ZERO_AVERAGE("normal-sized", 0, 1, Colour.GENERIC_SIZE_ONE),
	
	ONE_BIG("big", 1, 3, Colour.GENERIC_SIZE_TWO),
	
	TWO_LARGE("large", 3, 5, Colour.GENERIC_SIZE_THREE),
	
	THREE_HUGE("huge", 5, 11, Colour.GENERIC_SIZE_FOUR),
	
	FOUR_MASSIVE("massive", 11, 22, Colour.GENERIC_SIZE_FIVE),
	
	FIVE_ENORMOUS("enormous", 22, 30, Colour.GENERIC_SIZE_SIX),
	
	SIX_GIGANTIC("gigantic", 30, 38, Colour.GENERIC_SIZE_SEVEN),
	
	SEVEN_STALLION("stallion-sized", 38, 50, Colour.GENERIC_SIZE_EIGHT);

	private int minimumValue, maximumValue;
	private String descriptor;
	private Colour colour;

	private ClitorisSize(String descriptor, int minimumValue, int maximumValue, Colour colour) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
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

	public Colour getColour() {
		return colour;
	}
}
