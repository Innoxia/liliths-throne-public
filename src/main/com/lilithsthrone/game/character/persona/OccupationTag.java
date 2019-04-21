package com.lilithsthrone.game.character.persona;

/**
 * @since 0.2.10
 * @version 0.2.10
 * @author Innoxia
 */
public enum OccupationTag {

	/**Jobs that can only be selected by the player in character creation.*/
	PLAYER_ONLY,
	
	/**Jobs that are either illegal or sleazy.*/
	LOWLIFE,

	/**Work hours are from 18:00-02:00.*/
	EVENING_SHIFT,

	/**Work hours are from 21:00-05:00.*/
	NIGHT_SHIFT,
	
	/**Prevents the Occupation from being selected as a starting job.*/
	HAS_PREREQUISITES;
}
