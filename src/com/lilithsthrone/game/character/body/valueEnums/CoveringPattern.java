package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.83
 * @version 0.2.0
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
	MARKED("marked"),
	FRECKLED("freckled"),
	
	OMBRE("ombre");
	
	
	public static List<CoveringPattern> allStandardCoveringPatterns = new ArrayList<>();
	public static List<CoveringPattern> allHairCoveringPatterns = new ArrayList<>();
	
	static {
		allStandardCoveringPatterns.add(NONE);
		allStandardCoveringPatterns.add(HIGHLIGHTS);
		allStandardCoveringPatterns.add(STRIPED);
		allStandardCoveringPatterns.add(SPOTTED);
		allStandardCoveringPatterns.add(MOTTLED);
		allStandardCoveringPatterns.add(MARKED);
		allStandardCoveringPatterns.add(FRECKLED);
		

		allHairCoveringPatterns.add(NONE);
		allHairCoveringPatterns.add(HIGHLIGHTS);
		allHairCoveringPatterns.add(STRIPED);
		allHairCoveringPatterns.add(SPOTTED);
		allHairCoveringPatterns.add(MOTTLED);
		allHairCoveringPatterns.add(MARKED);
		allHairCoveringPatterns.add(OMBRE);
	}
	
	
	private String name;
	
	private CoveringPattern(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
