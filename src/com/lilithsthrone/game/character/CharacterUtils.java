package com.lilithsthrone.game.character;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.body.Antenna;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.Ass;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.Breast;
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
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.character.npc.dominion.DominionSuccubusAttacker;
import com.lilithsthrone.game.character.persona.History;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.BlockedParts;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.ColourListPresets;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.67
 * @version 0.2.4
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
			character.saveAsXML(properties, doc);
			
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
			String saveLocation = "data/characters/"+character.getName()+"_day"+Main.game.getDayNumber()+".xml";
			if(new File("data/characters/"+character.getName()+"_day"+Main.game.getDayNumber()+".xml").exists())
				saveLocation = "data/characters/"+character.getName()+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml";

			while(new File("data/characters/"+character.getName()+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml").exists()) {
				saveNumber++;
				saveLocation = "data/characters/"+character.getName()+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml";
			}
			
			StreamResult result = new StreamResult(new File(saveLocation));
			
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
		return new PlayerCharacter(new NameTriplet("Player"), 1, Gender.M_P_MALE, RacialBody.HUMAN, RaceStage.HUMAN, null, WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME);
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
	
	
	public static Body generateBody(Gender startingGender, GameCharacter mother, GameCharacter father) {
		RacialBody startingBodyType = RacialBody.HUMAN;
		RacialBody motherBody = RacialBody.valueOfRace(mother.getSubspecies().getOffspringSubspecies().getRace());
		RacialBody fatherBody = RacialBody.valueOfRace(father.getSubspecies().getOffspringSubspecies().getRace());
		Subspecies raceTakesAfter = mother.getSubspecies();
		RaceStage stage = RaceStage.HUMAN;
		boolean takesAfterMother = true;
		boolean raceFromMother = true;
		boolean feminineGender = startingGender.isFeminine();
		NPC blankNPC = Main.game.getGenericAndrogynousNPC();
		GameCharacter parentTakesAfter = mother;
		
		// Core body type is random:
		if(Math.random()<=0.5 || mother.getSubspecies().isOffspringAlwaysMothersRace()) {
			startingBodyType = motherBody;
			stage = mother.getRaceStage();
		} else {
			startingBodyType = fatherBody;
			stage = father.getRaceStage();
			raceTakesAfter = father.getSubspecies();
			raceFromMother = false;
		}
		
		switch(startingGender.isFeminine()
				?Main.getProperties().subspeciesFeminineFurryPreferencesMap.get(raceTakesAfter)
				:Main.getProperties().subspeciesMasculineFurryPreferencesMap.get(raceTakesAfter)) {
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
		
		Body body = generateBody(startingGender, startingBodyType, stage);

		body.setBodyMaterial(mother.getBodyMaterial());
		
		// Genetics! (Sort of...)
		
		// Takes other features from the parent closest to their femininity:
		if(Math.abs(mother.getFemininityValue()-body.getFemininity()) > Math.abs(father.getFemininityValue()-body.getFemininity())) {
			takesAfterMother = false;
			parentTakesAfter = father;
		}
		
		float takesAfterMotherChance = takesAfterMother?0.75f:0.25f;
		
		List<BodyCoveringType> typesToInfluence = new ArrayList<>();
		// Skin & fur colours:
		for(BodyPartInterface bp : body.getAllBodyParts()){
			if(bp.getType().getBodyCoveringType(body)!=null
					&& !(bp instanceof Eye)) {
				typesToInfluence.add(bp.getType().getBodyCoveringType(body));
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
		if(Math.random()>=0.9f) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getCoverings().put(body.getEye().getType().getBodyCoveringType(body),
						new Covering(body.getEye().getType().getBodyCoveringType(body), mother.getCovering(mother.getEyeType().getBodyCoveringType(mother)).getPattern(),
								mother.getCovering(mother.getEyeType().getBodyCoveringType(mother)).getPrimaryColour(), mother.getCovering(mother.getEyeType().getBodyCoveringType(mother)).isPrimaryGlowing(),
								mother.getCovering(mother.getEyeType().getBodyCoveringType(mother)).getPrimaryColour(), mother.getCovering(mother.getEyeType().getBodyCoveringType(mother)).isPrimaryGlowing()));
			} else {
				body.getCoverings().put(body.getEye().getType().getBodyCoveringType(body),
						new Covering(body.getEye().getType().getBodyCoveringType(body), father.getCovering(father.getEyeType().getBodyCoveringType(father)).getPattern(),
								father.getCovering(father.getEyeType().getBodyCoveringType(father)).getPrimaryColour(), father.getCovering(father.getEyeType().getBodyCoveringType(father)).isPrimaryGlowing(),
								father.getCovering(father.getEyeType().getBodyCoveringType(father)).getPrimaryColour(), father.getCovering(father.getEyeType().getBodyCoveringType(father)).isPrimaryGlowing()));
			}
		}
		// Pupil colour:
		if(Math.random()>=0.4f) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getCoverings().put(BodyCoveringType.EYE_PUPILS,
						new Covering(body.getEye().getType().getBodyCoveringType(body), mother.getCovering(BodyCoveringType.EYE_PUPILS).getPattern(),
								mother.getCovering(BodyCoveringType.EYE_PUPILS).getPrimaryColour(), mother.getCovering(BodyCoveringType.EYE_PUPILS).isPrimaryGlowing(),
								mother.getCovering(BodyCoveringType.EYE_PUPILS).getPrimaryColour(), mother.getCovering(BodyCoveringType.EYE_PUPILS).isPrimaryGlowing()));
			} else {
				body.getCoverings().put(BodyCoveringType.EYE_PUPILS,
						new Covering(body.getEye().getType().getBodyCoveringType(body), father.getCovering(BodyCoveringType.EYE_PUPILS).getPattern(),
								father.getCovering(BodyCoveringType.EYE_PUPILS).getPrimaryColour(), father.getCovering(BodyCoveringType.EYE_PUPILS).isPrimaryGlowing(),
								father.getCovering(BodyCoveringType.EYE_PUPILS).getPrimaryColour(), father.getCovering(BodyCoveringType.EYE_PUPILS).isPrimaryGlowing()));
			}
		}
		
		// Body core:
		// Height:
		if(!parentTakesAfter.getSubspecies().getOffspringSubspecies().isShortStature()) {
			body.setHeight(getSizeFromGenetics( //TODO
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
			for(PenisModifier pm : PenisModifier.values()) {
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
			if(Math.random()>=0.75) {
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
			body.getPenis().getTesticle().setCumProduction(blankNPC, getSizeFromGenetics(
					body.getPenis().getTesticle().getRawCumProductionValue(),
					inheritsFromMotherPenis, mother.getPenisRawCumProductionValue(),
					inheritsFromFatherPenis, father.getPenisRawCumProductionValue()));
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
			body.getVagina().setClitorisSize(blankNPC, getSizeFromGenetics(
					body.getVagina().getRawClitorisSizeValue(),
					inheritsFromMotherVagina, mother.getVaginaRawClitorisSizeValue(),
					inheritsFromFatherVagina, father.getVaginaRawClitorisSizeValue()));
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
		
		return body;
	}
	
	private static List<BodyCoveringType> setCoveringColours(Body body, GameCharacter character, List<BodyCoveringType> typesToInfluence) {
		List<BodyCoveringType> tempList = new ArrayList<>(typesToInfluence);
		
		// Skin & fur colours:
		for(BodyPartInterface bp : character.getAllBodyParts()){
			if(bp.getType().getBodyCoveringType(character)!=null
					&& !(bp instanceof Eye)) {
				if(tempList.contains(bp.getType().getBodyCoveringType(character))) {
					Covering covering = character.getCovering(bp.getType().getBodyCoveringType(character));
					body.getCoverings().put(
							bp.getType().getBodyCoveringType(character),
							new Covering(covering.getType(), covering.getPattern(), covering.getModifier(), covering.getPrimaryColour(), covering.isPrimaryGlowing(), covering.getSecondaryColour(), covering.isSecondaryGlowing()));
					tempList.remove(bp.getType().getBodyCoveringType(character));
//					System.out.println("Set: "+bp.getType().getName(character)+" : "+bp.getType().getBodyCoveringType().getName(character)+"("+bp.getType().getRace().getName()+") : "+covering.getPrimaryColour().getName());
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

	public static Body generateBody(Gender startingGender, Subspecies species, RaceStage stage) {
		return generateBody(startingGender, RacialBody.valueOfRace(species.getRace()), species, stage);
	}
	
	public static Body generateBody(Gender startingGender, RacialBody startingBodyType, RaceStage stage) {
		return generateBody(startingGender, startingBodyType, null, stage);
	}
	
	public static Body generateBody(Gender startingGender, RacialBody startingBodyType, Subspecies species, RaceStage stage) {
		
		boolean hasVagina = startingGender.getGenderName().isHasVagina();
		boolean hasPenis = startingGender.getGenderName().isHasPenis();
		boolean hasBreasts = startingGender.getGenderName().isHasBreasts();
		
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
						BreastShape.getRandomBreastShape(),
						(hasBreasts? startingBodyType.getBreastSize() : startingBodyType.getNoBreastSize()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleLactationRate() : startingBodyType.getMaleLactationRate()),
						((stage.isSkinFurry() && Main.getProperties().multiBreasts==1) || (stage.isBreastFurry() && Main.getProperties().multiBreasts==2)
								?(startingGender.isFeminine() ? startingBodyType.getBreastCountFemale() : startingBodyType.getBreastCountMale())
								:1),
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
				new Leg(stage.isLegFurry()?startingBodyType.getLegType():LegType.HUMAN),
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
									startingBodyType.getPenisGirth(),
									startingBodyType.getTesticleSize(),
									startingBodyType.getCumProduction(),
									startingBodyType.getTesticleQuantity())
								: new Penis(PenisType.NONE, 0, 0, 0, 0, 2))
						.horn(new Horn((stage.isHornFurry()?startingBodyType.getRandomHornType(false):HornType.NONE), (startingGender.isFeminine() ? startingBodyType.getFemaleHornLength() : startingBodyType.getMaleHornLength())))
						.antenna(new Antenna(stage.isAntennaFurry()?startingBodyType.getAntennaType():AntennaType.NONE))
						.tail(new Tail(stage.isTailFurry()?startingBodyType.getTailType():TailType.NONE))
						.wing(new Wing((stage.isWingFurry()?startingBodyType.getWingType():WingType.NONE), (startingGender.isFeminine() ? startingBodyType.getFemaleWingSize() : startingBodyType.getMaleWingSize())))
						.build();
		
		// Pubic hair:
		BodyHair hair = BodyHair.getRandomBodyHair();
		body.setPubicHair(hair);
		body.getFace().setFacialHair(null, hair);
		body.getArm().setUnderarmHair(null, hair);
		body.getAss().getAnus().setAssHair(null, hair);
		
		if(species!=null) {
			body.calculateRace();
			species.applySpeciesChanges(body);
			body.calculateRace();
		}
		
		return body;
	}
	
	public static void randomiseBody(GameCharacter character) {
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
		if(character.hasFetish(Fetish.FETISH_ANAL_RECEIVING) || character.getHistory()==History.PROSTITUTE) {
			character.setAssVirgin(false);
			character.setAssCapacity(character.getAssRawCapacityValue()*1.2f, true);
			character.setAssStretchedCapacity(character.getAssRawCapacityValue());
		} else {
			character.setAssVirgin(true);
		}
		
		// Body:
		character.setHeight(character.getHeightValue()-15 + Util.random.nextInt(30) +1);
		
		//Breasts:
		if(Main.getProperties().multiBreasts==0) {
			character.setBreastRows(1);
			
		} else if(Main.getProperties().multiBreasts==1) {
			if(character.getSkinType() == SkinType.HUMAN) {
				character.setBreastRows(1);
			}
		}
		
		if(character.hasBreasts()) {
			character.setBreastSize(Math.max(CupSize.AA.getMeasurement(), character.getBreastSize().getMeasurement() -2 +(Util.random.nextInt(5)))); // Random size between -2 and +2 of base value.
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
		
		// Face:
		if(character.hasFetish(Fetish.FETISH_ORAL_GIVING) || character.getHistory()==History.PROSTITUTE) {
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
		if(Math.random()<=0.2f && !character.getCovering(character.getHairType().getBodyCoveringType(character)).getType().getDyePatterns().isEmpty()) { // 20% chance to have a non-natural hair colour:
			Covering currentCovering = character.getCovering(character.getHairType().getBodyCoveringType(character));
			character.setHairCovering(new Covering(
					currentCovering.getType(),
					currentCovering.getType().getDyePatterns().get(Util.random.nextInt(currentCovering.getType().getDyePatterns().size())),
					currentCovering.getType().getAllPrimaryColours().isEmpty()
						?currentCovering.getPrimaryColour()
						:currentCovering.getType().getAllPrimaryColours().get(Util.random.nextInt(currentCovering.getType().getAllPrimaryColours().size())),
					Math.random()<=0.05f,
					currentCovering.getType().getAllSecondaryColours().isEmpty()
						?currentCovering.getSecondaryColour()
						:currentCovering.getType().getAllSecondaryColours().get(Util.random.nextInt(currentCovering.getType().getAllSecondaryColours().size())),
					Math.random()<=0.05f),
					true);
		}
		if(character.getHairRawLengthValue()!=0) {
			character.setHairLength(character.getHairLength().getMinimumValue() + Util.random.nextInt(character.getHairLength().getMaximumValue() - character.getHairLength().getMinimumValue()) +1);
		}
		
		// Penis:
		if(character.hasPenis()) {
			if(Math.random()<0.15f
					&& character.getHistory()!=History.PROSTITUTE
					&& !character.hasFetish(Fetish.FETISH_CUM_STUD)
					&& !character.hasFetish(Fetish.FETISH_VAGINAL_GIVING)
					&& !character.hasFetish(Fetish.FETISH_ANAL_GIVING)) {
				character.setPenisVirgin(false);
			} else {
				character.setPenisVirgin(true);
			}
			if((character.getGender()==Gender.F_P_TRAP || character.getGender()==Gender.N_P_TRAP) && Math.random()>=0.1f) { // Most traps have a small cock:
				character.setPenisSize(PenisSize.ONE_TINY.getMinimumValue() + Util.random.nextInt(character.getPenisSize().getMaximumValue() - character.getPenisSize().getMinimumValue()) +1);
				character.setTesticleSize(TesticleSize.ONE_TINY.getValue());
				character.setCumProduction(CumProduction.ONE_TRICKLE.getMedianValue());
			} else {
				character.setPenisSize(character.getPenisSize().getMinimumValue() + Util.random.nextInt(character.getPenisSize().getMaximumValue() - character.getPenisSize().getMinimumValue()) +1);
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
			if(character.hasFetish(Fetish.FETISH_PURE_VIRGIN) && character.getHistory()!=History.PROSTITUTE) {
				character.setVaginaVirgin(true);
				int capacity = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.TWO_TIGHT.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				character.setVaginaCapacity(capacity, true);
				
			} else {
				if(Math.random()>0.25f || character.getHistory()==History.PROSTITUTE) {
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
		character.getBody().calculateRace();
	}
	
	/**
	 * Sets the History for the supplied character.
	 * @param character
	 */
	public static void setHistoryAndPersonality(GameCharacter character) {
		
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
		 
		 if(Math.random()<prostituteChance) {
		 	character.setHistory(History.PROSTITUTE);
		 	
		 	character.setAssVirgin(false);
		 	character.setAssCapacity(character.getAssRawCapacityValue()*1.2f, true);
		 	character.setAssStretchedCapacity(character.getAssRawCapacityValue());
		 	
		 	if(character.hasVagina()) {
		 		character.setVaginaVirgin(false);
		 		character.setVaginaCapacity(character.getVaginaRawCapacityValue()*1.2f, true);
		 		character.setVaginaStretchedCapacity(character.getVaginaRawCapacityValue());
		 	}
		 	
		 	character.setPenisVirgin(false);
		 	
		 	character.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		 	character.setName(Name.getRandomProstituteTriplet());
		 	character.useItem(AbstractItemType.generateItem(ItemType.PROMISCUITY_PILL), character, false);
		 	
		 } else {
		 	character.setHistory(History.MUGGER);
		 }
		 
		 //TODO Set personality based on history. (Or vice-versa, but one should lead to the other.)
			
	}
	
	private static List<Fetish> getAllowedFetishes(GameCharacter character) {
		List<Fetish> allowedFetishes = new ArrayList<>();
		
		for(Fetish f : Fetish.values()) {
			if (f==Fetish.FETISH_PURE_VIRGIN) {
				if(character.hasVagina() && (character.getHistory()!=History.PROSTITUTE?Math.random()<=0.25f:true)) // 25% chance for prostitutes, as when drawn from amongst all the other fetishes, the actual chance will be much lower.
					allowedFetishes.add(f);
				
			} else if (f==Fetish.FETISH_BIMBO) {
				if(character.isFeminine())
					allowedFetishes.add(f);
				
			} else if (f==Fetish.FETISH_PREGNANCY || f==Fetish.FETISH_BROODMOTHER || f==Fetish.FETISH_VAGINAL_RECEIVING) {
				if(character.hasVagina())
					allowedFetishes.add(f);
				
			} else if (f==Fetish.FETISH_IMPREGNATION || f==Fetish.FETISH_SEEDER) {
				if(character.hasPenis() && character.sexualOrientation!=SexualOrientation.ANDROPHILIC)
					allowedFetishes.add(f);
				
			} else if (f==Fetish.FETISH_CUM_STUD) {
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
		availableFetishes.removeAll(character.getFetishes());
		
		int[] numberProb = new int[] {1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5};
		int numberOfFetishes = numberProb[Util.random.nextInt(numberProb.length)] - character.getFetishes().size();
		
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
				availableFetishes.remove(Fetish.FETISH_TRANSFORMATION_GIVING);
				fetishesAssigned++;
			}
			
			if(Math.random() < (Main.getProperties().forcedFetishPercentage/100f)) {
				character.addFetish(Fetish.FETISH_KINK_GIVING);
				availableFetishes.remove(Fetish.FETISH_KINK_GIVING);
				fetishesAssigned++;
			}
		}
		
		if(character.getRace()==Race.COW_MORPH && availableFetishes.contains(Fetish.FETISH_BREASTS_SELF)) {
			availableFetishes.add(Fetish.FETISH_BREASTS_SELF);
			availableFetishes.add(Fetish.FETISH_BREASTS_SELF);
			availableFetishes.add(Fetish.FETISH_BREASTS_SELF);
			availableFetishes.add(Fetish.FETISH_BREASTS_SELF);
			availableFetishes.add(Fetish.FETISH_BREASTS_SELF);
		}
		
		while(fetishesAssigned < numberOfFetishes && !availableFetishes.isEmpty()) {
			Fetish f = availableFetishes.get(Util.random.nextInt(availableFetishes.size()));
			character.addFetish(f);
			while(availableFetishes.remove(f)) {}
			fetishesAssigned++;
		}
		
		generateDesires(character);
	}
	
	public static void generateDesires(GameCharacter character) {
		
		List<Fetish> availableFetishes = getAllowedFetishes(character);
		availableFetishes.removeAll(character.getFetishes());
		availableFetishes.removeIf((f) -> !f.getFetishesForAutomaticUnlock().isEmpty()); //Do not allow derived fetishes
		for(Fetish f : character.getFetishes()) {
			switch(f) {
				default:
					break;
				// Related fetishes cannot be loved and disliked at the same time:
				case FETISH_PREGNANCY:
					availableFetishes.remove(Fetish.FETISH_BROODMOTHER);
					break;
				case FETISH_BROODMOTHER:
					availableFetishes.remove(Fetish.FETISH_PREGNANCY);
					break;
				case FETISH_IMPREGNATION:
					availableFetishes.remove(Fetish.FETISH_SEEDER);
					break;
				case FETISH_SEEDER:
					availableFetishes.remove(Fetish.FETISH_IMPREGNATION);
					break;
			}
		}
		
		// Desires:
		int[] posDesireProb = new int[] {1, 1, 2, 2, 2, 3, 3};
		int[] negDesireProb = new int[] {3, 3, 4, 4, 4, 5, 5};
		int numberOfPositiveDesires = posDesireProb[Util.random.nextInt(posDesireProb.length)];
		int numberOfNegativeDesires = negDesireProb[Util.random.nextInt(negDesireProb.length)];
		
		int desiresAssigned = 0;
		while(desiresAssigned < numberOfPositiveDesires && !availableFetishes.isEmpty()) {
			Fetish f = availableFetishes.get(Util.random.nextInt(availableFetishes.size()));
			character.setFetishDesire(f, FetishDesire.THREE_LIKE);
			availableFetishes.remove(f);
			switch(f) {
				default:
					break;
				// Related fetishes cannot be liked and disliked at the same time:
				case FETISH_PREGNANCY:
					availableFetishes.remove(Fetish.FETISH_BROODMOTHER);
					break;
				case FETISH_BROODMOTHER:
					availableFetishes.remove(Fetish.FETISH_PREGNANCY);
					break;
				case FETISH_IMPREGNATION:
					availableFetishes.remove(Fetish.FETISH_SEEDER);
					break;
				case FETISH_SEEDER:
					availableFetishes.remove(Fetish.FETISH_IMPREGNATION);
					break;
			}
			desiresAssigned++;
		}
		
		desiresAssigned = 0;
		if(character instanceof Cultist || character instanceof DominionSuccubusAttacker) { // Cultists and succubus attackers like raping
			availableFetishes.remove(Fetish.FETISH_NON_CON_DOM);
		}
		
		availableFetishes.remove(Fetish.FETISH_CUM_STUD); // Who doesn't like cumming? :3
		
		while(desiresAssigned < numberOfNegativeDesires && !availableFetishes.isEmpty()) {
			Fetish f = availableFetishes.get(Util.random.nextInt(availableFetishes.size()));
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
	
	public static void equipClothing(GameCharacter character, boolean replaceUnsuitableClothing, boolean onlyAddCoreClothing) {
		Colour primaryColour = ColourListPresets.ALL.getPresetColourList().get(Util.random.nextInt(ColourListPresets.ALL.getPresetColourList().size())),
				secondaryColour = ColourListPresets.ALL.getPresetColourList().get(Util.random.nextInt(ColourListPresets.ALL.getPresetColourList().size())),
				lingerieColour = ColourListPresets.LINGERIE.getPresetColourList().get(Util.random.nextInt(ColourListPresets.LINGERIE.getPresetColourList().size()));
		
		List<InventorySlot> inventorySlotsInPriorityOrder = new ArrayList<>();
		inventorySlotsInPriorityOrder.add(InventorySlot.TORSO_UNDER); // Torso needs to be randomly decided first, to give girls a chance to wear a dress.
		for(InventorySlot slot : InventorySlot.values()) {
			if(slot!=InventorySlot.TORSO_UNDER) {
				inventorySlotsInPriorityOrder.add(slot);
			}
		}
		
		if((character.isFeminine() && !character.hasFetish(Fetish.FETISH_CROSS_DRESSER)) || (!character.isFeminine() && character.hasFetish(Fetish.FETISH_CROSS_DRESSER))) {
			for(InventorySlot slot : inventorySlotsInPriorityOrder) {
				if(replaceUnsuitableClothing) {
					if(character.getClothingInSlot(slot)!=null) {
						if(character.getClothingInSlot(slot).getClothingType().getFemininityRestriction() == Femininity.MASCULINE) {
							character.unequipClothingIntoVoid(character.getClothingInSlot(slot), true, character);
						}
					}
				}
				
				if(!slot.isCoreClothing() && onlyAddCoreClothing) {
					// Don't add clothing if not core
				} else {
					if((slot.isCoreClothing() || Math.random()>0.75f || slot.isJewellery()) && !character.isSlotIncompatible(slot) && character.getClothingInSlot(slot)==null) {
						if(!ClothingType.getCommonClothingMapFemaleIncludingAndrogynous().get(slot).isEmpty() && (slot.slotBlockedByRace(character) != character.getRace())) {
							
							List<AbstractClothingType> clothingToUse = ClothingType.getCommonClothingMapFemaleIncludingAndrogynous().get(slot);
							
							if(character.getHistory()==History.PROSTITUTE) {
								clothingToUse = suitableFeminineClothing.get(History.PROSTITUTE);
							}
							AbstractClothingType ct = getClothingTypeForSlot(character, slot, clothingToUse);
							
							clothingToUse.remove(ClothingType.PENIS_CONDOM);
							
							if(ct!=null) {
								character.equipClothingFromNowhere(AbstractClothingType.generateClothing(
										ct,
										(slot == InventorySlot.GROIN || slot==InventorySlot.CHEST || slot==InventorySlot.SOCK
												? ct.getAvailablePrimaryColours().contains(lingerieColour)?lingerieColour:ct.getAvailablePrimaryColours().get(Util.random.nextInt(ct.getAvailablePrimaryColours().size()))
												: (slot.isCoreClothing()
														?ct.getAvailablePrimaryColours().contains(primaryColour)?primaryColour:ct.getAvailablePrimaryColours().get(Util.random.nextInt(ct.getAvailablePrimaryColours().size()))
														:ct.getAvailablePrimaryColours().contains(secondaryColour)?secondaryColour:ct.getAvailablePrimaryColours().get(Util.random.nextInt(ct.getAvailablePrimaryColours().size())))),
										false), true, character);
							}
						}
					}
				}
			}
			
		} else {
			for(InventorySlot slot : inventorySlotsInPriorityOrder) {
				if(replaceUnsuitableClothing) {
					if(character.getClothingInSlot(slot)!=null) {
						if(character.getClothingInSlot(slot).getClothingType().getFemininityRestriction() == Femininity.FEMININE) {
							character.unequipClothingIntoVoid(character.getClothingInSlot(slot), true, character);
						}
					}
				}
				
				if(!slot.isCoreClothing() && onlyAddCoreClothing) {
					// Don't add clothing if not core
				} else {
					if((slot.isCoreClothing() || Math.random()>0.75f || slot.isJewellery()) && !character.isSlotIncompatible(slot) && character.getClothingInSlot(slot)==null) {
						if(!ClothingType.getCommonClothingMapMaleIncludingAndrogynous().get(slot).isEmpty() && (slot.slotBlockedByRace(character) != character.getRace())) {
							

							List<AbstractClothingType> clothingToUse = ClothingType.getCommonClothingMapMaleIncludingAndrogynous().get(slot);
							clothingToUse.remove(ClothingType.PENIS_CONDOM);
							AbstractClothingType ct = getClothingTypeForSlot(character, slot, clothingToUse);
							
							if(ct!=null) {
							character.equipClothingFromNowhere(AbstractClothingType.generateClothing(
									ct,
									(slot == InventorySlot.GROIN || slot==InventorySlot.CHEST || slot==InventorySlot.SOCK
											?  ct.getAvailablePrimaryColours().contains(lingerieColour)?lingerieColour:ct.getAvailablePrimaryColours().get(Util.random.nextInt(ct.getAvailablePrimaryColours().size()))
													: (slot.isCoreClothing()
															?ct.getAvailablePrimaryColours().contains(primaryColour)?primaryColour:ct.getAvailablePrimaryColours().get(Util.random.nextInt(ct.getAvailablePrimaryColours().size()))
															:ct.getAvailablePrimaryColours().contains(secondaryColour)?secondaryColour:ct.getAvailablePrimaryColours().get(Util.random.nextInt(ct.getAvailablePrimaryColours().size())))),
									false), true, character);
							}
								
						}
					}
				}
			}
		}
	}
	
	private static AbstractClothingType getClothingTypeForSlot(GameCharacter character, InventorySlot slot, List<AbstractClothingType> clothingOptions) {
		List<AbstractClothingType> availableClothing = new ArrayList<>();

		boolean canEquip=true;
		
		for(AbstractClothingType ct : clothingOptions) {
			if(ct.getSlot()!=slot) {
				continue;
			}
			canEquip=true;
			
			if(slot==InventorySlot.CHEST && !character.hasBreasts()) {
				canEquip = false;
				
			} else if(character.hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
				
				for(BlockedParts bp : ct.getBlockedPartsList()) {
					boolean leavesAnusExposed = character.isCoverableAreaExposed(CoverableArea.ANUS) && !bp.blockedBodyParts.contains(CoverableArea.ANUS);
					boolean leavesNipplesExposed = character.isCoverableAreaExposed(CoverableArea.NIPPLES) && !bp.blockedBodyParts.contains(CoverableArea.NIPPLES);
					boolean leavesPenisExposed = !character.hasPenis() || (character.isCoverableAreaExposed(CoverableArea.PENIS) && !bp.blockedBodyParts.contains(CoverableArea.PENIS));
					boolean leavesVaginaExposed = !character.hasVagina() || (character.isCoverableAreaExposed(CoverableArea.VAGINA) && !bp.blockedBodyParts.contains(CoverableArea.VAGINA));
					//TODO
					if(!leavesNipplesExposed || (!leavesAnusExposed || !leavesPenisExposed && !leavesVaginaExposed)) {
						canEquip = false;
					}
				}
				
				
			} else {
				for(InventorySlot is : ct.getIncompatibleSlots()) {
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
			return availableClothing.get(Util.random.nextInt(availableClothing.size()));
		}
	}
	
	public static void applyMakeup(GameCharacter character, boolean overideExistingMakeup) {
		if((character.isFeminine() && !character.hasFetish(Fetish.FETISH_CROSS_DRESSER)) || (!character.isFeminine() && character.hasFetish(Fetish.FETISH_CROSS_DRESSER))) {
			List<Colour> colours = Util.newArrayListOfValues(
					new ListValue<>(Colour.COVERING_NONE),
					new ListValue<>(Colour.COVERING_CLEAR),
					new ListValue<>(Colour.COVERING_RED),
					new ListValue<>(Colour.COVERING_PINK),
					new ListValue<>(Colour.COVERING_BLUE));
			
			if(character.getHistory()==History.PROSTITUTE) {
				colours.remove(Colour.COVERING_NONE);
				colours.remove(Colour.COVERING_CLEAR);
			}
			
			Colour colourForCoordination = colours.get(Util.random.nextInt(colours.size()));
			Colour colourForNails = colours.get(Util.random.nextInt(colours.size()));
			
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

	private static Map<History, ArrayList<AbstractClothingType>> suitableFeminineClothing = new HashMap<>();
	
	static {
		suitableFeminineClothing.put(History.PROSTITUTE,
				Util.newArrayListOfValues(
						new ListValue<>(ClothingType.ANKLE_BRACELET),
						new ListValue<>(ClothingType.CHEST_LACY_PLUNGE_BRA),
						new ListValue<>(ClothingType.CHEST_OPEN_CUP_BRA),
						new ListValue<>(ClothingType.CHEST_PLUNGE_BRA),
						new ListValue<>(ClothingType.EYES_AVIATORS),
						new ListValue<>(ClothingType.FINGER_RING),
						new ListValue<>(ClothingType.FOOT_ANKLE_BOOTS),
						new ListValue<>(ClothingType.FOOT_HEELS),
						new ListValue<>(ClothingType.FOOT_THIGH_HIGH_BOOTS),
						new ListValue<>(ClothingType.FOOT_STILETTO_HEELS),
						new ListValue<>(ClothingType.GROIN_BACKLESS_PANTIES),
						new ListValue<>(ClothingType.GROIN_CROTCHLESS_PANTIES),
						new ListValue<>(ClothingType.GROIN_CROTCHLESS_THONG),
						new ListValue<>(ClothingType.GROIN_LACY_PANTIES),
						new ListValue<>(ClothingType.GROIN_THONG),
						new ListValue<>(ClothingType.GROIN_VSTRING),
						new ListValue<>(ClothingType.HAND_ELBOWLENGTH_GLOVES),
						new ListValue<>(ClothingType.HEAD_HEADBAND),
						new ListValue<>(ClothingType.HEAD_HEADBAND_BOW),
						new ListValue<>(ClothingType.LEG_CROTCHLESS_CHAPS),
						new ListValue<>(ClothingType.LEG_MICRO_SKIRT_BELTED),
						new ListValue<>(ClothingType.LEG_MICRO_SKIRT_PLEATED),
						new ListValue<>(ClothingType.LEG_MINI_SKIRT),
						new ListValue<>(ClothingType.LEG_SKIRT),
						new ListValue<>(ClothingType.NECK_HEART_NECKLACE),
						new ListValue<>(ClothingType.NECK_ANKH_NECKLACE),
						new ListValue<>(ClothingType.NIPPLE_TAPE_CROSSES),
						new ListValue<>(ClothingType.SOCK_FISHNET_STOCKINGS),
						new ListValue<>(ClothingType.SOCK_TIGHTS),
						new ListValue<>(ClothingType.STOMACH_OVERBUST_CORSET),
						new ListValue<>(ClothingType.STOMACH_UNDERBUST_CORSET),
						new ListValue<>(ClothingType.TORSO_FISHNET_TOP),
						new ListValue<>(ClothingType.TORSO_KEYHOLE_CROPTOP),
						new ListValue<>(ClothingType.TORSO_SHORT_CROPTOP),
						new ListValue<>(ClothingType.WRIST_BANGLE),
						new ListValue<>(ClothingType.WRIST_WOMENS_WATCH),
						
						new ListValue<>(ClothingType.PIERCING_EAR_BASIC_RING),
						new ListValue<>(ClothingType.PIERCING_LIP_RINGS),
						new ListValue<>(ClothingType.PIERCING_NAVEL_GEM),
						new ListValue<>(ClothingType.PIERCING_NIPPLE_BARS),
						new ListValue<>(ClothingType.PIERCING_NOSE_BASIC_RING),
						new ListValue<>(ClothingType.PIERCING_PENIS_RING),
						new ListValue<>(ClothingType.PIERCING_TONGUE_BAR),
						new ListValue<>(ClothingType.PIERCING_VAGINA_BARBELL_RING)));
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
