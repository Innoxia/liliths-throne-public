package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.83
 * @version 0.2.11
 * @author Innoxia
 */
public enum EyeType implements BodyPartTypeInterface {
	HUMAN(BodyCoveringType.EYE_HUMAN, Race.HUMAN, 1, EyeShape.ROUND, EyeShape.ROUND),

	ANGEL(BodyCoveringType.EYE_ANGEL, Race.ANGEL, 1, EyeShape.ROUND, EyeShape.ROUND),

	COW_MORPH(BodyCoveringType.EYE_COW_MORPH, Race.COW_MORPH, 1, EyeShape.ROUND, EyeShape.ROUND),

	DEMON_COMMON(BodyCoveringType.EYE_DEMON_COMMON, Race.DEMON, 1, EyeShape.ROUND, EyeShape.VERTICAL),

	DOG_MORPH(BodyCoveringType.EYE_DOG_MORPH, Race.DOG_MORPH, 1, EyeShape.ROUND, EyeShape.ROUND),

	LYCAN(BodyCoveringType.EYE_LYCAN, Race.WOLF_MORPH, 1, EyeShape.ROUND, EyeShape.ROUND),
	
	FOX_MORPH(BodyCoveringType.EYE_FOX_MORPH, Race.FOX_MORPH, 1, EyeShape.ROUND, EyeShape.VERTICAL),

	CAT_MORPH(BodyCoveringType.EYE_FELINE, Race.CAT_MORPH, 1, EyeShape.ROUND, EyeShape.VERTICAL),

	SQUIRREL_MORPH(BodyCoveringType.EYE_SQUIRREL, Race.SQUIRREL_MORPH, 1, EyeShape.ROUND, EyeShape.ROUND),

	RAT_MORPH(BodyCoveringType.EYE_RAT, Race.RAT_MORPH, 1, EyeShape.ROUND, EyeShape.ROUND),

	RABBIT_MORPH(BodyCoveringType.EYE_RABBIT, Race.RABBIT_MORPH, 1, EyeShape.ROUND, EyeShape.ROUND),
	
	BAT_MORPH(BodyCoveringType.EYE_BAT, Race.BAT_MORPH, 1, EyeShape.ROUND, EyeShape.ROUND),

	ALLIGATOR_MORPH(BodyCoveringType.EYE_ALLIGATOR_MORPH, Race.ALLIGATOR_MORPH, 1, EyeShape.ROUND, EyeShape.VERTICAL),

	HORSE_MORPH(BodyCoveringType.EYE_HORSE_MORPH, Race.HORSE_MORPH, 1, EyeShape.ROUND, EyeShape.HORIZONTAL),

	REINDEER_MORPH(BodyCoveringType.EYE_REINDEER_MORPH, Race.REINDEER_MORPH, 1, EyeShape.ROUND, EyeShape.HORIZONTAL),

	HARPY(BodyCoveringType.EYE_HARPY, Race.HARPY, 1, EyeShape.ROUND, EyeShape.ROUND);

	
	private BodyCoveringType coveringType;
	private Race race;
	private int eyePairs;
	private EyeShape irisShape, pupilShape;

	private EyeType(BodyCoveringType coveringType, Race race, int eyePairs, EyeShape irisShape, EyeShape pupilShape) {
		this.coveringType = coveringType;
		this.race = race;
		this.eyePairs = eyePairs;
		this.irisShape = irisShape;
		this.pupilShape = pupilShape;
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static EyeType getTypeFromString(String value) {
		if(value.equals("IMP")) {
			value = "DEMON_COMMON";
		}
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
		return "eye";
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return "eyes";
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("angelic");
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("cat-like");
			case COW_MORPH:
				return UtilText.returnStringAtRandom("cow-like");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("demonic");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("dog-like");
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("squirrel-like");
			case ALLIGATOR_MORPH:
				return UtilText.returnStringAtRandom("reptile-like");
			case HARPY:
				return UtilText.returnStringAtRandom("bird-like");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("horse-like");
			case REINDEER_MORPH:
				return UtilText.returnStringAtRandom("reindeer-like");
			case HUMAN:
				return UtilText.returnStringAtRandom("");
			case LYCAN:
				return UtilText.returnStringAtRandom("wolf-like");
			case FOX_MORPH:
				return UtilText.returnStringAtRandom("fox-like");
			case RAT_MORPH:
				return UtilText.returnStringAtRandom("rat-like");
			case RABBIT_MORPH:
				return UtilText.returnStringAtRandom("rabbit-like");
			case BAT_MORPH:
				return UtilText.returnStringAtRandom("bat-like");
		}
		return "";
	}

	@Override
	public BodyCoveringType getBodyCoveringType(Body body) {
		return coveringType;
	}

	@Override
	public Race getRace() {
		return race;
	}

	public int getEyePairs() {
		return eyePairs;
	}

	public EyeShape getIrisShape() {
		return irisShape;
	}

	public EyeShape getPupilShape() {
		return pupilShape;
	}
	
	private static Map<Race, List<EyeType>> typesMap = new HashMap<>();
	public static List<EyeType> getEyeTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<EyeType> types = new ArrayList<>();
		for(EyeType type : EyeType.values()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}