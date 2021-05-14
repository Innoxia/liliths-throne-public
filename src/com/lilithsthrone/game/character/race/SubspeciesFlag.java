package com.lilithsthrone.game.character.race;

/**
 * @since 0.3.8.7
 * @version 0.3.8.7
 * @author Innoxia
 */
public enum SubspeciesFlag {
	
	/** Prevents this Subspecies from being displayed in spawn/furry preferences entirely. */
	HIDDEN_FROM_PREFERENCES,
	
	/** Prevents this Subspecies from having its furry stage preference changed in the content options. */
	DISABLE_FURRY_PREFERENCE,

	/** Prevents this Subspecies from having its spawn rate changed in the content options. */
	DISABLE_SPAWN_PREFERENCE;
	
	
	/**
	 * <b>Use this instead of valueOf()</b>
	 */
	public static SubspeciesFlag getSubspeciesFlagFromString(String value) {
		value = value.replaceAll("DISBALE_", "DISABLE_");
		
		return valueOf(value);
	}
}
