package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.99
 * @version 0.1.99
 * @author Innoxia
 */
public enum CoveringModifier {

	EYE("eye"),
	FLUID("fluid"),
	MAKEUP("makeup"),
	GLOSSY("glossy"),
	MATTE("matte"),
	SPARKLY("sparkly"),
	METALLIC("metallic"),

	BLAZING("blazing"),
	SHIMMERING("shimmering"),
	SWIRLING("swirling"),
	
	GOOEY("gooey") {
		@Override
		public String getName() {
			return UtilText.returnStringAtRandom(
					"gooey",
					"wet",
					"squishy");
		}
	},
	
	SHORT("short"),
	SILKEN("silken"),
	SMOOTH("smooth"),
	FLUFFY("fluffy"),
	SHAGGY("shaggy"),
	FURRY("fur-like"),
	COARSE("coarse");
	
	private String descriptor;

	private CoveringModifier(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getName() {
		return descriptor;
	}
}
