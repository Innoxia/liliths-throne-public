package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public enum VaginaType implements BodyPartTypeInterface {

	NONE(null, FluidType.GIRL_CUM_HUMAN, null),

	HUMAN(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_ANGEL, Race.ANGEL, OrificeModifier.MUSCLE_CONTROL),

	DEMON_COMMON(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_DEMON, Race.DEMON, OrificeModifier.MUSCLE_CONTROL, OrificeModifier.TENTACLED),

	DOG_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_DOG_MORPH, Race.DOG_MORPH),

	WOLF_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_WOLF_MORPH, Race.WOLF_MORPH),
	
	FOX_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_FOX_MORPH, Race.FOX_MORPH),

	SQUIRREL_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_SQUIRREL_MORPH, Race.SQUIRREL_MORPH),

	RAT_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_RAT_MORPH, Race.RAT_MORPH),

	RABBIT_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_RABBIT_MORPH, Race.RABBIT_MORPH),

	BAT_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_BAT_MORPH, Race.BAT_MORPH),
	
	ALLIGATOR_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_ALLIGATOR_MORPH, Race.ALLIGATOR_MORPH),
	
	CAT_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_CAT_MORPH, Race.CAT_MORPH),

	COW_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_COW_MORPH, Race.COW_MORPH),

	HORSE_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_HORSE_MORPH, Race.HORSE_MORPH, OrificeModifier.PUFFY),

	REINDEER_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_REINDEER_MORPH, Race.REINDEER_MORPH, OrificeModifier.PUFFY),

	HARPY(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_HARPY, Race.HARPY);

	private BodyCoveringType skinType;
	private FluidType fluidType;
	private Race race;
	private List<OrificeModifier> defaultRacialOrificeModifiers;

	private VaginaType(BodyCoveringType skinType, FluidType fluidType, Race race, OrificeModifier... defaultRacialOrificeModifiers) {
		this.skinType = skinType;
		this.fluidType = fluidType;
		this.race = race;
		
		this.defaultRacialOrificeModifiers = new ArrayList<>();
		Collections.addAll(this.defaultRacialOrificeModifiers, defaultRacialOrificeModifiers);
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static VaginaType getTypeFromString(String value) {
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
		if(this==NONE) {
			return "";
		}
		if(gc.isVaginaVirgin()) {
			return UtilText.returnStringAtRandom(
					"cherry",
					"cherry",
					"cunt",
					"cunt",
					"kitty",
					"pussy",
					"pussy",
					"pussy",
					"pussy",
					"sex",
					"slit",
					"twat",
					"twat",
					(this==HORSE_MORPH?"horse-pussy":""));
			
		} else {
			return UtilText.returnStringAtRandom(
					"cunt",
					"cunt",
					"kitty",
					"pussy",
					"pussy",
					"pussy",
					"pussy",
					"sex",
					"slit",
					"twat",
					"twat",
					(this==HORSE_MORPH?"horse-pussy":""));
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(this==NONE) {
			return "";
		}
		if(gc.isVaginaVirgin()) {
			return UtilText.returnStringAtRandom(
					"cherries",
					"cherries",
					"cunts",
					"cunts",
					"kitties",
					"pussies",
					"pussies",
					"pussies",
					"pussies",
					"sexes",
					"slits",
					"twats",
					"twats",
					(this==HORSE_MORPH?"horse-pussies":""));
			
		} else {
			return UtilText.returnStringAtRandom(
					"cunts",
					"cunts",
					"kitties",
					"pussies",
					"pussies",
					"pussies",
					"pussies",
					"sexes",
					"slits",
					"twats",
					"twats",
					(this==HORSE_MORPH?"horse-pussies":""));
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		// Randomly give a type-specific, wetness, or capacity descriptor:
		switch(Util.random.nextInt(3)){
			case 0:
				switch(this){
					case ANGEL:
						return UtilText.returnStringAtRandom("perfect");
					case DEMON_COMMON:
						return UtilText.returnStringAtRandom("irresistible", "demonic");
					case DOG_MORPH:
						return UtilText.returnStringAtRandom("hot", "animalistic", "dog-like", "canine");
					case WOLF_MORPH:
						return UtilText.returnStringAtRandom("hot", "animalistic", "wolf-like", "lupine");
					case FOX_MORPH:
						return UtilText.returnStringAtRandom("hot", "animalistic", "fox-like", "vulpine");
					case CAT_MORPH:
						return UtilText.returnStringAtRandom("hot", "animalistic", "cat-like", "feline");
					case COW_MORPH:
						return UtilText.returnStringAtRandom("hot", "animalistic", "cow-like", "bovine");
					case ALLIGATOR_MORPH:
						return UtilText.returnStringAtRandom("hot", "alligator-like", "reptilian");
					case HORSE_MORPH:
						return UtilText.returnStringAtRandom("hot", "animalistic", "equine");
					case REINDEER_MORPH:
						return UtilText.returnStringAtRandom("hot", "animalistic", "reindeer-like", "rangiferine");
					case HUMAN:
						return UtilText.returnStringAtRandom("hot");
					case NONE:
						return UtilText.returnStringAtRandom("");
					case HARPY:
						return UtilText.returnStringAtRandom("hot", "bird-like", "avian");
					case SQUIRREL_MORPH:
						return UtilText.returnStringAtRandom("hot", "squirrel-like", "rodent");
					case BAT_MORPH:
						return UtilText.returnStringAtRandom("hot", "bat-like");
					case RAT_MORPH:
						return UtilText.returnStringAtRandom("hot", "rat-like", "rodent");
					case RABBIT_MORPH:
						return UtilText.returnStringAtRandom("hot", "rabbit-like", "bunny");
				}
				return "";
			case 1:
				if(Main.game.isInSex()) {
					if(Sex.hasLubricationTypeFromAnyone(gc, SexAreaOrifice.VAGINA)) {
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
	public String getTransformName() {
		switch(this){
			case ANGEL:
				return "angelic";
			case HARPY:
				return "harpy";
			case COW_MORPH:
				return "bovine";
			case DOG_MORPH:
				return "canine";
			case DEMON_COMMON:
				return "demonic";
			case HORSE_MORPH:
				return "equine";
			case REINDEER_MORPH:
				return "reindeer";
			case CAT_MORPH:
				return "feline";
			case HUMAN:
				return "human";
			case NONE:
				return "none";
			case SQUIRREL_MORPH:
				return "squirrel";
			case ALLIGATOR_MORPH:
				return "alligator";
			case WOLF_MORPH:
				return "wolf";
			case FOX_MORPH:
				return "fox";
			case BAT_MORPH:
				return "bat";
			case RAT_MORPH:
				return "rat";
			case RABBIT_MORPH:
				return "rabbit";
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

	public FluidType getFluidType() {
		return fluidType;
	}

	public List<OrificeModifier> getDefaultRacialOrificeModifiers() {
		return defaultRacialOrificeModifiers;
	}
	
}