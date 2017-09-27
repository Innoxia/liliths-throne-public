package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.65
 * @version 0.1.79
 * @author Innoxia
 */
public enum IntelligenceLevel {
	
	ZERO_AIRHEAD("airhead", 0, 5, Colour.INTELLIGENCE_STAGE_ZERO) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.INTELLIGENCE_PERK_0;
		}
	},
	
	ONE_AVERAGE("average", 5, 15, Colour.INTELLIGENCE_STAGE_ONE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.INTELLIGENCE_PERK_1;
		}
	},
	
	TWO_SMART("smart", 15, 35, Colour.INTELLIGENCE_STAGE_TWO) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.INTELLIGENCE_PERK_2;
		}
	},
	
	THREE_BRAINY("brainy", 35, 65, Colour.INTELLIGENCE_STAGE_THREE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.INTELLIGENCE_PERK_3;
		}
	},
	
	FOUR_GENIUS("genius", 65, 95, Colour.INTELLIGENCE_STAGE_FOUR) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.INTELLIGENCE_PERK_4;
		}
	},
	
	FIVE_POLYMATH("polymath", 95, 100, Colour.INTELLIGENCE_STAGE_FIVE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.INTELLIGENCE_PERK_5;
		}
	};
	
	private String name;
	private int minimumValue, maximumValue;
	private Colour colour;

	private IntelligenceLevel(String name, int minimumValue, int maximumValue, Colour colour) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
	}
	
	public abstract StatusEffect getRelatedStatusEffect();

	public String getName() {
		return name;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}

	public Colour getColour() {
		return colour;
	}
	
	public static IntelligenceLevel getIntelligenceLevelFromValue(float value){
		for(IntelligenceLevel al : IntelligenceLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return FIVE_POLYMATH;
	}

}
