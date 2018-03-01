package com.lilithsthrone.game.inventory.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.SexualOrientation;
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
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryBookAddedToLibrary;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;

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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return target.incrementMuscle(-25)
					+ target.incrementBodySize(25)
					+ target.setUnderarmHair(BodyHair.SIX_BUSHY);
		}
	},
	
	DYE_BRUSH(Util.newArrayListOfValues(
				new ListValue<>("Recolours a piece of clothing.")),
			Colour.GENERIC_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p>"
					+ "You use the dye brush."
					+ "</p>";
		}
	},
	
	USED_CONDOM_DRINK(Util.newArrayListOfValues(
			new ListValue<>("Provides a slimy snack.")),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return ""; // THIS EFFECT IS NOT USED, AS AbstractFilledCondom OVERRIDES THE USUAL AbstractItem's applyEffects() METHOD!!!
		}
	},
	
	BOOK_READ_CAT_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds cat-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(arcane)]")),
			Colour.RACE_CAT_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.CAT_MORPH, ItemType.BOOK_CAT_MORPH);
		}
	},
	
	BOOK_READ_COW_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds cow-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(arcane)]")),
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.COW_MORPH, ItemType.BOOK_COW_MORPH);
		}
	},
	
	BOOK_READ_DEMON(Util.newArrayListOfValues(
			new ListValue<>("Adds demon encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(arcane)]")),
			Colour.RACE_DEMON) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.DEMON, ItemType.BOOK_DEMON);
		}
	},
	
	BOOK_READ_DOG_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds dog-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(arcane)]")),
			Colour.RACE_DOG_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.DOG_MORPH, ItemType.BOOK_DOG_MORPH);
		}
	},
	
	BOOK_READ_ALLIGATOR_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds alligator-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(arcane)]")),
			Colour.RACE_ALLIGATOR_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.ALLIGATOR_MORPH, ItemType.BOOK_ALLIGATOR_MORPH);
		}
	},
	
	BOOK_READ_HARPY(Util.newArrayListOfValues(
			new ListValue<>("Adds harpy encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(arcane)]")),
			Colour.RACE_HARPY) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.HARPY, ItemType.BOOK_HARPY);
		}
	},
	
	BOOK_READ_HORSE_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds horse-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(arcane)]")),
			Colour.RACE_HORSE_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.HORSE_MORPH, ItemType.BOOK_HORSE_MORPH);
		}
	},
	
	BOOK_READ_REINDEER_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds reindeer-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(arcane)]")),
			Colour.RACE_REINDEER_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.REINDEER_MORPH, ItemType.BOOK_REINDEER_MORPH);
		}
	},
	
	BOOK_READ_HUMAN(Util.newArrayListOfValues(
			new ListValue<>("Adds human encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(arcane)]")),
			Colour.RACE_HUMAN) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.HUMAN, ItemType.BOOK_HUMAN);
		}
	},
	
	BOOK_READ_SQUIRREL_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds squirrel-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(arcane)]")),
			Colour.RACE_SQUIRREL_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.SQUIRREL_MORPH, ItemType.BOOK_SQUIRREL_MORPH);
		}
	},
	
	BOOK_READ_WOLF_MORPH(Util.newArrayListOfValues(
			new ListValue<>("Adds wolf-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+0.5)] [style.boldIntelligence(arcane)]")),
			Colour.RACE_WOLF_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.WOLF_MORPH, ItemType.BOOK_WOLF_MORPH);
		}
	},
	
	ORIENTATION_CHANGE(Util.newArrayListOfValues(
			new ListValue<>("Sets orientation to gynephilic."),
			new ListValue<>("[style.boldExcellent(+50)] [style.boldCorruption(corruption)]")),
			Colour.FEMININE_PLUS) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return Util.newArrayListOfValues(
					new ListValue<>(TFModifier.REMOVAL),
					new ListValue<>(TFModifier.ORIENTATION_GYNEPHILIC),
					new ListValue<>(TFModifier.ORIENTATION_AMBIPHILIC),
					new ListValue<>(TFModifier.ORIENTATION_ANDROPHILIC));
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return Util.newArrayListOfValues(
					new ListValue<>(TFModifier.ARCANE_BOOST));
		}

		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST));
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			descriptions.clear();
			
			if(primaryModifier!=null && primaryModifier!=TFModifier.NONE) {
				if(primaryModifier==TFModifier.REMOVAL) {
					descriptions.add("No effect.");
					
				} else if(primaryModifier==TFModifier.ORIENTATION_GYNEPHILIC) {
					descriptions.add("Sets orientation to [style.boldFeminineStrong(gynephilic)].");
					descriptions.add("[style.boldTerrible(+5)] [style.boldCorruption(corruption)]");
					
				} else if(primaryModifier==TFModifier.ORIENTATION_AMBIPHILIC) {
					descriptions.add("Sets orientation to [style.boldAndrogynous(ambiphilic)].");
					descriptions.add("[style.boldTerrible(+5)] [style.boldCorruption(corruption)]");
					
				} else {
					descriptions.add("Sets orientation to [style.boldMasculineStrong(androphilic)].");
					descriptions.add("[style.boldTerrible(+5)] [style.boldCorruption(corruption)]");
				}
			}
			
			return descriptions;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			
			if(primaryModifier!=null && primaryModifier!=TFModifier.NONE && primaryModifier!=TFModifier.REMOVAL) {
				target.incrementAttribute(Attribute.MAJOR_CORRUPTION, 5);
				
				if(primaryModifier==TFModifier.ORIENTATION_GYNEPHILIC) {
					boolean alreadyGynephilic = target.getSexualOrientation()==SexualOrientation.GYNEPHILIC;
					target.setSexualOrientation(SexualOrientation.GYNEPHILIC);
					if(target.isPlayer()) {//TODO
						return "<p style='text-align:center;'>"
									+ (alreadyGynephilic?"[style.colourDisabled(You're already gynephilic, so nothing happens...)]":"You're now [style.colourFemininePlus(gynephilic)]!")
								+ "</p>";
					
					} else {
						return UtilText.parse(target,
								"<p style='text-align:center;'>"
										+ (alreadyGynephilic?"[style.colourDisabled([npc.Name] is already gynephilic, so nothing happens...)]":"[npc.Name] is now [style.colourFemininePlus(gynephilic)]!")
									+ "</p>");
					}
					
				} else if(primaryModifier==TFModifier.ORIENTATION_AMBIPHILIC) {
					boolean alreadyAmbiphilic = target.getSexualOrientation()==SexualOrientation.AMBIPHILIC;
					target.setSexualOrientation(SexualOrientation.AMBIPHILIC);
					if(target.isPlayer()) {//TODO
						return "<p style='text-align:center;'>"
									+ (alreadyAmbiphilic?"[style.colourDisabled(You're already ambiphilic, so nothing happens...)]":"You're now [style.colourAndrogynous(ambiphilic)]!")
								+ "</p>";
					
					} else {
						return UtilText.parse(target,
								"<p style='text-align:center;'>"
										+ (alreadyAmbiphilic?"[style.colourDisabled([npc.Name] is already ambiphilic, so nothing happens...)]":"[npc.Name] is now [style.colourAndrogynous(ambiphilic)]!")
									+ "</p>");
					}
					
				} else {
					boolean alreadyAndrophilic = target.getSexualOrientation()==SexualOrientation.ANDROPHILIC;
					target.setSexualOrientation(SexualOrientation.ANDROPHILIC);
					if(target.isPlayer()) {//TODO
						return "<p style='text-align:center;'>"
									+ (alreadyAndrophilic?"[style.colourDisabled(You're already androphilic, so nothing happens...)]":"You're now [style.colourMasculinePlus(androphilic)]!")
								+ "</p>";
					
					} else {
						return UtilText.parse(target,
								"<p style='text-align:center;'>"
										+ (alreadyAndrophilic?"[style.colourDisabled([npc.Name] is already androphilic, so nothing happens...)]":"[npc.Name] is now [style.colourMasculinePlus(androphilic)]!")
									+ "</p>");
					}
				}
				
			} else {
				return "<p>"
							+ "Nothing happens, as the Hypno-Watch has had its enchantment removed."
							+ " You'll need to enchant it if you want to put it to use."
						+ "</p>";
			}
			
		}
	},
	
	VIXENS_VIRILITY(Util.newArrayListOfValues(
			new ListValue<>("Temporarily boosts fertility.")),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(energy)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'"),
			new ListValue<>("[style.boldMinorBad(Adds)] 15% to [style.boldAlcohol(intoxication level)]")),
			Colour.ATTRIBUTE_PHYSIQUE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);
			
			return "<p style='text-align:center;'>"
						+(target.isPlayer()
							?"A powerful wave of arcane energy washes over you......"
							:UtilText.parse(target, "A powerful wave of arcane energy washes over [npc.name]..."))
						+ "</br>"
						+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1)
					+ "</p>"
					+ target.incrementAlcoholLevel(0.15f);
		}
	},

	STR_BUBBLE_MILK(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(energy)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'")),
			Colour.ATTRIBUTE_PHYSIQUE) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return null;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return null;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return "<p style='text-align:center;'>"
						+(target.isPlayer()
							?"A powerful wave of arcane energy washes over you......"
							:UtilText.parse(target, "A powerful wave of arcane energy washes over [npc.name]..."))
						+ "</br>"
						+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1)
					+"</p>";
		}
	},

	STR_WOLF_WHISKEY(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(energy)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'"),
			new ListValue<>("[style.boldMinorBad(Adds)] 40% to [style.boldAlcohol(intoxication level)]")),
			Colour.ATTRIBUTE_PHYSIQUE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A powerful wave of arcane energy washes over you......"
						:UtilText.parse(target, "A powerful wave of arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1)
					+"</p>"
					+ target.incrementAlcoholLevel(0.4f);
		}
	},
	
	STR_SWAMP_WATER(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(energy)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'"),
			new ListValue<>("[style.boldMinorBad(Adds)] 50% to [style.boldAlcohol(intoxication level)]")),
			Colour.ATTRIBUTE_PHYSIQUE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return "<p style='text-align:center;'>"
					+"A powerful wave of arcane energy washes over you..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1)
					+"</p>"
					+ target.incrementAlcoholLevel(0.5f);
		}
	},
	
	// Intelligence:
	
	INT_FELINE_FANCY(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldAura(aura)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldIntelligence(arcane)] to 'potion effects'"),
			new ListValue<>("[style.boldMinorBad(Adds)] 10% to [style.boldAlcohol(intoxication level)]")),
			Colour.ATTRIBUTE_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);
			
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A cool wave of arcane energy washes over you......"
						:UtilText.parse(target, "A cool wave of arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 1)
					+"</p>"
					+ target.incrementAlcoholLevel(0.1f);
		}
	},
	
	INT_VANILLA_WATER(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldAura(aura)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldIntelligence(arcane)] to 'potion effects'")),
			Colour.ATTRIBUTE_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);
			
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
							?"A cool wave of arcane energy washes over you......"
							:UtilText.parse(target, "A cool wave of arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 1)
					+"</p>";
		}
	},
	
	// Fitness:
	
	FIT_CANINE_CRUSH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(energy)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'"),
			new ListValue<>("[style.boldMinorBad(Adds)] 5% to [style.boldAlcohol(intoxication level)]")),
			Colour.ATTRIBUTE_PHYSIQUE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A soothing wave of arcane energy washes over you......"
						:UtilText.parse(target, "A soothing wave of arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1)
					+"</p>"
					+ target.incrementAlcoholLevel(0.05f);
		}
	},
	
	FIT_SQUIRREL_JAVA(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(energy)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'")),
			Colour.ATTRIBUTE_PHYSIQUE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A soothing wave of arcane energy washes over you......"
						:UtilText.parse(target, "A soothing wave of arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1)
					+"</p>";
		}
	},
	
	FIT_EGG_NOG(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(energy)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'")),
			Colour.ATTRIBUTE_PHYSIQUE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A soothing wave of arcane energy washes over you......"
						:UtilText.parse(target, "A soothing wave of arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1)
					+"</p>";
		}
	},
	
	SEX_HARPY_PERFUME(Util.newArrayListOfValues(
			new ListValue<>("[style.boldSex(+1)] [style.boldFeminine(femininity)]"),
			new ListValue<>("[style.boldGood(+5)] [style.boldMana("+Attribute.DAMAGE_LUST.getName()+")] to 'potion effects'")),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A soothing wave of arcane energy washes over you......"
						:UtilText.parse(target, "A soothing wave of arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.incrementFemininity(1)
					+ "</br>"
					+ target.addPotionEffect(Attribute.DAMAGE_LUST, 5)
					+"</p>";
		}
	},
	
	SEX_MINCE_PIE(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldDmgMana(aura damage)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+1)] [style.boldDmgFire(fire damage)] to 'potion effects'")),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A warm wave of arcane energy washes over you......"
						:UtilText.parse(target, "A warm wave of arcane energy washes over [npc.name]..."))
					+ "</br>"
							+ target.addPotionEffect(Attribute.DAMAGE_LUST, 1)
					+ "</br>"
					+ target.addPotionEffect(Attribute.DAMAGE_FIRE, 1)
					+"</p>";
		}
	},
	
	// Corruption:
	
	COR_LILITHS_GIFT(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(energy)]"),
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldAura(aura)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldCorruption(corruption)] to 'potion effects'")),
			Colour.ATTRIBUTE_CORRUPTION) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);

			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A sickly wave of corruptive arcane energy washes over you......"
						:UtilText.parse(target, "A sickly wave of corruptive arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_CORRUPTION, 1)
					+"</p>";
		}
	},
	
	MYSTERY_KINK(Util.newArrayListOfValues(
			new ListValue<>("[style.boldFetish(Random fetish addition or removal)]")),
			Colour.FETISH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
				
				return "<p style='text-align:center;'>"
						+(target.isPlayer()
						?"A staggering wave of arcane energy crashes over you, the sheer strength of which almost causes you to black out."
								+ " As you stagger back from the brink of unconsciousness, you realise that you've [style.boldGood(gained)] the [style.boldFetish("+f.getName(target)+" fetish)]!"
						:UtilText.parse(target, "A staggering wave of arcane energy crashes over [npc.name], the sheer strength of which almost causes [npc.herHim] to black out."
								+ " As [npc.she] staggers back from the brink of unconsciousness, [npc.she] discovers that [npc.she]'s [style.boldGood(gained)] the [style.boldFetish("+f.getName(target)+" fetish)]!"))
						+"</p>";
				
			} else {
				Fetish f = fetishesToRemove.get(Util.random.nextInt(fetishesToRemove.size()));
				target.removeFetish(f);
				
				return "<p style='text-align:center;'>"
						+(target.isPlayer()
						?"A staggering wave of arcane energy crashes over you, the sheer strength of which almost causes you to black out."
								+ " As you stagger back from the brink of unconsciousness, you realise that you've [style.boldBad(lost)] your [style.boldFetish("+f.getName(target)+" fetish)]!"
						:UtilText.parse(target, "A staggering wave of arcane energy crashes over [npc.name], the sheer strength of which almost causes [npc.herHim] to black out."
								+ " As [npc.she] staggers back from the brink of unconsciousness, [npc.she] discovers that [npc.she]'s [style.boldBad(lost)] [npc.her] [style.boldFetish("+f.getName(target)+" fetish)]!"))
						+"</p>";
			}
		}
	},
	
	ADDICTION_REMOVAL(Util.newArrayListOfValues(
			new ListValue<>("[style.boldExcellent(Removes all addictions)]")),
			Colour.BASE_GOLD) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			boolean hadAddictions = !target.getAddictions().isEmpty();
			target.clearAddictions();
			target.setAlcoholLevel(0);
			target.removeStatusEffect(StatusEffect.PSYCHOACTIVE);
			
			if(target.isPlayer()) {
				if(hadAddictions) {
					return "<p style='text-align:center;'>"
							+"You feel a deep sense of calm wash over you, and, letting out a deep sigh, you find that you no longer have any addictions!"
							+"</p>";
				} else {
					return "<p style='text-align:center;'>"
							+"You feel a deep sense of calm wash over you, but other than causing you to let out a deep sigh, you find that the potion doesn't do anything..."
							+"</p>";
				}
				
			} else {
				if(hadAddictions) {
					return "<p style='text-align:center;'>"
							+UtilText.parse(target, "[npc.Name] feels a deep sense of calm wash over [npc.herHim], and, letting out a deep sigh, [npc.she] finds that [npc.she] no longer has any addictions!")
							+"</p>";
				} else {
					return "<p style='text-align:center;'>"
							+UtilText.parse(target, "[npc.Name] feels a deep sense of calm wash over [npc.herHim], but other than causing [npc.herHim] to let out a deep sigh, [npc.she] finds that the potion doesn't do anything...")
							+"</p>";
				}
			}
		}
	},
	
	EGGPLANT(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(energy)]"),
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldAura(aura)]")),
			Colour.ATTRIBUTE_CORRUPTION) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);
			
			if(target.isPlayer()) {
				return "<p style='text-align:center;'>"
							+"It's kind of tasty."
						+"</p>";
			} else {
				return "";
			}
		}
	},
	
	GIFT_CHOCOLATES(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 30% [style.boldHealth(energy)]")),
			Colour.ATTRIBUTE_HEALTH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth((target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/100)*30);
			
			if(target.isPlayer()) {
				return "<p style='text-align:center;'>"
						+"They're absolutely delicious, and it only takes you a few moments to finish off the entire box."
						+"</p>";
			} else {
				return "";
			}
		}
	},
	
	GIFT_PERFUME(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+5)] [style.boldSeduction(seduction damage)] to 'potion effects'")),
			Colour.ATTRIBUTE_LUST) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"You smell a lot nicer now..."
						:UtilText.parse(target, "[npc.Name] smells a lot nicer now..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.DAMAGE_LUST, 5)
					+"</p>";
		}
	},
	
	
	PRESENT(Util.newArrayListOfValues(
			new ListValue<>("Contains a random item.")),
			Colour.GENERIC_EXCELLENT) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			List<AbstractItemType> items = new ArrayList<>();
			items.add(ItemType.FIT_INGREDIENT_EGG_NOG);
			items.add(ItemType.SEX_INGREDIENT_MINCE_PIE);
			items.add(ItemType.RACE_INGREDIENT_REINDEER_MORPH);
			
			Map<AbstractClothingType, Integer> clothingMap = new HashMap<>();
			// Common clothing (55%):
			clothingMap.put(ClothingType.HEAD_ANTLER_HEADBAND, 11);
			clothingMap.put(ClothingType.NECK_SNOWFLAKE_NECKLACE, 11);
			clothingMap.put(ClothingType.PIERCING_EAR_SNOW_FLAKES, 11);
			clothingMap.put(ClothingType.PIERCING_NOSE_SNOWFLAKE_STUD, 11);
			clothingMap.put(ClothingType.TORSO_OVER_CHRISTMAS_SWEATER, 11);
			
			// Uncommon clothing (44%):
			clothingMap.put(ClothingType.JOLNIR_BOOTS, 4);
			clothingMap.put(ClothingType.JOLNIR_BOOTS_FEMININE, 4);
			clothingMap.put(ClothingType.JOLNIR_COAT, 4);
			clothingMap.put(ClothingType.JOLNIR_DRESS, 4);
			clothingMap.put(ClothingType.JOLNIR_HAT, 4);
			
			clothingMap.put(ClothingType.KIMONO_DRESS, 4);
			clothingMap.put(ClothingType.KIMONO_GETA, 4);
			clothingMap.put(ClothingType.KIMONO_HAIR_KANZASHI, 4);

			clothingMap.put(ClothingType.KIMONO_MENS_KIMONO, 4);
			clothingMap.put(ClothingType.KIMONO_MENS_GETA, 4);
			clothingMap.put(ClothingType.KIMONO_HAORI, 4);
			
			// 50% chance for consumable, 50% for clothing:
			if(Math.random()<0.5f) {
				AbstractItemType itemType = items.get(Util.random.nextInt(items.size()));
				
				return "<p>"
							+ "The present contained: <b>"+itemType.getDisplayName(true)+"</b>!"
						+ "</p>"
						+ user.addItem(AbstractItemType.generateItem(itemType), false);
				
			} else {
				AbstractClothingType clothingType = Util.getRandomObjectFromWeightedMap(clothingMap);
				AbstractClothing clothing = AbstractClothingType.generateClothing(clothingType);
				
				if(!Main.game.getPlayerCell().getInventory().isInventoryFull()) {
					Main.game.getPlayerCell().getInventory().addClothing(clothing);
					return "<p>"
								+ "The present contained: <b>"+clothing.getDisplayName(true)+"</b>!"
							+ "</p>"
							+ user.addClothing(clothing, true);
					
				} else {
					return "<p>"
								+ "The present contained: <b>"+clothing.getDisplayName(true)+"</b>!"
							+ "</p>"
							+ user.addClothing(clothing, false);
				}
			}
		}
	},
	
	// Racial:
	
	RACE_INNOXIAS_GIFT(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+2)] [style.boldPhysique(physique)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+2)] [style.boldIntelligence(arcane)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+5)] [style.boldCorruption(corruption)] to 'potion effects'")),
			Colour.RACE_HUMAN) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "You start to feel like this item is just for testing purposes..."
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2)
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 2)
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_CORRUPTION, 5);
		}
	},
	
	RACE_ANGELS_TEARS(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldIntelligence(arcane)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'")),
			Colour.RACE_HUMAN) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
						?"You start to feel a lot healthier..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot healthier..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1)
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 1);
		}
	},
	
	RACE_CANINE_CRUNCH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+2)] [style.boldPhysique(physique)] to 'potion effects'")),
			Colour.RACE_DOG_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
						?"You start to feel a lot more energetic..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot more energetic..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2);
		}
	},
	
	RACE_KITTYS_REWARD(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldIntelligence(arcane)] to 'potion effects'")),
			Colour.RACE_CAT_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
							?"You feel your arcane power increasing..."
							:UtilText.parse(target, "[npc.Name] starts to feel [npc.her] arcane power increasing..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 3);
		}
	},
	
	RACE_ROUND_NUTS(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+2)] [style.boldIntelligence(arcane)] to 'potion effects'")),
			Colour.RACE_SQUIRREL_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
							?"You feel your arcane power increasing..."
							:UtilText.parse(target, "[npc.Name] starts to feel [npc.her] arcane power increasing..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 2);
		}
	},
	
	RACE_SUGAR_CARROT_CUBE(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldPhysique(physique)] to 'potion effects'")),
			Colour.RACE_HORSE_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
						?"You start to feel a lot stronger..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot stronger..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
		}
	},
	
	RACE_SUGAR_COOKIE(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldPhysique(physique)] to 'potion effects'")),
			Colour.RACE_REINDEER_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
						?"You start to feel a lot stronger..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot stronger..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
		}
	},
	
	RACE_ALLIGATORS_GUMBO(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldPhysique(physique)] to 'potion effects'")),
			Colour.RACE_ALLIGATOR_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
						?"You start to feel a lot stronger..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot stronger..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
		}
	},
	
	RACE_BUBBLE_CREAM(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldPhysique(physique)] to 'potion effects'")),
			Colour.RACE_COW_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
						?"You start to feel a lot stronger..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot stronger..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
		}
	},
	
	RACE_MEAT_AND_MARROW(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+5)] [style.boldPhysique(physique)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+3)] [style.boldCorruption(corruption)] to 'potion effects'")),
			Colour.RACE_WOLF_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
						?"You start to feel a lot stronger..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot stronger..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 5)
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_CORRUPTION, 3);
		}
	},
	
	RACE_LOLLIPOP(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+5)] [style.boldPhysique(physique)] to 'potion effects'"),
			new ListValue<>("[style.boldSex(+3)] [style.boldFeminine(femininity)]")),
			Colour.RACE_WOLF_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
						?"You start to feel more feminine..."
						:UtilText.parse(target, "[npc.Name] starts to feel more feminine..."))
					+ "</br>"
					+ target.incrementFemininity(3)
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 5);
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
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			target.incrementEssenceCount(TFEssence.ANGEL, 1);
//			return "You have absorbed [style.boldGood(+1)] [style.boldAngel(Angel)] essence!";
//		}
//	},
	
	BOTTLED_ESSENCE_ARCANE(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence")),
			Colour.GENERIC_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_SQUIRREL_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldSquirrel(squirrel-morphs)]!";
		}
	},
	
 	BOTTLED_ESSENCE_ALLIGATOR_MORPH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldAlligator(alligator-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldAlligator(alligator-morphs)]")),
			Colour.RACE_ALLIGATOR_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_ALLIGATOR_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldAlligator(alligator-morphs)]!";
		}
	},
	
	BOTTLED_ESSENCE_DEMON(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldDemon(demons)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldDemon(demons)]")),
			Colour.RACE_DEMON) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_HORSE_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldHorse(horse-morphs)]!";
		}
	},
	
	BOTTLED_ESSENCE_REINDEER_MORPH(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldReindeer(reindeer-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldReindeer(reindeer-morphs)]")),
			Colour.RACE_REINDEER_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_REINDEER_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldReindeer(reindeer-morphs)]!";
		}
	},
	
	BOTTLED_ESSENCE_HUMAN(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldHuman(humans)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldHuman(humans)]")),
			Colour.RACE_HUMAN) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("As the lollipop's transformative effects start to make themselves known, you start to feel very light-headed...");
			
			if(!target.hasFetish(Fetish.FETISH_BIMBO)) {
				target.addFetish(Fetish.FETISH_BIMBO);
				if(target.isPlayer()) {
					sb.append("</br>"
							+ "<p>"
								+ "A giggle escapes from between your [pc.lips], and you suddenly find yourself unable to think of anything other than how, like, super awesome bimbos are and stuff!"
								+ "</br><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>You have gained the bimbo fetish!</b>"
							+ "</p>");
				} else {
					sb.append(UtilText.parse(target, "</br>"
							+ "<p>"
								+ "A giggle escapes from between [npc.name]'s [npc.lips], and [npc.she] suddenly finds [npc.herself] unable to think of anything other than how, like, super awesome bimbos are and stuff!"
								+ "</br><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>[npc.Name] has gained the bimbo fetish!</b>"
							+ "</p>"));
				}
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("As the lollipop's transformative effects start to make themselves known, you start to feel very light-headed...");
			
			if(!target.hasTrait(Perk.NYMPHOMANIAC, false)) {
				target.addPerk(Perk.NYMPHOMANIAC);
				if(target.isPlayer()) {
					sb.append("</br>"
							+ "<p>"
								+ "A desperate moan escapes from between your [pc.lips], and you suddenly find yourself unable to think of anything other than sex, sex, and more sex!"
								+ "</br><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>You have gained the nymphomaniac perk!</b>"
							+ "</p>");
				} else {
					sb.append(UtilText.parse(target, "</br>"
							+ "<p>"
								+ "A desperate moan escapes from between [npc.name]'s [npc.lips], and [npc.she] suddenly finds [npc.herself] unable to think of anything other than sex, sex, and more sex!"
								+ "</br><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>[npc.Name] has gained the nymphomaniac perk!</b>"
							+ "</p>"));
				}
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("As the perfume's transformative effects start to make themselves known, you start to feel very light-headed...");
			
			if(!target.hasFetish(Fetish.FETISH_DOMINANT)) {
				target.addFetish(Fetish.FETISH_DOMINANT);
				if(target.isPlayer()) {
					sb.append("</br>"
							+ "<p>"
								+ "A deep groan escapes from between your [pc.lips], and you suddenly find yourself thinking of how much you want to dominate the next person you come across!"
								+ "</br><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>You have gained the dominant fetish!</b>"
							+ "</p>");
				} else {
					sb.append(UtilText.parse(target, "</br>"
							+ "<p>"
								+ "A deep groan escapes from between [npc.name]'s [npc.lips], and [npc.she] suddenly finds [npc.herslef] thinking of how much [npc.she] wants to dominate the next person [npc.she] meets!"
								+ "</br><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>[npc.Name] has gained the dominant fetish!</b>"
							+ "</p>"));
				}
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
	
	ATTRIBUTE_PHYSIQUE(null,
			Colour.ATTRIBUTE_PHYSIQUE) {

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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return genericAttributeEffect(resourceRestoration.HEALTH, primaryModifier, secondaryModifier, potency, limit, user, target);
		}
	},
	
	ATTRIBUTE_ARCANE(null,
			Colour.ATTRIBUTE_ARCANE) {

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
			return genericAttributeEffectDescription(resourceRestoration.MANA, primaryModifier, secondaryModifier, potency, limit);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return genericAttributeEffect(resourceRestoration.MANA, primaryModifier, secondaryModifier, potency, limit, user, target);
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
			return genericAttributeEffectDescription(resourceRestoration.MANA, primaryModifier, secondaryModifier, potency, limit);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return genericAttributeEffect(resourceRestoration.MANA, primaryModifier, secondaryModifier, potency, limit, user, target);
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return genericAttributeEffect(resourceRestoration.ALL, primaryModifier, secondaryModifier, potency, limit, user, target);
		}
	},
	
	FETISH_ENHANCEMENT(null,
			Colour.FETISH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return Util.newArrayListOfValues(
					new ListValue<>(TFModifier.TF_MOD_FETISH_BODY_PART),
					new ListValue<>(TFModifier.TF_MOD_FETISH_BEHAVIOUR));
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			List<TFModifier> list = new ArrayList<>();
			list.add(TFModifier.NONE);
			if(primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR) {
				list.addAll(TFModifier.getTFBehaviouralFetishList());
				return list;
			} else {
				list.addAll(TFModifier.getTFBodyPartFetishList());
				return list;
			}
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return Util.newArrayListOfValues(
					new ListValue<>(TFPotency.BOOST),
					new ListValue<>(TFPotency.MINOR_BOOST),
					new ListValue<>(TFPotency.MINOR_DRAIN),
					new ListValue<>(TFPotency.DRAIN));
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			String descriptor = primaryModifier==TFModifier.TF_MOD_FETISH_BODY_PART ? "body-part": "behavioural";
			
			if(potency==TFPotency.BOOST) {
				if(secondaryModifier == TFModifier.NONE) {
					return Util.newArrayListOfValues(new ListValue<>("Adds a [style.boldFetish(random "+descriptor+" fetish)]."));
				} else {
					return Util.newArrayListOfValues(new ListValue<>("Adds the [style.boldFetish("+secondaryModifier.getName()+" fetish)]."));
				}
				
			} else if(potency==TFPotency.MINOR_BOOST) {
				if(secondaryModifier == TFModifier.NONE) {
					return Util.newArrayListOfValues(new ListValue<>("Boosts [style.boldLust(desire)] for a [style.boldFetish(random "+descriptor+" fetish)]."));
				} else {
					return Util.newArrayListOfValues(new ListValue<>("Boosts [style.boldLust(desire)] for the [style.boldFetish("+secondaryModifier.getName()+" fetish)]."));
				}
				
			} else if(potency==TFPotency.MINOR_DRAIN) {
				if(secondaryModifier == TFModifier.NONE) {
					return Util.newArrayListOfValues(new ListValue<>("Lowers [style.boldLust(desire)] for a [style.boldFetish(random "+descriptor+" fetish)] (if that fetish is not already owned)."));
				} else {
					return Util.newArrayListOfValues(new ListValue<>("Lowers [style.boldLust(desire)] for the [style.boldFetish("+secondaryModifier.getName()+" fetish)] (if that fetish is not already owned)."));
				}
				
			} else {
				if(secondaryModifier == TFModifier.NONE) {
					return Util.newArrayListOfValues(new ListValue<>("Removes a [style.boldFetish(random "+descriptor+" fetish)]."));
				} else {
					return Util.newArrayListOfValues(new ListValue<>("Removes the [style.boldFetish("+secondaryModifier.getName()+" fetish)]."));
				}
			}
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			List<Fetish> availableFetishes = new ArrayList<>();
			
			if(primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR) {
				for(TFModifier mod : TFModifier.getTFBehaviouralFetishList()) {
					if(mod.getFetish()!=null) {
						availableFetishes.add(mod.getFetish());
					}
				}
			} else {
				for(TFModifier mod : TFModifier.getTFBodyPartFetishList()) {
					if(mod.getFetish()!=null) {
						availableFetishes.add(mod.getFetish());
					}
				}
			}
			
			if(potency==TFPotency.BOOST) {
				if(secondaryModifier == TFModifier.NONE) {
					List<Fetish> fetishesToAdd = new ArrayList<>();
					for(Fetish f : availableFetishes) {
						if(f.getFetishesForAutomaticUnlock().isEmpty() && !target.hasFetish(f)) {
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
				
			} else if(potency==TFPotency.MINOR_BOOST) {
				if(secondaryModifier == TFModifier.NONE) {
					List<Fetish> fetishesToBoost = new ArrayList<>();
					for(Fetish f : availableFetishes) {
						if(f.getFetishesForAutomaticUnlock().isEmpty() && !target.hasFetish(f)) {
							if(f.isAvailable(target)) {
								fetishesToBoost.add(f);
							}
						}
					}
					
					if(!fetishesToBoost.isEmpty()) {
						Fetish f = fetishesToBoost.get(Util.random.nextInt(fetishesToBoost.size()));
						FetishDesire newDesire = target.getFetishDesire(f).getNextDesire();
						target.setFetishDesire(f, newDesire);
						
						return "<p>"
									+(target.isPlayer()
										?"A warm wave of arcane energy rises up within you, and as you feel its influential power seeping into your mind,"
												+ " you realise that you now <b style='color:"+newDesire.getColour().toWebHexString()+";'>"+newDesire.getNameAsPlayerVerb()+"</b> [style.boldFetish("+f.getShortDescriptor()+")]!"
										:UtilText.parse(target, "A warm wave of arcane energy rises up within [npc.name], and as [npc.she] feels its influential power seeping into [npc.her] mind,"
												+ " [npc.she] realises that [npc.she] now <b style='color:"+newDesire.getColour().toWebHexString()+";'>"+newDesire.getNameAsVerb()+"</b> [style.boldLust("+f.getShortDescriptor()+")]!"))
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
					FetishDesire newDesire = target.getFetishDesire(fetish).getNextDesire();
					
					if(target.setFetishDesire(fetish, newDesire)) {
						return "<p>"
									+(target.isPlayer()
										?"A warm wave of arcane energy rises up within you, and as you feel its influential power seeping into your mind,"
												+ " you realise that you now <b style='color:"+newDesire.getColour().toWebHexString()+";'>"+newDesire.getNameAsPlayerVerb()+"</b> [style.boldFetish("+fetish.getShortDescriptor()+")]!"
										:UtilText.parse(target, "A warm wave of arcane energy rises up within [npc.name], and as [npc.she] feels its influential power seeping into [npc.her] mind,"
												+ " [npc.she] realises that [npc.she] now <b style='color:"+newDesire.getColour().toWebHexString()+";'>"+newDesire.getNameAsVerb()+"</b> [style.boldLust("+fetish.getShortDescriptor()+")]!"))
								+"</p>";
					} else {
						return "<p>"
									+(target.isPlayer()
										?"[style.colourDisabled(Nothing happens, as you already "+newDesire.getNameAsPlayerVerb()+" "+fetish.getShortDescriptor()+"...)]"
										:UtilText.parse(target, "[style.colourDisabled(Nothing happens, as [npc.she] already "+newDesire.getNameAsPlayerVerb()+" "+fetish.getShortDescriptor()+"...)]"))
								+"</p>";
					}
				}
				
			} else if(potency==TFPotency.MINOR_DRAIN) {
				if(secondaryModifier == TFModifier.NONE) {
					List<Fetish> fetishesToDrain = new ArrayList<>();
					for(Fetish f : availableFetishes) {
						if(f.getFetishesForAutomaticUnlock().isEmpty() && !target.hasFetish(f)) {
							if(f.isAvailable(target)) {
								fetishesToDrain.add(f);
							}
						}
					}
					
					if(!fetishesToDrain.isEmpty()) {
						Fetish f = fetishesToDrain.get(Util.random.nextInt(fetishesToDrain.size()));
						FetishDesire newDesire = target.getFetishDesire(f).getPreviousDesire();
						target.setFetishDesire(f, newDesire);
						
						return "<p>"
									+(target.isPlayer()
										?"A warm wave of arcane energy rises up within you, and as you feel its influential power seeping into your mind,"
												+ " you realise that you now <b style='color:"+newDesire.getColour().toWebHexString()+";'>"+newDesire.getNameAsPlayerVerb()+"</b> [style.boldFetish("+f.getShortDescriptor()+")]!"
										:UtilText.parse(target, "A warm wave of arcane energy rises up within [npc.name], and as [npc.she] feels its influential power seeping into [npc.her] mind,"
												+ " [npc.she] realises that [npc.she] now <b style='color:"+newDesire.getColour().toWebHexString()+";'>"+newDesire.getNameAsVerb()+"</b> [style.boldLust("+f.getShortDescriptor()+")]!"))
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
					FetishDesire newDesire = target.getFetishDesire(fetish).getPreviousDesire();
					
					if(target.setFetishDesire(fetish, newDesire)) {
						return "<p>"
									+(target.isPlayer()
										?"A warm wave of arcane energy rises up within you, and as you feel its influential power seeping into your mind,"
												+ " you realise that you now <b style='color:"+newDesire.getColour().toWebHexString()+";'>"+newDesire.getNameAsPlayerVerb()+"</b> [style.boldFetish("+fetish.getShortDescriptor()+")]!"
										:UtilText.parse(target, "A warm wave of arcane energy rises up within [npc.name], and as [npc.she] feels its influential power seeping into [npc.her] mind,"
												+ " [npc.she] realises that [npc.she] now <b style='color:"+newDesire.getColour().toWebHexString()+";'>"+newDesire.getNameAsVerb()+"</b> [style.boldLust("+fetish.getShortDescriptor()+")]!"))
								+"</p>";
					} else {
						if(target.hasFetish(fetish)) {
							return "<p>"
									+(target.isPlayer()
										?"[style.colourDisabled(As you have the "+fetish.getName(target)+" fetish, your love of it can't decrease...)]"
										:UtilText.parse(target, "[style.colourDisabled(As [npc.she] has the "+fetish.getName(target)+" fetish, [npc.her] love of it can't decrease...)]"))
								+"</p>";
						} else {
							return "<p>"
										+(target.isPlayer()
											?"[style.colourDisabled(Nothing happens, as you already "+newDesire.getNameAsPlayerVerb()+" "+fetish.getShortDescriptor()+"...)]"
											:UtilText.parse(target, "[style.colourDisabled(Nothing happens, as [npc.she] already "+newDesire.getNameAsPlayerVerb()+" "+fetish.getShortDescriptor()+"...)]"))
									+"</p>";
						}
					}
				}
				
			} else {
				if(secondaryModifier == TFModifier.NONE) {
					List<Fetish> fetishesToRemove = new ArrayList<>();
					for(Fetish f : availableFetishes) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getRacialEffect(Race.DOG_MORPH, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	},
	
	RACE_ALLIGATOR_MORPH(null,
			Colour.RACE_ALLIGATOR_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(Race.ALLIGATOR_MORPH, primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(Race.ALLIGATOR_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.ALLIGATOR_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescriptionPlusChangeDescription()));
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getRacialEffect(Race.ALLIGATOR_MORPH, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getRacialEffect(Race.HORSE_MORPH, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	},
	
	RACE_REINDEER_MORPH(null,
			Colour.RACE_REINDEER_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(Race.REINDEER_MORPH, primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(Race.REINDEER_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.REINDEER_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescriptionPlusChangeDescription()));
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getRacialEffect(Race.REINDEER_MORPH, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getRacialEffect(Race.HARPY, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	},
	
	// CLOTHING:
	
	CLOTHING(null,
			Colour.RARITY_RARE) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getClothingPrimaryList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE) {
				return TFModifier.getClothingAttributeList();
				
			} else if(primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR) {
				return TFModifier.getTFBehaviouralFetishList();
				
			} else if(primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART) {
				return TFModifier.getTFBodyPartFetishList();
				
			} else if(primaryModifier == TFModifier.CLOTHING_ENSLAVEMENT
					|| primaryModifier == TFModifier.CLOTHING_SEALING) {
				return Util.newArrayListOfValues(new ListValue<>(TFModifier.ARCANE_BOOST));
				
			} else {
				return getClothingTFSecondaryModifiers(primaryModifier);
			}
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART
					|| !getClothingTFSecondaryModifiers(primaryModifier).isEmpty()) {
				return TFPotency.getAllPotencies();
				
			} else {
				return Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST));
			}
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			List<String> effectsList = new ArrayList<>();
			
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE) { //This is overriden in a couple of places ,such as in InventoryTooltipEventListener
				effectsList.add(
						(potency.getClothingBonusValue()<0
								?"[style.boldBad("+potency.getClothingBonusValue()+")] "
								:"[style.boldGood(+"+potency.getClothingBonusValue()+")] ")
						+ "<b style='color:"+secondaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(secondaryModifier.getAssociatedAttribute().getName())+"</b>");
				
			} else if(primaryModifier == TFModifier.CLOTHING_SEALING) {
				effectsList.add("[style.boldArcane(Seals onto wearer)]");
				
			} else if(primaryModifier == TFModifier.CLOTHING_ENSLAVEMENT) {
				effectsList.add("[style.boldCrimson(Enslaves the wearer)]");
				
			} else if(primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART) {
				if(potency==TFPotency.MAJOR_BOOST) {
					effectsList.add("[style.boldExcellent(Grants)] [style.boldFetish("+secondaryModifier.getName()+" fetish)] while worn.");
					
				} else if(potency==TFPotency.BOOST) {
					effectsList.add("[style.boldGood(Increases)] [style.boldLust(desire)] for [style.boldFetish("+secondaryModifier.getName()+" fetish)] while worn.");
					
				} else if(potency==TFPotency.MINOR_BOOST) {
					effectsList.add("[style.boldMinorGood(Slightly increases)] [style.boldLust(desire)] for [style.boldFetish("+secondaryModifier.getName()+" fetish)] while worn.");
					
				} else if(potency==TFPotency.MAJOR_DRAIN) {
					effectsList.add("<b style='color:"+FetishDesire.ZERO_HATE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(FetishDesire.ZERO_HATE.getNameAsVerb())+"</b> [style.boldFetish("+secondaryModifier.getName()+" fetish)] while worn.");
					
				} else if(potency==TFPotency.DRAIN) {
					effectsList.add("[style.boldBad(Decreases)] [style.boldLust(desire)] for [style.boldFetish("+secondaryModifier.getName()+" fetish)] while worn.");
					
				} else if(potency==TFPotency.MINOR_DRAIN) {
					effectsList.add("[style.boldMinorBad(Slightly decreases)] [style.boldLust(desire)] for [style.boldFetish("+secondaryModifier.getName()+" fetish)] while worn.");
				}
				
			} else {
				return getClothingTFDescriptions(primaryModifier, secondaryModifier, potency, limit, user, target);
			}
			
			return effectsList;
		}
		
		@Override
		public int getLimits(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getClothingTFLimits(primaryModifier, secondaryModifier);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE
					|| primaryModifier == TFModifier.CLOTHING_ENSLAVEMENT
					|| primaryModifier == TFModifier.CLOTHING_SEALING
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART) {
				return "";
			}
			return applyClothingTF(primaryModifier, secondaryModifier, potency, limit, user, target, timer);
		}
	},
	;
	
	
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
	
	public int getLimits(TFModifier primaryModifier, TFModifier secondaryModifier) {
		return 0;
	}
	
	public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
		return effectsDescriptions;
	}
	
	public abstract String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer);
	
	private enum resourceRestoration {
		HEALTH,
		MANA,
		ALL;
	}
	
	private static String getBookEffect(Race race, AbstractItemType book) {
		Main.getProperties().addRaceDiscovered(race);
		if(Main.getProperties().addAdvancedRaceKnowledge(race)) {
			Main.game.addEvent(new EventLogEntryBookAddedToLibrary(book), true);
		}
		if(Main.game.getPlayer().addBooksRead(book)) {
			Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_ARCANE, 0.5f);
		}
		
		return race.getBasicDescription()
				+race.getAdvancedDescription();
	}
	
	private static List<TFModifier> getClothingTFSecondaryModifiers(TFModifier primaryModifier) {
		switch(primaryModifier) {
			case TF_ASS:
				return Util.newArrayListOfValues(
						new ListValue<>(TFModifier.TF_MOD_SIZE),// ass size
						new ListValue<>(TFModifier.TF_MOD_SIZE_SECONDARY),// hip size
						new ListValue<>(TFModifier.TF_MOD_CAPACITY),
						new ListValue<>(TFModifier.TF_MOD_ELASTICITY),
						new ListValue<>(TFModifier.TF_MOD_PLASTICITY),
						new ListValue<>(TFModifier.TF_MOD_WETNESS)
						);
			case TF_BREASTS:
				return Util.newArrayListOfValues(
						new ListValue<>(TFModifier.TF_MOD_SIZE),// breast size
						new ListValue<>(TFModifier.TF_MOD_SIZE_SECONDARY),// nipple size
						new ListValue<>(TFModifier.TF_MOD_SIZE_TERTIARY),// areolae size
						new ListValue<>(TFModifier.TF_MOD_CAPACITY),
						new ListValue<>(TFModifier.TF_MOD_ELASTICITY),
						new ListValue<>(TFModifier.TF_MOD_PLASTICITY),
						new ListValue<>(TFModifier.TF_MOD_WETNESS)
						);
			case TF_CORE:
				return Util.newArrayListOfValues(
						new ListValue<>(TFModifier.TF_MOD_SIZE),// height
						new ListValue<>(TFModifier.TF_MOD_SIZE_SECONDARY),// muscle mass
						new ListValue<>(TFModifier.TF_MOD_SIZE_TERTIARY),// body size
						new ListValue<>(TFModifier.TF_MOD_FEMININITY)
						);
			case TF_FACE:
				return Util.newArrayListOfValues(
						new ListValue<>(TFModifier.TF_MOD_SIZE)//lip size
						);
			case TF_HAIR:
				return Util.newArrayListOfValues(
						new ListValue<>(TFModifier.TF_MOD_SIZE)// hair length
						);
			case TF_PENIS:
				return Util.newArrayListOfValues(
						new ListValue<>(TFModifier.TF_MOD_SIZE),// size
						new ListValue<>(TFModifier.TF_MOD_SIZE_SECONDARY),// testicle size
						new ListValue<>(TFModifier.TF_MOD_CAPACITY),
						new ListValue<>(TFModifier.TF_MOD_ELASTICITY),
						new ListValue<>(TFModifier.TF_MOD_PLASTICITY),
						new ListValue<>(TFModifier.TF_MOD_WETNESS)
						);
			case TF_VAGINA:
				return Util.newArrayListOfValues(
						new ListValue<>(TFModifier.TF_MOD_SIZE),// clit size
						new ListValue<>(TFModifier.TF_MOD_SIZE_SECONDARY),// labia size
						new ListValue<>(TFModifier.TF_MOD_CAPACITY),
						new ListValue<>(TFModifier.TF_MOD_ELASTICITY),
						new ListValue<>(TFModifier.TF_MOD_PLASTICITY),
						new ListValue<>(TFModifier.TF_MOD_WETNESS)
						);
			default:
				break;
		}
		return new ArrayList<>();
	}
	
	private static int getClothingTFLimits(TFModifier primaryModifier, TFModifier secondaryModifier) {

		switch(secondaryModifier) {
			case TF_MOD_CAPACITY:
				return Capacity.SEVEN_GAPING.getMaximumValue();
			case TF_MOD_ELASTICITY:
				return OrificeElasticity.SEVEN_ELASTIC.getValue();
			case TF_MOD_PLASTICITY:
				return OrificePlasticity.SEVEN_MOULDABLE.getValue();
			case TF_MOD_WETNESS:
				if(primaryModifier!=TFModifier.TF_PENIS
				&& primaryModifier!=TFModifier.TF_BREASTS) {
					return Wetness.SEVEN_DROOLING.getValue();
				}
				break;
			default:
				break;
		}
		
		switch(primaryModifier) {
			case TF_ASS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return AssSize.SEVEN_GIGANTIC.getValue();
					case TF_MOD_SIZE_SECONDARY:
						return HipSize.SEVEN_ABSURDLY_WIDE.getValue();
					default:
						break;
				}
				break;
			case TF_BREASTS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return CupSize.MAXIMUM.getMeasurement();
					case TF_MOD_SIZE_SECONDARY:
						return NippleSize.FOUR_MASSIVE.getValue();
					case TF_MOD_SIZE_TERTIARY:
						return  AreolaeSize.FOUR_MASSIVE.getValue();
					case TF_MOD_WETNESS:
						return Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue();
					default:
						break;
				}
				break;
			case TF_CORE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return Height.SEVEN_COLOSSAL.getMaximumValue() - Height.ZERO_TINY.getMinimumValue();
					case TF_MOD_SIZE_SECONDARY:
						return Muscle.FOUR_RIPPED.getMaximumValue();
					case TF_MOD_SIZE_TERTIARY:
						return BodySize.FOUR_HUGE.getMaximumValue();
					case TF_MOD_FEMININITY:
						return Femininity.FEMININE_STRONG.getMaximumFemininity();
					default:
						break;
				}
				break;
			case TF_FACE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return LipSize.FOUR_HUGE.getValue();
					default:
						break;
				}
				break;
			case TF_HAIR:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return HairLength.SEVEN_TO_FLOOR.getMaximumValue();
					default:
						break;
				}
				break;
			case TF_PENIS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return PenisSize.SEVEN_STALLION.getMaximumValue();
					case TF_MOD_SIZE_SECONDARY:
						return TesticleSize.SEVEN_ABSURD.getValue();
					case TF_MOD_WETNESS:
						return CumProduction.SEVEN_MONSTROUS.getMaximumValue();
					default:
						break;
				}
				break;
			case TF_VAGINA:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return ClitorisSize.SEVEN_STALLION.getMaximumValue();
					case TF_MOD_SIZE_SECONDARY:
						return LabiaSize.FOUR_MASSIVE.getValue();
					default:
						break;
				}
				break;
			default:
				break;
		}
		return 0;
	}
	
	private static List<String> getClothingTFDescriptions(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
		List<String> descriptions = new ArrayList<>();
		
		String orificeName = "";

		if(primaryModifier==TFModifier.TF_PENIS) {
			orificeName = "urethra";
		} else if(primaryModifier==TFModifier.TF_VAGINA) {
			orificeName = "vagina";
		} else if(primaryModifier==TFModifier.TF_BREASTS) {
			orificeName = "nipple";
		} else if(primaryModifier==TFModifier.TF_ASS) {
			orificeName = "anus";
		}
		
		switch(secondaryModifier) {
			case TF_MOD_CAPACITY:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+" capacity", limit+" inches"));
				break;
			case TF_MOD_ELASTICITY:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+" elasticity", OrificeElasticity.getElasticityFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_PLASTICITY:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+" plasticity", OrificePlasticity.getElasticityFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_WETNESS:
				if(primaryModifier!=TFModifier.TF_PENIS
					&& primaryModifier!=TFModifier.TF_BREASTS) {
					descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+" wetness", Wetness.valueOf(limit).getDescriptor()));
				}
				break;
			default:
				break;
		}
		
		switch(primaryModifier) {
			case TF_ASS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "ass size", AssSize.getAssSizeFromInt(limit).getDescriptor()));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "hip size", HipSize.getHipSizeFromInt(limit).getDescriptor()));
						break;
					default:
						break;
				}
				break;
			case TF_BREASTS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "cup size", CupSize.getCupSizeFromInt(limit).getCupSizeName()+"-cup"));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "nipple size", NippleSize.getNippleSizeFromInt(limit).getName()));
						break;
					case TF_MOD_SIZE_TERTIARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "areolae size", AreolaeSize.getAreolaeSizeFromInt(limit).getName()));
						break;
					case TF_MOD_WETNESS:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "lactation", limit+"ml"));
						break;
					default:
						break;
				}
				break;
			case TF_CORE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "height", Height.ZERO_TINY.getMinimumValue()+limit+"cm"));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "muscle size", String.valueOf(limit)));
						break;
					case TF_MOD_SIZE_TERTIARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "body size", String.valueOf(limit)));
						break;
					case TF_MOD_FEMININITY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "femininity", String.valueOf(limit)));
						break;
					default:
						break;
				}
				break;
			case TF_FACE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "lip size", LipSize.getLipSizeFromInt(limit).getName()));
						break;
					default:
						break;
				}
				break;
			case TF_HAIR:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "hair length", limit+"cm"));
						break;
					default:
						break;
				}
				break;
			case TF_PENIS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "penis size", limit+" inches"));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "testicle size", TesticleSize.getTesticleSizeFromInt(limit).getDescriptor()));
						break;
					case TF_MOD_WETNESS:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "cum production", limit+"ml"));
						break;
					default:
						break;
				}
				break;
			case TF_VAGINA:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "clitoris size", limit+" inches"));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "labia size", LabiaSize.getLabiaSizeFromInt(limit).getName()));
						break;
					default:
						break;
				}
				break;
			default:
				break;
		}
		
		return descriptions;
	}
	
	private static String getClothingTFChangeDescriptionEntry(TFPotency potency, String subject, String limit) {
		switch(potency) {
			case MINOR_BOOST:
				return ("Weekly "+subject+" increase. (Limit: "+limit+")");
			case BOOST:
				return ("Daily "+subject+" increase. (Limit: "+limit+")");
			case MAJOR_BOOST:
				return ("Hourly "+subject+" increase. (Limit: "+limit+")");
			case MINOR_DRAIN:
				return ("Hourly "+subject+" decrease. (Limit: "+limit+")");
			case DRAIN:
				return ("Daily "+subject+" decrease. (Limit: "+limit+")");
			case MAJOR_DRAIN:
				return ("Weekly "+subject+" decrease. (Limit: "+limit+")");
		}
		return "";
	}
	
	private static String applyClothingTF(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
		int capacityIncrement = (potency.isNegative()?-2:2);
		int elasticityIncrement = (potency.isNegative()?-1:1);
		int plasticityIncrement = (potency.isNegative()?-1:1);
		int wetnessIncrement = (potency.isNegative()?-1:1);
		int assSizeIncrement = (potency.isNegative()?-1:1);
		int hipSizeIncrement = (potency.isNegative()?-1:1);

		int breastSizeIncrement = (potency.isNegative()?-1:1);
		int nippleSizeIncrement = (potency.isNegative()?-1:1);
		int areolaeSizeIncrement = (potency.isNegative()?-1:1);
		int lactationIncrement = (potency.isNegative()?-1:1);

		int heightIncrement = (potency.isNegative()?-1:1);
		int muscleIncrement = (potency.isNegative()?-1:1);
		int bodySizeIncrement = (potency.isNegative()?-1:1);
		int femininityIncrement = (potency.isNegative()?-1:1);

		int lipSizeIncrement = (potency.isNegative()?-1:1);

		int hairLengthIncrement = (potency.isNegative()?-1:1);

		int penisSizeIncrement = (potency.isNegative()?-1:1);
		int testicleSizeIncrement = (potency.isNegative()?-1:1);
		int cumProductionIncrement = (potency.isNegative()?-1:1);

		int clitorisSizeIncrement = (potency.isNegative()?-1:1);
		int labiaSizeIncrement = (potency.isNegative()?-1:1);
		
		int TFCount = 0;
		int minutesRequired = 60;
		
		switch(potency) {
			case MINOR_BOOST:
				minutesRequired = 7 * 24 * 60;
				break;
			case BOOST:
				minutesRequired = 24 * 60;
				break;
			case MAJOR_BOOST:
				minutesRequired = 60;
				break;
			case MINOR_DRAIN:
				minutesRequired = 7 * 24 * 60;
				break;
			case DRAIN:
				minutesRequired = 24 * 60;
				break;
			case MAJOR_DRAIN:
				minutesRequired = 60;
				break;
		}
		
		TFCount = timer.getTimePassed()/minutesRequired;
		if(TFCount>=1) {
			timer.setTimePassed(timer.getTimePassed()%minutesRequired);
		}
//		System.out.println(timer.getTimePassed() + ", " + minutesRequired + ": " +TFCount);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<TFCount; i++) {
			switch(primaryModifier) {
				case TF_ASS:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(assSizeIncrement, target.getAssSize().getValue(), limit)) {
								sb.append(target.incrementAssSize(assSizeIncrement));
							} else if(target.getAssSize().getValue() != limit) {
								sb.append(target.setAssSize(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(hipSizeIncrement, target.getHipSize().getValue(), limit)) {
								sb.append(target.incrementHipSize(assSizeIncrement));
							} else if(target.getHipSize().getValue() != limit) {
								sb.append(target.setHipSize(limit));
							}
							break;
						case TF_MOD_CAPACITY:
							if(isWithinLimits(capacityIncrement, target.getAssRawCapacityValue(), limit)) {
								sb.append(target.incrementAssCapacity(capacityIncrement, true));
							} else if((int)target.getAssRawCapacityValue() != limit) {
								sb.append(target.setAssCapacity(limit, true));
							}
							break;
						case TF_MOD_ELASTICITY:
							if(isWithinLimits(elasticityIncrement, target.getAssElasticity().getValue(), limit)) {
								sb.append(target.incrementAssElasticity(elasticityIncrement));
							} else if(target.getAssElasticity().getValue() != limit) {
								sb.append(target.setAssElasticity(limit));
							}
							break;
						case TF_MOD_PLASTICITY:
							if(isWithinLimits(plasticityIncrement, target.getAssPlasticity().getValue(), limit)) {
								sb.append(target.incrementAssPlasticity(plasticityIncrement));
							} else if(target.getAssPlasticity().getValue() != limit) {
								sb.append(target.setAssPlasticity(limit));
							}
							break;
						case TF_MOD_WETNESS:
							if(isWithinLimits(wetnessIncrement, target.getAssWetness().getValue(), limit)) {
								sb.append(target.incrementAssWetness(wetnessIncrement));
							} else if(target.getAssWetness().getValue() != limit) {
								sb.append(target.setAssWetness(limit));
							}
							break;
						default:
							break;
					}
					break;
				case TF_BREASTS:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(breastSizeIncrement, target.getBreastRawSizeValue(), limit)) {
								sb.append(target.incrementBreastSize(breastSizeIncrement));
							} else if(target.getBreastRawSizeValue() != limit) {
								sb.append(target.setBreastSize(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(nippleSizeIncrement, target.getNippleSize().getValue(), limit)) {
								sb.append(target.incrementNippleSize(nippleSizeIncrement));
							} else if(target.getNippleSize().getValue() != limit) {
								sb.append(target.incrementNippleSize(limit));
							}
							break;
						case TF_MOD_SIZE_TERTIARY:
							if(isWithinLimits(areolaeSizeIncrement, target.getAreolaeSize().getValue(), limit)) {
								sb.append(target.incrementAreolaeSize(areolaeSizeIncrement));
							} else if(target.getAreolaeSize().getValue() != limit) {
								sb.append(target.incrementAreolaeSize(limit));
							}
							break;
						case TF_MOD_CAPACITY:
							if(isWithinLimits(capacityIncrement, target.getNippleRawCapacityValue(), limit)) {
								sb.append(target.incrementNippleCapacity(capacityIncrement, true));
							} else if((int)target.getNippleRawCapacityValue() != limit) {
								sb.append(target.setNippleCapacity(limit, true));
							}
							break;
						case TF_MOD_ELASTICITY:
							if(isWithinLimits(elasticityIncrement, target.getNippleElasticity().getValue(), limit)) {
								sb.append(target.incrementNippleElasticity(elasticityIncrement));
							} else if(target.getNippleElasticity().getValue() != limit) {
								sb.append(target.setNippleElasticity(limit));
							}
							break;
						case TF_MOD_PLASTICITY:
							if(isWithinLimits(plasticityIncrement, target.getNipplePlasticity().getValue(), limit)) {
								sb.append(target.incrementNipplePlasticity(plasticityIncrement));
							} else if(target.getNipplePlasticity().getValue() != limit) {
								sb.append(target.setNipplePlasticity(limit));
							}
							break;
						case TF_MOD_WETNESS:
							if(isWithinLimits(lactationIncrement, target.getBreastRawLactationValue(), limit)) {
								sb.append(target.incrementBreastLactation(lactationIncrement));
							} else if(target.getBreastRawLactationValue() != limit) {
								sb.append(target.setBreastLactation(limit));
							}
							break;
						default:
							break;
					}
					break;
				case TF_CORE:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(heightIncrement, target.getHeightValue()-Height.ZERO_TINY.getMinimumValue(), limit)) {
								sb.append(target.incrementHeight(heightIncrement));
							} else if(target.getHeightValue()-Height.ZERO_TINY.getMinimumValue() != limit) {
								sb.append(target.setHeight(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(muscleIncrement, target.getMuscleValue(), limit)) {
								sb.append(target.incrementMuscle(muscleIncrement));
							} else if(target.getMuscleValue() != limit) {
								sb.append(target.setMuscle(limit));
							}
							break;
						case TF_MOD_SIZE_TERTIARY:
							if(isWithinLimits(bodySizeIncrement, target.getBodySizeValue(), limit)) {
								sb.append(target.incrementBodySize(bodySizeIncrement));
							} else if(target.getBodySizeValue() != limit) {
								sb.append(target.setBodySize(limit));
							}
							break;
						case TF_MOD_FEMININITY:
							if(isWithinLimits(femininityIncrement, target.getFemininityValue(), limit)) {
								sb.append(target.incrementFemininity(femininityIncrement));
							} else if(target.getFemininityValue() != limit) {
								sb.append(target.setFemininity(limit));
							}
							break;
						default:
							break;
					}
					break;
				case TF_FACE:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(lipSizeIncrement, target.getLipSizeValue(), limit)) {
								sb.append(target.incrementLipSize(lipSizeIncrement));
							} else if(target.getLipSizeValue() != limit) {
								sb.append(target.setLipSize(limit));
							}
							break;
						default:
							break;
					}
					break;
				case TF_HAIR:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(hairLengthIncrement, target.getHairRawLengthValue(), limit)) {
								sb.append(target.incrementHairLength(hairLengthIncrement));
							} else if(target.getHairRawLengthValue() != limit) {
								sb.append(target.setHairLength(limit));
							}
							break;
						default:
							break;
					}
					break;
				case TF_PENIS:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(penisSizeIncrement, target.getPenisRawSizeValue(), limit)) {
								sb.append(target.incrementPenisSize(penisSizeIncrement));
							} else if(target.getPenisRawSizeValue() != limit) {
								sb.append(target.setPenisSize(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(testicleSizeIncrement, target.getTesticleSize().getValue(), limit)) {
								sb.append(target.incrementTesticleSize(testicleSizeIncrement));
							} else if(target.getTesticleSize().getValue() != limit) {
								sb.append(target.setTesticleSize(limit));
							}
							break;
						case TF_MOD_CAPACITY:
							if(isWithinLimits(capacityIncrement, target.getPenisRawCapacityValue(), limit)) {
								sb.append(target.incrementPenisCapacity(capacityIncrement, true));
							} else if((int)target.getPenisRawCapacityValue() != limit) {
								sb.append(target.setPenisCapacity(limit, true));
							}
							break;
						case TF_MOD_ELASTICITY:
							if(isWithinLimits(elasticityIncrement, target.getUrethraElasticity().getValue(), limit)) {
								sb.append(target.incrementUrethraElasticity(elasticityIncrement));
							} else if(target.getUrethraElasticity().getValue() != limit) {
								sb.append(target.setUrethraElasticity(limit));
							}
							break;
						case TF_MOD_PLASTICITY:
							if(isWithinLimits(plasticityIncrement, target.getUrethraPlasticity().getValue(), limit)) {
								sb.append(target.incrementUrethraPlasticity(plasticityIncrement));
							} else if(target.getUrethraPlasticity().getValue() != limit) {
								sb.append(target.setUrethraPlasticity(limit));
							}
							break;
						case TF_MOD_WETNESS:
							if(isWithinLimits(cumProductionIncrement, target.getPenisRawCumProductionValue(), limit)) {
								sb.append(target.incrementPenisCumProduction(cumProductionIncrement));
							} else if(target.getPenisRawCumProductionValue() != limit) {
								sb.append(target.setCumProduction(limit));
							}
							break;
						default:
							break;
					}
					break;
				case TF_VAGINA:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(clitorisSizeIncrement, target.getVaginaRawClitorisSizeValue(), limit)) {
								sb.append(target.incrementVaginaClitorisSize(clitorisSizeIncrement));
							} else if(target.getVaginaRawClitorisSizeValue() != limit) {
								sb.append(target.setVaginaClitorisSize(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(labiaSizeIncrement, target.getVaginaRawLabiaSizeValue(), limit)) {
								sb.append(target.incrementVaginaLabiaSize(labiaSizeIncrement));
							} else if(target.getVaginaRawLabiaSizeValue() != limit) {
								sb.append(target.setVaginaLabiaSize(limit));
							}
							break;
						case TF_MOD_CAPACITY:
							if(isWithinLimits(capacityIncrement, target.getVaginaRawCapacityValue(), limit)) {
								sb.append(target.incrementVaginaCapacity(capacityIncrement, true));
							} else if((int)target.getVaginaRawCapacityValue() != limit) {
								sb.append(target.setVaginaCapacity(limit, true));
							}
							break;
						case TF_MOD_ELASTICITY:
							if(isWithinLimits(elasticityIncrement, target.getVaginaElasticity().getValue(), limit)) {
								sb.append(target.incrementVaginaElasticity(elasticityIncrement));
							} else if(target.getVaginaElasticity().getValue() != limit) {
								sb.append(target.setVaginaElasticity(limit));
							}
							break;
						case TF_MOD_PLASTICITY:
							if(isWithinLimits(plasticityIncrement, target.getVaginaPlasticity().getValue(), limit)) {
								sb.append(target.incrementVaginaPlasticity(plasticityIncrement));
							} else if(target.getVaginaPlasticity().getValue() != limit) {
								sb.append(target.setVaginaPlasticity(limit));
							}
							break;
						case TF_MOD_WETNESS:
							if(isWithinLimits(wetnessIncrement, target.getVaginaWetness().getValue(), limit)) {
								sb.append(target.incrementVaginaWetness(wetnessIncrement));
							} else if(target.getVaginaWetness().getValue() != limit) {
								sb.append(target.setVaginaWetness(limit));
							}
							break;
						default:
							break;
					}
					break;
				default:
					break;
			}
		}
		
		return sb.toString();
	}
	
	private static boolean isWithinLimits(int increment, float currentValue, int limit) {
		if(increment<0) {
			if(increment + currentValue < limit) {
				return false;
			}
		} else {
			if(increment + currentValue > limit) {
				return false;
			}
		}
		return true;
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
					descriptions.add("[style.boldGood(Restores)] "+value+"% [style.boldHealth(energy)]");
				break;
			case MANA:
					descriptions.add("[style.boldGood(Restores)] "+value+"% [style.boldAura(aura)]");
				break;
			case ALL:
					descriptions.add("[style.boldGood(Restores)] "+value+"% [style.boldHealth(energy)]");
					descriptions.add("[style.boldGood(Restores)] "+value+"% [style.boldAura(aura)]");
				break;
		}
	}
	private static void addResourceDescriptionsDrain(int value, resourceRestoration restorationType) {
		switch(restorationType){
			case HEALTH:
					descriptions.add("[style.boldBad(Drains)] "+value+"% [style.boldHealth(energy)]");
				break;
			case MANA:
					descriptions.add("[style.boldBad(Drains)] "+value+"% [style.boldAura(aura)]");
				break;
			case ALL:
					descriptions.add("[style.boldBad(Drains)] "+value+"% [style.boldHealth(energy)]");
					descriptions.add("[style.boldBad(Drains)] "+value+"% [style.boldAura(aura)]");
				break;
		}
	}
	
	private static String applyRestoration(GameCharacter target, resourceRestoration restorationType, float percentage) {
		switch(restorationType) {
			case ALL:
				target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*percentage);
				target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*percentage);
				break;
			case HEALTH:
				target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*percentage);
				break;
			case MANA:
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
	private static Map<Race, Map<TFModifier, LinkedHashMap<TFModifier, List<TFPotency>>>> racialPrimaryModSecondaryModPotencyGrid = new HashMap<>();
	
	private static List<TFModifier> getRacialSecondaryModifiers(Race race, TFModifier primaryModifier) {
		if(racialPrimaryModSecondaryModPotencyGrid.containsKey(race) && racialPrimaryModSecondaryModPotencyGrid.get(race).containsKey(primaryModifier)) {
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).keySet());
		} else {
			populateGrid(race, primaryModifier);
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).keySet());
		}
	}
	
	private static List<TFPotency> getRacialPotencyModifiers(Race race, TFModifier primaryModifier, TFModifier secondaryModifier) {
		if(racialPrimaryModSecondaryModPotencyGrid.get(race).containsKey(primaryModifier)) {
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).get(secondaryModifier));
		} else {
			populateGrid(race, primaryModifier);
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).get(secondaryModifier));
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
				for(int i=0; i< EarType.getEarTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				}
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
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
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
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				for(int i=0; i< TailType.getTailTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				}
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
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_VAGINA_SQUIRTER, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				
				break;
				
			case TF_WINGS:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
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
		
		racialPrimaryModSecondaryModPotencyGrid.put(race, Util.newHashMapOfValues(new Value<>(primaryModifier, secondaryModPotencyMap)));
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
								return new RacialEffectUtil("Adds an extra pair of antennae.", singleBoost, "") { @Override public String applyEffect() {
									if(target.getAntennaType()==AntennaType.NONE && RacialBody.valueOfRace(race).getAntennaType()!=AntennaType.NONE) {
										return target.setAntennaType(RacialBody.valueOfRace(race).getAntennaType());
									} else {
										return target.incrementAntennaRows(singleBoost);
									} } };
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
				switch(secondaryModifier) {
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(EarType.getEarTypes(race).get(0).getTransformName())+" ears transformation.", 0, "") {
							@Override public String applyEffect() { return target.setEarType(EarType.getEarTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(EarType.getEarTypes(race).get(1).getTransformName())+" ears transformation.", 0, "") {
							@Override public String applyEffect() { return target.setEarType(EarType.getEarTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(EarType.getEarTypes(race).get(2).getTransformName())+" ears transformation.", 0, "") {
							@Override public String applyEffect() { return target.setEarType(EarType.getEarTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(EarType.getEarTypes(race).get(3).getTransformName())+" ears transformation.", 0, "") {
							@Override public String applyEffect() { return target.setEarType(EarType.getEarTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(EarType.getEarTypes(race).get(4).getTransformName())+" ears transformation.", 0, "") {
							@Override public String applyEffect() { return target.setEarType(EarType.getEarTypes(race).get(4)); } };
							
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" ears transformation.", 0, "") { @Override public String applyEffect() { return target.setEarType(RacialBody.valueOfRace(race).getEarType()); } };
				}
				
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
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in horn length.", mediumChangeMajorDrain, " inches") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in horn length.", mediumChangeDrain, " inches") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in horn length.", mediumChangeMinorDrain, " inches") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in horn length.", mediumChangeMinorBoost, " inches") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in horn length.", mediumChangeBoost, " inches") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in horn length.", mediumChangeMajorBoost, " inches") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMajorBoost); } };
						}
				
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of horns.", singleDrain, " pair of horns") { @Override public String applyEffect() { return target.incrementHornRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of horns.", singleBoost, " pair of horns") { @Override public String applyEffect() {
									if(target.getHornType()==HornType.NONE && RacialBody.valueOfRace(race).getHornType().size()>1) {
										return target.setHornType(RacialBody.valueOfRace(race).getHornType().get(1));
									} else {
										return target.incrementHornRows(singleBoost);
									} } };
						}
						
					case TF_TYPE_1:
						return new RacialEffectUtil((RacialBody.valueOfRace(race).getHornType().get(0)==HornType.NONE?"Removes horns.":"Grows "+RacialBody.valueOfRace(race).getHornType().get(0).getTransformName()+" horns."), 0, "") {
							@Override public String applyEffect() { return target.setHornType(RacialBody.valueOfRace(race).getHornType().get(0)); } };

					case TF_TYPE_2:
						return new RacialEffectUtil((RacialBody.valueOfRace(race).getHornType().get(1)==HornType.NONE?"Removes horns.":"Grows "+RacialBody.valueOfRace(race).getHornType().get(1).getTransformName()+" horns."), 0, "") {
							@Override public String applyEffect() { return target.setHornType(RacialBody.valueOfRace(race).getHornType().get(1)); } };

					case TF_TYPE_3:
						return new RacialEffectUtil((RacialBody.valueOfRace(race).getHornType().get(2)==HornType.NONE?"Removes horns.":"Grows "+RacialBody.valueOfRace(race).getHornType().get(2).getTransformName()+" horns."), 0, "") {
							@Override public String applyEffect() { return target.setHornType(RacialBody.valueOfRace(race).getHornType().get(2)); } };

					case TF_TYPE_4:
						return new RacialEffectUtil((RacialBody.valueOfRace(race).getHornType().get(3)==HornType.NONE?"Removes horns.":"Grows "+RacialBody.valueOfRace(race).getHornType().get(3).getTransformName()+" horns."), 0, "") {
							@Override public String applyEffect() { return target.setHornType(RacialBody.valueOfRace(race).getHornType().get(3)); } };

					case TF_TYPE_5:
						return new RacialEffectUtil((RacialBody.valueOfRace(race).getHornType().get(4)==HornType.NONE?"Removes horns.":"Grows "+RacialBody.valueOfRace(race).getHornType().get(4).getTransformName()+" horns."), 0, "") {
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
								return new RacialEffectUtil("Adds an extra tail.", singleBoost, " tail") { @Override public String applyEffect() {
									if(target.getTailType()==TailType.NONE && RacialBody.valueOfRace(race).getTailType()!=TailType.NONE) {
										return target.setTailType(RacialBody.valueOfRace(race).getTailType());
									} else {
										return target.incrementTailCount(singleBoost);
									} } };
						}
					case REMOVAL:
						return new RacialEffectUtil("Removes tail.", 0, "") { @Override public String applyEffect() { return target.setTailType(TailType.NONE); } };
						
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(TailType.getTailTypes(race).get(0).getTransformName())+" tail transformation.", 0, "") {
							@Override public String applyEffect() { return target.setTailType(TailType.getTailTypes(race).get(0)); } };
						
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(TailType.getTailTypes(race).get(1).getTransformName())+" tail transformation.", 0, "") {
							@Override public String applyEffect() { return target.setTailType(TailType.getTailTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(TailType.getTailTypes(race).get(2).getTransformName())+" tail transformation.", 0, "") {
							@Override public String applyEffect() { return target.setTailType(TailType.getTailTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(TailType.getTailTypes(race).get(3).getTransformName())+" tail transformation.", 0, "") {
							@Override public String applyEffect() { return target.setTailType(TailType.getTailTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(TailType.getTailTypes(race).get(4).getTransformName())+" tail transformation.", 0, "") {
							@Override public String applyEffect() { return target.setTailType(TailType.getTailTypes(race).get(4)); } };
							
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

					case TF_MOD_VAGINA_SQUIRTER:
						switch(potency) {
							case MINOR_BOOST:
								return new RacialEffectUtil("Makes vagina squirt on orgasm.", 0, "") { @Override public String applyEffect() { return target.setVaginaSquirter(true); } };
							case MINOR_DRAIN: default:
								return new RacialEffectUtil("Stops vagina from squirting on orgasm.", 0, "") { @Override public String applyEffect() { return target.setVaginaSquirter(false); } };
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
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in wing size.", smallChangeMajorDrain, " wing size") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in wing size.", smallChangeDrain, " wing size") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in wing size.", smallChangeMinorDrain, " wing size") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in wing size.", smallChangeMinorBoost, " wing size") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in wing size.", smallChangeBoost, " wing size") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in wing size.", smallChangeMajorBoost, " wing size") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMajorBoost); } };
						}
					default:
						if(RacialBody.valueOfRace(race).getWingType() == WingType.NONE) {
							return new RacialEffectUtil("Removes wings.", 0, "") { @Override public String applyEffect() { return target.setWingType(RacialBody.valueOfRace(race).getWingType()); } };
						} else {
							return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" wings transformation.", 0, "") { @Override public String applyEffect() { return target.setWingType(RacialBody.valueOfRace(race).getWingType()); } };
						}
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
