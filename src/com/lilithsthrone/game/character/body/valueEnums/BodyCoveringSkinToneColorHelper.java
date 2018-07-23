package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

public class BodyCoveringSkinToneColorHelper {
	private static final List<BodyCoveringType> NOT_FOR_THESE_BCTS = 
			Util.newArrayListOfValues(BodyCoveringType.MAKEUP_BLUSHER,
					BodyCoveringType.MAKEUP_EYE_LINER,
					BodyCoveringType.MAKEUP_EYE_SHADOW,
					BodyCoveringType.MAKEUP_LIPSTICK,
					BodyCoveringType.MAKEUP_NAIL_POLISH_FEET,
					BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS);
			
	
	private static class FilteredColours {
		private List<Colour> primary;
		private List<Colour> secondary;
		
		public FilteredColours(List<Colour> primary, List<Colour> secondary) {
			this.primary = primary;
			this.secondary = secondary;
		}

		public List<Colour> getPrimary() {
			return primary;
		}

		public List<Colour> getSecondary() {
			return secondary;
		}
		
		
	}
	
	private static Map<StartingSkinTone, Map<BodyCoveringType, FilteredColours>> filteredColours = new EnumMap<>(StartingSkinTone.class);
	
	private BodyCoveringSkinToneColorHelper() {
		//singleton via statics
	}
	
	public static List<Colour> getAcceptableColoursForPrimary(StartingSkinTone tone, BodyCoveringType bct) {
		return getOrCreateFilteredColoursForCombination(tone, bct).getPrimary();
	}
	
	public static List<Colour> getAcceptableColoursForSecondary(StartingSkinTone tone, BodyCoveringType bct) {
		return getOrCreateFilteredColoursForCombination(tone, bct).getSecondary();
	}
	
	private static FilteredColours getOrCreateFilteredColoursForCombination(StartingSkinTone tone, BodyCoveringType bct) {
		if (NOT_FOR_THESE_BCTS.contains(bct)) {
			return new FilteredColours(new ArrayList<>(), new ArrayList<>());
		}
		return filteredColours.computeIfAbsent(tone, ignored -> new EnumMap<>(BodyCoveringType.class)).computeIfAbsent(bct, ignored -> {
			Set<Colour> colourApplicationListPrimary = new HashSet<>();

			colourApplicationListPrimary.addAll(bct.getNaturalColoursPrimary());
			colourApplicationListPrimary.retainAll(tone.getAssociatedColours());
			if(colourApplicationListPrimary.isEmpty()) {
				colourApplicationListPrimary.addAll(bct.getNaturalColoursPrimary());
			}
			
			Set<Colour> colourApplicationListSecondary = new HashSet<>();

			colourApplicationListSecondary.addAll(bct.getNaturalColoursSecondary());
			colourApplicationListSecondary.retainAll(tone.getAssociatedColours());
			if(colourApplicationListSecondary.isEmpty()) {
				colourApplicationListSecondary.addAll(bct.getNaturalColoursSecondary());
			}
			return new FilteredColours(new ArrayList<>(colourApplicationListPrimary), new ArrayList<>(colourApplicationListSecondary));
		});
	}
}
