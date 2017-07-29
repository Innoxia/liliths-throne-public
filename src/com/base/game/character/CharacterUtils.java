package com.base.game.character;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.CumProduction;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.body.valueEnums.PenisSize;
import com.base.game.character.body.valueEnums.TesticleSize;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.Perk;
import com.base.game.character.effects.StatusEffect;
import com.base.game.character.gender.Gender;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.combat.SpecialAttack;
import com.base.game.combat.Spell;
import com.base.game.inventory.InventorySlot;
import com.base.game.inventory.clothing.BlockedParts;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.SexType;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Vector2i;
import com.base.world.WorldType;
import com.base.world.places.Dominion;

/**
 * @since 0.1.67
 * @version 0.1.82
 * @author Innoxia
 */
public class CharacterUtils {
	
	public static void saveCharacterAsXML(GameCharacter character){
		try {
		
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element properties = doc.createElement("character");
			doc.appendChild(properties);

			// GameCharacter class:
			
			// Core information:
			Element characterCoreInfo = doc.createElement("core");
			Comment comment = doc.createComment("If you want to edit any of these values, just be warned that it might break the game...");
			properties.appendChild(characterCoreInfo);
			createXMLElementWithValue(doc, characterCoreInfo, "name", character.getName());
			createXMLElementWithValue(doc, characterCoreInfo, "level", String.valueOf(character.getLevel()));
			createXMLElementWithValue(doc, characterCoreInfo, "version", Main.VERSION_NUMBER);
			createXMLElementWithValue(doc, characterCoreInfo, "sexualOrientation", String.valueOf(character.getSexualOrientation()));
			characterCoreInfo.getParentNode().insertBefore(comment, characterCoreInfo);
			
			// Attributes:
			Element characterCoreAttributes = doc.createElement("attributes");
			properties.appendChild(characterCoreAttributes);
			for(Attribute att : Attribute.values()){
				Element element = doc.createElement("attribute");
				characterCoreAttributes.appendChild(element);
				
				addAttribute(doc, element, "type", att.toString());
				addAttribute(doc, element, "value", String.valueOf(character.getBaseAttributeValue(att)));
			}
			
			// Perks:
			Element characterPerks = doc.createElement("perks");
			properties.appendChild(characterPerks);
			for(Perk p : Perk.values()){
				Element element = doc.createElement("perk");
				characterPerks.appendChild(element);
				
				addAttribute(doc, element, "type", p.toString());
				addAttribute(doc, element, "value", String.valueOf(character.hasPerk(p)));
			}
			
			// Fetishes:
			Element characterFetishes = doc.createElement("fetishes");
			properties.appendChild(characterFetishes);
			for(Fetish f : Fetish.values()){
				Element element = doc.createElement("fetish");
				characterFetishes.appendChild(element);
				
				addAttribute(doc, element, "type", f.toString());
				addAttribute(doc, element, "value", String.valueOf(character.hasFetish(f)));
			}
			
			// Status effects:
			Element characterStatusEffects = doc.createElement("statusEffects");
			properties.appendChild(characterStatusEffects);
			for(StatusEffect se : character.getStatusEffects()){
				Element element = doc.createElement("statusEffect");
				characterStatusEffects.appendChild(element);
				
				addAttribute(doc, element, "type", se.toString());
				addAttribute(doc, element, "value", String.valueOf(character.getStatusEffectDuration(se)));
			}
			
			// Combat (spells and special attacks):
			Element characterCombat = doc.createElement("combat");
			properties.appendChild(characterCombat);
			for(Spell s : Spell.values()){
				Element element = doc.createElement("spell");
				characterCombat.appendChild(element);
				
				addAttribute(doc, element, "type", s.toString());
				addAttribute(doc, element, "value", String.valueOf(character.getSpells().contains(s)));
			}
			for(SpecialAttack sa : SpecialAttack.values()){
				Element element = doc.createElement("specialAttack");
				characterCombat.appendChild(element);
				
				addAttribute(doc, element, "type", sa.toString());
				addAttribute(doc, element, "value", String.valueOf(character.getSpecialAttacks().contains(sa)));
			}
			
			// Pregnancy: TODO
//			Element characterPregnancy = doc.createElement("pregnancy");
//			properties.appendChild(characterPregnancy);
//			
//			Element characterPregnancyPossibility = doc.createElement("pregnancyPossibilities");
//			characterPregnancy.appendChild(characterPregnancyPossibility);
//			for(PregnancyPossibility pregPoss : character.getPotentialPartnersAsMother()) {
//				Element element = doc.createElement("possibility");
//				characterPregnancyPossibility.appendChild(element);
//				
////				addAttribute(doc, element, "fatherName", pregPoss.getFatherName());
////				addAttribute(doc, element, "gender", pregPoss.getGender().toString());
////				addAttribute(doc, element, "race", pregPoss.getRace().toString());
////				addAttribute(doc, element, "raceStage", pregPoss.getRaceStage().toString());
//				addAttribute(doc, element, "probability", String.valueOf(pregPoss.getProbability()));
//			}
//			
//			Element characterPregnancyCurrentLitter = doc.createElement("pregnantLitter");
//			characterPregnancy.appendChild(characterPregnancyCurrentLitter);
//			if (character.getPregnantLitter() != null) {
//				Element element = doc.createElement("litter");
//				characterPregnancyCurrentLitter.appendChild(element);
//
//				addAttribute(doc, element, "dayOfConception", String.valueOf(character.getPregnantLitter().getDayOfBirth()));
//				addAttribute(doc, element, "dayOfBirth", String.valueOf(character.getPregnantLitter().getDayOfBirth()));
////				addAttribute(doc, element, "fatherName", character.getPregnantLitter().getPartner().getName("a"));
////				addAttribute(doc, element, "race", character.getPregnantLitter().getRace().toString());
//				addAttribute(doc, element, "sons", String.valueOf(character.getPregnantLitter().getSons()));
//				addAttribute(doc, element, "daughters", String.valueOf(character.getPregnantLitter().getDaughters()));
//			}
//			
//			Element characterPregnancyBirthedLitters = doc.createElement("birthedLitters");
//			characterPregnancy.appendChild(characterPregnancyBirthedLitters);
//			for(Litter litter : character.getLittersBirthed()) {
//				Element element = doc.createElement("birthedLitter");
//				characterPregnancyBirthedLitters.appendChild(element);
//
//				addAttribute(doc, element, "dayOfConception", String.valueOf(litter.getDayOfBirth()));
//				addAttribute(doc, element, "dayOfBirth", String.valueOf(litter.getDayOfBirth()));
////				addAttribute(doc, element, "fatherName", litter.getPartner().getName("a"));
////				addAttribute(doc, element, "race", litter.getRace().toString());
//				addAttribute(doc, element, "sons", String.valueOf(litter.getSons()));
//				addAttribute(doc, element, "daughters", String.valueOf(litter.getDaughters()));
//			}
			
			// Body (oh here's the fun one...)
			Element characterBody = doc.createElement("body");
			properties.appendChild(characterBody);
			
			// Core:
			Element bodyCore = doc.createElement("bodyCore");
			characterBody.appendChild(bodyCore);
			addAttribute(doc, bodyCore, "piercedStomach", String.valueOf(character.isPiercedNavel()));
			addAttribute(doc, bodyCore, "height", String.valueOf(character.getRawHeightValue()));
			addAttribute(doc, bodyCore, "femininity", String.valueOf(character.getFemininity()));
			for(BodyCoveringType bct : BodyCoveringType.values()) {
				Element element = doc.createElement("bodyCovering");
				bodyCore.appendChild(element);
				
				addAttribute(doc, element, "type", bct.toString());
				addAttribute(doc, element, "colour", character.getSkinColour(bct).toString());
				addAttribute(doc, element, "discovered", String.valueOf(character.getBodyCoveringTypesDiscovered().contains(bct)));
			}
			
			// Arm:
			Element bodyArm = doc.createElement("arm");
			characterBody.appendChild(bodyArm);
			addAttribute(doc, bodyArm, "type", character.getArmType().toString());
			
			// Ass:
			Element bodyAss = doc.createElement("ass");
			characterBody.appendChild(bodyAss);
			addAttribute(doc, bodyAss, "type", character.getAssType().toString());
			addAttribute(doc, bodyAss, "assSize", String.valueOf(character.getAssSize().getValue()));
			addAttribute(doc, bodyAss, "hipSize", String.valueOf(character.getHipSize().getValue()));
			addAttribute(doc, bodyAss, "wetness", String.valueOf(character.getAssWetness().getValue()));
			addAttribute(doc, bodyAss, "elasticity", String.valueOf(character.getAssElasticity().getValue()));
			addAttribute(doc, bodyAss, "capacity", String.valueOf(character.getAssRawCapacityValue()));
			addAttribute(doc, bodyAss, "stretchedCapacity", String.valueOf(character.getAssStretchedCapacity()));
			addAttribute(doc, bodyAss, "virgin", String.valueOf(character.isAssVirgin()));
			addAttribute(doc, bodyAss, "bleached", String.valueOf(character.isAssBleached()));
			
			// Breasts:
			Element bodyBreast = doc.createElement("breasts");
			characterBody.appendChild(bodyBreast);
			addAttribute(doc, bodyBreast, "type", character.getBreastType().toString());
			addAttribute(doc, bodyBreast, "size", String.valueOf(character.getBreastSize().getMeasurement()));
			addAttribute(doc, bodyBreast, "lactation", String.valueOf(character.getBreastRawLactationValue()));
			addAttribute(doc, bodyBreast, "rows", String.valueOf(character.getBreastRows()));
			addAttribute(doc, bodyBreast, "elasticity", String.valueOf(character.getBreastElasticity().getValue()));
			addAttribute(doc, bodyBreast, "capacity", String.valueOf(character.getBreastRawCapacityValue()));
			addAttribute(doc, bodyBreast, "stretchedCapacity", String.valueOf(character.getBreastStretchedCapacity()));
			addAttribute(doc, bodyBreast, "pierced", String.valueOf(character.isPiercedNipple()));
			addAttribute(doc, bodyBreast, "virgin", String.valueOf(character.isBreastVirgin()));

			// Ear:
			Element bodyEar = doc.createElement("ear");
			characterBody.appendChild(bodyEar);
			addAttribute(doc, bodyEar, "type", character.getEarType().toString());
			addAttribute(doc, bodyEar, "pierced", String.valueOf(character.isPiercedEar()));

			// Eye:
			Element bodyEye = doc.createElement("eye");
			characterBody.appendChild(bodyEye);
			addAttribute(doc, bodyEye, "type", character.getEyeType().toString());
			
			// Face:
			Element bodyFace = doc.createElement("face");
			characterBody.appendChild(bodyFace);
			addAttribute(doc, bodyFace, "type", character.getFaceType().toString());
			addAttribute(doc, bodyFace, "makeupLevel", String.valueOf(character.getFaceMakeupLevel().getValue()));
			addAttribute(doc, bodyFace, "elasticity", String.valueOf(character.getFaceElasticity().getValue()));
			addAttribute(doc, bodyFace, "capacity", String.valueOf(character.getFaceRawCapacityValue()));
			addAttribute(doc, bodyFace, "stretchedCapacity", String.valueOf(character.getFaceStretchedCapacity()));
			addAttribute(doc, bodyFace, "piercedNose", String.valueOf(character.isPiercedNose()));
			addAttribute(doc, bodyFace, "piercedLip", String.valueOf(character.isPiercedLip()));
			addAttribute(doc, bodyFace, "piercedTongue", String.valueOf(character.isPiercedTongue()));
			addAttribute(doc, bodyFace, "virgin", String.valueOf(character.isFaceVirgin()));
			
			// Hair:
			Element bodyHair = doc.createElement("hair");
			characterBody.appendChild(bodyHair);
			addAttribute(doc, bodyHair, "type", character.getHairType().toString());
			addAttribute(doc, bodyHair, "length", String.valueOf(character.getHairRawLengthValue()));
			
			// Horn:
			Element bodyHorn = doc.createElement("horn");
			characterBody.appendChild(bodyHorn);
			addAttribute(doc, bodyHorn, "type", character.getHornType().toString());
			
			// Leg:
			Element bodyLeg = doc.createElement("leg");
			characterBody.appendChild(bodyLeg);
			addAttribute(doc, bodyLeg, "type", character.getLegType().toString());
			
			// Penis:
			Element bodyPenis = doc.createElement("penis");
			characterBody.appendChild(bodyPenis);
			addAttribute(doc, bodyPenis, "type", character.getPenisType().toString());
			addAttribute(doc, bodyPenis, "size", String.valueOf(character.getPenisRawSizeValue()));
			addAttribute(doc, bodyPenis, "testicleSize", String.valueOf(character.getTesticleSize().getValue()));
			addAttribute(doc, bodyPenis, "cumProduction", String.valueOf(character.getPenisRawCumProductionValue()));
			addAttribute(doc, bodyPenis, "numberOfTesticles", String.valueOf(character.getPenisNumberOfTesticles()));
			addAttribute(doc, bodyPenis, "elasticity", String.valueOf(character.getPenisUrethraElasticity().getValue()));
			addAttribute(doc, bodyPenis, "capacity", String.valueOf(character.getPenisRawCapacityValue()));
			addAttribute(doc, bodyPenis, "stretchedCapacity", String.valueOf(character.getPenisStretchedCapacity()));
			addAttribute(doc, bodyPenis, "virgin", String.valueOf(character.isUrethraVirgin()));
			addAttribute(doc, bodyPenis, "pierced", String.valueOf(character.isPiercedPenis()));
			
			// Skin:
			Element bodySkin = doc.createElement("skin");
			characterBody.appendChild(bodySkin);
			addAttribute(doc, bodySkin, "type", character.getSkinType().toString());
			
			// Tail:
			Element bodyTail = doc.createElement("tail");
			characterBody.appendChild(bodyTail);
			addAttribute(doc, bodyTail, "type", character.getTailType().toString());
			
			// Vagina:
			Element bodyVagina = doc.createElement("vagina");
			characterBody.appendChild(bodyVagina);
			addAttribute(doc, bodyVagina, "type", character.getVaginaType().toString());
			addAttribute(doc, bodyVagina, "wetness", String.valueOf(character.getVaginaWetness().getValue()));
			addAttribute(doc, bodyVagina, "clitSize", String.valueOf(character.getVaginaRawClitorisSizeValue()));
			addAttribute(doc, bodyVagina, "elasticity", String.valueOf(character.getVaginaElasticity().getValue()));
			addAttribute(doc, bodyVagina, "capacity", String.valueOf(character.getVaginaRawCapacityValue()));
			addAttribute(doc, bodyVagina, "stretchedCapacity", String.valueOf(character.getVaginaStretchedCapacity()));
			addAttribute(doc, bodyVagina, "virgin", String.valueOf(character.isVaginaVirgin()));
			addAttribute(doc, bodyVagina, "pierced", String.valueOf(character.isPiercedVagina()));
			
			// Wing:
			Element bodyWing = doc.createElement("wing");
			characterBody.appendChild(bodyWing);
			addAttribute(doc, bodyWing, "type", character.getWingType().toString());
			
			
			// PlayerCharacter specific:
			
			// Core:
			Element characterPlayerCoreInfo = doc.createElement("playerCore");
			properties.appendChild(characterPlayerCoreInfo);
			createXMLElementWithValue(doc, characterPlayerCoreInfo, "experience", String.valueOf(((PlayerCharacter)character).getExperience()));
			createXMLElementWithValue(doc, characterPlayerCoreInfo, "levelUpPoints", String.valueOf(((PlayerCharacter)character).getLevelUpPoints()));
			createXMLElementWithValue(doc, characterPlayerCoreInfo, "perkPoints", String.valueOf(((PlayerCharacter)character).getPerkPoints()));
			
			// Sex stats:
			Element characterSexStats = doc.createElement("sexStats");
			properties.appendChild(characterSexStats);
			
			Element characterCumCount = doc.createElement("cumCounts");
			characterSexStats.appendChild(characterCumCount);
			for(PenetrationType pt : PenetrationType.values()) {
				for(OrificeType ot : OrificeType.values()) {
					Element element = doc.createElement("cumCount");
					characterCumCount.appendChild(element);
					
					addAttribute(doc, element, "penetrationType", pt.toString());
					addAttribute(doc, element, "orificeType", ot.toString());
					addAttribute(doc, element, "count", String.valueOf(((PlayerCharacter)character).getStats().getCumCount(new SexType(pt, ot))));
				}
			}

			Element characterSexCount = doc.createElement("sexCounts");
			characterSexStats.appendChild(characterSexCount);
			for(PenetrationType pt : PenetrationType.values()) {
				for(OrificeType ot : OrificeType.values()) {
					Element element = doc.createElement("sexCount");
					characterSexCount.appendChild(element);
					
					addAttribute(doc, element, "penetrationType", pt.toString());
					addAttribute(doc, element, "orificeType", ot.toString());
					addAttribute(doc, element, "count", String.valueOf(((PlayerCharacter)character).getStats().getSexCount(new SexType(pt, ot))));
				}
			}

			Element characterVirginityTakenBy = doc.createElement("virginityTakenBy");
			characterSexStats.appendChild(characterVirginityTakenBy);
			for(PenetrationType pt : PenetrationType.values()) {
				for(OrificeType ot : OrificeType.values()) {
					if(((PlayerCharacter)character).getStats().getVirginityLoss(new SexType(pt, ot))!=null) {
						Element element = doc.createElement("virginity");
						characterVirginityTakenBy.appendChild(element);
						
						addAttribute(doc, element, "penetrationType", pt.toString());
						addAttribute(doc, element, "orificeType", ot.toString());
						addAttribute(doc, element, "takenBy", String.valueOf(((PlayerCharacter)character).getStats().getVirginityLoss(new SexType(pt, ot))));
					}
				}
			}
			
			
			
			
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
		
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	
	private static void createXMLElementWithValue(Document doc, Element parentElement, String elementName, String value){
		Element element = doc.createElement(elementName);
		parentElement.appendChild(element);
		Attr attr = doc.createAttribute("value");
		attr.setValue(value);
		element.setAttributeNode(attr);
	}
	
	private static void addAttribute(Document doc, Element parentElement, String attributeName, String value){
		Attr attr = doc.createAttribute(attributeName);
		attr.setValue(value);
		parentElement.setAttributeNode(attr);
	}
	
	private static StringBuilder characterImportLog;
	public static String getCharacterImportLog() {
		return characterImportLog.toString();
	}
	// This is a complete mess...
	public static PlayerCharacter loadCharacterFromXML(File xmlFile){
		
		PlayerCharacter importedCharacter = new PlayerCharacter(new NameTriplet("Player"), "", 1, Gender.MALE, RacialBody.HUMAN, RaceStage.HUMAN, null, WorldType.DOMINION, Dominion.CITY_AUNTS_HOME);
		characterImportLog = new StringBuilder();
		
		if (xmlFile.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(xmlFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				// Core info:
				NodeList nodes = doc.getElementsByTagName("core");
				Element element = (Element) nodes.item(0);

				// Name:
				importedCharacter.setName(new NameTriplet(((Element)element.getElementsByTagName("name").item(0)).getAttribute("value")));
				characterImportLog.append("</br>Set name: " + ((Element)element.getElementsByTagName("name").item(0)).getAttribute("value"));
				
				// Level:
				importedCharacter.setLevel(Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")));
				characterImportLog.append("</br>Set level: " + Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")));
				
				// Sexual Orientation:
				if(element.getElementsByTagName("sexualOrientation").getLength()!=0) {
					if(!((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value").equals("null")) {
						importedCharacter.setSexualOrientation(SexualOrientation.valueOf(((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value")));
						characterImportLog.append("</br>Set Sexual Orientation: " + SexualOrientation.valueOf(((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value")));
					} else {
						importedCharacter.setSexualOrientation(SexualOrientation.AMBIPHILIC);
						characterImportLog.append("</br>Set Sexual Orientation: " + SexualOrientation.AMBIPHILIC);
					}
				}
				
				// Temp fix for perk points:
				importedCharacter.setPerkPoints((Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")))-1);
				characterImportLog.append("</br>Set perkPoints: (TEMP FIX) " + (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))-1));
				
				int extraLevelUpPoints=0;
				// If there is a version number, attributes should be working correctly:
				if(element.getElementsByTagName("version").item(0)!=null) {
					
					nodes = doc.getElementsByTagName("attributes");
					element = (Element) nodes.item(0);
					for(int i=0; i<element.getElementsByTagName("attribute").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("attribute").item(i));
						
						try {
							importedCharacter.setAttribute(Attribute.valueOf(e.getAttribute("type")), Float.valueOf(e.getAttribute("value")));
							characterImportLog.append("</br>Set Attribute: "+Attribute.valueOf(e.getAttribute("type")).getName() +" to "+ Float.valueOf(e.getAttribute("value")));
						}catch(IllegalArgumentException ex){
						}
					}
					
				} else {
					extraLevelUpPoints = (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")) * 5);
					characterImportLog.append("</br>Old character version. Extra LevelUpPoints set to: "+(Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")) * 5));
				}
				
				// Fetishes:
				nodes = doc.getElementsByTagName("fetishes");
				element = (Element) nodes.item(0);
				if(element!=null) {
					for(int i=0; i<element.getElementsByTagName("fetish").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("fetish").item(i));
						
						try {
							if(Boolean.valueOf(e.getAttribute("value"))) {
								if(Fetish.valueOf(e.getAttribute("type")) != null) {
									importedCharacter.addFetish(Fetish.valueOf(e.getAttribute("type")));
									characterImportLog.append("</br>Added Fetish: "+Fetish.valueOf(e.getAttribute("type")).getName(importedCharacter));
								}
							}
						}catch(IllegalArgumentException ex){
						}
					}
				}
				
				// Perks:
//				nodes = doc.getElementsByTagName("perks");
//				element = (Element) nodes.item(0);
//				for(int i=0; i<element.getElementsByTagName("perk").getLength(); i++){
//					Element e = ((Element)element.getElementsByTagName("perk").item(i));
//					
//					try {
//						if(Boolean.valueOf(e.getAttribute("value"))) {
//							importedCharacter.addPerk(Perk.valueOf(e.getAttribute("type")));
//							characterImportLog.append("</br>Added Perk: "+Perk.valueOf(e.getAttribute("type")).getName(importedCharacter));
//						}
//					}catch(IllegalArgumentException ex){
//					}
//				}
				
				// Status Effects:
				nodes = doc.getElementsByTagName("statusEffects");
				element = (Element) nodes.item(0);
				for(int i=0; i<element.getElementsByTagName("statusEffect").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("statusEffect").item(i));
					
					try {
						if(Integer.valueOf(e.getAttribute("value"))!=-1) {
							importedCharacter.addStatusEffect(StatusEffect.valueOf(e.getAttribute("type")), Integer.valueOf(e.getAttribute("value")));
							characterImportLog.append("</br>Added Status Effect: "+StatusEffect.valueOf(e.getAttribute("type")).getName(importedCharacter)+" ("+Integer.valueOf(e.getAttribute("value"))+" minutes)");
						}
					}catch(IllegalArgumentException ex){
					}
				}
				
				// Combat:
				// TODO Combat is all derived from weapons/perks/body parts, so there's no reason for this to even be here...
				
				// Pregnancy: TODO
//				nodes = doc.getElementsByTagName("pregnancy");
//				// Possibilities:
//				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("pregnancyPossibilities").item(0);
//				for(int i=0; i<element.getElementsByTagName("possibility").getLength(); i++){
//					Element e = ((Element)element.getElementsByTagName("possibility").item(i));
//					
//					try {
//						importedCharacter.getPotentialPartnersAsMother().add(new PregnancyPossibility(
//								null,
//								null,
//								Float.valueOf(e.getAttribute("probability"))));
//						characterImportLog.append("</br>Added Pregnancy Possibility: Father:"+e.getAttribute("fatherName"));
//					}catch(IllegalArgumentException ex){
//					}
//				}
//				// Litter:
//				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("pregnantLitter").item(0);
//				for(int i=0; i<element.getElementsByTagName("litter").getLength(); i++){
//					Element e = ((Element)element.getElementsByTagName("litter").item(i));
//					
//					try {
//						importedCharacter.setPregnantLitter(new Litter(
//								Integer.valueOf(e.getAttribute("dayOfConception")),
//								Integer.valueOf(e.getAttribute("dayOfBirth")),
//								null,
//								null,
//								Race.valueOf(e.getAttribute("race")),
//								Integer.valueOf(e.getAttribute("sons")),
//								Integer.valueOf(e.getAttribute("daughters"))));
//						characterImportLog.append("</br>Added Litter: Father:"+e.getAttribute("fatherName"));
//					}catch(IllegalArgumentException ex){
//					}
//				}
//				// Birthed Litters:
//				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("birthedLitters").item(0);
//				for(int i=0; i<element.getElementsByTagName("birthedLitter").getLength(); i++){
//					Element e = ((Element)element.getElementsByTagName("birthedLitter").item(i));
//					
//					try {
//						importedCharacter.getLittersBirthed().add(new Litter(
//								Integer.valueOf(e.getAttribute("dayOfConception")),
//								Integer.valueOf(e.getAttribute("dayOfBirth")),
//								null,
//								null,
//								Race.valueOf(e.getAttribute("race")),
//								Integer.valueOf(e.getAttribute("sons")),
//								Integer.valueOf(e.getAttribute("daughters"))));
//						characterImportLog.append("</br>Added Birthed Litter: Father:"+e.getAttribute("fatherName"));
//					}catch(IllegalArgumentException ex){
//					}
//				}
				
				// Body:
				// Core:
				nodes = doc.getElementsByTagName("body");
				
				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("bodyCore").item(0);
				importedCharacter.setFemininity(Integer.valueOf(element.getAttribute("femininity")));
				characterImportLog.append("</br>Body: Set femininity: "+Integer.valueOf(element.getAttribute("femininity")));
				
				importedCharacter.setHeight(Integer.valueOf(element.getAttribute("height")));
				characterImportLog.append("</br>Body: Set height: "+Integer.valueOf(element.getAttribute("height")));
				
				importedCharacter.setPiercedNavel(Boolean.valueOf(element.getAttribute("piercedStomach")));
				characterImportLog.append("</br>Body: Set piercedStomach: "+Boolean.valueOf(element.getAttribute("piercedStomach")));
				
				for(int i=0; i<element.getElementsByTagName("bodyCovering").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("bodyCovering").item(i));
					
					try {
						importedCharacter.setBodyCoveringForXMLImport(BodyCoveringType.valueOf(e.getAttribute("type")), Colour.valueOf(e.getAttribute("colour")));
						if(Boolean.valueOf(e.getAttribute("discovered")))
							importedCharacter.getBodyCoveringTypesDiscovered().add(BodyCoveringType.valueOf(e.getAttribute("type")));
						characterImportLog.append("</br>Body: Set bodyCovering: "+e.getAttribute("type")+" "+Colour.valueOf(e.getAttribute("colour"))+" (discovered: "+e.getAttribute("discovered")+")");
					}catch(IllegalArgumentException ex){
					}
				}
				
				// Body parts:
				// Arm:
				try {
					importedCharacter.setArmType(ArmType.valueOf(((Element)((Element) nodes.item(0)).getElementsByTagName("arm").item(0)).getAttribute("type")));
					characterImportLog.append("</br></br>Body: Arm:"
							+ "</br>type: "+importedCharacter.getArmType());
				}catch(IllegalArgumentException ex){
				}
				
				// Ass:
				try {
					Element ass = (Element)((Element) nodes.item(0)).getElementsByTagName("ass").item(0);
					importedCharacter.setAssType(AssType.valueOf(ass.getAttribute("type")));
					importedCharacter.setAssSize(Integer.valueOf(ass.getAttribute("assSize")));
					importedCharacter.setAssBleached(Boolean.valueOf(ass.getAttribute("bleached")));
					importedCharacter.setAssCapacity(Float.valueOf(ass.getAttribute("capacity")));
					importedCharacter.setAssElasticity(Integer.valueOf(ass.getAttribute("elasticity")));
					importedCharacter.setHipSize(Integer.valueOf(ass.getAttribute("hipSize")));
					importedCharacter.setAssStretchedCapacity(Float.valueOf(ass.getAttribute("stretchedCapacity")));
					importedCharacter.setAssVirgin(Boolean.valueOf(ass.getAttribute("virgin")));
					importedCharacter.setAssWetness(Integer.valueOf(ass.getAttribute("wetness")));
					characterImportLog.append("</br></br>Body: Ass:"
							+ "</br>type: "+importedCharacter.getAssType()
							+ "</br>size: "+importedCharacter.getAssSize()
							+ "</br>bleached: "+importedCharacter.isAssBleached()
							+ "</br>capacity: "+importedCharacter.getAssCapacity()
							+ "</br>elasticity: "+importedCharacter.getAssElasticity()
							+ "</br>hipSize: "+importedCharacter.getHipSize()
							+ "</br>stretchedCapacity: "+importedCharacter.getAssStretchedCapacity()
							+ "</br>virgin: "+importedCharacter.isAssVirgin()
							+ "</br>wetness: "+importedCharacter.getAssWetness());
				}catch(IllegalArgumentException ex){
				}
				
				// Breasts:
				try {
					Element breasts = (Element)((Element) nodes.item(0)).getElementsByTagName("breasts").item(0);
					importedCharacter.setBreastType(BreastType.valueOf(breasts.getAttribute("type")));
					importedCharacter.setBreastCapacity(Float.valueOf(breasts.getAttribute("capacity")));
					importedCharacter.setBreastElasticity(Integer.valueOf(breasts.getAttribute("elasticity")));
					importedCharacter.setBreastLactation(Integer.valueOf(breasts.getAttribute("lactation")));
					importedCharacter.setPiercedNipple(Boolean.valueOf(breasts.getAttribute("pierced")));
					importedCharacter.setBreastRows(Integer.valueOf(breasts.getAttribute("rows")));
					importedCharacter.setBreastSize(Integer.valueOf(breasts.getAttribute("size")));
					importedCharacter.setBreastStretchedCapacity(Float.valueOf(breasts.getAttribute("stretchedCapacity")));
					importedCharacter.setBreastVirgin(Boolean.valueOf(breasts.getAttribute("virgin")));
					characterImportLog.append("</br></br>Body: Breasts:"
							+ "</br>type: "+importedCharacter.getBreastType()
							+ "</br>capacity: "+importedCharacter.getBreastRawCapacityValue()
							+ "</br>elasticity: "+importedCharacter.getBreastElasticity()
							+ "</br>lactation: "+importedCharacter.getBreastRawLactationValue()
							+ "</br>pierced: "+importedCharacter.isPiercedNipple()
							+ "</br>rows: "+importedCharacter.getBreastRows()
							+ "</br>size: "+importedCharacter.getBreastSize()
							+ "</br>stretchedCapacity: "+importedCharacter.getBreastStretchedCapacity()
							+ "</br>virgin: "+importedCharacter.isBreastVirgin());
				}catch(IllegalArgumentException ex){
				}
				
				// Ear:
				try {
					Element ear = (Element)((Element) nodes.item(0)).getElementsByTagName("ear").item(0);
					importedCharacter.setEarType(EarType.valueOf(ear.getAttribute("type")));
					importedCharacter.setPiercedEar(Boolean.valueOf(ear.getAttribute("pierced")));
					characterImportLog.append("</br></br>Body: Ear:"
							+ "</br>type: "+importedCharacter.getEarType()
							+ "</br>pierced: "+importedCharacter.isPiercedEar());
				}catch(IllegalArgumentException ex){
				}
				
				// Eye:
				try {
					Element eye = (Element)((Element) nodes.item(0)).getElementsByTagName("eye").item(0);
					importedCharacter.setEyeType(BodyCoveringType.valueOf(eye.getAttribute("type")));
					characterImportLog.append("</br></br>Body: Eye:"
							+ "</br>type: "+importedCharacter.getEyeType());
				}catch(IllegalArgumentException ex){
				}
				
				// Face:
				try {
					Element face = (Element)((Element) nodes.item(0)).getElementsByTagName("face").item(0);
					importedCharacter.setFaceType(FaceType.valueOf(face.getAttribute("type")));
					importedCharacter.setFaceCapacity(Float.valueOf(face.getAttribute("capacity")));
					importedCharacter.setFaceElasticity(Integer.valueOf(face.getAttribute("elasticity")));
					importedCharacter.setFaceMakeupLevel(Integer.valueOf(face.getAttribute("makeupLevel")));
					importedCharacter.setPiercedLip(Boolean.valueOf(face.getAttribute("piercedLip")));
					importedCharacter.setPiercedNose(Boolean.valueOf(face.getAttribute("piercedNose")));
					importedCharacter.setPiercedTongue(Boolean.valueOf(face.getAttribute("piercedTongue")));
					importedCharacter.setFaceStretchedCapacity(Float.valueOf(face.getAttribute("stretchedCapacity")));
					importedCharacter.setFaceVirgin(Boolean.valueOf(face.getAttribute("virgin")));
					characterImportLog.append("</br></br>Body: Face: "
							+ "</br>type: "+importedCharacter.getFaceType()
							+ "</br>capacity: "+importedCharacter.getFaceRawCapacityValue()
							+ "</br>elasticity: "+importedCharacter.getFaceElasticity()
							+ "</br>makeupLevel: "+importedCharacter.getFaceMakeupLevel()
							+ "</br>piercedLip: "+importedCharacter.isPiercedLip()
							+ "</br>piercedNose: "+importedCharacter.isPiercedNose()
							+ "</br>piercedTongue: "+importedCharacter.isPiercedTongue()
							+ "</br>stretchedCapacity: "+importedCharacter.getFaceStretchedCapacity()
							+ "</br>virgin: "+importedCharacter.isFaceVirgin());
				}catch(IllegalArgumentException ex){
				}
				
				// Hair:
				try {
					Element hair = (Element)((Element) nodes.item(0)).getElementsByTagName("hair").item(0);
					importedCharacter.setHairLength(Integer.valueOf(hair.getAttribute("length")));
					importedCharacter.setHairType(BodyCoveringType.valueOf(hair.getAttribute("type")));
					characterImportLog.append("</br></br>Body: Hair: "
							+ "</br>type: "+importedCharacter.getHairType()
							+ "</br>length: "+importedCharacter.getHairLength());
				}catch(IllegalArgumentException ex){
				}
				
				// Horn:
				try {
					Element horn = (Element)((Element) nodes.item(0)).getElementsByTagName("horn").item(0);
					importedCharacter.setHornType(HornType.valueOf(horn.getAttribute("type")));
					characterImportLog.append("</br></br>Body: Horn: "
							+ "</br>type: "+importedCharacter.getHornType());
				}catch(IllegalArgumentException ex){
				}
				
				// Leg:
				try {
					Element leg = (Element)((Element) nodes.item(0)).getElementsByTagName("leg").item(0);
					importedCharacter.setLegType(LegType.valueOf(leg.getAttribute("type")));
					characterImportLog.append("</br></br>Body: Leg: "
							+ "</br>type: "+importedCharacter.getLegType());
				}catch(IllegalArgumentException ex){
				}
				
				// Penis:
				try {
					Element penis = (Element)((Element) nodes.item(0)).getElementsByTagName("penis").item(0);
					importedCharacter.setPenisType(PenisType.valueOf(penis.getAttribute("type")));
					importedCharacter.setPenisCapacity(Float.valueOf(penis.getAttribute("capacity")));
					importedCharacter.setCumProduction(Integer.valueOf(penis.getAttribute("cumProduction")));
					importedCharacter.setPenisUrethraElasticity(Integer.valueOf(penis.getAttribute("elasticity")));
					importedCharacter.setPenisNumberOfTesticles(Integer.valueOf(penis.getAttribute("numberOfTesticles")));
					importedCharacter.setPiercedPenis(Boolean.valueOf(penis.getAttribute("pierced")));
					importedCharacter.setPenisSize(Integer.valueOf(penis.getAttribute("size")));
					importedCharacter.setPenisStretchedCapacity(Float.valueOf(penis.getAttribute("stretchedCapacity")));
					importedCharacter.setTesticleSize(Integer.valueOf(penis.getAttribute("testicleSize")));
					importedCharacter.setUrethraVirgin(Boolean.valueOf(penis.getAttribute("virgin")));
					characterImportLog.append("</br></br>Body: Penis: "
							+ "</br>type: "+importedCharacter.getPenisType()
							+ "</br>capacity: "+importedCharacter.getPenisCapacity()
							+ "</br>cumProduction: "+importedCharacter.getPenisRawCumProductionValue()
							+ "</br>elasticity: "+importedCharacter.getPenisUrethraElasticity()
							+ "</br>numberOfTesticles: "+importedCharacter.getPenisNumberOfTesticles()
							+ "</br>pierced: "+importedCharacter.isPiercedPenis()
							+ "</br>size: "+importedCharacter.getPenisRawSizeValue()
							+ "</br>stretchedCapacity: "+importedCharacter.getPenisStretchedCapacity()
							+ "</br>testicleSize: "+importedCharacter.getTesticleSize()
							+ "</br>virgin: "+importedCharacter.isUrethraVirgin());
				}catch(IllegalArgumentException ex){
				}
				
				// Skin:
				try {
					Element skin = (Element)((Element) nodes.item(0)).getElementsByTagName("skin").item(0);
					importedCharacter.setSkinType(BodyCoveringType.valueOf(skin.getAttribute("type")));
					characterImportLog.append("</br></br>Body: Skin: "
							+ "</br>type: "+importedCharacter.getSkinType());
				}catch(IllegalArgumentException ex){
				}
				
				// Tail:
				try {
					Element tail = (Element)((Element) nodes.item(0)).getElementsByTagName("tail").item(0);
					importedCharacter.setTailType(TailType.valueOf(tail.getAttribute("type")));
					characterImportLog.append("</br></br>Body: Tail: "
							+ "</br>type: "+importedCharacter.getTailType());
				}catch(IllegalArgumentException ex){
				}
				
				// Vagina:
				try {
					Element vagina = (Element)((Element) nodes.item(0)).getElementsByTagName("vagina").item(0);
					importedCharacter.setVaginaType(VaginaType.valueOf(vagina.getAttribute("type")));
					importedCharacter.setVaginaCapacity(Float.valueOf(vagina.getAttribute("capacity")));
					importedCharacter.setVaginaClitorisSize(Integer.valueOf(vagina.getAttribute("clitSize")));
					importedCharacter.setVaginaElasticity(Integer.valueOf(vagina.getAttribute("elasticity")));
					importedCharacter.setPiercedVagina(Boolean.valueOf(vagina.getAttribute("pierced")));
					importedCharacter.setVaginaStretchedCapacity(Float.valueOf(vagina.getAttribute("stretchedCapacity")));
					importedCharacter.setVaginaVirgin(Boolean.valueOf(vagina.getAttribute("virgin")));
					importedCharacter.setVaginaWetness(Integer.valueOf(vagina.getAttribute("wetness")));
					characterImportLog.append("</br></br>Body: Vagina: "
							+ "</br>type: "+importedCharacter.getVaginaType()
							+ "</br>capacity: "+importedCharacter.getVaginaCapacity()
							+ "</br>clitSize: "+importedCharacter.getVaginaClitorisSize()
							+ "</br>elasticity: "+importedCharacter.getVaginaElasticity()
							+ "</br>pierced: "+importedCharacter.isPiercedVagina()
							+ "</br>stretchedCapacity: "+importedCharacter.getVaginaStretchedCapacity()
							+ "</br>virgin: "+importedCharacter.isVaginaVirgin()
							+ "</br>wetness: "+importedCharacter.getVaginaWetness());
				}catch(IllegalArgumentException ex){
				}
				
				// Wing:
				try {
					Element wing = (Element)((Element) nodes.item(0)).getElementsByTagName("wing").item(0);
					importedCharacter.setWingType(WingType.valueOf(wing.getAttribute("type")));
					characterImportLog.append("</br></br>Body: Wing: "
							+ "</br>type: "+importedCharacter.getWingType()+"</br>");
				}catch(IllegalArgumentException ex){
				}
				
				// Player Core info:
				nodes = doc.getElementsByTagName("playerCore");
				element = (Element) nodes.item(0);

				// Experience:
				importedCharacter.incrementExperience(Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")));
				characterImportLog.append("</br>Set experience: " + Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")));
				
				// Level up points:
				importedCharacter.setLevelUpPoints(Integer.valueOf(((Element)element.getElementsByTagName("levelUpPoints").item(0)).getAttribute("value")) + extraLevelUpPoints);
				characterImportLog.append("</br>Set levelUpPoints: " + (Integer.valueOf(((Element)element.getElementsByTagName("levelUpPoints").item(0)).getAttribute("value")) + extraLevelUpPoints));

				// Perk points:
				//importedCharacter.setPerkPoints(Integer.valueOf(((Element)element.getElementsByTagName("perkPoints").item(0)).getAttribute("value")));
//				importedCharacter.setPerkPoints((Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))));
//				
//				characterImportLog.append("</br>Set perkPoints: (TEMP FIX) " + (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))));
				
				// Sex stats:
				nodes = doc.getElementsByTagName("sexStats");
				
				// Cum counts:
				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("cumCounts").item(0);
				for(int i=0; i<element.getElementsByTagName("cumCount").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("cumCount").item(i));
					
					try {
						for(int it =0 ; it<Integer.valueOf(e.getAttribute("count")) ; it++)
							importedCharacter.getStats().incrementCumCount(new SexType(PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))));
						characterImportLog.append("</br>Added cum count:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" x "+Integer.valueOf(e.getAttribute("count")));
					}catch(IllegalArgumentException ex){
					}
				}
				
				// Sex counts:
				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("sexCounts").item(0);
				for(int i=0; i<element.getElementsByTagName("sexCount").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("sexCount").item(i));
					
					try {
						for(int it =0 ; it<Integer.valueOf(e.getAttribute("count")) ; it++)
							importedCharacter.getStats().incrementSexCount(new SexType(PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))));
						characterImportLog.append("</br>Added sex count:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" x "+Integer.valueOf(e.getAttribute("count")));
					}catch(IllegalArgumentException ex){
					}
				}
				
				// Virginity losses:
				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("virginityTakenBy").item(0);
				for(int i=0; i<element.getElementsByTagName("virginity").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("virginity").item(i));
					
					try {
						for(int it =0 ; it<Integer.valueOf(e.getAttribute("count")) ; it++)
							importedCharacter.getStats().setVirginityLoss(new SexType(PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))), e.getAttribute("takenBy"));
						characterImportLog.append("</br>Added sex count:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" (taken by:"+e.getAttribute("takenBy")+")");
					}catch(IllegalArgumentException ex){
					}
				}
				
				
				importedCharacter.setLocation(new Vector2i(0, 0));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return importedCharacter;
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
				character.setPiercedNipple(true);
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
		if(character.hasFetish(Fetish.FETISH_ANAL)) {
			character.setAssVirgin(false);
			character.setAssCapacity(character.getAssRawCapacityValue()*1.2f);
			character.setAssStretchedCapacity(character.getAssRawCapacityValue());
		} else {
			character.setAssVirgin(true);
		}
		
		// Body:
		character.setHeight(character.getHeight()-15 + Util.random.nextInt(30) +1);
		
		//Breasts:
		if(Main.getProperties().multiBreasts==0) {
			character.setBreastRows(1);
			
		} else if(Main.getProperties().multiBreasts==1) {
			if(character.getSkinType() == BodyCoveringType.HUMAN) {
				character.setBreastRows(1);
			}
		}
		
		if(character.getBreastSize().getMeasurement()>0) {
			character.setBreastSize(character.getBreastSize().getMeasurement() -2 +(Util.random.nextInt(5))); // Random size between -2 and +2 of base value.
		}
		
		// Face:
		if(character.hasFetish(Fetish.FETISH_ORAL)) {
			character.setFaceCapacity(Capacity.FIVE_ROOMY.getMedianValue());
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
		character.setHairLength(character.getHairLength().getMinimumValue() + Util.random.nextInt(character.getHairLength().getMaximumValue() - character.getHairLength().getMinimumValue()) +1);
		
		// Penis:
		if(character.hasPenis()) {
			if(character.getGender()==Gender.SHEMALE && Math.random()>=0.1f) { // Most traps have a small cock:
				character.setPenisSize(PenisSize.ONE_TINY.getMinimumValue() + Util.random.nextInt(character.getPenisSize().getMaximumValue() - character.getPenisSize().getMinimumValue()) +1);
				character.setTesticleSize(TesticleSize.ONE_TINY.getValue());
				character.setCumProduction(CumProduction.ONE_TRICKLE.getMedianValue());
			} else {
				character.setPenisSize(character.getPenisSize().getMinimumValue() + Util.random.nextInt(character.getPenisSize().getMaximumValue() - character.getPenisSize().getMinimumValue()) +1);
			}
		}
		
		// Vagina:
		if(character.hasVagina()) {
			if(character.hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
				character.setVaginaVirgin(true);
				int capacity = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.TWO_TIGHT.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				character.setVaginaCapacity(capacity);
				
			} else {
				if(Math.random()>0.25f) {
					character.setVaginaVirgin(false);
					character.setVaginaCapacity(character.getVaginaRawCapacityValue()*1.2f);
					character.setVaginaStretchedCapacity(character.getVaginaRawCapacityValue());
				} else {
					character.setVaginaVirgin(true);
				}
			}
			character.setVaginaWetness(character.getVaginaWetness().getValue() -1 + Util.random.nextInt(3)); // +1 or -1 either way
		}
		
		character.setAssStretchedCapacity(character.getAssRawCapacityValue());
		character.setBreastStretchedCapacity(character.getBreastRawCapacityValue());
		character.setFaceStretchedCapacity(character.getFaceRawCapacityValue());
		character.setPenisStretchedCapacity(character.getPenisRawCapacityValue());
		character.setVaginaStretchedCapacity(character.getVaginaRawCapacityValue());
	}
	
	public static void addFetishes(GameCharacter character) {
		List<Fetish> fetishes = new ArrayList<>();
		for(Fetish f : Fetish.values()) {
			if (f.getFetishesForAutomaticUnlock().isEmpty()) {
				fetishes.add(f);
			} else if (f.isAvailable(character)) {
				fetishes.add(f);
			}
		}

		double randomNumber = Math.random();
		// Add two fetishes:
		if(randomNumber>0.75f) {
			Fetish f = fetishes.get(Util.random.nextInt(fetishes.size()));
			character.addFetish(f);
			fetishes.remove(f);
			character.addFetish(fetishes.get(Util.random.nextInt(fetishes.size())));
			
		// Add one fetish:
		} else if(randomNumber>0.25f) {
			character.addFetish(fetishes.get(Util.random.nextInt(fetishes.size())));
		}
	}
	
	public static void equipClothing(GameCharacter character, boolean replaceUnsuitableClothing) {
		Colour primaryColour = Colour.allClothingColours.get(Util.random.nextInt(Colour.allClothingColours.size())),
				secondaryColour = Colour.allClothingColours.get(Util.random.nextInt(Colour.allClothingColours.size())),
				lingerieColour = Colour.lingerieColours.get(Util.random.nextInt(Colour.lingerieColours.size()));
		
		List<InventorySlot> inventorySlotsInPriorityOrder = new ArrayList<>();
		inventorySlotsInPriorityOrder.add(InventorySlot.TORSO); // Torso needs to be randomly decided first, to give girls a chance to wear a dress.
		for(InventorySlot slot : InventorySlot.values()) {
			if(slot!=InventorySlot.TORSO) {
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
				
				if((slot.isCoreClothing() || Math.random()>0.75f || slot.isJewellery()) && !character.isSlotIncompatible(slot) && character.getClothingInSlot(slot)==null) {
					if(!ClothingType.getCommonClothingMapFemaleIncludingAndrogynous().get(slot).isEmpty() && (ClothingType.slotBlockedByRace(character, slot) != character.getRace())) {
						if(slot==InventorySlot.CHEST && !character.hasBreasts()) {
							// Skip if character shouldn't be wearing a bra.
						} else {
							ClothingType ct = getClothingTypeForSlot(character, slot, ClothingType.getCommonClothingMapFemaleIncludingAndrogynous().get(slot));
							
							if(ct!=null) {
							character.equipClothingFromNowhere(ClothingType.generateClothing(
									ct,
									(slot == InventorySlot.GROIN || slot==InventorySlot.CHEST || slot==InventorySlot.SOCK
											? lingerieColour
											: (slot.isCoreClothing()
													?primaryColour
													:secondaryColour)),
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
				
				if((slot.isCoreClothing() || Math.random()>0.75f || slot.isJewellery()) && !character.isSlotIncompatible(slot) && character.getClothingInSlot(slot)==null) {
					if(!ClothingType.getCommonClothingMapMaleIncludingAndrogynous().get(slot).isEmpty() && (ClothingType.slotBlockedByRace(character, slot) != character.getRace())) {
						if(slot==InventorySlot.CHEST && !character.hasBreasts()) {
							// Skip if character shouldn't be wearing a bra.
						} else {
							ClothingType ct = getClothingTypeForSlot(character, slot, ClothingType.getCommonClothingMapMaleIncludingAndrogynous().get(slot));
							
							if(ct!=null) {
							character.equipClothingFromNowhere(ClothingType.generateClothing(
									ct,
									(slot == InventorySlot.GROIN || slot==InventorySlot.CHEST || slot==InventorySlot.SOCK
											? lingerieColour
											: (slot.isCoreClothing()
													?primaryColour
													:secondaryColour)),
									false), true, character);
							}
							
						}
					}
				}
			}
		}
	}
	
	private static ClothingType getClothingTypeForSlot(GameCharacter character, InventorySlot slot, List<ClothingType> clothingOptions) {
		List<ClothingType> availableClothing = new ArrayList<>();

		boolean canEquip=true;
		
		for(ClothingType ct : clothingOptions) {
			if(slot==InventorySlot.CHEST && !character.hasBreasts()) {
				canEquip = false;
				
			} else if(character.hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
				for(BlockedParts bp : ct.getBlockedPartsList()) {
					if(bp.blockedBodyParts.contains(CoverableArea.ANUS)
							|| (bp.blockedBodyParts.contains(CoverableArea.NIPPLES) && character.hasBreasts())
							|| (bp.blockedBodyParts.contains(CoverableArea.PENIS) && character.hasPenis())
							|| (bp.blockedBodyParts.contains(CoverableArea.VAGINA) && character.hasVagina())) {
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
}
