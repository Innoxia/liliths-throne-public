package com.base.game.character.body.types;

import com.base.game.character.GameCharacter;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.82
 * @author Innoxia
 */
public enum TailType implements BodyPartTypeInterface {
	NONE(null, null),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON){
		public boolean isPrehensile() {
			return true;
		}
	},

	DOG_MORPH(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),
	
	LYCAN(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),

	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),

	HORSE_MORPH(BodyCoveringType.HAIR_HORSE_HAIR, Race.HORSE_MORPH),

	HARPY(BodyCoveringType.FEATHERS, Race.HARPY),
	
	FOX_MORPH(BodyCoveringType.VULPINE_FUR, Race.FOX_MORPH);

	private BodyCoveringType skinType;
	private Race race;

	private TailType(BodyCoveringType skinType, Race race) {
		this.skinType = skinType;
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
			case HARPY:
				return UtilText.returnStringAtRandom("tail feathers");
			default:
				return UtilText.returnStringAtRandom("tail");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			case HARPY:
				return UtilText.returnStringAtRandom("tail feathers");
			default:
				return UtilText.returnStringAtRandom("tails");
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("cat-like");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("spaded", "demonic");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("dog-like");
			case HARPY:
				return UtilText.returnStringAtRandom("colourful", "bird-like");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("horse-like");
			case LYCAN:
				return UtilText.returnStringAtRandom("wolf-like");
			case NONE:
				return UtilText.returnStringAtRandom("");
			case FOX_MORPH:
				return UtilText.returnStringAtRandom("fox-like", "vulpine", "bushy, fox-like");
			default:
				return UtilText.returnStringAtRandom("");
		}
	}
	
	public String getTailTipName(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("tip");
		}
	}
	
	public String getTailTipDescriptor(GameCharacter gc) {
		switch(this){
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("spaded");
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
	
	public boolean isPrehensile() {
		return false;
	}
	
}
