package com.lilithsthrone.game.sex.sexActions;

/**
 * @since 0.3
 * @version 0.3.1
 * @author Innoxia
 */
public abstract class SexActionOrgasmOverride {
	
	private boolean endsSex;
	private String description;
	
	public SexActionOrgasmOverride(boolean endsSex, String description) {
		super();
		this.endsSex = endsSex;
		this.description = description;
	}

	public abstract void applyEffects();
	
	public void applyEndEffects() {
	}

	public boolean isEndsSex() {
		return endsSex;
	}

	public String getDescription() {
		return description;
	}
}
