package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Colour;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.64
 * @version 0.1.90
 * @author Innoxia
 */
public enum OrificeElasticity {

	/*-------RIGID:---------*/

	/**Is extremely resistant to being stretched out.*/
	ZERO_UNYIELDING("rigid", 0, 0.05f, Colour.GENERIC_SIZE_ONE),
	
	/**Is very resistant to being stretched out.*/
	ONE_RIGID("stiff", 1, 0.1f, Colour.GENERIC_SIZE_TWO),
	
	/**Is resistant to being stretched out.*/
	TWO_FIRM("firm", 2, 0.15f, Colour.GENERIC_SIZE_THREE),

	/*-------NORMAL:---------*/
	
	/**Normal value, along with FOUR_LIMBER. Is quite resistant to being stretched out.*/
	THREE_FLEXIBLE("flexible", 3, 0.2f, Colour.GENERIC_SIZE_FOUR),

	/**Normal value, along with THREE_FLEXIBLE. Is somewhat resistant to being stretched out.*/
	FOUR_LIMBER("limber", 4, 0.25f, Colour.GENERIC_SIZE_FIVE),

	/*-------STRETCHY:---------*/
	
	/**Stretches out fairly quickly.*/
	FIVE_STRETCHY("stretchy", 5, 0.3f, Colour.GENERIC_SIZE_SIX),

	/**Easily stretches out.*/
	SIX_SUPPLE("supple", 6, 0.4f, Colour.GENERIC_SIZE_SEVEN),
	
	/**Very quickly stretches out.*/
	SEVEN_ELASTIC("elastic", 7, 0.5f, Colour.GENERIC_SIZE_EIGHT);

	
	private String descriptor;
	private int value;
	private float stretchModifier;
	private Colour colour;

	private OrificeElasticity(String descriptor, int value, float stretchModifier, Colour colour) {
		this.descriptor = descriptor;
		this.value = value;
		this.stretchModifier = stretchModifier;
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

	public Colour getColour() {
		return colour;
	}

}
