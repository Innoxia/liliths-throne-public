package com.lilithsthrone.game.inventory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.8.2
 * @author Innoxia
 */
public class SetBonus {
	
	public static List<AbstractSetBonus> allSetBonuses;
	
	public static Map<AbstractSetBonus, String> setBonusToIdMap = new HashMap<>();
	public static Map<String, AbstractSetBonus> idToSetBonusMap = new HashMap<>();
	
	/**
	 * @param id Will be in the format of: 'innoxia_maid'.
	 */
	public static AbstractSetBonus getSetBonusFromId(String id) {
		if(id.equals("SLUTTY_ENFORCER")) {
			id = "innoxia_slutty_enforcer";
		}
		if(id.equals("MAID")) {
			id = "innoxia_maid";
		}
		if(id.equals("BUTLER")) {
			id = "innoxia_butler";
		}
		if(id.equals("WITCH")) {
			id = "innoxia_witch";
		}
		if(id.equals("SCIENTIST")) {
			id = "innoxia_scientist";
		}
		if(id.equals("MILK_MAID")) {
			id = "innoxia_milk_maid";
		}
		if(id.equals("BDSM")) {
			id = "innoxia_bdsm";
		}
		if(id.equals("CATTLE")) {
			id = "innoxia_cattle";
		}
		if(id.equals("GEISHA")) {
			id = "innoxia_geisha";
		}
		if(id.equals("RONIN")) {
			id = "innoxia_ronin";
		}
		if(id.equals("WEAPON_DAISHO")) {
			id = "innoxia_daisho";
		}
		if(id.equals("JOLNIR")) {
			id = "innoxia_jolnir";
		}
		if(id.equals("SUN")) {
			id = "innoxia_sun";
		}
		if(id.equals("SNOWFLAKE")) {
			id = "innoxia_snowflake";
		}
		if(id.equals("RAINBOW")) {
			id = "innoxia_rainbow";
		}
		if(id.equals("DARK_SIREN")) {
			id = "innoxia_dark_siren";
		}
		if(id.equals("LYSSIETH_GUARD")) {
			id = "innoxia_lyssieth_guard";
		}
		
		id = Util.getClosestStringMatch(id, idToSetBonusMap.keySet());
		return idToSetBonusMap.get(id);
	}
	
	public static String getIdFromSetBonus(AbstractSetBonus setBonus) {
		return setBonusToIdMap.get(setBonus);
	}

	static {
		
		allSetBonuses = new ArrayList<>();
		
		// Modded set bonus types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/setBonuses");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String id = innerEntry.getKey();
					AbstractSetBonus setBonus = new AbstractSetBonus(innerEntry.getValue(), entry.getKey(), true) {};
					allSetBonuses.add(setBonus);
					setBonusToIdMap.put(setBonus, id);
					idToSetBonusMap.put(id, setBonus);
				} catch(Exception ex) {
					System.err.println("Loading modded set bonus failed at 'SetBonusType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res outfit types:

		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/setBonuses");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String id = innerEntry.getKey();
					AbstractSetBonus setBonus = new AbstractSetBonus(innerEntry.getValue(), entry.getKey(), false) {};
					allSetBonuses.add(setBonus);
					setBonusToIdMap.put(setBonus, id);
					idToSetBonusMap.put(id, setBonus);
//					System.out.println("SBT: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading set bonus failed at 'SetBonusType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	public static List<AbstractSetBonus> getAllSetBonuses() {
		return allSetBonuses;
	}

}
