package com.base.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum FluidModifier {

	MUSKY("musky"),
	VISCOUS("viscous"),
	STICKY("sticky"),
	SLIMY("slimy"),
	BUBBLING("bubbling"),
	ADDICTIVE("addictive"),
	HALLUCINOGENIC("hallucinogenic");
	
	private String descriptor;

	private FluidModifier(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getName() {
		return descriptor;
	}
}
