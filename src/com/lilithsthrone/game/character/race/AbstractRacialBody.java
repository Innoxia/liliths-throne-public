package com.lilithsthrone.game.character.race;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
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
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTentacleType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
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
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
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
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.1
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractRacialBody {
	
	private boolean mod;
	private boolean fromExternalFile;
	
	// Antenna:
	private List<AbstractAntennaType> antennaTypes;
	private int maleAntennaLength;
	private int femaleAntennaLength;
	
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
	private AreolaeShape maleAreolaeShape;
	private AreolaeShape femaleAreolaeShape;
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
	private int breastCrotchSize;
	private int breastCrotchLactationRate;
	private float breastCrotchCapacity;
	private int breastCrotchDepth;
	private int breastCrotchElasticity;
	private int breastCrotchPlasticity;
	private int nippleCountPerBreastCrotch;
	private int breastCrotchAreolaeSize;
	private int breastCrotchNippleSize;
	private NippleShape breastCrotchNippleShape;
	private int breastCrotchCount;
	private AreolaeShape breastCrotchAreolaeShape;

	// Core:
	private AbstractTorsoType torsoType;
	private BodyMaterial bodyMaterial;
	private String bodyHairId;
	private AbstractBodyCoveringType bodyHairType;
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
	private int testicleQuantity;
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
	private int clitGirth;
	private int vaginaElasticity;
	private int vaginaPlasticity;

	// Wings:
	private List<AbstractWingType> wingTypes;
	private int maleWingSize;
	private int femaleWingSize;
	
	// External file variables:
	private Map<PersonalityTrait, Float> personalityChanceOverrides;
	
	private int orientationFeminineGynephilic;
	private int orientationFeminineAmbiphilic;
	private int orientationFeminineAndrophilic;
	private int orientationMasculineGynephilic;
	private int orientationMasculineAmbiphilic;
	private int orientationMasculineAndrophilic;
	
	public AbstractRacialBody(
			List<AbstractAntennaType> antennaTypes,
			AbstractArmType armType,
				int armRows,
			AbstractAssType assType,
				AssSize maleAssSize,
				AssSize femaleAssSize,
				Wetness anusWetness,
				Capacity anusCapacity,
				OrificeDepth anusDepth,
				OrificeElasticity anusElasticity,
				OrificePlasticity anusPlasticity,
			AbstractBreastType breastType,
				List<BreastShape> breastShapes,
			CupSize noBreastSize,
				int breastCountMale,
				Lactation maleLactationRate,
				Capacity maleBreastCapacity,
				OrificeDepth maleBreastDepth,
				OrificeElasticity maleBreastElasticity,
				OrificePlasticity maleBreastPlasticity,
				NippleSize maleNippleSize,
				NippleShape maleNippleShape,
				AreolaeSize maleAreolaeSize,
				int maleNippleCountPerBreast,
			CupSize breastSize,
				int breastCountFemale,
				Lactation femaleLactationRate,
				Capacity femaleBreastCapacity,
				OrificeDepth femaleBreastDepth,
				OrificeElasticity femaleBreastElasticity,
				OrificePlasticity femaleBreastPlasticity,
				NippleSize femaleNippleSize,
				NippleShape femaleNippleShape,
				AreolaeSize femaleAreolaeSize,
				int femaleNippleCountPerBreast,
			AbstractBreastType breastCrotchType,
				List<BreastShape> breastCrotchShapes,
			CupSize breastCrotchSize,
				int breastCrotchCount,
				Lactation breastCrotchLactationRate,
				Capacity breastCrotchCapacity,
				OrificeDepth breastCrotchDepth,
				OrificeElasticity breastCrotchElasticity,
				OrificePlasticity breastCrotchPlasticity,
				NippleSize breastCrotchNippleSize,
				NippleShape breastCrotchNippleShape,
				AreolaeSize breastCrotchAreolaeSize,
				int nippleCountPerBreastCrotch,
			int maleHeight,
				int maleFemininity,
				int maleBodySize,
				int maleMuscle,
			int femaleHeight,
				int femaleFemininity,
				int femaleBodySize,
				int femaleMuscle,
			AbstractEarType earType,
			AbstractEyeType eyeType,
			AbstractFaceType faceType,
				LipSize maleLipSize,
				LipSize femaleLipSize,
			AbstractHairType hairType,
				HairLength maleHairLength,
				HairLength femaleHairLength,
			AbstractLegType legType,
				LegConfiguration legConfiguration,
			AbstractTorsoType skinType,
			BodyMaterial bodyMaterial,
			AbstractBodyCoveringType bodyHairType,
			HornLength maleHornLength,
				HornLength femaleHornLength,
				List<AbstractHornType> hornTypes,
			AbstractPenisType penisType,
				int penisSize,
				PenetrationGirth penisGirth,
				TesticleSize testicleSize,
				int testicleQuantity,
				CumProduction cumProduction,
			List<AbstractTailType> tailTypes,
			AbstractTentacleType tentacleType,
			AbstractVaginaType vaginaType,
				Wetness vaginaWetness,
				Capacity vaginaCapacity,
				OrificeDepth vaginaDepth,
				ClitorisSize clitSize,
				OrificeElasticity vaginaElasticity,
				OrificePlasticity vaginaPlasticity,
			List<AbstractWingType> wingTypes,
				WingSize maleWingSize,
				WingSize femaleWingSize,
			GenitalArrangement genitalArrangement) {
		this.mod = false;
		this.fromExternalFile = false;
		
		// Antenna:
		this.antennaTypes = antennaTypes;
		this.maleAntennaLength = HornLength.TWO_LONG.getMedianValue();
		this.femaleAntennaLength = HornLength.TWO_LONG.getMedianValue();
		
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
		
		this.maleAreolaeShape = AreolaeShape.NORMAL;
		this.femaleAreolaeShape = AreolaeShape.NORMAL;
		
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
		this.breastCrotchAreolaeShape = AreolaeShape.NORMAL;
		this.nippleCountPerBreastCrotch = nippleCountPerBreastCrotch;
		
		// Core:
		this.torsoType = skinType;
		this.bodyMaterial = bodyMaterial;
		this.bodyHairType= bodyHairType;
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
		this.testicleQuantity = testicleQuantity;
		this.cumProduction = cumProduction.getMedianValue();

		// Tail:
		this.tailTypes = tailTypes;
		
		// Tentacle:
		this.tentacleType = tentacleType;
		
		// Vagina:
		this.vaginaType = vaginaType;
		this.clitSize = clitSize.getMedianValue();
		this.clitGirth = PenetrationGirth.THREE_AVERAGE.getValue();
		this.vaginaCapacity = vaginaCapacity.getMedianValue();
		this.vaginaDepth = vaginaDepth.getValue();
		this.vaginaWetness = vaginaWetness.getValue();
		this.vaginaElasticity = vaginaElasticity.getValue();
		this.vaginaPlasticity = vaginaPlasticity.getValue();

		// Wings:
		this.wingTypes = wingTypes;
		this.maleWingSize = maleWingSize.getValue();
		this.femaleWingSize = femaleWingSize.getValue();
	}
	
	public AbstractRacialBody(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in statusEffect files it's <statusEffect>

				this.mod = mod;
				this.fromExternalFile = true;
				
				// Core:
				this.bodyMaterial = BodyMaterial.valueOf(coreElement.getMandatoryFirstOf("bodyMaterial").getTextContent());
				this.bodyHairId = coreElement.getMandatoryFirstOf("bodyHair").getTextContent();
				this.genitalArrangement = GenitalArrangement.valueOf(coreElement.getMandatoryFirstOf("genitalArrangement").getTextContent());
				this.maleHeight = Integer.valueOf(coreElement.getMandatoryFirstOf("maleHeight").getTextContent());
				this.maleFemininity = Integer.valueOf(coreElement.getMandatoryFirstOf("maleFemininity").getTextContent());
				this.maleBodySize = Integer.valueOf(coreElement.getMandatoryFirstOf("maleBodySize").getTextContent());
				this.maleMuscle = Integer.valueOf(coreElement.getMandatoryFirstOf("maleMuscle").getTextContent());
				this.femaleHeight = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleHeight").getTextContent());
				this.femaleFemininity = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleFemininity").getTextContent());
				this.femaleBodySize = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleBodySize").getTextContent());
				this.femaleMuscle = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleMuscle").getTextContent());
				
				// Antenna:
				this.antennaTypes = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("antennaTypes").getAllOf("type")) {
					antennaTypes.add(AntennaType.getAntennaTypeFromId(e.getTextContent()));
				}
				this.maleAntennaLength = Integer.valueOf(coreElement.getMandatoryFirstOf("maleAntennaLength").getTextContent());
				this.femaleAntennaLength = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleAntennaLength").getTextContent());
				
				// Arms:
				this.armType = ArmType.getArmTypeFromId(coreElement.getMandatoryFirstOf("armType").getTextContent());
				this.armRows = Integer.valueOf(coreElement.getMandatoryFirstOf("armRows").getTextContent());
				
				// Ass:
				this.assType = AssType.getAssTypeFromId(coreElement.getMandatoryFirstOf("assType").getTextContent());
				this.anusCapacity = Float.valueOf(coreElement.getMandatoryFirstOf("anusCapacity").getTextContent());
				this.anusDepth = Integer.valueOf(coreElement.getMandatoryFirstOf("anusDepth").getTextContent());
				this.anusWetness = Integer.valueOf(coreElement.getMandatoryFirstOf("anusWetness").getTextContent());
				this.maleAssSize = Integer.valueOf(coreElement.getMandatoryFirstOf("maleAssSize").getTextContent());
				this.femaleAssSize = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleAssSize").getTextContent());
				this.anusElasticity = Integer.valueOf(coreElement.getMandatoryFirstOf("anusElasticity").getTextContent());
				this.anusPlasticity = Integer.valueOf(coreElement.getMandatoryFirstOf("anusPlasticity").getTextContent());
				
				// Breasts:
				this.breastType = BreastType.getBreastTypeFromId(coreElement.getMandatoryFirstOf("breastType").getTextContent());
				this.breastShapes = new ArrayList<>();
				String udderShapes = coreElement.getMandatoryFirstOf("breastShapes").getAttribute("udderShapes");
				if(!udderShapes.isEmpty()) {
					if(Boolean.valueOf(udderShapes)) {
						breastShapes = BreastShape.getUdderBreastShapes();
					} else {
						breastShapes = BreastShape.getNonUdderBreastShapes();
					}
				} else {
					for(Element e : coreElement.getMandatoryFirstOf("breastShapes").getAllOf("shape")) {
						breastShapes.add(BreastShape.valueOf(e.getTextContent()));
					}
				}
				
				this.noBreastSize = Integer.valueOf(coreElement.getMandatoryFirstOf("maleBreastSize").getTextContent());
				this.breastCountMale = Integer.valueOf(coreElement.getMandatoryFirstOf("maleBreastRows").getTextContent());
				this.maleLactationRate = Integer.valueOf(coreElement.getMandatoryFirstOf("maleLactationRate").getTextContent());
				this.maleBreastCapacity = Float.valueOf(coreElement.getMandatoryFirstOf("maleBreastCapacity").getTextContent());
				this.maleBreastDepth = Integer.valueOf(coreElement.getMandatoryFirstOf("maleBreastDepth").getTextContent());
				this.maleBreastElasticity = Integer.valueOf(coreElement.getMandatoryFirstOf("maleBreastElasticity").getTextContent());
				this.maleBreastPlasticity = Integer.valueOf(coreElement.getMandatoryFirstOf("maleBreastPlasticity").getTextContent());
				this.maleNippleSize = Integer.valueOf(coreElement.getMandatoryFirstOf("maleNippleSize").getTextContent());
				this.maleNippleShape = NippleShape.valueOf(coreElement.getMandatoryFirstOf("maleNippleShape").getTextContent());
				this.maleAreolaeSize = Integer.valueOf(coreElement.getMandatoryFirstOf("maleAreolaeSize").getTextContent());
				this.maleAreolaeShape = AreolaeShape.valueOf(coreElement.getMandatoryFirstOf("maleAreolaeShape").getTextContent());
				this.maleNippleCountPerBreast = Integer.valueOf(coreElement.getMandatoryFirstOf("maleNippleCountPerBreast").getTextContent());
				
				this.breastSize = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleBreastSize").getTextContent());
				this.breastCountFemale = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleBreastRows").getTextContent());
				this.femaleLactationRate = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleLactationRate").getTextContent());
				this.femaleBreastCapacity = Float.valueOf(coreElement.getMandatoryFirstOf("femaleBreastCapacity").getTextContent());
				this.femaleBreastDepth = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleBreastDepth").getTextContent());
				this.femaleBreastElasticity = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleBreastElasticity").getTextContent());
				this.femaleBreastPlasticity = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleBreastPlasticity").getTextContent());
				this.femaleNippleSize = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleNippleSize").getTextContent());
				this.femaleNippleShape = NippleShape.valueOf(coreElement.getMandatoryFirstOf("femaleNippleShape").getTextContent());
				this.femaleAreolaeSize = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleAreolaeSize").getTextContent());
				this.femaleAreolaeShape = AreolaeShape.valueOf(coreElement.getMandatoryFirstOf("femaleAreolaeShape").getTextContent());
				this.femaleNippleCountPerBreast = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleNippleCountPerBreast").getTextContent());

				// BreastCrotchs/Crotch-boobs:
				this.breastCrotchType = BreastType.getBreastTypeFromId(coreElement.getMandatoryFirstOf("breastCrotchType").getTextContent());
				this.breastCrotchShapes = new ArrayList<>();
				udderShapes = coreElement.getMandatoryFirstOf("breastCrotchShapes").getAttribute("udderShapes");
				if(!udderShapes.isEmpty()) {
					if(Boolean.valueOf(udderShapes)) {
						breastCrotchShapes = BreastShape.getUdderBreastShapes();
					} else {
						breastCrotchShapes = BreastShape.getNonUdderBreastShapes();
					}
				} else {
					for(Element e : coreElement.getMandatoryFirstOf("breastCrotchShapes").getAllOf("shape")) {
						breastCrotchShapes.add(BreastShape.valueOf(e.getTextContent()));
					}
				}
				
				this.breastCrotchSize = Integer.valueOf(coreElement.getMandatoryFirstOf("breastCrotchSize").getTextContent());
				this.breastCrotchCount = Integer.valueOf(coreElement.getMandatoryFirstOf("breastCrotchRows").getTextContent());
				this.breastCrotchLactationRate = Integer.valueOf(coreElement.getMandatoryFirstOf("breastCrotchLactationRate").getTextContent());
				this.breastCrotchCapacity = Float.valueOf(coreElement.getMandatoryFirstOf("breastCrotchCapacity").getTextContent());
				this.breastCrotchDepth = Integer.valueOf(coreElement.getMandatoryFirstOf("breastCrotchDepth").getTextContent());
				this.breastCrotchElasticity = Integer.valueOf(coreElement.getMandatoryFirstOf("breastCrotchElasticity").getTextContent());
				this.breastCrotchPlasticity = Integer.valueOf(coreElement.getMandatoryFirstOf("breastCrotchPlasticity").getTextContent());
				this.breastCrotchNippleSize = Integer.valueOf(coreElement.getMandatoryFirstOf("breastCrotchNippleSize").getTextContent());
				this.breastCrotchNippleShape = NippleShape.valueOf(coreElement.getMandatoryFirstOf("breastCrotchNippleShape").getTextContent());
				this.breastCrotchAreolaeSize = Integer.valueOf(coreElement.getMandatoryFirstOf("breastCrotchAreolaeSize").getTextContent());
				this.breastCrotchAreolaeShape = AreolaeShape.valueOf(coreElement.getMandatoryFirstOf("breastCrotchAreolaeShape").getTextContent());
				this.nippleCountPerBreastCrotch = Integer.valueOf(coreElement.getMandatoryFirstOf("nippleCountPerBreastCrotch").getTextContent());

				// Face:
				this.faceType = FaceType.getFaceTypeFromId(coreElement.getMandatoryFirstOf("faceType").getTextContent());
				this.eyeType = EyeType.getEyeTypeFromId(coreElement.getMandatoryFirstOf("eyeType").getTextContent());
				this.earType = EarType.getEarTypeFromId(coreElement.getMandatoryFirstOf("earType").getTextContent());
				this.maleLipSize = Integer.valueOf(coreElement.getMandatoryFirstOf("maleLipSize").getTextContent());
				this.femaleLipSize = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleLipSize").getTextContent());

				// Hair:
				this.hairType = HairType.getHairTypeFromId(coreElement.getMandatoryFirstOf("hairType").getTextContent());
				this.maleHairLength = Integer.valueOf(coreElement.getMandatoryFirstOf("maleHairLength").getTextContent());
				this.femaleHairLength = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleHairLength").getTextContent());

				// Horns:
				this.hornTypes = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("hornTypes").getAllOf("type")) {
					hornTypes.add(HornType.getHornTypeFromId(e.getTextContent()));
				}
				this.maleHornLength = Integer.valueOf(coreElement.getMandatoryFirstOf("maleHornLength").getTextContent());
				this.femaleHornLength = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleHornLength").getTextContent());
				
				// Leg:
				this.legType = LegType.getLegTypeFromId(coreElement.getMandatoryFirstOf("legType").getTextContent());
				this.legConfiguration = LegConfiguration.valueOf(coreElement.getMandatoryFirstOf("legConfiguration").getTextContent());
				
				// Penis:
				this.penisType = PenisType.getPenisTypeFromId(coreElement.getMandatoryFirstOf("penisType").getTextContent());
				this.penisSize = Integer.valueOf(coreElement.getMandatoryFirstOf("penisLength").getTextContent());
				this.penisGirth = Integer.valueOf(coreElement.getMandatoryFirstOf("penisGirth").getTextContent());
				this.testicleSize = Integer.valueOf(coreElement.getMandatoryFirstOf("testicleSize").getTextContent());
				this.testicleQuantity = Integer.valueOf(coreElement.getMandatoryFirstOf("testicleQuantity").getTextContent());
				this.cumProduction = Integer.valueOf(coreElement.getMandatoryFirstOf("cumProduction").getTextContent());

				// Tail:
				this.tailTypes = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("tailTypes").getAllOf("type")) {
					tailTypes.add(TailType.getTailTypeFromId(e.getTextContent()));
				}
				
				// Tentacle:
				this.tentacleType = TentacleType.getTentacleTypeFromId(coreElement.getMandatoryFirstOf("tentacleType").getTextContent());

				// Torso:
				this.torsoType = TorsoType.getTorsoTypeFromId(coreElement.getMandatoryFirstOf("torsoType").getTextContent());
				
				// Vagina:
				this.vaginaType = VaginaType.getVaginaTypeFromId(coreElement.getMandatoryFirstOf("vaginaType").getTextContent());
				this.clitSize = Integer.valueOf(coreElement.getMandatoryFirstOf("clitSize").getTextContent());
				this.clitGirth = Integer.valueOf(coreElement.getMandatoryFirstOf("clitGirth").getTextContent());
				this.vaginaCapacity = Float.valueOf(coreElement.getMandatoryFirstOf("vaginaCapacity").getTextContent());
				this.vaginaDepth = Integer.valueOf(coreElement.getMandatoryFirstOf("vaginaDepth").getTextContent());
				this.vaginaWetness = Integer.valueOf(coreElement.getMandatoryFirstOf("vaginaWetness").getTextContent());
				this.vaginaElasticity = Integer.valueOf(coreElement.getMandatoryFirstOf("vaginaElasticity").getTextContent());
				this.vaginaPlasticity = Integer.valueOf(coreElement.getMandatoryFirstOf("vaginaPlasticity").getTextContent());

				// Wings:
				this.wingTypes = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("wingTypes").getAllOf("type")) {
					wingTypes.add(WingType.getWingTypeFromId(e.getTextContent()));
				}
				this.maleWingSize = Integer.valueOf(coreElement.getMandatoryFirstOf("maleWingSize").getTextContent());
				this.femaleWingSize = Integer.valueOf(coreElement.getMandatoryFirstOf("femaleWingSize").getTextContent());
				
				// Personalities:
				personalityChanceOverrides = new HashMap<>();
				if(coreElement.getOptionalFirstOf("personalityChances").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("personalityChances").getAllOf("entry")) {
						try {
							personalityChanceOverrides.put(PersonalityTrait.valueOf(e.getTextContent()), Float.valueOf(e.getAttribute("chance")));
						} catch(Exception ex) {
							System.err.println("AbstractRacialBody error: PersonalityTrait '"+e.getTextContent()+"' failed to load!");
						}
					}
				}
				
				// Orientations:
				orientationFeminineGynephilic = Integer.valueOf(coreElement.getMandatoryFirstOf("orientationFeminineGynephilic").getTextContent());
				orientationFeminineAmbiphilic = Integer.valueOf(coreElement.getMandatoryFirstOf("orientationFeminineAmbiphilic").getTextContent());
				orientationFeminineAndrophilic = Integer.valueOf(coreElement.getMandatoryFirstOf("orientationFeminineAndrophilic").getTextContent());
				orientationMasculineGynephilic = Integer.valueOf(coreElement.getMandatoryFirstOf("orientationMasculineGynephilic").getTextContent());
				orientationMasculineAmbiphilic = Integer.valueOf(coreElement.getMandatoryFirstOf("orientationMasculineAmbiphilic").getTextContent());
				orientationMasculineAndrophilic = Integer.valueOf(coreElement.getMandatoryFirstOf("orientationMasculineAndrophilic").getTextContent());
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractRacialBody was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
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
		
		if(this.fromExternalFile) {
			for(Entry<PersonalityTrait, Float> entry : personalityChanceOverrides.entrySet()) {
				map.put(entry.getKey(), entry.getValue());
			}
		}
		
		return map;
	}
	
	public SexualOrientation getSexualOrientation(Gender gender) {
		if(this.fromExternalFile) {
			if(gender.isFeminine()) {
				return SexualOrientationPreference.getSexualOrientationFromUserPreferences(orientationFeminineGynephilic, orientationFeminineAmbiphilic, orientationFeminineAndrophilic);
			} else {
				return SexualOrientationPreference.getSexualOrientationFromUserPreferences(orientationMasculineGynephilic, orientationMasculineAmbiphilic, orientationMasculineAndrophilic);
			}
			
		} else {
			if(gender.isFeminine()) {
				return SexualOrientationPreference.getSexualOrientationFromUserPreferences(20, 40, 40);
			} else {
				return SexualOrientationPreference.getSexualOrientationFromUserPreferences(60, 30, 10);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}
	
	public boolean isFromExternalFile() {
		return fromExternalFile;
	}
	
	/**
	 * @param includeTypeNONE Set as true if you want the returned AntennaType to possibly include AntennaType.NONE. (Will include NONE anyway if the list is empty.)
	 * @return A random AntennaType from this race's possible antennaTypes.
	 */
	public AbstractAntennaType getRandomrAntennaType(boolean includeTypeNONE) {
		List<AbstractAntennaType> antennaList = new ArrayList<>(antennaTypes);
		
		if(includeTypeNONE || antennaTypes.size()==1) {
			return antennaTypes.get(Util.random.nextInt(antennaTypes.size()));
		} else {
			antennaList.remove(AntennaType.NONE);
			return antennaList.get(Util.random.nextInt(antennaList.size()));
		}
	}
	
	public List<AbstractAntennaType> getAntennaTypes(boolean removeTypeNone) {
		if(removeTypeNone) {
			List<AbstractAntennaType> antennaList = new ArrayList<>(antennaTypes);
			antennaList.remove(AntennaType.NONE);
			return antennaList;
		}
		return antennaTypes;
	}

	public int getMaleAntennaLength() {
		return maleAntennaLength;
	}

	public int getFemaleAntennaLength() {
		return femaleAntennaLength;
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

	public AbstractTorsoType getTorsoType() {
		return torsoType;
	}
	
	public BodyMaterial getBodyMaterial() {
		return bodyMaterial;
	}
	
	public AbstractBodyCoveringType getBodyHairType() {
		if(this.isFromExternalFile()) {
			return BodyCoveringType.getBodyCoveringTypeFromId(bodyHairId);
		} else {
			return bodyHairType;
		}
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

	public AreolaeShape getMaleAreolaeShape() {
		return maleAreolaeShape;
	}

	public AreolaeShape getFemaleAreolaeShape() {
		return femaleAreolaeShape;
	}

	public AbstractBreastType getBreastCrotchType() {
		return breastCrotchType;
	}

	public List<BreastShape> getBreastCrotchShapes() {
		return breastCrotchShapes;
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

	public int getBreastCrotchNippleSize() {
		return breastCrotchNippleSize;
	}

	public NippleShape getBreastCrotchNippleShape() {
		return breastCrotchNippleShape;
	}

	public int getBreastCrotchAreolaeSize() {
		return breastCrotchAreolaeSize;
	}

	public AreolaeShape getBreastCrotchAreolaeShape() {
		return breastCrotchAreolaeShape;
	}

	public int getBreastCrotchCount() {
		return breastCrotchCount;
	}

	public int getClitSize() {
		return clitSize;
	}

	public int getClitGirth() {
		return clitGirth;
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
