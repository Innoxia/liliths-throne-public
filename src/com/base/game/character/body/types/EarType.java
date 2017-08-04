package com.base.game.character.body.types;

import com.base.game.character.GameCharacter;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public enum EarType implements BodyPartTypeInterface {
	HUMAN(BodyCoveringType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.ANGEL, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON),

	DOG_MORPH(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),

	LYCAN(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),

	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),

	HORSE_MORPH(BodyCoveringType.HORSE_HAIR, Race.HORSE_MORPH),

	HARPY(BodyCoveringType.FEATHERS, Race.HARPY),

	SLIME(BodyCoveringType.SLIME, Race.SLIME),
	
	FOX_MORPH(BodyCoveringType.VULPINE_FUR, Race.FOX_MORPH);

	private BodyCoveringType skinType;
	private Race race;

	private EarType(BodyCoveringType skinType, Race race) {
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
		return "ear";
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return "ears";
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("pointed", "delicate");
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "cat-like");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("pointed", "demonic");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "dog-like");
			case HARPY:
				return UtilText.returnStringAtRandom("feathered", "feather-covered");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("equine", "horse-like");
			case HUMAN:
				return UtilText.returnStringAtRandom("");
			case LYCAN:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "wolf-like");
			case SLIME:
				return UtilText.returnStringAtRandom("slimy", "gooey");
			case FOX_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "cat-like");
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
}
