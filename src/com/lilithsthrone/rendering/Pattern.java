package com.lilithsthrone.rendering;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

public class Pattern {
	
	private static Map<String, Pattern> allPatterns;
	
	private String name;
	private String SVGString; // TODO ADD THIS
	
	static {
		allPatterns = new HashMap<>();
		
		File dir = new File("res/patterns");
		
		allPatterns.put("none", new Pattern("none")); // Adding empty pattern
		
		if(dir.exists()) {
			FilenameFilter textFilter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					String lowercaseName = name.toLowerCase();
					if (lowercaseName.endsWith(".svg")) {
						return true;
					} else {
						return false;
					}
				}
			};
			
			for(File subFile : dir.listFiles(textFilter)) {
				if (subFile.exists()) {
					try {
						String newPatternName = subFile.getName().substring(0, subFile.getName().indexOf(".svg"));
						allPatterns.put(newPatternName, new Pattern(newPatternName));
						System.out.println("Added Pattern: " + newPatternName);
						
					} catch(Exception ex) {
					}
				}
			}
		}
	}
	
	public Pattern(String name) {
		this.name = name;
	}
	
	/**
	 * Checks all found patterns and returns one if available.
	 * @param name
	 * @return
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
		return this.name.replace('_', ' ');
	}
	
}