package com.base.game.character.attributes;

import com.base.utils.Colour;

/**
 * -100 to -80 = hate</br>
 * -79  to -60 = strongly dislike</br>
 * -59  to -40 = dislike</br>
 * -39  to -20 = annoyed</br>
 * -19  to -5 = annoyed</br>
 * -4   to  4 = neutral</br>
 * 5    to  19 = friendly</br>
 * 20   to  39 = like</br>
 * 40   to  59 = caring </br>
 * 60   to  79 = cherish</br>
 * 80   to 100 = love</br>
 * 
 * @since 0.1.78
 * @version 0.1.79
 * @author Innoxia
 */
public enum AffectionLevel {
	

	NEGATIVE_FIVE_HATE("hate", -100, -80, Colour.AFFECTION_NEGATIVE_FIVE),
	
	NEGATIVE_FOUR_STRONG_DISLIKE("strong dislike", -80, -60, Colour.AFFECTION_NEGATIVE_FOUR),
	
	NEGATIVE_THREE_DISLIKE("dislike", -60, -40, Colour.AFFECTION_NEGATIVE_THREE),
	
	NEGATIVE_TWO_COLD("cold", -40, -20, Colour.AFFECTION_NEGATIVE_TWO),
	
	NEGATIVE_ONE_ANNOYED("annoyed", -20, -5, Colour.AFFECTION_NEGATIVE_ONE),

	ZERO_NEUTRAL("neutral", -5, 5, Colour.AFFECTION_NEUTRAL),

	POSITIVE_ONE_FRIENDLY("friendly", 5, 20, Colour.AFFECTION_POSITIVE_ONE),

	POSITIVE_TWO_LIKE("like", 20, 40, Colour.AFFECTION_POSITIVE_TWO),

	POSITIVE_THREE_CARING("caring", 40, 60, Colour.AFFECTION_POSITIVE_THREE),

	POSITIVE_FOUR_CHERISH("cherish", 60, 80, Colour.AFFECTION_POSITIVE_FOUR),

	POSITIVE_FIVE_LOVE("love", 80, 100, Colour.AFFECTION_POSITIVE_FIVE);
	
	
	private String name;
	private int minimumValue, maximumValue;
	private Colour colour;
	private boolean mutualOrgasm;

	private AffectionLevel(String name, int minimumValue, int maximumValue, Colour colour) {
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
	
	public boolean isMutualOrgasm() {
		return mutualOrgasm;
	}

	public static AffectionLevel getAffectionLevelFromValue(float value){
		for(AffectionLevel al : AffectionLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return POSITIVE_FIVE_LOVE;
	}
}
