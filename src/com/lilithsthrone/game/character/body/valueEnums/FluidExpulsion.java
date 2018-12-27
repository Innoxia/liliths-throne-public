package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.7
 * @version 0.2.7
 * @author Innoxia
 */
public enum FluidExpulsion {
	
	ZERO_NONE("tiny", 0, 20, Colour.GENERIC_SIZE_ONE),

	ONE_SMALL("small", 20, 40, Colour.GENERIC_SIZE_TWO),

	TWO_MODERATE("moderate", 40, 60, Colour.GENERIC_SIZE_THREE),

	THREE_LARGE("large", 60, 80, Colour.GENERIC_SIZE_FOUR),

	FOUR_HUGE("huge", 80, 100, Colour.GENERIC_SIZE_FIVE);

	private int minimumValue, maximumValue;
	private String descriptor;
	private Colour colour;

	private FluidExpulsion(String descriptor, int minimumValue, int maximumValue, Colour colour) {
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

	public static FluidExpulsion getFluidExpulsionFromInt(int value) {
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
