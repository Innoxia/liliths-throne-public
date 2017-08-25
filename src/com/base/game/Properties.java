package com.base.game;

import java.io.File;
import java.io.Serializable;
import java.util.EnumMap;
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
import org.w3c.dom.NodeList;

import com.base.game.character.gender.AndrogynousIdentification;
import com.base.game.character.gender.Gender;
import com.base.game.character.gender.GenderPronoun;
import com.base.game.character.race.Race;
import com.base.game.character.race.FurryPreference;
import com.base.main.Main;

/**
 * @since 0.1.0
 * @version 0.1.82
 * @author Innoxia
 */
public class Properties implements Serializable {
	private static final long serialVersionUID = 1L;
	public String lastSaveLocation = "", nameColour = "", name = "", race = "", quest = "", versionNumber="";
	public int fontSize = 18, level = 1, money = 0, humanEncountersLevel = 1, multiBreasts = 1;
	public boolean lightTheme = false, overwriteWarning = true, nonConContent = false, incestContent = false, forcedTransformationContent = false, pubicHairContent = false, bodyHairContent = false;
	
	public AndrogynousIdentification androgynousIdentification = AndrogynousIdentification.CLOTHING_FEMININE;

	public Map<KeyboardAction, KeyCodeWithModifiers> hotkeyMapPrimary, hotkeyMapSecondary;
	
	public Map<GenderPronoun, String> genderPronounFemale, genderPronounMale;
	
	public Map<Gender, Integer> genderPreferencesMap;

	public Map<Race, FurryPreference> raceFemininePreferencesMap, raceMasculinePreferencesMap;

	public Properties() {
		hotkeyMapPrimary = new EnumMap<>(KeyboardAction.class);
		hotkeyMapSecondary = new EnumMap<>(KeyboardAction.class);

		for (KeyboardAction ka : KeyboardAction.values()) {
			hotkeyMapPrimary.put(ka, ka.getPrimaryDefault());
			hotkeyMapSecondary.put(ka, ka.getSecondaryDefault());
		}
		
		genderPronounFemale = new EnumMap<>(GenderPronoun.class);
		genderPronounMale = new EnumMap<>(GenderPronoun.class);

		for (GenderPronoun gp : GenderPronoun.values()) {
			genderPronounFemale.put(gp, gp.getFeminine());
			genderPronounMale.put(gp, gp.getMasculine());
		}
		
		genderPreferencesMap = new EnumMap<>(Gender.class);
		for(Gender g : Gender.values()) {
			genderPreferencesMap.put(g, g.getGenderPreferenceDefault().getValue());
		}
		
		raceFemininePreferencesMap = new EnumMap<>(Race.class);
		raceMasculinePreferencesMap = new EnumMap<>(Race.class);
		for(Race r : Race.values()) {
			raceFemininePreferencesMap.put(r, FurryPreference.NORMAL);
			raceMasculinePreferencesMap.put(r, FurryPreference.NORMAL);
		}
	}
	
	public void savePropertiesAsXML(){
		try {
		
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element properties = doc.createElement("properties");
			doc.appendChild(properties);

			// Previous save information:
			Element previousSave = doc.createElement("previousSave");
			properties.appendChild(previousSave);
			createXMLElementWithValue(doc, previousSave, "location", lastSaveLocation);
			createXMLElementWithValue(doc, previousSave, "nameColour", nameColour);
			createXMLElementWithValue(doc, previousSave, "name", name);
			createXMLElementWithValue(doc, previousSave, "race", race);
			createXMLElementWithValue(doc, previousSave, "quest", quest);
			createXMLElementWithValue(doc, previousSave, "level", String.valueOf(level));
			createXMLElementWithValue(doc, previousSave, "money", String.valueOf(money));
			createXMLElementWithValue(doc, previousSave, "versionNumber", Main.VERSION_NUMBER);
			
			// Game settings:
			Element settings = doc.createElement("settings");
			properties.appendChild(settings);
			createXMLElementWithValue(doc, settings, "fontSize", String.valueOf(fontSize));
			createXMLElementWithValue(doc, settings, "lightTheme", String.valueOf(lightTheme));
			createXMLElementWithValue(doc, settings, "nonConContent", String.valueOf(nonConContent));
			createXMLElementWithValue(doc, settings, "incestContent", String.valueOf(incestContent));
			createXMLElementWithValue(doc, settings, "forcedTransformationContent", String.valueOf(forcedTransformationContent));
			createXMLElementWithValue(doc, settings, "pubicHairContent", String.valueOf(pubicHairContent));
			createXMLElementWithValue(doc, settings, "bodyHairContent", String.valueOf(bodyHairContent));
			createXMLElementWithValue(doc, settings, "overwriteWarning", String.valueOf(overwriteWarning));
			createXMLElementWithValue(doc, settings, "androgynousIdentification", String.valueOf(androgynousIdentification));
			createXMLElementWithValue(doc, settings, "humanEncountersLevel", String.valueOf(humanEncountersLevel));
			createXMLElementWithValue(doc, settings, "multiBreasts", String.valueOf(multiBreasts));
			
			
			// Game key binds:
			Element keyBinds = doc.createElement("keyBinds");
			properties.appendChild(keyBinds);
			for (KeyboardAction ka : KeyboardAction.values()) {
				Element element = doc.createElement("binding");
				keyBinds.appendChild(element);
				
				Attr bindName = doc.createAttribute("bindName");
				bindName.setValue(ka.toString());
				element.setAttributeNode(bindName);
				
				Attr primaryBind = doc.createAttribute("primaryBind");
				if(hotkeyMapPrimary.get(ka)!=null)
					primaryBind.setValue(hotkeyMapPrimary.get(ka).toString());
				else
					primaryBind.setValue("");
				element.setAttributeNode(primaryBind);
				
				Attr secondaryBind = doc.createAttribute("secondaryBind");
				if(hotkeyMapSecondary.get(ka)!=null)
					secondaryBind.setValue(hotkeyMapSecondary.get(ka).toString());
				else
					secondaryBind.setValue("");
				element.setAttributeNode(secondaryBind);
			}
			
			// Gender pronouns:
			Element pronouns = doc.createElement("genderPronouns");
			properties.appendChild(pronouns);
			for (GenderPronoun gp : GenderPronoun.values()) {
				Element element = doc.createElement("pronoun");
				pronouns.appendChild(element);
				
				Attr pronounName = doc.createAttribute("pronounName");
				pronounName.setValue(gp.toString());
				element.setAttributeNode(pronounName);
				
				Attr feminineValue = doc.createAttribute("feminineValue");
				if(genderPronounFemale.get(gp)!=null)
					feminineValue.setValue(genderPronounFemale.get(gp));
				else
					feminineValue.setValue(gp.getFeminine());
				element.setAttributeNode(feminineValue);
				
				Attr masculineValue = doc.createAttribute("masculineValue");
				if(genderPronounMale.get(gp)!=null)
					masculineValue.setValue(genderPronounMale.get(gp));
				else
					masculineValue.setValue(gp.getMasculine());
				element.setAttributeNode(masculineValue);
			}
			
			// Gender preferences:
			Element genderPreferences = doc.createElement("genderPreferences");
			properties.appendChild(genderPreferences);
			for (Gender g : Gender.values()) {
				Element element = doc.createElement("preference");
				genderPreferences.appendChild(element);
				
				Attr gender = doc.createAttribute("gender");
				gender.setValue(g.toString());
				element.setAttributeNode(gender);
				
				Attr value = doc.createAttribute("value");
				value.setValue(String.valueOf(genderPreferencesMap.get(g).intValue()));
				element.setAttributeNode(value);
			}
			
			// Race preferences:
			Element racePreferences = doc.createElement("furryPreferences");
			properties.appendChild(racePreferences);
			for (Race r : Race.values()) {
				Element element = doc.createElement("preferenceFeminine");
				racePreferences.appendChild(element);
				
				Attr race = doc.createAttribute("race");
				race.setValue(r.toString());
				element.setAttributeNode(race);
				
				Attr preference = doc.createAttribute("preference");
				preference.setValue(raceFemininePreferencesMap.get(r).toString());
				element.setAttributeNode(preference);
				
				element = doc.createElement("preferenceMasculine");
				racePreferences.appendChild(element);
				
				race = doc.createAttribute("race");
				race.setValue(r.toString());
				element.setAttributeNode(race);
				
				preference = doc.createAttribute("preference");
				preference.setValue(raceMasculinePreferencesMap.get(r).toString());
				element.setAttributeNode(preference);
			}
			
			// Write out to properties.xml:
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("data/properties.xml"));
		
			transformer.transform(source, result);
		
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	
	private void createXMLElementWithValue(Document doc, Element parentElement, String elementName, String value){
		Element element = doc.createElement(elementName);
		parentElement.appendChild(element);
		Attr attr = doc.createAttribute("value");
		attr.setValue(value);
		element.setAttributeNode(attr);
	}
	
	public void loadPropertiesFromXML(){
		
		if (new File("data/properties.xml").exists())
			try {
				File propertiesXML = new File("data/properties.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(propertiesXML);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				// Previous save information:
				NodeList nodes = doc.getElementsByTagName("previousSave");
				Element element = (Element) nodes.item(0);
				lastSaveLocation = ((Element)element.getElementsByTagName("location").item(0)).getAttribute("value");
				nameColour = ((Element)element.getElementsByTagName("nameColour").item(0)).getAttribute("value");
				name = ((Element)element.getElementsByTagName("name").item(0)).getAttribute("value");
				race = ((Element)element.getElementsByTagName("race").item(0)).getAttribute("value");
				quest = ((Element)element.getElementsByTagName("quest").item(0)).getAttribute("value");
				level = Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"));
				money = Integer.valueOf(((Element)element.getElementsByTagName("money").item(0)).getAttribute("value"));
				versionNumber = ((Element)element.getElementsByTagName("versionNumber").item(0)).getAttribute("value");
				
				// Settings:
				nodes = doc.getElementsByTagName("settings");
				element = (Element) nodes.item(0);
				fontSize = Integer.valueOf(((Element)element.getElementsByTagName("fontSize").item(0)).getAttribute("value"));
				lightTheme = ((((Element)element.getElementsByTagName("lightTheme").item(0)).getAttribute("value")).equals("true"));
				
				nonConContent = ((((Element)element.getElementsByTagName("nonConContent").item(0)).getAttribute("value")).equals("true"));
				incestContent = ((((Element)element.getElementsByTagName("incestContent").item(0)).getAttribute("value")).equals("true"));
				forcedTransformationContent = ((((Element)element.getElementsByTagName("forcedTransformationContent").item(0)).getAttribute("value")).equals("true"));
				pubicHairContent = ((((Element)element.getElementsByTagName("pubicHairContent").item(0)).getAttribute("value")).equals("true"));
				bodyHairContent = ((((Element)element.getElementsByTagName("bodyHairContent").item(0)).getAttribute("value")).equals("true"));
				
				overwriteWarning = ((((Element)element.getElementsByTagName("overwriteWarning").item(0)).getAttribute("value")).equals("true"));
				if(element.getElementsByTagName("androgynousIdentification").item(0)!=null) {
					androgynousIdentification = AndrogynousIdentification.valueOf(((Element)element.getElementsByTagName("androgynousIdentification").item(0)).getAttribute("value"));
				}
				
				if((Element)element.getElementsByTagName("humanEncountersLevel").item(0)!=null) {
					humanEncountersLevel = Integer.valueOf(((Element)element.getElementsByTagName("humanEncountersLevel").item(0)).getAttribute("value"));
				} else {
					humanEncountersLevel = 1;
				}
				
				if((Element)element.getElementsByTagName("multiBreasts").item(0)!=null) {
					multiBreasts = Integer.valueOf(((Element)element.getElementsByTagName("multiBreasts").item(0)).getAttribute("value"));
				} else {
					multiBreasts = 1;
				}
				
				// Keys:
				nodes = doc.getElementsByTagName("keyBinds");
				element = (Element) nodes.item(0);
				for(int i=0; i<element.getElementsByTagName("binding").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("binding").item(i));
					
					if(e.getAttribute("primaryBind")!="")
						hotkeyMapPrimary.put(KeyboardAction.valueOf(e.getAttribute("bindName")), KeyCodeWithModifiers.fromString(e.getAttribute("primaryBind")));
					else
						hotkeyMapPrimary.put(KeyboardAction.valueOf(e.getAttribute("bindName")), null);
					
					if(e.getAttribute("secondaryBind")!="")
						hotkeyMapSecondary.put(KeyboardAction.valueOf(e.getAttribute("bindName")), KeyCodeWithModifiers.fromString(e.getAttribute("secondaryBind")));
					else
						hotkeyMapSecondary.put(KeyboardAction.valueOf(e.getAttribute("bindName")), null);
				}
				
				// Gender pronouns:
				nodes = doc.getElementsByTagName("genderPronouns");
				element = (Element) nodes.item(0);
				for(int i=0; i<element.getElementsByTagName("pronoun").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("pronoun").item(i));
					
					if(GenderPronoun.valueOf(e.getAttribute("pronounName"))!=null) {
						if(e.getAttribute("feminineValue")!="") {
							genderPronounFemale.put(GenderPronoun.valueOf(e.getAttribute("pronounName")), e.getAttribute("feminineValue"));
						} else {
							genderPronounFemale.put(GenderPronoun.valueOf(e.getAttribute("pronounName")), GenderPronoun.valueOf(e.getAttribute("pronounName")).getFeminine());
						}
						
						if(e.getAttribute("masculineValue")!="") {
							genderPronounMale.put(GenderPronoun.valueOf(e.getAttribute("pronounName")), e.getAttribute("masculineValue"));
						} else {
							genderPronounMale.put(GenderPronoun.valueOf(e.getAttribute("pronounName")), GenderPronoun.valueOf(e.getAttribute("pronounName")).getMasculine());
						}
					}
				}
				
				// Gender preferences:
				nodes = doc.getElementsByTagName("genderPreferences");
				element = (Element) nodes.item(0);
				for(int i=0; i<element.getElementsByTagName("preference").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("preference").item(i));
					
					if(e.getAttribute("gender")!="")
						genderPreferencesMap.put(Gender.valueOf(e.getAttribute("gender")), Integer.valueOf(e.getAttribute("value")));
				}
				
				// Race preferences:
				nodes = doc.getElementsByTagName("furryPreferences");
				element = (Element) nodes.item(0);
				for(int i=0; i<element.getElementsByTagName("preferenceFeminine").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("preferenceFeminine").item(i));
					
					if(e.getAttribute("race")!="")
						raceFemininePreferencesMap.put(Race.valueOf(e.getAttribute("race")), FurryPreference.valueOf(e.getAttribute("preference")));
				}
				for(int i=0; i<element.getElementsByTagName("preferenceMasculine").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("preferenceMasculine").item(i));
					
					if(e.getAttribute("race")!="")
						raceMasculinePreferencesMap.put(Race.valueOf(e.getAttribute("race")), FurryPreference.valueOf(e.getAttribute("preference")));
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
