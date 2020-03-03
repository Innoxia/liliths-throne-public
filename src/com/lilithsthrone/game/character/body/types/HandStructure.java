package com.lilithsthrone.game.character.body.types;

import java.util.List;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Stadler76
 */
public enum HandStructure {

	HUMAN(
		Util.newArrayListOfValues(""),
		Util.newArrayListOfValues("soft", "feminine")
	),

	ANGEL(
		Util.newArrayListOfValues("delicate", "soft"),
		Util.newArrayListOfValues("delicate", "soft", "feminine")
	),

	DEMON(
		Util.newArrayListOfValues(""),
		Util.newArrayListOfValues("delicate", "soft", "feminine")
	),

	BOVINE(
		Util.newArrayListOfValues("bovine"),
		Util.newArrayListOfValues("feminine", "bovine")
	),

	DOG_MORPH(
		Util.newArrayListOfValues("dog-like", "paw-like", "furry", "canine"),
		Util.newArrayListOfValues("soft", "feminine", "dog-like", "paw-like", "furry", "canine")
	),

	WOLF_MORPH(
		Util.newArrayListOfValues("wolf-like", "furry", "paw-like"),
		Util.newArrayListOfValues("soft", "feminine", "wolf-like", "furry", "paw-like")
	),

	FOX_MORPH(
		Util.newArrayListOfValues("fox-like", "furry", "paw-like"),
		Util.newArrayListOfValues("soft", "feminine", "fox-like", "furry", "paw-like")
	),

	CAT_MORPH(
		Util.newArrayListOfValues("soft", "delicate", "cat-like", "paw-like", "furry", "feline"),
		Util.newArrayListOfValues("soft", "feminine", "cat-like", "paw-like", "furry", "feline")
	),

	EQUINE(
		Util.newArrayListOfValues("equine"),
		Util.newArrayListOfValues("feminine", "equine")
	),

	REINDEER_MORPH(
		Util.newArrayListOfValues("reindeer"),
		Util.newArrayListOfValues("feminine", "reindeer")
	),

	REPTILE(
		Util.newArrayListOfValues("scaled"),
		Util.newArrayListOfValues("feminine", "scaled")
	),

	SQUIRREL_MORPH(
		Util.newArrayListOfValues("soft", "squirrel-like", "claw-like", "furry", "rodent"),
		Util.newArrayListOfValues("soft", "feminine", "squirrel-like", "claw-like", "furry", "rodent")
	),

	RAT_MORPH(
		Util.newArrayListOfValues("soft", "rat-like", "claw-like", "furry", "rodent"),
		Util.newArrayListOfValues("soft", "feminine", "rat-like", "claw-like", "furry", "rodent")
	),

	RABBIT_MORPH(
		Util.newArrayListOfValues("rabbit-like", "paw-like", "furry"),
		Util.newArrayListOfValues("soft", "feminine", "rabbit-like", "paw-like", "furry")		
	),

	BAT_MORPH(
		Util.newArrayListOfValues("bat-like"),
		Util.newArrayListOfValues("feminine", "bat-like")
	),

	AVIAN_FEATHERED(
		Util.newArrayListOfValues("feathered"),
		Util.newArrayListOfValues("feminine", "feathered")
	),
	;

	private final List<String> descriptorsMasculine;
	private final List<String> descriptorsFeminine;
	private final String name;
	private final String namePlural;

	private HandStructure(List<String> descriptorsMasculine, List<String> descriptorsFeminine) {
		this(descriptorsMasculine, descriptorsFeminine, "hand", "hands");
	}

	/**
	 * @param descriptorsMasculine The descriptors that are applied to a masculine form of this hand structure.
	 * @param descriptorsFeminine The descriptors that are applied to a feminine form of this hand structure.
	 * @param name The singular name of the hands associated with this hand structure. This will usually just be "hand".
	 * @param namePlural The plural name of the hands associated with this hand structure. This will usually just be "hands".
	 */
	private HandStructure(List<String> descriptorsMasculine, List<String> descriptorsFeminine, String name, String namePlural) {
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		this.name = name;
		this.namePlural = namePlural;
	}

	public List<String> getDescriptorsMasculine() {
		return descriptorsMasculine;
	}

	public List<String> getDescriptorsFeminine() {
		return descriptorsFeminine;
	}

	public String getName() {
		return name;
	}

	public String getNamePlural() {
		return namePlural;
	}

}
