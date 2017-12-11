package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum WingType implements BodyPartTypeInterface {
	NONE(null, null),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON),

	ANGEL(BodyCoveringType.ANGEL_FEATHER, Race.ANGEL) {
		@Override
		public boolean allowsFlight() {
			return true;
		}
	};

	private BodyCoveringType skinType;
	private Race race;

	private WingType(BodyCoveringType skinType, Race race) {
		this.skinType = skinType;
		this.race = race;
	}

	public boolean allowsFlight() {
		return false;
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
	
	public String getTransformName() {
		switch(this){
			case ANGEL:
				return "angelic";
			case DEMON_COMMON:
				return "bat-like";
			case NONE:
				return "none";
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
