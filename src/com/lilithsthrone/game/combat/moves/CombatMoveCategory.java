package com.lilithsthrone.game.combat.moves;

/**
 * Category of CombatType for use in organising CombatMoves in menus.
 * 
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public enum CombatMoveCategory {
	
	/** Moves available to everyone by default. */
	BASIC,

	/** Spells. */
	SPELL,
	
	/** Any move derived from body parts, weapons, fetishes, etc. */
	SPECIAL;
}
