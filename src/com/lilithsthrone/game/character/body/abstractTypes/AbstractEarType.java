package com.lilithsthrone.game.character.body.abstractTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.Game;
import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.2
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractEarType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;

	private String transformationName;

	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;
	
	private String name;
	private String namePlural;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String earTransformationDescription;
	private String earBodyDescription;
	
	private List<BodyPartTag> tags;
	
	/**
	 * @param coveringType What covers this ear type (i.e skin/fur/feather type).
	 * @param race What race has this ear type.
	 * @param ableToBeUsedAsHandlesInSex True if these ears are long and flexible enough to be grabbed and used as extra leverage during sex.
	 * @param transformationName The name that should be displayed when offering this ear type as a transformation. Should be something like "upright rabbit" or "floppy rabbit".
	 * @param name The singular name of the ear. This will usually just be "ear".
	 * @param namePlural The plural name of the ear. This will usually just be "ears".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this ear type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this ear type.
	 * @param earTransformationDescription A paragraph describing a character's ears transforming into this ear type. Parsing assumes that the character already has this ear type and associated skin covering.
	 * @param earBodyDescription A sentence or two to describe this ear type, as seen in the character view screen. It should follow the same format as all of the other entries in the EarType class.
	 */
	public AbstractEarType(AbstractBodyCoveringType coveringType,
			AbstractRace race,
			String transformationName,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String earTransformationDescription,
			String earBodyDescription) {
		
		this.coveringType = coveringType;
		this.race = race;
		
		this.transformationName = transformationName;
		this.name = name;
		this.namePlural = namePlural;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.earTransformationDescription = earTransformationDescription;
		this.earBodyDescription = earBodyDescription;
		
		this.tags = new ArrayList<>();
	}
	
	public AbstractEarType(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				Document doc = Game.docBuilder.parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile);

				this.mod = mod;
				this.fromExternalFile = true;
				
				this.race = Race.getRaceFromId(coreElement.getMandatoryFirstOf("race").getTextContent());
				this.coveringType = BodyCoveringType.getBodyCoveringTypeFromId(coreElement.getMandatoryFirstOf("coveringType").getTextContent());

				this.transformationName = coreElement.getMandatoryFirstOf("transformationName").getTextContent();

				this.tags = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("tags").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("tags").getAllOf("tag")) {
						tags.add(BodyPartTag.valueOf(e.getTextContent()));
					}
				}

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
				
				this.earTransformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.earBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractEarType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}
	
	public boolean isAbleToBeUsedAsHandlesInSex() {
		return this.getTags().contains(BodyPartTag.EAR_HANDLES_IN_SEX);
	}
	
	@Override
	public String getTransformationNameOverride() {
		return transformationName;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "a pair of";
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

	@Override
	public TFModifier getTFModifier() {
		return getTFTypeModifier(EarType.getEarTypes(race));
	}

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, earBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, earTransformationDescription);
	}

	@Override
	public List<BodyPartTag> getTags() {
		return tags;
	}
}
