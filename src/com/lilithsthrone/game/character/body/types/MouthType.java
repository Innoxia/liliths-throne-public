package com.lilithsthrone.game.character.body.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractMouthType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public class MouthType {
	
	public static AbstractMouthType HUMAN = new AbstractMouthType(Race.HUMAN, TongueType.HUMAN) {};

	public static AbstractMouthType ANGEL = new AbstractMouthType(Race.ANGEL, TongueType.ANGEL) {};

	public static AbstractMouthType DEMON_COMMON = new AbstractMouthType(Race.DEMON, TongueType.DEMON_COMMON) {};

	public static AbstractMouthType DOG_MORPH = new AbstractMouthType(Race.DOG_MORPH, TongueType.DOG_MORPH) {};

	public static AbstractMouthType WOLF_MORPH = new AbstractMouthType(Race.WOLF_MORPH, TongueType.WOLF_MORPH) {};

	public static AbstractMouthType FOX_MORPH = new AbstractMouthType(Race.FOX_MORPH, TongueType.FOX_MORPH) {};

	public static AbstractMouthType CAT_MORPH = new AbstractMouthType(Race.CAT_MORPH, TongueType.CAT_MORPH) {};

	public static AbstractMouthType COW_MORPH = new AbstractMouthType(Race.COW_MORPH, TongueType.COW_MORPH) {};

	public static AbstractMouthType SQUIRREL_MORPH = new AbstractMouthType(Race.SQUIRREL_MORPH, TongueType.SQUIRREL_MORPH) {};

	public static AbstractMouthType RAT_MORPH = new AbstractMouthType(Race.RAT_MORPH, TongueType.RAT_MORPH) {};

	public static AbstractMouthType RABBIT_MORPH = new AbstractMouthType(Race.RABBIT_MORPH, TongueType.RABBIT_MORPH) {};

	public static AbstractMouthType BAT_MORPH = new AbstractMouthType(Race.BAT_MORPH, TongueType.BAT_MORPH) {};

	public static AbstractMouthType ALLIGATOR_MORPH = new AbstractMouthType(Race.ALLIGATOR_MORPH, TongueType.ALLIGATOR_MORPH) {};

	public static AbstractMouthType HORSE_MORPH = new AbstractMouthType(Race.HORSE_MORPH, TongueType.HORSE_MORPH) {};

	public static AbstractMouthType REINDEER_MORPH = new AbstractMouthType(Race.REINDEER_MORPH, TongueType.REINDEER_MORPH) {};

	public static AbstractMouthType HARPY = new AbstractMouthType(BodyCoveringType.MOUTH,
			Race.HARPY,
			TongueType.HARPY,
			Util.newArrayListOfValues("beak"),
			Util.newArrayListOfValues("beaks"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			null,
			Util.newArrayListOfValues()) {
	};
	
	private static List<AbstractMouthType> allMouthTypes;
	private static Map<AbstractMouthType, String> mouthToIdMap = new HashMap<>();
	private static Map<String, AbstractMouthType> idToMouthMap = new HashMap<>();
	
	static {
		allMouthTypes = new ArrayList<>();
		
		// Add in hard-coded mouth types:
		Field[] fields = MouthType.class.getFields();
		
		for(Field f : fields){
			if (AbstractMouthType.class.isAssignableFrom(f.getType())) {
				
				AbstractMouthType ct;
				try {
					ct = ((AbstractMouthType) f.get(null));

					mouthToIdMap.put(ct, f.getName());
					idToMouthMap.put(f.getName(), ct);
					
					allMouthTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static AbstractMouthType getMouthTypeFromId(String id) {
		if(id.equals("IMP")) {
			return MouthType.DEMON_COMMON;
		}
		
		id = Util.getClosestStringMatch(id, idToMouthMap.keySet());
		return idToMouthMap.get(id);
	}
	
	public static String getIdFromMouthType(AbstractMouthType mouthType) {
		return mouthToIdMap.get(mouthType);
	}
	
	public static List<AbstractMouthType> getAllMouthTypes() {
		return allMouthTypes;
	}
	
	private static Map<AbstractRace, List<AbstractMouthType>> typesMap = new HashMap<>();
	
	public static List<AbstractMouthType> getMouthTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractMouthType> types = new ArrayList<>();
		for(AbstractMouthType type : MouthType.getAllMouthTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
	
}