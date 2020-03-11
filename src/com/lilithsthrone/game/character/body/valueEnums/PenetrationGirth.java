package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.1
 * @version 0.3.6.6
 * @author Innoxia
 */
public enum PenetrationGirth {

	ZERO_THIN(0, -0.3f, "thin", Colour.GENERIC_SIZE_ONE),
	
	ONE_SLENDER(1, -0.15f, "slender", Colour.GENERIC_SIZE_TWO),
	
	TWO_AVERAGE(2, 0, "averagely-girthed", Colour.GENERIC_SIZE_THREE),
	
	THREE_THICK(3, 0.15f, "thick", Colour.GENERIC_SIZE_FOUR),
	
	FOUR_FAT(4, 0.3f, "fat", Colour.GENERIC_SIZE_FIVE);
	
	
	private int value;
	private float diameterPercentageModifier;
	private String descriptor;
	private Colour colour;

	private PenetrationGirth(int value, float diameterPercentageModifier, String descriptor, Colour colour) {
		this.value = value;
		this.diameterPercentageModifier = diameterPercentageModifier;
		this.descriptor = descriptor;
		this.colour = colour;
	}

	public int getValue() {
		return value;
	}

	/**
	 * @return The percentage (as a float from 0->1) by which this girth increases the diameter of a penetration type.
	 */
	public float getDiameterPercentageModifier() {
		return diameterPercentageModifier;
	}
	
	public String getName() {
		return descriptor;
	}
	
	public static PenetrationGirth getGirthFromInt(int size) {
		for(PenetrationGirth ls : PenetrationGirth.values()) {
			if(size == ls.getValue()) {
				return ls;
			}
		}
		return ZERO_THIN;
	}
	
	public static int getLargest() {
		int largest = ZERO_THIN.value;
		for(PenetrationGirth ls : PenetrationGirth.values()) {
			largest = Math.max(largest, ls.value);
		}
		return largest;
	}

	public Colour getColour() {
		return colour;
	}
}
