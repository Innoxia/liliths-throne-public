package com.lilithsthrone.game.character.gender;

import java.util.HashMap;
import java.util.Map;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.78
 * @version 0.2.9
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
	
	public static Gender getGenderFromUserPreferences(boolean requiresVagina, boolean requiresPenis) {
		Map<Gender, Integer> genderMap = new HashMap<>();
		
		for(Gender g : Gender.values()) {
			if((!requiresVagina || g.getGenderName().isHasVagina())
					&& (!requiresPenis || g.getGenderName().isHasPenis())
					&& Main.getProperties().genderPreferencesMap.get(g)>0) {
				genderMap.put(g, Main.getProperties().genderPreferencesMap.get(g));
			}
		}
		
		if(genderMap.isEmpty()) {
			if(Math.random()>0.5f || requiresVagina) {
				if(requiresVagina && requiresPenis) {
					return Gender.F_P_V_B_FUTANARI;
					
				} else if(requiresPenis) {
					return Gender.F_P_B_SHEMALE;
				}
				return Gender.F_V_B_FEMALE;
				
			} else {
				return Gender.M_P_MALE;
			}
		}
		
		return Util.getRandomObjectFromWeightedMap(genderMap);
	}
	
	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
