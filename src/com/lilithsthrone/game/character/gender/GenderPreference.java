package com.lilithsthrone.game.character.gender;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.78
 * @version 0.1.86
 * @author Innoxia
 */
public enum GenderPreference {

	ZERO_NONE("off", 0),
	ONE_MINIMAL("minimal", 1),
	TWO_LOW("low", 5),
	THREE_AVERAGE("average", 10),
	FOUR_HIGH("high", 20),
	FIVE_ABUNDANT("abundant", 40);

	private String name;
	private int value;
	
	private GenderPreference(String name, int value) {
		this.name= name;
		this.value=value;
	}
	
	public static Gender getGenderFromUserPreferences() {
		int total=0;
		for(Gender g : Gender.values()) {
			total+=Main.getProperties().genderPreferencesMap.get(g);
		}
		
		if(total == 0) {
			if(Math.random()>0.5f) {
				return Gender.F_V_B_FEMALE;
			} else {
				return Gender.M_P_MALE;
			}
		}
		
		int random = Util.random.nextInt(total)+1;
		
		int newTotal=0;
		for(Gender g : Gender.values()) {
			newTotal+=Main.getProperties().genderPreferencesMap.get(g);
			if(random<=newTotal) {
				return g;
			}
		}
		
		return Gender.F_V_B_FEMALE;
	}
	
	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
