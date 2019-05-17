package com.lilithsthrone.game.character.body.valueEnums;

/**
 * Measured in millilitres.
 * 
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public enum Lactation {
	
	ZERO_NONE("none", "no", 0, 1, Wetness.ZERO_DRY),
	/** This, and all before, require actual milking to produce milk. */
	ONE_TRICKLE("trickle", "a tiny trickle of", 1, 30, Wetness.ONE_SLIGHTLY_MOIST),
	/** This, and all before, require actual milking to produce milk. */
	TWO_SMALL_AMOUNT("small", "a small amount of", 30, 100, Wetness.TWO_MOIST),
	/** This, and all before, require actual milking to produce milk. */
	THREE_DECENT_AMOUNT("decent", "a decent amount of", 100, 600, Wetness.THREE_WET),
	/** This, and all before, require actual milking to produce milk. */
	FOUR_LARGE_AMOUNT("large", "a large amount of", 600, 1000, Wetness.FOUR_SLIMY),
	/** They start drooling at the slightest touch. */
	FIVE_VERY_LARGE_DROOLING("huge", "a huge quantity of", 1000, 2000, Wetness.FIVE_SLOPPY),
	/** They start dripping at the slightest touch. */
	SIX_EXTREME_AMOUNT_DRIPPING("extreme", "an extreme amount of", 2000, 10000, Wetness.SIX_SOPPING_WET),
	/** They start pouring at the slightest touch. */
	SEVEN_MONSTROUS_AMOUNT_POURING("monstrous", "a monstrous amount of", 10000, 100000, Wetness.SEVEN_DROOLING);

	private String name;
	private String descriptor;
	private int minimumValue;
	private int maximumValue;
	private Wetness associatedWetness;

	private Lactation(String name, String descriptor, int minimumValue, int maximumValue, Wetness associatedWetness) {
		this.name = name;
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.associatedWetness = associatedWetness;
	}

	public String getName() {
		return name;
	}
	
	/**
	 * To fit into a sentence:<br/>
	 *  "Your breasts are producing "+getDescriptor()+" "+milkName+"."<br/>
	 *  "They are capable of producing "getDescriptor()+" "+milkName+", averaging at about "+breasts.getMilkProduction()+"mL each time they are milked."
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

	public static Lactation getLactationFromInt(int milkProduction) {
		for(Lactation l : Lactation.values()) {
			if(milkProduction>=l.getMinimumValue() && milkProduction<l.getMaximumValue()) {
				return l;
			}
		}
		return SEVEN_MONSTROUS_AMOUNT_POURING;
	}

	public Wetness getAssociatedWetness() {
		return associatedWetness;
	}
}
