package com.lilithsthrone.utils.colours;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public enum ColourTag {
	
	// These tags will add the Colour to relevant covering lists.
	// Some covering lists only have natural colours, while some have both natural and dye colours (such as SLIME and FEATHER).
	SKIN,
	SLIME_NATURAL,
	SLIME_DYE,
	FEATHER_NATURAL,
	FEATHER_DYE,
	FUR,
	SCALE,
	HORN,
	ANTLER,
	HAIR,
	
	// This tag will add the Colour to the 'all coverings' list:
	GENERIC_COVERING,
	
	// Enables the Colour to be used as a makeup colour
	MAKEUP,

	// Naturally-spawning iris colour, which is available to non-predatory races:
	IRIS_NATURAL,
	// Extra iris colour (only obtainable via transformation), which is available to non-predatory races:
	IRIS_DYE,

	// Naturally-spawning iris colour, which is available to predatory races:
	IRIS_PREDATOR_NATURAL,
	// Extra iris colour (only obtainable via transformation), which is available to predatory races:
	IRIS_PREDATOR_DYE,

	// Naturally-spawning pupil colour:
	PUPIL_NATURAL,
	// Extra pupil colour (only obtainable via transformation):
	PUPIL_DYE,

	// Naturally-spawning sclera colour:
	SCLERA_NATURAL,
	// Extra sclera colour (only obtainable via transformation):
	SCLERA_DYE;
	
}
