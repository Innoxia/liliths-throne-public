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

	private float percentange;

	private SpecialAttackSpellCosts(int percentange) {
		this.percentange = percentange;
	}

	public float getPercentange() {
		return percentange;
	}
}
