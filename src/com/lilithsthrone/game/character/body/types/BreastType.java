package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * BreastType should only influence a character's milk type.
 * 
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public enum BreastType implements BodyPartTypeInterface {
	HUMAN(NippleType.HUMAN, FluidType.MILK_HUMAN, BodyCoveringType.HUMAN, Race.HUMAN),

	ANGEL(NippleType.ANGEL, FluidType.MILK_ANGEL, BodyCoveringType.ANGEL, Race.ANGEL),

	DEMON_COMMON(NippleType.DEMON_COMMON, FluidType.MILK_DEMON_COMMON, BodyCoveringType.DEMON_COMMON, Race.DEMON),
	
	DOG_MORPH(NippleType.DOG_MORPH, FluidType.MILK_DOG_MORPH, BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),
	
	WOLF_MORPH(NippleType.WOLF_MORPH, FluidType.MILK_WOLF_MORPH, BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),
	
	FOX_MORPH(NippleType.FOX_MORPH, FluidType.MILK_FOX_MORPH, BodyCoveringType.FOX_FUR, Race.FOX_MORPH),
	
	COW_MORPH(NippleType.COW_MORPH, FluidType.MILK_COW_MORPH, BodyCoveringType.BOVINE_FUR, Race.COW_MORPH),
	
	CAT_MORPH(NippleType.CAT_MORPH, FluidType.MILK_CAT_MORPH, BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),
	
	SQUIRREL_MORPH(NippleType.SQUIRREL_MORPH, FluidType.MILK_SQUIRREL_MORPH, BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH),
	
	RAT_MORPH(NippleType.RAT_MORPH, FluidType.MILK_RAT_MORPH, BodyCoveringType.RAT_FUR, Race.RAT_MORPH),
	
	RABBIT_MORPH(NippleType.RABBIT_MORPH, FluidType.MILK_RABBIT_MORPH, BodyCoveringType.RABBIT_FUR, Race.RABBIT_MORPH),
	
	BAT_MORPH(NippleType.BAT_MORPH, FluidType.MILK_BAT_MORPH, BodyCoveringType.BAT_FUR, Race.BAT_MORPH),
	
	ALLIGATOR_MORPH(NippleType.ALLIGATOR_MORPH, FluidType.MILK_ALLIGATOR_MORPH, BodyCoveringType.ALLIGATOR_SCALES, Race.ALLIGATOR_MORPH),
	
	HORSE_MORPH(NippleType.HORSE_MORPH, FluidType.MILK_HORSE_MORPH, BodyCoveringType.HORSE_HAIR, Race.HORSE_MORPH),
	
	REINDEER_MORPH(NippleType.REINDEER_MORPH, FluidType.MILK_REINDEER_MORPH, BodyCoveringType.REINDEER_FUR, Race.REINDEER_MORPH),
	
	HARPY(NippleType.HARPY, FluidType.MILK_HARPY, BodyCoveringType.FEATHERS, Race.HARPY);

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

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static BreastType getTypeFromString(String value) {
		if(value.equals("IMP")) {
			value = "DEMON_COMMON";
		}
		return valueOf(value);
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
	public BodyCoveringType getBodyCoveringType(Body body) {
		if(body!=null) {
			return body.getSkin().getType().getBodyCoveringType(body);
		}
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}

	public FluidType getFluidType() {
		return fluidType;
	}
	
	private static Map<Race, List<BreastType>> typesMap = new HashMap<>();
	public static List<BreastType> getBreastTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<BreastType> types = new ArrayList<>();
		for(BreastType type : BreastType.values()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}