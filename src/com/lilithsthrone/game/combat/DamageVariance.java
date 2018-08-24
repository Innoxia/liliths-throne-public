package com.lilithsthrone.game.combat;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public enum DamageVariance {
	
	NONE(0),
	
	LOW(0.1f),
	
	MEDIUM(0.2f),
	
	HIGH(0.5f);

	private float percentage;

	private DamageVariance(float percentage) {
		this.percentage = percentage;
	}

	/**
	 * @return The percentage of damage variance that this value applies. Values are from 0 -> 1, mapping to 0% -> 100%
	 */
	public float getPercentage() {
		return percentage;
	}
}
