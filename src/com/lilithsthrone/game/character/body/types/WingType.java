package com.lilithsthrone.game.character.body.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
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
			"",
			"",
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


	private static List<AbstractWingType> allWingTypes;
	private static Map<AbstractWingType, String> wingToIdMap = new HashMap<>();
	private static Map<String, AbstractWingType> idToWingMap = new HashMap<>();
	
	static {
		allWingTypes = new ArrayList<>();
		
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
	
	private static Map<Race, List<AbstractWingType>> typesMap = new HashMap<>();
	public static List<AbstractWingType> getWingTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractWingType> types = new ArrayList<>();
		for(AbstractWingType type : WingType.getAllWingTypes()) { //TODO should have NONE?
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		if(types.isEmpty()) {
			for(AbstractWingType type : WingType.getAllWingTypes()) {
				if(type.getRace()==Race.NONE) {
					types.add(type);
				}
			}
		}
		typesMap.put(r, types);
		return types;
	}
}
