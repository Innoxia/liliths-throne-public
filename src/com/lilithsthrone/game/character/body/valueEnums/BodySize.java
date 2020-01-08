package com.lilithsthrone.game.character.body.valueEnums;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.83
 * @version 0.3.5
 * @author Innoxia
 */
public enum BodySize {
	ZERO_SKINNY("skinny", 0, 20, Colour.BODY_SIZE_ZERO),
	ONE_SLENDER("slender", 20, 40, Colour.BODY_SIZE_ONE),
	TWO_AVERAGE("average", 40, 60, Colour.BODY_SIZE_TWO),
	THREE_LARGE("large", 60, 80, Colour.BODY_SIZE_THREE),
	FOUR_HUGE("huge", 80, 100, Colour.BODY_SIZE_FOUR);

	private String name;
	private int minimumBodySize, maximumBodySize;
	private Colour colour;

	private BodySize(String name, int minimumBodySize, int maximumBodySize, Colour colour) {
		this.name = name;
		this.minimumBodySize = minimumBodySize;
		this.maximumBodySize = maximumBodySize;
		this.colour = colour;
	}

	public int getMinimumValue() {
		return minimumBodySize;
	}

	public int getMaximumValue() {
		return maximumBodySize;
	}
	
	public int getMedianValue() {
		return minimumBodySize + (maximumBodySize - minimumBodySize) / 2;
	}

	public static BodySize valueOf(int bodySize) {
		for(BodySize f : BodySize.values()) {
			if(bodySize>=f.getMinimumValue() && bodySize<f.getMaximumValue()) {
				return f;
			}
		}
		return FOUR_HUGE;
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
}
