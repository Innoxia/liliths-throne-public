package com.base.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum CoveringPattern {
	NONE("plain"),
	
	FLUID("fluid"),
	
	ORIFICE_ANUS("anus"),
	ORIFICE_VAGINA("vagina"),
	ORIFICE_NIPPLE("nipple"),
	ORIFICE_MOUTH("mouth"),
	
	HAIR_HIGHLIGHTS("highlighted"),

	EYE_IRISES("standard"),
	EYE_IRISES_HETEROCHROMATIC("heterochromatic"),
	EYE_PUPILS("standard"),
	EYE_PUPILS_HETEROCHROMATIC("heterochromatic"),
	
	STRIPED("striped"),
	SPOTTED("spotted"),
	MOTTLED("mottled");
	
	private String name;

	private CoveringPattern(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
