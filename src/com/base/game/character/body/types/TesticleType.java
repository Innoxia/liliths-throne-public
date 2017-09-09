package com.base.game.character.body.types;

import com.base.game.character.GameCharacter;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum TesticleType implements BodyPartTypeInterface {
	
	NONE(null, FluidType.CUM_HUMAN, null, false),

	HUMAN(BodyCoveringType.HUMAN, FluidType.CUM_HUMAN, Race.HUMAN, false),

	ANGEL(BodyCoveringType.ANGEL, FluidType.CUM_ANGEL, Race.ANGEL, false),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, FluidType.CUM_DEMON, Race.DEMON, false),

	BOVINE(BodyCoveringType.BOVINE_FUR, FluidType.CUM_COW_MORPH, Race.COW_MORPH, false),
	
	CANINE(BodyCoveringType.CANINE_FUR, FluidType.CUM_DOG_MORPH, Race.DOG_MORPH, false),
	
	LUPINE(BodyCoveringType.LYCAN_FUR, FluidType.CUM_WOLF_MORPH, Race.WOLF_MORPH, false),

	FELINE(BodyCoveringType.FELINE_FUR, FluidType.CUM_CAT_MORPH, Race.CAT_MORPH, false),

	EQUINE(BodyCoveringType.HORSE_HAIR, FluidType.CUM_HORSE_MORPH, Race.HORSE_MORPH, false),

	SLIME(BodyCoveringType.SLIME, FluidType.CUM_SLIME, Race.SLIME, false),

	AVIAN(BodyCoveringType.FEATHERS, FluidType.CUM_HARPY, Race.HARPY, true),
	
	SQUIRREL(BodyCoveringType.SQUIRREL_FUR, FluidType.CUM_SQUIRREL_MORPH, Race.SQUIRREL_MORPH, false);

	
	private BodyCoveringType skinType;
	private FluidType fluidType;
	private Race race;
	private boolean internal;

	private TesticleType(BodyCoveringType skinType, FluidType fluidType, Race race, boolean internal) {
		this.skinType = skinType;
		this.fluidType = fluidType;
		this.race = race;
		this.internal = internal;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "";
	}

	@Override
	public boolean isDefaultPlural() {
		return true;
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return UtilText.returnStringAtRandom("ball", "testicle");
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return UtilText.returnStringAtRandom("balls", "testicles");
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		return gc.getTesticleSize().getDescriptor();
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

	public boolean isInternal() {
		return internal;
	}
}
