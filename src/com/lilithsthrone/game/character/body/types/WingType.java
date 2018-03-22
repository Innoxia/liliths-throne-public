package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.2.1
 * @author Innoxia
 */
public enum WingType implements BodyPartTypeInterface {
	NONE(null, null, false),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON, true),

	IMP(BodyCoveringType.IMP, Race.IMP, true),

	ANGEL(BodyCoveringType.ANGEL_FEATHER, Race.ANGEL, true);

	private BodyCoveringType skinType;
	private Race race;
	private boolean allowsFlight;

	private WingType(BodyCoveringType skinType, Race race, boolean allowsFlight) {
		this.skinType = skinType;
		this.race = race;
		this.allowsFlight = allowsFlight;
	}

	public boolean allowsFlight() {
		return allowsFlight;
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
				return UtilText.returnStringAtRandom("demonic", "bat-like");
			case IMP:
				return UtilText.returnStringAtRandom("impish", "bat-like");
			case NONE:
				return "";
		}
		return "";
	}
	
	public String getTransformName() {
		switch(this){
			case ANGEL:
				return "angelic";
			case DEMON_COMMON:
				return "bat-like";
			case IMP:
				return "bat-like";
			case NONE:
				return "none";
		}
		return "";
	}

	@Override
	public BodyCoveringType getBodyCoveringType(Body body) {
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}
}
