package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Defined according to how much fluid is replenished every day, in ml.
 * 
 * @since 0.2.1
 * @version 0.3.2
 * @author Innoxia
 */
public enum FluidRegeneration {

	/** 0-249 ml/day. */
	ZERO_SLOW("slow", 0, 250, "slowly", PresetColour.GENERIC_SIZE_ONE),
	
	/** 250-749 ml/day. <b>Median value of 500ml/day is roughly the average for breasts producing milk.</b> */
	ONE_AVERAGE("average", 250, 750, "", PresetColour.GENERIC_SIZE_TWO),

	/** 750-4999 ml/day. Maximum value is roughly 3.5ml per minute. */
	TWO_FAST("fast", 750, 5000, "quickly", PresetColour.GENERIC_SIZE_THREE),

	/** 5000-99,999 ml/day. <b>This median value of ~50,000ml/day is a more realistic average for seminal fluid replenishment, but for gameplay purposes, the default is 10,000.</b> Maximum value is roughly 1.15ml per second. */
	THREE_RAPID("rapid", 5000, 100_000, "rapidly", PresetColour.GENERIC_SIZE_FOUR),
	
	/** 100,000-500,000 ml/day. Maximum value is roughly 5.8ml per second. */
	FOUR_VERY_RAPID("very rapid", 100_000, 500_000, "very rapidly", PresetColour.GENERIC_SIZE_FIVE);
	

	private int minimumValue;
	private int maximumValue;
	private String name;
	private String verb;
	private Colour colour;
	
	public static int CUM_REGEN_DEFAULT = 10_000;

	private FluidRegeneration(String name, int minimumValue, int maximumValue, String verb, Colour colour) {
		this.name = name;
		
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		
		this.verb = verb;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public String getVerb() {
		return verb;
	}
	
	public static FluidRegeneration getFluidRegenerationFromInt(int value) {
		for(FluidRegeneration regeneration : FluidRegeneration.values()) {
			if(value>=regeneration.getMinimumRegenerationValuePerDay() && value<regeneration.getMaximumRegenerationValuePerDay()) {
				return regeneration;
			}
		}
		return FOUR_VERY_RAPID;
	}

	public Colour getColour() {
		return colour;
	}

	// I gave them long method names as I know that I'll forget they're defined as ml/day otherwise...
	
	/**
	 * Will need to be divided by seconds per day (60*60*24), as the value returned is defined as ml per day.
	 * @return
	 */
	public int getMinimumRegenerationValuePerDay() {
		return minimumValue;
	}

	/**
	 * Will need to be divided by seconds per day (60*60*24), as the value returned is defined as ml per day.
	 * @return
	 */
	public int getMaximumRegenerationValuePerDay() {
		return maximumValue;
	}
	

	public int getMedianRegenerationValuePerDay() {
		return minimumValue + ((maximumValue - minimumValue) / 2);
	}
}
