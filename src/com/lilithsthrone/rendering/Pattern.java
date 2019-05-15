package com.lilithsthrone.rendering;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.controller.xmlParsing.XMLLoadException;
import com.lilithsthrone.controller.xmlParsing.XMLMissingTagException;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.SvgUtil;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Irbynx
 */
public class Pattern {
	
	private static Map<String, Pattern> allPatterns;
	private static Map<String, Pattern> defaultPatterns;
	
	private String name;
	private String displayName;
	
	private boolean primaryRecolourAvailable;
	private boolean secondaryRecolourAvailable;
	private boolean tertiaryRecolourAvailable;
	
	private String baseSVGString;
	private Map<Colour, Map<Colour, Map<Colour, String>>> SVGStringMap;
	
	static {
		allPatterns = new TreeMap<>();
		defaultPatterns = new TreeMap<>();
		
		File dir = new File("res/patterns");
		
		allPatterns.put("none", new Pattern("none")); // Adding empty pattern
		defaultPatterns.put("none", new Pattern("none"));
		
		if(dir.exists()) {
			FilenameFilter textFilter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					String lowercaseName = name.toLowerCase();
					if (lowercaseName.endsWith(".xml")) {
						return true;
					} else {
						return false;
					}
				}
			};
			
			for(File subFile : dir.listFiles(textFilter)) {
				if(subFile.exists()) {
					loadFromFile(subFile);
					
					
//					try {
//						String newPatternName = subFile.getName().substring(0, subFile.getName().indexOf(".svg"));
//						allPatterns.put(newPatternName, new Pattern(newPatternName));
//						if(!newPatternName.equalsIgnoreCase("rainbow")) {
//							defaultPatterns.put(newPatternName, new Pattern(newPatternName));
//						}
//						//System.out.println("Added Pattern: " + newPatternName);
//						
//					} catch(Exception ex) {
//					}
				}
			}
		}
	}
	
	public Pattern(String name) {
		this.name = name;
		SVGStringMap = new HashMap<>();
		
		baseSVGString = "";
		if(!name.equals("none")) {
			try {
				File patternFile = new File(System.getProperty("user.dir")+"/res/patterns/" + this.getName().toLowerCase() + ".svg");
				List<String> lines = Files.readAllLines(patternFile.toPath());
				StringBuilder sb = new StringBuilder();
				for(String line : lines) {
					sb.append(line);
				}
				baseSVGString = sb.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			primaryRecolourAvailable = 
					baseSVGString.contains("#f4d7d7")
					|| baseSVGString.contains("#e9afaf")
					|| baseSVGString.contains("#de8787")
					|| baseSVGString.contains("#d35f5f")
					|| baseSVGString.contains("#c83737");
			
			secondaryRecolourAvailable = 
					baseSVGString.contains("#f4e3d7")
					|| baseSVGString.contains("#e9c6af")
					|| baseSVGString.contains("#deaa87")
					|| baseSVGString.contains("#d38d5f")
					|| baseSVGString.contains("#c87137");
			
			tertiaryRecolourAvailable = 
					baseSVGString.contains("#f4eed7")
					|| baseSVGString.contains("#e9ddaf")
					|| baseSVGString.contains("#decd87")
					|| baseSVGString.contains("#d3bc5f")
					|| baseSVGString.contains("#c8ab37");
		}
		
	}
	
	private static Pattern loadFromFile(File clothingXMLFile) {
		try {
			Element patternElement = Element.getDocumentRootElement(clothingXMLFile);
			String loadedDisplayName = patternElement.getMandatoryFirstOf("name").getTextContent();
			boolean loadedDefaultPattern = Boolean.valueOf(patternElement.getMandatoryFirstOf("defaultPattern").getTextContent());
			String loadedPatternName = patternElement.getMandatoryFirstOf("patternName").getTextContent().replace(".svg", "");

			Pattern pattern = new Pattern(loadedPatternName);
			pattern.displayName = loadedDisplayName;
			
			allPatterns.put(loadedPatternName, pattern);
			if(loadedDefaultPattern) {
				defaultPatterns.put(loadedPatternName, pattern);
			}
			
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
		return allPatterns.get(name);
	}
	
	/**
	 * Returns all available patterns
	 */
	public static Map<String, Pattern> getAllPatterns() {
		return allPatterns;
	}

	/**
	 * Returns all default patterns which clothing can spawn with
	 */
	public static Map<String, Pattern> getAllDefaultPatterns() {
		return defaultPatterns;
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
	
	public boolean isPrimaryRecolourAvailable() {
		return primaryRecolourAvailable;
	}

	public boolean isSecondaryRecolourAvailable() {
		return secondaryRecolourAvailable;
	}

	public boolean isTertiaryRecolourAvailable() {
		return tertiaryRecolourAvailable;
	}

	public String getSVGString(Colour colour, Colour colourSecondary, Colour colourTertiary) {
		if(!SVGStringMap.containsKey(colour) || !SVGStringMap.get(colour).containsKey(colourSecondary) || !SVGStringMap.get(colour).get(colourSecondary).containsKey(colourTertiary)) {
			generateSVGImage(colour, colourSecondary, colourTertiary);
		}
		return SVGStringMap.get(colour).get(colourSecondary).get(colourTertiary);
	}
	
	private void generateSVGImage(Colour colour, Colour colourSecondary, Colour colourTertiary) {
		addSVGStringMapping(colour, colourSecondary, colourTertiary, SvgUtil.colourReplacementPattern(this.getName(), colour, colourSecondary, colourTertiary, baseSVGString));
	}
	
	private void addSVGStringMapping(Colour colour, Colour colourSecondary, Colour colourTertiary, String s) {
		if(SVGStringMap.get(colour)==null) {
			SVGStringMap.put(colour, new HashMap<>());
			SVGStringMap.get(colour).put(colourSecondary, new HashMap<>());
			
		} else if(SVGStringMap.get(colour).get(colourSecondary)==null) {
			SVGStringMap.get(colour).put(colourSecondary, new HashMap<>());
		}
		
		SVGStringMap.get(colour).get(colourSecondary).put(colourTertiary, s);
	}
}


// Can't find a good place to put the list for the future, so I'll put it here:
// Items supporting patterns as of the creation of the system:
//
// Bikini Bottoms
// Backless Panties
// Boxers
// Boyshorts
// Thigh highs
// Work boots
// Swimsuit
// Bikini
// Briefs
// Crotchless briefs
// Crotchless panties
// Crotchless thong
// Panties
// Vstring
// Elbow Length gloves
// Fingerless gloves
// Gloves
// Cargo trousers
// Thigh high socks
// Knee high socks
// Tshirt


