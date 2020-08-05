package com.lilithsthrone.game.character.body.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractTentacleType;
import com.lilithsthrone.game.character.body.tags.TentacleTypeTag;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.8
 * @version 0.3.8.9
 * @author Innoxia
 */
public class TentacleType {
	
	public static final AbstractTentacleType NONE = new AbstractTentacleType(
			null,
			Race.NONE,
			PenetrationGirth.THREE_AVERAGE,
			0f,
			"none",
			"",
			"",
			"",
			"",
			Util.newArrayListOfValues(),
			Util.newArrayListOfValues(),
			"",
			"",
			Util.newArrayListOfValues(),
			Util.newArrayListOfValues(),
			"#IF(npc.getTentacleCount()==1)"
					+ " [npc.She] gasps as [npc.she] feels [npc.her] [npc.tentacle] shrinking down and disappearing into [npc.her] body."
				+ "#ELSE"
					+ " [npc.She] gasps as [npc.she] feels [npc.her] [npc.tentacles] shrinking down and disappearing into [npc.her] body."
				+ "#ENDIF"
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldTfGeneric(no tentacles)].",
			"[style.colourDisabled([npc.She] [npc.do] not have any tentacles.)]",
			Util.newArrayListOfValues()) {
	};
	
	public static final AbstractTentacleType DEMON_COMMON = new AbstractTentacleType(
			BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			PenetrationGirth.ONE_SLENDER,
			1f,
			"demonic",
			"",
			"",
			"tentacle",
			"tentacles",
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			"tip",
			"tips",
			Util.newArrayListOfValues("rounded"),
			Util.newArrayListOfValues("rounded"),
			"#IF(npc.getTentacleCount()==1)"
				+ " A demonic tentacle sprouts from [npc.her] back, rapidly growing in size until it's about [npc.tentacleLength] long."
				+ " [npc.She] quickly [npc.verb(realise)] that [npc.she] [npc.has] complete control over where it goes, allowing [npc.herHim] to use it like a third limb."
				+ "<br/>"
				+ "[npc.Name] now [npc.has]"
				+ "#IF(npc.isShortStature())"
					+ " an [style.boldImp(impish tentacle)]"
				+ "#ELSE"
					+ " a [style.boldDemon(demonic tentacle)]"
				+ "#ENDIF"
				+ ", [npc.materialDescriptor] [npc.tentacleFullDescription(true)]."
			+ "#ELSE"
				+ " [npc.TentacleCount] demonic tentacles sprout from [npc.her] back, rapidly growing in size until they're each about [npc.tentacleLength] long."
				+ " [npc.She] quickly [npc.verb(realise)] that [npc.she] [npc.has] complete control over where they go, allowing [npc.herHim] to use them like extra limbs."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [npc.tentacleCount]"
				+ "#IF(npc.isShortStature())"
					+ " [style.boldImp(impish tentacles)]"
				+ "#ELSE"
					+ " [style.boldDemon(demonic tentacles)]"
				+ "#ENDIF"
				+ ", [npc.materialDescriptor] [npc.tentacleFullDescription(true)]."
			+ "#ENDIF",
			"Growing out from [npc.her] back, [npc.sheHasFull]"
				+ "#IF(npc.getTentacleCount()==1)"
					+ " a spaded, [npc.tentacleColour(true)] #IF(npc.isShortStature())impish#ELSEdemonic#ENDIF tentacle, over which [npc.sheHasFull] complete control, allowing [npc.herHim] to use it to grip and hold objects."
				+ "#ELSE"
					+ " [npc.tentacleCount] spaded, [npc.tentacleColour(true)] #IF(npc.isShortStature())impish#ELSEdemonic#ENDIF tentacles, over which [npc.sheHasFull] complete control, allowing [npc.herHim] to use them to grip and hold objects."
				+ "#ENDIF",
			Util.newArrayListOfValues(
					TentacleTypeTag.PREHENSILE,
					TentacleTypeTag.SUTABLE_FOR_PENETRATION,
					TentacleTypeTag.SLEEP_HUGGING,
					TentacleTypeTag.TAPERING_NONE)) {
	};

	private static List<AbstractTentacleType> allTentacleTypes;
	private static Map<AbstractTentacleType, String> tentacleToIdMap = new HashMap<>();
	private static Map<String, AbstractTentacleType> idToTentacleMap = new HashMap<>();
	
	static {
		allTentacleTypes = new ArrayList<>();
		
		// Add in hard-coded tentacle types:
		Field[] fields = TentacleType.class.getFields();
		
		for(Field f : fields){
			if (AbstractTentacleType.class.isAssignableFrom(f.getType())) {
				
				AbstractTentacleType ct;
				try {
					ct = ((AbstractTentacleType) f.get(null));

					tentacleToIdMap.put(ct, f.getName());
					idToTentacleMap.put(f.getName(), ct);
					
					allTentacleTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static AbstractTentacleType getTentacleTypeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToTentacleMap.keySet());
		return idToTentacleMap.get(id);
	}
	
	public static String getIdFromTentacleType(AbstractTentacleType tentacleType) {
		return tentacleToIdMap.get(tentacleType);
	}
	
	public static List<AbstractTentacleType> getAllTentacleTypes() {
		return allTentacleTypes;
	}
	
	private static Map<Race, List<AbstractTentacleType>> typesMap = new HashMap<>();
	
	public static List<AbstractTentacleType> getTentacleTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractTentacleType> types = new ArrayList<>();
		for(AbstractTentacleType type : TentacleType.getAllTentacleTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		if(types.isEmpty()) {
			types.add(TentacleType.NONE);
		}
		typesMap.put(r, types);
		return types;
	}
	
	public static List<AbstractTentacleType> getTentacleTypesSuitableForTransformation(List<AbstractTentacleType> options) {
		if (!options.contains(TentacleType.NONE)) {
			return options;
		}
		
		List<AbstractTentacleType> duplicatedOptions = new ArrayList<>(options);
		duplicatedOptions.remove(TentacleType.NONE);
		return duplicatedOptions;
	}
}
