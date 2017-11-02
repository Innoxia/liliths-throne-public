package com.lilithsthrone.game.character.body.types;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum FluidType implements BodyPartTypeInterface {
	
	// Cum:
	
	CUM_HUMAN(BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("human")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.HUMAN),

	CUM_ANGEL(BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("angelic")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.ANGEL),

	CUM_DEMON(BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("demonic")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.DEMON),

	CUM_DOG_MORPH(BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("canine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.DOG_MORPH),
	
	CUM_WOLF_MORPH(BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("lupine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.MUSKY),
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.WOLF_MORPH),
	
	CUM_CAT_MORPH(BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("feline")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.CAT_MORPH),
	
	CUM_SQUIRREL_MORPH(BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("squirrel")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.SQUIRREL_MORPH),
	
	CUM_GATOR_MORPH(BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("gator")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.GATOR_MORPH),
	
	CUM_HORSE_MORPH(BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("equine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.MUSKY),
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.HORSE_MORPH),
	
	CUM_COW_MORPH(BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("bovine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.MUSKY),
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.COW_MORPH),
	
	CUM_HARPY(BodyCoveringType.CUM,
			FluidFlavour.CUM,
			Util.newArrayListOfValues(new ListValue<>("avian")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.STICKY),
					new ListValue<>(FluidModifier.SLIMY)),
			Race.HARPY),

	CUM_SLIME(BodyCoveringType.CUM,
			FluidFlavour.SLIME,
			Util.newArrayListOfValues(new ListValue<>("slime")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.SLIME),
	
	// Girl cum:
	
	GIRL_CUM_HUMAN(BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("human")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.HUMAN),

	GIRL_CUM_ANGEL(BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("angelic")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.ANGEL),

	GIRL_CUM_DEMON(BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("demonic")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.DEMON),

	GIRL_CUM_DOG_MORPH(BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("canine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.DOG_MORPH),
	
	GIRL_CUM_WOLF_MORPH(BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("lupine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.WOLF_MORPH),
	
	GIRL_CUM_CAT_MORPH(BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("feline")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.CAT_MORPH),
	
	GIRL_CUM_SQUIRREL_MORPH(BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("squirrel")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.SQUIRREL_MORPH),
	
	GIRL_CUM_GATOR_MORPH(BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("gator")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.GATOR_MORPH),
	
	GIRL_CUM_HORSE_MORPH(BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("equine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.HORSE_MORPH),
	
	GIRL_CUM_COW_MORPH(BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("bovine")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.COW_MORPH),
	
	GIRL_CUM_HARPY(BodyCoveringType.GIRL_CUM,
			FluidFlavour.GIRL_CUM,
			Util.newArrayListOfValues(new ListValue<>("avian")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.HARPY),

	GIRL_CUM_SLIME(BodyCoveringType.GIRL_CUM,
			FluidFlavour.SLIME,
			Util.newArrayListOfValues(new ListValue<>("slime")),
			Util.newArrayListOfValues(
					new ListValue<>(FluidModifier.SLIMY)),
			Race.SLIME),
	
	// Milks:
	
	MILK_HUMAN(BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("human")),
			Util.newArrayListOfValues(),
			Race.HUMAN),

	MILK_ANGEL(BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("angelic")),
			Util.newArrayListOfValues(),
			Race.ANGEL),

	MILK_COW_MORPH(BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("Bovine")),
			Util.newArrayListOfValues(),
			Race.COW_MORPH),
	
	MILK_DEMON_COMMON(BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("demonic")),
			Util.newArrayListOfValues(),
			Race.DEMON),

	MILK_DOG_MORPH(BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("canine")),
			Util.newArrayListOfValues(),
			Race.DOG_MORPH),
	
	MILK_WOLF_MORPH(BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("lupine")),
			Util.newArrayListOfValues(),
			Race.WOLF_MORPH),
	
	MILK_CAT_MORPH(BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("feline")),
			Util.newArrayListOfValues(),
			Race.CAT_MORPH),
	
	MILK_SQUIRREL_MORPH(BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("squirrel")),
			Util.newArrayListOfValues(),
			Race.SQUIRREL_MORPH),
	
	MILK_GATOR_MORPH(BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("gator")),
			Util.newArrayListOfValues(),
			Race.GATOR_MORPH),
	
	MILK_HORSE_MORPH(BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("equine")),
			Util.newArrayListOfValues(),
			Race.HORSE_MORPH),
	
	MILK_HARPY(BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("avian")),
			Util.newArrayListOfValues(),
			Race.HARPY),

	MILK_SLIME(BodyCoveringType.MILK,
			FluidFlavour.MILK,
			Util.newArrayListOfValues(new ListValue<>("slime")),
			Util.newArrayListOfValues(),
			Race.SLIME);

	
	private BodyCoveringType bodyCoveringType;
	private FluidFlavour flavour;
	private List<String> descriptors;
	private List<FluidModifier> fluidModifiers;
	private Race race;

	private FluidType(BodyCoveringType bodyCoveringType, FluidFlavour flavour, List<String> descriptors, List<FluidModifier> fluidModifiers, Race race) {
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
	public BodyCoveringType getBodyCoveringType() {
		return bodyCoveringType;
	}

	public List<FluidModifier> getFluidModifiers() {
		return fluidModifiers;
	}

	@Override
	public Race getRace() {
		return race;
	}
}
