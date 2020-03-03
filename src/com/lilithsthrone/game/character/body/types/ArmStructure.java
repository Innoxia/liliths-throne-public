package com.lilithsthrone.game.character.body.types;

import java.util.List;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Stadler76
 */
public enum ArmStructure {
	
	HUMAN(Util.newArrayListOfValues("")),
	ANGEL(Util.newArrayListOfValues("delicate")),
	DEMON(Util.newArrayListOfValues("flawless")),
	FURRY(Util.newArrayListOfValues("furry", "fur-coated")),
	HAIR_COATED(Util.newArrayListOfValues("hair-coated")),
	REPTILE(Util.newArrayListOfValues("scaled", "reptile-like")),
	BAT_WINGS(Util.newArrayListOfValues("bat-like"), "wing", "wings"),
	AVIAN_WINGS(Util.newArrayListOfValues("feathered", "bird-like"), "wing", "wings"),
	;

	private final List<String> descriptorsMasculine;
	private final List<String> descriptorsFeminine;
	private final String name;
	private final String namePlural;

	private ArmStructure(List<String> descriptors) {
		this(descriptors, descriptors);
	}

	private ArmStructure(List<String> descriptors, String name, String namePlural) {
		this(descriptors, descriptors, name, namePlural);
	}

	private ArmStructure(List<String> descriptorsMasculine, List<String> descriptorsFeminine) {
		this(descriptorsMasculine, descriptorsFeminine, "arm", "arms");
	}

	/**
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this arm structure.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this arm structure.
	 * @param name The singular name of the arm structure. This will usually just be "arm" or "wing".
	 * @param namePlural The plural name of the arm structure. This will usually just be "arms" or "wings".
	 */
	private ArmStructure(List<String> descriptorsMasculine, List<String> descriptorsFeminine, String name, String namePlural) {
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
