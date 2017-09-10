package com.base.game.character.body.types;

import com.base.game.character.GameCharacter;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.84
 * @author Innoxia
 */
public enum TailType implements BodyPartTypeInterface {
	NONE(null, null, false, false),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON, true, true),

	DOG_MORPH(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH, false, false),
	
	LYCAN(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH, false, false),

	COW_MORPH(BodyCoveringType.BOVINE_FUR, Race.COW_MORPH, false, false),
	
	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH, true, false),

	SQUIRREL_MORPH(BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH, false, false),
	
	HORSE_MORPH(BodyCoveringType.HAIR_HORSE_HAIR, Race.HORSE_MORPH, false, false),

	HARPY(BodyCoveringType.FEATHERS, Race.HARPY, false, false);

	private BodyCoveringType skinType;
	private Race race;
	private boolean prehensile, suitableForPenetration;

	private TailType(BodyCoveringType skinType, Race race, boolean prehensile, boolean suitableForPenetration) {
		this.skinType = skinType;
		this.race = race;
		this.prehensile = prehensile;
		this.suitableForPenetration = suitableForPenetration;
	}
	
	@Override
	public boolean isDefaultPlural() {
		return false;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getTailCount()==1) {
			switch(this){
				case HARPY:
					return "a plume of";
				default:
					return "";
			}
		} else {
			switch(this){
				case HARPY:
					return Util.intToString(gc.getTailCount())+" plumes of";
				default:
					return Util.intToString(gc.getTailCount());
			}
		}
		
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
			case COW_MORPH:
				return UtilText.returnStringAtRandom("cow-like", "tufted");
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
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("squirrel-like", "fluffy");
			case NONE:
				return UtilText.returnStringAtRandom("");
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
	public BodyCoveringType getBodyCoveringType() {
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}
	
	public boolean isPrehensile() {
		return prehensile;
	}

	public boolean isSuitableForPenetration() {
		return suitableForPenetration;
	}
	
}
