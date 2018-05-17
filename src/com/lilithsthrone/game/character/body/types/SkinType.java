package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.83
 * @version 0.2.2
 * @author Innoxia
 */
public enum SkinType implements BodyPartTypeInterface {
	HUMAN(BodyCoveringType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.ANGEL, Race.ANGEL),

	COW_MORPH(BodyCoveringType.BOVINE_FUR, Race.COW_MORPH),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON),

	IMP(BodyCoveringType.IMP, Race.IMP),

	DOG_MORPH(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),

	LYCAN(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),

	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),

	SQUIRREL_MORPH(BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH),

	RAT_MORPH(BodyCoveringType.RAT_FUR, Race.RAT_MORPH),

	RABBIT_MORPH(BodyCoveringType.RABBIT_FUR, Race.RABBIT_MORPH),

	BAT_MORPH(BodyCoveringType.BAT_FUR, Race.BAT_MORPH),

	ALLIGATOR_MORPH(BodyCoveringType.ALLIGATOR_SCALES, Race.ALLIGATOR_MORPH),

	HORSE_MORPH(BodyCoveringType.HORSE_HAIR, Race.HORSE_MORPH),

	REINDEER_MORPH(BodyCoveringType.REINDEER_FUR, Race.REINDEER_MORPH),

	HARPY(BodyCoveringType.FEATHERS, Race.HARPY);

	
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
			case ALLIGATOR_SCALES:
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
			case ALLIGATOR_SCALES:
				return "scale";
			case SLIME:
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
			case ALLIGATOR_SCALES:
				return "scales";
			case SLIME:
				return "slime";
			default:
				return coveringType.getNamePlural(gc);
		}
	}
	
	public String getTransformName() {
		switch(this){
			case CAT_MORPH:
				return "feline";
			case COW_MORPH:
				return "bovine";
			case DEMON_COMMON:
				return "demonic";
			case IMP:
				return "impish";
			case DOG_MORPH:
				return "canine";
			case HARPY:
				return "plume";
			case HORSE_MORPH:
				return "equine";
			case REINDEER_MORPH:
				return "rangiferine";
			case LYCAN:
				return "lupine";
			case SQUIRREL_MORPH:
				return "fluffy";
			case ALLIGATOR_MORPH:
				return "alligator";
			case RAT_MORPH:
				return "rat";
			case RABBIT_MORPH:
				return "rabbit";
			case ANGEL:
				return "angel";
			case BAT_MORPH:
				return "bat";
			case HUMAN:
				return "human";
		}
		return "";
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
			case IMP:
				return UtilText.returnStringAtRandom("impish");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("dog-like");
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("squirrel-like");
			case ALLIGATOR_MORPH:
				return UtilText.returnStringAtRandom("alligator-like");
			case HARPY:
				return UtilText.returnStringAtRandom("bird-like");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("horse-like");
			case REINDEER_MORPH:
				return UtilText.returnStringAtRandom("reindeer-like");
			case HUMAN:
				return UtilText.returnStringAtRandom("");
			case LYCAN:
				return UtilText.returnStringAtRandom("wolf-like");
			case BAT_MORPH:
				return UtilText.returnStringAtRandom("bat-like");
			case RAT_MORPH:
				return UtilText.returnStringAtRandom("rat-like");
			case RABBIT_MORPH:
				return UtilText.returnStringAtRandom("rabbit-like");
		}
		return "";
	}

	@Override
	public BodyCoveringType getBodyCoveringType(Body body) {
		return coveringType;
	}

	@Override
	public Race getRace() {
		return race;
	}
}
