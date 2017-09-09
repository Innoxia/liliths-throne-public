package com.base.game.inventory.enchanting;

import java.io.IOException;
import java.io.InputStream;

import com.base.game.inventory.Rarity;
import com.base.game.inventory.item.AbstractItemType;
import com.base.game.inventory.item.ItemType;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.7
 * @version 0.1.83
 * @author Innoxia
 */
public enum TFEssence {
	
	CAT_MORPH("Cat-morph",
			"An essence gained from a cat-morph.",
			"feline",
			"essenceCatMorph",
			Colour.RACE_CAT_MORPH,
			Rarity.COMMON),
	
	DOG_MORPH("Dog-morph",
			"An essence gained from a dog-morph.",
			"canine",
			"essenceDogMorph",
			Colour.RACE_DOG_MORPH,
			Rarity.COMMON),
	
	HORSE_MORPH("Horse-morph",
			"An essence gained from a horse-morph.",
			"equine",
			"essenceHorseMorph",
			Colour.RACE_HORSE_MORPH,
			Rarity.COMMON),
	
	SQUIRREL_MORPH("Squirrel-morph",
			"An essence gained from a squirrel-morph.",
			"squirrel",
			"essenceSquirrelMorph",
			Colour.RACE_SQUIRREL_MORPH,
			Rarity.COMMON),
	
	WOLF_MORPH("Wolf-morph",
			"An essence gained from a wolf-morph.",
			"lupine",
			"essenceWolfMorph",
			Colour.RACE_WOLF_MORPH,
			Rarity.COMMON),

	COW_MORPH("Cow-morph",
			"An essence gained from a cow-morph.",
			"bovine",
			"essenceCowMorph",
			Colour.RACE_COW_MORPH,
			Rarity.COMMON),

	HARPY("Harpy",
			"An essence gained from a harpy.",
			"avian",
			"essenceHarpy",
			Colour.RACE_HARPY,
			Rarity.UNCOMMON),
	
//	SLIME("Slime",
//			"An essence gained from a slime.",
//			"slime",
//			"essenceSlime",
//			Colour.RACE_SLIME,
//			Rarity.COMMON),

	DEMON("Demon",
			"An essence gained from a demon.",
			"demonic",
			"essenceDemon",
			Colour.RACE_DEMON,
			Rarity.EPIC),
	
//	ANGEL("Angel",
//			"An essence gained from an angel.",
//			"angelic",
//			"essenceAngel",
//			Colour.RACE_ANGEL,
//			Rarity.RARE),
	
	HUMAN("Human",
			"An essence gained from a human.",
			"human",
			"essenceHuman",
			Colour.RACE_HUMAN,
			Rarity.COMMON),
	
	ARCANE("Arcane",
			"An exceedingly rare pure arcane essence.",
			"arcane",
			"essenceArcane",
			Colour.GENERIC_ARCANE,
			Rarity.LEGENDARY);
	

	private String name, description, descriptor, SVGString;
	private Colour colour;
	private Rarity rarity;

	private TFEssence(String name, String description, String descriptor, String SVGString, Colour colour, Rarity rarity) {
		this.name = name;
		this.description = description;
		this.descriptor = descriptor;
		this.SVGString = SVGString;
		this.colour = colour;
		this.rarity = rarity;
		
		// Set this item's file image:
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/base/res/crafting/" + SVGString + ".svg");
			String s = Util.inputStreamToString(is);

			s = s.replaceAll("#ff2a2a", this.colour.getShades()[0]);
			s = s.replaceAll("#ff5555", this.colour.getShades()[1]);
			s = s.replaceAll("#ff8080", this.colour.getShades()[2]);
			s = s.replaceAll("#ffaaaa", this.colour.getShades()[3]);
			s = s.replaceAll("#ffd5d5", this.colour.getShades()[4]);
			this.SVGString = s;

			is.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public String getSVGString() {
		return SVGString;
	}

	public Colour getColour() {
		return colour;
	}

	public Rarity getRarity() {
		return rarity;
	}
	
	public static AbstractItemType essenceToItem(TFEssence essence) {
		switch(essence) {
			case ARCANE:
				return ItemType.BOTTLED_ESSENCE_ARCANE;
			case CAT_MORPH:
				return ItemType.BOTTLED_ESSENCE_CAT_MORPH;
			case COW_MORPH:
				return ItemType.BOTTLED_ESSENCE_COW_MORPH;
			case DEMON:
				return ItemType.BOTTLED_ESSENCE_DEMON;
			case DOG_MORPH:
				return ItemType.BOTTLED_ESSENCE_DOG_MORPH;
			case HARPY:
				return ItemType.BOTTLED_ESSENCE_HARPY;
			case HORSE_MORPH:
				return ItemType.BOTTLED_ESSENCE_HORSE_MORPH;
			case HUMAN:
				return ItemType.BOTTLED_ESSENCE_HUMAN;
			case SQUIRREL_MORPH:
				return ItemType.BOTTLED_ESSENCE_SQUIRREL_MORPH;
			case WOLF_MORPH:
				return ItemType.BOTTLED_ESSENCE_WOLF_MORPH;
		}
		return ItemType.BOTTLED_ESSENCE_ARCANE;
	}
}
