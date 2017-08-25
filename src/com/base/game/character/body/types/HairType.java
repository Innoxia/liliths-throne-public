package com.base.game.character.body.types;

import com.base.game.character.GameCharacter;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum HairType implements BodyPartTypeInterface {
	HUMAN(BodyCoveringType.HAIR_HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.HAIR_ANGEL, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.HAIR_DEMON, Race.DEMON),

	DOG_MORPH(BodyCoveringType.HAIR_CANINE_FUR, Race.DOG_MORPH),

	LYCAN(BodyCoveringType.HAIR_LYCAN_FUR, Race.WOLF_MORPH),

	CAT_MORPH(BodyCoveringType.HAIR_FELINE_FUR, Race.CAT_MORPH),

	SQUIRREL_MORPH(BodyCoveringType.HAIR_SQUIRREL_FUR, Race.SQUIRREL_MORPH),

	HORSE_MORPH(BodyCoveringType.HAIR_HORSE_HAIR, Race.HORSE_MORPH),

	HARPY(BodyCoveringType.HAIR_HARPY, Race.HARPY),

	SLIME(BodyCoveringType.HAIR_SLIME, Race.SLIME);

	
	private BodyCoveringType coveringType;
	private Race race;

	private HairType(BodyCoveringType coveringType, Race race) {
		this.coveringType = coveringType;
		this.race = race;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "a head of";
	}

	@Override
	public boolean isDefaultPlural() {
		switch(coveringType) {
			case HAIR_HARPY:
				return true;
			default:
				return false;
		}
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		switch(coveringType) {
		case HAIR_HARPY:
			return "feather";
		case HAIR_SLIME:
			return "slime";
		default:
			return "hair";
	}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(coveringType) {
			case HAIR_HARPY:
				return "feathers";
			case HAIR_SLIME:
				return "slime";
			default:
				return "hair";
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("angelic");
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("cat-like");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("demonic");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("dog-like");
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("squirrel-like");
			case HARPY:
				return UtilText.returnStringAtRandom("bird-like");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("horse-like");
			case HUMAN:
				return UtilText.returnStringAtRandom("");
			case LYCAN:
				return UtilText.returnStringAtRandom("wolf-like");
			case SLIME:
				return UtilText.returnStringAtRandom("slimy", "gooey");
			default:
				return UtilText.returnStringAtRandom("");
		}
	}

	@Override
	public BodyCoveringType getBodyCoveringType() {
		return coveringType;
	}

	@Override
	public Race getRace() {
		return race;
	}
}
