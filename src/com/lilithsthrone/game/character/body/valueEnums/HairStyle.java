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
	
	NONE("natural", HairLength.ZERO_BALD, true),
	MESSY("messy", HairLength.ONE_VERY_SHORT, true),
	LOOSE("loose", HairLength.ONE_VERY_SHORT, true),
	CURLY("curly", HairLength.ONE_VERY_SHORT, true),
	STRAIGHT("straight", HairLength.ONE_VERY_SHORT, true),
	SLICKED_BACK("slicked-back", HairLength.ONE_VERY_SHORT, true),
	AFRO("afro", HairLength.ONE_VERY_SHORT, false),
	SIDECUT("sidecut", HairLength.TWO_SHORT, false),
	PIXIE("pixie-cut", HairLength.TWO_SHORT, false),
	MOHAWK("mohawk", HairLength.TWO_SHORT, false),
	DREADLOCKS("dreadlocks", HairLength.TWO_SHORT, false),
	BUN("bun", HairLength.THREE_SHOULDER_LENGTH, true),
	BOB_CUT("bob-cut", HairLength.THREE_SHOULDER_LENGTH, false),
	CHONMAGE("chonmage", HairLength.THREE_SHOULDER_LENGTH, false),
	TOPKNOT("topknot", HairLength.THREE_SHOULDER_LENGTH, true),
	WAVY("wavy", HairLength.THREE_SHOULDER_LENGTH, false),
	PONYTAIL("ponytail", HairLength.THREE_SHOULDER_LENGTH, true),
	LOW_PONYTAIL("low ponytail", HairLength.THREE_SHOULDER_LENGTH, true),
	TWIN_TAILS("twintails", HairLength.THREE_SHOULDER_LENGTH, false),
	CHIGNON("chignon", HairLength.FOUR_MID_BACK, false),
	BRAIDED("braided", HairLength.FOUR_MID_BACK, false),
	TWIN_BRAIDS("twin braids", HairLength.FOUR_MID_BACK, false),
	CROWN_BRAID("crown braid", HairLength.FOUR_MID_BACK, false),
	DRILLS("drill hair", HairLength.FOUR_MID_BACK, false),
	HIME_CUT("hime-cut", HairLength.FOUR_MID_BACK, false),
	BIRD_CAGE("bird cage", HairLength.SEVEN_TO_FLOOR, false);
	
	private String descriptor;
	private int minimumLengthRequired;
	private boolean selfapply;

	private HairStyle(String descriptor, HairLength minimumLengthRequired, boolean selfApply) {
		this.descriptor = descriptor;
		this.minimumLengthRequired = minimumLengthRequired.getMinimumValue();
		this.selfapply = selfApply;
	}

	public String getName() {
		return descriptor;
	}

	public int getMinimumLengthRequired() {
		return minimumLengthRequired;
	}

	public boolean isSelfApply() {
		return selfapply;
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
