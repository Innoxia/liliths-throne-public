package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.65
 * @version 0.3.4
 * @author Innoxia
 */
public enum PregnancyDescriptor {
	
	ALREADY_PREGNANT {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			
			if(characterBeingImpregnated.isPlayer()){
					return UtilText.parse(characterProvidingCum,
							"<p>"
								+ "You feel "+(selfcest?"your own":"[npc.namePos]")+" [npc.cum+] "
									+(isSlime
										?"dispersing through your slimy body, seeking to impregnate your core"
										:"deep in your [pc.pussy+]")
								+", but because [style.boldSex(you're already pregnant, you don't have to worry about it)]!"
							+ "</p>");
			} else {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ "[npc2.NameIsFull] already pregnant, [style.boldSex(so there's no chance that "
								+(!directSexInsemination
										? (selfcest
											?"[npc.she] has impregnated [npc.herself]"
											:"[npc1.nameHas] impregnated [npc2.herHim]")
										: (selfcest
											?"[npc.she] has knocked [npc.herself] up"
											:"[npc1.nameHas] knocked [npc2.herHim] up"))
								+")]!"
						+ "</p>");
			}
		}
	},
	
	NO_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			
			if(characterBeingImpregnated.isPlayer()){
					return UtilText.parse(characterProvidingCum,
							"<p>"
								+ "Despite feeling "+(selfcest?"your own":"[npc.namePos]")+" [npc.cum+] "
									+(isSlime
										?"dispersing through your slimy body, seeking to impregnate your core"
										:"deep in your womb")
								+", you feel that [style.boldSex(you aren't going to get pregnant from this)]."
							+ "</p>");
					
			} else if(selfcest) {
				return UtilText.parse(characterProvidingCum,
						"<p>"
							+ "Despite having [npc.her] "+(isSlime?"slimy body":"womb")+" filled with [npc.her] own seed,"
									+ " [style.boldSex(there's no chance that [npc.nameHasFull] impregnated [npc.herself])]."
						+ "</p>");
				
			} else {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ (!directSexInsemination
									?"Despite having [npc2.her] "+(isSlime?"slimy body":"womb")+" filled with [npc.namePos] [npc.cum+]"
									:"Despite having unprotected sex with [npc.name]")
							+ ", [style.boldSex(there's no chance that [npc2.nameHasFull] been impregnated)]."
						+ "</p>");
			}
		}
	},
	
	LOW_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			
			if(characterBeingImpregnated.isPlayer()){
				return UtilText.parse(characterProvidingCum,
						"<p>"
							+ "You feel "+(selfcest?"your own":"[npc.namePos]")+" [npc.cum+] "
							+(isSlime
									?"dispersing through your slimy body, seeking to impregnate your core"
									:"deep in your womb")
							+", and you realise that [style.boldSex("
								+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
										?"if you aren't already pregnant, there's a small chance you are now"
										:"there's a small chance you'll get pregnant")
							+")]!"
						+ "</p>");
				
			} else if(selfcest) {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ "After having [npc.her] "+(isSlime?"slimy body":"womb")+" filled with [npc.her] own seed, [npc.name] [npc.verb(realise)] that [style.boldSex("
									+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
											?"if [npc2.she] isn't already pregnant, there's a small chance [npc2.she] is now"
											:"there's a small chance that [npc2.sheIs] going to get pregnant")
							+")]!"
						+ "</p>");
				
			} else {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ (!directSexInsemination
									?"After having [npc2.her] "+(isSlime?"slimy body":"womb")+" filled with [npc.namePos] [npc.cum+]"
										+", [npc.name] [npc.verb(realise)] that [style.boldSex("
										+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
												?"if [npc2.she] isn't already pregnant, there's a small chance [npc2.she] is now"
												:"there's a small chance that [npc2.sheIs] going to get pregnant")
									:"After depositing [npc1.her] [npc.cum+] in [npc2.namePos] "+(isSlime?"slimy body":"womb")
										+", [npc.name] [npc.verb(realise)] that [style.boldSex("
										+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
												?"if [npc2.she] isn't already, there's a small chance that [npc.sheHas] got [npc2.name] pregnant"
												:"there's a small chance that [npc.sheHas] got [npc2.name] pregnant"))
							+")]!"
						+ "</p>");
			}
		}
	},
	
	AVERAGE_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			
			if(characterBeingImpregnated.isPlayer()){
				return UtilText.parse(characterProvidingCum,
						"<p>"
							+ "You feel "+(selfcest?"your own":"[npc.namePos]")+" [npc.cum+] "
							+(isSlime
									?"dispersing through your slimy body, seeking to impregnate your core"
									:"deep in your womb")
							+", and you realise that [style.boldSex("
									+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
											?"if you aren't already pregnant, there's a chance you are now"
											:"there's a chance you'll get pregnant")
							+")]!"
						+ "</p>");
				
			} else if(selfcest) {
				return UtilText.parse(characterProvidingCum,
						"<p>"
							+ "After having [npc.her] "+(isSlime?"slimy body":"womb")+" filled with [npc.her] own seed, [npc.name] [npc.verb(realise)] that [style.boldSex("
									+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
											?"if [npc.she] isn't already pregnant, there's a chance [npc.she] is now"
											:"there's a chance that [npc.sheIs] going to get pregnant")
							+")]!"
						+ "</p>");
				
			} else {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ (!directSexInsemination
									?"After having [npc2.her] "+(isSlime?"slimy body":"womb")+" filled with [npc.namePos] [npc.cum+]"
										+", [npc.name] [npc.verb(realise)] that [style.boldSex("
										+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
												?"if [npc2.she] isn't already pregnant, there's a chance [npc2.she] is now"
												:"there's a chance that [npc2.sheIs] going to get pregnant")
									:"After depositing [npc1.her] [npc.cum+] in [npc2.namePos] "+(isSlime?"slimy body":"womb")
										+", [npc.name] [npc.verb(realise)] that [style.boldSex("
										+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
												?"if [npc2.she] isn't already, there's a chance that [npc.sheHas] got [npc2.name] pregnant"
												:"there's a chance that [npc.sheHas] got [npc2.name] pregnant"))
							+")]!"
						+ "</p>");
			}
		}
	},
	
	HIGH_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			
			if(characterBeingImpregnated.isPlayer()){
				return UtilText.parse(characterProvidingCum,
						"<p>"
							+ "You feel "+(selfcest?"your own":"[npc.namePos]")+" [npc.cum+] "+
							(isSlime
									?"dispersing through your slimy body, seeking to impregnate your core"
									:"deep in your womb")
							+", and you realise that [style.boldSex("
									+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
											?"if you aren't already pregnant, there's a high chance you are now"
											:"there's a high chance you'll get pregnant")
							+")]!"
						+ "</p>");
				
			} else if(selfcest) {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ "After having [npc.her] "+(isSlime?"slimy body":"womb")+" filled with [npc.her] own seed, [npc.name] [npc.verb(realise)] that [style.boldSex("
									+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
											?"if [npc2.she] isn't already pregnant, there's a high chance [npc2.she] is now"
											:"there's a high chance that [npc2.sheIs] going to get pregnant")
							+")]!"
						+ "</p>");
				
			} else {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ (!directSexInsemination
									?"After having [npc2.her] "+(isSlime?"slimy body":"womb")+" filled with [npc.namePos] [npc.cum+]"
										+", [npc.name] [npc.verb(realise)] that [style.boldSex("
										+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
												?"if [npc2.she] isn't already pregnant, there's a high chance [npc2.she] is now"
												:"there's a high chance that [npc2.sheIs] going to get pregnant")
									:"After depositing [npc1.her] [npc.cum+] in [npc2.namePos] "+(isSlime?"slimy body":"womb")
										+", [npc.name] [npc.verb(realise)] that [style.boldSex("
										+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
												?"if [npc2.she] isn't already, there's a high chance that [npc.sheHas] got [npc2.name] pregnant"
												:"there's a high chance that [npc.sheHas] got [npc2.name] pregnant"))
							+")]!"
						+ "</p>");
			}
		}
	},
	
	CERTAINTY {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			
			if(characterBeingImpregnated.isPlayer()){
				return UtilText.parse(characterProvidingCum,
						"<p>"
							+ "You feel "+(selfcest?"your own":"[npc.namePos]")+" [npc.cum+] "
							+(isSlime
									?"dispersing through your slimy body, seeking to impregnate your core"
									:"deep in your womb")
							+", and you realise that [style.boldExcellent("
								+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
										?"if you aren't already pregnant, you certainly are now!)]"
										:"it's a certainty that you'll get pregnant!)]")
						+ "</p>");
				
			} else if(selfcest) {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ "After having [npc.her] "+(isSlime?"slimy body":"womb")+" filled with [npc.her] own seed, [npc.name] [npc.verb(realise)] that [style.boldExcellent("
									+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
											?"if [npc2.she] isn't already pregnant, [npc2.she] certainly is now!)]"
											:"it's a certainty that [npc2.sheIs] going to get pregnant!)]")
						+ "</p>");
				
			} else {
				return UtilText.parse(characterProvidingCum, characterBeingImpregnated,
						"<p>"
							+ (!directSexInsemination
									?"After having [npc2.her] "+(isSlime?"slimy body":"womb")+" filled with [npc.namePos] [npc.cum+]"
										+", [npc.name] [npc.verb(realise)] that [style.boldExcellent("
										+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
												?"if [npc2.she] isn't already pregnant, [npc2.she] certainly is now"
												:"it's a certainty that [npc2.sheIs] going to get pregnant")
									:"After depositing [npc1.her] [npc.cum+] in [npc2.namePos] "+(isSlime?"slimy body":"womb")
										+", [npc.name] [npc.verb(realise)] that [style.boldSex("
										+ (characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)
												?"if [npc2.she] isn't already, it's a certainty that [npc.sheHas] got [npc2.name] pregnant"
												:"it's a certainly that [npc.sheHas] got [npc2.name] pregnant"))
						+ "</p>");
			}
		}
	};
	
	
	public abstract String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination);
	
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
