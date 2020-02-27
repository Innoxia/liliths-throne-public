package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.3.6.6
 * @author Innoxia
 */
public enum PenetrationModifier {

	SHEATHED("sheathed", "Helps to hide bulge in clothing when not erect."),
	
	RIBBED("ribbed", ""),
	
	TENTACLED("tentacled", ""),
	
	KNOTTED("knotted", "Enables the affected penetration type to be locked inside an orifice at the moment of orgasm."),
	
	TAPERED("tapered", "Reduces effective diameter of the affected penetration type by 5%."),
	
	BLUNT("blunt", ""),
	
	FLARED("flared", "Increases effective diameter of the affected penetration type by 5%."),
	
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

	public String getDescription() {
		if(description.isEmpty()) {
			return "No gameplay effect.";
		}
		return description;
	}
}
