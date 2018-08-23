package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public enum TailType implements BodyPartTypeInterface {
	NONE(null, null, false, false),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON, true, true),
	
	DEMON_HAIR_TIP(BodyCoveringType.DEMON_COMMON, Race.DEMON, true, false),

	DOG_MORPH(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH, false, false),
	
	DOG_MORPH_STUBBY(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH, false, false),
	
	LYCAN(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH, false, false),
	
	FOX_MORPH(BodyCoveringType.FOX_FUR, Race.FOX_MORPH, false, false),
	
	FOX_MORPH_MAGIC(BodyCoveringType.FOX_FUR, Race.FOX_MORPH, false, false),

	COW_MORPH(BodyCoveringType.BOVINE_FUR, Race.COW_MORPH, false, false),
	
	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH, true, false),
	
	CAT_MORPH_SHORT(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH, false, false),
	
	CAT_MORPH_TUFTED(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH, true, false),

	SQUIRREL_MORPH(BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH, false, false),
	
	RAT_MORPH(BodyCoveringType.RAT_SKIN, Race.RAT_MORPH, true, true),
	
	RABBIT_MORPH(BodyCoveringType.RABBIT_FUR, Race.RABBIT_MORPH, false, false),
	
	ALLIGATOR_MORPH(BodyCoveringType.ALLIGATOR_SCALES, Race.ALLIGATOR_MORPH, false, false),
	
	HORSE_MORPH(BodyCoveringType.HAIR_HORSE_HAIR, Race.HORSE_MORPH, false, false),
	
	HORSE_MORPH_ZEBRA(BodyCoveringType.HAIR_HORSE_HAIR, Race.HORSE_MORPH, false, false),

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

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static TailType getTypeFromString(String value) {
		if(value.equals("IMP")) {
			value = "DEMON_COMMON";
		}
		return valueOf(value);
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
			case CAT_MORPH_SHORT:
				return UtilText.returnStringAtRandom("cat-like", "short");
			case CAT_MORPH_TUFTED:
				return UtilText.returnStringAtRandom("cat-like", "tufted");
			case COW_MORPH:
				return UtilText.returnStringAtRandom("cow-like", "tufted");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("spaded", "demonic");
			case DEMON_HAIR_TIP:
				return UtilText.returnStringAtRandom("hair-tipped", "demonic");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("dog-like");
			case DOG_MORPH_STUBBY:
				return UtilText.returnStringAtRandom("stubby", "dog-like");
			case ALLIGATOR_MORPH:
				return UtilText.returnStringAtRandom("alligator-like");
			case HARPY:
				return UtilText.returnStringAtRandom("colourful", "bird-like");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("horse-like");
			case HORSE_MORPH_ZEBRA:
				return UtilText.returnStringAtRandom("zebra-like");
			case REINDEER_MORPH:
				return UtilText.returnStringAtRandom("reindeer-like");
			case LYCAN:
				return UtilText.returnStringAtRandom("wolf-like");
			case FOX_MORPH:
				return UtilText.returnStringAtRandom("fox-like", "fluffy");
			case FOX_MORPH_MAGIC:
				return UtilText.returnStringAtRandom("magical", "fox-like", "fluffy");
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("squirrel-like", "fluffy");
			case NONE:
				return UtilText.returnStringAtRandom("");
			case RAT_MORPH:
				return UtilText.returnStringAtRandom("rat-like");
			case RABBIT_MORPH:
				return UtilText.returnStringAtRandom("rabbit-like", "fluffy");
		}
		
		return "";
	}

	@Override
	public String getTransformName() {
		switch(this){
			case CAT_MORPH:
				return "feline";
			case CAT_MORPH_SHORT:
				return "short feline";
			case CAT_MORPH_TUFTED:
				return "tufted feline";
			case COW_MORPH:
				return "bovine";
			case DEMON_COMMON:
				return "demonic spaded";
			case DEMON_HAIR_TIP:
				return "demonic hair-tipped";
			case DOG_MORPH:
				return "canine";
			case DOG_MORPH_STUBBY:
				return "stubby canine";
			case HARPY:
				return "harpy plume";
			case HORSE_MORPH:
				return "equine";
			case HORSE_MORPH_ZEBRA:
				return "zebra";
			case REINDEER_MORPH:
				return "reindeer";
			case LYCAN:
				return "wolf";
			case FOX_MORPH:
				return "fox";
			case SQUIRREL_MORPH:
				return "squirrel";
			case FOX_MORPH_MAGIC:
				return "arcane fox";
			case ALLIGATOR_MORPH:
				return "alligator";
			case NONE:
				return "none";
			case RAT_MORPH:
				return "rat";
			case RABBIT_MORPH:
				return "rabbit";
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
	
	public boolean isPrehensile() {
		return prehensile;
	}

	/**
	 * Takes into account whether player has 'Allow furry tail penetrations' turned on or off.
	 * @return
	 */
	public boolean isSuitableForPenetration() {
		return prehensile && (suitableForPenetration || Main.getProperties().hasValue(PropertyValue.furryTailPenetrationContent));
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
	

	public static List<TailType> getTailTypesSuitableForTransformation(List<TailType> options) {
		if (!options.contains(TailType.NONE)) {
			return options;
		}
		
		List<TailType> duplicatedOptions = new ArrayList<>(options);
		duplicatedOptions.remove(TailType.NONE);
		return duplicatedOptions;
	}
}