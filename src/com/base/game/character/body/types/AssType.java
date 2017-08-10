package com.base.game.character.body.types;

import com.base.game.character.GameCharacter;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public enum AssType implements BodyPartTypeInterface {
	HUMAN(BodyCoveringType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.ANGEL, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON),
	
	DOG_MORPH(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),
	
	WOLF_MORPH(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),
	
	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),

	HORSE_MORPH(BodyCoveringType.EQUINE_ASSHOLE, Race.HORSE_MORPH),
	
	HARPY(BodyCoveringType.FEATHERS, Race.HARPY),

	SLIME(BodyCoveringType.SLIME, Race.SLIME),
	
	FOX_MORPH(BodyCoveringType.VULPINE_FUR, Race.FOX_MORPH);

	private BodyCoveringType skinType;
	private Race race;

	private AssType(BodyCoveringType skinType, Race race) {
		this.skinType = skinType;
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
	public BodyCoveringType getSkinType() {
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}
	
	// Extra methods for asshole:
	
	public String getAssholeName() {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("asshole", "back door", "rear entrance");
		}
	}

	public String getAssholeDescriptor() {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("angelic", "perfect");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("demonic", "irresistible");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("puffy", "horse-like", "puffy-rimmed", "equine");
			case SLIME:
				return UtilText.returnStringAtRandom("gooey", "slimy");
			default:
				return UtilText.returnStringAtRandom("");
		}
	}
}
