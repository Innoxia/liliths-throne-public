package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.List;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.83
 * @version 0.1.99
 * @author Innoxia
 */
public enum AntennaType implements BodyPartTypeInterface {
	NONE("", null, null);
	
	
	
	private BodyCoveringType skinType;
	private Race race;
	
	private AntennaType(String descriptor, BodyCoveringType skinType, Race race) {
		this.skinType = skinType;
		this.race = race;
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
	public String getNameSingular(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("antenna");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("antennae");
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case NONE:
				return UtilText.returnStringAtRandom("");
			default:
				return UtilText.returnStringAtRandom("");
		}
	}

	@Override
	public BodyCoveringType getBodyCoveringType() {
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}
	
	public List<AntennaType> getAntennaTypes(Race r) {
		List<AntennaType> types = new ArrayList<>();
		switch(r) {
			case ALLIGATOR_MORPH:
			case ANGEL:
			case CAT_MORPH:
			case COW_MORPH:
			case DEMON:
			case DOG_MORPH:
			case HARPY:
			case HORSE_MORPH:
			case HUMAN:
			case REINDEER_MORPH:
			case SLIME:
			case SQUIRREL_MORPH:
			case WOLF_MORPH:
				types.add(NONE);
				break;
		}
		return types;
	}
}
