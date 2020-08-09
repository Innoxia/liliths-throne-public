package com.lilithsthrone.game.character.body.tags;

/**
 * @since 0.3.8.9
 * @version 0.3.8.9
 * @author Innoxia
 */
public enum TentacleTypeTag {
	
	PREHENSILE,
	
	SUTABLE_FOR_PENETRATION,
	
	SLEEP_HUGGING,
	
	
	// Types:
	
	/** Of a type which is covered in fur. Used for girth descriptors and descriptions.*/
	TYPE_FUR,
	
	
	// Tapering:
	
	/** The tail's diameter rapidly tapers off from the base. An example would be for demonic tails. */
	TAPERING_EXPONENTIAL,
	
	/** The tail's diameter tapers off at a steady rate from the base. An example would be for alligator tails. */
	TAPERING_LINEAR,
	
	/** The tail's diameter does not taper off from the base. Examples would be for cat, dog, rat tails. */
	TAPERING_NONE
	;
}
