package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.65
 * @version 0.1.79
 * @author Innoxia
 */
public enum ArousalLevel {

	ZERO_NONE("none", 0, 10, Colour.AROUSAL_STAGE_ZERO, false) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.AROUSAL_PERK_0;
		}
	},

	ONE_TURNED_ON("turned on", 10, 25, Colour.AROUSAL_STAGE_ONE, false) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.AROUSAL_PERK_1;
		}
	},

	TWO_EXCITED("excited", 25, 50, Colour.AROUSAL_STAGE_TWO, false) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.AROUSAL_PERK_2;
		}
	},

	THREE_HEATED("heated", 50, 80, Colour.AROUSAL_STAGE_THREE, false) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.AROUSAL_PERK_3;
		}
	},

	FOUR_PASSIONATE("passionate", 80, 95, Colour.AROUSAL_STAGE_FOUR, true) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.AROUSAL_PERK_4;
		}
	},

	FIVE_ORGASM_IMMINENT("imminent orgasm", 95, 100, Colour.AROUSAL_STAGE_FIVE, true) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.AROUSAL_PERK_5;
		}
	};
	
	
	private String name;
	private int minimumValue, maximumValue;
	private Colour colour;
	private boolean mutualOrgasm;

	private ArousalLevel(String name, int minimumValue, int maximumValue, Colour colour, boolean mutualOrgasm) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
		this.mutualOrgasm=mutualOrgasm;
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
	
	public int getMedianValue() {
		return (minimumValue + maximumValue) / 2;
	}

	public Colour getColour() {
		return colour;
	}
	
	public boolean isMutualOrgasm() {
		return mutualOrgasm;
	}

	public static ArousalLevel getArousalLevelFromValue(float value){
		for(ArousalLevel al : ArousalLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue()) {
				return al;
			}
		}
		return FIVE_ORGASM_IMMINENT;
	}

}
