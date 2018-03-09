package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisModifier;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.1
 * @author Innoxia
 */
public enum PenisType implements BodyPartTypeInterface {
	NONE(null, TesticleType.NONE, null),

	HUMAN(BodyCoveringType.PENIS, TesticleType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.PENIS, TesticleType.ANGEL, Race.ANGEL),

	BOVINE(BodyCoveringType.PENIS, TesticleType.BOVINE, Race.COW_MORPH, PenisModifier.TAPERED, PenisModifier.VEINY, PenisModifier.SHEATHED),

	DEMON_COMMON(BodyCoveringType.PENIS, TesticleType.DEMON_COMMON, Race.DEMON, PenisModifier.RIBBED, PenisModifier.TENTACLED, PenisModifier.PREHENSILE),

	IMP(BodyCoveringType.PENIS, TesticleType.IMP, Race.IMP, PenisModifier.RIBBED, PenisModifier.TENTACLED, PenisModifier.PREHENSILE),
	
	CANINE(BodyCoveringType.PENIS, TesticleType.CANINE, Race.DOG_MORPH, PenisModifier.KNOTTED, PenisModifier.SHEATHED, PenisModifier.TAPERED),
	
	LUPINE(BodyCoveringType.PENIS, TesticleType.LUPINE, Race.WOLF_MORPH, PenisModifier.KNOTTED, PenisModifier.SHEATHED, PenisModifier.TAPERED),

	FELINE(BodyCoveringType.PENIS, TesticleType.FELINE, Race.CAT_MORPH, PenisModifier.BARBED, PenisModifier.SHEATHED),

	ALLIGATOR_MORPH(BodyCoveringType.PENIS, TesticleType.ALLIGATOR_MORPH, Race.ALLIGATOR_MORPH, PenisModifier.BLUNT),

	EQUINE(BodyCoveringType.PENIS, TesticleType.EQUINE, Race.HORSE_MORPH, PenisModifier.FLARED, PenisModifier.VEINY, PenisModifier.SHEATHED),

	REINDEER_MORPH(BodyCoveringType.PENIS, TesticleType.REINDEER_MORPH, Race.REINDEER_MORPH, PenisModifier.FLARED, PenisModifier.SHEATHED),

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
		return UtilText.returnStringAtRandom("cock", "dick", "shaft");
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return UtilText.returnStringAtRandom("cocks", "dick", "shafts");
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
						return UtilText.returnStringAtRandom("bovine", "bull-", "bull-like", "bestial");
					case CANINE:
						return UtilText.returnStringAtRandom("canine", "dog-", "dog-like", "bestial");
					case DEMON_COMMON:
						return UtilText.returnStringAtRandom("demonic", "demon-");
					case IMP:
						return UtilText.returnStringAtRandom("impish", "imp-");
					case EQUINE:
						return UtilText.returnStringAtRandom("equine", "horse-", "horse-like", "bestial");
					case REINDEER_MORPH:
						return UtilText.returnStringAtRandom("rangiferine", "reindeer-", "reindeer-like", "bestial");
					case FELINE:
						return UtilText.returnStringAtRandom("feline", "cat-", "cat-like", "bestial");
					case ALLIGATOR_MORPH:
						return UtilText.returnStringAtRandom("reptilian", "alligator-", "alligator-like");
					case HUMAN:
						return UtilText.returnStringAtRandom("");
					case NONE:
						return UtilText.returnStringAtRandom("");
					case SQUIRREL:
						return UtilText.returnStringAtRandom("rodent", "squirrel-", "squirrel-like");
					case LUPINE:
						return UtilText.returnStringAtRandom("lupine", "wolf-", "wolf-like", "bestial");
				}
				break;
			case 1:
				return UtilText.returnStringAtRandom(
						gc.getPenisSize().getDescriptor(),
						gc.getPenisGirth()==PenisGirth.TWO_AVERAGE?"":gc.getPenisGirth().getName());
			default:
				if(Main.game.isInSex()) {
					return UtilText.returnStringAtRandom("hard", "throbbing");
				} else {
					return UtilText.returnStringAtRandom(
							gc.getPenisSize().getDescriptor(),
							gc.getPenisGirth()==PenisGirth.TWO_AVERAGE?"":gc.getPenisGirth().getName());
				}
		}
		return "";
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
			case IMP:
				return "impish";
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
