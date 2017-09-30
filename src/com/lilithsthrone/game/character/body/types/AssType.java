package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public enum AssType implements BodyPartTypeInterface {
	HUMAN(BodyCoveringType.HUMAN, AnusType.HUMAN, Race.HUMAN),
	
	ANGEL(BodyCoveringType.ANGEL, AnusType.ANGEL, Race.ANGEL),
	
	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, AnusType.DEMON_COMMON, Race.DEMON),
	
	DOG_MORPH(BodyCoveringType.CANINE_FUR, AnusType.DOG_MORPH, Race.DOG_MORPH),
	
	COW_MORPH(BodyCoveringType.BOVINE_FUR, AnusType.COW_MORPH, Race.COW_MORPH),

	SQUIRREL_MORPH(BodyCoveringType.SQUIRREL_FUR, AnusType.SQUIRREL_MORPH, Race.SQUIRREL_MORPH),
	
	WOLF_MORPH(BodyCoveringType.LYCAN_FUR, AnusType.WOLF_MORPH, Race.WOLF_MORPH),
	
	CAT_MORPH(BodyCoveringType.FELINE_FUR, AnusType.CAT_MORPH, Race.CAT_MORPH),
	
	HORSE_MORPH(BodyCoveringType.HORSE_HAIR, AnusType.HORSE_MORPH, Race.HORSE_MORPH),
	
	HARPY(BodyCoveringType.FEATHERS, AnusType.HARPY, Race.HARPY),
	
	SLIME(BodyCoveringType.SLIME, AnusType.SLIME, Race.SLIME);

	private BodyCoveringType skinType;
	private AnusType anusType;
	private Race race;

	private AssType(BodyCoveringType skinType, AnusType anusType, Race race) {
		this.skinType = skinType;
		this.anusType = anusType;
		this.race = race;
	}
	
	@Override
	public boolean isDefaultPlural() {
		return false;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "";
	}
	
	@Override
	public String getName(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("ass", "rear end", "butt", "rump");
		}
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		return getName(gc);
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("asses", "rear ends", "butts", "rumps");
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		// Randomly give a size or type-specific descriptor:
		switch(Util.random.nextInt(2)){
			case 0:
				switch (this) {
					case ANGEL:
						return UtilText.returnStringAtRandom("angelic", "perfect");
					case DEMON_COMMON:
						return UtilText.returnStringAtRandom("demonic", "perfect");
					default:
						return UtilText.returnStringAtRandom("");
				}
			default:
				return gc.getAssSize().getDescriptor();
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
	public AnusType getAnusType() {
		return anusType;
	}
}
