package com.lilithsthrone.game.character.race;

/**
 * @since 0.1.78
 * @version 0.1.86
 * @author Innoxia
 */
public enum SubspeciesPreference {

	ZERO_NONE("off", 0),
	ONE_MINIMAL("minimal", 10),
	TWO_LOW("low", 25),
	THREE_AVERAGE("average", 50),
	FOUR_HIGH("high", 75),
	FIVE_ABUNDANT("abundant", 100);

	private String name;
	private int value;
	
	private SubspeciesPreference(String name, int value) {
		this.name=name;
		this.value=value;
	}
	
	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
