package com.lilithsthrone.game.character;

import java.io.File;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLLoadException;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Antenna;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.Ass;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.Breast;
import com.lilithsthrone.game.character.body.BreastCrotch;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.Ear;
import com.lilithsthrone.game.character.body.Eye;
import com.lilithsthrone.game.character.body.Face;
import com.lilithsthrone.game.character.body.Hair;
import com.lilithsthrone.game.character.body.Horn;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.Skin;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.Wing;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
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
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.character.npc.dominion.DominionSuccubusAttacker;
import com.lilithsthrone.game.character.npc.misc.GenericAndrogynousNPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractRacialBody;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.AbstractOutfit;
import com.lilithsthrone.game.inventory.clothing.BlockedParts;
import com.lilithsthrone.game.inventory.clothing.BodyPartClothingBlock;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.OutfitType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.ColourListPresets;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.67
 * @version 0.3.2
 * @author Innoxia, tukaima
 */
public class CharacterUtils {
	
	public static void saveCharacterAsXML(PlayerCharacter character){
		try {
//			long timeStart = System.nanoTime();
//			System.out.println(timeStart);
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			
			Element properties = doc.createElement("playerCharacter");
			doc.appendChild(properties);

			// Modify birth date so that imported characters are the same age:
			character.setBirthday(character.getBirthday().plusYears(Game.TIME_SKIP_YEARS));
			character.saveAsXML(properties, doc);
			character.setBirthday(character.getBirthday().minusYears(Game.TIME_SKIP_YEARS));
			
//			System.out.println("Difference2: "+(System.nanoTime()-timeStart)/1000000000f);
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer1 = tf.newTransformer();
			transformer1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();

//			System.out.println("Difference3: "+(System.nanoTime()-timeStart)/1000000000f);
			
			transformer1.transform(new DOMSource(doc), new StreamResult(writer));
			
//			System.out.println("Difference4: "+(System.nanoTime()-timeStart)/1000000000f);
			
//			String output = writer.getBuffer().toString();
//			System.out.println("Difference: "+(System.nanoTime()-timeStart)/1000000000f);
//			System.out.println(output);
			
			// Save this xml:
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			
			File dir = new File("data/");
			dir.mkdir();
			
			File dirCharacter = new File("data/characters/");
			dirCharacter.mkdir();
			
			int saveNumber = 0;
			String saveLocation = "data/characters/"+character.getName(false)+"_day"+Main.game.getDayNumber()+".xml";
			if(new File("data/characters/"+character.getName(false)+"_day"+Main.game.getDayNumber()+".xml").exists())
				saveLocation = "data/characters/"+character.getName(false)+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml";

			while(new File("data/characters/"+character.getName(false)+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml").exists()) {
				saveNumber++;
				saveLocation = "data/characters/"+character.getName(false)+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml";
			}
			
			StreamResult result = new StreamResult(saveLocation);
			
			transformer.transform(source, result);
		
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void createXMLElementWithValue(Document doc, Element parentElement, String elementName, String value){
		Element element = doc.createElement(elementName);
		parentElement.appendChild(element);
		Attr attr = doc.createAttribute("value");
		attr.setValue(value);
		element.setAttributeNode(attr);
	}
	
	public static void addAttribute(Document doc, Element parentElement, String attributeName, String value){
		Attr attr = doc.createAttribute(attributeName);
		attr.setValue(value);
		parentElement.setAttributeNode(attr);
	}
	
	private static StringBuilder characterImportLog;
	public static String getCharacterImportLog() {
		return characterImportLog.toString();
	}
	
	public static void appendToImportLog(StringBuilder log, String message) {
		if(log!=null) {
			log.append(message);
		}
	}
	
	public static PlayerCharacter startLoadingCharacterFromXML(){
		return new PlayerCharacter(new NameTriplet("Player"), 1, null, Gender.M_P_MALE, Subspecies.HUMAN, RaceStage.HUMAN, WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME);
	}
	
	public static PlayerCharacter loadCharacterFromXML(File xmlFile, PlayerCharacter importedCharacter, CharacterImportSetting... settings){
		
		characterImportLog = new StringBuilder();
		
		if (xmlFile.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(xmlFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				if(doc.getElementsByTagName("playerCharacter").item(0) == null) { // Support for older versions:
					importedCharacter = PlayerCharacter.loadFromXML(characterImportLog, ((Element) doc.getElementsByTagName("character").item(0)), doc, settings);
				} else {
					importedCharacter = PlayerCharacter.loadFromXML(characterImportLog, (Element) ((Element) doc.getElementsByTagName("playerCharacter").item(0)).getElementsByTagName("character").item(0), doc, settings);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return importedCharacter;
	}
	
	public static RaceStage getRaceStageFromPreferences(FurryPreference preference, Gender gender, Subspecies species) {

		RaceStage raceStage = RaceStage.PARTIAL;
		
		switch(preference) {
			case HUMAN:
				return RaceStage.HUMAN;
			case MINIMUM:
				return RaceStage.PARTIAL;
			case REDUCED:
				return Util.randomItemFrom(Util.newArrayListOfValues(RaceStage.PARTIAL, RaceStage.LESSER));
			case NORMAL:
				return Util.randomItemFrom(Util.newArrayListOfValues(RaceStage.PARTIAL, RaceStage.LESSER, RaceStage.GREATER));
			case MAXIMUM:
				return RaceStage.GREATER;
		}
		
		return raceStage;
	}
	
	public static Body generateBody(GameCharacter linkedCharacter, Gender startingGender, GameCharacter mother, GameCharacter father) {
		
		Body body = Subspecies.getPreGeneratedBody(linkedCharacter, startingGender, mother, father);

		boolean takesAfterMother = true;
		GameCharacter parentTakesAfter = mother;
		boolean raceFromMother = true;
		AbstractRacialBody motherBody = RacialBody.valueOfRace(Subspecies.getOffspringFromMotherSubspecies(mother, father).getRace());
		AbstractRacialBody fatherBody = RacialBody.valueOfRace(Subspecies.getOffspringFromFatherSubspecies(mother, father).getRace());
		Subspecies raceTakesAfter = mother.getSubspecies();
		boolean feminineGender = startingGender.isFeminine();
		NPC blankNPC = Main.game.getNpc(GenericAndrogynousNPC.class);
		
		if(body==null) {
			AbstractRacialBody startingBodyType = RacialBody.HUMAN;
			RaceStage stage = RaceStage.HUMAN;
			
			// Core body type is random:
			if(Math.random()<=0.5) {
				startingBodyType = motherBody;
				stage = mother.getRaceStage();
				
			} else {
				startingBodyType = fatherBody;
				stage = father.getRaceStage();
				raceTakesAfter = father.getSubspecies();
				takesAfterMother = false;
				raceFromMother = false;
			}
			
			switch(startingGender.isFeminine()
					?Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(raceTakesAfter)
					:Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(raceTakesAfter)) {
				case HUMAN:
					stage = RaceStage.HUMAN;
					break;
				case MINIMUM:
					if(stage!=RaceStage.HUMAN
					|| stage!=RaceStage.PARTIAL) {
						stage = RaceStage.PARTIAL;
					}
					break;
				case REDUCED:
					if(stage!=RaceStage.HUMAN
						|| stage!=RaceStage.PARTIAL
						|| stage!=RaceStage.LESSER) {
						stage = RaceStage.LESSER;
					}
					break;
				case NORMAL:
					break;
				case MAXIMUM:
					stage = RaceStage.GREATER;
					break;
			}
			
			body = generateBody(linkedCharacter, startingGender, startingBodyType, stage);
		}
		
		body.setBodyMaterial(mother.getBodyMaterial());
		
		// Genetics! (Sort of...)
		
		// Takes other features from the parent closest to their femininity:
		if(Math.abs(mother.getFemininityValue()-body.getFemininity()) > Math.abs(father.getFemininityValue()-body.getFemininity())) {
			takesAfterMother = false;
			parentTakesAfter = father;
		}
		
		// Non-biped parents:
		if(takesAfterMother) {
			if(body.getLeg().getType().isLegConfigurationAvailable(mother.getLegConfiguration())) {
				body.getLeg().setLegConfigurationForced(body.getLeg().getType(), mother.getLegConfiguration());
			}
		} else {
			if(body.getLeg().getType().isLegConfigurationAvailable(father.getLegConfiguration())) {
				body.getLeg().setLegConfigurationForced(body.getLeg().getType(), father.getLegConfiguration());
			}
		}
		
		float takesAfterMotherChance = takesAfterMother?0.75f:0.25f;
		
		List<BodyCoveringType> typesToInfluence = new ArrayList<>();
		// Skin & fur colours:
		for(BodyPartInterface bp : body.getAllBodyParts()){
			if(bp.getBodyCoveringType(body)!=null
					&& !(bp instanceof Eye)) {
				typesToInfluence.add(bp.getBodyCoveringType(body));
			}
		}
		typesToInfluence.add(BodyCoveringType.ANUS);
		typesToInfluence.add(BodyCoveringType.NIPPLES);
		typesToInfluence.add(BodyCoveringType.MOUTH);
		typesToInfluence.add(BodyCoveringType.TONGUE);
		
		if(raceFromMother) {
			typesToInfluence = setCoveringColours(body, mother, typesToInfluence);
			setCoveringColours(body, father, typesToInfluence);
		} else {
			typesToInfluence = setCoveringColours(body, father, typesToInfluence);
			setCoveringColours(body, mother, typesToInfluence);
		}
		
		body.updateCoverings(false, false, true, false);
		
		
		// Iris colour:
		if(Math.random()<=0.9f) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getCoverings().put(body.getEye().getBodyCoveringType(body),
						new Covering(body.getEye().getBodyCoveringType(body), mother.getCovering(mother.getEyeCovering()).getPattern(),
								mother.getCovering(mother.getEyeCovering()).getPrimaryColour(), mother.getCovering(mother.getEyeCovering()).isPrimaryGlowing(),
								mother.getCovering(mother.getEyeCovering()).getPrimaryColour(), mother.getCovering(mother.getEyeCovering()).isPrimaryGlowing()));
			} else {
				body.getCoverings().put(body.getEye().getBodyCoveringType(body),
						new Covering(body.getEye().getBodyCoveringType(body), father.getCovering(father.getEyeCovering()).getPattern(),
								father.getCovering(father.getEyeCovering()).getPrimaryColour(), father.getCovering(father.getEyeCovering()).isPrimaryGlowing(),
								father.getCovering(father.getEyeCovering()).getPrimaryColour(), father.getCovering(father.getEyeCovering()).isPrimaryGlowing()));
			}
		}
		// Pupil colour:
		if(Math.random()<=0.5f) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getCoverings().put(BodyCoveringType.EYE_PUPILS,
						new Covering(body.getEye().getBodyCoveringType(body), mother.getCovering(BodyCoveringType.EYE_PUPILS).getPattern(),
								mother.getCovering(BodyCoveringType.EYE_PUPILS).getPrimaryColour(), mother.getCovering(BodyCoveringType.EYE_PUPILS).isPrimaryGlowing(),
								mother.getCovering(BodyCoveringType.EYE_PUPILS).getPrimaryColour(), mother.getCovering(BodyCoveringType.EYE_PUPILS).isPrimaryGlowing()));
			} else {
				body.getCoverings().put(BodyCoveringType.EYE_PUPILS,
						new Covering(body.getEye().getBodyCoveringType(body), father.getCovering(BodyCoveringType.EYE_PUPILS).getPattern(),
								father.getCovering(BodyCoveringType.EYE_PUPILS).getPrimaryColour(), father.getCovering(BodyCoveringType.EYE_PUPILS).isPrimaryGlowing(),
								father.getCovering(BodyCoveringType.EYE_PUPILS).getPrimaryColour(), father.getCovering(BodyCoveringType.EYE_PUPILS).isPrimaryGlowing()));
			}
		}
		// Sclera colour:
		if(Math.random()<=0.5f) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getCoverings().put(BodyCoveringType.EYE_SCLERA,
						new Covering(body.getEye().getBodyCoveringType(body), mother.getCovering(BodyCoveringType.EYE_SCLERA).getPattern(),
								mother.getCovering(BodyCoveringType.EYE_SCLERA).getPrimaryColour(), mother.getCovering(BodyCoveringType.EYE_SCLERA).isPrimaryGlowing(),
								mother.getCovering(BodyCoveringType.EYE_SCLERA).getPrimaryColour(), mother.getCovering(BodyCoveringType.EYE_SCLERA).isPrimaryGlowing()));
			} else {
				body.getCoverings().put(BodyCoveringType.EYE_SCLERA,
						new Covering(body.getEye().getBodyCoveringType(body), father.getCovering(BodyCoveringType.EYE_SCLERA).getPattern(),
								father.getCovering(BodyCoveringType.EYE_SCLERA).getPrimaryColour(), father.getCovering(BodyCoveringType.EYE_SCLERA).isPrimaryGlowing(),
								father.getCovering(BodyCoveringType.EYE_SCLERA).getPrimaryColour(), father.getCovering(BodyCoveringType.EYE_SCLERA).isPrimaryGlowing()));
			}
		}
		
		// Body core:
		// Height:
		if(body.getHeightValue()>=Height.ZERO_TINY.getMinimumValue()) {
//			System.out.println("height adjusted");
			body.setHeight(getSizeFromGenetics( //TODO check this
					body.getHeightValue(),
					(body.isFeminine()?mother.isFeminine():!mother.isFeminine()), mother.getHeightValue(),
					(body.isFeminine()?father.isFeminine():!father.isFeminine()), father.getHeightValue()));
		}
		
		// Femininity:
		switch(startingGender.getType()) {
			case FEMININE:
				if(takesAfterMother) {
					if(mother.getFemininityValue()>=Femininity.FEMININE.getMinimumFemininity()) {
						body.setFemininity(mother.getFemininityValue());
					}
				} else {
					if(father.getFemininityValue()>=Femininity.FEMININE.getMinimumFemininity()) {
						body.setFemininity(father.getFemininityValue());
					}
				}
				break;
			case NEUTRAL:
				if(takesAfterMother) {
					if(mother.getFemininity()==Femininity.ANDROGYNOUS) {
						body.setFemininity(mother.getFemininityValue());
					}
				} else {
					if(father.getFemininity()==Femininity.ANDROGYNOUS) {
						body.setFemininity(father.getFemininityValue());
					}
				}
				break;
			case MASCULINE:
				if(takesAfterMother) {
					if(mother.getFemininityValue()<Femininity.ANDROGYNOUS.getMinimumFemininity()) {
						body.setFemininity(mother.getFemininityValue());
					}
				} else {
					if(father.getFemininityValue()<Femininity.ANDROGYNOUS.getMinimumFemininity()) {
						body.setFemininity(father.getFemininityValue());
					}
				}
				break;
		}
		
		// Body size:
		int minimumSize = Math.min(mother.getBodySizeValue(), father.getBodySizeValue()) - Util.random.nextInt(5);
		int maximumSize = Math.min(mother.getBodySizeValue(), father.getBodySizeValue()) + Util.random.nextInt(5);
		if(takesAfterMother) {
			minimumSize = Math.max(minimumSize, (feminineGender?motherBody.getFemaleBodySize()-30:motherBody.getMaleBodySize()-30));
			maximumSize = Math.max(maximumSize, (feminineGender?motherBody.getFemaleBodySize()+30:motherBody.getMaleBodySize()+30));
		} else {
			minimumSize = Math.max(minimumSize, (feminineGender?fatherBody.getFemaleBodySize()-30:fatherBody.getMaleBodySize()-30));
			maximumSize = Math.max(maximumSize, (feminineGender?fatherBody.getFemaleBodySize()+30:fatherBody.getMaleBodySize()+30));
		}
		int variance = (maximumSize==minimumSize?0:Util.random.nextInt(maximumSize-minimumSize));
		body.setBodySize(minimumSize + variance);
		
		// Muscle:
		int minimumMuscle = Math.min(mother.getMuscleValue(), father.getMuscleValue()) - Util.random.nextInt(5);
		int maximumMuscle = Math.min(mother.getMuscleValue(), father.getMuscleValue()) + Util.random.nextInt(5);
		if(takesAfterMother) {
			minimumMuscle = Math.max(minimumMuscle, (feminineGender?motherBody.getFemaleMuscle()-30:motherBody.getMaleMuscle()-30));
			maximumMuscle = Math.max(maximumMuscle, (feminineGender?motherBody.getFemaleMuscle()+30:motherBody.getMaleMuscle()+30));
		} else {
			minimumMuscle = Math.max(minimumMuscle, (feminineGender?fatherBody.getFemaleMuscle()-30:fatherBody.getMaleMuscle()-30));
			maximumMuscle = Math.max(maximumMuscle, (feminineGender?fatherBody.getFemaleMuscle()+30:fatherBody.getMaleMuscle()+30));
		}
		variance = (maximumMuscle==minimumMuscle?0:Util.random.nextInt(maximumMuscle-minimumMuscle));
		body.setMuscle(minimumMuscle + variance);
		
		
		// Body parts:
		
		boolean inheritsFromMotherFemininity = mother.isFeminine() == body.isFeminine();
		boolean inheritsFromFatherFemininity = father.isFeminine() == body.isFeminine();
		
		// Arm:
		if(Math.random()>0.75) {
			body.getArm().setArmRows(blankNPC, parentTakesAfter.getArmRows());
		}
		
		// Ass:
		// Ass size:
		body.getAss().setAssSize(blankNPC, getSizeFromGenetics(
				body.getAss().getAssSize().getValue(),
				inheritsFromMotherFemininity, mother.getAssSize().getValue(),
				inheritsFromFatherFemininity, father.getAssSize().getValue()));
		// Hip size:
		body.getAss().setHipSize(blankNPC, getSizeFromGenetics(
				body.getAss().getHipSize().getValue(),
				inheritsFromMotherFemininity, mother.getHipSize().getValue(),
				inheritsFromFatherFemininity, father.getHipSize().getValue()));
		
		
		// Breasts:
		boolean inheritsFromMotherBreasts = mother.hasBreasts();
		boolean inheritsFromFatherBreasts = father.hasBreasts();
		if(body.getBreast().getRawSizeValue()>0) {
			// Breast shape:
			if(Math.random()>=0.8f) {
				if(inheritsFromMotherBreasts && inheritsFromFatherBreasts) {
					if(Math.random()>=takesAfterMotherChance) {
						body.getBreast().setShape(blankNPC, mother.getBreastShape());
					} else {
						body.getBreast().setShape(blankNPC, father.getBreastShape());
					}
				} else if(inheritsFromMotherBreasts) {
					body.getBreast().setShape(blankNPC, mother.getBreastShape());
				} else if(inheritsFromFatherBreasts) {
					body.getBreast().setShape(blankNPC, father.getBreastShape());
				}
			}
			// Breast size:
			body.getBreast().setSize(blankNPC, getSizeFromGenetics(
					body.getBreast().getSize().getMeasurement(),
					inheritsFromMotherBreasts, mother.getBreastSize().getMeasurement(),
					inheritsFromFatherBreasts, father.getBreastSize().getMeasurement()));
			// Breast rows:
			if(Math.random()>=0.75) {
				if(Math.random()>=takesAfterMotherChance) {
					body.getBreast().setRows(blankNPC, mother.getBreastRows());
				} else {
					body.getBreast().setRows(blankNPC, father.getBreastRows());
				}
			}
			// Modifiers:
			for(OrificeModifier om : OrificeModifier.values()) {
				if(Math.random()>=0.5) {
					if(inheritsFromMotherBreasts && inheritsFromFatherBreasts) {
						if(Math.random()>=takesAfterMotherChance) {
							if(mother.hasNippleOrificeModifier(om)) {
								body.getBreast().getNipples().getOrificeNipples().addOrificeModifier(blankNPC, om);
							}
						} else {
							if(father.hasNippleOrificeModifier(om)) {
								body.getBreast().getNipples().getOrificeNipples().addOrificeModifier(blankNPC, om);
							}
						}
					} else if(inheritsFromMotherBreasts) {
						if(mother.hasNippleOrificeModifier(om)) {
							body.getBreast().getNipples().getOrificeNipples().addOrificeModifier(blankNPC, om);
						}
					} else if(inheritsFromFatherBreasts) {
						if(father.hasNippleOrificeModifier(om)) {
							body.getBreast().getNipples().getOrificeNipples().addOrificeModifier(blankNPC, om);
						}
					}
				}
			}
		}
		// Nipple count:
		if(Math.random()>0.75f) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getBreast().setNippleCountPerBreast(blankNPC, mother.getNippleCountPerBreast());
			} else {
				body.getBreast().setNippleCountPerBreast(blankNPC, father.getNippleCountPerBreast());
			}
		}
		// Nipple shape:
		if(Math.random()>=0.75f) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getBreast().getNipples().setNippleShape(blankNPC, mother.getNippleShape());
			} else {
				body.getBreast().getNipples().setNippleShape(blankNPC, father.getNippleShape());
			}
		}
		// Areolae shape:
		if(Math.random()>=0.75f) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getBreast().getNipples().setAreolaeShape(blankNPC, mother.getAreolaeShape());
			} else {
				body.getBreast().getNipples().setAreolaeShape(blankNPC, father.getAreolaeShape());
			}
		}
		// Nipple size:
		body.getBreast().getNipples().setNippleSize(blankNPC, getSizeFromGenetics(
				body.getBreast().getNipples().getNippleSizeValue(),
				inheritsFromMotherBreasts, mother.getNippleSize().getValue(),
				inheritsFromFatherBreasts, father.getNippleSize().getValue()));
		// Areolae size:
		body.getBreast().getNipples().setAreolaeSize(blankNPC, getSizeFromGenetics(
				body.getBreast().getNipples().getAreolaeSizeValue(),
				inheritsFromMotherBreasts, mother.getAreolaeSize().getValue(),
				inheritsFromFatherBreasts, father.getAreolaeSize().getValue()));
		
		// Face:
		// Lip size:
		body.getFace().getMouth().setLipSize(blankNPC, getSizeFromGenetics(
				body.getFace().getMouth().getLipSizeValue(),
				inheritsFromMotherFemininity, mother.getLipSizeValue(),
				inheritsFromFatherFemininity, father.getLipSizeValue()));
		// Mouth modifiers:
		for(OrificeModifier om : OrificeModifier.values()) {
			if(Math.random()>=0.5) {
				if(Math.random()>=takesAfterMotherChance) {
					if(mother.hasFaceOrificeModifier(om)) {
						body.getFace().getMouth().getOrificeMouth().addOrificeModifier(blankNPC, om);
					}
				} else {
					if(father.hasFaceOrificeModifier(om)) {
						body.getFace().getMouth().getOrificeMouth().addOrificeModifier(blankNPC, om);
					}
				}
			}
		}
		// Tongue modifiers:
		for(TongueModifier tm : TongueModifier.values()) {
			if(Math.random()>=0.5) {
				if(Math.random()>=takesAfterMotherChance) {
					if(mother.hasTongueModifier(tm)) {
						body.getFace().getTongue().addTongueModifier(blankNPC, tm);
					}
				} else {
					if(father.hasTongueModifier(tm)) {
						body.getFace().getTongue().addTongueModifier(blankNPC, tm);
					}
				}
			}
		}
		
		// Eyes
		// Eye pairs:
		if(Math.random()>=0.75) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getEye().setEyePairs(blankNPC, mother.getEyePairs());
			} else {
				body.getEye().setEyePairs(blankNPC, father.getEyePairs());
			}
		}
		// Iris shape:
		if(Math.random()>=0.75) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getEye().setIrisShape(blankNPC, mother.getIrisShape());
			} else {
				body.getEye().setIrisShape(blankNPC, father.getIrisShape());
			}
		}
		// Pupil shape:
		if(Math.random()>=0.75) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getEye().setPupilShape(blankNPC, mother.getPupilShape());
			} else {
				body.getEye().setPupilShape(blankNPC, father.getPupilShape());
			}
		}
		
		// Horn:
		// Horn rows:
		if(Math.random()>=0.75) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getHorn().setHornRows(blankNPC, mother.getHornRows());
			} else {
				body.getHorn().setHornRows(blankNPC, father.getHornRows());
			}
		}
		
		// Penis:
		boolean inheritsFromMotherPenis = mother.hasPenis();
		boolean inheritsFromFatherPenis = father.hasPenis();
		if(body.getPenis().getType()!=PenisType.NONE) {
			// Penis size:
			body.getPenis().setPenisSize(blankNPC, getSizeFromGenetics(
					body.getPenis().getRawSizeValue(),
					inheritsFromMotherPenis, mother.getPenisRawSizeValue(),
					inheritsFromFatherPenis, father.getPenisRawSizeValue()));
			// Penis modifiers:
			for(PenetrationModifier pm : PenetrationModifier.values()) {
				if(Math.random()>=0.5) {
					if(inheritsFromMotherPenis && inheritsFromFatherPenis) {
						if(Math.random()>=takesAfterMotherChance) {
							if(mother.hasPenisModifier(pm)) {
								body.getPenis().addPenisModifier(blankNPC, pm);
							}
						} else {
							if(father.hasPenisModifier(pm)) {
								body.getPenis().addPenisModifier(blankNPC, pm);
							}
						}
					} else if(inheritsFromMotherPenis) {
						if(mother.hasPenisModifier(pm)) {
							body.getPenis().addPenisModifier(blankNPC, pm);
						}
					} else if(inheritsFromFatherPenis) {
						if(father.hasPenisModifier(pm)) {
							body.getPenis().addPenisModifier(blankNPC, pm);
						}
					}
				}
			}
			// Urethra modifiers:
			for(OrificeModifier om : OrificeModifier.values()) {
				if(Math.random()>=0.5) {
					if(inheritsFromMotherPenis && inheritsFromFatherPenis) {
						if(Math.random()>=takesAfterMotherChance) {
							if(mother.hasUrethraOrificeModifier(om)) {
								body.getPenis().getOrificeUrethra().addOrificeModifier(blankNPC, om);
							}
						} else {
							if(father.hasUrethraOrificeModifier(om)) {
								body.getPenis().getOrificeUrethra().addOrificeModifier(blankNPC, om);
							}
						}
					} else if(inheritsFromMotherPenis) {
						if(mother.hasUrethraOrificeModifier(om)) {
							body.getPenis().getOrificeUrethra().addOrificeModifier(blankNPC, om);
						}
					} else if(inheritsFromFatherPenis) {
						if(father.hasUrethraOrificeModifier(om)) {
							body.getPenis().getOrificeUrethra().addOrificeModifier(blankNPC, om);
						}
					}
				}
			}
			// Testicles:
			// Testicle size:
			body.getPenis().getTesticle().setTesticleSize(blankNPC, getSizeFromGenetics(
					body.getPenis().getTesticle().getTesticleSize().getValue(),
					inheritsFromMotherPenis, mother.getTesticleSize().getValue(),
					inheritsFromFatherPenis, father.getTesticleSize().getValue()));
			// Testicle count:
			if(Math.random()>=0.75) {
				if(inheritsFromMotherPenis && inheritsFromFatherPenis) {
					if(Math.random()>=takesAfterMotherChance) {
						body.getPenis().getTesticle().setTesticleCount(blankNPC, mother.getTesticleCount());
					} else {
						body.getPenis().getTesticle().setTesticleCount(blankNPC, father.getTesticleCount());
					}
				} else if(inheritsFromMotherPenis) {
					body.getPenis().getTesticle().setTesticleCount(blankNPC, mother.getTesticleCount());
				} else if(inheritsFromFatherPenis) {
					body.getPenis().getTesticle().setTesticleCount(blankNPC, father.getTesticleCount());
				}
			}
			// Internal testicles:
			if(body.getVagina().getType()!=VaginaType.NONE && !Main.game.isFutanariTesticlesEnabled()) {
				body.getPenis().getTesticle().setInternal(blankNPC, true);
				
			} else if(Math.random()>=0.75) {
				if(inheritsFromMotherPenis && inheritsFromFatherPenis) {
					if(Math.random()>=takesAfterMotherChance) {
						if(mother.isInternalTesticles()) {
							body.getPenis().getTesticle().setInternal(blankNPC, true);
						}
					} else {
						if(father.isInternalTesticles()) {
							body.getPenis().getTesticle().setInternal(blankNPC, true);
						}
					}
				} else if(inheritsFromMotherPenis) {
					if(mother.isInternalTesticles()) {
						body.getPenis().getTesticle().setInternal(blankNPC, true);
					}
				} else if(inheritsFromFatherPenis) {
					if(father.isInternalTesticles()) {
						body.getPenis().getTesticle().setInternal(blankNPC, true);
					}
				}
			}
			// Cum Production:
			body.getPenis().getTesticle().setCumStorage(blankNPC, getSizeFromGenetics(
					body.getPenis().getTesticle().getRawCumStorageValue(),
					inheritsFromMotherPenis, mother.getPenisRawCumStorageValue(),
					inheritsFromFatherPenis, father.getPenisRawCumStorageValue()));
		}
		
		// Tail:
		if(Math.random()>0.75) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getTail().setTailCount(blankNPC, mother.getTailCount());
			} else {
				body.getTail().setTailCount(blankNPC, father.getTailCount());
			}
		}
		
		// Vagina:
		boolean inheritsFromMotherVagina = mother.hasVagina();
		boolean inheritsFromFatherVagina = father.hasVagina();
		if(body.getVagina().getType()!=VaginaType.NONE) {
			// Clitoris size:
			body.getVagina().getClitoris().setClitorisSize(blankNPC, getSizeFromGenetics(
					body.getVagina().getClitoris().getRawClitorisSizeValue(),
					inheritsFromMotherVagina, mother.getVaginaRawClitorisSizeValue(),
					inheritsFromFatherVagina, father.getVaginaRawClitorisSizeValue()));
			//TODO clit girth and clit modifiers
			// Labia size:
			body.getVagina().setLabiaSize(blankNPC, getSizeFromGenetics(
					body.getVagina().getRawLabiaSizeValue(),
					inheritsFromMotherVagina, mother.getVaginaRawLabiaSizeValue(),
					inheritsFromFatherVagina, father.getVaginaRawLabiaSizeValue()));
//			// Capacity:
//			body.getVagina().getOrificeVagina().setCapacity(blankNPC, getSizeFromGenetics(
//					(int) body.getVagina().getOrificeVagina().getRawCapacityValue(),
//					inheritsFromMotherVagina, (int) mother.getVaginaRawCapacityValue(),
//					inheritsFromFatherVagina, (int) father.getVaginaRawCapacityValue()));
			// Wetness:
			body.getVagina().getOrificeVagina().setWetness(blankNPC, getSizeFromGenetics(
					body.getVagina().getOrificeVagina().getWetness(blankNPC).getValue(),
					inheritsFromMotherVagina, mother.getVaginaWetness().getValue(),
					inheritsFromFatherVagina, father.getVaginaWetness().getValue()));
			// Modifiers:
			for(OrificeModifier om : OrificeModifier.values()) {
				if(Math.random()>=0.5) {
					if(inheritsFromMotherVagina && inheritsFromFatherVagina) {
						if(Math.random()>=takesAfterMotherChance) {
							if(mother.hasVaginaOrificeModifier(om)) {
								body.getVagina().getOrificeVagina().addOrificeModifier(blankNPC, om);
							}
						} else {
							if(father.hasVaginaOrificeModifier(om)) {
								body.getVagina().getOrificeVagina().addOrificeModifier(blankNPC, om);
							}
						}
					} else if(inheritsFromMotherVagina) {
						if(mother.hasVaginaOrificeModifier(om)) {
							body.getVagina().getOrificeVagina().addOrificeModifier(blankNPC, om);
						}
					} else if(inheritsFromFatherVagina) {
						if(father.hasVaginaOrificeModifier(om)) {
							body.getVagina().getOrificeVagina().addOrificeModifier(blankNPC, om);
						}
					}
				}
			}
		}
		
		if(Main.getProperties().udders==0
				|| (body.getLeg().getLegConfiguration()==LegConfiguration.BIPEDAL && Main.getProperties().udders==1)
				|| (body.getLeg().getLegConfiguration()==LegConfiguration.BIPEDAL && body.getRaceStage()!=RaceStage.GREATER)) {
			body.getBreastCrotch().setType(null, BreastType.NONE);
		}
		
		raceTakesAfter.applySpeciesChanges(body);
		
		body.setTakesAfterMother(takesAfterMother);
		
		return body;
	}
	
	private static List<BodyCoveringType> setCoveringColours(Body body, GameCharacter character, List<BodyCoveringType> typesToInfluence) {
		List<BodyCoveringType> tempList = new ArrayList<>(typesToInfluence);
		
		// Skin & fur colours:
		for(BodyPartInterface bp : character.getAllBodyParts()){
			if(bp.getBodyCoveringType(character)!=null
					&& !(bp instanceof Eye)) {
				if(tempList.contains(bp.getBodyCoveringType(character))) {
					Covering covering = character.getCovering(bp.getBodyCoveringType(character));
					body.getCoverings().put(
							bp.getBodyCoveringType(character),
							new Covering(covering.getType(), covering.getPattern(), covering.getModifier(), covering.getPrimaryColour(), covering.isPrimaryGlowing(), covering.getSecondaryColour(), covering.isSecondaryGlowing()));
					tempList.remove(bp.getBodyCoveringType(character));
//					System.out.println("Set: "+bp.getType().getName(character)+" : "+bp.getBodyCoveringType().getName(character)+"("+bp.getType().getRace().getName()+") : "+covering.getPrimaryColour().getName());
				}
			}
		}
		
		List<BodyCoveringType> extraCoverings = new ArrayList<>();
		extraCoverings.add(BodyCoveringType.ANUS);
		extraCoverings.add(BodyCoveringType.NIPPLES);
		extraCoverings.add(BodyCoveringType.MOUTH);
		extraCoverings.add(BodyCoveringType.TONGUE);
		
		for(BodyCoveringType bct : extraCoverings) {
			if(tempList.contains(bct)) {
				Covering covering = character.getCovering(bct);
					body.getCoverings().put(
							bct,
							new Covering(covering.getType(), covering.getPattern(), covering.getModifier(), covering.getPrimaryColour(), covering.isPrimaryGlowing(), covering.getSecondaryColour(), covering.isSecondaryGlowing()));
					tempList.remove(bct);
			}
//				System.out.println("Set: "+bct+" : "+bct.getName(character)+" : "+covering.getPrimaryColour().getName());
		}
//		System.out.println("------------------------------");
		
		return tempList;
	}
	
	private static int getSizeFromGenetics(int baseSize, boolean inheritsFromMother, int motherSize, boolean inheritsFromFather, int fatherSize) {
		// $BaseRaceSize + RandomAmount($ParentSize - $BaseRaceSize), then +-10%
		int variation = 0;
		if(inheritsFromMother && inheritsFromFather) {
			variation = (motherSize + fatherSize)/2;
		} else if(inheritsFromMother) {
			variation = motherSize;
		} else if(inheritsFromFather) {
			variation = fatherSize;
		}
		
		if(variation != 0) {
			int difference = variation - baseSize;
			return (int) Math.round(baseSize + difference*Math.random());
		} else {
			return baseSize;
		}
		
//		return (int) ((baseSize + (Math.signum(difference)*Util.random.nextInt(Math.abs(difference) +1)))*(0.9f+(Math.random()*0.2f)));
	}

	public static Body generateHalfDemonBody(GameCharacter linkedCharacter, Gender startingGender, Subspecies halfSubspecies, boolean applyHalfDemonAttributeChanges) {
//		Gender startingGender;
		if(startingGender==null) {
			startingGender = Math.random()>0.5f?Gender.F_V_B_FEMALE:Gender.M_P_MALE;
		}
		RaceStage stage = CharacterUtils.getRaceStageFromPreferences(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(halfSubspecies), startingGender, halfSubspecies);
		AbstractRacialBody demonBody = RacialBody.DEMON;
		
		if(linkedCharacter!=null) {
//			startingGender = linkedCharacter.getGender();
//			stage = linkedCharacter.getRaceStage();
			
			if(applyHalfDemonAttributeChanges) {
				if(linkedCharacter.getAttributeValue(Attribute.MAJOR_CORRUPTION)<75) {
					linkedCharacter.setAttribute(Attribute.MAJOR_CORRUPTION, 75+Util.random.nextInt(26));
				}
				if(linkedCharacter.getAttributeValue(Attribute.MAJOR_ARCANE)<25) {
					linkedCharacter.setAttribute(Attribute.MAJOR_ARCANE, 25+Util.random.nextInt(11));
				}
			}
			
		}
		
		switch(stage) {
			case GREATER:
				break;
			case LESSER:
				break;
			// Anything less than lesser is covered by demon parts, so make it up to lesser:
			case PARTIAL_FULL:
			case PARTIAL:
			case HUMAN:
				stage = RaceStage.LESSER;
				break;
		}
		Body body = CharacterUtils.generateBody(linkedCharacter, startingGender, halfSubspecies, stage);
		body.setSubspeciesOverride(Subspecies.HALF_DEMON);
		
		body.setAss(new Ass(AssType.DEMON_COMMON,
				(startingGender.isFeminine() ? demonBody.getFemaleAssSize() : demonBody.getMaleAssSize()),
				demonBody.getAnusWetness(),
				demonBody.getAnusCapacity(),
				demonBody.getAnusElasticity(),
				demonBody.getAnusPlasticity(),
				true));
		
		body.setBreast(new Breast(BreastType.DEMON_COMMON,
				Util.randomItemFrom(demonBody.getBreastShapes()),
				(startingGender.getGenderName().isHasBreasts()
						? Math.max(CupSize.getMinimumCupSizeForBreasts().getMeasurement(), demonBody.getBreastSize()+Main.getProperties().breastSizePreference)
						: demonBody.getNoBreastSize()),
				(startingGender.isFeminine() ? demonBody.getFemaleLactationRate() : demonBody.getMaleLactationRate()),
				(startingGender.isFeminine() ? demonBody.getBreastCountFemale() : demonBody.getBreastCountMale()),
				(startingGender.isFeminine() ? demonBody.getFemaleNippleSize() : demonBody.getMaleNippleSize()),
				(startingGender.isFeminine() ? demonBody.getFemaleNippleShape() : demonBody.getMaleNippleShape()),
				(startingGender.isFeminine() ? demonBody.getFemaleAreolaeSize() : demonBody.getMaleAreolaeSize()),
				(startingGender.isFeminine() ? demonBody.getFemaleNippleCountPerBreast() : demonBody.getMaleNippleCountPerBreast()),
				(startingGender.isFeminine() ? demonBody.getFemaleBreastCapacity() : demonBody.getMaleBreastCapacity()),
				(startingGender.isFeminine() ? demonBody.getFemaleBreastElasticity() : demonBody.getMaleBreastElasticity()),
				(startingGender.isFeminine() ? demonBody.getFemaleBreastPlasticity() : demonBody.getMaleBreastPlasticity()), 
				true));
		
		body.setBreastCrotch(
				new BreastCrotch(startingGender.isFeminine()
					?demonBody.getBreastCrotchType()
					:BreastType.NONE,
				Util.randomItemFrom(demonBody.getBreastCrotchShapes()),
				demonBody.getBreastCrotchSize(),
				demonBody.getBreastCrotchLactationRate(),
				demonBody.getBreastCrotchCount(),
				demonBody.getBreastCrotchNippleSize(),
				demonBody.getBreastCrotchNippleShape(),
				demonBody.getBreastCrotchAreolaeSize(),
				demonBody.getNippleCountPerBreastCrotch(),
				demonBody.getBreastCrotchCapacity(),
				demonBody.getBreastCrotchElasticity(),
				demonBody.getBreastCrotchPlasticity(), 
				true));

		if(Main.getProperties().udders==0
				|| (body.getLeg().getLegConfiguration()==LegConfiguration.BIPEDAL && Main.getProperties().udders==1)
				|| (body.getLeg().getLegConfiguration()==LegConfiguration.BIPEDAL && body.getRaceStage()!=RaceStage.GREATER)) {
			body.getBreastCrotch().setType(null, BreastType.NONE);
		}

		if(halfSubspecies==Subspecies.HUMAN) {
			body.setEar(new Ear(EarType.DEMON_COMMON));
		}
		
		body.setEye(new Eye(EyeType.DEMON_COMMON));
		
		if(halfSubspecies==Subspecies.HUMAN) {
			body.setHair(new Hair(HairType.DEMON_COMMON,
					(startingGender.isFeminine() ? demonBody.getFemaleHairLength() : demonBody.getMaleHairLength()),
					HairStyle.getRandomHairStyle((startingGender.isFeminine() ? demonBody.getFemaleHairLength() : demonBody.getMaleHairLength()))));
		}
		
		body.setHorn(new Horn(demonBody.getRandomHornType(false),
				(startingGender.isFeminine() ? demonBody.getFemaleHornLength() : demonBody.getMaleHornLength())));
		
		body.setPenis(startingGender.getGenderName().isHasPenis()
				? new Penis(demonBody.getPenisType(),
					demonBody.getPenisSize(),
					true,
					demonBody.getPenisGirth(),
					demonBody.getTesticleSize(),
					demonBody.getCumProduction(),
					demonBody.getTesticleQuantity())
				: new Penis(PenisType.NONE, 0, false, 0, 0, 0, 2));
		// If non-human, set modifiers to be the same as the default race modifiers:
		if(halfSubspecies!=Subspecies.HUMAN) {
			body.getPenis().clearPenisModifiers();
			for(PenetrationModifier mod : RacialBody.valueOfRace(halfSubspecies.getRace()).getPenisType().getDefaultPenisModifiers()) {
				body.getPenis().addPenisModifier(linkedCharacter, mod);
			}
		}

		if(body.getPenis().getType()!=PenisType.NONE
				&& body.getPenis().getType()!=PenisType.DILDO
				&& body.getVagina().getType()!=VaginaType.NONE
				&& !Main.game.isFutanariTesticlesEnabled()) {
			body.getPenis().getTesticle().setInternal(null, true);
		}
		
		
		List<TailType> tailTypes = RacialBody.valueOfRace(halfSubspecies.getRace()).getTailType();
		if(tailTypes.size()==1 && tailTypes.get(0)==TailType.NONE) {
			body.setTail(new Tail(demonBody.getRandomTailType(false)));
		}
		
		body.setVagina(startingGender.getGenderName().isHasVagina()
				? new Vagina(demonBody.getVaginaType(),
						LabiaSize.getRandomLabiaSize().getValue(),
						demonBody.getClitSize(),
						demonBody.getVaginaWetness(),
						demonBody.getVaginaCapacity(),
						demonBody.getVaginaElasticity(),
						demonBody.getVaginaPlasticity(),
						true)
				: new Vagina(VaginaType.NONE, 0, 0, 0, 0, 3, 3, true));
		// If non-human, set modifiers to be the same as the default race modifiers:
		if(halfSubspecies!=Subspecies.HUMAN) {
			body.getVagina().getOrificeVagina().clearOrificeModifiers();
			for(OrificeModifier mod : RacialBody.valueOfRace(halfSubspecies.getRace()).getVaginaType().getDefaultRacialOrificeModifiers()) {
				body.getVagina().getOrificeVagina().addOrificeModifier(linkedCharacter, mod);
			}
		}
		
		if(!body.getArm().getType().allowsFlight()) { // Do not give back wings to characters who have arm wings.
			body.setWing(new Wing(demonBody.getRandomWingType(false),
					(startingGender.isFeminine() ? demonBody.getFemaleWingSize() : demonBody.getMaleWingSize())));
		}
		
//		startingBodyType.getBodyMaterial(),
//		startingBodyType.getGenitalArrangement(),
		body.setHeight((startingGender.isFeminine() ? demonBody.getFemaleHeight() : demonBody.getMaleHeight()));
		
		body.setFemininity(startingGender.getType()==PronounType.NEUTRAL?50:(startingGender.isFeminine() ? demonBody.getFemaleFemininity() : demonBody.getMaleFemininity()));
		
		body.setBodySize((startingGender.isFeminine() ? demonBody.getFemaleBodySize() : demonBody.getMaleBodySize()));
		
		body.setMuscle((startingGender.isFeminine() ? demonBody.getFemaleMuscle() : demonBody.getMaleMuscle()));

		body.updateCoverings(true, true, true, true);
		
		setBodyHair(body);
		
		return body;
	}
	
	public static Body generateBody(GameCharacter linkedCharacter, Gender startingGender, Subspecies species, RaceStage stage) {
		return generateBody(linkedCharacter, startingGender, RacialBody.valueOfRace(species.getRace()), species, stage);
	}
	
	public static Body generateBody(GameCharacter linkedCharacter, Gender startingGender, AbstractRacialBody startingBodyType, RaceStage stage) {
		return generateBody(linkedCharacter, startingGender, startingBodyType, null, stage);
	}
	
	public static Body generateBody(GameCharacter linkedCharacter, Gender startingGender, AbstractRacialBody startingBodyType, Subspecies species, RaceStage stage) {
		
		boolean hasVagina = startingGender.getGenderName().isHasVagina();
		boolean hasPenis = startingGender.getGenderName().isHasPenis();
		boolean hasBreasts = startingGender.getGenderName().isHasBreasts();
		boolean isSlime = species == Subspecies.SLIME;
		boolean isHalfDemon = species == Subspecies.HALF_DEMON;
		
		if(isSlime || isHalfDemon) {
			if(linkedCharacter==null || !linkedCharacter.isUnique()) {
				List<Subspecies> slimeSubspecies = new ArrayList<>();
				for(Subspecies subspecies : Subspecies.values()) {
					switch(subspecies) {
						// Special races that slimes/half-demons do not spawn as:
						case ELEMENTAL_AIR:
						case ELEMENTAL_ARCANE:
						case ELEMENTAL_EARTH:
						case ELEMENTAL_FIRE:
						case ELEMENTAL_WATER:
						case FOX_ASCENDANT:
						case FOX_ASCENDANT_FENNEC:
						case SLIME:
						case ANGEL:
						case DEMON:
						case HALF_DEMON:
						case IMP:
						case IMP_ALPHA:
						case ELDER_LILIN:
						case LILIN:
							break;
						default:
							if(startingGender.isFeminine()) {
								for(Entry<Subspecies, FurryPreference> entry : Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().entrySet()) {
									if(entry.getValue() != FurryPreference.HUMAN) {
										slimeSubspecies.add(subspecies);
									}
								}
							} else {
								for(Entry<Subspecies, FurryPreference> entry : Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().entrySet()) {
									if(entry.getValue() != FurryPreference.HUMAN) {
										slimeSubspecies.add(subspecies);
									}
								}
							}
							break;
					}
				}
				
				if(slimeSubspecies.isEmpty()) {
					slimeSubspecies.add(Subspecies.HUMAN);
				}
				species = Util.randomItemFrom(slimeSubspecies);
				
				if(isHalfDemon) {
					return generateHalfDemonBody(linkedCharacter, startingGender, species, true);
				}
				
				if(startingGender.isFeminine()) {
					stage = CharacterUtils.getRaceStageFromPreferences(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(species), startingGender, species);
					
				} else {
					stage = CharacterUtils.getRaceStageFromPreferences(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(species), startingGender, species);
				}
				
				startingBodyType = RacialBody.valueOfRace(species.getRace());
			}
		}
		
//		System.out.println(species+", "+stage);
		
		Body body = new Body.BodyBuilder(
				new Arm((stage.isArmFurry()?startingBodyType.getArmType():ArmType.HUMAN), startingBodyType.getArmRows()),
				new Ass(stage.isAssFurry()?startingBodyType.getAssType():AssType.HUMAN,
						(startingGender.isFeminine() ? startingBodyType.getFemaleAssSize() : startingBodyType.getMaleAssSize()),
						startingBodyType.getAnusWetness(),
						startingBodyType.getAnusCapacity(),
						startingBodyType.getAnusElasticity(),
						startingBodyType.getAnusPlasticity(),
						true),
				new Breast(stage.isBreastFurry()?startingBodyType.getBreastType():BreastType.HUMAN,
						Util.randomItemFrom(startingBodyType.getBreastShapes()),
						(hasBreasts
								? Math.max(CupSize.getMinimumCupSizeForBreasts().getMeasurement(), startingBodyType.getBreastSize()+Main.getProperties().breastSizePreference)
								: startingBodyType.getNoBreastSize()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleLactationRate() : startingBodyType.getMaleLactationRate()),
						(startingGender.isFeminine() ? startingBodyType.getBreastCountFemale() : startingBodyType.getBreastCountMale()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleNippleSize() : startingBodyType.getMaleNippleSize()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleNippleShape() : startingBodyType.getMaleNippleShape()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleAreolaeSize() : startingBodyType.getMaleAreolaeSize()),
						(stage.isBreastFurry() ? (startingGender.isFeminine() ? startingBodyType.getFemaleNippleCountPerBreast() : startingBodyType.getMaleNippleCountPerBreast()) : 1),
						(startingGender.isFeminine() ? startingBodyType.getFemaleBreastCapacity() : startingBodyType.getMaleBreastCapacity()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleBreastElasticity() : startingBodyType.getMaleBreastElasticity()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleBreastPlasticity() : startingBodyType.getMaleBreastPlasticity()), 
						true),
				new Face((stage.isFaceFurry()?startingBodyType.getFaceType():FaceType.HUMAN),
						(startingGender.isFeminine() ? startingBodyType.getFemaleLipSize() : startingBodyType.getMaleLipSize())),
				new Eye(stage.isEyeFurry()?startingBodyType.getEyeType():EyeType.HUMAN),
				new Ear(stage.isEarFurry()?startingBodyType.getEarType():EarType.HUMAN),
				new Hair(stage.isHairFurry()?startingBodyType.getHairType():HairType.HUMAN,
						(startingBodyType.isHairTypeLinkedToFaceType()
							?(stage.isFaceFurry()
									?(startingGender.isFeminine() ? startingBodyType.getFemaleHairLength() : startingBodyType.getMaleHairLength())
									:(startingGender.isFeminine() ? RacialBody.HUMAN.getFemaleHairLength() : RacialBody.HUMAN.getMaleHairLength()))
							:(startingGender.isFeminine() ? startingBodyType.getFemaleHairLength() : startingBodyType.getMaleHairLength())),
						HairStyle.getRandomHairStyle((startingGender.isFeminine() ? startingBodyType.getFemaleHairLength() : startingBodyType.getMaleHairLength()))),
				new Leg(stage.isLegFurry()?startingBodyType.getLegType():LegType.HUMAN, startingBodyType.getLegConfiguration()),
				new Skin(stage.isSkinFurry()?startingBodyType.getSkinType():SkinType.HUMAN),
						startingBodyType.getBodyMaterial(),
						startingBodyType.getGenitalArrangement(),
						(startingGender.isFeminine() ? startingBodyType.getFemaleHeight() : startingBodyType.getMaleHeight()),
						startingGender.getType()==PronounType.NEUTRAL?50:(startingGender.isFeminine() ? startingBodyType.getFemaleFemininity() : startingBodyType.getMaleFemininity()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleBodySize() : startingBodyType.getMaleBodySize()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleMuscle() : startingBodyType.getMaleMuscle()))
				.vagina(hasVagina
						? new Vagina(stage.isVaginaFurry()?startingBodyType.getVaginaType():VaginaType.HUMAN,
								LabiaSize.getRandomLabiaSize().getValue(),
								startingBodyType.getClitSize(),
								startingBodyType.getVaginaWetness(),
								startingBodyType.getVaginaCapacity(),
								startingBodyType.getVaginaElasticity(),
								startingBodyType.getVaginaPlasticity(),
								true)
						: new Vagina(VaginaType.NONE, 0, 0, 0, 0, 3, 3, true))
				.penis(hasPenis
						? new Penis(stage.isPenisFurry()?startingBodyType.getPenisType():PenisType.HUMAN,
							startingBodyType.getPenisSize(),
							true,
							startingBodyType.getPenisGirth(),
							startingBodyType.getTesticleSize(),
							startingBodyType.getCumProduction(),
							startingBodyType.getTesticleQuantity())
						: new Penis(PenisType.NONE, 0, false, 0, 0, 0, 2))
				.horn(new Horn((stage.isHornFurry()?startingBodyType.getRandomHornType(false):HornType.NONE), (startingGender.isFeminine() ? startingBodyType.getFemaleHornLength() : startingBodyType.getMaleHornLength())))
				.antenna(new Antenna(stage.isAntennaFurry()?startingBodyType.getAntennaType():AntennaType.NONE))
				.tail(new Tail(stage.isTailFurry()?startingBodyType.getRandomTailType(false):TailType.NONE))
				.tentacle(new Tentacle(stage.isTentacleFurry()?startingBodyType.getTentacleType():TentacleType.NONE))
				.wing(new Wing((stage.isWingFurry()?startingBodyType.getRandomWingType(false):WingType.NONE), (startingGender.isFeminine() ? startingBodyType.getFemaleWingSize() : startingBodyType.getMaleWingSize())))
				.breastCrotch(
					new BreastCrotch(
							startingGender.isFeminine()
								?startingBodyType.getBreastCrotchType()
								:BreastType.NONE,
							Util.randomItemFrom(startingBodyType.getBreastCrotchShapes()),
							startingBodyType.getBreastCrotchSize(),
							startingBodyType.getBreastCrotchLactationRate(),
							startingBodyType.getBreastCrotchCount(),
							startingBodyType.getBreastCrotchNippleSize(),
							startingBodyType.getBreastCrotchNippleShape(),
							startingBodyType.getBreastCrotchAreolaeSize(),
							startingBodyType.getNippleCountPerBreastCrotch(),
							startingBodyType.getBreastCrotchCapacity(),
							startingBodyType.getBreastCrotchElasticity(),
							startingBodyType.getBreastCrotchPlasticity(), 
							true))
				.build();
		
		if(Main.getProperties().udders==0
				|| (body.getLeg().getLegConfiguration()==LegConfiguration.BIPEDAL && Main.getProperties().udders==1)
				|| (body.getLeg().getLegConfiguration()==LegConfiguration.BIPEDAL && body.getRaceStage()!=RaceStage.GREATER)) {
			body.getBreastCrotch().setType(null, BreastType.NONE);
		}
		
		if(body.getPenis().getType()!=PenisType.NONE
				&& body.getPenis().getType()!=PenisType.DILDO
				&& body.getVagina().getType()!=VaginaType.NONE
				&& !Main.game.isFutanariTesticlesEnabled()) {
			body.getPenis().getTesticle().setInternal(null, true);
		}
		
		// Pubic hair:
		setBodyHair(body);
		
		if(species!=null) {
			if(stage!=RaceStage.HUMAN) {
				species.applySpeciesChanges(body);
			}
			if(isSlime) {
				Subspecies.SLIME.applySpeciesChanges(body);
			}
		}
		body.calculateRace(linkedCharacter);
		return body;
	}
	
	public static Body reassignBody(GameCharacter linkedCharacter, Body body, Gender startingGender, Subspecies species, RaceStage stage) {
		
		AbstractRacialBody startingBodyType = RacialBody.valueOfRace(species.getRace());
		
		boolean hasVagina = startingGender.getGenderName().isHasVagina();
		boolean hasPenis = startingGender.getGenderName().isHasPenis();
		boolean hasBreasts = startingGender.getGenderName().isHasBreasts();
		
		body.setArm(new Arm((stage.isArmFurry()?startingBodyType.getArmType():ArmType.HUMAN), startingBodyType.getArmRows()));
		
		body.setAss(new Ass(stage.isAssFurry()?startingBodyType.getAssType():AssType.HUMAN,
						(startingGender.isFeminine() ? startingBodyType.getFemaleAssSize() : startingBodyType.getMaleAssSize()),
						startingBodyType.getAnusWetness(),
						startingBodyType.getAnusCapacity(),
						startingBodyType.getAnusElasticity(),
						startingBodyType.getAnusPlasticity(),
						true));
		
		body.setBreast(new Breast(stage.isBreastFurry()?startingBodyType.getBreastType():BreastType.HUMAN,
				Util.randomItemFrom(startingBodyType.getBreastShapes()),
				(hasBreasts
						? Math.max(CupSize.getMinimumCupSizeForBreasts().getMeasurement(), startingBodyType.getBreastSize()+Main.getProperties().breastSizePreference)
						: startingBodyType.getNoBreastSize()),
				(startingGender.isFeminine() ? startingBodyType.getFemaleLactationRate() : startingBodyType.getMaleLactationRate()),
				(startingGender.isFeminine() ? startingBodyType.getBreastCountFemale() : startingBodyType.getBreastCountMale()),
				(startingGender.isFeminine() ? startingBodyType.getFemaleNippleSize() : startingBodyType.getMaleNippleSize()),
				(startingGender.isFeminine() ? startingBodyType.getFemaleNippleShape() : startingBodyType.getMaleNippleShape()),
				(startingGender.isFeminine() ? startingBodyType.getFemaleAreolaeSize() : startingBodyType.getMaleAreolaeSize()),
				(stage.isBreastFurry() ? (startingGender.isFeminine() ? startingBodyType.getFemaleNippleCountPerBreast() : startingBodyType.getMaleNippleCountPerBreast()) : 1),
				(startingGender.isFeminine() ? startingBodyType.getFemaleBreastCapacity() : startingBodyType.getMaleBreastCapacity()),
				(startingGender.isFeminine() ? startingBodyType.getFemaleBreastElasticity() : startingBodyType.getMaleBreastElasticity()),
				(startingGender.isFeminine() ? startingBodyType.getFemaleBreastPlasticity() : startingBodyType.getMaleBreastPlasticity()), 
				true));

		body.setBreastCrotch(
				new BreastCrotch(
					startingGender.isFeminine()
						?startingBodyType.getBreastCrotchType()
						:BreastType.NONE,
					Util.randomItemFrom(startingBodyType.getBreastCrotchShapes()),
					startingBodyType.getBreastCrotchSize(),
					startingBodyType.getBreastCrotchLactationRate(),
					startingBodyType.getBreastCrotchCount(),
					startingBodyType.getBreastCrotchNippleSize(),
					startingBodyType.getBreastCrotchNippleShape(),
					startingBodyType.getBreastCrotchAreolaeSize(),
					startingBodyType.getNippleCountPerBreastCrotch(),
					startingBodyType.getBreastCrotchCapacity(),
					startingBodyType.getBreastCrotchElasticity(),
					startingBodyType.getBreastCrotchPlasticity(), 
					true));
		
		body.setFace(new Face((stage.isFaceFurry()?startingBodyType.getFaceType():FaceType.HUMAN),
				(startingGender.isFeminine() ? startingBodyType.getFemaleLipSize() : startingBodyType.getMaleLipSize())));
		
		body.setEye(new Eye(stage.isEyeFurry()?startingBodyType.getEyeType():EyeType.HUMAN));
		
		body.setEar(new Ear(stage.isEarFurry()?startingBodyType.getEarType():EarType.HUMAN));
		
		body.setHair(new Hair(stage.isHairFurry()?startingBodyType.getHairType():HairType.HUMAN,
				(startingBodyType.isHairTypeLinkedToFaceType()
						?(stage.isFaceFurry()
								?(startingGender.isFeminine() ? startingBodyType.getFemaleHairLength() : startingBodyType.getMaleHairLength())
								:(startingGender.isFeminine() ? RacialBody.HUMAN.getFemaleHairLength() : RacialBody.HUMAN.getMaleHairLength()))
						:(startingGender.isFeminine() ? startingBodyType.getFemaleHairLength() : startingBodyType.getMaleHairLength())),
					HairStyle.getRandomHairStyle((startingGender.isFeminine() ? startingBodyType.getFemaleHairLength() : startingBodyType.getMaleHairLength()))));
		
		body.setLeg(new Leg(stage.isLegFurry()?startingBodyType.getLegType():LegType.HUMAN, startingBodyType.getLegConfiguration()));
		
		body.setSkin(new Skin(stage.isSkinFurry()?startingBodyType.getSkinType():SkinType.HUMAN));
		
		body.setBodyMaterial(startingBodyType.getBodyMaterial());

		body.setGenitalArrangement(startingBodyType.getGenitalArrangement());
		
		body.setHeight(startingGender.isFeminine()
				? startingBodyType.getFemaleHeight()
				: startingBodyType.getMaleHeight());
		
		body.setFemininity(startingGender.getType()==PronounType.NEUTRAL?50:(startingGender.isFeminine() ? startingBodyType.getFemaleFemininity() : startingBodyType.getMaleFemininity()));
		
		body.setBodySize(startingGender.isFeminine() ? startingBodyType.getFemaleBodySize() : startingBodyType.getMaleBodySize());
		
		body.setMuscle(startingGender.isFeminine() ? startingBodyType.getFemaleMuscle() : startingBodyType.getMaleMuscle());
		
		body.setVagina(hasVagina
				? new Vagina(stage.isVaginaFurry()?startingBodyType.getVaginaType():VaginaType.HUMAN,
						LabiaSize.getRandomLabiaSize().getValue(),
						startingBodyType.getClitSize(),
						startingBodyType.getVaginaWetness(),
						startingBodyType.getVaginaCapacity(),
						startingBodyType.getVaginaElasticity(),
						startingBodyType.getVaginaPlasticity(),
						true)
				: new Vagina(VaginaType.NONE, 0, 0, 0, 0, 3, 3, true));
		
		body.setPenis(hasPenis
				? new Penis(stage.isPenisFurry()?startingBodyType.getPenisType():PenisType.HUMAN,
						startingBodyType.getPenisSize(),
						true,
						startingBodyType.getPenisGirth(),
						startingBodyType.getTesticleSize(),
						startingBodyType.getCumProduction(),
						startingBodyType.getTesticleQuantity())
					: new Penis(PenisType.NONE, 0, false, 0, 0, 0, 2));
		
		body.setHorn(new Horn((stage.isHornFurry()?startingBodyType.getRandomHornType(false):HornType.NONE), (startingGender.isFeminine() ? startingBodyType.getFemaleHornLength() : startingBodyType.getMaleHornLength())));
		
		body.setAntenna(new Antenna(stage.isAntennaFurry()?startingBodyType.getAntennaType():AntennaType.NONE));
		
		body.setTail(new Tail(stage.isTailFurry()?startingBodyType.getRandomTailType(false):TailType.NONE));
		
		body.setTentacle(new Tentacle(stage.isTentacleFurry()?startingBodyType.getTentacleType():TentacleType.NONE));
		
		body.setWing(new Wing((stage.isWingFurry()?startingBodyType.getRandomWingType(false):WingType.NONE), (startingGender.isFeminine() ? startingBodyType.getFemaleWingSize() : startingBodyType.getMaleWingSize())));
		

		if(Main.getProperties().udders==0
				|| (body.getLeg().getLegConfiguration()==LegConfiguration.BIPEDAL && Main.getProperties().udders==1)
				|| (body.getLeg().getLegConfiguration()==LegConfiguration.BIPEDAL && body.getRaceStage()!=RaceStage.GREATER)) {
			body.getBreastCrotch().setType(null, BreastType.NONE);
		}
		
		if(body.getPenis().getType()!=PenisType.NONE
				&& body.getPenis().getType()!=PenisType.DILDO
				&& body.getVagina().getType()!=VaginaType.NONE
				&& !Main.game.isFutanariTesticlesEnabled()) {
			body.getPenis().getTesticle().setInternal(null, true);
		}
		
		// Pubic hair:
		setBodyHair(body);
		
		if(species!=null && stage!=RaceStage.HUMAN) {
			species.applySpeciesChanges(body);
		}
		body.calculateRace(linkedCharacter);
		
		if(linkedCharacter!=null) {
			linkedCharacter.postTransformationCalculation();
		}
		
		return body;
	}
	
	private static void setBodyHair(Body body) {
		int slobLevel = Util.random.nextInt(101);

		if(body.isFeminine()) {
			body.getFace().setFacialHair(null, BodyHair.ZERO_NONE);
			if(slobLevel>=95) {
				body.getArm().setUnderarmHair(null, BodyHair.SIX_BUSHY);
				body.setPubicHair(BodyHair.SEVEN_WILD);
				body.getAss().getAnus().setAssHair(null, BodyHair.FIVE_UNKEMPT);
				
			} else if(slobLevel>=80) {
				body.getArm().setUnderarmHair(null, BodyHair.FOUR_NATURAL);
				body.setPubicHair(BodyHair.SIX_BUSHY);
				body.getAss().getAnus().setAssHair(null, BodyHair.FOUR_NATURAL);
				
			} else if(slobLevel>=60) {
				body.getArm().setUnderarmHair(null, BodyHair.ZERO_NONE);
				body.setPubicHair(BodyHair.FOUR_NATURAL);
				body.getAss().getAnus().setAssHair(null, BodyHair.TWO_MANICURED);
				
			} else if(slobLevel>=40) {
				body.getArm().setUnderarmHair(null, BodyHair.ZERO_NONE);
				body.setPubicHair(BodyHair.TWO_MANICURED);
				body.getAss().getAnus().setAssHair(null, BodyHair.ZERO_NONE);
				
			} else {
				body.getArm().setUnderarmHair(null, BodyHair.ZERO_NONE);
				body.setPubicHair(BodyHair.ZERO_NONE);
				body.getAss().getAnus().setAssHair(null, BodyHair.ZERO_NONE);
			}
			
		} else {
			if(slobLevel>=95) {
				if(Math.random()>0.5) {
					body.getFace().setFacialHair(null, BodyHair.SEVEN_WILD);
				} else {
					body.getFace().setFacialHair(null, BodyHair.ZERO_NONE);
				}
				body.getArm().setUnderarmHair(null, BodyHair.SIX_BUSHY);
				body.setPubicHair(BodyHair.SEVEN_WILD);
				body.getAss().getAnus().setAssHair(null, BodyHair.FIVE_UNKEMPT);
				
			} else if(slobLevel>=80) {
				if(Math.random()>0.6) {
					body.getFace().setFacialHair(null, BodyHair.SIX_BUSHY);
				} else {
					body.getFace().setFacialHair(null, BodyHair.ZERO_NONE);
				}
				body.getArm().setUnderarmHair(null, BodyHair.FOUR_NATURAL);
				body.setPubicHair(BodyHair.SIX_BUSHY);
				body.getAss().getAnus().setAssHair(null, BodyHair.FOUR_NATURAL);
				
			} else if(slobLevel>=60) {
				if(Math.random()>0.7) {
					body.getFace().setFacialHair(null, BodyHair.FOUR_NATURAL);
				} else {
					body.getFace().setFacialHair(null, BodyHair.ZERO_NONE);
				}
				body.getArm().setUnderarmHair(null, BodyHair.FOUR_NATURAL);
				body.setPubicHair(BodyHair.FOUR_NATURAL);
				body.getAss().getAnus().setAssHair(null, BodyHair.TWO_MANICURED);
				
			} else if(slobLevel>=20) {
				if(Math.random()>0.8) {
					body.getFace().setFacialHair(null, BodyHair.ONE_STUBBLE);
				} else {
					body.getFace().setFacialHair(null, BodyHair.ZERO_NONE);
				}
				body.getArm().setUnderarmHair(null, BodyHair.FOUR_NATURAL);
				body.setPubicHair(BodyHair.THREE_TRIMMED);
				body.getAss().getAnus().setAssHair(null, BodyHair.ZERO_NONE);
				
			} else {
				body.getArm().setUnderarmHair(null, BodyHair.ZERO_NONE);
				body.setPubicHair(BodyHair.ZERO_NONE);
				body.getAss().getAnus().setAssHair(null, BodyHair.ZERO_NONE);
			}
		}
	}
	
	

	public static String setGenericName(GameCharacter character, List<String> exclusiveAdjectives) {
		return setGenericName(character, null, exclusiveAdjectives);
	}
	/**
	 * Generates and sets a generic name for this character, based on their personality.
	 * @param character The character to set a generic name for.
	 * @param exclusiveAdjectives A list of adjectives to exclude from the random assignment.
	 * @return The adjective that was chosen to describe the character.
	 */
	public static String setGenericName(GameCharacter character, String baseName, List<String> exclusiveAdjectives) {

		List<String> characterAdjectives = new ArrayList<>();

		switch(character.getPersonality().get(PersonalityTrait.ADVENTUROUSNESS)) {
			case HIGH:
				characterAdjectives.add("daring");
				characterAdjectives.add("fearless");
				break;
			case LOW:
				characterAdjectives.add("suspicious");
				characterAdjectives.add("cowardly");
				break;
			default:
				break;
		}
		switch(character.getPersonality().get(PersonalityTrait.AGREEABLENESS)) {
			case HIGH:
				characterAdjectives.add("playful");
				break;
			case LOW:
				characterAdjectives.add("rude");
				break;
			default:
				break;
		}
		switch(character.getPersonality().get(PersonalityTrait.CONSCIENTIOUSNESS)) {
			case LOW:
				characterAdjectives.add("arrogant");
				characterAdjectives.add("smug");
				break;
			default:
				break;
		}
		switch(character.getPersonality().get(PersonalityTrait.EXTROVERSION)) {
			case HIGH:
				characterAdjectives.add("excitable");
				characterAdjectives.add("energetic");
				break;
			default:
				break;
		}
		switch(character.getPersonality().get(PersonalityTrait.NEUROTICISM)) {
			case HIGH:
				characterAdjectives.add("nervous");
				break;
			case LOW:
				characterAdjectives.add("brash");
				characterAdjectives.add("cocky");
				break;
			default:
				break;
		}
		characterAdjectives.removeAll(exclusiveAdjectives);
		
		if(characterAdjectives.isEmpty()) {
			characterAdjectives = Util.newArrayListOfValues("cheeky", "excitable", "energetic", "cunning", "rude", "cocky", "smug");
			characterAdjectives.removeAll(exclusiveAdjectives);
		}
		
		String adjective = Util.randomItemFrom(characterAdjectives);
		
		if(baseName==null || baseName.isEmpty()) {
			character.setGenericName(adjective+" "+character.getSubspecies().getName(character));
		} else {
			character.setGenericName(adjective+" "+baseName);
		}
		
		return adjective;
	}
	
	public static void randomiseBody(GameCharacter character, boolean randomiseAge) {
		
		if(randomiseAge) {
			character.setBirthday(LocalDateTime.of(Main.game.getStartingDate().getYear()-AgeCategory.getAgeFromPreferences(character.getGender()), character.getBirthMonth(), character.getDayOfBirth(), 12, 0));
			if(character.getRace()==Race.DEMON || character.getRace()==Race.HARPY) {
				character.setAgeAppearanceDifferenceToAppearAsAge(18+Util.random.nextInt(9));
			}
		}
		
		// Piercings (in order of probability that they'll have them, based on some random website that orders popularity):
		// All piercings are reliant on having ear piercings first:
		if (Math.random() >= (character.isFeminine()?0.1f:0.9f) || character.hasFetish(Fetish.FETISH_MASOCHIST)) {
			character.setPiercedEar(true);
			
			if (Math.random() <= 0.33f || character.hasFetish(Fetish.FETISH_MASOCHIST)) {
				character.setPiercedNavel(true);
			}
			if (Math.random() <= 0.19f || character.hasFetish(Fetish.FETISH_MASOCHIST)) {
				character.setPiercedNose(true);
			}
			if (Math.random() <= 0.1f || character.hasFetish(Fetish.FETISH_MASOCHIST)) {
				character.setPiercedTongue(true);
			}
			if (Math.random() <= 0.1f || character.hasFetish(Fetish.FETISH_MASOCHIST)) {
				character.setPiercedNipples(true);
			}
			if (Math.random() <= 0.1f || character.hasFetish(Fetish.FETISH_MASOCHIST)) { // It said lip piercings are only in 4% of the pierced population, but that seems too low for the game.
				character.setPiercedLip(true);
			}
			// Genitals:
			if (character.hasPenis() && (Math.random() <= 0.05f || character.hasFetish(Fetish.FETISH_MASOCHIST))) {
				character.setPiercedPenis(true);
			}
			if (character.hasVagina() && (Math.random() <= 0.05f || character.hasFetish(Fetish.FETISH_MASOCHIST))) {
				character.setPiercedVagina(true);
			}
		}
		
		//Ass:
		if(character.hasFetish(Fetish.FETISH_ANAL_RECEIVING) || character.getHistory()==Occupation.NPC_PROSTITUTE) {
			character.setAssVirgin(false);
			character.setAssCapacity(character.getAssRawCapacityValue()*1.2f, true);
			character.setAssStretchedCapacity(character.getAssRawCapacityValue());
		} else {
			character.setAssVirgin(true);
		}
		
		// Body:
		int height = character.getHeightValue()-15 + Util.random.nextInt(30) +1;
		
		if(character.getHeight()==Height.NEGATIVE_TWO_MIMIMUM) {
			character.setHeight(Math.min(Height.NEGATIVE_TWO_MIMIMUM.getMaximumValue()-1, Math.max(Height.NEGATIVE_TWO_MIMIMUM.getMinimumValue(), height)));
			
		} else if(character.getHeight()==Height.NEGATIVE_ONE_TINY) {
			character.setHeight(Math.min(Height.NEGATIVE_ONE_TINY.getMaximumValue()-1, Math.max(Height.NEGATIVE_ONE_TINY.getMinimumValue(), height)));
			
		} else {
			character.setHeight(Math.max(Height.ZERO_TINY.getMinimumValue(), height));
		}
		
		//Breasts:
		if(character.hasBreasts()) {
			character.setBreastSize(Math.max(CupSize.AA.getMeasurement(), character.getBreastSize().getMeasurement() -2 + Util.random.nextInt(5))); // Random size between -2 and +2 of base value.
			if(Math.random()<=0.015f || character.hasFetish(Fetish.FETISH_LACTATION_SELF)) {
				character.setBreastMilkStorage((int)((character.getBreastSize().getMeasurement() * 5)*(1+(Math.random()*2))));
				if(Math.random()<=0.025f) {
					character.addMilkModifier(FluidModifier.ADDICTIVE);
				}
				if(Math.random()<=0.025f) {
					character.addMilkModifier(FluidModifier.HALLUCINOGENIC);
				}
			}
		}
		
		//BreastsCrotch:
		if(character.hasBreastsCrotch()) {
			character.setBreastCrotchSize(Math.max(CupSize.FLAT.getMeasurement(), character.getBreastCrotchSize().getMeasurement() -2 + Util.random.nextInt(5)));// Random size between -2 and +2 of base value. 
			// If bipedal, make sure it's smaller than the smallest upper cup size:
			if(character.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				character.setBreastCrotchSize(Math.min(character.getBreastCrotchRawSizeValue(), character.getBreastRawSizeValue()-(character.getBreastRows())));
			}
			
			if(Math.random()<=0.015f || character.hasFetish(Fetish.FETISH_LACTATION_SELF)) {
				character.setBreastCrotchMilkStorage((int)((character.getBreastCrotchSize().getMeasurement() * 5)*(1+(Math.random()*2))));
				if(Math.random()<=0.025f) {
					character.addMilkCrotchModifier(FluidModifier.ADDICTIVE);
				}
				if(Math.random()<=0.025f) {
					character.addMilkCrotchModifier(FluidModifier.HALLUCINOGENIC);
				}
			}
		}
		
		// Face:
		if(character.hasFetish(Fetish.FETISH_ORAL_GIVING) || character.getHistory()==Occupation.NPC_PROSTITUTE) {
			character.setFaceCapacity(Capacity.FIVE_ROOMY.getMedianValue(), true);
			character.setFaceStretchedCapacity(character.getFaceRawCapacityValue());
			character.setFaceVirgin(false);
			
		} else {
			if(Math.random()>0.15f) {
				character.setFaceVirgin(true);
			} else {
				character.setFaceVirgin(false);
			}
		}
		
		// Hair:
		if(Math.random()<=0.1f && !character.getCovering(character.getHairCovering()).getType().getDyePatterns().isEmpty()) { // 10% chance to have a non-natural hair colour:
			Covering currentCovering = character.getCovering(character.getHairCovering());
			character.setHairCovering(new Covering(
					currentCovering.getType(),
					Util.randomItemFrom(currentCovering.getType().getDyePatterns()),
					currentCovering.getType().getAllPrimaryColours().isEmpty()
						?currentCovering.getPrimaryColour()
						:Util.randomItemFrom(currentCovering.getType().getAllPrimaryColours()),
					Math.random()<=0.05f,
					currentCovering.getType().getAllSecondaryColours().isEmpty()
						?currentCovering.getSecondaryColour()
						:Util.randomItemFrom(currentCovering.getType().getAllSecondaryColours()),
					Math.random()<=0.05f),
					true);
		}
		if(character.getHairRawLengthValue()!=0) {
			character.setHairLength(character.getHairLength().getMinimumValue() + Util.random.nextInt(character.getHairLength().getMaximumValue() - character.getHairLength().getMinimumValue()) +1);
		}
		
		// Penis:
		if(character.hasPenis() || character.getRace()==Race.DEMON) {
			character.setPenisVirgin(true);
			if(Math.random()>0.15f
					|| character.getHistory()==Occupation.NPC_PROSTITUTE
					|| character.hasFetish(Fetish.FETISH_CUM_STUD)
					|| character.hasFetish(Fetish.FETISH_VAGINAL_GIVING)
					|| character.hasFetish(Fetish.FETISH_ANAL_GIVING)) {
				character.setPenisVirgin(false);
			}
			if((character.getGender()==Gender.F_P_TRAP || character.getGender()==Gender.N_P_TRAP) && Math.random()>=0.1f) { // Most traps have a small cock:
				character.setPenisSize(PenisSize.ONE_TINY.getMinimumValue() + Util.random.nextInt(character.getPenisSize().getMaximumValue() - character.getPenisSize().getMinimumValue()) +1);
				character.setTesticleSize(TesticleSize.ONE_TINY.getValue());
				character.setPenisCumStorage(CumProduction.ONE_TRICKLE.getMedianValue());
				
			} else {
				character.setPenisSize(character.getPenisSize().getMinimumValue() + Util.random.nextInt(character.getPenisSize().getMaximumValue() - character.getPenisSize().getMinimumValue()) +1);
				
				int testicleSize = character.getTesticleSize().getValue();
				testicleSize += Util.random.nextInt(3) - 1;
				character.setTesticleSize(testicleSize);
				
				int cumProduction = character.getPenisRawCumStorageValue();
				if(cumProduction>0) {
					cumProduction += Util.random.nextInt(cumProduction) - (cumProduction/2);
					character.setPenisCumStorage(cumProduction);
				}
				character.fillCumToMaxStorage();
			}
			
			if(Math.random()<=0.02f) {
				character.addCumModifier(FluidModifier.ADDICTIVE);
			}
			if(Math.random()<=0.02f) {
				character.addCumModifier(FluidModifier.HALLUCINOGENIC);
			}
		}
		
		// Vagina:
		if(character.hasVagina()) {
			if(character.hasFetish(Fetish.FETISH_PURE_VIRGIN) && character.getHistory()!=Occupation.NPC_PROSTITUTE) {
				character.setVaginaVirgin(true);
				int capacity = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.TWO_TIGHT.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				character.setVaginaCapacity(capacity, true);
				
			} else {
				if(Math.random()>0.99f || character.getHistory()==Occupation.NPC_PROSTITUTE) {
					character.setVaginaVirgin(false);
					character.setVaginaCapacity(character.getVaginaRawCapacityValue()*1.2f, true);
					character.setVaginaStretchedCapacity(character.getVaginaRawCapacityValue());
				} else {
					character.setVaginaVirgin(true);
				}
			}
			character.setVaginaWetness(character.getVaginaWetness().getValue() -1 + Util.random.nextInt(3)); // +1 or -1 either way
			if(Math.random()<=0.02f) {
				character.addGirlcumModifier(FluidModifier.ADDICTIVE);
			}
			if(Math.random()<=0.02f) {
				character.addGirlcumModifier(FluidModifier.HALLUCINOGENIC);
			}
		}
		
		character.setAssStretchedCapacity(character.getAssRawCapacityValue());
		character.setNippleStretchedCapacity(character.getNippleRawCapacityValue());
		character.setFaceStretchedCapacity(character.getFaceRawCapacityValue());
		character.setPenisStretchedCapacity(character.getPenisRawCapacityValue());
		character.setVaginaStretchedCapacity(character.getVaginaRawCapacityValue());
		
		character.getSubspecies().applySpeciesChanges(character.getBody());
		character.getBody().calculateRace(character);
	}
	
	public static void generateItemsInInventory(NPC character) {
		List<AbstractCoreItem> items;
		List<AbstractCoreItem> itemsRemoved = new ArrayList<>();
		
		for(int i=0; i<Util.random.nextInt(4)+2; i++) {
			items = new ArrayList<>(character.getLootItems());
			if(!items.isEmpty()) {
				AbstractCoreItem item = items.get(Util.random.nextInt(items.size()));
				if(!itemsRemoved.contains(item)) {
					if(item instanceof AbstractItem
							&& ((AbstractItem)item).getItemType() == ItemType.getLoreBook(character.getSubspecies())) {
						itemsRemoved.add(item);
					}
					if (item instanceof AbstractItem) {
						character.addItem((AbstractItem) item, false);
						
					} else if (item instanceof AbstractClothing) {
						character.addClothing((AbstractClothing) item, false);
						
					} else if (item instanceof AbstractWeapon) {
						character.addWeapon((AbstractWeapon) item, false);
					}
				}
			}
		}
	}
	
	/**
	 * Sets the History for the supplied character.
	 * @param character
	 */
	public static void setHistoryAndPersonality(GameCharacter character, boolean lowlife) {

		 //TODO Set personality based on history. (Or vice-versa, but one should lead to the other.)
		
		if(lowlife) {
			double prostituteChance = 0.15f; // Base 0.15% chance for any random to be a prostitute.
			 			
			 if(character.isFeminine()) {
				prostituteChance += 0.10f; // Bonus for femininity
			 }
			 
			 prostituteChance += Math.min((character.body.getBreast().getRawSizeValue()-7)*0.02f, 0.35f); // Compare breast size to average.
			 
			 if(character.hasPenis()) {
				prostituteChance += Math.min((character.body.getPenis().getRawSizeValue()-5)*0.01f, 0.10f); // Scaling based off of cock size. Very small cocks are a penalty.
			 } 
			 
			 if(character.hasVagina()) {
				prostituteChance += 0.15f; // Bonus for vagina.
			 }
			 
			 if(character.body.getBreast().getNipples().getOrificeNipples().getRawCapacityValue() >= 4) {
				prostituteChance += 0.05f; //Bonus for fuckable nipples.
			 }
			 
			 if(character.hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
				prostituteChance = 0.03f; // addFetishes() can be called before or after this method. This is a catch for the case where addFetishes() is called before.
			 }
			 
			 prostituteChance = Math.min(prostituteChance, 0.3f); // Prostitutes can only ever spawn at a maximum of a 30% chance.

			if (Math.random() < prostituteChance) {
				character.setHistory(Occupation.NPC_PROSTITUTE);

				character.setAssVirgin(false);
				character.setAssCapacity(character.getAssRawCapacityValue()
						* 1.2f,
						true);
				character.setAssStretchedCapacity(character.getAssRawCapacityValue());

				if (character.hasVagina()) {
					character.setVaginaVirgin(false);
					character.setVaginaCapacity(character.getVaginaRawCapacityValue()
							* 1.2f,
							true);
					character.setVaginaStretchedCapacity(character.getVaginaRawCapacityValue());
				}

				character.setPenisVirgin(false);

				character.setSexualOrientation(SexualOrientation.AMBIPHILIC);
				character.setName(Name.getRandomProstituteTriplet());
				character.useItem(AbstractItemType.generateItem(ItemType.PROMISCUITY_PILL),
						character,
						false);

			} else {
				character.setHistory(Occupation.NPC_MUGGER);
			}
			
		} else {
			List<Occupation> histories = Occupation.getAvailableHistories(character);
			histories.removeIf((his) -> his.isLowlife());
			character.setHistory(Util.randomItemFrom(histories));
		}
			
	}
	
	private static List<Fetish> getAllowedFetishes(GameCharacter character) {
		List<Fetish> allowedFetishes = new ArrayList<>();
		
		for(Fetish f : Fetish.values()) {
			if (f==Fetish.FETISH_PURE_VIRGIN) {
				if(character.hasVagina() && (character.getHistory()!=Occupation.NPC_PROSTITUTE?Math.random()<=0.25f:true)) // 25% chance for prostitutes, as when drawn from amongst all the other fetishes, the actual chance will be much lower.
					allowedFetishes.add(f);
				
			} else if (f==Fetish.FETISH_BIMBO) {
				if(character.isFeminine())
					allowedFetishes.add(f);
				
			} else if (f==Fetish.FETISH_PREGNANCY || f==Fetish.FETISH_VAGINAL_RECEIVING) {
				if(character.hasVagina())
					allowedFetishes.add(f);
				
			} else if (f==Fetish.FETISH_IMPREGNATION) {
				if(character.hasPenis() && character.sexualOrientation!=SexualOrientation.ANDROPHILIC)
					allowedFetishes.add(f);
				
			} else if (f==Fetish.FETISH_CUM_STUD || f==Fetish.FETISH_PENIS_GIVING) {
				if(character.hasPenis())
					allowedFetishes.add(f);
				
			} else if (f==Fetish.FETISH_BREASTS_SELF) {
				if(character.hasBreasts())
					allowedFetishes.add(f);
				
			// Fetishes for content locks:
			} else if (f==Fetish.FETISH_NON_CON_DOM || f==Fetish.FETISH_NON_CON_SUB) {
				if(Main.getProperties().hasValue(PropertyValue.nonConContent)) {
					allowedFetishes.add(f);
				}
				
			} else if (f==Fetish.FETISH_INCEST) {
				if(Main.getProperties().hasValue(PropertyValue.incestContent))
					allowedFetishes.add(f);
				
			} else if (f==Fetish.FETISH_LACTATION_OTHERS || f==Fetish.FETISH_LACTATION_SELF) {
				if(Main.getProperties().hasValue(PropertyValue.lactationContent))
					allowedFetishes.add(f);
				
			} else if (f.getFetishesForAutomaticUnlock().isEmpty()){
				allowedFetishes.add(f);
			}
		}
		
		return allowedFetishes;
	}
	
	public static void addFetishes(GameCharacter character) {
		
		List<Fetish> availableFetishes = getAllowedFetishes(character);
		
		// Remove existing fetishes:
		availableFetishes.removeAll(character.getFetishes(false));
		
		int[] numberProb = new int[] {1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5};
		int numberOfFetishes = Util.randomItemFrom(numberProb) - character.getFetishes(false).size();
		
		int fetishesAssigned = 0;
		
		if(((character.getMother()!=null && character.getMother().isPlayer()) || (character.getFather()!=null && character.getFather().isPlayer()))) {
			if(Main.getProperties().hasValue(PropertyValue.incestContent) && Math.random()>0.5f) {
				character.addFetish(Fetish.FETISH_INCEST);
				availableFetishes.remove(Fetish.FETISH_INCEST);
				fetishesAssigned++;
			}
		} else { // If not offspring, give them the chance for TF fetish and the Kink giving fetish:
			if(Math.random() < (Main.getProperties().forcedTFPercentage/100f)) {
				character.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);
				fetishesAssigned++;
			}
			availableFetishes.remove(Fetish.FETISH_TRANSFORMATION_GIVING);
			
			if(Math.random() < (Main.getProperties().forcedFetishPercentage/100f)) {
				character.addFetish(Fetish.FETISH_KINK_GIVING);
				fetishesAssigned++;
			}
			availableFetishes.remove(Fetish.FETISH_KINK_GIVING);
		}
		
		if(character.getRace()==Race.COW_MORPH && availableFetishes.contains(Fetish.FETISH_BREASTS_SELF)) {
			availableFetishes.add(Fetish.FETISH_BREASTS_SELF);
			availableFetishes.add(Fetish.FETISH_BREASTS_SELF);
			availableFetishes.add(Fetish.FETISH_BREASTS_SELF);
			availableFetishes.add(Fetish.FETISH_BREASTS_SELF);
			availableFetishes.add(Fetish.FETISH_BREASTS_SELF);
		}
		
		if(!Main.getProperties().hasValue(PropertyValue.analContent)) {
			availableFetishes.remove(Fetish.FETISH_ANAL_GIVING);
			availableFetishes.remove(Fetish.FETISH_ANAL_RECEIVING);
		}

		if(!Main.getProperties().hasValue(PropertyValue.footContent)) {
			availableFetishes.remove(Fetish.FETISH_FOOT_GIVING);
			availableFetishes.remove(Fetish.FETISH_FOOT_RECEIVING);
		}
		
		while(fetishesAssigned < numberOfFetishes && !availableFetishes.isEmpty()) {
			Fetish f = Util.randomItemFrom(availableFetishes);
			character.addFetish(f);
			while(availableFetishes.remove(f)) {}
			fetishesAssigned++;
		}
		
		if(character.getRace()==Race.RABBIT_MORPH && Math.random()<0.8f && character.hasVagina()) {
			character.addFetish(Fetish.FETISH_PREGNANCY);
		}
		if(character.getRace()==Race.RABBIT_MORPH && Math.random()<0.8f && character.hasPenis()) {
			character.addFetish(Fetish.FETISH_IMPREGNATION);
		}
		
		generateDesires(character);
	}
	
	public static void generateDesires(GameCharacter character) {
		
		List<Fetish> availableFetishes = getAllowedFetishes(character);
		availableFetishes.removeAll(character.getFetishes(false));
		availableFetishes.removeIf((f) -> !f.getFetishesForAutomaticUnlock().isEmpty()); //Do not allow derived fetishes
		for(Fetish f : character.getFetishes(false)) {
			switch(f) {
				default:
					break;
				// Related fetishes cannot be loved and disliked at the same time:
				case FETISH_PREGNANCY:
					availableFetishes.remove(Fetish.FETISH_VAGINAL_RECEIVING);
					break;
				case FETISH_IMPREGNATION:
					availableFetishes.remove(Fetish.FETISH_VAGINAL_GIVING);
					break;
			}
		}

		if(!Main.getProperties().hasValue(PropertyValue.analContent)) {
			availableFetishes.remove(Fetish.FETISH_ANAL_GIVING);
			availableFetishes.remove(Fetish.FETISH_ANAL_RECEIVING);
		}

		if(!Main.getProperties().hasValue(PropertyValue.footContent)) {
			availableFetishes.remove(Fetish.FETISH_FOOT_GIVING);
			availableFetishes.remove(Fetish.FETISH_FOOT_RECEIVING);
		}
		
		// Desires:
		int[] posDesireProb = new int[] {1, 1, 2, 2, 2, 3, 3};
		int[] negDesireProb = new int[] {3, 3, 4, 4, 4, 5, 5};
		int numberOfPositiveDesires = Util.randomItemFrom(posDesireProb);
		int numberOfNegativeDesires = Util.randomItemFrom(negDesireProb);
		
		int desiresAssigned = 0;
		List<Fetish> fetishesLiked = new ArrayList<>(character.getFetishes(false));
		while(desiresAssigned < numberOfPositiveDesires && !availableFetishes.isEmpty()) {
			Fetish f = Util.randomItemFrom(availableFetishes);
			character.setFetishDesire(f, FetishDesire.THREE_LIKE);
			availableFetishes.remove(f);
			fetishesLiked.add(f);
			desiresAssigned++;
		}
		
		if(character.getRace()==Race.RABBIT_MORPH && Math.random()<0.8f && character.hasVagina() && availableFetishes.contains(Fetish.FETISH_PREGNANCY)) {
			character.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.THREE_LIKE);
		}
		if(character.getRace()==Race.RABBIT_MORPH && Math.random()<0.8f && character.hasPenis() && availableFetishes.contains(Fetish.FETISH_IMPREGNATION)) {
			character.setFetishDesire(Fetish.FETISH_IMPREGNATION, FetishDesire.THREE_LIKE);
		}
		
		
		// Disliked fetishes:
		// Related fetishes cannot be liked and disliked at the same time:
		for(Fetish f : fetishesLiked) {
			switch(f) {
				default:
					break;
				case FETISH_VAGINAL_RECEIVING:
					availableFetishes.remove(Fetish.FETISH_PENIS_RECEIVING);
					break;
				case FETISH_VAGINAL_GIVING:
					availableFetishes.remove(Fetish.FETISH_PENIS_GIVING);
					break;
				case FETISH_PREGNANCY:
					availableFetishes.remove(Fetish.FETISH_VAGINAL_RECEIVING);
					availableFetishes.remove(Fetish.FETISH_PENIS_RECEIVING);
					availableFetishes.remove(Fetish.FETISH_CUM_ADDICT);
					break;
				case FETISH_IMPREGNATION:
					availableFetishes.remove(Fetish.FETISH_VAGINAL_GIVING);
					availableFetishes.remove(Fetish.FETISH_PENIS_GIVING);
					availableFetishes.remove(Fetish.FETISH_CUM_STUD);
					break;
				case FETISH_NON_CON_SUB:
					availableFetishes.remove(Fetish.FETISH_SUBMISSIVE);
					break;
			}
		}
		
		desiresAssigned = 0;
		if(character instanceof Cultist || character instanceof DominionSuccubusAttacker) { // Cultists and succubus attackers like raping
			availableFetishes.remove(Fetish.FETISH_NON_CON_DOM);
		}
		if(character.getSexualOrientation()!=SexualOrientation.GYNEPHILIC) {
			availableFetishes.remove(Fetish.FETISH_PENIS_RECEIVING);
		}
		if(character.getSexualOrientation()!=SexualOrientation.ANDROPHILIC) {
			availableFetishes.remove(Fetish.FETISH_VAGINAL_GIVING);
			availableFetishes.remove(Fetish.FETISH_BREASTS_OTHERS);
		}
		if(character.getRace()==Race.RABBIT_MORPH) {
			availableFetishes.remove(Fetish.FETISH_PREGNANCY);
			availableFetishes.remove(Fetish.FETISH_IMPREGNATION);
		}
		
		// Remove 'natural' fetish dislikes:
		availableFetishes.remove(Fetish.FETISH_CUM_STUD);
		availableFetishes.remove(Fetish.FETISH_PENIS_GIVING);
		availableFetishes.remove(Fetish.FETISH_VAGINAL_RECEIVING);
		
		while(desiresAssigned < numberOfNegativeDesires && !availableFetishes.isEmpty()) {
			Fetish f = Util.randomItemFrom(availableFetishes);
			character.setFetishDesire(f, Math.random()>0.5?FetishDesire.ONE_DISLIKE:FetishDesire.ZERO_HATE);
			if(f == Fetish.FETISH_DOMINANT) {
				availableFetishes.remove(Fetish.FETISH_SUBMISSIVE);
			}
			if(f == Fetish.FETISH_SUBMISSIVE) {
				availableFetishes.remove(Fetish.FETISH_DOMINANT);
			}
			availableFetishes.remove(f);
			desiresAssigned++;
		}
	}
	

	public static void equipClothingFromOutfitFolderId(GameCharacter character, OutfitType outfitType, String folderId, List<EquipClothingSetting> settings) {
		equipClothingFromOutfits(character, OutfitType.getOutfitsFromIdStart(folderId), outfitType, settings);
	}

	public static void equipClothingFromOutfitType(GameCharacter character, OutfitType outfitType, List<EquipClothingSetting> settings) {
		equipClothingFromOutfits(character, OutfitType.getAllOutfits(), outfitType, settings);
	}

	private static void equipClothingFromOutfits(GameCharacter character, List<AbstractOutfit> availableOutfits, OutfitType outfitType, List<EquipClothingSetting> settings) {
		Map<AbstractOutfit, Integer> weightedOutfits = new HashMap<>();
		
		for(AbstractOutfit outfit : availableOutfits) {
			if(outfit.isAvailableForCharacter(outfitType, character)) {
				weightedOutfits.put(outfit, outfit.getWeight());
			}
		}
		
		if(weightedOutfits.isEmpty()) {
			equipClothingFromOutfit(character, null, settings);
		} else {
			equipClothingFromOutfit(character, Util.getRandomObjectFromWeightedMap(weightedOutfits), settings);
		}
	}

	public static void equipClothingFromOutfitId(GameCharacter character, String outfitId, List<EquipClothingSetting> settings) {
		equipClothingFromOutfit(character, OutfitType.getOutfitTypeFromId(outfitId), settings);
	}
	
	public static void equipClothingFromOutfit(GameCharacter character, AbstractOutfit outfit, List<EquipClothingSetting> settings) {
		if(outfit!=null) {
			try {
				outfit.applyOutfit(character, settings);
				return;
			} catch (XMLLoadException e) {
				System.err.println("Outfit '"+outfit.getName()+"' could not be applied in CharacterUtils equipClothing(). Proceeding to randomly generate outfit...");
			}
		}
		
		Colour primaryColour = ColourListPresets.ALL.get(Util.random.nextInt(ColourListPresets.ALL.size())),
				secondaryColour = ColourListPresets.ALL.get(Util.random.nextInt(ColourListPresets.ALL.size())),
				lingerieColour = ColourListPresets.LINGERIE.get(Util.random.nextInt(ColourListPresets.LINGERIE.size()));
		
		// Remove exposing underwear if replaceUnsuitableClothing and is exposed:
		if(settings.contains(EquipClothingSetting.REPLACE_CLOTHING)
				&& character.getClothingInSlot(InventorySlot.GROIN)!=null
				&& (character.hasStatusEffect(StatusEffect.EXPOSED) || character.hasStatusEffect(StatusEffect.EXPOSED_PLUS_BREASTS))) {
			character.unequipClothingIntoVoid(character.getClothingInSlot(InventorySlot.GROIN), true, character);
		}
		
		List<InventorySlot> inventorySlotsInPriorityOrder = new ArrayList<>();
		inventorySlotsInPriorityOrder.add(InventorySlot.TORSO_UNDER); // Torso needs to be randomly decided first, to give girls a chance to wear a dress.
		for(InventorySlot slot : InventorySlot.values()) {
			if(slot!=InventorySlot.TORSO_UNDER) {
				inventorySlotsInPriorityOrder.add(slot);
			}
		}
		
		if((character.isFeminine() && !character.hasFetish(Fetish.FETISH_CROSS_DRESSER)) || (!character.isFeminine() && character.hasFetish(Fetish.FETISH_CROSS_DRESSER))) {
			for(InventorySlot slot : inventorySlotsInPriorityOrder) {
				if(settings.contains(EquipClothingSetting.REPLACE_CLOTHING)) {
					if(character.getClothingInSlot(slot)!=null) {
						if(character.getClothingInSlot(slot).getClothingType().getFemininityRestriction() == Femininity.MASCULINE) {
							character.unequipClothingIntoVoid(character.getClothingInSlot(slot), true, character);
						}
					}
				}
				
				if(slot==InventorySlot.LEG
							&& character.getClothingInSlot(InventorySlot.TORSO_UNDER)!=null
							&& character.getClothingInSlot(InventorySlot.TORSO_UNDER).getItemTags().contains(ItemTag.DRESS)) {
					// Don't add leg clothing if dress has been added
				} else {
					if((slot.isCoreClothing() || Math.random()>0.75f || (slot.isJewellery() && character.getBodyMaterial().isRequiresPiercing())) && !character.isSlotIncompatible(slot) && character.getClothingInSlot(slot)==null) {
						List<AbstractClothingType> clothingToUse = ClothingType.getCommonClothingMapFemaleIncludingAndrogynous().get(slot);
						if(character.getHistory()==Occupation.NPC_PROSTITUTE) {
							clothingToUse = ClothingType.getSuitableFeminineClothing().get(Occupation.NPC_PROSTITUTE);
						}
						if(!clothingToUse.isEmpty()) {
							BodyPartClothingBlock block = slot.getBodyPartClothingBlock(character);
							clothingToUse = clothingToUse.stream().filter((c) ->
								!c.isCondom(slot)
									&& (block==null || !Collections.disjoint(c.getItemTags(slot), block.getRequiredTags()))
									&& (!character.hasBreastsCrotch()
											|| character.getLegConfiguration()==LegConfiguration.TAUR  // Taurs crotch boobs are not concealed by stomach clothing, so don't bother
											|| c.isConcealsSlot(character, c.getEquipSlots().get(0), InventorySlot.STOMACH)
											|| c.getEquipSlots().get(0)!=InventorySlot.TORSO_UNDER || c.getEquipSlots().get(0)!=InventorySlot.TORSO_OVER
											|| character.getInventorySlotsConcealed().containsKey(InventorySlot.STOMACH))
								).collect(Collectors.toList());
							
							
							if(!clothingToUse.isEmpty()) {
								AbstractClothingType ct = getClothingTypeForSlot(character, slot, clothingToUse);
								
								if(ct!=null) {
									AbstractClothing clothingToAdd = AbstractClothingType.generateClothing(
											ct,
											(slot == InventorySlot.GROIN || slot==InventorySlot.CHEST || slot==InventorySlot.SOCK
													? ct.getAvailablePrimaryColours().contains(lingerieColour)?lingerieColour:ct.getAvailablePrimaryColours().get(Util.random.nextInt(ct.getAvailablePrimaryColours().size()))
													: (slot.isCoreClothing()
															?ct.getAvailablePrimaryColours().contains(primaryColour)?primaryColour:ct.getAvailablePrimaryColours().get(Util.random.nextInt(ct.getAvailablePrimaryColours().size()))
															:ct.getAvailablePrimaryColours().contains(secondaryColour)?secondaryColour:ct.getAvailablePrimaryColours().get(Util.random.nextInt(ct.getAvailablePrimaryColours().size())))),
											false);
									character.equipClothingFromNowhere(clothingToAdd, slot, true, character);
								}
							}
						}
					}
				}
			}
			
		} else {
			for(InventorySlot slot : inventorySlotsInPriorityOrder) {
				if(settings.contains(EquipClothingSetting.REPLACE_CLOTHING)) {
					if(character.getClothingInSlot(slot)!=null) {
						if(character.getClothingInSlot(slot).getClothingType().getFemininityRestriction() == Femininity.FEMININE) {
							character.unequipClothingIntoVoid(character.getClothingInSlot(slot), true, character);
						}
					}
				}
				
				if(slot==InventorySlot.LEG
							&& character.getClothingInSlot(InventorySlot.TORSO_UNDER)!=null
							&& character.getClothingInSlot(InventorySlot.TORSO_UNDER).getClothingType().toString().contains("DRESS")) {//TODO please don't do this //BE QUIET
					// Don't add leg clothing if dress has been added
					
				} else {
					if((slot.isCoreClothing() || Math.random()>0.75f || (slot.isJewellery() && character.getBodyMaterial().isRequiresPiercing())) && !character.isSlotIncompatible(slot) && character.getClothingInSlot(slot)==null) {
						
						List<AbstractClothingType> clothingToUse = ClothingType.getCommonClothingMapMaleIncludingAndrogynous().get(slot);
						
						if(!clothingToUse.isEmpty()) {
							BodyPartClothingBlock block = slot.getBodyPartClothingBlock(character);
							clothingToUse = clothingToUse.stream().filter((c) ->
								!c.isCondom(slot)
								&& (block==null || !Collections.disjoint(c.getItemTags(slot), block.getRequiredTags()))
									&& (!character.hasBreastsCrotch()
											|| character.getLegConfiguration()==LegConfiguration.TAUR  // Taurs crotch boobs are not concealed by stomach clothing, so don't bother
											|| c.isConcealsSlot(character, c.getEquipSlots().get(0), InventorySlot.STOMACH)
											|| c.getEquipSlots().get(0)!=InventorySlot.TORSO_UNDER || c.getEquipSlots().get(0)!=InventorySlot.TORSO_OVER
											|| character.getInventorySlotsConcealed().containsKey(InventorySlot.STOMACH))
								).collect(Collectors.toList());
							
							if(!clothingToUse.isEmpty()) {
								AbstractClothingType ct = getClothingTypeForSlot(character, slot, clothingToUse);
								
								if(ct!=null) {
									AbstractClothing clothingToAdd = AbstractClothingType.generateClothing(
											ct,
											(slot == InventorySlot.GROIN || slot==InventorySlot.CHEST || slot==InventorySlot.SOCK
													?  ct.getAvailablePrimaryColours().contains(lingerieColour)?lingerieColour:ct.getAvailablePrimaryColours().get(Util.random.nextInt(ct.getAvailablePrimaryColours().size()))
															: (slot.isCoreClothing()
																	?ct.getAvailablePrimaryColours().contains(primaryColour)?primaryColour:ct.getAvailablePrimaryColours().get(Util.random.nextInt(ct.getAvailablePrimaryColours().size()))
																	:ct.getAvailablePrimaryColours().contains(secondaryColour)?secondaryColour:ct.getAvailablePrimaryColours().get(Util.random.nextInt(ct.getAvailablePrimaryColours().size())))),
											false);
									character.equipClothingFromNowhere(clothingToAdd, slot, true, character);
								}
							}
								
						}
					}
				}
			}
		}
	}
	
	public static void equipPiercings(GameCharacter character, boolean replaceUnsuitableClothing) {
		 Map<InventorySlot, List<AbstractClothingType>> clothingMap = ClothingType.getCommonClothingMapMaleIncludingAndrogynous();
		 
		if(character.isFeminine() || character.hasFetish(Fetish.FETISH_CROSS_DRESSER)) {
			clothingMap = ClothingType.getCommonClothingMapFemaleIncludingAndrogynous();
		}
		
		for(InventorySlot slot : InventorySlot.getPiercingSlots()) {
			if(replaceUnsuitableClothing) {
				if(character.getClothingInSlot(slot)!=null) {
					if(character.getClothingInSlot(slot).getClothingType().getFemininityRestriction() == Femininity.MASCULINE) {
						character.unequipClothingIntoVoid(character.getClothingInSlot(slot), true, character);
					}
				}
			}
			
			if(!character.isSlotIncompatible(slot) && character.getClothingInSlot(slot)==null) {
				if(!clothingMap.get(slot).isEmpty()) {

					BodyPartClothingBlock block = slot.getBodyPartClothingBlock(character);
					List<AbstractClothingType> clothingToUse = clothingMap.get(slot);
					clothingToUse = clothingToUse.stream().filter((c) -> (block==null || !Collections.disjoint(c.getItemTags(slot), block.getRequiredTags()))).collect(Collectors.toList());
					
					AbstractClothingType ct = getClothingTypeForSlot(character, slot, clothingToUse);
					
					if(ct!=null) {
						character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ct, false), slot, true, character);
					}
				}
			}
		}
	}
	
	private static AbstractClothingType getClothingTypeForSlot(GameCharacter character, InventorySlot slot, List<AbstractClothingType> clothingOptions) {
		List<AbstractClothingType> availableClothing = new ArrayList<>();

		boolean canEquip=true;
		
		for(AbstractClothingType ct : clothingOptions) {
			if(!ct.getEquipSlots().contains(slot)) {
				continue;
			}
			canEquip=true;
			
			if(slot==InventorySlot.CHEST && !character.hasBreasts()) {
				canEquip = false;
				
			} else if(character.hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
				for(BlockedParts bp : ct.getBlockedPartsMap(character, slot)) {
					boolean leavesAnusExposed = character.isCoverableAreaExposed(CoverableArea.ANUS) && !bp.blockedBodyParts.contains(CoverableArea.ANUS);
					boolean leavesNipplesExposed = character.isCoverableAreaExposed(CoverableArea.NIPPLES) && !bp.blockedBodyParts.contains(CoverableArea.NIPPLES);
					boolean leavesPenisExposed = !character.hasPenis() || (character.isCoverableAreaExposed(CoverableArea.PENIS) && !bp.blockedBodyParts.contains(CoverableArea.PENIS));
					boolean leavesVaginaExposed = !character.hasVagina() || (character.isCoverableAreaExposed(CoverableArea.VAGINA) && !bp.blockedBodyParts.contains(CoverableArea.VAGINA));
					//TODO check this:
					if(!ct.isTransparent(slot) && (!leavesNipplesExposed || (!leavesAnusExposed || (!leavesPenisExposed && !leavesVaginaExposed)))) {
						canEquip = false;
					}
				}
				
				
			} else {
				for(InventorySlot is : ct.getIncompatibleSlots(character, slot)) {
					if(character.getClothingInSlot(is) != null) {
						canEquip = false;
					}
				}
			}
			
			if(canEquip) {
				availableClothing.add(ct);
			}
		}
		
		if(availableClothing.isEmpty()) {
			return null;
			
		} else {
			return Util.randomItemFrom(availableClothing);
		}
	}
	
	public static void applyMakeup(GameCharacter character, boolean overrideExistingMakeup) {
		if((character.isFeminine() && !character.hasFetish(Fetish.FETISH_CROSS_DRESSER)) || (!character.isFeminine() && character.hasFetish(Fetish.FETISH_CROSS_DRESSER))) {
			List<Colour> colours = Util.newArrayListOfValues(
					Colour.COVERING_NONE,
					Colour.COVERING_CLEAR,
					Colour.COVERING_RED,
					Colour.COVERING_PINK,
					Colour.COVERING_BLUE);
			
			if(character.getHistory()==Occupation.NPC_PROSTITUTE) {
				colours.remove(Colour.COVERING_NONE);
				colours.remove(Colour.COVERING_CLEAR);
			}
			
			Colour colourForCoordination = Util.randomItemFrom(colours);
			Colour colourForNails = Util.randomItemFrom(colours);
			
			character.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, colourForCoordination));
			character.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
			character.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, colourForCoordination));
			character.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, colourForCoordination));
			
			character.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, colourForNails));
			character.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, colourForNails));
			
		} else {
			// Masculine characters
		}
	}

	public static int getProstitutePrice(GameCharacter prostitute) {
		double prostitutePrice = 0.25f; // Base 0.25% chance for any random to be a prostitute.

		if (prostitute.isFeminine()) {
			prostitutePrice += 0.10f;
		}
		
		prostitutePrice += (prostitute.getBody().getBreast().getRawSizeValue()- 7)* 0.02f; // Breast size.

		if (prostitute.hasVagina()) {
			prostitutePrice += 0.15f; // More expensive if prostitute has a vagina.
		}

		if (prostitute.hasPenis()) {
			prostitutePrice += Math.min((prostitute.getBody().getPenis().getRawSizeValue() - 5) * 0.01f, 0.10f); // Penalises small penises, but adds price if penis is large.
		}

		if (prostitute.getBody().getBreast().getNipples().getOrificeNipples().getRawCapacityValue() >= 4) {
			prostitutePrice += 0.05f;  // Fuckable nipples add to price.
		}

		if (prostitute.isVisiblyPregnant()) {
			prostitutePrice = prostitutePrice * 0.8f; // Pregnant prostitutes charge 80% of their usual price.
		}

		return Math.max(150, (int) (prostitutePrice * 750)); // Minimum value is 150 flames.
	}

	
//	private static void equipPreset(GameCharacter character, boolean replaceUnsuitableClothing, boolean onlyAddCoreClothing) {
//		boolean feminineClothing = (character.isFeminine() && !character.hasFetish(Fetish.FETISH_CROSS_DRESSER)) || (!character.isFeminine() && character.hasFetish(Fetish.FETISH_CROSS_DRESSER));
//		
//		switch(character.getHistory()) {
//			case PROSTITUTE:
//				if(feminineClothing) {
//					equipIfNothingInSlot(character, );
//				} else {
//					
//				}
//				break;
//			default:
//				break;
//		}
//	}
//	
//	private static void equipIfNothingInSlot(GameCharacter character, AbstractClothing clothing) {
//		if(character.getClothingInSlot(clothing.getClothingType().getSlot()) == null) {
//			character.equipClothingFromNowhere(clothing, true, character);
//		}
//	}
}
