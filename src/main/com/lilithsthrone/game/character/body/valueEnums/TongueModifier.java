package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum TongueModifier {
	
	RIBBED("ribbed"),
	TENTACLED("tentacled"),
	BIFURCATED("bifurcated");
	
	private String descriptor;

	private TongueModifier(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getName() {
		return descriptor;
	}
}
