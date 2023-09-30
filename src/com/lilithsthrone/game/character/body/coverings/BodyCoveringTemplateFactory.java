package com.lilithsthrone.game.character.body.coverings;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.8
 * @version 0.3.7
 * @author Pimgd, Innoxia
 */
public class BodyCoveringTemplateFactory {
	
	public static BodyCoveringTemplate createSkin(Map<CoveringPattern, Integer> coverPatterns, List<Colour> naturalPrimaryColors, List<Colour> naturalSecondaryColours, List<Colour> dyeColours) {
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
	
	public static BodyCoveringTemplate createTopSkin(Map<CoveringPattern, Integer> coverPatterns, List<Colour> skinColors) {
		return createSkin(coverPatterns, skinColors, skinColors, null);
	}
	
	public static BodyCoveringTemplate createBottomSkin(List<Colour> skinColors) {
		return createSkin(Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)), skinColors, null, PresetColour.allSkinColours);
	}
	
	public static BodyCoveringTemplate createSlime(CoveringPattern basePattern, Map<CoveringPattern, Integer> coverPatterns) {
		return new BodyCoveringTemplate("a layer of",
				false,
				"slime",
				"slime",
				Util.newArrayListOfValues(CoveringModifier.GOOEY),
				null,
				Util.newHashMapOfValues(new Value<>(basePattern, 1)),
				coverPatterns,
				PresetColour.naturalSlimeColours,
				PresetColour.dyeSlimeColours,
				PresetColour.naturalSlimeColours,
				PresetColour.dyeSlimeColours);
	}

	public static BodyCoveringTemplate createSilicone(CoveringPattern basePattern, Map<CoveringPattern, Integer> coverPatterns) {
		return createSilicone("a layer of", "silicone", basePattern, coverPatterns);
	}
	
	public static BodyCoveringTemplate createSilicone(String determiner, String name, CoveringPattern basePattern, Map<CoveringPattern, Integer> coverPatterns) {
		return new BodyCoveringTemplate(determiner,
				false,
				name,
				name,
				Util.newArrayListOfValues(CoveringModifier.SMOOTH),
				Util.newArrayListOfValues(
						CoveringModifier.GLOSSY,
						CoveringModifier.GLITTERING,
						CoveringModifier.MATTE,
						CoveringModifier.SPARKLY,
						CoveringModifier.METALLIC),
				Util.newHashMapOfValues(new Value<>(basePattern, 1)),
				coverPatterns,
				PresetColour.naturalSiliconeColours,
				PresetColour.dyeSiliconeColours,
				PresetColour.naturalSiliconeColours,
				PresetColour.dyeSiliconeColours);
	}
	
	public static BodyCoveringTemplate createFurSkin(List<CoveringModifier> modifiers, Map<CoveringPattern, Integer> patterns) {
		return createFur("a layer of", "fur", modifiers, null, patterns);
	}

	public static BodyCoveringTemplate createFurSkin(List<CoveringModifier> modifiers, List<CoveringModifier> extraModifiers, Map<CoveringPattern, Integer> patterns) {
		return createFur("a layer of", "fur", modifiers, extraModifiers, patterns);
	}
	
	private static BodyCoveringTemplate createFur(String determiner, String name, List<CoveringModifier> modifiers, List<CoveringModifier> extraModifiers, Map<CoveringPattern, Integer> patterns) {
		return new BodyCoveringTemplate(determiner,
				false,
				name,
				name,
				modifiers,
				extraModifiers,
				patterns,
				CoveringPattern.allStandardCoveringPatterns,
				PresetColour.naturalFurColours,
				PresetColour.allCoveringColours,
				PresetColour.naturalFurColours,
				PresetColour.allCoveringColours);
	}
	
	private static BodyCoveringTemplate createHair(String determiner, String name, List<CoveringModifier> modifiers, Map<CoveringPattern, Integer> patterns) {
		return new BodyCoveringTemplate(determiner,
				false,
				name,
				name,
				modifiers,
				null,
				patterns,
				CoveringPattern.allHairCoveringPatterns,
				PresetColour.naturalHairColours,
				PresetColour.allCoveringColours,
				PresetColour.naturalHairColours,
				PresetColour.allCoveringColours);
	}
	
	private static BodyCoveringTemplate createHairWithoutPatterns(String determiner, String name, CoveringModifier modifier) {
		return createHair(determiner, name, Util.newArrayListOfValues(modifier), Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)));
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
	
	public static BodyCoveringTemplate createElemental(String name, CoveringModifier modifier, CoveringPattern pattern, List<Colour> naturalColours) {
		return new BodyCoveringTemplate("",
				false,
				name,
				name,
				Util.newArrayListOfValues(modifier),
				null,
				Util.newHashMapOfValues(new Value<>(pattern, 1)),
				null,
				naturalColours,
				PresetColour.allCoveringColours,
				naturalColours,
				PresetColour.allCoveringColours);
	}
        
	public static BodyCoveringTemplate createElemental(String name, List<CoveringModifier> naturalModifiers, List<CoveringModifier> extraModifiers, Map<CoveringPattern, Integer> naturalPatterns, Map<CoveringPattern, Integer> extraPatterns, List<Colour> naturalColours) {
		return new BodyCoveringTemplate("",
				false,
				name,
				name,
				naturalModifiers,
				extraModifiers,
				naturalPatterns,
				extraPatterns,
				naturalColours,
				PresetColour.allCoveringColours,
				naturalColours,
				PresetColour.allCoveringColours);
	}
	
	public static BodyCoveringTemplate createOrificeSkin(CoveringPattern pattern) {
		return new BodyCoveringTemplate("a layer of",
				false,
				"skin",
				"skin",
				Util.newArrayListOfValues(CoveringModifier.SMOOTH),
				null,
				pattern==null
					?null
					:Util.newHashMapOfValues(new Value<>(pattern, 1)),
				null,
				PresetColour.allSkinColours,
				null,
				Util.newArrayListOfValues(PresetColour.ORIFICE_INTERIOR),
				PresetColour.allSkinColours);
	}
	
	public static BodyCoveringTemplate createPenisSkin() {
		return new BodyCoveringTemplate("a layer of",
				false,
				"skin",
				"skin",
				Util.newArrayListOfValues(CoveringModifier.SMOOTH),
				null,
				Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 10)),
				Util.newHashMapOfValues(
						new Value<>(CoveringPattern.MARKED, 1),
						new Value<>(CoveringPattern.MOTTLED, 1),
						new Value<>(CoveringPattern.SPOTTED, 1),
						new Value<>(CoveringPattern.STRIPED, 1)),
				PresetColour.allSkinColours,
				null,
				Util.newArrayListOfValues(PresetColour.ORIFICE_INTERIOR),
				PresetColour.allSkinColours);
	}
	
	public static BodyCoveringTemplate createEyeIrisesWithCustomColors(List<Colour> naturalIrisColors, List<Colour> dyeIrisColours, boolean heteroIsExtra) {
		Map<CoveringPattern, Integer> natural = Util.newHashMapOfValues(
				new Value<>(CoveringPattern.EYE_IRISES, 5),
				new Value<>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC, 1));
		
		Map<CoveringPattern, Integer> extra = null;
		
		if(heteroIsExtra) {
			natural = Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_IRISES, 1));
			extra = Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC, 1));
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
		return createEyeIrisesWithCustomColors(PresetColour.naturalIrisColours, PresetColour.dyeIrisColours, true);
	}
	
	public static BodyCoveringTemplate createEyeIrisesHeterochromiaNaturallyOccurring() {
		return createEyeIrisesWithCustomColors(PresetColour.naturalIrisColours, PresetColour.dyeIrisColours, false);
	}
}
