package com.lilithsthrone.game.character;

import java.io.Serializable;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.DifficultyLevel;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.NippleType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TongueType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AddictionLevel;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodyShape;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishLevel;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCOffspring;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.combat.SpecialAttack;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.SlaveryManagementDialogue;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryAttributeChange;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryEncyclopediaUnlock;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingSet;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.PregnancyDescriptor;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.slavery.SlaveJob;
import com.lilithsthrone.game.slavery.SlaveJobSetting;
import com.lilithsthrone.game.slavery.SlavePermission;
import com.lilithsthrone.game.slavery.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.World;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;

/**
 * The class for all the game's characters. I think this is the biggest class in the game.
 * 
 * @since 0.1.0
 * @version 0.1.89
 * @author Innoxia
 */
public abstract class GameCharacter implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;
	
	/** Calculation description as used in getAttributeValue() */
	public static final String HEALTH_CALCULATION = "Level*10 + STR + Bonus HP";
	/** Calculation description as used in getAttributeValue() */
	public static final String MANA_CALCULATION = "Level*10 + INT + Bonus WP";
	/** Calculation description as used in getAttributeValue() */
	public static final String STAMINA_CALCULATION = "Level*10 + FIT + Bonus ST";

	
	// Core variables:
	protected String id;
	protected NameTriplet nameTriplet;
	protected String surname;
	protected boolean playerKnowsName;
	protected String playerPetName = "";
	protected String description;
	protected int level;
	
	protected History history;
	protected Personality personality;
	protected SexualOrientation sexualOrientation;
	private float obedience;

	private int experience, levelUpPoints, perkPoints;
	
	
	// Location:
	protected WorldType worldLocation;
	protected WorldType homeWorldLocation;
	protected Vector2i location;
	protected Vector2i homeLocation;
	
	
	// Body:
	protected Body body;
	protected Gender genderIdentity; // What gender this character prefers to be. Used to determine NPC demonic transformations (i.e. a demon who identifies as a female will transform back into a female whenever possible.)
	
	
	// Inventory:
	protected CharacterInventory inventory;

	
	// Attributes, perks & status effects:
	protected Map<Attribute, Float> attributes;
	protected Map<Attribute, Float> bonusAttributes;
	protected Map<Attribute, Float> potionAttributes;
	protected Set<Perk> perks;
	protected Set<Fetish> fetishes;
	protected Map<Fetish, FetishDesire> fetishDesireMap;
	protected Map<Fetish, Integer> fetishExperienceMap;
	protected Map<StatusEffect, Integer> statusEffects;
	protected Map<StatusEffect, String> statusEffectDescriptions;
	

	// Relationship stats:
	/** String is character ID*/
	private Map<String, Float> affectionMap;
	

	// Pregnancy:
	protected long timeProgressedToFinalPregnancyStage;
	protected List<PregnancyPossibility> potentialPartnersAsMother, potentialPartnersAsFather;
	protected Litter pregnantLitter;
	protected List<Litter> littersBirthed, littersFathered;
	
	
	// Family:
	protected String motherId, fatherId;
	protected int dayOfConception, dayOfBirth;

	
	// Slavery:
	protected List<String> slavesOwned;
	protected String owner;
	protected DialogueNodeOld enslavementDialogue;
	
	protected SlaveJob slaveJob;
	protected List<SlaveJobSetting> slaveJobSettings;
	protected Map<SlavePermission, Set<SlavePermissionSetting>> slavePermissionSettings;
	
	protected boolean[] workHours;
	
	
	// Combat:
	protected Set<Spell> spells;
	protected Set<SpecialAttack> specialAttacks;
	protected float health, mana, stamina;

	
	// Sex:
	protected Set<CoverableArea> playerKnowsAreas;
	protected Map<OrificeType, Integer> cummedInAreaMap;
	
	
	// Stats:
	// Combat stats:
	private int foughtPlayerCount, lostCombatCount, wonCombatCount;
	
	
	// Sex stats:
	private int sexConsensualCount, sexAsSubCount, sexAsDomCount;
	private Map<SexType, Integer> sexCountMap;
	private Map<SexType, Integer> cumCountMap;
	private Map<SexType, String> virginityLossMap;
	/** String is partner ID*/
	private Map<String, Map<SexType, Integer>> sexPartnerMap;

	
	// Fluids:
	private float alcoholLevel = 0f;
	private Map<FluidType, Integer> addictionsMap;
	private Map<FluidType, Long> addictionsSatisfiedMap;
	
	
	// Misc.:
	protected static List<CharacterChangeEventListener> playerAttributeChangeEventListeners = new ArrayList<>();
	protected static List<CharacterChangeEventListener> NPCAttributeChangeEventListeners = new ArrayList<>();

	protected static List<CharacterChangeEventListener> NPCLocationChangeEventListeners = new ArrayList<>();
	protected static List<CharacterChangeEventListener> playerLocationChangeEventListeners = new ArrayList<>();

	protected static List<CharacterChangeEventListener> NPCInventoryChangeEventListeners = new ArrayList<>();
	protected static List<CharacterChangeEventListener> playerInventoryChangeEventListeners = new ArrayList<>();

	protected GameCharacter(NameTriplet nameTriplet, String description, int level, Gender startingGender, RacialBody startingRace, RaceStage stage, CharacterInventory inventory, WorldType worldLocation, PlaceType startingPlace) {
		
		id = "NOT_SET"; // id gets set in Game's addNPC method, so it doesn't matter if this is unique or not... Right?
		
		this.nameTriplet = nameTriplet;
		surname = "";
		playerKnowsName = true;
		this.description = description;
		this.level = level;
		
		this.worldLocation = worldLocation;
		this.homeWorldLocation = worldLocation;
		location = new Vector2i(0, 0);
		
		this.setWorldLocation(worldLocation);
		this.setLocation(Main.game.getWorlds().get(worldLocation).getCell(startingPlace).getLocation());
		homeLocation = location;
		
		history = History.UNEMPLOYED;
		personality = startingRace.getPersionality();
		sexualOrientation = startingRace.getSexualOrientation(startingGender);

		affectionMap = new HashMap<>();
		
		obedience = 0;
		
		slavesOwned = new ArrayList<>();
		owner = "";
		enslavementDialogue = null;
		
		slaveJob = SlaveJob.IDLE;
		slaveJobSettings = new ArrayList<>();
		slavePermissionSettings = new HashMap<>();
		for(SlavePermission permission : SlavePermission.values()) {
			slavePermissionSettings.put(permission, new HashSet<>());
			for(SlavePermissionSetting setting : permission.getSettings()) {
				if(setting.isDefaultValue()) {
					slavePermissionSettings.get(permission).add(setting);
				}
			}
		}
		
		workHours = new boolean[24];
		
		motherId = "";
		fatherId = "";
		dayOfConception = 0;
		dayOfBirth = 0;
		
		levelUpPoints = 0;
		perkPoints = 0;
		experience = 0;

		if (inventory == null) {
			this.inventory = new CharacterInventory(0);
		} else {
			this.inventory = inventory;
		}
		
		attributes = new EnumMap<>(Attribute.class);
		bonusAttributes = new EnumMap<>(Attribute.class);
		perks = new HashSet<>();
		fetishes = new HashSet<>();
		fetishDesireMap = new HashMap<>();
		fetishExperienceMap = new HashMap<>();
		statusEffectDescriptions = new EnumMap<>(StatusEffect.class);
		statusEffects = new EnumMap<>(StatusEffect.class);
		
		potionAttributes = new EnumMap<>(Attribute.class);

		spells = EnumSet.noneOf(Spell.class);
		specialAttacks = EnumSet.noneOf(SpecialAttack.class);

		// Player knowledge:
		playerKnowsAreas = new HashSet<>();
		
		cummedInAreaMap = new HashMap<>();
		for(OrificeType ot : OrificeType.values()) {
			cummedInAreaMap.put(ot, 0);
		}
		
		timeProgressedToFinalPregnancyStage = 1;
		pregnantLitter = null;
		littersBirthed = new ArrayList<>();
		littersFathered = new ArrayList<>();
		potentialPartnersAsMother = new ArrayList<>();
		potentialPartnersAsFather = new ArrayList<>();
		
		// Stats:
		foughtPlayerCount=0;
		lostCombatCount=0;
		wonCombatCount=0;
		
		// Sex Stats:
		sexConsensualCount=0;
		sexAsSubCount=0;
		sexAsDomCount=0;
		
		sexPartnerMap = new HashMap<>();
		
		sexCountMap = new HashMap<>();
		cumCountMap = new HashMap<>();
		virginityLossMap = new HashMap<>();
		
		addictionsMap = new HashMap<>();
		addictionsSatisfiedMap = new HashMap<>();
		
		// Start all attributes and bonus attributes at 0:
		for (Attribute a : Attribute.values()) {
			attributes.put(a, (float) a.getBaseValue());
			bonusAttributes.put(a, 0f);
		}
		// Set starting attributes based on the character's race
		for (Attribute a : startingRace.getAttributeModifiers().keySet()) {
			attributes.put(a, startingRace.getAttributeModifiers().get(a));
		}
		
		health = getAttributeValue(Attribute.HEALTH_MAXIMUM);
		mana = getAttributeValue(Attribute.MANA_MAXIMUM);
		stamina = getAttributeValue(Attribute.STAMINA_MAXIMUM);

		// Set the character's starting body based on their gender and race:
		setBody(startingGender, startingRace, stage);
		genderIdentity = startingGender;
		
	}
	


	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = doc.createElement("character");
		parentElement.appendChild(properties);
		
		// ************** Core information **************//
		
		Element characterCoreInfo = doc.createElement("core");
		Comment comment = doc.createComment("If you want to edit any of these values, just be warned that it might break the game...");
		properties.appendChild(characterCoreInfo);
		
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "id", this.getId());
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "pathName", this.getClass().getCanonicalName());
		
		Element name = doc.createElement("name");
		characterCoreInfo.appendChild(name);
		CharacterUtils.addAttribute(doc, name, "nameFeminine", this.getNameTriplet().getFeminine());
		CharacterUtils.addAttribute(doc, name, "nameAndrogynous", this.getNameTriplet().getAndrogynous());
		CharacterUtils.addAttribute(doc, name, "nameMasculine", this.getNameTriplet().getMasculine());
		
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "surname", this.getSurname());
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "description", this.getDescription());
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "playerPetName", playerPetName);
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "playerKnowsName", String.valueOf(this.isPlayerKnowsName()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "level", String.valueOf(this.getTrueLevel()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "version", Main.VERSION_NUMBER);
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "history", this.getHistory().toString());//TODO
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "personality", this.getPersonality().toString());//TODO
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "sexualOrientation", this.getSexualOrientation().toString());//TODO
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "obedience", String.valueOf(this.getObedienceValue()));//TODO
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "genderIdentity", String.valueOf(this.getGenderIdentity()));
		

		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "experience", String.valueOf(this.getExperience()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "levelUpPoints", String.valueOf(this.getLevelUpPoints()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "perkPoints", String.valueOf(this.getPerkPoints()));

		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "health", String.valueOf(this.getHealth()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "mana", String.valueOf(this.getMana()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "stamina", String.valueOf(this.getStamina()));
		
		// Knows area map: TODO
		Element characterPlayerKnowsAreas = doc.createElement("playerKnowsAreas");
		characterCoreInfo.appendChild(characterPlayerKnowsAreas);
		for(CoverableArea area: getPlayerKnowsAreas()){
			Element element = doc.createElement("area");
			characterPlayerKnowsAreas.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "type", area.toString());
		}
		
		characterCoreInfo.getParentNode().insertBefore(comment, characterCoreInfo);
		

		// ************** Location Information **************//
		
		Element locationInformation = doc.createElement("locationInformation");
		properties.appendChild(locationInformation);
		CharacterUtils.createXMLElementWithValue(doc, locationInformation, "worldLocation", this.getWorldLocation().toString());
		CharacterUtils.createXMLElementWithValue(doc, locationInformation, "homeWorldLocation", this.getHomeWorldLocation().toString());
		Element location = doc.createElement("location");
		locationInformation.appendChild(location);
		CharacterUtils.addAttribute(doc, location, "x", String.valueOf(this.getLocation().getX()));
		CharacterUtils.addAttribute(doc, location, "y", String.valueOf(this.getLocation().getY()));
		location = doc.createElement("homeLocation");
		locationInformation.appendChild(location);
		CharacterUtils.addAttribute(doc, location, "x", String.valueOf(this.getHomeLocation().getX()));
		CharacterUtils.addAttribute(doc, location, "y", String.valueOf(this.getHomeLocation().getY()));
		
		

		// ************** Body **************//
		
		Element characterBody = doc.createElement("body");
		properties.appendChild(characterBody);
		
		this.body.saveAsXML(characterBody, doc);
		
		
		
		// ************** Inventory **************//
		
		this.inventory.saveAsXML(properties, doc);
		
		
		
		// ************** Attributes **************//
		
		// Attributes:
		Element characterCoreAttributes = doc.createElement("attributes");
		properties.appendChild(characterCoreAttributes);
		for(Attribute att : Attribute.values()){
			Element element = doc.createElement("attribute");
			characterCoreAttributes.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "type", att.toString());
			CharacterUtils.addAttribute(doc, element, "value", String.valueOf(this.getBaseAttributeValue(att)));
		}
		
		Element characterPotionAttributes = doc.createElement("potionAttributes");
		properties.appendChild(characterPotionAttributes);
		for(Entry<Attribute, Float> entry : getPotionAttributes().entrySet()){
			Element element = doc.createElement("attribute");
			characterPotionAttributes.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "type", entry.getKey().toString());
			CharacterUtils.addAttribute(doc, element, "value", String.valueOf(entry.getValue()));
		}
		
		// Perks:
		Element characterPerks = doc.createElement("perks");
		properties.appendChild(characterPerks);
		for(Perk p : this.getPerks()){
			Element element = doc.createElement("perk");
			characterPerks.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "type", p.toString());
		}
		
		// Fetishes:
		Element characterFetishes = doc.createElement("fetishes");
		properties.appendChild(characterFetishes);
		for(Fetish f : this.getFetishes()){
			Element element = doc.createElement("fetish");
			characterFetishes.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "type", f.toString());
		}
		
		Element fetishDesire = doc.createElement("fetishDesire");
		properties.appendChild(fetishDesire);
		for(Entry<Fetish, FetishDesire> entry : this.getFetishDesireMap().entrySet()){
			Element fondenessEntry = doc.createElement("entry");
			fetishDesire.appendChild(fondenessEntry);
			
			CharacterUtils.addAttribute(doc, fondenessEntry, "fetish", entry.getKey().toString());
			CharacterUtils.addAttribute(doc, fondenessEntry, "desire", entry.getValue().toString());
		}
		
		Element fetishExperience = doc.createElement("fetishExperience");
		properties.appendChild(fetishExperience);
		for(Entry<Fetish, Integer> entry : this.getFetishExperienceMap().entrySet()){
			Element expEntry = doc.createElement("entry");
			fetishExperience.appendChild(expEntry);
			
			CharacterUtils.addAttribute(doc, expEntry, "fetish", entry.getKey().toString());
			CharacterUtils.addAttribute(doc, expEntry, "experience", String.valueOf(entry.getValue()));
		}
		
		
		// Status effects:
		Element characterStatusEffects = doc.createElement("statusEffects");
		properties.appendChild(characterStatusEffects);
		for(StatusEffect se : this.getStatusEffects()){
			Element element = doc.createElement("statusEffect");
			characterStatusEffects.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "type", se.toString());
			CharacterUtils.addAttribute(doc, element, "value", String.valueOf(this.getStatusEffectDuration(se)));
		}
		
		
		
		// ************** Relationships **************//
		
		Element characterRelationships = doc.createElement("characterRelationships");
		properties.appendChild(characterRelationships);
		for(Entry<String, Float> entry : this.getAffectionMap().entrySet()){
			Element relationship = doc.createElement("relationship");
			characterRelationships.appendChild(relationship);
			
			CharacterUtils.addAttribute(doc, relationship, "character", entry.getKey());
			CharacterUtils.addAttribute(doc, relationship, "value", String.valueOf(entry.getValue()));
		}
		
		
		
		// ************** Pregnancy **************//
		
		// Pregnancy:
		Element characterPregnancy = doc.createElement("pregnancy");
		properties.appendChild(characterPregnancy);
		CharacterUtils.addAttribute(doc, characterPregnancy, "timeProgressedToFinalPregnancyStage", String.valueOf(this.getTimeProgressedToFinalPregnancyStage()));
		
		Element characterPotentialPartnersAsMother = doc.createElement("potentialPartnersAsMother");
		characterPregnancy.appendChild(characterPotentialPartnersAsMother);
		for(PregnancyPossibility pregPoss : this.getPotentialPartnersAsMother()) {
			pregPoss.saveAsXML(characterPotentialPartnersAsMother, doc);
		}
		
		Element characterPotentialPartnersAsFather = doc.createElement("potentialPartnersAsFather");
		characterPregnancy.appendChild(characterPotentialPartnersAsFather);
		for(PregnancyPossibility pregPoss : this.getPotentialPartnersAsFather()) {
			pregPoss.saveAsXML(characterPotentialPartnersAsFather, doc);
		}
		
		Element characterPregnancyCurrentLitter = doc.createElement("pregnantLitter");
		characterPregnancy.appendChild(characterPregnancyCurrentLitter);
		if (this.getPregnantLitter() != null) {
			this.getPregnantLitter().saveAsXML(characterPregnancyCurrentLitter, doc);
		}
		
		Element characterPregnancyBirthedLitters = doc.createElement("birthedLitters");
		characterPregnancy.appendChild(characterPregnancyBirthedLitters);
		for(Litter litter : this.getLittersBirthed()) {
			litter.saveAsXML(characterPregnancyBirthedLitters, doc);
		}
		
		Element characterPregnancyLittersFathered = doc.createElement("littersFathered");
		characterPregnancy.appendChild(characterPregnancyLittersFathered);
		for(Litter litter : this.getLittersFathered()) {
			litter.saveAsXML(characterPregnancyLittersFathered, doc);
		}
		
		
		
		// ************** Family **************//

		Element characterFamily = doc.createElement("family");
		properties.appendChild(characterFamily);
		
		CharacterUtils.createXMLElementWithValue(doc, characterFamily, "motherId", this.getMotherId());
		CharacterUtils.createXMLElementWithValue(doc, characterFamily, "fatherId", this.getFatherId());
		CharacterUtils.createXMLElementWithValue(doc, characterFamily, "dayOfConception", String.valueOf(this.getDayOfConception()));
		CharacterUtils.createXMLElementWithValue(doc, characterFamily, "dayOfBirth", String.valueOf(this.getDayOfBirth()));
		
		
		
		// ************** Slavery **************//

		Element slaveryElement = doc.createElement("slavery");
		properties.appendChild(slaveryElement);
		
		Element slavesOwned = doc.createElement("slavesOwned");
		slaveryElement.appendChild(slavesOwned);
		for(String slave : this.getSlavesOwned()) {
			Element element = doc.createElement("slave");
			slavesOwned.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "id", slave);
		}
		
		CharacterUtils.createXMLElementWithValue(doc, slaveryElement, "owner", this.getOwner()==null?"":this.getOwner().getId());
		CharacterUtils.createXMLElementWithValue(doc, slaveryElement, "slaveJob", this.getSlaveJob().toString());
		
		Element slaveJobSettings = doc.createElement("slaveJobSettings");
		slaveryElement.appendChild(slaveJobSettings);
		for(SlaveJobSetting setting : this.getSlaveJobSettings()) {
			Element element = doc.createElement("setting");
			slaveJobSettings.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "value", setting.toString());
		}
		
		Element slavePermissionSettings = doc.createElement("slavePermissionSettings");
		slaveryElement.appendChild(slavePermissionSettings);
		for(Entry<SlavePermission, Set<SlavePermissionSetting>> entry : this.getSlavePermissionSettings().entrySet()) {
			Element element = doc.createElement("permission");
			slavePermissionSettings.appendChild(element);

			CharacterUtils.addAttribute(doc, element, "type", entry.getKey().toString());
			for(SlavePermissionSetting setting : entry.getValue()) {
				Element settingElement = doc.createElement("setting");
				element.appendChild(settingElement);
				CharacterUtils.addAttribute(doc, settingElement, "value", setting.toString());
			}
		}
		

		Element slaveWorkHours = doc.createElement("slaveWorkHours");
		slaveryElement.appendChild(slaveWorkHours);
		for(int i=0; i<workHours.length; i++) {
			CharacterUtils.addAttribute(doc, slaveWorkHours, "hour"+String.valueOf(i), String.valueOf(workHours[i]));
		}
		
		
		
		// ************** Sex Stats **************//
		
		Element characterSexStats = doc.createElement("sexStats");
		properties.appendChild(characterSexStats);
		
		Element characterCummedInAreas = doc.createElement("cummedInAreas");
		characterSexStats.appendChild(characterCummedInAreas);
		for(OrificeType orifice : OrificeType.values()) {
			Element element = doc.createElement("entry");
			characterCummedInAreas.appendChild(element);

			CharacterUtils.addAttribute(doc, element, "orifice", orifice.toString());
			CharacterUtils.addAttribute(doc, element, "cumQuantity", String.valueOf(this.getCummedInAreaMap().get(orifice)));
		}
		
		CharacterUtils.createXMLElementWithValue(doc, characterSexStats, "sexConsensualCount", String.valueOf(this.getSexConsensualCount()));
		CharacterUtils.createXMLElementWithValue(doc, characterSexStats, "sexAsSubCount", String.valueOf(this.getSexAsSubCount()));
		CharacterUtils.createXMLElementWithValue(doc, characterSexStats, "sexAsDomCount", String.valueOf(this.getSexAsDomCount()));
		
		Element characterCumCount = doc.createElement("cumCounts");
		characterSexStats.appendChild(characterCumCount);
		for(SexParticipantType participant : SexParticipantType.values()) {
			for(PenetrationType pt : PenetrationType.values()) {
				for(OrificeType ot : OrificeType.values()) {
					if(this.getCumCount(new SexType(participant, pt, ot)) > 0) {
						Element element = doc.createElement("cumCount");
						characterCumCount.appendChild(element);

						CharacterUtils.addAttribute(doc, element, "participantType", participant.toString());
						CharacterUtils.addAttribute(doc, element, "penetrationType", pt.toString());
						CharacterUtils.addAttribute(doc, element, "orificeType", ot.toString());
						CharacterUtils.addAttribute(doc, element, "count", String.valueOf(this.getCumCount(new SexType(participant, pt, ot))));
					}
				}
			}
		}

		Element characterSexCount = doc.createElement("sexCounts");
		characterSexStats.appendChild(characterSexCount);
		for(SexParticipantType participant : SexParticipantType.values()) {
			for(PenetrationType pt : PenetrationType.values()) {
				for(OrificeType ot : OrificeType.values()) {
					if(this.getSexCount(new SexType(participant, pt, ot)) > 0) {
						Element element = doc.createElement("sexCount");
						characterSexCount.appendChild(element);

						CharacterUtils.addAttribute(doc, element, "participantType", participant.toString());
						CharacterUtils.addAttribute(doc, element, "penetrationType", pt.toString());
						CharacterUtils.addAttribute(doc, element, "orificeType", ot.toString());
						CharacterUtils.addAttribute(doc, element, "count", String.valueOf(this.getSexCount(new SexType(participant, pt, ot))));
					}
				}
			}
		}

		Element characterVirginityTakenBy = doc.createElement("virginityTakenBy");
		characterSexStats.appendChild(characterVirginityTakenBy);
		for(SexParticipantType participant : SexParticipantType.values()) {
			for(PenetrationType pt : PenetrationType.values()) {
				for(OrificeType ot : OrificeType.values()) {
					if(this.getVirginityLoss(new SexType(participant, pt, ot))!=null && !this.getVirginityLoss(new SexType(participant, pt, ot)).isEmpty()) {
						Element element = doc.createElement("virginity");
						characterVirginityTakenBy.appendChild(element);
	
						CharacterUtils.addAttribute(doc, element, "participantType", participant.toString());
						CharacterUtils.addAttribute(doc, element, "penetrationType", pt.toString());
						CharacterUtils.addAttribute(doc, element, "orificeType", ot.toString());
						CharacterUtils.addAttribute(doc, element, "takenBy", String.valueOf(this.getVirginityLoss(new SexType(participant, pt, ot))));
					}
				}
			}
		}
		
		Element sexPartnerMapElement = doc.createElement("sexPartnerMap");
		characterSexStats.appendChild(sexPartnerMapElement);
		for(String s : sexPartnerMap.keySet()) {
			Element element = doc.createElement("id");
			sexPartnerMapElement.appendChild(element);
			CharacterUtils.addAttribute(doc, element, "value", s);
			
			for(Entry<SexType, Integer> entry : sexPartnerMap.get(s).entrySet()) {
				Element entryElement = doc.createElement("entry");
				element.appendChild(entryElement);

				CharacterUtils.addAttribute(doc, entryElement, "participantType", entry.getKey().getAsParticipant().toString());
				CharacterUtils.addAttribute(doc, entryElement, "penetrationType", entry.getKey().getPenetrationType().toString());
				CharacterUtils.addAttribute(doc, entryElement, "orificeType", entry.getKey().getOrificeType().toString());
				CharacterUtils.addAttribute(doc, entryElement, "count", String.valueOf(entry.getValue()));
			}
		}
		
		
		
		// ************** Fluids **************//
		
		
		Element characterAddictions = doc.createElement("addictions");
		properties.appendChild(characterAddictions);
		CharacterUtils.addAttribute(doc, characterAddictions, "alcoholLevel", String.valueOf(alcoholLevel));
		
		Element addictionMap = doc.createElement("addictionMap");
		characterAddictions.appendChild(addictionMap);
		for(FluidType ft : addictionsMap.keySet()){
			Element element = doc.createElement("addiction");
			addictionMap.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "type", ft.toString());
			CharacterUtils.addAttribute(doc, element, "value", String.valueOf(addictionsMap.get(ft)));
		}
		
		Element addictionSatisfiedMap = doc.createElement("addictionSatisfiedMap");
		characterAddictions.appendChild(addictionSatisfiedMap);
		for(FluidType ft : addictionsSatisfiedMap.keySet()){
			Element element = doc.createElement("addiction");
			addictionSatisfiedMap.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "type", ft.toString());
			CharacterUtils.addAttribute(doc, element, "value", String.valueOf(addictionsSatisfiedMap.get(ft)));
		}
		
		
		
		return properties;
	}
	
	public static void loadGameCharacterVariablesFromXML(GameCharacter character, StringBuilder log, Element parentElement, Document doc, CharacterImportSetting... settings) {

		boolean noPregnancy = Arrays.asList(settings).contains(CharacterImportSetting.NO_PREGNANCY);
		
		
		// ************** Core information **************//
		
		NodeList nodes = parentElement.getElementsByTagName("core");
		Element element = (Element) nodes.item(0);

		if(((Element)element.getElementsByTagName("id").item(0))!=null) {
			character.setId(((Element)element.getElementsByTagName("id").item(0)).getAttribute("value"));
			CharacterUtils.appendToImportLog(log, "</br>Set id: " + character.getId());
		}
		
		// Name:
		if(!((Element)element.getElementsByTagName("name").item(0)).getAttribute("value").isEmpty()) {
			character.setName(new NameTriplet(((Element)element.getElementsByTagName("name").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "</br>Set name: " + ((Element)element.getElementsByTagName("name").item(0)).getAttribute("value"));
		} else {
			Element nameElement = ((Element)element.getElementsByTagName("name").item(0));
			character.setName(new NameTriplet(
					nameElement.getAttribute("nameMasculine"),
					nameElement.getAttribute("nameAndrogynous"),
					nameElement.getAttribute("nameFeminine")));
		}
		
		// Surname:
		if(element.getElementsByTagName("surname")!=null && element.getElementsByTagName("surname").getLength()>0) {
			character.setSurname(((Element)element.getElementsByTagName("surname").item(0)).getAttribute("value"));
			CharacterUtils.appendToImportLog(log, "</br>Set surname: " + ((Element)element.getElementsByTagName("surname").item(0)).getAttribute("value"));
		}
		
		// Level:
		character.setLevel(Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")));
		CharacterUtils.appendToImportLog(log, "</br>Set level: " + Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")));
		
		// Sexual Orientation:
		if(element.getElementsByTagName("sexualOrientation").getLength()!=0) {
			if(!((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value").equals("null")) {
				character.setSexualOrientation(SexualOrientation.valueOf(((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value")));
				CharacterUtils.appendToImportLog(log, "</br>Set Sexual Orientation: " + SexualOrientation.valueOf(((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value")));
			} else {
				character.setSexualOrientation(SexualOrientation.AMBIPHILIC);
				CharacterUtils.appendToImportLog(log, "</br>Set Sexual Orientation: " + SexualOrientation.AMBIPHILIC);
			}
		}

		if(element.getElementsByTagName("description").getLength()!=0) {
			character.setDescription(((Element)element.getElementsByTagName("description").item(0)).getAttribute("value"));
			CharacterUtils.appendToImportLog(log, "</br>Set description");
		}
		if(element.getElementsByTagName("playerPetName").getLength()!=0) {
			String petName = ((Element)element.getElementsByTagName("playerPetName").item(0)).getAttribute("value");
			character.setPlayerPetName(petName);
			CharacterUtils.appendToImportLog(log, "</br>Set playerPetName: "+petName);
		}
		if(element.getElementsByTagName("playerKnowsName").getLength()!=0) {
			character.setPlayerKnowsName(Boolean.valueOf(((Element)element.getElementsByTagName("playerKnowsName").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "</br>Set playerKnowsName: "+character.isPlayerKnowsName());
		}
		if(element.getElementsByTagName("history").getLength()!=0) {
			character.setHistory(History.valueOf(((Element)element.getElementsByTagName("history").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "</br>Set history: "+character.getHistory());
		}
		if(element.getElementsByTagName("personality").getLength()!=0) {
			String personality = ((Element)element.getElementsByTagName("personality").item(0)).getAttribute("value");
			if(personality.equals("AIR_SOCIALABLE")) {
				personality = "AIR_SOCIABLE";
			}
			character.setPersonality(Personality.valueOf(personality));
			CharacterUtils.appendToImportLog(log, "</br>Set personality: "+character.getPersonality());
		}
		if(element.getElementsByTagName("obedience").getLength()!=0) {
			character.setObedience(Float.valueOf(((Element)element.getElementsByTagName("obedience").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "</br>Set obedience: "+character.getObedience());
		}
		
		boolean setGenderIdentity = false;
		if(element.getElementsByTagName("genderIdentity").getLength()!=0) {
			try {
				if(!((Element)element.getElementsByTagName("genderIdentity").item(0)).getAttribute("value").equals("null")) {
					character.setGenderIdentity(Gender.valueOf(((Element)element.getElementsByTagName("genderIdentity").item(0)).getAttribute("value")));
					CharacterUtils.appendToImportLog(log, "</br>Set genderIdentity: "+character.getGenderIdentity());
					setGenderIdentity = true;
				}
			} catch (Exception ex) {
			}
		}
		
		
		
		int extraLevelUpPoints=0;
		// If there is a version number, attributes should be working correctly:
		if(element.getElementsByTagName("version").item(0)!=null) {
			nodes = parentElement.getElementsByTagName("attributes");
			Element attElement = (Element) nodes.item(0);
			for(int i=0; i<attElement.getElementsByTagName("attribute").getLength(); i++){
				Element e = ((Element)attElement.getElementsByTagName("attribute").item(i));
				
				try {
					character.setAttribute(Attribute.valueOf(e.getAttribute("type")), Float.valueOf(e.getAttribute("value")), false);
					CharacterUtils.appendToImportLog(log, "</br>Set Attribute: "+Attribute.valueOf(e.getAttribute("type")).getName() +" to "+ Float.valueOf(e.getAttribute("value")));
				}catch(IllegalArgumentException ex){
				}
			}
			
		} else {
			extraLevelUpPoints = (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")) * 5);
			CharacterUtils.appendToImportLog(log, "</br>Old character version. Extra LevelUpPoints set to: "+(Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")) * 5));
		}
		
		if(element.getElementsByTagName("health").getLength()!=0) {
			character.setHealth(Float.valueOf(((Element)element.getElementsByTagName("health").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "</br>Set health: "+character.getHealth());
		}
		if(element.getElementsByTagName("mana").getLength()!=0) {
			character.setMana(Float.valueOf(((Element)element.getElementsByTagName("mana").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "</br>Set mana: "+character.getMana());
		}
		if(element.getElementsByTagName("stamina").getLength()!=0) {
			character.setStamina(Float.valueOf(((Element)element.getElementsByTagName("stamina").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "</br>Set stamina: "+character.getStamina());
		}

		nodes = parentElement.getElementsByTagName("playerKnowsAreas");
		Element knowsElement = (Element) nodes.item(0);
		if(knowsElement!=null) {
			for(int i=0; i<knowsElement.getElementsByTagName("area").getLength(); i++){
				Element e = ((Element)knowsElement.getElementsByTagName("area").item(i));

				try {
					character.getPlayerKnowsAreas().add(CoverableArea.valueOf(e.getAttribute("type")));
					CharacterUtils.appendToImportLog(log, "</br>Added knows area: "+CoverableArea.valueOf(e.getAttribute("type")).getName());
				}catch(IllegalArgumentException ex){
				}
			}
		}
		
		
		nodes = parentElement.getElementsByTagName("playerCore");
		if(nodes.getLength()>0) { // Old version support:
			
			character.setPerkPoints((Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")))-1);
			CharacterUtils.appendToImportLog(log, "</br>Set perkPoints: (TEMP FIX) " + (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))-1));
			
			element = (Element) nodes.item(0);
	
			character.incrementExperience(Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "</br>Set experience: " + Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")));
			
			character.setLevelUpPoints(Integer.valueOf(((Element)element.getElementsByTagName("levelUpPoints").item(0)).getAttribute("value")) + extraLevelUpPoints);
			CharacterUtils.appendToImportLog(log, "</br>Set levelUpPoints: " + (Integer.valueOf(((Element)element.getElementsByTagName("levelUpPoints").item(0)).getAttribute("value")) + extraLevelUpPoints));
			
			
		} else {
			character.incrementExperience(Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "</br>Set experience: " + Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")));
			
			character.setLevelUpPoints(Integer.valueOf(((Element)element.getElementsByTagName("levelUpPoints").item(0)).getAttribute("value")) + extraLevelUpPoints);
			CharacterUtils.appendToImportLog(log, "</br>Set levelUpPoints: " + (Integer.valueOf(((Element)element.getElementsByTagName("levelUpPoints").item(0)).getAttribute("value")) + extraLevelUpPoints));
			
			try {
				character.setPerkPoints(Integer.valueOf(((Element)element.getElementsByTagName("perkPoints").item(0)).getAttribute("value")));
				CharacterUtils.appendToImportLog(log, "</br>Set perkPoints: " + (Integer.valueOf(((Element)element.getElementsByTagName("perkPoints").item(0)).getAttribute("value")) + extraLevelUpPoints));
			} catch(Exception ex) {
			}
		}
		
		
		
		// ************** Location Information **************//
		
		nodes = parentElement.getElementsByTagName("locationInformation");
		element = (Element) nodes.item(0);
		if(element!=null) {
			character.setLocation(
					WorldType.valueOf(((Element)element.getElementsByTagName("worldLocation").item(0)).getAttribute("value")),
					new Vector2i(
							Integer.valueOf(((Element)element.getElementsByTagName("location").item(0)).getAttribute("x")),
							Integer.valueOf(((Element)element.getElementsByTagName("location").item(0)).getAttribute("y"))),
					false);

			character.setHomeLocation(
					WorldType.valueOf(((Element)element.getElementsByTagName("homeWorldLocation").item(0)).getAttribute("value")),
					new Vector2i(
							Integer.valueOf(((Element)element.getElementsByTagName("homeLocation").item(0)).getAttribute("x")),
							Integer.valueOf(((Element)element.getElementsByTagName("homeLocation").item(0)).getAttribute("y"))));
			
		} else {
			character.setLocation(new Vector2i(0, 0));
		}
		
		

		// ************** Body **************//
		
		character.body = Body.loadFromXML(log, (Element) parentElement.getElementsByTagName("body").item(0), doc);
		if(!setGenderIdentity) {
			character.setGenderIdentity(character.getGender());
		}
		
		
		
		// ************** Inventory **************//
		
		character.resetInventory();
		
		nodes = parentElement.getElementsByTagName("characterInventory");
		element = (Element) nodes.item(0);
		if(element!=null) {
			character.inventory = CharacterInventory.loadFromXML(element, doc);
			
			for(AbstractClothing clothing : character.getClothingCurrentlyEquipped()) {
				character.incrementBonusAttribute(Attribute.RESISTANCE_PHYSICAL, clothing.getClothingType().getPhysicalResistance());
				for (Entry<Attribute, Integer> e : clothing.getAttributeModifiers().entrySet()) {
					character.incrementBonusAttribute(e.getKey(), e.getValue());
				}
			}
			
			if(character.getMainWeapon()!=null) {
				for (Entry<Attribute, Integer> e : character.getMainWeapon().getAttributeModifiers().entrySet()) {
					character.incrementBonusAttribute(e.getKey(), e.getValue());
				}
			}
			
			if(character.getOffhandWeapon()!=null) {
				for (Entry<Attribute, Integer> e : character.getOffhandWeapon().getAttributeModifiers().entrySet()) {
					character.incrementBonusAttribute(e.getKey(), e.getValue());
				}
			}
			
		} else {
			CharacterCreation.getDressed(character, false);
		}
		
		
		
		// ************** Attributes **************//
		
		// Core attributes are set in the first section.
		
		// Potion attributes:
		nodes = parentElement.getElementsByTagName("potionAttributes");
		Element attElement = (Element) nodes.item(0);
		if(attElement!=null) {
			for(int i=0; i<attElement.getElementsByTagName("attribute").getLength(); i++){
				Element e = ((Element)attElement.getElementsByTagName("attribute").item(i));

				try {
					character.addPotionEffect(Attribute.valueOf(e.getAttribute("type")), Float.valueOf(e.getAttribute("value")));
					CharacterUtils.appendToImportLog(log, "</br>Set Potion Attribute: "+Attribute.valueOf(e.getAttribute("type")).getName() +" to "+ Float.valueOf(e.getAttribute("value")));
				}catch(IllegalArgumentException ex){
				}
			}
		}
		
		// Perks:
		nodes = parentElement.getElementsByTagName("perks");
		element = (Element) nodes.item(0);
		if(element!=null) {
			for(int i=0; i<element.getElementsByTagName("perk").getLength(); i++){
				Element e = ((Element)element.getElementsByTagName("perk").item(i));
				
				try {
					if(!e.getAttribute("value").isEmpty()) {
						if(Boolean.valueOf(e.getAttribute("value"))) {
							if(Perk.valueOf(e.getAttribute("type")) != null) {
								character.addPerk(Perk.valueOf(e.getAttribute("type")));
								CharacterUtils.appendToImportLog(log, "</br>Added Perk: "+Perk.valueOf(e.getAttribute("type")).getName(character));
							}
						}
					} else {
						if(Perk.valueOf(e.getAttribute("type")) != null) {
							character.addPerk(Perk.valueOf(e.getAttribute("type")));
							CharacterUtils.appendToImportLog(log, "</br>Added Perk: "+Perk.valueOf(e.getAttribute("type")).getName(character));
						}
					}
				}catch(IllegalArgumentException ex){
				}
			}
		}
		
		// Fetishes:
		nodes = parentElement.getElementsByTagName("fetishes");
		element = (Element) nodes.item(0);
		if(element!=null) {
			if(element.getElementsByTagName("fetish").item(0)!=null && !((Element)element.getElementsByTagName("fetish").item(0)).getAttribute("value").isEmpty()) {
				for(int i=0; i<element.getElementsByTagName("fetish").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("fetish").item(i));
					
					try {
						if(e.getAttribute("type").equals("FETISH_NON_CON")) { // Support for old non-con fetish:
							character.incrementEssenceCount(TFEssence.ARCANE, 5);
							CharacterUtils.appendToImportLog(log, "</br>Added refund for old non-con fetish. (+5 arcane essences)");
							
						} else if(Fetish.valueOf(e.getAttribute("type")) != null) {
							if(Boolean.valueOf(((Element)element.getElementsByTagName("fetish").item(0)).getAttribute("value"))) {
								character.addFetish(Fetish.valueOf(e.getAttribute("type")));
								CharacterUtils.appendToImportLog(log, "</br>Added Fetish: "+Fetish.valueOf(e.getAttribute("type")).getName(character));
							}
						}
					}catch(IllegalArgumentException ex){
					}
				}
				
			} else {
				for(int i=0; i<element.getElementsByTagName("fetish").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("fetish").item(i));
					
					try {
						if(e.getAttribute("type").equals("FETISH_NON_CON")) { // Support for old non-con fetish:
							character.incrementEssenceCount(TFEssence.ARCANE, 5);
							CharacterUtils.appendToImportLog(log, "</br>Added refund for old non-con fetish. (+5 arcane essences)");
							
						} else if(Fetish.valueOf(e.getAttribute("type")) != null) {
							character.addFetish(Fetish.valueOf(e.getAttribute("type")));
							CharacterUtils.appendToImportLog(log, "</br>Added Fetish: "+Fetish.valueOf(e.getAttribute("type")).getName(character));
						}
					}catch(IllegalArgumentException ex){
					}
				}
			}
		}

		nodes = parentElement.getElementsByTagName("fetishDesire");
		element = (Element) nodes.item(0);
		if(element!=null) {
			for(int i=0; i<element.getElementsByTagName("entry").getLength(); i++){
				Element e = ((Element)element.getElementsByTagName("entry").item(i));

				try {
					character.setFetishDesire(Fetish.valueOf(e.getAttribute("fetish")), FetishDesire.valueOf(e.getAttribute("desire")));
					CharacterUtils.appendToImportLog(log, "</br>Set fetish desire: "+e.getAttribute("fetish") +" , "+ e.getAttribute("desire"));
				}catch(IllegalArgumentException ex){
				}
			}
		}
		
		nodes = parentElement.getElementsByTagName("fetishExperience");
		element = (Element) nodes.item(0);
		if(element!=null) {
			for(int i=0; i<element.getElementsByTagName("entry").getLength(); i++){
				Element e = ((Element)element.getElementsByTagName("entry").item(i));

				try {
					character.setFetishExperience(Fetish.valueOf(e.getAttribute("fetish")), Integer.valueOf(e.getAttribute("experience")));
					CharacterUtils.appendToImportLog(log, "</br>Set fetish experience: "+e.getAttribute("fetish") +" , "+ e.getAttribute("experience"));
				}catch(IllegalArgumentException ex){
				}
			}
		}
		
		
		// Status Effects:
		nodes = parentElement.getElementsByTagName("statusEffects");
		element = (Element) nodes.item(0);
		for(int i=0; i<element.getElementsByTagName("statusEffect").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("statusEffect").item(i));
			
			try {
				if(Integer.valueOf(e.getAttribute("value"))!=-1) {
					StatusEffect effect = StatusEffect.valueOf(e.getAttribute("type"));
					if(!noPregnancy || (effect!=StatusEffect.PREGNANT_0 && effect!=StatusEffect.PREGNANT_1 && effect!=StatusEffect.PREGNANT_2 && effect!=StatusEffect.PREGNANT_3)) {
						character.addStatusEffect(effect, Integer.valueOf(e.getAttribute("value")));
						CharacterUtils.appendToImportLog(log, "</br>Added Status Effect: "+StatusEffect.valueOf(e.getAttribute("type")).getName(character)+" ("+Integer.valueOf(e.getAttribute("value"))+" minutes)");
					}
				}
			}catch(IllegalArgumentException ex){
			}
		}
		
		
		
		// ************** Relationships **************//
		
		nodes = parentElement.getElementsByTagName("characterRelationships");
		element = (Element) nodes.item(0);
		if(element!=null) {
			for(int i=0; i<element.getElementsByTagName("relationship").getLength(); i++){
				Element e = ((Element)element.getElementsByTagName("relationship").item(i));
				
				character.setAffection(e.getAttribute("character"), Float.valueOf(e.getAttribute("value")));
				CharacterUtils.appendToImportLog(log, "</br>Set Relationship: "+e.getAttribute("character") +" , "+ Float.valueOf(e.getAttribute("value")));
			}
		}
		
		
		
		// ************** Pregnancy **************//
		
		if(!noPregnancy) {
			nodes = parentElement.getElementsByTagName("pregnancy");
			Element pregnancyElement = (Element) nodes.item(0);
			if(pregnancyElement!=null) {
				CharacterUtils.appendToImportLog(log, "</br></br>Pregnancies:");
				
				character.setTimeProgressedToFinalPregnancyStage(Integer.valueOf(pregnancyElement.getAttribute("timeProgressedToFinalPregnancyStage")));
				
				nodes = pregnancyElement.getElementsByTagName("potentialPartnersAsMother");
				element = (Element) nodes.item(0);
				if(element!=null) {
					for(int i=0; i<element.getElementsByTagName("pregnancyPossibility").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("pregnancyPossibility").item(i));
						
						character.getPotentialPartnersAsMother().add(PregnancyPossibility.loadFromXML(e, doc));
						CharacterUtils.appendToImportLog(log, "</br>Added Pregnancy Possibility as mother.");
					}
				}
				
				nodes = pregnancyElement.getElementsByTagName("potentialPartnersAsFather");
				element = (Element) nodes.item(0);
				if(element!=null) {
					for(int i=0; i<element.getElementsByTagName("pregnancyPossibility").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("pregnancyPossibility").item(i));
						
						character.getPotentialPartnersAsFather().add(PregnancyPossibility.loadFromXML(e, doc));
						CharacterUtils.appendToImportLog(log, "</br>Added Pregnancy Possibility as father.");
					}
				}
				
				nodes = pregnancyElement.getElementsByTagName("pregnantLitter");
				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("litter").item(0);
				if(element!=null) {
					character.setPregnantLitter(Litter.loadFromXML(element, doc));
					CharacterUtils.appendToImportLog(log, "</br>Added Pregnant litter.");
				}
				
				nodes = pregnancyElement.getElementsByTagName("birthedLitters");
				element = (Element) nodes.item(0);
				if(element!=null) {
					for(int i=0; i<element.getElementsByTagName("litter").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("litter").item(i));
						
						character.getLittersBirthed().add(Litter.loadFromXML(e, doc));
						CharacterUtils.appendToImportLog(log, "</br>Added litter birthed.");
					}
				}
				
				nodes = pregnancyElement.getElementsByTagName("littersFathered");
				element = (Element) nodes.item(0);
				if(element!=null) {
					for(int i=0; i<element.getElementsByTagName("litter").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("litter").item(i));
						
						character.getLittersFathered().add(Litter.loadFromXML(e, doc));
						CharacterUtils.appendToImportLog(log, "</br>Added litter fathered.");
					}
				}
			}
		}
		
		
		// ************** Family **************//
		
		nodes = parentElement.getElementsByTagName("family");
		Element familyElement = (Element) nodes.item(0);
		if(familyElement!=null) {
			character.setMother(((Element)familyElement.getElementsByTagName("motherId").item(0)).getAttribute("value"));
			character.setFather(((Element)familyElement.getElementsByTagName("fatherId").item(0)).getAttribute("value"));
			character.setDayOfConception(Integer.valueOf(((Element)familyElement.getElementsByTagName("dayOfConception").item(0)).getAttribute("value")));
			character.setDayOfBirth(Integer.valueOf(((Element)familyElement.getElementsByTagName("dayOfBirth").item(0)).getAttribute("value")));
		}
		
		
		
		// ************** Slavery **************//
		
		nodes = parentElement.getElementsByTagName("slavery");
		Element slaveryElement = (Element) nodes.item(0);
		if(slaveryElement!=null) {
			
			for(int i=0; i<((Element) slaveryElement.getElementsByTagName("slavesOwned").item(0)).getElementsByTagName("slave").getLength(); i++){
				Element e = ((Element)slaveryElement.getElementsByTagName("slave").item(i));
				
				if(!e.getAttribute("id").equals("NOT_SET")) {
					character.getSlavesOwned().add(e.getAttribute("id"));
					CharacterUtils.appendToImportLog(log, "</br>Added owned slave: "+e.getAttribute("id"));
				}
			}
			

			character.setOwner(((Element)slaveryElement.getElementsByTagName("owner").item(0)).getAttribute("value"));
			CharacterUtils.appendToImportLog(log, "</br>Set owner: "+character.getOwnerId());
			
			character.setSlaveJob(SlaveJob.valueOf(((Element)slaveryElement.getElementsByTagName("slaveJob").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "</br>Set slave job: "+character.getSlaveJob());
			
			for(int i=0; i<((Element) slaveryElement.getElementsByTagName("slaveJobSettings").item(0)).getElementsByTagName("setting").getLength(); i++){
				Element e = ((Element)slaveryElement.getElementsByTagName("setting").item(i));
				
				SlaveJobSetting setting = SlaveJobSetting.valueOf(e.getAttribute("value"));
				character.addSlaveJobSettings(setting);
				CharacterUtils.appendToImportLog(log, "</br>Added slave job setting: "+setting);
			}
			
			// Clear settings first:
			for(SlavePermission key : character.getSlavePermissionSettings().keySet()) {
				character.getSlavePermissionSettings().get(key).clear();
			}
			
			for(int i=0; i<((Element) slaveryElement.getElementsByTagName("slavePermissionSettings").item(0)).getElementsByTagName("permission").getLength(); i++){
				Element e = ((Element)slaveryElement.getElementsByTagName("permission").item(i));
				SlavePermission slavePermission =  SlavePermission.valueOf(e.getAttribute("type"));
				
				for(int j=0; j<e.getElementsByTagName("setting").getLength(); j++){
					Element e2 = ((Element)e.getElementsByTagName("setting").item(j));
					
					SlavePermissionSetting setting = SlavePermissionSetting.valueOf(e2.getAttribute("value"));
					character.addSlavePermissionSetting(slavePermission, setting);
					CharacterUtils.appendToImportLog(log, "</br>Added slave permission setting: "+slavePermission+", "+setting);
				}
			}

			Element workHourElement = ((Element)slaveryElement.getElementsByTagName("slaveWorkHours").item(0));
			for(int i=0; i<character.workHours.length; i++) {
				character.workHours[i] = Boolean.valueOf(workHourElement.getAttribute("hour"+String.valueOf(i)));
				CharacterUtils.appendToImportLog(log, "</br>Set work hour: "+i+", "+character.workHours[i]);
			}
		}
		
		
		
		// ************** Sex Stats **************//
		
		nodes = parentElement.getElementsByTagName("sexStats");
		Element sexStatsElement = (Element) nodes.item(0);

		if(sexStatsElement.getElementsByTagName("sexConsensualCount").getLength()!=0) {
			character.setSexConsensualCount(Integer.valueOf(((Element)sexStatsElement.getElementsByTagName("sexConsensualCount").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "</br>Set consensual sex count: "+character.getSexConsensualCount());
		}

		if(sexStatsElement.getElementsByTagName("sexAsSubCount").getLength()!=0) {
			character.setSexAsSubCount(Integer.valueOf(((Element)sexStatsElement.getElementsByTagName("sexAsSubCount").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "</br>Set sub sex count: "+character.getSexAsSubCount());
		}

		if(sexStatsElement.getElementsByTagName("sexAsDomCount").getLength()!=0) {
			character.setSexAsDomCount(Integer.valueOf(((Element)sexStatsElement.getElementsByTagName("sexAsDomCount").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "</br>Set dom sex count: "+character.getSexAsDomCount());
		}
		
		
		// Cummed in areas:
		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("cummedInAreas").item(0);
		if(element!=null) {
			for(int i=0; i<element.getElementsByTagName("entry").getLength(); i++){
				Element e = ((Element)element.getElementsByTagName("entry").item(i));
				
				try {
					character.setCummedInArea(OrificeType.valueOf(e.getAttribute("orifice")), Integer.valueOf(e.getAttribute("cumQuantity")));
					CharacterUtils.appendToImportLog(log, "</br>Added cummed in area: "+e.getAttribute("orifice")+", "+Integer.valueOf(e.getAttribute("cumQuantity"))+"ml");
				}catch(Exception ex){
				}
			}
		}
		
		// Cum counts:
		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("cumCounts").item(0);
		for(int i=0; i<element.getElementsByTagName("cumCount").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("cumCount").item(i));
			
			try {
				for(int it =0 ; it<Integer.valueOf(e.getAttribute("count")) ; it++) {
					character.incrementCumCount(new SexType(SexParticipantType.valueOf(e.getAttribute("participantType")), PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))));
				}
				CharacterUtils.appendToImportLog(log, "</br>Added cum count:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" x "+Integer.valueOf(e.getAttribute("count")));
			}catch(Exception ex){
			}
		}
		
		// Sex counts:
		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("sexCounts").item(0);
		for(int i=0; i<element.getElementsByTagName("sexCount").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("sexCount").item(i));
			
			try {
				for(int it =0 ; it<Integer.valueOf(e.getAttribute("count")) ; it++) {
					character.incrementSexCount(new SexType(SexParticipantType.valueOf(e.getAttribute("participantType")), PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))));
				}
				CharacterUtils.appendToImportLog(log, "</br>Added sex count:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" x "+Integer.valueOf(e.getAttribute("count")));
			}catch(Exception ex){
			}
		}
		
		// Virginity losses:
		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("virginityTakenBy").item(0);
		for(int i=0; i<element.getElementsByTagName("virginity").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("virginity").item(i));

			try {
				character.setVirginityLoss(new SexType(SexParticipantType.valueOf(e.getAttribute("participantType")), PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))), e.getAttribute("takenBy"));
				CharacterUtils.appendToImportLog(log, "</br>Added virginity loss:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" (taken by:"+e.getAttribute("takenBy")+")");
			}catch(Exception ex){
			}
		}
		
		
		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("sexPartnerMap").item(0);
		if(element!=null) {
			for(int i=0; i<element.getElementsByTagName("id").getLength(); i++){
				Element e = ((Element)element.getElementsByTagName("id").item(i));
				character.sexPartnerMap.put(e.getAttribute("value"), new HashMap<>());
				
				for(int j=0; j<element.getElementsByTagName("entry").getLength(); j++){
					Element e2 = ((Element)element.getElementsByTagName("entry").item(j));

					try {
						character.sexPartnerMap.get(e.getAttribute("value")).put(
								new SexType(
										SexParticipantType.valueOf(e.getAttribute("participantType")),
										PenetrationType.valueOf(e2.getAttribute("penetrationType")),
										OrificeType.valueOf(e2.getAttribute("orificeType"))),
										Integer.valueOf(e2.getAttribute("count")));
						
						CharacterUtils.appendToImportLog(log, "</br>Added sex tracking: "+e.getAttribute("value")+" "+e2.getAttribute("penetrationType")+"/"+e2.getAttribute("orificeType")+" "+Integer.valueOf(e2.getAttribute("count")));
					}catch(Exception ex){
					}
				}
			}
		}
		
		
		// ************** Addictions **************//

		nodes = parentElement.getElementsByTagName("addictions");
		Element addictionsElement = (Element) nodes.item(0);
		
		if(!addictionsElement.getAttribute("alcoholLevel").isEmpty()) {
			try {
				character.setAlcoholLevel(Float.valueOf(addictionsElement.getAttribute("alcoholLevel")));
			} catch(Exception ex) {
			}
		}
		
		if(addictionsElement!=null) {
			element = (Element) addictionsElement.getElementsByTagName("addictionMap").item(0);
			for(int i=0; i<element.getElementsByTagName("addiction").getLength(); i++){
				Element e = ((Element)element.getElementsByTagName("addiction").item(i));
				
				character.setAddiction(FluidType.valueOf(e.getAttribute("type")), Integer.valueOf(e.getAttribute("value")));
				CharacterUtils.appendToImportLog(log, "</br>Added addiction:"+e.getAttribute("type")+" "+e.getAttribute("value"));
			}
			
			element = (Element) addictionsElement.getElementsByTagName("addictionSatisfiedMap").item(0);
			for(int i=0; i<element.getElementsByTagName("addiction").getLength(); i++){
				Element e = ((Element)element.getElementsByTagName("addiction").item(i));
				
				character.setLastTimeSatisfiedAddiction(FluidType.valueOf(e.getAttribute("type")), Long.valueOf(e.getAttribute("value")));
				CharacterUtils.appendToImportLog(log, "</br>Set satisfied time:"+e.getAttribute("type")+" "+e.getAttribute("value"));
			}
		}
	}
	
	public abstract boolean isUnique();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getMapIcon() {
		return getRace().getStatusEffect().getSVGString(this);
	}
	public String getHomeMapIcon() {
		return getRace().getStatusEffect().getSVGStringDesaturated(this);
	}

	public String speech(String text) {
		return UtilText.parseSpeech(text, this);
	}
	public String thought(String text) {
		return UtilText.parseThought(text, this);
	}
	public String getName(String determiner) {
		if (Character.isUpperCase(getName().charAt(0)) || determiner.isEmpty()) {
			return getName();
			
		} else {
			return (determiner.equalsIgnoreCase("a") || determiner.equalsIgnoreCase("an")
						?(Character.isUpperCase(determiner.charAt(0))
								?Util.capitaliseSentence(UtilText.generateSingularDeterminer(getName()))
								:UtilText.generateSingularDeterminer(getName()))
						:determiner)
					+ " " + getName();
		}
	}

	public boolean isPlayerKnowsName() {
		return playerKnowsName;
	}

	public void setPlayerKnowsName(boolean playerKnowsName) {
		this.playerKnowsName = playerKnowsName;
	}

	public Set<CoverableArea> getPlayerKnowsAreas() {
		return playerKnowsAreas;
	}

	public String getSpeechColour() {
		if (Femininity.valueOf(getFemininityValue()) == Femininity.MASCULINE || Femininity.valueOf(getFemininityValue()) == Femininity.MASCULINE_STRONG) {
			if(isPlayer())
				return Colour.MASCULINE.toWebHexString();
			else
				return Colour.MASCULINE_NPC.toWebHexString();

		} else if (Femininity.valueOf(getFemininityValue()) == Femininity.ANDROGYNOUS){
			if(isPlayer())
				return Colour.ANDROGYNOUS.toWebHexString();
			else
				return Colour.ANDROGYNOUS_NPC.toWebHexString();

		} else {
			if(isPlayer())
				return Colour.FEMININE.toWebHexString();
			else
				return Colour.FEMININE_NPC.toWebHexString();
		}
	}

	protected void updateAttributeListeners() {
		if (NPCAttributeChangeEventListeners != null)
			for (CharacterChangeEventListener eventListener : NPCAttributeChangeEventListeners)
				eventListener.onChange();
	}

	protected void updateLocationListeners() {
		if (NPCLocationChangeEventListeners != null)
			for (CharacterChangeEventListener eventListener : NPCLocationChangeEventListeners)
				eventListener.onChange();
	}

	public void updateInventoryListeners() {
		if (NPCInventoryChangeEventListeners != null)
			for (CharacterChangeEventListener eventListener : NPCInventoryChangeEventListeners)
				eventListener.onChange();
	}

	public static void addPlayerAttributeChangeEventListener(CharacterChangeEventListener eventListener) {
		playerAttributeChangeEventListeners.add(eventListener);
	}

	public static void addNPCAttributeChangeEventListener(CharacterChangeEventListener eventListener) {
		NPCAttributeChangeEventListeners.add(eventListener);
	}

	public static void addPlayerLocationChangeEventListener(CharacterChangeEventListener eventListener) {
		playerLocationChangeEventListeners.add(eventListener);
	}

	public static void addNPCLocationChangeEventListener(CharacterChangeEventListener eventListener) {
		NPCLocationChangeEventListeners.add(eventListener);
	}

	public static void addPlayerInventoryChangeEventListener(CharacterChangeEventListener eventListener) {
		playerInventoryChangeEventListeners.add(eventListener);
	}

	public static void addNPCInventoryChangeEventListener(CharacterChangeEventListener eventListener) {
		NPCInventoryChangeEventListeners.add(eventListener);
	}


	public String getBodyDescription() {
		return body.getDescription(this);
	}
	
	public void setBody(Gender startingGender, GameCharacter mother, GameCharacter father) {
		body = CharacterUtils.generateBody(startingGender, mother, father);

		postTransformationCalculation();
	}
	
	public void setBody(Gender startingGender, RacialBody startingBodyType, RaceStage stage) {
		body = CharacterUtils.generateBody(startingGender, startingBodyType, stage);

		postTransformationCalculation();
	}

	public Gender getGenderIdentity() {
		return genderIdentity;
	}

	public void setGenderIdentity(Gender genderIdentity) {
		this.genderIdentity = genderIdentity;
	}

	
	public String getName() {
		if(this.isSlave()) {
			if(this.getOwner().isPlayer()) {
				playerKnowsName = true;
			}
		}
		if((nameTriplet==null || !playerKnowsName) && !isPlayer()) {
			if(isFeminine()) {
				if(getRace()==Race.HUMAN)
					return "woman";
				else
					return getRace().getSingularFemaleName();
				
			} else {
				if(getRace()==Race.HUMAN)
					return "man";
				else
					return getRace().getSingularMaleName();
			}
			
		} else {
			return getNameIgnoresPlayerKnowledge();
		}
	}
	
	public void setName(NameTriplet nameTriplet) {
		this.nameTriplet = nameTriplet;
	}
	
	public String getNameIgnoresPlayerKnowledge() {
		if(getFemininityValue()>Femininity.ANDROGYNOUS.getMaximumFemininity()) {
			return nameTriplet.getFeminine();
			
		} else if(getFemininityValue()>=Femininity.ANDROGYNOUS.getMinimumFemininity()) {
			return nameTriplet.getAndrogynous();
			
		} else {
			return nameTriplet.getMasculine();
		}
	}
	
	public NameTriplet getNameTriplet() {
		return nameTriplet;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPlayerPetName() {
		if(playerPetName.isEmpty()) {
			if(Main.game.getPlayer()==null) {
				return "";
			}
			return Main.game.getPlayer().getName();
		} else {
			return playerPetName;
		}
	}
	
	public void setPlayerPetName(String playerPetName) {
		this.playerPetName = playerPetName;
	}

	public boolean isPlayer() {
		return false;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		// Revert attributes from old History:
		for (Attribute att : this.history.getAttributeModifiers().keySet())
			incrementBonusAttribute(att, -this.history.getAttributeModifiers().get(att));
		this.history.revertExtraEffects(this);

		// Implement attributes from new History:
		for (Attribute att : history.getAttributeModifiers().keySet())
			incrementBonusAttribute(att, history.getAttributeModifiers().get(att));
		history.applyExtraEffects(this);

		this.history = history;

		updateAttributeListeners();
	}
	
	public Personality getPersonality() {
		return personality;
	}

	public void setPersonality(Personality personality) {
		this.personality = personality;
	}

	public SexualOrientation getSexualOrientation() {
		return sexualOrientation;
	}

	public void setSexualOrientation(SexualOrientation sexualOrientation) {
		this.sexualOrientation = sexualOrientation;
	}

	
	// Obedience:
	
	public ObedienceLevel getObedience() {
		return ObedienceLevel.getObedienceLevelFromValue(obedience);
	}
	
	public float getObedienceValue() {
		return Math.round(obedience*100)/100f;
	}

	public String setObedience(float obedience) {
		
		this.obedience = Math.max(-100, Math.min(100, obedience));
		
		return UtilText.parse(this,
					"<p style='text-align:center'>"
						+ "[npc.Name] now has <b>"+(obedience>0?"+":"")+obedience+"</b> [style.boldObedience(obedience)]!</br>"
						+ ObedienceLevel.getDescription(this, ObedienceLevel.getObedienceLevelFromValue(obedience), true, false)
					+ "</p>");
	}
	
	public String incrementObedience(float increment) {
		
		setObedience(getObedienceValue()+increment);
		
		return UtilText.parse(this,
				"<p style='text-align:center'>"
						+ "[npc.Name] "+(increment>0?"[style.boldGrow(gains)]":"[style.boldShrink(loses)]")+" <b>"+Math.abs(increment)+"</b> [style.boldObedience(obedience)]!"
					+ "</p>");
	}
	
	public float getHourlyObedienceChange(int hour) {
		if(this.workHours[hour]) {
			if(this.getSlaveJob()==SlaveJob.IDLE) {
				return this.getHomeLocationPlace().getHourlyObedienceChange();
			}
			// To get rid of e.g. 2.3999999999999999999999:
			return Math.round(this.getSlaveJob().getObedienceGain(this)*100)/100f;
		}
		
		// To get rid of e.g. 2.3999999999999999999999:
		return Math.round(this.getHomeLocationPlace().getHourlyObedienceChange()*100)/100f;
	}
	
	public float getDailyObedienceChange() {
		float totalObedienceChange = 0;
		
		for (int workHour = 0; workHour < this.getTotalHoursWorked(); workHour++) {
			if(this.getSlaveJob()==SlaveJob.IDLE) {
				totalObedienceChange+=this.getHomeLocationPlace().getHourlyObedienceChange();
			}
			totalObedienceChange+=this.getSlaveJob().getObedienceGain(this);
			
		}
		
		for (int homeHour = 0; homeHour < 24-this.getTotalHoursWorked(); homeHour++) {
			totalObedienceChange+=this.getHomeLocationPlace().getHourlyObedienceChange();
		}
		// To get rid of e.g. 2.3999999999999999999999:
		return Math.round(totalObedienceChange*100)/100f;
	}
	
	public int getSlavesWorkingJob(SlaveJob job) {
		int i=0;
			for(String id : this.getSlavesOwned()) {
				if(Main.game.getNPCById(id).getSlaveJob()==job) {
					i++;
				}
			}
		return i;
	}
	
	public int getValueAsSlave() {
		int value = 1000;
		switch(this.getRace()) {
			case ANGEL:
				value = 50000;
				break;
			case CAT_MORPH: case DOG_MORPH:
				value = 800;
				break;
			case COW_MORPH: case HORSE_MORPH:
				value = 1500;
				break;
			case REINDEER_MORPH:
				value = 2000;
				break;
			case DEMON:
				value = 10000;
				break;
			case HARPY:
				value = 1200;
				break;
			case HUMAN:
				value = 400;
				break;
			case SLIME:
				break;
			case SQUIRREL_MORPH:
				value = 600;
				break;
			case ALLIGATOR_MORPH:
				value = 1000;
				break;
			case WOLF_MORPH:
				value = 1000;
				break;
		}
		
		value += (getFetishes().size()*50);
		
		value *= (100+(getObedienceValue()/2))/100f;
		
		return value;
	}
	
	public SlaveJob getSlaveJob() {
		return slaveJob;
	}

	public void setSlaveJob(SlaveJob slaveJob) {
		slaveJobSettings.clear();
		this.slaveJob = slaveJob;
		for(SlaveJobSetting jobSetting : slaveJob.getDefaultMutuallyExclusiveSettings()) {
			addSlaveJobSettings(jobSetting);
		}
	}
	
	public boolean addSlaveJobSettings(SlaveJobSetting setting) {
		if(slaveJobSettings.contains(setting)) {
			return false;
		}
		
		return slaveJobSettings.add(setting);
	}
	
	public boolean removeSlaveJobSettings(SlaveJobSetting setting) {
		return slaveJobSettings.remove(setting);
	}
	
	public List<SlaveJobSetting> getSlaveJobSettings() {
		return slaveJobSettings;
	}
	
	public boolean addSlavePermissionSetting(SlavePermission permission, SlavePermissionSetting setting) {
		if(permission.isMutuallyExclusiveSettings()) {
			slavePermissionSettings.get(permission).clear();
		}
		return slavePermissionSettings.get(permission).add(setting);
	}
	
	public boolean removeSlavePermissionSetting(SlavePermission permission, SlavePermissionSetting setting) {
		if(permission.isMutuallyExclusiveSettings()) {
			System.err.println("You cannot remove a setting from a mutually exclusive settings list!");
			return false;
		}
		return slavePermissionSettings.get(permission).remove(setting);
	}
	
	public boolean hasSlavePermissionSetting(SlavePermissionSetting setting) {
		for(SlavePermission permission : SlavePermission.values()) {
			if(slavePermissionSettings.get(permission).contains(setting)) {
				return true;
			}
		}
		return false;
	}
	
	public Map<SlavePermission, Set<SlavePermissionSetting>> getSlavePermissionSettings() {
		return slavePermissionSettings;
	}
	
	public void resetWorkHours() {
		for(int i = 0 ; i<workHours.length ; i++) {
			workHours[i] = false;
		}
	}
	
	public boolean[] getWorkHours() {
		return workHours;
	}
	
	public void setWorkHour(int i, boolean isWorking) {
		workHours[i] = isWorking;
	}
	
	public int getTotalHoursWorked() {
		if(this.getSlaveJob()==SlaveJob.IDLE) {
			return 24;
		}
		int count = 0;
		for(int i = 0 ; i<workHours.length ; i++) {
			if(workHours[i]) {
				count++;
			}
		}
		return count;
	}


	// Affection:
	/**
	 * Do not use this method to alter the map!
	 */
	public Map<String, Float> getAffectionMap() {
		return affectionMap;
	}
	
	public void clearAffectionMap() {
		affectionMap.clear();
	}
	
	public float getAffection(GameCharacter character) {
		affectionMap.putIfAbsent(character.getId(), 0f);
		
		return Math.round(affectionMap.get(character.getId())*100)/100f;
	}
	
	public AffectionLevel getAffectionLevel(GameCharacter character) {
		return AffectionLevel.getAffectionLevelFromValue(getAffection(character));
	}
	
	/**
	 * Sets this character's affection towards the supplied GameCharacter.
	 * 
	 * @param character
	 * @param affection
	 * @return
	 */
	public String setAffection(GameCharacter character, float affection) {
		affectionMap.put(character.getId(), Math.max(-100, Math.min(100, affection)));
		
		if(character.isPlayer()) {
			return UtilText.parse(this,
					"<p style='text-align:center'>"
						+ "[npc.Name] now has <b>"+(affection>0?"+":"")+affection+"</b> [style.boldAffection(affection)] towards you!</br>"
						+ AffectionLevel.getDescription(this, character, AffectionLevel.getAffectionLevelFromValue(affection), true)
					+ "</p>");
			
		} else {
			return UtilText.parse(character,
					"<p style='text-align:center'>"
						+ getName("The")+" now has <b>"+(affection>0?"+":"")+affection+"</b> [style.boldAffection(affection)] towards [npc.name]!</br>"
						+ AffectionLevel.getDescription(this, character, AffectionLevel.getAffectionLevelFromValue(affection), true)
					+ "</p>");
		}
	}
	
	public void setAffection(String id, float affection) {
		affectionMap.put(id, Math.max(-100, Math.min(100, affection)));
	}
	
	/**
	 * Increments this character's affection towards the supplied GameCharacter.
	 * 
	 * @param character
	 * @param affectionIncrement
	 * @return
	 */
	public String incrementAffection(GameCharacter character, float affectionIncrement) {
		setAffection(character, getAffection(character) + affectionIncrement);
		
		return UtilText.parse(character,
				"<p style='text-align:center'>"
						+ getName("The") + " "+(affectionIncrement>0?"[style.boldGrow(gains)]":"[style.boldShrink(loses)]")+" <b>"+Math.abs(affectionIncrement)+"</b> [style.boldAffection(affection)] towards "+(character.isPlayer()?"you":"[npc.name]")+"!"
					+ "</p>");
	}
	
	
	// Slavery:
	
	public DialogueNodeOld getEnslavementDialogue() {
		return enslavementDialogue;
	}
	
	public void setEnslavementDialogue(DialogueNodeOld enslavementDialogue) {
		this.enslavementDialogue = enslavementDialogue;
	}
	
	public void applyEnslavementEffects(GameCharacter enslaver) {
		this.setAffection(enslaver, -200);
		this.setObedience(-100);
	}
	
	public boolean isAbleToBeEnslaved() {
		return getEnslavementDialogue()!=null;
	}
	
	public List<String> getSlavesOwned() {
		return slavesOwned;
	}
	
	public int getNumberOfSlavesIdle() {
		int i=0;
		for(String id : slavesOwned) {
			if(Main.game.getNPCById(id).getSlaveJob()==SlaveJob.IDLE) {
				i++;
			}
		}
		return i;
	}
	
	public int getNumberOfSlavesInAdministration() {
		int i=0;
		for(String id : slavesOwned) {
			if(Main.game.getNPCById(id).getLocationPlace().getPlaceType() == PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION) {
				i++;
			}
		}
		return i;
	}
	
	public int getSlaveryTotalDailyIncome() {
		int i=0;
		for(String id : slavesOwned) {
			i += Main.game.getNPCById(id).getSlaveJob().getFinalDailyIncomeAfterModifiers(Main.game.getNPCById(id));
		}
		return i;
	}
	
	public int getSlaveryTotalDailyUpkeep() {
		int i=0;
		for(Cell c : SlaveryManagementDialogue.importantCells) {
			i += c.getPlace().getUpkeep();
		}
		return i;
	}
	
	public boolean addSlave(NPC slave) {
		boolean added = slavesOwned.add(slave.getId());
		
		if(added) {
			if(slave.isSlave()) {
				slave.getOwner().removeSlave(slave);
			}
			slave.setOwner(this);
		}
		
		return added;
	}
	
	public boolean removeSlave(GameCharacter slave) {
		boolean removed = slavesOwned.remove(slave.getId());
		
		if(removed) {
			slave.setOwner("");
		}
		
		return removed;
	}
	
	public void removeAllSlaves() {
		for(String id : slavesOwned) {
			if(Main.game.isCharacterExisting(id)) {
				Main.game.getNPCById(id).setOwner("");
			}
		}
		
		slavesOwned.clear();
	}
	
	public String getOwnerId() {
		return owner;
	}
	
	public GameCharacter getOwner() {
		if(owner==null || owner.isEmpty()) {
			return null;
		}
		return Main.game.getNPCById(owner);
	}

	/**<b>Do not call this method directly! Use the owner's addSlave() and removeSlave() methods!</b>*/
	protected void setOwner(GameCharacter owner) {
		this.owner = owner.getId();
	}

	/**<b>Do not call this method directly! Use the owner's addSlave() and removeSlave() methods!</b>*/
	protected void setOwner(String owner) {
		this.owner = owner;
	}
	
	public boolean isSlave() {
		return !getOwnerId().isEmpty();
	}
	
	public boolean isRelatedTo(GameCharacter character) {//TODO grandchildren, cousins, etc.
		// If this character is the character's child:
		if((!getMotherId().isEmpty() && getMotherId().equals(character.getId()))
				|| (!getFatherId().isEmpty() && getFatherId().equals(character.getId()))) {
			return true;
		}
		// If this character is the character's sibling:
		if((!getMotherId().isEmpty() && getMotherId().equals(character.getMotherId()))
				|| (!getFatherId().isEmpty() && getFatherId().equals(character.getFatherId()))) {
			return true;
		}
		
		return false;
	}
	
	public GameCharacter getMother() {
		if(motherId==null || motherId.isEmpty()) {
			return null;
		}
		return Main.game.getNPCById(motherId);
	}
	
	public String getMotherId() {
		return motherId;
	}
	
	public void setMother(String motherId) {
		this.motherId = motherId;
	}

	public void setMother(GameCharacter mother) {
		motherId = mother.getId();
	}

	public GameCharacter getFather() {
		if(fatherId==null || fatherId.isEmpty()) {
			return null;
		}
		return Main.game.getNPCById(fatherId);
	}

	public String getFatherId() {
		return fatherId;
	}
	
	public void setFather(String fatherId) {
		this.fatherId = fatherId;
	}
	
	public void setFather(GameCharacter father) {
		fatherId = father.getId();
	}

	public int getDayOfConception() {
		return dayOfConception;
	}

	public void setDayOfConception(int dayOfConception) {
		this.dayOfConception = dayOfConception;
	}

	public int getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(int dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public int getExperience() {
		return experience;
	}

	private int getExperienceNeededForNextLevel() {
		return getLevel() * 10;
	}
	
	/**
	 * Increments experience. 100 experience per current level needed to level
	 * up.
	 * 
	 * @param increment
	 *            Only applied if it is a positive value.
	 */
	public void incrementExperience(int increment) {
		if (increment < 0)
			throw new IllegalArgumentException("Cannot increment experience by a negative value!");

		if (getLevel() == 20) {
			experience = 0;
			return;
		}

		experience += increment;

		if (experience >= getExperienceNeededForNextLevel())
			levelUp();
	}

	private void levelUp() {
		// For handling health, mana and stamina changes as a result of an attribute being changed:
		float healthPercentage = getHealthPercentage();
		float manaPercentage = getManaPercentage();
		float staminaPercentage = getStaminaPercentage();
		
		while (experience >= getExperienceNeededForNextLevel() && getLevel() < 20) {
			experience -= getExperienceNeededForNextLevel();

			level++;

			levelUpPoints += 5;
			perkPoints++;
		}
		if (getLevel() == 20) {
			experience = 0;
		}
		
		// Increment health, mana and stamina based on the change:
		setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM) * healthPercentage);
		setMana(getAttributeValue(Attribute.MANA_MAXIMUM) * manaPercentage);
		setStamina(getAttributeValue(Attribute.STAMINA_MAXIMUM) * staminaPercentage);
		
		if(!isPlayer()) {
			for(int i=0; i<levelUpPoints; i++) {
				switch(Util.random.nextInt(2)) {
					case 0:
						incrementAttribute(Attribute.STRENGTH, 1);
						break;
					case 1:
						incrementAttribute(Attribute.INTELLIGENCE, 1);
						break;
					default:
						incrementAttribute(Attribute.FITNESS, 1);
						break;
				}
			}
		}
	}
	
	public int getLevelUpPoints() {
		return levelUpPoints;
	}

	public void setLevelUpPoints(int levelUpPoints) {
		this.levelUpPoints = levelUpPoints;
	}

	public int getPerkPoints() {
		return perkPoints;
	}

	public void setPerkPoints(int perkPoints) {
		this.perkPoints = perkPoints;
	}

	// Attributes:

	public CorruptionLevel getCorruptionLevel(){
		return CorruptionLevel.getCorruptionLevelFromValue(getAttributeValue(Attribute.CORRUPTION));
	}

	public float getBaseAttributeValue(Attribute attribute) {
		// Special case for health:
		if (attribute == Attribute.HEALTH_MAXIMUM) {
			return (getLevel() * 10) + getAttributeValue(Attribute.STRENGTH);
		}

		// Special case for mana:
		if (attribute == Attribute.MANA_MAXIMUM) {
			return (getLevel() * 10) + getAttributeValue(Attribute.INTELLIGENCE);
		}

		// Special case for stamina:
		if (attribute == Attribute.STAMINA_MAXIMUM) {
			return (getLevel() * 10) + getAttributeValue(Attribute.FITNESS);
		}
		
		return attributes.get(attribute);
	}

	public float getBonusAttributeValue(Attribute att) {
		float value = bonusAttributes.get(att);

		// special cases for the attributes that the core attributes influence:
		if (att == Attribute.DAMAGE_ATTACK)
			value += ((attributes.get(Attribute.STRENGTH) + bonusAttributes.get(Attribute.STRENGTH)) < 0 ? 0 : (attributes.get(Attribute.STRENGTH) + bonusAttributes.get(Attribute.STRENGTH)) / 2);
		else if (att == Attribute.DAMAGE_SPELLS)
			value += ((attributes.get(Attribute.INTELLIGENCE) + bonusAttributes.get(Attribute.INTELLIGENCE)) < 0 ? 0 : (attributes.get(Attribute.INTELLIGENCE) + bonusAttributes.get(Attribute.INTELLIGENCE)) / 2);
		else if (att == Attribute.DAMAGE_MANA)
			value += ((attributes.get(Attribute.FITNESS) + bonusAttributes.get(Attribute.FITNESS)) < 0 ? 0 : (attributes.get(Attribute.FITNESS) + bonusAttributes.get(Attribute.FITNESS)) / 2);
		
		return value;
	}

	public float getAttributeValue(Attribute att) {
		// Special case for health:
		if (att == Attribute.HEALTH_MAXIMUM) {
			float healthMax = (getLevel() * 10) + getAttributeValue(Attribute.STRENGTH) + bonusAttributes.get(Attribute.HEALTH_MAXIMUM);
			return (healthMax < 1 ? 1 : healthMax);
		}

		// Special case for mana:
		else if (att == Attribute.MANA_MAXIMUM) {
			float manaMax = (getLevel() * 10) + getAttributeValue(Attribute.INTELLIGENCE) + bonusAttributes.get(Attribute.MANA_MAXIMUM);
			return (manaMax < 1 ? 1 : manaMax);
		}

		// Special case for stamina:
		else if (att == Attribute.STAMINA_MAXIMUM) {
			float maxStamina = (getLevel() * 10) + getAttributeValue(Attribute.FITNESS) + bonusAttributes.get(Attribute.STAMINA_MAXIMUM);
			return (maxStamina < 1 ? 1 : maxStamina);
		}

		float value = attributes.get(att) + getBonusAttributeValue(att);

		// Core attribute values are bound between 0 and 100
		if (att == Attribute.STRENGTH || att == Attribute.INTELLIGENCE || att == Attribute.FITNESS || att == Attribute.CORRUPTION) {
			if (value < 0)
				value = 0;
			if (value > 100)
				value = 100;

			return value;
		}

		return value;
	}

	public String setAttribute(Attribute att, float value) {
		return setAttribute(att, value, true);
	}
	
	public String setAttribute(Attribute att, float value, boolean appendAttributeChangeText) {
		return incrementAttribute(att, value - attributes.get(att), appendAttributeChangeText);
	}

	public String incrementAttribute(Attribute att, float increment) {
		return incrementAttribute(att, increment, true);
	}
	
	public String incrementAttribute(Attribute att, float increment, boolean appendAttributeChangeText) {
		float value = attributes.get(att) + increment;

		// For handling health, mana and stamina changes as a result of an
		// attribute being changed:
		float healthPercentage = getHealthPercentage();
		float manaPercentage = getManaPercentage();
		float staminaPercentage = getStaminaPercentage();

		// Core attribute values are bound between 0 and 100
		if (att == Attribute.STRENGTH || att == Attribute.INTELLIGENCE || att == Attribute.FITNESS || att == Attribute.CORRUPTION) {
			if (value < 0)
				value = 0;
			if (value > 100)
				value = 100;
		}
		attributes.put(att, value);
		
		if(isPlayer() && appendAttributeChangeText) {
			Main.game.addEvent(new EventLogEntryAttributeChange(att, increment, true), !Main.game.isInSex());
		}

		// Increment health, mana and stamina based on the change:
		setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM) * healthPercentage);
		setMana(getAttributeValue(Attribute.MANA_MAXIMUM) * manaPercentage);
		setStamina(getAttributeValue(Attribute.STAMINA_MAXIMUM) * staminaPercentage);

		updateAttributeListeners();
		
		return att.getAttributeChangeText(this, increment);
	}


	public void incrementBonusAttribute(Attribute att, float increment) {
		float value = bonusAttributes.get(att) + increment;

		float healthPercentage = getHealthPercentage();
		float manaPercentage = getManaPercentage();
		float staminaPercentage = getStaminaPercentage();

		// Bonus attributes can be literally anything (well, maybe not past
		// Integer.MAX_VALUE or Integer.MIN_VALUE, but that'll never happen,
		// right?)
		bonusAttributes.put(att, value);

		setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM) * healthPercentage);
		setMana(getAttributeValue(Attribute.MANA_MAXIMUM) * manaPercentage);
		setStamina(getAttributeValue(Attribute.STAMINA_MAXIMUM) * staminaPercentage);

		updateAttributeListeners();
	}
	
	public Map<Attribute, Float> getPotionAttributes() {
		return potionAttributes;
	}
	
	public void setPotionAttributes(Map<Attribute, Float> attributeMap) {
		potionAttributes = attributeMap;
	}
	
	public void setPotionAttribute(Attribute att, float value) {
		potionAttributes.put(att, value);
	}
	
	public String addPotionEffect(Attribute att, float value) {
		Map<Attribute, Float> savedPotionEffects = new EnumMap<>(Attribute.class);
		savedPotionEffects.putAll(getPotionAttributes());
		
		int potionTimeRemaining = 0;
		
		if(this.hasStatusEffect(StatusEffect.POTION_EFFECTS)) {
			potionTimeRemaining = getStatusEffectDuration(StatusEffect.POTION_EFFECTS);
			removeStatusEffect(StatusEffect.POTION_EFFECTS);
		}
		
		setPotionAttributes(savedPotionEffects);
		
		if(potionAttributes.containsKey(att)) {
			setPotionAttribute(att, potionAttributes.get(att)+value);
		} else {
			setPotionAttribute(att, value);
		}
		
		if(potionAttributes.get(att)==0) {
			potionAttributes.remove(att);
		}
		
		potionTimeRemaining+=30;
		
		if(potionTimeRemaining>=6*60) {
			addStatusEffect(StatusEffect.POTION_EFFECTS, 6*60);
			
		} else if(potionTimeRemaining>60) {
			addStatusEffect(StatusEffect.POTION_EFFECTS, potionTimeRemaining);
			
		} else {
			addStatusEffect(StatusEffect.POTION_EFFECTS, 60);
			
		}
		
		if(isPlayer()) {
			if(potionAttributes.get(att)<0)
				return "You now have [style.boldBad("+potionAttributes.get(att)+")] <b style='color:"+att.getColour().toWebHexString()+";'>"+att.getName()+"</b> for as long as you can maintain your potion effects!";
			else
				return "You now have [style.boldGood(+"+potionAttributes.get(att)+")] <b style='color:"+att.getColour().toWebHexString()+";'>"+att.getName()+"</b> for as long as you can maintain your potion effects!";
		} else {
			if(potionAttributes.get(att)<0)
				return UtilText.parse(this,
						"[npc.Name] now has [style.boldBad("+potionAttributes.get(att)+")] <b style='color:"+att.getColour().toWebHexString()+";'>"+att.getName()+"</b> for as long as [npc.she] can maintain [npc.her] potion effects!");
			else
				return UtilText.parse(this,
						"[npc.Name] now has [style.boldGood(+"+potionAttributes.get(att)+")] <b style='color:"+att.getColour().toWebHexString()+";'>"+att.getName()+"</b> for as long as [npc.she] can maintain [npc.her] potion effects!");
		}
	}
	
	public void clearPotionAttributes() {
		potionAttributes.clear();
	}
	

	// Perks:

	/**The returned list is ordered by rendering priority.*/
	public List<Perk> getPerks() {
		List<Perk> tempPerkList = new ArrayList<>(perks);
		tempPerkList.sort(Comparator.comparingInt(Perk::getRenderingPriority));
		return tempPerkList;
	}

	/**The returned list is ordered by rendering priority.*/
	public List<Fetish> getFetishes() {
		List<Fetish> tempFetishList = new ArrayList<>(fetishes);
		tempFetishList.sort(Comparator.comparingInt(Fetish::getRenderingPriority));
		return tempFetishList;
	}
	
	public boolean hasFetish(Fetish f) {
		return fetishes.contains(f);
	}
	
	public boolean addFetish(Fetish fetish) {
		if (fetishes.contains(fetish)) {
			return false;
		}
		
		fetishes.add(fetish);

		// Increment bonus attributes from this fetish:
		if (fetish.getAttributeModifiers() != null) {
			for (Entry<Attribute, Integer> e : fetish.getAttributeModifiers().entrySet()) {
				incrementBonusAttribute(e.getKey(), e.getValue());
			}
		}
		
		calculateSpecialAttacks();
		
		updateAttributeListeners();
		
		calculateSpecialFetishes();

		return true;
	}

	public boolean removeFetish(Fetish fetish) {
		if (!fetishes.contains(fetish)) {
			return false;
		}
		
		fetishes.remove(fetish);

		// Reverse bonus attributes from this fetish:
		if (fetish.getAttributeModifiers() != null) {
			for (Entry<Attribute, Integer> e : fetish.getAttributeModifiers().entrySet()) {
				incrementBonusAttribute(e.getKey(), -e.getValue());
			}
		}
		
		calculateSpecialAttacks();
		
		updateAttributeListeners();

		calculateSpecialFetishes();
		
		return true;
	}
	
	private void calculateSpecialFetishes() {
		for(Fetish f : Fetish.values()) {
			if(!f.getFetishesForAutomaticUnlock().isEmpty()) {
				boolean conditionsMet = true;
				for(Fetish fetishNeeded : f.getFetishesForAutomaticUnlock()) {
					if(!hasFetish(fetishNeeded)) {
						conditionsMet = false;
						break;
					}
				}
				if(conditionsMet) {
					addFetish(f);
				} else {
					removeFetish(f);
				}
			}
		}
	}
	
	private Map<Fetish, FetishDesire> getFetishDesireMap() {
		return fetishDesireMap;
	}
	
	public boolean setFetishDesire(Fetish fetish, FetishDesire desire) {
		if(this.hasFetish(fetish) && desire != FetishDesire.FOUR_LOVE) {
			fetishDesireMap.put(fetish, FetishDesire.FOUR_LOVE);
			return false;
		}
		if(getFetishDesire(fetish)==desire) {
			return false;
		}
		
		fetishDesireMap.put(fetish, desire);
		
		return true;
	}
	
	public FetishDesire getFetishDesire(Fetish fetish) {
		if(hasFetish(fetish)) {
			return FetishDesire.FOUR_LOVE;
		}
		if(!fetishDesireMap.containsKey(fetish)) {
			return FetishDesire.TWO_NEUTRAL;
		}
		return fetishDesireMap.get(fetish);
	}
	
	private Map<Fetish, Integer> getFetishExperienceMap() {
		return fetishExperienceMap;
	}
	
	public boolean setFetishExperience(Fetish fetish, int experience) {
		fetishExperienceMap.put(fetish, Math.max(0, Math.min(experience, FetishLevel.FOUR_MASTERFUL.getMaximumExperience())));
		return true;
	}
	
	public boolean incrementFetishExperience(Fetish fetish, int experienceIncrement) {
		fetishExperienceMap.putIfAbsent(fetish, 0);
		return setFetishExperience(fetish, Math.max(0, fetishExperienceMap.get(fetish)+experienceIncrement));
	}
	
	public int getFetishExperience(Fetish fetish) {
		if(!fetishExperienceMap.containsKey(fetish)) {
			return 0;
		}
		return fetishExperienceMap.get(fetish);
	}
	
	public FetishLevel getFetishLevel(Fetish fetish) {
		return FetishLevel.getFetishLevelFromValue(getFetishExperience(fetish));
	}
	
	
	public boolean hasPerk(Perk p) {
		return perks.contains(p);
	}

	public boolean addPerk(Perk perk) {
		if (perks.contains(perk)) {
			return false;
		}
		
		perks.add(perk);

		// Increment bonus attributes from this perk:
		if (perk.getAttributeModifiers() != null)
			for (Entry<Attribute, Integer> e : perk.getAttributeModifiers().entrySet())
				incrementBonusAttribute(e.getKey(), e.getValue());

		calculateSpecialAttacks();
		
		updateAttributeListeners();

		return true;
	}

	public boolean removePerk(Perk perk) {
		if (!perks.contains(perk))
			return false;

		perks.remove(perk);

		// Reverse bonus attributes from this perk:
		if (perk.getAttributeModifiers() != null)
			for (Entry<Attribute, Integer> e : perk.getAttributeModifiers().entrySet())
				incrementBonusAttribute(e.getKey(), -e.getValue());

		calculateSpecialAttacks();
		
		updateAttributeListeners();

		return true;
	}
	

	// Status effects:

	public void calculateStatusEffects(int turnTime) {
		// Count down status effects:
		String s;
		List<StatusEffect> tempListStatusEffects = new ArrayList<>();
		for (StatusEffect se : getStatusEffects()) {
			if (!se.isCombatEffect()){
				s = se.applyEffect(this, turnTime);
				if(s.length()!=0)
					statusEffectDescriptions.put(se, s);
			}
			
			incrementStatusEffectDuration(se, -turnTime);
			
			if (statusEffects.get(se) < 0 && !se.isConditionsMet(this))
				tempListStatusEffects.add(se);
		}

		// Remove all status effects that are no longer applicable:
		for(StatusEffect se : tempListStatusEffects)
			removeStatusEffect(se);

		if (!Main.game.isInCombat())
			clearCombatStatusEffects();

		// Add all status effects that are applicable:
		for (StatusEffect se : StatusEffect.values())
			if (se.isConditionsMet(this))
				addStatusEffect(se, -1);

		updateAttributeListeners();

	}
	
	/**
	 * The returned list is ordered by rendering priority.
	 */

	public List<StatusEffect> getStatusEffects() {
		List<StatusEffect> tempListStatusEffects = new ArrayList<>(statusEffects.keySet());
		tempListStatusEffects.sort(Comparator.comparingInt(StatusEffect::getRenderingPriority));
		return tempListStatusEffects;
	}


	public boolean hasStatusEffect(StatusEffect se) {
		return statusEffects.containsKey(se);
	}


	public boolean addStatusEffect(StatusEffect statusEffect, int length) {
		if (hasStatusEffect(statusEffect)){
			// refresh the effect
			statusEffects.put(statusEffect, length);
			return false;
		}
		
		statusEffects.put(statusEffect, length);

		// Increment bonus attributes from this StatusEffect:
		if (statusEffect.getAttributeModifiers(this) != null)
			for (Entry<Attribute, Float> e : statusEffect.getAttributeModifiers(this).entrySet())
				incrementBonusAttribute(e.getKey(), e.getValue());
		
		// Apply effect:
		String s = statusEffect.applyEffect(this, 0);
		if (!statusEffect.isCombatEffect()){
			if(s.length()!=0)
				statusEffectDescriptions.put(statusEffect, s);
		}
		
		updateAttributeListeners();

		return true;
	}


	public boolean removeStatusEffect(StatusEffect se) {
		if (!statusEffects.containsKey(se))
			return false;

		String s = se.applyRemoveStatusEffect(this);
		if (!se.isCombatEffect()){
			if(s.length()!=0)
				statusEffectDescriptions.put(se, s);
		}
		
		statusEffects.remove(se);

		updateAttributeListeners();

		return true;
	}


	public int getStatusEffectDuration(StatusEffect se) {
		return statusEffects.get(se);
	}


	public boolean setStatusEffectDuration(StatusEffect se, int length) {
		if (!statusEffects.containsKey(se))
			return false;

		statusEffects.put(se, length);

		return true;
	}


	public boolean incrementStatusEffectDuration(StatusEffect se, int increment) {
		if (!statusEffects.containsKey(se) || statusEffects.get(se) == -1)
			return false;

//		if (statusEffects.get(se) + increment < 0) // Remove the status effect
//													// if it ticked past 0:
//			removeStatusEffect(se);
//		else // Otherwise, increment the status effect:
			
		statusEffects.put(se, statusEffects.get(se) + increment);

		return true;
	}


	public Map<StatusEffect, String> getStatusEffectDescriptions() {
		return statusEffectDescriptions;
	}


	public void clearCombatStatusEffects() {
		List<StatusEffect> removalList = new ArrayList<>();
		for (StatusEffect se : statusEffects.keySet()) {
			if (se.isCombatEffect())
				removalList.add(se);
		}
		for (StatusEffect se : removalList)
			removeStatusEffect(se);
	}

	
	// Stats:

	public Map<SexType, Integer> getSexPartnerStats(GameCharacter c) {
		return sexPartnerMap.get(c.getId());
	}
	
	public Map<String, Map<SexType, Integer>> getSexPartners() {
		return sexPartnerMap;
	}
	
	public void addSexPartner(GameCharacter partner, SexType sexType) {
		this.sexPartnerMap.computeIfAbsent(partner.getId(), k -> new HashMap<>());
		
		Map <SexType, Integer> sp = this.sexPartnerMap.get(partner.getId());
		sp.putIfAbsent(sexType, 0);
		
		sp.put(sexType, sp.get(sexType) + 1);
	}


	public int getFoughtPlayerCount() {
		return foughtPlayerCount;
	}
	public void setFoughtPlayerCount(int count) {
		foughtPlayerCount = count;
	}

	public int getLostCombatCount() {
		return lostCombatCount;
	}
	public void setLostCombatCount(int count) {
		lostCombatCount = count;
	}

	public int getWonCombatCount() {
		return wonCombatCount;
	}
	public void setWonCombatCount(int count) {
		wonCombatCount = count;
	}

	
	// Sex stats:
	public int getSexConsensualCount() {
		return sexConsensualCount;
	}
	public void setSexConsensualCount(int count) {
		sexConsensualCount = count;
	}

	public int getSexAsSubCount() {
		return sexAsSubCount;
	}
	public void setSexAsSubCount(int count) {
		sexAsSubCount = count;
	}

	public int getSexAsDomCount() {
		return sexAsDomCount;
	}
	public void setSexAsDomCount(int count) {
		sexAsDomCount = count;
	}

	public int getTotalTimesHadSex() {
		return getSexAsDomCount() + getSexAsSubCount();
	}
	
	// Sex:

	public String getLostVirginityDescriptor() {
		return this.getLocationPlace().getPlaceType().getVirgintyLossDescription();
	}
	
	public void incrementSexCount(SexType sexType) {
		sexCountMap.merge(sexType, 1, (a, b) -> a + b);
	}
	public void setSexCount(SexType sexType, int integer) {
		sexCountMap.put(sexType, integer);
	}
	public int getSexCount(SexType sexType) {
		sexCountMap.putIfAbsent(sexType, 0);
		return sexCountMap.get(sexType);
	}

	// Cum:
	public void incrementCumCount(SexType sexType) {
		cumCountMap.merge(sexType, 1, (a, b) -> a + b);
	}
	public void setCumCount(SexType sexType, int integer) {
		cumCountMap.put(sexType, integer);
	}
	public int getCumCount(SexType sexType) {
		cumCountMap.putIfAbsent(sexType, 0);
		return cumCountMap.get(sexType);
	}

	// Virginity:
	public void setVirginityLoss(SexType sexType, String description) {
		virginityLossMap.put(sexType, description);
	}
	
	public String getVirginityLoss(SexType sexType) {
		return virginityLossMap.get(sexType);
	}
	
	// ****************** Sex & Dirty talk: ***************************
	
	public String getCondomEquipEffects(GameCharacter equipper, GameCharacter target, boolean rough) {
		if(Main.game.isInSex() && !target.isPlayer()) {
			if(Sex.isDom(Main.game.getPlayer()) ||Sex.isConsensual()) {
				return "<p>"
						+ "Holding out a condom to [npc.name], you force [npc.herHim] to take it and put it on."
						+ " Quickly ripping it out of its little foil wrapper, [npc.she] rolls it down the length of [npc.her] [npc.cock+] as [npc.she] whines at you,"
						+ " [npc.speech(Do I really have to? It feels so much better without one...)]"
						+ "</p>";
			} else {
				return "<p>"
						+ "Holding out a condom to [npc.name], you let out a sigh of relief as [npc.she] reluctantly takes it."
						+ " Quickly ripping it out of its little foil wrapper, [npc.she] rolls it down the length of [npc.her] [npc.cock+] as [npc.she] growls at you,"
						+ " [npc.speech(You'd better be glad that I'm in a good mood!)]"
						+ "</p>";
			}
		}
		return AbstractClothingType.getEquipDescriptions(target, equipper, rough,
				"You tear open the packet and roll the condom down the length of your [pc.penis].",
				"You tear open the packet and roll the condom down the length of [npc.name]'s [npc.penis].",
				"You tear open the packet and forcefully roll the condom down the length [npc.name]'s [npc.penis].",
				"[npc.Name] tears open the packet and rolls the condom down the length of [npc.her] [npc.penis].",
				"[npc.Name] tears open the packet and rolls the condom down the length of your [pc.penis].",
				"[npc.Name] tears open the packet and forcefully rolls the condom down the length of your [pc.penis].");
	}
	
	/**
	 * @return A <b>formatted</b> piece of speech, reacting to any current penetration.
	 */
	public String getDirtyTalk() {
		if(!Main.game.isInSex()) {
			return "";
		
		} else {
			boolean isPlayerDom = Sex.isDom(Main.game.getPlayer());
			List<String> speech = new ArrayList<>(), speechSelf = new ArrayList<>();
			String s = "";
			
			for(OrificeType orifice : OrificeType.values()) {
				switch(orifice) {
					case ANUS:
						s = getDirtyTalkAssPenetrated(Sex.getTargetedPartner(this), isPlayerDom);
						break;
					case ASS:
						s = null;
						break;
					case BREAST:
						s = getDirtyTalkBreastsPenetrated(Sex.getTargetedPartner(this), isPlayerDom);
						break;
					case MOUTH:
						s = getDirtyTalkMouthPenetrated(Sex.getTargetedPartner(this), isPlayerDom);
						break;
					case NIPPLE:
						s = getDirtyTalkNipplePenetrated(Sex.getTargetedPartner(this), isPlayerDom);
						break;
					case THIGHS:
						s = null;
						break;
					case URETHRA:
						s = null;
						break;
					case VAGINA:
						s = getDirtyTalkVaginaPenetrated(Sex.getTargetedPartner(this), isPlayerDom);
						break;
				}
				if(s!=null) {
					if(!Sex.getPenetratingCharacterUsingOrifice(this, orifice).equals(this)) {
						speech.add(s);
					} else {
						speechSelf.add(s);
					}
				}
			}
		
			for(PenetrationType penetration : PenetrationType.values()) {
				switch(penetration) {
					case FINGER:
						s = getDirtyTalkFingerPenetrating(Sex.getTargetedPartner(this), isPlayerDom);
						break;
					case PENIS:
						s = getDirtyTalkPenisPenetrating(Sex.getTargetedPartner(this), isPlayerDom);
						break;
					case TAIL:
						s = getDirtyTalkTailPenetrating(Sex.getTargetedPartner(this), isPlayerDom);
						break;
					case TENTACLE:
						s = null;
						break;
					case TONGUE:
						s = getDirtyTalkTonguePenetrating(Sex.getTargetedPartner(this), isPlayerDom);
						break;
				}
				if(s!=null) {
					if(!Sex.getCharactersBeingPenetratedBy(this, penetration).contains(this) || Sex.getCharactersBeingPenetratedBy(this, penetration).size()>1) {
						speech.add(s);
					} else {
						speechSelf.add(s);
					}
				}
			}
			
			
			// Choose a random line to say:
			if(!speech.isEmpty()){
				s = speech.get(Util.random.nextInt(speech.size())); // Prefer non-self penetrative speech.
				
			} else if(!speechSelf.isEmpty()){
				s = speechSelf.get(Util.random.nextInt(speechSelf.size()));
				
			} else {
				s = getDirtyTalkNoPenetration(Sex.getTargetedPartner(this), isPlayerDom);
			}
			
			return UtilText.parseSpeech(s, this);
		}
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this NPCs speech related to no ongoing penetration.
	 */
	public String getDirtyTalkNoPenetration(GameCharacter target, boolean isPlayerDom){
		String returnedLine = "";
		
		switch(Sex.getSexPace(this)) {
			case DOM_GENTLE:
				returnedLine = UtilText.returnStringAtRandom(
						"I'll be gentle, don't worry!",
						"You're going to be a good [npc2.girl] now, aren't you?",
						"Let's have some fun!",
						"You're going to love this!");
				break;
			case DOM_NORMAL:
				returnedLine = UtilText.returnStringAtRandom(
						"This is going to be good!",
						"How best to use you, I wonder...",
						"You're going to be a good [npc2.girl]!",
						"Ready for some fun?");
				break;
			case DOM_ROUGH:
				returnedLine = UtilText.returnStringAtRandom(
						"You ready to get fucked, slut?",
						"I'm going to fuck you senseless!",
						"You're my bitch now, understand?!",
						"I'm going to use you however I want, you fucking slut!");
				break;
			case SUB_EAGER:
				if(this.isVaginaVirgin() && this.hasVagina()) {
					returnedLine = UtilText.returnStringAtRandom(
							"Come on, fuck me already! Take my virginity!",
							"I'm still a virgin! Please, break me in already!",
							"What are you waiting for?! Fuck my virgin pussy already!",
							"I'm so horny! Please, fuck my pussy! Take my virginity!");
				} else {
					returnedLine = UtilText.returnStringAtRandom(
							"Come on, fuck me already! Please!",
							"Fuck me! Please!",
							"What are you waiting for?! Come on, fuck me!",
							"I'm so horny! Please, fuck me!");
				}
				break;
			case SUB_NORMAL:
				if(this.isVaginaVirgin() && this.hasVagina()) {
					returnedLine = UtilText.returnStringAtRandom(
							"I'll be a good [npc1.girl]! Just... I'm still a virgin, ok?",
							"I'll do whatever you want! I'm still a virgin though...",
							"Let's get started! But... I'm still a virgin...",
							"Let's have some fun! But... I'm still a virgin, ok?");
				} else {
					returnedLine = UtilText.returnStringAtRandom(
							"I'll be a good [npc.girl]!",
							"I'll do whatever you want!",
							"Let's get started!",
							"Let's have some fun!");
				}
				break;
			case SUB_RESISTING:
				if(this.isVaginaVirgin() && this.hasVagina()) {
					returnedLine = UtilText.returnStringAtRandom(
							"Go away! I-I'm still a virgin! Leave me alone!",
							"Stop it! Just go away! I-I'm still a virgin!",
							"Please stop! I don't want to lose my virginity!",
							"Don't do this! I'm still a virgin!");
				} else {
					returnedLine = UtilText.returnStringAtRandom(
							"Go away! Leave me alone!",
							"Stop it! Just go away!",
							"Please stop! Don't do this!");
				}
				break;
			default:
				returnedLine = UtilText.returnStringAtRandom(
						"This is going to be good!",
						"Time for some fun!",
						"Let's get started!",
						"Let's have some fun!");
		}
		
		if(returnedLine.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, returnedLine);
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to having their vagina used. Returns null if no vagina or penetration found.
	 */
	public String getDirtyTalkVaginaPenetrated(GameCharacter target, boolean isPlayerDom){
		String returnedLine = "";
		
		if(getVaginaType()!=VaginaType.NONE) {
			if(Sex.getPenetrationTypeInOrifice(this, OrificeType.VAGINA) != null) {
				switch(Sex.getPenetrationTypeInOrifice(this, OrificeType.VAGINA)) {
					case FINGER:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								returnedLine = UtilText.returnStringAtRandom(
										"That's right, be a good [npc2.girl] now and push your [npc2.fingers] in deeper!",
										"Good [npc2.girl]! Keep those [npc2.fingers] of yours busy!",
										"What a good [npc2.girl]! My pussy loves the feeling of your [npc2.fingers]!");
								break;
							case DOM_NORMAL:
								returnedLine = UtilText.returnStringAtRandom(
										"That's right, push your [npc2.fingers] in deep!",
										"Good [npc2.girl]! Get those [npc2.fingers] in deep!",
										"Keep going! Curl your [npc2.fingers] up a bit!");
								break;
							case DOM_ROUGH:
								returnedLine = UtilText.returnStringAtRandom(
										"Come on slut, you can get your [npc2.fingers] in deeper than that!",
										"Keep it up bitch! Get those [npc2.fingers] in deep!",
										"Keep going slut! Curl your [npc2.fingers] up and put in a little more effort!");
								break;
							case SUB_EAGER:
								returnedLine = UtilText.returnStringAtRandom(
										"Yes! Keep fingering me!",
										"Keep going! My pussy loves your attention!",
										"Oh yes! I love being fingered!");
								break;
							case SUB_NORMAL:
								returnedLine = UtilText.returnStringAtRandom(
										"Keep fingering me!",
										"Keep going! I love this!",
										"Oh yes!");
								break;
							case SUB_RESISTING:
								returnedLine = UtilText.returnStringAtRandom(
										"Get your [npc2.fingers] out of me! Stop! Please!",
										"Stop fingering me! Please, no more!",
										"Stop it! Stop! Please!");
								break;
							default:
								returnedLine = UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!");
								break;
						}
						break;
					case PENIS:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								returnedLine = UtilText.returnStringAtRandom(
										"My pussy loves your [npc2.cock]!",
										"Good [npc2.girl]! Keep sliding that delicious cock of yours in and out of me!",
										"What a good [npc2.girl]! Enjoy my pussy as your reward now!");
								break;
							case DOM_NORMAL:
								returnedLine = UtilText.returnStringAtRandom(
										"You like feeling my pussy gripping down on your cock?!",
										"Good [npc2.girl]! Push your [npc2.cock] in deep!",
										"Keep going! Get that [npc2.cock] in deep like a good [npc2.girl]!");
								break;
							case DOM_ROUGH:
								returnedLine = UtilText.returnStringAtRandom(
										"That's right slut, you're my little fuck toy now!",
										"Come on bitch! You can get your worthless [npc2.cock] in deeper than that!",
										"Fucking slut, put some more effort in! My pussy deserves better than your worthless [npc2.cock]!");
								break;
							case SUB_EAGER:
								returnedLine = UtilText.returnStringAtRandom(
										"Yes! Fuck me! Fuck me harder! Don't stop!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Fuck me! I love your [npc2.cock]!");
								break;
							case SUB_NORMAL:
								returnedLine = UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!");
								break;
							case SUB_RESISTING:
								returnedLine = UtilText.returnStringAtRandom(
										"Get out of me! Stop! Please!",
										"Please, no more! Take your cock out!",
										"Get out of me! Stop! Please!");
								break;
							default:
								returnedLine = UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!");
								break;
						}
						break;
					case TAIL:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								returnedLine = UtilText.returnStringAtRandom(
										"My pussy loves your [npc2.tail]! Keep going!",
										"Good [npc2.girl]! Keep fucking me with that [npc2.tail] of yours!",
										"What a good [npc2.girl]! Enjoy my pussy as your reward now!");
								break;
							case DOM_NORMAL:
								returnedLine = UtilText.returnStringAtRandom(
										"Oh yes! Your [npc2.tail] feels so good!",
										"Good [npc2.girl]! Push your [npc2.tail] in deep!",
										"Keep going! Get that [npc2.tail] in deep like a good [npc2.girl]!");
								break;
							case DOM_ROUGH:
								returnedLine = UtilText.returnStringAtRandom(
										"That's right slut, get that [npc2.tail] in deep like a good little fuck toy!",
										"Come on bitch! You can get your [npc2.tail] in deeper than that!",
										"Fucking bitch, put some more effort in! My pussy deserves better than some slut's [npc2.tail]!");
								break;
							case SUB_EAGER:
								returnedLine = UtilText.returnStringAtRandom(
										"Yes! Fuck me! Fuck me harder! Don't stop!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Fuck me! I love your [npc2.tail]! Get it deeper!");
								break;
							case SUB_NORMAL:
								returnedLine = UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!");
								break;
							case SUB_RESISTING:
								returnedLine = UtilText.returnStringAtRandom(
										"Get out of me! Stop! Please!",
										"Please, no more! Take your tail out!",
										"Get out of me! Stop! Please!");
								break;
							default:
								returnedLine = UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!");
								break;
						}
						break;
					case TONGUE:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								returnedLine = UtilText.returnStringAtRandom(
										"That's right, keep eating me out!",
										"Good [npc2.girl]! Keep that [npc2.tongue] of yours busy!",
										"What a good [npc2.girl]! You love the taste of my pussy, don't you?!");
								break;
							case DOM_NORMAL:
								returnedLine = UtilText.returnStringAtRandom(
										"Oh yes! Get that [npc2.tongue] in deep!",
										"Good [npc2.girl]! Get that [npc2.tongue] of yours in deep!",
										"Keep going! My pussy loves your [npc2.tongue]!");
								break;
							case DOM_ROUGH:
								returnedLine = UtilText.returnStringAtRandom(
										"That's right slut, keep eating me out like the worthless little fuck toy you are!",
										"Come on bitch! Get that [npc2.tongue] of yours in deeper!",
										"Fucking bitch, put some more effort in! You know how lucky you are, being allowed to taste my pussy like this?!");
								break;
							case SUB_EAGER:
								returnedLine = UtilText.returnStringAtRandom(
										"Yes! I love your [npc2.tongue]! Don't stop!",
										"Don't stop! Deeper! Eat me out! Yes, yes, yes!",
										"Oh yes! Taste my pussy! I love your [npc2.tongue]! Get it deeper!");
								break;
							case SUB_NORMAL:
								returnedLine = UtilText.returnStringAtRandom(
										"Yes! Don't stop!",
										"Don't stop! I love your [npc2.tongue]!",
										"Oh yes! Eat me out!");
								break;
							case SUB_RESISTING:
								returnedLine = UtilText.returnStringAtRandom(
										"Get out of me! Stop! Please!",
										"Please, no more! Take your tongue out!",
										"Get out of me! Stop! Please!");
								break;
							default:
								returnedLine = UtilText.returnStringAtRandom(
										"Yes! Get that tongue deeper!",
										"Oh yeah! Keep going!",
										"Deeper! Don't stop!");
						}
						break;
					default:// Self penetration:
						switch(Sex.getSexPace(this)) {
							case SUB_RESISTING:
								returnedLine = UtilText.returnStringAtRandom(
										"Go away! Leave me alone!",
										"Stop it! Just go away!",
										"Please stop! Don't do this!");
								break;
							default:
								returnedLine =  UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!");
								break;
						}
				}
			}
		}

		if(returnedLine.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, returnedLine);
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to having their asshole used. Returns null if no penetration found.
	 */
	public String getDirtyTalkAssPenetrated(GameCharacter target,  boolean isPlayerDom){
		String returnedLine = "";
		
		if(Sex.getPenetrationTypeInOrifice(this, OrificeType.ANUS) != null) {
			switch(Sex.getPenetrationTypeInOrifice(this, OrificeType.ANUS)) {
				case FINGER:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right, be a good [npc2.girl] now and push your [npc2.fingers] in deeper!",
									"Good [npc2.girl]! Keep those [npc2.fingers] of yours busy!",
									"What a good [npc2.girl]! Keep fingering my ass!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right, push your [npc2.fingers] in deep!",
									"Good [npc2.girl]! Get those [npc2.fingers] in deep!",
									"Keep going! My ass loves the attention!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"Come on slut, you can get your [npc2.fingers] in deeper than that!",
									"Keep it up bitch! Get those [npc2.fingers] in deep!",
									"Keep going slut! Get your [npc2.fingers] in deep and put in a little more effort!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Keep fingering my ass! Don't stop!",
									"Keep going! My ass loves the attention!",
									"Oh yes! My ass loves being fingered!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Keep fingering my ass!",
									"Keep going! I love this!",
									"Oh yes!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop fingering my ass! Stop! Please!",
									"Please, no more! Take your fingers out of my ass!",
									"Get out of my ass! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck!",
									"Yeah!",
									"Oh yeah!");
							break;
					}
					break;
				case PENIS:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"My ass loves your [npc2.cock]!",
									"Good [npc2.girl]! Keep sliding that delicious cock of yours in and out of my ass!",
									"What a good [npc2.girl]! Enjoy my ass as your reward now!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"You like feeling my slutty little asshole gripping down on your cock?!",
									"Good [npc2.girl]! Push your [npc2.cock] in deep!",
									"Keep going! Get that [npc2.cock] in deep like a good [npc2.girl]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right slut, you're my little fuck toy now!",
									"Come on bitch! You can get your worthless [npc2.cock] in deeper than that!",
									"Fucking slut, put some more effort in! My ass deserves better than your worthless [npc2.cock]!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck me! Fuck me harder! Don't stop!",
									"Don't stop! Harder! Fuck me! Yes, yes, yes!",
									"Oh yes! Fuck me! I love your [npc2.cock]!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck me!",
									"Don't stop! Fuck me!",
									"Oh yes! Fuck me!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your cock out of my ass!",
									"Get out of my ass! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck me! Yes! Harder!",
									"Oh yeah! Fuck me!",
									"Harder! Don't stop!");
							break;
					}
					break;
				case TAIL:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"My ass loves your [npc2.tail]! Keep going!",
									"Good [npc2.girl]! Keep fucking my ass with that [npc2.tail] of yours!",
									"What a good [npc2.girl]! Enjoy my ass as your reward now!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Oh yes! Your [npc2.tail] feels so good!",
									"Good [npc2.girl]! Push your [npc2.tail] in deep!",
									"Keep going! Get that [npc2.tail] in deep like a good [npc2.girl]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right slut, get that [npc2.tail] in deep like a good little fuck toy!",
									"Come on bitch! You can get your [npc2.tail] in deeper than that!",
									"Fucking bitch, put some more effort in! My ass deserves better than some slut's [npc2.tail]!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck me! Fuck me harder! Don't stop!",
									"Don't stop! Harder! Fuck me! Yes, yes, yes!",
									"Oh yes! Fuck me! I love your [npc2.tail]! Get it deeper!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck me!",
									"Don't stop! Fuck me!",
									"Oh yes! Fuck me!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your tail out of my ass!",
									"Get out of my ass! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck me! Yes! Harder!",
									"Oh yeah! Fuck me!",
									"Harder! Don't stop!");
							break;
					}
					break;
				case TONGUE:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right, keep that rimjob going like a good [npc2.girl]!",
									"Good [npc2.girl]! Keep that [npc2.tongue] of yours busy in my ass!",
									"What a good [npc2.girl]! You love the taste of my ass, don't you?!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Oh yes! Get that [npc2.tongue] in deep!",
									"Good [npc2.girl]! Get that [npc2.tongue] of yours in deep!",
									"Keep going! My ass loves your [npc2.tongue]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right slut, keep that rimjob going like the worthless little fuck toy you are!",
									"Come on bitch! Get that [npc2.tongue] of yours deeper into my ass!",
									"Fucking bitch, put some more effort in! You know how lucky you are, being allowed to lick my ass like this?!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! I love your [npc2.tongue]! Don't stop!",
									"Don't stop! Deeper! Get your [npc2.tongue] into my ass! Yes, yes, yes!",
									"Oh yes! Taste my ass! I love your [npc2.tongue]! Get it deeper!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Don't stop!",
									"Don't stop! I love your [npc2.tongue]!",
									"Oh yes! Taste my ass!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your tongue out of my ass!",
									"Get your tongue out of my ass! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Get that tongue deeper!",
									"Oh yeah! Keep going!",
									"Deeper! Don't stop!");
							break;
					}
					break;
				default:// Self penetration:
					switch(Sex.getSexPace(this)) {
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Go away! Leave me alone!",
									"Stop it! Just go away!",
									"Please stop! Don't do this!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck!",
									"Yeah!",
									"Oh yeah!");
							break;
					}
			}
		}

		if(returnedLine.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, returnedLine);
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to having their mouth used. Returns null if no penetration found.
	 */
	public String getDirtyTalkMouthPenetrated(GameCharacter target, boolean isPlayerDom){
		String returnedLine = "";

		if(Sex.getPenetrationTypeInOrifice(this, OrificeType.MOUTH) != null) {
			switch(Sex.getPenetrationTypeInOrifice(this, OrificeType.MOUTH)) {
				case FINGER:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"Your fingers taste good!",
									"Good [npc2.girl]! Your [npc2.fingers] taste so good!",
									"What a good [npc2.girl]!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Your fingers taste good!",
									"Good [npc2.girl]! Your [npc2.fingers] taste so good!",
									"What a good [npc2.girl]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"Fucking slut, let's get your [npc2.fingers] all nice and wet now!",
									"That's right bitch! Let's get your [npc2.fingers] nice and wet!",
									"Hold still slut! I need your [npc2.fingers] all nice and lubed up!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"I love the taste of your [npc2.fingers]! Don't stop!",
									"Keep going! I love sucking on your [npc2.fingers]!",
									"Oh yes! I love the taste of your [npc2.fingers]!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"I love the taste of your [npc2.fingers]!",
									"I love sucking on your [npc2.fingers]!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your fingers out of my mouth!",
									"Get out of my mouth! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck!",
									"Yeah!",
									"Oh yeah!");
							break;
					}
					break;
				case PENIS:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"I love your [npc2.cock]!",
									"Good [npc2.girl]! Your delicious cock deserves this nice reward!",
									"What a good [npc2.girl]! I hope you're enjoying your reward!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Your cock tastes so good!",
									"I love sucking your [npc2.cock]!",
									"Oh yes! Your [npc2.cock] tastes so good!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"Hold still slut, be a good little fuck toy and just be thankful that I love sucking cock!",
									"Stay still bitch! You'd better be happy that your worthless [npc2.cock] is the only thing for me to suck right now!",
									"Fucking slut, hold still! I need to practice my oral skills on your worthless [npc2.cock]!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Oh yes! I love your [npc2.cock]! I need it! Use my throat!",
									"Don't stop! Harder! Fuck my throat! Yes, yes, yes!",
									"Oh yes! I love your [npc2.cock]! You taste so good!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"I love your [npc2.cock]! Use my throat!",
									"Fuck my throat! Oh yes!",
									"I love your [npc2.cock]! You taste so good!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your cock out of my mouth!",
									"Get your cock away from me! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"I love sucking cock!",
									"Oh yeah! I love your cock!",
									"Don't stop!");
							break;
					}
					break;
				case TAIL:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"I love sucking your [npc2.tail]!",
									"Good [npc2.girl]! Get this delicious [npc2.tail] of yours deep down my throat!",
									"What a good [npc2.girl]! I hope you're enjoying your reward!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"I love sucking your [npc2.tail]!",
									"Good [npc2.girl]! Get this delicious [npc2.tail] of yours deep down my throat!",
									"What a good [npc2.girl]! I hope you're enjoying your reward!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"Hold still slut, be a good little fuck toy and just be thankful that I decided to practice oral on your [npc2.tail]!",
									"Stay still bitch! You'd better be happy that I decided to practice my cock-sucking on your [npc2.tail]!",
									"Fucking slut, hold still! I need to practice my oral skills on your [npc2.tail]!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Oh yes! I love your [npc2.tail]! I need it! Use my throat!",
									"Don't stop! Harder! Fuck my throat! Yes, yes, yes!",
									"Oh yes! I love your [npc2.tail]! You taste so good!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"I love your [npc2.tail]! Use my throat!",
									"Fuck my throat! Oh yes!",
									"I love your [npc2.tail]! You taste so good!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your tail out of my mouth!",
									"Get your tail away from me! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"I love sucking your [npc2.tail]!",
									"Oh yeah! I love your [npc2.tail]!",
									"Don't stop!");
							break;
					}
					break;
				case TONGUE:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Good [npc2.girl]!",
									"Good [npc2.girl]! I love your [npc2.lips]!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Good [npc2.girl]!",
									"Good [npc2.girl]! I love your [npc2.lips]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Good bitch!",
									"Good slut! I love your [npc2.lips]!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Good [npc2.girl]!",
									"Good [npc2.girl]! I love your [npc2.lips]!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Good [npc2.girl]!",
									"Good [npc2.girl]! I love your [npc2.lips]!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Stop kissing me like this!",
									"Get away from me! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Good [npc2.girl]!",
									"Good [npc2.girl]! I love your [npc2.lips]!",
									"Don't stop!");
							break;
					}
					break;
				default:// Self penetration:
					switch(Sex.getSexPace(this)) {
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Go away! Leave me alone!",
									"Stop it! Just go away!",
									"Please stop! Don't do this!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck!",
									"Yeah!",
									"Oh yeah!");
							break;
					}
			}
		}

		if(returnedLine.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, returnedLine);
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to having their nipples used. Returns null if no penetration found.
	 */
	public String getDirtyTalkNipplePenetrated(GameCharacter target, boolean isPlayerDom){
		String returnedLine = "";
		
		if(Sex.getPenetrationTypeInOrifice(this, OrificeType.NIPPLE) != null) {
			switch(Sex.getPenetrationTypeInOrifice(this, OrificeType.NIPPLE)) {
				case FINGER:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right, be a good [npc2.girl] now and push your [npc2.fingers] deeper into my nipple!",
									"Good [npc2.girl]! Keep those [npc2.fingers] of yours busy in my breast!",
									"What a good [npc2.girl]! My nipples love the feeling of your [npc2.fingers]!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right, push your [npc2.fingers] deep into my breast!",
									"Good [npc2.girl]! Get those [npc2.fingers] deep into my nipple!",
									"Keep going! Curl your [npc2.fingers] up a bit!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"Come on slut, you can get your [npc2.fingers] deeper into my breast than that!",
									"Keep it up bitch! Get those [npc2.fingers] deep into my nipple!",
									"Keep going slut! Curl your [npc2.fingers] up and put in a little more effort!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Keep fingering my nipple!",
									"Keep going! My nipples love your attention!",
									"Oh yes! I love getting my nipples fingered!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Keep fingering my nipple!",
									"Keep going! I love this!",
									"Oh yes!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your fingers out of my nipple!",
									"Get out of my nipple! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck!",
									"Yeah!",
									"Oh yeah!");
							break;
					}
					break;
				case PENIS:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"My nipples love your [npc2.cock]!",
									"Good [npc2.girl]! Keep sliding that delicious cock of yours in and out of my tits!",
									"What a good [npc2.girl]! Enjoy my tits as your reward now!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"You like feeling my nipple gripping down on your cock?!",
									"Good [npc2.girl]! Push your [npc2.cock] deep into my breast!",
									"Keep going! Get that [npc2.cock] in deep like a good [npc2.girl]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right slut, my tits could use some attention from a little fuck toy like you!",
									"Come on bitch! You can get your worthless [npc2.cock] deeper into my tits than that!",
									"Fucking slut, put some more effort in! My breasts deserve better than your worthless [npc2.cock]!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck my tits! Fuck them harder! Don't stop!",
									"Don't stop! Harder! Fuck my nipples! Yes, yes, yes!",
									"Oh yes! Fuck my tits! I love your [npc2.cock]!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck my tits!",
									"Don't stop! Fuck my nipples!",
									"Oh yes! Fuck me!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your cock out of my breast!",
									"Get out of my nipple! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck me! Yes! Harder!",
									"Oh yeah! Fuck me!",
									"Harder! Don't stop!");
							break;
					}
					break;
				case TAIL:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"My tits love your [npc2.tail]! Keep going!",
									"Good [npc2.girl]! Keep fucking my nipples with that [npc2.tail] of yours!",
									"What a good [npc2.girl]! Enjoy my tits as your reward now!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Oh yes! Your [npc2.tail] feels so good!",
									"Good [npc2.girl]! Push your [npc2.tail] deep into my breast!",
									"Keep going! Get that [npc2.tail] deep into my tits like a good [npc2.girl]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right slut, get that [npc2.tail] deep into my nipple like a good little fuck toy!",
									"Come on bitch! You can get your [npc2.tail] deeper into my tits than that!",
									"Fucking bitch, put some more effort in! My breasts deserve better than some slut's [npc2.tail]!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck my tits! Fuck them harder! Don't stop!",
									"Don't stop! Harder! Fuck my nipples! Yes, yes, yes!",
									"Oh yes! Fuck my tits! I love your [npc2.tail]!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck my tits!",
									"Don't stop! Fuck my nipples!",
									"Oh yes! Fuck me!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your tail out of my nipple!",
									"Get out of my nipple! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck me! Yes! Harder!",
									"Oh yeah! Fuck me!",
									"Harder! Don't stop!");
							break;
					}
					break;
				case TONGUE:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right, keep sucking on my nipples like a good [npc2.girl]!",
									"Good [npc2.girl]! Keep that [npc2.tongue] of yours busy!",
									"What a good [npc2.girl]! You love my tits, don't you?!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Oh yes! Keep sucking on my nipples!",
									"Good [npc2.girl]! Get that [npc2.tongue] of yours deep into my nipples!",
									"Keep going! My tits love the feel of your [npc2.tongue]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right slut, keep sucking on my tits like the worthless little fuck toy you are!",
									"Come on bitch! Get that [npc2.tongue] of yours deeper into my nipples!",
									"Fucking bitch, put some more effort in! You know how lucky you are, being allowed to suck on my tits like this?!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! I love the feeling of your lips on my tits! Don't stop!",
									"Don't stop! Suck on my tits! Yes, yes, yes!",
									"Oh yes! Lick my nipples! I love your [npc2.tongue]! Get it deeper!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Don't stop!",
									"Don't stop! I love your [npc2.tongue]!",
									"Oh yes! Suck on my tits!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your tongue out of my nipple!",
									"Leave me alone! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Get that tongue deeper!",
									"Oh yeah! Keep going!",
									"Deeper! Don't stop!");
							break;
					}
					break;
				default:// Self penetration:
					switch(Sex.getSexPace(this)) {
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Go away! Leave me alone!",
									"Stop it! Just go away!",
									"Please stop! Don't do this!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck!",
									"Yeah!",
									"Oh yeah!");
							break;
					}
			}
		}

		if(returnedLine.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, returnedLine);
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to having their nipples used. Returns null if no penetration found.
	 */
	public String getDirtyTalkBreastsPenetrated(GameCharacter target, boolean isPlayerDom){
		String returnedLine = "";
		
		if(Sex.getPenetrationTypeInOrifice(this, OrificeType.BREAST) != null) {
			switch(Sex.getPenetrationTypeInOrifice(this, OrificeType.BREAST)) {
			case FINGER:
				switch(Sex.getSexPace(this)) {
					case DOM_GENTLE:
						returnedLine = UtilText.returnStringAtRandom(
								"Good [pc.girl], you love having your [pc.breasts] fondled like this, don't you?",
								"I love your [pc.breasts]!",
								"What a good [pc.girl]! Your tits love the feeling of my [npc.fingers], don't they?");
						break;
					case DOM_NORMAL:
						returnedLine = UtilText.returnStringAtRandom(
								"You love the feeling of having your [pc.breasts] fondled, don't you?!",
								"I love your [pc.breasts+]!",
								"You like it when I press my [npc.fingers] into your [pc.breasts], like <i>this</i>?!");
						break;
					case DOM_ROUGH:
						returnedLine = UtilText.returnStringAtRandom(
								"What a dirty slut! Moaning as I grope your [pc.breasts+]!",
								"You love this, don't you bitch?! Having your [pc.breasts] groped and fondled like <i>this</i>!",
								"That's right slut! Your [pc.breasts+] are mine to use however I want!");
						break;
					case SUB_EAGER:
						returnedLine = UtilText.returnStringAtRandom(
								"Yes! I love the feel of your [pc.breasts]!",
								"I love giving your tits the attention they deserve!",
								"I love your [pc.breasts]!");
						break;
					case SUB_NORMAL:
						returnedLine = UtilText.returnStringAtRandom(
								"Yes! I love the feel of your [pc.breasts]!",
								"I love giving your tits the attention they deserve!",
								"I love your [pc.breasts]!");
						break;
					case SUB_RESISTING:
						returnedLine = UtilText.returnStringAtRandom(
								"I don't want to do this! Please let me stop!",
								"Let me go! I don't want to do this!",
								"Please! Stop! I don't want this!");
						break;
					default:
						returnedLine = UtilText.returnStringAtRandom(
								"Fuck!",
								"Yeah!",
								"Oh yeah!");
						break;
				}
				break;
			case PENIS:
				switch(Sex.getSexPace(this)) {
					case DOM_GENTLE:
						returnedLine = UtilText.returnStringAtRandom(
								"Good [pc.girl]! Feel my cock slide up between your [pc.breasts]!",
								"That's right, be a good [pc.girl] and moan for me! Feel my [npc.cock] sliding up between your [pc.breasts+]!",
								"Your [pc.breasts] feel so good squeezing down around my [npc.cock]!");
						break;
					case DOM_NORMAL:
						returnedLine = UtilText.returnStringAtRandom(
								"Fuck! Your tits feel so good to fuck!",
								"Oh yes! Wrap your tits around my cock!",
								"Your tits were made for my cock!");
						break;
					case DOM_ROUGH:
						returnedLine = UtilText.returnStringAtRandom(
								"That's right slut, pleasure my cock! Push your tits together and make this good for me!",
								"What a horny bitch! Using your tits to please my cock like a desperate slut!",
								"You like this, fuck toy?! Squeezing your [pc.breasts] around my cock and pleasing me like the slut you are?!");
						break;
					case SUB_EAGER:
						returnedLine = UtilText.returnStringAtRandom(
								"Yes! Use my cock! I love your tits!",
								"Don't stop! Harder! Fuck me! Yes, yes, yes!",
								"Oh yes! Use me! I love your tits!");
						break;
					case SUB_NORMAL:
						returnedLine = UtilText.returnStringAtRandom(
								"Yes! Fuck me!",
								"Don't stop! Fuck me!",
								"Oh yes! Fuck me!");
						break;
					case SUB_RESISTING:
						returnedLine = UtilText.returnStringAtRandom(
								"I don't want to do this! Please let me stop!",
								"Let me go! Get off my cock!",
								"Please! Stop! I don't want this!");
						break;
					default:
						returnedLine = UtilText.returnStringAtRandom(
								"Fuck me! Yes! Harder!",
								"Oh yeah! Fuck me!",
								"Harder! Don't stop!");
						break;
				}
				break;
			case TAIL:
				switch(Sex.getSexPace(this)) {
					case DOM_GENTLE:
						returnedLine = UtilText.returnStringAtRandom(
								"Good [pc.girl]! Feel my [npc.tail] slide up between your [pc.breasts]!",
								"That's right, be a good [pc.girl] and moan for me! Feel my [npc.tail] sliding up between your [pc.breasts+]!",
								"Your [pc.breasts] feel so good squeezing down around my [npc.tail]!");
						break;
					case DOM_NORMAL:
						returnedLine = UtilText.returnStringAtRandom(
								"Fuck! Your tits feel so good to fuck!",
								"Oh yes! Wrap your tits around my [npc.tail]!",
								"Your tits were made for my [npc.tail]!");
						break;
					case DOM_ROUGH:
						returnedLine = UtilText.returnStringAtRandom(
								"That's right slut, pleasure my [npc.tail]! Push your tits together and make this good for me!",
								"What a horny bitch! Using your tits to please my [npc.tail] like a desperate slut!",
								"You like this, fuck toy?! Squeezing your [pc.breasts] around my [npc.tail] and pleasing me like the slut you are?!");
						break;
					case SUB_EAGER:
						returnedLine = UtilText.returnStringAtRandom(
								"Yes! Use my [npc.tail]! I love your tits!",
								"Don't stop! Harder! Fuck me! Yes, yes, yes!",
								"Oh yes! Use me! I love your tits!");
						break;
					case SUB_NORMAL:
						returnedLine = UtilText.returnStringAtRandom(
								"Yes! Fuck me!",
								"Don't stop! Fuck me!",
								"Oh yes! Fuck me!");
						break;
					case SUB_RESISTING:
						returnedLine = UtilText.returnStringAtRandom(
								"I don't want to do this! Please let me stop!",
								"Let me go! Get off my [npc.tail]!",
								"Please! Stop! I don't want this!");
						break;
					default:
						returnedLine = UtilText.returnStringAtRandom(
								"Fuck me! Yes! Harder!",
								"Oh yeah! Fuck me!",
								"Harder! Don't stop!");
						break;
				}
				break;
			case TONGUE:
				switch(Sex.getSexPace(this)) {
					case DOM_GENTLE:
						returnedLine = UtilText.returnStringAtRandom(
								"Your tits taste so good!",
								"Good [pc.girl]! I love the taste of your tits!",
								"What a good [pc.girl]! You love having your tits kissed like this, don't you?");
						break;
					case DOM_NORMAL:
						returnedLine = UtilText.returnStringAtRandom(
								"Oh yes! Your tits taste so good!",
								"You like this? Feeling my tongue running over your breasts?!",
								"I love the taste of your tits!");
						break;
					case DOM_ROUGH:
						returnedLine = UtilText.returnStringAtRandom(
								"Keep still slut, and be thankful that I'm giving your tits some attention!",
								"Stay still bitch! Just keep moaning and enjoying this while it lasts!",
								"You'd better appreciate this bitch! You know how lucky you are, having me lick your tits?!");
						break;
					case SUB_EAGER:
						returnedLine = UtilText.returnStringAtRandom(
								"Yes! I love your tits! Let me suck on your nipples!",
								"Oh yes! Let me suck on your nipples! Yes, yes, yes!",
								"Oh yes! I love your tits!");
						break;
					case SUB_NORMAL:
						returnedLine = UtilText.returnStringAtRandom(
								"I love your tits! Let me suck on your nipples!",
								"Oh yes! Let me suck on your nipples!",
								"I love your tits!");
						break;
					case SUB_RESISTING:
						returnedLine = UtilText.returnStringAtRandom(
								"I don't want to do this! Please let me stop!",
								"Let me go! I don't want to do this!",
								"Please! Stop! I don't want this!");
						break;
					default:
						returnedLine = UtilText.returnStringAtRandom(
								"Yes! Keep going!",
								"Oh yeah! Keep going!",
								"Don't stop!");
						break;
				}
				break;
			default:// Self penetration:
				switch(Sex.getSexPace(this)) {
					case SUB_RESISTING:
						returnedLine = UtilText.returnStringAtRandom(
								"Go away! Leave me alone!",
								"Stop it! Just go away!",
								"Please stop! Don't do this!");
						break;
					default:
						returnedLine = UtilText.returnStringAtRandom(
								"That's right, try to get yourself off!",
								"You trying to get yourself off?",
								"Yes! Keep going!");
						break;
				}
			}
		}

		if(returnedLine.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, returnedLine);
	}
	
	// Dirty talk related to penetrating areas:
	
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to penetrating someone using their fingers. Returns null if no penetration found.
	 */
	public String getDirtyTalkFingerPenetrating(GameCharacter target, boolean isPlayerDom){
		List<String> availableLines = new ArrayList<>();
		
		if(!Sex.getOrificesBeingPenetratedBy(this, PenetrationType.FINGER, target).isEmpty()) {
			for(OrificeType orifice : Sex.getOrificesBeingPenetratedBy(this, PenetrationType.FINGER, target)) {
				switch(orifice) {
					case ANUS:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], you love feeling my [npc1.fingers] deep in your ass, don't you?",
										"I love fingering cute little asses like yours!",
										"What a good [npc2.girl]! Your ass loves the feeling of my [npc1.fingers], doesn't it?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You love the feeling of my [npc1.fingers] deep in your ass, don't you?!",
										"I love fingering cute little asses like yours!",
										"You like it when I curl my [npc1.fingers] up inside your ass, like <i>this</i>?!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"What a dirty slut! I can feel your horny little ass clenching down on my [npc1.fingers]!",
										"You love this, don't you bitch?! Feeling my [npc1.fingers] pushing deep into your slutty little asshole!",
										"That's right slut! You love having my [npc1.fingers] stuffed deep in your slutty ass!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Let me get my fingers deep inside your ass!",
										"I love giving your ass the attention it deserves!",
										"I love fingering your ass!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"I love fingering your ass!",
										"I love giving your ass the attention it deserves!",
										"I love fingering your ass!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!"));
								break;
						}
						break;
					case ASS:
						break;
					case BREAST:
						break;
					case MOUTH:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], keep sucking on my [npc1.fingers]!",
										"That's right, keep swirling your [npc2.tongue] around my [npc1.fingers]!",
										"What a good [npc2.girl]! You love sucking on my [npc1.fingers], don't you?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You love sucking on my [npc1.fingers], don't you?!",
										"That's right, keep sucking on my [npc1.fingers]!",
										"Keep sucking on my [npc1.fingers], just like that!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut! Suck on my [npc1.fingers] like you would on a nice thick cock!",
										"You love this, don't you bitch?! Having my [npc1.fingers] sliding in and out of your mouth!",
										"That's right slut! Suck on my [npc1.fingers] as I stuff them deep down your throat!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Suck on my [npc1.fingers]! Just like that!",
										"I love having my [npc1.fingers] sucked! Keep going!",
										"Keep sucking my [npc1.fingers]! Yes! Just like that!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Keep sucking on my [npc1.fingers]!",
										"I love having my [npc1.fingers] sucked!",
										"Keep sucking my [npc1.fingers]! Yes!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!"));
						}
						break;
					case NIPPLE:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], you love feeling my [npc1.fingers] deep in your nipples, don't you?",
										"I love fingering cute little nipples like yours!",
										"What a good [npc2.girl]! Your tits love the feeling of my [npc1.fingers], don't they?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You love the feeling of my [npc1.fingers] deep in your nipples, don't you?!",
										"I love fingering cute little nipples like yours!",
										"You like it when I curl my [npc1.fingers] up inside your tits, like <i>this</i>?!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"What a dirty slut! Moaning as I stuff my [npc1.fingers] deep into your nipples!",
										"You love this, don't you bitch?! Feeling my [npc1.fingers] pushing deep into your tits!",
										"That's right slut! You love having my [npc1.fingers] stuffed deep in your slutty nipples!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Let me get my fingers deep inside your nipples!",
										"I love giving your tits the attention they deserve!",
										"I love fingering your nipples!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"I love fingering your nipples!",
										"I love giving your tits the attention they deserve!",
										"I love fingering your nipples!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!"));
								break;
						}
						break;
					case THIGHS:
						break;
					case URETHRA:
						break;
					case VAGINA:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], you love feeling my [npc1.fingers] deep in your pussy, don't you?",
										"I love fingering cute little things like you!",
										"What a good [npc2.girl]! Your pussy loves the feeling of my [npc1.fingers], doesn't it?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You love the feeling of my [npc1.fingers] deep in your pussy, don't you?!",
										"I love fingering cute [npc2.girl]s like you!",
										"You like it when I curl my [npc1.fingers] up inside you, like <i>this</i>?!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"What a dirty slut! I can feel your horny pussy clenching down on my [npc1.fingers]!",
										"You love this, don't you bitch?! Feeling my [npc1.fingers] pushing deep into your slutty cunt!",
										"That's right slut! You love having my [npc1.fingers] stuffed deep in your slutty pussy!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Let me get my fingers deep inside your little pussy!",
										"I love giving your pussy the attention it deserves!",
										"I love fingering you!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"I love fingering your pussy!",
										"I love giving your pussy the attention it deserves!",
										"I love fingering you!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!"));
								break;
						}
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, availableLines.get(Util.random.nextInt(availableLines.size())));
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to penetrating someone using their penis. Returns null if no penetration found.
	 */
	public String getDirtyTalkPenisPenetrating(GameCharacter target, boolean isPlayerDom){
		List<String> availableLines = new ArrayList<>();
		
		if(!Sex.getOrificesBeingPenetratedBy(this, PenetrationType.PENIS, target).isEmpty()) {
			for(OrificeType orifice : Sex.getOrificesBeingPenetratedBy(this, PenetrationType.PENIS, target)) {
				switch(orifice) {
					case ANUS:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.cock] slide deep into your ass!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.cock] sliding deep into your hot ass!",
										"Your ass feels so good squeezing down around my [npc1.cock]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your ass feels so good!",
										"Oh yes! Take my cock! Take it deep!",
										"Your ass was made for my cock!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, take my cock! Your ass belongs to me!",
										"What a horny bitch! Take my cock you filthy little butt-slut!",
										"You feel that, fuck toy?! Do you feel my cock sinking deep into your slutty little ass?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my cock! I love your ass!",
										"Don't stop! Harder! Use my cock! Yes, yes, yes!",
										"Oh yes! Use me! I love your ass!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case ASS:
						break;
					case BREAST:
						break;
					case MOUTH:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], keep sucking my cock!",
										"That's right, use your [npc2.tongue] as well! You're good at sucking cock!",
										"What a good [npc2.girl]! You love sucking my cock, don't you?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You're good at sucking cock!",
										"Oh yeah! Keep sucking my cock!",
										"Use your tongue as well! Yeah, like that!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"Come on you slut! You can suck cock better than that!",
										"That's right bitch! Take my cock deep down your throat!",
										"Put some effort into it slut! You can suck cock better than that!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Keep sucking my cock! Just like that!",
										"Oh yes! Wrap those lips of yours around my cock! Keep going!",
										"Keep sucking my cock! Yes! Just like that!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Keep sucking my cock!",
										"Wrap those lips of yours around my cock! Keep going!",
										"Keep sucking my cock! Yes!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! Get off my cock!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case NIPPLE:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my cock slide deep into your breast!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.cock] sliding deep into your cute little nipple!",
										"Your cute little nipple feels so good squeezing down around my [npc1.cock]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your tits feel so good to fuck!",
										"Oh yes! Take my cock! Take it deep!",
										"Your tits were made for my cock!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, take my cock! Feel it pushing deep into your nipple!",
										"What a horny bitch! Taking my cock deep into your tit like a slut!",
										"You feel that, fuck toy?! Do you feel my cock sinking deep into your slutty little nipple?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my cock! I love your tits!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Use me! I love your tits!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! Get off my cock!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case THIGHS:
						break;
					case URETHRA:
						break;
					case VAGINA:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.cock] slide deep into your little pussy!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.cock] sliding deep into your cute little cunt!",
										"Your cute little cunt feels so good squeezing down around my [npc1.cock]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your pussy feels so good!",
										"Oh yes! Take my cock! Take it deep!",
										"Your pussy was made for my cock!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, take my cock! Your pussy belongs to me!",
										"What a horny bitch! Take my cock you slut!",
										"You feel that, fuck toy?! Do you feel my cock sinking deep into your slutty little cunt?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my cock! I love your pussy!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Use me! I love your pussy!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, availableLines.get(Util.random.nextInt(availableLines.size())));
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to penetrating someone using their tail. Returns null if no penetration found.
	 */
	public String getDirtyTalkTailPenetrating(GameCharacter target, boolean isPlayerDom){
		List<String> availableLines = new ArrayList<>();
		
		if(!Sex.getOrificesBeingPenetratedBy(this, PenetrationType.TAIL, target).isEmpty()) {
			for(OrificeType orifice : Sex.getOrificesBeingPenetratedBy(this, PenetrationType.TAIL, target)) {
				switch(orifice) {
					case ANUS:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.tail] slide deep into your cute little ass!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.tail] sliding deep into your cute little ass!",
										"Your cute little ass feels so good squeezing down around my [npc1.tail]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your ass feels so good!",
										"Oh yes! Take my [npc1.tail]! Take it deep into your ass!",
										"Your ass was made for a good tail-fucking!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right bitch, feel my [npc1.tail] pushing deep into your slutty ass!",
										"What a horny slut! Now moan for me as I fuck your ass with my tail!",
										"You feel that, fuck toy?! Do you feel my [npc1.tail] sinking deep into your slutty little ass?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my [npc1.tail]! I love your ass!",
										"Don't stop! Harder! Use my [npc1.tail]! Yes, yes, yes!",
										"Oh yes! Use me! I love your ass!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case ASS:
						break;
					case BREAST:
						break;
					case MOUTH:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], keep sucking my [npc1.tail]!",
										"That's right, use your [npc2.tongue] as well! You're good at this!",
										"What a good [npc2.girl]! You love sucking my [npc1.tail], don't you?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Take my [npc1.tail] deep down your throat!",
										"Oh yeah! Keep sucking my [npc1.tail]!",
										"Use your tongue as well! Yeah, like that!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"Come on you slut! Take my [npc1.tail] deep down your throat!",
										"That's right bitch! Take my [npc1.tail] deep down your throat!",
										"Put some effort into it slut! You can suck my [npc1.tail] better than that!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Keep sucking my [npc1.tail]! Just like that!",
										"Oh yes! Wrap those lips of yours around my [npc1.tail]! Keep going!",
										"Keep sucking my [npc1.tail]! Yes! Just like that!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Keep sucking my [npc1.tail]!",
										"Wrap those lips of yours around my [npc1.tail]! Keep going!",
										"Keep sucking my [npc1.tail]! Yes!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case NIPPLE:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.tail] slide deep into your breast!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.tail] sliding deep into your cute little nipple!",
										"Your cute little nipple feels so good squeezing down around my [npc1.tail]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your tits feel so good to fuck!",
										"Oh yes! Take my [npc1.tail] deep into your nipple!",
										"Your tits were made for my [npc1.tail]!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, take my [npc1.tail]! Feel it pushing deep into your nipple!",
										"What a horny bitch! Taking my [npc1.tail] deep into your tit like a slut!",
										"You feel that, fuck toy?! Do you feel my [npc1.tail] sinking deep into your slutty little nipple?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my [npc1.tail]! I love your tits!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Use me! I love your tits!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case THIGHS:
						break;
					case URETHRA:
						break;
					case VAGINA:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.tail] slide deep into your little pussy!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.tail] sliding deep into your cute little cunt!",
										"Your cute little cunt feels so good squeezing down around my [npc1.tail]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your pussy feels so good!",
										"Oh yes! Take my [npc1.tail]! Take it deep!",
										"Your pussy was made for a good tail-fucking!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, feel my [npc1.tail] pushing deep into your worthless little cunt! Your pussy belongs to me!",
										"What a horny bitch! Now moan for me as I fuck you with my tail!",
										"You feel that, fuck toy?! Do you feel my [npc1.tail] sinking deep into your slutty little cunt?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my [npc1.tail]! I love your pussy!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Use me! I love your pussy!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, availableLines.get(Util.random.nextInt(availableLines.size())));
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to penetrating someone using their tongue. Returns null if no penetration found.
	 */
	public String getDirtyTalkTonguePenetrating(GameCharacter target, boolean isPlayerDom){
		List<String> availableLines = new ArrayList<>();
		
		if(!Sex.getOrificesBeingPenetratedBy(this, PenetrationType.TONGUE, target).isEmpty()) {
			for(OrificeType orifice : Sex.getOrificesBeingPenetratedBy(this, PenetrationType.TONGUE, target)) {
				switch(orifice) {
					case ANUS:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right, moan for me as I pleasure your ass!",
										"Good [npc2.girl]! I love licking cute little asses like yours!",
										"What a good [npc2.girl]! You love my tongue in your ass, don't you?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right, moan for me as I lick your ass!",
										"Feel my tongue deep in your ass! Moan for me!",
										"You love my tongue in your ass, don't you?"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"Keep still slut, I need to practice my oral skills on your worthless ass!",
										"Stay still bitch! Just keep moaning and enjoying this while it lasts!",
										"You'd better appreciate this bitch! You know how lucky you are, being used as oral practice?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! I love your ass! Feel my [npc1.tongue] pushing deep!",
										"Oh yes! Let me lick your ass! Yes, yes, yes!",
										"Oh yes! I love licking ass! Let me get my [npc1.tongue] nice and deep!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"I love licking your ass!",
										"Let me lick your ass! I love this!",
										"I love licking your ass!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! Get your ass away from my face!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Get that tongue deeper!",
										"Oh yeah! Keep going!",
										"Deeper! Don't stop!"));
								break;
						}
						break;
					case ASS:
						break;
					case BREAST:
						break;
					case MOUTH:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Your lips taste so good!",
										"Good [npc2.girl]! Don't stop!",
										"What a good [npc2.girl]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Your lips taste so good!",
										"Good [npc2.girl]! Don't stop!",
										"What a good [npc2.girl]!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"Come on slut, you can kiss better than that!",
										"Put some more effort into this bitch! Kiss me like you mean it!",
										"Fucking slut, you can kiss me better than this! Put some more effort into it!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Oh yes! Your lips taste so good! I need more!",
										"I love kissing you! Yes, yes, yes!",
										"Oh yes! Your lips taste so good!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Your lips taste so good! I need more!",
										"I love kissing you!",
										"Your lips taste so good!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Your lips taste so good!",
										"I love kissing you!",
										"Don't stop!"));
								break;
						}
						break;
					case NIPPLE:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Your nipples taste so good!",
										"Good [npc2.girl]! I love the taste of your tits!",
										"What a good [npc2.girl]! You love having my tongue in your nipple, don't you?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Oh yes! Your tits taste so good!",
										"You like this? Feeling my tongue deep in your hot little nipple?!",
										"I love the taste of your tits!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"Keep still slut, I need to practice my skills on fuckable nipples like yours!",
										"Stay still bitch! Just keep moaning and enjoying this while it lasts!",
										"You'd better appreciate this bitch! You know how lucky you are, having me lick your nipples?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! I love your tits! Let me suck on your nipples!",
										"Oh yes! Let me suck on your nipples! Yes, yes, yes!",
										"Oh yes! I love your nipples! Let me get my [npc1.tongue] nice and deep!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"I love your tits! Let me suck on your nipples!",
										"Oh yes! Let me suck on your nipples!",
										"I love your nipples! Let me get my [npc1.tongue] nice and deep!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Get that tongue deeper!",
										"Oh yeah! Keep going!",
										"Deeper! Don't stop!"));
								break;
						}
						break;
					case THIGHS:
						break;
					case URETHRA:
						break;
					case VAGINA:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Your pussy tastes so good!",
										"Good [npc2.girl]! I love the taste of your pussy!",
										"What a good [npc2.girl]! You love my tongue in your pussy, don't you?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Oh yes! Your pussy tastes so good!",
										"You like this? Feeling my tongue deep in your hot little cunt?!",
										"I love the taste of your pussy!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"Keep still slut, I need to practice my oral skills on you!",
										"Stay still bitch! Just keep moaning and enjoying this while it lasts!",
										"You'd better appreciate this bitch! You know how lucky you are, being used as oral practice?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! I love your pussy! You taste so good!",
										"Oh yes! Let me eat you out! Yes, yes, yes!",
										"Oh yes! I love the taste of your pussy! Let me get my [npc1.tongue] nice and deep!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"I love your pussy! You taste so good!",
										"Let me eat you out! You taste good!",
										"I love the taste of your pussy!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please stop!",
										"Let me go! Get your pussy away from my face!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Get that tongue deeper!",
										"Oh yeah! Keep going!",
										"Deeper! Don't stop!"));
								break;
						}
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, availableLines.get(Util.random.nextInt(availableLines.size())));
	}
	
	
	// Area reveals: TODO All reveals need to take in character being revealed

	public String getAssRevealDescription(GameCharacter characterBeingRevealed) {
		if(characterBeingRevealed.isPlayer()) {
			switch(Sex.getSexPace(Sex.getTargetedPartner(this))) {
				case DOM_GENTLE:
					return "<p>"
								+ "[npc.Name] lets out a soft [npc.moan] as your [pc.asshole+] is revealed."
							+ "</p>";
				case DOM_NORMAL:
					return "<p>"
								+ "[npc.Name] lets out [npc.a_moan+] as your [pc.asshole+] is revealed."
							+ "</p>";
				case DOM_ROUGH:
					return "<p>"
								+ "[npc.Name] lets out a hungry growl as your [pc.asshole+] is revealed."
							+ "</p>";
				case SUB_EAGER:
					return "<p>"
								+ "[npc.Name] lets out [npc.a_moan+] as your [pc.asshole+] is revealed."
							+ "</p>";
				case SUB_NORMAL:
					return "<p>"
								+ "[npc.Name] lets out [npc.a_moan] as your [pc.asshole+] is revealed."
							+ "</p>";
				case SUB_RESISTING:
					return "<p>"
								+ "[npc.Name] lets out [npc.a_sob+] as your [pc.asshole+] is revealed."
							+ "</p>";
				default:
					return "<p>"
								+ "[npc.Name] lets out [npc.a_moan] as your [pc.asshole+] is revealed."
							+ "</p>";
			}
		} else {
			return "";
		}
	}

	public String getBreastsRevealDescription(GameCharacter characterBeingRevealed) {
		if(characterBeingRevealed.isPlayer()) {
			if(!Sex.isConsensual() && Sex.getSexPace(Sex.getTargetedPartner(this))!=SexPace.SUB_RESISTING) {
				if(!Sex.isDom(this)) {
					// Feminine NPC:
					if(this.isFeminine()) {
						if (characterBeingRevealed.isFeminine()) {
							if (!this.hasBreasts()) {
								return "<p>"
										+ "[npc.Name] struggles to stifle a mocking laugh as your flat chest is revealed, "
										+ "[npc.speech(Pfft-hahaha!)]"
										+ "</p>";
								
							} else if (this.getBreastRawSizeValue() <= CupSize.JJ.getMeasurement()) {
								if(this.getBreastSize().getMeasurement() >= this.getBreastSize().getMeasurement()) {
									return "<p>"
											+ "[npc.Name] puts on a patronising smile as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(Aww... They're pretty cute!)]"
											+ "</p>";
									
								} else {
									return "<p>"
											+ "[npc.Name] looks embarrassed as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(They're so much bigger than mine...)]"
											+ "</p>";
								}
								
							} else {
								return "<p>"
										+ "[npc.Name]'s jaw drops as your [pc.breastSize] breasts are revealed, "
										+ "[npc.speech(How much bubble milk have you been drinking?!)]"
										+ "</p>";
							}
							
						} else {
							if (!this.hasBreasts()) {
								return "";
								
							} else if (this.getBreastRawSizeValue() <= CupSize.C.getMeasurement()) {
								return "<p>"
										+ "In a very patronising voice, [npc.name] reacts to your breasts being revealed, "
										+ "[npc.speech(Aww, you trying to become a girl?)]"
										+ "</p>";
			
							} else if (this.getBreastRawSizeValue() <= CupSize.JJ.getMeasurement()) {
								if(this.getBreastSize().getMeasurement() >= this.getBreastSize().getMeasurement()) {
									return "<p>"
											+ "[npc.Name] looks surprised as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(Why would a guy have tits like that?)]"
											+ "</p>";
									
								} else {
									return "<p>"
											+ "[npc.Name] fails to contain [npc.her] surprise as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(A <i>guy</i> has bigger tits than me?!)]"
											+ "</p>";
								}
			
							} else {
								return "<p>"
										+ "[npc.Name]'s jaw drops as your [pc.breastSize] breasts are revealed, "
										+ "[npc.speech(How much bubble milk have you been drinking?!)]"
										+ "</p>";
							}
							
						}
						
					// Masculine NPC:
					} else {
						if (characterBeingRevealed.isFeminine()) {
							if (!this.hasBreasts()) {
								return "<p>"
										+ "[npc.Name] struggles to stifle a mocking laugh as your flat chest is revealed, "
										+ "[npc.speech(Pfft-hahaha!)]"
										+ "</p>";
								
							} else if (this.getBreastRawSizeValue() <= CupSize.C.getMeasurement()) {
								return "<p>"
										+ "[npc.Name] lets out a disappointed hum as your [pc.breastSize] breasts are revealed, "
										+ "[npc.speech(Huh... They're pretty small you know...)]"
										+ "</p>";
								
							} else if (this.getBreastRawSizeValue() <= CupSize.JJ.getMeasurement()) {
								return UtilText.parse(this,
											"[npc.Name]'s eyes light up as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(Oh fuck yeah... Look at the size of those tits!)]")
										+ "</p>";
								
							} else {
								return UtilText.parse(this,
											"[npc.Name]'s jaw drops as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(How much bubble milk have you been drinking?!)]")
										+ "</p>";
							}
							
						} else {
							if (!this.hasBreasts()) {
								return "";
								
							} else if (this.getBreastRawSizeValue() <= CupSize.C.getMeasurement()) {
								return "<p>"
											+ "In a mocking tone, [npc.name] questions you as your tiny breasts are revealed, "
											+ "[npc.speech(Hah, you trying to become a girl?)]"
										+ "</p>";
								
							} else if (this.getBreastRawSizeValue() <= CupSize.FF.getMeasurement()) {
								return "<p>"
											+ "[npc.Name] looks surprised as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(Why would a guy have tits like that?)]"
										+ "</p>";
								
							} else if (this.getBreastRawSizeValue() <= CupSize.JJ.getMeasurement()) {
								return "<p>"
											+ "[npc.Name] fails to contain his surprise as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(What's a <i>guy</i> doing with such massive tits?!)]"
										+ "</p>";
								
							} else {
								return "<p>"
											+ "[npc.Name]'s jaw drops as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(How much bubble milk have you been drinking?!)]"
										+ "</p>";
							}
							
						}
					}
					
				} else {
					// Feminine NPC:
					if(this.isFeminine()) {
						if (characterBeingRevealed.isFeminine()) {
							if (!this.hasBreasts()) {
								return "<p>"
										+ "[npc.Name] lets out a mocking laugh as your flat chest is revealed, "
										+ "[npc.speech(Hahaha, I don't think I've ever seen a girl with a chest <i>that</i> flat before!)]"
										+ "</p>";
			
							} else if (this.getBreastRawSizeValue() <= CupSize.JJ.getMeasurement()) {
								if(this.getBreastSize().getMeasurement() >= this.getBreastSize().getMeasurement()) {
									return "<p>"
											+ "[npc.Name] grins down at you as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(Aww, look at those tiny little things, how cute!)]"
											+ "</p>";
									
								} else {
									return "<p>"
											+ "[npc.Name] looks annoyed as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(Are you trying to put me to shame or something?!)]"
											+ "</p>";
								}
			
							} else {
								return "<p>"
										+ "[npc.Name]'s jaw drops as your [pc.breastSize] breasts are revealed, "
										+ "[npc.speech(How much bubble milk have you been drinking?!)]"
										+ "</p>";
							}
			
						} else {
							if (!this.hasBreasts()) {
								return "";
								
							} else if (this.getBreastRawSizeValue() <= CupSize.C.getMeasurement()) {
								return "<p>"
										+ "[npc.Name] grins at you as your [pc.breastSize] breasts are revealed, "
										+ "[npc.speech(Aww, you trying to become a girl?)]"
										+ "</p>";
			
							} else if (this.getBreastRawSizeValue() <= CupSize.JJ.getMeasurement()) {
								if(this.getBreastSize().getMeasurement() >= this.getBreastSize().getMeasurement()) {
									return "<p>"
											+ "[npc.Name] looks surprised as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(Why would a guy have tits like that?)]"
											+ "</p>";
									
								} else {
									return "<p>"
											+ "[npc.Name] looks annoyed as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(Are you kidding me?! A <i>guy</i> has bigger tits than me?!)]"
											+ "</p>";
								}
			
							} else {
								return "<p>"
										+ "[npc.Name]'s jaw drops as your [pc.breastSize] breasts are revealed, "
										+ "[npc.speech(How much bubble milk have you been drinking?!)]"
										+ "</p>";
							}
							
						}
						
					// Masculine NPC:
					} else {
						if (characterBeingRevealed.isFeminine()) {
							if (!this.hasBreasts()) {
								return "<p>"
											+ "[npc.Name] lets out a mocking laugh as your flat chest is revealed, "
											+ "[npc.speech(Hahaha, I don't think I've ever seen a girl with a chest <i>that</i> flat before!)]"
										+ "</p>";
								
							} else if (this.getBreastRawSizeValue() <= CupSize.C.getMeasurement()) {
								return "<p>"
											+ "[npc.Name] growls down at you as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(I like my girls with bigger tits than that!)]"
										+ "</p>";
								
							} else if (this.getBreastRawSizeValue() <= CupSize.FF.getMeasurement()) {
								return "<p>"
											+ "[npc.Name] grins as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(Mmm yeah, those are some nice tits!)]"
										+ "</p>";
								
							} else if (this.getBreastRawSizeValue() <= CupSize.JJ.getMeasurement()) {
								return "<p>"
											+ "[npc.Name] looks delighted as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(Oh fuck yeah! Look at the size of those things!)]"
										+ "</p>";
								
							} else {
								return "<p>"
											+ "[npc.Name]'s jaw drops as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(How much bubble milk have you been drinking?! What a fucking tit-cow!)]"
										+ "</p>";
							}
							
						} else {
							if (!this.hasBreasts()) {
								return "";
								
							} else if (this.getBreastRawSizeValue() <= CupSize.C.getMeasurement()) {
								return "<p>"
											+ "[npc.Name] bursts out laughing as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(Hahaha, you trying to become a girl?!)]"
										+ "</p>";
								
							} else if (this.getBreastRawSizeValue() <= CupSize.FF.getMeasurement()) {
								return "<p>"
											+ "[npc.Name] looks surprised as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(Why would a guy have tits like that?!)]"
										+ "</p>";
								
							} else if (this.getBreastRawSizeValue() <= CupSize.JJ.getMeasurement()) {
								return "<p>"
											+ "[npc.Name] lets out a mocking laugh as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(Are you kidding me?! Why does a <i>guy</i> have tits like that?!)]"
										+ "</p>";
								
							} else {
								return "<p>"
											+ "[npc.Name]'s jaw drops as your [pc.breastSize] breasts are revealed, "
											+ "[npc.speech(How much bubble milk have you been drinking?!)]"
										+ "</p>";
							}
						}
					}
				}
				
			} else {
				switch(Sex.getSexPace(this)) {
					case DOM_GENTLE:
						return "<p>"
									+ "[npc.Name] lets out a soft [npc.moan] as your [pc.breasts+] are revealed."
								+ "</p>";
					case DOM_NORMAL:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_moan+] as your [pc.breasts+] are revealed."
								+ "</p>";
					case DOM_ROUGH:
						return "<p>"
									+ "[npc.Name] lets out a hungry growl as your [pc.breasts+] are revealed."
								+ "</p>";
					case SUB_EAGER:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_moan+] as your [pc.breasts+] are revealed."
								+ "</p>";
					case SUB_NORMAL:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_moan] as your [pc.breasts+] are revealed."
								+ "</p>";
					case SUB_RESISTING:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_sob+] as your [pc.breasts+] are revealed."
								+ "</p>";
					default:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_moan] as your [pc.breasts+] are revealed."
								+ "</p>";
				}
			}
			
		} else {
			return "";
		}
	}

	public String getPenisRevealDescription(GameCharacter characterBeingRevealed) {
		if(characterBeingRevealed.isPlayer()) {
			if(!Sex.isConsensual() && Sex.getSexPace(Sex.getTargetedPartner(this))!=SexPace.SUB_RESISTING) {
				// Feminine NPC:
				if(this.isFeminine()) {
					if(!Sex.isDom(this)) {
						if(characterBeingRevealed.isFeminine()) {
							if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.ONE_TINY.getMaximumValue()) {
								return "<p>"+
											"[npc.Name] fails to suppress a little giggle as your tiny [pc.cock] is revealed, "
											+ "[npc.speech(Aww, that's so cute! I didn't realise you were [pc.a_gender]!)]"
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.TWO_AVERAGE.getMaximumValue()) {
								return "<p>"+
											"[npc.Name] lets out a surprised gasp as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(Ooh! You're [pc.a_gender]?!)]"
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.FOUR_HUGE.getMaximumValue()) {
								return "<p>"+
											"[npc.Name] grins as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(Y'know, what with the bulge and everything, it was pretty obvious you're [pc.a_gender]!)]"
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.SIX_GIGANTIC.getMaximumValue()) {
								return "<p>"+
											"Her eyes open wide as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(I mean, I could see it was big from your bulge, but damn! I've never seen [pc.a_gender] with such a huge cock!)]"
										+ "</p>";
								
							} else {
								return "<p>"+
											"[npc.Name]'s jaw drops as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(Holy shit! I didn't think [pc.gender]s could get cocks like that!)]"
										+ "</p>";
							}
							
						} else {
							if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.ONE_TINY.getMaximumValue()) {
								return "<p>"+
											"[npc.She] fails to suppress a mocking laugh as your tiny [pc.cock] is revealed, "
											+ "[npc.speech(Hahaha, that's so pathetic! It's like a little clit!)]"
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.TWO_AVERAGE.getMaximumValue()) {
								return "<p>"+
											"[npc.She] lets out a patronising 'aww' as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(Look at that cute little thing!)]"
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.FOUR_HUGE.getMaximumValue()) {
								return "<p>"+
											"[npc.She] grins as your [pc.cockSize] [pc.cock] is revealed, "
												+ "[npc.speech(~Mmm!~ Now that's what I like to see!)]"
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.SIX_GIGANTIC.getMaximumValue()) {
								return "<p>"+
											"Her eyes open wide as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(Oh wow... This is gonna be good!)]"
										+ "</p>";
								
							} else {
								return "<p>"+
											"[npc.Name]'s jaw drops as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(Holy shit! Now <i>that's</i> a cock!)]"
										+ "</p>";
							}
						}
						
					} else {
						if (characterBeingRevealed.isFeminine()) {
							if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.ONE_TINY.getMaximumValue()) {
								return "<p>"
										+ "[npc.She] lets out a little giggle as your tiny [pc.cock] is revealed, "
										+ "[npc.speech(Aww, that's so cute! I didn't realise you were [pc.a_gender]!)]"
										+ "</p>";
			
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.TWO_AVERAGE.getMaximumValue()) {
								return "<p>"
										+ "[npc.She] lets out a surprised gasp as your [pc.cockSize] [pc.cock] is revealed, "
										+ "[npc.speech(Ooh! You're a cute little [pc.gender], aren't you?!)]"
										+ "</p>";
			
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.FOUR_HUGE.getMaximumValue()) {
								return "<p>"
										+ "[npc.She] grins as your [pc.cockSize] [pc.cock] is revealed, "
										+ "[npc.speech(Y'know, what with the bulge and everything, it was pretty obvious you're [pc.a_gender]!)]"
										+ "</p>";
			
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.SIX_GIGANTIC.getMaximumValue()) {
								return "<p>"
										+ "[npc.Her] eyes open wide as your [pc.cockSize] [pc.cock] is revealed, "
										+ "[npc.speech(I mean, I could see it was big from your bulge, but damn! I've never seen [pc.a_gender] with such a huge cock!)]"
										+ "</p>";
			
							} else {
								return "<p>"
										+ "[npc.Her] jaw drops as your [pc.cockSize] [pc.cock] is revealed, "
										+ "[npc.speech(Holy shit! I didn't think [pc.gender]s could get cocks like that!)]"
										+ "</p>";
							}
			
						} else {
							if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.ONE_TINY.getMaximumValue()) {
								return "<p>"
										+ "[npc.She] lets out a mocking laugh as your tiny [pc.cock] is revealed, "
										+ "[npc.speech(Hahaha, that's so pathetic!)]"
										+ "</p>";
			
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.TWO_AVERAGE.getMaximumValue()) {
								return "<p>"
										+ "[npc.She] lets out a patronising 'aww' as your [pc.cockSize] [pc.cock] is revealed, "
										+ "[npc.speech(Look at that cute little thing!)]"
										+ "</p>";
			
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.FOUR_HUGE.getMaximumValue()) {
								return "<p>"
										+ "[npc.She] grins as your [pc.cockSize] [pc.cock] is revealed, "
										+ "[npc.speech(~Mmm!~ That looks pretty good!)]"
										+ "</p>";
			
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.SIX_GIGANTIC.getMaximumValue()) {
								return "<p>"
										+ "Her eyes open wide as your [pc.cockSize] [pc.cock] is revealed, "
										+ "[npc.speech(Oh wow...)]"
										+ "</p>";
			
							} else {
								return "<p>"
										+ "[npc.Her] jaw drops as your [pc.cockSize] [pc.cock] is revealed, "
										+ "[npc.speech(Holy shit! Now <i>that's</i> a cock!)]"
										+ "</p>";
							}
						}
					}
					
				// Masculine NPC:
				} else {
					if(!Sex.isDom(this)){
						if (characterBeingRevealed.isFeminine()) {
							if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.ONE_TINY.getMaximumValue()) {
								return "<p>"
											+ "[npc.Name] lets out a surprised grunt as your tiny [pc.cock] is revealed, "
											+ "[npc.speech(Wait, what?! I thought you were a girl... Well, it looks cute enough...)]"
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.TWO_AVERAGE.getMaximumValue()) {
								return "<p>"
											+ "[npc.Name] lets out a surprised grunt as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(Wait, what?! You're [pc.a_gender]?!)]"
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.FOUR_HUGE.getMaximumValue()) {
								return "<p>"
											+ "[npc.Name] lets out a surprised grunt as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(I should have guessed from that bulge...)]"
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.SIX_GIGANTIC.getMaximumValue()) {
								return "<p>"
											+ "[npc.Name] lets out a surprised grunt as your [pc.cockSize] [pc.cock] is revealed, "
											+ (this.hasPenis()&&this.getPenisRawSizeValue()<characterBeingRevealed.getPenisRawSizeValue()
													?"[npc.speech(I saw you had a bulge, but what the hell?! How does [pc.a_gender] have a bigger cock than <i>me</i>?!)]"
													:"[npc.speech(I saw you had a bulge, but damn! That's one massive cock !)]")
										+ "</p>";
								
							} else {
								return "<p>"
											+ "[npc.Name]'s jaw drops as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(Holy shit! I didn't think "+characterBeingRevealed.getGender().getName()+"s could get cocks that big!)]"
										+ "</p>";
							}
							
						} else {
							if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.ONE_TINY.getMaximumValue()) {
								return "<p>"
											+ "[npc.Name] struggles to suppress a mocking grunt as your tiny [pc.cock] is revealed, "
											+ "[npc.speech(Pfft! What a cute little thing...)]"
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.TWO_AVERAGE.getMaximumValue()) {
								return "<p>"
											+ "[npc.Name] lets out a patronising grunt as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(Hah! Look at that little thing!)]"
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.FOUR_HUGE.getMaximumValue()) {
								return "<p>"
											+ "[npc.Name] lets out a grunt as your [pc.cockSize] [pc.cock] is revealed, "
											+ (this.hasPenis()&&this.getPenisRawSizeValue()<characterBeingRevealed.getPenisRawSizeValue()
													? "[npc.speech(Huh... That's even bigger than mine...)]"
													: "[npc.speech(That's pretty big, I guess...)]")
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.SIX_GIGANTIC.getMaximumValue()) {
								return "<p>"
											+ "[npc.Name] lets out a surprised grunt as your [pc.cockSize] [pc.cock] is revealed, "
											+ (this.hasPenis()&&this.getPenisRawSizeValue()<characterBeingRevealed.getPenisRawSizeValue()
													? "[npc.speech(Fuck... It's even bigger than mine!)]"
													: "[npc.speech(Now that's one huge cock!)]")
										+ "</p>";
								
							} else {
								return "<p>"
											+ "[npc.Name] gulps nervously as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(Fuck... That thing's massive...)]"
										+ "</p>";
							}
						}
						
					} else {
						if (characterBeingRevealed.isFeminine()) {
							if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.ONE_TINY.getMaximumValue()) {
								return "<p>"
											+ "[npc.Name] lets out a surprised grunt as your tiny [pc.cock] is revealed, "
											+ "[npc.speech(Wait, what?! I thought you were a girl! Well, it doesn't really matter...)]"
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.TWO_AVERAGE.getMaximumValue()) {
								return "<p>"
											+ "[npc.Name] lets out a surprised grunt as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(Wait, what?! You're [pc.a_gender]?!)]"
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.FOUR_HUGE.getMaximumValue()) {
								return "<p>"
											+ "[npc.Name] lets out a surprised grunt as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(I should have guessed from that bulge...)]"
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.SIX_GIGANTIC.getMaximumValue()) {
								return "<p>"
											+ "[npc.Name] lets out a surprised grunt as your [pc.cockSize] [pc.cock] is revealed, "
											+ (this.hasPenis()&&this.getPenisRawSizeValue()<characterBeingRevealed.getPenisRawSizeValue()
													? "[npc.speech(I saw you had a bulge, but what the hell?! How does [pc.a_gender] have a bigger cock than <i>me</i>?!)]"
													: "[npc.speech(Now that's one huge cock!)]")
										+ "</p>";
								
							} else {
								return "<p>"
											+ "[npc.Name]'s jaw drops as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(Holy shit! I didn't think [pc.gender]s could get cocks that big!)]"
										+ "</p>";
							}
							
						} else {
							if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.ONE_TINY.getMaximumValue()) {
								return "<p>"
											+ "[npc.Name] lets out a derisive sneer as your tiny [pc.cock] is revealed, "
											+ (this.hasPenis()&&this.getPenisRawSizeValue()<characterBeingRevealed.getPenisRawSizeValue()
												? "[npc.speech(Hah! That's so pathetic! I'll show you what a real cock looks like!)]"
												: "[npc.speech(Hah! That's so pathetic!)]")
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.TWO_AVERAGE.getMaximumValue()) {
								return "<p>"
											+ "[npc.Name] lets out a patronising sneer as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(Hah! Look at that little thing!)]"
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.FOUR_HUGE.getMaximumValue()) {
								return "<p>"
											+ "[npc.Name] lets out a derisive sneer as your [pc.cockSize] [pc.cock] is revealed, "
											+ (this.hasPenis()&&this.getPenisRawSizeValue()<characterBeingRevealed.getPenisRawSizeValue()
												? "[npc.speech(Huh... Trying to compete with me for size are you?)]"
												: "[npc.speech(That's pretty big, I guess...)]")
										+ "</p>";
					
							} else if (characterBeingRevealed.getPenisRawSizeValue() <= PenisSize.SIX_GIGANTIC.getMaximumValue()) {
								return "<p>"
											+ "[npc.Name] lets out a surprised grunt as your [pc.cockSize] [pc.cock] is revealed, "
											+ (this.hasPenis()&&this.getPenisRawSizeValue()<characterBeingRevealed.getPenisRawSizeValue()
												? "[npc.speech(Fuck... You're even bigger than me...)]"
												: "[npc.speech(Now that's one huge cock!)]")
										+ "</p>";
								
							} else {
								return "<p>"
											+ "[npc.Name]'s jaw drops as your [pc.cockSize] [pc.cock] is revealed, "
											+ "[npc.speech(Fuck...)]"
										+ "</p>";
							}
						}
					}
				}
			} else {
				switch(Sex.getSexPace(this)) {
					case DOM_GENTLE:
						return "<p>"
									+ "[npc.Name] lets out a soft [npc.moan] as your [pc.penis+] is revealed."
								+ "</p>";
					case DOM_NORMAL:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_moan+] as your [pc.penis+] is revealed."
								+ "</p>";
					case DOM_ROUGH:
						return "<p>"
									+ "[npc.Name] lets out a hungry growl as your [pc.penis+] is revealed."
								+ "</p>";
					case SUB_EAGER:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_moan+] as your [pc.penis+] is revealed."
								+ "</p>";
					case SUB_NORMAL:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_moan] as your [pc.penis+] is revealed."
								+ "</p>";
					case SUB_RESISTING:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_sob+] as your [pc.penis+] is revealed."
								+ "</p>";
					default:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_moan] as your [pc.penis+] is revealed."
								+ "</p>";
				}
			}
			
		}  else {
			if(this.getPlayerKnowsAreas().contains(CoverableArea.PENIS) || !isFeminine()) {
				switch(Sex.getSexPace(this)) {
					case DOM_GENTLE:
							return "<p>"
									+ "[npc.Name] lets out a soft [npc.moan] as [npc.her] [npc.cock+] is revealed."
								+ "</p>";
					case DOM_NORMAL:
						return "<p>"
									+ "[npc.Name] lets out an excited [npc.moan] as [npc.she] sees you staring at [npc.her] [npc.cock+]."
								+ "</p>";
					case DOM_ROUGH:
						return "<p>"
									+ "[npc.Name] grins as [npc.she] sees you staring at [npc.her] [npc.cock+]."
								+ "</p>";
					case SUB_EAGER:
						return "<p>"
									+ "[npc.Name] lets out an excited [npc.moan] as [npc.she] sees you staring at [npc.her] [npc.cock+]."
								+ "</p>";
					case SUB_NORMAL:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_moan] as [npc.she] sees you staring at [npc.her] [npc.cock+]."
								+ "</p>";
					case SUB_RESISTING:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_sob] and tries to pull away from you as [npc.her] [npc.cock+] is revealed."
								+ "</p>";
					default:
						return "<p>"
									+ "[npc.Name] lets out an excited [npc.moan] as [npc.she] sees you staring at [npc.her] [npc.cock+]."
								+ "</p>";
				}
				
			} else {
				switch(Sex.getSexPace(this)) {
					case DOM_GENTLE:
						return "<p>"
									+ "[npc.Name] lets out a soft giggle as [npc.she] sees you staring at [npc.her] [npc.cock+],"
									+ " [npc.speech(Let's have some fun!)]"
								+ "</p>";
					case DOM_NORMAL:
						return "<p>"
									+ "[npc.Name] lets out a playful giggle as [npc.she] sees you staring at [npc.her] [npc.cock+],"
									+ " [npc.speech(That look on your face is priceless! Now let's have some fun!)]"
								+ "</p>";
					case DOM_ROUGH:
						return "<p>"
									+ "[npc.Name] lets out a laugh as [npc.she] sees you staring at [npc.her] [npc.cock+],"
									+ " [npc.speech(Hah! I bet you didn't expect this!)]"
								+ "</p>";
					case SUB_EAGER:
						return "<p>"
									+ "[npc.Name] lets out a playful giggle as [npc.she] sees you staring at [npc.her] [npc.cock+],"
									+ " [npc.speech(That look on your face is priceless! Now let's have some fun!)]"
								+ "</p>";
					case SUB_NORMAL:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_moan] as [npc.she] sees you staring at [npc.her] [npc.cock+],"
									+ " [npc.speech(Let's have some fun!)]"
								+ "</p>";
					case SUB_RESISTING:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_sob] and tries to pull away from you as [npc.her] [npc.cock+] is revealed,"
									+ " [npc.speech(Leave me alone!)]"
								+ "</p>";
					default:
						return "<p>"
									+ "[npc.Name] lets out an excited [npc.moan] as [npc.she] sees you staring at [npc.her] [npc.cock+],"
									+ " [npc.speech(Hah! I bet you didn't expect this!)]"
								+ "</p>";
				}
			}
		}
	}

	public String getVaginaRevealDescription(GameCharacter characterBeingRevealed) {
		if(characterBeingRevealed.isPlayer()) {
			switch(Sex.getSexPace(this)) {
				case DOM_GENTLE:
					return "<p>"
							+ "[npc.Name] lets out a soft [npc.moan] as [npc.she] sees "
									+ (Sex.getWetOrificeTypes(characterBeingRevealed).get(OrificeType.VAGINA).contains(LubricationType.PLAYER_NATURAL_LUBRICATION)
											? "your wet [pc.pussy] betraying your arousal, "
											: "your [pc.pussy+], ")
									+ (this.hasPenis()
											?"[npc.speech(You're going to love this, I promise...)]"
											:"[npc.speech(I'll make this feel good, I promise...)]")
							+ "</p>";
				case DOM_NORMAL:
					return "<p>"
							+ "[npc.Name] lets out a soft [npc.moan] as [npc.she] sees "
									+ (Sex.getWetOrificeTypes(characterBeingRevealed).get(OrificeType.VAGINA).contains(LubricationType.PLAYER_NATURAL_LUBRICATION)
											? "your wet [pc.pussy] betraying your arousal, "
											: "your [pc.pussy+], ")
									+ (this.hasPenis()
											?"[npc.speech(You're going to be a good fuck!)]"
											:"[npc.speech(This is going to be fun!)]")
							+ "</p>";
				case DOM_ROUGH:
					return "<p>"
							+ "[npc.Name] smirks when [npc.she] sees "
									+ (Sex.getWetOrificeTypes(characterBeingRevealed).get(OrificeType.VAGINA).contains(LubricationType.PLAYER_NATURAL_LUBRICATION)
											? "your wet [pc.pussy] betraying your arousal, "
											: "your [pc.pussy+], ")
									+ (this.hasPenis()
											?"[npc.speech(Ready for a good hard fucking, slut?)]"
											:"[npc.speech(Looking good, slut!)]")
							+ "</p>";
				case SUB_EAGER:
					return "<p>"
							+ "[npc.Name]'s eyes light up when [npc.she] sees "
							+ (Sex.getWetOrificeTypes(characterBeingRevealed).get(OrificeType.VAGINA).contains(LubricationType.PLAYER_NATURAL_LUBRICATION)
									? "your wet [pc.pussy] betraying your arousal."
									: "your [pc.pussy].")
							+ "</p>";
				case SUB_NORMAL:
					return "<p>"
								+ "[npc.Name] lets out [npc.a_moan] as your [pc.pussy+] is revealed."
							+ "</p>";
				case SUB_RESISTING:
					return "<p>"
							+ "[npc.Name] tries to pull away from you as "
							+ (Sex.getWetOrificeTypes(characterBeingRevealed).get(OrificeType.VAGINA).contains(LubricationType.PLAYER_NATURAL_LUBRICATION)
									? "your wet [pc.pussy] is revealed."
									: "your [pc.pussy+] is revealed.")
							+ "</p>";
				default:
					return "<p>"
								+ "[npc.Name] lets out [npc.a_moan] as your [pc.pussy+] is revealed."
							+ "</p>";
			}
			
		} else {
			if(this.getPlayerKnowsAreas().contains(CoverableArea.VAGINA) || isFeminine()) {
				switch(Sex.getSexPace(this)) {
					case DOM_GENTLE:
							return "<p>"
									+ "[npc.Name] lets out a soft [npc.moan] as [npc.her] [npc.pussy+] is revealed."
								+ "</p>";
					case DOM_NORMAL:
						return "<p>"
									+ "[npc.Name] lets out an excited [npc.moan] as [npc.she] sees you staring at [npc.her] [npc.pussy+]."
								+ "</p>";
					case DOM_ROUGH:
						return "<p>"
									+ "[npc.Name] grins as [npc.she] sees you staring at [npc.her] [npc.pussy+]."
								+ "</p>";
					case SUB_EAGER:
						return "<p>"
									+ "[npc.Name] lets out an excited [npc.moan] as [npc.she] sees you staring at [npc.her] [npc.pussy+]."
								+ "</p>";
					case SUB_NORMAL:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_moan] as [npc.she] sees you staring at [npc.her] [npc.pussy+]."
								+ "</p>";
					case SUB_RESISTING:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_sob] and tries to pull away from you as [npc.her] [npc.pussy+] is revealed."
								+ "</p>";
					default:
						return "<p>"
									+ "[npc.Name] lets out an excited [npc.moan] as [npc.she] sees you staring at [npc.her] [npc.pussy+]."
								+ "</p>";
				}
				
			} else {
				switch(Sex.getSexPace(this)) {
					case DOM_GENTLE:
						return "<p>"
									+ "[npc.Name] lets out a soft [npc.moan] as [npc.her] [npc.pussy+] is revealed,"
									+ " [npc.speech(Hah! I bet you didn't expect this!)]"
								+ "</p>";
					case DOM_NORMAL:
						return "<p>"
									+ "[npc.Name] lets out an excited [npc.moan] as [npc.she] sees you staring at [npc.her] [npc.pussy+],"
									+ " [npc.speech(Hah! I bet you didn't expect this!)]"
								+ "</p>";
					case DOM_ROUGH:
						return "<p>"
									+ "[npc.Name] grins as [npc.she] sees you staring at [npc.her] [npc.pussy+],"
									+ " [npc.speech(Hah! I bet you didn't expect this!)]"
								+ "</p>";
					case SUB_EAGER:
						return "<p>"
									+ "[npc.Name] lets out an excited [npc.moan] as [npc.she] sees you staring at [npc.her] [npc.pussy+],"
									+ " [npc.speech(Hah! I bet you didn't expect this!)]"
								+ "</p>";
					case SUB_NORMAL:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_moan] as [npc.she] sees you staring at [npc.her] [npc.pussy+],"
									+ " [npc.speech(Hah! I bet you didn't expect this!)]"
								+ "</p>";
					case SUB_RESISTING:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_sob] and tries to pull away from you as [npc.her] [npc.pussy+] is revealed,"
									+ " [npc.speech(Leave me alone!)]"
								+ "</p>";
					default:
						return "<p>"
									+ "[npc.Name] lets out an excited [npc.moan] as [npc.she] sees you staring at [npc.her] [npc.pussy+],"
									+ " [npc.speech(Hah! I bet you didn't expect this!)]"
								+ "</p>";
				}
			}
		}
	}

	public String getMoundRevealDescription(GameCharacter characterBeingRevealed) {
		if(characterBeingRevealed.isPlayer()) {
			if(!Sex.isConsensual() && Sex.getSexPace(this)!=SexPace.SUB_RESISTING) {
				if(isFeminine()) {
					if (!Sex.isDom(this)) {
						return "<p>"
								+ "[npc.Name] looks confused for a moment before letting out a patronising sigh, "
								+ "[npc.speech(Awww... You're like a little doll down there! That's so cute!)]"
								+ "</p>";
					} else {
						return "<p>"
								+ "[npc.Name] looks confused for a moment before breaking out into a mocking laugh, "
								+ "[npc.speech(Hahaha! You're like a little doll down there!)]"
								+ "</p>";
					}
				// Masculine NPC:
				} else {
					if(!Sex.isDom(this)) {
						return "<p>"
								+ "[npc.Name] looks confused for a moment before letting out a patronising sneer, "
								+ "[npc.speech(Awww... You're like a little doll down there! That's so cute!)]"
							+ "</p>";
					} else {
						return "<p>"
									+ "[npc.Name] looks confused for a moment before breaking out into a mocking laugh, "
									+ "[npc.speech(Hahaha! You're like a little doll down there!)]"
								+ "</p>";
					}
				}
				
			} else {
				switch(Sex.getSexPace(this)) {
					case DOM_GENTLE:
						return "<p>"
									+ "[npc.Name] lets out a soft [npc.moan] as your genderless mound is revealed."
								+ "</p>";
					case DOM_NORMAL:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_moan+] as your genderless mound is revealed."
								+ "</p>";
					case DOM_ROUGH:
						return "<p>"
									+ "[npc.Name] lets out a mocking laugh as your genderless mound is revealed."
								+ "</p>";
					case SUB_EAGER:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_moan+] as your genderless mound is revealed."
								+ "</p>";
					case SUB_NORMAL:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_moan] as your genderless mound is revealed."
								+ "</p>";
					case SUB_RESISTING:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_sob+] as your genderless mound is revealed."
								+ "</p>";
					default:
						return "<p>"
									+ "[npc.Name] lets out [npc.a_moan] as your genderless mound is revealed."
								+ "</p>";
				}
			}
		} else {
			return "";
		}
	}


	// Penetrations:
	
	private static String generateGenericPenetrationDescription(GameCharacter characterPenetrating, PenetrationType penetrationType, GameCharacter characterPenetrated, OrificeType orifice) {
		
		// Kissing:
		if(penetrationType == PenetrationType.TONGUE && orifice == OrificeType.MOUTH) {
			if(characterPenetrating.isPlayer()) {
				switch(Sex.getSexPace(characterPenetrating)) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"Your soft [pc.moans] are muffled into [npc.name]'s mouth as you continue kissing [npc.herHim].",
								"You gently press your [pc.lips+] against [npc.name]'s as you continue kissing [npc.herHim].",
								"Gently pressing your [pc.lips+] against [npc.name]'s, you continue making out with [npc.herHim].");
					case DOM_NORMAL:
						return UtilText.returnStringAtRandom(
								"Your [pc.moans+] are muffled into [npc.name]'s mouth as you continue passionately kissing [npc.herHim].",
								"You eagerly press your [pc.lips+] against [npc.name]'s as you continue passionately kissing [npc.herHim].",
								"Passionately pressing your [pc.lips+] against [npc.name]'s, you continue making out with [npc.herHim].");
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"Your [pc.moans+] are muffled into [npc.name]'s mouth as you continue forcefully snogging [npc.herHim].",
								"You roughly grind your [pc.lips+] against [npc.name]'s as you continue forcefully snogging [npc.herHim].",
								"Roughly grinding your [pc.lips+] against [npc.name]'s, you continue making out with [npc.herHim].");
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"Your [pc.moans+] are muffled into [npc.name]'s mouth as you continue passionately kissing [npc.herHim].",
								"You eagerly press your [pc.lips+] against [npc.name]'s as you continue passionately kissing [npc.herHim].",
								"Passionately pressing your [pc.lips+] against [npc.name]'s, you continue making out with [npc.herHim].");
					case SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								"Your [pc.moans] are muffled into [npc.name]'s mouth as you continue kissing [npc.herHim].",
								"You press your [pc.lips+] against [npc.name]'s as you continue kissing [npc.herHim].",
								"Pressing your [pc.lips+] against [npc.name]'s, you continue making out with [npc.herHim].");
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Your [pc.sobs+] are muffled into [npc.name]'s mouth as you desperately try to push [npc.herHim] away from you.",
								"You try to pull your [pc.lips+] away from [npc.name]'s as you struggle against [npc.herHim].",
								"Trying to pull your [pc.lips+] away from [npc.name]'s, you continue struggling against [npc.her] unwanted kiss.");
				}
				
			} else if(characterPenetrated.isPlayer()) {
				switch(Sex.getSexPace(characterPenetrating)) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"[npc.Name]'s soft [npc.moans] are muffled into your mouth as [npc.she] continues kissing you.",
								"[npc.Name] gently presses [npc.her] [npc.lips+] against yours as [npc.she] continues kissing you.",
								"Gently pressing [npc.her] [npc.lips+] against yours, [npc.name] continues making out with you.");
					case DOM_NORMAL:
						return UtilText.returnStringAtRandom(
								"[npc.Name]'s [npc.moans+] are muffled into your mouth as [npc.she] continues passionately kissing you.",
								"[npc.Name] eagerly presses [npc.her] [npc.lips+] against yours as [npc.she] continues passionately kissing you.",
								"Passionately pressing [npc.her] [npc.lips+] against yours, [npc.name] continues making out with you.");
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"[npc.Name]'s [npc.moans+] are muffled into your mouth as [npc.she] continues forcefully snogging you.",
								"[npc.Name] roughly grinds [npc.her] [npc.lips+] against yours as [npc.she] continues forcefully snogging you.",
								"Roughly grinding [npc.her] [npc.lips+] against yours, [npc.name] continues making out with you.");
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"[npc.Name]'s [npc.moans+] are muffled into your mouth as [npc.she] continues passionately kissing you.",
								"[npc.Name] eagerly presses [npc.her] [npc.lips+] against yours as [npc.she] continues passionately kissing you.",
								"Passionately pressing [npc.her] [npc.lips+] against yours, [npc.name] continues making out with you.");
					case SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								"[npc.Name]'s [npc.moans] are muffled into your mouth as [npc.she] continues kissing you.",
								"[npc.Name] presses [npc.her] [npc.lips+] against yours as [npc.she] continues kissing you.",
								"Pressing [npc.her] [npc.lips+] against yours, [npc.name] continues making out with you.");
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"[npc.Name]'s [npc.sobs+] are muffled into your mouth as [npc.she] desperately tries to push you away from [npc.herHim].",
								"[npc.Name] tries to pull [npc.her] [npc.lips+] away from yours as [npc.she] struggles against you.",
								"Trying to pull [npc.her] [npc.lips+] away from yours, [npc.name] continues struggling against your unwanted kiss.");
				}
				
			} else {
				switch(Sex.getSexPace(characterPenetrating)) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"[npc.Name]'s soft [npc.moans] are muffled into [npc2.name]'s mouth as [npc.she] continues kissing [npc2.herHim].",
								"[npc.Name] gently presses [npc.her] [npc.lips+] against [npc2.name]'s as [npc.she] continues kissing [npc2.herHim].",
								"Gently pressing [npc.her] [npc.lips+] against [npc2.name]'s, [npc.name] continues making out with [npc2.herHim].");
					case DOM_NORMAL:
						return UtilText.returnStringAtRandom(
								"[npc.Name]'s [npc.moans+] are muffled into [npc2.name]'s mouth as [npc.she] continues passionately kissing [npc2.herHim].",
								"[npc.Name] eagerly presses [npc.her] [npc.lips+] against [npc2.name]'s as [npc.she] continues passionately kissing [npc2.herHim].",
								"Passionately pressing [npc.her] [npc.lips+] against [npc2.name]'s, [npc.name] continues making out with [npc2.herHim].");
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"[npc.Name]'s [npc.moans+] are muffled into [npc2.name]'s mouth as [npc.she] continues forcefully snogging [npc2.herHim].",
								"[npc.Name] roughly grinds [npc.her] [npc.lips+] against [npc2.name]'s as [npc.she] continues forcefully snogging [npc2.herHim].",
								"Roughly grinding [npc.her] [npc.lips+] against [npc2.name]'s, [npc.name] continues making out with [npc2.herHim].");
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"[npc.Name]'s [npc.moans+] are muffled into [npc2.name]'s mouth as [npc.she] continues passionately kissing [npc2.herHim].",
								"[npc.Name] eagerly presses [npc.her] [npc.lips+] against [npc2.name]'s as [npc.she] continues passionately kissing [npc2.herHim].",
								"Passionately pressing [npc.her] [npc.lips+] against [npc2.name]'s, [npc.name] continues making out with [npc2.herHim].");
					case SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								"[npc.Name]'s [npc.moans] are muffled into [npc2.name]'s mouth as [npc.she] continues kissing [npc2.herHim].",
								"[npc.Name] presses [npc.her] [npc.lips+] against [npc2.name]'s as [npc.she] continues kissing [npc2.herHim].",
								"Pressing [npc.her] [npc.lips+] against [npc2.name]'s, [npc.name] continues making out with [npc2.herHim].");
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"[npc.Name]'s [npc.sobs+] are muffled into [npc2.name]'s mouth as [npc.she] desperately tries to push away from [npc2.herHim].",
								"[npc.Name] tries to pull [npc.her] [npc.lips+] away from [npc2.name]'s as [npc.she] struggles against [npc2.herHim].",
								"Trying to pull [npc.her] [npc.lips+] away from [npc2.name]'s, [npc.name] continues struggling against the unwanted kiss.");
				}
			}
		}
		
		String orificeName="";
		String penetratorName="";
		
		switch(penetrationType) {
			case FINGER:
				penetratorName = "[npc.fingers]";
				break;
			case PENIS:
				penetratorName = "[npc.penis+]";
				break;
			case TAIL:
				penetratorName = "[npc.tail+]";
				break;
			case TENTACLE:
				break;
			case TONGUE:
				penetratorName = "[npc.tongue]";
				break;
		}
		
		switch(orifice) {
			case ANUS:
				orificeName = "[npc2.asshole+]";
				break;
			case ASS:
				orificeName = "[npc2.ass+]";
				break;
			case MOUTH:
				orificeName = "mouth";
				break;
			case NIPPLE:
				orificeName = "[npc2.nipple+]";
				break;
			case URETHRA:
				break;
			case VAGINA:
				orificeName = "[npc2.pussy+]";
				break;
			case THIGHS:
				orificeName = "thighs";
				break;
			case BREAST:
				orificeName = "[npc2.breasts+]";
				break;
		}
		
		String penetratingQualifier = "";
		String penetratingAction = "";
		
		String penetratingPrefix = "";
		String penetratedPrefix = "";
		String penetratedPostfix = "";
		
		switch(Sex.getSexPace(characterPenetrating)) {
			case DOM_GENTLE:
				penetratingQualifier = UtilText.returnStringAtRandom("gently", "slowly", "steadily");
				penetratingAction = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("slide", "pump", "thrust")
						:UtilText.returnStringAtRandom("slides", "pumps", "thrusts");
				penetratingPrefix = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] drifts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc.Name] lets out [npc.a_moan+]", "[npc.A_moan+] drifts out from between [npc.name]'s [npc.lips+]");
			break;
			case DOM_NORMAL:
				penetratingQualifier = UtilText.returnStringAtRandom("eagerly", "enthusiastically", "readily", "happily");
				penetratingAction = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("slide", "push", "drive", "thrust", "pump")
						:UtilText.returnStringAtRandom("slides", "pushes", "drives", "thrusts", "pumps");
				penetratingPrefix = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] bursts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc.Name] lets out [npc.a_moan+]", "[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+]");
			break;
			case DOM_ROUGH:
				penetratingQualifier = UtilText.returnStringAtRandom("roughly", "forcefully", "mercilessly");
				penetratingAction = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("slam", "hammer", "thrust", "pump", "piston")
						:UtilText.returnStringAtRandom("slams", "hammers", "thrusts", "pumps", "pistons");
				penetratingPrefix = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] bursts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc.Name] lets out [npc.a_moan+]", "[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+]");
			break;
			case SUB_EAGER:
				penetratingQualifier = UtilText.returnStringAtRandom("desperately", "frantically", "eagerly", "enthusiastically");
				penetratingAction = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("slam", "hammer", "thrust", "pump")
						:UtilText.returnStringAtRandom("slams", "hammers", "thrusts", "pumps");
				penetratingPrefix = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] bursts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc.Name] lets out [npc.a_moan+]", "[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+]");
			break;
			case SUB_NORMAL:
				penetratingQualifier = UtilText.returnStringAtRandom("happily", "willingly");
				penetratingAction = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("slide", "push", "drive", "thrust", "pump")
						:UtilText.returnStringAtRandom("slides", "pushes", "drives", "thrusts", "pumps");
				penetratingPrefix = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] drifts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc.Name] lets out [npc.a_moan+]", "[npc.A_moan+] drifts out from between [npc.name]'s [npc.lips+]");
			break;
			case SUB_RESISTING:
				penetratingQualifier = UtilText.returnStringAtRandom("reluctantly", "half-heartedly", "hesitantly");
				penetratingAction = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("slide", "push", "drive")
						:UtilText.returnStringAtRandom("slides", "pushes", "drives");
				penetratingPrefix = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] bursts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc.Name] lets out [npc.a_moan+]", "[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+]");
			break;
		}
		
		switch(Sex.getSexPace(characterPenetrated)) {
			case DOM_GENTLE:
				penetratedPrefix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] drifts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc2.Name] lets out [npc2.a_moan+]", "[npc2.A_moan+] drifts out from between [npc2.name]'s [npc2.lips+]");
				penetratedPostfix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom(" causing you to let out [pc.a_moan+]", " causing [pc.a_moan+] to drift out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom(" causing [npc2.herHim] to let out [npc2.a_moan+]", " causing [npc2.a_moan+] to drift out from between [npc2.her] [npc2.lips+]");
				break;
			case DOM_NORMAL:
				penetratedPrefix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] bursts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc2.Name] lets out [npc2.a_moan+]", "[npc2.A_moan+] bursts out from between [npc2.name]'s [npc2.lips+]");
				penetratedPostfix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom(" causing you to let out [pc.a_moan+]", " causing [pc.a_moan+] to burst out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom(" causing [npc2.herHim] to let out [npc2.a_moan+]", " causing [npc2.a_moan+] to burst out from between [npc2.her] [npc2.lips+]");
				break;
			case DOM_ROUGH:
				penetratedPrefix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] bursts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc2.Name] lets out [npc2.a_moan+]", "[npc2.A_moan+] bursts out from between [npc2.name]'s [npc2.lips+]");
				penetratedPostfix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom(" causing you to let out [pc.a_moan+]", " causing [pc.a_moan+] to burst out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom(" causing [npc2.herHim] to let out [npc2.a_moan+]", " causing [npc2.a_moan+] to burst out from between [npc2.her] [npc2.lips+]");
				break;
			case SUB_EAGER:
				penetratedPrefix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] bursts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc2.Name] lets out [npc2.a_moan+]", "[npc2.A_moan+] bursts out from between [npc2.name]'s [npc2.lips+]");
				penetratedPostfix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom(" causing you to let out [pc.a_moan+]", " causing [pc.a_moan+] to burst out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom(" causing [npc2.herHim] to let out [npc2.a_moan+]", " causing [npc2.a_moan+] to burst out from between [npc2.her] [npc2.lips+]");
				break;
			case SUB_NORMAL:
				penetratedPrefix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] drifts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc2.Name] lets out [npc2.a_moan+]", "[npc2.A_moan+] drifts out from between [npc2.name]'s [npc2.lips+]");
				penetratedPostfix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom(" causing you to let out [pc.a_moan+]", " causing [pc.a_moan+] to drift out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom(" causing [npc2.herHim] to let out [npc2.a_moan+]", " causing [npc2.a_moan+] to drift out from between [npc2.her] [npc2.lips+]");
				break;
			case SUB_RESISTING:
				penetratedPrefix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("You struggle and try to protest", "You attempt to push [npc2.name] away", "You let out a protesting whine")
						:UtilText.returnStringAtRandom("[npc2.Name] struggles and tries to protest", "[npc2.Name] attempts to push you away", "[npc2.Name] lets out a protesting whine");
				penetratedPostfix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom(" causing you to let out [pc.a_moan+]", " causing [pc.a_moan+] to burst out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom(" causing [npc2.herHim] to let out [npc2.a_moan+]", " causing [npc2.a_moan+] to burst out from between [npc2.her] [npc2.lips+]");
				break;
		}
		
		// PC penetrating NPC:
		if(characterPenetrating.isPlayer() && !characterPenetrated.isPlayer()) {
			return UtilText.parse(characterPenetrating, characterPenetrated,
					UtilText.returnStringAtRandom(
					penetratedPrefix+" as you "+penetratingQualifier+" "+penetratingAction+" your "+penetratorName+" in and out of [npc2.her] "+orificeName+".",
					penetratedPrefix+" as you "+penetratingQualifier+" "+penetratingAction+" your "+penetratorName+" deep into [npc2.her] "+orificeName+".",
					"You "+penetratingQualifier+" "+penetratingAction+" your "+penetratorName+" deep into [npc2.name]'s "+orificeName+", "+penetratedPostfix+".",
					"You "+penetratingQualifier+" "+penetratingAction+" your "+penetratorName+" in and out of [npc2.name]'s "+orificeName+", "+penetratedPostfix+"."));
			
		// NPC penetrating PC:
		} else if(!characterPenetrating.isPlayer() && characterPenetrated.isPlayer()) {
			return UtilText.parse(characterPenetrating, characterPenetrated,
					UtilText.returnStringAtRandom(
					penetratingPrefix+" as [npc.she] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" in and out of your "+orificeName+".",
					penetratingPrefix+" as [npc.she] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" deep into your "+orificeName+".",
					"[npc.Name] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" deep into your "+orificeName+", "+penetratedPostfix+".",
					"[npc.Name] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" in and out of your "+orificeName+", "+penetratedPostfix+"."));
			
		// NPC penetrating other NPC:
		} else if(!characterPenetrating.isPlayer() && !characterPenetrated.isPlayer() && !characterPenetrating.equals(characterPenetrated)) {
			return UtilText.parse(characterPenetrating, characterPenetrated,
					UtilText.returnStringAtRandom(
					penetratingPrefix+" as [npc.she] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" in and out of [npc2.name]'s "+orificeName+".",
					penetratingPrefix+" as [npc.she] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" deep into [npc2.name]'s "+orificeName+".",
					"[npc.Name]"+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" deep into [npc2.name]'s "+orificeName+", "+penetratedPostfix+".",
					"[npc.Name]"+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" in and out of [npc2.name]'s "+orificeName+", "+penetratedPostfix+"."));
			
		// PC (dom) self-penetrating:
		} else if(characterPenetrating.isPlayer() && characterPenetrating.equals(characterPenetrated)) {
			return UtilText.parse(characterPenetrating, characterPenetrated,
					UtilText.returnStringAtRandom(
					penetratedPrefix+" as you "+penetratingQualifier+" "+penetratingAction+" your "+penetratorName+" in and out of your "+orificeName+".",
					penetratedPrefix+" as you "+penetratingQualifier+" "+penetratingAction+" your "+penetratorName+" deep into your "+orificeName+".",
					"You "+penetratingQualifier+" "+penetratingAction+" your "+penetratorName+" deep into your "+orificeName+", "+penetratedPostfix+".",
					"You "+penetratingQualifier+" "+penetratingAction+" your "+penetratorName+" in and out of your "+orificeName+", "+penetratedPostfix+"."));
			
		// NPC (sub) self-penetrating:
		} else {
			return UtilText.parse(characterPenetrating, characterPenetrated,
					UtilText.returnStringAtRandom(
					penetratedPrefix+" as [npc.she] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" in and out of [npc.her] "+orificeName+".",
					penetratedPrefix+" as [npc.she] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" deep into [npc.her] "+orificeName+".",
					"[npc.Name] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" deep into [npc.her] "+orificeName+", "+penetratedPostfix+".",
					"[npc.Name] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" in and out of [npc.her] "+orificeName+", "+penetratedPostfix+"."));
		}
		
	}
	
	private String getGenericInitialPenetration(GameCharacter characterPenetrating, PenetrationType penetrationType, GameCharacter characterPenetrated, OrificeType orifice) {
		String penetrationVerb=" slides", penetrationAdverb="";
		
		if(characterPenetrating.isPlayer()) {
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					penetrationAdverb = UtilText.returnStringAtRandom(" slowly", " gently");
					penetrationVerb = UtilText.returnStringAtRandom(" slide", " push", " glide");
					break;
				case DOM_NORMAL:
					penetrationAdverb = "";
					penetrationVerb = UtilText.returnStringAtRandom(" push");
					break;
				case DOM_ROUGH:
					penetrationAdverb = UtilText.returnStringAtRandom(" roughly", " violently", " forcefully");
					penetrationVerb = UtilText.returnStringAtRandom(" slam", " grind");
					break;
				case SUB_EAGER:
					penetrationAdverb = UtilText.returnStringAtRandom(" eagerly", " desperately", " enthusiastically");
					penetrationVerb = UtilText.returnStringAtRandom(" slam", " grind");
					break;
				case SUB_NORMAL:
					penetrationAdverb = "";
					penetrationVerb = UtilText.returnStringAtRandom(" pushes");
					penetrationVerb = UtilText.returnStringAtRandom(" push");
					break;
				case SUB_RESISTING:
					penetrationAdverb = UtilText.returnStringAtRandom(" reluctantly", " hesitantly");
					penetrationVerb = UtilText.returnStringAtRandom(" push");
					break;
			}
		} else {
			switch(Sex.getSexPace(characterPenetrating)) {
				case DOM_GENTLE:
					penetrationAdverb = UtilText.returnStringAtRandom(" slowly", " gently");
					penetrationVerb = UtilText.returnStringAtRandom(" slides", " pushes", " glides");
					break;
				case DOM_NORMAL:
					penetrationAdverb = "";
					penetrationVerb = UtilText.returnStringAtRandom(" pushes");
					break;
				case DOM_ROUGH:
					penetrationAdverb = UtilText.returnStringAtRandom(" roughly", " violently", " forcefully");
					penetrationVerb = UtilText.returnStringAtRandom(" slams", " grinds");
					break;
				case SUB_EAGER:
					penetrationAdverb = UtilText.returnStringAtRandom(" eagerly", " desperately", " enthusiastically");
					penetrationVerb = UtilText.returnStringAtRandom(" slams", " grinds");
					break;
				case SUB_NORMAL:
					penetrationAdverb = "";
					penetrationVerb = UtilText.returnStringAtRandom(" pushes");
					break;
				case SUB_RESISTING:
					penetrationAdverb = UtilText.returnStringAtRandom(" reluctantly", " hesitantly");
					penetrationVerb = UtilText.returnStringAtRandom(" pushes");
					break;
			}
		}
		
		if(characterPenetrating.isPlayer()) {
			return "You let out [pc.a_moan+] as you"+penetrationAdverb+penetrationVerb+" your "
					+penetrationType.getName(characterPenetrating)+" into "+(characterPenetrated.isPlayer()?"your ":"[npc.name]'s ")+orifice.getName(characterPenetrated)+".";
		} else {
			return "[npc.Name] lets out [npc.a_moan+] as [npc.she]"+penetrationAdverb+penetrationVerb+" [npc.her] "
					+penetrationType.getName(characterPenetrating)+" into "+(characterPenetrated.isPlayer()?"your ":"[npc.her] ")+orifice.getName(characterPenetrated)+".";
		}
	}
	
	public String getPenetrationDescription(boolean initialPenetration, GameCharacter characterPenetrating, PenetrationType penetrationType, GameCharacter characterPenetrated, OrificeType orifice) {
		
		if(orifice == OrificeType.ANUS) {
			if(characterPenetrated.isPlayer()) {
				if(initialPenetration) {
					switch(penetrationType) {
						case PENIS:
							if(!characterPenetrating.isPlayer()) {
								switch(getPenisType()){
									case ANGEL:
										break;
									case AVIAN:
										break;
									case BOVINE:
										return "You let out [pc.a_moan+] as you feel [npc.name]'s cow-like cock push into your [pc.asshole+].";
									case CANINE:
										return "You let out [pc.a_moan+] as you feel [npc.name]'s dog-like cock push into your [pc.asshole+].";
									case LUPINE:
										return "You let out [pc.a_moan+] as you feel [npc.name]'s wolf-like cock push into your [pc.asshole+].";
									case ALLIGATOR_MORPH:
										return "You let out [pc.a_moan+] as you feel [npc.name]'s reptile-like cock push into your [pc.asshole+].";
									case SQUIRREL:
										return "You let out [pc.a_moan+] as you feel [npc.name]'s squirrel-like cock push into your [pc.asshole+].";
									case DEMON_COMMON:
										return "You let out [pc.a_moan+] as you feel the little bumps lining [npc.name]'s demonic cock wriggling against the walls of your [pc.asshole] as [npc.she] penetrates you.";
									case EQUINE:
										return "You let out [pc.a_moan+] as you feel the flared head of [npc.name]'s horse-like cock push into your [pc.asshole+].";
									case REINDEER_MORPH:
										return "You let out [pc.a_moan+] as you feel the flared head of [npc.name]'s reindeer-like cock push into your [pc.asshole+].";
									case FELINE:
										return "You let out [pc.a_moan+] as you feel the barbs lining [npc.name]'s cat-like cock rake the walls of your [pc.asshole+] as [npc.she] starts to pull back.";
									case HUMAN:
										break;
									case NONE:
										break;
									case SLIME:
										break;
								}
							}
							break;
						
						default:
							break;
					}
					return getGenericInitialPenetration(characterPenetrating, penetrationType, characterPenetrated, orifice);
				}
				
				return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
			
			} else {
				if(initialPenetration) {
					switch(penetrationType) {
						case PENIS:
							if(characterPenetrating.isPlayer()) {
								switch(getAssType()){
									case DEMON_COMMON:
										return "As the [pc.cockHead+] of your [pc.cock+] pushes its way into [npc.name]'s demonic, pussy-like [npc.asshole],"
												+ " you feel a series of little writhing tentacles start to massage and stroke your throbbing length.";
									default:
										break;
								}
							}
							break;
						
						default:
							break;
					}
					return getGenericInitialPenetration(characterPenetrating, penetrationType, characterPenetrated, orifice);
				}
				
				return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
				
			}
			
		} else if(orifice == OrificeType.VAGINA) {

			if(characterPenetrated.isPlayer()) {
				if(initialPenetration) {
					switch(penetrationType) {
						case PENIS:
							if(!characterPenetrating.isPlayer()) {
								switch(getPenisType()){
									case ANGEL:
										break;
									case AVIAN:
										break;
									case BOVINE:
										return "You let out [pc.a_moan+] as you feel [npc.name]'s cow-like cock push into your [pc.pussy+].";
									case CANINE:
										return "You let out [pc.a_moan+] as you feel [npc.name]'s dog-like cock push into your [pc.pussy+].";
									case LUPINE:
										return "You let out [pc.a_moan+] as you feel [npc.name]'s wolf-like cock push into your [pc.pussy+].";
									case ALLIGATOR_MORPH:
										return "You let out [pc.a_moan+] as you feel [npc.name]'s reptile-like cock push into your [pc.pussy+].";
									case SQUIRREL:
										return "You let out [pc.a_moan+] as you feel [npc.name]'s squirrel-like cock push into your [pc.pussy+].";
									case DEMON_COMMON:
										return "You let out [pc.a_moan+] as you feel the little bumps lining [npc.name]'s demonic cock wriggling against the walls of your [pc.pussy] as [npc.she] penetrates you.";
									case EQUINE:
										return "You let out [pc.a_moan+] as you feel the flared head of [npc.name]'s horse-like cock push into your [pc.pussy+].";
									case REINDEER_MORPH:
										return "You let out [pc.a_moan+] as you feel the flared head of [npc.name]'s reindeer-like cock push into your [pc.pussy+].";
									case FELINE:
										return "You let out [pc.a_moan+] as you feel the barbs lining [npc.name]'s cat-like cock rake the walls of your [pc.pussy+] as [npc.she] starts to pull back.";
									case HUMAN:
										break;
									case NONE:
										break;
									case SLIME:
										break;
								}
							}
							break;
						
						default:
							break;
					}
					return getGenericInitialPenetration(characterPenetrating, penetrationType, characterPenetrated, orifice);
				}
				
				return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
				
			} else {
				
				if(initialPenetration) {
					switch(penetrationType) {
						case PENIS:
							if(characterPenetrating.isPlayer()) {
								switch(getVaginaType()){
									case DEMON_COMMON:
										return "As the [pc.cockHead+] of your [pc.cock+] pushes its way into [npc.name]'s demonic [npc.pussy], you feel a series of little writhing tentacles start to massage and stroke your throbbing length.";
									default:
										break;
								}
							}
							break;
						
						default:
							break;
					}
					return getGenericInitialPenetration(characterPenetrating, penetrationType, characterPenetrated, orifice);
				}
				
				return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
				
			}
			
		} else if(orifice == OrificeType.NIPPLE) {

			if(characterPenetrated.isPlayer()) {
				if(initialPenetration) {
					switch(penetrationType) {
						case PENIS:
							if(!characterPenetrating.isPlayer()) {
								switch(getPenisType()){
									case ANGEL:
										break;
									case AVIAN:
										break;
									case BOVINE:
										return "You let out [pc.a_moan+] as you feel [npc.name]'s cow-like cock push into your [pc.nipple+].";
									case CANINE:
										return "You let out [pc.a_moan+] as you feel [npc.name]'s dog-like cock push into your [pc.nipple+].";
									case LUPINE:
										return "You let out [pc.a_moan+] as you feel [npc.name]'s wolf-like cock push into your [pc.nipple+].";
									case ALLIGATOR_MORPH:
										return "You let out [pc.a_moan+] as you feel [npc.name]'s reptile-like cock push into your [pc.nipple+].";
									case SQUIRREL:
										return "You let out [pc.a_moan+] as you feel [npc.name]'s squirrel-like cock push into your [pc.nipple+].";
									case DEMON_COMMON:
										return "You let out [pc.a_moan+] as you feel the little bumps lining [npc.name]'s demonic cock wriggling against the walls of your fuckable [pc.nipple] as [npc.she] penetrates you.";
									case EQUINE:
										return "You let out [pc.a_moan+] as you feel the flared head of [npc.name]'s horse-like cock push into your [pc.nipple+].";
									case REINDEER_MORPH:
										return "You let out [pc.a_moan+] as you feel the flared head of [npc.name]'s reindeer-like cock push into your [pc.nipple+].";
									case FELINE:
										return "You let out [pc.a_moan+] as you feel the barbs lining [npc.name]'s cat-like cock rake the walls of your [pc.nipple+] as [npc.she] starts to pull back.";
									case HUMAN:
										break;
									case NONE:
										break;
									case SLIME:
										break;
								}
							}
							break;
						
						default:
							break;
					}
					return getGenericInitialPenetration(characterPenetrating, penetrationType, characterPenetrated, orifice);
				}
				
				return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
				
			} else {
				
				if(initialPenetration) {
					switch(penetrationType) {
						case PENIS:
							if(characterPenetrating.isPlayer()) {
								switch(getBreastType()){
									case DEMON_COMMON:
										return "As the [pc.cockHead+] of your [pc.cock+] pushes its way into [npc.name]'s [npc.nipples+], you feel a series of little writhing tentacles start to massage and stroke your throbbing length.";
									default:
										break;
								}
							}
							break;
						
						default:
							break;
					}
					return getGenericInitialPenetration(characterPenetrating, penetrationType, characterPenetrated, orifice);
				}
				
				return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
				
			}
		} else if(orifice == OrificeType.MOUTH) {

			if(characterPenetrated.isPlayer()) {
				if(initialPenetration) {
					switch(penetrationType) {
						case PENIS:
							if(!characterPenetrating.isPlayer()) {
								switch(getPenisType()){
									case ANGEL:
										break;
									case AVIAN:
										break;
									case BOVINE:
										return "You let out a muffled [pc.moan] as [npc.name]'s cow-like cock pushes its way into your mouth.";
									case CANINE:
										return "You let out a muffled [pc.moan] as [npc.name]'s dog-like cock pushes its way into your mouth.";
									case LUPINE:
										return "You let out a muffled [pc.moan] as [npc.name]'s wolf-like cock pushes its way into your mouth.";
									case ALLIGATOR_MORPH:
										return "You let out a muffled [pc.moan] as [npc.name]'s reptile-like cock pushes its way into your mouth.";
									case SQUIRREL:
										return "You let out a muffled [pc.moan] as [npc.name]'s squirrel-like cock pushes its way into your mouth.";
									case DEMON_COMMON:
										return "You let out a muffled [pc.moan] as you feel the little bumps lining [npc.name]'s demonic cock wriggling against your [pc.tongue+] as you start sucking [npc.herHim] off.";
									case EQUINE:
										return "You let out a muffled [pc.moan] as you feel the flared head of [npc.name]'s horse-like cock push its way into your mouth.";
									case REINDEER_MORPH:
										return "You let out a muffled [pc.moan] as you feel the flared head of [npc.name]'s reindeer-like cock push its way into your mouth.";
									case FELINE:
										return "You let out a muffled [pc.moan] as you feel the barbs lining [npc.name]'s cat-like cock rake over your [pc.tongue] as [npc.she] starts to pull back.";
									case HUMAN:
										break;
									case NONE:
										break;
									case SLIME:
										break;
								}
							}
							break;
						
						default:
							break;
					}
					return getGenericInitialPenetration(characterPenetrating, penetrationType, characterPenetrated, orifice);
				}
				
				return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
				
			} else {
				if(penetrationType!=PenetrationType.TONGUE && !characterPenetrated.isPlayer()) {
					characterPenetrated.getPlayerKnowsAreas().add(CoverableArea.MOUTH);
				}
				
				if(initialPenetration) {
					switch(penetrationType) {
						case PENIS:
							if(characterPenetrating.isPlayer()) {
								switch(Main.game.getPlayer().getPenisType()){
									case ANGEL:
										break;
									case AVIAN:
										break;
									case BOVINE:
										return "[npc.Name] lets out a muffled [npc.moan] as your cow-like cock pushes its way into [npc.her] mouth.";
									case CANINE:
										return "[npc.Name] lets out a muffled [npc.moan] as your dog-like cock pushes its way into [npc.her] mouth.";
									case LUPINE:
										return "[npc.Name] lets out a muffled [npc.moan] as your wolf-like cock pushes its way into [npc.her] mouth.";
									case ALLIGATOR_MORPH:
										return "[npc.Name] lets out a muffled [npc.moan] as your reptile-like cock pushes its way into [npc.her] mouth.";
									case SQUIRREL:
										return "[npc.Name] lets out a muffled [npc.moan] as your squirrel-like cock pushes its way into [npc.her] mouth.";
									case DEMON_COMMON:
										return "[npc.Name] lets out a muffled [npc.moan] as [npc.she] feels the little bumps lining your demonic cock wriggling against [npc.her] [npc.tongue+] as [npc.she] starts sucking you off.";
									case EQUINE:
										return "[npc.Name] lets out a muffled [npc.moan] as [npc.she] feels the flared head of your horse-like cock push its way into [npc.her] mouth.";
									case REINDEER_MORPH:
										return "[npc.Name] lets out a muffled [npc.moan] as [npc.she] feels the flared head of your reindeer-like cock push its way into [npc.her] mouth.";
									case FELINE:
										return "[npc.Name] lets out a muffled [npc.moan] as [npc.she] feels the barbs lining your cat-like cock rake over [npc.her] [npc.tongue] as you start to thrust in and out of [npc.her] mouth.";
									case HUMAN:
										break;
									case NONE:
										break;
									case SLIME:
										break;
								}
							}
							break;
						
						default:
							break;
					}
					return getGenericInitialPenetration(characterPenetrating, penetrationType, characterPenetrated, orifice);
				}
				
				return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
				
			}
		} else if(orifice == OrificeType.THIGHS) {

			if(initialPenetration) {
				return getGenericInitialPenetration(characterPenetrating, penetrationType, characterPenetrated, orifice);
			}
			
			return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
			
		}
		
		return "";
	}
	
	public String getStopPenetrationDescription(GameCharacter characterPenetrating, PenetrationType penetrationType, GameCharacter characterPenetrated, OrificeType orifice) {
		String orificeName = "", penetrationName = "";

		switch(penetrationType) {
			case FINGER:
				penetrationName = "[npc.fingers]";
				break;
			case PENIS:
				penetrationName = "[npc.cock+]";
				break;
			case TAIL:
				penetrationName = "[npc.tail+]";
				break;
			case TENTACLE:
				penetrationName = "tentacle";
				break;
			case TONGUE:
				penetrationName = "[npc.tongue+]";
				break;
		}
		
		switch(orifice) {
			case ANUS:
				orificeName = "[npc2.asshole+]";
				break;
			case ASS:
				orificeName = "[npc2.ass+]";
				break;
			case MOUTH:
				orificeName = "mouth";
				break;
			case NIPPLE:
				orificeName = "[npc2.nipple+]";
				break;
			case URETHRA:
				orificeName = "urethra";
				break;
			case VAGINA:
				orificeName = "[npc2.pussy+]";
				break;
			case THIGHS:
				orificeName = "thighs";
				break;
			case BREAST:
				orificeName = "[npc2.breasts+]";
				break;
		}
		
		
		if(characterPenetrating.isPlayer()) {
			if(characterPenetrated.isPlayer()) {
				return UtilText.parse(characterPenetrating, characterPenetrated,
						"You slide your "+penetrationName+" out of your "+orificeName+".");
			} else {
				return UtilText.parse(characterPenetrating, characterPenetrated,
						"You slide your "+penetrationName+" out of [npc2.name]'s "+orificeName+".");
			}
			
		} else {
			if(characterPenetrated.isPlayer()) {
				return UtilText.parse(characterPenetrating, characterPenetrated,
						"[npc.Name] slides [npc.her] "+penetrationName+" out of your "+orificeName+".");
			} else if(characterPenetrating.equals(characterPenetrated)) {
				return UtilText.parse(characterPenetrating, characterPenetrated,
						"[npc.Name] slides [npc.her] "+penetrationName+" out of [npc.her] "+orificeName+".");
			} else {
				return UtilText.parse(characterPenetrating, characterPenetrated,
						"[npc.Name] slides [npc.her] "+penetrationName+" out of [npc2.name]'s "+orificeName+".");
			}
		}
	}
	
	
	private static StringBuilder StringBuilderSB = new StringBuilder();
	
	public String getVirginityLossOrificeDescription(GameCharacter characterPenetrating, PenetrationType penetrationType, GameCharacter characterPenetrated, OrificeType orifice) {
		StringBuilderSB.setLength(0);
		
		switch(orifice) {
			case ANUS:
				if(characterPenetrated.isPlayer()) {
					StringBuilderSB.append(getPlayerAnalVirginityLossDescription(characterPenetrating, penetrationType));
				} else {
					StringBuilderSB.append(getPartnerAnalVirginityLossDescription(characterPenetrating, penetrationType));
				}
				break;
			case MOUTH:
				if(characterPenetrated.isPlayer()) {
					StringBuilderSB.append(getPlayerMouthVirginityLossDescription(characterPenetrating, penetrationType));
				} else {
					StringBuilderSB.append(getPartnerMouthVirginityLossDescription(characterPenetrating, penetrationType));
				}
				break;
			case NIPPLE:
				if(characterPenetrated.isPlayer()) {
					StringBuilderSB.append(getPlayerNippleVirginityLossDescription(characterPenetrating, penetrationType));
				} else {
					StringBuilderSB.append(getPartnerNippleVirginityLossDescription(characterPenetrating, penetrationType));
				}
				break;
			case URETHRA:
				if(characterPenetrated.isPlayer()) {
					StringBuilderSB.append(getPlayerUrethraVirginityLossDescription(characterPenetrating, penetrationType));
				} else {
					StringBuilderSB.append(getPartnerUrethraVirginityLossDescription(characterPenetrating, penetrationType));
				}
				break;
			case VAGINA:
				if(characterPenetrated.isPlayer()) {
					StringBuilderSB.append(getPlayerVaginaVirginityLossDescription(characterPenetrating, penetrationType));
				} else {
					StringBuilderSB.append(getPartnerVaginaVirginityLossDescription(characterPenetrating, penetrationType));
				}
				break;
			case ASS: case BREAST: case THIGHS:
				// Don't have a virginity to lose.
				break;
		}
		return StringBuilderSB.toString();
	}
	
	public String getVirginityLossPenetrationDescription(GameCharacter characterPenetrating, PenetrationType penetrationType, GameCharacter characterPenetrated, OrificeType orifice) {
		StringBuilderSB.setLength(0);
		
		switch(penetrationType) {
			case PENIS:
				if(characterPenetrating.isPlayer()) {
					if(Main.game.getPlayer().isPenisVirgin()) {
						StringBuilderSB.append(getPlayerPenileVirginityLossDescription(characterPenetrated, orifice));
					}
				} else {
					if(this.isPenisVirgin()) {
						StringBuilderSB.append(getPartnerPenileVirginityLossDescription(characterPenetrated, orifice));
					}
				}
				break;
			case FINGER: case TAIL: case TENTACLE: case TONGUE:
				// Don't have a virginity to lose.
				break;
		}
		
		return StringBuilderSB.toString();
	}
	
	protected String formatVirginityLoss(String rawInput) {
		return UtilText.formatVirginityLoss(rawInput);
	}
	
	protected String losingPureVirginity(GameCharacter characterPenetrating, PenetrationType penetrationType){
		if(characterPenetrating.isPlayer()) {
			return UtilText.parse(this,
					"<p style='text-align:center;'>"
						+ "<b style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Broken Virgin</b>"
					+ "</p>"
					+ "<p>"
						+ "You can't quite believe what you're doing to yourself."
						+ " As your "+(Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.VAGINA).getName(characterPenetrating))+" takes your own virginity in a single thrust, you find yourself letting out a desperate gasp."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[pc.thought(W-What am I doing?!</br>"
								+ "I-I'm just so... <i>horny</i>!</br>"
								+ "I-I can't help myself!)]"
					+ "</p>"
					+ "<p>"
						+ "You don't quite know how to react to your own actions."
						+ " The virginity that you prized so highly is now gone, and a vacant gaze settles over your face as your [pc.pussy+] spreads lewdly around your own "+penetrationType.getName(characterPenetrating)+"."
					+ "</p>"
					+ "<p>"
						+ "While you were a virgin, you felt invincible."
						+ " As though you could overcome any obstacle that was placed in your way."
						+ " Now, however..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
					+ "[pc.thought(Now I'm just some regular old slut...</br>"
							+ "So turned on that I choose to fuck myself...</br>"
							+ "All I'm good for now is being a worthless fuck-toy...)]"
					+ "</p>"
					+ "<p>"
						+ "With a shuddering sigh, you decide to resign yourself to the fact that now you're nothing more than a <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>broken virgin</b>..."
					+ "</p>");
			
		} else {
			return UtilText.parse(this,
					"<p style='text-align:center;'>"
						+ "<b style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Broken Virgin</b>"
					+ "</p>"
					+ "<p>"
						+ "You can't believe what's happening."
						+ " As [npc.name]'s "+(Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.VAGINA).getName(characterPenetrating))
						+" takes your virginity in a single thrust, you find yourself letting out a desperate gasp."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[pc.thought(This is how I lose my virginity?!</br>"
								+ "To... <i>[npc.a_race]</i>?!</br>"
								+ "This can't be happening!)]"
					+ "</p>"
					+ "<p>"
						+ "You don't quite know how to react."
						+ " The virginity that you prized so highly has been suddenly taken from you, and a vacant gaze settles over your face as your [pc.pussy+] spreads lewdly around the intruding object."
					+ "</p>"
					+ "<p>"
						+ "While you were a virgin, you felt invincible."
						+ " As though you could overcome any obstacle that was placed in your way."
						+ " Now, however..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
					+ "[pc.thought(Now I'm just some regular old slut...</br>"
							+ "Getting fucked by any random person I meet...</br>"
							+ "All I'm good for now is being the next lucky guy's fuck-toy...)]"
					+ "</p>"
					+ "<p>"
					+ "You're vaguely aware of [npc.name] grunting away somewhere in the background, completely oblivious to how hard you've been hit by the loss of your virginity."
					+ " With a shuddering sigh, you decide to resign yourself to the fact that now you're nothing more than a <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>broken virgin</b>..."
					+ "</p>");
		}
	}
	
	// Virginity loss:
	
	private String getPlayerAnalVirginityLossDescription(GameCharacter characterPenetrating, PenetrationType penetration){
		StringBuilderSB = new StringBuilder();
		
		boolean isPenis = penetration == PenetrationType.PENIS;
		boolean isTail = penetration == PenetrationType.TAIL;
		
		if(characterPenetrating.isPlayer()) { // SELF-PENETRATION
			// Initial penetration:
			if(Sex.getWetOrificeTypes(Main.game.getPlayer()).get(OrificeType.ANUS).isEmpty()) {
				// Dry:
				StringBuilderSB.append(
						"<p>"
							+ "You let out a painful cry as you force your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")+" into your dry [pc.asshole]."
							+ " Squirming and shuffling in discomfort, your cries grow louder and louder as you start fucking your own [pc.ass]; the lack of lubrication turning your first anal experience into one of mind-numbing agony."
						+ "</p>");
				
			} else {
				 // Wet:
				StringBuilderSB.append(
						"<p>"
							+ "You let out a painful cry as you force your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")+" into your [pc.asshole+]."
							+ " Squirming and shuffling in discomfort, you continue letting out little whimpers as you start fucking your own [pc.ass]."
							+ " Thankfully, your [pc.asshole] was lubricated beforehand, and you dread to think of how painful your first anal experience would have been otherwise."
						+ "</p>");
			}
			
			// Player masochist reaction:
			if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
				StringBuilderSB.append(
						"<p>"
							+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
							+ " The pain and discomfort at the feeling of losing your anal virginity is pure bliss, and you soon find yourself [pc.moaning] in a delightful haze of overwhelming ecstasy."
						+ "</p>");
			} else {
				StringBuilderSB.append(
						"<p>"
							+ "With tears welling up in your [pc.eyes], you let out another painful wail as you draw"+(isTail?" your [pc.tail]":"")+" back, before thrusting deep inside yourself once again."
							+ " This time, the pain isn't as extreme as before, and you realise that you're starting to get used to the feeling of using your own ass."
						+ "</p>");
			}
			
			// Ending:
			StringBuilderSB.append(
					"<p>"
						+ "The throbbing, painful ache in your [pc.ass] slowly starts to fade away, and as your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")
							+" pushes into your [pc.asshole+] once again, you let out a little whimper of relief as you feel that there's no accompanying stab of pain."
					+ "</p>");
			
		} else { // PARTNER PENETRATION
			// Initial penetration:
			if(Sex.getWetOrificeTypes(Main.game.getPlayer()).get(OrificeType.ANUS).isEmpty()) {
				// Dry:
				StringBuilderSB.append(
						"<p>"
							+ "You let out a painful cry as you feel [npc.name]'s "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")+" push into your dry [pc.asshole]."
							+ " Squirming and shuffling in discomfort, your cries grow louder and louder as [npc.name] starts fucking your [pc.ass]; the lack of lubrication turning your first anal experience into one of mind-numbing agony."
						+ "</p>");
				
			} else {
				 // Wet:
				StringBuilderSB.append(
						"<p>"
							+ "You let out a painful cry as you feel [npc.name]'s "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")+" push into your [pc.asshole+]."
							+ " Squirming and shuffling in discomfort, you continue letting out little whimpers as [npc.name] starts fucking your [pc.ass]."
							+ " Thankfully, your [pc.asshole] was lubricated beforehand, and you dread to think of how painful your first anal experience would have been otherwise."
						+ "</p>");
			}
			
			// Player masochist reaction:
			if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
				StringBuilderSB.append(
						"<p>"
							+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
							+ " The pain and discomfort at the feeling of losing your anal virginity is pure bliss, and you soon find yourself [pc.moaning] in a delightful haze of overwhelming ecstasy."
						+ "</p>");
			}
			
			// Partner sadistic reaction:
			if(this.hasFetish(Fetish.FETISH_SADIST)) {
				StringBuilderSB.append(
						"<p>"
							+ "With tears welling up in your [pc.eyes], you let out another painful wail as [npc.name] draws"+(isTail?" [npc.her] [npc.tail]":"")+" back, before violently thrusting deep inside you once again."
							+ " [npc.She] lets out an evil laugh as [npc.she] causes you to writhe about in pain, [npc.her] sadistic nature fuelling [npc.her] rough thrusts into your [pc.asshole] as [npc.she] ruthlessly fucks your [pc.ass]."
						+ "</p>");
			} else {
				StringBuilderSB.append(
						"<p>"
							+ "With tears welling up in your [pc.eyes], you let out another painful wail as [npc.name] draws"+(isTail?" [npc.her] [npc.tail]":"")+" back, before thrusting deep inside you once again."
							+ " This time, the pain isn't as extreme as before, and you realise that you're starting to get used to the feeling of being fucked in the ass."
						+ "</p>");
			}
			
			// Partner deflowering reaction:
			if(this.hasFetish(Fetish.FETISH_DEFLOWERING)) {
				StringBuilderSB.append(
						"<p>"
							+ "[npc.speech(Oh, yes!)] [npc.she] cries, [npc.speech(Good [pc.girl], saving your anal virginity for me!"
								+ " Remember this moment, remember that <i>my</i> "+(isPenis?"cock":"")+(isTail?"tail":"")+" was the the one that turned you into "+(Main.game.getPlayer().isFeminine()?"a horny buttslut":"a little fucktoy")+"!)]"
						+ "</p>");
			}
			
			// Ending:
			StringBuilderSB.append(
					"<p>"
						+ "The throbbing, painful ache in your [pc.ass] slowly starts to fade away, and as [npc.name]'s "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")
							+" pushes into your [pc.asshole+] once again, you let out a little whimper of relief as you feel that there's no accompanying stab of pain."
					+ "</p>");
		}
		
		
		StringBuilderSB.append(formatVirginityLoss("You'll always remember this moment as the time that you lost your anal virginity!"));
		
		return StringBuilderSB.toString();
	}
	
	
	
	protected String getPlayerVaginaVirginityLossDescription(GameCharacter characterPenetrating, PenetrationType penetration){
		StringBuilderSB = new StringBuilder();
		
		boolean isPenis = penetration == PenetrationType.PENIS;
		boolean isTail = penetration == PenetrationType.TAIL;
		
		if(characterPenetrating.isPlayer()) { // SELF-PENETRATION
			// Initial penetration:
			if(Sex.getWetOrificeTypes(Main.game.getPlayer()).get(OrificeType.VAGINA).isEmpty()) {
				// Dry:
				StringBuilderSB.append(
						"<p>"
							+ "As you drive your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")+" deep into your dry [pc.pussy], your vision suddenly explodes in stars, and a painful, high-pitched shriek escapes from between your lips."
							+ " Being penetrated without any form of lubrication would be uncomfortable at the best of times, but due to the fact that you're still a virgin, it's somewhat more than just a little discomfort,"
								+ " and your shriek turns into a shuddering cry as you shuffle about in pure agony."
						+ "</p>");
				
			} else {
				 // Wet:
				StringBuilderSB.append(
							"<p>"
								+ "As you drive your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")+" deep into your [pc.pussy+], your vision suddenly narrows down, and a painful, desperate wail escapes from between your lips."
								+ " Luckily, your pussy was lubricated before being penetrated, but due to the fact that you're still a virgin, it isn't enough to completely prevent the pain you now feel between your legs,"
									+ " and your wail turns into a shuddering moan as you shuffle about in discomfort."
							+ "</p>");
			}
			
			// Player masochist reaction:
			if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
				StringBuilderSB.append(
						"<p>"
							+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
							+ " The agony between your legs is pure bliss, and you focus on the pain as you squeal and moan in a delightful haze of overwhelming ecstasy."
						+ "</p>");
			}else {
				StringBuilderSB.append(
						"<p>"
							+ "Instinctively trying to clench your legs together, you let out another painful wail as you draw"+(isTail?" your [pc.tail]":"")+" back, before thrusting deep inside yourself once again."
							+ " This time, the pain isn't as extreme as before, and you realise that the initial hurt was due to your hymen being torn."
						+ "</p>");
			}
			
			// Ending:
			if (Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
				StringBuilderSB.append(
						"<p>"
							+ "As the pain recedes into a dull, throbbing ache between your legs, you feel a little trickle of blood running out of your now-broken-in pussy, and you can't help but let out yet another whimpering cry."
							+ " The throbbing, painful ache in your groin slowly starts to fade away, and as you push your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")
								+" into your [pc.pussy+] once again, you let out a sigh of relief as you feel that there's no accompanying stab of pain."
						+ "</p>");
			} else {
				StringBuilderSB.append(
						"<p>"
							+ "The throbbing, painful ache in your groin slowly starts to fade away, and as you push your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")
								+" into your [pc.pussy+] once again, you let out a sigh of relief as you feel that there's no accompanying stab of pain."
						+ "</p>");
			}
			
		} else { // PARTNER PENETRATION
			// Initial penetration:
			if(Sex.getWetOrificeTypes(Main.game.getPlayer()).get(OrificeType.VAGINA).isEmpty()) {
				// Dry:
				StringBuilderSB.append(
						"<p>"
							+ "As [npc.name]'s "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")+" drives deep into your dry [pc.pussy], your vision suddenly explodes in stars, and a painful, high-pitched shriek escapes from between your lips."
							+ " Being penetrated without any form of lubrication would be uncomfortable at the best of times, but due to the fact that you're still a virgin, it's somewhat more than just a little discomfort,"
								+ " and your shriek turns into a shuddering cry as you shuffle about in pure agony."
						+ "</p>");
				
			} else {
				 // Wet:
				StringBuilderSB.append(
							"<p>"
								+ "As [npc.name]'s "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")+" drives deep into your [pc.pussy+], your vision suddenly narrows down, and a painful, desperate wail escapes from between your lips."
								+ " Luckily, your pussy was lubricated before being penetrated, but due to the fact that you're still a virgin, it isn't enough to completely prevent the pain you now feel between your legs,"
									+ " and your wail turns into a shuddering moan as you shuffle about in discomfort."
							+ "</p>");
			}
			
			// Player masochist reaction:
			if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
				StringBuilderSB.append(
						"<p>"
							+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
							+ " The agony between your legs is pure bliss, and you focus on the pain as you squeal and moan in a delightful haze of overwhelming ecstasy."
						+ "</p>");
			}
			
			// Partner sadistic reaction:
			if(this.hasFetish(Fetish.FETISH_SADIST)) {
				StringBuilderSB.append(
						"<p>"
							+ "Trying desperately to clench your legs together, you let out another painful wail as [npc.name] draws"+(isTail?" [npc.her] [npc.tail]":"")+" back, before violently thrusting deep inside you once again."
							+ " [npc.She] lets out an evil laugh as [npc.she] causes you to writhe about in pain, [npc.her] sadistic nature fuelling [npc.her] rough thrusts into your pussy as [npc.she] ruthlessly tears through your hymen."
						+ "</p>");
			} else {
				StringBuilderSB.append(
						"<p>"
							+ "Trying desperately to clench your legs together, you let out another painful wail as [npc.name] draws"+(isTail?" [npc.her] [npc.tail]":"")+" back, before thrusting deep inside you once again."
							+ " This time, the pain isn't as extreme as before, and you realise that the initial hurt was due to your hymen being torn."
						+ "</p>");
			}
			
			// Partner deflowering reaction:
			if(this.hasFetish(Fetish.FETISH_DEFLOWERING)) {
				StringBuilderSB.append(
						"<p>"
							+ "[npc.speech(Oh, yes!)] [npc.she] cries, [npc.speech(Good [pc.girl], saving your virginity for me!"
								+ " Remember this moment, remember that <i>my</i> "+(isPenis?"cock":"")+(isTail?"tail":"")+" was the the one that broke you in!)]"
						+ "</p>");
			}
			
			// Ending:
			if (Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
				StringBuilderSB.append(
						"<p>"
							+ "As the pain recedes into a dull, throbbing ache between your legs, you feel a little trickle of blood running out of your now-broken-in pussy, and you can't help but let out yet another whimpering cry."
							+ " The throbbing, painful ache in your groin slowly starts to fade away, and as [npc.name]'s "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")
								+" pushes into your [pc.pussy+] once again, you let out a sigh of relief as you feel that there's no accompanying stab of pain."
						+ "</p>");
			} else {
				StringBuilderSB.append(
						"<p>"
							+ "The throbbing, painful ache in your groin slowly starts to fade away, and as [npc.name]'s "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")
								+" pushes into your [pc.pussy+] once again, you let out a sigh of relief as you feel that there's no accompanying stab of pain."
						+ "</p>");
			}
		}
		
		
		StringBuilderSB.append(formatVirginityLoss("Your hymen has been torn; you have lost your virginity!"));
		
		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
			StringBuilderSB.append(losingPureVirginity(characterPenetrating, penetration));
		}
		
		return StringBuilderSB.toString();
	}
	
//		public String getPlayerVaginaVirginityLossDescription(boolean isPlayerDom){
//			VelocityContext context = new VelocityContext();
//	        context.put("player", Main.game.getPlayer());
//	        context.put("game", Main.game);
//	        context.put("partner", Sex.getPartner());
//	        context.put("dominant", isPlayerDom);
//	        context.put("penetratedBy", Sex.playerPenetratedBy("VAGINA"));
//	        context.put("playerVaginaWet", Sex.isPlayerWet("VAGINA"));
//	        context.put("txt", UtilText.class);
//	        return UtilText.parse("/res/txt/dialogue/sex/Generic/PlayerVaginaVirginityLossDescription.txt", context);
//		}
	
	private String getPlayerPenileVirginityLossDescription(GameCharacter characterPenetrated, OrificeType orifice){
		return formatVirginityLoss("You'll always remember this moment as the time that you lost your penile virginity!");
	}
	
	private String getPlayerNippleVirginityLossDescription(GameCharacter characterPenetrating, PenetrationType penetration){
		return formatVirginityLoss("You'll always remember this moment as the time that you lost your nipple virginity!");
	}
	
	private String getPlayerUrethraVirginityLossDescription(GameCharacter characterPenetrating, PenetrationType penetration){
		return formatVirginityLoss("You have lost your urethral virginity!");
	}
	
	private String getPlayerMouthVirginityLossDescription(GameCharacter characterPenetrating, PenetrationType penetration){
		if(penetration == PenetrationType.PENIS) {
			if(characterPenetrating.isPlayer()) {
				return formatVirginityLoss("You'll always remember this moment as the first time that you sucked your own cock!");
			} else {
				return formatVirginityLoss("You'll always remember this moment as the first time that you sucked a cock!");
			}
			
		} else if(penetration == PenetrationType.TAIL) {
			if(characterPenetrating.isPlayer()) {
				return formatVirginityLoss("You'll always remember this moment as the first time that you sucked your tail!");
			} else {
				return formatVirginityLoss("You'll always remember this moment as the first time that you sucked someone's tail!");
			}
			
		} else {
			return formatVirginityLoss("You'll always remember this moment as the first time that you took something down your throat!");
		}
	}
	//TODO all these
	private String getPartnerPenileVirginityLossDescription(GameCharacter characterPenetrated, OrificeType orifice){
		return formatVirginityLoss("You have taken [npc.name]'s penile virginity!")
				+(Main.game.getPlayer().hasFetish(Fetish.FETISH_DEFLOWERING)
						?"<p style='text-align:center;><i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Due to your deflowering fetish, you gain</i>"
						+ " <i style='color:"+Colour.GENERIC_EXPERIENCE.toWebHexString()+";'>"+Fetish.getExperienceGainFromTakingOtherVirginity(Main.game.getPlayer())+"</i>"
						+ " <i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>experience!</i></p>"
						:"");
	}
	
	private String getPartnerAnalVirginityLossDescription(GameCharacter characterPenetrating, PenetrationType penetration){
		if(Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.ANUS).isPlayer()) {
			return formatVirginityLoss("You have taken [npc.name]'s anal virginity!")
					+(Main.game.getPlayer().hasFetish(Fetish.FETISH_DEFLOWERING)
							?"<p style='text-align:center;><i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Due to your deflowering fetish, you gain</i>"
							+ " <i style='color:"+Colour.GENERIC_EXPERIENCE.toWebHexString()+";'>"+Fetish.getExperienceGainFromTakingOtherVirginity(Main.game.getPlayer())+"</i>"
							+ " <i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>experience!</i></p>"
							:"");
			
		} else {
			return formatVirginityLoss("[npc.Name] has taken [npc.her] own anal virginity!");
		}
	}
	
	private String getPartnerVaginaVirginityLossDescription(GameCharacter characterPenetrating, PenetrationType penetration){
		if(Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.VAGINA).isPlayer()) {
			return formatVirginityLoss("[npc.Name]'s hymen has been torn; you have taken [npc.her] virginity!")
					+(Main.game.getPlayer().hasFetish(Fetish.FETISH_DEFLOWERING)
							?"<p style='text-align:center;><i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Due to your deflowering fetish, you gain</i>"
							+ " <i style='color:"+Colour.GENERIC_EXPERIENCE.toWebHexString()+";'>"+Fetish.getExperienceGainFromTakingVaginalVirginity(Main.game.getPlayer())+"</i>"
							+ " <i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>experience from taking [npc.name]'s virginity!</i></p>"
							:"");
			
		} else {
			return formatVirginityLoss("[npc.Name] has taken [npc.her] own virginity!");
		}
	}
	
	private String getPartnerNippleVirginityLossDescription(GameCharacter characterPenetrating, PenetrationType penetration){
		if(Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.NIPPLE).isPlayer()) {
			return formatVirginityLoss("You have taken [npc.name]'s nipple virginity!")
					+(Main.game.getPlayer().hasFetish(Fetish.FETISH_DEFLOWERING)
							?"<p style='text-align:center;><i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Due to your deflowering fetish, you gain</i>"
							+ " <i style='color:"+Colour.GENERIC_EXPERIENCE.toWebHexString()+";'>"+Fetish.getExperienceGainFromTakingOtherVirginity(Main.game.getPlayer())+"</i>"
							+ " <i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>experience!</i></p>"
							:"");
			
		} else {
			return formatVirginityLoss("[npc.Name] has taken [npc.her] own nipple virginity!");
		}
	}
	
	private String getPartnerUrethraVirginityLossDescription(GameCharacter characterPenetrating, PenetrationType penetration){
		if(Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.URETHRA).isPlayer()) {
			return formatVirginityLoss("You have taken [npc.name]'s urethral virginity!")
					+(Main.game.getPlayer().hasFetish(Fetish.FETISH_DEFLOWERING)
							?"<p style='text-align:center;><i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Due to your deflowering fetish, you gain</i>"
							+ " <i style='color:"+Colour.GENERIC_EXPERIENCE.toWebHexString()+";'>"+Fetish.getExperienceGainFromTakingOtherVirginity(Main.game.getPlayer())+"</i>"
							+ " <i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>experience!</i></p>"
							:"");
		} else {
			return formatVirginityLoss("[npc.Name] has taken [npc.her] own urethral virginity!");
		}
	}
	
	private String getPartnerMouthVirginityLossDescription(GameCharacter characterPenetrating, PenetrationType penetration){
		if(Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.MOUTH).isPlayer()) {
			return formatVirginityLoss("You have given [npc.name] [npc.her] first oral experience!")
					+(Main.game.getPlayer().hasFetish(Fetish.FETISH_DEFLOWERING)
							?"<p style='text-align:center;><i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Due to your deflowering fetish, you gain</i>"
							+ " <i style='color:"+Colour.GENERIC_EXPERIENCE.toWebHexString()+";'>"+Fetish.getExperienceGainFromTakingOtherVirginity(Main.game.getPlayer())+"</i>"
							+ " <i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>experience!</i></p>"
							:"");
			
		} else {
			return formatVirginityLoss("[npc.Name] has given [npc.herself] [npc.her] first oral experience!");
		}
	}
	
	
	// Stretching:
	
	protected String formatStretching(String rawInput) {
		return UtilText.formatStretching(rawInput);
	}

	
	public String getStretchingDescription(PenetrationType penetrationType, OrificeType orifice) {
		switch(orifice) {
			case ANUS:
				switch(penetrationType) {
				case FINGER: case PENIS: case TONGUE: case TAIL: case TENTACLE:
					return formatStretching(this.isPlayer()
							?"Your [pc.asshole+] is being stretched."
							:UtilText.parse(this, "[npc.Name]'s [npc.asshole+] is being stretched."));
				}
				break;
			case ASS:
				break;
			case BREAST:
				break;
			case MOUTH:
				switch(penetrationType) {
					case FINGER:
						return formatStretching(this.isPlayer()
								?UtilText.parse(this, "You're struggling to fit [npc.name]'s fingers down your throat.")
								:UtilText.parse(this, "[npc.Name] is struggling to fit your fingers down [npc.her] throat."));
					case PENIS:
						return formatStretching(this.isPlayer()
								?UtilText.parse(this, "You're struggling to fit [npc.name]'s [npc.cock+] down your throat.")
								:UtilText.parse(this, "[npc.Name] is struggling to fit your [pc.cock+] down [npc.her] throat."));
					case TAIL:
						return formatStretching(this.isPlayer()
								?UtilText.parse(this, "You're struggling to fit [npc.name]'s [npc.tail] down your throat.")
								:UtilText.parse(this, "[npc.Name] is struggling to fit your [pc.tail] down [npc.her] throat."));
					case TONGUE:
						return formatStretching(this.isPlayer()
								?UtilText.parse(this, "You're struggling to fit [npc.name]'s [npc.tongue] down your throat.")
								:UtilText.parse(this, "[npc.Name] is struggling to fit your [pc.tongue] down [npc.her] throat."));
					default:
						return "Your throat is being stretched out.";
				}
			case NIPPLE:
				switch(penetrationType) {
					case FINGER: case PENIS: case TONGUE: case TAIL: case TENTACLE:
						return formatStretching(this.isPlayer()
								?"Your [pc.nipples+] are being stretched."
								:UtilText.parse(this, "[npc.Name]'s [npc.nipples+] are being stretched."));
				}
				break;
			case THIGHS:
				break;
			case URETHRA:
				switch(penetrationType) {
				case FINGER: case PENIS: case TONGUE: case TAIL: case TENTACLE:
					return formatStretching(this.isPlayer()
							?"Your urethra is being stretched."
							:UtilText.parse(this, "[npc.Name]'s urethra is being stretched."));
				}
				break;
			case VAGINA:
				switch(penetrationType) {
					case FINGER: case PENIS: case TONGUE: case TAIL: case TENTACLE:
						return formatStretching(this.isPlayer()
								?"Your [pc.pussy+] is being stretched."
								:UtilText.parse(this, "[npc.Name]'s [npc.pussy+] is being stretched."));
				}
		}
		return "";
	}

	// Finished stretching:
	
	
	public String getStretchingFinishedDescription(OrificeType orifice) {
		if(this.isPlayer()) {
			switch(orifice) {
				case ANUS:
					return formatStretching("Your asshole has been stretched out to a comfortable size.");
				case ASS:
					break;
				case BREAST:
					break;
				case MOUTH:
					return formatStretching("Your throat has been stretched out to a comfortable size.");
				case NIPPLE:
					return formatStretching("Your nipples have been stretched out to a comfortable size.");
				case THIGHS:
					break;
				case URETHRA:
					return formatStretching("Your urethra has been stretched out to a comfortable size.");
				case VAGINA:
					return formatStretching("Your pussy has been stretched out to a comfortable size.");
			}
		} else {
			switch(orifice) {
				case ANUS:
					return formatStretching("[npc.Name] lets out [npc.a_moan+] as [npc.her] [npc.asshole+] finishes stretching out to a comfortable size.");
				case ASS:
					break;
				case BREAST:
					break;
				case MOUTH:
					return formatStretching("[npc.Name] lets out a muffled [npc.moan] as [npc.her] throat finishes stretching out to a comfortable size.");
				case NIPPLE:
					return formatStretching("[npc.Name] lets out [npc.a_moan+] as [npc.her] [npc.nipples+] finish stretching out to a comfortable size.");
				case THIGHS:
					break;
				case URETHRA:
					return formatStretching("[npc.Name] lets out [npc.a_moan+] as [npc.her] urethra finishes stretching out to a comfortable size.");
				case VAGINA:
					return formatStretching("[npc.Name] lets out [npc.a_moan+] as [npc.her] [npc.pussy+] finishes stretching out to a comfortable size.");
			}
		}
		
		return "";
	}

	
	// Too loose:
	
	protected String formatTooLoose(String rawInput) {
		return UtilText.formatTooLoose(rawInput);
	}
	
	
	public String getTooLooseDescription(OrificeType orifice) {
		if(this.isPlayer()) {
			switch(orifice) {
				case ANUS:
					return formatTooLoose("Your asshole is too loose to provide much pleasure...");
				case ASS:
					break;
				case BREAST:
					break;
				case MOUTH:
					break;
				case NIPPLE:
					return formatTooLoose("Your nipples are too loose to provide much pleasure...");
				case THIGHS:
					break;
				case URETHRA:
					return formatTooLoose("Your urethra is too loose to provide much pleasure...");
				case VAGINA:
					return formatTooLoose("Your pussy is too loose to provide much pleasure...");
			}
		} else {
			switch(orifice) {
				case ANUS:
					return formatTooLoose("[npc.Her] asshole is too loose to provide much pleasure...");
				case ASS:
					break;
				case BREAST:
					break;
				case MOUTH:
					break;
				case NIPPLE:
					return formatTooLoose("[npc.Her] nipples are too loose to provide much pleasure...");
				case THIGHS:
					break;
				case URETHRA:
					return formatTooLoose("[npc.Her] urethra is too loose to provide much pleasure...");
				case VAGINA:
					return formatTooLoose("[npc.Her] pussy is too loose to provide much pleasure...");
			}
		}
		
		return "";
		
	}
	
	// Addictions:
	
	/**
	 * @param fluid The FluidType to be ingested.
	 * @param orificeIngestedThrough Orifice through which the fluid is being ingested.
	 * @param addictive Is this fluid addictive or not.
	 * @return A <b>formatted paragraph</b> description of addiction increasing/satisfied, or an empty String if no addictive effects occur.
	 */
	public String ingestFluid(GameCharacter charactersFluid, FluidType fluid, OrificeType orificeIngestedThrough, int millilitres, List<FluidModifier> modifiers) {
		if(modifiers.contains(FluidModifier.ALCOHOLIC)) { //TODO factor in body size:
			this.incrementAlcoholLevel(millilitres * 0.001f);
			System.out.println(":3");
		}
		
		if(modifiers.contains(FluidModifier.ADDICTIVE)) {
			int increment = 5;
			switch(orificeIngestedThrough) {
				case ANUS:
					increment = 15;
					break;
				case MOUTH:
					increment = 15;
					break;
				case NIPPLE:
					increment = 5;
					break;
				case URETHRA:
					increment = 5;
					break;
				case VAGINA:
					increment = 10;
					break;
				case BREAST: case ASS: case THIGHS:
					break;
			}
			incrementAddiction(fluid, increment, true);
			if(isPlayer()) {
				return "<p>"
							+ "Due to the addictive properties of "+(charactersFluid==this?"your":charactersFluid.getName()+"'s")+" "+fluid.getName(charactersFluid)
								+", you find yourself [style.colourArcane(craving)] <span style='color:"+fluid.getRace().getColour().toWebHexString()+";'>"+fluid.getDescriptor(charactersFluid)+"</span> "+fluid.getName(charactersFluid)+"!"
						+ "</p>";
			} else {
				return UtilText.parse(this,
						"<p>"
							+ "Due to the addictive properties of "+(charactersFluid==this?"[npc.her]":(charactersFluid.isPlayer()?"your":charactersFluid.getName()+"'s"))+" "+fluid.getName(charactersFluid)
								+", [npc.name] finds [npc.herself] [style.colourArcane(craving)] <span style='color:"+fluid.getRace().getColour().toWebHexString()+";'>"+fluid.getDescriptor(charactersFluid)+"</span> "+fluid.getName(charactersFluid)+"!"
						+ "</p>");
			}
			
		} else if(addictionsMap.containsKey(fluid)) {
			setLastTimeSatisfiedAddiction(fluid, Main.game.getMinutesPassed());
			if(isPlayer()) {
				return "<p>"
							+ "Your [style.colourArcane(craving)] for <span style='color:"+fluid.getRace().getColour().toWebHexString()+";'>"
								+fluid.getDescriptor(charactersFluid)+"</span> "+fluid.getName(charactersFluid)+" has been satisfied. For now..."
						+ "</p>";
			} else {
				return UtilText.parse(this,
						"<p>"
							+ "[npc.Name]'s [style.colourArcane(craving)] for <span style='color:"+fluid.getRace().getColour().toWebHexString()+";'>"
								+fluid.getDescriptor(charactersFluid)+"</span> "+fluid.getName(charactersFluid)+" has been satisfied. For now..."
						+ "</p>");
			}
		}
		return "";
	}
	
	public float getAlcoholLevel() {
		return alcoholLevel;
	}
	
	public void setAlcoholLevel(float alcoholLevel) {
		this.alcoholLevel = Math.max(0, Math.min(1, alcoholLevel));
	}
	
	public void incrementAlcoholLevel(float alcoholLevelIncrement) {
		setAlcoholLevel(alcoholLevel + alcoholLevelIncrement);
	}
	
	public void clearAllAddictions() {
		addictionsMap.clear();
		addictionsSatisfiedMap.clear();
	}
	
	public Map<FluidType, Integer> getAddictionsMap() {
		return addictionsMap;
	}
	
	public void recalculateFluidAddictions() {
		List<FluidType> fluids = new ArrayList<>();
		for(FluidType fluid : addictionsMap.keySet()) {
			fluids.add(fluid);
		}
		for(FluidType fluid : fluids) {
			calculateIfAddictionCured(fluid);
		}
	}
	
	private void calculateIfAddictionCured(FluidType fluid) {
		if(Main.game.getMinutesPassed()-getLastTimeSatisfiedAddiction(fluid) > AddictionLevel.valueOf(addictionsMap.get(fluid)).getDaysUntilAddictionCured()*24*60) {
			addictionsMap.remove(fluid);
			addictionsSatisfiedMap.remove(fluid);
		}
	}
	
	public void setAddiction(FluidType fluid, int value) {
		addictionsMap.put(fluid, Math.max(0, Math.min(100, value)));
	}
	
	public void incrementAddiction(FluidType fluid, int increment, boolean setLastTimeSatisfiedToCurrentTime) {
		addictionsMap.putIfAbsent(fluid, 0);
		
		addictionsMap.put(fluid, Math.max(0, Math.min(100, addictionsMap.get(fluid) + increment)));
		
		if(setLastTimeSatisfiedToCurrentTime) {
			setLastTimeSatisfiedAddiction(fluid, Main.game.getMinutesPassed());
		}
	}
	
	public int getAddiction(FluidType fluid) {
		addictionsMap.putIfAbsent(fluid, 0);
		return addictionsMap.get(fluid);
	}
	
	public Map<FluidType, Long> getLastTimeSatisfiedAddictionMap() {
		return addictionsSatisfiedMap;
	}
	
	public void setLastTimeSatisfiedAddiction(FluidType fluid, long minutes) {
		addictionsSatisfiedMap.putIfAbsent(fluid, 0l);
		addictionsSatisfiedMap.put(fluid, minutes);
	}
	
	public long getLastTimeSatisfiedAddiction(FluidType fluid) {
		addictionsSatisfiedMap.putIfAbsent(fluid, 0l);
		return addictionsSatisfiedMap.get(fluid);
	}
	
	
	// Combat:

	/**
	 * The returned list is ordered by rendering priority.
	 */

	public List<SpecialAttack> getSpecialAttacks() {
		List<SpecialAttack> tempListSpecialAttacks = new ArrayList<>(specialAttacks);
		tempListSpecialAttacks.sort(Comparator.comparingInt(SpecialAttack::getRenderingPriority));
		return tempListSpecialAttacks;
	}


	public void calculateSpecialAttacks() {
		specialAttacks.clear();

		for (SpecialAttack sAttack : SpecialAttack.values())
			if (sAttack.isConditionsMet(this))
				specialAttacks.add(sAttack);

		updateAttributeListeners();
	}

	/**
	 * Includes spells from weapons.
	 */
	public List<Spell> getSpells() {
		List<Spell> tempListSpells = new ArrayList<>(spells);
		
		if(getMainWeapon()!=null)
			if(getMainWeapon().getSpells()!=null)
				tempListSpells.addAll(getMainWeapon().getSpells());
		
		if(getOffhandWeapon()!=null)
			if(getOffhandWeapon().getSpells()!=null)
				tempListSpells.addAll(getOffhandWeapon().getSpells());
		
		return tempListSpells;
	}


	public boolean spellKnown(Spell spell) {
		return spells.contains(spell);
	}


	public boolean addSpell(Spell spell) {
		return spells.add(spell);
	}


	public boolean removeSpell(Spell spell) {
		return spells.remove(spell);
	}


	public float getHealth() {
		return health;
	}


	public float getHealthPercentage() {
		return health / getAttributeValue(Attribute.HEALTH_MAXIMUM);
	}


	public String incrementHealth(float increment) {
		// Fetishes:
		if(Main.game.isInCombat()) {
			// Masochist:
			if (isMasochist() && increment < 0) {
				float manaLoss = (Math.round((increment*0.4f)*10))/10f;
				incrementMana(manaLoss);
				setHealth(getHealth() + (increment*0.6f));

				if (isPlayer()) {
					return ("<p>"
							+ "Due to your <b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>masochist fetish</b>, incoming damage is reduced by 40%, but in turn, you take"
									+ " <b>"+-(manaLoss)+"</b> <b style='color:" + Colour.DAMAGE_TYPE_MANA.toWebHexString() + ";'>willpower damage</b> as you struggle to control your arousal!"
							+ "</p>");
				} else {
					return (UtilText.parse(this,
							"<p>"
								+ "Due to [npc.her] <b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>masochist fetish</b>, incoming damage is reduced by 40%, but in turn, [npc.she] takes"
								+ " <b>"+-(manaLoss)+"</b> <b style='color:" + Colour.DAMAGE_TYPE_MANA.toWebHexString() + ";'>willpower damage</b> as [npc.she] struggles to control [npc.her] arousal!"
							+ "</p>"));
				}
			// Sadist:
			} else if ((isPlayer()?Combat.getOpponent().hasFetish(Fetish.FETISH_SADIST):Main.game.getPlayer().hasFetish(Fetish.FETISH_SADIST)) && increment < 0) {
				float manaLoss = (Math.round((increment*0.1f)*10))/10f;
				
				if (isPlayer()) {
					Combat.getOpponent().incrementMana(manaLoss);
					setHealth(getHealth() + increment);
					
					return (UtilText.parse(Combat.getOpponent(),
							"<p>"
								+ "Due to [npc.her] <b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>sadist fetish</b>, [npc.she] suffers 10% of dealt damage as willpower damage, causing [npc.herHim] to take"
								+ " <b>"+-(manaLoss)+"</b> <b style='color:" + Colour.DAMAGE_TYPE_MANA.toWebHexString() + ";'>willpower damage</b> as [npc.she] struggles to control [npc.her] arousal!"
							+ "</p>"));
					
				} else {
					Main.game.getPlayer().incrementMana(manaLoss);
					setHealth(getHealth() + increment);
					
					return ("<p>"
							+ "Due to your <b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>sadist fetish</b>, you suffer 10% of dealt damage as willpower damage, causing you to take"
									+ " <b>"+-(manaLoss)+"</b> <b style='color:" + Colour.DAMAGE_TYPE_MANA.toWebHexString() + ";'>willpower damage</b> as you struggle to control your arousal!"
							+ "</p>");
				}
				
				
			} else {
				setHealth(getHealth() + increment);
				return "";
			}
			
		} else {
			setHealth(getHealth() + increment);
			return "";
		}
	}


	public void setHealth(float health) {
		if (health < 0)
			this.health = 0;
		else if (health > getAttributeValue(Attribute.HEALTH_MAXIMUM))
			this.health = getAttributeValue(Attribute.HEALTH_MAXIMUM);
		else
			this.health = health;

		updateAttributeListeners();
	}

	/**
	 * @param percentage Use value of 0 -> 1
	 */
	public void setHealthPercentage(float percentage) {
		setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM) * percentage);
	}

	public float getMana() {
		return mana;
	}


	public float getManaPercentage() {
		return mana / getAttributeValue(Attribute.MANA_MAXIMUM);
	}


	public void setMana(float mana) {
		if (mana < 0)
			this.mana = 0;
		else if (mana > getAttributeValue(Attribute.MANA_MAXIMUM))
			this.mana = getAttributeValue(Attribute.MANA_MAXIMUM);
		else
			this.mana = mana;

		updateAttributeListeners();
	}
	
	/**
	 * @param percentage Use value of 0 -> 1
	 */
	public void setManaPercentage(float percentage) {
		setMana(getAttributeValue(Attribute.MANA_MAXIMUM) * percentage);
	}
	
	public void incrementMana(float increment) {
		setMana(getMana() + increment);
	}


	public float getStamina() {
		return stamina;
	}


	public float getStaminaPercentage() {
		return stamina / getAttributeValue(Attribute.STAMINA_MAXIMUM);
	}


	public void setStamina(float stamina) {
		if (stamina < 0)
			this.stamina = 0;
		else if (stamina > getAttributeValue(Attribute.STAMINA_MAXIMUM))
			this.stamina = getAttributeValue(Attribute.STAMINA_MAXIMUM);
		else
			this.stamina = stamina;

		updateAttributeListeners();
	}

	/**
	 * @param percentage Use value of 0 -> 1
	 */
	public void setStaminaPercentage(float percentage) {
		setStamina(getAttributeValue(Attribute.STAMINA_MAXIMUM) * percentage);
	}
	
	public void incrementStamina(float increment) {
		setStamina(getStamina() + increment);
	}


	public float getArousal() {
		return getAttributeValue(Attribute.AROUSAL);
	}
	
	public void setArousal(float arousal) {
		if (arousal < 0) {
			setAttribute(Attribute.AROUSAL, 0, false);
			
		} else if (arousal > 100) {
			setAttribute(Attribute.AROUSAL, 100, false);
			
		} else {
			setAttribute(Attribute.AROUSAL, arousal, false);
		}

		updateAttributeListeners();
	}
	
	public void incrementArousal(float increment) {
		setArousal(getArousal() + increment);
	}
	
	public float getLust() {
		return getAttributeValue(Attribute.LUST);
	}
	
	public LustLevel getLustLevel() {
		return LustLevel.getLustLevelFromValue(getAttributeValue(Attribute.LUST));
	}
	
	public void setLust(float arousal) {
		if (arousal < 0) {
			setAttribute(Attribute.LUST, 0, false);
			
		} else if (arousal > 100) {
			setAttribute(Attribute.LUST, 100, false);
			
		} else {
			setAttribute(Attribute.LUST, arousal, false);
		}

		updateAttributeListeners();
	}
	
	public void incrementLust(float increment) {
		setLust(getLust() + increment);
	}

	public boolean isWearingCondom() {
//		if(Main.game.isInSex() && this == Sex.getPartner())
//			System.out.println("condom inner: "+(this.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot())!=null));
		return this.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot())!=null && this.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()).getClothingType().equals(ClothingType.PENIS_CONDOM);
	}
	
	public boolean isExhibitionist() {
		return hasFetish(Fetish.FETISH_EXHIBITIONIST);
	}
	
	public boolean isMasochist() {
		return hasFetish(Fetish.FETISH_MASOCHIST);
	}
	
	public boolean isSubmissive() {
		return hasFetish(Fetish.FETISH_SUBMISSIVE);
	}
	
	// Pregnancy:

	public String rollForPregnancy(GameCharacter partner) {
		
		float pregnancyChance = 0;
		
		if(partner.getAttributeValue(Attribute.VIRILITY)<=0 || getAttributeValue(Attribute.FERTILITY)<=0) {
			pregnancyChance = 0;
			
		} else {
			pregnancyChance = 0;
			pregnancyChance += (partner.getAttributeValue(Attribute.VIRILITY)/100f) * partner.getPenisCumProduction().getPregnancyModifier();
			pregnancyChance += (getAttributeValue(Attribute.FERTILITY)/100f);
		}
		
		if(pregnancyChance>1)
			pregnancyChance=1;
		
		String s;
		
		if(isVisiblyPregnant()) {
			s = PregnancyDescriptor.ALREADY_PREGNANT.getDescriptor(this, partner);
			
		} else {
			PregnancyPossibility pregPoss = new PregnancyPossibility(this.getId(), partner.getId(), pregnancyChance);
			
			this.addPotentialPartnerAsMother(pregPoss);
			partner.addPotentialPartnerAsFather(pregPoss);

			if (pregnancyChance <= 0)
				s = PregnancyDescriptor.NO_CHANCE.getDescriptor(this, partner);
			else if(pregnancyChance<=0.15f)
				s = PregnancyDescriptor.LOW_CHANCE.getDescriptor(this, partner);
			else if(pregnancyChance<=0.3f)
				s = PregnancyDescriptor.AVERAGE_CHANCE.getDescriptor(this, partner);
			else if(pregnancyChance<1)
				s = PregnancyDescriptor.HIGH_CHANCE.getDescriptor(this, partner);
			else
				s = PregnancyDescriptor.CERTAINTY.getDescriptor(this, partner);
		}
		
		if (!hasStatusEffect(StatusEffect.PREGNANT_0) && !isPregnant()) {
			addStatusEffect(StatusEffect.PREGNANT_0, 60 * (4 + Util.random.nextInt(5)));
		}
		
		// Now roll for pregnancy:
		if (!isPregnant()) {
			if (Math.random() <= pregnancyChance) {
				
				int minimumNumberOfChildren = 1;
				int maximumNumberOfChildren = 1;
				
				if(getVaginaType()==VaginaType.HUMAN) {
					minimumNumberOfChildren = partner.getPenisType().getRace().getNumberOfOffspringLow();
					maximumNumberOfChildren = partner.getPenisType().getRace().getNumberOfOffspringHigh();
					
				} else {
					minimumNumberOfChildren = getVaginaType().getRace().getNumberOfOffspringLow();
					maximumNumberOfChildren = getVaginaType().getRace().getNumberOfOffspringHigh();
				}
				
				if(partner.hasFetish(Fetish.FETISH_SEEDER)) {
					maximumNumberOfChildren*=2;
				}
				if(hasFetish(Fetish.FETISH_BROODMOTHER)) {
					maximumNumberOfChildren*=2;
				}
				
				int numberOfChildren = minimumNumberOfChildren + Util.random.nextInt((maximumNumberOfChildren-minimumNumberOfChildren)+1);
				
				List<NPC> offspring = new ArrayList<>();
				for (int i = 0; i < numberOfChildren; i++) { // Add children here:
					NPC npc = new NPCOffspring(this, partner);
					offspring.add(npc);
					try {
						Main.game.addNPC(npc, false);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				pregnantLitter = new Litter(Main.game.getDayNumber(), Main.game.getDayNumber(), this, partner, offspring);
			}
		}
		
		return s;
	}

	public boolean isPregnant() {
		return pregnantLitter != null;
	}
	
	public boolean isHasAnyPregnancyEffects() {
		return hasStatusEffect(StatusEffect.PREGNANT_0) || hasStatusEffect(StatusEffect.PREGNANT_1) || hasStatusEffect(StatusEffect.PREGNANT_2) || hasStatusEffect(StatusEffect.PREGNANT_3);
	}
	
	public boolean isVisiblyPregnant() {
		return pregnantLitter != null && !hasStatusEffect(StatusEffect.PREGNANT_0);
	}
	
	public long getTimeProgressedToFinalPregnancyStage() {
		return timeProgressedToFinalPregnancyStage;
	}

	public void setTimeProgressedToFinalPregnancyStage(long timeProgressedToFinalPregnancyStage) {
		this.timeProgressedToFinalPregnancyStage = timeProgressedToFinalPregnancyStage;
	}

	/**
	 * Ends the character's pregnancy. If ended with birth, also handles litters
	 * added to the littersBirthed list.
	 * 
	 * @param withBirth
	 *            True if this pregnancy ends by giving birth.
	 */
	public void endPregnancy(boolean withBirth) {
		for(PregnancyPossibility pregPoss : potentialPartnersAsMother) {
			if(pregPoss.getFather()!=null) {
				pregPoss.getFather().getPotentialPartnersAsFather().remove(pregPoss);
			}
		}
		potentialPartnersAsMother.clear();
		
		if (withBirth) {
			Litter birthedLitter = pregnantLitter;
			
			if((birthedLitter.getFather()!=null && birthedLitter.getFather().isPlayer()) || (birthedLitter.getMother()!=null && birthedLitter.getMother().isPlayer())) {
				for(String id: birthedLitter.getOffspring()) {
					if(Main.game.isCharacterExisting(id)) {
						NPC npc = (NPC) Main.game.getNPCById(id);
						birthedLitter.setDayOfBirth(Main.game.getDayNumber());
						npc.setDayOfConception(birthedLitter.getDayOfConception());
						npc.setDayOfBirth(Main.game.getDayNumber());
					}
				}
			}
			
			littersBirthed.add(birthedLitter);
			
			if(birthedLitter.getFather()!=null) {
				birthedLitter.getFather().getLittersFathered().add(birthedLitter);
			}
		} else {
			if(pregnantLitter!=null) {
				for(String npc : pregnantLitter.getOffspring()) {
					Main.game.removeNPC(npc);
				}
			}
		}
		
		removeStatusEffect(StatusEffect.PREGNANT_1);
		removeStatusEffect(StatusEffect.PREGNANT_2);
		removeStatusEffect(StatusEffect.PREGNANT_3);

		pregnantLitter = null;
	}

	public List<Litter> getLittersBirthed() {
		return littersBirthed;
	}

	public Litter getLastLitterBirthed() {
		if(littersBirthed.isEmpty()) {
			return null;
		}
		return littersBirthed.get(littersBirthed.size() - 1);
	}
	
	public List<Litter> getLittersFathered() {
		return littersFathered;
	}
	
	public Litter getPregnantLitter() {
		return pregnantLitter;
	}
	
	public void setPregnantLitter(Litter pregnantLitter) {
		this.pregnantLitter = pregnantLitter;
	}

	public List<PregnancyPossibility> getPotentialPartnersAsMother() {
		return potentialPartnersAsMother;
	}
	
	public void addPotentialPartnerAsMother(PregnancyPossibility possibility) {
		boolean alreadyContainedPossibility = false;
		
		for(PregnancyPossibility currentPossibility : potentialPartnersAsMother) {
			if(currentPossibility.getMotherId().equals(possibility.getMotherId())
					&& currentPossibility.getFatherId().equals(possibility.getFatherId())) {
				currentPossibility.setProbability(1f-(1-currentPossibility.getProbability() * 1-possibility.getProbability()));
				alreadyContainedPossibility = true;
				break;
			}
		}
		
		if(!alreadyContainedPossibility) {
			potentialPartnersAsMother.add(possibility);
		}
	}
	
	public List<PregnancyPossibility> getPotentialPartnersAsFather() {
		return potentialPartnersAsFather;
	}
	
	public void addPotentialPartnerAsFather(PregnancyPossibility possibility) {
		boolean alreadyContainedPossibility = false;
		
		for(PregnancyPossibility currentPossibility : potentialPartnersAsFather) {
			if(currentPossibility.getMotherId().equals(possibility.getMotherId())
					&& currentPossibility.getFatherId().equals(possibility.getFatherId())) {
				currentPossibility.setProbability(1f-(1-currentPossibility.getProbability() * 1-possibility.getProbability()));
				alreadyContainedPossibility = true;
				break;
			}
		}
		
		if(!alreadyContainedPossibility) {
			potentialPartnersAsFather.add(possibility);
		}
	}
	
	// Cummed in areas:

	public Map<OrificeType, Integer> getCummedInAreaMap() {
		return cummedInAreaMap;
	}
	
	public void incrementCummedInArea(OrificeType area, int cumQuantityIncrement) {
		setCummedInArea(area, cummedInAreaMap.get(area)+cumQuantityIncrement);
	}
	
	public void setCummedInArea(OrificeType area, int cumQuantity) {
		if((this.isVisiblyPregnant() && cumQuantity >= CumProduction.SEVEN_MONSTROUS.getMinimumValue())) {
			cummedInAreaMap.put(area, CumProduction.SEVEN_MONSTROUS.getMinimumValue()-1);
		} else {
			cummedInAreaMap.put(area, Math.max(0, cumQuantity));
		}
	}
	
	public void resetCummedInAreaMap() {
		for(OrificeType orifice : OrificeType.values()) {
			setCummedInArea(orifice, 0);
		}
	}
	
	public void washAllOrifices() {
		for(OrificeType orifice : OrificeType.values()) {
			switch(orifice) {
				case MOUTH:
					break;
				case ASS: case BREAST: case THIGHS:
					setCummedInArea(orifice, 0);
					break;
				case ANUS: case NIPPLE: case URETHRA: case VAGINA:
					incrementCummedInArea(orifice, -500);
					break;
			}
		}
	}

	// Other:
	
	public Vector2i getLocation() {
		return location;
	}

	public void setLocation(Vector2i location) {
		this.location = location;

		updateLocationListeners();
	}
	
	public Vector2i getHomeLocation() {
		return homeLocation;
	}
	
	public WorldType getWorldLocation() {
		return worldLocation;
	}

	public void setWorldLocation(WorldType worldLocation) {
		this.worldLocation = worldLocation;
	}
	
	public WorldType getHomeWorldLocation() {
		return homeWorldLocation;
	}
	
	public GenericPlace getLocationPlace() {
		return Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getPlace();
	}
	
	public GenericPlace getHomeLocationPlace() {
		return Main.game.getWorlds().get(getHomeWorldLocation()).getCell(getHomeLocation()).getPlace();
	}
	
	public void setLocation(WorldType worldType, Vector2i location, boolean setAsHomeLocation) {
		setWorldLocation(worldType);
		setLocation(location);
		if(setAsHomeLocation) {
			setHomeLocation(worldType, location);
		}
	}

	public void setRandomLocation(WorldType worldType, PlaceType placeType, boolean setAsHomeLocation) {
		setLocation(worldType, Main.game.getWorlds().get(worldType).getRandomCell(placeType).getLocation(), setAsHomeLocation);
	}
	
	public void setLocation(WorldType worldType, PlaceType placeType, boolean setAsHomeLocation) {
		setLocation(worldType, Main.game.getWorlds().get(worldType).getCell(placeType).getLocation(), setAsHomeLocation);
	}
	
	/**
	 * Moves this character to an adjoining Cell which shares the PlaceType of the Cell that the character is already in.
	 * @return True if the character was moved.
	 */
	public boolean moveToAdjacentMatchingCellType() {
		World world = Main.game.getWorlds().get(this.getWorldLocation());
		List<Vector2i> availableLocations = new ArrayList<>();
		PlaceType currentlyOccupiedCellPlaceType = getLocationPlace().getPlaceType();
		
		if(world.getCell(this.getLocation().getX()+1, this.getLocation().getY())!=null && world.getCell(this.getLocation().getX()+1, this.getLocation().getY()).getPlace().getPlaceType()==currentlyOccupiedCellPlaceType) {
			availableLocations.add(new Vector2i(this.getLocation().getX()+1, this.getLocation().getY()));
		}
		if(world.getCell(this.getLocation().getX()-1, this.getLocation().getY())!=null && world.getCell(this.getLocation().getX()-1, this.getLocation().getY()).getPlace().getPlaceType()==currentlyOccupiedCellPlaceType) {
			availableLocations.add(new Vector2i(this.getLocation().getX()-1, this.getLocation().getY()));
		}
		if(world.getCell(this.getLocation().getX(), this.getLocation().getY()+1)!=null && world.getCell(this.getLocation().getX(), this.getLocation().getY()+1).getPlace().getPlaceType()==currentlyOccupiedCellPlaceType) {
			availableLocations.add(new Vector2i(this.getLocation().getX(), this.getLocation().getY()+1));
		}
		if(world.getCell(this.getLocation().getX(), this.getLocation().getY()-1)!=null && world.getCell(this.getLocation().getX(), this.getLocation().getY()-1).getPlace().getPlaceType()==currentlyOccupiedCellPlaceType) {
			availableLocations.add(new Vector2i(this.getLocation().getX(), this.getLocation().getY()-1));
		}
		
		if(availableLocations.isEmpty()) {
			return false;
		} else {
			this.setLocation(availableLocations.get(Util.random.nextInt(availableLocations.size())));
			return true;
		}
	}
	
	public void setHomeLocation(WorldType homeWorldLocation, PlaceType placeType) {
		this.homeWorldLocation = homeWorldLocation;
		this.homeLocation = Main.game.getWorlds().get(homeWorldLocation).getCell(placeType).getLocation();
	}
	
	public void setHomeLocation(WorldType homeWorldLocation, Vector2i location) {
		this.homeWorldLocation = homeWorldLocation;
		this.homeLocation = location;
	}
	
	public void returnToHome() {
		setLocation(homeWorldLocation, homeLocation, true);
	}

	private int getTrueLevel() {
		return level;
	}
	
	public int getLevel() {
		if(this.isPlayer() || !Main.getProperties().difficultyLevel.isNPCLevelScaling()) {
			return level;
		} else {
			if(Main.getProperties().difficultyLevel == DifficultyLevel.HELL) {
				if(level < Main.game.getPlayer().getLevel() * 2) {
					return Main.game.getPlayer().getLevel() * 2;
				} else {
					return level;
				}
			} else if(level < Main.game.getPlayer().getLevel()) {
				return Main.game.getPlayer().getLevel();
			} else {
				return level;
			}
		}
	}

	public void setLevel(int level) {
		this.level = level;
	}

	// -------------------- Inventory -------------------- //

	public String droppedItemText(AbstractCoreItem item) {
		if(this.getLocationPlace().isItemsDisappear()) {
			if(isPlayer()) {
				return "<p style='text-align:center;'>"
							+ "You drop your " + item.getName() + " on the floor."
							+ "</br>"
							+ "<span style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>The " + item.getName() + " will be lost if you leave this location!</span>"
						+ "</p>";
			} else {
				return UtilText.parse(this,
					"<p style='text-align:center;'>"
						+ "[npc.Name] drops [npc.her] " + item.getName() + " on the floor."
						+ "</br>"
						+ "<span style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>The " + item.getName() + " will be lost if you leave this location!</span>"
					+ "</p>");
			}
			
		} else {
			if(isPlayer()) {
				return "<p style='text-align:center;'>"
							+ "You drop your " + item.getName() + " on the floor."
							+ "</br>"
							+ "<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>The " + item.getName() + " will be stored safely in this location!</span>"
						+ "</p>";
			} else {
				return UtilText.parse(this,
					"<p style='text-align:center;'>"
						+ "[npc.Name] drops [npc.her] " + item.getName() + " on the floor."
						+ "</br>"
						+ "<span style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>The " + item.getName() + " will be stored safely in this location!</span>"
					+ "</p>");
			}
		}
	}

	public String addedItemToInventoryText(AbstractCoreItem item) {
		if(item instanceof AbstractItem) {
			return "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Item added to inventory:</b> <b>" + ((AbstractItem)item).getDisplayName(true) + "</b>";
		}
		if(item instanceof AbstractClothing) {
			return "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Clothing added to inventory:</b> <b>" + ((AbstractClothing)item).getDisplayName(true) + "</b>";
		}
		if(item instanceof AbstractWeapon) {
			return "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Weapon added to inventory:</b> <b>" + ((AbstractWeapon)item).getDisplayName(true) + "</b>";
		}
		return "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Item added to inventory:</b> <b>" + item.getName() + "</b>";
	}

	public String addedItemToInventoryText(AbstractItemType item) {
		return "<p style='text-align:center;'>" + "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>You add the " + item.getName(false) + " to your inventory.</span>" + "</p>";
	}

	public String inventoryFullText() {
		return "<p style='text-align:center;'>" + "<b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full!</b>" + "</p>";
	}
	
	/**
	 * First unequips all clothing into void, so that clothing effects are preserved.
	 */
	public void resetInventory(){
		unequipAllClothingIntoVoid();
		
		this.inventory = new CharacterInventory(0);
	}
	
	public void unequipAllClothingIntoVoid() {
		List<AbstractClothing> clothingEquipped = new ArrayList<>(this.getClothingCurrentlyEquipped());
		for(AbstractClothing clothing : clothingEquipped) {
			clothing.setSealed(false);
		}
		for(AbstractClothing clothing : clothingEquipped) {
			this.unequipClothingIntoVoid(clothing, true, this);
		}
	}
	
	public int getMoney() {
		return inventory.getMoney();
	}

	public void setMoney(int money) {
		inventory.setMoney(money);
	}
	
	public void incrementMoney(int money) {
		inventory.incrementMoney(money);
	}
	
	// Essences:
	
	public Map<TFEssence, Integer> getEssenceMap() {
		return inventory.getEssenceMap();
	}
	
	public int getEssenceCount(TFEssence essence) {
		return getEssenceMap().get(essence);
	}
	
	public void setEssenceCount(TFEssence essence, int amount) {
		getEssenceMap().put(essence, amount);
	}
	
	public void incrementEssenceCount(TFEssence essence, int increment) {
		if(getEssenceCount(essence)+increment < 0)
			getEssenceMap().put(essence, 0);
		else
			getEssenceMap().put(essence, getEssenceCount(essence)+increment);
	}
	
	public boolean hasEssences() {
		for(Integer i : getEssenceMap().values()) {
			if(i>0)
				return true;
		}
		return false;
	}
	public boolean hasNonArcaneEssences() {
		for(Entry<TFEssence, Integer> entry : getEssenceMap().entrySet()) {
			if(entry.getKey()!=TFEssence.ARCANE)
				if(entry.getValue()>0)
					return true;
		}
		return false;
	}
	
	
	public void clearNonEquippedInventory(){
		inventory.clearNonEquippedInventory();
	}

	public boolean isInventoryFull() {
		return inventory.isInventoryFull();
	}

	public int getMaximumInventorySpace() {
		return inventory.getMaximumInventorySpace();
	}

	public void setMaximumInventorySpace(int maxInventorySpace) {
		inventory.setMaximumInventorySpace(maxInventorySpace);
	}

	public int getInventorySlotsTaken() {
		return inventory.getInventorySlotsTaken();
	}
	
	public Map<AbstractWeapon, Integer> getMapOfDuplicateWeapons() {
		return inventory.getMapOfDuplicateWeapons();
	}
	public int getUniqueWeaponCount() {
		return inventory.getUniqueWeaponCount();
	}
	public int getWeaponCount() {
		return inventory.getWeaponCount();
	}
	public int getWeaponCount(AbstractWeapon weapon) {
		return inventory.getWeaponCount(weapon);
	}
	
	public Map<AbstractClothing, Integer> getMapOfDuplicateClothing() {
		return inventory.getMapOfDuplicateClothing();
	}
	public int getUniqueClothingCount() {
		return inventory.getUniqueClothingCount();
	}
	public int getClothingCount() {
		return inventory.getClothingCount();
	}
	public int getClothingCount(AbstractClothing clothing) {
		return inventory.getClothingCount(clothing);
	}
	
	public Map<AbstractItem, Integer> getMapOfDuplicateItems() {
		return inventory.getMapOfDuplicateItems();
	}
	public int getUniqueItemCount() {
		return inventory.getUniqueItemCount();
	}
	public int getItemCount() {
		return inventory.getItemCount();
	}
	public int getItemCount(AbstractItem item) {
		return inventory.getItemCount(item);
	}
	
	
	// -------------------- Items -------------------- //
	
	/**
	 * <b>DO NOT MODIFY!</b>
	 */
	public List<AbstractItem> getAllItemsInInventory() {
		return inventory.getAllItemsInInventory();
	}
	
	public AbstractItem getItem(int index) {
		return inventory.getItem(index);
	}
	
	/**
	 * Add an item to this character's inventory. If the inventory is full, the item is dropped in the character's current location.
	 * 
	 * @param removingFromFloor true if this item should be removed from the floor of the area the character is currently in on a successful pick up.
	 * @return Description of what happened.
	 */
	public String addItem(AbstractItem item, boolean removingFromFloor) {
		if (removingFromFloor) {
			if (inventory.addItem(item)) {
				updateInventoryListeners();
				Main.game.getWorlds().get(getWorldLocation()).getCell(location).getInventory().removeItem(item);
				return addedItemToInventoryText(item);
			} else {
				return inventoryFullText() + droppedItemText(item);
			}
			
		} else {
			if (inventory.addItem(item)) {
				updateInventoryListeners();
				return addedItemToInventoryText(item);
			} else {
				Main.game.getWorlds().get(getWorldLocation()).getCell(location).getInventory().addItem(item);
				return inventoryFullText() + droppedItemText(item);
			}
		}
	}
	
	public void removeItem(AbstractItem item) {
		inventory.removeItem(item);
	}
	
	public boolean hasItem(AbstractItem item) {
		return inventory.hasItem(item);
	}
	
	/**
	 * @return true if one of the items in this inventory has the same type as the Item provided.
	 */
	public boolean hasItemType(AbstractItemType item) {
		return inventory.hasItemType(item);
	}
	
	/**
	 * Drops the item in the cell this character is currently in.
	 * @return Description of what happened.
	 */
	public String dropItem(AbstractItem item) {
		if (inventory.dropItem(item, location)) {
			return droppedItemText(item);
			
		} else {
			return "";
		}
	}
	
	
	public String useItem(AbstractItem item, GameCharacter target, boolean removingFromFloor) {
		return useItem(item, target, removingFromFloor, false);
	}
	
	/**
	 * Uses the specified item on the specified target. If the item returns true on isConsumedOnUse() call, this item is removed from the character's inventory.
	 * 
	 * @param removingFromFloor true if an instance of this item should be consumed from the floor of the area the character is currently in. If item isConsumedOnUse() returns false, an item will not be removed from the floor.
	 * @return Description of what happened.
	 */

	public String useItem(AbstractItem item, GameCharacter target, boolean removingFromFloor, boolean onlyReturnEffects) {
		if(ItemType.allItems.contains(item.getItemType()) && isPlayer()) {
			if(Main.getProperties().addItemDiscovered(item.getItemType())) {
				Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(item.getItemType().getName(false), item.getRarity().getColour()), true);
			}
		}
		
		if (item.getItemType().isConsumedOnUse()) {
			if(removingFromFloor) {
				Main.game.getWorlds().get(getWorldLocation()).getCell(location).getInventory().removeItem(item);
			} else {
				removeItem(item);
			}
		}
		
		if(onlyReturnEffects) {
			return item.applyEffect(this, target);
		} else {
			return item.getUseDescription(this, target) + item.applyEffect(this, target);
		}
		
	}

	
	// -------------------- Weapons -------------------- //
	
	/**
	 * <b>DO NOT MODIFY!</b>
	 */
	public List<AbstractWeapon> getAllWeaponsInInventory() {
		return inventory.getAllWeaponsInInventory();
	}
	
	public AbstractWeapon getWeapon(int index) {
		return inventory.getWeapon(index);
	}
	
	public String addWeapon(AbstractWeapon weapon, boolean removingFromFloor) {
		if (inventory.addWeapon(weapon)) {
			if (removingFromFloor) {
				Main.game.getWorlds().get(getWorldLocation()).getCell(location).getInventory().removeWeapon(weapon);
			}
			return "<p style='text-align:center;'>" + addedItemToInventoryText(weapon)+"</p>";
			
		} else {
			Main.game.getWorlds().get(getWorldLocation()).getCell(location).getInventory().addWeapon(weapon);
			return inventoryFullText() + "</br>" + droppedItemText(weapon);
		}
	}
	
	public boolean removeWeapon(AbstractWeapon weapon) {
		return inventory.removeWeapon(weapon);
	}
	
	public boolean hasWeapon(AbstractWeapon weapon) {
		return inventory.hasWeapon(weapon);
	}
	
	public String dropWeapon(AbstractWeapon weapon) {
		if (inventory.dropWeapon(weapon, location)) {
			return droppedItemText(weapon);
			
		} else
			return "";
	}
	
	public AbstractWeapon getMainWeapon() {
		return inventory.getMainWeapon();
	}

	
	/** @return Description of equipping this weapon. */
	public String equipMainWeaponFromInventory(AbstractWeapon weapon, GameCharacter fromCharactersInventory) {
		fromCharactersInventory.removeWeapon(weapon);
		return equipMainWeapon(weapon);
	}

	/** @return Description of equipping this weapon. */
	public String equipMainWeaponFromFloor(AbstractWeapon weapon) {
		Main.game.getWorlds().get(getWorldLocation()).getCell(location).getInventory().removeWeapon(weapon);
		return equipMainWeapon(weapon);
	}

	/** @return Description of equipping this weapon. */
	public String equipMainWeaponFromNowhere(AbstractWeapon weapon) {
		return equipMainWeapon(weapon);
	}
	
	private String equipMainWeapon(AbstractWeapon weapon) {
		if (weapon == null) {
			throw new NullPointerException("null weapon was passed.");
		}
		
		String s = "";
		
		if (getMainWeapon() != null) {
			s += addWeapon(getMainWeapon(), false);

			// Revert old melee weapon's attribute bonuses:
			if (getMainWeapon().getAttributeModifiers() != null) {
				for (Entry<Attribute, Integer> e : getMainWeapon().getAttributeModifiers().entrySet()) {
					incrementBonusAttribute(e.getKey(), -e.getValue());
				}
			}
		}

		s += weapon.onEquip(this);
		// Apply its attribute bonuses:
		if (weapon.getAttributeModifiers() != null) {
			for (Entry<Attribute, Integer> e : weapon.getAttributeModifiers().entrySet()) {
				incrementBonusAttribute(e.getKey(), e.getValue());
			}
		}
		
		inventory.equipMainWeapon(weapon);
		updateInventoryListeners();
		
		return s;
	}
	
	public String unequipMainWeapon(boolean dropToFloor) {
		if (getMainWeapon() != null) {
			// If weapon is unequipped, revert its attribute bonuses:
			if (getMainWeapon().getAttributeModifiers() != null)
				for (Entry<Attribute, Integer> e : getMainWeapon().getAttributeModifiers().entrySet())
					incrementBonusAttribute(e.getKey(), -e.getValue());

			boolean mustDropToFloor = isInventoryFull() && !hasWeapon(getMainWeapon());
			String s;
			if (mustDropToFloor || dropToFloor) {
				Main.game.getWorlds().get(getWorldLocation()).getCell(location).getInventory().addWeapon(getMainWeapon());
				s = getMainWeapon().getWeaponType().unequipText(this) + (mustDropToFloor && !dropToFloor ? inventoryFullText() : "") + droppedItemText(getMainWeapon());
			} else {
				addWeapon(getMainWeapon(), false);
				s = getMainWeapon().getWeaponType().unequipText(this) + "<p style='text-align:center;'>" + addedItemToInventoryText(getMainWeapon())+"</p>";
			}
			inventory.unequipMainWeapon();
			updateInventoryListeners();
			
			return s;
		} else
			return "";
	}
	
	public AbstractWeapon getOffhandWeapon() {
		return inventory.getOffhandWeapon();
	}
	
	
	/** @return Description of equipping this weapon. */
	public String equipOffhandWeaponFromInventory(AbstractWeapon weapon, GameCharacter fromCharactersInventory) {
		String s = equipOffhandWeapon(weapon);
		fromCharactersInventory.removeWeapon(weapon);
		return s;
	}

	/** @return Description of equipping this weapon. */
	public String equipOffhandWeaponFromFloor(AbstractWeapon weapon) {
		String s = equipOffhandWeapon(weapon);
		Main.game.getWorlds().get(getWorldLocation()).getCell(location).getInventory().removeWeapon(weapon);
		return s;
	}

	/** @return Description of equipping this weapon. */
	public String equipOffhandWeaponFromNowhere(AbstractWeapon weapon) {
		return equipOffhandWeapon(weapon);
	}
	
	public String equipOffhandWeapon(AbstractWeapon weapon) {
		if (weapon == null) {
			throw new NullPointerException("null weapon was passed.");
		}
		
		String s = "";
		
		if (getOffhandWeapon() != null) {
			s += addWeapon(getOffhandWeapon(), false);

			// Revert old weapon's attribute bonuses:
			if (getOffhandWeapon().getAttributeModifiers() != null) {
				for (Entry<Attribute, Integer> e : getOffhandWeapon().getAttributeModifiers().entrySet()) {
					incrementBonusAttribute(e.getKey(), -e.getValue());
				}
			}
		}
		
		s += weapon.onEquip(this);
		// Apply its attribute bonuses:
		if (weapon.getAttributeModifiers() != null) {
			for (Entry<Attribute, Integer> e : weapon.getAttributeModifiers().entrySet()) {
				incrementBonusAttribute(e.getKey(), e.getValue());
			}
		}
		
		inventory.equipOffhandWeapon(weapon);
		updateInventoryListeners();
		
		return s;
	}
	
	public String unequipOffhandWeapon(boolean dropToFloor) {
		if (getOffhandWeapon() != null) {
			// If weapon is unequipped, revert it's attribute bonuses:
			if (getOffhandWeapon().getAttributeModifiers() != null)
				for (Entry<Attribute, Integer> e : getOffhandWeapon().getAttributeModifiers().entrySet())
					incrementBonusAttribute(e.getKey(), -e.getValue());
			
			boolean mustDropToFloor = isInventoryFull() && !hasWeapon(getOffhandWeapon());
			String s;
			if (mustDropToFloor || dropToFloor) {
				Main.game.getWorlds().get(getWorldLocation()).getCell(location).getInventory().addWeapon(getOffhandWeapon());
				s = getOffhandWeapon().getWeaponType().unequipText(this) + (mustDropToFloor && !dropToFloor ? inventoryFullText() : "") + droppedItemText(getOffhandWeapon());
			} else {
				addWeapon(getOffhandWeapon(), false);
				s = getOffhandWeapon().getWeaponType().unequipText(this) + "<p style='text-align:center;'>" + addedItemToInventoryText(getOffhandWeapon())+"</p>";
			}

			inventory.unequipOffhandWeapon();
			updateInventoryListeners();
			
			return s;
		} else
			return "";
	}
	
	
	// -------------------- Clothing -------------------- //
	
	/**
	 * <b>DO NOT MODIFY!</b>
	 */
	public List<AbstractClothing> getAllClothingInInventory() {
		return inventory.getAllClothingInInventory();
	}
	
	public AbstractClothing getClothing(int index) {
		return inventory.getClothing(index);
	}
	
	public void cleanAllClothing() {
		inventory.cleanAllClothing();
	}

	public String addClothing(AbstractClothing clothing, boolean removingFromFloor) {
		if (inventory.addClothing(clothing)) {
			updateInventoryListeners();
			if (removingFromFloor)
				Main.game.getWorlds().get(getWorldLocation()).getCell(location).getInventory().removeClothing(clothing);
			return "<p style='text-align:center;'>" + addedItemToInventoryText(clothing)+"</p>";
		} else
			return inventoryFullText() + droppedItemText(clothing);
	}


	public boolean removeClothing(AbstractClothing clothing) {
		return inventory.removeClothing(clothing);
	}


	public String dropClothing(AbstractClothing clothing) {
		if (inventory.dropClothing(clothing, location)) {
			updateInventoryListeners();

			return droppedItemText(clothing);
		} else
			return "";
	}
	
	public boolean hasClothing(AbstractClothing clothing) {
		return inventory.hasClothing(clothing);
	}


	public List<AbstractClothing> getClothingCurrentlyEquipped() {
		return inventory.getClothingCurrentlyEquipped();
	}


	public AbstractClothing getClothingInSlot(InventorySlot invSlot) {
		return inventory.getClothingInSlot(invSlot);
	}


	public int getClothingSetCount(ClothingSet clothingSet) {
		return inventory.getClothingSetCount(clothingSet);
	}
	
	public boolean isSlotIncompatible(InventorySlot slot) {
		return inventory.isSlotIncompatible(slot);
	}
	
	public void deleteAllEquippedClothing() {
		List<AbstractClothing> equippedClothing = new ArrayList<>();
		for(AbstractClothing clothing : getClothingCurrentlyEquipped()) {
			equippedClothing.add(clothing);
			clothing.setSealed(false);
		}
		for(AbstractClothing clothing : equippedClothing) {
			unequipClothingIntoVoid(clothing, true, this);
		}
		equippedClothing.clear();
	}

	/**
	 * 
	 * @param newClothing The clothing to equip to this character.
	 * @param automaticClothingManagement Whether clothing should automatically be shifted/removed in order to equip this clothing.
	 * @param characterClothingEquipper The character who is equipping the clothing to this character.
	 * @param fromCharactersInventory The character who has this clothing in their inventory.
	 * @return Equip description
	 */
	public String equipClothingFromInventory(AbstractClothing newClothing, boolean automaticClothingManagement, GameCharacter characterClothingEquipper, GameCharacter fromCharactersInventory) {
		boolean wasAbleToEquip = inventory.isAbleToEquip(newClothing, true, automaticClothingManagement, this, characterClothingEquipper);

		// If this item was able to be equipped, and it was equipped, apply its attribute bonuses:
		if (wasAbleToEquip) {
			incrementBonusAttribute(Attribute.RESISTANCE_PHYSICAL, newClothing.getClothingType().getPhysicalResistance());
			for (Entry<Attribute, Integer> e : newClothing.getAttributeModifiers().entrySet()) {
				incrementBonusAttribute(e.getKey(), e.getValue());
			}
			
			fromCharactersInventory.removeClothing(newClothing);

			newClothing.setEnchantmentKnown(true);

			updateInventoryListeners();

			if (isPlayer() && Main.game.isInNewWorld()) {
				if (Main.getProperties().addClothingDiscovered(newClothing.getClothingType())) {
					Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(newClothing.getName(), newClothing.getRarity().getColour()), true);
				}
			}
		}

		return inventory.getEquipDescription();
	}

	/**
	 * <b>!!!ONLY FOR USE IN CHARACTER IMPORT!!!</b>
	 * @param newClothing
	 */
	public void equipClothingOverride(AbstractClothing newClothing) {
		inventory.getClothingCurrentlyEquipped().add(newClothing);
		incrementBonusAttribute(Attribute.RESISTANCE_PHYSICAL, newClothing.getClothingType().getPhysicalResistance());
		for (Entry<Attribute, Integer> e : newClothing.getAttributeModifiers().entrySet()) {
			incrementBonusAttribute(e.getKey(), e.getValue());
		}
		
		newClothing.setEnchantmentKnown(true);

		updateInventoryListeners();
	}

	public String equipClothingFromNowhere(AbstractClothing newClothing, boolean automaticClothingManagement, GameCharacter characterClothingEquipper) {
		boolean wasAbleToEquip = inventory.isAbleToEquip(newClothing, true, automaticClothingManagement, this, characterClothingEquipper);

		// If this item was able to be equipped, and it was equipped, apply it's
		// attribute bonuses:
		if (wasAbleToEquip) {
			incrementBonusAttribute(Attribute.RESISTANCE_PHYSICAL, newClothing.getClothingType().getPhysicalResistance());
			for (Entry<Attribute, Integer> e : newClothing.getAttributeModifiers().entrySet()) {
				incrementBonusAttribute(e.getKey(), e.getValue());
			}
			
			newClothing.setEnchantmentKnown(true);

			updateInventoryListeners();

			if (isPlayer() && Main.game.isInNewWorld()) {
				if (Main.getProperties().addClothingDiscovered(newClothing.getClothingType())) {
					Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(newClothing.getName(), newClothing.getRarity().getColour()), true);
				}
			}
		}

		return inventory.getEquipDescription();
	}


	public String equipClothingFromGround(AbstractClothing newClothing, boolean automaticClothingManagement, GameCharacter characterClothingEquipper) {
		boolean wasAbleToEquip = inventory.isAbleToEquip(newClothing, true, automaticClothingManagement, this, characterClothingEquipper);

		// If this item was able to be equipped, and it was equipped, apply it's
		// attribute bonuses:
		if (wasAbleToEquip) {
			incrementBonusAttribute(Attribute.RESISTANCE_PHYSICAL, newClothing.getClothingType().getPhysicalResistance());
			for (Entry<Attribute, Integer> e : newClothing.getAttributeModifiers().entrySet())
				incrementBonusAttribute(e.getKey(), e.getValue());

			Main.game.getWorlds().get(getWorldLocation()).getCell(location).getInventory().removeClothing(newClothing);

			newClothing.setEnchantmentKnown(true);

			updateInventoryListeners();

			if (isPlayer() && Main.game.isInNewWorld()) {
				if (Main.getProperties().addClothingDiscovered(newClothing.getClothingType())) {
					Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(newClothing.getName(), newClothing.getRarity().getColour()), true);
				}
			}
		}

		return inventory.getEquipDescription();
	}


	public boolean isAbleToEquip(AbstractClothing newClothing, boolean automaticClothingManagement, GameCharacter characterClothingEquipper) {
		return inventory.isAbleToEquip(newClothing, false, automaticClothingManagement, this, characterClothingEquipper);
	}


	public String getEquipDescription() {
		return inventory.getEquipDescription();
	}


	public String unequipClothingIntoInventory(AbstractClothing clothing, boolean automaticClothingManagement, GameCharacter characterClothingUnequipper) {
		boolean wasAbleToUnequip = inventory.isAbleToUnequip(clothing, true, automaticClothingManagement, this, characterClothingUnequipper);

		// If this item was able to be unequipped, and it was unequipped, revert
		// it's attribute bonuses:
		if (wasAbleToUnequip) {
			incrementBonusAttribute(Attribute.RESISTANCE_PHYSICAL, -clothing.getClothingType().getPhysicalResistance());
			for (Entry<Attribute, Integer> e : clothing.getAttributeModifiers().entrySet()) {
				incrementBonusAttribute(e.getKey(), -e.getValue());
			}
			
			boolean fitsIntoInventory = !isInventoryFull() || hasClothing(clothing);

			// Place the clothing into inventory:
			if (fitsIntoInventory)
				addClothing(clothing, false);
			else
				Main.game.getWorlds().get(getWorldLocation()).getCell(location).getInventory().addClothing(clothing);

			updateInventoryListeners();
			
			return "<p style='text-align:center;'>"
				+inventory.getEquipDescription()
				+"</br>"
				+ (!fitsIntoInventory
						? droppedItemText(clothing)
						: addedItemToInventoryText(clothing))
				+ "</p>";
		}

		return "<p style='text-align:center;'>"
				+inventory.getEquipDescription()
				+ "</p>";
	}


	public String unequipClothingOntoFloor(AbstractClothing clothing, boolean automaticClothingManagement, GameCharacter characterClothingUnequipper) { // TODO it's saying "added to inventory"
		boolean wasAbleToUnequip = inventory.isAbleToUnequip(clothing, true, automaticClothingManagement, this, characterClothingUnequipper);

		// If this item was able to be unequipped, and it was unequipped, revert
		// it's attribute bonuses:
		if (wasAbleToUnequip) {
			incrementBonusAttribute(Attribute.RESISTANCE_PHYSICAL, -clothing.getClothingType().getPhysicalResistance());
			for (Entry<Attribute, Integer> e : clothing.getAttributeModifiers().entrySet()) {
				incrementBonusAttribute(e.getKey(), -e.getValue());
			}

			// Place the clothing on the floor:
			Main.game.getWorlds().get(getWorldLocation()).getCell(location).getInventory().addClothing(clothing);

			updateInventoryListeners();
		}

		return inventory.getEquipDescription() + droppedItemText(clothing);
	}
	
	public String unequipClothingIntoVoid(AbstractClothing clothing, boolean automaticClothingManagement, GameCharacter characterClothingUnequipper) { // TODO it's saying "added to inventory"
		boolean wasAbleToUnequip = inventory.isAbleToUnequip(clothing, true, automaticClothingManagement, this, characterClothingUnequipper);

		// If this item was able to be unequipped, and it was unequipped, revert
		// it's attribute bonuses:
		if (wasAbleToUnequip) {
			incrementBonusAttribute(Attribute.RESISTANCE_PHYSICAL, -clothing.getClothingType().getPhysicalResistance());
			for (Entry<Attribute, Integer> e : clothing.getAttributeModifiers().entrySet()) {
				incrementBonusAttribute(e.getKey(), -e.getValue());
			}

			updateInventoryListeners();
		}

		return inventory.getEquipDescription();
	}


	public boolean isAbleToUnequip(AbstractClothing clothing, boolean automaticClothingManagement, GameCharacter characterClothingRemover) {
		return inventory.isAbleToUnequip(clothing, false, automaticClothingManagement, this, characterClothingRemover);
	}


	public String getUnequipDescription() {
		return inventory.getEquipDescription();
	}


	/**
	 * <b>Warning:</b> Passing in a DisplacementType that isn't present for the passed in clothing *might* throw a null pointer exception...
	 * 
	 * @param clothing The clothing that is to be checked for displacement.
	 * @param dt The type of displacement to perform upon this clothing.
	 * @param displaceIfAble If true, the clothing will be displaced (if it's able to be displaced). If false, this method can just be used as a boolean check to see if it's able to be displaced without actually displacing it.
	 * @param automaticClothingManagement If true, other clothing will be moved aside to get access to the clothing that needs to be displaced. If false, then if other clothing is blocking this clothing from being displaced, this method will return false.
	 * @param characterClothingDisplacer The character who is performing the displacement action.
	 * @return True if the clothing can be displaced, false if it can't/
	 */
	public boolean isAbleToBeDisplaced(AbstractClothing clothing, DisplacementType dt, boolean displaceIfAble, boolean automaticClothingManagement, GameCharacter characterClothingDisplacer) {
		boolean wasAbleToDisplace = inventory.isAbleToBeDisplaced(clothing, dt, displaceIfAble, automaticClothingManagement, this, characterClothingDisplacer);

		// If this item was able to be displaced, and it was displaced, apply
		// exposed effects:
		if (wasAbleToDisplace && displaceIfAble) {
			updateInventoryListeners();
		}

		return wasAbleToDisplace;
	}


	public String getDisplaceDescription() {
		return inventory.getDisplaceDescription();
	}


	public boolean isAbleToBeReplaced(AbstractClothing clothing, DisplacementType dt, boolean replaceIfAble, boolean automaticClothingManagement, GameCharacter characterClothingDisplacer) {
		boolean wasAbleToReplace = inventory.isAbleToBeReplaced(clothing, dt, replaceIfAble, automaticClothingManagement, this, characterClothingDisplacer);

		// If this item was able to be displaced, and it was displaced, apply
		// exposed effects:
		if (wasAbleToReplace && replaceIfAble) {
			updateInventoryListeners();
		}

		return wasAbleToReplace;
	}

	public void replaceAllClothing() {
		
	}
	

	public String getReplaceDescription() {
		return inventory.getReplaceDescription();
	}
	
	public AbstractClothing getBlockingClothing() {
		return inventory.getBlockingClothing();
	}

	public SimpleEntry<AbstractClothing, DisplacementType> getNextClothingToRemoveForCoverableAreaAccess(CoverableArea coverableArea) {
		return inventory.getNextClothingToRemoveForCoverableAreaAccess(coverableArea);
	}

	public boolean isAbleToAccessCoverableArea(CoverableArea area, boolean byRemovingClothing) {
		return inventory.isAbleToAccessCoverableArea(area, byRemovingClothing);
	}
	
	public AbstractClothing getClothingBlockingCoverableAreaAccess(CoverableArea area, boolean byRemovingClothing) {
		return inventory.getClothingBlockingCoverableAreaAccess(area, byRemovingClothing);
	}
	
	public boolean isCoverableAreaExposed(CoverableArea area) {
		return inventory.isCoverableAreaExposed(area);
	}
	
	public boolean isPenetrationTypeExposed(PenetrationType pt) {
		switch(pt) {
			case FINGER:
				return true;
			case PENIS:
				return isCoverableAreaExposed(CoverableArea.PENIS);
			case TAIL:
				return true;
			case TENTACLE:
				return true;
			case TONGUE:
				return isCoverableAreaExposed(CoverableArea.MOUTH);
			default:
				return true;
		}
	}
	
	public boolean isOrificeTypeExposed(OrificeType ot) {
		switch(ot) {
			case ANUS:
				return isCoverableAreaExposed(CoverableArea.ANUS);
			case ASS:
				return isCoverableAreaExposed(CoverableArea.ASS);
			case MOUTH:
				return isCoverableAreaExposed(CoverableArea.MOUTH);
			case NIPPLE:
				return isCoverableAreaExposed(CoverableArea.NIPPLES);
			case URETHRA:
				return isCoverableAreaExposed(CoverableArea.PENIS);
			case VAGINA:
				return isCoverableAreaExposed(CoverableArea.VAGINA);
			case THIGHS:
				return isCoverableAreaExposed(CoverableArea.THIGHS);
			case BREAST:
				return isCoverableAreaExposed(CoverableArea.BREASTS);
		}
		return false;
	}


	public int getClothingAverageFemininity() {
		return inventory.getClothingAverageFemininity();
	}

	public AbstractClothing getLowestZLayerCoverableArea(CoverableArea area) {
		return inventory.getLowestZLayerCoverableArea(area);
	}

	public AbstractClothing getHighestZLayerCoverableArea(CoverableArea area) {
		return inventory.getHighestZLayerCoverableArea(area);
	}
	
	public Set<InventorySlot> getDirtySlots() {
		return inventory.getDirtySlots();
	}
	
	public boolean isDirtySlot(InventorySlot slot) {
		return inventory.isDirtySlot(slot);
	}
	
	public boolean addDirtySlot(InventorySlot slot) {
		return inventory.addDirtySlot(slot);
	}
	
	public boolean removeDirtySlot(InventorySlot slot) {
		return inventory.removeDirtySlot(slot);
	}

	public void cleanAllDirtySlots() {
		inventory.cleanAllDirtySlots();
	}

	// Body:

	/** <b>Please use accessor methods instead! Accessing and modifying body parts directly will cause problems! >:(</b> */
	public Body getBody() {
		return body;
	}
	
	public List<BodyPartInterface> getAllBodyParts() {
		return body.getAllBodyParts();
	}
	
	private class GenderAppearance {
		public String description;
		public Gender gender;
		public GenderAppearance(String description, Gender gender) {
			this.description = description;
			this.gender = gender;
		}
	}
	
	public Gender getAppearsAsGender() {
		return calculateGenderAppearance().gender;
	}

	public String getAppearsAsGenderDescription() {
		return calculateGenderAppearance().description;
	}
	
	private GenderAppearance calculateGenderAppearance() {
		boolean visibleVagina = isCoverableAreaExposed(CoverableArea.VAGINA) && hasVagina();
		boolean visiblePenis = isCoverableAreaExposed(CoverableArea.PENIS) && hasPenis();
		boolean bulgeFromCock = hasPenis() && getGenitalArrangement() != GenitalArrangement.CLOACA && (hasPenisModifier(PenisModifier.SHEATHED)
									? getPenisRawSizeValue()>=PenisSize.FOUR_HUGE.getMaximumValue()
									: getPenisRawSizeValue()>=PenisSize.THREE_LARGE.getMaximumValue());
		boolean bulgeFromBalls = hasPenis() && getGenitalArrangement() != GenitalArrangement.CLOACA && (isInternalTesticles()
									? false
									: getTesticleSize().getValue()>=TesticleSize.FOUR_HUGE.getValue());
		
		if(this.getFemininityValue()>=Femininity.FEMININE.getMinimumFemininity()) {
			if(hasBreasts()) {
				if(visibleVagina && visiblePenis) {
					// Exposed penis and vagina:
					return new GenderAppearance(
							isPlayer()
							?"Due to the fact that both your [pc.vagina] and [pc.penis] are exposed, as well as the fact that you have [pc.breastSize] breasts, everyone can tell that you're [pc.a_gender] on first glance."
							:UtilText.parse(this,
									"Due to the fact that both [npc.her] [npc.vagina] and [npc.penis] are exposed, as well as the fact that [npc.she] has [npc.breastSize] breasts, everyone can tell that [npc.she]'s [npc.a_gender] on first glance."),
							Gender.F_P_V_B_FUTANARI);
						
				} else if(visibleVagina) {
					// Exposed vagina and obvious penis bulge:
					if(bulgeFromCock) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your exposed [pc.vagina] and [pc.breastSize] breasts, reveals to everyone that you're [pc.a_gender]."
								:UtilText.parse(this,
										"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, reveals to everyone that [npc.she]'s [npc.a_gender]."),
								Gender.F_P_V_B_FUTANARI);
						
					} else if (bulgeFromBalls) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your exposed [pc.vagina] and [pc.breastSize] breasts, reveals to everyone that you're [pc.a_gender]."
								:UtilText.parse(this,
										"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, reveals to everyone that [npc.she]'s [npc.a_gender]."),
								Gender.F_P_V_B_FUTANARI);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina] and [pc.breastSize] breasts, and the fact that your [pc.penis] remains concealed, everyone assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.F_V_B_FEMALE.getName())+" "+Gender.F_V_B_FEMALE.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.F_V_B_FEMALE.getName())+" "+Gender.F_V_B_FEMALE.getName()+" on first glance."),
								Gender.F_V_B_FEMALE);
						
					} else {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina] and [pc.breastSize] breasts, everyone correctly assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.F_V_B_FEMALE.getName())+" "+Gender.F_V_B_FEMALE.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.F_V_B_FEMALE.getName())+" "+Gender.F_V_B_FEMALE.getName()+" on first glance."),
								Gender.F_V_B_FEMALE);
					}
					
				} else if(visiblePenis) {
					// Exposed penis:
					return new GenderAppearance(
							isPlayer()
							?"Due to your exposed [pc.penis] and [pc.breastSize] breasts, everyone assumes that you're "
									+UtilText.generateSingularDeterminer(Gender.F_P_B_SHEMALE.getName())+" "+Gender.F_P_B_SHEMALE.getName()+" on first glance."
							:UtilText.parse(this,
									"Due to [npc.her] exposed [npc.penis] and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
										+UtilText.generateSingularDeterminer(Gender.F_P_B_SHEMALE.getName())+" "+Gender.F_P_B_SHEMALE.getName()+" on first glance."),
							Gender.F_P_B_SHEMALE);
					
				} else {
					// Obvious bulge:
					if(bulgeFromCock) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your feminine appearance and [pc.breastSize] breasts, leads everyone to believe that you're "
										+UtilText.generateSingularDeterminer(Gender.F_P_B_SHEMALE.getName())+" "+Gender.F_P_B_SHEMALE.getName()+"."
								:UtilText.parse(this,
										"Due to the [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] feminine appearance and [npc.breastSize] breasts, leads everyone to believe that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.F_P_B_SHEMALE.getName())+" "+Gender.F_P_B_SHEMALE.getName()+"."),
								Gender.F_P_B_SHEMALE);
						
					} else if (bulgeFromBalls) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your feminine appearance and [pc.breastSize] breasts, leads everyone to believe that you're "
										+UtilText.generateSingularDeterminer(Gender.F_P_B_SHEMALE.getName())+" "+Gender.F_P_B_SHEMALE.getName()+"."
								:UtilText.parse(this,
										"Due to the [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] feminine appearance and [npc.breastSize] breasts, leads everyone to believe that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.F_P_B_SHEMALE.getName())+" "+Gender.F_P_B_SHEMALE.getName()+"."),
								Gender.F_P_B_SHEMALE);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Your [pc.penis] is concealed, so, due to your feminine appearance and [pc.breastSize] breasts, everyone assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.F_V_B_FEMALE.getName())+" "+Gender.F_V_B_FEMALE.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] feminine appearance and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.F_V_B_FEMALE.getName())+" "+Gender.F_V_B_FEMALE.getName()+" on first glance."),
								Gender.F_V_B_FEMALE);
						
					} else if(hasVagina()) {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Your feminine appearance and [pc.breastSize] breasts leads everyone to correctly assume that you're "
										+UtilText.generateSingularDeterminer(Gender.F_V_B_FEMALE.getName())+" "+Gender.F_V_B_FEMALE.getName()+"."
								:UtilText.parse(this,
										"Due to [npc.her] feminine appearance and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.F_V_B_FEMALE.getName())+" "+Gender.F_V_B_FEMALE.getName()+"."),
								Gender.F_V_B_FEMALE);
						
					} else {
						if(isCoverableAreaExposed(CoverableArea.VAGINA) && isCoverableAreaExposed(CoverableArea.PENIS)) {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is exposed, so, due to your feminine appearance and [pc.breastSize] breasts, strangers treat you as "
											+UtilText.generateSingularDeterminer(Gender.F_B_DOLL.getName())+" "+Gender.F_B_DOLL.getName()+"."
									:UtilText.parse(this,
											"Due to [npc.her] genderless mound being exposed, combined with [npc.her] feminine appearance and [npc.breastSize] breasts, everyone can tell that [npc.she]'s "
												+UtilText.generateSingularDeterminer(Gender.F_B_DOLL.getName())+" "+Gender.F_B_DOLL.getName()+"."),
									Gender.F_B_DOLL);
							
						} else {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is concealed, so, due to your feminine appearance and [pc.breastSize] breasts, strangers treat you as "
											+UtilText.generateSingularDeterminer(Gender.F_V_B_FEMALE.getName())+" "+Gender.F_V_B_FEMALE.getName()+"."
									:UtilText.parse(this,
											"Due to [npc.her] feminine appearance and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
												+UtilText.generateSingularDeterminer(Gender.F_V_B_FEMALE.getName())+" "+Gender.F_V_B_FEMALE.getName()+"."),
									Gender.F_V_B_FEMALE);
						}
					}
				}
				
			// No breasts:
			} else {
				if(visibleVagina && visiblePenis) {
					// Exposed penis and vagina:
					return new GenderAppearance(
							isPlayer()
							?"Due to the fact that both your [pc.vagina] and [pc.penis] are exposed, everyone can tell that you're [pc.a_gender] on first glance."
							:UtilText.parse(this,
									"Due to the fact that both [npc.her] [npc.vagina] and [npc.penis] are exposed, everyone can tell that [npc.she]'s [npc.a_gender] on first glance."),
							Gender.F_P_V_FUTANARI);
						
				} else if(visibleVagina) {
					// Exposed vagina and obvious penis bulge:
					if(bulgeFromCock) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your exposed [pc.vagina], reveals to everyone that you're [pc.a_gender]."
								:UtilText.parse(this,
										"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina], reveals to everyone that [npc.she]'s [npc.a_gender]."),
								Gender.F_P_V_FUTANARI);
						
					} else if (bulgeFromBalls) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your exposed [pc.vagina], reveals to everyone that you're [pc.a_gender]."
								:UtilText.parse(this,
										"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina], reveals to everyone that [npc.she]'s [npc.a_gender]."),
								Gender.F_P_V_FUTANARI);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina], and the fact that your [pc.penis] remains concealed, everyone assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.F_V_FEMALE.getName())+" "+Gender.F_V_FEMALE.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] exposed [npc.vagina], everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.F_V_FEMALE.getName())+" "+Gender.F_V_FEMALE.getName()+" on first glance."),
								Gender.F_V_FEMALE);
						
					} else {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina], everyone correctly assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.F_V_FEMALE.getName())+" "+Gender.F_V_FEMALE.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] exposed [npc.vagina], everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.F_V_FEMALE.getName())+" "+Gender.F_V_FEMALE.getName()+" on first glance."),
								Gender.F_V_FEMALE);
					}
					
				} else if(visiblePenis) {
					// Exposed penis:
					return new GenderAppearance(
							isPlayer()
							?"Due to your exposed [pc.penis], everyone assumes that you're "
									+UtilText.generateSingularDeterminer(Gender.F_P_TRAP.getName())+" "+Gender.F_P_TRAP.getName()+" on first glance."
							:UtilText.parse(this,
									"Due to [npc.her] exposed [npc.penis], everyone assumes that [npc.she]'s "
										+UtilText.generateSingularDeterminer(Gender.F_P_TRAP.getName())+" "+Gender.F_P_TRAP.getName()+" on first glance."),
							Gender.F_P_TRAP);
					
				} else {
					// Obvious bulge:
					if(bulgeFromCock) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your feminine appearance, leads everyone to believe that you're "
										+UtilText.generateSingularDeterminer(Gender.F_P_TRAP.getName())+" "+Gender.F_P_TRAP.getName()+"."
								:UtilText.parse(this,
										"Due to the [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] feminine appearance, leads everyone to believe that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.F_P_TRAP.getName())+" "+Gender.F_P_TRAP.getName()+"."),
								Gender.F_P_TRAP);
						
					} else if (bulgeFromBalls) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your feminine appearance, leads everyone to believe that you're "
										+UtilText.generateSingularDeterminer(Gender.F_P_TRAP.getName())+" "+Gender.F_P_TRAP.getName()+"."
								:UtilText.parse(this,
										"Due to the [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] feminine appearance, leads everyone to believe that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.F_P_TRAP.getName())+" "+Gender.F_P_TRAP.getName()+"."),
								Gender.F_P_TRAP);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Your [pc.penis] is concealed, so, due to your feminine appearance, everyone assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.F_V_FEMALE.getName())+" "+Gender.F_V_FEMALE.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] feminine appearance, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.F_V_FEMALE.getName())+" "+Gender.F_V_FEMALE.getName()+" on first glance."),
								Gender.F_V_FEMALE);
						
					} else if(hasVagina()) {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Your feminine appearance leads everyone to correctly assume that you're "
										+UtilText.generateSingularDeterminer(Gender.F_V_FEMALE.getName())+" "+Gender.F_V_FEMALE.getName()+"."
								:UtilText.parse(this,
										"Due to [npc.her] feminine appearance, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.F_V_FEMALE.getName())+" "+Gender.F_V_FEMALE.getName()+"."),
								Gender.F_V_FEMALE);
						
					} else {
						if(isCoverableAreaExposed(CoverableArea.VAGINA) && isCoverableAreaExposed(CoverableArea.PENIS)) {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is exposed, so, due to your feminine appearance, strangers treat you as "
											+UtilText.generateSingularDeterminer(Gender.F_DOLL.getName())+" "+Gender.F_DOLL.getName()+"."
									:UtilText.parse(this,
											"Due to [npc.her] genderless mound being exposed, combined with [npc.her] feminine appearance, everyone can tell that [npc.she]'s "
												+UtilText.generateSingularDeterminer(Gender.F_DOLL.getName())+" "+Gender.F_DOLL.getName()+"."),
									Gender.F_DOLL);
							
						} else {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is concealed, so, due to your feminine appearance, strangers treat you as "
											+UtilText.generateSingularDeterminer(Gender.F_V_FEMALE.getName())+" "+Gender.F_V_FEMALE.getName()+"."
									:UtilText.parse(this,
											"Due to [npc.her] feminine appearance, everyone assumes that [npc.she]'s "
												+UtilText.generateSingularDeterminer(Gender.F_V_FEMALE.getName())+" "+Gender.F_V_FEMALE.getName()+"."),
									Gender.F_V_FEMALE);
						}
					}
				}
			}
			
		// Androgynous:
		} else if(this.getFemininityValue()>=Femininity.ANDROGYNOUS.getMinimumFemininity()) {
			if(hasBreasts()) {
				if(visibleVagina && visiblePenis) {
					// Exposed penis and vagina:
					return new GenderAppearance(
							isPlayer()
							?"Due to the fact that both your [pc.vagina] and [pc.penis] are exposed, as well as the fact that you have [pc.breastSize] breasts, everyone can tell that you're [pc.a_gender] on first glance."
							:UtilText.parse(this,
									"Due to the fact that both [npc.her] [npc.vagina] and [npc.penis] are exposed, as well as the fact that [npc.she] has [npc.breastSize] breasts, everyone can tell that [npc.she]'s [npc.a_gender] on first glance."),
							Gender.N_P_V_B_HERMAPHRODITE);
						
				} else if(visibleVagina) {
					// Exposed vagina and obvious penis bulge:
					if(bulgeFromCock) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your exposed [pc.vagina] and [pc.breastSize] breasts, reveals to everyone that you're [pc.a_gender]."
								:UtilText.parse(this,
										"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, reveals to everyone that [npc.she]'s [npc.a_gender]."),
								Gender.N_P_V_B_HERMAPHRODITE);
						
					} else if (bulgeFromBalls) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your exposed [pc.vagina] and [pc.breastSize] breasts, reveals to everyone that you're [pc.a_gender]."
								:UtilText.parse(this,
										"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, reveals to everyone that [npc.she]'s [npc.a_gender]."),
								Gender.N_P_V_B_HERMAPHRODITE);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina] and [pc.breastSize] breasts, and the fact that your [pc.penis] remains concealed, everyone assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.N_V_B_TOMBOY.getName())+" "+Gender.N_V_B_TOMBOY.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.N_V_B_TOMBOY.getName())+" "+Gender.N_V_B_TOMBOY.getName()+" on first glance."),
								Gender.N_V_B_TOMBOY);
						
					} else {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina] and [pc.breastSize] breasts, everyone correctly assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.N_V_B_TOMBOY.getName())+" "+Gender.N_V_B_TOMBOY.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.N_V_B_TOMBOY.getName())+" "+Gender.N_V_B_TOMBOY.getName()+" on first glance."),
								Gender.N_V_B_TOMBOY);
					}
					
				} else if(visiblePenis) {
					// Exposed penis:
					return new GenderAppearance(
							isPlayer()
							?"Due to your exposed [pc.penis] and [pc.breastSize] breasts, everyone assumes that you're "
									+UtilText.generateSingularDeterminer(Gender.N_P_B_SHEMALE.getName())+" "+Gender.N_P_B_SHEMALE.getName()+" on first glance."
							:UtilText.parse(this,
									"Due to [npc.her] exposed [npc.penis] and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
										+UtilText.generateSingularDeterminer(Gender.N_P_B_SHEMALE.getName())+" "+Gender.N_P_B_SHEMALE.getName()+" on first glance."),
							Gender.N_P_B_SHEMALE);
					
				} else {
					// Obvious bulge:
					if(bulgeFromCock) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your androgynous appearance and [pc.breastSize] breasts, leads everyone to believe that you're "
										+UtilText.generateSingularDeterminer(Gender.N_P_B_SHEMALE.getName())+" "+Gender.N_P_B_SHEMALE.getName()+"."
								:UtilText.parse(this,
										"Due to the [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] androgynous appearance and [npc.breastSize] breasts, leads everyone to believe that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.N_P_B_SHEMALE.getName())+" "+Gender.N_P_B_SHEMALE.getName()+"."),
								Gender.N_P_B_SHEMALE);
						
					} else if (bulgeFromBalls) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your androgynous appearance and [pc.breastSize] breasts, leads everyone to believe that you're "
										+UtilText.generateSingularDeterminer(Gender.N_P_B_SHEMALE.getName())+" "+Gender.N_P_B_SHEMALE.getName()+"."
								:UtilText.parse(this,
										"Due to the [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] androgynous appearance and [npc.breastSize] breasts, leads everyone to believe that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.N_P_B_SHEMALE.getName())+" "+Gender.N_P_B_SHEMALE.getName()+"."),
								Gender.N_P_B_SHEMALE);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Your [pc.penis] is concealed, so, due to your androgynous appearance and [pc.breastSize] breasts, everyone assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.N_V_B_TOMBOY.getName())+" "+Gender.N_V_B_TOMBOY.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] androgynous appearance and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.N_V_B_TOMBOY.getName())+" "+Gender.N_V_B_TOMBOY.getName()+" on first glance."),
								Gender.N_V_B_TOMBOY);
						
					} else if(hasVagina()) {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Your androgynous appearance and [pc.breastSize] breasts leads everyone to correctly assume that you're "
										+UtilText.generateSingularDeterminer(Gender.N_V_B_TOMBOY.getName())+" "+Gender.N_V_B_TOMBOY.getName()+"."
								:UtilText.parse(this,
										"Due to [npc.her] androgynous appearance and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.N_V_B_TOMBOY.getName())+" "+Gender.N_V_B_TOMBOY.getName()+"."),
								Gender.N_V_B_TOMBOY);
						
					} else {
						if(isCoverableAreaExposed(CoverableArea.VAGINA) && isCoverableAreaExposed(CoverableArea.PENIS)) {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is exposed, so, due to your androgynous appearance and [pc.breastSize] breasts, strangers treat you as "
											+UtilText.generateSingularDeterminer(Gender.N_B_DOLL.getName())+" "+Gender.N_B_DOLL.getName()+"."
									:UtilText.parse(this,
											"Due to [npc.her] genderless mound being exposed, combined with [npc.her] androgynous appearance and [npc.breastSize] breasts, everyone can tell that [npc.she]'s "
												+UtilText.generateSingularDeterminer(Gender.N_B_DOLL.getName())+" "+Gender.N_B_DOLL.getName()+"."),
									Gender.N_B_DOLL);
							
						} else {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is concealed, so, due to your androgynous appearance and [pc.breastSize] breasts, strangers treat you as "
											+UtilText.generateSingularDeterminer(Gender.N_V_B_TOMBOY.getName())+" "+Gender.N_V_B_TOMBOY.getName()+"."
									:UtilText.parse(this,
											"Due to [npc.her] androgynous appearance and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
												+UtilText.generateSingularDeterminer(Gender.N_V_B_TOMBOY.getName())+" "+Gender.N_V_B_TOMBOY.getName()+"."),
									Gender.N_V_B_TOMBOY);
						}
					}
				}
				
			// No breasts:
			} else {
				if(visibleVagina && visiblePenis) {
					// Exposed penis and vagina:
					return new GenderAppearance(
							isPlayer()
							?"Due to the fact that both your [pc.vagina] and [pc.penis] are exposed, everyone can tell that you're [pc.a_gender] on first glance."
							:UtilText.parse(this,
									"Due to the fact that both [npc.her] [npc.vagina] and [npc.penis] are exposed, everyone can tell that [npc.she]'s [npc.a_gender] on first glance."),
							Gender.N_P_V_HERMAPHRODITE);
						
				} else if(visibleVagina) {
					// Exposed vagina and obvious penis bulge:
					if(bulgeFromCock) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your exposed [pc.vagina], reveals to everyone that you're [pc.a_gender]."
								:UtilText.parse(this,
										"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina], reveals to everyone that [npc.she]'s [npc.a_gender]."),
								Gender.N_P_V_HERMAPHRODITE);
						
					} else if (bulgeFromBalls) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your exposed [pc.vagina], reveals to everyone that you're [pc.a_gender]."
								:UtilText.parse(this,
										"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina], reveals to everyone that [npc.she]'s [npc.a_gender]."),
								Gender.N_P_V_HERMAPHRODITE);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina], and the fact that your [pc.penis] remains concealed, everyone assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.N_V_TOMBOY.getName())+" "+Gender.N_V_TOMBOY.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] exposed [npc.vagina], everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.N_V_TOMBOY.getName())+" "+Gender.N_V_TOMBOY.getName()+" on first glance."),
								Gender.N_V_TOMBOY);
						
					} else {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina], everyone correctly assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.N_V_TOMBOY.getName())+" "+Gender.N_V_TOMBOY.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] exposed [npc.vagina], everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.N_V_TOMBOY.getName())+" "+Gender.N_V_TOMBOY.getName()+" on first glance."),
								Gender.N_V_TOMBOY);
					}
					
				} else if(visiblePenis) {
					// Exposed penis:
					return new GenderAppearance(
							isPlayer()
							?"Due to your exposed [pc.penis], everyone assumes that you're "
									+UtilText.generateSingularDeterminer(Gender.N_P_TRAP.getName())+" "+Gender.N_P_TRAP.getName()+" on first glance."
							:UtilText.parse(this,
									"Due to [npc.her] exposed [npc.penis], everyone assumes that [npc.she]'s "
										+UtilText.generateSingularDeterminer(Gender.N_P_TRAP.getName())+" "+Gender.N_P_TRAP.getName()+" on first glance."),
							Gender.N_P_TRAP);
					
				} else {
					// Obvious bulge:
					if(bulgeFromCock) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your androgynous appearance, leads everyone to believe that you're "
										+UtilText.generateSingularDeterminer(Gender.N_P_TRAP.getName())+" "+Gender.N_P_TRAP.getName()+"."
								:UtilText.parse(this,
										"Due to the [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] androgynous appearance, leads everyone to believe that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.N_P_TRAP.getName())+" "+Gender.N_P_TRAP.getName()+"."),
								Gender.N_P_TRAP);
						
					} else if (bulgeFromBalls) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your androgynous appearance, leads everyone to believe that you're "
										+UtilText.generateSingularDeterminer(Gender.N_P_TRAP.getName())+" "+Gender.N_P_TRAP.getName()+"."
								:UtilText.parse(this,
										"Due to the [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] androgynous appearance, leads everyone to believe that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.N_P_TRAP.getName())+" "+Gender.N_P_TRAP.getName()+"."),
								Gender.N_P_TRAP);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Your [pc.penis] is concealed, so, due to your androgynous appearance, everyone assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.N_V_TOMBOY.getName())+" "+Gender.N_V_TOMBOY.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] androgynous appearance, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.N_V_TOMBOY.getName())+" "+Gender.N_V_TOMBOY.getName()+" on first glance."),
								Gender.N_V_TOMBOY);
						
					} else if(hasVagina()) {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Your androgynous appearance leads everyone to correctly assume that you're "
										+UtilText.generateSingularDeterminer(Gender.N_V_TOMBOY.getName())+" "+Gender.N_V_TOMBOY.getName()+"."
								:UtilText.parse(this,
										"Due to [npc.her] androgynous appearance, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.N_V_TOMBOY.getName())+" "+Gender.N_V_TOMBOY.getName()+"."),
								Gender.N_V_TOMBOY);
						
					} else {
						if(isCoverableAreaExposed(CoverableArea.VAGINA) && isCoverableAreaExposed(CoverableArea.PENIS)) {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is exposed, so, due to your androgynous appearance, strangers treat you as "
											+UtilText.generateSingularDeterminer(Gender.N_NEUTER.getName())+" "+Gender.N_NEUTER.getName()+"."
									:UtilText.parse(this,
											"Due to [npc.her] genderless mound being exposed, combined with [npc.her] androgynous appearance, everyone can tell that [npc.she]'s "
												+UtilText.generateSingularDeterminer(Gender.N_NEUTER.getName())+" "+Gender.N_NEUTER.getName()+"."),
									Gender.N_NEUTER);
							
						} else {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is concealed, so, due to your androgynous appearance, strangers treat you as "
											+UtilText.generateSingularDeterminer(Gender.N_V_TOMBOY.getName())+" "+Gender.N_V_TOMBOY.getName()+"."
									:UtilText.parse(this,
											"Due to [npc.her] androgynous appearance, everyone assumes that [npc.she]'s "
												+UtilText.generateSingularDeterminer(Gender.N_V_TOMBOY.getName())+" "+Gender.N_V_TOMBOY.getName()+"."),
									Gender.N_V_TOMBOY);
						}
					}
				}
			}
			
		// Masculine:
		} else {
			if(hasBreasts()) {
				if(visibleVagina && visiblePenis) {
					// Exposed penis and vagina:
					return new GenderAppearance(
							isPlayer()
							?"Due to the fact that both your [pc.vagina] and [pc.penis] are exposed, as well as the fact that you have [pc.breastSize] breasts, everyone can tell that you're [pc.a_gender] on first glance."
							:UtilText.parse(this,
									"Due to the fact that both [npc.her] [npc.vagina] and [npc.penis] are exposed, as well as the fact that [npc.she] has [npc.breastSize] breasts, everyone can tell that [npc.she]'s [npc.a_gender] on first glance."),
							Gender.M_P_V_B_HERMAPHRODITE);
						
				} else if(visibleVagina) {
					// Exposed vagina and obvious penis bulge:
					if(bulgeFromCock) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your exposed [pc.vagina] and [pc.breastSize] breasts, reveals to everyone that you're [pc.a_gender]."
								:UtilText.parse(this,
										"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, reveals to everyone that [npc.she]'s [npc.a_gender]."),
								Gender.M_P_V_B_HERMAPHRODITE);
						
					} else if (bulgeFromBalls) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your exposed [pc.vagina] and [pc.breastSize] breasts, reveals to everyone that you're [pc.a_gender]."
								:UtilText.parse(this,
										"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, reveals to everyone that [npc.she]'s [npc.a_gender]."),
								Gender.M_P_V_B_HERMAPHRODITE);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina] and [pc.breastSize] breasts, and the fact that your [pc.penis] remains concealed, everyone assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.M_V_B_BUTCH.getName())+" "+Gender.M_V_B_BUTCH.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.M_V_B_BUTCH.getName())+" "+Gender.M_V_B_BUTCH.getName()+" on first glance."),
								Gender.M_V_B_BUTCH);
						
					} else {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina] and [pc.breastSize] breasts, everyone correctly assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.M_V_B_BUTCH.getName())+" "+Gender.M_V_B_BUTCH.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.M_V_B_BUTCH.getName())+" "+Gender.M_V_B_BUTCH.getName()+" on first glance."),
								Gender.M_V_B_BUTCH);
					}
					
				} else if(visiblePenis) {
					// Exposed penis:
					return new GenderAppearance(
							isPlayer()
							?"Due to your exposed [pc.penis] and [pc.breastSize] breasts, everyone assumes that you're "
									+UtilText.generateSingularDeterminer(Gender.M_P_B_BUSTYBOY.getName())+" "+Gender.M_P_B_BUSTYBOY.getName()+" on first glance."
							:UtilText.parse(this,
									"Due to [npc.her] exposed [npc.penis] and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
										+UtilText.generateSingularDeterminer(Gender.M_P_B_BUSTYBOY.getName())+" "+Gender.M_P_B_BUSTYBOY.getName()+" on first glance."),
							Gender.M_P_B_BUSTYBOY);
					
				} else {
					// Obvious bulge:
					if(bulgeFromCock) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your masculine appearance and [pc.breastSize] breasts, leads everyone to believe that you're "
										+UtilText.generateSingularDeterminer(Gender.M_P_B_BUSTYBOY.getName())+" "+Gender.M_P_B_BUSTYBOY.getName()+"."
								:UtilText.parse(this,
										"Due to the [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] masculine appearance and [npc.breastSize] breasts, leads everyone to believe that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.M_P_B_BUSTYBOY.getName())+" "+Gender.M_P_B_BUSTYBOY.getName()+"."),
								Gender.M_P_B_BUSTYBOY);
						
					} else if (bulgeFromBalls) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your masculine appearance and [pc.breastSize] breasts, leads everyone to believe that you're "
										+UtilText.generateSingularDeterminer(Gender.M_P_B_BUSTYBOY.getName())+" "+Gender.M_P_B_BUSTYBOY.getName()+"."
								:UtilText.parse(this,
										"Due to the [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] masculine appearance and [npc.breastSize] breasts, leads everyone to believe that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.M_P_B_BUSTYBOY.getName())+" "+Gender.M_P_B_BUSTYBOY.getName()+"."),
								Gender.M_P_B_BUSTYBOY);
					}
					
					if(hasPenis()) {
						// Correctly assume busty boy:
						return new GenderAppearance(
								isPlayer()
								?"Your [pc.penis] is concealed, so, due to your masculine appearance and [pc.breastSize] breasts, everyone correctly assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.M_P_B_BUSTYBOY.getName())+" "+Gender.M_P_B_BUSTYBOY.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] masculine appearance and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.M_P_B_BUSTYBOY.getName())+" "+Gender.M_P_B_BUSTYBOY.getName()+" on first glance."),
								Gender.M_P_B_BUSTYBOY);
						
					} else if(hasVagina()) {
						// Assume bustyboy:
						return new GenderAppearance(
								isPlayer()
								?"Your masculine appearance and [pc.breastSize] breasts leads everyone to assume that you're "
										+UtilText.generateSingularDeterminer(Gender.M_P_B_BUSTYBOY.getName())+" "+Gender.M_P_B_BUSTYBOY.getName()+"."
								:UtilText.parse(this,
										"Due to [npc.her] masculine appearance and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.M_P_B_BUSTYBOY.getName())+" "+Gender.M_P_B_BUSTYBOY.getName()+"."),
								Gender.M_P_B_BUSTYBOY);
						
					} else {
						if(isCoverableAreaExposed(CoverableArea.VAGINA) && isCoverableAreaExposed(CoverableArea.PENIS)) {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is exposed, so, due to your masculine appearance and [pc.breastSize] breasts, strangers treat you as "
											+UtilText.generateSingularDeterminer(Gender.M_B_MANNEQUIN.getName())+" "+Gender.M_B_MANNEQUIN.getName()+"."
									:UtilText.parse(this,
											"Due to [npc.her] genderless mound being exposed, combined with [npc.her] masculine appearance and [npc.breastSize] breasts, everyone can tell that [npc.she]'s "
												+UtilText.generateSingularDeterminer(Gender.M_B_MANNEQUIN.getName())+" "+Gender.M_B_MANNEQUIN.getName()+"."),
									Gender.M_B_MANNEQUIN);
							
						} else {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is concealed, so, due to your masculine appearance and [pc.breastSize] breasts, strangers treat you as "
											+UtilText.generateSingularDeterminer(Gender.M_V_B_BUTCH.getName())+" "+Gender.M_V_B_BUTCH.getName()+"."
									:UtilText.parse(this,
											"Due to [npc.her] masculine appearance and [npc.breastSize] breasts, everyone assumes that [npc.she]'s "
												+UtilText.generateSingularDeterminer(Gender.M_V_B_BUTCH.getName())+" "+Gender.M_V_B_BUTCH.getName()+"."),
									Gender.M_V_B_BUTCH);
						}
					}
				}
				
			// No breasts:
			} else {
				if(visibleVagina && visiblePenis) {
					// Exposed penis and vagina:
					return new GenderAppearance(
							isPlayer()
							?"Due to the fact that both your [pc.vagina] and [pc.penis] are exposed, everyone can tell that you're [pc.a_gender] on first glance."
							:UtilText.parse(this,
									"Due to the fact that both [npc.her] [npc.vagina] and [npc.penis] are exposed, everyone can tell that [npc.she]'s [npc.a_gender] on first glance."),
							Gender.M_P_V_HERMAPHRODITE);
						
				} else if(visibleVagina) {
					// Exposed vagina and obvious penis bulge:
					if(bulgeFromCock) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your exposed [pc.vagina], reveals to everyone that you're [pc.a_gender]."
								:UtilText.parse(this,
										"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina], reveals to everyone that [npc.she]'s [npc.a_gender]."),
								Gender.M_P_V_HERMAPHRODITE);
						
					} else if (bulgeFromBalls) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your exposed [pc.vagina], reveals to everyone that you're [pc.a_gender]."
								:UtilText.parse(this,
										"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina], reveals to everyone that [npc.she]'s [npc.a_gender]."),
								Gender.M_P_V_HERMAPHRODITE);
					}
					
					if(hasPenis()) {
						// Assume cuntboy, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina], and the fact that your [pc.penis] remains concealed, everyone assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.M_V_CUNTBOY.getName())+" "+Gender.M_V_CUNTBOY.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] exposed [npc.vagina], everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.M_V_CUNTBOY.getName())+" "+Gender.M_V_CUNTBOY.getName()+" on first glance."),
								Gender.M_V_CUNTBOY);
						
					} else {
						// Correctly assume cuntboy:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina], everyone correctly assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.M_V_CUNTBOY.getName())+" "+Gender.M_V_CUNTBOY.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] exposed [npc.vagina], everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.M_V_CUNTBOY.getName())+" "+Gender.M_V_CUNTBOY.getName()+" on first glance."),
								Gender.M_V_CUNTBOY);
					}
					
				} else if(visiblePenis) {
					// Exposed penis:
					return new GenderAppearance(
							isPlayer()
							?"Due to your exposed [pc.penis], everyone assumes that you're "
									+UtilText.generateSingularDeterminer(Gender.M_P_MALE.getName())+" "+Gender.M_P_MALE.getName()+" on first glance."
							:UtilText.parse(this,
									"Due to [npc.her] exposed [npc.penis], everyone assumes that [npc.she]'s "
										+UtilText.generateSingularDeterminer(Gender.M_P_MALE.getName())+" "+Gender.M_P_MALE.getName()+" on first glance."),
							Gender.M_P_MALE);
					
				} else {
					// Obvious bulge:
					if(bulgeFromCock) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your masculine appearance, leads everyone to believe that you're "
										+UtilText.generateSingularDeterminer(Gender.M_P_MALE.getName())+" "+Gender.M_P_MALE.getName()+"."
								:UtilText.parse(this,
										"Due to the [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] masculine appearance, leads everyone to believe that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.M_P_MALE.getName())+" "+Gender.M_P_MALE.getName()+"."),
								Gender.M_P_MALE);
						
					} else if (bulgeFromBalls) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your masculine appearance, leads everyone to believe that you're "
										+UtilText.generateSingularDeterminer(Gender.M_P_MALE.getName())+" "+Gender.M_P_MALE.getName()+"."
								:UtilText.parse(this,
										"Due to the [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] masculine appearance, leads everyone to believe that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.M_P_MALE.getName())+" "+Gender.M_P_MALE.getName()+"."),
								Gender.M_P_MALE);
					}
					
					if(hasPenis()) {
						// Assume male:
						return new GenderAppearance(
								isPlayer()
								?"Your [pc.penis] is concealed, so, due to your masculine appearance, everyone assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.M_P_MALE.getName())+" "+Gender.M_P_MALE.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] masculine appearance, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.M_P_MALE.getName())+" "+Gender.M_P_MALE.getName()+" on first glance."),
								Gender.M_P_MALE);
						
					} else if(hasVagina()) {
						// Assume male:
						return new GenderAppearance(
								isPlayer()
								?"Your masculine appearance leads everyone to assume that you're "
										+UtilText.generateSingularDeterminer(Gender.M_P_MALE.getName())+" "+Gender.M_P_MALE.getName()+"."
								:UtilText.parse(this,
										"Due to [npc.her] masculine appearance, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.M_P_MALE.getName())+" "+Gender.M_P_MALE.getName()+"."),
								Gender.M_P_MALE);
						
					} else {
						if(isCoverableAreaExposed(CoverableArea.VAGINA) && isCoverableAreaExposed(CoverableArea.PENIS)) {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is exposed, so, due to your masculine appearance, strangers treat you as "
											+UtilText.generateSingularDeterminer(Gender.M_MANNEQUIN.getName())+" "+Gender.M_MANNEQUIN.getName()+"."
									:UtilText.parse(this,
											"Due to [npc.her] genderless mound being exposed, combined with [npc.her] masculine appearance, everyone can tell that [npc.she]'s "
												+UtilText.generateSingularDeterminer(Gender.M_MANNEQUIN.getName())+" "+Gender.M_MANNEQUIN.getName()+"."),
									Gender.M_MANNEQUIN);
							
						} else {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is concealed, so, due to your masculine appearance, strangers treat you as "
											+UtilText.generateSingularDeterminer(Gender.M_P_MALE.getName())+" "+Gender.M_P_MALE.getName()+"."
									:UtilText.parse(this,
											"Due to [npc.her] masculine appearance, everyone assumes that [npc.she]'s "
												+UtilText.generateSingularDeterminer(Gender.M_P_MALE.getName())+" "+Gender.M_P_MALE.getName()+"."),
									Gender.M_P_MALE);
						}
					}
				}
			}
			
		}
	}


	public Gender getGender() {
		return body.getGender();
	}
	
	//TODO Improve isFeminine method to take into account knowledge of the character's gender.
	public boolean isFeminine() {
		boolean isFeminine = body.isFeminine();
		
		if(Femininity.valueOf(getFemininityValue()) == Femininity.ANDROGYNOUS) {
			switch(Main.getProperties().androgynousIdentification){
				case FEMININE:
					isFeminine = true;
					break;
				case CLOTHING_FEMININE:
					isFeminine = getClothingAverageFemininity() >= 50;
					break;
				case CLOTHING_MASCULINE:
					isFeminine = getClothingAverageFemininity() > 50;
					break;
				case MASCULINE:
					isFeminine = false;
					break;
				default:
					break;
			}
		}
//		else {
//			return body.getGender().isFeminine();
//		}
		return isFeminine;
	}


	public Race getRace() {
		return body.getRace();
	}
	
	public Race getAntennaRace() {
		return body.getAntenna().getType().getRace();
	}
	
	public Race getArmRace() {
		return body.getArm().getType().getRace();
	}

	public Race getAssRace() {
		return body.getAss().getType().getRace();
	}

	public Race getBreastRace() {
		return body.getBreast().getType().getRace();
	}

	public Race getFaceRace() {
		return body.getFace().getType().getRace();
	}

	public Race getEyeRace() {
		return body.getEye().getType().getRace();
	}

	public Race getEarRace() {
		return body.getEar().getType().getRace();
	}

	public Race getHairRace() {
		return body.getHair().getType().getRace();
	}

	public Race getLegRace() {
		return body.getLeg().getType().getRace();
	}

	public Race getSkinRace() {
		return body.getSkin().getType().getRace();
	}

	public Race getHornRace() {
		return body.getHorn().getType().getRace();
	}

	public Race getPenisRace() {
		return body.getPenis().getType().getRace();
	}

	public Race getTailRace() {
		return body.getTail().getType().getRace();
	}
	
	public Race getTongueRace() {
		return body.getFace().getTongue().getType().getRace();
	}

	public Race getVaginaRace() {
		return body.getVagina().getType().getRace();
	}

	public Race getWingRace() {
		return body.getWing().getType().getRace();
	}

	public String postTransformationCalculation() {
		return postTransformationCalculation(true);
	}
	public String postTransformationCalculation(boolean displayColourDiscovered) {
		StringBuilder postTFSB = new StringBuilder();
		// If this is the first time getting this covering type:
		for(BodyPartInterface bp : body.getAllBodyParts()) {
			BodyCoveringType bct = bp.getType().getBodyCoveringType();
			if(!body.getBodyCoveringTypesDiscovered().contains(bct)) {
				if(bct!=null) {
					body.getBodyCoveringTypesDiscovered().add(bct);
					
					String bctName = bct.getRace().getName();
					if(bct == BodyCoveringType.HORN) {
						bctName = "horn";
					}
					if(bct == BodyCoveringType.ANTLER_REINDEER) {
						bctName = "antler";
					}
					
					if(displayColourDiscovered) {
						if(isPlayer()) {
							postTFSB.append(
									"<b>You have discovered that your natural</b> "
									+ (bct == BodyCoveringType.HORN
										? "<b>"+bctName+"'s"
										: "<b style='color:"+bct.getRace().getColour().toWebHexString()+";'>"+bctName+"'s</b> <b>"+bct.getName(this))
									+" colour is "+getCovering(bct).getColourDescriptor(true, false)+"!</b>");
						} else {
							postTFSB.append(UtilText.parse(this,
									"<b>[npc.Name] has discovered that [npc.her] natural</b> "
									+ (bct == BodyCoveringType.HORN
										? "<b>"+bctName+"'s"
										: "<b style='color:"+bct.getRace().getColour().toWebHexString()+";'>"+bctName+"'s</b> <b>"+bct.getName(this))
									+" colour is "+getCovering(bct).getColourDescriptor(true, false)+"!</b>"));
						}
					}
				}
			}
		}
		
		body.calculateRace();
		calculateSpecialAttacks();

		postTFSB.append(inventory.calculateClothingPostTransformation(this));
		
		updateInventoryListeners();
		updateAttributeListeners();
		
		return postTFSB.toString();
	}

	public Set<BodyCoveringType> getBodyCoveringTypesDiscovered(){
		return body.getBodyCoveringTypesDiscovered();
	}
	
	public BodyCoveringType getBodyHairCoveringType(Race race) {
		switch(race) {
			case ANGEL:
				return BodyCoveringType.BODY_HAIR_ANGEL;
			case CAT_MORPH:
				return BodyCoveringType.BODY_HAIR_FELINE_FUR;
			case COW_MORPH:
				return BodyCoveringType.BODY_HAIR_BOVINE_FUR;
			case DEMON:
				return BodyCoveringType.BODY_HAIR_DEMON;
			case DOG_MORPH:
				return BodyCoveringType.BODY_HAIR_CANINE_FUR;
			case ALLIGATOR_MORPH:
				return BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR;
			case HARPY:
				return BodyCoveringType.BODY_HAIR_HARPY;
			case HORSE_MORPH:
				return BodyCoveringType.BODY_HAIR_HORSE_HAIR;
			case REINDEER_MORPH:
				return BodyCoveringType.BODY_HAIR_REINDEER_HAIR;
			case HUMAN:
				return BodyCoveringType.BODY_HAIR_HUMAN;
			case SLIME:
				return BodyCoveringType.BODY_HAIR_SLIME;
			case SQUIRREL_MORPH:
				return BodyCoveringType.BODY_HAIR_SQUIRREL_FUR;
			case WOLF_MORPH:
				return BodyCoveringType.BODY_HAIR_LYCAN_FUR;
		}
		return BodyCoveringType.BODY_HAIR_HUMAN;
	}
	
	public BodyCoveringType getBodyHairCoveringType() {
		return getBodyHairCoveringType(getRace());
	}

	public RaceStage getRaceStage() {
		return body.getRaceStage();
	}

	// Femininity:

	public Femininity getFemininity() {
		return Femininity.valueOf(body.getFemininity());
	}
	
	public int getFemininityValue() {
		return body.getFemininity();
	}
	
	public String setFemininity(int femininity) {
		
		String beardLoss = "";
		
		if(femininity>=Femininity.ANDROGYNOUS.getMinimumFemininity() && this.getFacialHair()!=BodyHair.ZERO_NONE) {
			if(isPlayer()) {
				beardLoss = "<p>"
								+ "As your body shifts into a more feminine form, you feel your facial hair falling out; evidence that you're no longer able to grow a beard."
							+ "</p>";
			} else {
				beardLoss = "<p>"
								+ "As [npc.her] body shifts into a more feminine form, [npc.her] facial hair falls out; evidence that [npc.she]'s no longer able to grow a beard."
							+ "</p>";
			}
		}
		
		if (body.getFemininity() < femininity) {
			if (body.setFemininity(femininity)) {
				if(isPlayer()) {
						return "<p>"
									+ "You feel your body subtly shifting to become <b style='color:" + Colour.FEMININE.toWebHexString() + ";'>more feminine</b>.</br>"
									+ "You now have <b style='color:"+ Femininity.valueOf(getFemininityValue()).getColour().toWebHexString() + ";'>" + Femininity.getFemininityName(getFemininityValue(), true) + "</b> body."
							+ "</p>"
							+beardLoss;
				} else {
					return UtilText.parse(this,
							"<p>"
								+ "[npc.Name]'s body subtly shifts to become <b style='color:" + Colour.FEMININE.toWebHexString() + ";'>more feminine</b>.</br>"
								+ "[npc.She] now has <b style='color:"+ Femininity.valueOf(getFemininityValue()).getColour().toWebHexString() + ";'>" + Femininity.getFemininityName(getFemininityValue(), true) + "</b> body."
							+ "</p>"
							+beardLoss);
				}
			}
		} else {
			if (body.setFemininity(femininity)) {
				if(isPlayer()) {
					return "<p>"
								+ "You feel your body subtly shifting to become <b style='color:" + Colour.MASCULINE.toWebHexString() + ";'>more masculine</b>.</br>"
								+ "You have <b style='color:"+ Femininity.valueOf(getFemininityValue()).getColour().toWebHexString() + ";'>" + Femininity.getFemininityName(getFemininityValue(), true) + "</b> body."
							+ "</p>"
							+beardLoss;
				} else {
					return UtilText.parse(this,
							"<p>"
								+ "[npc.Name]'s body subtly shifts to become <b style='color:" + Colour.MASCULINE.toWebHexString() + ";'>more masculine</b>.</br>"
								+ "[npc.She] now has <b style='color:"+ Femininity.valueOf(getFemininityValue()).getColour().toWebHexString() + ";'>" + Femininity.getFemininityName(getFemininityValue(), true) + "</b> body."
							+ "</p>"
							+beardLoss);
				}
			}
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Your femininity doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>[npc.Name]'s femininity doesn't change...</span>")
				+ "</p>";
		}
	}

	public String incrementFemininity(int increment) {
		return setFemininity(getFemininityValue() + increment);
	}

	// Body size:
	
	public BodySize getBodySize() {
		return BodySize.valueOf(body.getBodySize());
	}
	
	public int getBodySizeValue() {
		return body.getBodySize();
	}

	public String setBodySize(int bodySize) {
		if (body.getBodySize() < bodySize) {
			if (body.setBodySize(bodySize)) {
				if(isPlayer()) {
					return "<p>"
								+ "You feel your body shifting and expanding as <b style='color:" + Colour.BODY_SIZE_THREE.toWebHexString() + ";'>you grow larger</b>.</br>"
								+ "You now have <b style='color:"+ BodySize.valueOf(getBodySizeValue()).getColour().toWebHexString() + ";'>" + BodySize.valueOf(getBodySizeValue()).getName(true)+ "</b>, "
									+ Muscle.valueOf(getMuscleValue()).getName(false) + " body, giving you "+BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue())).getName(true)+" appearance."
							+ "</p>";
				} else {
					return UtilText.parse(this,
							"<p>"
								+ "[npc.Name]'s body shifts and expands as <b style='color:" + Colour.BODY_SIZE_THREE.toWebHexString() + ";'>[npc.she] grows larger</b>.</br>"
								+ "[npc.She] now has <b style='color:"+ BodySize.valueOf(getBodySizeValue()).getColour().toWebHexString() + ";'>" + BodySize.valueOf(getBodySizeValue()).getName(true) + "</b>, "
										+ Muscle.valueOf(getMuscleValue()).getName(false) + " body, giving [npc.herHim] "+BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue())).getName(true)+" appearance."
							+ "</p>");
				}
			}
		} else {
			if (body.setBodySize(bodySize)) {
				if(isPlayer()) {
					return "<p>"
							+ "You feel your body shifting and narrowing down as <b style='color:" + Colour.BODY_SIZE_ONE.toWebHexString() + ";'>you get slimmer</b>.</br>"
							+ "You now have <b style='color:"+ BodySize.valueOf(getBodySizeValue()).getColour().toWebHexString() + ";'>" + BodySize.valueOf(getBodySizeValue()).getName(true) + "</b>, "
									+ Muscle.valueOf(getMuscleValue()).getName(false) + " body, giving you "+BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue())).getName(true)+" appearance."
						+ "</p>";
				} else {
					return UtilText.parse(this,
							"<p>"
								+ "[npc.Name]'s body shifts and narrows down as <b style='color:" + Colour.BODY_SIZE_ONE.toWebHexString() + ";'>[npc.she] gets slimmer</b>.</br>"
								+ "[npc.She] now has <b style='color:"+ BodySize.valueOf(getBodySizeValue()).getColour().toWebHexString() + ";'>" + BodySize.valueOf(getBodySizeValue()).getName(true) + "</b>, "
										+ Muscle.valueOf(getMuscleValue()).getName(false) + " body, giving [npc.herHim] "+BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue())).getName(true)+" appearance."
							+ "</p>");
				}
			}
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Your body's size doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>[npc.Name]'s body doesn't change...</span>")
				+ "</p>";
		}
	}

	public String incrementBodySize(int increment) {
		return setBodySize(getBodySizeValue() + increment);
	}
	
	// Muscle:
	
	public Muscle getMuscle() {
		return Muscle.valueOf(body.getMuscle());
	}
	
	public int getMuscleValue() {
		return body.getMuscle();
	}

	public String setMuscle(int muscle) {
		if (body.getMuscle() < muscle) {
			if (body.setMuscle(muscle)) {
				if(isPlayer()) {
					return "<p>"
								+ "You feel your body shifting as <b style='color:" + Colour.MUSCLE_THREE.toWebHexString() + ";'>you get more muscular</b>.</br>"
								+ "You now have <b style='color:"+ Muscle.valueOf(getMuscleValue()).getColour().toWebHexString() + ";'>" + Muscle.valueOf(getMuscleValue()).getName(true) + "</b>, "
										+ BodySize.valueOf(getBodySizeValue()).getName(false) + " body, giving you "+BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue())).getName(true)+" appearance."
							+ "</p>";
				} else {
					return UtilText.parse(this,
							"<p>"
								+ "[npc.Name]'s body shifts as <b style='color:" + Colour.MUSCLE_THREE.toWebHexString() + ";'>[npc.she] gets more muscular</b>.</br>"
								+ "[npc.She] now has <b style='color:"+ Muscle.valueOf(getMuscleValue()).getColour().toWebHexString() + ";'>" + Muscle.valueOf(getMuscleValue()).getName(true) + "</b>, "
										+ BodySize.valueOf(getBodySizeValue()).getName(false) + " body, giving [npc.her] "+BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue())).getName(true)+" appearance."
							+ "</p>");
				}
			}
		} else {
			if (body.setMuscle(muscle)) {
				if(isPlayer()) {
					return "<p>"
							+ "You feel your body shifting as <b style='color:" + Colour.MUSCLE_ONE.toWebHexString() + ";'>you lose some of your muscle</b>.</br>"
							+ "You now have <b style='color:"+ Muscle.valueOf(getMuscleValue()).getColour().toWebHexString() + ";'>" + Muscle.valueOf(getMuscleValue()).getName(true) + "</b>, "
									+ BodySize.valueOf(getBodySizeValue()).getName(false) + " body, giving you "+BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue())).getName(true)+" appearance."
						+ "</p>";
				} else {
					return UtilText.parse(this,
							"<p>"
								+ "[npc.Name]'s body shifts as <b style='color:" + Colour.MUSCLE_ONE.toWebHexString() + ";'>[npc.she] loses some of [npc.her] muscle</b>.</br>"
								+ "[npc.She] now has <b style='color:"+ Muscle.valueOf(getMuscleValue()).getColour().toWebHexString() + ";'>" + Muscle.valueOf(getMuscleValue()).getName(true) + "</b>, "
										+ BodySize.valueOf(getBodySizeValue()).getName(false) + " body, giving [npc.her] "+BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue())).getName(true)+" appearance."
							+ "</p>");
				}
			}
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Your body's muscles don't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>[npc.Name]'s muscles don't change...</span>")
				+ "</p>";
		}
	}

	public String incrementMuscle(int increment) {
		return setMuscle(getMuscleValue() + increment);
	}
	

	public BodyShape getBodyShape() {
		return BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue()));
	}

	/**
	 * @return The character's height in cm.
	 */
	public int getHeightValue() {
		return body.getHeightValue();
	}
	
	public Height getHeight() {
		return body.getHeight();
	}

	/**
	 * @return Formatted description of height change.
	 */
	public String setHeight(int height) {
		if (body.getHeightValue() < height) {
			if (body.setHeight(height))
				return isPlayer()
						? "<p>"
							+ "The world around you seems slightly further away than it used to be, but after a moment you realise that you've just <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>grown taller</b>."
							+ "</br>"
							+ "You are now <b>" + getHeightValue() + "cm</b> tall."
						+ "</p>"
						: UtilText.parse(this,
							"<p>"
								+ "[npc.She] sways from side to side a little, [npc.her] balance suddenly thrown off by the fact that [npc.she]'s just <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>grown taller</b>."
							+ "</p>");
		} else {
			if (body.setHeight(height))
				return isPlayer()
						? "<p>"
							+ "The world around you suddenly seems slightly closer than it used to be, but after a moment you realise that you've just <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>become shorter</b>."
							+ "</br>"
							+ "You are now <b>" + getHeightValue() + "cm</b> tall."
						+ "</p>"
						: UtilText.parse(this,
							"<p>"
								+ "[npc.She] shrinks down, <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>becoming noticeably shorter</b>."
							+ "</p>");
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Your height doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>[npc.Name]'s height doesn't change...</span>")
				+ "</p>";
		}
	}
	
	/**
	 * @return Formatted description of height change.
	 */
	public String incrementHeight(int increment) {
		return setHeight(getHeightValue() + increment);
	}

	/**
	 * <b>THIS IS NOT A FINISHED METHOD.</b> TODO
	 * 
	 * @return The character's weight. Weight is calculated from height and
	 *         takes into consideration special race modifiers.
	 */
	public int getWeight() {
		return body.getCalculatedWeight();
	}
	
	public boolean isAbleToFly() {
		return body.isAbleToFly();
	}
	
	// Pubic Hair:
	public BodyHair getPubicHair() {
		return body.getPubicHair();
	}
	public Covering getPubicHairType() {
		return getCovering(getBodyHairCoveringType(getSkinType().getRace()));
	}
	public String setPubicHair(BodyHair pubicHair) {
		if(getPubicHair() == pubicHair) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
			
		} else {
			UtilText.transformationContentSB.setLength(0);
			
			switch(pubicHair) {
				case ZERO_NONE:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>There is no longer any trace of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>There is no longer any trace of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case ONE_STUBBLE:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a stubbly patch of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has a stubbly patch of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case TWO_MANICURED:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a well-manicured patch of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has a well-manicured patch of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case THREE_TRIMMED:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a trimmed patch of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has a trimmed patch of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case FOUR_NATURAL:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a natural bush of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has a natural bush of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case FIVE_UNKEMPT:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have an unkempt mass of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has an unkempt mass of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case SIX_BUSHY:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a thick mass of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has a thick mass of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case SEVEN_WILD:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a wild, bushy mass of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has a wild, bushy mass of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
			}
		}
		
		body.setPubicHair(pubicHair);

		return UtilText.transformationContentSB.toString();
	}
	public String setPubicHair(int value) {
		return setPubicHair(BodyHair.getBodyHairFromValue(value));
	}
	public String incrementPubicHair(int increment) {
		int value = getPubicHair().getValue() + increment;
		if(value < 0) {
			value = 0;
		} else if(value > BodyHair.SEVEN_WILD.getValue()) {
			value = BodyHair.SEVEN_WILD.getValue();
		}
		return setPubicHair(BodyHair.getBodyHairFromValue(value));
	}
	
	// Piercings:
	
	// Navel/Stomach (I wasn't very consistent with the names...):
	public boolean isPiercedNavel() {
		return body.isPiercedStomach();
	}
	/**
	 * @return Formatted description of piercing change.
	 */
	public String setPiercedNavel(boolean pierced) {
		if (!isPiercedNavel() && pierced) {
			body.setPiercedStomach(true);
			if(isPlayer()) {
				return "<p>"
							+ "Your navel is now <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>pierced</b>."
						+ "</p>";
			}else {
				return UtilText.parse(this,
						"<p>"
							+ "[npc.Her] navel is now <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>pierced</b>."
						+ "</p>");
			}
			
		} else if (isPiercedNavel() && !pierced) {
			body.setPiercedStomach(false);
			if(isPlayer()) {
				return "<p>"
							+ "Your navel is <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>no longer pierced</b>."
						+ "</p>";
			}else {
				return UtilText.parse(this,
						"<p>"
							+ "[npc.Her] navel is <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>no longer pierced</b>."
						+ "</p>");
			}
		} else {
			return "<p>"
						+ "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Nothing seems to happen.</span>"
					+ "</p>";
		}
	}

	// Body parts in alphabetical order:
	
	
	
	// ------------------------------ Antennae: ------------------------------ //
	
	// Type:
	public AntennaType getAntennaType() {
		return body.getAntenna().getType();
	}
	public String setAntennaType(AntennaType hornType) {
		return body.getAntenna().setType(this, hornType);
	}
	// Misc.:
	public boolean hasAntennas() {
		return body.getAntenna().getType() != AntennaType.NONE;
	}
	// Names:
	public String getAntennaName() {
		return body.getAntenna().getName(this);
	}
	public String getAntennaNameSingular() {
		return body.getAntenna().getNameSingular(this);
	}
	public String getAntennaName(boolean withDescriptor) {
		return body.getAntenna().getName(this, withDescriptor);
	}
	public String getAntennaDescriptor() {
		return body.getAntenna().getDescriptor(this);
	}
	public String getAntennaDeterminer() {
		return body.getAntenna().getDeterminer(this);
	}
	public String getAntennaPronoun() {
		return body.getAntenna().getType().getPronoun();
	}
	// Rows:
	public int getAntennaRows() {
		return body.getAntenna().getAntennaRows();
	}
	public String setAntennaRows(int rows) {
		return body.getAntenna().setAntennaRows(this, rows);
	}
	public String incrementAntennaRows(int increment) {
		return body.getAntenna().setAntennaRows(this, getAntennaRows()+increment);
	}
	
	
	
	// ------------------------------ Arms: ------------------------------ //
	
	// Type:
	public ArmType getArmType() {
		return body.getArm().getType();
	}
	public String setArmType(ArmType type) {
		return body.getArm().setType(this, type);
	}
	// Names:
	public String getArmName() {
		return body.getArm().getName(this);
	}
	public String getArmNameSingular() {
		return body.getArm().getNameSingular(this);
	}
	public String getArmName(boolean withDescriptor) {
		return body.getArm().getName(this, withDescriptor);
	}
	public String getArmDescriptor() {
		return body.getArm().getDescriptor(this);
	}
	public String getArmDeterminer() {
		return body.getArm().getDeterminer(this);
	}
	public String getArmPronoun() {
		return body.getArm().getType().getPronoun();
	}
	// Rows:
	public int getArmRows() {
		return body.getArm().getArmRows();
	}
	public String setArmRows(int armRows) {
		return body.getArm().setArmRows(this, armRows);
	}
	public String incrementArmRows(int increment) {
		return setArmRows(getArmRows() + increment);
	}
	// Nail polish:
	public Covering getHandNailPolish() {
		return getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS);
	}
	public String setHandNailPolish(Covering nailPolish) {
		body.getCoverings().put(nailPolish.getType(), nailPolish);
		if(isPlayer()) {
			if(nailPolish.getPattern()==CoveringPattern.NONE) {
				return "<p>"
							+ "You are [style.boldShrink(not wearing)] any nail polish."
						+ "</p>";
			} else {
				return "<p>"
							+ "You are [style.boldGrow(now wearing)] "+nailPolish.getColourDescriptor(true, false)+" nail polish."
						+ "</p>";
			}
			
		} else {
			if(nailPolish.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any nail polish."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+nailPolish.getColourDescriptor(true, false)+" nail polish."
						+ "</p>");
			}
		}
	}
	// Underarm hair:
	public BodyHair getUnderarmHair() {
		return body.getArm().getUnderarmHair();
	}
	public Covering getUnderarmHairType() {
		return getCovering(getBodyHairCoveringType(getArmType().getRace()));
	}
	public String setUnderarmHair(BodyHair underarmHair) {
		return body.getArm().setUnderarmHair(this, underarmHair);
	}
	public String setUnderarmHair(int value) {
		return body.getArm().setUnderarmHair(this, BodyHair.getBodyHairFromValue(value));
	}
	public String incrementUnderarmHair(int increment) {
		int value = getUnderarmHair().getValue() + increment;
		if(value < 0) {
			value = 0;
		} else if(value > BodyHair.SEVEN_WILD.getValue()) {
			value = BodyHair.SEVEN_WILD.getValue();
		}
		return body.getArm().setUnderarmHair(this, BodyHair.getBodyHairFromValue(value));
	}
	
	
	
	// ------------------------------ Ass: ------------------------------ //
	
	// Type:
	public AssType getAssType() {
		return body.getAss().getType();
	}
	public String setAssType(AssType type) {
		return body.getAss().setType(this, type);
	}
	// Names:
	public String getAssName() {
		return body.getAss().getName(this);
	}
	public String getAssNameSingular() {
		return body.getAss().getNameSingular(this);
	}
	public String getAssName(boolean withDescriptor) {
		return body.getAss().getName(this, withDescriptor);
	}
	public String getAssDescriptor() {
		return body.getAss().getDescriptor(this);
	}
	public String getAssDeterminer() {
		return body.getAss().getDeterminer(this);
	}
	public String getAssPronoun() {
		return body.getAss().getType().getPronoun();
	}
	public String getAssDescription() {
		return body.getAssDescription(this);
	}
	// Names:
	public String getAnusName() {
		return body.getAss().getAnus().getName(this);
	}
	public String getAnusNameSingular() {
		return body.getAss().getAnus().getNameSingular(this);
	}
	public String getAnusName(boolean withDescriptor) {
		return body.getAss().getAnus().getName(this, withDescriptor);
	}
	public String getAnusDescriptor() {
		return body.getAss().getAnus().getDescriptor(this);
	}
	public String getAnusDeterminer() {
		return body.getAss().getAnus().getDeterminer(this);
	}
	public String getAnusPronoun() {
		return body.getAss().getAnus().getType().getPronoun();
	}
	// Size:
	public AssSize getAssSize() {
		return body.getAss().getAssSize();
	}
	public String setAssSize(int assSize) {
		return body.getAss().setAssSize(this, assSize);
	}
	public String incrementAssSize(int assSize) {
		return body.getAss().setAssSize(this, getAssSize().getValue() + assSize);
	}
	// Hip size:
	public HipSize getHipSize() {
		return body.getAss().getHipSize();
	}
	public String setHipSize(int hipSize) {
		return body.getAss().setHipSize(this, hipSize);
	}
	public String incrementHipSize(int hipSize) {
		return body.getAss().setHipSize(this, getHipSize().getValue() + hipSize);
	}
	// Ass hair:
	public BodyHair getAssHair() {
		return body.getAss().getAnus().getAssHair();
	}
	public Covering getAssHairType() {
		return body.getAss().getAnus().getAssHairType(this);
	}
	public String setAssHair(BodyHair assHair) {
		return body.getAss().getAnus().setAssHair(this, assHair);
	}
	public String setAssHair(int value) {
		return body.getAss().getAnus().setAssHair(this, BodyHair.getBodyHairFromValue(value));
	}
	public String incrementAssHair(int increment) {
		int value = getAssHair().getValue() + increment;
		if(value < 0) {
			value = 0;
		} else if(value > BodyHair.SEVEN_WILD.getValue()) {
			value = BodyHair.SEVEN_WILD.getValue();
		}
		return body.getAss().getAnus().setAssHair(this, BodyHair.getBodyHairFromValue(value));
	}
	// Orifice stats:
	// Wetness:
	public Wetness getAssWetness() {
		return body.getAss().getAnus().getOrificeAnus().getWetness(this);
	}
	public String setAssWetness(int wetness) {
		return body.getAss().getAnus().getOrificeAnus().setWetness(this, wetness);
	}
	public String incrementAssWetness(int increment) {
		return body.getAss().getAnus().getOrificeAnus().setWetness(this, getAssWetness().getValue() + increment);
	}
	// Capacity:
	public Capacity getAssCapacity() {
		return body.getAss().getAnus().getOrificeAnus().getCapacity();
	}
	public float getAssRawCapacityValue() {
		return body.getAss().getAnus().getOrificeAnus().getRawCapacityValue();
	}
	public float getAssStretchedCapacity() {
		return body.getAss().getAnus().getOrificeAnus().getStretchedCapacity();
	}
	public void setAssStretchedCapacity(float capacity){
		body.getAss().getAnus().getOrificeAnus().setStretchedCapacity(capacity);
	}
	public void incrementAssStretchedCapacity(float increment){
		body.getAss().getAnus().getOrificeAnus().setStretchedCapacity(getAssStretchedCapacity() + increment);
	}
	public String setAssCapacity(float capacity, boolean setStretchedValueToNewValue) {
		return body.getAss().getAnus().getOrificeAnus().setCapacity(this, capacity, setStretchedValueToNewValue);
	}
	public String incrementAssCapacity(float increment, boolean setStretchedValueToNewValue) {
		return setAssCapacity(getAssRawCapacityValue() + increment, setStretchedValueToNewValue);
	}
	// Elasticity:
	public OrificeElasticity getAssElasticity() {
		return body.getAss().getAnus().getOrificeAnus().getElasticity();
	}
	public String setAssElasticity(int elasticity) {
		return body.getAss().getAnus().getOrificeAnus().setElasticity(this, elasticity);
	}
	public String incrementAssElasticity(int increment) {
		return setAssElasticity(getAssElasticity().getValue() + increment);
	}
	// Plasticity:
	public OrificePlasticity getAssPlasticity() {
		return body.getAss().getAnus().getOrificeAnus().getPlasticity();
	}
	public String setAssPlasticity(int plasticity) {
		return body.getAss().getAnus().getOrificeAnus().setPlasticity(this, plasticity);
	}
	public String incrementAssPlasticity(int increment) {
		return setAssPlasticity(getAssPlasticity().getValue() + increment);
	}
	// Virginity:
	public boolean isAssVirgin() {
		return body.getAss().getAnus().getOrificeAnus().isVirgin();
	}
	public void setAssVirgin(boolean virgin) {
		body.getAss().getAnus().getOrificeAnus().setVirgin(virgin);
	}
	// Bleaching:
	public boolean isAssBleached() {
		return body.getAss().getAnus().isBleached();
	}
	public void setAssBleached(boolean bleached) {
		body.getAss().getAnus().setAssBleached(this, bleached);
	}
	// Modifiers:
	public boolean hasAssOrificeModifier(OrificeModifier modifier) {
		return body.getAss().getAnus().getOrificeAnus().hasOrificeModifier(modifier);
	}
	public String addAssOrificeModifier(OrificeModifier modifier) {
		return body.getAss().getAnus().getOrificeAnus().addOrificeModifier(this, modifier);
	}
	public String removeAssOrificeModifier(OrificeModifier modifier) {
		return body.getAss().getAnus().getOrificeAnus().removeOrificeModifier(this, modifier);
	}
	
	
	
	// ------------------------------ Body Material: ------------------------------ //
	
	// Type:
	public BodyMaterial getBodyMaterial() {
		return body.getBodyMaterial();
	}
	public String setBodyMaterial(BodyMaterial type) {
		body.setBodyMaterial(type);
		
		return ""; // TODO
	}
	
	
	
	// ------------------------------ Breasts: ------------------------------ //
	
	// Misc:
	public boolean hasBreasts() {
		return body.getBreast().hasBreasts();
	}
	public boolean isBreastFuckableNipplePenetration() {
		return body.getBreast().isFuckable();
	}
	public boolean isBreastFuckablePaizuri() {
		return body.getBreast().getRawSizeValue() >= CupSize.C.getMeasurement();
	}
	// Type:
	public BreastType getBreastType() {
		return body.getBreast().getType();
	}
	public String setBreastType(BreastType type) {
		return body.getBreast().setType(this, type);
	}
	// Shape:
	public BreastShape getBreastShape() {
		return body.getBreast().getShape();
	}
	public String setBreastShape(BreastShape shape) {
		return body.getBreast().setShape(this, shape);
	}
	// Names:
	public String getBreastName() {
		return body.getBreast().getName(this);
	}
	public String getBreastNameSingular() {
		return body.getBreast().getNameSingular(this);
	}
	public String getBreastName(boolean withDescriptor) {
		return body.getBreast().getName(this, withDescriptor);
	}
	public String getBreastDescriptor() {
		return body.getBreast().getDescriptor(this);
	}
	public String getBreastPronoun() {
		return body.getBreast().getType().getPronoun();
	}
	public String getBreastDescription() {
		return body.getBreastDescription(this);
	}
	// Breast rows:
	public int getBreastRows() {
		return body.getBreast().getRows();
	}
	public String setBreastRows(int rows) {
		return body.getBreast().setRows(this, rows);
	}
	public String incrementBreastRows(int increment) {
		return setBreastRows(getBreastRows() + increment);
	}
	// Lactation:
	public Lactation getBreastLactation() {
		return body.getBreast().getLactation();
	}
	public int getBreastRawLactationValue() {
		return body.getBreast().getRawLactationValue();
	}
	public String setBreastLactation(int lactation) {
		return body.getBreast().setLactation(this, lactation);
	}
	public String incrementBreastLactation(int increment) {
		return setBreastLactation(getBreastRawLactationValue() + increment);
	}
	// Breast size:
	public CupSize getBreastSize() {
		return body.getBreast().getSize();
	}
	public int getBreastRawSizeValue() {
		return body.getBreast().getRawSizeValue();
	}
	public String setBreastSize(int size) {
		return body.getBreast().setSize(this, size);
	}
	public String incrementBreastSize(int increment) {
		return setBreastSize(getBreastRawSizeValue() + increment);
	}
	
	// Nipples:
	
	// Type:
	/**NippleType is automatically changed when BreastType is set.*/
	public NippleType getNippleType() {
		return body.getBreast().getNipples().getType();
	}
	// Names:
	public String getNippleName() {
		return body.getBreast().getNipples().getName(this);
	}
	public String getNippleNameSingular() {
		return body.getBreast().getNipples().getNameSingular(this, false);
	}
	public String getNippleName(boolean withDescriptor) {
		return body.getBreast().getNipples().getName(this, withDescriptor);
	}
	public String getNippleDescriptor() {
		return body.getBreast().getNipples().getDescriptor(this);
	}
	// Count:
	public int getNippleCountPerBreast() {
		return body.getBreast().getNippleCountPerBreast();
	}
	public String setNippleCountPerBreast(int count) {
		return body.getBreast().setNippleCountPerBreast(this, count);
	}
	public String incrementNippleCountPerBreast(int increment) {
		return body.getBreast().setNippleCountPerBreast(this, getNippleCountPerBreast() + increment);
	}
	// Nipple Shape:
	public NippleShape getNippleShape() {
		return body.getBreast().getNipples().getNippleShape();
	}
	public String setNippleShape(NippleShape nippleShape) {
		return body.getBreast().getNipples().setNippleShape(this, nippleShape);
	}
	// Nipple size:
	public NippleSize getNippleSize() {
		return body.getBreast().getNipples().getNippleSize();
	}
	public String setNippleSize(int nippleSize) {
		return body.getBreast().getNipples().setNippleSize(this, nippleSize);
	}
	public String incrementNippleSize(int increment) {
		return body.getBreast().getNipples().setNippleSize(this, getNippleSize().getValue() + increment);
	}
	// Areolae size:
	public AreolaeSize getAreolaeSize() {
		return body.getBreast().getNipples().getAreolaeSize();
	}
	public String setAreolaeSize(int areolaeSize) {
		return body.getBreast().getNipples().setAreolaeSize(this, areolaeSize);
	}
	public String incrementAreolaeSize(int increment) {
		return body.getBreast().getNipples().setAreolaeSize(this, getAreolaeSize().getValue() + increment);
	}
	// Areolae Shape:
	public AreolaeShape getAreolaeShape() {
		return body.getBreast().getNipples().getAreolaeShape();
	}
	public String setAreolaeShape(AreolaeShape areolaeShape) {
		return body.getBreast().getNipples().setAreolaeShape(this, areolaeShape);
	}
	// Piercing:
	public boolean isPiercedNipple() {
		return body.getBreast().getNipples().isPierced();
	}
	public String setPiercedNipples(boolean pierced) {
		return body.getBreast().getNipples().setPierced(this, pierced);
	}
	// Orifice stats:
	// Wetness:
	public Wetness getNippleWetness() {
		return body.getBreast().getNipples().getOrificeNipples().getWetness(this);
	}
	// Capacity:
	public Capacity getNippleCapacity() {
		return body.getBreast().getNipples().getOrificeNipples().getCapacity();
	}
	public float getNippleRawCapacityValue() {
		return body.getBreast().getNipples().getOrificeNipples().getRawCapacityValue();
	}
	public float getNippleStretchedCapacity() {
		return body.getBreast().getNipples().getOrificeNipples().getStretchedCapacity();
	}
	public void setNippleStretchedCapacity(float capacity){
		body.getBreast().getNipples().getOrificeNipples().setStretchedCapacity(capacity);
	}
	public void incrementNippleStretchedCapacity(float increment){
		body.getBreast().getNipples().getOrificeNipples().setStretchedCapacity(getNippleStretchedCapacity() + increment);
	}
	public String setNippleCapacity(float capacity, boolean setStretchedValueToNewValue) {
		return body.getBreast().getNipples().getOrificeNipples().setCapacity(this, capacity, setStretchedValueToNewValue);
	}
	public String incrementNippleCapacity(float increment, boolean setStretchedValueToNewValue) {
		return setNippleCapacity(getNippleRawCapacityValue() + increment, setStretchedValueToNewValue);
	}
	// Elasticity:
	public OrificeElasticity getNippleElasticity() {
		return body.getBreast().getNipples().getOrificeNipples().getElasticity();
	}
	public String setNippleElasticity(int elasticity) {
		return body.getBreast().getNipples().getOrificeNipples().setElasticity(this, elasticity);
	}
	public String incrementNippleElasticity(int increment) {
		return setNippleElasticity(getNippleElasticity().getValue() + increment);
	}
	// Plasticity:
	public OrificePlasticity getNipplePlasticity() {
		return body.getBreast().getNipples().getOrificeNipples().getPlasticity();
	}
	public String setNipplePlasticity(int plasticity) {
		return body.getBreast().getNipples().getOrificeNipples().setPlasticity(this, plasticity);
	}
	public String incrementNipplePlasticity(int increment) {
		return setNipplePlasticity(getNipplePlasticity().getValue() + increment);
	}
	// Virginity:
	public boolean isNippleVirgin() {
		return body.getBreast().getNipples().getOrificeNipples().isVirgin();
	}
	public void setNippleVirgin(boolean virgin) {
		body.getBreast().getNipples().getOrificeNipples().setVirgin(virgin);
	}
	// Modifiers:
	public boolean hasNippleOrificeModifier(OrificeModifier modifier) {
		return body.getBreast().getNipples().getOrificeNipples().hasOrificeModifier(modifier);
	}
	public String addNippleOrificeModifier(OrificeModifier modifier) {
		return body.getBreast().getNipples().getOrificeNipples().addOrificeModifier(this, modifier);
	}
	public String removeNippleOrificeModifier(OrificeModifier modifier) {
		return body.getBreast().getNipples().getOrificeNipples().removeOrificeModifier(this, modifier);
	}
	
	// Milk:
	
	public String getMilkName() {
		return body.getBreast().getMilk().getName(this);
	}
	// Flavour:
	public FluidFlavour getMilkFlavour() {
		return body.getBreast().getMilk().getFlavour();
	}
	public String setMilkFlavour(FluidFlavour flavour) {
		return body.getBreast().getMilk().setFlavour(this, flavour);
	}
	// Modifiers:
	public boolean hasMilkModifier(FluidModifier fluidModifier) {
		return body.getBreast().getMilk().hasFluidModifier(fluidModifier);
	}
	public String addMilkModifier(FluidModifier fluidModifier) {
		return body.getBreast().getMilk().addFluidModifier(this, fluidModifier);
	}
	public String removeMilkModifier(FluidModifier fluidModifier) {
		return body.getBreast().getMilk().removeFluidModifier(this, fluidModifier);
	}
	// Transformations:
	public List<ItemEffect> getMilkTransformativeEffects() {
		return body.getBreast().getMilk().getTransformativeEffects();
	}
	
	
	
	// ------------------------------ Ears: ------------------------------ //
	
	// Type:
	public EarType getEarType() {
		return body.getEar().getType();
	}
	public String setEarType(EarType type) {
		return body.getEar().setType(this, type);
	}
	// Names:
	public String getEarName() {
		return body.getEar().getName(this);
	}
	public String getEarNameSingular() {
		return body.getEar().getNameSingular(this);
	}
	public String getEarName(boolean withDescriptor) {
		return body.getEar().getName(this, withDescriptor);
	}
	public String getEarDescriptor() {
		return body.getEar().getDescriptor(this);
	}
	public String getEarDeterminer() {
		return body.getEar().getDeterminer(this);
	}
	public String getEarPronoun() {
		return body.getEar().getType().getPronoun();
	}
	//Piercing:
	public boolean isPiercedEar() {
		return body.getEar().isPierced();
	}
	public String setPiercedEar(boolean pierced) {
		return body.getEar().setPierced(this, pierced);
	}
	
	

	// ------------------------------ Eyes: ------------------------------ //
	
	// Type:
	public EyeType getEyeType() {
		return body.getEye().getType();
	}
	public String setEyeType(EyeType type) {
		return body.getEye().setType(this, type);
	}
	// Names:
	public String getEyeName() {
		return body.getEye().getName(this);
	}
	public String getEyeNameSingular() {
		return body.getEye().getNameSingular(this);
	}
	public String getEyeName(boolean withDescriptor) {
		return body.getEye().getName(this, withDescriptor);
	}
	public String getEyeDescriptor() {
		return body.getEye().getDescriptor(this);
	}
	public String getEyeDeterminer() {
		return body.getEye().getDeterminer(this);
	}
	public String getEyePronoun() {
		return body.getEye().getType().getPronoun();
	}
	// Coverings:
	public Covering getEyeIrisCovering() {
		return body.getCoverings().get(body.getEye().getType().getBodyCoveringType());
	}
	public Covering getEyePupilCovering() {
		return body.getCoverings().get(BodyCoveringType.EYE_PUPILS);
	}
	public String setEyeCovering(Covering covering) {
		return body.getEye().setEyeCovering(this, covering);
	}
	// Eye makeup:
	public Covering getEyeLiner() {
		return getCovering(BodyCoveringType.MAKEUP_EYE_LINER);
	}
	public String setEyeLiner(Covering eyeLiner) {
		body.getCoverings().put(eyeLiner.getType(), eyeLiner);
		if(isPlayer()) {
			if(eyeLiner.getPattern()==CoveringPattern.NONE) {
				return "<p>"
							+ "You are [style.boldShrink(not wearing)] any eye liner."
						+ "</p>";
			} else {
				return "<p>"
							+ "You are [style.boldGrow(now wearing)] "+eyeLiner.getColourDescriptor(true, false)+" eye liner."
						+ "</p>";
			}
			
		} else {
			if(eyeLiner.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any eye liner."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+eyeLiner.getColourDescriptor(true, false)+" eye liner."
						+ "</p>");
			}
		}
	}
	public Covering getEyeShadow() {
		return getCovering(BodyCoveringType.MAKEUP_EYE_SHADOW);
	}
	public String setEyeShadow(Covering eyeShadow) {
		body.getCoverings().put(eyeShadow.getType(), eyeShadow);
		if(isPlayer()) {
			if(eyeShadow.getPattern()==CoveringPattern.NONE) {
				return "<p>"
							+ "You are [style.boldShrink(not wearing)] any eye shadow."
						+ "</p>";
			} else {
				return "<p>"
							+ "You are [style.boldGrow(now wearing)] "+eyeShadow.getColourDescriptor(true, false)+" eye shadow."
						+ "</p>";
			}
			
		} else {
			if(eyeShadow.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any eye shadow."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+eyeShadow.getColourDescriptor(true, false)+" eye shadow."
						+ "</p>");
			}
		}
	}
	// Pairs Count:
	public int getEyePairs() {
		return body.getEye().getEyePairs();
	}
	public String setEyePairs(int eyePairs) {
		return body.getEye().setEyePairs(this, eyePairs);
	}
	public String incrementEyePairs(int increment) {
		return body.getEye().setEyePairs(this, getEyePairs() + increment);
	}
	// Shapes:
	public EyeShape getIrisShape() {
		return body.getEye().getIrisShape();
	}
	public String setIrisShape(EyeShape eyeShape) {
		return body.getEye().setIrisShape(this, eyeShape);
	}
	public EyeShape getPupilShape() {
		return body.getEye().getPupilShape();
	}
	public String setPupilShape(EyeShape eyeShape) {
		return body.getEye().setPupilShape(this, eyeShape);
	}
	
	

	// ------------------------------ Face: ------------------------------ //
	
	// Type:
	public FaceType getFaceType() {
		return body.getFace().getType();
	}
	public String setFaceType(FaceType type) {
		return body.getFace().setType(this, type);
	}
	// Names:
	public String getFaceName() {
		return body.getFace().getName(this);
	}
	public String getFaceNameSingular() {
		return body.getFace().getNameSingular(this);
	}
	public String getFaceName(boolean withDescriptor) {
		return body.getFace().getName(this, withDescriptor);
	}
	public String getFaceDescriptor() {
		return body.getFace().getDescriptor(this);
	}
	public String getFaceDeterminer() {
		return body.getFace().getDeterminer(this);
	}
	public String getFacePronoun() {
		return body.getFace().getType().getPronoun();
	}
	// Nose Names:
	public String getNoseNameSingular() {
		return body.getFace().getNoseNameSingular(this);
	}
	public String getNoseNamePlural() {
		return body.getFace().getNoseNamePlural(this);
	}
	public String getNoseDescriptor() {
		return body.getFace().getNoseDescriptor(this);
	}
	// Lip Names:
	public String getLipsNameSingular() {
		return body.getFace().getMouth().getLipsNameSingular(this);
	}
	public String getLipsNamePlural() {
		return body.getFace().getMouth().getLipsNamePlural(this);
	}
	public String getLipsDescriptor() {
		return body.getFace().getMouth().getLipsDescriptor(this);
	}
	// Nose Piercing:
	public boolean isPiercedNose() {
		return body.getFace().isPiercedNose();
	}
	public String setPiercedNose(boolean piercedNose) {
		return body.getFace().setPiercedNose(this, piercedNose);
	}
	// Lip Size:
	public LipSize getLipSize() {
		return body.getFace().getMouth().getLipSize();
	}
	public int getLipSizeValue() {
		return body.getFace().getMouth().getLipSizeValue();
	}
	public String setLipSize(int lipSize) {
		return body.getFace().getMouth().setLipSize(this, lipSize);
	}
	public String incrementLipSize(int lipSize) {
		return body.getFace().getMouth().setLipSize(this, getLipSize().getValue() + lipSize);
	}
	// Piercing:
	public boolean isPiercedLip() {
		return body.getFace().getMouth().isPiercedLip();
	}
	public String setPiercedLip(boolean pierced) {
		return body.getFace().getMouth().setPiercedLip(this, pierced);
	}
	// Makeup:
	public Covering getLipstick() {
		return getCovering(BodyCoveringType.MAKEUP_LIPSTICK);
	}
	public String setLipstick(Covering lipstick) {
		body.getCoverings().put(lipstick.getType(), lipstick);
		if(isPlayer()) {
			if(lipstick.getPattern()==CoveringPattern.NONE) {
				return "<p>"
							+ "You are [style.boldShrink(not wearing)] any lipstick."
						+ "</p>";
			} else {
				return "<p>"
							+ "You are [style.boldGrow(now wearing)] "+lipstick.getColourDescriptor(true, false)+" lipstick."
						+ "</p>";
			}
			
		} else {
			if(lipstick.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any lipstick."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+lipstick.getColourDescriptor(true, false)+" lipstick."
						+ "</p>");
			}
		}
	}
	public Covering getBlusher() {
		return getCovering(BodyCoveringType.MAKEUP_BLUSHER);
	}
	public String setBlusher(Covering blusher) {
		body.getCoverings().put(blusher.getType(), blusher);
		if(isPlayer()) {
			if(blusher.getPattern()==CoveringPattern.NONE) {
				return "<p>"
							+ "You are [style.boldShrink(not wearing)] any blusher."
						+ "</p>";
			} else {
				return "<p>"
							+ "You are [style.boldGrow(now wearing)] "+blusher.getColourDescriptor(true, false)+" blusher."
						+ "</p>";
			}
			
		} else {
			if(blusher.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any blusher."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+blusher.getColourDescriptor(true, false)+" blusher."
						+ "</p>");
			}
		}
	}
	// Facial hair:
	public BodyHair getFacialHair() {
		if(this.getFemininityValue()>=Femininity.ANDROGYNOUS.getMinimumFemininity()) {
			setFacialHair(BodyHair.ZERO_NONE);
		}
		return body.getFace().getFacialHair();
	}
	public Covering getFacialHairType() {
		return body.getFace().getFacialHairType(this);
	}
	public String setFacialHair(BodyHair facialHair) {
		return body.getFace().setFacialHair(this, facialHair);
	}
	public String setFacialHair(int value) {
		return body.getFace().setFacialHair(this, BodyHair.getBodyHairFromValue(value));
	}
	public String incrementFacialHair(int increment) {
		int value = getFacialHair().getValue() + increment;
		if(value < 0) {
			value = 0;
		} else if(value > BodyHair.SEVEN_WILD.getValue()) {
			value = BodyHair.SEVEN_WILD.getValue();
		}
		return body.getFace().setFacialHair(this, BodyHair.getBodyHairFromValue(value));
	}
	// Orifice stats:
	// Wetness:
	public Wetness getFaceWetness() {
		return body.getFace().getMouth().getOrificeMouth().getWetness(this);
	}
	public String setFaceWetness(int wetness) {
		return body.getFace().getMouth().getOrificeMouth().setWetness(this, wetness);
	}
	public String incrementFaceWetness(int increment) {
		return body.getFace().getMouth().getOrificeMouth().setWetness(this, getFaceWetness().getValue() + increment);
	}
	// Capacity:
	public Capacity getFaceCapacity() {
		return body.getFace().getMouth().getOrificeMouth().getCapacity();
	}
	public float getFaceRawCapacityValue() {
		return body.getFace().getMouth().getOrificeMouth().getRawCapacityValue();
	}
	public float getFaceStretchedCapacity() {
		return body.getFace().getMouth().getOrificeMouth().getStretchedCapacity();
	}
	public void setFaceStretchedCapacity(float capacity){
		body.getFace().getMouth().getOrificeMouth().setStretchedCapacity(capacity);
	}
	public void incrementFaceStretchedCapacity(float increment){
		body.getFace().getMouth().getOrificeMouth().setStretchedCapacity(getFaceStretchedCapacity() + increment);
	}
	public String setFaceCapacity(float capacity, boolean setStretchedValueToNewValue) {
		return body.getFace().getMouth().getOrificeMouth().setCapacity(this, capacity, setStretchedValueToNewValue);
	}
	public String incrementFaceCapacity(float increment, boolean setStretchedValueToNewValue) {
		return setFaceCapacity(getFaceRawCapacityValue() + increment, setStretchedValueToNewValue);
	}
	// Elasticity:
	public OrificeElasticity getFaceElasticity() {
		return body.getFace().getMouth().getOrificeMouth().getElasticity();
	}
	public String setFaceElasticity(int elasticity) {
		return body.getFace().getMouth().getOrificeMouth().setElasticity(this, elasticity);
	}
	public String incrementFaceElasticity(int increment) {
		return setFaceElasticity(getFaceElasticity().getValue() + increment);
	}
	// Plasticity:
	public OrificePlasticity getFacePlasticity() {
		return body.getFace().getMouth().getOrificeMouth().getPlasticity();
	}
	public String setFacePlasticity(int plasticity) {
		return body.getFace().getMouth().getOrificeMouth().setPlasticity(this, plasticity);
	}
	public String incrementFacePlasticity(int increment) {
		return setFacePlasticity(getFacePlasticity().getValue() + increment);
	}
	// Virginity:
	public boolean isFaceVirgin() {
		return body.getFace().getMouth().getOrificeMouth().isVirgin();
	}
	public void setFaceVirgin(boolean virgin) {
		body.getFace().getMouth().getOrificeMouth().setVirgin(virgin);
	}
	// Modifiers:
	public boolean hasFaceOrificeModifier(OrificeModifier modifier) {
		return body.getFace().getMouth().getOrificeMouth().hasOrificeModifier(modifier);
	}
	public String addFaceOrificeModifier(OrificeModifier modifier) {
		return body.getFace().getMouth().getOrificeMouth().addOrificeModifier(this, modifier);
	}
	public String removeFaceOrificeModifier(OrificeModifier modifier) {
		return body.getFace().getMouth().getOrificeMouth().removeOrificeModifier(this, modifier);
	}
	
	
	
	// ------------------------------ Genital arrangement: ------------------------------ //
	
	// Type:
	public GenitalArrangement getGenitalArrangement() {
		return body.getGenitalArrangement();
	}
	public String setGenitalArrangement(GenitalArrangement type) {
		body.setGenitalArrangement(type);
		
		return ""; // TODO
	}
	
	
	
	// ------------------------------ Hair: ------------------------------ //
	
	// Type:
	public HairType getHairType() {
		return body.getHair().getType();
	}
	public String setHairType(HairType type) {
		return body.getHair().setType(this, type);
	}
	// Names:
	public String getHairName() {
		return body.getHair().getName(this);
	}
	public String getHairName(boolean withDescriptor) {
		return body.getHair().getName(this, withDescriptor);
	}
	public String getHairDescriptor() {
		return body.getHair().getDescriptor(this);
	}
	public String getHairDeterminer() {
		return body.getHair().getDeterminer(this);
	}
	public String getHairPronoun() {
		return body.getHair().getType().getPronoun();
	}
	// Length:
	public HairLength getHairLength() {
		return body.getHair().getLength();
	}
	public int getHairRawLengthValue() {
		return body.getHair().getRawLengthValue();
	}
	public String setHairLength(int length) {
		return body.getHair().setLength(this, length);
	}
	public String incrementHairLength(int increment) {
		return setHairLength(getHairRawLengthValue() + increment);
	}
	// Style:
	public HairStyle getHairStyle() {
		return body.getHair().getStyle();
	}
	public String setHairStyle(HairStyle hairStyle) {
		return body.getHair().setStyle(this, hairStyle);
	}
	// Covering:
	public Covering getHairCovering() {
		return body.getCoverings().get(body.getHair().getType().getBodyCoveringType());
	}
	public String setHairCovering(Covering covering, boolean updateBodyHair) {
		if(!getHairCovering().equals(covering)) {
			body.getCoverings().put(covering.getType(), covering);
			
			body.updateCoverings(false, false, updateBodyHair, false);
			
			if (isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as your [pc.hair] "+(getHairType().isDefaultPlural()?"change":"changes")+" colour.</br>"
							+ "You now have [style.boldTfGeneric([pc.hairFullDescription])]."
						+ "</p>";
			} else {
				return UtilText.parse(this,
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.her] [npc.hair] "+(getHairType().isDefaultPlural()?"change":"changes")+" colour.</br>"
							+ "[npc.She] now has [style.boldTfGeneric([npc.hairFullDescription])]."
						+ "</p>");
			}
		}
		
		return "<p>" + "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Nothing seems to happen.</span>" + "</p>";
	}
	
	
	
	// ------------------------------ Horns: ------------------------------ //
	
	// Type:
	public HornType getHornType() {
		return body.getHorn().getType();
	}
	public String setHornType(HornType hornType) {
		return body.getHorn().setType(this, hornType);
	}
	// Misc.:
	public boolean hasHorns() {
		return body.getHorn().getType() != HornType.NONE;
	}
	// Names:
	public String getHornName() {
		return body.getHorn().getName(this);
	}
	public String getHornNameSingular() {
		return body.getHorn().getNameSingular(this);
	}
	public String getHornName(boolean withDescriptor) {
		return body.getHorn().getName(this, withDescriptor);
	}
	public String getHornDescriptor() {
		return body.getHorn().getDescriptor(this);
	}
	public String getHornDeterminer() {
		return body.getHorn().getDeterminer(this);
	}
	public String getHornPronoun() {
		return body.getHorn().getType().getPronoun();
	}
	// Length:
	public int getHornLength() {
		return body.getHorn().getHornLengthValue();
	}
	public String setHornLength(int length) {
		return body.getHorn().setHornLength(this, length);
	}
	public String incrementHornLength(int increment) {
		return body.getHorn().setHornLength(this, getHornLength() + increment);
	}
	// Rows:
	public int getHornRows() {
		return body.getHorn().getHornRows();
	}
	public String setHornRows(int rows) {
		return body.getHorn().setHornRows(this, rows);
	}
	public String incrementHornRows(int increment) {
		return body.getHorn().setHornRows(this, getHornRows() + increment);
	}
	
	
	
	// ------------------------------ Legs: ------------------------------ //
	
	// Type:
	public LegType getLegType() {
		return body.getLeg().getType();
	}
	public String setLegType(LegType type) {
		return body.getLeg().setType(this, type);
	}
	// Name:
	public String getLegName() {
		return body.getLeg().getName(this);
	}
	public String getLegName(boolean withDescriptor) {
		return body.getLeg().getName(this, withDescriptor);
	}
	public String getLegDescriptor() {
		return body.getLeg().getDescriptor(this);
	}
	public String getLegDeterminer() {
		return body.getLeg().getDeterminer(this);
	}
	public String getLegPronoun() {
		return body.getLeg().getType().getPronoun();
	}
	// Nail polish:
	public Covering getFootNailPolish() {
		return getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET);
	}
	public String setFootNailPolish(Covering nailPolish) {
		body.getCoverings().put(nailPolish.getType(), nailPolish);
		if(isPlayer()) {
			if(nailPolish.getPattern()==CoveringPattern.NONE) {
				return "<p>"
							+ "You are [style.boldShrink(not wearing)] any toenail polish."
						+ "</p>";
			} else {
				return "<p>"
							+ "You are [style.boldGrow(now wearing)] "+nailPolish.getColourDescriptor(true, false)+" toenail polish."
						+ "</p>";
			}
			
		} else {
			if(nailPolish.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any toenail polish."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+nailPolish.getColourDescriptor(true, false)+" toenail polish."
						+ "</p>");
			}
		}
	}
	
	
	
	// ------------------------------ Mound: ------------------------------ //
	
	public String getMoundDescription() {
		return body.getMoundDescription(this);
	}
	
	
	
	// ------------------------------ Tongue: ------------------------------ //
	
	// Type:
	public TongueType getTongueType() {
		return body.getFace().getTongue().getType();
	}
	// Names:
	public String getTongueName() {
		return body.getFace().getTongue().getName(this);
	}
	public String getTongueName(boolean withDescriptor) {
		return body.getFace().getTongue().getName(this, withDescriptor);
	}
	public String getTongueDescriptor() {
		return body.getFace().getTongue().getDescriptor(this);
	}
	public String getTongueDeterminer() {
		return body.getFace().getTongue().getDeterminer(this);
	}
	public String getTonguePronoun() {
		return body.getFace().getTongue().getType().getPronoun();
	}
	// Length:
	public TongueLength getTongueLength() {
		return body.getFace().getTongue().getTongueLength();
	}
	public int getTongueLengthValue() {
		return body.getFace().getTongue().getTongueLengthValue();
	}
	public String setTongueLength(int tongueLength) {
		return body.getFace().getTongue().setTongueLength(this, tongueLength);
	}
	public String incrementTongueLength(int increment) {
		return body.getFace().getTongue().setTongueLength(this, getTongueLengthValue() + increment);
	}
	// Pierced:
	public boolean isPiercedTongue() {
		return body.getFace().getTongue().isPierced();
	}
	public String setPiercedTongue(boolean pierced) {
		return body.getFace().getTongue().setPierced(this, pierced);
	}
	// Tongue Modifiers:
	public boolean hasTongueModifier(TongueModifier modifier) {
		return body.getFace().getTongue().hasTongueModifier(modifier);
	}
	public String addTongueModifier(TongueModifier modifier) {
		return body.getFace().getTongue().addTongueModifier(this, modifier);
	}
	public String removeTongueModifier(TongueModifier modifier) {
		return body.getFace().getTongue().removeTongueModifier(this, modifier);
	}
	
	
	
	// ------------------------------ Penis: ------------------------------ //
	
	// Type:
	public PenisType getPenisType() {
		return body.getPenis().getType();
	}
	public String setPenisType(PenisType type) {
		return body.getPenis().setType(this, type);
	}
	// Misc.:
	public boolean hasPenis() {
		return body.getPenis().getType() != PenisType.NONE;
	}
	public boolean isPenisVirgin() {
		return body.getPenis().isVirgin();
	}
	public void setPenisVirgin(boolean virgin) {
		body.getPenis().setVirgin(virgin);
	}
	// Names:
	public String getPenisName() {
		return body.getPenis().getName(this);
	}
	public String getPenisName(boolean withDescriptor) {
		return body.getPenis().getName(this, withDescriptor);
	}
	public String getPenisDescriptor() {
		return body.getPenis().getDescriptor(this);
	}
	public String getPenisDeterminer() {
		return body.getPenis().getDeterminer(this);
	}
	public String getPenisPronoun() {
		return body.getPenis().getType().getPronoun();
	}
	public String getPenisDescription() {
		return body.getPenisDescription(this);
	}
	// Penis size:
	public PenisSize getPenisSize() {
		return body.getPenis().getSize();
	}
	public int getPenisRawSizeValue() {
		return body.getPenis().getRawSizeValue();
	}
	public String setPenisSize(int size) {
		return body.getPenis().setPenisSize(this, size);
	}
	public String incrementPenisSize(int increment) {
		return setPenisSize(getPenisRawSizeValue() + increment);
	}
	// Pierced:
	public boolean isPiercedPenis() {
		return body.getPenis().isPierced();
	}
	public String setPiercedPenis(boolean pierced) {
		return body.getPenis().setPierced(this, pierced);
	}
	// Modifiers:
	public List<PenisModifier> getPenisModifiers() {
		List<PenisModifier> list = new ArrayList<>();
		list.addAll(body.getPenis().getPenisModifiers());
		return list;
	}
	public boolean hasPenisModifier(PenisModifier modifier) {
		return body.getPenis().hasPenisModifier(modifier);
	}
	public String addPenisModifier(PenisModifier modifier) {
		return body.getPenis().addPenisModifier(this, modifier);
	}
	public String removePenisModifier(PenisModifier modifier) {
		return body.getPenis().removePenisModifier(this, modifier);
	}
	
	// Urethra:
	
	// Capacity:
	public Capacity getPenisCapacity() {
		return body.getPenis().getOrificeUrethra().getCapacity();
	}
	public String setPenisCapacity(float capacity, boolean setStretchedValueToNewValue) {
		return body.getPenis().getOrificeUrethra().setCapacity(this, capacity, setStretchedValueToNewValue);
	}
	public String incrementPenisCapacity(float increment, boolean setStretchedValueToNewValue) {
		return setPenisCapacity(getPenisRawCapacityValue() + increment, setStretchedValueToNewValue);
	}
	public float getPenisRawCapacityValue() {
		return body.getPenis().getOrificeUrethra().getRawCapacityValue();
	}
	public float getPenisStretchedCapacity() {
		return body.getPenis().getOrificeUrethra().getStretchedCapacity();
	}
	public void setPenisStretchedCapacity(float capacity){
		body.getPenis().getOrificeUrethra().setStretchedCapacity(capacity);
	}
	public void incrementPenisStretchedCapacity(float increment){
		body.getPenis().getOrificeUrethra().setStretchedCapacity(getPenisStretchedCapacity() + increment);
	}
	// Elasticity:
	public OrificeElasticity getUrethraElasticity() {
		return body.getPenis().getOrificeUrethra().getElasticity();
	}
	public String setUrethraElasticity(int elasticity) {
		return body.getPenis().getOrificeUrethra().setElasticity(this, elasticity);
	}
	public String incrementUrethraElasticity(int increment) {
		return setUrethraElasticity(getUrethraElasticity().getValue() + increment);
	}
	// Plasticity:
	public OrificePlasticity getUrethraPlasticity() {
		return body.getPenis().getOrificeUrethra().getPlasticity();
	}
	public String setUrethraPlasticity(int plasticity) {
		return body.getPenis().getOrificeUrethra().setPlasticity(this, plasticity);
	}
	public String incrementUrethraPlasticity(int increment) {
		return setUrethraPlasticity(getUrethraPlasticity().getValue() + increment);
	}
	// Virgin:
	public boolean isUrethraVirgin() {
		return body.getPenis().getOrificeUrethra().isVirgin();
	}
	public void setUrethraVirgin(boolean virgin) {
		body.getPenis().getOrificeUrethra().setVirgin(virgin);
	}
	// Modifiers:
	public boolean hasUrethraOrificeModifier(OrificeModifier modifier) {
		return body.getPenis().getOrificeUrethra().hasOrificeModifier(modifier);
	}
	public String addUrethraOrificeModifier(OrificeModifier modifier) {
		return body.getPenis().getOrificeUrethra().addOrificeModifier(this, modifier);
	}
	public String removeUrethraOrificeModifier(OrificeModifier modifier) {
		return body.getPenis().getOrificeUrethra().removeOrificeModifier(this, modifier);
	}
	
	// ------------------------------ Second Penis: ------------------------------ //
	
	// Type:
	public PenisType getSecondPenisType() {
		return body.getSecondPenis().getType();
	}
	public String setSecondPenisType(PenisType type) {
		return body.getSecondPenis().setType(this, type);
	}
	// Misc.:
	public boolean hasSecondPenis() {
		return body.getSecondPenis().getType() != PenisType.NONE;
	}
	// Names:
	public String getSecondPenisName() {
		return body.getSecondPenis().getName(this);
	}
	public String getSecondPenisName(boolean withDescriptor) {
		return body.getSecondPenis().getName(this, withDescriptor);
	}
	public String getSecondPenisDescriptor() {
		return body.getSecondPenis().getDescriptor(this);
	}
	public String getSecondPenisDeterminer() {
		return body.getSecondPenis().getDeterminer(this);
	}
	public String getSecondPenisPronoun() {
		return body.getSecondPenis().getType().getPronoun();
	}
	// Penis size:
	public PenisSize getSecondPenisSize() {
		return body.getSecondPenis().getSize();
	}
	public int getSecondPenisRawSizeValue() {
		return body.getSecondPenis().getRawSizeValue();
	}
	public String setSecondPenisSize(int size) {
		return body.getSecondPenis().setPenisSize(this, size);
	}
	public String incrementSecondPenisSize(int increment) {
		return setSecondPenisSize(getSecondPenisRawSizeValue() + increment);
	}
	// Pierced:
	public boolean isPiercedSecondPenis() {
		return body.getSecondPenis().isPierced();
	}
	public String setPiercedSecondPenis(boolean pierced) {
		return body.getSecondPenis().setPierced(this, pierced);
	}
	// Modifiers:
	public boolean hasSecondPenisModifier(PenisModifier modifier) {
		return body.getSecondPenis().hasPenisModifier(modifier);
	}
	public String addSecondPenisModifier(PenisModifier modifier) {
		return body.getSecondPenis().addPenisModifier(this, modifier);
	}
	public String removeSecondPenisModifier(PenisModifier modifier) {
		return body.getSecondPenis().removePenisModifier(this, modifier);
	}
	// Urethra:
	// Capacity:
	public Capacity getSecondPenisCapacity() {
		return body.getSecondPenis().getOrificeUrethra().getCapacity();
	}
	public String setSecondPenisCapacity(float capacity, boolean setStretchedValueToNewValue) {
		return body.getSecondPenis().getOrificeUrethra().setCapacity(this, capacity, setStretchedValueToNewValue);
	}
	public String incrementSecondPenisCapacity(float increment, boolean setStretchedValueToNewValue) {
		return setSecondPenisCapacity(getSecondPenisRawCapacityValue() + increment, setStretchedValueToNewValue);
	}
	public float getSecondPenisRawCapacityValue() {
		return body.getSecondPenis().getOrificeUrethra().getRawCapacityValue();
	}
	public float getSecondPenisStretchedCapacity() {
		return body.getSecondPenis().getOrificeUrethra().getStretchedCapacity();
	}
	public void setSecondPenisStretchedCapacity(float capacity){
		body.getSecondPenis().getOrificeUrethra().setStretchedCapacity(capacity);
	}
	public void incrementSecondPenisStretchedCapacity(float increment){
		body.getSecondPenis().getOrificeUrethra().setStretchedCapacity(getSecondPenisStretchedCapacity() + increment);
	}
	// Elasticity:
	public OrificeElasticity getSecondUrethraElasticity() {
		return body.getSecondPenis().getOrificeUrethra().getElasticity();
	}
	public String setSecondUrethraElasticity(int elasticity) {
		return body.getSecondPenis().getOrificeUrethra().setElasticity(this, elasticity);
	}
	public String incrementSecondUrethraElasticity(int increment) {
		return setUrethraElasticity(getUrethraElasticity().getValue() + increment);
	}
	// Plasticity:
	public OrificePlasticity getSecondUrethraPlasticity() {
		return body.getSecondPenis().getOrificeUrethra().getPlasticity();
	}
	public String setSecondUrethraPlasticity(int plasticity) {
		return body.getSecondPenis().getOrificeUrethra().setPlasticity(this, plasticity);
	}
	public String incrementSecondUrethraPlasticity(int increment) {
		return setUrethraPlasticity(getUrethraPlasticity().getValue() + increment);
	}
	// Virgin:
	public boolean isSecondUrethraVirgin() {
		return body.getSecondPenis().getOrificeUrethra().isVirgin();
	}
	public void setSecondUrethraVirgin(boolean virgin) {
		body.getSecondPenis().getOrificeUrethra().setVirgin(virgin);
	}
	// Modifiers:
	public boolean hasSecondUrethraOrificeModifier(OrificeModifier modifier) {
		return body.getSecondPenis().getOrificeUrethra().hasOrificeModifier(modifier);
	}
	public String addSecondUrethraOrificeModifier(OrificeModifier modifier) {
		return body.getSecondPenis().getOrificeUrethra().addOrificeModifier(this, modifier);
	}
	public String removeSecondUrethraOrificeModifier(OrificeModifier modifier) {
		return body.getSecondPenis().getOrificeUrethra().removeOrificeModifier(this, modifier);
	}

	// ------------------------------ Testicles: ------------------------------ //
	
	// Count:
	public int getPenisNumberOfTesticles() {
		return body.getPenis().getTesticle().getTesticleCount();
	}
	public String setPenisNumberOfTesticles(int testicleCount) {
		return body.getPenis().getTesticle().setTesticleCount(this, testicleCount);
	}
	// Cum Production:
	public CumProduction getPenisCumProduction() {
		return body.getPenis().getTesticle().getCumProduction();
	}
	public int getPenisRawCumProductionValue() {
		return body.getPenis().getTesticle().getRawCumProductionValue();
	}
	public String setCumProduction(int cumProduction) {
		return body.getPenis().getTesticle().setCumProduction(this, cumProduction);
	}
	public String incrementPenisCumProduction(int increment) {
		return setCumProduction(getPenisRawCumProductionValue() + increment);
	}
	// Testicle size:
	public TesticleSize getTesticleSize() {
		return body.getPenis().getTesticle().getTesticleSize();
	}
	public String setTesticleSize(int size) {
		return body.getPenis().getTesticle().setTesticleSize(this, size);
	}
	public String incrementTesticleSize(int increment) {
		return setTesticleSize(getTesticleSize().getValue() + increment);
	}
	// Testicle count:
	public int getTesticleCount() {
		return body.getPenis().getTesticle().getTesticleCount();
	}
	public String setTesticleCount(int count) {
		return body.getPenis().getTesticle().setTesticleCount(this, count);
	}
	public String incrementTesticleCount(int increment) {
		return body.getPenis().getTesticle().setTesticleCount(this, getTesticleCount() + increment);
	}
	// Internal:
	public boolean isInternalTesticles() {
		return body.getPenis().getTesticle().isInternal();
	}
	public String setInternalTesticles(boolean internal) {
		return body.getPenis().getTesticle().setInternal(this, internal);
	}
	
	// Cum:
	public FluidCum getCum() {
		return body.getPenis().getTesticle().getCum();
	}
	public String getCumName() {
		return body.getPenis().getTesticle().getCum().getName(this);
	}
	// Flavour:
	public FluidFlavour getCumFlavour() {
		return body.getPenis().getTesticle().getCum().getFlavour();
	}
	public String setCumFlavour(FluidFlavour flavour) {
		return body.getPenis().getTesticle().getCum().setFlavour(this, flavour);
	}
	// Modifiers:
	public List<FluidModifier> getCumModifiers() {
		List<FluidModifier> list = new ArrayList<>();
		list.addAll(body.getPenis().getTesticle().getCum().getFluidModifiers());
		return list;
	}
	public boolean hasCumModifier(FluidModifier fluidModifier) {
		return body.getPenis().getTesticle().getCum().hasFluidModifier(fluidModifier);
	}
	public String addCumModifier(FluidModifier fluidModifier) {
		return body.getPenis().getTesticle().getCum().addFluidModifier(this, fluidModifier);
	}
	public String removeCumModifier(FluidModifier fluidModifier) {
		return body.getPenis().getTesticle().getCum().removeFluidModifier(this, fluidModifier);
	}
	// Transformations:
	public List<ItemEffect> getCumTransformativeEffects() {
		return body.getPenis().getTesticle().getCum().getTransformativeEffects();
	}
	
	
	
	// ------------------------------ Skin: ------------------------------ //
	
	// Type:
	public SkinType getSkinType() {
		return body.getSkin().getType();
	}
	public String setSkinType(SkinType type) {
		return body.getSkin().setType(this, type);
	}
	// Names:
	public String getSkinName() {
		return body.getSkin().getName(this);
	}
	public String getSkinName(boolean withDescriptor) {
		return body.getSkin().getName(this, withDescriptor);
	}
	public String getSkinDescriptor() {
		return body.getSkin().getDescriptor(this);
	}
	public String getSkinDeterminer() {
		return body.getSkin().getDeterminer(this);
	}
	public String getSkinPronoun() {
		return body.getSkin().getType().getPronoun();
	}
	public Covering getCovering(BodyCoveringType bodyCoveringType) {
		return body.getCoverings().get(bodyCoveringType);
	}

	/**
	 * @return Formatted description of skin colour change.
	 */
	public String setSkinCovering(Covering covering, boolean updateAllSkinColours) {
		if(!getCovering(getSkinType().getBodyCoveringType()).equals(covering)) {

			BodyCoveringType coveringType = covering.getType();
			
			body.getCoverings().put(coveringType, covering);
			
			body.updateCoverings(false, false, false, updateAllSkinColours);
			
			List<String> affectedParts = new ArrayList<>();
			for (BodyPartInterface part : body.getAllBodyParts()) {
				if (part.getType().getBodyCoveringType() == coveringType) {
					affectedParts.add(part.getName(this));
				}
			}
			
			if (!affectedParts.isEmpty()) {
				if (isPlayer()) {
					return "<p>"
								+ "The " + coveringType.getName(this) + " on your " + Util.stringsToStringList(affectedParts, false) + " suddenly start" + (coveringType.isDefaultPlural() ? "" : "s") + " to itch, and you let out a startled cry as "
								+ (coveringType.isDefaultPlural() ? "they begin" : "it begins") + " to change colour.</br>"
								+ "You now have "+covering.getFullDescription(this, true)+"."
							+ "</p>";
				} else {
					return UtilText.parse(this,
							"<p>"
								+ "[npc.Name] feels the " + coveringType.getName(this) + " on [npc.her] " + Util.stringsToStringList(affectedParts, false) + " suddenly start to itch, and [npc.she] lets out a startled cry as "
								+ (coveringType.isDefaultPlural() ? "they begin" : "it begins") + " to change colour.</br>"
								+ "[npc.She] now has "+covering.getFullDescription(this, true)+"."
							+ "</p>");
				}
			}
		}

		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}
	
	
	
	// ------------------------------ Tail: ------------------------------ //
	
	// Type:
	public TailType getTailType() {
		return body.getTail().getType();
	}
	public String setTailType(TailType type) {
		return body.getTail().setType(this, type);
	}
	// Misc.:
	public boolean isTailPrehensile(){
		return body.getTail().getType().isPrehensile();
	}
	// Names:
	public String getTailName() {
		return body.getTail().getName(this);
	}
	public String getTailName(boolean withDescriptor) {
		return body.getTail().getName(this, withDescriptor);
	}
	public String getTailDescriptor() {
		return body.getTail().getDescriptor(this);
	}
	public String getTailDeterminer() {
		return body.getTail().getDeterminer(this);
	}
	public String getTailPronoun() {
		return body.getTail().getType().getPronoun();
	}
	// Count:
	public int getTailCount() {
		return body.getTail().getTailCount();
	}
	public String setTailCount(int tailCount) {
		return body.getTail().setTailCount(this, tailCount);
	}
	public String incrementTailCount(int increment) {
		return body.getTail().setTailCount(this, getTailCount() + increment);
	}
	
	

	// ------------------------------ Vagina: ------------------------------ //
	
	// Type:
	public VaginaType getVaginaType() {
		return body.getVagina().getType();
	}
	public String setVaginaType(VaginaType type, boolean overridePregnancyPrevention) {
		return body.getVagina().setType(this, type, overridePregnancyPrevention);
	}
	public String setVaginaType(VaginaType type) {
		return body.getVagina().setType(this, type);
	}
	// Misc.:
	public boolean hasVagina() {
		return body.getVagina().getType() != VaginaType.NONE;
	}
	// Labia size:
	public LabiaSize getVaginaLabiaSize() {
		return body.getVagina().getLabiaSize();
	}
	public int getVaginaRawLabiaSizeValue() {
		return body.getVagina().getRawLabiaSizeValue();
	}
	public String setVaginaLabiaSize(int labiaSize) {
		return body.getVagina().setLabiaSize(this, labiaSize);
	}
	public String incrementVaginaLabiaSize(int increment) {
		return setVaginaLabiaSize(getVaginaRawLabiaSizeValue() + increment);
	}
	// Clitoris size:
	public ClitorisSize getVaginaClitorisSize() {
		return body.getVagina().getClitorisSize();
	}
	public int getVaginaRawClitorisSizeValue() {
		return body.getVagina().getRawClitorisSizeValue();
	}
	public String setVaginaClitorisSize(int clitSize) {
		return body.getVagina().setClitorisSize(this, clitSize);
	}
	public String incrementVaginaClitorisSize(int increment) {
		return setVaginaClitorisSize(getVaginaRawClitorisSizeValue() + increment);
	}
	// Names:
	public String getVaginaName(boolean withDescriptor) {
		return body.getVagina().getName(this, withDescriptor);
	}
	public String getVaginaDescriptor() {
		return body.getVagina().getDescriptor(this);
	}
	public String getVaginaDeterminer() {
		return body.getVagina().getDeterminer(this);
	}
	public String getVaginaPronoun() {
		return body.getVagina().getType().getPronoun();
	}
	public String getVaginaDescription() {
		return body.getVaginaDescription(this);
	}
	// Piercing:
	public boolean isPiercedVagina() {
		return body.getVagina().isPierced();
	}
	public String setPiercedVagina(boolean pierced) {
		return body.getVagina().setPierced(this, pierced);
	}
	// Orifice stats:
	// Wetness:
	public Wetness getVaginaWetness() {
		return body.getVagina().getOrificeVagina().getWetness(this);
	}
	public String setVaginaWetness(int wetness) {
		return body.getVagina().getOrificeVagina().setWetness(this, wetness);
	}
	public String incrementVaginaWetness(int increment) {
		return body.getVagina().getOrificeVagina().setWetness(this, getVaginaWetness().getValue() + increment);
	}
	// Capacity:
	public Capacity getVaginaCapacity() {
		return body.getVagina().getOrificeVagina().getCapacity();
	}
	public float getVaginaRawCapacityValue() {
		return body.getVagina().getOrificeVagina().getRawCapacityValue();
	}
	public float getVaginaStretchedCapacity() {
		return body.getVagina().getOrificeVagina().getStretchedCapacity();
	}
	public void setVaginaStretchedCapacity(float capacity){
		body.getVagina().getOrificeVagina().setStretchedCapacity(capacity);
	}
	public void incrementVaginaStretchedCapacity(float increment){
		body.getVagina().getOrificeVagina().setStretchedCapacity(getVaginaStretchedCapacity() + increment);
	}
	public String setVaginaCapacity(float capacity, boolean setStretchedValueToNewValue) {
		return body.getVagina().getOrificeVagina().setCapacity(this, capacity, setStretchedValueToNewValue);
	}
	public String incrementVaginaCapacity(float increment, boolean setStretchedValueToNewValue) {
		return setVaginaCapacity(getVaginaRawCapacityValue() + increment, setStretchedValueToNewValue);
	}
	// Elasticity:
	public OrificeElasticity getVaginaElasticity() {
		return body.getVagina().getOrificeVagina().getElasticity();
	}
	public String setVaginaElasticity(int elasticity) {
		return body.getVagina().getOrificeVagina().setElasticity(this, elasticity);
	}
	public String incrementVaginaElasticity(int increment) {
		return setVaginaElasticity(getVaginaElasticity().getValue() + increment);
	}
	// Plasticity:
	public OrificePlasticity getVaginaPlasticity() {
		return body.getVagina().getOrificeVagina().getPlasticity();
	}
	public String setVaginaPlasticity(int plasticity) {
		return body.getVagina().getOrificeVagina().setPlasticity(this, plasticity);
	}
	public String incrementVaginaPlasticity(int increment) {
		return setVaginaPlasticity(getVaginaPlasticity().getValue() + increment);
	}
	// Virginity:
	public boolean isVaginaVirgin() {
		return body.getVagina().getOrificeVagina().isVirgin();
	}
	public void setVaginaVirgin(boolean virgin) {
		body.getVagina().getOrificeVagina().setVirgin(virgin);
	}
	// Modifiers:
	public boolean hasVaginaOrificeModifier(OrificeModifier modifier) {
		return body.getVagina().getOrificeVagina().hasOrificeModifier(modifier);
	}
	public String addVaginaOrificeModifier(OrificeModifier modifier) {
		return body.getVagina().getOrificeVagina().addOrificeModifier(this, modifier);
	}
	public String removeVaginaOrificeModifier(OrificeModifier modifier) {
		return body.getVagina().getOrificeVagina().removeOrificeModifier(this, modifier);
	}
	
	// Girlcum:
	
	public String getGirlcumName() {
		return body.getVagina().getGirlcum().getName(this);
	}
	// Flavour:
	public FluidFlavour getGirlcumFlavour() {
		return body.getVagina().getGirlcum().getFlavour();
	}
	public String setGirlcumFlavour(FluidFlavour flavour) {
		return body.getVagina().getGirlcum().setFlavour(this, flavour);
	}
	// Modifiers:
	public boolean hasGirlcumModifier(FluidModifier fluidModifier) {
		return body.getVagina().getGirlcum().hasFluidModifier(fluidModifier);
	}
	public String addGirlcumModifier(FluidModifier fluidModifier) {
		return body.getVagina().getGirlcum().addFluidModifier(this, fluidModifier);
	}
	public String removeGirlcumModifier(FluidModifier fluidModifier) {
		return body.getVagina().getGirlcum().removeFluidModifier(this, fluidModifier);
	}
	// Transformations:
	public List<ItemEffect> getGirlcumTransformativeEffects() {
		return body.getVagina().getGirlcum().getTransformativeEffects();
	}
	
	
	// ------------------------------ Wings: ------------------------------ //
	
	// Type:
	public WingType getWingType() {
		return body.getWing().getType();
	}
	public String setWingType(WingType type) {
		return body.getWing().setType(this, type);
	}
	// Size:
	public WingSize getWingSize() {
		return body.getWing().getSize();
	}
	public int getWingSizeValue() {
		return body.getWing().getSizeValue();
	}
	public String setWingSize(int size) {
		return body.getWing().setSize(this, size);
	}
	public String incrementWingSize(int increment) {
		return body.getWing().setSize(this, getWingSizeValue()+increment);
	}
	// Names:
	public String getWingName() {
		return body.getWing().getName(this);
	}
	public String getWingName(boolean withDescriptor) {
		return body.getWing().getName(this, withDescriptor);
	}
	public String getWingDescriptor() {
		return body.getWing().getDescriptor(this);
	}
	public String getWingDeterminer() {
		return body.getWing().getDeterminer(this);
	}
	public String getWingPronoun() {
		return body.getWing().getType().getPronoun();
	}

}
