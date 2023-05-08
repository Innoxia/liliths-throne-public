package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public enum AlcoholLevel {
	
	ZERO_SOBER("sober", 0, 0, 0.01f, PresetColour.ALCOHOL_LEVEL_ZERO),
	
	ONE_TIPSY("tipsy", 0, 0.01f, 0.2f, PresetColour.ALCOHOL_LEVEL_ONE),
	
	TWO_MERRY("merry", 0, 0.2f, 0.4f, PresetColour.ALCOHOL_LEVEL_TWO),
	
	THREE_DRUNK("drunk", 30, 0.4f, 0.6f, PresetColour.ALCOHOL_LEVEL_THREE),
	
	FOUR_HAMMERED("hammered", 20, 0.6f, 0.8f, PresetColour.ALCOHOL_LEVEL_FOUR),
	
	FIVE_WASTED("wasted", 10, 0.8f, 1, PresetColour.ALCOHOL_LEVEL_FIVE);
	
	private String name;
	private float minimumValue;
	private float maximumValue;
	private Colour colour;
	private int slurredSpeechFrequency;

	private AlcoholLevel(String name, int slurredSpeechFrequency, float minimumValue, float maximumValue, Colour colour) {
		this.name = name;
		this.slurredSpeechFrequency = slurredSpeechFrequency;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
	}
	
	public String getName() {
		return name;
	}
	public int getSlurredSpeechFrequency() {
		return slurredSpeechFrequency;
	}

	public float getMinimumValue() {
		return minimumValue;
	}

	public float getMaximumValue() {
		return maximumValue;
	}

	public Colour getColour() {
		return colour;
	}
	
	public static AlcoholLevel getAlcoholLevelFromValue(float value){
		for(AlcoholLevel al : AlcoholLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return FIVE_WASTED;
	}
	
	public boolean isLessThan(AlcoholLevel level) {
		return this.getMinimumValue()<level.getMinimumValue();
	}
	
	public boolean isGreaterThan(AlcoholLevel level) {
		return this.getMinimumValue()>level.getMinimumValue();
	}
}
