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
 * @since 0.1.83
 * @version 0.2.11
 * @author Innoxia
 */
public enum AntennaType implements BodyPartTypeInterface {
	NONE("", null, null);
	
	private BodyCoveringType skinType;
	private Race race;
	
	private AntennaType(String descriptor, BodyCoveringType skinType, Race race) {
		this.skinType = skinType;
		this.race = race;
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static AntennaType getTypeFromString(String value) {
		return valueOf(value);
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
				return UtilText.returnStringAtRandom("antenna");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("antennae");
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case NONE:
				return UtilText.returnStringAtRandom("");
			default:
				return UtilText.returnStringAtRandom("");
		}
	}

	@Override
	public BodyCoveringType getBodyCoveringType(Body body) {
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}
	
	@Override
	public String getTransformName() {
		switch(this){
			case NONE:
				return "none";
		}
		return "";
	}
	
	private static Map<Race, List<AntennaType>> typesMap = new HashMap<>();
	public static List<AntennaType> getAntennaTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AntennaType> types = new ArrayList<>();
		for(AntennaType type : AntennaType.values()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}
