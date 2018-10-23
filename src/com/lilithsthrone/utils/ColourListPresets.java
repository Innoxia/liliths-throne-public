package com.lilithsthrone.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.2.4
 * @version 0.2.6
 * @author Innoxia
 */
public enum ColourListPresets {

	NONE(new ArrayList<>()),
	
	// > JUST
	
	JUST_WHITE(Util.newArrayListOfValues(
			Colour.CLOTHING_WHITE)),
	
	JUST_BLACK(Util.newArrayListOfValues(
			Colour.CLOTHING_BLACK)),

	JUST_DARK_RED(Util.newArrayListOfValues(
			Colour.CLOTHING_RED_DARK)),
	
	JUST_RED(Util.newArrayListOfValues(
			Colour.CLOTHING_RED)),
	
	JUST_TAN(Util.newArrayListOfValues(
			Colour.CLOTHING_TAN)),
	
	JUST_BROWN(Util.newArrayListOfValues(
			Colour.CLOTHING_BROWN)),

	JUST_DARK_BROWN(Util.newArrayListOfValues(
			Colour.CLOTHING_BROWN_DARK)),
	
	JUST_ORANGE(Util.newArrayListOfValues(
			Colour.CLOTHING_ORANGE)),
	
	JUST_YELLOW(Util.newArrayListOfValues(
			Colour.CLOTHING_YELLOW)),
	
	JUST_PINK(Util.newArrayListOfValues(
			Colour.CLOTHING_PINK)),
	
	JUST_GREY(Util.newArrayListOfValues(
			Colour.CLOTHING_GREY)),
	
	JUST_GOLD(Util.newArrayListOfValues(
			Colour.CLOTHING_GOLD)),
	
	JUST_STEEL(Util.newArrayListOfValues(
			Colour.CLOTHING_STEEL)),

	JUST_SILVER(Util.newArrayListOfValues(
			Colour.CLOTHING_SILVER)),

	JUST_COPPER(Util.newArrayListOfValues(
			Colour.CLOTHING_COPPER)),
	
	BLACK_OR_WHITE(Util.newArrayListOfValues(
			Colour.CLOTHING_BLACK,
			Colour.CLOTHING_WHITE)),
	
	DENIM(Util.newArrayListOfValues(
			Colour.CLOTHING_BLUE_LIGHT,
			Colour.CLOTHING_BLUE,
			Colour.CLOTHING_PINK_LIGHT,
			Colour.CLOTHING_PINK,
			Colour.CLOTHING_WHITE,
			Colour.CLOTHING_GREY,
			Colour.CLOTHING_BLACK)),
	
	KIMONO(Util.newArrayListOfValues(
			Colour.CLOTHING_BLUE_LIGHT,
			Colour.CLOTHING_PINK_LIGHT,
			Colour.CLOTHING_PINK,
			Colour.CLOTHING_PURPLE,
			Colour.CLOTHING_PURPLE_LIGHT,
			Colour.CLOTHING_RED,
			Colour.CLOTHING_TURQUOISE,
			Colour.CLOTHING_WHITE,
			Colour.CLOTHING_YELLOW)),
	
	MAID(Util.newArrayListOfValues(
			Colour.CLOTHING_PINK_LIGHT,
			Colour.CLOTHING_PINK,
			Colour.CLOTHING_BLACK)),
	
	MILK_MAID(Util.newArrayListOfValues(
			Colour.CLOTHING_PINK_LIGHT,
			Colour.CLOTHING_BLUE_LIGHT,
			Colour.CLOTHING_PURPLE_LIGHT,
			Colour.CLOTHING_GREEN,
			Colour.CLOTHING_TAN,
			Colour.CLOTHING_BROWN,
			Colour.CLOTHING_BLACK)),
	
	LEATHER(Util.newArrayListOfValues(
			Colour.CLOTHING_WHITE,
			Colour.CLOTHING_BLACK,
			Colour.CLOTHING_GREY,
			Colour.CLOTHING_BROWN,
			Colour.CLOTHING_BROWN_DARK,
			Colour.CLOTHING_TAN)),
	
	LINGERIE(Util.newArrayListOfValues(
			Colour.CLOTHING_WHITE,
			Colour.CLOTHING_BLACK,
			Colour.CLOTHING_GREY,
			Colour.CLOTHING_RED,
			Colour.CLOTHING_RED_BRIGHT,
			Colour.CLOTHING_RED_DARK,
			Colour.CLOTHING_ORANGE,
			Colour.CLOTHING_ORANGE_BRIGHT,
			Colour.CLOTHING_ORANGE_DARK,
			Colour.CLOTHING_BROWN,
			Colour.CLOTHING_BROWN_DARK,
			Colour.CLOTHING_TAN,
			Colour.CLOTHING_YELLOW,
			Colour.CLOTHING_GREEN_LIME,
			Colour.CLOTHING_GREEN,
			Colour.CLOTHING_GREEN_DARK,
			Colour.CLOTHING_TURQUOISE,
			Colour.CLOTHING_BLUE_LIGHT,
			Colour.CLOTHING_BLUE,
			Colour.CLOTHING_BLUE_DARK,
			Colour.CLOTHING_PURPLE,
			Colour.CLOTHING_PURPLE_DARK,
			Colour.CLOTHING_PURPLE_LIGHT,
			Colour.CLOTHING_PERIWINKLE,
			Colour.CLOTHING_PINK,
			Colour.CLOTHING_PINK_LIGHT)),
	
	ALL_METAL(Util.newArrayListOfValues(
			Colour.CLOTHING_BLACK_STEEL,
			Colour.CLOTHING_STEEL,
			Colour.CLOTHING_COPPER,
			Colour.CLOTHING_SILVER,
			Colour.CLOTHING_ROSE_GOLD,
			Colour.CLOTHING_GOLD,
			Colour.CLOTHING_PLATINUM)),
	
	NOT_WHITE(Util.newArrayListOfValues(
			Colour.CLOTHING_BLACK,
			Colour.CLOTHING_GREY,
			Colour.CLOTHING_RED,
			Colour.CLOTHING_RED_BRIGHT,
			Colour.CLOTHING_RED_DARK,
			Colour.CLOTHING_ORANGE,
			Colour.CLOTHING_ORANGE_BRIGHT,
			Colour.CLOTHING_ORANGE_DARK,
			Colour.CLOTHING_BROWN,
			Colour.CLOTHING_BROWN_DARK,
			Colour.CLOTHING_TAN,
			Colour.CLOTHING_YELLOW,
			Colour.CLOTHING_GREEN_LIME,
			Colour.CLOTHING_GREEN,
			Colour.CLOTHING_GREEN_DARK,
			Colour.CLOTHING_TURQUOISE,
			Colour.CLOTHING_BLUE_LIGHT,
			Colour.CLOTHING_BLUE,
			Colour.CLOTHING_BLUE_DARK,
			Colour.CLOTHING_PURPLE,
			Colour.CLOTHING_PURPLE_DARK,
			Colour.CLOTHING_PURPLE_LIGHT,
			Colour.CLOTHING_PERIWINKLE,
			Colour.CLOTHING_PINK,
			Colour.CLOTHING_PINK_LIGHT)),
	
	ALL(Util.newArrayListOfValues(
			Colour.CLOTHING_WHITE,
			Colour.CLOTHING_BLACK,
			Colour.CLOTHING_GREY,
			Colour.CLOTHING_RED,
			Colour.CLOTHING_RED_BRIGHT,
			Colour.CLOTHING_RED_DARK,
			Colour.CLOTHING_ORANGE,
			Colour.CLOTHING_ORANGE_BRIGHT,
			Colour.CLOTHING_ORANGE_DARK,
			Colour.CLOTHING_BROWN,
			Colour.CLOTHING_BROWN_DARK,
			Colour.CLOTHING_TAN,
			Colour.CLOTHING_YELLOW,
			Colour.CLOTHING_GREEN_LIME,
			Colour.CLOTHING_GREEN,
			Colour.CLOTHING_GREEN_DARK,
			Colour.CLOTHING_TURQUOISE,
			Colour.CLOTHING_BLUE_LIGHT,
			Colour.CLOTHING_BLUE,
			Colour.CLOTHING_BLUE_DARK,
			Colour.CLOTHING_PURPLE,
			Colour.CLOTHING_PURPLE_DARK,
			Colour.CLOTHING_PURPLE_LIGHT,
			Colour.CLOTHING_PERIWINKLE,
			Colour.CLOTHING_PINK,
			Colour.CLOTHING_PINK_LIGHT)),
	
	ALL_WITH_METALS(Util.newArrayListOfValues(
			Colour.CLOTHING_WHITE,
			Colour.CLOTHING_BLACK,
			Colour.CLOTHING_GREY,
			Colour.CLOTHING_RED,
			Colour.CLOTHING_RED_BRIGHT,
			Colour.CLOTHING_RED_DARK,
			Colour.CLOTHING_ORANGE,
			Colour.CLOTHING_ORANGE_BRIGHT,
			Colour.CLOTHING_ORANGE_DARK,
			Colour.CLOTHING_BROWN,
			Colour.CLOTHING_BROWN_DARK,
			Colour.CLOTHING_TAN,
			Colour.CLOTHING_YELLOW,
			Colour.CLOTHING_GREEN_LIME,
			Colour.CLOTHING_GREEN,
			Colour.CLOTHING_GREEN_DARK,
			Colour.CLOTHING_TURQUOISE,
			Colour.CLOTHING_BLUE_LIGHT,
			Colour.CLOTHING_BLUE,
			Colour.CLOTHING_BLUE_DARK,
			Colour.CLOTHING_PURPLE,
			Colour.CLOTHING_PURPLE_DARK,
			Colour.CLOTHING_PURPLE_LIGHT,
			Colour.CLOTHING_PERIWINKLE,
			Colour.CLOTHING_PINK,
			Colour.CLOTHING_PINK_LIGHT,
			Colour.CLOTHING_STEEL,
			Colour.CLOTHING_COPPER,
			Colour.CLOTHING_SILVER,
			Colour.CLOTHING_ROSE_GOLD,
			Colour.CLOTHING_GOLD,
			Colour.CLOTHING_PLATINUM));
	
	private List<Colour> presetColourList;
	
	public List<Colour> getPresetColourList() {
		return presetColourList;
	}

	private ColourListPresets(List<Colour> presetColourList) {
		this.presetColourList = presetColourList;
	}

	
	
}
