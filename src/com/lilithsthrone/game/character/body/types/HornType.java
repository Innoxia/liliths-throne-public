package com.lilithsthrone.game.character.body.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * 
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public class HornType {
	
	//TODO If any more horn types are added, check to see that the potion TFs still work. (5 types is currently the maximum.)
	
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

	// Cows:
	
	public static final AbstractHornType BOVINE_CURVED = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.COW_MORPH,
			2,
			"curved",
			"horn",
			"horns",
			Util.newArrayListOfValues("curved", "bovine"),
			Util.newArrayListOfValues("curved", "bovine", "smooth"),
			"slightly-curved #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF."
					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(curved #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], curved #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
	};

	public static final AbstractHornType BOVINE_STRAIGHT = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.COW_MORPH,
			2,
			"straight",
			"horn",
			"horns",
			Util.newArrayListOfValues("straight", "bovine"),
			Util.newArrayListOfValues("straight", "bovine", "smooth"),
			"sleek, straight #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF."
					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(straight #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], straight #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
	};

	// Reindeer:
	
	public static final AbstractHornType REINDEER_RACK = new AbstractHornType(
			BodyCoveringType.ANTLER_REINDEER,
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

	// Demons:
	
	public static final AbstractHornType CURLED = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.DEMON,
			2,
			"curled",
			"horn",
			"horns",
			Util.newArrayListOfValues("curled", "demonic"),
			Util.newArrayListOfValues("curled", "demonic", "smooth"),
			"circular-curling #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF."
					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(curled #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], circular-curling #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
	};
	
	public static final AbstractHornType SPIRAL = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.DEMON,
			2,
			"spiral", //Rasen no Chikara
			"horn",
			"horns",
			Util.newArrayListOfValues("spiral", "demonic"),
			Util.newArrayListOfValues("spiral", "demonic", "smooth"),
			"twisted, spiralling #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF."
					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(spiral #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], spiralling #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
	};
	
	public static final AbstractHornType CURVED = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.DEMON,
			2,
			"curved",
			"horn",
			"horns",
			Util.newArrayListOfValues("curved", "demonic"),
			Util.newArrayListOfValues("curved", "demonic", "smooth"),
			"slightly-curved #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF."
					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(curved #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], curved #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
	};
	
	public static final AbstractHornType SWEPT_BACK = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.DEMON,
			2,
			"swept-back",
			"horn",
			"horns",
			Util.newArrayListOfValues("swept-back", "demonic"),
			Util.newArrayListOfValues("swept-back", "demonic", "smooth"),
			"sleek #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF, before sweeping back and curving over [npc.her] head."
					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(swept-back #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], swept-back #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
	};
	
	public static final AbstractHornType STRAIGHT = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.DEMON,
			2,
			"straight",
			"horn",
			"horns",
			Util.newArrayListOfValues("straight", "demonic"),
			Util.newArrayListOfValues("straight", "demonic", "smooth"),
			"sleek, straight horns."
					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(straight #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], straight #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
	};
	
	
	private static List<AbstractHornType> allHornTypes;
	private static Map<AbstractHornType, String> hornToIdMap = new HashMap<>();
	private static Map<String, AbstractHornType> idToHornMap = new HashMap<>();
	
	static {
		allHornTypes = new ArrayList<>();
		
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
	}
	
	public static AbstractHornType getHornTypeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToHornMap.keySet());
		return idToHornMap.get(id);
	}
	
	public static String getIdFromHornType(AbstractHornType hornType) {
		return hornToIdMap.get(hornType);
	}
	
	public static List<AbstractHornType> getAllHornTypes() {
		return allHornTypes;
	}
	
	private static Map<Race, List<AbstractHornType>> typesMap = new HashMap<>();
	public static List<AbstractHornType> getHornTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractHornType> types = new ArrayList<>();
		for(AbstractHornType type : HornType.getAllHornTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		if(types.isEmpty()) {
			types.add(HornType.NONE);
		}
		return types;
	}
}
