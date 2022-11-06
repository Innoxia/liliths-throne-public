package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public enum HairStyle {

//	- parted down the middle
//	- side parted
//	- shaved (different from bald)
//	- punk (hair draped over face)
	
	NONE("natural", Femininity.ANDROGYNOUS, HairLength.ZERO_BALD) {
		@Override
		public String getName(Body body) {
			if(body!=null && body.isFeral()) {
				if(body.getLegConfiguration()==LegConfiguration.AVIAN) {
					return "plumed";
				} else {
					return "maned";
				}
			}
			return super.getName(body);
		}
	},
	MESSY("messy", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT),
	LOOSE("loose", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT),
	CURLY("curly", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT),
	STRAIGHT("straight", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT),
	SLICKED_BACK("slicked-back", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT),
	SIDECUT("sidecut", Femininity.ANDROGYNOUS, HairLength.TWO_SHORT),
	MOHAWK("mohawk", Femininity.ANDROGYNOUS, HairLength.TWO_SHORT),
	DREADLOCKS("dreadlocks", Femininity.ANDROGYNOUS, HairLength.TWO_SHORT),
	
	AFRO("afro", Femininity.MASCULINE, HairLength.ONE_VERY_SHORT),
	TOPKNOT("topknot", Femininity.MASCULINE, HairLength.THREE_SHOULDER_LENGTH),
	
	PIXIE("pixie-cut", Femininity.FEMININE, HairLength.TWO_SHORT),
	BUN("bun", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	BOB_CUT("bob-cut", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	CHONMAGE("chonmage", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	WAVY("wavy", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	PONYTAIL("ponytail", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	LOW_PONYTAIL("low ponytail", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	TWIN_TAILS("twintails", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	SIDE_BRAIDS("side braids", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	CHIGNON("chignon", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	BRAIDED("braided", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	TWIN_BRAIDS("twin braids", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	CROWN_BRAID("crown braid", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	DRILLS("ojou ringlets", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	HIME_CUT("hime-cut", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	BIRD_CAGE("bird cage", Femininity.FEMININE, HairLength.SEVEN_TO_FLOOR);
	
	private String descriptor;
	private Femininity femininity;
	private int minimumLengthRequired;

	private HairStyle(String descriptor, Femininity femininity, HairLength minimumLengthRequired) {
		this.descriptor = descriptor;
		this.femininity = femininity;
		this.minimumLengthRequired = minimumLengthRequired.getMinimumValue();
	}

	public String getName(GameCharacter owner) {
		if(owner==null) {
			return descriptor;
		}
		return getName(owner.getBody());
	}

	public String getName(Body body) {
		return descriptor;
	}
	
	/** This should just be used for random character hair style generation. */
	public Femininity getFemininity() {
		return femininity;
	}

	public int getMinimumLengthRequired() {
		return minimumLengthRequired;
	}
	
	/**
	 * @return A random hair style, filtered by femininity and length limitations.
	 */
	public static HairStyle getRandomHairStyle(boolean feminine, int hairLength) {
		List<HairStyle> availableStyles = new ArrayList<>();
		
		for(HairStyle hs : HairStyle.values()) {
			if((hs.getFemininity()==Femininity.ANDROGYNOUS || hs.getFemininity().isFeminine()==feminine) && hs.getMinimumLengthRequired() <= hairLength) {
				availableStyles.add(hs);
			}
		}
		
		// Most likely to have a "normal" hair style:
		if(Math.random()>0.10f) {
			availableStyles.remove(HairStyle.AFRO);
			availableStyles.remove(HairStyle.SIDECUT);
			availableStyles.remove(HairStyle.MOHAWK);
			availableStyles.remove(HairStyle.HIME_CUT);
			availableStyles.remove(HairStyle.CHONMAGE);
			availableStyles.remove(HairStyle.DREADLOCKS);
			availableStyles.remove(HairStyle.BIRD_CAGE);
			availableStyles.remove(HairStyle.DRILLS);
		}
		
		return availableStyles.get(Util.random.nextInt(availableStyles.size()));
	}
}
