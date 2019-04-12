package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.65
 * @version 0.3.1
 * @author Innoxia
 */
public enum PregnancyDescriptor {
	
	ALREADY_PREGNANT {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			
			if(characterBeingImpregnated.isPlayer()){
					return UtilText.parse(characterProvidingCum,
							"<p>"
								+ "You feel "+(selfcest?"your own":"[npc.namePos]")+" [npc.cum+] "
									+(isSlime
										?"dispersing through your slimy body, seeking to impregnate your core"
										:"deep in your [pc.pussy+]")
								+", but because <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>you're already pregnant, you don't have to worry about it!</b>"
							+ "</p>");
			} else {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ "[npc2.NameIsFull] already pregnant, <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>so there's no chance that "+(selfcest?"[npc.she] has knocked [npc.herself]":"[npc1.nameHas] knocked [npc2.herHim]")+" up!</b>"
						+ "</p>");
			}
		}
	},
	
	NO_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			
			if(characterBeingImpregnated.isPlayer()){
					return UtilText.parse(characterProvidingCum,
							"<p>"
								+ "Despite feeling "+(selfcest?"your own":"[npc.namePos]")+" [npc.cum+] "
									+(isSlime
										?"dispersing through your slimy body, seeking to impregnate your core"
										:"deep in your womb")
								+", you feel that <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>you aren't going to get pregnant from this.</b>"
							+ "</p>");
					
			} else if(selfcest) {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ "Despite having [npc.her] "+(isSlime?"slimy body":"womb")+" filled with [npc.her] own seed,"
									+ " <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>there's no chance that [npc.nameHasFull] impregnated [npc.herself].</b>"
						+ "</p>");
				
			} else {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ "Despite having unprotected sex with [npc2.name], <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>there's no chance that [npc1.nameHasFull] impregnated [npc2.herHim].</b>"
						+ "</p>");
			}
		}
	},
	
	LOW_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			
			if(characterBeingImpregnated.isPlayer()){
				return UtilText.parse(characterProvidingCum,
						"<p>"
							+ "You feel "+(selfcest?"your own":"[npc.namePos]")+" [npc.cum+] "
							+(isSlime
									?"dispersing through your slimy body, seeking to impregnate your core"
									:"deep in your womb")
							+", and you realise that <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>"
								+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
										?"if you aren't already pregnant, there's a small chance you are now!</b>"
										:"there's a small chance you'll get pregnant!</b>")
						+ "</p>");
				
			} else if(selfcest) {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ "After having [npc.her] "+(isSlime?"slimy body":"womb")+" filled with [npc.her] own seed, [npc.name] [npc.verb(realise)] that <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>"
									+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
											?"if [npc2.she] isn't already pregnant, there's a small chance [npc2.she] is now!</b>"
											:"there's a small chance that [npc2.sheIs] going to get pregnant!</b>")
						+ "</p>");
				
			} else {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ "After depositing [npc1.her] [npc1.cum+] in [npc2.namePos] "
							+(isSlime
									?"slimy body"
									:"womb")
							+", [npc.name] [npc.verb(realise)] that <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>"
									+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
											?"if [npc2.she] isn't already pregnant, there's a small chance [npc2.she] is now!</b>"
											:"there's a small chance that [npc2.sheIs] going to get pregnant!</b>")
						+ "</p>");
			}
		}
	},
	
	AVERAGE_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			
			if(characterBeingImpregnated.isPlayer()){
				return UtilText.parse(characterProvidingCum,
						"<p>"
						+ "You feel "+(selfcest?"your own":"[npc.namePos]")+" [npc.cum+] "
						+(isSlime
								?"dispersing through your slimy body, seeking to impregnate your core"
								:"deep in your womb")
						+", and you realise that <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>"
								+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
										?"if you aren't already pregnant, there's a chance you are now!</b>"
										:"there's a chance you'll get pregnant!</b>")
						+ "</p>");
				
			} else if(selfcest) {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ "After having [npc.her] "+(isSlime?"slimy body":"womb")+" filled with [npc.her] own seed, [npc.name] [npc.verb(realise)] that <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>"
									+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
											?"if [npc2.she] isn't already pregnant, there's a chance [npc2.she] is now!</b>"
											:"there's a chance that [npc2.sheIs] going to get pregnant!</b>")
						+ "</p>");
				
			} else {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ "After depositing [npc1.her] [npc1.cum+] in [npc2.namePos] "
							+(isSlime
									?"slimy body"
									:"womb")
							+", [npc.name] [npc.verb(realise)] that <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>"
									+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
											?"if [npc2.she] isn't already pregnant, there's a chance [npc2.she] is now!</b>"
											:"there's a chance that [npc2.sheIs] going to get pregnant!</b>")
						+ "</p>");
			}
		}
	},
	
	HIGH_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			
			if(characterBeingImpregnated.isPlayer()){
				return UtilText.parse(characterProvidingCum,
						"<p>"
						+ "You feel "+(selfcest?"your own":"[npc.namePos]")+" [npc.cum+] "+
						(isSlime
								?"dispersing through your slimy body, seeking to impregnate your core"
								:"deep in your womb")
						+", and you realise that <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>"
								+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
										?"if you aren't already pregnant, there's a high chance you are now!</b>"
										:"there's a high chance you'll get pregnant!</b>")
						+ "</p>");
				
			} else if(selfcest) {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ "After having [npc.her] "+(isSlime?"slimy body":"womb")+" filled with [npc.her] own seed, [npc.name] [npc.verb(realise)] that <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>"
									+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
											?"if [npc2.she] isn't already pregnant, there's a high chance [npc2.she] is now!</b>"
											:"there's a high chance that [npc2.sheIs] going to get pregnant!</b>")
						+ "</p>");
				
			} else {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ "After depositing [npc1.her] [npc1.cum+] in [npc2.namePos] "
							+(isSlime
									?"slimy body"
									:"womb")
							+", [npc.name] [npc.verb(realise)] that <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>"
									+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
											?"if [npc2.she] isn't already pregnant, there's a high chance [npc2.she] is now!</b>"
											:"there's a high chance that [npc2.sheIs] going to get pregnant!</b>")
						+ "</p>");
			}
		}
	},
	
	CERTAINTY {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			
			if(characterBeingImpregnated.isPlayer()){
				return UtilText.parse(characterProvidingCum,
						"<p>"
							+ "You feel "+(selfcest?"your own":"[npc.namePos]")+" [npc.cum+] "
							+(isSlime
									?"dispersing through your slimy body, seeking to impregnate your core"
									:"deep in your womb")
							+", and you realise that <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>"
								+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
										?"if you aren't already pregnant, you certainly are now!</b>"
										:"it's a certainty that you'll get pregnant!</b>")
						+ "</p>");
				
			} else if(selfcest) {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ "After having [npc.her] "+(isSlime?"slimy body":"womb")+" filled with [npc.her] own seed, [npc.name] [npc.verb(realise)] that <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>"
									+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
											?"if [npc2.she] isn't already pregnant, [npc2.she] certainly is now!</b>"
											:"it's a certainty that [npc2.sheIs] going to get pregnant!</b>")
						+ "</p>");
				
			} else {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ "After depositing [npc1.her] [npc1.cum+] in [npc2.namePos] "
							+(isSlime
									?"slimy body"
									:"womb")
							+", [npc.name] [npc.verb(realise)] that <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>"
									+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
											?"if [npc2.she] isn't already pregnant, [npc2.she] certainly is now!</b>"
											:"it's a certainty that [npc2.sheIs] going to get pregnant!</b>")
						+ "</p>");
			}
		}
	};
	
	
	public abstract String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum);
	
	public static PregnancyDescriptor getPregnancyDescriptorBasedOnProbability(float probability) {
		if (probability <= 0) {
			return PregnancyDescriptor.NO_CHANCE;
		} else if (probability <= 0.15f) {
			return PregnancyDescriptor.LOW_CHANCE;
		} else if (probability <= 0.3f) {
			return PregnancyDescriptor.AVERAGE_CHANCE;
		} else if (probability < 1) {
			return PregnancyDescriptor.HIGH_CHANCE;
		} else {
			return PregnancyDescriptor.CERTAINTY;
		}
	}
}
