package com.lilithsthrone.game.sex.sexActions;

/**
 * @since 0.3
 * @version 0.3.1
 * @author Innoxia
 */
public abstract class SexActionOrgasmOverride {
	
	private boolean endsSex;
	
	public SexActionOrgasmOverride(boolean endsSex) {
		this.endsSex = endsSex;
	}

	public abstract void applyEffects();
	
	public void applyEndEffects() {
	}

	public boolean isEndsSex() {
		return endsSex;
	}

	public abstract String getDescription();
}
