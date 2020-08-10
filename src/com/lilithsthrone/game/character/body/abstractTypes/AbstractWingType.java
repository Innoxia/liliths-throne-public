package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.8.2
 * @version 0.3.9.1
 * @author Innoxia
 */
public abstract class AbstractWingType implements BodyPartTypeInterface {

	private BodyCoveringType skinType;
	private AbstractRace race;
	
	private boolean allowsFlight;

	private String transformationName;
	private String name;
	private String namePlural;

	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String wingTransformationDescription;
	private String wingBodyDescription;
	
	/**
	 * @param skinType What covers this wing type (i.e skin/fur/feather type).
	 * @param race What race has this wing type.
	 * @param allowsFlight Whether this wing type is capable of granting the character flight.
	 * @param transformationName The name that should be displayed when offering this wing type as a transformation. Should be something like "curved" or "straight".
	 * @param name The singular name of the wing. This will usually just be "wing" or "antler".
	 * @param namePlural The plural name of the wing. This will usually just be "wings" or "antlers".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this wing type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this wing type.
	 * @param wingTransformationDescription A paragraph describing a character's wings transforming into this wing type. Parsing assumes that the character already has this wing type and associated skin covering.
	 * @param wingBodyDescription A sentence or two to describe this wing type, as seen in the character view screen. It should follow the same format as all of the other entries in the WingType class.
	 */
	public AbstractWingType(
			BodyCoveringType skinType,
			AbstractRace race,
			boolean allowsFlight,
			String transformationName,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String wingTransformationDescription,
			String wingBodyDescription) {
		
		this.skinType = skinType;
		this.race = race;

		this.allowsFlight = allowsFlight;
		
		this.transformationName = transformationName;
		this.name = name;
		this.namePlural = namePlural;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.wingTransformationDescription = wingTransformationDescription;
		this.wingBodyDescription = wingBodyDescription;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "a pair of";
	}

	public boolean allowsFlight() {
		return allowsFlight;
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
	public AbstractRace getRace() {
		return race;
	}

	@Override
	public TFModifier getTFModifier() {
		return this == WingType.NONE ? TFModifier.REMOVAL : getTFTypeModifier(WingType.getWingTypes(race));
	}

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, wingBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, wingTransformationDescription);
	}
}
