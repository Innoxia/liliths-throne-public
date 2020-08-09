package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.8.2
 * @version 0.3.8.2
 * @author Innoxia
 */
public abstract class AbstractHairType implements BodyPartTypeInterface {

	private BodyCoveringType coveringType;
	private Race race;

	private boolean ableToBeGrabbedInSex; //TODO make sure this is accounted for
	
	private String transformationName;
	private String name;
	private String namePlural;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String hairTransformationDescription;
	private String hairBodyDescription;
	
	/**
	 * @param skinType What this hair type has as a covering (i.e hair/scales/feather type).
	 * @param race What race has this hair type.
	 * @param ableToBeGrabbedInSex True if this hair is suitable to be grabbed and used as extra leverage during sex. Should only be false if it would be impossible to comfortably grab (e.g. if the hair type was sharp, pointy scales or something).
	 * @param transformationName The name that should be displayed when offering this hair type as a transformation. Should be something like "rabbit" or "cat".
	 * @param name The singular name of the hair. This will usually just be "hair".
	 * @param namePlural The plural name of the hair. This will usually just be "hairs".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this hair type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this hair type.
	 * @param hairTransformationDescription A paragraph describing a character's hair transforming into this hair type. Parsing assumes that the character already has this hair type and associated covering.
	 * @param hairBodyDescription A sentence or two to describe this hair type, as seen in the character view screen. It should follow the same format as all of the other entries in the HairType class.
	 */
	public AbstractHairType(BodyCoveringType skinType,
			Race race,
			boolean ableToBeGrabbedInSex,
			String transformationName,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String hairTransformationDescription,
			String hairBodyDescription) {
		
		this.coveringType = skinType;
		this.race = race;
		
		this.ableToBeGrabbedInSex = ableToBeGrabbedInSex;
		
		this.transformationName = transformationName;
		this.name = name;
		this.namePlural = namePlural;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.hairTransformationDescription = hairTransformationDescription;
		this.hairBodyDescription = hairBodyDescription;
	}
	
	public boolean isAbleToBeGrabbedInSex() {
		return ableToBeGrabbedInSex;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "a head of";
	}

	@Override
	public boolean isDefaultPlural() {
		return false;
	}

	@Override
	public String getTransformName() {
		return transformationName;
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		if(gc.getBodyMaterial()==BodyMaterial.SLIME) {
			return "slime-"+name;
		}
		return name;
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(gc.getBodyMaterial()==BodyMaterial.SLIME) {
			return "slime-"+namePlural;
		}
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
	public BodyCoveringType getBodyCoveringType(Body body) {
		return coveringType;
	}

	@Override
	public Race getRace() {
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
}
