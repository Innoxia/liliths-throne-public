package com.lilithsthrone.game.inventory.enchanting;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PregnancyPossibility;
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
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.OffspringMapDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.7
 * @version 0.3.1
 * @author Innoxia
 */
public class ItemEffectType {
	
	public static AbstractItemEffectType TESTING = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Test item."),
		Colour.GENERIC_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return target.incrementMuscle(-25)
					+ target.incrementBodySize(25)
					+ target.setUnderarmHair(BodyHair.SIX_BUSHY);
		}
	};
	
	public static AbstractItemEffectType DYE_BRUSH = new AbstractItemEffectType(Util.newArrayListOfValues(
				"Recolours a piece of clothing."),
			Colour.GENERIC_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p>"
					+ "You use the dye brush."
					+ "</p>";
		}
	};
	
	public static AbstractItemEffectType REFORGE_HAMMER = new AbstractItemEffectType(Util.newArrayListOfValues(
				"Changes a weapon's damage type."),
			Colour.GENERIC_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p>"
					+ "You use the reforge hammer."
					+ "</p>";
		}
	};
	
	public static AbstractItemEffectType USED_CONDOM_DRINK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Provides a slimy snack."),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return ""; // THIS EFFECT IS NOT USED, AS AbstractFilledCondom OVERRIDES THE USUAL AbstractItem's applyEffects() METHOD!!!
		}
	};

	public static AbstractItemEffectType FILLED_MOO_MILKER_DRINK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Provides a milky drink."),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return ""; // THIS EFFECT IS NOT USED, AS AbstractFilledBreastPump OVERRIDES THE USUAL AbstractItem's applyEffects() METHOD!!!
			// Then why is it here? :thinking:
		}
	};
	
	
	public static AbstractItemEffectType ORIENTATION_CHANGE = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Sets orientation to gynephilic.",
			"[style.boldExcellent(+50)] [style.boldCorruption(corruption)]"),
			Colour.FEMININE_PLUS) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return Util.newArrayListOfValues(
					TFModifier.REMOVAL,
					TFModifier.ORIENTATION_GYNEPHILIC,
					TFModifier.ORIENTATION_AMBIPHILIC,
					TFModifier.ORIENTATION_ANDROPHILIC);
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return Util.newArrayListOfValues(
					TFModifier.ARCANE_BOOST);
		}

		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return Util.newArrayListOfValues(TFPotency.MINOR_BOOST);
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
			} else {
				descriptions.add("Enchantable.");
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
			"Temporary fertility boost",
			"Temporary virility boost"),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.removeStatusEffect(StatusEffect.PROMISCUITY_PILL);
			
			target.addStatusEffect(StatusEffect.VIXENS_VIRILITY, 60*24*60);
			
			if(target.isPlayer()) {
				return "<p>"
						+ "The little pink pill easily slides down your throat, and within moments, you feel "
						+ ( target.hasVagina()
								? "a strange, warm glow spreading from what you guess must be your ovaries."
									+ " Your mind fogs over with an overwhelming desire to feel potent sperm spurting deep into your "+(target.isVisiblyPregnant()?"pussy":"womb")
									+", and before you can stop it, a little whimper escapes from between your [pc.lips]."
									+ (target.hasPenisIgnoreDildo()
											?" At the same time, your manhood begins to throb with need, and you feel "
											:"") 
							:"")
						+ (target.hasPenisIgnoreDildo() 
								? "an overpowering desire to sink deep into a fertile female's cunt and fill her with your [pc.cum+]."
								: "")
						+ (!target.hasPenisIgnoreDildo() && !target.hasVagina()
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
										+ (target.hasPenisIgnoreDildo()
												?" At the same time, [npc.her] manhood begins to throb with need, and [npc.she] feels "
												:"") 
									:"")
							+ (target.hasPenisIgnoreDildo()
									? "an overpowering desire to sink deep into a fertile female's cunt and fill her with [npc.cum+]."
									: "")
							+ (!target.hasPenisIgnoreDildo() && !target.hasVagina()
									?"a desperate heat in [npc.her] genderless mound."
									:"")
						+ "</p>");
			}
			
		}
	};
	
	public static AbstractItemEffectType PROMISCUITY_PILL = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Temporarily reduces fertility."),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.removeStatusEffect(StatusEffect.VIXENS_VIRILITY);
			
			target.addStatusEffect(StatusEffect.PROMISCUITY_PILL, 60*24*60);
			
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
			"Milks breasts."),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			int milkPumped = (int) Math.min(target.getBreastRawStoredMilkValue(), ItemType.getMooMilkerMaxMilk());
			target.incrementBreastStoredMilk(-milkPumped);
			if(target.isPlayer()) {
				return "<p>"
							+ "It only takes a moment before the beaker is filled with "+ Units.fluid(milkPumped, Units.UnitType.LONG)+" of your [pc.milk]."
						+ "</p>"
						+ user.addItem(AbstractItemType.generateFilledBreastPump(ItemType.MOO_MILKER_EMPTY.getColourPrimary(), target, target.getMilk(), milkPumped), false, true);
			
			} else {
				return UtilText.parse(target,
						"<p>"
							+ "It only takes a moment before the beaker is filled with "+Units.fluid(milkPumped, Units.UnitType.LONG)+" of [npc.her] [npc.milk]."
						+ "</p>"
						+ user.addItem(AbstractItemType.generateFilledBreastPump(ItemType.MOO_MILKER_EMPTY.getColourPrimary(), target, target.getMilk(), milkPumped), false, true));
			}
		}
	};
	

	public static AbstractItemEffectType PREGNANCY_TEST = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Reveals pregnancy info."),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.performImpregnationCheck(false);
			if(target.isPregnant()) {
				List <GameCharacter> fathers = new ArrayList<>();
				for(PregnancyPossibility pp : target.getPotentialPartnersAsMother()) {
					if(pp.getFather()!=null) {
						fathers.add(pp.getFather());
					}
				}
				for(GameCharacter father : fathers) {
					father.getPotentialPartnersAsFather().removeIf((pp) -> pp.getMotherId().equals(target.getId()) && target.getPregnantLitter().getFather() != (father));
				}
				
				target.getPotentialPartnersAsMother().removeIf((pp) -> !pp.getFatherId().equals(target.getPregnantLitter().getFatherId()));
				
				GameCharacter father = target.getPregnantLitter().getFather();
				
				return "<p>"
						+ "The digital readout lights up with two parallel red lines, with flashing pink text next to that displaying: '[style.italicsArcane(Pregnant!)]'"
					+ "</p>"
					+ "<p>"
						+ "Underneath the flashing pregnancy confirmation, there's some extra information, which reads:<br/>"
						+ "<i>"
						+ "Father: "+(father!=null
										?father.getNameIgnoresPlayerKnowledge()+" ("+Util.capitaliseSentence(target.getPregnantLitter().getFatherRace().getName(father))+")"
										:"Unknown!"+" ("+Util.capitaliseSentence(target.getPregnantLitter().getFatherRace().getName(target))+")")+"<br/>"
						+ "Litter size: " +target.getPregnantLitter().getTotalLitterCount()+"<br/>"
						+ "[style.colourFeminine(Daughters)]: " +(target.getPregnantLitter().getDaughtersFromFather()+target.getPregnantLitter().getDaughtersFromMother())+"<br/>"
						+ "[style.colourMasculine(Sons)]: " +(target.getPregnantLitter().getSonsFromFather()+target.getPregnantLitter().getSonsFromMother())+"<br/>"
						+ "</i>"
					+ "</p>";
				
			} else {
				return "<p>"
					+ "The digital readout lights up with a single red line, with solid black text next to that displaying: '<i>Not Pregnant.</i>'"
				+ "</p>";
			}
		}
	};
	
	
	public static AbstractItemEffectType MOTHERS_MILK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Advances pregnancy."),
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
							target.incrementStatusEffectDuration(StatusEffect.PREGNANT_1, -(target.getStatusEffectDuration(StatusEffect.PREGNANT_1)-(5*60)));
							
						} else if(target.hasStatusEffect(StatusEffect.PREGNANT_2)) {
								target.incrementStatusEffectDuration(StatusEffect.PREGNANT_2, -(target.getStatusEffectDuration(StatusEffect.PREGNANT_2)-(5*60)));
						}
						
						return "<p>"
								+ "You eagerly gulp down the rich, creamy liquid. Its delicious taste overwhelms your senses, and before you know what's happening, you've already drained the entire bottle."
								+ " With an alarmed cry, you feel your belly swell and grow, and, rubbing your [pc.hands] down over your pregnant bump, you feel that your pregnancy has advanced..."
								+ "</p>";
					}
					
				} else {
					if(target.hasStatusEffect(StatusEffect.PREGNANT_0)) {
						target.incrementStatusEffectDuration(StatusEffect.PREGNANT_0, -(target.getStatusEffectDuration(StatusEffect.PREGNANT_0)-(5*60)));
						
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
									+ " Seeing as [npc.sheIs] already in the final stage of pregnancy, nothing happens..."
								+ "</p>");
					} else {
						if(target.hasStatusEffect(StatusEffect.PREGNANT_1)) {
							target.incrementStatusEffectDuration(StatusEffect.PREGNANT_1, -(target.getStatusEffectDuration(StatusEffect.PREGNANT_1)-(5*60)));
							
						} else if(target.hasStatusEffect(StatusEffect.PREGNANT_2)) {
								target.incrementStatusEffectDuration(StatusEffect.PREGNANT_2, -(target.getStatusEffectDuration(StatusEffect.PREGNANT_2)-(5*60)));
						}
						return UtilText.parse(target,
								"<p>"
									+ "[npc.Name] gulps down the rich, creamy liquid, quickly draining the entire bottle."
									+ " With a little cry, [npc.her] belly swells and grows, and, rubbing [npc.her] [npc.hands] down over [npc.her] pregnant bump, [npc.she] feels that [npc.her] pregnancy has advanced..."
								+ "</p>");
					}
					
				} else {
					if(target.hasStatusEffect(StatusEffect.PREGNANT_0)) {
						target.incrementStatusEffectDuration(StatusEffect.PREGNANT_0, -(target.getStatusEffectDuration(StatusEffect.PREGNANT_0)-(5*60)));

						return UtilText.parse(target,
								"<p>"
									+ "[npc.Name] gulps down the rich, creamy liquid, quickly draining the entire bottle."
									+ " Although [npc.she] don't know if [npc.sheIs] actually pregnant yet, [npc.she] starts to feel a soothing warmth spreading throughout [npc.her] abdomen..."
								+ "</p>");
						
					} else {
						return UtilText.parse(target,
								"<p>"
									+ "[npc.Name] gulps down the rich, creamy liquid, quickly draining the entire bottle."
									+ " Seeing as [npc.sheIs] not pregnant, nothing happens..."
								+ "</p>");
					}
				}
			}
		}
	};
	
	// Ingredients and potions:
	
	// Strength:
	
	public static AbstractItemEffectType STR_EQUINE_CIDER = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 5% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]",
			"[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'",
			"[style.boldMinorBad(Adds)] 15% to [style.boldAlcohol(intoxication level)]"),
			Colour.ATTRIBUTE_PHYSIQUE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);
			
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A powerful wave of arcane energy washes over you..."
						:UtilText.parse(target, "A powerful wave of arcane energy washes over [npc.name]..."))
					+ "</p>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1)
					+ target.incrementAlcoholLevel(0.15f);
		}
	};

	public static AbstractItemEffectType STR_BUBBLE_MILK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 5% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]",
			"[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'"),
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
						?"A powerful wave of arcane energy washes over you..."
						:UtilText.parse(target, "A powerful wave of arcane energy washes over [npc.name]..."))
					+ "</p>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1)
					+"</p>";
		}
	};

	public static AbstractItemEffectType STR_WOLF_WHISKEY = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 5% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]",
			"[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'",
			"[style.boldMinorBad(Adds)] 40% to [style.boldAlcohol(intoxication level)]"),
			Colour.ATTRIBUTE_PHYSIQUE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A powerful wave of arcane energy washes over you..."
						:UtilText.parse(target, "A powerful wave of arcane energy washes over [npc.name]..."))
					+ "</p>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1)
					+ target.incrementAlcoholLevel(0.4f);
		}
	};
	
	public static AbstractItemEffectType STR_SWAMP_WATER = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 5% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]",
			"[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'",
			"[style.boldMinorBad(Adds)] 75% to [style.boldAlcohol(intoxication level)]"),
			Colour.ATTRIBUTE_PHYSIQUE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return "<p style='text-align:center;'>"
					+"A powerful wave of arcane energy washes over you..."
					+ "</p>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1)
					+ target.incrementAlcoholLevel(0.75f);
		}
	};
	
	public static AbstractItemEffectType STR_BLACK_RATS_RUM = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 5% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]",
			"[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'",
			"[style.boldMinorBad(Adds)] 50% to [style.boldAlcohol(intoxication level)]"),
			Colour.ATTRIBUTE_PHYSIQUE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return "<p style='text-align:center;'>"
						+"A powerful wave of arcane energy washes over you..."
					+ "</p>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1)
					+ target.incrementAlcoholLevel(0.5f);
		}
	};
	
	// Intelligence:
	
	public static AbstractItemEffectType INT_FELINE_FANCY = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 5% [style.boldAura(aura)]",
			"[style.boldGood(+1)] [style.boldIntelligence(arcane)] to 'potion effects'",
			"[style.boldMinorBad(Adds)] 10% to [style.boldAlcohol(intoxication level)]"),
			Colour.ATTRIBUTE_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);
			
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A cool wave of arcane energy washes over you..."
						:UtilText.parse(target, "A cool wave of arcane energy washes over [npc.name]..."))
					+ "</p>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 1)
					+ target.incrementAlcoholLevel(0.1f);
		}
	};
	
	public static AbstractItemEffectType INT_GRAPE_JUICE = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 5% [style.boldAura(aura)]",
			"[style.boldGood(+10)] [style.boldPhysique(critical power)] to 'potion effects'",
			"[style.boldMinorBad(Adds)] 10% to [style.boldAlcohol(intoxication level)]"),
			Colour.ATTRIBUTE_PHYSIQUE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);
			
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"Your senses are heightened..."
						:UtilText.parse(target, "[npc.NamePos] senses are heightened..."))
					+ "</p>"
					+ target.addPotionEffect(Attribute.CRITICAL_DAMAGE, 10)
					+ target.incrementAlcoholLevel(0.1f);
		}
	};
	
	public static AbstractItemEffectType INT_VANILLA_WATER = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 5% [style.boldAura(aura)]",
			"[style.boldGood(+1)] [style.boldIntelligence(arcane)] to 'potion effects'"),
			Colour.ATTRIBUTE_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);
			
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
							?"A cool wave of arcane energy washes over you..."
							:UtilText.parse(target, "A cool wave of arcane energy washes over [npc.name]..."))
					+ "</p>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 1);
		}
	};
	
	// Fitness:
	
	public static AbstractItemEffectType FIT_CANINE_CRUSH = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 5% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]",
			"[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'",
			"[style.boldMinorBad(Adds)] 5% to [style.boldAlcohol(intoxication level)]"),
			Colour.ATTRIBUTE_PHYSIQUE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A soothing wave of arcane energy washes over you..."
						:UtilText.parse(target, "A soothing wave of arcane energy washes over [npc.name]..."))
					+ "</p>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1)
					+ target.incrementAlcoholLevel(0.05f);
		}
	};
	
	public static AbstractItemEffectType FIT_SQUIRREL_JAVA = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 5% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]",
			"[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'"),
			Colour.ATTRIBUTE_PHYSIQUE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A soothing wave of arcane energy washes over you..."
						:UtilText.parse(target, "A soothing wave of arcane energy washes over [npc.name]..."))
					+ "</p>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1);
		}
	};
	
	public static AbstractItemEffectType INT_FRUIT_BAT_SQUASH = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 5% [style.boldAura(aura)]",
			"[style.boldGood(+1)] [style.boldArcane(arcane)] to 'potion effects'"),
			Colour.ATTRIBUTE_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);

			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A soothing wave of arcane energy washes over you..."
						:UtilText.parse(target, "A soothing wave of arcane energy washes over [npc.name]..."))
					+ "</p>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 1);
		}
	};
	
	public static AbstractItemEffectType FIT_EGG_NOG = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 5% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]",
			"[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'"),
			Colour.ATTRIBUTE_PHYSIQUE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);

			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A soothing wave of arcane energy washes over you..."
						:UtilText.parse(target, "A soothing wave of arcane energy washes over [npc.name]..."))
					+ "</p>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1);
		}
	};
	
	public static AbstractItemEffectType SEX_HARPY_PERFUME = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldSex(+1)] [style.boldFeminine(femininity)]",
			"[style.boldGood(+5)] [style.boldMana("+Attribute.DAMAGE_LUST.getName()+")] to 'potion effects'"),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A soothing wave of arcane energy washes over you..."
						:UtilText.parse(target, "A soothing wave of arcane energy washes over [npc.name]..."))
					+ "<br/>"
					+ target.incrementFemininity(1)
					+ "</p>"
					+ target.addPotionEffect(Attribute.DAMAGE_LUST, 5);
		}
	};
	
	public static AbstractItemEffectType SEX_SLIME_DRINK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldSex(+1)] [style.boldCorruption(Vagina Wetness)]",
			"[style.boldSex(+1)] [style.boldCorruption(Anal Wetness)]",
			"[style.boldGood(+5)] [style.boldLust("+Attribute.DAMAGE_LUST.getName()+")] to 'potion effects'"),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A warm wave of arcane energy washes over you..."
						:UtilText.parse(target, "A warm wave of arcane energy washes over [npc.name]..."))
					+ "</p>"
					+ (target.hasVagina()?target.incrementVaginaWetness(1):"")
					+ target.incrementAssWetness(1)
					+ target.addPotionEffect(Attribute.DAMAGE_LUST, 5);
		}
	};
	
	public static AbstractItemEffectType SEX_RABBIT_MORPH_DRINK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldSex(+5)] [style.boldCorruption(Fertility)] to 'potion effects'",
			"[style.boldSex(+5)] [style.boldCorruption(Virility)] to 'potion effects'",
			"[style.boldBad(-10)] [style.boldLust("+Attribute.RESISTANCE_LUST.getName()+")] to 'potion effects'"),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A warm wave of arcane energy washes over you..."
						:UtilText.parse(target, "A warm wave of arcane energy washes over [npc.name]..."))
					+ "</p>"
					+ target.addPotionEffect(Attribute.FERTILITY, 5)
					+ target.addPotionEffect(Attribute.VIRILITY, 5)
					+ target.addPotionEffect(Attribute.RESISTANCE_LUST, -10);
		}
	};
	
	public static AbstractItemEffectType SEX_MINCE_PIE = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldDmgMana(aura damage)] to 'potion effects'",
			"[style.boldGood(+1)] [style.boldDmgFire(fire damage)] to 'potion effects'"),
			Colour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"A warm wave of arcane energy washes over you..."
						:UtilText.parse(target, "A warm wave of arcane energy washes over [npc.name]..."))
					+ "</p>"
					+ target.addPotionEffect(Attribute.DAMAGE_LUST, 1)
					+ target.addPotionEffect(Attribute.DAMAGE_FIRE, 1);
		}
	};
	
	// Corruption:
	
	public static AbstractItemEffectType COR_LILITHS_GIFT = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 5% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]",
			"[style.boldGood(Restores)] 5% [style.boldAura(aura)]",
			"[style.boldGood(+25)] [style.boldCorruption(corruption)] to 'potion effects'"),
			Colour.ATTRIBUTE_CORRUPTION) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);

			return "<p style='text-align:center;'>"
						+UtilText.parse(target, "A sickly wave of corruptive arcane energy washes over [npc.name]...")
					+ "</p>"
					+ target.addPotionEffect(Attribute.MAJOR_CORRUPTION, 25);
		}
	};

	public static AbstractItemEffectType COR_IMPISH_BREW = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 10% [style.boldAura(aura)]",
			"[style.boldGood(+50)] [style.boldCorruption(corruption)] to 'potion effects'"),
			Colour.ATTRIBUTE_CORRUPTION) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/10);

			return "<p style='text-align:center;'>"
						+UtilText.parse(target, "A sickly wave of corruptive arcane energy washes over [npc.name]...")
					+ "</p>"
					+ target.addPotionEffect(Attribute.MAJOR_CORRUPTION, 50);
		}
	};
	
	public static AbstractItemEffectType MYSTERY_KINK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldFetish(Random fetish addition or removal)]"),
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
								+ " As [npc.she] staggers back from the brink of unconsciousness, [npc.she] discovers that [npc.sheIs] [style.boldGood(gained)] the [style.boldFetish("+f.getName(target)+" fetish)]!"))
						+"</p>";
				
			} else {
				Fetish f = fetishesToRemove.get(Util.random.nextInt(fetishesToRemove.size()));
				target.removeFetish(f);
				
				return "<p style='text-align:center;'>"
						+(target.isPlayer()
						?"A staggering wave of arcane energy crashes over you, the sheer strength of which almost causes you to black out."
								+ " As you stagger back from the brink of unconsciousness, you realise that you've [style.boldBad(lost)] your [style.boldFetish("+f.getName(target)+" fetish)]!"
						:UtilText.parse(target, "A staggering wave of arcane energy crashes over [npc.name], the sheer strength of which almost causes [npc.herHim] to black out."
								+ " As [npc.she] staggers back from the brink of unconsciousness, [npc.she] discovers that [npc.sheIs] [style.boldBad(lost)] [npc.her] [style.boldFetish("+f.getName(target)+" fetish)]!"))
						+"</p>";
			}
		}
	};
	
	public static AbstractItemEffectType ADDICTION_REMOVAL = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldExcellent(Removes all addictions)]"),
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
	
	public static AbstractItemEffectType MUSHROOMS = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldTfGeneric(Makes slime and orifice interiors glow)]",
			"Causes a [style.boldPsychoactive(psychoactive trip)]"),
			Colour.ATTRIBUTE_CORRUPTION) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			StringBuilder sb = new StringBuilder();

			sb.append("<p>");
				if(target.getBodyMaterial()==BodyMaterial.SLIME) {
					if(target.isPlayer()) {
						sb.append("Your slimy body starts [style.boldTfGeneric(glowing)]!");
					} else {
						sb.append(UtilText.parse(target, "[npc.NamePos] slimy body starts [style.boldTfGeneric(glowing)]!"));
					}
					
					target.getCovering(BodyCoveringType.SLIME).setPrimaryGlowing(true);
					target.getCovering(BodyCoveringType.SLIME).setSecondaryGlowing(true);

					target.getCovering(BodyCoveringType.SLIME_ANUS).setPrimaryGlowing(true);
					target.getCovering(BodyCoveringType.SLIME_ANUS).setSecondaryGlowing(true);

					target.getCovering(BodyCoveringType.SLIME_EYE).setPrimaryGlowing(true);
					target.getCovering(BodyCoveringType.SLIME_EYE).setSecondaryGlowing(true);

					target.getCovering(BodyCoveringType.SLIME_HAIR).setPrimaryGlowing(true);
					target.getCovering(BodyCoveringType.SLIME_HAIR).setSecondaryGlowing(true);

					target.getCovering(BodyCoveringType.SLIME_MOUTH).setPrimaryGlowing(true);
					target.getCovering(BodyCoveringType.SLIME_MOUTH).setSecondaryGlowing(true);

					target.getCovering(BodyCoveringType.SLIME_NIPPLES).setPrimaryGlowing(true);
					target.getCovering(BodyCoveringType.SLIME_NIPPLES).setSecondaryGlowing(true);

					target.getCovering(BodyCoveringType.SLIME_PUPILS).setPrimaryGlowing(true);
					target.getCovering(BodyCoveringType.SLIME_PUPILS).setSecondaryGlowing(true);

					target.getCovering(BodyCoveringType.SLIME_SCLERA).setPrimaryGlowing(true);
					target.getCovering(BodyCoveringType.SLIME_SCLERA).setSecondaryGlowing(true);

					target.getCovering(BodyCoveringType.SLIME_VAGINA).setPrimaryGlowing(true);
					target.getCovering(BodyCoveringType.SLIME_VAGINA).setSecondaryGlowing(true);

					target.getCovering(BodyCoveringType.SLIME_PENIS).setPrimaryGlowing(true);
					target.getCovering(BodyCoveringType.SLIME_PENIS).setSecondaryGlowing(true);
					
				} else {
					if(target.isPlayer()) {
						sb.append("The interiors of all of your orifices start to [style.boldTfGeneric(glow)]!");
					} else {
						sb.append(UtilText.parse(target, "The interiors of all of [npc.namePos] orifices start to [style.boldTfGeneric(glow)]!"));
					}

					target.getCovering(BodyCoveringType.MOUTH).setSecondaryGlowing(true);
					target.getCovering(BodyCoveringType.ANUS).setSecondaryGlowing(true);
					target.getCovering(BodyCoveringType.VAGINA).setSecondaryGlowing(true);
					target.getCovering(BodyCoveringType.PENIS).setSecondaryGlowing(true);
					target.getCovering(BodyCoveringType.NIPPLES).setSecondaryGlowing(true);
				}
			sb.append("</p>");

			target.addStatusEffect(StatusEffect.PSYCHOACTIVE, 6*60*60);

			sb.append("<p>");
				if(target.isPlayer()) {
					sb.append("Multi-coloured stars and spots start to fade in and out of your vision, and you feel your head spinning as you start to [style.boldPsychoactive(trip out)]!");
				} else {
					sb.append(UtilText.parse(target, "Multi-coloured stars and spots start to fade in and out of [npc.namePos] vision, and [npc.she] feels [npc.her] head spinning as [npc.she] starts to [style.boldPsychoactive(trip out)]!"));
				}
			sb.append("</p>");
			
			return sb.toString();
		}
	};
	
	public static AbstractItemEffectType EGGPLANT = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 5% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]",
			"[style.boldGood(Restores)] 5% [style.boldAura(aura)]"),
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
	
	
	
	public static AbstractItemEffectType EGGPLANT_POTION = new AbstractItemEffectType(null,
			Colour.BASE_PURPLE) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return Util.newArrayListOfValues(TFModifier.TF_PENIS);
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
			return Util.newArrayListOfValues(getRacialEffect(Race.HUMAN, primaryModifier, secondaryModifier, potency, user, target).getDescription());
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getRacialEffect(Race.HUMAN, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	};
	
	public static AbstractItemEffectType ADDICTION_REMOVAL_REFINEMENT = new AbstractItemEffectType(null,
			Colour.BASE_BLUE_LIGHT) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return Util.newArrayListOfValues(TFModifier.CORRUPTION);
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return Util.newArrayListOfValues(TFModifier.ARCANE_BOOST);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return Util.newArrayListOfValues(
					TFPotency.MINOR_BOOST,
					TFPotency.BOOST,
					TFPotency.MAJOR_BOOST);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			switch(potency) {
				case MINOR_BOOST:
					return Util.newArrayListOfValues("[style.boldMinorGood(-5)] [style.boldCorruption(Corruption)]");
				case BOOST:
					return Util.newArrayListOfValues("[style.boldGood(-10)] [style.boldCorruption(Corruption)]");
				case MAJOR_BOOST:
					return Util.newArrayListOfValues("[style.boldExcellent(-15)] [style.boldCorruption(Corruption)]");
				case MINOR_DRAIN:
				case DRAIN:
				case MAJOR_DRAIN:
					break;
			}
			return Util.newArrayListOfValues("");
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			switch(potency) {
				case MINOR_BOOST:
					return "<p style='text-align:center;'>"
								+ "For a moment, it looks as though nothing is going to happen,"
									+ " but as [npc.name] [npc.verb(swallow)] down the last couple of drops remaining in [npc.her] mouth, a sudden, cascading wave of purify energy rushes through [npc.herHim]."
								+ " Accompanied by a faint, light-blue flash which seems to radiate from every visible part of [npc.her] body, this energy rises up into [npc.her] head,"
									+ " where it quickly gets to work purifying [npc.her] thoughts and calming [npc.her] libido..."
							+"</p>"
							+ target.incrementAttribute(Attribute.MAJOR_CORRUPTION, -5);
				case BOOST:
					return "<p style='text-align:center;'>"
							+ "For a moment, it looks as though nothing is going to happen,"
								+ " but as [npc.name] [npc.verb(swallow)] down the last couple of drops remaining in [npc.her] mouth, a sudden, cascading wave of purify energy rushes through [npc.herHim]."
							+ " Accompanied by a bright, light-blue flash which seems to radiate from every visible part of [npc.her] body, this energy rises up into [npc.her] head,"
								+ " where it quickly gets to work purifying [npc.her] thoughts and calming [npc.her] libido..."
						+"</p>"
						+ target.incrementAttribute(Attribute.MAJOR_CORRUPTION, -10);
				case MAJOR_BOOST:
					return "<p style='text-align:center;'>"
							+ "For a moment, it looks as though nothing is going to happen,"
								+ " but as [npc.name] [npc.verb(swallow)] down the last couple of drops remaining in [npc.her] mouth, a sudden, cascading wave of purify energy rushes through [npc.herHim]."
							+ " Accompanied by a blinding, light-blue flash which seems to radiate from every visible part of [npc.her] body, this energy rises up into [npc.her] head,"
								+ " where it quickly gets to work purifying [npc.her] thoughts and calming [npc.her] libido..."
						+"</p>"
						+ target.incrementAttribute(Attribute.MAJOR_CORRUPTION, -15);
				case MINOR_DRAIN:
				case DRAIN:
				case MAJOR_DRAIN:
					break;
			}
			return "";
		}
	};
	
	public static AbstractItemEffectType GIFT_CHOCOLATES = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 30% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]"),
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
			"[style.boldGood(+5)] [style.boldSeduction(seduction damage)] to 'potion effects'"),
			Colour.ATTRIBUTE_LUST) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"You smell a lot nicer now..."
						:UtilText.parse(target, "[npc.Name] smells a lot nicer now..."))
					+ "<br/>"
					+ target.addPotionEffect(Attribute.DAMAGE_LUST, 5)
					+"</p>";
		}
	};
	
	
	public static AbstractItemEffectType PRESENT = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Contains a random item."),
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
			"[style.boldGood(+2)] [style.boldPhysique(physique)] to 'potion effects'",
			"[style.boldGood(+2)] [style.boldIntelligence(arcane)] to 'potion effects'",
			"[style.boldGood(+5)] [style.boldCorruption(corruption)] to 'potion effects'"),
			Colour.RACE_HUMAN) {
		
		@Override
		public String getPotionDescriptor() {
			return "demonic";
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "You start to feel like this item is just for testing purposes..."
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2)
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 2)
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_CORRUPTION, 5);
		}
	};
	
	public static AbstractItemEffectType RACE_ANGELS_TEARS = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldIntelligence(arcane)] to 'potion effects'",
			"[style.boldGood(+1)] [style.boldPhysique(physique)] to 'potion effects'"),
			Colour.RACE_HUMAN) {

		@Override
		public String getPotionDescriptor() {
			return "angelic";
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p style='text-align:center;'>"
						+(target.isPlayer()
							?"You start to feel a lot healthier..."
							:UtilText.parse(target, "[npc.Name] starts to feel a lot healthier..."))
					+ "</p>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1)
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 1);
		}
	};
	
	public static AbstractItemEffectType RACE_CANINE_CRUNCH = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+2)] [style.boldPhysique(physique)] to 'potion effects'"),
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
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2);
		}
	};
	
	public static AbstractItemEffectType RACE_FOX_PIE = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+2)] [style.boldPhysique(physique)] to 'potion effects'"),
			Colour.RACE_FOX_MORPH) {

		@Override
		public String getPotionDescriptor() {
			return "vulpine";
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
			"[style.boldGood(+2)] [style.boldPhysique(physique)] to 'potion effects'"),
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
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2);
		}
	};
	
	public static AbstractItemEffectType RACE_CARROT_CAKE = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldSex(+15)] [style.boldCorruption(Fertility)] to 'potion effects'",
			"[style.boldSex(+15)] [style.boldCorruption(Virility)] to 'potion effects'",
			"[style.boldBad(-25)] [style.boldLust("+Attribute.RESISTANCE_LUST.getName()+")] to 'potion effects'"),
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
					+ "<br/>"
					+ target.addPotionEffect(Attribute.FERTILITY, 15)
					+ target.addPotionEffect(Attribute.VIRILITY, 15)
					+ target.addPotionEffect(Attribute.RESISTANCE_LUST, -25);
		}
	};
	
	public static AbstractItemEffectType RACE_KITTYS_REWARD = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+3)] [style.boldIntelligence(arcane)] to 'potion effects'"),
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
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 3);
		}
	};
	
	public static AbstractItemEffectType RACE_ROUND_NUTS = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+2)] [style.boldIntelligence(arcane)] to 'potion effects'"),
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
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 2);
		}
	};
	
	public static AbstractItemEffectType RACE_FRUIT_SALAD = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+2)] [style.boldIntelligence(arcane)] to 'potion effects'"),
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
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 2);
		}
	};
	
	public static AbstractItemEffectType RACE_SUGAR_CARROT_CUBE = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+3)] [style.boldPhysique(physique)] to 'potion effects'"),
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
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
		}
	};
	
	public static AbstractItemEffectType RACE_SUGAR_COOKIE = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+3)] [style.boldPhysique(physique)] to 'potion effects'"),
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
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
		}
	};
	
	public static AbstractItemEffectType RACE_ALLIGATORS_GUMBO = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+3)] [style.boldPhysique(physique)] to 'potion effects'"),
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
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
		}
	};
	
	public static AbstractItemEffectType RACE_BUBBLE_CREAM = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+3)] [style.boldPhysique(physique)] to 'potion effects'"),
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
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
		}
	};
	
	public static AbstractItemEffectType RACE_MEAT_AND_MARROW = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+5)] [style.boldPhysique(physique)] to 'potion effects'",
			"[style.boldGood(+3)] [style.boldCorruption(corruption)] to 'potion effects'"),
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
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 5)
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_CORRUPTION, 3);
		}
	};
	
	public static AbstractItemEffectType RACE_LOLLIPOP = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+5)] [style.boldPhysique(physique)] to 'potion effects'",
			"[style.boldSex(+3)] [style.boldFeminine(femininity)]"),
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
					+ "<br/>"
					+ target.incrementFemininity(3)
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 5);
		}
	};
	
	public static AbstractItemEffectType RACE_BIOJUICE = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+25)] [style.boldCorruption(corruption)]",
			"[style.boldGood(+25)] [style.boldCorruption(corruption)] to 'potion effects'",
			"[style.boldSlime(Transforms body into slime!)]"),
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
				return target.incrementAttribute(Attribute.MAJOR_CORRUPTION, 25)
						+ "<br/>"
						+ target.addPotionEffect(Attribute.MAJOR_CORRUPTION, 25)
						+ "<br/>"
						+ target.setBodyMaterial(BodyMaterial.SLIME);
			}
		}
	};
	
	// Essences:
	
//	public static AbstractItemEffectType BOTTLED_ESSENCE_ANGEL = new AbstractItemEffectType(Util.newArrayListOfValues(
//			"[style.boldGood(+1)] [style.boldAngel(Angel)] essence"),
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
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence"),
			Colour.GENERIC_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_CAT_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+2)] [style.boldPhysique("+Util.capitaliseSentence(Attribute.MAJOR_PHYSIQUE.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldCat(cat-morphs)]"),
			Colour.RACE_CAT_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_CAT_MORPH, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldCat(cat-morphs)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_COW_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+2)] [style.boldPhysique("+Util.capitaliseSentence(Attribute.MAJOR_PHYSIQUE.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldCow(cow-morphs)]"),
			Colour.RACE_COW_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_COW_MORPH, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldCow(cow-morphs)]!";
		}
	};

 	public static AbstractItemEffectType BOTTLED_ESSENCE_SQUIRREL_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+2)] [style.boldPhysique("+Util.capitaliseSentence(Attribute.MAJOR_PHYSIQUE.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldSquirrel(squirrel-morphs)]"),
			Colour.RACE_SQUIRREL_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_SQUIRREL_MORPH, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldSquirrel(squirrel-morphs)]!";
		}
	};

 	public static AbstractItemEffectType BOTTLED_ESSENCE_RAT_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+2)] [style.boldPhysique("+Util.capitaliseSentence(Attribute.MAJOR_PHYSIQUE.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldRat(rat-morphs)]"),
			Colour.RACE_RAT_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_RAT_MORPH, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldRat(rat-morphs)]!";
		}
	};

 	public static AbstractItemEffectType BOTTLED_ESSENCE_RABBIT_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+5)] [style.boldLust("+Util.capitaliseSentence(Attribute.DAMAGE_LUST.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldRabbit(rabbit-morphs)]"),
			Colour.RACE_RABBIT_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_RABBIT_MORPH, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldRabbit(rabbit-morphs)]!";
		}
	};

 	public static AbstractItemEffectType BOTTLED_ESSENCE_BAT_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+2)] [style.boldPhysique("+Util.capitaliseSentence(Attribute.MAJOR_PHYSIQUE.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldBat(bat-morphs)]"),
			Colour.RACE_BAT_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_BAT_MORPH, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldBat(bat-morphs)]!";
		}
	};
	
 	public static AbstractItemEffectType BOTTLED_ESSENCE_ALLIGATOR_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+2)] [style.boldPhysique("+Util.capitaliseSentence(Attribute.MAJOR_PHYSIQUE.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldAlligator(alligator-morphs)]"),
			Colour.RACE_ALLIGATOR_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_ALLIGATOR_MORPH, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldAlligator(alligator-morphs)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_DEMON = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+2)] [style.boldArcane("+Util.capitaliseSentence(Attribute.MAJOR_ARCANE.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldDemon(demons)]"),
			Colour.RACE_DEMON) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_DEMON, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldDemon(demons)]!";
		}
	};

	public static AbstractItemEffectType BOTTLED_ESSENCE_IMP = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+5)] [style.boldLust("+Util.capitaliseSentence(Attribute.DAMAGE_LUST.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldImp(imps)]"),
			Colour.RACE_IMP) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_IMP, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldImp(imps)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_DOG_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+2)] [style.boldPhysique("+Util.capitaliseSentence(Attribute.MAJOR_PHYSIQUE.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldDog(dog-morphs)]"),
			Colour.RACE_DOG_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_DOG_MORPH, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldDog(dog-morphs)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_HARPY = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+5)] [style.boldLust("+Util.capitaliseSentence(Attribute.DAMAGE_LUST.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldHarpy(harpies)]"),
			Colour.RACE_HARPY) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_HARPY, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldHarpy(harpies)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_HORSE_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+2)] [style.boldPhysique("+Util.capitaliseSentence(Attribute.MAJOR_PHYSIQUE.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldHorse(horse-morphs)]"),
			Colour.RACE_HORSE_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_HORSE_MORPH, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldHorse(horse-morphs)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_REINDEER_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+2)] [style.boldPhysique("+Util.capitaliseSentence(Attribute.MAJOR_PHYSIQUE.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldReindeer(reindeer-morphs)]"),
			Colour.RACE_REINDEER_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_REINDEER_MORPH, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldReindeer(reindeer-morphs)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_HUMAN = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+2)] [style.boldPhysique("+Util.capitaliseSentence(Attribute.MAJOR_PHYSIQUE.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldHuman(humans)]"),
			Colour.RACE_HUMAN) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_HUMAN, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldHuman(humans)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_WOLF_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+2)] [style.boldPhysique("+Util.capitaliseSentence(Attribute.MAJOR_PHYSIQUE.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldWolf(wolf-morphs)]"),
			Colour.RACE_WOLF_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_WOLF_MORPH, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldWolf(wolf-morphs)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_FOX_MORPH = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+2)] [style.boldPhysique("+Util.capitaliseSentence(Attribute.MAJOR_PHYSIQUE.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldFox(fox-morphs)]"),
			Colour.RACE_FOX_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_FOX_MORPH, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldFox(fox-morphs)]!";
		}
	};
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_SLIME = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane)] essence",
			"[style.boldGood(+5)] [style.boldLust("+Util.capitaliseSentence(Attribute.DAMAGE_LUST.getName())+")]",
			"[style.boldGood(+25)] [style.bold(damage vs)] [style.boldSlime(slimes)]"),
			Colour.RACE_WOLF_MORPH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(TFEssence.ARCANE, 1, false);
			target.addStatusEffect(StatusEffect.COMBAT_BONUS_SLIME, 60*4*60);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane)] essence, and are now far more effective at fighting [style.boldSlime(slimes)]!";
		}
	};
	
	
	// Specials:
	
	public static AbstractItemEffectType BIMBO_LOLLIPOP = new AbstractItemEffectType(Util.newArrayListOfValues(
			"<b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Bimbo</b> <b style='color:"+Colour.RACE_HARPY.toWebHexString()+";'>harpy</b> <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>transformation</b>"),
			Colour.RACE_HARPY) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
						+ UtilText.parse(target, "As the lollipop's transformative effects start to make themselves known, [npc.name] [npc.verb(start)] to feel very light-headed...")
					+ "</p>");
			
			if(!target.hasFetish(Fetish.FETISH_BIMBO)) {
				target.addFetish(Fetish.FETISH_BIMBO);
				if(target.isPlayer()) {
					sb.append("<br/>"
							+ "<p>"
								+ "A giggle escapes from between your [pc.lips], and you suddenly find yourself unable to think of anything other than how, like, super awesome bimbos are and stuff!"
								+ "<br/><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>You have gained the bimbo fetish!</b>"
							+ "</p>");
				} else {
					sb.append(UtilText.parse(target, "<br/>"
							+ "<p>"
								+ "A giggle escapes from between [npc.namePos] [npc.lips], and [npc.she] suddenly finds [npc.herself] unable to think of anything other than how, like, super awesome bimbos are and stuff!"
								+ "<br/><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>[npc.Name] has gained the bimbo fetish!</b>"
							+ "</p>"));
				}
			}
			
			
			// Non-racial changes
			if(target.getFemininityValue()<95) {
				sb.append("<br/>" + target.setFemininity(95));
			}
			if(target.getBreastSize().getMeasurement()<CupSize.DD.getMeasurement()) {
				sb.append("<br/>" + target.setBreastSize(CupSize.DD.getMeasurement()));
			}
			if(target.getAssSize().getValue()<AssSize.FOUR_LARGE.getValue()) {
				sb.append("<br/>" + target.setAssSize(AssSize.FOUR_LARGE.getValue()));
			}
			if(target.getHipSize().getValue()<HipSize.FOUR_WOMANLY.getValue()) {
				sb.append("<br/>" + target.setHipSize(HipSize.FOUR_WOMANLY.getValue()));
			}
			if(target.getHairType()!=HairType.HARPY) {
				sb.append("<br/>" + target.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, Colour.COVERING_BLEACH_BLONDE, false, Colour.COVERING_BLEACH_BLONDE, false), true));
			}
			if(target.getSkinType()!=SkinType.HARPY) {
				sb.append("<br/>" + target.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, Colour.COVERING_BLEACH_BLONDE, false, Colour.COVERING_BLEACH_BLONDE, false), true));
			}
			
			// Harpy TFs:
			if(target.getFaceType()!=FaceType.HUMAN)
				sb.append("<br/>" + target.setFaceType(FaceType.HUMAN));
			if(target.getEarType()!=EarType.HARPY)
				sb.append("<br/>" + target.setEarType(EarType.HARPY));
			if(target.getEyeType()!=EyeType.HARPY)
				sb.append("<br/>" + target.setEyeType(EyeType.HARPY));
			if(target.getHairType()!=HairType.HARPY)
				sb.append("<br/>" + target.setHairType(HairType.HARPY));
			if(target.getSkinType()!=SkinType.HUMAN)
				sb.append("<br/>" + target.setSkinType(SkinType.HUMAN));

			if(target.getWingType()!=WingType.NONE)
				sb.append("<br/>" + target.setWingType(WingType.NONE));
			if(target.getHornType()!=HornType.NONE)
				sb.append("<br/>" + target.setHornType(HornType.NONE));

			if(target.getArmType()!=ArmType.HARPY)
				sb.append("<br/>" + target.setArmType(ArmType.HARPY));
			if(target.getLegType()!=LegType.HARPY)
				sb.append("<br/>" + target.setLegType(LegType.HARPY));
			if(target.getTailType()!=TailType.HARPY)
				sb.append("<br/>" + target.setTailType(TailType.HARPY));

			if(target.getBreastType()!=BreastType.HARPY)
				sb.append("<br/>" + target.setBreastType(BreastType.HARPY));
			if(target.getAssType()!=AssType.HARPY)
				sb.append("<br/>" + target.setAssType(AssType.HARPY));

			if(target.hasPenisIgnoreDildo()) {
				sb.append("<br/>" + target.setPenisType(PenisType.AVIAN));

				if(target.getPenisRawCumStorageValue()<CumProduction.TWO_SMALL_AMOUNT.getMedianValue()) {
					sb.append("<br/>" + target.setPenisCumStorage(CumProduction.TWO_SMALL_AMOUNT.getMedianValue()));
					target.fillCumToMaxStorage();
				}
			}
			if(target.hasVagina()) {
				sb.append("<br/>" + target.setVaginaType(VaginaType.HARPY));
			}
			
			return sb.toString();
		}
	};
	
	public static AbstractItemEffectType NYMPHO_LOLLIPOP = new AbstractItemEffectType(Util.newArrayListOfValues(
			"<b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Nympho</b> <b style='color:"+Colour.RACE_HARPY.toWebHexString()+";'>harpy</b> <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>transformation</b>"),
			Colour.RACE_HARPY) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
						+ UtilText.parse(target, "As the lollipop's transformative effects start to make themselves known, [npc.name] [npc.verb(start)] to feel very light-headed...")
					+ "</p>");
			
			if(!target.hasTrait(Perk.NYMPHOMANIAC, false)) {
				target.addPerk(Perk.NYMPHOMANIAC);
				if(target.isPlayer()) {
					sb.append("<br/>"
							+ "<p>"
								+ "A desperate moan escapes from between your [pc.lips], and you suddenly find yourself unable to think of anything other than sex, sex, and more sex!"
								+ "<br/><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>You have gained the nymphomaniac perk!</b>"
							+ "</p>");
				} else {
					sb.append(UtilText.parse(target, "<br/>"
							+ "<p>"
								+ "A desperate moan escapes from between [npc.namePos] [npc.lips], and [npc.she] suddenly finds [npc.herself] unable to think of anything other than sex, sex, and more sex!"
								+ "<br/><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>[npc.Name] has gained the nymphomaniac perk!</b>"
							+ "</p>"));
				}
			}
			
			// Non-racial changes
			if(target.getFemininityValue()<95) {
				sb.append("<br/>" + target.setFemininity(95));
			}
			if(target.getBreastSize().getMeasurement()<CupSize.C.getMeasurement()) {
				sb.append("<br/>" + target.setBreastSize(CupSize.C.getMeasurement()));
			}
			if(target.getAssSize().getValue()<AssSize.THREE_NORMAL.getValue()) {
				sb.append("<br/>" + target.setAssSize(AssSize.THREE_NORMAL.getValue()));
			}
			if(target.getHipSize().getValue()<HipSize.THREE_GIRLY.getValue()) {
				sb.append("<br/>" + target.setHipSize(HipSize.THREE_GIRLY.getValue()));
			}
			if(target.getHairType()!=HairType.HARPY) {
				sb.append("<br/>" + target.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, Colour.COVERING_PINK, false, Colour.COVERING_PINK, false), true));
			}
			if(target.getSkinType()!=SkinType.HARPY) {
				sb.append("<br/>" + target.setHairCovering(new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, Colour.COVERING_PINK, false, Colour.COVERING_PINK, false), true));
			}
			
			// Harpy TFs:
			if(target.getFaceType()!=FaceType.HUMAN)
				sb.append("<br/>" + target.setFaceType(FaceType.HUMAN));
			if(target.getEarType()!=EarType.HARPY)
				sb.append("<br/>" + target.setEarType(EarType.HARPY));
			if(target.getEyeType()!=EyeType.HARPY)
				sb.append("<br/>" + target.setEyeType(EyeType.HARPY));
			if(target.getHairType()!=HairType.HARPY)
				sb.append("<br/>" + target.setHairType(HairType.HARPY));
			if(target.getSkinType()!=SkinType.HUMAN)
				sb.append("<br/>" + target.setSkinType(SkinType.HUMAN));

			if(target.getWingType()!=WingType.NONE)
				sb.append("<br/>" + target.setWingType(WingType.NONE));
			if(target.getHornType()!=HornType.NONE)
				sb.append("<br/>" + target.setHornType(HornType.NONE));

			if(target.getArmType()!=ArmType.HARPY)
				sb.append("<br/>" + target.setArmType(ArmType.HARPY));
			if(target.getLegType()!=LegType.HARPY)
				sb.append("<br/>" + target.setLegType(LegType.HARPY));
			if(target.getTailType()!=TailType.HARPY)
				sb.append("<br/>" + target.setTailType(TailType.HARPY));

			if(target.getBreastType()!=BreastType.HARPY)
				sb.append("<br/>" + target.setBreastType(BreastType.HARPY));
			if(target.getAssType()!=AssType.HARPY)
				sb.append("<br/>" + target.setAssType(AssType.HARPY));
			if(target.getAssWetness().getValue()<Wetness.TWO_MOIST.getValue())
				sb.append("<br/>" + target.setAssWetness(Wetness.TWO_MOIST.getValue()));
				

			if(target.hasPenisIgnoreDildo()) {
				sb.append("<br/>" + target.setPenisType(PenisType.AVIAN));

				if(target.getPenisRawCumStorageValue()<CumProduction.THREE_AVERAGE.getMedianValue()) {
					sb.append("<br/>" + target.setPenisCumStorage(CumProduction.THREE_AVERAGE.getMedianValue()));
					target.fillCumToMaxStorage();
				}
			}
			if(target.hasVagina()) {
				sb.append("<br/>" + target.setVaginaType(VaginaType.HARPY));

				if(target.getVaginaWetness().getValue()<Wetness.FOUR_SLIMY.getValue())
					sb.append("<br/>" + target.setVaginaWetness(Wetness.FOUR_SLIMY.getValue()));
			}
			
			return sb.toString();
		}
	};
	
	public static AbstractItemEffectType DOMINANT_PERFUME = new AbstractItemEffectType(Util.newArrayListOfValues(
			"<b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Dominant</b> <b style='color:"+Colour.RACE_HARPY.toWebHexString()+";'>harpy</b> <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>transformation</b>"),
			Colour.RACE_HARPY) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ UtilText.parse(target, "As the perfume's transformative effects start to make themselves known, [npc.name] [npc.verb(start)] to feel very light-headed...")
					+ "</p>");
			
			if(!target.hasFetish(Fetish.FETISH_DOMINANT)) {
				target.addFetish(Fetish.FETISH_DOMINANT);
				if(target.isPlayer()) {
					sb.append("<br/>"
							+ "<p>"
								+ "A deep groan escapes from between your [pc.lips], and you suddenly find yourself thinking of how much you want to dominate the next person you come across!"
								+ "<br/><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>You have gained the dominant fetish!</b>"
							+ "</p>");
				} else {
					sb.append(UtilText.parse(target, "<br/>"
							+ "<p>"
								+ "A deep groan escapes from between [npc.namePos] [npc.lips], and [npc.she] suddenly finds [npc.herself] thinking of how much [npc.she] wants to dominate the next person [npc.she] meets!"
								+ "<br/><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>[npc.Name] has gained the dominant fetish!</b>"
							+ "</p>"));
				}
			}
			
			// Non-racial changes
			if(target.getFemininityValue()<95) {
				sb.append("<br/>" + target.setFemininity(95));
			}
			if(target.getBreastSize().getMeasurement()<CupSize.C.getMeasurement()) {
				sb.append("<br/>" + target.setBreastSize(CupSize.C.getMeasurement()));
			}
			if(target.getAssSize().getValue()<AssSize.THREE_NORMAL.getValue()) {
				sb.append("<br/>" + target.setAssSize(AssSize.THREE_NORMAL.getValue()));
			}
			if(target.getHipSize().getValue()<HipSize.THREE_GIRLY.getValue()) {
				sb.append("<br/>" + target.setHipSize(HipSize.THREE_GIRLY.getValue()));
			}
			if(target.getHairType()!=HairType.HARPY) {
				sb.append("<br/>" + target.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, Colour.COVERING_BLACK, false, Colour.COVERING_BLACK, false), true));
			}
			if(target.getSkinType()!=SkinType.HARPY) {
				sb.append("<br/>" + target.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, Colour.COVERING_RED, false, Colour.COVERING_RED, false), true));
			}
			
			// Harpy TFs:
			if(target.getFaceType()!=FaceType.HUMAN)
				sb.append("<br/>" + target.setFaceType(FaceType.HUMAN));
			if(target.getEarType()!=EarType.HARPY)
				sb.append("<br/>" + target.setEarType(EarType.HARPY));
			if(target.getEyeType()!=EyeType.HARPY)
				sb.append("<br/>" + target.setEyeType(EyeType.HARPY));
			if(target.getHairType()!=HairType.HARPY)
				sb.append("<br/>" + target.setHairType(HairType.HARPY));
			if(target.getSkinType()!=SkinType.HUMAN)
				sb.append("<br/>" + target.setSkinType(SkinType.HUMAN));

			if(target.getWingType()!=WingType.NONE)
				sb.append("<br/>" + target.setWingType(WingType.NONE));
			if(target.getHornType()!=HornType.NONE)
				sb.append("<br/>" + target.setHornType(HornType.NONE));

			if(target.getArmType()!=ArmType.HARPY)
				sb.append("<br/>" + target.setArmType(ArmType.HARPY));
			if(target.getLegType()!=LegType.HARPY)
				sb.append("<br/>" + target.setLegType(LegType.HARPY));
			if(target.getTailType()!=TailType.HARPY)
				sb.append("<br/>" + target.setTailType(TailType.HARPY));

			if(target.getBreastType()!=BreastType.HARPY)
				sb.append("<br/>" + target.setBreastType(BreastType.HARPY));
			if(target.getAssType()!=AssType.HARPY)
				sb.append("<br/>" + target.setAssType(AssType.HARPY));
				

			if(target.hasPenisIgnoreDildo()) {
				sb.append("<br/>" + target.setPenisType(PenisType.AVIAN));

				if(target.getPenisRawCumStorageValue()<CumProduction.TWO_SMALL_AMOUNT.getMedianValue()) {
					sb.append("<br/>" + target.setPenisCumStorage(CumProduction.TWO_SMALL_AMOUNT.getMedianValue()));
					target.fillCumToMaxStorage();
				}
			}
			if(target.hasVagina()) {
				sb.append("<br/>" + target.setVaginaType(VaginaType.HARPY));
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
					TFModifier.TF_MOD_FETISH_BODY_PART,
					TFModifier.TF_MOD_FETISH_BEHAVIOUR);
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
					TFPotency.BOOST,
					TFPotency.MINOR_BOOST,
					TFPotency.MINOR_DRAIN,
					TFPotency.DRAIN);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			String descriptor = primaryModifier==TFModifier.TF_MOD_FETISH_BODY_PART ? "body-part": "behavioural";
			
			if(potency==TFPotency.BOOST) {
				if(secondaryModifier == TFModifier.NONE) {
					return Util.newArrayListOfValues("Adds a [style.boldFetish(random "+descriptor+" fetish)].");
				} else {
					return Util.newArrayListOfValues("Adds the [style.boldFetish("+secondaryModifier.getName()+" fetish)].");
				}
				
			} else if(potency==TFPotency.MINOR_BOOST) {
				if(secondaryModifier == TFModifier.NONE) {
					return Util.newArrayListOfValues("Boosts [style.boldLust(desire)] for a [style.boldFetish(random "+descriptor+" fetish)].");
				} else {
					return Util.newArrayListOfValues("Boosts [style.boldLust(desire)] for the [style.boldFetish("+secondaryModifier.getName()+" fetish)].");
				}
				
			} else if(potency==TFPotency.MINOR_DRAIN) {
				if(secondaryModifier == TFModifier.NONE) {
					return Util.newArrayListOfValues("Lowers [style.boldLust(desire)] for a [style.boldFetish(random "+descriptor+" fetish)] (if that fetish is not already owned).");
				} else {
					return Util.newArrayListOfValues("Lowers [style.boldLust(desire)] for the [style.boldFetish("+secondaryModifier.getName()+" fetish)] (if that fetish is not already owned).");
				}
				
			} else {
				if(secondaryModifier == TFModifier.NONE) {
					return Util.newArrayListOfValues("Removes a [style.boldFetish(random "+descriptor+" fetish)].");
				} else {
					return Util.newArrayListOfValues("Removes the [style.boldFetish("+secondaryModifier.getName()+" fetish)].");
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
						return target.addFetish(f);
						
					} else {
						return "<p>"
									+"[style.colourDisabled(Nothing happens...)]"
								+"</p>";
					}
					
				} else {
					Fetish fetish = secondaryModifier.getFetish();
					
					return target.addFetish(fetish);
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
						
						return target.setFetishDesire(f, newDesire);
						
					} else {
						return "<p>"
									+"[style.colourDisabled(Nothing happens...)]"
								+"</p>";
					}
					
				} else {
					Fetish fetish = secondaryModifier.getFetish();
					FetishDesire newDesire = target.getFetishDesire(fetish).getNextDesire();
					
					return target.setFetishDesire(fetish, newDesire);
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
						
						return target.setFetishDesire(f, newDesire);
						
					} else {
						return "<p>"
									+"[style.colourDisabled(Nothing happens...)]"
								+"</p>";
					}
					
				} else {
					Fetish fetish = secondaryModifier.getFetish();
					FetishDesire newDesire = target.getFetishDesire(fetish).getPreviousDesire();
					
					return target.setFetishDesire(fetish, newDesire);
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
						return target.removeFetish(f);
						
					} else {
						return "<p>"
									+"[style.colourDisabled(Nothing happens...)]"
								+"</p>";
					}
					
				} else {
					Fetish fetish = secondaryModifier.getFetish();
					
					return target.removeFetish(fetish);
				}
			}
		}
	};
	
	// RACIAL:
	
	public static AbstractItemEffectType DEBUG_DEMON_POTION_EFFECT = new AbstractItemEffectType(null,
			Colour.RACE_DEMON) {
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			List<String> effectsDescription = new ArrayList<>();
			
			effectsDescription.add("[style.boldBad(Does not affect player or unique characters)]");
			
			effectsDescription.add("[style.boldTfGeneric(Transforms)] non-demons into [style.boldDemon(half-demons)]");

			effectsDescription.add("[style.boldTfGeneric(Transforms)] half-demons into [style.boldDemon(demons)]");
			
			return effectsDescription;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			if(target.isPlayer()) {
				return "<p style='text-align:center'>[style.italicsDisabled(This item does not work on you...)]</p>";
			}
			if(target.isUnique() && (!target.isSlave() || target.getOwner().isPlayer())) {
				return "<p style='text-align:center'>[style.italicsDisabled(This item does not work on non-slave unique characters...)]</p>";
			}
			
			Subspecies sub = Subspecies.getFleshSubspecies(target);
			if(sub.getRace()!=Race.DEMON) {
				target.setBody(CharacterUtils.generateHalfDemonBody(target, target.getGender(), sub, true));
				return UtilText.parse(target, "<p style='text-align:center; color:"+Colour.RACE_DEMON.toWebHexString()+";'><i>[npc.Name] is now [npc.a_race]!</i></p>");
			} else {
				target.setBody(target.getGender(), Subspecies.DEMON, RaceStage.GREATER);
				target.setSubspeciesOverride(Subspecies.DEMON);
				return UtilText.parse(target, "<p style='text-align:center; color:"+Colour.RACE_DEMON.toWebHexString()+";'><i>[npc.Name] is now [npc.a_race]!</i></p>");
			}
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
			return Util.newArrayListOfValues(getRacialEffect(Race.HUMAN, primaryModifier, secondaryModifier, potency, user, target).getDescription());
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
			return Util.newArrayListOfValues(getRacialEffect(Race.CAT_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription());
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
			return Util.newArrayListOfValues(getRacialEffect(Race.COW_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription());
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
			return Util.newArrayListOfValues(getRacialEffect(Race.SQUIRREL_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription());
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
			return Util.newArrayListOfValues(getRacialEffect(Race.RAT_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription());
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
			return Util.newArrayListOfValues(getRacialEffect(Race.RABBIT_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription());
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
			return Util.newArrayListOfValues(getRacialEffect(Race.BAT_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription());
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
			return Util.newArrayListOfValues(getRacialEffect(Race.DOG_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription());
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getRacialEffect(Race.DOG_MORPH, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	};
	
	public static AbstractItemEffectType RACE_FOX_MORPH = new AbstractItemEffectType(null,
			Colour.RACE_FOX_MORPH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFRacialBodyPartsList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(Race.FOX_MORPH, primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(Race.FOX_MORPH, primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(getRacialEffect(Race.FOX_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription());
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getRacialEffect(Race.FOX_MORPH, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
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
			return Util.newArrayListOfValues(getRacialEffect(Race.ALLIGATOR_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription());
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
			return Util.newArrayListOfValues(getRacialEffect(Race.HORSE_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription());
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
			return Util.newArrayListOfValues(getRacialEffect(Race.REINDEER_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription());
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
			return Util.newArrayListOfValues(getRacialEffect(Race.WOLF_MORPH, primaryModifier, secondaryModifier, potency, user, target).getDescription());
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
			return Util.newArrayListOfValues(getRacialEffect(Race.HARPY, primaryModifier, secondaryModifier, potency, user, target).getDescription());
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getRacialEffect(Race.HARPY, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	};

	public static AbstractItemEffectType RACE_SLIME = new AbstractItemEffectType(null,
			Colour.RACE_SLIME) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return Util.newArrayListOfValues(TFModifier.TF_MATERIAL_FLESH);
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			return Util.newArrayListOfValues(TFModifier.ARCANE_BOOST);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return Util.newArrayListOfValues(TFPotency.MINOR_BOOST);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues("Changes the target's body material to flesh.");
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return target.setBodyMaterial(BodyMaterial.FLESH);
		}
	};
	
	// CLOTHING:
	
	public static AbstractItemEffectType CLOTHING = new AbstractItemEffectType(null,
			Colour.RARITY_RARE) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getClothingPrimaryList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE) {
				return TFModifier.getClothingAttributeList();
				
			} else if(primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
				return TFModifier.getClothingMajorAttributeList();
				
			} else if(primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR) {
				return TFModifier.getTFBehaviouralFetishList();
				
			} else if(primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART) {
				return TFModifier.getTFBodyPartFetishList();
				
			} else if(primaryModifier == TFModifier.CLOTHING_SPECIAL) {
				if(Main.game.getPlayer().isHasSlaverLicense()) {
					return Util.newArrayListOfValues(TFModifier.CLOTHING_SEALING, TFModifier.CLOTHING_ENSLAVEMENT);
				} else {
					return Util.newArrayListOfValues(TFModifier.CLOTHING_SEALING);
				}
				
			} else if(primaryModifier == TFModifier.CLOTHING_CONDOM) {
				return Util.newArrayListOfValues(TFModifier.ARCANE_BOOST);
				
			} else {
				return getClothingTFSecondaryModifiers(primaryModifier);
			}
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE
					|| primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART
					|| primaryModifier == TFModifier.CLOTHING_CONDOM
					|| !getClothingTFSecondaryModifiers(primaryModifier).isEmpty()) {
				return TFPotency.getAllPotencies();
				
			} else if(secondaryModifier == TFModifier.CLOTHING_SEALING) {
				return Util.newArrayListOfValues(TFPotency.MINOR_BOOST, TFPotency.MINOR_DRAIN, TFPotency.DRAIN, TFPotency.MAJOR_DRAIN);
				
			} else {
				return Util.newArrayListOfValues(TFPotency.MINOR_BOOST);
			}
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			List<String> effectsList = new ArrayList<>();
			
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE
					|| primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) { //This is overridden in a couple of places, such as in InventoryTooltipEventListener
				effectsList.add(
						(potency.getClothingBonusValue()<0
								?"[style.boldBad("+potency.getClothingBonusValue()+")] "
								:"[style.boldGood(+"+potency.getClothingBonusValue()+")] ")
						+ "<b style='color:"+secondaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(secondaryModifier.getAssociatedAttribute().getName())+"</b>");
				
			} else if(secondaryModifier == TFModifier.CLOTHING_SEALING) {
				if(potency==TFPotency.MINOR_DRAIN) {
					effectsList.add("[style.boldCrimson(Seals onto wearer)] <b>(Removal cost: [style.boldArcane(25)])</b>");
					
				} else if(potency==TFPotency.DRAIN) {
					effectsList.add("[style.boldCrimson(Seals onto wearer)] <b>(Removal cost: [style.boldArcane(100)])</b>");
					
				} else if(potency==TFPotency.MAJOR_DRAIN) {
					effectsList.add("[style.boldCrimson(Seals onto wearer)] <b>(Removal cost: [style.boldArcane(500)])</b>");
					
				} else {
					effectsList.add("[style.boldCrimson(Seals onto wearer)] <b>(Removal cost: [style.boldArcane(5)])</b>");
				}
				
			} else if(secondaryModifier == TFModifier.CLOTHING_ENSLAVEMENT) {
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
				
			} else if(primaryModifier == TFModifier.CLOTHING_CONDOM) {
				if(potency==TFPotency.MAJOR_BOOST) {
					effectsList.add("[style.boldExcellent(Infinite)] safe cum capacity.");
					
				} else if(potency==TFPotency.BOOST) {
					effectsList.add("[style.boldGood("+CumProduction.SIX_EXTREME.getMaximumValue()+"ml)] safe cum capacity.");
					
				} else if(potency==TFPotency.MINOR_BOOST) {
					effectsList.add("[style.boldMinorGood("+CumProduction.FIVE_HUGE.getMaximumValue()+"ml)] safe cum capacity.");
					
				} else if(potency==TFPotency.MAJOR_DRAIN
						|| potency==TFPotency.DRAIN
						|| potency==TFPotency.MINOR_DRAIN) {
					effectsList.add("[style.boldTerrible(Sabotaged)] to always break!");
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
					|| primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE
					|| secondaryModifier == TFModifier.CLOTHING_ENSLAVEMENT
					|| secondaryModifier == TFModifier.CLOTHING_SEALING
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART
					|| primaryModifier == TFModifier.CLOTHING_CONDOM) {
				return "";
			}
			return applyClothingTF(primaryModifier, secondaryModifier, potency, limit, user, target, timer);
		}
	};
	
	public static AbstractItemEffectType TATTOO = new AbstractItemEffectType(null,
			Colour.RARITY_RARE) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTattooPrimaryList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE) {
				return TFModifier.getClothingAttributeList();
				
			}else if(primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
				return TFModifier.getClothingMajorAttributeList();
				
			}  else if(primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR) {
				return TFModifier.getTFBehaviouralFetishList();
				
			} else if(primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART) {
				return TFModifier.getTFBodyPartFetishList();
				
			} else {
				return getClothingTFSecondaryModifiers(primaryModifier);
			}
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE
					|| primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART
					|| !getClothingTFSecondaryModifiers(primaryModifier).isEmpty()) {
				return TFPotency.getAllPotencies();
				
			} else {
				return Util.newArrayListOfValues(TFPotency.MINOR_BOOST);
			}
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			List<String> effectsList = new ArrayList<>();
			
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE
					|| primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) { //This is overridden in a couple of places, such as in InventoryTooltipEventListener
				effectsList.add(
						(potency.getClothingBonusValue()<0
								?"[style.boldBad("+potency.getClothingBonusValue()+")] "
								:"[style.boldGood(+"+potency.getClothingBonusValue()+")] ")
						+ "<b style='color:"+secondaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(secondaryModifier.getAssociatedAttribute().getName())+"</b>");
				
			} else if(primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART) {
				if(potency==TFPotency.MAJOR_BOOST) {
					effectsList.add("[style.boldExcellent(Grants)] [style.boldFetish("+secondaryModifier.getName()+" fetish)]");
					
				} else if(potency==TFPotency.BOOST) {
					effectsList.add("[style.boldGood(Increases)] [style.boldLust(desire)] for [style.boldFetish("+secondaryModifier.getName()+" fetish)]");
					
				} else if(potency==TFPotency.MINOR_BOOST) {
					effectsList.add("[style.boldMinorGood(Slightly increases)] [style.boldLust(desire)] for [style.boldFetish("+secondaryModifier.getName()+" fetish)]");
					
				} else if(potency==TFPotency.MAJOR_DRAIN) {
					effectsList.add("<b style='color:"+FetishDesire.ZERO_HATE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(FetishDesire.ZERO_HATE.getNameAsVerb())+"</b> [style.boldFetish("+secondaryModifier.getName()+" fetish)]");
					
				} else if(potency==TFPotency.DRAIN) {
					effectsList.add("[style.boldBad(Decreases)] [style.boldLust(desire)] for [style.boldFetish("+secondaryModifier.getName()+" fetish)]");
					
				} else if(potency==TFPotency.MINOR_DRAIN) {
					effectsList.add("[style.boldMinorBad(Slightly decreases)] [style.boldLust(desire)] for [style.boldFetish("+secondaryModifier.getName()+" fetish)]");
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
					|| primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART) {
				return "";
			}
			return applyClothingTF(primaryModifier, secondaryModifier, potency, limit, user, target, timer);
		}
	};
	

	public static AbstractItemEffectType WEAPON = new AbstractItemEffectType(null,
			Colour.RARITY_RARE) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getWeaponPrimaryList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(TFModifier primaryModifier) {
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE) {
				return TFModifier.getWeaponAttributeList();
			} else {
				return TFModifier.getWeaponMajorAttributeList();
			}
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return TFPotency.getAllPotencies();
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			List<String> effectsList = new ArrayList<>();
			
			effectsList.add(
					(potency.getClothingBonusValue()<0
							?"[style.boldBad("+potency.getClothingBonusValue()+")] "
							:"[style.boldGood(+"+potency.getClothingBonusValue()+")] ")
					+ "<b style='color:"+secondaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(secondaryModifier.getAssociatedAttribute().getName())+"</b>");
				
			return effectsList;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "";
		}
	};
	
	public static AbstractItemEffectType OFFSPRING_MAP = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Facilitates the discovery of offspring."),
			Colour.RARITY_LEGENDARY) {
		@Override
		public boolean isBreakOutOfInventory() {
			return true;
		}
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			Main.game.setContent(new Response("", "", OffspringMapDialogue.OFFSPRING_CHOICE));
			return "";
		}
	};
	
	
	public static Map<AbstractItemEffectType, String> itemEffectTypeToIdMap = new HashMap<>();
	public static Map<String, AbstractItemEffectType> idToItemEffectTypeMap = new HashMap<>();
	public static List<AbstractItemEffectType> allEffectTypes = new ArrayList<>();
	
	public static void addAbstractItemEffectToIds(String id, AbstractItemEffectType itemEffectType) {
		allEffectTypes.add(itemEffectType);
		
		itemEffectTypeToIdMap.put(itemEffectType, id);
		idToItemEffectTypeMap.put(id, itemEffectType);
	}
	
	public static AbstractItemEffectType getItemEffectTypeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToItemEffectTypeMap.keySet());
		return idToItemEffectTypeMap.get(id);
	}
	
	public static String getIdFromItemEffectType(AbstractItemEffectType itemEffectType) {
		return itemEffectTypeToIdMap.get(itemEffectType);
	}
	
	// set in ItemType
	public static AbstractItemEffectType getBookEffectFromSubspecies(Subspecies subspecies) {
		String id = Util.getClosestStringMatch("BOOK_READ_"+subspecies.toString(), idToItemEffectTypeMap.keySet());
		return idToItemEffectTypeMap.get(id);
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
					
					itemEffectTypeToIdMap.put(iet, f.getName());
					idToItemEffectTypeMap.put(f.getName(), iet);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
