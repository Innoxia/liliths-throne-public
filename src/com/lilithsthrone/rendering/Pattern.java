package com.lilithsthrone.rendering;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.controller.xmlParsing.XMLLoadException;
import com.lilithsthrone.controller.xmlParsing.XMLMissingTagException;
import com.lilithsthrone.game.inventory.ColourReplacement;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;

/**
 * 
 * Can't find a good place to put the list for the future, so I'll put it here:<br/>
 * Items supporting patterns as of the creation of the system:<br/>
 * <i>
 * Bikini Bottoms<br/>
 * Backless Panties<br/>
 * Boxers<br/>
 * Boyshorts<br/>
 * Thigh highs<br/>
 * Work boots<br/>
 * Swimsuit<br/>
 * Bikini<br/>
 * Briefs<br/>
 * Crotchless briefs<br/>
 * Crotchless panties<br/>
 * Crotchless thong<br/>
 * Panties<br/>
 * Vstring<br/>
 * Elbow Length gloves<br/>
 * Fingerless gloves<br/>
 * Gloves<br/>
 * Cargo trousers<br/>
 * Thigh high socks<br/>
 * Knee high socks<br/>
 * Tshirt<br/>
 * </i>
 * @since 0.2.6
 * @version 0.3.9.8
 * @author Irbynx, Innoxia, AceXp
 */
public class Pattern {
	
	private static Map<String, Pattern> allPatterns;
	private static Map<String, Pattern> defaultPatterns;
	
	private String name;
	private String displayName;
	
	private String baseSVGString;
	private Map<String, String> SVGStringMap;
	
	static {
		allPatterns = new TreeMap<>();
		defaultPatterns = new TreeMap<>();

		allPatterns.put("none", new Pattern("none", null)); // Adding empty pattern
		defaultPatterns.put("none", new Pattern("none", null));
		
		// Modded patterns:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/items/patterns");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					loadFromFile(innerEntry.getKey(), innerEntry.getValue());
				} catch(Exception ex) {
					System.err.println("Loading modded pattern failed at 'Pattern'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res patterns:

		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/patterns");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					loadFromFile(innerEntry.getKey(), innerEntry.getValue());
//					System.out.println("P: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading pattern failed at 'Pattern'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	public Pattern(String name, File xmlFile) {
		this.name = name;
		SVGStringMap = new HashMap<>();
		
		baseSVGString = "";
		if(!name.equals("none")) {
			try {
				String fileName = xmlFile.getPath().replaceAll("xml","svg");
				File patternFile = new File(fileName);
				List<String> lines = Files.readAllLines(patternFile.toPath());
				StringBuilder sb = new StringBuilder();
				for(String line : lines) {
					sb.append(line);
				}
				baseSVGString = sb.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static Pattern loadFromFile(String id, File clothingXMLFile) {
		try {
			Element patternElement = Element.getDocumentRootElement(clothingXMLFile);
			String loadedDisplayName = patternElement.getMandatoryFirstOf("name").getTextContent();
			boolean loadedDefaultPattern = Boolean.valueOf(patternElement.getMandatoryFirstOf("defaultPattern").getTextContent());
			String loadedPatternName = patternElement.getMandatoryFirstOf("patternName").getTextContent().replace(".svg", "");

			Pattern pattern = new Pattern(loadedPatternName, clothingXMLFile);
			pattern.displayName = loadedDisplayName;
			
			allPatterns.put(id, pattern);
			if(loadedDefaultPattern) {
				defaultPatterns.put(id, pattern);
			}
			
//			allPatterns.put(loadedPatternName, pattern);
//			if(loadedDefaultPattern) {
//				defaultPatterns.put(loadedPatternName, pattern);
//			}
			
			return pattern;
			
		} catch (XMLLoadException e) {
			e.printStackTrace();
		} catch (XMLMissingTagException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/**
	 * Checks all found patterns and returns one if available.
	 */
	public static Pattern getPattern(String name) {
		if(name.equals("cow_patterned")
				|| name.equals("tiger_striped")
				|| name.equals("horizontally_tiger_striped")
				|| name.equals("leopard_printed")
				|| name.equals("multi_camo")
				|| name.equals("rainbow")
				|| name.equals("polka_dots_big")
				|| name.equals("camo")
				|| name.equals("polka_dots_small")
				|| name.equals("polka_dots_tiny")) {
			name = "irbynx_"+name;
			
		} else if(name.equals("urban_splinter_camo")) {
			name = "dsg_"+name;
		}
		
		return allPatterns.get(name);
	}

	/**
	 * Returns all available patterns
	 */
	public static List<Pattern> getAllPatterns() {
		return new ArrayList<>(allPatterns.values());
	}

	/**
	 * Returns all default patterns which clothing can spawn with
	 */
	public static List<Pattern> getAllDefaultPatterns() {
		return new ArrayList<>(defaultPatterns.values());
	}
	
	/**
	 * Returns name of the pattern
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns human readable name.
	 * @return
	 */
	public String getNiceName() {
		if(displayName!=null && displayName.length()>0) {
			return displayName;
		}
		return this.name.replace('_', ' ');
	}
	
	public boolean isRecolourAvailable(ColourReplacement colourReplacement) {
		for(String s : colourReplacement.getColourReplacements()) {
			if(baseSVGString.contains(s)) {
				return true;
			}
		}
		return false;
	}

	private String generateIdentifier(List<Colour> colours) {
		StringBuilder sb = new StringBuilder(this.getName());
		for(Colour c : colours) {
			sb.append(c.getId());
		}
		return sb.toString();
	}
	
	public String getSVGString(List<Colour> patternColours, List<ColourReplacement> patternColourReplacements) {
		if(!SVGStringMap.containsKey(generateIdentifier(patternColours))) {
			generateSVGImage(patternColours, patternColourReplacements);
		}
		return SVGStringMap.get(generateIdentifier(patternColours));
	}
	
	private void generateSVGImage(List<Colour> patternColours, List<ColourReplacement> patternColourReplacements) {
		SVGStringMap.put(generateIdentifier(patternColours), SvgUtil.colourReplacementPattern(this.getName(), patternColours, patternColourReplacements, baseSVGString));
	}
}


