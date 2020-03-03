package com.lilithsthrone.game.character.body.types;

import java.util.List;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Stadler76
 */
public enum FingerStructure {

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
		Util.newArrayListOfValues("padded", "canine"),
		Util.newArrayListOfValues("soft", "feminine", "padded", "canine")
	),

	WOLF_MORPH(
		Util.newArrayListOfValues("padded", "wolf-like"),
		Util.newArrayListOfValues("soft", "feminine", "padded", "wolf-like")
	),

	FOX_MORPH(
		Util.newArrayListOfValues("padded", "fox-like"),
		Util.newArrayListOfValues("soft", "feminine", "padded", "fox-like")
	),

	CAT_MORPH(
		Util.newArrayListOfValues("soft", "delicate", "padded", "feline"),
		Util.newArrayListOfValues("soft", "feminine", "padded", "feline")
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

	RODENT(
		Util.newArrayListOfValues("soft", "clawed", "rodent"),
		Util.newArrayListOfValues("soft", "feminine", "clawed", "rodent")
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
		Util.newArrayListOfValues("soft", "feminine", "bat-like")
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

	private FingerStructure(List<String> descriptorsMasculine, List<String> descriptorsFeminine) {
		this(descriptorsMasculine, descriptorsFeminine, "finger", "fingers");
	}

	/**
	 * @param descriptorsMasculine The descriptors that are applied to a masculine form of this fingers of this finger structure.
	 * @param descriptorsFeminine The descriptors that are applied to a feminine form of the fingers of this finger structure.
	 * @param name The singular name of the fingers associated with this finger structure. This will usually just be "finger".
	 * @param namePlural The plural name of the hands associated with this finger structure. This will usually just be "fingers".
	 */
	private FingerStructure(List<String> descriptorsMasculine, List<String> descriptorsFeminine, String name, String namePlural) {
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
