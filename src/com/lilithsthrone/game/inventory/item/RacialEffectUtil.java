package com.lilithsthrone.game.inventory.item;

public abstract class RacialEffectUtil {
	
	private String description;
	
	public RacialEffectUtil(String description) {
		this.description = description;
	}
	
	public abstract String applyEffect();

	public String getDescription() {
		return description;
	}
}
