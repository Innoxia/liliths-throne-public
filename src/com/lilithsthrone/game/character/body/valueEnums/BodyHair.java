package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.83
 * @version 0.1.86
 * @author Innoxia
 */
public enum BodyHair {

	ZERO_NONE(0, "none", PresetColour.GENERIC_SIZE_ONE),
	ONE_STUBBLE(1, "stubble", PresetColour.GENERIC_SIZE_TWO),
	TWO_MANICURED(2, "manicured", PresetColour.GENERIC_SIZE_THREE),
	THREE_TRIMMED(3, "trimmed", PresetColour.GENERIC_SIZE_FOUR),
	FOUR_NATURAL(4, "natural", PresetColour.GENERIC_SIZE_FIVE),
	FIVE_UNKEMPT(5, "unkempt", PresetColour.GENERIC_SIZE_SIX),
	SIX_BUSHY(6, "bushy", PresetColour.GENERIC_SIZE_SEVEN),
	SEVEN_WILD(7, "wild", PresetColour.GENERIC_SIZE_EIGHT);
	
	private int value;
	private String descriptor;
	private Colour colour;

	private BodyHair(int value, String descriptor, Colour colour) {
		this.value = value;
		this.descriptor = descriptor;
		this.colour=colour;
	}
	
	public int getValue() {
		return value;
	}

	public String getName() {
		return descriptor;
	}

	public Colour getColour() {
		return colour;
	}
	
	public static BodyHair getRandomBodyHair() {
		return BodyHair.values()[Util.random.nextInt(BodyHair.values().length)];
	}
	
	public static BodyHair getBodyHairFromValue(int value) {
		for(BodyHair bh : BodyHair.values()) {
			if(bh.getValue() == value) {
				return bh;
			}
		}
		return ZERO_NONE;
	}
}
