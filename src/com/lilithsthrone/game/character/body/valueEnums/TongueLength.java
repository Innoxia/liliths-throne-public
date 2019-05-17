package com.lilithsthrone.game.character.body.valueEnums;

/**
 * Sizes in cm.
 * 
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum TongueLength {
	
	ZERO_NORMAL("normal-sized", 0, 5),

	ONE_LONG("long", 5, 10),

	TWO_VERY_LONG("very long", 10, 20),

	THREE_EXTREMELY_LONG("extremely long", 20, 30),

	FOUR_ABSURDLY_LONG("absurdly long", 30, 60);

	private int minimumValue, maximumValue;
	private String descriptor;

	private TongueLength(String descriptor, int minimumValue, int maximumValue) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}

	public int getMedianValue() {
		return minimumValue + ((maximumValue - minimumValue) / 2);
	}

	public static TongueLength getTongueLengthFromInt(int cm) {
		for(TongueLength ps : TongueLength.values()) {
			if(cm>=ps.getMinimumValue() && cm<ps.getMaximumValue()) {
				return ps;
			}
		}
		return FOUR_ABSURDLY_LONG;
	}
	
	public String getDescriptor() {
		return descriptor;
	}
}
