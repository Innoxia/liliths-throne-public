package com.lilithsthrone.game.settings;

/**
 * @since 0.1.95
 * @version 0.2.3
 * @author FeiFongWong
 */
public enum ForcedFetishTendency {

	BOTTOM_HEAVY("Bottoming+", "Forced fetishes will almost always add bottoming and remove topping activities and behaviours."),

	BOTTOM("Bottoming", "While NPC tastes still matter, forced fetishes will usually add bottoming and remove topping activities and behaviours."),

	NEUTRAL("Neutral", "Forced fetishes will be determined solely by the tastes and whims of the controlling NPC, and the inherent randomness of the universe."),

	TOP ("Topping", "While NPC tastes still matter, forced fetishes will usually add topping and remove bottoming activities and behaviours."),

	TOP_HEAVY("Topping+", "Forced fetishes will almost always add topping and remove bottoming activities and behaviours.");


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
