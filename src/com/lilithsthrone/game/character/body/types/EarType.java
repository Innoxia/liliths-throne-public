package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

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

	COW_MORPH(BodyCoveringType.BOVINE_FUR, Race.COW_MORPH),

	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),

	SQUIRREL_MORPH(BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH),

	ALLIGATOR_MORPH(BodyCoveringType.ALLIGATOR_SCALES, Race.ALLIGATOR_MORPH),

	HORSE_MORPH(BodyCoveringType.HORSE_HAIR, Race.HORSE_MORPH),

	REINDEER_MORPH(BodyCoveringType.REINDEER_FUR, Race.REINDEER_MORPH),

	HARPY(BodyCoveringType.FEATHERS, Race.HARPY);

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
			case COW_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "cow-like");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("pointed", "demonic");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "dog-like");
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "squirrel-like");
			case ALLIGATOR_MORPH:
				return UtilText.returnStringAtRandom("scaled", "scale-covered");
			case HARPY:
				return UtilText.returnStringAtRandom("feathered", "feather-covered");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("equine", "horse-like");
			case REINDEER_MORPH:
				return UtilText.returnStringAtRandom("rangiferine", "reindeer-like");
			case HUMAN:
				return UtilText.returnStringAtRandom("");
			case LYCAN:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "wolf-like");
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
			case ALLIGATOR_MORPH:
				return "alligator";
			case HARPY:
				return "avian";
			case HORSE_MORPH:
				return "equine";
			case REINDEER_MORPH:
				return "rangiferine";
			case HUMAN:
				return "human";
			case LYCAN:
				return "lupine";
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
}
