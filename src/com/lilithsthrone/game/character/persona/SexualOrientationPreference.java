package com.lilithsthrone.game.character.persona;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

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
		int gyne = Main.getProperties().orientationPreferencesMap.get(SexualOrientation.GYNEPHILIC) * gynephilicWeight;
		int ambi = Main.getProperties().orientationPreferencesMap.get(SexualOrientation.AMBIPHILIC) * ambiphilicWeight;
		int andro = Main.getProperties().orientationPreferencesMap.get(SexualOrientation.ANDROPHILIC) * androphilicWeight;
		int total = gyne + ambi + andro;

		if(total == 0) {
			return SexualOrientation.AMBIPHILIC;
		}
		
		int random = Util.random.nextInt(total)+1;
		
		int newTotal = 0;
		
		newTotal += gyne;
		if(random <= newTotal) {
			return SexualOrientation.GYNEPHILIC;
		}

		newTotal += ambi;
		if(random <= newTotal) {
			return SexualOrientation.AMBIPHILIC;
		}

		newTotal += andro;
		if(random <= newTotal) {
			return SexualOrientation.ANDROPHILIC;
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
