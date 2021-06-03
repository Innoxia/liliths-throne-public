package com.lilithsthrone.game.sex.managers;

/**
 * @since 0.3.4
 * @version 0.4.1
 * @author Innoxia
 */
public enum OrgasmBehaviour {
	
	/** The character with this behaviour will ignore requests and treat all 'pull out' orgasm actions as having a SexActionPriority of UNIQUE_MAX, which should be enough to force them into pulling out. */
	PULL_OUT,
	
	/** The character with this behaviour will handle requests as normal (they might ignore them based on their affection, fetishes, and dominance), so either pull out or creampie actions could be used. */
	DEFAULT,

	/** The character with this behaviour will ignore requests and treat all 'creampie' orgasm actions as having a SexActionPriority of UNIQUE_MAX, which should be enough to force them into giving their partner a creampie. */
	CREAMPIE;
}
