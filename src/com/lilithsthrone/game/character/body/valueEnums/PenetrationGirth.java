package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.1
 * @version 0.3.7.5
 * @author Innoxia
 */
public enum PenetrationGirth {

	ZERO_THIN(0, -0.6f, "thin", PresetColour.GENERIC_SIZE_ONE),
	
	ONE_SLENDER(1, -0.3f, "slender", PresetColour.GENERIC_SIZE_TWO),
	
	TWO_NARROW(2, -0.15f, "narrow", PresetColour.GENERIC_SIZE_THREE),
	
	THREE_AVERAGE(3, 0, "averagely-girthed", PresetColour.GENERIC_SIZE_FOUR),
	
	FOUR_THICK(4, 0.15f, "thick", PresetColour.GENERIC_SIZE_FIVE),
	
	FIVE_FAT(5, 0.3f, "fat", PresetColour.GENERIC_SIZE_SIX),
	
	SIX_GIRTHY(6, 0.6f, "girthy", PresetColour.GENERIC_SIZE_SEVEN);
	
	
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
	
	public static int getMaximum() {
		return SIX_GIRTHY.getValue();
	}
	
	public Colour getColour() {
		return colour;
	}
}
