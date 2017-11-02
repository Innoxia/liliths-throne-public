package com.lilithsthrone.game.inventory.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisModifier;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryBookAddedToLibrary;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.7
 * @version 0.1.86
 * @author Innoxia
 */
public enum ItemEffectType {
	
	TESTING(Util.newArrayListOfValues(
			new ListValue<>("Test item.")),
		Colour.GENERIC_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return target.incrementMuscle(-25)
					+ target.incrementBodySize(25)
					+ target.setUnderarmHair(BodyHair.SIX_BUSHY);
		}
	},
	
	DYE_BRUSH(Util.newArrayListOfValues(
				new ListValue<>("Recolours a piece of clothing.")),
			Colour.GENERIC_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return "<p>"
					+ "You use the dye brush."
					+ "</p>";
		}
	},
	
	USED_CONDOM_DRINK(Util.newArrayListOfValues(
			new ListValue<>("Provides a slimy snack.")),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getBookEffect(Race.CAT_MORPH, ItemType.BOOK_CAT_MORPH);
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getBookEffect(Race.COW_MORPH, ItemType.BOOK_COW_MORPH);
		}
	},
	
	BOOK_READ_DEMON(Util.newArrayListOfValues(
			new ListValue<>("Adds demon encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_DEMON) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getBookEffect(Race.DEMON, ItemType.BOOK_DEMON);
		}
	},
	
	BOOK_READ_DOG_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds dog-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_DOG_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getBookEffect(Race.DOG_MORPH, ItemType.BOOK_DOG_MORPH);
		}
	},
	
	BOOK_READ_GATOR_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds gator-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_GATOR_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getBookEffect(Race.GATOR_MORPH, ItemType.BOOK_GATOR_MORPH);
		}
	},
	
	BOOK_READ_HARPY(Util.newArrayListOfValues(
			new ListValue<>("Adds harpy encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_HARPY) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getBookEffect(Race.HARPY, ItemType.BOOK_HARPY);
		}
	},
	
	BOOK_READ_HORSE_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds horse-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_HORSE_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getBookEffect(Race.HORSE_MORPH, ItemType.BOOK_HORSE_MORPH);
		}
	},
	
	BOOK_READ_HUMAN(Util.newArrayListOfValues(
			new ListValue<>("Adds human encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_HUMAN) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getBookEffect(Race.HUMAN, ItemType.BOOK_HUMAN);
		}
	},
	
	BOOK_READ_SQUIRREL_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds squirrel-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_SQUIRREL_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getBookEffect(Race.SQUIRREL_MORPH, ItemType.BOOK_SQUIRREL_MORPH);
		}
	},
	
	BOOK_READ_WOLF_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds wolf-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(intelligence)]")),
			Colour.RACE_WOLF_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getBookEffect(Race.WOLF_MORPH, ItemType.BOOK_WOLF_MORPH);
		}
	},
	
	VIXENS_VIRILITY(Util.newArrayListOfValues(
			new ListValue<>("Temporarily boosts fertility.")),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return (target.isPlayer()
						?"A powerful wave of arcane energy washes over you......"
						:UtilText.parse(target, "A powerful wave of arcane energy washes over [npc.name]..."))
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return (target.isPlayer()
						?"A powerful wave of arcane energy washes over you......"
						:UtilText.parse(target, "A powerful wave of arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.STRENGTH, 1);
		}
	},

	STR_WOLF_WHISKEY(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(health)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldStrength(strength)] to 'potion effects'")),
			Colour.ATTRIBUTE_STRENGTH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return (target.isPlayer()
						?"A powerful wave of arcane energy washes over you......"
						:UtilText.parse(target, "A powerful wave of arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.STRENGTH, 1);
		}
	},
	
	STR_SWAMP_WATER(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(health)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldStrength(strength)] to 'potion effects'")),
			Colour.ATTRIBUTE_STRENGTH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);
			
			return (target.isPlayer()
						?"A cool wave of arcane energy washes over you......"
						:UtilText.parse(target, "A cool wave of arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.INTELLIGENCE, 1);
		}
	},
	
	INT_VANILLA_WATER(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldWillpower(willpower)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldIntelligence(intelligence)] to 'potion effects'")),
			Colour.ATTRIBUTE_INTELLIGENCE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);
			
			return (target.isPlayer()
							?"A cool wave of arcane energy washes over you......"
							:UtilText.parse(target, "A cool wave of arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.INTELLIGENCE, 1);
		}
	},
	
	// Fitness:
	
	FIT_CANINE_CRUSH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldStamina(stamina)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldFitness(fitness)] to 'potion effects'")),
			Colour.ATTRIBUTE_FITNESS) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementStamina(target.getAttributeValue(Attribute.STAMINA_MAXIMUM)/20);

			return (target.isPlayer()
						?"A soothing wave of arcane energy washes over you......"
						:UtilText.parse(target, "A soothing wave of arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.FITNESS, 1);
		}
	},
	
	FIT_SQUIRREL_JAVA(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldStamina(stamina)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldFitness(fitness)] to 'potion effects'")),
			Colour.ATTRIBUTE_FITNESS) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementStamina(target.getAttributeValue(Attribute.STAMINA_MAXIMUM)/20);

			return (target.isPlayer()
						?"A soothing wave of arcane energy washes over you......"
						:UtilText.parse(target, "A soothing wave of arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.FITNESS, 1);
		}
	},
	
	SEX_HARPY_PERFUME(Util.newArrayListOfValues(
			new ListValue<>("[style.boldSex(+1)] [style.boldFeminine(femininity)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldFitness(fitness)] to 'potion effects'")),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return (target.isPlayer()
						?"A soothing wave of arcane energy washes over you......"
						:UtilText.parse(target, "A soothing wave of arcane energy washes over [npc.name]..."))
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);
			target.incrementStamina(target.getAttributeValue(Attribute.STAMINA_MAXIMUM)/20);

			return (target.isPlayer()
						?"A sickly wave of corruptive arcane energy washes over you......"
						:UtilText.parse(target, "A sickly wave of corruptive arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.CORRUPTION, 1);
		}
	},
	
	MYSTERY_KINK(Util.newArrayListOfValues(
			new ListValue<>("[style.boldFetish(Random fetish addition or removal)]")),
			Colour.FETISH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			List<Fetish> fetishesToAdd = new ArrayList<>();
			List<Fetish> fetishesToRemove = new ArrayList<>();
			for(Fetish f : Fetish.values()) {
				if(f.getFetishesForAutomaticUnlock().isEmpty()) {
					if(target.hasFetish(f)) {
						fetishesToRemove.add(f);
						
					} else if(f.isAvailable(target)) {
						fetishesToAdd.add(f);
					}
				}
			}
			
			if((Math.random()>0.33f && !fetishesToAdd.isEmpty()) || fetishesToRemove.isEmpty()) {
				Fetish f = fetishesToAdd.get(Util.random.nextInt(fetishesToAdd.size()));
				target.addFetish(f);
				
				return (target.isPlayer()
						?"A staggering wave of arcane energy crashes over you, the sheer strength of which almost causes you to black out."
								+ " As you stagger back from the brink of unconsciousness, you realise that you've [style.boldGood(gained)] the [style.boldFetish("+f.getName(target)+" fetish)]!"
						:UtilText.parse(target, "A staggering wave of arcane energy crashes over [npc.name], the sheer strength of which almost causes [npc.herHim] to black out."
								+ " As [npc.she] staggers back from the brink of unconsciousness, [npc.she] discovers that [npc.she]'s [style.boldGood(gained)] the [style.boldFetish("+f.getName(target)+" fetish)]!"));
				
			} else {
				Fetish f = fetishesToRemove.get(Util.random.nextInt(fetishesToRemove.size()));
				target.removeFetish(f);
				
				return (target.isPlayer()
						?"A staggering wave of arcane energy crashes over you, the sheer strength of which almost causes you to black out."
								+ " As you stagger back from the brink of unconsciousness, you realise that you've [style.boldBad(lost)] your [style.boldFetish("+f.getName(target)+" fetish)]!"
						:UtilText.parse(target, "A staggering wave of arcane energy crashes over [npc.name], the sheer strength of which almost causes [npc.herHim] to black out."
								+ " As [npc.she] staggers back from the brink of unconsciousness, [npc.she] discovers that [npc.she]'s [style.boldBad(lost)] [npc.her] [style.boldFetish("+f.getName(target)+" fetish)]!"));
			}
		}
	},
	
	EGGPLANT(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(health)]"),
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldWillpower(willpower)]"),
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldStamina(stamina)]")),
			Colour.ATTRIBUTE_CORRUPTION) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return (target.isPlayer()
						?"You start to feel a lot healthier..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot healthier..."))
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return (target.isPlayer()
						?"You start to feel a lot more energetic..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot more energetic..."))
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return (target.isPlayer()
							?"You start to feel a lot more intelligent..."
							:UtilText.parse(target, "[npc.Name] starts to feel a lot more intelligent..."))
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return (target.isPlayer()
							?"You start to feel a lot fitter..."
							:UtilText.parse(target, "[npc.Name] starts to feel a lot fitter..."))
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return (target.isPlayer()
						?"You start to feel a lot stronger..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot stronger..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.STRENGTH, 3)
					+ "</br>"
					+ target.addPotionEffect(Attribute.FITNESS, 2);
		}
	},
	
	RACE_GATORS_GUMBO(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldStrength(strength)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+2)] [style.boldFitness(fitness)] to 'potion effects'")),
			Colour.RACE_GATOR_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return "You start to feel a lot stronger..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.STRENGTH, 3)
					+ "</br>"
					+ target.addPotionEffect(Attribute.FITNESS, 2);
		}
	},
	
	RACE_BUBBLE_CREAM(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldStrength(strength)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+2)] [style.boldFitness(fitness)] to 'potion effects'")),
			Colour.RACE_COW_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return (target.isPlayer()
						?"You start to feel a lot stronger..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot stronger..."))
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return (target.isPlayer()
						?"You start to feel a lot stronger..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot stronger..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.STRENGTH, 5)
					+ "</br>"
					+ target.addPotionEffect(Attribute.CORRUPTION, 3);
		}
	},
	
	RACE_LOLLIPOP(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+5)] [style.boldFitness(fitness)] to 'potion effects'"),
			new ListValue<>("[style.boldSex(+3)] [style.boldFeminine(femininity)]")),
			Colour.RACE_WOLF_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return (target.isPlayer()
						?"You start to feel more feminine..."
						:UtilText.parse(target, "[npc.Name] starts to feel mroe feminine..."))
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
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
//			target.incrementEssenceCount(TFEssence.ANGEL, 1);
//			return "You have absorbed [style.boldGood(+1)] [style.boldAngel(Angel)] essence!";
//		}
//	},
	
	BOTTLED_ESSENCE_ARCANE(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence")),
			Colour.GENERIC_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence!";
		}
	},
	
	BOTTLED_ESSENCE_CAT_MORPH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldCat(cat-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldCat(cat-morphs)]")),
			Colour.RACE_CAT_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_CAT_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldCat(cat-morphs)]!";
		}
	},
	
	BOTTLED_ESSENCE_COW_MORPH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldCow(cow-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldCow(cow-morphs)]")),
			Colour.RACE_COW_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_COW_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldCow(cow-morphs)]!";
		}
	},

 	BOTTLED_ESSENCE_SQUIRREL_MORPH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldSquirrel(squirrel-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldSquirrel(squirrel-morphs)]")),
			Colour.RACE_SQUIRREL_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_SQUIRREL_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldSquirrel(squirrel-morphs)]!";
		}
	},
	
 	BOTTLED_ESSENCE_GATOR_MORPH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldGator(Gator-morph)] essence")),
			Colour.RACE_GATOR_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.GATOR_MORPH, 1);
			return "You have absorbed [style.boldGood(+1)] [style.boldGator(Gator-morph)] essence!";
		}
	},
	
	BOTTLED_ESSENCE_DEMON(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldDemon(demons)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldDemon(demons)]")),
			Colour.RACE_DEMON) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_DEMON, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldDemon(demons)]!";
		}
	},
	
	BOTTLED_ESSENCE_DOG_MORPH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldDog(dog-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldDog(dog-morphs)]")),
			Colour.RACE_DOG_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_DOG_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldDog(dog-morphs)]!";
		}
	},
	
	BOTTLED_ESSENCE_HARPY(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldHarpy(harpies)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldHarpy(harpies)]")),
			Colour.RACE_HARPY) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_HARPY, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldHarpy(harpies)]!";
		}
	},
	
	BOTTLED_ESSENCE_HORSE_MORPH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldHorse(horse-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldHorse(horse-morphs)]")),
			Colour.RACE_HORSE_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_HORSE_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldHorse(horse-morphs)]!";
		}
	},
	
	BOTTLED_ESSENCE_HUMAN(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldHuman(humans)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldHuman(humans)]")),
			Colour.RACE_HUMAN) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_HUMAN, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldHuman(humans)]!";
		}
	},
	
	BOTTLED_ESSENCE_WOLF_MORPH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldWolf(wolf-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldWolf(wolf-morphs)]")),
			Colour.RACE_WOLF_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_WOLF_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldWolf(wolf-morphs)]!";
		}
	},
	
	
	// Specials:
	
	BIMBO_LOLLIPOP(Util.newArrayListOfValues(
			new ListValue<>("<b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Bimbo</b> <b style='color:"+Colour.RACE_HARPY.toWebHexString()+";'>harpy</b> <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>transformation</b>")),
			Colour.RACE_HARPY) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
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
			if(target.getFemininityValue()<95) {
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
				sb.append("</br>" + target.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, Colour.FEATHERS_BLEACH_BLONDE, false, Colour.FEATHERS_BLEACH_BLONDE, false), true));
			}
			if(target.getSkinType()!=SkinType.HARPY) {
				sb.append("</br>" + target.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, Colour.FEATHERS_BLEACH_BLONDE, false, Colour.FEATHERS_BLEACH_BLONDE, false), true));
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
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
			if(target.getFemininityValue()<95) {
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
				sb.append("</br>" + target.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, Colour.FEATHERS_PINK, false, Colour.FEATHERS_PINK, false), true));
			}
			if(target.getSkinType()!=SkinType.HARPY) {
				sb.append("</br>" + target.setHairCovering(new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, Colour.FEATHERS_PINK, false, Colour.FEATHERS_PINK, false), true));
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
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
			if(target.getFemininityValue()<95) {
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
				sb.append("</br>" + target.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, Colour.FEATHERS_BLACK, false, Colour.FEATHERS_BLACK, false), true));
			}
			if(target.getSkinType()!=SkinType.HARPY) {
				sb.append("</br>" + target.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, Colour.FEATHERS_RED, false, Colour.FEATHERS_RED, false), true));
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
	
	// Enchantment effects: TODO
	
	ATTRIBUTE_STRENGTH(null,
			Colour.ATTRIBUTE_STRENGTH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFModStrengthList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return TFModifier.getTFAttributeList();
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(secondaryModifier != TFModifier.NONE) {
				return TFPotency.getAllPotencies();
			} else {
				return new ArrayList<>();
			}
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return genericAttributeEffectDescription(resourceRestoration.HEALTH, primaryModifier, secondaryModifier, potency, limit);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return genericAttributeEffect(resourceRestoration.HEALTH, primaryModifier, secondaryModifier, potency, limit, user, target);
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
			return TFModifier.getTFAttributeList();
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(secondaryModifier != TFModifier.NONE) {
				return TFPotency.getAllPotencies();
			} else {
				return new ArrayList<>();
			}
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return genericAttributeEffectDescription(resourceRestoration.WILLPOWER, primaryModifier, secondaryModifier, potency, limit);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return genericAttributeEffect(resourceRestoration.WILLPOWER, primaryModifier, secondaryModifier, potency, limit, user, target);
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
			return TFModifier.getTFAttributeList();
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(secondaryModifier != TFModifier.NONE) {
				return TFPotency.getAllPotencies();
			} else {
				return new ArrayList<>();
			}
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return genericAttributeEffectDescription(resourceRestoration.STAMINA, primaryModifier, secondaryModifier, potency, limit);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return genericAttributeEffect(resourceRestoration.STAMINA, primaryModifier, secondaryModifier, potency, limit, user, target);
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
			return TFModifier.getTFAttributeList();
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(secondaryModifier != TFModifier.NONE) {
				return TFPotency.getAllPotencies();
			} else {
				return new ArrayList<>();
			}
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return genericAttributeEffectDescription(resourceRestoration.STAMINA, primaryModifier, secondaryModifier, potency, limit);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return genericAttributeEffect(resourceRestoration.STAMINA, primaryModifier, secondaryModifier, potency, limit, user, target);
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
			return TFModifier.getTFAttributeList();
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(secondaryModifier != TFModifier.NONE) {
				return TFPotency.getAllPotencies();
			} else {
				return new ArrayList<>();
			}
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return genericAttributeEffectDescription(resourceRestoration.ALL, primaryModifier, secondaryModifier, potency, limit);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return genericAttributeEffect(resourceRestoration.ALL, primaryModifier, secondaryModifier, potency, limit, user, target);
		}
	},
	
	FETISH_ENHANCEMENT(null,
			Colour.FETISH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return Util.newArrayListOfValues(new ListValue<>(TFModifier.NONE));
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return TFModifier.getTFFetishList();
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST), new ListValue<>(TFPotency.MINOR_DRAIN));
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			if(potency==TFPotency.MINOR_BOOST) {
				if(secondaryModifier == TFModifier.NONE) {
					return Util.newArrayListOfValues(new ListValue<>("[style.boldFetish(Adds a random fetish.)]"));
				} else {
					return Util.newArrayListOfValues(new ListValue<>("[style.boldFetish(Adds the "+secondaryModifier.getName()+" fetish.)]"));
				}
				
			} else {
				if(secondaryModifier == TFModifier.NONE) {
					return Util.newArrayListOfValues(new ListValue<>("[style.boldFetish(Removes a random fetish.)]"));
				} else {
					return Util.newArrayListOfValues(new ListValue<>("[style.boldFetish(Removes the "+secondaryModifier.getName()+" fetish.)]"));
				}
			}
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			if(potency==TFPotency.MINOR_BOOST) {
				if(secondaryModifier == TFModifier.NONE) {
					List<Fetish> fetishesToAdd = new ArrayList<>();
					for(Fetish f : Fetish.values()) {
						if(f.getFetishesForAutomaticUnlock().isEmpty()) {
							if(f.isAvailable(target)) {
								fetishesToAdd.add(f);
							}
						}
					}
					
					if(!fetishesToAdd.isEmpty()) {
						Fetish f = fetishesToAdd.get(Util.random.nextInt(fetishesToAdd.size()));
						target.addFetish(f);
						
						return "<p>"
									+(target.isPlayer()
										?"A staggering wave of arcane energy crashes over you, the sheer strength of which almost causes you to black out."
											+ " As you stagger back from the brink of unconsciousness, you realise that you've [style.boldGood(gained)] the [style.boldFetish("+f.getName(target)+" fetish)]!"
										:UtilText.parse(target, "A staggering wave of arcane energy crashes over [npc.name], the sheer strength of which almost causes [npc.herHim] to black out."
											+ " As [npc.she] staggers back from the brink of unconsciousness, [npc.she] discovers that [npc.she]'s [style.boldGood(gained)] the [style.boldFetish("+f.getName(target)+" fetish)]!"))
								+"</p>";
						
					} else {
						return "<p>"
									+(target.isPlayer()
										?"[style.colourDisabled(Nothing happens...)]"
										:UtilText.parse(target, "[style.colourDisabled(Nothing happens...)]"))
								+"</p>";
					}
					
				} else {
					Fetish fetish = secondaryModifier.getFetish();
					
					if(!target.hasFetish(fetish)) {
						target.addFetish(fetish);
						return "<p>"
									+(target.isPlayer()
										?"A staggering wave of arcane energy crashes over you, the sheer strength of which almost causes you to black out."
											+ " As you stagger back from the brink of unconsciousness, you realise that you've [style.boldGood(gained)] the [style.boldFetish("+fetish.getName(target)+" fetish)]!"
										:UtilText.parse(target, "A staggering wave of arcane energy crashes over [npc.name], the sheer strength of which almost causes [npc.herHim] to black out."
											+ " As [npc.she] staggers back from the brink of unconsciousness, [npc.she] discovers that [npc.she]'s [style.boldGood(gained)] the [style.boldFetish("+fetish.getName(target)+" fetish)]!"))
								+"</p>";	
					} else {
						return "<p>"
									+(target.isPlayer()
										?"[style.colourDisabled(Nothing happens, as you already have the "+fetish.getName(target)+" fetish...)]"
										:UtilText.parse(target, "[style.colourDisabled(Nothing happens, as [npc.she] already has the "+fetish.getName(target)+" fetish...)]"))
								+"</p>";
					}
				}
				
			} else {
				if(secondaryModifier == TFModifier.NONE) {
					List<Fetish> fetishesToRemove = new ArrayList<>();
					for(Fetish f : Fetish.values()) {
						if(f.getFetishesForAutomaticUnlock().isEmpty()) {
							if(target.hasFetish(f)) {
								fetishesToRemove.add(f);
							}
						}
					}
					
					if(!fetishesToRemove.isEmpty()) {
						Fetish f = fetishesToRemove.get(Util.random.nextInt(fetishesToRemove.size()));
						target.removeFetish(f);
						
						return "<p>"
									+(target.isPlayer()
										?"A staggering wave of arcane energy crashes over you, the sheer strength of which almost causes you to black out."
											+ " As you stagger back from the brink of unconsciousness, you realise that you've [style.boldBad(lost)] your [style.boldFetish("+f.getName(target)+" fetish)]!"
										:UtilText.parse(target, "A staggering wave of arcane energy crashes over [npc.name], the sheer strength of which almost causes [npc.herHim] to black out."
											+ " As [npc.she] staggers back from the brink of unconsciousness, [npc.she] discovers that [npc.she]'s [style.boldBad(lost)] [npc.her] [style.boldFetish("+f.getName(target)+" fetish)]!"))
								+"</p>";
					} else {
						return "<p>"
									+(target.isPlayer()
										?"[style.colourDisabled(Nothing happens...)]"
										:UtilText.parse(target, "[style.colourDisabled(Nothing happens...)]"))
								+"</p>";
					}
					
				} else {
					Fetish fetish = secondaryModifier.getFetish();
					
					if(target.hasFetish(fetish)) {
						target.removeFetish(fetish);
						return "<p>"
									+(target.isPlayer()
										?"A staggering wave of arcane energy crashes over you, the sheer strength of which almost causes you to black out."
											+ " As you stagger back from the brink of unconsciousness, you realise that you've [style.boldBad(lost)] your [style.boldFetish("+fetish.getName(target)+" fetish)]!"
										:UtilText.parse(target, "A staggering wave of arcane energy crashes over [npc.name], the sheer strength of which almost causes [npc.herHim] to black out."
											+ " As [npc.she] staggers back from the brink of unconsciousness, [npc.she] discovers that [npc.she]'s [style.boldBad(lost)] [npc.her] [style.boldFetish("+fetish.getName(target)+" fetish)]!"))
								+"</p>";
					} else {
						return "<p>"
									+(target.isPlayer()
										?"[style.colourDisabled(Nothing happens, as you already lack the "+fetish.getName(target)+" fetish...)]"
										:UtilText.parse(target, "[style.colourDisabled(Nothing happens, as [npc.she] already lacks the "+fetish.getName(target)+" fetish...)]"))
								+"</p>";
					}
				}
			}
		}
	},
	
	// RACIAL:
	
	RACE_DEMON(null,
			Colour.RACE_DEMON) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.DEMON, primaryModifier, secondaryModifier, potency, user, target).getDescription()));
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.DEMON, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	},
	
	RACE_HUMAN(null,
			Colour.RACE_HUMAN) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.HUMAN, primaryModifier, secondaryModifier, potency, user, target).getDescription()));
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.HUMAN, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	},
	
	RACE_CAT_MORPH(null,
			Colour.RACE_CAT_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.CAT_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription()));
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.CAT_MORPH, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	},
	
	RACE_COW_MORPH(null,
			Colour.RACE_COW_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.COW_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription()));
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.COW_MORPH, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	},
	
	RACE_SQUIRREL_MORPH(null,
			Colour.RACE_SQUIRREL_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.SQUIRREL_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription()));
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.SQUIRREL_MORPH, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	},
	
	RACE_DOG_MORPH(null,
			Colour.RACE_DOG_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.DOG_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription()));
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.DOG_MORPH, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	},
	
	RACE_GATOR_MORPH(null,
			Colour.RACE_GATOR_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.GATOR_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription()));
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.GATOR_MORPH, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	},
	
	RACE_HORSE_MORPH(null,
			Colour.RACE_HORSE_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.HORSE_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription()));
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.HORSE_MORPH, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	},
	
	RACE_WOLF_MORPH(null,
			Colour.RACE_WOLF_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.WOLF_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription()));
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.WOLF_MORPH, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	},
	
	RACE_HARPY(null,
			Colour.RACE_HARPY) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.HARPY, primaryModifier, secondaryModifier, potency, user, target).getDescription()));
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return getRacialEffect(Race.HARPY, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
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

	public List<TFModifier> getPrimaryModifiers() {
		return new ArrayList<>();
	}
	
	public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
		return new ArrayList<>();
	}
	
	public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
		return new ArrayList<>();
	}
	
	public List<Integer> getLimits(TFModifier primaryModifier, TFModifier secondaryModifier) {
		return new ArrayList<>();
	}
	
	public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
		return effectsDescriptions;
	}
	
	public abstract String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target);
	
	private enum resourceRestoration {
		HEALTH,
		WILLPOWER,
		STAMINA,
		ALL;
	}
	
	private static String getBookEffect(Race race, AbstractItemType book) {
		Main.getProperties().addRaceDiscovered(race);
		if(Main.getProperties().addAdvancedRaceKnowledge(race)) {
			Main.game.addEvent(new EventLogEntryBookAddedToLibrary(book), true);
			Main.game.getPlayer().incrementAttribute(Attribute.INTELLIGENCE, 0.5f);
		}
		
		return race.getBasicDescription()
				+race.getAdvancedDescription();
	}
	
	private static List<String> descriptions = new ArrayList<>();
	private static List<String> genericAttributeEffectDescription(resourceRestoration restorationType, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit) {
		descriptions.clear();
		
		switch(secondaryModifier) {
			case ARCANE_BOOST:
				switch(potency) {
					case MAJOR_DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsDrain(60, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add("[style.boldTerrible(-1)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b>");
							}
						}
						break;
					case DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsDrain(40, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add("[style.boldTerrible(-0.5)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b>");
							}
						}
						break;
					case MINOR_DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsDrain(20, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add("[style.boldBad(-5)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b> to 'potion effects'");
							}
						}
						break;
					case MINOR_BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsRestore(20, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add("[style.boldGood(+5)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b> to 'potion effects'");
							}
						}
						break;
					case BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsRestore(40, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add("[style.boldExcellent(+0.5)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b>");
							}
						}
						break;
					case MAJOR_BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsRestore(60, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add("[style.boldExcellent(+1)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b>");
							}
						}
						break;
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
	
	private static String applyRestoration(GameCharacter target, resourceRestoration restorationType, float percentage) {
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
		
		if(percentage > 0) {
			if(target.isPlayer()) {
				return "A soothing warmth spreads all throughout your body, and with a deeply satisfied sigh, you find yourself feeling a lot healthier than you did just a moment ago.";
			} else {
				return UtilText.parse(target, "[npc.Name] lets out a satisfied sigh, and you notice that [npc.she]'s suddenly looking a lot healthier than [npc.she] did just a moment ago.");
			}
		} else {
			if(target.isPlayer()) {
				return "A sickly warmth creeps its way all throughout your body, and with a feeble cough, you find yourself feeling weaker than you did just a moment ago.";
			} else {
				return UtilText.parse(target, "[npc.Name] lets out a feeble cough, and you notice that [npc.she]'s suddenly looking a little weaker than [npc.she] did just a moment ago.");
			}
		}
	}
	
	private static String genericAttributeEffect(resourceRestoration restorationType, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
		
		switch(secondaryModifier) {
			case ARCANE_BOOST:
				switch(potency) {
					case MAJOR_DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							return applyRestoration(target, restorationType, -0.6f);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								return target.incrementAttribute(primaryModifier.getAssociatedAttribute(), -1);
							}
						}
						break;
					case DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							return applyRestoration(target, restorationType, -0.4f);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								return target.incrementAttribute(primaryModifier.getAssociatedAttribute(), -0.5f);
							}
						}
						break;
					case MINOR_DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							return applyRestoration(target, restorationType, -0.2f);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								return "A sickly wave of arcane energy washes over you..."
										+ "</br>"
										+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), -5);
							}
						}
						break;
					case MINOR_BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							return applyRestoration(target, restorationType, 0.2f);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								return "A soothing wave of arcane energy washes over you..."
										+ "</br>"
										+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), 5);
							}
						}
						break;
					case BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							return applyRestoration(target, restorationType, 0.4f);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								return target.incrementAttribute(primaryModifier.getAssociatedAttribute(), 0.5f);
							}
						}
						break;
					case MAJOR_BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							return applyRestoration(target, restorationType, 0.6f);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								return target.incrementAttribute(primaryModifier.getAssociatedAttribute(), 1);
							}
						}
						break;
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
	
	// Caching:
	private static Map<TFModifier, LinkedHashMap<TFModifier, List<TFPotency>>> racialPrimaryModSecondaryModPotencyGrid = new HashMap<>();
	
	private static List<TFModifier> getRacialSecondaryModifiers(TFModifier primaryModifier) {
		if(racialPrimaryModSecondaryModPotencyGrid.containsKey(primaryModifier)) {
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(primaryModifier).keySet());
		} else {
			populateGrid(primaryModifier);
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(primaryModifier).keySet());
		}
	}
	
	private static List<TFPotency> getRacialPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
		if(racialPrimaryModSecondaryModPotencyGrid.containsKey(primaryModifier)) {
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(primaryModifier).get(secondaryModifier));
		} else {
			populateGrid(primaryModifier);
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(primaryModifier).get(secondaryModifier));
		}
	}
	
	private static void populateGrid(TFModifier primaryModifier){ //TODO Please make this better -.-
		LinkedHashMap<TFModifier, List<TFPotency>> secondaryModPotencyMap = new LinkedHashMap<>();
		secondaryModPotencyMap.put(TFModifier.NONE, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
		
		switch(primaryModifier) {
			case TF_ANTENNA:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
			
			case TF_ARMS:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				if(Main.game.isBodyHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_ASS:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				if(Main.game.isBodyHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
				
			case TF_BREASTS:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT_SECONDARY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_ROUND, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_POINTY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_PERKY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_SIDESET, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_WIDE, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_NARROW, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_NORMAL, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_VAGINA, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_LIPS, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_AREOLAE_CIRCLE, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_AREOLAE_HEART, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_AREOLAE_STAR, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
				
			case TF_CORE:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FEMININITY, TFPotency.getAllPotencies());
				if(Main.game.isPubicHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_EARS:
				break;
				
			case TF_EYES:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_CIRCLE, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_VERTICAL, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_HORIZONTAL, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_HEART, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_STAR, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_CIRCLE, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_VERTICAL, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_HORIZONTAL, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_HEART, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_STAR, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
				
			case TF_FACE:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_RIBBED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_TENTACLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_BIFURCATED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				
				if(Main.game.isFacialHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_HAIR:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				break;
				
			case TF_HORNS:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
				
			case TF_LEGS:
				break;
				
			case TF_PENIS:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));

				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_BARBED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_FLARED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_KNOTTED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_PREHENSILE, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_RIBBED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_SHEATHED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_TAPERED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_TENTACLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_VEINY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_INTERNAL, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
				
			case TF_SKIN:
				break;
				
			case TF_TAIL:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
				
			case TF_VAGINA:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				
				break;
				
			case TF_WINGS:
				break;
				
			case TF_CUM: case TF_MILK: case TF_GIRLCUM:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_ADDICTIVE, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_ALCOHOLIC, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_BUBBLING, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_HALLUCINOGENIC, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_MUSKY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_SLIMY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_STICKY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_VISCOUS, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_BEER, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CHOCOLATE, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CUM, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_GIRLCUM, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_MILK, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_HONEY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_MINT, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_PINEAPPLE, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_SLIME, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_STRAWBERRY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_VANILLA, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
				
			default:
				break;
		}
		
		racialPrimaryModSecondaryModPotencyGrid.put(primaryModifier, secondaryModPotencyMap);
	}
	
	// And in the comments these words appear: 'My name is Innoxia, creator of smut: Look on my methods, ye Modders, and despair!'
	
	private static RacialEffectUtil getRacialEffect(Race race, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, GameCharacter user, GameCharacter target) {
		switch(primaryModifier) {
			case TF_ANTENNA:
				switch(secondaryModifier) {
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of antennae.") { @Override public String applyEffect() { return target.incrementAntennaRows(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of antennae.") { @Override public String applyEffect() { return target.incrementAntennaRows(1); } };
						}
					default:
						if(RacialBody.valueOfRace(race).getAntennaType() == AntennaType.NONE) {
							return new RacialEffectUtil("Removes antennae.") { @Override public String applyEffect() { return target.setAntennaType(RacialBody.valueOfRace(race).getAntennaType()); } };
						} else {
							return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" antennae transformation.") { @Override public String applyEffect() { return target.setAntennaType(RacialBody.valueOfRace(race).getAntennaType()); } };
						}
				}
			
			case TF_ARMS:
				switch(secondaryModifier) {
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of arms.") { @Override public String applyEffect() { return target.incrementArmRows(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of arms.") { @Override public String applyEffect() { return target.incrementArmRows(1); } };
						}
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Removes all underarm hair.") { @Override public String applyEffect() { return target.incrementUnderarmHair(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Removes a large amount of underarm hair.") { @Override public String applyEffect() { return target.incrementUnderarmHair(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes some underarm hair.") { @Override public String applyEffect() { return target.incrementUnderarmHair(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds some underarm hair.") { @Override public String applyEffect() { return target.incrementUnderarmHair(1); } };
							case BOOST:
								return new RacialEffectUtil("Adds a large amount of underarm hair.") { @Override public String applyEffect() { return target.incrementUnderarmHair(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Adds a huge amount of underarm hair.") { @Override public String applyEffect() { return target.incrementUnderarmHair(3); } };
						}
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" arm transformation.") { @Override public String applyEffect() { return target.setArmType(RacialBody.valueOfRace(race).getArmType()); } };
				}
				
			case TF_ASS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in ass size.") { @Override public String applyEffect() { return target.incrementAssSize(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in ass size.") { @Override public String applyEffect() { return target.incrementAssSize(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in ass size.") { @Override public String applyEffect() { return target.incrementAssSize(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in ass size.") { @Override public String applyEffect() { return target.incrementAssSize(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in ass size.") { @Override public String applyEffect() { return target.incrementAssSize(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in ass size.") { @Override public String applyEffect() { return target.incrementAssSize(3); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in hip size.") { @Override public String applyEffect() { return target.incrementHipSize(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in hip size.") { @Override public String applyEffect() { return target.incrementHipSize(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in hip size.") { @Override public String applyEffect() { return target.incrementHipSize(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in hip size.") { @Override public String applyEffect() { return target.incrementHipSize(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in hip size.") { @Override public String applyEffect() { return target.incrementHipSize(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in hip size.") { @Override public String applyEffect() { return target.incrementHipSize(3); } };
						}
						
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Removes all ass hair.") { @Override public String applyEffect() { return target.incrementAssHair(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Removes a large amount of ass hair.") { @Override public String applyEffect() { return target.incrementAssHair(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes some ass hair.") { @Override public String applyEffect() { return target.incrementAssHair(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds some ass hair.") { @Override public String applyEffect() { return target.incrementAssHair(1); } };
							case BOOST:
								return new RacialEffectUtil("Adds a large amount of ass hair.") { @Override public String applyEffect() { return target.incrementAssHair(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Adds a huge amount of ass hair.") { @Override public String applyEffect() { return target.incrementAssHair(3); } };
						}
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in anal capacity.") { @Override public String applyEffect() { return target.incrementAssCapacity(-15, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in anal capacity.") { @Override public String applyEffect() { return target.incrementAssCapacity(-5, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in anal capacity.") { @Override public String applyEffect() { return target.incrementAssCapacity(-1, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in anal capacity.") { @Override public String applyEffect() { return target.incrementAssCapacity(1, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in anal capacity.") { @Override public String applyEffect() { return target.incrementAssCapacity(5, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in anal capacity.") { @Override public String applyEffect() { return target.incrementAssCapacity(15, true); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in anal elasticity.") { @Override public String applyEffect() { return target.incrementAssElasticity(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in anal elasticity.") { @Override public String applyEffect() { return target.incrementAssElasticity(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in anal elasticity.") { @Override public String applyEffect() { return target.incrementAssElasticity(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in anal elasticity.") { @Override public String applyEffect() { return target.incrementAssElasticity(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in anal elasticity.") { @Override public String applyEffect() { return target.incrementAssElasticity(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in anal elasticity.") { @Override public String applyEffect() { return target.incrementAssElasticity(3); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in anal plasticity.") { @Override public String applyEffect() { return target.incrementAssPlasticity(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in anal plasticity.") { @Override public String applyEffect() { return target.incrementAssPlasticity(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in anal plasticity.") { @Override public String applyEffect() { return target.incrementAssPlasticity(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in anal plasticity.") { @Override public String applyEffect() { return target.incrementAssPlasticity(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in anal plasticity.") { @Override public String applyEffect() { return target.incrementAssPlasticity(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in anal plasticity.") { @Override public String applyEffect() { return target.incrementAssPlasticity(3); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in natural anal lubrication.") { @Override public String applyEffect() { return target.incrementAssWetness(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in natural anal lubrication.") { @Override public String applyEffect() { return target.incrementAssWetness(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in natural anal lubrication.") { @Override public String applyEffect() { return target.incrementAssWetness(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in natural anal lubrication.") { @Override public String applyEffect() { return target.incrementAssWetness(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in natural anal lubrication.") { @Override public String applyEffect() { return target.incrementAssWetness(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in natural anal lubrication.") { @Override public String applyEffect() { return target.incrementAssWetness(3); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes puffiness from anal rim.") { @Override public String applyEffect() { return target.removeAssOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes anal rim puffy.") { @Override public String applyEffect() { return target.addAssOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal ribbing from anus.") { @Override public String applyEffect() { return target.removeAssOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal ribbing to anus.") { @Override public String applyEffect() { return target.addAssOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes extra internal muscles from anus.") { @Override public String applyEffect() { return target.removeAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds extra internal muscles to anus.") { @Override public String applyEffect() { return target.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal tentacles from anus.") { @Override public String applyEffect() { return target.removeAssOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal tentacles to anus.") { @Override public String applyEffect() { return target.addAssOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" ass transformation.") { @Override public String applyEffect() { return target.setAssType(RacialBody.valueOfRace(race).getAssType()); } };
				}
				
			case TF_BREASTS:
				switch(secondaryModifier) {
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of breasts.") { @Override public String applyEffect() { return target.incrementBreastRows(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of breasts.") { @Override public String applyEffect() { return target.incrementBreastRows(1); } };
						}
					case TF_MOD_COUNT_SECONDARY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra nipple from each breast.") { @Override public String applyEffect() { return target.incrementNippleCountPerBreast(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra nipple to each breast.") { @Override public String applyEffect() { return target.incrementNippleCountPerBreast(1); } };
						}
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in breast size.") { @Override public String applyEffect() { return target.incrementBreastSize(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in breast size.") { @Override public String applyEffect() { return target.incrementBreastSize(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in breast size.") { @Override public String applyEffect() { return target.incrementBreastSize(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in breast size.") { @Override public String applyEffect() { return target.incrementBreastSize(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in breast size.") { @Override public String applyEffect() { return target.incrementBreastSize(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in breast size.") { @Override public String applyEffect() { return target.incrementBreastSize(3); } };
						}
						

					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in nipple size.") { @Override public String applyEffect() { return target.incrementNippleSize(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in nipple size.") { @Override public String applyEffect() { return target.incrementNippleSize(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in nipple size.") { @Override public String applyEffect() { return target.incrementNippleSize(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in nipple size.") { @Override public String applyEffect() { return target.incrementNippleSize(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in nipple size.") { @Override public String applyEffect() { return target.incrementNippleSize(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in nipple size.") { @Override public String applyEffect() { return target.incrementNippleSize(3); } };
						}

					case TF_MOD_BREAST_SHAPE_ROUND:
						return new RacialEffectUtil("Transforms breast shape into being round.") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.ROUND); } };
					case TF_MOD_BREAST_SHAPE_PERKY:
						return new RacialEffectUtil("Transforms breast shape into being perky.") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.PERKY); } };
					case TF_MOD_BREAST_SHAPE_POINTY:
						return new RacialEffectUtil("Transforms breast shape into being pointy.") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.POINTY); } };
					case TF_MOD_BREAST_SHAPE_SIDESET:
						return new RacialEffectUtil("Transforms breast shape into being sideset.") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.SIDE_SET); } };
					case TF_MOD_BREAST_SHAPE_WIDE:
						return new RacialEffectUtil("Transforms breast shape into being wide.") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.WIDE); } };
					case TF_MOD_BREAST_SHAPE_NARROW:
						return new RacialEffectUtil("Transforms breast shape into being narrow.") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.NARROW); } };
						
					case TF_MOD_NIPPLE_NORMAL:
						return new RacialEffectUtil("Turns nipples into a normal, human-like shape.") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.NORMAL); } };
					case TF_MOD_NIPPLE_VAGINA:
						return new RacialEffectUtil("Turns nipples into vaginas.") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.VAGINA); } };
					case TF_MOD_NIPPLE_LIPS:
						return new RacialEffectUtil("Turns nipples into pairs of lips.") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.LIPS); } };
						
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in areolae size.") { @Override public String applyEffect() { return target.incrementAreolaeSize(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in areolae size.") { @Override public String applyEffect() { return target.incrementAreolaeSize(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in areolae size.") { @Override public String applyEffect() { return target.incrementAreolaeSize(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in areolae size.") { @Override public String applyEffect() { return target.incrementAreolaeSize(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in areolae size.") { @Override public String applyEffect() { return target.incrementAreolaeSize(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in areolae size.") { @Override public String applyEffect() { return target.incrementAreolaeSize(3); } };
						}
					case TF_MOD_AREOLAE_CIRCLE:
						return new RacialEffectUtil("Turns the shape of areolae into normal circles.") { @Override public String applyEffect() { return target.setAreolaeShape(AreolaeShape.NORMAL); } };
					case TF_MOD_AREOLAE_HEART:
						return new RacialEffectUtil("Turns the shape of areolae into hearts.") { @Override public String applyEffect() { return target.setAreolaeShape(AreolaeShape.HEART); } };
					case TF_MOD_AREOLAE_STAR:
						return new RacialEffectUtil("Turns the shape of areolae into stars.") { @Override public String applyEffect() { return target.setAreolaeShape(AreolaeShape.STAR); } };
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in nipple capacity.") { @Override public String applyEffect() { return target.incrementNippleCapacity(-15, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in nipple capacity.") { @Override public String applyEffect() { return target.incrementNippleCapacity(-5, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in nipple capacity.") { @Override public String applyEffect() { return target.incrementNippleCapacity(-1, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in nipple capacity.") { @Override public String applyEffect() { return target.incrementNippleCapacity(1, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in nipple capacity.") { @Override public String applyEffect() { return target.incrementNippleCapacity(5, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in nipple capacity.") { @Override public String applyEffect() { return target.incrementNippleCapacity(15, true); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in nipple elasticity.") { @Override public String applyEffect() { return target.incrementNippleElasticity(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in nipple elasticity.") { @Override public String applyEffect() { return target.incrementNippleElasticity(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in nipple elasticity.") { @Override public String applyEffect() { return target.incrementNippleElasticity(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in nipple elasticity.") { @Override public String applyEffect() { return target.incrementNippleElasticity(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in nipple elasticity.") { @Override public String applyEffect() { return target.incrementNippleElasticity(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in nipple elasticity.") { @Override public String applyEffect() { return target.incrementNippleElasticity(3); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in nipple plasticity.") { @Override public String applyEffect() { return target.incrementNipplePlasticity(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in nipple plasticity.") { @Override public String applyEffect() { return target.incrementNipplePlasticity(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in nipple plasticity.") { @Override public String applyEffect() { return target.incrementNipplePlasticity(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in nipple plasticity.") { @Override public String applyEffect() { return target.incrementNipplePlasticity(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in nipple plasticity.") { @Override public String applyEffect() { return target.incrementNipplePlasticity(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in nipple plasticity.") { @Override public String applyEffect() { return target.incrementNipplePlasticity(3); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in lactation.") { @Override public String applyEffect() { return target.incrementBreastLactation(-50); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in lactation.") { @Override public String applyEffect() { return target.incrementBreastLactation(-15); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in lactation.") { @Override public String applyEffect() { return target.incrementBreastLactation(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in lactation.") { @Override public String applyEffect() { return target.incrementBreastLactation(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in lactation.") { @Override public String applyEffect() { return target.incrementBreastLactation(15); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in lactation.") { @Override public String applyEffect() { return target.incrementBreastLactation(50); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes puffiness from nipples.") { @Override public String applyEffect() { return target.removeNippleOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes nipples extra puffy.") { @Override public String applyEffect() { return target.addNippleOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal ribbing from nipples.") { @Override public String applyEffect() { return target.removeNippleOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal ribbing to nipples.") { @Override public String applyEffect() { return target.addNippleOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal muscles from nipples.") { @Override public String applyEffect() { return target.removeNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal muscles to nipples.") { @Override public String applyEffect() { return target.addNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal tentacles from nipples.") { @Override public String applyEffect() { return target.removeNippleOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal tentacles to nipples.") { @Override public String applyEffect() { return target.addNippleOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" breast transformation.") { @Override public String applyEffect() { return target.setBreastType(RacialBody.valueOfRace(race).getBreastType()); } };
				}
				
			case TF_CORE: 
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in height.") { @Override public String applyEffect() { return target.incrementHeight(-15); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in height.") { @Override public String applyEffect() { return target.incrementHeight(-5); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in height.") { @Override public String applyEffect() { return target.incrementHeight(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in height.") { @Override public String applyEffect() { return target.incrementHeight(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in height.") { @Override public String applyEffect() { return target.incrementHeight(5); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in height.") { @Override public String applyEffect() { return target.incrementHeight(15); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in muscle mass.") { @Override public String applyEffect() { return target.incrementMuscle(-15); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in muscle mass.") { @Override public String applyEffect() { return target.incrementMuscle(-5); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in muscle mass.") { @Override public String applyEffect() { return target.incrementMuscle(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in muscle mass.") { @Override public String applyEffect() { return target.incrementMuscle(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in muscle mass.") { @Override public String applyEffect() { return target.incrementMuscle(5); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in muscle mass.") { @Override public String applyEffect() { return target.incrementMuscle(15); } };
						}
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in body size.") { @Override public String applyEffect() { return target.incrementBodySize(-15); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in body size.") { @Override public String applyEffect() { return target.incrementBodySize(-5); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in body size.") { @Override public String applyEffect() { return target.incrementBodySize(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in body size.") { @Override public String applyEffect() { return target.incrementBodySize(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in body size.") { @Override public String applyEffect() { return target.incrementBodySize(5); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in body size.") { @Override public String applyEffect() { return target.incrementBodySize(15); } };
						}
					case TF_MOD_FEMININITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in femininity.") { @Override public String applyEffect() { return target.incrementFemininity(-15); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in femininity.") { @Override public String applyEffect() { return target.incrementFemininity(-5); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in femininity.") { @Override public String applyEffect() { return target.incrementFemininity(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in femininity.") { @Override public String applyEffect() { return target.incrementFemininity(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in femininity.") { @Override public String applyEffect() { return target.incrementFemininity(5); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in femininity.") { @Override public String applyEffect() { return target.incrementFemininity(15); } };
						}
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Removes all pubic hair.") { @Override public String applyEffect() { return target.incrementPubicHair(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Removes a large amount of pubic hair.") { @Override public String applyEffect() { return target.incrementPubicHair(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes some pubic hair.") { @Override public String applyEffect() { return target.incrementPubicHair(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds some pubic hair.") { @Override public String applyEffect() { return target.incrementPubicHair(1); } };
							case BOOST:
								return new RacialEffectUtil("Adds a large amount of pubic hair.") { @Override public String applyEffect() { return target.incrementPubicHair(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Adds a huge amount of pubic hair.") { @Override public String applyEffect() { return target.incrementPubicHair(3); } };
						}
						
					default:
						return new RacialEffectUtil("Random "+race.getName()+" transformation") {
							@Override
							public String applyEffect() {
								TFModifier mod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
								
								return getRacialEffect(race, mod, secondaryModifier, potency, user, target).applyEffect();
							}
						};
				}
				
			case TF_EARS:
				return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" ears transformation.") { @Override public String applyEffect() { return target.setEarType(RacialBody.valueOfRace(race).getEarType()); } };
				
			case TF_EYES:
				switch(secondaryModifier) {
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of eyes.") { @Override public String applyEffect() { return target.incrementEyePairs(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of eyes.") { @Override public String applyEffect() { return target.incrementEyePairs(1); } };
						}

					case TF_MOD_EYE_IRIS_CIRCLE:
						return new RacialEffectUtil("Gives irises a round shape.") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.ROUND); } };
					case TF_MOD_EYE_IRIS_VERTICAL:
						return new RacialEffectUtil("Gives irises a vertical shape.") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.VERTICAL); } };
					case TF_MOD_EYE_IRIS_HORIZONTAL:
						return new RacialEffectUtil("Gives irises a horizontal shape.") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.HORIZONTAL); } };
					case TF_MOD_EYE_IRIS_HEART:
						return new RacialEffectUtil("Gives irises a heart shape.") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.HEART); } };
					case TF_MOD_EYE_IRIS_STAR:
						return new RacialEffectUtil("Gives irises a star shape.") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.STAR); } };
						
					case TF_MOD_EYE_PUPIL_CIRCLE:
						return new RacialEffectUtil("Gives pupils a round shape.") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.ROUND); } };
					case TF_MOD_EYE_PUPIL_VERTICAL:
						return new RacialEffectUtil("Gives pupils a vertical shape.") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.VERTICAL); } };
					case TF_MOD_EYE_PUPIL_HORIZONTAL:
						return new RacialEffectUtil("Gives pupils a horizontal shape.") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.HORIZONTAL); } };
					case TF_MOD_EYE_PUPIL_HEART:
						return new RacialEffectUtil("Gives pupils a heart shape.") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.HEART); } };
					case TF_MOD_EYE_PUPIL_STAR:
						return new RacialEffectUtil("Gives pupils a star shape.") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.STAR); } };
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" eyes transformation.") { @Override public String applyEffect() { return target.setEyeType(RacialBody.valueOfRace(race).getEyeType()); } };
				}
				
			case TF_FACE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in lip size.") { @Override public String applyEffect() { return target.incrementLipSize(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in lip size.") { @Override public String applyEffect() { return target.incrementLipSize(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in lip size.") { @Override public String applyEffect() { return target.incrementLipSize(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in lip size.") { @Override public String applyEffect() { return target.incrementLipSize(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in lip size.") { @Override public String applyEffect() { return target.incrementLipSize(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in lip size.") { @Override public String applyEffect() { return target.incrementLipSize(3); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes puffiness from lips.") { @Override public String applyEffect() { return target.removeFaceOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes lips extra puffy.") { @Override public String applyEffect() { return target.addFaceOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal ribbing from throat.") { @Override public String applyEffect() { return target.removeFaceOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal ribbing to throat.") { @Override public String applyEffect() { return target.addFaceOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal muscles from throat.") { @Override public String applyEffect() { return target.removeFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal muscles to throat.") { @Override public String applyEffect() { return target.addFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal tentacles from throat.") { @Override public String applyEffect() { return target.removeFaceOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal tentacles to throat.") { @Override public String applyEffect() { return target.addFaceOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in tongue length.") { @Override public String applyEffect() { return target.incrementTongueLength(-15); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in tongue length.") { @Override public String applyEffect() { return target.incrementTongueLength(-5); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in tongue length.") { @Override public String applyEffect() { return target.incrementTongueLength(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in tongue length.") { @Override public String applyEffect() { return target.incrementTongueLength(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in tongue length.") { @Override public String applyEffect() { return target.incrementTongueLength(5); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in tongue length.") { @Override public String applyEffect() { return target.incrementTongueLength(15); } };
						}
					case TF_MOD_TONGUE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes ribbing from tongue.") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds ribbing to tongue.") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.RIBBED); } };
						}
					case TF_MOD_TONGUE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes tentacles from tongue.") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds tentacles to tongue.") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.TENTACLED); } };
						}
					case TF_MOD_TONGUE_BIFURCATED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes bifurcation from tongue.") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.BIFURCATED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds bifurcation to tongue.") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.BIFURCATED); } };
						}
						
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Removes all facial hair.") { @Override public String applyEffect() { return target.incrementFacialHair(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Removes a large amount of facial hair.") { @Override public String applyEffect() { return target.incrementFacialHair(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes some facial hair.") { @Override public String applyEffect() { return target.incrementFacialHair(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds some facial hair.") { @Override public String applyEffect() { return target.incrementFacialHair(1); } };
							case BOOST:
								return new RacialEffectUtil("Adds a large amount of facial hair.") { @Override public String applyEffect() { return target.incrementFacialHair(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Adds a huge amount of facial hair.") { @Override public String applyEffect() { return target.incrementFacialHair(3); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" face transformation.") { @Override public String applyEffect() { return target.setFaceType(RacialBody.valueOfRace(race).getFaceType()); } };
				}
				
			case TF_HAIR:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in hair length.") { @Override public String applyEffect() { return target.incrementHairLength(-15); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in hair length.") { @Override public String applyEffect() { return target.incrementHairLength(-5); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in hair length.") { @Override public String applyEffect() { return target.incrementHairLength(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in hair length.") { @Override public String applyEffect() { return target.incrementHairLength(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in hair length.") { @Override public String applyEffect() { return target.incrementHairLength(5); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in hair length.") { @Override public String applyEffect() { return target.incrementHairLength(15); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" hair transformation.") { @Override public String applyEffect() { return target.setHairType(RacialBody.valueOfRace(race).getHairType()); } };
				}
				
			case TF_HORNS:
				switch(secondaryModifier) {
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of horns.") { @Override public String applyEffect() { return target.incrementHornRows(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of horns.") { @Override public String applyEffect() { return target.incrementHornRows(1); } };
						}
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" horns transformation.") { @Override public String applyEffect() { return target.setHornType(RacialBody.valueOfRace(race).getHornType()); } };
				}
				
			case TF_LEGS:
				return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" legs transformation.") { @Override public String applyEffect() { return target.setLegType(RacialBody.valueOfRace(race).getLegType()); } };
				
			case TF_PENIS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in penis size.") { @Override public String applyEffect() { return target.incrementPenisSize(-15); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in penis size.") { @Override public String applyEffect() { return target.incrementPenisSize(-5); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in penis size.") { @Override public String applyEffect() { return target.incrementPenisSize(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in penis size.") { @Override public String applyEffect() { return target.incrementPenisSize(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in penis size.") { @Override public String applyEffect() { return target.incrementPenisSize(5); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in penis size.") { @Override public String applyEffect() { return target.incrementPenisSize(15); } };
						}
					case REMOVAL:
							return new RacialEffectUtil("Removes penis.") { @Override public String applyEffect() { return target.setPenisType(PenisType.NONE); } };
								
					case TF_MOD_PENIS_BARBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes barbs from penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.BARBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds barbs to penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.BARBED); } };
						}
					case TF_MOD_PENIS_FLARED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes flare from penis head.") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.FLARED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds flare to penis head.") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.FLARED); } };
						}
					case TF_MOD_PENIS_KNOTTED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes knot from base of penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.KNOTTED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds knot to base of penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.KNOTTED); } };
						}
					case TF_MOD_PENIS_PREHENSILE:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes prehensility from penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.PREHENSILE); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds prehensility to penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.PREHENSILE); } };
						}
					case TF_MOD_PENIS_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes ribbing from penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds ribbing to penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.RIBBED); } };
						}
					case TF_MOD_PENIS_SHEATHED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes sheath from penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.SHEATHED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds sheath to penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.SHEATHED); } };
						}
					case TF_MOD_PENIS_TAPERED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes tapering from penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.TAPERED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds tapering to penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.TAPERED); } };
						}
					case TF_MOD_PENIS_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes tentacles from penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds tentacles to penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.TENTACLED); } };
						}
					case TF_MOD_PENIS_VEINY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes bulging veins from penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.VEINY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds bulging veins to penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.VEINY); } };
						}

					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in testicle size.") { @Override public String applyEffect() { return target.incrementTesticleSize(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in testicle size.") { @Override public String applyEffect() { return target.incrementTesticleSize(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in testicle size.") { @Override public String applyEffect() { return target.incrementTesticleSize(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in testicle size.") { @Override public String applyEffect() { return target.incrementTesticleSize(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in testicle size.") { @Override public String applyEffect() { return target.incrementTesticleSize(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in testicle size.") { @Override public String applyEffect() { return target.incrementTesticleSize(3); } };
						}
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of testicles.") { @Override public String applyEffect() { return target.incrementTesticleCount(-2); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of testicles.") { @Override public String applyEffect() { return target.incrementTesticleCount(2); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in cum production.") { @Override public String applyEffect() { return target.incrementPenisCumProduction(-50); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in cum production.") { @Override public String applyEffect() { return target.incrementPenisCumProduction(-15); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in cum production.") { @Override public String applyEffect() { return target.incrementPenisCumProduction(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in cum production.") { @Override public String applyEffect() { return target.incrementPenisCumProduction(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in cum production.") { @Override public String applyEffect() { return target.incrementPenisCumProduction(15); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in cum production.") { @Override public String applyEffect() { return target.incrementPenisCumProduction(50); } };
						}
					case TF_MOD_INTERNAL:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Makes testicles external.") { @Override public String applyEffect() { return target.setInternalTesticles(false); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes testicles internal.") { @Override public String applyEffect() { return target.setInternalTesticles(true); } };
						}
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in urethra capacity.") { @Override public String applyEffect() { return target.incrementPenisCapacity(-15, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in urethra capacity.") { @Override public String applyEffect() { return target.incrementPenisCapacity(-5, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in urethra capacity.") { @Override public String applyEffect() { return target.incrementPenisCapacity(-1, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in urethra capacity.") { @Override public String applyEffect() { return target.incrementPenisCapacity(1, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in urethra capacity.") { @Override public String applyEffect() { return target.incrementPenisCapacity(5, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in urethra capacity.") { @Override public String applyEffect() { return target.incrementPenisCapacity(15, true); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in urethra elasticity.") { @Override public String applyEffect() { return target.incrementUrethraElasticity(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in urethra elasticity.") { @Override public String applyEffect() { return target.incrementUrethraElasticity(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in urethra elasticity.") { @Override public String applyEffect() { return target.incrementUrethraElasticity(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in urethra elasticity.") { @Override public String applyEffect() { return target.incrementUrethraElasticity(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in urethra elasticity.") { @Override public String applyEffect() { return target.incrementUrethraElasticity(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in urethra elasticity.") { @Override public String applyEffect() { return target.incrementUrethraElasticity(3); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in urethra plasticity.") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in urethra plasticity.") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in urethra plasticity.") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in urethra plasticity.") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in urethra plasticity.") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in urethra plasticity.") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(3); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes puffiness from urethra.") { @Override public String applyEffect() { return target.removeUrethraOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes urethra extra puffy.") { @Override public String applyEffect() { return target.addUrethraOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal ribbing from urethra.") { @Override public String applyEffect() { return target.removeUrethraOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal ribbing to urethra.") { @Override public String applyEffect() { return target.addUrethraOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal muscles from urethra.") { @Override public String applyEffect() { return target.removeUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal muscles to urethra.") { @Override public String applyEffect() { return target.addUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal tentacles from urethra.") { @Override public String applyEffect() { return target.removeUrethraOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal tentacles to urethra.") { @Override public String applyEffect() { return target.addUrethraOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" penis transformation.") { @Override public String applyEffect() { return target.setPenisType(RacialBody.valueOfRace(race).getPenisType()); } };
				}
				
			case TF_SKIN:
				return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" skin transformation.") { @Override public String applyEffect() { return target.setSkinType(RacialBody.valueOfRace(race).getSkinType()); } };
				
			case TF_TAIL:
				switch(secondaryModifier) {
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra tail.") { @Override public String applyEffect() { return target.incrementTailCount(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra tail.") { @Override public String applyEffect() { return target.incrementTailCount(1); } };
						}
					default:
						if(RacialBody.valueOfRace(race).getTailType() == TailType.NONE) {
							return new RacialEffectUtil("Removes tail.") { @Override public String applyEffect() { return target.setTailType(RacialBody.valueOfRace(race).getTailType()); } };
						} else {
							return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" tail transformation.") { @Override public String applyEffect() { return target.setTailType(RacialBody.valueOfRace(race).getTailType()); } };
						}
				}
				
			case TF_VAGINA:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in clitoris size.") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(-15); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in clitoris size.") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(-5); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in clitoris size.") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in clitoris size.") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in clitoris size.") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(5); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in clitoris size.") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(15); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in labia size.") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in labia size.") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in labia size.") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in labia size.") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in labia size.") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in labia size.") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(3); } };
						}
					case REMOVAL:
							return new RacialEffectUtil("Removes vagina.") { @Override public String applyEffect() { return target.setVaginaType(VaginaType.NONE); } };
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in vagina capacity.") { @Override public String applyEffect() { return target.incrementVaginaCapacity(-15, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in vagina capacity.") { @Override public String applyEffect() { return target.incrementVaginaCapacity(-5, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in vagina capacity.") { @Override public String applyEffect() { return target.incrementVaginaCapacity(-1, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in vagina capacity.") { @Override public String applyEffect() { return target.incrementVaginaCapacity(1, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in vagina capacity.") { @Override public String applyEffect() { return target.incrementVaginaCapacity(5, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in vagina capacity.") { @Override public String applyEffect() { return target.incrementVaginaCapacity(15, true); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in vagina elasticity.") { @Override public String applyEffect() { return target.incrementVaginaElasticity(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in vagina elasticity.") { @Override public String applyEffect() { return target.incrementVaginaElasticity(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in vagina elasticity.") { @Override public String applyEffect() { return target.incrementVaginaElasticity(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in vagina elasticity.") { @Override public String applyEffect() { return target.incrementVaginaElasticity(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in vagina elasticity.") { @Override public String applyEffect() { return target.incrementVaginaElasticity(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in vagina elasticity.") { @Override public String applyEffect() { return target.incrementVaginaElasticity(3); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in vagina plasticity.") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in vagina plasticity.") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in vagina plasticity.") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in vagina plasticity.") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in vagina plasticity.") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in vagina plasticity.") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(3); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in natural vaginal lubrication.") { @Override public String applyEffect() { return target.incrementVaginaWetness(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in natural vaginal lubrication.") { @Override public String applyEffect() { return target.incrementVaginaWetness(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in natural vaginal lubrication.") { @Override public String applyEffect() { return target.incrementVaginaWetness(-1); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in natural vaginal lubrication.") { @Override public String applyEffect() { return target.incrementVaginaWetness(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in natural vaginal lubrication.") { @Override public String applyEffect() { return target.incrementVaginaWetness(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in natural vaginal lubrication.") { @Override public String applyEffect() { return target.incrementVaginaWetness(3); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes puffiness from labia.") { @Override public String applyEffect() { return target.removeVaginaOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes labia extra puffy.") { @Override public String applyEffect() { return target.addVaginaOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal ribbing from vagina.") { @Override public String applyEffect() { return target.removeVaginaOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal ribbing to vagina.") { @Override public String applyEffect() { return target.addVaginaOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal muscles from vagina.") { @Override public String applyEffect() { return target.removeVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal muscles to vagina.") { @Override public String applyEffect() { return target.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal tentacles from vagina.") { @Override public String applyEffect() { return target.removeVaginaOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal tentacles to vagina.") { @Override public String applyEffect() { return target.addVaginaOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" vagina transformation.") { @Override public String applyEffect() { return target.setVaginaType(RacialBody.valueOfRace(race).getVaginaType()); } };
				}
				
			case TF_WINGS:
				if(RacialBody.valueOfRace(race).getWingType() == WingType.NONE) {
					return new RacialEffectUtil("Removes wings.") { @Override public String applyEffect() { return target.setWingType(RacialBody.valueOfRace(race).getWingType()); } };
				} else {
					return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" wings transformation.") { @Override public String applyEffect() { return target.setWingType(RacialBody.valueOfRace(race).getWingType()); } };
				}
				
			case TF_CUM:
				switch(secondaryModifier) {
					case TF_MOD_FLAVOUR_BEER:
						return new RacialEffectUtil("Makes cum taste like beer.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.BEER); } };
					case TF_MOD_FLAVOUR_CHOCOLATE:
						return new RacialEffectUtil("Makes cum taste like chocolate.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.CHOCOLATE); } };
					case TF_MOD_FLAVOUR_CUM:
						return new RacialEffectUtil("Makes cum taste like cum.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.CUM); } };
					case TF_MOD_FLAVOUR_GIRLCUM:
						return new RacialEffectUtil("Makes cum taste like girlcum.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.GIRL_CUM); } };
					case TF_MOD_FLAVOUR_MILK:
						return new RacialEffectUtil("Makes cum taste like milk.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.MILK); } };
					case TF_MOD_FLAVOUR_HONEY:
						return new RacialEffectUtil("Makes cum taste like honey.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.HONEY); } };
					case TF_MOD_FLAVOUR_MINT:
						return new RacialEffectUtil("Makes cum taste like mint.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.MINT); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("Makes cum taste like pineapple.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_SLIME:
						return new RacialEffectUtil("Makes cum taste like slime.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.SLIME); } };
					case TF_MOD_FLAVOUR_STRAWBERRY:
						return new RacialEffectUtil("Makes cum taste like strawberries.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.STRAWBERRY); } };
					case TF_MOD_FLAVOUR_VANILLA:
						return new RacialEffectUtil("Makes cum taste like vanilla.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.VANILLA); } };
	
					case TF_MOD_FLUID_ADDICTIVE:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes addictive effect from cum.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.ADDICTIVE); } };
						} else {
							return new RacialEffectUtil("Adds an addictive effect to cum.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.ADDICTIVE); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes alcoholic effect from cum.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.ALCOHOLIC); } };
						} else {
							return new RacialEffectUtil("Makes cum alcoholic.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.ALCOHOLIC); } };
						}
					case TF_MOD_FLUID_BUBBLING:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes bubbling effect from cum.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.BUBBLING); } };
						} else {
							return new RacialEffectUtil("Makes cum fizzy and bubbly.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.BUBBLING); } };
						}
					case TF_MOD_FLUID_HALLUCINOGENIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes hallucinogenic effect from cum.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("Adds a hallucinogenic effect to cum.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MUSKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes musky effect from cum.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.MUSKY); } };
						} else {
							return new RacialEffectUtil("Makes cum give off a potent musk.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.MUSKY); } };
						}
					case TF_MOD_FLUID_SLIMY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes slimy effect from cum.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.SLIMY); } };
						} else {
							return new RacialEffectUtil("Makes cum slimy.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.SLIMY); } };
						}
					case TF_MOD_FLUID_STICKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes sticky effect from cum.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.STICKY); } };
						} else {
							return new RacialEffectUtil("Makes cum sticky.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.STICKY); } };
						}
					case TF_MOD_FLUID_VISCOUS:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes viscous effect from cum.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.VISCOUS); } };
						} else {
							return new RacialEffectUtil("Makes cum thick and viscous.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.VISCOUS); } };
						}
						
					default:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in cum production.") { @Override public String applyEffect() { return target.incrementPenisCumProduction(-50); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in cum production.") { @Override public String applyEffect() { return target.incrementPenisCumProduction(-15); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in cum production.") { @Override public String applyEffect() { return target.incrementPenisCumProduction(-1); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("Small increase in cum production.") { @Override public String applyEffect() { return target.incrementPenisCumProduction(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in cum production.") { @Override public String applyEffect() { return target.incrementPenisCumProduction(15); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in cum production.") { @Override public String applyEffect() { return target.incrementPenisCumProduction(50); } };
						}
				}
				break;
				
			case TF_MILK:
				switch(secondaryModifier) {
					case TF_MOD_FLAVOUR_BEER:
						return new RacialEffectUtil("Makes milk taste like beer.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.BEER); } };
					case TF_MOD_FLAVOUR_CHOCOLATE:
						return new RacialEffectUtil("Makes milk taste like chocolate.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.CHOCOLATE); } };
					case TF_MOD_FLAVOUR_CUM:
						return new RacialEffectUtil("Makes milk taste like cum.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.CUM); } };
					case TF_MOD_FLAVOUR_GIRLCUM:
						return new RacialEffectUtil("Makes milk taste like girlcum.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.GIRL_CUM); } };
					case TF_MOD_FLAVOUR_MILK:
						return new RacialEffectUtil("Makes milk taste like milk.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.MILK); } };
					case TF_MOD_FLAVOUR_HONEY:
						return new RacialEffectUtil("Makes milk taste like honey.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.HONEY); } };
					case TF_MOD_FLAVOUR_MINT:
						return new RacialEffectUtil("Makes milk taste like mint.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.MINT); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("Makes milk taste like pineapple.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_SLIME:
						return new RacialEffectUtil("Makes milk taste like slime.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.SLIME); } };
					case TF_MOD_FLAVOUR_STRAWBERRY:
						return new RacialEffectUtil("Makes milk taste like strawberries.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.STRAWBERRY); } };
					case TF_MOD_FLAVOUR_VANILLA:
						return new RacialEffectUtil("Makes milk taste like vanilla.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.VANILLA); } };
	
					case TF_MOD_FLUID_ADDICTIVE:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes addictive effect from milk.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.ADDICTIVE); } };
						} else {
							return new RacialEffectUtil("Adds an addictive effect to milk.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.ADDICTIVE); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes alcoholic effect from milk.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.ALCOHOLIC); } };
						} else {
							return new RacialEffectUtil("Makes milk alcoholic.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.ALCOHOLIC); } };
						}
					case TF_MOD_FLUID_BUBBLING:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes bubbling effect from milk.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.BUBBLING); } };
						} else {
							return new RacialEffectUtil("Makes milk fizzy and bubbly.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.BUBBLING); } };
						}
					case TF_MOD_FLUID_HALLUCINOGENIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes hallucinogenic effect from milk.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("Adds a hallucinogenic effect to milk.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MUSKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes musky effect from milk.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.MUSKY); } };
						} else {
							return new RacialEffectUtil("Makes milk give off a potent musk.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.MUSKY); } };
						}
					case TF_MOD_FLUID_SLIMY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes slimy effect from milk.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.SLIMY); } };
						} else {
							return new RacialEffectUtil("Makes milk slimy.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.SLIMY); } };
						}
					case TF_MOD_FLUID_STICKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes sticky effect from milk.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.STICKY); } };
						} else {
							return new RacialEffectUtil("Makes milk sticky.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.STICKY); } };
						}
					case TF_MOD_FLUID_VISCOUS:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes viscous effect from milk.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.VISCOUS); } };
						} else {
							return new RacialEffectUtil("Makes milk thick and viscous.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.VISCOUS); } };
						}
						
					default:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in lactation.") { @Override public String applyEffect() { return target.incrementBreastLactation(-50); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in lactation.") { @Override public String applyEffect() { return target.incrementBreastLactation(-15); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in lactation.") { @Override public String applyEffect() { return target.incrementBreastLactation(-1); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("Small increase in lactation.") { @Override public String applyEffect() { return target.incrementBreastLactation(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in lactation.") { @Override public String applyEffect() { return target.incrementBreastLactation(15); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in lactation.") { @Override public String applyEffect() { return target.incrementBreastLactation(50); } };
						}
				}
				break;
					
			case TF_GIRLCUM:
				switch(secondaryModifier) {
					case TF_MOD_FLAVOUR_BEER:
						return new RacialEffectUtil("Makes girlcum taste like beer.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.BEER); } };
					case TF_MOD_FLAVOUR_CHOCOLATE:
						return new RacialEffectUtil("Makes girlcum taste like chocolate.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.CHOCOLATE); } };
					case TF_MOD_FLAVOUR_CUM:
						return new RacialEffectUtil("Makes girlcum taste like cum.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.CUM); } };
					case TF_MOD_FLAVOUR_GIRLCUM:
						return new RacialEffectUtil("Makes girlcum taste like girlcum.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.GIRL_CUM); } };
					case TF_MOD_FLAVOUR_MILK:
						return new RacialEffectUtil("Makes girlcum taste like milk.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.MILK); } };
					case TF_MOD_FLAVOUR_HONEY:
						return new RacialEffectUtil("Makes girlcum taste like honey.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.HONEY); } };
					case TF_MOD_FLAVOUR_MINT:
						return new RacialEffectUtil("Makes girlcum taste like mint.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.MINT); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("Makes girlcum taste like pineapple.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_SLIME:
						return new RacialEffectUtil("Makes girlcum taste like slime.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.SLIME); } };
					case TF_MOD_FLAVOUR_STRAWBERRY:
						return new RacialEffectUtil("Makes girlcum taste like strawberries.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.STRAWBERRY); } };
					case TF_MOD_FLAVOUR_VANILLA:
						return new RacialEffectUtil("Makes girlcum taste like vanilla.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.VANILLA); } };
	
					case TF_MOD_FLUID_ADDICTIVE:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes addictive effect from girlcum.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.ADDICTIVE); } };
						} else {
							return new RacialEffectUtil("Adds an addictive effect to girlcum.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.ADDICTIVE); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes alcoholic effect from girlcum.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.ALCOHOLIC); } };
						} else {
							return new RacialEffectUtil("Makes girlcum alcoholic.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.ALCOHOLIC); } };
						}
					case TF_MOD_FLUID_BUBBLING:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes bubbling effect from girlcum.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.BUBBLING); } };
						} else {
							return new RacialEffectUtil("Makes girlcum fizzy and bubbly.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.BUBBLING); } };
						}
					case TF_MOD_FLUID_HALLUCINOGENIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes hallucinogenic effect from girlcum.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("Adds a hallucinogenic effect to girlcum.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MUSKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes musky effect from girlcum.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.MUSKY); } };
						} else {
							return new RacialEffectUtil("Makes girlcum give off a potent musk.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.MUSKY); } };
						}
					case TF_MOD_FLUID_SLIMY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes slimy effect from girlcum.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.SLIMY); } };
						} else {
							return new RacialEffectUtil("Makes girlcum slimy.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.SLIMY); } };
						}
					case TF_MOD_FLUID_STICKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes sticky effect from girlcum.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.STICKY); } };
						} else {
							return new RacialEffectUtil("Makes girlcum sticky.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.STICKY); } };
						}
					case TF_MOD_FLUID_VISCOUS:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes viscous effect from girlcum.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.VISCOUS); } };
						} else {
							return new RacialEffectUtil("Makes girlcum thick and viscous.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.VISCOUS); } };
						}
						
					default:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in natural vaginal lubrication.") { @Override public String applyEffect() { return target.incrementVaginaWetness(-3); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in natural vaginal lubrication.") { @Override public String applyEffect() { return target.incrementVaginaWetness(-2); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in natural vaginal lubrication.") { @Override public String applyEffect() { return target.incrementVaginaWetness(-1); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("Small increase in natural vaginal lubrication.") { @Override public String applyEffect() { return target.incrementVaginaWetness(1); } };
							case BOOST:
								return new RacialEffectUtil("Increase in natural vaginal lubrication.") { @Override public String applyEffect() { return target.incrementVaginaWetness(2); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in natural vaginal lubrication.") { @Override public String applyEffect() { return target.incrementVaginaWetness(3); } };
						}
				}
				break;
				
			default:
				return new RacialEffectUtil("Random non-racial transformation") {
					@Override
					public String applyEffect() {
						TFModifier mod = TFModifier.NONE, modSecondary = TFModifier.NONE;
						
						while (mod == TFModifier.NONE || modSecondary == TFModifier.NONE) {
							mod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
							modSecondary = getRacialSecondaryModifiers(mod).get(Util.random.nextInt(getRacialSecondaryModifiers(mod).size()));
						}
						
						TFPotency pot = getRacialPotencyModifiers(mod, modSecondary).get(Util.random.nextInt(getRacialPotencyModifiers(mod, modSecondary).size()));
						
						return getRacialEffect(race, mod, modSecondary, pot, user, target).applyEffect();
					}
				};
		}
		
		return new RacialEffectUtil("Random non-racial transformation") {
			@Override
			public String applyEffect() {
				TFModifier mod = TFModifier.NONE, modSecondary = TFModifier.NONE;
				
				while (mod == TFModifier.NONE || modSecondary == TFModifier.NONE) {
					mod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
					modSecondary = getRacialSecondaryModifiers(mod).get(Util.random.nextInt(getRacialSecondaryModifiers(mod).size()));
				}
				
				TFPotency pot = getRacialPotencyModifiers(mod, modSecondary).get(Util.random.nextInt(getRacialPotencyModifiers(mod, modSecondary).size()));
				
				return getRacialEffect(race, mod, modSecondary, pot, user, target).applyEffect();
			}
		};
	}
	
}
