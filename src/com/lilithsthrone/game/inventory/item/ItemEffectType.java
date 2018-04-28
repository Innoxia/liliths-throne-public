package com.lilithsthrone.game.inventory.item;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
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
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Race;
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

/**
 * @since 0.1.7
 * @version 0.2.4
 * @author Innoxia
 */
public class ItemEffectType {
	
	public static AbstractItemEffectType TESTING = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Test item.")),
		Colour.GENERIC_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return target.incrementMuscle(-25)
					+ target.incrementBodySize(25)
					+ target.setUnderarmHair(BodyHair.SIX_BUSHY);
		}
	};
	
	public static AbstractItemEffectType DYE_BRUSH = new AbstractItemEffectType(Util.newArrayListOfValues(
				new ListValue<>("Recolours a piece of clothing.")),
			Colour.GENERIC_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p>"
					+ "You use the dye brush."
					+ "</p>";
		}
	};
	
	public static AbstractItemEffectType USED_CONDOM_DRINK = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Provides a slimy snack.")),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return ""; // THIS EFFECT IS NOT USED, AS AbstractFilledCondom OVERRIDES THE USUAL AbstractItem's applyEffects() METHOD!!!
		}
	};
	
	public static AbstractItemEffectType FILLED_MOO_MILKER_DRINK = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Provides a milky drink.")),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return ""; // THIS EFFECT IS NOT USED, AS AbstractFilledBreastPump OVERRIDES THE USUAL AbstractItem's applyEffects() METHOD!!!
		}
	};
	
	public static AbstractItemEffectType BOOK_READ_CAT_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Adds cat-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldCatMorph("+Attribute.DAMAGE_CAT_MORPH.getName()+")]"),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldCatMorph("+Attribute.RESISTANCE_CAT_MORPH.getName()+")]")),
			Colour.RACE_CAT_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.CAT_MORPH, ItemType.BOOK_CAT_MORPH);
		}
	};
	
	public static AbstractItemEffectType BOOK_READ_COW_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Adds cow-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldCowMorph("+Attribute.DAMAGE_COW_MORPH.getName()+")]"),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldCowMorph("+Attribute.RESISTANCE_COW_MORPH.getName()+")]")),
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
	};
	
	public static AbstractItemEffectType BOOK_READ_DEMON = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Adds demon encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldDemon("+Attribute.DAMAGE_DEMON.getName()+")]"),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldDemon("+Attribute.RESISTANCE_DEMON.getName()+")]")),
			Colour.RACE_DEMON) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.DEMON, ItemType.BOOK_DEMON);
		}
	};

	public static AbstractItemEffectType BOOK_READ_IMP = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Adds imp encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldImp("+Attribute.DAMAGE_IMP.getName()+")]"),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldImp("+Attribute.RESISTANCE_IMP.getName()+")]")),
			Colour.RACE_IMP) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.IMP, ItemType.BOOK_IMP);
		}
	};
	
	public static AbstractItemEffectType BOOK_READ_DOG_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Adds dog-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldDogMorph("+Attribute.DAMAGE_DOG_MORPH.getName()+")]"),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldDogMorph("+Attribute.RESISTANCE_DOG_MORPH.getName()+")]")),
			Colour.RACE_DOG_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.DOG_MORPH, ItemType.BOOK_DOG_MORPH);
		}
	};
	
	public static AbstractItemEffectType BOOK_READ_ALLIGATOR_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Adds alligator-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldAlligatorMorph("+Attribute.DAMAGE_ALLIGATOR_MORPH.getName()+")]"),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldAlligatorMorph("+Attribute.RESISTANCE_ALLIGATOR_MORPH.getName()+")]")),
			Colour.RACE_ALLIGATOR_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.ALLIGATOR_MORPH, ItemType.BOOK_ALLIGATOR_MORPH);
		}
	};
	
	public static AbstractItemEffectType BOOK_READ_HARPY = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Adds harpy encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldHarpy("+Attribute.DAMAGE_HARPY.getName()+")]"),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldHarpy("+Attribute.RESISTANCE_HARPY.getName()+")]")),
			Colour.RACE_HARPY) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.HARPY, ItemType.BOOK_HARPY);
		}
	};
	
	public static AbstractItemEffectType BOOK_READ_HORSE_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Adds horse-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldHorseMorph("+Attribute.DAMAGE_HORSE_MORPH.getName()+")]"),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldHorseMorph("+Attribute.RESISTANCE_HORSE_MORPH.getName()+")]")),
			Colour.RACE_HORSE_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.HORSE_MORPH, ItemType.BOOK_HORSE_MORPH);
		}
	};
	
	public static AbstractItemEffectType BOOK_READ_REINDEER_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Adds reindeer-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldReindeerMorph("+Attribute.DAMAGE_REINDEER_MORPH.getName()+")]"),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldReindeerMorph("+Attribute.RESISTANCE_REINDEER_MORPH.getName()+")]")),
			Colour.RACE_REINDEER_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.REINDEER_MORPH, ItemType.BOOK_REINDEER_MORPH);
		}
	};
	
	public static AbstractItemEffectType BOOK_READ_HUMAN = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Adds human encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldHuman("+Attribute.DAMAGE_HUMAN.getName()+")]"),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldHuman("+Attribute.RESISTANCE_HUMAN.getName()+")]")),
			Colour.RACE_HUMAN) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.HUMAN, ItemType.BOOK_HUMAN);
		}
	};
	
	public static AbstractItemEffectType BOOK_READ_SQUIRREL_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Adds squirrel-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldSquirrelMorph("+Attribute.DAMAGE_SQUIRREL_MORPH.getName()+")]"),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldSquirrelMorph("+Attribute.RESISTANCE_SQUIRREL_MORPH.getName()+")]")),
			Colour.RACE_SQUIRREL_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.SQUIRREL_MORPH, ItemType.BOOK_SQUIRREL_MORPH);
		}
	};
	
	public static AbstractItemEffectType BOOK_READ_RAT_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Adds rat-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldRatMorph("+Attribute.DAMAGE_RAT_MORPH.getName()+")]"),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldRatMorph("+Attribute.RESISTANCE_RAT_MORPH.getName()+")]")),
			Colour.RACE_RAT_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.RAT_MORPH, ItemType.BOOK_RAT_MORPH);
		}
	};
	
	public static AbstractItemEffectType BOOK_READ_RABBIT_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Adds rabbit-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldRabbitMorph("+Attribute.DAMAGE_RABBIT_MORPH.getName()+")]"),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldRabbitMorph("+Attribute.RESISTANCE_RABBIT_MORPH.getName()+")]")),
			Colour.RACE_RABBIT_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.RABBIT_MORPH, ItemType.BOOK_RABBIT_MORPH);
		}
	};
	
	public static AbstractItemEffectType BOOK_READ_BAT_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Adds bat-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldBatMorph("+Attribute.DAMAGE_BAT_MORPH.getName()+")]"),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldBatMorph("+Attribute.RESISTANCE_BAT_MORPH.getName()+")]")),
			Colour.RACE_BAT_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.BAT_MORPH, ItemType.BOOK_BAT_MORPH);
		}
	};
	
	public static AbstractItemEffectType BOOK_READ_WOLF_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Adds wolf-morph encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldWolfMorph("+Attribute.DAMAGE_WOLF_MORPH.getName()+")]"),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldWolfMorph("+Attribute.RESISTANCE_WOLF_MORPH.getName()+")]")),
			Colour.RACE_WOLF_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.WOLF_MORPH, ItemType.BOOK_WOLF_MORPH);
		}
	};

	public static AbstractItemEffectType BOOK_READ_SLIME = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Adds slime encyclopedia entry."),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldSlime("+Attribute.DAMAGE_SLIME.getName()+")]"),
			new ListValue<>("[style.boldExcellent(+5)] [style.boldSlime("+Attribute.RESISTANCE_SLIME.getName()+")]")),
			Colour.RACE_SLIME) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getBookEffect(Race.SLIME, ItemType.BOOK_SLIME);
		}
	};
	
	public static AbstractItemEffectType ORIENTATION_CHANGE = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	public static AbstractItemEffectType VIXENS_VIRILITY = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Temporarily boosts fertility.")),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.removeStatusEffect(StatusEffect.PROMISCUITY_PILL);
			
			target.addStatusEffect(StatusEffect.VIXENS_VIRILITY, 60*24);
			
			if(target.isPlayer()) {
				return "<p>"
						+ "The little pink pill easily slides down your throat, and within moments, you feel "
						+ ( target.hasVagina()
								? "a strange, warm glow spreading from what you guess must be your ovaries."
									+ " Your mind fogs over with an overwhelming desire to feel potent sperm spurting deep into your "+(target.isVisiblyPregnant()?"pussy":"womb")
									+", and before you can stop it, a little whimper escapes from between your [pc.lips]."
									+ (target.hasPenis()
											?" At the same time, your manhood begins to throb with need, and you feel "
											:"") 
							:"")
						+ (target.hasPenis() 
								? "an overpowering desire to sink deep into a fertile female's cunt and fill her with your [pc.cum+]."
								: "")
						+ (!target.hasPenis() && !target.hasVagina()
								?"a desperate heat in [npc.her] genderless mound."
								:"")
					+ "</p>";
			
			} else {
				return UtilText.parse(target,
						"<p>"
							+ "The little pink pill easily slides down [npc.her] throat, and within moments, [npc.she] feels "
							+ ( target.hasVagina()
									? "a strange, warm glow spreading from [npc.her] ovaries."
										+ " [npc.Her] mind fogs over with an overwhelming desire to feel potent sperm spurting deep into [npc.her] "+(target.isVisiblyPregnant()?"pussy":"womb")
										+", and before [npc.she] can stop it, a little whimper escapes from between [npc.her] [npc.lips]."
										+ (target.hasPenis()
												?" At the same time, [npc.her] manhood begins to throb with need, and [npc.she] feels "
												:"") 
									:"")
							+ (target.hasPenis()
									? "an overpowering desire to sink deep into a fertile female's cunt and fill her with [npc.cum+]."
									: "")
							+ (!target.hasPenis() && !target.hasVagina()
									?"a desperate heat in [npc.her] genderless mound."
									:"")
						+ "</p>");
			}
			
		}
	};
	
	public static AbstractItemEffectType PROMISCUITY_PILL = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	

	public static AbstractItemEffectType MOO_MILKER = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("Milks breasts.")),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			int milkPumped = Math.min(target.getBreastRawStoredMilkValue(), ItemType.getMooMilkerMaxMilk());
			target.incrementBreastStoredMilk(-milkPumped);
			if(target.isPlayer()) {
				return "<p>"
							+ "It only takes a moment before the beaker is filled with "+milkPumped+"ml of your [pc.milk]."
						+ "</p>"
						+ user.addItem(AbstractItemType.generateFilledBreastPump(ItemType.MOO_MILKER_EMPTY.getColourPrimary(), target, target.getMilk(), milkPumped), false, true);
			
			} else {
				return UtilText.parse(target,
						"<p>"
							+ "It only takes a moment before the beaker is filled with "+milkPumped+"ml of [npc.her] [npc.milk]."
						+ "</p>"
						+ user.addItem(AbstractItemType.generateFilledBreastPump(ItemType.MOO_MILKER_EMPTY.getColourPrimary(), target, target.getMilk(), milkPumped), false, true));
			}
		}
	};
	
	public static AbstractItemEffectType MOTHERS_MILK = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	// Ingredients and potions:
	
	// Strength:
	
	public static AbstractItemEffectType STR_EQUINE_CIDER = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};

	public static AbstractItemEffectType STR_BUBBLE_MILK = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};

	public static AbstractItemEffectType STR_WOLF_WHISKEY = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	public static AbstractItemEffectType STR_SWAMP_WATER = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	public static AbstractItemEffectType STR_BLACK_RATS_RUM = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	// Intelligence:
	
	public static AbstractItemEffectType INT_FELINE_FANCY = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	public static AbstractItemEffectType INT_VANILLA_WATER = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	// Fitness:
	
	public static AbstractItemEffectType FIT_CANINE_CRUSH = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	public static AbstractItemEffectType FIT_SQUIRREL_JAVA = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	public static AbstractItemEffectType INT_FRUIT_BAT_SQUASH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 5% [style.boldHealth(energy)]"),
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(arcane)] to 'potion effects'")),
			Colour.ATTRIBUTE_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A soothing wave of arcane energy washes over you......"
						:UtilText.parse(target, "A soothing wave of arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 1)
					+"</p>";
		}
	};
	
	public static AbstractItemEffectType FIT_EGG_NOG = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	public static AbstractItemEffectType SEX_HARPY_PERFUME = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	public static AbstractItemEffectType SEX_SLIME_DRINK = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldSex(+1)] [style.boldCorruption(Vagina Wetness)]"),
			new ListValue<>("[style.boldSex(+1)] [style.boldCorruption(Anal Wetness)]"),
			new ListValue<>("[style.boldGood(+5)] [style.boldLust("+Attribute.DAMAGE_LUST.getName()+")] to 'potion effects'")),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A warm wave of arcane energy washes over you......"
						:UtilText.parse(target, "A warm wave of arcane energy washes over [npc.name]..."))
					+ "</p>"
					+ (target.hasVagina()?target.incrementVaginaWetness(1):"")
					+ target.incrementAssWetness(1)
					+ target.addPotionEffect(Attribute.DAMAGE_LUST, 5);
		}
	};
	
	public static AbstractItemEffectType SEX_RABBIT_MORPH_DRINK = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldSex(+5)] [style.boldCorruption(Fertility)] to 'potion effects'"),
			new ListValue<>("[style.boldSex(+5)] [style.boldCorruption(Virility)] to 'potion effects'"),
			new ListValue<>("[style.boldBad(-10)] [style.boldLust("+Attribute.RESISTANCE_LUST.getName()+")] to 'potion effects'")),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A warm wave of arcane energy washes over you......"
						:UtilText.parse(target, "A warm wave of arcane energy washes over [npc.name]..."))
					+ "</p>"
					+ target.addPotionEffect(Attribute.FERTILITY, 5)
					+ target.addPotionEffect(Attribute.VIRILITY, 5)
					+ target.addPotionEffect(Attribute.RESISTANCE_LUST, -10);
		}
	};
	
	public static AbstractItemEffectType SEX_MINCE_PIE = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	// Corruption:
	
	public static AbstractItemEffectType COR_LILITHS_GIFT = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};

	public static AbstractItemEffectType COR_IMPISH_BREW = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(Restores)] 10% [style.boldAura(aura)]"),
			new ListValue<>("[style.boldGood(+5)] [style.boldCorruption(corruption)] to 'potion effects'")),
			Colour.ATTRIBUTE_CORRUPTION) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/10);

			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A sickly wave of corruptive arcane energy washes over you......"
						:UtilText.parse(target, "A sickly wave of corruptive arcane energy washes over [npc.name]..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_CORRUPTION, 5)
					+"</p>";
		}
	};
	
	public static AbstractItemEffectType MYSTERY_KINK = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	public static AbstractItemEffectType ADDICTION_REMOVAL = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	public static AbstractItemEffectType EGGPLANT = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	public static AbstractItemEffectType GIFT_CHOCOLATES = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	public static AbstractItemEffectType GIFT_PERFUME = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	
	public static AbstractItemEffectType PRESENT = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	// Racial:
	
	public static AbstractItemEffectType RACE_INNOXIAS_GIFT = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+2)] [style.boldPhysique(physique)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+2)] [style.boldIntelligence(arcane)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+5)] [style.boldCorruption(corruption)] to 'potion effects'")),
			Colour.RACE_HUMAN) {
		
		@Override
		public String getPotionDescriptor() {
			return "demonic";
		}
		
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
	};
	
	public static AbstractItemEffectType RACE_ANGELS_TEARS = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldIntelligence(arcane)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'")),
			Colour.RACE_HUMAN) {

		@Override
		public String getPotionDescriptor() {
			return "angelic";
		}
		
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
	};
	
	public static AbstractItemEffectType RACE_CANINE_CRUNCH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+2)] [style.boldPhysique(physique)] to 'potion effects'")),
			Colour.RACE_DOG_MORPH) {

		@Override
		public String getPotionDescriptor() {
			return "canine";
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
						?"You start to feel a lot more energetic..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot more energetic..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2);
		}
	};
	
	public static AbstractItemEffectType RACE_BURGER = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+2)] [style.boldPhysique(physique)] to 'potion effects'")),
			Colour.RACE_RAT_MORPH) {

		@Override
		public String getPotionDescriptor() {
			return "rat";
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
						?"You start to feel a lot more energetic..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot more energetic..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2);
		}
	};
	
	public static AbstractItemEffectType RACE_CARROT_CAKE = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldSex(+15)] [style.boldCorruption(Fertility)] to 'potion effects'"),
			new ListValue<>("[style.boldSex(+15)] [style.boldCorruption(Virility)] to 'potion effects'"),
			new ListValue<>("[style.boldBad(-25)] [style.boldLust("+Attribute.RESISTANCE_LUST.getName()+")] to 'potion effects'")),
			Colour.RACE_RABBIT_MORPH) {

		@Override
		public String getPotionDescriptor() {
			return "lagomorphic";
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
						?"You start to feel like you need to breed..."
						:UtilText.parse(target, "[npc.Name] starts to feel like [npc.she] needs to breed..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.FERTILITY, 15)
					+ target.addPotionEffect(Attribute.VIRILITY, 15)
					+ target.addPotionEffect(Attribute.RESISTANCE_LUST, -25);
		}
	};
	
	public static AbstractItemEffectType RACE_KITTYS_REWARD = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldIntelligence(arcane)] to 'potion effects'")),
			Colour.RACE_CAT_MORPH) {

		@Override
		public String getPotionDescriptor() {
			return "feline";
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
							?"You feel your arcane power increasing..."
							:UtilText.parse(target, "[npc.Name] starts to feel [npc.her] arcane power increasing..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 3);
		}
	};
	
	public static AbstractItemEffectType RACE_ROUND_NUTS = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+2)] [style.boldIntelligence(arcane)] to 'potion effects'")),
			Colour.RACE_SQUIRREL_MORPH) {

		@Override
		public String getPotionDescriptor() {
			return "sciuridine";
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
							?"You feel your arcane power increasing..."
							:UtilText.parse(target, "[npc.Name] starts to feel [npc.her] arcane power increasing..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 2);
		}
	};
	
	public static AbstractItemEffectType RACE_FRUIT_SALAD = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+2)] [style.boldIntelligence(arcane)] to 'potion effects'")),
			Colour.RACE_BAT_MORPH) {

		@Override
		public String getPotionDescriptor() {
			return "chiropterine";
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
							?"You feel your arcane power increasing..."
							:UtilText.parse(target, "[npc.Name] starts to feel [npc.her] arcane power increasing..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 2);
		}
	};
	
	public static AbstractItemEffectType RACE_SUGAR_CARROT_CUBE = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldPhysique(physique)] to 'potion effects'")),
			Colour.RACE_HORSE_MORPH) {

		@Override
		public String getPotionDescriptor() {
			return "equine";
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
						?"You start to feel a lot stronger..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot stronger..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
		}
	};
	
	public static AbstractItemEffectType RACE_SUGAR_COOKIE = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldPhysique(physique)] to 'potion effects'")),
			Colour.RACE_REINDEER_MORPH) {

		@Override
		public String getPotionDescriptor() {
			return "rangiferine";
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
						?"You start to feel a lot stronger..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot stronger..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
		}
	};
	
	public static AbstractItemEffectType RACE_ALLIGATORS_GUMBO = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldPhysique(physique)] to 'potion effects'")),
			Colour.RACE_ALLIGATOR_MORPH) {

		@Override
		public String getPotionDescriptor() {
			return "alligator";
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
						?"You start to feel a lot stronger..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot stronger..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
		}
	};
	
	public static AbstractItemEffectType RACE_BUBBLE_CREAM = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+3)] [style.boldPhysique(physique)] to 'potion effects'")),
			Colour.RACE_COW_MORPH) {

		@Override
		public String getPotionDescriptor() {
			return "bovine";
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return (target.isPlayer()
						?"You start to feel a lot stronger..."
						:UtilText.parse(target, "[npc.Name] starts to feel a lot stronger..."))
					+ "</br>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
		}
	};
	
	public static AbstractItemEffectType RACE_MEAT_AND_MARROW = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+5)] [style.boldPhysique(physique)] to 'potion effects'"),
			new ListValue<>("[style.boldGood(+3)] [style.boldCorruption(corruption)] to 'potion effects'")),
			Colour.RACE_WOLF_MORPH) {

		@Override
		public String getPotionDescriptor() {
			return "lupine";
		}
		
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
	};
	
	public static AbstractItemEffectType RACE_LOLLIPOP = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+5)] [style.boldPhysique(physique)] to 'potion effects'"),
			new ListValue<>("[style.boldSex(+3)] [style.boldFeminine(femininity)]")),
			Colour.RACE_HARPY) {

		@Override
		public String getPotionDescriptor() {
			return "harpy";
		}
		
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
	};
	
	public static AbstractItemEffectType RACE_BIOJUICE = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+50)] [style.boldCorruption(corruption)] to 'potion effects'"),
			new ListValue<>("[style.boldSlime(Transforms body into slime!)]")),
			Colour.RACE_SLIME) {

		@Override
		public String getPotionDescriptor() {
			return "slime";
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			if(target.getBody().getBodyMaterial()==BodyMaterial.SLIME) {
				if(target.isPlayer()) {
					return "[style.colourDisabled(You are already a slime, so nothing happens...)]";
				} else {
					return UtilText.parse(target, "[style.colourDisabled([npc.Name] is already a slime, so nothing happens...)]");
				}
				
			} else {
				return target.addPotionEffect(Attribute.MAJOR_CORRUPTION, 50)
						+ "</br>"
						+ target.setBodyMaterial(BodyMaterial.SLIME);
			}
		}
	};
	
	// Essences:
	
//	public static AbstractItemEffectType BOTTLED_ESSENCE_ANGEL = new AbstractItemEffectType(Util.newArrayListOfValues(
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
//	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_ARCANE = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence")),
			Colour.GENERIC_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_CAT_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldCat(cat-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldCat(cat-morphs)]")),
			Colour.RACE_CAT_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_CAT_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldCat(cat-morphs)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_COW_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldCow(cow-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldCow(cow-morphs)]")),
			Colour.RACE_COW_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_COW_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldCow(cow-morphs)]!";
		}
	};

 	public static AbstractItemEffectType BOTTLED_ESSENCE_SQUIRREL_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldSquirrel(squirrel-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldSquirrel(squirrel-morphs)]")),
			Colour.RACE_SQUIRREL_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_SQUIRREL_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldSquirrel(squirrel-morphs)]!";
		}
	};

 	public static AbstractItemEffectType BOTTLED_ESSENCE_RAT_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldRat(rat-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldRat(rat-morphs)]")),
			Colour.RACE_RAT_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_RAT_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldRat(rat-morphs)]!";
		}
	};

 	public static AbstractItemEffectType BOTTLED_ESSENCE_RABBIT_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldRabbit(rabbit-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldRabbit(rabbit-morphs)]")),
			Colour.RACE_RABBIT_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_RABBIT_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldRabbit(rabbit-morphs)]!";
		}
	};

 	public static AbstractItemEffectType BOTTLED_ESSENCE_BAT_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldBat(bat-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldBat(bat-morphs)]")),
			Colour.RACE_BAT_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_BAT_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldBat(bat-morphs)]!";
		}
	};
	
 	public static AbstractItemEffectType BOTTLED_ESSENCE_ALLIGATOR_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldAlligator(alligator-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldAlligator(alligator-morphs)]")),
			Colour.RACE_ALLIGATOR_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_ALLIGATOR_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldAlligator(alligator-morphs)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_DEMON = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldDemon(demons)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldDemon(demons)]")),
			Colour.RACE_DEMON) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_DEMON, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldDemon(demons)]!";
		}
	};

	public static AbstractItemEffectType BOTTLED_ESSENCE_IMP = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldImp(imps)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldImp(imps)]")),
			Colour.RACE_IMP) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_IMP, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldImp(imps)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_DOG_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldDog(dog-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldDog(dog-morphs)]")),
			Colour.RACE_DOG_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_DOG_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldDog(dog-morphs)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_HARPY = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldHarpy(harpies)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldHarpy(harpies)]")),
			Colour.RACE_HARPY) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_HARPY, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldHarpy(harpies)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_HORSE_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldHorse(horse-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldHorse(horse-morphs)]")),
			Colour.RACE_HORSE_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_HORSE_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldHorse(horse-morphs)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_REINDEER_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldReindeer(reindeer-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldReindeer(reindeer-morphs)]")),
			Colour.RACE_REINDEER_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_REINDEER_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldReindeer(reindeer-morphs)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_HUMAN = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldHuman(humans)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldHuman(humans)]")),
			Colour.RACE_HUMAN) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_HUMAN, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldHuman(humans)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_WOLF_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldWolf(wolf-morphs)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldWolf(wolf-morphs)]")),
			Colour.RACE_WOLF_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_WOLF_MORPH, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldWolf(wolf-morphs)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_SLIME = new AbstractItemEffectType(Util.newArrayListOfValues(
			new ListValue<>("[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(damage vs)] [style.boldSlime(slimes)]"),
			new ListValue<>("[style.boldGood(+25%)] [style.bold(resistance vs)] [style.boldSlime(slimes)]")),
			Colour.RACE_WOLF_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_SLIME, 60*4);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldSlime(slimes)]!";
		}
	};
	
	
	// Specials:
	
	public static AbstractItemEffectType BIMBO_LOLLIPOP = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	public static AbstractItemEffectType NYMPHO_LOLLIPOP = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	public static AbstractItemEffectType DOMINANT_PERFUME = new AbstractItemEffectType(Util.newArrayListOfValues(
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
	};
	
	// Enchantment effects: TODO
	
	public static AbstractItemEffectType ATTRIBUTE_PHYSIQUE = new AbstractItemEffectType(null,
			Colour.ATTRIBUTE_PHYSIQUE) {

		@Override
		public String getPotionDescriptor() {
			return "vivid";
		}
		
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
			return genericAttributeEffectDescription(ResourceRestoration.HEALTH, primaryModifier, secondaryModifier, potency, limit);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return genericAttributeEffect(ResourceRestoration.HEALTH, primaryModifier, secondaryModifier, potency, limit, user, target);
		}
	};
	
	public static AbstractItemEffectType ATTRIBUTE_ARCANE = new AbstractItemEffectType(null,
			Colour.ATTRIBUTE_ARCANE) {

		@Override
		public String getPotionDescriptor() {
			return "soothing";
		}
		
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
			return genericAttributeEffectDescription(ResourceRestoration.MANA, primaryModifier, secondaryModifier, potency, limit);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return genericAttributeEffect(ResourceRestoration.MANA, primaryModifier, secondaryModifier, potency, limit, user, target);
		}
	};
	
	public static AbstractItemEffectType ATTRIBUTE_SEXUAL = new AbstractItemEffectType(null,
			Colour.GENERIC_SEX) {

		@Override
		public String getPotionDescriptor() {
			return "arousing";
		}
		
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
			return genericAttributeEffectDescription(ResourceRestoration.MANA, primaryModifier, secondaryModifier, potency, limit);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return genericAttributeEffect(ResourceRestoration.MANA, primaryModifier, secondaryModifier, potency, limit, user, target);
		}
	};
	
	public static AbstractItemEffectType ATTRIBUTE_CORRUPTION = new AbstractItemEffectType(null,
			Colour.ATTRIBUTE_CORRUPTION) {

		@Override
		public String getPotionDescriptor() {
			return "viscous";
		}
		
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
			return genericAttributeEffectDescription(ResourceRestoration.ALL, primaryModifier, secondaryModifier, potency, limit);
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return genericAttributeEffect(ResourceRestoration.ALL, primaryModifier, secondaryModifier, potency, limit, user, target);
		}
	};
	
	public static AbstractItemEffectType FETISH_ENHANCEMENT = new AbstractItemEffectType(null,
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
	};
	
	// RACIAL:
	
	public static AbstractItemEffectType RACE_DEMON = new AbstractItemEffectType(null,
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
	};
	
	public static AbstractItemEffectType RACE_HUMAN = new AbstractItemEffectType(null,
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
	};
	
	public static AbstractItemEffectType RACE_CAT_MORPH = new AbstractItemEffectType(null,
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
	};
	
	public static AbstractItemEffectType RACE_COW_MORPH = new AbstractItemEffectType(null,
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
	};
	
	public static AbstractItemEffectType RACE_SQUIRREL_MORPH = new AbstractItemEffectType(null,
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
	};

	public static AbstractItemEffectType RACE_RAT_MORPH = new AbstractItemEffectType(null,
			Colour.RACE_RAT_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(Race.RAT_MORPH, primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(Race.RAT_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.RAT_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescriptionPlusChangeDescription()));
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getRacialEffect(Race.RAT_MORPH, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	};

	public static AbstractItemEffectType RACE_RABBIT_MORPH = new AbstractItemEffectType(null,
			Colour.RACE_RABBIT_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(Race.RABBIT_MORPH, primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(Race.RABBIT_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.RABBIT_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescriptionPlusChangeDescription()));
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getRacialEffect(Race.RABBIT_MORPH, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	};

	public static AbstractItemEffectType RACE_BAT_MORPH = new AbstractItemEffectType(null,
			Colour.RACE_BAT_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(Race.BAT_MORPH, primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(Race.BAT_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(new ListValue<>(getRacialEffect(Race.BAT_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescriptionPlusChangeDescription()));
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getRacialEffect(Race.BAT_MORPH, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	};
	
	public static AbstractItemEffectType RACE_DOG_MORPH = new AbstractItemEffectType(null,
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
	};
	
	public static AbstractItemEffectType RACE_ALLIGATOR_MORPH = new AbstractItemEffectType(null,
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
	};
	
	public static AbstractItemEffectType RACE_HORSE_MORPH = new AbstractItemEffectType(null,
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
	};
	
	public static AbstractItemEffectType RACE_REINDEER_MORPH = new AbstractItemEffectType(null,
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
	};
	
	public static AbstractItemEffectType RACE_WOLF_MORPH = new AbstractItemEffectType(null,
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
	};
	
	public static AbstractItemEffectType RACE_HARPY = new AbstractItemEffectType(null,
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
	};
	
	// CLOTHING:
	
	public static AbstractItemEffectType CLOTHING = new AbstractItemEffectType(null,
			Colour.RARITY_RARE) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			if(Main.game.getPlayer().isHasSlaverLicense()) {
				return TFModifier.getClothingPrimaryList();
			} else {
				ArrayList<TFModifier> noEnslavement = new ArrayList<>(TFModifier.getClothingPrimaryList());
				noEnslavement.remove(TFModifier.CLOTHING_ENSLAVEMENT);
				return noEnslavement;
			}
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
	};
	
	

	private static Map<AbstractItemEffectType, String> itemEffectTypeToIdMap = new HashMap<>();
	private static Map<String, AbstractItemEffectType> idToItemEffectTypeMap = new HashMap<>();
	private static List<AbstractItemEffectType> allEffectTypes = new ArrayList<>();
	
	public static void addAbstractItemEffectToIds(String id, AbstractItemEffectType itemEffectType) {
		allEffectTypes.add(itemEffectType);
		
		itemEffectTypeToIdMap.put(itemEffectType, id);
		idToItemEffectTypeMap.put(id, itemEffectType);
	}
	
	public static AbstractItemEffectType getItemEffectTypeFromId(String id) {
		return idToItemEffectTypeMap.get(id);
	}
	
	public static String getIdFromItemEffectType(AbstractItemEffectType itemEffectType) {
		return itemEffectTypeToIdMap.get(itemEffectType);
	}
	
	public static List<AbstractItemEffectType> getAllEffectTypes() {
		return allEffectTypes;
	}

	static {
		
		Field[] fields = ItemEffectType.class.getFields();
		
		for(Field f : fields){
			
			if (AbstractItemEffectType.class.isAssignableFrom(f.getType())) {
				
				AbstractItemEffectType iet;
				try {
					iet = ((AbstractItemEffectType) f.get(null));
					
					allEffectTypes.add(iet);
					
					// I feel like this is stupid :thinking:
					itemEffectTypeToIdMap.put(iet, f.getName());
					idToItemEffectTypeMap.put(f.getName(), iet);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				
			}
		}
  	    
	}
	
}
