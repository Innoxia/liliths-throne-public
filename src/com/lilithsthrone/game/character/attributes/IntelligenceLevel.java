package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.65
 * @version 0.3.8.2
 * @author Innoxia
 */
public enum IntelligenceLevel {
	
	ZERO_AIRHEAD("arcane impotence", 0, 10, PresetColour.INTELLIGENCE_STAGE_ZERO) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			if(Main.game.isInNewWorld()) {
				return StatusEffect.INTELLIGENCE_PERK_0;
			} else {
				return StatusEffect.INTELLIGENCE_PERK_0_OLD_WORLD;
			}
		}
	},
	
	ONE_AVERAGE("arcane potential", 10, 30, PresetColour.INTELLIGENCE_STAGE_ONE) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.INTELLIGENCE_PERK_1;
		}
	},
	
	TWO_SMART("arcane proficiency", 30, 50, PresetColour.INTELLIGENCE_STAGE_TWO) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.INTELLIGENCE_PERK_2;
		}
	},
	
	THREE_BRAINY("arcane prowess", 50, 70, PresetColour.INTELLIGENCE_STAGE_THREE) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.INTELLIGENCE_PERK_3;
		}
	},
	
	FOUR_GENIUS("arcane mastery", 70, 90, PresetColour.INTELLIGENCE_STAGE_FOUR) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.INTELLIGENCE_PERK_4;
		}
	},
	
	FIVE_POLYMATH("arcane brilliance", 90, 100, PresetColour.INTELLIGENCE_STAGE_FIVE) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
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
	
	public abstract AbstractStatusEffect getRelatedStatusEffect();

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
