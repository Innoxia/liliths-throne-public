package com.lilithsthrone.game.settings;

import java.util.HashMap;
import java.util.Map;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.78
 * @version 0.2.11
 * @author Innoxia
 */

public enum ContentPreferenceValue {

	ZERO_NONE("off", 0),
	
	ONE_MINIMAL("minimal", 1),
	
	TWO_LOW("low", 5),
	
	THREE_AVERAGE("average", 10),
	
	FOUR_HIGH("high", 20),
	
	FIVE_ABUNDANT("abundant", 40);

	private String name;
	private int value;
	
	private ContentPreferenceValue(String name, int value) {
		this.name= name;
		this.value=value;
	}
	
	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	public static <U> U getRandomValueFromPreferences (Map<U, Integer> preferences, Map<U, Integer> weights, U defaultValue) {
		HashMap<U, Integer> weightedPreferenceMap = new HashMap<>();
		preferences.forEach((preference, value) -> {
			if(weights.containsKey(preference)) weightedPreferenceMap.put(preference, value * weights.get(preference));
		}); 
		
		U preference = Util.getRandomObjectFromWeightedMap(weightedPreferenceMap);
		
		if(preference != null) {
			return preference;
		}
		return defaultValue;
	}
}
