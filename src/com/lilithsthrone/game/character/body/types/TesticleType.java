package com.lilithsthrone.game.character.body.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractTesticleType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.8.8
 * @author Innoxia
 */
public class TesticleType {
	
	public static AbstractTesticleType NONE = new AbstractTesticleType(null, Race.NONE, FluidType.CUM_HUMAN, false) {
	};

	public static AbstractTesticleType DILDO = new AbstractTesticleType(BodyCoveringType.getBodyCoveringTypeFromId("RUBBER_MAIN_SKIN"), Race.NONE, FluidType.CUM_HUMAN, false) {
	};
	
	public static AbstractTesticleType HUMAN = new AbstractTesticleType(BodyCoveringType.PENIS, Race.HUMAN, FluidType.CUM_HUMAN, false) {
	};

	public static AbstractTesticleType ANGEL = new AbstractTesticleType(BodyCoveringType.PENIS, Race.ANGEL, FluidType.CUM_ANGEL, false) {
	};

	public static AbstractTesticleType DEMON_COMMON = new AbstractTesticleType(BodyCoveringType.PENIS, Race.DEMON, FluidType.CUM_DEMON, false) {
	};

	public static AbstractTesticleType BOVINE = new AbstractTesticleType(BodyCoveringType.BOVINE_FUR, Race.COW_MORPH, FluidType.CUM_COW_MORPH, false) {
	};
	
	public static AbstractTesticleType CANINE = new AbstractTesticleType(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH, FluidType.CUM_DOG_MORPH, false) {
	};
	
	public static AbstractTesticleType LUPINE = new AbstractTesticleType(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH, FluidType.CUM_WOLF_MORPH, false) {
	};
	
	public static AbstractTesticleType FOX_MORPH = new AbstractTesticleType(BodyCoveringType.FOX_FUR, Race.FOX_MORPH, FluidType.CUM_FOX_MORPH, false) {
	};

	public static AbstractTesticleType FELINE = new AbstractTesticleType(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH, FluidType.CUM_CAT_MORPH, false) {
	};

	public static AbstractTesticleType ALLIGATOR_MORPH = new AbstractTesticleType(BodyCoveringType.PENIS, Race.ALLIGATOR_MORPH, FluidType.CUM_ALLIGATOR_MORPH, true) {
	};

	public static AbstractTesticleType EQUINE = new AbstractTesticleType(BodyCoveringType.PENIS, Race.HORSE_MORPH, FluidType.CUM_HORSE_MORPH, false) {
	};

	public static AbstractTesticleType REINDEER_MORPH = new AbstractTesticleType(BodyCoveringType.PENIS, Race.REINDEER_MORPH, FluidType.CUM_REINDEER_MORPH, false) {
	};

	public static AbstractTesticleType AVIAN = new AbstractTesticleType(BodyCoveringType.PENIS, Race.HARPY, FluidType.CUM_HARPY, true) {
	};
	
	public static AbstractTesticleType SQUIRREL = new AbstractTesticleType(BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH, FluidType.CUM_SQUIRREL_MORPH, false) {
	};

	public static AbstractTesticleType RAT_MORPH = new AbstractTesticleType(BodyCoveringType.PENIS, Race.RAT_MORPH, FluidType.CUM_RAT_MORPH, false) {
	};
	
	public static AbstractTesticleType RABBIT_MORPH = new AbstractTesticleType(BodyCoveringType.PENIS, Race.RABBIT_MORPH, FluidType.CUM_RABBIT_MORPH, false) {
	};

	public static AbstractTesticleType BAT_MORPH = new AbstractTesticleType(BodyCoveringType.PENIS, Race.BAT_MORPH, FluidType.CUM_BAT_MORPH, false) {
	};


	private static List<AbstractTesticleType> allTesticleTypes;
	private static Map<AbstractTesticleType, String> testicleToIdMap = new HashMap<>();
	private static Map<String, AbstractTesticleType> idToTesticleMap = new HashMap<>();
	
	static {
		allTesticleTypes = new ArrayList<>();
		
		// Add in hard-coded testicle types:
		Field[] fields = TesticleType.class.getFields();
		
		for(Field f : fields){
			if (AbstractTesticleType.class.isAssignableFrom(f.getType())) {
				
				AbstractTesticleType ct;
				try {
					ct = ((AbstractTesticleType) f.get(null));

					testicleToIdMap.put(ct, f.getName());
					idToTesticleMap.put(f.getName(), ct);
					
					allTesticleTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allTesticleTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractTesticleType getTesticleTypeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToTesticleMap.keySet());
		return idToTesticleMap.get(id);
	}
	
	public static String getIdFromTesticleType(AbstractTesticleType testicleType) {
		return testicleToIdMap.get(testicleType);
	}
	
	public static List<AbstractTesticleType> getAllTesticleTypes() {
		return allTesticleTypes;
	}
	
	private static Map<AbstractRace, List<AbstractTesticleType>> typesMap = new HashMap<>();
	public static List<AbstractTesticleType> getTesticleTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractTesticleType> types = new ArrayList<>();
		for(AbstractTesticleType type : TesticleType.getAllTesticleTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
	
}