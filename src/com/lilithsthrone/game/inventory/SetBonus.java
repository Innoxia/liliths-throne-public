package com.lilithsthrone.game.inventory;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * @param id Will be in the format of: 'innoxia_enforcer'.
	 */
	public static AbstractSetBonus getSetBonusFromId(String id) {
		if(id.equals("ENFORCER")) {
			id = "innoxia_enforcer";
		}
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
		
		File dir = new File("res/mods");
		
		if (dir.exists() && dir.isDirectory()) {
			File[] modDirectoryListing = dir.listFiles();
			if (modDirectoryListing != null) {
				for (File modAuthorDirectory : modDirectoryListing) {
					File modAuthorClothingDirectory = new File(modAuthorDirectory.getAbsolutePath()+"/setBonuses");
					File[] innerDirectoryListing = modAuthorClothingDirectory.listFiles((path, filename) -> filename.endsWith(".xml"));
					if (innerDirectoryListing != null) {
						for (File innerChild : innerDirectoryListing) {
							try {
								String id = modAuthorDirectory.getName()+"_"+innerChild.getName().split("\\.")[0];
								AbstractSetBonus setBonus = new AbstractSetBonus(innerChild, modAuthorDirectory.getName(), true) {};
								allSetBonuses.add(setBonus);
								setBonusToIdMap.put(setBonus, id);
								idToSetBonusMap.put(id, setBonus);
							} catch(Exception ex) {
								System.err.println("Loading modded set bonus failed at 'SetBonus' Code 1. File path: "+innerChild.getAbsolutePath());
							}
						}
					}
				}
			}
		}
		
		// Add in external res set bonuses:
		
		dir = new File("res/setBonuses");
		
		if (dir.exists() && dir.isDirectory()) {
			File[] authorDirectoriesListing = dir.listFiles();
			if (authorDirectoriesListing != null) {
				for (File authorDirectory : authorDirectoriesListing) {
					if (authorDirectory.isDirectory()){
						File[] innerDirectoryListing = authorDirectory.listFiles((path, filename) -> filename.endsWith(".xml"));
						if (innerDirectoryListing != null) {
							for (File innerChild : innerDirectoryListing) {
								try {
									String id = authorDirectory.getName()+"_"+innerChild.getName().split("\\.")[0];
									AbstractSetBonus setBonus = new AbstractSetBonus(innerChild, authorDirectory.getName(), true) {};
									allSetBonuses.add(setBonus);
									setBonusToIdMap.put(setBonus, id);
									idToSetBonusMap.put(id, setBonus);
								} catch(Exception ex) {
									System.err.println("Loading modded set bonus failed at 'SetBonus' Code 2. File path: "+innerChild.getAbsolutePath());
								}
							}
						}
					}
				}
			}
		}
		
		
		Field[] fields = SetBonus.class.getFields();
		
		for(Field f : fields){
			if (AbstractSetBonus.class.isAssignableFrom(f.getType())) {
				
				AbstractSetBonus setBonus;
				
				try {
					setBonus = ((AbstractSetBonus) f.get(null));

					setBonusToIdMap.put(setBonus, f.getName());
					idToSetBonusMap.put(f.getName(), setBonus);
					allSetBonuses.add(setBonus);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static List<AbstractSetBonus> getAllSetBonuses() {
		return allSetBonuses;
	}

}
