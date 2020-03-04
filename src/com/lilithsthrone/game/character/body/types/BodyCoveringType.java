package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.ColourListPresets;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public enum BodyCoveringType {

	// Skin shades go light->dark

	HUMAN(BodyCoveringTemplateFactory.createTopSkin(
			Util.newHashMapOfValues(
					new Value<>(CoveringPattern.NONE, 30),
					new Value<>(CoveringPattern.FRECKLED_FACE, 2),
					new Value<>(CoveringPattern.FRECKLED, 1)),
			Colour.humanSkinColours)),
	
	FOX_FUR("a layer of",
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
					Colour.COVERING_ORANGE,
					Colour.COVERING_TAN,
					Colour.COVERING_GREY,
					Colour.COVERING_BLACK),
			Colour.allCoveringColours,
			Util.newArrayListOfValues(
					Colour.COVERING_WHITE), // Fox markings are always naturally white
			Colour.allCoveringColours),
	
	ANGEL(BodyCoveringTemplateFactory.createTopSkin(
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			Colour.humanSkinColours)),

	ANGEL_FEATHER("a layer of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			CoveringPattern.allHairCoveringPatterns,
			Util.newArrayListOfValues(Colour.COVERING_WHITE),
			Util.mergeLists(Colour.dyeFeatherColours, Colour.naturalFeatherColours),
			Util.newArrayListOfValues(Colour.COVERING_WHITE),
			Util.mergeLists(Colour.dyeFeatherColours, Colour.naturalFeatherColours)),
	
	DEMON_COMMON(BodyCoveringTemplateFactory.createTopSkin(
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			Colour.demonSkinColours)),

	DEMON_FEATHER("a layer of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			CoveringPattern.allHairCoveringPatterns,
			Util.newArrayListOfValues(Colour.COVERING_BLACK),
			Util.mergeLists(Colour.dyeFeatherColours, Colour.naturalFeatherColours),
			Util.newArrayListOfValues(Colour.COVERING_BLACK),
			Util.mergeLists(Colour.dyeFeatherColours, Colour.naturalFeatherColours)),

	DEMON_HORSE_HAIR("a layer of",
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
			Colour.naturalFurColours,
			Colour.allCoveringColours,
			Colour.naturalFurColours,
			Colour.allCoveringColours),
	
	BAT_SKIN(BodyCoveringTemplateFactory.createBottomSkin(Colour.humanSkinColours)),
	
	BAT_FUR(BodyCoveringTemplateFactory.createFurSkin(
			Util.newArrayListOfValues(CoveringModifier.SHORT),
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)))),
	
	CANINE_FUR(BodyCoveringTemplateFactory.createFurSkin(
			Util.newArrayListOfValues(
					CoveringModifier.FLUFFY,
					CoveringModifier.SHORT,
					CoveringModifier.SHAGGY),
			Util.newHashMapOfValues(
					new Value<>(CoveringPattern.NONE, 30),
					new Value<>(CoveringPattern.SPOTTED, 5),
					new Value<>(CoveringPattern.MARKED, 5)))),
	
	LYCAN_FUR(BodyCoveringTemplateFactory.createFurSkin(Util.newArrayListOfValues(CoveringModifier.SHAGGY), null)),

	FELINE_FUR(BodyCoveringTemplateFactory.createFurSkin(
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
					new Value<>(CoveringPattern.HIGHLIGHTS, 5)))),

	SQUIRREL_FUR(BodyCoveringTemplateFactory.createFurSkin(Util.newArrayListOfValues(CoveringModifier.SMOOTH), null)),
	
	RAT_SKIN(BodyCoveringTemplateFactory.createBottomSkin(Colour.ratSkinColours)),
	
	RAT_FUR(BodyCoveringTemplateFactory.createFurSkin(Util.newArrayListOfValues(CoveringModifier.SMOOTH), null)),

	RABBIT_FUR(BodyCoveringTemplateFactory.createFurSkin(Util.newArrayListOfValues(CoveringModifier.SMOOTH), null)),
	
	HORSE_HAIR("a layer of",
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
			Colour.naturalFurColours,
			Colour.allCoveringColours,
			Colour.naturalFurColours,
			Colour.allCoveringColours),
	
	REINDEER_FUR(BodyCoveringTemplateFactory.createFurSkin(
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)))),
	
	BOVINE_FUR(BodyCoveringTemplateFactory.createFurSkin(
			Util.newArrayListOfValues(
					CoveringModifier.SHORT,
					CoveringModifier.SMOOTH),
			Util.newHashMapOfValues(
					new Value<>(CoveringPattern.NONE, 10),
					new Value<>(CoveringPattern.SPOTTED, 5),
					new Value<>(CoveringPattern.MOTTLED, 1),
					new Value<>(CoveringPattern.MARKED, 1)))),
	
	DILDO("a layer of", // This colour is set in GameCharacter's getCovering method, based on the colour of the dildo equipped.
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
			null),
	
	PENIS(BodyCoveringTemplateFactory.createPenisSkin()),

	ANUS(BodyCoveringTemplateFactory.createOrificeSkin(CoveringPattern.ORIFICE_ANUS)),
	
	MOUTH(BodyCoveringTemplateFactory.createOrificeSkin(CoveringPattern.ORIFICE_MOUTH)),
	
	NIPPLES(BodyCoveringTemplateFactory.createOrificeSkin(CoveringPattern.ORIFICE_NIPPLE)),
	
	NIPPLES_CROTCH(BodyCoveringTemplateFactory.createOrificeSkin(CoveringPattern.ORIFICE_NIPPLE_CROTCH)),
	
	VAGINA(BodyCoveringTemplateFactory.createOrificeSkin(CoveringPattern.ORIFICE_VAGINA)),
	

	FIRE(BodyCoveringTemplateFactory.createElemental("flames", CoveringModifier.BLAZING, 
					Colour.COVERING_ORANGE,
					Colour.COVERING_BLUE_LIGHT)),
	
	FIRE_HAIR(BodyCoveringTemplateFactory.createElemental("flames", CoveringModifier.BLAZING, 
			Colour.COVERING_ORANGE,
			Colour.COVERING_BLUE_LIGHT)),
	
	WATER(BodyCoveringTemplateFactory.createElemental("water", CoveringModifier.SHIMMERING, 
			Colour.COVERING_BLUE,
			Colour.COVERING_BLUE_LIGHT)),
	
	WATER_HAIR(BodyCoveringTemplateFactory.createElemental("water", CoveringModifier.SHIMMERING, 
			Colour.COVERING_BLUE,
			Colour.COVERING_BLUE_LIGHT)),

	ICE(BodyCoveringTemplateFactory.createElemental("ice", CoveringModifier.SHIMMERING, Colour.COVERING_BLUE_LIGHT)),
	
	ICE_HAIR(BodyCoveringTemplateFactory.createElemental("ice", CoveringModifier.SHIMMERING, Colour.COVERING_BLUE_LIGHT)),

	AIR(BodyCoveringTemplateFactory.createElemental("vapours", CoveringModifier.SWIRLING, Colour.COVERING_BLUE_LIGHT)),
	
	AIR_HAIR(BodyCoveringTemplateFactory.createElemental("vapours", CoveringModifier.SWIRLING, Colour.COVERING_BLUE_LIGHT)),

	STONE(BodyCoveringTemplateFactory.createElemental("stone", CoveringModifier.MATTE, Colour.COVERING_GREY)),
	
	STONE_HAIR(BodyCoveringTemplateFactory.createElemental("stone", CoveringModifier.MATTE, Colour.COVERING_GREY)),

	RUBBER(BodyCoveringTemplateFactory.createElemental("rubber", CoveringModifier.GLOSSY, Colour.COVERING_BLACK)),
	
	RUBBER_HAIR(BodyCoveringTemplateFactory.createElemental("rubber", CoveringModifier.GLOSSY, Colour.COVERING_BLACK)),

	ARCANE(BodyCoveringTemplateFactory.createElemental("energy", CoveringModifier.SWIRLING, Colour.COVERING_PINK)),
	
	ARCANE_HAIR(BodyCoveringTemplateFactory.createElemental("energy", CoveringModifier.SWIRLING, Colour.COVERING_PINK)),
	
	
	SLIME(BodyCoveringTemplateFactory.createSlime(CoveringPattern.NONE, CoveringPattern.allStandardCoveringPatterns)),

	SLIME_EYE(BodyCoveringTemplateFactory.createSlime(CoveringPattern.EYE_IRISES,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC, 1)))),
	
	SLIME_PUPILS(BodyCoveringTemplateFactory.createSlime(CoveringPattern.EYE_PUPILS,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_PUPILS_HETEROCHROMATIC, 1)))),
	
	SLIME_SCLERA(BodyCoveringTemplateFactory.createSlime(CoveringPattern.EYE_SCLERA,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_SCLERA_HETEROCHROMATIC, 1)))),
	
	SLIME_HAIR(BodyCoveringTemplateFactory.createSlime(CoveringPattern.NONE, CoveringPattern.allHairCoveringPatterns)),

	SLIME_BODY_HAIR(BodyCoveringTemplateFactory.createSlime(CoveringPattern.NONE, CoveringPattern.allHairCoveringPatterns)),
	
	SLIME_ANUS(BodyCoveringTemplateFactory.createSlime(CoveringPattern.ORIFICE_ANUS, null)),
	
	SLIME_MOUTH(BodyCoveringTemplateFactory.createSlime(CoveringPattern.ORIFICE_MOUTH, null)),
	
	SLIME_NIPPLES(BodyCoveringTemplateFactory.createSlime(CoveringPattern.ORIFICE_NIPPLE, null)),
	
	SLIME_NIPPLES_CROTCH(BodyCoveringTemplateFactory.createSlime(CoveringPattern.ORIFICE_NIPPLE_CROTCH, null)),
	
	SLIME_VAGINA(BodyCoveringTemplateFactory.createSlime(CoveringPattern.ORIFICE_VAGINA, null)),
	
	SLIME_PENIS(BodyCoveringTemplateFactory.createSlime(CoveringPattern.NONE, CoveringPattern.allStandardCoveringPatterns)),

	FEATHERS("a layer of",
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
			Colour.naturalFeatherColours,
			Colour.dyeFeatherColours,
			Colour.naturalFeatherColours,
			Colour.dyeFeatherColours),

	ALLIGATOR_SCALES("a layer of",
			true,
			"scales",
			"scale",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			CoveringPattern.allScalesCoveringPatterns,
			Colour.naturalScaleColours,
			Colour.allCoveringColours,
			Colour.naturalScaleColours,
			Colour.allCoveringColours),

	// MISC:
	
	HORN("a layer of",
			false,
			"keratin",
			"keratin",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			null,
			CoveringPattern.allScalesCoveringPatterns,
			Colour.hornColours,
			Colour.allCoveringColours,
			Colour.hornColours,
			Colour.allCoveringColours),

	ANTLER_REINDEER("a layer of",
			false,
			"velvet",
			"velvet",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			null,
			CoveringPattern.allScalesCoveringPatterns,
			Colour.antlerColours,
			Colour.allCoveringColours,
			Colour.antlerColours,
			Colour.allCoveringColours),

	TONGUE("a layer of",
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
			Util.newArrayListOfValues(Colour.ORIFICE_INTERIOR),
			Colour.allSkinColours,
			Util.newArrayListOfValues(Colour.ORIFICE_INTERIOR),
			Colour.allSkinColours),

	// HAIR:

	HAIR_HUMAN(BodyCoveringTemplateFactory.createHeadHair(CoveringModifier.SMOOTH)),
	
	HAIR_ANGEL(BodyCoveringTemplateFactory.createHeadHair(CoveringModifier.SILKEN)),
	
	HAIR_FOX_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.allCoveringColours,
			Colour.naturalHairColours,
			Colour.allCoveringColours),

	HAIR_DEMON(BodyCoveringTemplateFactory.createHeadHair(CoveringModifier.SILKEN)),

	HAIR_CANINE_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)),

	HAIR_LYCAN_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)),

	HAIR_FELINE_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)),

	HAIR_HORSE_HAIR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.COARSE)),

	HAIR_REINDEER_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.COARSE)),

	HAIR_BOVINE_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.COARSE)),

	HAIR_SQUIRREL_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)),

	HAIR_RAT_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)),

	HAIR_RABBIT_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)),
	
	HAIR_BAT_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)),
	
	HAIR_HARPY("a plume of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			CoveringPattern.allHairCoveringPatterns,
			null,
			Colour.naturalFeatherColours,
			Colour.dyeFeatherColours,
			Colour.naturalFeatherColours,
			Colour.dyeFeatherColours),
	
	HAIR_SCALES_ALLIGATOR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.COARSE)), //Why do alligators have hair?!
	
	
	// BODY HAIR:
	
	BODY_HAIR_HUMAN(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.COARSE)),

	BODY_HAIR_ANGEL(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.SILKEN)),

	BODY_HAIR_DEMON(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.SILKEN)),

	BODY_HAIR_CANINE_FUR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)),

	BODY_HAIR_LYCAN_FUR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)),

	
	BODY_HAIR_FOX_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.allCoveringColours,
			Colour.naturalHairColours,
			Colour.allCoveringColours),

	BODY_HAIR_FELINE_FUR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)),

	BODY_HAIR_HORSE_HAIR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.COARSE)),

	BODY_HAIR_REINDEER_HAIR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.COARSE)),

	BODY_HAIR_BOVINE_FUR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.COARSE)),

	BODY_HAIR_SQUIRREL_FUR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)),

	BODY_HAIR_RAT_FUR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)),

	BODY_HAIR_RABBIT_FUR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)),
	
	BODY_HAIR_BAT_FUR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)),
	
	BODY_HAIR_HARPY("a plume of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(CoveringModifier.FLUFFY),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalFeatherColours,
			Colour.dyeFeatherColours,
			Colour.naturalFeatherColours,
			Colour.dyeFeatherColours),

	BODY_HAIR_SCALES_ALLIGATOR("a crest of",
			false,
			"scales",
			"scale",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			null,
			CoveringPattern.allStandardCoveringPatterns,
			Colour.naturalScaleColours,
			Colour.allCoveringColours,
			Colour.naturalScaleColours,
			Colour.allCoveringColours),


	
	// EYES:
	
	EYE_HUMAN(BodyCoveringTemplateFactory.createEyeIrisesHeterochromiaNaturallyOccurring()),

	EYE_ANGEL(BodyCoveringTemplateFactory.createEyeIrises()),

	EYE_DEMON_COMMON(BodyCoveringTemplateFactory.createEyeIrisesWithCustomColors(
			Colour.naturalDemonIrisColours, Colour.dyeDemonIrisColours, true)),

	EYE_DOG_MORPH(BodyCoveringTemplateFactory.createEyeIrisesHeterochromiaNaturallyOccurring()),

	EYE_LYCAN(BodyCoveringTemplateFactory.createEyeIrisesWithCustomColors(
			Colour.naturalPredatorIrisColours, Colour.dyePredatorIrisColours, true)),

	
	EYE_FOX_MORPH("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_IRISES, 1)),
			Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC, 1)),
			Colour.naturalPredatorIrisColours,
			Colour.dyePredatorIrisColours,
			Colour.naturalPredatorIrisColours,
			Colour.dyePredatorIrisColours),

	EYE_FELINE(BodyCoveringTemplateFactory.createEyeIrisesWithCustomColors(
			Colour.naturalPredatorIrisColours, Colour.dyePredatorIrisColours, true)),

	EYE_SQUIRREL(BodyCoveringTemplateFactory.createEyeIrises()),

	EYE_RAT(BodyCoveringTemplateFactory.createEyeIrises()),

	EYE_RABBIT(BodyCoveringTemplateFactory.createEyeIrises()),
	
	EYE_BAT(BodyCoveringTemplateFactory.createEyeIrises()),
	
	EYE_ALLIGATOR_MORPH(BodyCoveringTemplateFactory.createEyeIrises()),

	EYE_HORSE_MORPH(BodyCoveringTemplateFactory.createEyeIrisesHeterochromiaNaturallyOccurring()),

	EYE_REINDEER_MORPH(BodyCoveringTemplateFactory.createEyeIrises()),

	EYE_COW_MORPH(BodyCoveringTemplateFactory.createEyeIrisesHeterochromiaNaturallyOccurring()),

	EYE_HARPY(BodyCoveringTemplateFactory.createEyeIrises()),

	EYE_PUPILS("a pair of",
			true,
			"pupils",
			"pupil",
			Util.newArrayListOfValues(CoveringModifier.EYE),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_PUPILS, 1)),
			Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_PUPILS_HETEROCHROMATIC, 1)),
			Colour.naturalPupilColours,
			Colour.dyePupilColours,
			Colour.naturalPupilColours,
			Colour.dyePupilColours),

	EYE_SCLERA("a pair of",
			true,
			"sclerae",
			"sclera",
			Util.newArrayListOfValues(CoveringModifier.EYE),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_SCLERA, 1)),
			Util.newHashMapOfValues(new Value<>(CoveringPattern.EYE_SCLERA_HETEROCHROMATIC, 1)),
			Colour.naturalScleraColours,
			Colour.dyeScleraColours,
			Colour.naturalScleraColours,
			Colour.dyeScleraColours),
	
	// Fluids:
	
	CUM("",
			false,
			"cum",
			"cum",
			Util.newArrayListOfValues(CoveringModifier.FLUID),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.FLUID, 1)),
			null,
			Util.newArrayListOfValues(Colour.COVERING_WHITE),
			Util.newArrayListOfValues(
					Colour.COVERING_CLEAR,
					Colour.COVERING_BROWN,
					Colour.COVERING_BLACK,
					Colour.COVERING_RED,
					Colour.COVERING_BLUE,
					Colour.COVERING_PURPLE,
					Colour.COVERING_GREEN),
			null,
			null),
	
	GIRL_CUM("",
			false,
			"girlcum",
			"girlcum",
			Util.newArrayListOfValues(CoveringModifier.FLUID),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.FLUID, 1)),
			null,
			Util.newArrayListOfValues(Colour.COVERING_CLEAR),
			Util.newArrayListOfValues(
					Colour.COVERING_WHITE,
					Colour.COVERING_BROWN,
					Colour.COVERING_BLACK,
					Colour.COVERING_RED,
					Colour.COVERING_BLUE,
					Colour.COVERING_PURPLE,
					Colour.COVERING_GREEN),
			null,
			null),
	
	MILK("",
			false,
			"milk",
			"milk",
			Util.newArrayListOfValues(CoveringModifier.FLUID),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.FLUID, 1)),
			null,
			Util.newArrayListOfValues(Colour.COVERING_WHITE),
			Util.newArrayListOfValues(
					Colour.COVERING_CLEAR,
					Colour.COVERING_BROWN,
					Colour.COVERING_BLACK,
					Colour.COVERING_RED,
					Colour.COVERING_BLUE,
					Colour.COVERING_PURPLE,
					Colour.COVERING_GREEN),
			null,
			null),
	
	// Makeup:
	
	MAKEUP_BLUSHER("a layer of",
			false,
			"blusher",
			"blusher",
			Util.newArrayListOfValues(CoveringModifier.MAKEUP),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			null,
			Util.newArrayListOfValues(Colour.COVERING_NONE),
			Colour.allMakeupColours,
			null,
			null),
	
	MAKEUP_EYE_LINER("a layer of",
			false,
			"eye liner",
			"eye liner",
			Util.newArrayListOfValues(CoveringModifier.MAKEUP),
			null,
			Util.newHashMapOfValues(new Value<>(CoveringPattern.NONE, 1)),
			null,
			Util.newArrayListOfValues(Colour.COVERING_NONE),
			Colour.allMakeupColours,
			null,
			null),
	
	MAKEUP_EYE_SHADOW("a layer of",
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
			Util.newArrayListOfValues(Colour.COVERING_NONE),
			Colour.allMakeupColours,
			null,
			null),

	MAKEUP_LIPSTICK("a layer of",
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
			Util.newArrayListOfValues(Colour.COVERING_NONE),
			Colour.allMakeupColours,
			null,
			Colour.allMakeupColours),
	
	MAKEUP_NAIL_POLISH_HANDS("a layer of",
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
			Util.newArrayListOfValues(Colour.COVERING_NONE),
			Colour.allMakeupColours,
			null,
			Colour.allMakeupColours),
	
	MAKEUP_NAIL_POLISH_FEET("a layer of",
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
			Util.newArrayListOfValues(Colour.COVERING_NONE),
			Colour.allMakeupColours,
			null,
			Colour.allMakeupColours);
	
	private String determiner; 
	private String namePlural;
	private String nameSingular;
	private List<CoveringModifier> naturalModifiers;
	private List<CoveringModifier> extraModifiers;
	private List<Colour> naturalColoursPrimary;
	private List<Colour> dyeColoursPrimary;
	private List<Colour> naturalColoursSecondary;
	private List<Colour> dyeColoursSecondary;
	private List<Colour> allColours;
	private List<Colour> allPrimaryColours;
	private List<Colour> allSecondaryColours;
	private Map<CoveringPattern, Integer> naturalPatterns;
	private Map<CoveringPattern, Integer> dyePatterns;
	private Map<CoveringPattern, Integer> allPatterns;
	private boolean isDefaultPlural;
	
	private BodyCoveringType(BodyCoveringTemplate template) {
		determiner = template.determiner;
		namePlural = template.namePlural;
		nameSingular = template.nameSingular;
		naturalModifiers = template.naturalModifiers;
		extraModifiers = template.extraModifiers;
		naturalColoursPrimary = template.naturalColoursPrimary;
		dyeColoursPrimary = template.dyeColoursPrimary;
		naturalColoursSecondary = template.naturalColoursSecondary;
		dyeColoursSecondary = template.dyeColoursSecondary;
		naturalPatterns = template.naturalPatterns;
		dyePatterns = template.dyePatterns;
		isDefaultPlural = template.isDefaultPlural;
		
		allPatterns = new HashMap<>();
		for(Entry<CoveringPattern, Integer> entry : this.naturalPatterns.entrySet()) {
			this.allPatterns.put(entry.getKey(), entry.getValue());
		}
		if(dyePatterns!=null) {
			for(Entry<CoveringPattern, Integer> entry : this.dyePatterns.entrySet()) {
				this.allPatterns.put(entry.getKey(), entry.getValue());
			}
		}
		
//		allColours = new ArrayList<>();
//		allColours.addAll(naturalColoursPrimary);
//		allColours.addAll(dyeColoursPrimary);
//		allColours.addAll(naturalColoursSecondary);
//		allColours.addAll(dyeColoursSecondary);
//		
//		allPrimaryColours = new ArrayList<>();
//		allPrimaryColours.addAll(naturalColoursPrimary);
//		allPrimaryColours.addAll(dyeColoursPrimary);
//		
//		allSecondaryColours = new ArrayList<>();
//		allSecondaryColours.addAll(naturalColoursSecondary);
//		allSecondaryColours.addAll(dyeColoursSecondary);
		
		allColours = new ArrayList<>();
		allPrimaryColours = new ArrayList<>();
		allSecondaryColours = new ArrayList<>();
		for(Colour c : this.naturalColoursPrimary) {
			allColours.add(c);
			allPrimaryColours.add(c);
		}
		for(Colour c : this.dyeColoursPrimary) {
			if(!allColours.contains(c)) {
				allColours.add(c);
			}
			if(!allPrimaryColours.contains(c)) {
				allPrimaryColours.add(c);
			}
		}
		for(Colour c : this.naturalColoursSecondary) {
			allColours.add(c);
			allSecondaryColours.add(c);
		}
		for(Colour c : this.dyeColoursSecondary) {
			if(!allColours.contains(c)) {
				allColours.add(c);
			}
			if(!allSecondaryColours.contains(c)) {
				allSecondaryColours.add(c);
			}
		}
	}

	private BodyCoveringType(
			String determiner,
			boolean isDefaultPlural,
			String namePlural,
			String nameSingular,
			List<CoveringModifier> naturalModifiers,
			List<CoveringModifier> extraModifiers,
			Map<CoveringPattern, Integer> naturalPatterns,
			Map<CoveringPattern, Integer> dyePatterns,
			List<Colour> naturalColoursPrimary,
			List<Colour> dyeColoursPrimary,
			List<Colour> naturalColoursSecondary,
			List<Colour> dyeColoursSecondary) {
		
		this.determiner = determiner;
		this.namePlural = namePlural;
		this.nameSingular=nameSingular;
		this.isDefaultPlural = isDefaultPlural;
		
		if(naturalModifiers == null) {
			this.naturalModifiers = new ArrayList<>();
		} else {
			this.naturalModifiers = naturalModifiers;
		}
		
		if(extraModifiers == null) {
			this.extraModifiers = new ArrayList<>();
		} else {
			this.extraModifiers = extraModifiers;
		}
		
		if(naturalPatterns == null) {
			this.naturalPatterns = new HashMap<>();
			this.naturalPatterns.put(CoveringPattern.NONE, 1);
		} else {
			this.naturalPatterns = naturalPatterns;
		}

		this.dyePatterns = new HashMap<>();
		if(dyePatterns != null) {
			this.dyePatterns = new HashMap<>();
			for(Entry<CoveringPattern, Integer> entry : dyePatterns.entrySet()) {
				if(!this.naturalPatterns.containsKey(entry.getKey())) {
					this.dyePatterns.put(entry.getKey(), entry.getValue());
				}
			}
		}
		
		allPatterns = new HashMap<>();
		if(naturalPatterns == null) {
			allPatterns.put(CoveringPattern.NONE, 1);
		} else {
			for(Entry<CoveringPattern, Integer> entry : this.naturalPatterns.entrySet()) {
				this.allPatterns.put(entry.getKey(), entry.getValue());
			}
		}
		
		if(dyePatterns != null) {
			for(Entry<CoveringPattern, Integer> entry : this.dyePatterns.entrySet()) {
				if(!this.allPatterns.containsKey(entry.getKey())) {
					this.allPatterns.put(entry.getKey(), entry.getValue());
				}
			}
		}
		
		
		if(naturalColoursPrimary == null) {
			this.naturalColoursPrimary = new ArrayList<>();
		} else {
			this.naturalColoursPrimary = naturalColoursPrimary;
		}
		if(dyeColoursPrimary == null) {
			this.dyeColoursPrimary = new ArrayList<>();
		} else {
			this.dyeColoursPrimary = dyeColoursPrimary;
		}
		
		if(naturalColoursSecondary == null) {
			this.naturalColoursSecondary = new ArrayList<>();
		} else {
			this.naturalColoursSecondary = naturalColoursSecondary;
		}
		if(dyeColoursSecondary == null) {
			this.dyeColoursSecondary = new ArrayList<>();
		} else {
			this.dyeColoursSecondary = dyeColoursSecondary;
		}
		
		allColours = new ArrayList<>();
		allPrimaryColours = new ArrayList<>();
		allSecondaryColours = new ArrayList<>();
		for(Colour c : this.naturalColoursPrimary) {
			allColours.add(c);
			allPrimaryColours.add(c);
		}
		for(Colour c : this.dyeColoursPrimary) {
			if(!allColours.contains(c)) {
				allColours.add(c);
			}
			if(!allPrimaryColours.contains(c)) {
				allPrimaryColours.add(c);
			}
		}
		for(Colour c : this.naturalColoursSecondary) {
			allColours.add(c);
			allSecondaryColours.add(c);
		}
		for(Colour c : this.dyeColoursSecondary) {
			if(!allColours.contains(c)) {
				allColours.add(c);
			}
			if(!allSecondaryColours.contains(c)) {
				allSecondaryColours.add(c);
			}
		}
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static BodyCoveringType getTypeFromString(String value) {
		if(value.equals("IMP")) {
			value = "DEMON_COMMON";
			
		} else if(value.equals("HAIR_IMP")) {
			value = "HAIR_DEMON";
			
		} else if(value.equals("BODY_HAIR_IMP")) {
			value = "BODY_HAIR_DEMON";
			
		} else if(value.equals("EYE_IMP")) {
			value = "EYE_DEMON_COMMON";
		}
		return valueOf(value);
	}
	
	public String getDeterminer(GameCharacter gc) {
		return determiner;
	}

	public boolean isDefaultPlural() {
		return isDefaultPlural;
	}
	
	public String getNameSingular(GameCharacter gc) {
		return nameSingular;
	}
	
	public String getNamePlural(GameCharacter gc) {
		return namePlural;
	}
	
	public String getName(GameCharacter gc){
		if(isDefaultPlural()) {
			return getNamePlural(gc);
		} else {
			return getNameSingular(gc);
		}
	}
	
	public Map<CoveringPattern, Integer> getNaturalPatterns() {
		return naturalPatterns;
	}

	public Map<CoveringPattern, Integer> getDyePatterns() {
		return dyePatterns;
	}

	public Map<CoveringPattern, Integer> getAllPatterns() {
		return allPatterns;
	}
	
	public List<Colour> getNaturalColoursPrimary() {
		return naturalColoursPrimary;
	}

	public List<Colour> getDyeColoursPrimary() {
		return dyeColoursPrimary;
	}

	public List<Colour> getNaturalColoursSecondary() {
		return naturalColoursSecondary;
	}

	public List<Colour> getDyeColoursSecondary() {
		return dyeColoursSecondary;
	}

	public List<Colour> getAllColours() {
		return allColours;
	}
	
	public List<Colour> getAllPrimaryColours() {
		return allPrimaryColours;
	}
	
	public List<Colour> getAllSecondaryColours() {
		return allSecondaryColours;
	}

	public BodyCoveringType getBodyCoveringType() {
		return this;
	}

	public List<CoveringModifier> getNaturalModifiers() {
		return naturalModifiers;
	}

	public List<CoveringModifier> getExtraModifiers() {
		return extraModifiers;
	}
}