package com.base.game.character.body.types;

import com.base.game.character.GameCharacter;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public enum ArmType implements BodyPartTypeInterface {

	HUMAN(BodyCoveringType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.HUMAN, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON),

	DOG_MORPH(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),
	
	LYCAN(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),

	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),

	HORSE_MORPH(BodyCoveringType.HORSE_HAIR, Race.HORSE_MORPH),

	SLIME(BodyCoveringType.SLIME, Race.SLIME),

	HARPY(BodyCoveringType.FEATHERS, Race.HARPY),
	
	FOX_MORPH(BodyCoveringType.VULPINE_FUR, Race.FOX_MORPH);

	private BodyCoveringType skinType;
	private Race race;

	private ArmType(BodyCoveringType skinType, Race race) {
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
		switch(this){
			case HARPY:
				return UtilText.returnStringAtRandom("wing");
			default:
				return UtilText.returnStringAtRandom("arm");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			case HARPY:
				return UtilText.returnStringAtRandom("wings");
			default:
				return UtilText.returnStringAtRandom("arms");
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("slender", "delicate");
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("slender", "flawless");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated");
			case HARPY:
				return UtilText.returnStringAtRandom("feathered", "bird-like");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("");
			case HUMAN:
				return UtilText.returnStringAtRandom("");
			case LYCAN:
				return UtilText.returnStringAtRandom("furry", "fur-coated");
			case SLIME:
				return UtilText.returnStringAtRandom("slimy", "gooey");
			case FOX_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated");
			default:
				return UtilText.returnStringAtRandom("");
		}
	}

	@Override
	public BodyCoveringType getSkinType() {
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
					return UtilText.returnStringAtRandom("slender", "delicate", "soft", "feminine");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "cat-like", "paw-like", "furry", "feline");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("slender", "delicate", "soft", "feminine");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "dog-like", "paw-like", "furry", "canine");
				case HARPY:
					return UtilText.returnStringAtRandom("feminine", "feathered");
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("feminine", "equine");
				case HUMAN:
					return UtilText.returnStringAtRandom("soft", "feminine");
				case LYCAN:
					return UtilText.returnStringAtRandom("soft", "feminine", "wolf-like", "paw-like", "furry", "lupine");
				case SLIME:
					return UtilText.returnStringAtRandom("slimy", "feminine");
				case FOX_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "vixen-like", "fox-like", "paw-like", "furry", "vulpine", "fluffy");
				default:
					return UtilText.returnStringAtRandom("");
			}
		} else {
			switch(this){
				case ANGEL:
					return UtilText.returnStringAtRandom("soft", "delicate");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "delicate", "cat-like", "paw-like", "furry", "feline");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("slender");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("dog-like", "paw-like", "furry", "canine");
				case HARPY:
					return UtilText.returnStringAtRandom("feathered");
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("equine");
				case HUMAN:
					return UtilText.returnStringAtRandom("");
				case LYCAN:
					return UtilText.returnStringAtRandom("wolf-like", "furry", "paw-like");
				case SLIME:
					return UtilText.returnStringAtRandom("slimy");
				case FOX_MORPH:
					return (UtilText.returnStringAtRandom("fox-like", "furry", "vulpine");
				default:
					return UtilText.returnStringAtRandom("");
			}
		}
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
					return UtilText.returnStringAtRandom("feminine", "soft", "slender", "delicate");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("feminine", "soft", "padded", "feline");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("feminine", "soft", "slender", "delicate");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("feminine", "soft", "padded", "canine");
				case HARPY:
					return UtilText.returnStringAtRandom("feminine", "feathered");
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("feminine", "equine");
				case HUMAN:
					return UtilText.returnStringAtRandom("feminine", "soft");
				case LYCAN:
					return UtilText.returnStringAtRandom("feminine", "soft", "padded", "wolf-like");
				case SLIME:
					return UtilText.returnStringAtRandom("feminine", "slimy");
				case FOX_MORPH:
					return UtilText.returnStringAtRandom("feminine", "soft", "vixen-like", "fox-like", "padded", "vulpine");
				default:
					return UtilText.returnStringAtRandom("");
			}
		} else {
			switch(this){
				case ANGEL:
					return UtilText.returnStringAtRandom("soft", "delicate");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "delicate", "padded", "feline");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("slender");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("dog-like", "padded", "canine");
				case HARPY:
					return UtilText.returnStringAtRandom("feathered");
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("equine");
				case HUMAN:
					return UtilText.returnStringAtRandom("");
				case LYCAN:
					return UtilText.returnStringAtRandom("wolf-like", "padded");
				case SLIME:
					return UtilText.returnStringAtRandom("slimy");
				case FOX_MORPH:
					return UtilText.returnStringAtRandom("fox-like", "padded", "vulpine");
				default:
					return UtilText.returnStringAtRandom("");
			}
		}
	}
	
}
