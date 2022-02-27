package com.lilithsthrone.game.character.race;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
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
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.persona.PersonalityCategory;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.persona.SexualOrientationPreference;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.7.1
 * @author Innoxia
 */
public class RacialBody {
	
	public static AbstractRacialBody HUMAN = new AbstractRacialBody(
			Util.newArrayListOfValues(AntennaType.NONE),
			ArmType.HUMAN, 1,
			AssType.HUMAN, AssSize.TWO_SMALL, AssSize.FOUR_LARGE, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.HUMAN, BreastShape.getNonUdderBreastShapes(),
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.C, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			BreastType.NONE, BreastShape.getNonUdderBreastShapes(),
			CupSize.C, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			180, 25, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue(),
			170, 75, BodySize.TWO_AVERAGE.getMinimumValue(), Muscle.ONE_LIGHTLY_MUSCLED.getMinimumValue(),
			EarType.HUMAN,
			EyeType.HUMAN,
			FaceType.HUMAN, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.HUMAN, HairLength.TWO_SHORT, HairLength.FOUR_MID_BACK,
			LegType.HUMAN, LegConfiguration.BIPEDAL,
			TorsoType.HUMAN,
			BodyMaterial.FLESH,
			BodyCoveringType.BODY_HAIR_HUMAN,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(HornType.NONE),
			PenisType.HUMAN, 15, PenetrationGirth.THREE_AVERAGE, TesticleSize.TWO_AVERAGE, 2, CumProduction.THREE_AVERAGE,
			Util.newArrayListOfValues(TailType.NONE),
			TentacleType.NONE,
			VaginaType.HUMAN, Wetness.TWO_MOIST, Capacity.TWO_TIGHT, OrificeDepth.TWO_AVERAGE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			Util.newArrayListOfValues(WingType.NONE), WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {
	};

	public static AbstractRacialBody ANGEL = new AbstractRacialBody(
			Util.newArrayListOfValues(AntennaType.NONE),
			ArmType.ANGEL, 1,
			AssType.ANGEL, AssSize.TWO_SMALL, AssSize.TWO_SMALL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.ANGEL, BreastShape.getNonUdderBreastShapes(),
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.C, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			BreastType.NONE, BreastShape.getNonUdderBreastShapes(),
			CupSize.C, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			170, 25, BodySize.THREE_LARGE.getMedianValue(), Muscle.FOUR_RIPPED.getMedianValue(),
			170, 95, BodySize.ONE_SLENDER.getMedianValue(), Muscle.FOUR_RIPPED.getMedianValue(),
			EarType.ANGEL,
			EyeType.ANGEL,
			FaceType.ANGEL, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.ANGEL, HairLength.TWO_SHORT, HairLength.FOUR_MID_BACK,
			LegType.ANGEL, LegConfiguration.BIPEDAL,
			TorsoType.ANGEL,
			BodyMaterial.FLESH,
			BodyCoveringType.BODY_HAIR_ANGEL,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(HornType.NONE),
			PenisType.ANGEL, 13, PenetrationGirth.THREE_AVERAGE,TesticleSize.FOUR_HUGE, 2, CumProduction.SEVEN_MONSTROUS,
			Util.newArrayListOfValues(TailType.NONE),
			TentacleType.NONE,
			VaginaType.ANGEL,Wetness.ONE_SLIGHTLY_MOIST,Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.TWO_AVERAGE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.SEVEN_ELASTIC, OrificePlasticity.ZERO_RUBBERY,
			Util.newArrayListOfValues(WingType.ANGEL), WingSize.THREE_LARGE, WingSize.THREE_LARGE,
			GenitalArrangement.NORMAL) {
		@Override
		public Map<PersonalityTrait, Float> getPersonalityTraitChances() {
			Map<PersonalityTrait, Float> map = new HashMap<>();
			
			for(PersonalityTrait trait : PersonalityTrait.values()) {
				if(trait.getPersonalityCategory()!=PersonalityCategory.SPEECH) { // Angels don't have speech-related traits
					if(trait==PersonalityTrait.BRAVE) {
						map.put(trait, 0.2f);
						
					} else if(trait==PersonalityTrait.PRUDE) {
						map.put(trait, 0.75f);
						
					} else if(trait!=PersonalityTrait.LEWD) { // Angels are a proud and noble race
						map.put(trait, 0.05f);
					}
				}
			}
			
			return map;
		}
		@Override
		public SexualOrientation getSexualOrientation(Gender gender) {
			return SexualOrientationPreference.getSexualOrientationFromUserPreferences(0, 1, 0);
		}
	};

	public static AbstractRacialBody DEMON = new AbstractRacialBody(
			Util.newArrayListOfValues(AntennaType.NONE),
			ArmType.DEMON_COMMON, 1,
			AssType.DEMON_COMMON, AssSize.TWO_SMALL, AssSize.FOUR_LARGE, Wetness.FOUR_SLIMY, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.FOUR_DEEP, OrificeElasticity.SEVEN_ELASTIC, OrificePlasticity.ZERO_RUBBERY,
			BreastType.DEMON_COMMON, BreastShape.getNonUdderBreastShapes(),
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.THREE_SPACIOUS, OrificeElasticity.SEVEN_ELASTIC, OrificePlasticity.ZERO_RUBBERY,
				NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.F, 1, Lactation.ZERO_NONE, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.THREE_SPACIOUS, OrificeElasticity.SEVEN_ELASTIC, OrificePlasticity.ZERO_RUBBERY,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			BreastType.NONE, BreastShape.getNonUdderBreastShapes(),
			CupSize.C, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.THREE_SPACIOUS, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			190, 10, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			180, 90, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			EarType.DEMON_COMMON,
			EyeType.DEMON_COMMON,
			FaceType.DEMON_COMMON, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.DEMON, HairLength.TWO_SHORT, HairLength.FIVE_ABOVE_ASS,
			LegType.DEMON_COMMON, LegConfiguration.BIPEDAL,
			TorsoType.DEMON_COMMON,
			BodyMaterial.FLESH,
			BodyCoveringType.BODY_HAIR_DEMON,
			HornLength.ONE_SMALL, HornLength.TWO_LONG, Util.newArrayListOfValues(HornType.CURLED, HornType.SPIRAL, HornType.SWEPT_BACK, HornType.CURVED, HornType.STRAIGHT, HornType.ANTLERS),
			PenisType.DEMON_COMMON, 25, PenetrationGirth.FOUR_GIRTHY, TesticleSize.FOUR_HUGE, 2, CumProduction.SIX_EXTREME,
			Util.newArrayListOfValues(TailType.DEMON_COMMON, TailType.DEMON_HAIR_TIP, TailType.DEMON_HORSE),
			TentacleType.NONE, 
			VaginaType.DEMON_COMMON, Wetness.SEVEN_DROOLING, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.FOUR_DEEP, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.SEVEN_ELASTIC, OrificePlasticity.ZERO_RUBBERY,
			Util.newArrayListOfValues(WingType.DEMON_COMMON, WingType.DEMON_FEATHERED), WingSize.THREE_LARGE, WingSize.THREE_LARGE,
			GenitalArrangement.NORMAL) {
		@Override
		public Map<PersonalityTrait, Float> getPersonalityTraitChances() {
			Map<PersonalityTrait, Float> map = super.getPersonalityTraitChances();
			map.put(PersonalityTrait.LEWD, 0.8f);// Demons are lewd
			map.put(PersonalityTrait.INNOCENT, 0.01f);// Demons are very rarely innocent
			map.put(PersonalityTrait.PRUDE, 0.01f);// Demons are very rarely prudes
			return map;
		}
		@Override
		public SexualOrientation getSexualOrientation(Gender gender) {
			return SexualOrientationPreference.getSexualOrientationFromUserPreferences(0, 1, 0);
		}
		@Override
		public AbstractLegType getLegType(LegConfiguration configuration) {
			if(configuration==LegConfiguration.QUADRUPEDAL) {
				return LegType.DEMON_HORSE_HOOFED;
			}
			return super.getLegType(configuration);
		}
	};
	
	public static AbstractRacialBody COW_MORPH = new AbstractRacialBody(
			Util.newArrayListOfValues(AntennaType.NONE),
			ArmType.COW_MORPH, 1,
			AssType.COW_MORPH, AssSize.TWO_SMALL, AssSize.FIVE_HUGE, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.THREE_SPACIOUS, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.COW_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.H, 1, Lactation.THREE_DECENT_AMOUNT, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.THREE_LARGE, NippleShape.NORMAL, AreolaeSize.THREE_LARGE, 4,
			BreastType.COW_MORPH, BreastShape.getUdderBreastShapes(),
			CupSize.H, 0, Lactation.FOUR_LARGE_AMOUNT, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.FOUR_MASSIVE, NippleShape.NORMAL, AreolaeSize.THREE_LARGE, 4,
			195, 30, BodySize.FOUR_HUGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			185, 70, BodySize.THREE_LARGE.getMedianValue(), Muscle.TWO_TONED.getMedianValue(),
			EarType.COW_MORPH,
			EyeType.COW_MORPH, 
			FaceType.COW_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.COW_MORPH, HairLength.TWO_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.COW_MORPH, LegConfiguration.BIPEDAL,
			TorsoType.COW_MORPH,
			BodyMaterial.FLESH,
			BodyCoveringType.BODY_HAIR_BOVINE_FUR,
			HornLength.TWO_LONG, HornLength.ONE_SMALL, Util.newArrayListOfValues(HornType.CURVED, HornType.STRAIGHT),
			PenisType.COW_MORPH, 18, PenetrationGirth.FOUR_GIRTHY, TesticleSize.THREE_LARGE, 2, CumProduction.FOUR_LARGE,
			Util.newArrayListOfValues(TailType.COW_MORPH),
			TentacleType.NONE,
			VaginaType.COW_MORPH, Wetness.THREE_WET, Capacity.TWO_TIGHT, OrificeDepth.THREE_SPACIOUS, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			Util.newArrayListOfValues(WingType.NONE), WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {
	};
	
	public static AbstractRacialBody DOG_MORPH = new AbstractRacialBody(
			Util.newArrayListOfValues(AntennaType.NONE),
			ArmType.DOG_MORPH, 1,
			AssType.DOG_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.DOG_MORPH, BreastShape.getNonUdderBreastShapes(), 
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.C, 3, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			BreastType.DOG_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.AA, 2, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			185, 30, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			175, 70, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.TWO_TONED.getMedianValue(),
			EarType.DOG_MORPH,
			EyeType.DOG_MORPH,
			FaceType.DOG_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.DOG_MORPH, HairLength.TWO_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.DOG_MORPH, LegConfiguration.BIPEDAL,
			TorsoType.DOG_MORPH,
			BodyMaterial.FLESH,
			BodyCoveringType.BODY_HAIR_CANINE_FUR,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(HornType.NONE),
			PenisType.DOG_MORPH, 15, PenetrationGirth.THREE_AVERAGE, TesticleSize.THREE_LARGE, 2, CumProduction.FOUR_LARGE,
			Util.newArrayListOfValues(TailType.DOG_MORPH),
			TentacleType.NONE,
			VaginaType.DOG_MORPH, Wetness.THREE_WET, Capacity.TWO_TIGHT, OrificeDepth.TWO_AVERAGE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			Util.newArrayListOfValues(WingType.NONE), WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {
		@Override
		public Map<PersonalityTrait, Float> getPersonalityTraitChances() {
			Map<PersonalityTrait, Float> map = super.getPersonalityTraitChances();
			map.put(PersonalityTrait.CONFIDENT, 0.5f);// Good doggos
			return map;
		}
	};
	
	public static AbstractRacialBody FOX_MORPH = new AbstractRacialBody(
			Util.newArrayListOfValues(AntennaType.NONE),
			ArmType.FOX_MORPH, 1,
			AssType.FOX_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.FOX_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.C, 3, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			BreastType.FOX_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.AA, 2, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			170, 15, BodySize.ONE_SLENDER.getMedianValue(), Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue(),
			160, 75, BodySize.ONE_SLENDER.getMedianValue(), Muscle.ONE_LIGHTLY_MUSCLED.getMinimumValue(),
			EarType.FOX_MORPH,
			EyeType.FOX_MORPH,
			FaceType.FOX_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL, HairType.FOX_MORPH,
			HairLength.TWO_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.FOX_MORPH, LegConfiguration.BIPEDAL,
			TorsoType.FOX_MORPH,
			BodyMaterial.FLESH,
			BodyCoveringType.BODY_HAIR_FOX_FUR,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(HornType.NONE),
			PenisType.FOX_MORPH, 15, PenetrationGirth.THREE_AVERAGE, TesticleSize.TWO_AVERAGE, 2, CumProduction.FOUR_LARGE,
			Util.newArrayListOfValues(TailType.FOX_MORPH),
			TentacleType.NONE,
			VaginaType.FOX_MORPH, Wetness.THREE_WET, Capacity.TWO_TIGHT, OrificeDepth.TWO_AVERAGE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			Util.newArrayListOfValues(WingType.NONE), WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {
	};
	
	public static AbstractRacialBody WOLF_MORPH = new AbstractRacialBody(
			Util.newArrayListOfValues(AntennaType.NONE),
			ArmType.WOLF_MORPH, 1,
			AssType.WOLF_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.WOLF_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.C, 3, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			BreastType.WOLF_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.AA, 2, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			185, 15, BodySize.THREE_LARGE.getMedianValue(), Muscle.FOUR_RIPPED.getMedianValue(),
			175, 75, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			EarType.WOLF_MORPH,
			EyeType.WOLF_MORPH,
			FaceType.WOLF_MORPH,
			LipSize.ONE_AVERAGE, LipSize.TWO_FULL, HairType.WOLF_MORPH,
			HairLength.TWO_SHORT, HairLength.THREE_SHOULDER_LENGTH, 
			LegType.WOLF_MORPH, LegConfiguration.BIPEDAL,
			TorsoType.WOLF_MORPH,
			BodyMaterial.FLESH,
			BodyCoveringType.BODY_HAIR_LYCAN_FUR,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(HornType.NONE),
			PenisType.WOLF_MORPH, 18, PenetrationGirth.FOUR_GIRTHY, TesticleSize.THREE_LARGE, 2, CumProduction.FIVE_HUGE,
			Util.newArrayListOfValues(TailType.WOLF_MORPH),
			TentacleType.NONE,
			VaginaType.WOLF_MORPH, Wetness.FOUR_SLIMY, Capacity.TWO_TIGHT, OrificeDepth.TWO_AVERAGE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			Util.newArrayListOfValues(WingType.NONE), WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {
	};

	public static AbstractRacialBody CAT_MORPH = new AbstractRacialBody(
			Util.newArrayListOfValues(AntennaType.NONE),
			ArmType.CAT_MORPH, 1,
			AssType.CAT_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.CAT_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.C, 2, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			BreastType.CAT_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.AA, 2, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			175, 35, BodySize.ONE_SLENDER.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			165, 85, BodySize.ONE_SLENDER.getMedianValue(), Muscle.TWO_TONED.getMedianValue(),
			EarType.CAT_MORPH,
			EyeType.CAT_MORPH,
			FaceType.CAT_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.CAT_MORPH, HairLength.TWO_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.CAT_MORPH, LegConfiguration.BIPEDAL,
			TorsoType.CAT_MORPH,
			BodyMaterial.FLESH,
			BodyCoveringType.BODY_HAIR_FELINE_FUR,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(HornType.NONE),
			PenisType.CAT_MORPH, 13, PenetrationGirth.THREE_AVERAGE, TesticleSize.TWO_AVERAGE, 2, CumProduction.THREE_AVERAGE,
			Util.newArrayListOfValues(TailType.CAT_MORPH),
			TentacleType.NONE,
			VaginaType.CAT_MORPH, Wetness.TWO_MOIST, Capacity.TWO_TIGHT, OrificeDepth.TWO_AVERAGE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			Util.newArrayListOfValues(WingType.NONE), WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {
	};

	public static AbstractRacialBody HORSE_MORPH = new AbstractRacialBody(
			Util.newArrayListOfValues(AntennaType.NONE),
			ArmType.HORSE_MORPH, 1,
			AssType.HORSE_MORPH, AssSize.TWO_SMALL, AssSize.FOUR_LARGE, Wetness.ZERO_DRY, Capacity.THREE_SLIGHTLY_LOOSE, OrificeDepth.THREE_SPACIOUS, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.HORSE_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.DD, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			BreastType.HORSE_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.B, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			195, 10, BodySize.THREE_LARGE.getMedianValue(), Muscle.FOUR_RIPPED.getMedianValue(),
			180, 70, BodySize.THREE_LARGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			EarType.HORSE_MORPH,
			EyeType.HORSE_MORPH,
			FaceType.HORSE_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.HORSE_MORPH, HairLength.TWO_SHORT, HairLength.FOUR_MID_BACK,
			LegType.HORSE_MORPH, LegConfiguration.BIPEDAL,
			TorsoType.HORSE_MORPH,
			BodyMaterial.FLESH,
			BodyCoveringType.BODY_HAIR_HORSE_HAIR,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(HornType.NONE, HornType.HORSE_STRAIGHT),
			PenisType.EQUINE, 23, PenetrationGirth.FIVE_THICK, TesticleSize.FOUR_HUGE, 2, CumProduction.FIVE_HUGE, Util.newArrayListOfValues(TailType.HORSE_MORPH),
			TentacleType.NONE,
			VaginaType.HORSE_MORPH, Wetness.THREE_WET, Capacity.THREE_SLIGHTLY_LOOSE, OrificeDepth.THREE_SPACIOUS, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			Util.newArrayListOfValues(WingType.NONE), WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {
	};

	public static AbstractRacialBody REINDEER_MORPH = new AbstractRacialBody(
			Util.newArrayListOfValues(AntennaType.NONE),
			ArmType.REINDEER_MORPH, 1,
			AssType.REINDEER_MORPH, AssSize.TWO_SMALL, AssSize.FOUR_LARGE, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.THREE_SPACIOUS, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.REINDEER_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.D, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			BreastType.REINDEER_MORPH, BreastShape.getUdderBreastShapes(),
			CupSize.C, 2, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			190, 10, BodySize.THREE_LARGE.getMedianValue(), Muscle.FOUR_RIPPED.getMedianValue(),
			180, 70, BodySize.THREE_LARGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			EarType.REINDEER_MORPH,
			EyeType.REINDEER_MORPH,
			FaceType.REINDEER_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.REINDEER_MORPH, HairLength.TWO_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.REINDEER_MORPH, LegConfiguration.BIPEDAL,
			TorsoType.REINDEER_MORPH,
			BodyMaterial.FLESH,
			BodyCoveringType.BODY_HAIR_REINDEER_HAIR,
			HornLength.THREE_HUGE, HornLength.TWO_LONG, Util.newArrayListOfValues(HornType.REINDEER_RACK),
			PenisType.REINDEER_MORPH, 20, PenetrationGirth.FOUR_GIRTHY, TesticleSize.THREE_LARGE, 2, CumProduction.THREE_AVERAGE,
			Util.newArrayListOfValues(TailType.REINDEER_MORPH),
			TentacleType.NONE,
			VaginaType.REINDEER_MORPH, Wetness.TWO_MOIST, Capacity.THREE_SLIGHTLY_LOOSE, OrificeDepth.THREE_SPACIOUS, ClitorisSize.ONE_BIG, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			Util.newArrayListOfValues(WingType.NONE), WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {
	};

	public static AbstractRacialBody ALLIGATOR_MORPH = new AbstractRacialBody(
			Util.newArrayListOfValues(AntennaType.NONE),
		    ArmType.ALLIGATOR_MORPH, 1,
		    AssType.ALLIGATOR_MORPH, AssSize.TWO_SMALL, AssSize.TWO_SMALL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
		    BreastType.ALLIGATOR_MORPH, BreastShape.getNonUdderBreastShapes(),
		    CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
		    	NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
		    CupSize.AA,  1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
		    	NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
		    BreastType.NONE, BreastShape.getNonUdderBreastShapes(),
		    CupSize.FLAT,  1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
		    	NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
		    185, 25, BodySize.THREE_LARGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
		    178, 95, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.TWO_TONED.getMedianValue(),
		    EarType.ALLIGATOR_MORPH,
		    EyeType.ALLIGATOR_MORPH,
		    FaceType.ALLIGATOR_MORPH, LipSize.ONE_AVERAGE, LipSize.ONE_AVERAGE,
		    HairType.ALLIGATOR_MORPH, HairLength.ONE_VERY_SHORT, HairLength.THREE_SHOULDER_LENGTH,
		    LegType.ALLIGATOR_MORPH, LegConfiguration.BIPEDAL,
		    TorsoType.ALLIGATOR_MORPH,
		    BodyMaterial.FLESH,
		    BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(HornType.NONE),
		    PenisType.ALLIGATOR_MORPH, 18, PenetrationGirth.FOUR_GIRTHY, TesticleSize.FOUR_HUGE, 2, CumProduction.FIVE_HUGE,
		    Util.newArrayListOfValues(TailType.ALLIGATOR_MORPH),
		    TentacleType.NONE,
		    VaginaType.ALLIGATOR_MORPH, Wetness.SEVEN_DROOLING, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.TWO_AVERAGE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.SEVEN_ELASTIC, OrificePlasticity.ZERO_RUBBERY,
		    Util.newArrayListOfValues(WingType.NONE),  WingSize.ZERO_TINY, WingSize.ZERO_TINY,
		    GenitalArrangement.CLOACA) {
	};
	
	public static AbstractRacialBody SQUIRREL_MORPH = new AbstractRacialBody(
			Util.newArrayListOfValues(AntennaType.NONE),
			ArmType.SQUIRREL_MORPH, 1,
			AssType.SQUIRREL_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.SQUIRREL_MORPH,
			BreastShape.getNonUdderBreastShapes(),
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.C, 3, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			BreastType.SQUIRREL_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.AA, 2, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			170, 35, BodySize.ONE_SLENDER.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			160, 85, BodySize.ONE_SLENDER.getMedianValue(), Muscle.TWO_TONED.getMedianValue(),
			EarType.SQUIRREL_MORPH,
			EyeType.SQUIRREL_MORPH,
			FaceType.SQUIRREL_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.SQUIRREL_MORPH, HairLength.ONE_VERY_SHORT, HairLength.TWO_SHORT,
			LegType.SQUIRREL_MORPH, LegConfiguration.BIPEDAL,
			TorsoType.SQUIRREL_MORPH,
			BodyMaterial.FLESH,
		    BodyCoveringType.BODY_HAIR_SQUIRREL_FUR,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(HornType.NONE),
			PenisType.SQUIRREL_MORPH, 20, PenetrationGirth.FOUR_GIRTHY, TesticleSize.THREE_LARGE, 2, CumProduction.THREE_AVERAGE,
			Util.newArrayListOfValues(TailType.SQUIRREL_MORPH),
			TentacleType.NONE,
			VaginaType.SQUIRREL_MORPH, Wetness.TWO_MOIST, Capacity.THREE_SLIGHTLY_LOOSE, OrificeDepth.THREE_SPACIOUS, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			Util.newArrayListOfValues(WingType.NONE), WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {
	};

	public static AbstractRacialBody RAT_MORPH = new AbstractRacialBody(
			Util.newArrayListOfValues(AntennaType.NONE),
			ArmType.RAT_MORPH, 1,
			AssType.RAT_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.RAT_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.C, 3, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			BreastType.RAT_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.AA, 3, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			170, 35, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			160, 85, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.TWO_TONED.getMedianValue(),
			EarType.RAT_MORPH,
			EyeType.RAT_MORPH,
			FaceType.RAT_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.RAT_MORPH, HairLength.ONE_VERY_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.RAT_MORPH, LegConfiguration.BIPEDAL,
			TorsoType.RAT_MORPH,
			BodyMaterial.FLESH,
		    BodyCoveringType.BODY_HAIR_RAT_FUR,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(HornType.NONE),
			PenisType.RAT_MORPH, 15, PenetrationGirth.THREE_AVERAGE,
			TesticleSize.TWO_AVERAGE, 2, CumProduction.THREE_AVERAGE,
			Util.newArrayListOfValues(TailType.RAT_MORPH),
			TentacleType.NONE,
			VaginaType.RAT_MORPH, Wetness.TWO_MOIST, Capacity.TWO_TIGHT, OrificeDepth.TWO_AVERAGE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			Util.newArrayListOfValues(WingType.NONE), WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {
		@Override
		public Map<PersonalityTrait, Float> getPersonalityTraitChances() {
			Map<PersonalityTrait, Float> map = super.getPersonalityTraitChances();
			map.put(PersonalityTrait.COWARDLY, 0.1f);// Slightly higher chance than normal to be cowardly
			map.put(PersonalityTrait.SLOVENLY, 0.2f);// Higher chance to be slovenly
			return map;
		}
	};

	public static AbstractRacialBody RABBIT_MORPH = new AbstractRacialBody(
			Util.newArrayListOfValues(AntennaType.NONE),
			ArmType.RABBIT_MORPH, 1,
			AssType.RABBIT_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.RABBIT_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.C, 3, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			BreastType.RABBIT_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.AA, 2, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			170, 35, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			160, 85, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.TWO_TONED.getMedianValue(),
			EarType.RABBIT_MORPH,
			EyeType.RABBIT_MORPH,
			FaceType.RABBIT_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.RABBIT_MORPH, HairLength.ONE_VERY_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.RABBIT_MORPH, LegConfiguration.BIPEDAL,
			TorsoType.RABBIT_MORPH,
			BodyMaterial.FLESH,
		    BodyCoveringType.BODY_HAIR_RABBIT_FUR,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(HornType.NONE),
			PenisType.RABBIT_MORPH, 18, PenetrationGirth.THREE_AVERAGE, TesticleSize.TWO_AVERAGE, 2, CumProduction.THREE_AVERAGE,
			Util.newArrayListOfValues(TailType.RABBIT_MORPH),
			TentacleType.NONE,
			VaginaType.RABBIT_MORPH, Wetness.TWO_MOIST, Capacity.TWO_TIGHT, OrificeDepth.TWO_AVERAGE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			Util.newArrayListOfValues(WingType.NONE), WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {
	};

	public static AbstractRacialBody BAT_MORPH = new AbstractRacialBody(
			Util.newArrayListOfValues(AntennaType.NONE),
			ArmType.BAT_MORPH, 1,
			AssType.BAT_MORPH, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.BAT_MORPH, BreastShape.getNonUdderBreastShapes(),
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.B, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			BreastType.NONE, BreastShape.getNonUdderBreastShapes(),
			CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			170, 35, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			160, 85, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.TWO_TONED.getMedianValue(),
			EarType.BAT_MORPH,
			EyeType.BAT_MORPH,
			FaceType.BAT_MORPH, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.BAT_MORPH, HairLength.ONE_VERY_SHORT, HairLength.THREE_SHOULDER_LENGTH,
			LegType.BAT_MORPH, LegConfiguration.BIPEDAL,
			TorsoType.BAT_MORPH,
			BodyMaterial.FLESH,
		    BodyCoveringType.BODY_HAIR_BAT_FUR,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(HornType.NONE),
			PenisType.BAT_MORPH, 13, PenetrationGirth.THREE_AVERAGE, TesticleSize.TWO_AVERAGE, 2, CumProduction.THREE_AVERAGE,
			Util.newArrayListOfValues(TailType.BAT_MORPH),
			TentacleType.NONE,
			VaginaType.BAT_MORPH, Wetness.TWO_MOIST, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.TWO_AVERAGE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			Util.newArrayListOfValues(WingType.NONE), WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.NORMAL) {
		@Override
		public Map<PersonalityTrait, Float> getPersonalityTraitChances() {
			Map<PersonalityTrait, Float> map = super.getPersonalityTraitChances();
			map.put(PersonalityTrait.CONFIDENT, 0.15f);// Higher chance than normal to be excitable
			return map;
		}
	};
	
	public static AbstractRacialBody HARPY = new AbstractRacialBody(
			Util.newArrayListOfValues(AntennaType.NONE),
			ArmType.HARPY, 1,
			AssType.HARPY, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
			BreastType.HARPY, BreastShape.getNonUdderBreastShapes(),
			CupSize.TRAINING_A, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			CupSize.B, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.TWO_BIG, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
			BreastType.NONE, BreastShape.getNonUdderBreastShapes(),
			CupSize.TRAINING_A, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeDepth.TWO_AVERAGE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
				NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
			150, 75, BodySize.ZERO_SKINNY.getMedianValue(), Muscle.THREE_MUSCULAR.getMedianValue(),
			150, 95, BodySize.ZERO_SKINNY.getMedianValue(), Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue(),
			EarType.HARPY,
			EyeType.HARPY,
			FaceType.HARPY, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
			HairType.HARPY, HairLength.THREE_SHOULDER_LENGTH, HairLength.FIVE_ABOVE_ASS,
			LegType.HARPY, LegConfiguration.BIPEDAL,
			TorsoType.HARPY,
			BodyMaterial.FLESH,
		    BodyCoveringType.BODY_HAIR_HARPY,
			HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(HornType.NONE),
			PenisType.HARPY, 5, PenetrationGirth.TWO_NARROW, TesticleSize.ZERO_VESTIGIAL, 2, CumProduction.ONE_TRICKLE,
			Util.newArrayListOfValues(TailType.HARPY),
			TentacleType.NONE,
			VaginaType.HARPY, Wetness.THREE_WET, Capacity.ONE_EXTREMELY_TIGHT, OrificeDepth.TWO_AVERAGE, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
			Util.newArrayListOfValues(WingType.NONE), WingSize.ZERO_TINY, WingSize.ZERO_TINY,
			GenitalArrangement.CLOACA) {
		@Override
		public Map<PersonalityTrait, Float> getPersonalityTraitChances() {
			Map<PersonalityTrait, Float> map = super.getPersonalityTraitChances();
			map.put(PersonalityTrait.LEWD, 0.25f);
			map.put(PersonalityTrait.COWARDLY, 0.1f);
			return map;
		}
		@Override
		public SexualOrientation getSexualOrientation(Gender gender) {
			return SexualOrientationPreference.getSexualOrientationFromUserPreferences(95, 5, 0);
		}
	};
	
	public static AbstractRacialBody valueOfRace(AbstractRace race) {
		return race.getRacialBody();
	}
	
	public static List<AbstractRacialBody> allRacialBodies;
	
	public static Map<AbstractRacialBody, String> RacialBodyToIdMap = new HashMap<>();
	public static Map<String, AbstractRacialBody> idToRacialBodyMap = new HashMap<>();
	
	/**
	 * @param id Will be in the format of: 'innoxia_maid'.
	 */
	public static AbstractRacialBody getRacialBodyFromId(String id) {
		id = Util.getClosestStringMatch(id, idToRacialBodyMap.keySet());
		return idToRacialBodyMap.get(id);
	}
	
	public static String getIdFromRacialBody(AbstractRacialBody perk) {
		return RacialBodyToIdMap.get(perk);
	}

	static {
		allRacialBodies = new ArrayList<>();

		// Modded racial bodies:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", null, "racialBody");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractRacialBody racialBody = new AbstractRacialBody(innerEntry.getValue(), entry.getKey(), true) {};
					String id = innerEntry.getKey().replaceAll("_racialBody", "");
					allRacialBodies.add(racialBody);
					RacialBodyToIdMap.put(racialBody, id);
					idToRacialBodyMap.put(id, racialBody);
				} catch(Exception ex) {
					System.err.println("Loading modded racialBody failed at 'RacialBody'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res racial bodies:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", null, "racialBody");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractRacialBody racialBody = new AbstractRacialBody(innerEntry.getValue(), entry.getKey(), false) {};
					String id = innerEntry.getKey().replaceAll("_racialBody", "");
					allRacialBodies.add(racialBody);
					RacialBodyToIdMap.put(racialBody, id);
					idToRacialBodyMap.put(id, racialBody);
				} catch(Exception ex) {
					System.err.println("Loading racialBody failed at 'RacialBody'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// Hard-coded:
		
		Field[] fields = RacialBody.class.getFields();
		
		for(Field f : fields){
			if (AbstractRacialBody.class.isAssignableFrom(f.getType())) {
				
				AbstractRacialBody RacialBody;
				
				try {
					RacialBody = ((AbstractRacialBody) f.get(null));

					RacialBodyToIdMap.put(RacialBody, f.getName());
					idToRacialBodyMap.put(f.getName(), RacialBody);
					allRacialBodies.add(RacialBody);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static List<AbstractRacialBody> getAllRacialBodies() {
		return allRacialBodies;
	}
}
