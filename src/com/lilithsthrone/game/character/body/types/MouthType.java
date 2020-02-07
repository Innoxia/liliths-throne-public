package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.race.Race;

/**
 * @since 0.1.83
 * @version 0.3.2
 * @author Innoxia
 */
public enum MouthType implements BodyPartTypeInterface {
	
	HUMAN("mouth", "mouths", BodyCoveringType.MOUTH, Race.HUMAN),

	ANGEL("mouth", "mouths", BodyCoveringType.MOUTH, Race.ANGEL),

	DEMON_COMMON("mouth", "mouths", BodyCoveringType.MOUTH, Race.DEMON, OrificeModifier.EXTRA_DEEP),

	DOG_MORPH("mouth", "mouths", BodyCoveringType.MOUTH, Race.DOG_MORPH),
	
	WOLF_MORPH("mouth", "mouths", BodyCoveringType.MOUTH, Race.WOLF_MORPH),
	
	FOX_MORPH("mouth", "mouths", BodyCoveringType.MOUTH, Race.FOX_MORPH),
	
	CAT_MORPH("mouth", "mouths", BodyCoveringType.MOUTH, Race.CAT_MORPH),
	
	COW_MORPH("mouth", "mouths", BodyCoveringType.MOUTH, Race.COW_MORPH),
	
	SQUIRREL_MORPH("mouth", "mouths", BodyCoveringType.MOUTH, Race.SQUIRREL_MORPH),
	
	RAT_MORPH("mouth", "mouths", BodyCoveringType.MOUTH, Race.RAT_MORPH),
	
	RABBIT_MORPH("mouth", "mouths", BodyCoveringType.MOUTH, Race.RABBIT_MORPH),
	
	BAT_MORPH("mouth", "mouths", BodyCoveringType.MOUTH, Race.BAT_MORPH),
	
	ALLIGATOR_MORPH("mouth", "mouths", BodyCoveringType.MOUTH, Race.ALLIGATOR_MORPH),
	
	HORSE_MORPH("mouth", "mouths", BodyCoveringType.MOUTH, Race.HORSE_MORPH),
	
	REINDEER_MORPH("mouth", "mouths", BodyCoveringType.MOUTH, Race.REINDEER_MORPH),
	
	HARPY("beak", "beaks", BodyCoveringType.MOUTH, Race.HARPY);

	private String name;
	private String namePlural;
	private BodyCoveringType skinType;
	private Race race;
	private List<OrificeModifier> defaultRacialOrificeModifiers;

	private MouthType(String name, String namePlural, BodyCoveringType skinType, Race race, OrificeModifier... defaultRacialOrificeModifiers) {
		this.name = name;
		this.namePlural = namePlural;
		this.skinType = skinType;
		this.race = race;
		this.defaultRacialOrificeModifiers = new ArrayList<>();
		Collections.addAll(this.defaultRacialOrificeModifiers, defaultRacialOrificeModifiers);
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static MouthType getTypeFromString(String value) {
		if(value.equals("IMP")) {
			value = "DEMON_COMMON";
		}
		return valueOf(value);
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
		return name;
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return namePlural;
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		return "";
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