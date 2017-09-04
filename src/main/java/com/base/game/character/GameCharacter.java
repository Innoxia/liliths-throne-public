package com.base.game.character;

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

import com.base.game.character.attributes.Attribute;
import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.Arm;
import com.base.game.character.body.Ass;
import com.base.game.character.body.Body;
import com.base.game.character.body.BodyPartInterface;
import com.base.game.character.body.Breast;
import com.base.game.character.body.Covering;
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
import com.base.game.character.body.types.AntennaType;
import com.base.game.character.body.types.ArmType;
import com.base.game.character.body.types.AssType;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.types.BreastType;
import com.base.game.character.body.types.EarType;
import com.base.game.character.body.types.EyeType;
import com.base.game.character.body.types.FaceType;
import com.base.game.character.body.types.HairType;
import com.base.game.character.body.types.HornType;
import com.base.game.character.body.types.LegType;
import com.base.game.character.body.types.NippleType;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.SkinType;
import com.base.game.character.body.types.TailType;
import com.base.game.character.body.types.TongueType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.types.WingType;
import com.base.game.character.body.valueEnums.AreolaeShape;
import com.base.game.character.body.valueEnums.AreolaeSize;
import com.base.game.character.body.valueEnums.AssSize;
import com.base.game.character.body.valueEnums.BodyHair;
import com.base.game.character.body.valueEnums.BodyMaterial;
import com.base.game.character.body.valueEnums.BodyShape;
import com.base.game.character.body.valueEnums.BodySize;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.ClitorisSize;
import com.base.game.character.body.valueEnums.CoveringPattern;
import com.base.game.character.body.valueEnums.CumProduction;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.body.valueEnums.EyeShape;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.body.valueEnums.FluidFlavour;
import com.base.game.character.body.valueEnums.FluidModifier;
import com.base.game.character.body.valueEnums.HairLength;
import com.base.game.character.body.valueEnums.HairStyle;
import com.base.game.character.body.valueEnums.HipSize;
import com.base.game.character.body.valueEnums.Lactation;
import com.base.game.character.body.valueEnums.LipSize;
import com.base.game.character.body.valueEnums.Muscle;
import com.base.game.character.body.valueEnums.NippleShape;
import com.base.game.character.body.valueEnums.NippleSize;
import com.base.game.character.body.valueEnums.OrificeElasticity;
import com.base.game.character.body.valueEnums.OrificeModifier;
import com.base.game.character.body.valueEnums.OrificePlasticity;
import com.base.game.character.body.valueEnums.PenisModifier;
import com.base.game.character.body.valueEnums.PenisSize;
import com.base.game.character.body.valueEnums.TesticleSize;
import com.base.game.character.body.valueEnums.TongueLength;
import com.base.game.character.body.valueEnums.TongueModifier;
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
import com.base.game.inventory.item.ItemEffect;
import com.base.game.inventory.item.ItemType;
import com.base.game.inventory.weapon.AbstractWeapon;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.PregnancyDescriptor;
import com.base.game.sex.SexType;
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
 * @version 0.1.83
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

	protected List<GameCharacter> slavesOwned;
	protected GameCharacter owner;
	
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

		this.nameTriplet = nameTriplet;
		playerKnowsName = true;
		this.description = description;
		this.level = level;
		
		this.worldLocation = worldLocation;
		this.startingPlace = startingPlace;
		location = new Vector2i(0, 0);
		
		history = History.NEUTRAL;

		slavesOwned = new ArrayList<>();
		owner = null;
		
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
				new Arm((stage.isArmFurry()?startingBodyType.getArmType():ArmType.HUMAN), startingBodyType.getArmRows()),
				new Ass(stage.isAssFurry()?startingBodyType.getAssType():AssType.HUMAN,
						(startingGender.isFeminine() ? startingBodyType.getFemaleAssSize() : startingBodyType.getMaleAssSize()),
						startingBodyType.getAnusWetness(),
						startingBodyType.getAnusCapacity(),
						startingBodyType.getAnusElasticity(),
						startingBodyType.getAnusPlasticity(),
						true),
				new Breast(stage.isBreastFurry()?startingBodyType.getBreastType():BreastType.HUMAN,
						(startingGender.isFeminine() ? startingBodyType.getFemaleBreastSize() : startingBodyType.getMaleBreastSize()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleLactationRate() : startingBodyType.getMaleLactationRate()),
						((stage.isSkinFurry() && Main.getProperties().multiBreasts==1) || (stage.isBreastFurry() && Main.getProperties().multiBreasts==2)
								?(startingGender.isFeminine() ? startingBodyType.getBreastCountFemale() : startingBodyType.getBreastCountMale())
								:1),
						(startingGender.isFeminine() ? startingBodyType.getFemaleNippleSize() : startingBodyType.getMaleNippleSize()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleNippleShape() : startingBodyType.getMaleNippleShape()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleAreolaeSize() : startingBodyType.getMaleAreolaeSize()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleNippleCountPerBreast() : startingBodyType.getMaleNippleCountPerBreast()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleBreastCapacity() : startingBodyType.getMaleBreastCapacity()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleBreastElasticity() : startingBodyType.getMaleBreastElasticity()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleBreastPlasticity() : startingBodyType.getMaleBreastPlasticity()), 
						true),
				new Face((stage.isFaceFurry()?startingBodyType.getFaceType():FaceType.HUMAN),
						(startingGender.isFeminine() ? startingBodyType.getFemaleLipSize() : startingBodyType.getMaleLipSize())),
				new Eye(stage.isEyeFurry()?startingBodyType.getEyeType():EyeType.HUMAN),
				new Ear(stage.isEarFurry()?startingBodyType.getEarType():EarType.HUMAN),
				new Hair(stage.isHairFurry()?startingBodyType.getHairType():HairType.HUMAN,
						(startingGender.isFeminine() ? startingBodyType.getFemaleHairLength() : startingBodyType.getMaleHairLength()),
						HairStyle.getRandomHairStyle((startingGender.isFeminine() ? startingBodyType.getFemaleHairLength() : startingBodyType.getMaleHairLength()))),
				new Leg(stage.isLegFurry()?startingBodyType.getLegType():LegType.HUMAN),
				new Skin(stage.isSkinFurry()?startingBodyType.getSkinType():SkinType.HUMAN),
				startingBodyType.getBodyMaterial(),
				(startingGender.isFeminine() ? startingBodyType.getFemaleHeight() : startingBodyType.getMaleHeight()),
				(startingGender.isFeminine() ? startingBodyType.getFemaleFemininity() : startingBodyType.getMaleFemininity()),
				(startingGender.isFeminine() ? startingBodyType.getFemaleBodySize() : startingBodyType.getMaleBodySize()),
				(startingGender.isFeminine() ? startingBodyType.getFemaleMuscle() : startingBodyType.getMaleMuscle()))
						.vagina((startingGender==Gender.FUTANARI || startingGender==Gender.HERMAPHRODITE || startingGender==Gender.CUNT_BOY || startingGender==Gender.FEMALE)
								? new Vagina(stage.isVaginaFurry()?startingBodyType.getVaginaType():VaginaType.HUMAN,
										startingBodyType.getClitSize(),
										startingBodyType.getVaginaWetness(),
										startingBodyType.getVaginaCapacity(),
										startingBodyType.getVaginaElasticity(),
										startingBodyType.getVaginaPlasticity(),
										true)
								: new Vagina(VaginaType.NONE, 0, 0, 0, 3, 3, true))
						.penis((startingGender==Gender.FUTANARI || startingGender==Gender.HERMAPHRODITE || startingGender==Gender.SHEMALE || startingGender==Gender.MALE)
								? new Penis(stage.isPenisFurry()?startingBodyType.getPenisType():PenisType.HUMAN,
									startingBodyType.getPenisSize(),
									startingBodyType.getTesticleSize(),
									startingBodyType.getCumProduction(),
									startingBodyType.getTesticleQuantity())
								: new Penis(PenisType.NONE, 0, 0, 0, 2))
						.horn(startingBodyType.getHornTypeFemale() == HornType.NONE ? new Horn(HornType.NONE) : new Horn(!startingGender.isFeminine()
								? (stage.isHornFurry()?startingBodyType.getHornTypeMale():HornType.NONE)
								: (stage.isHornFurry()?startingBodyType.getHornTypeFemale():HornType.NONE)))
						.tail(new Tail(stage.isTailFurry()?startingBodyType.getTailType():TailType.NONE))
						.wing(new Wing(stage.isWingFurry()?startingBodyType.getWingType():WingType.NONE))
						.build();

		postTransformationCalculation();
	}
	
	public void setBodyCoveringForXMLImport(BodyCoveringType bct, CoveringPattern pattern, Colour primary, boolean primaryGlow, Colour secondary, boolean secondaryGlow) {
		body.getCoverings().put(bct, new Covering(bct, pattern, primary, primaryGlow, secondary, secondaryGlow));
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
	
	
	// Slavery:
	
	public List<GameCharacter> getSlavesOwned() {
		return slavesOwned;
	}
	
	public boolean addSlave(GameCharacter slave) {
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
	public void setOwner(GameCharacter owner) {
		this.owner = owner;
	}
	
	public boolean isSlave() {
		return owner != null;
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
				birthedLitter.setDayOfBirth(Main.game.getDayNumber());
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

	public String addedItemToInventoryText(ItemType item) {
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
							+ "<b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>New entry in your phone's encyclopedia:</b>"
							+ "</br>"
							+ "<b>Item:</b> <b style='color:"+item.getRarity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(item.getName())+"</b>"
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
								+ "<b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>New entry in your phone's encyclopedia:</b>"
								+ "</br>"
								+ "<b>Clothing:</b> <b style='color:"+newClothing.getRarity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(newClothing.getName())+"</b>"
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
								+ "<b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>New entry in your phone's encyclopedia:</b>"
								+ "</br>"
								+ "<b>Clothing:</b> <b style='color:"+newClothing.getRarity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(newClothing.getName())+"</b>"
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
								+ "<b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>New entry in your phone's encyclopedia:</b>"
								+ "</br>"
								+ "<b>Clothing:</b> <b style='color:"+newClothing.getRarity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(newClothing.getName())+"</b>"
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
				if((getPenisRawSizeValue()>=PenisSize.THREE_LARGE.getMaximumValue() || getTesticleSize().getValue()>=TesticleSize.FOUR_HUGE.getValue()) && hasPenis()) {
					// Exposed vagina and obvious penis bulge:
					if(isPlayer()) {
						if(getPenisRawSizeValue()>=PenisSize.THREE_LARGE.getMaximumValue()) {
							return new GenderAppearance("The [pc.cockSize] bulge between your legs, combined with the fact that your [pc.vagina] is exposed, reveals to everyone that you're [pc.a_gender].", Gender.FUTANARI);
						} else {
							return new GenderAppearance("The "+getTesticleSize().getDescriptor()+" bulge of your [pc.balls] between your legs, combined with the fact that your [pc.vagina] is exposed, reveals to everyone that you're [pc.a_gender].",
									Gender.FUTANARI);
						}
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"The [npc.cockSize] bulge between [npc.her] legs, combined with the fact that [npc.her] [npc.vagina] is exposed, reveals to everyone that [npc.she]'s [npc.a_gender]."), Gender.FUTANARI);
					}
					
				}
				
				if(hasPenis()) {
					// Assume female, as penis is not visible:
					if(isPlayer()) {
						return new GenderAppearance("As your [pc.vagina] is exposed, and your [pc.penis] remains concealed, everyone assumes that you're "
								+UtilText.generateSingularDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+" on first glance.", Gender.FEMALE);
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"Due to the fact that [npc.her] [npc.vagina] is exposed, everyone assumes that [npc.she]'s "
										+UtilText.generateSingularDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+" on first glance."), Gender.FEMALE);
					}
					
				} else {
					// Correctly assume female:
					if(isPlayer()) {
						return new GenderAppearance("Due to the fact that your [pc.vagina] is exposed, everyone correctly assumes that you're "
								+UtilText.generateSingularDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+" on first glance.", Gender.FEMALE);
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"Due to the fact that [npc.her] [npc.vagina] is exposed, everyone assumes that [npc.she]'s "
										+UtilText.generateSingularDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+" on first glance."), Gender.FEMALE);
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
				if((getPenisRawSizeValue()>=PenisSize.THREE_LARGE.getMaximumValue() || getTesticleSize().getValue()>=TesticleSize.FOUR_HUGE.getValue()) && hasPenis()) {
					// Obvious penis bulge:
					if(isPlayer()) {
						if(getPenisRawSizeValue()>=PenisSize.THREE_LARGE.getMaximumValue()) {
							return new GenderAppearance("The [pc.cockSize] bulge between your legs, combined with your feminine appearance, leads everyone to believe that you're [pc.a_gender].", Gender.SHEMALE);
						} else {
							return new GenderAppearance("The "+getTesticleSize().getDescriptor()+" bulge of your [pc.balls] between your legs, combined with your feminine appearance, leads everyone to believe that you're [pc.a_gender].", Gender.SHEMALE);
						}
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] feminine appearance, leads everyone to believe that [npc.she]'s [npc.a_gender]."), Gender.SHEMALE);
					}
					
				} else {
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						if(isPlayer()) {
							return new GenderAppearance("Your [pc.penis] is concealed, so, due to your feminine appearance, strangers assume that you're "
									+UtilText.generateSingularDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+".", Gender.FEMALE);
						} else {
							return new GenderAppearance(UtilText.parse(this,
									"Due to [npc.her] feminine appearance, [npc.she] appears to be "
											+UtilText.generateSingularDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+"."), Gender.FEMALE);
						}
						
					} else if(hasVagina()) {
						// Correctly assume female:
						if(isPlayer()) {
							return new GenderAppearance("Your feminine appearance leads everyone to correctly assume that you're "
									+UtilText.generateSingularDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+".", Gender.FEMALE);
						} else {
							return new GenderAppearance(UtilText.parse(this,
									"Due to [npc.her] feminine appearance, [npc.she] appears to be "
											+UtilText.generateSingularDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+"."), Gender.FEMALE);
						}
						
					} else {
						if(isCoverableAreaExposed(CoverableArea.VAGINA) && isCoverableAreaExposed(CoverableArea.PENIS)) {
							// Can see doll:
							if(isPlayer()) {
								if(isFeminine()) {
									return new GenderAppearance("Your genderless mound is exposed, so, due to your feminine appearance, strangers treat you like "
											+UtilText.generateSingularDeterminer(Gender.GENDERLESS_FEMININE.getName())+" "+Gender.GENDERLESS_FEMININE.getName()+".", Gender.GENDERLESS_FEMININE);
								} else {
									return new GenderAppearance("Your genderless mound is exposed, so, due to your masculine appearance, strangers treat you like "
											+UtilText.generateSingularDeterminer(Gender.GENDERLESS_MASCULINE.getName())+" "+Gender.GENDERLESS_MASCULINE.getName()+".", Gender.GENDERLESS_MASCULINE);
								}
							} else {
								if(isFeminine()) {
									return new GenderAppearance(UtilText.parse(this,
											"Due to [npc.her] genderless mound being exposed, everyone can tell that [npc.she]'s "
													+UtilText.generateSingularDeterminer(Gender.GENDERLESS_FEMININE.getName())+" "+Gender.GENDERLESS_FEMININE.getName()+"."), Gender.GENDERLESS_FEMININE);
								} else {
									return new GenderAppearance(UtilText.parse(this,
											"Due to [npc.her] genderless mound being exposed, everyone can tell that [npc.she]'s "
													+UtilText.generateSingularDeterminer(Gender.GENDERLESS_MASCULINE.getName())+" "+Gender.GENDERLESS_MASCULINE.getName()+"."), Gender.GENDERLESS_MASCULINE);
								}
							}
							
						} else {
							// Correctly assume doll:
							if(isPlayer()) {
								return new GenderAppearance("Your genderless mound is concealed, so, due to your feminine appearance, strangers assume that you're "
										+UtilText.generateSingularDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+".", Gender.FEMALE);
							} else {
								return new GenderAppearance(UtilText.parse(this,
										"Due to [npc.her] feminine appearance, [npc.she] appears to be "
												+UtilText.generateSingularDeterminer(Gender.FEMALE.getName())+" "+Gender.FEMALE.getName()+"."), Gender.FEMALE);
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
				if((getPenisRawSizeValue()>=PenisSize.THREE_LARGE.getMaximumValue() || getTesticleSize().getValue()>=TesticleSize.FOUR_HUGE.getValue()) && hasPenis()) {
					// Exposed vagina and obvious penis bulge:
					if(isPlayer()) {
						if(getPenisRawSizeValue()>=PenisSize.THREE_LARGE.getMaximumValue()) {
							return new GenderAppearance("The [pc.cockSize] bulge between your legs, combined with the fact that your [pc.vagina] is exposed, reveals to everyone that you're [pc.a_gender].", Gender.HERMAPHRODITE);
						} else {
							return new GenderAppearance("The "+getTesticleSize().getDescriptor()+" bulge of your [pc.balls] between your legs, combined with the fact that your [pc.vagina] is exposed, reveals to everyone that you're [pc.a_gender].",
									Gender.HERMAPHRODITE);
						}
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"The [npc.cockSize] bulge between [npc.her] legs, combined with the fact that [npc.her] [npc.vagina] is exposed, reveals to everyone that [npc.she]'s [npc.a_gender]."), Gender.HERMAPHRODITE);
					}
					
				}
				
				if(hasPenis()) {
					// Assume cuntboy, as penis is not visible:
					if(isPlayer()) {
						return new GenderAppearance("As your [pc.vagina] is exposed, and your [pc.penis] remains concealed, everyone assumes that you're "
								+UtilText.generateSingularDeterminer(Gender.CUNT_BOY.getName())+" "+Gender.CUNT_BOY.getName()+" on first glance.", Gender.CUNT_BOY);
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"Due to the fact that [npc.her] [npc.vagina] is exposed, everyone assumes that [npc.she]'s "
										+UtilText.generateSingularDeterminer(Gender.CUNT_BOY.getName())+" "+Gender.CUNT_BOY.getName()+" on first glance."), Gender.CUNT_BOY);
					}
					
				} else {
					// Correctly assume cuntboy:
					if(isPlayer()) {
						return new GenderAppearance("Due to the fact that your [pc.vagina] is exposed, everyone correctly assumes that you're "
								+UtilText.generateSingularDeterminer(Gender.CUNT_BOY.getName())+" "+Gender.CUNT_BOY.getName()+" on first glance.", Gender.CUNT_BOY);
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"Due to the fact that [npc.her] [npc.vagina] is exposed, everyone assumes that [npc.she]'s "
										+UtilText.generateSingularDeterminer(Gender.CUNT_BOY.getName())+" "+Gender.CUNT_BOY.getName()+" on first glance."), Gender.CUNT_BOY);
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
				if((getPenisRawSizeValue()>=PenisSize.THREE_LARGE.getMaximumValue() || getTesticleSize().getValue()>=TesticleSize.FOUR_HUGE.getValue()) && hasPenis()) {
					// Obvious penis bulge:
					if(isPlayer()) {
						if(getPenisRawSizeValue()>=PenisSize.THREE_LARGE.getMaximumValue()) {
							return new GenderAppearance("The [pc.cockSize] bulge between your legs, combined with your masculine appearance, leads everyone to believe that you're [pc.a_gender].", Gender.MALE);
						} else {
							return new GenderAppearance("The "+getTesticleSize().getDescriptor()+" bulge of your [pc.balls] between your legs, combined with your masculine appearance, leads everyone to believe that you're [pc.a_gender].", Gender.MALE);
						}
					} else {
						return new GenderAppearance(UtilText.parse(this,
								"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] masculine appearance, leads everyone to believe that [npc.she]'s [npc.a_gender]."), Gender.MALE);
					}
					
				} else {
					if(hasPenis()) {
						// Assume male, as penis is not visible:
						if(isPlayer()) {
							return new GenderAppearance("Your [pc.penis] is concealed, so, due to your masculine appearance, strangers assume that you're "
									+UtilText.generateSingularDeterminer(Gender.MALE.getName())+" "+Gender.MALE.getName()+".", Gender.MALE);
						} else {
							return new GenderAppearance(UtilText.parse(this,
									"Due to [npc.her] masculine appearance, [npc.she] appears to be "
											+UtilText.generateSingularDeterminer(Gender.MALE.getName())+" "+Gender.MALE.getName()+"."), Gender.MALE);
						}
						
					} else if(hasVagina()) {
						// Assume male:
						if(isPlayer()) {
							return new GenderAppearance("Your masculine appearance leads everyone to assume that you're "
									+UtilText.generateSingularDeterminer(Gender.MALE.getName())+" "+Gender.MALE.getName()+".", Gender.MALE);
						} else {
							return new GenderAppearance(UtilText.parse(this,
									"Due to [npc.her] masculine appearance, [npc.she] appears to be "
											+UtilText.generateSingularDeterminer(Gender.MALE.getName())+" "+Gender.MALE.getName()+"."), Gender.MALE);
						}
						
					} else {
						if(isCoverableAreaExposed(CoverableArea.VAGINA) && isCoverableAreaExposed(CoverableArea.PENIS)) {
							// Can see doll:
							if(isPlayer()) {
								if(isFeminine()) {
									return new GenderAppearance("Your genderless mound is exposed, so strangers treat you like "
											+UtilText.generateSingularDeterminer(Gender.GENDERLESS_FEMININE.getName())+" "+Gender.GENDERLESS_FEMININE.getName()+".", Gender.GENDERLESS_FEMININE);
								} else {
									return new GenderAppearance("Your genderless mound is exposed, so strangers treat you like "
											+UtilText.generateSingularDeterminer(Gender.GENDERLESS_MASCULINE.getName())+" "+Gender.GENDERLESS_MASCULINE.getName()+".", Gender.GENDERLESS_MASCULINE);
								}
							} else {
								if(isFeminine()) {
									return new GenderAppearance(UtilText.parse(this,
											"Due to [npc.her] genderless mound being exposed, everyone can tell that [npc.she]'s "
													+UtilText.generateSingularDeterminer(Gender.GENDERLESS_FEMININE.getName())+" "+Gender.GENDERLESS_FEMININE.getName()+"."), Gender.GENDERLESS_FEMININE);
								} else {
									return new GenderAppearance(UtilText.parse(this,
											"Due to [npc.her] genderless mound being exposed, everyone can tell that [npc.she]'s "
													+UtilText.generateSingularDeterminer(Gender.GENDERLESS_MASCULINE.getName())+" "+Gender.GENDERLESS_MASCULINE.getName()+"."), Gender.GENDERLESS_MASCULINE);
								}
							}
							
						} else {
							// Correctly assume doll:
							if(isPlayer()) {
								return new GenderAppearance("Your genderless mound is concealed, so, due to your masculine appearance, strangers assume that you're "
										+UtilText.generateSingularDeterminer(Gender.MALE.getName())+" "+Gender.MALE.getName()+".", Gender.MALE);
							} else {
								return new GenderAppearance(UtilText.parse(this,
										"Due to [npc.her] masculine appearance, [npc.she] appears to be "
												+UtilText.generateSingularDeterminer(Gender.MALE.getName())+" "+Gender.MALE.getName()+"."), Gender.MALE);
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
									+" <b style='color:"+bp.getType().getBodyCoveringType().getRace().getColour().toWebHexString()+";'>"+bp.getType().getBodyCoveringType().getRace().getName()+"'s</b> <b>"+bp.getType().getBodyCoveringType().getName(this)+" colour is "
									+getCovering(bp.getType().getBodyCoveringType()).getColourDescriptor(true)+"!</b>");
						} else {
							postTFSB.append(UtilText.parse(this,
									"<b>[npc.Name] has discovered that [npc.her] natural</b>"
									+" <b style='color:"+bp.getType().getBodyCoveringType().getRace().getColour().toWebHexString()+";'>"+bp.getType().getBodyCoveringType().getRace().getName()+"'s</b> <b>"+bp.getType().getBodyCoveringType().getName(this)+" colour is</b>"
									+ " <b style='color:"+getCovering(bp.getType().getBodyCoveringType()).getPrimaryColour().toWebHexString()+";'>"+getCovering(bp.getType().getBodyCoveringType()).getColourDescriptor(false)+"</b><b>!</b>"));
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

	public RaceStage getRaceStage() {
		return body.getRaceStage();
	}

	// Femininity:

	public int getFemininity() {
		return body.getFemininity();
	}
	
	public String setFemininity(int femininity) {
		
		String beardLoss = "";
		
		if(femininity>=Femininity.ANDROGYNOUS.getMinimumFemininity() && this.getFacialHair()!=BodyHair.NONE) {
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
									+ "You now have <b style='color:"+ Femininity.valueOf(getFemininity()).getColour().toWebHexString() + ";'>" + Femininity.getFemininityName(getFemininity(), true) + "</b> body."
							+ "</p>"
							+beardLoss;
				} else {
					return UtilText.parse(this,
							"<p>"
								+ "[npc.Name]'s body subtly shifts to become <b style='color:" + Colour.FEMININE.toWebHexString() + ";'>more feminine</b>.</br>"
								+ "[npc.She] now has <b style='color:"+ Femininity.valueOf(getFemininity()).getColour().toWebHexString() + ";'>" + Femininity.getFemininityName(getFemininity(), true) + "</b> body."
							+ "</p>"
							+beardLoss);
				}
			}
		} else {
			if (body.setFemininity(femininity)) {
				if(isPlayer()) {
					return "<p>"
								+ "You feel your body subtly shifting to become <b style='color:" + Colour.MASCULINE.toWebHexString() + ";'>more masculine</b>.</br>"
								+ "You have <b style='color:"+ Femininity.valueOf(getFemininity()).getColour().toWebHexString() + ";'>" + Femininity.getFemininityName(getFemininity(), true) + "</b> body."
							+ "</p>"
							+beardLoss;
				} else {
					return UtilText.parse(this,
							"<p>"
								+ "[npc.Name]'s body subtly shifts to become <b style='color:" + Colour.MASCULINE.toWebHexString() + ";'>more masculine</b>.</br>"
								+ "[npc.She] now has <b style='color:"+ Femininity.valueOf(getFemininity()).getColour().toWebHexString() + ";'>" + Femininity.getFemininityName(getFemininity(), true) + "</b> body."
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
		return setFemininity(getFemininity() + increment);
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
						? "<p>" + "The world around you seems slightly further away than it used to be, but after a moment you realise that you've just <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>grown taller</b>."
								+ "</br>" + "You are now <b>" + getHeight() + "cm</b> tall." + "</p>"
						: UtilText.genderParsing(this,
								"<p>" + "<She> sways from side to side a little, <her> balance suddenly thrown off by the fact that she's just <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>grown taller</b>." + "</p>");
		} else {
			if (body.setHeight(height))
				return isPlayer()
						? "<p>" + "The world around you suddenly seems slightly closer than it used to be, but after a moment you realise that you've just <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>become shorter</b>."
								+ "</br>" + "You are now <b>" + getHeight() + "cm</b> tall." + "</p>"
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
				case NONE:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>There is no longer any trace of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>There is no longer any trace of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case MANICURED:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a well-manicured patch of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has a well-manicured patch of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case TRIMMED:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a trimmed patch of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has a trimmed patch of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case BUSHY:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a thick mass of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has a thick mass of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
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
		} else if(value > 3) {
			value = 3;
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
							+ "You are [style.boldGrow(now wearing)] "+nailPolish.getColourDescriptor(true)+" nail polish."
						+ "</p>";
			}
			
		} else {
			if(nailPolish.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any nail polish."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+nailPolish.getColourDescriptor(true)+" nail polish."
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
		} else if(value > 3) {
			value = 3;
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
		} else if(value > 3) {
			value = 3;
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
	public boolean isBreastFuckable() {
		return body.getBreast().isFuckable();
	}
	// Type:
	public BreastType getBreastType() {
		return body.getBreast().getType();
	}
	public String setBreastType(BreastType type) {
		return body.getBreast().setType(this, type);
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
							+ "You are [style.boldGrow(now wearing)] "+eyeLiner.getColourDescriptor(true)+" eye liner."
						+ "</p>";
			}
			
		} else {
			if(eyeLiner.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any eye liner."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+eyeLiner.getColourDescriptor(true)+" eye liner."
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
							+ "You are [style.boldGrow(now wearing)] "+eyeShadow.getColourDescriptor(true)+" eye shadow."
						+ "</p>";
			}
			
		} else {
			if(eyeShadow.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any eye shadow."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+eyeShadow.getColourDescriptor(true)+" eye shadow."
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
							+ "You are [style.boldGrow(now wearing)] "+lipstick.getColourDescriptor(true)+" lipstick."
						+ "</p>";
			}
			
		} else {
			if(lipstick.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any lipstick."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+lipstick.getColourDescriptor(true)+" lipstick."
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
							+ "You are [style.boldGrow(now wearing)] "+blusher.getColourDescriptor(true)+" blusher."
						+ "</p>";
			}
			
		} else {
			if(blusher.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any blusher."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+blusher.getColourDescriptor(true)+" blusher."
						+ "</p>");
			}
		}
	}
	// Facial hair:
	public BodyHair getFacialHair() {
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
		} else if(value > 3) {
			value = 3;
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
			
			if(updateBodyHair) {
				body.updateBodyHairColour();
			}
			
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
							+ "You are [style.boldGrow(now wearing)] "+nailPolish.getColourDescriptor(true)+" toenail polish."
						+ "</p>";
			}
			
		} else {
			if(nailPolish.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any toenail polish."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+nailPolish.getColourDescriptor(true)+" toenail polish."
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
		return body.getFace().getTongue().addTongueModifier(this, modifier);
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
			
			if(updateAllSkinColours) {
				body.updateAllSkinCoverings();
			}
			
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
	public String setVaginaType(VaginaType type) {
		return body.getVagina().setType(this, type);
	}
	// Misc.:
	public boolean hasVagina() {
		return body.getVagina().getType() != VaginaType.NONE;
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
