package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.89
 * @author Innoxia
 */
public enum HornType implements BodyPartTypeInterface {
	NONE("", null, null),

	CURLED("curled", BodyCoveringType.HORN, Race.DEMON),
	SPIRAL("spiral", BodyCoveringType.HORN, Race.DEMON),
	CURVED("curved", BodyCoveringType.HORN, Race.COW_MORPH),
	SWEPT_BACK("swept-back", BodyCoveringType.HORN, Race.DEMON),
	STRAIGHT("straight", BodyCoveringType.HORN, Race.COW_MORPH);
	
	//TODO if add any more, add more TFModifiers for enchanting
	
	private BodyCoveringType skinType;
	private Race race;
	
	private HornType(String descriptor, BodyCoveringType skinType, Race race) {
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
		switch(this){
			default:
				return UtilText.returnStringAtRandom("horn");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("horns");
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case NONE:
				return "";
			case SWEPT_BACK:
				return UtilText.returnStringAtRandom("short", "small");
			case STRAIGHT:
				return UtilText.returnStringAtRandom("straight", "sleek");
			case CURLED:
				return UtilText.returnStringAtRandom("curled");
			case CURVED:
				return UtilText.returnStringAtRandom("curved");
			case SPIRAL:
				return UtilText.returnStringAtRandom("spiral");
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
