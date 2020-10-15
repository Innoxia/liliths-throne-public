package com.lilithsthrone.world.places;

/**
 * @since 0.4
 * @version 0.4.0
 * @author Innoxia
 */
public enum Darkness {
	
	/** The darkness in the tile is based on the daylight hours. */
	DAYLIGHT,

	/** The tile is always dark (such as in caves). */
	ALWAYS_DARK,

	/** The tile is always light (typically via artificial light sources). */
	ALWAYS_LIGHT;
}
