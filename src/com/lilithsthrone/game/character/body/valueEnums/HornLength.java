package com.lilithsthrone.game.character.body.valueEnums;

/**
 * Sizes in inches.
 * 
 * @since 0.1.89
 * @version 0.1.89
 * @author Innoxia
 */
public enum HornLength {
	
	ZERO_TINY("tiny", 0, 2),

	ONE_SMALL("small", 2, 6),

	TWO_LONG("long", 6, 12),

	THREE_HUGE("huge", 12, 20),

	FOUR_MASSIVE("massive", 20, 30);

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

	public static HornLength getHornLengthFromInt(int inches) {
		for(HornLength ps : HornLength.values()) {
			if(inches>=ps.getMinimumValue() && inches<ps.getMaximumValue()) {
				return ps;
			}
		}
		return FOUR_MASSIVE;
	}
	
	public String getDescriptor() {
		return descriptor;
	}
}
