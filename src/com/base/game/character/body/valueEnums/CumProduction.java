package com.base.game.character.body.valueEnums;

/**
 * Measured in millilitres.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum CumProduction {

	/** Self-explanatory.... */
	ZERO_NONE("no cum at all", 0, 1, 100, 0),
	
	/** There's about one or two drops of cum here... */
	ONE_TRICKLE("a tiny trickle of cum", 1, 3, 75, 0.5f),
	
	/** This is significantly less than an average amount of cum that a human male produces when orgasming. */
	TWO_SMALL_AMOUNT("a small amount of cum", 3, 6, 50, 0.75f),
	
	/** This is an average amount of cum that a human male produces when orgasming. */
	THREE_AVERAGE("an average amount of cum", 6, 16, 25, 1f),
	
	/** This is a large amount of cum, although relatively speaking, it's not a huge quantity of liquid. */
	FOUR_LARGE("a large amount of cum", 16, 30, 5, 1.2f),
	
	/** This is a very large amount of cum. (About a quarter of a can of coke.)*/
	FIVE_HUGE("a huge amount of cum", 30, 100, 0, 1.4f),
	
	/** This and SEVEN_MONSTROUS are a bit ridiculous, and should only be used as part of "extreme proportion" content. */
	SIX_EXTREME("an extreme amount of cum", 100, 1000, 0, 1.6f),
	
	/** This and SIX_EXTREME are a bit ridiculous, and should only be used as part of "extreme proportion" content. */
	SEVEN_MONSTROUS("a monstrous amount of cum", 1000, 10000, 0, 2f);

	private int minimumValue, maximumValue, arousalNeededToStartPreCumming;
	private float pregnancyModifer;
	private String descriptor;

	private CumProduction(String descriptor, int minimumValue, int maximumValue, int arousalNeededToStartPreCumming, float pregnancyModifer) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.arousalNeededToStartPreCumming = arousalNeededToStartPreCumming;
		this.pregnancyModifer = pregnancyModifer;
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
	public float getPregnancyModifer() {
		return pregnancyModifer;
	}
}
