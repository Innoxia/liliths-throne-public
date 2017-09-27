package com.lilithsthrone.game.sex;

import com.lilithsthrone.utils.BaseColour;

/**
 * @since 0.1.69.9
 * @version 0.1.79
 * @author Innoxia
 */
public enum SexPace {
	
	SUB_RESISTING(false, "resisting", BaseColour.CRIMSON),
	SUB_NORMAL(false, "normal", BaseColour.PINK),
	SUB_EAGER(false, "eager", BaseColour.PINK_DEEP), //TODO currently not used
	
	DOM_GENTLE(true, "gentle", BaseColour.PINK_LIGHT), //TODO currently not used
	DOM_NORMAL(true, "normal", BaseColour.PINK),
	DOM_ROUGH(true, "rough", BaseColour.CRIMSON);
	
	
	private boolean isDom;
	private String name;
	private BaseColour colour;

	private SexPace(boolean isDom, String name, BaseColour colour) {
		this.isDom = isDom;
		this.name = name;
		this.colour = colour;
	}

	public boolean isDom() {
		return isDom;
	}

	public String getName() {
		return name;
	}

	public BaseColour getColour() {
		return colour;
	}
}
