package com.lilithsthrone.game.inventory.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
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
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryBookAddedToLibrary;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.4
 * @version 0.2.4
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
	
	protected static String getBookEffect(Race race, AbstractItemType book) {
		Main.getProperties().addRaceDiscovered(race);
		if(Main.getProperties().addAdvancedRaceKnowledge(race) && book!=null) {
			Main.game.addEvent(new EventLogEntryBookAddedToLibrary(book), true);
		}
		
		if(Main.game.getPlayer().addRaceDiscoveredFromBook(race)) {
			return race.getBasicDescription()
					+race.getAdvancedDescription()
					+Main.game.getPlayer().incrementAttribute(race.getDamageMultiplier(), 5f)
					+Main.game.getPlayer().incrementAttribute(race.getResistanceMultiplier(), 5f);
			
		} else {
			return race.getBasicDescription()
					+race.getAdvancedDescription()
					+"<p style='text-align:center; color:"+Colour.TEXT_GREY.toWebHexString()+";'>"
						+ "Nothing further can be gained from re-reading this book..."
					+ "</p>";
		}
		
	}
	
	protected static List<TFModifier> getClothingTFSecondaryModifiers(TFModifier primaryModifier) {
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
	
	protected static int getClothingTFLimits(TFModifier primaryModifier, TFModifier secondaryModifier) {

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
						return CupSize.getMaximumCupSize().getMeasurement();
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
	
	protected static List<String> getClothingTFDescriptions(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
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

		int heightIncrement = (potency.isNegative()?-1:1);
		int muscleIncrement = (potency.isNegative()?-1:1);
		int bodySizeIncrement = (potency.isNegative()?-1:1);
		int femininityIncrement = (potency.isNegative()?-1:1);

		int lipSizeIncrement = (potency.isNegative()?-1:1);

		int hairLengthIncrement = (potency.isNegative()?-1:1);

		int penisSizeIncrement = (potency.isNegative()?-1:1);
		int testicleSizeIncrement = (potency.isNegative()?-1:1);
		int cumProductionIncrement = (potency.isNegative()?-5:5);

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
						default:
							break;
					}
					break;
				case TF_CORE:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(heightIncrement, target.getHeightValue()-Height.ZERO_TINY.getMinimumValue(), limit)) {
								sb.append(target.incrementHeight(heightIncrement));
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
							if(isWithinLimits(cumProductionIncrement, target.getPenisRawCumProductionValue(), limit)) {
								sb.append(target.incrementPenisCumProduction(cumProductionIncrement));
							} else if(isSetToLimit(cumProductionIncrement, target.getPenisRawCumProductionValue(), limit)) {
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
	
	private static boolean isSetToLimit(int increment, float currentValue, int limit) {
		if(increment<0) {
			if(currentValue <= limit) {
				return false;
			}
		} else {
			if(currentValue >= limit) {
				return false;
			}
		}
		return true;
	}
	
	protected static List<String> descriptions = new ArrayList<>();
	protected static List<String> genericAttributeEffectDescription(ResourceRestoration restorationType, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit) {
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
	
	private static void addResourceDescriptionsRestore(int value, ResourceRestoration restorationType) {
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
	private static void addResourceDescriptionsDrain(int value, ResourceRestoration restorationType) {
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
	
	protected static String genericAttributeEffect(ResourceRestoration restorationType, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {

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
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
			
			case TF_ARMS:
				for(int i=0; i< ArmType.getArmTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				if(Main.game.isBodyHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_ASS:
				for(int i=0; i< AssType.getAssTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
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

				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
				
			case TF_BREASTS:
				for(int i=0; i< BreastType.getBreastTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				}
				
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

				if(Main.getProperties().hasValue(PropertyValue.nipplePenContent)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_REGENERATION, TFPotency.getAllPotencies());

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
				for(int i=0; i< EyeType.getEyeTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				}
				
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
				for(int i=0; i< FaceType.getFaceTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));

				secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_RIBBED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_TENTACLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_BIFURCATED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				
				if(Main.game.isFacialHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_HAIR:
				for(int i=0; i< HairType.getHairTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				break;
				
			case TF_HORNS:
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				for(int i=0; i< RacialBody.valueOfRace(race).getHornType().size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				break;
				
			case TF_LEGS:
				for(int i=0; i< LegType.getLegTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				}
				break;
				
			case TF_PENIS:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
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
				
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
//				secondaryModPotencyMap.put(TFModifier.TF_MOD_REGENERATION, TFPotency.getAllPotencies()); TODO
				secondaryModPotencyMap.put(TFModifier.TF_MOD_INTERNAL, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				
				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
	
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				}
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
				
				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY_2, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY_2, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY_2, TFPotency.getAllPotencies());
	
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY_2, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED_2, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED_2, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED_2, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_DRAIN), new ListValue<>(TFPotency.MINOR_BOOST)));
				}
				
				break;
				
			case TF_WINGS:
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				for(int i=0; i< WingType.getWingTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(new ListValue<>(TFPotency.MINOR_BOOST)));
				}
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
	
	protected static RacialEffectUtil getRacialEffect(Race race, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, GameCharacter user, GameCharacter target) {
		
		switch(primaryModifier) {
			case TF_ANTENNA:
				switch(secondaryModifier) {
					case TF_TYPE_1:
						return new RacialEffectUtil(AntennaType.getAntennaTypes(race).get(0)==AntennaType.NONE?"Removes antennae.":Util.capitaliseSentence(AntennaType.getAntennaTypes(race).get(0).getTransformName())+" antenna transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setAntennaType(AntennaType.getAntennaTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(AntennaType.getAntennaTypes(race).get(1)==AntennaType.NONE?"Removes antennae.":Util.capitaliseSentence(AntennaType.getAntennaTypes(race).get(1).getTransformName())+" antenna transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setAntennaType(AntennaType.getAntennaTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(AntennaType.getAntennaTypes(race).get(2)==AntennaType.NONE?"Removes antennae.":Util.capitaliseSentence(AntennaType.getAntennaTypes(race).get(2).getTransformName())+" antenna transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setAntennaType(AntennaType.getAntennaTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(AntennaType.getAntennaTypes(race).get(3)==AntennaType.NONE?"Removes antennae.":Util.capitaliseSentence(AntennaType.getAntennaTypes(race).get(3).getTransformName())+" antenna transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setAntennaType(AntennaType.getAntennaTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(AntennaType.getAntennaTypes(race).get(4)==AntennaType.NONE?"Removes antennae.":Util.capitaliseSentence(AntennaType.getAntennaTypes(race).get(4).getTransformName())+" antenna transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setAntennaType(AntennaType.getAntennaTypes(race).get(4)); } };
						
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
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(ArmType.getArmTypes(race).get(0).getTransformName())+" arm transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setArmType(ArmType.getArmTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(ArmType.getArmTypes(race).get(1).getTransformName())+" arm transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setArmType(ArmType.getArmTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(ArmType.getArmTypes(race).get(2).getTransformName())+" arm transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setArmType(ArmType.getArmTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(ArmType.getArmTypes(race).get(3).getTransformName())+" arm transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setArmType(ArmType.getArmTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(ArmType.getArmTypes(race).get(4).getTransformName())+" arm transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setArmType(ArmType.getArmTypes(race).get(4)); } };
						
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
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(AssType.getAssTypes(race).get(0).getTransformName())+" ass transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setAssType(AssType.getAssTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(AssType.getAssTypes(race).get(1).getTransformName())+" ass transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setAssType(AssType.getAssTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(AssType.getAssTypes(race).get(2).getTransformName())+" ass transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setAssType(AssType.getAssTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(AssType.getAssTypes(race).get(3).getTransformName())+" ass transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setAssType(AssType.getAssTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(AssType.getAssTypes(race).get(4).getTransformName())+" ass transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setAssType(AssType.getAssTypes(race).get(4)); } };
						
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
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(0).getTransformName())+" breast transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setBreastType(BreastType.getBreastTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(1).getTransformName())+" breast transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setBreastType(BreastType.getBreastTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(2).getTransformName())+" breast transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setBreastType(BreastType.getBreastTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(3).getTransformName())+" breast transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setBreastType(BreastType.getBreastTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(4).getTransformName())+" breast transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setBreastType(BreastType.getBreastTypes(race).get(4)); } };
						
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
								return new RacialEffectUtil("Huge decrease in milk storage.", largeChangeMajorDrain, "ml") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in milk storage.", largeChangeDrain, "ml") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in milk storage.", largeChangeMinorDrain, "ml") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in milk storage.", largeChangeMinorBoost, "ml") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in milk storage.", largeChangeBoost, "ml") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in milk storage.", largeChangeMajorBoost, "ml") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorBoost); } };
						}
					case TF_MOD_REGENERATION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in milk regeneration.", largeChangeMajorDrain, "") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in milk regeneration.", largeChangeDrain, "") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in milk regeneration.", largeChangeMinorDrain, "") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(largeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in milk regeneration.", largeChangeMinorBoost, "") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in milk regeneration.", largeChangeBoost, "") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in milk regeneration.", largeChangeMajorBoost, "") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(largeChangeMajorBoost); } };
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
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(EyeType.getEyeTypes(race).get(0).getTransformName())+" eyes transformation.", 0, "") {
							@Override public String applyEffect() { return target.setEyeType(EyeType.getEyeTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(EyeType.getEyeTypes(race).get(1).getTransformName())+" eyes transformation.", 0, "") {
							@Override public String applyEffect() { return target.setEyeType(EyeType.getEyeTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(EyeType.getEyeTypes(race).get(2).getTransformName())+" eyes transformation.", 0, "") {
							@Override public String applyEffect() { return target.setEyeType(EyeType.getEyeTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(EyeType.getEyeTypes(race).get(3).getTransformName())+" eyes transformation.", 0, "") {
							@Override public String applyEffect() { return target.setEyeType(EyeType.getEyeTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(EyeType.getEyeTypes(race).get(4).getTransformName())+" eyes transformation.", 0, "") {
							@Override public String applyEffect() { return target.setEyeType(EyeType.getEyeTypes(race).get(4)); } };
						
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
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(FaceType.getFaceTypes(race).get(0).getTransformName())+" face transformation.", 0, "") {
							@Override public String applyEffect() { return target.setFaceType(FaceType.getFaceTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(FaceType.getFaceTypes(race).get(1).getTransformName())+" face transformation.", 0, "") {
							@Override public String applyEffect() { return target.setFaceType(FaceType.getFaceTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(FaceType.getFaceTypes(race).get(2).getTransformName())+" face transformation.", 0, "") {
							@Override public String applyEffect() { return target.setFaceType(FaceType.getFaceTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(FaceType.getFaceTypes(race).get(3).getTransformName())+" face transformation.", 0, "") {
							@Override public String applyEffect() { return target.setFaceType(FaceType.getFaceTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(FaceType.getFaceTypes(race).get(4).getTransformName())+" face transformation.", 0, "") {
							@Override public String applyEffect() { return target.setFaceType(FaceType.getFaceTypes(race).get(4)); } };
						
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
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in throat capacity.", mediumChangeMajorDrain, " capacity") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in throat capacity.", mediumChangeDrain, " capacity") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in throat capacity.", mediumChangeMinorDrain, " capacity") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in throat capacity.", mediumChangeMinorBoost, " capacity") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in throat capacity.", mediumChangeBoost, " capacity") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in throat capacity.", mediumChangeMajorBoost, " capacity") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in throat elasticity.", smallChangeMajorDrain, " elasticity") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in throat elasticity.", smallChangeDrain, " elasticity") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in throat elasticity.", smallChangeMinorDrain, " elasticity") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in throat elasticity.", smallChangeMinorBoost, " elasticity") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in throat elasticity.", smallChangeBoost, " elasticity") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in throat elasticity.", smallChangeMajorBoost, " elasticity") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in throat plasticity.", smallChangeMajorDrain, " plasticity") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in throat plasticity.", smallChangeDrain, " plasticity") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in throat plasticity.", smallChangeMinorDrain, " plasticity") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in throat plasticity.", smallChangeMinorBoost, " plasticity") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in throat plasticity.", smallChangeBoost, " plasticity") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in throat plasticity.", smallChangeMajorBoost, " plasticity") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in saliva production.", smallChangeMajorDrain, " wetness") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in saliva production.", smallChangeDrain, " wetness") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in saliva production.", smallChangeMinorDrain, " wetness") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in saliva production.", smallChangeMinorBoost, " wetness") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in saliva production.", smallChangeBoost, " wetness") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in saliva production.", smallChangeMajorBoost, " wetness") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMajorBoost); } };
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
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(HairType.getHairTypes(race).get(0).getTransformName())+" hair transformation.", 0, "") {
							@Override public String applyEffect() { return target.setHairType(HairType.getHairTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(HairType.getHairTypes(race).get(1).getTransformName())+" hair transformation.", 0, "") {
							@Override public String applyEffect() { return target.setHairType(HairType.getHairTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(HairType.getHairTypes(race).get(2).getTransformName())+" hair transformation.", 0, "") {
							@Override public String applyEffect() { return target.setHairType(HairType.getHairTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(HairType.getHairTypes(race).get(3).getTransformName())+" hair transformation.", 0, "") {
							@Override public String applyEffect() { return target.setHairType(HairType.getHairTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(HairType.getHairTypes(race).get(4).getTransformName())+" hair transformation.", 0, "") {
							@Override public String applyEffect() { return target.setHairType(HairType.getHairTypes(race).get(4)); } };
						
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
						
					case REMOVAL:
						return new RacialEffectUtil("Removes horns.", 0, "") { @Override public String applyEffect() { return target.setHornType(HornType.NONE); } };
						
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
				switch(secondaryModifier) {
					case TF_TYPE_1:
						return new RacialEffectUtil(Util.capitaliseSentence(LegType.getLegTypes(race).get(0).getTransformName())+" legs transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setLegType(LegType.getLegTypes(race).get(0)); } };
	
					case TF_TYPE_2:
						return new RacialEffectUtil(Util.capitaliseSentence(LegType.getLegTypes(race).get(1).getTransformName())+" legs transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setLegType(LegType.getLegTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(Util.capitaliseSentence(LegType.getLegTypes(race).get(2).getTransformName())+" legs transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setLegType(LegType.getLegTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(Util.capitaliseSentence(LegType.getLegTypes(race).get(3).getTransformName())+" legs transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setLegType(LegType.getLegTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(Util.capitaliseSentence(LegType.getLegTypes(race).get(4).getTransformName())+" legs transformation.",
								0, "") {
							@Override public String applyEffect() { return target.setLegType(LegType.getLegTypes(race).get(4)); } };
							
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" legs transformation.", 0, "") { @Override public String applyEffect() { return target.setLegType(RacialBody.valueOfRace(race).getLegType()); } };
				}
				
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
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in penis girth.", smallChangeMajorDrain, " girth") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in penis girth.", smallChangeDrain, " size") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in penis girth.", smallChangeMinorDrain, " girth") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in penis girth.", smallChangeMinorBoost, " girth") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in penis girth.", smallChangeBoost, " girth") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in penis girth.", smallChangeMajorBoost, " girth") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMajorBoost); } };
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

					case TF_MOD_SIZE_TERTIARY:
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
						return new RacialEffectUtil(TailType.getTailTypes(race).get(0)==TailType.NONE?"Removes tail.":Util.capitaliseSentence(TailType.getTailTypes(race).get(0).getTransformName())+" tail transformation.", 0, "") {
							@Override public String applyEffect() { return target.setTailType(TailType.getTailTypes(race).get(0)); } };
						
					case TF_TYPE_2:
						return new RacialEffectUtil(TailType.getTailTypes(race).get(1)==TailType.NONE?"Removes tail.":Util.capitaliseSentence(TailType.getTailTypes(race).get(1).getTransformName())+" tail transformation.", 0, "") {
							@Override public String applyEffect() { return target.setTailType(TailType.getTailTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(TailType.getTailTypes(race).get(2)==TailType.NONE?"Removes tail.":Util.capitaliseSentence(TailType.getTailTypes(race).get(2).getTransformName())+" tail transformation.", 0, "") {
							@Override public String applyEffect() { return target.setTailType(TailType.getTailTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(TailType.getTailTypes(race).get(3)==TailType.NONE?"Removes tail.":Util.capitaliseSentence(TailType.getTailTypes(race).get(3).getTransformName())+" tail transformation.", 0, "") {
							@Override public String applyEffect() { return target.setTailType(TailType.getTailTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(TailType.getTailTypes(race).get(4)==TailType.NONE?"Removes tail.":Util.capitaliseSentence(TailType.getTailTypes(race).get(4).getTransformName())+" tail transformation.", 0, "") {
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
						
					// Urethral stuff:
					case TF_MOD_CAPACITY_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in urethra capacity.", mediumChangeMajorDrain, " capacity") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in urethra capacity.", mediumChangeDrain, " capacity") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in urethra capacity.", mediumChangeMinorDrain, " capacity") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in urethra capacity.", mediumChangeMinorBoost, " capacity") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("Increase in urethra capacity.", mediumChangeBoost, " capacity") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in urethra capacity.", mediumChangeMajorBoost, " capacity") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_ELASTICITY_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in urethra elasticity.", smallChangeMajorDrain, " elasticity") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in urethra elasticity.", smallChangeDrain, " elasticity") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in urethra elasticity.", smallChangeMinorDrain, " elasticity") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in urethra elasticity.", smallChangeMinorBoost, " elasticity") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in urethra elasticity.", smallChangeBoost, " elasticity") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in urethra elasticity.", smallChangeMajorBoost, " elasticity") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Huge decrease in urethra plasticity.", smallChangeMajorDrain, " plasticity") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in urethra plasticity.", smallChangeDrain, " plasticity") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in urethra plasticity.", smallChangeMinorDrain, " plasticity") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Small increase in urethra plasticity.", smallChangeMinorBoost, " plasticity") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in urethra plasticity.", smallChangeBoost, " plasticity") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in urethra plasticity.", smallChangeMajorBoost, " plasticity") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY_2:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes puffiness from urethra.", 0, "") { @Override public String applyEffect() { return target.removeVaginaUrethraOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Makes urethra extra puffy.", 0, "") { @Override public String applyEffect() { return target.addVaginaUrethraOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED_2:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal ribbing from urethra.", 0, "") { @Override public String applyEffect() { return target.removeVaginaUrethraOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal ribbing to urethra.", 0, "") { @Override public String applyEffect() { return target.addVaginaUrethraOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED_2:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal muscles from urethra.", 0, "") { @Override public String applyEffect() { return target.removeVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal muscles to urethra.", 0, "") { @Override public String applyEffect() { return target.addVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED_2:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Removes internal tentacles from urethra.", 0, "") { @Override public String applyEffect() { return target.removeVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Adds internal tentacles to urethra.", 0, "") { @Override public String applyEffect() { return target.addVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName())+" vagina transformation.", 0, "") { @Override public String applyEffect() { return target.setVaginaType(RacialBody.valueOfRace(race).getVaginaType()); } };
				}
				
			case TF_WINGS:
				switch(secondaryModifier) {
					case REMOVAL:
						return new RacialEffectUtil("Removes wings.", 0, "") { @Override public String applyEffect() { return target.setWingType(WingType.NONE); } };
						
					case TF_TYPE_1:
						return new RacialEffectUtil(WingType.getWingTypes(race).get(0)==WingType.NONE?"Removes wings.":Util.capitaliseSentence(WingType.getWingTypes(race).get(0).getTransformName())+" wings transformation.", 0, "") {
							@Override public String applyEffect() { return target.setWingType(WingType.getWingTypes(race).get(0)); } };
						
					case TF_TYPE_2:
						return new RacialEffectUtil(WingType.getWingTypes(race).get(1)==WingType.NONE?"Removes wings.":Util.capitaliseSentence(WingType.getWingTypes(race).get(1).getTransformName())+" wings transformation.", 0, "") {
							@Override public String applyEffect() { return target.setWingType(WingType.getWingTypes(race).get(1)); } };
	
					case TF_TYPE_3:
						return new RacialEffectUtil(WingType.getWingTypes(race).get(2)==WingType.NONE?"Removes wings.":Util.capitaliseSentence(WingType.getWingTypes(race).get(2).getTransformName())+" wings transformation.", 0, "") {
							@Override public String applyEffect() { return target.setWingType(WingType.getWingTypes(race).get(2)); } };
	
					case TF_TYPE_4:
						return new RacialEffectUtil(WingType.getWingTypes(race).get(3)==WingType.NONE?"Removes wings.":Util.capitaliseSentence(WingType.getWingTypes(race).get(3).getTransformName())+" wings transformation.", 0, "") {
							@Override public String applyEffect() { return target.setWingType(WingType.getWingTypes(race).get(3)); } };
	
					case TF_TYPE_5:
						return new RacialEffectUtil(WingType.getWingTypes(race).get(4)==WingType.NONE?"Removes wings.":Util.capitaliseSentence(WingType.getWingTypes(race).get(4).getTransformName())+" wings transformation.", 0, "") {
							@Override public String applyEffect() { return target.setWingType(WingType.getWingTypes(race).get(4)); } };
							
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
								return new RacialEffectUtil("Huge decrease in milk storage.", largeChangeMajorDrain, "ml") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Decrease in milk storage.", largeChangeDrain, "ml") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Small decrease in milk storage.", largeChangeMinorDrain, "ml") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("Small increase in milk storage.", largeChangeMinorBoost, "ml") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Increase in milk storage.", largeChangeBoost, "ml") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Huge increase in milk storage.", largeChangeMajorBoost, "ml") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorBoost); } };
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
