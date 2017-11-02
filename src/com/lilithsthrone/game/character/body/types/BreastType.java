package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * BreastType is only really a change of nipple type. Breasts always look
 * human-like. Don't use SkinType for Breasts. Instead, use the character's main
 * body skin.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum BreastType implements BodyPartTypeInterface {
	HUMAN(NippleType.HUMAN, FluidType.MILK_HUMAN, BodyCoveringType.HUMAN, Race.HUMAN),

	ANGEL(NippleType.ANGEL, FluidType.MILK_ANGEL, BodyCoveringType.ANGEL, Race.ANGEL),

	DEMON_COMMON(NippleType.DEMON_COMMON, FluidType.MILK_DEMON_COMMON, BodyCoveringType.DEMON_COMMON, Race.DEMON),

	DOG_MORPH(NippleType.DOG_MORPH, FluidType.MILK_DOG_MORPH, BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),
	
	WOLF_MORPH(NippleType.WOLF_MORPH, FluidType.MILK_WOLF_MORPH, BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),
	
	COW_MORPH(NippleType.COW_MORPH, FluidType.MILK_COW_MORPH, BodyCoveringType.BOVINE_FUR, Race.COW_MORPH),
	
	CAT_MORPH(NippleType.CAT_MORPH, FluidType.MILK_CAT_MORPH, BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),
	
	SQUIRREL_MORPH(NippleType.SQUIRREL_MORPH, FluidType.MILK_SQUIRREL_MORPH, BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH),
	
	GATOR_MORPH(NippleType.GATOR_MORPH, FluidType.MILK_GATOR_MORPH, BodyCoveringType.SCALES_GATOR, Race.GATOR_MORPH),
	
	HORSE_MORPH(NippleType.HORSE_MORPH, FluidType.MILK_HORSE_MORPH, BodyCoveringType.HORSE_HAIR, Race.HORSE_MORPH),
	
	HARPY(NippleType.HARPY, FluidType.MILK_HARPY, BodyCoveringType.FEATHERS, Race.HARPY),

	SLIME(NippleType.SLIME, FluidType.MILK_SLIME, BodyCoveringType.SLIME, Race.SLIME);

	private NippleType nippleType;
	private FluidType fluidType;
	private BodyCoveringType skinType;
	private Race race;

	private BreastType(NippleType nippleType, FluidType fluidType, BodyCoveringType skinType, Race race) {
		this.nippleType = nippleType;
		this.fluidType = fluidType;
		this.skinType = skinType;
		this.race = race;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getBreastRows()==1) {
			return "a pair of";
		} else if(gc.getBreastRows()==2) {
			return "two pairs of";
		} else {
			return "three pairs of";
		}
	}

	@Override
	public boolean isDefaultPlural() {
		return true;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		if(gc.getBreastSize()==CupSize.FLAT) {
			return "pec";
		}
		
		switch(this){
			default:
				return UtilText.returnStringAtRandom("breast", "boob", "tit");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(gc.getBreastSize()==CupSize.FLAT) {
			return "pecs";
		}
		
		switch(this){
			default:
				return UtilText.returnStringAtRandom("breasts", "boobs", "mammaries", "tits");
		}
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		if(gc.getBreastSize()==CupSize.FLAT)
			return "";
		switch(this){
			default:
				return gc.getBreastSize().getDescriptor();
		}
	}
	
	public NippleType getNippleType() {
		return nippleType;
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
}
