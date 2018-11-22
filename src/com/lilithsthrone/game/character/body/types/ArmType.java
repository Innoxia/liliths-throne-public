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
public enum ArmType implements BodyPartTypeInterface {

	HUMAN(BodyCoveringType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.HUMAN, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON),

	COW_MORPH(BodyCoveringType.BOVINE_FUR, Race.COW_MORPH),
	
	DOG_MORPH(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),
	
	LYCAN(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),
	
	FOX_MORPH(BodyCoveringType.FOX_FUR, Race.FOX_MORPH),

	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),

	HORSE_MORPH(BodyCoveringType.HORSE_HAIR, Race.HORSE_MORPH),

	REINDEER_MORPH(BodyCoveringType.REINDEER_FUR, Race.REINDEER_MORPH),

	ALLIGATOR_MORPH(BodyCoveringType.ALLIGATOR_SCALES, Race.ALLIGATOR_MORPH),

	SQUIRREL_MORPH(BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH),
	
	RAT_MORPH(BodyCoveringType.RAT_FUR, Race.RAT_MORPH),

	RABBIT_MORPH(BodyCoveringType.RABBIT_FUR, Race.RABBIT_MORPH),
	
	BAT_MORPH(BodyCoveringType.BAT_SKIN, Race.BAT_MORPH) {
		@Override
		public boolean allowsFlight() {
			return true;
		}
	},
	
	HARPY(BodyCoveringType.FEATHERS, Race.HARPY) {
		@Override
		public boolean allowsFlight() {
			return true;
		}
	};
	
	private BodyCoveringType skinType;
	private Race race;
	
	private ArmType(BodyCoveringType skinType, Race race) {
		this.skinType = skinType;
		this.race = race;
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static ArmType getTypeFromString(String value) {
		if(value.equals("IMP")) {
			value = "DEMON_COMMON";
		}
		return valueOf(value);
	}
	
	public boolean allowsFlight() {
		return false;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getArmRows()==1) {
			return "a pair of";
		} else if(gc.getArmRows()==2) {
			return "two pairs of";
		} else {
			return "three pairs of";
		}
	}

	@Override
	public boolean isDefaultPlural() {
		return true;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		switch(this){
			case HARPY:
			case BAT_MORPH:
				return UtilText.returnStringAtRandom("wing");
			default:
				return UtilText.returnStringAtRandom("arm");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			case HARPY:
			case BAT_MORPH:
				return UtilText.returnStringAtRandom("wings");
			default:
				return UtilText.returnStringAtRandom("arms");
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("delicate");
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("flawless");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated");
			case COW_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated");
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated");
			case ALLIGATOR_MORPH:
				return UtilText.returnStringAtRandom("scaled", "reptile-like");
			case HARPY:
				return UtilText.returnStringAtRandom("feathered", "bird-like");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("");
			case REINDEER_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated");
			case HUMAN:
				return UtilText.returnStringAtRandom("");
			case LYCAN:
				return UtilText.returnStringAtRandom("furry", "fur-coated");
			case FOX_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated");
			case BAT_MORPH:
				return UtilText.returnStringAtRandom("bat-like");
			case RAT_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated");
			case RABBIT_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated");
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
	
	public String getHandsNameSingular(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("hand");
		}
	}
	
	public String getHandsNamePlural(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("hands");
		}
	}

	public String getHandsDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			switch(this){
				case ANGEL:
					return UtilText.returnStringAtRandom("delicate", "soft", "feminine");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "cat-like", "paw-like", "furry", "feline");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("delicate", "soft", "feminine");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "dog-like", "paw-like", "furry", "canine");
				case COW_MORPH:
					return UtilText.returnStringAtRandom("feminine", "bovine");
				case ALLIGATOR_MORPH:
					return UtilText.returnStringAtRandom("feminine", "scaled");
				case HARPY:
					return UtilText.returnStringAtRandom("feminine", "feathered");
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("feminine", "equine");
				case REINDEER_MORPH:
					return UtilText.returnStringAtRandom("feminine", "rangiferine");
				case HUMAN:
					return UtilText.returnStringAtRandom("soft", "feminine");
				case LYCAN:
					return UtilText.returnStringAtRandom("soft", "feminine", "wolf-like", "furry", "paw-like");
				case FOX_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "fox-like", "furry", "paw-like");
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "squirrel-like", "claw-like", "furry", "rodent");
				case RAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "rat-like", "claw-like", "furry", "rodent");
				case RABBIT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "rabbit-like", "paw-like", "furry");
				case BAT_MORPH:
					return UtilText.returnStringAtRandom("feminine", "bat-like");
			}
		} else {
			switch(this){
				case ANGEL:
					return UtilText.returnStringAtRandom("soft", "delicate");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "delicate", "cat-like", "paw-like", "furry", "feline");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("dog-like", "paw-like", "furry", "canine");
				case COW_MORPH:
					return UtilText.returnStringAtRandom("bovine");
				case ALLIGATOR_MORPH:
					return UtilText.returnStringAtRandom("scaled");
				case HARPY:
					return UtilText.returnStringAtRandom("feathered");
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("equine");
				case REINDEER_MORPH:
					return UtilText.returnStringAtRandom("rangiferine");
				case HUMAN:
					return UtilText.returnStringAtRandom("");
				case LYCAN:
					return UtilText.returnStringAtRandom("wolf-like", "furry", "paw-like");
				case FOX_MORPH:
					return UtilText.returnStringAtRandom("fox-like", "furry", "paw-like");
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "squirrel-like", "claw-like", "furry", "rodent");
				case RAT_MORPH:
					return UtilText.returnStringAtRandom("rat-like", "claw-like", "furry", "rodent");
				case RABBIT_MORPH:
					return UtilText.returnStringAtRandom("rabbit-like", "paw-like", "furry");
				case BAT_MORPH:
					return UtilText.returnStringAtRandom("bat-like");
			}
		}
		
		return "";
	}
	
	public String getFingersNameSingular(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("finger");
		}
	}
	
	public String getFingersNamePlural(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("fingers");
		}
	}

	public String getFingersDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			switch(this){
				case ANGEL:
					return UtilText.returnStringAtRandom("delicate", "soft", "feminine");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "padded", "feline");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("delicate", "soft", "feminine");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "padded", "canine");
				case COW_MORPH:
					return UtilText.returnStringAtRandom("feminine", "bovine");
				case ALLIGATOR_MORPH:
					return UtilText.returnStringAtRandom("feminine", "scaled");
				case HARPY:
					return UtilText.returnStringAtRandom("feminine", "feathered");
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("feminine", "equine");
				case REINDEER_MORPH:
					return UtilText.returnStringAtRandom("feminine", "rangiferine");
				case HUMAN:
					return UtilText.returnStringAtRandom("soft", "feminine");
				case LYCAN:
					return UtilText.returnStringAtRandom("soft", "feminine", "padded", "wolf-like");
				case FOX_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "padded", "fox-like");
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "clawed", "rodent");
				case RAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "rat-like", "claw-like", "furry", "rodent");
				case RABBIT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "rabbit-like", "paw-like", "furry");
				case BAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "bat-like");
			}
		} else {
			switch(this){
				case ANGEL:
					return UtilText.returnStringAtRandom("soft", "delicate");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "delicate", "padded", "feline");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("dog-like", "padded", "canine");
				case COW_MORPH:
					return UtilText.returnStringAtRandom("bovine");
				case ALLIGATOR_MORPH:
					return UtilText.returnStringAtRandom("scaled");
				case HARPY:
					return UtilText.returnStringAtRandom("feathered");
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("equine");
				case REINDEER_MORPH:
					return UtilText.returnStringAtRandom("rangiferine");
				case HUMAN:
					return UtilText.returnStringAtRandom("");
				case LYCAN:
					return UtilText.returnStringAtRandom("wolf-like", "padded");
				case FOX_MORPH:
					return UtilText.returnStringAtRandom("fox-like", "padded");
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "clawed", "rodent");
				case RAT_MORPH:
					return UtilText.returnStringAtRandom("rat-like", "claw-like", "furry", "rodent");
				case RABBIT_MORPH:
					return UtilText.returnStringAtRandom("rabbit-like", "paw-like", "furry");
				case BAT_MORPH:
					return UtilText.returnStringAtRandom("bat-like");
			}
		}
		
		return "";
	}
	
	private static Map<Race, List<ArmType>> typesMap = new HashMap<>();
	public static List<ArmType> getArmTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<ArmType> types = new ArrayList<>();
		for(ArmType type : ArmType.values()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}