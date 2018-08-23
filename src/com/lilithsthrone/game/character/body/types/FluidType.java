package com.lilithsthrone.game.character.body.types;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidTypeBase;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.2.11
 * @author Innoxia
 */
public enum FluidType implements BodyPartTypeInterface {
	
	// Cum:
	
	CUM_HUMAN(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues("human"),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY),
			Race.HUMAN),

	CUM_ANGEL(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues("angelic"),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY),
			Race.ANGEL),

	CUM_DEMON(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY),
			Race.DEMON),

	CUM_DOG_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues("canine"),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY),
			Race.DOG_MORPH),
	
	CUM_WOLF_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues("lupine"),
			Util.newArrayListOfValues(
					FluidModifier.MUSKY,
					FluidModifier.STICKY,
					FluidModifier.SLIMY),
			Race.WOLF_MORPH),
	
	CUM_FOX_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues("vulpine"),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY),
			Race.FOX_MORPH),
	
	CUM_CAT_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues("feline"),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY),
			Race.CAT_MORPH),
	
	CUM_SQUIRREL_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues("squirrel"),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY),
			Race.SQUIRREL_MORPH),
	
	CUM_RAT_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues("rat"),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY),
			Race.RAT_MORPH),
	
	CUM_RABBIT_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues("rabbit"),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY),
			Race.RABBIT_MORPH),
	
	CUM_BAT_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues("bat"),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY),
			Race.BAT_MORPH),
	
	CUM_ALLIGATOR_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues("alligator"),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY),
			Race.ALLIGATOR_MORPH),
	
	CUM_HORSE_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues("equine"),
			Util.newArrayListOfValues(
					FluidModifier.MUSKY,
					FluidModifier.STICKY,
					FluidModifier.SLIMY),
			Race.HORSE_MORPH),
	
	CUM_REINDEER_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues("rangiferine"),
			Util.newArrayListOfValues(
					FluidModifier.MUSKY,
					FluidModifier.STICKY,
					FluidModifier.SLIMY),
			Race.REINDEER_MORPH),
	
	CUM_COW_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues("bovine"),
			Util.newArrayListOfValues(
					FluidModifier.MUSKY,
					FluidModifier.STICKY,
					FluidModifier.SLIMY),
			Race.COW_MORPH),
	
	CUM_HARPY(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues("avian"),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY),
			Race.HARPY),
	
	// Girl cum:
	
	GIRL_CUM_HUMAN(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues("human"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY),
			Race.HUMAN),

	GIRL_CUM_ANGEL(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues("angelic"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY),
			Race.ANGEL),

	GIRL_CUM_DEMON(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY),
			Race.DEMON),

	GIRL_CUM_DOG_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues("canine"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY),
			Race.DOG_MORPH),
	
	GIRL_CUM_WOLF_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues("lupine"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY),
			Race.WOLF_MORPH),
	
	GIRL_CUM_FOX_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues("vulpine"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY),
			Race.FOX_MORPH),
	
	GIRL_CUM_CAT_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues("feline"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY),
			Race.CAT_MORPH),
	
	GIRL_CUM_SQUIRREL_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues("squirrel"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY),
			Race.SQUIRREL_MORPH),
	
	GIRL_CUM_RAT_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues("rat"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY),
			Race.RAT_MORPH),

	GIRL_CUM_RABBIT_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues("rabbit"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY),
			Race.RABBIT_MORPH),
	
	GIRL_CUM_BAT_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues("bat"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY),
			Race.BAT_MORPH),
	
	GIRL_CUM_ALLIGATOR_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues("alligator"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY),
			Race.ALLIGATOR_MORPH),
	
	GIRL_CUM_HORSE_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues("equine"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY),
			Race.HORSE_MORPH),
	
	GIRL_CUM_REINDEER_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues("rangiferine"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY),
			Race.REINDEER_MORPH),
	
	GIRL_CUM_COW_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues("bovine"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY),
			Race.COW_MORPH),
	
	GIRL_CUM_HARPY(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues("avian"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY),
			Race.HARPY),
	
	// Milks:
	
	MILK_HUMAN(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues("human"),
			Util.newArrayListOfValues(),
			Race.HUMAN),

	MILK_ANGEL(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues("angelic"),
			Util.newArrayListOfValues(),
			Race.ANGEL),

	MILK_COW_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues("bovine"),
			Util.newArrayListOfValues(),
			Race.COW_MORPH),
	
	MILK_DEMON_COMMON(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues(),
			Race.DEMON),

	MILK_DOG_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues("canine"),
			Util.newArrayListOfValues(),
			Race.DOG_MORPH),
	
	MILK_WOLF_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues("lupine"),
			Util.newArrayListOfValues(),
			Race.WOLF_MORPH),
	
	MILK_FOX_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues("vulpine"),
			Util.newArrayListOfValues(),
			Race.FOX_MORPH),
	
	MILK_CAT_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues("feline"),
			Util.newArrayListOfValues(),
			Race.CAT_MORPH),
	
	MILK_SQUIRREL_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues("squirrel"),
			Util.newArrayListOfValues(),
			Race.SQUIRREL_MORPH),
	
	MILK_RAT_MORPH(FluidTypeBase.MILK, // I don't get it. Everyone loves rats, but they don't wanna drink the rats' milk?
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues("rat"),
			Util.newArrayListOfValues(),
			Race.RAT_MORPH),
	
	MILK_RABBIT_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues("rabbit"),
			Util.newArrayListOfValues(),
			Race.RABBIT_MORPH),
	
	MILK_BAT_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues("bat"),
			Util.newArrayListOfValues(),
			Race.BAT_MORPH),
	
	MILK_ALLIGATOR_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues("alligator"),
			Util.newArrayListOfValues(),
			Race.ALLIGATOR_MORPH),
	
	MILK_HORSE_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues("equine"),
			Util.newArrayListOfValues(),
			Race.HORSE_MORPH),
	
	MILK_REINDEER_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues("rangiferine"),
			Util.newArrayListOfValues(),
			Race.REINDEER_MORPH),
	
	MILK_HARPY(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues("avian"),
			Util.newArrayListOfValues(),
			Race.HARPY);

	
	private FluidTypeBase baseType;
	private BodyCoveringType bodyCoveringType;
	private FluidFlavour flavour;
	private List<String> descriptors;
	private List<FluidModifier> fluidModifiers;
	private Race race;

	private FluidType(FluidTypeBase baseType, BodyCoveringType bodyCoveringType, FluidFlavour flavour, List<String> descriptors, List<FluidModifier> fluidModifiers, Race race) {
		this.baseType = baseType;
		this.bodyCoveringType = bodyCoveringType;
		this.flavour = flavour;
		this.descriptors = descriptors;
		this.fluidModifiers = fluidModifiers;
		this.race = race;
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static FluidType getTypeFromString(String value) {
		if(value.equals("CUM_IMP")) {
			value = "CUM_DEMON";
			
		} else if(value.equals("GIRL_CUM_IMP")) {
			value = "GIRL_CUM_DEMON";
			
		} else if(value.equals("MILK_IMP")) {
			value = "MIKL_DEMON_COMMON";
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
		return bodyCoveringType.getName(gc);
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return bodyCoveringType.getNamePlural(gc);
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		return descriptors.get(Util.random.nextInt(descriptors.size()));
	}
	
	public FluidFlavour getFlavour() {
		return flavour;
	}

	@Override
	public BodyCoveringType getBodyCoveringType(Body body) {
		return bodyCoveringType;
	}

	public List<FluidModifier> getFluidModifiers() {
		return fluidModifiers;
	}

	@Override
	public Race getRace() {
		return race;
	}

	public FluidTypeBase getBaseType() {
		return baseType;
	}
}