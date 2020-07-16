package com.lilithsthrone.game.character.body.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractFootType;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.10
 * @version 0.3.1
 * @author Innoxia
 */
public class FootType {
	
	public static AbstractFootType HUMANOID = new AbstractFootType("humanoid",
			"foot",
			"feet",
			Util.newArrayListOfValues("masculine"),
			Util.newArrayListOfValues("feminine", "soft", "delicate", "slender"),
			"toe",
			"toes",
			Util.newArrayListOfValues("masculine"),
			Util.newArrayListOfValues("feminine", "soft", "delicate", "slender"),
			"footjob",
			"[npc.SheHasFull] human-like feet.",
			Util.newArrayListOfValues(FootStructure.PLANTIGRADE)) {
	};

	public static AbstractFootType PAWS = new AbstractFootType("paw-like",
			"paw",
			"paws",
			Util.newArrayListOfValues("masculine","padded"),
			Util.newArrayListOfValues("feminine", "soft", "padded", "delicate", "slender"),
			"toe",
			"toes",
			Util.newArrayListOfValues("masculine", "padded"),
			Util.newArrayListOfValues("feminine", "soft", "padded", "delicate", "slender"),
			"footjob",
			"[npc.SheHasFull] paw-like feet.",
			Util.newArrayListOfValues(
					FootStructure.PLANTIGRADE,
					FootStructure.DIGITIGRADE)) {
	};

	public static AbstractFootType HOOFS = new AbstractFootType("hoof-like",
			"hoof",
			"hoofs",
			Util.newArrayListOfValues("masculine","hard"),
			Util.newArrayListOfValues("feminine", "delicate", "hard"),
			"hoof",
			"hoofs",
			Util.newArrayListOfValues("masculine", "hard"),
			Util.newArrayListOfValues("feminine", "hard", "delicate"),
			"hoofjob",
			"[npc.SheHasFull] hoofs in place of feet.",
			Util.newArrayListOfValues(
					FootStructure.UNGULIGRADE)) {
	};

	public static AbstractFootType TALONS = new AbstractFootType("bird-like",
			"talon",
			"talons",
			Util.newArrayListOfValues("masculine","clawed"),
			Util.newArrayListOfValues("feminine", "clawed", "slender"),
			"claw",
			"claws",
			Util.newArrayListOfValues("masculine", "sharp"),
			Util.newArrayListOfValues("feminine", "sharp", "slender"),
			"clawjob",
			"[npc.SheHasFull] bird-like talons in place of feet.",
			Util.newArrayListOfValues(
					FootStructure.DIGITIGRADE)) {
	};

	private static List<AbstractFootType> allFootTypes;
	private static Map<AbstractFootType, String> footToIdMap = new HashMap<>();
	private static Map<String, AbstractFootType> idToFootMap = new HashMap<>();
	
	static {
		allFootTypes = new ArrayList<>();
		
		// Add in hard-coded foot types:
		Field[] fields = FootType.class.getFields();
		
		for(Field f : fields){
			if (AbstractFootType.class.isAssignableFrom(f.getType())) {
				
				AbstractFootType ct;
				try {
					ct = ((AbstractFootType) f.get(null));

					footToIdMap.put(ct, f.getName());
					idToFootMap.put(f.getName(), ct);
					
					allFootTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static AbstractFootType getFootTypeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToFootMap.keySet());
		return idToFootMap.get(id);
	}
	
	public static String getIdFromFootType(AbstractFootType footType) {
		return footToIdMap.get(footType);
	}
	
	public static List<AbstractFootType> getAllFootTypes() {
		return allFootTypes;
	}
}
