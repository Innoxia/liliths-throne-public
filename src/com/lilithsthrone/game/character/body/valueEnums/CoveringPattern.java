package com.lilithsthrone.game.character.body.valueEnums;

import java.util.HashMap;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public enum CoveringPattern {
	NONE(false, "plain"),
	
	FLUID(false, "fluid"),
	
	ORIFICE_ANUS(true, "anus"),
	ORIFICE_VAGINA(true, "vagina"),
	ORIFICE_MOUTH(true, "mouth"),
	ORIFICE_SPINNERET(true, "spinneret"),
	ORIFICE_NIPPLE(true, "nipple") {
		@Override
		public boolean isNaturalSecondColour(GameCharacter owner) {
			return owner!=null && owner.getNippleCapacity()!=Capacity.ZERO_IMPENETRABLE;
		}
	},
	ORIFICE_NIPPLE_CROTCH(true, "teat") {
		@Override
		public boolean isNaturalSecondColour(GameCharacter owner) {
			return  owner!=null && owner.getNippleCrotchCapacity()!=Capacity.ZERO_IMPENETRABLE;
		}
	},

	EYE_IRISES(false, "standard"),
	EYE_IRISES_HETEROCHROMATIC(true, "heterochromatic"),
	EYE_PUPILS(false, "standard"),
	EYE_PUPILS_HETEROCHROMATIC(true, "heterochromatic"),
	EYE_SCLERA(false, "standard"),
	EYE_SCLERA_HETEROCHROMATIC(true, "heterochromatic"),

	HIGHLIGHTS(true, "highlighted"),
	STRIPED(true, "striped"),
	SPOTTED(true, "spotted"),
	MOTTLED(true, "mottled"),
	MARKED(true, "marked"),
	FRECKLED(true, "freckled"),
	FRECKLED_FACE(false, "freckled (face)"),
	
	OMBRE(true, "ombre");
	
	
	public static Map<CoveringPattern, Integer> allStandardCoveringPatterns = new HashMap<>();
	public static Map<CoveringPattern, Integer> allHairCoveringPatterns = new HashMap<>();
	public static Map<CoveringPattern, Integer> allScalesCoveringPatterns = new HashMap<>();
	public static Map<CoveringPattern, Integer> allSlimeCoveringPatterns = new HashMap<>();
	public static Map<CoveringPattern, Integer> allSiliconeCoveringPatterns = new HashMap<>();
	
	
	static {
		allStandardCoveringPatterns.put(NONE, 1);
		allStandardCoveringPatterns.put(HIGHLIGHTS, 1);
		allStandardCoveringPatterns.put(STRIPED, 1);
		allStandardCoveringPatterns.put(SPOTTED, 1);
		allStandardCoveringPatterns.put(MOTTLED, 1);
		allStandardCoveringPatterns.put(MARKED, 1);
		allStandardCoveringPatterns.put(FRECKLED, 1);
		allStandardCoveringPatterns.put(FRECKLED_FACE, 1);
		
		allHairCoveringPatterns.put(NONE, 1);
		allHairCoveringPatterns.put(HIGHLIGHTS, 1);
		allHairCoveringPatterns.put(STRIPED, 1);
		allHairCoveringPatterns.put(SPOTTED, 1);
		allHairCoveringPatterns.put(MOTTLED, 1);
		allHairCoveringPatterns.put(MARKED, 1);
		allHairCoveringPatterns.put(OMBRE, 1);

		allScalesCoveringPatterns.put(NONE, 1);
		allScalesCoveringPatterns.put(HIGHLIGHTS, 1);
		allScalesCoveringPatterns.put(STRIPED, 1);
		allScalesCoveringPatterns.put(SPOTTED, 1);
		allScalesCoveringPatterns.put(MOTTLED, 1);
		allScalesCoveringPatterns.put(MARKED, 1);
		
		allSlimeCoveringPatterns.put(NONE, 1);
		allSlimeCoveringPatterns.put(HIGHLIGHTS, 1);
		allSlimeCoveringPatterns.put(STRIPED, 1);
		allSlimeCoveringPatterns.put(SPOTTED, 1);
		allSlimeCoveringPatterns.put(MOTTLED, 1);
		allSlimeCoveringPatterns.put(MARKED, 1);
		allSlimeCoveringPatterns.put(FRECKLED, 1);
		allSlimeCoveringPatterns.put(FRECKLED_FACE, 1);
		allSlimeCoveringPatterns.put(OMBRE, 1);

		allSiliconeCoveringPatterns.put(NONE, 1);
		allSiliconeCoveringPatterns.put(HIGHLIGHTS, 1);
		allSiliconeCoveringPatterns.put(STRIPED, 1);
		allSiliconeCoveringPatterns.put(SPOTTED, 1);
		allSiliconeCoveringPatterns.put(MOTTLED, 1);
		allSiliconeCoveringPatterns.put(MARKED, 1);
		allSiliconeCoveringPatterns.put(FRECKLED, 1);
		allSiliconeCoveringPatterns.put(FRECKLED_FACE, 1);
		allSiliconeCoveringPatterns.put(OMBRE, 1);
	}
	
	
	private String name;
	private boolean naturalSecondColour;
	
	private CoveringPattern(boolean naturalSecondColour, String name) {
		this.naturalSecondColour = naturalSecondColour;
		this.name = name;
	}

	/**
	 * @return true if this CoveringPattern has a secondary colour by default.
	 */
	public boolean isNaturalSecondColour(GameCharacter owner) {
		return naturalSecondColour;
	}

	public String getName() {
		return name;
	}
}
