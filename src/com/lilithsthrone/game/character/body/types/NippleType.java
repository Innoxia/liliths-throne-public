package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.1
 * @author Innoxia
 */
public enum NippleType implements BodyPartTypeInterface {
	
	HUMAN(Race.HUMAN),

	ANGEL(Race.ANGEL),

	DEMON_COMMON(Race.DEMON),

	DOG_MORPH(Race.DOG_MORPH),
	
	WOLF_MORPH(Race.WOLF_MORPH),
	
	FOX_MORPH(Race.FOX_MORPH),
	
	CAT_MORPH(Race.CAT_MORPH),
	
	COW_MORPH(Race.COW_MORPH),
	
	SQUIRREL_MORPH(Race.SQUIRREL_MORPH),
	
	RAT_MORPH(Race.RAT_MORPH),
	
	BAT_MORPH(Race.BAT_MORPH),
	
	RABBIT_MORPH(Race.RABBIT_MORPH),
	
	ALLIGATOR_MORPH(Race.ALLIGATOR_MORPH),
	
	HORSE_MORPH(Race.HORSE_MORPH),
	
	REINDEER_MORPH(Race.REINDEER_MORPH),
	
	HARPY(Race.HARPY);

	
	private Race race;
	private List<OrificeModifier> defaultRacialOrificeModifiers;

	private NippleType(Race race, OrificeModifier... defaultRacialOrificeModifiers) {
		this.race = race;
		this.defaultRacialOrificeModifiers = new ArrayList<>();
		Collections.addAll(this.defaultRacialOrificeModifiers, defaultRacialOrificeModifiers);
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static NippleType getTypeFromString(String value) {
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
		switch(gc.getNippleShape()) {
			case LIPS:
				return  UtilText.returnStringAtRandom("lipple", "nipple-lip");
			case INVERTED:
				if(gc.hasBreasts()) {
					return UtilText.returnStringAtRandom("inverted nipple", "inverted teat");
				} else {
					return "inverted nipple";
				}
			case NORMAL:
				if(gc.hasBreasts()) {
					return UtilText.returnStringAtRandom("nipple", "teat");
					
				} else {
					return "nipples";
				}
			case VAGINA:
				return UtilText.returnStringAtRandom("nipple-cunt", "nipple-pussy");
		}
		return "";
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(gc.getNippleShape()) {
			case LIPS:
				return  UtilText.returnStringAtRandom("lipples", "nipple-lips");
			case INVERTED:
				if(gc.hasBreasts()) {
					return UtilText.returnStringAtRandom("inverted nipples", "inverted teats");
				} else {
					return "inverted nipples";
				}
			case NORMAL:
				if(gc.hasBreasts()) {
					return UtilText.returnStringAtRandom("nipples", "teats");
				} else {
					return "nipples";
				}
			case VAGINA:
				return UtilText.returnStringAtRandom("nipple-cunts", "nipple-pussies");
		}
		return "";
	}

	// Not used for now, due to being terrible:
	public String getNameCrotchSingular(GameCharacter gc) {
		if(gc.getBreastCrotchShape()==BreastShape.UDDERS) {
			switch(gc.getNippleShape()) {
				case LIPS:
					return  UtilText.returnStringAtRandom("udder-lipple", "udder-nipple-lip");
				case INVERTED:
					if(gc.hasBreasts()) {
						return UtilText.returnStringAtRandom("inverted udder-nipple", "inverted udder-teat");
					} else {
						return "inverted udder-nipple";
					}
				case NORMAL:
					if(gc.hasBreasts()) {
						return UtilText.returnStringAtRandom("udder-nipple", "udder-teat");
					} else {
						return "udder-nipple";
					}
				case VAGINA:
					return UtilText.returnStringAtRandom("udder-nipple-cunt", "udder-nipple-pussy");
			}
			
		} else {
			switch(gc.getNippleShape()) {
				case LIPS:
					return  UtilText.returnStringAtRandom("crotch-lipple", "crotch-nipple-lip");
				case INVERTED:
					if(gc.hasBreasts()) {
						return UtilText.returnStringAtRandom("inverted crotch-nipple", "inverted crotch-teat");
					} else {
						return "inverted crotch-nipple";
					}
				case NORMAL:
					if(gc.hasBreasts()) {
						return UtilText.returnStringAtRandom("crotch-nipple", "crotch-teat");
						
					} else {
						return "crotch-nipple";
					}
				case VAGINA:
					return UtilText.returnStringAtRandom("crotch-nipple-cunt", "crotch-nipple-pussy");
			}
		}
		return "";
	}

	// Not used for now, due to being terrible:
	public String getNameCrotchPlural(GameCharacter gc) {
		if(gc.getBreastCrotchShape()==BreastShape.UDDERS) {
			switch(gc.getNippleShape()) {
				case LIPS:
					return  UtilText.returnStringAtRandom("udder-lipples", "udder-nipple-lips");
				case INVERTED:
					if(gc.hasBreasts()) {
						return UtilText.returnStringAtRandom("inverted udder-nipples", "inverted udder-teats");
					} else {
						return "inverted udder-nipples";
					}
				case NORMAL:
					if(gc.hasBreasts()) {
						return UtilText.returnStringAtRandom("udder-nipples", "udder-teats");
						
					} else {
						return "udder-nipples";
					}
				case VAGINA:
					return UtilText.returnStringAtRandom("udder-nipple-cunts", "udder-nipple-pussies");
			}
		} else {
			switch(gc.getNippleShape()) {
				case LIPS:
					return  UtilText.returnStringAtRandom("crotch-lipples", "crotch-nipple-lips");
				case INVERTED:
					if(gc.hasBreasts()) {
						return UtilText.returnStringAtRandom("inverted crotch-nipples", "inverted crotch-teats");
					} else {
						return "inverted crotch-nipples";
					}
				case NORMAL:
					if(gc.hasBreasts()) {
						return UtilText.returnStringAtRandom("crotch-nipples", "crotch-teats");
						
					} else {
						return "crotch-nipples";
					}
				case VAGINA:
					return UtilText.returnStringAtRandom("crotch-nipple-cunts", "crotch-nipple-pussies");
			}
		}
		return "";
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
			default:
				descriptor = new String[] { "" };
				break;
		}
		
		return descriptor[Util.random.nextInt(descriptor.length)];
	}
	
	@Override
	public BodyCoveringType getBodyCoveringType(Body body) {
		return BodyCoveringType.NIPPLES;
	}

	@Override
	public Race getRace() {
		return race;
	}

	public List<OrificeModifier> getDefaultRacialOrificeModifiers() {
		return defaultRacialOrificeModifiers;
	}
}