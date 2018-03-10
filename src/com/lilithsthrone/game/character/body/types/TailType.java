package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.1
 * @author Innoxia
 */
public enum TailType implements BodyPartTypeInterface {
	NONE(null, null, false, false),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON, true, true),

	IMP(BodyCoveringType.IMP, Race.IMP, true, true),

	DOG_MORPH(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH, false, false),
	
	DOG_MORPH_STUBBY(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH, false, false),
	
	LYCAN(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH, false, false),

	COW_MORPH(BodyCoveringType.BOVINE_FUR, Race.COW_MORPH, false, false),
	
	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH, true, false),

	SQUIRREL_MORPH(BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH, false, false),
	
	ALLIGATOR_MORPH(BodyCoveringType.ALLIGATOR_SCALES, Race.ALLIGATOR_MORPH, false, false),
	
	HORSE_MORPH(BodyCoveringType.HAIR_HORSE_HAIR, Race.HORSE_MORPH, false, false),

	REINDEER_MORPH(BodyCoveringType.REINDEER_FUR, Race.REINDEER_MORPH, false, false),
	
	HARPY(BodyCoveringType.FEATHERS, Race.HARPY, false, false);

	private BodyCoveringType skinType;
	private Race race;
	private boolean prehensile, suitableForPenetration;

	private TailType(BodyCoveringType skinType, Race race, boolean prehensile, boolean suitableForPenetration) {
		this.skinType = skinType;
		this.race = race;
		this.prehensile = prehensile;
		this.suitableForPenetration = suitableForPenetration;
	}
	
	@Override
	public boolean isDefaultPlural() {
		return false;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getTailCount()==1) {
			switch(this){
				case HARPY:
					return "a plume of";
				default:
					return "";
			}
		} else {
			switch(this){
				case HARPY:
					return Util.intToString(gc.getTailCount())+" plumes of";
				default:
					return Util.intToString(gc.getTailCount());
			}
		}
		
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		switch(this){
			case HARPY:
				return UtilText.returnStringAtRandom("tail feathers");
			default:
				return UtilText.returnStringAtRandom("tail");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			case HARPY:
				return UtilText.returnStringAtRandom("tail feathers");
			default:
				return UtilText.returnStringAtRandom("tails");
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("cat-like");
			case COW_MORPH:
				return UtilText.returnStringAtRandom("cow-like", "tufted");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("spaded", "demonic");
			case IMP:
				return UtilText.returnStringAtRandom("spaded", "impish");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("dog-like");
			case DOG_MORPH_STUBBY:
				return UtilText.returnStringAtRandom("stubby", "dog-like");
			case ALLIGATOR_MORPH:
				return UtilText.returnStringAtRandom("gator-like");
			case HARPY:
				return UtilText.returnStringAtRandom("colourful", "bird-like");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("horse-like");
			case REINDEER_MORPH:
				return UtilText.returnStringAtRandom("reindeer-like");
			case LYCAN:
				return UtilText.returnStringAtRandom("wolf-like");
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("squirrel-like", "fluffy");
			case NONE:
				return UtilText.returnStringAtRandom("");
		}
		
		return "";
	}
	
	public String getTransformName() {
		switch(this){
			case CAT_MORPH:
				return "feline";
			case COW_MORPH:
				return "bovine";
			case DEMON_COMMON:
				return "spaded";
			case IMP:
				return "spaded";
			case DOG_MORPH:
				return "canine";
			case DOG_MORPH_STUBBY:
				return UtilText.returnStringAtRandom("stubby canine");
			case HARPY:
				return "plume";
			case HORSE_MORPH:
				return "equine";
			case REINDEER_MORPH:
				return "rangiferine";
			case LYCAN:
				return "lupine";
			case SQUIRREL_MORPH:
				return "fluffy";
			case ALLIGATOR_MORPH:
				return "alligator";
			case NONE:
				return "none";
		}
		return "";
	}
	
	public String getTailTipName(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("tip");
		}
	}
	
	public String getTailTipDescriptor(GameCharacter gc) {
		switch(this){
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("spaded");
			case IMP:
				return UtilText.returnStringAtRandom("spaded");
			default:
				return UtilText.returnStringAtRandom("");
		}
	}

	@Override
	public BodyCoveringType getBodyCoveringType() {
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}
	
	public boolean isPrehensile() {
		return prehensile;
	}

	/**
	 * Takes into account whether player has 'Allow furry tail penetrations' turned on or off.
	 * @return
	 */
	public boolean isSuitableForPenetration() {
		return prehensile && (suitableForPenetration || Main.getProperties().furryTailPenetrationContent);
	}
	
	private static Map<Race, List<TailType>> typesMap = new HashMap<>();
	public static List<TailType> getTailTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<TailType> types = new ArrayList<>();
		for(TailType type : TailType.values()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}
