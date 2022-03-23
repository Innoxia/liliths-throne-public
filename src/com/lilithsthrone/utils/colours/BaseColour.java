package com.lilithsthrone.utils.colours;

import com.lilithsthrone.utils.Util;

import javafx.scene.paint.Color;

/**
 * @since 0.1.69
 * @version 0.4
 * @author Innoxia
 */
public enum BaseColour {

	WHITE(Util.newColour(0xFFFFFF), Util.newColour(0x636363)),
	SILVER(Util.newColour(0xF3F3F3), Util.newColour(0x636363)),
	PLATINUM(Util.newColour(0xE4E5E2), Util.newColour(0xE4E5E2)),
	
	ROSE(Util.newColour(0xEBC2FF), Util.newColour(0xB800E6)),
	INDIGO(Util.newColour(0x9683ec), Util.newColour(0x9683ec)),
	LILAC(Util.newColour(0xC8A2C8), Util.newColour(0xC8A2C8)),
	LILAC_LIGHT(Util.newColour(0xDCD0FF), Util.newColour(0xDCD0FF)),
	PURPLE_DARK(Util.newColour(0x934cff), Util.newColour(0x6000D6)),
	PURPLE(Util.newColour(0xB980FF), Util.newColour(0x943DFF)),
	PURPLE_LIGHT(Util.newColour(0xDA8FFF), Util.newColour(0xC552FF)),

	PINK_SALMON(Util.newColour(0xFF99C9), Util.newColour(0xFF57A5)),
	PINK(Util.newColour(0xFF6BDA), Util.newColour(0xFF0FC3)),
	PINK_LIGHT(Util.newColour(0xF5A8FF), Util.newColour(0xCF26BE)),
	PINK_PALE(Util.newColour(0xf5d6ff), Util.newColour(0xd8ade2)),
	PINK_DEEP(Util.newColour(0xFF33CC), Util.newColour(0xFF33CC)),
		
	MAGENTA(Util.newColour(0xFF1472), Util.newColour(0xFF1472)),
	CRIMSON(Util.newColour(0xFF385D), Util.newColour(0xFF385D)),
	RED(Util.newColour(0xEA5D76), Util.newColour(0xDD1D40)),
	RED_DARK(Util.newColour(0xA61641), Util.newColour(0x960012)),
	RED_LIGHT(Util.newColour(0xEE95A6), Util.newColour(0xE9536F)),
	ROSE_GOLD(Util.newColour(0xE7C1BB), Util.newColour(0xE7C1BB)),
	
	TAN(Util.newColour(0xEDC491), Util.newColour(0xDC8D2E)),
	BROWN_LIGHT(Util.newColour(0xd9b6a3), Util.newColour(0xbd7e5b)),
	BROWN(Util.newColour(0xD0A38B), Util.newColour(0xB5714A)),
	BROWN_DARK(Util.newColour(0x9F775B), Util.newColour(0x785945)),
	AUBURN(Util.newColour(0xCF6654), Util.newColour(0xBB4935)),
	ORANGE_LIGHT(Util.newColour(0xffad8c), Util.newColour(0xfa743f)),
	ORANGE(Util.newColour(0xFF9970), Util.newColour(0xFA4700)),
	ORANGE_DARK(Util.newColour(0xd97c5b), Util.newColour(0xd97c5b)),
	AMBER(Util.newColour(0xFFC552), Util.newColour(0xBD7E00)),
	GINGER(Util.newColour(0xFF9147), Util.newColour(0xF06000)),
	COPPER(Util.newColour(0xD46F2B), Util.newColour(0xB96227)),
	BRONZE(Util.newColour(0xCD7F32), Util.newColour(0xCD7F32)),
	BRASS(Util.newColour(0xab8317), Util.newColour(0xab8317)),
	
	GOLD(Util.newColour(0xFFCC00), Util.newColour(0xCCA300)),
	YELLOW(Util.newColour(0xECEC5B), Util.newColour(0xC4C700)),
	YELLOW_LIGHT(Util.newColour(0xF8F8B9), Util.newColour(0xC1A42F)),
	YELLOW_PALE(Util.newColour(0xf8f8d9), Util.newColour(0xd9bb41)),
	
	GREEN_LIME(Util.newColour(0xB4D987), Util.newColour(0x83BE3C)),
	GREEN_LIGHT(Util.newColour(0x8fefbf), Util.newColour(0x1DB96B)),
	GREEN(Util.newColour(0x57DB7E), Util.newColour(0x0D683B)),
	GREEN_DARK(Util.newColour(0x209746), Util.newColour(0x209746)),
	
	AQUA(Util.newColour(0x61FFFF), Util.newColour(0x009999)), // Ne, Kazuma...
	TEAL(Util.newColour(0x6CBCB1), Util.newColour(0x439389)),
	PERIWINKLE(Util.newColour(0xCCCCFF), Util.newColour(0xCCCCFF)),
	BLUE_LIGHT(Util.newColour(0x99EBFF), Util.newColour(0x00A7D1)),
	BLUE(Util.newColour(0x05CDFF), Util.newColour(0x00627A)),
	BLUE_DARK(Util.newColour(0x0090BF), Util.newColour(0x002E43)),
	BLUE_STEEL(Util.newColour(0xA7B7D2), Util.newColour(0x5671A4)),
	BLUE_PALE(Util.newColour(0xd9f8ff), Util.newColour(0x69bcd1)),

	GREY_LIGHT(Util.newColour(0xd3d3d3), Util.newColour(0x999999)),
	GREY(Util.newColour(0xB3B3B3), Util.newColour(0x777777)),
	GREY_DARK(Util.newColour(0x999999), Util.newColour(0x515151)),
	PITCH_BLACK(Util.newColour(0x222222), Util.newColour(0x222222)),
	BLACK(Util.newColour(0x777777), Util.newColour(0x1F1F1F));
	
	private Color colour, lightColour;

	private BaseColour(Color colour, Color lightColour) {
		this.colour = colour;
		this.lightColour = lightColour;
	}

	public Color getColour() {
		return colour;
	}

	public Color getLightColour() {
		return lightColour;
	}
	
	public String toWebHexString() {
		return getColour().toString().substring(2, 8);
	}
	
	/**
	 * Shades goes from dark to light. So shades[0] is the darkest.
	 * length 5
	 */
	public String[] getShades() {
		String[] shadesString = new String[5];
		float luminosity = -0.3f;
		int red = Integer.parseInt(colour.toString().substring(2, 4), 16);
		int gre = Integer.parseInt(colour.toString().substring(4, 6), 16);
		int blu = Integer.parseInt(colour.toString().substring(6, 8), 16);
		int r, g, b;

		for (int i = 0; i < 5; i++) {
			r = red + (int)(red * (i * 0.15f + luminosity));
			r = Math.max(Math.min(r, 255), 0);

			g = gre + (int)(gre * (i * 0.15f + luminosity));
			g = Math.max(Math.min(g, 255), 0);

			b = blu + (int)(blu * (i * 0.15f + luminosity));
			b = Math.max(Math.min(b, 255), 0);

			shadesString[i] = String.format("#%02X%02X%02X", r, g, b);
		}

		return shadesString;
	}
}
