package com.lilithsthrone.game.dialogue.responses;

/**
 * As of 0.4.1, all of the following tags are used exclusively by sex responses.
 * 
 * @since 0.2.11
 * @version 0.4.1
 * @author Innoxia
 */
public enum ResponseTag {
	
	DISABLE_POSITIONING, // Disables positioning actions
	
	// Positioning and paces:
	
	/** These four will affect what the starting sex position is. */
	PREFER_ORAL,
	PREFER_MISSIONARY,
	PREFER_DOGGY,
	PREFER_COW_GIRL,

	/** 
	 * These four will affect the player's starting sex pace.
	 * The dominant and submissive 'NORMAL' values are missing as those are the defaults if none of these tags are used.
	 */
	START_PACE_PLAYER_SUB_RESISTING,
	START_PACE_PLAYER_SUB_EAGER,
	START_PACE_PLAYER_DOM_GENTLE,
	START_PACE_PLAYER_DOM_ROUGH;
}
