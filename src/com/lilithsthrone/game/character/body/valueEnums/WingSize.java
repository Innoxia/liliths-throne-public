package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.95
 * @version 0.3.1
 * @author Innoxia
 */
public enum WingSize {

	ZERO_TINY(0, "tiny", Colour.GENERIC_SIZE_ONE),
	ONE_SMALL(1, "small", Colour.GENERIC_SIZE_TWO),
	TWO_AVERAGE(2, "average-sized", Colour.GENERIC_SIZE_THREE),
	THREE_LARGE(3, "large", Colour.GENERIC_SIZE_FOUR),
	FOUR_HUGE(4, "huge", Colour.GENERIC_SIZE_FIVE);
	
	
	private int value;
	private String descriptor;
	private Colour colour;

	private WingSize(int value, String descriptor, Colour colour) {
		this.value = value;
		this.descriptor = descriptor;
		this.colour = colour;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return descriptor;
	}
	
	public static WingSize getWingSizeFromInt(int size) {
		for(WingSize ls : WingSize.values()) {
			if(size == ls.getValue()) {
				return ls;
			}
		}
		return ZERO_TINY;
	}
	
	public static int getLargest() {
		int largest = ZERO_TINY.value;
		for(WingSize ls : WingSize.values()) {
			largest = Math.max(largest, ls.value);
		}
		return largest;
	}

	public Colour getColour() {
		return colour;
	}
}
