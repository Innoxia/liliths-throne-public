package com.base.game.character;

import com.base.game.character.effects.StatusEffect;
import com.base.utils.Colour;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public enum SexualOrientation {

	ANDROPHILIC("androphilic", Colour.MASCULINE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.AROUSAL_PERK_0;
		}
	},

	GYNEPHILIC("gynephilic",Colour.FEMININE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.AROUSAL_PERK_1;
		}
	},

	AMBIPHILIC("ambiphilic", Colour.ANDROGYNOUS) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.AROUSAL_PERK_2;
		}
	};
	
	private String name;
	private Colour colour;

	private SexualOrientation(String name, Colour colour) {
		this.name = name;
		this.colour = colour;
	}
	
	public abstract StatusEffect getRelatedStatusEffect();

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}
	
}
