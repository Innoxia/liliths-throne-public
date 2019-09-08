package com.lilithsthrone.game.inventory.enchanting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.types.AbstractHornType;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.FootStructure;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidExpulsion;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryBookAddedToLibrary;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.4
 * @version 0.3
 * @author Innoxia
 */
public abstract class AbstractItemEffectType {
	
	private List<String> effectsDescriptions;
	private Colour colour;
	
	public AbstractItemEffectType(List<String> effectsDescriptions, Colour colour) {
		this.effectsDescriptions = effectsDescriptions;
		this.colour = colour;
	}
	
	public Colour getColour() {
		return colour;
	}
	
	public String getPotionDescriptor() {
		return "";
	}
	
	/**
	 * <b>This disables use in sex or combat automatically.</b>
	 * @return true if the use of this item should exit inventory management. i.e. If it's meant to set the content to a specific scene.
	 */
	public boolean isBreakOutOfInventory() {
		return false;
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

	public int getSmallLimitChange() {
		if (EnchantmentDialogue.getSecondaryMod() == TFModifier.TF_MOD_WETNESS
				&& (EnchantmentDialogue.getPrimaryMod() == TFModifier.TF_BREASTS
						|| EnchantmentDialogue.getPrimaryMod() == TFModifier.TF_BREASTS_CROTCH
						|| EnchantmentDialogue.getPrimaryMod() == TFModifier.TF_PENIS)) {
			// Increase small change for fluids
			return 10;
		}
		return 1;
	}

	public int getLargeLimitChange() {
		if (EnchantmentDialogue.getSecondaryMod() == TFModifier.TF_MOD_WETNESS
				&& (EnchantmentDialogue.getPrimaryMod() == TFModifier.TF_BREASTS
						|| EnchantmentDialogue.getPrimaryMod() == TFModifier.TF_BREASTS_CROTCH
						|| EnchantmentDialogue.getPrimaryMod() == TFModifier.TF_PENIS)) {
			// Decrease large change for fluids
			return 500;
		}
		return Math.max(5, getMaximumLimit()/10);
	}

	public int getMaximumLimit() {
		return getLimits(EnchantmentDialogue.getPrimaryMod(), EnchantmentDialogue.getSecondaryMod());
	}
	
	public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
		return effectsDescriptions;
	}
	
	public abstract String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer);
	
	public static String getBookEffect(GameCharacter reader, Subspecies subspecies, boolean withDescription) {
		Main.getProperties().addRaceDiscovered(subspecies);
		if(Main.getProperties().addAdvancedRaceKnowledge(subspecies) && ItemType.getLoreBook(subspecies)!=null) {
			Main.game.addEvent(new EventLogEntryBookAddedToLibrary(ItemType.getLoreBook(subspecies)), true);
		}

		AbstractPerk perk = Perk.getSubspeciesRelatedPerk(subspecies);
		if(!reader.isPlayer() || ((PlayerCharacter) reader).addRaceDiscoveredFromBook(subspecies) || !reader.hasPerkAnywhereInTree(perk)) {
			return (withDescription
						?subspecies.getBasicDescription(null)
								+subspecies.getAdvancedDescription(null)
						:"")
					+reader.addSpecialPerk(perk);
			
		} else {
			return subspecies.getBasicDescription(null)
					+subspecies.getAdvancedDescription(null)
					+"<p style='text-align:center; color:"+Colour.TEXT_GREY.toWebHexString()+";'>"
						+ "Nothing further can be gained from re-reading this book..."
					+ "</p>";
		}
		
	}
	
	protected static List<TFModifier> getClothingTFSecondaryModifiers(TFModifier primaryModifier) {
		switch(primaryModifier) {
			case TF_ASS:
				return Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,// ass size
						TFModifier.TF_MOD_SIZE_SECONDARY,// hip size
						TFModifier.TF_MOD_CAPACITY,
						TFModifier.TF_MOD_ELASTICITY,
						TFModifier.TF_MOD_PLASTICITY,
						TFModifier.TF_MOD_WETNESS,
						TFModifier.TF_MOD_ORIFICE_PUFFY,
						TFModifier.TF_MOD_ORIFICE_RIBBED,
						TFModifier.TF_MOD_ORIFICE_MUSCLED,
						TFModifier.TF_MOD_ORIFICE_TENTACLED
						);
			case TF_BREASTS: case TF_BREASTS_CROTCH:
				return Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,// breast size
						TFModifier.TF_MOD_SIZE_SECONDARY,// nipple size
						TFModifier.TF_MOD_SIZE_TERTIARY,// areolae size
						TFModifier.TF_MOD_CAPACITY,
						TFModifier.TF_MOD_ELASTICITY,
						TFModifier.TF_MOD_PLASTICITY,
						TFModifier.TF_MOD_WETNESS,
						TFModifier.TF_MOD_REGENERATION,
						TFModifier.TF_MOD_ORIFICE_PUFFY,
						TFModifier.TF_MOD_ORIFICE_RIBBED,
						TFModifier.TF_MOD_ORIFICE_MUSCLED,
						TFModifier.TF_MOD_ORIFICE_TENTACLED
						);
			case TF_CORE:
				return Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,// height
						TFModifier.TF_MOD_SIZE_SECONDARY,// muscle mass
						TFModifier.TF_MOD_SIZE_TERTIARY,// body size
						TFModifier.TF_MOD_FEMININITY
						);
			case TF_FACE:
				return Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,
						TFModifier.TF_MOD_ORIFICE_PUFFY,
						TFModifier.TF_MOD_ORIFICE_RIBBED,
						TFModifier.TF_MOD_ORIFICE_MUSCLED,
						TFModifier.TF_MOD_ORIFICE_TENTACLED
						);
			case TF_HAIR:
				return Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE// hair length
						);
			case TF_PENIS:
				List<TFModifier> penisMods = Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,
						TFModifier.TF_MOD_SIZE_SECONDARY,
						TFModifier.TF_MOD_CAPACITY,
						TFModifier.TF_MOD_ELASTICITY,
						TFModifier.TF_MOD_PLASTICITY,
						TFModifier.TF_MOD_WETNESS,
						TFModifier.TF_MOD_CUM_EXPULSION,
						TFModifier.TF_MOD_REGENERATION
						);

				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					penisMods.add(TFModifier.TF_MOD_ORIFICE_PUFFY);
					penisMods.add(TFModifier.TF_MOD_ORIFICE_RIBBED);
					penisMods.add(TFModifier.TF_MOD_ORIFICE_MUSCLED);
					penisMods.add(TFModifier.TF_MOD_ORIFICE_TENTACLED);
				}
				return penisMods;
				
			case TF_VAGINA:
				List<TFModifier> mods = Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,// clit size
						TFModifier.TF_MOD_SIZE_SECONDARY,// labia size
						TFModifier.TF_MOD_CAPACITY,
						TFModifier.TF_MOD_ELASTICITY,
						TFModifier.TF_MOD_PLASTICITY,
						TFModifier.TF_MOD_WETNESS,
						TFModifier.TF_MOD_ORIFICE_PUFFY,
						TFModifier.TF_MOD_ORIFICE_RIBBED,
						TFModifier.TF_MOD_ORIFICE_MUSCLED,
						TFModifier.TF_MOD_ORIFICE_TENTACLED
						);

				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					mods.add(TFModifier.TF_MOD_CAPACITY_2);
					mods.add(TFModifier.TF_MOD_ELASTICITY_2);
					mods.add(TFModifier.TF_MOD_PLASTICITY_2);
					mods.add(TFModifier.TF_MOD_ORIFICE_PUFFY_2);
					mods.add(TFModifier.TF_MOD_ORIFICE_RIBBED_2);
					mods.add(TFModifier.TF_MOD_ORIFICE_MUSCLED_2);
					mods.add(TFModifier.TF_MOD_ORIFICE_TENTACLED_2);
				}
				return mods;
			default:
				break;
		}
		return new ArrayList<>();
	}
	
	protected static int getClothingTFLimits(TFModifier primaryModifier, TFModifier secondaryModifier) {

		switch(secondaryModifier) {
			case TF_MOD_CAPACITY:
				return Capacity.SEVEN_GAPING.getMaximumValue();
			case TF_MOD_ELASTICITY:
				return OrificeElasticity.SEVEN_ELASTIC.getValue();
			case TF_MOD_PLASTICITY:
				return OrificePlasticity.SEVEN_MOULDABLE.getValue();
			case TF_MOD_CAPACITY_2:
				return Capacity.SEVEN_GAPING.getMaximumValue();
			case TF_MOD_ELASTICITY_2:
				return OrificeElasticity.SEVEN_ELASTIC.getValue();
			case TF_MOD_PLASTICITY_2:
				return OrificePlasticity.SEVEN_MOULDABLE.getValue();
			case TF_MOD_WETNESS:
				if(primaryModifier!=TFModifier.TF_PENIS
					&& primaryModifier!=TFModifier.TF_BREASTS
					&& primaryModifier!=TFModifier.TF_BREASTS_CROTCH) {
					return Wetness.SEVEN_DROOLING.getValue();
				}
				break;
			case TF_MOD_ORIFICE_PUFFY:
			case TF_MOD_ORIFICE_RIBBED:
			case TF_MOD_ORIFICE_MUSCLED:
			case TF_MOD_ORIFICE_TENTACLED:
				return 0;
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
			case TF_BREASTS: case TF_BREASTS_CROTCH:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return CupSize.getMaximumCupSize().getMeasurement();
					case TF_MOD_SIZE_SECONDARY:
						return NippleSize.FOUR_MASSIVE.getValue();
					case TF_MOD_SIZE_TERTIARY:
						return  AreolaeSize.FOUR_MASSIVE.getValue();
					case TF_MOD_WETNESS:
						return Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue();
					case TF_MOD_REGENERATION:
						return FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay();
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
					case TF_MOD_CUM_EXPULSION:
						return FluidExpulsion.FOUR_HUGE.getMaximumValue();
					case TF_MOD_REGENERATION:
						return FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay();
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
	
	protected static List<String> getClothingTFDescriptions(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
		List<String> descriptions = new ArrayList<>();
		
		String orificeName = "";

		if(primaryModifier==TFModifier.TF_PENIS) {
			orificeName = "urethra";
		} else if(primaryModifier==TFModifier.TF_VAGINA) {
			orificeName = "vagina";
		} else if(primaryModifier==TFModifier.TF_BREASTS) {
			orificeName = "nipple";
		} else if(primaryModifier==TFModifier.TF_BREASTS_CROTCH) {
			orificeName = "teat (crotch)";
		} else if(primaryModifier==TFModifier.TF_ASS) {
			orificeName = "anus";
		}
		
		switch(secondaryModifier) {
			case TF_MOD_CAPACITY:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+" capacity", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
				break;
			case TF_MOD_ELASTICITY:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+" elasticity", OrificeElasticity.getElasticityFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_PLASTICITY:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+" plasticity", OrificePlasticity.getElasticityFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_CAPACITY_2:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"l urethra capacity", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
				break;
			case TF_MOD_ELASTICITY_2:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"l urethra elasticity", OrificeElasticity.getElasticityFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_PLASTICITY_2:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"l urethra plasticity", OrificePlasticity.getElasticityFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_WETNESS:
				if(primaryModifier!=TFModifier.TF_PENIS
						&& primaryModifier!=TFModifier.TF_BREASTS
						&& primaryModifier!=TFModifier.TF_BREASTS_CROTCH) {
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
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "anus puffy", "anal puffyness"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "anus ribbed",  "anal ribbing"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "anus muscled", "extra anal muscles"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "anus tentacled", "anal tentacles"));
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
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "lactation", Units.fluid(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_REGENERATION:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "milk regeneration", String.valueOf(limit)));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "nipples puffy", "nipple puffyness"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "nipples internally ribbed",  "nipple ribbing"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "nipples internally muscled", "internal nipple muscles"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "nipples internally tentacled", "nipple tentacles"));
						break;
					default:
						break;
				}
				break;
			case TF_BREASTS_CROTCH:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "cup size (crotch)", CupSize.getCupSizeFromInt(limit).getCupSizeName()+"-cup"));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "nipple size (crotch)", NippleSize.getNippleSizeFromInt(limit).getName()));
						break;
					case TF_MOD_SIZE_TERTIARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "areolae size (crotch)", AreolaeSize.getAreolaeSizeFromInt(limit).getName()));
						break;
					case TF_MOD_WETNESS:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "lactation (crotch)", Units.fluid(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_REGENERATION:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "milk regeneration (crotch)", String.valueOf(limit)));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "nipples puffy (crotch)", "nipple puffyness"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "nipples internally ribbed (crotch)",  "nipple ribbing"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "nipples internally muscled (crotch)", "internal nipple muscles"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "nipples internally tentacled (crotch)", "nipple tentacles"));
						break;
					default:
						break;
				}
				break;
			case TF_CORE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "height", Units.size(Height.ZERO_TINY.getMinimumValue() + limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
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
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "tongue length", TongueLength.getTongueLengthFromInt(limit).getDescriptor()));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "lips puffy", "puffy lips"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "throat internally ribbed",  "throat ribbing"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "throat internally muscled", "extra throat muscles"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "throat internally tentacled", "throat tentacles"));
						break;
					default:
						break;
				}
				break;
			case TF_HAIR:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "hair length", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					default:
						break;
				}
				break;
			case TF_PENIS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "penis size", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "testicle size", TesticleSize.getTesticleSizeFromInt(limit).getDescriptor()));
						break;
					case TF_MOD_WETNESS:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "cum storage", Units.fluid(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_CUM_EXPULSION:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "cum expulsion", limit+"%"));
						break;
					case TF_MOD_REGENERATION:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "cum regeneration", String.valueOf(limit)));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "urethra puffy", "puffy urethra"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "urethra internally ribbed",  "urethral ribbing"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "urethra internally muscled", "urethral muscles"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "urethra internally tentacled", "urethral tentacles"));
						break;
					default:
						break;
				}
				break;
			case TF_VAGINA:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "clitoris size", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "labia size", LabiaSize.getLabiaSizeFromInt(limit).getName()));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "vagina puffy", "puffy vagina"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "vagina internally ribbed",  "vaginal ribbing"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "vagina internally muscled", "extra vaginal muscles"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "vagina internally tentacled", "vaginal tentacles"));
						break;
					case TF_MOD_ORIFICE_PUFFY_2:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "urethra puffy", "puffy urethra"));
						break;
					case TF_MOD_ORIFICE_RIBBED_2:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "urethra internally ribbed",  "urethral ribbing"));
						break;
					case TF_MOD_ORIFICE_MUSCLED_2:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "urethra internally muscled", "urethral muscles"));
						break;
					case TF_MOD_ORIFICE_TENTACLED_2:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "urethra internally tentacled", "urethral tentacles"));
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
	
	private static String getClothingOrificeTFChangeDescriptionEntry(TFPotency potency, String changeAdd, String changeRemove) {
		switch(potency) {
			case MINOR_BOOST:
				return ("In a week, makes "+changeAdd+".");
			case BOOST:
				return ("In a day, makes "+changeAdd+".");
			case MAJOR_BOOST:
				return ("In an hour, makes "+changeAdd+".");
			case MINOR_DRAIN:
				return ("In a week, removes "+changeRemove+".");
			case DRAIN:
				return ("In a day, removes "+changeRemove+".");
			case MAJOR_DRAIN:
				return ("In an hour, removes "+changeRemove+".");
		}
		return "";
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
				return ("Weekly "+subject+" decrease. (Limit: "+limit+")");
			case DRAIN:
				return ("Daily "+subject+" decrease. (Limit: "+limit+")");
			case MAJOR_DRAIN:
				return ("Hourly "+subject+" decrease. (Limit: "+limit+")");
		}
		return "";
	}
	
	protected static String applyClothingTF(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
		int capacityIncrement = (potency.isNegative()?-2:2);
		int elasticityIncrement = (potency.isNegative()?-1:1);
		int plasticityIncrement = (potency.isNegative()?-1:1);
		int wetnessIncrement = (potency.isNegative()?-1:1);
		int assSizeIncrement = (potency.isNegative()?-1:1);
		int hipSizeIncrement = (potency.isNegative()?-1:1);

		int breastSizeIncrement = (potency.isNegative()?-1:1);
		int nippleSizeIncrement = (potency.isNegative()?-1:1);
		int areolaeSizeIncrement = (potency.isNegative()?-1:1);
		int lactationIncrement = (potency.isNegative()?-50:50);

		int fluidRegenerationIncrement = (potency.isNegative()?-250:250);
		
		int heightIncrement = (potency.isNegative()?-1:1);
		int muscleIncrement = (potency.isNegative()?-1:1);
		int bodySizeIncrement = (potency.isNegative()?-1:1);
		int femininityIncrement = (potency.isNegative()?-1:1);

		int lipSizeIncrement = (potency.isNegative()?-1:1);

		int hairLengthIncrement = (potency.isNegative()?-1:1);

		int penisSizeIncrement = (potency.isNegative()?-1:1);
		int testicleSizeIncrement = (potency.isNegative()?-1:1);
		int cumStorageIncrement = (potency.isNegative()?-5:5);
		int cumExpulsionIncrement = (potency.isNegative()?-5:5);

		int clitorisSizeIncrement = (potency.isNegative()?-1:1);
		int labiaSizeIncrement = (potency.isNegative()?-1:1);
		
		int TFCount = 0;
		int secondsRequired = 60*60;
		
		switch(potency) {
			case MINOR_BOOST:
				secondsRequired = 7 * 24 * 60 * 60;
				break;
			case BOOST:
				secondsRequired = 24 * 60 * 60;
				break;
			case MAJOR_BOOST:
				secondsRequired = 60 * 60;
				break;
			case MINOR_DRAIN:
				secondsRequired = 7 * 24 * 60 * 60;
				break;
			case DRAIN:
				secondsRequired = 24 * 60 * 60;
				break;
			case MAJOR_DRAIN:
				secondsRequired = 60 * 60;
				break;
		}
		
		TFCount = timer.getSecondsPassed()/secondsRequired;
		if(TFCount>=1) {
			timer.setSecondsPassed(timer.getSecondsPassed()%secondsRequired);
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
							} else if(isSetToLimit(assSizeIncrement, target.getAssSize().getValue(), limit)) {
								sb.append(target.setAssSize(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(hipSizeIncrement, target.getHipSize().getValue(), limit)) {
								sb.append(target.incrementHipSize(assSizeIncrement));
							} else if(isSetToLimit(hipSizeIncrement, target.getHipSize().getValue(), limit)) {
								sb.append(target.setHipSize(limit));
							}
							break;
						case TF_MOD_CAPACITY:
							if(isWithinLimits(capacityIncrement, target.getAssRawCapacityValue(), limit)) {
								sb.append(target.incrementAssCapacity(capacityIncrement, true));
							} else if(isSetToLimit(capacityIncrement, target.getAssRawCapacityValue(), limit)) {
								sb.append(target.setAssCapacity(limit, true));
							}
							break;
						case TF_MOD_ELASTICITY:
							if(isWithinLimits(elasticityIncrement, target.getAssElasticity().getValue(), limit)) {
								sb.append(target.incrementAssElasticity(elasticityIncrement));
							} else if(isSetToLimit(elasticityIncrement, target.getAssElasticity().getValue(), limit)) {
								sb.append(target.setAssElasticity(limit));
							}
							break;
						case TF_MOD_PLASTICITY:
							if(isWithinLimits(plasticityIncrement, target.getAssPlasticity().getValue(), limit)) {
								sb.append(target.incrementAssPlasticity(plasticityIncrement));
							} else if(isSetToLimit(plasticityIncrement, target.getAssPlasticity().getValue(), limit)) {
								sb.append(target.setAssPlasticity(limit));
							}
							break;
						case TF_MOD_WETNESS:
							if(isWithinLimits(wetnessIncrement, target.getAssWetness().getValue(), limit)) {
								sb.append(target.incrementAssWetness(wetnessIncrement));
							} else if(isSetToLimit(wetnessIncrement, target.getAssWetness().getValue(), limit)) {
								sb.append(target.setAssWetness(limit));
							}
							break;
						case TF_MOD_ORIFICE_PUFFY:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasAssOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.addAssOrificeModifier(OrificeModifier.PUFFY));
								}
							} else {
								if(target.hasAssOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.removeAssOrificeModifier(OrificeModifier.PUFFY));
								}
							}
							break;
						case TF_MOD_ORIFICE_RIBBED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasAssOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.addAssOrificeModifier(OrificeModifier.RIBBED));
								}
							} else {
								if(target.hasAssOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.removeAssOrificeModifier(OrificeModifier.RIBBED));
								}
							}
							break;
						case TF_MOD_ORIFICE_MUSCLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							} else {
								if(target.hasAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.removeAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							}
							break;
						case TF_MOD_ORIFICE_TENTACLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasAssOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.addAssOrificeModifier(OrificeModifier.TENTACLED));
								}
							} else {
								if(target.hasAssOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.removeAssOrificeModifier(OrificeModifier.TENTACLED));
								}
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
							} else if(isSetToLimit(breastSizeIncrement, target.getBreastRawSizeValue(), limit)) {
								sb.append(target.setBreastSize(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(nippleSizeIncrement, target.getNippleSize().getValue(), limit)) {
								sb.append(target.incrementNippleSize(nippleSizeIncrement));
							} else if(isSetToLimit(nippleSizeIncrement, target.getNippleSize().getValue(), limit)) {
								sb.append(target.incrementNippleSize(limit));
							}
							break;
						case TF_MOD_SIZE_TERTIARY:
							if(isWithinLimits(areolaeSizeIncrement, target.getAreolaeSize().getValue(), limit)) {
								sb.append(target.incrementAreolaeSize(areolaeSizeIncrement));
							} else if(isSetToLimit(areolaeSizeIncrement, target.getAreolaeSize().getValue(), limit)) {
								sb.append(target.incrementAreolaeSize(limit));
							}
							break;
						case TF_MOD_CAPACITY:
							if(isWithinLimits(capacityIncrement, target.getNippleRawCapacityValue(), limit)) {
								sb.append(target.incrementNippleCapacity(capacityIncrement, true));
							} else if(isSetToLimit(capacityIncrement, target.getNippleRawCapacityValue(), limit)) {
								sb.append(target.setNippleCapacity(limit, true));
							}
							break;
						case TF_MOD_ELASTICITY:
							if(isWithinLimits(elasticityIncrement, target.getNippleElasticity().getValue(), limit)) {
								sb.append(target.incrementNippleElasticity(elasticityIncrement));
							} else if(isSetToLimit(elasticityIncrement, target.getNippleElasticity().getValue(), limit)) {
								sb.append(target.setNippleElasticity(limit));
							}
							break;
						case TF_MOD_PLASTICITY:
							if(isWithinLimits(plasticityIncrement, target.getNipplePlasticity().getValue(), limit)) {
								sb.append(target.incrementNipplePlasticity(plasticityIncrement));
							} else if(isSetToLimit(plasticityIncrement, target.getNipplePlasticity().getValue(), limit)) {
								sb.append(target.setNipplePlasticity(limit));
							}
							break;
						case TF_MOD_WETNESS:
							if(isWithinLimits(lactationIncrement, target.getBreastRawMilkStorageValue(), limit)) {
								sb.append(target.incrementBreastMilkStorage(lactationIncrement));
							} else if(isSetToLimit(lactationIncrement, target.getBreastRawMilkStorageValue(), limit)) {
								sb.append(target.setBreastMilkStorage(limit));
							}
							break;
						case TF_MOD_REGENERATION:
							if(isWithinLimits(fluidRegenerationIncrement, target.getBreastRawLactationRegenerationValue(), limit)) {
								sb.append(target.incrementBreastLactationRegeneration(fluidRegenerationIncrement));
							} else if(isSetToLimit(fluidRegenerationIncrement, target.getBreastRawLactationRegenerationValue(), limit)) {
								sb.append(target.setBreastLactationRegeneration(limit));
							}
							break;
						case TF_MOD_ORIFICE_PUFFY:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.addNippleOrificeModifier(OrificeModifier.PUFFY));
								}
							} else {
								if(target.hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.removeNippleOrificeModifier(OrificeModifier.PUFFY));
								}
							}
							break;
						case TF_MOD_ORIFICE_RIBBED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasNippleOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.addNippleOrificeModifier(OrificeModifier.RIBBED));
								}
							} else {
								if(target.hasNippleOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.removeNippleOrificeModifier(OrificeModifier.RIBBED));
								}
							}
							break;
						case TF_MOD_ORIFICE_MUSCLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.addNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							} else {
								if(target.hasNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.removeNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							}
							break;
						case TF_MOD_ORIFICE_TENTACLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasNippleOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.addNippleOrificeModifier(OrificeModifier.TENTACLED));
								}
							} else {
								if(target.hasNippleOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.removeNippleOrificeModifier(OrificeModifier.TENTACLED));
								}
							}
							break;
						default:
							break;
					}
					break;
				case TF_BREASTS_CROTCH:
					if(target.hasBreastsCrotch()) {
						switch(secondaryModifier) {
							case TF_MOD_SIZE:
								if(isWithinLimits(breastSizeIncrement, target.getBreastCrotchRawSizeValue(), limit)) {
									sb.append(target.incrementBreastCrotchSize(breastSizeIncrement));
								} else if(isSetToLimit(breastSizeIncrement, target.getBreastCrotchRawSizeValue(), limit)) {
									sb.append(target.setBreastCrotchSize(limit));
								}
								break;
							case TF_MOD_SIZE_SECONDARY:
								if(isWithinLimits(nippleSizeIncrement, target.getNippleCrotchSize().getValue(), limit)) {
									sb.append(target.incrementNippleCrotchSize(nippleSizeIncrement));
								} else if(isSetToLimit(nippleSizeIncrement, target.getNippleCrotchSize().getValue(), limit)) {
									sb.append(target.incrementNippleCrotchSize(limit));
								}
								break;
							case TF_MOD_SIZE_TERTIARY:
								if(isWithinLimits(areolaeSizeIncrement, target.getAreolaeCrotchSize().getValue(), limit)) {
									sb.append(target.incrementAreolaeCrotchSize(areolaeSizeIncrement));
								} else if(isSetToLimit(areolaeSizeIncrement, target.getAreolaeCrotchSize().getValue(), limit)) {
									sb.append(target.incrementAreolaeCrotchSize(limit));
								}
								break;
							case TF_MOD_CAPACITY:
								if(isWithinLimits(capacityIncrement, target.getNippleCrotchRawCapacityValue(), limit)) {
									sb.append(target.incrementNippleCrotchCapacity(capacityIncrement, true));
								} else if(isSetToLimit(capacityIncrement, target.getNippleCrotchRawCapacityValue(), limit)) {
									sb.append(target.setNippleCrotchCapacity(limit, true));
								}
								break;
							case TF_MOD_ELASTICITY:
								if(isWithinLimits(elasticityIncrement, target.getNippleCrotchElasticity().getValue(), limit)) {
									sb.append(target.incrementNippleCrotchElasticity(elasticityIncrement));
								} else if(isSetToLimit(elasticityIncrement, target.getNippleCrotchElasticity().getValue(), limit)) {
									sb.append(target.setNippleCrotchElasticity(limit));
								}
								break;
							case TF_MOD_PLASTICITY:
								if(isWithinLimits(plasticityIncrement, target.getNippleCrotchPlasticity().getValue(), limit)) {
									sb.append(target.incrementNippleCrotchPlasticity(plasticityIncrement));
								} else if(isSetToLimit(plasticityIncrement, target.getNippleCrotchPlasticity().getValue(), limit)) {
									sb.append(target.setNippleCrotchPlasticity(limit));
								}
								break;
							case TF_MOD_WETNESS:
								if(isWithinLimits(lactationIncrement, target.getBreastCrotchRawMilkStorageValue(), limit)) {
									sb.append(target.incrementBreastCrotchMilkStorage(lactationIncrement));
								} else if(isSetToLimit(lactationIncrement, target.getBreastCrotchRawMilkStorageValue(), limit)) {
									sb.append(target.setBreastCrotchMilkStorage(limit));
								}
								break;
							case TF_MOD_REGENERATION:
								if(isWithinLimits(fluidRegenerationIncrement, target.getBreastCrotchRawLactationRegenerationValue(), limit)) {
									sb.append(target.incrementBreastCrotchLactationRegeneration(fluidRegenerationIncrement));
								} else if(isSetToLimit(fluidRegenerationIncrement, target.getBreastCrotchRawLactationRegenerationValue(), limit)) {
									sb.append(target.setBreastCrotchLactationRegeneration(limit));
								}
								break;
							case TF_MOD_ORIFICE_PUFFY:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasNippleCrotchOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.addNippleCrotchOrificeModifier(OrificeModifier.PUFFY));
									}
								} else {
									if(target.hasNippleCrotchOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.removeNippleCrotchOrificeModifier(OrificeModifier.PUFFY));
									}
								}
								break;
							case TF_MOD_ORIFICE_RIBBED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasNippleCrotchOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.addNippleCrotchOrificeModifier(OrificeModifier.RIBBED));
									}
								} else {
									if(target.hasNippleCrotchOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.removeNippleCrotchOrificeModifier(OrificeModifier.RIBBED));
									}
								}
								break;
							case TF_MOD_ORIFICE_MUSCLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.addNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								} else {
									if(target.hasNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.removeNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								}
								break;
							case TF_MOD_ORIFICE_TENTACLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasNippleCrotchOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.addNippleCrotchOrificeModifier(OrificeModifier.TENTACLED));
									}
								} else {
									if(target.hasNippleCrotchOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.removeNippleCrotchOrificeModifier(OrificeModifier.TENTACLED));
									}
								}
								break;
							default:
								break;
						}
					}
					break;
				case TF_CORE:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(heightIncrement, target.getHeightValue()-Height.ZERO_TINY.getMinimumValue(), limit)) {
								sb.append(target.incrementHeight(heightIncrement, false));
							} else if(isSetToLimit(heightIncrement, target.getHeightValue()-Height.ZERO_TINY.getMinimumValue(), limit)) {
								sb.append(target.setHeight(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(muscleIncrement, target.getMuscleValue(), limit)) {
								sb.append(target.incrementMuscle(muscleIncrement));
							} else if(isSetToLimit(muscleIncrement, target.getMuscleValue(), limit)) {
								sb.append(target.setMuscle(limit));
							}
							break;
						case TF_MOD_SIZE_TERTIARY:
							if(isWithinLimits(bodySizeIncrement, target.getBodySizeValue(), limit)) {
								sb.append(target.incrementBodySize(bodySizeIncrement));
							} else if(isSetToLimit(bodySizeIncrement, target.getBodySizeValue(), limit)) {
								sb.append(target.setBodySize(limit));
							}
							break;
						case TF_MOD_FEMININITY:
							if(isWithinLimits(femininityIncrement, target.getFemininityValue(), limit)) {
								sb.append(target.incrementFemininity(femininityIncrement));
							} else if(isSetToLimit(femininityIncrement, target.getFemininityValue(), limit)) {
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
							} else if(isSetToLimit(lipSizeIncrement, target.getLipSizeValue(), limit)) {
								sb.append(target.setLipSize(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(lipSizeIncrement, target.getTongueLengthValue(), limit)) {
								sb.append(target.incrementTongueLength(lipSizeIncrement));
							} else if(isSetToLimit(lipSizeIncrement, target.getTongueLengthValue(), limit)) {
								sb.append(target.setTongueLength(limit));
							}
							break;
						case TF_MOD_ORIFICE_PUFFY:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasFaceOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.addFaceOrificeModifier(OrificeModifier.PUFFY));
								}
							} else {
								if(target.hasFaceOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.removeFaceOrificeModifier(OrificeModifier.PUFFY));
								}
							}
							break;
						case TF_MOD_ORIFICE_RIBBED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasFaceOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.addFaceOrificeModifier(OrificeModifier.RIBBED));
								}
							} else {
								if(target.hasFaceOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.removeFaceOrificeModifier(OrificeModifier.RIBBED));
								}
							}
							break;
						case TF_MOD_ORIFICE_MUSCLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.addFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							} else {
								if(target.hasFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.removeFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							}
							break;
						case TF_MOD_ORIFICE_TENTACLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasFaceOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.addFaceOrificeModifier(OrificeModifier.TENTACLED));
								}
							} else {
								if(target.hasFaceOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.removeFaceOrificeModifier(OrificeModifier.TENTACLED));
								}
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
							} else if(isSetToLimit(hairLengthIncrement, target.getHairRawLengthValue(), limit)) {
								sb.append(target.setHairLength(limit));
							}
							break;
						default:
							break;
					}
					break;
				case TF_PENIS:
					if(target.hasPenis()) {
						switch(secondaryModifier) {
							case TF_MOD_SIZE:
								if(isWithinLimits(penisSizeIncrement, target.getPenisRawSizeValue(), limit)) {
									sb.append(target.incrementPenisSize(penisSizeIncrement));
								} else if(isSetToLimit(penisSizeIncrement, target.getPenisRawSizeValue(), limit)) {
									sb.append(target.setPenisSize(limit));
								}
								break;
							case TF_MOD_SIZE_SECONDARY:
								if(isWithinLimits(testicleSizeIncrement, target.getTesticleSize().getValue(), limit)) {
									sb.append(target.incrementTesticleSize(testicleSizeIncrement));
								} else if(isSetToLimit(testicleSizeIncrement, target.getTesticleSize().getValue(), limit)) {
									sb.append(target.setTesticleSize(limit));
								}
								break;
							case TF_MOD_CAPACITY:
								if(isWithinLimits(capacityIncrement, target.getPenisRawCapacityValue(), limit)) {
									sb.append(target.incrementPenisCapacity(capacityIncrement, true));
								} else if(isSetToLimit(capacityIncrement, target.getPenisRawCapacityValue(), limit)) {
									sb.append(target.setPenisCapacity(limit, true));
								}
								break;
							case TF_MOD_ELASTICITY:
								if(isWithinLimits(elasticityIncrement, target.getUrethraElasticity().getValue(), limit)) {
									sb.append(target.incrementUrethraElasticity(elasticityIncrement));
								} else if(isSetToLimit(elasticityIncrement, target.getUrethraElasticity().getValue(), limit)) {
									sb.append(target.setUrethraElasticity(limit));
								}
								break;
							case TF_MOD_PLASTICITY:
								if(isWithinLimits(plasticityIncrement, target.getUrethraPlasticity().getValue(), limit)) {
									sb.append(target.incrementUrethraPlasticity(plasticityIncrement));
								} else if(isSetToLimit(plasticityIncrement, target.getUrethraPlasticity().getValue(), limit)) {
									sb.append(target.setUrethraPlasticity(limit));
								}
								break;
							case TF_MOD_WETNESS:
								if(isWithinLimits(cumStorageIncrement, target.getPenisRawCumStorageValue(), limit)) {
									sb.append(target.incrementPenisCumStorage(cumStorageIncrement));
								} else if(isSetToLimit(cumStorageIncrement, target.getPenisRawCumStorageValue(), limit)) {
									sb.append(target.setPenisCumStorage(limit));
								}
								break;
							case TF_MOD_REGENERATION:
								if(isWithinLimits(fluidRegenerationIncrement, target.getPenisRawCumProductionRegenerationValue(), limit)) {
									sb.append(target.incrementPenisCumProductionRegeneration(fluidRegenerationIncrement));
								} else if(isSetToLimit(fluidRegenerationIncrement, target.getPenisRawCumProductionRegenerationValue(), limit)) {
									sb.append(target.setPenisCumProductionRegeneration(limit));
								}
								break;
							case TF_MOD_CUM_EXPULSION:
								if(isWithinLimits(cumExpulsionIncrement, target.getPenisRawCumExpulsionValue(), limit)) {
									sb.append(target.incrementPenisCumExpulsion(cumExpulsionIncrement));
								} else if(isSetToLimit(cumExpulsionIncrement, target.getPenisRawCumExpulsionValue(), limit)) {
									sb.append(target.setPenisCumExpulsion(limit));
								}
								break;
							case TF_MOD_ORIFICE_PUFFY:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasUrethraOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.addUrethraOrificeModifier(OrificeModifier.PUFFY));
									}
								} else {
									if(target.hasUrethraOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.removeUrethraOrificeModifier(OrificeModifier.PUFFY));
									}
								}
								break;
							case TF_MOD_ORIFICE_RIBBED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasUrethraOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.addUrethraOrificeModifier(OrificeModifier.RIBBED));
									}
								} else {
									if(target.hasUrethraOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.removeUrethraOrificeModifier(OrificeModifier.RIBBED));
									}
								}
								break;
							case TF_MOD_ORIFICE_MUSCLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.addUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								} else {
									if(target.hasUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.removeUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								}
								break;
							case TF_MOD_ORIFICE_TENTACLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasUrethraOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.addUrethraOrificeModifier(OrificeModifier.TENTACLED));
									}
								} else {
									if(target.hasUrethraOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.removeUrethraOrificeModifier(OrificeModifier.TENTACLED));
									}
								}
								break;
							default:
								break;
						}
					}
					break;
				case TF_VAGINA:
					if(target.hasVagina()) {
						switch(secondaryModifier) {
							case TF_MOD_SIZE:
								if(isWithinLimits(clitorisSizeIncrement, target.getVaginaRawClitorisSizeValue(), limit)) {
									sb.append(target.incrementVaginaClitorisSize(clitorisSizeIncrement));
								} else if(isSetToLimit(clitorisSizeIncrement, target.getVaginaRawClitorisSizeValue(), limit)) {
									sb.append(target.setVaginaClitorisSize(limit));
								}
								break;
							case TF_MOD_SIZE_SECONDARY:
								if(isWithinLimits(labiaSizeIncrement, target.getVaginaRawLabiaSizeValue(), limit)) {
									sb.append(target.incrementVaginaLabiaSize(labiaSizeIncrement));
								} else if(isSetToLimit(labiaSizeIncrement, target.getVaginaRawLabiaSizeValue(), limit)) {
									sb.append(target.setVaginaLabiaSize(limit));
								}
								break;
							case TF_MOD_CAPACITY:
								if(isWithinLimits(capacityIncrement, target.getVaginaRawCapacityValue(), limit)) {
									sb.append(target.incrementVaginaCapacity(capacityIncrement, true));
								} else if(isSetToLimit(capacityIncrement, target.getVaginaRawCapacityValue(), limit)) {
									sb.append(target.setVaginaCapacity(limit, true));
								}
								break;
							case TF_MOD_ELASTICITY:
								if(isWithinLimits(elasticityIncrement, target.getVaginaElasticity().getValue(), limit)) {
									sb.append(target.incrementVaginaElasticity(elasticityIncrement));
								} else if(isSetToLimit(elasticityIncrement, target.getVaginaElasticity().getValue(), limit)) {
									sb.append(target.setVaginaElasticity(limit));
								}
								break;
							case TF_MOD_PLASTICITY:
								if(isWithinLimits(plasticityIncrement, target.getVaginaPlasticity().getValue(), limit)) {
									sb.append(target.incrementVaginaPlasticity(plasticityIncrement));
								} else if(isSetToLimit(plasticityIncrement, target.getVaginaPlasticity().getValue(), limit)) {
									sb.append(target.setVaginaPlasticity(limit));
								}
								break;
							case TF_MOD_WETNESS:
								if(isWithinLimits(wetnessIncrement, target.getVaginaWetness().getValue(), limit)) {
									sb.append(target.incrementVaginaWetness(wetnessIncrement));
								} else if(isSetToLimit(wetnessIncrement, target.getVaginaWetness().getValue(), limit)) {
									sb.append(target.setVaginaWetness(limit));
								}
								break;
							case TF_MOD_ORIFICE_PUFFY:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.addVaginaOrificeModifier(OrificeModifier.PUFFY));
									}
								} else {
									if(target.hasVaginaOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.removeVaginaOrificeModifier(OrificeModifier.PUFFY));
									}
								}
								break;
							case TF_MOD_ORIFICE_RIBBED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.addVaginaOrificeModifier(OrificeModifier.RIBBED));
									}
								} else {
									if(target.hasVaginaOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.removeVaginaOrificeModifier(OrificeModifier.RIBBED));
									}
								}
								break;
							case TF_MOD_ORIFICE_MUSCLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								} else {
									if(target.hasVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.removeVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								}
								break;
							case TF_MOD_ORIFICE_TENTACLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.addVaginaOrificeModifier(OrificeModifier.TENTACLED));
									}
								} else {
									if(target.hasVaginaOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.removeVaginaOrificeModifier(OrificeModifier.TENTACLED));
									}
								}
								break;
							case TF_MOD_CAPACITY_2:
								if(isWithinLimits(capacityIncrement, target.getVaginaUrethraRawCapacityValue(), limit)) {
									sb.append(target.incrementVaginaUrethraCapacity(capacityIncrement, true));
								} else if(isSetToLimit(capacityIncrement, target.getVaginaUrethraRawCapacityValue(), limit)) {
									sb.append(target.setVaginaUrethraCapacity(limit, true));
								}
								break;
							case TF_MOD_ELASTICITY_2:
								if(isWithinLimits(elasticityIncrement, target.getVaginaUrethraElasticity().getValue(), limit)) {
									sb.append(target.incrementVaginaUrethraElasticity(elasticityIncrement));
								} else if(isSetToLimit(elasticityIncrement, target.getVaginaUrethraElasticity().getValue(), limit)) {
									sb.append(target.setVaginaUrethraElasticity(limit));
								}
								break;
							case TF_MOD_PLASTICITY_2:
								if(isWithinLimits(plasticityIncrement, target.getVaginaUrethraPlasticity().getValue(), limit)) {
									sb.append(target.incrementVaginaUrethraPlasticity(plasticityIncrement));
								} else if(isSetToLimit(plasticityIncrement, target.getVaginaUrethraPlasticity().getValue(), limit)) {
									sb.append(target.setVaginaUrethraPlasticity(limit));
								}
								break;
							case TF_MOD_ORIFICE_PUFFY_2:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaUrethraOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.addVaginaUrethraOrificeModifier(OrificeModifier.PUFFY));
									}
								} else {
									if(target.hasVaginaUrethraOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.removeVaginaUrethraOrificeModifier(OrificeModifier.PUFFY));
									}
								}
								break;
							case TF_MOD_ORIFICE_RIBBED_2:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaUrethraOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.addVaginaUrethraOrificeModifier(OrificeModifier.RIBBED));
									}
								} else {
									if(target.hasVaginaUrethraOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.removeVaginaUrethraOrificeModifier(OrificeModifier.RIBBED));
									}
								}
								break;
							case TF_MOD_ORIFICE_MUSCLED_2:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.addVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								} else {
									if(target.hasVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.removeVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								}
								break;
							case TF_MOD_ORIFICE_TENTACLED_2:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.addVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED));
									}
								} else {
									if(target.hasVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.removeVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED));
									}
								}
								break;
							default:
								break;
						}
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
		} else if(increment + currentValue > limit) {
			return false;
		}
		return true;
	}
	
	private static boolean isSetToLimit(int increment, float currentValue, int limit) {
		if(increment<0) {
			if(currentValue <= limit) {
				return false;
			}
		} else if(currentValue >= limit) {
			return false;
		}
		return true;
	}
	
	protected static List<String> descriptions = new ArrayList<>();
	protected static List<String> genericAttributeEffectDescription(ResourceRestoration restorationType, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit) {
		descriptions.clear();
		
		switch(secondaryModifier) {
			default:
				switch(potency) {
					case MAJOR_DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsDrain(60, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add("[style.boldBad(-15)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b> to 'potion effects'");
							}
						}
						break;
					case DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsDrain(40, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add("[style.boldBad(-10)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b> to 'potion effects'");
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
								descriptions.add("[style.boldGood(+10)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b> to 'potion effects'");
							}
						}
						break;
					case MAJOR_BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsRestore(60, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add("[style.boldGood(+15)] <b style='color:"+primaryModifier.getAssociatedAttribute().getColour().toWebHexString()+";'>"+primaryModifier.getAssociatedAttribute().getName()+"</b> to 'potion effects'");
							}
						}
						break;
				}
				break;
		}
		
		return descriptions;
	}
	
	private static void addResourceDescriptionsRestore(int value, ResourceRestoration restorationType) {
		switch(restorationType){
			case HEALTH:
					descriptions.add("[style.boldGood(Restores)] "+value+"% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]");
				break;
			case MANA:
					descriptions.add("[style.boldGood(Restores)] "+value+"% [style.boldAura(aura)]");
				break;
			case ALL:
					descriptions.add("[style.boldGood(Restores)] "+value+"% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]");
					descriptions.add("[style.boldGood(Restores)] "+value+"% [style.boldAura(aura)]");
				break;
		}
	}
	private static void addResourceDescriptionsDrain(int value, ResourceRestoration restorationType) {
		switch(restorationType){
			case HEALTH:
					descriptions.add("[style.boldBad(Drains)] "+value+"% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]");
				break;
			case MANA:
					descriptions.add("[style.boldBad(Drains)] "+value+"% [style.boldAura(aura)]");
				break;
			case ALL:
					descriptions.add("[style.boldBad(Drains)] "+value+"% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]");
					descriptions.add("[style.boldBad(Drains)] "+value+"% [style.boldAura(aura)]");
				break;
		}
	}
	
	private static String applyRestoration(GameCharacter target, ResourceRestoration restorationType, float percentage) {
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
				return UtilText.parse(target, "[npc.Name] lets out a satisfied sigh, and you notice that [npc.sheIs] suddenly looking a lot healthier than [npc.she] did just a moment ago.");
			}
		} else {
			if(target.isPlayer()) {
				return "A sickly warmth creeps its way all throughout your body, and with a feeble cough, you find yourself feeling weaker than you did just a moment ago.";
			} else {
				return UtilText.parse(target, "[npc.Name] lets out a feeble cough, and you notice that [npc.sheIs] suddenly looking a little weaker than [npc.she] did just a moment ago.");
			}
		}
	}
	
	protected static String genericAttributeEffect(ResourceRestoration restorationType, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
		switch(secondaryModifier) {
			default:
				switch(potency) {
					case MAJOR_DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							return applyRestoration(target, restorationType, -0.6f);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								return "A sickly wave of arcane energy washes over you..."
										+ "<br/>"
										+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), -15);
							}
						}
						break;
					case DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							return applyRestoration(target, restorationType, -0.4f);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								return "A sickly wave of arcane energy washes over you..."
										+ "<br/>"
										+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), -10);
							}
						}
						break;
					case MINOR_DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							return applyRestoration(target, restorationType, -0.2f);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								return "A sickly wave of arcane energy washes over you..."
										+ "<br/>"
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
										+ "<br/>"
										+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), 5);
							}
						}
						break;
					case BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							return applyRestoration(target, restorationType, 0.4f);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								return "A soothing wave of arcane energy washes over you..."
										+ "<br/>"
										+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), 10);
							}
						}
						break;
					case MAJOR_BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							return applyRestoration(target, restorationType, 0.6f);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								return "A soothing wave of arcane energy washes over you..."
										+ "<br/>"
										+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), 15);
							}
						}
						break;
				}
				break;
		}
		
		return "";
	}
	
	// Caching:
	private static Map<Race, Map<TFModifier, LinkedHashMap<TFModifier, List<TFPotency>>>> racialPrimaryModSecondaryModPotencyGrid = new HashMap<>();
	
	protected static List<TFModifier> getRacialSecondaryModifiers(Race race, TFModifier primaryModifier) {
		if(racialPrimaryModSecondaryModPotencyGrid.containsKey(race) && racialPrimaryModSecondaryModPotencyGrid.get(race).containsKey(primaryModifier)) {
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).keySet());
		} else {
			populateGrid(race, primaryModifier);
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).keySet());
		}
	}
	
	protected static List<TFPotency> getRacialPotencyModifiers(Race race, TFModifier primaryModifier, TFModifier secondaryModifier) {
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
				for(int i=0; i< AntennaType.getAntennaTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				break;
			
			case TF_ARMS:
				for(int i=0; i< ArmType.getArmTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				if(Main.game.isBodyHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_ASS:
				for(int i=0; i< AssType.getAssTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				if(Main.game.isAssHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				break;
				
			case TF_BREASTS: case TF_BREASTS_CROTCH:
				for(int i=0; i< BreastType.getBreastTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				
				if(primaryModifier==TFModifier.TF_BREASTS_CROTCH) {
					secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT_SECONDARY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());

				if(primaryModifier==TFModifier.TF_BREASTS_CROTCH) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_UDDERS, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_ROUND, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_POINTY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_PERKY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_SIDESET, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_WIDE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_NARROW, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_NORMAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_VAGINA, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_LIPS, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_AREOLAE_CIRCLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_AREOLAE_HEART, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_AREOLAE_STAR, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));

				if(Main.getProperties().hasValue(PropertyValue.nipplePenContent)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_REGENERATION, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				break;
				
			case TF_CORE:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FEMININITY, TFPotency.getAllPotencies());
				if(Main.game.isPubicHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_INTERNAL, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				break;
				
			case TF_EARS:
				for(int i=0; i< EarType.getEarTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				break;
				
			case TF_EYES:
				for(int i=0; i< EyeType.getEyeTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_CIRCLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_VERTICAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_HORIZONTAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_HEART, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_STAR, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_CIRCLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_VERTICAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_HORIZONTAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_HEART, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_STAR, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				break;
				
			case TF_FACE:
				for(int i=0; i< FaceType.getFaceTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));

				secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_BIFURCATED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				if(Main.game.isFacialHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_HAIR:
				for(int i=0; i< HairType.getHairTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				break;
				
			case TF_HORNS:
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				for(int i=0; i< RacialBody.valueOfRace(race).getHornTypes(true).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT_SECONDARY, TFPotency.getAllPotencies());
				break;
				
			case TF_LEGS:
				for(int i=0; i< LegType.getLegTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(RacialBody.valueOfRace(race).getLegType().isLegConfigurationAvailable(LegConfiguration.BIPEDAL)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_BIPEDAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(RacialBody.valueOfRace(race).getLegType().isLegConfigurationAvailable(LegConfiguration.TAUR)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_TAUR, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(RacialBody.valueOfRace(race).getLegType().isLegConfigurationAvailable(LegConfiguration.TAIL_LONG)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_TAIL_LONG, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(RacialBody.valueOfRace(race).getLegType().isLegConfigurationAvailable(LegConfiguration.TAIL)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_TAIL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(RacialBody.valueOfRace(race).getLegType().isLegConfigurationAvailable(LegConfiguration.ARACHNID)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_ARACHNID, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(RacialBody.valueOfRace(race).getLegType().isLegConfigurationAvailable(LegConfiguration.CEPHALOPOD)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_CEPHALOPOD, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(RacialBody.valueOfRace(race).getLegType().getFootType().getPermittedFootStructures().contains(FootStructure.PLANTIGRADE)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_FOOT_STRUCTURE_PLANTIGRADE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(RacialBody.valueOfRace(race).getLegType().getFootType().getPermittedFootStructures().contains(FootStructure.DIGITIGRADE)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_FOOT_STRUCTURE_DIGITIGRADE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(RacialBody.valueOfRace(race).getLegType().getFootType().getPermittedFootStructures().contains(FootStructure.UNGULIGRADE)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_FOOT_STRUCTURE_UNGULIGRADE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				break;
				
			case TF_PENIS:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));

				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_BARBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_FLARED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_BLUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_KNOTTED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_PREHENSILE, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_SHEATHED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_TAPERED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_VEINY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_REGENERATION, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_CUM_EXPULSION, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_INTERNAL, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				if(Main.game.isPubicHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				
				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
	
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				}
				break;
				
			case TF_SKIN:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				break;
				
			case TF_TAIL:
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				for(int i=0; i< TailType.getTailTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				break;
				
			case TF_VAGINA:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_BARBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_FLARED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_BLUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_KNOTTED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_PREHENSILE, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_SHEATHED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_TAPERED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_VEINY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());

				if(Main.game.isPubicHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_VAGINA_SQUIRTER, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY_2, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY_2, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY_2, TFPotency.getAllPotencies());
	
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY_2, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED_2, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED_2, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED_2, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				}
				
				break;
				
			case TF_WINGS:
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				for(int i=0; i< WingType.getWingTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				break;
				
			case TF_CUM: case TF_MILK: case TF_MILK_CROTCH: case TF_GIRLCUM:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_ADDICTIVE, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_ALCOHOLIC, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_BUBBLING, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_HALLUCINOGENIC, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_MINERAL_OIL, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_MUSKY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_SLIMY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_STICKY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_VISCOUS, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_BEER, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CHOCOLATE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CUM, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_GIRLCUM, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_MILK, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_HONEY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_MINT, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_PINEAPPLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_BUBBLEGUM, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_STRAWBERRY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CHERRY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_VANILLA, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				break;
				
			default:
				secondaryModPotencyMap.put(TFModifier.NONE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				break;
		}
		
		racialPrimaryModSecondaryModPotencyGrid.put(race, Util.newHashMapOfValues(new Value<>(primaryModifier, secondaryModPotencyMap)));
	}
	
	// And in the comments these words appear: 'My name is Innoxia, creator of smut: Look on my methods, ye Modders, and despair!'
	// Contributor's comment: OH GOD WHY
	// Innoxia's comment: Because you must suffer!
	
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
	private static int largeChangeMinorDrain = -5;
	private static int largeChangeMinorBoost = 5;
	private static int largeChangeBoost = 15;
	private static int largeChangeMajorBoost = 50;

	private static int hugeChangeMajorDrain = -500;
	private static int hugeChangeDrain = -100;
	private static int hugeChangeMinorDrain = -25;
	private static int hugeChangeMinorBoost = 25;
	private static int hugeChangeBoost = 100;
	private static int hugeChangeMajorBoost = 500;
	
	private static int singleDrain = -1;
	private static int singleBoost = 1;
	
	protected static RacialEffectUtil getRacialEffect(Race race, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, GameCharacter user, GameCharacter target) {
		
		switch(primaryModifier) {
			case TF_ANTENNA:
				switch(secondaryModifier) {
					case TF_TYPE_1:
						return new RacialEffectUtil(AntennaType.getAntennaTypes(race).get(0)==AntennaType.NONE?"Removes antennae.":Util.capitaliseSentence(AntennaType.getAntennaTypes(race).get(0).getTransformName())+" antenna transformation.") {
							@Override public String applyEffect() { return target.setAntennaType(AntennaType.getAntennaTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(AntennaType.getAntennaTypes(race).get(1)==AntennaType.NONE?"Removes antennae.":Util.capitaliseSentence(AntennaType.getAntennaTypes(race).get(1).getTransformName())+" antenna transformation.") {
							@Override public String applyEffect() { return target.setAntennaType(AntennaType.getAntennaTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(AntennaType.getAntennaTypes(race).get(2)==AntennaType.NONE?"Removes antennae.":Util.capitaliseSentence(AntennaType.getAntennaTypes(race).get(2).getTransformName())+" antenna transformation.") {
							@Override public String applyEffect() { return target.setAntennaType(AntennaType.getAntennaTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(AntennaType.getAntennaTypes(race).get(3)==AntennaType.NONE?"Removes antennae.":Util.capitaliseSentence(AntennaType.getAntennaTypes(race).get(3).getTransformName())+" antenna transformation.") {
							@Override public String applyEffect() { return target.setAntennaType(AntennaType.getAntennaTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(AntennaType.getAntennaTypes(race).get(4)==AntennaType.NONE?"Removes antennae.":Util.capitaliseSentence(AntennaType.getAntennaTypes(race).get(4).getTransformName())+" antenna transformation.") {
							@Override public String applyEffect() { return target.setAntennaType(AntennaType.getAntennaTypes(race).get(4)); } };
						
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of antennae.") { @Override public String applyEffect() { return target.incrementAntennaRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of antennae.") { @Override public String applyEffect() {
									if(target.getAntennaType()==AntennaType.NONE && RacialBody.valueOfRace(race).getAntennaType()!=AntennaType.NONE) {
										return target.setAntennaType(RacialBody.valueOfRace(race).getAntennaType());
									} else {
										return target.incrementAntennaRows(singleBoost);
									} } };
						}
					default:
						if(RacialBody.valueOfRace(race).getAntennaType() == AntennaType.NONE) {
							return new RacialEffectUtil("Removes antennae.") { @Override public String applyEffect() { return target.setAntennaType(RacialBody.valueOfRace(race).getAntennaType()); } };
						} else {
							return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" antennae transformation.") { @Override public String applyEffect() { return target.setAntennaType(RacialBody.valueOfRace(race).getAntennaType()); } };
						}
				}
			
			case TF_ARMS:
				switch(secondaryModifier) {
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(ArmType.getArmTypes(race).get(0).getTransformName())+" arm transformation.") {
							@Override public String applyEffect() { return target.setArmType(ArmType.getArmTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(ArmType.getArmTypes(race).get(1).getTransformName())+" arm transformation.") {
							@Override public String applyEffect() { return target.setArmType(ArmType.getArmTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(ArmType.getArmTypes(race).get(2).getTransformName())+" arm transformation.") {
							@Override public String applyEffect() { return target.setArmType(ArmType.getArmTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(ArmType.getArmTypes(race).get(3).getTransformName())+" arm transformation.") {
							@Override public String applyEffect() { return target.setArmType(ArmType.getArmTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(ArmType.getArmTypes(race).get(4).getTransformName())+" arm transformation.") {
							@Override public String applyEffect() { return target.setArmType(ArmType.getArmTypes(race).get(4)); } };
						
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of arms.") { @Override public String applyEffect() { return target.incrementArmRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of arms.") { @Override public String applyEffect() { return target.incrementArmRows(singleBoost); } };
						}
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Removes a huge amount of underarm hair. (" + smallChangeMajorDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Removes a large amount of underarm hair. (" + smallChangeDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes some underarm hair. (" + smallChangeMinorDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds some underarm hair. (+" + smallChangeMinorBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Adds a large amount of underarm hair. (+" + smallChangeBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Adds a huge amount of underarm hair. (+" + smallChangeMajorBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeMajorBoost); } };
						}
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" arm transformation.") { @Override public String applyEffect() { return target.setArmType(RacialBody.valueOfRace(race).getArmType()); } };
				}
				
			case TF_ASS:
				switch(secondaryModifier) {
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(AssType.getAssTypes(race).get(0).getTransformName())+" ass transformation.") {
							@Override public String applyEffect() { return target.setAssType(AssType.getAssTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(AssType.getAssTypes(race).get(1).getTransformName())+" ass transformation.") {
							@Override public String applyEffect() { return target.setAssType(AssType.getAssTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(AssType.getAssTypes(race).get(2).getTransformName())+" ass transformation.") {
							@Override public String applyEffect() { return target.setAssType(AssType.getAssTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(AssType.getAssTypes(race).get(3).getTransformName())+" ass transformation.") {
							@Override public String applyEffect() { return target.setAssType(AssType.getAssTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(AssType.getAssTypes(race).get(4).getTransformName())+" ass transformation.") {
							@Override public String applyEffect() { return target.setAssType(AssType.getAssTypes(race).get(4)); } };
						
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in ass size. (" + smallChangeMajorDrain + " ass size)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in ass size. (" + smallChangeDrain + " ass size)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in ass size. (" + smallChangeMinorDrain + " ass size)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in ass size. (+" + smallChangeMinorBoost + " ass size)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in ass size. (+" + smallChangeBoost + " ass size)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in ass size. (+" + smallChangeMajorBoost + " ass size)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in hip size. (" + smallChangeMajorDrain + " hip size)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in hip size. (" + smallChangeDrain + " hip size)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in hip size. (" + smallChangeMinorDrain + " hip size)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in hip size. (+" + smallChangeMinorBoost + " hip size)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in hip size. (+" + smallChangeBoost + " hip size)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in hip size. (+" + smallChangeMajorBoost + " hip size)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Removes a huge amount of ass hair. (" + smallChangeMajorDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Removes a large amount of ass hair. (" + smallChangeDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes some ass hair. (" + smallChangeMinorDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds some ass hair. (+" + smallChangeMinorBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Adds a large amount of ass hair. (+" + smallChangeBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Adds a huge amount of ass hair. (+" + smallChangeMajorBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in anal capacity. (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in anal capacity. (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in anal capacity. (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in anal capacity. (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in anal capacity. (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in anal capacity. (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in anal elasticity. (" + smallChangeMajorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in anal elasticity. (" + smallChangeDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in anal elasticity. (" + smallChangeMinorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in anal elasticity. (+" + smallChangeMinorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in anal elasticity. (+" + smallChangeBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in anal elasticity. (+" + smallChangeMajorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in anal plasticity. (" + smallChangeMajorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in anal plasticity. (" + smallChangeDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in anal plasticity. (" + smallChangeMinorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in anal plasticity. (+" + smallChangeMinorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in anal plasticity. (+" + smallChangeBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in anal plasticity. (+" + smallChangeMajorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in natural anal lubrication. (" + smallChangeMajorDrain + " wetness)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in natural anal lubrication. (" + smallChangeDrain + " wetness)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in natural anal lubrication. (" + smallChangeMinorDrain + " wetness)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in natural anal lubrication. (+" + smallChangeMinorBoost + " wetness)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in natural anal lubrication. (+" + smallChangeBoost + " wetness)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in natural anal lubrication. (+" + smallChangeMajorBoost + " wetness)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMajorBoost); } };
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
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" ass transformation.") { @Override public String applyEffect() { return target.setAssType(RacialBody.valueOfRace(race).getAssType()); } };
				}
				
			case TF_BREASTS:
				switch(secondaryModifier) {
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(0).getTransformName())+" breast transformation.") {
							@Override public String applyEffect() { return target.setBreastType(BreastType.getBreastTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(1).getTransformName())+" breast transformation.") {
							@Override public String applyEffect() { return target.setBreastType(BreastType.getBreastTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(2).getTransformName())+" breast transformation.") {
							@Override public String applyEffect() { return target.setBreastType(BreastType.getBreastTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(3).getTransformName())+" breast transformation.") {
							@Override public String applyEffect() { return target.setBreastType(BreastType.getBreastTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(4).getTransformName())+" breast transformation.") {
							@Override public String applyEffect() { return target.setBreastType(BreastType.getBreastTypes(race).get(4)); } };
						
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of breasts.") { @Override public String applyEffect() { return target.incrementBreastRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of breasts.") { @Override public String applyEffect() { return target.incrementBreastRows(singleBoost); } };
						}
					case TF_MOD_COUNT_SECONDARY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra nipple from each breast.") { @Override public String applyEffect() { return target.incrementNippleCountPerBreast(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra nipple to each breast.") { @Override public String applyEffect() { return target.incrementNippleCountPerBreast(singleBoost); } };
						}
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in breast size. (" + smallChangeMajorDrain + " breast size)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in breast size. (" + smallChangeDrain + " breast size)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in breast size. (" + smallChangeMinorDrain + " breast size)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in breast size. (+" + smallChangeMinorBoost + " breast size)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in breast size. (+" + smallChangeBoost + " breast size)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in breast size. (+" + smallChangeMajorBoost + " breast size)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMajorBoost); } };
						}
						

					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in nipple size. (" + smallChangeMajorDrain + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in nipple size. (" + smallChangeDrain + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in nipple size. (" + smallChangeMinorDrain + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in nipple size. (+" + smallChangeMinorBoost + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in nipple size. (+" + smallChangeBoost + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in nipple size. (+" + smallChangeMajorBoost + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMajorBoost); } };
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
								return new RacialEffectUtil("Huge decrease in areolae size. (" + smallChangeMajorDrain + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in areolae size. (" + smallChangeDrain + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in areolae size. (" + smallChangeMinorDrain + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in areolae size. (+" + smallChangeMinorBoost + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in areolae size. (+" + smallChangeBoost + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in areolae size. (+" + smallChangeMajorBoost + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMajorBoost); } };
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
								return new RacialEffectUtil("Huge decrease in nipple capacity. (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in nipple capacity. (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in nipple capacity. (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in nipple capacity. (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in nipple capacity. (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in nipple capacity. (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in nipple elasticity. (" + smallChangeMajorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in nipple elasticity. (" + smallChangeDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in nipple elasticity. (" + smallChangeMinorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in nipple elasticity. (+" + smallChangeMinorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in nipple elasticity. (+" + smallChangeBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in nipple elasticity. (+" + smallChangeMajorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in nipple plasticity. (" + smallChangeMajorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in nipple plasticity. (" + smallChangeDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in nipple plasticity. (" + smallChangeMinorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in nipple plasticity. (+" + smallChangeMinorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in nipple plasticity. (+" + smallChangeBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in nipple plasticity. (+" + smallChangeMajorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in milk storage. (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in milk storage. (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in milk storage. (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in milk storage. (+" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in milk storage. (+" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in milk storage. (+" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorBoost); } };
						}
					case TF_MOD_REGENERATION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in milk regeneration.") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in milk regeneration.") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in milk regeneration.") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in milk regeneration.") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in milk regeneration.") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in milk regeneration.") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeMajorBoost); } };
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
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" breast transformation.") { @Override public String applyEffect() { return target.setBreastType(RacialBody.valueOfRace(race).getBreastType()); } };
				}

			case TF_BREASTS_CROTCH:
				switch(secondaryModifier) {
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(0).getTransformName())+" crotch-boob transformation.") {
							@Override public String applyEffect() { return target.setBreastCrotchType(BreastType.getBreastTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(1).getTransformName())+" crotch-boob transformation.") {
							@Override public String applyEffect() { return target.setBreastCrotchType(BreastType.getBreastTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(2).getTransformName())+" crotch-boob transformation.") {
							@Override public String applyEffect() { return target.setBreastCrotchType(BreastType.getBreastTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(3).getTransformName())+" crotch-boob transformation.") {
							@Override public String applyEffect() { return target.setBreastCrotchType(BreastType.getBreastTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(4).getTransformName())+" crotch-boob transformation.") {
							@Override public String applyEffect() { return target.setBreastCrotchType(BreastType.getBreastTypes(race).get(4)); } };

					case REMOVAL:
						return new RacialEffectUtil("Removes crotch-boobs.") {
							@Override public String applyEffect() { return target.setBreastCrotchType(BreastType.NONE); } };
							
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of crotch-boobs.") { @Override public String applyEffect() { return target.incrementBreastCrotchRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of crotch-boobs.") { @Override public String applyEffect() { return target.incrementBreastCrotchRows(singleBoost); } };
						}
					case TF_MOD_COUNT_SECONDARY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra nipple from each crotch-boob.") { @Override public String applyEffect() {
									return target.incrementNippleCrotchCountPerBreast(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra nipple to each crotch-boob.") { @Override public String applyEffect() {
									return target.incrementNippleCrotchCountPerBreast(singleBoost); } };
						}
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in crotch-boob size. (" + smallChangeMajorDrain + " breast size)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in crotch-boob size. (" + smallChangeDrain + " breast size)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in crotch-boob size. (" + smallChangeMinorDrain + " breast size)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in crotch-boob size. (" + smallChangeMinorBoost + " breast size)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in crotch-boob size. (" + smallChangeBoost + " breast size)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in crotch-boob size. (" + smallChangeMajorBoost + " breast size)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeMajorBoost); } };
						}
						

					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in nipple size. (" + smallChangeMajorDrain + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in nipple size. (" + smallChangeDrain + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in nipple size. (" + smallChangeMinorDrain + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in nipple size. (" + smallChangeMinorBoost + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in nipple size. (" + smallChangeBoost + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in nipple size. (" + smallChangeMajorBoost + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeMajorBoost); } };
						}

					case TF_MOD_BREAST_SHAPE_UDDERS:
						return new RacialEffectUtil("Transforms crotch-boobs into udders.") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.UDDERS); } };
					case TF_MOD_BREAST_SHAPE_ROUND:
						return new RacialEffectUtil("Transforms crotch-boob shape into being round.") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.ROUND); } };
					case TF_MOD_BREAST_SHAPE_PERKY:
						return new RacialEffectUtil("Transforms crotch-boob shape into being perky.") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.PERKY); } };
					case TF_MOD_BREAST_SHAPE_POINTY:
						return new RacialEffectUtil("Transforms crotch-boob shape into being pointy.") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.POINTY); } };
					case TF_MOD_BREAST_SHAPE_SIDESET:
						return new RacialEffectUtil("Transforms crotch-boob shape into being sideset.") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.SIDE_SET); } };
					case TF_MOD_BREAST_SHAPE_WIDE:
						return new RacialEffectUtil("Transforms crotch-boob shape into being wide.") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.WIDE); } };
					case TF_MOD_BREAST_SHAPE_NARROW:
						return new RacialEffectUtil("Transforms crotch-boob shape into being narrow.") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.NARROW); } };
						
					case TF_MOD_NIPPLE_NORMAL:
						return new RacialEffectUtil("Turns nipples into a normal, human-like shape.") { @Override public String applyEffect() { return target.setNippleCrotchShape(NippleShape.NORMAL); } };
					case TF_MOD_NIPPLE_VAGINA:
						return new RacialEffectUtil("Turns nipples into vaginas.") { @Override public String applyEffect() { return target.setNippleCrotchShape(NippleShape.VAGINA); } };
					case TF_MOD_NIPPLE_LIPS:
						return new RacialEffectUtil("Turns nipples into pairs of lips.") { @Override public String applyEffect() { return target.setNippleCrotchShape(NippleShape.LIPS); } };
						
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in areolae size. (" + smallChangeMajorDrain + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in areolae size. (" + smallChangeDrain + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in areolae size. (" + smallChangeMinorDrain + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in areolae size. (" + smallChangeMinorBoost + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in areolae size. (" + smallChangeBoost + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in areolae size. (" + smallChangeMajorBoost + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeMajorBoost); } };
						}
					case TF_MOD_AREOLAE_CIRCLE:
						return new RacialEffectUtil("Turns the shape of areolae into normal circles.") { @Override public String applyEffect() { return target.setAreolaeCrotchShape(AreolaeShape.NORMAL); } };
					case TF_MOD_AREOLAE_HEART:
						return new RacialEffectUtil("Turns the shape of areolae into hearts.") { @Override public String applyEffect() { return target.setAreolaeCrotchShape(AreolaeShape.HEART); } };
					case TF_MOD_AREOLAE_STAR:
						return new RacialEffectUtil("Turns the shape of areolae into stars.") { @Override public String applyEffect() { return target.setAreolaeCrotchShape(AreolaeShape.STAR); } };
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in nipple capacity. (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in nipple capacity. (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in nipple capacity. (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in nipple capacity. (" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in nipple capacity. (" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in nipple capacity. (" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in nipple elasticity. (" + smallChangeMajorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in nipple elasticity. (" + smallChangeDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in nipple elasticity. (" + smallChangeMinorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in nipple elasticity. (" + smallChangeMinorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in nipple elasticity. (" + smallChangeBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in nipple elasticity. (" + smallChangeMajorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in nipple plasticity. (" + smallChangeMajorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in nipple plasticity. (" + smallChangeDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in nipple plasticity. (" + smallChangeMinorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in nipple plasticity. (" + smallChangeMinorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in nipple plasticity. (" + smallChangeBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in nipple plasticity. (" + smallChangeMajorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in milk storage. (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in milk storage. (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in milk storage. (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in milk storage. (" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in milk storage. (" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in milk storage. (" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMajorBoost); } };
						}
					case TF_MOD_REGENERATION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in milk regeneration.") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in milk regeneration.") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in milk regeneration.") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in milk regeneration.") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in milk regeneration.") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in milk regeneration.") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes puffiness from nipples.") { @Override public String applyEffect() { return target.removeNippleCrotchOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes nipples extra puffy.") { @Override public String applyEffect() { return target.addNippleCrotchOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal ribbing from nipples.") { @Override public String applyEffect() { return target.removeNippleCrotchOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal ribbing to nipples.") { @Override public String applyEffect() { return target.addNippleCrotchOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal muscles from nipples.") { @Override public String applyEffect() { return target.removeNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal muscles to nipples.") { @Override public String applyEffect() { return target.addNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal tentacles from nipples.") { @Override public String applyEffect() { return target.removeNippleCrotchOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal tentacles to nipples.") { @Override public String applyEffect() { return target.addNippleCrotchOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" crotch-boob transformation.") { @Override public String applyEffect() {
							return target.setBreastCrotchType(RacialBody.valueOfRace(race).getBreastType()); } };
				}
				
			case TF_CORE: 
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in height. (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMajorDrain, false); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in height. (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeDrain, false); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in height. (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMinorDrain, false); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in height. (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMinorBoost, false); } };
							case BOOST:
								return new RacialEffectUtil("Increase in height. (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeBoost, false); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in height. (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMajorBoost, false); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in muscle mass. (" + mediumChangeMajorDrain + " muscles)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in muscle mass. (" + mediumChangeDrain + " muscles)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in muscle mass. (" + mediumChangeMinorDrain + " muscles)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in muscle mass. (+" + mediumChangeMinorBoost + " muscles)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in muscle mass. (+" + mediumChangeBoost + " muscles)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in muscle mass. (+" + mediumChangeMajorBoost + " muscles)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in body size. (" + mediumChangeMajorDrain + " body size)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in body size. (" + mediumChangeDrain + " body size)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in body size. (" + mediumChangeMinorDrain + " body size)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in body size. (+" + mediumChangeMinorBoost + " body size)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in body size. (+" + mediumChangeBoost + " body size)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in body size. (+" + mediumChangeMajorBoost + " body size)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMajorBoost); } };
						}
					case TF_MOD_FEMININITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in femininity. (" + mediumChangeMajorDrain + " femininity)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in femininity. (" + mediumChangeDrain + " femininity)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in femininity. (" + mediumChangeMinorDrain + " femininity)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in femininity. (+" + mediumChangeMinorBoost + " femininity)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in femininity. (+" + mediumChangeBoost + " femininity)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in femininity. (+" + mediumChangeMajorBoost + " femininity)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMajorBoost); } };
						}
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Removes a huge amount of pubic hair. (" + smallChangeMajorDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Removes a large amount of pubic hair. (" + smallChangeDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes some pubic hair. (" + smallChangeMinorDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds some pubic hair. (+" + smallChangeMinorBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Adds a large amount of pubic hair. (+" + smallChangeBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Adds a huge amount of pubic hair. (+" + smallChangeMajorBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorBoost); } };
						}
					case TF_MOD_INTERNAL:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes cloaca, making genitals and asshole external.") { @Override public String applyEffect() { return target.setGenitalArrangement(GenitalArrangement.NORMAL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Moves genitals and asshole into internal slit-like cloaca.") { @Override public String applyEffect() { return target.setGenitalArrangement(GenitalArrangement.CLOACA); } };
						}
						
					default:
						return new RacialEffectUtil("Random "+race.getName(false)+" transformation") {
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
						return new RacialEffectUtil(Util.capitaliseSentence(EarType.getEarTypes(race).get(0).getTransformName())+" ears transformation.") {
							@Override public String applyEffect() { return target.setEarType(EarType.getEarTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(EarType.getEarTypes(race).get(1).getTransformName())+" ears transformation.") {
							@Override public String applyEffect() { return target.setEarType(EarType.getEarTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(EarType.getEarTypes(race).get(2).getTransformName())+" ears transformation.") {
							@Override public String applyEffect() { return target.setEarType(EarType.getEarTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(EarType.getEarTypes(race).get(3).getTransformName())+" ears transformation.") {
							@Override public String applyEffect() { return target.setEarType(EarType.getEarTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(EarType.getEarTypes(race).get(4).getTransformName())+" ears transformation.") {
							@Override public String applyEffect() { return target.setEarType(EarType.getEarTypes(race).get(4)); } };
							
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" ears transformation.") { @Override public String applyEffect() { return target.setEarType(RacialBody.valueOfRace(race).getEarType()); } };
				}
				
			case TF_EYES:
				switch(secondaryModifier) {
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(EyeType.getEyeTypes(race).get(0).getTransformName())+" eyes transformation.") {
							@Override public String applyEffect() { return target.setEyeType(EyeType.getEyeTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(EyeType.getEyeTypes(race).get(1).getTransformName())+" eyes transformation.") {
							@Override public String applyEffect() { return target.setEyeType(EyeType.getEyeTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(EyeType.getEyeTypes(race).get(2).getTransformName())+" eyes transformation.") {
							@Override public String applyEffect() { return target.setEyeType(EyeType.getEyeTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(EyeType.getEyeTypes(race).get(3).getTransformName())+" eyes transformation.") {
							@Override public String applyEffect() { return target.setEyeType(EyeType.getEyeTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(EyeType.getEyeTypes(race).get(4).getTransformName())+" eyes transformation.") {
							@Override public String applyEffect() { return target.setEyeType(EyeType.getEyeTypes(race).get(4)); } };
						
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of eyes.") { @Override public String applyEffect() { return target.incrementEyePairs(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of eyes.") { @Override public String applyEffect() { return target.incrementEyePairs(singleBoost); } };
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
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" eyes transformation.") { @Override public String applyEffect() { return target.setEyeType(RacialBody.valueOfRace(race).getEyeType()); } };
				}
				
			case TF_FACE:
				switch(secondaryModifier) {
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(FaceType.getFaceTypes(race).get(0).getTransformName())+" face transformation.") {
							@Override public String applyEffect() { return target.setFaceType(FaceType.getFaceTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(FaceType.getFaceTypes(race).get(1).getTransformName())+" face transformation.") {
							@Override public String applyEffect() { return target.setFaceType(FaceType.getFaceTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(FaceType.getFaceTypes(race).get(2).getTransformName())+" face transformation.") {
							@Override public String applyEffect() { return target.setFaceType(FaceType.getFaceTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(FaceType.getFaceTypes(race).get(3).getTransformName())+" face transformation.") {
							@Override public String applyEffect() { return target.setFaceType(FaceType.getFaceTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(FaceType.getFaceTypes(race).get(4).getTransformName())+" face transformation.") {
							@Override public String applyEffect() { return target.setFaceType(FaceType.getFaceTypes(race).get(4)); } };
						
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in lip size. (" + smallChangeMajorDrain + " lip size)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in lip size. (" + smallChangeDrain + " lip size)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in lip size. (" + smallChangeMinorDrain + " lip size)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in lip size. (+" + smallChangeMinorBoost + " lip size)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in lip size. (+" + smallChangeBoost + " lip size)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in lip size. (+" + smallChangeMajorBoost + " lip size)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMajorBoost); } };
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
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in throat capacity. (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in throat capacity. (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in throat capacity. (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in throat capacity. (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in throat capacity. (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in throat capacity. (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in throat elasticity. (" + smallChangeMajorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in throat elasticity. (" + smallChangeDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in throat elasticity. (" + smallChangeMinorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in throat elasticity. (+" + smallChangeMinorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in throat elasticity. (+" + smallChangeBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in throat elasticity. (+" + smallChangeMajorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in throat plasticity. (" + smallChangeMajorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in throat plasticity. (" + smallChangeDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in throat plasticity. (" + smallChangeMinorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in throat plasticity. (+" + smallChangeMinorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in throat plasticity. (+" + smallChangeBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in throat plasticity. (+" + smallChangeMajorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in saliva production. (" + smallChangeMajorDrain + " wetness)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in saliva production. (" + smallChangeDrain + " wetness)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in saliva production. (" + smallChangeMinorDrain + " wetness)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in saliva production. (+" + smallChangeMinorBoost + " wetness)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in saliva production. (+" + smallChangeBoost + " wetness)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in saliva production. (+" + smallChangeMajorBoost + " wetness)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in tongue length. (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in tongue length. (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in tongue length. (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in tongue length. (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in tongue length. (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in tongue length. (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMajorBoost); } };
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
								return new RacialEffectUtil("Removes a huge amount of facial hair. (" + smallChangeMajorDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Removes a large amount of facial hair. (" + smallChangeDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes some facial hair. (" + smallChangeMinorDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds some facial hair. (+" + smallChangeMinorBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Adds a large amount of facial hair. (+" + smallChangeBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Adds a huge amount of facial hair. (+" + smallChangeMajorBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeMajorBoost); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" face transformation.") { @Override public String applyEffect() { return target.setFaceType(RacialBody.valueOfRace(race).getFaceType()); } };
				}
				
			case TF_HAIR:
				switch(secondaryModifier) {
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(HairType.getHairTypes(race).get(0).getTransformName())+" hair transformation.") {
							@Override public String applyEffect() { return target.setHairType(HairType.getHairTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(HairType.getHairTypes(race).get(1).getTransformName())+" hair transformation.") {
							@Override public String applyEffect() { return target.setHairType(HairType.getHairTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(HairType.getHairTypes(race).get(2).getTransformName())+" hair transformation.") {
							@Override public String applyEffect() { return target.setHairType(HairType.getHairTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(HairType.getHairTypes(race).get(3).getTransformName())+" hair transformation.") {
							@Override public String applyEffect() { return target.setHairType(HairType.getHairTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(HairType.getHairTypes(race).get(4).getTransformName())+" hair transformation.") {
							@Override public String applyEffect() { return target.setHairType(HairType.getHairTypes(race).get(4)); } };
						
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in hair length. (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in hair length. (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in hair length. (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in hair length. (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in hair length. (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in hair length. (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMajorBoost); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" hair transformation.") { @Override public String applyEffect() { return target.setHairType(RacialBody.valueOfRace(race).getHairType()); } };
				}
				
			case TF_HORNS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in horn length. (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in horn length. (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in horn length. (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in horn length. (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in horn length. (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in horn length. (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMajorBoost); } };
						}
				
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of horns.") { @Override public String applyEffect() { return target.incrementHornRows(singleDrain); } };
							case MINOR_BOOST: default://TODO
								return new RacialEffectUtil("Adds an extra pair of horns.") { @Override public String applyEffect() {
									List<AbstractHornType> hornTypesSuitableForTransformation = RacialBody.valueOfRace(race).getHornTypes(true);
									if(target.getHornType().equals(HornType.NONE) && !hornTypesSuitableForTransformation.isEmpty()) {
										return target.setHornType(hornTypesSuitableForTransformation.get(0));
									} else {
										return target.incrementHornRows(singleBoost);
									} } };
						}
						
					case TF_MOD_COUNT_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in horns per row. (" + smallChangeMajorDrain + " horns per row)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in horns per row. (" + smallChangeDrain + " horns per row)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in horns per row. (" + smallChangeMinorDrain + " horns per row)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in horns per row. (+" + smallChangeMinorBoost + " horns per row)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in horns per row. (+" + smallChangeBoost + " horns per row)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in horns per row. (+" + smallChangeMajorBoost + " horns per row)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeMajorBoost); } };
						}
						
					case REMOVAL:
						return new RacialEffectUtil("Removes horns.") { @Override public String applyEffect() { return target.setHornType(HornType.NONE); } };
						
					case TF_TYPE_1:
						return getHornTypeRacialEffectUtil(race, target, 0);

					case TF_TYPE_2:
						return getHornTypeRacialEffectUtil(race, target, 1);

					case TF_TYPE_3:
						return getHornTypeRacialEffectUtil(race, target, 2);

					case TF_TYPE_4:
						return getHornTypeRacialEffectUtil(race, target, 3);

					case TF_TYPE_5:
						return getHornTypeRacialEffectUtil(race, target, 4);
							
					default:
						List<AbstractHornType> hornTypes = RacialBody.valueOfRace(race).getHornTypes(true);
						AbstractHornType hornType = hornTypes.isEmpty()?HornType.NONE:Util.randomItemFrom(hornTypes);
						return new RacialEffectUtil(hornType.equals(HornType.NONE)?"Removes horns.":Util.capitaliseSentence(race.getName(false))+" horn transformation.") {
							@Override public String applyEffect() { return target.setHornType(hornType); } };
				}
				
			case TF_LEGS:
				switch(secondaryModifier) {
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(LegType.getLegTypes(race).get(0).getTransformName())+" legs transformation.") {
							@Override public String applyEffect() { return target.setLegType(LegType.getLegTypes(race).get(0)); } };
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(LegType.getLegTypes(race).get(1).getTransformName())+" legs transformation.") {
							@Override public String applyEffect() { return target.setLegType(LegType.getLegTypes(race).get(1)); } };
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(LegType.getLegTypes(race).get(2).getTransformName())+" legs transformation.") {
							@Override public String applyEffect() { return target.setLegType(LegType.getLegTypes(race).get(2)); } };
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(LegType.getLegTypes(race).get(3).getTransformName())+" legs transformation.") {
							@Override public String applyEffect() { return target.setLegType(LegType.getLegTypes(race).get(3)); } };
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(LegType.getLegTypes(race).get(4).getTransformName())+" legs transformation.") {
							@Override public String applyEffect() { return target.setLegType(LegType.getLegTypes(race).get(4)); } };

					case TF_MOD_LEG_CONFIG_BIPEDAL:
						return new RacialEffectUtil(" Transforms legs to bipedal "+race.getName(false)+"'s.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType().applyLegConfigurationTransformation(target, LegConfiguration.BIPEDAL, true, false); } };

					case TF_MOD_LEG_CONFIG_TAUR:
						return new RacialEffectUtil(" Transforms lower body to a quadrupedal, feral "+race.getName(true)+"'s.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType().applyLegConfigurationTransformation(target, LegConfiguration.TAUR, true, false); } };

					case TF_MOD_LEG_CONFIG_TAIL_LONG:
						return new RacialEffectUtil(" Transforms lower body to a long-tailed, feral "+race.getName(true)+"'s.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType().applyLegConfigurationTransformation(target, LegConfiguration.TAIL_LONG, true, false); } };

					case TF_MOD_LEG_CONFIG_TAIL:
						return new RacialEffectUtil(" Transforms lower body to a tailed, feral "+race.getName(true)+"'s.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType().applyLegConfigurationTransformation(target, LegConfiguration.TAIL, true, false); } };

					case TF_MOD_LEG_CONFIG_ARACHNID:
						return new RacialEffectUtil(" Transforms lower body to an eight-legged, feral "+race.getName(true)+"'s.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType().applyLegConfigurationTransformation(target, LegConfiguration.ARACHNID, true, false); } };

					case TF_MOD_LEG_CONFIG_CEPHALOPOD:
						return new RacialEffectUtil(" Transforms lower body to an eight-tentacled, feral "+race.getName(true)+"'s.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType().applyLegConfigurationTransformation(target, LegConfiguration.CEPHALOPOD, true, false); } };
							

					case TF_MOD_FOOT_STRUCTURE_PLANTIGRADE:
						return new RacialEffectUtil(" Transforms foot structure to be plantigrade.") {
							@Override public String applyEffect() { return target.setFootStructure(FootStructure.PLANTIGRADE); } };

					case TF_MOD_FOOT_STRUCTURE_DIGITIGRADE:
						return new RacialEffectUtil(" Transforms foot structure to be digitigrade.") {
							@Override public String applyEffect() { return target.setFootStructure(FootStructure.DIGITIGRADE); } };

					case TF_MOD_FOOT_STRUCTURE_UNGULIGRADE:
						return new RacialEffectUtil(" Transforms foot structure to be unguligrade.") {
							@Override public String applyEffect() { return target.setFootStructure(FootStructure.UNGULIGRADE); } };
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" legs transformation.") { @Override public String applyEffect() { return target.setLegType(RacialBody.valueOfRace(race).getLegType()); } };
				}
				
			case TF_PENIS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in penis size. (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in penis size. (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in penis size. (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in penis size. (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in penis size. (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in penis size. (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in penis girth. (" + smallChangeMajorDrain + " girth)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in penis girth. (" + smallChangeDrain + " size)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in penis girth. (" + smallChangeMinorDrain + " girth)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in penis girth. (+" + smallChangeMinorBoost + " girth)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in penis girth. (+" + smallChangeBoost + " girth)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in penis girth. (+" + smallChangeMajorBoost + " girth)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMajorBoost); } };
						}
					case REMOVAL:
							return new RacialEffectUtil("Removes penis.") { @Override public String applyEffect() { return target.setPenisType(PenisType.NONE); } };

					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Removes a huge amount of pubic hair. (" + smallChangeMajorDrain + " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Removes a large amount of pubic hair. (" + smallChangeDrain + " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes some pubic hair. (" + smallChangeMinorDrain + " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds some pubic hair. (" + smallChangeMinorBoost + " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Adds a large amount of pubic hair. (" + smallChangeBoost + " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Adds a huge amount of pubic hair. (" + smallChangeMajorBoost + " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_PENIS_BARBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes barbs from penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.BARBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds barbs to penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.BARBED); } };
						}
					case TF_MOD_PENIS_FLARED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes flare from penis head.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.FLARED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds flare to penis head.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.FLARED); } };
						}
					case TF_MOD_PENIS_BLUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes blunted head from penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.BLUNT); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes penis head blunt.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.BLUNT); } };
						}
					case TF_MOD_PENIS_KNOTTED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes knot from base of penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.KNOTTED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds knot to base of penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.KNOTTED); } };
						}
					case TF_MOD_PENIS_PREHENSILE:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes prehensility from penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.PREHENSILE); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds prehensility to penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.PREHENSILE); } };
						}
					case TF_MOD_PENIS_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes ribbing from penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds ribbing to penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.RIBBED); } };
						}
					case TF_MOD_PENIS_SHEATHED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes sheath from penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.SHEATHED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds sheath to penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.SHEATHED); } };
						}
					case TF_MOD_PENIS_TAPERED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes tapering from penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.TAPERED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds tapering to penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.TAPERED); } };
						}
					case TF_MOD_PENIS_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes tentacles from penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds tentacles to penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.TENTACLED); } };
						}
					case TF_MOD_PENIS_VEINY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes bulging veins from penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.VEINY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds bulging veins to penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.VEINY); } };
						}

					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in testicle size. (" + smallChangeMajorDrain + " size)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in testicle size. (" + smallChangeDrain + " size)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in testicle size. (" + smallChangeMinorDrain + " size)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in testicle size. (+" + smallChangeMinorBoost + " size)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in testicle size. (+" + smallChangeBoost + " size)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in testicle size. (+" + smallChangeMajorBoost + " size)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMajorBoost); } };
						}
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of testicles.") { @Override public String applyEffect() { return target.incrementTesticleCount(smallChangeDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of testicles.") { @Override public String applyEffect() { return target.incrementTesticleCount(smallChangeBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in maximum cum storage. (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeMajorDrain);
									target.fillCumToMaxStorage();
									return s;} };
							case DRAIN:
								return new RacialEffectUtil("Decrease in maximum cum storage. (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeDrain);
									target.fillCumToMaxStorage();
									return s; } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in maximum cum storage. (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeMinorDrain);
									target.fillCumToMaxStorage();
									return s; } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in maximum cum storage. (+" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeMinorBoost);
									target.fillCumToMaxStorage();
									return s; } };
							case BOOST:
								return new RacialEffectUtil("Increase in maximum cum storage. (+" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeBoost);
									target.fillCumToMaxStorage();
									return s; } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in maximum cum storage. (+" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeMajorBoost);
									target.fillCumToMaxStorage();
									return s; } };
						}
					case TF_MOD_REGENERATION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in cum regeneration.") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in cum regeneration.") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in cum regeneration.") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in cum regeneration.") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in cum regeneration.") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in cum regeneration.") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeMajorBoost); } };
						}
					case TF_MOD_CUM_EXPULSION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in cum amount at orgasm. (" + mediumChangeMajorDrain + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in cum amount at orgasm. (" + mediumChangeDrain + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in cum amount at orgasm. (" + mediumChangeMinorDrain + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in cum amount at orgasm. (+" + mediumChangeMinorBoost + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in cum amount at orgasm. (+" + mediumChangeBoost + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in cum amount at orgasm. (+" + mediumChangeMajorBoost + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeMajorBoost); } };
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
								return new RacialEffectUtil("Huge decrease in urethra capacity. (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in urethra capacity. (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in urethra capacity. (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in urethra capacity. (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in urethra capacity. (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in urethra capacity. (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in urethra elasticity. (" + smallChangeMajorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in urethra elasticity. (" + smallChangeDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in urethra elasticity. (" + smallChangeMinorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in urethra elasticity. (+" + smallChangeMinorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in urethra elasticity. (+" + smallChangeBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in urethra elasticity. (+" + smallChangeMajorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in urethra plasticity. (" + smallChangeMajorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in urethra plasticity. (" + smallChangeDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in urethra plasticity. (" + smallChangeMinorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in urethra plasticity. (+" + smallChangeMinorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in urethra plasticity. (+" + smallChangeBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in urethra plasticity. (+" + smallChangeMajorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMajorBoost); } };
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
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" penis transformation.") { @Override public String applyEffect() { return target.setPenisType(RacialBody.valueOfRace(race).getPenisType()); } };
				}
				
			case TF_SKIN:
				return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" skin transformation.") { @Override public String applyEffect() { return target.setSkinType(RacialBody.valueOfRace(race).getSkinType()); } };
				
			case TF_TAIL:
				switch(secondaryModifier) {
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra tail.") { @Override public String applyEffect() { return target.incrementTailCount(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra tail.") { @Override public String applyEffect() {
									List<TailType> tailTypesSuitableForTransformation = TailType.getTailTypesSuitableForTransformation(RacialBody.valueOfRace(race).getTailType());
									if(target.getTailType()==TailType.NONE && !tailTypesSuitableForTransformation.isEmpty()) {
										return target.setTailType(tailTypesSuitableForTransformation.get(0));
									} else {
										return target.incrementTailCount(singleBoost);
									} } };
						}
					case REMOVAL:
						return new RacialEffectUtil("Removes tail.") { @Override public String applyEffect() { return target.setTailType(TailType.NONE); } };
						
					case TF_TYPE_1:
						return new RacialEffectUtil(TailType.getTailTypes(race).get(0)==TailType.NONE?"Removes tail.":Util.capitaliseSentence(TailType.getTailTypes(race).get(0).getTransformName())+" tail transformation.") {
							@Override public String applyEffect() { return target.setTailType(TailType.getTailTypes(race).get(0)); } };
						
					case TF_TYPE_2:
						return new RacialEffectUtil(
								TailType.getTailTypes(race).size()<2 || TailType.getTailTypes(race).get(1)==TailType.NONE
									?"Removes tail."
									:Util.capitaliseSentence(TailType.getTailTypes(race).get(1).getTransformName())+" tail transformation.") {
							@Override public String applyEffect() { if(TailType.getTailTypes(race).size()<2) {return target.setTailType(TailType.NONE); } else {return target.setTailType(TailType.getTailTypes(race).get(1));} } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(
								TailType.getTailTypes(race).size()<3 || TailType.getTailTypes(race).get(2)==TailType.NONE
									?"Removes tail."
									:Util.capitaliseSentence(TailType.getTailTypes(race).get(1).getTransformName())+" tail transformation.") {
							@Override public String applyEffect() { if(TailType.getTailTypes(race).size()<3) {return target.setTailType(TailType.NONE); } else {return target.setTailType(TailType.getTailTypes(race).get(2));} } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(
								TailType.getTailTypes(race).size()<4 || TailType.getTailTypes(race).get(3)==TailType.NONE
									?"Removes tail."
									:Util.capitaliseSentence(TailType.getTailTypes(race).get(1).getTransformName())+" tail transformation.") {
							@Override public String applyEffect() { if(TailType.getTailTypes(race).size()<4) {return target.setTailType(TailType.NONE); } else {return target.setTailType(TailType.getTailTypes(race).get(3));} } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(
								TailType.getTailTypes(race).size()<5 || TailType.getTailTypes(race).get(4)==TailType.NONE
									?"Removes tail."
									:Util.capitaliseSentence(TailType.getTailTypes(race).get(1).getTransformName())+" tail transformation.") {
							@Override public String applyEffect() { if(TailType.getTailTypes(race).size()<5) {return target.setTailType(TailType.NONE); } else {return target.setTailType(TailType.getTailTypes(race).get(4));} } };
							
					default:
						TailType tailType = RacialBody.valueOfRace(race).getRandomTailType(false);
						return new RacialEffectUtil(tailType==TailType.NONE?"Removes tail.":Util.capitaliseSentence(race.getName(false))+" tail transformation.") {
							@Override public String applyEffect() { return target.setTailType(tailType); } };
				}
				
			case TF_VAGINA:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in clitoris size. (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in clitoris size. (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in clitoris size. (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in clitoris size. (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in clitoris size. (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in clitoris size. (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in clitoris girth. (" + smallChangeMajorDrain + " girth)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in clitoris girth. (" + smallChangeDrain + " girth)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in clitoris girth. (" + smallChangeMinorDrain + " girth)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in clitoris girth. (+" + smallChangeMinorBoost + " girth)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in clitoris girth. (+" + smallChangeBoost + " girth)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in clitoris girth. (+" + smallChangeMajorBoost + " girth)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in labia size. (" + smallChangeMajorDrain + " size)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in labia size. (" + smallChangeDrain + " size)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in labia size. (" + smallChangeMinorDrain + " size)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in labia size. (+" + smallChangeMinorBoost + " size)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in labia size. (+" + smallChangeBoost + " size)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in labia size. (+" + smallChangeMajorBoost + " size)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMajorBoost); } };
						}
					case REMOVAL:
							return new RacialEffectUtil("Removes vagina.") { @Override public String applyEffect() { return target.setVaginaType(VaginaType.NONE); } };
							
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Removes a huge amount of pubic hair. (" + smallChangeMajorDrain + " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Removes a large amount of pubic hair. (" + smallChangeDrain + " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes some pubic hair. (" + smallChangeMinorDrain + " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds some pubic hair. (" + smallChangeMinorBoost + " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Adds a large amount of pubic hair. (" + smallChangeBoost + " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Adds a huge amount of pubic hair. (" + smallChangeMajorBoost + " hairiness") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorBoost); } };
						}

					case TF_MOD_PENIS_BARBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes barbs from clitoris.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.BARBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds barbs to clitoris.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.BARBED); } };
						}
					case TF_MOD_PENIS_FLARED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes flare from clitoris head.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.FLARED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds flare to clitoris head.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.FLARED); } };
						}
					case TF_MOD_PENIS_BLUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes blunted tip from clitoris.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.BLUNT); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes tip of clitoris blunt.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.BLUNT); } };
						}
					case TF_MOD_PENIS_KNOTTED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes knot from base of clitoris.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.KNOTTED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds knot to base of clitoris.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.KNOTTED); } };
						}
					case TF_MOD_PENIS_PREHENSILE:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes prehensility from clitoris.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.PREHENSILE); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds prehensility to clitoris.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.PREHENSILE); } };
						}
					case TF_MOD_PENIS_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes ribbing from clitoris.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds ribbing to clitoris.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.RIBBED); } };
						}
					case TF_MOD_PENIS_SHEATHED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes extra clitoral hood sheathing.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.SHEATHED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds extra clitoral hood sheathing.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.SHEATHED); } };
						}
					case TF_MOD_PENIS_TAPERED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes tapering from clitoris.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.TAPERED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds tapering to clitoris.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.TAPERED); } };
						}
					case TF_MOD_PENIS_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes tentacles from clitoris.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds tentacles to clitoris.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.TENTACLED); } };
						}
					case TF_MOD_PENIS_VEINY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes bulging veins from clitoris.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.VEINY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds bulging veins to clitoris.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.VEINY); } };
						}
							
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in vagina capacity. (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in vagina capacity. (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in vagina capacity. (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in vagina capacity. (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in vagina capacity. (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in vagina capacity. (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in vagina elasticity. (" + smallChangeMajorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in vagina elasticity. (" + smallChangeDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in vagina elasticity. (" + smallChangeMinorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in vagina elasticity. (+" + smallChangeMinorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in vagina elasticity. (+" + smallChangeBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in vagina elasticity. (+" + smallChangeMajorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in vagina plasticity. (" + smallChangeMajorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in vagina plasticity. (" + smallChangeDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in vagina plasticity. (" + smallChangeMinorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in vagina plasticity. (+" + smallChangeMinorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in vagina plasticity. (+" + smallChangeBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in vagina plasticity. (+" + smallChangeMajorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in natural vaginal lubrication. (" + Units.fluid(smallChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in natural vaginal lubrication. (" + Units.fluid(smallChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in natural vaginal lubrication. (" + Units.fluid(smallChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in natural vaginal lubrication. (+" + Units.fluid(smallChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in natural vaginal lubrication. (+" + Units.fluid(smallChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in natural vaginal lubrication. (+" + Units.fluid(smallChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorBoost); } };
						}

					case TF_MOD_VAGINA_SQUIRTER:
						switch(potency) {
							case MINOR_BOOST:
								return new RacialEffectUtil("Makes vagina squirt on orgasm.") { @Override public String applyEffect() { return target.setVaginaSquirter(true); } };
							case MINOR_DRAIN: default:
								return new RacialEffectUtil("Stops vagina from squirting on orgasm.") { @Override public String applyEffect() { return target.setVaginaSquirter(false); } };
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
						
					// Urethral stuff:
					case TF_MOD_CAPACITY_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in urethra capacity. (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in urethra capacity. (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in urethra capacity. (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in urethra capacity. (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in urethra capacity. (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in urethra capacity. (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_ELASTICITY_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in urethra elasticity. (" + smallChangeMajorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in urethra elasticity. (" + smallChangeDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in urethra elasticity. (" + smallChangeMinorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in urethra elasticity. (+" + smallChangeMinorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in urethra elasticity. (+" + smallChangeBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in urethra elasticity. (+" + smallChangeMajorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in urethra plasticity. (" + smallChangeMajorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in urethra plasticity. (" + smallChangeDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in urethra plasticity. (" + smallChangeMinorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in urethra plasticity. (+" + smallChangeMinorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in urethra plasticity. (+" + smallChangeBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in urethra plasticity. (+" + smallChangeMajorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY_2:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes puffiness from urethra.") { @Override public String applyEffect() { return target.removeVaginaUrethraOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes urethra extra puffy.") { @Override public String applyEffect() { return target.addVaginaUrethraOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED_2:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal ribbing from urethra.") { @Override public String applyEffect() { return target.removeVaginaUrethraOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal ribbing to urethra.") { @Override public String applyEffect() { return target.addVaginaUrethraOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED_2:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal muscles from urethra.") { @Override public String applyEffect() { return target.removeVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal muscles to urethra.") { @Override public String applyEffect() { return target.addVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED_2:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal tentacles from urethra.") { @Override public String applyEffect() { return target.removeVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal tentacles to urethra.") { @Override public String applyEffect() { return target.addVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" vagina transformation.") { @Override public String applyEffect() { return target.setVaginaType(RacialBody.valueOfRace(race).getVaginaType()); } };
				}
				
			case TF_WINGS:
				switch(secondaryModifier) {
					case REMOVAL:
						return new RacialEffectUtil("Removes wings.") { @Override public String applyEffect() { return target.setWingType(WingType.NONE); } };
						
					case TF_TYPE_1:
						return new RacialEffectUtil(WingType.getWingTypes(race).get(0)==WingType.NONE?"Removes wings.":Util.capitaliseSentence(WingType.getWingTypes(race).get(0).getTransformName())+" wings transformation.") {
							@Override public String applyEffect() { return target.setWingType(WingType.getWingTypes(race).get(0)); } };
						
					case TF_TYPE_2:
						return new RacialEffectUtil(WingType.getWingTypes(race).get(1)==WingType.NONE?"Removes wings.":Util.capitaliseSentence(WingType.getWingTypes(race).get(1).getTransformName())+" wings transformation.") {
							@Override public String applyEffect() { return target.setWingType(WingType.getWingTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(WingType.getWingTypes(race).get(2)==WingType.NONE?"Removes wings.":Util.capitaliseSentence(WingType.getWingTypes(race).get(2).getTransformName())+" wings transformation.") {
							@Override public String applyEffect() { return target.setWingType(WingType.getWingTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(WingType.getWingTypes(race).get(3)==WingType.NONE?"Removes wings.":Util.capitaliseSentence(WingType.getWingTypes(race).get(3).getTransformName())+" wings transformation.") {
							@Override public String applyEffect() { return target.setWingType(WingType.getWingTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(WingType.getWingTypes(race).get(4)==WingType.NONE?"Removes wings.":Util.capitaliseSentence(WingType.getWingTypes(race).get(4).getTransformName())+" wings transformation.") {
							@Override public String applyEffect() { return target.setWingType(WingType.getWingTypes(race).get(4)); } };
							
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in wing size. (" + smallChangeMajorDrain + " wing size)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in wing size. (" + smallChangeDrain + " wing size)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in wing size. (" + smallChangeMinorDrain + " wing size)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in wing size. (+" + smallChangeMinorBoost + " wing size)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in wing size. (+" + smallChangeBoost + " wing size)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in wing size. (+" + smallChangeMajorBoost + " wing size)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMajorBoost); } };
						}
					default:
						WingType wingType = RacialBody.valueOfRace(race).getRandomWingType(false);
						return new RacialEffectUtil(wingType==WingType.NONE?"Removes wings.":Util.capitaliseSentence(race.getName(false))+" wings transformation.") {
							@Override public String applyEffect() { return target.setWingType(wingType); } };
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
					case TF_MOD_FLAVOUR_CHERRY:
						return new RacialEffectUtil("Makes cum taste like cherries.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.CHERRY); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("Makes cum taste like pineapple.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_BUBBLEGUM:
						return new RacialEffectUtil("Makes cum taste like bubblegum.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.BUBBLEGUM); } };
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
							return new RacialEffectUtil("Removes psychoactive effect from cum.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("Adds a psychoactive effect to cum.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MINERAL_OIL:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes mineral oil effect from cum.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.MINERAL_OIL); } };
						} else {
							return new RacialEffectUtil("Adds a mineral oil effect to cum.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.MINERAL_OIL); } };
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
								return new RacialEffectUtil("Huge decrease in cum production. (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in cum production. (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in cum production. (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("Small increase in cum production. (+" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in cum production. (+" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in cum production. (+" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeMajorBoost); } };
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
					case TF_MOD_FLAVOUR_CHERRY:
						return new RacialEffectUtil("Makes milk taste like cherries.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.CHERRY); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("Makes milk taste like pineapple.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_BUBBLEGUM:
						return new RacialEffectUtil("Makes milk taste like bubblegum.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.BUBBLEGUM); } };
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
							return new RacialEffectUtil("Removes psychoactive effect from milk.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("Adds a psychoactive effect to milk.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MINERAL_OIL:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes mineral oil effect from milk.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.MINERAL_OIL); } };
						} else {
							return new RacialEffectUtil("Adds a mineral oil effect to milk.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.MINERAL_OIL); } };
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
								return new RacialEffectUtil("Huge decrease in milk storage. (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in milk storage. (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in milk storage. (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("Small increase in milk storage. (+" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in milk storage. (+" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in milk storage. (+" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorBoost); } };
						}
				}
				break;

			case TF_MILK_CROTCH:
				switch(secondaryModifier) {
					case TF_MOD_FLAVOUR_BEER:
						return new RacialEffectUtil("Makes udder-milk taste like beer.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.BEER); } };
					case TF_MOD_FLAVOUR_CHOCOLATE:
						return new RacialEffectUtil("Makes udder-milk taste like chocolate.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.CHOCOLATE); } };
					case TF_MOD_FLAVOUR_CUM:
						return new RacialEffectUtil("Makes udder-milk taste like cum.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.CUM); } };
					case TF_MOD_FLAVOUR_GIRLCUM:
						return new RacialEffectUtil("Makes udder-milk taste like girlcum.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.GIRL_CUM); } };
					case TF_MOD_FLAVOUR_MILK:
						return new RacialEffectUtil("Makes udder-milk taste like milk.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.MILK); } };
					case TF_MOD_FLAVOUR_HONEY:
						return new RacialEffectUtil("Makes udder-milk taste like honey.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.HONEY); } };
					case TF_MOD_FLAVOUR_MINT:
						return new RacialEffectUtil("Makes udder-milk taste like mint.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.MINT); } };
					case TF_MOD_FLAVOUR_CHERRY:
						return new RacialEffectUtil("Makes udder-milk taste like cherries.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.CHERRY); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("Makes udder-milk taste like pineapple.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_BUBBLEGUM:
						return new RacialEffectUtil("Makes udder-milk taste like bubblegum.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.BUBBLEGUM); } };
					case TF_MOD_FLAVOUR_STRAWBERRY:
						return new RacialEffectUtil("Makes udder-milk taste like strawberries.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.STRAWBERRY); } };
					case TF_MOD_FLAVOUR_VANILLA:
						return new RacialEffectUtil("Makes udder-milk taste like vanilla.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.VANILLA); } };
	
					case TF_MOD_FLUID_ADDICTIVE:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes addictive effect from udder-milk.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.ADDICTIVE); } };
						} else {
							return new RacialEffectUtil("Adds an addictive effect to udder-milk.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.ADDICTIVE); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes alcoholic effect from udder-milk.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.ALCOHOLIC); } };
						} else {
							return new RacialEffectUtil("Makes udder-milk alcoholic.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.ALCOHOLIC); } };
						}
					case TF_MOD_FLUID_BUBBLING:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes bubbling effect from udder-milk.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.BUBBLING); } };
						} else {
							return new RacialEffectUtil("Makes udder-milk fizzy and bubbly.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.BUBBLING); } };
						}
					case TF_MOD_FLUID_HALLUCINOGENIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes psychoactive effect from udder-milk.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("Adds a psychoactive effect to udder-milk.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MINERAL_OIL:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes mineral oil effect from udder-milk.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.MINERAL_OIL); } };
						} else {
							return new RacialEffectUtil("Adds a mineral oil effect to udder-milk.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.MINERAL_OIL); } };
						}
					case TF_MOD_FLUID_MUSKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes musky effect from udder-milk.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.MUSKY); } };
						} else {
							return new RacialEffectUtil("Makes udder-milk give off a potent musk.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.MUSKY); } };
						}
					case TF_MOD_FLUID_SLIMY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes slimy effect from udder-milk.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.SLIMY); } };
						} else {
							return new RacialEffectUtil("Makes udder-milk slimy.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.SLIMY); } };
						}
					case TF_MOD_FLUID_STICKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes sticky effect from udder-milk.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.STICKY); } };
						} else {
							return new RacialEffectUtil("Makes udder-milk sticky.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.STICKY); } };
						}
					case TF_MOD_FLUID_VISCOUS:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes viscous effect from udder-milk.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.VISCOUS); } };
						} else {
							return new RacialEffectUtil("Makes udder-milk thick and viscous.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.VISCOUS); } };
						}
						
					default:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in udder-milk storage. (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in udder-milk storage. (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in udder-milk storage. (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("Small increase in udder-milk storage. (" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in udder-milk storage. (" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in udder-milk storage. (" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMajorBoost); } };
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
					case TF_MOD_FLAVOUR_CHERRY:
						return new RacialEffectUtil("Makes girlcum taste like cherries.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.CHERRY); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("Makes girlcum taste like pineapple.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_BUBBLEGUM:
						return new RacialEffectUtil("Makes girlcum taste like bubblegum.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.BUBBLEGUM); } };
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
							return new RacialEffectUtil("Removes psychoactive effect from girlcum.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("Adds a psychoactive effect to girlcum.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MINERAL_OIL:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Removes mineral oil effect from girlcum.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.MINERAL_OIL); } };
						} else {
							return new RacialEffectUtil("Adds a mineral oil effect to girlcum.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.MINERAL_OIL); } };
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
								return new RacialEffectUtil("Huge decrease in natural vaginal lubrication. (" + Units.fluid(smallChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in natural vaginal lubrication. (" + Units.fluid(smallChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in natural vaginal lubrication. (" + Units.fluid(smallChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("Small increase in natural vaginal lubrication. (+" + Units.fluid(smallChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in natural vaginal lubrication. (+" + Units.fluid(smallChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in natural vaginal lubrication. (+" + Units.fluid(smallChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorBoost); } };
						}
				}
				break;
			default:
				break;
		}

		return new RacialEffectUtil("Random non-racial transformation") {
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

	private static RacialEffectUtil getHornTypeRacialEffectUtil(Race race, GameCharacter target, int index) {
		List<AbstractHornType> hornTypes = RacialBody.valueOfRace(race).getHornTypes(true);
		AbstractHornType selectedHornType = index >= hornTypes.size() ? HornType.NONE : hornTypes.get(index);
		
		return new RacialEffectUtil("Grows "+selectedHornType.getTransformName()+" horn"+(selectedHornType==HornType.HORSE_STRAIGHT?"":"s")+".") {
			@Override public String applyEffect() { return target.setHornType(selectedHornType); } };
	}
	
}
