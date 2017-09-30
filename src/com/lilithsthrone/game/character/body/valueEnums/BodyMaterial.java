package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum BodyMaterial {

	FLESH("flesh"),
	SLIME("slime"),
	FIRE("fire"),
	ICE("ice"),
	RUBBER("rubber");
	
	private String descriptor;

	private BodyMaterial(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getName() {
		return descriptor;
	}
}
