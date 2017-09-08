package com.base.game.character.body.types;

import java.util.ArrayList;
import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.valueEnums.OrificeModifier;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.OrificeType;
import com.base.game.sex.Sex;
import com.base.main.Main;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum VaginaType implements BodyPartTypeInterface {

	NONE(null, FluidType.GIRL_CUM_HUMAN, null),

	HUMAN(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_ANGEL, Race.ANGEL, OrificeModifier.MUSCLE_CONTROL),

	DEMON_COMMON(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_DEMON, Race.DEMON, OrificeModifier.MUSCLE_CONTROL, OrificeModifier.TENTACLED),

	DOG_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_DOG_MORPH, Race.DOG_MORPH),

	WOLF_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_WOLF_MORPH, Race.WOLF_MORPH),

	SQUIRREL_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_SQUIRREL_MORPH, Race.SQUIRREL_MORPH),
	
	CAT_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_CAT_MORPH, Race.CAT_MORPH),

	HORSE_MORPH(BodyCoveringType.VAGINA, FluidType.GIRL_CUM_HORSE_MORPH, Race.HORSE_MORPH, OrificeModifier.PUFFY),

	SLIME(BodyCoveringType.VAGINA_SLIME, FluidType.GIRL_CUM_SLIME, Race.SLIME),

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
					case CAT_MORPH:
						return UtilText.returnStringAtRandom("hot", "animalistic", "cat-like", "feline");
					case HORSE_MORPH:
						return UtilText.returnStringAtRandom("hot", "animalistic", "equine");
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
	public BodyCoveringType getBodyCoveringType() {
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
