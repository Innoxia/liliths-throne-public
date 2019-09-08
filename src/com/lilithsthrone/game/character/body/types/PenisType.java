package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public enum PenisType implements BodyPartTypeInterface {
	NONE(null, TesticleType.NONE, Race.NONE),

	DILDO(BodyCoveringType.DILDO, TesticleType.DILDO, Race.NONE),
	
	HUMAN(BodyCoveringType.PENIS, TesticleType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.PENIS, TesticleType.ANGEL, Race.ANGEL),

	BOVINE(BodyCoveringType.PENIS, TesticleType.BOVINE, Race.COW_MORPH, PenetrationModifier.TAPERED, PenetrationModifier.VEINY, PenetrationModifier.SHEATHED),

	DEMON_COMMON(BodyCoveringType.PENIS, TesticleType.DEMON_COMMON, Race.DEMON, PenetrationModifier.RIBBED, PenetrationModifier.TENTACLED, PenetrationModifier.PREHENSILE),
	
	CANINE(BodyCoveringType.PENIS, TesticleType.CANINE, Race.DOG_MORPH, PenetrationModifier.KNOTTED, PenetrationModifier.SHEATHED, PenetrationModifier.TAPERED),
	
	LUPINE(BodyCoveringType.PENIS, TesticleType.LUPINE, Race.WOLF_MORPH, PenetrationModifier.KNOTTED, PenetrationModifier.SHEATHED, PenetrationModifier.TAPERED),
	
	VULPINE(BodyCoveringType.PENIS, TesticleType.FOX_MORPH, Race.FOX_MORPH, PenetrationModifier.KNOTTED, PenetrationModifier.SHEATHED, PenetrationModifier.TAPERED),

	FELINE(BodyCoveringType.PENIS, TesticleType.FELINE, Race.CAT_MORPH, PenetrationModifier.BARBED, PenetrationModifier.SHEATHED),

	ALLIGATOR_MORPH(BodyCoveringType.PENIS, TesticleType.ALLIGATOR_MORPH, Race.ALLIGATOR_MORPH, PenetrationModifier.BLUNT),

	EQUINE(BodyCoveringType.PENIS, TesticleType.EQUINE, Race.HORSE_MORPH, PenetrationModifier.FLARED, PenetrationModifier.VEINY, PenetrationModifier.SHEATHED),

	REINDEER_MORPH(BodyCoveringType.PENIS, TesticleType.REINDEER_MORPH, Race.REINDEER_MORPH, PenetrationModifier.TAPERED, PenetrationModifier.SHEATHED),

	AVIAN(BodyCoveringType.PENIS, TesticleType.AVIAN, Race.HARPY, PenetrationModifier.SHEATHED),
	
	SQUIRREL(BodyCoveringType.PENIS, TesticleType.SQUIRREL, Race.SQUIRREL_MORPH, PenetrationModifier.SHEATHED),
	
	RAT_MORPH(BodyCoveringType.PENIS, TesticleType.RAT_MORPH, Race.RAT_MORPH, PenetrationModifier.SHEATHED),
	
	RABBIT_MORPH(BodyCoveringType.PENIS, TesticleType.RABBIT_MORPH, Race.RABBIT_MORPH, PenetrationModifier.SHEATHED),
	
	BAT_MORPH(BodyCoveringType.PENIS, TesticleType.BAT_MORPH, Race.BAT_MORPH, PenetrationModifier.SHEATHED);

	
	private BodyCoveringType skinType;
	private TesticleType testicleType;
	private Race race;
	private List<PenetrationModifier> defaultPenisModifiers;

	private PenisType(BodyCoveringType skinType, TesticleType testicleType, Race race, PenetrationModifier... defaultPenisModifiers) {
		this.skinType = skinType;
		this.race = race;
		this.testicleType=testicleType;
		
		this.defaultPenisModifiers = new ArrayList<>();
		Collections.addAll(this.defaultPenisModifiers, defaultPenisModifiers);
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static PenisType getTypeFromString(String value) {
		if(value.equals("IMP")) {
			value = "DEMON_COMMON";
		}
		return valueOf(value);
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
		if(this==PenisType.DILDO) {
			return UtilText.returnStringAtRandom("dildo", "cock");
		} else {
			return UtilText.returnStringAtRandom("cock", "cock", "cock", "dick", "dick", "shaft");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(this==PenisType.DILDO) {
			return UtilText.returnStringAtRandom("dildoes", "cocks");
		} else {
			return UtilText.returnStringAtRandom("cocks", "cocks", "cocks", "dicks", "dicks", "shafts");
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
						return UtilText.returnStringAtRandom("bovine", "bull-like", "bestial");
					case CANINE:
						return UtilText.returnStringAtRandom("canine", "dog-like", "bestial");
					case DEMON_COMMON:
						return UtilText.returnStringAtRandom("demonic");
					case EQUINE:
						return UtilText.returnStringAtRandom("equine", "horse-like", "bestial");
					case REINDEER_MORPH:
						return UtilText.returnStringAtRandom("rangiferine", "reindeer-like", "bestial");
					case FELINE:
						return UtilText.returnStringAtRandom("feline", "cat-like", "bestial");
					case ALLIGATOR_MORPH:
						return UtilText.returnStringAtRandom("reptilian", "alligator-like");
					case HUMAN:
						return UtilText.returnStringAtRandom("");
					case NONE:
						return UtilText.returnStringAtRandom("");
					case SQUIRREL:
						return UtilText.returnStringAtRandom("rodent", "squirrel-like");
					case LUPINE:
						return UtilText.returnStringAtRandom("lupine", "wolf-like", "bestial");
					case VULPINE:
						return UtilText.returnStringAtRandom("vulpine", "fox-like", "bestial");
					case BAT_MORPH:
						return UtilText.returnStringAtRandom("bat-", "bat-like");
					case RAT_MORPH:
						return UtilText.returnStringAtRandom("rodent", "rat-like");
					case RABBIT_MORPH:
						return UtilText.returnStringAtRandom("rabbit-", "rabbit-like");
					case DILDO:
						return UtilText.returnStringAtRandom("rubber", "rubbery", "silicone", "artificial");
				}
				break;
			case 1:
				return UtilText.returnStringAtRandom(
						gc.getPenisSize()==PenisSize.TWO_AVERAGE?"":gc.getPenisSize().getDescriptor(),
						gc.getPenisGirth()==PenisGirth.TWO_AVERAGE?"":gc.getPenisGirth().getName());
			default:
				if(Main.game.isInSex() && this!=PenisType.DILDO) {
					return UtilText.returnStringAtRandom("hard", "throbbing");
				} else {
					return UtilText.returnStringAtRandom(
							gc.getPenisSize()==PenisSize.TWO_AVERAGE?"":gc.getPenisSize().getDescriptor(),
							gc.getPenisGirth()==PenisGirth.TWO_AVERAGE?"":gc.getPenisGirth().getName());
				}
		}
		return "";
	}

	@Override
	public String getTransformName() {
		switch(this){
			case ANGEL:
				return "angelic";
			case AVIAN:
				return "harpy";
			case BOVINE:
				return "bovine";
			case CANINE:
				return "canine";
			case DEMON_COMMON:
				return "demonic";
			case EQUINE:
				return "equine";
			case REINDEER_MORPH:
				return "reindeer";
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
				return "wolf";
			case VULPINE:
				return "fox";
			case BAT_MORPH:
				return "bat";
			case RAT_MORPH:
				return "rat";
			case RABBIT_MORPH:
				return "rabbit";
			case DILDO:
				return "artificial";
		}
		return "";
	}

	@Override
	public BodyCoveringType getBodyCoveringType(Body body) {
		return skinType;
	}
	
	@Override
	public Race getRace() {
		return race;
	}

	
	public String getPenisHeadName(GameCharacter gc) {
		return UtilText.returnStringAtRandom("head", "tip");
	}
	
	public String getPenisHeadDescriptor(GameCharacter gc) {
		for(PenetrationModifier mod : PenetrationModifier.values()) {
			if(gc.getPenisModifiers().contains(PenetrationModifier.BLUNT)) {
				switch(mod) {
					case BLUNT:
						return UtilText.returnStringAtRandom("blunt");
					case FLARED:
						return UtilText.returnStringAtRandom("wide", "flared", "flat");
					case TAPERED:
						return UtilText.returnStringAtRandom("tapered", "pointed");
					case KNOTTED:
					case PREHENSILE:
					case RIBBED:
					case SHEATHED:
					case BARBED:
					case TENTACLED:
					case VEINY:
						break;
				}
			}
		}
		return "";
	}
	
	public String getCumName(GameCharacter gc) {
		String prefix = "";
		switch(this){
			case BOVINE:
				prefix = UtilText.returnStringAtRandom("bull-", "cow-", "bovine-");
				break;
			case CANINE:
				prefix = UtilText.returnStringAtRandom("dog-", "canine-");
				break;
			case EQUINE:
				prefix = UtilText.returnStringAtRandom("horse-", "equine-");
				break;
			case FELINE:
				prefix = UtilText.returnStringAtRandom("cat-", "feline-");
				break;
			case NONE:
				return UtilText.returnStringAtRandom("");
			case ALLIGATOR_MORPH:
				prefix = UtilText.returnStringAtRandom("alligator-");
				break;
			case ANGEL:
				prefix = UtilText.returnStringAtRandom("angel-");
				break;
			case AVIAN:
				prefix = UtilText.returnStringAtRandom("harpy-", "avian-");
				break;
			case BAT_MORPH:
				prefix = UtilText.returnStringAtRandom("bat-");
				break;
			case DEMON_COMMON:
				prefix = UtilText.returnStringAtRandom("demon-");
				break;
			case HUMAN:
				break;
			case LUPINE:
				prefix = UtilText.returnStringAtRandom("wolf-", "lupine-");
				break;
			case VULPINE:
				prefix = UtilText.returnStringAtRandom("fox-", "vulpine-");
				break;
			case RAT_MORPH:
				prefix = UtilText.returnStringAtRandom("rat-", "rodent-");
				break;
			case RABBIT_MORPH:
				prefix = UtilText.returnStringAtRandom("rabbit-");
				break;
			case REINDEER_MORPH:
				prefix = UtilText.returnStringAtRandom("reindeer-");
				break;
			case SQUIRREL:
				prefix = UtilText.returnStringAtRandom("squirrel-", "rodent-");
				break;
			case DILDO:
				prefix = UtilText.returnStringAtRandom("stored", "preserved");
				break;
		}
		if(Math.random()>0.5f && !prefix.isEmpty()) {
			return prefix + UtilText.returnStringAtRandom("cum", "cream", "jism", "jizz", "seed", "spooge");
		} else {
			return UtilText.returnStringAtRandom("cum", "cream", "jism", "jizz", "load", "seed", "spooge");
		}
	}
	
	public String getCumDescriptor() {
		switch(this){
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("corruptive", "corrupted", "potent", "thick", "hot");
			case NONE:
				return UtilText.returnStringAtRandom("");
			default:
				return UtilText.returnStringAtRandom("potent", "thick", "hot");
		}
	}


	public TesticleType getTesticleType() {
		return testicleType;
	}

	public List<PenetrationModifier> getDefaultPenisModifiers() {
		return defaultPenisModifiers;
	}

}