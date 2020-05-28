package com.lilithsthrone.utils.colours;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.4
 * @version 0.3.2
 * @author Innoxia
 */
@SuppressWarnings("unchecked")
public class ColourListPresets {

	public static ArrayList<Colour> NONE = new ArrayList<>();
	
	public static ArrayList<Colour> JUST_WHITE = Util.newArrayListOfValues(
			PresetColour.CLOTHING_WHITE);
	
	public static ArrayList<Colour> JUST_BLACK = Util.newArrayListOfValues(
			PresetColour.CLOTHING_BLACK);

	public static ArrayList<Colour> JUST_DARK_RED = Util.newArrayListOfValues(
			PresetColour.CLOTHING_RED_DARK);
	
	public static ArrayList<Colour> JUST_RED = Util.newArrayListOfValues(
			PresetColour.CLOTHING_RED);
	
	public static ArrayList<Colour> JUST_TAN = Util.newArrayListOfValues(
			PresetColour.CLOTHING_TAN);
	
	public static ArrayList<Colour> JUST_BROWN = Util.newArrayListOfValues(
			PresetColour.CLOTHING_BROWN);

	public static ArrayList<Colour> JUST_DARK_BROWN = Util.newArrayListOfValues(
			PresetColour.CLOTHING_BROWN_DARK);
	
	public static ArrayList<Colour> JUST_ORANGE = Util.newArrayListOfValues(
			PresetColour.CLOTHING_ORANGE);
	
	public static ArrayList<Colour> JUST_YELLOW = Util.newArrayListOfValues(
			PresetColour.CLOTHING_YELLOW);
	
	public static ArrayList<Colour> JUST_PINK = Util.newArrayListOfValues(
			PresetColour.CLOTHING_PINK);
	
	public static ArrayList<Colour> JUST_GREY = Util.newArrayListOfValues(
			PresetColour.CLOTHING_GREY);
	
	public static ArrayList<Colour> JUST_GOLD = Util.newArrayListOfValues(
			PresetColour.CLOTHING_GOLD);
	
	public static ArrayList<Colour> JUST_ROSE_GOLD = Util.newArrayListOfValues(
			PresetColour.CLOTHING_ROSE_GOLD);
	
	public static ArrayList<Colour> JUST_STEEL = Util.newArrayListOfValues(
			PresetColour.CLOTHING_STEEL);

	public static ArrayList<Colour> JUST_SILVER = Util.newArrayListOfValues(
			PresetColour.CLOTHING_SILVER);

	public static ArrayList<Colour> JUST_COPPER = Util.newArrayListOfValues(
			PresetColour.CLOTHING_COPPER);
	
	public static ArrayList<Colour> BLACK_OR_WHITE = Util.newArrayListOfValues(
			PresetColour.CLOTHING_BLACK,
			PresetColour.CLOTHING_WHITE);

	public static ArrayList<Colour> SHADES_OF_GREY = Util.newArrayListOfValues(
			PresetColour.CLOTHING_BLACK_JET,
			PresetColour.CLOTHING_BLACK,
			PresetColour.CLOTHING_GREY_DARK,
			PresetColour.CLOTHING_GREY,
			PresetColour.CLOTHING_WHITE);
	
	public static ArrayList<Colour> DARK_SHADES = Util.newArrayListOfValues(
			PresetColour.CLOTHING_GREY,
			PresetColour.CLOTHING_GREY_DARK,
			PresetColour.CLOTHING_BLACK,
			PresetColour.CLOTHING_BLACK_JET,
			PresetColour.CLOTHING_BROWN_VERY_DARK,
			PresetColour.CLOTHING_RED_VERY_DARK,
			PresetColour.CLOTHING_GREEN_VERY_DARK,
			PresetColour.CLOTHING_BLUE_VERY_DARK);
	
	public static ArrayList<Colour> DENIM = Util.newArrayListOfValues(
			PresetColour.CLOTHING_BLUE_GREY,
			PresetColour.CLOTHING_BLUE_NAVY,
			PresetColour.CLOTHING_PINK_LIGHT,
			PresetColour.CLOTHING_DESATURATED_BROWN,
			PresetColour.CLOTHING_WHITE,
			PresetColour.CLOTHING_GREY,
			PresetColour.CLOTHING_BLACK);
	
	public static ArrayList<Colour> KIMONO = Util.newArrayListOfValues(
			PresetColour.CLOTHING_BLUE_LIGHT,
			PresetColour.CLOTHING_PINK_LIGHT,
			PresetColour.CLOTHING_PINK,
			PresetColour.CLOTHING_PURPLE,
			PresetColour.CLOTHING_PURPLE_LIGHT,
			PresetColour.CLOTHING_RED,
			PresetColour.CLOTHING_TURQUOISE,
			PresetColour.CLOTHING_WHITE,
			PresetColour.CLOTHING_YELLOW);
	
	public static ArrayList<Colour> MAID = Util.newArrayListOfValues(
			PresetColour.CLOTHING_PINK_LIGHT,
			PresetColour.CLOTHING_PINK,
			PresetColour.CLOTHING_BLACK);
	
	public static ArrayList<Colour> MILK_MAID = Util.newArrayListOfValues(
			PresetColour.CLOTHING_PINK_LIGHT,
			PresetColour.CLOTHING_BLUE_LIGHT,
			PresetColour.CLOTHING_PURPLE_LIGHT,
			PresetColour.CLOTHING_GREEN,
			PresetColour.CLOTHING_TAN,
			PresetColour.CLOTHING_KHAKI,
			PresetColour.CLOTHING_BROWN,
			PresetColour.CLOTHING_BLACK);
	
	public static ArrayList<Colour> LEATHER = Util.newArrayListOfValues(
			PresetColour.CLOTHING_WHITE,
			PresetColour.CLOTHING_BLACK,
			PresetColour.CLOTHING_BLACK_JET,
			PresetColour.CLOTHING_GREY,
			PresetColour.CLOTHING_DESATURATED_BROWN,
			PresetColour.CLOTHING_BROWN,
			PresetColour.CLOTHING_BROWN_DARK,
			PresetColour.CLOTHING_BROWN_VERY_DARK,
			PresetColour.CLOTHING_TAN,
			PresetColour.CLOTHING_KHAKI);
	
	public static ArrayList<Colour> LINGERIE = Util.newArrayListOfValues(
			PresetColour.CLOTHING_WHITE,
			PresetColour.CLOTHING_GREY_LIGHT,
			PresetColour.CLOTHING_GREY,
			PresetColour.CLOTHING_GREY_DARK,
			PresetColour.CLOTHING_BLACK,
			PresetColour.CLOTHING_BLACK_JET,
			PresetColour.CLOTHING_RED_VERY_DARK,
			PresetColour.CLOTHING_RED_BURGUNDY,
			PresetColour.CLOTHING_RED_DARK,
			PresetColour.CLOTHING_RED,
			PresetColour.CLOTHING_RED_BRIGHT,
			PresetColour.CLOTHING_ORANGE,
			PresetColour.CLOTHING_ORANGE_BRIGHT,
			PresetColour.CLOTHING_ORANGE_DARK,
			PresetColour.CLOTHING_BROWN,
			PresetColour.CLOTHING_BROWN_DARK,
			PresetColour.CLOTHING_BROWN_VERY_DARK,
			PresetColour.CLOTHING_TAN,
			PresetColour.CLOTHING_KHAKI,
			PresetColour.CLOTHING_OLIVE,
			PresetColour.CLOTHING_YELLOW,
			PresetColour.CLOTHING_YELLOW_DARK,
			PresetColour.CLOTHING_GREEN_LIME,
			PresetColour.CLOTHING_GREEN,
			PresetColour.CLOTHING_GREEN_DRAB,
			PresetColour.CLOTHING_GREEN_DARK,
			PresetColour.CLOTHING_GREEN_VERY_DARK,
			PresetColour.CLOTHING_TURQUOISE,
			PresetColour.CLOTHING_BLUE_LIGHT,
			PresetColour.CLOTHING_BLUE,
			PresetColour.CLOTHING_BLUE_GREY,
			PresetColour.CLOTHING_BLUE_NAVY,
			PresetColour.CLOTHING_BLUE_DARK,
			PresetColour.CLOTHING_BLUE_VERY_DARK,
			PresetColour.CLOTHING_PURPLE_VERY_DARK,
			PresetColour.CLOTHING_PURPLE_ROYAL,
			PresetColour.CLOTHING_PURPLE_DARK,
			PresetColour.CLOTHING_PURPLE,
			PresetColour.CLOTHING_PURPLE_LIGHT,
			PresetColour.CLOTHING_PERIWINKLE,
			PresetColour.CLOTHING_PINK_LIGHT,
			PresetColour.CLOTHING_PINK,
			PresetColour.CLOTHING_PINK_HOT,
			PresetColour.CLOTHING_PINK_DARK);
	
	public static ArrayList<Colour> ALL_METAL = Util.newArrayListOfValues(
			PresetColour.CLOTHING_BLACK_STEEL,
			PresetColour.CLOTHING_GUNMETAL,
			PresetColour.CLOTHING_STEEL,
			PresetColour.CLOTHING_IRON,
			PresetColour.CLOTHING_BRASS,
			PresetColour.CLOTHING_COPPER,
			PresetColour.CLOTHING_BRONZE,
			PresetColour.CLOTHING_SILVER,
			PresetColour.CLOTHING_ROSE_GOLD,
			PresetColour.CLOTHING_GOLD,
			PresetColour.CLOTHING_PLATINUM);
			
	
	public static ArrayList<Colour> ALL = Util.newArrayListOfValues(
			PresetColour.CLOTHING_WHITE,
			PresetColour.CLOTHING_GREY_LIGHT,
			PresetColour.CLOTHING_GREY,
			PresetColour.CLOTHING_GREY_DARK,
			PresetColour.CLOTHING_BLACK,
			PresetColour.CLOTHING_BLACK_JET,
			PresetColour.CLOTHING_RED_VERY_DARK,
			PresetColour.CLOTHING_RED_BURGUNDY,
			PresetColour.CLOTHING_RED_DARK,
			PresetColour.CLOTHING_RED,
			PresetColour.CLOTHING_RED_BRIGHT,
			PresetColour.CLOTHING_ORANGE,
			PresetColour.CLOTHING_ORANGE_BRIGHT,
			PresetColour.CLOTHING_ORANGE_DARK,
			PresetColour.CLOTHING_DESATURATED_BROWN,
			PresetColour.CLOTHING_BROWN,
			PresetColour.CLOTHING_BROWN_DARK,
			PresetColour.CLOTHING_BROWN_VERY_DARK,
			PresetColour.CLOTHING_TAN,
			PresetColour.CLOTHING_KHAKI,
			PresetColour.CLOTHING_OLIVE,
			PresetColour.CLOTHING_YELLOW,
			PresetColour.CLOTHING_YELLOW_DARK,
			PresetColour.CLOTHING_GREEN_LIME,
			PresetColour.CLOTHING_GREEN,
			PresetColour.CLOTHING_GREEN_DRAB,
			PresetColour.CLOTHING_GREEN_DARK,
			PresetColour.CLOTHING_GREEN_VERY_DARK,
			PresetColour.CLOTHING_TURQUOISE,
			PresetColour.CLOTHING_BLUE_LIGHT,
			PresetColour.CLOTHING_BLUE,
			PresetColour.CLOTHING_BLUE_GREY,
			PresetColour.CLOTHING_BLUE_NAVY,
			PresetColour.CLOTHING_BLUE_DARK,
			PresetColour.CLOTHING_BLUE_VERY_DARK,
			PresetColour.CLOTHING_PURPLE_VERY_DARK,
			PresetColour.CLOTHING_PURPLE_ROYAL,
			PresetColour.CLOTHING_PURPLE_DARK,
			PresetColour.CLOTHING_PURPLE,
			PresetColour.CLOTHING_PURPLE_LIGHT,
			PresetColour.CLOTHING_PERIWINKLE,
			PresetColour.CLOTHING_PINK_LIGHT,
			PresetColour.CLOTHING_PINK,
			PresetColour.CLOTHING_PINK_HOT,
			PresetColour.CLOTHING_PINK_DARK);
	
	public static ArrayList<Colour> ALL_WITH_METALS = Util.newArrayListOfValues(
			PresetColour.CLOTHING_BLACK_STEEL,
			PresetColour.CLOTHING_GUNMETAL,
			PresetColour.CLOTHING_STEEL,
			PresetColour.CLOTHING_IRON,
			PresetColour.CLOTHING_BRASS,
			PresetColour.CLOTHING_COPPER,
			PresetColour.CLOTHING_BRONZE,
			PresetColour.CLOTHING_SILVER,
			PresetColour.CLOTHING_ROSE_GOLD,
			PresetColour.CLOTHING_GOLD,
			PresetColour.CLOTHING_PLATINUM,
			
			PresetColour.CLOTHING_WHITE,
			PresetColour.CLOTHING_GREY_LIGHT,
			PresetColour.CLOTHING_GREY,
			PresetColour.CLOTHING_GREY_DARK,
			PresetColour.CLOTHING_BLACK,
			PresetColour.CLOTHING_BLACK_JET,
			PresetColour.CLOTHING_RED_VERY_DARK,
			PresetColour.CLOTHING_RED_BURGUNDY,
			PresetColour.CLOTHING_RED_DARK,
			PresetColour.CLOTHING_RED,
			PresetColour.CLOTHING_RED_BRIGHT,
			PresetColour.CLOTHING_ORANGE,
			PresetColour.CLOTHING_ORANGE_BRIGHT,
			PresetColour.CLOTHING_ORANGE_DARK,
			PresetColour.CLOTHING_DESATURATED_BROWN,
			PresetColour.CLOTHING_BROWN,
			PresetColour.CLOTHING_BROWN_DARK,
			PresetColour.CLOTHING_BROWN_VERY_DARK,
			PresetColour.CLOTHING_TAN,
			PresetColour.CLOTHING_KHAKI,
			PresetColour.CLOTHING_OLIVE,
			PresetColour.CLOTHING_YELLOW_DARK,
			PresetColour.CLOTHING_YELLOW,
			PresetColour.CLOTHING_GREEN_LIME,
			PresetColour.CLOTHING_GREEN,
			PresetColour.CLOTHING_GREEN_DRAB,
			PresetColour.CLOTHING_GREEN_DARK,
			PresetColour.CLOTHING_GREEN_VERY_DARK,
			PresetColour.CLOTHING_TURQUOISE,
			PresetColour.CLOTHING_BLUE_LIGHT,
			PresetColour.CLOTHING_BLUE,
			PresetColour.CLOTHING_BLUE_GREY,
			PresetColour.CLOTHING_BLUE_NAVY,
			PresetColour.CLOTHING_BLUE_DARK,
			PresetColour.CLOTHING_BLUE_VERY_DARK,
			PresetColour.CLOTHING_PURPLE_VERY_DARK,
			PresetColour.CLOTHING_PURPLE_ROYAL,
			PresetColour.CLOTHING_PURPLE_DARK,
			PresetColour.CLOTHING_PURPLE,
			PresetColour.CLOTHING_PURPLE_LIGHT,
			PresetColour.CLOTHING_PERIWINKLE,
			PresetColour.CLOTHING_PINK_LIGHT,
			PresetColour.CLOTHING_PINK,
			PresetColour.CLOTHING_PINK_HOT,
			PresetColour.CLOTHING_PINK_DARK);
	
	public static ArrayList<Colour> NOT_WHITE = new ArrayList<>(ALL);
	
	public static ArrayList<Colour> NOT_BLACK = new ArrayList<>(ALL);
	
	static {
		NOT_WHITE.remove(PresetColour.CLOTHING_WHITE);
		NOT_BLACK.remove(PresetColour.CLOTHING_BLACK);
		NOT_BLACK.remove(PresetColour.CLOTHING_BLACK_JET);
	}

	private static Map<String, ArrayList<Colour>> idToColourListMap = new HashMap<>();
	
	public static Map<String, ArrayList<Colour>> getIdToColourListMap() {
		return idToColourListMap;
	}

	public static ArrayList<Colour> getColourListFromId(String id) {
		// Fix inconsistent naming:
		if(id.equals("ALL_METALS")) {
			id = "ALL_METAL";
			
		} else if(id.equals("ALL_WITH_METAL")) {
			id = "ALL_WITH_METALS";
		}
		
		id = Util.getClosestStringMatch(id, idToColourListMap.keySet());
		return idToColourListMap.get(id);
	}
	
	static {
		Field[] fields = ColourListPresets.class.getFields();
	
		for(Field f : fields){
			
			if (ArrayList.class.isAssignableFrom(f.getType())) {
				
				ArrayList<Colour> ct;
				try {
					ct = ((ArrayList<Colour>) f.get(null));
	
					idToColourListMap.put(f.getName(), ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
}
