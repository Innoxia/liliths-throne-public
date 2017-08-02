package com.base.game.character.race;

import com.base.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.1.52
 * @author Innoxia
 */
public enum Genus {

	HUMAN("human",
			Colour.CLOTHING_RED),
	RODENT("rodent",
			Colour.CLOTHING_RED),
	SLIME("slime",
			Colour.CLOTHING_RED),
	CANINE("canine",
			Colour.CLOTHING_RED),
	FELINE("feline",
			Colour.CLOTHING_RED),
	EQUINE("equine",
			Colour.CLOTHING_RED),
	VULPINE("vulpine",
			Colour.CLOTHING_RED),
	BOVINE("bovine",
			Colour.CLOTHING_RED),
	INSECT("insectoid",
			Colour.CLOTHING_RED),
	ARACHNID("arachnoid",
			Colour.CLOTHING_RED),
	AVIAN("aviine",
			Colour.CLOTHING_RED),
	AQUATIC("aquatic",
			Colour.CLOTHING_RED),
	REPTILE("reptilian",
			Colour.CLOTHING_RED),
	DRAGON("dragon",
			Colour.CLOTHING_RED),
	DEMON("demon",
			Colour.CLOTHING_RED),
	CELESTIAL("celestial",
			Colour.CLOTHING_RED);

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
