package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.8.2
 * @version 0.3.8.2
 * @author Innoxia
 */
public abstract class AbstractNippleType implements BodyPartTypeInterface {

	private AbstractBodyCoveringType skinType;
	private AbstractRace race;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	List<OrificeModifier> defaultRacialOrificeModifiers;
	
	/**
	 * @param skinType What covers this nipple.
	 * @param race What race has this ass type.
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this ass type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this ass type.
	 * @param defaultRacialOrificeModifiers Which modifiers this nipple naturally spawns with.
	 */
	public AbstractNippleType(AbstractBodyCoveringType skinType,
			AbstractRace race,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			List<OrificeModifier> defaultRacialOrificeModifiers) {
		
		this.skinType = skinType;
		this.race = race;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;

		if(defaultRacialOrificeModifiers==null) {
			this.defaultRacialOrificeModifiers = new ArrayList<>();
		} else {
			this.defaultRacialOrificeModifiers = defaultRacialOrificeModifiers;
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
					return "nipple";
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
		if (gc.isFeminine()) {
			return Util.randomItemFrom(descriptorsFeminine);
		} else {
			return Util.randomItemFrom(descriptorsMasculine);
		}
	}

	@Override
	public AbstractBodyCoveringType getBodyCoveringType(Body body) {
		return skinType;
	}

	@Override
	public AbstractRace getRace() {
		return race;
	}

	public List<OrificeModifier> getDefaultRacialOrificeModifiers() {
		return defaultRacialOrificeModifiers;
	}
}
