package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;

/**
 * Measured in cm of penis size that could fit comfortably within this capacity.
 * 
 * @since 0.1.0
 * @version 0.3.5.5
 * @author Innoxia
 */
public enum Capacity {
	ZERO_IMPENETRABLE("extremely tight", PenisSize.ZERO_MICROSCOPIC, PenisSize.ONE_TINY, Colour.GENERIC_SIZE_ONE),
	
	ONE_EXTREMELY_TIGHT("tight", PenisSize.ONE_TINY, PenisSize.TWO_AVERAGE, Colour.GENERIC_SIZE_TWO),
	
	TWO_TIGHT("somewhat tight", PenisSize.TWO_AVERAGE, PenisSize.THREE_LARGE, Colour.GENERIC_SIZE_THREE),
	
	THREE_SLIGHTLY_LOOSE("slightly loose", PenisSize.THREE_LARGE, PenisSize.FOUR_HUGE, Colour.GENERIC_SIZE_FOUR),
	
	FOUR_LOOSE("loose", PenisSize.FOUR_HUGE, PenisSize.FIVE_ENORMOUS, Colour.GENERIC_SIZE_FIVE),
	
	FIVE_ROOMY("very loose", PenisSize.FIVE_ENORMOUS, PenisSize.SIX_GIGANTIC, Colour.GENERIC_SIZE_SIX) {
		@Override
		public String getDescriptor() {
			if(!Main.game.isGapeContentEnabled()) {
				return FOUR_LOOSE.getDescriptor();
			}
			return super.getDescriptor();
		}
		@Override
		public Colour getColour() {
			if(!Main.game.isGapeContentEnabled()) {
				return FOUR_LOOSE.getColour();
			}
			return super.getColour();
		}
	},
	
	SIX_STRETCHED_OPEN("stretched open", PenisSize.SIX_GIGANTIC, PenisSize.SEVEN_STALLION, Colour.GENERIC_SIZE_SEVEN) {
		@Override
		public String getDescriptor() {
			if(!Main.game.isGapeContentEnabled()) {
				return FOUR_LOOSE.getDescriptor();
			}
			return super.getDescriptor();
		}
		@Override
		public Colour getColour() {
			if(!Main.game.isGapeContentEnabled()) {
				return FOUR_LOOSE.getColour();
			}
			return super.getColour();
		}
	},
	
	SEVEN_GAPING("gaping wide", PenisSize.SEVEN_STALLION, PenisSize.SEVEN_STALLION, Colour.GENERIC_SIZE_EIGHT) {
		@Override
		public String getDescriptor() {
			if(!Main.game.isGapeContentEnabled()) {
				return FOUR_LOOSE.getDescriptor();
			}
			return super.getDescriptor();
		}
		@Override
		public Colour getColour() {
			if(!Main.game.isGapeContentEnabled()) {
				return FOUR_LOOSE.getColour();
			}
			return super.getColour();
		}
	};

	
	private String descriptor;
	private PenisSize maximumSizeComfortable;
	private PenisSize maximumSizeComfortableWithLube;
	private Colour colour;

	private Capacity(String descriptor, PenisSize maximumSizeComfortable, PenisSize maximumSizeComfortableWithLube, Colour colour) {
		this.descriptor = descriptor;

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
		return maximumSizeComfortable.getMedianValue();
	}

	public static Capacity getCapacityFromValue(float capacity) {
		for(Capacity c : Capacity.values()) {
			if(capacity>=c.getMinimumValue() && capacity<c.getMaximumValue()) {
				return c;
			}
		}
		return SEVEN_GAPING;
	}

	public static Capacity getCapacityToFitPenis(PenisSize size) {
		for(Capacity c : Capacity.values()) {
			if(size==c.getMaximumSizeComfortable()) {
				return c;
			}
		}
		return SEVEN_GAPING;
	}

	/**
	 * @param girth The girth of the penetrating object.
	 * @param penisSize The size of the penetrating object.
	 * @param twoPenetrationsInOrifice Whether there are two objects penetrating the orifice.
	 * @return The size of the penetrating object for use in stretch calculations.
	 */
	public static float calculateStretchSize(PenetrationGirth girth, int penisSize, boolean twoPenetrationsInOrifice) {
		float penisSizeWithGirth = ((float)penisSize)*girth.getOrificeStretchFactor();
		return Math.min(
				PenisSize.SEVEN_STALLION.getMaximumValue(),
				twoPenetrationsInOrifice
					? penisSizeWithGirth * Penis.TWO_PENIS_SIZE_MULTIPLIER
					: penisSizeWithGirth);
	}
	
	/**
	 * @param capacity The capacity of the orifice being penetrated.
	 * @param penisSize The size of the penis (or other penetrative object) being inserted.
	 * @param twoPenisesInVagina Pass in true if there are two penises in the orifice.
	 * @return true if the penis size is <=60% of the orifice capacity.
	 */
	public static boolean isPenisSizeTooSmall(float capacity, PenetrationGirth girth, int penisSize, boolean twoPenisesInVagina) {
		return calculateStretchSize(girth, penisSize, twoPenisesInVagina) <= capacity*0.6f;
	}

	/**
	 * @param elasticity The elasticity of the orifice being penetrated. A higher elasticity is more tolerable of bigger penetrations.
	 * @param capacity The capacity of the orifice being penetrated.
	 * @param penisSize The size of the penis (or other penetrative object) being inserted.
	 * @param lubed Whether the orifice is lubricated or not. If lubricated, the orifice is able to take bigger penetrations.
	 * @param twoPenisesInVagina Pass in true if there are two penises in the orifice.
	 * @return true if the penis size (factoring in girth) is larger than the capacity can handle.
	 */
	public static boolean isPenisSizeTooBig(OrificeElasticity elasticity, float capacity, PenetrationGirth girth, int penisSize, boolean lubed, boolean twoPenises) {
		float penisSizeUsed = calculateStretchSize(girth, penisSize, twoPenises);
		
		float tolerance = elasticity.getSizeTolerancePercentage();
		if(tolerance>0 && lubed) {
			tolerance+=0.1f; // Add 10% tolerance if lubed
		}
		tolerance+=1.01; // Base of 1%
//		System.out.println("Capacity check: "+penisSizeUsed+" | "+(capacity*tolerance));
		return penisSizeUsed > capacity*tolerance;
		
//		return (lubed && penisSizeUsed > Capacity.getCapacityFromValue(capacity).getMaximumSizeComfortableWithLube().getMaximumValue())
//				|| (!lubed && penisSizeUsed > Capacity.getCapacityFromValue(capacity).getMaximumSizeComfortable().getMaximumValue());
	}

	/**
	 * To fit into a sentence:
	 * <br/>"Your vagina is "+getDescriptor()+"."
	 * <br/>"Your "+getDescriptor()+" asshole is stretched wide open."
	 */
	public String getDescriptor() {
		return descriptor;
	}
	
	/**
	 * A coloured version of getDescriptor().
	 */
	public String getDescriptor(boolean coloured) {
		if(coloured) {
			return "<span style='color:"+this.getColour().toWebHexString()+";'>"+getDescriptor()+"</span>";
		}
		return getDescriptor();
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
