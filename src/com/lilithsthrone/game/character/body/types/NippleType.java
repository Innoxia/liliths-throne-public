package com.lilithsthrone.game.character.body.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractNippleType;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.8.2
 * @author Innoxia
 */
public class NippleType {

	public static AbstractNippleType HUMAN = new AbstractNippleType(BodyCoveringType.NIPPLES,
			Race.HUMAN,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};

	public static AbstractNippleType ANGEL = new AbstractNippleType(BodyCoveringType.NIPPLES,
			Race.ANGEL,
			Util.newArrayListOfValues("perfect", "flawless"),
			Util.newArrayListOfValues("perfect", "flawless"),
			Util.newArrayListOfValues()){
	};

	public static AbstractNippleType DEMON = new AbstractNippleType(BodyCoveringType.NIPPLES,
			Race.DEMON,
			Util.newArrayListOfValues("perfect", "flawless"),
			Util.newArrayListOfValues("perfect", "flawless"),
			Util.newArrayListOfValues()){
	};

	public static AbstractNippleType DOG_MORPH = new AbstractNippleType(BodyCoveringType.NIPPLES,
			Race.DOG_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};

	public static AbstractNippleType WOLF_MORPH = new AbstractNippleType(BodyCoveringType.NIPPLES,
			Race.WOLF_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};

	public static AbstractNippleType FOX_MORPH = new AbstractNippleType(BodyCoveringType.NIPPLES,
			Race.FOX_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};

	public static AbstractNippleType CAT_MORPH = new AbstractNippleType(BodyCoveringType.NIPPLES,
			Race.CAT_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};

	public static AbstractNippleType COW_MORPH = new AbstractNippleType(BodyCoveringType.NIPPLES,
			Race.COW_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};

	public static AbstractNippleType SQUIRREL_MORPH = new AbstractNippleType(BodyCoveringType.NIPPLES,
			Race.SQUIRREL_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};

	public static AbstractNippleType RAT_MORPH = new AbstractNippleType(BodyCoveringType.NIPPLES,
			Race.RAT_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};

	public static AbstractNippleType BAT_MORPH = new AbstractNippleType(BodyCoveringType.NIPPLES,
			Race.BAT_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};

	public static AbstractNippleType RABBIT_MORPH = new AbstractNippleType(BodyCoveringType.NIPPLES,
			Race.RABBIT_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};

	public static AbstractNippleType ALLIGATOR_MORPH = new AbstractNippleType(BodyCoveringType.NIPPLES,
			Race.ALLIGATOR_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};

	public static AbstractNippleType HORSE_MORPH = new AbstractNippleType(BodyCoveringType.NIPPLES,
			Race.HORSE_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};

	public static AbstractNippleType REINDEER_MORPH = new AbstractNippleType(BodyCoveringType.NIPPLES,
			Race.REINDEER_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};

	public static AbstractNippleType HARPY = new AbstractNippleType(BodyCoveringType.NIPPLES,
			Race.HARPY,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};

	
//	/**
//	 * Use instead of <i>valueOf()</i>.
//	 */
//	public static NippleType getTypeFromString(String value) {
//		if(value.equals("IMP")) {
//			value = "DEMON_COMMON";
//		}
//		return valueOf(value);
//	}
	
	private static List<AbstractNippleType> allNippleTypes;
	private static Map<AbstractNippleType, String> nippleToIdMap = new HashMap<>();
	private static Map<String, AbstractNippleType> idToNippleMap = new HashMap<>();
	
	static {
		allNippleTypes = new ArrayList<>();
		
		// Add in hard-coded nipple types:
		Field[] fields = NippleType.class.getFields();
		
		for(Field f : fields){
			if (AbstractNippleType.class.isAssignableFrom(f.getType())) {
				
				AbstractNippleType ct;
				try {
					ct = ((AbstractNippleType) f.get(null));

					nippleToIdMap.put(ct, f.getName());
					idToNippleMap.put(f.getName(), ct);
					
					allNippleTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static AbstractNippleType getNippleTypeFromId(String id) {
		if(id.equals("IMP") || id.equals("DEMON_COMMON")) {
			return NippleType.DEMON;
		}
		
		id = Util.getClosestStringMatch(id, idToNippleMap.keySet());
		return idToNippleMap.get(id);
	}
	
	public static String getIdFromNippleType(AbstractNippleType nippleType) {
		return nippleToIdMap.get(nippleType);
	}
	
	public static List<AbstractNippleType> getAllNippleTypes() {
		return allNippleTypes;
	}
	
	private static Map<Race, List<AbstractNippleType>> typesMap = new HashMap<>();
	public static List<AbstractNippleType> getNippleTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractNippleType> types = new ArrayList<>();
		for(AbstractNippleType type : NippleType.getAllNippleTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}