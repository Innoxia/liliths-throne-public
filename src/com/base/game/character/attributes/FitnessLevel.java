package com.base.game.character.attributes;

import com.base.game.character.effects.StatusEffect;
import com.base.utils.Colour;

/**
 * @since 0.1.65
 * @version 0.1.79
 * @author Innoxia
 */
public enum FitnessLevel {
	
	ZERO_KLUTZ("klutz", 0, 5, Colour.FITNESS_STAGE_ZERO) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.FITNESS_PERK_0;
		}
	},
	
	ONE_AVERAGE("average", 5, 15, Colour.FITNESS_STAGE_ONE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.FITNESS_PERK_1;
		}
	},
	
	TWO_FLEXIBLE("flexible", 15, 35, Colour.FITNESS_STAGE_TWO) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.FITNESS_PERK_2;
		}
	},
	
	THREE_LIMBER("limber", 35, 65, Colour.FITNESS_STAGE_THREE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.FITNESS_PERK_3;
		}
	},
	
	FOUR_ATHLETIC("athletic", 65, 95, Colour.FITNESS_STAGE_FOUR) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.FITNESS_PERK_4;
		}
	},
	
	FIVE_ACROBAT("acrobat", 95, 100, Colour.FITNESS_STAGE_FIVE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.FITNESS_PERK_5;
		}
	};
	
	private String name;
	private int minimumValue, maximumValue;
	private Colour colour;

	private FitnessLevel(String name, int minimumValue, int maximumValue, Colour colour) {
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
	
	public static FitnessLevel getFitnessLevelFromValue(float value){
		for(FitnessLevel al : FitnessLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return FIVE_ACROBAT;
	}

}
