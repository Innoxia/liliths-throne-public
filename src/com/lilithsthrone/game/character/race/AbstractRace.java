package com.lilithsthrone.game.character.race;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.LegConfigurationAffinity;
import com.lilithsthrone.game.character.body.valueEnums.Affinity;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.9.1
 * @version 0.4.2
 * @author Innoxia, Maxis
 */
public abstract class AbstractRace {

	private boolean mod;
	private boolean fromExternalFile;
	
	private String racialBodyId;
	private String raceChangeString;
	
	private String name;
	private String namePlural;
	private String nameSillyMode;
	private String namePluralSillyMode;
	private Map<LegConfigurationAffinity, String> nameFeral;
	private Map<LegConfigurationAffinity, String> nameFeralPlural;
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
	private Map<AbstractFetish, Map<String, Integer>> racialFetishModifiers;

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
				LegConfigurationAffinity.getFeralNamesMap(LegConfiguration.BIPEDAL, nameFeral),
				LegConfigurationAffinity.getFeralNamesMap(LegConfiguration.BIPEDAL, nameFeralPlural),
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
			Map<LegConfigurationAffinity, String> nameFeral,
			Map<LegConfigurationAffinity, String> nameFeralPlural,
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
		this.nameSillyMode = name;
		this.namePluralSillyMode = namePlural;
		this.nameFeral = nameFeral;
		for (Affinity affinity : Affinity.getAllAffinities()) {
			if (!nameFeral.containsKey(new LegConfigurationAffinity(LegConfiguration.BIPEDAL, affinity))) {
				System.err.println("Warning: AbstractRace '"+name+"' did not have a definition for nameFeral BIPEDAL and affinity = "+String.valueOf(affinity)+"!");
				this.nameFeral.put(new LegConfigurationAffinity(LegConfiguration.BIPEDAL, affinity), name);
			}
		}
		this.nameFeralPlural = nameFeralPlural;
		for (Affinity affinity : Affinity.getAllAffinities()) {
			if (!nameFeralPlural.containsKey(new LegConfigurationAffinity(LegConfiguration.BIPEDAL, affinity))) {
				System.err.println("Warning: AbstractRace '"+name+"' did not have a definition for nameFeralPlural BIPEDAL and affinity = "+String.valueOf(affinity)+"!");
				this.nameFeralPlural.put(new LegConfigurationAffinity(LegConfiguration.BIPEDAL, affinity), namePlural);
			}
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
		
		this.racialFetishModifiers = new HashMap<AbstractFetish, Map<String, Integer>>();

		this.feralPartsAvailable = true;
		this.ableToSelfTransform = false;
		this.flyingRace = false;
	}
	
	public AbstractRace(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in statusEffect files it's <statusEffect>

				this.mod = mod;
				this.fromExternalFile = true;
				
				this.racialBodyId = coreElement.getMandatoryFirstOf("racialBody").getTextContent();
				
				this.raceChangeString = coreElement.getMandatoryFirstOf("applyRaceChanges").getTextContent();
				
				this.name = coreElement.getMandatoryFirstOf("name").getTextContent();
				this.namePlural = coreElement.getMandatoryFirstOf("namePlural").getTextContent();
				
				if(coreElement.getOptionalFirstOf("nameSillyMode").isPresent()) {
					this.nameSillyMode = coreElement.getMandatoryFirstOf("nameSillyMode").getTextContent();
				} else {
					this.nameSillyMode = this.name;
				}
				if(coreElement.getOptionalFirstOf("namePluralSillyMode").isPresent()) {
					this.namePluralSillyMode = coreElement.getMandatoryFirstOf("namePluralSillyMode").getTextContent();
				} else {
					this.namePluralSillyMode = this.namePlural;
				}
				
				this.nameFeral = new HashMap<>();
				String anyFeralName = "";
				if(coreElement.getOptionalFirstOf("nameFeral").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("nameFeral").getAllOf("name")) {
						try {
							String loadedFeralName = e.getTextContent();
							if(anyFeralName.isEmpty()) {
								anyFeralName = loadedFeralName;
							}
							if (e.getAttribute("affinity").isEmpty()) {
								for (Affinity affinity : Affinity.getAllAffinities()) {
									LegConfigurationAffinity configAffinity = new LegConfigurationAffinity(LegConfiguration.valueOf(e.getAttribute("legConfiguration")), affinity);
									if (!this.nameFeral.containsKey(configAffinity)) {
										this.nameFeral.put(configAffinity, loadedFeralName);
									}
								}
							} else {
								this.nameFeral.put(new LegConfigurationAffinity(
									LegConfiguration.valueOf(e.getAttribute("legConfiguration")),
									Affinity.getAffinityFromId(e.getAttribute("affinity"))
								), loadedFeralName);
							}
						} catch(Exception ex) {
							System.err.println("Error in AbstractRace loading: LegConfiguration '"+e.getAttribute("legConfiguration")+"' not recognised in nameFeral!");
							ex.printStackTrace();
						}
					}
				}
				for (Affinity affinity : Affinity.getAllAffinities()) {
					if(!this.nameFeral.containsKey(new LegConfigurationAffinity(LegConfiguration.BIPEDAL, affinity))) {
						this.nameFeral.put(new LegConfigurationAffinity(LegConfiguration.BIPEDAL, affinity), anyFeralName);
					}
				}

				this.nameFeralPlural = new HashMap<>();
				anyFeralName = "";
				if(coreElement.getOptionalFirstOf("nameFeralPlural").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("nameFeralPlural").getAllOf("name")) {
						try {
							String loadedFeralName = e.getTextContent();
							if(anyFeralName.isEmpty()) {
								anyFeralName = loadedFeralName;
							}
							if (e.getAttribute("affinity").isEmpty()) {
								for (Affinity affinity : Affinity.getAllAffinities()) {
									LegConfigurationAffinity configAffinity = new LegConfigurationAffinity(LegConfiguration.valueOf(e.getAttribute("legConfiguration")), affinity);
									if (!this.nameFeralPlural.containsKey(configAffinity)) {
										this.nameFeralPlural.put(configAffinity, loadedFeralName);
									}
								}
							} else {
								this.nameFeralPlural.put(new LegConfigurationAffinity(
									LegConfiguration.valueOf(e.getAttribute("legConfiguration")),
									Affinity.getAffinityFromId(e.getAttribute("affinity"))
								), loadedFeralName);
							}
						} catch(Exception ex) {
							System.err.println("Error in AbstractRace loading: LegConfiguration '"+e.getAttribute("legConfiguration")+"' not recognised in nameFeralPlural!");
							ex.printStackTrace();
						}
					}
				}
				for (Affinity affinity : Affinity.getAllAffinities()) {
					if(!this.nameFeralPlural.containsKey(new LegConfigurationAffinity(LegConfiguration.BIPEDAL, affinity))) {
						this.nameFeralPlural.put(new LegConfigurationAffinity(LegConfiguration.BIPEDAL, affinity), anyFeralName);
					}
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
				
				this.racialFetishModifiers = Util.newHashMapOfValues();
				if(coreElement.getOptionalFirstOf("racialFetishModifiers").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("racialFetishModifiers").getAllOf("fetish")) {
						try {
							AbstractFetish fetish = Fetish.getFetishFromId(e.getTextContent());
							HashMap<String, Integer> weights = new HashMap<>();
							if(!e.getAttribute("love").isEmpty()) {
								weights.put("love", Integer.parseInt(e.getAttribute("love")));
							}
							if(!e.getAttribute("like").isEmpty()) {
								weights.put("like", Integer.parseInt(e.getAttribute("like")));
							}
							if(!e.getAttribute("dislike").isEmpty()) {
								weights.put("dislike", Integer.parseInt(e.getAttribute("dislike")));
							}
							if(!e.getAttribute("hate").isEmpty()) {
								weights.put("hate", Integer.parseInt(e.getAttribute("hate")));
							}
							this.racialFetishModifiers.put(fetish, weights);
						} catch(Exception ex) {
							System.err.println("Error in AbstractRace loading: Fetish '"+e.getTextContent()+"' not recognised in racialFetishModifiers!");
							ex.printStackTrace();
						}
					}
				}
				
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
	
	public String getName(Body body, boolean feral) {
		if(feral) {
			return getFeralName(new LegConfigurationAffinity(body), false);
		}
		if(Main.game!=null && Main.game.isSillyMode()) {
			return nameSillyMode;
		}
		return name;
	}
	
	public String getName(boolean feral) {
		return getName(null, feral);
	}
	
	public String getNamePlural(Body body, boolean feral) {
		if(feral) {
			return getFeralName(new LegConfigurationAffinity(body), true);
		}
		if(Main.game!=null && Main.game.isSillyMode()) {
			return namePluralSillyMode;
		}
		return namePlural;
	}
	
	public String getNamePlural(boolean feral) {
		return getNamePlural(null, feral);
	}
	
	public String getFeralName(LegConfigurationAffinity legConfigurationAffinity, boolean plural) {
		if(plural) {
			if(nameFeralPlural.containsKey(legConfigurationAffinity)) {
				return nameFeralPlural.get(legConfigurationAffinity);
			} else {
				return nameFeralPlural.get(new LegConfigurationAffinity(LegConfiguration.BIPEDAL, Affinity.AMPHIBIOUS));
			}
		} else {
			if(nameFeral.containsKey(legConfigurationAffinity)) {
				return nameFeral.get(legConfigurationAffinity);
			} else {
				return nameFeral.get(new LegConfigurationAffinity(LegConfiguration.BIPEDAL, Affinity.AMPHIBIOUS));
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

	public Map<AbstractFetish, Map<String, Integer>> getRacialFetishModifiers() {
		return racialFetishModifiers;
	}
}
