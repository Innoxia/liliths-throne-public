package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.65
 * @version 0.1.79
 * @author Innoxia
 */
public enum PhysiqueLevel {
	
	ZERO_WEAK("frail", 0, 5, PresetColour.PHYSIQUE_STAGE_ZERO) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.PHYSIQUE_PERK_0;
		}
	},
	
	ONE_AVERAGE("average", 5, 15, PresetColour.PHYSIQUE_STAGE_ONE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.PHYSIQUE_PERK_1;
		}
	},
	
	TWO_STRONG("fit", 15, 35, PresetColour.PHYSIQUE_STAGE_TWO) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.PHYSIQUE_PERK_2;
		}
	},
	
	THREE_POWERFUL("powerful", 35, 65, PresetColour.PHYSIQUE_STAGE_THREE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.PHYSIQUE_PERK_3;
		}
	},
	
	FOUR_MIGHTY("mighty", 65, 95, PresetColour.PHYSIQUE_STAGE_FOUR) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.PHYSIQUE_PERK_4;
		}
	},
	
	FIVE_HERCULEAN("herculean", 95, 100, PresetColour.PHYSIQUE_STAGE_FIVE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.PHYSIQUE_PERK_5;
		}
	};
	
	private String name;
	private int minimumValue, maximumValue;
	private Colour colour;

	private PhysiqueLevel(String name, int minimumValue, int maximumValue, Colour colour) {
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
	
	public static PhysiqueLevel getPhysiqueLevelFromValue(float value){
		for(PhysiqueLevel al : PhysiqueLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return FIVE_HERCULEAN;
	}
}
