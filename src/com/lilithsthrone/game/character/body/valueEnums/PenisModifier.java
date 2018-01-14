package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.1.95
 * @author Innoxia
 */
public enum PenisModifier {

	SHEATHED("sheathed"),
	RIBBED("ribbed"),
	TENTACLED("tentacled"),
	KNOTTED("knotted"),
	TAPERED("tapered"),
	BLUNT("blunt"),
	FLARED("flared"),
	BARBED("barbed"),
	VEINY("veiny"),
	PREHENSILE("prehensile");
	
	private String descriptor;

	private PenisModifier(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getName() {
		return descriptor;
	}
}
