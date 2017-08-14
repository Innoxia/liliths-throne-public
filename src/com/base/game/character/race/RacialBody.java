package com.base.game.character.race;

import java.util.HashMap;

import com.base.game.character.SexualOrientation;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.body.types.ArmType;
import com.base.game.character.body.types.AssType;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.types.BreastType;
import com.base.game.character.body.types.EarType;
import com.base.game.character.body.types.FaceType;
import com.base.game.character.body.types.HornType;
import com.base.game.character.body.types.LegType;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.TailType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.types.WingType;
import com.base.game.character.body.valueEnums.AssSize;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.ClitorisSize;
import com.base.game.character.body.valueEnums.CumProduction;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.body.valueEnums.HairLength;
import com.base.game.character.body.valueEnums.Lactation;
import com.base.game.character.body.valueEnums.OrificeElasticity;
import com.base.game.character.body.valueEnums.PenisSize;
import com.base.game.character.body.valueEnums.TesticleSize;
import com.base.game.character.body.valueEnums.Wetness;
import com.base.game.character.gender.Gender;
import com.base.utils.Util;
import com.base.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.1.82
 * @author Innoxia
 */
public enum RacialBody {

	// HUMAN:
	HUMAN(Util.newHashMapOfValues(
			new Value<Attribute, Float>(Attribute.STRENGTH, 10f),
			new Value<Attribute, Float>(Attribute.INTELLIGENCE, 10f),
			new Value<Attribute, Float>(Attribute.FITNESS, 10f),
			new Value<Attribute, Float>(Attribute.CORRUPTION, 5f)),
			ArmType.HUMAN,
			AssType.HUMAN, AssSize.TWO_SMALL, AssSize.FOUR_LARGE, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE,
			BreastType.HUMAN, CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, CupSize.C, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE,
			180, 25, 170, 75,
			FaceType.HUMAN, BodyCoveringType.EYE_HUMAN, EarType.HUMAN,
			BodyCoveringType.HAIR_HUMAN, HairLength.TWO_SHORT, HairLength.FOUR_MID_BACK,
			LegType.HUMAN,
			BodyCoveringType.HUMAN,
			HornType.NONE, HornType.NONE,
			PenisType.HUMAN, PenisSize.TWO_AVERAGE, TesticleSize.TWO_AVERAGE, 2, CumProduction.THREE_AVERAGE,
			TailType.NONE,
			VaginaType.HUMAN, Wetness.TWO_MOIST, Capacity.TWO_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER,
			WingType.NONE),

	// ANGEL:
	ANGEL(Util.newHashMapOfValues(
			new Value<Attribute, Float>(Attribute.STRENGTH, 10f),
			new Value<Attribute, Float>(Attribute.INTELLIGENCE, 80f),
			new Value<Attribute, Float>(Attribute.FITNESS, 80f),
			new Value<Attribute, Float>(Attribute.CORRUPTION, 0f)),
			ArmType.ANGEL,
			AssType.ANGEL, AssSize.TWO_SMALL, AssSize.TWO_SMALL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE,
			BreastType.ANGEL, CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, CupSize.A, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE,
			170, 55, 170, 55,
			FaceType.ANGEL, BodyCoveringType.EYE_ANGEL, EarType.ANGEL,
			BodyCoveringType.HAIR_ANGEL, HairLength.FOUR_MID_BACK, HairLength.FOUR_MID_BACK,
			LegType.ANGEL,
			BodyCoveringType.ANGEL,
			HornType.NONE, HornType.NONE,
			PenisType.ANGEL, PenisSize.FIVE_ENORMOUS, TesticleSize.FOUR_HUGE, 2, CumProduction.SEVEN_MONSTROUS,
			TailType.NONE,
			VaginaType.ANGEL, Wetness.SEVEN_DROOLING, Capacity.ONE_EXTREMELY_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.SEVEN_ELASTIC,
			WingType.ANGEL) {
		
		@Override
		public SexualOrientation getSexualOrientation(Gender gender) {
			return SexualOrientation.AMBIPHILIC;
		}
	},

	// DEMON:
	DEMON(Util.newHashMapOfValues(
			new Value<Attribute, Float>(Attribute.STRENGTH, 60f),
			new Value<Attribute, Float>(Attribute.INTELLIGENCE, 80f),
			new Value<Attribute, Float>(Attribute.FITNESS, 80f),
			new Value<Attribute, Float>(Attribute.CORRUPTION, 100f)),
			ArmType.DEMON_COMMON,
			AssType.DEMON_COMMON, AssSize.TWO_SMALL, AssSize.FOUR_LARGE, Wetness.FOUR_SLIMY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.SEVEN_ELASTIC,
			BreastType.DEMON_COMMON, CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.SEVEN_ELASTIC, CupSize.F, 3, Lactation.ZERO_NONE, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.SEVEN_ELASTIC,
			190, 10, 180, 90,
			FaceType.DEMON_COMMON, BodyCoveringType.EYE_DEMON_COMMON, EarType.DEMON_COMMON,
			BodyCoveringType.HAIR_DEMON, HairLength.FOUR_MID_BACK, HairLength.FIVE_ABOVE_ASS,
			LegType.DEMON_COMMON,
			BodyCoveringType.DEMON_COMMON,
			HornType.DEMON_COMMON_FEMALE, HornType.DEMON_COMMON_MALE,
			PenisType.DEMON_COMMON, PenisSize.FIVE_ENORMOUS, TesticleSize.FOUR_HUGE, 4, CumProduction.SEVEN_MONSTROUS,
			TailType.DEMON_COMMON,
			VaginaType.DEMON_COMMON, Wetness.SEVEN_DROOLING, Capacity.ONE_EXTREMELY_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.SEVEN_ELASTIC,
			WingType.DEMON_COMMON) {
		
		@Override
		public SexualOrientation getSexualOrientation(Gender gender) {
			return SexualOrientation.AMBIPHILIC;
		}
	},

	// CANINES:
		DOG_MORPH(
				Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.STRENGTH, 25f),
						new Value<Attribute, Float>(Attribute.INTELLIGENCE, 20f),
						new Value<Attribute, Float>(Attribute.FITNESS, 20f),
						new Value<Attribute, Float>(Attribute.CORRUPTION, 25f)),
				ArmType.DOG_MORPH,
				AssType.DOG_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE,
				BreastType.DOG_MORPH, CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, CupSize.C, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE,
				180, 30, 170, 70,
				FaceType.DOG_MORPH, BodyCoveringType.EYE_DOG_MORPH, EarType.DOG_MORPH,
				BodyCoveringType.HAIR_CANINE_FUR, HairLength.TWO_SHORT, HairLength.THREE_SHOULDER_LENGTH,
				LegType.DOG_MORPH,
				BodyCoveringType.CANINE_FUR,
				HornType.NONE, HornType.NONE,
				PenisType.CANINE, PenisSize.THREE_LARGE, TesticleSize.THREE_LARGE, 2, CumProduction.FOUR_LARGE,
				TailType.DOG_MORPH,
				VaginaType.DOG_MORPH, Wetness.THREE_WET, Capacity.TWO_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER,
				WingType.NONE),
		
	// BOVINES:
		COW_MORPH(
				Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.STRENGTH, 40f),
						new Value<Attribute, Float>(Attribute.INTELLIGENCE, 10f),
						new Value<Attribute, Float>(Attribute.FITNESS, 25f),
						new Value<Attribute, Float>(Attribute.CORRUPTION, 15f)),
				ArmType.COW_MORPH,
				AssType.COW_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE,
				BreastType.COW_MORPH, CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, CupSize.E, 3, Lactation.THREE_DECENT_AMOUNT, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE,
				180, 30, 170, 70,
				FaceType.COW_MORPH, BodyCoveringType.EYE_BOVINE, EarType.COW_MORPH,
				BodyCoveringType.HAIR_BOVINE_FUR, HairLength.TWO_SHORT, HairLength.THREE_SHOULDER_LENGTH,
				LegType.BOVINE,
				BodyCoveringType.BOVINE_FUR,
				HornType.BOVINE_FEMALE, HornType.BOVINE_MALE,
				PenisType.BOVINE, PenisSize.THREE_LARGE, TesticleSize.THREE_LARGE, 2, CumProduction.FOUR_LARGE,
				TailType.BOVINE,
				VaginaType.BOVINE, Wetness.THREE_WET, Capacity.TWO_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER,
				WingType.NONE),
		
	WOLF_MORPH(
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.STRENGTH, 30f),
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, 20f),
					new Value<Attribute, Float>(Attribute.FITNESS, 20f),
					new Value<Attribute, Float>(Attribute.CORRUPTION, 50f)),
			ArmType.LYCAN,
			AssType.WOLF_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE,
			BreastType.WOLF_MORPH, CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, CupSize.C, 3, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE,
			185, 15, 175, 75,
			FaceType.LYCAN, BodyCoveringType.EYE_LYCAN, EarType.LYCAN,
			BodyCoveringType.HAIR_LYCAN_FUR, HairLength.TWO_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.LYCAN,
			BodyCoveringType.LYCAN_FUR,
			HornType.NONE, HornType.NONE,
			PenisType.LUPINE, PenisSize.THREE_LARGE, TesticleSize.THREE_LARGE, 2, CumProduction.FIVE_HUGE,
			TailType.LYCAN,
			VaginaType.WOLF_MORPH, Wetness.FOUR_SLIMY, Capacity.TWO_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER,
			WingType.NONE),

	// FELINES:
	CAT_MORPH(
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.STRENGTH, 20f),
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, 20f),
					new Value<Attribute, Float>(Attribute.FITNESS, 30f),
					new Value<Attribute, Float>(Attribute.CORRUPTION, 25f)),
			ArmType.CAT_MORPH,
			AssType.CAT_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE,
			BreastType.CAT_MORPH, CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, CupSize.D, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE,
			170, 35, 160, 85,
			FaceType.CAT_MORPH, BodyCoveringType.EYE_FELINE, EarType.CAT_MORPH,
			BodyCoveringType.HAIR_FELINE_FUR, HairLength.THREE_SHOULDER_LENGTH, HairLength.FOUR_MID_BACK,
			LegType.CAT_MORPH,
			BodyCoveringType.FELINE_FUR,
			HornType.NONE, HornType.NONE,
			PenisType.FELINE, PenisSize.TWO_AVERAGE, TesticleSize.TWO_AVERAGE, 2, CumProduction.THREE_AVERAGE,
			TailType.CAT_MORPH,
			VaginaType.CAT_MORPH, Wetness.TWO_MOIST, Capacity.TWO_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER,
			WingType.NONE),

	// EQUINES:
	HORSE_MORPH(
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.STRENGTH, 30f),
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, 15f),
					new Value<Attribute, Float>(Attribute.FITNESS, 20f),
					new Value<Attribute, Float>(Attribute.CORRUPTION, 25f)),
			ArmType.HORSE_MORPH,
			AssType.HORSE_MORPH, AssSize.TWO_SMALL, AssSize.FIVE_HUGE, Wetness.ZERO_DRY, Capacity.THREE_SLIGHTLY_LOOSE, OrificeElasticity.THREE_FLEXIBLE,
			BreastType.HORSE_MORPH, CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, CupSize.GG, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE,
			180, 10, 170, 70,
			FaceType.HORSE_MORPH, BodyCoveringType.EYE_HORSE_MORPH, EarType.HORSE_MORPH,
			BodyCoveringType.HAIR_HORSE_HAIR, HairLength.TWO_SHORT, HairLength.FOUR_MID_BACK,
			LegType.HORSE_MORPH,
			BodyCoveringType.HORSE_HAIR,
			HornType.NONE, HornType.NONE,
			PenisType.EQUINE, PenisSize.FOUR_HUGE, TesticleSize.FOUR_HUGE, 2, CumProduction.FIVE_HUGE,
			TailType.HORSE_MORPH,
			VaginaType.HORSE_MORPH, Wetness.THREE_WET, Capacity.THREE_SLIGHTLY_LOOSE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER,
			WingType.NONE),

	// SLIMES:
	SLIME(Util.newHashMapOfValues(
			new Value<Attribute, Float>(Attribute.STRENGTH, 5f),
			new Value<Attribute, Float>(Attribute.INTELLIGENCE, 5f),
			new Value<Attribute, Float>(Attribute.FITNESS, 5f),
			new Value<Attribute, Float>(Attribute.CORRUPTION, 50f)),
			ArmType.SLIME,
			AssType.SLIME, AssSize.FIVE_HUGE, AssSize.FIVE_HUGE, Wetness.SEVEN_DROOLING, Capacity.SEVEN_GAPING, OrificeElasticity.SEVEN_ELASTIC,
			BreastType.SLIME, CupSize.JJ, 1, Lactation.SEVEN_MONSTROUS_AMOUNT_POURING, Capacity.SEVEN_GAPING, OrificeElasticity.SEVEN_ELASTIC,
				CupSize.JJ, 1, Lactation.SEVEN_MONSTROUS_AMOUNT_POURING, Capacity.SEVEN_GAPING, OrificeElasticity.SEVEN_ELASTIC,
			170, 75, 160, 95,
			FaceType.SLIME, BodyCoveringType.EYE_SLIME, EarType.SLIME,
			BodyCoveringType.HAIR_SLIME, HairLength.SEVEN_TO_FLOOR, HairLength.SEVEN_TO_FLOOR,
			LegType.SLIME,
			BodyCoveringType.SLIME,
			HornType.NONE, HornType.NONE,
			PenisType.SLIME, PenisSize.SEVEN_STALLION, TesticleSize.ZERO_VESTIGIAL, 2, CumProduction.SEVEN_MONSTROUS,
			TailType.NONE,
			VaginaType.SLIME, Wetness.SEVEN_DROOLING, Capacity.SEVEN_GAPING, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.SEVEN_ELASTIC,
			WingType.NONE),

	// RODENTS:
	SQUIRREL_MORPH(Util.newHashMapOfValues(
			new Value<Attribute, Float>(Attribute.STRENGTH, 10f),
			new Value<Attribute, Float>(Attribute.INTELLIGENCE, 30f),
			new Value<Attribute, Float>(Attribute.FITNESS, 50f),
			new Value<Attribute, Float>(Attribute.CORRUPTION, 15f)),
			ArmType.SQUIRREL_MORPH,
			AssType.SQUIRREL_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE,
			BreastType.SQUIRREL_MORPH, CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, CupSize.D, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE,
			170, 35, 160, 85,
			FaceType.SQUIRREL_MORPH, BodyCoveringType.EYE_SQUIRREL, EarType.SQUIRREL_MORPH,
			BodyCoveringType.HAIR_SQUIRREL_FUR, HairLength.ONE_VERY_SHORT, HairLength.TWO_SHORT,
			LegType.SQUIRREL_MORPH,
			BodyCoveringType.SQUIRREL_FUR,
			HornType.NONE, HornType.NONE,
			PenisType.SQUIRREL, PenisSize.THREE_LARGE, TesticleSize.THREE_LARGE, 2, CumProduction.THREE_AVERAGE,
			TailType.SQUIRREL_MORPH,
			VaginaType.SQUIRREL_MORPH, Wetness.TWO_MOIST, Capacity.FOUR_LOOSE, ClitorisSize.ONE_BIG, OrificeElasticity.FOUR_LIMBER,
			WingType.NONE),

	// AVIAN:
		HARPY(Util.newHashMapOfValues(
				new Value<Attribute, Float>(Attribute.STRENGTH, 5f),
				new Value<Attribute, Float>(Attribute.INTELLIGENCE, 10f),
				new Value<Attribute, Float>(Attribute.FITNESS, 50f),
			new Value<Attribute, Float>(Attribute.CORRUPTION, 25f)),
			ArmType.HARPY,
			AssType.HARPY, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE,
			BreastType.HARPY, CupSize.AA, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, CupSize.B, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE,
			154, 75, 154, 95,
			FaceType.HARPY, BodyCoveringType.EYE_HARPY, EarType.HARPY,
			BodyCoveringType.HAIR_HARPY, HairLength.THREE_SHOULDER_LENGTH, HairLength.FIVE_ABOVE_ASS,
			LegType.HARPY,
			BodyCoveringType.FEATHERS,
			HornType.NONE, HornType.NONE,
			PenisType.AVIAN, PenisSize.ONE_TINY, TesticleSize.ZERO_VESTIGIAL, 2, CumProduction.ONE_TRICKLE,
			TailType.HARPY,
			VaginaType.HARPY, Wetness.THREE_WET, Capacity.TWO_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER,
			WingType.NONE) {
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
	private HashMap<Attribute, Float> attributeModifiers;

	private ArmType armType;
	private AssType assType;
	private BreastType breastType;
	private FaceType faceType;
	private BodyCoveringType eyeType;
	private EarType earType;
	private BodyCoveringType hairType;
	private LegType legType;
	private BodyCoveringType skinType;
	private HornType hornTypeFemale;
	private HornType hornTypeMale;
	private PenisType penisType;
	private TailType tailType;
	private VaginaType vaginaType;
	private WingType wingType;

	private int anusCapacity, anusWetness, maleAssSize, femaleAssSize, anusPlasticity, maleHairLength, femaleHairLength, maleBreastSize, femaleBreastSize, maleLactationRate, femaleLactationRate, femaleBreastCapacity, maleBreastCapacity,
			femaleBreastPlasticity, maleBreastPlasticity, clitSize, maleHeight, femaleHeight, maleFemininity, femaleFemininity, penisSize, testicleSize, cumProduction, vaginaCapacity, vaginaWetness, vaginaPlasticity, penisCount, breastCountMale,
			breastCountFemale, testicleQuantity;

	private RacialBody(HashMap<Attribute, Float> attributeModifiers,
			ArmType armType,
			AssType assType, AssSize maleAssSize, AssSize femaleAssSize, Wetness anusWetness, Capacity anusCapacity, OrificeElasticity anusPlasticity,
			BreastType breastType, CupSize maleBreastSize, int breastCountMale, Lactation maleLactationRate, Capacity maleBreastCapacity, OrificeElasticity maleBreastPlasticity,
				CupSize femaleBreastSize, int breastCountFemale, Lactation femaleLactationRate, Capacity femaleBreastCapacity, OrificeElasticity femaleBreastPlasticity,
			int maleHeight, int maleFemininity, int femaleHeight, int femaleFemininity,
			FaceType faceType, BodyCoveringType eyeType, EarType earType,
			BodyCoveringType hairType, HairLength maleHairLength, HairLength femaleHairLength,
			LegType legType,
			BodyCoveringType skinType,
			HornType hornTypeFemale, HornType hornTypeMale,
			PenisType penisType, PenisSize penisSize, TesticleSize testicleSize, int testicleQuantity, CumProduction cumProduction,
			TailType tailType,
			VaginaType vaginaType, Wetness vaginaWetness, Capacity vaginaCapacity, ClitorisSize clitSize, OrificeElasticity vaginaPlasticity,
			WingType wingType) {

		this.attributeModifiers = attributeModifiers;
		this.armType = armType;
		this.assType = assType;
		this.breastType = breastType;
		this.faceType = faceType;
		this.eyeType = eyeType;
		this.earType = earType;
		this.hairType = hairType;
		this.legType = legType;
		this.skinType = skinType;
		this.hornTypeFemale = hornTypeFemale;
		this.hornTypeMale = hornTypeMale;
		this.penisType = penisType;
		this.tailType = tailType;
		this.vaginaType = vaginaType;
		this.wingType = wingType;

		this.anusCapacity = anusCapacity.getMedianValue();
		this.anusWetness = anusWetness.getValue();
		this.maleAssSize = maleAssSize.getValue();
		this.femaleAssSize = femaleAssSize.getValue();
		this.anusPlasticity = anusPlasticity.getValue();
		this.maleHairLength = maleHairLength.getMedianValue();
		this.femaleHairLength = femaleHairLength.getMedianValue();
		this.maleBreastSize = maleBreastSize.getMeasurement();
		this.femaleBreastSize = femaleBreastSize.getMeasurement();
		this.maleLactationRate = maleLactationRate.getMedianValue();
		this.femaleLactationRate = femaleLactationRate.getMedianValue();
		this.breastCountMale = breastCountMale;
		this.breastCountFemale = breastCountFemale;
		this.femaleBreastCapacity = femaleBreastCapacity.getMedianValue();
		this.maleBreastCapacity = maleBreastCapacity.getMedianValue();
		this.femaleBreastPlasticity = femaleBreastPlasticity.getValue();
		this.maleBreastPlasticity = maleBreastPlasticity.getValue();

		this.clitSize = clitSize.getMedianValue();
		this.maleHeight = maleHeight;
		this.femaleHeight = femaleHeight;
		this.maleFemininity = maleFemininity;
		this.femaleFemininity = femaleFemininity;
		this.penisSize = penisSize.getMedianValue();
		this.testicleSize = testicleSize.getValue();
		this.cumProduction = cumProduction.getMedianValue();
		this.vaginaCapacity = vaginaCapacity.getMedianValue();
		this.vaginaWetness = vaginaWetness.getValue();
		this.vaginaPlasticity = vaginaPlasticity.getValue();
		this.penisCount = 1;
		this.testicleQuantity = testicleQuantity;

	}

	public static RacialBody valueOfRace(Race race) {
		switch (race) {
			case ANGEL:
				return RacialBody.ANGEL;
			case CAT_MORPH:
				return RacialBody.CAT_MORPH;
			case DEMON:
				return RacialBody.DEMON;
			case DOG_MORPH:
				return RacialBody.DOG_MORPH;
			case COW_MORPH:
				return RacialBody.COW_MORPH;
			case HARPY:
				return RacialBody.HARPY;
			case HORSE_MORPH:
				return RacialBody.HORSE_MORPH;
			case HUMAN:
				return RacialBody.HUMAN;
			case WOLF_MORPH:
				return RacialBody.WOLF_MORPH;
			case SLIME:
				return RacialBody.SLIME;
			case SQUIRREL_MORPH:
				return RacialBody.SQUIRREL_MORPH;
		}
		return RacialBody.HUMAN;
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
			if(chance<0.15f) {
				return SexualOrientation.ANDROPHILIC;
			} else if (chance<0.5f) {
				return SexualOrientation.GYNEPHILIC;
			} else {
				return SexualOrientation.AMBIPHILIC;
			}
		}
	}

	public HashMap<Attribute, Float> getAttributeModifiers() {
		return attributeModifiers;
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

	public BodyCoveringType getEyeType() {
		return eyeType;
	}

	public EarType getEarType() {
		return earType;
	}

	public BodyCoveringType getHairType() {
		return hairType;
	}

	public LegType getLegType() {
		return legType;
	}

	public BodyCoveringType getSkinType() {
		return skinType;
	}

	public HornType getHornTypeFemale() {
		return hornTypeFemale;
	}

	public HornType getHornTypeMale() {
		return hornTypeMale;
	}

	public PenisType getPenisType() {
		return penisType;
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

	public int getAnusCapacity() {
		return anusCapacity;
	}

	public int getAnusWetness() {
		return anusWetness;
	}

	public int getAnusPlasticity() {
		return anusPlasticity;
	}

	public int getMaleHeight() {
		return maleHeight;
	}

	public int getFemaleHeight() {
		return femaleHeight;
	}

	public int getMaleFemininity() {
		return maleFemininity;
	}

	public int getFemaleFemininity() {
		return femaleFemininity;
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

	public int getMaleBreastSize() {
		return maleBreastSize;
	}

	public int getFemaleBreastSize() {
		return femaleBreastSize;
	}

	public int getMaleLactationRate() {
		return maleLactationRate;
	}

	public int getFemaleLactationRate() {
		return femaleLactationRate;
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

	public int getClitSize() {
		return clitSize;
	}

	public int getPenisSize() {
		return penisSize;
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

	public int getVaginaPlasticity() {
		return vaginaPlasticity;
	}

	public int getPenisCount() {
		return penisCount;
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

}
