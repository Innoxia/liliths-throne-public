package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 0.4.6.6
 * @version 0.4.6.6
 * @author Stadler76
 */
public enum Affinity {

	/** Loves water. Hates not being near water. */
	AQUATIC,

	/** Neutral affinity. Indifferent to being near water or not. The default for most races including humans. */
	AMPHIBIOUS,

	/** Hates water. The opposite of AQUATIC. */
	TERRESTRIAL;

	public static final List<Affinity> allAffinities = new ArrayList<>();
	public static final Map<Affinity, String> affinityToIdMap = new HashMap<>();
	public static final Map<String, Affinity> idToAffinityMap = new HashMap<>();

	static {
		for(Affinity a : Affinity.values()) {
			allAffinities.add(a);
			affinityToIdMap.put(a, a.toString());
			idToAffinityMap.put(a.toString(), a);
		}
	}

	public static List<Affinity> getAllAffinities() {
		return allAffinities;
	}

	public static Affinity getAffinityFromId(String id) {
		id = Util.getClosestStringMatch(id, idToAffinityMap.keySet());
		return idToAffinityMap.get(id);
	}

	public static String getIdFromAffinity(Affinity a) {
		return affinityToIdMap.get(a);
	}
}
