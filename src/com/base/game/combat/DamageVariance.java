package com.base.game.combat;

/**
 * @since 0.1.0
 * @version 0.1.64
 * @author Innoxia
 */
public enum DamageVariance {
	NONE(0), LOW(0.1f), MEDIUM(0.2f), HIGH(0.5f);

	private float percentange;

	private DamageVariance(float percentange) {
		this.percentange = percentange;
	}

	public float getPercentange() {
		return percentange;
	}
}
