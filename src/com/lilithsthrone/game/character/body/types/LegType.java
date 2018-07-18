package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.2.8
 * @author Innoxia
 */
public enum LegType implements BodyPartTypeInterface {

	HUMAN(FootStructure.PLANTIGRADE, BodyCoveringType.HUMAN, Race.HUMAN),

	ANGEL(FootStructure.PLANTIGRADE, BodyCoveringType.ANGEL, Race.ANGEL),

	COW_MORPH(FootStructure.UNGULIGRADE, BodyCoveringType.BOVINE_FUR, Race.COW_MORPH),
	
	DEMON_COMMON(FootStructure.PLANTIGRADE, BodyCoveringType.DEMON_COMMON, Race.DEMON),
	DEMON_HOOFED(FootStructure.UNGULIGRADE, BodyCoveringType.DEMON_COMMON, Race.DEMON),
	
	IMP(FootStructure.PLANTIGRADE, BodyCoveringType.IMP, Race.IMP),

	DOG_MORPH(FootStructure.DIGITIGRADE, BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),
	
	LYCAN(FootStructure.DIGITIGRADE, BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),
	
	FOX_MORPH(FootStructure.DIGITIGRADE, BodyCoveringType.FOX_FUR, Race.FOX_MORPH),

	SQUIRREL_MORPH(FootStructure.DIGITIGRADE, BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH),

	RAT_MORPH(FootStructure.DIGITIGRADE, BodyCoveringType.RAT_FUR, Race.RAT_MORPH),
	
	RABBIT_MORPH(FootStructure.DIGITIGRADE, BodyCoveringType.RABBIT_FUR, Race.RABBIT_MORPH),

	BAT_MORPH(FootStructure.DIGITIGRADE, BodyCoveringType.BAT_FUR, Race.BAT_MORPH),
	
	CAT_MORPH(FootStructure.DIGITIGRADE, BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),

	ALLIGATOR_MORPH(FootStructure.PLANTIGRADE, BodyCoveringType.ALLIGATOR_SCALES, Race.ALLIGATOR_MORPH),
	
	HORSE_MORPH(FootStructure.UNGULIGRADE, BodyCoveringType.HORSE_HAIR, Race.HORSE_MORPH),

	REINDEER_MORPH(FootStructure.UNGULIGRADE, BodyCoveringType.REINDEER_FUR, Race.REINDEER_MORPH),

	HARPY(FootStructure.DIGITIGRADE, BodyCoveringType.FEATHERS, Race.HARPY);
	
	
	private FootStructure defaultFootStructure;
	private BodyCoveringType skinType;
	private Race race;

	private LegType(FootStructure defaultFootStructure, BodyCoveringType skinType, Race race) {
		this.defaultFootStructure = defaultFootStructure;
		this.skinType = skinType;
		this.race = race;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "a pair of";
	}

	@Override
	public boolean isDefaultPlural() {
		return true;
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return "leg";
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return "legs";
	}

	public FootStructure getDefaultFootStructure() {
		return defaultFootStructure;
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("delicate", "radiant");
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "anthropomorphic cat-like");
			case COW_MORPH:
				return UtilText.returnStringAtRandom("hoofed", "furry", "fur-coated", "anthropomorphic cow-like");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("flawless");
			case DEMON_HOOFED:
				return UtilText.returnStringAtRandom("hoofed", "flawless");
			case IMP:
				return UtilText.returnStringAtRandom("");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "anthropomorphic dog-like");
			case ALLIGATOR_MORPH:
				return UtilText.returnStringAtRandom("scaly", "reptile-like");
			case HARPY:
				return UtilText.returnStringAtRandom("scaly", "bird-like");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("hoofed", "hair-coated", "anthropomorphic horse-like");
			case REINDEER_MORPH:
				return UtilText.returnStringAtRandom("hoofed", "hair-coated", "anthropomorphic reindeer-like");
			case HUMAN:
				return UtilText.returnStringAtRandom("");
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "anthropomorphic squirrel-like");
			case LYCAN:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "anthropomorphic wolf-like");
			case FOX_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "anthropomorphic fox-like");
			case RAT_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "anthropomorphic rat-like");
			case RABBIT_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "anthropomorphic rabbit-like");
			case BAT_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "anthropomorphic bat-like");
		}
		return "";
	}
	
	public String getTransformName() {
		switch(this){
			case ANGEL:
				return "angelic";
			case CAT_MORPH:
				return "feline";
			case DEMON_COMMON:
				return "demonic";
			case DEMON_HOOFED:
				return "demonic-hoofed";
			case IMP:
				return "impish";
			case DOG_MORPH:
				return "canine";
			case COW_MORPH:
				return "bovine";
			case SQUIRREL_MORPH:
				return "furry";
			case ALLIGATOR_MORPH:
				return "alligator";
			case HARPY:
				return "feathered";
			case HORSE_MORPH:
				return "equine";
			case REINDEER_MORPH:
				return "rangiferine";
			case HUMAN:
				return "human";
			case LYCAN:
				return "lupine";
			case FOX_MORPH:
				return "vulpine";
			case BAT_MORPH:
				return "bat";
			case RAT_MORPH:
				return "rat";
			case RABBIT_MORPH:
				return "rabbit";
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
	
	public String getFeetNameSingular(GameCharacter gc) {
		switch(this){
			case COW_MORPH:
			case HORSE_MORPH:
			case REINDEER_MORPH:
			case DEMON_HOOFED:
				return UtilText.returnStringAtRandom("hoof");
			case HARPY:
				return UtilText.returnStringAtRandom("foot", "talon");
			default:
				return UtilText.returnStringAtRandom("foot");
		}
	}
	
	public String getFeetNamePlural(GameCharacter gc) {
		switch(this){
		case COW_MORPH:
			case HORSE_MORPH:
			case REINDEER_MORPH:
			case DEMON_HOOFED:
				return UtilText.returnStringAtRandom("hoofs");
			case HARPY:
				return UtilText.returnStringAtRandom("feet", "talons");
			default:
				return UtilText.returnStringAtRandom("feet");
		}
	}

	public String getFeetDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			switch(this){
				case ANGEL:
					return UtilText.returnStringAtRandom("delicate", "soft", "feminine");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "cat-like", "paw-like", "furry", "feline");
				case COW_MORPH:
					return UtilText.returnStringAtRandom("feminine", "cow-like", "bovine");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("delicate", "soft", "feminine");
				case DEMON_HOOFED:
					return UtilText.returnStringAtRandom("hard", "feminine");
				case IMP:
					return UtilText.returnStringAtRandom("delicate", "soft", "feminine");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "dog-like", "paw-like", "furry", "canine");
				case ALLIGATOR_MORPH:
					return UtilText.returnStringAtRandom("scaly", "reptile-like");
				case HARPY:
					return UtilText.returnStringAtRandom("clawed", "bird-like");
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("feminine", "horse-like", "equine");
				case REINDEER_MORPH:
					return UtilText.returnStringAtRandom("feminine", "reindeer-like", "rangiferine");
				case HUMAN:
					return UtilText.returnStringAtRandom("soft", "feminine");
				case LYCAN:
					return UtilText.returnStringAtRandom("soft", "feminine", "wolf-like", "furry", "paw-like");
				case FOX_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "fox-like", "furry", "paw-like");
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "squirrel-like", "paw-like", "furry");
				case BAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "bat-like", "paw-like", "furry");
				case RAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "rat-like", "paw-like", "furry");
				case RABBIT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "rabbit-like", "paw-like", "furry");
			}
		} else {
			switch(this){
				case ANGEL:
					return UtilText.returnStringAtRandom("soft", "delicate");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "delicate", "cat-like", "paw-like", "furry", "feline");
				case COW_MORPH:
					return UtilText.returnStringAtRandom("cow-like", "bovine");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("");
				case DEMON_HOOFED:
					return UtilText.returnStringAtRandom("hard");
				case IMP:
					return UtilText.returnStringAtRandom("");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("dog-like", "paw-like", "furry", "canine");
				case ALLIGATOR_MORPH:
					return UtilText.returnStringAtRandom("scaly", "reptile-like");
				case HARPY:
					return UtilText.returnStringAtRandom("clawed", "bird-like");
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("horse-like", "equine");
				case REINDEER_MORPH:
					return UtilText.returnStringAtRandom("feminine", "reindeer-like", "rangiferine");
				case HUMAN:
					return UtilText.returnStringAtRandom("");
				case LYCAN:
					return UtilText.returnStringAtRandom("wolf-like", "furry", "paw-like");
				case FOX_MORPH:
					return UtilText.returnStringAtRandom("fox-like", "furry", "paw-like");
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "squirrel-like", "paw-like", "furry");
				case BAT_MORPH:
					return UtilText.returnStringAtRandom("bat-like", "paw-like", "furry");
				case RAT_MORPH:
					return UtilText.returnStringAtRandom("rat-like", "paw-like", "furry");
				case RABBIT_MORPH:
					return UtilText.returnStringAtRandom("rabbit-like", "paw-like", "furry");
			}
		}
		return "";
	}
	
	public String getToesNameSingular(GameCharacter gc) {
		switch(this){
			case COW_MORPH:
			case HORSE_MORPH:
			case REINDEER_MORPH:
			case DEMON_HOOFED:
				return UtilText.returnStringAtRandom("hoof");
			case HARPY:
				return UtilText.returnStringAtRandom("claw");
			default:
				return UtilText.returnStringAtRandom("toe");
		}
	}
	
	public String getToesNamePlural(GameCharacter gc) {
		switch(this){
			case COW_MORPH:
			case HORSE_MORPH:
			case REINDEER_MORPH:
			case DEMON_HOOFED:
				return UtilText.returnStringAtRandom("hoof");
			case HARPY:
				return UtilText.returnStringAtRandom("claws");
			default:
				return UtilText.returnStringAtRandom("toes");
		}
	}
	
	public String getToesDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			switch(this){
				case ANGEL:
					return UtilText.returnStringAtRandom("delicate", "soft", "feminine");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "cat-like", "paw-like", "furry", "feline");
				case COW_MORPH:
					return UtilText.returnStringAtRandom("feminine", "cow-like", "bovine");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("delicate", "soft", "feminine");
				case DEMON_HOOFED:
					return UtilText.returnStringAtRandom("feminine", "hard");
				case IMP:
					return UtilText.returnStringAtRandom("delicate", "soft", "feminine");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "dog-like", "paw-like", "furry", "canine");
				case ALLIGATOR_MORPH:
					return UtilText.returnStringAtRandom("scaly", "reptile-like");
				case HARPY:
					return UtilText.returnStringAtRandom("clawed", "bird-like");
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("feminine", "horse-like", "equine");
				case REINDEER_MORPH:
					return UtilText.returnStringAtRandom("feminine", "reindeer-like", "rangiferine");
				case HUMAN:
					return UtilText.returnStringAtRandom("soft", "feminine");
				case LYCAN:
					return UtilText.returnStringAtRandom("soft", "feminine", "wolf-like", "furry", "paw-like");
				case FOX_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "fox-like", "furry", "paw-like");
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "squirrel-like", "paw-like", "furry");
				case BAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "bat-like", "paw-like", "furry");
				case RAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "rat-like", "paw-like", "furry");
				case RABBIT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "rabbit-like", "paw-like", "furry");
			}
		} else {
			switch(this){
				case ANGEL:
					return UtilText.returnStringAtRandom("soft", "delicate");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "delicate", "cat-like", "paw-like", "furry", "feline");
				case COW_MORPH:
					return UtilText.returnStringAtRandom("cow-like", "bovine");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("");
				case DEMON_HOOFED:
					return UtilText.returnStringAtRandom("hard");
				case IMP:
					return UtilText.returnStringAtRandom("");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("dog-like", "paw-like", "furry", "canine");
				case ALLIGATOR_MORPH:
					return UtilText.returnStringAtRandom("scaly", "reptile-like");
				case HARPY:
					return UtilText.returnStringAtRandom("clawed", "bird-like");
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("horse-like", "equine");
				case REINDEER_MORPH:
					return UtilText.returnStringAtRandom("feminine", "reindeer-like", "rangiferine");
				case HUMAN:
					return UtilText.returnStringAtRandom("");
				case LYCAN:
					return UtilText.returnStringAtRandom("wolf-like", "furry", "paw-like");
				case FOX_MORPH:
					return UtilText.returnStringAtRandom("fox-like", "furry", "paw-like");
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "squirrel-like", "paw-like", "furry");
				case BAT_MORPH:
					return UtilText.returnStringAtRandom("bat-like", "paw-like", "furry");
				case RAT_MORPH:
					return UtilText.returnStringAtRandom("rat-like", "paw-like", "furry");
				case RABBIT_MORPH:
					return UtilText.returnStringAtRandom("rabbit-like", "paw-like", "furry");
			}
		}
		return "";
	}
	
	private static Map<Race, List<LegType>> typesMap = new HashMap<>();
	public static List<LegType> getLegTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<LegType> types = new ArrayList<>();
		for(LegType type : LegType.values()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}