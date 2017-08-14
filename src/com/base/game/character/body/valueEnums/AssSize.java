package com.base.game.character.body.valueEnums;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.0
 * @version 0.1.82
 * @author Innoxia
 */
public enum AssSize {
	ZERO_FLAT("completely flat", 0),
	
	ONE_TINY("tiny", 1),
	
	TWO_SMALL("small", 2),
	
	THREE_NORMAL("normal-sized", 3),
	
	FOUR_LARGE("large", 4),
	
	FIVE_HUGE("huge", 5),
	
	SIX_MASSIVE("massive", 6),
	
	SEVEN_GIGANTIC("gigantic", 7);

	private String descriptor;
	private int assSize;

	private AssSize(String descriptor, int assSize) {
		this.descriptor = descriptor;
		this.assSize = assSize;
	}

	public static AssSize getAssSizeFromInt(int assSize) {
		for(AssSize as : AssSize.values()) {
			if(as.getValue() == assSize) {
				return as;
			}
		}
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
