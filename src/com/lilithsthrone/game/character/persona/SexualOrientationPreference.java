package com.lilithsthrone.game.character.persona;

import java.util.HashMap;
import java.util.Map;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @author GalacticOtter, Innoxia
 * @since 0.2.12
 * @version 0.2.12
 */
public enum SexualOrientationPreference {

	ZERO_NONE("off", 0),
	ONE_MINIMAL("minimal", 1),
	TWO_LOW("low", 5),
	THREE_AVERAGE("average", 10),
	FOUR_HIGH("high", 20),
	FIVE_ABUNDANT("abundant", 40);

	private String name;
	private int value;
	
	private SexualOrientationPreference(String name, int value) {
		this.name= name;
		this.value=value;
	}
	
	public static SexualOrientation getSexualOrientationFromUserPreferences(int gynephilicWeight, int ambiphilicWeight, int androphilicWeight) {
		Map<SexualOrientation, Integer> orientationPreferencesMap = new HashMap<>();
		orientationPreferencesMap.put(SexualOrientation.GYNEPHILIC, Main.getProperties().orientationPreferencesMap.get(SexualOrientation.GYNEPHILIC) * gynephilicWeight);
		orientationPreferencesMap.put(SexualOrientation.AMBIPHILIC, Main.getProperties().orientationPreferencesMap.get(SexualOrientation.AMBIPHILIC) * ambiphilicWeight);
		orientationPreferencesMap.put(SexualOrientation.ANDROPHILIC, Main.getProperties().orientationPreferencesMap.get(SexualOrientation.ANDROPHILIC) * androphilicWeight);
		
//		System.out.println(Main.getProperties().orientationPreferencesMap.get(SexualOrientation.GYNEPHILIC));
//		System.out.println(Main.getProperties().orientationPreferencesMap.get(SexualOrientation.AMBIPHILIC));
//		System.out.println(Main.getProperties().orientationPreferencesMap.get(SexualOrientation.ANDROPHILIC));
		
		SexualOrientation orientation = Util.getRandomObjectFromWeightedMap(orientationPreferencesMap);
		
//		System.out.println("set to "+orientation);
		
		if(orientation!=null) {
			return orientation;
		}
		return SexualOrientation.AMBIPHILIC;
	}
	
	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
