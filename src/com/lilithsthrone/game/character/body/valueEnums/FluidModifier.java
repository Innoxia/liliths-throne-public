package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.1.89
 * @author Innoxia
 */
public enum FluidModifier {

	MUSKY("musky"),
	VISCOUS("viscous"),
	STICKY("sticky"),
	SLIMY("slimy"),
	MINERAL_OIL("mineral oil"),
	BUBBLING("bubbling"),
	ALCOHOLIC("alcoholic"),
	ADDICTIVE("addictive"),
	HALLUCINOGENIC("psychoactive");
	
	private String descriptor;

	private FluidModifier(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getName() {
		return descriptor;
	}
}
