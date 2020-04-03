package com.lilithsthrone.game.character.body.valueEnums;

import java.util.List;

import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.?
 * @version 0.3.7
 * @author Pimgd
 */
public enum StartingSkinTone {
	
	VERY_LIGHT(Util.newArrayListOfValues(
			PresetColour.SKIN_PALE,
			PresetColour.SKIN_PINK,
			PresetColour.SKIN_BLUE,
			PresetColour.SKIN_IVORY,
			
			PresetColour.COVERING_BLEACH_BLONDE,
			PresetColour.COVERING_GINGER,
			PresetColour.COVERING_LILAC,
			PresetColour.COVERING_PINK,
			PresetColour.COVERING_WHITE,
			
			PresetColour.ORIFICE_INTERIOR,
			PresetColour.TONGUE,

			PresetColour.COVERING_BROWN,
			PresetColour.COVERING_BLONDE,
			PresetColour.COVERING_BLEACH_BLONDE,
			PresetColour.COVERING_GINGER,
			PresetColour.COVERING_WHITE,
			PresetColour.COVERING_PINK,
			
			PresetColour.COVERING_CLEAR,
			PresetColour.COVERING_NONE,

			PresetColour.EYE_BROWN,
			PresetColour.EYE_BLUE,
			PresetColour.EYE_GREEN,
			PresetColour.EYE_YELLOW,
			PresetColour.EYE_RED,
			PresetColour.EYE_PINK,
			PresetColour.EYE_ORANGE,
			PresetColour.EYE_BLACK)),
			
	LIGHT(Util.newArrayListOfValues(
			PresetColour.SKIN_LIGHT,
			PresetColour.SKIN_ROSY,
			PresetColour.SKIN_PINK,
			PresetColour.SKIN_BLUE,
			PresetColour.SKIN_IVORY,
			PresetColour.SKIN_LILAC,
			
			PresetColour.COVERING_WHITE,
			PresetColour.COVERING_BLUE,
			PresetColour.COVERING_LILAC,
			PresetColour.COVERING_PINK,
			PresetColour.COVERING_RED,
			PresetColour.COVERING_GREEN,
			PresetColour.COVERING_YELLOW,
			PresetColour.COVERING_ORANGE,
			PresetColour.COVERING_GINGER,
			PresetColour.COVERING_BLEACH_BLONDE,
			
			PresetColour.ORIFICE_INTERIOR,
			PresetColour.TONGUE,

			PresetColour.COVERING_BROWN,
			PresetColour.COVERING_BLONDE,
			PresetColour.COVERING_BLEACH_BLONDE,
			PresetColour.COVERING_GINGER,
			PresetColour.COVERING_RED,
			PresetColour.COVERING_WHITE,
			PresetColour.COVERING_BLUE,
			PresetColour.COVERING_PURPLE,
			PresetColour.COVERING_PINK,
			PresetColour.COVERING_GREEN,
			
			PresetColour.COVERING_CLEAR,
			PresetColour.COVERING_NONE,

			PresetColour.EYE_BROWN,
			PresetColour.EYE_BLUE,
			PresetColour.EYE_GREEN,
			PresetColour.EYE_YELLOW,
			PresetColour.EYE_RED,
			PresetColour.EYE_PINK,
			PresetColour.EYE_ORANGE,
			PresetColour.EYE_BLACK)),
			
	OLIVE(Util.newArrayListOfValues(
			PresetColour.SKIN_OLIVE,
			PresetColour.SKIN_TANNED,
			PresetColour.SKIN_RED,
			PresetColour.SKIN_BROWN,
			PresetColour.SKIN_PINK,
			PresetColour.SKIN_BLUE,
			PresetColour.SKIN_LILAC,
			PresetColour.SKIN_PURPLE,
			PresetColour.SKIN_IVORY,

			PresetColour.COVERING_BLACK,
			PresetColour.COVERING_WHITE,
			PresetColour.COVERING_BLUE,
			PresetColour.COVERING_LILAC,
			PresetColour.COVERING_PINK,
			PresetColour.COVERING_RED,
			PresetColour.COVERING_GREEN,
			PresetColour.COVERING_YELLOW,
			PresetColour.COVERING_ORANGE,
			PresetColour.COVERING_GINGER,
			PresetColour.COVERING_BLEACH_BLONDE,
			
			PresetColour.ORIFICE_INTERIOR,
			PresetColour.TONGUE,

			PresetColour.COVERING_BROWN,
			PresetColour.COVERING_BROWN_DARK,
			PresetColour.COVERING_BLACK,
			PresetColour.COVERING_BLONDE,
			PresetColour.COVERING_BLEACH_BLONDE,
			PresetColour.COVERING_RED,
			PresetColour.COVERING_WHITE,
			PresetColour.COVERING_BLUE,
			PresetColour.COVERING_PURPLE,
			PresetColour.COVERING_PINK,
			PresetColour.COVERING_GREEN,
			
			PresetColour.COVERING_CLEAR,
			PresetColour.COVERING_NONE,

			PresetColour.EYE_BROWN,
			PresetColour.EYE_BLUE,
			PresetColour.EYE_GREEN,
			PresetColour.EYE_YELLOW,
			PresetColour.EYE_RED,
			PresetColour.EYE_PINK,
			PresetColour.EYE_ORANGE,
			PresetColour.EYE_BLACK)),
			
	DARK(Util.newArrayListOfValues(
			PresetColour.SKIN_DARK,
			PresetColour.SKIN_RED,
			PresetColour.SKIN_BROWN,
			PresetColour.SKIN_LILAC,
			PresetColour.SKIN_PURPLE,

			PresetColour.COVERING_BLACK,
			PresetColour.COVERING_WHITE,
			PresetColour.COVERING_BLUE,
			PresetColour.COVERING_LILAC,
			PresetColour.COVERING_PINK,
			PresetColour.COVERING_RED,
			PresetColour.COVERING_GREEN,
			PresetColour.COVERING_YELLOW,
			PresetColour.COVERING_ORANGE,
			
			PresetColour.ORIFICE_INTERIOR,
			PresetColour.TONGUE,

			PresetColour.COVERING_BROWN,
			PresetColour.COVERING_BROWN_DARK,
			PresetColour.COVERING_BLACK,
			PresetColour.COVERING_RED,
			PresetColour.COVERING_WHITE,
			PresetColour.COVERING_BLUE,
			PresetColour.COVERING_PURPLE,
			PresetColour.COVERING_PINK,
			PresetColour.COVERING_GREEN,
			
			PresetColour.COVERING_CLEAR,
			PresetColour.COVERING_NONE,

			PresetColour.EYE_BROWN,
			PresetColour.EYE_YELLOW,
			PresetColour.EYE_RED,
			PresetColour.EYE_PINK,
			PresetColour.EYE_ORANGE,
			PresetColour.EYE_BLACK)),
			
	VERY_DARK(Util.newArrayListOfValues(
			PresetColour.SKIN_EBONY,
			PresetColour.SKIN_CHOCOLATE,
			PresetColour.SKIN_RED,
			PresetColour.SKIN_BROWN,
			PresetColour.SKIN_PURPLE,

			PresetColour.COVERING_BLACK,
			PresetColour.COVERING_WHITE,
			PresetColour.COVERING_BLUE,
			PresetColour.COVERING_RED,
			PresetColour.COVERING_GREEN,
			PresetColour.COVERING_YELLOW,
			PresetColour.COVERING_ORANGE,
			
			PresetColour.ORIFICE_INTERIOR,
			PresetColour.TONGUE,

			PresetColour.COVERING_BROWN,
			PresetColour.COVERING_BROWN_DARK,
			PresetColour.COVERING_BLACK,
			PresetColour.COVERING_RED,
			PresetColour.COVERING_WHITE,
			PresetColour.COVERING_BLUE,
			PresetColour.COVERING_PURPLE,
			PresetColour.COVERING_GREEN,
			
			PresetColour.COVERING_CLEAR,
			PresetColour.COVERING_NONE,

			PresetColour.EYE_BROWN,
			PresetColour.EYE_YELLOW,
			PresetColour.EYE_RED,
			PresetColour.EYE_PINK,
			PresetColour.EYE_ORANGE,
			PresetColour.EYE_BLACK));
	
	private List<Colour> associatedColours;
	
	private StartingSkinTone(List<Colour> associatedColours) {
		this.associatedColours = associatedColours;
	}

	public List<Colour> getAssociatedColours() {
		return associatedColours;
	}
	
}
