package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.StatusEffect;

import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.0
 * @version 0.0.1
 * @author Picobyte
 */
public enum ThirstLevel {

	ZERO_QUENCHED("quenched", 0, 5, 0.5f, Colour.THIRST_STAGE_ZERO) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.THIRST_PERK_0;
		}
	},

	ONE_SLAKED("slaked", 5, 20, 0.75f, Colour.THIRST_STAGE_ONE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.THIRST_PERK_1;
		}
	},

	TWO_FRESHENED("freshened", 20, 50, 1f, Colour.THIRST_STAGE_TWO) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.THIRST_PERK_2;
		}
	},

	THREE_THIRSTING("thirsting", 50, 70, 1.25f, Colour.THIRST_STAGE_THREE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.THIRST_PERK_3;
		}
	},

	FOUR_THIRSTY("thirsty", 70, 90, 1.5f, Colour.THIRST_STAGE_FOUR) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.THIRST_PERK_4;
		}
	},

	FIVE_DEHYDRATED("dehydrated", 90, 100, 1.5f, Colour.THIRST_STAGE_FIVE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.THIRST_PERK_5;
		}
	};


	private String name;
	private int minimumValue, maximumValue;
	private float thirstModifier;
	private Colour colour;

	private ThirstLevel(String name, int minimumValue, int maximumValue, float thirstModifier, Colour colour) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.thirstModifier = thirstModifier;
		this.colour = colour;
	}

	public abstract StatusEffect getRelatedStatusEffect();

	public String getName() {
		return name;
	}
	public String getCName() {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
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

	public float getThirstModifier() {
		return thirstModifier;
	}

	public Colour getColour() {
		return colour;
	}

	public static ThirstLevel getThirstLevelFromValue(float value){
		for(ThirstLevel al : ThirstLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue()) {
				return al;
			}
		}
		return FIVE_DEHYDRATED;
	}

	public String getStatusEffectDescription(boolean consensual, GameCharacter character) {
		    switch(this) {
                        case ZERO_QUENCHED:
				    if (character.isPlayer()) {
					    return "You currently are not thirsty at all.";
				    } else {
					    return UtilText.parse(character, "[npc.Name] isn't thirsty at all.");
				    }
                            case ONE_SLAKED:
				    if (character.isPlayer()) {
					    return "You recently drank something, you are not yet thirsty.";
				    } else {
					    return UtilText.parse(character, "[npc.Name] recently drank something, [npc.she] doesn't need to drink anything yet.");
				    }
			    case TWO_FRESHENED:
				    if (character.isPlayer()) {
					    return "It's been some time ago that you drank something, but you are not thirsty yet.";
				    } else {
					    return UtilText.parse(character, "[npc.Name] wouldn't refuse a drink, but [npc.she] isn't thirsty yet.");
				    }
			    case THREE_THIRSTING:
				    if (character.isPlayer()) {
					    return "You feel the thirst rising. You could use a drink.";
				    } else {
					    return UtilText.parse(character, "[npc.Name] could use a drink.");
				    }
			    case FOUR_THIRSTY:
				    if (character.isPlayer()) {
					    return "You are parch dry, you'd really could use a drink!";
				    } else {
					    return UtilText.parse(character, "[npc.Name] is really thirsty, [npc.she] is dying for a drink!");
				    }
			    case FIVE_DEHYDRATED:
				    if (character.isPlayer()) {
					    return "You're seriously dehydrated, you're feeling weak and are dying for a drink, anything will do!";
				    } else {
					    return UtilText.parse(character, "[npc.Name] is almost bone-dry! [npc.She]'s dying for a drink, anything will do!");
				    }
		    }
		return "";

	}
}
