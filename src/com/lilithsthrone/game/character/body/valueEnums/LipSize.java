package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum LipSize {

	ZERO_THIN(0, "thin", Colour.GENERIC_SIZE_ONE),
	ONE_AVERAGE(1, "average-sized", Colour.GENERIC_SIZE_TWO),
	TWO_FULL(2, "full", Colour.GENERIC_SIZE_THREE),
	THREE_PLUMP(3, "plump", Colour.GENERIC_SIZE_FOUR),
	FOUR_HUGE(4, "huge", Colour.GENERIC_SIZE_FIVE);
	
	
	private int value;
	private String descriptor;
	private Colour colour;

	private LipSize(int value, String descriptor, Colour colour) {
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
	
	public static LipSize getLipSizeFromInt(int index) {
		for(LipSize ls : LipSize.values()) {
			if(index == ls.getValue()) {
				return ls;
			}
		}
		return ZERO_THIN;
	}
	
	public static int getLargest() {
		int largest = ZERO_THIN.value;
		for(LipSize ls : LipSize.values()) {
			largest = Math.max(largest, ls.value);
		}
		return largest;
	}

	public Colour getColour() {
		return colour;
	}
}
