package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.tags.FaceTypeTag;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.FaceStructure;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.NoseType;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public abstract class AbstractFaceType implements BodyPartTypeInterface {

	private BodyCoveringType skinType;
	private Race race;

	private AbstractMouthType mouthType;
	private FaceStructure faceStructure;
	private NoseType noseType;
	
	private String faceTransformationDescription;
	private String faceBodyDescription;

	private List<FaceTypeTag> tags;

	/**
	 * @param skinType What covers this face type (i.e skin/fur/feather type).
	 * @param race What race has this face type.
	 * @param faceStructure The face structure associated with this face type.
	 * @param mouthType The type of mouth that this face type has.
	 * @param noseType The nose type associated with this face type.
	 * @param faceTransformationDescription A paragraph describing a character's face transforming into this face type. Parsing assumes that the character already has this face type and associated skin covering.
	 * @param faceBodyDescription A sentence or two to describe this face type, as seen in the character view screen. It should follow the same format as all of the other entries in the AssType class.
	 * @param tags A list of tags which help to define the features of this face type.
	 */
	public AbstractFaceType(
		BodyCoveringType skinType,
		Race race,
		FaceStructure faceStructure,
		AbstractMouthType mouthType,
		NoseType noseType,
		String faceTransformationDescription,
		String faceBodyDescription,
		List<FaceTypeTag> tags
	) {
		this.skinType = skinType;
		this.race = race;

		this.faceStructure = faceStructure;
		this.mouthType = mouthType;
		this.noseType = noseType;

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

	public FaceStructure getFaceStructure() {
		return faceStructure;
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		if(faceStructure.getNames()==null) {
			if(this.getTags().contains(FaceTypeTag.MUZZLE)) {
				return UtilText.returnStringAtRandom("muzzle", "face");
			}
			return "face";
		}
		return Util.randomItemFrom(faceStructure.getNames());
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(faceStructure.getNamesPlural()==null) {
			if(this.getTags().contains(FaceTypeTag.MUZZLE)) {
				return UtilText.returnStringAtRandom("muzzles", "faces");
			}
			return "faces";
		}
		return Util.randomItemFrom(faceStructure.getNamesPlural());
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(faceStructure.getDescriptorsFeminine());
		} else {
			return Util.randomItemFrom(faceStructure.getDescriptorsMasculine());
		}
	}

	@Override
	/**
	 * <b>This should never be used - the covering of an face is determined by the torso's covering!</b>
	 */
	public BodyCoveringType getBodyCoveringType(Body body) {
		if(body!=null) {
			return body.getTorso().getBodyCoveringType(body);
		}
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}

	public NoseType getNoseType() {
		return noseType;
	}

	public String getNoseNameSingular(GameCharacter gc) {
		return noseType.getName();
	}
	
	public String getNoseNamePlural(GameCharacter gc) {
		return noseType.getNamePlural();
	}

	public String getNoseDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(noseType.getDescriptorsFeminine());
		} else {
			return Util.randomItemFrom(noseType.getDescriptorsMasculine());
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

	public List<FaceTypeTag> getTags() {
		return tags;
	}
}
