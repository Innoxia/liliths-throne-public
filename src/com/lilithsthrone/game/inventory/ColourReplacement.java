package com.lilithsthrone.game.inventory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.ColourListPresets;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.7.7
 * @version 0.3.7.7
 * @author Innoxia
 */
public class ColourReplacement {

	public static final Colour DEFAULT_COLOUR_VALUE = PresetColour.CLOTHING_BLACK;

	public static final List<String> DEFAULT_PRIMARY_REPLACEMENTS = Util.newArrayListOfValues("#ff2a2a", "#ff5555", "#ff8080", "#ffaaaa", "#ffd5d5");
	public static final List<String> DEFAULT_SECONDARY_REPLACEMENTS = Util.newArrayListOfValues("#ff7f2a", "#ff9955", "#ffb380", "#ffccaa", "#ffe6d5");
	public static final List<String> DEFAULT_TERTIARY_REPLACEMENTS = Util.newArrayListOfValues("#ffd42a", "#ffdd55", "#ffe680", "#ffeeaa", "#fff6d5");
	public static final List<String> DEFAULT_QUATERNARY_REPLACEMENTS = Util.newArrayListOfValues("#abc837", "#bcd35f", "#cdde87", "#dde9af", "#eef4d7");
	
	public static final List<String> DEFAULT_PATTERN_PRIMARY_REPLACEMENTS = Util.newArrayListOfValues("#c83737", "#d35f5f", "#de8787", "#e9afaf", "#f4d7d7");
	public static final List<String> DEFAULT_PATTERN_SECONDARY_REPLACEMENTS = Util.newArrayListOfValues("#c87137", "#d38d5f", "#deaa87", "#e9c6af", "#f4e3d7");
	public static final List<String> DEFAULT_PATTERN_TERTIARY_REPLACEMENTS = Util.newArrayListOfValues("#c8ab37", "#d3bc5f", "#decd87", "#e9ddaf", "#f4eed7");
	
	private boolean recolouringAllowed;
	private List<String> colourReplacements;
	private List<Colour> defaultColours;
	private List<Colour> extraColours;
	private List<Colour> allColours;
	
	public ColourReplacement(boolean recolouringAllowed, List<String> colourReplacements, List<Colour> defaultColours, List<Colour> extraColours) {
		this.recolouringAllowed = recolouringAllowed;
		
		this.colourReplacements = new ArrayList<>(colourReplacements);
		
		// This is all so that colours are displayed in the correct gradient order when retrieved:
		
		this.defaultColours = new ArrayList<>();
		if (defaultColours == null) {
			this.defaultColours.add(DEFAULT_COLOUR_VALUE);
		} else {
			this.defaultColours.addAll(defaultColours);
		}

		
		this.extraColours = new ArrayList<>();
		if (extraColours != null) {
			this.extraColours.addAll(extraColours);
		}

		Set<Colour> colourSet = new HashSet<>();
		this.allColours = new ArrayList<>(ColourListPresets.DEBUG_ALL);
		if(defaultColours!=null) {
			colourSet.addAll(defaultColours);
		}
		if(extraColours!=null) {
			colourSet.addAll(extraColours);
		}
		this.allColours.removeIf((c)->!colourSet.contains(c));
		for(Colour c : colourSet) {
			if(!this.allColours.contains(c)) {
				this.allColours.add(c);
			}
		}
		// Remove duplicate lime green from index 0:
		if(allColours.stream().filter(c->c==PresetColour.CLOTHING_GREEN_LIME).count()>1) {
			allColours.remove(PresetColour.CLOTHING_GREEN_LIME);
		}
	}

	public boolean isRecolouringAllowed() {
		return recolouringAllowed;
	}

	public List<String> getColourReplacements() {
		return colourReplacements;
	}
	
	// Default colours:
	
	public List<Colour> getDefaultColours() {
		return defaultColours;
	}
	
	public Colour getFirstOfDefaultColours() {
		if(getDefaultColours()==null || getDefaultColours().isEmpty()) {
			return DEFAULT_COLOUR_VALUE;
		}
		return getDefaultColours().get(0);
	}
	
	public Colour getRandomOfDefaultColours() {
		if(getDefaultColours()==null || getDefaultColours().isEmpty()) {
			return DEFAULT_COLOUR_VALUE;
		}
		return Util.randomItemFrom(getDefaultColours());
	}

	// Extra colours:
	
	public List<Colour> getExtraColours() {
		return extraColours;
	}
	
	public Colour getFirstOfExtraColours() {
		if(getExtraColours()==null || getExtraColours().isEmpty()) {
			return DEFAULT_COLOUR_VALUE;
		}
		return getExtraColours().get(0);
	}
	
	public Colour getRandomOfExtraColours() {
		if(getExtraColours()==null || getExtraColours().isEmpty()) {
			return DEFAULT_COLOUR_VALUE;
		}
		return Util.randomItemFrom(getExtraColours());
	}

	// All colours:
	
	public List<Colour> getAllColours() {
		return allColours;
	}
	
	public Colour getFirstOfAllColours() {
		if(getAllColours()==null || getAllColours().isEmpty()) {
			return DEFAULT_COLOUR_VALUE;
		}
		return getAllColours().get(0);
	}
	
	public Colour getRandomOfAllColours() {
		if(getAllColours()==null || getAllColours().isEmpty()) {
			return DEFAULT_COLOUR_VALUE;
		}
		return Util.randomItemFrom(getAllColours());
	}
	
}
