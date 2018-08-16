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
public enum BladderLevel {

	ZERO_JUST_PEED("drained", 0, 5, 0.5f, Colour.BLADDER_STAGE_ZERO) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.BLADDER_PERK_0;
		}
	},

	ONE_RECENTLY_PEED("needless", 5, 20, 0.75f, Colour.BLADDER_STAGE_ONE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.BLADDER_PERK_1;
		}
	},

	TWO_PEED_A_WHILE_AGO("adjusted", 20, 50, 1f, Colour.BLADDER_STAGE_TWO) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.BLADDER_PERK_2;
		}
	},

	THREE_MAY_WANT_TO_GO("predisposed", 50, 70, 1.25f, Colour.BLADDER_STAGE_THREE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.BLADDER_PERK_3;
		}
	},

	FOUR_NEED_TO_GO("urged", 70, 90, 1.5f, Colour.BLADDER_STAGE_FOUR) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.BLADDER_PERK_4;
		}
	},

	FIVE_BARELY_HOLD_IT("bursting", 90, 100, 1.5f, Colour.BLADDER_STAGE_FIVE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.BLADDER_PERK_5;
		}
	};


	private String name;
	private int minimumValue, maximumValue;
	private float bladderModifier;
	private Colour colour;

	private BladderLevel(String name, int minimumValue, int maximumValue, float bladderModifier, Colour colour) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.bladderModifier = bladderModifier;
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

	public float getBladderModifier() {
		return bladderModifier;
	}

	public Colour getColour() {
		return colour;
	}

	public static BladderLevel getBladderLevelFromValue(float value){
		for(BladderLevel al : BladderLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue()) {
				return al;
			}
		}
		return FIVE_BARELY_HOLD_IT;
	}

	public String getStatusEffectDescription(boolean consensual, GameCharacter character) {
		    switch(this) {
			    case ZERO_JUST_PEED:
				    if (character.isPlayer()) {
					    return "You currently feel no need at all to go to the john.";
				    } else {
					    return UtilText.parse(character, "[npc.Name] has no need to go to the john at all.");
				    }
			    case ONE_RECENTLY_PEED:
				    if (character.isPlayer()) {
					    return "You recently went to the john, you don't feel any need to go.";
				    } else {
					    return UtilText.parse(character, "[npc.Name] recently went to the john, [npc.she] doesn't feel the need to go.");
				    }
			    case TWO_PEED_A_WHILE_AGO:
				    if (character.isPlayer()) {
					    return "It's been some time ago that you peed. You could go, but there's no urgency yet.";
				    } else {
					    return UtilText.parse(character, "[npc.Name] could go to the john, but there's no urgency yet.");
				    }
			    case THREE_MAY_WANT_TO_GO:
				    if (character.isPlayer()) {
					    return "You feel the urgency to go rising. Better go when you're able to, just to be sure.";
				    } else {
					    return UtilText.parse(character, "[npc.Name] could go to the john just now.");
				    }
			    case FOUR_NEED_TO_GO:
				    if (character.isPlayer()) {
					    return "Oh no, you need to go to the john urgently! Is there a toilet anywhere near?";
				    } else {
					    return UtilText.parse(character, "[npc.Name] really needs to go to the john! Is there a toilet anywhere nearby?");
				    }
			    case FIVE_BARELY_HOLD_IT:
				    if (character.isPlayer()) {
						    return "You cannot hold it any longer! You'll just have to do it here, or you'll have an accident!";
					    } else {
					    return UtilText.parse(character, "[npc.Name] cannot hold it any longer! [npc.She]'ll just have to do it here, or there will be an accident!");
				    }
		    }
		return "";

	}
}
