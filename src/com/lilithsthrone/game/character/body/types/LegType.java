package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum LegType implements BodyPartTypeInterface {

	HUMAN(BodyCoveringType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.HUMAN, Race.ANGEL),

	COW_MORPH(BodyCoveringType.BOVINE_FUR, Race.COW_MORPH),
	
	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON),

	DOG_MORPH(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),
	
	LYCAN(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),

	SQUIRREL_MORPH(BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH),
	
	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),

	GATOR_MORPH(BodyCoveringType.SCALES_GATOR, Race.GATOR_MORPH),
	
	HORSE_MORPH(BodyCoveringType.HORSE_HAIR, Race.HORSE_MORPH),

	SLIME(BodyCoveringType.SLIME, Race.SLIME),

	HARPY( BodyCoveringType.FEATHERS, Race.HARPY);

	private BodyCoveringType skinType;
	private Race race;

	private LegType(BodyCoveringType skinType, Race race) {
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

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("slender", "delicate", "radiant");
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "anthropomorphic cat-like");
			case COW_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "anthropomorphic cow-like");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("slender", "flawless");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "anthropomorphic dog-like");
			case GATOR_MORPH:
				return UtilText.returnStringAtRandom("scaly", "reptile-like");
			case HARPY:
				return UtilText.returnStringAtRandom("scaly", "bird-like");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("hooved");
			case HUMAN:
				return UtilText.returnStringAtRandom("");
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "anthropomorphic squirrel-like");
			case LYCAN:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "anthropomorphic wolf-like");
			case SLIME:
				return UtilText.returnStringAtRandom("slimy", "gooey");
			default:
				return UtilText.returnStringAtRandom("");
		}
	}

	@Override
	public BodyCoveringType getBodyCoveringType() {
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}
	
	public String getFeetNameSingular(GameCharacter gc) {
		switch(this){
			case HARPY:
				return UtilText.returnStringAtRandom("foot", "talon");
			default:
				return UtilText.returnStringAtRandom("foot");
		}
	}
	
	public String getFeetNamePlural(GameCharacter gc) {
		switch(this){
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
					return UtilText.returnStringAtRandom("slender", "delicate", "soft", "feminine");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "cat-like", "paw-like", "furry", "feline");
				case COW_MORPH:
					return UtilText.returnStringAtRandom("feminine", "cow-like", "bovine");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("slender", "delicate", "soft", "feminine");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "dog-like", "paw-like", "furry", "canine");
				case GATOR_MORPH:
					return UtilText.returnStringAtRandom("scaly", "reptile-like");
				case HARPY:
					return UtilText.returnStringAtRandom("clawed", "bird-like");
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("feminine", "horse-like", "equine");
				case HUMAN:
					return UtilText.returnStringAtRandom("soft", "feminine");
				case LYCAN:
					return UtilText.returnStringAtRandom("soft", "feminine", "wolf-like", "furry", "paw-like");
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "squirrel-like", "paw-like", "furry");
				case SLIME:
					return UtilText.returnStringAtRandom("slimy", "feminine");
				default:
					return UtilText.returnStringAtRandom("");
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
					return UtilText.returnStringAtRandom("slender");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("dog-like", "paw-like", "furry", "canine");
				case GATOR_MORPH:
					return UtilText.returnStringAtRandom("scaly", "reptile-like");
				case HARPY:
					return UtilText.returnStringAtRandom("clawed", "bird-like");
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("horse-like", "equine");
				case HUMAN:
					return UtilText.returnStringAtRandom("");
				case LYCAN:
					return UtilText.returnStringAtRandom("wolf-like", "furry", "paw-like");
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "squirrel-like", "paw-like", "furry");
				case SLIME:
					return UtilText.returnStringAtRandom("slimy");
				default:
					return UtilText.returnStringAtRandom("");
			}
		}
	}
}
