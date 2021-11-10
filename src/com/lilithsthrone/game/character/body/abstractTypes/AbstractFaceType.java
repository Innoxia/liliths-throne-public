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
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.MouthType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractFaceType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;
	
	private String transformationName;
	
	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;

	private boolean facialHairAllowed;
	
	private AbstractMouthType mouthType;
	
	private List<String> names;
	private List<String> namesPlural;
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;

	private String noseName;
	private String noseNamePlural;
	private List<String> noseDescriptorsMasculine;
	private List<String> noseDescriptorsFeminine;
	
	private String faceTransformationDescription;
	private String faceBodyDescription;
	private String faceBodyDescriptionFeral = "[npc.SheHasFull] the [npc.feminineDescriptor(true)] face of a feral [npc.legRace], which is [npc.materialDescriptor] [npc.faceFullDescription(true)].";

	private List<BodyPartTag> tags;

	public AbstractFaceType(AbstractBodyCoveringType coveringType,
			AbstractRace race,
			AbstractMouthType mouthType,
			List<String> names,
			List<String> namesPlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String noseName,
			String noseNamePlural,
			List<String> noseDescriptorsMasculine,
			List<String> noseDescriptorsFeminine,
			String faceTransformationDescription,
			String faceBodyDescription,
			List<BodyPartTag> tags) {
		this(
			coveringType,
			race,
			mouthType,
			names,
			namesPlural,
			descriptorsMasculine,
			descriptorsFeminine,
			noseName,
			noseNamePlural,
			noseDescriptorsMasculine,
			noseDescriptorsFeminine,
			faceTransformationDescription,
			faceBodyDescription,
			"",
			tags
		);
	}
	
	/**
	 * @param coveringType What covers this face type (i.e skin/fur/feather type).
	 * @param race What race has this face type.
	 * @param faceType The type of face that this face type has.
	 * @param names A list of singular names for this face type. Pass in null to use generic names.
	 * @param namesPlural A list of plural names for this face type. Pass in null to use generic names.
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this face type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this face type.
	 * @param faceTransformationDescription A paragraph describing a character's face transforming into this face type. Parsing assumes that the character already has this face type and associated skin covering.
	 * @param faceBodyDescription A sentence or two to describe this face type, as seen in the character view screen. It should follow the same format as all of the other entries in the AssType class.
	 * @param tags A list of tags which help to define the features of this face type.
	 */
	public AbstractFaceType(AbstractBodyCoveringType coveringType,
			AbstractRace race,
			AbstractMouthType mouthType,
			List<String> names,
			List<String> namesPlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String noseName,
			String noseNamePlural,
			List<String> noseDescriptorsMasculine,
			List<String> noseDescriptorsFeminine,
			String faceTransformationDescription,
			String faceBodyDescription,
			String faceBodyDescriptionFeral,
			List<BodyPartTag> tags) {
		
		this.facialHairAllowed = race.getRacialClass().isAnthroHair();
		
		this.coveringType = coveringType;
		this.race = race;
		this.mouthType = mouthType;

		this.transformationName = null; // Use default race transformation name
		
		this.names = names;
		this.namesPlural = namesPlural;
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.noseName = noseName;
		this.noseNamePlural = noseNamePlural;
		this.noseDescriptorsMasculine = noseDescriptorsMasculine;
		this.noseDescriptorsFeminine = noseDescriptorsFeminine;
		
		this.faceTransformationDescription = faceTransformationDescription;
		this.faceBodyDescription = faceBodyDescription;
		if (!faceBodyDescriptionFeral.isEmpty()) {
			this.faceBodyDescriptionFeral = faceBodyDescriptionFeral;
		}
		
		this.tags = tags;
	}

	public AbstractFaceType(File XMLFile, String author, boolean mod) {
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
				
				this.facialHairAllowed = race.getRacialClass().isAnthroHair();
				if(coreElement.getOptionalFirstOf("facialHairAllowed").isPresent()) {
					this.facialHairAllowed = Boolean.valueOf(coreElement.getMandatoryFirstOf("facialHairAllowed").getTextContent());
				}
				
				this.transformationName = coreElement.getMandatoryFirstOf("transformationName").getTextContent();
				
				this.mouthType = MouthType.getMouthTypeFromId(coreElement.getMandatoryFirstOf("mouthType").getTextContent());

				this.tags = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("tags").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("tags").getAllOf("tag")) {
						tags.add(BodyPartTag.getBodyPartTagFromId(e.getTextContent()));
					}
				}
				
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
				
				this.noseName = coreElement.getMandatoryFirstOf("noseName").getTextContent();
				this.noseNamePlural = coreElement.getMandatoryFirstOf("noseNamePlural").getTextContent();
				this.noseDescriptorsMasculine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("noseDescriptorsMasculine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("noseDescriptorsMasculine").getAllOf("descriptor")) {
						noseDescriptorsMasculine.add(e.getTextContent());
					}
				}
				this.noseDescriptorsFeminine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("noseDescriptorsFeminine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("noseDescriptorsFeminine").getAllOf("descriptor")) {
						noseDescriptorsFeminine.add(e.getTextContent());
					}
				}
				
				this.faceTransformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.faceBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				if (coreElement.getOptionalFirstOf("bodyDescriptionFeral").isPresent()) {
					this.faceBodyDescriptionFeral = coreElement.getMandatoryFirstOf("bodyDescriptionFeral").getTextContent();
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractFaceType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}
	
	public boolean isFacialHairAllowed() {
		return facialHairAllowed;
	}

	public AbstractMouthType getMouthType() {
		return mouthType;
	}

	@Override
	public String getTransformationNameOverride() {
		return transformationName;
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
			if(this.getTags().contains(BodyPartTag.FACE_MUZZLE)) {
				return UtilText.returnStringAtRandom("muzzle", "face");
			}
			return "face";
		}
		return Util.randomItemFrom(names);
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(namesPlural==null || namesPlural.isEmpty()) {
			if(this.getTags().contains(BodyPartTag.FACE_MUZZLE)) {
				return UtilText.returnStringAtRandom("muzzles", "faces");
			}
			return "faces";
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
//	/**
//	 * <b>This should never be used - the covering of a face is determined by the torso's covering!</b>
//	 */
	public AbstractBodyCoveringType getBodyCoveringType(Body body) {
//		if(body!=null) {
//			return body.getTorso().getBodyCoveringType(body);
//		}
		return coveringType;
	}

	@Override
	public AbstractRace getRace() {
		return race;
	}

	public String getNoseNameSingular(GameCharacter gc) {
		return noseName;
	}
	
	public String getNoseNamePlural(GameCharacter gc) {
		return noseNamePlural;
	}

	public String getNoseDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(noseDescriptorsFeminine);
		} else {
			return Util.randomItemFrom(noseDescriptorsMasculine);
		}
	}
	
//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, owner.isFeral() ? faceBodyDescriptionFeral : faceBodyDescription);
	}
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, faceTransformationDescription);
	}

	@Override
	public TFModifier getTFModifier() {
		return getTFTypeModifier(FaceType.getFaceTypes(race));
	}

	@Override
	public List<BodyPartTag> getTags() {
		return tags;
	}
}
