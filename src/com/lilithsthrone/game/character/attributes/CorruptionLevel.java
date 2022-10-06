package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.65
 * @version 0.3.8.2
 * @author Innoxia
 */
public enum CorruptionLevel {

	/**A character at this level: Will not want any sexual relations with anyone but the person they love.*/
	ZERO_PURE("pure", 0, 10, PresetColour.CORRUPTION_STAGE_ZERO, 0) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.CORRUPTION_PERK_0;
		}

		@Override
		public boolean isAbleToPerformCorruptiveAction(CorruptionLevel action) {
			switch(action){
				case ZERO_PURE:
				case ONE_VANILLA:
					return true;
				case TWO_HORNY:
				case THREE_DIRTY:
				case FOUR_LUSTFUL:
				case FIVE_CORRUPT:
					return false;
			}
			return false;
		}
	},
	/**A character at this level: Will be open to the idea of having multiple sexual partners, and will be willing to have sex with defeated enemies. Their in-sex options will be limited, however, to quite vanilla acts.*/
	ONE_VANILLA("vanilla", 10, 30, PresetColour.CORRUPTION_STAGE_ONE, 0.1f) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.CORRUPTION_PERK_1;
		}

		@Override
		public boolean isAbleToPerformCorruptiveAction(CorruptionLevel action) {
			switch(action){
				case ZERO_PURE:
				case ONE_VANILLA:
				case TWO_HORNY:
					return true;
				case THREE_DIRTY:
				case FOUR_LUSTFUL:
				case FIVE_CORRUPT:
					return false;
			}
			return false;
		}
	},
	/**A character at this level: Will have sex with pretty much anything, but won't do anything too crazy.*/
	TWO_HORNY("horny", 30, 50, PresetColour.CORRUPTION_STAGE_TWO, 0.25f) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.CORRUPTION_PERK_2;
		}

		@Override
		public boolean isAbleToPerformCorruptiveAction(CorruptionLevel action) {
			switch(action){
				case ZERO_PURE:
				case ONE_VANILLA:
				case TWO_HORNY:
				case THREE_DIRTY:
					return true;
				case FOUR_LUSTFUL:
				case FIVE_CORRUPT:
					return false;
			}
			return false;
		}
	},
	/**A character at this level: This is where the character starts to really lose themselves to corruption. Rough sex.*/
	THREE_DIRTY("dirty", 50, 70, PresetColour.CORRUPTION_STAGE_THREE, 0.5f) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.CORRUPTION_PERK_3;
		}

		@Override
		public boolean isAbleToPerformCorruptiveAction(CorruptionLevel action) {
			switch(action){
				case ZERO_PURE:
				case ONE_VANILLA:
				case TWO_HORNY:
				case THREE_DIRTY:
				case FOUR_LUSTFUL:
					return true;
				case FIVE_CORRUPT:
					return false;
			}
			return false;
		}
	},
	/**A character at this level: A complete sex fiend. Will do anything, with anyone, anywhere and anyhow.*/
	FOUR_LUSTFUL("lustful", 70, 90, PresetColour.CORRUPTION_STAGE_FOUR, 1f) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.CORRUPTION_PERK_4;
		}

		@Override
		public boolean isAbleToPerformCorruptiveAction(CorruptionLevel action) {
			return true;
		}
	},
	/**A character at this level: Is completely lost to corruption.*/
	FIVE_CORRUPT("corrupt", 90, 100, PresetColour.CORRUPTION_STAGE_FIVE, 2f) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.CORRUPTION_PERK_5;
		}

		@Override
		public boolean isAbleToPerformCorruptiveAction(CorruptionLevel action) {
			return true;
		}
	};
	
	private String name;
	private int minimumValue, maximumValue;
	private Colour colour;
	private float corruptionBypass;

	private CorruptionLevel(String name, int minimumValue, int maximumValue, Colour colour, float corruptionBypass) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
		this.corruptionBypass=corruptionBypass;
	}
	
	public abstract AbstractStatusEffect getRelatedStatusEffect();
	
	public abstract boolean isAbleToPerformCorruptiveAction(CorruptionLevel action);

	public String getName() {
		return name;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMedianValue() {
		return (minimumValue + maximumValue) / 2;
	}

	public int getMaximumValue() {
		return maximumValue;
	}

	public Colour getColour() {
		return colour;
	}
	
	public float getCorruptionBypass() {
		return corruptionBypass;
	}
	
	public static CorruptionLevel getCorruptionLevelFromValue(float value){
		for(CorruptionLevel al : CorruptionLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return FIVE_CORRUPT;
	}
}
