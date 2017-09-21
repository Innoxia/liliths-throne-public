package com.base.game.character.body.types;

import java.util.ArrayList;
import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.valueEnums.OrificeModifier;
import com.base.game.character.race.Race;
import com.base.utils.Util;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum NippleType implements BodyPartTypeInterface {
	
	HUMAN(BodyCoveringType.NIPPLES, Race.HUMAN),

	ANGEL(BodyCoveringType.NIPPLES, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.NIPPLES, Race.DEMON),

	DOG_MORPH(BodyCoveringType.NIPPLES, Race.DOG_MORPH),
	
	WOLF_MORPH(BodyCoveringType.NIPPLES, Race.WOLF_MORPH),
	
	CAT_MORPH(BodyCoveringType.NIPPLES, Race.CAT_MORPH),
	
	COW_MORPH(BodyCoveringType.NIPPLES, Race.COW_MORPH),
	
	SQUIRREL_MORPH(BodyCoveringType.NIPPLES, Race.SQUIRREL_MORPH),
	
	HORSE_MORPH(BodyCoveringType.NIPPLES, Race.HORSE_MORPH),
	
	HARPY(BodyCoveringType.NIPPLES, Race.HARPY),

	SLIME(BodyCoveringType.NIPPLES_SLIME, Race.SLIME);

	
	private BodyCoveringType skinType;
	private Race race;
	private List<OrificeModifier> defaultRacialOrificeModifiers;

	private NippleType(BodyCoveringType skinType, Race race, OrificeModifier... defaultRacialOrificeModifiers) {
		this.skinType = skinType;
		this.race = race;
		this.defaultRacialOrificeModifiers = new ArrayList<>();
		for(OrificeModifier om : defaultRacialOrificeModifiers) {
			this.defaultRacialOrificeModifiers.add(om);
		}
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
		String name[] = new String[] {"nipple", "teat"};
		
		return name[Util.random.nextInt(name.length)];
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		String name[] = new String[] {"nipples", "teats" };
		
		return name[Util.random.nextInt(name.length)];
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		String descriptor[];
		
		switch(this){
			case ANGEL:
				descriptor = new String[] { "flawless", "perky" };
				break;
			case DEMON_COMMON:
				descriptor = new String[] { "demonic", "irresistible" };
				break;
			case HUMAN:
				descriptor = new String[] { "" };
				break;
			case SLIME:
				descriptor = new String[] { "gooey" };
				break;
			default:
				descriptor = new String[] { "" };
				break;
		}
		
		return descriptor[Util.random.nextInt(descriptor.length)];
	}
	
	@Override
	public BodyCoveringType getBodyCoveringType() {
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}

	public List<OrificeModifier> getDefaultRacialOrificeModifiers() {
		return defaultRacialOrificeModifiers;
	}
}
