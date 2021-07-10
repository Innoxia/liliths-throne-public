package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAnusType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public class AnusType {
	
	public static AbstractAnusType HUMAN = new AbstractAnusType(BodyCoveringType.ANUS,
			Race.HUMAN,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};
	
	public static AbstractAnusType ANGEL = new AbstractAnusType(BodyCoveringType.ANUS,
			Race.ANGEL,
			null,
			null,
			Util.newArrayListOfValues("angelic", "perfect"),
			Util.newArrayListOfValues("angelic", "perfect"),
			Util.newArrayListOfValues()){
	};
	
	public static AbstractAnusType DEMON_COMMON = new AbstractAnusType(BodyCoveringType.ANUS,
			Race.DEMON,
			null,
			null,
			Util.newArrayListOfValues("demonic", "irresistible"),
			Util.newArrayListOfValues("demonic", "irresistible"),
			Util.newArrayListOfValues(OrificeModifier.MUSCLE_CONTROL)){
	};
	
	public static AbstractAnusType COW_MORPH = new AbstractAnusType(BodyCoveringType.ANUS,
			Race.COW_MORPH,
			null,
			null,
			Util.newArrayListOfValues("cow-like", "bovine"),
			Util.newArrayListOfValues("cow-like", "bovine"),
			Util.newArrayListOfValues(OrificeModifier.PUFFY)){
	};
	
	public static AbstractAnusType DOG_MORPH = new AbstractAnusType(BodyCoveringType.ANUS,
			Race.DOG_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};
	
	public static AbstractAnusType FOX_MORPH = new AbstractAnusType(BodyCoveringType.ANUS,
			Race.FOX_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};
	
	public static AbstractAnusType SQUIRREL_MORPH = new AbstractAnusType(BodyCoveringType.ANUS,
			Race.SQUIRREL_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};
	
	public static AbstractAnusType RAT_MORPH = new AbstractAnusType(BodyCoveringType.ANUS,
			Race.RAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};
	
	public static AbstractAnusType RABBIT_MORPH = new AbstractAnusType(BodyCoveringType.ANUS,
			Race.RABBIT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};
	
	public static AbstractAnusType BAT_MORPH = new AbstractAnusType(BodyCoveringType.ANUS,
			Race.BAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};
	
	public static AbstractAnusType WOLF_MORPH = new AbstractAnusType(BodyCoveringType.ANUS,
			Race.BAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};
	
	public static AbstractAnusType CAT_MORPH = new AbstractAnusType(BodyCoveringType.ANUS,
			Race.CAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};
	
	public static AbstractAnusType HORSE_MORPH = new AbstractAnusType(BodyCoveringType.ANUS,
			Race.HORSE_MORPH,
			null,
			null,
			Util.newArrayListOfValues("horse-like", "equine"),
			Util.newArrayListOfValues("horse-like", "equine"),
			Util.newArrayListOfValues(OrificeModifier.PUFFY)){
	};
	
	public static AbstractAnusType REINDEER_MORPH = new AbstractAnusType(BodyCoveringType.ANUS,
			Race.REINDEER_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(OrificeModifier.PUFFY)){
	};
	
	public static AbstractAnusType ALLIGATOR_MORPH = new AbstractAnusType(BodyCoveringType.ANUS,
			Race.ALLIGATOR_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};
	
	public static AbstractAnusType HARPY = new AbstractAnusType(BodyCoveringType.ANUS,
			Race.HARPY,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()){
	};
	
	
	private static final List<AbstractAnusType> allAnusTypes;
	private static final Map<AbstractAnusType, String> anusToIdMap = new HashMap<>();
	private static final Map<String, AbstractAnusType> idToAnusMap = new HashMap<>();
	
	static {
		allAnusTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					if(Element.getDocumentRootElement(innerEntry.getValue()).getTagName().equals("anus")) {
						AbstractAnusType type = new AbstractAnusType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allAnusTypes.add(type);
						anusToIdMap.put(type, id);
						idToAnusMap.put(id, type);
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
		// External res types:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					if(Element.getDocumentRootElement(innerEntry.getValue()).getTagName().equals("anus")) {
						AbstractAnusType type = new AbstractAnusType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allAnusTypes.add(type);
						anusToIdMap.put(type, id);
						idToAnusMap.put(id, type);
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
		// Add in hard-coded anus types:
		Field[] fields = AnusType.class.getFields();
		for(Field f : fields){
			if (AbstractAnusType.class.isAssignableFrom(f.getType())) {
				AbstractAnusType ct;
				try {
					ct = ((AbstractAnusType) f.get(null));
					anusToIdMap.put(ct, f.getName());
					idToAnusMap.put(f.getName(), ct);
					allAnusTypes.add(ct);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		allAnusTypes.sort((t1, t2)->
				t1.getRace() == Race.NONE
						?-1
						:(t2.getRace() == Race.NONE
						?1
						:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractAnusType getAnusTypeFromId(String id) {
		if(id.equals("IMP")) {
			return AnusType.DEMON_COMMON;
		}
		id = Util.getClosestStringMatch(id, idToAnusMap.keySet());
		return idToAnusMap.get(id);
	}
	
	public static String getIdFromAnusType(AbstractAnusType anusType) {
		return anusToIdMap.get(anusType);
	}
	
	public static List<AbstractAnusType> getAllAnusTypes() {
		return allAnusTypes;
	}
	
	private static final Map<AbstractRace, List<AbstractAnusType>> typesMap = new HashMap<>();
	public static List<AbstractAnusType> getAnusTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractAnusType> types = new ArrayList<>();
		for(AbstractAnusType type : AnusType.getAllAnusTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
	
}