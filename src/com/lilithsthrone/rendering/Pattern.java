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
import com.lilithsthrone.game.inventory.ColourReplacement;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.colours.Colour;

/**
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

		File dir = new File("res/mods");

		if (dir.exists() && dir.isDirectory()) {
			File[] modDirectoryListing = dir.listFiles();
			if (modDirectoryListing != null) {
				for (File modAuthorDirectory : modDirectoryListing) {
					File modAuthorPatternDirectory = new File(modAuthorDirectory.getAbsolutePath()+"/items/patterns");
					FilenameFilter textFilter = (dir1, name) -> {
						String lowercaseName = name.toLowerCase();
						if (lowercaseName.endsWith(".xml")) {
							return true;
						} else {
							return false;
						}
					};
					File[] patternFilesListing = modAuthorPatternDirectory.listFiles(textFilter);
					if (patternFilesListing != null) {
						for (File patternFile : patternFilesListing) {
							try {
								if(patternFile.exists()) {
									loadFromFile(patternFile);
								}
							} catch(Exception ex) {
								System.err.println("Loading modded pattern failed in 'mod folder patterns'. File path: "+patternFile.getAbsolutePath());
							}
						}
					}
				}
			}
		}

		dir = new File("res/patterns");
		
		if(dir.exists()) {
			FilenameFilter textFilter = (dir1, name) -> {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".xml")) {
					return true;
				} else {
					return false;
				}
			};
			
			for(File subFile : dir.listFiles(textFilter)) {
				if(subFile.exists()) {
					loadFromFile(subFile);
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
	
	private static Pattern loadFromFile(File clothingXMLFile) {
		try {
			Element patternElement = Element.getDocumentRootElement(clothingXMLFile);
			String loadedDisplayName = patternElement.getMandatoryFirstOf("name").getTextContent();
			boolean loadedDefaultPattern = Boolean.valueOf(patternElement.getMandatoryFirstOf("defaultPattern").getTextContent());
			String loadedPatternName = patternElement.getMandatoryFirstOf("patternName").getTextContent().replace(".svg", "");

			Pattern pattern = new Pattern(loadedPatternName, clothingXMLFile);
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


