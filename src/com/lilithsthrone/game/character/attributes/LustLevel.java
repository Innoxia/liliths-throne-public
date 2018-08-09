package com.lilithsthrone.game.character.attributes;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.97
 * @version 0.2.0
 * @author Innoxia
 */
public enum LustLevel {

	ZERO_COLD("cold", 0, 5, 0.5f, Colour.LUST_STAGE_ZERO, SexPace.SUB_RESISTING, SexPace.DOM_GENTLE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_0;
		}
	},

	ONE_HORNY("horny", 5, 20, 0.75f, Colour.LUST_STAGE_ONE, SexPace.SUB_NORMAL, SexPace.DOM_NORMAL) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_1;
		}
	},

	TWO_AMOROUS("sensual", 20, 50, 1f, Colour.LUST_STAGE_TWO, SexPace.SUB_NORMAL, SexPace.DOM_NORMAL) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_2;
		}
	},

	THREE_LUSTFUL("amorous", 50, 80, 1.25f, Colour.LUST_STAGE_THREE, SexPace.SUB_NORMAL, SexPace.DOM_NORMAL) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_3;
		}
	},

	FOUR_IMPASSIONED("lustful", 80, 95, 1.5f, Colour.LUST_STAGE_FOUR, SexPace.SUB_EAGER, SexPace.DOM_ROUGH) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_4;
		}
	},
	
	FIVE_BURNING("impassioned", 95, 100, 1.5f, Colour.LUST_STAGE_FIVE, SexPace.SUB_EAGER, SexPace.DOM_ROUGH) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_5;
		}
	};
	
	
	private String name;
	private int minimumValue, maximumValue;
	private float arousalModifier;
	private Colour colour;
	private SexPace sexPaceSubmissive;
	private SexPace sexPaceDominant;

	private LustLevel(String name, int minimumValue, int maximumValue, float arousalModifier, Colour colour, SexPace sexPaceSubmissive, SexPace sexPaceDominant) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.arousalModifier = arousalModifier;
		this.colour = colour;
		this.sexPaceSubmissive = sexPaceSubmissive;
		this.sexPaceDominant = sexPaceDominant;
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
	
	public float getArousalModifier() {
		return arousalModifier;
	}

	public Colour getColour() {
		return colour;
	}

	public static LustLevel getLustLevelFromValue(float value){
		for(LustLevel al : LustLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue()) {
				return al;
			}
		}
		return FIVE_BURNING;
	}


	public SexPace getSexPaceSubmissive() {
		return sexPaceSubmissive;
	}

	public SexPace getSexPaceDominant() {
		return sexPaceDominant;
	}
	
	public SexPace getSexPace(boolean consensual, GameCharacter character) {
		SexPace pace;
		if(Sex.isDom(character)) {
			if((character.hasFetish(Fetish.FETISH_SUBMISSIVE) && !character.hasFetish(Fetish.FETISH_SADIST) && !character.hasFetish(Fetish.FETISH_DOMINANT))
					|| character.getFetishDesire(Fetish.FETISH_SADIST) == FetishDesire.ZERO_HATE) {
				pace = SexPace.DOM_GENTLE;
				
			} else if(character.getFetishDesire(Fetish.FETISH_SADIST) == FetishDesire.ONE_DISLIKE) {
				pace = SexPace.DOM_NORMAL;
				
			} else if(character.hasFetish(Fetish.FETISH_SADIST)) {
				pace = SexPace.DOM_ROUGH;
				
			} else {
				pace = getSexPaceDominant();
			}
			
		} else {
			pace = getSexPaceSubmissive();
			if(character.hasFetish(Fetish.FETISH_NON_CON_SUB) || ((character instanceof NPC) && ((NPC)character).hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer))) {
				pace = SexPace.SUB_RESISTING;
			}
		}
		
		if(pace==SexPace.SUB_RESISTING && !Main.getProperties().hasValue(PropertyValue.nonConContent)) {
			pace = SexPace.SUB_NORMAL;
		}
		
		if(pace==SexPace.DOM_ROUGH
				&& ((!character.hasFetish(Fetish.FETISH_DOMINANT) && !character.hasFetish(Fetish.FETISH_SADIST) && !character.hasFetish(Fetish.FETISH_NON_CON_DOM))
						|| (character.getFetishDesire(Fetish.FETISH_SADIST) == FetishDesire.ONE_DISLIKE
							|| character.getFetishDesire(Fetish.FETISH_SADIST) == FetishDesire.ZERO_HATE))) {
			pace = SexPace.DOM_NORMAL;
		}
		
		return pace;
	}
	
	public List<String> getStatusEffectModifierDescription(boolean consensual, GameCharacter character) {
		List<String> modifiersList = new ArrayList<>();
		
		if(Main.game.isInSex()) {
			switch(this.getSexPace(consensual, character)) {
				case DOM_GENTLE:
					if(!character.isPlayer()) {
						modifiersList.add("Prefers <b style='color: " + SexPace.DOM_GENTLE.getColour().toWebHexString() + "'>gentle</b> pace");
					}
					break;
				case DOM_NORMAL:
					if(!character.isPlayer()) {
						modifiersList.add("Prefers <b style='color: " + SexPace.DOM_NORMAL.getColour().toWebHexString() + "'>normal</b> pace");
					}
					break;
				case DOM_ROUGH:
					if(!character.isPlayer()) {
						if(!character.hasFetish(Fetish.FETISH_DOMINANT) && !character.hasFetish(Fetish.FETISH_SADIST)) {
							modifiersList.add("Prefers <b style='color: " + SexPace.DOM_NORMAL.getColour().toWebHexString() + "'>normal</b> pace");
							modifiersList.add("(<b style='color: " + SexPace.DOM_ROUGH.getColour().toWebHexString() + "'>Rough</b> pace requires "+Fetish.FETISH_DOMINANT.getName(character)
													+", "+Fetish.FETISH_NON_CON_DOM.getName(character)+", or "+Fetish.FETISH_SADIST.getName(character)+" fetish)");
						} else {
							modifiersList.add("Prefers <b style='color: " + SexPace.DOM_ROUGH.getColour().toWebHexString() + "'>rough</b> pace");
						}
					}
					break;
				case SUB_EAGER:
					if(!character.isPlayer()) {
						modifiersList.add("Prefers <b style='color: " + SexPace.SUB_EAGER.getColour().toWebHexString() + "'>eager</b> pace");
					}
					break;
				case SUB_NORMAL:
					if(!character.isPlayer()) {
						modifiersList.add("Prefers <b style='color: " + SexPace.SUB_NORMAL.getColour().toWebHexString() + "'>normal</b> pace");
					}
					break;
				case SUB_RESISTING:
					if(!character.isPlayer()) {
						if(character.hasFetish(Fetish.FETISH_NON_CON_SUB)) {
							modifiersList.add("Always prefers <b style='color: " + SexPace.SUB_RESISTING.getColour().toWebHexString() + "'>resisting</b> pace due to "+Fetish.FETISH_NON_CON_SUB.getName(character)+" fetish");
						} else {
							modifiersList.add("Prefers <b style='color: " + SexPace.SUB_RESISTING.getColour().toWebHexString() + "'>resisting</b> pace");
						}
					}
					break;
			}
		
			int gains = (int)(this.getArousalModifier()*100);
			modifiersList.add((gains>=100?"[style.boldArousal("+gains+"%)]":"[style.boldBad("+gains+"%)]")+" arousal gains");
			
		}
		
		return modifiersList;
	}
	
	public String getStatusEffectDescription(boolean consensual, GameCharacter character) {
		if(Main.game.isInSex()) {
			switch(this.getSexPace(consensual, character)) {
				case DOM_GENTLE:
					if (character.isPlayer()) {
						return "You feel like taking things slow and gentle.";
					} else {
						return UtilText.parse(character, "[npc.Name] is feeling like taking things slow and gentle.");
					}
				case DOM_NORMAL:
					if (character.isPlayer()) {
						return "Filled with passion, you're eager to have sex at the moment.";
					} else {
						return UtilText.parse(character, "Filled with passion, [npc.name] is eager to have sex at the moment.");
					}
				case DOM_ROUGH:
					if (character.isPlayer()) {
						return "You're burning with passion, and will do anything to sate your lust.";
					} else {
						return UtilText.parse(character, "[npc.Name] is burning with passion, and will do anything to sate [npc.her] lust.");
					}
				case SUB_EAGER:
					if (character.isPlayer()) {
						return "You're burning with passion, and will do anything to sate your lust.";
					} else {
						return UtilText.parse(character, "[npc.Name] is burning with passion, and will do anything to sate [npc.her] lust.");
					}
				case SUB_NORMAL:
					if (character.isPlayer()) {
						return "Filled with passion, you're eager to have sex at the moment.";
					} else {
						return UtilText.parse(character, "Filled with passion, [npc.name] is eager to have sex at the moment.");
					}
				case SUB_RESISTING:
					if (character.isPlayer()) {
						return "You aren't interested in having sex at all right now.";
					} else {
						return UtilText.parse(character, "[npc.Name] isn't interested in having sex at all right now.");
					}
			}
		} else {
			switch(this) {
				case ZERO_COLD:
					if (character.isPlayer()) {
						return "You are currently completely disinterested in having sex.";
					} else {
						return UtilText.parse(character, "[npc.Name] is currently completely disinterested in having sex.");
					}
				case ONE_HORNY:
					if (character.isPlayer()) {
						return "You are currently feeling quite horny, but are still in control of your lust.";
					} else {
						return UtilText.parse(character, "[npc.Name] is currently quite horny, but is still in control of [npc.her] lust.");
					}
				case TWO_AMOROUS:
					if (character.isPlayer()) {
						return "You are currently feeling more than a little lustful, and are thinking about sex quite a lot...";
					} else {
						return UtilText.parse(character, "[npc.Name] is currently feeling more than a little lustful, and is thinking about sex quite a lot...");
					}
				case THREE_LUSTFUL:
					if (character.isPlayer()) {
						return "You are currently filled with lust, and are struggling to think of anything other than sex.";
					} else {
						return UtilText.parse(character, "[npc.Name] is currently filled with lust, and is struggling to think of anything other than sex.");
					}
				case FOUR_IMPASSIONED:
					if (character.isPlayer()) {
						return "You are completely filled with lust, and are struggling to think of anything other than sex.";
					} else {
						return UtilText.parse(character, "[npc.Name] is completely filled with lust, and is struggling to think of anything other than sex.");
					}
				case FIVE_BURNING:
					if (character.isPlayer()) {
						return "You're completely overwhelmed with lust, and are incapable of thinking of anything but sex.";
					} else {
						return UtilText.parse(character, "[npc.Name] is completely overwhelmed with lust, and is incapable of thinking of anything but sex.");
					}
			}
		}
		return "";
	
	}
}
