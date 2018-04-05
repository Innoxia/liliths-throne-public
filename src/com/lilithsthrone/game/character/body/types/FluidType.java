package com.lilithsthrone.game.character.body.types;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidTypeBase;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.83
 * @version 0.2.2
 * @author Innoxia
 */
public enum FluidType implements BodyPartTypeInterface {
	
	// Cum:
	
	CUM_HUMAN(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("human")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.HUMAN),

	CUM_ANGEL(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("angelic")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.ANGEL),

	CUM_DEMON(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("demonic")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.DEMON),

	CUM_IMP(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("impish")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.IMP),

	CUM_DOG_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("canine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.DOG_MORPH),
	
	CUM_WOLF_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("lupine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.MUSKY),
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.WOLF_MORPH),
	
	CUM_CAT_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("feline")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.CAT_MORPH),
	
	CUM_SQUIRREL_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("squirrel")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.SQUIRREL_MORPH),
	
	CUM_RAT_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("rat")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.RAT_MORPH),
	
	CUM_RABBIT_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("rabbit")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.RABBIT_MORPH),
	
	CUM_BAT_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("bat")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.BAT_MORPH),
	
	CUM_ALLIGATOR_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("alligator")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.ALLIGATOR_MORPH),
	
	CUM_HORSE_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("equine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.MUSKY),
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.HORSE_MORPH),
	
	CUM_REINDEER_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("rangiferine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.MUSKY),
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.REINDEER_MORPH),
	
	CUM_COW_MORPH(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("bovine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.MUSKY),
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.COW_MORPH),
	
	CUM_HARPY(FluidTypeBase.CUM,
			BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("avian")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.HARPY),
	
	// Girl cum:
	
	GIRL_CUM_HUMAN(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("human")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.HUMAN),

	GIRL_CUM_ANGEL(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("angelic")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.ANGEL),

	GIRL_CUM_DEMON(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("demonic")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.DEMON),

	GIRL_CUM_IMP(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("impish")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.IMP),

	GIRL_CUM_DOG_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("canine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.DOG_MORPH),
	
	GIRL_CUM_WOLF_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("lupine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.WOLF_MORPH),
	
	GIRL_CUM_CAT_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("feline")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.CAT_MORPH),
	
	GIRL_CUM_SQUIRREL_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("squirrel")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.SQUIRREL_MORPH),
	
	GIRL_CUM_RAT_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("rat")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.RAT_MORPH),

	GIRL_CUM_RABBIT_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("rabbit")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.RABBIT_MORPH),
	
	GIRL_CUM_BAT_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("bat")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.BAT_MORPH),
	
	GIRL_CUM_ALLIGATOR_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("alligator")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.ALLIGATOR_MORPH),
	
	GIRL_CUM_HORSE_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("equine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.HORSE_MORPH),
	
	GIRL_CUM_REINDEER_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("rangiferine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.REINDEER_MORPH),
	
	GIRL_CUM_COW_MORPH(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("bovine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.COW_MORPH),
	
	GIRL_CUM_HARPY(FluidTypeBase.GIRLCUM,
			BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("avian")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.HARPY),
	
	// Milks:
	
	MILK_HUMAN(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("human")),
			Util.newArrayListOfValues(),
			Race.HUMAN),

	MILK_ANGEL(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("angelic")),
			Util.newArrayListOfValues(),
			Race.ANGEL),

	MILK_COW_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("bovine")),
			Util.newArrayListOfValues(),
			Race.COW_MORPH),
	
	MILK_DEMON_COMMON(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("demonic")),
			Util.newArrayListOfValues(),
			Race.DEMON),

	MILK_IMP(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("impish")),
			Util.newArrayListOfValues(),
			Race.IMP),

	MILK_DOG_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("canine")),
			Util.newArrayListOfValues(),
			Race.DOG_MORPH),
	
	MILK_WOLF_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("lupine")),
			Util.newArrayListOfValues(),
			Race.WOLF_MORPH),
	
	MILK_CAT_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("feline")),
			Util.newArrayListOfValues(),
			Race.CAT_MORPH),
	
	MILK_SQUIRREL_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("squirrel")),
			Util.newArrayListOfValues(),
			Race.SQUIRREL_MORPH),
	
	MILK_RAT_MORPH(FluidTypeBase.MILK, // I don't get it. Everyone loves rats, but they don't wanna drink the rats' milk?
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("rat")),
			Util.newArrayListOfValues(),
			Race.RAT_MORPH),
	
	MILK_RABBIT_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("rabbit")),
			Util.newArrayListOfValues(),
			Race.RABBIT_MORPH),
	
	MILK_BAT_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("bat")),
			Util.newArrayListOfValues(),
			Race.BAT_MORPH),
	
	MILK_ALLIGATOR_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("alligator")),
			Util.newArrayListOfValues(),
			Race.ALLIGATOR_MORPH),
	
	MILK_HORSE_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("equine")),
			Util.newArrayListOfValues(),
			Race.HORSE_MORPH),
	
	MILK_REINDEER_MORPH(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("rangiferine")),
			Util.newArrayListOfValues(),
			Race.REINDEER_MORPH),
	
	MILK_HARPY(FluidTypeBase.MILK,
			BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("avian")),
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
