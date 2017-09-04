package com.base.game.character.body.types;

import java.util.ArrayList;
import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.valueEnums.OrificeModifier;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum MouthType implements BodyPartTypeInterface {
	
	HUMAN(BodyCoveringType.MOUTH, Race.HUMAN),

	ANGEL(BodyCoveringType.MOUTH, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.MOUTH, Race.DEMON),

	DOG_MORPH(BodyCoveringType.MOUTH, Race.DOG_MORPH),
	
	WOLF_MORPH(BodyCoveringType.MOUTH, Race.WOLF_MORPH),
	
	CAT_MORPH(BodyCoveringType.MOUTH, Race.CAT_MORPH),
	
	SQUIRREL_MORPH(BodyCoveringType.MOUTH, Race.SQUIRREL_MORPH),
	
	HORSE_MORPH(BodyCoveringType.MOUTH, Race.HORSE_MORPH),
	
	HARPY(BodyCoveringType.MOUTH, Race.HARPY),

	SLIME(BodyCoveringType.MOUTH_SLIME, Race.SLIME);

	
	private BodyCoveringType skinType;
	private Race race;
	private List<OrificeModifier> defaultRacialOrificeModifiers;

	private MouthType(BodyCoveringType skinType, Race race, OrificeModifier... defaultRacialOrificeModifiers) {
		this.skinType = skinType;
		this.race = race;
		this.defaultRacialOrificeModifiers = new ArrayList<>();
		for(OrificeModifier om : defaultRacialOrificeModifiers) {
			this.defaultRacialOrificeModifiers.add(om);
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
		return UtilText.returnStringAtRandom(
				"mouth",
				"throat");
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return UtilText.returnStringAtRandom(
				"mouthes",
				"throats");
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		return "";
	}
	
	@Override
	public BodyCoveringType getBodyCoveringType() {
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
