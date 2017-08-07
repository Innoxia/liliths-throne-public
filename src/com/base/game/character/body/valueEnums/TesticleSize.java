package com.base.game.character.body.valueEnums;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum TesticleSize {

	/**Little bumps under the skin.*/
	ZERO_VESTIGIAL("vestigial", 0),

	/**Small for a human.*/
	ONE_TINY("tiny", 1),
	
	/**Average for a human.*/
	TWO_AVERAGE("average-sized", 2),

	/**Large for a human. Average for a dog or wolf-morph.*/
	THREE_LARGE("large", 3),

	/**Unrealistically large for a human. Average for a horse-morph.*/
	FOUR_HUGE("huge", 4),

	/**Large for a horse-morph.*/
	FIVE_MASSIVE("massive", 5),

	/**Getting pretty absurd.*/
	SIX_GIGANTIC("gigantic", 6),

	/**"Extreme proportion" content.*/
	SEVEN_ABSURD("absurdly enormous", 7);

	private int value;
	private String descriptor;

	private TesticleSize(String descriptor, int value) {
		this.descriptor = descriptor;
		this.value = value;
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
}
