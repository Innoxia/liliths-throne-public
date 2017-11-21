package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.88
 * @version 0.1.88
 * @author Innoxia
 */
public enum AddictionLevel {
	ZERO_NO_ADDICTION("none", 0, 1, Colour.CORRUPTION_STAGE_ZERO, 0),
	ONE_MILD("mild", 1, 20, Colour.CORRUPTION_STAGE_ONE, 2),
	TWO_NOTICEABLE("noticeable", 20, 40, Colour.CORRUPTION_STAGE_TWO, 3),
	THREE_STRONG("strong", 40, 60, Colour.CORRUPTION_STAGE_THREE, 4),
	FOUR_SEVERE("severe", 60, 80, Colour.CORRUPTION_STAGE_FOUR, 5),
	FIVE_DEPENDENCE("dependence", 80, 101, Colour.CORRUPTION_STAGE_FIVE, 6);

	private String name;
	private int minimumAddiction, maximumAddiction;
	private Colour colour;
	private int daysUntilAddictionCured;

	private AddictionLevel(String name, int minimumAddiction, int maximumAddiction, Colour colour, int daysUntilAddictionCured) {
		this.name = name;
		this.minimumAddiction = minimumAddiction;
		this.maximumAddiction = maximumAddiction;
		this.colour = colour;
		this.daysUntilAddictionCured = daysUntilAddictionCured;
	}

	public int getMinimumValue() {
		return minimumAddiction;
	}

	public int getMaximumValue() {
		return maximumAddiction;
	}
	
	public int getMedianValue() {
		return minimumAddiction + (maximumAddiction - minimumAddiction) / 2;
	}

	public static AddictionLevel valueOf(int bodySize) {
		for(AddictionLevel f : AddictionLevel.values()) {
			if(bodySize>=f.getMinimumValue() && bodySize<f.getMaximumValue()) {
				return f;
			}
		}
		return FIVE_DEPENDENCE;
	}
	
	public String getName(boolean withDeterminer) {
		if(withDeterminer) {
			return UtilText.generateSingularDeterminer(name) + " " + name;
		} else {
			return name;
		}
	}
	
	public Colour getColour() {
		return colour;
	}

	public int getDaysUntilAddictionCured() {
		return daysUntilAddictionCured;
	}
}
