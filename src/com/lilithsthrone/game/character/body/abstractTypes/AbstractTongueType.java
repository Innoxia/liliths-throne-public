package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.3.9.1
 * @author Innoxia
 */
public abstract class AbstractTongueType implements BodyPartTypeInterface {

	private BodyCoveringType skinType;
	private AbstractRace race;

	private int defaultLength;
	
	private String transformationName;
	private String name;
	private String namePlural;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String tongueBodyDescription;
	
	private List<TongueModifier> defaultRacialTongueModifiers;
	
	/**
	 * @param skinType What covers this tongue type.
	 * @param race What race has this tongue type.
	 * @param defaultLength The default length of this tongue, measured in cm.
	 * @param transformationName The name that should be displayed when offering this tongue type as a transformation. Should be something like "upright rabbit" or "floppy rabbit".
	 * @param name The singular name of the tongue. This will usually just be "tongue".
	 * @param namePlural The plural name of the tongue. This will usually just be "tongues".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this tongue type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this tongue type.
	 * @param tongueBodyDescription A sentence or two to describe this tongue type, as seen in the character view screen. It should follow the same format as all of the other entries in the TongueType class.
	 * @param defaultRacialTongueModifiers The default modifiers that this tongue type spawns with.
	 */
	public AbstractTongueType(BodyCoveringType skinType,
			AbstractRace race,
			int defaultLength,
			String transformationName,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String tongueBodyDescription,
			List<TongueModifier> defaultRacialTongueModifiers) {
		
		this.skinType = skinType;
		this.race = race;
		
		this.defaultLength = defaultLength;
		
		this.transformationName = transformationName;
		this.name = name;
		this.namePlural = namePlural;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.tongueBodyDescription = tongueBodyDescription;
		
		this.defaultRacialTongueModifiers = defaultRacialTongueModifiers;
	}
	
	public int getDefaultLength() {
		return defaultLength;
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
	public AbstractRace getRace() {
		return race;
	}

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, tongueBodyDescription);
	}
	
	
	public List<TongueModifier> getDefaultRacialTongueModifiers() {
		return defaultRacialTongueModifiers;
	}
	
}
