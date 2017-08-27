package com.base.game.character.body.valueEnums;

import com.base.utils.Colour;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum TesticleSize {

	/**Little bumps under the skin.*/
	ZERO_VESTIGIAL("vestigial", 0, Colour.GENERIC_SIZE_ONE),

	/**Small for a human.*/
	ONE_TINY("tiny", 1, Colour.GENERIC_SIZE_TWO),
	
	/**Average for a human.*/
	TWO_AVERAGE("average-sized", 2, Colour.GENERIC_SIZE_THREE),

	/**Large for a human. Average for a dog or wolf-morph.*/
	THREE_LARGE("large", 3, Colour.GENERIC_SIZE_FOUR),

	/**Unrealistically large for a human. Average for a horse-morph.*/
	FOUR_HUGE("huge", 4, Colour.GENERIC_SIZE_FIVE),

	/**Large for a horse-morph.*/
	FIVE_MASSIVE("massive", 5, Colour.GENERIC_SIZE_SIX),

	/**Getting pretty absurd.*/
	SIX_GIGANTIC("gigantic", 6, Colour.GENERIC_SIZE_SEVEN),

	/**"Extreme proportion" content.*/
	SEVEN_ABSURD("absurdly enormous", 7, Colour.GENERIC_SIZE_EIGHT);

	private int value;
	private String descriptor;
	private Colour colour;

	private TesticleSize(String descriptor, int value, Colour colour) {
		this.descriptor = descriptor;
		this.value = value;
		this.colour=colour;
	}

	public int getValue() {
		return value;
	}

	public static TesticleSize getTesticleSizeFromInt(int inches) {
		for(TesticleSize ts : TesticleSize.values()) {
			if(inches == ts.getValue()) {
				return ts;
			}
		}
		return SEVEN_ABSURD;
	}

	/**
	 * To fit into a sentence: "Your balls are "+getDescriptor()+"."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public Colour getColour() {
		return colour;
	}
}
