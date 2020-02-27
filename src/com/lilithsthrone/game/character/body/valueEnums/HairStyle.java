package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.1.84
 * @author Innoxia
 */
public enum HairStyle {

//	- parted down the middle
//	- side parted
//	- shaved (different from bald)
//	- punk (hair draped over face)
	
	NONE("natural", HairLength.ZERO_BALD),
	MESSY("messy", HairLength.ONE_VERY_SHORT),
	LOOSE("loose", HairLength.ONE_VERY_SHORT),
	CURLY("curly", HairLength.ONE_VERY_SHORT),
	STRAIGHT("straight", HairLength.ONE_VERY_SHORT),
	SLICKED_BACK("slicked-back", HairLength.ONE_VERY_SHORT),
	AFRO("afro", HairLength.ONE_VERY_SHORT),
	SIDECUT("sidecut", HairLength.TWO_SHORT),
	PIXIE("pixie-cut", HairLength.TWO_SHORT),
	MOHAWK("mohawk", HairLength.TWO_SHORT),
	DREADLOCKS("dreadlocks", HairLength.TWO_SHORT),
	BUN("bun", HairLength.THREE_SHOULDER_LENGTH),
	BOB_CUT("bob-cut", HairLength.THREE_SHOULDER_LENGTH),
	CHONMAGE("chonmage", HairLength.THREE_SHOULDER_LENGTH),
	TOPKNOT("topknot", HairLength.THREE_SHOULDER_LENGTH),
	WAVY("wavy", HairLength.THREE_SHOULDER_LENGTH),
	PONYTAIL("ponytail", HairLength.THREE_SHOULDER_LENGTH),
	LOW_PONYTAIL("low ponytail", HairLength.THREE_SHOULDER_LENGTH),
	TWIN_TAILS("twintails", HairLength.THREE_SHOULDER_LENGTH),
	CHIGNON("chignon", HairLength.FOUR_MID_BACK),
	BRAIDED("braided", HairLength.FOUR_MID_BACK),
	TWIN_BRAIDS("twin braids", HairLength.FOUR_MID_BACK),
	CROWN_BRAID("crown braid", HairLength.FOUR_MID_BACK),
	DRILLS("drill hair", HairLength.FOUR_MID_BACK),
	HIME_CUT("hime-cut", HairLength.FOUR_MID_BACK),
	BIRD_CAGE("bird cage", HairLength.SEVEN_TO_FLOOR);
	
	private String descriptor;
	private int minimumLengthRequired;

	private HairStyle(String descriptor, HairLength minimumLengthRequired) {
		this.descriptor = descriptor;
		this.minimumLengthRequired = minimumLengthRequired.getMinimumValue();
	}

	public String getName() {
		return descriptor;
	}

	public int getMinimumLengthRequired() {
		return minimumLengthRequired;
	}
	
	public static HairStyle getRandomHairStyle(int hairLength) {
		List<HairStyle> availableStyles = new ArrayList<>();
		
		for(HairStyle hs : HairStyle.values()) {
			if(hs.getMinimumLengthRequired() <= hairLength) {
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
