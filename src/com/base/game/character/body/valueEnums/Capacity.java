package com.base.game.character.body.valueEnums;

import com.base.game.character.body.Penis;

/**
 * Measured in inches of penis size that could fit comfortably within this capacity.
 * 
 * @since 0.1.0
 * @version 0.1.65
 * @author Innoxia
 */
public enum Capacity {
	ZERO_IMPENETRABLE("extremely tight", PenisSize.NEGATIVE_UTILITY_VALUE, PenisSize.ZERO_MICROSCOPIC, PenisSize.ZERO_MICROSCOPIC),
	ONE_EXTREMELY_TIGHT("tight", PenisSize.ZERO_MICROSCOPIC, PenisSize.ONE_TINY, PenisSize.ONE_TINY),
	TWO_TIGHT("somewhat tight", PenisSize.ZERO_MICROSCOPIC, PenisSize.ONE_TINY, PenisSize.TWO_AVERAGE),
	THREE_SLIGHTLY_LOOSE("slightly loose", PenisSize.ONE_TINY, PenisSize.TWO_AVERAGE, PenisSize.THREE_LARGE),
	FOUR_LOOSE("loose", PenisSize.ONE_TINY, PenisSize.THREE_LARGE, PenisSize.FOUR_HUGE),
	FIVE_ROOMY("very loose", PenisSize.TWO_AVERAGE, PenisSize.FOUR_HUGE, PenisSize.FIVE_ENORMOUS),
	SIX_STRETCHED_OPEN("stretched open", PenisSize.TWO_AVERAGE, PenisSize.FIVE_ENORMOUS, PenisSize.SIX_GIGANTIC),
	SEVEN_GAPING("gaping wide", PenisSize.THREE_LARGE, PenisSize.SIX_GIGANTIC, PenisSize.SEVEN_STALLION);

	private String descriptor;
	private PenisSize sizeTooSmall, maximumSizeComfortable, maximumSizeComfortableWithLube;

	private Capacity(String descriptor, PenisSize sizeTooSmall, PenisSize maximumSizeComfortable, PenisSize maximumSizeComfortableWithLube) {
		this.descriptor = descriptor;

		this.sizeTooSmall = sizeTooSmall;
		this.maximumSizeComfortable = maximumSizeComfortable;
		this.maximumSizeComfortableWithLube = maximumSizeComfortableWithLube;
	}

	public int getMinimumValue() {
		return maximumSizeComfortable.getMinimumValue();
	}

	public int getMaximumValue() {
		return maximumSizeComfortableWithLube.getMaximumValue();
	}

	public int getMedianValue() {
		return maximumSizeComfortable.getMinimumValue() + (maximumSizeComfortable.getMaximumValue() - maximumSizeComfortable.getMinimumValue()) / 2;
	}

	public static Capacity getCapacityFromValue(float capacity) {
		if (capacity <= ZERO_IMPENETRABLE.getMaximumValue())
			return ZERO_IMPENETRABLE;
		else if (capacity <= ONE_EXTREMELY_TIGHT.getMaximumValue())
			return ONE_EXTREMELY_TIGHT;
		else if (capacity <= TWO_TIGHT.getMaximumValue())
			return TWO_TIGHT;
		else if (capacity <= THREE_SLIGHTLY_LOOSE.getMaximumValue())
			return THREE_SLIGHTLY_LOOSE;
		else if (capacity <= FOUR_LOOSE.getMaximumValue())
			return FOUR_LOOSE;
		else if (capacity <= FIVE_ROOMY.getMaximumValue())
			return FIVE_ROOMY;
		else if (capacity <= SIX_STRETCHED_OPEN.getMaximumValue())
			return SIX_STRETCHED_OPEN;
		else
			return SEVEN_GAPING;
	}

	/**
	 * This should only really be used for calculating what size to stretch a
	 * demonic orifice to.
	 */
	public static Capacity getCapacityToFitPenis(PenisSize size) {
		if (size == PenisSize.ZERO_MICROSCOPIC)
			return ONE_EXTREMELY_TIGHT;
		else if (size == PenisSize.ONE_TINY)
			return TWO_TIGHT;
		else if (size == PenisSize.TWO_AVERAGE)
			return THREE_SLIGHTLY_LOOSE;
		else if (size == PenisSize.THREE_LARGE)
			return FOUR_LOOSE;
		else if (size == PenisSize.FOUR_HUGE)
			return FIVE_ROOMY;
		else if (size == PenisSize.FIVE_ENORMOUS)
			return SIX_STRETCHED_OPEN;
		else if (size == PenisSize.SIX_GIGANTIC)
			return SEVEN_GAPING;
		else
			return SEVEN_GAPING;
	}

	public static boolean isPenisSizeTooSmall(int capacity, int penisSize, boolean twoPenisesInVagina) {
		if ((twoPenisesInVagina ? penisSize * Penis.TWO_PENIS_SIZE_MULTIPLIER : penisSize) <= Capacity.getCapacityFromValue(capacity).getSizeTooSmall().getMaximumValue())
			return true;
		else
			return false;
	}

	public static boolean isPenisSizeTooBig(int capacity, int penisSize, boolean lubed, boolean twoPenises) {
		if ((lubed && (twoPenises ? penisSize * Penis.TWO_PENIS_SIZE_MULTIPLIER : penisSize) > Capacity.getCapacityFromValue(capacity).getMaximumSizeComfortableWithLube().getMaximumValue())
				|| (!lubed && (twoPenises ? penisSize * Penis.TWO_PENIS_SIZE_MULTIPLIER : penisSize) > Capacity.getCapacityFromValue(capacity).getMaximumSizeComfortable().getMaximumValue()))
			return true;
		else
			return false;
	}

	/**
	 * To fit into a sentence: "Your vagina is "+getDescriptor()+"." "Your "
	 * getDescriptor()+" asshole is stretched wide open."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public PenisSize getSizeTooSmall() {
		return sizeTooSmall;
	}

	public PenisSize getMaximumSizeComfortable() {
		return maximumSizeComfortable;
	}

	public PenisSize getMaximumSizeComfortableWithLube() {
		return maximumSizeComfortableWithLube;
	}

}
