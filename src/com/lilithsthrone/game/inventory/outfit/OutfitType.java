package com.lilithsthrone.game.inventory.outfit;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.controller.xmlParsing.XMLLoadException;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.1
 * @version 0.3.5
 * @author Innoxia
 */
public enum OutfitType {

	/** To be used for characters that attack the player. Might be used for other similar criminal activities. */
	MUGGER,

	/** To be used for characters who sell their bodies. Might be used for other similar activities, such as strippers. */
	PROSTITUTE,

	/** To be used for characters in a casual setting. Either at home, going out, or non-uniformed casual work. */
	CASUAL,

	/** To be used for the character's smart clothing at their job. */
	JOB_SMART,

	/** To be used when performing manual labour or other such work. */
	JOB_LABOUR,

	/** To be used when going clubbing. */
	CLUBBING,
	
	/** To be used on casual dates, such as to a restaurant. */
	CASUAL_DATE,
	
	/** To be used on formal occasions, such as fancy balls or cocktail parties. */
	FORMAL_DATE,
	
	/** Sportswear for exercise. */
	ATHLETIC,
	
	/** Clothing to be worn to bed. */
	NIGHTWEAR,

	/** Sexy clothing to be worn to bed. */
	NIGHTWEAR_SEXY;
	
	

	private static List<AbstractOutfit> allOutfits;
	private static List<AbstractOutfit> moddedOutfits;
	
	private static Map<AbstractOutfit, String> outfitsToIdMap = new HashMap<>();
	private static Map<String, AbstractOutfit> idToOutfitMap = new HashMap<>();
	
	public static AbstractOutfit getOutfitTypeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToOutfitMap.keySet());
		return idToOutfitMap.get(id);
	}
	
	public static String getIdFromOutfitType(AbstractOutfit outfitsType) {
		return outfitsToIdMap.get(outfitsType);
	}
	
	/**
	 * Used to get all AbstractOutfits in a directory.
	 * 
	 * @param idStart The starting String of the ids you want.
	 * @return A list of all AbstractOutfits which have their id starting with the provided String.
	 */
	public static List<AbstractOutfit> getOutfitsFromIdStart(String idStart) {
		List<AbstractOutfit> returnList = new ArrayList<>();
		for(Entry<String, AbstractOutfit> entry : idToOutfitMap.entrySet()) {
			if(entry.getKey().startsWith(idStart)) {
				returnList.add(entry.getValue());
//				System.out.println("x: "+entry.getKey());
			}
		}
//		System.out.println(returnList.size());
		return returnList;
	}
	
	public static List<AbstractOutfit> getAllOutfits() {
		return allOutfits;
	}

	public static List<AbstractOutfit> getModdedOutfits() {
		return moddedOutfits;
	}

	static {
		
		allOutfits = new ArrayList<>();
		
		// Load in modded outfits:
		moddedOutfits = new ArrayList<>();
		File dir = new File("res/mods");
		
		if (dir.exists() && dir.isDirectory()) {
			File[] modDirectoryListing = dir.listFiles();
			if (modDirectoryListing != null) {
				for (File modAuthorDirectory : modDirectoryListing) {
					File modAuthorOutfitDirectory = new File(modAuthorDirectory.getAbsolutePath()+"/outfits");
					
					File[] outfitsDirectoriesListing = modAuthorOutfitDirectory.listFiles();
					if (outfitsDirectoriesListing != null) {
						for (File outfitsDirectory : outfitsDirectoriesListing) {
							if (outfitsDirectory.isDirectory()){
								File[] innerDirectoryListing = outfitsDirectory.listFiles((path, filename) -> filename.endsWith(".xml"));
								if (innerDirectoryListing != null) {
									for (File innerChild : innerDirectoryListing) {
										try{
											AbstractOutfit ct = new AbstractOutfit(innerChild) {};
											moddedOutfits.add(ct);
											String id = modAuthorDirectory.getName()+"_"+innerChild.getParentFile().getName()+"_"+innerChild.getName().split("\\.")[0];
//											System.out.println(id);
											outfitsToIdMap.put(ct, id);
											idToOutfitMap.put(id, ct);
											
										} catch(XMLLoadException ex){ // we want to catch any errors here; we shouldn't want to load any mods that are invalid as that may cause severe bugs
											System.err.println(ex);
											System.out.println(ex); // temporary, I think mod loading failure should be displayed to player on screen
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		allOutfits.addAll(moddedOutfits);
		
		
		// Add in external res outfits:
		
		dir = new File("res/outfits");

		if (dir.exists() && dir.isDirectory()) {
			File[] modDirectoryListing = dir.listFiles();
			if (modDirectoryListing != null) {
				for (File modAuthorDirectory : modDirectoryListing) {
					File[] outfitsDirectoriesListing = modAuthorDirectory.listFiles();
					if (outfitsDirectoriesListing != null) {
						for (File outfitsDirectory : outfitsDirectoriesListing) {
							if (outfitsDirectory.isDirectory()){
								File[] innerDirectoryListing = outfitsDirectory.listFiles((path, filename) -> filename.endsWith(".xml"));
								if (innerDirectoryListing != null) {
									for (File innerChild : innerDirectoryListing) {
										try{
											AbstractOutfit ct = new AbstractOutfit(innerChild) {};
											allOutfits.add(ct);
											String id = modAuthorDirectory.getName()+"_"+innerChild.getParentFile().getName()+"_"+innerChild.getName().split("\\.")[0];
//											System.out.println(id);
											outfitsToIdMap.put(ct, id);
											idToOutfitMap.put(id, ct);
											
										} catch(XMLLoadException ex){ // we want to catch any errors here; we shouldn't want to load any mods that are invalid as that may cause severe bugs
											System.err.println(ex);
											System.out.println(ex); // temporary, I think mod loading failure should be displayed to player on screen
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
