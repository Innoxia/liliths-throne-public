package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.PenisModifier;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum PenisType implements BodyPartTypeInterface {
	NONE(null, TesticleType.NONE, null),

	HUMAN(BodyCoveringType.PENIS, TesticleType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.PENIS, TesticleType.ANGEL, Race.ANGEL),

	BOVINE(BodyCoveringType.PENIS, TesticleType.BOVINE, Race.COW_MORPH, PenisModifier.TAPERED, PenisModifier.VEINY, PenisModifier.SHEATHED),

	DEMON_COMMON(BodyCoveringType.PENIS, TesticleType.DEMON_COMMON, Race.DEMON, PenisModifier.RIBBED, PenisModifier.TENTACLED, PenisModifier.PREHENSILE),

	CANINE(BodyCoveringType.PENIS, TesticleType.CANINE, Race.DOG_MORPH, PenisModifier.KNOTTED, PenisModifier.SHEATHED, PenisModifier.TAPERED),
	
	LUPINE(BodyCoveringType.PENIS, TesticleType.LUPINE, Race.WOLF_MORPH, PenisModifier.KNOTTED, PenisModifier.SHEATHED, PenisModifier.TAPERED),

	FELINE(BodyCoveringType.PENIS, TesticleType.FELINE, Race.CAT_MORPH, PenisModifier.BARBED, PenisModifier.SHEATHED),

	ALLIGATOR_MORPH(BodyCoveringType.PENIS, TesticleType.ALLIGATOR_MORPH, Race.ALLIGATOR_MORPH, PenisModifier.BLUNT),

	EQUINE(BodyCoveringType.PENIS, TesticleType.EQUINE, Race.HORSE_MORPH, PenisModifier.FLARED, PenisModifier.VEINY, PenisModifier.SHEATHED),

	REINDEER_MORPH(BodyCoveringType.PENIS, TesticleType.REINDEER_MORPH, Race.REINDEER_MORPH, PenisModifier.FLARED, PenisModifier.SHEATHED),

	SLIME(BodyCoveringType.PENIS, TesticleType.SLIME, Race.SLIME),

	AVIAN(BodyCoveringType.PENIS, TesticleType.AVIAN, Race.HARPY, PenisModifier.SHEATHED),
	
	SQUIRREL(BodyCoveringType.PENIS, TesticleType.SQUIRREL, Race.SQUIRREL_MORPH, PenisModifier.SHEATHED);

	
	private BodyCoveringType skinType;
	private TesticleType testicleType;
	private Race race;
	private List<PenisModifier> defaultPenisModifiers;

	private PenisType(BodyCoveringType skinType, TesticleType testicleType, Race race, PenisModifier... defaultPenisModifiers) {
		this.skinType = skinType;
		this.race = race;
		this.testicleType=testicleType;
		
		this.defaultPenisModifiers = new ArrayList<>();
		Collections.addAll(this.defaultPenisModifiers, defaultPenisModifiers);
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "";
	}

	@Override
	public boolean isDefaultPlural() {
		return false;
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("cock", "shaft");
			case AVIAN:
				return UtilText.returnStringAtRandom("cock", "shaft");
			case BOVINE:
				return UtilText.returnStringAtRandom("bull-cock", "bovine cock", "bull-like cock", "bull-dick");
			case CANINE:
				return UtilText.returnStringAtRandom("dog-cock", "canine cock", "dog-like cock", "dog-dick");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("cock", "demon-cock", "demon-dick");
			case EQUINE:
				return UtilText.returnStringAtRandom("horse-cock", "equine cock", "horse-like cock", "horse-dick");
			case REINDEER_MORPH:
				return UtilText.returnStringAtRandom("reindeer-cock", "rangiferine cock", "reindeer-like cock", "reindeer-dick");
			case FELINE:
				return UtilText.returnStringAtRandom("cat-cock", "feline cock", "cat-like cock", "cat-dick");
			case ALLIGATOR_MORPH:
				return UtilText.returnStringAtRandom("reptile-cock", "reptile cock", "reptile-like cock", "reptile-dick");
			case HUMAN:
				return UtilText.returnStringAtRandom("cock", "shaft");
			case NONE:
				return UtilText.returnStringAtRandom("");
			case SLIME:
				return UtilText.returnStringAtRandom("slime-cock", "slimy cock", "slime-dick");
			case SQUIRREL:
				return UtilText.returnStringAtRandom("squirrel-cock", "rodent cock", "squirrel-like cock", "squirrel-dick");
			default:
				return UtilText.returnStringAtRandom("cock", "shaft");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("cocks", "shafts");
			case AVIAN:
				return UtilText.returnStringAtRandom("cocks", "shafts");
			case BOVINE:
				return UtilText.returnStringAtRandom("bull-cocks", "bovine cocks", "bull-like cocks", "bull-dicks");
			case CANINE:
				return UtilText.returnStringAtRandom("dog-cocks", "canine cocks", "dog-like cocks", "dog-dicks");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("cocks", "demon-cocks", "demon-dicks");
			case EQUINE:
				return UtilText.returnStringAtRandom("horse-cocks", "equine cocks", "horse-like cocks", "horse-dicks");
			case REINDEER_MORPH:
				return UtilText.returnStringAtRandom("reindeer-cocks", "rangiferine cocks", "reindeer-like cocks", "reindeer-dicks");
			case FELINE:
				return UtilText.returnStringAtRandom("cat-cocks", "feline cocks", "cat-like cocks", "cat-dicks");
			case ALLIGATOR_MORPH:
				return UtilText.returnStringAtRandom("reptile-cocks", "reptile cocks", "reptile-like cocks", "reptile-dicks");
			case HUMAN:
				return UtilText.returnStringAtRandom("cocks", "shafts");
			case NONE:
				return UtilText.returnStringAtRandom("");
			case SLIME:
				return UtilText.returnStringAtRandom("slime-cocks", "slimy cocks", "slime-dicks");
			case SQUIRREL:
				return UtilText.returnStringAtRandom("squirrel-cocks", "rodent cocks", "squirrel-like cocks", "squirrel-dicks");
			default:
				return UtilText.returnStringAtRandom("cocks", "shafts");
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		// Randomly give a size/aroused or type-specific descriptor:
		switch(Util.random.nextInt(3)){
			case 0:
				switch(this){
					case ANGEL:
						return UtilText.returnStringAtRandom("angelic");
					case AVIAN:
						return UtilText.returnStringAtRandom("avian");
					case BOVINE:
						return UtilText.returnStringAtRandom("bestial", "thick");
					case CANINE:
						return UtilText.returnStringAtRandom("bestial");
					case DEMON_COMMON:
						return UtilText.returnStringAtRandom("demonic");
					case EQUINE:
						return UtilText.returnStringAtRandom("bestial", "thick");
					case REINDEER_MORPH:
						return UtilText.returnStringAtRandom("bestial", "thick");
					case FELINE:
						return UtilText.returnStringAtRandom("bestial");
					case ALLIGATOR_MORPH:
						return UtilText.returnStringAtRandom("reptilian");
					case HUMAN:
						return UtilText.returnStringAtRandom("");
					case NONE:
						return UtilText.returnStringAtRandom("");
					case SLIME:
						return UtilText.returnStringAtRandom("gooey");
					case SQUIRREL:
						return UtilText.returnStringAtRandom("");
					default:
						return UtilText.returnStringAtRandom("");
				}
			case 1:
				return gc.getPenisSize().getDescriptor();
			default:
				if(Main.game.isInSex()) {
					return UtilText.returnStringAtRandom("hard", "throbbing");
				} else {
					return gc.getPenisSize().getDescriptor();
				}
		}
		
	}
	
	public String getTransformName() {
		switch(this){
			case ANGEL:
				return "angelic";
			case AVIAN:
				return "avian";
			case BOVINE:
				return "bovine";
			case CANINE:
				return "canine";
			case DEMON_COMMON:
				return "demonic";
			case EQUINE:
				return "equine";
			case REINDEER_MORPH:
				return "rangiferine";
			case FELINE:
				return "feline";
			case HUMAN:
				return "human";
			case NONE:
				return "none";
			case SLIME:
				return "slime";
			case SQUIRREL:
				return "squirrel";
			case ALLIGATOR_MORPH:
				return "alligator";
			case LUPINE:
				return "lupine";
		}
		return "";
	}

	@Override
	public BodyCoveringType getBodyCoveringType() {
		return skinType;
	}
	
	@Override
	public Race getRace() {
		return race;
	}

	
	public String getPenisHeadName(GameCharacter gc) {
		switch(this){
			case EQUINE:
				return UtilText.returnStringAtRandom("head");
			case NONE:
				return UtilText.returnStringAtRandom("");
			default:
				return UtilText.returnStringAtRandom("head", "tip");
		}
	}
	
	public String getPenisHeadDescriptor(GameCharacter gc) {
		switch(this){
			case BOVINE:
				return UtilText.returnStringAtRandom("wide", "flared", "flat");
			case CANINE:
				return UtilText.returnStringAtRandom("tapered", "pointed");
			case EQUINE:
				return UtilText.returnStringAtRandom("wide", "flared", "flat");
			case ALLIGATOR_MORPH:
				return UtilText.returnStringAtRandom("blunt");
			case FELINE:
				return UtilText.returnStringAtRandom("");
			case NONE:
				return UtilText.returnStringAtRandom("");
			default:
				return UtilText.returnStringAtRandom("");
		}
	}
	
	public String getCumName(GameCharacter gc) {
		switch(this){
			case BOVINE:
				return UtilText.returnStringAtRandom("bull-cum", "bull-cream", "cow-jism", "bull-jizz", "cow-seed");
			case CANINE:
				if(gc.getRace()==Race.WOLF_MORPH) {
					return UtilText.returnStringAtRandom("wolf-cum", "wolf-cream", "wolf-jism", "wolf-jizz", "wolf-seed");
				} else {
					return UtilText.returnStringAtRandom("dog-cum", "dog-cream", "dog-jism", "dog-jizz", "dog-seed");
				}
			case EQUINE:
				return UtilText.returnStringAtRandom("horse-cum", "horse-cream", "horse-jism", "horse-jizz", "horse-seed");
			case FELINE:
				return UtilText.returnStringAtRandom("cat-cum", "cat-cream", "cat-jism", "cat-jizz", "cat-seed");
			case NONE:
				return UtilText.returnStringAtRandom("");
			default:
				return UtilText.returnStringAtRandom("cum", "cream", "jism", "jizz", "load", "seed", "spooge");
		}
	}
	
	public String getCumDescriptor() {
		switch(this){
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("corruptive", "corrupted", "potent", "sticky", "hot", "salty");
			case NONE:
				return UtilText.returnStringAtRandom("");
			default:
				return UtilText.returnStringAtRandom("potent", "sticky", "hot", "salty");
		}
	}


	public TesticleType getTesticleType() {
		return testicleType;
	}

	public List<PenisModifier> getDefaultPenisModifiers() {
		return defaultPenisModifiers;
	}

}
