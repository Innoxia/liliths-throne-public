package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Measured in cm of <b>diameter</b> of a penetrative object which could fit comfortably within an orifice.
 * 
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public enum Capacity {
	
	ZERO_IMPENETRABLE("extremely tight", "tight", 0f, 1f, PresetColour.GENERIC_SIZE_ONE, false),
	
	ONE_EXTREMELY_TIGHT("tight", "tight", 1f, 2f, PresetColour.GENERIC_SIZE_TWO, false),
	
	TWO_TIGHT("somewhat tight", "tight", 2f, 4f, PresetColour.GENERIC_SIZE_THREE, false),
	
	THREE_SLIGHTLY_LOOSE("slightly loose", "loose", 4f, 6f, PresetColour.GENERIC_SIZE_FOUR, false),
	
	FOUR_LOOSE("loose", "loose", 6f, 9f, PresetColour.GENERIC_SIZE_FIVE, false),
	
	FIVE_ROOMY("very loose", "loose", 9f, 12f, PresetColour.GENERIC_SIZE_SIX, true) {
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
	
	SIX_STRETCHED_OPEN("stretched open", "gaping", 12f, 16f, PresetColour.GENERIC_SIZE_SEVEN, true) {
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
	
	SEVEN_GAPING("gaping wide", "gaping", 16f, 25f, PresetColour.GENERIC_SIZE_EIGHT, true) {
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
	private String speechDescriptor;
	private float minimumSizeComfortable;
	private float maximumSizeComfortable;
	private Colour colour;
	private boolean gapeContentRestricted;

	private Capacity(String descriptor, String speechDescriptor, float minimumSizeComfortable, float maximumSizeComfortable, Colour colour, boolean gapeContentRestricted) {
		this.descriptor = descriptor;
		this.speechDescriptor = speechDescriptor;

		this.minimumSizeComfortable = minimumSizeComfortable;
		this.maximumSizeComfortable = maximumSizeComfortable;
		
		this.colour=colour;
		this.gapeContentRestricted = gapeContentRestricted;
	}

	/** @return The minimum diameter that this Capacity can handle without it feeling too small. */
	public float getMinimumValue() {
		return minimumSizeComfortable;
	}

	/** @return The maximum diameter that this Capacity can handle without it feeling too big. */
	public float getMaximumValue(boolean withLube) {
		return maximumSizeComfortable * (withLube?1.05f:1);
	}

	/** @return The median diameter that this Capacity can handle without it feeling too big or small. */
	public float getMedianValue() {
		return (minimumSizeComfortable + maximumSizeComfortable)/2f;
	}

	public static Capacity getCapacityFromValue(float capacity) {
		for(Capacity c : Capacity.values()) {
			if(capacity>=c.getMinimumValue() && capacity<c.getMaximumValue(false)) {
				return c;
			}
		}
		return SEVEN_GAPING;
	}

	/**
	 * @param orificeModifiers The modifiers of the orifice being penetrated.
	 * @param capacity The capacity of the orifice being penetrated.
	 * @param diameter The total diameter of the penis(es) (or other penetrative object(s)) being inserted.
	 * @return true if the diameter is <=60% of the orifice capacity and the orificeModifiers does not contain MUSCLE_CONTROL.
	 */
	public static boolean isPenetrationDiameterTooSmall(Set<OrificeModifier> orificeModifiers, float capacity, float diameter) {
		return !orificeModifiers.contains(OrificeModifier.MUSCLE_CONTROL)
				&& diameter <= capacity*0.6f;
	}

	/**
	 * @param elasticity The elasticity of the orifice being penetrated. A higher elasticity is more tolerable of bigger penetrations.
	 * @param capacity The capacity of the orifice being penetrated.
	 * @param diameter The total diameter of the penis(es) (or other penetrative object(s)) being inserted.
	 * @param lubed Whether the orifice is lubricated or not. If lubricated, the orifice is able to take bigger penetrations.
	 * @return true if the penis size (factoring in girth) is larger than the capacity can handle.
	 */
	public static boolean isPenetrationDiameterTooBig(OrificeElasticity elasticity, float capacity, float diameter, boolean lubed) {
		float maximumComfortableDiameter = getMaximumComfortableDiameter(elasticity, capacity, lubed);
		
		return diameter > maximumComfortableDiameter;
	}
	
	public static float getMaximumComfortableDiameter(OrificeElasticity elasticity, float capacity, boolean lubed) {
		float tolerance = 1.01f + elasticity.getSizeTolerancePercentage(); // Base of 1%
		
		if(lubed) {
			tolerance+=0.1f; // Add 10% tolerance if lubed
		}
		
		return capacity*tolerance;
	}

	/**
	 * To fit into a sentence:
	 * <br/>"Your vagina is "+getDescriptor()+"."
	 * <br/>"Your "+getDescriptor()+" asshole is stretched wide open."
	 * @return A descriptor of this capacity. A simpler descriptor is returned if the game is parsing speech.
	 */
	public String getDescriptor() {
		if(UtilText.isInSpeech()) {
			return speechDescriptor;
		}
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

	public Colour getColour() {
		return colour;
	}
	
	public static List<Capacity> getCapacityListFromPreferences() {
		List<Capacity> capacities = new ArrayList<>();

		for(Capacity value : Capacity.values()) {
			if(!value.gapeContentRestricted || Main.game.isGapeContentEnabled()) {
				capacities.add(value);
			}
		}
		
		return capacities;
	}

}
