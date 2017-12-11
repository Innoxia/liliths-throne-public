package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Colour;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.0
 * @version 0.1.90
 * @author Innoxia
 */
public enum Wetness {

	ZERO_DRY("dry", 0, 100, Colour.GENERIC_SIZE_ONE),
	
	ONE_SLIGHTLY_MOIST("slightly moist", 1, 50, Colour.GENERIC_SIZE_TWO),
	
	TWO_MOIST("moist", 2, 25, Colour.GENERIC_SIZE_THREE),
	
	THREE_WET("wet", 3, 10, Colour.GENERIC_SIZE_FOUR),
	
	FOUR_SLIMY("slimy", 4, 5, Colour.GENERIC_SIZE_FIVE),
	
	FIVE_SLOPPY("sloppy", 5, 0, Colour.GENERIC_SIZE_SIX),
	
	SIX_SOPPING_WET("sopping wet", 6, 0, Colour.GENERIC_SIZE_SEVEN),
	
	SEVEN_DROOLING("drooling", 7, 0, Colour.GENERIC_SIZE_EIGHT);
	
	
	private int wetness, arousalNeededToGetVaginaWet;
	private String descriptor;
	private Colour colour;

	private Wetness(String descriptor, int wetness, int arousalNeededToGetVaginaWet, Colour colour) {
		this.descriptor = descriptor;
		this.wetness = wetness;
		this.arousalNeededToGetVaginaWet = arousalNeededToGetVaginaWet;
		this.colour = colour;
	}

	public static Wetness valueOf(int wetness) {
		for(Wetness w : Wetness.values()) {
			if(wetness == w.getValue()) {
				return w;
			}
		}
		return SEVEN_DROOLING;
	}

	/**
	 * To fit into a sentence: "Your vagina is "+getDescriptor()+"." "Your "
	 * getDescriptor()+" asshole is stretched wide open."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public int getValue() {
		return wetness;
	}

	public int getArousalNeededToGetVaginaWet() {
		return arousalNeededToGetVaginaWet;
	}

	public Colour getColour() {
		return colour;
	}
}
