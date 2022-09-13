package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Measured in millilitres.
 * 
 * @since 0.1.0
 * @version 0.3.8.7
 * @author Innoxia
 */
public enum CumProduction {

	/** Self-explanatory.... */
	ZERO_NONE("none", "no", 0, 1, 100, Wetness.ZERO_DRY, PresetColour.GENERIC_SIZE_ONE, 0),
	
	/** There's about one or two drops of cum here... */
	ONE_TRICKLE("drop", "a few drops of", 1, 3, 75, Wetness.ONE_SLIGHTLY_MOIST, PresetColour.GENERIC_SIZE_TWO, 0),
	
	/** This is significantly less than an average amount of cum that a human male produces when orgasming. */
	TWO_SMALL_AMOUNT("trickle", "a trickle of", 3, 6, 50, Wetness.TWO_MOIST, PresetColour.GENERIC_SIZE_THREE, 0),
	
	/** This is an average amount of cum that a human male produces when orgasming. */
	THREE_AVERAGE("average", "an average amount of", 6, 16, 25, Wetness.THREE_WET, PresetColour.GENERIC_SIZE_FOUR, 0),
	
	/** This is a large amount of cum, although relatively speaking, it's not a huge quantity of liquid. */
	FOUR_LARGE("large", "a large amount of", 16, 30, 5, Wetness.FOUR_SLIMY, PresetColour.GENERIC_SIZE_FIVE, 1),
	
	/** This is a very large amount of cum. (About a quarter of a can of coke.)*/
	FIVE_HUGE("huge", "a huge amount of", 30, 100, 0, Wetness.FIVE_SLOPPY, PresetColour.GENERIC_SIZE_SIX, 2),
	
	/** This and SEVEN_MONSTROUS are a bit ridiculous, and should only be used as part of "extreme proportion" content. */
	SIX_EXTREME("extreme", "an extreme amount of", 100, 1000, 0, Wetness.SIX_SOPPING_WET, PresetColour.GENERIC_SIZE_SEVEN, 4),
	
	/** This and SIX_EXTREME are a bit ridiculous, and should only be used as part of "extreme proportion" content. */
	SEVEN_MONSTROUS("monstrous", "a monstrous amount of", 1000, 10000, 0, Wetness.SEVEN_DROOLING, PresetColour.GENERIC_SIZE_EIGHT, 8);

	private String name;
	private String descriptor;
	private int minimumValue;
	private int maximumValue;
	private int arousalNeededToStartPreCumming;
	private Wetness associatedWetness;
	private Colour colour;
	private int additionalSlotsDirtiedUponOrgasm;

	private CumProduction(String name, String descriptor, int minimumValue, int maximumValue, int arousalNeededToStartPreCumming, Wetness associatedWetness, Colour colour, int additionalSlotsDirtiedUponOrgasm) {
		this.name = name;
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.arousalNeededToStartPreCumming = arousalNeededToStartPreCumming;
		this.associatedWetness = associatedWetness;
		this.colour = colour;
		this.additionalSlotsDirtiedUponOrgasm = additionalSlotsDirtiedUponOrgasm;
	}

	public String getName() {
		return name;
	}

	/**
	 * To fit into a sentence: "Your balls are producing "+getDescriptor()+"."
	 * "They are capable of producing "getDescriptor()+", averaging at about "
	 * +penis.getCumProduction()+"mL at each orgasm."
	 */
	public String getDescriptor() {
		return descriptor;
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

	public Wetness getAssociatedWetness() {
		return associatedWetness;
	}

	public Colour getColour() {
		return colour;
	}
	
	/**
	 * @return The number of extra inventory slots which should be dirtied when this quantity of cum is being expelled upon a target.
	 */
	public int getAdditionalSlotsDirtiedUponOrgasm() {
		return additionalSlotsDirtiedUponOrgasm;
	}
	
	
}
