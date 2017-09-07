package com.base.game.character.body.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.valueEnums.PenisModifier;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;
import com.base.main.Main;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum PenisType implements BodyPartTypeInterface {
	NONE(null, TesticleType.NONE, null),

	HUMAN(BodyCoveringType.PENIS, TesticleType.HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.PENIS, TesticleType.ANGEL, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.PENIS, TesticleType.DEMON_COMMON, Race.DEMON, PenisModifier.RIBBED, PenisModifier.TENTACLED, PenisModifier.PREHENSILE),

	CANINE(BodyCoveringType.PENIS, TesticleType.CANINE, Race.DOG_MORPH, PenisModifier.KNOTTED, PenisModifier.SHEATHED, PenisModifier.TAPERED),
	
	LUPINE(BodyCoveringType.PENIS, TesticleType.LUPINE, Race.WOLF_MORPH, PenisModifier.KNOTTED, PenisModifier.SHEATHED, PenisModifier.TAPERED),

	FELINE(BodyCoveringType.PENIS, TesticleType.FELINE, Race.CAT_MORPH, PenisModifier.BARBED, PenisModifier.SHEATHED),

	EQUINE(BodyCoveringType.PENIS, TesticleType.EQUINE, Race.HORSE_MORPH, PenisModifier.FLARED, PenisModifier.VEINY, PenisModifier.SHEATHED),

	SLIME(BodyCoveringType.PENIS, TesticleType.SLIME, Race.SLIME),

	AVIAN(BodyCoveringType.PENIS, TesticleType.AVIAN, Race.HARPY, PenisModifier.SHEATHED),
	
	SQUIRREL(BodyCoveringType.PENIS, TesticleType.SQUIRREL, Race.SQUIRREL_MORPH, PenisModifier.SHEATHED);

	
	private BodyCoveringType skinType;
	private TesticleType testicleType;
	private Race race;
	private List<PenisModifier> defaultPenisModifiers;

	private PenisType(BodyCoveringType skinType, TesticleType testicleType, Race race, PenisModifier... defaultPenisModifiers) {
		this.skinType = skinType;
		this.race = race;
		this.testicleType=testicleType;
		
		this.defaultPenisModifiers = new ArrayList<>();
		Collections.addAll(this.defaultPenisModifiers, defaultPenisModifiers);
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
						return UtilText.returnStringAtRandom("angelic");
					case AVIAN:
						return UtilText.returnStringAtRandom("avian");
					case CANINE:
						return UtilText.returnStringAtRandom("bestial");
					case DEMON_COMMON:
						return UtilText.returnStringAtRandom("demonic");
					case EQUINE:
						return UtilText.returnStringAtRandom("bestial", "thick");
					case FELINE:
						return UtilText.returnStringAtRandom("bestial");
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
	public BodyCoveringType getBodyCoveringType() {
		return skinType;
	}
	
	@Override
	public Race getRace() {
		return race;
	}

	public TesticleType getTesticleType() {
		return testicleType;
	}

	public List<PenisModifier> getDefaultPenisModifiers() {
		return defaultPenisModifiers;
	}

}
