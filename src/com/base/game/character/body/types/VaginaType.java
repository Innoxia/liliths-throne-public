package com.base.game.character.body.types;

import com.base.game.character.GameCharacter;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.OrificeType;
import com.base.game.sex.Sex;
import com.base.main.Main;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.78
 * @author Innoxia
 */
public enum VaginaType implements BodyPartTypeInterface {

	NONE(null, null),

	HUMAN(BodyCoveringType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.ANGEL, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON),

	DOG_MORPH(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),

	WOLF_MORPH(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),

	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),

	HORSE_MORPH(BodyCoveringType.EQUINE_VAGINA, Race.HORSE_MORPH),

	SLIME(BodyCoveringType.SLIME, Race.SLIME) {
		@Override
		public String getCumName() {
			return "slime";
		}
	},

	HARPY(BodyCoveringType.FEATHERS, Race.HARPY);

	private BodyCoveringType skinType;
	private Race race;

	private VaginaType(BodyCoveringType skinType, Race race) {
		this.skinType = skinType;
		this.race = race;
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
		if(gc.isVaginaVirgin()) {
			switch(this){
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("cherry", "cunt", "kitty", "pussy", "sex", "slit", "twat", "horse-pussy");
				case NONE:
					return "";
				default:
					return UtilText.returnStringAtRandom("cherry", "cunt", "kitty", "pussy", "sex", "slit", "twat");
			}
			
		} else {
			switch(this){
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("cunt", "kitty", "pussy", "sex", "slit", "twat", "horse-pussy");
				case NONE:
					return "";
				default:
					return UtilText.returnStringAtRandom("cunt", "kitty", "pussy", "sex", "slit", "twat");
			}
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(gc.isVaginaVirgin()) {
			switch(this){
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("cherries", "cunts", "kitties", "pussies", "sex", "slits", "twats", "horse-pussies");
				case NONE:
					return "";
				default:
					return UtilText.returnStringAtRandom("cherries", "cunts", "kitties", "pussies", "sex", "slits", "twats");
			}
			
		} else {
			switch(this){
				case HORSE_MORPH:
					return UtilText.returnStringAtRandom("cunts", "kitties", "pussies", "sex", "slits", "twats", "horse-pussies");
				case NONE:
					return "";
				default:
					return UtilText.returnStringAtRandom("cunts", "kitties", "pussies", "sex", "slits", "twats");
			}
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		// Randomly give a type-specific, wetness, or capcity descriptor:
		switch(Util.random.nextInt(3)){
			case 0:
				switch(this){
					case ANGEL:
						return UtilText.returnStringAtRandom("perfect");
					case DEMON_COMMON:
						return UtilText.returnStringAtRandom("tentacle-lined", "irresistible", "demonic");
					case DOG_MORPH:
						return UtilText.returnStringAtRandom("hot", "animalistic", "dog-like", "canine");
					case WOLF_MORPH:
						return UtilText.returnStringAtRandom("hot", "animalistic", "wolf-like", "lupine");
					case CAT_MORPH:
						return UtilText.returnStringAtRandom("hot", "animalistic", "cat-like", "feline");
					case HORSE_MORPH:
						return UtilText.returnStringAtRandom("puffy", "puffy-lipped", "black-lipped", "equine");
					case HUMAN:
						return UtilText.returnStringAtRandom("hot");
					case NONE:
						return UtilText.returnStringAtRandom("");
					case SLIME:
						return UtilText.returnStringAtRandom("gooey");
					case HARPY:
						return UtilText.returnStringAtRandom("hot", "bird-like", "avian");
					default:
						return UtilText.returnStringAtRandom("");
				}
			case 1:
				if(Main.game.isInSex()) {
					if(gc.isPlayer() && !Sex.getWetOrificeTypes().get(OrificeType.VAGINA_PLAYER).isEmpty()) {
						return "wet";
					} else if(!gc.isPlayer() && !Sex.getWetOrificeTypes().get(OrificeType.VAGINA_PARTNER).isEmpty()) {
						return "wet";
					} else {
						return gc.getVaginaWetness().getDescriptor();
					}
				} else {
					return gc.getVaginaWetness().getDescriptor();
				}
			default:
				return gc.getVaginaCapacity().getDescriptor();
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

	public String getCumDescriptor() {
		return UtilText.returnStringAtRandom("slick", "slippery");
	}
	
	public String getCumName() {
		return UtilText.returnStringAtRandom("girl-cum", "juices");
	}
	
}
