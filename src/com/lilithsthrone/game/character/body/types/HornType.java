package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * 
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public enum HornType implements BodyPartTypeInterface {
	NONE("", null, null),

	BOVINE_CURVED("curved", BodyCoveringType.HORN, Race.COW_MORPH),
	BOVINE_STRAIGHT("straight", BodyCoveringType.HORN, Race.COW_MORPH),
	
	REINDEER_RACK("multi-branched", BodyCoveringType.ANTLER_REINDEER, Race.REINDEER_MORPH),
	
	CURLED("curled", BodyCoveringType.HORN, Race.DEMON),
	SPIRAL("spiral", BodyCoveringType.HORN, Race.DEMON),
	CURVED("curved", BodyCoveringType.HORN, Race.DEMON),
	SWEPT_BACK("swept-back", BodyCoveringType.HORN, Race.DEMON),
	STRAIGHT("straight", BodyCoveringType.HORN, Race.DEMON);
	
	//TODO If any more horn types are added, check to see that the potion TFs still work. (5 might be the maximum.)
	
	private BodyCoveringType skinType;
	private Race race;
	
	private HornType(String descriptor, BodyCoveringType skinType, Race race) {
		this.skinType = skinType;
		this.race = race;
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static HornType getTypeFromString(String value) {
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
			case REINDEER_RACK:
				return "antler";
			default:
				return UtilText.returnStringAtRandom("horn");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			case REINDEER_RACK:
				return "antlers";
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
				return UtilText.returnStringAtRandom("swept-back");
			case REINDEER_RACK:
				return UtilText.returnStringAtRandom("branching");
			case STRAIGHT: case BOVINE_STRAIGHT:
				return UtilText.returnStringAtRandom("straight");
			case CURLED:
				return UtilText.returnStringAtRandom("curled");
			case CURVED: case BOVINE_CURVED:
				return UtilText.returnStringAtRandom("curved");
			case SPIRAL:
				return UtilText.returnStringAtRandom("spiral");
		}
		return "";
	}

	@Override
	public String getTransformName() {
		switch(this){
			case NONE:
				return "none";
			case SWEPT_BACK:
				return UtilText.returnStringAtRandom("swept-back");
			case REINDEER_RACK:
				return UtilText.returnStringAtRandom("branching");
			case STRAIGHT: case BOVINE_STRAIGHT:
				return UtilText.returnStringAtRandom("straight");
			case CURLED:
				return UtilText.returnStringAtRandom("curled");
			case CURVED: case BOVINE_CURVED:
				return UtilText.returnStringAtRandom("curved");
			case SPIRAL:
				return UtilText.returnStringAtRandom("spiral");
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
	
	public static List<HornType> getHornTypesSuitableForTransformation(List<HornType> options) {
		if (!options.contains(HornType.NONE)) {
			return options;
		}
		
		List<HornType> duplicatedOptions = new ArrayList<>(options);
		duplicatedOptions.remove(HornType.NONE);
		return duplicatedOptions;
	}
}
