package com.lilithsthrone.game.character.body.valueEnums;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum Wetness {

	ZERO_DRY("dry", 0, 100),
	
	ONE_SLIGHTLY_MOIST("slightly moist", 1, 50),
	
	TWO_MOIST("moist", 2, 25),
	
	THREE_WET("wet", 3, 10),
	
	FOUR_SLIMY("slimy", 4, 5),
	
	FIVE_SLOPPY("sloppy", 5, 0),
	
	SIX_SOPPING_WET("sopping wet", 6, 0),
	
	SEVEN_DROOLING("drooling", 7, 0);
	
	
	private int wetness, arousalNeededToGetVaginaWet;
	private String descriptor;

	private Wetness(String descriptor, int wetness, int arousalNeededToGetVaginaWet) {
		this.descriptor = descriptor;
		this.wetness = wetness;
		this.arousalNeededToGetVaginaWet = arousalNeededToGetVaginaWet;
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
}
