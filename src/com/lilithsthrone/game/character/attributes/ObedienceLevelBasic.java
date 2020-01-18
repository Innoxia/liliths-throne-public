package com.lilithsthrone.game.character.attributes;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public enum ObedienceLevelBasic {

	/** -100 to -30*/
	DISOBEDIENT("disobedient", -100, -30, Colour.AFFECTION_NEGATIVE_FIVE),

	/** -30 to 30*/
	NEUTRAL("neutral", -30, 30, Colour.AFFECTION_NEUTRAL),

	/** 30 to 100*/
	OBEDIENT("obedient", 30, 100, Colour.AFFECTION_POSITIVE_FIVE);
	
	
	private String name;
	private int minimumValue, maximumValue;
	private Colour colour;

	private ObedienceLevelBasic(String name, int minimumValue, int maximumValue, Colour colour) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
	}
	
	public String getName() {
		return name;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}
	
	public int getMedianValue() {
		return (minimumValue + maximumValue) / 2;
	}

	public Colour getColour() {
		return colour;
	}

	public static ObedienceLevelBasic getObedienceLevelFromValue(float value){
		for(ObedienceLevelBasic al : ObedienceLevelBasic.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return OBEDIENT;
	}
}
