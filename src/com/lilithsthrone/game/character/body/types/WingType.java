package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public enum WingType implements BodyPartTypeInterface {
	NONE(null, null, false),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON, true),

	DEMON_FEATHERED(BodyCoveringType.DEMON_FEATHER, Race.DEMON, true),

	ANGEL(BodyCoveringType.ANGEL_FEATHER, Race.ANGEL, true);

	private BodyCoveringType skinType;
	private Race race;
	private boolean allowsFlight;

	private WingType(BodyCoveringType skinType, Race race, boolean allowsFlight) {
		this.skinType = skinType;
		this.race = race;
		this.allowsFlight = allowsFlight;
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static WingType getTypeFromString(String value) {
		if(value.equals("IMP")) {
			value = "DEMON_COMMON";
		}
		return valueOf(value);
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
				return UtilText.returnStringAtRandom("angelic", "feathered");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("demonic", "bat-like");
			case DEMON_FEATHERED:
				return UtilText.returnStringAtRandom("demonic", "feathered");
			case NONE:
				return "";
		}
		return "";
	}

	@Override
	public String getTransformName() {
		switch(this){
			case ANGEL:
				return "angelic feathered";
			case DEMON_COMMON:
				return "demonic bat-like";
			case DEMON_FEATHERED:
				return "demonic feathered";
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
	
	private static Map<Race, List<WingType>> typesMap = new HashMap<>();
	public static List<WingType> getWingTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<WingType> types = new ArrayList<>();
		for(WingType type : WingType.values()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}
