package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.1
 * @version 0.2.1
 * @author Innoxia
 */
public enum FluidRegeneration {

	// I named these wrong...
	ZERO_MINIMUM(0, 0.0005f, "slow", "slowly", Colour.GENERIC_SIZE_ONE),
	ONE_AVERAGE(1, 0.0025f, "average", "", Colour.GENERIC_SIZE_TWO),
	TWO_FULL(2, 0.0075f, "fast", "quickly", Colour.GENERIC_SIZE_THREE),
	THREE_PLUMP(3, 0.015f, "rapid", "rapidly", Colour.GENERIC_SIZE_FOUR),
	FOUR_MAXIMUM(4, 0.05f, "very rapid", "very rapidly", Colour.GENERIC_SIZE_FIVE);
	
	
	private int value;
	private float percentageRegen;
	private String descriptor;
	private String verb;
	private Colour colour;

	private FluidRegeneration(int value, float percentageRegen, String descriptor, String verb, Colour colour) {
		this.value = value;
		this.percentageRegen = percentageRegen;
		this.descriptor = descriptor;
		this.verb = verb;
		this.colour = colour;
	}

	public int getValue() {
		return value;
	}

	public float getPercentageRegen() {
		return percentageRegen;
	}

	public String getName() {
		return descriptor;
	}

	public String getVerb() {
		return verb;
	}
	
	public static FluidRegeneration getFluidRegenerationFromInt(int inches) {
		for(FluidRegeneration ls : FluidRegeneration.values()) {
			if(inches == ls.getValue()) {
				return ls;
			}
		}
		return ZERO_MINIMUM;
	}

	public Colour getColour() {
		return colour;
	}
}
