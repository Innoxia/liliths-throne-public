package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractAntennaType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
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
			BodyCoveringType.ANTENNA,
			Race.NONE,
			"none",
			"antenna",
			"antennae",
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
		
		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("antenna")) {
					try {
						AbstractAntennaType type = new AbstractAntennaType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allAntennaTypes.add(type);
						antennaToIdMap.put(type, id);
						idToAntennaMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// External res types:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("antenna")) {
					try {
						AbstractAntennaType type = new AbstractAntennaType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allAntennaTypes.add(type);
						antennaToIdMap.put(type, id);
						idToAntennaMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
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
		
		Collections.sort(allAntennaTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
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
