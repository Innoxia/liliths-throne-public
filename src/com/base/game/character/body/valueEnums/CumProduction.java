package com.base.game.character.body.valueEnums;

import com.base.utils.Colour;

/**
 * Measured in millilitres.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum CumProduction {

	/** Self-explanatory.... */
	ZERO_NONE("no", 0, 1, 100, 0, Wetness.ZERO_DRY, Colour.GENERIC_SIZE_ONE),
	
	/** There's about one or two drops of cum here... */
	ONE_TRICKLE("a tiny trickle of", 1, 3, 75, 0.5f, Wetness.ONE_SLIGHTLY_MOIST, Colour.GENERIC_SIZE_TWO),
	
	/** This is significantly less than an average amount of cum that a human male produces when orgasming. */
	TWO_SMALL_AMOUNT("a small amount of", 3, 6, 50, 0.75f, Wetness.TWO_MOIST, Colour.GENERIC_SIZE_THREE),
	
	/** This is an average amount of cum that a human male produces when orgasming. */
	THREE_AVERAGE("an average amount of", 6, 16, 25, 1f, Wetness.THREE_WET, Colour.GENERIC_SIZE_FOUR),
	
	/** This is a large amount of cum, although relatively speaking, it's not a huge quantity of liquid. */
	FOUR_LARGE("a large amount of", 16, 30, 5, 1.2f, Wetness.FOUR_SLIMY, Colour.GENERIC_SIZE_FIVE),
	
	/** This is a very large amount of cum. (About a quarter of a can of coke.)*/
	FIVE_HUGE("a huge amount of", 30, 100, 0, 1.4f, Wetness.FIVE_SLOPPY, Colour.GENERIC_SIZE_SIX),
	
	/** This and SEVEN_MONSTROUS are a bit ridiculous, and should only be used as part of "extreme proportion" content. */
	SIX_EXTREME("an extreme amount of", 100, 1000, 0, 1.6f, Wetness.SIX_SOPPING_WET, Colour.GENERIC_SIZE_SEVEN),
	
	/** This and SIX_EXTREME are a bit ridiculous, and should only be used as part of "extreme proportion" content. */
	SEVEN_MONSTROUS("a monstrous amount of", 1000, 10000, 0, 2f, Wetness.SEVEN_DROOLING, Colour.GENERIC_SIZE_EIGHT);

	private int minimumValue;
	private int maximumValue;
	private int arousalNeededToStartPreCumming;
	private float pregnancyModifier;
	private String descriptor;
	private Wetness associatedWetness;
	private Colour colour;

	private CumProduction(String descriptor, int minimumValue, int maximumValue, int arousalNeededToStartPreCumming, float pregnancyModifier, Wetness associatedWetness, Colour colour) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.arousalNeededToStartPreCumming = arousalNeededToStartPreCumming;
		this.pregnancyModifier = pregnancyModifier;
		this.associatedWetness = associatedWetness;
		this.colour = colour;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}

	public int getMedianValue() {
		return minimumValue + (maximumValue - minimumValue) / 2;
	}

	public int getArousalNeededToStartPreCumming() {
		return arousalNeededToStartPreCumming;
	}

	public static CumProduction getCumProductionFromInt(int cumProduction) {
		for(CumProduction cp : CumProduction.values()) {
			if(cumProduction>=cp.getMinimumValue() && cumProduction<cp.getMaximumValue()) {
				return cp;
			}
		}
		return SEVEN_MONSTROUS;
	}

	/**
	 * To fit into a sentence: "Your balls are producing "+getDescriptor()+"."
	 * "They are capable of producing "getDescriptor()+", averaging at about "
	 * +penis.getCumProduction()+"mL at each orgasm."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	/**
	 * In the range of 0 to 2. Average is 1.
	 */
	public float getPregnancyModifier() {
		return pregnancyModifier;
	}

	public Wetness getAssociatedWetness() {
		return associatedWetness;
	}

	public Colour getColour() {
		return colour;
	}
}
