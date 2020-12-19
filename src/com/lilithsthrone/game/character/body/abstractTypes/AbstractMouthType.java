package com.lilithsthrone.game.character.body.abstractTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.TongueType;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractMouthType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;
	
	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;
	private AbstractTongueType tongueType;
	
	private List<String> names;
	private List<String> namesPlural;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String mouthBodyDescription;
	
	List<OrificeModifier> defaultRacialOrificeModifiers;

	public AbstractMouthType(AbstractRace race, AbstractTongueType tongueType) {
		this(BodyCoveringType.MOUTH,
				race,
				tongueType,
				null,
				null,
				Util.newArrayListOfValues(""),
				Util.newArrayListOfValues(""),
				null,
				Util.newArrayListOfValues());
	}
	
	/**
	 * @param coveringType What covers this mouth type (i.e skin/fur/feather type). This is never used, as skin type covering mouth is determined by torso covering.
	 * @param race What race has this mouth type.
	 * @param tongueType The type of tongue this mouth contains.
	 * @param names A list of singular names for this mouth type. Pass in null to use generic names.
	 * @param namesPlural A list of plural names for this mouth type. Pass in null to use generic names.
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this mouth type.
	 * @param mouthBodyDescription A sentence or two to describe this mouth type, as seen in the character view screen. It should follow the same format as all of the other entries in the MouthType class. Pass in null to use a generic description.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this mouth type.
	 */
	public AbstractMouthType(AbstractBodyCoveringType coveringType,
			AbstractRace race,
			AbstractTongueType tongueType,
			List<String> names,
			List<String> namesPlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String mouthBodyDescription,
			List<OrificeModifier> defaultRacialOrificeModifiers) {
		
		this.coveringType = coveringType;
		this.race = race;
		this.tongueType = tongueType;
		
		this.names = names;
		this.namesPlural = namesPlural;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		if(mouthBodyDescription==null || mouthBodyDescription.isEmpty()) {
			this.mouthBodyDescription = "[npc.SheHasFull] [npc.lipSize], [npc.mouthColourPrimary(true)] [npc.lips]"
						+ "#IF(npc.isWearingLipstick())"
							+ "#IF(npc.isPiercedLip()), which have been pierced, and#ELSE, which#ENDIF"
							+ " are currently [npc.materialCompositionDescriptor]"
							+ "#IF(npc.isHeavyMakeup(BODY_COVERING_TYPE_MAKEUP_LIPSTICK) && game.isLipstickMarkingEnabled())"
								+ " a [style.colourPinkDeep(heavy layer)] of"
							+ "#ENDIF"
							+ " [#npc.getLipstick().getFullDescription(npc, true)]."
						+ "#ELSE"
							+ "#IF(npc.isPiercedLip()), which have been pierced#ENDIF."
						+ "#ENDIF"
						+ " [npc.Her] throat is [npc.mouthColourSecondary(true)] in colour.";
		} else {
			this.mouthBodyDescription = mouthBodyDescription;
		}

		if(defaultRacialOrificeModifiers==null) {
			this.defaultRacialOrificeModifiers = new ArrayList<>();
		} else {
			this.defaultRacialOrificeModifiers = defaultRacialOrificeModifiers;
		}
	}
	
	public AbstractMouthType(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile);

				this.mod = mod;
				this.fromExternalFile = true;
				
				this.race = Race.getRaceFromId(coreElement.getMandatoryFirstOf("race").getTextContent());
				this.coveringType = BodyCoveringType.getBodyCoveringTypeFromId(coreElement.getMandatoryFirstOf("coveringType").getTextContent());
				
				this.tongueType = TongueType.getTongueTypeFromId(coreElement.getMandatoryFirstOf("tongueType").getTextContent());
				
				this.names = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("names").getAllOf("name")) {
					names.add(e.getTextContent());
				}
				
				this.namesPlural = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("namesPlural").getAllOf("name")) {
					namesPlural.add(e.getTextContent());
				}
				
				this.descriptorsMasculine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("descriptorsMasculine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("descriptorsMasculine").getAllOf("descriptor")) {
						descriptorsMasculine.add(e.getTextContent());
					}
				}
				
				this.descriptorsFeminine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("descriptorsFeminine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("descriptorsFeminine").getAllOf("descriptor")) {
						descriptorsFeminine.add(e.getTextContent());
					}
				}
				
				this.mouthBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				
				this.defaultRacialOrificeModifiers = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("defaultOrificeModifiers").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("defaultOrificeModifiers").getAllOf("modifier")) {
						defaultRacialOrificeModifiers.add(OrificeModifier.valueOf(e.getTextContent()));
					}
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractAnusType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}
	
	public AbstractTongueType getTongueType() {
		return tongueType;
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
		if(names==null || names.isEmpty()) {
			return UtilText.returnStringAtRandom("mouth");
		}
		return Util.randomItemFrom(names);
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(namesPlural==null || names.isEmpty()) {
			return UtilText.returnStringAtRandom("mouths");
		}
		return Util.randomItemFrom(namesPlural);
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(descriptorsFeminine);
		} else {
			return Util.randomItemFrom(descriptorsMasculine);
		}
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
		return UtilText.parse(owner, mouthBodyDescription);
	}
	
	public List<OrificeModifier> getDefaultRacialOrificeModifiers() {
		return defaultRacialOrificeModifiers;
	}
}
