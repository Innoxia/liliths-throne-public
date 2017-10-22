package com.lilithsthrone.game.character;

import java.io.Serializable;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
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

import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.Covering;
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
import com.lilithsthrone.game.character.body.types.NippleType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TongueType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
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
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkInterface;
import com.lilithsthrone.game.character.effects.StatusEffect;
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
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryAttributeChange;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryEncyclopediaUnlock;
import com.lilithsthrone.game.dialogue.utils.MiscDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingSet;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.CoverableArea;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.PregnancyDescriptor;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.slavery.SlaveJob;
import com.lilithsthrone.game.slavery.SlaveJobSettings;
import com.lilithsthrone.game.slavery.SlavePermission;
import com.lilithsthrone.game.slavery.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.Dominion;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceInterface;
import com.lilithsthrone.world.places.SlaverAlley;

/**
 * The class for all the game's characters. I think this is the biggest class in the game.
 * 
 * @since 0.1.0
 * @version 0.1.87
 * @author Innoxia
 */
public class GameCharacter implements Serializable, XMLSaving {

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

	private int experience, levelUpPoints, perkPoints;
	
	protected WorldType worldLocation;
	protected WorldType homeWorldLocation;
	protected PlaceInterface startingPlace;
	protected Vector2i location;
	protected Vector2i homeLocation;
	
	protected Body body;
	protected CharacterInventory inventory;

	protected History history;
	protected Personality personality;
	protected SexualOrientation sexualOrientation;

	// Relationship stats:
	private Map<GameCharacter, Float> affectionMap;
	
	// Slavery:
	private float obedience;
	
	protected List<NPC> slavesOwned;
	protected GameCharacter owner;
	protected DialogueNodeOld enslavementDialogue;
	
	protected SlaveJob slaveJob;
	protected List<SlaveJobSettings> slaveJobSettings;
	protected Map<SlavePermission, Set<SlavePermissionSetting>> slavePermissionSettings;
	
	protected boolean[] workHours;
	
	// Family:
	protected GameCharacter mother, father;
	protected int dayOfConception, dayOfBirth;

	// Attributes, perks & status effects:
	protected Map<Attribute, Float> attributes;
	protected Map<Attribute, Float> bonusAttributes;
	protected Set<Perk> perks;
	protected Set<Fetish> fetishes;
	protected Map<StatusEffect, Integer> statusEffects;
	protected Map<StatusEffect, String> statusEffectDescriptions;
	
	// Keep a track of what potion effects this character is experiencing:
	protected Map<Attribute, Float> potionAttributes;

	// Combat:
	protected Set<Spell> spells;
	protected Set<SpecialAttack> specialAttacks;
	protected float health, mana, stamina;

	// Sex:
	protected Map<CoverableArea, Boolean> playerKnowsAreasMap;
	protected Map<OrificeType, Set<GameCharacter>> cummedInAreaMap;
	
	// Pregnancy:
	protected long timeProgressedToFinalPregnancyStage;
	protected List<PregnancyPossibility> potentialPartnersAsMother, potentialPartnersAsFather;
	protected Litter pregnantLitter;
	protected List<Litter> littersBirthed, littersFathered;

	// Clothes:
	protected int nakedSlots;
	
	// Stats:
	// Combat stats:
	private int foughtPlayerCount, lostCombatCount, wonCombatCount;
	
	
	// Sex stats:
	private int sexConsensualCount, sexAsSubCount, sexAsDomCount;
	private Map<SexType, Integer> sexCountMap;
	private Map<SexType, Integer> cumCountMap;
	private Map<SexType, String> virginityLossMap;
	private Map<GameCharacter, Map<SexType, Integer>> sexPartnerMap;

	
	// Misc.:
	protected static List<CharacterChangeEventListener> playerAttributeChangeEventListeners = new ArrayList<>();
	protected static List<CharacterChangeEventListener> NPCAttributeChangeEventListeners = new ArrayList<>();

	protected static List<CharacterChangeEventListener> NPCLocationChangeEventListeners = new ArrayList<>();
	protected static List<CharacterChangeEventListener> playerLocationChangeEventListeners = new ArrayList<>();

	protected static List<CharacterChangeEventListener> NPCInventoryChangeEventListeners = new ArrayList<>();
	protected static List<CharacterChangeEventListener> playerInventoryChangeEventListeners = new ArrayList<>();

	protected GameCharacter(NameTriplet nameTriplet, String description, int level, Gender startingGender, RacialBody startingRace, RaceStage stage, CharacterInventory inventory, WorldType worldLocation, PlaceInterface startingPlace) {
		
		id = "NPC-"+(Main.game.getNpcTally()+1); // id gets set in Game's addNPC method, so it doesn't matter if this is unique or not... Right?
		
		this.nameTriplet = nameTriplet;
		surname = "";
		playerKnowsName = true;
		this.description = description;
		this.level = level;
		
		this.worldLocation = worldLocation;
		this.homeWorldLocation = worldLocation;
		this.startingPlace = startingPlace;
		location = new Vector2i(0, 0);
		
		this.setWorldLocation(worldLocation);
		this.setLocation(Main.game.getWorlds().get(worldLocation).getCell(startingPlace).getLocation());
		homeLocation = location;
		
		history = History.UNEMPLOYED;
		personality = startingRace.getPersionality();
		sexualOrientation = startingRace.getSexualOrientation(startingGender);

		affectionMap = new HashMap<GameCharacter,Float>();
		
		obedience = 0;
		
		slavesOwned = new ArrayList<>();
		owner = null;
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
		
		mother = null;
		father = null;
		dayOfConception = 0;
		dayOfBirth = 0;
		
		levelUpPoints = 0;
		perkPoints = 0;
		experience = 0;

		nakedSlots = 0;

		if (inventory == null) {
			this.inventory = new CharacterInventory(0);
		} else {
			this.inventory = inventory;
		}
		
		attributes = new EnumMap<>(Attribute.class);
		bonusAttributes = new EnumMap<>(Attribute.class);
		perks = new HashSet<>();
		fetishes = new HashSet<>();
		statusEffectDescriptions = new EnumMap<>(StatusEffect.class);
		statusEffects = new EnumMap<>(StatusEffect.class);
		
		potionAttributes = new EnumMap<>(Attribute.class);

		spells = EnumSet.noneOf(Spell.class);
		specialAttacks = EnumSet.noneOf(SpecialAttack.class);

		// Player knowledge:
		playerKnowsAreasMap = new HashMap<>();
		for(CoverableArea ca : CoverableArea.values()) {
			playerKnowsAreasMap.put(ca, false);
		}
		
		cummedInAreaMap = new EnumMap<>(OrificeType.class);
		for(OrificeType ot : OrificeType.values()) {
			cummedInAreaMap.put(ot, new HashSet<>());
		}
		
		timeProgressedToFinalPregnancyStage = 1;
		pregnantLitter = null;
		littersBirthed = new ArrayList<>();
		littersFathered = new ArrayList<>();
		potentialPartnersAsMother = new ArrayList<>();
		potentialPartnersAsFather = new ArrayList<>();
		
		// Stats:
		initStats();
		
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
	}
	


	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = doc.createElement("character");
		parentElement.appendChild(properties);
		
		/*TODO
			// Family:
			protected GameCharacter mother, father;
			protected int dayOfConception, dayOfBirth;
		
			// Keep a track of what potion effects this character is experiencing:
			protected Map<Attribute, Float> potionAttributes;
		 */
		
		// Core information:
		Element characterCoreInfo = doc.createElement("core");
		Comment comment = doc.createComment("If you want to edit any of these values, just be warned that it might break the game...");
		properties.appendChild(characterCoreInfo);
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "id", this.getId());
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "name", this.getName());
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "surname", this.getSurname());
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "description", this.getDescription());//TODO
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "playerPetName", this.getPlayerPetName());//TODO
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "playerKnowsName", String.valueOf(this.isPlayerKnowsName()));//TODO
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "level", String.valueOf(this.getLevel()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "version", Main.VERSION_NUMBER);
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "history", this.getHistory().toString());//TODO
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "personality", this.getPersonality().toString());//TODO
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "sexualOrientation", this.getSexualOrientation().toString());//TODO
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "obedience", String.valueOf(this.getObedience()));//TODO
		characterCoreInfo.getParentNode().insertBefore(comment, characterCoreInfo);
		
		// Relationships:
		Element characterRelationships = doc.createElement("characterRelationships");//TODO
		properties.appendChild(characterRelationships);
		for(Entry<GameCharacter, Float> entry : this.getAffectionMap().entrySet()){
			Element relationship = doc.createElement("relationship");
			characterRelationships.appendChild(relationship);
			
			CharacterUtils.addAttribute(doc, relationship, "character", entry.getKey().getId());
			CharacterUtils.addAttribute(doc, relationship, "value", String.valueOf(entry.getValue()));
		}
		
		// Attributes:
		Element characterCoreAttributes = doc.createElement("attributes");
		properties.appendChild(characterCoreAttributes);
		for(Attribute att : Attribute.values()){
			Element element = doc.createElement("attribute");
			characterCoreAttributes.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "type", att.toString());
			CharacterUtils.addAttribute(doc, element, "value", String.valueOf(this.getBaseAttributeValue(att)));
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
		
		// Status effects:
		Element characterStatusEffects = doc.createElement("statusEffects");
		properties.appendChild(characterStatusEffects);
		for(StatusEffect se : this.getStatusEffects()){
			Element element = doc.createElement("statusEffect");
			characterStatusEffects.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "type", se.toString());
			CharacterUtils.addAttribute(doc, element, "value", String.valueOf(this.getStatusEffectDuration(se)));
		}
		
		// Combat (spells and special attacks): TODO
//		Element characterCombat = doc.createElement("combat");
//		properties.appendChild(characterCombat);
//		for(Spell s : Spell.values()){
//			Element element = doc.createElement("spell");
//			characterCombat.appendChild(element);
//			
//			CharacterUtils.addAttribute(doc, element, "type", s.toString());
//			CharacterUtils.addAttribute(doc, element, "value", String.valueOf(this.getSpells().contains(s)));
//		}
//		for(SpecialAttack sa : SpecialAttack.values()){
//			Element element = doc.createElement("specialAttack");
//			characterCombat.appendChild(element);
//			
//			CharacterUtils.addAttribute(doc, element, "type", sa.toString());
//			CharacterUtils.addAttribute(doc, element, "value", String.valueOf(this.getSpecialAttacks().contains(sa)));
//		}
		
		// Pregnancy: TODO
//		Element characterPregnancy = doc.createElement("pregnancy");
//		properties.appendChild(characterPregnancy);
//		
//		Element characterPregnancyPossibility = doc.createElement("pregnancyPossibilities");
//		characterPregnancy.appendChild(characterPregnancyPossibility);
//		for(PregnancyPossibility pregPoss : this.getPotentialPartnersAsMother()) {
//			Element element = doc.createElement("possibility");
//			characterPregnancyPossibility.appendChild(element);
//			
////			CharacterUtils.addAttribute(doc, element, "fatherName", pregPoss.getFatherName());
////			CharacterUtils.addAttribute(doc, element, "gender", pregPoss.getGender().toString());
////			CharacterUtils.addAttribute(doc, element, "race", pregPoss.getRace().toString());
////			CharacterUtils.addAttribute(doc, element, "raceStage", pregPoss.getRaceStage().toString());
//			CharacterUtils.addAttribute(doc, element, "probability", String.valueOf(pregPoss.getProbability()));
//		}
//		
//		Element characterPregnancyCurrentLitter = doc.createElement("pregnantLitter");
//		characterPregnancy.appendChild(characterPregnancyCurrentLitter);
//		if (this.getPregnantLitter() != null) {
//			Element element = doc.createElement("litter");
//			characterPregnancyCurrentLitter.appendChild(element);
//
//			CharacterUtils.addAttribute(doc, element, "dayOfConception", String.valueOf(this.getPregnantLitter().getDayOfBirth()));
//			CharacterUtils.addAttribute(doc, element, "dayOfBirth", String.valueOf(this.getPregnantLitter().getDayOfBirth()));
////			CharacterUtils.addAttribute(doc, element, "fatherName", this.getPregnantLitter().getPartner().getName("a"));
////			CharacterUtils.addAttribute(doc, element, "race", this.getPregnantLitter().getRace().toString());
//			CharacterUtils.addAttribute(doc, element, "sons", String.valueOf(this.getPregnantLitter().getSons()));
//			CharacterUtils.addAttribute(doc, element, "daughters", String.valueOf(this.getPregnantLitter().getDaughters()));
//		}
//		
//		Element characterPregnancyBirthedLitters = doc.createElement("birthedLitters");
//		characterPregnancy.appendChild(characterPregnancyBirthedLitters);
//		for(Litter litter : this.getLittersBirthed()) {
//			Element element = doc.createElement("birthedLitter");
//			characterPregnancyBirthedLitters.appendChild(element);
//
//			CharacterUtils.addAttribute(doc, element, "dayOfConception", String.valueOf(litter.getDayOfBirth()));
//			CharacterUtils.addAttribute(doc, element, "dayOfBirth", String.valueOf(litter.getDayOfBirth()));
////			CharacterUtils.addAttribute(doc, element, "fatherName", litter.getPartner().getName("a"));
////			CharacterUtils.addAttribute(doc, element, "race", litter.getRace().toString());
//			CharacterUtils.addAttribute(doc, element, "sons", String.valueOf(litter.getSons()));
//			CharacterUtils.addAttribute(doc, element, "daughters", String.valueOf(litter.getDaughters()));
//		}
		
		// Body:
		Element characterBody = doc.createElement("body");
		properties.appendChild(characterBody);
		
		this.body.saveAsXML(characterBody, doc);
		

//		System.out.println("Difference1: "+(System.nanoTime()-timeStart)/1000000000f);
		
		// PlayerCharacter specific:
		
		// Core:
		Element characterPlayerCoreInfo = doc.createElement("playerCore");
		properties.appendChild(characterPlayerCoreInfo);
		CharacterUtils.createXMLElementWithValue(doc, characterPlayerCoreInfo, "experience", String.valueOf(this.getExperience()));
		CharacterUtils.createXMLElementWithValue(doc, characterPlayerCoreInfo, "levelUpPoints", String.valueOf(this.getLevelUpPoints()));
		CharacterUtils.createXMLElementWithValue(doc, characterPlayerCoreInfo, "perkPoints", String.valueOf(this.getPerkPoints()));
		
		// Sex stats:
		Element characterSexStats = doc.createElement("sexStats");
		properties.appendChild(characterSexStats);
		
		Element characterCumCount = doc.createElement("cumCounts");
		characterSexStats.appendChild(characterCumCount);
		for(PenetrationType pt : PenetrationType.values()) {
			for(OrificeType ot : OrificeType.values()) {
				if(this.getCumCount(new SexType(pt, ot)) > 0) {
					Element element = doc.createElement("cumCount");
					characterCumCount.appendChild(element);
					
					CharacterUtils.addAttribute(doc, element, "penetrationType", pt.toString());
					CharacterUtils.addAttribute(doc, element, "orificeType", ot.toString());
					CharacterUtils.addAttribute(doc, element, "count", String.valueOf(this.getCumCount(new SexType(pt, ot))));
				}
			}
		}

		Element characterSexCount = doc.createElement("sexCounts");
		characterSexStats.appendChild(characterSexCount);
		for(PenetrationType pt : PenetrationType.values()) {
			for(OrificeType ot : OrificeType.values()) {
				if(this.getSexCount(new SexType(pt, ot)) > 0) {
					Element element = doc.createElement("sexCount");
					characterSexCount.appendChild(element);
					
					CharacterUtils.addAttribute(doc, element, "penetrationType", pt.toString());
					CharacterUtils.addAttribute(doc, element, "orificeType", ot.toString());
					CharacterUtils.addAttribute(doc, element, "count", String.valueOf(this.getSexCount(new SexType(pt, ot))));
				}
			}
		}

		Element characterVirginityTakenBy = doc.createElement("virginityTakenBy");
		characterSexStats.appendChild(characterVirginityTakenBy);
		for(PenetrationType pt : PenetrationType.values()) {
			for(OrificeType ot : OrificeType.values()) {
				if(this.getVirginityLoss(new SexType(pt, ot))!=null && !this.getVirginityLoss(new SexType(pt, ot)).isEmpty()) {
					Element element = doc.createElement("virginity");
					characterVirginityTakenBy.appendChild(element);
					
					CharacterUtils.addAttribute(doc, element, "penetrationType", pt.toString());
					CharacterUtils.addAttribute(doc, element, "orificeType", ot.toString());
					CharacterUtils.addAttribute(doc, element, "takenBy", String.valueOf(this.getVirginityLoss(new SexType(pt, ot))));
				}
			}
		}
		
		
		// Inventory:
		Element characterInventory = doc.createElement("characterInventory");
		properties.appendChild(characterInventory);
		CharacterUtils.createXMLElementWithValue(doc, characterInventory, "money", String.valueOf(this.getMoney()));
		CharacterUtils.createXMLElementWithValue(doc, characterInventory, "essences", String.valueOf(this.getEssenceCount(TFEssence.ARCANE)));
		
		if(this.getMainWeapon() != null) {
			Element mainWeapon = doc.createElement("mainWeapon");
			characterInventory.appendChild(mainWeapon);
			this.getMainWeapon().saveAsXML(mainWeapon, doc);
		}
		
		if(this.getOffhandWeapon() != null) {
			Element offhandWeapon = doc.createElement("offhandWeapon");
			characterInventory.appendChild(offhandWeapon);
			this.getOffhandWeapon().saveAsXML(offhandWeapon, doc);
		}
		
		Element clothingEquipped = doc.createElement("clothingEquipped");
		characterInventory.appendChild(clothingEquipped);
		for(AbstractClothing clothing : this.getClothingCurrentlyEquipped()) {
			clothing.saveAsXML(clothingEquipped, doc);
		}
		
		Element itemsInInventory = doc.createElement("itemsInInventory");
		characterInventory.appendChild(itemsInInventory);
		for(Entry<AbstractItem, Integer> item : this.getMapOfDuplicateItems().entrySet()) {
			Element e = item.getKey().saveAsXML(itemsInInventory, doc);
			CharacterUtils.addAttribute(doc, e, "count", String.valueOf(item.getValue()));
		}
		
		Element clothingInInventory = doc.createElement("clothingInInventory");
		characterInventory.appendChild(clothingInInventory);
		for(Entry<AbstractClothing, Integer> clothing : this.getMapOfDuplicateClothing().entrySet()) {
			Element e = clothing.getKey().saveAsXML(clothingInInventory, doc);
			CharacterUtils.addAttribute(doc, e, "count", String.valueOf(clothing.getValue()));
		}
		
		Element weaponsInInventory = doc.createElement("weaponsInInventory");
		characterInventory.appendChild(weaponsInInventory);
		for(Entry<AbstractWeapon, Integer> weapon : this.getMapOfDuplicateWeapons().entrySet()) {
			Element e = weapon.getKey().saveAsXML(weaponsInInventory, doc);
			CharacterUtils.addAttribute(doc, e, "count", String.valueOf(weapon.getValue()));
		}
		
		return properties;
	}
	
	public static GameCharacter loadFromXML(StringBuilder log, Element parentElement, Document doc) {
		GameCharacter character = new GameCharacter(new NameTriplet(""), "", 0, Gender.F_V_B_FEMALE, RacialBody.HUMAN, RaceStage.HUMAN, new CharacterInventory(0), WorldType.DOMINION, Dominion.CITY_AUNTS_HOME);
		
		// Core info:
		NodeList nodes = parentElement.getElementsByTagName("core");
		Element element = (Element) nodes.item(0);

		// Name:
		character.setName(new NameTriplet(((Element)element.getElementsByTagName("name").item(0)).getAttribute("value")));
		CharacterUtils.appendToImportLog(log, "</br>Set name: " + ((Element)element.getElementsByTagName("name").item(0)).getAttribute("value"));
		
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
		}
		if(element.getElementsByTagName("playerPetName").getLength()!=0) {
			character.setPlayerPetName(((Element)element.getElementsByTagName("playerPetName").item(0)).getAttribute("value"));
		}
		if(element.getElementsByTagName("playerKnowsName").getLength()!=0) {
			character.setPlayerKnowsName(Boolean.valueOf(((Element)element.getElementsByTagName("playerKnowsName").item(0)).getAttribute("value")));
		}
		if(element.getElementsByTagName("history").getLength()!=0) {
			character.setHistory(History.valueOf(((Element)element.getElementsByTagName("history").item(0)).getAttribute("value")));
		}
		if(element.getElementsByTagName("personality").getLength()!=0) {
			character.setPersonality(Personality.valueOf(((Element)element.getElementsByTagName("personality").item(0)).getAttribute("value")));
		}
		if(element.getElementsByTagName("obedience").getLength()!=0) {
			character.setObedience(Float.valueOf(((Element)element.getElementsByTagName("obedience").item(0)).getAttribute("value")));
		}
		
		
		
		// Temp fix for perk points:
		character.setPerkPoints((Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")))-1);
		CharacterUtils.appendToImportLog(log, "</br>Set perkPoints: (TEMP FIX) " + (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))-1));
		
		int extraLevelUpPoints=0;
		// If there is a version number, attributes should be working correctly:
		if(element.getElementsByTagName("version").item(0)!=null) {
			nodes = parentElement.getElementsByTagName("attributes");
			element = (Element) nodes.item(0);
			for(int i=0; i<element.getElementsByTagName("attribute").getLength(); i++){
				Element e = ((Element)element.getElementsByTagName("attribute").item(i));
				
				try {
					character.setAttribute(Attribute.valueOf(e.getAttribute("type")), Float.valueOf(e.getAttribute("value")));
					CharacterUtils.appendToImportLog(log, "</br>Set Attribute: "+Attribute.valueOf(e.getAttribute("type")).getName() +" to "+ Float.valueOf(e.getAttribute("value")));
				}catch(IllegalArgumentException ex){
				}
			}
			
		} else {
			extraLevelUpPoints = (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")) * 5);
			CharacterUtils.appendToImportLog(log, "</br>Old character version. Extra LevelUpPoints set to: "+(Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")) * 5));
		}
		
		
		nodes = parentElement.getElementsByTagName("characterRelationships");
		element = (Element) nodes.item(0);
		for(int i=0; i<element.getElementsByTagName("characterRelationships").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("relationship").item(i));
			
			if(e.getAttribute("character").equals("PlayerCharacter") && !character.isPlayer()) {
				character.setAffection(Main.game.getPlayer(), Float.valueOf(e.getAttribute("value")));
				CharacterUtils.appendToImportLog(log, "</br>Set Relationship: "+e.getAttribute("character") +" , "+ Float.valueOf(e.getAttribute("value")));
			}
		}
		
		// Fetishes:
		nodes = parentElement.getElementsByTagName("fetishes");
		element = (Element) nodes.item(0);
		if(element!=null) {
			for(int i=0; i<element.getElementsByTagName("fetish").getLength(); i++){
				Element e = ((Element)element.getElementsByTagName("fetish").item(i));
				
				try {
					if(Fetish.valueOf(e.getAttribute("type")) != null) {
						character.addFetish(Fetish.valueOf(e.getAttribute("type")));
						CharacterUtils.appendToImportLog(log, "</br>Added Fetish: "+Fetish.valueOf(e.getAttribute("type")).getName(character));
					}
				}catch(IllegalArgumentException ex){
				}
			}
		}
		
		// Perks:
//		nodes = parentElement.getElementsByTagName("perks");
//		element = (Element) nodes.item(0);
//		for(int i=0; i<element.getElementsByTagName("perk").getLength(); i++){
//			Element e = ((Element)element.getElementsByTagName("perk").item(i));
//			
//			try {
//				if(Boolean.valueOf(e.getAttribute("value"))) {
//					character.addPerk(Perk.valueOf(e.getAttribute("type")));
//					CharacterUtils.appendToImportLog(log, "</br>Added Perk: "+Perk.valueOf(e.getAttribute("type")).getName(character));
//				}
//			}catch(IllegalArgumentException ex){
//			}
//		}
		
		// Status Effects:
		nodes = parentElement.getElementsByTagName("statusEffects");
		element = (Element) nodes.item(0);
		for(int i=0; i<element.getElementsByTagName("statusEffect").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("statusEffect").item(i));
			
			try {
				if(Integer.valueOf(e.getAttribute("value"))!=-1) {
					character.addStatusEffect(StatusEffect.valueOf(e.getAttribute("type")), Integer.valueOf(e.getAttribute("value")));
					CharacterUtils.appendToImportLog(log, "</br>Added Status Effect: "+StatusEffect.valueOf(e.getAttribute("type")).getName(character)+" ("+Integer.valueOf(e.getAttribute("value"))+" minutes)");
				}
			}catch(IllegalArgumentException ex){
			}
		}
		
		// Combat:
		// TODO Combat is all derived from weapons/perks/body parts, so there's no reason for this to even be here...
		
		// Pregnancy: TODO
//		nodes = parentElement.getElementsByTagName("pregnancy");
//		// Possibilities:
//		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("pregnancyPossibilities").item(0);
//		for(int i=0; i<element.getElementsByTagName("possibility").getLength(); i++){
//			Element e = ((Element)element.getElementsByTagName("possibility").item(i));
//			
//			try {
//				character.getPotentialPartnersAsMother().add(new PregnancyPossibility(
//						null,
//						null,
//						Float.valueOf(e.getAttribute("probability"))));
//				CharacterUtils.appendToImportLog(log, "</br>Added Pregnancy Possibility: Father:"+e.getAttribute("fatherName"));
//			}catch(IllegalArgumentException ex){
//			}
//		}
//		// Litter:
//		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("pregnantLitter").item(0);
//		for(int i=0; i<element.getElementsByTagName("litter").getLength(); i++){
//			Element e = ((Element)element.getElementsByTagName("litter").item(i));
//			
//			try {
//				character.setPregnantLitter(new Litter(
//						Integer.valueOf(e.getAttribute("dayOfConception")),
//						Integer.valueOf(e.getAttribute("dayOfBirth")),
//						null,
//						null,
//						Race.valueOf(e.getAttribute("race")),
//						Integer.valueOf(e.getAttribute("sons")),
//						Integer.valueOf(e.getAttribute("daughters"))));
//				CharacterUtils.appendToImportLog(log, "</br>Added Litter: Father:"+e.getAttribute("fatherName"));
//			}catch(IllegalArgumentException ex){
//			}
//		}
//		// Birthed Litters:
//		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("birthedLitters").item(0);
//		for(int i=0; i<element.getElementsByTagName("birthedLitter").getLength(); i++){
//			Element e = ((Element)element.getElementsByTagName("birthedLitter").item(i));
//			
//			try {
//				character.getLittersBirthed().add(new Litter(
//						Integer.valueOf(e.getAttribute("dayOfConception")),
//						Integer.valueOf(e.getAttribute("dayOfBirth")),
//						null,
//						null,
//						Race.valueOf(e.getAttribute("race")),
//						Integer.valueOf(e.getAttribute("sons")),
//						Integer.valueOf(e.getAttribute("daughters"))));
//				CharacterUtils.appendToImportLog(log, "</br>Added Birthed Litter: Father:"+e.getAttribute("fatherName"));
//			}catch(IllegalArgumentException ex){
//			}
//		}
		
		// Body:
		character.body = Body.loadFromXML(log, (Element) parentElement.getElementsByTagName("body").item(0), doc);
		
		// Player Core info:
		nodes = parentElement.getElementsByTagName("playerCore");
		element = (Element) nodes.item(0);

		// Experience:
		character.incrementExperience(Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")));
		CharacterUtils.appendToImportLog(log, "</br>Set experience: " + Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")));
		
		// Level up points:
		character.setLevelUpPoints(Integer.valueOf(((Element)element.getElementsByTagName("levelUpPoints").item(0)).getAttribute("value")) + extraLevelUpPoints);
		CharacterUtils.appendToImportLog(log, "</br>Set levelUpPoints: " + (Integer.valueOf(((Element)element.getElementsByTagName("levelUpPoints").item(0)).getAttribute("value")) + extraLevelUpPoints));

		// Perk points:
		//character.setPerkPoints(Integer.valueOf(((Element)element.getElementsByTagName("perkPoints").item(0)).getAttribute("value")));
//		character.setPerkPoints((Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))));
//		
//		CharacterUtils.appendToImportLog(log, "</br>Set perkPoints: (TEMP FIX) " + (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))));
		
		// Sex stats:
		nodes = parentElement.getElementsByTagName("sexStats");
		
		// Cum counts:
		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("cumCounts").item(0);
		for(int i=0; i<element.getElementsByTagName("cumCount").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("cumCount").item(i));
			
			try {
				for(int it =0 ; it<Integer.valueOf(e.getAttribute("count")) ; it++)
					character.incrementCumCount(new SexType(PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))));
				CharacterUtils.appendToImportLog(log, "</br>Added cum count:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" x "+Integer.valueOf(e.getAttribute("count")));
			}catch(IllegalArgumentException ex){
			}
		}
		
		// Sex counts:
		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("sexCounts").item(0);
		for(int i=0; i<element.getElementsByTagName("sexCount").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("sexCount").item(i));
			
			try {
				for(int it =0 ; it<Integer.valueOf(e.getAttribute("count")) ; it++)
					character.incrementSexCount(new SexType(PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))));
				CharacterUtils.appendToImportLog(log, "</br>Added sex count:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" x "+Integer.valueOf(e.getAttribute("count")));
			}catch(IllegalArgumentException ex){
			}
		}
		
		// Virginity losses:
		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("virginityTakenBy").item(0);
		for(int i=0; i<element.getElementsByTagName("virginity").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("virginity").item(i));

			character.setVirginityLoss(new SexType(PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))), e.getAttribute("takenBy"));
			CharacterUtils.appendToImportLog(log, "</br>Added virginity loss:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" (taken by:"+e.getAttribute("takenBy")+")");
		}
		
		
		
		// Inventory:
		character.resetInventory();
		nodes = parentElement.getElementsByTagName("characterInventory");
		element = (Element) nodes.item(0);

		character.setMoney(Integer.valueOf(((Element)element.getElementsByTagName("money").item(0)).getAttribute("value")));
		character.setEssenceCount(TFEssence.ARCANE, Integer.valueOf(((Element)element.getElementsByTagName("essences").item(0)).getAttribute("value")));
		
		if(element.getElementsByTagName("mainWeapon").item(0)!=null) {
			character.equipMainWeaponFromNowhere(AbstractWeapon.loadFromXML(
					(Element) ((Element)element.getElementsByTagName("mainWeapon").item(0)).getElementsByTagName("weapon").item(0),
					doc));
		}

		if(element.getElementsByTagName("offhandWeapon").item(0)!=null) {
			character.equipOffhandWeaponFromNowhere(AbstractWeapon.loadFromXML(
					(Element) ((Element)element.getElementsByTagName("offhandWeapon").item(0)).getElementsByTagName("weapon").item(0),
					doc));
		}
		
		Element clothingEquipped = (Element) element.getElementsByTagName("clothingEquipped").item(0);
		for(int i=0; i<clothingEquipped.getElementsByTagName("clothing").getLength(); i++){
			Element e = ((Element)clothingEquipped.getElementsByTagName("clothing").item(i));
			
			character.equipClothingOverride(AbstractClothing.loadFromXML(e, doc));
		}
		//TODO count
		Element itemsInInventory = (Element) element.getElementsByTagName("itemsInInventory").item(0);
		for(int i=0; i<itemsInInventory.getElementsByTagName("item").getLength(); i++){
			Element e = ((Element)itemsInInventory.getElementsByTagName("item").item(i));
			
			for(int itemCount = 0 ; itemCount < Integer.valueOf(e.getAttribute("count")); itemCount++) {
				character.addItem(AbstractItem.loadFromXML(e, doc), false);
			}
		}
		
		Element clothingInInventory = (Element) element.getElementsByTagName("clothingInInventory").item(0);
		for(int i=0; i<clothingInInventory.getElementsByTagName("clothing").getLength(); i++){
			Element e = ((Element)clothingInInventory.getElementsByTagName("clothing").item(i));

			for(int clothingCount = 0 ; clothingCount < Integer.valueOf(e.getAttribute("count")); clothingCount++) {
				character.addClothing(AbstractClothing.loadFromXML(e, doc), false);
			}
		}
		
		Element weaponsInInventory = (Element) element.getElementsByTagName("weaponsInInventory").item(0);
		for(int i=0; i<weaponsInInventory.getElementsByTagName("weapon").getLength(); i++){
			Element e = ((Element)weaponsInInventory.getElementsByTagName("weapon").item(i));

			for(int weaponCount = 0 ; weaponCount < Integer.valueOf(e.getAttribute("count")); weaponCount++) {
				character.addWeapon(AbstractWeapon.loadFromXML(e, doc), false);
			}
		}
		
		
		character.setLocation(new Vector2i(0, 0));
		
		return character;
	}
	
	
	
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
			return determiner + " " + getName();
		}
	}

	public boolean isPlayerKnowsName() {
		return playerKnowsName;
	}

	public void setPlayerKnowsName(boolean playerKnowsName) {
		this.playerKnowsName = playerKnowsName;
	}

	public Map<CoverableArea, Boolean> getPlayerKnowsAreasMap() {
		return playerKnowsAreasMap;
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

	public String getName() {
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
			incrementAttribute(att, -this.history.getAttributeModifiers().get(att));
		this.history.revertExtraEffects(this);

		// Implement attributes from new History:
		for (Attribute att : history.getAttributeModifiers().keySet())
			incrementAttribute(att, history.getAttributeModifiers().get(att));
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
	
	public float getObedience() {
		return obedience;
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
		
		setObedience(getObedience()+increment);
		
		return UtilText.parse(this,
				"<p style='text-align:center'>"
						+ "[npc.Name] "+(increment>0?"[style.boldGrow(gains)]":"[style.boldShrink(loses)]")+" <b>"+Math.abs(increment)+"</b> [style.boldObedience(obedience)]!"
					+ "</p>");
	}
	
	public float getHourlyObedienceChange(int hour) {
		if(this.workHours[hour]) {
			if(this.getSlaveJob()==SlaveJob.IDLE) {
				return this.getHomeLocationPlace().getObedienceChange();
			}
			return this.getSlaveJob().getObedienceGain();
		}

		return this.getHomeLocationPlace().getObedienceChange();
	}
	
	public float getDailyObedienceChange() {
		float totalObedienceChange = 0;
		
		for (int workHour = 0; workHour < this.getTotalHoursWorked(); workHour++) {
			if(this.getSlaveJob()==SlaveJob.IDLE) {
				totalObedienceChange+=this.getHomeLocationPlace().getObedienceChange();
			}
			totalObedienceChange+=this.getSlaveJob().getObedienceGain();
			
		}
		
		for (int homeHour = 0; homeHour < 24-this.getTotalHoursWorked(); homeHour++) {
			totalObedienceChange+=this.getHomeLocationPlace().getObedienceChange();
		}
		// To get rid of e.g. 2.3999999999999999999999:
		return Math.round(totalObedienceChange*100)/100;
	}
	
	public int getSlavesWorkingJob(SlaveJob job) {
		int i=0;
			for(NPC slave : this.getSlavesOwned()) {
				if(slave.getSlaveJob()==job) {
					i++;
				}
			}
		return i;
	}
	
	public int getValueAsSlave() {
		int value = 1000;
		
		value += (getFetishes().size()*50);
		
		value *= (100+(getObedience()/2))/100f;
		
		return value;
	}
	
	public SlaveJob getSlaveJob() {
		return slaveJob;
	}

	public void setSlaveJob(SlaveJob slaveJob) {
		slaveJobSettings.clear();
		this.slaveJob = slaveJob;
	}
	
	public boolean addSlaveJobSettings(SlaveJobSettings setting) {
		return slaveJobSettings.add(setting);
	}
	
	public boolean removeSlaveJobSettings(SlaveJobSettings setting) {
		return slaveJobSettings.remove(setting);
	}
	
	public List<SlaveJobSettings> getSlaveJobSettings() {
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
	public Map<GameCharacter, Float> getAffectionMap() {
		return affectionMap;
	}
	
	public float getAffection(GameCharacter character) {
		if(affectionMap.containsKey(character)) {
			return affectionMap.get(character);
		} else {
			affectionMap.put(character, 0f);
			return 0;
		}
	}
	
	public AffectionLevel getAffectionLevel(GameCharacter character) {
		if(affectionMap.containsKey(character)) {
			return AffectionLevel.getAffectionLevelFromValue(affectionMap.get(character));
		} else {
			return AffectionLevel.getAffectionLevelFromValue(0);
		}
	}
	
	/**
	 * Sets this character's affection towards the supplied GameCharacter.
	 * 
	 * @param character
	 * @param affection
	 * @return
	 */
	public String setAffection(GameCharacter character, float affection) {
		
		affectionMap.put(character, Math.max(-100, Math.min(100, affection)));
		
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
	
	public List<NPC> getSlavesOwned() {
		return slavesOwned;
	}
	
	public int getNumberOfSlavesIdle() {
		int i=0;
		for(NPC slave : slavesOwned) {
			if(slave.getSlaveJob()==SlaveJob.IDLE) {
				i++;
			}
		}
		return i;
	}
	
	public int getNumberOfSlavesInAdministration() {
		int i=0;
		for(NPC slave : slavesOwned) {
			if(slave.getLocationPlace().getPlaceType() == SlaverAlley.SLAVERY_ADMINISTRATION) {
				i++;
			}
		}
		return i;
	}
	
	public int getSlaveryTotalDailyIncome() {
		int i=0;
		for(NPC slave : slavesOwned) {
			i += slave.getSlaveJob().getFinalDailyIncomeAfterModifiers(slave);
		}
		return i;
	}
	
	public int getSlaveryTotalDailyUpkeep() {
		int i=0;
		for(Cell c : MiscDialogue.importantCells) {
			i += c.getPlace().getUpkeep();
		}
		return i;
	}
	
	public boolean addSlave(NPC slave) {
		boolean added = slavesOwned.add(slave);
		
		if(added) {
			if(slave.isSlave()) {
				slave.getOwner().removeSlave(slave);
			}
			slave.setOwner(this);
		}
		
		return added;
	}
	
	public boolean removeSlave(GameCharacter slave) {
		boolean removed = slavesOwned.remove(slave);
		
		if(removed) {
			slave.setOwner(null);
		}
		
		return removed;
	}
	
	public GameCharacter getOwner() {
		return owner;
	}

	/**
	 * Do not call this method directly! Use the owner's addSlave() and removeSlave() methods!
	 * @param owner
	 */
	protected void setOwner(GameCharacter owner) {
		this.owner = owner;
	}
	
	public boolean isSlave() {
		return owner != null;
	}
	
	
	
	public GameCharacter getMother() {
		return mother;
	}

	public void setMother(GameCharacter mother) {
		this.mother = mother;
	}

	public GameCharacter getFather() {
		return father;
	}

	public void setFather(GameCharacter father) {
		this.father = father;
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

		if (level == 20) {
			experience = 0;
			return;
		}

		experience += increment;

		if (experience >= level * 10)
			levelUp();
	}

	private void levelUp() {
		// For handling health, mana and stamina changes as a result of an
		// attribute being changed:
		float healthPercentage = getHealthPercentage();
		float manaPercentage = getManaPercentage();
		float staminaPercentage = getStaminaPercentage();
		
		while (experience >= level * 10 && level < 20) {
			experience = experience % (level * 10);

			level++;

			levelUpPoints += 5;
			perkPoints++;
		}
		if (level == 20) {
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
			return (level * 5) + getAttributeValue(Attribute.STRENGTH);
		}

		// Special case for mana:
		if (attribute == Attribute.MANA_MAXIMUM) {
			return (level * 5) + getAttributeValue(Attribute.INTELLIGENCE);
		}

		// Special case for stamina:
		if (attribute == Attribute.STAMINA_MAXIMUM) {
			return (level * 5) + getAttributeValue(Attribute.FITNESS);
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
			float healthMax = (level * 10) + getAttributeValue(Attribute.STRENGTH) + bonusAttributes.get(Attribute.HEALTH_MAXIMUM);
			return (healthMax < 1 ? 1 : healthMax);
		}

		// Special case for mana:
		else if (att == Attribute.MANA_MAXIMUM) {
			float manaMax = (level * 10) + getAttributeValue(Attribute.INTELLIGENCE) + bonusAttributes.get(Attribute.MANA_MAXIMUM);
			return (manaMax < 1 ? 1 : manaMax);
		}

		// Special case for stamina:
		else if (att == Attribute.STAMINA_MAXIMUM) {
			float maxStamina = (level * 10) + getAttributeValue(Attribute.FITNESS) + bonusAttributes.get(Attribute.STAMINA_MAXIMUM);
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
		return incrementAttribute(att, value - attributes.get(att));
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
		
		if(isPlayer() && att != Attribute.AROUSAL) {
			Main.game.addEvent(new EventLogEntryAttributeChange(att, increment, true), appendAttributeChangeText);
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
		tempPerkList.sort(Comparator.comparingInt(PerkInterface::getRenderingPriority));
		return tempPerkList;
	}

	/**The returned list is ordered by rendering priority.*/
	public List<Fetish> getFetishes() {
		List<Fetish> tempFetishList = new ArrayList<>(fetishes);
		tempFetishList.sort(Comparator.comparingInt(Fetish::getRenderingPriority));
		return tempFetishList;
	}
	
//	public boolean hasFetish(String f) {
//		Fetish p = Fetish.valueOf(f.toUpperCase(Locale.ENGLISH));
//		return hasPerk(p);
//	}
//	public boolean hasPerk(String p) {
//		Perk pe = Perk.valueOf(p.toUpperCase(Locale.ENGLISH));
//		return hasPerk(pe);
//	}
	
	public boolean hasFetish(Fetish f) {
		return fetishes.contains(f);
	}
	
	public boolean addFetish(Fetish fetish) {
		if (fetishes.contains(fetish))
			return false;

		fetishes.add(fetish);

		// Increment bonus attributes from this fetish:
		if (fetish.getAttributeModifiers() != null)
			for (Entry<Attribute, Integer> e : fetish.getAttributeModifiers().entrySet())
				incrementBonusAttribute(e.getKey(), e.getValue());

		calculateSpecialAttacks();
		
		updateAttributeListeners();
		
		calculateSpecialFetishes();

		return true;
	}

	public boolean removeFetish(Fetish fetish) {
		if (!fetishes.contains(fetish))
			return false;

		fetishes.remove(fetish);

		// Reverse bonus attributes from this fetish:
		if (fetish.getAttributeModifiers() != null)
			for (Entry<Attribute, Integer> e : fetish.getAttributeModifiers().entrySet())
				incrementBonusAttribute(e.getKey(), -e.getValue());

		calculateSpecialAttacks();
		
		updateAttributeListeners();

		calculateSpecialFetishes();
		
		return true;
	}
	
	private void calculateSpecialFetishes() {
		for(Fetish f : Fetish.values()) {
			if(!f.getFetishesForAutomaticUnlock().isEmpty() && !hasFetish(f)) {
				boolean conditionsMet = true;
				for(Fetish fetishNeeded : f.getFetishesForAutomaticUnlock()) {
					if(!hasFetish(fetishNeeded)) {
						conditionsMet = false;
						break;
					}
				}
				if(conditionsMet) {
					addFetish(f);
				}
			}
		}
	}
	
	public boolean hasPerk(Perk p) {
		return perks.contains(p);
	}

	public boolean addPerk(Perk perk) {
		if (perks.contains(perk))
			return false;

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
	
	private void initStats() {
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
		
	}

	public Map<SexType, Integer> getSexPartnerStats(GameCharacter c) {
		return sexPartnerMap.get(c);
	}
	
	public Map<GameCharacter, Map<SexType, Integer>> getSexPartners() {
		return sexPartnerMap;
	}
	
	public void addSexPartner(GameCharacter partner, SexType sexType) {
		this.sexPartnerMap.computeIfAbsent(partner, k -> new HashMap<>());
		
		Map <SexType, Integer> sp = this.sexPartnerMap.get(partner);
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
		return getSexAsDomCount() + getSexAsSubCount() + getSexConsensualCount();
	}
	
	// Sex:
	public void incrementSexCount(SexType sexType) {
		sexCountMap.merge(sexType, 1, (a, b) -> a + b);
	}
	public void setSexCount(SexType sexType, int integer) {
		sexCountMap.put(sexType, integer);
	}
	public int getSexCount(SexType sexType) {
		if(sexCountMap.get(sexType)==null) {
			return 0;
		} else {
			return sexCountMap.get(sexType);
		}
	}

	// Cum:
	public void incrementCumCount(SexType sexType) {
		cumCountMap.merge(sexType, 1, (a, b) -> a + b);
	}
	public void setCumCount(SexType sexType, int integer) {
		cumCountMap.put(sexType, integer);
	}
	public int getCumCount(SexType sexType) {
		if(cumCountMap.get(sexType)==null) {
			return 0;
		} else {
			return cumCountMap.get(sexType);
		}
	}

	// Virginity:
	public void setVirginityLoss(SexType sexType, String description) {
		virginityLossMap.put(sexType, description);
	}
	
	public String getVirginityLoss(SexType sexType) {
		return virginityLossMap.get(sexType);
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
	
	public void incrementStamina(float increment) {
		setStamina(getStamina() + increment);
	}


	public float getArousal() {
		return getAttributeValue(Attribute.AROUSAL);
	}
	
	public void setArousal(float arousal) {
		if (arousal < 0) {
			setAttribute(Attribute.AROUSAL, 0);
			
		} else if (arousal > 100) {
			setAttribute(Attribute.AROUSAL, 100);
			
		} else {
			setAttribute(Attribute.AROUSAL, arousal);
		}

		updateAttributeListeners();
	}
	
	public void incrementArousal(float increment) {
		setArousal(getArousal() + increment);
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
			PregnancyPossibility pregPoss = new PregnancyPossibility(this, partner, pregnancyChance);
			
			potentialPartnersAsMother.add(pregPoss);
			partner.getPotentialPartnersAsFather().add(pregPoss);

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
				
				int numberOfChildren = 0;
				
				if(getVaginaType()==VaginaType.HUMAN) {
					numberOfChildren = partner.getPenisType().getRace().getNumberOfOffspringLow()
							+ Util.random.nextInt((partner.getPenisType().getRace().getNumberOfOffspringHigh() - partner.getPenisType().getRace().getNumberOfOffspringLow())+1);
					
				} else {
					numberOfChildren = getVaginaType().getRace().getNumberOfOffspringLow()
							+ Util.random.nextInt((getVaginaType().getRace().getNumberOfOffspringHigh() - getVaginaType().getRace().getNumberOfOffspringLow())+1);
				}
				
				if(partner.hasFetish(Fetish.FETISH_SEEDER)) {
					numberOfChildren*=2;
				}
				if(hasFetish(Fetish.FETISH_BROODMOTHER)) {
					numberOfChildren*=2;
				}
				
				List<NPC> offspring = new ArrayList<>();
				for (int i = 0; i < numberOfChildren; i++) { // Add children here:
					offspring.add(new NPCOffspring(this, partner));
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
			
			if(birthedLitter.getFather().isPlayer() || birthedLitter.getMother().isPlayer()) {
				for(NPC npc: birthedLitter.getOffspring()) {
					Main.game.getOffspring().add(npc);
					birthedLitter.setDayOfBirth(Main.game.getDayNumber());
					npc.setDayOfConception(birthedLitter.getDayOfConception());
					npc.setDayOfBirth(Main.game.getDayNumber());
				}
			}
			
			littersBirthed.add(birthedLitter);
			
			if(pregnantLitter.getFather()!=null) {
				pregnantLitter.getFather().getLittersFathered().add(birthedLitter);
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
	
	public List<PregnancyPossibility> getPotentialPartnersAsFather() {
		return potentialPartnersAsFather;
	}
	
	// Cummed in areas:

	public Map<OrificeType, Set<GameCharacter>> getCummedInAreaMap() {
		return cummedInAreaMap;
	}
	public void addCharactersCumToCummedInArea(OrificeType area, GameCharacter character) {
		cummedInAreaMap.get(area).add(character);
	}
	public void clearCummedInArea(OrificeType area) {
		cummedInAreaMap.get(area).clear();
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
	
	public PlaceInterface getStartingPlace() {
		return startingPlace;
	}
	
	public void setLocation(WorldType worldType, Vector2i location, boolean setAsHomeLocation) {
		setWorldLocation(worldType);
		setLocation(location);
		if(setAsHomeLocation) {
			setHomeLocation(worldType, location);
		}
	}
	
	public void setLocation(WorldType worldType, PlaceInterface placeType, boolean setAsHomeLocation) {
		setLocation(worldType, Main.game.getWorlds().get(worldType).getCell(placeType).getLocation(), setAsHomeLocation);
	}
	
	public void setHomeLocation(WorldType homeWorldLocation, PlaceInterface placeType) {
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

	public int getLevel() {
		return level;
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
	
	public void resetInventory(){
		this.inventory = new CharacterInventory(0);
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


	public boolean isCoverableAreaExposed(CoverableArea area) {
		return inventory.isCoverableAreaExposed(area);
	}
	
	public boolean isPenetrationTypeExposed(PenetrationType pt) {
		switch(pt) {
			case FINGER_PARTNER: case FINGER_PLAYER:
				return true;
			case PENIS_PARTNER: case PENIS_PLAYER:
				return isCoverableAreaExposed(CoverableArea.PENIS);
			case TAIL_PARTNER: case TAIL_PLAYER:
				return true;
			case TENTACLE_PARTNER: case TENTACLE_PLAYER:
				return true;
			case TONGUE_PARTNER: case TONGUE_PLAYER:
				return isCoverableAreaExposed(CoverableArea.MOUTH);
			default:
				return true;
		}
	}
	
	public boolean isOrificeTypeExposed(OrificeType ot) {
		switch(ot) {
			case ANUS_PARTNER: case ANUS_PLAYER:
				return isCoverableAreaExposed(CoverableArea.ANUS);
			case MOUTH_PARTNER: case MOUTH_PLAYER:
				return isCoverableAreaExposed(CoverableArea.MOUTH);
			case NIPPLE_PARTNER: case NIPPLE_PLAYER:
				return isCoverableAreaExposed(CoverableArea.NIPPLES);
			case URETHRA_PARTNER: case URETHRA_PLAYER:
				return isCoverableAreaExposed(CoverableArea.PENIS);
			case VAGINA_PARTNER: case VAGINA_PLAYER:
				return isCoverableAreaExposed(CoverableArea.VAGINA);
			default:
				return true;
		}
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
						// Assume male, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Your [pc.penis] is concealed, so, due to your masculine appearance, everyone assumes that you're "
										+UtilText.generateSingularDeterminer(Gender.M_P_MALE.getName())+" "+Gender.M_P_MALE.getName()+" on first glance."
								:UtilText.parse(this,
										"Due to [npc.her] masculine appearance, everyone assumes that [npc.she]'s "
											+UtilText.generateSingularDeterminer(Gender.M_P_MALE.getName())+" "+Gender.M_P_MALE.getName()+" on first glance."),
								Gender.M_P_MALE);
						
					} else if(hasVagina()) {
						// Correctly assume male:
						return new GenderAppearance(
								isPlayer()
								?"Your masculine appearance leads everyone to correctly assume that you're "
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
											+UtilText.generateSingularDeterminer(Gender.M_V_CUNTBOY.getName())+" "+Gender.M_V_CUNTBOY.getName()+"."
									:UtilText.parse(this,
											"Due to [npc.her] masculine appearance, everyone assumes that [npc.she]'s "
												+UtilText.generateSingularDeterminer(Gender.M_V_CUNTBOY.getName())+" "+Gender.M_V_CUNTBOY.getName()+"."),
									Gender.M_V_CUNTBOY);
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
			if(!body.getBodyCoveringTypesDiscovered().contains(bp.getType().getBodyCoveringType())) {
				if(bp.getType().getBodyCoveringType()!=null) {
					body.getBodyCoveringTypesDiscovered().add(bp.getType().getBodyCoveringType());
					
					if(displayColourDiscovered) {
						if(isPlayer()) {
							postTFSB.append(
									"<b>You have discovered that your natural</b>"
									+" <b style='color:"+bp.getType().getBodyCoveringType().getRace().getColour().toWebHexString()+";'>"+bp.getType().getBodyCoveringType().getRace().getName()+"'s</b>"
											+ " <b>"+bp.getType().getBodyCoveringType().getName(this)+" colour is "
									+getCovering(bp.getType().getBodyCoveringType()).getColourDescriptor(true, false)+"!</b>");
						} else {
							postTFSB.append(UtilText.parse(this,
									"<b>[npc.Name] has discovered that [npc.her] natural</b>"
									+" <b style='color:"+bp.getType().getBodyCoveringType().getRace().getColour().toWebHexString()+";'>"+bp.getType().getBodyCoveringType().getRace().getName()+"'s</b>"
											+ " <b>"+bp.getType().getBodyCoveringType().getName(this)+" colour is</b> "
											+getCovering(bp.getType().getBodyCoveringType()).getColourDescriptor(true, false)+"!</b>"));
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
			case HARPY:
				return BodyCoveringType.BODY_HAIR_HARPY;
			case HORSE_MORPH:
				return BodyCoveringType.BODY_HAIR_HORSE_HAIR;
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
						? "<p>" + "The world around you seems slightly further away than it used to be, but after a moment you realise that you've just <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>grown taller</b>."
								+ "</br>" + "You are now <b>" + getHeightValue() + "cm</b> tall." + "</p>"
						: UtilText.genderParsing(this,
								"<p>" + "<She> sways from side to side a little, <her> balance suddenly thrown off by the fact that she's just <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>grown taller</b>." + "</p>");
		} else {
			if (body.setHeight(height))
				return isPlayer()
						? "<p>" + "The world around you suddenly seems slightly closer than it used to be, but after a moment you realise that you've just <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>become shorter</b>."
								+ "</br>" + "You are now <b>" + getHeightValue() + "cm</b> tall." + "</p>"
						: UtilText.genderParsing(this, "<p>" + "<She> shrinks down, <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>becoming noticeably shorter</b>." + "</p>");
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
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> navel is now <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>pierced</b>."
						+ "</p>");
			}
			
		} else if (isPiercedNavel() && !pierced) {
			body.setPiercedStomach(false);
			if(isPlayer()) {
				return "<p>"
							+ "Your navel is <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>no longer pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> navel is <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>no longer pierced</b>."
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
	public String setAssCapacity(float capacity) {
		return body.getAss().getAnus().getOrificeAnus().setCapacity(this, capacity);
	}
	public String incrementAssCapacity(float increment) {
		return setAssCapacity(getAssRawCapacityValue() + increment);
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
	public String setNippleCapacity(float capacity) {
		return body.getBreast().getNipples().getOrificeNipples().setCapacity(this, capacity);
	}
	public String incrementNippleCapacity(float increment) {
		return setNippleCapacity(getNippleRawCapacityValue() + increment);
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
		if(isFeminine()) {
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
	public String setFaceCapacity(float capacity) {
		return body.getFace().getMouth().getOrificeMouth().setCapacity(this, capacity);
	}
	public String incrementFaceCapacity(float increment) {
		return setFaceCapacity(getFaceRawCapacityValue() + increment);
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
	public String setPenisCapacity(float capacity) {
		return body.getPenis().getOrificeUrethra().setCapacity(this, capacity);
	}
	public String incrementPenisCapacity(float increment) {
		return setPenisCapacity(getPenisRawCapacityValue() + increment);
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
	public String setSecondPenisCapacity(float capacity) {
		return body.getSecondPenis().getOrificeUrethra().setCapacity(this, capacity);
	}
	public String incrementSecondPenisCapacity(float increment) {
		return setSecondPenisCapacity(getSecondPenisRawCapacityValue() + increment);
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
	public String setVaginaCapacity(float capacity) {
		return body.getVagina().getOrificeVagina().setCapacity(this, capacity);
	}
	public String incrementVaginaCapacity(float increment) {
		return setVaginaCapacity(getVaginaRawCapacityValue() + increment);
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
