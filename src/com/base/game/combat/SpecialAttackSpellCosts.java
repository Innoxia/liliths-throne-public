package com.base.game.combat;

/**
 * @since 0.1.0
 * @version 0.1.63
 * @author Innoxia
 */
public enum SpecialAttackSpellCosts {
	LOW(1),
	MEDIUM(3),
	HIGH(5),
	EXTREME(10);

	private float percentage;

	private SpecialAttackSpellCosts(int percentage) {
		this.percentage = percentage;
	}

	public float getPercentage() {
		return percentage;
	}
}
