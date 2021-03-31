package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.64
 * @version 0.3.21
 * @author Innoxia
 */
public enum OrificeElasticity {

	/*-------RIGID:---------*/

	/**Is extremely resistant to being stretched out.*/
	ZERO_UNYIELDING("rigid", 0, 0.025f, 0, false, PresetColour.GENERIC_SIZE_ONE),
	
	/**Is very resistant to being stretched out.*/
	ONE_RIGID("stiff", 1, 0.05f, 0.05f, false, PresetColour.GENERIC_SIZE_TWO),
	
	/**Is resistant to being stretched out.*/
	TWO_FIRM("firm", 2, 0.1f, 0.1f, false, PresetColour.GENERIC_SIZE_THREE),

	/*-------NORMAL:---------*/
	
	/**Normal value, along with FOUR_LIMBER. Is quite resistant to being stretched out.*/
	THREE_FLEXIBLE("flexible", 3, 0.15f, 0.15f, false, PresetColour.GENERIC_SIZE_FOUR),

	/**Normal value, along with THREE_FLEXIBLE. Is somewhat resistant to being stretched out.*/
	FOUR_LIMBER("limber", 4, 0.2f, 0.2f, true, PresetColour.GENERIC_SIZE_FIVE),

	/*-------STRETCHY:---------*/
	
	/**Stretches out fairly quickly.*/
	FIVE_STRETCHY("stretchy", 5, 0.25f, 0.25f, true, PresetColour.GENERIC_SIZE_SIX),

	/**Easily stretches out.*/
	SIX_SUPPLE("supple", 6, 0.3f, 0.25f, true, PresetColour.GENERIC_SIZE_SEVEN),
	
	/**Very quickly stretches out.*/
	SEVEN_ELASTIC("elastic", 7, 0.5f, 0.25f, true, PresetColour.GENERIC_SIZE_EIGHT);

	
	private String descriptor;
	private int value;
	private float stretchModifier;
	private float sizeTolerancePercentage;
	private boolean extendingUncomfortableDepthStart;
	private Colour colour;

	/**
	 * @param descriptor A word to describe this elasticity.
	 * @param value A unique Integer identification number.
	 * @param stretchModifier A percentage correlating to how much an orifice is stretched out by per turn of being fucked by a too-large penetration.
	 * @param sizeTolerancePercentage A percentage corresponding to the default tolerance an orifice has for penetrations being too big.
	 * @param colour The colour of this elasticity value, mainly used for colouring text related to the elasticity of orifices.
	 */
	private OrificeElasticity(String descriptor, int value, float stretchModifier, float sizeTolerancePercentage, boolean extendingUncomfortableDepthStart, Colour colour) {
		this.descriptor = descriptor;
		this.value = value;
		this.stretchModifier = stretchModifier;
		this.sizeTolerancePercentage = sizeTolerancePercentage;
		this.extendingUncomfortableDepthStart = extendingUncomfortableDepthStart;
		this.colour = colour;
	}

	public static OrificeElasticity getElasticityFromInt(int value) {
		for(OrificeElasticity oe : OrificeElasticity.values()) {
			if(value == oe.getValue()) {
				return oe;
			}
		}
		return SEVEN_ELASTIC;
	}

	/**
	 * To fit into a sentence: "You have a "+getDescriptor()+" ass."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public int getValue() {
		return value;
	}

	public float getStretchModifier() {
		return stretchModifier;
	}

	public float getSizeTolerancePercentage() {
		return sizeTolerancePercentage;
	}

	public boolean isExtendingUncomfortableDepthStart() {
		return extendingUncomfortableDepthStart;
	}

	public Colour getColour() {
		return colour;
	}

}
