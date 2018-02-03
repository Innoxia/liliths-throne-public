package com.lilithsthrone.game.sex;

import com.lilithsthrone.utils.BaseColour;

/**
 * @since 0.1.69.9
 * @version 0.1.99
 * @author Innoxia
 */
public enum SexPace {
	
	SUB_RESISTING(3, 3, false, "resisting", BaseColour.CRIMSON),
	SUB_NORMAL(2, 0, false, "normal", BaseColour.PINK),
	SUB_EAGER(3, 0, false, "eager", BaseColour.PINK_DEEP),
	
	DOM_GENTLE(1, 0, true, "gentle", BaseColour.PINK_LIGHT),
	DOM_NORMAL(2, 0, true, "normal", BaseColour.PINK),
	DOM_ROUGH(3, 1, true, "rough", BaseColour.CRIMSON);
	

	private int actionStaminaCostSelf;
	private int actionStaminaCostPartner;
	private boolean isDom;
	private String name;
	private BaseColour colour;
	
	private SexPace(int actionStaminaCostSelf, int actionStaminaCostPartner, boolean isDom, String name, BaseColour colour) {
		this.actionStaminaCostSelf = actionStaminaCostSelf;
		this.actionStaminaCostPartner = actionStaminaCostPartner;
		this.isDom = isDom;
		this.name = name;
		this.colour = colour;
	}

	public int getActionStaminaCostSelf() {
		return actionStaminaCostSelf;
	}
	
	public int getActionStaminaCostPartner() {
		return actionStaminaCostPartner;
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
