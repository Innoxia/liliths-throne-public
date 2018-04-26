package com.lilithsthrone.game.character.race;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.AttributeRange;
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
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.2.2
 * @author Innoxia
 */
public enum RacialBody {

	// HUMAN:
	HUMAN(Util.newHashMapOfValues(
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(5f, 15f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(0f, 0f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(5f, 25f))),
			AntennaType.NONE,
			ArmType.HUMAN, 1,
			AssType.HUMAN, AssSize.TWO_SMALL, AssSize.FOUR_LARGE, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.HUMAN,
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.C, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			180, 25, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue(),
			170, 75, BodySize.TWO_AVERAGE.getMinimumValue(), Muscle.ONE_LIGHTLY_MUSCLED.getMinimumValue(),
			EarType.HUMAN,
			EyeType.HUMAN,
			FaceType.HUMAN, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.HUMAN, HairLength.TWO_SHORT, HairLength.FOUR_MID_BACK,
			LegType.HUMAN,
			SkinType.HUMAN, BodyMaterial.FLESH,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(new ListValue<>(HornType.NONE)),
			PenisType.HUMAN, PenisSize.TWO_AVERAGE, PenisGirth.TWO_AVERAGE,
			PenisType.NONE, PenisSize.TWO_AVERAGE, PenisGirth.TWO_AVERAGE,
			TesticleSize.TWO_AVERAGE, 2, CumProduction.THREE_AVERAGE,
			TailType.NONE,
			VaginaType.HUMAN, Wetness.TWO_MOIST, Capacity.TWO_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			WingType.NONE, WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL),

	// ANGEL:
	ANGEL(Util.newHashMapOfValues(
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(20f, 40f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(20f, 30f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(0f, 0f))),
			AntennaType.NONE,
			ArmType.ANGEL, 1,
			AssType.ANGEL, AssSize.TWO_SMALL, AssSize.TWO_SMALL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.ANGEL,
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.A, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			170, 25, BodySize.THREE_LARGE.getMedianValue(), Muscle.FOUR_RIPPED.getMedianValue(),
			170, 95, BodySize.ONE_SLENDER.getMedianValue(), Muscle.FOUR_RIPPED.getMedianValue(),
			EarType.ANGEL,
			EyeType.ANGEL,
			FaceType.ANGEL, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.ANGEL, HairLength.TWO_SHORT, HairLength.FOUR_MID_BACK,
			LegType.ANGEL,
			SkinType.ANGEL, BodyMaterial.FLESH,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(new ListValue<>(HornType.NONE)),
			PenisType.ANGEL, PenisSize.FOUR_HUGE, PenisGirth.TWO_AVERAGE,
			PenisType.NONE, PenisSize.TWO_AVERAGE, PenisGirth.TWO_AVERAGE,
			TesticleSize.FOUR_HUGE, 2, CumProduction.SEVEN_MONSTROUS,
			TailType.NONE,
			VaginaType.ANGEL, Wetness.SEVEN_DROOLING, Capacity.ONE_EXTREMELY_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.SEVEN_ELASTIC, OrificePlasticity.ZERO_RUBBERY,
			WingType.ANGEL, WingSize.THREE_LARGE, WingSize.THREE_LARGE,
			GenitalArrangement.NORMAL) {

		@Override
		public Map<PersonalityTrait, PersonalityWeight> getPersonality() {
			return Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE));
		}
		
		@Override
		public SexualOrientation getSexualOrientation(Gender gender) {
			return SexualOrientation.AMBIPHILIC;
		}
	},

	// DEMON:
	DEMON(Util.newHashMapOfValues(
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(20f, 40f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(20f, 30f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(80f, 100f))),
			AntennaType.NONE,
			ArmType.DEMON_COMMON, 1,
			AssType.DEMON_COMMON, AssSize.TWO_SMALL, AssSize.FOUR_LARGE, Wetness.FOUR_SLIMY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.SEVEN_ELASTIC, OrificePlasticity.ZERO_RUBBERY,
			BreastType.DEMON_COMMON,
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.SEVEN_ELASTIC, OrificePlasticity.ZERO_RUBBERY, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.F, 1, Lactation.ZERO_NONE, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.SEVEN_ELASTIC, OrificePlasticity.ZERO_RUBBERY, NippleSize.ZERO_TINY, NippleShape.VAGINA, AreolaeSize.TWO_BIG, 1,
			190, 10, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			180, 90, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			EarType.DEMON_COMMON,
			EyeType.DEMON_COMMON,
			FaceType.DEMON_COMMON, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.DEMON_COMMON, HairLength.TWO_SHORT, HairLength.FIVE_ABOVE_ASS,
			LegType.DEMON_COMMON,
			SkinType.DEMON_COMMON, BodyMaterial.FLESH,
			HornLength.ONE_SMALL, HornLength.TWO_LONG,
				Util.newArrayListOfValues(new ListValue<>(HornType.CURLED), new ListValue<>(HornType.SPIRAL), new ListValue<>(HornType.SWEPT_BACK), new ListValue<>(HornType.CURVED), new ListValue<>(HornType.STRAIGHT)),
			PenisType.DEMON_COMMON, PenisSize.FIVE_ENORMOUS, PenisGirth.THREE_THICK,
			PenisType.NONE, PenisSize.TWO_AVERAGE, PenisGirth.THREE_THICK,
			TesticleSize.FOUR_HUGE, 4, CumProduction.SIX_EXTREME,
			TailType.DEMON_COMMON,
			VaginaType.DEMON_COMMON, Wetness.SEVEN_DROOLING, Capacity.ONE_EXTREMELY_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.SEVEN_ELASTIC, OrificePlasticity.ZERO_RUBBERY,
			WingType.DEMON_COMMON, WingSize.ONE_SMALL, WingSize.ONE_SMALL,
			GenitalArrangement.NORMAL) {

		@Override
		public Map<PersonalityTrait, PersonalityWeight> getPersonality() {
			return Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH));
		}
		
		@Override
		public SexualOrientation getSexualOrientation(Gender gender) {
			return SexualOrientation.AMBIPHILIC;
		}
	},
	
	IMP(Util.newHashMapOfValues(
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(1, 10)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(5, 10)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(90, 100))),
			AntennaType.NONE,
			ArmType.IMP, 1,
			AssType.IMP, AssSize.TWO_SMALL, AssSize.FOUR_LARGE, Wetness.FOUR_SLIMY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.SEVEN_ELASTIC, OrificePlasticity.ZERO_RUBBERY,
			BreastType.IMP,
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.SEVEN_ELASTIC, OrificePlasticity.ZERO_RUBBERY, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.F, 1, Lactation.ZERO_NONE, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.SEVEN_ELASTIC, OrificePlasticity.ZERO_RUBBERY, NippleSize.ZERO_TINY, NippleShape.VAGINA, AreolaeSize.TWO_BIG, 1,
			Height.NEGATIVE_TWO_MIMIMUM.getMedianValue(), 10, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			Height.NEGATIVE_TWO_MIMIMUM.getMedianValue(), 90, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			EarType.IMP,
			EyeType.IMP,
			FaceType.IMP, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.IMP, HairLength.TWO_SHORT, HairLength.FIVE_ABOVE_ASS,
			LegType.IMP,
			SkinType.IMP, BodyMaterial.FLESH,
			HornLength.ONE_SMALL, HornLength.TWO_LONG,
				Util.newArrayListOfValues(new ListValue<>(HornType.CURLED), new ListValue<>(HornType.SPIRAL), new ListValue<>(HornType.SWEPT_BACK), new ListValue<>(HornType.CURVED), new ListValue<>(HornType.STRAIGHT)),
			PenisType.IMP, PenisSize.FOUR_HUGE, PenisGirth.THREE_THICK,
			PenisType.NONE, PenisSize.TWO_AVERAGE, PenisGirth.THREE_THICK,
			TesticleSize.THREE_LARGE, 4, CumProduction.SIX_EXTREME,
			TailType.IMP,
			VaginaType.IMP, Wetness.SEVEN_DROOLING, Capacity.FOUR_LOOSE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FIVE_STRETCHY, OrificePlasticity.FIVE_YIELDING,
			WingType.IMP, WingSize.TWO_AVERAGE, WingSize.TWO_AVERAGE,
			GenitalArrangement.NORMAL) {
		
		@Override
		public Map<PersonalityTrait, PersonalityWeight> getPersonality() {
			return Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE));
		}
		
		@Override
		public SexualOrientation getSexualOrientation(Gender gender) {
			return SexualOrientation.AMBIPHILIC;
		}
	},

	// BOVINES:
	COW_MORPH(
			Util.newHashMapOfValues(
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(20f, 50f)),
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(0f, 0f)),
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(20f, 30f))),
			AntennaType.NONE,
			ArmType.COW_MORPH, 1,
			AssType.COW_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE,  OrificePlasticity.THREE_RESILIENT,
			BreastType.COW_MORPH,
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE,  OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.H, 3, Lactation.THREE_DECENT_AMOUNT, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE,  OrificePlasticity.THREE_RESILIENT, NippleSize.THREE_LARGE, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 4,
			195, 30, BodySize.FOUR_HUGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			185, 70, BodySize.THREE_LARGE.getMedianValue(), Muscle.TWO_TONED.getMedianValue(),
			EarType.COW_MORPH, 
			EyeType.COW_MORPH,
			FaceType.COW_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.COW_MORPH, HairLength.TWO_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.COW_MORPH,
			SkinType.COW_MORPH, BodyMaterial.FLESH,
			HornLength.TWO_LONG, HornLength.ONE_SMALL, Util.newArrayListOfValues(new ListValue<>(HornType.BOVINE_CURVED), new ListValue<>(HornType.BOVINE_STRAIGHT)),
			PenisType.BOVINE, PenisSize.THREE_LARGE, PenisGirth.THREE_THICK,
			PenisType.NONE, PenisSize.TWO_AVERAGE, PenisGirth.THREE_THICK,
			TesticleSize.THREE_LARGE, 2, CumProduction.FOUR_LARGE,
			TailType.COW_MORPH,
			VaginaType.COW_MORPH, Wetness.THREE_WET, Capacity.TWO_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			WingType.NONE, WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {
	},
	
	// CANINES:
	DOG_MORPH(
			Util.newHashMapOfValues(
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(20f, 30f)),
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(0f, 0f)),
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(20f, 30f))),
			AntennaType.NONE,
			ArmType.DOG_MORPH, 1,
			AssType.DOG_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.DOG_MORPH,
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.C, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			185, 30, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			175, 70, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.TWO_TONED.getMedianValue(),
			EarType.DOG_MORPH,
			EyeType.DOG_MORPH,
			FaceType.DOG_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.DOG_MORPH, HairLength.TWO_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.DOG_MORPH,
			SkinType.DOG_MORPH, BodyMaterial.FLESH,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(new ListValue<>(HornType.NONE)),
			PenisType.CANINE, PenisSize.TWO_AVERAGE, PenisGirth.TWO_AVERAGE,
			PenisType.NONE, PenisSize.TWO_AVERAGE, PenisGirth.TWO_AVERAGE,
			TesticleSize.THREE_LARGE, 2, CumProduction.FOUR_LARGE,
			TailType.DOG_MORPH,
			VaginaType.DOG_MORPH, Wetness.THREE_WET, Capacity.TWO_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			WingType.NONE, WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {

		@Override
		public Map<PersonalityTrait, PersonalityWeight> getPersonality() {
			return Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE));
		}
	},
	
	WOLF_MORPH(
			Util.newHashMapOfValues(
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(20f, 40f)),
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(0f, 0f)),
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(30f, 50f))),
			AntennaType.NONE,
			ArmType.LYCAN, 1,
			AssType.WOLF_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.WOLF_MORPH,
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.C, 3, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			185, 15, BodySize.THREE_LARGE.getMedianValue(), Muscle.FOUR_RIPPED.getMedianValue(),
			175, 75, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			EarType.LYCAN,
			EyeType.LYCAN,
			FaceType.LYCAN, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.LYCAN, HairLength.TWO_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.LYCAN,
			SkinType.LYCAN, BodyMaterial.FLESH,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(new ListValue<>(HornType.NONE)),
			PenisType.LUPINE, PenisSize.THREE_LARGE, PenisGirth.THREE_THICK,
			PenisType.NONE, PenisSize.TWO_AVERAGE, PenisGirth.THREE_THICK,
			TesticleSize.THREE_LARGE, 2, CumProduction.FIVE_HUGE,
			TailType.LYCAN,
			VaginaType.WOLF_MORPH, Wetness.FOUR_SLIMY, Capacity.TWO_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			WingType.NONE, WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {

	},

	// FELINES:
	CAT_MORPH(
			Util.newHashMapOfValues(
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(10f, 30f)),
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(0f, 0f)),
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(20f, 30f))),
			AntennaType.NONE,
			ArmType.CAT_MORPH, 1,
			AssType.CAT_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.CAT_MORPH,
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.C, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			175, 35, BodySize.ONE_SLENDER.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			165, 85, BodySize.ONE_SLENDER.getMedianValue(), Muscle.TWO_TONED.getMedianValue(),
			EarType.CAT_MORPH,
			EyeType.CAT_MORPH,
			FaceType.CAT_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.CAT_MORPH, HairLength.TWO_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.CAT_MORPH,
			SkinType.CAT_MORPH, BodyMaterial.FLESH,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(new ListValue<>(HornType.NONE)),
			PenisType.FELINE, PenisSize.TWO_AVERAGE, PenisGirth.TWO_AVERAGE,
			PenisType.NONE, PenisSize.TWO_AVERAGE, PenisGirth.TWO_AVERAGE,
			TesticleSize.TWO_AVERAGE, 2, CumProduction.THREE_AVERAGE,
			TailType.CAT_MORPH,
			VaginaType.CAT_MORPH, Wetness.TWO_MOIST, Capacity.TWO_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			WingType.NONE, WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {

	},

	// EQUINES:
	HORSE_MORPH(
			Util.newHashMapOfValues(
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(20f, 40f)),
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(0f, 0f)),
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(20f, 30f))),
			AntennaType.NONE,
			ArmType.HORSE_MORPH, 1,
			AssType.HORSE_MORPH, AssSize.TWO_SMALL, AssSize.FIVE_HUGE, Wetness.ZERO_DRY, Capacity.THREE_SLIGHTLY_LOOSE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.HORSE_MORPH,
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.DD, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			195, 10, BodySize.THREE_LARGE.getMedianValue(), Muscle.FOUR_RIPPED.getMedianValue(),
			180, 70, BodySize.THREE_LARGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			EarType.HORSE_MORPH,
			EyeType.HORSE_MORPH,
			FaceType.HORSE_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.HORSE_MORPH, HairLength.TWO_SHORT, HairLength.FOUR_MID_BACK,
			LegType.HORSE_MORPH,
			SkinType.HORSE_MORPH, BodyMaterial.FLESH,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(new ListValue<>(HornType.NONE)),
			PenisType.EQUINE, PenisSize.FOUR_HUGE, PenisGirth.FOUR_FAT,
			PenisType.NONE, PenisSize.TWO_AVERAGE, PenisGirth.FOUR_FAT,
			TesticleSize.FOUR_HUGE, 2, CumProduction.FIVE_HUGE,
			TailType.HORSE_MORPH,
			VaginaType.HORSE_MORPH, Wetness.THREE_WET, Capacity.THREE_SLIGHTLY_LOOSE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			WingType.NONE, WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {

	},

	REINDEER_MORPH(Util.newHashMapOfValues(
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(20f, 50f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(0f, 0f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(20f, 30f))),
			AntennaType.NONE,
			ArmType.REINDEER_MORPH, 1,
			AssType.REINDEER_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.REINDEER_MORPH,
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.D, 3, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ONE_SMALL, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			190, 10, BodySize.THREE_LARGE.getMedianValue(), Muscle.FOUR_RIPPED.getMedianValue(),
			180, 70, BodySize.THREE_LARGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			EarType.REINDEER_MORPH,
			EyeType.REINDEER_MORPH,
			FaceType.REINDEER_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.REINDEER_MORPH, HairLength.TWO_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.REINDEER_MORPH,
			SkinType.REINDEER_MORPH, BodyMaterial.FLESH,
			HornLength.THREE_HUGE, HornLength.TWO_LONG, Util.newArrayListOfValues(new ListValue<>(HornType.REINDEER_RACK)),
			PenisType.REINDEER_MORPH, PenisSize.THREE_LARGE, PenisGirth.THREE_THICK,
			PenisType.NONE, PenisSize.TWO_AVERAGE, PenisGirth.THREE_THICK,
			TesticleSize.THREE_LARGE, 2, CumProduction.THREE_AVERAGE,
			TailType.REINDEER_MORPH,
			VaginaType.REINDEER_MORPH, Wetness.TWO_MOIST, Capacity.FOUR_LOOSE, ClitorisSize.ONE_BIG, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			WingType.NONE, WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {
	
	},

	// REPTILE:
	ALLIGATOR_MORPH(Util.newHashMapOfValues(
	            new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(20f, 40f)),
	            new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(0f, 0f)),
	            new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(20f, 30f))),
		    AntennaType.NONE,
		    ArmType.ALLIGATOR_MORPH, 1,
		    AssType.ALLIGATOR_MORPH, AssSize.TWO_SMALL, AssSize.TWO_SMALL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
		    BreastType.ALLIGATOR_MORPH,
		    CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
		    CupSize.AA, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
		    185, 25, BodySize.THREE_LARGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
		    178, 95, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.TWO_TONED.getMedianValue(),
		    EarType.ALLIGATOR_MORPH,
		    EyeType.ALLIGATOR_MORPH,
		    FaceType.ALLIGATOR_MORPH, LipSize.ONE_AVERAGE, LipSize.ONE_AVERAGE,
		    HairType.ALLIGATOR_MORPH, HairLength.ZERO_BALD, HairLength.ZERO_BALD,
		    LegType.ALLIGATOR_MORPH,
		    SkinType.ALLIGATOR_MORPH, BodyMaterial.FLESH,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(new ListValue<>(HornType.NONE)),
		    PenisType.ALLIGATOR_MORPH, PenisSize.THREE_LARGE, PenisGirth.THREE_THICK,
		    PenisType.NONE, PenisSize.TWO_AVERAGE, PenisGirth.THREE_THICK,
		    TesticleSize.FOUR_HUGE, 2, CumProduction.FIVE_HUGE,
		    TailType.ALLIGATOR_MORPH,
		    VaginaType.ALLIGATOR_MORPH, Wetness.SEVEN_DROOLING, Capacity.ONE_EXTREMELY_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.SEVEN_ELASTIC, OrificePlasticity.ZERO_RUBBERY,
		    WingType.NONE, WingSize.ZERO_TINY, WingSize.ZERO_TINY,
		    GenitalArrangement.CLOACA) {
		
		@Override
		public boolean isHairTypeLinkedToFaceType() {
			return true;
		}
	},
	
	// RODENTS:
	SQUIRREL_MORPH(Util.newHashMapOfValues(
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(5f, 15f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(0f, 0f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(20f, 30f))),
			AntennaType.NONE,
			ArmType.SQUIRREL_MORPH, 1,
			AssType.SQUIRREL_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.SQUIRREL_MORPH,
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.C, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			170, 35, BodySize.ONE_SLENDER.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			160, 85, BodySize.ONE_SLENDER.getMedianValue(), Muscle.TWO_TONED.getMedianValue(),
			EarType.SQUIRREL_MORPH,
			EyeType.SQUIRREL_MORPH,
			FaceType.SQUIRREL_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.SQUIRREL_MORPH, HairLength.ONE_VERY_SHORT, HairLength.TWO_SHORT,
			LegType.SQUIRREL_MORPH,
			SkinType.SQUIRREL_MORPH, BodyMaterial.FLESH,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(new ListValue<>(HornType.NONE)),
			PenisType.SQUIRREL, PenisSize.FOUR_HUGE, PenisGirth.THREE_THICK,
			PenisType.NONE, PenisSize.TWO_AVERAGE, PenisGirth.THREE_THICK,
			TesticleSize.THREE_LARGE, 2, CumProduction.THREE_AVERAGE,
			TailType.SQUIRREL_MORPH,
			VaginaType.SQUIRREL_MORPH, Wetness.TWO_MOIST, Capacity.THREE_SLIGHTLY_LOOSE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			WingType.NONE, WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {

	},

	RAT_MORPH(Util.newHashMapOfValues(
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(10f, 20f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(0f, 0f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(30f, 50f))),
			AntennaType.NONE,
			ArmType.RAT_MORPH, 1,
			AssType.RAT_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.RAT_MORPH,
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.C, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			170, 35, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			160, 85, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.TWO_TONED.getMedianValue(),
			EarType.RAT_MORPH,
			EyeType.RAT_MORPH,
			FaceType.RAT_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.RAT_MORPH, HairLength.ONE_VERY_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.RAT_MORPH,
			SkinType.RAT_MORPH, BodyMaterial.FLESH,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(new ListValue<>(HornType.NONE)),
			PenisType.RAT_MORPH, PenisSize.THREE_LARGE, PenisGirth.TWO_AVERAGE,
			PenisType.NONE, PenisSize.TWO_AVERAGE, PenisGirth.TWO_AVERAGE,
			TesticleSize.TWO_AVERAGE, 2, CumProduction.THREE_AVERAGE,
			TailType.RAT_MORPH,
			VaginaType.RAT_MORPH, Wetness.TWO_MOIST, Capacity.THREE_SLIGHTLY_LOOSE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			WingType.NONE, WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {

		@Override
		public Map<PersonalityTrait, PersonalityWeight> getPersonality() {
			return Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE));
		}
	},

	RABBIT_MORPH(Util.newHashMapOfValues(
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(5f, 15f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(0f, 0f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(20f, 60f))),
			AntennaType.NONE,
			ArmType.RABBIT_MORPH, 1,
			AssType.RABBIT_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.RABBIT_MORPH,
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.E, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			170, 35, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			160, 85, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.TWO_TONED.getMedianValue(),
			EarType.RABBIT_MORPH,
			EyeType.RABBIT_MORPH,
			FaceType.RABBIT_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.RABBIT_MORPH, HairLength.ONE_VERY_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.RABBIT_MORPH,
			SkinType.RABBIT_MORPH, BodyMaterial.FLESH,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(new ListValue<>(HornType.NONE)),
			PenisType.RABBIT_MORPH, PenisSize.THREE_LARGE, PenisGirth.TWO_AVERAGE,
			PenisType.NONE, PenisSize.TWO_AVERAGE, PenisGirth.TWO_AVERAGE,
			TesticleSize.TWO_AVERAGE, 2, CumProduction.THREE_AVERAGE,
			TailType.RABBIT_MORPH,
			VaginaType.RABBIT_MORPH, Wetness.TWO_MOIST, Capacity.THREE_SLIGHTLY_LOOSE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			WingType.NONE, WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {

	},

	BAT_MORPH(Util.newHashMapOfValues(
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(5f, 10f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(0f, 0f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(10f, 30f))),
			AntennaType.NONE,
			ArmType.BAT_MORPH, 1,
			AssType.BAT_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.BAT_MORPH,
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.C, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			170, 35, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			160, 85, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.TWO_TONED.getMedianValue(),
			EarType.BAT_MORPH,
			EyeType.BAT_MORPH,
			FaceType.BAT_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.BAT_MORPH, HairLength.ONE_VERY_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.BAT_MORPH,
			SkinType.BAT_MORPH, BodyMaterial.FLESH,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(new ListValue<>(HornType.NONE)),
			PenisType.BAT_MORPH, PenisSize.THREE_LARGE, PenisGirth.TWO_AVERAGE,
			PenisType.NONE, PenisSize.TWO_AVERAGE, PenisGirth.TWO_AVERAGE,
			TesticleSize.TWO_AVERAGE, 2, CumProduction.THREE_AVERAGE,
			TailType.NONE,
			VaginaType.BAT_MORPH, Wetness.TWO_MOIST, Capacity.THREE_SLIGHTLY_LOOSE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			WingType.NONE, WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {

		@Override
		public Map<PersonalityTrait, PersonalityWeight> getPersonality() {
			return Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE));
		}
	},
	
	// AVIAN:
	HARPY(Util.newHashMapOfValues(
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(0f, 15f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(0f, 0f)),
				new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(20f, 50f))),
			AntennaType.NONE,
			ArmType.HARPY, 1,
			AssType.HARPY, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.HARPY,
			CupSize.TRAINING_A, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.B, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			150, 75, BodySize.ZERO_SKINNY.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			150, 95, BodySize.ZERO_SKINNY.getMedianValue(), Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue(),
			EarType.HARPY,
			EyeType.HARPY,
			FaceType.HARPY, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.HARPY, HairLength.THREE_SHOULDER_LENGTH, HairLength.FIVE_ABOVE_ASS,
			LegType.HARPY,
			SkinType.HARPY, BodyMaterial.FLESH,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(new ListValue<>(HornType.NONE)),
			PenisType.AVIAN, PenisSize.ONE_TINY, PenisGirth.ONE_THIN,
			PenisType.NONE, PenisSize.ONE_TINY, PenisGirth.ONE_THIN,
			TesticleSize.ZERO_VESTIGIAL, 2, CumProduction.ONE_TRICKLE,
			TailType.HARPY,
			VaginaType.HARPY, Wetness.THREE_WET, Capacity.TWO_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			WingType.NONE, WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.CLOACA) {

		@Override
		public Map<PersonalityTrait, PersonalityWeight> getPersonality() {
			return Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE));
		}
		
		@Override
		public SexualOrientation getSexualOrientation(Gender gender) {
			double chance = Math.random();
			
			if(chance<0.95f) {
				return SexualOrientation.GYNEPHILIC;
			} else {
				return SexualOrientation.AMBIPHILIC;
			}
		}
	};

	// Attributes modified by this Trait:
	private HashMap<Attribute, AttributeRange> attributeModifiers;

	private AntennaType antennaType;
	private ArmType armType;
	private AssType assType;
	private BreastType breastType;
	private FaceType faceType;
	private EyeType eyeType;
	private EarType earType;
	private HairType hairType;
	private LegType legType;
	private SkinType skinType;
	private List<HornType> hornTypes;
	private PenisType penisType;
	private PenisType penisSecondType;
	private TailType tailType;
	private VaginaType vaginaType;
	private WingType wingType;
	
	private BodyMaterial bodyMaterial;
	private GenitalArrangement genitalArrangement;
	
	private NippleShape maleNippleShape, femaleNippleShape;
	
	private int armRows,
			anusCapacity, anusWetness, maleAssSize, femaleAssSize, anusElasticity, anusPlasticity,
			maleHairLength, femaleHairLength,
			maleHornLength, femaleHornLength,
			noBreastSize, breastSize, maleLactationRate, femaleLactationRate, femaleBreastCapacity, maleBreastCapacity,
			femaleBreastElasticity, maleBreastElasticity, femaleBreastPlasticity, maleBreastPlasticity, maleNippleCountPerBreast, femaleNippleCountPerBreast, maleAreolaeSize, femaleAreolaeSize, maleNippleSize, femaleNippleSize,
			clitSize,
			maleHeight, femaleHeight, maleFemininity, femaleFemininity, maleBodySize, femaleBodySize, maleMuscle, femaleMuscle,
			maleLipSize, femaleLipSize,
			penisSize, penisSecondSize,
			penisGirth, penisSecondGirth,
			testicleSize, cumProduction, vaginaCapacity, vaginaWetness, vaginaElasticity, vaginaPlasticity, breastCountMale,
			breastCountFemale, testicleQuantity,
			maleWingSize, femaleWingSize;

	private RacialBody(HashMap<Attribute, AttributeRange> attributeModifiers,
			AntennaType antennaType,
			ArmType armType, int armRows,
			AssType assType, AssSize maleAssSize, AssSize femaleAssSize, Wetness anusWetness, Capacity anusCapacity, OrificeElasticity anusElasticity, OrificePlasticity anusPlasticity,
			BreastType breastType,
			CupSize maleBreastSize, int breastCountMale, Lactation maleLactationRate, Capacity maleBreastCapacity, OrificeElasticity maleBreastElasticity, OrificePlasticity maleBreastPlasticity,
				NippleSize maleNippleSize, NippleShape maleNippleShape, AreolaeSize maleAreolaeSize, int maleNippleCountPerBreast,
			CupSize femaleBreastSize, int breastCountFemale, Lactation femaleLactationRate, Capacity femaleBreastCapacity, OrificeElasticity femaleBreastElasticity, OrificePlasticity femaleBreastPlasticity,
				NippleSize femaleNippleSize, NippleShape femaleNippleShape, AreolaeSize femaleAreolaeSize, int femaleNippleCountPerBreast,
			int maleHeight, int maleFemininity, int maleBodySize, int maleMuscle,
			int femaleHeight, int femaleFemininity, int femaleBodySize, int femaleMuscle,
			EarType earType,
			EyeType eyeType,
			FaceType faceType, LipSize maleLipSize, LipSize femaleLipSize,
			HairType hairType, HairLength maleHairLength, HairLength femaleHairLength,
			LegType legType,
			SkinType skinType, BodyMaterial bodyMaterial,
			HornLength maleHornLength, HornLength femaleHornLength, List<HornType> hornTypes,
			PenisType penisType, PenisSize penisSize, PenisGirth penisGirth,
			PenisType penisSecondType, PenisSize penisSecondSize, PenisGirth penisSecondGirth,
			TesticleSize testicleSize, int testicleQuantity, CumProduction cumProduction,
			TailType tailType,
			VaginaType vaginaType, Wetness vaginaWetness, Capacity vaginaCapacity, ClitorisSize clitSize, OrificeElasticity vaginaElasticity, OrificePlasticity vaginaPlasticity,
			WingType wingType, WingSize maleWingSize, WingSize femaleWingSize,
			GenitalArrangement genitalArrangement) {
		
		this.attributeModifiers = attributeModifiers;
		
		this.antennaType = antennaType;
		this.armType = armType;
		this.armRows = armRows;
		
		this.assType = assType;
		this.breastType = breastType;
		this.faceType = faceType;
		this.eyeType = eyeType;
		this.earType = earType;
		this.hairType = hairType;
		this.legType = legType;
		this.skinType = skinType;
		this.bodyMaterial = bodyMaterial;
		this.hornTypes = hornTypes;
		
		this.penisType = penisType;
		this.penisSecondType = penisSecondType;
		this.tailType = tailType;
		this.vaginaType = vaginaType;
		this.wingType = wingType;

		this.anusCapacity = anusCapacity.getMedianValue();
		this.anusWetness = anusWetness.getValue();
		this.maleAssSize = maleAssSize.getValue();
		this.femaleAssSize = femaleAssSize.getValue();
		this.anusElasticity = anusElasticity.getValue();
		this.anusPlasticity = anusPlasticity.getValue();
		
		this.maleHairLength = maleHairLength.getMedianValue();
		this.femaleHairLength = femaleHairLength.getMedianValue();

		this.maleHornLength = maleHornLength.getMedianValue();
		this.femaleHornLength = femaleHornLength.getMedianValue();
		
		this.noBreastSize = maleBreastSize.getMeasurement();
		this.breastCountMale = breastCountMale;
		this.maleLactationRate = maleLactationRate.getMedianValue();
		this.maleBreastCapacity = maleBreastCapacity.getMedianValue();
		this.maleBreastElasticity = maleBreastElasticity.getValue();
		this.maleBreastPlasticity = maleBreastPlasticity.getValue();
		this.maleNippleSize = maleNippleSize.getValue();
		this.maleNippleShape = maleNippleShape;
		this.maleAreolaeSize = maleAreolaeSize.getValue();
		this.maleNippleCountPerBreast = maleNippleCountPerBreast;
		
		this.breastSize = femaleBreastSize.getMeasurement();
		this.breastCountFemale = breastCountFemale;
		this.femaleLactationRate = femaleLactationRate.getMedianValue();
		this.femaleBreastCapacity = femaleBreastCapacity.getMedianValue();
		this.femaleBreastElasticity = femaleBreastElasticity.getValue();
		this.femaleBreastPlasticity = femaleBreastPlasticity.getValue();
		this.femaleNippleSize = femaleNippleSize.getValue();
		this.femaleNippleShape = femaleNippleShape;
		this.femaleAreolaeSize = femaleAreolaeSize.getValue();
		this.femaleNippleCountPerBreast = femaleNippleCountPerBreast;
		
		this.clitSize = clitSize.getMedianValue();
		
		this.maleHeight = maleHeight;
		this.maleFemininity = maleFemininity;
		this.maleBodySize = maleBodySize;
		this.maleMuscle = maleMuscle;
		
		this.femaleHeight = femaleHeight;
		this.femaleFemininity = femaleFemininity;
		this.femaleBodySize = femaleBodySize;
		this.femaleMuscle = femaleMuscle;
		
		this.maleLipSize = maleLipSize.getValue();
		this.femaleLipSize = femaleLipSize.getValue();
		
		this.penisSize = penisSize.getMedianValue();
		this.penisSecondSize = penisSecondSize.getMedianValue();

		this.penisGirth = penisGirth.getValue();
		this.penisSecondGirth = penisSecondGirth.getValue();
		
		this.testicleSize = testicleSize.getValue();
		this.cumProduction = cumProduction.getMedianValue();
		this.vaginaCapacity = vaginaCapacity.getMedianValue();
		this.vaginaWetness = vaginaWetness.getValue();
		this.vaginaElasticity = vaginaElasticity.getValue();
		this.vaginaPlasticity = vaginaPlasticity.getValue();
		this.testicleQuantity = testicleQuantity;
		
		this.maleWingSize = maleWingSize.getValue();
		this.femaleWingSize = femaleWingSize.getValue();
		
		this.genitalArrangement = genitalArrangement;

	}

	public static RacialBody valueOfRace(Race race) {
		switch (race) {
			case ANGEL:
				return RacialBody.ANGEL;
			case CAT_MORPH:
				return RacialBody.CAT_MORPH;
			case COW_MORPH:
				return RacialBody.COW_MORPH;
			case DEMON:
			case ELEMENTAL_AIR:
			case ELEMENTAL_ARCANE:
			case ELEMENTAL_EARTH:
			case ELEMENTAL_FIRE:
			case ELEMENTAL_WATER:
				return RacialBody.DEMON;
			case IMP:
				return RacialBody.IMP;
			case DOG_MORPH:
				return RacialBody.DOG_MORPH;
			case ALLIGATOR_MORPH:
				return RacialBody.ALLIGATOR_MORPH;
			case HARPY:
				return RacialBody.HARPY;
			case HORSE_MORPH:
				return RacialBody.HORSE_MORPH;
			case REINDEER_MORPH:
				return RacialBody.REINDEER_MORPH;
			case HUMAN:
				return RacialBody.HUMAN;
			case WOLF_MORPH:
				return RacialBody.WOLF_MORPH;
			case SQUIRREL_MORPH:
				return RacialBody.SQUIRREL_MORPH;
			case SLIME:
				return RacialBody.HUMAN;
			case BAT_MORPH:
				return RacialBody.BAT_MORPH;
			case RAT_MORPH:
				return RacialBody.RAT_MORPH;
			case RABBIT_MORPH:
				return RacialBody.RABBIT_MORPH;
		}
		return RacialBody.HUMAN;
	}
	
	/**
	 * <b>Does not include angels or slimes</b>
	 * @param gender
	 * @return
	 */
	public static RacialBody getRandomCommonRacialBodyFromPreferences(Gender gender) {
		
		List<Race> availableRaces = new ArrayList<>();
		for(Race r : Race.values()) {
			if(r != Race.ANGEL) {
				availableRaces.add(r);
			}
		}
		
		if(gender.isFeminine()) {
			for(Entry<Subspecies, FurryPreference> entry : Main.getProperties().subspeciesFeminineFurryPreferencesMap.entrySet()) {
				if(entry.getValue() == FurryPreference.HUMAN) {
					availableRaces.remove(entry.getKey().getRace());
				}
			}
		} else {
			for(Entry<Subspecies, FurryPreference> entry : Main.getProperties().subspeciesMasculineFurryPreferencesMap.entrySet()) {
				if(entry.getValue() == FurryPreference.HUMAN) {
					availableRaces.remove(entry.getKey().getRace());
				}
			}
		}
		
		if(availableRaces.isEmpty()) {
			return RacialBody.HUMAN;
		}
		
		return valueOfRace(availableRaces.get(Util.random.nextInt(availableRaces.size())));
	}
	
	/**
	 * @return A map of personality traits and their normal associated values for this race.</br>
	 *  When generating an individual's personality, there is a 25% chance of the weight of each of these traits being moved up or down by 1 (e.g. from AVERAGE to HIGH), and a 5% chance of them being moved up or down 2 (e.g. from LOW to HIGH).</br>
	 *  As a result, a race with all weights set to AVERAGE should end up with a mostly-balanced personality, with one or two traits being skewed up or down.
	 */
	public Map<PersonalityTrait, PersonalityWeight> getPersonality() {
		return Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE));
	}
	
	public SexualOrientation getSexualOrientation(Gender gender) {
		double chance = Math.random();
		
		if(gender.isFeminine()) {
			if(chance<0.15f) {
				return SexualOrientation.GYNEPHILIC;
			} else if (chance<0.5f) {
				return SexualOrientation.ANDROPHILIC;
			} else {
				return SexualOrientation.AMBIPHILIC;
			}
			
		} else {
			if(chance<0.10f) {
				return SexualOrientation.ANDROPHILIC;
			} else if (chance<0.5f) {
				return SexualOrientation.GYNEPHILIC;
			} else {
				return SexualOrientation.AMBIPHILIC;
			}
		}
	}
	
	public HashMap<Attribute, AttributeRange> getAttributeModifiers() {
		return attributeModifiers;
	}

	public AntennaType getAntennaType() {
		return antennaType;
	}

	public ArmType getArmType() {
		return armType;
	}

	public AssType getAssType() {
		return assType;
	}

	public BreastType getBreastType() {
		return breastType;
	}

	public FaceType getFaceType() {
		return faceType;
	}

	public EyeType getEyeType() {
		return eyeType;
	}

	public EarType getEarType() {
		return earType;
	}

	public HairType getHairType() {
		return hairType;
	}
	
	/**
	 * @return true if this RacialBody requires FaceType to not be human in order to apply hair settings.
	 */
	public boolean isHairTypeLinkedToFaceType() {
		return false;
	}

	public LegType getLegType() {
		return legType;
	}

	public SkinType getSkinType() {
		return skinType;
	}
	
	public BodyMaterial getBodyMaterial() {
		return bodyMaterial;
	}

	/**
	 * @param includeTypeNONE Set as true if you want the returned HornType to possibly include HornType.NONE. (Will include NONE anyway if the list is empty.)
	 * @return A random HornType from this race's possible hornTypes.
	 */
	public HornType getRandomHornType(boolean includeTypeNONE) {
		List<HornType> hornList = new ArrayList<>(hornTypes);
		
		if(includeTypeNONE || hornTypes.size()==1) {
			return hornTypes.get(Util.random.nextInt(hornTypes.size()));
		} else {
			hornList.remove(HornType.NONE);
			return hornList.get(Util.random.nextInt(hornList.size()));
		}
	}
	
	public List<HornType> getHornType() {
		return hornTypes;
	}

	public PenisType getPenisType() {
		return penisType;
	}
	
	public PenisType getPenisSecondType() {
		return penisSecondType;
	}

	public TailType getTailType() {
		return tailType;
	}

	public VaginaType getVaginaType() {
		return vaginaType;
	}

	public WingType getWingType() {
		return wingType;
	}


	public int getArmRows() {
		return armRows;
	}
	
	public int getAnusCapacity() {
		return anusCapacity;
	}

	public int getAnusWetness() {
		return anusWetness;
	}

	public int getAnusElasticity() {
		return anusElasticity;
	}
	
	public int getAnusPlasticity() {
		return anusPlasticity;
	}

	public int getMaleHeight() {
		return maleHeight;
	}

	public int getMaleFemininity() {
		return maleFemininity;
	}

	public int getMaleMuscle() {
		return maleMuscle;
	}
	
	public int getMaleBodySize() {
		return maleBodySize;
	}

	public int getFemaleHeight() {
		return femaleHeight;
	}

	public int getFemaleFemininity() {
		return femaleFemininity;
	}
	
	public int getFemaleBodySize() {
		return femaleBodySize;
	}

	public int getFemaleMuscle() {
		return femaleMuscle;
	}

	public int getMaleAssSize() {
		return maleAssSize;
	}

	public int getFemaleAssSize() {
		return femaleAssSize;
	}

	public int getMaleHairLength() {
		return maleHairLength;
	}

	public int getFemaleHairLength() {
		return femaleHairLength;
	}

	public int getMaleHornLength() {
		return maleHornLength;
	}

	public int getFemaleHornLength() {
		return femaleHornLength;
	}

	public int getNoBreastSize() {
		return noBreastSize;
	}

	public int getBreastSize() {
		return breastSize;
	}

	public int getMaleLactationRate() {
		return maleLactationRate;
	}

	public int getFemaleLactationRate() {
		return femaleLactationRate;
	}

	public int getFemaleBreastElasticity() {
		return femaleBreastElasticity;
	}

	public int getMaleBreastElasticity() {
		return maleBreastElasticity;
	}

	public int getFemaleBreastPlasticity() {
		return femaleBreastPlasticity;
	}

	public int getMaleBreastPlasticity() {
		return maleBreastPlasticity;
	}

	public int getFemaleBreastCapacity() {
		return femaleBreastCapacity;
	}

	public int getMaleBreastCapacity() {
		return maleBreastCapacity;
	}

	public int getMaleNippleSize() {
		return maleNippleSize;
	}

	public int getFemaleNippleSize() {
		return femaleNippleSize;
	}

	public NippleShape getMaleNippleShape() {
		return maleNippleShape;
	}

	public NippleShape getFemaleNippleShape() {
		return femaleNippleShape;
	}

	public int getMaleNippleCountPerBreast() {
		return maleNippleCountPerBreast;
	}

	public int getFemaleNippleCountPerBreast() {
		return femaleNippleCountPerBreast;
	}

	public int getMaleAreolaeSize() {
		return maleAreolaeSize;
	}

	public int getFemaleAreolaeSize() {
		return femaleAreolaeSize;
	}

	public int getClitSize() {
		return clitSize;
	}

	public int getPenisSize() {
		return penisSize;
	}
	
	public int getPenisSecondSize() {
		return penisSecondSize;
	}
	
	public int getPenisGirth() {
		return penisGirth;
	}
	
	public int getPenisSecondGirth() {
		return penisSecondGirth;
	}

	public int getTesticleSize() {
		return testicleSize;
	}

	public int getCumProduction() {
		return cumProduction;
	}

	public int getVaginaCapacity() {
		return vaginaCapacity;
	}

	public int getVaginaWetness() {
		return vaginaWetness;
	}

	public int getVaginaElasticity() {
		return vaginaElasticity;
	}
	
	public int getVaginaPlasticity() {
		return vaginaPlasticity;
	}

	public int getBreastCountMale() {
		return breastCountMale;
	}

	public int getBreastCountFemale() {
		return breastCountFemale;
	}

	public int getTesticleQuantity() {
		return testicleQuantity;
	}

	public int getMaleLipSize() {
		return maleLipSize;
	}

	public int getFemaleLipSize() {
		return femaleLipSize;
	}

	public int getMaleWingSize() {
		return maleWingSize;
	}

	public int getFemaleWingSize() {
		return femaleWingSize;
	}

	public GenitalArrangement getGenitalArrangement() {
		return genitalArrangement;
	}

}
