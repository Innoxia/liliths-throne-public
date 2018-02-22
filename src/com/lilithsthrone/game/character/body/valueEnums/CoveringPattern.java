package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.83
 * @version 0.1.99
 * @author Innoxia
 */
public enum CoveringPattern {
	NONE("plain"),
	
	FLUID("fluid"),
	
	ORIFICE_ANUS("anus"),
	ORIFICE_VAGINA("vagina"),
	ORIFICE_NIPPLE("nipple"),
	ORIFICE_MOUTH("mouth"),

	EYE_IRISES("standard"),
	EYE_IRISES_HETEROCHROMATIC("heterochromatic"),
	EYE_PUPILS("standard"),
	EYE_PUPILS_HETEROCHROMATIC("heterochromatic"),

	HIGHLIGHTS("highlighted"),
	STRIPED("striped"),
	SPOTTED("spotted"),
	MOTTLED("mottled"),
	MARKED("marked");
	
	
	public static List<CoveringPattern> allStandardCoveringPatterns = new ArrayList<>();
	
	static {
		allStandardCoveringPatterns.add(NONE);
		allStandardCoveringPatterns.add(HIGHLIGHTS);
		allStandardCoveringPatterns.add(STRIPED);
		allStandardCoveringPatterns.add(SPOTTED);
		allStandardCoveringPatterns.add(MOTTLED);
		allStandardCoveringPatterns.add(MARKED);
	}
	
	
	private String name;
	
	private CoveringPattern(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
