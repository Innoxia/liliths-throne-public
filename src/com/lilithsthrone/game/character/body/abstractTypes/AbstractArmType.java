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
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.BodyPartClothingBlock;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3
 * @version 0.3.9.1
 * @author Innoxia
 */
public abstract class AbstractArmType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;

	private String transformationName;
	
	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;
	
	private boolean allowsFlight;

	private boolean underarmHairAllowed;
	
	private String name;
	private String namePlural;
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String handName;
	private String handNamePlural;
	private List<String> handDescriptorsMasculine;
	private List<String> handDescriptorsFeminine;

	private String fingerName;
	private String fingerNamePlural;
	private List<String> fingerDescriptorsMasculine;
	private List<String> fingerDescriptorsFeminine;

	private String armTransformationDescription;
	private String armBodyDescription;
	
	private List<BodyPartTag> armTags;
	
	/**
	 * @param coveringType What covers this arm type (i.e skin/fur/feather type).
	 * @param race What race has this arm type.
	 * @param name The singular name of the arm. This will usually just be "arm" or "wing".
	 * @param namePlural The plural name of the arm. This will usually just be "arms" or "wings".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this arm type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this arm type.
	 * @param handName The singular name of the hands associated with this arm type. This will usually just be "hand".
	 * @param handNamePlural The plural name of the hands associated with this arm type. This will usually just be "hands".
	 * @param handDescriptorsMasculine The descriptors that are applied to a masculine form of this hand.
	 * @param handDescriptorsFeminine The descriptors that are applied to a feminine form of this hand.
	 * @param fingerName The singular name of the fingers associated with this arm type. This will usually just be "finger".
	 * @param fingerNamePlural The plural name of the hands associated with this arm type. This will usually just be "fingers".
	 * @param fingerDescriptorsMasculine The descriptors that are applied to a masculine form of this fingers of this arm type's hand.
	 * @param fingerDescriptorsFeminine The descriptors that are applied to a feminine form of the fingers of this arm type's hand.
	 * @param armTransformationDescription A paragraph describing a character's arms transforming into this arm type. Parsing assumes that the character already has this arm type and associated skin covering.
	 * @param armBodyDescription A sentence or two to describe this arm type, as seen in the character view screen. It should follow the same format as all of the other entries in the ArmType class.
	 */
	public AbstractArmType(AbstractBodyCoveringType coveringType,
			AbstractRace race,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String handName,
			String handNamePlural,
			List<String> handDescriptorsMasculine,
			List<String> handDescriptorsFeminine,
			String fingerName,
			String fingerNamePlural,
			List<String> fingerDescriptorsMasculine,
			List<String> fingerDescriptorsFeminine,
			String armTransformationDescription,
			String armBodyDescription) {

		this.underarmHairAllowed = race.getRacialClass().isAnthroHair();
		
		this.coveringType = coveringType;
		this.race = race;

		this.transformationName = null; // Use default race transformation name
		
		this.allowsFlight = false;
		
		this.name = name;
		this.namePlural = namePlural;
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.handName = handName;
		this.handNamePlural = handNamePlural;
		this.handDescriptorsMasculine = handDescriptorsMasculine;
		this.handDescriptorsFeminine = handDescriptorsFeminine;

		this.fingerName = fingerName;
		this.fingerNamePlural = fingerNamePlural;
		this.fingerDescriptorsMasculine = fingerDescriptorsMasculine;
		this.fingerDescriptorsFeminine = fingerDescriptorsFeminine;
		
		this.armTransformationDescription = armTransformationDescription;
		this.armBodyDescription = armBodyDescription;
		
		this.armTags = Util.newArrayListOfValues(BodyPartTag.ARM_STANDARD);
	}
	
	public AbstractArmType(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				Document doc = Main.docBuilder.parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile);

				this.mod = mod;
				this.fromExternalFile = true;
				
				this.race = Race.getRaceFromId(coreElement.getMandatoryFirstOf("race").getTextContent());
				this.coveringType = BodyCoveringType.getBodyCoveringTypeFromId(coreElement.getMandatoryFirstOf("coveringType").getTextContent());

				this.transformationName = coreElement.getMandatoryFirstOf("transformationName").getTextContent();

				this.underarmHairAllowed = race.getRacialClass().isAnthroHair();
				if(coreElement.getOptionalFirstOf("underarmHairAllowed").isPresent()) {
					this.underarmHairAllowed = Boolean.valueOf(coreElement.getMandatoryFirstOf("underarmHairAllowed").getTextContent());
				}
				
				this.allowsFlight = Boolean.valueOf(coreElement.getMandatoryFirstOf("allowsFlight").getTextContent());

				this.armTags = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("tags").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("tags").getAllOf("tag")) {
						armTags.add(BodyPartTag.valueOf(e.getTextContent()));
					}
				}
				if(armTags.isEmpty()) {
					armTags.add(BodyPartTag.ARM_STANDARD);
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

				this.handName = coreElement.getMandatoryFirstOf("handName").getTextContent();
				this.handNamePlural = coreElement.getMandatoryFirstOf("handNamePlural").getTextContent();
				this.handDescriptorsMasculine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("handDescriptorsMasculine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("handDescriptorsMasculine").getAllOf("descriptor")) {
						handDescriptorsMasculine.add(e.getTextContent());
					}
				}
				this.handDescriptorsFeminine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("handDescriptorsFeminine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("handDescriptorsFeminine").getAllOf("descriptor")) {
						handDescriptorsFeminine.add(e.getTextContent());
					}
				}

				this.fingerName = coreElement.getMandatoryFirstOf("fingerName").getTextContent();
				this.fingerNamePlural = coreElement.getMandatoryFirstOf("fingerNamePlural").getTextContent();
				this.fingerDescriptorsMasculine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("fingerDescriptorsMasculine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("fingerDescriptorsMasculine").getAllOf("descriptor")) {
						fingerDescriptorsMasculine.add(e.getTextContent());
					}
				}
				this.fingerDescriptorsFeminine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("fingerDescriptorsFeminine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("fingerDescriptorsFeminine").getAllOf("descriptor")) {
						fingerDescriptorsFeminine.add(e.getTextContent());
					}
				}
				
				this.armTransformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.armBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractArmType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}
	
	public boolean isUnderarmHairAllowed() {
		return underarmHairAllowed;
	}

	public boolean allowsFlight() {
		return allowsFlight;
	}

	@Override
	public String getTransformationNameOverride() {
		return transformationName;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getArmRows()==1) {
			return "a pair of";
		} else {
			return Util.intToString(gc.getArmRows())+" pairs of";
		}
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
	
	public String getHandsNameSingular(GameCharacter gc) {
		return handName;
	}
	
	public String getHandsNamePlural(GameCharacter gc) {
		return handNamePlural;
	}

	public String getHandsDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(handDescriptorsFeminine);
		} else {
			return Util.randomItemFrom(handDescriptorsMasculine);
		}
	}
	
	public String getFingersNameSingular(GameCharacter gc) {
		return fingerName;
	}
	
	public String getFingersNamePlural(GameCharacter gc) {
		return fingerNamePlural;
	}

	public String getFingersDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(fingerDescriptorsFeminine);
		} else {
			return Util.randomItemFrom(fingerDescriptorsMasculine);
		}
	}
	
	@Override
	public BodyPartClothingBlock getBodyPartClothingBlock() {
		if(this.getTags().contains(BodyPartTag.ARM_WINGS_FEATHERED)) {
			return new BodyPartClothingBlock(
					Util.newArrayListOfValues(
							InventorySlot.HAND,
							InventorySlot.WRIST,
							InventorySlot.TORSO_OVER,
							InventorySlot.TORSO_UNDER),
					Race.HARPY,
					"Due to the fact that [npc.nameHasFull] bird-like wings instead of arms, only specialist clothing can be worn in this slot.",
					Util.newArrayListOfValues(
						ItemTag.FITS_FEATHERED_ARM_WINGS,
						ItemTag.FITS_FEATHERED_ARM_WINGS_EXCLUSIVE,
						ItemTag.FITS_ARM_WINGS,
						ItemTag.FITS_ARM_WINGS_EXCLUSIVE
					));
		}
		if(this.getTags().contains(BodyPartTag.ARM_WINGS_LEATHERY)) {
			return new BodyPartClothingBlock(
					Util.newArrayListOfValues(
							InventorySlot.HAND,
							InventorySlot.WRIST,
							InventorySlot.TORSO_OVER,
							InventorySlot.TORSO_UNDER),
					Race.BAT_MORPH,
					"Due to the fact that [npc.nameHasFull] leathery wings instead of arms, only specialist clothing can be worn in this slot.",
					Util.newArrayListOfValues(
						ItemTag.FITS_LEATHERY_ARM_WINGS,
						ItemTag.FITS_LEATHERY_ARM_WINGS_EXCLUSIVE,
						ItemTag.FITS_ARM_WINGS,
						ItemTag.FITS_ARM_WINGS_EXCLUSIVE
					));
		}
		return null;
	}
	
//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, armBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, armTransformationDescription);
	}

	@Override
	public List<BodyPartTag> getTags() {
		return armTags;
	}
}
