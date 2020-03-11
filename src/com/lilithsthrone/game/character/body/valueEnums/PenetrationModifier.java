package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public enum PenetrationModifier {

	SHEATHED("sheathed", "Helps to hide bulge in clothing when not erect."),
	
	RIBBED("ribbed", ""),
	
	TENTACLED("tentacled", ""),
	
	KNOTTED("knotted", "Grants ability to be pushed inside an orifice at the moment of orgasm, doubling effective diameter and locking partners together. (Requires orifice to be deep enough for knotted base to be inserted.)"),
	
	TAPERED("tapered", "Reduces effective diameter by 5%."),
	
	BLUNT("blunt", ""),
	
	FLARED("flared", "Increases effective diameter by 5%."),
	
	BARBED("barbed", ""),
	
	VEINY("veiny", ""),
	
	PREHENSILE("prehensile", "");
	
	private String name;
	private String description;

	private PenetrationModifier(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}
	
	public boolean isSpecialEffects() {
		return !description.isEmpty();
	}
	
	public String getDescription() {
		if(description.isEmpty()) {
			return "No gameplay effect.";
		}
		return description;
	}
}
