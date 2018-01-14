package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.97
 * @version 0.1.97
 * @author Innoxia
 */
public enum LustLevel {

	ZERO_COLD("cold", 0, 20, Colour.LUST_STAGE_ZERO, SexPace.SUB_RESISTING, SexPace.DOM_GENTLE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_0;
		}
	},

	ONE_HORNY("horny", 20, 40, Colour.LUST_STAGE_ONE, SexPace.SUB_NORMAL, SexPace.DOM_NORMAL) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_1;
		}
	},

	TWO_AMOROUS("amorous", 40, 60, Colour.LUST_STAGE_TWO, SexPace.SUB_NORMAL, SexPace.DOM_NORMAL) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_2;
		}
	},

	THREE_IMPASSIONED("impassioned", 60, 80, Colour.LUST_STAGE_THREE, SexPace.SUB_NORMAL, SexPace.DOM_NORMAL) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_3;
		}
	},

	FOUR_BURNING("burning", 80, 100, Colour.LUST_STAGE_FOUR, SexPace.SUB_EAGER, SexPace.DOM_ROUGH) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_4;
		}
	};
	
	
	private String name;
	private int minimumValue, maximumValue;
	private Colour colour;
	private SexPace sexPaceSubmissive;
	private SexPace sexPaceDominant;

	private LustLevel(String name, int minimumValue, int maximumValue, Colour colour, SexPace sexPaceSubmissive, SexPace sexPaceDominant) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
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

	public Colour getColour() {
		return colour;
	}

	public static LustLevel getLustLevelFromValue(float value){
		for(LustLevel al : LustLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue()) {
				return al;
			}
		}
		return FOUR_BURNING;
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
			pace = getSexPaceDominant();
		} else {
			pace = getSexPaceSubmissive();
		}
		
		if(consensual && pace==SexPace.SUB_RESISTING && !character.hasFetish(Fetish.FETISH_NON_CON_SUB)) {
			pace = SexPace.SUB_NORMAL;
		}
		
		return pace;
	}
	
//	public String getSVGImage(GameCharacter character) {
//		if(Sex.isDom(character)) {
//			switch(this.getSexPaceDominant()) {
//				case DOM_GENTLE:
//					return SVGImages.SVG_IMAGE_PROVIDER.getResponseDomGentle();
//				case DOM_NORMAL:
//					return SVGImages.SVG_IMAGE_PROVIDER.getResponseDomNormal();
//				case DOM_ROUGH:
//					return SVGImages.SVG_IMAGE_PROVIDER.getResponseDomRough();
//				case SUB_EAGER:
//					return SVGImages.SVG_IMAGE_PROVIDER.getResponseSubEager();
//				case SUB_NORMAL:
//					return SVGImages.SVG_IMAGE_PROVIDER.getResponseSubNormal();
//				case SUB_RESISTING:
//					return SVGImages.SVG_IMAGE_PROVIDER.getResponseSubResist();
//			}
//		} else {
//			switch(this.getSexPaceSubmissive()) {
//				case DOM_GENTLE:
//					return SVGImages.SVG_IMAGE_PROVIDER.getResponseDomGentle();
//				case DOM_NORMAL:
//					return SVGImages.SVG_IMAGE_PROVIDER.getResponseDomNormal();
//				case DOM_ROUGH:
//					return SVGImages.SVG_IMAGE_PROVIDER.getResponseDomRough();
//				case SUB_EAGER:
//					return SVGImages.SVG_IMAGE_PROVIDER.getResponseSubEager();
//				case SUB_NORMAL:
//					return SVGImages.SVG_IMAGE_PROVIDER.getResponseSubNormal();
//				case SUB_RESISTING:
//					return SVGImages.SVG_IMAGE_PROVIDER.getResponseSubResist();
//			}
//		}
//		return "";	
//	}
	
	public String getStatusEffectModifierDescription(boolean consensual, GameCharacter character) {
		switch(this.getSexPace(consensual, character)) {
			case DOM_GENTLE:
				return "<b style='color: " + SexPace.DOM_GENTLE.getColour().toWebHexString() + "'>Gentle</b> pace";
			case DOM_NORMAL:
				return "<b style='color: " + SexPace.DOM_NORMAL.getColour().toWebHexString() + "'>Normal</b> pace";
			case DOM_ROUGH:
				return "<b style='color: " + SexPace.DOM_ROUGH.getColour().toWebHexString() + "'>Rough</b> pace";
			case SUB_EAGER:
				return "<b style='color: " + SexPace.SUB_EAGER.getColour().toWebHexString() + "'>Eager</b> pace";
			case SUB_NORMAL:
				return "<b style='color: " + SexPace.SUB_NORMAL.getColour().toWebHexString() + "'>Normal</b> pace";
			case SUB_RESISTING:
				return "<b style='color: " + SexPace.SUB_RESISTING.getColour().toWebHexString() + "'>Resisting</b> pace";
		}
		return "";
	}
	
	public String getStatusEffectDescription(boolean consensual, GameCharacter character) {
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
		return "";
	
	}
}
