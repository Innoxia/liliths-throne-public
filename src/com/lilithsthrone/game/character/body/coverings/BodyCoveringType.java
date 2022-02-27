package com.lilithsthrone.game.character.body.coverings;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.ColourListPresets;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4.0
 * @author Innoxia
 */
public class BodyCoveringType {
	
	public static AbstractBodyCoveringType HUMAN = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_SKIN,
			BodyCoveringTemplateFactory.createSkin(
			Util.newHashMapOfValues(
					new Value<>(CoveringPattern.NONE, 30),
					new Value<>(CoveringPattern.FRECKLED_FACE, 2),
					new Value<>(CoveringPattern.FRECKLED, 1)),
			PresetColour.getHumanSkinColours(),
			PresetColour.getHumanSkinColours(),
			PresetColour.allSkinColours)) {
	};
	
	public static AbstractBodyCoveringType FOX_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_FUR,
			"a layer of",
			false,
			"fur",
			"fur",
			Util.newArrayListOfValues(
					CoveringModifier.FLUFFY,
					CoveringModifier.SMOOTH),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.MARKED, 1)), // Foxes always have white markings on their undersides
			CoveringPattern.allHairCoveringPatterns,
			Util.newArrayListOfValues(
					PresetColour.COVERING_ORANGE,
					PresetColour.COVERING_TAN,
					PresetColour.COVERING_GREY,
					PresetColour.COVERING_BLACK),
			PresetColour.allCoveringColours,
			Util.newArrayListOfValues(
					PresetColour.COVERING_WHITE), // Fox markings are always naturally white
			PresetColour.allCoveringColours) {
	};
	
	public static AbstractBodyCoveringType ANGEL = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_SKIN,
			BodyCoveringTemplateFactory.createTopSkin(
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			PresetColour.getHumanSkinColours())) {
	};

	public static AbstractBodyCoveringType ANGEL_FEATHER = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_FEATHER,
			"a layer of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			CoveringPattern.allHairCoveringPatterns,
			Util.newArrayListOfValues(PresetColour.COVERING_WHITE),
			Util.mergeLists(PresetColour.dyeFeatherColours, PresetColour.naturalFeatherColours),
			Util.newArrayListOfValues(PresetColour.COVERING_WHITE),
			Util.mergeLists(PresetColour.dyeFeatherColours, PresetColour.naturalFeatherColours)) {
	};
	
	public static AbstractBodyCoveringType DEMON_COMMON = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_SKIN,
			BodyCoveringTemplateFactory.createTopSkin(
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			PresetColour.demonSkinColours)) {
	};

	public static AbstractBodyCoveringType DEMON_FEATHER = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_FEATHER,
			"a layer of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			CoveringPattern.allHairCoveringPatterns,
			Util.newArrayListOfValues(PresetColour.COVERING_BLACK),
			Util.mergeLists(PresetColour.dyeFeatherColours, PresetColour.naturalFeatherColours),
			Util.newArrayListOfValues(PresetColour.COVERING_BLACK),
			Util.mergeLists(PresetColour.dyeFeatherColours, PresetColour.naturalFeatherColours)) {
	};

//	public static AbstractBodyCoveringType DEMON_HORSE_HAIR = new AbstractBodyCoveringType(
//			BodyCoveringCategory.MAIN_HAIR,
//			"a layer of",
//			false,
//			"hair",
//			"hair",
//			Util.newArrayListOfValues(CoveringModifier.SHORT),
//			null,
//			Util.newHashMapOfValues(
//					new Value<>(CoveringPattern.NONE, 30),
//					new Value<>(CoveringPattern.MOTTLED, 5),
//					new Value<>(CoveringPattern.SPOTTED, 5),
//					new Value<>(CoveringPattern.MARKED, 5)),
//			CoveringPattern.allHairCoveringPatterns,
//			PresetColour.naturalFurColours,
//			PresetColour.allCoveringColours,
//			PresetColour.naturalFurColours,
//			PresetColour.allCoveringColours) {
//	};
	
	public static AbstractBodyCoveringType BAT_SKIN = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_SKIN,
			BodyCoveringTemplateFactory.createBottomSkin(PresetColour.getHumanSkinColours())) {
	};
	
	public static AbstractBodyCoveringType BAT_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_FUR,
			BodyCoveringTemplateFactory.createFurSkin(
			Util.newArrayListOfValues(CoveringModifier.SHORT),
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)))) {
	};
	
	public static AbstractBodyCoveringType CANINE_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_FUR,
			BodyCoveringTemplateFactory.createFurSkin(
			Util.newArrayListOfValues(
					CoveringModifier.FLUFFY,
					CoveringModifier.SHORT,
					CoveringModifier.SHAGGY),
			Util.newHashMapOfValues(
					new Value<>(CoveringPattern.NONE, 30),
					new Value<>(CoveringPattern.SPOTTED, 5),
					new Value<>(CoveringPattern.MARKED, 5)))) {
	};
	
	public static AbstractBodyCoveringType LYCAN_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_FUR,
			BodyCoveringTemplateFactory.createFurSkin(Util.newArrayListOfValues(CoveringModifier.SHAGGY), null)) {
	};

	public static AbstractBodyCoveringType FELINE_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_FUR,
			BodyCoveringTemplateFactory.createFurSkin(
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH,
					CoveringModifier.SHORT,
					CoveringModifier.FLUFFY),
			Util.newHashMapOfValues(
					new Value<>(CoveringPattern.NONE, 50),
					new Value<>(CoveringPattern.MOTTLED, 5),
					new Value<>(CoveringPattern.SPOTTED, 5),
					new Value<>(CoveringPattern.MARKED, 5),
					new Value<>(CoveringPattern.STRIPED, 5),
					new Value<>(CoveringPattern.HIGHLIGHTS, 5)))) {
	};

	public static AbstractBodyCoveringType SQUIRREL_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_FUR,
			BodyCoveringTemplateFactory.createFurSkin(Util.newArrayListOfValues(CoveringModifier.SMOOTH), null)) {
	};
	
	public static AbstractBodyCoveringType RAT_SKIN = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_SKIN,
			BodyCoveringTemplateFactory.createBottomSkin(PresetColour.ratSkinColours)) {
	};
	
	public static AbstractBodyCoveringType RAT_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_FUR,
			BodyCoveringTemplateFactory.createFurSkin(Util.newArrayListOfValues(CoveringModifier.SMOOTH), null)) {
	};

	public static AbstractBodyCoveringType RABBIT_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_FUR,
			BodyCoveringTemplateFactory.createFurSkin(Util.newArrayListOfValues(CoveringModifier.SMOOTH, CoveringModifier.FLUFFY), null)) {
	};
	
	public static AbstractBodyCoveringType HORSE_HAIR = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_HAIR,
			"a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(CoveringModifier.SHORT),
			null,
			Util.newHashMapOfValues(
					new Value<>(CoveringPattern.NONE, 30),
					new Value<>(CoveringPattern.MOTTLED, 5),
					new Value<>(CoveringPattern.SPOTTED, 5),
					new Value<>(CoveringPattern.MARKED, 5)),
			CoveringPattern.allHairCoveringPatterns,
			PresetColour.naturalFurColours,
			PresetColour.allCoveringColours,
			PresetColour.naturalFurColours,
			PresetColour.allCoveringColours) {
	};
	
	public static AbstractBodyCoveringType REINDEER_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_FUR,
			BodyCoveringTemplateFactory.createFurSkin(
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)))) {
	};
	
	public static AbstractBodyCoveringType BOVINE_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_FUR,
			BodyCoveringTemplateFactory.createFurSkin(
			Util.newArrayListOfValues(
					CoveringModifier.SHORT,
					CoveringModifier.SMOOTH),
			Util.newHashMapOfValues(
					new Value<>(CoveringPattern.NONE, 10),
					new Value<>(CoveringPattern.SPOTTED, 5),
					new Value<>(CoveringPattern.MOTTLED, 1),
					new Value<>(CoveringPattern.MARKED, 1)))) {
	};

	/** Used for their leg covering */
	public static AbstractBodyCoveringType HARPY_SKIN = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_SKIN,
			"a layer of", // This colour is set in GameCharacter's getCovering method, based on the colour of the dildo equipped.
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(CoveringModifier.LEATHERY),
			null,
			null,
			null,
			Util.newArrayListOfValues(
					PresetColour.SKIN_EBONY,
					PresetColour.SKIN_GREY,
					PresetColour.SKIN_AMBER,
					PresetColour.SKIN_YELLOW,
					PresetColour.SKIN_ORANGE),
			ColourListPresets.ALL,
			ColourListPresets.ALL,
			null) {
	};
	
	public static AbstractBodyCoveringType DILDO = new AbstractBodyCoveringType(
			BodyCoveringCategory.ARTIFICIAL,
			"a layer of", // This colour is set in GameCharacter's getCovering method, based on the colour of the dildo equipped.
			false,
			"silicone",
			"silicone",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			null,
			null,
			ColourListPresets.ALL,
			null,
			ColourListPresets.ALL,
			null) {
	};
	
	public static AbstractBodyCoveringType PENIS = new AbstractBodyCoveringType(
			BodyCoveringCategory.PENIS,
			BodyCoveringTemplateFactory.createPenisSkin()) {
	};

	public static AbstractBodyCoveringType ANUS = new AbstractBodyCoveringType(
			BodyCoveringCategory.ANUS,
			BodyCoveringTemplateFactory.createOrificeSkin(CoveringPattern.ORIFICE_ANUS)) {
	};
	
	public static AbstractBodyCoveringType MOUTH = new AbstractBodyCoveringType(
			BodyCoveringCategory.MOUTH,
			BodyCoveringTemplateFactory.createOrificeSkin(CoveringPattern.ORIFICE_MOUTH)) {
	};
	
	public static AbstractBodyCoveringType NIPPLES = new AbstractBodyCoveringType(
			BodyCoveringCategory.NIPPLE,
			BodyCoveringTemplateFactory.createOrificeSkin(CoveringPattern.ORIFICE_NIPPLE)) {
	};
	
	public static AbstractBodyCoveringType NIPPLES_CROTCH = new AbstractBodyCoveringType(
			BodyCoveringCategory.NIPPLE_CROTCH,
			BodyCoveringTemplateFactory.createOrificeSkin(CoveringPattern.ORIFICE_NIPPLE_CROTCH)) {
	};
	
	public static AbstractBodyCoveringType VAGINA = new AbstractBodyCoveringType(
			BodyCoveringCategory.VAGINA,
			BodyCoveringTemplateFactory.createOrificeSkin(CoveringPattern.ORIFICE_VAGINA)) {
	};
	
	public static AbstractBodyCoveringType SPINNERET = new AbstractBodyCoveringType(
			BodyCoveringCategory.SPINNERET,
			BodyCoveringTemplateFactory.createOrificeSkin(CoveringPattern.ORIFICE_SPINNERET)) {
	};
	
	
	public static AbstractBodyCoveringType ALLIGATOR_SCALES = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_SCALES,
			"a layer of",
			true,
			"scales",
			"scale",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			CoveringPattern.allScalesCoveringPatterns,
			PresetColour.naturalScaleColours,
			PresetColour.allCoveringColours,
			PresetColour.naturalScaleColours,
			PresetColour.allCoveringColours) {
	};

	public static AbstractBodyCoveringType SNAKE_SCALES = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_SCALES,
			"a layer of",
			true,
			"scales",
			"scale",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			CoveringPattern.allScalesCoveringPatterns,
			PresetColour.naturalScaleColours,
			PresetColour.allCoveringColours,
			PresetColour.naturalScaleColours,
			PresetColour.allCoveringColours) {
	};
	
	public static AbstractBodyCoveringType SPIDER_CHITIN = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_CHITIN,
			"a layer of",
			false,
			"chitin",
			"chitin",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			CoveringPattern.allScalesCoveringPatterns,
			PresetColour.naturalScaleColours,
			PresetColour.allCoveringColours,
			PresetColour.naturalScaleColours,
			PresetColour.allCoveringColours) {
	};
	
	public static AbstractBodyCoveringType OCTOPUS_SKIN = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_SKIN,
			"a layer of",
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			Util.newHashMapOfValues(
					new Value<>(CoveringPattern.NONE, 5),
					new Value<>(CoveringPattern.SPOTTED, 1)),
			PresetColour.allSkinColours,
			PresetColour.allCoveringColours,
			PresetColour.allSkinColours,
			PresetColour.allCoveringColours) {
	};

	public static AbstractBodyCoveringType FISH_SCALES = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_SCALES,
			"a layer of",
			true,
			"scales",
			"scale",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			CoveringPattern.allScalesCoveringPatterns,
			PresetColour.naturalScaleColours,
			PresetColour.allCoveringColours,
			PresetColour.naturalScaleColours,
			PresetColour.allCoveringColours) {
	};
	
	// MISC:

	public static AbstractBodyCoveringType ANTENNA = new AbstractBodyCoveringType(
			BodyCoveringCategory.ANTENNAE,
			"a layer of",
			false,
			"chitin",
			"chitin",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			null,
			CoveringPattern.allScalesCoveringPatterns,
			PresetColour.naturalScaleColours,
			PresetColour.allCoveringColours,
			PresetColour.naturalScaleColours,
			PresetColour.allCoveringColours) {
	};
	
	public static AbstractBodyCoveringType HORN = new AbstractBodyCoveringType(
			BodyCoveringCategory.HORN,
			"a layer of",
			false,
			"keratin",
			"keratin",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			null,
			CoveringPattern.allScalesCoveringPatterns,
			PresetColour.hornColours,
			PresetColour.allCoveringColours,
			PresetColour.hornColours,
			PresetColour.allCoveringColours) {
	};

	public static AbstractBodyCoveringType ANTLER = new AbstractBodyCoveringType(
			BodyCoveringCategory.ANTLER,
			"a layer of",
			false,
			"velvet",
			"velvet",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			null,
			CoveringPattern.allScalesCoveringPatterns,
			PresetColour.antlerColours,
			PresetColour.allCoveringColours,
			PresetColour.antlerColours,
			PresetColour.allCoveringColours) {
	};

	public static AbstractBodyCoveringType TONGUE = new AbstractBodyCoveringType(
			BodyCoveringCategory.TONGUE,
			"a layer of",
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			null,
			Util.newHashMapOfValues(
					new Value<>(CoveringPattern.NONE, 1),
					new Value<>(CoveringPattern.HIGHLIGHTS, 1),
					new Value<>(CoveringPattern.STRIPED, 1),
					new Value<>(CoveringPattern.SPOTTED, 1),
					new Value<>(CoveringPattern.MOTTLED, 1),
					new Value<>(CoveringPattern.MARKED, 1)),
			Util.newArrayListOfValues(PresetColour.ORIFICE_INTERIOR),
			PresetColour.allSkinColours,
			Util.newArrayListOfValues(PresetColour.ORIFICE_INTERIOR),
			PresetColour.allSkinColours) {
	};

	public static AbstractBodyCoveringType FEATHERS = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_FEATHER,
			"a layer of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newHashMapOfValues(
					new Value<>(CoveringPattern.NONE, 10),
					new Value<>(CoveringPattern.MARKED, 10),
					new Value<>(CoveringPattern.SPOTTED, 5),
					new Value<>(CoveringPattern.MOTTLED, 2),
					new Value<>(CoveringPattern.STRIPED, 1)),
			CoveringPattern.allHairCoveringPatterns,
			PresetColour.naturalFeatherColours,
			PresetColour.dyeFeatherColours,
			PresetColour.naturalFeatherColours,
			PresetColour.dyeFeatherColours) {
	};

	public static AbstractBodyCoveringType WING_LEATHER = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_SKIN,
			"a layer of",
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(CoveringModifier.LEATHERY),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			CoveringPattern.allStandardCoveringPatterns,
			Util.newArrayListOfValues(PresetColour.COVERING_BLACK),
			PresetColour.allCoveringColours,
			Util.newArrayListOfValues(PresetColour.COVERING_BLACK),
			PresetColour.allCoveringColours) {
	};
	
	public static AbstractBodyCoveringType WING_CHITIN = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAIN_CHITIN,
			"a layer of",
			false,
			"chitin",
			"chitin",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			CoveringPattern.allScalesCoveringPatterns,
			Util.newArrayListOfValues(PresetColour.COVERING_CLEAR),
			PresetColour.allCoveringColours,
			Util.newArrayListOfValues(PresetColour.COVERING_CLEAR),
			PresetColour.allCoveringColours) {
	};
	
	// HAIR:

	public static AbstractBodyCoveringType HAIR_HUMAN = new AbstractBodyCoveringType(
			BodyCoveringCategory.HAIR,
			BodyCoveringTemplateFactory.createHeadHair(CoveringModifier.SMOOTH)) {};
	
	public static AbstractBodyCoveringType HAIR_ANGEL = new AbstractBodyCoveringType(
			BodyCoveringCategory.HAIR,
			BodyCoveringTemplateFactory.createHeadHair(CoveringModifier.SILKEN)) {};
	
	public static AbstractBodyCoveringType HAIR_FOX_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.HAIR,
			"a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			CoveringPattern.allHairCoveringPatterns,
			PresetColour.naturalHairColours,
			PresetColour.allCoveringColours,
			PresetColour.naturalHairColours,
			PresetColour.allCoveringColours) {
	};

	public static AbstractBodyCoveringType HAIR_DEMON = new AbstractBodyCoveringType(
			BodyCoveringCategory.HAIR,
			BodyCoveringTemplateFactory.createHeadHair(CoveringModifier.SILKEN)) {};

	public static AbstractBodyCoveringType HAIR_CANINE_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.HAIR,
			BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)) {};

	public static AbstractBodyCoveringType HAIR_LYCAN_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.HAIR,
			BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)) {};

	public static AbstractBodyCoveringType HAIR_FELINE_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.HAIR,
			BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)) {};

	public static AbstractBodyCoveringType HAIR_HORSE_HAIR = new AbstractBodyCoveringType(
			BodyCoveringCategory.HAIR,
			BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.COARSE)) {};

	public static AbstractBodyCoveringType HAIR_REINDEER_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.HAIR,
			BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.COARSE)) {};

	public static AbstractBodyCoveringType HAIR_BOVINE_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.HAIR,
			BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.COARSE)) {};

	public static AbstractBodyCoveringType HAIR_SQUIRREL_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.HAIR,
			BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)) {};

	public static AbstractBodyCoveringType HAIR_RAT_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.HAIR,
			BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)) {};

	public static AbstractBodyCoveringType HAIR_RABBIT_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.HAIR,
			BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)) {};
	
	public static AbstractBodyCoveringType HAIR_BAT_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.HAIR,
			BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)) {};
	
	public static AbstractBodyCoveringType HAIR_HARPY = new AbstractBodyCoveringType(
			BodyCoveringCategory.HAIR,
			"a plume of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			CoveringPattern.allHairCoveringPatterns,
			null,
			PresetColour.naturalFeatherColours,
			PresetColour.dyeFeatherColours,
			PresetColour.naturalFeatherColours,
			PresetColour.dyeFeatherColours) {
	};
	
	public static AbstractBodyCoveringType HAIR_SCALES_ALLIGATOR = new AbstractBodyCoveringType(
			BodyCoveringCategory.HAIR,
			BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.COARSE)) {};
	
	
	// BODY HAIR:
	
	public static AbstractBodyCoveringType BODY_HAIR_HUMAN = new AbstractBodyCoveringType(
			BodyCoveringCategory.BODY_HAIR,
			BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.COARSE)) {};

	public static AbstractBodyCoveringType BODY_HAIR_ANGEL = new AbstractBodyCoveringType(
			BodyCoveringCategory.BODY_HAIR,
			BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.SILKEN)) {};

	public static AbstractBodyCoveringType BODY_HAIR_DEMON = new AbstractBodyCoveringType(
			BodyCoveringCategory.BODY_HAIR,
			BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.SILKEN)) {};

	public static AbstractBodyCoveringType BODY_HAIR_CANINE_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.BODY_HAIR,
			BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)) {};

	public static AbstractBodyCoveringType BODY_HAIR_LYCAN_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.BODY_HAIR,
			BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)) {};

	public static AbstractBodyCoveringType BODY_HAIR_FOX_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.BODY_HAIR,
			BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)) {};
	
	public static AbstractBodyCoveringType BODY_HAIR_FELINE_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.BODY_HAIR,
			BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)) {};

	public static AbstractBodyCoveringType BODY_HAIR_HORSE_HAIR = new AbstractBodyCoveringType(
			BodyCoveringCategory.BODY_HAIR,
			BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.COARSE)) {};

	public static AbstractBodyCoveringType BODY_HAIR_REINDEER_HAIR = new AbstractBodyCoveringType(
			BodyCoveringCategory.BODY_HAIR,
			BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.COARSE)) {};

	public static AbstractBodyCoveringType BODY_HAIR_BOVINE_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.BODY_HAIR,
			BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.COARSE)) {};

	public static AbstractBodyCoveringType BODY_HAIR_SQUIRREL_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.BODY_HAIR,
			BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)) {};

	public static AbstractBodyCoveringType BODY_HAIR_RAT_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.BODY_HAIR,
			BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)) {};

	public static AbstractBodyCoveringType BODY_HAIR_RABBIT_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.BODY_HAIR,
			BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)) {};
	
	public static AbstractBodyCoveringType BODY_HAIR_BAT_FUR = new AbstractBodyCoveringType(
			BodyCoveringCategory.BODY_HAIR,
			BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)) {};
	
	public static AbstractBodyCoveringType BODY_HAIR_HARPY = new AbstractBodyCoveringType(
			BodyCoveringCategory.BODY_HAIR,
			"a patch of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(CoveringModifier.FLUFFY),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			PresetColour.naturalFeatherColours,
			PresetColour.dyeFeatherColours,
			PresetColour.naturalFeatherColours,
			PresetColour.dyeFeatherColours) {
	};

	public static AbstractBodyCoveringType BODY_HAIR_SCALES_ALLIGATOR = new AbstractBodyCoveringType(
			BodyCoveringCategory.BODY_HAIR,
			BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.COARSE)) {};


	
	// EYES:
	
	public static AbstractBodyCoveringType EYE_HUMAN = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_IRIS,
			BodyCoveringTemplateFactory.createEyeIrisesHeterochromiaNaturallyOccurring()) {};

	public static AbstractBodyCoveringType EYE_ANGEL = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_IRIS,
			BodyCoveringTemplateFactory.createEyeIrises()) {};

	public static AbstractBodyCoveringType EYE_DEMON_COMMON = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_IRIS,
			BodyCoveringTemplateFactory.createEyeIrisesWithCustomColors(
			PresetColour.naturalDemonIrisColours, PresetColour.dyeDemonIrisColours, true)) {
	};

	public static AbstractBodyCoveringType EYE_DOG_MORPH = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_IRIS,
			BodyCoveringTemplateFactory.createEyeIrisesHeterochromiaNaturallyOccurring()) {};

	public static AbstractBodyCoveringType EYE_LYCAN = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_IRIS,
			BodyCoveringTemplateFactory.createEyeIrisesWithCustomColors(
			PresetColour.naturalPredatorIrisColours, PresetColour.dyePredatorIrisColours, true)) {
	};
	
	public static AbstractBodyCoveringType EYE_FOX_MORPH = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_IRIS,
			"a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_IRISES, 1)),
			Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC, 1)),
			PresetColour.naturalPredatorIrisColours,
			PresetColour.dyePredatorIrisColours,
			PresetColour.naturalPredatorIrisColours,
			PresetColour.dyePredatorIrisColours) {
	};

	public static AbstractBodyCoveringType EYE_FELINE = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_IRIS,
			BodyCoveringTemplateFactory.createEyeIrisesWithCustomColors(
			PresetColour.naturalPredatorIrisColours, PresetColour.dyePredatorIrisColours, true)) {
	};

	public static AbstractBodyCoveringType EYE_SQUIRREL = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_IRIS,
			BodyCoveringTemplateFactory.createEyeIrises()) {};

	public static AbstractBodyCoveringType EYE_RAT = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_IRIS,
			BodyCoveringTemplateFactory.createEyeIrises()) {};

	public static AbstractBodyCoveringType EYE_RABBIT = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_IRIS,
			BodyCoveringTemplateFactory.createEyeIrises()) {};
	
	public static AbstractBodyCoveringType EYE_BAT = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_IRIS,
			BodyCoveringTemplateFactory.createEyeIrises()) {};
	
	public static AbstractBodyCoveringType EYE_ALLIGATOR_MORPH = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_IRIS,
			BodyCoveringTemplateFactory.createEyeIrises()) {};

	public static AbstractBodyCoveringType EYE_HORSE_MORPH = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_IRIS,
			BodyCoveringTemplateFactory.createEyeIrisesHeterochromiaNaturallyOccurring()) {};

	public static AbstractBodyCoveringType EYE_REINDEER_MORPH = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_IRIS,
			BodyCoveringTemplateFactory.createEyeIrises()) {};

	public static AbstractBodyCoveringType EYE_COW_MORPH = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_IRIS,
			BodyCoveringTemplateFactory.createEyeIrisesHeterochromiaNaturallyOccurring()) {};

	public static AbstractBodyCoveringType EYE_HARPY = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_IRIS,
			BodyCoveringTemplateFactory.createEyeIrises()) {};

	public static AbstractBodyCoveringType EYE_PUPILS = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_PUPIL,
			"a pair of",
			true,
			"pupils",
			"pupil",
			Util.newArrayListOfValues(CoveringModifier.EYE),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_PUPILS, 1)),
			Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_PUPILS_HETEROCHROMATIC, 1)),
			PresetColour.naturalPupilColours,
			PresetColour.dyePupilColours,
			PresetColour.naturalPupilColours,
			PresetColour.dyePupilColours) {
	};

	public static AbstractBodyCoveringType EYE_SCLERA = new AbstractBodyCoveringType(
			BodyCoveringCategory.EYE_SCLERA,
			"a pair of",
			true,
			"sclerae",
			"sclera",
			Util.newArrayListOfValues(CoveringModifier.EYE),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_SCLERA, 1)),
			Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_SCLERA_HETEROCHROMATIC, 1)),
			PresetColour.naturalScleraColours,
			PresetColour.dyeScleraColours,
			PresetColour.naturalScleraColours,
			PresetColour.dyeScleraColours) {
	};
	
	
	// Fluids:
	
	public static AbstractBodyCoveringType CUM = new AbstractBodyCoveringType(
			BodyCoveringCategory.FLUID,
			"",
			false,
			"cum",
			"cum",
			Util.newArrayListOfValues(CoveringModifier.FLUID),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.FLUID, 1)),
			null,
			Util.newArrayListOfValues(PresetColour.COVERING_WHITE),
			PresetColour.allCoveringColours,
			null,
			null) {
	};
	
	public static AbstractBodyCoveringType GIRL_CUM = new AbstractBodyCoveringType(
			BodyCoveringCategory.FLUID,
			"",
			false,
			"girlcum",
			"girlcum",
			Util.newArrayListOfValues(CoveringModifier.FLUID),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.FLUID, 1)),
			null,
			Util.newArrayListOfValues(PresetColour.COVERING_CLEAR),
			PresetColour.allCoveringColours,
			null,
			null) {
	};
	
	public static AbstractBodyCoveringType MILK = new AbstractBodyCoveringType(
			BodyCoveringCategory.FLUID,
			"",
			false,
			"milk",
			"milk",
			Util.newArrayListOfValues(CoveringModifier.FLUID),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.FLUID, 1)),
			null,
			Util.newArrayListOfValues(PresetColour.COVERING_WHITE),
			PresetColour.allCoveringColours,
			null,
			null) {
	};
	
	
	// Makeup:
	
	public static AbstractBodyCoveringType MAKEUP_BLUSHER = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAKEUP,
			"a layer of",
			false,
			"blusher",
			"blusher",
			Util.newArrayListOfValues(CoveringModifier.MAKEUP),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			null,
			Util.newArrayListOfValues(PresetColour.COVERING_NONE),
			PresetColour.allMakeupColours,
			null,
			null) {
	};
	
	public static AbstractBodyCoveringType MAKEUP_EYE_LINER = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAKEUP,
			"a layer of",
			false,
			"eye liner",
			"eye liner",
			Util.newArrayListOfValues(CoveringModifier.MAKEUP),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			null,
			Util.newArrayListOfValues(PresetColour.COVERING_NONE),
			PresetColour.allMakeupColours,
			null,
			null) {
	};
	
	public static AbstractBodyCoveringType MAKEUP_EYE_SHADOW = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAKEUP,
			"a layer of",
			false,
			"eye shadow",
			"eye shadow",
			Util.newArrayListOfValues(
					CoveringModifier.MATTE,
					CoveringModifier.SPARKLY,
					CoveringModifier.METALLIC),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			null,
			Util.newArrayListOfValues(PresetColour.COVERING_NONE),
			PresetColour.allMakeupColours,
			null,
			null) {
	};

	public static AbstractBodyCoveringType MAKEUP_LIPSTICK = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAKEUP,
			"a layer of",
			false,
			"lipstick",
			"lipstick",
			Util.newArrayListOfValues(
					CoveringModifier.GLOSSY,
					CoveringModifier.MATTE,
					CoveringModifier.SPARKLY,
					CoveringModifier.METALLIC),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			Util.newHashMapOfValues(
					new Value<>(CoveringPattern.SPOTTED, 1),
					new Value<>(CoveringPattern.STRIPED, 1)),
			Util.newArrayListOfValues(PresetColour.COVERING_NONE),
			PresetColour.allMakeupColours,
			null,
			PresetColour.allMakeupColours) {
	};
	
	public static AbstractBodyCoveringType MAKEUP_NAIL_POLISH_HANDS = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAKEUP,
			"a layer of",
			false,
			"nail polish",
			"nail polish",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH,
					CoveringModifier.SPARKLY,
					CoveringModifier.METALLIC),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			Util.newHashMapOfValues(
					new Value<>(CoveringPattern.SPOTTED, 1),
					new Value<>(CoveringPattern.STRIPED, 1)),
			Util.newArrayListOfValues(PresetColour.COVERING_NONE),
			PresetColour.allMakeupColours,
			null,
			PresetColour.allMakeupColours) {
	};
	
	public static AbstractBodyCoveringType MAKEUP_NAIL_POLISH_FEET = new AbstractBodyCoveringType(
			BodyCoveringCategory.MAKEUP,
			"a layer of",
			false,
			"nail polish",
			"nail polish",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH,
					CoveringModifier.SPARKLY,
					CoveringModifier.METALLIC),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			Util.newHashMapOfValues(
					new Value<>(CoveringPattern.SPOTTED, 1),
					new Value<>(CoveringPattern.STRIPED, 1)),
			Util.newArrayListOfValues(PresetColour.COVERING_NONE),
			PresetColour.allMakeupColours,
			null,
			PresetColour.allMakeupColours) {
	};
	
	
	public static List<AbstractBodyCoveringType> allBodyCoveringTypes;
	
	public static Map<AbstractBodyCoveringType, String> bodyCoveringTypesToIdMap = new HashMap<>();
	public static Map<String, AbstractBodyCoveringType> idToBodyCoveringTypesMap = new HashMap<>();

	public static List<AbstractBodyCoveringType> allMakeupTypes;
	public static List<AbstractBodyCoveringType> allSlimeTypes;
	
	public static AbstractBodyCoveringType getBodyCoveringTypeFromId(String id) {
		
		// Imp changes:
		if(id.equals("IMP")) {
			id = "DEMON_COMMON";
		} else if(id.equals("HAIR_IMP")) {
			id = "HAIR_DEMON";
		} else if(id.equals("BODY_HAIR_IMP")) {
			id = "BODY_HAIR_DEMON";
		} else if(id.equals("EYE_IMP")) {
			id = "EYE_DEMON_COMMON";
		}
		// Material updates in v0.4:
		if(id.equals("SLIME")) {
			id = "SLIME_MAIN_SKIN";
		} else if(id.equals("SLIME_EYE")) {
			id = "SLIME_EYE_IRIS";
		} else if(id.equals("SLIME_PUPILS")) {
			id = "SLIME_EYE_PUPIL";
		} else if(id.equals("SLIME_SCLERA")) {
			id = "SLIME_EYE_SCLERA";
		} else if(id.equals("SLIME_NIPPLES")) {
			id = "SLIME_NIPPLE";
		} else if(id.equals("SLIME_NIPPLES_CROTCH")) {
			id = "SLIME_NIPPLE_CROTCH";
		} else if(id.equals("FIRE")) {
			id = "FIRE_MAIN_SKIN";
		} else if(id.equals("WATER")) {
			id = "WATER_MAIN_SKIN";
		} else if(id.equals("ICE")) {
			id = "ICE_MAIN_SKIN";
		} else if(id.equals("AIR")) {
			id = "AIR_MAIN_SKIN";
		} else if(id.equals("STONE")) {
			id = "STONE_MAIN_SKIN";
		} else if(id.equals("RUBBER")) {
			id = "RUBBER_MAIN_SKIN";
		} else if(id.equals("ARCANE")) {
			id = "ARCANE_MAIN_SKIN";
		}
		// Other changes:
		if(id.equals("DEMON_HORSE_HAIR")) {
			id = "HORSE_HAIR";
		} else if(id.equals("ANTLER_REINDEER")) {
			id = "ANTLER";
		}
		
		id = Util.getClosestStringMatch(id, idToBodyCoveringTypesMap.keySet());
		return idToBodyCoveringTypesMap.get(id);
	}
	
	public static String getIdFromBodyCoveringType(AbstractBodyCoveringType bct) {
		return bodyCoveringTypesToIdMap.get(bct);
	}
	
	/**
	 * @param material The BodyMaterial which the body is made up of.
	 * @param category The BodyCoveringCategory which you want to find the AbstractBodyCoveringType of.
	 * @return The AbstractBodyCoveringType which corresponds to the supplied material and category.
	 */
	public static AbstractBodyCoveringType getMaterialBodyCoveringType(BodyMaterial material, BodyCoveringCategory category) {
		return getBodyCoveringTypeFromId(material.toString()+"_"+category.toString());
	}
	
	static {
		allBodyCoveringTypes = new ArrayList<>();
		allMakeupTypes = new ArrayList<>();
		
		// Modded covering types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "coveringTypes", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractBodyCoveringType coveringType = new AbstractBodyCoveringType(innerEntry.getValue(), entry.getKey(), true) {};
					String id = innerEntry.getKey().replaceAll("coveringTypes_", "");
					allBodyCoveringTypes.add(coveringType);
					bodyCoveringTypesToIdMap.put(coveringType, id);
					idToBodyCoveringTypesMap.put(id, coveringType);
				} catch(Exception ex) {
					System.err.println("Loading modded bodyCoveringType failed at 'BodyCoveringType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res covering types:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", "coveringTypes", null);
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractBodyCoveringType coveringType = new AbstractBodyCoveringType(innerEntry.getValue(), entry.getKey(), false) {};
					String id = innerEntry.getKey().replaceAll("coveringTypes_", "");
					allBodyCoveringTypes.add(coveringType);
					bodyCoveringTypesToIdMap.put(coveringType, id);
					idToBodyCoveringTypesMap.put(id, coveringType);
				} catch(Exception ex) {
					System.err.println("Loading bodyCoveringType failed at 'BodyCoveringType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		Field[] fields = BodyCoveringType.class.getFields();
		
		for(Field f : fields){
			if(AbstractBodyCoveringType.class.isAssignableFrom(f.getType())) {
				AbstractBodyCoveringType bct;
				
				try {
					bct = ((AbstractBodyCoveringType) f.get(null));
					
					if(bct.getCategory()==BodyCoveringCategory.MAKEUP) {
						allMakeupTypes.add(bct);
					}
					
					bodyCoveringTypesToIdMap.put(bct, f.getName());
					idToBodyCoveringTypesMap.put(f.getName(), bct);
					allBodyCoveringTypes.add(bct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		// Automatically add AbstractBodyCoveringTypes for all non-flesh materials:
		for(BodyMaterial mat : BodyMaterial.values()) {
			if(mat!=BodyMaterial.FLESH) {
				if(mat==BodyMaterial.SLIME) {
					allSlimeTypes = new ArrayList<>();
					
					for(BodyCoveringCategory cat : BodyCoveringCategory.values()) {
						AbstractBodyCoveringType bct = null;
						switch(cat) {
							case ANTENNAE:
							case ANTLER:
							case HORN:
							case BODY_HAIR:
							case HAIR:
							case MAIN_FEATHER:
							case MAIN_FUR:
							case MAIN_HAIR:
							case MAIN_SCALES:
							case MAIN_CHITIN:
							case MAIN_SKIN:
							case PENIS:
							case TONGUE:
								bct = new AbstractBodyCoveringType(cat, BodyCoveringTemplateFactory.createSlime(CoveringPattern.NONE, CoveringPattern.allSlimeCoveringPatterns)) {};
								break;
							case EYE_IRIS:
								bct = new AbstractBodyCoveringType(cat,
										BodyCoveringTemplateFactory.createSlime(CoveringPattern.EYE_IRISES, Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC, 1)))) {
								};
								break;
							case EYE_PUPIL:
								bct = new AbstractBodyCoveringType(cat,
										BodyCoveringTemplateFactory.createSlime(CoveringPattern.EYE_PUPILS, Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC, 1)))) {
								};
								break;
							case EYE_SCLERA:
								bct = new AbstractBodyCoveringType(cat,
										BodyCoveringTemplateFactory.createSlime(CoveringPattern.EYE_SCLERA, Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC, 1)))) {
								};
								break;
								
							case ANUS:
								bct = new AbstractBodyCoveringType(cat, BodyCoveringTemplateFactory.createSlime(CoveringPattern.ORIFICE_ANUS, null)) {};
								break;
							case MOUTH:
								bct = new AbstractBodyCoveringType(cat, BodyCoveringTemplateFactory.createSlime(CoveringPattern.ORIFICE_MOUTH, null)) {};
								break;
							case NIPPLE:
								bct = new AbstractBodyCoveringType(cat, BodyCoveringTemplateFactory.createSlime(CoveringPattern.ORIFICE_NIPPLE, null)) {};
								break;
							case NIPPLE_CROTCH:
								bct = new AbstractBodyCoveringType(cat, BodyCoveringTemplateFactory.createSlime(CoveringPattern.ORIFICE_NIPPLE_CROTCH, null)) {};
								break;
							case VAGINA:
								bct = new AbstractBodyCoveringType(cat, BodyCoveringTemplateFactory.createSlime(CoveringPattern.ORIFICE_VAGINA, null)) {};
								break;
							case SPINNERET:
								bct = new AbstractBodyCoveringType(cat, BodyCoveringTemplateFactory.createSlime(CoveringPattern.ORIFICE_SPINNERET, null)) {};
								break;
								
							case ARTIFICIAL:
							case FLUID:
							case MAKEUP:
								break;
						}
						
						if(bct!=null) {
							String id =  mat.toString()+"_"+cat.toString();
							bodyCoveringTypesToIdMap.put(bct,id);
							idToBodyCoveringTypesMap.put(id, bct);
							allBodyCoveringTypes.add(bct);
							allSlimeTypes.add(bct);
						}
					}
					
				} else {
					String name = "";
					CoveringModifier modifier = CoveringModifier.SMOOTH;
					List<Colour> naturalColours = new ArrayList<>();
					
					switch(mat) {
						case FLESH:
						case SLIME:
							break;
						case AIR:
							name = "vapours";
							modifier = CoveringModifier.SWIRLING;
							naturalColours.add(PresetColour.COVERING_BLUE_LIGHT);
							naturalColours.add(PresetColour.COVERING_GREY);
							naturalColours.add(PresetColour.COVERING_WHITE);
							break;
						case ARCANE:
							name = "energy";
							modifier = CoveringModifier.SWIRLING;
							naturalColours.add(PresetColour.COVERING_PINK);
							naturalColours.add(PresetColour.COVERING_PINK_DARK);
							naturalColours.add(PresetColour.COVERING_PINK_LIGHT);
							break;
						case FIRE:
							name = "flames";
							modifier = CoveringModifier.BLAZING;
							naturalColours.add(PresetColour.COVERING_RED);
							naturalColours.add(PresetColour.COVERING_ORANGE);
							naturalColours.add(PresetColour.COVERING_YELLOW);
							naturalColours.add(PresetColour.COVERING_BLUE_LIGHT);
							break;
						case ICE:
							name = "ice";
							modifier = CoveringModifier.GLITTERING;
							naturalColours.add(PresetColour.COVERING_BLUE_LIGHT);
							naturalColours.add(PresetColour.COVERING_WHITE);
							break;
						case RUBBER:
							name = "rubber";
							modifier = CoveringModifier.GLOSSY;
							naturalColours.add(PresetColour.COVERING_BLACK);
							break;
						case STONE:
							name = "stone";
							modifier = CoveringModifier.MATTE;
							naturalColours.add(PresetColour.COVERING_GREY);
							naturalColours.add(PresetColour.COVERING_BLACK);
							break;
						case WATER:
							name = "water";
							modifier = CoveringModifier.SHIMMERING;
							naturalColours.add(PresetColour.COVERING_BLUE);
							naturalColours.add(PresetColour.COVERING_BLUE_LIGHT);
							naturalColours.add(PresetColour.COVERING_BLUE_DARK);
							break;
					}
					for(BodyCoveringCategory cat : BodyCoveringCategory.values()) {
						if(cat.isInfluencedByMaterialType()) {
							CoveringPattern pattern = CoveringPattern.NONE;
							switch(cat) {
								case ANTENNAE:
								case ANTLER:
								case HORN:
								case BODY_HAIR:
								case HAIR:
								case MAIN_FEATHER:
								case MAIN_FUR:
								case MAIN_HAIR:
								case MAIN_SCALES:
								case MAIN_CHITIN:
								case MAIN_SKIN:
								case PENIS:
								case TONGUE:
									break;
								case EYE_IRIS:
									pattern = CoveringPattern.EYE_IRISES;
									break;
								case EYE_PUPIL:
									pattern = CoveringPattern.EYE_PUPILS;
									break;
								case EYE_SCLERA:
									pattern = CoveringPattern.EYE_SCLERA;
									break;
								case ANUS:
									pattern = CoveringPattern.ORIFICE_ANUS;
									break;
								case MOUTH:
									pattern = CoveringPattern.ORIFICE_MOUTH;
									break;
								case NIPPLE:
									pattern = CoveringPattern.ORIFICE_NIPPLE;
									break;
								case NIPPLE_CROTCH:
									pattern = CoveringPattern.ORIFICE_NIPPLE_CROTCH;
									break;
								case VAGINA:
									pattern = CoveringPattern.ORIFICE_VAGINA;
									break;
								case SPINNERET:
									pattern = CoveringPattern.ORIFICE_SPINNERET;
									break;
								case ARTIFICIAL:
								case FLUID:
								case MAKEUP:
									break;
							}
							AbstractBodyCoveringType bct = new AbstractBodyCoveringType(cat, BodyCoveringTemplateFactory.createElemental(name, modifier, pattern, naturalColours)) {};
							
							String id =  mat.toString()+"_"+cat.toString();
							bodyCoveringTypesToIdMap.put(bct, id);
							idToBodyCoveringTypesMap.put(id, bct);
							allBodyCoveringTypes.add(bct);
						}
					}
				}
				
			}
		}
		
	}
	
	public static List<AbstractBodyCoveringType> getAllBodyCoveringTypes() {
		return allBodyCoveringTypes;
	}
	
	public static List<AbstractBodyCoveringType> getAllMakeupTypes() {
		return allMakeupTypes;
	}
	
	public static List<AbstractBodyCoveringType> getAllSlimeTypes() {
		return allSlimeTypes;
	}
}