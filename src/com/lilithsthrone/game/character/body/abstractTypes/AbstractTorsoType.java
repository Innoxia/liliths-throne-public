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
 * @since 0.3.8.9
 * @version 0.3.8.9
 * @author Innoxia
 */
public abstract class AbstractTorsoType implements BodyPartTypeInterface {

	private BodyCoveringType skinType;
	private Race race;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String skinTransformationDescription;
	private String skinBodyDescription;
	
	/**
	 * @param skinType What constitutes this skin covering (i.e skin/fur/feather type).
	 * @param race What race has this skin type.
	 * @param name The singular name of the skin. This will usually just be "skin".
	 * @param namePlural The plural name of the skin. This will usually just be "skin".
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this skin type.
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this skin type.
	 * @param skinTransformationDescription A paragraph describing a character's skins transforming into this skin type. Parsing assumes that the character already has this skin type and associated skin covering.
	 * @param skinBodyDescription A sentence or two to describe this skin type, as seen in the character view screen. It should follow the same format as all of the other entries in the SkinType class.
	 */
	public AbstractTorsoType(BodyCoveringType skinType,
			Race race,
			List<String> descriptorsFeminine,
			List<String> descriptorsMasculine,
			String skinTransformationDescription,
			String skinBodyDescription) {
		
		this.skinType = skinType;
		this.race = race;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.skinTransformationDescription = skinTransformationDescription;
		this.skinBodyDescription = skinBodyDescription;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return "a layer of";
	}

	@Override
	public boolean isDefaultPlural() {
		return false;
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return skinType.getName(gc);
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return skinType.getNamePlural(gc);
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
		return UtilText.parse(owner, skinBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, skinTransformationDescription);
	}
}
