package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.85
 * @author Innoxia
 */
public enum HornType implements BodyPartTypeInterface {
	NONE("", null, null),

	DEMON("short, curved", BodyCoveringType.HORN_DEMON, Race.DEMON),

	BOVINE("long, curved", BodyCoveringType.HORN_COW, Race.COW_MORPH);

	private BodyCoveringType skinType;
	private Race race;
	
	private HornType(String descriptor, BodyCoveringType skinType, Race race) {
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
				return UtilText.returnStringAtRandom("horn");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("horns");
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if(gc.isFeminine()) {
			switch(this){
				case DEMON:
					return UtilText.returnStringAtRandom("long", "swept-back", "sleek");
				case BOVINE:
					return UtilText.returnStringAtRandom("short", "sleek");
				default:
					return UtilText.returnStringAtRandom("");
			}
		} else {
			switch(this){
				case DEMON:
					return UtilText.returnStringAtRandom("short", "swept-back");
				case BOVINE:
					return UtilText.returnStringAtRandom("long");
				default:
					return UtilText.returnStringAtRandom("");
			}
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
}
