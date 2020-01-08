package com.lilithsthrone.game.dialogue.places.submission.ratWarrens;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public enum VengarCompanionBehaviour {

	SERVING(false, 0.5f),
	
	FLIRTING(false, 0.25f),

	FUCKING(false, 0.1f),
	
	VENGAR(true, 0.1f),
	
	SILENCE(true, 0.025f),
	
	SHADOW(true, 0.025f),
	
	GROUP_SEX(true, 0.05f);
	
	private boolean dailyLimited;
	private float chanceOfOccurance;
	
	private VengarCompanionBehaviour(boolean dailyLimited, float chanceOfOccurance) {
		this.dailyLimited = dailyLimited;
		this.chanceOfOccurance = chanceOfOccurance;
	}

	public boolean isDailyLimited() {
		return dailyLimited;
	}

	public float getChanceOfOccurance() {
		return chanceOfOccurance;
	}
	
	
}
