package com.lilithsthrone.game.character.race;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.LegConfigurationAquatic;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.9.1
 * @version 0.4.0
 * @author Innoxia
 */
public abstract class AbstractRace {

	private boolean mod;
	private boolean fromExternalFile;
	
	private String racialBodyId;
	private String raceChangeString;
	
	private String name;
	private String namePlural;
	private Map<LegConfigurationAquatic, String> nameFeral;
	private Map<LegConfigurationAquatic, String> nameFeralPlural;
	private String defaultTransformName;
	
	private Colour colour;
	
	private Disposition disposition;
	private RacialClass racialClass;
	
	private CombatBehaviour preferredCombatBehaviour;
	private int numberOfOffspringLow;
	private int numberOfOffspringHigh;
	private float chanceForMaleOffspring;
	private FurryPreference defaultFemininePreference;
	private FurryPreference defaultMasculinePreference;
	private boolean affectedByFurryPreference;

	private boolean feralPartsAvailable;
	private boolean ableToSelfTransform;
	private boolean flyingRace;
	
	public AbstractRace(String name,
			String namePlural,
			String nameFeral,
			String nameFeralPlural,
			String defaultTransformName,
			Colour colour,
			Disposition disposition,
			RacialClass racialClass,
			CombatBehaviour preferredCombatBehaviour,
			float chanceForMaleOffspring,
			int numberOfOffspringLow,
			int numberOfOffspringHigh,
			FurryPreference defaultFemininePreference,
			FurryPreference defaultMasculinePreference,
			boolean affectedByFurryPreference) {
		this(name,
				namePlural,
				Util.newHashMapOfValues(
					new Value<>(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, false), nameFeral),
					new Value<>(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, true), nameFeral)),
				Util.newHashMapOfValues(
					new Value<>(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, false), nameFeralPlural),
					new Value<>(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, true), nameFeralPlural)),
				defaultTransformName,
				colour,
				disposition,
				racialClass,
				preferredCombatBehaviour,
				chanceForMaleOffspring,
				numberOfOffspringLow,
				numberOfOffspringHigh,
				defaultFemininePreference, 
				defaultMasculinePreference,
				affectedByFurryPreference);
	}
	
	public AbstractRace(String name,
			String namePlural,
			Map<LegConfigurationAquatic, String> nameFeral,
			Map<LegConfigurationAquatic, String> nameFeralPlural,
			String defaultTransformName,
			Colour colour,
			Disposition disposition,
			RacialClass racialClass,
			CombatBehaviour preferredCombatBehaviour,
			float chanceForMaleOffspring,
			int numberOfOffspringLow,
			int numberOfOffspringHigh,
			FurryPreference defaultFemininePreference,
			FurryPreference defaultMasculinePreference,
			boolean affectedByFurryPreference) {
		this.mod = false;
		this.fromExternalFile = false;
		
		this.name = name;
		this.namePlural = namePlural;
		this.nameFeral = nameFeral;
		if (!nameFeral.containsKey(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, false))) {
			System.err.println("Warning: AbstractRace '"+name+"' did not have a definition for nameFeral BIPEDAL and aquatic = false!");
			this.nameFeral.put(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, false), name);
		}
		if (!nameFeral.containsKey(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, true))) {
			System.err.println("Warning: AbstractRace '"+name+"' did not have a definition for nameFeral BIPEDAL and aquatic = true!");
			this.nameFeral.put(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, true), name);
		}
		this.nameFeralPlural = nameFeralPlural;
		if (!nameFeralPlural.containsKey(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, false))) {
			System.err.println("Warning: AbstractRace '"+name+"' did not have a definition for nameFeralPlural BIPEDAL and aquatic = false!");
			this.nameFeralPlural.put(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, false), namePlural);
		}
		if (!nameFeralPlural.containsKey(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, true))) {
			System.err.println("Warning: AbstractRace '"+name+"' did not have a definition for nameFeralPlural BIPEDAL and aquatic = true!");
			this.nameFeralPlural.put(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, true), namePlural);
		}
		this.defaultTransformName = defaultTransformName;
		
		this.colour = colour;
		
		this.disposition = disposition;
		this.racialClass = racialClass;
		
		this.preferredCombatBehaviour = preferredCombatBehaviour;

		this.chanceForMaleOffspring=chanceForMaleOffspring;
		
		this.numberOfOffspringLow = numberOfOffspringLow;
		this.numberOfOffspringHigh = numberOfOffspringHigh;
		
		this.defaultFemininePreference = defaultFemininePreference;
		this.defaultMasculinePreference = defaultMasculinePreference;
		
		this.affectedByFurryPreference = affectedByFurryPreference;
		
		this.feralPartsAvailable = true;
		this.ableToSelfTransform = false;
		this.flyingRace = false;
	}
	
	public AbstractRace(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in statusEffect files it's <statusEffect>

				this.mod = mod;
				this.fromExternalFile = true;
				
				this.racialBodyId = coreElement.getMandatoryFirstOf("racialBody").getTextContent();
				
				this.raceChangeString = coreElement.getMandatoryFirstOf("applyRaceChanges").getTextContent();
				
				this.name = coreElement.getMandatoryFirstOf("name").getTextContent();
				this.namePlural = coreElement.getMandatoryFirstOf("namePlural").getTextContent();
				
				this.nameFeral = new HashMap<>();
				String anyFeralName = "";
				if(coreElement.getOptionalFirstOf("nameFeral").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("nameFeral").getAllOf("name")) {
						try {
							LegConfigurationAquatic configAquatic;
							LegConfigurationAquatic configNotAquatic;
							String loadedFeralName = e.getTextContent();
							if(anyFeralName.isEmpty()) {
								anyFeralName = loadedFeralName;
							}
							if (e.getAttribute("isAquatic").isEmpty()) {
								configAquatic = new LegConfigurationAquatic(LegConfiguration.valueOf(e.getAttribute("legConfiguration")), true);
								configNotAquatic = new LegConfigurationAquatic(LegConfiguration.valueOf(e.getAttribute("legConfiguration")), false);
								if (!this.nameFeral.containsKey(configAquatic)) {
									this.nameFeral.put(configAquatic, loadedFeralName);
								}
								if (!this.nameFeral.containsKey(configNotAquatic)) {
									this.nameFeral.put(configNotAquatic, loadedFeralName);
								}
							} else {
								this.nameFeral.put(new LegConfigurationAquatic(
									LegConfiguration.valueOf(e.getAttribute("legConfiguration")),
									Boolean.valueOf(e.getAttribute("isAquatic"))
								), loadedFeralName);
							}
						} catch(Exception ex) {
							System.err.println("Error in AbstractRace loading: LegConfiguration '"+e.getAttribute("legConfiguration")+"' not recognised in nameFeral!");
							ex.printStackTrace();
						}
					}
				}
				if(!this.nameFeral.containsKey(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, false))) {
//					System.err.println("Warning: AbstractRace '"+this.name+"' did not have a definition for nameFeral BIPEDAL and aquatic = false!");
					this.nameFeral.put(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, false), anyFeralName);
				}
				if(!this.nameFeral.containsKey(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, true))) {
//					System.err.println("Warning: AbstractRace '"+this.name+"' did not have a definition for nameFeral BIPEDAL and aquatic = true!");
					this.nameFeral.put(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, true), anyFeralName);
				}

				this.nameFeralPlural = new HashMap<>();
				anyFeralName = "";
				if(coreElement.getOptionalFirstOf("nameFeralPlural").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("nameFeralPlural").getAllOf("name")) {
						try {
							LegConfigurationAquatic configAquatic;
							LegConfigurationAquatic configNotAquatic;
							String loadedFeralName = e.getTextContent();
							if(anyFeralName.isEmpty()) {
								anyFeralName = loadedFeralName;
							}
							if (e.getAttribute("isAquatic").isEmpty()) {
								configAquatic = new LegConfigurationAquatic(LegConfiguration.valueOf(e.getAttribute("legConfiguration")), true);
								configNotAquatic = new LegConfigurationAquatic(LegConfiguration.valueOf(e.getAttribute("legConfiguration")), false);
								if (!this.nameFeralPlural.containsKey(configAquatic)) {
									this.nameFeralPlural.put(configAquatic, loadedFeralName);
								}
								if (!this.nameFeralPlural.containsKey(configNotAquatic)) {
									this.nameFeralPlural.put(configNotAquatic, loadedFeralName);
								}
							} else {
								this.nameFeralPlural.put(new LegConfigurationAquatic(
									LegConfiguration.valueOf(e.getAttribute("legConfiguration")),
									Boolean.valueOf(e.getAttribute("isAquatic"))
								), loadedFeralName);
							}
						} catch(Exception ex) {
							System.err.println("Error in AbstractRace loading: LegConfiguration '"+e.getAttribute("legConfiguration")+"' not recognised in nameFeralPlural!");
							ex.printStackTrace();
						}
					}
				}
				if(!this.nameFeralPlural.containsKey(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, false))) {
//					System.err.println("Warning: AbstractRace '"+this.name+"' did not have a definition for nameFeralPlural BIPEDAL and aquatic = false!");
					this.nameFeralPlural.put(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, false), anyFeralName);
				}
				if(!this.nameFeralPlural.containsKey(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, true))) {
//					System.err.println("Warning: AbstractRace '"+this.name+"' did not have a definition for nameFeralPlural BIPEDAL and aquatic = true!");
					this.nameFeralPlural.put(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, true), anyFeralName);
				}
				
				this.defaultTransformName = coreElement.getMandatoryFirstOf("defaultTransformName").getTextContent();
				
				this.colour = PresetColour.getColourFromId(coreElement.getMandatoryFirstOf("colour").getTextContent());

				this.disposition = Disposition.valueOf(coreElement.getMandatoryFirstOf("disposition").getTextContent());

				this.racialClass = RacialClass.valueOf(coreElement.getMandatoryFirstOf("racialClass").getTextContent());
				
				this.preferredCombatBehaviour = CombatBehaviour.BALANCED;
				if(coreElement.getOptionalFirstOf("combatBehaviour").isPresent()) {
					try {
						this.preferredCombatBehaviour = CombatBehaviour.valueOf(coreElement.getMandatoryFirstOf("combatBehaviour").getTextContent());
					} catch(Exception ex) {
						System.err.println("Error in AbstractRace loading: CombatBehaviour '"+coreElement.getMandatoryFirstOf("combatBehaviour").getTextContent()+"' not recognised!");
					}
				}
				
				this.chanceForMaleOffspring = Float.valueOf(coreElement.getMandatoryFirstOf("chanceForMaleOffspring").getTextContent());
				
				this.numberOfOffspringLow = Integer.valueOf(coreElement.getMandatoryFirstOf("numberOfOffspringLow").getTextContent());
				this.numberOfOffspringHigh = Integer.valueOf(coreElement.getMandatoryFirstOf("numberOfOffspringHigh").getTextContent());
				
				this.defaultFemininePreference = FurryPreference.valueOf(coreElement.getMandatoryFirstOf("defaultFemininePreference").getTextContent());
				this.defaultMasculinePreference = FurryPreference.valueOf(coreElement.getMandatoryFirstOf("defaultMasculinePreference").getTextContent());
				
				this.affectedByFurryPreference = Boolean.valueOf(coreElement.getMandatoryFirstOf("affectedByFurryPreference").getTextContent());
				
				this.feralPartsAvailable = Boolean.valueOf(coreElement.getMandatoryFirstOf("feralPartsAvailable").getTextContent());
				this.ableToSelfTransform = Boolean.valueOf(coreElement.getMandatoryFirstOf("ableToSelfTransform").getTextContent());
				this.flyingRace = Boolean.valueOf(coreElement.getMandatoryFirstOf("flyingRace").getTextContent());
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractRace was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}

	public AbstractRacialBody getRacialBody() {
		if(this.isFromExternalFile()) {
			return RacialBody.getRacialBodyFromId(racialBodyId);
		}
		System.err.println("Warning: AbstractRacialBody is calling getRacialBody() where racialBodyId does not exist!");
		return null;
	}

	/**
	 * Applies any special racial changes to the body which is passed in. This is called <b>before</b> Subspecies.applySpeciesChanges()
	 */
	public void applyRaceChanges(Body body) {
		if(this.isFromExternalFile()) {
			UtilText.setBodyForParsing("targetedBody", body);
			UtilText.parse(raceChangeString);
		}
	}
	
	public boolean isFeralPartsAvailable() {
		return feralPartsAvailable;
	}
	
	public boolean isFlyingRace() {
		return flyingRace;
	}

	public boolean isAbleToSelfTransform() {
		return ableToSelfTransform;
	}
	
	public String getName(GameCharacter character, boolean feral) {
		if(feral) {
			return getFeralName(
				character != null ? 
					new LegConfigurationAquatic(character.getLegConfiguration(), character.getSubspecies().isAquatic(character)) : // <-- maybe use isAquatic(null) here? ~Stadler76
					new LegConfigurationAquatic(LegConfiguration.BIPEDAL, false),
				false);
		}
		return name;
	}
	
	public String getName(boolean feral) {
		return getName(null, feral);
	}
	
	public String getNamePlural(GameCharacter character, boolean feral) {
		if(feral) {
			return getFeralName(
				character != null ? 
					new LegConfigurationAquatic(character.getLegConfiguration(), character.getSubspecies().isAquatic(character)) : // <-- maybe use isAquatic(null) here? ~Stadler76
					new LegConfigurationAquatic(LegConfiguration.BIPEDAL, false),
				true);
		}
		return namePlural;
	}
	
	public String getNamePlural(boolean feral) {
		return getNamePlural(null, feral);
	}
	
	public String getFeralName(LegConfigurationAquatic legConfigurationAquatic, boolean plural) {
		if(plural) {
			if(nameFeralPlural.containsKey(legConfigurationAquatic)) {
				return nameFeralPlural.get(legConfigurationAquatic);
			} else {
				return nameFeralPlural.get(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, false));
			}
		} else {
			if(nameFeral.containsKey(legConfigurationAquatic)) {
				return nameFeral.get(legConfigurationAquatic);
			} else {
				return nameFeral.get(new LegConfigurationAquatic(LegConfiguration.BIPEDAL, false));
			}
		}
	}

	public String getDefaultTransformName() {
		return defaultTransformName;
	}

	public Disposition getDisposition() {
		return disposition;
	}

	public RacialClass getRacialClass() {
		return racialClass;
	}

	public CombatBehaviour getPreferredCombatBehaviour() {
		return preferredCombatBehaviour;
	}

	public int getNumberOfOffspringLow() {
		return numberOfOffspringLow;
	}

	public int getNumberOfOffspringHigh() {
		return numberOfOffspringHigh;
	}
	
	public Colour getColour() {
		return colour;
	}
	
	public boolean isAffectedByFurryPreference() {
		return affectedByFurryPreference;
	}
	
	public float getChanceForMaleOffspring() {
		return chanceForMaleOffspring;
	}

	/**
	 * <b>Should only be used in Subspecies' getDamageMultiplier() method!</b>
	 */
	public AbstractAttribute getDefaultDamageMultiplier() {
		return Attribute.getRacialDamageAttribute(this);
	}

	public FurryPreference getDefaultFemininePreference() {
		return defaultFemininePreference;
	}

	public FurryPreference getDefaultMasculinePreference() {
		return defaultMasculinePreference;
	}

}
