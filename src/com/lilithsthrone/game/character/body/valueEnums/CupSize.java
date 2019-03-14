package com.lilithsthrone.game.character.body.valueEnums;

/**
 * Measurements are in inches. Measured in bust to underbust using the UK system.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum CupSize {
	
	FLAT("flat", "flat", 0),

	// Training bra sizes:
	
	TRAINING_AAA("almost unnoticeable", "training-AAA", 1) {
		@Override
		public boolean isTrainingBraSize() {
			return true;
		}
	},
	TRAINING_AA("almost unnoticeable", "training-AA", 2) {
		@Override
		public boolean isTrainingBraSize() {
			return true;
		}
	},
	TRAINING_A("almost unnoticeable", "training-A", 3) {
		@Override
		public boolean isTrainingBraSize() {
			return true;
		}
	},
	
	// Normal cup sizes:
	
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
	
	// Hyper sizes:
	
	X_AA("extreme", "X-AA", 26),
	X_A("extreme", "X-A", 27),
	X_B("extreme", "X-B", 28),
	X_C("extreme", "X-C", 29),
	X_D("extreme", "X-D", 30),
	X_DD("extreme", "X-DD", 31),
	X_E("extreme", "X-E", 32),
	X_F("extreme", "X-F", 33),
	X_FF("extreme", "X-FF", 34),
	X_G("extreme", "X-G", 35),
	X_GG("extreme", "X-GG", 36),
	X_H("extreme", "X-H", 37),
	X_HH("extreme", "X-HH", 38),
	X_J("extreme", "X-J", 39),
	X_JJ("extreme", "X-JJ", 40),
	X_K("extreme", "X-K", 41),
	X_KK("extreme", "X-KK", 42),
	X_L("extreme", "X-L", 43),
	X_LL("extreme", "X-LL", 44),
	X_M("extreme", "X-M", 45),
	X_MM("extreme", "X-MM", 46),
	X_N("extreme", "X-N", 47),

	XX_AA("monstrous", "XX-AA", 48),
	XX_A("monstrous", "XX-A", 49),
	XX_B("monstrous", "XX-B", 50),
	XX_C("monstrous", "XX-C", 51),
	XX_D("monstrous", "XX-D", 52),
	XX_DD("monstrous", "XX-DD", 53),
	XX_E("monstrous", "XX-E", 54),
	XX_F("monstrous", "XX-F", 55),
	XX_FF("monstrous", "XX-FF", 56),
	XX_G("monstrous", "XX-G", 57),
	XX_GG("monstrous", "XX-GG", 58),
	XX_H("monstrous", "XX-H", 59),
	XX_HH("monstrous", "XX-HH", 60),
	XX_J("monstrous", "XX-J", 61),
	XX_JJ("monstrous", "XX-JJ", 62),
	XX_K("monstrous", "XX-K", 63),
	XX_KK("monstrous", "XX-KK", 64),
	XX_L("monstrous", "XX-L", 65),
	XX_LL("monstrous", "XX-LL", 66),
	XX_M("monstrous", "XX-M", 67),
	XX_MM("monstrous", "XX-MM", 68),
	XX_N("monstrous", "XX-N", 69),

	XXX_AA("hyper", "XXX-AA", 70),
	XXX_A("hyper", "XXX-A", 71),
	XXX_B("hyper", "XXX-B", 72),
	XXX_C("hyper", "XXX-C", 73),
	XXX_D("hyper", "XXX-D", 74),
	XXX_DD("hyper", "XXX-DD", 75),
	XXX_E("hyper", "XXX-E", 76),
	XXX_F("hyper", "XXX-F", 77),
	XXX_FF("hyper", "XXX-FF", 78),
	XXX_G("hyper", "XXX-G", 79),
	XXX_GG("hyper", "XXX-GG", 80),
	XXX_H("hyper", "XXX-H", 81),
	XXX_HH("hyper", "XXX-HH", 82),
	XXX_J("hyper", "XXX-J", 83),
	XXX_JJ("hyper", "XXX-JJ", 84),
	XXX_K("hyper", "XXX-K", 85),
	XXX_KK("hyper", "XXX-KK", 86),
	XXX_L("hyper", "XXX-L", 87),
	XXX_LL("hyper", "XXX-LL", 88),
	XXX_M("hyper", "XXX-M", 89),
	XXX_MM("hyper", "XXX-MM", 90),
	XXX_N("hyper", "XXX-N", 91);

	private String descriptor;
	private String cupSizeName;
	private int measurement;

	private CupSize(String descriptor, String cupSizeName, int measurement) {
		this.descriptor = descriptor;
		this.cupSizeName = cupSizeName;
		this.measurement = measurement;
	}
	
	public boolean isTrainingBraSize() {
		return false;
	}
	
	/**
	 * @return The minimum size which is regarded as a character 'having breasts' by the game.
	 */
	public static CupSize getMinimumCupSizeForBreasts() {
		return CupSize.AA;
	}

	/**
	 * @param size Measurement in inches from bust to underbust.
	 */
	public static CupSize getCupSizeFromInt(int size) {
		for (CupSize cs : values()) {
			if (size == cs.measurement) {
				return cs;
			}
		}
		return XXX_N;
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
	
	public static CupSize getMaximumCupSize() {
		return XXX_N;
	}
}
