package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Sizes in cm.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum PenisLength {
	/**Utility*/
	NEGATIVE_UTILITY_VALUE("N/A", -1, -1, PresetColour.GENERIC_SIZE_ONE),

	/**Barely even anything there. (0-1 inches)*/
	ZERO_MICROSCOPIC("tiny", 0, 5, PresetColour.GENERIC_SIZE_TWO),

	/**Average size for a harpy. (2-4 inches)*/
	ONE_TINY("small", 5, 12, PresetColour.GENERIC_SIZE_TWO),

	/**Average size for all regular morphs. (5-6 inches)*/
	TWO_AVERAGE("average-sized", 12, 17, PresetColour.GENERIC_SIZE_THREE),

	/**Average size for a wolf morph. (7-8 inches)*/
	THREE_LARGE("large", 17, 22, PresetColour.GENERIC_SIZE_FOUR),

	/** Straying into the bounds of "world record for a human". Average size for a horse morph. (9-10 inches)*/ 
	FOUR_HUGE("huge", 22, 28, PresetColour.GENERIC_SIZE_FIVE),

	/** Large for a horse morph. Past what a human could maintain erect (11-13 inches)*/
	FIVE_ENORMOUS("enormous", 28, 36, PresetColour.GENERIC_SIZE_SIX),

	/**This is just ridiculous... World record for a horse-morph. (14-23 inches)*/
	SIX_GIGANTIC("gigantic", 36, 60, PresetColour.GENERIC_SIZE_SEVEN),

	/**And this is for "extreme proportion" content. (24-40 inches)*/
	SEVEN_STALLION("stallion-sized", 60, 100, PresetColour.GENERIC_SIZE_EIGHT);

	private int minimumValue, maximumValue;
	private String descriptor;
	private Colour colour;

	private PenisLength(String descriptor, int minimumValue, int maximumValue, Colour colour) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour=colour;
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

	public static PenisLength getPenisLengthFromInt(int cm) {
		for(PenisLength ps : PenisLength.values()) {
			if(cm>=ps.getMinimumValue() && cm<ps.getMaximumValue()) {
				return ps;
			}
		}
		return SEVEN_STALLION;
	}

	/**
	 * To fit into a sentence: "Your cock is "+getDescriptor()+"." "Your "
	 * getDescriptor()+" cock is dribbling precum."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public Colour getColour() {
		return colour;
	}
	
}
