package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public enum FaceType implements BodyPartTypeInterface {
	HUMAN(BodyCoveringType.HUMAN, MouthType.HUMAN, TongueType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.HUMAN, MouthType.ANGEL, TongueType.HUMAN, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, MouthType.DEMON_COMMON, TongueType.DEMON_COMMON, Race.DEMON),

	DOG_MORPH(BodyCoveringType.CANINE_FUR, MouthType.DOG_MORPH, TongueType.DOG_MORPH, Race.DOG_MORPH),
	
	LYCAN(BodyCoveringType.LYCAN_FUR, MouthType.WOLF_MORPH, TongueType.WOLF_MORPH, Race.WOLF_MORPH),

	CAT_MORPH(BodyCoveringType.FELINE_FUR, MouthType.CAT_MORPH, TongueType.CAT_MORPH, Race.CAT_MORPH),

	COW_MORPH(BodyCoveringType.BOVINE_FUR, MouthType.COW_MORPH, TongueType.COW_MORPH, Race.COW_MORPH),

	SQUIRREL_MORPH(BodyCoveringType.SQUIRREL_FUR, MouthType.SQUIRREL_MORPH, TongueType.SQUIRREL_MORPH, Race.SQUIRREL_MORPH),

	HORSE_MORPH(BodyCoveringType.HORSE_HAIR, MouthType.HORSE_MORPH, TongueType.HORSE_MORPH, Race.HORSE_MORPH),

	SLIME(BodyCoveringType.SLIME, MouthType.SLIME, TongueType.SLIME, Race.SLIME),

	HARPY(BodyCoveringType.FEATHERS, MouthType.HARPY, TongueType.TENGU, Race.HARPY);

	private BodyCoveringType skinType;
	private MouthType mouthType;
	private TongueType tongueType;
	private Race race;

	private FaceType(BodyCoveringType skinType, MouthType mouthType, TongueType tongueType, Race race) {
		this.skinType = skinType;
		this.mouthType = mouthType;
		this.tongueType = tongueType;
		this.race = race;
	}
	
	@Override
	public boolean isDefaultPlural() {
		return false;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "";
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		switch(this){
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("muzzle", "face");
			case COW_MORPH:
				return UtilText.returnStringAtRandom("muzzle", "face");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("muzzle", "face");
			case LYCAN:
				return UtilText.returnStringAtRandom("muzzle", "face");
			default:
				return UtilText.returnStringAtRandom("face");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("muzzles", "faces");
			case COW_MORPH:
				return UtilText.returnStringAtRandom("muzzles", "faces");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("muzzles", "faces");
			case LYCAN:
				return UtilText.returnStringAtRandom("muzzles", "faces");
			default:
				return UtilText.returnStringAtRandom("faces");
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("perfect", "flawless", "angelic");
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("anthropomorphic cat-like", "cat-like", "feline");
			case COW_MORPH:
				return UtilText.returnStringAtRandom("anthropomorphic cow-like", "cow-like", "bovine");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("perfect", "flawless", "demonic");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("anthropomorphic dog-like", "dog-like", "canine");
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("anthropomorphic squirrel-like", "squirrel-like", "rodent");
			case HARPY:
				return UtilText.returnStringAtRandom("anthropomorphic bird-like", "bird-like");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("anthropomorphic horse-like", "horse-like", "equine");
			case HUMAN:
				return UtilText.returnStringAtRandom("");
			case LYCAN:
				return UtilText.returnStringAtRandom("anthropomorphic wolf-like", "wolf-like");
			case SLIME:
				return UtilText.returnStringAtRandom("slimy");
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
				return "avian";
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
	
	public MouthType getMouthType() {
		return mouthType;
	}

	public TongueType getTongueType() {
		return tongueType;
	}
	
	

}
