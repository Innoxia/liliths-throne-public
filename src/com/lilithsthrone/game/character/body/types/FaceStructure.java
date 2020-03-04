package com.lilithsthrone.game.character.body.types;

import java.util.List;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Stadler76
 */
public enum FaceStructure {

	HUMAN(Util.newArrayListOfValues("")),
	ANGEL(Util.newArrayListOfValues("perfect", "flawless", "angelic")),
	DEMON_COMMON(Util.newArrayListOfValues("perfect", "flawless", "demonic")),
	ALLIGATOR_MORPH(Util.newArrayListOfValues("anthropomorphic alligator-like", "alligator-like", "reptile")),
	BAT_MORPH(Util.newArrayListOfValues("anthropomorphic bat-like", "bat-like")),
	CAT_MORPH(Util.newArrayListOfValues("anthropomorphic cat-like", "cat-like", "feline")),
	CAT_MORPH_PANTHER(Util.newArrayListOfValues("anthropomorphic panther-like", "panther-like", "panther")),
	COW_MORPH(Util.newArrayListOfValues("anthropomorphic cow-like", "cow-like", "bovine")),
	DOG_MORPH(Util.newArrayListOfValues("anthropomorphic dog-like", "dog-like", "canine")),
	FOX_MORPH(Util.newArrayListOfValues("anthropomorphic fox-like", "fox-like")),
	HARPY(Util.newArrayListOfValues("anthropomorphic bird-like", "bird-like")),
	HORSE_MORPH(Util.newArrayListOfValues("anthropomorphic horse-like", "horse-like", "equine")),
	RABBIT_MORPH(Util.newArrayListOfValues("anthropomorphic rabbit-like", "rabbit-like")),
	RAT_MORPH(Util.newArrayListOfValues("anthropomorphic rat-like", "rat-like", "rodent")),
	REINDEER_MORPH(Util.newArrayListOfValues("anthropomorphic reindeer-like", "reindeer-like", "rangiferine")),
	SQUIRREL_MORPH(Util.newArrayListOfValues("anthropomorphic squirrel-like", "squirrel-like", "rodent")),
	WOLF_MORPH(Util.newArrayListOfValues("anthropomorphic wolf-like", "wolf-like")),
	;

	private final List<String> descriptorsMasculine;
	private final List<String> descriptorsFeminine;
	private final List<String> names;
	private final List<String> namesPlural;

	private FaceStructure(List<String> descriptors) {
		this(descriptors, descriptors, null, null);
	}

	private FaceStructure(List<String> descriptorsMasculine, List<String> descriptorsFeminine) {
		this(descriptorsMasculine, descriptorsFeminine, null, null);
	}

	/**
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this face type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this face type.
	 * @param names A list of singular names for this face type. Pass in null to use generic names.
	 * @param namesPlural A list of plural names for this face type. Pass in null to use generic names.
	 */
	private FaceStructure(List<String> descriptorsMasculine, List<String> descriptorsFeminine, List<String> names, List<String> namesPlural) {
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		this.names = names;
		this.namesPlural = namesPlural;
	}

	public List<String> getDescriptorsMasculine() {
		return descriptorsMasculine;
	}

	public List<String> getDescriptorsFeminine() {
		return descriptorsFeminine;
	}

	public List<String> getNames() {
		return names;
	}

	public List<String> getNamesPlural() {
		return namesPlural;
	}
}
