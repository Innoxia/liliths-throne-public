package com.lilithsthrone.game.character.markings;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.utils.ColourListPresets;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.6
 * @version 0.2.8
 * @author Innoxia
 */
public class TattooType {

	public static AbstractTattooType NONE = new AbstractTattooType(
			"none",
			"none",
			"This tattoo has no particular pattern, and simply displays either writing or a counter.",
			ColourListPresets.JUST_GREY.getPresetColourList(),
			null,
			null,
			null);
	
	public static AbstractTattooType FLOWERS = new AbstractTattooType(
			"flowers",
			"flowers",
			"This tattoo depicts a flowing series of intertwined flowers.",
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null);

	public static AbstractTattooType TRIBAL = new AbstractTattooType(
			"tribal",
			"tribal",
			"A series of flowing lines and intricate patterns.",
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null);

	public static AbstractTattooType BUTTERFLIES = new AbstractTattooType(
			"butterflies",
			"butterflies",
			"An artistic depiction of a trio of butterflies in mid-flight.",
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null);
	
	public static AbstractTattooType LINES = new AbstractTattooType(
			"lines",
			"lines",
			"A series of flowing, swirling lines.",
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null);
	
	public static Map<AbstractCoreType, String> tattooToIdMap = new HashMap<>();
	public static Map<String, AbstractCoreType> idToTattooMap = new HashMap<>();
	
	public static AbstractTattooType getTattooTypeFromId(String id) {
//		System.out.print("ID: "+id);
		id = Util.getClosestStringMatch(id, idToTattooMap.keySet());
//		System.out.println("  set to: "+id);
		return (AbstractTattooType) idToTattooMap.get(id);
	}
	
	public static String getIdFromTattooType(AbstractTattooType tattooType) {
		return tattooToIdMap.get(tattooType);
	}
	
	public static List<AbstractTattooType> getAllTattooTypes() {
		return tattooToIdMap.keySet().stream()
				.map(abstractCoreType -> (AbstractTattooType) abstractCoreType)
				.sorted((t1, t2) -> t1.equals(TattooType.NONE) ? -1 : (t1.getName().compareTo(t2.getName())))
				.collect(Collectors.toList());
	}
	
	static {
		
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
