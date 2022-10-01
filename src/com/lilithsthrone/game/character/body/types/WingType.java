package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.8.2
 * @author Innoxia
 */
public class WingType {

	// If any more wing types are added, check to see that the potion TFs still work. (5 types is currently the maximum.)
	
	public static final AbstractWingType NONE = new AbstractWingType(
			null,
			Race.NONE,
			false,
			"none",
			"wing",
			"wings",
			new ArrayList<>(),
			new ArrayList<>(),
			"#IF(npc.getLegConfiguration().isWingsOnLegConfiguration())"
					+ "With a strong tugging sensation, [npc.her] [npc.wings] shrink away and disappear into the sides of [npc.her] [npc.legConfiguration] body."
			+ "#ELSE"
				+ "With a strong tugging sensation, [npc.her] [npc.wings] shrink away and disappear into [npc.her] back."
			+ "#ENDIF"
			+ "<br/>[npc.Name] now [npc.has] [style.boldTfGeneric(no wings)].",
			"") {
	};

	// Angels:
	
	public static final AbstractWingType ANGEL = new AbstractWingType(
			BodyCoveringType.ANGEL_FEATHER,
			Race.ANGEL,
			true,
			"angelic feathered",
			"wing",
			"wings",
			Util.newArrayListOfValues("angelic", "feathered"),
			Util.newArrayListOfValues("angelic", "feathered"),
			"#IF(npc.getLegConfiguration().isWingsOnLegConfiguration())"
				+ "[npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], feathered, angelic wings push out from the sides of [npc.her] [npc.legConfiguration] body."
			+ "#ELSE"
				+ "[npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], feathered, angelic wings push out from [npc.her] shoulder blades."
			+ "#ENDIF"
			+ "<br/>"
			+ "[npc.Name] now [npc.has] [style.boldAngel(angelic, feathered wings)].",
			"[npc.sheHasFull] a pair of [npc.wingSize], feathered, angelic wings, which are [npc.materialDescriptor] [npc.wingFullDescription(true)].") {
	};

	// Demons:
	
	public static final AbstractWingType DEMON_COMMON = new AbstractWingType(
			BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			true,
			"demonic leathery",
			"wing",
			"wings",
			Util.newArrayListOfValues("demonic", "leathery"),
			Util.newArrayListOfValues("demonic", "leathery"),
			"#IF(npc.getLegConfiguration().isWingsOnLegConfiguration())"
				+ "[npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], leathery, demonic wings push out from the sides of [npc.her] [npc.legConfiguration] body."
			+ "#ELSE"
				+ "[npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], leathery, demonic wings push out from [npc.her] shoulder blades."
			+ "#ENDIF"
			+ "<br/>"
			+ "#IF(npc.isShortStature())"
				+ "[npc.Name] now [npc.has] [style.boldImp(impish, leathery wings)]."
			+ "#ELSE"
				+ "[npc.Name] now [npc.has] [style.boldDemon(demonic, leathery wings)]."
			+ "#ENDIF",
			"[npc.sheHasFull] a pair of [npc.wingSize], leathery, demonic wings, which are [npc.materialDescriptor] [npc.wingFullDescription(true)].") {
	};

	public static final AbstractWingType DEMON_FEATHERED = new AbstractWingType(
			BodyCoveringType.DEMON_FEATHER,
			Race.DEMON,
			true,
			"demonic feathered",
			"wing",
			"wings",
			Util.newArrayListOfValues("demonic", "feathered"),
			Util.newArrayListOfValues("demonic", "feathered"),
			"#IF(npc.getLegConfiguration().isWingsOnLegConfiguration())"
				+ "[npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], feathered, demonic wings push out from the sides of [npc.her] [npc.legConfiguration] body."
			+ "#ELSE"
				+ "[npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], feathered, demonic wings push out from [npc.her] shoulder blades."
			+ "#ENDIF"
			+ "<br/>"
			+ "#IF(npc.isShortStature())"
				+ "[npc.Name] now [npc.has] [style.boldImp(impish, feathered wings)]."
			+ "#ELSE"
				+ "[npc.Name] now [npc.has] [style.boldDemon(demonic, feathered wings)]."
			+ "#ENDIF",
			"[npc.sheHasFull] a pair of [npc.wingSize], feathered, demonic wings, which are [npc.materialDescriptor] [npc.wingFullDescription(true)].") {
	};
	
	// Generic:

	public static final AbstractWingType LEATHERY = new AbstractWingType(
			BodyCoveringType.WING_LEATHER,
			Race.NONE,
			true,
			"leathery",
			"wing",
			"wings",
			Util.newArrayListOfValues("leathery"),
			Util.newArrayListOfValues("leathery"),
			"#IF(npc.getLegConfiguration().isWingsOnLegConfiguration())"
				+ "[npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], leathery wings push out from the sides of [npc.her] [npc.legConfiguration] body."
			+ "#ELSE"
				+ "[npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], leathery wings push out from [npc.her] shoulder blades."
			+ "#ENDIF"
			+ "<br/>"
			+ "[npc.Name] now [npc.has] [style.boldTfGeneric(leathery wings)].",
			"[npc.sheHasFull] a pair of [npc.wingSize], leathery wings, which are [npc.materialDescriptor] [npc.wingFullDescription(true)].") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};

	public static final AbstractWingType FEATHERED = new AbstractWingType(
			BodyCoveringType.FEATHERS,
			Race.NONE,
			true,
			"feathered",
			"wing",
			"wings",
			Util.newArrayListOfValues("feathered"),
			Util.newArrayListOfValues("feathered"),
			"#IF(npc.getLegConfiguration().isWingsOnLegConfiguration())"
				+ "[npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], feathered wings push out from the sides of [npc.her] [npc.legConfiguration] body."
			+ "#ELSE"
				+ "[npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], feathered wings push out from [npc.her] shoulder blades."
			+ "#ENDIF"
			+ "<br/>"
			+ "[npc.Name] now [npc.has] [style.boldTfGeneric(feathered wings)].",
			"[npc.sheHasFull] a pair of [npc.wingSize], feathered wings, which are [npc.materialDescriptor] [npc.wingFullDescription(true)].") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};

	public static final AbstractWingType INSECT = new AbstractWingType(
			BodyCoveringType.WING_CHITIN,
			Race.NONE,
			true,
			"insect",
			"wing",
			"wings",
			Util.newArrayListOfValues("chitinous"),
			Util.newArrayListOfValues("chitinous"),
			"#IF(npc.getLegConfiguration().isWingsOnLegConfiguration())"
				+ "[npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], insect-like wings push out from the sides of [npc.her] [npc.legConfiguration] body."
			+ "#ELSE"
				+ "[npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], insect-like wings push out from [npc.her] shoulder blades."
			+ "#ENDIF"
			+ "<br/>"
			+ "[npc.Name] now [npc.has] [style.boldTfGeneric(chitinous, insect-like wings)].",
			"[npc.sheHasFull] a pair of [npc.wingSize], insect-like wings, which are [npc.materialDescriptor] [npc.wingFullDescription(true)].") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};


	private static List<AbstractWingType> allWingTypes;
	private static Map<AbstractWingType, String> wingToIdMap = new HashMap<>();
	private static Map<String, AbstractWingType> idToWingMap = new HashMap<>();
	
	static {
		allWingTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("wing")) {
					try {
						AbstractWingType type = new AbstractWingType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allWingTypes.add(type);
						wingToIdMap.put(type, id);
						idToWingMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("wing")) {
					try {
						AbstractWingType type = new AbstractWingType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allWingTypes.add(type);
						wingToIdMap.put(type, id);
						idToWingMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded wing types:
		
		Field[] fields = WingType.class.getFields();
		
		for(Field f : fields){
			if (AbstractWingType.class.isAssignableFrom(f.getType())) {
				
				AbstractWingType ct;
				try {
					ct = ((AbstractWingType) f.get(null));

					wingToIdMap.put(ct, f.getName());
					idToWingMap.put(f.getName(), ct);
					
					allWingTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allWingTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractWingType getWingTypeFromId(String id) {
		if(id.equals("IMP")) {
			return WingType.DEMON_COMMON;
		}
		if(id.equals("PEGASUS")) {
			return WingType.FEATHERED;
		}
		id = Util.getClosestStringMatch(id, idToWingMap.keySet());
		return idToWingMap.get(id);
	}
	
	public static String getIdFromWingType(AbstractWingType wingType) {
		return wingToIdMap.get(wingType);
	}
	
	public static List<AbstractWingType> getAllWingTypes() {
		return allWingTypes;
	}
	
	private static Map<AbstractRace, List<AbstractWingType>> typesMap = new HashMap<>();
	
	public static List<AbstractWingType> getWingTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractWingType> types = new ArrayList<>();
		for(AbstractWingType type : WingType.getAllWingTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		if(types.isEmpty()) {
			for(AbstractWingType type : WingType.getAllWingTypes()) {
				if(type.isGeneric()) {
					types.add(type);
				}
			}
		}
		typesMap.put(r, types);
		return types;
	}
}
