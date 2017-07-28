package com.base.game.character.body.types;

import com.base.game.character.GameCharacter;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public enum WingType implements BodyPartTypeInterface {
	NONE(null, null),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON),

	ANGEL(BodyCoveringType.DEMON_COMMON, Race.ANGEL);

	private BodyCoveringType skinType;
	private Race race;

	private WingType(BodyCoveringType skinType, Race race) {
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
		return "wing";
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return "wings";
	}

	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("angelic", "huge", "feathered");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("demonic", "little", "bat-like");
			case NONE:
				return UtilText.returnStringAtRandom("");
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
