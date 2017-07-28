package com.base.game.character.body.valueEnums;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.64
 * @version 0.1.64
 * @author Innoxia
 */
public enum OrificeElasticity {

	/*-------RIGID:---------*/

	/**Is extremely resistant to being stretched out, and will only recover about 25% of its original size after sex.*/
	ZERO_UNYIELDING("rigid", 0, 0.05f, 0.75f),
	
	/**Is very resistant to being stretched out, and will only recover about 50% of its original size after sex.*/
	ONE_RIGID("stiff", 1, 0.1f, 0.5f),
	
	/**Is resistant to being stretched out, and will only recover about 75% of its original size after sex.*/
	TWO_FIRM("firm", 2, 0.15f, 0.25f),

	/*-------NORMAL:---------*/
	
	/**Normal value, along with FOUR_LIMBER. Is quite resistant to being stretched out, but will very slowly return to starting value after sex.*/
	THREE_FLEXIBLE("flexible", 3, 0.2f, 0),

	/**Normal value, along with THREE_FLEXIBLE. Is somewhat resistant to being stretched out, but will slowly return to starting value after sex.*/
	FOUR_LIMBER("limber", 4, 0.25f, 0),

	/*-------STRECHY:---------*/
	
	/**Stretches out fairly quickly, returns to starting value after sex.*/
	FIVE_STRETCHY("stretchy", 5, 0.3f, 0),

	/**Easily stretches out, quickly returns to starting value after sex.*/
	SIX_SUPPLE("supple", 6, 0.4f, 0),
	
	/**Very quickly stretches out, instantly returns to starting value after sex.*/
	SEVEN_ELASTIC("elastic", 7, 0.5f, 0);

	
	private String descriptor;
	private int value;
	private float stretchModifier, capacityIncreaseModifier; 

	private OrificeElasticity(String descriptor, int value, float stretchModifier, float capacityIncreaseModifier) {
		this.descriptor = descriptor;
		this.value = value;
		this.stretchModifier = stretchModifier;
		this.capacityIncreaseModifier = capacityIncreaseModifier;
	}

	public static OrificeElasticity getElasticityFromInt(int value) {
		if (value <= ZERO_UNYIELDING.value)
			return ZERO_UNYIELDING;
		else if (value <= ONE_RIGID.value)
			return ONE_RIGID;
		else if (value <= TWO_FIRM.value)
			return TWO_FIRM;
		else if (value <= THREE_FLEXIBLE.value)
			return THREE_FLEXIBLE;
		else if (value <= FOUR_LIMBER.value)
			return FOUR_LIMBER;
		else if (value <= FIVE_STRETCHY.value)
			return FIVE_STRETCHY;
		else if (value <= SIX_SUPPLE.value)
			return SIX_SUPPLE;
		else
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
	
	public float getCapacityIncreaseModifier() {
		return capacityIncreaseModifier;
	}

}
