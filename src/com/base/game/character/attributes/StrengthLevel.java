package com.base.game.character.attributes;

import com.base.game.character.effects.StatusEffect;
import com.base.utils.Colour;

/**
 * @since 0.1.65
 * @version 0.1.79
 * @author Innoxia
 */
public enum StrengthLevel {
	
	ZERO_WEAK("weak", 0, 5, Colour.STRENGTH_STAGE_ZERO) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.STRENGTH_PERK_0;
		}
	},
	
	ONE_AVERAGE("average", 5, 15, Colour.STRENGTH_STAGE_ONE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.STRENGTH_PERK_1;
		}
	},
	
	TWO_STRONG("strong", 15, 35, Colour.STRENGTH_STAGE_TWO) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.STRENGTH_PERK_2;
		}
	},
	
	THREE_POWERFUL("powerful", 35, 65, Colour.STRENGTH_STAGE_THREE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.STRENGTH_PERK_3;
		}
	},
	
	FOUR_MIGHTY("mighty", 65, 95, Colour.STRENGTH_STAGE_FOUR) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.STRENGTH_PERK_4;
		}
	},
	
	FIVE_HERCULEAN("herculean", 95, 100, Colour.STRENGTH_STAGE_FIVE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.STRENGTH_PERK_5;
		}
	};
	
	private String name;
	private int minimumValue, maximumValue;
	private Colour colour;

	private StrengthLevel(String name, int minimumValue, int maximumValue, Colour colour) {
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
	
	public static StrengthLevel getStrengthLevelFromValue(float value){
		for(StrengthLevel al : StrengthLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return FIVE_HERCULEAN;
	}
}
