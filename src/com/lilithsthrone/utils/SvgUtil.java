package com.lilithsthrone.utils;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.inventory.ColourReplacement;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.utils.colours.BaseColour;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Put any static util methods related to .svg files in here.
 * 
 * @since 0.2.12
 * @version 0.3.7.7
 * @author Innoxia
 */
public class SvgUtil {

	public static String colourReplacementPattern(String gradientReplacementID, List<Colour> patternColours, List<ColourReplacement> patternColourReplacements, String inputString) {
		String s = inputString;
	
		StringBuilder idReplacement = new StringBuilder(gradientReplacementID);
		for(Colour c : patternColours) {
			idReplacement.append(c.getId());
		}
		
		s = s.replaceAll("linearGradient\\d|innoGrad\\d|radialGradient\\d",
				idReplacement.toString() + "$0");

		s = sanitizeImageString(s, false);

		for(int i=0; i<patternColourReplacements.size(); i++) {
			List<String> replacements = patternColourReplacements.get(i).getColourReplacements();
			String[] shades;
			if(i<patternColours.size()) {
				shades = patternColours.get(i).getShades(replacements.size());
			} else {
				shades = patternColourReplacements.get(i).getFirstOfDefaultColours().getShades(replacements.size());
			}
			
			for(int j=0; j<replacements.size(); j++) {
				String replacementString = replacements.get(j);
				// If replacementString is in a format that gets shortened, make sure that the shortened version is replaced as well.
				// i.e. #ffaaaa is sometimes shortened to #faa, so the replacement string needs to be in the format "#ffaaaa|#faa(?!\\d)". The '(?!\\d)' part is there to make sure that other strings are not replaced, such as #faa123, #faa4ab, etc.:
				if(replacementString.charAt(1)==replacementString.charAt(2)
						&& replacementString.charAt(3)==replacementString.charAt(4)
						&& replacementString.charAt(5)==replacementString.charAt(6)) {
					replacementString = replacementString + "|#"+replacementString.charAt(1)+replacementString.charAt(3)+replacementString.charAt(5)+"(?!\\d)";
				}
				s = s.replaceAll(replacementString, shades[j]);
			}
		}
	
		return s;
	}
	
	private static final List<ColourReplacement> DEFAULT_COLOUR_REPLACEMENT_LIST;
	static {
		DEFAULT_COLOUR_REPLACEMENT_LIST = new ArrayList<>();
		DEFAULT_COLOUR_REPLACEMENT_LIST.add(new ColourReplacement(true, ColourReplacement.DEFAULT_PRIMARY_REPLACEMENTS, PresetColour.getAllPresetColours(), null));
		DEFAULT_COLOUR_REPLACEMENT_LIST.add(new ColourReplacement(true, ColourReplacement.DEFAULT_SECONDARY_REPLACEMENTS, PresetColour.getAllPresetColours(), null));
		DEFAULT_COLOUR_REPLACEMENT_LIST.add(new ColourReplacement(true, ColourReplacement.DEFAULT_TERTIARY_REPLACEMENTS, PresetColour.getAllPresetColours(), null));
		DEFAULT_COLOUR_REPLACEMENT_LIST.add(new ColourReplacement(true, ColourReplacement.DEFAULT_QUATERNARY_REPLACEMENTS, PresetColour.getAllPresetColours(), null));
	}
	
	public static String colourReplacement(String gradientReplacementID, Colour colour, String inputString) {
		return colourReplacement(gradientReplacementID, Util.newArrayListOfValues(colour), null, inputString);
	}

	public static String colourReplacement(String gradientReplacementID, Colour colour, Colour colourSecondary, Colour colourTertiary, String inputString) {
		return colourReplacement(gradientReplacementID, Util.newArrayListOfValues(colour, colourSecondary, colourTertiary), null, inputString);
	}

	public static String colourReplacement(String gradientReplacementID, Colour colour, Colour colourSecondary, Colour colourTertiary, Colour colourQuaternary, String inputString) {
		return colourReplacement(gradientReplacementID, Util.newArrayListOfValues(colour, colourSecondary, colourTertiary, colourQuaternary), null, inputString);
	}

	public static String colourReplacement(String gradientReplacementID, List<Colour> colours, List<ColourReplacement> colourReplacements, String inputString) {
		String s = inputString;
		
		if(gradientReplacementID!=null) {
			StringBuilder idReplacement = new StringBuilder(gradientReplacementID);
			for(Colour c : colours) {
				idReplacement.append(c.getId());
			}
			s = s.replaceAll("linearGradient\\d|innoGrad\\d|radialGradient\\d",
					idReplacement.toString() + "$0");
		}

		s = sanitizeImageString(s, true);
		
		if(colourReplacements==null) {
			colourReplacements = DEFAULT_COLOUR_REPLACEMENT_LIST;
		}
		
		for(int i=0; i<colourReplacements.size(); i++) {
			List<String> replacements = colourReplacements.get(i).getColourReplacements();
			String[] shades;
			if(i<colours.size()) {
				shades = colours.get(i).getShades(replacements.size());
			} else {
				shades = colourReplacements.get(i).getFirstOfDefaultColours().getShades(replacements.size());
			}
			
			for(int j=0; j<replacements.size(); j++) {
				String replacementString = replacements.get(j);
				// If replacementString is in a format that gets shortened, make sure that the shortened version is replaced as well.
				// i.e. #ffaaaa is sometimes shortened to #faa, so the replacement string needs to be in the format "#ffaaaa|#faa(?!\\d)". The '(?!\\d)' part is there to make sure that other strings are not replaced, such as #faa123, #faa4ab, etc.:
				if(replacementString.charAt(1)==replacementString.charAt(2)
						&& replacementString.charAt(3)==replacementString.charAt(4)
						&& replacementString.charAt(5)==replacementString.charAt(6)) {
					replacementString = replacementString + "|#"+replacementString.charAt(1)+replacementString.charAt(3)+replacementString.charAt(5)+"(?!\\d)";
				}
				s = s.replaceAll(replacementString.toLowerCase(), shades[j]);
			}
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

	public static String getSVGWithHandledPattern(String errorId, String s, String pattern, List<Colour> patternColours, List<ColourReplacement> patternColourReplacements) {
		if(!s.contains("patternLayer")) { // Making sure that the pattern layer exists.
			return s;
		}
		
		if(pattern == null || pattern.equals("none")) {
			return s; // No pattern - no need to adjust anything.
		}
		
		String returnable;
		
		// Locating the "patternLayer".
		int patternLayerStartIndex = s.indexOf("patternLayer");
		int patternLayerEndIndex = s.indexOf("</g>", patternLayerStartIndex);
		
		// Setting up clip mask
		String newClipMask = "<clipPath id=\"internalPatternClip\">";
		
		int firstShapeStartIndex = s.indexOf("<path", patternLayerStartIndex);
		int lastShapeEndIndex = firstShapeStartIndex;
		
		boolean continueSetUp = true;
		
		while(continueSetUp){
			int currentShapeStartIndex = s.indexOf("<path", lastShapeEndIndex);
			int currentShapeEndIndex = s.indexOf("/>", currentShapeStartIndex);
			
			if(currentShapeEndIndex > patternLayerEndIndex || currentShapeEndIndex == -1 || currentShapeStartIndex == -1) {
				continueSetUp = false;
			} else {
				newClipMask = newClipMask + s.substring(currentShapeStartIndex, currentShapeEndIndex) + " />";
				lastShapeEndIndex = currentShapeEndIndex;
			}
		}
		
		newClipMask = newClipMask + "</clipPath>";
		
		//System.out.print(newClipMask);
		
		// Adding clip mask to the returned string.
		int defIndex = s.indexOf("<defs");
		int defEndIndex;
		if (defIndex > 0) {
			// Replace defs
			defEndIndex = s.indexOf("</defs>");
			returnable = s.substring(0, defEndIndex) + newClipMask;
		} else {
			// Insert defs
			defEndIndex = s.indexOf('>') + 1;
			returnable = s.substring(0, defEndIndex) + "<defs>" + newClipMask + "</defs>";
		}
		
		// Loading pattern
		String loadedPattern;
		try {
			loadedPattern = Pattern.getPattern(pattern).getSVGString(patternColours, patternColourReplacements);
		} catch(Exception ex) {
			System.err.println("Error in pattern loading method getSVGWithHandledPattern(): "+errorId);
			return s;
		}
		// Getting shapes from the pattern
		String newPattern = "";
		
		int firstPatternShapeStartIndex = loadedPattern.indexOf("<path");
		int firstRectStartIndex = loadedPattern.indexOf("<rect");
		if((firstRectStartIndex != -1 && firstRectStartIndex < firstPatternShapeStartIndex) || firstPatternShapeStartIndex == -1) {
			firstPatternShapeStartIndex = firstRectStartIndex;
		}
		int lastPatternShapeEndIndex = firstPatternShapeStartIndex;
		
		boolean continuePatternSetUp = true;
		
		while(continuePatternSetUp){
			int currentShapeStartIndex = loadedPattern.indexOf("<path", lastPatternShapeEndIndex);
			int currentRectStartIndex = loadedPattern.indexOf("<rect", lastPatternShapeEndIndex);
			if((currentRectStartIndex != -1 && currentRectStartIndex < currentShapeStartIndex) || currentShapeStartIndex == -1) {
				currentShapeStartIndex = currentRectStartIndex;
			}
			int currentShapeEndIndex = loadedPattern.indexOf("/>", currentShapeStartIndex);
			
			if(currentShapeEndIndex == -1 || currentShapeStartIndex == -1) {
				continuePatternSetUp = false;
			} else {
				newPattern = newPattern
						+ loadedPattern.substring(currentShapeStartIndex, currentShapeEndIndex)
						+ "clip-path=\"url(#internalPatternClip)\""
						+ "/>";
				lastPatternShapeEndIndex = currentShapeEndIndex;
			}
		}

		returnable = returnable + s.substring(defEndIndex, firstShapeStartIndex)
				+ newPattern
				+ s.substring(patternLayerEndIndex);
		
		//System.out.print(returnable);
		
		return returnable;
	}
	
	private static String sanitizeImageString(String imageString, boolean sanitizeSizes) {
		String s = imageString;

		// Remove xml header from svg, if it has one
		s = s.replaceFirst("<\\?xml[^?]*\\?>", "");

		if (sanitizeSizes) {
			// Fixes issue of SVG icons overflowing:
//			s = s.replaceFirst("width=\"100%\"\\R   height=\"100%\"", "");
			s = s.replaceFirst("width=\"100%\"", "");
			s = s.replaceFirst("height=\"100%\"", "");
		}

		return s;
	}
}
