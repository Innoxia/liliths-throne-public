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
			return ""; // THIS EFFECT IS NOT USED, AS AbstractFilledCondom OVERRIDES THE USUAL AbstractItem's applyEffects() METHOD!!!
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
	
	ADDICTION_REMOVAL(Util.newArrayListOfValues(
			new ListValue<>("[style.boldExcellent(Removes all addictions)]")),
			Colour.BASE_GOLD) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			boolean hadAddictions = !target.getAddictionsMap().isEmpty();
			target.clearAllAddictions();
			
			if(target.isPlayer()) {
				if(hadAddictions) {
					return "You feel a deep sense of calm wash over you, and, letting out a deep sigh, you find that you no longer have any addictions!";
				} else {
					return "You feel a deep sense of calm wash over you, but other than causing you to let out a deep sigh, you find that the potion doesn't do anything...";
				}
				
			} else {
				if(hadAddictions) {
					return UtilText.parse(target, "[npc.Name] feels a deep sense of calm wash over [npc.herHim], and, letting out a deep sigh, [npc.she] finds that [npc.she] no longer has any addictions!");
				} else {
					return UtilText.parse(target, "[npc.Name] feels a deep sense of calm wash over [npc.herHim], but other than causing [npc.herHim] to let out a deep sigh, [npc.she] finds that the potion doesn't do anything...");
				}
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
			return getRacialSecondaryModifiers(Race.DEMON, primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(Race.DEMON, primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.DEMON, primaryModifier, secondaryModifier, potency, user, target).getDescriptionPlusChangeDescription()));
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
			return getRacialSecondaryModifiers(Race.HUMAN, primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(Race.HUMAN, primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.HUMAN, primaryModifier, secondaryModifier, potency, user, target).getDescriptionPlusChangeDescription()));
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
			return getRacialSecondaryModifiers(Race.CAT_MORPH, primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(Race.CAT_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.CAT_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescriptionPlusChangeDescription()));
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
			return getRacialSecondaryModifiers(Race.COW_MORPH, primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(Race.COW_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.COW_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescriptionPlusChangeDescription()));
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
			return getRacialSecondaryModifiers(Race.SQUIRREL_MORPH, primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(Race.SQUIRREL_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.SQUIRREL_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescriptionPlusChangeDescription()));
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
			return getRacialSecondaryModifiers(Race.DOG_MORPH, primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(Race.DOG_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.DOG_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescriptionPlusChangeDescription()));
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
			return getRacialSecondaryModifiers(Race.HORSE_MORPH, primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(Race.HORSE_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.HORSE_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescriptionPlusChangeDescription()));
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
			return getRacialSecondaryModifiers(Race.WOLF_MORPH, primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(Race.WOLF_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.WOLF_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescriptionPlusChangeDescription()));
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
			return getRacialSecondaryModifiers(Race.HARPY, primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(Race.HARPY, primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.HARPY, primaryModifier, secondaryModifier, potency, user, target).getDescriptionPlusChangeDescription()));
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
		}
		if(Main.game.getPlayer().addBooksRead(book)) {
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
	
	private static List<TFModifier> getRacialSecondaryModifiers(Race race, TFModifier primaryModifier) {
		if(racialPrimaryModSecondaryModPotencyGrid.containsKey(primaryModifier)) {
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(primaryModifier).keySet());
		} else {
//			racialPrimaryModSecondaryModPotencyGrid.clear();
//			for(TFModifier mod : TFModifier.values()) {
//				for(TFPotency potency : TFPotency.values()) {
//					if(getRacialEffect(race, primaryModifier, mod, potency, user, target)!=null) {
//						racialPrimaryModSecondaryModPotencyGrid.putIfAbsent(key, value);
//						racialPrimaryModSecondaryModPotencyGrid.put(primaryModifier, secondaryModPotencyMap);
//					}
//				}
//			}
			populateGrid(race, primaryModifier);
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(primaryModifier).keySet());
		}
	}
	
	private static List<TFPotency> getRacialPotencyModifiers(Race race, TFModifier primaryModifier, TFModifier secondaryModifier) {
		if(racialPrimaryModSecondaryModPotencyGrid.containsKey(primaryModifier)) {
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(primaryModifier).get(secondaryModifier));
		} else {
			populateGrid(race, primaryModifier);
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(primaryModifier).get(secondaryModifier));
		}
	}
	
	private static void populateGrid(Race race, TFModifier primaryModifier){ //TODO Please make this better -.-
		LinkedHashMap<TFModifier, List<TFPotency>> secondaryModPotencyMap = new LinkedHashMap<>();
		
		switch(primaryModifier) {
			case TF_ANTENNA:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
			
			case TF_ARMS:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				if(Main.game.isBodyHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_ASS:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
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
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
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
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FEMININITY, TFPotency.getAllPotencies());
				if(Main.game.isPubicHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_EARS:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
				
			case TF_EYES:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
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
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
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
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				break;
				
			case TF_HORNS:
				for(int i=0; i< RacialBody.valueOfRace(race).getHornType().size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
				
			case TF_LEGS:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
				
			case TF_PENIS:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
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
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
				
			case TF_TAIL:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
				
			case TF_VAGINA:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
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
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
				
			case TF_CUM: case TF_MILK: case TF_GIRLCUM:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
				
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
				secondaryModPotencyMap.put(TFModifier.NONE, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
		}
		
		racialPrimaryModSecondaryModPotencyGrid.put(primaryModifier, secondaryModPotencyMap);
	}
	
	// And in the comments these words appear: 'My name is Innoxia, creator of smut: Look on my methods, ye Modders, and despair!'
	
	private static int smallChangeMajorDrain = -3;
	private static int smallChangeDrain = -2;
	private static int smallChangeMinorDrain = -1;
	private static int smallChangeMinorBoost = 1;
	private static int smallChangeBoost = 2;
	private static int smallChangeMajorBoost = 3;
	
	private static int mediumChangeMajorDrain = -15;
	private static int mediumChangeDrain = -5;
	private static int mediumChangeMinorDrain = -1;
	private static int mediumChangeMinorBoost = 1;
	private static int mediumChangeBoost = 5;
	private static int mediumChangeMajorBoost = 15;
	
	private static int largeChangeMajorDrain = -50;
	private static int largeChangeDrain = -15;
	private static int largeChangeMinorDrain = -1;
	private static int largeChangeMinorBoost = 1;
	private static int largeChangeBoost = 15;
	private static int largeChangeMajorBoost = 50;

	private static int singleDrain = -1;
	private static int singleBoost = 1;
	
	private static RacialEffectUtil getRacialEffect(Race race, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, GameCharacter user, GameCharacter target) {
		switch(primaryModifier) {
			case TF_ANTENNA:
				switch(secondaryModifier) {
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of antennae.", singleDrain, "") { @Override public String applyEffect() { return target.incrementAntennaRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of antennae.", singleBoost, "") { @Override public String applyEffect() { return target.incrementAntennaRows(singleBoost); } };
						}
					default:
						if(RacialBody.valueOfRace(race).getAntennaType() == AntennaType.NONE) {
							return new RacialEffectUtil("Removes antennae.", 0, "") { @Override public String applyEffect() { return target.setAntennaType(RacialBody.valueOfRace(race).getAntennaType()); } };
						} else {
							return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" antennae transformation.", 0, "") { @Override public String applyEffect() { return target.setAntennaType(RacialBody.valueOfRace(race).getAntennaType()); } };
						}
				}
			
			case TF_ARMS:
				switch(secondaryModifier) {
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of arms.", singleDrain, "") { @Override public String applyEffect() { return target.incrementArmRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of arms.", singleBoost, "") { @Override public String applyEffect() { return target.incrementArmRows(singleBoost); } };
						}
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Removes a huge amount of underarm hair.", smallChangeMajorDrain, " hairiness") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Removes a large amount of underarm hair.", smallChangeDrain, " hairiness") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes some underarm hair.", smallChangeMinorDrain, " hairiness") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds some underarm hair.", smallChangeMinorBoost, " hairiness") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Adds a large amount of underarm hair.", smallChangeBoost, " hairiness") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Adds a huge amount of underarm hair.", smallChangeMajorBoost, " hairiness") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeMajorBoost); } };
						}
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" arm transformation.", 0, "") { @Override public String applyEffect() { return target.setArmType(RacialBody.valueOfRace(race).getArmType()); } };
				}
				
			case TF_ASS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in ass size.", smallChangeMajorDrain, " ass size") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in ass size.", smallChangeDrain, " ass size") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in ass size.", smallChangeMinorDrain, " ass size") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in ass size.", smallChangeMinorBoost, " ass size") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in ass size.", smallChangeBoost, " ass size") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in ass size.", smallChangeMajorBoost, " ass size") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in hip size.", smallChangeMajorDrain, " hip size") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in hip size.", smallChangeDrain, " hip size") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in hip size.", smallChangeMinorDrain, " hip size") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in hip size.", smallChangeMinorBoost, " hip size") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in hip size.", smallChangeBoost, " hip size") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in hip size.", smallChangeMajorBoost, " hip size") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Removes a huge amount of ass hair.", smallChangeMajorDrain, " hairiness") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Removes a large amount of ass hair.", smallChangeDrain, " hairiness") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes some ass hair.", smallChangeMinorDrain, " hairiness") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds some ass hair.", smallChangeMinorBoost, " hairiness") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Adds a large amount of ass hair.", smallChangeBoost, " hairiness") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Adds a huge amount of ass hair.", smallChangeMajorBoost, " hairiness") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in anal capacity.", mediumChangeMajorDrain, " capacity") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in anal capacity.", mediumChangeDrain, " capacity") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in anal capacity.", mediumChangeMinorDrain, " capacity") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in anal capacity.", mediumChangeMinorBoost, " capacity") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in anal capacity.", mediumChangeBoost, " capacity") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in anal capacity.", mediumChangeMajorBoost, " capacity") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in anal elasticity.", smallChangeMajorDrain, " elasticity") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in anal elasticity.", smallChangeDrain, " elasticity") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in anal elasticity.", smallChangeMinorDrain, " elasticity") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in anal elasticity.", smallChangeMinorBoost, " elasticity") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in anal elasticity.", smallChangeBoost, " elasticity") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in anal elasticity.", smallChangeMajorBoost, " elasticity") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in anal plasticity.", smallChangeMajorDrain, " plasticity") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in anal plasticity.", smallChangeDrain, " plasticity") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in anal plasticity.", smallChangeMinorDrain, " plasticity") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in anal plasticity.", smallChangeMinorBoost, " plasticity") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in anal plasticity.", smallChangeBoost, " plasticity") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in anal plasticity.", smallChangeMajorBoost, " plasticity") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in natural anal lubrication.", smallChangeMajorDrain, " wetness") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in natural anal lubrication.", smallChangeDrain, " wetness") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in natural anal lubrication.", smallChangeMinorDrain, " wetness") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in natural anal lubrication.", smallChangeMinorBoost, " wetness") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in natural anal lubrication.", smallChangeBoost, " wetness") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in natural anal lubrication.", smallChangeMajorBoost, " wetness") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes puffiness from anal rim.", 0, "") { @Override public String applyEffect() { return target.removeAssOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes anal rim puffy.", 0, "") { @Override public String applyEffect() { return target.addAssOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal ribbing from anus.", 0, "") { @Override public String applyEffect() { return target.removeAssOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal ribbing to anus.", 0, "") { @Override public String applyEffect() { return target.addAssOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes extra internal muscles from anus.", 0, "") { @Override public String applyEffect() { return target.removeAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds extra internal muscles to anus.", 0, "") { @Override public String applyEffect() { return target.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal tentacles from anus.", 0, "") { @Override public String applyEffect() { return target.removeAssOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal tentacles to anus.", 0, "") { @Override public String applyEffect() { return target.addAssOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" ass transformation.", 0, "") { @Override public String applyEffect() { return target.setAssType(RacialBody.valueOfRace(race).getAssType()); } };
				}
				
			case TF_BREASTS:
				switch(secondaryModifier) {
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of breasts.", singleDrain, " pair of breasts") { @Override public String applyEffect() { return target.incrementBreastRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of breasts.", singleBoost, " pair of breasts") { @Override public String applyEffect() { return target.incrementBreastRows(singleBoost); } };
						}
					case TF_MOD_COUNT_SECONDARY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra nipple from each breast.", singleDrain, " nipple on each breast") { @Override public String applyEffect() { return target.incrementNippleCountPerBreast(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra nipple to each breast.", singleBoost, " nipple on each breast") { @Override public String applyEffect() { return target.incrementNippleCountPerBreast(singleBoost); } };
						}
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in breast size.", smallChangeMajorDrain, " breast size") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in breast size.", smallChangeDrain, " breast size") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in breast size.", smallChangeMinorDrain, " breast size") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in breast size.", smallChangeMinorBoost, " breast size") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in breast size.", smallChangeBoost, " breast size") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in breast size.", smallChangeMajorBoost, " breast size") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMajorBoost); } };
						}
						

					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in nipple size.", smallChangeMajorDrain, " nipple size") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in nipple size.", smallChangeDrain, " nipple size") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in nipple size.", smallChangeMinorDrain, " nipple size") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in nipple size.", smallChangeMinorBoost, " nipple size") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in nipple size.", smallChangeBoost, " nipple size") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in nipple size.", smallChangeMajorBoost, " nipple size") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMajorBoost); } };
						}

					case TF_MOD_BREAST_SHAPE_ROUND:
						return new RacialEffectUtil("Transforms breast shape into being round.", 0, "") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.ROUND); } };
					case TF_MOD_BREAST_SHAPE_PERKY:
						return new RacialEffectUtil("Transforms breast shape into being perky.", 0, "") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.PERKY); } };
					case TF_MOD_BREAST_SHAPE_POINTY:
						return new RacialEffectUtil("Transforms breast shape into being pointy.", 0, "") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.POINTY); } };
					case TF_MOD_BREAST_SHAPE_SIDESET:
						return new RacialEffectUtil("Transforms breast shape into being sideset.", 0, "") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.SIDE_SET); } };
					case TF_MOD_BREAST_SHAPE_WIDE:
						return new RacialEffectUtil("Transforms breast shape into being wide.", 0, "") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.WIDE); } };
					case TF_MOD_BREAST_SHAPE_NARROW:
						return new RacialEffectUtil("Transforms breast shape into being narrow.", 0, "") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.NARROW); } };
						
					case TF_MOD_NIPPLE_NORMAL:
						return new RacialEffectUtil("Turns nipples into a normal, human-like shape.", 0, "") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.NORMAL); } };
					case TF_MOD_NIPPLE_VAGINA:
						return new RacialEffectUtil("Turns nipples into vaginas.", 0, "") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.VAGINA); } };
					case TF_MOD_NIPPLE_LIPS:
						return new RacialEffectUtil("Turns nipples into pairs of lips.", 0, "") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.LIPS); } };
						
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in areolae size.", smallChangeMajorDrain, " areolae size") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in areolae size.", smallChangeDrain, " areolae size") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in areolae size.", smallChangeMinorDrain, " areolae size") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in areolae size.", smallChangeMinorBoost, " areolae size") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in areolae size.", smallChangeBoost, " areolae size") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in areolae size.", smallChangeMajorBoost, " areolae size") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMajorBoost); } };
						}
					case TF_MOD_AREOLAE_CIRCLE:
						return new RacialEffectUtil("Turns the shape of areolae into normal circles.", 0, "") { @Override public String applyEffect() { return target.setAreolaeShape(AreolaeShape.NORMAL); } };
					case TF_MOD_AREOLAE_HEART:
						return new RacialEffectUtil("Turns the shape of areolae into hearts.", 0, "") { @Override public String applyEffect() { return target.setAreolaeShape(AreolaeShape.HEART); } };
					case TF_MOD_AREOLAE_STAR:
						return new RacialEffectUtil("Turns the shape of areolae into stars.", 0, "") { @Override public String applyEffect() { return target.setAreolaeShape(AreolaeShape.STAR); } };
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in nipple capacity.", mediumChangeMajorDrain, " capacity") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in nipple capacity.", mediumChangeDrain, " capacity") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in nipple capacity.", mediumChangeMinorDrain, " capacity") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in nipple capacity.", mediumChangeMinorBoost, " capacity") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in nipple capacity.", mediumChangeBoost, " capacity") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in nipple capacity.", mediumChangeMajorBoost, " capacity") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in nipple elasticity.", smallChangeMajorDrain, " elasticity") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in nipple elasticity.", smallChangeDrain, " elasticity") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in nipple elasticity.", smallChangeMinorDrain, " elasticity") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in nipple elasticity.", smallChangeMinorBoost, " elasticity") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in nipple elasticity.", smallChangeBoost, " elasticity") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in nipple elasticity.", smallChangeMajorBoost, " elasticity") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in nipple plasticity.", smallChangeMajorDrain, " plasticity") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in nipple plasticity.", smallChangeDrain, " plasticity") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in nipple plasticity.", smallChangeMinorDrain, " plasticity") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in nipple plasticity.", smallChangeMinorBoost, " plasticity") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in nipple plasticity.", smallChangeBoost, " plasticity") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in nipple plasticity.", smallChangeMajorBoost, " plasticity") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in lactation.", largeChangeMajorDrain, "ml") { @Override public String applyEffect() { return target.incrementBreastLactation(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in lactation.", largeChangeDrain, "ml") { @Override public String applyEffect() { return target.incrementBreastLactation(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in lactation.", largeChangeMinorDrain, "ml") { @Override public String applyEffect() { return target.incrementBreastLactation(largeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in lactation.", largeChangeMinorBoost, "ml") { @Override public String applyEffect() { return target.incrementBreastLactation(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in lactation.", largeChangeBoost, "ml") { @Override public String applyEffect() { return target.incrementBreastLactation(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in lactation.", largeChangeMajorBoost, "ml") { @Override public String applyEffect() { return target.incrementBreastLactation(largeChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes puffiness from nipples.", 0, "") { @Override public String applyEffect() { return target.removeNippleOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes nipples extra puffy.", 0, "") { @Override public String applyEffect() { return target.addNippleOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal ribbing from nipples.", 0, "") { @Override public String applyEffect() { return target.removeNippleOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal ribbing to nipples.", 0, "") { @Override public String applyEffect() { return target.addNippleOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal muscles from nipples.", 0, "") { @Override public String applyEffect() { return target.removeNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal muscles to nipples.", 0, "") { @Override public String applyEffect() { return target.addNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal tentacles from nipples.", 0, "") { @Override public String applyEffect() { return target.removeNippleOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal tentacles to nipples.", 0, "") { @Override public String applyEffect() { return target.addNippleOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" breast transformation.", 0, "") { @Override public String applyEffect() { return target.setBreastType(RacialBody.valueOfRace(race).getBreastType()); } };
				}
				
			case TF_CORE: 
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in height.", mediumChangeMajorDrain, "cm") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in height.", mediumChangeDrain, "cm") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in height.", mediumChangeMinorDrain, "cm") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in height.", mediumChangeMinorBoost, "cm") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in height.", mediumChangeBoost, "cm") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in height.", mediumChangeMajorBoost, "cm") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in muscle mass.", mediumChangeMajorDrain, " muscles") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in muscle mass.", mediumChangeDrain, " muscles") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in muscle mass.", mediumChangeMinorDrain, " muscles") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in muscle mass.", mediumChangeMinorBoost, " muscles") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in muscle mass.", mediumChangeBoost, " muscles") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in muscle mass.", mediumChangeMajorBoost, " muscles") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in body size.", mediumChangeMajorDrain, " body size") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in body size.", mediumChangeDrain, " body size") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in body size.", mediumChangeMinorDrain, " body size") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in body size.", mediumChangeMinorBoost, " body size") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in body size.", mediumChangeBoost, " body size") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in body size.", mediumChangeMajorBoost, " body size") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMajorBoost); } };
						}
					case TF_MOD_FEMININITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in femininity.", mediumChangeMajorDrain, " femininity") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in femininity.", mediumChangeDrain, " femininity") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in femininity.", mediumChangeMinorDrain, " femininity") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in femininity.", mediumChangeMinorBoost, " femininity") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in femininity.", mediumChangeBoost, " femininity") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in femininity.", mediumChangeMajorBoost, " femininity") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMajorBoost); } };
						}
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Removes a huge amount of pubic hair.", smallChangeMajorDrain, " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Removes a large amount of pubic hair.", smallChangeDrain, " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes some pubic hair.", smallChangeMinorDrain, " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds some pubic hair.", smallChangeMinorBoost, " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Adds a large amount of pubic hair.", smallChangeBoost, " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Adds a huge amount of pubic hair.", smallChangeMajorBoost, " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorBoost); } };
						}
						
					default:
						return new RacialEffectUtil("Random "+race.getName()+" transformation", 0, "") {
							@Override
							public String applyEffect() {
								TFModifier mod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
								
								return getRacialEffect(race, mod, secondaryModifier, potency, user, target).applyEffect();
							}
						};
				}
				
			case TF_EARS:
				return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" ears transformation.", 0, "") { @Override public String applyEffect() { return target.setEarType(RacialBody.valueOfRace(race).getEarType()); } };
				
			case TF_EYES:
				switch(secondaryModifier) {
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of eyes.", singleDrain, " pair of eyes") { @Override public String applyEffect() { return target.incrementEyePairs(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of eyes.", singleBoost, " pair of eyes") { @Override public String applyEffect() { return target.incrementEyePairs(singleBoost); } };
						}

					case TF_MOD_EYE_IRIS_CIRCLE:
						return new RacialEffectUtil("Gives irises a round shape.", 0, "") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.ROUND); } };
					case TF_MOD_EYE_IRIS_VERTICAL:
						return new RacialEffectUtil("Gives irises a vertical shape.", 0, "") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.VERTICAL); } };
					case TF_MOD_EYE_IRIS_HORIZONTAL:
						return new RacialEffectUtil("Gives irises a horizontal shape.", 0, "") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.HORIZONTAL); } };
					case TF_MOD_EYE_IRIS_HEART:
						return new RacialEffectUtil("Gives irises a heart shape.", 0, "") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.HEART); } };
					case TF_MOD_EYE_IRIS_STAR:
						return new RacialEffectUtil("Gives irises a star shape.", 0, "") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.STAR); } };
						
					case TF_MOD_EYE_PUPIL_CIRCLE:
						return new RacialEffectUtil("Gives pupils a round shape.", 0, "") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.ROUND); } };
					case TF_MOD_EYE_PUPIL_VERTICAL:
						return new RacialEffectUtil("Gives pupils a vertical shape.", 0, "") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.VERTICAL); } };
					case TF_MOD_EYE_PUPIL_HORIZONTAL:
						return new RacialEffectUtil("Gives pupils a horizontal shape.", 0, "") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.HORIZONTAL); } };
					case TF_MOD_EYE_PUPIL_HEART:
						return new RacialEffectUtil("Gives pupils a heart shape.", 0, "") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.HEART); } };
					case TF_MOD_EYE_PUPIL_STAR:
						return new RacialEffectUtil("Gives pupils a star shape.", 0, "") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.STAR); } };
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" eyes transformation.", 0, "") { @Override public String applyEffect() { return target.setEyeType(RacialBody.valueOfRace(race).getEyeType()); } };
				}
				
			case TF_FACE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in lip size.", smallChangeMajorDrain, " lip size") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in lip size.", smallChangeDrain, " lip size") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in lip size.", smallChangeMinorDrain, " lip size") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in lip size.", smallChangeMinorBoost, " lip size") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in lip size.", smallChangeBoost, " lip size") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in lip size.", smallChangeMajorBoost, " lip size") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes puffiness from lips.", 0, "") { @Override public String applyEffect() { return target.removeFaceOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes lips extra puffy.", 0, "") { @Override public String applyEffect() { return target.addFaceOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal ribbing from throat.", 0, "") { @Override public String applyEffect() { return target.removeFaceOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal ribbing to throat.", 0, "") { @Override public String applyEffect() { return target.addFaceOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal muscles from throat.", 0, "") { @Override public String applyEffect() { return target.removeFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal muscles to throat.", 0, "") { @Override public String applyEffect() { return target.addFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal tentacles from throat.", 0, "") { @Override public String applyEffect() { return target.removeFaceOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal tentacles to throat.", 0, "") { @Override public String applyEffect() { return target.addFaceOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in tongue length.", mediumChangeMajorDrain, " inches") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in tongue length.", mediumChangeDrain, " inches") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in tongue length.", mediumChangeMinorDrain, " inches") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in tongue length.", mediumChangeMinorBoost, " inches") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in tongue length.", mediumChangeBoost, " inches") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in tongue length.", mediumChangeMajorBoost, " inches") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMajorBoost); } };
						}
					case TF_MOD_TONGUE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes ribbing from tongue.", 0, "") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds ribbing to tongue.", 0, "") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.RIBBED); } };
						}
					case TF_MOD_TONGUE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes tentacles from tongue.", 0, "") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds tentacles to tongue.", 0, "") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.TENTACLED); } };
						}
					case TF_MOD_TONGUE_BIFURCATED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes bifurcation from tongue.", 0, "") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.BIFURCATED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds bifurcation to tongue.", 0, "") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.BIFURCATED); } };
						}
						
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Removes a huge amount of facial hair.", smallChangeMajorDrain, " hairiness") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Removes a large amount of facial hair.", smallChangeDrain, " hairiness") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes some facial hair.", smallChangeMinorDrain, " hairiness") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds some facial hair.", smallChangeMinorBoost, " hairiness") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Adds a large amount of facial hair.", smallChangeBoost, " hairiness") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Adds a huge amount of facial hair.", smallChangeMajorBoost, " hairiness") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeMajorBoost); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" face transformation.", 0, "") { @Override public String applyEffect() { return target.setFaceType(RacialBody.valueOfRace(race).getFaceType()); } };
				}
				
			case TF_HAIR:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in hair length.", mediumChangeMajorDrain, " inches") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in hair length.", mediumChangeDrain, " inches") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in hair length.", mediumChangeMinorDrain, " inches") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in hair length.", mediumChangeMinorBoost, " inches") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in hair length.", mediumChangeBoost, " inches") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in hair length.", mediumChangeMajorBoost, " inches") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMajorBoost); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" hair transformation.", 0, "") { @Override public String applyEffect() { return target.setHairType(RacialBody.valueOfRace(race).getHairType()); } };
				}
				
			case TF_HORNS:
				switch(secondaryModifier) {
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of horns.", singleDrain, " pair of horns") { @Override public String applyEffect() { return target.incrementHornRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of horns.", singleBoost, " pair of horns") { @Override public String applyEffect() { return target.incrementHornRows(singleBoost); } };
						}
					case TF_TYPE_1:
						return new RacialEffectUtil((RacialBody.valueOfRace(race).getHornType().get(0)==HornType.NONE?"Removes horns.":"Grows "+RacialBody.valueOfRace(race).getHornType().get(0).getName(true, Main.game.getPlayer())+"."), 0, "") {
							@Override public String applyEffect() { return target.setHornType(RacialBody.valueOfRace(race).getHornType().get(0)); } };

					case TF_TYPE_2:
						return new RacialEffectUtil((RacialBody.valueOfRace(race).getHornType().get(1)==HornType.NONE?"Removes horns.":"Grows "+RacialBody.valueOfRace(race).getHornType().get(1).getName(true, Main.game.getPlayer())+"."), 0, "") {
							@Override public String applyEffect() { return target.setHornType(RacialBody.valueOfRace(race).getHornType().get(1)); } };

					case TF_TYPE_3:
						return new RacialEffectUtil((RacialBody.valueOfRace(race).getHornType().get(2)==HornType.NONE?"Removes horns.":"Grows "+RacialBody.valueOfRace(race).getHornType().get(2).getName(true, Main.game.getPlayer())+"."), 0, "") {
							@Override public String applyEffect() { return target.setHornType(RacialBody.valueOfRace(race).getHornType().get(2)); } };

					case TF_TYPE_4:
						return new RacialEffectUtil((RacialBody.valueOfRace(race).getHornType().get(3)==HornType.NONE?"Removes horns.":"Grows "+RacialBody.valueOfRace(race).getHornType().get(3).getName(true, Main.game.getPlayer())+"."), 0, "") {
							@Override public String applyEffect() { return target.setHornType(RacialBody.valueOfRace(race).getHornType().get(3)); } };

					case TF_TYPE_5:
						return new RacialEffectUtil((RacialBody.valueOfRace(race).getHornType().get(4)==HornType.NONE?"Removes horns.":"Grows "+RacialBody.valueOfRace(race).getHornType().get(4).getName(true, Main.game.getPlayer())+"."), 0, "") {
							@Override public String applyEffect() { return target.setHornType(RacialBody.valueOfRace(race).getHornType().get(4)); } };
							
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" horns transformation.", 0, "") { @Override public String applyEffect() { return target.setHornType(RacialBody.valueOfRace(race).getRandomHornType(false)); } };
				}
				
			case TF_LEGS:
				return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" legs transformation.", 0, "") { @Override public String applyEffect() { return target.setLegType(RacialBody.valueOfRace(race).getLegType()); } };
				
			case TF_PENIS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in penis size.", mediumChangeMajorDrain, " inches") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in penis size.", mediumChangeDrain, " inches") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in penis size.", mediumChangeMinorDrain, " inches") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in penis size.", mediumChangeMinorBoost, " inches") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in penis size.", mediumChangeBoost, " inches") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in penis size.", mediumChangeMajorBoost, " inches") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMajorBoost); } };
						}
					case REMOVAL:
							return new RacialEffectUtil("Removes penis.", 0, "") { @Override public String applyEffect() { return target.setPenisType(PenisType.NONE); } };
								
					case TF_MOD_PENIS_BARBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes barbs from penis.", 0, "") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.BARBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds barbs to penis.", 0, "") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.BARBED); } };
						}
					case TF_MOD_PENIS_FLARED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes flare from penis head.", 0, "") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.FLARED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds flare to penis head.", 0, "") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.FLARED); } };
						}
					case TF_MOD_PENIS_KNOTTED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes knot from base of penis.", 0, "") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.KNOTTED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds knot to base of penis.", 0, "") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.KNOTTED); } };
						}
					case TF_MOD_PENIS_PREHENSILE:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes prehensility from penis.", 0, "") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.PREHENSILE); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds prehensility to penis.", 0, "") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.PREHENSILE); } };
						}
					case TF_MOD_PENIS_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes ribbing from penis.", 0, "") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds ribbing to penis.", 0, "") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.RIBBED); } };
						}
					case TF_MOD_PENIS_SHEATHED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes sheath from penis.", 0, "") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.SHEATHED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds sheath to penis.", 0, "") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.SHEATHED); } };
						}
					case TF_MOD_PENIS_TAPERED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes tapering from penis.", 0, "") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.TAPERED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds tapering to penis.", 0, "") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.TAPERED); } };
						}
					case TF_MOD_PENIS_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes tentacles from penis.", 0, "") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds tentacles to penis.", 0, "") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.TENTACLED); } };
						}
					case TF_MOD_PENIS_VEINY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes bulging veins from penis.", 0, "") { @Override public String applyEffect() { return target.removePenisModifier(PenisModifier.VEINY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds bulging veins to penis.", 0, "") { @Override public String applyEffect() { return target.addPenisModifier(PenisModifier.VEINY); } };
						}

					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in testicle size.", smallChangeMajorDrain, " size") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in testicle size.", smallChangeDrain, " size") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in testicle size.", smallChangeMinorDrain, " size") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in testicle size.", smallChangeMinorBoost, " size") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in testicle size.", smallChangeBoost, " size") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in testicle size.", smallChangeMajorBoost, " size") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMajorBoost); } };
						}
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of testicles.", smallChangeDrain, " testicles") { @Override public String applyEffect() { return target.incrementTesticleCount(smallChangeDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of testicles.", smallChangeBoost, " testicles") { @Override public String applyEffect() { return target.incrementTesticleCount(smallChangeBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in cum production.", largeChangeMajorDrain, "ml") { @Override public String applyEffect() { return target.incrementPenisCumProduction(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in cum production.", largeChangeDrain, "ml") { @Override public String applyEffect() { return target.incrementPenisCumProduction(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in cum production.", largeChangeMinorDrain, "ml") { @Override public String applyEffect() { return target.incrementPenisCumProduction(largeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in cum production.", largeChangeMinorBoost, "ml") { @Override public String applyEffect() { return target.incrementPenisCumProduction(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in cum production.", largeChangeBoost, "ml") { @Override public String applyEffect() { return target.incrementPenisCumProduction(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in cum production.", largeChangeMajorBoost, "ml") { @Override public String applyEffect() { return target.incrementPenisCumProduction(largeChangeMajorBoost); } };
						}
					case TF_MOD_INTERNAL:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Makes testicles external.", 0, "") { @Override public String applyEffect() { return target.setInternalTesticles(false); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes testicles internal.", 0, "") { @Override public String applyEffect() { return target.setInternalTesticles(true); } };
						}
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in urethra capacity.", mediumChangeMajorDrain, " capacity") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in urethra capacity.", mediumChangeDrain, " capacity") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in urethra capacity.", mediumChangeMinorDrain, " capacity") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in urethra capacity.", mediumChangeMinorBoost, " capacity") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in urethra capacity.", mediumChangeBoost, " capacity") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in urethra capacity.", mediumChangeMajorBoost, " capacity") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in urethra elasticity.", smallChangeMajorDrain, " elasticity") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in urethra elasticity.", smallChangeDrain, " elasticity") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in urethra elasticity.", smallChangeMinorDrain, " elasticity") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in urethra elasticity.", smallChangeMinorBoost, " elasticity") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in urethra elasticity.", smallChangeBoost, " elasticity") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in urethra elasticity.", smallChangeMajorBoost, " elasticity") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in urethra plasticity.", smallChangeMajorDrain, " plasticity") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in urethra plasticity.", smallChangeDrain, " plasticity") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in urethra plasticity.", smallChangeMinorDrain, " plasticity") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in urethra plasticity.", smallChangeMinorBoost, " plasticity") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in urethra plasticity.", smallChangeBoost, " plasticity") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in urethra plasticity.", smallChangeMajorBoost, " plasticity") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes puffiness from urethra.", 0, "") { @Override public String applyEffect() { return target.removeUrethraOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes urethra extra puffy.", 0, "") { @Override public String applyEffect() { return target.addUrethraOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal ribbing from urethra.", 0, "") { @Override public String applyEffect() { return target.removeUrethraOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal ribbing to urethra.", 0, "") { @Override public String applyEffect() { return target.addUrethraOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal muscles from urethra.", 0, "") { @Override public String applyEffect() { return target.removeUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal muscles to urethra.", 0, "") { @Override public String applyEffect() { return target.addUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal tentacles from urethra.", 0, "") { @Override public String applyEffect() { return target.removeUrethraOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal tentacles to urethra.", 0, "") { @Override public String applyEffect() { return target.addUrethraOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" penis transformation.", 0, "") { @Override public String applyEffect() { return target.setPenisType(RacialBody.valueOfRace(race).getPenisType()); } };
				}
				
			case TF_SKIN:
				return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" skin transformation.", 0, "") { @Override public String applyEffect() { return target.setSkinType(RacialBody.valueOfRace(race).getSkinType()); } };
				
			case TF_TAIL:
				switch(secondaryModifier) {
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra tail.", singleDrain, " tail") { @Override public String applyEffect() { return target.incrementTailCount(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra tail.", singleBoost, " tail") { @Override public String applyEffect() { return target.incrementTailCount(singleBoost); } };
						}
					default:
						if(RacialBody.valueOfRace(race).getTailType() == TailType.NONE) {
							return new RacialEffectUtil("Removes tail.", 0, "") { @Override public String applyEffect() { return target.setTailType(RacialBody.valueOfRace(race).getTailType()); } };
						} else {
							return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" tail transformation.", 0, "") { @Override public String applyEffect() { return target.setTailType(RacialBody.valueOfRace(race).getTailType()); } };
						}
				}
				
			case TF_VAGINA:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in clitoris size.", mediumChangeMajorDrain, " inches") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in clitoris size.", mediumChangeDrain, " inches") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in clitoris size.", mediumChangeMinorDrain, " inches") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in clitoris size.", mediumChangeMinorBoost, " inches") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in clitoris size.", mediumChangeBoost, " inches") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in clitoris size.", mediumChangeMajorBoost, " inches") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in labia size.", smallChangeMajorDrain, " size") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in labia size.", smallChangeDrain, " size") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in labia size.", smallChangeMinorDrain, " size") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in labia size.", smallChangeMinorBoost, " size") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in labia size.", smallChangeBoost, " size") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in labia size.", smallChangeMajorBoost, " size") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMajorBoost); } };
						}
					case REMOVAL:
							return new RacialEffectUtil("Removes vagina.", 0, "") { @Override public String applyEffect() { return target.setVaginaType(VaginaType.NONE); } };
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in vagina capacity.", mediumChangeMajorDrain, " capacity") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in vagina capacity.", mediumChangeDrain, " capacity") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in vagina capacity.", mediumChangeMinorDrain, " capacity") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in vagina capacity.", mediumChangeMinorBoost, " capacity") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in vagina capacity.", mediumChangeBoost, " capacity") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in vagina capacity.", mediumChangeMajorBoost, " capacity") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in vagina elasticity.", smallChangeMajorDrain, " elasticity") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in vagina elasticity.", smallChangeDrain, " elasticity") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in vagina elasticity.", smallChangeMinorDrain, " elasticity") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in vagina elasticity.", smallChangeMinorBoost, " elasticity") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in vagina elasticity.", smallChangeBoost, " elasticity") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in vagina elasticity.", smallChangeMajorBoost, " elasticity") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in vagina plasticity.", smallChangeMajorDrain, " plasticity") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in vagina plasticity.", smallChangeDrain, " plasticity") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in vagina plasticity.", smallChangeMinorDrain, " plasticity") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in vagina plasticity.", smallChangeMinorBoost, " plasticity") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in vagina plasticity.", smallChangeBoost, " plasticity") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in vagina plasticity.", smallChangeMajorBoost, " plasticity") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in natural vaginal lubrication.", smallChangeMajorDrain, "ml") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in natural vaginal lubrication.", smallChangeDrain, "ml") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in natural vaginal lubrication.", smallChangeMinorDrain, "ml") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in natural vaginal lubrication.", smallChangeMinorBoost, "ml") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in natural vaginal lubrication.", smallChangeBoost, "ml") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in natural vaginal lubrication.", smallChangeMajorBoost, "ml") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes puffiness from labia.", 0, "") { @Override public String applyEffect() { return target.removeVaginaOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes labia extra puffy.", 0, "") { @Override public String applyEffect() { return target.addVaginaOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal ribbing from vagina.", 0, "") { @Override public String applyEffect() { return target.removeVaginaOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal ribbing to vagina.", 0, "") { @Override public String applyEffect() { return target.addVaginaOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal muscles from vagina.", 0, "") { @Override public String applyEffect() { return target.removeVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal muscles to vagina.", 0, "") { @Override public String applyEffect() { return target.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal tentacles from vagina.", 0, "") { @Override public String applyEffect() { return target.removeVaginaOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal tentacles to vagina.", 0, "") { @Override public String applyEffect() { return target.addVaginaOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" vagina transformation.", 0, "") { @Override public String applyEffect() { return target.setVaginaType(RacialBody.valueOfRace(race).getVaginaType()); } };
				}
				
			case TF_WINGS:
				if(RacialBody.valueOfRace(race).getWingType() == WingType.NONE) {
					return new RacialEffectUtil("Removes wings.", 0, "") { @Override public String applyEffect() { return target.setWingType(RacialBody.valueOfRace(race).getWingType()); } };
				} else {
					return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" wings transformation.", 0, "") { @Override public String applyEffect() { return target.setWingType(RacialBody.valueOfRace(race).getWingType()); } };
				}
				
			case TF_CUM:
				switch(secondaryModifier) {
					case TF_MOD_FLAVOUR_BEER:
						return new RacialEffectUtil("Makes cum taste like beer.", 0, "") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.BEER); } };
					case TF_MOD_FLAVOUR_CHOCOLATE:
						return new RacialEffectUtil("Makes cum taste like chocolate.", 0, "") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.CHOCOLATE); } };
					case TF_MOD_FLAVOUR_CUM:
						return new RacialEffectUtil("Makes cum taste like cum.", 0, "") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.CUM); } };
					case TF_MOD_FLAVOUR_GIRLCUM:
						return new RacialEffectUtil("Makes cum taste like girlcum.", 0, "") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.GIRL_CUM); } };
					case TF_MOD_FLAVOUR_MILK:
						return new RacialEffectUtil("Makes cum taste like milk.", 0, "") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.MILK); } };
					case TF_MOD_FLAVOUR_HONEY:
						return new RacialEffectUtil("Makes cum taste like honey.", 0, "") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.HONEY); } };
					case TF_MOD_FLAVOUR_MINT:
						return new RacialEffectUtil("Makes cum taste like mint.", 0, "") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.MINT); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("Makes cum taste like pineapple.", 0, "") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_SLIME:
						return new RacialEffectUtil("Makes cum taste like slime.", 0, "") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.SLIME); } };
					case TF_MOD_FLAVOUR_STRAWBERRY:
						return new RacialEffectUtil("Makes cum taste like strawberries.", 0, "") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.STRAWBERRY); } };
					case TF_MOD_FLAVOUR_VANILLA:
						return new RacialEffectUtil("Makes cum taste like vanilla.", 0, "") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.VANILLA); } };
	
					case TF_MOD_FLUID_ADDICTIVE:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes addictive effect from cum.", 0, "") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.ADDICTIVE); } };
						} else {
							return new RacialEffectUtil("Adds an addictive effect to cum.", 0, "") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.ADDICTIVE); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes alcoholic effect from cum.", 0, "") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.ALCOHOLIC); } };
						} else {
							return new RacialEffectUtil("Makes cum alcoholic.", 0, "") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.ALCOHOLIC); } };
						}
					case TF_MOD_FLUID_BUBBLING:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes bubbling effect from cum.", 0, "") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.BUBBLING); } };
						} else {
							return new RacialEffectUtil("Makes cum fizzy and bubbly.", 0, "") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.BUBBLING); } };
						}
					case TF_MOD_FLUID_HALLUCINOGENIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes psychoactive effect from cum.", 0, "") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("Adds a psychoactive effect to cum.", 0, "") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MUSKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes musky effect from cum.", 0, "") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.MUSKY); } };
						} else {
							return new RacialEffectUtil("Makes cum give off a potent musk.", 0, "") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.MUSKY); } };
						}
					case TF_MOD_FLUID_SLIMY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes slimy effect from cum.", 0, "") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.SLIMY); } };
						} else {
							return new RacialEffectUtil("Makes cum slimy.", 0, "") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.SLIMY); } };
						}
					case TF_MOD_FLUID_STICKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes sticky effect from cum.", 0, "") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.STICKY); } };
						} else {
							return new RacialEffectUtil("Makes cum sticky.", 0, "") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.STICKY); } };
						}
					case TF_MOD_FLUID_VISCOUS:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes viscous effect from cum.", 0, "") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.VISCOUS); } };
						} else {
							return new RacialEffectUtil("Makes cum thick and viscous.", 0, "") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.VISCOUS); } };
						}
						
					default:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in cum production.", largeChangeMajorDrain, "ml") { @Override public String applyEffect() { return target.incrementPenisCumProduction(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in cum production.", largeChangeDrain, "ml") { @Override public String applyEffect() { return target.incrementPenisCumProduction(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in cum production.", largeChangeMinorDrain, "ml") { @Override public String applyEffect() { return target.incrementPenisCumProduction(largeChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("Small increase in cum production.", largeChangeMinorBoost, "ml") { @Override public String applyEffect() { return target.incrementPenisCumProduction(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in cum production.", largeChangeBoost, "ml") { @Override public String applyEffect() { return target.incrementPenisCumProduction(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in cum production.", largeChangeMajorBoost, "ml") { @Override public String applyEffect() { return target.incrementPenisCumProduction(largeChangeMajorBoost); } };
						}
				}
				break;
				
			case TF_MILK:
				switch(secondaryModifier) {
					case TF_MOD_FLAVOUR_BEER:
						return new RacialEffectUtil("Makes milk taste like beer.", 0, "") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.BEER); } };
					case TF_MOD_FLAVOUR_CHOCOLATE:
						return new RacialEffectUtil("Makes milk taste like chocolate.", 0, "") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.CHOCOLATE); } };
					case TF_MOD_FLAVOUR_CUM:
						return new RacialEffectUtil("Makes milk taste like cum.", 0, "") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.CUM); } };
					case TF_MOD_FLAVOUR_GIRLCUM:
						return new RacialEffectUtil("Makes milk taste like girlcum.", 0, "") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.GIRL_CUM); } };
					case TF_MOD_FLAVOUR_MILK:
						return new RacialEffectUtil("Makes milk taste like milk.", 0, "") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.MILK); } };
					case TF_MOD_FLAVOUR_HONEY:
						return new RacialEffectUtil("Makes milk taste like honey.", 0, "") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.HONEY); } };
					case TF_MOD_FLAVOUR_MINT:
						return new RacialEffectUtil("Makes milk taste like mint.", 0, "") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.MINT); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("Makes milk taste like pineapple.", 0, "") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_SLIME:
						return new RacialEffectUtil("Makes milk taste like slime.", 0, "") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.SLIME); } };
					case TF_MOD_FLAVOUR_STRAWBERRY:
						return new RacialEffectUtil("Makes milk taste like strawberries.", 0, "") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.STRAWBERRY); } };
					case TF_MOD_FLAVOUR_VANILLA:
						return new RacialEffectUtil("Makes milk taste like vanilla.", 0, "") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.VANILLA); } };
	
					case TF_MOD_FLUID_ADDICTIVE:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes addictive effect from milk.", 0, "") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.ADDICTIVE); } };
						} else {
							return new RacialEffectUtil("Adds an addictive effect to milk.", 0, "") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.ADDICTIVE); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes alcoholic effect from milk.", 0, "") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.ALCOHOLIC); } };
						} else {
							return new RacialEffectUtil("Makes milk alcoholic.", 0, "") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.ALCOHOLIC); } };
						}
					case TF_MOD_FLUID_BUBBLING:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes bubbling effect from milk.", 0, "") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.BUBBLING); } };
						} else {
							return new RacialEffectUtil("Makes milk fizzy and bubbly.", 0, "") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.BUBBLING); } };
						}
					case TF_MOD_FLUID_HALLUCINOGENIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes psychoactive effect from milk.", 0, "") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("Adds a psychoactive effect to milk.", 0, "") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MUSKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes musky effect from milk.", 0, "") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.MUSKY); } };
						} else {
							return new RacialEffectUtil("Makes milk give off a potent musk.", 0, "") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.MUSKY); } };
						}
					case TF_MOD_FLUID_SLIMY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes slimy effect from milk.", 0, "") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.SLIMY); } };
						} else {
							return new RacialEffectUtil("Makes milk slimy.", 0, "") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.SLIMY); } };
						}
					case TF_MOD_FLUID_STICKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes sticky effect from milk.", 0, "") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.STICKY); } };
						} else {
							return new RacialEffectUtil("Makes milk sticky.", 0, "") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.STICKY); } };
						}
					case TF_MOD_FLUID_VISCOUS:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes viscous effect from milk.", 0, "") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.VISCOUS); } };
						} else {
							return new RacialEffectUtil("Makes milk thick and viscous.", 0, "") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.VISCOUS); } };
						}
						
					default:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in lactation.", largeChangeMajorDrain, "ml") { @Override public String applyEffect() { return target.incrementBreastLactation(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in lactation.", largeChangeDrain, "ml") { @Override public String applyEffect() { return target.incrementBreastLactation(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in lactation.", largeChangeMinorDrain, "ml") { @Override public String applyEffect() { return target.incrementBreastLactation(largeChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("Small increase in lactation.", largeChangeMinorBoost, "ml") { @Override public String applyEffect() { return target.incrementBreastLactation(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in lactation.", largeChangeBoost, "ml") { @Override public String applyEffect() { return target.incrementBreastLactation(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in lactation.", largeChangeMajorBoost, "ml") { @Override public String applyEffect() { return target.incrementBreastLactation(largeChangeMajorBoost); } };
						}
				}
				break;
					
			case TF_GIRLCUM:
				switch(secondaryModifier) {
					case TF_MOD_FLAVOUR_BEER:
						return new RacialEffectUtil("Makes girlcum taste like beer.", 0, "") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.BEER); } };
					case TF_MOD_FLAVOUR_CHOCOLATE:
						return new RacialEffectUtil("Makes girlcum taste like chocolate.", 0, "") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.CHOCOLATE); } };
					case TF_MOD_FLAVOUR_CUM:
						return new RacialEffectUtil("Makes girlcum taste like cum.", 0, "") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.CUM); } };
					case TF_MOD_FLAVOUR_GIRLCUM:
						return new RacialEffectUtil("Makes girlcum taste like girlcum.", 0, "") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.GIRL_CUM); } };
					case TF_MOD_FLAVOUR_MILK:
						return new RacialEffectUtil("Makes girlcum taste like milk.", 0, "") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.MILK); } };
					case TF_MOD_FLAVOUR_HONEY:
						return new RacialEffectUtil("Makes girlcum taste like honey.", 0, "") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.HONEY); } };
					case TF_MOD_FLAVOUR_MINT:
						return new RacialEffectUtil("Makes girlcum taste like mint.", 0, "") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.MINT); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("Makes girlcum taste like pineapple.", 0, "") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_SLIME:
						return new RacialEffectUtil("Makes girlcum taste like slime.", 0, "") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.SLIME); } };
					case TF_MOD_FLAVOUR_STRAWBERRY:
						return new RacialEffectUtil("Makes girlcum taste like strawberries.", 0, "") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.STRAWBERRY); } };
					case TF_MOD_FLAVOUR_VANILLA:
						return new RacialEffectUtil("Makes girlcum taste like vanilla.", 0, "") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.VANILLA); } };
	
					case TF_MOD_FLUID_ADDICTIVE:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes addictive effect from girlcum.", 0, "") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.ADDICTIVE); } };
						} else {
							return new RacialEffectUtil("Adds an addictive effect to girlcum.", 0, "") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.ADDICTIVE); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes alcoholic effect from girlcum.", 0, "") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.ALCOHOLIC); } };
						} else {
							return new RacialEffectUtil("Makes girlcum alcoholic.", 0, "") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.ALCOHOLIC); } };
						}
					case TF_MOD_FLUID_BUBBLING:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes bubbling effect from girlcum.", 0, "") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.BUBBLING); } };
						} else {
							return new RacialEffectUtil("Makes girlcum fizzy and bubbly.", 0, "") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.BUBBLING); } };
						}
					case TF_MOD_FLUID_HALLUCINOGENIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes psychoactive effect from girlcum.", 0, "") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("Adds a psychoactive effect to girlcum.", 0, "") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MUSKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes musky effect from girlcum.", 0, "") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.MUSKY); } };
						} else {
							return new RacialEffectUtil("Makes girlcum give off a potent musk.", 0, "") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.MUSKY); } };
						}
					case TF_MOD_FLUID_SLIMY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes slimy effect from girlcum.", 0, "") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.SLIMY); } };
						} else {
							return new RacialEffectUtil("Makes girlcum slimy.", 0, "") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.SLIMY); } };
						}
					case TF_MOD_FLUID_STICKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes sticky effect from girlcum.", 0, "") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.STICKY); } };
						} else {
							return new RacialEffectUtil("Makes girlcum sticky.", 0, "") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.STICKY); } };
						}
					case TF_MOD_FLUID_VISCOUS:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes viscous effect from girlcum.", 0, "") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.VISCOUS); } };
						} else {
							return new RacialEffectUtil("Makes girlcum thick and viscous.", 0, "") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.VISCOUS); } };
						}
						
					default:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in natural vaginal lubrication.", smallChangeMajorDrain, "ml") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in natural vaginal lubrication.", smallChangeDrain, "ml") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in natural vaginal lubrication.", smallChangeMinorDrain, "ml") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("Small increase in natural vaginal lubrication.", smallChangeMinorBoost, "ml") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in natural vaginal lubrication.", smallChangeBoost, "ml") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in natural vaginal lubrication.", smallChangeMajorBoost, "ml") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorBoost); } };
						}
				}
				break;
				
			default:
				return new RacialEffectUtil("Random non-racial transformation", 0, "") {
					@Override
					public String applyEffect() {
						TFModifier mod = TFModifier.NONE, modSecondary = TFModifier.NONE;
						
						while (mod == TFModifier.NONE || modSecondary == TFModifier.NONE) {
							mod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
							modSecondary = getRacialSecondaryModifiers(race, mod).get(Util.random.nextInt(getRacialSecondaryModifiers(race, mod).size()));
						}
						
						TFPotency pot = getRacialPotencyModifiers(race, mod, modSecondary).get(Util.random.nextInt(getRacialPotencyModifiers(race, mod, modSecondary).size()));
						
						return getRacialEffect(race, mod, modSecondary, pot, user, target).applyEffect();
					}
				};
		}
		
		return new RacialEffectUtil("Random non-racial transformation", 0, "") {
			@Override
			public String applyEffect() {
				TFModifier mod = TFModifier.NONE, modSecondary = TFModifier.NONE;
				
				while (mod == TFModifier.NONE || modSecondary == TFModifier.NONE) {
					mod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
					modSecondary = getRacialSecondaryModifiers(race, mod).get(Util.random.nextInt(getRacialSecondaryModifiers(race, mod).size()));
				}
				
				TFPotency pot = getRacialPotencyModifiers(race, mod, modSecondary).get(Util.random.nextInt(getRacialPotencyModifiers(race, mod, modSecondary).size()));
				
				return getRacialEffect(race, mod, modSecondary, pot, user, target).applyEffect();
			}
		};
	}
	
}
