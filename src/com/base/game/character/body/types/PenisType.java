package com.base.game.character.body.types;

import com.base.game.character.GameCharacter;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;
import com.base.main.Main;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.78
 * @author Innoxia
 */
public enum PenisType implements BodyPartTypeInterface {
	NONE(null, null),

	HUMAN(BodyCoveringType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.ANGEL, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON) {
		@Override
		public boolean isRibbedShaft() {
			return true;
		}
		@Override
		public boolean isTentacledShaft() {
			return true;
		}
	},

	CANINE(BodyCoveringType.CANINE_PENIS, Race.DOG_MORPH) {
		@Override
		public boolean isKnotted() {
			return true;
		}
	},
	
	LUPINE(BodyCoveringType.CANINE_PENIS, Race.WOLF_MORPH) {
		@Override
		public boolean isKnotted() {
			return true;
		}
	},

	FELINE(BodyCoveringType.HUMAN, Race.CAT_MORPH){
		@Override
		public boolean isBarbedShaft() {
			return true;
		}
	},

	EQUINE(BodyCoveringType.EQUINE_PENIS, Race.HORSE_MORPH) {
		@Override
		public boolean isFlaredHead() {
			return true;
		}
	},

	SLIME(BodyCoveringType.SLIME, Race.SLIME) {
		@Override
		public String getCumName(GameCharacter gc) {
			return "slime";
		}
	},

	AVIAN(BodyCoveringType.HUMAN, Race.HARPY),
	
	SQUIRREL(BodyCoveringType.HUMAN, Race.SQUIRREL_MORPH);

	
	private BodyCoveringType skinType;
	private Race race;

	private PenisType(BodyCoveringType skinType, Race race) {
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
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("cock", "shaft");
			case AVIAN:
				return UtilText.returnStringAtRandom("cock", "shaft");
			case CANINE:
				return UtilText.returnStringAtRandom("dog-cock", "canine cock", "dog-like cock", "dog-dick");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("cock", "demon-cock", "demon-dick");
			case EQUINE:
				return UtilText.returnStringAtRandom("horse-cock", "equine cock", "horse-like cock", "horse-dick");
			case FELINE:
				return UtilText.returnStringAtRandom("cat-cock", "feline cock", "cat-like cock", "cat-dick");
			case HUMAN:
				return UtilText.returnStringAtRandom("cock", "shaft");
			case NONE:
				return UtilText.returnStringAtRandom("");
			case SLIME:
				return UtilText.returnStringAtRandom("slime-cock", "slimy cock", "slime-dick");
			case SQUIRREL:
				return UtilText.returnStringAtRandom("squirrel-cock", "rodent cock", "squirrel-like cock", "squirrel-dick");
			default:
				return UtilText.returnStringAtRandom("cock", "shaft");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("cocks", "shafts");
			case AVIAN:
				return UtilText.returnStringAtRandom("cocks", "shafts");
			case CANINE:
				return UtilText.returnStringAtRandom("dog-cocks", "canine cocks", "dog-like cocks", "dog-dicks");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("cocks", "demon-cocks", "demon-dicks");
			case EQUINE:
				return UtilText.returnStringAtRandom("horse-cocks", "equine cocks", "horse-like cocks", "horse-dicks");
			case FELINE:
				return UtilText.returnStringAtRandom("cat-cocks", "feline cocks", "cat-like cocks", "cat-dicks");
			case HUMAN:
				return UtilText.returnStringAtRandom("cocks", "shafts");
			case NONE:
				return UtilText.returnStringAtRandom("");
			case SLIME:
				return UtilText.returnStringAtRandom("slime-cocks", "slimy cocks", "slime-dicks");
			case SQUIRREL:
				return UtilText.returnStringAtRandom("squirrel-cocks", "rodent cocks", "squirrel-like cocks", "squirrel-dicks");
			default:
				return UtilText.returnStringAtRandom("cocks", "shafts");
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		// Randomly give a size/aroused or type-specific descriptor:
		switch(Util.random.nextInt(3)){
			case 0:
				switch(this){
					case ANGEL:
						return UtilText.returnStringAtRandom("perfect");
					case AVIAN:
						return UtilText.returnStringAtRandom("retractable");
					case CANINE:
						return UtilText.returnStringAtRandom("knotted", "red", "tapered", "bestial");
					case DEMON_COMMON:
						return UtilText.returnStringAtRandom("ribbed", "bumpy");
					case EQUINE:
						return UtilText.returnStringAtRandom("flared", "bestial", "thick");
					case FELINE:
						return UtilText.returnStringAtRandom("barbed");
					case HUMAN:
						return UtilText.returnStringAtRandom("");
					case NONE:
						return UtilText.returnStringAtRandom("");
					case SLIME:
						return UtilText.returnStringAtRandom("gooey");
					case SQUIRREL:
						return UtilText.returnStringAtRandom("");
					default:
						return UtilText.returnStringAtRandom("");
				}
			case 1:
				return gc.getPenisSize().getDescriptor();
			default:
				if(Main.game.isInSex()) {
					return UtilText.returnStringAtRandom("hard", "throbbing");
				} else {
					return gc.getPenisSize().getDescriptor();
				}
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
	
	public boolean isKnotted() {
		return false;
	}
	public boolean isFlaredHead() {
		return false;
	}
	public boolean isBarbedShaft() {
		return false;
	}
	public boolean isRibbedShaft() {
		return false;
	}
	public boolean isTentacledShaft() {
		return false;
	}
	
	public String getPenisHeadName(GameCharacter gc) {
		switch(this){
			case EQUINE:
				return UtilText.returnStringAtRandom("head");
			case NONE:
				return UtilText.returnStringAtRandom("");
			default:
				return UtilText.returnStringAtRandom("head", "tip");
		}
	}
	
	public String getPenisHeadDescriptor(GameCharacter gc) {
		switch(this){
			case CANINE:
				return UtilText.returnStringAtRandom("tapered", "pointed");
			case EQUINE:
				return UtilText.returnStringAtRandom("wide", "flared", "flat");
			case FELINE:
				return UtilText.returnStringAtRandom("");
			case NONE:
				return UtilText.returnStringAtRandom("");
			default:
				return UtilText.returnStringAtRandom("");
		}
	}
	
	public String getCumName(GameCharacter gc) {
		switch(this){
			case CANINE:
				if(gc.getRace()==Race.WOLF_MORPH) {
					return UtilText.returnStringAtRandom("wolf-cum", "wolf-cream", "wolf-jism", "wolf-jizz", "wolf-seed");
				} else {
					return UtilText.returnStringAtRandom("dog-cum", "dog-cream", "dog-jism", "dog-jizz", "dog-seed");
				}
			case EQUINE:
				return UtilText.returnStringAtRandom("horse-cum", "horse-cream", "horse-jism", "horse-jizz", "horse-seed");
			case FELINE:
				return UtilText.returnStringAtRandom("cat-cum", "cat-cream", "cat-jism", "cat-jizz", "cat-seed");
			case NONE:
				return UtilText.returnStringAtRandom("");
			default:
				return UtilText.returnStringAtRandom("cum", "cream", "jism", "jizz", "load", "seed", "spooge");
		}
	}
	
	public String getCumDescriptor() {
		switch(this){
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("corruptive", "corrupted", "potent", "sticky", "hot", "salty");
			case NONE:
				return UtilText.returnStringAtRandom("");
			default:
				return UtilText.returnStringAtRandom("potent", "sticky", "hot", "salty");
		}
	}

}
