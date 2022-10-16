package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.99
 * @version 0.4
 * @author Innoxia
 */
public enum CoveringModifier {

	EYE("eye", false),
	FLUID("fluid", false),
	MAKEUP("makeup", false),
	GLOSSY("glossy", false),
	MATTE("matte", false),
	SPARKLY("sparkly", false),
	METALLIC("metallic", false),

	BLAZING("blazing", false),
	SHIMMERING("shimmering", false),
	GLITTERING("glittering", false),
	SWIRLING("swirling", false),
	
	GOOEY("gooey", false) {
		@Override
		public String getName() {
			return UtilText.returnStringAtRandom(
					"gooey",
					"wet",
					"squishy");
		}
	},
	
	// Generic:
	SMOOTH("smooth", false),
	ROUGH("rough", false),
	
	//Skin:
	LEATHERY("leathery", false),
	
	// Fur/hair:
	SHORT("short", true),
	SILKEN("silken", true),
	FLUFFY("fluffy", true),
	SHAGGY("shaggy", true),
	FURRY("fur-like", true), // FURRY is only used for head hair, not body-covering fur.
	COARSE("coarse", true);
	
	private String descriptor;
	private boolean furryModifier;

	private CoveringModifier(String descriptor, boolean furryModifier) {
		this.descriptor = descriptor;
		this.furryModifier = furryModifier;
	}

	public String getName() {
		return descriptor;
	}
	
	/**
	 * @return true if this is a modifier which is typically assigned to furry or hairy coverings.
	 */
	public boolean isFurryModifier() {
		return furryModifier;
	}
}
