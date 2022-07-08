package com.lilithsthrone.game;

import java.io.File;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.AndrogynousIdentification;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderNames;
import com.lilithsthrone.game.character.gender.GenderPronoun;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesPreference;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryEncyclopediaUnlock;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.settings.DifficultyLevel;
import com.lilithsthrone.game.settings.ForcedFetishTendency;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.settings.KeyCodeWithModifiers;
import com.lilithsthrone.game.settings.KeyboardAction;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4.2
 * @author Innoxia, Maxis
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

	public String badEndTitle = "";
	
	public int fontSize = 18;
	public int level = 1;
	public int money = 0;
	public int arcaneEssences = 0;
	
	public static final String[] taurFurryLevelName = new String[] {
			"Untouched",
			"Human",
			"Minimum",
			"Lesser",
			"Greater",
			"Maximum"};
	public static final String[] taurFurryLevelDescription = new String[] {
			"If an NPC is generated as a taur, their upper body's furriness will be based on your furry preferences for their race.",
			"If an NPC is generated as a taur, their upper body will always be completely human.",
			"If an NPC is generated as a taur, they will always have the upper-body of a partial morph (so eyes, ears, horns, and antenna will be non-human).",
			"If an NPC is generated as a taur, they will always have the upper-body of a partial morph (so eyes, ears, horns, and antenna will be non-human). They also have the chance to spawn with furry breasts and arms.",
			"If an NPC is generated as a taur, they will always have the upper-body of a partial morph (so eyes, ears, horns, and antenna will be non-human). They also have the chance to spawn with furry breasts, arms, skin/fur, and faces.",
			"If an NPC is generated as a taur, they will always have the upper-body of a greater morph, spawning in with furry ears, eyes, horns, antenna, breasts, arms, skin/fur, and face."};
	public int taurFurryLevel = 2;

	public int humanSpawnRate = 5;
	public int taurSpawnRate = 5;
	public int halfDemonSpawnRate = 5;
	
	public int multiBreasts = 1;
	public static String[] multiBreastsLabels = new String[] {"Off", "Furry-only", "On"};
	public static String[] multiBreastsDescriptions = new String[] {
			"Randomly-generated NPCs will never have multiple rows of breasts.",
			"Randomly-generated NPCs will only have multiple rows of breasts if they have furry skin. (Default setting.)",
			"Randomly-generated NPCs will have multiple rows of breasts if their breast type is furry (starts at 'Minor morph' level)."};
	
	/** 0=off, 1=taur-only, 2=on*/
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

	public int bypassSexActions = 2;
	public static String[] bypassSexActionsLabels = new String[] {"None", "Limited", "Full"};
	public static String[] getBypassSexActionsDescriptions = new String[] {
			"There will be no options to bypass sex action corruption requirements, you are limited in your actions based on your corruption and fetishes.",
			"Sex action corruption requirements may be bypassed if your corruption level is one level below the required corruption level of the action, but you will gain corruption if you do so.",
			"All sex action corruption requirements may be bypassed, but you will gain corruption if you do so."};

	public int pregnancyDuration = 1;
	
	public int forcedTFPercentage = 40;
	public int forcedFetishPercentage = 0;

	public float randomRacePercentage = 0.15f;

	public int pregnancyBreastGrowthVariance = 2;
	public int pregnancyBreastGrowth = 1;
	public int pregnancyUdderGrowth = 1;
	
	public int pregnancyBreastGrowthLimit = CupSize.E.getMeasurement();
	public int pregnancyUdderGrowthLimit = CupSize.E.getMeasurement();
	
	public int pregnancyLactationIncreaseVariance = 100;
	public int pregnancyLactationIncrease = 250;
	public int pregnancyUdderLactationIncrease = 250;
	
	public int pregnancyLactationLimit = 1000;
	public int pregnancyUdderLactationLimit = 1000;
	
	public int breastSizePreference = 0;
	public int udderSizePreference = 0;
	public int penisSizePreference = 0;
	public int trapPenisSizePreference = -70;
	
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
	public Map<AbstractFetish, Integer> fetishPreferencesMap;

	public Map<PronounType, Map<AgeCategory, Integer>> agePreferencesMap;
	
	private Map<AbstractSubspecies, FurryPreference> subspeciesFeminineFurryPreferencesMap;
	private Map<AbstractSubspecies, FurryPreference> subspeciesMasculineFurryPreferencesMap;
	
	private Map<AbstractSubspecies, SubspeciesPreference> subspeciesFemininePreferencesMap;
	private Map<AbstractSubspecies, SubspeciesPreference> subspeciesMasculinePreferencesMap;

	public Map<Colour, Integer> skinColourPreferencesMap;
	
	// Transformation Settings
	private FurryPreference forcedTFPreference;
	private ForcedTFTendency forcedTFTendency;
	private ForcedFetishTendency forcedFetishTendency;
	
	// Discoveries:
	private Set<AbstractItemType> itemsDiscovered;
	private Set<AbstractWeaponType> weaponsDiscovered;
	private Set<AbstractClothingType> clothingDiscovered;
	private Set<AbstractSubspecies> subspeciesDiscovered;
	private Set<AbstractSubspecies> subspeciesAdvancedKnowledge;

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

		resetOrientationPreferences();
		
		resetFetishPreferences();

		resetAgePreferences();
		
		forcedTFPreference = FurryPreference.NORMAL;
		forcedTFTendency = ForcedTFTendency.NEUTRAL;
		forcedFetishTendency = ForcedFetishTendency.NEUTRAL;
		
		subspeciesFeminineFurryPreferencesMap = new HashMap<>();
		subspeciesMasculineFurryPreferencesMap = new HashMap<>();
		for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
			subspeciesFeminineFurryPreferencesMap.put(s, s.getDefaultFemininePreference());
			subspeciesMasculineFurryPreferencesMap.put(s, s.getDefaultMasculinePreference());
		}
		
		subspeciesFemininePreferencesMap = new HashMap<>();
		subspeciesMasculinePreferencesMap = new HashMap<>();
		for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
			subspeciesFemininePreferencesMap.put(s, s.getSubspeciesPreferenceDefault());
			subspeciesMasculinePreferencesMap.put(s, s.getSubspeciesPreferenceDefault());
		}
		
		skinColourPreferencesMap = new LinkedHashMap<>();
		for(Entry<Colour, Integer> entry : PresetColour.getHumanSkinColoursMap().entrySet()) {
			skinColourPreferencesMap.put(entry.getKey(), entry.getValue());
		}
		
		itemsDiscovered = new HashSet<>();
		weaponsDiscovered = new HashSet<>();
		clothingDiscovered = new HashSet<>();
		subspeciesDiscovered = new HashSet<>();
		subspeciesAdvancedKnowledge = new HashSet<>();
	}
	
	public void savePropertiesAsXML(){
		try {
			Document doc = Main.getDocBuilder().newDocument();
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
					XMLUtil.createXMLElementWithValue(doc, valuesElement, "propertyValue", value.toString());
				}
			}
			
			// Game settings:
			Element settings = doc.createElement("settings");
			properties.appendChild(settings);
			createXMLElementWithValue(doc, settings, "fontSize", String.valueOf(fontSize));
			
			createXMLElementWithValue(doc, settings, "preferredArtist", preferredArtist);
			if(!badEndTitle.isEmpty()) {
				createXMLElementWithValue(doc, settings, "badEndTitle", badEndTitle);
			}
			createXMLElementWithValue(doc, settings, "androgynousIdentification", String.valueOf(androgynousIdentification));
			createXMLElementWithValue(doc, settings, "humanSpawnRate", String.valueOf(humanSpawnRate));
			createXMLElementWithValue(doc, settings, "taurSpawnRate", String.valueOf(taurSpawnRate));
			createXMLElementWithValue(doc, settings, "halfDemonSpawnRate", String.valueOf(halfDemonSpawnRate));
			createXMLElementWithValue(doc, settings, "taurFurryLevel", String.valueOf(taurFurryLevel));
			createXMLElementWithValue(doc, settings, "multiBreasts", String.valueOf(multiBreasts));
			createXMLElementWithValue(doc, settings, "udders", String.valueOf(udders));
			createXMLElementWithValue(doc, settings, "autoSaveFrequency", String.valueOf(autoSaveFrequency));
			createXMLElementWithValue(doc, settings, "bypassSexActions", String.valueOf(bypassSexActions));
			createXMLElementWithValue(doc, settings, "pregnancyDuration", String.valueOf(pregnancyDuration));
			createXMLElementWithValue(doc, settings, "forcedTFPercentage", String.valueOf(forcedTFPercentage));
			createXMLElementWithValue(doc, settings, "randomRacePercentage", String.valueOf(randomRacePercentage)); 

			createXMLElementWithValue(doc, settings, "pregnancyBreastGrowthVariance", String.valueOf(pregnancyBreastGrowthVariance));
			createXMLElementWithValue(doc, settings, "pregnancyBreastGrowth", String.valueOf(pregnancyBreastGrowth));
			createXMLElementWithValue(doc, settings, "pregnancyUdderGrowth", String.valueOf(pregnancyUdderGrowth));
			createXMLElementWithValue(doc, settings, "pregnancyBreastGrowthLimit", String.valueOf(pregnancyBreastGrowthLimit));
			createXMLElementWithValue(doc, settings, "pregnancyUdderGrowthLimit", String.valueOf(pregnancyUdderGrowthLimit));
			createXMLElementWithValue(doc, settings, "pregnancyLactationIncreaseVariance", String.valueOf(pregnancyLactationIncreaseVariance));
			createXMLElementWithValue(doc, settings, "pregnancyLactationIncrease", String.valueOf(pregnancyLactationIncrease));
			createXMLElementWithValue(doc, settings, "pregnancyUdderLactationIncrease", String.valueOf(pregnancyUdderLactationIncrease));
			createXMLElementWithValue(doc, settings, "pregnancyLactationLimit", String.valueOf(pregnancyLactationLimit));
			createXMLElementWithValue(doc, settings, "pregnancyUdderLactationLimit", String.valueOf(pregnancyUdderLactationLimit));

			createXMLElementWithValue(doc, settings, "breastSizePreference", String.valueOf(breastSizePreference));
			createXMLElementWithValue(doc, settings, "udderSizePreference", String.valueOf(udderSizePreference));
			createXMLElementWithValue(doc, settings, "penisSizePreference", String.valueOf(penisSizePreference));
			createXMLElementWithValue(doc, settings, "trapPenisSizePreference", String.valueOf(trapPenisSizePreference));
			
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
			
			// Fetish preferences:
			Element fetishPreferences = doc.createElement("fetishPreferences");
			properties.appendChild(fetishPreferences);
			for (AbstractFetish f : Fetish.getAllFetishes()) {
				Element element = doc.createElement("preference");
				fetishPreferences.appendChild(element);
				
				Attr fetish = doc.createAttribute("fetish");
				fetish.setValue(Fetish.getIdFromFetish(f));
				element.setAttributeNode(fetish);
				
				Attr value = doc.createAttribute("value");
				value.setValue(String.valueOf(fetishPreferencesMap.get(f).intValue()));
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
			for (AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
				Element element = doc.createElement("preferenceFeminine");
				racePreferences.appendChild(element);
				
				Attr race = doc.createAttribute("subspecies");
				race.setValue(Subspecies.getIdFromSubspecies(subspecies));
				element.setAttributeNode(race);
				
				Attr preference = doc.createAttribute("preference");
				preference.setValue(subspeciesFemininePreferencesMap.get(subspecies).toString());
				element.setAttributeNode(preference);

				preference = doc.createAttribute("furryPreference");
				preference.setValue(subspeciesFeminineFurryPreferencesMap.get(subspecies).toString());
				element.setAttributeNode(preference);
				
				element = doc.createElement("preferenceMasculine");
				racePreferences.appendChild(element);
				
				race = doc.createAttribute("subspecies");
				race.setValue(Subspecies.getIdFromSubspecies(subspecies));
				element.setAttributeNode(race);
				
				preference = doc.createAttribute("preference");
				preference.setValue(subspeciesMasculinePreferencesMap.get(subspecies).toString());
				element.setAttributeNode(preference);
				
				preference = doc.createAttribute("furryPreference");
				preference.setValue(subspeciesMasculineFurryPreferencesMap.get(subspecies).toString());
				element.setAttributeNode(preference);
			}

			// Skin colour preferences:
			Element skinColourPreferences = doc.createElement("skinColourPreferences");
			properties.appendChild(skinColourPreferences);
			for (Entry<Colour, Integer> colour : skinColourPreferencesMap.entrySet()) {
				Element element = doc.createElement("preference");
				skinColourPreferences.appendChild(element);
				
				Attr skinColour = doc.createAttribute("colour");
				skinColour.setValue(colour.getKey().getId());
				element.setAttributeNode(skinColour);
				
				Attr value = doc.createAttribute("value");
				value.setValue(String.valueOf(colour.getValue()));
				element.setAttributeNode(value);
			}
			
			// Discoveries:
			Element itemsDiscovered = doc.createElement("itemsDiscovered");
			properties.appendChild(itemsDiscovered);
			for (AbstractItemType itemType : this.itemsDiscovered) {
				try {
					if(itemType!=null) {
						Element element = doc.createElement("type");
						itemsDiscovered.appendChild(element);
						element.setTextContent(itemType.getId());
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
						Element element = doc.createElement("type");
						weaponsDiscovered.appendChild(element);
						element.setTextContent(weaponType.getId());
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
						Element element = doc.createElement("type");
						clothingDiscovered.appendChild(element);
						element.setTextContent(clothingType.getId());
					}
				} catch(Exception ex) {
					// Catch errors from modded items being removed
				}
			}
			
			Element racesDiscovered = doc.createElement("racesDiscovered");
			properties.appendChild(racesDiscovered);
			for(AbstractSubspecies subspecies : this.subspeciesDiscovered) {
				if(!this.subspeciesAdvancedKnowledge.contains(subspecies)) {
					Element element = doc.createElement("race");
					racesDiscovered.appendChild(element);
					element.setTextContent(Subspecies.getIdFromSubspecies(subspecies));
				}
			}
			Element racesDiscoveredAdvanced = doc.createElement("racesDiscoveredAdvanced");
			properties.appendChild(racesDiscoveredAdvanced);
			for(AbstractSubspecies subspecies : this.subspeciesAdvancedKnowledge) {
				Element element = doc.createElement("race");
				racesDiscoveredAdvanced.appendChild(element);
				element.setTextContent(Subspecies.getIdFromSubspecies(subspecies));
			}
			
			
			// Write out to properties.xml:
			Transformer transformer = Main.transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult("data/properties.xml");
		
			transformer.transform(source, result);
		
		} catch (TransformerException e) {
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
				Document doc = Main.getDocBuilder().parse(propertiesXML);
				
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
					if(Main.isVersionOlderThan(versionNumber, "0.3.5.8")) {
						values.add(PropertyValue.gapeContent);
					}
					if(Main.isVersionOlderThan(versionNumber, "0.3.5.9")) {
						values.add(PropertyValue.levelDrain);
					}
					if(Main.isVersionOlderThan(versionNumber, "0.3.6.6")) {
						values.add(PropertyValue.furryHairContent);
					}
					if(Main.isVersionOlderThan(versionNumber, "0.3.6.7")) {
						values.add(PropertyValue.penetrationLimitations);
					}
					if(Main.isVersionOlderThan(versionNumber, "0.3.7.5")) {
						values.add(PropertyValue.lipstickMarkingContent);
					}
					if(Main.isVersionOlderThan(versionNumber, "0.3.7.7")) {
						values.add(PropertyValue.weatherInterruptions);
					}
					if(Main.isVersionOlderThan(versionNumber, "0.3.8.9")) {
						values.add(PropertyValue.badEndContent);
					}
					if(Main.isVersionOlderThan(versionNumber, "0.4.0.5")) {
						values.add(PropertyValue.armpitContent);
					}
					for(int i=0; i < element.getElementsByTagName("propertyValue").getLength(); i++){
						Element e = (Element) element.getElementsByTagName("propertyValue").item(i);
						
						try {
							values.add(PropertyValue.valueOf(e.getAttribute("value")));
						} catch(Exception ex) {
						}
					}
					if(Main.isVersionOlderThan(versionNumber, "0.4.1.5")) {
						values.add(PropertyValue.vestigialMultiBreasts);
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

				if(element.getElementsByTagName("badEndTitle").item(0)!=null) {
					badEndTitle =((Element)element.getElementsByTagName("badEndTitle").item(0)).getAttribute("value");
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

				if(!Main.isVersionOlderThan(versionNumber, "0.3.8.3")) { // Reset taur furry preference after v0.3.8.2
					if(element.getElementsByTagName("taurFurryLevel").item(0)!=null) {
						taurFurryLevel = Integer.valueOf(((Element)element.getElementsByTagName("taurFurryLevel").item(0)).getAttribute("value"));
					} else {
						taurFurryLevel = 2;
					}
				}
				
				if(element.getElementsByTagName("humanEncountersLevel").item(0)!=null) { // Old version support:
					humanSpawnRate = Integer.valueOf(((Element)element.getElementsByTagName("humanEncountersLevel").item(0)).getAttribute("value"));
					if(humanSpawnRate==1) {
						humanSpawnRate = 5;
					} else if(humanSpawnRate==2) {
						humanSpawnRate = 25;
					} else if(humanSpawnRate==3) {
						humanSpawnRate = 50;
					} else if(humanSpawnRate==4) {
						humanSpawnRate = 75;
					}
				} else if(element.getElementsByTagName("humanSpawnRate").item(0)!=null) {
					humanSpawnRate = Integer.valueOf(((Element)element.getElementsByTagName("humanSpawnRate").item(0)).getAttribute("value"));
				} else {
					humanSpawnRate = 5;
				}
				
				if(element.getElementsByTagName("taurSpawnRate").item(0)!=null) {
					taurSpawnRate = Integer.valueOf(((Element)element.getElementsByTagName("taurSpawnRate").item(0)).getAttribute("value"));
				} else {
					taurSpawnRate = 5;
				}
				
				if(element.getElementsByTagName("halfDemonSpawnRate").item(0)!=null) {
					halfDemonSpawnRate = Integer.valueOf(((Element)element.getElementsByTagName("halfDemonSpawnRate").item(0)).getAttribute("value"));
				} else {
					halfDemonSpawnRate = 5;
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

				if(element.getElementsByTagName("bypassSexActions").item(0)!=null) {
					bypassSexActions = Integer.valueOf(((Element)element.getElementsByTagName("bypassSexActions").item(0)).getAttribute("value"));
				} else {
					bypassSexActions = 2;
				}
				
				
				if(element.getElementsByTagName("pregnancyDuration").item(0)!=null) {
					pregnancyDuration = Integer.valueOf(((Element)element.getElementsByTagName("pregnancyDuration").item(0)).getAttribute("value"));
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
					pregnancyUdderGrowth = Integer.valueOf(((Element)element.getElementsByTagName("pregnancyUdderGrowth").item(0)).getAttribute("value"));
					
					pregnancyBreastGrowthLimit = Integer.valueOf(((Element)element.getElementsByTagName("pregnancyBreastGrowthLimit").item(0)).getAttribute("value"));
					pregnancyUdderGrowthLimit = Integer.valueOf(((Element)element.getElementsByTagName("pregnancyUdderGrowthLimit").item(0)).getAttribute("value"));
					
					pregnancyLactationIncreaseVariance = Integer.valueOf(((Element)element.getElementsByTagName("pregnancyLactationIncreaseVariance").item(0)).getAttribute("value"));
					
					pregnancyLactationIncrease = Integer.valueOf(((Element)element.getElementsByTagName("pregnancyLactationIncrease").item(0)).getAttribute("value"));
					pregnancyUdderLactationIncrease = Integer.valueOf(((Element)element.getElementsByTagName("pregnancyUdderLactationIncrease").item(0)).getAttribute("value"));
					
					pregnancyLactationLimit = Integer.valueOf(((Element)element.getElementsByTagName("pregnancyLactationLimit").item(0)).getAttribute("value"));
					pregnancyUdderLactationLimit = Integer.valueOf(((Element)element.getElementsByTagName("pregnancyUdderLactationLimit").item(0)).getAttribute("value"));

					breastSizePreference = Integer.valueOf(((Element)element.getElementsByTagName("breastSizePreference").item(0)).getAttribute("value"));
					udderSizePreference = Integer.valueOf(((Element)element.getElementsByTagName("udderSizePreference").item(0)).getAttribute("value"));
					
					penisSizePreference = Integer.valueOf(((Element)element.getElementsByTagName("penisSizePreference").item(0)).getAttribute("value"));
				}catch(Exception ex) {
				}

				try {
					trapPenisSizePreference = Integer.valueOf(((Element)element.getElementsByTagName("trapPenisSizePreference").item(0)).getAttribute("value"));
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
				
				// Fetish preferences:
				nodes = doc.getElementsByTagName("fetishPreferences");
				element = (Element) nodes.item(0);
				if(element!=null && element.getElementsByTagName("preference")!=null) {
					for(int i=0; i<element.getElementsByTagName("preference").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("preference").item(i));
						
						try {
							if(!e.getAttribute("fetish").isEmpty()) {
								fetishPreferencesMap.put(Fetish.getFetishFromId(e.getAttribute("fetish")), Integer.valueOf(e.getAttribute("value")));
							}
						} catch(IllegalArgumentException ex){
							System.err.println("loadPropertiesFromXML() error: fetishPreferences preference");
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
								this.setFeminineSubspeciesPreference(Subspecies.getSubspeciesFromId(e.getAttribute("subspecies")), SubspeciesPreference.valueOf(e.getAttribute("preference")));
								this.setFeminineFurryPreference(Subspecies.getSubspeciesFromId(e.getAttribute("subspecies")), FurryPreference.valueOf(e.getAttribute("furryPreference")));
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
								this.setMasculineSubspeciesPreference(Subspecies.getSubspeciesFromId(e.getAttribute("subspecies")), SubspeciesPreference.valueOf(e.getAttribute("preference")));
								this.setMasculineFurryPreference(Subspecies.getSubspeciesFromId(e.getAttribute("subspecies")), FurryPreference.valueOf(e.getAttribute("furryPreference")));
								
							} catch(Exception ex) {
							}
						}
					}
				}

				// Skin colour preferences:
				nodes = doc.getElementsByTagName("skinColourPreferences");
				element = (Element) nodes.item(0);
				if(element!=null && element.getElementsByTagName("preference")!=null) {
					for(int i=0; i<element.getElementsByTagName("preference").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("preference").item(i));
						
						try {
							skinColourPreferencesMap.put(PresetColour.getColourFromId(e.getAttribute("colour")), Integer.valueOf(e.getAttribute("value")));
						} catch(IllegalArgumentException ex){
							System.err.println("loadPropertiesFromXML() error: skinColourPreferences preference");
						}
					}
				}
				
				// Item Discoveries:
				if(Main.isVersionOlderThan(versionNumber, "0.3.7.7")) {
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
							
							String clothingId = e.getAttribute("id");
							
							if(!clothingId.isEmpty()) {
								if(!clothingId.startsWith("dsg_eep_uniques")) {
									if(clothingId.startsWith("dsg_eep_servequipset_enfdjacket")) {
										clothingId = "dsg_eep_servequipset_enfdjacket";
									} else if(clothingId.startsWith("dsg_eep_servequipset_enfdwaistcoat")) {
										clothingId = "dsg_eep_servequipset_enfdwaistcoat";
									} else if(clothingId.startsWith("dsg_eep_servequipset_enfberet")) {
										clothingId = "dsg_eep_servequipset_enfberet";
									}
									clothingDiscovered.add(ClothingType.getClothingTypeFromId(clothingId));
								}
							}
						}
					}
					
				} else {
					nodes = doc.getElementsByTagName("itemsDiscovered");
					element = (Element) nodes.item(0);
					nodes = element.getElementsByTagName("type");
					if(element!=null && nodes!=null) {
						for(int i=0; i<nodes.getLength(); i++){
							Element e = ((Element)nodes.item(i));
							itemsDiscovered.add(ItemType.getItemTypeFromId(e.getTextContent()));
						}
					}
					
					nodes = doc.getElementsByTagName("weaponsDiscovered");
					element = (Element) nodes.item(0);
					nodes = element.getElementsByTagName("type");
					if(element!=null && nodes!=null) {
						for(int i=0; i<nodes.getLength(); i++){
							Element e = ((Element)nodes.item(i));
							weaponsDiscovered.add(WeaponType.getWeaponTypeFromId(e.getTextContent()));
						}
					}
					
					nodes = doc.getElementsByTagName("clothingDiscovered");
					element = (Element) nodes.item(0);
					nodes = element.getElementsByTagName("type");
					if(element!=null && nodes!=null) {
						for(int i=0; i<nodes.getLength(); i++){
							Element e = ((Element)nodes.item(i));
							String clothingId = e.getTextContent();
							if(!clothingId.startsWith("dsg_eep_uniques")) {
								if(clothingId.startsWith("dsg_eep_servequipset_enfdjacket")) {
									clothingId = "dsg_eep_servequipset_enfdjacket";
								} else if(clothingId.startsWith("dsg_eep_servequipset_enfdwaistcoat")) {
									clothingId = "dsg_eep_servequipset_enfdwaistcoat";
								} else if(clothingId.startsWith("dsg_eep_servequipset_enfberet")) {
									clothingId = "dsg_eep_servequipset_enfberet";
								}
								clothingDiscovered.add(ClothingType.getClothingTypeFromId(clothingId));
							}
						}
					}
				}

				// Subspecies Discoveries:
				if(Main.isVersionOlderThan(versionNumber, "0.3.7.7")) {
					nodes = doc.getElementsByTagName("racesDiscovered");
					element = (Element) nodes.item(0);
					if(element!=null && element.getElementsByTagName("raceDiscovery")!=null) {
						for(int i=0; i<element.getElementsByTagName("raceDiscovery").getLength(); i++){
							Element e = ((Element)element.getElementsByTagName("raceDiscovery").item(i));
							
							if(!e.getAttribute("discovered").isEmpty()) {
								if(Boolean.valueOf(e.getAttribute("discovered"))) {
									try {
										this.subspeciesDiscovered.add(Subspecies.getSubspeciesFromId(e.getAttribute("race")));
									} catch(Exception ex) {
									}
								}
							}
							if(!e.getAttribute("advancedKnowledge").isEmpty()) {
								if(Boolean.valueOf(e.getAttribute("advancedKnowledge"))) {
									try {
										this.subspeciesAdvancedKnowledge.add(Subspecies.getSubspeciesFromId(e.getAttribute("race")));
									} catch(Exception ex) {
									}
								}
							}
						}
					}
					
				} else {
					nodes = doc.getElementsByTagName("racesDiscovered");
					element = (Element) nodes.item(0);
					NodeList races = element.getElementsByTagName("race");
					if(element!=null && races!=null) {
						for(int i=0; i<races.getLength(); i++){
							Element e = ((Element)races.item(i));
							try {
								this.subspeciesDiscovered.add(Subspecies.getSubspeciesFromId(e.getTextContent()));
							} catch(Exception ex) {
							}
						}
					}
					nodes = doc.getElementsByTagName("racesDiscoveredAdvanced");
					element = (Element) nodes.item(0);
					races = element.getElementsByTagName("race");
					if(element!=null && races!=null) {
						for(int i=0; i<races.getLength(); i++){
							Element e = ((Element)races.item(i));
							try {
								this.subspeciesDiscovered.add(Subspecies.getSubspeciesFromId(e.getTextContent()));
								this.subspeciesAdvancedKnowledge.add(Subspecies.getSubspeciesFromId(e.getTextContent()));
							} catch(Exception ex) {
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
	
	public void resetContentOptions() {
		autoSaveFrequency = 0;
		bypassSexActions = 2;
		multiBreasts = 1;
		udders = 1;
		pregnancyDuration = 1;
		forcedTFPercentage = 40;
		forcedFetishPercentage = 40;
		setForcedFetishTendency(ForcedFetishTendency.NEUTRAL);
		setForcedTFTendency(ForcedTFTendency.NEUTRAL);
		setForcedTFPreference(FurryPreference.NORMAL);
		
		pregnancyBreastGrowthVariance = 2;
		pregnancyBreastGrowth = 1;
		pregnancyUdderGrowth = 1;
		
		pregnancyBreastGrowthLimit = CupSize.E.getMeasurement();
		pregnancyUdderGrowthLimit = CupSize.E.getMeasurement();
		
		pregnancyLactationIncreaseVariance = 100;
		pregnancyLactationIncrease = 250;
		pregnancyUdderLactationIncrease = 250;
		
		pregnancyLactationLimit = 1000;
		pregnancyUdderLactationLimit = 1000;
		
		breastSizePreference = 0;
		udderSizePreference = 0;
		penisSizePreference = 0;
		trapPenisSizePreference = -70;

		skinColourPreferencesMap = new LinkedHashMap<>();
		for(Entry<Colour, Integer> entry : PresetColour.getHumanSkinColoursMap().entrySet()) {
			skinColourPreferencesMap.put(entry.getKey(), entry.getValue());
		}
	}
	
	// Add discoveries:
	
	private void applyAdditionalDiscoveries(AbstractCoreType itemType) {
		for(AbstractCoreType it : itemType.getAdditionalDiscoveryTypes()) {
			if(it instanceof AbstractWeaponType) {
				Main.game.getPlayer().addWeaponDiscovered((AbstractWeaponType)it);
				weaponsDiscovered.add((AbstractWeaponType)it);
			}
			if(it instanceof AbstractClothingType) {
				Main.game.getPlayer().addClothingDiscovered((AbstractClothingType)it);
				clothingDiscovered.add((AbstractClothingType)it);
			}
			if(it instanceof AbstractItemType) {
				Main.game.getPlayer().addItemDiscovered((AbstractItemType)it);
				itemsDiscovered.add((AbstractItemType)it);
			}
		}
	}
	
	public void completeSharedEncyclopedia() {
		for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
			this.addRaceDiscovered(subspecies);
			this.addAdvancedRaceKnowledge(subspecies);
		}
		for(AbstractItemType itemType : ItemType.getAllItems()) {
			this.addItemDiscovered(itemType);
		}
		for(AbstractClothingType clothingType : ClothingType.getAllClothing()) {
			this.addClothingDiscovered(clothingType);
		}
		for(AbstractWeaponType weaponType : WeaponType.getAllWeapons()) {
			this.addWeaponDiscovered(weaponType);
		}
	}
	
	public int getItemsDiscoveredCount() {
		if(!this.hasValue(PropertyValue.sharedEncyclopedia)) {
			return Main.game.getPlayer().getItemsDiscoveredCount();
		}
		return itemsDiscovered.size();
	}
	
	public boolean addItemDiscovered(AbstractItemType itemType) {
		if(itemType.getItemTags().contains(ItemTag.CHEAT_ITEM)
				|| itemType.getItemTags().contains(ItemTag.SILLY_MODE)) {
			return false;
		}
		boolean returnDiscovered = false;
		boolean playerDiscovered = Main.game.getPlayer().addItemDiscovered(itemType);
		if(itemsDiscovered.add(itemType) || (!this.hasValue(PropertyValue.sharedEncyclopedia) && playerDiscovered)) {
			setValue(PropertyValue.newItemDiscovered, true);
			returnDiscovered = true;
		}
		applyAdditionalDiscoveries(itemType);
		
		return returnDiscovered;
	}

	/** This method <b>takes into account</b> the 'shared Encyclopedia' content setting. */
	public boolean isItemDiscovered(AbstractItemType itemType) {
		if(this.hasValue(PropertyValue.sharedEncyclopedia)) {
			return itemsDiscovered.contains(itemType);
		}
		return Main.game.getPlayer().isItemDiscovered(itemType);
	}

	public void resetItemDiscovered() {
		itemsDiscovered.clear();
	}

	public int getClothingDiscoveredCount() {
		if(!this.hasValue(PropertyValue.sharedEncyclopedia)) {
			return Main.game.getPlayer().getClothingDiscoveredCount();
		}
		return clothingDiscovered.size();
	}
	
	public boolean addClothingDiscovered(AbstractClothingType clothingType) {
		if(clothingType.getDefaultItemTags().contains(ItemTag.CHEAT_ITEM)
				|| clothingType.getDefaultItemTags().contains(ItemTag.SILLY_MODE)) {
			return false;
		}
		boolean returnDiscovered = false;
		boolean playerDiscovered = Main.game.getPlayer().addClothingDiscovered(clothingType);
		if(clothingDiscovered.add(clothingType) || (!this.hasValue(PropertyValue.sharedEncyclopedia) && playerDiscovered)) {
			setValue(PropertyValue.newClothingDiscovered, true);
			returnDiscovered = true;
		}
		applyAdditionalDiscoveries(clothingType);
		
		return returnDiscovered;
	}

	/** This method <b>takes into account</b> the 'shared Encyclopedia' content setting. */
	public boolean isClothingDiscovered(AbstractClothingType clothingType) {
		if(this.hasValue(PropertyValue.sharedEncyclopedia)) {
			return clothingDiscovered.contains(clothingType);
		}
		return Main.game.getPlayer().isClothingDiscovered(clothingType);
	}

	public void resetClothingDiscovered() {
		clothingDiscovered.clear();
	}

	public int getWeaponsDiscoveredCount() {
		if(!this.hasValue(PropertyValue.sharedEncyclopedia)) {
			return Main.game.getPlayer().getWeaponsDiscoveredCount();
		}
		return weaponsDiscovered.size();
	}
	
	public boolean addWeaponDiscovered(AbstractWeaponType weaponType) {
		if(weaponType.getItemTags().contains(ItemTag.CHEAT_ITEM)
				|| weaponType.getItemTags().contains(ItemTag.SILLY_MODE)) {
			return false;
		}
		boolean returnDiscovered = false;
		boolean playerDiscovered = Main.game.getPlayer().addWeaponDiscovered(weaponType);
		if(weaponsDiscovered.add(weaponType) || (!this.hasValue(PropertyValue.sharedEncyclopedia) && playerDiscovered)) {
			setValue(PropertyValue.newWeaponDiscovered, true);
			returnDiscovered = true;
		}
		applyAdditionalDiscoveries(weaponType);
		
		return returnDiscovered;
	}

	/** This method <b>takes into account</b> the 'shared Encyclopedia' content setting. */
	public boolean isWeaponDiscovered(AbstractWeaponType weaponType) {
		if(this.hasValue(PropertyValue.sharedEncyclopedia)) {
			return weaponsDiscovered.contains(weaponType);
		}
		return Main.game.getPlayer().isWeaponDiscovered(weaponType);
	}

	public void resetWeaponDiscovered() {
		weaponsDiscovered.clear();
	}

	public int getSubspeciesDiscoveredCount() {
		if(!this.hasValue(PropertyValue.sharedEncyclopedia)) {
			return Main.game.getPlayer().getSubspeciesDiscoveredCount();
		}
		return subspeciesDiscovered.size();
	}
	
	public boolean addRaceDiscovered(AbstractSubspecies subspecies) {
		boolean playerDiscovered = Main.game.getPlayer().addRaceDiscovered(subspecies);
		if(subspeciesDiscovered.add(subspecies) || (!this.hasValue(PropertyValue.sharedEncyclopedia) && playerDiscovered)) {
			Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(subspecies.getName(null), subspecies.getColour(null)), true);
			setValue(PropertyValue.newRaceDiscovered, true);
			return true;
		}
		return false;
	}

	/** This method <b>takes into account</b> the 'shared Encyclopedia' content setting. */
	public boolean isRaceDiscovered(AbstractSubspecies subspecies) {
		if(this.hasValue(PropertyValue.sharedEncyclopedia)) {
			return subspeciesDiscovered.contains(subspecies);
		}
		return Main.game.getPlayer().isRaceDiscovered(subspecies);
	}

	public void resetRaceDiscovered() {
		subspeciesDiscovered.clear();
	}

	public int getSubspeciesAdvancedDiscoveredCount() {
		if(!this.hasValue(PropertyValue.sharedEncyclopedia)) {
			return Main.game.getPlayer().getSubspeciesAdvancedDiscoveredCount();
		}
		return subspeciesAdvancedKnowledge.size();
	}
	
	public boolean addAdvancedRaceKnowledge(AbstractSubspecies subspecies) {
		boolean playerDiscovered = Main.game.getPlayer().addAdvancedRaceKnowledge(subspecies);
		if(subspeciesAdvancedKnowledge.add(subspecies) || (!this.hasValue(PropertyValue.sharedEncyclopedia) && playerDiscovered)) {
			Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(subspecies.getName(null)+" (Advanced)", subspecies.getColour(null)), true);
			return true;
		}
		return false;
	}

	/** This method <b>takes into account</b> the 'shared Encyclopedia' content setting. */
	public boolean isAdvancedRaceKnowledgeDiscovered(AbstractSubspecies subspecies) {
		if(this.hasValue(PropertyValue.sharedEncyclopedia)) {
			if(subspeciesAdvancedKnowledge.contains(subspecies)) {
				return true;
			}
			// If this subspecies shares a lore book with the parent subspecies, and that parent subspecies is unlocked, then return true:
			AbstractSubspecies coreSubspecies = AbstractSubspecies.getMainSubspeciesOfRace(subspecies.getRace());
			if(ItemType.getLoreBook(subspecies).equals(ItemType.getLoreBook(coreSubspecies))) {
				return subspeciesAdvancedKnowledge.contains(coreSubspecies);
			}
			
			return false;
		}
		return Main.game.getPlayer().isAdvancedRaceKnowledgeDiscovered(subspecies);
	}

	public void resetAdvancedRaceKnowledge() {
		subspeciesAdvancedKnowledge.clear();
	}
	
	public void setFeminineFurryPreference(AbstractSubspecies subspecies, FurryPreference furryPreference) {
		if(subspecies.getRace().isAffectedByFurryPreference()) {
			subspeciesFeminineFurryPreferencesMap.put(subspecies, furryPreference);
		}
	}
	
	public void setMasculineFurryPreference(AbstractSubspecies subspecies, FurryPreference furryPreference) {
		if(subspecies.getRace().isAffectedByFurryPreference()) {
			subspeciesMasculineFurryPreferencesMap.put(subspecies, furryPreference);
		}
	}
	
	public void setFeminineSubspeciesPreference(AbstractSubspecies subspecies, SubspeciesPreference subspeciesPreference) {
		subspeciesFemininePreferencesMap.put(subspecies, subspeciesPreference);
	}
	
	public void setMasculineSubspeciesPreference(AbstractSubspecies subspecies, SubspeciesPreference subspeciesPreference) {
		subspeciesMasculinePreferencesMap.put(subspecies, subspeciesPreference);
	}

	public Map<AbstractSubspecies, FurryPreference> getSubspeciesFeminineFurryPreferencesMap() {
		return subspeciesFeminineFurryPreferencesMap;
	}

	public Map<AbstractSubspecies, FurryPreference> getSubspeciesMasculineFurryPreferencesMap() {
		return subspeciesMasculineFurryPreferencesMap;
	}

	public Map<AbstractSubspecies, SubspeciesPreference> getSubspeciesFemininePreferencesMap() {
		return subspeciesFemininePreferencesMap;
	}

	public Map<AbstractSubspecies, SubspeciesPreference> getSubspeciesMasculinePreferencesMap() {
		return subspeciesMasculinePreferencesMap;
	}

	public void resetGenderPreferences() {
		genderPreferencesMap = new EnumMap<>(Gender.class);
		for(Gender g : Gender.values()) {
			genderPreferencesMap.put(g, g.getGenderPreferenceDefault().getValue());
		}
	}

	public void resetOrientationPreferences() {
		orientationPreferencesMap = new EnumMap<>(SexualOrientation.class);
		for(SexualOrientation o : SexualOrientation.values()) {
			orientationPreferencesMap.put(o, o.getOrientationPreferenceDefault().getValue());
		}
	}

	public void resetFetishPreferences() {
		fetishPreferencesMap = new HashMap<>();
		for(AbstractFetish f : Fetish.getAllFetishes()) {
			fetishPreferencesMap.put(f, f.getFetishPreferenceDefault().getValue());
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
	
	/** 0=off, 1=taur-only, 2=on*/
	public int getUddersLevel() {
		return udders;
	}
	
	public void setUddersLevel(int udders) {
		this.udders = udders;
	}
}
