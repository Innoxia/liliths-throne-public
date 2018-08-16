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
public enum HungerLevel {

	ZERO_SATIATED("satiated", 0, 5, 0.5f, Colour.HUNGER_STAGE_ZERO) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.HUNGER_PERK_0;
		}
	},

	ONE_FED("fed", 5, 20, 0.75f, Colour.HUNGER_STAGE_ONE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.HUNGER_PERK_1;
		}
	},

	TWO_NOURISHED("nourished", 20, 50, 1f, Colour.HUNGER_STAGE_TWO) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.HUNGER_PERK_2;
		}
	},

	THREE_HUNGRY("hungry", 50, 70, 1.25f, Colour.HUNGER_STAGE_THREE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.HUNGER_PERK_3;
		}
	},

	FOUR_STARVING("starving", 70, 90, 1.5f, Colour.HUNGER_STAGE_FOUR) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.HUNGER_PERK_4;
		}
	},

	FIVE_FAMINED("famined", 90, 100, 1.5f, Colour.HUNGER_STAGE_FIVE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.HUNGER_PERK_5;
		}
	};


	private String name;
	private int minimumValue, maximumValue;
	private float hungerModifier;
	private Colour colour;

	private HungerLevel(String name, int minimumValue, int maximumValue, float hungerModifier, Colour colour) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.hungerModifier = hungerModifier;
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

	public float getHungerModifier() {
		return hungerModifier;
	}

	public Colour getColour() {
		return colour;
	}

	public static HungerLevel getHungerLevelFromValue(float value){
		for(HungerLevel al : HungerLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue()) {
				return al;
			}
		}
		return FIVE_FAMINED;
	}

	public String getStatusEffectDescription(boolean consensual, GameCharacter character) {
		    switch(this) {
                        case ZERO_SATIATED:
				    if (character.isPlayer()) {
					    return "You currently are not hungry at all.";
				    } else {
					    return UtilText.parse(character, "[npc.Name] isn't hungry at all.");
				    }
                            case ONE_FED:
				    if (character.isPlayer()) {
					    return "You recently ate something, you are not yet hungry.";
				    } else {
					    return UtilText.parse(character, "[npc.Name] recently ate something, [npc.she] doesn't need to eat anything yet.");
				    }
			    case TWO_NOURISHED:
				    if (character.isPlayer()) {
					    return "It's been some time ago that you ate something, but you are not hungry yet.";
				    } else {
					    return UtilText.parse(character, "[npc.Name] wouldn't refuse some food, but [npc.she] isn't hungry yet.");
				    }
			    case THREE_HUNGRY:
				    if (character.isPlayer()) {
					    return "You feel the hunger rising. You could use some food.";
				    } else {
					    return UtilText.parse(character, "[npc.Name] could use some food.");
				    }
			    case FOUR_STARVING:
				    if (character.isPlayer()) {
					    return "You are famined, you really could use some food!";
				    } else {
					    return UtilText.parse(character, "[npc.Name] is really hungry, [npc.she] is dying for something to eat!");
				    }
			    case FIVE_FAMINED:
				    if (character.isPlayer()) {
					    return "You're seriously famined, you're feeling weak and are dying for some food, anything will do!";
				    } else {
					    return UtilText.parse(character, "[npc.Name] is famined! [npc.She]'s dying for some food, anything will do!");
				    }
		    }
		return "";

	}
}
