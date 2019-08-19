package com.lilithsthrone.game.sex;

/**
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public enum SexControl {

	/**The character cannot do anything in sex.*/
	NONE(0),

	/**The character can only perform self actions in sex.*/
	SELF(1),

	/**The character can only use ongoing actions in sex. <b>Default for submissive NPCs in non-equal sex.</b>*/
	ONGOING_ONLY(2),

	/**The character can use ongoing actions in sex, as well as penetrations that are not related to taking virginity (can still start actions that take their own virginity though). <b>Default for the player when submissive in non-equal sex.</b>*/
	ONGOING_PLUS_LIMITED_PENETRATIONS(3),

	/**The character can perform any action in sex. Only this level of control can end sex.*/
	FULL(4);
	
	private int value;

	private SexControl(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	
}
