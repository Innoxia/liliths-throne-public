package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.7
 * @version 0.2.7
 * @author Innoxia
 */
public enum FluidExpulsion {
	
	ZERO_NONE("tiny", 0, 0.2f, Colour.GENERIC_SIZE_ONE),

	ONE_SMALL("small", 0.2f, 0.4f, Colour.GENERIC_SIZE_TWO),

	TWO_MODERATE("moderate", 0.4f, 0.6f, Colour.GENERIC_SIZE_THREE),

	THREE_LARGE("large", 0.6f, 0.8f, Colour.GENERIC_SIZE_FOUR),

	FOUR_HUGE("huge", 0.8f, 1f, Colour.GENERIC_SIZE_FIVE);

	private float minimumValue, maximumValue;
	private String descriptor;
	private Colour colour;

	private FluidExpulsion(String descriptor, float minimumValue, float maximumValue, Colour colour) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour=colour;
	}

	public float getMinimumValue() {
		return minimumValue;
	}

	public float getMaximumValue() {
		return maximumValue;
	}

	public float getMedianValue() {
		return minimumValue + ((maximumValue - minimumValue) / 2);
	}

	public static FluidExpulsion getFluidExpulsionFromFloat(float value) {
		for(FluidExpulsion expulsion : FluidExpulsion.values()) {
			if(value>=expulsion.getMinimumValue() && value<expulsion.getMaximumValue()) {
				return expulsion;
			}
		}
		return FOUR_HUGE;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public Colour getColour() {
		return colour;
	}
}
