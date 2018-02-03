package com.lilithsthrone.game;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author FeiFongWong
 */
public enum ForcedFetishTendency {
	
	BOTTOM_HEAVY("Bottoming (Heavy)", "Forced fetishes will almost always add bottoming and remove topping activities and behaviors."),
	
	BOTTOM("Bottoming", "While NPC tastes still matter, forced fetishes will usually add bottoming and remove topping activities and behaviors."),
	
	NEUTRAL("Neutral", "Forced fetishes will be determined solely by the tastes and whims of the controlling NPC, and the inherent radomness of the universe."),
	
	TOP ("Topping", "While NPC tastes still matter, forced fetishes will usually add topping and remove bottoming activities and behaviors."),
	
	TOP_HEAVY("Topping (Heavy)", "Forced fetishes will almost always add topping and remove bottoming activities and behaviors.");


	private String name;
	private String description;

	
	private ForcedFetishTendency(String name, String description) {
		this.name = name;
		this.description = description;

	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}



}
