package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Colour;


public enum IngestionRate {

	ZERO_MINIMUM(0.2f, "minimal", "very slowly", Colour.GENERIC_SIZE_ONE),
	ONE_SLOW(0.5f, "slow", "slowly", Colour.GENERIC_SIZE_ONE),
	TWO_AVERAGE(2f, "average", "normally", Colour.GENERIC_SIZE_TWO),
	THREE_FAST(6f, "fast", "quickly", Colour.GENERIC_SIZE_THREE),
	FOUR_RAPID(18f, "rapid", "rapidly", Colour.GENERIC_SIZE_FOUR),
	FIVE_MAXIMUM(48f, "very rapid", "very rapidly", Colour.GENERIC_SIZE_FIVE);
	

	private float rate;
	private String descriptor;
	private String verb;
	private Colour colour;

	private IngestionRate(float ingestionRate, String descriptor, String verb, Colour colour) {
		this.rate = ingestionRate;
		this.descriptor = descriptor;
		this.verb = verb;
		this.colour = colour;
	}

	public int getValue() {
		return ordinal();
	}

	public float getRate() {
		return rate;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public String getVerb() {
		return verb;
	}
	
	public static IngestionRate getIngestionRateFromInt(int i) {
		if (i < IngestionRate.values().length) {
			return IngestionRate.values()[i];
		}
		return ZERO_MINIMUM;
	}

	public Colour getColour() {
		return colour;
	}
}
