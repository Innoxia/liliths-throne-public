package com.lilithsthrone.game.character.body.types;

import java.util.Arrays;
import java.util.List;

import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

public class BodyCoveringTemplateFactory {
	
	public static BodyCoveringTemplate createSkin(List<CoveringPattern> coverPatterns, List<Colour> naturalPrimaryColors, List<Colour> naturalSecondaryColours, List<Colour> dyeColours) {
		return new BodyCoveringTemplate("a layer of",
				false,
				"skin",
				"skin",
				Util.newArrayListOfValues(CoveringModifier.SMOOTH),
				null,
				coverPatterns,
				CoveringPattern.allStandardCoveringPatterns,
				naturalPrimaryColors,
				dyeColours,
				naturalSecondaryColours,
				dyeColours);
	}
	
	public static BodyCoveringTemplate createTopSkin(List<CoveringPattern> coverPatterns, List<Colour> skinColors) {
		return createSkin(coverPatterns, skinColors, skinColors, null);
	}
	
	public static BodyCoveringTemplate createBottomSkin(List<Colour> skinColors) {
		return createSkin(Util.newArrayListOfValues(CoveringPattern.NONE), skinColors, null, Colour.allSkinColours);
	}
	
	public static BodyCoveringTemplate createSlime(CoveringPattern basePattern, List<CoveringPattern> coverPatterns) {
		return new BodyCoveringTemplate("a layer of",
				false,
				"slime",
				"slime",
				Util.newArrayListOfValues(CoveringModifier.GOOEY),
				null,
				Util.newArrayListOfValues(basePattern),
				coverPatterns,
				Colour.naturalSlimeColours,
				Colour.dyeSlimeColours,
				Colour.naturalSlimeColours,
				Colour.dyeSlimeColours);
	}
	
	public static BodyCoveringTemplate createFurSkinHair(List<CoveringModifier> modifiers, List<CoveringPattern> patterns) {
		return createHair("a layer of", "fur", modifiers, patterns);
	}
	
	private static BodyCoveringTemplate createHair(String determiner, String name, List<CoveringModifier> modifiers, List<CoveringPattern> patterns) {
		return new BodyCoveringTemplate(determiner,
				false,
				name,
				name,
				modifiers,
				null,
				patterns,
				CoveringPattern.allHairCoveringPatterns,
				Colour.naturalHairColours,
				Colour.dyeHairColours,
				Colour.naturalHairColours,
				Colour.dyeHairColours);
	}
	
	private static BodyCoveringTemplate createHairWithoutPatterns(String determiner, String name, CoveringModifier modifier) {
		return createHair(determiner, name, Util.newArrayListOfValues(modifier), Util.newArrayListOfValues(CoveringPattern.NONE));
	}
	
	public static BodyCoveringTemplate createHeadHair(CoveringModifier modifier) {
		return createHairWithoutPatterns("a head of", "hair", modifier);
	}
	
	public static BodyCoveringTemplate createFurHeadHair(CoveringModifier modifier) {
		return createHairWithoutPatterns("a layer of", "hair", modifier);
	}
	
	public static BodyCoveringTemplate createBodyHair(CoveringModifier modifier) {
		return createHairWithoutPatterns("a layer of", "hair", modifier);
	}
	
	public static BodyCoveringTemplate createElemental(String name, CoveringModifier modifier, Colour... naturalHairColours) {
		return new BodyCoveringTemplate("",
				false,
				name,
				name,
				Util.newArrayListOfValues(modifier),
				null,
				Util.newArrayListOfValues(CoveringPattern.NONE),
				null,
				Arrays.asList(naturalHairColours),
				null,
				null,
				null);
	}
	
	public static BodyCoveringTemplate createOrificeSkin(CoveringPattern pattern) {
		return new BodyCoveringTemplate("a layer of",
				false,
				"skin",
				"skin",
				Util.newArrayListOfValues(CoveringModifier.SMOOTH),
				null,
				pattern == null ? null : Util.newArrayListOfValues(pattern),
				null,
				Colour.allSkinColours,
				null,
				Util.newArrayListOfValues(Colour.ORIFICE_INTERIOR),
				Colour.allSkinColours);
	}
	
	private static BodyCoveringTemplate createEyeIrisesWithCustomColors(List<Colour> naturalIrisColors, List<Colour> dyeIrisColours, boolean heteroIsExtra) {
		List<CoveringPattern> natural = Util.newArrayListOfValues(CoveringPattern.EYE_IRISES, CoveringPattern.EYE_IRISES_HETEROCHROMATIC);
		List<CoveringPattern> extra = null;
		if (heteroIsExtra) {
			natural = Util.newArrayListOfValues(CoveringPattern.EYE_IRISES);
			extra = Util.newArrayListOfValues(CoveringPattern.EYE_IRISES_HETEROCHROMATIC);
		}
		return new BodyCoveringTemplate("a pair of",
				true,
				"eyes",
				"eye",
				Util.newArrayListOfValues(CoveringModifier.EYE),
				null,
				natural,
				extra,
				naturalIrisColors,
				dyeIrisColours,
				naturalIrisColors,
				dyeIrisColours);
	}
	
	public static BodyCoveringTemplate createEyeIrisesWithCustomColors(List<Colour> naturalIrisColours, List<Colour> dyeIrisColours) {
		return createEyeIrisesWithCustomColors(naturalIrisColours, dyeIrisColours, true);
	}
	
	public static BodyCoveringTemplate createEyeIrises() {
		return createEyeIrisesWithCustomColors(Colour.naturalIrisColours, Colour.dyeIrisColours, true);
	}
	
	public static BodyCoveringTemplate createEyeIrisesHeterochromiaNaturallyOccuring() {
		return createEyeIrisesWithCustomColors(Colour.naturalIrisColours, Colour.dyeIrisColours, false);
	}
}
