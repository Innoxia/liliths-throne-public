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
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractEyeType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;

	private String transformationName;

	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;

	private int defaultPairCount;
	
	private EyeShape defaultIrisShape;
	private EyeShape defaultPupilShape;
	
	private String name;
	private String namePlural;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String eyeTransformationDescription;
	private String eyeBodyDescription;
	
	/**
	 * @param coveringType What covers this eye type (i.e skin/fur/feather type).
	 * @param race What race has this eye type.
	 * @param defaultPairCount How many pairs of eyes is the default for this type.
	 * @param defaultIrisShape What the default shape of the iris is for this type.
	 * @param pupilShape What the default shape of the pupil is for this type.
	 * @param transformationName The name that should be displayed when offering this eye type as a transformation. Should be something like "upright rabbit" or "floppy rabbit".
	 * @param name The singular name of the eye. This will usually just be "eye".
	 * @param namePlural The plural name of the eye. This will usually just be "eyes".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this eye type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this eye type.
	 * @param eyeTransformationDescription A paragraph describing a character's eyes transforming into this eye type. Parsing assumes that the character already has this eye type and associated skin covering.
	 * @param eyeBodyDescription A sentence or two to describe this eye type, as seen in the character view screen. It should follow the same format as all of the other entries in the EyeType class.
	 */
	public AbstractEyeType(AbstractBodyCoveringType coveringType,
			AbstractRace race,
			int defaultPairCount,
			EyeShape defaultIrisShape,
			EyeShape defaultPupilShape,
			String transformationName,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String eyeTransformationDescription,
			String eyeBodyDescription) {
		
		this.coveringType = coveringType;
		this.race = race;
		
		this.defaultPairCount = defaultPairCount;

		this.defaultIrisShape = defaultIrisShape;
		this.defaultPupilShape = defaultPupilShape;
		
		this.transformationName = transformationName;
		
		this.name = name;
		this.namePlural = namePlural;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.eyeTransformationDescription = eyeTransformationDescription;
		this.eyeBodyDescription = eyeBodyDescription;
	}
	
	public AbstractEyeType(File XMLFile, String author, boolean mod) {
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
				
				this.transformationName = coreElement.getMandatoryFirstOf("transformationName").getTextContent();
				
				this.defaultPairCount = Integer.valueOf(coreElement.getMandatoryFirstOf("defaultPairCount").getTextContent());
				
				this.defaultIrisShape = EyeShape.valueOf(coreElement.getMandatoryFirstOf("defaultIrisShape").getTextContent());
				this.defaultPupilShape = EyeShape.valueOf(coreElement.getMandatoryFirstOf("defaultPupilShape").getTextContent());

				
				this.name = coreElement.getMandatoryFirstOf("name").getTextContent();
				this.namePlural = coreElement.getMandatoryFirstOf("namePlural").getTextContent();
				
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
				
				this.eyeTransformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.eyeBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractEyeType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}

	public int getDefaultPairCount() {
		return defaultPairCount;
	}

	public EyeShape getDefaultIrisShape() {
		return defaultIrisShape;
	}

	public EyeShape getDefaultPupilShape() {
		return defaultPupilShape;
	}
	
	@Override
	public String getTransformationNameOverride() {
		return transformationName;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getEyePairs()==1) {
			return "a pair of";
		}
		return Util.intToString(gc.getEyePairs())+" pairs of";
	}

	@Override
	public boolean isDefaultPlural(GameCharacter gc) {
		return true;
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return name;
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return namePlural;
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
		return UtilText.parse(owner, eyeBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, eyeTransformationDescription);
	}
}
