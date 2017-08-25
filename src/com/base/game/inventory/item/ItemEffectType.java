package com.base.game.inventory.item;

import java.util.ArrayList;
import java.util.List;

import com.base.game.Game;
import com.base.game.character.GameCharacter;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.body.Covering;
import com.base.game.character.body.types.ArmType;
import com.base.game.character.body.types.AssType;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.types.BreastType;
import com.base.game.character.body.types.EarType;
import com.base.game.character.body.types.EyeType;
import com.base.game.character.body.types.FaceType;
import com.base.game.character.body.types.HairType;
import com.base.game.character.body.types.HornType;
import com.base.game.character.body.types.LegType;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.SkinType;
import com.base.game.character.body.types.TailType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.types.WingType;
import com.base.game.character.body.valueEnums.AssSize;
import com.base.game.character.body.valueEnums.BodyHair;
import com.base.game.character.body.valueEnums.CoveringPattern;
import com.base.game.character.body.valueEnums.CumProduction;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.body.valueEnums.HipSize;
import com.base.game.character.body.valueEnums.Wetness;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.Perk;
import com.base.game.character.effects.StatusEffect;
import com.base.game.character.race.Race;
import com.base.game.character.race.RacialBody;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.game.inventory.enchanting.TFModifier;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.7
 * @version 0.1.8
 * @author Innoxia
 */
public enum ItemEffectType {
	
	TESTING(Util.newArrayListOfValues(
			new ListValue<>("Test item.")),
		Colour.GENERIC_ARCANE) {

	@Override
	public List<TFModifier> getPrimaryModifiers() {
		return null;
	}

	@Override
	public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
		return null;
	}
	
	@Override
	public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
		return target.incrementMuscle(-25)
				+ target.incrementBodySize(25)
				+ target.setUnderarmHair(BodyHair.BUSHY);
	}
},
	
	DYE_BRUSH(Util.newArrayListOfValues(
				new ListValue<>("Recolours a piece of clothing.")),
			Colour.GENERIC_ARCANE) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return "<p>"
					+ "You use the dye brush."
					+ "</p>";
		}
	},
	
	APPLY_CONDOM(Util.newArrayListOfValues(
			new ListValue<>("Prevents pregnancy.")),
			Colour.GENERIC_SEX) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			if(target.getPenisType()!=PenisType.NONE && target.isCoverableAreaExposed(CoverableArea.PENIS) && !target.isWearingCondom())
				target.setWearingCondom(true);
			
			Main.mainController.forceInventoryRender();
			return "<p>"
					+ "You pull a condom out from your inventory, and, quickly tearing open the wrapper, you roll the rubber down the length of your cock."
					+ "</p>";
		}
	},
	
	USED_CONDOM_DRINK(Util.newArrayListOfValues(
			new ListValue<>("Provides a slimy snack.")),
			Colour.GENERIC_SEX) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return "<p>"
					+ "It tastes salty..." //TODO
					+ "</p>";
		}
	},
	
	BOOK_READ_CAT_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds cat-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_CAT_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return Race.CAT_MORPH.getBasicDescription()
					+Race.CAT_MORPH.getAdvancedDescription()
					+(Main.game.getPlayer().addRaceDiscovered(Race.CAT_MORPH)
							?Game.getRaceDiscoveredMessage(Race.CAT_MORPH)
							:"")
					+(Main.game.getPlayer().addRacesAdvancedKnowledge(Race.CAT_MORPH)
							?Game.getRaceAdvancedKnowledgeMessage(Race.CAT_MORPH)
								+"<p>"
									+ "<b style='colour:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Book added to Lilaya's library!</b>"
								+ "</p>"
								+ Main.game.getPlayer().incrementAttribute(Attribute.INTELLIGENCE, 0.5f)
							:"");
		}
	},
	
	BOOK_READ_COW_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds cow-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_COW_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return Race.COW_MORPH.getBasicDescription()
					+Race.COW_MORPH.getAdvancedDescription()
					+(Main.game.getPlayer().addRaceDiscovered(Race.COW_MORPH)
							?Game.getRaceDiscoveredMessage(Race.COW_MORPH)
							:"")
					+(Main.game.getPlayer().addRacesAdvancedKnowledge(Race.COW_MORPH)
							?Game.getRaceAdvancedKnowledgeMessage(Race.COW_MORPH)
								+"<p>"
									+ "<b style='colour:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Book added to Lilaya's library!</b>"
								+ "</p>"
								+ Main.game.getPlayer().incrementAttribute(Attribute.INTELLIGENCE, 0.5f)
							:"");
		}
	},
	
	BOOK_READ_DEMON(Util.newArrayListOfValues(
			new ListValue<>("Adds demon encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_DEMON) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return Race.DEMON.getBasicDescription()
					+Race.DEMON.getAdvancedDescription()
					+(Main.game.getPlayer().addRaceDiscovered(Race.DEMON)
							?Game.getRaceDiscoveredMessage(Race.DEMON)
							:"")
					+(Main.game.getPlayer().addRacesAdvancedKnowledge(Race.DEMON)
							?Game.getRaceAdvancedKnowledgeMessage(Race.DEMON)
								+"<p>"
									+ "<b style='colour:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Book added to Lilaya's library!</b>"
								+ "</p>"
								+ Main.game.getPlayer().incrementAttribute(Attribute.INTELLIGENCE, 0.5f)
							:"");
		}
	},
	
	BOOK_READ_DOG_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds dog-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_DOG_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return Race.DOG_MORPH.getBasicDescription()
					+Race.DOG_MORPH.getAdvancedDescription()
					+(Main.game.getPlayer().addRaceDiscovered(Race.DOG_MORPH)
							?Game.getRaceDiscoveredMessage(Race.DOG_MORPH)
							:"")
					+(Main.game.getPlayer().addRacesAdvancedKnowledge(Race.DOG_MORPH)
							?Game.getRaceAdvancedKnowledgeMessage(Race.DOG_MORPH)
								+"<p>"
									+ "<b style='colour:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Book added to Lilaya's library!</b>"
								+ "</p>"
								+ Main.game.getPlayer().incrementAttribute(Attribute.INTELLIGENCE, 0.5f)
							:"");
		}
	},
	
	BOOK_READ_HARPY(Util.newArrayListOfValues(
			new ListValue<>("Adds harpy encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_HARPY) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return Race.HARPY.getBasicDescription()
					+Race.HARPY.getAdvancedDescription()
					+(Main.game.getPlayer().addRaceDiscovered(Race.HARPY)
							?Game.getRaceDiscoveredMessage(Race.HARPY)
							:"")
					+(Main.game.getPlayer().addRacesAdvancedKnowledge(Race.HARPY)
							?Game.getRaceAdvancedKnowledgeMessage(Race.HARPY)
								+"<p>"
									+ "<b style='colour:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Book added to Lilaya's library!</b>"
								+ "</p>"
								+ Main.game.getPlayer().incrementAttribute(Attribute.INTELLIGENCE, 0.5f)
							:"");
		}
	},
	
	BOOK_READ_HORSE_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds horse-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_HORSE_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return Race.HORSE_MORPH.getBasicDescription()
					+Race.HORSE_MORPH.getAdvancedDescription()
					+(Main.game.getPlayer().addRaceDiscovered(Race.HORSE_MORPH)
							?Game.getRaceDiscoveredMessage(Race.HORSE_MORPH)
							:"")
					+(Main.game.getPlayer().addRacesAdvancedKnowledge(Race.HORSE_MORPH)
							?Game.getRaceAdvancedKnowledgeMessage(Race.HORSE_MORPH)
								+"<p>"
									+ "<b style='colour:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Book added to Lilaya's library!</b>"
								+ "</p>"
								+ Main.game.getPlayer().incrementAttribute(Attribute.INTELLIGENCE, 0.5f)
							:"");
		}
	},
	
	BOOK_READ_HUMAN(Util.newArrayListOfValues(
			new ListValue<>("Adds human encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_HUMAN) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return Race.HUMAN.getBasicDescription()
					+Race.HUMAN.getAdvancedDescription()
					+(Main.game.getPlayer().addRaceDiscovered(Race.HUMAN)
							?Game.getRaceDiscoveredMessage(Race.HUMAN)
							:"")
					+(Main.game.getPlayer().addRacesAdvancedKnowledge(Race.HUMAN)
							?Game.getRaceAdvancedKnowledgeMessage(Race.HUMAN)
								+"<p>"
									+ "<b style='colour:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Book added to Lilaya's library!</b>"
								+ "</p>"
								+ Main.game.getPlayer().incrementAttribute(Attribute.INTELLIGENCE, 0.5f)
							:"");
		}
	},
	
	BOOK_READ_SQUIRREL_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds squirrel-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_SQUIRREL_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return Race.SQUIRREL_MORPH.getBasicDescription()
					+Race.SQUIRREL_MORPH.getAdvancedDescription()
					+(Main.game.getPlayer().addRaceDiscovered(Race.SQUIRREL_MORPH)
							?Game.getRaceDiscoveredMessage(Race.SQUIRREL_MORPH)
							:"")
					+(Main.game.getPlayer().addRacesAdvancedKnowledge(Race.SQUIRREL_MORPH)
							?Game.getRaceAdvancedKnowledgeMessage(Race.SQUIRREL_MORPH)
								+"<p>"
									+ "<b style='colour:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Book added to Lilaya's library!</b>"
								+ "</p>"
								+ Main.game.getPlayer().incrementAttribute(Attribute.INTELLIGENCE, 0.5f)
							:"");
		}
	},
	
	BOOK_READ_WOLF_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds wolf-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_WOLF_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return Race.WOLF_MORPH.getBasicDescription()
					+Race.WOLF_MORPH.getAdvancedDescription()
					+(Main.game.getPlayer().addRaceDiscovered(Race.WOLF_MORPH)
							?Game.getRaceDiscoveredMessage(Race.WOLF_MORPH)
							:"")
					+(Main.game.getPlayer().addRacesAdvancedKnowledge(Race.WOLF_MORPH)
							?Game.getRaceAdvancedKnowledgeMessage(Race.WOLF_MORPH)
								+"<p>"
									+ "<b style='colour:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Book added to Lilaya's library!</b>"
								+ "</p>"
								+ Main.game.getPlayer().incrementAttribute(Attribute.INTELLIGENCE, 0.5f)
							:"");
		}
	},
	
	VIXENS_VIRILITY(Util.newArrayListOfValues(
			new ListValue<>("Temporarily boosts fertility.")),
			Colour.GENERIC_SEX) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.removeStatusEffect(StatusEffect.PROMISCUITY_PILL);
			
			target.addStatusEffect(StatusEffect.VIXENS_VIRILITY, 60*24);
			
			if(target.isPlayer()) {
				return "<p>"
						+ "The little pink pill easily slides down your throat, and within moments, you feel "
						+ ( Main.game.getPlayer().hasVagina()
								? "a strange, warm glow spreading from what you guess must be your ovaries."
									+ " Your mind fogs over with an overwhelming desire to feel potent sperm spurting deep into your "+(Main.game.getPlayer().isVisiblyPregnant()?"pussy":"womb")
									+", and before you can stop it, a little whimper escapes from between your [pc.lips]."
									+ (Main.game.getPlayer().hasPenis()
											?" At the same time, your manhood begins to throb with need, and you feel "
											:"") 
							:"")
						+ (Main.game.getPlayer().hasPenis() 
								? "an overpowering desire to sink deep into a fertile female's cunt and fill her with your [pc.cum+]."
								: "")
					+ "</p>";
			
			} else {
				return UtilText.parse(target,
						"<p>"
							+ "The little pink pill easily slides down [npc.her] throat, and within moments, [npc.she] feels "
							+ ( target.hasVagina()
									? "a strange, warm glow spreading from [npc.her] ovaries."
										+ " [npc.Her] mind fogs over with an overwhelming desire to feel potent sperm spurting deep into [npc.her] "+(Main.game.getPlayer().isVisiblyPregnant()?"pussy":"womb")
										+", and before [npc.she] can stop it, a little whimper escapes from between [npc.her] [npc.lips]."
										+ (target.hasPenis()
												?" At the same time, [npc.her] manhood begins to throb with need, and [npc.she] feels "
												:"") 
								:"")
							+ (Main.game.getPlayer().hasPenis() 
									? "an overpowering desire to sink deep into a fertile female's cunt and fill her with [npc.cum+]."
									: "")
						+ "</p>");
			}
			
		}
	},
	
	PROMISCUITY_PILL(Util.newArrayListOfValues(
			new ListValue<>("Temporarily reduces fertility.")),
			Colour.GENERIC_SEX) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.removeStatusEffect(StatusEffect.VIXENS_VIRILITY);
			
			target.addStatusEffect(StatusEffect.PROMISCUITY_PILL, 60*24);
			
			if(target.isPlayer()) {
				return "<p>"
							+ "The little blue pill easily slides down your throat, and after only a few moments, you feel a cool throbbing sensation taking root deep within your loins."
						+ "</p>";
			
			} else {
				return UtilText.parse(target,
						"<p>"
							+ "The little blue pill easily slides down [npc.her] throat, and after only a few moments, [npc.she] feels a cool throbbing sensation taking root deep within [npc.her] loins."
						+ "</p>");
			}
		}
	},
	
	MOTHERS_MILK(Util.newArrayListOfValues(
			new ListValue<>("Advances pregnancy.")),
			Colour.GENERIC_SEX) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			if(target.isPlayer()) {
				if(target.isVisiblyPregnant()) {
					if(target.hasStatusEffect(StatusEffect.PREGNANT_3)) {
						return "<p>"
								+ "You eagerly gulp down the rich, creamy liquid. Its delicious taste overwhelms your senses, and before you know what's happening, you've already drained the entire bottle."
								+ " Seeing as you're already in the final stage of pregnancy, nothing happens, but it sure did taste good..."
								+ "</p>";
					} else {
						if(target.hasStatusEffect(StatusEffect.PREGNANT_1)) {
							target.incrementStatusEffectDuration(StatusEffect.PREGNANT_1, -(target.getStatusEffectDuration(StatusEffect.PREGNANT_1)-60));
							
						} else if(target.hasStatusEffect(StatusEffect.PREGNANT_2)) {
								target.incrementStatusEffectDuration(StatusEffect.PREGNANT_2, -(target.getStatusEffectDuration(StatusEffect.PREGNANT_2)-60));
						}
						
						return "<p>"
								+ "You eagerly gulp down the rich, creamy liquid. Its delicious taste overwhelms your senses, and before you know what's happening, you've already drained the entire bottle."
								+ " With an alarmed cry, you feel your belly swell and grow, and, rubbing your [pc.hands] down over your pregnant bump, you feel that your pregnancy has advanced..."
								+ "</p>";
					}
					
				} else {
					if(target.hasStatusEffect(StatusEffect.PREGNANT_0)) {
						target.incrementStatusEffectDuration(StatusEffect.PREGNANT_0, -(target.getStatusEffectDuration(StatusEffect.PREGNANT_0)-60));
						
						return "<p>"
									+ "You eagerly gulp down the rich, creamy liquid. Its delicious taste overwhelms your senses, and before you know what's happening, you've already drained the entire bottle."
									+ " You don't know if you're actually pregnant yet, but you start to feel a soothing warmth spreading throughout your abdomen..."
								+ "</p>";
					} else {
						return "<p>"
									+ "You eagerly gulp down the rich, creamy liquid. Its delicious taste overwhelms your senses, and before you know what's happening, you've already drained the entire bottle."
									+ " Seeing as you're not pregnant, nothing happens..."
								+ "</p>";
					}
				}
				
			} else {
				if(target.isVisiblyPregnant()) {
					if(target.hasStatusEffect(StatusEffect.PREGNANT_3)) {
						return UtilText.parse(target,
								"<p>"
									+ "[npc.Name] gulps down the rich, creamy liquid, quickly draining the entire bottle."
									+ " Seeing as [npc.she]'s already in the final stage of pregnancy, nothing happens..."
								+ "</p>");
					} else {
						if(target.hasStatusEffect(StatusEffect.PREGNANT_1)) {
							target.incrementStatusEffectDuration(StatusEffect.PREGNANT_1, -(target.getStatusEffectDuration(StatusEffect.PREGNANT_1)-60));
							
						} else if(target.hasStatusEffect(StatusEffect.PREGNANT_2)) {
								target.incrementStatusEffectDuration(StatusEffect.PREGNANT_2, -(target.getStatusEffectDuration(StatusEffect.PREGNANT_2)-60));
						}
						return UtilText.parse(target,
								"<p>"
									+ "[npc.Name] gulps down the rich, creamy liquid, quickly draining the entire bottle."
									+ " With a little cry, [npc.her] belly swells and grows, and, rubbing [npc.her] [npc.hands] down over [npc.her] pregnant bump, [npc.she] feels that [npc.her] pregnancy has advanced..."
								+ "</p>");
					}
					
				} else {
					if(target.hasStatusEffect(StatusEffect.PREGNANT_0)) {
						target.incrementStatusEffectDuration(StatusEffect.PREGNANT_0, -(target.getStatusEffectDuration(StatusEffect.PREGNANT_0)-60));

						return UtilText.parse(target,
								"<p>"
									+ "[npc.Name] gulps down the rich, creamy liquid, quickly draining the entire bottle."
									+ " Although [npc.she] don't know if [npc.she]'s actually pregnant yet, [npc.she] starts to feel a soothing warmth spreading throughout [npc.her] abdomen..."
								+ "</p>");
						
					} else {
						return UtilText.parse(target,
								"<p>"
									+ "[npc.Name] gulps down the rich, creamy liquid, quickly draining the entire bottle."
									+ " Seeing as [npc.she]'s not pregnant, nothing happens..."
								+ "</p>");
					}
				}
			}
		}
	},
	
	// Ingredients and potions:
	
	// Strength:
	
	STR_EQUINE_CIDER(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(health)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldStrength(strength)] to 'potion effects'")),
			Colour.ATTRIBUTE_STRENGTH) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return "A powerful wave of arcane energy washes over you..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.STRENGTH, 1);
		}
	},

	STR_BUBBLE_MILK(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(health)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldStrength(strength)] to 'potion effects'")),
			Colour.ATTRIBUTE_STRENGTH) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return "A powerful wave of arcane energy washes over you..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.STRENGTH, 1);
		}
	},

	STR_WOLF_WHISKEY(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(health)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldStrength(strength)] to 'potion effects'")),
			Colour.ATTRIBUTE_STRENGTH) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return "A powerful wave of arcane energy washes over you..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.STRENGTH, 1);
		}
	},
	
	// Intelligence:
	
	INT_FELINE_FANCY(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldWillpower(willpower)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldIntelligence(intelligence)] to 'potion effects'")),
			Colour.ATTRIBUTE_INTELLIGENCE) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);
			
			return "A cool wave of arcane energy washes over you..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.INTELLIGENCE, 1);
		}
	},
	
	INT_VANILLA_WATER(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldWillpower(willpower)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldIntelligence(intelligence)] to 'potion effects'")),
			Colour.ATTRIBUTE_INTELLIGENCE) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);
			
			return "A cool wave of arcane energy washes over you..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.INTELLIGENCE, 1);
		}
	},
	
	// FItness:
	
	FIT_CANINE_CRUSH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldStamina(stamina)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldFitness(fitness)] to 'potion effects'")),
			Colour.ATTRIBUTE_FITNESS) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementStamina(target.getAttributeValue(Attribute.STAMINA_MAXIMUM)/20);

			return "A soothing wave of arcane energy washes over you..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.FITNESS, 1);
		}
	},
	
	FIT_SQUIRREL_JAVA(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldStamina(stamina)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldFitness(fitness)] to 'potion effects'")),
			Colour.ATTRIBUTE_FITNESS) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementStamina(target.getAttributeValue(Attribute.STAMINA_MAXIMUM)/20);

			return "A soothing wave of arcane energy washes over you..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.FITNESS, 1);
		}
	},
	
	SEX_HARPY_PERFUME(Util.newArrayListOfValues(
			new ListValue<>("[style.boldSex(+1)] [style.boldFeminine(femininity)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldFitness(fitness)] to 'potion effects'")),
			Colour.GENERIC_SEX) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return "A soothing wave of arcane energy washes over you..."
					+ "</br>"
					+ target.incrementFemininity(1)
					+ "</br>"
					+ target.addPotionEffect(Attribute.FITNESS, 1);
		}
	},
	
	// Corruption:
	
	COR_LILITHS_GIFT(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(health)]"),
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldWillpower(willpower)]"),
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldStamina(stamina)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldCorruption(corruption)] to 'potion effects'")),
			Colour.ATTRIBUTE_CORRUPTION) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);
			target.incrementStamina(target.getAttributeValue(Attribute.STAMINA_MAXIMUM)/20);

			return "A sickly wave of corruptive arcane energy washes over you..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.CORRUPTION, 1);
		}
	},
	
	EGGPLANT(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(health)]"),
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldWillpower(willpower)]"),
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldStamina(stamina)]")),
			Colour.ATTRIBUTE_CORRUPTION) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);
			target.incrementStamina(target.getAttributeValue(Attribute.STAMINA_MAXIMUM)/20);
			
			if(target.isPlayer()) {
				return "It's kind of tasty.";
			} else {
				return "";
			}
		}
	},
	
	// Racial:
	
	RACE_INNOXIAS_GIFT(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+2)] [style.boldStrength(strength)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+2)] [style.boldIntelligence(intelligence)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+2)] [style.boldFitness(fitness)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+5)] [style.boldCorruption(corruption)] to 'potion effects'")),
			Colour.RACE_HUMAN) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return "You start to feel like this item is just for testing purposes..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.STRENGTH, 2)
					+ "</br>"
					+ target.addPotionEffect(Attribute.INTELLIGENCE, 2)
					+ "</br>"
					+ target.addPotionEffect(Attribute.FITNESS, 2)
					+ "</br>"
					+ target.addPotionEffect(Attribute.CORRUPTION, 5);
		}
	},
	
	RACE_ANGELS_TEARS(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldStrength(strength)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+1)] [style.boldIntelligence(intelligence)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+1)] [style.boldFitness(fitness)] to 'potion effects'")),
			Colour.RACE_HUMAN) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return "You start to feel a lot healthier..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.STRENGTH, 1)
					+ "</br>"
					+ target.addPotionEffect(Attribute.INTELLIGENCE, 1)
					+ "</br>"
					+ target.addPotionEffect(Attribute.FITNESS, 1);
		}
	},
	
	RACE_CANINE_CRUNCH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldFitness(fitness)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+2)] [style.boldStrength(strength)] to 'potion effects'")),
			Colour.RACE_DOG_MORPH) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return "You start to feel a lot more energetic..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.FITNESS, 3)
					+ "</br>"
					+ target.addPotionEffect(Attribute.STRENGTH, 2);
		}
	},
	
	RACE_KITTYS_REWARD(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldIntelligence(intelligence)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+2)] [style.boldFitness(fitness)] to 'potion effects'")),
			Colour.RACE_CAT_MORPH) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return "You start to feel a lot more intelligent..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.INTELLIGENCE, 3)
					+ "</br>"
					+ target.addPotionEffect(Attribute.FITNESS, 2);
		}
	},
	
	RACE_ROUND_NUTS(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldFitness(fitness)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+2)] [style.boldIntelligence(intelligence)] to 'potion effects'")),
			Colour.RACE_SQUIRREL_MORPH) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return "You start to feel a lot fitter..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.FITNESS, 3)
					+ "</br>"
					+ target.addPotionEffect(Attribute.INTELLIGENCE, 2);
		}
	},
	
	RACE_SUGAR_CARROT_CUBE(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldStrength(strength)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+2)] [style.boldFitness(fitness)] to 'potion effects'")),
			Colour.RACE_HORSE_MORPH) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return "You start to feel a lot stronger..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.STRENGTH, 3)
					+ "</br>"
					+ target.addPotionEffect(Attribute.FITNESS, 2);
		}
	},
	
	RACE_FRESH_SPROUTS(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldStrength(strength)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+2)] [style.boldFitness(fitness)] to 'potion effects'")),
			Colour.RACE_COW_MORPH) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return "You start to feel a lot stronger..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.STRENGTH, 3)
					+ "</br>"
					+ target.addPotionEffect(Attribute.FITNESS, 2);
		}
	},
	
	RACE_MEAT_AND_MARROW(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+5)] [style.boldStrength(strength)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+3)] [style.boldCorruption(corruption)] to 'potion effects'")),
			Colour.RACE_WOLF_MORPH) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return "You start to feel a lot stronger..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.STRENGTH, 5)
					+ "</br>"
					+ target.addPotionEffect(Attribute.CORRUPTION, 3);
		}
	},
	
	RACE_LOLIPOP(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+5)] [style.boldFitness(fitness)] to 'potion effects'"),
			new ListValue<>("[style.boldSex(+3)] [style.boldFeminine(femininity)]")),
			Colour.RACE_WOLF_MORPH) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return "You start to feel a lot more feminine..."
					+ "</br>"
					+ target.incrementFemininity(3)
					+ "</br>"
					+ target.addPotionEffect(Attribute.FITNESS, 5);
		}
	},
	
	// Essences:
	
//	BOTTLED_ESSENCE_ANGEL(Util.newArrayListOfValues(
//			new ListValue<>("[style.boldGood(+1)] [style.boldAngel(Angel)] essence")),
//			Colour.RACE_ANGEL) {
//		
//		@Override
//		public List<TFModifier> getPrimaryModifiers() {
//			return null;
//		}
//
//		@Override
//		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
//			return null;
//		}
//		
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
//			target.incrementEssenceCount(TFEssence.ANGEL, 1);
//			return "You have absorbed [style.boldGood(+1)] [style.boldAngel(Angel)] essence!";
//		}
//	},
	
	BOTTLED_ESSENCE_ARCANE(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence")),
			Colour.GENERIC_ARCANE) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence!";
		}
	},
	
	BOTTLED_ESSENCE_CAT_MORPH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldCat(Cat-morph)] essence")),
			Colour.RACE_CAT_MORPH) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.CAT_MORPH, 1);
			return "You have absorbed [style.boldGood(+1)] [style.boldCat(Cat-morph)] essence!";
		}
	},
	
	BOTTLED_ESSENCE_COW_MORPH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldCat(Cow-morph)] essence")),
			Colour.RACE_COW_MORPH) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.COW_MORPH, 1);
			return "You have absorbed [style.boldGood(+1)] [style.boldCat(Cow-morph)] essence!";
		}
	},

 	BOTTLED_ESSENCE_SQUIRREL_MORPH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldSquirrel(Squirrel-morph)] essence")),
			Colour.RACE_SQUIRREL_MORPH) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.SQUIRREL_MORPH, 1);
			return "You have absorbed [style.boldGood(+1)] [style.boldSquirrel(Squirrel-morph)] essence!";
		}
	},
	
	BOTTLED_ESSENCE_DEMON(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldDemon(Demon)] essence")),
			Colour.RACE_DEMON) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.DEMON, 1);
			return "You have absorbed [style.boldGood(+1)] [style.boldDemon(Demon)] essence!";
		}
	},
	
	BOTTLED_ESSENCE_DOG_MORPH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldDog(Dog-morph)] essence")),
			Colour.RACE_DOG_MORPH) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.DOG_MORPH, 1);
			return "You have absorbed [style.boldGood(+1)] [style.boldDog(Dog-morph)] essence!";
		}
	},
	
	BOTTLED_ESSENCE_HARPY(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldHarpy(Harpy)] essence")),
			Colour.RACE_HARPY) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.HARPY, 1);
			return "You have absorbed [style.boldGood(+1)] [style.boldHarpy(Harpy)] essence!";
		}
	},
	
	BOTTLED_ESSENCE_HORSE_MORPH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldHorse(Horse-morph)] essence")),
			Colour.RACE_HORSE_MORPH) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.HORSE_MORPH, 1);
			return "You have absorbed [style.boldGood(+1)] [style.boldHorse(Horse-morph)] essence!";
		}
	},
	
	BOTTLED_ESSENCE_HUMAN(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldHuman(Human)] essence")),
			Colour.RACE_HUMAN) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.HUMAN, 1);
			return "You have absorbed [style.boldGood(+1)] [style.boldHuman(Human)] essence!";
		}
	},
	
	BOTTLED_ESSENCE_WOLF_MORPH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldWolf(Wolf-morph)] essence")),
			Colour.RACE_WOLF_MORPH) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.WOLF_MORPH, 1);
			return "You have absorbed [style.boldGood(+1)] [style.boldWolf(Wolf-morph)] essence!";
		}
	},
	
	
	// Specials:
	
	BIMBO_LOLLIPOP(Util.newArrayListOfValues(
			new ListValue<>("<b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Bimbo</b> <b style='color:"+Colour.RACE_HARPY.toWebHexString()+";'>harpy</b> <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>transformation</b>")),
			Colour.RACE_HARPY) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("As the lollipop's transformative effects start to make themselves known, you start to feel very light-headed...");
			
			if(!target.hasFetish(Fetish.FETISH_BIMBO)) {
				target.addFetish(Fetish.FETISH_BIMBO);
				sb.append("</br>"
						+ "<p>"
							+ "A giggle escapes from between your [pc.lips], and you suddenly find yourself unable to think of anything other than how, like, super awesome bimbos are and stuff!"
							+ "</br><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>You have gained the bimbo fetish!</b>"
						+ "</p>");
			}
			
			// Non-racial changes
			if(target.getFemininity()<95) {
				sb.append("</br>" + target.setFemininity(95));
			}
			if(target.getBreastSize().getMeasurement()<CupSize.DD.getMeasurement()) {
				sb.append("</br>" + target.setBreastSize(CupSize.DD.getMeasurement()));
			}
			if(target.getAssSize().getValue()<AssSize.FOUR_LARGE.getValue()) {
				sb.append("</br>" + target.setAssSize(AssSize.FOUR_LARGE.getValue()));
			}
			if(target.getHipSize().getValue()<HipSize.FOUR_WOMANLY.getValue()) {
				sb.append("</br>" + target.setHipSize(HipSize.FOUR_WOMANLY.getValue()));
			}
			if(target.getHairType()!=HairType.HARPY) {
				sb.append("</br>" + target.setCovering(new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, Colour.FEATHERS_BLEACH_BLONDE, false, Colour.FEATHERS_BLEACH_BLONDE, false)));
			}
			if(target.getSkinType()!=SkinType.HARPY) {
				sb.append("</br>" + target.setCovering(new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, Colour.FEATHERS_BLEACH_BLONDE, false, Colour.FEATHERS_BLEACH_BLONDE, false)));
			}
			
			// Harpy TFs:
			if(target.getFaceType()!=FaceType.HUMAN)
				sb.append("</br>" + target.setFaceType(FaceType.HUMAN));
			if(target.getEarType()!=EarType.HARPY)
				sb.append("</br>" + target.setEarType(EarType.HARPY));
			if(target.getEyeType()!=EyeType.HARPY)
				sb.append("</br>" + target.setEyeType(EyeType.HARPY));
			if(target.getHairType()!=HairType.HARPY)
				sb.append("</br>" + target.setHairType(HairType.HARPY));
			if(target.getSkinType()!=SkinType.HUMAN)
				sb.append("</br>" + target.setSkinType(SkinType.HUMAN));

			if(target.getWingType()!=WingType.NONE)
				sb.append("</br>" + target.setWingType(WingType.NONE));
			if(target.getHornType()!=HornType.NONE)
				sb.append("</br>" + target.setHornType(HornType.NONE));

			if(target.getArmType()!=ArmType.HARPY)
				sb.append("</br>" + target.setArmType(ArmType.HARPY));
			if(target.getLegType()!=LegType.HARPY)
				sb.append("</br>" + target.setLegType(LegType.HARPY));
			if(target.getTailType()!=TailType.HARPY)
				sb.append("</br>" + target.setTailType(TailType.HARPY));

			if(target.getBreastType()!=BreastType.HARPY)
				sb.append("</br>" + target.setBreastType(BreastType.HARPY));
			if(target.getAssType()!=AssType.HARPY)
				sb.append("</br>" + target.setAssType(AssType.HARPY));

			if(target.hasPenis()) {
				sb.append("</br>" + target.setPenisType(PenisType.AVIAN));
			}
			if(target.hasVagina()) {
				sb.append("</br>" + target.setVaginaType(VaginaType.HARPY));
			}
			
			return sb.toString();
		}
	},
	
	NYMPHO_LOLLIPOP(Util.newArrayListOfValues(
			new ListValue<>("<b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Nympho</b> <b style='color:"+Colour.RACE_HARPY.toWebHexString()+";'>harpy</b> <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>transformation</b>")),
			Colour.RACE_HARPY) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("As the lollipop's transformative effects start to make themselves known, you start to feel very light-headed...");
			
			if(!target.hasPerk(Perk.NYMPHOMANIAC)) {
				target.addPerk(Perk.NYMPHOMANIAC);
				sb.append("</br>"
						+ "<p>"
							+ "A desperate moan escapes from between your [pc.lips], and you suddenly find yourself unable to think of anything other than sex, sex, and more sex!"
							+ "</br><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>You have gained the nymphomaniac perk!</b>"
						+ "</p>");
			}
			
			// Non-racial changes
			if(target.getFemininity()<95) {
				sb.append("</br>" + target.setFemininity(95));
			}
			if(target.getBreastSize().getMeasurement()<CupSize.C.getMeasurement()) {
				sb.append("</br>" + target.setBreastSize(CupSize.C.getMeasurement()));
			}
			if(target.getAssSize().getValue()<AssSize.THREE_NORMAL.getValue()) {
				sb.append("</br>" + target.setAssSize(AssSize.THREE_NORMAL.getValue()));
			}
			if(target.getHipSize().getValue()<HipSize.THREE_GIRLY.getValue()) {
				sb.append("</br>" + target.setHipSize(HipSize.THREE_GIRLY.getValue()));
			}
			if(target.getHairType()!=HairType.HARPY) {
				sb.append("</br>" + target.setCovering(new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, Colour.FEATHERS_PINK, false, Colour.FEATHERS_PINK, false)));
			}
			if(target.getSkinType()!=SkinType.HARPY) {
				sb.append("</br>" + target.setCovering(new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, Colour.FEATHERS_PINK, false, Colour.FEATHERS_PINK, false)));
			}
			
			// Harpy TFs:
			if(target.getFaceType()!=FaceType.HUMAN)
				sb.append("</br>" + target.setFaceType(FaceType.HUMAN));
			if(target.getEarType()!=EarType.HARPY)
				sb.append("</br>" + target.setEarType(EarType.HARPY));
			if(target.getEyeType()!=EyeType.HARPY)
				sb.append("</br>" + target.setEyeType(EyeType.HARPY));
			if(target.getHairType()!=HairType.HARPY)
				sb.append("</br>" + target.setHairType(HairType.HARPY));
			if(target.getSkinType()!=SkinType.HUMAN)
				sb.append("</br>" + target.setSkinType(SkinType.HUMAN));

			if(target.getWingType()!=WingType.NONE)
				sb.append("</br>" + target.setWingType(WingType.NONE));
			if(target.getHornType()!=HornType.NONE)
				sb.append("</br>" + target.setHornType(HornType.NONE));

			if(target.getArmType()!=ArmType.HARPY)
				sb.append("</br>" + target.setArmType(ArmType.HARPY));
			if(target.getLegType()!=LegType.HARPY)
				sb.append("</br>" + target.setLegType(LegType.HARPY));
			if(target.getTailType()!=TailType.HARPY)
				sb.append("</br>" + target.setTailType(TailType.HARPY));

			if(target.getBreastType()!=BreastType.HARPY)
				sb.append("</br>" + target.setBreastType(BreastType.HARPY));
			if(target.getAssType()!=AssType.HARPY)
				sb.append("</br>" + target.setAssType(AssType.HARPY));
			if(target.getAssWetness().getValue()<Wetness.TWO_MOIST.getValue())
				sb.append("</br>" + target.setAssWetness(Wetness.TWO_MOIST.getValue()));
				

			if(target.hasPenis()) {
				sb.append("</br>" + target.setPenisType(PenisType.AVIAN));

				if(target.getPenisRawCumProductionValue()<CumProduction.THREE_AVERAGE.getMedianValue())
					sb.append("</br>" + target.setCumProduction(CumProduction.THREE_AVERAGE.getMedianValue()));
			}
			if(target.hasVagina()) {
				sb.append("</br>" + target.setVaginaType(VaginaType.HARPY));

				if(target.getVaginaWetness().getValue()<Wetness.FOUR_SLIMY.getValue())
					sb.append("</br>" + target.setVaginaWetness(Wetness.FOUR_SLIMY.getValue()));
			}
			
			return sb.toString();
		}
	},
	
	DOMINANT_PERFUME(Util.newArrayListOfValues(
			new ListValue<>("<b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Dominant</b> <b style='color:"+Colour.RACE_HARPY.toWebHexString()+";'>harpy</b> <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>transformation</b>")),
			Colour.RACE_HARPY) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("As the perfume's transformative effects start to make themselves known, you start to feel very light-headed...");
			
			if(!target.hasFetish(Fetish.FETISH_DOMINANT)) {
				target.addFetish(Fetish.FETISH_DOMINANT);
				sb.append("</br>"
						+ "<p>"
							+ "A deep groan escapes from between your [pc.lips], and you suddenly find yourself thinking of how much you want to dominate the next person you come across!"
							+ "</br><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>You have gained the dominant fetish!</b>"
						+ "</p>");
			}
			
			// Non-racial changes
			if(target.getFemininity()<95) {
				sb.append("</br>" + target.setFemininity(95));
			}
			if(target.getBreastSize().getMeasurement()<CupSize.C.getMeasurement()) {
				sb.append("</br>" + target.setBreastSize(CupSize.C.getMeasurement()));
			}
			if(target.getAssSize().getValue()<AssSize.THREE_NORMAL.getValue()) {
				sb.append("</br>" + target.setAssSize(AssSize.THREE_NORMAL.getValue()));
			}
			if(target.getHipSize().getValue()<HipSize.THREE_GIRLY.getValue()) {
				sb.append("</br>" + target.setHipSize(HipSize.THREE_GIRLY.getValue()));
			}
			if(target.getHairType()!=HairType.HARPY) {
				sb.append("</br>" + target.setCovering(new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, Colour.FEATHERS_BLACK, false, Colour.FEATHERS_BLACK, false)));
			}
			if(target.getSkinType()!=SkinType.HARPY) {
				sb.append("</br>" + target.setCovering(new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, Colour.FEATHERS_RED, false, Colour.FEATHERS_RED, false)));
			}
			
			// Harpy TFs:
			if(target.getFaceType()!=FaceType.HUMAN)
				sb.append("</br>" + target.setFaceType(FaceType.HUMAN));
			if(target.getEarType()!=EarType.HARPY)
				sb.append("</br>" + target.setEarType(EarType.HARPY));
			if(target.getEyeType()!=EyeType.HARPY)
				sb.append("</br>" + target.setEyeType(EyeType.HARPY));
			if(target.getHairType()!=HairType.HARPY)
				sb.append("</br>" + target.setHairType(HairType.HARPY));
			if(target.getSkinType()!=SkinType.HUMAN)
				sb.append("</br>" + target.setSkinType(SkinType.HUMAN));

			if(target.getWingType()!=WingType.NONE)
				sb.append("</br>" + target.setWingType(WingType.NONE));
			if(target.getHornType()!=HornType.NONE)
				sb.append("</br>" + target.setHornType(HornType.NONE));

			if(target.getArmType()!=ArmType.HARPY)
				sb.append("</br>" + target.setArmType(ArmType.HARPY));
			if(target.getLegType()!=LegType.HARPY)
				sb.append("</br>" + target.setLegType(LegType.HARPY));
			if(target.getTailType()!=TailType.HARPY)
				sb.append("</br>" + target.setTailType(TailType.HARPY));

			if(target.getBreastType()!=BreastType.HARPY)
				sb.append("</br>" + target.setBreastType(BreastType.HARPY));
			if(target.getAssType()!=AssType.HARPY)
				sb.append("</br>" + target.setAssType(AssType.HARPY));
				

			if(target.hasPenis()) {
				sb.append("</br>" + target.setPenisType(PenisType.AVIAN));
			}
			if(target.hasVagina()) {
				sb.append("</br>" + target.setVaginaType(VaginaType.HARPY));
			}
			
			return sb.toString();
		}
	},
	
	// Enchantment effects:
	
	ATTRIBUTE_STRENGTH(null,
			Colour.ATTRIBUTE_STRENGTH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFModStrengthList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return TFModifier.getTFModNegPosList();
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return genericAttributeEffectDescription(resourceRestoration.HEALTH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return genericAttributeEffect(resourceRestoration.HEALTH, primaryModifier, secondaryModifier, user, target);
		}
	},
	
	ATTRIBUTE_INTELLIGENCE(null,
			Colour.ATTRIBUTE_INTELLIGENCE) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFModIntelligenceList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return TFModifier.getTFModNegPosList();
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return genericAttributeEffectDescription(resourceRestoration.WILLPOWER, primaryModifier, secondaryModifier);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return genericAttributeEffect(resourceRestoration.WILLPOWER, primaryModifier, secondaryModifier, user, target);
		}
	},
	
	ATTRIBUTE_FITNESS(null,
			Colour.ATTRIBUTE_FITNESS) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFModFitnessList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return TFModifier.getTFModNegPosList();
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return genericAttributeEffectDescription(resourceRestoration.STAMINA, primaryModifier, secondaryModifier);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return genericAttributeEffect(resourceRestoration.STAMINA, primaryModifier, secondaryModifier, user, target);
		}
	},
	
	ATTRIBUTE_SEXUAL(null,
			Colour.GENERIC_SEX) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFModSexualList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return TFModifier.getTFModNegPosList();
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return genericAttributeEffectDescription(resourceRestoration.STAMINA, primaryModifier, secondaryModifier);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return genericAttributeEffect(resourceRestoration.STAMINA, primaryModifier, secondaryModifier, user, target);
		}
	},
	
	ATTRIBUTE_CORRUPTION(null,
			Colour.ATTRIBUTE_CORRUPTION) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFModCorruptionList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return TFModifier.getTFModNegPosList();
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return genericAttributeEffectDescription(resourceRestoration.ALL, primaryModifier, secondaryModifier);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return genericAttributeEffect(resourceRestoration.ALL, primaryModifier, secondaryModifier, user, target);
		}
	},
	
	// RACIAL:
	
	RACE_DEMON(null,
			Colour.RACE_DEMON) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsListList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialEffectDescription(Race.DEMON, primaryModifier, secondaryModifier);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.DEMON, primaryModifier, secondaryModifier, user, target);
		}
	},
	
	RACE_HUMAN(null,
			Colour.RACE_HUMAN) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsListList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialEffectDescription(Race.HUMAN, primaryModifier, secondaryModifier);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.HUMAN, primaryModifier, secondaryModifier, user, target);
		}
	},
	
	RACE_CAT_MORPH(null,
			Colour.RACE_CAT_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsListList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialEffectDescription(Race.CAT_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.CAT_MORPH, primaryModifier, secondaryModifier, user, target);
		}
	},
	
	RACE_COW_MORPH(null,
			Colour.RACE_COW_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsListList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialEffectDescription(Race.COW_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.COW_MORPH, primaryModifier, secondaryModifier, user, target);
		}
	},
	
	RACE_SQUIRREL_MORPH(null,
			Colour.RACE_SQUIRREL_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsListList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialEffectDescription(Race.SQUIRREL_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.SQUIRREL_MORPH, primaryModifier, secondaryModifier, user, target);
		}
	},
	
	RACE_DOG_MORPH(null,
			Colour.RACE_DOG_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsListList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialEffectDescription(Race.DOG_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.DOG_MORPH, primaryModifier, secondaryModifier, user, target);
		}
	},
	
	RACE_HORSE_MORPH(null,
			Colour.RACE_HORSE_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsListList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialEffectDescription(Race.HORSE_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.HORSE_MORPH, primaryModifier, secondaryModifier, user, target);
		}
	},
	
	RACE_WOLF_MORPH(null,
			Colour.RACE_WOLF_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsListList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialEffectDescription(Race.WOLF_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.WOLF_MORPH, primaryModifier, secondaryModifier, user, target);
		}
	},
	
	RACE_HARPY(null,
			Colour.RACE_HARPY) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsListList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialEffectDescription(Race.HARPY, primaryModifier, secondaryModifier);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.HARPY, primaryModifier, secondaryModifier, user, target);
		}
	},;
	
	
	private List<String> effectsDescriptions;
	private Colour colour;
	
	private ItemEffectType(List<String> effectsDescriptions, Colour colour) {
		this.effectsDescriptions = effectsDescriptions;
		this.colour = colour;
	}
	
	public Colour getColour() {
		return colour;
	}


	public abstract List<TFModifier> getPrimaryModifiers();
	
	public abstract List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier);
	
	public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier) {
		return effectsDescriptions;
	}
	
	public abstract String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target);
	
	private enum resourceRestoration {
		HEALTH,
		WILLPOWER,
		STAMINA,
		ALL;
	}

	private static List<String> descriptions = new ArrayList<>();
	private static List<String> genericAttributeEffectDescription(resourceRestoration restorationType, TFModifier primaryModifier, TFModifier secondaryModifier) {
		descriptions.clear();
		
		switch(secondaryModifier) {
			case POSITIVE_WEAK:
				if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
					addResourceDescriptionsRestore(20, restorationType);
				} else {
					if(primaryModifier.getAssociatedAttribute()!=null) {
						descriptions.add("[style.boldGood(+5)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b> to 'potion effects'");
					}
				}
				break;
			case POSITIVE_AVERAGE:
				if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
					addResourceDescriptionsRestore(30, restorationType);
				} else {
					if(primaryModifier.getAssociatedAttribute()!=null) {
						descriptions.add("[style.boldExcellent(+0.5)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b>");
					}
				}
				break;
			case POSITIVE_STRONG:
				if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
					addResourceDescriptionsRestore(40, restorationType);
				} else {
					if(primaryModifier.getAssociatedAttribute()!=null) {
						descriptions.add("[style.boldExcellent(+1)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b>");
					}
				}
				break;
		
			case NEGATIVE_WEAK:
				if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
					addResourceDescriptionsDrain(10, restorationType);
				} else {
					if(primaryModifier.getAssociatedAttribute()!=null) {
						descriptions.add("[style.boldBad(-5)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b> to 'potion effects'");
					}
				}
				break;
			case NEGATIVE_AVERAGE:
				if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
					addResourceDescriptionsDrain(20, restorationType);
				} else {
					if(primaryModifier.getAssociatedAttribute()!=null) {
						descriptions.add("[style.boldTerrible(-0.5)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b>");
					}
				}
				break;
			case NEGATIVE_STRONG:
				if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
					addResourceDescriptionsDrain(30, restorationType);
				} else {
					if(primaryModifier.getAssociatedAttribute()!=null) {
						descriptions.add("[style.boldTerrible(-1)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b>");
					}
				}
				break;
			
			default:
				if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
					addResourceDescriptionsRestore(10, restorationType);
					
				} else {
					if(primaryModifier.getAssociatedAttribute()!=null) {
						descriptions.add("[style.boldGood(+1)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b> to 'potion effects'");
					}
				}
				break;
		}
		
		return descriptions;
	}
	
	private static void addResourceDescriptionsRestore(int value, resourceRestoration restorationType) {
		switch(restorationType){
			case HEALTH:
					descriptions.add("[style.boldGood(Restores)] "+value+"% [style.boldHealth(health)]");
				break;
			case STAMINA:
					descriptions.add("[style.boldGood(Restores)] "+value+"% [style.boldStamina(stamina)]");
				break;
			case WILLPOWER:
					descriptions.add("[style.boldGood(Restores)] "+value+"% [style.boldWillpower(willpower)]");
				break;
			case ALL:
					descriptions.add("[style.boldGood(Restores)] "+value+"% [style.boldHealth(health)]");
					descriptions.add("[style.boldGood(Restores)] "+value+"% [style.boldStamina(stamina)]");
					descriptions.add("[style.boldGood(Restores)] "+value+"% [style.boldWillpower(willpower)]");
				break;
		}
	}
	private static void addResourceDescriptionsDrain(int value, resourceRestoration restorationType) {
		switch(restorationType){
			case HEALTH:
					descriptions.add("[style.boldBad(Drains)] "+value+"% [style.boldHealth(health)]");
				break;
			case STAMINA:
					descriptions.add("[style.boldBad(Drains)] "+value+"% [style.boldStamina(stamina)]");
				break;
			case WILLPOWER:
					descriptions.add("[style.boldBad(Drains)] "+value+"% [style.boldWillpower(willpower)]");
				break;
			case ALL:
					descriptions.add("[style.boldBad(Drains)] "+value+"% [style.boldHealth(health)]");
					descriptions.add("[style.boldBad(Drains)] "+value+"% [style.boldStamina(stamina)]");
					descriptions.add("[style.boldBad(Drains)] "+value+"% [style.boldWillpower(willpower)]");
				break;
		}
	}
	
	private static void applyRestoration(GameCharacter target, resourceRestoration restorationType, float percentage) {
		switch(restorationType) {
			case ALL:
				target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*percentage);
				target.incrementStamina(target.getAttributeValue(Attribute.STAMINA_MAXIMUM)*percentage);
				target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*percentage);
				break;
			case HEALTH:
				target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*percentage);
				break;
			case STAMINA:
				target.incrementStamina(target.getAttributeValue(Attribute.STAMINA_MAXIMUM)*percentage);
				break;
			case WILLPOWER:
				target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*percentage);
				break;
		}
	}
	
	private static String genericAttributeEffect(resourceRestoration restorationType, TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
		
		switch(secondaryModifier) {
			case POSITIVE_WEAK:
				if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
					applyRestoration(target, restorationType, 0.2f);
					
					if(target.isPlayer()) {
						return "A soothing warmth spreads all throughout your body, and with a deeply satisfied sigh, you find yourself feeling a lot healthier than you did just a moment ago.";
					} else {
						return UtilText.parse(target, "[npc.Name] lets out a satisfied sigh, and you notice that [npc.she]'s suddenly looking a lot healthier than [npc.she] did just a moment ago.");
					}
					
				} else {
					if(primaryModifier.getAssociatedAttribute()!=null) {
						return "A warm wave of arcane energy washes over you..."
								+ "</br>"
								+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), 5);
					}
				}
				break;
			case POSITIVE_AVERAGE:
				if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
					applyRestoration(target, restorationType, 0.3f);
					if(target.isPlayer()) {
						return "A soothing warmth spreads all throughout your body, and with a deeply satisfied sigh, you find yourself feeling a lot healthier than you did just a moment ago.";
					} else {
						return UtilText.parse(target, "[npc.Name] lets out a satisfied sigh, and you notice that [npc.she]'s suddenly looking a lot healthier than [npc.she] did just a moment ago.");
					}
					
				} else {
					if(primaryModifier.getAssociatedAttribute()!=null) {
						return target.incrementAttribute(primaryModifier.getAssociatedAttribute(), 0.5f);
					}
				}
				break;
			case POSITIVE_STRONG:
				if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
					applyRestoration(target, restorationType, 0.4f);
					if(target.isPlayer()) {
						return "A soothing warmth spreads all throughout your body, and with a deeply satisfied sigh, you find yourself feeling a lot healthier than you did just a moment ago.";
					} else {
						return UtilText.parse(target, "[npc.Name] lets out a satisfied sigh, and you notice that [npc.she]'s suddenly looking a lot healthier than [npc.she] did just a moment ago.");
					}
					
				} else {
					if(primaryModifier.getAssociatedAttribute()!=null) {
						return target.incrementAttribute(primaryModifier.getAssociatedAttribute(), 1);
					}
				}
				break;
		
			case NEGATIVE_WEAK:
				if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
					applyRestoration(target, restorationType, -0.1f);
					if(target.isPlayer()) {
						return "A sickly warmth creeps its way all throughout your body, and with a feeble cough, you find yourself feeling weaker than you did just a moment ago.";
					} else {
						return UtilText.parse(target, "[npc.Name] lets out a feeble cough, and you notice that [npc.she]'s suddenly looking a little weaker than [npc.she] did just a moment ago.");
					}
				} else {
					if(primaryModifier.getAssociatedAttribute()!=null) {
						return "A warm wave of arcane energy washes over you..."
								+ "</br>"
								+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), -5);
					}
				}
				break;
			case NEGATIVE_AVERAGE:
				if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
					applyRestoration(target, restorationType, -0.2f);
					if(target.isPlayer()) {
						return "A sickly warmth creeps its way all throughout your body, and with a feeble cough, you find yourself feeling weaker than you did just a moment ago.";
					} else {
						return UtilText.parse(target, "[npc.Name] lets out a feeble cough, and you notice that [npc.she]'s suddenly looking a little weaker than [npc.she] did just a moment ago.");
					}
				} else {
					if(primaryModifier.getAssociatedAttribute()!=null) {
						return target.incrementAttribute(primaryModifier.getAssociatedAttribute(), -0.5f);
					}
				}
				break;
			case NEGATIVE_STRONG:
				if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
					applyRestoration(target, restorationType, -0.3f);
					if(target.isPlayer()) {
						return "A sickly warmth creeps its way all throughout your body, and with a feeble cough, you find yourself feeling weaker than you did just a moment ago.";
					} else {
						return UtilText.parse(target, "[npc.Name] lets out a feeble cough, and you notice that [npc.she]'s suddenly looking a little weaker than [npc.she] did just a moment ago.");
					}
				} else {
					if(primaryModifier.getAssociatedAttribute()!=null) {
						return target.incrementAttribute(primaryModifier.getAssociatedAttribute(), -1);
					}
				}
				break;
			
			default:
				if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
					applyRestoration(target, restorationType, 0.1f);
					
					if(target.isPlayer()) {
						return "A soothing warmth spreads all throughout your body, and with a deeply satisfied sigh, you find yourself feeling a lot healthier than you did just a moment ago.";
					} else {
						return UtilText.parse(target, "[npc.Name] lets out a satisfied sigh, and you notice that [npc.she]'s suddenly looking a lot healthier than [npc.she] did just a moment ago.");
					}
				} else {
					if(primaryModifier.getAssociatedAttribute()!=null) {
						return "A warm wave of arcane energy washes over you..."
								+ "</br>"
								+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), 1);
					}
				}
				break;
		}
		
		return "";
	}
	
	private static List<TFModifier> getRacialSecondaryModifiers(TFModifier primaryModifier) {
		List<TFModifier> secondaryModifiers = new ArrayList<>();
		secondaryModifiers.add(TFModifier.NONE);
		
		switch(primaryModifier) {
			case TF_ARMS:
				break;
				
			case TF_ASS:
				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_HIP_SIZE_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_HIP_SIZE_DECREASE);

				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_INCREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_DECREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_INCREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_DECREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_INCREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_DECREASE_STRONG);
				break;
				
			case TF_BREASTS:
				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_BREAST_ROWS_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_BREAST_ROWS_DECREASE);
				
				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_INCREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_DECREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_INCREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_DECREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_INCREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_DECREASE_STRONG);
				break;
				
			case TF_CORE:
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK);
				secondaryModifiers.add(TFModifier.TF_MOD_FEMININITY_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_FEMININITY_INCREASE);
				
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_FEMININITY_DECREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_FEMININITY_INCREASE_STRONG);
				break;
				
			case TF_EARS:
				break;
				
			case TF_EYES:
				break;
				
			case TF_FACE:
				break;
				
			case TF_HAIR:
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK);

				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK_STRONG);
				break;
				
			case TF_HORNS:
				break;
				
			case TF_LEGS:
				break;
				
			case TF_PENIS:
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_TESTICLE_SIZE_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_TESTICLE_SIZE_DECREASE);
				secondaryModifiers.add(TFModifier.REMOVAL);

				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_INCREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_DECREASE_STRONG);
				break;
				
			case TF_SKIN:
				break;
				
			case TF_TAIL:
				break;
				
			case TF_VAGINA:
				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_DECREASE);
				secondaryModifiers.add(TFModifier.REMOVAL);

				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_INCREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_DECREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_INCREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_DECREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_INCREASE_STRONG);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_DECREASE_STRONG);
				break;
				
			case TF_WINGS:
				break;
				
			default:
				break;
		}
		
		return secondaryModifiers;
	}
	
	private static List<String> getRacialEffectDescription(Race race, TFModifier primaryModifier, TFModifier secondaryModifier) {
		descriptions.clear();
		
		switch(primaryModifier) {
			case TF_ARMS:
				descriptions.add(Util.capitaliseSentence(race.getName())+" arm transformation.");
				break;
				
			case TF_ASS:
				switch(secondaryModifier) {
					case TF_MOD_CAPACITY_DECREASE:
						descriptions.add("Normal decrease to ass capacity.");
						break;
					case TF_MOD_CAPACITY_INCREASE:
						descriptions.add("Normal increase to ass capacity.");
						break;
					case TF_MOD_ELASTICITY_DECREASE:
						descriptions.add("Normal decrease to ass elasticity.");
						break;
					case TF_MOD_ELASTICITY_INCREASE:
						descriptions.add("Normal increase to ass elasticity.");
						break;
					case TF_MOD_SIZE_GROW:
						descriptions.add("Normal increase to ass size.");
						break;
					case TF_MOD_SIZE_SHRINK:
						descriptions.add("Normal decrease to ass size.");
						break;
					case TF_MOD_WETNESS_DECREASE:
						descriptions.add("Normal decrease to ass wetness.");
						break;
					case TF_MOD_WETNESS_INCREASE:
						descriptions.add("Normal increase to ass wetness.");
						break;
					case TF_MOD_HIP_SIZE_DECREASE:
						descriptions.add("Normal decrease to hip size.");
						break;
					case TF_MOD_HIP_SIZE_INCREASE:
						descriptions.add("Normal increase to hip size.");
						break;
						
					case TF_MOD_CAPACITY_DECREASE_STRONG:
						descriptions.add("Large decrease to ass capacity.");
						break;
					case TF_MOD_CAPACITY_INCREASE_STRONG:
						descriptions.add("Large increase to ass capacity.");
						break;
					case TF_MOD_ELASTICITY_DECREASE_STRONG:
						descriptions.add("Large decrease to ass elasticity.");
						break;
					case TF_MOD_ELASTICITY_INCREASE_STRONG:
						descriptions.add("Large increase to ass elasticity.");
						break;
					case TF_MOD_SIZE_GROW_STRONG:
						descriptions.add("Large increase to ass size.");
						break;
					case TF_MOD_SIZE_SHRINK_STRONG:
						descriptions.add("Large decrease to ass size.");
						break;
					case TF_MOD_WETNESS_DECREASE_STRONG:
						descriptions.add("Large decrease to ass wetness.");
						break;
					case TF_MOD_WETNESS_INCREASE_STRONG:
						descriptions.add("Large increase to ass wetness.");
						break;
						
					default:
						descriptions.add(Util.capitaliseSentence(race.getName())+" ass transformation.");
						break;
				}
				break;
				
			case TF_BREASTS:
				switch(secondaryModifier) {
					case TF_MOD_CAPACITY_DECREASE:
						descriptions.add("Normal decrease to breast capacity.");
						break;
					case TF_MOD_CAPACITY_INCREASE:
						descriptions.add("Normal increase to breast capacity.");
						break;
					case TF_MOD_ELASTICITY_DECREASE:
						descriptions.add("Normal decrease to breast elasticity.");
						break;
					case TF_MOD_ELASTICITY_INCREASE:
						descriptions.add("Normal increase to breast elasticity.");
						break;
					case TF_MOD_SIZE_GROW:
						descriptions.add("Normal increase to breast size.");
						break;
					case TF_MOD_SIZE_SHRINK:
						descriptions.add("Normal decrease to breast size.");
						break;
					case TF_MOD_WETNESS_DECREASE:
						descriptions.add("Normal decrease to lactation.");
						break;
					case TF_MOD_WETNESS_INCREASE:
						descriptions.add("Normal increase to lactation.");
						break;
					case TF_MOD_BREAST_ROWS_DECREASE:
						descriptions.add("Removes a breast row.");
						break;
					case TF_MOD_BREAST_ROWS_INCREASE:
						descriptions.add("Adds a breast row.");
						break;
						
					case TF_MOD_CAPACITY_DECREASE_STRONG:
						descriptions.add("Large decrease to breast capacity.");
						break;
					case TF_MOD_CAPACITY_INCREASE_STRONG:
						descriptions.add("Large increase to breast capacity.");
						break;
					case TF_MOD_ELASTICITY_DECREASE_STRONG:
						descriptions.add("Large decrease to breast elasticity.");
						break;
					case TF_MOD_ELASTICITY_INCREASE_STRONG:
						descriptions.add("Large increase to breast elasticity.");
						break;
					case TF_MOD_SIZE_GROW_STRONG:
						descriptions.add("Large increase to breast size.");
						break;
					case TF_MOD_SIZE_SHRINK_STRONG:
						descriptions.add("Large decrease to breast size.");
						break;
					case TF_MOD_WETNESS_DECREASE_STRONG:
						descriptions.add("Large decrease to lactation.");
						break;
					case TF_MOD_WETNESS_INCREASE_STRONG:
						descriptions.add("Large increase to lactation.");
						break;
						
					default:
						descriptions.add(Util.capitaliseSentence(race.getName())+" breast transformation.");
						break;
				}
				break;
				
			case TF_CORE: 
				switch(secondaryModifier) {
					case TF_MOD_SIZE_GROW:
						descriptions.add("Normal increase to height.");
						break;
					case TF_MOD_SIZE_SHRINK:
						descriptions.add("Normal decrease to height.");
						break;
					case TF_MOD_FEMININITY_INCREASE:
						descriptions.add("Normal increase to femininity.");
						break;
					case TF_MOD_FEMININITY_DECREASE:
						descriptions.add("Normal increase to masculinity.");
						break;
						
					case TF_MOD_SIZE_GROW_STRONG:
						descriptions.add("Large increase to height.");
						break;
					case TF_MOD_SIZE_SHRINK_STRONG:
						descriptions.add("Large decrease to height.");
						break;
					case TF_MOD_FEMININITY_INCREASE_STRONG:
						descriptions.add("Large increase to femininity.");
						break;
					case TF_MOD_FEMININITY_DECREASE_STRONG:
						descriptions.add("Large increase to masculinity.");
						break;
						
					default:
						descriptions.add("Random "+race.getName()+" transformation.");
						break;
				}
				break;
				
			case TF_EARS:
				descriptions.add(Util.capitaliseSentence(race.getName())+" ears transformation.");
				break;
				
			case TF_EYES:
				descriptions.add(Util.capitaliseSentence(race.getName())+" eyes transformation.");
				break;
				
			case TF_FACE:
				descriptions.add(Util.capitaliseSentence(race.getName())+" face transformation.");
				break;
				
			case TF_HAIR:
				switch(secondaryModifier) {
					case TF_MOD_SIZE_GROW:
						descriptions.add("Normal increase to hair length.");
						break;
					case TF_MOD_SIZE_SHRINK:
						descriptions.add("Normal decrease to hair length.");
						break;
						
					case TF_MOD_SIZE_GROW_STRONG:
						descriptions.add("Large increase to hair length.");
						break;
					case TF_MOD_SIZE_SHRINK_STRONG:
						descriptions.add("Large decrease to hair length.");
						break;
						
					default:
						descriptions.add(Util.capitaliseSentence(race.getName())+" hair transformation.");
						break;
				}
				break;
				
			case TF_HORNS:
				if(RacialBody.valueOfRace(race).getHornTypeMale() == HornType.NONE && RacialBody.valueOfRace(race).getHornTypeFemale() == HornType.NONE) {
					descriptions.add("Removes horns.");
					
				} else if(RacialBody.valueOfRace(race).getHornTypeFemale() == HornType.NONE) {
					descriptions.add("Removes horns if feminine.");
					descriptions.add(Util.capitaliseSentence(race.getName())+" horns transformation if masculine.");
					
				} else if(RacialBody.valueOfRace(race).getHornTypeMale() == HornType.NONE) {
					descriptions.add(Util.capitaliseSentence(race.getName())+" horns transformation if feminine.");
					descriptions.add("Removes horns if masculine.");
					
				} else {
					descriptions.add(Util.capitaliseSentence(race.getName())+" horns transformation.");
				}
				break;
				
			case TF_LEGS:
				descriptions.add(Util.capitaliseSentence(race.getName())+" legs transformation.");
				break;
				
			case TF_PENIS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE_GROW:
						descriptions.add("Normal increase to penis size.");
						break;
					case TF_MOD_SIZE_SHRINK:
						descriptions.add("Normal decrease to penis size.");
						break;
					case TF_MOD_WETNESS_DECREASE:
						descriptions.add("Normal decrease to cum production.");
						break;
					case TF_MOD_WETNESS_INCREASE:
						descriptions.add("Normal increase to cum production.");
						break;
					case TF_MOD_TESTICLE_SIZE_INCREASE:
						descriptions.add("Normal increase to testicle size.");
						break;
					case TF_MOD_TESTICLE_SIZE_DECREASE:
						descriptions.add("Normal decrease to testicle size.");
						break;
					case REMOVAL:
						descriptions.add("Removes penis.");
						break;
						
					case TF_MOD_SIZE_GROW_STRONG:
						descriptions.add("Large increase to penis size.");
						break;
					case TF_MOD_SIZE_SHRINK_STRONG:
						descriptions.add("Large decrease to penis size.");
						break;
					case TF_MOD_WETNESS_DECREASE_STRONG:
						descriptions.add("Large decrease to cum production.");
						break;
					case TF_MOD_WETNESS_INCREASE_STRONG:
						descriptions.add("Large increase to cum production.");
						break;
						
					default:
						descriptions.add(Util.capitaliseSentence(race.getName())+" penis transformation.");
						break;
				}
				break;
				
			case TF_SKIN:
				descriptions.add(Util.capitaliseSentence(race.getName())+" skin transformation.");
				break;
				
			case TF_TAIL:
				if(RacialBody.valueOfRace(race).getTailType() == TailType.NONE) {
					descriptions.add("Removes tail.");
				} else {
					descriptions.add(Util.capitaliseSentence(race.getName())+" tail transformation.");
				}
				break;
				
			case TF_VAGINA:
				switch(secondaryModifier) {
					case TF_MOD_CAPACITY_DECREASE:
						descriptions.add("Normal decrease to vagina capacity.");
						break;
					case TF_MOD_CAPACITY_INCREASE:
						descriptions.add("Normal increase to vagina capacity.");
						break;
					case TF_MOD_ELASTICITY_DECREASE:
						descriptions.add("Normal decrease to vagina elasticity.");
						break;
					case TF_MOD_ELASTICITY_INCREASE:
						descriptions.add("Normal increase to vagina elasticity.");
						break;
					case TF_MOD_SIZE_GROW:
						descriptions.add("Normal increase to clit size.");
						break;
					case TF_MOD_SIZE_SHRINK:
						descriptions.add("Normal decrease to clit size.");
						break;
					case TF_MOD_WETNESS_DECREASE:
						descriptions.add("Normal decrease to vagina wetness.");
						break;
					case TF_MOD_WETNESS_INCREASE:
						descriptions.add("Normal increase to vagina wetness.");
						break;
					case REMOVAL:
						descriptions.add("Removes vagina.");
						break;
						
					case TF_MOD_CAPACITY_DECREASE_STRONG:
						descriptions.add("Large decrease to vagina capacity.");
						break;
					case TF_MOD_CAPACITY_INCREASE_STRONG:
						descriptions.add("Large increase to vagina capacity.");
						break;
					case TF_MOD_ELASTICITY_DECREASE_STRONG:
						descriptions.add("Large decrease to vagina elasticity.");
						break;
					case TF_MOD_ELASTICITY_INCREASE_STRONG:
						descriptions.add("Large increase to vagina elasticity.");
						break;
					case TF_MOD_SIZE_GROW_STRONG:
						descriptions.add("Large increase to clit size.");
						break;
					case TF_MOD_SIZE_SHRINK_STRONG:
						descriptions.add("Large decrease to clit size.");
						break;
					case TF_MOD_WETNESS_DECREASE_STRONG:
						descriptions.add("Large decrease to vagina wetness.");
						break;
					case TF_MOD_WETNESS_INCREASE_STRONG:
						descriptions.add("Large increase to vagina wetness.");
						break;
						
					default:
						descriptions.add(Util.capitaliseSentence(race.getName())+" vagina transformation.");
						break;
				}
				break;
				
			case TF_WINGS:
				if(RacialBody.valueOfRace(race).getWingType() == WingType.NONE) {
					descriptions.add("Removes wings.");
				} else {
					descriptions.add(Util.capitaliseSentence(race.getName())+" wings transformation.");
				}
				break;
				
			default:
				descriptions.add("Random non-racial transformation.");
				break;
		}
		
		return descriptions;
	}
	
	private static String getRacialEffect(Race race, TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
		
		int normalValue = 1, largeValue = 5;
		
		switch(primaryModifier) {
			case TF_ARMS:
				return target.setArmType(RacialBody.valueOfRace(race).getArmType());
				
			case TF_ASS:
				switch(secondaryModifier) {
					case TF_MOD_CAPACITY_DECREASE:
						return target.incrementAssCapacity(-normalValue);
					case TF_MOD_CAPACITY_INCREASE:
						return target.incrementAssCapacity(normalValue);
					case TF_MOD_ELASTICITY_DECREASE:
						return target.incrementAssElasticity(-normalValue);
					case TF_MOD_ELASTICITY_INCREASE:
						return target.incrementAssElasticity(normalValue);
					case TF_MOD_SIZE_GROW:
						return target.incrementAssSize(normalValue);
					case TF_MOD_SIZE_SHRINK:
						return target.incrementAssSize(-normalValue);
					case TF_MOD_WETNESS_DECREASE:
						return target.incrementAssWetness(-normalValue);
					case TF_MOD_WETNESS_INCREASE:
						return target.incrementAssWetness(normalValue);
					case TF_MOD_HIP_SIZE_DECREASE:
						return target.incrementHipSize(-normalValue);
					case TF_MOD_HIP_SIZE_INCREASE:
						return target.incrementHipSize(normalValue);

					case TF_MOD_CAPACITY_DECREASE_STRONG:
						return target.incrementAssCapacity(-largeValue);
					case TF_MOD_CAPACITY_INCREASE_STRONG:
						return target.incrementAssCapacity(largeValue);
					case TF_MOD_ELASTICITY_DECREASE_STRONG:
						return target.incrementAssElasticity(-largeValue);
					case TF_MOD_ELASTICITY_INCREASE_STRONG:
						return target.incrementAssElasticity(largeValue);
					case TF_MOD_SIZE_GROW_STRONG:
						return target.incrementAssSize(largeValue);
					case TF_MOD_SIZE_SHRINK_STRONG:
						return target.incrementAssSize(-largeValue);
					case TF_MOD_WETNESS_DECREASE_STRONG:
						return target.incrementAssWetness(-largeValue);
					case TF_MOD_WETNESS_INCREASE_STRONG:
						return target.incrementAssWetness(largeValue);
						
					default:
						return target.setAssType(RacialBody.valueOfRace(race).getAssType());
				}
				
			case TF_BREASTS:
				switch(secondaryModifier) {
					case TF_MOD_CAPACITY_DECREASE:
						return target.incrementNippleCapacity(-normalValue);
					case TF_MOD_CAPACITY_INCREASE:
						return target.incrementNippleCapacity(normalValue);
					case TF_MOD_ELASTICITY_DECREASE:
						return target.incrementNippleElasticity(-normalValue);
					case TF_MOD_ELASTICITY_INCREASE:
						return target.incrementNippleElasticity(normalValue);
					case TF_MOD_SIZE_GROW:
						return target.incrementBreastSize(normalValue);
					case TF_MOD_SIZE_SHRINK:
						return target.incrementBreastSize(-normalValue);
					case TF_MOD_WETNESS_DECREASE:
						return target.incrementBreastLactation(-normalValue*5);
					case TF_MOD_WETNESS_INCREASE:
						return target.incrementBreastLactation(normalValue*5);
					case TF_MOD_BREAST_ROWS_DECREASE:
						return target.incrementBreastRows(-normalValue);
					case TF_MOD_BREAST_ROWS_INCREASE:
						return target.incrementBreastRows(normalValue);
						
					case TF_MOD_CAPACITY_DECREASE_STRONG:
						return target.incrementNippleCapacity(-largeValue);
					case TF_MOD_CAPACITY_INCREASE_STRONG:
						return target.incrementNippleCapacity(largeValue);
					case TF_MOD_ELASTICITY_DECREASE_STRONG:
						return target.incrementNippleElasticity(-largeValue);
					case TF_MOD_ELASTICITY_INCREASE_STRONG:
						return target.incrementNippleElasticity(largeValue);
					case TF_MOD_SIZE_GROW_STRONG:
						return target.incrementBreastSize(largeValue);
					case TF_MOD_SIZE_SHRINK_STRONG:
						return target.incrementBreastSize(-largeValue);
					case TF_MOD_WETNESS_DECREASE_STRONG:
						return target.incrementBreastLactation(-largeValue*5);
					case TF_MOD_WETNESS_INCREASE_STRONG:
						return target.incrementBreastLactation(largeValue*5);
						
					default:
						return target.setBreastType(RacialBody.valueOfRace(race).getBreastType());
				}
				
			case TF_CORE: 
				switch(secondaryModifier) {
					case TF_MOD_SIZE_GROW:
						return target.incrementHeight(normalValue);
					case TF_MOD_SIZE_SHRINK:
						return target.incrementHeight(-normalValue);
					case TF_MOD_FEMININITY_INCREASE:
						return target.incrementFemininity(normalValue);
					case TF_MOD_FEMININITY_DECREASE:
						return target.incrementFemininity(-normalValue);

					case TF_MOD_SIZE_GROW_STRONG:
						return target.incrementHeight(largeValue);
					case TF_MOD_SIZE_SHRINK_STRONG:
						return target.incrementHeight(-largeValue);
					case TF_MOD_FEMININITY_INCREASE_STRONG:
						return target.incrementFemininity(largeValue);
					case TF_MOD_FEMININITY_DECREASE_STRONG:
						return target.incrementFemininity(-largeValue);
						
					default: // This could theoretically just keep randomly choosing TF_CORE and freeze the game, but the chance is negligible (performance won't be affected for even a hundred loops or so):
						return getRacialEffect(race, TFModifier.getTFRacialBodyPartsListList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsListList().size())), secondaryModifier, user, target);
				}
				
			case TF_EARS:
				return target.setEarType(RacialBody.valueOfRace(race).getEarType());
				
			case TF_EYES:
				return target.setEyeType(RacialBody.valueOfRace(race).getEyeType());
				
			case TF_FACE:
				return target.setFaceType(RacialBody.valueOfRace(race).getFaceType());
				
			case TF_HAIR:
				switch(secondaryModifier) {
					case TF_MOD_SIZE_GROW:
						return target.incrementHairLength(normalValue);
					case TF_MOD_SIZE_SHRINK:
						return target.incrementHairLength(-normalValue);

					case TF_MOD_SIZE_GROW_STRONG:
						return target.incrementHairLength(largeValue);
					case TF_MOD_SIZE_SHRINK_STRONG:
						return target.incrementHairLength(-largeValue);
						
					default:
						return target.setHairType(RacialBody.valueOfRace(race).getHairType());
				}
				
			case TF_HORNS:
				if(target.isFeminine()) {
					return target.setHornType(RacialBody.valueOfRace(race).getHornTypeFemale());
				} else {
					return target.setHornType(RacialBody.valueOfRace(race).getHornTypeMale());
				}
				
			case TF_LEGS:
				return target.setLegType(RacialBody.valueOfRace(race).getLegType());
				
			case TF_PENIS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE_GROW:
						if(target.getPenisType()==PenisType.NONE)
							return target.setPenisType(RacialBody.valueOfRace(race).getPenisType());
						return target.incrementPenisSize(normalValue);
					case TF_MOD_SIZE_SHRINK:
						return target.incrementPenisSize(-normalValue);
					case TF_MOD_WETNESS_DECREASE:
						return target.incrementPenisCumProduction(-normalValue*5);
					case TF_MOD_WETNESS_INCREASE:
						return target.incrementPenisCumProduction(normalValue*5);
					case TF_MOD_TESTICLE_SIZE_INCREASE:
						return target.incrementTesticleSize(normalValue);
					case TF_MOD_TESTICLE_SIZE_DECREASE:
						return target.incrementTesticleSize(-normalValue);
					case REMOVAL:
						return target.setPenisType(PenisType.NONE);
						
					case TF_MOD_SIZE_GROW_STRONG:
						if(target.getPenisType()==PenisType.NONE)
							return target.setPenisType(RacialBody.valueOfRace(race).getPenisType());
						return target.incrementPenisSize(largeValue);
					case TF_MOD_SIZE_SHRINK_STRONG:
						return target.incrementPenisSize(-largeValue);
					case TF_MOD_WETNESS_DECREASE_STRONG:
						return target.incrementPenisCumProduction(-largeValue*5);
					case TF_MOD_WETNESS_INCREASE_STRONG:
						return target.incrementPenisCumProduction(largeValue*5);
						
					default:
						return target.setPenisType(RacialBody.valueOfRace(race).getPenisType());
				}
				
			case TF_SKIN:
				return target.setSkinType(RacialBody.valueOfRace(race).getSkinType());
				
			case TF_TAIL:
				return target.setTailType(RacialBody.valueOfRace(race).getTailType());
				
			case TF_VAGINA:
				switch(secondaryModifier) {
					case TF_MOD_CAPACITY_DECREASE:
						return target.incrementVaginaCapacity(-normalValue);
					case TF_MOD_CAPACITY_INCREASE:
						if(target.getVaginaType()==VaginaType.NONE)
							return target.setVaginaType(RacialBody.valueOfRace(race).getVaginaType());
						return target.incrementVaginaCapacity(normalValue);
					case TF_MOD_ELASTICITY_DECREASE:
						return target.incrementVaginaElasticity(-normalValue);
					case TF_MOD_ELASTICITY_INCREASE:
						return target.incrementVaginaElasticity(normalValue);
					case TF_MOD_SIZE_GROW:
						if(target.getVaginaType()==VaginaType.NONE)
							return target.setVaginaType(RacialBody.valueOfRace(race).getVaginaType());
						return target.incrementVaginaClitorisSize(normalValue);
					case TF_MOD_SIZE_SHRINK:
						return target.incrementVaginaClitorisSize(-normalValue);
					case TF_MOD_WETNESS_DECREASE:
						return target.incrementVaginaWetness(-normalValue);
					case TF_MOD_WETNESS_INCREASE:
						return target.incrementVaginaWetness(normalValue);
					case REMOVAL:
						return target.setVaginaType(VaginaType.NONE);
						
					case TF_MOD_CAPACITY_DECREASE_STRONG:
						return target.incrementVaginaCapacity(-largeValue);
					case TF_MOD_CAPACITY_INCREASE_STRONG:
						if(target.getVaginaType()==VaginaType.NONE)
							return target.setVaginaType(RacialBody.valueOfRace(race).getVaginaType());
						return target.incrementVaginaCapacity(largeValue);
					case TF_MOD_ELASTICITY_DECREASE_STRONG:
						return target.incrementVaginaElasticity(-largeValue);
					case TF_MOD_ELASTICITY_INCREASE_STRONG:
						return target.incrementVaginaElasticity(largeValue);
					case TF_MOD_SIZE_GROW_STRONG:
						if(target.getVaginaType()==VaginaType.NONE)
							return target.setVaginaType(RacialBody.valueOfRace(race).getVaginaType());
						return target.incrementVaginaClitorisSize(largeValue);
					case TF_MOD_SIZE_SHRINK_STRONG:
						return target.incrementVaginaClitorisSize(-largeValue);
					case TF_MOD_WETNESS_DECREASE_STRONG:
						return target.incrementVaginaWetness(-largeValue);
					case TF_MOD_WETNESS_INCREASE_STRONG:
						return target.incrementVaginaWetness(largeValue);
						
					default:
						return target.setVaginaType(RacialBody.valueOfRace(race).getVaginaType());
				}
				
			case TF_WINGS:
				return target.setWingType(RacialBody.valueOfRace(race).getWingType());
				
			default:
				return randomNonRacialTransformation(race, primaryModifier, secondaryModifier, user, target);
		}
	}
	
	private static String randomNonRacialTransformation(Race race, TFModifier primaryModifier, TFModifier secondaryModifier, GameCharacter user, GameCharacter target) {
		List<TFModifier> primaryModifiers = new ArrayList<>();
		
		primaryModifiers.add(TFModifier.TF_ASS);
		primaryModifiers.add(TFModifier.TF_BREASTS);
		primaryModifiers.add(TFModifier.TF_CORE);
		primaryModifiers.add(TFModifier.TF_HAIR);
		if(target.hasPenis())
			primaryModifiers.add(TFModifier.TF_PENIS);
		if(target.hasVagina())
			primaryModifiers.add(TFModifier.TF_VAGINA);
		
		TFModifier randomPrimaryMod = primaryModifiers.get(Util.random.nextInt(primaryModifiers.size()));
		
		List<TFModifier> secondaryModifiers = new ArrayList<>();
		
		switch(randomPrimaryMod) {
			case TF_ASS:
				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_HIP_SIZE_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_HIP_SIZE_INCREASE);
				break;
			case TF_BREASTS:
				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_BREAST_ROWS_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_BREAST_ROWS_INCREASE);
				break;
			case TF_CORE:
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK);
				secondaryModifiers.add(TFModifier.TF_MOD_FEMININITY_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_FEMININITY_DECREASE);
				break;
			case TF_HAIR:
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK);
				break;
			case TF_PENIS:
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_TESTICLE_SIZE_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_TESTICLE_SIZE_DECREASE);
				secondaryModifiers.add(TFModifier.REMOVAL);
				break;
			case TF_VAGINA:
				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_CAPACITY_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_ELASTICITY_INCREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_GROW);
				secondaryModifiers.add(TFModifier.TF_MOD_SIZE_SHRINK);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_DECREASE);
				secondaryModifiers.add(TFModifier.TF_MOD_WETNESS_INCREASE);
				secondaryModifiers.add(TFModifier.REMOVAL);
				break;
			default:
				secondaryModifiers.add(TFModifier.NONE);
				break;
		}
		

		TFModifier randomSecondaryMod = secondaryModifiers.get(Util.random.nextInt(secondaryModifiers.size()));
		
		return getRacialEffect(race, randomPrimaryMod, randomSecondaryMod, user, target);
	}
}
