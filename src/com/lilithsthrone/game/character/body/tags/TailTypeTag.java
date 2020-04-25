package com.lilithsthrone.game.character.body.tags;

/**
 * @since 0.3.6.2
 * @version 0.3.7
 * @author Innoxia
 */
public enum TailTypeTag {
	
	PREHENSILE,
	
	SUTABLE_FOR_PENETRATION,
	
	SLEEP_HUGGING,
	
	
	// Types:
	
	/** Of a type which is covered in skin. Used for girth descriptors and descriptions. Examples would be demonic, alligator, rat.*/
	TYPE_SKIN,
	
	/** Of a type which is covered in fur. Used for girth descriptors and descriptions. Examples would be cat, dog, fox.*/
	TYPE_FUR,
	
	/** Of a type which is a small tuft covered in fur. Used for girth descriptors and descriptions. Examples would be rabbit, reindeer.*/
	TYPE_TUFT,
	
	/** Of a type which is made entirely of hair. Used for girth descriptors and descriptions. Examples would be horse.*/
	TYPE_HAIR,
	
	/** Of a type which is made of feathers. Used for girth descriptors and descriptions. Examples would be harpy.*/
	TYPE_FEATHER,
	
	/** Of a type which doesn't fit into other categories. Used for girth descriptors and descriptions. Examples would be bat.*/
	TYPE_GENERIC,
	
	
	// Tapering:
	
	/** The tail's diameter rapidly tapers off from the base. An example would be for demonic tails. */
	TAPERING_EXPONENTIAL,
	
	/** The tail's diameter tapers off at a steady rate from the base. An example would be for alligator tails. */
	TAPERING_LINEAR,
	
	/** The tail's diameter does not taper off from the base. Examples would be for cat, dog, rat tails. */
	TAPERING_NONE
	;
}
