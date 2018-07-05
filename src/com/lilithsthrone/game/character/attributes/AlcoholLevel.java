package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public enum AlcoholLevel {
	
	ZERO_SOBER("sober", 0, 0.01f, Colour.ALCOHOL_LEVEL_ZERO),
	
	ONE_TIPSY("tipsy", 0.01f, 0.2f, Colour.ALCOHOL_LEVEL_ONE),
	
	TWO_MERRY("merry", 0.2f, 0.4f, Colour.ALCOHOL_LEVEL_TWO),
	
	THREE_DRUNK("drunk", 0.4f, 0.6f, Colour.ALCOHOL_LEVEL_THREE),
	
	FOUR_HAMMERED("hammered", 0.6f, 0.8f, Colour.ALCOHOL_LEVEL_FOUR),
	
	FIVE_WASTED("wasted", 0.8f, 1, Colour.ALCOHOL_LEVEL_FIVE);
	
	private String name;
	private float minimumValue, maximumValue;
	private Colour colour;

	private AlcoholLevel(String name, float minimumValue, float maximumValue, Colour colour) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
	}
	
	public String getName() {
		return name;
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
}
