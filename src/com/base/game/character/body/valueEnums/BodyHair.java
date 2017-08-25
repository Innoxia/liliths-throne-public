package com.base.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum BodyHair {

	NONE(0, "no"),
	MANICURED(1, "manicured"),
	TRIMMED(2, "trimmed"),
	BUSHY(3, "bushy");
	
	private int value;
	private String descriptor;

	private BodyHair(int value, String descriptor) {
		this.value = value;
		this.descriptor = descriptor;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return descriptor;
	}
}
