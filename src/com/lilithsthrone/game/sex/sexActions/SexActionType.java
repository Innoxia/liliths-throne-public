package com.lilithsthrone.game.sex.sexActions;

/**
 * @since 0.1.65
 * @version 0.2.8
 * @author Innoxia
 */
public enum SexActionType {

	/**Standard ongoing action. Requires all SexAreaInterfaces to be occupied with each other.*/
	ONGOING,

	/**Standard non-penetrative action. Requires all SexAreaInterfaces to be exposed.*/
	REQUIRES_EXPOSED,

	/**Standard non-penetrative action. Requires all SexAreaInterfaces to be free (not involved in an ongoing action).*/
	REQUIRES_NO_PENETRATION,

	/**Non-penetrative action which also requires all SexAreaInterfaces to be exposed.*/
	REQUIRES_NO_PENETRATION_AND_EXPOSED,

	/**Action which starts an ongoing penetration or action (such as performing a blowjob, or fingering someone).*/
	START_ONGOING,

	/**Action which stops the ongoing process of all SexAreaInterfaces.*/
	STOP_ONGOING,

	/**Positioning action.*/
	POSITIONING,

	/**The action taken to prepare for partner's orgasm.*/
	PREPARE_FOR_PARTNER_ORGASM,

	/**Orgasm.*/
	ORGASM,

	/**Orgasm that doesn't reset arousal.*/
	ORGASM_NO_AROUSAL_RESET,

	/**Special miscellaneous actions such as stopping sex.*/
	SPECIAL;

	/**
	 * @return True if this action <b>could</b> be a penetrating one.
	 *  If the ongoing action involves no penetration, such as penis being rubbed over pussy without being inserted (there are none at the time of this method being created in v0.3.0.6), then this will not be accurate.
	 */
	public boolean isPenetratingOption() {
		return this == START_ONGOING || this == ONGOING;
	}

	public boolean isOrgasmOption() {
		return this == ORGASM || this == ORGASM_NO_AROUSAL_RESET;
	}
}
