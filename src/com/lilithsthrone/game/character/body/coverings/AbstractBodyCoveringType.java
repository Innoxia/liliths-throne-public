package com.lilithsthrone.game.character.body.coverings;

import java.io.File;
import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.ColourListPresets;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.4
 * @version 0.4.0
 * @author Innoxia
 */
public abstract class AbstractBodyCoveringType {

	private boolean mod;
	private boolean fromExternalFile;
	
	private BodyCoveringCategory category;
	private String nameTransformation;
	private String determiner; 
	private String namePlural;
	private String nameSingular;
	private List<CoveringModifier> naturalModifiers;
	private List<CoveringModifier> extraModifiers;
	private List<CoveringModifier> allModifiers;
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
		this.mod = false;
		this.fromExternalFile = false;
		
		this.category = category;
		
		this.nameTransformation = null;
		
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

		allModifiers = new ArrayList<>();
		Set<CoveringModifier> modSet = new HashSet<>();
		modSet.addAll(this.naturalModifiers);
		modSet.addAll(this.extraModifiers);
		allModifiers.addAll(modSet);
		
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
		this.mod = false;
		this.fromExternalFile = false;
		
		this.category = category;

		this.nameTransformation = null;
		
		this.determiner = determiner;
		this.namePlural = namePlural;
		this.nameSingular = nameSingular;
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
		
		allModifiers = new ArrayList<>();
		Set<CoveringModifier> modSet = new HashSet<>();
		modSet.addAll(this.naturalModifiers);
		modSet.addAll(this.extraModifiers);
		allModifiers.addAll(modSet);
		
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
		
		setupColourLists();
	}

	public AbstractBodyCoveringType(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in statusEffect files it's <statusEffect>

				this.mod = mod;
				this.fromExternalFile = true;
				
				this.category = BodyCoveringCategory.valueOf(coreElement.getMandatoryFirstOf("category").getTextContent());

				if(coreElement.getOptionalFirstOf("nameTransformation").isPresent()) {
					this.nameTransformation = coreElement.getMandatoryFirstOf("nameTransformation").getTextContent();
				} else {
					this.nameTransformation = null;
				}
				
				this.determiner = coreElement.getMandatoryFirstOf("determiner").getTextContent();
				this.nameSingular = coreElement.getMandatoryFirstOf("name").getTextContent();
				this.namePlural = coreElement.getMandatoryFirstOf("namePlural").getTextContent();
				this.isDefaultPlural = Boolean.valueOf(coreElement.getMandatoryFirstOf("namePlural").getAttribute("pluralByDefault"));
				
				this.naturalModifiers = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("naturalCoveringModifiers").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("naturalCoveringModifiers").getAllOf("modifier")) {
						this.naturalModifiers.add(CoveringModifier.valueOf(e.getTextContent()));
					}
				}
				if(this.naturalModifiers.isEmpty()) {
					System.err.println("WARNING: AbstractBodyCoveringType mod did not have any naturalModifiers defined! SMOOTH added for error-prevention.");
					this.naturalModifiers.add(CoveringModifier.SMOOTH);
				}
				
				this.extraModifiers = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("extraCoveringModifiers").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("extraCoveringModifiers").getAllOf("modifier")) {
						this.extraModifiers.add(CoveringModifier.valueOf(e.getTextContent()));
					}
				}
				
				this.naturalPatterns = new HashMap<>();
				if(coreElement.getOptionalFirstOf("naturalPatterns").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("naturalPatterns").getAllOf("pattern")) {
						this.naturalPatterns.put(CoveringPattern.valueOf(e.getTextContent()), Integer.valueOf(e.getAttribute("weighting")));
					}
				}
				if(this.naturalPatterns.isEmpty()) {
					this.naturalPatterns.put(CoveringPattern.NONE, 1);
				}
				
				this.dyePatterns = new HashMap<>();
				if(coreElement.getOptionalFirstOf("extraPatterns").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("extraPatterns").getAllOf("pattern")) {
						CoveringPattern pattern = CoveringPattern.valueOf(e.getTextContent());
						if(!this.naturalPatterns.containsKey(pattern)) {
							this.dyePatterns.put(pattern, Integer.valueOf(e.getAttribute("weighting")));
						}
					}
				}
				

				Function< Element, List<Colour> > getColoursFromElement = (colorsElement) -> { //Helper function to get the colors depending on if it's a specified group or a list of individual colors
					String values = colorsElement.getAttribute("values");
					try {
						if(values.isEmpty()) {
							List<Colour> colours = new ArrayList<>();
							for(Element element : colorsElement.getAllOf("colour")) {
								colours.add(PresetColour.getColourFromId(element.getTextContent()));
							}
							return colours;
						} else {
							return ColourListPresets.getColourListFromId(values);
						}
						
					} catch (Exception e) {
						throw new IllegalStateException("AbstractBodyCoveringType: Colour tag reading failure: "+colorsElement.getTagName()+" " + e.getMessage(), e);
					}
				};

				this.naturalColoursPrimary = coreElement.getOptionalFirstOf("naturalColoursPrimary")
					.map(getColoursFromElement::apply)
					.orElseGet(ArrayList::new);
				
				this.dyeColoursPrimary = coreElement.getOptionalFirstOf("extraColoursPrimary")
					.map(getColoursFromElement::apply)
					.orElseGet(ArrayList::new);

				this.naturalColoursSecondary = coreElement.getOptionalFirstOf("naturalColoursSecondary")
					.map(getColoursFromElement::apply)
					.orElseGet(ArrayList::new);
				
				this.dyeColoursSecondary = coreElement.getOptionalFirstOf("extraColoursSecondary")
					.map(getColoursFromElement::apply)
					.orElseGet(ArrayList::new);
				
				
				// Set up 'all' lists:
				
				allPatterns = new HashMap<>();
				for(Entry<CoveringPattern, Integer> entry : this.naturalPatterns.entrySet()) {
					this.allPatterns.put(entry.getKey(), entry.getValue());
				}
				for(Entry<CoveringPattern, Integer> entry : this.dyePatterns.entrySet()) {
					if(!this.allPatterns.containsKey(entry.getKey())) {
						this.allPatterns.put(entry.getKey(), entry.getValue());
					}
				}
				
				allModifiers = new ArrayList<>();
				Set<CoveringModifier> modSet = new HashSet<>();
				modSet.addAll(this.naturalModifiers);
				modSet.addAll(this.extraModifiers);
				allModifiers.addAll(modSet);
				
				setupColourLists();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractBodyCoveringType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	private void setupColourLists() {
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
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
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
	
	/**
	 *  @return The name of the covering for use in transformation menus. Will most likely return the same as getName(gc)
	 */
	public String getNameTransformation(GameCharacter gc) {
		if(nameTransformation==null) {
			return getName(gc);
		}
		return nameTransformation;
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

	public List<CoveringModifier> getAllModifiers() {
		return allModifiers;
	}
}
