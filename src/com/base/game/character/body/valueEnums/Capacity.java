package com.base.game.character.body.valueEnums;

import com.base.game.character.body.Penis;
import com.base.utils.Colour;

/**
 * Measured in inches of penis size that could fit comfortably within this capacity.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum Capacity {
	ZERO_IMPENETRABLE("extremely tight", PenisSize.NEGATIVE_UTILITY_VALUE, PenisSize.ZERO_MICROSCOPIC, PenisSize.ONE_TINY, Colour.GENERIC_SIZE_ONE),
	
	ONE_EXTREMELY_TIGHT("tight", PenisSize.ZERO_MICROSCOPIC, PenisSize.ONE_TINY, PenisSize.TWO_AVERAGE, Colour.GENERIC_SIZE_TWO),
	
	TWO_TIGHT("somewhat tight", PenisSize.ZERO_MICROSCOPIC, PenisSize.TWO_AVERAGE, PenisSize.THREE_LARGE, Colour.GENERIC_SIZE_THREE),
	
	THREE_SLIGHTLY_LOOSE("slightly loose", PenisSize.ONE_TINY, PenisSize.THREE_LARGE, PenisSize.FOUR_HUGE, Colour.GENERIC_SIZE_FOUR),
	
	FOUR_LOOSE("loose", PenisSize.ONE_TINY, PenisSize.FOUR_HUGE, PenisSize.FIVE_ENORMOUS, Colour.GENERIC_SIZE_FIVE),
	
	FIVE_ROOMY("very loose", PenisSize.TWO_AVERAGE, PenisSize.FIVE_ENORMOUS, PenisSize.SIX_GIGANTIC, Colour.GENERIC_SIZE_SIX),
	
	SIX_STRETCHED_OPEN("stretched open", PenisSize.TWO_AVERAGE, PenisSize.SIX_GIGANTIC, PenisSize.SEVEN_STALLION, Colour.GENERIC_SIZE_SEVEN),
	
	SEVEN_GAPING("gaping wide", PenisSize.THREE_LARGE, PenisSize.SEVEN_STALLION, PenisSize.SEVEN_STALLION, Colour.GENERIC_SIZE_EIGHT);

	
	private String descriptor;
	private PenisSize sizeTooSmall, maximumSizeComfortable, maximumSizeComfortableWithLube;
	private Colour colour;

	private Capacity(String descriptor, PenisSize sizeTooSmall, PenisSize maximumSizeComfortable, PenisSize maximumSizeComfortableWithLube, Colour colour) {
		this.descriptor = descriptor;

		this.sizeTooSmall = sizeTooSmall;
		this.maximumSizeComfortable = maximumSizeComfortable;
		this.maximumSizeComfortableWithLube = maximumSizeComfortableWithLube;
		this.colour=colour;
	}

	public int getMinimumValue() {
		return maximumSizeComfortable.getMinimumValue();
	}

	public int getMaximumValue() {
		return maximumSizeComfortable.getMaximumValue();
	}

	public int getMedianValue() {
		return maximumSizeComfortable.getMedianValue();//.getMinimumValue() + (maximumSizeComfortable.getMaximumValue() - maximumSizeComfortable.getMinimumValue()) / 2;
	}

	public static Capacity getCapacityFromValue(float capacity) {
		for(Capacity c : Capacity.values()) {
			if(capacity>=c.getMinimumValue() && capacity<c.getMaximumValue()) {
				return c;
			}
		}
		return SEVEN_GAPING;
	}

	/**
	 * This should only really be used for calculating what size to stretch a demonic orifice to.
	 */
	public static Capacity getCapacityToFitPenis(PenisSize size) {
		for(Capacity c : Capacity.values()) {
			if(size==c.getMaximumSizeComfortable()) {
				return c;
			}
		}
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

	public Colour getColour() {
		return colour;
	}

}
