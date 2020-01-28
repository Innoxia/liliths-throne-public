package com.lilithsthrone.game.character;
import java.util.Arrays;
import java.util.List;

/**
 * @since 0.3.2
 * @version 0.3.2
 * @author Innoxia
 */
public enum EquipClothingSetting {
	
	ADD_WEAPONS,
	
	ADD_TATTOOS,
	
	ADD_ACCESSORIES,
	
	REPLACE_CLOTHING,
	
	REMOVE_SEALS;
	
	public static List<EquipClothingSetting> getAllClothingSettings() {
		return Arrays.asList(EquipClothingSetting.values());
	}
}
