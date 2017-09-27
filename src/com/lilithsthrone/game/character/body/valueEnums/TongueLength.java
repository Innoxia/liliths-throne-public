package com.lilithsthrone.game.character.body.valueEnums;

/**
 * Sizes in inches.
 * 
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum TongueLength {
	
	ZERO_NORMAL("normal-sized", 0, 2),

	ONE_LONG("long", 2, 4),

	TWO_VERY_LONG("very long", 4, 8),

	THREE_EXTREMELY_LONG("extremely long", 8, 12),

	FOUR_ABSURDLY_LONG("absurdly long", 12, 24);

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

	public static TongueLength getTongueLengthFromInt(int inches) {
		for(TongueLength ps : TongueLength.values()) {
			if(inches>=ps.getMinimumValue() && inches<ps.getMaximumValue()) {
				return ps;
			}
		}
		return FOUR_ABSURDLY_LONG;
	}
	
	public String getDescriptor() {
		return descriptor;
	}
}
