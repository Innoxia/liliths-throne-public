package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.tags.ArmTypeTag;
import com.lilithsthrone.game.character.body.types.ArmStructure;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.FingerType;
import com.lilithsthrone.game.character.body.types.HandType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3
 * @version 0.3.9.1
 * @author Innoxia
 */
public abstract class AbstractArmType implements BodyPartTypeInterface {

	private BodyCoveringType skinType;
	private AbstractRace race;
	
	private ArmStructure armStructure;
	private HandType handType;
	private FingerType fingerType;
	
	private String armTransformationDescription;
	private String armBodyDescription;
	
	/**
	 * @param skinType What covers this arm type (i.e skin/fur/feather type).
	 * @param race What race has this arm type.
	 * @param armStructure The arm structure associated with this arm type.
	 * @param handType The hand structure associated with this arm type.
	 * @param fingerType The finger structure associated with this arm type.
	 * @param armTransformationDescription A paragraph describing a character's arms transforming into this arm type. Parsing assumes that the character already has this arm type and associated skin covering.
	 * @param armBodyDescription A sentence or two to describe this arm type, as seen in the character view screen. It should follow the same format as all of the other entries in the ArmType class.
	 */
	public AbstractArmType(BodyCoveringType skinType,
			AbstractRace race,
			ArmStructure armStructure,
			HandType handType,
			FingerType fingerType,
			String armTransformationDescription,
			String armBodyDescription) {
		
		this.skinType = skinType;
		this.race = race;
		
		this.armStructure = armStructure;
		this.handType = handType;
		this.fingerType = fingerType;
		
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
		return armStructure.getName();
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return armStructure.getNamePlural();
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(armStructure.getDescriptorsFeminine());
		} else {
			return Util.randomItemFrom(armStructure.getDescriptorsMasculine());
		}
	}

	@Override
	public BodyCoveringType getBodyCoveringType(Body body) {
		return skinType;
	}

	@Override
	public AbstractRace getRace() {
		return race;
	}

	public ArmStructure getArmStructure() {
		return armStructure;
	}

	public HandType getHandType() {
		return handType;
	}

	public FingerType getFingerType() {
		return fingerType;
	}
	

	public String getHandsNameSingular(GameCharacter gc) {
		return handType.getName();
	}
	
	public String getHandsNamePlural(GameCharacter gc) {
		return handType.getNamePlural();
	}

	public String getHandsDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(handType.getDescriptorsFeminine());
		} else {
			return Util.randomItemFrom(handType.getDescriptorsMasculine());
		}
	}
	
	public String getFingersNameSingular(GameCharacter gc) {
		return fingerType.getName();
	}
	
	public String getFingersNamePlural(GameCharacter gc) {
		return fingerType.getNamePlural();
	}

	public String getFingersDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(fingerType.getDescriptorsFeminine());
		} else {
			return Util.randomItemFrom(fingerType.getDescriptorsMasculine());
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

//	@Override
	public List<ArmTypeTag> getTags() {
		return Util.newArrayListOfValues(ArmTypeTag.STANDARD);
	}
}
