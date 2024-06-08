package com.lilithsthrone.game.character.body.abstractTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.main.Main;
import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.8.2
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractHairType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;

	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;

	private String transformationName;
	
	private double neckFluffChance;
	private boolean neckFluffRequiresGreater;

	private boolean defaultPlural;
	private String determiner;
	private String name;
	private String namePlural;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String hairTransformationDescription;
	private String hairBodyDescription;
	
	private List<BodyPartTag> tags;
	
	/**
	 * @param skinType What this hair type has as a covering (i.e hair/scales/feather type).
	 * @param race What race has this hair type.
	 * @param transformationName The name that should be displayed when offering this hair type as a transformation. Should be something like "rabbit" or "cat".
	 * @param name The singular name of the hair. This will usually just be "hair".
	 * @param namePlural The plural name of the hair. This will usually just be "hairs".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this hair type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this hair type.
	 * @param hairTransformationDescription A paragraph describing a character's hair transforming into this hair type. Parsing assumes that the character already has this hair type and associated covering.
	 * @param hairBodyDescription A sentence or two to describe this hair type, as seen in the character view screen. It should follow the same format as all of the other entries in the HairType class.
	 * @param tags BodyPartTags whichshould be applied to this hair type.
	 */
	public AbstractHairType(AbstractBodyCoveringType skinType,
			AbstractRace race,
			String transformationName,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String hairTransformationDescription,
			String hairBodyDescription,
			List<BodyPartTag> tags) {
		
		this.coveringType = skinType;
		this.race = race;
		
		this.neckFluffRequiresGreater = true;
		this.neckFluffChance = 0;
		
		this.transformationName = transformationName;
		
		this.defaultPlural = false;
		this.determiner = "a head of";
		this.name = name;
		this.namePlural = namePlural;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.hairTransformationDescription = hairTransformationDescription;
		this.hairBodyDescription = hairBodyDescription;
		
		if(tags==null) {
			this.tags = new ArrayList<>();
		} else {
			this.tags = tags;
		}
	}

	public AbstractHairType(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile);

				this.mod = mod;
				this.fromExternalFile = true;
				
				this.race = Race.getRaceFromId(coreElement.getMandatoryFirstOf("race").getTextContent());
				this.coveringType = BodyCoveringType.getBodyCoveringTypeFromId(coreElement.getMandatoryFirstOf("coveringType").getTextContent());

				this.transformationName = coreElement.getMandatoryFirstOf("transformationName").getTextContent();
				
				this.neckFluffRequiresGreater = true;
				if(coreElement.getOptionalFirstOf("neckFluffChance").isPresent()) {
					neckFluffRequiresGreater = Boolean.valueOf(coreElement.getMandatoryFirstOf("neckFluffChance").getAttribute("requiresGreaterMorph"));
				}
				
				neckFluffChance = 0;
				if(coreElement.getOptionalFirstOf("neckFluffChance").isPresent()) {
					neckFluffChance = Float.valueOf(coreElement.getMandatoryFirstOf("neckFluffChance").getTextContent())/100d;
				}
				
				this.tags = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("tags").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("tags").getAllOf("tag")) {
						tags.add(BodyPartTag.getBodyPartTagFromId(e.getTextContent()));
					}
				}
				
				this.determiner = coreElement.getMandatoryFirstOf("determiner").getTextContent();
				this.name = coreElement.getMandatoryFirstOf("name").getTextContent();
				this.namePlural = coreElement.getMandatoryFirstOf("namePlural").getTextContent();
				this.defaultPlural = Boolean.valueOf(coreElement.getMandatoryFirstOf("namePlural").getAttribute("pluralByDefault"));
				
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
				
				this.hairTransformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.hairBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractHairType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}
	
	public boolean isAbleToBeGrabbedInSex() {
		return this.getTags().contains(BodyPartTag.HAIR_HANDLES_IN_SEX);
	}
	
	/**
	 * @return Chance for this hair type to spawn with neck fluff, from 0->1.0 representing 0->100%
	 */
	public double getNeckFluffChance() {
		return neckFluffChance;
	}

	/**
	 * @return true if neck fluff is only applied on spawn if the character is a greater morph.
	 */
	public boolean isNeckFluffRequiresGreater() {
		return neckFluffRequiresGreater;
	}
	
	@Override
	public String getTransformationNameOverride() {
		return transformationName;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return determiner;
	}

	@Override
	public boolean isDefaultPlural(GameCharacter gc) {
		return defaultPlural;
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		switch(gc.getBodyMaterial()) {
			case SLIME:
				return "slime-"+name;
			case PLANT:
				return "leaf-"+name;
			case SILICONE:
				return "silicone-"+name;
			default:
				return name;
		}
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(gc.getBodyMaterial()) {
			case SLIME:
				return "slime-"+namePlural;
			case PLANT:
				return "leaf-"+namePlural;
			case SILICONE:
				return "silicone-"+namePlural;
			default:
				return namePlural;
		}
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
		return getTFTypeModifier(HairType.getHairTypes(race));
	}

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, hairBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, hairTransformationDescription);
	}

	@Override
	public List<BodyPartTag> getTags() {
		return tags;
	}
}
