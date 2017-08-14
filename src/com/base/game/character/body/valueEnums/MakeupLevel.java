package com.base.game.character.body.valueEnums;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum MakeupLevel {
	NONE("no", 0),
	
	MINIMAL("an unnoticable amount of", 1),
	
	LOW("a slight touch of", 2),
	
	AVERAGE("a tasteful amount of", 3),
	
	HIGH("a lot of", 4),
	
	VERY_HIGH("a heavy coating of", 5),
	
	EXTREME("a thick plastering of", 6),
	
	MAXIMUM("an absurd amount of", 7);
	
	
	private String descriptor;
	private int makeupLevel;

	private MakeupLevel(String descriptor, int makeupLevel) {
		this.descriptor = descriptor;
		this.makeupLevel = makeupLevel;
	}

	public static MakeupLevel getMakeupLevelFromInt(int makeupLevel) {
		for (MakeupLevel ml : values()) {
			if (makeupLevel == ml.getValue()) {
				return ml;
			}
		}
		return MAXIMUM;
	}

	/**
	 * To fit into a sentence: "Your face has "+getDescriptor()+" makeup on."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public int getValue() {
		return makeupLevel;
	}
}
