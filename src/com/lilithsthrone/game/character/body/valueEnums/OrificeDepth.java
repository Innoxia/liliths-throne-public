package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public enum OrificeDepth {
	
	/** 50% of normal depth. */
	ZERO_EXTREMELY_SHALLOW("very shallow", 0, 0.5f, PresetColour.GENERIC_SIZE_ONE),

	/** 75% of normal depth. */
	ONE_SHALLOW("shallow", 1, 0.75f, PresetColour.GENERIC_SIZE_TWO),
	
	/*-------AVERAGE VALUE:---------*/
	/** 100% of normal depth. */
	TWO_AVERAGE("average-depth", 2, 1f, PresetColour.GENERIC_SIZE_THREE),

	/** 150% of normal depth. */
	THREE_SPACIOUS("spacious", 3, 1.5f, PresetColour.GENERIC_SIZE_FOUR),

	/** 200% of normal depth. */
	FOUR_DEEP("deep", 4, 2f, PresetColour.GENERIC_SIZE_FIVE),

	/** 250% of normal depth. */
	FIVE_VERY_DEEP("very deep", 5, 2.5f, PresetColour.GENERIC_SIZE_SIX),

	/** 300% of normal depth. */
	SIX_CAVERNOUS("cavernous", 6, 3f, PresetColour.GENERIC_SIZE_SEVEN),

	/** 400% of normal depth. */
	SEVEN_FATHOMLESS("fathomless", 7, 4f, PresetColour.GENERIC_SIZE_EIGHT);

	
	private String descriptor;
	private int value;
	private float depthPercentage;
	private Colour colour;

	/**
	 * @param descriptor A word to describe this depth.
	 * @param value A unique Integer identification number.
	 * @param depthPercentage What percentage of an orifice's default depth should be applied when it has this depth value.
	 * @param colour The colour of this elasticity value, mainly used for colouring text related to the elasticity of orifices.
	 */
	private OrificeDepth(String descriptor, int value, float depthPercentage, Colour colour) {
		this.descriptor = descriptor;
		this.value = value;
		this.depthPercentage = depthPercentage;
		this.colour = colour;
	}

	public static OrificeDepth getDepthFromInt(int value) {
		for(OrificeDepth oe : OrificeDepth.values()) {
			if(value == oe.getValue()) {
				return oe;
			}
		}
		return SEVEN_FATHOMLESS;
	}

	/**To fit into a sentence: "You have a "+getDescriptor()+" pussy." */
	public String getDescriptor() {
		return descriptor;
	}

	public int getValue() {
		return value;
	}

	public float getDepthPercentage() {
		return depthPercentage;
	}

	public Colour getColour() {
		return colour;
	}

}
