package com.lilithsthrone.game.character.body.valueEnums;

/**
 * Sizes in cm.
 * 
 * @since 0.1.89
 * @version 0.1.89
 * @author Innoxia
 */
public enum HornLength {
	
	ZERO_TINY("tiny", 0, 5),

	ONE_SMALL("small", 5, 15),

	TWO_LONG("long", 15, 30),

	THREE_HUGE("huge", 30, 50),

	FOUR_MASSIVE("massive", 50, 75);

	private int minimumValue, maximumValue;
	private String descriptor;

	private HornLength(String descriptor, int minimumValue, int maximumValue) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
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

	public static HornLength getHornLengthFromInt(int cm) {
		for(HornLength ps : HornLength.values()) {
			if(cm>=ps.getMinimumValue() && cm<ps.getMaximumValue()) {
				return ps;
			}
		}
		return FOUR_MASSIVE;
	}
	
	public String getDescriptor() {
		return descriptor;
	}
}
