package com.lilithsthrone.utils;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.2.4
 * @version 0.2.4
 * @author Innoxia
 */
public enum ColourListPresets {

	NONE(new ArrayList<>()),
	
	// > JUST
	
	JUST_WHITE(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_WHITE))),
	
	JUST_BLACK(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_BLACK))),
	
	JUST_RED(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_RED))),
	
	JUST_BROWN(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_BROWN))),
	
	JUST_YELLOW(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_YELLOW))),
	
	JUST_PINK(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_PINK))),
	
	JUST_GREY(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_GREY))),
	
	JUST_GOLD(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_GOLD))),
	
	JUST_STEEL(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_STEEL))),
	
	BLACK_OR_WHITE(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_BLACK),
			new ListValue<Colour>(Colour.CLOTHING_WHITE))),
	
	DENIM(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_BLUE_LIGHT),
			new ListValue<Colour>(Colour.CLOTHING_BLUE),
			new ListValue<Colour>(Colour.CLOTHING_PINK_LIGHT),
			new ListValue<Colour>(Colour.CLOTHING_PINK),
			new ListValue<Colour>(Colour.CLOTHING_WHITE),
			new ListValue<Colour>(Colour.CLOTHING_GREY),
			new ListValue<Colour>(Colour.CLOTHING_BLACK))),
	
	KIMONO(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_BLUE_LIGHT),
			new ListValue<Colour>(Colour.CLOTHING_PINK_LIGHT),
			new ListValue<Colour>(Colour.CLOTHING_PINK),
			new ListValue<Colour>(Colour.CLOTHING_PURPLE),
			new ListValue<Colour>(Colour.CLOTHING_PURPLE_LIGHT),
			new ListValue<Colour>(Colour.CLOTHING_RED),
			new ListValue<Colour>(Colour.CLOTHING_TURQUOISE),
			new ListValue<Colour>(Colour.CLOTHING_WHITE),
			new ListValue<Colour>(Colour.CLOTHING_YELLOW))),
	
	MAID(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_PINK_LIGHT),
			new ListValue<Colour>(Colour.CLOTHING_PINK),
			new ListValue<Colour>(Colour.CLOTHING_BLACK))),
	
	MILK_MAID(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_PINK_LIGHT),
			new ListValue<Colour>(Colour.CLOTHING_BLUE_LIGHT),
			new ListValue<Colour>(Colour.CLOTHING_PURPLE_LIGHT),
			new ListValue<Colour>(Colour.CLOTHING_GREEN),
			new ListValue<Colour>(Colour.CLOTHING_TAN),
			new ListValue<Colour>(Colour.CLOTHING_BROWN),
			new ListValue<Colour>(Colour.CLOTHING_BLACK))),
	
	LEATHER(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_WHITE),
			new ListValue<Colour>(Colour.CLOTHING_BLACK),
			new ListValue<Colour>(Colour.CLOTHING_GREY),
			new ListValue<Colour>(Colour.CLOTHING_BROWN),
			new ListValue<Colour>(Colour.CLOTHING_TAN))),
	
	LINGERIE(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_WHITE),
			new ListValue<Colour>(Colour.CLOTHING_BLACK),
			new ListValue<Colour>(Colour.CLOTHING_GREY),
			new ListValue<Colour>(Colour.CLOTHING_RED),
			new ListValue<Colour>(Colour.CLOTHING_RED_BRIGHT),
			new ListValue<Colour>(Colour.CLOTHING_RED_DARK),
			new ListValue<Colour>(Colour.CLOTHING_ORANGE),
			new ListValue<Colour>(Colour.CLOTHING_ORANGE_BRIGHT),
			new ListValue<Colour>(Colour.CLOTHING_ORANGE_DARK),
			new ListValue<Colour>(Colour.CLOTHING_BROWN),
			new ListValue<Colour>(Colour.CLOTHING_TAN),
			new ListValue<Colour>(Colour.CLOTHING_YELLOW),
			new ListValue<Colour>(Colour.CLOTHING_GREEN_LIME),
			new ListValue<Colour>(Colour.CLOTHING_GREEN),
			new ListValue<Colour>(Colour.CLOTHING_GREEN_DARK),
			new ListValue<Colour>(Colour.CLOTHING_TURQUOISE),
			new ListValue<Colour>(Colour.CLOTHING_BLUE_LIGHT),
			new ListValue<Colour>(Colour.CLOTHING_BLUE),
			new ListValue<Colour>(Colour.CLOTHING_BLUE_DARK),
			new ListValue<Colour>(Colour.CLOTHING_PURPLE),
			new ListValue<Colour>(Colour.CLOTHING_PURPLE_DARK),
			new ListValue<Colour>(Colour.CLOTHING_PURPLE_LIGHT),
			new ListValue<Colour>(Colour.CLOTHING_PINK),
			new ListValue<Colour>(Colour.CLOTHING_PINK_LIGHT))),
	
	ALL_METAL(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_BLACK_STEEL),
			new ListValue<Colour>(Colour.CLOTHING_STEEL),
			new ListValue<Colour>(Colour.CLOTHING_COPPER),
			new ListValue<Colour>(Colour.CLOTHING_SILVER),
			new ListValue<Colour>(Colour.CLOTHING_ROSE_GOLD),
			new ListValue<Colour>(Colour.CLOTHING_GOLD),
			new ListValue<Colour>(Colour.CLOTHING_PLATINUM))),
	
	NOT_WHITE(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_BLACK),
			new ListValue<Colour>(Colour.CLOTHING_GREY),
			new ListValue<Colour>(Colour.CLOTHING_RED),
			new ListValue<Colour>(Colour.CLOTHING_RED_BRIGHT),
			new ListValue<Colour>(Colour.CLOTHING_RED_DARK),
			new ListValue<Colour>(Colour.CLOTHING_ORANGE),
			new ListValue<Colour>(Colour.CLOTHING_ORANGE_BRIGHT),
			new ListValue<Colour>(Colour.CLOTHING_ORANGE_DARK),
			new ListValue<Colour>(Colour.CLOTHING_BROWN),
			new ListValue<Colour>(Colour.CLOTHING_TAN),
			new ListValue<Colour>(Colour.CLOTHING_YELLOW),
			new ListValue<Colour>(Colour.CLOTHING_GREEN_LIME),
			new ListValue<Colour>(Colour.CLOTHING_GREEN),
			new ListValue<Colour>(Colour.CLOTHING_GREEN_DARK),
			new ListValue<Colour>(Colour.CLOTHING_TURQUOISE),
			new ListValue<Colour>(Colour.CLOTHING_BLUE_LIGHT),
			new ListValue<Colour>(Colour.CLOTHING_BLUE),
			new ListValue<Colour>(Colour.CLOTHING_BLUE_DARK),
			new ListValue<Colour>(Colour.CLOTHING_PURPLE),
			new ListValue<Colour>(Colour.CLOTHING_PURPLE_DARK),
			new ListValue<Colour>(Colour.CLOTHING_PURPLE_LIGHT),
			new ListValue<Colour>(Colour.CLOTHING_PINK),
			new ListValue<Colour>(Colour.CLOTHING_PINK_LIGHT))),
	
	ALL(Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.CLOTHING_WHITE),
			new ListValue<Colour>(Colour.CLOTHING_BLACK),
			new ListValue<Colour>(Colour.CLOTHING_GREY),
			new ListValue<Colour>(Colour.CLOTHING_RED),
			new ListValue<Colour>(Colour.CLOTHING_RED_BRIGHT),
			new ListValue<Colour>(Colour.CLOTHING_RED_DARK),
			new ListValue<Colour>(Colour.CLOTHING_ORANGE),
			new ListValue<Colour>(Colour.CLOTHING_ORANGE_BRIGHT),
			new ListValue<Colour>(Colour.CLOTHING_ORANGE_DARK),
			new ListValue<Colour>(Colour.CLOTHING_BROWN),
			new ListValue<Colour>(Colour.CLOTHING_TAN),
			new ListValue<Colour>(Colour.CLOTHING_YELLOW),
			new ListValue<Colour>(Colour.CLOTHING_GREEN_LIME),
			new ListValue<Colour>(Colour.CLOTHING_GREEN),
			new ListValue<Colour>(Colour.CLOTHING_GREEN_DARK),
			new ListValue<Colour>(Colour.CLOTHING_TURQUOISE),
			new ListValue<Colour>(Colour.CLOTHING_BLUE_LIGHT),
			new ListValue<Colour>(Colour.CLOTHING_BLUE),
			new ListValue<Colour>(Colour.CLOTHING_BLUE_DARK),
			new ListValue<Colour>(Colour.CLOTHING_PURPLE),
			new ListValue<Colour>(Colour.CLOTHING_PURPLE_DARK),
			new ListValue<Colour>(Colour.CLOTHING_PURPLE_LIGHT),
			new ListValue<Colour>(Colour.CLOTHING_PINK),
			new ListValue<Colour>(Colour.CLOTHING_PINK_LIGHT)));
	
	private List<Colour> presetColourList;
	
	public List<Colour> getPresetColourList() {
		return presetColourList;
	}

	private ColourListPresets(List<Colour> presetColourList) {
		this.presetColourList = presetColourList;
	}

	
	
}
