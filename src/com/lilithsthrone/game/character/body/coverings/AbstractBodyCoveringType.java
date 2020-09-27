package com.lilithsthrone.game.character.body.coverings;

import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.utils.colours.Colour;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractBodyCoveringType {

	private BodyCoveringCategory category; 
	private String determiner; 
	private String namePlural;
	private String nameSingular;
	private List<CoveringModifier> naturalModifiers;
	private List<CoveringModifier> extraModifiers;
	private List<Colour> naturalColoursPrimary;
	private List<Colour> dyeColoursPrimary;
	private List<Colour> naturalColoursSecondary;
	private List<Colour> dyeColoursSecondary;
	private List<Colour> allColours;
	private List<Colour> allPrimaryColours;
	private List<Colour> allSecondaryColours;
	private Map<CoveringPattern, Integer> naturalPatterns;
	private Map<CoveringPattern, Integer> dyePatterns;
	private Map<CoveringPattern, Integer> allPatterns;
	private boolean isDefaultPlural;
	
	public AbstractBodyCoveringType(BodyCoveringCategory category, BodyCoveringTemplate template) {
		this.category = category;
		
		determiner = template.determiner;
		namePlural = template.namePlural;
		nameSingular = template.nameSingular;
		naturalModifiers = template.naturalModifiers;
		extraModifiers = template.extraModifiers;
		naturalColoursPrimary = template.naturalColoursPrimary;
		dyeColoursPrimary = template.dyeColoursPrimary;
		naturalColoursSecondary = template.naturalColoursSecondary;
		dyeColoursSecondary = template.dyeColoursSecondary;
		naturalPatterns = template.naturalPatterns;
		dyePatterns = template.dyePatterns;
		isDefaultPlural = template.isDefaultPlural;
		
		allPatterns = new HashMap<>();
		for(Entry<CoveringPattern, Integer> entry : this.naturalPatterns.entrySet()) {
			this.allPatterns.put(entry.getKey(), entry.getValue());
		}
		if(dyePatterns!=null) {
			for(Entry<CoveringPattern, Integer> entry : this.dyePatterns.entrySet()) {
				this.allPatterns.put(entry.getKey(), entry.getValue());
			}
		}
		
		allColours = new ArrayList<>();
		allPrimaryColours = new ArrayList<>();
		allSecondaryColours = new ArrayList<>();
		
		for(Colour c : this.naturalColoursPrimary) {
			allColours.add(c);
			allPrimaryColours.add(c);
		}
		for(Colour c : this.dyeColoursPrimary) {
			if(!allColours.contains(c)) {
				allColours.add(c);
			}
			if(!allPrimaryColours.contains(c)) {
				allPrimaryColours.add(c);
			}
		}
		for(Colour c : this.naturalColoursSecondary) {
			allColours.add(c);
			allSecondaryColours.add(c);
		}
		for(Colour c : this.dyeColoursSecondary) {
			if(!allColours.contains(c)) {
				allColours.add(c);
			}
			if(!allSecondaryColours.contains(c)) {
				allSecondaryColours.add(c);
			}
		}
	}

	public AbstractBodyCoveringType(BodyCoveringCategory category,
			String determiner,
			boolean isDefaultPlural,
			String namePlural,
			String nameSingular,
			List<CoveringModifier> naturalModifiers,
			List<CoveringModifier> extraModifiers,
			Map<CoveringPattern, Integer> naturalPatterns,
			Map<CoveringPattern, Integer> dyePatterns,
			List<Colour> naturalColoursPrimary,
			List<Colour> dyeColoursPrimary,
			List<Colour> naturalColoursSecondary,
			List<Colour> dyeColoursSecondary) {
		this.category = category;
		
		this.determiner = determiner;
		this.namePlural = namePlural;
		this.nameSingular=nameSingular;
		this.isDefaultPlural = isDefaultPlural;
		
		if(naturalModifiers == null) {
			this.naturalModifiers = new ArrayList<>();
		} else {
			this.naturalModifiers = naturalModifiers;
		}
		
		if(extraModifiers == null) {
			this.extraModifiers = new ArrayList<>();
		} else {
			this.extraModifiers = extraModifiers;
		}
		
		if(naturalPatterns == null) {
			this.naturalPatterns = new HashMap<>();
			this.naturalPatterns.put(CoveringPattern.NONE, 1);
		} else {
			this.naturalPatterns = naturalPatterns;
		}

		this.dyePatterns = new HashMap<>();
		if(dyePatterns != null) {
			this.dyePatterns = new HashMap<>();
			for(Entry<CoveringPattern, Integer> entry : dyePatterns.entrySet()) {
				if(!this.naturalPatterns.containsKey(entry.getKey())) {
					this.dyePatterns.put(entry.getKey(), entry.getValue());
				}
			}
		}
		
		allPatterns = new HashMap<>();
		if(naturalPatterns == null) {
			allPatterns.put(CoveringPattern.NONE, 1);
		} else {
			for(Entry<CoveringPattern, Integer> entry : this.naturalPatterns.entrySet()) {
				this.allPatterns.put(entry.getKey(), entry.getValue());
			}
		}
		
		if(dyePatterns != null) {
			for(Entry<CoveringPattern, Integer> entry : this.dyePatterns.entrySet()) {
				if(!this.allPatterns.containsKey(entry.getKey())) {
					this.allPatterns.put(entry.getKey(), entry.getValue());
				}
			}
		}
		
		
		if(naturalColoursPrimary == null) {
			this.naturalColoursPrimary = new ArrayList<>();
		} else {
			this.naturalColoursPrimary = naturalColoursPrimary;
		}
		if(dyeColoursPrimary == null) {
			this.dyeColoursPrimary = new ArrayList<>();
		} else {
			this.dyeColoursPrimary = dyeColoursPrimary;
		}
		
		if(naturalColoursSecondary == null) {
			this.naturalColoursSecondary = new ArrayList<>();
		} else {
			this.naturalColoursSecondary = naturalColoursSecondary;
		}
		if(dyeColoursSecondary == null) {
			this.dyeColoursSecondary = new ArrayList<>();
		} else {
			this.dyeColoursSecondary = dyeColoursSecondary;
		}
		
		allColours = new ArrayList<>();
		allPrimaryColours = new ArrayList<>();
		allSecondaryColours = new ArrayList<>();
		for(Colour c : this.naturalColoursPrimary) {
			allColours.add(c);
			allPrimaryColours.add(c);
		}
		for(Colour c : this.dyeColoursPrimary) {
			if(!allColours.contains(c)) {
				allColours.add(c);
			}
			if(!allPrimaryColours.contains(c)) {
				allPrimaryColours.add(c);
			}
		}
		for(Colour c : this.naturalColoursSecondary) {
			allColours.add(c);
			allSecondaryColours.add(c);
		}
		for(Colour c : this.dyeColoursSecondary) {
			if(!allColours.contains(c)) {
				allColours.add(c);
			}
			if(!allSecondaryColours.contains(c)) {
				allSecondaryColours.add(c);
			}
		}
	}

	@Override
	public String toString() {
		new AccessException("WARNING: AbstractBodyCoveringType is calling toString()!").printStackTrace(System.err);
		return BodyCoveringType.getIdFromBodyCoveringType(this);
	}
	
	public BodyCoveringCategory getCategory() {
		return category;
	}

	public String getDeterminer(GameCharacter gc) {
		return determiner;
	}

	public boolean isDefaultPlural() {
		return isDefaultPlural;
	}
	
	public String getNameSingular(GameCharacter gc) {
		return nameSingular;
	}
	
	public String getNamePlural(GameCharacter gc) {
		return namePlural;
	}
	
	public String getName(GameCharacter gc){
		if(isDefaultPlural()) {
			return getNamePlural(gc);
		} else {
			return getNameSingular(gc);
		}
	}
	
	public Map<CoveringPattern, Integer> getNaturalPatterns() {
		return naturalPatterns;
	}

	public Map<CoveringPattern, Integer> getDyePatterns() {
		return dyePatterns;
	}

	public Map<CoveringPattern, Integer> getAllPatterns() {
		return allPatterns;
	}
	
	public List<Colour> getNaturalColoursPrimary() {
		return naturalColoursPrimary;
	}

	public List<Colour> getDyeColoursPrimary() {
		return dyeColoursPrimary;
	}

	public List<Colour> getNaturalColoursSecondary() {
		return naturalColoursSecondary;
	}

	public List<Colour> getDyeColoursSecondary() {
		return dyeColoursSecondary;
	}

	public List<Colour> getAllColours() {
		return allColours;
	}
	
	public List<Colour> getAllPrimaryColours() {
		return allPrimaryColours;
	}
	
	public List<Colour> getAllSecondaryColours() {
		return allSecondaryColours;
	}

//	public AbstractBodyCoveringType getBodyCoveringType() {
//		return this;
//	}

	public List<CoveringModifier> getNaturalModifiers() {
		return naturalModifiers;
	}

	public List<CoveringModifier> getExtraModifiers() {
		return extraModifiers;
	}
}
