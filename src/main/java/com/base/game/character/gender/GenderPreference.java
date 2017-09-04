package com.base.game.character.gender;

import com.base.main.Main;
import com.base.utils.Util;

/**
 * @since 0.1.78
 * @version 0.1.82
 * @author Innoxia
 */
public enum GenderPreference {

	NONE(0),
	LOW(1),
	NORMAL(5),
	HIGH(10);
	
	private int value;
	private GenderPreference(int value) {
		this.value=value;
	}
	
	public static Gender getGenderFromUserPreferences() {
		int total=0;
		for(Gender g : Gender.values()) {
			total+=Main.getProperties().genderPreferencesMap.get(g);
		}
		
		int random = Util.random.nextInt(total)+1;
		
		int newTotal=0;
		for(Gender g : Gender.values()) {
			newTotal+=Main.getProperties().genderPreferencesMap.get(g);
			if(random<=newTotal) {
				return g;
			}
		}
		
		return Gender.FEMALE;
	}
	
	public int getValue() {
		return value;
	}
}
