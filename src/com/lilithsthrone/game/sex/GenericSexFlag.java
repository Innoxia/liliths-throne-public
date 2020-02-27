package com.lilithsthrone.game.sex;

/**
 * Flags for use in NPC.calculateGenericSexEffects() to restrict outcomes.
 * 
 * @since 0.3.4
 * @version 0.3.6.2
 * @author Innoxia
 */
public enum GenericSexFlag {
	
	NO_DESCRIPTION_NEEDED,

	/** Pass in to only return direct effect descriptions (such as cum ingested or virginity lost). */
	LIMITED_DESCRIPTION_NEEDED,
	
	EXTENDED_DESCRIPTION_NEEDED,
	
	PREVENT_CREAMPIE,
	
	PREVENT_LEVEL_DRAIN;
	
}
