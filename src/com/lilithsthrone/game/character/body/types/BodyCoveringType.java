package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.ColourListPresets;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.4
 * @author Innoxia
 */
public enum BodyCoveringType {

	// Skin shades go light->dark

	HUMAN("a layer of",
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE,
					CoveringPattern.FRECKLED),
			CoveringPattern.allStandardCoveringPatterns,
			Colour.humanSkinColours,
			null,
			Colour.humanSkinColours,
			null),

	ANGEL("a layer of",
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(CoveringPattern.NONE),
			CoveringPattern.allStandardCoveringPatterns,
			Colour.humanSkinColours,
			null,
			Colour.humanSkinColours,
			null),

	ANGEL_FEATHER("a layer of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Util.newArrayListOfValues(Colour.FEATHERS_WHITE),
			Colour.allFeatherColours,
			Util.newArrayListOfValues(Colour.FEATHERS_WHITE),
			Colour.allFeatherColours),
	
	DEMON_COMMON("a layer of",
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allStandardCoveringPatterns,
			Colour.demonSkinColours,
			null,
			Colour.demonSkinColours,
			null),

	IMP("a layer of",
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allStandardCoveringPatterns,
			Colour.demonSkinColours,
			null,
			Colour.demonSkinColours,
			null),

	BAT_SKIN("a layer of",
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allStandardCoveringPatterns,
			Colour.humanSkinColours,
			Colour.allSkinColours,
			null,
			Colour.allSkinColours),
	
	BAT_FUR("a layer of",
			false,
			"fur",
			"fur",
			Util.newArrayListOfValues(
					CoveringModifier.SHORT),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),
	
	CANINE_FUR("a layer of",
			false,
			"fur",
			"fur",
			Util.newArrayListOfValues(
					CoveringModifier.FLUFFY,
					CoveringModifier.SHORT,
					CoveringModifier.SHAGGY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE,
					CoveringPattern.MARKED,
					CoveringPattern.SPOTTED),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),
	
	LYCAN_FUR("a layer of",
			false,
			"fur",
			"fur",
			Util.newArrayListOfValues(
					CoveringModifier.SHAGGY),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),

	FELINE_FUR("a layer of",
			false,
			"fur",
			"fur",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH,
					CoveringModifier.FLUFFY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE,
					CoveringPattern.MOTTLED,
					CoveringPattern.SPOTTED,
					CoveringPattern.MARKED,
					CoveringPattern.STRIPED,
					CoveringPattern.HIGHLIGHTS),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),

	SQUIRREL_FUR("a layer of",
			false,
			"fur",
			"fur",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),

	RAT_SKIN("a layer of",
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allStandardCoveringPatterns,
			Colour.ratSkinColours,
			Colour.allSkinColours,
			null,
			Colour.allSkinColours),
	
	RAT_FUR("a layer of",
			false,
			"fur",
			"fur",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),

	RABBIT_FUR("a layer of",
			false,
			"fur",
			"fur",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),
	
	HORSE_HAIR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.SHORT),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE,
					CoveringPattern.MOTTLED,
					CoveringPattern.SPOTTED,
					CoveringPattern.MARKED,
					CoveringPattern.STRIPED),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),
	
	REINDEER_FUR("a layer of",
			false,
			"fur",
			"fur",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),
	
	BOVINE_FUR("a layer of",
			false,
			"fur",
			"fur",
			Util.newArrayListOfValues(
					CoveringModifier.SHORT,
					CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE,
					CoveringPattern.MOTTLED,
					CoveringPattern.SPOTTED,
					CoveringPattern.MARKED),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),
	
	DILDO("a layer of", // This colour is set in GameCharacter's getCovering method, based on the colour of the dildo equipped.
			false,
			"silicone",
			"silicone",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			null,
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null),
	
	PENIS("a layer of",
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			null,
			null,
			Colour.allSkinColours,
			null,
			Util.newArrayListOfValues(
					Colour.ORIFICE_INTERIOR),
			Colour.allSkinColours),

	ANUS("a layer of",
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.ORIFICE_ANUS),
			null,
			Colour.allSkinColours,
			null,
			Util.newArrayListOfValues(
					Colour.ORIFICE_INTERIOR),
			Colour.allSkinColours),
	
	MOUTH("a layer of",
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.ORIFICE_MOUTH),
			null,
			Colour.allSkinColours,
			null,
			Util.newArrayListOfValues(
					Colour.ORIFICE_INTERIOR),
			Colour.allSkinColours),
	
	NIPPLES("a layer of",
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.ORIFICE_NIPPLE),
			null,
			Colour.allSkinColours,
			null,
			Util.newArrayListOfValues(
					Colour.ORIFICE_INTERIOR),
			Colour.allSkinColours),
	
	VAGINA("a layer of",
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.ORIFICE_VAGINA),
			null,
			Colour.allSkinColours,
			null,
			Util.newArrayListOfValues(
					Colour.ORIFICE_INTERIOR),
			Colour.allSkinColours),
	

	FIRE("",
			false,
			"flames",
			"flames",
			Util.newArrayListOfValues(
					CoveringModifier.BLAZING),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_ORANGE,
					Colour.COVERING_BLUE_LIGHT),
			null,
			null,
			null),
	FIRE_HAIR("",
			false,
			"flames",
			"flames",
			Util.newArrayListOfValues(
					CoveringModifier.BLAZING),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_ORANGE,
					Colour.COVERING_BLUE_LIGHT),
			null,
			null,
			null),
	
	WATER("",
			false,
			"water",
			"water",
			Util.newArrayListOfValues(
					CoveringModifier.SHIMMERING),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_BLUE,
					Colour.COVERING_BLUE_LIGHT),
			null,
			null,
			null),
	WATER_HAIR("",
			false,
			"water",
			"water",
			Util.newArrayListOfValues(
					CoveringModifier.SHIMMERING),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_BLUE,
					Colour.COVERING_BLUE_LIGHT),
			null,
			null,
			null),

	ICE("",
			false,
			"ice",
			"ice",
			Util.newArrayListOfValues(
					CoveringModifier.SHIMMERING),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_BLUE_LIGHT),
			null,
			null,
			null),
	ICE_HAIR("",
			false,
			"ice",
			"ice",
			Util.newArrayListOfValues(
					CoveringModifier.SHIMMERING),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_BLUE_LIGHT),
			null,
			null,
			null),

	AIR("",
			false,
			"vapours",
			"vapours",
			Util.newArrayListOfValues(
					CoveringModifier.SWIRLING),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_BLUE_LIGHT),
			null,
			null,
			null),
	AIR_HAIR("",
			false,
			"vapours",
			"vapours",
			Util.newArrayListOfValues(
					CoveringModifier.SWIRLING),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_BLUE_LIGHT),
			null,
			null,
			null),

	STONE("",
			false,
			"stone",
			"stone",
			Util.newArrayListOfValues(
					CoveringModifier.MATTE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_GREY),
			null,
			null,
			null),
	STONE_HAIR("",
			false,
			"stone",
			"stone",
			Util.newArrayListOfValues(
					CoveringModifier.MATTE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_GREY),
			null,
			null,
			null),

	RUBBER("",
			false,
			"rubber",
			"rubber",
			Util.newArrayListOfValues(
					CoveringModifier.GLOSSY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_BLACK),
			null,
			null,
			null),
	RUBBER_HAIR("",
			false,
			"rubber",
			"rubber",
			Util.newArrayListOfValues(
					CoveringModifier.GLOSSY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_BLACK),
			null,
			null,
			null),

	ARCANE("",
			false,
			"energy",
			"energy",
			Util.newArrayListOfValues(
					CoveringModifier.SWIRLING),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_PINK),
			null,
			null,
			null),
	ARCANE_HAIR("",
			false,
			"energy",
			"energy",
			Util.newArrayListOfValues(
					CoveringModifier.SWIRLING),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_PINK),
			null,
			null,
			null),
	
	
	SLIME("a layer of",
			false,
			"slime",
			"slime",
			Util.newArrayListOfValues(
					CoveringModifier.GOOEY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allStandardCoveringPatterns,
			Colour.allSlimeColours,
			null,
			Colour.allSlimeColours,
			null),

	SLIME_EYE("a layer of",
			false,
			"slime",
			"slime",
			Util.newArrayListOfValues(
					CoveringModifier.GOOEY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Colour.allSlimeColours,
			null,
			Colour.allSlimeColours,
			null),
	
	SLIME_PUPILS("a layer of",
			false,
			"slime",
			"slime",
			Util.newArrayListOfValues(
					CoveringModifier.GOOEY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_PUPILS),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_PUPILS_HETEROCHROMATIC),
			Colour.allSlimeColours,
			null,
			Colour.allSlimeColours,
			null),
	
	SLIME_SCLERA("a layer of",
			false,
			"slime",
			"slime",
			Util.newArrayListOfValues(
					CoveringModifier.GOOEY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_SCLERA),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_SCLERA_HETEROCHROMATIC),
			Colour.allSlimeColours,
			null,
			Colour.allSlimeColours,
			null),
	
	SLIME_HAIR("a layer of",
			false,
			"slime",
			"slime",
			Util.newArrayListOfValues(
					CoveringModifier.GOOEY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.allSlimeColours,
			null,
			Colour.allSlimeColours,
			null),
	
	SLIME_ANUS("a layer of",
			false,
			"slime",
			"slime",
			Util.newArrayListOfValues(
					CoveringModifier.GOOEY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.ORIFICE_ANUS),
			null,
			Colour.allSlimeColours,
			null,
			Colour.allSlimeColours,
			null),
	
	SLIME_MOUTH("a layer of",
			false,
			"slime",
			"slime",
			Util.newArrayListOfValues(
					CoveringModifier.GOOEY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.ORIFICE_MOUTH),
			null,
			Colour.allSlimeColours,
			null,
			Colour.allSlimeColours,
			null),
	
	SLIME_NIPPLES("a layer of",
			false,
			"slime",
			"slime",
			Util.newArrayListOfValues(
					CoveringModifier.GOOEY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.ORIFICE_NIPPLE),
			null,
			Colour.allSlimeColours,
			null,
			Colour.allSlimeColours,
			null),
	
	SLIME_VAGINA("a layer of",
			false,
			"slime",
			"slime",
			Util.newArrayListOfValues(
					CoveringModifier.GOOEY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.ORIFICE_VAGINA),
			null,
			Colour.allSlimeColours,
			null,
			Colour.allSlimeColours,
			null),

	FEATHERS("a layer of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE,
					CoveringPattern.MOTTLED,
					CoveringPattern.SPOTTED,
					CoveringPattern.MARKED,
					CoveringPattern.STRIPED),
			CoveringPattern.allHairCoveringPatterns,
			Colour.allFeatherColours,
			null,
			Colour.allFeatherColours,
			null),

	ALLIGATOR_SCALES("a layer of",
			true,
			"scales",
			"scale",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allScalesCoveringPatterns,
			Colour.naturalScaleColours,
			Colour.dyeScaleColours,
			Colour.naturalScaleColours,
			Colour.dyeScaleColours),

	// MISC:
	
	HORN("a layer of",
			false,
			"keratin",
			"keratin",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			null,
			CoveringPattern.allScalesCoveringPatterns,
			Colour.hornColours,
			Colour.dyeHornColours,
			Colour.hornColours,
			Colour.dyeHornColours),

	ANTLER_REINDEER("a layer of",
			false,
			"velvet",
			"velvet",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			null,
			CoveringPattern.allScalesCoveringPatterns,
			Colour.antlerColours,
			Colour.dyeAntlerColours,
			Colour.antlerColours,
			Colour.dyeAntlerColours),

	TONGUE("a layer of",
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			null,
			CoveringPattern.allStandardCoveringPatterns,
			Util.newArrayListOfValues(
					Colour.ORIFICE_INTERIOR),
			Colour.allSkinColours,
			Util.newArrayListOfValues(
					Colour.ORIFICE_INTERIOR),
			Colour.allSkinColours),

	// HAIR:

	HAIR_HUMAN("a head of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_ANGEL("a head of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.SILKEN),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_DEMON("a head of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.SILKEN),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_IMP("a head of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.SILKEN),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),
	
	HAIR_CANINE_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_LYCAN_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_FELINE_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_HORSE_HAIR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.COARSE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_REINDEER_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.COARSE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_BOVINE_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.COARSE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_SQUIRREL_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_RAT_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_RABBIT_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),
	
	HAIR_BAT_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),
	
	HAIR_HARPY("a plume of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			CoveringPattern.allHairCoveringPatterns,
			null,
			Colour.allFeatherColours,
			null,
			Colour.allFeatherColours,
			null),
	
	HAIR_SCALES_ALLIGATOR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.COARSE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),
	
	
	// BODY HAIR:
	
	BODY_HAIR_HUMAN("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.COARSE),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_ANGEL("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.SILKEN),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_DEMON("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.SILKEN),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_IMP("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.SILKEN),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_CANINE_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_LYCAN_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_FELINE_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_HORSE_HAIR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.COARSE),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_REINDEER_HAIR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.COARSE),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_BOVINE_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.COARSE),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_SQUIRREL_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_RAT_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_RABBIT_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),
	
	BODY_HAIR_BAT_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),
	
	BODY_HAIR_HARPY("a plume of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(
					CoveringModifier.FLUFFY),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Colour.allFeatherColours,
			null,
			Colour.allFeatherColours,
			null),

	BODY_HAIR_SCALES_ALLIGATOR("a plume of",
			false,
			"scales",
			"scale",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH),
			null,
			null,
			CoveringPattern.allStandardCoveringPatterns,
			Colour.naturalScaleColours,
			Colour.dyeScaleColours,
			Colour.naturalScaleColours,
			Colour.dyeScaleColours),


	
	// EYES:

	EYE_HUMAN("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES,
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			null,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_ANGEL("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_DEMON_COMMON("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Colour.naturalDemonIrisColours,
			Colour.dyeDemonIrisColours,
			Colour.naturalDemonIrisColours,
			Colour.dyeDemonIrisColours),

	EYE_IMP("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Colour.naturalDemonIrisColours,
			Colour.dyeDemonIrisColours,
			Colour.naturalDemonIrisColours,
			Colour.dyeDemonIrisColours),
	
	EYE_DOG_MORPH("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_LYCAN("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Colour.naturalPredatorIrisColours,
			Colour.dyePredatorIrisColours,
			Colour.naturalPredatorIrisColours,
			Colour.dyePredatorIrisColours),

	EYE_FELINE("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Colour.naturalPredatorIrisColours,
			Colour.dyePredatorIrisColours,
			Colour.naturalPredatorIrisColours,
			Colour.dyePredatorIrisColours),

	EYE_SQUIRREL("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_RAT("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_RABBIT("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),
	
	EYE_BAT("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),
	
	EYE_ALLIGATOR_MORPH("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_HORSE_MORPH("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_REINDEER_MORPH("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_COW_MORPH("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_HARPY("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_PUPILS("a pair of",
			true,
			"pupils",
			"pupil",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_PUPILS),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_PUPILS_HETEROCHROMATIC),
			Colour.naturalPupilColours,
			Colour.dyePupilColours,
			Colour.naturalPupilColours,
			Colour.dyePupilColours),

	EYE_SCLERA("a pair of",
			true,
			"sclerae",
			"sclera",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_SCLERA),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_SCLERA_HETEROCHROMATIC),
			Colour.naturalScleraColours,
			Colour.dyeScleraColours,
			Colour.naturalScleraColours,
			Colour.dyeScleraColours),
	
	// Fluids:
	
	CUM("",
			false,
			"cum",
			"cum",
			Util.newArrayListOfValues(
					CoveringModifier.FLUID),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.FLUID),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_WHITE),
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
			Util.newArrayListOfValues(
					CoveringModifier.FLUID),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.FLUID),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_CLEAR),
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
			Util.newArrayListOfValues(
					CoveringModifier.FLUID),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.FLUID),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_WHITE),
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
			Util.newArrayListOfValues(
					CoveringModifier.MAKEUP),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_NONE),
			Util.newArrayListOfValues(
					Colour.COVERING_RED,
					Colour.COVERING_RED_DARK,
					Colour.COVERING_ORANGE,
					Colour.COVERING_BROWN,
					Colour.COVERING_GREEN,
					Colour.COVERING_GREEN_DARK,
					Colour.COVERING_BLUE,
					Colour.COVERING_BLUE_DARK,
					Colour.COVERING_PURPLE,
					Colour.COVERING_PURPLE_DARK,
					Colour.COVERING_PINK,
					Colour.COVERING_PINK_DARK,
					Colour.COVERING_WHITE,
					Colour.COVERING_SILVER,
					Colour.COVERING_BLACK),
			null,
			null),
	
	MAKEUP_EYE_LINER("a layer of",
			false,
			"eye liner",
			"eye liner",
			Util.newArrayListOfValues(
					CoveringModifier.MAKEUP),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_NONE),
			Util.newArrayListOfValues(
					Colour.COVERING_RED,
					Colour.COVERING_RED_DARK,
					Colour.COVERING_ORANGE,
					Colour.COVERING_BROWN,
					Colour.COVERING_GREEN,
					Colour.COVERING_GREEN_DARK,
					Colour.COVERING_BLUE,
					Colour.COVERING_BLUE_DARK,
					Colour.COVERING_PURPLE,
					Colour.COVERING_PURPLE_DARK,
					Colour.COVERING_PINK,
					Colour.COVERING_PINK_DARK,
					Colour.COVERING_WHITE,
					Colour.COVERING_SILVER,
					Colour.COVERING_BLACK),
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
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_NONE),
			Util.newArrayListOfValues(
					Colour.COVERING_RED,
					Colour.COVERING_RED_DARK,
					Colour.COVERING_ORANGE,
					Colour.COVERING_BROWN,
					Colour.COVERING_GREEN,
					Colour.COVERING_GREEN_DARK,
					Colour.COVERING_BLUE,
					Colour.COVERING_BLUE_DARK,
					Colour.COVERING_PURPLE,
					Colour.COVERING_PURPLE_DARK,
					Colour.COVERING_PINK,
					Colour.COVERING_PINK_DARK,
					Colour.COVERING_WHITE,
					Colour.COVERING_SILVER,
					Colour.COVERING_BLACK),
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
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			Util.newArrayListOfValues(
					CoveringPattern.SPOTTED,
					CoveringPattern.STRIPED),
			Util.newArrayListOfValues(
					Colour.COVERING_NONE),
			Util.newArrayListOfValues(
					Colour.COVERING_CLEAR,
					Colour.COVERING_RED,
					Colour.COVERING_RED_DARK,
					Colour.COVERING_ORANGE,
					Colour.COVERING_BROWN,
					Colour.COVERING_GREEN,
					Colour.COVERING_GREEN_DARK,
					Colour.COVERING_BLUE,
					Colour.COVERING_BLUE_DARK,
					Colour.COVERING_PURPLE,
					Colour.COVERING_PURPLE_DARK,
					Colour.COVERING_PINK,
					Colour.COVERING_PINK_DARK,
					Colour.COVERING_WHITE,
					Colour.COVERING_SILVER,
					Colour.COVERING_BLACK),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_CLEAR,
					Colour.COVERING_RED,
					Colour.COVERING_RED_DARK,
					Colour.COVERING_ORANGE,
					Colour.COVERING_BROWN,
					Colour.COVERING_GREEN,
					Colour.COVERING_GREEN_DARK,
					Colour.COVERING_BLUE,
					Colour.COVERING_BLUE_DARK,
					Colour.COVERING_PURPLE,
					Colour.COVERING_PURPLE_DARK,
					Colour.COVERING_PINK,
					Colour.COVERING_PINK_DARK,
					Colour.COVERING_WHITE,
					Colour.COVERING_SILVER,
					Colour.COVERING_BLACK)),
	
	MAKEUP_NAIL_POLISH_HANDS("a layer of",
			false,
			"nail polish",
			"nail polish",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH,
					CoveringModifier.SPARKLY,
					CoveringModifier.METALLIC),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			Util.newArrayListOfValues(
					CoveringPattern.SPOTTED,
					CoveringPattern.STRIPED),
			Util.newArrayListOfValues(
					Colour.COVERING_NONE),
			Util.newArrayListOfValues(
					Colour.COVERING_CLEAR,
					Colour.COVERING_RED,
					Colour.COVERING_RED_DARK,
					Colour.COVERING_ORANGE,
					Colour.COVERING_BROWN,
					Colour.COVERING_GREEN,
					Colour.COVERING_GREEN_DARK,
					Colour.COVERING_BLUE,
					Colour.COVERING_BLUE_DARK,
					Colour.COVERING_PURPLE,
					Colour.COVERING_PURPLE_DARK,
					Colour.COVERING_PINK,
					Colour.COVERING_PINK_DARK,
					Colour.COVERING_WHITE,
					Colour.COVERING_SILVER,
					Colour.COVERING_BLACK),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_CLEAR,
					Colour.COVERING_RED,
					Colour.COVERING_RED_DARK,
					Colour.COVERING_ORANGE,
					Colour.COVERING_BROWN,
					Colour.COVERING_GREEN,
					Colour.COVERING_GREEN_DARK,
					Colour.COVERING_BLUE,
					Colour.COVERING_BLUE_DARK,
					Colour.COVERING_PURPLE,
					Colour.COVERING_PURPLE_DARK,
					Colour.COVERING_PINK,
					Colour.COVERING_PINK_DARK,
					Colour.COVERING_WHITE,
					Colour.COVERING_SILVER,
					Colour.COVERING_BLACK)),
	
	MAKEUP_NAIL_POLISH_FEET("a layer of",
			false,
			"nail polish",
			"nail polish",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH,
					CoveringModifier.SPARKLY,
					CoveringModifier.METALLIC),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			Util.newArrayListOfValues(
					CoveringPattern.SPOTTED,
					CoveringPattern.STRIPED),
			Util.newArrayListOfValues(
					Colour.COVERING_NONE),
			Util.newArrayListOfValues(
					Colour.COVERING_CLEAR,
					Colour.COVERING_RED,
					Colour.COVERING_RED_DARK,
					Colour.COVERING_ORANGE,
					Colour.COVERING_AMBER,
					Colour.COVERING_BROWN,
					Colour.COVERING_GREEN,
					Colour.COVERING_GREEN_DARK,
					Colour.COVERING_BLUE,
					Colour.COVERING_BLUE_DARK,
					Colour.COVERING_PURPLE,
					Colour.COVERING_PURPLE_DARK,
					Colour.COVERING_PINK,
					Colour.COVERING_PINK_DARK,
					Colour.COVERING_WHITE,
					Colour.COVERING_SILVER,
					Colour.COVERING_BLACK),
			null,
			Util.newArrayListOfValues(
					Colour.COVERING_CLEAR,
					Colour.COVERING_RED,
					Colour.COVERING_RED_DARK,
					Colour.COVERING_ORANGE,
					Colour.COVERING_AMBER,
					Colour.COVERING_BROWN,
					Colour.COVERING_GREEN,
					Colour.COVERING_GREEN_DARK,
					Colour.COVERING_BLUE,
					Colour.COVERING_BLUE_DARK,
					Colour.COVERING_PURPLE,
					Colour.COVERING_PURPLE_DARK,
					Colour.COVERING_PINK,
					Colour.COVERING_PINK_DARK,
					Colour.COVERING_WHITE,
					Colour.COVERING_SILVER,
					Colour.COVERING_BLACK));
	
	private String determiner, namePlural, nameSingular;
	private List<CoveringModifier> naturalModifiers, extraModifiers;
	private List<Colour> naturalColoursPrimary, dyeColoursPrimary, naturalColoursSecondary, dyeColoursSecondary, allColours, allPrimaryColours, allSecondaryColours;
	private List<CoveringPattern> naturalPatterns, dyePatterns, allPatterns;
	private boolean isDefaultPlural;

	private BodyCoveringType(
			String determiner,
			boolean isDefaultPlural,
			String namePlural,
			String nameSingular,
			List<CoveringModifier> naturalModifiers,
			List<CoveringModifier> extraModifiers,
			List<CoveringPattern> naturalPatterns,
			List<CoveringPattern> dyePatterns,
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
			this.naturalPatterns = new ArrayList<>();
			this.naturalPatterns.add(CoveringPattern.NONE);
		} else {
			this.naturalPatterns = naturalPatterns;
		}

		this.dyePatterns = new ArrayList<>();
		if(dyePatterns != null) {
			this.dyePatterns.addAll(dyePatterns);
			this.dyePatterns.removeAll(this.naturalPatterns);
		}
		
		allPatterns = new ArrayList<>();
		if(naturalPatterns == null) {
			allPatterns.add(CoveringPattern.NONE);
		} else {
			allPatterns.addAll(this.naturalPatterns);
		}
		
		if(dyePatterns != null) {
			allPatterns.addAll(this.dyePatterns);
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
//			this.dyeColoursPrimary.remove(c);
		}
		for(Colour c : this.dyeColoursPrimary) {
			allColours.add(c);
			allPrimaryColours.add(c);
		}
		for(Colour c : this.naturalColoursSecondary) {
			allColours.add(c);
			allSecondaryColours.add(c);
//			this.dyeColoursSecondary.remove(c);
		}
		for(Colour c : this.dyeColoursSecondary) {
			allColours.add(c);
			allSecondaryColours.add(c);
		}
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
	
	public List<CoveringPattern> getNaturalPatterns() {
		return naturalPatterns;
	}

	public List<CoveringPattern> getDyePatterns() {
		return dyePatterns;
	}

	public List<CoveringPattern> getAllPatterns() {
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
