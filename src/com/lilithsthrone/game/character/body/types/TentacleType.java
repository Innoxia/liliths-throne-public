package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public enum TentacleType implements BodyPartTypeInterface {
	NONE(null, Race.NONE, false),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON, true);

	private BodyCoveringType skinType;
	private Race race;
	private boolean suitableForPenetration;

	private TentacleType(BodyCoveringType skinType, Race race, boolean suitableForPenetration) {
		this.skinType = skinType;
		this.race = race;
		this.suitableForPenetration = suitableForPenetration;
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static TentacleType getTypeFromString(String value) {
		return valueOf(value);
	}
	
	@Override
	public boolean isDefaultPlural() {
		return false;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getTentacleCount()==1) {
			switch(this){
				default:
					return "";
			}
		} else {
			switch(this){
				default:
					return Util.intToString(gc.getTentacleCount());
			}
		}
		
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("tentacle");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("tentacles");
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("demonic");
			case NONE:
				return UtilText.returnStringAtRandom("");
		}
		
		return "";
	}

	@Override
	public String getTransformName() {
		switch(this){
			case DEMON_COMMON:
				return "demonic";
			case NONE:
				return "none";
		}
		return "";
	}
	
	public String getTentacleTipName(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("end");
		}
	}
	
	public String getTentacleTipDescriptor(GameCharacter gc) {
		switch(this){
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("rounded");
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

	/**
	 * Takes into account whether player has 'Allow furry tail penetrations' turned on or off.
	 * @return
	 */
	public boolean isSuitableForPenetration() {
		return suitableForPenetration;
	}
	
	private static Map<Race, List<TentacleType>> typesMap = new HashMap<>();
	public static List<TentacleType> getTentacleTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<TentacleType> types = new ArrayList<>();
		for(TentacleType type : TentacleType.values()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}
