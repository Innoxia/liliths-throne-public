package com.lilithsthrone.game.character.persona;

/**
 * @since 0.2.4
 * @version 0.2.4
 * @author Innoxia
 */
public enum PersonalityWeight {
	
	LOW(0, "low"),
	
	AVERAGE(1, "average"),
	
	HIGH(2, "high");
	
	private int value;
	private String name;
	
	private PersonalityWeight(int value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static PersonalityWeight getPersonalityWeightFromInt(int value) {
		if(value<=LOW.getValue()) {
			return LOW;
		} else if(value==AVERAGE.getValue()) {
			return AVERAGE;
		} else {
			return HIGH;
		}
	}
}
