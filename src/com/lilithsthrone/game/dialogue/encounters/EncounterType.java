package com.lilithsthrone.game.dialogue.encounters;

/**
 * @since 0.1.69.9
 * @version 0.3.8.3
 * @author Innoxia
 */
public enum EncounterType {

	SPECIAL_DOMINION_CULTIST,
	
	SLAVE_USES_YOU(true),
	
	// Dominion:
	
	DOMINION_STREET_FIND_HAPPINESS, // Kinariu
	DOMINION_STREET_RENTAL_MOMMY,
	DOMINION_STREET_PILL_HANDOUT,
	
	DOMINION_FIND_ITEM,
	DOMINION_FIND_CLOTHING,
	DOMINION_FIND_WEAPON,

	DOMINION_ALLEY_ENFORCERS,
	DOMINION_ALLEY_ATTACK(true),
	DOMINION_STORM_ATTACK(true),
	
	DOMINION_EXPRESS_CENTAUR,
	
	
	// Harpy nests:
	
	HARPY_NEST_ATTACK(true),
//	HARPY_NEST_ATTACK_STORM(true),
	HARPY_NEST_FIND_ITEM,
	
	
	// Submission:
	
	SUBMISSION_TUNNEL_ATTACK(true),
	SUBMISSION_FIND_ITEM,

	BAT_CAVERN_BAT_ATTACK(true),
	BAT_CAVERN_SLIME_ATTACK(true),
	BAT_CAVERN_FIND_ITEM,
        BAT_CAVERN_REBEL_BASE_DISCOVERED,
	
	VENGAR_CAPTIVE_SERVE,
	VENGAR_CAPTIVE_GROPED,
	VENGAR_CAPTIVE_VENGAR_FUCK,
	VENGAR_CAPTIVE_RAT_FUCK,
	VENGAR_CAPTIVE_ORAL_UNDER_TABLE,
	VENGAR_CAPTIVE_GROUP_SEX,
	
	VENGAR_CAPTIVE_CLEAN_ROOM,
	VENGAR_CAPTIVE_SHADOW_SILENCE_DOMINATE,
	VENGAR_CAPTIVE_ROOM_BARRED
	;

	EncounterType() {}
	EncounterType(boolean opportunistic) {
		this.opportunistic = opportunistic;
	}

	private boolean opportunistic = false;

	public boolean isOpportunistic() {
		return opportunistic;
	}
}
