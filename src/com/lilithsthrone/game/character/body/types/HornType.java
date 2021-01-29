package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * 
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class HornType {
	
	// If any more horn types are added, check to see that the potion TFs still work. (5 types is currently the maximum.)
	
	public static final AbstractHornType NONE = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"none",
			"",
			"",
			new ArrayList<>(),
			new ArrayList<>(),
			"<br/>[npc.Name] now [npc.has] [style.boldTfGeneric(no horns)].",
			"") {
	};

//	// Cows:
//	
//	public static final AbstractHornType BOVINE_CURVED = new AbstractHornType(
//			BodyCoveringType.HORN,
//			Race.COW_MORPH,
//			2,
//			"curved",
//			"horn",
//			"horns",
//			Util.newArrayListOfValues("curved", "bovine"),
//			Util.newArrayListOfValues("curved", "bovine", "smooth"),
//			"slightly-curved #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF."
//					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(curved #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
//			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], curved #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
//	};
//
//	public static final AbstractHornType BOVINE_STRAIGHT = new AbstractHornType(
//			BodyCoveringType.HORN,
//			Race.COW_MORPH,
//			2,
//			"straight",
//			"horn",
//			"horns",
//			Util.newArrayListOfValues("straight", "bovine"),
//			Util.newArrayListOfValues("straight", "bovine", "smooth"),
//			"sleek, straight #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF."
//					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(straight #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
//			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], straight #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
//	};

	// Reindeer:
	
	public static final AbstractHornType REINDEER_RACK = new AbstractHornType(
			BodyCoveringType.ANTLER,
			Race.REINDEER_MORPH,
			2,
			"multi-branched",
			"antler",
			"antlers",
			Util.newArrayListOfValues("multi-branched", "reindeer"),
			Util.newArrayListOfValues("multi-branched", "reindeer"),
			"branching, multi-pronged #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF."
					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(reindeer-like #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], multi-branched #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
	};
	
	// Horse:
	
	public static final AbstractHornType HORSE_STRAIGHT = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.HORSE_MORPH,
			1,
			"unicorn",
			"horn",
			"horns",
			Util.newArrayListOfValues("straight", "twirling", "unicorn"),
			Util.newArrayListOfValues("straight", "twirling", "unicorn"),
			"straight, twirling unicorn's #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF."
					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(unicorn #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], unicorn's #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
	};

	// Generic:
	
	public static final AbstractHornType CURLED = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"curled",
			"horn",
			"horns",
			Util.newArrayListOfValues("curled"),
			Util.newArrayListOfValues("curled", "smooth"),
			"circular-curling #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF."
					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(curled #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], circular-curling #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	public static final AbstractHornType SPIRAL = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"spiral", //Rasen no Chikara
			"horn",
			"horns",
			Util.newArrayListOfValues("spiral"),
			Util.newArrayListOfValues("spiral", "smooth"),
			"twisted, spiralling #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF."
					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(spiral #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], spiralling #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	public static final AbstractHornType CURVED = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"curved",
			"horn",
			"horns",
			Util.newArrayListOfValues("curved"),
			Util.newArrayListOfValues("curved", "smooth"),
			"slightly-curved #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF."
					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(curved #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], curved #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	public static final AbstractHornType SWEPT_BACK = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"swept-back",
			"horn",
			"horns",
			Util.newArrayListOfValues("swept-back"),
			Util.newArrayListOfValues("swept-back", "smooth"),
			"sleek #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF, before sweeping back and curving over [npc.her] head."
					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(swept-back #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], swept-back #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	public static final AbstractHornType STRAIGHT = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"straight",
			"horn",
			"horns",
			Util.newArrayListOfValues("straight"),
			Util.newArrayListOfValues("straight", "smooth"),
			"sleek, straight horns."
					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(straight #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], straight #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	
	private static List<AbstractHornType> allHornTypes;
	private static Map<AbstractHornType, String> hornToIdMap = new HashMap<>();
	private static Map<String, AbstractHornType> idToHornMap = new HashMap<>();
	
	static {
		allHornTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("horn")) {
					try {
						AbstractHornType type = new AbstractHornType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allHornTypes.add(type);
						hornToIdMap.put(type, id);
						idToHornMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("horn")) {
					try {
						AbstractHornType type = new AbstractHornType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allHornTypes.add(type);
						hornToIdMap.put(type, id);
						idToHornMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded horn types:
		
		Field[] fields = HornType.class.getFields();
		
		for(Field f : fields){
			if (AbstractHornType.class.isAssignableFrom(f.getType())) {
				
				AbstractHornType ct;
				try {
					ct = ((AbstractHornType) f.get(null));

					hornToIdMap.put(ct, f.getName());
					idToHornMap.put(f.getName(), ct);
					
					allHornTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allHornTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractHornType getHornTypeFromId(String id) {
		if(id.equals("BOVINE_CURVED")) {
			return CURVED;
		} else if(id.equals("BOVINE_STRAIGHT")) {
			return STRAIGHT;
		}
		id = Util.getClosestStringMatch(id, idToHornMap.keySet());
		return idToHornMap.get(id);
	}
	
	public static String getIdFromHornType(AbstractHornType hornType) {
		return hornToIdMap.get(hornType);
	}
	
	public static List<AbstractHornType> getAllHornTypes() {
		return allHornTypes;
	}
	
	private static Map<AbstractRace, List<AbstractHornType>> typesMap = new HashMap<>();
	
	public static List<AbstractHornType> getHornTypes(AbstractRace r, boolean includeNone) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractHornType> types = new ArrayList<>();
		for(AbstractHornType type : HornType.getAllHornTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		if(types.isEmpty()) {
			for(AbstractHornType type : HornType.getAllHornTypes()) {
				if(type.getRace()==Race.NONE && (includeNone || type!=HornType.NONE)) {
					types.add(type);
				}
			}
		}
		typesMap.put(r, types);
		return types;
	
		
		
//		if(typesMap.containsKey(r)) {
//			return typesMap.get(r);
//		}
//		
//		List<AbstractHornType> types = new ArrayList<>();
//		for(AbstractHornType type : HornType.getAllHornTypes()) {
//			if(type.getRace()==r) {
//				types.add(type);
//			}
//		}
//		if(types.isEmpty()) {
//			types.add(HornType.NONE);
//		}
//		typesMap.put(r, types);
//		return types;
	}
}
