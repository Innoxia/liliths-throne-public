package com.base.game.character.body.types;

import java.util.ArrayList;
import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.character.race.Race;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.0
 * @version 0.1.69.9
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
					new ListValue<Colour>(Colour.HUMAN_SKIN_LIGHT),
					new ListValue<Colour>(Colour.HUMAN_SKIN_OLIVE),
					new ListValue<Colour>(Colour.HUMAN_SKIN_DARK),
					new ListValue<Colour>(Colour.HUMAN_SKIN_EBONY)),
			null),

	ANGEL(Race.ANGEL,
			"a layer of",
			false,
			"skin",
			"skin",
			"glowing, perfectly unblemished",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.HUMAN_SKIN_LIGHT),
					new ListValue<Colour>(Colour.HUMAN_SKIN_OLIVE),
					new ListValue<Colour>(Colour.HUMAN_SKIN_DARK),
					new ListValue<Colour>(Colour.HUMAN_SKIN_EBONY)),
			null),

	DEMON_COMMON(Race.DEMON,
			"a layer of",
			false,
			"skin",
			"skin",
			"soft",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.DEMON_SKIN_PINK),
					new ListValue<Colour>(Colour.DEMON_SKIN_LILAC),
					new ListValue<Colour>(Colour.DEMON_SKIN_BLUE),
					new ListValue<Colour>(Colour.DEMON_SKIN_PURPLE),
					new ListValue<Colour>(Colour.DEMON_SKIN_RED),
					new ListValue<Colour>(Colour.DEMON_SKIN_BROWN),
					new ListValue<Colour>(Colour.DEMON_SKIN_EBONY),
					new ListValue<Colour>(Colour.DEMON_SKIN_IVORY)),
			null),

	CANINE_FUR(Race.DOG_MORPH,
			"a layer of",
			false,
			"fur",
			"fur",
			"shaggy",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_WHITE),
					new ListValue<Colour>(Colour.COVERING_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_BROWN_DARK),
					new ListValue<Colour>(Colour.COVERING_BLACK)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_GINGER),
					new ListValue<Colour>(Colour.COVERING_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_RED))),
	
	LYCAN_FUR(Race.WOLF_MORPH,
			"a layer of",
			false,
			"fur",
			"fur",
			"shaggy",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_WHITE),
					new ListValue<Colour>(Colour.COVERING_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_BROWN_DARK),
					new ListValue<Colour>(Colour.COVERING_BLACK)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_GINGER),
					new ListValue<Colour>(Colour.COVERING_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_RED))),

	CANINE_PENIS(Race.DOG_MORPH,
			"a layer of",
			false,
			"skin",
			"skin",
			"smooth",
			Util.newArrayListOfValues(new ListValue<Colour>(Colour.CANINE_COCK)),
			null),

	FELINE_FUR(Race.CAT_MORPH,
			"a layer of",
			false,
			"fur",
			"fur",
			"smooth",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_WHITE),
					new ListValue<Colour>(Colour.COVERING_BLONDE),
					new ListValue<Colour>(Colour.COVERING_GINGER),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_BROWN_DARK),
					new ListValue<Colour>(Colour.COVERING_BLACK)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_RED))),

	HORSE_HAIR(Race.HORSE_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLACK),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_BROWN_DARK),
					new ListValue<Colour>(Colour.COVERING_BLONDE),
					new ListValue<Colour>(Colour.COVERING_GINGER),
					new ListValue<Colour>(Colour.COVERING_WHITE)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_RED))),

	EQUINE_PENIS(Race.HORSE_MORPH,
			"a layer of",
			false,
			"skin",
			"skin",
			"thick",
			Util.newArrayListOfValues(new ListValue<Colour>(Colour.EQUINE_COCK)),
			null),

	EQUINE_VAGINA(Race.HORSE_MORPH,
			"a layer of",
			false,
			"skin",
			"skin",
			"thick",
			Util.newArrayListOfValues(new ListValue<Colour>(Colour.EQUINE_VAGINA)),
			null),

	EQUINE_ASSHOLE(Race.HORSE_MORPH,
			"a layer of",
			false,
			"skin",
			"skin",
			"thick",
			Util.newArrayListOfValues(new ListValue<Colour>(Colour.EQUINE_ASSHOLE)),
			null),

	SLIME(Race.SLIME,
			"a layer of",
			false,
			"slime",
			"slime",
			"gooey",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.SLIME_PINK),
					new ListValue<Colour>(Colour.SLIME_BLUE),
					new ListValue<Colour>(Colour.SLIME_GREEN),
					new ListValue<Colour>(Colour.SLIME_RED),
					new ListValue<Colour>(Colour.SLIME_BLACK)),
			null),

	FEATHERS(Race.HARPY,
			"a layer of",
			true,
			"feathers",
			"feather",
			"",
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
	
	VULPINE_FUR(Race.FOX_MORPH,
		    	"a layer of",
		    	true,
		    	"fur",
		    	"fur",
		    	"soft",
		    	Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_WHITE)
					new ListValue<Colour>(Colour.COVERING_BLONDE),
					new ListValue<Colour>(Colour.COVERING_SILVER),
					new ListValue<Colour>(Colour.COVERING_GINGER),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_BROWN_DARK),
					new ListValue<Colour>(Colour.COVERING_BLACK)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_RED))),

	// MISC:

	HORN(Race.DEMON,
			"a layer of",
			false,
			"keratin",
			"keratin",
			"hard",
			Util.newArrayListOfValues(new ListValue<Colour>(Colour.HORN_WHITE)),
			null),

	TONGUE(Race.HUMAN,
			"a layer of",
			false,
			"skin",
			"skin",
			"fleshy",
			Util.newArrayListOfValues(new ListValue<Colour>(Colour.TONGUE)),
			null),

	// HAIR:

	HAIR_HUMAN(Race.HUMAN,
			"a head of",
			false,
			"hair",
			"hair",
			"",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLACK),
					new ListValue<Colour>(Colour.COVERING_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_GINGER),
					new ListValue<Colour>(Colour.COVERING_WHITE)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_RED))),

	HAIR_ANGEL(Race.ANGEL,
			"a head of",
			false,
			"hair",
			"hair",
			"glowing, silken",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLONDE),
					new ListValue<Colour>(Colour.COVERING_WHITE)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BLACK),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_GINGER),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_RED))),

	HAIR_DEMON(Race.DEMON,
			"a head of",
			false,
			"hair",
			"hair",
			"silken",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLACK),
					new ListValue<Colour>(Colour.COVERING_WHITE)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_GINGER),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_RED))),

	HAIR_CANINE_FUR(Race.DOG_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"fur-like",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLACK),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_BROWN_DARK),
					new ListValue<Colour>(Colour.COVERING_BLONDE),
					new ListValue<Colour>(Colour.COVERING_WHITE)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_GINGER),
					new ListValue<Colour>(Colour.COVERING_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_RED))),

	HAIR_LYCAN_FUR(Race.WOLF_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"fur-like",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLACK),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_BROWN_DARK),
					new ListValue<Colour>(Colour.COVERING_BLONDE),
					new ListValue<Colour>(Colour.COVERING_WHITE)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_GINGER),
					new ListValue<Colour>(Colour.COVERING_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_RED))),

	HAIR_FELINE_FUR(Race.CAT_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"fur-like",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLACK),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_BROWN_DARK),
					new ListValue<Colour>(Colour.COVERING_BLONDE),
					new ListValue<Colour>(Colour.COVERING_GINGER),
					new ListValue<Colour>(Colour.COVERING_WHITE)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_RED))),

	HAIR_HORSE_HAIR(Race.HORSE_MORPH,
			"a layer of",
			false,
			"hair",
			"hair",
			"coarse",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLACK),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_BROWN_DARK),
					new ListValue<Colour>(Colour.COVERING_BLONDE),
					new ListValue<Colour>(Colour.COVERING_GINGER),
					new ListValue<Colour>(Colour.COVERING_WHITE)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_RED))),

	HAIR_SLIME(Race.SLIME,
			"a mass of",
			false,
			"slime",
			"slime",
			"gooey",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.SLIME_BLACK),
					new ListValue<Colour>(Colour.SLIME_BLUE),
					new ListValue<Colour>(Colour.SLIME_GREEN),
					new ListValue<Colour>(Colour.SLIME_PINK),
					new ListValue<Colour>(Colour.SLIME_RED)),
			null),

	HAIR_HARPY(Race.HARPY,
			"a plume of",
			true,
			"feathers",
			"feather",
			"attractive",
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

	HAIR_VULPINE_FUR(Race.FOX_MORPH,
		    	"a layer of",
		    	true,
			"hair",
			"hair",
			"fur-like",
		    	Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_WHITE)
					new ListValue<Colour>(Colour.COVERING_BLONDE),
					new ListValue<Colour>(Colour.COVERING_SILVER),
					new ListValue<Colour>(Colour.COVERING_GINGER),
					new ListValue<Colour>(Colour.COVERING_BROWN),
					new ListValue<Colour>(Colour.COVERING_BROWN_DARK),
					new ListValue<Colour>(Colour.COVERING_BLACK)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.COVERING_BLEACH_BLONDE),
					new ListValue<Colour>(Colour.COVERING_BLUE),
					new ListValue<Colour>(Colour.COVERING_GREEN),
					new ListValue<Colour>(Colour.COVERING_PINK),
					new ListValue<Colour>(Colour.COVERING_PURPLE),
					new ListValue<Colour>(Colour.COVERING_RED))),
	
	// EYES:

	EYE_HUMAN(Race.HUMAN,
			"a pair of",
			true,
			"eyes",
			"eye",
			"",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_BLUE),
					new ListValue<Colour>(Colour.EYE_BROWN),
					new ListValue<Colour>(Colour.EYE_GREEN)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_YELLOW),
					new ListValue<Colour>(Colour.EYE_RED),
					new ListValue<Colour>(Colour.EYE_ORANGE),
					new ListValue<Colour>(Colour.EYE_PINK),
					new ListValue<Colour>(Colour.EYE_BLACK))),

	EYE_ANGEL(Race.ANGEL,
			"a pair of",
			true,
			"eyes",
			"eye",
			"",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_BLUE),
					new ListValue<Colour>(Colour.EYE_BROWN),
					new ListValue<Colour>(Colour.EYE_GREEN)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_YELLOW),
					new ListValue<Colour>(Colour.EYE_RED),
					new ListValue<Colour>(Colour.EYE_ORANGE),
					new ListValue<Colour>(Colour.EYE_PINK),
					new ListValue<Colour>(Colour.EYE_BLACK))),

	EYE_DEMON_COMMON(Race.DEMON,
			"a pair of",
			true,
			"eyes",
			"eye",
			"snake-like",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_BLUE),
					new ListValue<Colour>(Colour.EYE_BROWN),
					new ListValue<Colour>(Colour.EYE_GREEN),
					new ListValue<Colour>(Colour.EYE_YELLOW),
					new ListValue<Colour>(Colour.EYE_RED),
					new ListValue<Colour>(Colour.EYE_ORANGE),
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
					new ListValue<Colour>(Colour.EYE_BLUE),
					new ListValue<Colour>(Colour.EYE_BROWN),
					new ListValue<Colour>(Colour.EYE_GREEN)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_YELLOW),
					new ListValue<Colour>(Colour.EYE_RED),
					new ListValue<Colour>(Colour.EYE_ORANGE),
					new ListValue<Colour>(Colour.EYE_PINK),
					new ListValue<Colour>(Colour.EYE_BLACK))),

	EYE_LYCAN(Race.WOLF_MORPH,
			"a pair of",
			true,
			"eyes",
			"eye",
			"wolf-like",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_BLUE),
					new ListValue<Colour>(Colour.EYE_BROWN),
					new ListValue<Colour>(Colour.EYE_GREEN),
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
					new ListValue<Colour>(Colour.EYE_BLUE),
					new ListValue<Colour>(Colour.EYE_BROWN),
					new ListValue<Colour>(Colour.EYE_GREEN),
					new ListValue<Colour>(Colour.EYE_YELLOW)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_RED),
					new ListValue<Colour>(Colour.EYE_ORANGE),
					new ListValue<Colour>(Colour.EYE_PINK),
					new ListValue<Colour>(Colour.EYE_BLACK))),

	EYE_HORSE_MORPH(Race.HORSE_MORPH,
			"a pair of",
			true,
			"eyes",
			"eye",
			"horse-like",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_BLUE),
					new ListValue<Colour>(Colour.EYE_BROWN),
					new ListValue<Colour>(Colour.EYE_GREEN)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_YELLOW),
					new ListValue<Colour>(Colour.EYE_RED),
					new ListValue<Colour>(Colour.EYE_ORANGE),
					new ListValue<Colour>(Colour.EYE_PINK),
					new ListValue<Colour>(Colour.EYE_BLACK))),

	EYE_HARPY(Race.HARPY,
			"a pair of",
			true,
			"eyes",
			"eye",
			"",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_BLUE),
					new ListValue<Colour>(Colour.EYE_BROWN),
					new ListValue<Colour>(Colour.EYE_GREEN),
					new ListValue<Colour>(Colour.EYE_YELLOW)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_RED),
					new ListValue<Colour>(Colour.EYE_ORANGE),
					new ListValue<Colour>(Colour.EYE_PINK),
					new ListValue<Colour>(Colour.EYE_BLACK))),

	EYE_VULPINE(Race.FOX_MORPH
		    	"a pair of",
		    	true,
		    	"eyes",
		    	"eye",
		    	"fox-like",
		    	Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_YELLOW),
					new ListValue<Colour>(Colour.EYE_BROWN),
					new ListValue<Colour>(Colour.EYE_ORANGE),
					new ListValue<Colour>(Colour.EYE_GREEN)),
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.EYE_BLUE),
					new ListValue<Colour>(Colour.EYE_RED),
					new ListValue<Colour>(Colour.EYE_PINK),
					new ListValue<Colour>(Colour.EYE_BLACK))),
	
	EYE_SLIME(Race.SLIME,
			"a pair of",
			true,
			"eyes",
			"eye",
			"slime",
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.SLIME_PINK),
					new ListValue<Colour>(Colour.SLIME_BLUE),
					new ListValue<Colour>(Colour.SLIME_GREEN),
					new ListValue<Colour>(Colour.SLIME_RED),
					new ListValue<Colour>(Colour.SLIME_BLACK)),
			null);
					
	
	private String determiner, namePlural, nameSingular, descriptor;
	private List<Colour> naturalColours, dyeColours, allColours;
	private Race race;
	private boolean isDeafultPlural;

	private BodyCoveringType(Race race, String determiner, boolean isDeafultPlural, String namePlural, String nameSingular, String descriptor, List<Colour> naturalColours, List<Colour> dyeColours) {
		this.determiner = determiner;
		this.namePlural = namePlural;
		this.nameSingular=nameSingular;
		this.descriptor = descriptor;
		this.isDeafultPlural = isDeafultPlural;

		this.naturalColours = naturalColours;
		this.dyeColours = dyeColours;
		
		allColours = new ArrayList<>();

		if(naturalColours!=null)
			for(Colour c : naturalColours)
				allColours.add(c);
		
		if(dyeColours!=null)
			for(Colour c : dyeColours)
				allColours.add(c);
		
		this.race = race;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return determiner;
	}

	@Override
	public boolean isDefaultPlural() {
		return isDeafultPlural;
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
	
	public List<Colour> getNaturalColours() {
		return naturalColours;
	}

	public List<Colour> getDyeColours() {
		return dyeColours;
	}
	
	public List<Colour> getAllColours() {
		return allColours;
	}

	public BodyCoveringType getSkinType() {
		return this;
	}

	@Override
	public Race getRace() {
		return race;
	}
}
