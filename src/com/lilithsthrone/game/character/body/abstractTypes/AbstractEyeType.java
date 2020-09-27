package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.3.9.1
 * @author Innoxia
 */
public abstract class AbstractEyeType implements BodyPartTypeInterface {

	private AbstractBodyCoveringType skinType;
	private AbstractRace race;

	private int defaultPairCount;
	
	private EyeShape defaultIrisShape;
	private EyeShape defaultPupilShape;
	
	private String transformationName;
	private String name;
	private String namePlural;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String eyeTransformationDescription;
	private String eyeBodyDescription;
	
	/**
	 * @param skinType What covers this eye type (i.e skin/fur/feather type).
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
	public AbstractEyeType(AbstractBodyCoveringType skinType,
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
		
		this.skinType = skinType;
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
	public String getDeterminer(GameCharacter gc) {
		if(gc.getEyePairs()==1) {
			return "a pair of";
		}
		return Util.intToString(gc.getEyePairs())+" pairs of";
	}

	@Override
	public boolean isDefaultPlural() {
		return true;
	}

	@Override
	public String getTransformName() {
		return transformationName;
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
		return skinType;
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
