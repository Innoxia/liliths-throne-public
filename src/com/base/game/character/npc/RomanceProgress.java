package com.base.game.character.npc;

public enum RomanceProgress {

	ZERO_NONE(0, 0),
	ONE_LUKEWARM(1, 24),
	TWO_FRIENDLY(25, 49),
	THREE_LIKE(50, 74),
	FOUR_LOVE(74, 99),
	FIVE_DEVOTED(100, 100);

	private int progressMin, progressMax;

	private RomanceProgress(int progressMin, int progressMax) {
		this.progressMin = progressMin;
		this.progressMax = progressMax;
	}

	public RomanceProgress getProgressFromInt(int progress) {
		if (progress == 0)
			return ZERO_NONE;
		else if (progress <= ONE_LUKEWARM.getProgressMax())
			return ONE_LUKEWARM;
		else if (progress <= TWO_FRIENDLY.getProgressMax())
			return TWO_FRIENDLY;
		else if (progress <= THREE_LIKE.getProgressMax())
			return THREE_LIKE;
		else if (progress <= FOUR_LOVE.getProgressMax())
			return FOUR_LOVE;
		else
			return FIVE_DEVOTED;
	}

	public int getProgressMin() {
		return progressMin;
	}

	public int getProgressMax() {
		return progressMax;
	}
}
