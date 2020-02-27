package com.lilithsthrone.utils;

/**
 * Put any static util methods related to .svg files in here.
 *
 * @since 0.2.12
 * @version 0.2.12
 * @author Innoxia
 */
public class SvgUtil {

	public static String colourReplacementPattern(String gradientReplacementID, Colour colour, Colour colourSecondary, Colour colourTertiary, String inputString) {
		String s = inputString;

		s = s.replaceAll("linearGradient\\d|innoGrad\\d|radialGradient\\d",
				gradientReplacementID + colour.toString() + (colourSecondary!=null?colourSecondary.toString():"") + (colourTertiary!=null?colourTertiary.toString():"") + "$0");

		s = sanitizeImageString(s, false);

		if(colour!=null) {
			s = s.replaceAll("#f4d7d7", colour.getShades()[0]);
			s = s.replaceAll("#e9afaf", colour.getShades()[1]);
			s = s.replaceAll("#de8787", colour.getShades()[2]);
			s = s.replaceAll("#d35f5f", colour.getShades()[3]);
			s = s.replaceAll("#c83737", colour.getShades()[4]);
		}

		if(colourSecondary!=null) {
			s = s.replaceAll("#f4e3d7", colourSecondary.getShades()[0]);
			s = s.replaceAll("#e9c6af", colourSecondary.getShades()[1]);
			s = s.replaceAll("#deaa87", colourSecondary.getShades()[2]);
			s = s.replaceAll("#d38d5f", colourSecondary.getShades()[3]);
			s = s.replaceAll("#c87137", colourSecondary.getShades()[4]);
		}

		if(colourTertiary!=null) {
			s = s.replaceAll("#f4eed7", colourTertiary.getShades()[0]);
			s = s.replaceAll("#e9ddaf", colourTertiary.getShades()[1]);
			s = s.replaceAll("#decd87", colourTertiary.getShades()[2]);
			s = s.replaceAll("#d3bc5f", colourTertiary.getShades()[3]);
			s = s.replaceAll("#c8ab37", colourTertiary.getShades()[4]);
		}

		return s;
	}

	public static String colourReplacement(String gradientReplacementID, Colour colour, String inputString) {
		return colourReplacement(gradientReplacementID, colour, null, null, null, inputString);
	}
	
	public static String colourReplacement(String gradientReplacementID, Colour colour, Colour colourSecondary, Colour colourTertiary, String inputString) {
		return colourReplacement(gradientReplacementID, colour, colourSecondary, colourTertiary, null, inputString);
	}

	public static String colourReplacement(String gradientReplacementID, Colour colour, Colour colourSecondary, Colour colourTertiary, Colour colourQuaternary, String inputString) {
		String s = inputString;
		
		if(gradientReplacementID!=null) {
			s = s.replaceAll("linearGradient\\d|innoGrad\\d|radialGradient\\d",
					gradientReplacementID + colour.toString() + (colourSecondary!=null?colourSecondary.toString():"") + (colourTertiary!=null?colourTertiary.toString():"") + (colourQuaternary!=null?colourQuaternary.toString():"") + "$0");
		}

		s = sanitizeImageString(s, true);

		if(colour!=null) {
			s = s.replaceAll("#ff2a2a", colour.getShades()[0]);
			s = s.replaceAll("#ff5555|#f55(?!\\d)", colour.getShades()[1]);
			s = s.replaceAll("#ff8080", colour.getShades()[2]);
			s = s.replaceAll("#ffaaaa|#faa(?!\\d)", colour.getShades()[3]);
			s = s.replaceAll("#ffd5d5", colour.getShades()[4]);
		}

		if(colourSecondary!=null) {
			s = s.replaceAll("#ff7f2a", colourSecondary.getShades()[0]);
			s = s.replaceAll("#ff9955|#f95(?!\\d)", colourSecondary.getShades()[1]);
			s = s.replaceAll("#ffb380", colourSecondary.getShades()[2]);
			s = s.replaceAll("#ffccaa|#fca(?!\\d)", colourSecondary.getShades()[3]);
			s = s.replaceAll("#ffe6d5", colourSecondary.getShades()[4]);
		}

		if(colourTertiary!=null) {
			s = s.replaceAll("#ffd42a", colourTertiary.getShades()[0]);
			s = s.replaceAll("#ffdd55|#fd5(?!\\d)", colourTertiary.getShades()[1]);
			s = s.replaceAll("#ffe680", colourTertiary.getShades()[2]);
			s = s.replaceAll("#ffeeaa|#fea(?!\\d)", colourTertiary.getShades()[3]);
			s = s.replaceAll("#fff6d5", colourTertiary.getShades()[4]);
		}
		
		if(colourQuaternary!=null) {
			s = s.replaceAll("#abc837", colourQuaternary.getShades()[0]);
			s = s.replaceAll("#bcd35f", colourQuaternary.getShades()[1]);
			s = s.replaceAll("#cdde87", colourQuaternary.getShades()[2]);
			s = s.replaceAll("#dde9af", colourQuaternary.getShades()[3]);
			s = s.replaceAll("#eef4d7", colourQuaternary.getShades()[4]);
		}
		
		return s;
	}



	public static String colourReplacement(String gradientReplacementID, BaseColour colour, String inputString) {
		return colourReplacement(gradientReplacementID, colour, null, null, inputString);
	}

	public static String colourReplacement(String gradientReplacementID, BaseColour colour, BaseColour colourSecondary, BaseColour colourTertiary, String inputString) {
		String s = inputString;

		if(gradientReplacementID!=null) {
			s = s.replaceAll("linearGradient\\d|innoGrad\\d|radialGradient\\d",
					gradientReplacementID + colour.toString() + (colourSecondary!=null?colourSecondary.toString():"") + (colourTertiary!=null?colourTertiary.toString():"") + "$0");
		}

		s = sanitizeImageString(s, true);

		if(colour!=null) {
			s = s.replaceAll("#ff2a2a", colour.getShades()[0]);
			s = s.replaceAll("#ff5555|#f55(?!\\d)", colour.getShades()[1]);
			s = s.replaceAll("#ff8080", colour.getShades()[2]);
			s = s.replaceAll("#ffaaaa|#faa(?!\\d)", colour.getShades()[3]);
			s = s.replaceAll("#ffd5d5", colour.getShades()[4]);
		}

		if(colourSecondary!=null) {
			s = s.replaceAll("#ff7f2a", colourSecondary.getShades()[0]);
			s = s.replaceAll("#ff9955|#f95(?!\\d)", colourSecondary.getShades()[1]);
			s = s.replaceAll("#ffb380", colourSecondary.getShades()[2]);
			s = s.replaceAll("#ffccaa|#fca(?!\\d)", colourSecondary.getShades()[3]);
			s = s.replaceAll("#ffe6d5", colourSecondary.getShades()[4]);
		}

		if(colourTertiary!=null) {
			s = s.replaceAll("#ffd42a", colourTertiary.getShades()[0]);
			s = s.replaceAll("#ffdd55|#fd5(?!\\d)", colourTertiary.getShades()[1]);
			s = s.replaceAll("#ffe680", colourTertiary.getShades()[2]);
			s = s.replaceAll("#ffeeaa|#fea(?!\\d)", colourTertiary.getShades()[3]);
			s = s.replaceAll("#fff6d5", colourTertiary.getShades()[4]);
		}

		return s;
	}


	public static String colourReplacement(String gradientReplacementID, String colour, String inputString) {
		String s = inputString;

		if(gradientReplacementID!=null) {
			s = s.replaceAll("linearGradient\\d|innoGrad\\d|radialGradient\\d",
					gradientReplacementID + colour.toString() + "$0");
		}

		s = sanitizeImageString(s, true);

		if(colour!=null) {
			s = s.replaceAll("#ff2a2a", colour);
			s = s.replaceAll("#ff5555|#f55(?!\\d)", colour);
			s = s.replaceAll("#ff8080", colour);
			s = s.replaceAll("#ffaaaa|#faa(?!\\d)", colour);
			s = s.replaceAll("#ffd5d5", colour);
		}

		return s;
	}

	private static String sanitizeImageString(String imageString, boolean sanitizeSizes) {
		String s = imageString;

		// Remove xml header from svg, if it has one
		s = s.replaceFirst("<\\?xml[^?]*\\?>", "");

		if (sanitizeSizes) {
			// Fixes issue of SVG icons overflowing:
			s = s.replaceFirst("width=\"100%\"\\R   height=\"100%\"", "");
		}

		return s;
	}
}
