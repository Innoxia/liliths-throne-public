package com.base.game.character.effects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.valueEnums.AssSize;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.CumProduction;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.body.valueEnums.HipSize;
import com.base.game.character.body.valueEnums.Lactation;
import com.base.game.character.body.valueEnums.OrificeElasticity;
import com.base.game.character.body.valueEnums.PenisSize;
import com.base.game.character.body.valueEnums.TesticleSize;
import com.base.game.character.body.valueEnums.Wetness;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.63
 * @author Innoxia
 */
public enum TransformationEffect {

	// Handles every possible transformation in the game.

	// TODO big corruption increases for monster types
	
	

	// HUMAN:
	HUMAN("human transformation", "Transforms the target into a human.") {

		@Override
		public String applyEffect(GameCharacter target) {
			return genericMinorTransformation(target, RacialBody.HUMAN, 0.75f, Transformation.ANGEL_TEARS);
		}
	},
	
	// DEMON: TODO temporary!!!
	DEMON_MAJOR("demon major transformation", "Powerful demonic transformation.") {
		@Override
		public String applyEffect(GameCharacter target) {
			return genericMajorTransformation(target, RacialBody.DEMON, 0.75f, Transformation.STATS_CORRUPTION);
		}
	},
	

	// CANINE:
	DOG_MORPH_MINOR("dog-morph transformation", "Weak dog-morph transformation.") {
		@Override
		public String applyEffect(GameCharacter target) {
			return genericMinorTransformation(target, RacialBody.DOG_MORPH, 0.75f, Transformation.STATS_DOG_MORPH);
		}
	},
	DOG_MORPH_MAJOR("dog-morph major transformation", "Powerful dog-morph transformation.") {
		@Override
		public String applyEffect(GameCharacter target) {
			return genericMajorTransformation(target, RacialBody.DOG_MORPH, 0.75f, Transformation.STATS_DOG_MORPH);
		}
	},

	WOLF_MORPH_MINOR("wolf-morph transformation", "Weak wolf-morph transformation.") {
		@Override
		public String applyEffect(GameCharacter target) {
			return genericMinorTransformation(target, RacialBody.WOLF_MORPH, 0.75f, Transformation.STATS_WOLF_MORPH);
		}
	},
	WOLF_MORPH_MAJOR("wolf-morph major transformation", "Powerful wolf-morph transformation.") {
		@Override
		public String applyEffect(GameCharacter target) {
			return genericMajorTransformation(target, RacialBody.WOLF_MORPH, 0.75f, Transformation.STATS_WOLF_MORPH);
		}
	},

	// FELINE:
	CAT_MORPH_MINOR("cat-morph transformation", "Weak cat-morph transformation.") {
		@Override
		public String applyEffect(GameCharacter target) {
			return genericMinorTransformation(target, RacialBody.CAT_MORPH, 0.75f, Transformation.STATS_CAT_MORPH);
		}
	},
	CAT_MORPH_MAJOR("cat-morph major transformation", "Powerful cat-morph transformation.") {
		@Override
		public String applyEffect(GameCharacter target) {
			return genericMajorTransformation(target, RacialBody.CAT_MORPH, 0.75f, Transformation.STATS_CAT_MORPH);
		}
	},

	// RODENT:
	SQUIRREL_MORPH_MINOR("squirrel-morph transformation", "Weak squirrel-morph transformation.") {
		@Override
		public String applyEffect(GameCharacter target) {
			return genericMinorTransformation(target, RacialBody.SQUIRREL_MORPH, 0.75f, Transformation.STATS_SQUIRREL_MORPH);
		}
	},
	SQUIRREL_MORPH_MAJOR("squirrel-morph major transformation", "Powerful squirrel-morph transformation.") {
		@Override
		public String applyEffect(GameCharacter target) {
			return genericMajorTransformation(target, RacialBody.SQUIRREL_MORPH, 0.75f, Transformation.STATS_SQUIRREL_MORPH);
		}
	},

	// EQUINE:
	HORSE_MORPH_MINOR("horse-morph transformation", "Weak horse-morph transformation.") {
		@Override
		public String applyEffect(GameCharacter target) {
			return genericMinorTransformation(target, RacialBody.HORSE_MORPH, 0.75f, Transformation.STATS_HORSE_MORPH);
		}
	},
	HORSE_MORPH_MAJOR("horse-morph major transformation", "Powerful horse-morph transformation.") {
		@Override
		public String applyEffect(GameCharacter target) {
			return genericMajorTransformation(target, RacialBody.HORSE_MORPH, 0.75f, Transformation.STATS_HORSE_MORPH);
		}
	},

	// AVIAN:
	HARPY("harpy transformation", "Transforms the target into a harpy. Will not transform the target further than a " + RaceStage.LESSER.getName() + " harpy.") {

		@Override
		public String applyEffect(GameCharacter target) {
			return genericMinorTransformation(target, RacialBody.HARPY, 0.75f, Transformation.STATS_HARPY);
		}
	},
	
	// SLIME:
	SLIME("slime transformation", "Fully transforms the target into a " + RaceStage.GREATER.getName() + " slime.") {

		@Override
		public String applyEffect(GameCharacter target) {
			return "";
			// return monsterTransformation(target, RacialBody.SLIME, true);
		}
	},

	LILITHS_GIFT("strong sexual boon", "Transforms the size and potency of the target's sexual organs.") {
		@Override
		public String applyEffect(GameCharacter target) {
			effectStringBuilder = new StringBuilder();

			availableTransformationList.clear();

			// Feminine transformations:
			if (target.isFeminine()) {
				if (target.getAssSize().getValue() < AssSize.SIX_MASSIVE.getValue())
					availableTransformationList.add(Transformation.GROW_ASS);

				if (target.getAssWetness().getValue() < Wetness.FIVE_SLOPPY.getValue())
					availableTransformationList.add(Transformation.ASS_WETNESS_INCREASE);

				if (target.getBreastSize().getMeasurement() < CupSize.N.getMeasurement())
					availableTransformationList.add(Transformation.GROW_BREASTS);

				if (target.getHipSize().getValue() < HipSize.SIX_EXTREMELY_WIDE.getValue())
					availableTransformationList.add(Transformation.GROW_HIPS);
			}

			if (target.getVaginaType() != VaginaType.NONE) {
				if (target.getVaginaWetness().getValue() < Wetness.SIX_SOPPING_WET.getValue())
					availableTransformationList.add(Transformation.VAGINA_WETNESS_INCREASE);

				if (target.getVaginaRawCapacityValue() < Capacity.SIX_STRETCHED_OPEN.getMedianValue())
					availableTransformationList.add(Transformation.VAGINA_CAPACITY_INCREASE);
			}

			if (target.getPenisType() != PenisType.NONE) {
				if (target.getPenisRawSizeValue() < PenisSize.SIX_GIGANTIC.getMedianValue())
					availableTransformationList.add(Transformation.GROW_PENIS);

				if (target.getTesticleSize().getValue() < TesticleSize.SIX_GIGANTIC.getValue())
					availableTransformationList.add(Transformation.GROW_TESTICLES);

				if (target.getPenisRawCumProductionValue() < CumProduction.SIX_EXTREME.getMaximumValue())
					availableTransformationList.add(Transformation.CUM_PRODUCTION_INCREASE_LARGE);
			}
			
			applyTransformations(target, 1, null);
			
			effectStringBuilder.append(Transformation.STATS_CORRUPTION.applyEffect(target, RacialBody.valueOfRace(target.getRace())));
			
			return effectStringBuilder.toString();
		}
	},

	LYXIAS_DREAM("Lyxia's dream", "Increases the target's ability to generate sexual fluids.") {
		@Override
		public String applyEffect(GameCharacter target) {
			effectStringBuilder = new StringBuilder();
			target.incrementMana(-10);

			availableTransformationList.clear();

			// Increases breast size, testicle size, vagina wetness, lactation
			// or cum production

			if (target.getBreastSize().getMeasurement() < CupSize.MAXIMUM.getMeasurement())
				availableTransformationList.add(Transformation.GROW_BREASTS);

			if (target.getBreastRawLactationValue() < Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue())
				availableTransformationList.add(Transformation.LACTATION_INCREASE);

			if (target.getVaginaType() != VaginaType.NONE) {
				if (target.getVaginaWetness().getValue() < Wetness.SEVEN_DROOLING.getValue())
					availableTransformationList.add(Transformation.VAGINA_WETNESS_INCREASE);
			}
			if (target.getPenisType() != PenisType.NONE) {
				if (target.getTesticleSize().getValue() < TesticleSize.SEVEN_ABSURD.getValue())
					availableTransformationList.add(Transformation.GROW_TESTICLES);

				if (target.getPenisRawCumProductionValue() < CumProduction.SEVEN_MONSTROUS.getMaximumValue())
					availableTransformationList.add(Transformation.CUM_PRODUCTION_INCREASE_LARGE);
			}

			applyTransformations(target, 2, null);

			return effectStringBuilder.toString();
		}
	},
	LUNETTES_NEED("Lunette's need", "Increases the target's orifice capacity and wetness.") {
		@Override
		public String applyEffect(GameCharacter target) {
			effectStringBuilder = new StringBuilder();
			target.incrementMana(-10);

			availableTransformationList.clear();

			// Increases capacity & wetness of everything, making breasts & cock
			// fuckable

			if (target.getBreastSize().getMeasurement() < CupSize.DD.getMeasurement())
				availableTransformationList.add(Transformation.GROW_BREASTS);
			else if (target.getBreastRawCapacityValue() < Capacity.SEVEN_GAPING.getMaximumValue())
				availableTransformationList.add(Transformation.BREAST_CAPACITY_INCREASE);
			if (target.getBreastRawLactationValue() < Lactation.THREE_DECENT_AMOUNT.getMedianValue())
				availableTransformationList.add(Transformation.LACTATION_INCREASE);

			if (target.getAssRawCapacityValue() < Capacity.SEVEN_GAPING.getMaximumValue())
				availableTransformationList.add(Transformation.ASS_CAPACITY_INCREASE);
			if (target.getAssWetness().getValue() < Wetness.SEVEN_DROOLING.getValue())
				availableTransformationList.add(Transformation.ASS_WETNESS_INCREASE);

			if (target.getVaginaType() != VaginaType.NONE) {
				if (target.getVaginaWetness().getValue() < Wetness.SEVEN_DROOLING.getValue())
					availableTransformationList.add(Transformation.VAGINA_WETNESS_INCREASE);
				if (target.getVaginaRawCapacityValue() < Capacity.SEVEN_GAPING.getMedianValue())
					availableTransformationList.add(Transformation.VAGINA_CAPACITY_INCREASE);
			} else
				availableTransformationList.add(Transformation.VAGINA);

			if (target.getPenisType() != PenisType.NONE) {
				if (target.getPenisRawCapacityValue() < Capacity.SEVEN_GAPING.getMaximumValue())
					availableTransformationList.add(Transformation.PENIS_CAPACITY_INCREASE);
				if (target.getPenisRawCumProductionValue() < CumProduction.THREE_AVERAGE.getMedianValue())
					availableTransformationList.add(Transformation.CUM_PRODUCTION_INCREASE_LARGE);
			}

			applyTransformations(target, 2, null);

			return effectStringBuilder.toString();
		}
	},
	LISOPHIAS_DESIRE("Lisophia's desire", "Increases the target's masculinity in more ways than one.") {
		@Override
		public String applyEffect(GameCharacter target) {
			effectStringBuilder = new StringBuilder();
			target.incrementMana(-10);

			availableTransformationList.clear();

			// Increases cock & ball size. Grows second cock. Increases cum
			// production.
			if (target.getPenisType() != PenisType.NONE) {
				if (target.getPenisRawSizeValue() < PenisSize.SEVEN_STALLION.getMaximumValue())
					availableTransformationList.add(Transformation.GROW_PENIS);

				if (target.getTesticleSize().getValue() < TesticleSize.SEVEN_ABSURD.getValue())
					availableTransformationList.add(Transformation.GROW_TESTICLES);

				if (target.getPenisRawCumProductionValue() < CumProduction.SEVEN_MONSTROUS.getMaximumValue())
					availableTransformationList.add(Transformation.CUM_PRODUCTION_INCREASE_LARGE);

				// if(target.getSecondPenisType()==PenisType.NONE)
				// availableTransformationList.add(Transformation.PENIS_SECOND);
			} else
				availableTransformationList.add(Transformation.PENIS);

			applyTransformations(target, 2, null);

			return effectStringBuilder.toString();
		}
	},
	LIRECEAS_DEMAND("Lisophia's desire", "Increases the target's femininity.") {
		@Override
		public String applyEffect(GameCharacter target) {
			effectStringBuilder = new StringBuilder();
			target.incrementMana(-10);

			availableTransformationList.clear();

			// Hyper-feminine, growing hips, breasts, tightens pussy, makes wet,
			// shrinks cocks.

			if (target.getFemininity() < Femininity.FEMININE_STRONG.getMaximumFemininity())
				availableTransformationList.add(Transformation.FEMININITY_INCREASE_LARGE);

			if (target.getBreastSize().getMeasurement() < CupSize.DD.getMeasurement())
				availableTransformationList.add(Transformation.GROW_BREASTS);

			if (target.getAssSize().getValue() < AssSize.FOUR_LARGE.getValue())
				availableTransformationList.add(Transformation.GROW_ASS);

			if (target.getHipSize().getValue() < HipSize.FOUR_WOMANLY.getValue())
				availableTransformationList.add(Transformation.GROW_HIPS);

			if (target.getVaginaType() != VaginaType.NONE) {
				if (target.getVaginaWetness().getValue() < Wetness.THREE_WET.getValue())
					availableTransformationList.add(Transformation.VAGINA_WETNESS_INCREASE);
				if (target.getVaginaRawCapacityValue() > Capacity.TWO_TIGHT.getMedianValue())
					availableTransformationList.add(Transformation.VAGINA_CAPACITY_DECREASE);
			} else
				availableTransformationList.add(Transformation.VAGINA);

			if (target.getPenisType() != PenisType.NONE) {
				if (target.getPenisRawSizeValue() != 0)
					availableTransformationList.add(Transformation.SHRINK_PENIS);
				else {
					// if(target.getSecondPenisType()!=PenisType.NONE)
					// availableTransformationList.add(Transformation.REMOVE_SECOND_PENIS);
					// else
					availableTransformationList.add(Transformation.REMOVE_PENIS);
				}
			}

			applyTransformations(target, 2, null);

			return effectStringBuilder.toString();
		}
	},

	// Normal non-racial transformations:

	BUBBLE_MILK("bubble-milk", "Increases the target's milking capabilities.") {
		@Override
		public String applyEffect(GameCharacter target) {
			effectStringBuilder = new StringBuilder();

			availableTransformationList.clear();
			
			if (target.getBreastRawLactationValue() < Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue())
				availableTransformationList.add(Transformation.LACTATION_INCREASE);

			if (target.getBreastSize().getMeasurement() < CupSize.JJ.getMeasurement())
				availableTransformationList.add(Transformation.GROW_BREASTS);

			applyTransformations(target, 1, null);

			return effectStringBuilder.toString();
		}
	},
	BUBBLE_CREAM("bubble-cream", "Greatly increases the target's milking capabilities.") {
		@Override
		public String applyEffect(GameCharacter target) {
			effectStringBuilder = new StringBuilder();

			availableTransformationList.clear();

			if (target.getBreastRawLactationValue() < Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue())
				availableTransformationList.add(Transformation.LACTATION_INCREASE);

			if (target.getBreastSize().getMeasurement() < CupSize.MAXIMUM.getMeasurement())
				availableTransformationList.add(Transformation.GROW_BREASTS);

			if (target.getBreastRows() < 3)
				availableTransformationList.add(Transformation.BREAST_ROW_ADD);

			applyTransformations(target, 2, null);

			return effectStringBuilder.toString();
		}
	},

	THROBBING_GLOW("Throbbing Glow", "Makes a man grow where it counts.") {
		@Override
		public String applyEffect(GameCharacter target) {
			effectStringBuilder = new StringBuilder();

			availableTransformationList.clear();

			if (target.getVaginaType() != VaginaType.NONE) {
				if (target.getVaginaRawCapacityValue() != 0)
					availableTransformationList.add(Transformation.VAGINA_CAPACITY_DECREASE);
				else
					availableTransformationList.add(Transformation.REMOVE_VAGINA);
			}

			if (target.getPenisType() != PenisType.NONE) {
				if (target.getPenisRawSizeValue() < PenisSize.FIVE_ENORMOUS.getMedianValue())
					availableTransformationList.add(Transformation.GROW_PENIS);

				if (target.getTesticleSize().getValue() < TesticleSize.FOUR_HUGE.getValue())
					availableTransformationList.add(Transformation.GROW_TESTICLES);

				if (target.getPenisRawCumProductionValue() < CumProduction.FIVE_HUGE.getMedianValue())
					availableTransformationList.add(Transformation.CUM_PRODUCTION_INCREASE);
			} else
				availableTransformationList.add(Transformation.PENIS);

			applyTransformations(target, 1, null);

			return effectStringBuilder.toString();
		}
	},
	THROBBING_FIRE("Throbbing Fire", "Grows your manhood in more ways than one.") {
		@Override
		public String applyEffect(GameCharacter target) {
			effectStringBuilder = new StringBuilder();

			availableTransformationList.clear();

			if (target.getVaginaType() != VaginaType.NONE) {
				if (target.getVaginaRawCapacityValue() != 0)
					availableTransformationList.add(Transformation.VAGINA_CAPACITY_DECREASE);
				else
					availableTransformationList.add(Transformation.REMOVE_VAGINA);
			}

			if (target.getPenisType() != PenisType.NONE) {
				if (target.getPenisRawSizeValue() < PenisSize.SEVEN_STALLION.getMaximumValue())
					availableTransformationList.add(Transformation.GROW_PENIS);

				if (target.getTesticleSize().getValue() < TesticleSize.SIX_GIGANTIC.getValue())
					availableTransformationList.add(Transformation.GROW_TESTICLES);

				if (target.getPenisRawCumProductionValue() < CumProduction.SIX_EXTREME.getMaximumValue())
					availableTransformationList.add(Transformation.CUM_PRODUCTION_INCREASE_LARGE);
				// if(target.getSecondPenisType()==PenisType.NONE)
				// availableTransformationList.add(Transformation.PENIS_SECOND);
			} else
				availableTransformationList.add(Transformation.PENIS);

			applyTransformations(target, 2, null);

			return effectStringBuilder.toString();
		}
	},

	FLOWERS_WARMTH("Flower's Warmth", "Just what every girl needs.") {
		@Override
		public String applyEffect(GameCharacter target) {
			effectStringBuilder = new StringBuilder();

			availableTransformationList.clear();

			if (target.getBreastSize().getMeasurement() < CupSize.JJ.getMeasurement())
				availableTransformationList.add(Transformation.GROW_BREASTS);

			if (target.getAssSize().getValue() < AssSize.FIVE_HUGE.getValue())
				availableTransformationList.add(Transformation.GROW_ASS);

			if (target.getHipSize().getValue() < HipSize.FIVE_VERY_WIDE.getValue())
				availableTransformationList.add(Transformation.GROW_HIPS);

			if (target.getVaginaType() != VaginaType.NONE) {
				if (target.getVaginaWetness().getValue() < Wetness.FOUR_SLIMY.getValue())
					availableTransformationList.add(Transformation.VAGINA_WETNESS_INCREASE);
			} else
				availableTransformationList.add(Transformation.VAGINA);

			if (target.getPenisType() != PenisType.NONE) {
				if (target.getPenisRawSizeValue() != 0)
					availableTransformationList.add(Transformation.SHRINK_PENIS);
				else {
					// if(target.getSecondPenisType()!=PenisType.NONE)
					// availableTransformationList.add(Transformation.REMOVE_SECOND_PENIS);
					// else
					availableTransformationList.add(Transformation.REMOVE_PENIS);
				}
			}

			applyTransformations(target, 1, null);

			return effectStringBuilder.toString();
		}
	},
	FLOWERS_HEAT("Flower's Heat", "Just what every woman needs.") {
		@Override
		public String applyEffect(GameCharacter target) {
			effectStringBuilder = new StringBuilder();

			availableTransformationList.clear();

			if (target.getBreastSize().getMeasurement() < CupSize.N.getMeasurement())
				availableTransformationList.add(Transformation.GROW_BREASTS);

			if (target.getAssSize().getValue() < AssSize.SIX_MASSIVE.getValue())
				availableTransformationList.add(Transformation.GROW_ASS);

			if (target.getHipSize().getValue() < HipSize.SIX_EXTREMELY_WIDE.getValue())
				availableTransformationList.add(Transformation.GROW_HIPS);

			if (target.getVaginaType() != VaginaType.NONE) {
				if (target.getVaginaWetness().getValue() < Wetness.SIX_SOPPING_WET.getValue())
					availableTransformationList.add(Transformation.VAGINA_WETNESS_INCREASE);
			} else
				availableTransformationList.add(Transformation.VAGINA);

			if (target.getPenisType() != PenisType.NONE) {
				if (target.getPenisRawSizeValue() != 0)
					availableTransformationList.add(Transformation.SHRINK_PENIS);
				else {
					// if(target.getSecondPenisType()!=PenisType.NONE)
					// availableTransformationList.add(Transformation.REMOVE_SECOND_PENIS);
					// else
					availableTransformationList.add(Transformation.REMOVE_PENIS);
				}
			}

			applyTransformations(target, 2, null);

			return effectStringBuilder.toString();
		}
	},

	WET_KISS("Wet Kiss", "Open wide...") {
		@Override
		public String applyEffect(GameCharacter target) {
			effectStringBuilder = new StringBuilder();

			availableTransformationList.clear();

			if (target.getBreastRawCapacityValue() > 0)
				availableTransformationList.add(Transformation.BREAST_CAPACITY_INCREASE);
			
			if (target.getAssRawCapacityValue() < Capacity.SEVEN_GAPING.getMaximumValue())
				availableTransformationList.add(Transformation.ASS_CAPACITY_INCREASE);
			if (target.getAssWetness().getValue() < Wetness.SEVEN_DROOLING.getValue())
				availableTransformationList.add(Transformation.ASS_WETNESS_INCREASE);

			if (target.getVaginaType() != VaginaType.NONE) {
				if (target.getVaginaWetness().getValue() < Wetness.SEVEN_DROOLING.getValue())
					availableTransformationList.add(Transformation.VAGINA_WETNESS_INCREASE);
				if (target.getVaginaRawCapacityValue() < Capacity.SEVEN_GAPING.getMedianValue())
					availableTransformationList.add(Transformation.VAGINA_CAPACITY_INCREASE);
			}

			if (target.getPenisType() != PenisType.NONE)
				if (target.getPenisRawCapacityValue() > 0)
					availableTransformationList.add(Transformation.PENIS_CAPACITY_INCREASE);

			applyTransformations(target, 1, null);

			return effectStringBuilder.toString();
		}
	},
	MASOCHISTS_HEAVEN("Masochist's Heaven", "For those that love pain") {
		@Override
		public String applyEffect(GameCharacter target) {
			effectStringBuilder = new StringBuilder();

			availableTransformationList.clear();

			if (target.getBreastRawCapacityValue() > 0)
				availableTransformationList.add(Transformation.BREAST_CAPACITY_DECREASE);

			if (target.getAssRawCapacityValue() > Capacity.ONE_EXTREMELY_TIGHT.getMedianValue())
				availableTransformationList.add(Transformation.ASS_CAPACITY_DECREASE);
			if (target.getAssWetness().getValue() > Wetness.ZERO_DRY.getValue())
				availableTransformationList.add(Transformation.ASS_WETNESS_DECREASE);
			if (target.getAssElasticity().getValue() > OrificeElasticity.ZERO_UNYIELDING.getValue())
				availableTransformationList.add(Transformation.ASS_ELASTICITY_DECREASE);

			if (target.getVaginaType() != VaginaType.NONE) {
				if (target.getVaginaWetness().getValue() > Wetness.ZERO_DRY.getValue())
					availableTransformationList.add(Transformation.VAGINA_WETNESS_DECREASE);
				if (target.getVaginaRawCapacityValue() > Capacity.ONE_EXTREMELY_TIGHT.getMedianValue())
					availableTransformationList.add(Transformation.VAGINA_CAPACITY_DECREASE);
				if (target.getVaginaElasticity().getValue() > OrificeElasticity.ZERO_UNYIELDING.getValue())
					availableTransformationList.add(Transformation.VAGINA_ELASTICITY_DECREASE);
			}

			if (target.getPenisType() != PenisType.NONE)
				if (target.getPenisRawCapacityValue() > 0)
					availableTransformationList.add(Transformation.PENIS_CAPACITY_DECREASE);

			applyTransformations(target, 1, null);

			return effectStringBuilder.toString();
		}
	},
	GOING_BIG("Going Big", "For those that love size") {
		@Override
		public String applyEffect(GameCharacter target) {
			effectStringBuilder = new StringBuilder();

			availableTransformationList.clear();

			if (target.getBreastRawCapacityValue() > 0)
				availableTransformationList.add(Transformation.BREAST_CAPACITY_DECREASE);

			if (target.getAssRawCapacityValue() > Capacity.ONE_EXTREMELY_TIGHT.getMedianValue())
				availableTransformationList.add(Transformation.ASS_CAPACITY_DECREASE);
			if (target.getAssWetness().getValue() < Wetness.FIVE_SLOPPY.getValue())
				availableTransformationList.add(Transformation.ASS_WETNESS_INCREASE);
			if (target.getAssElasticity().getValue() < OrificeElasticity.SEVEN_ELASTIC.getValue())
				availableTransformationList.add(Transformation.ASS_ELASTICITY_INCREASE);

			if (target.getVaginaType() != VaginaType.NONE) {
				if (target.getVaginaWetness().getValue() < Wetness.FIVE_SLOPPY.getValue())
					availableTransformationList.add(Transformation.VAGINA_WETNESS_INCREASE);
				if (target.getVaginaRawCapacityValue() > Capacity.ONE_EXTREMELY_TIGHT.getMedianValue())
					availableTransformationList.add(Transformation.VAGINA_CAPACITY_DECREASE);
				if (target.getVaginaElasticity().getValue() < OrificeElasticity.SEVEN_ELASTIC.getValue())
					availableTransformationList.add(Transformation.VAGINA_ELASTICITY_INCREASE);
			}

			if (target.getPenisType() != PenisType.NONE)
				if (target.getPenisRawCapacityValue() > 0)
					availableTransformationList.add(Transformation.PENIS_CAPACITY_DECREASE);

			applyTransformations(target, 1, null);

			return effectStringBuilder.toString();
		}
	},

	SCARLET_WHISPER("Scarlet Whisper", "Secrets of the fairer sex.") {
		@Override
		public String applyEffect(GameCharacter target) {
			effectStringBuilder = new StringBuilder();

			availableTransformationList.clear();

			if (target.getFemininity() < Femininity.FEMININE.getMinimumFemininity())
				availableTransformationList.add(Transformation.FEMININITY_INCREASE_LARGE);
			else if (target.getFemininity() < Femininity.FEMININE_STRONG.getMaximumFemininity())
				availableTransformationList.add(Transformation.FEMININITY_INCREASE);

			if (target.getBreastSize().getMeasurement() < CupSize.DD.getMeasurement())
				availableTransformationList.add(Transformation.GROW_BREASTS);

			if (target.getAssSize().getValue() < AssSize.FOUR_LARGE.getValue())
				availableTransformationList.add(Transformation.GROW_ASS);

			if (target.getHipSize().getValue() < HipSize.FOUR_WOMANLY.getValue())
				availableTransformationList.add(Transformation.GROW_HIPS);

			if (target.getVaginaType() == VaginaType.NONE)
				availableTransformationList.add(Transformation.VAGINA);

			if (target.getPenisType() != PenisType.NONE) {
				if (target.getPenisRawSizeValue() != 0)
					availableTransformationList.add(Transformation.SHRINK_PENIS);
				else {
					// if(target.getSecondPenisType()!=PenisType.NONE)
					// availableTransformationList.add(Transformation.REMOVE_SECOND_PENIS);
					// else
					availableTransformationList.add(Transformation.REMOVE_PENIS);
				}
			}

			applyTransformations(target, 1, null);

			return effectStringBuilder.toString();
		}
	},
	FLAMING_THUNDER("Flaming Thunder", "Unleash your masculinity.") {
		@Override
		public String applyEffect(GameCharacter target) {
			effectStringBuilder = new StringBuilder();

			availableTransformationList.clear();

			if (target.getFemininity() > Femininity.MASCULINE.getMaximumFemininity())
				availableTransformationList.add(Transformation.FEMININITY_DECREASE_LARGE);
			else if (target.getFemininity() > 0)
				availableTransformationList.add(Transformation.FEMININITY_DECREASE);

			if (target.getBreastSize().getMeasurement() > 0)
				availableTransformationList.add(Transformation.SHRINK_BREASTS);

			if (target.getAssSize().getValue() > AssSize.TWO_SMALL.getValue())
				availableTransformationList.add(Transformation.SHRINK_ASS);

			if (target.getHipSize().getValue() > HipSize.TWO_NARROW.getValue())
				availableTransformationList.add(Transformation.SHRINK_HIPS);

			if (target.getVaginaType() != VaginaType.NONE) {
				if (target.getVaginaRawCapacityValue() > 0)
					availableTransformationList.add(Transformation.VAGINA_CAPACITY_DECREASE);
				else
					availableTransformationList.add(Transformation.REMOVE_VAGINA);
			}

			if (target.getPenisType() != PenisType.NONE) {
				if (target.getPenisRawSizeValue() < PenisSize.THREE_LARGE.getMedianValue())
					availableTransformationList.add(Transformation.GROW_PENIS);
			} else
				availableTransformationList.add(Transformation.PENIS);

			applyTransformations(target, 1, null);

			return effectStringBuilder.toString();
		}
	};

	private static StringBuilder effectStringBuilder;
	private static List<Transformation> availableTransformationList;
	static {
		effectStringBuilder = new StringBuilder("");
		availableTransformationList = new ArrayList<>();
	}

	private static void applyTransformations(GameCharacter target, int numberOfTransformations, RacialBody targetBody) {
		if (!availableTransformationList.isEmpty()) {
			Collections.shuffle(availableTransformationList);

			for (int i = 0; i < numberOfTransformations; i++)
				if (availableTransformationList.size() > i)
					effectStringBuilder.append(availableTransformationList.get(i).applyEffect(target, (targetBody == null ? RacialBody.valueOfRace(target.getRace()) : targetBody)));
				else
					return;

		} else
			effectStringBuilder.append("<p>Nothing seems to happen...</p>");
	}

	/**
	 * Transforms using the Transformation 'statsTF' if cannot transform or transformation chance fails.</br>
	 * </br>
	 * 
	 * <b>Partial transformation:</b></br>
	 * Transforms ass</br>
	 * Transforms vagina</br>
	 * Transforms penis</br>
	 * Transforms ears</br>
	 * Transforms eyes</br>
	 * Transforms hair</br>
	 * Transforms tail</br>
	 * Transforms wings</br>
	 * Transforms horns</br>
	 * </br>
	 * 
	 * <b>Lesser transformation:</b></br>
	 * Transform arms & legs if full partial morph</br>
	 * Reverts lesser transformation if of a different race.</br>
	 * </br>
	 * 
	 * <b>Greater transformation:</b></br>
	 * Reverts greater transformation if of a different race.</br>
	 * 
	 * @param target
	 * @param targetTransformationRacialBody
	 * @param chanceToTransform
	 * @param statsTF
	 * @return Description of the transformation.
	 */
	private static String genericMinorTransformation(GameCharacter target, RacialBody targetTransformationRacialBody, float chanceToTransform, Transformation statsTF) {
		effectStringBuilder = new StringBuilder("");
		availableTransformationList.clear();

		// If target is not this race and is a greater morph, revert greater
		// morph status:
		if (RacialBody.valueOfRace(target.getRace()) != targetTransformationRacialBody && target.getRaceStage() == RaceStage.GREATER) {
			return Transformation.GREATER.applyEffect(target, RacialBody.HUMAN);

			// If target is not this race and is a lesser morph, revert lesser
			// morph status:
		} else if (RacialBody.valueOfRace(target.getRace()) != targetTransformationRacialBody && target.getRaceStage() == RaceStage.LESSER) {
			return Transformation.LESSER.applyEffect(target, RacialBody.HUMAN);

			// Otherwise, target is either a partial or full partial, so just
			// add all possible partial transformations:
		} else {
			// If target is this race and is a full partial morph, transform
			// into lesser morph:
			if (RacialBody.valueOfRace(target.getRace()) == targetTransformationRacialBody && target.getRaceStage() == RaceStage.PARTIAL_FULL)
				return Transformation.LESSER.applyEffect(target, targetTransformationRacialBody);

			// Standard transformations:

			if (target.getEyeType() != targetTransformationRacialBody.getEyeType())
				availableTransformationList.add(Transformation.EYE);

			if (target.getEarType() != targetTransformationRacialBody.getEarType())
				availableTransformationList.add(Transformation.EAR);

			if (target.getHairType() != targetTransformationRacialBody.getHairType())
				availableTransformationList.add(Transformation.HAIR);

			if (target.getTailType() != targetTransformationRacialBody.getTailType())
				availableTransformationList.add(Transformation.TAIL);

			if (target.getWingType() != targetTransformationRacialBody.getWingType())
				availableTransformationList.add(Transformation.WINGS);

			// Sexual (won't grow new vagina or penis, but will transform them):

			if (target.getAssType() != targetTransformationRacialBody.getAssType())
				availableTransformationList.add(Transformation.ASS);

			if (target.getBreastType() != targetTransformationRacialBody.getBreastType())
				availableTransformationList.add(Transformation.BREASTS);

			if (target.getVaginaType() != VaginaType.NONE && target.getVaginaType() != targetTransformationRacialBody.getVaginaType())
				availableTransformationList.add(Transformation.VAGINA);

			if (target.getPenisType() != PenisType.NONE && target.getPenisType() != targetTransformationRacialBody.getPenisType())
				availableTransformationList.add(Transformation.PENIS);

			// Gender-specific:
			if (target.isFeminine()) {
				if (target.getHornType() != targetTransformationRacialBody.getHornTypeFemale())
					availableTransformationList.add(Transformation.HORNS_FEMALE);
				if(target.getBreastRows()<targetTransformationRacialBody.getBreastCountFemale())
					availableTransformationList.add(Transformation.BREAST_ROW_ADD);
			} else {
				if (target.getHornType() != targetTransformationRacialBody.getHornTypeMale())
					availableTransformationList.add(Transformation.HORNS_MALE);
				if(target.getBreastRows()>targetTransformationRacialBody.getBreastCountMale())
					availableTransformationList.add(Transformation.BREAST_ROW_REMOVE);
			}

			if (!availableTransformationList.isEmpty() && Math.random() <= chanceToTransform)
				return availableTransformationList.get(Util.random.nextInt(availableTransformationList.size())).applyEffect(target, targetTransformationRacialBody);
			else
				return statsTF.applyEffect(target, targetTransformationRacialBody);
		}
	}

	/**
	 * Transforms using the Transformation 'statsTF' if cannot transform or transformation chance fails.</br>
	 * </br>
	 * 
	 * <b>Partial transformation (transforms two at a time):</b></br>
	 * Transforms ass</br>
	 * Transforms vagina</br>
	 * Transforms penis</br>
	 * Transforms ears</br>
	 * Transforms eyes</br>
	 * Transforms hair</br>
	 * Transforms tail</br>
	 * Transforms wings</br>
	 * Transforms horns</br>
	 * </br>
	 * 
	 * <b>Lesser transformation:</b></br>
	 * Transform arms & legs if full partial morph</br>
	 * Reverts lesser transformation if of a different race.</br>
	 * </br>
	 * 
	 * <b>Greater transformation:</b></br>
	 * Transform face & skin if lesser morph</br>
	 * Reverts greater transformation if of a different race.</br>
	 * 
	 * @param target
	 * @param targetTransformationRacialBody
	 * @param chanceToTransform
	 * @param statsTF
	 * @return Description of the transformation.
	 */
	private static String genericMajorTransformation(GameCharacter target, RacialBody targetTransformationRacialBody, float chanceToTransform, Transformation statsTF) {
		effectStringBuilder = new StringBuilder("");
		availableTransformationList.clear();

		// If target is not this race and is a greater morph, revert greater
		// morph status:
		if (RacialBody.valueOfRace(target.getRace()) != targetTransformationRacialBody && target.getRaceStage() == RaceStage.GREATER) {
			return Transformation.GREATER.applyEffect(target, RacialBody.HUMAN);

			// If target is not this race and is a lesser morph, revert lesser
			// morph status:
		} else if (RacialBody.valueOfRace(target.getRace()) != targetTransformationRacialBody && target.getRaceStage() == RaceStage.LESSER) {
			return Transformation.LESSER.applyEffect(target, RacialBody.HUMAN);

			// Otherwise, target is either a partial or full partial, so just
			// add all possible partial transformations:
		} else {
			// If target is this race and is a full partial morph, transform
			// into lesser morph:
			if (RacialBody.valueOfRace(target.getRace()) == targetTransformationRacialBody && target.getRaceStage() == RaceStage.PARTIAL_FULL)
				return Transformation.LESSER.applyEffect(target, targetTransformationRacialBody);

			// If target is this race and is a lesser morph, transform into
			// greater morph:
			if (RacialBody.valueOfRace(target.getRace()) == targetTransformationRacialBody && target.getRaceStage() == RaceStage.LESSER)
				return Transformation.GREATER.applyEffect(target, targetTransformationRacialBody);

			// Standard transformations:

			if (target.getEyeType() != targetTransformationRacialBody.getEyeType())
				availableTransformationList.add(Transformation.EYE);

			if (target.getEarType() != targetTransformationRacialBody.getEarType())
				availableTransformationList.add(Transformation.EAR);

			if (target.getHairType() != targetTransformationRacialBody.getHairType())
				availableTransformationList.add(Transformation.HAIR);

			if (target.getTailType() != targetTransformationRacialBody.getTailType())
				availableTransformationList.add(Transformation.TAIL);

			if (target.getWingType() != targetTransformationRacialBody.getWingType())
				availableTransformationList.add(Transformation.WINGS);

			// Sexual (won't grow new vagina or penis, but will transform them):

			if (target.getAssType() != targetTransformationRacialBody.getAssType())
				availableTransformationList.add(Transformation.ASS);

			if (target.getBreastType() != targetTransformationRacialBody.getBreastType())
				availableTransformationList.add(Transformation.BREASTS);

			if (target.getVaginaType() != VaginaType.NONE && target.getVaginaType() != targetTransformationRacialBody.getVaginaType())
				availableTransformationList.add(Transformation.VAGINA);

			if (target.getPenisType() != PenisType.NONE && target.getPenisType() != targetTransformationRacialBody.getPenisType())
				availableTransformationList.add(Transformation.PENIS);

			// Gender-specific:
			if (target.isFeminine()) {
				if (target.getHornType() != targetTransformationRacialBody.getHornTypeFemale())
					availableTransformationList.add(Transformation.HORNS_FEMALE);
				if(target.getBreastRows()<targetTransformationRacialBody.getBreastCountFemale())
					availableTransformationList.add(Transformation.BREAST_ROW_ADD);
			} else {
				if (target.getHornType() != targetTransformationRacialBody.getHornTypeMale())
					availableTransformationList.add(Transformation.HORNS_MALE);
				if(target.getBreastRows()>targetTransformationRacialBody.getBreastCountMale())
					availableTransformationList.add(Transformation.BREAST_ROW_REMOVE);
			}

			if (!availableTransformationList.isEmpty() && Math.random() <= chanceToTransform) {
				int index = Util.random.nextInt(availableTransformationList.size());

				// Two transformations:
				effectStringBuilder.append(availableTransformationList.get(index).applyEffect(target, targetTransformationRacialBody));
				availableTransformationList.remove(index);

				if (!availableTransformationList.isEmpty()) {
					index = Util.random.nextInt(availableTransformationList.size());
					effectStringBuilder.append(availableTransformationList.get(index).applyEffect(target, targetTransformationRacialBody));
				}

				return effectStringBuilder.toString();
			} else
				return statsTF.applyEffect(target, targetTransformationRacialBody);
		}
	}
	private String name, description;

	private TransformationEffect(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public abstract String applyEffect(GameCharacter target);

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
