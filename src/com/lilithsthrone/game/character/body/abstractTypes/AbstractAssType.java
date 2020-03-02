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
 * @since 0.3
 * @version 0.3
 * @author Innoxia
 */
public abstract class AbstractAssType implements BodyPartTypeInterface {

	private BodyCoveringType skinType;
	private Race race;
	private AbstractAnusType anusType;
	
	private List<String> names;
	private List<String> namesPlural;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String assTransformationDescription;
	private String assBodyDescription;
	
	/**
	 * @param skinType What covers this ass type (i.e skin/fur/feather type). This is never used, as skin type covering ass is determined by torso covering.
	 * @param race What race has this ass type.
	 * @param anusType The type of anus that this ass type has.
	 * @param names A list of singular names for this ass type. Pass in null to use generic names.
	 * @param namesPlural A list of plural names for this ass type. Pass in null to use generic names.
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this ass type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this ass type.
	 * @param assTransformationDescription A paragraph describing a character's ass transforming into this ass type. Parsing assumes that the character already has this ass type and associated skin covering.
	 * @param assBodyDescription A sentence or two to describe this ass type, as seen in the character view screen. It should follow the same format as all of the other entries in the AssType class.
	 */
	public AbstractAssType(BodyCoveringType skinType,
			Race race,
			AbstractAnusType anusType,
			List<String> names,
			List<String> namesPlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String assTransformationDescription,
			String assBodyDescription) {
		
		this.skinType = skinType;
		this.race = race;
		this.anusType = anusType;
		
		this.names = names;
		this.namesPlural = namesPlural;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.assTransformationDescription = assTransformationDescription;
		this.assBodyDescription = assBodyDescription;
	}
	
	public AbstractAnusType getAnusType() {
		return anusType;
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
			return UtilText.returnStringAtRandom("ass", "rear end", "butt", "rump");
		}
		return Util.randomItemFrom(names);
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(namesPlural==null) {
			return UtilText.returnStringAtRandom("asses", "rear ends", "butts", "rumps");
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
	/**
	 * <b>This should never be used - the covering of an ass is determined by the torso's covering!</b>
	 */
	public BodyCoveringType getBodyCoveringType(Body body) {
		if(body!=null) {
			return body.getSkin().getBodyCoveringType(body);
		}
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, assBodyDescription);
	}
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, assTransformationDescription);
	}
}
