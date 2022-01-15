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
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAntennaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.types.AntennaType;
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
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
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
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
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
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryBookAddedToLibrary;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.4
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractItemEffectType {
	
	private List<String> effectsDescriptions;
	private Colour colour;
	
	public AbstractItemEffectType(
			List<String> effectsDescriptions,
			Colour colour) {
		this.effectsDescriptions = effectsDescriptions;
		this.colour = colour;
	}
	
	public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
		if(effectsDescriptions==null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(effectsDescriptions);
	}
	
	public Colour getColour() {
		return colour;
	}
	
	/**
	 * @return Usually null, but if this ItemEffectType has an associated Race, this is how to access it.
	 */
	public AbstractRace getAssociatedRace() {
		return null;
	}
	
	public abstract String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer);
	
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
	
	public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
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
	
	public static String getBookEffect(GameCharacter reader, AbstractSubspecies mainSubspecies, List<AbstractSubspecies> additionalUnlockSubspecies, boolean withDescription) {
		List<AbstractSubspecies> subsPlusMain = new ArrayList<>();
		subsPlusMain.add(mainSubspecies);
		if(additionalUnlockSubspecies!=null) {
			subsPlusMain.addAll(additionalUnlockSubspecies);
		}
		
		for(AbstractSubspecies subspecies : subsPlusMain) {
			Main.getProperties().addRaceDiscovered(subspecies);
			if(Main.getProperties().addAdvancedRaceKnowledge(subspecies) && ItemType.getLoreBook(subspecies)!=null) {
				Main.game.addEvent(new EventLogEntryBookAddedToLibrary(ItemType.getLoreBook(subspecies)), true);
			}
		}
		
		AbstractPerk perk = Perk.getSubspeciesRelatedPerk(mainSubspecies);
		if(!reader.isPlayer() || ((PlayerCharacter) reader).addRaceDiscoveredFromBook(mainSubspecies) || !reader.hasPerkAnywhereInTree(perk)) {
			return (withDescription
						?mainSubspecies.getBasicDescription(null)
								+mainSubspecies.getAdvancedDescription(null)
						:"")
					+reader.addSpecialPerk(perk);
			
		} else {
			return mainSubspecies.getBasicDescription(null)
					+mainSubspecies.getAdvancedDescription(null)
					+"<p style='text-align:center; color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
						+ "Nothing further can be gained from re-reading this book..."
					+ "</p>";
		}
		
	}
	
	protected static List<TFModifier> getClothingTFSecondaryModifiers(TFModifier primaryModifier) {
		switch(primaryModifier) {
			case TF_ASS:
				List<TFModifier> assMods = Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,// ass size
						TFModifier.TF_MOD_SIZE_SECONDARY,// hip size
						Main.game.isAssHairEnabled()
							?TFModifier.TF_MOD_BODY_HAIR
							:null);
				if(Main.game.isAnalContentEnabled()) {
					assMods.add(TFModifier.TF_MOD_CAPACITY);
					if(Main.game.isPenetrationLimitationsEnabled()) {
						assMods.add(TFModifier.TF_MOD_DEPTH);
					}
					assMods.add(TFModifier.TF_MOD_ELASTICITY);
					assMods.add(TFModifier.TF_MOD_PLASTICITY);
					assMods.add(TFModifier.TF_MOD_WETNESS);
					assMods.add(TFModifier.TF_MOD_ORIFICE_PUFFY);
					assMods.add(TFModifier.TF_MOD_ORIFICE_RIBBED);
					assMods.add(TFModifier.TF_MOD_ORIFICE_MUSCLED);
					assMods.add(TFModifier.TF_MOD_ORIFICE_TENTACLED);
				}
				return assMods;
			case TF_ARMS:
				return Util.newArrayListOfValues(
						Main.game.isBodyHairEnabled()
							?TFModifier.TF_MOD_BODY_HAIR
							:null);
			case TF_BREASTS: case TF_BREASTS_CROTCH:
				return Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,// breast size
						TFModifier.TF_MOD_SIZE_SECONDARY,// nipple size
						TFModifier.TF_MOD_SIZE_TERTIARY,// areolae size
						TFModifier.TF_MOD_CAPACITY,
						Main.game.isPenetrationLimitationsEnabled()
							?TFModifier.TF_MOD_DEPTH
							:null,
						TFModifier.TF_MOD_ELASTICITY,
						TFModifier.TF_MOD_PLASTICITY,
						Main.game.isLactationContentEnabled()
							?TFModifier.TF_MOD_WETNESS
							:null,
						Main.game.isLactationContentEnabled()
							?TFModifier.TF_MOD_REGENERATION
							:null,
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
						TFModifier.TF_MOD_FEMININITY,
						Main.game.isPubicHairEnabled()
							?TFModifier.TF_MOD_BODY_HAIR
							:null
						);
			case TF_FACE:
				return Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE, // lip size
						TFModifier.TF_MOD_SIZE_SECONDARY, // tongue size
						TFModifier.TF_MOD_CAPACITY,
						Main.game.isPenetrationLimitationsEnabled()
							?TFModifier.TF_MOD_DEPTH
							:null,
						TFModifier.TF_MOD_ELASTICITY,
						TFModifier.TF_MOD_PLASTICITY,
						TFModifier.TF_MOD_WETNESS,

						TFModifier.TF_MOD_ORIFICE_PUFFY,
						TFModifier.TF_MOD_ORIFICE_RIBBED,
						TFModifier.TF_MOD_ORIFICE_MUSCLED,
						TFModifier.TF_MOD_ORIFICE_TENTACLED,
						
						TFModifier.TF_MOD_TONGUE_RIBBED,
						TFModifier.TF_MOD_TONGUE_TENTACLED,
						TFModifier.TF_MOD_TONGUE_BIFURCATED,
						TFModifier.TF_MOD_TONGUE_WIDE,
						TFModifier.TF_MOD_TONGUE_FLAT,
						TFModifier.TF_MOD_TONGUE_STRONG,
						
						Main.game.isFacialHairEnabled()
							?TFModifier.TF_MOD_BODY_HAIR
							:null
						);
			case TF_HAIR:
				return Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,// hair length
						TFModifier.TF_MOD_SIZE_SECONDARY// neck floof
						);
			case TF_PENIS:
				List<TFModifier> penisMods = Util.newArrayListOfValues(
						TFModifier.TF_TYPE_1,
						TFModifier.REMOVAL,
						TFModifier.TF_MOD_SIZE,
						TFModifier.TF_MOD_SIZE_SECONDARY,
						TFModifier.TF_MOD_SIZE_TERTIARY);
				
				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					penisMods.add(TFModifier.TF_MOD_CAPACITY);
					if(Main.game.isPenetrationLimitationsEnabled()) {
						penisMods.add(TFModifier.TF_MOD_DEPTH);
					}
					penisMods.add(TFModifier.TF_MOD_ELASTICITY);
					penisMods.add(TFModifier.TF_MOD_PLASTICITY);
				}
				penisMods.add(TFModifier.TF_MOD_WETNESS);
				penisMods.add(TFModifier.TF_MOD_CUM_EXPULSION);
				penisMods.add(TFModifier.TF_MOD_REGENERATION);
				if(Main.game.isPubicHairEnabled()) {
					penisMods.add(TFModifier.TF_MOD_BODY_HAIR);
				}

				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					penisMods.add(TFModifier.TF_MOD_ORIFICE_PUFFY);
					penisMods.add(TFModifier.TF_MOD_ORIFICE_RIBBED);
					penisMods.add(TFModifier.TF_MOD_ORIFICE_MUSCLED);
					penisMods.add(TFModifier.TF_MOD_ORIFICE_TENTACLED);
				}
				return penisMods;
				
			case TF_VAGINA:
				List<TFModifier> mods = Util.newArrayListOfValues(
						TFModifier.TF_TYPE_1,
						TFModifier.REMOVAL,
						TFModifier.TF_MOD_SIZE,// clit size
						TFModifier.TF_MOD_SIZE_SECONDARY,// labia size
						TFModifier.TF_MOD_CAPACITY,
						Main.game.isPenetrationLimitationsEnabled()
							?TFModifier.TF_MOD_DEPTH
							:null,
						TFModifier.TF_MOD_ELASTICITY,
						TFModifier.TF_MOD_PLASTICITY,
						TFModifier.TF_MOD_WETNESS,
						TFModifier.TF_MOD_ORIFICE_PUFFY,
						TFModifier.TF_MOD_ORIFICE_RIBBED,
						TFModifier.TF_MOD_ORIFICE_MUSCLED,
						TFModifier.TF_MOD_ORIFICE_TENTACLED,
						Main.game.isPubicHairEnabled()
							?TFModifier.TF_MOD_BODY_HAIR
							:null
						);

				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					mods.add(TFModifier.TF_MOD_CAPACITY_2);
					if(Main.game.isPenetrationLimitationsEnabled()) {
						mods.add(TFModifier.TF_MOD_DEPTH_2);
					}
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
				return (int) Capacity.SEVEN_GAPING.getMaximumValue(false);
			case TF_MOD_DEPTH:
				return OrificeDepth.SEVEN_FATHOMLESS.getValue();
			case TF_MOD_ELASTICITY:
				return OrificeElasticity.SEVEN_ELASTIC.getValue();
			case TF_MOD_PLASTICITY:
				return OrificePlasticity.SEVEN_MOULDABLE.getValue();
			case TF_MOD_CAPACITY_2:
				return (int) Capacity.SEVEN_GAPING.getMaximumValue(false);
			case TF_MOD_DEPTH_2:
				return OrificeDepth.SEVEN_FATHOMLESS.getValue();
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
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
					default:
						break;
				}
				break;
			case TF_ARMS:
				switch(secondaryModifier) {
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
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
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
					default:
						break;
				}
				break;
			case TF_FACE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return LipSize.FOUR_HUGE.getValue();
					case TF_MOD_SIZE_SECONDARY:
						return TongueLength.FOUR_ABSURDLY_LONG.getMaximumValue();
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
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
						return PenisLength.SEVEN_STALLION.getMaximumValue();
					case TF_MOD_SIZE_SECONDARY:
						return PenetrationGirth.SEVEN_FAT.getValue();
					case TF_MOD_SIZE_TERTIARY:
						return TesticleSize.SEVEN_ABSURD.getValue();
					case TF_MOD_WETNESS:
						return CumProduction.SEVEN_MONSTROUS.getMaximumValue();
					case TF_MOD_CUM_EXPULSION:
						return FluidExpulsion.FOUR_HUGE.getMaximumValue();
					case TF_MOD_REGENERATION:
						return FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay();
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
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
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
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
		} else if(primaryModifier==TFModifier.TF_FACE) {
			orificeName = "throat";
		}
		
		switch(secondaryModifier) {
			case TF_MOD_CAPACITY:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+" capacity", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
				break;
			case TF_MOD_DEPTH:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+" depth", OrificeDepth.getDepthFromInt(limit).getDescriptor()));
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
			case TF_MOD_DEPTH_2:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"l urethra depth", OrificeDepth.getDepthFromInt(limit).getDescriptor()));
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
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "ass hairiness", BodyHair.getBodyHairFromValue(limit).getName()));
						break;
					default:
						break;
				}
				break;
			case TF_ARMS:
				switch(secondaryModifier) {
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "underarm hairiness", BodyHair.getBodyHairFromValue(limit).getName()));
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
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "pubic hairiness", BodyHair.getBodyHairFromValue(limit).getName()));
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
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "tongue length", Units.size(limit)));
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
					case TF_MOD_TONGUE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "ribbed tongue",  "tongue ribbing"));
						break;
					case TF_MOD_TONGUE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "tentacled tongue", "tongue tentacles"));
						break;
					case TF_MOD_TONGUE_BIFURCATED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "bifurcated tongue", "tongue bifurcation"));
						break;
					case TF_MOD_TONGUE_WIDE:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "wide tongue", "tongue widening"));
						break;
					case TF_MOD_TONGUE_FLAT:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "flat tongue", "tongue flattening"));
						break;
					case TF_MOD_TONGUE_STRONG:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "strong tongue", "tongue strengthening"));
						break;
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "beard length", BodyHair.getBodyHairFromValue(limit).getName()));
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
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MINOR_BOOST:
								descriptions.add("In a week, grows neck fluff.");
								break;
							case BOOST:
								descriptions.add("In a day, grows neck fluff.");
								break;
							case MAJOR_BOOST:
								descriptions.add("In an hour, grows neck fluff.");
								break;
							case MINOR_DRAIN:
								descriptions.add("In a week, removes neck fluff.");
								break;
							case DRAIN:
								descriptions.add("In a day, removes neck fluff.");
								break;
							case MAJOR_DRAIN:
								descriptions.add("In an hour, removes neck fluff.");
								break;
							default:
								break;
						}
						break;
					default:
						break;
				}
				break;
			case TF_PENIS:
				switch(secondaryModifier) {
					case TF_TYPE_1:
						switch(potency) {
							case MINOR_BOOST:
								descriptions.add("In a week, grows penis.");
								break;
							case BOOST:
								descriptions.add("In a day, grows penis.");
								break;
							case MAJOR_BOOST:
								descriptions.add("In an hour, grows penis.");
								break;
							default:
								break;
						}
						break;
					case REMOVAL:
						switch(potency) {
							case MINOR_BOOST:
								descriptions.add("In a week, removes penis.");
								break;
							case BOOST:
								descriptions.add("In a day, removes penis.");
								break;
							case MAJOR_BOOST:
								descriptions.add("In an hour, removes penis.");
								break;
							default:
								break;
						}
						break;
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "penis length", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "penis girth", PenetrationGirth.getGirthFromInt(limit).getName()));
						break;
					case TF_MOD_SIZE_TERTIARY:
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
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "pubic hairiness", BodyHair.getBodyHairFromValue(limit).getName()));
						break;
					default:
						break;
				}
				break;
			case TF_VAGINA:
				switch(secondaryModifier) {
					case TF_TYPE_1:
						switch(potency) {
							case MINOR_BOOST:
								descriptions.add("In a week, grows vagina.");
								break;
							case BOOST:
								descriptions.add("In a day, grows vagina.");
								break;
							case MAJOR_BOOST:
								descriptions.add("In an hour, grows vagina.");
								break;
							default:
								break;
						}
						break;
					case REMOVAL:
						switch(potency) {
							case MINOR_BOOST:
								descriptions.add("In a week, removes vagina.");
								break;
							case BOOST:
								descriptions.add("In a day, removes vagina.");
								break;
							case MAJOR_BOOST:
								descriptions.add("In an hour, removes vagina.");
								break;
							default:
								break;
						}
						break;
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
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "pubic hairiness", BodyHair.getBodyHairFromValue(limit).getName()));
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
		int depthIncrement = (potency.isNegative()?-1:1);
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

		int bodyHairIncrement = (potency.isNegative()?-1:1);
		
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
						case TF_MOD_DEPTH:
							if(isWithinLimits(depthIncrement, target.getAssDepth().getValue(), limit)) {
								sb.append(target.incrementAssDepth(depthIncrement));
							} else if(isSetToLimit(depthIncrement, target.getAssDepth().getValue(), limit)) {
								sb.append(target.setAssDepth(limit));
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
						case TF_MOD_BODY_HAIR:
							if(isWithinLimits(bodyHairIncrement, target.getAssHair().getValue(), limit)) {
								sb.append(target.incrementAssHair(bodyHairIncrement));
							} else if(isSetToLimit(bodyHairIncrement, target.getAssHair().getValue(), limit)) {
								sb.append(target.setAssHair(limit));
							}
							break;
						default:
							break;
					}
					break;
				case TF_ARMS:
					switch(secondaryModifier) {
						case TF_MOD_BODY_HAIR:
							if(isWithinLimits(bodyHairIncrement, target.getUnderarmHair().getValue(), limit)) {
								sb.append(target.incrementUnderarmHair(bodyHairIncrement));
							} else if(isSetToLimit(bodyHairIncrement, target.getUnderarmHair().getValue(), limit)) {
								sb.append(target.setUnderarmHair(limit));
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
						case TF_MOD_DEPTH:
							if(isWithinLimits(depthIncrement, target.getNippleDepth().getValue(), limit)) {
								sb.append(target.incrementNippleDepth(depthIncrement));
							} else if(isSetToLimit(depthIncrement, target.getNippleDepth().getValue(), limit)) {
								sb.append(target.setNippleDepth(limit));
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
							case TF_MOD_DEPTH:
								if(isWithinLimits(depthIncrement, target.getNippleCrotchDepth().getValue(), limit)) {
									sb.append(target.incrementNippleCrotchDepth(depthIncrement));
								} else if(isSetToLimit(depthIncrement, target.getNippleCrotchDepth().getValue(), limit)) {
									sb.append(target.setNippleCrotchDepth(limit));
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
						case TF_MOD_BODY_HAIR:
							if(isWithinLimits(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
								sb.append(target.incrementPubicHair(bodyHairIncrement));
							} else if(isSetToLimit(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
								sb.append(target.setPubicHair(limit));
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
						case TF_MOD_CAPACITY:
							if(isWithinLimits(capacityIncrement, target.getFaceRawCapacityValue(), limit)) {
								sb.append(target.incrementFaceCapacity(capacityIncrement, true));
							} else if(isSetToLimit(capacityIncrement, target.getFaceRawCapacityValue(), limit)) {
								sb.append(target.setFaceCapacity(limit, true));
							}
							break;
						case TF_MOD_DEPTH:
							if(isWithinLimits(depthIncrement, target.getFaceDepth().getValue(), limit)) {
								sb.append(target.incrementFaceDepth(depthIncrement));
							} else if(isSetToLimit(depthIncrement, target.getFaceDepth().getValue(), limit)) {
								sb.append(target.setFaceDepth(limit));
							}
							break;
						case TF_MOD_ELASTICITY:
							if(isWithinLimits(elasticityIncrement, target.getFaceElasticity().getValue(), limit)) {
								sb.append(target.incrementFaceElasticity(elasticityIncrement));
							} else if(isSetToLimit(elasticityIncrement, target.getFaceElasticity().getValue(), limit)) {
								sb.append(target.setFaceElasticity(limit));
							}
							break;
						case TF_MOD_PLASTICITY:
							if(isWithinLimits(plasticityIncrement, target.getFacePlasticity().getValue(), limit)) {
								sb.append(target.incrementFacePlasticity(plasticityIncrement));
							} else if(isSetToLimit(plasticityIncrement, target.getFacePlasticity().getValue(), limit)) {
								sb.append(target.setFacePlasticity(limit));
							}
							break;
						case TF_MOD_WETNESS:
							if(isWithinLimits(wetnessIncrement, target.getFaceWetness().getValue(), limit)) {
								sb.append(target.incrementFaceWetness(wetnessIncrement));
							} else if(isSetToLimit(wetnessIncrement, target.getFaceWetness().getValue(), limit)) {
								sb.append(target.setFaceWetness(limit));
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
						case TF_MOD_TONGUE_RIBBED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.RIBBED)) {
									sb.append(target.addTongueModifier(TongueModifier.RIBBED));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.RIBBED)) {
									sb.append(target.removeTongueModifier(TongueModifier.RIBBED));
								}
							}
							break;
						case TF_MOD_TONGUE_TENTACLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.TENTACLED)) {
									sb.append(target.addTongueModifier(TongueModifier.TENTACLED));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.TENTACLED)) {
									sb.append(target.removeTongueModifier(TongueModifier.TENTACLED));
								}
							}
							break;
						case TF_MOD_TONGUE_BIFURCATED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.BIFURCATED)) {
									sb.append(target.addTongueModifier(TongueModifier.BIFURCATED));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.BIFURCATED)) {
									sb.append(target.removeTongueModifier(TongueModifier.BIFURCATED));
								}
							}
							break;
						case TF_MOD_TONGUE_WIDE:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.WIDE)) {
									sb.append(target.addTongueModifier(TongueModifier.WIDE));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.WIDE)) {
									sb.append(target.removeTongueModifier(TongueModifier.WIDE));
								}
							}
							break;
						case TF_MOD_TONGUE_FLAT:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.FLAT)) {
									sb.append(target.addTongueModifier(TongueModifier.FLAT));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.FLAT)) {
									sb.append(target.removeTongueModifier(TongueModifier.FLAT));
								}
							}
							break;
						case TF_MOD_TONGUE_STRONG:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.STRONG)) {
									sb.append(target.addTongueModifier(TongueModifier.STRONG));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.STRONG)) {
									sb.append(target.removeTongueModifier(TongueModifier.STRONG));
								}
							}
							break;
						case TF_MOD_BODY_HAIR:
							if(isWithinLimits(bodyHairIncrement, target.getFacialHair().getValue(), limit)) {
								sb.append(target.incrementFacialHair(bodyHairIncrement));
							} else if(isSetToLimit(bodyHairIncrement, target.getFacialHair().getValue(), limit)) {
								sb.append(target.setFacialHair(limit));
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
						case TF_MOD_SIZE_SECONDARY:
							if(potency.isNegative()) {
								if(target.isNeckFluff()) {
									sb.append(target.setNeckFluff(false));
								}
							} else {
								if(!target.isNeckFluff()) {
									sb.append(target.setNeckFluff(true));
								}
							}
							break;
						default:
							break;
					}
					break;
				case TF_PENIS:
					if(target.hasPenisIgnoreDildo()) {
						switch(secondaryModifier) {
							case REMOVAL:
								sb.append(target.setPenisType(PenisType.NONE));
								break;
							case TF_MOD_SIZE:
								if(isWithinLimits(penisSizeIncrement, target.getPenisRawSizeValue(), limit)) {
									sb.append(target.incrementPenisSize(penisSizeIncrement));
								} else if(isSetToLimit(penisSizeIncrement, target.getPenisRawSizeValue(), limit)) {
									sb.append(target.setPenisSize(limit));
								}
								break;
							case TF_MOD_SIZE_SECONDARY:
								if(isWithinLimits(penisSizeIncrement, target.getPenisGirth().getValue(), limit)) {
									sb.append(target.incrementPenisGirth(penisSizeIncrement));
								} else if(isSetToLimit(penisSizeIncrement, target.getPenisGirth().getValue(), limit)) {
									sb.append(target.setPenisGirth(limit));
								}
								break;
							case TF_MOD_SIZE_TERTIARY:
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
							case TF_MOD_DEPTH:
								if(isWithinLimits(depthIncrement, target.getUrethraDepth().getValue(), limit)) {
									sb.append(target.incrementUrethraDepth(depthIncrement));
								} else if(isSetToLimit(depthIncrement, target.getUrethraDepth().getValue(), limit)) {
									sb.append(target.setUrethraDepth(limit));
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
							case TF_MOD_BODY_HAIR:
								if(isWithinLimits(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
									sb.append(target.incrementPubicHair(bodyHairIncrement));
								} else if(isSetToLimit(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
									sb.append(target.setPubicHair(limit));
								}
								break;
							default:
								break;
						}
						
					} else { // Do not have penis:
						switch(secondaryModifier) {
							case TF_TYPE_1:
								if(target.getSubspeciesOverride()!=null) {
									sb.append(target.setPenisType(PenisType.getPenisTypes(target.getSubspeciesOverrideRace()).get(0)));
								} else {
									sb.append(target.setPenisType(PenisType.getPenisTypes(target.getRace()).get(0)));
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
							case REMOVAL:
								sb.append(target.setVaginaType(VaginaType.NONE));
								break;
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
							case TF_MOD_DEPTH:
								if(isWithinLimits(depthIncrement, target.getVaginaDepth().getValue(), limit)) {
									sb.append(target.incrementVaginaDepth(depthIncrement));
								} else if(isSetToLimit(depthIncrement, target.getVaginaDepth().getValue(), limit)) {
									sb.append(target.setVaginaDepth(limit));
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
							case TF_MOD_DEPTH_2:
								if(isWithinLimits(depthIncrement, target.getVaginaUrethraDepth().getValue(), limit)) {
									sb.append(target.incrementVaginaUrethraDepth(depthIncrement));
								} else if(isSetToLimit(depthIncrement, target.getVaginaUrethraDepth().getValue(), limit)) {
									sb.append(target.setVaginaUrethraDepth(limit));
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
							case TF_MOD_BODY_HAIR:
								if(isWithinLimits(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
									sb.append(target.incrementPubicHair(bodyHairIncrement));
								} else if(isSetToLimit(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
									sb.append(target.setPubicHair(limit));
								}
								break;
							default:
								break;
						}
						
					} else { // Does not have vagina:
						switch(secondaryModifier) {
							case TF_TYPE_1:
								if(target.getSubspeciesOverride()!=null) {
									sb.append(target.setVaginaType(VaginaType.getVaginaTypes(target.getSubspeciesOverrideRace()).get(0)));
								} else {
									sb.append(target.setVaginaType(VaginaType.getVaginaTypes(target.getRace()).get(0)));
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
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(-15, "b")+" to 'potion effects'");
							}
						}
						break;
					case DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsDrain(40, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(-10, "b")+" to 'potion effects'");
							}
						}
						break;
					case MINOR_DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsDrain(20, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(-5, "b")+" to 'potion effects'");
							}
						}
						break;
					case MINOR_BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsRestore(20, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(5, "b")+" to 'potion effects'");
							}
						}
						break;
					case BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsRestore(40, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(10, "b")+" to 'potion effects'");
							}
						}
						break;
					case MAJOR_BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsRestore(60, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(15, "b")+" to 'potion effects'");
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
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p>");
			switch(potency) {
				case MAJOR_DRAIN:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, -0.6f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "A sickly wave of arcane energy washes over [npc.name]...")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), -15));
						}
					}
					break;
				case DRAIN:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, -0.4f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "A sickly wave of arcane energy washes over [npc.name]...")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), -10));
						}
					}
					break;
				case MINOR_DRAIN:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, -0.2f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "A sickly wave of arcane energy washes over [npc.name]...")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), -5));
						}
					}
					break;
				case MINOR_BOOST:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, 0.2f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "A soothing wave of arcane energy washes over [npc.name]...")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), 5));
						}
					}
					break;
				case BOOST:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, 0.4f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "A soothing wave of arcane energy washes over [npc.name]...")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), 10));
						}
					}
					break;
				case MAJOR_BOOST:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, 0.6f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "A soothing wave of arcane energy washes over [npc.name]...")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), 15));
						}
					}
					break;
			}
		sb.append("</p>");
		
		return sb.toString();
	}
	
	// Caching:
	private static Map<AbstractRace, Map<TFModifier, LinkedHashMap<TFModifier, List<TFPotency>>>> racialPrimaryModSecondaryModPotencyGrid = new HashMap<>();
	
	protected static List<TFModifier> getRacialSecondaryModifiers(AbstractRace race, TFModifier primaryModifier) {
		if(racialPrimaryModSecondaryModPotencyGrid.containsKey(race) && racialPrimaryModSecondaryModPotencyGrid.get(race).containsKey(primaryModifier)) {
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).keySet());
		} else {
			populateGrid(race, primaryModifier);
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).keySet());
		}
	}
	
	protected static List<TFPotency> getRacialPotencyModifiers(AbstractRace race, TFModifier primaryModifier, TFModifier secondaryModifier) {
		if(racialPrimaryModSecondaryModPotencyGrid.get(race).containsKey(primaryModifier)) {
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).get(secondaryModifier));
		} else {
			populateGrid(race, primaryModifier);
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).get(secondaryModifier));
		}
	}
	
	private static void populateGrid(AbstractRace race, TFModifier primaryModifier) {
		LinkedHashMap<TFModifier, List<TFPotency>> secondaryModPotencyMap = new LinkedHashMap<>();
		
		switch(primaryModifier) {
			case TF_ANTENNA:
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				for(int i=0; i< RacialBody.valueOfRace(race).getAntennaTypes(true).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT_SECONDARY, TFPotency.getAllPotencies());
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
				
				if(Main.game.isAnalContentEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
					}
					
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
	
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				}
				break;
				
			case TF_BREASTS: case TF_BREASTS_CROTCH:
				for(int i=0; i< BreastType.getBreastTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
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
				secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_INVERTED, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				if(Main.game.isNipplePenEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_VAGINA, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_LIPS, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_AREOLAE_CIRCLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_AREOLAE_HEART, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_AREOLAE_STAR, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));

				if(Main.game.isNipplePenEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
					}
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				}
				if(Main.game.isLactationContentEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_REGENERATION, TFPotency.getAllPotencies());
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				if(Main.game.isNipplePenEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				}
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
				secondaryModPotencyMap.put(TFModifier.TF_MOD_INTERNAL, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST, TFPotency.BOOST));
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
				if(Main.game.isPenetrationLimitationsEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_BIFURCATED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_WIDE, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_FLAT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_STRONG, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				if(Main.game.isFacialHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_HAIR:
				for(int i=0; i< HairType.getHairTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				break;
				
			case TF_HORNS:
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
//				for(int i=0; i< RacialBody.valueOfRace(race).getHornTypes(true).size();i++) {
				for(int i=0; i< HornType.getHornTypes(race, false).size();i++) {
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
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.BIPEDAL))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_BIPEDAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.QUADRUPEDAL))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_TAUR, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.TAIL_LONG))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_TAIL_LONG, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.TAIL))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_TAIL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.ARACHNID))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_ARACHNID, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.CEPHALOPOD))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_CEPHALOPOD, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.AVIAN))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_AVIAN, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.getFootType().getPermittedFootStructures(null).contains(FootStructure.PLANTIGRADE))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_FOOT_STRUCTURE_PLANTIGRADE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.getFootType().getPermittedFootStructures(null).contains(FootStructure.DIGITIGRADE))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_FOOT_STRUCTURE_DIGITIGRADE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.getFootType().getPermittedFootStructures(null).contains(FootStructure.UNGULIGRADE))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_FOOT_STRUCTURE_UNGULIGRADE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.hasSpinneret())) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));

					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
					}
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_PENIS:
				for(int i=0; i< PenisType.getPenisTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());

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
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_OVIPOSITOR, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				
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
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
					}
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
	
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				}
				break;
				
			case TF_SKIN:
				for(int i=0; i< TorsoType.getTorsoTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				break;
				
			case TF_TAIL:
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				for(int i=0; i< TailType.getTailTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));

				if(RacialBody.valueOfRace(race).getTailType().stream().anyMatch(tt->tt.hasSpinneret())) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));

					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
					}
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_TENTACLE:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				break;
				
			case TF_VAGINA:
				for(int i=0; i< VaginaType.getVaginaTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_VAGINA_SQUIRTER, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_VAGINA_EGG_LAYER, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
				if(Main.game.isPenetrationLimitationsEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());

				if(Main.game.isPubicHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY_2, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH_2, TFPotency.getAllPotencies());
					}
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY_2, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY_2, TFPotency.getAllPotencies());
	
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY_2, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED_2, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED_2, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED_2, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				}

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
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_OVIPOSITOR, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
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
				

				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CUM, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_GIRLCUM, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_MILK, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_BEER, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CHOCOLATE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_HONEY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_MINT, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_PINEAPPLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_BUBBLEGUM, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_STRAWBERRY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CHERRY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_VANILLA, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_COFFEE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_TEA, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_MAPLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CINNAMON, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_LEMON, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_ORANGE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_GRAPE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_MELON, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_COCONUT, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_BLUEBERRY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
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
	
	private static int modifierTypeToInt(TFModifier modifier) {
		List<TFModifier> modifierList = Util.newArrayListOfValues(
				TFModifier.TF_TYPE_1,
				TFModifier.TF_TYPE_2,
				TFModifier.TF_TYPE_3,
				TFModifier.TF_TYPE_4,
				TFModifier.TF_TYPE_5,
				TFModifier.TF_TYPE_6,
				TFModifier.TF_TYPE_7,
				TFModifier.TF_TYPE_8,
				TFModifier.TF_TYPE_9,
				TFModifier.TF_TYPE_10);
		
		if(modifierList.contains(modifier)) {
			return modifierList.indexOf(modifier);
		}
		return 0;
	}
	
	protected static RacialEffectUtil getRacialEffect(AbstractRace race, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, GameCharacter user, GameCharacter target) {
		
		switch(primaryModifier) {
			case TF_ANTENNA:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Antenna length (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Antenna length (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Antenna length (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Antenna length (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Antenna length (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Antenna length (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeMajorBoost); } };
						}
				
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of antennae.") { @Override public String applyEffect() { return target.incrementAntennaRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra pair of antennae.") { @Override public String applyEffect() {
									List<AbstractAntennaType> antennaTypesSuitableForTransformation = RacialBody.valueOfRace(race).getAntennaTypes(true);
									if(target.getAntennaType().equals(AntennaType.NONE) && !antennaTypesSuitableForTransformation.isEmpty()) {
										return target.setAntennaType(antennaTypesSuitableForTransformation.get(0));
									} else {
										return target.incrementAntennaRows(singleBoost);
									} } };
						}
						
					case TF_MOD_COUNT_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Antennae per row (" + smallChangeMajorDrain + " antennae per row)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Antennae per row (" + smallChangeDrain + " antennae per row)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Antennae per row (" + smallChangeMinorDrain + " antennae per row)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Antennae per row (+" + smallChangeMinorBoost + " antennae per row)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Antennae per row (+" + smallChangeBoost + " antennae per row)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Antennae per row (+" + smallChangeMajorBoost + " antennae per row)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeMajorBoost); } };
						}
						
					case REMOVAL:
						return new RacialEffectUtil("Removes antennae.") { @Override public String applyEffect() { return target.setAntennaType(AntennaType.NONE); } };
	
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = modifierTypeToInt(secondaryModifier);
						return getAntennaTypeRacialEffectUtil(race, target, index);
							
					default:
						List<AbstractAntennaType> antennaTypes = RacialBody.valueOfRace(race).getAntennaTypes(true);
						AbstractAntennaType antennaType = antennaTypes.isEmpty()?AntennaType.NONE:Util.randomItemFrom(antennaTypes);
						return new RacialEffectUtil(antennaType.equals(AntennaType.NONE)?"Removes antennae.":Util.capitaliseSentence(race.getName(false))+" antenna transformation.") {
							@Override public String applyEffect() { return target.setAntennaType(antennaType); } };
				}
			
			case TF_ARMS:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(ArmType.getArmTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(ArmType.getArmTypes(race).get(index).getTransformName())+" arm transformation.") {
							@Override public String applyEffect() { return target.setArmType(ArmType.getArmTypes(race).get(index)); } };
	
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
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(AssType.getAssTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(AssType.getAssTypes(race).get(index).getTransformName())+" ass transformation.") {
							@Override public String applyEffect() { return target.setAssType(AssType.getAssTypes(race).get(index)); } };
	
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Ass size (" + smallChangeMajorDrain + " ass size)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Ass size (" + smallChangeDrain + " ass size)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Ass size (" + smallChangeMinorDrain + " ass size)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Ass size (+" + smallChangeMinorBoost + " ass size)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Ass size (+" + smallChangeBoost + " ass size)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Ass size (+" + smallChangeMajorBoost + " ass size)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Hip size (" + smallChangeMajorDrain + " hip size)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Hip size (" + smallChangeDrain + " hip size)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Hip size (" + smallChangeMinorDrain + " hip size)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Hip size (+" + smallChangeMinorBoost + " hip size)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Hip size (+" + smallChangeBoost + " hip size)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Hip size (+" + smallChangeMajorBoost + " hip size)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMajorBoost); } };
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
								return new RacialEffectUtil("[style.colourTerrible(--)] Anal capacity (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Anal capacity (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Anal capacity (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Anal capacity (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Anal capacity (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Anal capacity (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Anal depth (" + smallChangeMajorDrain + " depth)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Anal depth (" + smallChangeDrain + " depth)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Anal depth (" + smallChangeMinorDrain + " depth)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Anal depth (+" + smallChangeMinorBoost + " depth)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Anal depth (+" + smallChangeBoost + " depth)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Anal depth (+" + smallChangeMajorBoost + " depth)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Anal elasticity (" + smallChangeMajorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Anal elasticity (" + smallChangeDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Anal elasticity (" + smallChangeMinorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Anal elasticity (+" + smallChangeMinorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Anal elasticity (+" + smallChangeBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Anal elasticity (+" + smallChangeMajorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Anal plasticity (" + smallChangeMajorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Anal plasticity (" + smallChangeDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Anal plasticity (" + smallChangeMinorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Anal plasticity (+" + smallChangeMinorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Anal plasticity (+" + smallChangeBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Anal plasticity (+" + smallChangeMajorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Anal lubrication (" + smallChangeMajorDrain + " wetness)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Anal lubrication (" + smallChangeDrain + " wetness)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Anal lubrication (" + smallChangeMinorDrain + " wetness)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Anal lubrication (+" + smallChangeMinorBoost + " wetness)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Anal lubrication (+" + smallChangeBoost + " wetness)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Anal lubrication (+" + smallChangeMajorBoost + " wetness)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMajorBoost); } };
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
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(BreastType.getBreastTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(index).getTransformName())+" breast transformation.") {
							@Override public String applyEffect() { return target.setBreastType(BreastType.getBreastTypes(race).get(index)); } };
	
					case REMOVAL:
						return new RacialEffectUtil("Completely flattens breasts.") {
							@Override public String applyEffect() { return target.setBreastSize(0); } };
							
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
								return new RacialEffectUtil("[style.colourTerrible(--)] Breast size (" + smallChangeMajorDrain + " breast size)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Breast size (" + smallChangeDrain + " breast size)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Breast size (" + smallChangeMinorDrain + " breast size)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Breast size (+" + smallChangeMinorBoost + " breast size)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Breast size (+" + smallChangeBoost + " breast size)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Breast size (+" + smallChangeMajorBoost + " breast size)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMajorBoost); } };
						}
						

					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Nipple size (" + smallChangeMajorDrain + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Nipple size (" + smallChangeDrain + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Nipple size (" + smallChangeMinorDrain + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Nipple size (+" + smallChangeMinorBoost + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Nipple size (+" + smallChangeBoost + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Nipple size (+" + smallChangeMajorBoost + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMajorBoost); } };
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
						return new RacialEffectUtil("Turns nipples into a normal shape.") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.NORMAL); } };
					case TF_MOD_NIPPLE_INVERTED:
						return new RacialEffectUtil("Turns nipples into a normal, although inverted, shape.") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.INVERTED); } };
					case TF_MOD_NIPPLE_VAGINA:
						return new RacialEffectUtil("Turns nipples into vaginas.") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.VAGINA); } };
					case TF_MOD_NIPPLE_LIPS:
						return new RacialEffectUtil("Turns nipples into pairs of lips.") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.LIPS); } };
						
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Areolae size (" + smallChangeMajorDrain + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Areolae size (" + smallChangeDrain + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Areolae size (" + smallChangeMinorDrain + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Areolae size (+" + smallChangeMinorBoost + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Areolae size (+" + smallChangeBoost + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Areolae size (+" + smallChangeMajorBoost + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMajorBoost); } };
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
								return new RacialEffectUtil("[style.colourTerrible(--)] Nipple capacity (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Nipple capacity (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Nipple capacity (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Nipple capacity (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Nipple capacity (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Nipple capacity (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Nipple depth (" + smallChangeMajorDrain + " depth)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Nipple depth (" + smallChangeDrain + " depth)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Nipple depth (" + smallChangeMinorDrain + " depth)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Nipple depth (+" + smallChangeMinorBoost + " depth)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Nipple depth (+" + smallChangeBoost + " depth)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Nipple depth (+" + smallChangeMajorBoost + " depth)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Nipple elasticity (" + smallChangeMajorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Nipple elasticity (" + smallChangeDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Nipple elasticity (" + smallChangeMinorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Nipple elasticity (+" + smallChangeMinorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Nipple elasticity (+" + smallChangeBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Nipple elasticity (+" + smallChangeMajorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Nipple plasticity (" + smallChangeMajorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Nipple plasticity (" + smallChangeDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Nipple plasticity (" + smallChangeMinorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Nipple plasticity (+" + smallChangeMinorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Nipple plasticity (+" + smallChangeBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Nipple plasticity (+" + smallChangeMajorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Milk storage (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Milk storage (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Milk storage (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Milk storage (+" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Milk storage (+" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Milk storage (+" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorBoost); } };
						}
					case TF_MOD_REGENERATION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Milk regeneration") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Milk regeneration") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Milk regeneration") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Milk regeneration") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Milk regeneration") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Milk regeneration") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeMajorBoost); } };
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
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(BreastType.getBreastTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(index).getTransformName())+" crotch-boob transformation.") {
							@Override public String applyEffect() { return target.setBreastCrotchType(BreastType.getBreastTypes(race).get(index)); } };
	
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
								return new RacialEffectUtil("[style.colourTerrible(--)] Crotch-boob size (" + smallChangeMajorDrain + " breast size)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Crotch-boob size (" + smallChangeDrain + " breast size)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Crotch-boob size (" + smallChangeMinorDrain + " breast size)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Crotch-boob size (" + smallChangeMinorBoost + " breast size)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Crotch-boob size (" + smallChangeBoost + " breast size)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Crotch-boob size (" + smallChangeMajorBoost + " breast size)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeMajorBoost); } };
						}
						

					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Nipple size (" + smallChangeMajorDrain + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Nipple size (" + smallChangeDrain + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Nipple size (" + smallChangeMinorDrain + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Nipple size (" + smallChangeMinorBoost + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Nipple size (" + smallChangeBoost + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Nipple size (" + smallChangeMajorBoost + " nipple size)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeMajorBoost); } };
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
						return new RacialEffectUtil("Turns nipples into a normal shape.") { @Override public String applyEffect() { return target.setNippleCrotchShape(NippleShape.NORMAL); } };
					case TF_MOD_NIPPLE_INVERTED:
						return new RacialEffectUtil("Turns nipples into a normal, although inverted, shape.") { @Override public String applyEffect() { return target.setNippleCrotchShape(NippleShape.INVERTED); } };
					case TF_MOD_NIPPLE_VAGINA:
						return new RacialEffectUtil("Turns nipples into vaginas.") { @Override public String applyEffect() { return target.setNippleCrotchShape(NippleShape.VAGINA); } };
					case TF_MOD_NIPPLE_LIPS:
						return new RacialEffectUtil("Turns nipples into pairs of lips.") { @Override public String applyEffect() { return target.setNippleCrotchShape(NippleShape.LIPS); } };
						
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Areolae size (" + smallChangeMajorDrain + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Areolae size (" + smallChangeDrain + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Areolae size (" + smallChangeMinorDrain + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Areolae size (" + smallChangeMinorBoost + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Areolae size (" + smallChangeBoost + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Areolae size (" + smallChangeMajorBoost + " areolae size)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeMajorBoost); } };
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
								return new RacialEffectUtil("[style.colourTerrible(--)] Nipple capacity (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Nipple capacity (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Nipple capacity (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Nipple capacity (" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Nipple capacity (" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Nipple capacity (" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Nipple depth (" + smallChangeMajorDrain + " depth)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Nipple depth (" + smallChangeDrain + " depth)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Nipple depth (" + smallChangeMinorDrain + " depth)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Nipple depth (+" + smallChangeMinorBoost + " depth)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Nipple depth (+" + smallChangeBoost + " depth)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Nipple depth (+" + smallChangeMajorBoost + " depth)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Nipple elasticity (" + smallChangeMajorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Nipple elasticity (" + smallChangeDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Nipple elasticity (" + smallChangeMinorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Nipple elasticity (" + smallChangeMinorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Nipple elasticity (" + smallChangeBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Nipple elasticity (" + smallChangeMajorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Nipple plasticity (" + smallChangeMajorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Nipple plasticity (" + smallChangeDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Nipple plasticity (" + smallChangeMinorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Nipple plasticity (" + smallChangeMinorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Nipple plasticity (" + smallChangeBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Nipple plasticity (" + smallChangeMajorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Milk storage (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Milk storage (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Milk storage (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Milk storage (" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Milk storage (" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Milk storage (" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMajorBoost); } };
						}
					case TF_MOD_REGENERATION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Milk regeneration") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Milk regeneration") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Milk regeneration") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Milk regeneration") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Milk regeneration") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Milk regeneration") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeMajorBoost); } };
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
								return new RacialEffectUtil("[style.colourTerrible(--)] Height (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMajorDrain, false); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Height (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeDrain, false); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Height (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMinorDrain, false); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Height (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMinorBoost, false); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Height (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeBoost, false); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Height (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMajorBoost, false); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Muscle mass (" + mediumChangeMajorDrain + " muscles)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Muscle mass (" + mediumChangeDrain + " muscles)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Muscle mass (" + mediumChangeMinorDrain + " muscles)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Muscle mass (+" + mediumChangeMinorBoost + " muscles)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Muscle mass (+" + mediumChangeBoost + " muscles)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Muscle mass (+" + mediumChangeMajorBoost + " muscles)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Body size (" + mediumChangeMajorDrain + " body size)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Body size (" + mediumChangeDrain + " body size)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Body size (" + mediumChangeMinorDrain + " body size)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Body size (+" + mediumChangeMinorBoost + " body size)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Body size (+" + mediumChangeBoost + " body size)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Body size (+" + mediumChangeMajorBoost + " body size)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMajorBoost); } };
						}
					case TF_MOD_FEMININITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Femininity (" + mediumChangeMajorDrain + " femininity)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Femininity (" + mediumChangeDrain + " femininity)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Femininity (" + mediumChangeMinorDrain + " femininity)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Femininity (+" + mediumChangeMinorBoost + " femininity)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Femininity (+" + mediumChangeBoost + " femininity)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Femininity (+" + mediumChangeMajorBoost + " femininity)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMajorBoost); } };
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
							case BOOST:
								return new RacialEffectUtil("Moves genitals and asshole into cloaca (at asshole location).") {
									@Override public String applyEffect() { return target.setGenitalArrangement(GenitalArrangement.CLOACA_BEHIND); }
								};
							case MINOR_BOOST:
								default:
								return new RacialEffectUtil("Moves genitals and asshole into cloaca (at genital location).") {
									@Override public String applyEffect() { return target.setGenitalArrangement(GenitalArrangement.CLOACA); }
								};
						}
						
					default:
						return new RacialEffectUtil("Random "+race.getName(false)+" transformation") {
							@Override
							public String applyEffect() {
								List<TFModifier> availableModifiers = new ArrayList<>();
								
								// Only add TFModifiers which will do something:
								for(TFModifier tfMod : TFModifier.getTFRacialBodyPartsList()) {
									boolean add = false;
									switch(tfMod) {
										case TF_ANTENNA:
											add = target.getAntennaType() != race.getRacialBody().getAntennaTypes(false).get(0);
											break;
										case TF_ARMS:
											add = target.getArmType() != race.getRacialBody().getArmType();
											break;
										case TF_ASS:
											add = target.getAssType() != race.getRacialBody().getAssType();
											break;
										case TF_BREASTS:
											add = target.getBreastType() != race.getRacialBody().getBreastType();
											break;
										case TF_BREASTS_CROTCH:
											add = target.hasBreastsCrotch() && target.getBreastCrotchType() != race.getRacialBody().getBreastCrotchType();
											break;
										case TF_CORE:
											break;
										case TF_EARS:
											add = target.getEarType() != race.getRacialBody().getEarType();
											break;
										case TF_EYES:
											add = target.getEyeType() != race.getRacialBody().getEyeType();
											break;
										case TF_FACE:
											add = target.getFaceType() != race.getRacialBody().getFaceType();
											break;
										case TF_HAIR:
											add = target.getHairType() != race.getRacialBody().getHairType();
											break;
										case TF_HORNS:
											add = target.getHornType() != race.getRacialBody().getHornTypes(false).get(0);
											break;
										case TF_LEGS:
											add = target.getLegType() != race.getRacialBody().getLegType();
											break;
										case TF_PENIS:
											add = target.hasPenisIgnoreDildo() && target.getPenisType() != race.getRacialBody().getPenisType();
											break;
										case TF_SKIN:
											add = target.getTorsoType() != race.getRacialBody().getTorsoType();
											break;
										case TF_TAIL:
											add = target.getTailType() != race.getRacialBody().getTailType().get(0);
											break;
										case TF_TENTACLE:
											break;
										case TF_VAGINA:
											add = target.hasVagina() && target.getVaginaType() != race.getRacialBody().getVaginaType();
											break;
										case TF_WINGS:
											add = target.getWingType() != race.getRacialBody().getWingTypes().get(0);
											break;
										default:
											break;
									}
									if(add) {
										availableModifiers.add(tfMod);
									}
								}
								
								if(availableModifiers.isEmpty()) {
									return UtilText.parse(target, "<p style='text-align:center'>[style.italicsDisabled([npc.NameHasFull] no more random "+race.getName(true)+" transformations available, so nothing happens...)]</p>");
								}
								
								
								TFModifier mod = availableModifiers.get(Util.random.nextInt(availableModifiers.size()));
								
								// If race does not have antenna, horns, tail, wings, or crotch-boobs, make sure that the TF is to remove:
								if((mod==TFModifier.TF_ANTENNA && race.getRacialBody().getAntennaTypes(false).size()==1 && race.getRacialBody().getAntennaTypes(false).contains(AntennaType.NONE))
										|| (mod==TFModifier.TF_HORNS && race.getRacialBody().getHornTypes(false).size()==1 && race.getRacialBody().getHornTypes(false).contains(HornType.NONE))
										|| (mod==TFModifier.TF_TAIL && race.getRacialBody().getTailType().size()==1 && race.getRacialBody().getTailType().contains(TailType.NONE))
										|| (mod==TFModifier.TF_WINGS && race.getRacialBody().getWingTypes().size()==1 && race.getRacialBody().getWingTypes().contains(WingType.NONE))
										|| ((mod==TFModifier.TF_BREASTS_CROTCH && race.getRacialBody().getBreastCrotchType()==BreastType.NONE))) {
									return getRacialEffect(race, mod, TFModifier.REMOVAL, potency, user, target).applyEffect();
								}
								
								return getRacialEffect(race, mod, secondaryModifier, potency, user, target).applyEffect();
							}
						};
				}
				
			case TF_EARS:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(EarType.getEarTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(EarType.getEarTypes(race).get(index).getTransformName())+" ears transformation.") {
							@Override public String applyEffect() { return target.setEarType(EarType.getEarTypes(race).get(index)); } };
	
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" ears transformation.") { @Override public String applyEffect() { return target.setEarType(RacialBody.valueOfRace(race).getEarType()); } };
				}
				
			case TF_EYES:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(EyeType.getEyeTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(EyeType.getEyeTypes(race).get(index).getTransformName())+" eyes transformation.") {
							@Override public String applyEffect() { return target.setEyeType(EyeType.getEyeTypes(race).get(index)); } };
						
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
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(FaceType.getFaceTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(FaceType.getFaceTypes(race).get(index).getTransformName())+" face transformation.") {
							@Override public String applyEffect() { return target.setFaceType(FaceType.getFaceTypes(race).get(index)); } };
						
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Lip size (" + smallChangeMajorDrain + " lip size)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Lip size (" + smallChangeDrain + " lip size)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Lip size (" + smallChangeMinorDrain + " lip size)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Lip size (+" + smallChangeMinorBoost + " lip size)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Lip size (+" + smallChangeBoost + " lip size)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Lip size (+" + smallChangeMajorBoost + " lip size)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMajorBoost); } };
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
								return new RacialEffectUtil("[style.colourTerrible(--)] Throat capacity (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Throat capacity (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Throat capacity (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Throat capacity (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Throat capacity (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Throat capacity (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Throat depth (" + smallChangeMajorDrain + " depth)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Throat depth (" + smallChangeDrain + " depth)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Throat depth (" + smallChangeMinorDrain + " depth)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Throat depth (+" + smallChangeMinorBoost + " depth)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Throat depth (+" + smallChangeBoost + " depth)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Throat depth (+" + smallChangeMajorBoost + " depth)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Throat elasticity (" + smallChangeMajorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Throat elasticity (" + smallChangeDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Throat elasticity (" + smallChangeMinorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Throat elasticity (+" + smallChangeMinorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Throat elasticity (+" + smallChangeBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Throat elasticity (+" + smallChangeMajorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Throat plasticity (" + smallChangeMajorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Throat plasticity (" + smallChangeDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Throat plasticity (" + smallChangeMinorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Throat plasticity (+" + smallChangeMinorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Throat plasticity (+" + smallChangeBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Throat plasticity (+" + smallChangeMajorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Saliva production (" + smallChangeMajorDrain + " wetness)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Saliva production (" + smallChangeDrain + " wetness)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Saliva production (" + smallChangeMinorDrain + " wetness)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Saliva production (+" + smallChangeMinorBoost + " wetness)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Saliva production (+" + smallChangeBoost + " wetness)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Saliva production (+" + smallChangeMajorBoost + " wetness)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Tongue length (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Tongue length (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Tongue length (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Tongue length (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Tongue length (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Tongue length (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMajorBoost); } };
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
					case TF_MOD_TONGUE_WIDE:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Reverts tongue widening.") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.WIDE); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Widens tongue.") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.WIDE); } };
						}
					case TF_MOD_TONGUE_FLAT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Reverts tongue flattening.") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.FLAT); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Flattens tongue.") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.FLAT); } };
						}
					case TF_MOD_TONGUE_STRONG:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes extra strength from tongue.") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.STRONG); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes tongue extra strong.") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.STRONG); } };
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
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(HairType.getHairTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(HairType.getHairTypes(race).get(index).getTransformName())+" hair transformation.") {
							@Override public String applyEffect() { return target.setHairType(HairType.getHairTypes(race).get(index)); } };
						
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Hair length (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Hair length (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Hair length (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Hair length (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Hair length (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Hair length (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMajorBoost); } };
						}

					case TF_MOD_SIZE_SECONDARY:
						if(potency.isNegative()) {
							return new RacialEffectUtil("Removes neck fluff.") { @Override public String applyEffect() { return target.setNeckFluff(false); } };
						} else {
							return new RacialEffectUtil("Adds neck fluff.") { @Override public String applyEffect() { return target.setNeckFluff(true); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" hair transformation.") { @Override public String applyEffect() { return target.setHairType(RacialBody.valueOfRace(race).getHairType()); } };
				}
				
			case TF_HORNS:
				switch(secondaryModifier) {
					case REMOVAL:
						return new RacialEffectUtil("Removes horns.") { @Override public String applyEffect() { return target.setHornType(HornType.NONE); } };
						
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = modifierTypeToInt(secondaryModifier);
						List<AbstractHornType> hornTypes = HornType.getHornTypes(race, false);
						AbstractHornType selectedHornType = index >= hornTypes.size() ? HornType.NONE : hornTypes.get(index);
						return new RacialEffectUtil(selectedHornType.equals(HornType.NONE)?"Removes horns.":"Grows "+selectedHornType.getTransformName()+" horn"+(selectedHornType==HornType.HORSE_STRAIGHT?"":"s")+".") {
							@Override public String applyEffect() { return target.setHornType(selectedHornType); } };
						
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Horn length (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Horn length (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Horn length (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Horn length (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Horn length (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Horn length (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMajorBoost); } };
						}
				
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra pair of horns.") { @Override public String applyEffect() { return target.incrementHornRows(singleDrain); } };
							case MINOR_BOOST: default:
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
								return new RacialEffectUtil("[style.colourTerrible(--)] Horns per row (" + smallChangeMajorDrain + " horns per row)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Horns per row (" + smallChangeDrain + " horns per row)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Horns per row (" + smallChangeMinorDrain + " horns per row)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Horns per row (+" + smallChangeMinorBoost + " horns per row)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Horns per row (+" + smallChangeBoost + " horns per row)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Horns per row (+" + smallChangeMajorBoost + " horns per row)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeMajorBoost); } };
						}
						
					default:
						List<AbstractHornType> defaultHornTypes = RacialBody.valueOfRace(race).getHornTypes(true);
						AbstractHornType hornType = defaultHornTypes.isEmpty()?HornType.NONE:Util.randomItemFrom(defaultHornTypes);
						return new RacialEffectUtil(hornType.equals(HornType.NONE)?"Removes horns.":Util.capitaliseSentence(race.getName(false))+" horn transformation.") {
							@Override public String applyEffect() { return target.setHornType(hornType); } };
				}
				
			case TF_LEGS:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(LegType.getLegTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(LegType.getLegTypes(race).get(index).getTransformName())+" legs transformation.") {
							@Override public String applyEffect() { return target.setLegType(LegType.getLegTypes(race).get(index)); } };

					case TF_MOD_LEG_CONFIG_BIPEDAL:
						return new RacialEffectUtil(" Transforms lower body into a pair of bipedal legs.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.BIPEDAL).applyLegConfigurationTransformation(target, LegConfiguration.BIPEDAL, true, true); } };

					case TF_MOD_LEG_CONFIG_TAUR:
						return new RacialEffectUtil(" Transforms lower body into a quadrupedal form.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.QUADRUPEDAL).applyLegConfigurationTransformation(target, LegConfiguration.QUADRUPEDAL, true, true); } };

					case TF_MOD_LEG_CONFIG_TAIL_LONG:
						return new RacialEffectUtil(" Transforms lower body into a long, snake-like tail.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.TAIL_LONG).applyLegConfigurationTransformation(target, LegConfiguration.TAIL_LONG, true, true); } };

					case TF_MOD_LEG_CONFIG_TAIL:
						return new RacialEffectUtil(" Transforms lower body into a fish-like tail.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.TAIL).applyLegConfigurationTransformation(target, LegConfiguration.TAIL, true, true); } };

					case TF_MOD_LEG_CONFIG_ARACHNID:
						return new RacialEffectUtil(" Transforms lower body into the form of an eight-legged arachnid.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.ARACHNID).applyLegConfigurationTransformation(target, LegConfiguration.ARACHNID, true, true); } };

					case TF_MOD_LEG_CONFIG_CEPHALOPOD:
						return new RacialEffectUtil(" Transforms lower body into the form of an eight-tentacled cephalopod.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.CEPHALOPOD).applyLegConfigurationTransformation(target, LegConfiguration.CEPHALOPOD, true, true); } };

					case TF_MOD_LEG_CONFIG_AVIAN:
						return new RacialEffectUtil(" Transforms lower body into the form of a feral bird.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.AVIAN).applyLegConfigurationTransformation(target, LegConfiguration.AVIAN, true, true); } };
							

					case TF_MOD_FOOT_STRUCTURE_PLANTIGRADE:
						return new RacialEffectUtil(" Transforms foot structure to be plantigrade.") {
							@Override public String applyEffect() { return target.setFootStructure(FootStructure.PLANTIGRADE); } };

					case TF_MOD_FOOT_STRUCTURE_DIGITIGRADE:
						return new RacialEffectUtil(" Transforms foot structure to be digitigrade.") {
							@Override public String applyEffect() { return target.setFootStructure(FootStructure.DIGITIGRADE); } };

					case TF_MOD_FOOT_STRUCTURE_UNGULIGRADE:
						return new RacialEffectUtil(" Transforms foot structure to be unguligrade.") {
							@Override public String applyEffect() { return target.setFootStructure(FootStructure.UNGULIGRADE); } };

					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Serpent-tail length (" + mediumChangeMajorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeMajorDrain/100f); }
									};
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Serpent-tail length (" + mediumChangeDrain + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeDrain/100f); }
									};
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Serpent-tail length (" + mediumChangeMinorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeMinorDrain/100f); }
									};
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Serpent-tail length (+" + mediumChangeMinorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeMinorBoost/100f); }
									};
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Serpent-tail length (+" + mediumChangeBoost + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeBoost/100f); }
									};
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Serpent-tail length (+" + mediumChangeMajorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeMajorBoost/100f); }
									};
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes puffiness from spinneret.") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes spinneret extra puffy.") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal ribbing from spinneret.") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal ribbing to spinneret.") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal muscles from spinneret.") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal muscles to spinneret.") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal tentacles from spinneret.") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal tentacles to spinneret.") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.TENTACLED); } };
						}
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Spinneret capacity (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Spinneret capacity (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Spinneret capacity (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Spinneret capacity (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Spinneret capacity (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Spinneret capacity (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Spinneret depth (" + smallChangeMajorDrain + " depth)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Spinneret depth (" + smallChangeDrain + " depth)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Spinneret depth (" + smallChangeMinorDrain + " depth)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Spinneret depth (+" + smallChangeMinorBoost + " depth)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Spinneret depth (+" + smallChangeBoost + " depth)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Spinneret depth (+" + smallChangeMajorBoost + " depth)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Spinneret elasticity (" + smallChangeMajorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Spinneret elasticity (" + smallChangeDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Spinneret elasticity (" + smallChangeMinorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Spinneret elasticity (+" + smallChangeMinorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Spinneret elasticity (+" + smallChangeBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Spinneret elasticity (+" + smallChangeMajorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Spinneret plasticity (" + smallChangeMajorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Spinneret plasticity (" + smallChangeDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Spinneret plasticity (" + smallChangeMinorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Spinneret plasticity (+" + smallChangeMinorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Spinneret plasticity (+" + smallChangeBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Spinneret plasticity (+" + smallChangeMajorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Spinneret wetness (" + smallChangeMajorDrain + " wetness)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Spinneret wetness (" + smallChangeDrain + " wetness)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Spinneret wetness (" + smallChangeMinorDrain + " wetness)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Spinneret wetness (+" + smallChangeMinorBoost + " wetness)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Spinneret wetness (+" + smallChangeBoost + " wetness)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Spinneret wetness (+" + smallChangeMajorBoost + " wetness)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMajorBoost); } };
						}
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" legs transformation.") { @Override public String applyEffect() { return target.setLegType(RacialBody.valueOfRace(race).getLegType()); } };
				}
				
			case TF_PENIS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Penis length (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Penis length (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Penis length (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Penis length (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Penis length (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Penis length (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Penis girth (" + smallChangeMajorDrain + " girth)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Penis girth (" + smallChangeDrain + " size)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Penis girth (" + smallChangeMinorDrain + " girth)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Penis girth (+" + smallChangeMinorBoost + " girth)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Penis girth (+" + smallChangeBoost + " girth)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Penis girth (+" + smallChangeMajorBoost + " girth)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMajorBoost); } };
						}
					case REMOVAL:
							return new RacialEffectUtil("Removes penis.") { @Override public String applyEffect() { return target.setPenisType(PenisType.NONE); } };

					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Removes a huge amount of pubic hair. (" + smallChangeMajorDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Removes a large amount of pubic hair. (" + smallChangeDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes some pubic hair. (" + smallChangeMinorDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds some pubic hair. (" + smallChangeMinorBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Adds a large amount of pubic hair. (" + smallChangeBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Adds a huge amount of pubic hair. (" + smallChangeMajorBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorBoost); } };
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
					case TF_MOD_PENIS_OVIPOSITOR:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes ovipositor functionality from penis.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.OVIPOSITOR); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds ovipositor functionality to penis.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.OVIPOSITOR); } };
						}

					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Testicle size (" + smallChangeMajorDrain + " size)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Testicle size (" + smallChangeDrain + " size)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Testicle size (" + smallChangeMinorDrain + " size)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Testicle size (+" + smallChangeMinorBoost + " size)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Testicle size (+" + smallChangeBoost + " size)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Testicle size (+" + smallChangeMajorBoost + " size)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMajorBoost); } };
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
								return new RacialEffectUtil("[style.colourTerrible(--)] Cum storage (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeMajorDrain);
									target.fillCumToMaxStorage();
									return s;} };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Cum storage (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeDrain);
									target.fillCumToMaxStorage();
									return s; } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Cum storage (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeMinorDrain);
									target.fillCumToMaxStorage();
									return s; } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Cum storage (+" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeMinorBoost);
									target.fillCumToMaxStorage();
									return s; } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Cum storage (+" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeBoost);
									target.fillCumToMaxStorage();
									return s; } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Cum storage (+" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeMajorBoost);
									target.fillCumToMaxStorage();
									return s; } };
						}
					case TF_MOD_REGENERATION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Cum regeneration") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Cum regeneration") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Cum regeneration") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Cum regeneration") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Cum regeneration") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Cum regeneration") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeMajorBoost); } };
						}
					case TF_MOD_CUM_EXPULSION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Cum amount at orgasm (" + mediumChangeMajorDrain + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Cum amount at orgasm (" + mediumChangeDrain + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Cum amount at orgasm (" + mediumChangeMinorDrain + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Cum amount at orgasm (+" + mediumChangeMinorBoost + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Cum amount at orgasm (+" + mediumChangeBoost + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Cum amount at orgasm (+" + mediumChangeMajorBoost + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeMajorBoost); } };
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
								return new RacialEffectUtil("[style.colourTerrible(--)] Urethra capacity (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Urethra capacity (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Urethra capacity (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Urethra capacity (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Urethra capacity (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Urethra capacity (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Urethra depth (" + smallChangeMajorDrain + " depth)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Urethra depth (" + smallChangeDrain + " depth)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Urethra depth (" + smallChangeMinorDrain + " depth)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Urethra depth (+" + smallChangeMinorBoost + " depth)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Urethra depth (+" + smallChangeBoost + " depth)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Urethra depth (+" + smallChangeMajorBoost + " depth)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Urethra elasticity (" + smallChangeMajorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Urethra elasticity (" + smallChangeDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Urethra elasticity (" + smallChangeMinorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Urethra elasticity (+" + smallChangeMinorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Urethra elasticity (+" + smallChangeBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Urethra elasticity (+" + smallChangeMajorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Urethra plasticity (" + smallChangeMajorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Urethra plasticity (" + smallChangeDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Urethra plasticity (" + smallChangeMinorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Urethra plasticity (+" + smallChangeMinorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Urethra plasticity (+" + smallChangeBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Urethra plasticity (+" + smallChangeMajorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMajorBoost); } };
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

					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(PenisType.getPenisTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(
								PenisType.getPenisTypes(race).get(index)==PenisType.NONE
									?"Removes penis."
									:Util.capitaliseSentence(PenisType.getPenisTypes(race).get(index).getTransformName())+" penis transformation.") {
							@Override public String applyEffect() { return target.setPenisType(PenisType.getPenisTypes(race).get(index)); } };
							
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" penis transformation.") { @Override public String applyEffect() { return target.setPenisType(RacialBody.valueOfRace(race).getPenisType()); } };
				}
				
			case TF_SKIN:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(TorsoType.getTorsoTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(TorsoType.getTorsoTypes(race).get(index).getTransformName())+" torso transformation.") {
							@Override public String applyEffect() { return target.setTorsoType(TorsoType.getTorsoTypes(race).get(index)); } };
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" torso transformation.") { @Override public String applyEffect() { return target.setTorsoType(RacialBody.valueOfRace(race).getTorsoType()); } };
				}
				
			case TF_TAIL:
				switch(secondaryModifier) {
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes an extra tail.") { @Override public String applyEffect() { return target.incrementTailCount(singleDrain, false); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds an extra tail.") { @Override public String applyEffect() {
									List<AbstractTailType> tailTypesSuitableForTransformation = TailType.getTailTypesSuitableForTransformation(RacialBody.valueOfRace(race).getTailType());
									if(target.getTailType()==TailType.NONE && !tailTypesSuitableForTransformation.isEmpty()) {
										return target.setTailType(tailTypesSuitableForTransformation.get(0));
									} else {
										return target.incrementTailCount(singleBoost, false);
									} } };
						}

					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Tail length (" + mediumChangeMajorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeMajorDrain/100f); }
									};
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Tail length (" + mediumChangeDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeDrain/100f); }
									};
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Tail length (" + mediumChangeMinorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeMinorDrain/100f); }
									};
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Tail length (+" + mediumChangeMinorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeMinorBoost/100f); }
									};
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Tail length (+" + mediumChangeBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeBoost/100f); }
									};
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Tail length (+" + mediumChangeMajorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeMajorBoost/100f); }
									};
						}
						
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Tail girth (" + smallChangeMajorDrain + " girth)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Tail girth (" + smallChangeDrain + " girth)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Tail girth (" + smallChangeMinorDrain + " girth)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Tail girth (+" + smallChangeMinorBoost + " girth)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Tail girth (+" + smallChangeBoost + " girth)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Tail girth (+" + smallChangeMajorBoost + " girth)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeMajorBoost); } };
						}
						
					case REMOVAL:
						return new RacialEffectUtil("Removes tail.") { @Override public String applyEffect() { return target.setTailType(TailType.NONE); } };

					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(TailType.getTailTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(
								TailType.getTailTypes(race).get(index)==TailType.NONE
									?"Removes tail."
									:Util.capitaliseSentence(TailType.getTailTypes(race).get(index).getTransformName())+" tail transformation.") {
							@Override public String applyEffect() { return target.setTailType(TailType.getTailTypes(race).get(index)); } };

					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes puffiness from spinneret.") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes spinneret extra puffy.") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal ribbing from spinneret.") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal ribbing to spinneret.") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal muscles from spinneret.") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal muscles to spinneret.") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal tentacles from spinneret.") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal tentacles to spinneret.") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.TENTACLED); } };
						}
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Spinneret capacity (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Spinneret capacity (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Spinneret capacity (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Spinneret capacity (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Spinneret capacity (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Spinneret capacity (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Spinneret depth (" + smallChangeMajorDrain + " depth)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Spinneret depth (" + smallChangeDrain + " depth)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Spinneret depth (" + smallChangeMinorDrain + " depth)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Spinneret depth (+" + smallChangeMinorBoost + " depth)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Spinneret depth (+" + smallChangeBoost + " depth)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Spinneret depth (+" + smallChangeMajorBoost + " depth)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Spinneret elasticity (" + smallChangeMajorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Spinneret elasticity (" + smallChangeDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Spinneret elasticity (" + smallChangeMinorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Spinneret elasticity (+" + smallChangeMinorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Spinneret elasticity (+" + smallChangeBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Spinneret elasticity (+" + smallChangeMajorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Spinneret plasticity (" + smallChangeMajorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Spinneret plasticity (" + smallChangeDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Spinneret plasticity (" + smallChangeMinorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Spinneret plasticity (+" + smallChangeMinorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Spinneret plasticity (+" + smallChangeBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Spinneret plasticity (+" + smallChangeMajorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Spinneret wetness (" + smallChangeMajorDrain + " wetness)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Spinneret wetness (" + smallChangeDrain + " wetness)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Spinneret wetness (" + smallChangeMinorDrain + " wetness)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Spinneret wetness (+" + smallChangeMinorBoost + " wetness)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Spinneret wetness (+" + smallChangeBoost + " wetness)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Spinneret wetness (+" + smallChangeMajorBoost + " wetness)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMajorBoost); } };
						}
							
					default:
						AbstractTailType tailType = RacialBody.valueOfRace(race).getRandomTailType(false);
						return new RacialEffectUtil(tailType==TailType.NONE?"Removes tail.":Util.capitaliseSentence(race.getName(false))+" tail transformation.") {
							@Override public String applyEffect() { return target.setTailType(tailType); } };
				}
				
			case TF_TENTACLE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Tentacle length (" + mediumChangeMajorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeMajorDrain/100f); }
									};
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Tentacle length (" + mediumChangeDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeDrain/100f); }
									};
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Tentacle length (" + mediumChangeMinorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeMinorDrain/100f); }
									};
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Tentacle length (+" + mediumChangeMinorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeMinorBoost/100f); }
									};
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Tentacle length (+" + mediumChangeBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeBoost/100f); }
									};
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Tentacle length (+" + mediumChangeMajorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeMajorBoost/100f); }
									};
						}
						
					default://case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Tentacle girth (" + smallChangeMajorDrain + " girth)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Tentacle girth (" + smallChangeDrain + " girth)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Tentacle girth (" + smallChangeMinorDrain + " girth)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Tentacle girth (+" + smallChangeMinorBoost + " girth)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Tentacle girth (+" + smallChangeBoost + " girth)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Tentacle girth (+" + smallChangeMajorBoost + " girth)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeMajorBoost); } };
						}
				}
				
			case TF_VAGINA:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Clitoris length (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Clitoris length (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Clitoris length (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Clitoris length (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Clitoris length (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Clitoris length (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Clitoris girth (" + smallChangeMajorDrain + " girth)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Clitoris girth (" + smallChangeDrain + " girth)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Clitoris girth (" + smallChangeMinorDrain + " girth)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Clitoris girth (+" + smallChangeMinorBoost + " girth)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Clitoris girth (+" + smallChangeBoost + " girth)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Clitoris girth (+" + smallChangeMajorBoost + " girth)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Labia size (" + smallChangeMajorDrain + " size)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Labia size (" + smallChangeDrain + " size)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Labia size (" + smallChangeMinorDrain + " size)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Labia size (+" + smallChangeMinorBoost + " size)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Labia size (+" + smallChangeBoost + " size)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Labia size (+" + smallChangeMajorBoost + " size)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMajorBoost); } };
						}
					case REMOVAL:
							return new RacialEffectUtil("Removes vagina.") { @Override public String applyEffect() { return target.setVaginaType(VaginaType.NONE); } };
							
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Removes a huge amount of pubic hair. (" + smallChangeMajorDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Removes a large amount of pubic hair. (" + smallChangeDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes some pubic hair. (" + smallChangeMinorDrain + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds some pubic hair. (" + smallChangeMinorBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Adds a large amount of pubic hair. (" + smallChangeBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Adds a huge amount of pubic hair. (" + smallChangeMajorBoost + " hairiness)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorBoost); } };
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
					case TF_MOD_PENIS_OVIPOSITOR:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes ovipositor functionality from clitoris.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.OVIPOSITOR); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds ovipositor functionality to clitoris.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.OVIPOSITOR); } };
						}
							
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Vagina capacity (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Vagina capacity (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Vagina capacity (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Vagina capacity (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Vagina capacity (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Vagina capacity (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Vagina depth (" + smallChangeMajorDrain + " depth)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Vagina depth (" + smallChangeDrain + " depth)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Vagina depth (" + smallChangeMinorDrain + " depth)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Vagina depth (+" + smallChangeMinorBoost + " depth)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Vagina depth (+" + smallChangeBoost + " depth)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Vagina depth (+" + smallChangeMajorBoost + " depth)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Vagina elasticity (" + smallChangeMajorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Vagina elasticity (" + smallChangeDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Vagina elasticity (" + smallChangeMinorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Vagina elasticity (+" + smallChangeMinorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Vagina elasticity (+" + smallChangeBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Vagina elasticity (+" + smallChangeMajorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Vagina plasticity (" + smallChangeMajorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Vagina plasticity (" + smallChangeDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Vagina plasticity (" + smallChangeMinorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Vagina plasticity (+" + smallChangeMinorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Vagina plasticity (+" + smallChangeBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Vagina plasticity (+" + smallChangeMajorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Vaginal lubrication (" + Units.fluid(smallChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Vaginal lubrication (" + Units.fluid(smallChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Vaginal lubrication (" + Units.fluid(smallChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Vaginal lubrication (+" + Units.fluid(smallChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Vaginal lubrication (+" + Units.fluid(smallChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Vaginal lubrication (+" + Units.fluid(smallChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorBoost); } };
						}

					case TF_MOD_VAGINA_SQUIRTER:
						switch(potency) {
							case MINOR_BOOST:
								return new RacialEffectUtil("Makes vagina squirt on orgasm.") { @Override public String applyEffect() { return target.setVaginaSquirter(true); } };
							case MINOR_DRAIN: default:
								return new RacialEffectUtil("Stops vagina from squirting on orgasm.") { @Override public String applyEffect() { return target.setVaginaSquirter(false); } };
						}

					case TF_MOD_VAGINA_EGG_LAYER:
						switch(potency) {
							case MINOR_BOOST:
								return new RacialEffectUtil("Makes vagina lay eggs.") { @Override public String applyEffect() { return target.setVaginaEggLayer(true); } };
							case MINOR_DRAIN: default:
								return new RacialEffectUtil("Makes vagina birth live young.") { @Override public String applyEffect() { return target.setVaginaEggLayer(false); } };
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
								return new RacialEffectUtil("[style.colourTerrible(--)] Urethra capacity (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Urethra capacity (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Urethra capacity (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Urethra capacity (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Urethra capacity (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Urethra capacity (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Urethra depth (" + smallChangeMajorDrain + " depth)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Urethra depth (" + smallChangeDrain + " depth)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Urethra depth (" + smallChangeMinorDrain + " depth)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Urethra depth (+" + smallChangeMinorBoost + " depth)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Urethra depth (+" + smallChangeBoost + " depth)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Urethra depth (+" + smallChangeMajorBoost + " depth)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Urethra elasticity (" + smallChangeMajorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Urethra elasticity (" + smallChangeDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Urethra elasticity (" + smallChangeMinorDrain + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Urethra elasticity (+" + smallChangeMinorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Urethra elasticity (+" + smallChangeBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Urethra elasticity (+" + smallChangeMajorBoost + " elasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Urethra plasticity (" + smallChangeMajorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Urethra plasticity (" + smallChangeDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Urethra plasticity (" + smallChangeMinorDrain + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Urethra plasticity (+" + smallChangeMinorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Urethra plasticity (+" + smallChangeBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Urethra plasticity (+" + smallChangeMajorBoost + " plasticity)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMajorBoost); } };
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

					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(VaginaType.getVaginaTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(
								VaginaType.getVaginaTypes(race).get(index)==VaginaType.NONE
									?"Removes penis."
									:Util.capitaliseSentence(VaginaType.getVaginaTypes(race).get(index).getTransformName())+" vagina transformation.") {
							@Override public String applyEffect() { return target.setVaginaType(VaginaType.getVaginaTypes(race).get(index)); } };
							
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" vagina transformation.") { @Override public String applyEffect() { return target.setVaginaType(RacialBody.valueOfRace(race).getVaginaType()); } };
				}
				
			case TF_WINGS:
				switch(secondaryModifier) {
					case REMOVAL:
						return new RacialEffectUtil("Removes wings.") { @Override public String applyEffect() { return target.setWingType(WingType.NONE); } };

					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(WingType.getWingTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(WingType.getWingTypes(race).get(index)==WingType.NONE?"Removes wings.":Util.capitaliseSentence(WingType.getWingTypes(race).get(index).getTransformName())+" wings transformation.") {
							@Override public String applyEffect() { return target.setWingType(WingType.getWingTypes(race).get(index)); } };
							
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Wing size (" + smallChangeMajorDrain + " wing size)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Wing size (" + smallChangeDrain + " wing size)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Wing size (" + smallChangeMinorDrain + " wing size)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Wing size (+" + smallChangeMinorBoost + " wing size)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Wing size (+" + smallChangeBoost + " wing size)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Wing size (+" + smallChangeMajorBoost + " wing size)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMajorBoost); } };
						}
					default:
						AbstractWingType wingType = RacialBody.valueOfRace(race).getRandomWingType(false);
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
					case TF_MOD_FLAVOUR_COFFEE:
						return new RacialEffectUtil("Makes cum taste like coffee.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.COFFEE); } };
					case TF_MOD_FLAVOUR_TEA:
						return new RacialEffectUtil("Makes cum taste like tea.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.TEA); } };
					case TF_MOD_FLAVOUR_MAPLE:
						return new RacialEffectUtil("Makes cum taste like maple.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.MAPLE); } };
					case TF_MOD_FLAVOUR_CINNAMON:
						return new RacialEffectUtil("Makes cum taste like cinnamon.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.CINNAMON); } };
					case TF_MOD_FLAVOUR_LEMON:
						return new RacialEffectUtil("Makes cum taste like lemon.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.LEMON); } };
					case TF_MOD_FLAVOUR_ORANGE:
						return new RacialEffectUtil("Makes cum taste like orange.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.ORANGE); } };
					case TF_MOD_FLAVOUR_GRAPE:
						return new RacialEffectUtil("Makes cum taste like grape.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.GRAPE); } };
					case TF_MOD_FLAVOUR_MELON:
						return new RacialEffectUtil("Makes cum taste like melon.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.MELON); } };
					case TF_MOD_FLAVOUR_COCONUT:
						return new RacialEffectUtil("Makes cum taste like coconut.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.COCONUT); } };
					case TF_MOD_FLAVOUR_BLUEBERRY:
						return new RacialEffectUtil("Makes cum taste like blueberries.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.BLUEBERRY); } };
	
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
								return new RacialEffectUtil("[style.colourTerrible(--)] Cum storage (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Cum storage (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Cum storage (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Cum storage (+" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Cum storage (+" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Cum storage (+" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeMajorBoost); } };
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
					case TF_MOD_FLAVOUR_COFFEE:
						return new RacialEffectUtil("Makes milk taste like coffee.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.COFFEE); } };
					case TF_MOD_FLAVOUR_TEA:
						return new RacialEffectUtil("Makes milk taste like tea.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.TEA); } };
					case TF_MOD_FLAVOUR_MAPLE:
						return new RacialEffectUtil("Makes milk taste like maple.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.MAPLE); } };
					case TF_MOD_FLAVOUR_CINNAMON:
						return new RacialEffectUtil("Makes milk taste like cinnamon.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.CINNAMON); } };
					case TF_MOD_FLAVOUR_LEMON:
						return new RacialEffectUtil("Makes milk taste like lemon.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.LEMON); } };
					case TF_MOD_FLAVOUR_ORANGE:
						return new RacialEffectUtil("Makes milk taste like orange.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.ORANGE); } };
					case TF_MOD_FLAVOUR_GRAPE:
						return new RacialEffectUtil("Makes milk taste like grape.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.GRAPE); } };
					case TF_MOD_FLAVOUR_MELON:
						return new RacialEffectUtil("Makes milk taste like melon.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.MELON); } };
					case TF_MOD_FLAVOUR_COCONUT:
						return new RacialEffectUtil("Makes milk taste like coconut.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.COCONUT); } };
					case TF_MOD_FLAVOUR_BLUEBERRY:
						return new RacialEffectUtil("Makes milk taste like blueberries.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.BLUEBERRY); } };
	
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
								return new RacialEffectUtil("[style.colourTerrible(--)] Milk storage (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Milk storage (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Milk storage (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Milk storage (+" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Milk storage (+" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Milk storage (+" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorBoost); } };
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
					case TF_MOD_FLAVOUR_COFFEE:
						return new RacialEffectUtil("Makes udder-milk taste like coffee.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.COFFEE); } };
					case TF_MOD_FLAVOUR_TEA:
						return new RacialEffectUtil("Makes udder-milk taste like tea.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.TEA); } };
					case TF_MOD_FLAVOUR_MAPLE:
						return new RacialEffectUtil("Makes udder-milk taste like maple.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.MAPLE); } };
					case TF_MOD_FLAVOUR_CINNAMON:
						return new RacialEffectUtil("Makes udder-milk taste like cinnamon.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.CINNAMON); } };
					case TF_MOD_FLAVOUR_LEMON:
						return new RacialEffectUtil("Makes udder-milk taste like lemon.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.LEMON); } };
					case TF_MOD_FLAVOUR_ORANGE:
						return new RacialEffectUtil("Makes udder-milk taste like orange.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.ORANGE); } };
					case TF_MOD_FLAVOUR_GRAPE:
						return new RacialEffectUtil("Makes udder-milk taste like grape.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.GRAPE); } };
					case TF_MOD_FLAVOUR_MELON:
						return new RacialEffectUtil("Makes udder-milk taste like melon.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.MELON); } };
					case TF_MOD_FLAVOUR_COCONUT:
						return new RacialEffectUtil("Makes udder-milk taste like coconut.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.COCONUT); } };
					case TF_MOD_FLAVOUR_BLUEBERRY:
						return new RacialEffectUtil("Makes udder-milk taste like blueberries.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.BLUEBERRY); } };
	
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
								return new RacialEffectUtil("[style.colourTerrible(--)] Udder-milk storage (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Udder-milk storage (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Udder-milk storage (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Udder-milk storage (" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Udder-milk storage (" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Udder-milk storage (" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMajorBoost); } };
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
					case TF_MOD_FLAVOUR_COFFEE:
						return new RacialEffectUtil("Makes girlcum taste like coffee.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.COFFEE); } };
					case TF_MOD_FLAVOUR_TEA:
						return new RacialEffectUtil("Makes girlcum taste like tea.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.TEA); } };
					case TF_MOD_FLAVOUR_MAPLE:
						return new RacialEffectUtil("Makes girlcum taste like maple.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.MAPLE); } };
					case TF_MOD_FLAVOUR_CINNAMON:
						return new RacialEffectUtil("Makes girlcum taste like cinnamon.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.CINNAMON); } };
					case TF_MOD_FLAVOUR_LEMON:
						return new RacialEffectUtil("Makes girlcum taste like lemons.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.LEMON); } };
					case TF_MOD_FLAVOUR_ORANGE:
						return new RacialEffectUtil("Makes girlcum taste like orange.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.ORANGE); } };
					case TF_MOD_FLAVOUR_GRAPE:
						return new RacialEffectUtil("Makes girlcum taste like grape.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.GRAPE); } };
					case TF_MOD_FLAVOUR_MELON:
						return new RacialEffectUtil("Makes girlcum taste like melon.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.MELON); } };
					case TF_MOD_FLAVOUR_COCONUT:
						return new RacialEffectUtil("Makes girlcum taste like coconut.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.COCONUT); } };
					case TF_MOD_FLAVOUR_BLUEBERRY:
						return new RacialEffectUtil("Makes girlcum taste like blueberries.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.BLUEBERRY); } };
	
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
								return new RacialEffectUtil("[style.colourTerrible(--)] Vaginal lubrication (" + Units.fluid(smallChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Vaginal lubrication (" + Units.fluid(smallChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Vaginal lubrication (" + Units.fluid(smallChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Vaginal lubrication (+" + Units.fluid(smallChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Vaginal lubrication (+" + Units.fluid(smallChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Vaginal lubrication (+" + Units.fluid(smallChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorBoost); } };
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

	private static RacialEffectUtil getAntennaTypeRacialEffectUtil(AbstractRace race, GameCharacter target, int index) {
		List<AbstractAntennaType> antennaTypes = RacialBody.valueOfRace(race).getAntennaTypes(true);
		AbstractAntennaType selectedAntennaType = index >= antennaTypes.size() ? AntennaType.NONE : antennaTypes.get(index);
		
		return new RacialEffectUtil("Grows "+selectedAntennaType.getTransformName()+" antennae.") {
			@Override public String applyEffect() { return target.setAntennaType(selectedAntennaType); } };
	}

}
