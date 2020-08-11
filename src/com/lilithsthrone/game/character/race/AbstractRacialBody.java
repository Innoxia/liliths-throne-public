package com.lilithsthrone.game.character.race;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractAntennaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractArmType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEarType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTentacleType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
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
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.1
 * @version 0.3.8.2
 * @author Innoxia
 */
public abstract class AbstractRacialBody {
	// Antenna:
	private AbstractAntennaType antennaType;
	
	// Arms:
	private AbstractArmType armType;
	private int armRows;

	// Ass:
	private AbstractAssType assType;
	private float anusCapacity;
	private int anusDepth;
	private int anusWetness;
	private int maleAssSize;
	private int femaleAssSize;
	private int anusElasticity;
	private int anusPlasticity;

	// Breasts:
	private AbstractBreastType breastType;
	private List<BreastShape> breastShapes;
	private NippleShape maleNippleShape;
	private NippleShape femaleNippleShape;
	private int noBreastSize;
	private int breastSize;
	private int maleLactationRate;
	private int femaleLactationRate;
	private float femaleBreastCapacity;
	private float maleBreastCapacity;
	private int femaleBreastDepth;
	private int maleBreastDepth;
	private int femaleBreastElasticity;
	private int maleBreastElasticity;
	private int femaleBreastPlasticity;
	private int maleBreastPlasticity;
	private int maleNippleCountPerBreast;
	private int femaleNippleCountPerBreast;
	private int maleAreolaeSize;
	private int femaleAreolaeSize;
	private int maleNippleSize;
	private int femaleNippleSize;
	private int breastCountMale;
	private int breastCountFemale;

	// BreastCrotchs/Crotch-boobs:
	private AbstractBreastType breastCrotchType;
	private List<BreastShape> breastCrotchShapes;
	private NippleShape breastCrotchNippleShape;
	private int breastCrotchSize;
	private int breastCrotchLactationRate;
	private float breastCrotchCapacity;
	private int breastCrotchDepth;
	private int breastCrotchElasticity;
	private int breastCrotchPlasticity;
	private int nippleCountPerBreastCrotch;
	private int breastCrotchAreolaeSize;
	private int breastCrotchNippleSize;
	private int breastCrotchCount;

	// Core:
	private AbstractTorsoType skinType;
	private BodyMaterial bodyMaterial;
	private GenitalArrangement genitalArrangement;
	private int maleHeight;
	private int femaleHeight;
	private int maleFemininity;
	private int femaleFemininity;
	private int maleBodySize;
	private int femaleBodySize;
	private int maleMuscle;
	private int femaleMuscle;
	
	// Hair:
	private AbstractHairType hairType;
	private int maleHairLength;
	private int femaleHairLength;

	// Horns:
	private List<AbstractHornType> hornTypes;
	private int maleHornLength;
	private int femaleHornLength;

	// Face:
	private AbstractFaceType faceType;
	private AbstractEyeType eyeType;
	private AbstractEarType earType;
	private int maleLipSize;
	private int femaleLipSize;

	// Legs:
	private AbstractLegType legType;
	private LegConfiguration legConfiguration;

	// Penis:
	private AbstractPenisType penisType;
	private int penisSize;
	private int penisGirth;
	private int testicleSize;
	private int cumProduction;

	// Tail:
	private List<AbstractTailType> tailTypes;
	
	// Tentacle:
	private AbstractTentacleType tentacleType;
	
	// Vagina:
	private AbstractVaginaType vaginaType;
	private float vaginaCapacity;
	private int vaginaDepth;
	private int vaginaWetness;
	private int clitSize;
	private int vaginaElasticity;
	private int vaginaPlasticity;
	private int testicleQuantity;

	// Wings:
	private List<AbstractWingType> wingTypes;
	private int maleWingSize;
	private int femaleWingSize;

	public AbstractRacialBody(AbstractAntennaType antennaType,
			AbstractArmType armType, int armRows,
			AbstractAssType assType, AssSize maleAssSize, AssSize femaleAssSize, Wetness anusWetness, Capacity anusCapacity, OrificeDepth anusDepth, OrificeElasticity anusElasticity, OrificePlasticity anusPlasticity,
			AbstractBreastType breastType, List<BreastShape> breastShapes,
			CupSize noBreastSize, int breastCountMale, Lactation maleLactationRate, Capacity maleBreastCapacity, OrificeDepth maleBreastDepth, OrificeElasticity maleBreastElasticity, OrificePlasticity maleBreastPlasticity, NippleSize maleNippleSize,
				NippleShape maleNippleShape, AreolaeSize maleAreolaeSize, int maleNippleCountPerBreast,
			CupSize breastSize, int breastCountFemale, Lactation femaleLactationRate, Capacity femaleBreastCapacity, OrificeDepth femaleBreastDepth, OrificeElasticity femaleBreastElasticity, OrificePlasticity femaleBreastPlasticity, NippleSize femaleNippleSize,
				NippleShape femaleNippleShape, AreolaeSize femaleAreolaeSize, int femaleNippleCountPerBreast,
			AbstractBreastType breastCrotchType, List<BreastShape> breastCrotchShapes,
			CupSize breastCrotchSize, int breastCrotchCount, Lactation breastCrotchLactationRate, Capacity breastCrotchCapacity, OrificeDepth breastCrotchDepth, OrificeElasticity breastCrotchElasticity, OrificePlasticity breastCrotchPlasticity, NippleSize breastCrotchNippleSize,
				NippleShape breastCrotchNippleShape, AreolaeSize breastCrotchAreolaeSize, int nippleCountPerBreastCrotch,
			int maleHeight, int maleFemininity, int maleBodySize, int maleMuscle,
			int femaleHeight, int femaleFemininity, int femaleBodySize, int femaleMuscle,
			AbstractEarType earType,
			AbstractEyeType eyeType,
			AbstractFaceType faceType, LipSize maleLipSize, LipSize femaleLipSize,
			AbstractHairType hairType, HairLength maleHairLength, HairLength femaleHairLength,
			AbstractLegType legType, LegConfiguration legConfiguration,
			AbstractTorsoType skinType,
			BodyMaterial bodyMaterial,
			HornLength maleHornLength, HornLength femaleHornLength, List<AbstractHornType> hornTypes,
			AbstractPenisType penisType, int penisSize, PenetrationGirth penisGirth, TesticleSize testicleSize, int testicleQuantity, CumProduction cumProduction,
			List<AbstractTailType> tailTypes,
			AbstractTentacleType tentacleType,
			AbstractVaginaType vaginaType, Wetness vaginaWetness, Capacity vaginaCapacity, OrificeDepth vaginaDepth, ClitorisSize clitSize, OrificeElasticity vaginaElasticity, OrificePlasticity vaginaPlasticity,
			List<AbstractWingType> wingTypes, WingSize maleWingSize, WingSize femaleWingSize,
			GenitalArrangement genitalArrangement) {

		// Antenna:
		this.antennaType = antennaType;
		
		// Arms:
		this.armType = armType;
		this.armRows = armRows;
		
		// Ass:
		this.assType = assType;
		this.anusCapacity = anusCapacity.getMedianValue();
		this.anusDepth = anusDepth.getValue();
		this.anusWetness = anusWetness.getValue();
		this.maleAssSize = maleAssSize.getValue();
		this.femaleAssSize = femaleAssSize.getValue();
		this.anusElasticity = anusElasticity.getValue();
		this.anusPlasticity = anusPlasticity.getValue();
		
		// Breasts:
		this.breastType = breastType;
		this.breastShapes = breastShapes;
		this.noBreastSize = noBreastSize.getMeasurement();
		this.breastCountMale = breastCountMale;
		this.maleLactationRate = maleLactationRate.getMedianValue();
		this.maleBreastCapacity = maleBreastCapacity.getMedianValue();
		this.maleBreastDepth = maleBreastDepth.getValue();
		this.maleBreastElasticity = maleBreastElasticity.getValue();
		this.maleBreastPlasticity = maleBreastPlasticity.getValue();
		this.maleNippleSize = maleNippleSize.getValue();
		this.maleNippleShape = maleNippleShape;
		this.maleAreolaeSize = maleAreolaeSize.getValue();
		this.maleNippleCountPerBreast = maleNippleCountPerBreast;
		
		this.breastSize = breastSize.getMeasurement();
		this.breastCountFemale = breastCountFemale;
		this.femaleLactationRate = femaleLactationRate.getMedianValue();
		this.femaleBreastCapacity = femaleBreastCapacity.getMedianValue();
		this.femaleBreastDepth = femaleBreastDepth.getValue();
		this.femaleBreastElasticity = femaleBreastElasticity.getValue();
		this.femaleBreastPlasticity = femaleBreastPlasticity.getValue();
		this.femaleNippleSize = femaleNippleSize.getValue();
		this.femaleNippleShape = femaleNippleShape;
		this.femaleAreolaeSize = femaleAreolaeSize.getValue();
		this.femaleNippleCountPerBreast = femaleNippleCountPerBreast;

		// BreastCrotchs/Crotch-boobs:
		this.breastCrotchType = breastCrotchType;
		this.breastCrotchShapes = breastCrotchShapes;
		this.breastCrotchSize = breastCrotchSize.getMeasurement();
		this.breastCrotchCount = breastCrotchCount;
		this.breastCrotchLactationRate = breastCrotchLactationRate.getMedianValue();
		this.breastCrotchCapacity = breastCrotchCapacity.getMedianValue();
		this.breastCrotchDepth = breastCrotchDepth.getValue();
		this.breastCrotchElasticity = breastCrotchElasticity.getValue();
		this.breastCrotchPlasticity = breastCrotchPlasticity.getValue();
		this.breastCrotchNippleSize = breastCrotchNippleSize.getValue();
		this.breastCrotchNippleShape = breastCrotchNippleShape;
		this.breastCrotchAreolaeSize = breastCrotchAreolaeSize.getValue();
		this.nippleCountPerBreastCrotch = nippleCountPerBreastCrotch;
		

		// Core:
		this.skinType = skinType;
		this.bodyMaterial = bodyMaterial;
		this.genitalArrangement = genitalArrangement;
		this.maleHeight = maleHeight;
		this.maleFemininity = maleFemininity;
		this.maleBodySize = maleBodySize;
		this.maleMuscle = maleMuscle;
		this.femaleHeight = femaleHeight;
		this.femaleFemininity = femaleFemininity;
		this.femaleBodySize = femaleBodySize;
		this.femaleMuscle = femaleMuscle;

		// Face:
		this.faceType = faceType;
		this.eyeType = eyeType;
		this.earType = earType;
		this.maleLipSize = maleLipSize.getValue();
		this.femaleLipSize = femaleLipSize.getValue();

		// Hair:
		this.hairType = hairType;
		this.maleHairLength = maleHairLength.getMedianValue();
		this.femaleHairLength = femaleHairLength.getMedianValue();

		// Horns:
		this.hornTypes = hornTypes;
		this.maleHornLength = maleHornLength.getMedianValue();
		this.femaleHornLength = femaleHornLength.getMedianValue();
		
		// Leg:
		this.legType = legType;
		this.legConfiguration = legConfiguration;
		
		// Penis:
		this.penisType = penisType;
		this.penisSize = penisSize;
		this.penisGirth = penisGirth.getValue();
		this.testicleSize = testicleSize.getValue();
		this.cumProduction = cumProduction.getMedianValue();

		// Tail:
		this.tailTypes = tailTypes;
		
		// Tentacle:
		this.tentacleType = tentacleType;
		
		// Vagina:
		this.vaginaType = vaginaType;
		this.clitSize = clitSize.getMedianValue();
		this.vaginaCapacity = vaginaCapacity.getMedianValue();
		this.vaginaDepth = vaginaDepth.getValue();
		this.vaginaWetness = vaginaWetness.getValue();
		this.vaginaElasticity = vaginaElasticity.getValue();
		this.vaginaPlasticity = vaginaPlasticity.getValue();
		this.testicleQuantity = testicleQuantity;

		// Wings:
		this.wingTypes = wingTypes;
		this.maleWingSize = maleWingSize.getValue();
		this.femaleWingSize = femaleWingSize.getValue();
		
	}
	
	/**
	 * <b>Does not include angels or slimes</b>
	 * @param gender
	 * @return
	 */
	public static AbstractRacialBody getRandomCommonRacialBodyFromPreferences(Gender gender) {
		
		List<AbstractRace> availableRaces = new ArrayList<>();
		for(AbstractRace r : Race.getAllRaces()) {
			if(r != Race.ANGEL) {
				availableRaces.add(r);
			}
		}
		
		if(gender.isFeminine()) {
			for(Entry<Subspecies, FurryPreference> entry : Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().entrySet()) {
				if(entry.getValue() == FurryPreference.HUMAN) {
					availableRaces.remove(entry.getKey().getRace());
				}
			}
		} else {
			for(Entry<Subspecies, FurryPreference> entry : Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().entrySet()) {
				if(entry.getValue() == FurryPreference.HUMAN) {
					availableRaces.remove(entry.getKey().getRace());
				}
			}
		}
		
		if(availableRaces.isEmpty()) {
			return RacialBody.HUMAN;
		}
		
		return RacialBody.valueOfRace(availableRaces.get(Util.random.nextInt(availableRaces.size())));
	}
	
	/**
	 * @return A map of personality traits and the percentage chance that a member of this race will spawn with them.
	 */
	public Map<PersonalityTrait, Float> getPersonalityTraitChances() {
		Map<PersonalityTrait, Float> map = new HashMap<>();
		
		for(PersonalityTrait trait : PersonalityTrait.values()) {
			if(trait.getPersonalityCategory()==PersonalityCategory.SPEECH) {
				if(trait==PersonalityTrait.MUTE) {
					map.put(trait, 0.001f); // Mute should be very rare.
				} else {
					map.put(trait, 0.01f); // Speech-related traits should be rare for a normal race.
				}
				
			} else if(trait.getPersonalityCategory()==PersonalityCategory.SEX && trait!=PersonalityTrait.LEWD) {
				map.put(trait, 0.025f); // Smaller chance for people to be prude or innocent.
					
			} else {
				map.put(trait, 0.05f); // With each category having two values, it's a ~10% chance to have a special trait in each category.
			}
		}
		
		return map;
	}
	
	public SexualOrientation getSexualOrientation(Gender gender) {
		if(gender.isFeminine()) {
			return SexualOrientationPreference.getSexualOrientationFromUserPreferences(15, 50, 35);
		} else {
			return SexualOrientationPreference.getSexualOrientationFromUserPreferences(40, 50, 10);
		}
	}
	
	public AbstractAntennaType getAntennaType() {
		return antennaType;
	}

	public AbstractArmType getArmType() {
		return armType;
	}

	public AbstractAssType getAssType() {
		return assType;
	}

	public AbstractBreastType getBreastType() {
		return breastType;
	}

	public List<BreastShape> getBreastShapes() {
		return breastShapes;
	}

	public AbstractFaceType getFaceType() {
		return faceType;
	}

	public AbstractEyeType getEyeType() {
		return eyeType;
	}

	public AbstractEarType getEarType() {
		return earType;
	}

	public AbstractHairType getHairType() {
		return hairType;
	}

	public AbstractLegType getLegType() {
		return getLegType(getLegConfiguration());
	}
	
	/**
	 * @return The default legType for this body when its LegConfiguration is the passed in configuration argument.
	 */
	public AbstractLegType getLegType(LegConfiguration configuration) {
		return legType;
	}
	
	public LegConfiguration getLegConfiguration() {
		return legConfiguration;
	}

	public AbstractTorsoType getSkinType() {
		return skinType;
	}
	
	public BodyMaterial getBodyMaterial() {
		return bodyMaterial;
	}

	/**
	 * @param includeTypeNONE Set as true if you want the returned HornType to possibly include HornType.NONE. (Will include NONE anyway if the list is empty.)
	 * @return A random HornType from this race's possible hornTypes.
	 */
	public AbstractHornType getRandomHornType(boolean includeTypeNONE) {
		List<AbstractHornType> hornList = new ArrayList<>(hornTypes);
		
		if(includeTypeNONE || hornTypes.size()==1) {
			return hornTypes.get(Util.random.nextInt(hornTypes.size()));
		} else {
			hornList.remove(HornType.NONE);
			return hornList.get(Util.random.nextInt(hornList.size()));
		}
	}
	
	public List<AbstractHornType> getHornTypes(boolean removeTypeNone) {
		if(removeTypeNone) {
			List<AbstractHornType> hornList = new ArrayList<>(hornTypes);
			hornList.remove(HornType.NONE);
			return hornList;
		}
		return hornTypes;
	}
	
	public AbstractPenisType getPenisType() {
		return penisType;
	}
	
	/**
	 * @param includeTypeNONE Set as true if you want the returned TailType to possibly include TailType.NONE. (Will include NONE anyway if the list is empty.)
	 * @return A random TailType from this race's possible tailTypes.
	 */
	public AbstractTailType getRandomTailType(boolean includeTypeNONE) {
		List<AbstractTailType> tailList = new ArrayList<>(tailTypes);
		
		if(includeTypeNONE || tailTypes.size()==1) {
			return tailTypes.get(Util.random.nextInt(tailTypes.size()));
		} else {
			tailList.remove(TailType.NONE);
			return tailList.get(Util.random.nextInt(tailList.size()));
		}
	}
	
	public List<AbstractTailType> getTailType() {
		return tailTypes;
	}

	public AbstractTentacleType getTentacleType() {
		return tentacleType;
	}
	
	public AbstractVaginaType getVaginaType() {
		return vaginaType;
	}

	/**
	 * @param includeTypeNONE Set as true if you want the returned TailType to possibly include TailType.NONE. (Will include NONE anyway if the list is empty.)
	 * @return A random TailType from this race's possible tailTypes.
	 */
	public AbstractWingType getRandomWingType(boolean includeTypeNONE) {
		List<AbstractWingType> wingList = new ArrayList<>(wingTypes);
		
		if(includeTypeNONE || wingTypes.size()==1) {
			return wingTypes.get(Util.random.nextInt(wingTypes.size()));
		} else {
			wingList.remove(WingType.NONE);
			return wingList.get(Util.random.nextInt(wingList.size()));
		}
	}
	
	public List<AbstractWingType> getWingTypes() {
		return wingTypes;
	}
	
	public int getArmRows() {
		return armRows;
	}
	
	public float getAnusCapacity() {
		return anusCapacity;
	}

	public int getAnusDepth() {
		return anusDepth;
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

	public float getFemaleBreastCapacity() {
		return femaleBreastCapacity;
	}

	public float getMaleBreastCapacity() {
		return maleBreastCapacity;
	}

	public int getFemaleBreastDepth() {
		return femaleBreastDepth;
	}
	
	public int getMaleBreastDepth() {
		return maleBreastDepth;
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

	public AbstractBreastType getBreastCrotchType() {
		return breastCrotchType;
	}

	public List<BreastShape> getBreastCrotchShapes() {
		return breastCrotchShapes;
	}


	public NippleShape getBreastCrotchNippleShape() {
		return breastCrotchNippleShape;
	}

	public int getBreastCrotchSize() {
		return breastCrotchSize;
	}

	public int getBreastCrotchLactationRate() {
		return breastCrotchLactationRate;
	}

	public float getBreastCrotchCapacity() {
		return breastCrotchCapacity;
	}

	public int getBreastCrotchDepth() {
		return breastCrotchDepth;
	}
	
	public int getBreastCrotchElasticity() {
		return breastCrotchElasticity;
	}

	public int getBreastCrotchPlasticity() {
		return breastCrotchPlasticity;
	}

	public int getNippleCountPerBreastCrotch() {
		return nippleCountPerBreastCrotch;
	}

	public int getBreastCrotchAreolaeSize() {
		return breastCrotchAreolaeSize;
	}

	public int getBreastCrotchNippleSize() {
		return breastCrotchNippleSize;
	}

	public int getBreastCrotchCount() {
		return breastCrotchCount;
	}

	public int getClitSize() {
		return clitSize;
	}

	public int getPenisSize() {
		return penisSize;
	}
	
	public int getPenisGirth() {
		return penisGirth;
	}

	public int getTesticleSize() {
		return testicleSize;
	}

	public int getCumProduction() {
		return cumProduction;
	}

	public float getVaginaCapacity() {
		return vaginaCapacity;
	}
	
	public int getVaginaDepth() {
		return vaginaDepth;
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
