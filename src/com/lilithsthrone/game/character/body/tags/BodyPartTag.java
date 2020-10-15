package com.lilithsthrone.game.character.body.tags;

/**
 * @since 0.4
 * @version 0.4.0
 * @author Innoxia, Stadler76
 */
public enum BodyPartTag {
	
	// Misc.:
	
	/** Removes the 'Darkness' status effect when in an unlit area. Granted by nocturnal subspecies' eyes. (Should really only be given to eye types.) */
	NIGHT_VISION,
	/** Removes the 'Darkness' status effect when in an unlit area. Granted by bat ears. (Should really only be given to ear types.) */
	ECHO_LOCATION,
	
	
	// Arms:
	
	ARM_STANDARD,
	ARM_WINGS,
	ARM_WINGS_FEATHERED,
	ARM_WINGS_LEATHERY,
	
	
	// Face:
	
	/** Standard humanoid faces. This is the default and can be left out. */
	FACE_STANDARD,
	
	/** Muzzled faces */
	FACE_MUZZLE,
	
	/** Fangs instead of normal teeth */
	FACE_FANGS,
	
	/** Beaked faces */
	FACE_BEAK,
	
	/** Pointy, triangular shark teeth (Currently NYI, but planned for the (far?) future) */
	FACE_SHARK_TEETH,
	
	/** Being bald aka hairLength == 0 is not unnatural for that faceType. Use this tag for mammalian races. */
	FACE_NATURAL_BALDNESS_FURRY,
	/** Being bald aka hairLength == 0 is not unnatural for that faceType. Use this tag for avian races. */
	FACE_NATURAL_BALDNESS_AVIAN,
	/** Being bald aka hairLength == 0 is not unnatural for that faceType. Use this tag for reptilian or amphibious races. */
	FACE_NATURAL_BALDNESS_SCALY,
	

	// Tail (these are also used for tentacles):

	TAIL_PREHENSILE,
	
	TAIL_SUTABLE_FOR_PENETRATION,
	
	TAIL_SLEEP_HUGGING,
	
	
	/** Of a type which is covered in skin. Used for girth descriptors and descriptions. Examples would be demonic, alligator, rat.*/
	TAIL_TYPE_SKIN,
	
	/** Of a type which is covered in fur. Used for girth descriptors and descriptions. Examples would be cat, dog, fox.*/
	TAIL_TYPE_FUR,
	
	/** Of a type which is a small tuft covered in fur. Used for girth descriptors and descriptions. Examples would be rabbit, reindeer.*/
	TAIL_TYPE_TUFT,
	
	/** Of a type which is made entirely of hair. Used for girth descriptors and descriptions. Examples would be horse.*/
	TAIL_TYPE_HAIR,
	
	/** Of a type which is made of feathers. Used for girth descriptors and descriptions. Examples would be harpy.*/
	TAIL_TYPE_FEATHER,
	
	/** Of a type which doesn't fit into other categories. Used for girth descriptors and descriptions. Examples would be bat.*/
	TAIL_TYPE_GENERIC,
	
	
	/** The tail's diameter rapidly tapers off from the base. An example would be for demonic tails. */
	TAIL_TAPERING_EXPONENTIAL,
	
	/** The tail's diameter tapers off at a steady rate from the base. An example would be for alligator tails. */
	TAIL_TAPERING_LINEAR,
	
	/** The tail's diameter does not taper off from the base. Examples would be for cat, dog, rat tails. */
	TAIL_TAPERING_NONE
}
