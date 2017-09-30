package com.lilithsthrone.game.combat;

/**
 * @since 0.1.0
 * @version 0.1.64
 * @author Innoxia
 */
public enum DamageVariance {
	NONE(0), LOW(0.1f), MEDIUM(0.2f), HIGH(0.5f);

	private float percentage;

	private DamageVariance(float percentage) {
		this.percentage = percentage;
	}

	public float getPercentage() {
		return percentage;
	}
}
