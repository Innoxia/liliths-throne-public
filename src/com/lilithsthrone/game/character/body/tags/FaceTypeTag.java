package com.lilithsthrone.game.character.body.tags;

/**
 * @since 0.3.6.1
 * @version 0.3.6.1
 * @author Stadler76
 */
public enum FaceTypeTag {
	/** Standard aka human-ish faces. This is the default and can be left out. */
	STANDARD,
	/** Muzzled faces */
	MUZZLE,
	/** Fangs instead of normal teeth */
	FANGS,
	/** Beaked faces */
	BEAK,
	/** Pointy, trianguler shark teeth (Currently NYI, but planned for the (far?) future) */
	SHARK_TEETH,
	/** Being bald aka hairLength == 0 is not unnatural for that faceType */
	NATURAL_BALDNESS,
}
