package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3
 * @version 0.3.9.1
 * @author Innoxia
 */
public abstract class AbstractArmType implements BodyPartTypeInterface {

	private AbstractBodyCoveringType skinType;
	private AbstractRace race;
	
	private String name;
	private String namePlural;
	
	private String handName;
	private String handNamePlural;
	private List<String> handDescriptorsMasculine;
	private List<String> handDescriptorsFeminine;

	private String fingerName;
	private String fingerNamePlural;
	private List<String> fingerDescriptorsMasculine;
	private List<String> fingerDescriptorsFeminine;

	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String armTransformationDescription;
	private String armBodyDescription;
	
	/**
	 * @param skinType What covers this arm type (i.e skin/fur/feather type).
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
	public AbstractArmType(AbstractBodyCoveringType skinType,
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
		
		this.skinType = skinType;
		this.race = race;
		
		this.name = name;
		this.namePlural = namePlural;
		
		this.handName = handName;
		this.handNamePlural = handNamePlural;
		this.handDescriptorsMasculine = handDescriptorsMasculine;
		this.handDescriptorsFeminine = handDescriptorsFeminine;

		this.fingerName = fingerName;
		this.fingerNamePlural = fingerNamePlural;
		this.fingerDescriptorsMasculine = fingerDescriptorsMasculine;
		this.fingerDescriptorsFeminine = fingerDescriptorsFeminine;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.armTransformationDescription = armTransformationDescription;
		this.armBodyDescription = armBodyDescription;
	}
	
	public boolean allowsFlight() {
		return false;
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
	public boolean isDefaultPlural() {
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
		return skinType;
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
		return Util.newArrayListOfValues(BodyPartTag.ARM_STANDARD);
	}
}
