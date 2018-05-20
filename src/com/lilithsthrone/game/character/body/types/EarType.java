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
 * @version 0.2.2
 * @author Innoxia
 */
public enum EarType implements BodyPartTypeInterface {
	HUMAN(BodyCoveringType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.ANGEL, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON),

	IMP(BodyCoveringType.IMP, Race.IMP),
	
	DOG_MORPH(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),
	DOG_MORPH_POINTED(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),
	DOG_MORPH_FOLDED(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),

	LYCAN(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),

	COW_MORPH(BodyCoveringType.BOVINE_FUR, Race.COW_MORPH),

	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),

	SQUIRREL_MORPH(BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH),

	RAT_MORPH(BodyCoveringType.RAT_FUR, Race.RAT_MORPH),

	RABBIT_MORPH(BodyCoveringType.RABBIT_FUR, Race.RABBIT_MORPH),
	RABBIT_MORPH_FLOPPY(BodyCoveringType.RABBIT_FUR, Race.RABBIT_MORPH),
	
	BAT_MORPH(BodyCoveringType.BAT_FUR, Race.BAT_MORPH),

	ALLIGATOR_MORPH(BodyCoveringType.ALLIGATOR_SCALES, Race.ALLIGATOR_MORPH),

	HORSE_MORPH(BodyCoveringType.HORSE_HAIR, Race.HORSE_MORPH),

	REINDEER_MORPH(BodyCoveringType.REINDEER_FUR, Race.REINDEER_MORPH),

	HARPY(BodyCoveringType.FEATHERS, Race.HARPY),
	BEE(BodyCoveringType.BEE, Race.BEE);

	private BodyCoveringType skinType;
	private Race race;

	private EarType(BodyCoveringType skinType, Race race) {
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
		return "ear";
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return "ears";
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("pointed", "delicate");
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "cat-like");
			case COW_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "cow-like");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("pointed", "demonic");
			case IMP:
				return UtilText.returnStringAtRandom("pointed", "impish");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("floppy", "furry", "fur-coated", "dog-like");
			case DOG_MORPH_POINTED:
				return UtilText.returnStringAtRandom("pointed", "furry", "fur-coated", "dog-like");
			case DOG_MORPH_FOLDED:
				return UtilText.returnStringAtRandom("folded", "furry", "fur-coated", "dog-like");
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "squirrel-like");
			case ALLIGATOR_MORPH:
				return UtilText.returnStringAtRandom("scaled", "scale-covered");
			case HARPY:
				return UtilText.returnStringAtRandom("feathered", "feather-covered");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("equine", "horse-like");
			case REINDEER_MORPH:
				return UtilText.returnStringAtRandom("rangiferine", "reindeer-like");
			case HUMAN:
				return UtilText.returnStringAtRandom("");
			case LYCAN:
				return UtilText.returnStringAtRandom("furry", "fur-coated", "wolf-like");
			case BAT_MORPH:
				return UtilText.returnStringAtRandom("large", "bat-like");
			case RAT_MORPH:
				return UtilText.returnStringAtRandom("rat-like");
			case RABBIT_MORPH:
				return UtilText.returnStringAtRandom("upright", "furry", "fur-coated", "rabbit-like");
			case RABBIT_MORPH_FLOPPY:
				return UtilText.returnStringAtRandom("floppy", "furry", "fur-coated", "rabbit-like");
			case BEE:
				return UtilText.returnStringAtRandom("beeish", "delicate");
		}
		return "";
	}
	
	public String getTransformName() {
		switch(this){
			case DOG_MORPH_POINTED:
				return "pointed canine";
			case DOG_MORPH_FOLDED:
				return "folded canine";
			case RABBIT_MORPH:
				return "upright rabbit";
			case RABBIT_MORPH_FLOPPY:
				return "floppy rabbit";
			default:
				return getRace().getTransformName();
				
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
	
	private static Map<Race, List<EarType>> typesMap = new HashMap<>();
	public static List<EarType> getEarTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<EarType> types = new ArrayList<>();
		for(EarType type : EarType.values()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}
