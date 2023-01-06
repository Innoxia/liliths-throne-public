package com.lilithsthrone.game.inventory.enchanting;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.Litter;
import com.lilithsthrone.game.character.PregnancyPossibility;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TorsoType;
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
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.misc.GenericAndrogynousNPC;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.MiscDialogue;
import com.lilithsthrone.game.dialogue.utils.OffspringMapDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.7
 * @version 0.3.9
 * @author Innoxia
 */
public class ItemEffectType {
	
	public static AbstractItemEffectType TESTING = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Test item."),
		PresetColour.GENERIC_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return target.incrementMuscle(-25)
					+ target.incrementBodySize(25)
					+ target.setUnderarmHair(BodyHair.SIX_BUSHY);
		}
	};
	
	public static AbstractItemEffectType DYE_BRUSH = new AbstractItemEffectType(Util.newArrayListOfValues(
				"Recolours a piece of clothing."),
			PresetColour.GENERIC_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p>"
					+ "You use the dye brush."
					+ "</p>";
		}
	};
	
	public static AbstractItemEffectType REFORGE_HAMMER = new AbstractItemEffectType(Util.newArrayListOfValues(
				"Changes a weapon's damage type."),
			PresetColour.GENERIC_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p>"
					+ "You use the reforge hammer."
					+ "</p>";
		}
	};
	
	public static AbstractItemEffectType USED_CONDOM_DRINK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Provides a slimy snack."),
			PresetColour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return ""; // THIS EFFECT IS NOT USED, AS AbstractFilledCondom OVERRIDES THE USUAL AbstractItem's applyEffects() METHOD!!!
		}
	};

	public static AbstractItemEffectType FILLED_MOO_MILKER_DRINK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Provides a milky drink."),
			PresetColour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return ""; // THIS EFFECT IS NOT USED, AS AbstractFilledBreastPump OVERRIDES THE USUAL AbstractItem's applyEffects() METHOD!!!
			// Then why is it here? :thinking:
		}
	};
	
	
	public static AbstractItemEffectType ORIENTATION_CHANGE = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Sets orientation to gynephilic.",
			Attribute.MAJOR_CORRUPTION.getFormattedValue(50)),
			PresetColour.FEMININE_PLUS) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return Util.newArrayListOfValues(
					TFModifier.REMOVAL,
					TFModifier.ORIENTATION_GYNEPHILIC,
					TFModifier.ORIENTATION_AMBIPHILIC,
					TFModifier.ORIENTATION_ANDROPHILIC,
					TFModifier.PERSONALITY_TRAIT_SPEECH_LISP,
					TFModifier.PERSONALITY_TRAIT_SPEECH_STUTTER,
					TFModifier.PERSONALITY_TRAIT_SPEECH_SLOVENLY);
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
			return Util.newArrayListOfValues(
					TFModifier.ARCANE_BOOST);
		}

		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_LISP
					|| primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_STUTTER
					|| primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_SLOVENLY) {
				return Util.newArrayListOfValues(
						TFPotency.MINOR_DRAIN,
						TFPotency.MINOR_BOOST);
			}
			return Util.newArrayListOfValues(TFPotency.MINOR_BOOST);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			descriptions.clear();
			
			if(primaryModifier!=null && primaryModifier!=TFModifier.NONE) {
				if(primaryModifier==TFModifier.REMOVAL) {
					descriptions.add("No effect.");
					
				} else if(primaryModifier==TFModifier.ORIENTATION_GYNEPHILIC) {
					descriptions.add("Sets orientation to [style.boldFeminineStrong(gynephilic)]");
					descriptions.add(Attribute.MAJOR_CORRUPTION.getFormattedValue(5));
					
				} else if(primaryModifier==TFModifier.ORIENTATION_AMBIPHILIC) {
					descriptions.add("Sets orientation to [style.boldAndrogynous(ambiphilic)]");
					descriptions.add(Attribute.MAJOR_CORRUPTION.getFormattedValue(5));
					
				} else if(primaryModifier==TFModifier.ORIENTATION_ANDROPHILIC) {
					descriptions.add("Sets orientation to [style.boldMasculineStrong(androphilic)]");
					descriptions.add(Attribute.MAJOR_CORRUPTION.getFormattedValue(5));
					
				} else if(primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_LISP) {
					if(potency==TFPotency.MINOR_DRAIN) {
						descriptions.add("Removes <b style='color:"+TFModifier.PERSONALITY_TRAIT_SPEECH_LISP.getColour().toWebHexString()+";'>lisp</b>");
					} else {
						descriptions.add("Adds <b style='color:"+TFModifier.PERSONALITY_TRAIT_SPEECH_LISP.getColour().toWebHexString()+";'>lisp</b>");
					}
					
				} else if(primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_STUTTER) {
					if(potency==TFPotency.MINOR_DRAIN) {
						descriptions.add("Removes <b style='color:"+TFModifier.PERSONALITY_TRAIT_SPEECH_STUTTER.getColour().toWebHexString()+";'>stutter</b>");
					} else {
						descriptions.add("Adds <b style='color:"+TFModifier.PERSONALITY_TRAIT_SPEECH_STUTTER.getColour().toWebHexString()+";'>stutter</b>");
					}
					
				} else if(primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_SLOVENLY) {
					if(potency==TFPotency.MINOR_DRAIN) {
						descriptions.add("Removes <b style='color:"+TFModifier.PERSONALITY_TRAIT_SPEECH_SLOVENLY.getColour().toWebHexString()+";'>slovenly speech</b>");
					} else {
						descriptions.add("Adds <b style='color:"+TFModifier.PERSONALITY_TRAIT_SPEECH_SLOVENLY.getColour().toWebHexString()+";'>slovenly speech</b>");
					}
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
					return UtilText.parse(target,
							"<p style='text-align:center;'>"
									+ (alreadyGynephilic
											?"[style.colourDisabled([npc.NameIsFull] already gynephilic, so nothing happens...)]"
											:"[npc.NameIsFull] now [style.colourFemininePlus(gynephilic)]!")
								+ "</p>");
					
				} else if(primaryModifier==TFModifier.ORIENTATION_AMBIPHILIC) {
					boolean alreadyAmbiphilic = target.getSexualOrientation()==SexualOrientation.AMBIPHILIC;
					target.setSexualOrientation(SexualOrientation.AMBIPHILIC);
					return UtilText.parse(target,
							"<p style='text-align:center;'>"
									+ (alreadyAmbiphilic
											?"[style.colourDisabled([npc.NameIsFull] already ambiphilic, so nothing happens...)]"
											:"[npc.NameIsFull] now [style.colourAndrogynous(ambiphilic)]!")
								+ "</p>");
					
					
				} else if(primaryModifier==TFModifier.ORIENTATION_ANDROPHILIC) {
					boolean alreadyAndrophilic = target.getSexualOrientation()==SexualOrientation.ANDROPHILIC;
					target.setSexualOrientation(SexualOrientation.ANDROPHILIC);
					return UtilText.parse(target,
							"<p style='text-align:center;'>"
									+ (alreadyAndrophilic
											?"[style.colourDisabled([npc.NameIsFull] already androphilic, so nothing happens...)]"
											:"[npc.NameIsFull] now [style.colourMasculinePlus(androphilic)]!")
								+ "</p>");
					
				} else if(primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_LISP) {
					boolean alreadyLisp = target.hasPersonalityTrait(PersonalityTrait.LISP);
					if(potency==TFPotency.MINOR_DRAIN) {
						target.removePersonalityTrait(PersonalityTrait.LISP);
						return UtilText.parse(target,
								"<p style='text-align:center;'>"
										+ (!alreadyLisp
											?"[style.colourDisabled([npc.Name] already [npc.do]n't speak with a lisp, so nothing happens...)]"
											:"[npc.Name] suddenly [npc.verb(find)] [npc.herself] [style.colourMinorGood(able to speak without a lisp)]!")
									+ "</p>");
						
					} else {
						target.addPersonalityTrait(PersonalityTrait.LISP);
						return UtilText.parse(target,
								"<p style='text-align:center;'>"
										+ (alreadyLisp
											?"[style.colourDisabled([npc.Name] already [npc.verb(speak)] with a lisp, so nothing happens...)]"
											:"[npc.Name] suddenly [npc.verb(find)] [npc.herself] [style.colourMinorBad(speaking with a lisp)]!")
									+ "</p>");
					}
					
				} else if(primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_STUTTER) {
					boolean alreadyStutter = target.hasPersonalityTrait(PersonalityTrait.STUTTER);
					if(potency==TFPotency.MINOR_DRAIN) {
						target.removePersonalityTrait(PersonalityTrait.STUTTER);
						return UtilText.parse(target,
								"<p style='text-align:center;'>"
										+ (!alreadyStutter
												?"[style.colourDisabled([npc.Name] already [npc.do]n't with a stutter, so nothing happens...)]"
												:"[npc.Name] suddenly [npc.verb(find)] [npc.herself] [style.colourMinorGood(able to speak without stuttering)]!")
									+ "</p>");
						
					} else {
						target.addPersonalityTrait(PersonalityTrait.STUTTER);
						return UtilText.parse(target,
								"<p style='text-align:center;'>"
										+ (alreadyStutter
												?"[style.colourDisabled([npc.Name] already [npc.verb(speak)] with a stutter, so nothing happens...)]"
												:"[npc.Name] suddenly [npc.verb(find)] [npc.herself] [style.colourMinorBad(speaking with a stutter)]!")
									+ "</p>");
					}
					
				} else if(primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_SLOVENLY) {
					boolean alreadySlovenly = target.hasPersonalityTrait(PersonalityTrait.SLOVENLY);
					if(potency==TFPotency.MINOR_DRAIN) {
						target.removePersonalityTrait(PersonalityTrait.SLOVENLY);
						return UtilText.parse(target,
								"<p style='text-align:center;'>"
										+ (!alreadySlovenly
												?"[style.colourDisabled([npc.Name] already [npc.do]n't speak in a slovenly manner, so nothing happens...)]"
												:"[npc.Name] suddenly [npc.verb(find)] [npc.herself] [style.colourMinorGood(no longer speaking in a slovenly manner)]!")
									+ "</p>");
						
					} else {
						target.addPersonalityTrait(PersonalityTrait.SLOVENLY);
						return UtilText.parse(target,
								"<p style='text-align:center;'>"
										+ (alreadySlovenly
												?"[style.colourDisabled([npc.Name] already [npc.verb(speak)] in a slovenly manner, so nothing happens...)]"
												:"[npc.Name] suddenly [npc.verb(find)] [npc.herself] [style.colourMinorBad(speaking in a slovenly manner)]!")
									+ "</p>");
					}
				}
				
			} 
			
			return "<p>"
						+ "Nothing happens, as the Hypno-Watch has had its enchantment removed."
						+ " You'll need to enchant it if you want to put it to use."
					+ "</p>";
		}
	};
	
//	public static AbstractItemEffectType VIXENS_VIRILITY = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.FERTILITY.getFormattedValue(50)+" for 24 hours",
//			Attribute.VIRILITY.getFormattedValue(50)+" for 24 hours",
//			"[style.boldBad(Removes status effect:)]",
//			"<i>'"+StatusEffect.PROMISCUITY_PILL.getName(null)+"'</i>"),
//			PresetColour.GENERIC_SEX) {
//		
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			target.removeStatusEffect(StatusEffect.PROMISCUITY_PILL);
//			target.addStatusEffect(StatusEffect.VIXENS_VIRILITY, 60*24*60);
//			return UtilText.parse(target,
//					"<p style='margin-bottom:0; padding-bottom:0;'>"
//						+ "The little purple pill easily slides down [npc.her] throat, and within moments [npc.she] [npc.verb(feel)] "
//						+ ( target.hasVagina()
//								? "a soothing, warm glow spreading out from [npc.her] ovaries into [npc.her] lower torso."
//									+ " [npc.Her] mind fogs over with an overwhelming desire to feel potent sperm spurting deep into [npc.her] "+(target.isVisiblyPregnant()?"pussy":"womb")
//									+", and before [npc.she] can stop it, a horny whimper escapes from between [npc.her] [npc.lips]."
//									+ (target.hasPenisIgnoreDildo()
//											?" At the same time, [npc.her] manhood begins to throb with need, and [npc.she] [npc.verb(feel)] "
//											:"") 
//								:"")
//						+ (target.hasPenisIgnoreDildo()
//								? "an overpowering desire to sink deep into a fertile female's cunt and fill her with [npc.cum+]."
//								: "")
//						+ (!target.hasPenisIgnoreDildo() && !target.hasVagina()
//								?"a desperate heat in [npc.her] genderless mound."
//								:"")
//					+"</p>"
//					+ "<p style='text-align:center; margin-top:0; padding-top:0;'>"
//						+ "[style.colourPink([npc.Name] [npc.is] now experiencing <i>'"+StatusEffect.VIXENS_VIRILITY.getName(target)+"'</i> for the next 24 hours!)]"
//					+ "</p>");
//		}
//	};
//	
//	public static AbstractItemEffectType PROMISCUITY_PILL = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.FERTILITY.getFormattedValue(-100)+" for 24 hours",
//			Attribute.VIRILITY.getFormattedValue(-100)+" for 24 hours",
//			"[style.boldBad(Removes status effect:)]",
//			"<i>'"+StatusEffect.VIXENS_VIRILITY.getName(null)+"'</i>"),
//			PresetColour.GENERIC_SEX) {
//		
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			target.removeStatusEffect(StatusEffect.VIXENS_VIRILITY);
//			target.addStatusEffect(StatusEffect.PROMISCUITY_PILL, 60*24*60);
//			return UtilText.parse(target,
//					"<p>"
//						+ "The little blue pill easily slides down [npc.namePos] throat, and after only a few moments [npc.she] [npc.verb(feel)] a cool throbbing sensation taking root deep within [npc.her] loins."
//					+ "</p>"
//					+ "<p style='text-align:center; margin-top:0; padding-top:0;'>"
//						+ "[style.colourBlueLight([npc.Name] [npc.is] now experiencing <i>'"+StatusEffect.PROMISCUITY_PILL.getName(target)+"'</i> for the next 24 hours!)]"
//					+ "</p>");
//		}
//	};
	

	public static AbstractItemEffectType MOO_MILKER = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Milks breasts."),
			PresetColour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			int milkPumped = (int) Math.min(target.getBreastRawStoredMilkValue(), ItemType.getMooMilkerMaxMilk());
			target.incrementBreastStoredMilk(-milkPumped);
			if(target.isPlayer()) {
				return "<p>"
							+ "It only takes a moment before the beaker is filled with "+ Units.fluid(milkPumped, Units.UnitType.LONG)+" of your [pc.milk]."
						+ "</p>"
						+ user.addItem(Main.game.getItemGen().generateFilledBreastPump(ItemType.MOO_MILKER_EMPTY.getColourShades().get(0), target, target.getMilk(), milkPumped), false, true);
			
			} else {
				return UtilText.parse(target,
						"<p>"
							+ "It only takes a moment before the beaker is filled with "+Units.fluid(milkPumped, Units.UnitType.LONG)+" of [npc.her] [npc.milk]."
						+ "</p>"
						+ user.addItem(Main.game.getItemGen().generateFilledBreastPump(ItemType.MOO_MILKER_EMPTY.getColourShades().get(0), target, target.getMilk(), milkPumped), false, true));
			}
		}
	};
	

	public static AbstractItemEffectType PREGNANCY_TEST = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Reveals pregnancy info."),
			PresetColour.GENERIC_SEX) {
		
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
				String unknownFatherName = "Unknown!";
				if(father==null) {
					try {
						OffspringSeed offspring0 = target.getPregnantLitter().getOffspringSeed().iterator().next();
						if(!offspring0.getFatherName().equals("???")) {
							unknownFatherName = offspring0.getFatherName();
						}
					} catch(Exception ex) {
					}
				}
				
				return "<p style='text-align:center;'>"
						+ "The digital readout lights up with two parallel red lines, with flashing pink text next to that displaying:"
						+ "<br/><b>'[style.italicsArcane(Pregnant!)]'</b>"
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "Underneath the flashing pregnancy confirmation, there's some extra information, which reads:<br/>"
						+ "<i>"
						+ "Father: "+(father!=null
										?father.getNameIgnoresPlayerKnowledge()+" ("+Util.capitaliseSentence(target.getPregnantLitter().getFatherRace().getName(father.getBody()))+")"
										:unknownFatherName+" ("+Util.capitaliseSentence(target.getPregnantLitter().getFatherRace().getName(Main.game.getNpc(GenericAndrogynousNPC.class).getBody()))+")")+"<br/>"
						+ "Litter size: " +target.getPregnantLitter().getTotalLitterCount()+"<br/>"
						+ "[style.colourFeminine(Daughters)]: " +(target.getPregnantLitter().getDaughtersFromFather()+target.getPregnantLitter().getDaughtersFromMother())+"<br/>"
						+ "[style.colourMasculine(Sons)]: " +(target.getPregnantLitter().getSonsFromFather()+target.getPregnantLitter().getSonsFromMother())+"<br/>"
						+ "</i>"
					+ "</p>";
				
			} else {
				return "<p style='text-align:center;'>"
					+ "The digital readout lights up with a single red line, with solid black text next to that displaying:"
					+ "<br/><b>'<i>Not Pregnant.</i>'</b>"
				+ "</p>";
			}
		}
	};
	
	
	public static AbstractItemEffectType MOTHERS_MILK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Advances pregnancy and incubations."),
			PresetColour.GENERIC_SEX) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			StringBuilder sb = new StringBuilder();
			boolean effectsObserved = false;
			
			sb.append("<p>");
			sb.append("[npc.Name] eagerly [npc.verb(gulp)] down the rich, creamy liquid; its delicious taste spurs [npc.herHim] on into quickly draining the entire bottle.");

			Map<SexAreaOrifice, List<AbstractStatusEffect>> incubationEffectMap = Util.newHashMapOfValues(
					new Value<>(SexAreaOrifice.VAGINA, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_WOMB_1, StatusEffect.INCUBATING_EGGS_WOMB_2, StatusEffect.INCUBATING_EGGS_WOMB_3)),
					new Value<>(SexAreaOrifice.ANUS, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_STOMACH_1, StatusEffect.INCUBATING_EGGS_STOMACH_2, StatusEffect.INCUBATING_EGGS_STOMACH_3)),
					new Value<>(SexAreaOrifice.NIPPLE, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_NIPPLES_1, StatusEffect.INCUBATING_EGGS_NIPPLES_2, StatusEffect.INCUBATING_EGGS_NIPPLES_3)),
					new Value<>(SexAreaOrifice.NIPPLE_CROTCH, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_1, StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_2, StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_3)),
					new Value<>(SexAreaOrifice.SPINNERET, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_SPINNERET_1, StatusEffect.INCUBATING_EGGS_SPINNERET_2, StatusEffect.INCUBATING_EGGS_SPINNERET_3)));
			
			for(Entry<SexAreaOrifice, List<AbstractStatusEffect>> entry : incubationEffectMap.entrySet()) {
				Litter litter = target.getIncubationLitter(entry.getKey());
				if(litter!=null) {
					boolean advanced = false;
					if(target.hasStatusEffect(entry.getValue().get(0))) {
						target.removeStatusEffect(entry.getValue().get(0));
						advanced = true;
					} else if(target.hasStatusEffect(entry.getValue().get(1))){
						target.removeStatusEffect(entry.getValue().get(1));
						advanced = true;
					}
					if(advanced) {
						effectsObserved = true;
						int eggs = litter.getTotalLitterCount();
						String areaName = entry.getKey().getName(target, true);
						if(entry.getKey()==SexAreaOrifice.VAGINA) {
							areaName = "womb";
						} else if(entry.getKey()==SexAreaOrifice.ANUS) {
							areaName = "stomach";
						}
						sb.append("</br>");
						if(eggs>1) {
							sb.append("The eggs being incubated in [npc.namePos] <b>"+areaName+"</b> rapidly mature and get noticeably heavier, letting [npc.herHim] know that they're ");
						} else {
							sb.append("The egg being incubated in [npc.namePos] <b>"+areaName+"</b> rapidly matures and gets noticeably heavier, letting [npc.herHim] know that it's ");
						}
						if(target.hasStatusEffect(entry.getValue().get(1))) {
							sb.append("[style.boldYellowLight(advanced to the next stage of incubation)]!");
						} else {
							sb.append("now [style.boldYellowLight(ready to be laid)]!");
						}
					}
				}
			}
			
			if(target.isVisiblyPregnant()) {
				if(!target.hasStatusEffect(StatusEffect.PREGNANT_3)) {
					effectsObserved = true;
					if(target.hasStatusEffect(StatusEffect.PREGNANT_1)) {
						target.removeStatusEffect(StatusEffect.PREGNANT_1);
						
					} else if(target.hasStatusEffect(StatusEffect.PREGNANT_2)) {
						target.removeStatusEffect(StatusEffect.PREGNANT_2);
					}
					sb.append("</br>");
					sb.append("Immediately, [npc.her] belly rapidly swells and grows in size, and [npc.she] can't help but let out a deep [npc.moan] as a rush of energy flows up throughout [npc.her] body."
								+ " After just a moment, the effects come a halt, and [npc.name] [npc.verb(smile)] happily to [npc.herself] as"
									+ " [npc.she] [npc.verb(reflect)] on the fact that the expansion of [npc.her] pregnant bump has taken [npc.herHim] into the next stage of [npc.her] pregnancy...");
				}
				
			} else if(target.hasStatusEffect(StatusEffect.PREGNANT_0)) {
				effectsObserved = true;
				target.removeStatusEffect(StatusEffect.PREGNANT_0);
				
				if(target.isPregnant()) {
					sb.append("</br>");
					sb.append("A soothing warmth quickly spreads throughout [npc.her] lower abdomen, and as [npc.she] [npc.verb(let)] out an involuntary gasp,"
									+ " [npc.her] belly suddenly swells up into an unmistakably [style.boldMinorGood(pregnant bump)]!");
					
				} else {
					sb.append("</br>");
					sb.append("Although a soothing warmth spreads throughout [npc.her] lower abdomen, no sign of any pregnancy manifests itself in [npc.her] belly."
								+ " It looks like [npc.sheIs] [style.boldMinorBad(not pregnant)] after all...");
				}
			}
			
			if(!effectsObserved) {
				sb.append("Aside from quenching [npc.her] thirst, the liquid doesn't have any effect on [npc.name]...");
			}
			
			sb.append("</p>");
			
			return UtilText.parse(target, sb.toString());
		}
	};
	
	public static AbstractItemEffectType REJUVENATION_POTION = new AbstractItemEffectType(
			null,
			PresetColour.BASE_PURPLE) {
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			List<String> effects = new ArrayList<>();
			
			effects.add("[style.boldExcellent(Instantly recovers)] [style.boldSex(stretched orifices)]");
			
			if(Main.game.isLactationContentEnabled()) {
				effects.add("[style.boldGood(Fully refills)] [style.boldMilk(milk storage)]");
			}
			if(Main.game.isCumRegenerationEnabled()) {
				effects.add("[style.boldGood(Fully refills)] [style.boldCum(cum storage)]");
			}
			
			return effects;
		}
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			StringBuilder sb = new StringBuilder();
			List<String> areasTightened = new ArrayList<>();
			
			if (target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				areasTightened.add("pussy");
				target.setVaginaStretchedCapacity(target.getVaginaRawCapacityValue());
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				areasTightened.add("asshole");
				target.setAssStretchedCapacity(target.getAssRawCapacityValue());
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				areasTightened.add("nipples");
				target.setNippleStretchedCapacity(target.getNippleRawCapacityValue());
			}
			if (target.hasBreastsCrotch()
					&& target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				areasTightened.add("crotch-nipples");
				target.setNippleCrotchStretchedCapacity(target.getNippleCrotchRawCapacityValue());
			}
			if (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				areasTightened.add("penile urethra");
				target.setPenisStretchedCapacity(target.getPenisRawCapacityValue());
			}
			if (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				areasTightened.add("vaginal urethra");
				target.setVaginaUrethraStretchedCapacity(target.getVaginaUrethraRawCapacityValue());
			}
			
			if(!areasTightened.isEmpty()) {
				sb.append(UtilText.parse(target,
							"<br/>[npc.Her] stretched-out [style.colourSex("+Util.stringsToStringList(areasTightened, false)+")]"
							+ (areasTightened.size()>1
								?" [style.colourGood(tighten back up)]!"
								:" [style.colourGood(tightens back up)]!")));
			}
			
			if(Main.game.isLactationContentEnabled()) {
				if(target.getBreastRawMilkStorageValue()>0 && target.getBreastRawStoredMilkValue()<target.getBreastRawMilkStorageValue()) {
					target.setBreastStoredMilk(target.getBreastRawMilkStorageValue());
					sb.append(UtilText.parse(target, "<br/>[npc.NamePos] [npc.breasts+] [style.colourGood(fill up)] with [npc.milk]!"));
				}
				if(target.hasBreastsCrotch() && target.getBreastCrotchRawMilkStorageValue()>0 && target.getBreastCrotchRawStoredMilkValue()<target.getBreastCrotchRawMilkStorageValue()) {
					target.setBreastCrotchStoredMilk(target.getBreastCrotchRawMilkStorageValue());
					sb.append(UtilText.parse(target, "<br/>[npc.NamePos] [npc.crotchBoobs+] [style.colourGood(fill up)] with [npc.crotchMilk]!"));
				}
			}

			if(Main.game.isCumRegenerationEnabled()) {
				if(target.hasPenis() && target.getPenisRawCumStorageValue()>0 && target.getPenisRawStoredCumValue()<target.getPenisRawCumStorageValue()) {
					target.setPenisStoredCum(target.getPenisRawCumStorageValue());
					sb.append(UtilText.parse(target, "<br/>[npc.NamePos] [npc.balls+] [style.colourGood(fill up)] with [npc.cum]!"));
				}
			}
			
			if(sb.length()==0) {
				sb.append(UtilText.parse(target,
							"<br/>Apart from experiencing this comforting feeling, nothing else happens to [npc.herHim]..."));
			}
			
			return "<p style='text-align:center;'>"
						+"[npc.Name] [npc.verb(let)] out a deep sigh as a cool, soothing sensation starts to wash over [npc.herHim]."
						+ "<i>"
							+ sb.toString()
						+"</i>"
					+ "</p>";
		}
	};
	
	public static AbstractItemEffectType CIGARETTE_PACK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Provides 20 Starr Cigarettes."),
			PresetColour.BASE_PURPLE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return target.addItem(Main.game.getItemGen().generateItem(ItemType.CIGARETTE), 20, false, target.isPlayer());
		}
	};
	
	public static AbstractItemEffectType CIGARETTE = new AbstractItemEffectType(null,
			PresetColour.BASE_PURPLE) {
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			List<String> list = super.getEffectsDescription(primaryModifier, secondaryModifier, potency, limit, user, target);
			AbstractStatusEffect se = StatusEffect.RECENTLY_SMOKED;
			list.add("Applies <i style='color:"+se.getColour().toWebHexString()+";'>'"+Util.capitaliseSentence(se.getName(target))+"'</i>:");
			for(Entry<AbstractAttribute, Float> entry : se.getAttributeModifiers(target).entrySet()) {
				list.add("<i>"+entry.getKey().getFormattedValue(entry.getValue())+"</i>");
			}
			return list;
		}
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.removeStatusEffect(StatusEffect.RECENTLY_SMOKED);
			target.addStatusEffect(StatusEffect.SMOKING, 60*5);
			return UtilText.parse(target,
					"<p>"
						+ "[npc.Name] immediately [npc.verb(feel)] the positive effects of the aura-boosting supplements added to the Starr Cigarette."
						+ " As the smoke fills [npc.her] lungs, however, [npc.she] also [npc.verb(feel)] slightly less healthy..."
					+ "</p>");
		}
	};

	public static AbstractItemEffectType MAKEUP_SET = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldPink(Opens cosmetics screen)]",
			"[style.boldSex(Automatically re-applies heavy lipstick after sex)]"),
			PresetColour.BASE_PURPLE) {
		@Override
		public boolean isBreakOutOfInventory() {
			return true;
		}
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			BodyChanging.setTarget(target);
			Main.game.setContent(new Response(
					"",
					"",
					MiscDialogue.getMakeupDialogue(false,
							(BodyChanging.getTarget().isPlayer()
								?"You open the arcane makeup set and prepare to get started..."
								:"You open the arcane makeup set and prepare to get started on applying makeup to [npc.name]..."))));
			return "";
		}
	};
	
	// Ingredients and potions:
	
	public static AbstractItemEffectType MYSTERY_KINK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldFetish(Random fetish addition or removal)]"),
			PresetColour.FETISH) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			List<AbstractFetish> fetishesToAdd = new ArrayList<>();
			List<AbstractFetish> fetishesToRemove = new ArrayList<>();
			for(AbstractFetish f : Fetish.getAllFetishes()) {
				if(f.getFetishesForAutomaticUnlock().isEmpty()) {
					if(target.hasFetish(f)) {
						fetishesToRemove.add(f);
						
					} else if(f.isAvailable(target)) {
						fetishesToAdd.add(f);
					}
				}
			}
			
			// Remove possible fetish modifications based on user content settings:
			if(!Main.game.isAnalContentEnabled()) {
				fetishesToAdd.remove(Fetish.FETISH_ANAL_GIVING);
				fetishesToAdd.remove(Fetish.FETISH_ANAL_RECEIVING);
				fetishesToRemove.remove(Fetish.FETISH_ANAL_GIVING);
				fetishesToRemove.remove(Fetish.FETISH_ANAL_RECEIVING);
			}
			if(!Main.game.isLactationContentEnabled()) {
				fetishesToAdd.remove(Fetish.FETISH_LACTATION_OTHERS);
				fetishesToAdd.remove(Fetish.FETISH_LACTATION_SELF);
				fetishesToRemove.remove(Fetish.FETISH_LACTATION_OTHERS);
				fetishesToRemove.remove(Fetish.FETISH_LACTATION_SELF);
			}
			if(!Main.game.isFootContentEnabled()) {
				fetishesToAdd.remove(Fetish.FETISH_FOOT_GIVING);
				fetishesToAdd.remove(Fetish.FETISH_FOOT_RECEIVING);
				fetishesToRemove.remove(Fetish.FETISH_FOOT_GIVING);
				fetishesToRemove.remove(Fetish.FETISH_FOOT_RECEIVING);
			}
			if(!Main.game.isArmpitContentEnabled()) {
				fetishesToAdd.remove(Fetish.FETISH_ARMPIT_GIVING);
				fetishesToAdd.remove(Fetish.FETISH_ARMPIT_RECEIVING);
				fetishesToRemove.remove(Fetish.FETISH_ARMPIT_GIVING);
				fetishesToRemove.remove(Fetish.FETISH_ARMPIT_RECEIVING);
			}
			if(!Main.game.isIncestEnabled()) {
				fetishesToAdd.remove(Fetish.FETISH_INCEST);
				fetishesToRemove.remove(Fetish.FETISH_INCEST);
			}
			if(!Main.game.isNonConEnabled()) {
				fetishesToAdd.remove(Fetish.FETISH_NON_CON_DOM);
				fetishesToRemove.remove(Fetish.FETISH_NON_CON_DOM);
				fetishesToAdd.remove(Fetish.FETISH_NON_CON_SUB);
				fetishesToRemove.remove(Fetish.FETISH_NON_CON_SUB);
			}

			if((Math.random()>0.33f && !fetishesToAdd.isEmpty()) || fetishesToRemove.isEmpty()) {
				AbstractFetish f = fetishesToAdd.get(Util.random.nextInt(fetishesToAdd.size()));
				target.addFetish(f);
				
				return "<p style='text-align:center;'>"
						+(target.isPlayer()
						?"A staggering wave of arcane energy crashes over you, the sheer strength of which almost causes you to black out."
								+ " As you stagger back from the brink of unconsciousness, you realise that you've [style.boldGood(gained)] the [style.boldFetish("+f.getName(target)+" fetish)]!"
						:UtilText.parse(target, "A staggering wave of arcane energy crashes over [npc.name], the sheer strength of which almost causes [npc.herHim] to black out."
								+ " As [npc.she] staggers back from the brink of unconsciousness, [npc.she] discovers that [npc.sheIs] [style.boldGood(gained)] the [style.boldFetish("+f.getName(target)+" fetish)]!"))
						+"</p>";
				
			} else {
				AbstractFetish f = fetishesToRemove.get(Util.random.nextInt(fetishesToRemove.size()));
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
			"[style.boldMinorGood(Removes)] [style.boldExcellent(all)] [style.colourBad(addictions)]",
			"[style.boldMinorGood(Removes)] [style.colourAlcohol(alcohol intoxication)]",
			"[style.boldMinorGood(Removes)] [style.colourPsychoactive(psychoactive effects)]"),
			PresetColour.BASE_GOLD) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			boolean hadAddictions = !target.getAddictions().isEmpty();
			target.clearAddictions();
			
			boolean drunk = target.getIntoxicationPercentage()>0;
			target.setAlcoholLevel(0);
			
			boolean psychoactive = target.removeStatusEffect(StatusEffect.PSYCHOACTIVE);
			
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p style='text-align:center;'>");
				sb.append("[npc.Name] [npc.verb(feel)] a blissful sense of inner-peace wash over [npc.herHim]...");
				if(hadAddictions) {
					sb.append("<br/><i>[npc.SheIsFull] no longer addicted to any substances!</i>");
				}
				if(drunk) {
					sb.append("<br/><i>The alcohol still in [npc.her] system instantly metabolises!</i>");
				}
				if(psychoactive) {
					sb.append("<br/><i>The psychoactive trip which [npc.she] [npc.was] experiencing suddenly comes to an end!</i>");
				}
				if(!hadAddictions && !drunk && !psychoactive) {
					sb.append("<br/>[style.italicsDisabled(Other than experiencing this pleasant feeling, nothing happens...)]");
				}
			sb.append("</p>");
			
			return UtilText.parse(target, sb.toString());
		}
	};
	
	public static AbstractItemEffectType MUSHROOMS = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldTfGeneric(Makes slime and orifice interiors glow)]",
			"Causes a [style.boldPsychoactive(psychoactive trip)]"),
			PresetColour.ATTRIBUTE_CORRUPTION) {
		
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
					
					for(AbstractBodyCoveringType bct : BodyCoveringType.getAllSlimeTypes()) {
						target.getCovering(bct).setPrimaryGlowing(true);
						target.getCovering(bct).setSecondaryGlowing(true);
					}
					
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
			PresetColour.ATTRIBUTE_CORRUPTION) {
		
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
			PresetColour.BASE_PURPLE) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return Util.newArrayListOfValues(TFModifier.TF_PENIS);
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
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
			PresetColour.BASE_BLUE_LIGHT) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return Util.newArrayListOfValues(
					TFModifier.CORRUPTION,
					TFModifier.TF_MOD_HYMEN);
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
			return Util.newArrayListOfValues(TFModifier.ARCANE_BOOST);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(primaryModifier==TFModifier.CORRUPTION) {
				return Util.newArrayListOfValues(
						TFPotency.MINOR_BOOST,
						TFPotency.BOOST,
						TFPotency.MAJOR_BOOST);
			} else {
				return Util.newArrayListOfValues(
						TFPotency.MINOR_DRAIN,
						TFPotency.MINOR_BOOST);
			}
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			if(primaryModifier==TFModifier.CORRUPTION) {
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
				
			} else {
				if(potency.isNegative()) {
					return Util.newArrayListOfValues("[style.boldMinorBad(Removes)] [style.boldSex(hymen)]");
				} else {
					return Util.newArrayListOfValues("[style.boldMinorGood(Regenerates)] [style.boldSex(hymen)]");
				}
			}
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			if(primaryModifier==TFModifier.CORRUPTION) {
				String purifyingDescription =
						"<p style='text-align:center;'>"
								+ "For a moment, it looks as though nothing is going to happen,"
								+ " but as [npc.name] [npc.verb(swallow)] down the last couple of drops remaining in [npc.her] mouth, a sudden, cascading wave of purifying energy rushes through [npc.herHim]."
							+ " Accompanied by a faint, light-blue flash which seems to radiate from every visible part of [npc.her] body, this energy rises up into [npc.her] head,"
								+ " where it quickly gets to work purifying [npc.her] thoughts and calming [npc.her] libido..."
						+"</p>";
				switch(potency) {
					case MINOR_BOOST:
						return purifyingDescription
								+ target.incrementAttribute(Attribute.MAJOR_CORRUPTION, -5);
					case BOOST:
						return purifyingDescription
							+ target.incrementAttribute(Attribute.MAJOR_CORRUPTION, -10);
					case MAJOR_BOOST:
						return purifyingDescription
							+ target.incrementAttribute(Attribute.MAJOR_CORRUPTION, -15);
					case MINOR_DRAIN:
					case DRAIN:
					case MAJOR_DRAIN:
						break;
				}
				return "";
				
			} else {
				if(potency.isNegative()) {
					return target.setHymen(false);
				} else {
					return target.setHymen(true);
				}
			}
		}
	};
	
	public static AbstractItemEffectType GIFT_CHOCOLATES = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(Restores)] 30% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]"),
			PresetColour.ATTRIBUTE_HEALTH) {
		
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
			Attribute.DAMAGE_LUST.getFormattedValue(5)+" to 'potion effects'"),
			PresetColour.ATTRIBUTE_LUST) {
		
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
			PresetColour.GENERIC_EXCELLENT) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			List<AbstractItemType> items = new ArrayList<>();
			items.add(ItemType.getItemTypeFromId("innoxia_race_reindeer_rudolphs_egg_nog"));
			items.add(ItemType.getItemTypeFromId("innoxia_race_none_mince_pie"));
			items.add(ItemType.getItemTypeFromId("innoxia_race_reindeer_sugar_cookie"));
			
			Map<AbstractClothingType, Integer> clothingMap = new HashMap<>();
			// Common clothing (55%):
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_head_antler_headband"), 11);
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_elemental_snowflake_necklace"), 11);
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_elemental_piercing_ear_snowflakes"), 11);
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_elemental_piercing_nose_snowflake"), 11);
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_torsoOver_christmas_jumper"), 11);
			
			// Uncommon clothing (44%):
			clothingMap.put(ClothingType.JOLNIR_BOOTS, 4);
			clothingMap.put(ClothingType.JOLNIR_BOOTS_FEMININE, 4);
			clothingMap.put(ClothingType.JOLNIR_COAT, 4);
			clothingMap.put(ClothingType.JOLNIR_DRESS, 4);
			clothingMap.put(ClothingType.JOLNIR_HAT, 4);
			
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_japanese_kimono"), 4);
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_japanese_geta"), 4);
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_japanese_kanzashi"), 4);

			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_japanese_mens_kimono"), 4);
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_japanese_mens_geta"), 4);
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_japanese_haori"), 4);
			
			// 50% chance for consumable, 50% for clothing:
			if(Math.random()<0.5f) {
				AbstractItemType itemType = items.get(Util.random.nextInt(items.size()));
				
				return "<p>"
							+ "The present contained: <b>"+itemType.getDisplayName(true)+"</b>!"
						+ "</p>"
						+ user.addItem(Main.game.getItemGen().generateItem(itemType), false);
				
			} else {
				AbstractClothingType clothingType = Util.getRandomObjectFromWeightedMap(clothingMap);
				AbstractClothing clothing = Main.game.getItemGen().generateClothing(clothingType);
				
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
			Attribute.MAJOR_PHYSIQUE.getFormattedValue(2)+" to 'potion effects'",
			Attribute.MAJOR_ARCANE.getFormattedValue(2)+" to 'potion effects'",
			Attribute.MAJOR_CORRUPTION.getFormattedValue(5)+" to 'potion effects'"),
			PresetColour.RACE_HUMAN) {
		
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
	
	/**
	 * This is just to provide a hard-coded hook to the biojuice canister's effects, for use in NPC.generateTransformativePotion()
	 */
	public static AbstractItemEffectType RACE_SLIME_TF_UTIL_EFFECT = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Transforms the consumer into a slime!"),
			PresetColour.RACE_HUMAN) {
		@Override
		public String getPotionDescriptor() {
			return "slime";
		}
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return Main.game.getItemGen().generateItem(ItemType.getItemTypeFromId("innoxia_race_slime_biojuice_canister")).applyEffect(user, target);
		}
	};
	
//	public static AbstractItemEffectType RACE_BREAD_ROLL = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(1)+" to 'potion effects'"),
//			PresetColour.RACE_HUMAN) {
//		@Override
//		public String getPotionDescriptor() {
//			return "human";
//		}
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot healthier...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_CANINE_CRUNCH = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(2)+" to 'potion effects'"),
//			PresetColour.RACE_DOG_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "canine";
//		}
//		
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot more energetic...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_FOX_PIE = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(2)+" to 'potion effects'"),
//			PresetColour.RACE_FOX_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "vulpine";
//		}
//		
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot more energetic...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_BURGER = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(2)+" to 'potion effects'"),
//			PresetColour.RACE_RAT_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "rat";
//		}
//		
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot more energetic...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_CARROT_CAKE = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.FERTILITY.getFormattedValue(15)+" to 'potion effects'",
//			Attribute.VIRILITY.getFormattedValue(15)+" to 'potion effects'",
//			Attribute.RESISTANCE_LUST.getFormattedValue(-25)+" to 'potion effects'"),
//			PresetColour.RACE_RABBIT_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "lagomorphic";
//		}
//		
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel like [npc.she] needs to breed...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.FERTILITY, 15)
//					+ target.addPotionEffect(Attribute.VIRILITY, 15)
//					+ target.addPotionEffect(Attribute.RESISTANCE_LUST, -25);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_KITTYS_REWARD = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_ARCANE.getFormattedValue(3)+" to 'potion effects'"),
//			PresetColour.RACE_CAT_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "feline";
//		}
//		
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel [npc.her] arcane power increasing...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 3);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_ROUND_NUTS = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_ARCANE.getFormattedValue(2)+" to 'potion effects'"),
//			PresetColour.RACE_SQUIRREL_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "sciuridine";
//		}
//		
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel [npc.her] arcane power increasing...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 2);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_FRUIT_SALAD = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_ARCANE.getFormattedValue(2)+" to 'potion effects'"),
//			PresetColour.RACE_BAT_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "chiropterine";
//		}
//		
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel [npc.her] arcane power increasing...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 2);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_SUGAR_CARROT_CUBE = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(3)+" to 'potion effects'"),
//			PresetColour.RACE_HORSE_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "equine";
//		}
//		
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot stronger...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_SUGAR_COOKIE = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(3)+" to 'potion effects'"),
//			PresetColour.RACE_REINDEER_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "reindeer";
//		}
//		
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot stronger...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
//		}
//	};
//	
//	public static AbstractItemEffectType RACE_ALLIGATORS_GUMBO = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(3)+" to 'potion effects'"),
//			PresetColour.RACE_ALLIGATOR_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "alligator";
//		}
//		
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot stronger...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
//		}
//	};
//	
//	public static AbstractItemEffectType RACE_BUBBLE_CREAM = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(3)+" to 'potion effects'"),
//			PresetColour.RACE_COW_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "bovine";
//		}
//		
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot stronger...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_MEAT_AND_MARROW = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(5)+" to 'potion effects'"),
//			PresetColour.RACE_WOLF_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "lupine";
//		}
//		
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot stronger...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 5);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_LOLLIPOP = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.DAMAGE_LUST.getFormattedValue(10)+" to 'potion effects'",
//			"[style.boldSex(+3)] [style.boldFeminine(femininity)]"),
//			PresetColour.RACE_HARPY) {
//		@Override
//		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
//			List<String> list = super.getEffectsDescription(primaryModifier, secondaryModifier, potency, limit, user, target);
//			AbstractStatusEffect se = StatusEffect.LOLLIPOP_SUCKING;
//			list.add("Applies <i style='color:"+se.getColour().toWebHexString()+";'>'"+Util.capitaliseSentence(se.getName(target))+"'</i>:");
//			for(Entry<AbstractAttribute, Float> entry : se.getAttributeModifiers(target).entrySet()) {
//				list.add("<i>"+entry.getKey().getFormattedValue(entry.getValue())+"</i>");
//			}
//			return list;
//		}
//		@Override
//		public String getPotionDescriptor() {
//			return "harpy";
//		}
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			target.addStatusEffect(StatusEffect.LOLLIPOP_SUCKING, 60*20);
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel more feminine...")
//					+ "</p>"
//					+ target.incrementFemininity(3)
//					+ "<br/>"
//					+ target.addPotionEffect(Attribute.DAMAGE_LUST, 10);
//		}
//	};
//	
//	public static AbstractItemEffectType RACE_BIOJUICE = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_CORRUPTION.getFormattedValue(25),
//			Attribute.MAJOR_CORRUPTION.getFormattedValue(50)+" to 'potion effects'",
//			"[style.boldSlime(Transforms body into slime!)]"),
//			PresetColour.RACE_SLIME) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "slime";
//		}
//		
//		@Override
//		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			if(target.getBody().getBodyMaterial()==BodyMaterial.SLIME) {
//				return "<p style='text-align:center;'>"
//							+ UtilText.parse(target, "[style.colourDisabled([npc.NameIsFull] already a slime, so nothing happens...)]")
//						+ "</p>";
//				
//			} else {
//				return target.incrementAttribute(Attribute.MAJOR_CORRUPTION, 25)
//						+ "<br/>"
//						+ target.addPotionEffect(Attribute.MAJOR_CORRUPTION, 50)
//						+ "<br/>"
//						+ target.setBodyMaterial(BodyMaterial.SLIME);
//			}
//		}
//	};
	
	// Essences:
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_ARCANE = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.boldGood(+1)] [style.boldArcane(Arcane essence)]"),
			PresetColour.GENERIC_ARCANE) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementEssenceCount(1, false);
			return "You have absorbed [style.boldGood(+1)] [style.boldArcane(Arcane essence)]!";
		}
	};
	

	// Specials:
	
	public static AbstractItemEffectType BIMBO_LOLLIPOP = new AbstractItemEffectType(Util.newArrayListOfValues(
			"<b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>Bimbo</b> <b style='color:"+PresetColour.RACE_HARPY.toWebHexString()+";'>harpy</b> <b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>transformation</b>",
			"Applies [style.boldSex('Sucking lollipop')] status effect"),
			PresetColour.RACE_HARPY) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.addStatusEffect(StatusEffect.LOLLIPOP_SUCKING, 60*20);
			
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
								+ "<br/><b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>You have gained the bimbo fetish!</b>"
							+ "</p>");
				} else {
					sb.append(UtilText.parse(target, "<br/>"
							+ "<p>"
								+ "A giggle escapes from between [npc.namePos] [npc.lips], and [npc.she] suddenly finds [npc.herself] unable to think of anything other than how, like, super awesome bimbos are and stuff!"
								+ "<br/><b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>[npc.Name] has gained the bimbo fetish!</b>"
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
			
			if(!target.isAbleToHaveRaceTransformed()) {
				sb.append("<br/>");
				sb.append(UtilText.parse(target, "As [npc.nameIsFull] immune to racial transformations, the lollipop has no further effect!"));
				
			} else {
				if(target.getHairType()!=HairType.HARPY) {
					sb.append("<br/>" + target.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_BLEACH_BLONDE, false, PresetColour.COVERING_BLEACH_BLONDE, false), true));
				}
				if(target.getTorsoType()!=TorsoType.HARPY) {
					sb.append("<br/>" + target.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, PresetColour.COVERING_BLEACH_BLONDE, false, PresetColour.COVERING_BLEACH_BLONDE, false), true));
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
				if(target.getTorsoType()!=TorsoType.HUMAN)
					sb.append("<br/>" + target.setTorsoType(TorsoType.HUMAN));
	
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
					sb.append("<br/>" + target.setPenisType(PenisType.HARPY));
	
					if(target.getPenisRawCumStorageValue()<CumProduction.TWO_SMALL_AMOUNT.getMedianValue()) {
						sb.append("<br/>" + target.setPenisCumStorage(CumProduction.TWO_SMALL_AMOUNT.getMedianValue()));
						target.fillCumToMaxStorage();
					}
				}
				if(target.hasVagina()) {
					sb.append("<br/>" + target.setVaginaType(VaginaType.HARPY));
				}
			}
			
			return sb.toString();
		}
	};
	
	public static AbstractItemEffectType NYMPHO_LOLLIPOP = new AbstractItemEffectType(Util.newArrayListOfValues(
			"<b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>Nympho</b> <b style='color:"+PresetColour.RACE_HARPY.toWebHexString()+";'>harpy</b> <b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>transformation</b>",
			"Applies [style.boldSex('Sucking lollipop')] status effect"),
			PresetColour.RACE_HARPY) {
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.addStatusEffect(StatusEffect.LOLLIPOP_SUCKING, 60*20);
			
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
								+ "<br/><b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>You have gained the nymphomaniac perk!</b>"
							+ "</p>");
				} else {
					sb.append(UtilText.parse(target, "<br/>"
							+ "<p>"
								+ "A desperate moan escapes from between [npc.namePos] [npc.lips], and [npc.she] suddenly finds [npc.herself] unable to think of anything other than sex, sex, and more sex!"
								+ "<br/><b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>[npc.Name] has gained the nymphomaniac perk!</b>"
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

			if(!target.isAbleToHaveRaceTransformed()) {
				sb.append("<br/>");
				sb.append(UtilText.parse(target, "As [npc.nameIsFull] immune to racial transformations, the lollipop has no further effect!"));
				
			} else {
				if(target.getHairType()!=HairType.HARPY) {
					sb.append("<br/>" + target.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_PINK, false, PresetColour.COVERING_PINK, false), true));
				}
				if(target.getTorsoType()!=TorsoType.HARPY) {
					sb.append("<br/>" + target.setHairCovering(new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, PresetColour.COVERING_PINK, false, PresetColour.COVERING_PINK, false), true));
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
				if(target.getTorsoType()!=TorsoType.HUMAN)
					sb.append("<br/>" + target.setTorsoType(TorsoType.HUMAN));
	
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
					sb.append("<br/>" + target.setPenisType(PenisType.HARPY));
	
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
			}
			
			return sb.toString();
		}
	};
	
	public static AbstractItemEffectType DOMINANT_PERFUME = new AbstractItemEffectType(Util.newArrayListOfValues(
			"<b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>Dominant</b> <b style='color:"+PresetColour.RACE_HARPY.toWebHexString()+";'>harpy</b> <b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>transformation</b>"),
			PresetColour.RACE_HARPY) {
		
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
								+ "<br/><b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>You have gained the dominant fetish!</b>"
							+ "</p>");
				} else {
					sb.append(UtilText.parse(target, "<br/>"
							+ "<p>"
								+ "A deep groan escapes from between [npc.namePos] [npc.lips], and [npc.she] suddenly finds [npc.herself] thinking of how much [npc.she] wants to dominate the next person [npc.she] meets!"
								+ "<br/><b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>[npc.Name] has gained the dominant fetish!</b>"
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

			if(!target.isAbleToHaveRaceTransformed()) {
				sb.append("<br/>");
				sb.append(UtilText.parse(target, "As [npc.nameIsFull] immune to racial transformations, the perfume has no further effect!"));
				
			} else {
				if(target.getHairType()!=HairType.HARPY) {
					sb.append("<br/>" + target.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_BLACK, false), true));
				}
				if(target.getTorsoType()!=TorsoType.HARPY) {
					sb.append("<br/>" + target.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, PresetColour.COVERING_RED, false, PresetColour.COVERING_RED, false), true));
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
				if(target.getTorsoType()!=TorsoType.HUMAN)
					sb.append("<br/>" + target.setTorsoType(TorsoType.HUMAN));
	
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
					sb.append("<br/>" + target.setPenisType(PenisType.HARPY));
	
					if(target.getPenisRawCumStorageValue()<CumProduction.TWO_SMALL_AMOUNT.getMedianValue()) {
						sb.append("<br/>" + target.setPenisCumStorage(CumProduction.TWO_SMALL_AMOUNT.getMedianValue()));
						target.fillCumToMaxStorage();
					}
				}
				if(target.hasVagina()) {
					sb.append("<br/>" + target.setVaginaType(VaginaType.HARPY));
				}
			}
			
			return sb.toString();
		}
	};
	
	
	// Enchantment effects:
	
	public static AbstractItemEffectType ATTRIBUTE_PHYSIQUE = new AbstractItemEffectType(null,
			PresetColour.ATTRIBUTE_PHYSIQUE) {

		@Override
		public String getPotionDescriptor() {
			return "vivid";
		}
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFModStrengthList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
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
			PresetColour.ATTRIBUTE_ARCANE) {

		@Override
		public String getPotionDescriptor() {
			return "soothing";
		}
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFModIntelligenceList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
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
			PresetColour.GENERIC_SEX) {

		@Override
		public String getPotionDescriptor() {
			return "arousing";
		}
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFModSexualList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
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
			PresetColour.ATTRIBUTE_CORRUPTION) {

		@Override
		public String getPotionDescriptor() {
			return "viscous";
		}
		
		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTFModCorruptionList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
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
			PresetColour.FETISH) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return Util.newArrayListOfValues(
					TFModifier.TF_MOD_FETISH_BODY_PART,
					TFModifier.TF_MOD_FETISH_BEHAVIOUR);
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
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
			List<AbstractFetish> availableFetishes = new ArrayList<>();
			
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
					List<AbstractFetish> fetishesToAdd = new ArrayList<>();
					for(AbstractFetish f : availableFetishes) {
						if(f.getFetishesForAutomaticUnlock().isEmpty() && !target.hasFetish(f)) {
							if(f.isAvailable(target)) {
								fetishesToAdd.add(f);
							}
						}
					}
					
					if(!fetishesToAdd.isEmpty()) {
						AbstractFetish f = fetishesToAdd.get(Util.random.nextInt(fetishesToAdd.size()));
						return target.addFetish(f);
						
					} else {
						return "<p>"
									+"[style.colourDisabled(Nothing happens...)]"
								+"</p>";
					}
					
				} else {
					AbstractFetish fetish = secondaryModifier.getFetish();
					
					return target.addFetish(fetish);
				}
				
			} else if(potency==TFPotency.MINOR_BOOST) {
				if(secondaryModifier == TFModifier.NONE) {
					List<AbstractFetish> fetishesToBoost = new ArrayList<>();
					for(AbstractFetish f : availableFetishes) {
						if(f.getFetishesForAutomaticUnlock().isEmpty() && !target.hasFetish(f)) {
							if(f.isAvailable(target)) {
								fetishesToBoost.add(f);
							}
						}
					}
					
					if(!fetishesToBoost.isEmpty()) {
						AbstractFetish f = fetishesToBoost.get(Util.random.nextInt(fetishesToBoost.size()));
						FetishDesire newDesire = target.getFetishDesire(f).getNextDesire();
						
						return target.setFetishDesire(f, newDesire);
						
					} else {
						return "<p>"
									+"[style.colourDisabled(Nothing happens...)]"
								+"</p>";
					}
					
				} else {
					AbstractFetish fetish = secondaryModifier.getFetish();
					FetishDesire newDesire = target.getFetishDesire(fetish).getNextDesire();
					
					return target.setFetishDesire(fetish, newDesire);
				}
				
			} else if(potency==TFPotency.MINOR_DRAIN) {
				if(secondaryModifier == TFModifier.NONE) {
					List<AbstractFetish> fetishesToDrain = new ArrayList<>();
					for(AbstractFetish f : availableFetishes) {
						if(f.getFetishesForAutomaticUnlock().isEmpty() && !target.hasFetish(f)) {
							if(f.isAvailable(target)) {
								fetishesToDrain.add(f);
							}
						}
					}
					
					if(!fetishesToDrain.isEmpty()) {
						AbstractFetish f = fetishesToDrain.get(Util.random.nextInt(fetishesToDrain.size()));
						FetishDesire newDesire = target.getFetishDesire(f).getPreviousDesire();
						
						return target.setFetishDesire(f, newDesire);
						
					} else {
						return "<p>"
									+"[style.colourDisabled(Nothing happens...)]"
								+"</p>";
					}
					
				} else {
					AbstractFetish fetish = secondaryModifier.getFetish();
					FetishDesire newDesire = target.getFetishDesire(fetish).getPreviousDesire();
					
					return target.setFetishDesire(fetish, newDesire);
				}
				
			} else {
				if(secondaryModifier == TFModifier.NONE) {
					List<AbstractFetish> fetishesToRemove = new ArrayList<>();
					for(AbstractFetish f : availableFetishes) {
						if(f.getFetishesForAutomaticUnlock().isEmpty()) {
							if(target.hasFetish(f)) {
								fetishesToRemove.add(f);
							}
						}
					}
					
					if(!fetishesToRemove.isEmpty()) {
						AbstractFetish f = fetishesToRemove.get(Util.random.nextInt(fetishesToRemove.size()));
						return target.removeFetish(f);
						
					} else {
						return "<p>"
									+"[style.colourDisabled(Nothing happens...)]"
								+"</p>";
					}
					
				} else {
					AbstractFetish fetish = secondaryModifier.getFetish();
					
					return target.removeFetish(fetish);
				}
			}
		}
	};
	
	// RACIAL:
	
	public static AbstractItemEffectType DEBUG_DEMON_POTION_EFFECT = new AbstractItemEffectType(null,
			PresetColour.RACE_DEMON) {
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			List<String> effectsDescription = new ArrayList<>();
			
			effectsDescription.add("[style.boldBad(Does not affect unique characters)]");
			
			effectsDescription.add("[style.boldTfGeneric(Transforms)] non-demons into [style.boldDemon(half-demons)]");

			effectsDescription.add("[style.boldTfGeneric(Transforms)] half-demons into [style.boldDemon(demons)]");
			
			return effectsDescription;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			if(target.isPlayer()) {
//				return "<p style='text-align:center'>[style.italicsDisabled(This item does not work on you...)]</p>";
//			}
			if(!target.isPlayer() && target.isUnique() && (!target.isSlave() || target.getOwner().isPlayer())) {
				return "<p style='text-align:center'>[style.italicsDisabled(This item does not work on non-slave unique characters...)]</p>";
			}
			
			AbstractSubspecies sub = target.getBody().getFleshSubspecies();
			if(sub.getRace()!=Race.DEMON) {
				target.setBody(Main.game.getCharacterUtils().generateHalfDemonBody(target, target.getGender(), sub, true), false);
				return UtilText.parse(target, "<p style='text-align:center; color:"+PresetColour.RACE_DEMON.toWebHexString()+";'><i>[npc.Name] is now [npc.a_race]!</i></p>");
			} else {
				target.setBody(target.getGender(), Subspecies.DEMON, RaceStage.GREATER, false);
				target.setSubspeciesOverride(Subspecies.DEMON);
				return UtilText.parse(target, "<p style='text-align:center; color:"+PresetColour.RACE_DEMON.toWebHexString()+";'><i>[npc.Name] is now [npc.a_race]!</i></p>");
			}
		}
	};
	
	// CLOTHING:
	
	public static AbstractItemEffectType CLOTHING = new AbstractItemEffectType(null,
			PresetColour.RARITY_RARE) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getClothingPrimaryList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE) {
				return TFModifier.getClothingAttributeList();
				
			} else if(primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
				return TFModifier.getClothingMajorAttributeList();
				
			} else if(primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR) {
				return TFModifier.getTFBehaviouralFetishList();
				
			} else if(primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART) {
				return TFModifier.getTFBodyPartFetishList();
				
			} else if(primaryModifier == TFModifier.CLOTHING_SPECIAL) {
				List<TFModifier> mods =  Util.newArrayListOfValues(TFModifier.CLOTHING_SEALING, TFModifier.CLOTHING_SERVITUDE);
				if(targetItem instanceof AbstractClothing) {
					 //If this clothing is a 'sex toy' or groin/nipple clothing, then allow vibration and orgasm denial enchantments:
					if(((AbstractClothing)targetItem).getItemTags().contains(ItemTag.ENABLE_SEX_EQUIP)
							|| !Collections.disjoint(
									((AbstractClothing)targetItem).getClothingType().getEquipSlots(),
									Util.newArrayListOfValues(
											InventorySlot.GROIN,
											InventorySlot.VAGINA,
											InventorySlot.PENIS,
											InventorySlot.ANUS,
											InventorySlot.NIPPLE,
											InventorySlot.CHEST,
											InventorySlot.PIERCING_NIPPLE,
											InventorySlot.PIERCING_PENIS,
											InventorySlot.PIERCING_VAGINA))) {
						mods.add(TFModifier.CLOTHING_VIBRATION);
						mods.add(TFModifier.CLOTHING_ORGASM_PREVENTION);
					}
				}
				if(Main.game.getPlayer().isHasSlaverLicense()) {
					mods.add(TFModifier.CLOTHING_ENSLAVEMENT);
				}
				return mods;
				
			} else if(primaryModifier == TFModifier.CLOTHING_CONDOM) {
				return Util.newArrayListOfValues(TFModifier.ARCANE_BOOST);
				
			} else {
				return getClothingTFSecondaryModifiers(primaryModifier);
			}
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			 if(secondaryModifier == TFModifier.CLOTHING_SEALING) {
				return Util.newArrayListOfValues(TFPotency.MINOR_BOOST, TFPotency.MINOR_DRAIN, TFPotency.DRAIN, TFPotency.MAJOR_DRAIN);
				
			} else if(secondaryModifier == TFModifier.CLOTHING_VIBRATION
						|| secondaryModifier == TFModifier.REMOVAL
						|| secondaryModifier == TFModifier.TF_TYPE_1) {
				return Util.newArrayListOfValues(TFPotency.MINOR_BOOST, TFPotency.BOOST, TFPotency.MAJOR_BOOST);
				
			} else if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE
						|| primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE
						|| primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR
						|| primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART
						|| primaryModifier == TFModifier.CLOTHING_CONDOM
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
				effectsList.add(secondaryModifier.getAssociatedAttribute().getFormattedValue(potency.getClothingBonusValue()));
				
			} else if(secondaryModifier == TFModifier.CLOTHING_SEALING) {
				if(potency==TFPotency.MINOR_DRAIN) {
					effectsList.add("[style.boldCrimson(Seals onto wearer)] <b>(Unseal: [style.boldArcane(" + ItemEffect.SEALED_COST_MINOR_DRAIN + ")])</b>");
					
				} else if(potency==TFPotency.DRAIN) {
					effectsList.add("[style.boldCrimson(Seals onto wearer)] <b>(Unseal: [style.boldArcane(" + ItemEffect.SEALED_COST_DRAIN + ")])</b>");
					
				} else if(potency==TFPotency.MAJOR_DRAIN) {
					effectsList.add("[style.boldCrimson(Seals onto wearer)] <b>(Unseal: [style.boldArcane(" + ItemEffect.SEALED_COST_MAJOR_DRAIN + ")])</b>");
					
				} else {
					effectsList.add("[style.boldCrimson(Seals onto wearer)] <b>(Unseal: [style.boldArcane(" + ItemEffect.SEALED_COST_MINOR_BOOST + ")])</b>");
				}
				
			} else if(secondaryModifier == TFModifier.CLOTHING_SERVITUDE) {
				effectsList.add("[style.boldBad(Inhibits)] [style.boldTfGeneric(self-transformations)]");
				effectsList.add("[style.boldBad(Prevents)] [style.boldArcane(removal of seals)]");
				
			} else if(secondaryModifier == TFModifier.CLOTHING_ENSLAVEMENT) {
				effectsList.add("[style.boldCrimson(Enslaves the wearer)]");
				
			} else if(secondaryModifier == TFModifier.CLOTHING_ORGASM_PREVENTION) {
				effectsList.add("[style.boldCrimson(Prevents wearer from orgasming)]");
				
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
					effectsList.add("[style.boldGood("+Units.fluid(CumProduction.SIX_EXTREME.getMaximumValue())+")] safe cum capacity.");
					
				} else if(potency==TFPotency.MINOR_BOOST) {
					effectsList.add("[style.boldMinorGood("+Units.fluid(CumProduction.FIVE_HUGE.getMaximumValue())+")] safe cum capacity.");
					
				} else if(potency==TFPotency.MAJOR_DRAIN
						|| potency==TFPotency.DRAIN
						|| potency==TFPotency.MINOR_DRAIN) {
					effectsList.add("[style.boldTerrible(Sabotaged)] to always break!");
				}
				
			} else if(secondaryModifier == TFModifier.CLOTHING_VIBRATION) {
				if(potency==TFPotency.MAJOR_BOOST) {
					effectsList.add("[style.boldSex(+20)] [style.boldLust(Resting lust)]");
					effectsList.add("[style.boldSex(+2)] [style.boldArousal(arousal/turn)] [style.boldSex(in sex)]");
					
				} else if(potency==TFPotency.BOOST) {
					effectsList.add("[style.boldSex(+10)] [style.boldLust(Resting lust)]");
					effectsList.add("[style.boldSex(+1)] [style.boldArousal(arousal/turn)] [style.boldSex(in sex)]");
					
				} else {
					effectsList.add("[style.boldSex(+5)] [style.boldLust(Resting lust)]");
					effectsList.add("[style.boldSex(+0.5)] [style.boldArousal(arousal/turn)] [style.boldSex(in sex)]");
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
					|| secondaryModifier == TFModifier.CLOTHING_SERVITUDE
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
			PresetColour.RARITY_RARE) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getTattooPrimaryList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
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
			 if(secondaryModifier == TFModifier.CLOTHING_VIBRATION
					|| secondaryModifier == TFModifier.REMOVAL
					|| secondaryModifier == TFModifier.TF_TYPE_1) {
				return Util.newArrayListOfValues(TFPotency.MINOR_BOOST, TFPotency.BOOST, TFPotency.MAJOR_BOOST);
				
			} else if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE
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
				effectsList.add(secondaryModifier.getAssociatedAttribute().getFormattedValue(potency.getClothingBonusValue()));
				
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
			PresetColour.RARITY_RARE) {

		@Override
		public List<TFModifier> getPrimaryModifiers() {
			return TFModifier.getWeaponPrimaryList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
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

			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE || primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
				effectsList.add(secondaryModifier.getAssociatedAttribute().getFormattedValue(potency.getClothingBonusValue()));
				
			} else {
				effectsList.add("[style.boldBad(Unrecognised effect:)] "+primaryModifier.getName());
			}
			
			return effectsList;
		}
		
		@Override
		public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "";
		}
	};
	
	public static AbstractItemEffectType OFFSPRING_MAP = new AbstractItemEffectType(Util.newArrayListOfValues(
			"Facilitates the discovery of offspring."),
			PresetColour.RARITY_LEGENDARY) {
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

	public static AbstractItemEffectType getRacialEffectType(AbstractRace race) {
		return racialEffectTypes.get(race);
	}
	
	public static Map<AbstractItemEffectType, String> itemEffectTypeToIdMap = new HashMap<>();
	public static Map<String, AbstractItemEffectType> idToItemEffectTypeMap = new HashMap<>();
	public static List<AbstractItemEffectType> allEffectTypes = new ArrayList<>();
	public static Map<AbstractRace, AbstractItemEffectType> racialEffectTypes = new HashMap<>();
	
	public static void addAbstractItemEffectToIds(String id, AbstractItemEffectType itemEffectType) {
		allEffectTypes.add(itemEffectType);
		
		itemEffectTypeToIdMap.put(itemEffectType, id);
		idToItemEffectTypeMap.put(id, itemEffectType);
	}
	
	public static AbstractItemEffectType getItemEffectTypeFromId(String id) {
		if(id.startsWith("RACE_")) {
			return getRacialEffectType(Race.getRaceFromId(id.substring(5)));
		}
		id = Util.getClosestStringMatch(id, idToItemEffectTypeMap.keySet());
		return idToItemEffectTypeMap.get(id);
	}
	
	public static String getIdFromItemEffectType(AbstractItemEffectType itemEffectType) {
		return itemEffectTypeToIdMap.get(itemEffectType);
	}
	
	// set in ItemType
	public static AbstractItemEffectType getBookEffectFromSubspecies(AbstractSubspecies subspecies) {
		String id = Util.getClosestStringMatch("BOOK_READ_"+Subspecies.getIdFromSubspecies(subspecies), idToItemEffectTypeMap.keySet());
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
		
		for(AbstractRace race : Race.getAllRaces()) {
			if(race==Race.SLIME) { // Special case for slimes:
				racialEffectTypes.put(
						race,
						new AbstractItemEffectType(null,
								race.getColour()) {
							@Override
							public AbstractRace getAssociatedRace() {
								return race;
							}
							@Override
							public List<TFModifier> getPrimaryModifiers() {
								return Util.newArrayListOfValues(TFModifier.TF_MATERIAL_FLESH);
							}
							@Override
							public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
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
								return target.getBodyMaterial() == BodyMaterial.SLIME
										? target.setBodyMaterial(BodyMaterial.FLESH)
										: "<p style='margin-bottom:0; padding-bottom:0;'>" +
											"[style.colourDisabled([npc.NameIsFull] an elemental, so nothing happens...)]" +
											"</p>";
							}
						});
				
			} else {
				racialEffectTypes.put(
						race,
						new AbstractItemEffectType(null,
								race.getColour()) {
							@Override
							public AbstractRace getAssociatedRace() {
								return race;
							}
							@Override
							public List<TFModifier> getPrimaryModifiers() {
								return TFModifier.getTFRacialBodyPartsList();
							}
							@Override
							public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
								return getRacialSecondaryModifiers(race, primaryModifier);
							}
							@Override
							public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
								return getRacialPotencyModifiers(race, primaryModifier, secondaryModifier);
							}
							@Override
							public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
								return Util.newArrayListOfValues(getRacialEffect(race, primaryModifier, secondaryModifier, potency, user, target).getDescription());
							}
							@Override
							public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
								return getRacialEffect(race, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
							}
						});
			}
		}
		
		for(Entry<AbstractRace, AbstractItemEffectType> entry : racialEffectTypes.entrySet()) {
			allEffectTypes.add(entry.getValue());
			
			String id = "RACE_"+Race.getIdFromRace(entry.getKey());
			itemEffectTypeToIdMap.put(entry.getValue(), id);
			idToItemEffectTypeMap.put(id, entry.getValue());
		}
	}
	
}
