package com.lilithsthrone.game.sex;

/**
 * Flags for use in NPC.calculateGenericSexEffects() to restrict outcomes.
 * 
 * @since 0.3.4
 * @version 0.3.8.8
 * @author Innoxia
 */
public enum GenericSexFlag {

	/** Do not generate any descriptions and only apply the effects. */
	NO_DESCRIPTION_NEEDED,

	/** Pass in to only return direct effect descriptions (such as cum ingested or virginity lost). */
	LIMITED_DESCRIPTION_NEEDED,

	/** Pass in to return a detailed description of what happened during the generic sex scene. This will have no effect if the sex partner is null. */
	EXTENDED_DESCRIPTION_NEEDED,

	/** Overrides pullout behaviour to make sure that any penis penetrations result in finishing inside the orifice. */
	FORCE_CREAMPIE,

	/** Overrides pullout behaviour to make sure that any penis penetrations pull out instead of finishing inside. */
	PREVENT_CREAMPIE,

	/** Prevents any of the involved characters from having their level drained. */
	PREVENT_LEVEL_DRAIN;
	
}
