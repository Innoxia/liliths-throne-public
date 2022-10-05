package com.lilithsthrone.game.character.markings;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.6
 * @version 0.4.4.1
 * @author Innoxia
 */
public class TattooType {

//	public static AbstractTattooType NONE = new AbstractTattooType(
//			"none",
//			"none",
//			"This tattoo has no particular pattern, and simply displays either writing or a counter.",
//			"a tattoo which lacks any kind of image or pattern",
//			ColourListPresets.JUST_GREY,
//			null,
//			null,
//			null);
	
//	public static AbstractTattooType FLOWERS = new AbstractTattooType(
//			"flowers",
//			"flowers",
//			"This tattoo depicts a flowing series of intertwined flowers.",
//			"a floral-themed tattoo, depicting a flowing series of intertwined flowers",
//			ColourListPresets.ALL,
//			ColourListPresets.ALL,
//			ColourListPresets.ALL,
//			null);

//	public static AbstractTattooType TRIBAL = new AbstractTattooType(
//			"tribal",
//			"tribal",
//			"A series of flowing lines and intricate patterns.",
//			"a tattoo which consists of a series of flowing lines and intricate patterns",
//			ColourListPresets.ALL,
//			null,
//			null,
//			null);

//	public static AbstractTattooType BUTTERFLIES = new AbstractTattooType(
//			"butterflies",
//			"butterflies",
//			"An artistic depiction of a trio of butterflies in mid-flight.",
//			"a tattoo of a trio of butterflies in mid-flight",
//			ColourListPresets.ALL,
//			ColourListPresets.ALL,
//			ColourListPresets.ALL,
//			null);
	
//	public static AbstractTattooType LINES = new AbstractTattooType(
//			"lines",
//			"lines",
//			"A series of flowing, swirling lines.",
//			"a tattoo which consists of a series of flowing, swirling lines",
//			ColourListPresets.ALL,
//			null,
//			null,
//			null);
	
	private static Map<AbstractTattooType, String> tattooToIdMap = new HashMap<>();
	private static Map<String, AbstractTattooType> idToTattooMap = new HashMap<>();
	
	public static AbstractTattooType getTattooTypeFromId(String id) {
		if(id.equalsIgnoreCase("NONE")) {
			id = "innoxia_misc_none";
		} else if(id.equalsIgnoreCase("FLOWERS")) {
			id = "innoxia_plant_flowers";
		} else if(id.equalsIgnoreCase("TRIBAL")) {
			id = "innoxia_symbol_tribal";
		} else if(id.equalsIgnoreCase("BUTTERFLIES")) {
			id = "innoxia_animal_butterflies";
		} else if(id.equalsIgnoreCase("LINES")) {
			id = "innoxia_symbol_lines";
		}
		
		id = Util.getClosestStringMatch(id, idToTattooMap.keySet());
		return idToTattooMap.get(id);
	}
	
	public static String getIdFromTattooType(AbstractTattooType tattooType) {
		return tattooToIdMap.get(tattooType);
	}

	/**
	 * @return A list of tattoos which the target has available to them.
	 */
	public static List<AbstractTattooType> getConditionalTattooTypes(GameCharacter target) {
		List<AbstractTattooType> tattoos = getAllTattooTypes();
		tattoos.removeIf(tattoo -> !tattoo.isAvailable(target));
		return tattoos;
	}
	
	public static List<AbstractTattooType> getAllTattooTypes() {
		List<AbstractTattooType> allTattoos = new ArrayList<>(tattooToIdMap.keySet());
		
		allTattoos.sort((t1, t2) -> t1.equals(TattooType.getTattooTypeFromId("innoxia_misc_none"))?-1:(t1.getName().compareTo(t2.getName())));
		
		return allTattoos;
	}
	
	static {
		
		// Modded tattoo types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/items/tattoos");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractTattooType tattoo = new AbstractTattooType(innerEntry.getValue()) {};
					tattooToIdMap.put(tattoo, innerEntry.getKey());
					idToTattooMap.put(innerEntry.getKey(), tattoo);
				} catch(Exception ex) {
					System.err.println("Loading modded tattoo failed at 'TattooType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res tattoo types:

		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/tattoos");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractTattooType tattoo = new AbstractTattooType(innerEntry.getValue()) {};
					tattooToIdMap.put(tattoo, innerEntry.getKey());
					idToTattooMap.put(innerEntry.getKey(), tattoo);
//					System.out.println("TT: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading tattoo failed at 'TattooType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}

		// Hard-coded tattoo types (all those up above):
		
		Field[] fields = TattooType.class.getFields();
		
		for(Field f : fields){
			
			if (AbstractTattooType.class.isAssignableFrom(f.getType())) {
				
				AbstractTattooType ct;
				try {
					ct = ((AbstractTattooType) f.get(null));

					// I feel like this is stupid :thinking:
					tattooToIdMap.put(ct, f.getName());
					idToTattooMap.put(f.getName(), ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
}
