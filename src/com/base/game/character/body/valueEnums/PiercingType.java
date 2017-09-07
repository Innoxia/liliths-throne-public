package com.base.game.character.body.valueEnums;

/**
 * @since 0.1.84
 * @version 0.1.84
 * @author Innoxia
 */
public enum PiercingType {
	
	EAR("ear"),
	NOSE("nose"),
	LIP("lip"),
	TONGUE("tongue"),
	NAVEL("navel"),
	NIPPLE("nipple"),
	VAGINA("vagina"),
	PENIS("penis");
	
	private String name;

	private PiercingType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
