package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum BodyCoveringType implements BodyPartTypeInterface {

	// Skin shades go light->dark

	HUMAN(Race.HUMAN,
			"a layer of",
			false,
			"skin",
			"skin",
			"normal",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED)),
			Colour.humanSkinColours,
			null,
			Colour.humanSkinColours,
			null),

	ANGEL(Race.ANGEL,
			"a layer of",
			false,
			"skin",
			"skin",
			"flawless",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED)),
			Colour.humanSkinColours,
			null,
			Colour.humanSkinColours,
			null),

	ANGEL_FEATHER(Race.ANGEL,
			"a layer of",
			true,
			"feathers",
			"feather",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.FEATHERS_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.FEATHERS_WHITE),
					new ListValue<Colour>(Colour.FEATHERS_PINK),
					new ListValue<Colour>(Colour.FEATHERS_YELLOW),
					new ListValue<Colour>(Colour.FEATHERS_ORANGE),
					new ListValue<Colour>(Colour.FEATHERS_GINGER),
					new ListValue<Colour>(Colour.FEATHERS_BLUE),
					new ListValue<Colour>(Colour.FEATHERS_LILAC),
					new ListValue<Colour>(Colour.FEATHERS_GREEN),
					new ListValue<Colour>(Colour.FEATHERS_RED),
					new ListValue<Colour>(Colour.FEATHERS_BLACK)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.FEATHERS_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.FEATHERS_WHITE),
					new ListValue<Colour>(Colour.FEATHERS_PINK),
					new ListValue<Colour>(Colour.FEATHERS_YELLOW),
					new ListValue<Colour>(Colour.FEATHERS_ORANGE),
					new ListValue<Colour>(Colour.FEATHERS_GINGER),
					new ListValue<Colour>(Colour.FEATHERS_BLUE),
					new ListValue<Colour>(Colour.FEATHERS_LILAC),
					new ListValue<Colour>(Colour.FEATHERS_GREEN),
					new ListValue<Colour>(Colour.FEATHERS_RED),
					new ListValue<Colour>(Colour.FEATHERS_BLACK)),
			null),
	
	DEMON_COMMON(Race.DEMON,
			"a layer of",
			false,
			"skin",
			"skin",
			"flawless",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED)),
			Colour.demonSkinColours,
			null,
			Colour.demonSkinColours,
			null),

	CANINE_FUR(Race.DOG_MORPH,
			"a layer of",
			false,
			"fur",
			"fur",
			"shaggy",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED),
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS)),
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),
	
	LYCAN_FUR(Race.WOLF_MORPH,
			"a layer of",
			false,
			"fur",
			"fur",
			"shaggy",
			null,
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED),
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS)),
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),

	FELINE_FUR(Race.CAT_MORPH,
			"a layer of",
			false,
			"fur",
			"fur",
			"smooth",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED),
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS)),
			null,
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),

	SQUIRREL_FUR(Race.SQUIRREL_MORPH,
			"a layer of",
			false,
			"fur",
			"fur",
			"smooth",
			null,
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED),
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS)),
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),
	
	HORSE_HAIR(Race.HORSE_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS)),
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),
	
	REINDEER_FUR(Race.REINDEER_MORPH,
			"a layer of",
			false,
			"fur",
			"fur",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED),
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS)),
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),
	
	BOVINE_FUR(Race.COW_MORPH,
			"a layer of",
			false,
			"fur",
			"fur",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED),
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS)),
			Colour.naturalFurColours,
			Colour.dyeFurColours,
			Colour.naturalFurColours,
			Colour.dyeFurColours),
	
	PENIS(Race.HUMAN,
			"a layer of",
			false,
			"skin",
			"skin",
			"soft",
			null,
			null,
			Colour.allSkinColours,
			null,
			null,
			null),

	ANUS(Race.HUMAN,
			"a layer of",
			false,
			"skin",
			"skin",
			"soft",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.ORIFICE_ANUS)),
			null,
			Colour.allSkinColours,
			null,
			Colour.orificeInteriors,
			null),
	
	ANUS_SLIME(Race.SLIME,
			"a layer of",
			false,
			"slime",
			"slime",
			"soft",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.ORIFICE_ANUS)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.SLIME_PINK),
					new ListValue<Colour>(Colour.SLIME_BLUE),
					new ListValue<Colour>(Colour.SLIME_GREEN),
					new ListValue<Colour>(Colour.SLIME_RED),
					new ListValue<Colour>(Colour.SLIME_BLACK)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.SLIME_PINK),
					new ListValue<Colour>(Colour.SLIME_BLUE),
					new ListValue<Colour>(Colour.SLIME_GREEN),
					new ListValue<Colour>(Colour.SLIME_RED),
					new ListValue<Colour>(Colour.SLIME_BLACK)),
			null),
	
	MOUTH(Race.HUMAN,
			"a layer of",
			false,
			"skin",
			"skin",
			"soft",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.ORIFICE_MOUTH)),
			null,
			Colour.allSkinColours,
			null,
			Colour.orificeInteriors,
			null),
	
	MOUTH_SLIME(Race.SLIME,
			"a layer of",
			false,
			"slime",
			"slime",
			"soft",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.ORIFICE_MOUTH)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.SLIME_PINK),
					new ListValue<Colour>(Colour.SLIME_BLUE),
					new ListValue<Colour>(Colour.SLIME_GREEN),
					new ListValue<Colour>(Colour.SLIME_RED),
					new ListValue<Colour>(Colour.SLIME_BLACK)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.SLIME_PINK),
					new ListValue<Colour>(Colour.SLIME_BLUE),
					new ListValue<Colour>(Colour.SLIME_GREEN),
					new ListValue<Colour>(Colour.SLIME_RED),
					new ListValue<Colour>(Colour.SLIME_BLACK)),
			null),
	
	NIPPLES(Race.HUMAN,
			"a layer of",
			false,
			"skin",
			"skin",
			"soft",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.ORIFICE_NIPPLE)),
			null,
			Colour.allSkinColours,
			null,
			Colour.orificeInteriors,
			null),
	
	NIPPLES_SLIME(Race.SLIME,
			"a layer of",
			false,
			"slime",
			"slime",
			"soft",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.ORIFICE_NIPPLE)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.SLIME_PINK),
					new ListValue<Colour>(Colour.SLIME_BLUE),
					new ListValue<Colour>(Colour.SLIME_GREEN),
					new ListValue<Colour>(Colour.SLIME_RED),
					new ListValue<Colour>(Colour.SLIME_BLACK)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.SLIME_PINK),
					new ListValue<Colour>(Colour.SLIME_BLUE),
					new ListValue<Colour>(Colour.SLIME_GREEN),
					new ListValue<Colour>(Colour.SLIME_RED),
					new ListValue<Colour>(Colour.SLIME_BLACK)),
			null),
	
	VAGINA(Race.HUMAN,
			"a layer of",
			false,
			"skin",
			"skin",
			"soft",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.ORIFICE_VAGINA)),
			null,
			Colour.allSkinColours,
			null,
			Colour.orificeInteriors,
			null),
	
	VAGINA_SLIME(Race.SLIME,
			"a layer of",
			false,
			"slime",
			"slime",
			"soft",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.ORIFICE_VAGINA)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.SLIME_PINK),
					new ListValue<Colour>(Colour.SLIME_BLUE),
					new ListValue<Colour>(Colour.SLIME_GREEN),
					new ListValue<Colour>(Colour.SLIME_RED),
					new ListValue<Colour>(Colour.SLIME_BLACK)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.SLIME_PINK),
					new ListValue<Colour>(Colour.SLIME_BLUE),
					new ListValue<Colour>(Colour.SLIME_GREEN),
					new ListValue<Colour>(Colour.SLIME_RED),
					new ListValue<Colour>(Colour.SLIME_BLACK)),
			null),

	SLIME(Race.SLIME,
			"a layer of",
			false,
			"slime",
			"slime",
			"gooey",
			null,
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.SLIME_PINK),
					new ListValue<Colour>(Colour.SLIME_BLUE),
					new ListValue<Colour>(Colour.SLIME_GREEN),
					new ListValue<Colour>(Colour.SLIME_RED),
					new ListValue<Colour>(Colour.SLIME_BLACK)),
			null,
			null,
			null),

	FEATHERS(Race.HARPY,
			"a layer of",
			true,
			"feathers",
			"feather",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.FEATHERS_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.FEATHERS_WHITE),
					new ListValue<Colour>(Colour.FEATHERS_PINK),
					new ListValue<Colour>(Colour.FEATHERS_YELLOW),
					new ListValue<Colour>(Colour.FEATHERS_ORANGE),
					new ListValue<Colour>(Colour.FEATHERS_GINGER),
					new ListValue<Colour>(Colour.FEATHERS_BLUE),
					new ListValue<Colour>(Colour.FEATHERS_LILAC),
					new ListValue<Colour>(Colour.FEATHERS_GREEN),
					new ListValue<Colour>(Colour.FEATHERS_RED),
					new ListValue<Colour>(Colour.FEATHERS_BLACK)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.FEATHERS_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.FEATHERS_WHITE),
					new ListValue<Colour>(Colour.FEATHERS_PINK),
					new ListValue<Colour>(Colour.FEATHERS_YELLOW),
					new ListValue<Colour>(Colour.FEATHERS_ORANGE),
					new ListValue<Colour>(Colour.FEATHERS_GINGER),
					new ListValue<Colour>(Colour.FEATHERS_BLUE),
					new ListValue<Colour>(Colour.FEATHERS_LILAC),
					new ListValue<Colour>(Colour.FEATHERS_GREEN),
					new ListValue<Colour>(Colour.FEATHERS_RED),
					new ListValue<Colour>(Colour.FEATHERS_BLACK)),
			null),

	ALLIGATOR_SCALES(Race.ALLIGATOR_MORPH,
			"a layer of",
			true,
			"scales",
			"scale",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED)),
			Colour.naturalScaleColours,
			Colour.dyeScaleColours,
			Colour.naturalScaleColours,
			Colour.dyeScaleColours),
	
	HAIR_SCALES_ALLIGATOR(Race.ALLIGATOR_MORPH,
			"a layer of",
			true,
			"scales",
			"scale",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED)),
			Colour.naturalScaleColours,
			Colour.dyeScaleColours,
			Colour.naturalScaleColours,
			Colour.dyeScaleColours),

	// MISC:
	
	HORN(Race.HUMAN,
			"a layer of",
			false,
			"keratin",
			"keratin",
			"hard",
			null,
			null,
			Util.newArrayListOfValues(new ListValue<Colour>(Colour.HORN_WHITE)),
			null,
			Colour.hornColours,
			null),

	ANTLER_REINDEER(Race.REINDEER_MORPH,
			"a layer of",
			false,
			"velvet",
			"velvet",
			"hard",
			null,
			null,
			Util.newArrayListOfValues(new ListValue<Colour>(Colour.ANTLER_TAN)),
			null,
			Colour.hornColours,
			null),

	TONGUE(Race.HUMAN,
			"a layer of",
			false,
			"skin",
			"skin",
			"fleshy",
			null,
			null,
			Util.newArrayListOfValues(new ListValue<Colour>(Colour.ORIFICE_INTERIOR)),
			null,
			Colour.orificeInteriors,
			null),

	// HAIR:

	HAIR_HUMAN(Race.HUMAN,
			"a head of",
			false,
			"hair",
			"hair",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_ANGEL(Race.ANGEL,
			"a head of",
			false,
			"hair",
			"hair",
			"silken",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_DEMON(Race.DEMON,
			"a head of",
			false,
			"hair",
			"hair",
			"silken",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_CANINE_FUR(Race.DOG_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"fur-like",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_LYCAN_FUR(Race.WOLF_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"fur-like",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_FELINE_FUR(Race.CAT_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"fur-like",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_HORSE_HAIR(Race.HORSE_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"coarse",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_REINDEER_FUR(Race.REINDEER_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"coarse",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_BOVINE_FUR(Race.COW_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"coarse",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_SQUIRREL_FUR(Race.SQUIRREL_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"fur-like",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	HAIR_SLIME(Race.SLIME,
			"a mass of",
			false,
			"slime",
			"slime",
			"gooey",
			null,
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.SLIME_BLACK),
					new ListValue<Colour>(Colour.SLIME_BLUE),
					new ListValue<Colour>(Colour.SLIME_GREEN),
					new ListValue<Colour>(Colour.SLIME_PINK),
					new ListValue<Colour>(Colour.SLIME_RED)),
			null,
			null,
			null),

	HAIR_HARPY(Race.HARPY,
			"a plume of",
			true,
			"feathers",
			"feather",
			"attractive",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE),
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.FEATHERS_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.FEATHERS_WHITE),
					new ListValue<Colour>(Colour.FEATHERS_PINK),
					new ListValue<Colour>(Colour.FEATHERS_YELLOW),
					new ListValue<Colour>(Colour.FEATHERS_ORANGE),
					new ListValue<Colour>(Colour.FEATHERS_GINGER),
					new ListValue<Colour>(Colour.FEATHERS_BLUE),
					new ListValue<Colour>(Colour.FEATHERS_LILAC),
					new ListValue<Colour>(Colour.FEATHERS_GREEN),
					new ListValue<Colour>(Colour.FEATHERS_RED),
					new ListValue<Colour>(Colour.FEATHERS_BLACK)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.FEATHERS_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.FEATHERS_WHITE),
					new ListValue<Colour>(Colour.FEATHERS_PINK),
					new ListValue<Colour>(Colour.FEATHERS_YELLOW),
					new ListValue<Colour>(Colour.FEATHERS_ORANGE),
					new ListValue<Colour>(Colour.FEATHERS_GINGER),
					new ListValue<Colour>(Colour.FEATHERS_BLUE),
					new ListValue<Colour>(Colour.FEATHERS_LILAC),
					new ListValue<Colour>(Colour.FEATHERS_GREEN),
					new ListValue<Colour>(Colour.FEATHERS_RED),
					new ListValue<Colour>(Colour.FEATHERS_BLACK)),
			null),
	
	
	// BODY HAIR:
	
	BODY_HAIR_HUMAN(Race.HUMAN,
			"a layer of",
			false,
			"hair",
			"hair",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE),
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_ANGEL(Race.ANGEL,
			"a layer of",
			false,
			"hair",
			"hair",
			"silken",
			null,
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_DEMON(Race.DEMON,
			"a layer of",
			false,
			"hair",
			"hair",
			"silken",
			null,
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_CANINE_FUR(Race.DOG_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"fur-like",
			null,
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_LYCAN_FUR(Race.WOLF_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"fur-like",
			null,
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_FELINE_FUR(Race.CAT_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"fur-like",
			null,
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_HORSE_HAIR(Race.HORSE_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"coarse",
			null,
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_REINDEER_HAIR(Race.REINDEER_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"coarse",
			null,
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_BOVINE_FUR(Race.COW_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"coarse",
			null,
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_SQUIRREL_FUR(Race.SQUIRREL_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"fur-like",
			null,
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.HIGHLIGHTS),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Colour.naturalHairColours,
			Colour.dyeHairColours,
			Colour.naturalHairColours,
			Colour.dyeHairColours),

	BODY_HAIR_SLIME(Race.SLIME,
			"a mass of",
			false,
			"slime",
			"slime",
			"gooey",
			null,
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.SLIME_BLACK),
					new ListValue<Colour>(Colour.SLIME_BLUE),
					new ListValue<Colour>(Colour.SLIME_GREEN),
					new ListValue<Colour>(Colour.SLIME_PINK),
					new ListValue<Colour>(Colour.SLIME_RED)),
			null,
			null,
			null),

	BODY_HAIR_HARPY(Race.HARPY,
			"a plume of",
			true,
			"feathers",
			"feather",
			"fluffy",
			null,
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.FEATHERS_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.FEATHERS_WHITE),
					new ListValue<Colour>(Colour.FEATHERS_PINK),
					new ListValue<Colour>(Colour.FEATHERS_YELLOW),
					new ListValue<Colour>(Colour.FEATHERS_ORANGE),
					new ListValue<Colour>(Colour.FEATHERS_GINGER),
					new ListValue<Colour>(Colour.FEATHERS_BLUE),
					new ListValue<Colour>(Colour.FEATHERS_LILAC),
					new ListValue<Colour>(Colour.FEATHERS_GREEN),
					new ListValue<Colour>(Colour.FEATHERS_RED),
					new ListValue<Colour>(Colour.FEATHERS_BLACK)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.FEATHERS_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.FEATHERS_WHITE),
					new ListValue<Colour>(Colour.FEATHERS_PINK),
					new ListValue<Colour>(Colour.FEATHERS_YELLOW),
					new ListValue<Colour>(Colour.FEATHERS_ORANGE),
					new ListValue<Colour>(Colour.FEATHERS_GINGER),
					new ListValue<Colour>(Colour.FEATHERS_BLUE),
					new ListValue<Colour>(Colour.FEATHERS_LILAC),
					new ListValue<Colour>(Colour.FEATHERS_GREEN),
					new ListValue<Colour>(Colour.FEATHERS_RED),
					new ListValue<Colour>(Colour.FEATHERS_BLACK)),
			null),

	BODY_HAIR_SCALES_ALLIGATOR(Race.ALLIGATOR_MORPH,
			"a plume of",
			false,
			"scales",
			"scale",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE),
					new ListValue<CoveringPattern>(CoveringPattern.MOTTLED),
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			null,
			Colour.naturalScaleColours,
			Colour.dyeScaleColours,
			Colour.naturalScaleColours,
			Colour.dyeScaleColours),


	
	// EYES:

	EYE_HUMAN(Race.HUMAN,
			"a pair of",
			true,
			"eyes",
			"eye",
			"human",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES),
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC)),
			null,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_ANGEL(Race.ANGEL,
			"a pair of",
			true,
			"eyes",
			"eye",
			"angelic",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC)),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_DEMON_COMMON(Race.DEMON,
			"a pair of",
			true,
			"eyes",
			"eye",
			"alluring",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_BLUE),
					new ListValue<Colour>(Colour.EYE_BROWN),
					new ListValue<Colour>(Colour.EYE_GREEN),
					new ListValue<Colour>(Colour.EYE_YELLOW),
					new ListValue<Colour>(Colour.EYE_RED),
					new ListValue<Colour>(Colour.EYE_ORANGE),
					new ListValue<Colour>(Colour.EYE_AMBER),
					new ListValue<Colour>(Colour.EYE_PINK),
					new ListValue<Colour>(Colour.EYE_BLACK)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_BLUE),
					new ListValue<Colour>(Colour.EYE_BROWN),
					new ListValue<Colour>(Colour.EYE_GREEN),
					new ListValue<Colour>(Colour.EYE_YELLOW),
					new ListValue<Colour>(Colour.EYE_RED),
					new ListValue<Colour>(Colour.EYE_ORANGE),
					new ListValue<Colour>(Colour.EYE_AMBER),
					new ListValue<Colour>(Colour.EYE_PINK),
					new ListValue<Colour>(Colour.EYE_BLACK)),
			null),

	EYE_DOG_MORPH(Race.DOG_MORPH,
			"a pair of",
			true,
			"eyes",
			"eye",
			"dog-like",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC)),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_LYCAN(Race.WOLF_MORPH,
			"a pair of",
			true,
			"eyes",
			"eye",
			"wolf-like",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_BLUE),
					new ListValue<Colour>(Colour.EYE_BROWN),
					new ListValue<Colour>(Colour.EYE_GREEN),
					new ListValue<Colour>(Colour.EYE_AMBER),
					new ListValue<Colour>(Colour.EYE_YELLOW)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_RED),
					new ListValue<Colour>(Colour.EYE_ORANGE),
					new ListValue<Colour>(Colour.EYE_PINK),
					new ListValue<Colour>(Colour.EYE_BLACK)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_BLUE),
					new ListValue<Colour>(Colour.EYE_BROWN),
					new ListValue<Colour>(Colour.EYE_GREEN),
					new ListValue<Colour>(Colour.EYE_AMBER),
					new ListValue<Colour>(Colour.EYE_YELLOW)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_RED),
					new ListValue<Colour>(Colour.EYE_ORANGE),
					new ListValue<Colour>(Colour.EYE_PINK),
					new ListValue<Colour>(Colour.EYE_BLACK))),

	EYE_FELINE(Race.CAT_MORPH,
			"a pair of",
			true,
			"eyes",
			"eye",
			"cat-like",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_BLUE),
					new ListValue<Colour>(Colour.EYE_BROWN),
					new ListValue<Colour>(Colour.EYE_GREEN),
					new ListValue<Colour>(Colour.EYE_AMBER),
					new ListValue<Colour>(Colour.EYE_YELLOW)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_RED),
					new ListValue<Colour>(Colour.EYE_ORANGE),
					new ListValue<Colour>(Colour.EYE_PINK),
					new ListValue<Colour>(Colour.EYE_BLACK)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_BLUE),
					new ListValue<Colour>(Colour.EYE_BROWN),
					new ListValue<Colour>(Colour.EYE_GREEN),
					new ListValue<Colour>(Colour.EYE_AMBER),
					new ListValue<Colour>(Colour.EYE_YELLOW)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_RED),
					new ListValue<Colour>(Colour.EYE_ORANGE),
					new ListValue<Colour>(Colour.EYE_PINK),
					new ListValue<Colour>(Colour.EYE_BLACK))),

	EYE_SQUIRREL(Race.SQUIRREL_MORPH,
			"a pair of",
			true,
			"eyes",
			"eye",
			"squirrel-like",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC)),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_ALLIGATOR_MORPH(Race.ALLIGATOR_MORPH,
			"a pair of",
			true,
			"eyes",
			"eye",
			"reptile-like",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC)),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_HORSE_MORPH(Race.HORSE_MORPH,
			"a pair of",
			true,
			"eyes",
			"eye",
			"horse-like",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC)),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_REINDEER_MORPH(Race.REINDEER_MORPH,
			"a pair of",
			true,
			"eyes",
			"eye",
			"reindeer-like",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC)),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_COW_MORPH(Race.COW_MORPH,
			"a pair of",
			true,
			"eyes",
			"eye",
			"cow-like",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC)),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_HARPY(Race.HARPY,
			"a pair of",
			true,
			"eyes",
			"eye",
			"avian",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC)),
			Colour.naturalIrisColours,
			Colour.dyeIrisColours,
			Colour.naturalIrisColours,
			Colour.dyeIrisColours),

	EYE_SLIME(Race.SLIME,
			"a pair of",
			true,
			"eyes",
			"eye",
			"slime",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_IRISES_HETEROCHROMATIC)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.SLIME_PINK),
					new ListValue<Colour>(Colour.SLIME_BLUE),
					new ListValue<Colour>(Colour.SLIME_GREEN),
					new ListValue<Colour>(Colour.SLIME_RED),
					new ListValue<Colour>(Colour.SLIME_BLACK)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.SLIME_PINK),
					new ListValue<Colour>(Colour.SLIME_BLUE),
					new ListValue<Colour>(Colour.SLIME_GREEN),
					new ListValue<Colour>(Colour.SLIME_RED),
					new ListValue<Colour>(Colour.SLIME_BLACK)),
			null),
	
	EYE_PUPILS(Race.HUMAN,
			"a pair of",
			true,
			"pupils",
			"pupil",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_PUPILS)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.EYE_PUPILS_HETEROCHROMATIC)),
			Colour.naturalPupilColours,
			Colour.dyePupilColours,
			Colour.naturalPupilColours,
			Colour.dyePupilColours),
	
	// Fluids:
	
	CUM(Race.HUMAN,
			"",
			false,
			"cum",
			"cum",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.FLUID)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_WHITE)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_CLEAR),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_BLACK),
					new ListValue<Colour>(Colour.COVERING_RED),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.FEATHERS_BLUE),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_GREEN)),
			null,
			null),
	
	GIRL_CUM(Race.HUMAN,
			"",
			false,
			"girlcum",
			"girlcum",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.FLUID)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_CLEAR)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_WHITE),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_BLACK),
					new ListValue<Colour>(Colour.COVERING_RED),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.FEATHERS_BLUE),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_GREEN)),
			null,
			null),
	
	MILK(Race.HUMAN,
			"",
			false,
			"milk",
			"milk",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.FLUID)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_WHITE)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_CLEAR),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_BLACK),
					new ListValue<Colour>(Colour.COVERING_RED),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.FEATHERS_BLUE),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_GREEN)),
			null,
			null),
	
	// Makeup:
	
	MAKEUP_BLUSHER(Race.HUMAN,
			"a layer of",
			false,
			"blusher",
			"blusher",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_NONE)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_RED),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_WHITE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_ORANGE),
					new ListValue<Colour>(Colour.COVERING_BLACK)),
			null,
			null),
	
	MAKEUP_EYE_LINER(Race.HUMAN,
			"a layer of",
			false,
			"eye liner",
			"eye liner",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_NONE)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_RED),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_WHITE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_ORANGE),
					new ListValue<Colour>(Colour.COVERING_BLACK)),
			null,
			null),
	
	MAKEUP_EYE_SHADOW(Race.HUMAN,
			"a layer of",
			false,
			"eye shadow",
			"eye shadow",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_NONE)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_RED),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_WHITE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_ORANGE),
					new ListValue<Colour>(Colour.COVERING_BLACK)),
			null,
			null),

	MAKEUP_LIPSTICK(Race.HUMAN,
			"a layer of",
			false,
			"lipstick",
			"lipstick",
			"",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_NONE)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_CLEAR),
					new ListValue<Colour>(Colour.COVERING_RED),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_WHITE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_ORANGE),
					new ListValue<Colour>(Colour.COVERING_BLACK)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_CLEAR),
					new ListValue<Colour>(Colour.COVERING_RED),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_WHITE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_ORANGE),
					new ListValue<Colour>(Colour.COVERING_BLACK))),
	
	MAKEUP_NAIL_POLISH_HANDS(Race.HUMAN,
			"a layer of",
			false,
			"nail polish",
			"nail polish",
			"smooth",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_NONE)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_CLEAR),
					new ListValue<Colour>(Colour.COVERING_RED),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_WHITE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_ORANGE),
					new ListValue<Colour>(Colour.COVERING_BLACK)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_CLEAR),
					new ListValue<Colour>(Colour.COVERING_RED),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_WHITE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_ORANGE),
					new ListValue<Colour>(Colour.COVERING_BLACK))),
	
	MAKEUP_NAIL_POLISH_FEET(Race.HUMAN,
			"a layer of",
			false,
			"nail polish",
			"nail polish",
			"smooth",
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.NONE)),
			Util.newArrayListOfValues(
					new ListValue<CoveringPattern>(CoveringPattern.SPOTTED),
					new ListValue<CoveringPattern>(CoveringPattern.STRIPED)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_NONE)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_CLEAR),
					new ListValue<Colour>(Colour.COVERING_RED),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_WHITE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_ORANGE),
					new ListValue<Colour>(Colour.COVERING_BLACK)),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_CLEAR),
					new ListValue<Colour>(Colour.COVERING_RED),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_WHITE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_ORANGE),
					new ListValue<Colour>(Colour.COVERING_BLACK)));
	
	private String determiner, namePlural, nameSingular, descriptor;
	private List<Colour> naturalColoursPrimary, dyeColoursPrimary, naturalColoursSecondary, dyeColoursSecondary, allColours, allPrimaryColours, allSecondaryColours;
	private List<CoveringPattern> naturalPatterns, dyePatterns, allPatterns;
	private Race race;
	private boolean isDefaultPlural;

	private BodyCoveringType(
			Race race,
			String determiner,
			boolean isDefaultPlural,
			String namePlural,
			String nameSingular,
			String descriptor,
			List<CoveringPattern> naturalPatterns,
			List<CoveringPattern> dyePatterns,
			List<Colour> naturalColoursPrimary,
			List<Colour> dyeColoursPrimary,
			List<Colour> naturalColoursSecondary,
			List<Colour> dyeColoursSecondary) {
		
		this.determiner = determiner;
		this.namePlural = namePlural;
		this.nameSingular=nameSingular;
		this.descriptor = descriptor;
		this.isDefaultPlural = isDefaultPlural;
		
		if(naturalPatterns == null) {
			this.naturalPatterns = new ArrayList<>();
			this.naturalPatterns.add(CoveringPattern.NONE);
		} else {
			this.naturalPatterns = naturalPatterns;
		}
		
		if(dyePatterns == null) {
			this.dyePatterns = new ArrayList<>();
		} else {
			this.dyePatterns = dyePatterns;
		}
		
		allPatterns = new ArrayList<>();
		if(naturalPatterns == null) {
			allPatterns.add(CoveringPattern.NONE);
		} else {
			allPatterns.addAll(naturalPatterns);
		}
		
		if(dyePatterns != null) {
			allPatterns.addAll(dyePatterns);
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
			allColours.add(c);
			allPrimaryColours.add(c);
		}
		for(Colour c : this.naturalColoursSecondary) {
			allColours.add(c);
			allSecondaryColours.add(c);
		}
		for(Colour c : this.dyeColoursSecondary) {
			allColours.add(c);
			allSecondaryColours.add(c);
		}
		
		this.race = race;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return determiner;
	}

	@Override
	public boolean isDefaultPlural() {
		return isDefaultPlural;
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return nameSingular;
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return namePlural;
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		return descriptor;
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

	@Override
	public Race getRace() {
		return race;
	}
}
