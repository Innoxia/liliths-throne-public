package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum OrificeModifier {
	
	PUFFY("puffy"),
	RIBBED("internally-ribbed"),
	TENTACLED("tentacled"),
	MUSCLE_CONTROL("internally-muscled");
	
	private String descriptor;

	private OrificeModifier(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getName() {
		return descriptor;
	}
}
