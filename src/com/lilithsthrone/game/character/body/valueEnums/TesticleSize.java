package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum TesticleSize {

	/**Little bumps under the skin.*/
	ZERO_VESTIGIAL("vestigial", 0, PresetColour.GENERIC_SIZE_ONE),

	/**Small for a human.*/
	ONE_TINY("tiny", 1, PresetColour.GENERIC_SIZE_TWO),
	
	/**Average for a human.*/
	TWO_AVERAGE("average-sized", 2, PresetColour.GENERIC_SIZE_THREE),

	/**Large for a human. Average for a dog or wolf-morph.*/
	THREE_LARGE("plumb-sized", 3, PresetColour.GENERIC_SIZE_FOUR), //large

	/**Unrealistically large for a human. Average for a horse-morph.*/
	FOUR_HUGE("softball-sized", 4, PresetColour.GENERIC_SIZE_FIVE), //huge

	/**Large for a horse-morph.*/
	FIVE_MASSIVE("football-sized", 5, PresetColour.GENERIC_SIZE_SIX), //massive

	/**Getting pretty absurd.*/
	SIX_GIGANTIC("soccerball-sized", 6, PresetColour.GENERIC_SIZE_SEVEN), //gigantic

	/**"Extreme proportion" content.*/
	SEVEN_ABSURD("watermellon-sized", 7, PresetColour.GENERIC_SIZE_EIGHT), //absurdly enormous

	/**"Small hyper proportion content. - size of a large pumpkin - movement hindering*/
	EIGHT_SLOWING("large pumpkin-sized", 8, PresetColour.GENERIC_SIZE_NINE),

	/**"hyper proportion content. - size of a beanbag - touches ground*/
	NINE_DRAGGING("beanbag-sized", 9, PresetColour.GENERIC_SIZE_TEN),

	/**"hyper proportion content. - size of a multiperson beanbag - drags on the ground behind player*/
	TEN_INSANE("ground-dragging", 10, PresetColour.GENERIC_SIZE_ELEVEN),

	/**"immobilizing proportion content. - size of a multiperson beanbag - drags on the ground behind player*/
	ELEVEN_IMMOBILIZING("immobilizing", 11, PresetColour.GENERIC_SIZE_TWELVE);

	private int value;
	private String descriptor;
	private Colour colour;

	private TesticleSize(String descriptor, int value, Colour colour) {
		this.descriptor = descriptor;
		this.value = value;
		this.colour=colour;
	}

	public int getValue() {
		return value;
	}

	public static TesticleSize getTesticleSizeFromInt(int index) {
		for(TesticleSize ts : TesticleSize.values()) {
			if(index == ts.getValue()) {
				return ts;
			}
		}
		return ELEVEN_IMMOBILIZING;
	}
	
	public static TesticleSize getMaxSize() {
		return getTesticleSizeFromInt(-1);
	}
	

	/**
	 * To fit into a sentence: "Your balls are "+getDescriptor()+"."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public Colour getColour() {
		return colour;
	}
}
