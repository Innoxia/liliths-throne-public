package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.3.7.2
 * @author Innoxia
 */
public enum OrificeModifier {
	
	PUFFY("puffy", ""),
	
	RIBBED("internally-ribbed", ""),
	
	TENTACLED("tentacled", ""),
	
	MUSCLE_CONTROL("internally-muscled", "Prevents this orifice from ever being regarded as 'too loose', no matter how slender an insertion nor how loose its capacity might be.");
	
	
	private String name;
	private String description;

	private OrificeModifier(String name, String description) {
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
