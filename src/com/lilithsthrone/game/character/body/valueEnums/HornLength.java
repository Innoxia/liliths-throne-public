package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Util;

/**
 * Sizes in cm.
 * 
 * @since 0.1.89
 * @version 0.1.89
 * @author Innoxia
 */
public enum HornLength {
	
	ZERO_TINY("tiny", 0, 5, false),

	ONE_SMALL("small", 5, 15, true),

	TWO_LONG("long", 15, 30, true),

	THREE_HUGE("huge", 30, 50, true),

	FOUR_MASSIVE("massive", 50, 75, true);

	private int minimumValue, maximumValue;
	private String descriptor;
	private boolean suitableAsHandles;

	private HornLength(String descriptor, int minimumValue, int maximumValue, boolean suitableAsHandles) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.suitableAsHandles = suitableAsHandles;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}

	public int getMedianValue() {
		return (minimumValue + maximumValue) / 2;
	}

	public int getRandomValue() {
		return minimumValue + Util.random.nextInt(maximumValue - minimumValue);
	}

	public static HornLength getLengthFromInt(int cm) {
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

	public boolean isSuitableAsHandles() {
		return suitableAsHandles;
	}
}
