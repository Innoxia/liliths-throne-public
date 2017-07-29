package com.base.game.sex;

import com.base.game.character.GameCharacter;
import com.base.game.character.effects.StatusEffect;
import com.base.game.dialogue.utils.UtilText;
import com.base.utils.Colour;

/**
 * @since 0.1.65
 * @version 0.1.66
 * @author Innoxia
 */
public enum PregnancyDescriptor {
	
	ALREADY_PREGNANT {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum) {
			if(characterBeingImpregnated.isPlayer()){
					return "<p>"
							+ "You feel " + characterProvidingCum.getName("the") + "'s hot cum deep in your pussy, but because "
									+ "<b style='color:" + Colour.GENERIC_SEX + ";'>you're already pregnant, you don't have to worry about it!</b>"
							+ "</p>";
			}else{
				return UtilText.genderParsing(characterBeingImpregnated,
						"<p>"
						+ "Despite having unprotected sex with " + characterBeingImpregnated.getName("the") + ", "
								+ "<b style='color:" + Colour.GENERIC_SEX + ";'><she>'s already pregnant, so you don't have to worry about it!</b>"
						+ "</p>");
			}
		}
	},
	NO_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum) {
			if(characterBeingImpregnated.isPlayer()){
					return "<p>"
							+ "You feel " + characterProvidingCum.getName("the") + "'s hot cum deep in your womb, but you feel that "
									+ "<b style='color:" + Colour.GENERIC_SEX + ";'>you won't get pregnant from this.</b>"
							+ "</p>";
			}else{
				return UtilText.genderParsing(characterBeingImpregnated,
						"<p>"
						+ "Despite having unprotected sex with " + characterBeingImpregnated.getName("the") + ", "
								+ "<b style='color:" + Colour.GENERIC_SEX + ";'><she> isn't going to get pregnant from this.</b>"
						+ "</p>");
			}
		}
	},
	LOW_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum) {
			if(characterBeingImpregnated.isPlayer()){
				return "<p>"
						+ "You feel " + characterProvidingCum.getName("the") + "'s hot cum deep in your womb, and you realise that <b style='color:" + Colour.GENERIC_SEX + ";'>"
								+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
										?"if you aren't already pregnant, there's a small chance you are now!</b>"
										:"there's a small chance you'll get pregnant!</b>")
						+ "</p>";
			}else{
				return UtilText.genderParsing(characterBeingImpregnated,
						"<p>"
						+ "After depositing your hot cum in " + characterBeingImpregnated.getName("the") + "'s womb, you realise that <b style='color:" + Colour.GENERIC_SEX + ";'>"
								+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
										?"if <she> isn't already pregnant, there's a small chance <she> is now!</b>"
										:"there's a small chance <she>'s going to get pregnant!</b>")
						+ "</p>");
			}
		}
	},
	AVERAGE_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum) {
			if(characterBeingImpregnated.isPlayer()){
				return "<p>"
						+ "You feel " + characterProvidingCum.getName("the") + "'s hot cum deep in your womb, and you realise that <b style='color:" + Colour.GENERIC_SEX + ";'>"
								+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
										?"if you aren't already pregnant, there's a chance you are now!</b>"
										:"there's a chance you'll get pregnant!</b>")
						+ "</p>";
			}else{
				return UtilText.genderParsing(characterBeingImpregnated,
						"<p>"
						+ "After depositing your hot cum in " + characterBeingImpregnated.getName("the") + "'s womb, you realise that <b style='color:" + Colour.GENERIC_SEX + ";'>"
								+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
										?"if <she> isn't already pregnant, there's a chance <she> is now!</b>"
										:"there's a chance <she>'s going to get pregnant!</b>")
						+ "</p>");
			}
		}
	},
	HIGH_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum) {
			if(characterBeingImpregnated.isPlayer()){
				return "<p>"
						+ "You feel " + characterProvidingCum.getName("the") + "'s hot cum deep in your womb, and you realise that <b style='color:" + Colour.GENERIC_SEX + ";'>"
								+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
										?"if you aren't already pregnant, there's a high chance you are now!</b>"
										:"there's a high chance you'll get pregnant!</b>")
						+ "</p>";
			}else{

				return UtilText.genderParsing(characterBeingImpregnated,
						"<p>"
						+ "After depositing your hot cum in " + characterBeingImpregnated.getName("the") + "'s womb, you realise that <b style='color:" + Colour.GENERIC_SEX + ";'>"
								+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
										?"if <she> isn't already pregnant, there's a high chance <she> is now!</b>"
										:"there's a high chance <she>'s going to get pregnant!</b>")
						+ "</p>");
			}
		}
	},
	CERTAINTY {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum) {
			if(characterBeingImpregnated.isPlayer()){
				return "<p>"
						+ "You feel " + characterProvidingCum.getName("the") + "'s hot cum deep in your womb, and you realise that <b style='color:" + Colour.GENERIC_SEX + ";'>"
								+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
										?"if you aren't already pregnant, you certainly are now!</b>"
										:"it's a certainty that you'll get pregnant!</b>")
						+ "</p>";
			}else{

				return UtilText.genderParsing(characterBeingImpregnated,
						"<p>"
						+ "After depositing your hot cum in " + characterBeingImpregnated.getName("the") + "'s womb, you realise that <b style='color:" + Colour.GENERIC_SEX + ";'>"
								+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
										?"if <she> isn't already pregnant, <she> certainly is now!</b>"
										:"it's a certainty that <she>'s going to get pregnant!</b>")
						+ "</p>");
			}
		}
	};
	
	private PregnancyDescriptor(){
	}
	
	public abstract String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum);
}
