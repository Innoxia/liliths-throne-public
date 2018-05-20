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
public enum FaceType implements BodyPartTypeInterface {
	HUMAN(BodyCoveringType.HUMAN, MouthType.HUMAN, TongueType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.HUMAN, MouthType.ANGEL, TongueType.HUMAN, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, MouthType.DEMON_COMMON, TongueType.DEMON_COMMON, Race.DEMON),

	IMP(BodyCoveringType.IMP, MouthType.IMP, TongueType.IMP, Race.IMP),
	
	DOG_MORPH(BodyCoveringType.CANINE_FUR, MouthType.DOG_MORPH, TongueType.DOG_MORPH, Race.DOG_MORPH),
	
	LYCAN(BodyCoveringType.LYCAN_FUR, MouthType.WOLF_MORPH, TongueType.WOLF_MORPH, Race.WOLF_MORPH),

	CAT_MORPH(BodyCoveringType.FELINE_FUR, MouthType.CAT_MORPH, TongueType.CAT_MORPH, Race.CAT_MORPH),

	COW_MORPH(BodyCoveringType.BOVINE_FUR, MouthType.COW_MORPH, TongueType.COW_MORPH, Race.COW_MORPH),

	SQUIRREL_MORPH(BodyCoveringType.SQUIRREL_FUR, MouthType.SQUIRREL_MORPH, TongueType.SQUIRREL_MORPH, Race.SQUIRREL_MORPH),

	RAT_MORPH(BodyCoveringType.RAT_FUR, MouthType.RAT_MORPH, TongueType.RAT_MORPH, Race.RAT_MORPH),

	RABBIT_MORPH(BodyCoveringType.RABBIT_FUR, MouthType.RABBIT_MORPH, TongueType.RABBIT_MORPH, Race.RABBIT_MORPH),

	BAT_MORPH(BodyCoveringType.BAT_FUR, MouthType.BAT_MORPH, TongueType.BAT_MORPH, Race.BAT_MORPH),

	ALLIGATOR_MORPH(BodyCoveringType.ALLIGATOR_SCALES, MouthType.ALLIGATOR_MORPH, TongueType.ALLIGATOR_MORPH, Race.ALLIGATOR_MORPH),

	HORSE_MORPH(BodyCoveringType.HORSE_HAIR, MouthType.HORSE_MORPH, TongueType.HORSE_MORPH, Race.HORSE_MORPH),

	REINDEER_MORPH(BodyCoveringType.REINDEER_FUR, MouthType.REINDEER_MORPH, TongueType.REINDEER_MORPH, Race.REINDEER_MORPH),
	
	BEE(BodyCoveringType.BEE, MouthType.BEE, TongueType.BEE, Race.BEE),

	HARPY(BodyCoveringType.FEATHERS, MouthType.HARPY, TongueType.TENGU, Race.HARPY);

	private BodyCoveringType skinType;
	private MouthType mouthType;
	private TongueType tongueType;
	private Race race;

	private FaceType(BodyCoveringType skinType, MouthType mouthType, TongueType tongueType, Race race) {
		this.skinType = skinType;
		this.mouthType = mouthType;
		this.tongueType = tongueType;
		this.race = race;
	}
	
	@Override
	public boolean isDefaultPlural() {
		return false;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "";
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		switch(this){
			case ALLIGATOR_MORPH:
				return UtilText.returnStringAtRandom("snout", "face");
			case ANGEL:
			case DEMON_COMMON:
			case HARPY:
			case HORSE_MORPH:
			case HUMAN:
			case IMP:
			case BEE:
				return UtilText.returnStringAtRandom("face");
			case RAT_MORPH:
			case RABBIT_MORPH:
			case REINDEER_MORPH:
			case SQUIRREL_MORPH:
			case BAT_MORPH:
			case CAT_MORPH:
			case COW_MORPH:
			case DOG_MORPH:
			case LYCAN:
				return UtilText.returnStringAtRandom("muzzle", "face");
		}
		
		return "";
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			case ALLIGATOR_MORPH:
				return UtilText.returnStringAtRandom("snouts", "faces");
			case ANGEL:
			case DEMON_COMMON:
			case HARPY:
			case HORSE_MORPH:
			case HUMAN:
			case IMP:
			case BEE:
				return UtilText.returnStringAtRandom("faces");
			case RAT_MORPH:
			case RABBIT_MORPH:
			case REINDEER_MORPH:
			case SQUIRREL_MORPH:
			case BAT_MORPH:
			case CAT_MORPH:
			case COW_MORPH:
			case DOG_MORPH:
			case LYCAN:
				return UtilText.returnStringAtRandom("muzzles", "faces");
		}
		
		return "";
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("perfect", "flawless", "angelic");
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("anthropomorphic cat-like", "cat-like", "feline");
			case COW_MORPH:
				return UtilText.returnStringAtRandom("anthropomorphic cow-like", "cow-like", "bovine");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("perfect", "flawless", "demonic");
			case IMP:
				return UtilText.returnStringAtRandom("mischievous", "impish");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("anthropomorphic dog-like", "dog-like", "canine");
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("anthropomorphic squirrel-like", "squirrel-like", "rodent");
			case ALLIGATOR_MORPH:
				return UtilText.returnStringAtRandom("anthropomorphic alligator-like", "alligator-like", "reptile");
			case HARPY:
				return UtilText.returnStringAtRandom("anthropomorphic bird-like", "bird-like");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("anthropomorphic horse-like", "horse-like", "equine");
			case REINDEER_MORPH:
				return UtilText.returnStringAtRandom("anthropomorphic reindeer-like", "reindeer-like", "rangiferine");
			case HUMAN:
				return UtilText.returnStringAtRandom("");
			case LYCAN:
				return UtilText.returnStringAtRandom("anthropomorphic wolf-like", "wolf-like");
			case RAT_MORPH:
				return UtilText.returnStringAtRandom("anthropomorphic rat-like", "rat-like", "rodent");
			case BAT_MORPH:
				return UtilText.returnStringAtRandom("anthropomorphic bat-like", "bat-like");
			case RABBIT_MORPH:
				return UtilText.returnStringAtRandom("anthropomorphic rabbit-like", "rabbit-like");
			case BEE:
				return UtilText.returnStringAtRandom("anthropomorphic bee-like", "bee-like", "beeish");
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
	
	public MouthType getMouthType() {
		return mouthType;
	}

	public TongueType getTongueType() {
		return tongueType;
	}

	private static Map<Race, List<FaceType>> typesMap = new HashMap<>();
	public static List<FaceType> getFaceTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<FaceType> types = new ArrayList<>();
		for(FaceType type : FaceType.values()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}
