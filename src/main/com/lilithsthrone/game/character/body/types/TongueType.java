package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum TongueType implements BodyPartTypeInterface {

	HUMAN(BodyCoveringType.TONGUE, Race.HUMAN, 1),
	
	ANGEL(BodyCoveringType.TONGUE, Race.ANGEL, 1),

	DEMON_COMMON(BodyCoveringType.TONGUE, Race.DEMON, 1, TongueModifier.BIFURCATED),

	DOG_MORPH(BodyCoveringType.TONGUE, Race.DOG_MORPH, 1),

	WOLF_MORPH(BodyCoveringType.TONGUE, Race.WOLF_MORPH, 1),
	
	FOX_MORPH(BodyCoveringType.TONGUE, Race.FOX_MORPH, 1),

	CAT_MORPH(BodyCoveringType.TONGUE, Race.CAT_MORPH, 1),

	COW_MORPH(BodyCoveringType.TONGUE, Race.COW_MORPH, 1),

	ALLIGATOR_MORPH(BodyCoveringType.TONGUE, Race.ALLIGATOR_MORPH, 1),

	HORSE_MORPH(BodyCoveringType.TONGUE, Race.HORSE_MORPH, 1),

	REINDEER_MORPH(BodyCoveringType.TONGUE, Race.REINDEER_MORPH, 1),

	TENGU(BodyCoveringType.TONGUE, Race.HARPY, 1),

	SQUIRREL_MORPH(BodyCoveringType.TONGUE, Race.SQUIRREL_MORPH, 1),

	RAT_MORPH(BodyCoveringType.TONGUE, Race.RAT_MORPH, 1),
	
	RABBIT_MORPH(BodyCoveringType.TONGUE, Race.RABBIT_MORPH, 1),

	BAT_MORPH(BodyCoveringType.TONGUE, Race.BAT_MORPH, 1);
	
	private BodyCoveringType skinType;
	private Race race;
	private int defaultTongueLength;
	private List<TongueModifier> defaultRacialTongueModifiers;

	private TongueType(BodyCoveringType skinType, Race race, int defaultTongueLength, TongueModifier... defaultRacialTongueModifiers) {
		this.skinType = skinType;
		this.race = race;
		this.defaultTongueLength = defaultTongueLength;
		
		this.defaultRacialTongueModifiers = new ArrayList<>();
		Collections.addAll(this.defaultRacialTongueModifiers, defaultRacialTongueModifiers);
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static TongueType getTypeFromString(String value) {
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
		return "tongue";
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return "tongues";
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("thin", "cat-like");
			case COW_MORPH:
				return UtilText.returnStringAtRandom("wide", "strong", "cow-like");
			case DEMON_COMMON:
				break;
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("wide", "dog-like");
			case ALLIGATOR_MORPH:
				break;
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("strong", "horse-like");
			case REINDEER_MORPH:
				return UtilText.returnStringAtRandom("strong", "reindeer-like");
			case HUMAN:
				break;
			case ANGEL:
				break;
			case SQUIRREL_MORPH:
				break;
			case TENGU:
				return UtilText.returnStringAtRandom("bird-like");
			case BAT_MORPH:
				break;
			case RAT_MORPH:
				break;
			case RABBIT_MORPH:
				break;
			case WOLF_MORPH:
				return UtilText.returnStringAtRandom("wide", "wolf-like");
			case FOX_MORPH:
				return UtilText.returnStringAtRandom("thin", "fox-like");
		}
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

	public int getDefaultTongueLength() {
		return defaultTongueLength;
	}

	public List<TongueModifier> getDefaultRacialTongueModifiers() {
		return defaultRacialTongueModifiers;
	}

}