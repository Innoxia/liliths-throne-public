package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum EyeType implements BodyPartTypeInterface {
	HUMAN(BodyCoveringType.EYE_HUMAN, Race.HUMAN, 1, EyeShape.ROUND, EyeShape.ROUND),

	ANGEL(BodyCoveringType.EYE_ANGEL, Race.ANGEL, 1, EyeShape.ROUND, EyeShape.ROUND),

	COW_MORPH(BodyCoveringType.EYE_COW_MORPH, Race.COW_MORPH, 1, EyeShape.ROUND, EyeShape.ROUND),

	DEMON_COMMON(BodyCoveringType.EYE_DEMON_COMMON, Race.DEMON, 1, EyeShape.ROUND, EyeShape.VERTICAL),

	DOG_MORPH(BodyCoveringType.EYE_DOG_MORPH, Race.DOG_MORPH, 1, EyeShape.ROUND, EyeShape.ROUND),

	LYCAN(BodyCoveringType.EYE_LYCAN, Race.WOLF_MORPH, 1, EyeShape.ROUND, EyeShape.ROUND),

	CAT_MORPH(BodyCoveringType.EYE_FELINE, Race.CAT_MORPH, 1, EyeShape.ROUND, EyeShape.VERTICAL),

	SQUIRREL_MORPH(BodyCoveringType.EYE_SQUIRREL, Race.SQUIRREL_MORPH, 1, EyeShape.ROUND, EyeShape.ROUND),

	GATOR_MORPH(BodyCoveringType.EYE_GATOR_MORPH, Race.GATOR_MORPH, 1, EyeShape.ROUND, EyeShape.ROUND),

	HORSE_MORPH(BodyCoveringType.EYE_HORSE_MORPH, Race.HORSE_MORPH, 1, EyeShape.ROUND, EyeShape.HORIZONTAL),

	HARPY(BodyCoveringType.EYE_HARPY, Race.HARPY, 1, EyeShape.ROUND, EyeShape.ROUND),

	SLIME(BodyCoveringType.EYE_SLIME, Race.SLIME, 1, EyeShape.ROUND, EyeShape.ROUND);

	
	private BodyCoveringType coveringType;
	private Race race;
	private int eyePairs;
	private EyeShape irisShape, pupilShape;

	private EyeType(BodyCoveringType coveringType, Race race, int eyePairs, EyeShape irisShape, EyeShape pupilShape) {
		this.coveringType = coveringType;
		this.race = race;
		this.eyePairs = eyePairs;
		this.irisShape = irisShape;
		this.pupilShape = pupilShape;
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
		return "eye";
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return "eyes";
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("angelic");
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("cat-like");
			case COW_MORPH:
				return UtilText.returnStringAtRandom("cow-like");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("demonic");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("dog-like");
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("squirrel-like");
			case GATOR_MORPH:
				return UtilText.returnStringAtRandom("reptile-like");
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

	public int getEyePairs() {
		return eyePairs;
	}

	public EyeShape getIrisShape() {
		return irisShape;
	}

	public EyeShape getPupilShape() {
		return pupilShape;
	}
}
