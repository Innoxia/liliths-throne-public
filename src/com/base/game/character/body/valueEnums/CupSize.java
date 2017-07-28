package com.base.game.character.body.valueEnums;

/**
 * Measurements are in inches. Measured in bust to underbust using the UK
 * system.
 * 
 * @since 0.1.0
 * @version 0.1.0
 * @author Innoxia
 */
public enum CupSize {
	FLAT("no", "no", 0),
	TRAINING("almost unnoticable", "training", 3),
	AA("extremely tiny", "AA", 4),
	A("tiny", "A", 5),
	B("small", "B", 6),
	C("average-sized", "C", 7),
	D("large", "D", 8),
	DD("large", "DD", 9),
	E("sizeable", "E", 10),
	F("sizeable", "F", 11),
	FF("sizeable", "FF", 12),
	G("huge", "G", 13),
	GG("huge", "GG", 14),
	H("huge", "H", 15),
	HH("massive", "HH", 16),
	J("massive", "J", 17),
	JJ("massive", "JJ", 18),
	K("gigantic", "K", 19),
	KK("gigantic", "KK", 20),
	L("gigantic", "L", 21),
	LL("colossal", "LL", 22),
	M("colossal", "M", 23),
	MM("colossal", "MM", 24),
	N("colossal", "N", 25),
	EXTREME("extreme", "X", 50),
	MONSTROUS("monstrous", "XX", 75),
	MAXIMUM("impossibly colossal", "XXX", 100);

	private String descriptor, cupSizeName;
	private int measurement;

	private CupSize(String descriptor, String cupSizeName, int measurement) {
		this.descriptor = descriptor;
		this.cupSizeName = cupSizeName;
		this.measurement = measurement;
	}

	/**
	 * @param inches
	 *            Measurement in inches from bust to underbust.
	 */
	public static CupSize getCupSizeFromInt(int inches) {
		for (CupSize cs : values())
			if (inches <= cs.measurement)
				return cs;
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
