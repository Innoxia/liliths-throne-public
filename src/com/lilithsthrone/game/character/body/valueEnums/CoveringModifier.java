package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.99
 * @version 0.1.99
 * @author Innoxia
 */
public enum CoveringModifier {

	EYE("eye"),
	FLUID("fluid"),
	MAKEUP("makeup"),
	
	SHORT("short"),
	SILKEN("silken"),
	SMOOTH("smooth"),
	FLUFFY("fluffy"),
	SHAGGY("shaggy"),
	FURRY("fur-like"),
	COARSE("coarse");
	
	private String descriptor;

	private CoveringModifier(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getName() {
		return descriptor;
	}
}
