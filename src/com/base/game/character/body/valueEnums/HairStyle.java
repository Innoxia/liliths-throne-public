package com.base.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.List;

import com.base.utils.Util;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum HairStyle { // TODO

	NONE("natural", HairLength.ZERO_BALD),
	LOOSE("loose", HairLength.ONE_VERY_SHORT),
	CURLY("curly", HairLength.ONE_VERY_SHORT),
	STRAIGHT("straight", HairLength.ONE_VERY_SHORT),
	WAVY("wavy", HairLength.THREE_SHOULDER_LENGTH),
	PONYTAIL("ponytail", HairLength.THREE_SHOULDER_LENGTH),
	TWIN_TAILS("twintails", HairLength.THREE_SHOULDER_LENGTH),
	BRAIDED("braided", HairLength.FOUR_MID_BACK);
	
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
		
		return availableStyles.get(Util.random.nextInt(availableStyles.size()));
	}
}
