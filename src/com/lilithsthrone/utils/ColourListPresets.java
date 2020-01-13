package com.lilithsthrone.utils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @since 0.2.4
 * @version 0.3.2
 * @author Innoxia
 */
@SuppressWarnings("unchecked")
public class ColourListPresets {

	public static ArrayList<Colour> NONE = new ArrayList<>();
	
	public static ArrayList<Colour> JUST_WHITE = Util.newArrayListOfValues(
			Colour.CLOTHING_WHITE);
	
	public static ArrayList<Colour> JUST_BLACK = Util.newArrayListOfValues(
			Colour.CLOTHING_BLACK);

	public static ArrayList<Colour> JUST_DARK_RED = Util.newArrayListOfValues(
			Colour.CLOTHING_RED_DARK);
	
	public static ArrayList<Colour> JUST_RED = Util.newArrayListOfValues(
			Colour.CLOTHING_RED);
	
	public static ArrayList<Colour> JUST_TAN = Util.newArrayListOfValues(
			Colour.CLOTHING_TAN);
	
	public static ArrayList<Colour> JUST_BROWN = Util.newArrayListOfValues(
			Colour.CLOTHING_BROWN);

	public static ArrayList<Colour> JUST_DARK_BROWN = Util.newArrayListOfValues(
			Colour.CLOTHING_BROWN_DARK);
	
	public static ArrayList<Colour> JUST_ORANGE = Util.newArrayListOfValues(
			Colour.CLOTHING_ORANGE);
	
	public static ArrayList<Colour> JUST_YELLOW = Util.newArrayListOfValues(
			Colour.CLOTHING_YELLOW);
	
	public static ArrayList<Colour> JUST_PINK = Util.newArrayListOfValues(
			Colour.CLOTHING_PINK);
	
	public static ArrayList<Colour> JUST_GREY = Util.newArrayListOfValues(
			Colour.CLOTHING_GREY);
	
	public static ArrayList<Colour> JUST_GOLD = Util.newArrayListOfValues(
			Colour.CLOTHING_GOLD);
	
	public static ArrayList<Colour> JUST_ROSE_GOLD = Util.newArrayListOfValues(
			Colour.CLOTHING_ROSE_GOLD);
	
	public static ArrayList<Colour> JUST_STEEL = Util.newArrayListOfValues(
			Colour.CLOTHING_STEEL);

	public static ArrayList<Colour> JUST_SILVER = Util.newArrayListOfValues(
			Colour.CLOTHING_SILVER);

	public static ArrayList<Colour> JUST_COPPER = Util.newArrayListOfValues(
			Colour.CLOTHING_COPPER);
	
	public static ArrayList<Colour> BLACK_OR_WHITE = Util.newArrayListOfValues(
			Colour.CLOTHING_BLACK,
			Colour.CLOTHING_WHITE);

	public static ArrayList<Colour> SHADES_OF_GREY = Util.newArrayListOfValues(
			Colour.CLOTHING_BLACK_JET,
			Colour.CLOTHING_BLACK,
			Colour.CLOTHING_GREY_DARK,
			Colour.CLOTHING_GREY,
			Colour.CLOTHING_WHITE);
	
	public static ArrayList<Colour> DARK_SHADES = Util.newArrayListOfValues(
			Colour.CLOTHING_GREY,
			Colour.CLOTHING_GREY_DARK,
			Colour.CLOTHING_BLACK,
			Colour.CLOTHING_BLACK_JET,
			Colour.CLOTHING_BROWN_VERY_DARK,
			Colour.CLOTHING_RED_VERY_DARK,
			Colour.CLOTHING_GREEN_VERY_DARK,
			Colour.CLOTHING_BLUE_VERY_DARK);
	
	public static ArrayList<Colour> DENIM = Util.newArrayListOfValues(
			Colour.CLOTHING_BLUE_GREY,
			Colour.CLOTHING_BLUE_NAVY,
			Colour.CLOTHING_PINK_LIGHT,
			Colour.CLOTHING_DESATURATED_BROWN,
			Colour.CLOTHING_WHITE,
			Colour.CLOTHING_GREY,
			Colour.CLOTHING_BLACK);
	
	public static ArrayList<Colour> KIMONO = Util.newArrayListOfValues(
			Colour.CLOTHING_BLUE_LIGHT,
			Colour.CLOTHING_PINK_LIGHT,
			Colour.CLOTHING_PINK,
			Colour.CLOTHING_PURPLE,
			Colour.CLOTHING_PURPLE_LIGHT,
			Colour.CLOTHING_RED,
			Colour.CLOTHING_TURQUOISE,
			Colour.CLOTHING_WHITE,
			Colour.CLOTHING_YELLOW);
	
	public static ArrayList<Colour> MAID = Util.newArrayListOfValues(
			Colour.CLOTHING_PINK_LIGHT,
			Colour.CLOTHING_PINK,
			Colour.CLOTHING_BLACK);
	
	public static ArrayList<Colour> MILK_MAID = Util.newArrayListOfValues(
			Colour.CLOTHING_PINK_LIGHT,
			Colour.CLOTHING_BLUE_LIGHT,
			Colour.CLOTHING_PURPLE_LIGHT,
			Colour.CLOTHING_GREEN,
			Colour.CLOTHING_TAN,
			Colour.CLOTHING_KHAKI,
			Colour.CLOTHING_BROWN,
			Colour.CLOTHING_BLACK);
	
	public static ArrayList<Colour> LEATHER = Util.newArrayListOfValues(
			Colour.CLOTHING_WHITE,
			Colour.CLOTHING_BLACK,
			Colour.CLOTHING_BLACK_JET,
			Colour.CLOTHING_GREY,
			Colour.CLOTHING_DESATURATED_BROWN,
			Colour.CLOTHING_BROWN,
			Colour.CLOTHING_BROWN_DARK,
			Colour.CLOTHING_BROWN_VERY_DARK,
			Colour.CLOTHING_TAN,
			Colour.CLOTHING_KHAKI);
	
	public static ArrayList<Colour> LINGERIE = Util.newArrayListOfValues(
			Colour.CLOTHING_WHITE,
			Colour.CLOTHING_GREY_LIGHT,
			Colour.CLOTHING_GREY,
			Colour.CLOTHING_GREY_DARK,
			Colour.CLOTHING_BLACK,
			Colour.CLOTHING_BLACK_JET,
			Colour.CLOTHING_RED_VERY_DARK,
			Colour.CLOTHING_RED_DARK,
			Colour.CLOTHING_RED,
			Colour.CLOTHING_RED_BRIGHT,
			Colour.CLOTHING_ORANGE,
			Colour.CLOTHING_ORANGE_BRIGHT,
			Colour.CLOTHING_ORANGE_DARK,
			Colour.CLOTHING_BROWN,
			Colour.CLOTHING_BROWN_DARK,
			Colour.CLOTHING_BROWN_VERY_DARK,
			Colour.CLOTHING_TAN,
			Colour.CLOTHING_KHAKI,
			Colour.CLOTHING_OLIVE,
			Colour.CLOTHING_YELLOW,
			Colour.CLOTHING_YELLOW_DARK,
			Colour.CLOTHING_GREEN_LIME,
			Colour.CLOTHING_GREEN,
			Colour.CLOTHING_GREEN_DRAB,
			Colour.CLOTHING_GREEN_DARK,
			Colour.CLOTHING_GREEN_VERY_DARK,
			Colour.CLOTHING_TURQUOISE,
			Colour.CLOTHING_BLUE_LIGHT,
			Colour.CLOTHING_BLUE,
			Colour.CLOTHING_BLUE_GREY,
			Colour.CLOTHING_BLUE_NAVY,
			Colour.CLOTHING_BLUE_DARK,
			Colour.CLOTHING_BLUE_VERY_DARK,
			Colour.CLOTHING_PURPLE_VERY_DARK,
			Colour.CLOTHING_PURPLE_DARK,
			Colour.CLOTHING_PURPLE,
			Colour.CLOTHING_PURPLE_LIGHT,
			Colour.CLOTHING_PERIWINKLE,
			Colour.CLOTHING_PINK_LIGHT,
			Colour.CLOTHING_PINK,
			Colour.CLOTHING_PINK_HOT,
			Colour.CLOTHING_PINK_DARK);
	
	public static ArrayList<Colour> ALL_METAL = Util.newArrayListOfValues(
			Colour.CLOTHING_BLACK_STEEL,
			Colour.CLOTHING_GUNMETAL,
			Colour.CLOTHING_STEEL,
			Colour.CLOTHING_BRASS,
			Colour.CLOTHING_COPPER,
			Colour.CLOTHING_SILVER,
			Colour.CLOTHING_ROSE_GOLD,
			Colour.CLOTHING_GOLD,
			Colour.CLOTHING_PLATINUM);
			
	
	public static ArrayList<Colour> ALL = Util.newArrayListOfValues(
			Colour.CLOTHING_WHITE,
			Colour.CLOTHING_GREY_LIGHT,
			Colour.CLOTHING_GREY,
			Colour.CLOTHING_GREY_DARK,
			Colour.CLOTHING_BLACK,
			Colour.CLOTHING_BLACK_JET,
			Colour.CLOTHING_RED_VERY_DARK,
			Colour.CLOTHING_RED_DARK,
			Colour.CLOTHING_RED,
			Colour.CLOTHING_RED_BRIGHT,
			Colour.CLOTHING_ORANGE,
			Colour.CLOTHING_ORANGE_BRIGHT,
			Colour.CLOTHING_ORANGE_DARK,
			Colour.CLOTHING_DESATURATED_BROWN,
			Colour.CLOTHING_BROWN,
			Colour.CLOTHING_BROWN_DARK,
			Colour.CLOTHING_BROWN_VERY_DARK,
			Colour.CLOTHING_TAN,
			Colour.CLOTHING_KHAKI,
			Colour.CLOTHING_OLIVE,
			Colour.CLOTHING_YELLOW,
			Colour.CLOTHING_YELLOW_DARK,
			Colour.CLOTHING_GREEN_LIME,
			Colour.CLOTHING_GREEN,
			Colour.CLOTHING_GREEN_DRAB,
			Colour.CLOTHING_GREEN_DARK,
			Colour.CLOTHING_GREEN_VERY_DARK,
			Colour.CLOTHING_TURQUOISE,
			Colour.CLOTHING_BLUE_LIGHT,
			Colour.CLOTHING_BLUE,
			Colour.CLOTHING_BLUE_GREY,
			Colour.CLOTHING_BLUE_NAVY,
			Colour.CLOTHING_BLUE_DARK,
			Colour.CLOTHING_BLUE_VERY_DARK,
			Colour.CLOTHING_PURPLE_VERY_DARK,
			Colour.CLOTHING_PURPLE_DARK,
			Colour.CLOTHING_PURPLE,
			Colour.CLOTHING_PURPLE_LIGHT,
			Colour.CLOTHING_PERIWINKLE,
			Colour.CLOTHING_PINK_LIGHT,
			Colour.CLOTHING_PINK,
			Colour.CLOTHING_PINK_HOT,
			Colour.CLOTHING_PINK_DARK);
	
	public static ArrayList<Colour> ALL_WITH_METALS = Util.newArrayListOfValues(
			Colour.CLOTHING_WHITE,
			Colour.CLOTHING_GREY_LIGHT,
			Colour.CLOTHING_GREY,
			Colour.CLOTHING_GREY_DARK,
			Colour.CLOTHING_BLACK,
			Colour.CLOTHING_BLACK_JET,
			Colour.CLOTHING_RED_VERY_DARK,
			Colour.CLOTHING_RED_DARK,
			Colour.CLOTHING_RED,
			Colour.CLOTHING_RED_BRIGHT,
			Colour.CLOTHING_ORANGE,
			Colour.CLOTHING_ORANGE_BRIGHT,
			Colour.CLOTHING_ORANGE_DARK,
			Colour.CLOTHING_DESATURATED_BROWN,
			Colour.CLOTHING_BROWN,
			Colour.CLOTHING_BROWN_DARK,
			Colour.CLOTHING_BROWN_VERY_DARK,
			Colour.CLOTHING_TAN,
			Colour.CLOTHING_KHAKI,
			Colour.CLOTHING_OLIVE,
			Colour.CLOTHING_YELLOW_DARK,
			Colour.CLOTHING_YELLOW,
			Colour.CLOTHING_GREEN_LIME,
			Colour.CLOTHING_GREEN,
			Colour.CLOTHING_GREEN_DRAB,
			Colour.CLOTHING_GREEN_DARK,
			Colour.CLOTHING_GREEN_VERY_DARK,
			Colour.CLOTHING_TURQUOISE,
			Colour.CLOTHING_BLUE_LIGHT,
			Colour.CLOTHING_BLUE,
			Colour.CLOTHING_BLUE_GREY,
			Colour.CLOTHING_BLUE_NAVY,
			Colour.CLOTHING_BLUE_DARK,
			Colour.CLOTHING_BLUE_VERY_DARK,
			Colour.CLOTHING_PURPLE_VERY_DARK,
			Colour.CLOTHING_PURPLE_DARK,
			Colour.CLOTHING_PURPLE,
			Colour.CLOTHING_PURPLE_LIGHT,
			Colour.CLOTHING_PERIWINKLE,
			Colour.CLOTHING_PINK_LIGHT,
			Colour.CLOTHING_PINK,
			Colour.CLOTHING_PINK_HOT,
			Colour.CLOTHING_PINK_DARK,
			Colour.CLOTHING_BLACK_STEEL,
			Colour.CLOTHING_GUNMETAL,
			Colour.CLOTHING_STEEL,
			Colour.CLOTHING_BRASS,
			Colour.CLOTHING_COPPER,
			Colour.CLOTHING_SILVER,
			Colour.CLOTHING_ROSE_GOLD,
			Colour.CLOTHING_GOLD,
			Colour.CLOTHING_PLATINUM);
	
	public static ArrayList<Colour> NOT_WHITE = new ArrayList<>(ALL);
	
	public static ArrayList<Colour> NOT_BLACK = new ArrayList<>(ALL);
	
	static {
		NOT_WHITE.remove(Colour.CLOTHING_WHITE);
		NOT_BLACK.remove(Colour.CLOTHING_BLACK);
		NOT_BLACK.remove(Colour.CLOTHING_BLACK_JET);
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
