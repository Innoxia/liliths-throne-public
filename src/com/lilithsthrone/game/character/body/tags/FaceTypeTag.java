package com.lilithsthrone.game.character.body.tags;

/**
 * @since 0.3.6.1
 * @version 0.3.6.5
 * @author Stadler76
 */
public enum FaceTypeTag {
	
	/** Standard humanoid faces. This is the default and can be left out. */
	STANDARD,
	
	/** Muzzled faces */
	MUZZLE,
	
	/** Fangs instead of normal teeth */
	FANGS,
	
	/** Beaked faces */
	BEAK,
	
	/** Pointy, triangular shark teeth (Currently NYI, but planned for the (far?) future) */
	SHARK_TEETH,
	
	/** Being bald aka hairLength == 0 is not unnatural for that faceType. Use this tag for mammalian races. */
	NATURAL_BALDNESS_FURRY,
	/** Being bald aka hairLength == 0 is not unnatural for that faceType. Use this tag for avian races. */
	NATURAL_BALDNESS_AVIAN,
	/** Being bald aka hairLength == 0 is not unnatural for that faceType. Use this tag for reptilian or amphibious races. */
	NATURAL_BALDNESS_SCALY;
}
