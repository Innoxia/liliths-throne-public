package com.lilithsthrone.game.character.body.valueEnums;

/**
 * Measurements are in inches. Measured in bust to underbust using the UK system.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum CupSize {
	FLAT("no", "no", 0, 3),
	TRAINING("almost unnoticable", "training", 3, 4),
	AA("extremely tiny", "AA", 4, 5),
	A("tiny", "A", 5, 6),
	B("small", "B", 6, 7),
	C("average-sized", "C", 7, 8),
	D("large", "D", 8, 9),
	DD("large", "DD", 9, 10),
	E("sizeable", "E", 10, 11),
	F("sizeable", "F", 11, 12),
	FF("sizeable", "FF", 12, 13),
	G("huge", "G", 13, 14),
	GG("huge", "GG", 14, 15),
	H("huge", "H", 15, 16),
	HH("massive", "HH", 16, 17),
	J("massive", "J", 17, 18),
	JJ("massive", "JJ", 18, 19),
	K("gigantic", "K", 19, 20),
	KK("gigantic", "KK", 20, 21),
	L("gigantic", "L", 21, 22),
	LL("colossal", "LL", 22, 23),
	M("colossal", "M", 23, 24),
	MM("colossal", "MM", 24, 25),
	N("colossal", "N", 25, 50),
	EXTREME("extreme", "X", 50, 75),
	MONSTROUS("monstrous", "XX", 75, 100),
	MAXIMUM("impossibly colossal", "XXX", 100, 101);

	private String descriptor, cupSizeName;
	private int measurement, upperBound;

	private CupSize(String descriptor, String cupSizeName, int measurement, int upperBound) {
		this.descriptor = descriptor;
		this.cupSizeName = cupSizeName;
		this.measurement = measurement;
		this.upperBound = upperBound;
	}

	/**
	 * @param inches Measurement in inches from bust to underbust.
	 */
	public static CupSize getCupSizeFromInt(int inches) {
		for (CupSize cs : values()) {
			if (inches >= cs.measurement && inches < cs.upperBound) {
				return cs;
			}
		}
		return MAXIMUM;
	}

	/**
	 * To fit into a sentence: "You have "+getDescriptor()+" breasts."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public String getCupSizeName() {
		return cupSizeName;
	}

	public int getMeasurement() {
		return measurement;
	}
}
