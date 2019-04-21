package com.lilithsthrone.game.character.body.types;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.2
 * @version 0.3.2
 * @author Innoxia
 */
public abstract class AbstractEarType implements BodyPartTypeInterface {

	private BodyCoveringType skinType;
	private Race race;

	private boolean ableToBeUsedAsHandlesInSex;
	
	private String transformationName;
	private String name;
	private String namePlural;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String earTransformationDescription;
	private String earBodyDescription;
	
	/**
	 * @param skinType What covers this ear type (i.e skin/fur/feather type).
	 * @param race What race has this ear type.
	 * @param ableToBeUsedAsHandlesInSex True if these ears are long and flexible enough to be grabbed and used as extra leverage during sex.
	 * @param transformationName The name that should be displayed when offering this ear type as a transformation. Should be something like "upright rabbit" or "floppy rabbit".
	 * @param name The singular name of the ear. This will usually just be "ear".
	 * @param namePlural The plural name of the ear. This will usually just be "ears".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this ear type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this ear type.
	 * @param earTransformationDescription A paragraph describing a character's ears transforming into this ear type. Parsing assumes that the character already has this ear type and associated skin covering.
	 * @param earBodyDescription A sentence or two to describe this ear type, as seen in the character view screen. It should follow the same format as all of the other entries in the EarType class.
	 */
	public AbstractEarType(BodyCoveringType skinType,
			Race race,
			boolean ableToBeUsedAsHandlesInSex,
			String transformationName,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String earTransformationDescription, String earBodyDescription) {
		
		this.skinType = skinType;
		this.race = race;
		
		this.ableToBeUsedAsHandlesInSex = ableToBeUsedAsHandlesInSex;
		
		this.transformationName = transformationName;
		this.name = name;
		this.namePlural = namePlural;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.earTransformationDescription = earTransformationDescription;
		this.earBodyDescription = earBodyDescription;
	}
	
	public boolean isAbleToBeUsedAsHandlesInSex() {
		return ableToBeUsedAsHandlesInSex;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "a pair of";
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
	public BodyCoveringType getBodyCoveringType(Body body) {
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, earBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, earTransformationDescription);
	}
}
