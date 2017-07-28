package com.base.game.character.body.valueEnums;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.0
 * @version 0.1.0
 * @author Innoxia
 */
public enum AssSize {
	ZERO_FLAT("completely flat", 0), ONE_TINY("tiny", 1), TWO_SMALL("small", 2), THREE_NORMAL("normal-sized", 3), FOUR_LARGE("large", 4), FIVE_HUGE("huge", 5), SIX_MASSIVE("massive", 6), SEVEN_GIGANTIC("gigantic", 7);

	private String descriptor;
	private int assSize;

	private AssSize(String descriptor, int assSize) {
		this.descriptor = descriptor;
		this.assSize = assSize;
	}

	public static AssSize getAssSizeFromInt(int assSize) {
		if (assSize <= ZERO_FLAT.assSize)
			return ZERO_FLAT;
		else if (assSize <= ONE_TINY.assSize)
			return ONE_TINY;
		else if (assSize <= TWO_SMALL.assSize)
			return TWO_SMALL;
		else if (assSize <= THREE_NORMAL.assSize)
			return THREE_NORMAL;
		else if (assSize <= FOUR_LARGE.assSize)
			return FOUR_LARGE;
		else if (assSize <= FIVE_HUGE.assSize)
			return FIVE_HUGE;
		else if (assSize <= SIX_MASSIVE.assSize)
			return SIX_MASSIVE;
		else
			return SEVEN_GIGANTIC;
	}

	/**
	 * To fit into a sentence: "You have a "+getDescriptor()+" ass."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public int getValue() {
		return assSize;
	}
}
