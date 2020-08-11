package com.lilithsthrone.game.character.body.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractAntennaType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public class AntennaType {

	public static final AbstractAntennaType NONE = new AbstractAntennaType(
			BodyCoveringType.HORN,
			Race.NONE,
			"none",
			"",
			"",
			new ArrayList<>(),
			new ArrayList<>(),
			"<br/>[npc.Name] now [npc.has] [style.boldTfGeneric(no antennae)].",
			"") {
	};

	private static List<AbstractAntennaType> allAntennaTypes;
	private static Map<AbstractAntennaType, String> antennaToIdMap = new HashMap<>();
	private static Map<String, AbstractAntennaType> idToAntennaMap = new HashMap<>();
	
	static {
		allAntennaTypes = new ArrayList<>();
		
		// Add in hard-coded antenna types:
		Field[] fields = AntennaType.class.getFields();
		
		for(Field f : fields){
			if (AbstractAntennaType.class.isAssignableFrom(f.getType())) {
				
				AbstractAntennaType ct;
				try {
					ct = ((AbstractAntennaType) f.get(null));

					antennaToIdMap.put(ct, f.getName());
					idToAntennaMap.put(f.getName(), ct);
					
					allAntennaTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static AbstractAntennaType getAntennaTypeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToAntennaMap.keySet());
		return idToAntennaMap.get(id);
	}
	
	public static String getIdFromAntennaType(AbstractAntennaType antennaType) {
		return antennaToIdMap.get(antennaType);
	}
	
	public static List<AbstractAntennaType> getAllAntennaTypes() {
		return allAntennaTypes;
	}
	
	private static Map<AbstractRace, List<AbstractAntennaType>> typesMap = new HashMap<>();
	public static List<AbstractAntennaType> getAntennaTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractAntennaType> types = new ArrayList<>();
		for(AbstractAntennaType type : AntennaType.getAllAntennaTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		if(types.isEmpty()) {
			types.add(AntennaType.NONE);
		}
		return types;
	}
}
