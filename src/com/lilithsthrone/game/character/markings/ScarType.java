package com.lilithsthrone.game.character.markings;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public enum ScarType {

	CLAW_MARKS("claw mark", "claw marks", true),
	
	BURNS("burn mark", "burn marks", false),
	
	STRAIGHT_SCAR("straight scar", "straight scars", false),
	
	JAGGED_SCAR("jagged scar", "jagged scars", false),
	
	BRUIS("bruise", "bruises", false);
	
	private String name;
	private String namePlural;
	private boolean alwaysPlural;
	
	private ScarType(String name, String namePlural, boolean alwaysPlural) {
		this.name = name;
		this.namePlural = namePlural;
		this.alwaysPlural = alwaysPlural;
	}

	public String getName() {
		return name;
	}

	public String getNamePlural() {
		return namePlural;
	}
	
	public boolean isAlwaysPlural() {
		return alwaysPlural;
	}
	
}
