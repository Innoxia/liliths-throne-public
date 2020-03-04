package com.lilithsthrone.game.character.body.types;

import java.util.List;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Stadler76
 */
public enum NoseType {
	HUMAN,
	ANGEL,
	DEMON_COMMON,
	ALLIGATOR_MORPH,
	BAT_MORPH,
	CAT_MORPH,
	CAT_MORPH_PANTHER,
	COW_MORPH,
	DOG_MORPH,
	FOX_MORPH,
	HARPY,
	HORSE_MORPH,
	RABBIT_MORPH,
	RAT_MORPH,
	REINDEER_MORPH,
	SQUIRREL_MORPH,
	WOLF_MORPH,
	;

	private final List<String> descriptorsMasculine;
	private final List<String> descriptorsFeminine;
	private final String name;
	private final String namePlural;

	private NoseType() {
		this(Util.newArrayListOfValues(""), Util.newArrayListOfValues(""), "nose", "noses");
	}

	/**
	 * @param descriptorsMasculine The descriptors that are applied to a masculine form of this nose type.
	 * @param descriptorsFeminine The descriptors that are applied to a feminine form of this nose type.
	 * @param name The singular name of the nose associated with this nose type. This will usually just be "nose".
	 * @param namePlural The plural name of the noses associated with this nose type. This will usually just be "noses".
	 */
	private NoseType(List<String> descriptorsMasculine, List<String> descriptorsFeminine, String name, String namePlural) {
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
