package com.lilithsthrone.game.dialogue.responses;

/**
 * @since 0.2.11
 * @version 0.3.4
 * @author Innoxia
 */
public enum ResponseTag {

	// All of the following tags are used exclusively by sex responses:
	
	/** All positioning actions will be disabled during the sex scene. */
	DISABLE_POSITIONING,

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
