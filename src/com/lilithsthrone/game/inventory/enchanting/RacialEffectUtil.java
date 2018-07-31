package com.lilithsthrone.game.inventory.enchanting;

/**
 * @since 0.1.7?
 * @version 0.1.89
 * @author Innoxia
 */
public abstract class RacialEffectUtil {
	
	private String description;
	
	public RacialEffectUtil(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public abstract String applyEffect();

}
