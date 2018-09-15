package com.lilithsthrone.game.settings;

/**
 * @since 0.1.78
 * @version 0.2.11
 * @author Innoxia
 */
public enum ContentPreferenceValue {

	ZERO_NONE("off", 0),
	
	ONE_MINIMAL("minimal", 1),
	
	TWO_LOW("low", 5),
	
	THREE_AVERAGE("average", 10),
	
	FOUR_HIGH("high", 20),
	
	FIVE_ABUNDANT("abundant", 40);

	private String name;
	private int value;
	
	private ContentPreferenceValue(String name, int value) {
		this.name= name;
		this.value=value;
	}
	
	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
