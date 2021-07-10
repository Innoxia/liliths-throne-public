package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTongueType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public class TongueType {
	
	public static AbstractTongueType HUMAN = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.HUMAN,
			3,
			"tongue",
			"tongues",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)] [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType ANGEL = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.ANGEL,
			3,
			"tongue",
			"tongues",
			Util.newArrayListOfValues("angelic"),
			Util.newArrayListOfValues("angelic"),
			" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)] [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType DEMON_COMMON = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.DEMON,
			6,
			"tongue",
			"tongues",
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)] [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType DOG_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.DOG_MORPH,
			8,
			"tongue",
			"tongues",
			Util.newArrayListOfValues("dog-like"),
			Util.newArrayListOfValues("dog-like"),
			" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)], dog-like [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.WIDE,
					TongueModifier.FLAT)) {
	};
	
	public static AbstractTongueType WOLF_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.WOLF_MORPH,
			8,
			"tongue",
			"tongues",
			Util.newArrayListOfValues("wolf-like"),
			Util.newArrayListOfValues("wolf-like"),
			" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)], wolf-like [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.WIDE,
					TongueModifier.FLAT)) {
	};
	
	public static AbstractTongueType FOX_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.FOX_MORPH,
			6,
			"tongue",
			"tongues",
			Util.newArrayListOfValues("fox-like"),
			Util.newArrayListOfValues("fox-like"),
			" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)], fox-like [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.FLAT)) {
	};
	
	public static AbstractTongueType CAT_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.CAT_MORPH,
			6,
			"tongue",
			"tongues",
			Util.newArrayListOfValues("cat-like"),
			Util.newArrayListOfValues("cat-like"),
			" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)], cat-like [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.FLAT)) {
	};
	
	public static AbstractTongueType COW_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.COW_MORPH,
			12,
			"tongue",
			"tongues",
			Util.newArrayListOfValues("cow-like"),
			Util.newArrayListOfValues("cow-like"),
			" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)], cow-like [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.STRONG)) {
	};
	
	public static AbstractTongueType ALLIGATOR_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.ALLIGATOR_MORPH,
			6,
			"tongue",
			"tongues",
			Util.newArrayListOfValues("alligator-like"),
			Util.newArrayListOfValues("alligator-like"),
			" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)], alligator-like [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.STRONG)) {
	};
	
	public static AbstractTongueType HORSE_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.HORSE_MORPH,
			8,
			"tongue",
			"tongues",
			Util.newArrayListOfValues("horse-like"),
			Util.newArrayListOfValues("horse-like"),
			" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)], horse-like [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.STRONG)) {
	};
	
	public static AbstractTongueType REINDEER_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.REINDEER_MORPH,
			8,
			"tongue",
			"tongues",
			Util.newArrayListOfValues("reindeer-like"),
			Util.newArrayListOfValues("reindeer-like"),
			" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)], reindeer-like [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.STRONG)) {
	};
	
	public static AbstractTongueType HARPY = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.HARPY,
			6,
			"tongue",
			"tongues",
			Util.newArrayListOfValues("bird-like"),
			Util.newArrayListOfValues("bird-like"),
			" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)], bird-like [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.FLAT)) {
	};
	
	public static AbstractTongueType SQUIRREL_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.SQUIRREL_MORPH,
			6,
			"tongue",
			"tongues",
			Util.newArrayListOfValues("squirrel-like"),
			Util.newArrayListOfValues("squirrel-like"),
			" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)], squirrel-like [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType RAT_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.RAT_MORPH,
			6,
			"tongue",
			"tongues",
			Util.newArrayListOfValues("rat-like"),
			Util.newArrayListOfValues("rat-like"),
			" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)], rat-like [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType RABBIT_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.RABBIT_MORPH,
			6,
			"tongue",
			"tongues",
			Util.newArrayListOfValues("rabbit-like"),
			Util.newArrayListOfValues("rabbit-like"),
			" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)], rabbit-like [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType BAT_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.BAT_MORPH,
			6,
			"tongue",
			"tongues",
			Util.newArrayListOfValues("bat-like"),
			Util.newArrayListOfValues("bat-like"),
			" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)], bat-like [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.",
			Util.newArrayListOfValues()) {
	};
	
	private static final List<AbstractTongueType> allTongueTypes;
	private static final Map<AbstractTongueType, String> tongueToIdMap = new HashMap<>();
	private static final Map<String, AbstractTongueType> idToTongueMap = new HashMap<>();
	
	static {
		allTongueTypes = new ArrayList<>();
		
		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					if(Element.getDocumentRootElement(innerEntry.getValue()).getTagName().equals("tongue")) {
						AbstractTongueType type = new AbstractTongueType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTongueTypes.add(type);
						tongueToIdMap.put(type, id);
						idToTongueMap.put(id, type);
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
					if(Element.getDocumentRootElement(innerEntry.getValue()).getTagName().equals("tongue")) {
						AbstractTongueType type = new AbstractTongueType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTongueTypes.add(type);
						tongueToIdMap.put(type, id);
						idToTongueMap.put(id, type);
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
		// Add in hard-coded tongue types:
		Field[] fields = TongueType.class.getFields();
		for(Field f : fields){
			if (AbstractTongueType.class.isAssignableFrom(f.getType())) {
				AbstractTongueType ct;
				try {
					ct = ((AbstractTongueType) f.get(null));
					tongueToIdMap.put(ct, f.getName());
					idToTongueMap.put(f.getName(), ct);
					allTongueTypes.add(ct);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		allTongueTypes.sort((t1, t2)->
				t1.getRace() == Race.NONE
						?-1
						:(t2.getRace() == Race.NONE
						?1
						:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractTongueType getTongueTypeFromId(String id) {
		switch (id) {
			case "IMP":
				return TongueType.DEMON_COMMON;
			case "LYCAN":
				return TongueType.WOLF_MORPH;
			case "TENGU":
				return TongueType.HARPY;
		}
		id = Util.getClosestStringMatch(id, idToTongueMap.keySet());
		return idToTongueMap.get(id);
	}
	
	public static String getIdFromTongueType(AbstractTongueType tongueType) {
		return tongueToIdMap.get(tongueType);
	}
	
	public static List<AbstractTongueType> getAllTongueTypes() {
		return allTongueTypes;
	}
	
	private static final Map<AbstractRace, List<AbstractTongueType>> typesMap = new HashMap<>();
	
	public static List<AbstractTongueType> getTongueTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractTongueType> types = new ArrayList<>();
		for(AbstractTongueType type : TongueType.getAllTongueTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}

}