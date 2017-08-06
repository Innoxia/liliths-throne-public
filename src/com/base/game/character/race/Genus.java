package com.base.game.character.race;

import com.base.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.1.82
 * @author Innoxia
 */
public enum Genus {

	HUMAN("human",
			Colour.RACE_HUMAN),
	RODENT("rodent",
			Colour.CLOTHING_RED), //TODO
	SLIME("slime",
			Colour.RACE_SLIME),
	CANINE("canine",
			Colour.RACE_DOG_MORPH),
	FELINE("feline",
			Colour.RACE_CAT_MORPH),
	EQUINE("equine",
			Colour.RACE_HORSE_MORPH),
	VULPINE("vulpine",
			Colour.CLOTHING_RED), //TODO
	BOVINE("bovine",
			Colour.CLOTHING_RED), //TODO
	INSECT("insectoid",
			Colour.CLOTHING_RED), //TODO
	ARACHNID("arachnoid",
			Colour.CLOTHING_RED), //TODO
	AVIAN("aviine",
			Colour.RACE_HARPY),
	AQUATIC("aquatic",
			Colour.CLOTHING_RED), //TODO
	REPTILE("reptilian",
			Colour.CLOTHING_RED), //TODO
	DRAGON("dragon",
			Colour.CLOTHING_RED), //TODO
	DEMON("demon",
			Colour.RACE_DEMON),
	CELESTIAL("celestial",
			Colour.RACE_ANGEL);

	private String name;
	private Colour colour;

	private Genus(String name, Colour colour) {
		this.name = name;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}
}
