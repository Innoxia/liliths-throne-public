package com.lilithsthrone.game.combat;

/**
 * @since 0.1.0
 * @version 0.1.64
 * @author Innoxia
 */
public enum DamageLevel {
	NONE("none",
			0),
	POOR("poor",
			0.75f),
	NORMAL("average",
			1f),
	HIGH("good",
			1.25f),
	EXTREME("excellent",
			1.5f),
	ABSURD("absurd",
			1000f);

	private String name;
	private float damageModifier;

	private DamageLevel(String name, float damageModifier) {
		this.name = name;
		this.damageModifier = damageModifier;
	}

	public String getName() {
		return name;
	}

	public float getDamageModifier() {
		return damageModifier;
	}
}
