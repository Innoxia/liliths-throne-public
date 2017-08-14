package com.base.game.character.body.types;

import com.base.game.character.GameCharacter;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public enum TongueType implements BodyPartTypeInterface {

	HUMAN(BodyCoveringType.TONGUE, Race.HUMAN),

	DEMON_COMMON(BodyCoveringType.TONGUE, Race.DEMON),

	DOG_MORPH(BodyCoveringType.TONGUE, Race.DOG_MORPH),

	WOLF_MORPH(BodyCoveringType.TONGUE, Race.WOLF_MORPH),

	CAT_MORPH(BodyCoveringType.TONGUE, Race.CAT_MORPH),

	HORSE_MORPH(BodyCoveringType.TONGUE, Race.HORSE_MORPH),

	BOVINE(BodyCoveringType.TONGUE, Race.COW_MORPH),

	SLIME(BodyCoveringType.SLIME, Race.SLIME),

	TENGU(BodyCoveringType.TONGUE, Race.HARPY),

	SQUIRREL_MORPH(BodyCoveringType.TONGUE, Race.SQUIRREL_MORPH);
	
	private BodyCoveringType skinType;
	private Race race;

	private TongueType(BodyCoveringType skinType, Race race) {
		this.skinType = skinType;
		this.race = race;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "";
	}

	@Override
	public boolean isDefaultPlural() {
		return false;
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return "tongue";
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return "tongues";
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("thin", "cat-like");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("forked");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("wide", "dog-like");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("strong", "horse-like", "long");
			case BOVINE:
				return UtilText.returnStringAtRandom("strong", "cow-like", "long");
			case HUMAN:
				return UtilText.returnStringAtRandom("");
			case SLIME:
				return UtilText.returnStringAtRandom("slimy");
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("tiny");
			case TENGU:
				return UtilText.returnStringAtRandom("long", "bird-like");
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
