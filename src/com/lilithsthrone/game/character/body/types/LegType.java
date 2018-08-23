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
 * @version 0.2.11
 * @author Innoxia
 */
public enum LegType implements BodyPartTypeInterface {

	HUMAN(FootStructure.PLANTIGRADE, FootType.HUMANOID, BodyCoveringType.HUMAN, Race.HUMAN),

	ANGEL(FootStructure.PLANTIGRADE, FootType.HUMANOID, BodyCoveringType.ANGEL, Race.ANGEL),

	COW_MORPH(FootStructure.UNGULIGRADE, FootType.HOOFS, BodyCoveringType.BOVINE_FUR, Race.COW_MORPH),
	
	DEMON_COMMON(FootStructure.PLANTIGRADE, FootType.HUMANOID, BodyCoveringType.DEMON_COMMON, Race.DEMON),
	DEMON_HOOFED(FootStructure.UNGULIGRADE, FootType.HOOFS, BodyCoveringType.DEMON_COMMON, Race.DEMON),
	
	DOG_MORPH(FootStructure.DIGITIGRADE, FootType.PAWS, BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),
	
	LYCAN(FootStructure.DIGITIGRADE, FootType.PAWS, BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),
	
	FOX_MORPH(FootStructure.DIGITIGRADE, FootType.PAWS, BodyCoveringType.FOX_FUR, Race.FOX_MORPH),

	SQUIRREL_MORPH(FootStructure.DIGITIGRADE, FootType.PAWS, BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH),

	RAT_MORPH(FootStructure.DIGITIGRADE, FootType.PAWS, BodyCoveringType.RAT_FUR, Race.RAT_MORPH),
	
	RABBIT_MORPH(FootStructure.DIGITIGRADE, FootType.PAWS, BodyCoveringType.RABBIT_FUR, Race.RABBIT_MORPH),

	BAT_MORPH(FootStructure.DIGITIGRADE, FootType.PAWS, BodyCoveringType.BAT_FUR, Race.BAT_MORPH),
	
	CAT_MORPH(FootStructure.DIGITIGRADE, FootType.PAWS, BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),

	ALLIGATOR_MORPH(FootStructure.PLANTIGRADE, FootType.HUMANOID, BodyCoveringType.ALLIGATOR_SCALES, Race.ALLIGATOR_MORPH),
	
	HORSE_MORPH(FootStructure.UNGULIGRADE, FootType.HOOFS, BodyCoveringType.HORSE_HAIR, Race.HORSE_MORPH),

	REINDEER_MORPH(FootStructure.UNGULIGRADE, FootType.HOOFS, BodyCoveringType.REINDEER_FUR, Race.REINDEER_MORPH),

	HARPY(FootStructure.DIGITIGRADE, FootType.TALONS, BodyCoveringType.FEATHERS, Race.HARPY);
	
	
	private FootStructure defaultFootStructure;
	private FootType footType;
	private BodyCoveringType skinType;
	private Race race;

	private LegType(FootStructure defaultFootStructure, FootType footType, BodyCoveringType skinType, Race race) {
		this.defaultFootStructure = defaultFootStructure;
		this.footType = footType;
		this.skinType = skinType;
		this.race = race;
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static LegType getTypeFromString(String value) {
		if(value.equals("IMP")) {
			value = "DEMON_COMMON";
		}
		return valueOf(value);
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

	public FootType getFootType() {
		return footType;
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

	@Override
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
			case DOG_MORPH:
				return "canine";
			case COW_MORPH:
				return "bovine";
			case SQUIRREL_MORPH:
				return "squirrel";
			case ALLIGATOR_MORPH:
				return "alligator";
			case HARPY:
				return "harpy";
			case HORSE_MORPH:
				return "equine";
			case REINDEER_MORPH:
				return "reindeer";
			case HUMAN:
				return "human";
			case LYCAN:
				return "wolf";
			case FOX_MORPH:
				return "fox";
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
		return this.getFootType().getSingularName();
	}
	
	public String getFeetNamePlural(GameCharacter gc) {
		return this.getFootType().getPluralName();
	}

	public String getFeetDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			switch(this){
				case ANGEL:
					return UtilText.returnStringAtRandom("delicate", "soft", "feminine");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "cat-like", "furry", "feline");
				case COW_MORPH:
					return UtilText.returnStringAtRandom("feminine", "cow-like", "bovine");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("delicate", "soft", "feminine");
				case DEMON_HOOFED:
					return UtilText.returnStringAtRandom("hard", "feminine");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "dog-like", "furry", "canine");
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
					return UtilText.returnStringAtRandom("soft", "feminine", "wolf-like", "furry");
				case FOX_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "fox-like", "furry");
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "squirrel-like", "furry");
				case BAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "bat-like", "furry");
				case RAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "rat-like", "furry");
				case RABBIT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "rabbit-like", "furry");
			}
		} else {
			switch(this){
				case ANGEL:
					return UtilText.returnStringAtRandom("soft", "delicate");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "delicate", "cat-like", "furry", "feline");
				case COW_MORPH:
					return UtilText.returnStringAtRandom("cow-like", "bovine");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("");
				case DEMON_HOOFED:
					return UtilText.returnStringAtRandom("hard");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("dog-like", "furry", "canine");
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
					return UtilText.returnStringAtRandom("wolf-like", "furry");
				case FOX_MORPH:
					return UtilText.returnStringAtRandom("fox-like", "furry");
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "squirrel-like", "furry");
				case BAT_MORPH:
					return UtilText.returnStringAtRandom("bat-like", "furry");
				case RAT_MORPH:
					return UtilText.returnStringAtRandom("rat-like", "furry");
				case RABBIT_MORPH:
					return UtilText.returnStringAtRandom("rabbit-like", "furry");
			}
		}
		return "";
	}
	
	public String getToesNameSingular(GameCharacter gc) {
		return this.getFootType().getToesSingularName();
	}
	
	public String getToesNamePlural(GameCharacter gc) {
		return this.getFootType().getToesPluralName();
	}
	
	public String getToesDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			switch(this){
				case ANGEL:
					return UtilText.returnStringAtRandom("delicate", "soft", "feminine");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "cat-like", "furry", "feline");
				case COW_MORPH:
					return UtilText.returnStringAtRandom("feminine", "cow-like", "bovine");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("delicate", "soft", "feminine");
				case DEMON_HOOFED:
					return UtilText.returnStringAtRandom("feminine", "hard");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "dog-like", "furry", "canine");
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
					return UtilText.returnStringAtRandom("soft", "feminine", "wolf-like", "furry");
				case FOX_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "fox-like", "furry");
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "squirrel-like", "furry");
				case BAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "bat-like", "furry");
				case RAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "rat-like", "furry");
				case RABBIT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "rabbit-like", "furry");
			}
		} else {
			switch(this){
				case ANGEL:
					return UtilText.returnStringAtRandom("soft", "delicate");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "delicate", "cat-like", "furry", "feline");
				case COW_MORPH:
					return UtilText.returnStringAtRandom("cow-like", "bovine");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("");
				case DEMON_HOOFED:
					return UtilText.returnStringAtRandom("hard");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("dog-like", "furry", "canine");
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
					return UtilText.returnStringAtRandom("wolf-like", "furry");
				case FOX_MORPH:
					return UtilText.returnStringAtRandom("fox-like", "furry");
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "squirrel-like", "furry");
				case BAT_MORPH:
					return UtilText.returnStringAtRandom("bat-like", "furry");
				case RAT_MORPH:
					return UtilText.returnStringAtRandom("rat-like", "furry");
				case RABBIT_MORPH:
					return UtilText.returnStringAtRandom("rabbit-like", "furry");
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