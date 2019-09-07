package com.lilithsthrone.game;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.gender.*;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesPreference;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryEncyclopediaUnlock;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.settings.*;
import com.lilithsthrone.main.Main;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

/**
 * @since 0.1.0
 * @version 0.3.4
 * @author Innoxia
 */
public class Properties {

	public String lastSaveLocation = "";
	public String lastQuickSaveName = "";
	public String nameColour = "";
	public String name = "";
	public String race = "";
	public String quest = "";
	public String versionNumber = "";
	public String preferredArtist = "jam";

	public int fontSize = 18;
	public int level = 1;
	public int money = 0;
	public int arcaneEssences = 0;
	public int humanEncountersLevel = 1;
	public int taurFurryLevel = 1;
	
	
	public int multiBreasts = 1;
	public static String[] multiBreastsLabels = new String[] {"Off", "Furry-only", "On"};
	public static String[] multiBreastsDescriptions = new String[] {
			"Randomly-generated NPCs will never have multiple rows of breasts.",
			"Randomly-generated NPCs will only have multiple rows of breasts if they have furry skin. (Default setting.)",
			"Randomly-generated NPCs will have multiple rows of breasts if their breast type is furry (starts at 'Minor morph' level)."};
	
	public int udders = 1;
	public static String[] uddersLabels = new String[] {"Off", "Taur-only", "On"};
	public static String[] uddersDescriptions = new String[] {
			"Neither randomly-generated taurs nor anthro-morphs will ever have udders or crotch-boobs.",
			"Randomly-generated NPCs will only have udders or crotch-boobs if they have a non-bipedal body. (Default setting.)",
			"Randomly-generated greater-anthro-morphs, as well as taurs, will have udders and crotch boobs."};
	

	public int autoSaveFrequency = 0;
	public static String[] autoSaveLabels = new String[] {"Always", "Daily", "Weekly"};
	public static String[] autoSaveDescriptions = new String[] {
			"The game will autosave every time you transition to a new map.",
			"The game will autosave when you transition to a new map, at a maximum rate of once per in-game day.",
			"The game will autosave when you transition to a new map, at a maximum rate of once per in-game week."};
	
	public int forcedTFPercentage = 40;
	public int forcedFetishPercentage = 0;

	public float randomRacePercentage = 0.15f;

	public int pregnancyBreastGrowthVariance = 2;
	public int pregnancyBreastGrowth = 1;
	public int pregnancyBreastGrowthLimit = CupSize.E.getMeasurement();
	public int pregnancyLactationIncreaseVariance = 100;
	public int pregnancyLactationIncrease = 250;
	public int pregnancyLactationLimit = 1000;
	
	public int breastSizePreference = 0;
	public int penisSizePreference = 0;
//	public String[] breastSizePreferenceLabels = new String[] {"Minimum", "Tiny", "Small", "Reduced", "Default", "Big", "Huge", "Massive", "Maximum"};
//	public int[] breastSizePreferenceMultiplierLabels = new int[] {-8, -6, -4, -2, 0, 2, 4, 8, 16};
	
	public Set<PropertyValue> values;

	// Difficulty settings
	public DifficultyLevel difficultyLevel = DifficultyLevel.NORMAL;
	public float AIblunderRate = 0.0f; /// The amount of times the AI will use random weight selection for an action instead of the proper one. 1.0 means every time, 0.0 means never.
	
	public AndrogynousIdentification androgynousIdentification = AndrogynousIdentification.CLOTHING_FEMININE;

	public Map<KeyboardAction, KeyCodeWithModifiers> hotkeyMapPrimary;
	public Map<KeyboardAction, KeyCodeWithModifiers> hotkeyMapSecondary;

	public Map<GenderNames, String> genderNameFemale;
	public Map<GenderNames, String> genderNameMale;
	public Map<GenderNames, String> genderNameNeutral;
	
	public Map<GenderPronoun, String> genderPronounFemale;
	public Map<GenderPronoun, String> genderPronounMale;
	
	public Map<Gender, Integer> genderPreferencesMap;
	
	public Map<SexualOrientation, Integer> orientationPreferencesMap;

	public Map<PronounType, Map<AgeCategory, Integer>> agePreferencesMap;
	
	private Map<Subspecies, FurryPreference> subspeciesFeminineFurryPreferencesMap;
	private Map<Subspecies, FurryPreference> subspeciesMasculineFurryPreferencesMap;
	
	private Map<Subspecies, SubspeciesPreference> subspeciesFemininePreferencesMap;
	private Map<Subspecies, SubspeciesPreference> subspeciesMasculinePreferencesMap;
	
	// Transformation Settings
	private FurryPreference forcedTFPreference;
	private ForcedTFTendency forcedTFTendency;
	private ForcedFetishTendency forcedFetishTendency;
	
	// Discoveries:
	private Set<AbstractItemType> itemsDiscovered;
	private Set<AbstractWeaponType> weaponsDiscovered;
	private Set<AbstractClothingType> clothingDiscovered;
	private Set<Subspecies> subspeciesDiscovered;
	private Set<Subspecies> subspeciesAdvancedKnowledge;

	public Properties() {
		values = new HashSet<>();
		for(PropertyValue value : PropertyValue.values()) {
			if(value.getDefaultValue()) {
				values.add(value);
			}
		}

		hotkeyMapPrimary = new EnumMap<>(KeyboardAction.class);
		hotkeyMapSecondary = new EnumMap<>(KeyboardAction.class);

		for (KeyboardAction ka : KeyboardAction.values()) {
			hotkeyMapPrimary.put(ka, ka.getPrimaryDefault());
			hotkeyMapSecondary.put(ka, ka.getSecondaryDefault());
		}
		
		genderNameFemale = new EnumMap<>(GenderNames.class);
		genderNameMale = new EnumMap<>(GenderNames.class);
		genderNameNeutral = new EnumMap<>(GenderNames.class);
		
		for (GenderNames gn : GenderNames.values()) {
			genderNameFemale.put(gn, gn.getFeminine());
			genderNameMale.put(gn, gn.getMasculine());
			genderNameNeutral.put(gn, gn.getNeutral());
		}
		
		genderPronounFemale = new EnumMap<>(GenderPronoun.class);
		genderPronounMale = new EnumMap<>(GenderPronoun.class);

		for (GenderPronoun gp : GenderPronoun.values()) {
			genderPronounFemale.put(gp, gp.getFeminine());
			genderPronounMale.put(gp, gp.getMasculine());
		}
		
		resetGenderPreferences();

		orientationPreferencesMap = new EnumMap<>(SexualOrientation.class);
		for(SexualOrientation o : SexualOrientation.values()) {
			orientationPreferencesMap.put(o, o.getOrientationPreferenceDefault().getValue());
		}
		
		resetAgePreferences();
		
		forcedTFPreference = FurryPreference.NORMAL;
		forcedTFTendency = ForcedTFTendency.NEUTRAL;
		forcedFetishTendency = ForcedFetishTendency.NEUTRAL;
		
		subspeciesFeminineFurryPreferencesMap = new EnumMap<>(Subspecies.class);
		subspeciesMasculineFurryPreferencesMap = new EnumMap<>(Subspecies.class);
		for(Subspecies s : Subspecies.values()) {
			subspeciesFeminineFurryPreferencesMap.put(s, s.getRace().getDefaultFemininePreference());
			subspeciesMasculineFurryPreferencesMap.put(s, s.getRace().getDefaultMasculinePreference());
		}
		
		subspeciesFemininePreferencesMap = new EnumMap<>(Subspecies.class);
		subspeciesMasculinePreferencesMap = new EnumMap<>(Subspecies.class);
		for(Subspecies s : Subspecies.values()) {
			subspeciesFemininePreferencesMap.put(s, s.getSubspeciesPreferenceDefault());
			subspeciesMasculinePreferencesMap.put(s, s.getSubspeciesPreferenceDefault());
		}
		
		itemsDiscovered = new HashSet<>();
		weaponsDiscovered = new HashSet<>();
		clothingDiscovered = new HashSet<>();
		subspeciesDiscovered = new HashSet<>();
		subspeciesAdvancedKnowledge = new HashSet<>();
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
			createXMLElementWithValue(doc, previousSave, "arcaneEssences", String.valueOf(arcaneEssences));
			createXMLElementWithValue(doc, previousSave, "versionNumber", Main.VERSION_NUMBER);
			createXMLElementWithValue(doc, previousSave, "lastQuickSaveName", lastQuickSaveName);
			
			

			Element valuesElement = doc.createElement("propertyValues");
			properties.appendChild(valuesElement);
			for(PropertyValue value : PropertyValue.values()) {
				if(values.contains(value)) {
					CharacterUtils.createXMLElementWithValue(doc, valuesElement, "propertyValue", value.toString());
				}
			}
			
			// Game settings:
			Element settings = doc.createElement("settings");
			properties.appendChild(settings);
			createXMLElementWithValue(doc, settings, "fontSize", String.valueOf(fontSize));
			
			createXMLElementWithValue(doc, settings, "preferredArtist", preferredArtist);
			
			createXMLElementWithValue(doc, settings, "androgynousIdentification", String.valueOf(androgynousIdentification));
			createXMLElementWithValue(doc, settings, "humanEncountersLevel", String.valueOf(humanEncountersLevel));
			createXMLElementWithValue(doc, settings, "taurFurryLevel", String.valueOf(taurFurryLevel));
			createXMLElementWithValue(doc, settings, "multiBreasts", String.valueOf(multiBreasts));
			createXMLElementWithValue(doc, settings, "udders", String.valueOf(udders));
			createXMLElementWithValue(doc, settings, "autoSaveFrequency", String.valueOf(autoSaveFrequency));
			createXMLElementWithValue(doc, settings, "forcedTFPercentage", String.valueOf(forcedTFPercentage));
			createXMLElementWithValue(doc, settings, "randomRacePercentage", String.valueOf(randomRacePercentage)); 

			createXMLElementWithValue(doc, settings, "pregnancyBreastGrowthVariance", String.valueOf(pregnancyBreastGrowthVariance));
			createXMLElementWithValue(doc, settings, "pregnancyBreastGrowth", String.valueOf(pregnancyBreastGrowth));
			createXMLElementWithValue(doc, settings, "pregnancyBreastGrowthLimit", String.valueOf(pregnancyBreastGrowthLimit));
			createXMLElementWithValue(doc, settings, "pregnancyLactationIncreaseVariance", String.valueOf(pregnancyLactationIncreaseVariance));
			createXMLElementWithValue(doc, settings, "pregnancyLactationIncrease", String.valueOf(pregnancyLactationIncrease));
			createXMLElementWithValue(doc, settings, "pregnancyLactationLimit", String.valueOf(pregnancyLactationLimit));

			createXMLElementWithValue(doc, settings, "breastSizePreference", String.valueOf(breastSizePreference));
			createXMLElementWithValue(doc, settings, "penisSizePreference", String.valueOf(penisSizePreference));
			
			createXMLElementWithValue(doc, settings, "forcedFetishPercentage", String.valueOf(forcedFetishPercentage));

			createXMLElementWithValue(doc, settings, "difficultyLevel", difficultyLevel.toString());
			createXMLElementWithValue(doc, settings, "AIblunderRate", String.valueOf(AIblunderRate));
			
			
			
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
			
			// Gender names:
			Element genderNames = doc.createElement("genderNames");
			properties.appendChild(genderNames);
			for (GenderNames gp : GenderNames.values()) {
				Element element = doc.createElement("genderName");
				genderNames.appendChild(element);
				
				Attr pronounName = doc.createAttribute("name");
				pronounName.setValue(gp.toString());
				element.setAttributeNode(pronounName);
				
				Attr feminineValue = doc.createAttribute("feminineValue");
				if(genderNameFemale.get(gp)!=null) {
					feminineValue.setValue(genderNameFemale.get(gp));
				} else {
					feminineValue.setValue(gp.getFeminine());
				}
				element.setAttributeNode(feminineValue);
				
				Attr masculineValue = doc.createAttribute("masculineValue");
				if(genderNameMale.get(gp)!=null) {
					masculineValue.setValue(genderNameMale.get(gp));
				} else {
					masculineValue.setValue(gp.getMasculine());
				}
				element.setAttributeNode(masculineValue);
				
				Attr neutralValue = doc.createAttribute("neutralValue");
				if(genderNameNeutral.get(gp)!=null) {
					neutralValue.setValue(genderNameNeutral.get(gp));
				} else {
					neutralValue.setValue(gp.getNeutral());
				}
				element.setAttributeNode(neutralValue);
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

			// Sexual orientation preferences:
			Element orientationPreferences = doc.createElement("orientationPreferences");
			properties.appendChild(orientationPreferences);
			for (SexualOrientation o : SexualOrientation.values()) {
				Element element = doc.createElement("preference");
				orientationPreferences.appendChild(element);
				
				Attr orientation = doc.createAttribute("orientation");
				orientation.setValue(o.toString());
				element.setAttributeNode(orientation);
				
				Attr value = doc.createAttribute("value");
				value.setValue(String.valueOf(orientationPreferencesMap.get(o).intValue()));
				element.setAttributeNode(value);
			}
			
			// Age preferences:
			Element agePreferences = doc.createElement("agePreferences");
			properties.appendChild(agePreferences);
			for (AgeCategory ageCat : AgeCategory.values()) {
				Element element = doc.createElement("preference");
				agePreferences.appendChild(element);
				
				Attr age = doc.createAttribute("age");
				age.setValue(ageCat.toString());
				element.setAttributeNode(age);
				
				for(PronounType pronoun : PronounType.values()) {
					Attr value = doc.createAttribute(pronoun.toString());
					value.setValue(String.valueOf(agePreferencesMap.get(pronoun).get(ageCat).intValue()));
					element.setAttributeNode(value);
				}
			}
			
			// Forced TF settings:
			createXMLElementWithValue(doc, settings, "forcedTFPreference", String.valueOf(forcedTFPreference));
			createXMLElementWithValue(doc, settings, "forcedTFTendency", String.valueOf(forcedTFTendency));
			createXMLElementWithValue(doc, settings, "forcedFetishTendency", String.valueOf(forcedFetishTendency));
			
			// Race preferences:
			Element racePreferences = doc.createElement("subspeciesPreferences");
			properties.appendChild(racePreferences);
			for (Subspecies s : Subspecies.values()) {
				Element element = doc.createElement("preferenceFeminine");
				racePreferences.appendChild(element);
				
				Attr race = doc.createAttribute("subspecies");
				race.setValue(s.toString());
				element.setAttributeNode(race);
				
				Attr preference = doc.createAttribute("preference");
				preference.setValue(subspeciesFemininePreferencesMap.get(s).toString());
				element.setAttributeNode(preference);

				preference = doc.createAttribute("furryPreference");
				preference.setValue(subspeciesFeminineFurryPreferencesMap.get(s).toString());
				element.setAttributeNode(preference);
				
				element = doc.createElement("preferenceMasculine");
				racePreferences.appendChild(element);
				
				race = doc.createAttribute("subspecies");
				race.setValue(s.toString());
				element.setAttributeNode(race);
				
				preference = doc.createAttribute("preference");
				preference.setValue(subspeciesMasculinePreferencesMap.get(s).toString());
				element.setAttributeNode(preference);
				
				preference = doc.createAttribute("furryPreference");
				preference.setValue(subspeciesMasculineFurryPreferencesMap.get(s).toString());
				element.setAttributeNode(preference);
			}
			
			// Discoveries:
			Element itemsDiscovered = doc.createElement("itemsDiscovered");
			properties.appendChild(itemsDiscovered);
			for (AbstractItemType itemType : this.itemsDiscovered) {
				try {
					if(itemType!=null) {
						Element element = doc.createElement("itemType");
						itemsDiscovered.appendChild(element);
						
						Attr hash = doc.createAttribute("id");
						hash.setValue(itemType.getId());
						element.setAttributeNode(hash);
					}
				} catch(Exception ex) {
					// Catch errors from modded items being removed
				}
			}
			
			Element weaponsDiscovered = doc.createElement("weaponsDiscovered");
			properties.appendChild(weaponsDiscovered);
			for (AbstractWeaponType weaponType : this.weaponsDiscovered) {
				try {
					if(weaponType!=null) {
						Element element = doc.createElement("weaponType");
						weaponsDiscovered.appendChild(element);
						
						Attr hash = doc.createAttribute("id");
						hash.setValue(weaponType.getId());
						element.setAttributeNode(hash);
					}
				} catch(Exception ex) {
					// Catch errors from modded weapons being removed
				}
			}
			
			Element clothingDiscovered = doc.createElement("clothingDiscovered");
			properties.appendChild(clothingDiscovered);
			for (AbstractClothingType clothingType : this.clothingDiscovered) {
				try {
					if(clothingType!=null) {
						Element element = doc.createElement("clothingType");
						clothingDiscovered.appendChild(element);
						
						Attr hash = doc.createAttribute("id");
						hash.setValue(clothingType.getId());
						element.setAttributeNode(hash);
					}
				} catch(Exception ex) {
					// Catch errors from modded items being removed
				}
			}
			
			Element racesDiscovered = doc.createElement("racesDiscovered");
			properties.appendChild(racesDiscovered);
			for (Subspecies subspecies : Subspecies.values()) {
				Element element = doc.createElement("raceDiscovery");
				racesDiscovered.appendChild(element);
				
				Attr discovered = doc.createAttribute("race");
				discovered.setValue(subspecies.toString());
				element.setAttributeNode(discovered);
				
				discovered = doc.createAttribute("discovered");
				discovered.setValue(String.valueOf(this.subspeciesDiscovered.contains(subspecies)));
				element.setAttributeNode(discovered);
				
				discovered = doc.createAttribute("advancedKnowledge");
				discovered.setValue(String.valueOf(this.subspeciesAdvancedKnowledge.contains(subspecies)));
				element.setAttributeNode(discovered);
			}
			
			
			// Write out to properties.xml:
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult("data/properties.xml");
		
			transformer.transform(source, result);
		
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
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
				if(element.getElementsByTagName("arcaneEssences").item(0)!=null) {
					arcaneEssences = Integer.valueOf(((Element)element.getElementsByTagName("arcaneEssences").item(0)).getAttribute("value"));
				}
				versionNumber = ((Element)element.getElementsByTagName("versionNumber").item(0)).getAttribute("value");
				if(element.getElementsByTagName("lastQuickSaveName").item(0)!=null) {
					lastQuickSaveName = ((Element)element.getElementsByTagName("lastQuickSaveName").item(0)).getAttribute("value");
				}
				
				nodes = doc.getElementsByTagName("propertyValues");
				element = (Element) nodes.item(0);
				if(element!=null) {
					values.clear();
					if(Main.isVersionOlderThan(versionNumber, "0.2.7")) {
						values.add(PropertyValue.analContent);
						values.add(PropertyValue.futanariTesticles);
						values.add(PropertyValue.cumRegenerationContent);
					}
					if(Main.isVersionOlderThan(versionNumber, "0.2.7.6")) {
						values.add(PropertyValue.ageContent);
					}
					if(Main.isVersionOlderThan(versionNumber, "0.2.12")) {
						values.add(PropertyValue.autoSexClothingManagement);
					}
					if(Main.isVersionOlderThan(versionNumber, "0.3.0.5")) {
						values.add(PropertyValue.bipedalCloaca);
					}
					if(Main.isVersionOlderThan(versionNumber, "0.3.1.7")) {
						values.add(PropertyValue.footContent);
					}
					if(Main.isVersionOlderThan(versionNumber, "0.3.3.9")) {
						values.add(PropertyValue.enchantmentLimits);
					}
					for(int i=0; i < element.getElementsByTagName("propertyValue").getLength(); i++){
						Element e = (Element) element.getElementsByTagName("propertyValue").item(i);
						
						try {
							values.add(PropertyValue.valueOf(e.getAttribute("value")));
						} catch(Exception ex) {
						}
					}
				} else {
					// Old values support:
					nodes = doc.getElementsByTagName("settings");
					element = (Element) nodes.item(0);
					
					this.setValue(PropertyValue.lightTheme, Boolean.valueOf((((Element)element.getElementsByTagName("lightTheme").item(0)).getAttribute("value"))));
					this.setValue(PropertyValue.furryTailPenetrationContent, Boolean.valueOf((((Element)element.getElementsByTagName("furryTailPenetrationContent").item(0)).getAttribute("value"))));
					this.setValue(PropertyValue.nonConContent, Boolean.valueOf((((Element)element.getElementsByTagName("nonConContent").item(0)).getAttribute("value"))));
					this.setValue(PropertyValue.incestContent, Boolean.valueOf((((Element)element.getElementsByTagName("incestContent").item(0)).getAttribute("value"))));
					
					if(element.getElementsByTagName("inflationContent").item(0)!=null) {
						this.setValue(PropertyValue.inflationContent, Boolean.valueOf((((Element)element.getElementsByTagName("inflationContent").item(0)).getAttribute("value"))));
					}
					this.setValue(PropertyValue.facialHairContent, Boolean.valueOf((((Element)element.getElementsByTagName("facialHairContent").item(0)).getAttribute("value"))));
					this.setValue(PropertyValue.pubicHairContent, Boolean.valueOf((((Element)element.getElementsByTagName("pubicHairContent").item(0)).getAttribute("value"))));
					this.setValue(PropertyValue.bodyHairContent, Boolean.valueOf((((Element)element.getElementsByTagName("bodyHairContent").item(0)).getAttribute("value"))));
					if(element.getElementsByTagName("feminineBeardsContent").item(0)!=null) {
						this.setValue(PropertyValue.feminineBeardsContent, Boolean.valueOf((((Element)element.getElementsByTagName("feminineBeardsContent").item(0)).getAttribute("value"))));
					}
					if(element.getElementsByTagName("lactationContent").item(0)!=null) {
						this.setValue(PropertyValue.lactationContent, Boolean.valueOf((((Element)element.getElementsByTagName("lactationContent").item(0)).getAttribute("value"))));
					}
					if(element.getElementsByTagName("cumRegenerationContent").item(0)!=null) {
						this.setValue(PropertyValue.cumRegenerationContent, Boolean.valueOf((((Element)element.getElementsByTagName("cumRegenerationContent").item(0)).getAttribute("value"))));
					}
					if(element.getElementsByTagName("urethralContent").item(0)!=null) {
						this.setValue(PropertyValue.urethralContent, Boolean.valueOf((((Element)element.getElementsByTagName("urethralContent").item(0)).getAttribute("value"))));
					}
					
					this.setValue(PropertyValue.newWeaponDiscovered, Boolean.valueOf(((Element)element.getElementsByTagName("newWeaponDiscovered").item(0)).getAttribute("value")));
					this.setValue(PropertyValue.newClothingDiscovered, Boolean.valueOf(((Element)element.getElementsByTagName("newClothingDiscovered").item(0)).getAttribute("value")));
					this.setValue(PropertyValue.newItemDiscovered, Boolean.valueOf(((Element)element.getElementsByTagName("newItemDiscovered").item(0)).getAttribute("value")));
					this.setValue(PropertyValue.newRaceDiscovered, Boolean.valueOf(((Element)element.getElementsByTagName("newRaceDiscovered").item(0)).getAttribute("value")));
					
					this.setValue(PropertyValue.overwriteWarning, Boolean.valueOf(((Element)element.getElementsByTagName("overwriteWarning").item(0)).getAttribute("value")));
					this.setValue(PropertyValue.fadeInText, Boolean.valueOf(((Element)element.getElementsByTagName("fadeInText").item(0)).getAttribute("value")));
					
					if(element.getElementsByTagName("calendarDisplay").item(0)!=null) {
						this.setValue(PropertyValue.calendarDisplay, Boolean.valueOf(((Element)element.getElementsByTagName("calendarDisplay").item(0)).getAttribute("value")));
					}
					
					if(element.getElementsByTagName("twentyFourHourTime").item(0)!=null) {
						this.setValue(PropertyValue.twentyFourHourTime, Boolean.valueOf(((Element)element.getElementsByTagName("twentyFourHourTime").item(0)).getAttribute("value")));
					}
				}
				
				// Settings:
				nodes = doc.getElementsByTagName("settings");
				element = (Element) nodes.item(0);
				fontSize = Integer.valueOf(((Element)element.getElementsByTagName("fontSize").item(0)).getAttribute("value"));
				
				if(element.getElementsByTagName("preferredArtist").item(0)!=null) {
					preferredArtist =((Element)element.getElementsByTagName("preferredArtist").item(0)).getAttribute("value");
				}
				
				if(element.getElementsByTagName("difficultyLevel").item(0)!=null) {
					difficultyLevel = DifficultyLevel.valueOf(((Element)element.getElementsByTagName("difficultyLevel").item(0)).getAttribute("value"));
				}

				if(element.getElementsByTagName("AIblunderRate").item(0)!=null) {
					AIblunderRate = Float.valueOf(((Element)element.getElementsByTagName("AIblunderRate").item(0)).getAttribute("value"));
				}
				
				if(element.getElementsByTagName("androgynousIdentification").item(0)!=null) {
					androgynousIdentification = AndrogynousIdentification.valueOf(((Element)element.getElementsByTagName("androgynousIdentification").item(0)).getAttribute("value"));
				}
				
				if(element.getElementsByTagName("humanEncountersLevel").item(0)!=null) {
					humanEncountersLevel = Integer.valueOf(((Element)element.getElementsByTagName("humanEncountersLevel").item(0)).getAttribute("value"));
				} else {
					humanEncountersLevel = 1;
				}
				
				if(element.getElementsByTagName("taurFurryLevel").item(0)!=null) {
					taurFurryLevel = Integer.valueOf(((Element)element.getElementsByTagName("taurFurryLevel").item(0)).getAttribute("value"));
				} else {
					taurFurryLevel = 1;
				}
				
				if(element.getElementsByTagName("multiBreasts").item(0)!=null) {
					multiBreasts = Integer.valueOf(((Element)element.getElementsByTagName("multiBreasts").item(0)).getAttribute("value"));
				} else {
					multiBreasts = 1;
				}
				
				if(element.getElementsByTagName("udders").item(0)!=null) {
					udders = Integer.valueOf(((Element)element.getElementsByTagName("udders").item(0)).getAttribute("value"));
				} else {
					udders = 1;
				}
				
				if(element.getElementsByTagName("autoSaveFrequency").item(0)!=null) {
					autoSaveFrequency = Integer.valueOf(((Element)element.getElementsByTagName("autoSaveFrequency").item(0)).getAttribute("value"));
				} else {
					autoSaveFrequency = 0;
				}
				
				if(element.getElementsByTagName("forcedTFPercentage").item(0)!=null) {
					forcedTFPercentage = Integer.valueOf(((Element)element.getElementsByTagName("forcedTFPercentage").item(0)).getAttribute("value"));
				}
				if(element.getElementsByTagName("forcedFetishPercentage").item(0)!=null) {
					forcedFetishPercentage = Integer.valueOf(((Element)element.getElementsByTagName("forcedFetishPercentage").item(0)).getAttribute("value"));
				}
				// Randomized percentage of body pref race.
				if(element.getElementsByTagName("randomRacePercentage").item(0)!=null) {
					randomRacePercentage = Float.parseFloat(((Element)element.getElementsByTagName("randomRacePercentage").item(0)).getAttribute("value"));
				}

				// Forced TF preference:
				if(element.getElementsByTagName("forcedTFPreference").item(0)!=null) {
					forcedTFPreference = FurryPreference.valueOf(((Element)element.getElementsByTagName("forcedTFPreference").item(0)).getAttribute("value"));
				}
				if(element.getElementsByTagName("forcedTFTendency").item(0)!=null) {
					forcedTFTendency = ForcedTFTendency.valueOf(((Element)element.getElementsByTagName("forcedTFTendency").item(0)).getAttribute("value"));
				}
				if(element.getElementsByTagName("forcedFetishTendency").item(0)!=null) {
					forcedFetishTendency = ForcedFetishTendency.valueOf(((Element)element.getElementsByTagName("forcedFetishTendency").item(0)).getAttribute("value"));
				}
				
				try {
					pregnancyBreastGrowthVariance = Integer.valueOf(((Element)element.getElementsByTagName("pregnancyBreastGrowthVariance").item(0)).getAttribute("value"));
					pregnancyBreastGrowth = Integer.valueOf(((Element)element.getElementsByTagName("pregnancyBreastGrowth").item(0)).getAttribute("value"));
					pregnancyBreastGrowthLimit = Integer.valueOf(((Element)element.getElementsByTagName("pregnancyBreastGrowthLimit").item(0)).getAttribute("value"));
					pregnancyLactationIncreaseVariance = Integer.valueOf(((Element)element.getElementsByTagName("pregnancyLactationIncreaseVariance").item(0)).getAttribute("value"));
					pregnancyLactationIncrease = Integer.valueOf(((Element)element.getElementsByTagName("pregnancyLactationIncrease").item(0)).getAttribute("value"));
					pregnancyLactationLimit = Integer.valueOf(((Element)element.getElementsByTagName("pregnancyLactationLimit").item(0)).getAttribute("value"));

					breastSizePreference = Integer.valueOf(((Element)element.getElementsByTagName("breastSizePreference").item(0)).getAttribute("value"));
					penisSizePreference = Integer.valueOf(((Element)element.getElementsByTagName("penisSizePreference").item(0)).getAttribute("value"));
				}catch(Exception ex) {
				}
				
				// Keys:
				nodes = doc.getElementsByTagName("keyBinds");
				element = (Element) nodes.item(0);
				if(element!=null && element.getElementsByTagName("binding")!=null) {
					for(int i=0; i<element.getElementsByTagName("binding").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("binding").item(i));
						
						if(!e.getAttribute("primaryBind").isEmpty())
							hotkeyMapPrimary.put(KeyboardAction.valueOf(e.getAttribute("bindName")), KeyCodeWithModifiers.fromString(e.getAttribute("primaryBind")));
						else
							hotkeyMapPrimary.put(KeyboardAction.valueOf(e.getAttribute("bindName")), null);
						
						if(!e.getAttribute("secondaryBind").isEmpty())
							hotkeyMapSecondary.put(KeyboardAction.valueOf(e.getAttribute("bindName")), KeyCodeWithModifiers.fromString(e.getAttribute("secondaryBind")));
						else
							hotkeyMapSecondary.put(KeyboardAction.valueOf(e.getAttribute("bindName")), null);
					}
				}
				
				// Gender names:
				nodes = doc.getElementsByTagName("genderNames");
				element = (Element) nodes.item(0);
				if(element!=null && element.getElementsByTagName("genderName")!=null) {
					for(int i=0; i<element.getElementsByTagName("genderName").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("genderName").item(i));
	
						if(!e.getAttribute("feminineValue").isEmpty()) {
							genderNameFemale.put(GenderNames.valueOf(e.getAttribute("name")), e.getAttribute("feminineValue"));
						} else {
							genderNameFemale.put(GenderNames.valueOf(e.getAttribute("name")), GenderPronoun.valueOf(e.getAttribute("name")).getFeminine());
						}
	
						if(!e.getAttribute("masculineValue").isEmpty()) {
							genderNameMale.put(GenderNames.valueOf(e.getAttribute("name")), e.getAttribute("masculineValue"));
						} else {
							genderNameMale.put(GenderNames.valueOf(e.getAttribute("name")), GenderPronoun.valueOf(e.getAttribute("name")).getMasculine());
						}
						
						if(!e.getAttribute("neutralValue").isEmpty()) {
							genderNameNeutral.put(GenderNames.valueOf(e.getAttribute("name")), e.getAttribute("neutralValue"));
						} else {
							genderNameNeutral.put(GenderNames.valueOf(e.getAttribute("name")), GenderPronoun.valueOf(e.getAttribute("name")).getNeutral());
						}
					}
				}
				// Gender pronouns:
				nodes = doc.getElementsByTagName("genderPronouns");
				element = (Element) nodes.item(0);
				if(element!=null && element.getElementsByTagName("pronoun")!=null) {
					for(int i=0; i<element.getElementsByTagName("pronoun").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("pronoun").item(i));
						
						try {
							if(!e.getAttribute("feminineValue").isEmpty()) {
								genderPronounFemale.put(GenderPronoun.valueOf(e.getAttribute("pronounName")), e.getAttribute("feminineValue"));
							} else {
								genderPronounFemale.put(GenderPronoun.valueOf(e.getAttribute("pronounName")), GenderPronoun.valueOf(e.getAttribute("pronounName")).getFeminine());
							}
		
							if(!e.getAttribute("masculineValue").isEmpty()) {
								genderPronounMale.put(GenderPronoun.valueOf(e.getAttribute("pronounName")), e.getAttribute("masculineValue"));
							} else {
								genderPronounMale.put(GenderPronoun.valueOf(e.getAttribute("pronounName")), GenderPronoun.valueOf(e.getAttribute("pronounName")).getMasculine());
							}
						} catch(IllegalArgumentException ex){
							System.err.println("loadPropertiesFromXML() error: genderPronouns pronoun");
						}
					}
				}
				
				// Gender preferences:
				nodes = doc.getElementsByTagName("genderPreferences");
				element = (Element) nodes.item(0);
				if(element!=null && element.getElementsByTagName("preference")!=null) {
					for(int i=0; i<element.getElementsByTagName("preference").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("preference").item(i));
						
						try {
							if(!e.getAttribute("gender").isEmpty()) {
								genderPreferencesMap.put(Gender.valueOf(e.getAttribute("gender")), Integer.valueOf(e.getAttribute("value")));
							}
						} catch(IllegalArgumentException ex){
							System.err.println("loadPropertiesFromXML() error: genderPreferences preference");
						}
					}
				}

				// Age preferences:
				nodes = doc.getElementsByTagName("agePreferences");
				element = (Element) nodes.item(0);
				if(element!=null && element.getElementsByTagName("preference")!=null) {
					for(int i=0; i<element.getElementsByTagName("preference").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("preference").item(i));
						
						try {
							for(PronounType pronoun : PronounType.values()) {
								agePreferencesMap.get(pronoun).put(AgeCategory.valueOf(e.getAttribute("age")), Integer.valueOf(e.getAttribute(pronoun.toString())));
							}
						} catch(IllegalArgumentException ex){
							System.err.println("loadPropertiesFromXML() error: agePreferences preference");
						}
					}
				}
				

				// Sexual orientation preferences:
				nodes = doc.getElementsByTagName("orientationPreferences");
				element = (Element) nodes.item(0);
				if(element!=null && element.getElementsByTagName("preference")!=null) {
					for(int i=0; i<element.getElementsByTagName("preference").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("preference").item(i));
						
						try {
							if(!e.getAttribute("orientation").isEmpty()) {
								orientationPreferencesMap.put(SexualOrientation.valueOf(e.getAttribute("orientation")), Integer.valueOf(e.getAttribute("value")));
							}
						} catch(IllegalArgumentException ex){
							System.err.println("loadPropertiesFromXML() error: orientationPreferences preference");
						}
					}
				}
				
				// Race preferences:
				nodes = doc.getElementsByTagName("subspeciesPreferences");
				element = (Element) nodes.item(0);
				if(element!=null && element.getElementsByTagName("preferenceFeminine")!=null) {
					for(int i=0; i<element.getElementsByTagName("preferenceFeminine").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("preferenceFeminine").item(i));
						
						if(!e.getAttribute("subspecies").isEmpty()) {
							try {
								this.setFeminineSubspeciesPreference(Subspecies.valueOf(e.getAttribute("subspecies")), SubspeciesPreference.valueOf(e.getAttribute("preference")));
								this.setFeminineFurryPreference(Subspecies.valueOf(e.getAttribute("subspecies")), FurryPreference.valueOf(e.getAttribute("furryPreference")));
							} catch(Exception ex) {
							}
						}
					}
				}
				if(element!=null && element.getElementsByTagName("preferenceMasculine")!=null) {
					for(int i=0; i<element.getElementsByTagName("preferenceMasculine").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("preferenceMasculine").item(i));
						
						if(!e.getAttribute("subspecies").isEmpty()) {
							try {
								this.setMasculineSubspeciesPreference(Subspecies.valueOf(e.getAttribute("subspecies")), SubspeciesPreference.valueOf(e.getAttribute("preference")));
								this.setMasculineFurryPreference(Subspecies.valueOf(e.getAttribute("subspecies")), FurryPreference.valueOf(e.getAttribute("furryPreference")));
								
							} catch(Exception ex) {
							}
						}
					}
				}
				
				// Discoveries:
				nodes = doc.getElementsByTagName("itemsDiscovered");
				element = (Element) nodes.item(0);
				if(element!=null && element.getElementsByTagName("itemType")!=null) {
					for(int i=0; i<element.getElementsByTagName("itemType").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("itemType").item(i));
						
						if(!e.getAttribute("id").isEmpty()) {
							if(ItemType.getIdToItemMap().get(e.getAttribute("id"))!=null) {
								itemsDiscovered.add(ItemType.getIdToItemMap().get(e.getAttribute("id")));
							}
						}
					}
				}
				
				nodes = doc.getElementsByTagName("weaponsDiscovered");
				element = (Element) nodes.item(0);
				if(element!=null && element.getElementsByTagName("weaponType")!=null) {
					for(int i=0; i<element.getElementsByTagName("weaponType").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("weaponType").item(i));
						
						if(!e.getAttribute("id").isEmpty()) {
							weaponsDiscovered.add(WeaponType.getWeaponTypeFromId(e.getAttribute("id")));
						}
					}
				}
				
				nodes = doc.getElementsByTagName("clothingDiscovered");
				element = (Element) nodes.item(0);
				if(element!=null && element.getElementsByTagName("clothingType")!=null) {
					for(int i=0; i<element.getElementsByTagName("clothingType").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("clothingType").item(i));
						
						if(!e.getAttribute("id").isEmpty()) {
							clothingDiscovered.add(ClothingType.getClothingTypeFromId(e.getAttribute("id")));
						}
					}
				}
				
				nodes = doc.getElementsByTagName("racesDiscovered");
				element = (Element) nodes.item(0);
				if(element!=null && element.getElementsByTagName("raceDiscovery")!=null) {
					for(int i=0; i<element.getElementsByTagName("raceDiscovery").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("raceDiscovery").item(i));
						
						if(!e.getAttribute("discovered").isEmpty()) {
							if(Boolean.valueOf(e.getAttribute("discovered"))) {
								try {
									this.subspeciesDiscovered.add(Subspecies.valueOf(e.getAttribute("race")));
								} catch(Exception ex) {
								}
							}
						}
						if(!e.getAttribute("advancedKnowledge").isEmpty()) {
							if(Boolean.valueOf(e.getAttribute("advancedKnowledge"))) {
								try {
									this.subspeciesAdvancedKnowledge.add(Subspecies.valueOf(e.getAttribute("race")));
								} catch(Exception ex) {
								}
							}
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	

	public boolean hasValue(PropertyValue value) {
		return values.contains(value);
	}
	
	public void setValue(PropertyValue value, boolean flagged) {
		if(flagged) {
			values.add(value);
		} else {
			values.remove(value);
		}
	}
	
	// Add discoveries:
	
	public boolean addItemDiscovered(AbstractItemType itemType) {
		if(itemsDiscovered.add(itemType)) {
			setValue(PropertyValue.newItemDiscovered, true);
			return true;
		}
		return false;
	}
	
	public boolean isItemDiscovered(AbstractItemType itemType) {
		return itemsDiscovered.contains(itemType);
	}
	
	public boolean addClothingDiscovered(AbstractClothingType clothingType) {
		if(clothingDiscovered.add(clothingType)) {
			setValue(PropertyValue.newClothingDiscovered, true);
			return true;
		}
		return false;
	}
	
	public boolean isClothingDiscovered(AbstractClothingType clothingType) {
		return clothingDiscovered.contains(clothingType);
	}
	
	public boolean addWeaponDiscovered(AbstractWeaponType weaponType) {
		if(weaponsDiscovered.add(weaponType)) {
			setValue(PropertyValue.newWeaponDiscovered, true);
			return true;
		}
		return false;
	}
	
	public boolean isWeaponDiscovered(AbstractWeaponType weaponType) {
		return weaponsDiscovered.contains(weaponType);
	}
	
	public boolean addRaceDiscovered(Subspecies subspecies) {
		if(subspeciesDiscovered.add(subspecies)) {
			Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(subspecies.getName(null), subspecies.getColour(null)), true);
			setValue(PropertyValue.newRaceDiscovered, true);
			return true;
		}
		return false;
	}
	
	public boolean isRaceDiscovered(Subspecies subspecies) {
		return subspeciesDiscovered.contains(subspecies);
	}
	
	public boolean addAdvancedRaceKnowledge(Subspecies subspecies) {
		boolean added = subspeciesAdvancedKnowledge.add(subspecies);
		if(added) {
			Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(subspecies.getName(null)+" (Advanced)", subspecies.getColour(null)), true);
		}
		return added;
	}
	
	public boolean isAdvancedRaceKnowledgeDiscovered(Subspecies subspecies) {
		if(subspeciesAdvancedKnowledge.contains(subspecies)) {
			return true;
		}
		// If this subspecies shares a lore book with the parent subspecies, and that parent subspecies is unlocked, then return true:
		Subspecies coreSubspecies = Subspecies.getMainSubspeciesOfRace(subspecies.getRace());
		if(ItemType.getLoreBook(subspecies).equals(ItemType.getLoreBook(coreSubspecies))) {
			return subspeciesAdvancedKnowledge.contains(coreSubspecies);
		}
		
		return false;
	}
	
	public void setFeminineFurryPreference(Subspecies subspecies, FurryPreference furryPreference) {
		if(subspecies.getRace().isAffectedByFurryPreference()) {
			subspeciesFeminineFurryPreferencesMap.put(subspecies, furryPreference);
		}
	}
	
	public void setMasculineFurryPreference(Subspecies subspecies, FurryPreference furryPreference) {
		if(subspecies.getRace().isAffectedByFurryPreference()) {
			subspeciesMasculineFurryPreferencesMap.put(subspecies, furryPreference);
		}
	}
	
	public void setFeminineSubspeciesPreference(Subspecies subspecies, SubspeciesPreference subspeciesPreference) {
		subspeciesFemininePreferencesMap.put(subspecies, subspeciesPreference);
	}
	
	public void setMasculineSubspeciesPreference(Subspecies subspecies, SubspeciesPreference subspeciesPreference) {
		subspeciesMasculinePreferencesMap.put(subspecies, subspeciesPreference);
	}

	public Map<Subspecies, FurryPreference> getSubspeciesFeminineFurryPreferencesMap() {
		return subspeciesFeminineFurryPreferencesMap;
	}

	public Map<Subspecies, FurryPreference> getSubspeciesMasculineFurryPreferencesMap() {
		return subspeciesMasculineFurryPreferencesMap;
	}

	public Map<Subspecies, SubspeciesPreference> getSubspeciesFemininePreferencesMap() {
		return subspeciesFemininePreferencesMap;
	}

	public Map<Subspecies, SubspeciesPreference> getSubspeciesMasculinePreferencesMap() {
		return subspeciesMasculinePreferencesMap;
	}

	public void resetGenderPreferences() {
		genderPreferencesMap = new EnumMap<>(Gender.class);
		for(Gender g : Gender.values()) {
			genderPreferencesMap.put(g, g.getGenderPreferenceDefault().getValue());
		}
	}
	
	public void resetAgePreferences() {
		agePreferencesMap = new HashMap<>();
		for(PronounType pronoun : PronounType.values()) {
			agePreferencesMap.put(pronoun, new HashMap<>());
			for(AgeCategory ageCat : AgeCategory.values()) {
				agePreferencesMap.get(pronoun).put(ageCat, ageCat.getAgePreferenceDefault().getValue());
			}
		}
	}

	public FurryPreference getForcedTFPreference() {
		return forcedTFPreference;
	}

	public void setForcedTFPreference(FurryPreference forcedTFPreference) {
		this.forcedTFPreference = forcedTFPreference;
	}

	public ForcedTFTendency getForcedTFTendency() {
		return forcedTFTendency;
	}

	public void setForcedTFTendency(ForcedTFTendency forcedTFTendency) {
		this.forcedTFTendency = forcedTFTendency;
	}

	public ForcedFetishTendency getForcedFetishTendency() {
		return forcedFetishTendency;
	}

	public void setForcedFetishTendency(ForcedFetishTendency forcedFetishTendency) {
		this.forcedFetishTendency = forcedFetishTendency;
	}
	
	public float getRandomRacePercentage() {
		return randomRacePercentage;
		
	}
}
