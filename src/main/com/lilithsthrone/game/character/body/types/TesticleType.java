package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.83
 * @version 0.2.5
 * @author Innoxia
 */
public enum TesticleType implements BodyPartTypeInterface {
	
	NONE(null, FluidType.CUM_HUMAN, null, false),

	DILDO(BodyCoveringType.RUBBER, FluidType.CUM_HUMAN, Race.HUMAN, false),
	
	HUMAN(BodyCoveringType.PENIS, FluidType.CUM_HUMAN, Race.HUMAN, false),

	ANGEL(BodyCoveringType.PENIS, FluidType.CUM_ANGEL, Race.ANGEL, false),

	DEMON_COMMON(BodyCoveringType.PENIS, FluidType.CUM_DEMON, Race.DEMON, false),

	BOVINE(BodyCoveringType.BOVINE_FUR, FluidType.CUM_COW_MORPH, Race.COW_MORPH, false),
	
	CANINE(BodyCoveringType.CANINE_FUR, FluidType.CUM_DOG_MORPH, Race.DOG_MORPH, false),
	
	LUPINE(BodyCoveringType.LYCAN_FUR, FluidType.CUM_WOLF_MORPH, Race.WOLF_MORPH, false),
	
	FOX_MORPH(BodyCoveringType.FOX_FUR, FluidType.CUM_FOX_MORPH, Race.FOX_MORPH, false),

	FELINE(BodyCoveringType.FELINE_FUR, FluidType.CUM_CAT_MORPH, Race.CAT_MORPH, false),

	ALLIGATOR_MORPH(BodyCoveringType.PENIS, FluidType.CUM_ALLIGATOR_MORPH, Race.ALLIGATOR_MORPH, true),

	EQUINE(BodyCoveringType.PENIS, FluidType.CUM_HORSE_MORPH, Race.HORSE_MORPH, false),

	REINDEER_MORPH(BodyCoveringType.PENIS, FluidType.CUM_REINDEER_MORPH, Race.REINDEER_MORPH, false),

	AVIAN(BodyCoveringType.PENIS, FluidType.CUM_HARPY, Race.HARPY, true),
	
	SQUIRREL(BodyCoveringType.SQUIRREL_FUR, FluidType.CUM_SQUIRREL_MORPH, Race.SQUIRREL_MORPH, false),

	RAT_MORPH(BodyCoveringType.PENIS, FluidType.CUM_RAT_MORPH, Race.RAT_MORPH, false),
	
	RABBIT_MORPH(BodyCoveringType.PENIS, FluidType.CUM_RABBIT_MORPH, Race.RABBIT_MORPH, false),

	BAT_MORPH(BodyCoveringType.PENIS, FluidType.CUM_BAT_MORPH, Race.BAT_MORPH, false);

	
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

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static TesticleType getTypeFromString(String value) {
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

	public boolean isInternal() {
		return internal;
	}
}