package com.lilithsthrone.game.character.body.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.utils.Util;

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
	/** Removes the 'Darkness' status effect when in an unlit area. Granted by snake snoots. */
	THERMAL_VISION,
	/** For races which have some kind of special flight ability not granted by arm-wings or real wings, use this tag on the relevant body part. */
	ALLOWS_FLIGHT,
	
	// Torso:
	
	/** Torso types with this tag are considered to have a dorsal fin. */
	TORSO_DORSAL_FIN,
	
	
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
	
	/** When the CMSpecialAttack 'BITE' is used, characters with venomous teeth apply the 'poisoned' effect. */
	FACE_VENOMOUS_TEETH,

	/** When the CMSpecialAttack 'BITE' is used, characters with venomous teeth apply the 'lust poisoned' effect. */
	FACE_VENOMOUS_TEETH_LUST,
	
	/** Being bald aka hairLength == 0 is not unnatural for that faceType. Use this tag for mammalian races. */
	FACE_NATURAL_BALDNESS_FURRY,
	/** Being bald aka hairLength == 0 is not unnatural for that faceType. Use this tag for avian races. */
	FACE_NATURAL_BALDNESS_AVIAN,
	/** Being bald aka hairLength == 0 is not unnatural for that faceType. Use this tag for reptilian or amphibious races. */
	FACE_NATURAL_BALDNESS_SCALY,
	/** Being bald aka hairLength == 0 is not unnatural for that faceType. Use this tag for races which have human-like skin. */
	FACE_NATURAL_BALDNESS_SKIN,
	
	
	// Ears:

	/** Tags the ears as being large enough to be able to be grabbed and used as handles during sex scenes. */
	EAR_HANDLES_IN_SEX,
	
	
	// Hair:

	/** Tags the hair as being suitable for being grabbed and used as a handle during sex scenes.
	 * Should probably be added to every hair type unless it would be impossible to comfortably grab (e.g. if the hair type was sharp, pointy scales or something). */
	HAIR_HANDLES_IN_SEX,
	
	/** Tags the hair as being naturally styled into a mane. */
	HAIR_NATURAL_MANE,
	
	/** Tags the hair type as always being present on furry and scaly heads, even if the player has 'furry hair' turned off. (USeful for races such as hyenas which have a hair-type mane.)*/
	HAIR_IGNORE_PLAYER_SETTINGS,
	
	
	// Tail (these are also used for tentacles):
	/** Prehensile tails are those which can be manipulated into being used like an extra limb (e.g. monkey tails) */
	TAIL_PREHENSILE,
	
	/** This tail is never suitable for penetration, even if the player has furry tail penetration turned on (e.g. rabbit or harpy tails) */
	TAIL_NEVER_SUITABLE_FOR_PENETRATION,
	
	/** Whether this tail is suitable for penetrating orifices (e.g demon tails). Note that furry tails should not be marked with this, as that relies on the player's furry penetration settings and is determined from whether the tail is prehensile. */
	TAIL_SUITABLE_FOR_PENETRATION,
	
	/** Whether this tail can be curled up and hugged when falling asleep. */
	TAIL_SLEEP_HUGGING,
	
	/** Whether this tail unlocks the 'tail swipe' special attack (e.g. alligator or dragon tails) */
	TAIL_ATTACK,

	/** Whether this tail unlocks poison sting attacks. (Not yet implemented) */
	TAIL_VENOMOUS_STING,

	/** Whether this tail unlocks lust-applying sting attacks. (Not yet implemented) */
	TAIL_VENOMOUS_STING_LUST,

	/** Whether this tail can act as an ovipositor. */
	TAIL_OVIPOSITOR,
	
	// Tail types:
	/** Of a type which is covered in skin. Used for girth descriptors and descriptions. Examples would be demonic, rat.*/
	TAIL_TYPE_SKIN,

	/** Of a type which is covered in scales. Used for girth descriptors and descriptions. Examples would be alligator.*/
	TAIL_TYPE_SCALES,
	
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
	
	// Tail tapering (only use one of these at a time!):
	/** The tail's diameter rapidly tapers off from the base. An example would be for demonic tails. */
	TAIL_TAPERING_EXPONENTIAL,
	
	/** The tail's diameter tapers off at a steady rate from the base. An example would be for alligator tails. */
	TAIL_TAPERING_LINEAR,

	/** The tail's diameter expands from the base and then tapers off, creating a bulbous, oval shape. Examples would be for spider-morph abdomen-tails. */
	TAIL_TAPERING_BULBOUS,
	
	/** The tail's diameter does not taper off from the base. Examples would be for cat, dog, rat tails. */
	TAIL_TAPERING_NONE;
	
	
	
	public static List<BodyPartTag> allBodyPartTags = new ArrayList<>();
	public static Map<BodyPartTag, String> bodypartTagToIdMap = new HashMap<>();
	public static Map<String, BodyPartTag> idToBodypartTagMap = new HashMap<>();
	
	static {
		for(BodyPartTag tag : BodyPartTag.values()) {
			allBodyPartTags.add(tag);
			bodypartTagToIdMap.put(tag, tag.toString());
			idToBodypartTagMap.put(tag.toString(), tag);
		}
	}
	
	public static List<BodyPartTag> getAllBodyPartTags() {
		return allBodyPartTags;
	}

	public static BodyPartTag getBodyPartTagFromId(String id) {
		id = Util.getClosestStringMatch(id, idToBodypartTagMap.keySet());
		return idToBodypartTagMap.get(id);
	}
	
	public static String getIdFromBodyPartTag(BodyPartTag bpt) {
		return bodypartTagToIdMap.get(bpt);
	}
}
