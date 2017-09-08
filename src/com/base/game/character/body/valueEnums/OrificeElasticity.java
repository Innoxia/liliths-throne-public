package com.base.game.character.body.valueEnums;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.64
 * @version 0.1.83
 * @author Innoxia
 */
public enum OrificeElasticity {

	/*-------RIGID:---------*/

	/**Is extremely resistant to being stretched out.*/
	ZERO_UNYIELDING("rigid", 0, 0.05f),
	
	/**Is very resistant to being stretched out.*/
	ONE_RIGID("stiff", 1, 0.1f),
	
	/**Is resistant to being stretched out.*/
	TWO_FIRM("firm", 2, 0.15f),

	/*-------NORMAL:---------*/
	
	/**Normal value, along with FOUR_LIMBER. Is quite resistant to being stretched out.*/
	THREE_FLEXIBLE("flexible", 3, 0.2f),

	/**Normal value, along with THREE_FLEXIBLE. Is somewhat resistant to being stretched out.*/
	FOUR_LIMBER("limber", 4, 0.25f),

	/*-------STRETCHY:---------*/
	
	/**Stretches out fairly quickly.*/
	FIVE_STRETCHY("stretchy", 5, 0.3f),

	/**Easily stretches out.*/
	SIX_SUPPLE("supple", 6, 0.4f),
	
	/**Very quickly stretches out.*/
	SEVEN_ELASTIC("elastic", 7, 0.5f);

	
	private String descriptor;
	private int value;
	private float stretchModifier; 

	private OrificeElasticity(String descriptor, int value, float stretchModifier) {
		this.descriptor = descriptor;
		this.value = value;
		this.stretchModifier = stretchModifier;
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

}
