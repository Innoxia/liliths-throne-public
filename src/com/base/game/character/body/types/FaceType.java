package com.base.game.character.body.types;

import com.base.game.character.GameCharacter;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public enum FaceType implements BodyPartTypeInterface {
	HUMAN(BodyCoveringType.HUMAN, TongueType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.HUMAN, TongueType.HUMAN, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, TongueType.DEMON_COMMON, Race.DEMON),

	DOG_MORPH(BodyCoveringType.CANINE_FUR, TongueType.DOG_MORPH, Race.DOG_MORPH),
	
	LYCAN(BodyCoveringType.LYCAN_FUR, TongueType.WOLF_MORPH, Race.WOLF_MORPH),

	CAT_MORPH(BodyCoveringType.FELINE_FUR, TongueType.CAT_MORPH, Race.CAT_MORPH),

	SQUIRREL_MORPH(BodyCoveringType.SQUIRREL_FUR, TongueType.SQUIRREL_MORPH, Race.SQUIRREL_MORPH),

	HORSE_MORPH(BodyCoveringType.HORSE_HAIR, TongueType.HORSE_MORPH, Race.HORSE_MORPH),

	SLIME(BodyCoveringType.SLIME, TongueType.SLIME, Race.SLIME),

	HARPY(BodyCoveringType.FEATHERS, TongueType.TENGU, Race.HARPY);

	private BodyCoveringType skinType;
	private TongueType tongueType;
	private Race race;

	private FaceType(BodyCoveringType skinType, TongueType tongueType, Race race) {
		this.skinType = skinType;

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

	@Override
	public BodyCoveringType getSkinType() {
		return skinType;
	}
	
	@Override
	public Race getRace() {
		return race;
	}
	
	public TongueType getTongueType() {
		return tongueType;
	}
	
	public String getLipsNameSingular(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("lip");
		}
	}
	
	public String getLipsNamePlural(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("lips");
		}
	}

	public String getLipsDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			switch(this){
				default:
					return UtilText.returnStringAtRandom("soft", "plump", "full");
			}
		} else {
			switch(this){
				default:
					return UtilText.returnStringAtRandom("");
			}
		}
	}

}
