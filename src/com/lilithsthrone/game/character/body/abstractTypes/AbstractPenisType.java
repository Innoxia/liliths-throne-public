package com.lilithsthrone.game.character.body.abstractTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.main.Main;
import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.TesticleType;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.8.9
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractPenisType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;
	
	// Maps the name to weighting for use in random selection:
	protected static final Map<String, Integer> BASE_NAMES_SINGULAR = Util.newHashMapOfValues(new Value<>("cock", 3), new Value<>("dick", 2), new Value<>("shaft", 1));
	protected static final Map<String, Integer> BASE_NAMES_PLURAL = Util.newHashMapOfValues(new Value<>("cocks", 3), new Value<>("dicks", 2), new Value<>("shafts", 1));
	
	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;

	private String transformationName;

	private boolean pubicHairAllowed;
	
	private AbstractTesticleType testicleType;
	
	private String additionalTransformationEffect;
	
	private List<String> namesFeminine;
	private List<String> namesPluralFeminine;
	private List<String> namesMasculine;
	private List<String> namesPluralMasculine;
	
	private List<String> descriptors;
	
	private String transformationDescription;
	private String bodyDescription;
	
	private List<PenetrationModifier> defaultRacialPenetrationModifiers;
	
	/**
	 * @param coveringType What covers this penis type.
	 * @param race What race has this penis type.
	 * @param testicleType The type of testicles that this penis has.
	 * @param namesFeminine A list of singular feminine names for this penis type.
	 *  Pass in null to use generic names.
	 *  Empty values also use generic names.
	 *  Names ending in '-' are handled in a special manner by appending a generic penis name to the end of it before returning.
	 * @param namesPluralFeminine A list of plural feminine names for this penis type.
	 *  Pass in null to use generic names.
	 *  Empty values also use generic names.
	 *  Names ending in '-' are handled in a special manner by appending a generic penis name to the end of it before returning.
	 * @param namesMasculine A list of singular masculine names for this penis type. (Special effects are the same as namesFeminine.)
	 * @param namesPluralMasculine A list of plural masculine names for this penis type. (Special effects are the same as namesPluralFeminine.)
	 * @param descriptors The descriptors that can be used to this penis type.
	 * @param transformationDescription A paragraph describing a character's penis transforming into this penis type. Parsing assumes that the character already has this penis type and associated skin covering.
	 * @param bodyDescription A sentence or two to describe this penis type, as seen in the character view screen. It should follow the same format as all of the other entries in the PenisType class.
	 * @param defaultRacialPenetrationModifiers Which modifiers this penis naturally spawns with.
	 */
	public AbstractPenisType(AbstractBodyCoveringType coveringType,
			AbstractRace race,
			AbstractTesticleType testicleType,
			List<String> namesFeminine,
			List<String> namesPluralFeminine,
			List<String> namesMasculine,
			List<String> namesPluralMasculine,
			List<String> descriptors,
			String transformationDescription,
			String bodyDescription,
			List<PenetrationModifier> defaultRacialPenetrationModifiers) {

		this.pubicHairAllowed = race.getRacialClass().isAnthroHair();
		
		this.coveringType = coveringType;
		this.race = race;
		this.testicleType = testicleType;

		this.transformationName = null; // Use default race transformation name
		
		this.namesFeminine = namesFeminine;
		this.namesPluralFeminine = namesPluralFeminine;
		this.namesMasculine = namesMasculine;
		this.namesPluralMasculine = namesPluralMasculine;
		
		this.descriptors = descriptors;
		
		this.transformationDescription = transformationDescription;
		this.bodyDescription = bodyDescription;
		
		if(defaultRacialPenetrationModifiers==null) {
			this.defaultRacialPenetrationModifiers = new ArrayList<>();
		} else {
			this.defaultRacialPenetrationModifiers = defaultRacialPenetrationModifiers;
		}
	}
	
	public AbstractPenisType(AbstractBodyCoveringType coveringType,
			AbstractRace race,
			AbstractTesticleType testicleType,
			String transformationDescription,
			String bodyDescription,
			List<PenetrationModifier> defaultRacialPenetrationModifiers) {
		this(coveringType,
				race,
				testicleType,
				null, null,
				null, null,
				null,
				transformationDescription,
				bodyDescription,
				defaultRacialPenetrationModifiers);
	}
	
	public AbstractPenisType(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile);

				this.mod = mod;
				this.fromExternalFile = true;
				
				this.race = Race.getRaceFromId(coreElement.getMandatoryFirstOf("race").getTextContent());
				this.transformationName = coreElement.getMandatoryFirstOf("transformationName").getTextContent();
				this.coveringType = BodyCoveringType.getBodyCoveringTypeFromId(coreElement.getMandatoryFirstOf("coveringType").getTextContent());

				this.transformationName = coreElement.getMandatoryFirstOf("transformationName").getTextContent();

				this.pubicHairAllowed = race.getRacialClass().isAnthroHair();
				if(coreElement.getOptionalFirstOf("pubicHairAllowed").isPresent()) {
					this.pubicHairAllowed = Boolean.valueOf(coreElement.getMandatoryFirstOf("pubicHairAllowed").getTextContent());
				}
				
				this.testicleType = TesticleType.getTesticleTypeFromId(coreElement.getMandatoryFirstOf("testicleType").getTextContent());
				
				this.additionalTransformationEffect = coreElement.getMandatoryFirstOf("additionalTransformationEffect").getTextContent();
				
				this.namesFeminine = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("namesFeminine").getAllOf("name")) {
					namesFeminine.add(e.getTextContent());
				}
				this.namesPluralFeminine = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("namesPluralFeminine").getAllOf("name")) {
					namesPluralFeminine.add(e.getTextContent());
				}
				
				this.namesMasculine = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("namesMasculine").getAllOf("name")) {
					namesMasculine.add(e.getTextContent());
				}
				this.namesPluralMasculine = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("namesPluralMasculine").getAllOf("name")) {
					namesPluralMasculine.add(e.getTextContent());
				}
				
				this.descriptors = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("descriptors").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("descriptors").getAllOf("descriptor")) {
						descriptors.add(e.getTextContent());
					}
				}
				
				this.defaultRacialPenetrationModifiers = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("defaultPenetrationModifiers").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("defaultPenetrationModifiers").getAllOf("modifier")) {
						defaultRacialPenetrationModifiers.add(PenetrationModifier.valueOf(e.getTextContent()));
					}
				}
				
				this.transformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.bodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractPenisType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}
	
	public boolean isPubicHairAllowed() {
		return pubicHairAllowed;
	}

	@Override
	public String getTransformationNameOverride() {
		return transformationName;
	}
	
	public AbstractTesticleType getTesticleType() {
		return testicleType;
	}

	public List<PenetrationModifier> getDefaultRacialPenetrationModifiers() {
		return defaultRacialPenetrationModifiers;
	}
	
	public String getPenisHeadName(GameCharacter gc) {
		return UtilText.returnStringAtRandom("head", "tip");
	}
	
	public String getPenisHeadDescriptor(GameCharacter gc) {
		for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
			if(gc.getPenisModifiers().contains(mod)) {
				switch(mod) {
					case BLUNT:
						return UtilText.returnStringAtRandom("blunt");
					case FLARED:
						return UtilText.returnStringAtRandom("wide", "flared", "flat");
					case TAPERED:
						return UtilText.returnStringAtRandom("tapered", "pointed");
					case KNOTTED:
					case PREHENSILE:
					case RIBBED:
					case SHEATHED:
					case BARBED:
					case TENTACLED:
					case VEINY:
					case OVIPOSITOR:
						break;
				}
			}
		}
		return "";
	}
	
	public String getCumName(GameCharacter gc) {
		return this.getTesticleType().getFluidType().getName(gc);
	}
	
	public String getCumDescriptor(GameCharacter gc) {
		return this.getTesticleType().getFluidType().getDescriptor(gc);
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return "";
	}

	@Override
	public boolean isDefaultPlural(GameCharacter gc) {
		return false;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		String name;
		Map<String, Integer> returnNames = new HashMap<>(BASE_NAMES_SINGULAR);
		if(UtilText.isInSpeech()) {
			returnNames.remove("shaft");
		}
		
		if(gc.isFeminine()) {
			if(namesFeminine==null || namesFeminine.isEmpty()) {
				name = Util.getRandomObjectFromWeightedMap(returnNames);
				
			} else {
				name = Util.randomItemFrom(namesFeminine);
			}
		} else {
			if(namesMasculine==null || namesMasculine.isEmpty()) {
				name = Util.getRandomObjectFromWeightedMap(returnNames);
				
			} else {
				name = Util.randomItemFrom(namesMasculine);
			}
		}
		
		if(!Main.game.isStarted()) {
			return name;
		}
		
		if(name.endsWith("-")) {
			if(Math.random()<0.25f) { // 25% chance to return this '-' name.
				return UtilText.parse(gc, name + Util.getRandomObjectFromWeightedMap(returnNames));
			} else {
				return UtilText.parse(gc, Util.getRandomObjectFromWeightedMap(returnNames));
			}
		}
		if(name.isEmpty()) {
			return UtilText.parse(gc, Util.getRandomObjectFromWeightedMap(returnNames));
		}
		
		return UtilText.parse(gc, name);
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		String name;
		Map<String, Integer> returnNames = new HashMap<>(BASE_NAMES_PLURAL);
		if(UtilText.isInSpeech()) {
			returnNames.remove("shafts");
		}
		
		if(gc.isFeminine()) {
			if(namesPluralFeminine==null || namesPluralFeminine.isEmpty()) {
				name = Util.getRandomObjectFromWeightedMap(returnNames);
				
			} else {
				name = Util.randomItemFrom(namesPluralFeminine);
			}
		} else {
			if(namesPluralMasculine==null || namesPluralMasculine.isEmpty()) {
				name = Util.getRandomObjectFromWeightedMap(returnNames);
				
			} else {
				name = Util.randomItemFrom(namesPluralMasculine);
			}
		}
		
		if(!Main.game.isStarted()) {
			return name;
		}
		
		if(name.endsWith("-")) {
			if(Math.random()<0.25f) { // 25% chance to return this '-' name.
				return UtilText.parse(gc, name + Util.getRandomObjectFromWeightedMap(returnNames));
			} else {
				return UtilText.parse(gc, name + Util.getRandomObjectFromWeightedMap(returnNames));
			}
		}
		if(name.isEmpty()) {
			return UtilText.parse(gc, Util.getRandomObjectFromWeightedMap(returnNames));
		}
		
		return UtilText.parse(gc, name);
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if(descriptors!=null && !descriptors.isEmpty()) {
			return Util.randomItemFrom(descriptors);
		}
		return "";
	}
	
	@Override
	public AbstractBodyCoveringType getBodyCoveringType(Body body) {
		return coveringType;
	}

	@Override
	public AbstractRace getRace() {
		return race;
	}

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, bodyDescription);
	}
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, transformationDescription);
	}
	
	/**
	 * This method is called immediately before and immediately after the target's penis type is changed into this type. When before, applicationAfterChangeApplied is false, and when after, applicationAfterChangeApplied is true.
	 * It is not called if owner is null.
	 */
	public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
		if(this.isFromExternalFile()) {
			UtilText.addSpecialParsingString(String.valueOf(applicationAfterChangeApplied), true);
			return UtilText.parse(owner, additionalTransformationEffect);
		}
		return "";
	}
}
