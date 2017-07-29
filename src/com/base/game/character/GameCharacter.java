package com.base.game.character;

import java.io.Serializable;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.base.game.character.attributes.Attribute;
import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.Arm;
import com.base.game.character.body.Ass;
import com.base.game.character.body.Body;
import com.base.game.character.body.BodyPartInterface;
import com.base.game.character.body.Breast;
import com.base.game.character.body.Ear;
import com.base.game.character.body.Eye;
import com.base.game.character.body.Face;
import com.base.game.character.body.Hair;
import com.base.game.character.body.Horn;
import com.base.game.character.body.Leg;
import com.base.game.character.body.Penis;
import com.base.game.character.body.Skin;
import com.base.game.character.body.Tail;
import com.base.game.character.body.Vagina;
import com.base.game.character.body.Wing;
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
import com.base.game.character.body.types.TongueType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.types.WingType;
import com.base.game.character.body.valueEnums.AssSize;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.ClitorisSize;
import com.base.game.character.body.valueEnums.CumProduction;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.body.valueEnums.HairLength;
import com.base.game.character.body.valueEnums.HipSize;
import com.base.game.character.body.valueEnums.Lactation;
import com.base.game.character.body.valueEnums.MakeupLevel;
import com.base.game.character.body.valueEnums.OrificeElasticity;
import com.base.game.character.body.valueEnums.PenisSize;
import com.base.game.character.body.valueEnums.TesticleSize;
import com.base.game.character.body.valueEnums.Wetness;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.Perk;
import com.base.game.character.effects.PerkInterface;
import com.base.game.character.effects.StatusEffect;
import com.base.game.character.gender.Gender;
import com.base.game.character.npc.NPC;
import com.base.game.character.npc.NPCOffspring;
import com.base.game.character.race.Race;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.combat.Combat;
import com.base.game.combat.SpecialAttack;
import com.base.game.combat.Spell;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.CharacterInventory;
import com.base.game.inventory.InventorySlot;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.ClothingSet;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.inventory.clothing.DisplacementType;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.game.inventory.item.AbstractItem;
import com.base.game.inventory.item.ItemType;
import com.base.game.inventory.weapon.AbstractWeapon;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.PregnancyDescriptor;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Vector2i;
import com.base.world.WorldType;
import com.base.world.places.PlaceInterface;

/**
 * The class for all the game's characters. I think this is the biggest class in the game.
 * 
 * @since 0.1.0
 * @version 0.1.82
 * @author Innoxia
 */
public class GameCharacter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** Calculation description as used in getAttributeValue() */
	public static final String HEALTH_CALCULATION = "Level*10 + STR + Bonus HP";
	/** Calculation description as used in getAttributeValue() */
	public static final String MANA_CALCULATION = "Level*10 + INT + Bonus WP";
	/** Calculation description as used in getAttributeValue() */
	public static final String STAMINA_CALCULATION = "Level*10 + FIT + Bonus ST";

	// Core variables:
	protected NameTriplet nameTriplet;
	protected boolean playerKnowsName;
	protected String playerPetName = "";
	protected String description;
	protected int level;

	private int experience, levelUpPoints, perkPoints;
	
	protected WorldType worldLocation;
	protected PlaceInterface startingPlace;
	protected Vector2i location;
	
	protected Body body;
	protected CharacterInventory inventory;

	protected History history;
	
	protected SexualOrientation sexualOrientation;
	
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
	protected boolean wearingCondom;
	protected Map<OrificeType, Set<GameCharacter>> cummedInAreaMap;
	
	// Pregnancy:
	protected long timeProgressedToFinalPregnancyStage;
	protected List<PregnancyPossibility> potentialPartnersAsMother, potentialPartnersAsFather;
	protected Litter pregnantLitter;
	protected List<Litter> littersBirthed, littersFathered;

	// Clothes:
	protected int nakedSlots;
	
	// Stats:
	protected CharacterStats stats;

	// Misc.:
	protected static List<CharacterChangeEventListener> playerAttributeChangeEventListeners = new ArrayList<>();
	protected static List<CharacterChangeEventListener> NPCAttributeChangeEventListeners = new ArrayList<>();

	protected static List<CharacterChangeEventListener> NPCLocationChangeEventListeners = new ArrayList<>();
	protected static List<CharacterChangeEventListener> playerLocationChangeEventListeners = new ArrayList<>();

	protected static List<CharacterChangeEventListener> NPCInventoryChangeEventListeners = new ArrayList<>();
	protected static List<CharacterChangeEventListener> playerInventoryChangeEventListeners = new ArrayList<>();

	protected GameCharacter(NameTriplet nameTriplet, String description, int level, Gender startingGender, RacialBody startingRace, RaceStage stage, CharacterInventory inventory, WorldType worldLocation, PlaceInterface startingPlace) {

		this.nameTriplet = nameTriplet;
		playerKnowsName = true;
		this.description = description;
		this.level = level;
		
		this.worldLocation = worldLocation;
		this.startingPlace = startingPlace;
		location = new Vector2i(0, 0);
		
		history = History.NEUTRAL;
		
		sexualOrientation = startingRace.getSexualOrientation(startingGender);
		
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
		stats = new CharacterStats();
		
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
	
	public String speech(String text) {
		return UtilText.parseSpeech(text, this);
	}
	public String thought(String text) {
		return UtilText.parseThought(text, this);
	}
	public String getName(String determiner) {
		if (Character.isUpperCase(getName().charAt(0)) || determiner=="") {
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

	public String getSpeechColour() {
		if (Femininity.valueOf(getFemininity()) == Femininity.MASCULINE || Femininity.valueOf(getFemininity()) == Femininity.MASCULINE_STRONG) {
			if(isPlayer())
				return Colour.MASCULINE.toWebHexString();
			else
				return Colour.MASCULINE_NPC.toWebHexString();

		} else if (Femininity.valueOf(getFemininity()) == Femininity.ANDROGYNOUS){
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

	// This is messy ;_;
	public void setBody(Gender startingGender, RacialBody startingBodyType, RaceStage stage) {
		body = new Body.BodyBuilder(
				new Arm(stage.isArmFurry()?startingBodyType.getArmType():ArmType.HUMAN),
				new Ass(stage.isAssFurry()?startingBodyType.getAssType():AssType.HUMAN,
						(startingGender.isFeminine() ? startingBodyType.getFemaleAssSize() : startingBodyType.getMaleAssSize()),
						startingBodyType.getAnusWetness(),
						startingBodyType.getAnusCapacity(),
						startingBodyType.getAnusPlasticity(),
						true),
				new Breast(stage.isBreastFurry()?startingBodyType.getBreastType():BreastType.HUMAN,
						(startingGender.isFeminine() ? startingBodyType.getFemaleBreastSize() : startingBodyType.getMaleBreastSize()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleLactationRate() : startingBodyType.getMaleLactationRate()),
						((stage.isSkinFurry() && Main.getProperties().multiBreasts==1) || (stage.isBreastFurry() && Main.getProperties().multiBreasts==2)
								?(startingGender.isFeminine() ? startingBodyType.getBreastCountFemale() : startingBodyType.getBreastCountMale())
								:1),
						false,
						(startingGender.isFeminine() ? startingBodyType.getFemaleBreastCapacity() : startingBodyType.getMaleBreastCapacity()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleBreastPlasticity() : startingBodyType.getMaleBreastPlasticity()), 
						true),
				new Face(stage.isFaceFurry()?startingBodyType.getFaceType():FaceType.HUMAN,
						0),
				new Eye(stage.isEyeFurry()?startingBodyType.getEyeType():BodyCoveringType.EYE_HUMAN),
				new Ear(stage.isEarFurry()?startingBodyType.getEarType():EarType.HUMAN),
				new Hair(stage.isHairFurry()?startingBodyType.getHairType():BodyCoveringType.HAIR_HUMAN,
						(startingGender.isFeminine() ? startingBodyType.getFemaleHairLength() : startingBodyType.getMaleHairLength())),
				new Leg(stage.isLegFurry()?startingBodyType.getLegType():LegType.HUMAN),
				new Skin(stage.isSkinFurry()?startingBodyType.getSkinType():BodyCoveringType.HUMAN),
				(startingGender.isFeminine() ? startingBodyType.getFemaleHeight() : startingBodyType.getMaleHeight()),
				(startingGender.isFeminine() ? startingBodyType.getFemaleFemininity() : startingBodyType.getMaleFemininity()))
						.vagina((startingGender==Gender.FUTANARI || startingGender==Gender.HERMAPHRODITE || startingGender==Gender.CUNT_BOY || startingGender==Gender.FEMALE)
								? new Vagina(stage.isVaginaFurry()?startingBodyType.getVaginaType():VaginaType.HUMAN,
										startingBodyType.getClitSize(),
										startingBodyType.getVaginaWetness(),
										startingBodyType.getVaginaCapacity(),
										startingBodyType.getVaginaPlasticity(),
										true)
								: new Vagina(VaginaType.NONE, 0, 0, 0, 0, true))
						.penis((startingGender==Gender.FUTANARI || startingGender==Gender.HERMAPHRODITE || startingGender==Gender.SHEMALE || startingGender==Gender.MALE)
								? new Penis(stage.isPenisFurry()?startingBodyType.getPenisType():PenisType.HUMAN,
									(startingBodyType.getPenisCount() == 2 ? startingBodyType.getPenisType() : PenisType.NONE),
									startingBodyType.getPenisSize(),
									startingBodyType.getTesticleSize(),
									startingBodyType.getCumProduction(),
									startingBodyType.getTesticleQuantity())
								: new Penis(PenisType.NONE, PenisType.NONE, 0, 0, 0, 0))
						.horn(startingBodyType.getHornTypeFemale() == HornType.NONE ? new Horn(HornType.NONE) : new Horn(!startingGender.isFeminine()
								? (stage.isHornFurry()?startingBodyType.getHornTypeMale():HornType.NONE)
								: (stage.isHornFurry()?startingBodyType.getHornTypeFemale():HornType.NONE)))
						.tail(new Tail(stage.isTailFurry()?startingBodyType.getTailType():TailType.NONE))
						.wing(new Wing(stage.isWingFurry()?startingBodyType.getWingType():WingType.NONE))
						.build();

		postTransformationCalculation();
	}
	
	public void setBodyCoveringForXMLImport(BodyCoveringType bct, Colour c) {
		body.getBodyCoveringTypeColours().put(bct, c);
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
			if(getFemininity()>Femininity.ANDROGYNOUS.getMaximumFemininity()) {
				return nameTriplet.getFeminine();
				
			} else if(getFemininity()>=Femininity.ANDROGYNOUS.getMinimumFemininity()) {
				return nameTriplet.getAndrogynous();
				
			} else {
				return nameTriplet.getMasculine();
			}
		}
	}

	public void setName(NameTriplet nameTriplet) {
		this.nameTriplet = nameTriplet;
	}

	public NameTriplet getNameTriplet() {
		return nameTriplet;
	}
	
	public String getPlayerPetName() {
		if(playerPetName=="") {
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

	public SexualOrientation getSexualOrientation() {
		return sexualOrientation;
	}

	public void setSexualOrientation(SexualOrientation sexualOrientation) {
		this.sexualOrientation = sexualOrientation;
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
		return incrementAttribute(att, value - attributes.get(att));
	}

	public String incrementAttribute(Attribute att, float increment) {
		float value = attributes.get(att) + increment;

		// For handling health, mana and stamina changes as a result of an
		// attribute being changed:
		float healthPercetage = getHealthPercentage();
		float manaPercetage = getManaPercentage();
		float staminaPercetage = getStaminaPercentage();

		// Core attribute values are bound between 0 and 100
		if (att == Attribute.STRENGTH || att == Attribute.INTELLIGENCE || att == Attribute.FITNESS || att == Attribute.CORRUPTION) {
			if (value < 0)
				value = 0;
			if (value > 100)
				value = 100;
		}
		attributes.put(att, value);

		// Increment health, mana and stamina based on the change:
		setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM) * healthPercetage);
		setMana(getAttributeValue(Attribute.MANA_MAXIMUM) * manaPercetage);
		setStamina(getAttributeValue(Attribute.STAMINA_MAXIMUM) * staminaPercetage);

		updateAttributeListeners();

		return att.getAttributeChangeText(this, increment);
	}


	public void incrementBonusAttribute(Attribute att, float increment) {
		float value = bonusAttributes.get(att) + increment;

		float healthPercetage = getHealthPercentage();
		float manaPercetage = getManaPercentage();
		float staminaPercetage = getStaminaPercentage();

		// Bonus attributes can be literally anything (well, maybe not past
		// Integer.MAX_VALUE or Integer.MIN_VALUE, but that'll never happen,
		// right?)
		bonusAttributes.put(att, value);

		setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM) * healthPercetage);
		setMana(getAttributeValue(Attribute.MANA_MAXIMUM) * manaPercetage);
		setStamina(getAttributeValue(Attribute.STAMINA_MAXIMUM) * staminaPercetage);

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
		
		removeStatusEffect(StatusEffect.POTION_EFFECTS);
		
		setPotionAttributes(savedPotionEffects);
		
		if(potionAttributes.containsKey(att))
			setPotionAttribute(att, potionAttributes.get(att)+value);
		else
			setPotionAttribute(att, value);
		
		if(potionAttributes.get(att)==0)
			potionAttributes.remove(att);
		
		addStatusEffect(StatusEffect.POTION_EFFECTS, 60);
		
		if(isPlayer()) {
			if(potionAttributes.get(att)<0)
				return "You now have [style.boldBad("+potionAttributes.get(att)+")] <b style='color:"+att.getColour()+";'>"+att.getName()+"</b> for as long as you can maintain your potion effects!";
			else
				return "You now have [style.boldGood(+"+potionAttributes.get(att)+")] <b style='color:"+att.getColour()+";'>"+att.getName()+"</b> for as long as you can maintain your potion effects!";
		} else {
			if(potionAttributes.get(att)<0)
				return UtilText.parse(this,
						"[npc.Name] now has [style.boldBad("+potionAttributes.get(att)+")] <b style='color:"+att.getColour()+";'>"+att.getName()+"</b> for as long as [npc.she] can maintain [npc.her] potion effects!");
			else
				return UtilText.parse(this,
						"[npc.Name] now has [style.boldGood(+"+potionAttributes.get(att)+")] <b style='color:"+att.getColour()+";'>"+att.getName()+"</b> for as long as [npc.she] can maintain [npc.her] potion effects!");
		}
	}
	
	public void clearPotionAttributes() {
		potionAttributes.clear();
	}
	

	// Perks:

	private List<Perk> tempPerkList;
	private List<Fetish> tempFetishList;

	/**The returned list is ordered by rendering priority.*/
	public List<Perk> getPerks() {
		tempPerkList = new ArrayList<>(perks);
		tempPerkList.sort((PerkInterface o1, PerkInterface o2) -> ((Integer) o2.getRenderingPriority()).compareTo(((Integer) o1.getRenderingPriority())));
		return tempPerkList;
	}

	/**The returned list is ordered by rendering priority.*/
	public List<Fetish> getFetishes() {
		tempFetishList = new ArrayList<>(fetishes);
		tempFetishList.sort((PerkInterface o1, PerkInterface o2) -> ((Integer) o2.getRenderingPriority()).compareTo(((Integer) o1.getRenderingPriority())));
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
		if (f == null)
			return true;
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
		List<StatusEffect> tempListStatusEffects = new ArrayList<StatusEffect>(statusEffects.keySet());
		tempListStatusEffects.sort((StatusEffect o1, StatusEffect o2) -> ((Integer) o2.getRenderingPriority()).compareTo(((Integer) o1.getRenderingPriority())));
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
	
	public CharacterStats getStats() {
		return stats;
	}
	
	
	
	// Combat:

	private List<SpecialAttack> tempListSpecialAttacks;

	/**
	 * The returned list is ordered by rendering priority.
	 */

	public List<SpecialAttack> getSpecialAttacks() {
		tempListSpecialAttacks = new ArrayList<SpecialAttack>(specialAttacks);
		tempListSpecialAttacks.sort((SpecialAttack o1, SpecialAttack o2) -> ((Integer) o2.getRenderingPriority()).compareTo(((Integer) o1.getRenderingPriority())));
		return tempListSpecialAttacks;
	}


	public void calculateSpecialAttacks() {
		specialAttacks.clear();

		for (SpecialAttack sAttack : SpecialAttack.values())
			if (sAttack.isConditionsMet(this))
				specialAttacks.add(sAttack);

		updateAttributeListeners();
	}

	private List<Spell> tempListSpells;

	/**
	 * Includes spells from weapons.
	 */
	public List<Spell> getSpells() {
		tempListSpells = new ArrayList<Spell>(spells);
		
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
							+ "Due to your <b style='color:" + Colour.GENERIC_ARCANE + ";'>masochist fetish</b>, incoming damage is reduced by 40%, but in turn, you take"
									+ " <b>"+-(manaLoss)+"</b> <b style='color:" + Colour.DAMAGE_TYPE_MANA + ";'>willpower damage</b> as you struggle to control your arousal!"
							+ "</p>");
				} else {
					return (UtilText.genderParsing(this,
							"<p>"
								+ "Due to <her> <b style='color:" + Colour.GENERIC_ARCANE + ";'>masochist fetish</b>, incoming damage is reduced by 40%, but in turn, <she> takes"
								+ " <b>"+-(manaLoss)+"</b> <b style='color:" + Colour.DAMAGE_TYPE_MANA + ";'>willpower damage</b> as <she> struggles to control <her> arousal!"
							+ "</p>"));
				}
			// Sadist:
			} else if ((isPlayer()?Combat.getOpponent().hasFetish(Fetish.FETISH_SADIST):Main.game.getPlayer().hasFetish(Fetish.FETISH_SADIST)) && increment < 0) {
				float manaLoss = (Math.round((increment*0.1f)*10))/10f;
				
				if (isPlayer()) {
					Combat.getOpponent().incrementMana(manaLoss);
					setHealth(getHealth() + increment);
					
					return (UtilText.parse(this,
							"<p>"
								+ "Due to [npc.her] <b style='color:" + Colour.GENERIC_ARCANE + ";'>sadist fetish</b>, [npc.she] suffers 10% of dealt damage as willpower damage, causing [npc.herHim] to take"
								+ " <b>"+-(manaLoss)+"</b> <b style='color:" + Colour.DAMAGE_TYPE_MANA + ";'>willpower damage</b> as [npc.she] struggles to control [npc.her] arousal!"
							+ "</p>"));
					
				} else {
					Main.game.getPlayer().incrementMana(manaLoss);
					setHealth(getHealth() + increment);
					
					return ("<p>"
							+ "Due to your <b style='color:" + Colour.GENERIC_ARCANE + ";'>sadist fetish</b>, you suffer 10% of dealt damage as willpower damage, causing you to take"
									+ " <b>"+-(manaLoss)+"</b> <b style='color:" + Colour.DAMAGE_TYPE_MANA + ";'>willpower damage</b> as you struggle to control your arousal!"
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
		return wearingCondom;
	}

	public void setWearingCondom(boolean wearingCondom) {
		this.wearingCondom = wearingCondom;
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
		
		if(partner.getAttributeValue(Attribute.VIRILITY)==0 || getAttributeValue(Attribute.FERTILITY)==0) {
			pregnancyChance = 0;
			
		} else {
			pregnancyChance = 0;
			pregnancyChance += (partner.getAttributeValue(Attribute.VIRILITY)/100f) * partner.getPenisCumProduction().getPregnancyModifer();
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
			if(partner!=null) {
				partner.getPotentialPartnersAsFather().add(pregPoss);
			}
			
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
			
			for(NPC npc: birthedLitter.getOffspring()) {
				Main.game.getOffspring().add(npc);
				npc.setDayOfConception(birthedLitter.getDayOfConception());
				npc.setDayOfBirth(Main.game.getDayNumber());
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

	public WorldType getWorldLocation() {
		return worldLocation;
	}

	public void setWorldLocation(WorldType worldLocation) {
		this.worldLocation = worldLocation;
	}
	
	public PlaceInterface getLocationPlace() {
		return Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getPlace();
	}
	
	public PlaceInterface getStartingPlace() {
		return startingPlace;
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
							+ "<span style='color:" + Colour.GENERIC_TERRIBLE + ";'>The " + item.getName() + " will be lost if you leave this location!</span>"
						+ "</p>";
			} else {
				return UtilText.parse(this,
					"<p style='text-align:center;'>"
						+ "[npc.Name] drops [npc.her] " + item.getName() + " on the floor."
						+ "</br>"
						+ "<span style='color:" + Colour.GENERIC_TERRIBLE + ";'>The " + item.getName() + " will be lost if you leave this location!</span>"
					+ "</p>");
			}
			
		} else {
			if(isPlayer()) {
				return "<p style='text-align:center;'>"
							+ "You drop your " + item.getName() + " on the floor."
							+ "</br>"
							+ "<span style='color:" + Colour.GENERIC_EXCELLENT + ";'>The " + item.getName() + " will be stored safely in this location!</span>"
						+ "</p>";
			} else {
				return UtilText.parse(this,
					"<p style='text-align:center;'>"
						+ "[npc.Name] drops [npc.her] " + item.getName() + " on the floor."
						+ "</br>"
						+ "<span style='color:" + Colour.GENERIC_TERRIBLE + ";'>The " + item.getName() + " will be stored safely in this location!</span>"
					+ "</p>");
			}
		}
	}

	public String addedItemToInventoryText(AbstractCoreItem item) {
		if(item instanceof AbstractItem) {
			return "<b style='color:" + Colour.GENERIC_GOOD + ";'>Item added to inventory:</b> <b>" + ((AbstractItem)item).getDisplayName(true) + "</b>";
		}
		if(item instanceof AbstractClothing) {
			return "<b style='color:" + Colour.GENERIC_GOOD + ";'>Clothing added to inventory:</b> <b>" + ((AbstractClothing)item).getDisplayName(true) + "</b>";
		}
		if(item instanceof AbstractWeapon) {
			return "<b style='color:" + Colour.GENERIC_GOOD + ";'>Weapon added to inventory:</b> <b>" + ((AbstractWeapon)item).getDisplayName(true) + "</b>";
		}
		return "<b style='color:" + Colour.GENERIC_GOOD + ";'>Item added to inventory:</b> <b>" + item.getName() + "</b>";
	}

	public String addedItemToInventoryText(ItemType item) {
		return "<p style='text-align:center;'>" + "<span style='color:" + Colour.GENERIC_GOOD + ";'>You add the " + item.getName(false) + " to your inventory.</span>" + "</p>";
	}

	public String inventoryFullText() {
		return "<p style='text-align:center;'>" + "<b style='color:" + Colour.GENERIC_BAD + ";'>Your inventory is full!</b>" + "</p>";
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
				Main.game.getActiveWorld().getCell(location).getInventory().removeItem(item);
				return addedItemToInventoryText(item);
			} else {
				return inventoryFullText() + droppedItemText(item);
			}
			
		} else {
			if (inventory.addItem(item)) {
				updateInventoryListeners();
				return addedItemToInventoryText(item);
			} else {
				Main.game.getActiveWorld().getCell(location).getInventory().addItem(item);
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
	public boolean hasItemType(ItemType item) {
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
		
		if(ItemType.availableItems.contains(item.getItemType()) && isPlayer()) {
			if(Main.game.getPlayer().getItemsDiscovered().add(item.getItemType())) {
				Main.game.getPlayer().setNewItemDiscovered(true);
				Main.game.getTextEndStringBuilder().append(
						"<p style='text-align:center;'>"
							+ "<b style='color:"+Colour.GENERIC_EXCELLENT+";'>New entry in your phone's encyclopedia:</b>"
							+ "</br>"
							+ "<b>Item:</b> <b style='color:"+item.getRarity().getColour()+";'>"+Util.capitaliseSentence(item.getName())+"</b>"
						+ "</p>");
			}
		}
		
		if (item.getItemType().isConsumedOnUse()) {
			if(removingFromFloor)
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().removeItem(item);
			else
				removeItem(item);
		}
		
		if(onlyReturnEffects) {
			return item.applyEffect(this, target);
		} else {
			return item.getUseDescription(this, target) + item.applyEffect(this, target);
		}
		
	}

	
	// -------------------- Weapons -------------------- //
	
	public AbstractWeapon getWeapon(int index) {
		return inventory.getWeapon(index);
	}
	
	public String addWeapon(AbstractWeapon weapon, boolean removingFromFloor) {
		if (inventory.addWeapon(weapon)) {
			if (removingFromFloor)
				Main.game.getActiveWorld().getCell(location).getInventory().removeWeapon(weapon);
			return "<p style='text-align:center;'>" + addedItemToInventoryText(weapon)+"</p>";
			
		} else {
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
	
	/**
	 * @return Description of equipping this weapon.
	 */
	public String equipMainWeapon(AbstractWeapon weapon, boolean removingFromFloor) {
		if (weapon == null)
			throw new NullPointerException("null weapon was passed.");

		if (hasWeapon(weapon))
			removeWeapon(weapon);
		
		String s = "";
		
		if (getMainWeapon() != null) {
			s += addWeapon(getMainWeapon(), false);

			// Revert old melee weapon's attribute bonuses:
			if (getMainWeapon().getAttributeModifiers() != null)
				for (Entry<Attribute, Integer> e : getMainWeapon().getAttributeModifiers().entrySet())
					incrementBonusAttribute(e.getKey(), -e.getValue());

		}

		s += weapon.onEquip(this);
		// Apply its attribute bonuses:
		if (weapon.getAttributeModifiers() != null)
			for (Entry<Attribute, Integer> e : weapon.getAttributeModifiers().entrySet())
				incrementBonusAttribute(e.getKey(), e.getValue());
				
		inventory.equipMainWeapon(weapon);
		updateInventoryListeners();
		
		if (removingFromFloor)
			Main.game.getActiveWorld().getCell(location).getInventory().removeWeapon(weapon);
		
		return s;
	}
	
	public String unequipMainWeapon(boolean dropToFloor) {
		if (getMainWeapon() != null) {
			// If weapon is unequipped, revert its attribute bonuses:
			if (getMainWeapon().getAttributeModifiers() != null)
				for (Entry<Attribute, Integer> e : getMainWeapon().getAttributeModifiers().entrySet())
					incrementBonusAttribute(e.getKey(), -e.getValue());

			String s;
			if (isInventoryFull() || dropToFloor) {
				Main.game.getActiveWorld().getCell(location).getInventory().addWeapon(getMainWeapon());
				s = getMainWeapon().getWeaponType().unequipText(this) + (isInventoryFull() && !dropToFloor ? inventoryFullText() : "") + droppedItemText(getMainWeapon());
			} else {
				addWeapon(getMainWeapon(), false);
				s = getMainWeapon().getWeaponType().unequipText(this) + "<p style='text-align:center;'>" + addedItemToInventoryText(getMainWeapon())+"</p>";
			}
			inventory.unequipMainWEapon();
			updateInventoryListeners();
			
			return s;
		} else
			return "";
	}
	
	public AbstractWeapon getOffhandWeapon() {
		return inventory.getOffhandWeapon();
	}
	
	/**
	 * @return Description of equipping this weapon.
	 */
	public String equipOffhandWeapon(AbstractWeapon weapon, boolean removingFromFloor) {
		if (weapon == null)
			throw new NullPointerException("null weapon was passed.");

		if (hasWeapon(weapon))
			removeWeapon(weapon);

		String s = "";
		
		if (getOffhandWeapon() != null) {
			s += addWeapon(getOffhandWeapon(), false);

			// Revert old weapon's attribute bonuses:
			if (getOffhandWeapon().getAttributeModifiers() != null)
				for (Entry<Attribute, Integer> e : getOffhandWeapon().getAttributeModifiers().entrySet())
					incrementBonusAttribute(e.getKey(), -e.getValue());

		}
		
		s += weapon.onEquip(this);
		// Apply its attribute bonuses:
		if (weapon.getAttributeModifiers() != null)
			for (Entry<Attribute, Integer> e : weapon.getAttributeModifiers().entrySet())
				incrementBonusAttribute(e.getKey(), e.getValue());
		
		inventory.equipOffhandWeapon(weapon);
		updateInventoryListeners();
		if (removingFromFloor)
			Main.game.getActiveWorld().getCell(location).getInventory().removeWeapon(weapon);
		
		return s;
	}
	
	public String unequipOffhandWeapon(boolean dropToFloor) {
		if (getOffhandWeapon() != null) {
			// If weapon is unequipped, revert it's attribute bonuses:
			if (getOffhandWeapon().getAttributeModifiers() != null)
				for (Entry<Attribute, Integer> e : getOffhandWeapon().getAttributeModifiers().entrySet())
					incrementBonusAttribute(e.getKey(), -e.getValue());
			String s;
			if (isInventoryFull() || dropToFloor) {
				Main.game.getActiveWorld().getCell(location).getInventory().addWeapon(getOffhandWeapon());
				s = getOffhandWeapon().getWeaponType().unequipText(this) + (isInventoryFull() && !dropToFloor ? inventoryFullText() : "") + droppedItemText(getOffhandWeapon());
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
				Main.game.getActiveWorld().getCell(location).getInventory().removeClothing(clothing);
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

	public String equipClothingFromInventory(AbstractClothing newClothing, boolean automaticClothingManagement, GameCharacter characterClothingEquipper) {
		boolean wasAbleToEquip = inventory.isAbleToEquip(newClothing, true, automaticClothingManagement, this, characterClothingEquipper);

		// If this item was able to be equipped, and it was equipped, apply it's
		// attribute bonuses:
		if (wasAbleToEquip) {
			incrementBonusAttribute(Attribute.RESISTANCE_PHYSICAL, newClothing.getClothingType().getPhysicalResistance());
			for (Entry<Attribute, Integer> e : newClothing.getAttributeModifiers().entrySet()) {
				incrementBonusAttribute(e.getKey(), e.getValue());
			}
			
			removeClothing(newClothing);

			newClothing.setEnchantmentKnown(true);

			updateInventoryListeners();

			if (isPlayer()) {
				if (Main.game.getPlayer().getClothingDiscovered().add(newClothing.getClothingType())) {
					Main.game.getPlayer().setNewClothingDiscovered(true);
					Main.game.getTextEndStringBuilder().append(
							"<p style='text-align:center;'>"
								+ "<b style='color:"+Colour.GENERIC_EXCELLENT+";'>New entry in your phone's encyclopedia:</b>"
								+ "</br>"
								+ "<b>Clothing:</b> <b style='color:"+newClothing.getRarity().getColour()+";'>"+Util.capitaliseSentence(newClothing.getName())+"</b>"
							+ "</p>");
				}
			}
		}

		return inventory.getEquipDescription();
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

			if (isPlayer()) {
				if (Main.game.getPlayer().getClothingDiscovered().add(newClothing.getClothingType())) {
					Main.game.getPlayer().setNewClothingDiscovered(true);
					Main.game.getTextEndStringBuilder().append(
							"<p style='text-align:center;'>"
								+ "<b style='color:"+Colour.GENERIC_EXCELLENT+";'>New entry in your phone's encyclopedia:</b>"
								+ "</br>"
								+ "<b>Clothing:</b> <b style='color:"+newClothing.getRarity().getColour()+";'>"+Util.capitaliseSentence(newClothing.getName())+"</b>"
							+ "</p>");
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

			Main.game.getActiveWorld().getCell(location).getInventory().removeClothing(newClothing);

			newClothing.setEnchantmentKnown(true);

			updateInventoryListeners();

			if (isPlayer()) {
				if (Main.game.getPlayer().getClothingDiscovered().add(newClothing.getClothingType())) {
					Main.game.getPlayer().setNewClothingDiscovered(true);
					Main.game.getTextEndStringBuilder().append(
							"<p style='text-align:center;'>"
								+ "<b style='color:"+Colour.GENERIC_EXCELLENT+";'>New entry in your phone's encyclopedia:</b>"
								+ "</br>"
								+ "<b>Clothing:</b> <b style='color:"+newClothing.getRarity().getColour()+";'>"+Util.capitaliseSentence(newClothing.getName())+"</b>"
							+ "</p>");
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

			// Place the clothing into inventory:
			if (!isInventoryFull())
				addClothing(clothing, false);
			else
				Main.game.getActiveWorld().getCell(location).getInventory().addClothing(clothing);

			updateInventoryListeners();
			
			return "<p style='text-align:center;'>"
				+inventory.getEquipDescription()
				+"</br>"
				+ (isInventoryFull()
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
			Main.game.getActiveWorld().getCell(location).getInventory().addClothing(clothing);

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

	/** Private StringBuilder for use in transformation description methods. */
	private StringBuilder transformationSB = new StringBuilder();

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
		boolean visibleVagina = isCoverableAreaExposed(CoverableArea.VAGINA) && hasVagina(),
				visiblePenis = isCoverableAreaExposed(CoverableArea.PENIS) && hasPenis();
		
		if(isFeminine()) {
			if(visibleVagina && visiblePenis) {
					// Exposed penis and vagina:
					if(isPlayer()) {
						return new GenderAppearance("Due to the fact that both your [pc.vagina] and [pc.penis] are exposed, everyone can tell that you're [pc.a_gender] on first glance.", Gender.FUTANARI);
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"Due to the fact that both [npc.her] [npc.vagina] and [npc.penis] are exposed, everyone can tell that [npc.she]'s [npc.a_gender] on first glance."), Gender.FUTANARI);
					}
					
			} else if(visibleVagina) {
				if(getPenisRawSizeValue()>=PenisSize.THREE_LARGE.getMaximumValue() && hasPenis()) {
					// Exposed vagina and obvious penis bulge:
					if(isPlayer()) {
						return new GenderAppearance("The [pc.cockSize] bulge between your legs, combined with the fact that your [pc.vagina] is exposed, reveals to everyone that you're [pc.a_gender].", Gender.FUTANARI);
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"The [npc.cockSize] bulge between [npc.her] legs, combined with the fact that [npc.her] [npc.vagina] is exposed, reveals to everyone that [npc.she]'s [npc.a_gender]."), Gender.FUTANARI);
					}
					
				}
				
				if(hasPenis()) {
					// Assume female, as penis is not visible:
					if(isPlayer()) {
						return new GenderAppearance("As your [pc.vagina] is exposed, and your [pc.penis] remains concealed, everyone assumes that you're "
								+UtilText.generateSingluarDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+" on first glance.", Gender.FEMALE);
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"Due to the fact that [npc.her] [npc.vagina] is exposed, everyone assumes that [npc.she]'s "
										+UtilText.generateSingluarDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+" on first glance."), Gender.FEMALE);
					}
					
				} else {
					// Correctly assume female:
					if(isPlayer()) {
						return new GenderAppearance("Due to the fact that your [pc.vagina] is exposed, everyone correctly assumes that you're "
								+UtilText.generateSingluarDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+" on first glance.", Gender.FEMALE);
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"Due to the fact that [npc.her] [npc.vagina] is exposed, everyone assumes that [npc.she]'s "
										+UtilText.generateSingluarDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+" on first glance."), Gender.FEMALE);
					}
				}
				
			} else if(visiblePenis) {
				// Exposed penis:
				if(isPlayer()) {
					return new GenderAppearance("Due to the fact that your [pc.penis] is exposed, everyone assumes that you're [pc.a_gender] on first glance.", Gender.SHEMALE);
				} else {
					return new GenderAppearance(UtilText.parse(this,
							"Due to the fact that [npc.her] [npc.penis] is exposed, everyone assumes that [npc.she]'s [npc.a_gender] on first glance."), Gender.SHEMALE);
				}
				
			} else {
				if(getPenisRawSizeValue()>=PenisSize.THREE_LARGE.getMaximumValue() && hasPenis()) {
					// Obvious penis bulge:
					if(isPlayer()) {
						return new GenderAppearance("The [pc.cockSize] bulge between your legs, combined with your feminine appearance, leads everyone to believe that you're [pc.a_gender].", Gender.SHEMALE);
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] feminine appearance, leads everyone to believe that [npc.she]'s [npc.a_gender]."), Gender.SHEMALE);
					}
					
				} else {
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						if(isPlayer()) {
							return new GenderAppearance("Your [pc.penis] is concealed, so, due to your feminine appearance, strangers assume that you're "
									+UtilText.generateSingluarDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+".", Gender.FEMALE);
						} else {
							return new GenderAppearance(UtilText.parse(this,
									"Due to [npc.her] feminine appearance, [npc.she] appears to be "
											+UtilText.generateSingluarDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+"."), Gender.FEMALE);
						}
						
					} else if(hasVagina()) {
						// Correctly assume female:
						if(isPlayer()) {
							return new GenderAppearance("Your feminine appearance leads everyone to correctly assume that you're "
									+UtilText.generateSingluarDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+".", Gender.FEMALE);
						} else {
							return new GenderAppearance(UtilText.parse(this,
									"Due to [npc.her] feminine appearance, [npc.she] appears to be "
											+UtilText.generateSingluarDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+"."), Gender.FEMALE);
						}
						
					} else {
						if(isCoverableAreaExposed(CoverableArea.VAGINA) && isCoverableAreaExposed(CoverableArea.PENIS)) {
							// Can see doll:
							if(isPlayer()) {
								if(isFeminine()) {
									return new GenderAppearance("Your genderless mound is exposed, so, due to your feminine appearance, strangers treat you like "
											+UtilText.generateSingluarDeterminer(Gender.GENDERLESS_FEMININE.getName())+" "+Gender.GENDERLESS_FEMININE.getName()+".", Gender.GENDERLESS_FEMININE);
								} else {
									return new GenderAppearance("Your genderless mound is exposed, so, due to your masculine appearance, strangers treat you like "
											+UtilText.generateSingluarDeterminer(Gender.GENDERLESS_MASCULINE.getName())+" "+Gender.GENDERLESS_MASCULINE.getName()+".", Gender.GENDERLESS_MASCULINE);
								}
							} else {
								if(isFeminine()) {
									return new GenderAppearance(UtilText.parse(this,
											"Due to [npc.her] genderless mound being exposed, everyone can tell that [npc.she]'s "
													+UtilText.generateSingluarDeterminer(Gender.GENDERLESS_FEMININE.getName())+" "+Gender.GENDERLESS_FEMININE.getName()+"."), Gender.GENDERLESS_FEMININE);
								} else {
									return new GenderAppearance(UtilText.parse(this,
											"Due to [npc.her] genderless mound being exposed, everyone can tell that [npc.she]'s "
													+UtilText.generateSingluarDeterminer(Gender.GENDERLESS_MASCULINE.getName())+" "+Gender.GENDERLESS_MASCULINE.getName()+"."), Gender.GENDERLESS_MASCULINE);
								}
							}
							
						} else {
							// Correctly assume doll:
							if(isPlayer()) {
								return new GenderAppearance("Your genderless mound is concealed, so, due to your feminine appearance, strangers assume that you're "
										+UtilText.generateSingluarDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+".", Gender.FEMALE);
							} else {
								return new GenderAppearance(UtilText.parse(this,
										"Due to [npc.her] feminine appearance, [npc.she] appears to be "
												+UtilText.generateSingluarDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+"."), Gender.FEMALE);
							}
						}
					}
				}
			}
			
		} else { // Masculine:
			
			if(visibleVagina && visiblePenis) {
				// Exposed penis and vagina:
				if(isPlayer()) {
					return new GenderAppearance("Due to the fact that both your [pc.vagina] and [pc.penis] are exposed, everyone can tell that you're [pc.a_gender] on first glance.", Gender.HERMAPHRODITE);
				} else {
					return new GenderAppearance(UtilText.parse(this,
							"Due to the fact that both [npc.her] [npc.vagina] and [npc.penis] are exposed, everyone can tell that [npc.she]'s [npc.a_gender] on first glance."), Gender.HERMAPHRODITE);
				}
				
			} else if(visibleVagina) {
				if(getPenisRawSizeValue()>=PenisSize.THREE_LARGE.getMaximumValue() && hasPenis()) {
					// Exposed vagina and obvious penis bulge:
					if(isPlayer()) {
						return new GenderAppearance("The [pc.cockSize] bulge between your legs, combined with the fact that your [pc.vagina] is exposed, reveals to everyone that you're [pc.a_gender].", Gender.HERMAPHRODITE);
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"The [npc.cockSize] bulge between [npc.her] legs, combined with the fact that [npc.her] [npc.vagina] is exposed, reveals to everyone that [npc.she]'s [npc.a_gender]."), Gender.HERMAPHRODITE);
					}
					
				}
				
				if(hasPenis()) {
					// Assume cuntboy, as penis is not visible:
					if(isPlayer()) {
						return new GenderAppearance("As your [pc.vagina] is exposed, and your [pc.penis] remains concealed, everyone assumes that you're "
								+UtilText.generateSingluarDeterminer(Gender.CUNT_BOY.getName())+" "+Gender.CUNT_BOY.getName()+" on first glance.", Gender.CUNT_BOY);
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"Due to the fact that [npc.her] [npc.vagina] is exposed, everyone assumes that [npc.she]'s "
										+UtilText.generateSingluarDeterminer(Gender.CUNT_BOY.getName())+" "+Gender.CUNT_BOY.getName()+" on first glance."), Gender.CUNT_BOY);
					}
					
				} else {
					// Correctly assume cuntboy:
					if(isPlayer()) {
						return new GenderAppearance("Due to the fact that your [pc.vagina] is exposed, everyone correctly assumes that you're "
								+UtilText.generateSingluarDeterminer(Gender.CUNT_BOY.getName())+" "+Gender.CUNT_BOY.getName()+" on first glance.", Gender.CUNT_BOY);
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"Due to the fact that [npc.her] [npc.vagina] is exposed, everyone assumes that [npc.she]'s "
										+UtilText.generateSingluarDeterminer(Gender.CUNT_BOY.getName())+" "+Gender.CUNT_BOY.getName()+" on first glance."), Gender.CUNT_BOY);
					}
				}
				
			} else if(visiblePenis) {
				// Exposed penis:
				if(isPlayer()) {
					return new GenderAppearance("Due to the fact that your [pc.penis] is exposed, everyone assumes that you're [pc.a_gender] on first glance.", Gender.MALE);
				} else {
					return new GenderAppearance(UtilText.parse(this,
							"Due to the fact that [npc.her] [npc.penis] is exposed, everyone assumes that [npc.she]'s [npc.a_gender] on first glance."), Gender.MALE);
				}
				
			} else {
				if(getPenisRawSizeValue()>=PenisSize.THREE_LARGE.getMaximumValue() && hasPenis()) {
					// Obvious penis bulge:
					if(isPlayer()) {
						return new GenderAppearance("The [pc.cockSize] bulge between your legs, combined with your masculine appearance, leads everyone to believe that you're [pc.a_gender].", Gender.MALE);
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] masculine appearance, leads everyone to believe that [npc.she]'s [npc.a_gender]."), Gender.MALE);
					}
					
				} else {
					if(hasPenis()) {
						// Assume male, as penis is not visible:
						if(isPlayer()) {
							return new GenderAppearance("Your [pc.penis] is concealed, so, due to your masculine appearance, strangers assume that you're "
									+UtilText.generateSingluarDeterminer(Gender.MALE.getName())+" "+Gender.MALE.getName()+".", Gender.MALE);
						} else {
							return new GenderAppearance(UtilText.parse(this,
									"Due to [npc.her] masculine appearance, [npc.she] appears to be "
											+UtilText.generateSingluarDeterminer(Gender.MALE.getName())+" "+Gender.MALE.getName()+"."), Gender.MALE);
						}
						
					} else if(hasVagina()) {
						// Assume male:
						if(isPlayer()) {
							return new GenderAppearance("Your masculine appearance leads everyone to assume that you're "
									+UtilText.generateSingluarDeterminer(Gender.MALE.getName())+" "+Gender.MALE.getName()+".", Gender.MALE);
						} else {
							return new GenderAppearance(UtilText.parse(this,
									"Due to [npc.her] masculine appearance, [npc.she] appears to be "
											+UtilText.generateSingluarDeterminer(Gender.MALE.getName())+" "+Gender.MALE.getName()+"."), Gender.MALE);
						}
						
					} else {
						if(isCoverableAreaExposed(CoverableArea.VAGINA) && isCoverableAreaExposed(CoverableArea.PENIS)) {
							// Can see doll:
							if(isPlayer()) {
								if(isFeminine()) {
									return new GenderAppearance("Your genderless mound is exposed, so strangers treat you like "
											+UtilText.generateSingluarDeterminer(Gender.GENDERLESS_FEMININE.getName())+" "+Gender.GENDERLESS_FEMININE.getName()+".", Gender.GENDERLESS_FEMININE);
								} else {
									return new GenderAppearance("Your genderless mound is exposed, so strangers treat you like "
											+UtilText.generateSingluarDeterminer(Gender.GENDERLESS_MASCULINE.getName())+" "+Gender.GENDERLESS_MASCULINE.getName()+".", Gender.GENDERLESS_MASCULINE);
								}
							} else {
								if(isFeminine()) {
									return new GenderAppearance(UtilText.parse(this,
											"Due to [npc.her] genderless mound being exposed, everyone can tell that [npc.she]'s "
													+UtilText.generateSingluarDeterminer(Gender.GENDERLESS_FEMININE.getName())+" "+Gender.GENDERLESS_FEMININE.getName()+"."), Gender.GENDERLESS_FEMININE);
								} else {
									return new GenderAppearance(UtilText.parse(this,
											"Due to [npc.her] genderless mound being exposed, everyone can tell that [npc.she]'s "
													+UtilText.generateSingluarDeterminer(Gender.GENDERLESS_MASCULINE.getName())+" "+Gender.GENDERLESS_MASCULINE.getName()+"."), Gender.GENDERLESS_MASCULINE);
								}
							}
							
						} else {
							// Correctly assume doll:
							if(isPlayer()) {
								return new GenderAppearance("Your genderless mound is concealed, so, due to your masculine appearance, strangers assume that you're "
										+UtilText.generateSingluarDeterminer(Gender.MALE.getName())+" "+Gender.MALE.getName()+".", Gender.MALE);
							} else {
								return new GenderAppearance(UtilText.parse(this,
										"Due to [npc.her] masculine appearance, [npc.she] appears to be "
												+UtilText.generateSingluarDeterminer(Gender.MALE.getName())+" "+Gender.MALE.getName()+"."), Gender.MALE);
							}
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
		boolean isFeminine = true;
		
		if(Femininity.valueOf(getFemininity()) == Femininity.ANDROGYNOUS) {
			switch(Main.getProperties().androgynousIdentification){
				case FEMININE:
					isFeminine = true;
					break;
				case CLOTHING_FEMININE:
					isFeminine = true;
					if(getClothingAverageFemininity()<50)
						isFeminine = false;
					break;
				case CLOTHING_MASCULINE:
					isFeminine = false;
					if(getClothingAverageFemininity()>50)
						isFeminine = true;
					break;
				case MASCULINE:
					isFeminine = false;
					break;
				default:
					break;
			}
//			System.out.println("fem: "+isFeminine +"   "+getClothingAverageFemininity());
			return isFeminine;
		} else {
			return body.getGender().isFeminine();
		}
	}


	public Race getRace() {
		return body.getRace();
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


	public Race getVaginaRace() {
		return body.getVagina().getType().getRace();
	}


	public Race getWingRace() {
		return body.getWing().getType().getRace();
	}

	private StringBuilder postTFSB;
	public String postTransformationCalculation() {
		return postTransformationCalculation(true);
	}
	public String postTransformationCalculation(boolean displayColourDiscovered) {
		postTFSB = new StringBuilder();
		// If this is the first time getting this covering type:
		for(BodyPartInterface bp : body.getAllBodyParts()) {
			if(!body.getBodyCoveringTypesDiscovered().contains(bp.getType().getSkinType())) {
				if(bp.getType().getSkinType()!=null) {
					body.getBodyCoveringTypesDiscovered().add(bp.getType().getSkinType());
					
					if(displayColourDiscovered) {
						if(isPlayer()) {
							postTFSB.append("<b>You have discovered that your natural</b>"
											+" <b style='color:"+bp.getType().getRace().getColour()+";'>"+bp.getType().getRace().getName()+"'s</b> <b>"+bp.getName(this)+" colour is</b>"
											+ " <b style='color:"+getSkinColour(bp.getType().getSkinType())+";'>"+getSkinColour(bp.getType().getSkinType()).getName()+"</b><b>!</b>");
						} else {
							postTFSB.append(UtilText.parse(this,
									"<b>[npc.Name] has discovered that [npc.her] natural</b>"
									+" <b style='color:"+bp.getType().getRace().getColour()+";'>"+bp.getType().getRace().getName()+"'s</b> <b>"+bp.getName(this)+" colour is</b>"
									+ " <b style='color:"+getSkinColour(bp.getType().getSkinType())+";'>"+getSkinColour(bp.getType().getSkinType()).getName()+"</b><b>!</b>"));
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

	public RaceStage getRaceStage() {
		return body.getRaceStage();
	}


	public int getFemininity() {
		return body.getFemininity();
	}

	/**
	 * @return Formatted description of piercing change.
	 */
	public String setFemininity(int femininity) {
		if (body.getFemininity() < femininity) {
			if (body.setFemininity(femininity))
				return isPlayer()
						? "<p>" + "You feel your body slim down and shift to become <b style='color:" + Colour.FEMININE + ";'>more feminine</b>." + "</br>" + "You have <b style='color:"
								+ Femininity.valueOf(getFemininity()).getColour() + ";'>" + Femininity.getFemininityName(getFemininity(), true) + "</b> body." + "</p>"
						: UtilText.genderParsing(this, "<p>" + "<Her> body shifts to become <b style='color:" + Colour.FEMININE + ";'>more feminine</b>." + "</p>");
		} else {
			if (body.setFemininity(femininity))
				return isPlayer()
						? "<p>" + "You feel your body getting broader and shifting to become <b style='color:" + Colour.MASCULINE + ";'>more masculine</b>." + "</br>" + "You have <b style='color:"
								+ Femininity.valueOf(getFemininity()).getColour() + ";'>" + Femininity.getFemininityName(getFemininity(), true) + "</b> body." + "</p>"
						: UtilText.genderParsing(this, "<p>" + "<Her> body shifts to become <b style='color:" + Colour.MASCULINE + ";'>more masculine</b>." + "</p>");
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY + ";'>Your femininity doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name]'s femininity doesn't change...</span>")
				+ "</p>";
		}
	}

	/**
	 * @return Formatted description of piercing change.
	 */
	public String incrementFemininity(int increment) {
		return setFemininity(getFemininity() + increment);
	}

	/**
	 * @return The character's height in cm.
	 */
	public int getHeight() {
		return body.getHeight();
	}
	
	public String getHeightName() {
		return body.getHeightName();
	}

	/**
	 * @return The character's underlying height. This value is not indicative
	 *         of the Character's actual height, which is subject to modifiers.
	 *         The modified (real) height is returned by getHeight().
	 */
	public int getRawHeightValue() {
		return body.getHeight();
	}

	/**
	 * @return Formatted description of height change.
	 */
	public String setHeight(int height) {
		if (body.getHeight() < height) {
			if (body.setHeight(height))
				return isPlayer()
						? "<p>" + "The world around you seems slightly further away than it used to be, but after a moment you realise that you've just <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>grown taller</b>."
								+ "</br>" + "You are now <b>" + getHeight() + "cm</b> tall." + "</p>"
						: UtilText.genderParsing(this,
								"<p>" + "<She> sways from side to side a little, <her> balance suddenly thrown off by the fact that she's just <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>grown taller</b>." + "</p>");
		} else {
			if (body.setHeight(height))
				return isPlayer()
						? "<p>" + "The world around you suddenly seems slightly closer than it used to be, but after a moment you realise that you've just <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>become shorter</b>."
								+ "</br>" + "You are now <b>" + getHeight() + "cm</b> tall." + "</p>"
						: UtilText.genderParsing(this, "<p>" + "<She> shrinks down, <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>becoming noticeably shorter</b>." + "</p>");
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY + ";'>Your height doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name]'s height doesn't change...</span>")
				+ "</p>";
		}
	}

	/**
	 * @return Formatted description of height change.
	 */
	public String incrementHeight(int increment) {
		return setHeight(getHeight() + increment);
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

	// Piercings:

	// Nose:
	public boolean isPiercedNose() {
		return body.getFace().isPiercedNose();
	}
	/**
	 * @return Formatted description of piercing change.
	 */
	public String setPiercedNose(boolean pierced) {
		if (!isPiercedNose() && pierced) {
			body.getFace().setPiercedNose(pierced);
			if(isPlayer()) {
				return "<p>"
							+ "Your nose is now <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> nose is now <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>pierced</b>."
						+ "</p>");
			}
			
		} else if (isPiercedNose() && !pierced) {
			body.getFace().setPiercedNose(pierced);
			if(isPlayer()) {
				return "<p>"
							+ "Your nose is <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>no longer pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> nose is <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>no longer pierced</b>."
						+ "</p>");
			}
		} else {
			return "<p>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>"
					+ "</p>";
		}
	}
	
	// Ears:
	public boolean isPiercedEar() {
		return body.getEar().isPierced();
	}
	/**
	 * @return Formatted description of piercing change.
	 */
	public String setPiercedEar(boolean pierced) {
		if (!isPiercedEar() && pierced) {
			body.getEar().setPierced(pierced);
			if(isPlayer()) {
				return "<p>"
							+ "Your ears are now <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> ears are now <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>pierced</b>."
						+ "</p>");
			}
			
		} else if (isPiercedEar() && !pierced) {
			body.getEar().setPierced(pierced);
			if(isPlayer()) {
				return "<p>"
							+ "Your ears are <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>no longer pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> ears are <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>no longer pierced</b>."
						+ "</p>");
			}
		} else {
			return "<p>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>"
					+ "</p>";
		}
	}
	
	// Tongue:
	public boolean isPiercedTongue() {
		return body.getFace().getTongue().isPierced();
	}
	/**
	 * @return Formatted description of piercing change.
	 */
	public String setPiercedTongue(boolean pierced) {
		if (!isPiercedTongue() && pierced) {
			body.getFace().getTongue().setPierced(pierced);
			if(isPlayer()) {
				return "<p>"
							+ "Your tongue is now <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> tongue is now <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>pierced</b>."
						+ "</p>");
			}
			
		} else if (isPiercedTongue() && !pierced) {
			body.getFace().getTongue().setPierced(pierced);
			if(isPlayer()) {
				return "<p>"
							+ "Your tongue is <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>no longer pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> tongue is <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>no longer pierced</b>."
						+ "</p>");
			}
		} else {
			return "<p>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>"
					+ "</p>";
		}
	}
	
	// Lips:
	public boolean isPiercedLip() {
		return body.getFace().isPiercedLip();
	}
	/**
	 * @return Formatted description of piercing change.
	 */
	public String setPiercedLip(boolean pierced) {
		if (!isPiercedLip() && pierced) {
			body.getFace().setPiercedLip(pierced);
			if(isPlayer()) {
				return "<p>"
							+ "Your lips are now <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> lips are now <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>pierced</b>."
						+ "</p>");
			}
			
		} else if (isPiercedLip() && !pierced) {
			body.getFace().setPiercedLip(pierced);
			if(isPlayer()) {
				return "<p>"
							+ "Your lips are <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>no longer pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> lips are <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>no longer pierced</b>."
						+ "</p>");
			}
		} else {
			return "<p>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>"
					+ "</p>";
		}
	}
	
	// Navel/Stomach (I wasn't very consistent with the names...):
	public boolean isPiercedNavel() {
		return body.isPiercedStomach();
	}
	/**
	 * @return Formatted description of piercing change.
	 */
	public String setPiercedNavel(boolean pierced) {
		if (!isPiercedNavel() && pierced) {
			body.setPiercedStomach(pierced);
			if(isPlayer()) {
				return "<p>"
							+ "Your navel is now <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> navel is now <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>pierced</b>."
						+ "</p>");
			}
			
		} else if (isPiercedNavel() && !pierced) {
			body.setPiercedStomach(pierced);
			if(isPlayer()) {
				return "<p>"
							+ "Your navel is <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>no longer pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> navel is <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>no longer pierced</b>."
						+ "</p>");
			}
		} else {
			return "<p>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>"
					+ "</p>";
		}
	}
	
	// Nipples:
	public boolean isPiercedNipple() {
		return body.getBreast().isPierced();
	}
	/**
	 * @return Formatted description of piercing change.
	 */
	public String setPiercedNipple(boolean pierced) {
		if (!isPiercedNipple() && pierced) {
			body.getBreast().setPierced(pierced);
			if(isPlayer()) {
				return "<p>"
							+ "Your nipples are now <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> nipples are now <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>pierced</b>."
						+ "</p>");
			}
			
		} else if (isPiercedNipple() && !pierced) {
			body.getBreast().setPierced(pierced);
			if(isPlayer()) {
				return "<p>"
							+ "Your nipples are <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>no longer pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> nipples are <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>no longer pierced</b>."
						+ "</p>");
			}
		} else {
			return "<p>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>"
					+ "</p>";
		}
	}

	// Vagina:
	public boolean isPiercedVagina() {
		return body.getVagina().isPierced();
	}
	/**
	 * @return Formatted description of piercing change.
	 */
	public String setPiercedVagina(boolean pierced) {
		if (!isPiercedVagina() && pierced) {
			body.getVagina().setPierced(pierced);
			if(isPlayer()) {
				return "<p>"
							+ "Your vagina is now <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> vagina is now <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>pierced</b>."
						+ "</p>");
			}
			
		} else if (isPiercedVagina() && !pierced) {
			body.getVagina().setPierced(pierced);
			if(isPlayer()) {
				return "<p>"
							+ "Your vagina is <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>no longer pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> vagina is <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>no longer pierced</b>."
						+ "</p>");
			}
		} else {
			return "<p>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>"
					+ "</p>";
		}
	}

	// Penis:
	public boolean isPiercedPenis() {
		return body.getPenis().isPierced();
	}
	/**
	 * @return Formatted description of piercing change.
	 */
	public String setPiercedPenis(boolean pierced) {
		if (!isPiercedPenis() && pierced) {
			body.getPenis().setPierced(pierced);
			if(isPlayer()) {
				return "<p>"
							+ "Your penis is now <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> penis is now <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>pierced</b>."
						+ "</p>");
			}
			
		} else if (isPiercedPenis() && !pierced) {
			body.getPenis().setPierced(pierced);
			if(isPlayer()) {
				return "<p>"
							+ "Your penis is <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>no longer pierced</b>."
						+ "</p>";
			}else {
				return UtilText.genderParsing(this,
						"<p>"
							+ "<Her> penis is <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>no longer pierced</b>."
						+ "</p>");
			}
		} else {
			return "<p>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>"
					+ "</p>";
		}
	}

	// Body parts in alphabetical order:

	// Arms:

	public ArmType getArmType() {
		return body.getArm().getType();
	}

	/**
	 * @return Formatted description of arm change.
	 */
	public String setArmType(ArmType type) {
		if (type == getArmType()) {
			if (isPlayer()) {
				return "<p style='text-align:center;'>"
							+ "<span style='color:" + Colour.TEXT_GREY + ";'>You already have the [pc.arms] of a [pc.armRace], so nothing happens...</span>"
						+ "</p>";
			} else {
				return "<p style='text-align:center;'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] already has the [npc.arms] of a [npc.armRace], so nothing happens...</span>")
					+ "</p>";
			}
			
		} else {
			if (isPlayer())
				transformationSB = new StringBuilder(
						"<p>" + "Your " + getArmType().getName(this) + " suddenly feel hot and itchy, and you gasp as they start to transform" + (getArmType() == ArmType.HARPY ? ", rapidly turning into feather-covered humanoid arms." : "."));
			else
				transformationSB = new StringBuilder(UtilText.genderParsing(this,
						"<p>" + getName("The") + " gasps as <her> " + getArmType().getName(this) + " start to transform" + (getArmType() == ArmType.HARPY ? ", rapidly turning into feather-covered humanoid arms." : ".")));
		}

		switch (type) {
		case HUMAN:
			transformationSB.append(isPlayer()
					? " Within moments, your upper limbs have turned back into normal human arms, complete with regular human hands." + " They're covered in a layer of ordinary " + getSkinColour(BodyCoveringType.HUMAN).getName() + " skin." + "</br>"
							+ "You now have <b style='color:" + Colour.TRANSFORMATION_LESSER + ";'>human arms</b>."
					: UtilText.genderParsing(this, " Within moments, <her> upper limbs have turned into human arms, complete with regular human hands." + " They're covered in a layer of ordinary "
							+ getSkinColour(BodyCoveringType.HUMAN).getName() + " skin." + "</br>" + "<She> now has <b style='color:" + Colour.TRANSFORMATION_LESSER + ";'>human arms</b>."));
			break;
		case DEMON_COMMON:
			transformationSB.append(isPlayer()
					? " You feel your upper limbs slimming down to become slender, human-like arms. Your hands similarly transform into more delicate versions of their human counterparts." + " As the transformation finishes, a layer of flawless "
							+ getSkinColour(BodyCoveringType.DEMON_COMMON).getName() + " skin rapidly grows to cover your perfectly-formed appendages." + "</br>" + "You now have <b style='color:" + Colour.TRANSFORMATION_LESSER
							+ ";'>demonic arms</b>."
					: UtilText.genderParsing(this,
							" <Her> upper limbs slim down to become slender, human-like arms. <Her> hands similarly transform into more delicate versions of their human counterparts." + " As the transformation finishes, a layer of flawless "
									+ getSkinColour(BodyCoveringType.DEMON_COMMON).getName() + " skin rapidly grows to cover <her> perfectly-formed appendages." + "</br>" + "<She> now has <b style='color:" + Colour.TRANSFORMATION_LESSER
									+ ";'>demonic arms</b>."));
			break;
		case DOG_MORPH:
			transformationSB.append(isPlayer()
					? " A layer of " + getSkinColour(BodyCoveringType.CANINE_FUR).getName() + " fur quickly grows over your arms and hands to replace your old " + getSkinColour(getArmType().getSkinType()).getName() + " "
							+ getArmType().getSkinType().getName(this) + "." + " As your new fur finishes growing over the backs of your hands, little blunt claws grow to replace your fingernails."
							+ " Turning your hands over, you see your palms rapidly transforming into little leathery pads, and before you know it, you're left with a pair of anthropomorphic, dog-like hands."
							+ " At your upper-biceps, your new fur smoothly transitions into the " + getSkinType().getName(this) + " that's covering the rest of your body." + "</br>" + "You now have anthropomorphic <b style='color:"
							+ Colour.TRANSFORMATION_LESSER + ";'>dog-like arms</b>."
					: UtilText.genderParsing(this,
							" A layer of " + getSkinColour(BodyCoveringType.CANINE_FUR).getName() + " fur quickly grows over <her> arms and hands to replace <her> old " + getSkinColour(getArmType().getSkinType()).getName() + " "
									+ getArmType().getSkinType().getName(this) + "." + " As the new fur finishes growing over the backs of <her> hands, little blunt claws grow to replace <her> fingernails."
									+ " <She> turns <her> hands over to reveal that <her> palms are rapidly transforming into little leathery pads, and after only a moment, <she>'s left with a pair of anthropomorphic, dog-like hands."
									+ " At <her> upper-biceps, <her> new fur smoothly transitions into the " + getSkinType().getName(this) + " that's covering covering the rest of <her> body." + "</br>" + "<She> now has anthropomorphic <b style='color:"
									+ Colour.TRANSFORMATION_LESSER + ";'>dog-like arms</b>."));
			break;
		case LYCAN:
			transformationSB.append(isPlayer()
					? " A layer of shaggy " + getSkinColour(BodyCoveringType.LYCAN_FUR).getName() + " fur quickly grows over your arms and hands to replace your old " + getSkinColour(getArmType().getSkinType()).getName() + " "
							+ getArmType().getSkinType().getName(this) + "." + " As your new fur finishes growing over the backs of your hands, sharp claws grow to replace your fingernails."
							+ " Turning your hands over, you see your palms rapidly transforming into tough leathery pads, and before you know it, you're left with a pair of anthropomorphic, wolf-like hands."
							+ " At your upper-biceps, your new fur smoothly transitions into the " + getSkinType().getName(this) + " that's covering the rest of your body." + "</br>" + "You now have anthropomorphic <b style='color:"
							+ Colour.TRANSFORMATION_LESSER + ";'>wolf-like arms</b>."
					: UtilText.genderParsing(this,
							" A layer of shaggy " + getSkinColour(BodyCoveringType.LYCAN_FUR).getName() + " fur quickly grows over <her> arms and hands to replace <her> old " + getSkinColour(getArmType().getSkinType()).getName() + " "
									+ getArmType().getSkinType().getName(this) + "." + " As the new fur finishes growing over the backs of <her> hands, sharp claws grow to replace <her> fingernails."
									+ " <She> turns <her> hands over to reveal that <her> palms are rapidly transforming into tough leathery pads, and after only a moment, <she>'s left with a pair of anthropomorphic, wolf-like hands."
									+ " At <her> upper-biceps, <her> new fur smoothly transitions into the " + getSkinType().getName(this) + " that's covering the rest of <her> body." + "</br>" + "<She> now has anthropomorphic <b style='color:"
									+ Colour.TRANSFORMATION_LESSER + ";'>wolf-like arms</b>."));
			break;
		case CAT_MORPH:
			transformationSB.append(isPlayer()
					? " A layer of smooth " + getSkinColour(BodyCoveringType.FELINE_FUR).getName() + " fur quickly grows over your arms and hands to replace your old " + getSkinColour(getArmType().getSkinType()).getName() + " "
							+ getArmType().getSkinType().getName(this) + "." + " As your new fur finishes growing over the backs of your hands, sharp, retractable claws grow to replace your fingernails."
							+ " Turning your hands over, you see your palms rapidly transforming into little pink pads, and before you know it, you're left with a pair of anthropomorphic, cat-like hands."
							+ " At your upper-biceps, your new fur smoothly transitions into the " + getSkinType().getName(this) + " that's covering the rest of your body." + "</br>" + "You now have anthropomorphic <b style='color:"
							+ Colour.TRANSFORMATION_LESSER + ";'>cat-like arms</b>."
					: UtilText.genderParsing(this,
							" A layer of smooth " + getSkinColour(BodyCoveringType.FELINE_FUR).getName() + " fur quickly grows over <her> arms and hands to replace <her> old " + getSkinColour(getArmType().getSkinType()).getName() + " "
									+ getArmType().getSkinType().getName(this) + "." + " As the new fur finishes growing over the backs of <her> hands, sharp, retractable claws grow to replace <her> fingernails."
									+ " <She> turns <her> hands over to reveal that <her> palms are rapidly transforming into little pink pads, and after only a moment, <she>'s left with a pair of anthropomorphic, cat-like hands."
									+ " At <her> upper-biceps, <her> new fur smoothly transitions into the " + getSkinType().getName(this) + " that's covering the rest of <her> body." + "</br>" + "<She> now has anthropomorphic <b style='color:"
									+ Colour.TRANSFORMATION_LESSER + ";'>cat-like arms</b>."));
			break;
		case HORSE_MORPH:
			transformationSB.append(isPlayer()
					? " A layer of short " + getSkinColour(BodyCoveringType.HORSE_HAIR).getName() + " horse-hair quickly grows over your arms and hands to replace your old " + getSkinColour(getArmType().getSkinType()).getName() + " "
							+ getArmType().getSkinType().getName(this) + "." + " As your new hair finishes growing over the backs of your hands, your fingernails grow thicker and transform into tough, hoof-like nails, but despite their appearance,"
							+ " you're relieved to find that they've lost none of their dexterity." + " At your upper-biceps, your new horse-hair smoothly transitions into the " + getSkinType().getName(this) + " that's covering the rest of your body."
							+ "</br>" + "You now have anthropomorphic <b style='color:" + Colour.TRANSFORMATION_LESSER + ";'>horse-like arms</b>."
					: UtilText.genderParsing(this,
							" A layer of short " + getSkinColour(BodyCoveringType.HORSE_HAIR).getName() + " horse-hair quickly grows over <her> arms and hands to replace <her> old " + getSkinColour(getArmType().getSkinType()).getName() + " "
									+ getArmType().getSkinType().getName(this) + "." + " As the new hair finishes growing over the backs of <her> hands, <her> fingernails grow thicker and transform into tough, hoof-like nails."
									+ " At <her> upper-biceps, <her> new horse-hair smoothly transitions into the " + getSkinType().getName(this) + " that's covering the rest of <her> body." + "</br>" + "<She> now has anthropomorphic <b style='color:"
									+ Colour.TRANSFORMATION_LESSER + ";'>horse-like arms</b>."));
			break;
		case HARPY:
			transformationSB.append(isPlayer()
					? " A layer of " + getSkinColour(BodyCoveringType.FEATHERS).getName() + " feathers suddenly sprout out all over your arms and hands, quickly replacing your old " + getSkinColour(getArmType().getSkinType()).getName() + " "
							+ getArmType().getSkinType().getName(this) + "."
							+ " As you gasp and turn your arms this way and that, new feathers continue to grow all over your upper limbs, and after just a few moments, your arms are completely covered in feathers."
							+ " Just as you think that the transformation has finished, you cry out in shock as you feel your bones shifting and molding into a new form."
							+ " Before you have any time to think about what's happening, the transformation comes to an end, leaving you with a pair of huge, feathered wings in place of arms."
							+ " Where your hands once were, your fingers have shrunk down into the middle-joint of your new appendages. All that's left is a feathered opposable thumb, which ends in a blunt claw."
							+ " By folding your wings back onto themselves, you can thankfully still use your thumb to grasp and manipulate objects."
							+ " Where your new wings meet your body at the shoulder, your feathers smoothly cover the transition into the " + getSkinType().getName(this) + " that's covering your torso." + "</br>"
							+ "You now have a pair of <b style='color:" + Colour.TRANSFORMATION_LESSER + ";'>harpy wings</b> in place of arms."
					: UtilText.genderParsing(this,
							" A layer of " + getSkinColour(BodyCoveringType.FEATHERS).getName() + " feathers suddenly sprout out all over <her> arms and hands, quickly replacing <her> old " + getSkinColour(getArmType().getSkinType()).getName() + " "
									+ getArmType().getSkinType().getName(this) + "."
									+ " As <she> gasps and twists <her> arms this way and that, new feathers continue to grow all over <her> upper limbs, and after just a few moments, <her> arms are completely covered in feathers."
									+ " Just as it seems that the transformation has finished, <she> cries out in shock as <her> bones start shifting and molding into a new form."
									+ " Thankfully for <her>, the transformation soon comes to an end, leaving <her> with a pair of huge, feathered wings in place of arms."
									+ " Where <her> hands once were, <her> fingers have shrunk down into the middle-joint of <her> new appendages. All that's left is a feathered opposable thumb, which ends in a blunt claw."
									+ " By folding <her> wings back onto themselves, <she> can thankfully still use <her> thumb to grasp and manipulate objects."
									+ " Where <her> new wings meet <her> body at the shoulder, <her> feathers smoothly cover the transition into the " + getSkinType().getName(this) + " that's covering <her> torso." + "</br>"
									+ "<She> now has a pair of <b style='color:" + Colour.TRANSFORMATION_LESSER + ";'>harpy wings</b> in place of arms."));
			break;
		// case SLIME:
		// if(getArmType()!=ArmType.HUMAN)
		// transformationSB.append(isPlayer()
		// ?" Within moments, your "+getArmType().getName(this)+" have turned into
		// normal-looking human arms, complete with regular human hands."
		// + " They are covered in a layer of normal-looking
		// "+getSkinColour(SkinType.HUMAN).getName()+" skin."
		// :GenericSentence.parseTextForGenderReplacement(this,
		// " Within moments, <her> "+getArmType().getName(this)+" have turned back
		// into normal-looking human arms, complete with regular human hands."
		// + " They are covered in a layer of normal-looking
		// "+getSkinColour(SkinType.HUMAN).getName()+" skin."));
		// transformationSB.append(isPlayer()
		// ?" Your arms suddenly feel extremely weak, and you gasp in horror as
		// you feel them start to melt. All the way up to your shoulder, your
		// flesh turns into"
		// + " "+getSkinColour(SkinType.SLIME).getName()+" slime. After only a
		// few seconds, your arms have transformed into formless slimy
		// tentacles."
		// + " You desperately try to move your new limbs, and as you do so,
		// they suddenly re-shape and form back into human-looking arms, the
		// only difference being"
		// + " that they are now made of
		// "+getSkinColour(SkinType.SLIME).getName()+" slime."
		// :GenericSentence.parseTextForGenderReplacement(this,
		// " All the way up to <her> shoulder, <her> flesh starts to melt,
		// turning into "+getSkinColour(SkinType.SLIME).getName()+" slime."
		// + " After only a few seconds, <her> arms have transformed into
		// formless slimy tentacles. <She> desperately wriggles <her> new limbs
		// about, and as <she> does so,"
		// + " they suddenly re-shape and form back into human-looking arms, the
		// only difference being"
		// + " that they are now made of
		// "+getSkinColour(SkinType.SLIME).getName()+" slime."));
		// break;
		default:
			transformationSB.append(isPlayer()
					? " Your arms shift and change into " + type.getDeterminer(this) + " " + type.getName(this) + "." + "</br>" + "You now have <b style='color:" + Colour.TRANSFORMATION_LESSER + ";'>" + type.getDeterminer(this) + " "
							+ type.getName(this) + "</b>."
					: UtilText.genderParsing(this, " <Her> arms shift and change into " + type.getDeterminer(this) + " " + type.getName(this) + "." + "</br>" + "<She> now has <b style='color:"
							+ Colour.TRANSFORMATION_LESSER + ";'>" + type.getDeterminer(this) + " " + type.getName(this) + "</b>."));
		}
		body.getArm().setType(type);
		return transformationSB.toString() + "</br></br>" + postTransformationCalculation() + "</p>";
	}

	public String getArmName() {
		return body.getArm().getName(this);
	}

	public String getArmNameSingular() {
		return body.getArm().getType().getNameSingular(this);
	}

	public String getArmName(boolean withDescriptor) {
		return body.getArm().getName(this, withDescriptor);
	}

	public String getArmDescriptor() {
		return body.getArm().getType().getDescriptor(this);
	}

	public String getArmDeterminer() {
		return body.getArm().getType().getDeterminer(this);
	}

	public String getArmPronoun() {
		return body.getArm().getType().getPronoun();
	}

	// Ass:

	public AssType getAssType() {
		return body.getAss().getType();
	}

	/**
	 * @return Formatted description of asshole change. Setting ass type only
	 *         affects asshole, as the ass shape is determined by body type.
	 */
	public String setAssType(AssType type) {
		if (type == getAssType()) {
			if(isPlayer()) {
				return "<p style='text-align:center;'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>You already have the ass of a [pc.assRace], so nothing happens...</span>"
					+ "</p>";
			} else {
				return "<p style='text-align:center;'>"
						+ UtilText.parse(this,
								"<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] already has the ass of [npc.a_assRace], so nothing happens...</span>")
					+ "</p>";
			}
			
		} else {
			if (isPlayer()) {
				transformationSB = new StringBuilder("<p>"
						+ "Your asshole starts to noticeably soften and become very sensitive, and you let out a lewd moan as it starts to transform."
						+ " You pant and sigh as you feel your back door shifting into a new form, and as the transformation comes to an end, you give your rear entrance an experimental clench.");
			} else {
				transformationSB = new StringBuilder(UtilText.parse(this,
						"[npc.Name] lets out a lewd moan as [npc.her] asshole starts to transform."
						+ " [npc.She] pants and sighs as [npc.her] back door shifts into a new form, and as the transformation comes to an end, [npc.she] gives [npc.her] rear entrance an experimental clench."));
			}
		}

		switch (type) {
		case HUMAN:
			if (isPlayer()) {
				transformationSB.append(" You discover that your asshole has turned back into a normal human-shaped one."
						+ " Like the rest of your body, your ass is still covered in [pc.assColour] [pc.assSkin], and you realise that the transformation was solely focused on your asshole."
						+ "</br>"
						+ "You now have a [style.boldSex(normal human asshole)]."
						+ "</p>");
			} else {
				transformationSB.append(UtilText.parse(this,
						" [npc.She] soon discovers that [npc.her] asshole has turned into a normal human-shaped one."
								+ " Like the rest of [npc.her] body, [npc.her] ass is still covered in [npc.assColour] [npc.assSkin], as the transformation was solely focused on [npc.her] asshole."
								+ "</br>"
								+ "[npc.She] now has a [style.boldSex(normal human asshole)]."
								+ "</p>"));
			}
			break;
		case DEMON_COMMON:
			if (isPlayer()) {
				transformationSB.append(" You discover that your asshole has turned into a pussy-like orifice, and you take a sharp intake of breath as you feel that it's now lined with a series of gripping muscles;"
							+ " perfectly formed to milk and squeeze any cock that finds its way inside."
						+ " Like the rest of your body, your ass is still covered in [pc.assColour] [pc.assSkin], and you realise that the transformation was solely focused on your asshole."
						+ "</br>"
						+ "You now have a [style.boldSex(demonic pussy-like asshole)]."
						+ "</p>");
			} else {
				transformationSB.append(UtilText.parse(this,
						" [npc.She] soon discovers that [npc.her] asshole has turned into a pussy-like orifice, and [npc.she] takes a sharp intake of breath as [npc.she] feels that it's now lined with a series of gripping muscles;"
									+ " perfectly formed to milk and squeeze any cock that finds its way inside."
								+ " Like the rest of [npc.her] body, [npc.her] ass is still covered in [npc.assColour] [npc.assSkin], as the transformation was solely focused on [npc.her] asshole."
								+ "</br>"
								+ "[npc.She] now has a [style.boldSex(demonic pussy-like asshole)]."
								+ "</p>"));
			}
			break;
		case DOG_MORPH:
			if (isPlayer()) {
				transformationSB.append(" You discover that your asshole has shifted shape slightly, although it feels very similar to a normal human-shaped one."
						+ " Like the rest of your body, your ass is still covered in [pc.assColour] [pc.assSkin], and you realise that the transformation was solely focused on your asshole."
						+ "</br>"
						+ "You now have a [style.boldSex(canine asshole)]."
						+ "</p>");
			} else {
				transformationSB.append(UtilText.parse(this,
						" [npc.She] soon discovers that [npc.her] asshole has shifted shape slightly, although it looks to be very similar to a normal human-shaped one."
								+ " Like the rest of [npc.her] body, [npc.her] ass is still covered in [npc.assColour] [npc.assSkin], as the transformation was solely focused on [npc.her] asshole."
								+ "</br>"
								+ "[npc.She] now has a [style.boldSex(canine asshole)]."
								+ "</p>"));
			}
			break;
		case WOLF_MORPH:
			if (isPlayer()) {
				transformationSB.append(" You discover that your asshole has shifted shape slightly, although it feels very similar to a normal human-shaped one."
						+ " Like the rest of your body, your ass is still covered in [pc.assColour] [pc.assSkin], and you realise that the transformation was solely focused on your asshole."
						+ "</br>"
						+ "You now have a [style.boldSex(lupine asshole)]."
						+ "</p>");
			} else {
				transformationSB.append(UtilText.parse(this,
						" [npc.She] soon discovers that [npc.her] asshole has shifted shape slightly, although it looks to be very similar to a normal human-shaped one."
								+ " Like the rest of [npc.her] body, [npc.her] ass is still covered in [npc.assColour] [npc.assSkin], as the transformation was solely focused on [npc.her] asshole."
								+ "</br>"
								+ "[npc.She] now has a [style.boldSex(lupine asshole)]."
								+ "</p>"));
			}
			break;
		case CAT_MORPH:
			if (isPlayer()) {
				transformationSB.append(" You discover that your asshole has shifted shape slightly, although it feels very similar to a normal human-shaped one."
						+ " Like the rest of your body, your ass is still covered in [pc.assColour] [pc.assSkin], and you realise that the transformation was solely focused on your asshole."
						+ "</br>"
						+ "You now have a [style.boldSex(feline asshole)]."
						+ "</p>");
			} else {
				transformationSB.append(UtilText.parse(this,
						" [npc.She] soon discovers that [npc.her] asshole has shifted shape slightly, although it looks to be very similar to a normal human-shaped one."
								+ " Like the rest of [npc.her] body, [npc.her] ass is still covered in [npc.assColour] [npc.assSkin], as the transformation was solely focused on [npc.her] asshole."
								+ "</br>"
								+ "[npc.She] now has a [style.boldSex(feline asshole)]."
								+ "</p>"));
			}
			break;
		case HORSE_MORPH:
			if (isPlayer()) {
				transformationSB.append(" You discover that the rim of your asshole has puffed up and grown larger, and you take a sharp intake of breath as you realise that your black-skinned anus now looks like one you'd find on a horse."
						+ " Like the rest of your body, your ass is still covered in [pc.assColour] [pc.assSkin], and you realise that the transformation was solely focused on your asshole."
						+ "</br>"
						+ "You now have a [style.boldSex(puffy horse-like asshole)]."
						+ "</p>");
			} else {
				transformationSB.append(UtilText.parse(this,
						" [npc.She] soon discovers that the rim of [npc.her] asshole has puffed up and grown larger,"
							+ " and [npc.she] takes a sharp intake of breath as [npc.she] realises that [npc.her] black-skinned anus now looks like one found on a horse."
						+ " Like the rest of [npc.her] body, [npc.her] ass is still covered in [npc.assColour] [npc.assSkin], as the transformation was solely focused on [npc.her] asshole."
						+ "</br>"
						+ "[npc.She] now has a [style.boldSex(puffy horse-like asshole)]."
						+ "</p>"));
			}
			break;
		case HARPY:
			if (isPlayer()) {
				transformationSB.append(" You discover that your asshole has shifted shape slightly, although it feels very similar to a normal human-shaped one."
						+ " Like the rest of your body, your ass is still covered in [pc.assColour] [pc.assSkin], and you realise that the transformation was solely focused on your asshole."
						+ "</br>"
						+ "You now have an [style.boldSex(avian asshole)]."
						+ "</p>");
			} else {
				transformationSB.append(UtilText.parse(this,
						" [npc.She] soon discovers that [npc.her] asshole has shifted shape slightly, although it looks to be very similar to a normal human-shaped one."
								+ " Like the rest of [npc.her] body, [npc.her] ass is still covered in [npc.assColour] [npc.assSkin], as the transformation was solely focused on [npc.her] asshole."
								+ "</br>"
								+ "[npc.She] now has an [style.boldSex(avian asshole)]."
								+ "</p>"));
			}
			break;
		// case SLIME:
		// transformationSB.append(isPlayer()
		// ?" You let out a startled cry as you feel your ass start to sag and
		// melt as your flesh turns into slime!"
		// + " You desperately concentrate on trying to stop the transformation,
		// and as you do so, you feel your ass re-form into a regular-shaped
		// rear end."
		// + " You sigh with relief; your ass has managed to stay intact, but
		// it's now made out of "+getSkinColour(SkinType.SLIME).getName()+"
		// slime!"
		// :GenericSentence.parseTextForGenderReplacement(this,
		// " <She> lets out a startled cry as <her> ass starts to sag and melt
		// as <her> flesh turns into slime!"
		// + " <She> scrunches her eyes shut, desperately trying to will the
		// transformation to stop, and as <she> does so, <her> ass re-forms into
		// a regular-shaped rear end."
		// + " <She> opens <her> eyes and sighs with relief; <her> ass has
		// managed to stay intact, but it's now made out of
		// "+getSkinColour(SkinType.SLIME).getName()+" slime!"));
		// break;

		default:
			transformationSB.append(isPlayer()
					? "Your ass shifts and changes into " + type.getDeterminer(this) + " " + type.getName(this) + "." + "</br>" + "You now have <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + type.getDeterminer(this) + " "
							+ type.getName(this) + "</b>."
					: UtilText.genderParsing(this, "<Her> ass shifts and changes into " + type.getDeterminer(this) + " " + type.getName(this) + "." + "</br>" + " <She> now has <b style='color:"
							+ Colour.TRANSFORMATION_SEXUAL + ";'>" + type.getDeterminer(this) + " " + type.getName(this) + "</b>."));
		}
		
		body.getAss().setType(type);
		return transformationSB.toString()
				+ "<p>"
				+ postTransformationCalculation(false)
				+ "</p>";
	}

	public String getAssName() {
		return body.getAss().getName(this);
	}

	public String getAssName(boolean withDescriptor) {
		return body.getAss().getName(this, withDescriptor);
	}

	public String getAssDescriptor() {
		return body.getAss().getType().getDescriptor(this);
	}
	
	public String getAssholeName(boolean withDescriptor) {
		return body.getAss().getAssholeName(this, withDescriptor);
	}

	public String getAssholeDescriptor() {
		return body.getAss().getAssholeDescriptor(this);
	}

	public String getAssDeterminer() {
		return body.getAss().getType().getDeterminer(this);
	}

	public String getAssPronoun() {
		return body.getAss().getType().getPronoun();
	}

	public String getAssDescription() {
		return body.getAssDescription(this);
	}

	public boolean isAssVirgin() {
		return body.getAss().isVirgin();
	}

	public void setAssVirgin(boolean virgin) {
		body.getAss().setVirgin(virgin);
	}

	public boolean isAssBleached() {
		return body.getAss().isBleached();
	}

	public void setAssBleached(boolean bleached) {
		body.getAss().setBleached(bleached);
	}
	
//	public boolean isAssCreampied(){
//		return body.getAss().isCreampied();
//	}
//
//	public void setAssCreampied(boolean creampied){
//		body.getAss().setCreampied(creampied);
//	}

	// Capacity:
	public Capacity getAssCapacity() {
		return body.getAss().getCapacity();
	}

	public float getAssRawCapacityValue() {
		return body.getAss().getRawCapacityValue();
	}
	
	public float getAssStretchedCapacity() {
		return body.getAss().getStretchedCapacity();
	}

	public void setAssStretchedCapacity(float capacity){
		body.getAss().setStretchedCapacity(capacity);
	}
	public void incrementAssStretchedCapacity(float increment){
		body.getAss().setStretchedCapacity(getAssStretchedCapacity() + increment);
	}

	/**
	 * @return Formatted description of capacity change.
	 */
	public String setAssCapacity(float capacity) {
		
		if (getAssRawCapacityValue() < capacity) {
			if (body.getAss().setCapacity(capacity))
				if (isPlayer())
					return "<p>" + "You let out a gasp as you feel your asshole dilate and stretch out with a mind of its own." + "</br>" + "From being stretched, <b style='color:" + Colour.TRANSFORMATION_SEXUAL
							+ ";'>your asshole is now " + getAssCapacity().getDescriptor() + "</b>!" + "</p>";
				else
					return UtilText.genderParsing(this, "<p>" + "<She> lets out a gasp as <her> asshole dilates and stretches out with a mind of its own." + "</br>" + "From being stretched, <b style='color:"
							+ Colour.TRANSFORMATION_SEXUAL + ";'><her> asshole is now " + getAssCapacity().getDescriptor() + "</b>!" + "</p>");
		} else {
			if (body.getAss().setCapacity(capacity))
				if (isPlayer())
					return "<p>" + "You let out a cry as you feel your asshole uncontrollably tighten and clench." + "</br>" + "From tightening up, <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>your asshole is now "
							+ getAssCapacity().getDescriptor() + "</b>!" + "</p>";
				else
					return UtilText.genderParsing(this, "<p>" + "<She> lets out a cry as <her> asshole uncontrollably tightens and clenches." + "</br>" + "From tightening up, <b style='color:"
							+ Colour.TRANSFORMATION_SEXUAL + ";'><her> asshole is now " + getAssCapacity().getDescriptor() + "</b>!" + "</p>");
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY + ";'>Your [pc.asshole]'s capacity doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The capacity of [npc.name]'s [pc.asshole] doesn't change...</span>")
				+ "</p>";
		}
	}

	public String incrementAssCapacity(float increment) {
		return setAssCapacity(getAssRawCapacityValue() + increment);
	}

	// Wetness:
	public Wetness getAssWetness() {
		return body.getAss().getWetness();
	}

	/**
	 * @return Formatted description of wetness change.
	 */
	public String setAssWetness(int wetness) {
		if (getAssWetness().getValue() < wetness) {
			if (body.getAss().setWetness(wetness)) {
				if (isPlayer())
					return "<p>" + "Your eyes widen as you feel moisture beading around your asshole, and you let out a lewd moan as you realise that your rear entrance is lubricating itself." + "</br>"
							+ "After the transformation comes to an end, <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>your asshole is now " + getAssWetness().getDescriptor() + "</b>." + "</p>";
				else
					return UtilText.genderParsing(this, "<p>" + "<Her> eyes widen as moisture beads around <her> asshole, and <she> lets out a lewd moan as <she> realises that <her> rear entrance is lubricating itself."
							+ "</br>" + "After the transformation comes to an end, <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'><her> asshole is now " + getAssWetness().getDescriptor() + "</b>." + "</p>");
			}
		} else {
			if (body.getAss().setWetness(wetness)) {
				if (isPlayer())
					return "<p>" + "You fidget around uncomfortably as you feel your asshole drying up." + "</br>" + "After the transformation comes to an end, <b style='color:" + Colour.TRANSFORMATION_SEXUAL
							+ ";'>your asshole is now " + getAssWetness().getDescriptor() + "</b>." + "</p>";
				else
					return UtilText.genderParsing(this, "<p>" + "<She> fidgets around uncomfortably as <she> feels <her> asshole drying up." + "</br>" + "After the transformation comes to an end, <b style='color:"
							+ Colour.TRANSFORMATION_SEXUAL + ";'><her> asshole is now " + getAssWetness().getDescriptor() + "</b>." + "</p>");
			}
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY + ";'>Your [pc.asshole]'s wetness doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The wetness of [npc.name]'s [npc.asshole] doesn't change...</span>")
				+ "</p>";
		}
	}

	public String incrementAssWetness(int increment) {
		return setAssWetness(getAssWetness().getValue() + increment);
	}

	// Ass size:
	public AssSize getAssSize() {
		return body.getAss().getAssSize();
	}

	/**
	 * @return Formatted description of size change.
	 */
	public String setAssSize(int size) {
		if (getAssSize().getValue() < size) {
			if (body.getAss().setAssSize(size))
				if (isPlayer())
					return "</p>" + "You feel a little off-balance as your rear end suddenly seems to get heavier, and as you give it an experimental shake, you find that your ass has definitely grown larger." + "</br>"
							+ "You now have a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + getAssSize().getDescriptor() + " ass</b>!" + "</p>";
				else
					return UtilText.genderParsing(this,
							"</p>" + "<She> looks to be a little off-balance as <her> rear end suddenly seems to get larger, and as <she> gives it an experimental shake, <she> discovers that it's definitely grown bigger and heavier." + "</br>"
									+ "<She> now has a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + getAssSize().getDescriptor() + " ass</b>!" + "</p>");
		} else {
			if (body.getAss().setAssSize(size))
				if (isPlayer())
					return "<p>" + "You suddenly feel like a weight has been lifted from your rear end, and after giving it an experimental shake, you find that your ass has shrunk." + "</br>" + "You now have a <b style='color:"
							+ Colour.TRANSFORMATION_SEXUAL + ";'>" + getAssSize().getDescriptor() + " ass</b>!" + "</p>";
				else
					return UtilText.genderParsing(this, "<p>" + "<She> looks like a weight has been lifted from <her> rear end, and after giving it an experimental shake, <she> discovers that <her> ass has shrunk."
							+ "</br>" + "<She> now has a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + getAssSize().getDescriptor() + " ass</b>!" + "</p>");
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY + ";'>The size of your ass doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The size of [npc.name]'s ass doesn't change...</span>")
				+ "</p>";
		}
	}

	public String incrementAssSize(int increment) {
		return setAssSize(getAssSize().getValue() + increment);
	}

	// Hip size:
	public HipSize getHipSize() {
		return body.getAss().getHipSize();
	}

	/**
	 * @return Formatted description of size change.
	 */
	public String setHipSize(int size) {
		if (getHipSize().getValue() < size) {
			if (body.getAss().setHipSize(size)) {
				if (isPlayer())
					return "<p>" + "You inhale sharply in surprise as you feel your hips push out and reshape themselves, growing wider right before your eyes." + "</br>" + "You now have <b style='color:"
							+ Colour.TRANSFORMATION_SEXUAL + ";'>" + getHipSize().getDescriptor() + " hips</b>!" + "</p>";
				else
					return UtilText.genderParsing(this, "<p>" + "<She> inhales sharply in surprise as <her> hips push out and reshape themselves, growing wider right before <her> eyes." + "</br>"
							+ "<She> now has <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + getHipSize().getDescriptor() + " hips</b>!" + "</p>");
			}
		} else {
			if (body.getAss().setHipSize(size)) {
				if (isPlayer())
					return "<p>" + "You inhale sharply in surprise as you feel your hips collapse inwards and reshape themselves, narrowing down right before your eyes." + "</br>" + "You now have <b style='color:"
							+ Colour.TRANSFORMATION_SEXUAL + ";'>" + getHipSize().getDescriptor() + " hips</b>!" + "</p>";
				else
					return UtilText.genderParsing(this, "<p>" + "<She> inhales sharply in surprise as <her> hips collapse inwards and reshape themselves, narrowing down right before <her> eyes." + "</br>"
							+ "<She> now has <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + getHipSize().getDescriptor() + " hips</b>!" + "</p>");
			}
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY + ";'>The size of your hips doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The size of [npc.name]'s hips doesn't change...</span>")
				+ "</p>";
		}
	}

	public String incrementHipSize(int increment) {
		return setHipSize(getHipSize().getValue() + increment);
	}

	// Ass elasticity:
	public OrificeElasticity getAssElasticity() {
		return body.getAss().getElasticity();
	}

	public String setAssElasticity(int elasticity) {
		if (getAssElasticity().getValue() < elasticity) {
			if (body.getAss().setElasticity(elasticity)) {
				if (isPlayer())
					return "</p>"
								+ "You let out a little gasp as you feel a strange slackening sensation pulsating deep within your asshole."
								+ "</br>"
								+ "Your <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>asshole is now " + getAssElasticity().getDescriptor() + "</b>!"
							+ "</p>";
				else
					return UtilText.genderParsing(this, 
							"</p>"
								+ "<She> lets out a little gasp as a strange slackening sensation pulsates deep within <her> asshole."
								+ "</br>"
								+ "<Her> <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>asshole is now " + getAssElasticity().getDescriptor() + "</b>!"
							+ "</p>");
			}
		} else {
			if (body.getAss().setElasticity(elasticity)) {
				if (isPlayer())
					return "</p>"
								+ "You let out a little gasp as you feel a strange clenching sensation pulsating deep within your asshole."
								+ "</br>"
								+ "Your <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>asshole is now " + getAssElasticity().getDescriptor() + "</b>!"
							+ "</p>";
				else
					return UtilText.genderParsing(this,
							"</p>"
								+ "<She> lets out a little gasp as a strange clenching sensation pulsates deep within <her> asshole."
								+ "</br>"
								+ "<Her> <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>asshole is now " + getAssElasticity().getDescriptor() + "</b>!"
							+ "</p>");
			}
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY + ";'>Your [pc.asshole]'s elasticity doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The elasticity of [npc.name]'s [pc.asshole] doesn't change...</span>")
				+ "</p>";
		}
	}

	public String incrementAssElasticity(int increment) {
		return setAssElasticity(getAssElasticity().getValue() + increment);
	}

	// Breasts:

	public BreastType getBreastType() {
		return body.getBreast().getType();
	}
	
	/**
	 * A character is defined as having breasts if thier breasts are AA-cups or higher.
	 */
	public boolean hasBreasts() {
		return body.getBreast().getRawSizeValue()>=CupSize.AA.getMeasurement();
	}

	/**
	 * @return Formatted description of nipple change. Breasts always have a
	 *         shape related to the character's body, but nipples can freely
	 *         change.
	 */
	public String setBreastType(BreastType type) {
		if (type == getBreastType())
			if(isPlayer()) { 
				return "<p style='text-align:center;'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>You already have the breasts of a [pc.breastRace], so nothing happens...</span>"
					+ "</p>";
			} else {
				return "<p style='text-align:center;'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] already has the breasts of a [npc.breastRace], so nothing happens...</span>")
					+ "</p>";
			}
		
		else {
			if (isPlayer())
				transformationSB = new StringBuilder("<p>"
						+ "Your " + (getBreastRawSizeValue() > 0 ? "breasts suddenly feel" : "chest suddenly feels") + " extremely soft and sensitive, and you let out a cry as "
						+ (getBreastRawSizeValue() > 0 ? "they start to transform." : "it starts to transform."));
			else
				transformationSB = new StringBuilder(
						UtilText.parse(this, "<p>[npc.Name] lets out a cry as [npc.her] " + (getBreastRawSizeValue() > 0 ? "breasts start" : "chest starts") + " to transform."));
		}
		//TODO these descriptions are placeholders
		switch (type) {
			case HUMAN:
				if (isPlayer()) {
					transformationSB.append(
							" Your nipples shift into normal human-like ones, and as your areolae finally stop tingling, you're left panting and sweating from the intense transformation."
							+ " Like the rest of your body, your chest is still covered in [pc.breastsColour] [pc.breastsSkin], and you realise that the transformation was solely focused on your nipples."
							+ "</br>"
							+ "You now have [style.boldSex(human nipples)], and when lactating, you will produce [style.boldSex(human milk)]."
							+ "</p>");
				} else {
					transformationSB.append(UtilText.parse(this,
							" [npc.Her] nipples shift into normal human-like ones, and by the time the transformation comes to an end, [npc.she]'s left panting and sweating."
								+ " Like the rest of [npc.her] body, [npc.her] chest is still covered in [npc.breastsColour] [npc.breastsSkin], as the transformation was solely focused on [npc.her] nipples."
								+ "</br>"
								+ "[npc.She] now has [style.boldSex(human nipples)], and when lactating, [npc.she] will produce [style.boldSex(human milk)]."
								+ "</p>"));
				}
				break;
			case DEMON_COMMON:
				if (isPlayer()) {
					transformationSB.append(
							" Your nipples swell and stretch, and you find yourself desperately moaning as they change into little pseudo-pussies."
							+ " As the transformation comes to an end, a slimy milk-like liquid briefly drools out from your new nipples, signalling that they want to be fucked."
							+ " Like the rest of your body, your chest is still covered in [pc.breastsColour] [pc.breastsSkin], and you realise that the transformation was solely focused on your nipples."
							+ "</br>"
							+ "You now have [style.boldSex(demonic pseudo-pussies in place of nipples)], and when lactating, you will produce [style.boldSex(demonic milk)]."
							+ "</p>");
				} else {
					transformationSB.append(UtilText.parse(this,
							" [npc.Her] nipples swell and stretch, and [npc.she] starts desperately moaning as they change into little pseudo-pussies."
								+ " As the transformation comes to an end, a slimy milk-like liquid briefly drools out from [npc.her] new nipples, signalling that they want to be fucked."
								+ "</br>"
								+ "[npc.She] now has [style.boldSex(demonic pseudo-pussies in place of nipples)], and when lactating, [npc.she] will produce [style.boldSex(demonic milk)]."
								+ "</p>"));
				}
				break;
			case DOG_MORPH:
				if (isPlayer()) {
					transformationSB.append(
							" Your nipples shift and change shape to look like regular human ones, but despite their common appearance, you can feel a hidden change taking place deep within your chest."
							+ " As your areolae finally stop tingling, a little drop of milk beads out from your teats, revealing the main effect of your transformation."
							+ " Like the rest of your body, your chest is still covered in [pc.breastsColour] [pc.breastsSkin], and you realise that the transformation was solely focused on your nipples."
							+ "</br>"
							+ "You now have [style.boldSex(canine nipples)], and when lactating, you will produce [style.boldSex(canine milk)]."
							+ "</p>");
				} else {
					transformationSB.append(UtilText.parse(this,
							" [npc.Her] nipples shift and change shape to look like regular human ones, but despite their common appearance, a hidden change takes place deep within [npc.her] chest."
							+ " As [npc.she] feels [npc.her] areolae stop tingling, a little drop of milk beads out from [npc.her] teats, revealing the main effect of the transformation."
								+ "</br>"
								+ "[npc.She] now has [style.boldSex(canine nipples)], and when lactating, [npc.she] will produce [style.boldSex(canine milk)]."
								+ "</p>"));
				}
				break;
			case WOLF_MORPH:
				if (isPlayer()) {
					transformationSB.append(
							" Your nipples shift and change shape to look like regular human ones, but despite their common appearance, you can feel a hidden change taking place deep within your chest."
							+ " As your areolae finally stop tingling, a little drop of milk beads out from your teats, revealing the main effect of your transformation."
							+ " Like the rest of your body, your chest is still covered in [pc.breastsColour] [pc.breastsSkin], and you realise that the transformation was solely focused on your nipples."
							+ "</br>"
							+ "You now have [style.boldSex(lupine nipples)], and when lactating, you will produce [style.boldSex(lupine milk)]."
							+ "</p>");
				} else {
					transformationSB.append(UtilText.parse(this,
							" [npc.Her] nipples shift and change shape to look like regular human ones, but despite their common appearance, a hidden change takes place deep within [npc.her] chest."
							+ " As [npc.she] feels [npc.her] areolae stop tingling, a little drop of milk beads out from [npc.her] teats, revealing the main effect of the transformation."
								+ "</br>"
								+ "[npc.She] now has [style.boldSex(lupine nipples)], and when lactating, [npc.she] will produce [style.boldSex(lupine milk)]."
								+ "</p>"));
				}
				break;
			case CAT_MORPH:
				if (isPlayer()) {
					transformationSB.append(
							" Your nipples shift and change shape to look like regular human ones, but despite their common appearance, you can feel a hidden change taking place deep within your chest."
							+ " As your areolae finally stop tingling, a little drop of milk beads out from your teats, revealing the main effect of your transformation."
							+ " Like the rest of your body, your chest is still covered in [pc.breastsColour] [pc.breastsSkin], and you realise that the transformation was solely focused on your nipples."
							+ "</br>"
							+ "You now have [style.boldSex(feline nipples)], and when lactating, you will produce [style.boldSex(feline milk)]."
							+ "</p>");
				} else {
					transformationSB.append(UtilText.parse(this,
							" [npc.Her] nipples shift and change shape to look like regular human ones, but despite their common appearance, a hidden change takes place deep within [npc.her] chest."
							+ " As [npc.she] feels [npc.her] areolae stop tingling, a little drop of milk beads out from [npc.her] teats, revealing the main effect of the transformation."
								+ "</br>"
								+ "[npc.She] now has [style.boldSex(feline nipples)], and when lactating, [npc.she] will produce [style.boldSex(feline milk)]."
								+ "</p>"));
				}
				break;
			case HORSE_MORPH:
				if (isPlayer()) {
					transformationSB.append(
							" Your nipples shift and change shape to look like regular human ones, but despite their common appearance, you can feel a hidden change taking place deep within your chest."
							+ " As your areolae finally stop tingling, a little drop of milk beads out from your teats, revealing the main effect of your transformation."
							+ " Like the rest of your body, your chest is still covered in [pc.breastsColour] [pc.breastsSkin], and you realise that the transformation was solely focused on your nipples."
							+ "</br>"
							+ "You now have [style.boldSex(equine nipples)], and when lactating, you will produce [style.boldSex(equine milk)]."
							+ "</p>");
				} else {
					transformationSB.append(UtilText.parse(this,
							" [npc.Her] nipples shift and change shape to look like regular human ones, but despite their common appearance, a hidden change takes place deep within [npc.her] chest."
							+ " As [npc.she] feels [npc.her] areolae stop tingling, a little drop of milk beads out from [npc.her] teats, revealing the main effect of the transformation."
								+ "</br>"
								+ "[npc.She] now has [style.boldSex(equine nipples)], and when lactating, [npc.she] will produce [style.boldSex(equine milk)]."
								+ "</p>"));
				}
				break;
			case HARPY:
				if (isPlayer()) {
					transformationSB.append(
							" Your nipples shift and change shape to look like regular human ones, but despite their common appearance, you can feel a hidden change taking place deep within your chest."
							+ " As your areolae finally stop tingling, a little drop of milk beads out from your teats, revealing the main effect of your transformation."
							+ " Like the rest of your body, your chest is still covered in [pc.breastsColour] [pc.breastsSkin], and you realise that the transformation was solely focused on your nipples."
							+ "</br>"
							+ "You now have [style.boldSex(avian nipples)], and when lactating, you will produce [style.boldSex(avian milk)]."
							+ "</p>");
				} else {
					transformationSB.append(UtilText.parse(this,
							" [npc.Her] nipples shift and change shape to look like regular human ones, but despite their common appearance, a hidden change takes place deep within [npc.her] chest."
							+ " As [npc.she] feels [npc.her] areolae stop tingling, a little drop of milk beads out from [npc.her] teats, revealing the main effect of the transformation."
								+ "</br>"
								+ "[npc.She] now has [style.boldSex(avian nipples)], and when lactating, [npc.she] will produce [style.boldSex(avian milk)]."
								+ "</p>"));
				}
				break;
			// case SLIME:
			// transformationSB.append(isPlayer()
			// ?" Your moan turns to a startled cry as your nipples start to melt
			// into slime and drool down your chest."
			// + " You frantically try to regain control your body, and as you do
			// so, your drooling nipples form up into human-looking ones, although
			// now they're made out of "
			// +getSkinColour(SkinType.SLIME).getName()+" slime!"
			// :GenericSentence.parseTextForGenderReplacement(this,
			// " <Her> moans turn to startled cries as <her> nipples start to melt
			// into slime and drool down <her> chest."
			// + " As <she> frantically tries to regain control <her> body, <her>
			// drooling nipples form up into human-looking ones, although now
			// they're made out of "
			// +getSkinColour(SkinType.SLIME).getName()+" slime!"));
			// break;
	
			default:
				transformationSB.append(isPlayer()
						? "Your chest shifts and changes into " + type.getDeterminer(this) + " " + type.getName(this) + "." + "</br>" + "You now have <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + type.getName(this) + "</b>."
						: UtilText.genderParsing(this, "<Her> chest shifts and changes into " + type.getDeterminer(this) + " " + type.getName(this) + "." + "</br>" + "<She> now has <b style='color:"
								+ Colour.TRANSFORMATION_SEXUAL + ";'>" + type.getName(this) + "</b>."));
		}
		body.getBreast().setType(type);
		return transformationSB.toString()
				+ "<p>"
				+ postTransformationCalculation(false)
				+ "</p>";
	}

	public String getBreastName() {
		return body.getBreast().getName(this);
	}
	
	public String getBreastNameSingular() {
		return body.getBreast().getType().getNameSingular(this);
	}

	public String getBreastName(boolean withDescriptor) {
		return body.getBreast().getName(this, withDescriptor);
	}

	public String getBreastDescriptor() {
		return body.getBreast().getType().getDescriptor(this);
	}
	
	public String getNippleDeterminer() {
		return body.getBreast().getDeterminer(this);
	}
	
	public String getNippleName() {
		return body.getBreast().getNippleName(false);
	}
	
	public String getNippleNameSingular() {
		return body.getBreast().getNippleNameSingular();
	}
	
	public String getNippleName(boolean withDescriptor) {
		return body.getBreast().getNippleName(withDescriptor);
	}

	public String getNippleDescriptor() {
		return body.getBreast().getNippleDescriptor();
	}

	public String getBreastDeterminer() {
		return body.getBreast().getDeterminer(this);
	}

	public String getBreastPronoun() {
		return body.getBreast().getType().getPronoun();
	}

	public String getBreastDescription() {
		return body.getBreastDescription(this);
	}

	public boolean isBreastVirgin() {
		return body.getBreast().isVirgin();
	}

	public void setBreastVirgin(boolean virgin) {
		body.getBreast().setVirgin(virgin);
	}

	public int getBreastRows() {
		return body.getBreast().getRows();
	}

	/**
	 * @return Formatted description of number of breasts change.
	 */
	public String setBreastRows(int rows) {
		String transformation = "";
		
		if(rows<=0) {
			rows = 1;
		} else if (rows>3) {
			rows=3;
		}
		
		if (rows < getBreastRows()) {
			if (isPlayer())
				transformation = "<p>" + "You feel a strange bubbling sensation within your " + (getBreastRawSizeValue() > 0 ? "breasts" : "chest") + ", and before you have time to react, "
						+ (getBreastRows() == 3 ? (rows == 2 ? "the lowest of your extra pairs" : "your two extra pairs") : "your extra pair") + " of " + (getBreastRawSizeValue() > 0 ? "breasts" : "nipples") + " rapidly shrink away into the "
						+ getSkinType().getName(this) + " of your torso." + "</br>" 
						+ "You now have <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + Util.intToString(rows) + " pair"
						+ (rows > 1 ? "s" : "") + " of " + (getBreastRawSizeValue() > 0 ? "breasts" : "nipples") + "</b>." + "</p>";
			else
				transformation = UtilText.genderParsing(this,
						"<p>" + "<She> glances down at <her> " + (getBreastRawSizeValue() > 0 ? "breasts" : "chest") + ", and before <she> has time to react, <her> "
								+ (getBreastRows() == 3 ? (rows == 2 ? "the lowest of <her> extra pairs" : "<her> two extra pairs") : "<her> extra pair") + " of " + (getBreastRawSizeValue() > 0 ? "breasts" : "nipples")
								+ " rapidly shrink away into the " + getSkinType().getName(this) + " of <her> torso." + "</br>" + "<She> now has <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>"
								+ Util.intToString(rows) + " pair" + (rows > 1 ? "s" : "") + " of " + (getBreastRawSizeValue() > 0 ? "breasts" : "nipples") + "</b>." + "</p>");

		} else if (rows > getBreastRows()) {
			if (isPlayer())
				transformation = "<p>" + "You feel a strange bubbling sensation within your " + (getBreastRawSizeValue() > 0 ? "breasts" : "chest") + ", and before you have time to react, "
						+ (getBreastRows() == 1 ? (rows == 3 ? "two extra pairs" : "an extra pair") : "an extra pair") + " of " + (getBreastRawSizeValue() > 0 ? "breasts" : "nipples") + " rapidly grow out of the " + getSkinType().getName(this)
						+ " of your lower torso." + "</br>" + "You now have <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + Util.intToString(rows) + " pair" + (rows > 1 ? "s" : "") + " of "
						+ (getBreastRawSizeValue() > 0 ? "breasts" : "nipples") + "</b>." + "</p>";
			else
				transformation = UtilText.genderParsing(this,
						"<p>" + "<She> glances down at <her> " + (getBreastRawSizeValue() > 0 ? "breasts" : "chest") + ", and before <she> has time to react, "
								+ (getBreastRows() == 1 ? (rows == 3 ? "two extra pairs" : "an extra pair") : "an extra pair") + " of " + (getBreastRawSizeValue() > 0 ? "breasts" : "nipples") + " rapidly grow out of the " + getSkinType().getName(this)
								+ " of <her> lower torso." + "</br>" + "<She> now has <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + Util.intToString(rows) + " pair" + (rows > 1 ? "s" : "")
								+ " of " + (getBreastRawSizeValue() > 0 ? "breasts" : "nipples") + "</b>." + "</p>");

		} else {
			
			if(isPlayer()) {
				return "<p class='center'>"
							+ "<span style='color:" + Colour.TEXT_GREY + ";'>The amount of breast rows you currently have doesn't change...</span>"
						+ "</p>";
			} else {
				return "<p class='center'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The amount of breast rows that [npc.name] currently has doesn't change...</span>")
					+ "</p>";
			}
		}

		body.getBreast().setRows(rows);

		return transformation;
	}

	public String incrementBreastRows(int increment) {
		return setBreastRows(getBreastRows() + increment);
	}

	public boolean isBreastFuckable() {
		return body.getBreast().isFuckable();
	}

	public String getMilkName() {
		return body.getBreast().getMilkName();
	}
	
//	public boolean isBreastCreampied(){
//		return body.getBreast().isCreampied();
//	}
//
//	public void setBreastCreampied(boolean creampied){
//		body.getBreast().setCreampied(creampied);
//	}

	// Capacity:
	public Capacity getBreastCapacity() {
		return body.getBreast().getCapacity();
	}

	public float getBreastRawCapacityValue() {
		return body.getBreast().getRawCapacityValue();
	}
	
	public float getBreastStretchedCapacity() {
		return body.getBreast().getStretchedCapacity();
	}
	
	public void setBreastStretchedCapacity(float capacity){
		body.getBreast().setStretchedCapacity(capacity);
	}
	public void incrementBreastStretchedCapacity(float increment){
		body.getBreast().setStretchedCapacity(getBreastStretchedCapacity() + increment);
	}

	/**
	 * @return Formatted description of breast capacity change.
	 */
	public String setBreastCapacity(float capacity) {
		if (getBreastRawCapacityValue() < capacity) {
			if (getBreastRawCapacityValue() == 0) {
				if (body.getBreast().setCapacity(capacity)) {
					if (isPlayer())
						return "<p>" + "You squirm about uncomfortably as your nipples start to get unusually hard and sensitive." + " A strange pressure starts to build up in your " + (getBreastRawSizeValue() > 0 ? "breasts" : "chest")
								+ ", and you let out a groan as you feel a drastic transformation taking place deep within your " + (getBreastRawSizeValue() > 0 ? "mammaries" : "torso") + "."
								+ " You can't help but let out a lewd scream as your nipples suddenly spread open, revealing a deep, fuckable passage that's just formed behind them." + "</br>" + "You now have <b style='color:"
								+ Colour.TRANSFORMATION_SEXUAL + ";'>fuckable nipples</b>!" + "</p>";
					else
						return UtilText.genderParsing(this,
								"<p>" + "<She> squirms about uncomfortably as <her> nipples start to get unusually hard and sensitive." + " A strange pressure starts to build up in <her> " + (getBreastRawSizeValue() > 0 ? "breasts" : "chest")
										+ ", and <she> lets out a groan as a drastic transformation takes deep within <her> " + (getBreastRawSizeValue() > 0 ? "mammaries" : "torso") + "."
										+ " <She> suddenly lets out a lewd scream as <her> nipples spread open, revealing a deep, fuckable passage that's just formed behind them." + "</br>" + "<She> now has <b style='color:"
										+ Colour.TRANSFORMATION_SEXUAL + ";'>fuckable nipples</b>!" + "</p>");
				}
			}

			if (body.getBreast().setCapacity(capacity)) {
				if (isPlayer())
					return "<p>" + "You let out a lewd moan as you feel a familiar pressure building up behind your fuckable nipples."
							+ " After just a few moments, you feel the transformation coming to an end, and you realise that your nipple-cunts have grown both wider and deeper." + "</br>" + "Your <b style='color:"
							+ Colour.TRANSFORMATION_SEXUAL + ";'>fuckable nipples' capacity has increased</b>!" + "</p>";
				else
					return UtilText.genderParsing(this,
							"<p>" + "<She> lets out a lewd moan as a familiar pressure builds up behind <her> fuckable nipples."
									+ " After just a few moments the transformation stops, leaving <her> nipple-cunts both wider and deeper than they used to be." + "</br>" + "<Her> <b style='color:" + Colour.TRANSFORMATION_SEXUAL
									+ ";'>fuckable nipples' capacity has increased</b>!" + "</p>");
			}
		} else {
			if (capacity == 0) {
				if (body.getBreast().setCapacity(capacity)) {
					if (isPlayer())
						return "<p>" + "You squirm about uncomfortably as your nipples start to get unusually hard and sensitive." + " A strange pressure starts to build up in your " + (getBreastRawSizeValue() > 0 ? "breasts" : "chest")
								+ ", and you let out a groan as you feel a drastic transformation taking place deep within your " + (getBreastRawSizeValue() > 0 ? "mammaries" : "torso") + "."
								+ " You can't help but let out a disappointed sigh as your nipples tighten and transform into non-fuckable ones." + "</br>" + "You now have <b style='color:" + Colour.TRANSFORMATION_SEXUAL
								+ ";'>non-fuckable nipples</b>!" + "</p>";
					else
						return UtilText.genderParsing(this,
								"<p>" + "<She> squirms about uncomfortably as <her> nipples start to get unusually hard and sensitive." + " A strange pressure starts to build up in <her> " + (getBreastRawSizeValue() > 0 ? "breasts" : "chest")
										+ ", and <she> lets out a groan as a drastic transformation takes deep within <her> " + (getBreastRawSizeValue() > 0 ? "mammaries" : "torso") + "."
										+ " <She> lets out a disappointed sigh as <her> nipples tighten and transform into non-fuckable ones." + "</br>" + "<She> now has <b style='color:" + Colour.TRANSFORMATION_SEXUAL
										+ ";'>non-fuckable nipples</b>!" + "</p>");
				}
			}
			if (body.getBreast().setCapacity(capacity)) {
				if (isPlayer())
					return "<p>" + "You let out a lewd moan as you feel a familiar pressure building up behind your fuckable nipples."
							+ " After just a few moments, you feel the transformation coming to an end, and you realise that your nipple-cunts have tightened up." + "</br>" + "Your <b style='color:" + Colour.TRANSFORMATION_SEXUAL
							+ ";'>fuckable nipples' capacity has decreased</b>!" + "</p>";
				else
					return UtilText.genderParsing(this,
							"<p>" + "<She> lets out a lewd moan as a familiar pressure builds up behind <her> fuckable nipples." + " After just a few moments the transformation stops, leaving <her> nipple-cunts tighter than they used to be."
									+ "</br>" + "<Her> <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>fuckable nipples' capacity has decreased</b>!" + "</p>");
			}
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY + ";'>The capacity of your nipples doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The capacity of [npc.name]'s nipples doesn't change...</span>")
				+ "</p>";
		}
	}

	public String incrementBreastCapacity(float increment) {
		return setBreastCapacity(getBreastRawCapacityValue() + increment);
	}

	// Lactation:
	public Lactation getBreastLactation() {
		return body.getBreast().getLactation();
	}

	public int getBreastRawLactationValue() {
		return body.getBreast().getRawLactationValue();
	}

	/**
	 * @return Formatted description of lactation amount change.
	 */
	public String setBreastLactation(int lactation) {
		if (getBreastRawLactationValue() < lactation) {
			if (body.getBreast().setLactation(lactation))
				if (isPlayer())
					return "<p>" + "You feel a strange bubbling and churning taking place deep within your " + (getBreastRawSizeValue() > 0 ? "breasts" : "chest") + ", and you let out a little gasp as a few drops of " + getMilkName()
							+ " suddenly leak from your nipples." + "</br>" + "You are now producing  <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + getBreastLactation().getDescriptor() + " " + getMilkName() + "</b>!"
							+ "</p>";
				else
					return UtilText.genderParsing(this,
							"<p>" + "<She> squirms and moans as <she> looks down at <her> " + (getBreastRawSizeValue() > 0 ? "breasts" : "chest") + ", and <she> lets out a little gasp as a few drops of " + getMilkName()
									+ " suddenly leak from <her> nipples." + "</br>" + "<She> is now producing <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + getBreastLactation().getDescriptor() + " " + getMilkName()
									+ "</b>!" + "</p>");
		} else {
				if (body.getBreast().setLactation(lactation))
					if (isPlayer())
						return "<p>" + "You feel a light bubbling sensation taking place deep within your " + (getBreastRawSizeValue() > 0 ? "breasts" : "chest") + ", and you let out a little sigh as you feel your " + getMilkName()
								+ " production drying up." + "</br>" + "You are now producing  <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + getBreastLactation().getDescriptor() + " " + getMilkName() + "</b>!"
								+ "</p>";
					else
						return UtilText.genderParsing(this,
								"<p>" + "<She> squirms and moans as <she> looks down at <her> " + (getBreastRawSizeValue() > 0 ? "breasts" : "chest") + ", and <she> lets out a little sigh as <she> feels <her> " + getMilkName()
										+ " production drying up." + "</br>" + "<She> is now producing <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + getBreastLactation().getDescriptor() + " " + getMilkName() + "</b>!"
										+ "</p>");
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY + ";'>The amount of [pc.milk] that you're producing doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The amount of [npc.milk] that [npc.name] is producing doesn't change...</span>")
				+ "</p>";
		}
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

	/**
	 * @return Formatted description of breast size change.
	 */
	public String setBreastSize(int size) {
		if (getBreastRawSizeValue() < size) {
			if (body.getBreast().setSize(size))
				if (isPlayer())
					return "<p>" + "You feel a tingling heat spread quickly through your " + (getBreastRawSizeValue() > 0 ? "breasts" : "chest") + ", and you can't help but let out a desperate moan as your "
							+ (getBreastRawSizeValue() > 0 ? "mammaries swell up and grow larger." : "chest swells up, and before you know what's happening, a pair of breasts have materialised on your previously-flat torso.") + "</br>"
							+ "You now have <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + getBreastSize().getDescriptor() + (getBreastSize() != CupSize.TRAINING ? ", " + getBreastSize().getCupSizeName() + "-cup" : " ")
							+ " breasts</b>!" + "</p>";
				else
					return UtilText.genderParsing(this,
							"<p>" + "A tingling heat spreads quickly through <her> " + (getBreastRawSizeValue() > 0 ? "breasts" : "chest") + ", and <she> lets out a desperate moan as <her> "
									+ (getBreastRawSizeValue() > 0 ? "mammaries swell up and grow larger." : "chest swells up, and before <she> knows what's happening, a pair of breasts have materialised on <her> previously-flat torso.") + "</br>"
									+ "<She> now has <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + getBreastSize().getDescriptor()
									+ (getBreastSize() != CupSize.TRAINING ? ", " + getBreastSize().getCupSizeName() + "-cup" : " ") + " breasts</b>!" + "</p>");
		} else {
			if (body.getBreast().setSize(size))
				if (isPlayer())
					return "<p>" + "You feel a tingling heat spread quickly through your breasts, and you can't help but let out a frustrated groan as your mammaries shrink down and get smaller." + "</br>"
							+ (getBreastSize() == CupSize.FLAT ? "You now have <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>a completely flat chest</b>!"
									: "You now have <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + getBreastSize().getDescriptor()
											+ (getBreastSize() != CupSize.TRAINING ? ", " + getBreastSize().getCupSizeName() + "-cup" : " ") + " breasts</b>!")
							+ "</p>";
				else
					return UtilText.genderParsing(this,
							"<p>" + "A tingling heat spreads quickly through <her> breasts, and <she> lets out a frustrated groan as <her> mammaries shrink down and get smaller." + "</br>"
									+ (getBreastSize() == CupSize.FLAT ? "<She> now has <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>a completely flat chest</b>!"
											: "<She> now has <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + getBreastSize().getDescriptor()
													+ (getBreastSize() != CupSize.TRAINING ? ", " + getBreastSize().getCupSizeName() + "-cup" : " ") + " breasts</b>!")
									+ "</p>");
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY + ";'>The size of your [pc.breasts] doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The size of [npc.name]'s [npc.breasts] doesn't change...</span>")
				+ "</p>";
		}
	}

	public String incrementBreastSize(int increment) {
		return setBreastSize(getBreastRawSizeValue() + increment);
	}

	// Breast elasticity:
	public OrificeElasticity getBreastElasticity() {
		return body.getBreast().getElasticity();
	}

	public String setBreastElasticity(int elasticity) {
		if (getBreastElasticity().getValue() < elasticity) {
			if (body.getBreast().setElasticity(elasticity))
				if (isPlayer())
					return "</p>"
								+ "You let out a lewd moan as you feel a strange slackening sensation pulsating just behind your nipples."
								+ "</br>"
								+ "Your <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>nipples are now " + getBreastElasticity().getDescriptor() + "</b>!"
							+ "</p>";
				else
					return UtilText.genderParsing(this,
							"</p>"
								+ "<She> lets out a lewd moan as a strange slackening sensation pulsates just behind <her> nipples."
								+ "</br>"
								+ "<Her> <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>nipples are now " + getBreastElasticity().getDescriptor() + "</b>!"
							+ "</p>");
		} else {
			if (body.getBreast().setElasticity(elasticity))
				if (isPlayer())
					return "</p>"
								+ "You let out a lewd moan as you feel a strange clenching sensation pulsating just behind your nipples."
								+ "</br>"
								+ "Your <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>nipples are now " + getBreastElasticity().getDescriptor() + "</b>!"
							+ "</p>";
				else
					return UtilText.genderParsing(this,
							"</p>"
								+ "<She> lets out a lewd moan as a strange clenching sensation pulsates just behind <her> nipples."
								+ "</br>"
								+ "<Her> <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>nipples are now " + getBreastElasticity().getDescriptor() + "</b>!"
							+ "</p>");
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY + ";'>The elasticity of your nipples doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The elasticity of [npc.name]'s nipples doesn't change...</span>")
				+ "</p>";
		}
	}

	public String incrementBreastElasticity(int increment) {
		return setBreastElasticity(getBreastElasticity().getValue() + increment);
	}

	// Hair:

	public BodyCoveringType getHairType() {
		return body.getHair().getType();
	}

	/**
	 * @return Formatted description of hair type change.
	 */
	public String setHairType(BodyCoveringType type) {
		if (type == getHairType()) {
			if(isPlayer()) {
				return "<p style='text-align:center;'>"
							+ "<span style='color:" + Colour.TEXT_GREY + ";'>You already have the [pc.hair] of a [pc.hairRace], so nothing happens...</span>"
						+ "</p>";
			} else {
				return "<p style='text-align:center;'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] already has the [npc.hair] of a [npc.hairRace], so nothing happens...</span>")
					+ "</p>";
			}
			
		} else {
			if (isPlayer())
				transformationSB = new StringBuilder("<p>" + "Your scalp tingles and itches, and you rub the top of your head as you feel your " + getHairName() + " start to transform.");
			else
				transformationSB = new StringBuilder(UtilText.genderParsing(this, "<p>" + getName("The") + " rubs the top of <her> head as <her> " + getHairName() + " starts to transform."));
		}

		switch (type) {
		case HAIR_HUMAN:
			transformationSB.append(isPlayer()
					? " The feeling goes away almost as quickly as it came, leaving you with human-like " + getHairColour(BodyCoveringType.HAIR_HUMAN).getName() + " hair." + "</br>" + "You now have <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>human hair</b>."
					: UtilText.genderParsing(this, " The transformation is over in a matter of seconds, leaving <herPro> with human-like " + getHairColour(BodyCoveringType.HAIR_HUMAN).getName() + " hair." + "</br>"
							+ "<She> now has <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>human hair</b>."));
			break;
		case HAIR_CANINE_FUR:
			transformationSB.append(isPlayer()
					? " The feeling goes away almost as quickly as it came, leaving you with a dog-morph's " + getHairColour(BodyCoveringType.HAIR_CANINE_FUR).getName() + ", fur-like hair." + "</br>" + "You now have <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>furry dog-like hair</b>."
					: UtilText.genderParsing(this, " The transformation is over in a matter of seconds, leaving <herPro> with a dog-morph's " + getHairColour(BodyCoveringType.HAIR_CANINE_FUR).getName() + ", fur-like hair." + "</br>"
							+ "<She> now has <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>furry dog-like hair</b>."));
			break;
		case HAIR_LYCAN_FUR:
			transformationSB.append(isPlayer()
					? " The feeling goes away almost as quickly as it came, leaving you with a wolf-morph's " + getHairColour(BodyCoveringType.HAIR_LYCAN_FUR).getName() + ", fur-like hair." + "</br>" + "You now have <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>furry wolf-like hair</b>."
					: UtilText.genderParsing(this, " The transformation is over in a matter of seconds, leaving <herPro> with a wolf-morph's " + getHairColour(BodyCoveringType.HAIR_LYCAN_FUR).getName() + ", fur-like hair." + "</br>"
							+ "<She> nwo has <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>furry wolf-like hair</b>."));
			break;
		case HAIR_FELINE_FUR:
			transformationSB.append(isPlayer()
					? " The feeling goes away almost as quickly as it came, leaving you with a cat-morph's " + getHairColour(BodyCoveringType.HAIR_FELINE_FUR).getName() + ", fur-like hair." + "</br>" + "You now have <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>furry cat-like hair</b>."
					: UtilText.genderParsing(this, " The transformation is over in a matter of seconds, leaving <herPro> with a cat-morph's " + getHairColour(BodyCoveringType.HAIR_FELINE_FUR).getName() + ", fur-like hair." + "</br>"
							+ "<She> now has <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>furry cat-like hair</b>."));
			break;
		case HAIR_HORSE_HAIR:
			transformationSB.append(isPlayer()
					? " The feeling goes away almost as quickly as it came, leaving you with a horse-morph's coarse " + getHairColour(BodyCoveringType.HAIR_HORSE_HAIR).getName() + " hair." + "</br>" + "You now have <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>coarse horse-like hair</b>."
					: UtilText.genderParsing(this, " The transformation is over in a matter of seconds, leaving <herPro> with a horse-morph's coarse " + getHairColour(BodyCoveringType.HAIR_FELINE_FUR).getName() + " hair." + "</br>"
							+ "<She> now has <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>coarse horse-like hair</b>."));
			break;
		case HAIR_HARPY:
			transformationSB.append(isPlayer()
					? " The feeling quickly grows mroe and more intense, and you gasp in shock as a plume of attractive " + getHairColour(BodyCoveringType.HAIR_HARPY).getName() + " feathers quickly sprout from the top of your head." + "</br>"
							+ "You now have a <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>plume of harpy feathers</b>."
					: UtilText.genderParsing(this, " <She> gasps in shock as a plume of attractive " + getHairColour(BodyCoveringType.HAIR_HARPY).getName() + " feathers quickly sprout from the top of <her> head." + "</br>"
							+ "<She> now has a <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>plume of harpy feathers</b>."));
			break;
		// case SLIME:
		// transformationSB.append(isPlayer()
		// ?" Your "+getHairName()+" clumps together and starts to melt into
		// thick slime. After a few seconds, all of your "+getHairName()+" has
		// been turned into "+getHairColour(HairType.SLIME).getName()+" slime."
		// :GenericSentence.parseTextForGenderReplacement(this,
		// " <Her> "+getHairName()+" clumps together and starts to melt into
		// thick slime. After a few seconds, all of <her> "+getHairName()+" has
		// been turned into "+getHairColour(HairType.SLIME).getName()+"
		// slime."));
		// break;
		default:
			transformationSB.append(isPlayer()
					? "The " + getHairName() + " on your head shifts and changes into " + type.getDeterminer(this) + " " + type.getName(this) + "." + "</br>" + "You now have <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>"
							+ type.getName(this) + "</b>."
					: UtilText.genderParsing(this, "The " + getHairName() + " on <her> head shifts and changes into " + type.getDeterminer(this) + " " + type.getName(this) + "." + "</br>" + "<She> now has <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>" + type.getName(this) + "</b>."));
		}
		body.getHair().setType(type);
		return transformationSB.toString() + "</br></br>" + postTransformationCalculation() + "</p>";
	}

	public String getHairName() {
		return body.getHair().getName(this);
	}

	public String getHairName(boolean withDescriptor) {
		return body.getHair().getName(this, withDescriptor);
	}

	public String getHairDescriptor() {
		return body.getHair().getType().getDescriptor(this);
	}

	public String getHairDeterminer() {
		return body.getHair().getType().getDeterminer(this);
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

	/**
	 * @return Formatted description of hair length change.
	 */
	public String setHairLength(int length) {
		if (getHairRawLengthValue() < length) {
			if (body.getHair().setLength(length))
				if (isPlayer())
					return "<p>" + "Your scalp itches for a moment as your " + (getHairType() == BodyCoveringType.HAIR_HARPY ? "plume of feathers" : getHairName()) + " grows longer." + "</br>" + "You now have <b style='color:"
							+ Colour.TRANSFORMATION_GENERIC + ";'>" + getHairLength().getDescriptor() + " " + getHairName() + "</b>." + "</p>";
				else
					return UtilText.genderParsing(this, "<p>" + "<She> rubs at <her> head as <her> " + (getHairType() == BodyCoveringType.HAIR_HARPY ? "plume of feathers" : getHairName()) + " grows longer." + "</br>"
							+ "<She> now has <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>" + getHairLength().getDescriptor() + " " + getHairName() + "</b>." + "</p>");
		} else {
			if (body.getHair().setLength(length))
				if (isPlayer())
					return "<p>" + "Your scalp itches for a moment as your " + (getHairType() == BodyCoveringType.HAIR_HARPY ? "plume of feathers" : getHairName()) + " becomes shorter." + "</br>" + "You now have <b style='color:"
							+ Colour.TRANSFORMATION_GENERIC + ";'>" + getHairLength().getDescriptor() + " " + getHairName() + "</b>." + "</p>";
				else
					return UtilText.genderParsing(this, "<p>" + "<She> rubs at <her> head as <her> " + (getHairType() == BodyCoveringType.HAIR_HARPY ? "plume of feathers" : getHairName()) + " becomes shorter." + "</br>"
							+ "<She> now has <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>" + getHairLength().getDescriptor() + " " + getHairName() + "</b>." + "</p>");
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY + ";'>The length of your [pc.hair] doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The length of [npc.name]'s [npc.hair] doesn't change...</span>")
				+ "</p>";
		}
	}

	public String incrementHairLength(int increment) {
		return setHairLength(getHairRawLengthValue() + increment);
	}

	// Colour:
	public Colour getHairColour() {
		return body.getBodyCoveringTypeColours().get(body.getHair().getType());
	}

	public Colour getHairColour(BodyCoveringType hairType) {
		return body.getBodyCoveringTypeColours().get(hairType);
	}

	/**
	 * @return Formatted description of hair colour change.
	 */
	public String setHairColour(Colour shade) {
		if(body.getHair().getType().getAllColours().contains(shade)) {
			body.getBodyCoveringTypeColours().put(body.getHair().getType(), shade);
			if (isPlayer())
				return "<p>" + "You let out a little gasp as your " + (getHairType() == BodyCoveringType.HAIR_HARPY ? "plume of feathers" : getHairName()) + " changes colour." + "</br>" + "You now have <b style='color:"
						+ Colour.TRANSFORMATION_GENERIC + ";'>" + shade.getName() + " " + getHairName() + "</b>." + "</p>";
			else
				return UtilText.genderParsing(this, "<p>" + "<She> lets out a little gasp as <her> " + (getHairType() == BodyCoveringType.HAIR_HARPY ? "plume of feathers" : getHairName()) + " changes colour." + "</br>"
						+ "<She> now has <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>" + shade.getName() + " " + getHairName() + "</b>." + "</p>");
		}
		
		return "<p>" + "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>" + "</p>";
	}

	/**
	 * <b>WARNING:</b> This method might not make much sense from the returned
	 * description. It returns a description of the hairType changing colour,
	 * even if that character doesn't currently have that hairType.
	 * 
	 * @return Formatted description of hair colour change.
	 */
	public String setHairColour(BodyCoveringType hairType, Colour shade) {
		if(hairType.getAllColours().contains(shade)) {
			body.getBodyCoveringTypeColours().put(hairType, shade);
			if (isPlayer())
				return "<p>" + "You let out a little gasp as your " + (hairType == BodyCoveringType.HAIR_HARPY ? "plume of feathers" : hairType.getName(false, this)) + " changes colour." + "</br>" + "You now have <b style='color:"
						+ Colour.TRANSFORMATION_GENERIC + ";'>" + shade.getName() + " " + hairType.getName(false, this) + "</b>." + "</p>";
			else
				return UtilText.genderParsing(this, "<p>" + "<She> lets out a little gasp as <her> " + (hairType == BodyCoveringType.HAIR_HARPY ? "plume of feathers" : hairType.getName(false, this)) + " changes colour." + "</br>"
						+ "<She> now has <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>" + shade.getName() + " " + hairType.getName(false, this) + "</b>." + "</p>");
		}
		
		return "<p>" + "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>" + "</p>";
	}

	// Face:

	public FaceType getFaceType() {
		return body.getFace().getType();
	}

	/**
	 * @return Formatted description of face type change.
	 */
	public String setFaceType(FaceType type) {
		if (type == getFaceType()) {
			if(isPlayer()) {
				return "<p style='text-align:center;'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>You already have the [pc.face] of a [pc.faceRace], so nothing happens...</span>"
					+ "</p>";
			} else {
				return "<p style='text-align:center;'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] already has the [npc.face] of a [npc.faceRace], so nothing happens...</span>")
					+ "</p>";
			}
			
		} else {
			if (isPlayer())
				transformationSB = new StringBuilder("<p>" + "An intense tingling sensation suddenly sweeps across your face, and you scrunch up your eyes as you start to transform."
						+ " With an audible crunch, your facial bones start to restructure themselves, and although the feeling isn't painful, it's enough of a shock to cause you to let out an involuntary cry.");
			else
				transformationSB = new StringBuilder(UtilText.genderParsing(this,
						"<p>" + getName("The") + " scrunches up <her> eyes as <her> face starts to transform." + " <She> lets out a cry in shock as <her> facial bones crunch and start to restructure themselves."));
		}

		switch (type) {
		case HUMAN:
			transformationSB.append(isPlayer()
					? " Thankfully, the alarming feeling is over within a few moments, and you discover that you've been left with a normal human face, covered in " + getSkinColour(BodyCoveringType.HUMAN).getName() + " skin." + "</br>"
							+ "You now have a <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>human face</b>."
					: UtilText.genderParsing(this, " Thankfully for <herPro>, the transformation is over within a few moments, leaving <herPro> with a normal human face, covered in "
							+ getSkinColour(BodyCoveringType.HUMAN).getName() + " skin." + "</br>" + "<She> now has <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>human face</b>."));
			break;
		case DEMON_COMMON:
			transformationSB.append(isPlayer()
					? " Thankfully, the alarming feeling is over within a few moments, and you discover that you've been left with a new face, covered in flawless " + getSkinColour(BodyCoveringType.DEMON_COMMON).getName() + " skin."
							+ " Although it's similar to that of a human's, your new demonic face has a strange alluring quality to it, and is indescribably attractive to anyone who lays their eyes on it." + "</br>"
							+ "You now have a <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>demonic face</b>."
					: UtilText.genderParsing(this,
							" Thankfully for <herPro>, the transformation is over within a few moments, leaving <herPro> with an indescribably alluring human-like face, which is now covered in flawless "
									+ getSkinColour(BodyCoveringType.DEMON_COMMON).getName() + " skin." + "</br>" + "<She> now has a <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>demonic face</b>."));
			break;
		case DOG_MORPH:
			transformationSB.append(isPlayer()
					? " You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic dog-like muzzle, and your tongue flattens and grows wider, turning into a dog-like tongue." + " A layer of "
							+ getSkinColour(BodyCoveringType.CANINE_FUR).getName() + " fur quickly grows to cover your new face, and as the transformation ends, you realise that you've now got the face of a greater dog-morph." + "</br>"
							+ "You now have an <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>anthropomorphic dog-like face</b>."
					: UtilText.genderParsing(this,
							" <Her> nose and mouth twitch and transform as they push out into an anthropomorphic dog-like muzzle, and <her> tongue flattens and grows wider, turning into a dog-like tongue." + " A layer of "
									+ getSkinColour(BodyCoveringType.CANINE_FUR).getName() + " fur quickly grows to cover <her> new face, and as the transformation ends, <she>'s left with the face of a greater dog-morph." + "</br>"
									+ "<She> now has an <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>anthropomorphic dog-like face</b>."));
			break;
		case LYCAN:
			transformationSB.append(isPlayer()
					? " You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic wolf-like muzzle, and your tongue flattens and grows wider, turning into a wolf-like tongue." + " A layer of "
							+ getSkinColour(BodyCoveringType.LYCAN_FUR).getName() + " fur quickly grows to cover your new face, and as the transformation ends, you realise that you've now got the face of a greater wolf-morph." + "</br>"
							+ "You now have an <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>anthropomorphic wolf-like face</b>."
					: UtilText.genderParsing(this,
							" <Her> nose and mouth twitch and transform as they push out into an anthropomorphic wolf-like muzzle, and <her> tongue flattens and grows wider, turning into a wolf-like tongue." + " A layer of "
									+ getSkinColour(BodyCoveringType.LYCAN_FUR).getName() + " fur quickly grows to cover <her> new face, and as the transformation ends, <she>'s left with the face of a greater wolf-morph." + "</br>"
									+ "<She> now has an <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>anthropomorphic wolf-like face</b>."));
			break;
		case CAT_MORPH:
			transformationSB.append(isPlayer()
					? " You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic cat-like muzzle, and your tongue flattens and changes into a little pink cat-like tongue." + " A layer of "
							+ getSkinColour(BodyCoveringType.FELINE_FUR).getName() + " fur quickly grows to cover your new face, and as the transformation ends, you realise that you've now got the face of a greater cat-morph." + "</br>"
							+ "You now have an <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>anthropomorphic cat-like face</b>."
					: UtilText.genderParsing(this,
							" <Her> nose and mouth twitch and transform as they push out into an anthropomorphic cat-like muzzle, and <her> tongue flattens and changes into a little pink cat-like tongue." + " A layer of "
									+ getSkinColour(BodyCoveringType.FELINE_FUR).getName() + " fur quickly grows to cover <her> new face, and as the transformation ends, <she>'s left with the face of a greater cat-morph." + "</br>"
									+ "<She> now has an <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>anthropomorphic cat-like face</b>."));
			break;
		case HORSE_MORPH:
			transformationSB.append(isPlayer()
					? " You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic horse-like muzzle, and your tongue grows longer and stronger, turning into a horse-like tongue." + " A layer of short, "
							+ getSkinColour(BodyCoveringType.HORSE_HAIR).getName() + " horse-hair quickly grows to cover your new face, and as the transformation ends, you realise that you've now got the face of a greater horse-morph." + "</br>"
							+ "You now have an <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>anthropomorphic horse-like face</b>."
					: UtilText.genderParsing(this,
							" <Her> nose and mouth twitch and transform as they push out into an anthropomorphic horse-like muzzle, and <her> tongue grows longer and stronger, turning into a horse-like tongue." + " A layer of short, "
									+ getSkinColour(BodyCoveringType.HORSE_HAIR).getName() + " horse-hair quickly grows to cover <her> new face, and as the transformation ends, <she>'s left with the face of a greater horse-morph." + "</br>"
									+ "<She> now has an <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>anthropomorphic horse-like face</b>."));
			break;
		case HARPY:
			transformationSB.append(isPlayer()
					? " You feel your nose and mouth twitching and transforming as they fuse together and push out into a short beak, and your tongue grows longer and thinner, turning into a bird-like tongue." + " A layer of short, "
							+ getSkinColour(BodyCoveringType.FEATHERS).getName()
							+ " feathers quickly grow to cover your new face, and as the transformation ends, you realise that you've now got the face of a greater harpy."
							+ " You find, much to your relief, that you're able to harden or soften the edges of your beak at will, allowing you to portray facial emotions and wrap your beak's edges around anything you might want to put in your mouth."
							+ "</br>" + "You now have an <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>anthropomorphic bird-like harpy face</b>."
					: UtilText.genderParsing(this,
							" <Her> nose and mouth twitch and transform as they fuse together and push out into a short beak, and <her> tongue grows longer and thinner, turning into a bird-like tongue." + " A layer of short, "
									+ getSkinColour(BodyCoveringType.FEATHERS).getName() + " feathers quickly grow to cover <her> new face, and as the transformation ends, <she>'s left with the face of a greater harpy."
									+ " <She> soon discovers, much to <her> relief, that <she>'s able to harden or soften the edges of <her> beak at will, allowing <herPro> to portray facial emotions and wrap <her> beak's edges"
									+ " around anything <she> might want to put in <her> mouth." + "</br>" + "<She> now has an <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>anthropomorphic bird-like harpy face</b>."));
			break;
		// case SLIME:
		// transformationSB.append(isPlayer()
		// ?" You let out a startled cry as your facial bones start to melt! The
		// rest of your face soon follows, and the world goes dark as your
		// entire head melts into a mass of
		// "+getSkinColour(SkinType.SLIME).getName()+" slime."
		// + " You try not to panic, and concentrate with all your might to
		// re-form your head. After a couple of seconds, you succeed, and are
		// able to see again! You try to let out a sigh of relief,"
		// + " but instead all you manage to do is blow bubbles up through your
		// new slime head. Your eyes open wide as you struggle to stop yourself
		// from trying to scream. Instead, you concentrate extremely hard,"
		// + " and manage to form a slimy tongue and vocal cords in your new
		// throat. You try once more to let out a sigh of relief, and this time,
		// it works!"
		// + " You suppose that you'll just have to live with the fact that your
		// entire head is now made from morphable
		// "+getSkinColour(SkinType.SLIME).getName()+" slime."
		// :GenericSentence.parseTextForGenderReplacement(this,
		// " <She> lets out a startled cry as <her> facial bones start to melt!
		// The rest of <her> face soon follows, and soon <her> entire head has
		// melted into a mass of "+getSkinColour(SkinType.SLIME).getName()+"
		// slime."
		// + " After a couple of seconds, the slime re-forms into a human-like
		// face, and <she> lets out a sigh of relief."
		// + " <Her> entire head is now made from morphable
		// "+getSkinColour(SkinType.SLIME).getName()+" slime."));
		// break;
		default:
			transformationSB.append(isPlayer()
					? " Your face shifts and changes into " + type.getDeterminer(this) + " " + type.getName(this) + "." + "</br>" + "You now have <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>" + type.getDeterminer(this) + " "
							+ type.getName(this) + "</b>."
					: UtilText.genderParsing(this, "<Her> face shifts and changes into " + type.getDeterminer(this) + " " + type.getName(this) + "." + "</br>" + "<She> now has <b style='color:"
							+ Colour.TRANSFORMATION_GREATER + ";'>" + type.getDeterminer(this) + " " + type.getName(this) + "</b>."));
		}
		body.getFace().setType(type);
		return transformationSB.toString() + "</br></br>" + postTransformationCalculation() + "</p>";
	}

	public String getFaceName() {
		return body.getFace().getName(this);
	}

	public String getFaceName(boolean withDescriptor) {
		return body.getFace().getName(this, withDescriptor);
	}

	public String getFaceDescriptor() {
		return body.getFace().getType().getDescriptor(this);
	}

	public String getFaceDeterminer() {
		return body.getFace().getType().getDeterminer(this);
	}

	public String getFacePronoun() {
		return body.getFace().getType().getPronoun();
	}

	public MakeupLevel getFaceMakeupLevel() {
		return body.getFace().getMakeupLevel();
	}

	/**
	 * @return Formatted description of new level of makeup.
	 */
	public String setFaceMakeupLevel(int makeupLevel) {
		if (body.getFace().setMakeupLevel(makeupLevel))
			if (isPlayer())
				return "<p>" + "You now have <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>" + getFaceMakeupLevel().getDescriptor() + " makeup on.</b>" + "</p>";
			else
				return UtilText.genderParsing(this, "<p>" + "<She> now has <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>" + getFaceMakeupLevel().getDescriptor() + " makeup on.</b>" + "</p>");

		return "<p>" + "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>" + "</p>";
	}

	public String incrementFaceMakeupLevel(int increment) {
		return setFaceMakeupLevel(getFaceMakeupLevel().getValue() + increment);
	}

	public boolean isFaceVirgin() {
		return body.getFace().isVirgin();
	}

	public void setFaceVirgin(boolean virgin) {
		body.getFace().setVirgin(virgin);
	}

	public Capacity getFaceCapacity() {
		return body.getFace().getCapacity();
	}

	public float getFaceRawCapacityValue() {
		return body.getFace().getRawCapacityValue();
	}
	
	public float getFaceStretchedCapacity() {
		return body.getFace().getStretchedCapacity();
	}
	public void setFaceStretchedCapacity(float capacity){
		body.getFace().setStretchedCapacity(capacity);
	}
	public void incrementFaceStretchedCapacity(float increment){
		body.getFace().setStretchedCapacity(getFaceStretchedCapacity() + increment);
	}

	/**
	 * @return Formatted description of throat capacity change.
	 */
	public String setFaceCapacity(float capacity) {
		if (getFaceRawCapacityValue() < capacity) {
			if (body.getFace().setCapacity(capacity))
				if (isPlayer())
					return "<p>" + "You let out a gasp as you feel your throat relax and stretch out with a mind of its own." + "</br>" + "You can now comfortably <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>deep-throat "
							+ getFaceCapacity().getMaximumSizeComfortable().getDescriptor() + " cocks</b>!" + "</p>";
				else
					return UtilText.genderParsing(this, "<p>" + "<She> lets out a gasp as <her> throat relaxes and stretches out with a mind of its own." + "</br>" + "<She> can now comfortably <b style='color:"
							+ Colour.TRANSFORMATION_SEXUAL + ";'>deep-throat " + getFaceCapacity().getMaximumSizeComfortable().getDescriptor() + " cocks</b>!" + "</p>");
		} else {
			if (body.getFace().setCapacity(capacity))
				if (isPlayer())
					return "<p>" + "You let out a gasp as you feel your throat close up and tighten." + "</br>" + "You can now only <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>deep-throat "
							+ getFaceCapacity().getMaximumSizeComfortable().getDescriptor() + " cocks</b>." + "</p>";
				else
					return UtilText.genderParsing(this, "<p>" + "<She> lets out a gasp as <her> throat closes up and tightens." + "</br>" + "<She> can now only <b style='color:"
							+ Colour.TRANSFORMATION_SEXUAL + ";'>deep-throat " + getFaceCapacity().getMaximumSizeComfortable().getDescriptor() + " cocks</b>." + "</p>");
		}
		return "<p>" + "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>" + "</p>";
	}

	public String incrementFaceCapacity(float increment) {
		return setFaceCapacity(getFaceRawCapacityValue() + increment);
	}
	
	public OrificeElasticity getFaceElasticity() {
		return body.getFace().getElasticity();
	}

	public String setFaceElasticity(int elasticity) {
		if (getFaceElasticity().getValue() < elasticity) {
			if (body.getFace().setElasticity(elasticity))
				if (isPlayer())
					return "</p>"
								+ "You let out a little gasp as you feel a strange slackening sensation pulsating deep within your throat."
								+ "</br>"
								+ "Your <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>throat is now " + getAssElasticity().getDescriptor() + "</b>!"
							+ "</p>";
				else
					return UtilText.genderParsing(this, 
							"</p>"
								+ "<She> lets out a little gasp as a strange slackening sensation pulsates deep within <her> throat."
								+ "</br>"
								+ "<Her> <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>throat is now " + getAssElasticity().getDescriptor() + "</b>!"
							+ "</p>");
		} else {
			if (body.getFace().setElasticity(elasticity))
				if (isPlayer())
					return "</p>"
								+ "You let out a little gasp as you feel a strange clenching sensation pulsating deep within your throat."
								+ "</br>"
								+ "Your <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>throat is now " + getAssElasticity().getDescriptor() + "</b>!"
							+ "</p>";
				else
					return UtilText.genderParsing(this,
							"</p>"
								+ "<She> lets out a little gasp as a strange clenching sensation pulsates deep within <her> throat."
								+ "</br>"
								+ "<Her> <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>throat is now " + getAssElasticity().getDescriptor() + "</b>!"
							+ "</p>");
		}
		return "<p>"
					+ "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>"
				+ "</p>";
	}

	public String incrementFaceElasticity(int increment) {
		return setFaceElasticity(getFaceElasticity().getValue() + increment);
	}
	
	

	// Eye (part of Face):
	public BodyCoveringType getEyeType() {
		return body.getEye().getType();
	}

	/**
	 * @return Formatted description of eye change.
	 */
	public String setEyeType(BodyCoveringType type) {
		if (type == getEyeType()) {
			if(isPlayer()) {
				return "<p style='text-align:center;'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>You already have the eyes of a [pc.eyeRace], so nothing happens...</span>"
					+ "</p>";
			} else {
				return "<p style='text-align:center;'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] already has the [npc.eyes] of a [npc.eyeRace], so nothing happens...</span>")
					+ "</p>";
			}
		}
		
		transformationSB = new StringBuilder(isPlayer() ? "<p>" + "Your eyes suddenly grow hot and itchy, and you shut them tight, rubbing at them as they start to transform."
				: "<p>" + "<Her> eyes suddenly grow hot and itchy, and <she> shuts them tight, rubbing at them as they start to transform.");

		switch (type) {
			case EYE_HUMAN:
				transformationSB.append(isPlayer()
						? " By the time you open them again, they've changed into normal human eyes." + "</br>" + "You now have a pair of <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>" + getEyeColour(type).getName()
								+ " human eyes</b>."
						: UtilText.genderParsing(this, " By the time <she> opens them again, they've changed into normal human eyes." + "</br>" + "<She> now has a pair of <b style='color:"
								+ Colour.TRANSFORMATION_PARTIAL + ";'>" + getEyeColour(type).getName() + " human eyes</b>."));
				break;
			case EYE_DEMON_COMMON:
				transformationSB.append(isPlayer()
						? " Your pupils slim down and become narrow slits, while your irises shift to become slightly larger than those found on a human." + "</br>" + "You now have a pair of <b style='color:"
								+ Colour.TRANSFORMATION_PARTIAL + ";'>" + getEyeColour(type).getName() + ", snake-like demon eyes</b>."
						: UtilText.genderParsing(this, " <Her> pupils slim down and become narrow slits, while <her> irises shift to become slightly larger than those found on a human." + "</br>"
								+ "<She> now has a pair of <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>" + getEyeColour(type).getName() + ", snake-like demon eyes</b>."));
				break;
			case EYE_DOG_MORPH:
				transformationSB.append(isPlayer()
						? " Your irises grow to become slightly larger than those found on a human, while your pupils remain as little black circles in the centre." + "</br>" + "You now have a pair of anthropomorphic <b style='color:"
								+ Colour.TRANSFORMATION_PARTIAL + ";'>" + getEyeColour(type).getName() + ", dog-like eyes</b>."
						: UtilText.genderParsing(this, " <Her> irises grow to become slightly larger than those found on a human, while <her> pupils remain as little black circles in the centre." + "</br>"
								+ "<She> now has a pair of anthropomorphic <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>" + getEyeColour(type).getName() + ", dog-like eyes</b>."));
				break;
			case EYE_LYCAN:
				transformationSB.append(isPlayer()
						? " Your irises grow to become slightly larger than those found on a human, while your pupils remain as little black circles in the centre." + "</br>" + "You now have a pair of anthropomorphic <b style='color:"
								+ Colour.TRANSFORMATION_PARTIAL + ";'>" + getEyeColour(type).getName() + ", wolf-like eyes</b>."
						: UtilText.genderParsing(this, " <Her> irises grow to become slightly larger than those found on a human, while <her> pupils remain as little black circles in the centre." + "</br>"
								+ "<She> now has a pair of anthropomorphic <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>" + getEyeColour(type).getName() + ", wolf-like eyes</b>."));
				break;
			case EYE_FELINE:
				transformationSB.append(isPlayer()
						? " Your pupils slim down and become narrow slits, while your irises shift to become slightly larger than those found on a human." + "</br>" + "You now have a pair of anthropomorphic <b style='color:"
								+ Colour.TRANSFORMATION_PARTIAL + ";'>" + getEyeColour(type).getName() + ", cat-like eyes</b>."
						: UtilText.genderParsing(this, " <Her> pupils slim down and become narrow slits, while <her> irises shift to become slightly larger than those found on a human." + "</br>"
								+ "<She> now has a pair of anthropomorphic <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>" + getEyeColour(type).getName() + ", cat-like eyes</b>."));
				break;
			case EYE_HORSE_MORPH:
				transformationSB.append(isPlayer()
						? " Your pupils squash down and become slightly more horizontal, while your irises shift to become slightly larger than those found on a human." + "</br>" + "You now have a pair of anthropomorphic <b style='color:"
								+ Colour.TRANSFORMATION_PARTIAL + ";'>" + getEyeColour(type).getName() + ", horse-like eyes</b>."
						: UtilText.genderParsing(this, " <Her> pupils squash down and become slightly more horizontal, while <her> irises shift to become slightly larger than those found on a human." + "</br>"
								+ "<She> now has a pair of anthropomorphic <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>" + getEyeColour(type).getName() + ", horse-like eyes</b>."));
				break;
			case EYE_HARPY:
				transformationSB.append(isPlayer()
						? " Your irises shift to become slightly larger than those found on a human, and become a vivid shade of " + getEyeColour(type).getName() + "." + "</br>" + "You now have a pair of vividly-coloured <b style='color:"
								+ Colour.TRANSFORMATION_PARTIAL + ";'>harpy eyes</b>."
						: UtilText.genderParsing(this, " <Her> irises shift to become slightly larger than those found on a human, and become a vivid shade of " + getEyeColour(type).getName() + "." + "</br>"
								+ "<She> now has a pair of vividly-coloured <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>harpy eyes</b>."));
				break;
			default:
				transformationSB.append(isPlayer() ? "</br>" + "You now have " + getEyeDeterminer() + "<b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>" + getEyeName(true) + "</b>."
						: UtilText.genderParsing(this, "</br>" + "<She> now has " + getEyeDeterminer() + "<b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>" + getEyeName(true) + "</b>."));
				break;
		}
		body.getEye().setType(type);
		return transformationSB.toString() + "</br></br>" + postTransformationCalculation() + "</p>";
	}

	public String getEyeName() {
		return body.getEye().getName(this);
	}
	
	public String getEyeNameSingular() {
		return body.getEye().getType().getNameSingular(this);
	}

	public String getEyeName(boolean withDescriptor) {
		return body.getEye().getName(this, withDescriptor);
	}

	public String getEyeDescriptor() {
		return body.getEye().getType().getDescriptor(this);
	}

	public String getEyeDeterminer() {
		return body.getEye().getType().getDeterminer(this);
	}

	public String getEyePronoun() {
		return body.getEye().getType().getPronoun();
	}

	public Colour getEyeColour() {
		return body.getBodyCoveringTypeColours().get(body.getEye().getType());
	}

	public Colour getEyeColour(BodyCoveringType eyeType) {
		return body.getBodyCoveringTypeColours().get(eyeType);
	}

	public String setEyeColour(Colour shade) {
		return setEyeColour(getEyeType(), shade);
	}

	/**
	 * <b>WARNING:</b> This method might not make much sense from the returned
	 * description. It returns a description of the eyeType changing colour,
	 * even if that character doesn't currently have that eyeType.
	 * 
	 * @return Formatted description of eye colour change.
	 */
	public String setEyeColour(BodyCoveringType eyeType, Colour shade) {
		if(eyeType.getAllColours().contains(shade)) {
			body.getBodyCoveringTypeColours().put(eyeType, shade);
			
			if (isPlayer())
				return "<p>" + "Your vision goes blurry for a moment as your irises change colour." + "</br>" + "You now have <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>" + shade.getName() + " eyes</b>." + "</p>";
			else
				return UtilText.genderParsing(this,
						"<p>" + "<She> blinks a few times as <her> irises change colour." + "</br>" + "<She> now has <b style='color:" + Colour.TRANSFORMATION_GENERIC + ";'>" + shade.getName() + " eyes</b>." + "</p>");
		}
		
		return "<p>" + "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>" + "</p>";
	}

	// Ear (part of Face):
	public EarType getEarType() {
		return body.getEar().getType();
	}

	/**
	 * @return Formatted description of ear change.
	 */
	public String setEarType(EarType type) {
		if (type == getEarType()) {
			if(isPlayer()) {
				return "<p style='text-align:center;'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>You already have the [pc.ears] of a [pc.earRace], so nothing happens...</span>"
					+ "</p>";
			} else {
				return "<p style='text-align:center;'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] already has the [npc.ears] of a [npc.earRace], so nothing happens...</span>")
					+ "</p>";
			}
		}
		
		
		transformationSB = new StringBuilder(isPlayer() ? "<p>" + "Your ears start to involuntarily twitch and itch, and as you rub at them, you feel them start to transform."
				: "<p>" + "<Her> ears start to involuntarily twitch and itch, and as <she> rubs at them, they start to transform.");

		switch (type) {

		case HUMAN:
			transformationSB.append(isPlayer()
					? " Within moments, they've reverted back to being normal human ears, covered in a layer of " + getSkinColour(getSkinType()).getName() + " skin." + "</br>" + "You now have a pair of <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>human ears</b>."
					: UtilText.genderParsing(this, " Within moments, they've changed into normal human ears, covered in a layer of " + getSkinColour(getSkinType()).getName() + " skin." + "</br>"
							+ "<She> now has a pair of <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>human ears</b>."));
			break;
		case DEMON_COMMON:
			transformationSB.append(isPlayer()
					? " They quickly shift into looking like normal human ears, but the tops soon grow up into slight points, giving them an exotic look."
							+ " The skin that covers them ends up being the same colour as the rest of your body, being a shade of " + getSkinColour(getSkinType()).getName() + "." + "</br>" + "You now have a pair of <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>demon ears</b>."
					: UtilText.genderParsing(this,
							" They quickly shift into looking like normal human ears, but the tops soon grow up into slight points, giving them an exotic look."
									+ " The skin that covers them ends up being the same colour as the rest of <her> body, being a shade of " + getSkinColour(getSkinType()).getName() + "." + "</br>" + "<She> now has a pair of <b style='color:"
									+ Colour.TRANSFORMATION_PARTIAL + ";'>demon ears</b>."));
			break;
		case DOG_MORPH:
			transformationSB.append(isPlayer()
					? " They quickly grow into upright points, and shift to sit higher up on your head than a normal pair of human ears would." + " A layer of " + getSkinColour(BodyCoveringType.CANINE_FUR).getName()
							+ " fur grows to cover them, and as the transformation finishes, you discover that you can easily twitch your new dog-like ears back and forth." + "</br>"
							+ "You now have a pair of pointed, anthropomorphic <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>dog-like ears</b>."
					: UtilText.genderParsing(this,
							" They quickly grow into upright points, and shift to sit higher up on <her> head than a normal pair of human ears would." + " A layer of " + getSkinColour(BodyCoveringType.CANINE_FUR).getName()
									+ " fur grows to cover them, and as the transformation finishes, <she> experimentally twitches <her> new dog-like ears back and forth." + "</br>"
									+ "<She> now has a pair of pointed, anthropomorphic <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>dog-like ears</b>."));
			break;
		case LYCAN:
			transformationSB.append(isPlayer()
					? " They quickly grow into large, upright points, and shift to sit higher up on your head than a normal pair of human ears would." + " A layer of " + getSkinColour(BodyCoveringType.LYCAN_FUR).getName()
							+ " fur grows to cover them, and as the transformation finishes, you discover that you can easily twitch your new wolf-like ears back and forth." + "</br>" + "You now have a pair of large, anthropomorphic <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>wolf-like ears</b>."
					: UtilText.genderParsing(this,
							" They quickly grow into lrage, upright points, and shift to sit higher up on <her> head than a normal pair of human ears would." + " A layer of " + getSkinColour(BodyCoveringType.LYCAN_FUR).getName()
									+ " fur grows to cover them, and as the transformation finishes, <she> experimentally twitches <her> new wolf-like ears back and forth." + "</br>" + "<She> now has a pair of large, anthropomorphic <b style='color:"
									+ Colour.TRANSFORMATION_PARTIAL + ";'>wolf-like ears</b>."));
			break;
		case CAT_MORPH:
			transformationSB.append(isPlayer()
					? " They quickly grow into small, upright points, and shift to sit higher up on your head than a normal pair of human ears would." + " A layer of " + getSkinColour(BodyCoveringType.FELINE_FUR).getName()
							+ " fur grows to cover them, and as the transformation finishes, you discover that you can easily twitch your new cat-like ears back and forth." + "</br>"
							+ "You now have a pair of pointed, anthropomorphic <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>cat-like ears</b>."
					: UtilText.genderParsing(this,
							" They quickly grow into small, upright points, and shift to sit higher up on <her> head than a normal pair of human ears would." + " A layer of " + getSkinColour(BodyCoveringType.FELINE_FUR).getName()
									+ " fur grows to cover them, and as the transformation finishes, <she> experimentally twitches <her> new cat-like ears back and forth." + "</br>"
									+ "<She> now has a pair of pointed, anthropomorphic <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>cat-like ears</b>."));
			break;
		case HORSE_MORPH:
			transformationSB.append(isPlayer()
					? " They quickly grow into sturdy little upright points, and shift to sit higher up on your head than a normal pair of human ears would." + " A layer of short " + getSkinColour(BodyCoveringType.HORSE_HAIR).getName()
							+ " hair grows to cover them, and as the transformation finishes, you discover that you can easily twitch your new horse-like ears back and forth." + "</br>"
							+ "You now have a pair of pointed, anthropomorphic <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>horse-like ears</b>."
					: UtilText.genderParsing(this,
							" They quickly grow into sturdy little upright points, and shift to sit higher up on <her> head than a normal pair of human ears would." + " A layer of short " + getSkinColour(BodyCoveringType.HORSE_HAIR).getName()
									+ " hair grows to cover them, and as the transformation finishes, <she> experimentally twitches <her> new horse-like ears back and forth." + "</br>"
									+ "<She> now has a pair of pointed, anthropomorphic <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>horse-like ears</b>."));
			break;
		case HARPY:
			transformationSB.append(isPlayer()
					? " They quickly shrink down into little nubs, and shift to point slightly back and up." + " A layer of pretty " + getSkinColour(BodyCoveringType.FEATHERS).getName()
							+ " feathers quickly grow to cover them, and as the transformation finishes, you're left with a pair of feathered harpy ears." + "</br>" + "You now have a pair of anthropomorphic <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>feather-covered harpy ears</b>."
					: UtilText.genderParsing(this,
							" They quickly shrink down into little nubs, and shift to point slightly back and up." + " A layer of pretty " + getSkinColour(BodyCoveringType.FEATHERS).getName()
									+ " feathers quickly grow to cover them, and as the transformation finishes, <she>'s left with a pair of feathered harpy ears." + "</br>" + "<She> nwo has a pair of anthropomorphic <b style='color:"
									+ Colour.TRANSFORMATION_PARTIAL + ";'>feather-covered harpy ears</b>."));
			break;
		default:
			transformationSB.append(isPlayer() ? "</br>" + "You now have " + getEyeDeterminer() + "<b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>" + getEyeName(true) + "</b>."
					: UtilText.genderParsing(this, "</br>" + "<She> now has " + getEyeDeterminer() + "<b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>" + getEyeName(true) + "</b>."));
			break;
		}
		body.getEar().setType(type);
		return transformationSB.toString() + "</br></br>" + postTransformationCalculation() + "</p>";
	}

	public String getEarName() {
		return body.getEar().getName(this);
	}
	
	public String getEarNameSingular() {
		return body.getEar().getType().getNameSingular(this);
	}

	public String getEarName(boolean withDescriptor) {
		return body.getEar().getName(this, withDescriptor);
	}

	public String getEarDescriptor() {
		return body.getEar().getType().getDescriptor(this);
	}

	public String getEarDeterminer() {
		return body.getEar().getType().getDeterminer(this);
	}

	public String getEarPronoun() {
		return body.getEar().getType().getPronoun();
	}

	// Tongue (part of Face):
	public TongueType getTongueType() {
		return body.getFace().getTongue().getType();
	}
	
	public String getTongueName() {
		return body.getFace().getTongue().getName(this);
	}

	public String getTongueName(boolean withDescriptor) {
		return body.getFace().getTongue().getName(this, withDescriptor);
	}

	public String getTongueDescriptor() {
		return body.getFace().getTongue().getType().getDescriptor(this);
	}

	public String getTongueDeterminer() {
		return body.getFace().getTongue().getType().getDeterminer(this);
	}

	public String getTonguePronoun() {
		return body.getFace().getTongue().getType().getPronoun();
	}
	
	// Horn:

	public HornType getHornType() {
		return body.getHorn().getType();
	}
	
	public boolean hasHorns() {
		return body.getHorn().getType() != HornType.NONE;
	}

	/**
	 * @return Formatted description of horn change.
	 */
	public String setHornType(HornType hornType) {
		if (hornType == getHornType()) {
			if(isPlayer()) {
				return "<p style='text-align:center;'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>You already have the [pc.horns] of a [pc.hornRace], so nothing happens...</span>"
					+ "</p>";
			} else {
				return "<p style='text-align:center;'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] already has the [npc.horns] of a [npc.hornRace], so nothing happens...</span>")
					+ "</p>";
			}

		} if (getHornType() == HornType.NONE) {
			transformationSB = new StringBuilder(isPlayer() ? "<p>" + "You rub at your forehead as it starts to feel hot and sensitive, and as you do, something starts pushing out from under your " + getSkinType().getName(this) + "."
					: UtilText.genderParsing(this, "<p>" + getName("The") + " tries to look up as something starts to push out from under <her> " + getSkinType().getName(this) + "."));
		} else {
			transformationSB = new StringBuilder(isPlayer() ? "<p>" + "You feel an odd tingling sensation at the base of your " + getHornType().getName(this) + ", and you gasp as you feel them start to transform."
					: UtilText.genderParsing(this,
							"<p>" + getName("The") + " tries to look up as the " + getSkinType().getName(this) + " at the base of <her> " + getHornType().getName(this) + " starts to tingle and itch."));
		}

		switch (hornType) {
		case NONE:
			transformationSB.append(isPlayer()
					? " Chunks of your " + getHornType().getName(this) + " fall to the floor as they start to crumble away, and within moments, they've completely disappeared." + "</br>" + "You have <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>lost your " + getHornType().getName(this) + "</b>."
					: UtilText.genderParsing(this, " Chunks of <her> " + getHornType().getName(this) + " fall to the floor as they start to crumble away, and within moments, they've completely disappeared." + "</br>"
							+ "<She> has <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>lost <her> " + getHornType().getName(this) + "</b>."));
			break;
		case DEMON_COMMON_FEMALE:
			transformationSB.append(isPlayer()
					? " A pair of hard nubs push out from your upper forehead, and you gasp as you feel a pair of long, sleek horns pushing out to sweep back across the sides of your head." + "</br>" + "You now have a pair of <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>feminine-looking demon horns</b>."
					: UtilText.genderParsing(this, " A pair of hard nubs push out from <her> upper forehead, and <she> gasps as a pair of long, sleek horns push out to sweep back across the sides of <her> head."
							+ "</br>" + "<She> now has a pair of <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>feminine-looking demon horns</b>."));
			break;
		case DEMON_COMMON_MALE:
			transformationSB.append(isPlayer()
					? " A pair of hard nubs push out from your upper forehead, and you gasp as you feel a pair of short, ridged horns push out and slightly bend back over your head." + "</br>" + "You now have a pair of <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>masculine-looking demon horns</b>."
					: UtilText.genderParsing(this, " A pair of hard nubs push out from <her> upper forehead, and <she> gasps as a pair of short, ridged horns push out and slightly bend back over <her> head." + "</br>"
							+ "<She> now has a pair of <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>masculine-looking demon horns</b>."));
			break;
		default:
			transformationSB.append(isPlayer()
					? " " + Util.capitaliseSentence(hornType.getDeterminer(this)) + " " + hornType.getName(this) + " grow out from the top of your head." + "</br>" + "You now have <b style='color:" + Colour.TRANSFORMATION_PARTIAL
							+ ";'>" + hornType.getName(this) + "</b>."
					: UtilText.genderParsing(this, " " + Util.capitaliseSentence(hornType.getDeterminer(this)) + " " + hornType.getName(this) + " grow on top of <her> head." + "</br>" + "<She> nwo has <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>" + hornType.getName(this) + "</b>."));
		}

		body.getHorn().setType(hornType);
		return transformationSB.toString() + "</br></br>" + postTransformationCalculation() + "</p>";
	}

	public String getHornName() {
		return body.getHorn().getName(this);
	}
	
	public String getHornNameSingular() {
		return body.getHorn().getType().getNameSingular(this);
	}

	public String getHornName(boolean withDescriptor) {
		return body.getHorn().getName(this, withDescriptor);
	}

	public String getHornDescriptor() {
		return body.getHorn().getType().getDescriptor(this);
	}

	public String getHornDeterminer() {
		return body.getHorn().getType().getDeterminer(this);
	}

	public String getHornPronoun() {
		return body.getHorn().getType().getPronoun();
	}

	// Legs:

	public LegType getLegType() {
		return body.getLeg().getType();
	}

	/**
	 * @return Formatted description of leg change.
	 */
	public String setLegType(LegType type) {
		if (type == getLegType()) {
			if(isPlayer()) {
				return "<p style='text-align:center;'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>You already have the [pc.legs] of a [pc.legRace], so nothing happens...</span>"
					+ "</p>";
			} else {
				return "<p style='text-align:center;'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] already has the [npc.legs] of a [npc.legRace], so nothing happens...</span>")
					+ "</p>";
			}
		}
		
		if(isPlayer()) {
			transformationSB = new StringBuilder(
					"<p>"
						+ "Your legs start to wobble and feel weak, and as you look down to see what's wrong, they start to transform.");
		} else {
			transformationSB = new StringBuilder(
					"<p>"
						+ "[npc.Name] almost loses [npc.her] balance as [npc.her] legs start to transform.");
		}

		switch (type) {
			case HUMAN:
				if(isPlayer()) {
					transformationSB.append(" They rapidly shift back into normal-looking human legs and feet, covered in "+getSkinColour(BodyCoveringType.HUMAN).getName()+" skin.</br>"
							+ "You now have a pair of [style.boldTfLesser(human legs)].");
				} else {
					transformationSB.append(" They rapidly shift back into normal-looking human legs and feet, covered in "+getSkinColour(BodyCoveringType.HUMAN).getName()+" skin.</br>"
								+ "[npc.She] now has a pair of [style.boldTfLesser(human legs)].");
				}
				break;
			case DEMON_COMMON:
				if(isPlayer()) {
					transformationSB.append(" They quickly shift into a pair of smooth, slender legs, and you let out a gasp as a layer of flawless "+getSkinColour(BodyCoveringType.DEMON_COMMON).getName()+" skin rapidly grows to cover them."
										+ " As they finish transforming, you almost lose your balance and fall over as the bones in your feet start to shift and rearrange themselves."
										+ " After a moment, they've transformed into slender human-like feet, ending in soft, delicate toes.</br>"
										+ "You now have a pair of [style.boldTfLesser(demonic legs)].");
				} else {
					transformationSB.append(" They quickly shift into a pair of smooth, slender legs, and [npc.she] lets out a gasp as a layer of flawless "+getSkinColour(BodyCoveringType.DEMON_COMMON).getName()+" skin rapidly grows to cover them."
										+ " As they finish transforming, [npc.she] almost loses [npc.her] balance as the bones in [npc.her] feet start to shift and rearrange themselves."
										+ " After a moment, they've transformed into slender human-like feet, ending in soft, delicate toes.</br>"
										+ "[npc.She] now has a pair of [style.boldTfLesser(demonic legs)].");
				}
				break;
			case DOG_MORPH:
				if(isPlayer()) {
					transformationSB.append(" A layer of "+getSkinColour(BodyCoveringType.CANINE_FUR).getName()+" fur quickly grows to replace the [pc.legSkin] on your legs as they shift into a new form."
										+ " As your new fur spreads down to the ends of your toes, your feet start to shift and transform."
										+ " Your toenails thicken into little blunt claws, and leathery pads grow to cover your soles, leaving you with paw-like feet."
										+ " As the transformation ends, you see that your new fur smoothly transitions into the [pc.skin] covering the rest of your body at your upper-thigh.</br>"
										+ "You now have a pair of anthropomorphic [style.boldTfLesser(dog-like legs)].");
				} else {
					transformationSB.append(" A layer of " + getSkinColour(BodyCoveringType.CANINE_FUR).getName() + " fur quickly grows to replace the [npc.legSkin] on [npc.her] legs as they shift into a new form."
										+ " As the new fur spreads down to the ends of [npc.her] toes, [npc.her] feet start to shift and transform."
										+ " [npc.Her] toenails thicken into little blunt claws, and leathery pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
										+ " [npc.Her] new fur smoothly transitions into the [npc.Skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.</br>"
										+ "[npc.She] now has a pair of anthropomorphic [style.boldTfLesser(dog-like legs)].");
				}
				break;
			case LYCAN:
				if(isPlayer()) {
					transformationSB.append(" A layer of " + getSkinColour(BodyCoveringType.LYCAN_FUR).getName() + " fur quickly grows to replace the [pc.legSkin] on your legs as they shift into a new form."
										+ " As your new fur spreads down to the ends of your toes, your feet start to shift and transform."
										+ " Your toenails thicken into sharp claws, and tough leathery pads grow to cover your soles, leaving you with paw-like feet."
										+ " As the transformation ends, you see that your new fur smoothly transitions into the [pc.skin] covering the rest of your body at your upper-thigh.</br>"
										+ "You now have a pair of anthropomorphic [style.boldTfLesser(wolf-like legs)].");
				} else {
					transformationSB.append(" A layer of " + getSkinColour(BodyCoveringType.LYCAN_FUR).getName() + " fur quickly grows to replace the [npc.legSkin] on [npc.her] legs as they shift into a new form."
										+ " As the new fur spreads down to the ends of [npc.her] toes, [npc.her] feet start to shift and transform."
										+ " [npc.Her] toenails thicken into sharp claws, and tough leathery pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
										+ " [npc.Her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.</br>"
										+ "[npc.She] now has a pair of anthropomorphic [style.boldTfLesser(wolf-like legs)].");
				}
				break;
			case CAT_MORPH:
				if(isPlayer()) {
					transformationSB.append(" A layer of " + getSkinColour(BodyCoveringType.LYCAN_FUR).getName() + " fur quickly grows to replace the [pc.legSkin] on your legs as they shift into a new form."
						+ " As your new fur spreads down to the ends of your toes, your feet start to shift and transform."
						+ " Your toenails thicken into sharp, retractable claws, and little pink pads grow to cover your soles, leaving you with paw-like feet."
						+ " As the transformation ends, you see that your new fur smoothly transitions into the [pc.skin] covering the rest of your body at your upper-thigh.</br>"
						+ "You now have a pair of anthropomorphic [style.boldTfLesser(cat-like legs)].");
				} else {
					transformationSB.append(" A layer of " + getSkinColour(BodyCoveringType.LYCAN_FUR).getName() + " fur quickly grows to replace the [npc.legSkin] on [npc.her] legs as they shift into a new form."
								+ " As the new fur spreads down to the ends of [npc.her] toes, [npc.her] feet start to shift and transform."
								+ " [npc.Her] toenails thicken into sharp, retractable claws, and little pink pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
								+ " [npc.Her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.</br>"
								+ "[npc.She] now has a pair of anthropomorphic [style.boldTfLesser(cat-like legs)].");
				}
				break;
			case HORSE_MORPH:
				if(isPlayer()) {
					transformationSB.append(" A layer of short " + getSkinColour(BodyCoveringType.HORSE_HAIR).getName() + " hair quickly grows to replace the [pc.legSkin] on your legs as they shift into a new form."
								+ " As your new horse-like hair spreads down to the ends of your toes, your feet start to shift and transform."
								+ " Your toes push together, and you let out a cry as a thick, hoof-like nail grows in their place, quickly transforming to turn your feet into horse-like hooves."
								+ " As the transformation ends, you see that your new horse-hair smoothly transitions into the [pc.skin] covering the rest of your body at your upper-thigh.</br>"
								+ "You now have a pair of anthropomorphic [style.boldTfLesser(horse-like legs)], complete with hooves in place of feet.");
				} else {
					transformationSB.append(" A layer of short " + getSkinColour(BodyCoveringType.HORSE_HAIR).getName() + " hair quickly grows to replace the [npc.legSkin] on [npc.her] legs as they shift into a new form."
										+ " As [npc.her] new horse-like hair spreads down to the ends of [npc.her] toes, [npc.her] feet start to shift and transform."
										+ " [npc.Her] toes push together, and [npc.she] lets out a cry as a thick, hoof-like nail grows in their place, quickly transforming to turn [npc.her] feet into horse-like hooves."
										+ " [npc.Her] new horse-hair smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.</br>"
										+ "[npc.She] now has a pair of anthropomorphic [style.boldTfLesser(horse-like legs)], complete with hooves in place of feet.");
				}
				break;
			case HARPY:
				if(isPlayer()) {
					transformationSB.append(" A layer of scaly bird-like leather quickly grows to replace the [pc.legSkin] on your legs as they shift into a new form."
								+ " As your new leathery skin spreads down to the ends of your toes, your feet start to undergo an extreme transformation."
								+ " Your toes combine together and re-shape themselves into three forward-facing talons, as a fourth, thumb-like talon branches out behind them."
								+ " As the transformation ends, a layer of "+getSkinColour(BodyCoveringType.HAIR_HARPY).getName()
									+" feathers grow around your upper-thigh, smoothly transitioning into your new leathery skin which now covers your lower-legs.</br>"
								+ "You now have a pair of anthropomorphic [style.boldTfLesser(harpy legs)], complete with bird-like talons in place of feet.");
				} else {
					transformationSB.append(" A layer of scaly bird-like leather quickly grows to replace the [npc.legSkin] on [npc.her] legs as they shift into a new form."
								+ " As [npc.her] new leathery skin spreads down to the ends of [npc.her] toes, [npc.her] feet start to undergo an extreme transformation."
								+ " [npc.Her] toes combine together and re-shape themselves into three forward-facing talons, as a fourth, thumb-like talon branches out behind them."
								+ " As the transformation ends, a layer of "+getSkinColour(BodyCoveringType.HAIR_HARPY).getName()
									+" feathers grow around [npc.her] upper-thigh, smoothly transitioning into [npc.her] new leathery skin which now covers [npc.her] lower-legs.</br>"
								+ "[npc.She] now has a pair of anthropomorphic [style.boldTfLesser(harpy legs)], complete with bird-like talons in place of feet.");
				}
				break;
				// case SLIME:
				// transformationSB.append(isPlayer()
				// ?" You let out a startled cry as your legs start to melt together
				// into an amorphous blob of "+getSkinColour(SkinType.SLIME).getName()+"
				// slime."
				// + " You desperately try to concentrate on re-forming your legs, and
				// as you do so, they start to re-define themselves as"
				// + " human-shaped legs. Apart from the fact that they're made out of
				// translucent slime, your legs end up looking pretty normal."
				// :GenericSentence.parseTextForGenderReplacement(this,
				// " <She> lets out a startled cry as <her> legs start to melt together
				// into an amorphous blob of "+getSkinColour(SkinType.SLIME).getName()+"
				// slime."
				// + " <She> desperately tries to move <her> shapeless bottom half, and
				// as <she> does so, it starts to re-define itself into a pair of"
				// + " human-shaped legs. Apart from the fact that they're now made out
				// of translucent slime, <her> legs look pretty normal."));
				// break;
				default:
					transformationSB.append(isPlayer()
							? " Your legs shift and change into " + type.getDeterminer(this) + " " + type.getName(this) + "." + "</br>" 
							+ "You now have <b style='color:" + Colour.TRANSFORMATION_LESSER + ";'>" + type.getName(this) + "</b>."
							: UtilText.genderParsing(this, " <Her> legs shift and change into " + type.getDeterminer(this) + " " + type.getName(this) + "." + "</br>" + "<She> now has <b style='color:"
									+ Colour.TRANSFORMATION_LESSER + ";'>" + type.getName(this) + "</b>."));
		}
		body.getLeg().setType(type);
		return transformationSB.toString() + "</br></br>" + postTransformationCalculation() + "</p>";
	}

	public String getLegName() {
		return body.getLeg().getName(this);
	}

	public String getLegName(boolean withDescriptor) {
		return body.getLeg().getName(this, withDescriptor);
	}

	public String getLegDescriptor() {
		return body.getLeg().getType().getDescriptor(this);
	}

	public String getLegDeterminer() {
		return body.getLeg().getType().getDeterminer(this);
	}

	public String getLegPronoun() {
		return body.getLeg().getType().getPronoun();
	}

	// Penis:

	public PenisType getPenisType() {
		return body.getPenis().getType();
	}
	
	public boolean hasPenis() {
		return body.getPenis().getType() != PenisType.NONE;
	}
	
	public boolean isPenisKnotted() {
		return body.getPenis().getType().isKnotted();
	}
	public boolean isPenisFlaredHead() {
		return body.getPenis().getType().isFlaredHead();
	}
	public boolean isPenisBarbedShaft() {
		return body.getPenis().getType().isBarbedShaft();
	}
	public boolean isPenisRibbedShaft() {
		return body.getPenis().getType().isRibbedShaft();
	}
	public boolean isPenisTentacledShaft() {
		return body.getPenis().getType().isTentacledShaft();
	}

	/**
	 * Description may be a little off if you're trying to grow a new penis that
	 * isn't human (but should be acceptable as a rough description).
	 * 
	 * @return Formatted description of penis change.
	 */
	public String setPenisType(PenisType type) {
		if (type == getPenisType()) {
			if(isPlayer()) {
				if(type==PenisType.NONE) {
					return "<p style='text-align:center;'>"
							+ "<span style='color:" + Colour.TEXT_GREY + ";'>You already lack a penis, so nothing happens...</span>"
						+ "</p>";
					
				} else {
					return "<p style='text-align:center;'>"
							+ "<span style='color:" + Colour.TEXT_GREY + ";'>You already have a [pc.penisRace]'s penis, so nothing happens...</span>"
						+ "</p>";
				}
			}else {
				if(type==PenisType.NONE) {
					return "<p style='text-align:center;'>"
							+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] already lacks a penis, so nothing happens...</span>")
						+ "</p>";
					
				} else {
					return "<p style='text-align:center;'>"
							+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] already has a [npc.penisRace]'s penis, so nothing happens...</span>")
						+ "</p>";
				}
			}
		}
		
		if (getPenisType() == PenisType.NONE) {
			body.getPenis().setNumberOfTesticles(2);
			if (isPlayer())
				transformationSB = new StringBuilder(
						"<p>" + "You feel an intense heat building up in your groin, and you let out a lewd moan as you feel the " + getSkinType().getName(this) + " " + (getVaginaType() == VaginaType.NONE ? "between your legs" : "above your pussy")
								+ " tighten up and start to press outwards." + " Within moments, a large bump has formed " + (getVaginaType() == VaginaType.NONE ? "in the middle of your groin," : "above your feminine slit,")
								+ " and with a sudden splitting sensation, the bump forms into a human penis." + " As your new cock flops down " + (getVaginaType() == VaginaType.NONE
										? "between your legs, you feel a pair of balls push out underneath the base of your new shaft," : "to bump against your pussy, you feel a pair of balls pushing out between your two sexes,")
								+ " and you let out an unwitting moan as your new sexual organ finishes growing." + "</br>");
			else
				transformationSB = new StringBuilder(UtilText.genderParsing(this, "<p>" + getName("The") + " suddenly blushes and lets out a lewd moan as <she> squeezes <her> thighs together."
						+ (getVaginaType() == VaginaType.NONE ? "Between <her> legs" : "Above <her> feminine slit,") + ", a new human penis quickly grows out from under <her> " + getSkinType().getName(this) + "." + "</br>"));
		} else {
			if (isPlayer())
				transformationSB = new StringBuilder(
						"<p>" + "You let out a gasp as you feel your cock suddenly stand to attention, and as you try to get your unexpected erection under control, your member starts to feel incredibly sensitive and starts to transform.");
			else
				transformationSB = new StringBuilder(
						UtilText.genderParsing(this, "<p>" + getName("The") + " suddenly blushes and lets out a lewd moan, squeezing <her> thighs together as <her> cock starts to transform."));
		}

		switch (type) {

		case NONE:
			// Remove balls:
			body.getPenis().setNumberOfTesticles(0);
			transformationSB.append(isPlayer()
					? " You squirm and moan as your cock and balls rapidly shrink into your " + getSkinType().getName(this) + ", and within seconds, nothing's left to remind you of your manhood." + "</br>" + "You <b style='color:"
							+ Colour.TRANSFORMATION_SEXUAL + ";'>no longer have a penis</b>."
					: UtilText.genderParsing(this, " <She> squirms and moans as <her> cock and balls rapidly shrink into <her> " + getSkinType().getName(this)
							+ ", and within seconds, nothing's left to remind <herPro> of <her> manhood." + "</br>" + "<She> <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>no longer has a penis</b>."));
			// Restore urethra virginity:
			setUrethraVirgin(true);
			// Remove penis piercing:
			if(isPiercedPenis())
				setPiercedPenis(false);
			break;
		case HUMAN:
			if (getPenisType() == PenisType.NONE)
				transformationSB.append(isPlayer() ? "You now have a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>human penis</b>."
						: UtilText.genderParsing(this, "<She> now has a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>human penis</b>."));
			else
				transformationSB.append(isPlayer()
						? " You squirm and moan as your cock and balls shift and change, and before you know what's happened, they've turned into their human counterparts, covered in " + getSkinColour(type.getSkinType()).getName() + " skin."
								+ "</br>" + "You now have a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>human penis</b>."
						: UtilText.genderParsing(this, " <She> squirms and moans as <her> cock and balls shift and change, and within moments they've turned into their human counterparts, covered in "
								+ getSkinColour(type.getSkinType()).getName() + " skin." + "</br>" + "<She> now has a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>human penis</b>."));
			break;
		case DEMON_COMMON:
			transformationSB.append(isPlayer()
					? " You squirm and moan as the skin covering your cock and balls transforms into smooth, highly sensitive " + getSkinColour(type.getSkinType()).getName() + " skin."
							+ " As you're wondering if that's all that's going to change, your penis suddenly throbs and wriggles about with a mind of its own, and you let out an involuntary moan as slimy pre-cum starts drooling from the tip."
							+ " Within moments, your cock has reshaped itself into a meaty shaft, with thick ridges pressing out all along its length."
							+ " The most striking feature of your new member, however, is the rows of little bumps that press out and wriggle with a mind of their own."
							+ " You groan as more pre-cum starts to leak from these numerous little pseudo-cocks lining your new demonic shaft." + "</br>" + "You now have a <b style='color:" + Colour.TRANSFORMATION_SEXUAL
							+ ";'>demonic penis</b>."
					: UtilText.genderParsing(this,
							" <She> squirms and moans as the skin covering <her> cock and balls transforms into smooth, highly sensitive " + getSkinColour(type.getSkinType()).getName() + " skin."
									+ " <Her> transformation doesn't stop there, and <her> cock suddenly throbs and wriggles about with a mind of its own as slimy pre-cum starts drooling from the tip."
									+ " Within moments, <her> cock has reshaped itself into a meaty shaft, with thick ridges pressing out all along its length."
									+ " The most striking feature of <her> new member, however, is the rows of little bumps that press out and wriggle with a mind of their own."
									+ " <She> groans as more pre-cum starts to leak from these numerous little pseudo-cocks lining <her> new demonic shaft." + "</br>" + "<She> now has a <b style='color:"
									+ Colour.TRANSFORMATION_SEXUAL + ";'>demonic penis</b>."));
			break;
		case CANINE:
			transformationSB.append(isPlayer()
					? " You squirm and moan as the skin covering your cock quickly turns " + getSkinColour(type.getSkinType()).getName() + ", and as the transformation moves onto the next stage, slimy pre-cum starts leaking from the tip."
							+ " Letting out an involuntary moan, you feel your penis shifting into a new form, and you're hit by a wave of overwhelming arousal as a thick red knot presses out at the base of your shaft."
							+ " Panting and gasping for air, you feel the tip of your cock narrowing down as it tapers into its new form."
							+ " As the transformation comes to an end, you discover that your shaft has reshaped itself into a tapered dog-like cock, with a thick, throbbing knot at the base." + "</br>" + "You now have a <b style='color:"
							+ Colour.TRANSFORMATION_SEXUAL + ";'>knotted dog-cock</b>."
					: UtilText.genderParsing(this,
							" <She> squirms and moans as the skin covering <her> cock quickly turns " + getSkinColour(type.getSkinType()).getName() + ", and as the transformation moves onto the next stage, slimy pre-cum starts leaking from the tip."
									+ " Letting out an involuntary moan, <her> penis starts shifting into a new form, and a thick red knot presses out at the base of <her> shaft."
									+ " Panting and gasping for air, the tip of <her> cock narrows down as it tapers into its new form."
									+ " As the transformation comes to an end, <she>'s left with a tapered dog-like cock, with a thick, throbbing knot at the base." + "</br>" + "<She> now has a <b style='color:"
									+ Colour.TRANSFORMATION_SEXUAL + ";'>knotted dog-cock</b>."));
			break;
		case LUPINE:
			transformationSB.append(isPlayer()
					? " You squirm and moan as the skin covering your cock quickly turns " + getSkinColour(type.getSkinType()).getName() + ", and as the transformation moves onto the next stage, slimy pre-cum starts leaking from the tip."
							+ " Letting out an involuntary moan, you feel your penis shifting into a new form, and you're hit by a wave of overwhelming arousal as a thick red knot presses out at the base of your shaft."
							+ " Panting and gasping for air, you feel the tip of your cock narrowing down as it tapers into its new form."
							+ " As the transformation comes to an end, you discover that your shaft has reshaped itself into a tapered wolf-like cock, with a thick, throbbing knot at the base." + "</br>" + "You now have a <b style='color:"
							+ Colour.TRANSFORMATION_SEXUAL + ";'>knotted wolf-cock</b>."
					: UtilText.genderParsing(this,
							" <She> squirms and moans as the skin covering <her> cock quickly turns " + getSkinColour(type.getSkinType()).getName() + ", and as the transformation moves onto the next stage, slimy pre-cum starts leaking from the tip."
									+ " Letting out an involuntary moan, <her> penis starts shifting into a new form, and a thick red knot presses out at the base of <her> shaft."
									+ " Panting and gasping for air, the tip of <her> cock narrows down as it tapers into its new form."
									+ " As the transformation comes to an end, <she>'s left with a tapered wolf-like cock, with a thick, throbbing knot at the base." + "</br>" + "<She> now has a <b style='color:"
									+ Colour.TRANSFORMATION_SEXUAL + ";'>knotted wolf-cock</b>."));
			break;
		case FELINE:
			transformationSB.append(isPlayer()
					? " You squirm and moan as the skin covering your cock quickly turns " + getSkinColour(type.getSkinType()).getName() + ", and as the transformation moves onto the next stage, slimy pre-cum starts leaking from the tip."
							+ " Letting out an involuntary moan, you feel your penis shifting into a new form, and you're hit by a wave of overwhelming arousal as rows of little backwards-facing barbs press out all along your shaft."
							+ " Panting and gasping for air, the transformation finally comes to an end, and you discover that your shaft has reshaped itself into a barbed cat-like cock." + "</br>" + "You now have a <b style='color:"
							+ Colour.TRANSFORMATION_SEXUAL + ";'>barbed cat-dick</b>."
					: UtilText.genderParsing(this,
							" <She> squirms and moans as the skin covering <her> cock quickly turns " + getSkinColour(type.getSkinType()).getName() + ", and as the transformation moves onto the next stage, slimy pre-cum starts leaking from the tip."
									+ " Letting out an involuntary moan, <her> penis starts shifting into a new form, and rows of little backwards-facing barbs press out all along <her> shaft."
									+ " Panting and gasping for air, the transformation finally comes to an end, leaving <herPro> with a barbed cat-like cock." + "</br>" + "<She> now has a <b style='color:"
									+ Colour.TRANSFORMATION_SEXUAL + ";'>barbed cat-dick</b>."));
			break;
		case EQUINE:
			transformationSB.append(isPlayer()
					? " You squirm and moan as the skin covering your cock and balls quickly turns " + getSkinColour(type.getSkinType()).getName() + ", and as the transformation moves onto the next stage, slimy pre-cum starts leaking from the tip."
							+ " Letting out an involuntary moan, you feel your penis shifting into a new form, and you're hit by a wave of overwhelming arousal as your shaft grows wider and the head flattens down."
							+ " Panting and gasping for air, the transformation finally comes to an end, and you discover that your penis has reshaped itself into a fat horse-like cock, complete with a wide, flared head." + "</br>"
							+ "You now have a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>flared horse-cock</b>."
					: UtilText.genderParsing(this,
							" <She> squirms and moans as the skin covering <her> cock and balls quickly turns " + getSkinColour(type.getSkinType()).getName()
									+ ", and as the transformation moves onto the next stage, slimy pre-cum starts leaking from the tip."
									+ " Letting out an involuntary moan, <her> penis starts shifting into a new form, and <her> shaft grows wider as the head flattens down."
									+ " Panting and gasping for air, the transformation finally comes to an end, leaving <herPro> with a fat horse-like cock, complete with a wide, flared head." + "</br>" + "<She> now has a <b style='color:"
									+ Colour.TRANSFORMATION_SEXUAL + ";'>flared horse-cock</b>."));
			break;
		case AVIAN:
			transformationSB.append(isPlayer()
					? " You squirm and moan as the skin covering your cock and balls quickly turns " + getSkinColour(type.getSkinType()).getName() + ", and as the transformation moves onto the next stage, slimy pre-cum starts leaking from the tip."
							+ " Letting out an involuntary moan, you feel your penis shifting into a new form, and you're hit by a wave of overwhelming arousal as a cloaca-like slit forms around the base."
							+ " Panting and gasping for air, you discover that your penis has reshaped itself into a human-looking cock, but you now possess the ability to retract your sexual organs into the cloaca that's formed around the base."
							+ "</br>" + "You now have a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>bird-like cock</b>."
					: UtilText.genderParsing(this,
							" <She> squirms and moans as the skin covering <her> cock and balls quickly turns " + getSkinColour(type.getSkinType()).getName()
									+ ", and as the transformation moves onto the next stage, slimy pre-cum starts leaking from the tip."
									+ " Letting out an involuntary moan, <her> penis starts shifting into a new form, and a cloaca-like slit forms around the base."
									+ " Panting and gasping for air, <she>'s left with a human-like cock, but <she> now possesses the ability to retract <her> sexual organs into the cloaca that's formed around the base." + "</br>"
									+ "<She> now has a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>bird-like cock</b>."));
			break;
		// case SLIME:
		// transformationSB.append(isPlayer()
		// ?" You let out a startled cry as your "+getPenisType().getName(this)+"
		// and balls start melting into
		// "+getSkinColour(type.getSkinType()).getName()+" slime. You
		// concentrate on trying to re-form your genitals, and within seconds,"
		// + " a new slime cock and balls form out of the gooey mass."
		// :GenericSentence.parseTextForGenderReplacement(this,
		// " <She> lets out a startled cry as <her> "+getPenisType().getName(this)+"
		// and balls start melting into
		// "+getSkinColour(type.getSkinType()).getName()+" slime. After a few
		// seconds, a new slime cock and balls"
		// + " form out of the gooey mass."));
		// break;

		default:
			transformationSB.append(isPlayer()
					? " Your cock shifts and changes into " + type.getDeterminer(this) + " " + type.getName(this) + "." + "</br>" + "You now have a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + type.getName(this) + "</b>."
					: UtilText.genderParsing(this, " <Her> cock shifts and changes into " + type.getDeterminer(this) + " " + type.getName(this) + "." + "</br>" + "<She> now has  <b style='color:"
							+ Colour.TRANSFORMATION_SEXUAL + ";'>" + type.getName(this) + "</b>."));
		}

		body.getPenis().setType(type);
		return transformationSB.toString() + "</br></br>" + postTransformationCalculation() + "</p>";
	}

	public String getPenisName() {
		return body.getPenis().getName(this);
	}

	public String getPenisName(boolean withDescriptor) {
		return body.getPenis().getName(this, withDescriptor);
	}
	
	public String getCumName(boolean withDescriptor) {

		String cumName = body.getPenis().getType().getCumName(this);
		
		if(withDescriptor) {
			String descriptor = getCumDescriptor();
			return (descriptor.length() > 0 ? descriptor + " " : "") + cumName;
		} else {
			return cumName;
		}
	}
	
	public String getCumDescriptor() {
		return body.getPenis().getType().getCumDescriptor();
	}
	
	public String getPenisDescriptor() {
		return body.getPenis().getType().getDescriptor(this);
	}

	public String getPenisDeterminer() {
		return body.getPenis().getType().getDeterminer(this);
	}

	public String getPenisPronoun() {
		return body.getPenis().getType().getPronoun();
	}

	public String getPenisDescription() {
		return body.getPenisDescription(this);
	}

	public int getPenisNumberOfTesticles() {
		return body.getPenis().getNumberOfTesticles();
	}

	/**
	 * @return Formatted description of testicle count change.
	 */
	public String setPenisNumberOfTesticles(int testicleCount) {
		if (getPenisNumberOfTesticles() < testicleCount) {
			if (body.getPenis().setNumberOfTesticles(testicleCount))
				if (isPlayer())
					return "<p>" + "You let out a surprised cry as you feel your balls rapidly swell and grow heavier." + " After only a few seconds, your swollen testicles painlessly split apart and multiply." + "</br>"
							+ "You now have <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + Util.intToString(testicleCount) + " balls</b>." + "</p>";
				else
					return UtilText.genderParsing(this,
							"<p>" + "<She> lets out a surprised cry as <her> balls rapidly swell and grow heavier." + " After only a few seconds, <her> swollen testicles painlessly split apart and multiply." + "</br>"
									+ "<She> now has <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + Util.intToString(testicleCount) + " balls</b>." + "</p>");
		} else {
			if (body.getPenis().setNumberOfTesticles(testicleCount))
				if (isPlayer())
					return "<p>" + "You let out a surprised cry as you feel your balls shrink and fuse together." + "</br>" + "You now have <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>"
							+ Util.intToString(testicleCount) + " balls</b>." + "</p>";
				else
					return UtilText.genderParsing(this, "<p>" + "<She> lets out a surprised cry as <her> balls shrink and fuse together." + "</br>" + "<She> now has <b style='color:"
							+ Colour.TRANSFORMATION_SEXUAL + ";'>" + Util.intToString(testicleCount) + " balls</b>." + "</p>");
		}
		return "<p>" + "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>" + "</p>";
	}

	public boolean isUrethraVirgin() {
		return body.getPenis().isUrethraVirgin();
	}

	public void setUrethraVirgin(boolean virgin) {
		body.getPenis().setUrethraVirgin(virgin);
	}

	// Capacity:
	public Capacity getPenisCapacity() {
		return body.getPenis().getCapacity();
	}

	public float getPenisRawCapacityValue() {
		return body.getPenis().getRawCapacityValue();
	}
	
	public float getPenisStretchedCapacity() {
		return body.getPenis().getStretchedCapacity();
	}
	public void setPenisStretchedCapacity(float capacity){
		body.getPenis().setStretchedCapacity(capacity);
	}
	public void incrementPenisStretchedCapacity(float increment){
		body.getPenis().setStretchedCapacity(getPenisStretchedCapacity() + increment);
	}

	/**
	 * @return Formatted description of urethra capacity change.
	 */
	public String setPenisCapacity(float capacity) {

		if (getPenisRawCapacityValue() < capacity) {
			if (body.getPenis().setCapacity(capacity))
				if (getPenisRawCapacityValue() == 0) {
					if (isPlayer())
						return "<p>" + "You let out a gasp as you feel your urethra slacken and get wider." + "</br>" + "You can now have a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>fuckable urethra</b>." + "</p>";
					else
						return UtilText.genderParsing(this,
								"<p>" + "<She> lets out a gasp as <her> urethra slackens and gets wider." + "</br>" + "<She> can now has a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>fuckable urethra</b>." + "</p>");
				} else {
					if (isPlayer())
						return "<p>" + "You let out a gasp as you feel your urethra slacken and get wider." + "</br>" + "You can now fit <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>"
								+ Capacity.getCapacityFromValue(capacity).getMaximumSizeComfortable().getDescriptor() + " cocks down your urethra</b>." + "</p>";
					else
						return UtilText.genderParsing(this, "<p>" + "<She> lets out a gasp as <her> urethra slackens and gets wider." + "</br>" + "<She> can now fit <b style='color:"
								+ Colour.TRANSFORMATION_SEXUAL + ";'>" + Capacity.getCapacityFromValue(capacity).getMaximumSizeComfortable().getDescriptor() + " cocks down <her> urethra</b>." + "</p>");
				}
		} else {

			if (body.getPenis().setCapacity(capacity))
				if (capacity == 0) {
					if (isPlayer())
						return "<p>" + "You let out a lewd moan as you feel your urethra tighten up." + "</br>" + "You <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>no longer have a fuckable urethra</b>." + "</p>";
					else
						return UtilText.genderParsing(this,
								"<p>" + "<She> lets out a lewd moan as <her> urethra tightens up." + "</br>" + "<She> <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>no longer has a fuckable urethra</b>." + "</p>");
				} else {
					if (isPlayer())
						return "<p>" + "You let out a lewd moan as you feel your urethra tighten up." + "</br>" + "You can now fit <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>"
								+ Capacity.getCapacityFromValue(capacity).getMaximumSizeComfortable().getDescriptor() + " cocks down your urethra</b>." + "</p>";
					else
						return UtilText.genderParsing(this, "<p>" + "<She> lets out a lewd moan as <her> urethra tightens up." + "</br>" + "<She> can now fit <b style='color:"
								+ Colour.TRANSFORMATION_SEXUAL + ";'>" + Capacity.getCapacityFromValue(capacity).getMaximumSizeComfortable().getDescriptor() + " cocks down <her> urethra</b>." + "</p>");
				}
		}
		return "<p>" + "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>" + "</p>";
	}

	public String incrementPenisCapacity(float increment) {
		return setPenisCapacity(getPenisRawCapacityValue() + increment);
	}

	// Cum Production:
	public CumProduction getPenisCumProduction() {
		return body.getPenis().getCumProduction();
	}

	public int getPenisRawCumProductionValue() {
		return body.getPenis().getRawCumProductionValue();
	}

	/**
	 * @return Formatted description of cum production change.
	 */
	public String setCumProduction(int cumProduction) {
		if(hasPenis()) {
			if (getPenisRawCumProductionValue() < cumProduction) {
				if (body.getPenis().setCumProduction(cumProduction))
					if (isPlayer())
						return "<p>" + "You feel your balls grow heavier and fill up as your cum production increases." + "</br>" + "You are now <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>producing "
								+ CumProduction.getCumProductionFromInt(cumProduction).getDescriptor() + "</b>." + "</p>";
					else
						return UtilText.genderParsing(this, "<p>" + "<Her> balls grow heavier and fill up as <her> cum production increases." + "</br>" + "<She> is now <b style='color:"
								+ Colour.TRANSFORMATION_SEXUAL + ";'>producing " + CumProduction.getCumProductionFromInt(cumProduction).getDescriptor() + "</b>." + "</p>");
			} else {
				if (body.getPenis().setCumProduction(cumProduction))
					if (isPlayer())
						return "<p>" + "You feel your balls get lighter as your cum production decreases." + "</br>" + "You are now <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>producing "
								+ CumProduction.getCumProductionFromInt(cumProduction).getDescriptor() + "</b>." + "</p>";
					else
						return UtilText.genderParsing(this, "<p>" + "<Her> balls get lighter as <her> cum production decreases." + "</br>" + "<She> is now <b style='color:" + Colour.TRANSFORMATION_SEXUAL
								+ ";'>producing " + CumProduction.getCumProductionFromInt(cumProduction).getDescriptor() + "</b>." + "</p>");
			}
			
			if(isPlayer()) {
				return "<p class='center'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>Your cum production doesn't change...</span>"
					+ "</p>";
			} else {
				return "<p class='center'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name]'s cum production doesn't change...</span>")
					+ "</p>";
			}
			
		} else {
			if(isPlayer()) {
				return "<p class='center'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>You don't have a penis, so nothing happens...</span>"
					+ "</p>";
			} else {
				return "<p class='center'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] doesn't have a penis, so nothing happens...</span>")
					+ "</p>";
			}
		}
	}

	public String incrementPenisCumProduction(int increment) {
		return setCumProduction(getPenisRawCumProductionValue() + increment);
	}

	// Penis size:
	public PenisSize getPenisSize() {
		return body.getPenis().getSize();
	}

	public int getPenisRawSizeValue() {
		return body.getPenis().getRawSizeValue();
	}

	/**
	 * @return Formatted description of penis size change.
	 */
	public String setPenisSize(int size) {
		if(hasPenis()) {
			if (getPenisRawSizeValue() < size) {
				if (body.getPenis().setPenisSize(size))
					if (isPlayer())
						return "<p>" + "You let out a groan as you feel a deep throbbing sensation building up at the base of your cock."
								+ " Your cheeks flush red as the feeling works its way up your shaft, and as a trickle of pre-cum leaks out from the head of your now-hard member, you realise that your cock has grown larger." + "</br>"
								+ "You now have a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + PenisSize.getPenisSizeFromInt(size).getDescriptor() + " cock</b>." + "</p>";
					else
						return UtilText.genderParsing(this,
								"<p>" + "<She> lets out a groan as a deep throbbing sensation builds up at the base of <her> cock."
										+ " <Her> cheeks flush red as the feeling works its way up <her> shaft, and as a trickle of pre-cum leaks out from the head of <her> now-hard member, <her> cock suddenly grows larger." + "</br>"
										+ "<She> now has a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + PenisSize.getPenisSizeFromInt(size).getDescriptor() + " cock</b>." + "</p>");
			} else {
				if (body.getPenis().setPenisSize(size))
					if (isPlayer())
						return "<p>" + "You let out a groan as you feel a strange contracting sensation building up at the base of your cock."
								+ " Your cheeks flush red as the feeling works its way up your shaft, and as a trickle of pre-cum leaks out from the head of your now-hard member, you realise that your cock has shrunk." + "</br>"
								+ "You now have a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + PenisSize.getPenisSizeFromInt(size).getDescriptor() + " cock</b>." + "</p>";
					else
						return UtilText.genderParsing(this,
								"<p>" + "<She> lets out a groan as a strange contracting sensation builds up at the base of <her> cock."
										+ " <Her> cheeks flush red as the feeling works its way up <her> shaft, and as a trickle of pre-cum leaks out from the head of <her> now-hard member, <her> cock suddenly shrinks." + "</br>"
										+ "<She> now has a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>" + PenisSize.getPenisSizeFromInt(size).getDescriptor() + " cock</b>." + "</p>");
			}
			
			if(isPlayer()) {
				return "<p class='center'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>The size of your [pc.penis] doesn't change...</span>"
					+ "</p>";
			} else {
				return "<p class='center'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The size of [npc.name]'s [npc.penis] doesn't change...</span>")
					+ "</p>";
			}
		} else {
			if(isPlayer()) {
				return "<p class='center'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>You don't have a penis, so nothing happens...</span>"
					+ "</p>";
			} else {
				return "<p class='center'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] doesn't have a penis, so nothing happens...</span>")
					+ "</p>";
			}
		}
		
	}

	public String incrementPenisSize(int increment) {
		return setPenisSize(getPenisRawSizeValue() + increment);
	}

	// Testicle size:
	public TesticleSize getTesticleSize() {
		return body.getPenis().getTesticleSize();
	}

	/**
	 * @return Formatted description of testicle size change.
	 */
	public String setTesticleSize(int size) {
		if(hasPenis()) {
			if (getTesticleSize().getValue() < size) {
				if (body.getPenis().setTesticleSize(size))
					if (isPlayer())
						return "<p>" + "You let out a lewd moan as your balls start to ache with a strange pressing sensation."
								+ " After a few moments, they suddenly swell and grow larger, and you're left panting and groaning as the transformation comes to an end." + "</br>" + "You now have <b style='color:"
								+ Colour.TRANSFORMATION_SEXUAL + ";'>" + TesticleSize.getTesticleSizeFromInt(size).getDescriptor() + " balls</b>." + "</p>";
					else
						return UtilText.genderParsing(this,
								"<p>" + "<She> lets out a lewd moan as <her> balls start to ache with a strange pressing sensation."
										+ " After a few moments, they suddenly swell and grow larger, and <she>'s left panting and groaning as the transformation comes to an end." + "</br>" + "<She> now has <b style='color:"
										+ Colour.TRANSFORMATION_SEXUAL + ";'>" + TesticleSize.getTesticleSizeFromInt(size).getDescriptor() + " balls</b>." + "</p>");
			} else {
				if (body.getPenis().setTesticleSize(size))
					if (isPlayer())
						return "<p>" + "You let out a surprised yelp as your balls start to ache with a strange shrivelling sensation."
								+ " After a few moments, they suddenly shrink and get smaller, and you're left groaning in frustration as the transformation comes to an end." + "</br>" + "You now have <b style='color:"
								+ Colour.TRANSFORMATION_SEXUAL + ";'>" + TesticleSize.getTesticleSizeFromInt(size).getDescriptor() + " balls</b>." + "</p>";
					else
						return UtilText.genderParsing(this,
								"<p>" + "<She> lets out a surprised yelp as <her> balls start to ache with a strange shrivelling sensation."
										+ " After a few moments, they suddenly shrink and get smaller, and <she>'s left groaning in frustration as the transformation comes to an end." + "</br>" + "<She> now has <b style='color:"
										+ Colour.TRANSFORMATION_SEXUAL + ";'>" + TesticleSize.getTesticleSizeFromInt(size).getDescriptor() + " balls</b>." + "</p>");
			}
			
			if(isPlayer()) {
				return "<p class='center'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>The size of your [pc.balls] doesn't change...</span>"
					+ "</p>";
			} else {
				return "<p class='center'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The size of [npc.name]'s [npc.balls] doesn't change...</span>")
					+ "</p>";
			}
		} else {
			if(isPlayer()) {
				return "<p class='center'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>You don't have a penis, so nothing happens...</span>"
					+ "</p>";
			} else {
				return "<p class='center'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] doesn't have a penis, so nothing happens...</span>")
					+ "</p>";
			}
		}
	}

	public String incrementTesticleSize(int increment) {
		return setTesticleSize(getTesticleSize().getValue() + increment);
	}

	public boolean isInternalTesticles() {
		if(getPenisType()==PenisType.AVIAN) {
			return true;
		} else {
			return false;
		}
	}
	
	// Urethra:
	public OrificeElasticity getPenisUrethraElasticity() {
		return body.getPenis().getElasticity();
	}

	public String setPenisUrethraElasticity(int plasticity) {
		if (getPenisUrethraElasticity().getValue() < plasticity) {
			if (body.getPenis().setElasticity(plasticity))
				if (isPlayer())
					return "</p>"
								+ "You let out a desperate groan as you feel a strange slackening sensation pulsating up the length of your cock."
								+ "</br>" 
								+ "Your <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>urethra is now " + getPenisUrethraElasticity().getDescriptor() + "</b>!"
							+ "</p>";
				else
					return UtilText.genderParsing(this,
							"</p>"
								+ "<She> lets out a desperate groan as a strange slackening sensation pulsates up the length of <her> cock." 
								+ "</br>"
								+ "<Her> <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>urethra is now " + getPenisUrethraElasticity().getDescriptor() + "</b>!"
							+ "</p>");
		} else {
			if (body.getPenis().setElasticity(plasticity))
				if (isPlayer())
					return "</p>"
								+ "You let out a desperate groan as you feel a strange clenching sensation pulsating up the length of your cock."
								+ "</br>"
								+ "Your <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>urethra is now " + getPenisUrethraElasticity().getDescriptor() + "</b>!"
							+ "</p>";
				else
					return UtilText.genderParsing(this, 
							"</p>"
								+ "<She> lets out a desperate groan as a strange clenching sensation pulsates up the length of <her> cock."
								+ "</br>"
								+ "<Her> <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>urethra is now " + getPenisUrethraElasticity().getDescriptor() + "</b>!"
							+ "</p>");
		}
		return "<p>" 
					+ "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>" 
				+ "</p>";
	}

	public String incrementPenisElasticity(int increment) {
		return setPenisUrethraElasticity(getPenisUrethraElasticity().getValue() + increment);
	}
	
//	public boolean isPenisCreampied(){
//		return body.getPenis().isCreampied();
//	}
//
//	public void setPenisCreampied(boolean creampied){
//		body.getPenis().setCreampied(creampied);
//	}

	// Skin:

	public BodyCoveringType getSkinType() {
		return body.getSkin().getType();
	}

	/**
	 * @return Formatted description of skin change.
	 */
	public String setSkinType(BodyCoveringType skinType) {
		if (skinType == getSkinType()) {
			if(isPlayer()) {
			return "<p style='text-align:center;'>"
					+ "<span style='color:" + Colour.TEXT_GREY + ";'>You already have the [pc.skin] of a [pc.skinRace], so nothing happens...</span>"
				+ "</p>";
			
			} else {
				return "<p style='text-align:center;'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] already has the [npc.skin] of a [npc.skinRace], so nothing happens...</span>")
					+ "</p>";	
			}
		}
		
		if (isPlayer())
			transformationSB = new StringBuilder("<p>" + "Your entire torso starts to itch and grow hot, and you frantically start scratching all over as your " + getSkinName() + " starts to transform.");
		else
			transformationSB = new StringBuilder(
					UtilText.genderParsing(this, "<p>" + getName("The") + " squirms in discomfort and starts scratching at <herPro>self as <her> " + getSkinName() + " starts to transform."));

		switch (skinType) {
		case HUMAN:
			transformationSB.append(isPlayer()
					? " The " + getSkinType().getName(this) + " covering your body rapidly turn" + (getSkinType() == BodyCoveringType.FEATHERS ? "" : "s") + " back into " + getSkinColour(skinType).getName() + " human skin, and you let out a satisfied sigh"
							+ " as the transformation comes to an end and the itching stops." + "</br>" + "You are now <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>covered in human skin</b>."
					: UtilText.genderParsing(this,
							" The " + getSkinType().getName(this) + " covering <her> body rapidly turn" + (getSkinType() == BodyCoveringType.FEATHERS ? "" : "s") + " back into " + getSkinColour(skinType).getName()
									+ " human skin, and <she> lets out a satisfied sigh" + " as the transformation comes to an end and the itching stops." + "</br>" + "<She> is now <b style='color:" + Colour.TRANSFORMATION_GREATER
									+ ";'>covered in human skin</b>."));
			break;
		case DEMON_COMMON:
			transformationSB.append(isPlayer()
					? " The " + getSkinType().getName(this) + " covering your body rapidly turn" + (getSkinType() == BodyCoveringType.FEATHERS ? "" : "s") + " into flawless " + getSkinColour(skinType).getName() + " skin, and you let out a satisfied sigh"
							+ " as the transformation comes to an end and the itching stops. Looking at your new skin, you notice that the colour tones all over your body have become perfectly balanced in order to help show off your figure."
							+ "</br>" + "You are now <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>covered in demonic skin</b>."
					: UtilText.genderParsing(this,
							" The " + getSkinType().getName(this) + " covering <her> body rapidly turn" + (getSkinType() == BodyCoveringType.FEATHERS ? "" : "s") + " into flawless " + getSkinColour(skinType).getName()
									+ " skin, and <she> lets out a satisfied sigh"
									+ " as the transformation comes to an end and the itching stops. The colour tones all over <her> body have become perfectly balanced in order to help to show off <her> figure." + "</br>"
									+ "<She> is now <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>covered in demonic skin</b>."));
			break;
		case CANINE_FUR:
			transformationSB.append(isPlayer()
					? " The " + getSkinType().getName(this) + " covering your body rapidly turn" + (getSkinType() == BodyCoveringType.FEATHERS ? "" : "s") + " into a layer of " + getSkinColour(skinType).getName()
							+ " dog-like fur, and you let out a satisfied sigh"
							+ " as the transformation comes to an end and the itching stops. Looking at your new fur, you notice that it follows the lines of your figure and is quite smooth and pleasant to touch." + "</br>"
							+ "You are now <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>covered in dog-like fur</b>."
					: UtilText.genderParsing(this,
							" The " + getSkinType().getName(this) + " covering <her> body rapidly turn" + (getSkinType() == BodyCoveringType.FEATHERS ? "" : "s") + " into a layer of " + getSkinColour(skinType).getName()
									+ " dog-like fur, and <she> lets out a satisfied sigh" + " as the transformation comes to an end and the itching stops. <Her> new fur follows the lines of <her> figure and is quite smooth and pleasant to touch."
									+ "</br>" + "<She> is now <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>covered in dog-like fur</b>."));
			break;
		case LYCAN_FUR:
			transformationSB.append(isPlayer() ? " The " + getSkinType().getName(this) + " covering your body rapidly turn" + (getSkinType() == BodyCoveringType.FEATHERS ? "" : "s") + " into a layer of " + getSkinColour(skinType).getName()
					+ " wolf-like fur, and you let out a satisfied sigh" + " as the transformation comes to an end and the itching stops. Looking at your new fur, you notice that it's a little shaggy around your joints and is quite densely packed."
					+ "</br>" + "You are now <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>covered in wolf-like fur</b>."
					: UtilText.genderParsing(this,
							" The " + getSkinType().getName(this) + " covering <her> body rapidly turn" + (getSkinType() == BodyCoveringType.FEATHERS ? "" : "s") + " into a layer of " + getSkinColour(skinType).getName()
									+ " wolf-like fur, and <she> lets out a satisfied sigh" + " as the transformation comes to an end and the itching stops. <Her> new fur is a little shaggy around <her> joints and is quite densely packed." + "</br>"
									+ "<She> is now <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>covered in wolf-like fur</b>."));
			break;
		case FELINE_FUR:
			transformationSB.append(isPlayer() ? " The " + getSkinType().getName(this) + " covering your body rapidly turn" + (getSkinType() == BodyCoveringType.FEATHERS ? "" : "s") + " into a layer of " + getSkinColour(skinType).getName()
					+ " cat-like fur, and you let out a satisfied sigh" + " as the transformation comes to an end and the itching stops. Looking at your new fur, you notice that it follows the lines of your figure and is extremely smooth and soft."
					+ "</br>" + "You are now <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>covered in cat-like fur</b>."
					: UtilText.genderParsing(this,
							" The " + getSkinType().getName(this) + " covering <her> body rapidly turn" + (getSkinType() == BodyCoveringType.FEATHERS ? "" : "s") + " into a layer of " + getSkinColour(skinType).getName()
									+ " cat-like fur, and <she> lets out a satisfied sigh" + " as the transformation comes to an end and the itching stops. <Her> new fur follows the lines of <her> figure and is extremely smooth and soft." + "</br>"
									+ "<She> is now <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>covered in cat-like fur</b>."));
			break;
		case HORSE_HAIR:
			transformationSB.append(isPlayer() ? " The " + getSkinType().getName(this) + " covering your body rapidly turn" + (getSkinType() == BodyCoveringType.FEATHERS ? "" : "s") + " into a layer of short " + getSkinColour(skinType).getName()
					+ " horse-hair, and you let out a satisfied sigh" + " as the transformation comes to an end and the itching stops. Your new hair looks very sleek, and helps to show off your figure, although it's a little coarse to the touch."
					+ "</br>" + "You are now <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>covered in a short layer of horse-hair</b>."
					: UtilText.genderParsing(this,
							" The " + getSkinType().getName(this) + " covering <her> body rapidly turn" + (getSkinType() == BodyCoveringType.FEATHERS ? "" : "s") + " into a layer of short " + getSkinColour(skinType).getName()
									+ " horse-hair, and <she> lets out a satisfied sigh"
									+ " as the transformation comes to an end and the itching stops. <Her> new hair looks very sleek, and helps to show off <her> figure, although it's a little coarse to the touch." + "</br>"
									+ "<She> is now <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>covered in a short layer of horse-hair</b>."));
			break;
		case FEATHERS:
			transformationSB.append(isPlayer()
					? " The " + getSkinType().getName(this) + " covering your body rapidly turns into a layer of overlapping " + getSkinColour(skinType).getName() + " feathers, and you let out a satisfied sigh"
							+ " as the transformation comes to an end and the itching stops. Your new feathers follow the lines of your figure and are shorter and softer around your nipples, asshole and groin." + "</br>"
							+ "You are now <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>covered in feathers</b>."
					: UtilText.genderParsing(this,
							" The " + getSkinType().getName(this) + " covering <her> body rapidly turns into a layer of overlapping " + getSkinColour(skinType).getName() + " feathers, and <she> lets out a satisfied sigh"
									+ " as the transformation comes to an end and the itching stops. <Her> new feathers follow the lines of <her> figure and are shorter and softer around <her> nipples, asshole and groin." + "</br>"
									+ "<She> is now <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>covered in feathers</b>."));
			break;
		// case SLIME:
		// transformationSB.append(isPlayer()
		// ?" You suddenly feel your whole body sink down as your flesh melts
		// into "+getSkinColour(skinType).getName()+" slime. You manage to catch
		// yourself before"
		// + " your body slides away from you, and, focusing hard, you manage to
		// re-form your body into a regular human shape."
		// :GenericSentence.parseTextForGenderReplacement(this,
		// " <Her> whole body suddenly sinks down as <her> flesh melts into
		// "+getSkinColour(skinType).getName()+" slime. <She> manages to catch
		// <her>self before"
		// + " <her> body slides away from <her>, and manages to re-form
		// <her>self into a regular human shape."));
		// break;
		default:
			transformationSB.append(isPlayer()
					? "A layer of " + getSkinColour(skinType).getName() + " " + skinType.getName(this) + " rapidly grows to cover your body." + "</br>" + "You now have <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>" + " "
							+ getSkinColour(skinType).getName() + " " + skinType.getName(this) + "</b>."
					: UtilText.genderParsing(this, "A layer of " + getSkinColour(skinType).getName() + " " + skinType.getName(this) + " rapidly grows to cover <her> body." + "</br>" + " <She> now has <b style='color:"
							+ Colour.TRANSFORMATION_GREATER + ";'>" + getSkinColour(skinType).getName() + " " + skinType.getName(this) + "</b>."));
		}
		body.getSkin().setType(skinType);
		return transformationSB.toString() + "</br></br>" + postTransformationCalculation() + "</p>";
	}

	public String getSkinName() {
		return body.getSkin().getName(this);
	}

	public String getSkinName(boolean withDescriptor) {
		return body.getSkin().getName(this, withDescriptor);
	}

	public String getSkinDescriptor() {
		return body.getSkin().getType().getDescriptor(this);
	}

	public String getSkinDeterminer() {
		return body.getSkin().getType().getDeterminer(this);
	}

	public String getSkinPronoun() {
		return body.getSkin().getType().getPronoun();
	}

	public Colour getSkinColour(BodyCoveringType skinType) {
		if(skinType==null)
			return Colour.TEXT_GREY;
		
		return body.getBodyCoveringTypeColours().get(skinType);
	}

	/**
	 * @return Formatted description of skin colour change.
	 */
	public String setSkinColour(BodyCoveringType skinType, Colour shade) {
		if(skinType.getAllColours().contains(shade)) {

			body.getBodyCoveringTypeColours().put(skinType, shade);
			
			List<String> affectedParts = new ArrayList<>();
			for (BodyPartInterface part : body.getAllBodyParts())
				if (part.getType().getSkinType() == skinType)
					affectedParts.add(part.getName(this));

			if (affectedParts.isEmpty())
				return "<p>" + "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>" + "</p>";

			if (isPlayer())
				return "<p>" + "The " + skinType.getName(this) + " on your " + Util.stringsToStringList(affectedParts, false) + " suddenly start" + (skinType.isDefaultPlural() ? "" : "s") + " to itch, and you let out a startled cry as" + " "
						+ (skinType.isDefaultPlural() ? "they begin" : "it begins") + " to change colour." + "</br>" + "You now have <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>"
						+ shade.getName() + " " + skinType.getName(this) + "</b>." + "</p>";
			else
				return UtilText.genderParsing(this,
						"<p>" + "The " + skinType.getName(this) + " on <her> " + Util.stringsToStringList(affectedParts, false) + " suddenly start" + (skinType.isDefaultPlural() ? "" : "s") + " to itch, and <she> lets out a startled cry as" + " "
								+ (skinType.isDefaultPlural() ? "they begin" : "it begins") + " to change colour." + "</br>" + "<She> now has <b style='color:" + Colour.TRANSFORMATION_GREATER + ";'>" + shade.getName() + " "
								+ skinType.getName(this) + "</b>." + "</p>");
			
		}
		return "<p>" + "<span style='color:" + Colour.TEXT_GREY + ";'>Nothing seems to happen.</span>" + "</p>";
	}

	// Tail:

	public TailType getTailType() {
		return body.getTail().getType();
	}
	
	public boolean isTailPrehensile(){
		return body.getTail().getType().isPrehensile();
	}

	/**
	 * @return Formatted description of tail change.
	 */
	public String setTailType(TailType tailType) {
		if (tailType == getTailType()) {
			if(isPlayer()) {
				return "<p style='text-align:center;'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>You already have the [pc.tail] of a [pc.tailRace], so nothing happens...</span>"
					+ "</p>";
			} else {
				return "<p style='text-align:center;'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] already has the [npc.tail] of a [npc.tailRace], so nothing happens...</span>")
					+ "</p>";	
			}
		}
		
		if (getTailType() == TailType.NONE) {
			if (isPlayer())
				transformationSB = new StringBuilder("<p>" + "You feel a strange bubbling sensation building up in your lower back, and after just a moment you feel something pushing out from under your " + getSkinType().getName(this) + ".");
			else
				transformationSB = new StringBuilder(UtilText.genderParsing(this,
						"<p>" + getName("The") + " tries to look around behind <herPro> as something starts to push out from under the " + getSkinType().getName(this) + " of <her> lower back."));
		} else {
			if (isPlayer())
				transformationSB = new StringBuilder("<p>" + "You feel your " + getTailType().getName(this) + " growing hot and itchy, and after just a moment it starts to transform.");
			else
				transformationSB = new StringBuilder(UtilText.genderParsing(this, "<p>" + getName("The") + " tries to look back at <her> " + getTailType().getName(this) + " as it starts to transform."));
		}

		switch (tailType) {
		case NONE:
			transformationSB.append(isPlayer()
					? " You gasp as you feel your " + getTailType().getName(this) + " shrinking down and disappearing into your lower back." + "</br>" + "You <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>have lost your "
							+ getTailType().getName(this) + "</b>."
					: UtilText.genderParsing(this, " <She> gasps as <her> " + getTailType().getName(this) + " shrinks down and disappears into <her> lower back." + "</br>" + "<She> <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>has lost <her> " + getTailType().getName(this) + "</b>."));
			break;
		case DEMON_COMMON:
			transformationSB.append(isPlayer()
					? " A thin, spaded demon's tail sprouts from just above your ass, rapidly growing in size until it's slightly longer than one of your legs."
							+ " You wriggle it around and soon discover that you have complete control over where it goes, allowing you to use it like a third limb." + "</br>" + "You now have a <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>demon tail</b>."
					: UtilText.genderParsing(this,
							" A thin, spaded demon's tail sprouts from just above <her> ass, rapidly growing in size until it's slightly longer than one of <her> legs."
									+ " <She> gives it an experimental wriggle, and it seems as though <she> has complete control over where it goes." + "</br>" + "<She> now has a <b style='color:" + Colour.TRANSFORMATION_PARTIAL
									+ ";'>demon tail</b>."));
			break;
		case DOG_MORPH:
			transformationSB.append(isPlayer()
					? " A furry " + getSkinColour(BodyCoveringType.CANINE_FUR).getName() + " dog-like tail sprouts from just above your ass, rapidly growing in size until it's about half the length of one of your legs."
							+ " You quickly realise that you have little control over it, and it wags with a mind of its own whenever you get excited." + "</br>" + "You now have a <b style='color:" + Colour.TRANSFORMATION_PARTIAL
							+ ";'>dog-like tail</b>."
					: UtilText.genderParsing(this,
							" A furry " + getSkinColour(BodyCoveringType.CANINE_FUR) + " dog-like tail sprouts from just above <her> ass, rapidly growing in size until it's about half the length of one of <her> legs."
									+ " It looks like <she> has little control over it, and it wags with a mind of its own whenever <she> gets excited." + "</br>" + "<She> now has a <b style='color:" + Colour.TRANSFORMATION_PARTIAL
									+ ";'>dog-like tail</b>."));
			break;
		case LYCAN:
			transformationSB.append(isPlayer()
					? " A furry " + getSkinColour(BodyCoveringType.LYCAN_FUR).getName() + " wolf-like tail sprouts from just above your ass, rapidly growing in size until it's just over half the length of one of your legs."
							+ " You quickly realise that you have limited control over it, and it takes a lot of effort to stop it from betraying your emotions." + "</br>" + "You now have a <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>wolf-like tail</b>."
					: UtilText.genderParsing(this,
							" A furry " + getSkinColour(BodyCoveringType.LYCAN_FUR).getName() + " wolf-like tail sprouts from just above <her> ass, rapidly growing in size until it's just over half the length of one of <her> legs."
									+ " It looks like <she> only has limited control over it, and it would take a lot of effort to stop it from betraying <her> emotions." + "</br>" + "<She> now has a <b style='color:"
									+ Colour.TRANSFORMATION_PARTIAL + ";'>wolf-like tail</b>."));
			break;
		case CAT_MORPH:
			transformationSB.append(isPlayer()
					? " A furry " + getSkinColour(BodyCoveringType.FELINE_FUR).getName() + " cat-like tail sprouts from just above your ass, rapidly growing in size until it's almost as long as one of your legs."
							+ " You quickly realise that you have a decent amount of control over it, and you can twist it almost anywhere you please." + "</br>" + "You now have a <b style='color:" + Colour.TRANSFORMATION_PARTIAL
							+ ";'>cat-like tail</b>."
					: UtilText.genderParsing(this,
							" A furry " + getSkinColour(BodyCoveringType.FELINE_FUR).getName() + " cat-like tail sprouts from just above <her> ass, rapidly growing in size until it's almost as long as one of <her> legs."
									+ " It looks like <she> has a decent amount of control over it, and <she> experimentally twists it around <her> leg." + "</br>" + "<She> now has a <b style='color:" + Colour.TRANSFORMATION_PARTIAL
									+ ";'>cat-like tail</b>."));
			break;
		case HORSE_MORPH:
			transformationSB.append(isPlayer()
					? " A " + getSkinColour(BodyCoveringType.HORSE_HAIR).getName() + " horse-like tail sprouts from just above your ass, rapidly growing in length until it hangs to just over half-way down your legs."
							+ " You quickly discover that your control over it is limited to swishing it from side to side." + "</br>" + "You now have a <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>horse-like tail</b>."
					: UtilText.genderParsing(this,
							" A " + getSkinColour(BodyCoveringType.HORSE_HAIR).getName() + " horse-like tail sprouts from just above <her> ass, rapidly growing in length until it hangs to just over half-way down <her> legs."
									+ " <She> gives it a few experimental swishes from side to side, which seems to be the limit of <her> control over it." + "</br>" + "<She> now has a <b style='color:"
									+ Colour.TRANSFORMATION_PARTIAL + ";'>horse-like tail</b>."));
			break;
		case HARPY:
			transformationSB
					.append(isPlayer()
							? " A pretty " + getSkinColour(BodyCoveringType.FEATHERS).getName() + " plume of tail feathers sprout from just above your ass. Each feather quickly grows to about one-third the length of one of your legs."
									+ " You discover that you can quickly raise and lower your new plume, which helps you to keep your balance." + "</br>" + "You now have a <b style='color:" + Colour.TRANSFORMATION_PARTIAL
									+ ";'>bird-like tail</b>."
							: UtilText.genderParsing(this,
									" A pretty " + getSkinColour(BodyCoveringType.FEATHERS).getName() + " plume of tail feathers sprout from just above <her> ass. Each feather quickly grows to about one-third the length of one of <her> legs."
											+ " <She> flicks it up and down, which seems to help <herPro> to balance <herPro>self." + "</br>" + "<She> now has a <b style='color:" + Colour.TRANSFORMATION_PARTIAL
											+ ";'>bird-like tail</b>."));
			break;
		default:
			transformationSB.append(isPlayer()
					? " " + Util.capitaliseSentence(tailType.getDeterminer(this)) + " " + tailType.getName(this) + " grows out from your lower back." + "</br>" + "You now have <b style='color:" + Colour.TRANSFORMATION_PARTIAL
							+ ";'>" + tailType.getDeterminer(this) + " " + tailType.getName(this) + "</b>."
					: UtilText.genderParsing(this, " " + Util.capitaliseSentence(tailType.getDeterminer(this)) + " " + tailType.getName(this) + " grows out from <her> lower back." + "</br>"
							+ "<She> now has <b style='color:" + Colour.TRANSFORMATION_LESSER + ";'>" + tailType.getDeterminer(this) + " " + tailType.getName(this) + "</b>."));
		}

		body.getTail().setType(tailType);
		return transformationSB.toString() + "</br></br>" + postTransformationCalculation() + "</p>";
	}

	public String getTailName() {
		return body.getTail().getName(this);
	}

	public String getTailName(boolean withDescriptor) {
		return body.getTail().getName(this, withDescriptor);
	}

	public String getTailDescriptor() {
		return body.getTail().getType().getDescriptor(this);
	}

	public String getTailDeterminer() {
		return body.getTail().getType().getDeterminer(this);
	}

	public String getTailPronoun() {
		return body.getTail().getType().getPronoun();
	}

	// Vagina:

	public VaginaType getVaginaType() {
		return body.getVagina().getType();
	}
	
	public boolean hasVagina() {
		return body.getVagina().getType() != VaginaType.NONE;
	}

	/**
	 * @return Formatted description of vagina change.
	 */
	public String setVaginaType(VaginaType type) {
		if(transformationSB==null)
			transformationSB = new StringBuilder();
		else
			transformationSB.setLength(0);
		
		if (type == getVaginaType()) {
			if(isPlayer()) {
				if(type == VaginaType.NONE) {
					return "<p style='text-align:center;'>"
							+ "<span style='color:" + Colour.TEXT_GREY + ";'>You already lack a vagina, so nothing happens...</span>"
						+ "</p>";
					
				} else {
					return "<p style='text-align:center;'>"
							+ "<span style='color:" + Colour.TEXT_GREY + ";'>You already have [pc.a_vaginaRace] pussy, so nothing happens...</span>"
						+ "</p>";
				}
			} else {
				if(type == VaginaType.NONE) {
					return "<p style='text-align:center;'>"
							+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] already lacks a vagina, so nothing happens...</span>")
						+ "</p>";
					
				} else {
					return "<p style='text-align:center;'>"
							+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] already has [npc.a_vaginaRace] pussy, so nothing happens...</span>")
						+ "</p>";	
				}
			}
		}
		
		if (isPregnant() || hasStatusEffect(StatusEffect.PREGNANT_0)) {
			if(isPlayer()) {
				transformationSB.append(
						"<p>"
							+ "You feel your [pc.pussy+] start to grow hot and sensitive, and you let out a lewd moan as a wave of tingling excitement washes through your lower abdomen."
							+ " Much to your surprise, the feeling fades away almost as quickly as it came, and you realise that "
							+ (hasStatusEffect(StatusEffect.PREGNANT_0)
									?"<b>the possibility of being pregnant has prevented your vagina from transforming</b>!"
									:"<b>your ongoing pregnancy has prevented your vagina from transforming</b>!")
							+ "</br>"
							+ "Your pussy remains [style.boldTfSex(unchanged)]."
						+ "</p>");
			} else {
				transformationSB.append(UtilText.parse(this,
						"<p>"
							+ "[npc.Name] lets out a lewd moan as [npc.she] feels [npc.her] [npc.pussy+] starting to grow hot and sensitive,"
								+ " and as a wave of tingling excitement washes through [npc.her] lower abdomen, [npc.her] moan turns into a desperate gasp."
							+ " Much to [npc.her] surprise, the feeling fades away almost as quickly as it came, and with a sigh, [npc.she] realises that "
							+ (hasStatusEffect(StatusEffect.PREGNANT_0)
									?"<b>the possibility of being pregnant has prevented [npc.her] vagina from transforming</b>!"
									:"<b>[npc.her] ongoing pregnancy has prevented [npc.her] vagina from transforming</b>!")
							+ "</br>"
							+ "[npc.Name]'s pussy remains [style.boldTfSex(unchanged)]."
						+ "</p>"));
			}
			return transformationSB.toString()
					+ "<p>"
						+postTransformationCalculation()
					+"</p>";
		}
		
		if (getVaginaType() == VaginaType.NONE) {
			if (isPlayer()) {
				transformationSB.append(
						"<p>"
							+ "You feel a strange heat spreading through your groin, and you let out an involuntary moan as you feel the [pc.skin] between your [pc.legs] starting to cave inwards."
							+ " Within moments, a deep furrow has formed "
							+ (getPenisType() == PenisType.NONE
								? "in the middle of your groin,"
								: "beneath your cock,")
							+ " and you start panting and squirming as the strange feeling shows no sign of stopping there."
							+ " A sudden, penetrating sensation tears through your groin, and while it isn't painful, you still cry out in shock as the groove between your legs splits and forms into a new, virgin pussy."
							+ " As the feeling finally starts to fade away, your new clit and labia finish forming, and a trickle of girl-cum leaks out from your excited slit."
						+ "</p>"
						+ "<p>"
							+ "Just as you start thinking that the transformation is over, a warm, tingling feeling shoots up into your lower abdomen,"
								+ " and you can't help but let out a desperate squeal as you feel a full female reproductive system rapidly growing inside of you."
							+ " As your transformation finally comes to an end, you're left panting and covered in sweat, and you feel that your new cunt is already soaking wet from arousal."
							+ "</br>"
							+ "You now have a [style.boldTfSex(vagina)]!"
						+ "</p>");
				
				if(hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					transformationSB.append(
							"<p style='text-align:center;'>"
									+ "[style.boldExcellent(Pure Virgin)]"
							+ "</p>"
							+ "<p>"
								+ "You can't remember the last time you felt so good."
								+ " As your new pussy finishes growing, you realise that you're now technically a virgin once more."
								+ " After all, this pussy has never been penetrated before!"
							+ "</p>"
							+ "<p style='text-align:center;'>"
								+ "[pc.thought(I-I'm a virgin?!"
								+ "</br>"
								+ "Haha! I'm a virgin!)]"
							+ "</p>"
							+ "<p>"
								+ "The elation you feel at discovering that you're a virgin again is unlike anything you've ever felt before."
								+ " Tears start to well up in your eyes as you find yourself overcome with joy."
							+ "</p>"
							+ "<p>"
								+ "You are invincible."
								+ " You can overcome any obstacle that's placed in your way."
								+ " You are..."
							+ "</p>"
							+ "<p style='text-align:center;'>"
							+ "<b>A </b>[style.boldExcellent(Pure Virgin)]<b>!</b>"
							+ "</p>"
							);
				}
				
			} else {
				transformationSB.append(UtilText.parse(this,
						"<p>"
							+ "[npc.Name] blushes as [npc.she] feels a strange heat spreading through [npc.her] groin,"
								+ " and can't help but let out an involuntary moan as [npc.she] feels the [npc.skin] between [npc.her] [npc.legs] starting to cave inwards."
							+ " Within moments, a deep furrow has formed "
							+ (getPenisType() == PenisType.NONE
								? "in the middle of [npc.her] groin,"
								: "beneath [npc.her] cock,")
							+ " and [npc.she] starts panting and squirming as the strange feeling shows no sign of stopping there."
							+ " A sudden, penetrating sensation tears through [npc.her] groin, and while it isn't painful, [npc.she] still cries out in shock as the groove between [npc.her] legs splits and forms into a new, virgin pussy."
							+ " As the feeling finally starts to fade away, [npc.her] new clit and labia finish forming, and a trickle of girl-cum leaks out from [npc.her] excited slit."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts to think that the transformation is over, a warm, tingling feeling shoots up into [npc.her] lower abdomen,"
								+ " and [npc.she] can't help but let out a desperate squeal as [npc.she] feels a full female reproductive system rapidly growing inside of [npc.herHim]."
							+ " As [npc.her] transformation finally comes to an end, [npc.she]'s left panting and covered in sweat, and [npc.she] lets out a lewd moan as [npc.she] feels that [npc.her] new cunt is already soaking wet from arousal."
							+ "</br>"
							+ "[npc.Name] now has a [style.boldTfSex(vagina)]!"
						+ "</p>"));
			}

			body.getVagina().setType(VaginaType.HUMAN);
			
			if(type==VaginaType.HUMAN) {
				return transformationSB.toString()
						+ "<p>"
							+postTransformationCalculation()
						+"</p>";
			} else {
				return transformationSB.toString()
						+ setVaginaType(type);
			}
			
		} else {
			if (isPlayer()) {
				transformationSB.append(
						"<p>"
							+ "A strange heat suddenly washes through your pussy, and you cry out as it starts to transform.");
			} else {
				transformationSB.append(UtilText.parse(this,
						"<p>"
							+"[npc.Name] suddenly blushes and squeezes [npc.her] thighs together as [npc.she] feels [npc.her] pussy starting to transform."));
			}
		}

		switch (type) {
			case NONE:
				if(isPlayer()) {
					transformationSB.append(
								" You feel your slit suddenly tighten and squeeze shut, and you let out a cry as a strange pressure fills your lower abdomen."
								+ " Thankfully, it doesn't last long, and when the discomfort subsides, you gasp as you suddenly realise that your cunt has vanished, along with all of your female reproductive organs."
								+ "</br>"
								+ "You have [style.boldBad(lost your vagina)]."
							+ "</p>");
				} else {
					transformationSB.append(UtilText.parse(this,
							" [npc.She] moans and squirms about as [npc.she] feels [npc.her] [npc.pussy] suddenly tighten and squeeze shut, and with a desperate cry, a strange pressure washes through [npc.her] lower abdomen."
							+ " Thankfully for [npc.herHim], the feeling fades almost as quickly as it came, and when the discomfort subsides, [npc.she] gasps as [npc.she] suddenly realises that [npc.her] cunt has vanished,"
								+ " along with all of [npc.her] female reproductive organs."
							+ "</br>"
							+ "[npc.Name] has [style.boldBad(lost [npc.her] vagina)]."
						+ "</p>"));
				}
				
				if(isPlayer() && hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					if(!isVaginaVirgin()) {
						transformationSB.append(
								"<p style='text-align:center;'>"
									+ "[style.boldGood(Unbroken Virgin)]"
								+ "</p>"
								+ "<p>"
									+ "As the reality of losing your worthless fuck-hole finally sinks in, you start to think of yourself as something more than just a cheap whore."
									+ " After all, if you don't have a pussy, it's technically not possible for you to have lost your virginity!"
								+ "</p>"
								+ "<p style='text-align:center;'>"
										+"[pc.thought(I-I'm no longer just a worthless fuck-toy..."
										+ "</br>"
										+ "It's not possible for me to have lost my virginity if I don't have a vagina!)]"
								+ "</p>"
								+ "<p>"
									+ "With a deep sigh, you realise that you no longer have to worry about being a broken-in slut."
									+ " Despite the fact that you can now finally see yourself as a real person again, not having a pussy is making you feel a little restless."
								+ "</p>"
								+ "<p style='text-align:center;'>"
									+"[pc.thought(I need to find a way to get a new pussy!"
										+ "</br>"
										+ "Then I'll be a pure virgin again!)]"
								+ "</p>"
								+ "<p style='text-align:center;'>"
									+ "<b>You are</b> [style.boldGood(no longer a)] [style.boldTerrible(Broken Virgin)]<b>!</b>"
								+ "</p>"
								);
					} else {
						transformationSB.append(
								"<p style='text-align:center;'>"
										+ "[style.boldBad(Pure Virgin?)]"
								+ "</p>"
								+ "<p>"
									+ "As the reality of losing your vagina finally sinks in, you start to feel a rising panic in the back of your mind."
									+ " After all, if you don't have a pussy, you can't call yourself a pure virgin!"
								+ "</p>"
								+ "<p style='text-align:center;'>"
									+ "[pc.thought(~Aah!~ I need to get my pussy back!)]"
								+ "</p>"
								+ "<p style='text-align:center;'>"
									+ "<b>Until you get your vagina back, you are</b> [style.boldBad(no longer a)] [style.boldExcellent(Pure Virgin)]<b>!</b>"
								+ "</p>"
								);
					}
				}
				
				setVaginaVirgin(true);
				if(isPiercedVagina()) {
					setPiercedVagina(false);
				}
				break;
			case HUMAN:
				if(isPlayer()) {
					transformationSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Within moments, the feeling fades away, and you discover that your "+getVaginaName(true)+" has shaped itself into a regular human vagina."
							+ " As you let out a little sigh, a warm, tingling feeling spreads up through your lower abdomen, and you realise that your internal reproductive organs have also transformed into those of a human."
						+ "</p>"
						+ "<p>"
							+ "You now have the [style.boldTfSex(female reproductive system of a human)], complete with a hot little [style.boldTfSex(human pussy)]."
						+ "</p>");
				} else {
					transformationSB.append(UtilText.parse(this, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " Within moments, the feeling starts to fade away, and with a little cry of surprise, [npc.she] discovers that [npc.her] "+getVaginaName(true)+" has shaped itself into a regular human vagina."
							+ " A warm, tingling feeling spreads up through [npc.her] lower abdomen, and as the transformation comes to an end, [npc.she] realises that [npc.her] internal reproductive organs have also transformed into those of a human."
						+ "</p>"
						+ "<p>"
							+ "[npc.Name] now has the [style.boldTfSex(female reproductive system of a human)], complete with a hot little [style.boldTfSex(human pussy)]."
						+ "</p>"));
				}
				break;
			case DEMON_COMMON:
				if(isPlayer()) {
					transformationSB.append(
								" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
								+ " A strange, bubbling sensation starts running down deep into your cunt, and you let out a lewd moan as you feel rows of little wriggling tentacles grow out to line your inner vaginal walls."
								+ " Gasping for breath, you feel a new set of muscles forming within your pussy, and with an experimental squeeze, you discover that you have an incredible amount of control over your pussy's new additions."
								+ " With one last shiver of pleasure, your outer folds turn a shade of "+getSkinColour(type.getSkinType()).getName()+" as your pussy reshapes its exterior into the most perfect-looking vagina you've ever seen."
							+ "</p>"
							+ "<p>"
								+ "Just as you think that the transformation has come to an end, your pussy's new tentacles and muscles involuntarily clench down,"
									+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
								+ " Images of fat demonic cocks slamming deep into your new pussy flash before your eyes, and your squeal turns into a satisfied moan as you imagine them pumping their tainted seed deep into your demonic womb."
								+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
							+ "</p>"
							+ "<p>"
								+ "You now have the [style.boldTfSex(female reproductive system of a demon)], complete with a perfectly-formed [style.boldTfSex(demonic pussy)]."
							+ "</p>");
				} else {
					transformationSB.append(UtilText.parse(this,
								" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
										+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
								+ " A strange, bubbling sensation starts running down deep into [npc.her] cunt, and [npc.she] lets out a lewd moan as [npc.she] feels rows of little wriggling tentacles grow out to line [npc.her] inner vaginal walls."
								+ " Gasping for breath, [npc.she] feels a new set of muscles forming within [npc.her] pussy, and with an experimental squeeze,"
									+ " [npc.she] discovers that [npc.she] has an incredible amount of control over [npc.her] pussy's new additions."
								+ " With one last shiver of pleasure, [npc.her] outer folds turn a shade of "+getSkinColour(type.getSkinType()).getName()+" as [npc.her] pussy reshapes its exterior into an absolutely perfect-looking vagina."
							+ "</p>"
							+ "<p>"
								+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy's new tentacles and muscles involuntarily clench down,"
									+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
								+ " Images of fat demonic cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
									+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them pumping their tainted seed deep into [npc.her] demonic womb."
								+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
									+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
							+ "</p>"
							+ "<p>"
								+ "[npc.Name] now has the [style.boldTfSex(female reproductive system of a demon)], complete with a perfectly-formed [style.boldTfSex(demonic pussy)]."
							+ "</p>"));
				}
				break;
			case DOG_MORPH:
				if(isPlayer()) {
					transformationSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Within moments, the feeling fades away, and you discover that your "+getVaginaName(true)+" has shaped itself into a that of a dog-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+getSkinColour(type.getSkinType()).getName()+" fur has grown around it, giving your feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of knotted dog-like cocks slamming deep into your new pussy flash before your eyes, and your squeal turns into a satisfied moan as you imagine them pumping their potent seed deep into your canine womb."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
						+ "</p>"
						+ "<p>"
							+ "You now have the [style.boldTfSex(female reproductive system of a dog-morph)], complete with an animalistic [style.boldTfSex(canine pussy)]."
						+ "</p>");
				} else {
					transformationSB.append(UtilText.parse(this, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " Within moments, the feeling fades away, and [npc.she] discovers that [npc.her] "+getVaginaName(true)+" has shaped itself into a that of a dog-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+getSkinColour(type.getSkinType()).getName()+" fur has grown around it, giving [npc.her] feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of knotted dog-like cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them pumping their potent seed deep into [npc.her] canine womb."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
						+ "</p>"
						+ "<p>"
							+ "[npc.Name] now has the [style.boldTfSex(female reproductive system of a dog-morph)], complete with an animalistic [style.boldTfSex(canine pussy)]."
						+ "</p>"));
				}
				break;
			case WOLF_MORPH:
				if(isPlayer()) {
					transformationSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Within moments, the feeling fades away, and you discover that your "+getVaginaName(true)+" has shaped itself into a that of a wolf-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+getSkinColour(type.getSkinType()).getName()+" fur has grown around it, giving your feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of knotted wolf-like cocks slamming deep into your new pussy flash before your eyes, and your squeal turns into a satisfied moan as you imagine them pumping their potent seed deep into your lupine womb."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
						+ "</p>"
						+ "<p>"
							+ "You now have the [style.boldTfSex(female reproductive system of a wolf-morph)], complete with an animalistic [style.boldTfSex(lupine pussy)]."
						+ "</p>");
				} else {
					transformationSB.append(UtilText.parse(this, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " Within moments, the feeling fades away, and [npc.she] discovers that [npc.her] "+getVaginaName(true)+" has shaped itself into a that of a wolf-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+getSkinColour(type.getSkinType()).getName()+" fur has grown around it, giving [npc.her] feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of knotted wolf-like cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them pumping their potent seed deep into [npc.her] lupine womb."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
						+ "</p>"
						+ "<p>"
							+ "[npc.Name] now has the [style.boldTfSex(female reproductive system of a wolf-morph)], complete with an animalistic [style.boldTfSex(lupine pussy)]."
						+ "</p>"));
				}
				break;
			case CAT_MORPH:
				if(isPlayer()) {
					transformationSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Within moments, the feeling fades away, and you discover that your "+getVaginaName(true)+" has shaped itself into a that of a cat-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+getSkinColour(type.getSkinType()).getName()+" fur has grown around it, giving your feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of barbed cat-like cocks slamming deep into your new pussy flash before your eyes, and your squeal turns into a satisfied moan as you imagine them pumping their potent seed deep into your feline womb."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
						+ "</p>"
						+ "<p>"
							+ "You now have the [style.boldTfSex(female reproductive system of a cat-morph)], complete with an animalistic [style.boldTfSex(feline pussy)]."
						+ "</p>");
				} else {
					transformationSB.append(UtilText.parse(this, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " Within moments, the feeling fades away, and [npc.she] discovers that [npc.her] "+getVaginaName(true)+" has shaped itself into a that of a cat-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+getSkinColour(type.getSkinType()).getName()+" fur has grown around it, giving [npc.her] feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of barbed cat-like cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them pumping their potent seed deep into [npc.her] feline womb."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
						+ "</p>"
						+ "<p>"
							+ "[npc.Name] now has the [style.boldTfSex(female reproductive system of a cat-morph)], complete with an animalistic [style.boldTfSex(feline pussy)]."
						+ "</p>"));
				}
				break;
			case HORSE_MORPH:
				if(isPlayer()) {
					transformationSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Your pussy lips puff up and darken to a deep black, and you find yourself moaning and squirming as your cunt reshapes itself into a horse-like vagina, giving your feminine sex a very animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of huge, flared horse-cocks slamming deep into your new pussy flash before your eyes, and your squeal turns into a satisfied moan as you imagine them pumping their hot seed deep into your equine womb."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
						+ "</p>"
						+ "<p>"
							+ "You now have the [style.boldTfSex(female reproductive system of a horse-morph)], complete with an animalistic [style.boldTfSex(equine pussy)]."
						+ "</p>");
				} else {
					transformationSB.append(UtilText.parse(this, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " [npc.Her] pussy lips puff up and darken to a deep black, and [npc.she] starts moaning and squirming as [npc.her] cunt reshapes itself into a horse-like vagina, giving [npc.her] feminine sex a very animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of huge, flared horse-cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them pumping their potent seed deep into [npc.her] equine womb."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
						+ "</p>"
						+ "<p>"
							+ "[npc.Name] now has the [style.boldTfSex(female reproductive system of a horse-morph)], complete with an animalistic [style.boldTfSex(equine pussy)]."
						+ "</p>"));
				}
				break;
			case HARPY:
				if(isPlayer()) {
					transformationSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Within moments, the feeling fades away, and you discover that your "+getVaginaName(true)+" has shaped itself into a that of a harpy."
							+ " Although it looks like a normal human pussy, a short layer of soft "+getSkinColour(type.getSkinType()).getName()+" feathers have grown around it, giving your feminine sex a rather avian appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of slamming your pussy down on cute little harpy cocks flash before your eyes, and your squeal turns into a satisfied moan as you imagine them squirting their hot seed deep into your hungry avian cunt."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
						+ "</p>"
						+ "<p>"
							+ "You now have the [style.boldTfSex(female reproductive system of a harpy)], complete with an [style.boldTfSex(avian pussy)]."
						+ "</p>");
				} else {
					transformationSB.append(UtilText.parse(this, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " Within moments, the feeling fades away, and [npc.she] discovers that [npc.her] "+getVaginaName(true)+" has shaped itself into a that of a harpy."
							+ " Although it looks like a normal human pussy, a short layer of soft "+getSkinColour(type.getSkinType()).getName()+" feathers have grown around it, giving [npc.her] feminine sex a rather avian appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of slamming [npc.her] pussy down on cute little harp cocks flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them squirting their hot seed deep into [npc.her] hungry avian cunt."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
						+ "</p>"
						+ "<p>"
							+ "[npc.Name] now has the [style.boldTfSex(female reproductive system of a harpy)], complete with an [style.boldTfSex(avian pussy)]."
						+ "</p>"));
				}
				break;
			case SLIME://TODO turns into dripping slime
				transformationSB.append(isPlayer()
						? " You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
								+ " Your pussy lips puff up and darken to a deep black, and you find yourself moaning and squirming as your cunt reshapes itself into a horse-like vagina." + "</br>" + "You now have a <b style='color:"
								+ Colour.TRANSFORMATION_SEXUAL + ";'>horse-pussy</b>."
						: UtilText.genderParsing(this,
								" <She> lets out a squeal of excitement as a wave of pleasure runs up from <her> groin, and <her> slit starts shifting and contracting with a mind of its own."
										+ " Within moments, the transformation ends, and <she> discovers that she's now got a horse-like vagina." + "</br>" + "<She> now has a <b style='color:" + Colour.TRANSFORMATION_SEXUAL
										+ ";'>horse-pussy</b>."));
				break;
			default:
				transformationSB.append(isPlayer()
						? Util.capitaliseSentence(type.getDeterminer(this)) + " " + type.getName(this) + " grows between your legs." + "</br>" + "You now have a <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>"
								+ type.getName(this) + "</b>."
						: UtilText.genderParsing(this, Util.capitaliseSentence(type.getDeterminer(this)) + " " + type.getName(this) + " grows between <her> legs." + "</br>" + "<She> now has a <b style='color:"
								+ Colour.TRANSFORMATION_SEXUAL + ";'>" + type.getName(this) + "</b>."));
		}
		
		body.getVagina().setType(type);
		return transformationSB.toString()
				+ "<p>"
					+ postTransformationCalculation()
				+ "</p>";
	}

//	public String getVaginaName() {
//		return body.getVagina().getName();
//	}

	public String getVaginaName(boolean withDescriptor) {
		return body.getVagina().getName(this, withDescriptor);
	}

	public String getVaginaDescriptor() {
		return body.getVagina().getType().getDescriptor(this);
	}

	public String getVaginaDeterminer() {
		return body.getVagina().getType().getDeterminer(this);
	}

	public String getVaginaPronoun() {
		return body.getVagina().getType().getPronoun();
	}

	public String getVaginaDescription() {
		return body.getVaginaDescription(this);
	}

	public boolean isVaginaVirgin() {
		return body.getVagina().isVirgin();
	}

	public void setVaginaVirgin(boolean virgin) {
		body.getVagina().setVirgin(virgin);
	}

	// Capacity:
	public Capacity getVaginaCapacity() {
		return body.getVagina().getCapacity();
	}

	public float getVaginaRawCapacityValue() {
		return body.getVagina().getRawCapacityValue();
	}
	
	public float getVaginaStretchedCapacity() {
		return body.getVagina().getStretchedCapacity();
	}
	public void setVaginaStretchedCapacity(float capacity){
		body.getVagina().setStretchedCapacity(capacity);
	}
	public void incrementVaginaStretchedCapacity(float increment){
		body.getVagina().setStretchedCapacity(getVaginaStretchedCapacity() + increment);
	}

	/**
	 * @return Formatted description of vagina capacity change.
	 */
	public String setVaginaCapacity(float capacity) {
		if(hasVagina()) {
			if (getVaginaRawCapacityValue() < capacity) {
				if (body.getVagina().setCapacity(capacity))
					if (isPlayer())
						return "<p>" + "You let out a gasp as you feel your pussy slacken and stretch wider." + "</br>" + "As the transformation ends, you find that <b style='color:" + Colour.TRANSFORMATION_SEXUAL
								+ ";'>your pussy is now " + getVaginaCapacity().getDescriptor() + "</b>!" + "</p>";
					else
						return UtilText.genderParsing(this, "<p>" + "<She> lets out a gasp as <her> pussy slackens and stretches wider." + "</br>" + "As the transformation ends, <she> discovers that <b style='color:"
								+ Colour.TRANSFORMATION_SEXUAL + ";'><her> pussy is now " + getVaginaCapacity().getDescriptor() + "</b>!" + "</p>");
			} else {
				if (body.getVagina().setCapacity(capacity))
					if (isPlayer())
						return "<p>" + "You let out a gasp as you feel your pussy contract and get tighter." + "</br>" + "As the transformation ends, you find that <b style='color:" + Colour.TRANSFORMATION_SEXUAL
								+ ";'>your pussy is now " + getVaginaCapacity().getDescriptor() + "</b>!" + "</p>";
					else
						return UtilText.genderParsing(this, "<p>" + "<She> lets out a gasp as <her> pussy contracts and gets tighter." + "</br>" + "As the transformation ends, <she> discovers that <b style='color:"
								+ Colour.TRANSFORMATION_SEXUAL + ";'><her> pussy is now " + getVaginaCapacity().getDescriptor() + "</b>!" + "</p>");
			}
			
			if(isPlayer()) {
				return "<p class='center'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>Your [pc.pussy]'s capacity doesn't change...</span>"
					+ "</p>";
			} else {
				return "<p class='center'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The capacity of [npc.name]'s [npc.pussy] doesn't change...</span>")
					+ "</p>";
			}
			
		} else {
			if(isPlayer()) {
				return "<p class='center'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>You don't have a vagina, so nothing happens...</span>"
					+ "</p>";
			} else {
				return "<p class='center'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] doesn't have a vagina, so nothing happens...</span>")
					+ "</p>";
			}
		}
	}

	public String incrementVaginaCapacity(float increment) {
		return setVaginaCapacity(getVaginaRawCapacityValue() + increment);
	}

	// Wetness:
	public Wetness getVaginaWetness() {
		return body.getVagina().getWetness();
	}

	/**
	 * @return Formatted description of vagina wetness change.
	 */
	public String setVaginaWetness(int wetness) {
		if (hasVagina()) {
			if (getVaginaWetness().getValue() < wetness) {
				if (body.getVagina().setWetness(wetness))
					if (isPlayer())
						return "<p>" + "You let out a lewd moan as you feel moisture start beading on the surface of your pussy lips." + " A trickle of girl-cum leaks from your slit, signalling that your cunt has become wetter." + "</br>"
								+ "Your <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>pussy is now " + getVaginaWetness().getDescriptor() + "</b>!" + "</p>";
					else
						return UtilText.genderParsing(this,
								"<p>" + "<She> lets out a lewd moan as moisture starts beading on the surface of <her> pussy lips." + " A trickle of girl-cum leaks from <her> slit, signalling that <her> cunt has become wetter." + "</br>"
										+ "<Her> <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>pussy is now " + getVaginaWetness().getDescriptor() + "</b>!" + "</p>");
			} else {
					if (body.getVagina().setWetness(wetness))
						if (isPlayer())
							return "<p>" + "You let out a frustrated groan as you feel your pussy drying up." + "</br>" + "Your <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>pussy is now " + getVaginaWetness().getDescriptor()
									+ "</b>!" + "</p>";
						else
							return UtilText.genderParsing(this, "<p>" + "<She> lets out a frustrated groan as <her> pussy dries up." + "</br>" + "<Her> <b style='color:" + Colour.TRANSFORMATION_SEXUAL
									+ ";'>pussy is now " + getVaginaWetness().getDescriptor() + "</b>!" + "</p>");
			}
	
			if(isPlayer()) {
				return "<p class='center'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>Your [pc.pussy]'s wetness doesn't change...</span>"
					+ "</p>";
			} else {
				return "<p class='center'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The wetness of [npc.name]'s [npc.pussy] doesn't change...</span>")
					+ "</p>";
			}
			
		} else {
			if(isPlayer()) {
				return "<p class='center'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>You don't have a vagina, so nothing happens...</span>"
					+ "</p>";
			} else {
				return "<p class='center'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] doesn't have a vagina, so nothing happens...</span>")
					+ "</p>";
			}
		}
	}

	public String incrementVaginaWetness(int increment) {
		return setVaginaWetness(getVaginaWetness().getValue() + increment);
	}

	// Clitoris size:
	public ClitorisSize getVaginaClitorisSize() {
		return body.getVagina().getClitorisSize();
	}

	public int getVaginaRawClitorisSizeValue() {
		return body.getVagina().getRawClitorisSizeValue();
	}

	/**
	 * @return Formatted description of clitoris size change.
	 */
	public String setVaginaClitorisSize(int size) {
		if(hasVagina()) {
			if (getVaginaRawClitorisSizeValue() < size) {
				if (body.getVagina().setClitorisSize(size))
					if (isPlayer())
						return "<p>" + "You gasp and squirm as a tingling sensation shoots up through your pussy." + " After a moment, the feeling fades away, and you realise that your clit has grown larger." + "</br>" + "Your <b style='color:"
								+ Colour.TRANSFORMATION_SEXUAL + ";'>clit is now " + getVaginaClitorisSize().getDescriptor() + "</b>!" + "</p>";
					else
						return UtilText.genderParsing(this,
								"<p>" + "<She> gasps and squirms as a tingling sensation shoots up through <her> pussy." + " After a moment, the feeling fades away, and <she> realises that <her> clit has grown larger." + "</br>"
										+ "<Her> <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>clit is now " + getVaginaClitorisSize().getDescriptor() + "</b>!" + "</p>");
			} else {
				if (body.getVagina().setClitorisSize(size))
					if (isPlayer())
						return "<p>" + "You gasp and squirm as a tugging sensation shoots up through your pussy." + " After a moment, the feeling fades away, and you realise that your clit has shrunk." + "</br>" + "Your <b style='color:"
								+ Colour.TRANSFORMATION_SEXUAL + ";'>clit is now " + getVaginaClitorisSize().getDescriptor() + "</b>!" + "</p>";
					else
						return UtilText.genderParsing(this,
								"<p>" + "<She> gasps and squirms as a tugging sensation shoots up through <her> pussy." + " After a moment, the feeling fades away, and <she> realises that <her> clit has shrunk." + "</br>" + "<Her> <b style='color:"
										+ Colour.TRANSFORMATION_SEXUAL + ";'>clit is now " + getVaginaClitorisSize().getDescriptor() + "</b>!" + "</p>");
			}
	
			if(isPlayer()) {
				return "<p class='center'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>The size of your clitoris doesn't change...</span>"
					+ "</p>";
			} else {
				return "<p class='center'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The size of [npc.name]'s clitoris doesn't change...</span>")
					+ "</p>";
			}
			
		} else {
			if(isPlayer()) {
				return "<p class='center'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>You don't have a vagina, so nothing happens...</span>"
					+ "</p>";
			} else {
				return "<p class='center'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] doesn't have a vagina, so nothing happens...</span>")
					+ "</p>";
			}
		}
	}

	public String incrementVaginaClitorisSize(int increment) {
		return setVaginaClitorisSize(getVaginaRawClitorisSizeValue() + increment);
	}

	// Vagina plasticity:
	public OrificeElasticity getVaginaElasticity() {
		return body.getVagina().getElasticity();
	}

	public String setVaginaElasticity(int plasticity) {
		if(hasVagina()) {
			if (getVaginaElasticity().getValue() < plasticity) {
				if (body.getVagina().setElasticity(plasticity))
					if (isPlayer())
						return "</p>"
									+ "You let out a high-pitched squeal as you feel a strange slackening sensation pulsating deep within your pussy."
									+ "</br>"
									+ "Your <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>vagina is now " + getVaginaElasticity().getDescriptor() + "</b>!"
								+ "</p>";
					else
						return UtilText.genderParsing(this,
								"</p>"
									+ "<She> lets out a high-pitched squeal as a strange slackening sensation pulsates deep within <her> pussy."
									+ "</br>"
									+ "<Her> <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>vagina is now " + getVaginaElasticity().getDescriptor() + "</b>!"
								+ "</p>");
			} else {
				if (body.getVagina().setElasticity(plasticity))
					if (isPlayer())
						return "</p>"
									+ "You let out a high-pitched squeal as you feel a strange clenching sensation pulsating deep within your pussy."
									+ "</br>"
									+ "Your <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>vagina is now " + getVaginaElasticity().getDescriptor() + "</b>!" 
								+ "</p>";
					else
						return UtilText.genderParsing(this,
								"</p>"
									+ "<She> lets out a high-pitched squeal as a strange clenching sensation pulsates deep within <her> pussy."
									+ "</br>"
									+ "<Her> <b style='color:" + Colour.TRANSFORMATION_SEXUAL + ";'>vagina is now " + getVaginaElasticity().getDescriptor() + "</b>!"
								+ "</p>");
			}
	
			if(isPlayer()) {
				return "<p class='center'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>Your [pc.pussy]'s elasticity doesn't change...</span>"
					+ "</p>";
			} else {
				return "<p class='center'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>The elasticity of [npc.name]'s [npc.pussy] doesn't change...</span>")
					+ "</p>";
			}
			
		} else {
			if(isPlayer()) {
				return "<p class='center'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>You don't have a vagina, so nothing happens...</span>"
					+ "</p>";
			} else {
				return "<p class='center'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] doesn't have a vagina, so nothing happens...</span>")
					+ "</p>";
			}
		}
	}

	public String incrementVaginaElasticity(int increment) {
		return setVaginaElasticity(getVaginaElasticity().getValue() + increment);
	}

//	public boolean isVaginaCreampied(){
//		return body.getVagina().isCreampied();
//	}
//
//	public void setVaginaCreampied(boolean creampied){
//		body.getVagina().setCreampied(creampied);
//	}

	// Mound:
	public String getMoundDescription() {
		return body.getMoundDescription(this);
	}

	// Wing:
	
	public WingType getWingType() {
		return body.getWing().getType();
	}

	/**
	 * @return Formatted description of clitoris size change.
	 */
	public String setWingType(WingType wingType) {
		if (wingType == getWingType()) {
			if(isPlayer()) {
				return "<p style='text-align:center;'>"
						+ "<span style='color:" + Colour.TEXT_GREY + ";'>You already have the [pc.wings] of a [pc.wingRace], so nothing happens...</span>"
					+ "</p>";
			} else {
				return "<p style='text-align:center;'>"
						+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY + ";'>[npc.Name] already has the [npc.wings] of a [npc.wingRace], so nothing happens...</span>")
					+ "</p>";	
			}
		}
		
		if (getWingType() == WingType.NONE) {
			if (isPlayer())
				transformationSB = new StringBuilder("<p>" + "You feel a strange bubbling feeling rising up in your back, and as you start to wonder what's going on, you feel something pushing out from under your " + getSkinType().getName(this) + ".");
			else
				transformationSB = new StringBuilder(UtilText.genderParsing(this,
						"<p>" + getName("The") + " tries to look round behind <herPro> as something starts to push out from under the " + getSkinType().getName(this) + " of <her> back."));
		} else {
			if (isPlayer())
				transformationSB = new StringBuilder("<p>" + "Your " + getWingType().getName(this) + " suddenly start to twitch and wriggle with a mind of their own, and you let out a gasp as you feel them start to transform.");
			else
				transformationSB = new StringBuilder(UtilText.genderParsing(this, "<p>" + getName("The") + " tries to look back at <her> " + getWingType().getName(this) + " as they start to transform."));
		}

		switch (wingType) {
		case NONE:
			transformationSB.append(isPlayer()
					? " With a strong tugging sensation, your " + getWingType().getName(this) + " shrink away into the flesh of your back." + "</br>" + "Your <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>"
							+ getWingType().getName(this) + " have disappeared</b>!"
					: UtilText.genderParsing(this, " With a strong tugging sensation, <her> " + getWingType().getName(this) + " shrink away into the flesh of <her> back." + "</br>" + "<Her> <b style='color:"
							+ Colour.TRANSFORMATION_PARTIAL + ";'>" + getWingType().getName(this) + " have disappeared</b>!"));
			break;
		case DEMON_COMMON:
			transformationSB.append(isPlayer()
					? " You bite your lip to try and suppress an unexpected moan of pleasure as a pair of cute little bat-like wings push out behind you." + " You give them an experimental flutter, but they seem to be too small to be of any use."
							+ "</br>" + "You now have <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>demon wings</b>!"
					: UtilText.genderParsing(this, " <She> bites <her> lip to try and suppress an unexpected moan of pleasure as a pair of cute little bat-like wings push out behind <her>."
							+ " <She> gives them an experimental flutter, but they seem to be too small to be of any use." + "</br>" + "<She> now has <b style='color:" + Colour.TRANSFORMATION_PARTIAL + ";'>demon wings</b>!"));
			break;
		default:
			transformationSB.append(isPlayer()
					? Util.capitaliseSentence(wingType.getDeterminer(this)) + " " + wingType.getName(this) + " grow out from your upper back." + "</br>" + "You now have <b style='color:" + Colour.TRANSFORMATION_LESSER + ";'>"
							+ wingType.getName(this) + "</b>."
					: UtilText.genderParsing(this, Util.capitaliseSentence(wingType.getDeterminer(this)) + " " + wingType.getName(this) + " grow out from <her> upper back." + "</br>" + "<She> now has <b style='color:"
							+ Colour.TRANSFORMATION_LESSER + ";'>" + wingType.getName(this) + "</b>."));
		}

		body.getWing().setType(wingType);
		return transformationSB.toString() + "</br></br>" + postTransformationCalculation() + "</p>";
	}

	public String getWingName() {
		return body.getWing().getName(this);
	}

	public String getWingName(boolean withDescriptor) {
		return body.getWing().getName(this, withDescriptor);
	}

	public String getWingDescriptor() {
		return body.getWing().getType().getDescriptor(this);
	}

	public String getWingDeterminer() {
		return body.getWing().getType().getDeterminer(this);
	}

	public String getWingPronoun() {
		return body.getWing().getType().getPronoun();
	}

	public Map<CoverableArea, Boolean> getPlayerKnowsAreasMap() {
		return playerKnowsAreasMap;
	}

}
