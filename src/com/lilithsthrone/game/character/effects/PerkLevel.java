package com.lilithsthrone.game.character.effects;

public enum PerkLevel {
	LEVEL_ONE(1), LEVEL_FIVE(5), LEVEL_TEN(10), LEVEL_FIFTEEN(15);

	private int level;

	private PerkLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}
}
