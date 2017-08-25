package com.base.game.character.body.types;

import com.base.game.character.GameCharacter;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum SkinType implements BodyPartTypeInterface {
	HUMAN(BodyCoveringType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.ANGEL, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON),

	DOG_MORPH(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),

	LYCAN(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),

	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),

	SQUIRREL_MORPH(BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH),

	HORSE_MORPH(BodyCoveringType.HORSE_HAIR, Race.HORSE_MORPH),

	HARPY(BodyCoveringType.FEATHERS, Race.HARPY),

	SLIME(BodyCoveringType.SLIME, Race.SLIME);

	
	private BodyCoveringType coveringType;
	private Race race;

	private SkinType(BodyCoveringType coveringType, Race race) {
		this.coveringType = coveringType;
		this.race = race;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "a layer of";
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
			return coveringType.getName(gc);
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
				return coveringType.getNamePlural(gc);
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
