package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Colour;

/**
 * Anything over ZERO_AVERAGE is basically a pseudo penis
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum ClitorisSize {
	
	ZERO_AVERAGE("normal-sized", 0, 1, Colour.GENERIC_SIZE_ONE),
	
	ONE_BIG("big", 1, 8, Colour.GENERIC_SIZE_TWO),
	
	TWO_LARGE("large", 8, 12, Colour.GENERIC_SIZE_THREE),
	
	THREE_HUGE("huge", 12, 28, Colour.GENERIC_SIZE_FOUR),
	
	FOUR_MASSIVE("massive", 28, 55, Colour.GENERIC_SIZE_FIVE),
	
	FIVE_ENORMOUS("enormous", 55, 75, Colour.GENERIC_SIZE_SIX),
	
	SIX_GIGANTIC("gigantic", 75, 100, Colour.GENERIC_SIZE_SEVEN),
	
	SEVEN_STALLION("stallion-sized", 100, 125, Colour.GENERIC_SIZE_EIGHT);

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

	public static ClitorisSize getClitorisSizeFromInt(int cm) {
		for(ClitorisSize cs : ClitorisSize.values()) {
			if(cm>=cs.getMinimumValue() && cm<cs.getMaximumValue()) {
				return cs;
			}
		}
		return SEVEN_STALLION;
	}

	/**
	 * To fit into a sentence: "Your clitoris is "+getDescriptor()+"." "Your "
	 * getDescriptor()+" cock is dribbling precum."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public Colour getColour() {
		return colour;
	}
}
