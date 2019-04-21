package com.lilithsthrone.game.dialogue.encounters;

/**
 * @since 0.1.69.9
 * @version 0.3.2
 * @author Innoxia
 */
public enum EncounterType {

	SPECIAL_DOMINION_CULTIST,
	
	SLAVE_USES_YOU(true),
	
	DOMINION_STREET_FIND_HAPPINESS, // Kinariu
	DOMINION_STREET_RENTAL_MOMMY,
	DOMINION_STREET_VIXEN_VIRILITY_HANDOUT,
	
	DOMINION_FIND_ITEM,
	DOMINION_FIND_CLOTHING,
	DOMINION_FIND_WEAPON,

	DOMINION_ALLEY_ATTACK(true),
	DOMINION_STORM_ATTACK(true),
	
	
	HARPY_NEST_ATTACK(true),
	HARPY_NEST_ATTACK_STORM(true),
	HARPY_NEST_FIND_ITEM,
	
	SUBMISSION_TUNNEL_ATTACK(true),
	SUBMISSION_FIND_ITEM,

	BAT_CAVERN_BAT_ATTACK(true),
	BAT_CAVERN_SLIME_ATTACK(true),
	BAT_CAVERN_FIND_ITEM
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
