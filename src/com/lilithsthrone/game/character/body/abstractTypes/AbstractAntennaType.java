package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public abstract class AbstractAntennaType implements BodyPartTypeInterface {

	private BodyCoveringType skinType;
	private Race race;

	private String transformationName;
	private String name;
	private String namePlural;

	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String antennaTransformationDescription;
	private String antennaBodyDescription;
	
	/**
	 * @param skinType What covers this antenna type (i.e skin/fur/feather type).
	 * @param race What race has this antenna type.
	 * @param transformationName The name that should be displayed when offering this antenna type as a transformation. Should be something like "curved" or "straight".
	 * @param name The singular name of the antenna. This will usually just be "antenna" or "antler".
	 * @param namePlural The plural name of the antenna. This will usually just be "antennae" or "antlers".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this antenna type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this antenna type.
	 * @param antennaTransformationDescription A paragraph describing a character's antennae transforming into this antenna type. Parsing assumes that the character already has this antenna type and associated skin covering.
	 * @param antennaBodyDescription A sentence or two to describe this antenna type, as seen in the character view screen. It should follow the same format as all of the other entries in the AntennaType class.
	 */
	public AbstractAntennaType(
			BodyCoveringType skinType,
			Race race,
			String transformationName,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String antennaTransformationDescription,
			String antennaBodyDescription) {
		
		this.skinType = skinType;
		this.race = race;
		
		this.transformationName = transformationName;
		this.name = name;
		this.namePlural = namePlural;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.antennaTransformationDescription = antennaTransformationDescription;
		this.antennaBodyDescription = antennaBodyDescription;
	}
	
	public boolean allowsFlight() {
		return false;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getAntennaRows()==1) {
			return "a pair of";
			
		} else {
			return Util.intToString(gc.getAntennaRows())+" pairs of";
		}
	}
	
	@Override
	public String getTransformName() {
		return transformationName;
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
	public BodyCoveringType getBodyCoveringType(Body body) {
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, antennaBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, antennaTransformationDescription);
	}
}
