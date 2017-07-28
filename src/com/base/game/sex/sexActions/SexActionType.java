package com.base.game.sex.sexActions;

/**
 * @since 0.1.65
 * @version 0.1.79
 * @author Innoxia
 */
public enum SexActionType {
	
	// Player:

	/**Standard player action. Requires the associated PenetrationType and OrificeType to be occupied with each other.*/
	PLAYER(true),
	
	/**Standard player action. Requires the associated PenetrationType and OrificeTypes to be free.*/
	PLAYER_REQUIRES_NO_PENETRATION(true),
	
	/**Standard player action. Requires the associated PenetrationType and OrificeTypes to be free.*/
	PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED(true),
	
	/**Player action. Applies penetration.*/
	PLAYER_PENETRATION(true),
	
	/**Player action. Stops penetration.*/
	PLAYER_STOP_PENETRATION(true),
	
	/**Player action. Changes position.*/
	PLAYER_POSITIONING(true),

	/**Player action. Orgasm.*/
	PLAYER_ORGASM(true),
	
	/**Player action. Orgasm that doesn't reset arousal.*/
	PLAYER_ORGASM_NO_AROUSAL_RESET(true),
	
	/**Player action. Special miscellaneous actions such as stopping sex.*/
	PLAYER_SPECIAL(true),

	
	// Mutual:
	
	/**Mutual orgasms. Player is always in control of these.*/
	MUTUAL_ORGASM(true),
	
	
	// Partner:

	/**Standard partner action. Requires the associated PenetrationType and OrificeType to be occupied with each other.*/
	PARTNER(false),
	
	/**Standard partner action. Requires the associated PenetrationType and OrificeTypes to be free.*/
	PARTNER_REQUIRES_NO_PENETRATION(false),
	
	/**Standard partner action. Requires the associated PenetrationType and OrificeTypes to be free.*/
	PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED(false),

	/**Partner action. Applies penetration.*/
	PARTNER_PENETRATION(false),
	
	/**Partner action. Stops penetration.*/
	PARTNER_STOP_PENETRATION(false),
	
	/**Partner action. Changes position.*/
	PARTNER_POSITIONING(false),
	
	/**Partner action. Orgasm.*/
	PARTNER_ORGASM(false),
	
	/**Partner action. Orgasm that doesn't reset arousal.*/
	PARTNER_ORGASM_NO_AROUSAL_RESET(false),
	
	/**Partner action. Special miscellaneous actions such as stopping sex.*/
	PARTNER_SPECIAL(false);
	
	
	private boolean playerAction;
	
	private SexActionType(boolean playerAction){
		this.playerAction=playerAction;
	}

	public boolean isPlayerAction() {
		return playerAction;
	}
	
	public boolean isOrgasmOption() {
		return this == PLAYER_ORGASM
				|| this == PLAYER_ORGASM_NO_AROUSAL_RESET

				|| this == PARTNER_ORGASM
				|| this == PARTNER_ORGASM_NO_AROUSAL_RESET
				
				|| this == MUTUAL_ORGASM;
	}
}
