package com.lilithsthrone.utils.colours;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.4
 * @version 0.4
 * @author Innoxia
 */
@SuppressWarnings("unchecked")
public class ColourListPresets {

	public static ArrayList<Colour> NONE = new ArrayList<>();

	//----- COVERING PRESETS -----//
	
	public static ArrayList<Colour> humanSkinColours = new ArrayList<>(PresetColour.getHumanSkinColours());
	public static ArrayList<Colour> ratSkinColours = new ArrayList<>(PresetColour.ratSkinColours);
	public static ArrayList<Colour> demonSkinColours = new ArrayList<>(PresetColour.demonSkinColours);
	public static ArrayList<Colour> allSkinColours = new ArrayList<>(PresetColour.allSkinColours);
	
	public static ArrayList<Colour> naturalSlimeColours = new ArrayList<>(PresetColour.naturalSlimeColours);
	public static ArrayList<Colour> dyeSlimeColours = new ArrayList<>(PresetColour.dyeSlimeColours);
	
	public static ArrayList<Colour> naturalFeatherColours = new ArrayList<>(PresetColour.naturalFeatherColours);
	public static ArrayList<Colour> dyeFeatherColours = new ArrayList<>(PresetColour.dyeFeatherColours);
	
	public static ArrayList<Colour> naturalFurColours = new ArrayList<>(PresetColour.naturalFurColours);
	
	public static ArrayList<Colour> allCoveringColours = new ArrayList<>(PresetColour.allCoveringColours);
	
	public static ArrayList<Colour> allMakeupColours = new ArrayList<>(PresetColour.allMakeupColours);
	
	public static ArrayList<Colour> naturalScaleColours = new ArrayList<>(PresetColour.naturalScaleColours);
	
	public static ArrayList<Colour> hornColours = new ArrayList<>(PresetColour.hornColours);
	public static ArrayList<Colour> antlerColours = new ArrayList<>(PresetColour.antlerColours);
	
	public static ArrayList<Colour> naturalHairColours = new ArrayList<>(PresetColour.naturalHairColours);
	
	public static ArrayList<Colour> naturalIrisColours = new ArrayList<>(PresetColour.naturalIrisColours);
	public static ArrayList<Colour> dyeIrisColours = new ArrayList<>(PresetColour.dyeIrisColours);

	public static ArrayList<Colour> naturalDemonIrisColours = new ArrayList<>(PresetColour.naturalDemonIrisColours);
	public static ArrayList<Colour> dyeDemonIrisColours = new ArrayList<>(PresetColour.dyeDemonIrisColours);

	public static ArrayList<Colour> naturalPredatorIrisColours = new ArrayList<>(PresetColour.naturalPredatorIrisColours);
	public static ArrayList<Colour> dyePredatorIrisColours = new ArrayList<>(PresetColour.dyePredatorIrisColours);

	public static ArrayList<Colour> naturalPupilColours = new ArrayList<>(PresetColour.naturalPupilColours);
	public static ArrayList<Colour> dyePupilColours = new ArrayList<>(PresetColour.dyePupilColours);

	public static ArrayList<Colour> naturalScleraColours = new ArrayList<>(PresetColour.naturalScleraColours);
	public static ArrayList<Colour> dyeScleraColours = new ArrayList<>(PresetColour.dyeScleraColours);
	
	
	//----- CLOTHING PRESETS -----//
	
	/*
		NOTE:
		All clothing colours are available in the JUST_ form. For example,
		JUST_PINK will map to PresetColour.CLOTHING_PINK.

		The handful of JUST_ entries defined below are here so that they may
		be referenced in code, the remainder are created dynamically.
	 */
	
	public static ArrayList<Colour> JUST_WHITE = Util.newArrayListOfValues(
			PresetColour.CLOTHING_WHITE);
	
	public static ArrayList<Colour> JUST_BLACK = Util.newArrayListOfValues(
			PresetColour.CLOTHING_BLACK);

	public static ArrayList<Colour> JUST_RED_DARK = Util.newArrayListOfValues(
			PresetColour.CLOTHING_RED_DARK);
	
	public static ArrayList<Colour> JUST_RED = Util.newArrayListOfValues(
			PresetColour.CLOTHING_RED);

	public static ArrayList<Colour> JUST_GREY = Util.newArrayListOfValues(
			PresetColour.CLOTHING_GREY);
	
	public static ArrayList<Colour> JUST_GOLD = Util.newArrayListOfValues(
			PresetColour.CLOTHING_GOLD);
	
	public static ArrayList<Colour> JUST_ROSE_GOLD = Util.newArrayListOfValues(
			PresetColour.CLOTHING_ROSE_GOLD);
	
	public static ArrayList<Colour> JUST_STEEL = Util.newArrayListOfValues(
			PresetColour.CLOTHING_STEEL);

	public static ArrayList<Colour> BLACK_OR_WHITE = Util.newArrayListOfValues(
			PresetColour.CLOTHING_BLACK,
			PresetColour.CLOTHING_WHITE);

	public static ArrayList<Colour> SHADES_OF_GREY = Util.newArrayListOfValues(
//			PresetColour.CLOTHING_BLACK_JET,
			PresetColour.CLOTHING_BLACK,
			PresetColour.CLOTHING_GREY_DARK,
			PresetColour.CLOTHING_GREY,
			PresetColour.CLOTHING_GREY_LIGHT
//			PresetColour.CLOTHING_WHITE
			);
	
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
			PresetColour.CLOTHING_DESATURATED_BROWN_DARK,
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
			PresetColour.CLOTHING_DESATURATED_BROWN_DARK,
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
			PresetColour.CLOTHING_DESATURATED_BROWN_DARK,
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
			PresetColour.CLOTHING_DESATURATED_BROWN_DARK,
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
	
	// For testing:
	
	// For use with items that should have skin colour tones:
	// Be aware that these colours were made for text readability, not for item display, and so they shouldn't really be used (I added them for testing and decided to just leave them in)
	public static ArrayList<Colour> HUMAN_SKIN_COLOURS;
	public static ArrayList<Colour> ALL_SKIN_COLOURS;
	public static ArrayList<Colour> ALL_WITH_SKIN_COLOURS;

	//Speshul debug color list with added damage and BaseColours
	//Modders don't use this in your items rrrrreeeeee
	public static ArrayList<Colour> DEBUG_ALL = Util.newArrayListOfValues(
			PresetColour.BASE_WHITE,
			PresetColour.BASE_GREY_LIGHT,
			PresetColour.BASE_GREY,
			PresetColour.BASE_GREY_DARK,
			PresetColour.BASE_ROSE,
			PresetColour.BASE_LILAC,
			PresetColour.BASE_LILAC_LIGHT,
			PresetColour.BASE_INDIGO,
			PresetColour.BASE_PURPLE_DARK,
			PresetColour.BASE_PURPLE,
			PresetColour.BASE_PURPLE_LIGHT,
			PresetColour.BASE_PINK_DEEP,
			PresetColour.BASE_PINK_SALMON,
			PresetColour.BASE_PINK,
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.BASE_MAGENTA,
			PresetColour.BASE_CRIMSON,
			PresetColour.BASE_RED_DARK,
			PresetColour.BASE_RED,
			PresetColour.BASE_RED_LIGHT,
			PresetColour.BASE_TAN,
			PresetColour.BASE_BROWN,
			PresetColour.BASE_BROWN_DARK,
			PresetColour.BASE_COPPER,
			PresetColour.BASE_ORANGE,
			PresetColour.BASE_GINGER,
			PresetColour.BASE_GOLD,
			PresetColour.BASE_YELLOW,
			PresetColour.BASE_YELLOW_LIGHT,
			PresetColour.BASE_GREEN_LIME,
			PresetColour.BASE_GREEN_LIGHT,
			PresetColour.BASE_GREEN,
			PresetColour.BASE_GREEN_DARK,
			PresetColour.BASE_AQUA,
			PresetColour.BASE_TEAL,
			PresetColour.BASE_PERIWINKLE,
			PresetColour.BASE_BLUE_DARK,
			PresetColour.BASE_BLUE_LIGHT,
			PresetColour.BASE_BLUE,
			PresetColour.BASE_BLUE_STEEL,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_PITCH_BLACK,
			PresetColour.DAMAGE_TYPE_UNARMED,
			PresetColour.DAMAGE_TYPE_MELEE,
			PresetColour.DAMAGE_TYPE_RANGED,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			PresetColour.DAMAGE_TYPE_MANA,
			PresetColour.DAMAGE_TYPE_LUST,
			PresetColour.DAMAGE_TYPE_SPELL,
			PresetColour.DAMAGE_TYPE_FIRE,
			PresetColour.DAMAGE_TYPE_COLD,
			PresetColour.DAMAGE_TYPE_POISON,
			PresetColour.DAMAGE_TYPE_PURE);
		
	static {
		NOT_WHITE.remove(PresetColour.CLOTHING_WHITE);
		NOT_BLACK.remove(PresetColour.CLOTHING_BLACK);
		NOT_BLACK.remove(PresetColour.CLOTHING_BLACK_JET);
		
		DEBUG_ALL.addAll(ALL_WITH_METALS);
		
		
		// Skin colours:
		
		HUMAN_SKIN_COLOURS = new ArrayList<>(humanSkinColours);
		
		ALL_SKIN_COLOURS = new ArrayList<>(allSkinColours);
		ALL_SKIN_COLOURS.removeIf(c->c.isMetallic() || c.isRainbow());
		
		ALL_WITH_SKIN_COLOURS = new ArrayList<>(allSkinColours);
		ALL_WITH_SKIN_COLOURS.addAll(ALL);
		ALL_WITH_SKIN_COLOURS.removeIf(c->c.isMetallic() || c.isRainbow());
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
		
		id = Util.getClosestStringMatchUnordered(id, idToColourListMap.keySet());
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
		
		List<Colour> clothingColours = PresetColour.getAllPresetColours("CLOTHING_");
		for(Colour c : clothingColours) {
			String presetName = "JUST_" + PresetColour.getIdFromColour(c).substring("CLOTHING_".length());
			
			if (!idToColourListMap.containsKey(presetName)) {
				ArrayList<Colour> preset = Util.newArrayListOfValues(c);
				idToColourListMap.put(presetName, preset);
			}
		}
	}
}
