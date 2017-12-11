package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum ArmType implements BodyPartTypeInterface {

	HUMAN(BodyCoveringType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.HUMAN, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON),

	COW_MORPH(BodyCoveringType.BOVINE_FUR, Race.COW_MORPH),
	
	DOG_MORPH(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),
	
	LYCAN(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),

	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),

	HORSE_MORPH(BodyCoveringType.HORSE_HAIR, Race.HORSE_MORPH),

	SLIME(BodyCoveringType.SLIME, Race.SLIME),

	SQUIRREL_MORPH(BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH),

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
			case COW_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated");
			case SQUIRREL_MORPH:
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
			default:
				return UtilText.returnStringAtRandom("");
		}
	}
	
	public String getTransformName() {
		switch(this){
			case ANGEL:
				return "angelic";
			case CAT_MORPH:
				return "feline";
			case DEMON_COMMON:
				return "demonic";
			case DOG_MORPH:
				return "canine";
			case COW_MORPH:
				return "bovine";
			case SQUIRREL_MORPH:
				return "furry";
			case HARPY:
				return "feathered";
			case HORSE_MORPH:
				return "equine";
			case HUMAN:
				return "human";
			case LYCAN:
				return "lupine";
			case SLIME:
				return "slimy";
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
				case COW_MORPH:
					return UtilText.returnStringAtRandom("feminine", "bovine");
				case HARPY:
					return UtilText.returnStringAtRandom("feminine", "feathered");
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("feminine", "equine");
				case HUMAN:
					return UtilText.returnStringAtRandom("soft", "feminine");
				case LYCAN:
					return UtilText.returnStringAtRandom("soft", "feminine", "wolf-like", "furry", "paw-like");
				case SLIME:
					return UtilText.returnStringAtRandom("slimy", "feminine");
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "squirrel-like", "claw-like", "furry", "rodent");
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
				case COW_MORPH:
					return UtilText.returnStringAtRandom("bovine");
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
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "squirrel-like", "claw-like", "furry", "rodent");
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
					return UtilText.returnStringAtRandom("slender", "delicate", "soft", "feminine");
				case CAT_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "padded", "feline");
				case DEMON_COMMON:
					return UtilText.returnStringAtRandom("slender", "delicate", "soft", "feminine");
				case DOG_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "padded", "canine");
				case COW_MORPH:
					return UtilText.returnStringAtRandom("feminine", "bovine");
				case HARPY:
					return UtilText.returnStringAtRandom("feminine", "feathered");
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("feminine", "equine");
				case HUMAN:
					return UtilText.returnStringAtRandom("soft", "feminine");
				case LYCAN:
					return UtilText.returnStringAtRandom("soft", "feminine", "padded", "wolf-like");
				case SLIME:
					return UtilText.returnStringAtRandom("slimy", "feminine");
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "feminine", "clawed", "rodent");
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
				case COW_MORPH:
					return UtilText.returnStringAtRandom("bovine");
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
				case SQUIRREL_MORPH:
					return UtilText.returnStringAtRandom("soft", "clawed", "rodent");
				default:
					return UtilText.returnStringAtRandom("");
			}
		}
	}
	
}
