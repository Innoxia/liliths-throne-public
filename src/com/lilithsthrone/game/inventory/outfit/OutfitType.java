package com.lilithsthrone.game.inventory.outfit;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		id = Util.getClosestStringMatchUnordered(id, idToOutfitMap.keySet());
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
		
		// Modded outfit types:

		moddedOutfits = new ArrayList<>();
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/outfits");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractOutfit ct = new AbstractOutfit(innerEntry.getValue()) {};
					moddedOutfits.add(ct);
					String id = innerEntry.getKey();
					outfitsToIdMap.put(ct, id);
					idToOutfitMap.put(id, ct);
				} catch(Exception ex) {
					System.err.println("Loading modded outfit failed at 'OutfitType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}

		allOutfits.addAll(moddedOutfits);
		
		// External res outfit types:

		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/outfits");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractOutfit ct = new AbstractOutfit(innerEntry.getValue()) {};
					allOutfits.add(ct);
					String id = innerEntry.getKey();
					outfitsToIdMap.put(ct, id);
					idToOutfitMap.put(id, ct);
					System.out.println("OT: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading outfit failed at 'OutfitType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
	}
	
}
