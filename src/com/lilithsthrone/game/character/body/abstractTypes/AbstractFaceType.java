package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.3.9.1
 * @author Innoxia
 */
public abstract class AbstractFaceType implements BodyPartTypeInterface {

	private AbstractBodyCoveringType skinType;
	private AbstractRace race;
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

	private List<BodyPartTag> tags;
	
	/**
	 * @param skinType What covers this face type (i.e skin/fur/feather type).
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
	public AbstractFaceType(AbstractBodyCoveringType skinType,
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
		
		this.skinType = skinType;
		this.race = race;
		this.mouthType = mouthType;
		
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
		
		this.tags = tags;
	}
	
	public AbstractMouthType getMouthType() {
		return mouthType;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "";
	}
	
	@Override
	public boolean isDefaultPlural() {
		return false;
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		if(names==null) {
			if(this.getTags().contains(BodyPartTag.FACE_MUZZLE)) {
				return UtilText.returnStringAtRandom("muzzle", "face");
			}
			return "face";
		}
		return Util.randomItemFrom(names);
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(namesPlural==null) {
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
		return skinType;
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
		return UtilText.parse(owner, faceBodyDescription);
	}
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, faceTransformationDescription);
	}

	@Override
	public TFModifier getTFModifier() {
		return getTFTypeModifier(FaceType.getFaceTypes(race));
	}

	public List<BodyPartTag> getTags() {
		return tags;
	}
}
