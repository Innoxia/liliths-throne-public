package com.base.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum NippleSize {

	ZERO_TINY(0, "tiny"),
	ONE_SMALL(1, "small"),
	TWO_BIG(2, "big"),
	THREE_LARGE(3, "large"),
	FOUR_MASSIVE(4, "massive");
	
	
	private int value;
	private String descriptor;

	private NippleSize(int value, String descriptor) {
		this.value = value;
		this.descriptor = descriptor;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return descriptor;
	}
	
	public static NippleSize getNippleSizeFromInt(int inches) {
		for(NippleSize as : NippleSize.values()) {
			if(inches == as.getValue()) {
				return as;
			}
		}
		return ZERO_TINY;
	}
}
