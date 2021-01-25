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
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.8.9
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractTorsoType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;

	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;

	private String transformationName;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String skinTransformationDescription;
	private String skinBodyDescription;
	
	private List<BodyPartTag> torsoTags;
	
	/**
	 * @param coveringType What constitutes this skin covering (i.e skin/fur/feather type).
	 * @param race What race has this skin type.
	 * @param name The singular name of the skin. This will usually just be "skin".
	 * @param namePlural The plural name of the skin. This will usually just be "skin".
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this skin type.
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this skin type.
	 * @param skinTransformationDescription A paragraph describing a character's skins transforming into this skin type. Parsing assumes that the character already has this skin type and associated skin covering.
	 * @param skinBodyDescription A sentence or two to describe this skin type, as seen in the character view screen. It should follow the same format as all of the other entries in the SkinType class.
	 */
	public AbstractTorsoType(AbstractBodyCoveringType coveringType,
			AbstractRace race,
			List<String> descriptorsFeminine,
			List<String> descriptorsMasculine,
			String skinTransformationDescription,
			String skinBodyDescription) {
		
		this.coveringType = coveringType;
		this.race = race;

		this.transformationName = null; // Use default race transformation name
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.skinTransformationDescription = skinTransformationDescription;
		this.skinBodyDescription = skinBodyDescription;
		
		this.torsoTags = new ArrayList<>();
	}
	
	public AbstractTorsoType(File XMLFile, String author, boolean mod) {
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
				
				this.skinTransformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.skinBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				
				this.torsoTags = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("tags").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("tags").getAllOf("tag")) {
						torsoTags.add(BodyPartTag.valueOf(e.getTextContent()));
					}
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractTorsoType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}
	
	@Override
	public String getTransformationNameOverride() {
		return transformationName;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return "a layer of";
	}

	@Override
	public boolean isDefaultPlural(GameCharacter gc) {
		return false;
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return coveringType.getName(gc);
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return coveringType.getNamePlural(gc);
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
		return UtilText.parse(owner, skinBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, skinTransformationDescription);
	}

	@Override
	public List<BodyPartTag> getTags() {
		return torsoTags;
	}
}
