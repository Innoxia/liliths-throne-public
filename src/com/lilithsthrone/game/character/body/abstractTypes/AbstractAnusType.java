package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public abstract class AbstractAnusType implements BodyPartTypeInterface {

	private BodyCoveringType skinType;
	private Race race;
	
	private List<String> names;
	private List<String> namesPlural;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private List<OrificeModifier> defaultRacialOrificeModifiers;
	
	/**
	 * @param skinType What covers this anus.
	 * @param race What race has this ass type.
	 * @param names A list of singular names for this ass type. Pass in null to use generic names.
	 * @param namesPlural A list of plural names for this ass type. Pass in null to use generic names.
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this ass type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this ass type.
	 * @param defaultRacialOrificeModifiers Which modifiers this anus naturally spawns with.
	 */
	public AbstractAnusType(BodyCoveringType skinType,
			Race race,
			List<String> names,
			List<String> namesPlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			List<OrificeModifier> defaultRacialOrificeModifiers) {
		
		this.skinType = skinType;
		this.race = race;
		
		this.names = names;
		this.namesPlural = namesPlural;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;

		if(defaultRacialOrificeModifiers==null) {
			this.defaultRacialOrificeModifiers = new ArrayList<>();
		} else {
			this.defaultRacialOrificeModifiers = defaultRacialOrificeModifiers;
		}
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
			return UtilText.returnStringAtRandom("asshole", "back door", "rear entrance");
		}
		return Util.randomItemFrom(names);
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(namesPlural==null) {
			return UtilText.returnStringAtRandom("assholes", "back doors", "rear entrances");
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
	public BodyCoveringType getBodyCoveringType(Body body) {
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}

	public List<OrificeModifier> getDefaultRacialOrificeModifiers() {
		return defaultRacialOrificeModifiers;
	}
}
