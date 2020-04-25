package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.List;

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
	
	NONE("natural", Femininity.ANDROGYNOUS, HairLength.ZERO_BALD, true),
	MESSY("messy", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT, true),
	LOOSE("loose", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT, true),
	CURLY("curly", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT, true),
	STRAIGHT("straight", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT, true),
	SLICKED_BACK("slicked-back", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT, true),
	SIDECUT("sidecut", Femininity.ANDROGYNOUS, HairLength.TWO_SHORT, false),
	MOHAWK("mohawk", Femininity.ANDROGYNOUS, HairLength.TWO_SHORT, false),
	DREADLOCKS("dreadlocks", Femininity.ANDROGYNOUS, HairLength.TWO_SHORT, false),
	
	AFRO("afro", Femininity.MASCULINE, HairLength.ONE_VERY_SHORT, true),
	TOPKNOT("topknot", Femininity.MASCULINE, HairLength.THREE_SHOULDER_LENGTH, false),
	
	PIXIE("pixie-cut", Femininity.FEMININE, HairLength.TWO_SHORT, false),
	BUN("bun", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH, true),
	BOB_CUT("bob-cut", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH, false),
	CHONMAGE("chonmage", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH, false),
	WAVY("wavy", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH, false),
	PONYTAIL("ponytail", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH, true),
	LOW_PONYTAIL("low ponytail", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH, true),
	TWIN_TAILS("twintails", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH, false),
	CHIGNON("chignon", Femininity.FEMININE, HairLength.FOUR_MID_BACK, false),
	BRAIDED("braided", Femininity.FEMININE, HairLength.FOUR_MID_BACK, false),
	TWIN_BRAIDS("twin braids", Femininity.FEMININE, HairLength.FOUR_MID_BACK, false),
	CROWN_BRAID("crown braid", Femininity.FEMININE, HairLength.FOUR_MID_BACK, false),
	DRILLS("drill hair", Femininity.FEMININE, HairLength.FOUR_MID_BACK, false),
	HIME_CUT("hime-cut", Femininity.FEMININE, HairLength.FOUR_MID_BACK, false),
	BIRD_CAGE("bird cage", Femininity.FEMININE, HairLength.SEVEN_TO_FLOOR, false);

	private String descriptor;
	private Femininity femininity;
	private int minimumLengthRequired;
	private boolean selfapply;

	private HairStyle(String descriptor, Femininity femininity, HairLength minimumLengthRequired, boolean selfApply) {
		this.descriptor = descriptor;
		this.femininity = femininity;
		this.minimumLengthRequired = minimumLengthRequired.getMinimumValue();
		this.selfapply = selfApply;
	}

	public String getName() {
		return descriptor;
	}

	/** This should just be used for random character hair style generation. */
	public Femininity getFemininity() {
		return femininity;
	}

	public int getMinimumLengthRequired() {
		return minimumLengthRequired;
	}

	public boolean isSelfApply() {
		return selfapply;
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
