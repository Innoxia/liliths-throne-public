package com.lilithsthrone.game.inventory.weapon;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.84
 * @version 0.3.7.4
 * @author Innoxia
 */
public class WeaponType {
	
	private static List<AbstractWeaponType> allWeapons = new ArrayList<>();
	public static List<AbstractWeaponType> moddedWeapons = new ArrayList<>();
	
	public static Map<AbstractWeaponType, String> weaponToIdMap = new HashMap<>();
	public static Map<String, AbstractWeaponType> idToWeaponMap = new HashMap<>();
	

	public static AbstractWeaponType getWeaponTypeFromId(String id) {
		return getWeaponTypeFromId(id, true);
	}
	
	/**
	 * @param closestMatch Pass in true if you want to get whatever WeaponType has the closest match to the provided id, even if it's not exactly the same.
	 */
	public static AbstractWeaponType getWeaponTypeFromId(String id, boolean closestMatch) {
//		System.out.print("ID: "+id);
		
		if(id.equals("RANGED_MUSKET")) {	
			id = "innoxia_gun_arcane_musket";
		}
		if(id.equals("MAIN_WESTERN_KKP") || id.equals("innoxia_western_kkp_western_kkp")) {	
			id = "innoxia_gun_western_kkp";
		}
		if(id.equals("innoxia_revolver_revolver")) {	
			id = "innoxia_gun_revolver";
		}

		if(id.equals("innoxia_pistolCrossbow")) {
			id = "innoxia_bow_pistol_crossbow";
		}
		
		if(id.equals("MAIN_FEATHER_DUSTER")) {
			id = "innoxia_cleaning_feather_duster";
		}
		if(id.equals("MAIN_WITCH_BROOM")) {
			id = "innoxia_cleaning_witch_broom";
		}
		
		if(id.equals("OFFHAND_BUCKLER") || id.equals("innoxia_buckler_buckler")) {
			id = "innoxia_shield_buckler";
		}
		if(id.equals("innoxia_crudeShield_crude_shield")) {
			id = "innoxia_shield_crude_wooden";
		}
		
		if(id.equals("OFFHAND_BOW_AND_ARROW")) {
			id = "innoxia_bow_shortbow";
		}
		
		if(id.equals("MELEE_KNIGHTLY_SWORD")) {	
			id = "innoxia_europeanSwords_arming_sword";
		}
		if(id.equals("MELEE_ZWEIHANDER")) {
			id = "innoxia_europeanSwords_zweihander";
		}
		
		if(id.equals("OFFHAND_CHAOS_RARE")) {	
			id = "innoxia_feather_rare";
		}
		if(id.equals("OFFHAND_CHAOS_EPIC")) {
			id = "innoxia_feather_epic";
		}

		if(id.equals("MELEE_CHAOS_RARE")) {	
			id = "innoxia_crystal_rare";
		}
		if(id.equals("MELEE_CHAOS_EPIC")) {	
			id = "innoxia_crystal_EPIC";
		}
		if(id.equals("MELEE_CHAOS_LEGENDARY")) {	
			id = "innoxia_crystal_legendary";
		}
		
		if(closestMatch) {
			id = Util.getClosestStringMatch(id, idToWeaponMap.keySet());
		}
		
		return idToWeaponMap.get(id);
	}
	
	public static String getIdFromWeaponType(AbstractWeaponType weaponType) {
		return weaponToIdMap.get(weaponType);
	}

	static {
		// Load in modded weapons:
		moddedWeapons = new ArrayList<>();
		File dir = new File("res/mods");
		
		if (dir.exists() && dir.isDirectory()) {
			File[] modDirectoryListing = dir.listFiles();
			if (modDirectoryListing != null) {
				for (File modAuthorDirectory : modDirectoryListing) {
					File modAuthorClothingDirectory = new File(modAuthorDirectory.getAbsolutePath()+"/items/weapons");
					
					File[] clothingDirectoriesListing = modAuthorClothingDirectory.listFiles();
					if (clothingDirectoriesListing != null) {
						for (File clothingDirectory : clothingDirectoriesListing) {
							if (clothingDirectory.isDirectory()){
								File[] innerDirectoryListing = clothingDirectory.listFiles((path, filename) -> filename.endsWith(".xml"));
								if (innerDirectoryListing != null) {
									for (File innerChild : innerDirectoryListing) {
										try {
											String id = modAuthorDirectory.getName()+"_"+innerChild.getParentFile().getName()+"_"+innerChild.getName().split("\\.")[0];
											AbstractWeaponType ct = new AbstractWeaponType(innerChild, modAuthorDirectory.getName(), true) {};
											moddedWeapons.add(ct);
											weaponToIdMap.put(ct, id);
											idToWeaponMap.put(id, ct);
										} catch(Exception ex) {
											System.err.println("Loading modded weapon failed at 'WeaponType' Code 1. File path: "+innerChild.getAbsolutePath());
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		allWeapons.addAll(moddedWeapons);
		
		
		// Add in external res weapons:
		
		dir = new File("res/weapons");
		
		if (dir.exists() && dir.isDirectory()) {
			File[] authorDirectoriesListing = dir.listFiles();
			if (authorDirectoriesListing != null) {
				for (File authorDirectory : authorDirectoriesListing) {
					if (authorDirectory.isDirectory()){
						for (File clothingDirectory : authorDirectory.listFiles()) {
							if (clothingDirectory.isDirectory()){
								File[] innerDirectoryListing = clothingDirectory.listFiles((path, filename) -> filename.endsWith(".xml"));
								if (innerDirectoryListing != null) {
									for (File innerChild : innerDirectoryListing) {
										try {
											String id = authorDirectory.getName()+"_"+innerChild.getParentFile().getName()+"_"+innerChild.getName().split("\\.")[0];
											AbstractWeaponType ct = new AbstractWeaponType(innerChild, authorDirectory.getName(), false) {};
											allWeapons.add(ct);
											weaponToIdMap.put(ct, id);
											idToWeaponMap.put(id, ct);
										} catch(Exception ex) {
											System.err.println("Loading modded weapon failed at 'WeaponType' Code 2. File path: "+innerChild.getAbsolutePath());
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
//		Field[] fields = WeaponType.class.getFields();
//		
//		for(Field f : fields){
//			if (AbstractWeaponType.class.isAssignableFrom(f.getType())) {
//				
//				AbstractWeaponType weapon;
//				
//				try {
//					weapon = ((AbstractWeaponType) f.get(null));
//
//					// I feel like this is stupid :thinking:
//					weaponToIdMap.put(weapon, f.getName());
//					idToWeaponMap.put(f.getName(), weapon);
//					
//					allWeapons.add(weapon);
//					
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					e.printStackTrace();
//				}
//			}
//		}
	}

	public static List<AbstractWeaponType> getAllWeapons() {
		return allWeapons;
	}
}
