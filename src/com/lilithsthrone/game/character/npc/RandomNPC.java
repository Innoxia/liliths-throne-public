package com.lilithsthrone.game.character.npc;

import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

public class RandomNPC extends NPC {
	protected RandomNPC(boolean isImported, boolean addedToContacts, NPCGenerationFlag... generationFlags) {
		super(isImported,
				null,
				null,
				"",
				18, // Age and Birthday will be set later
				Month.JANUARY,
				1,
				1, // Level should be set in the constructor
				Gender.getGenderFromUserPreferences(false, false),
				null,
				null,
				new CharacterInventory(0),
				null,
				null,
				addedToContacts,
				generationFlags);
	}
	
	protected void setupGambler(AbstractSubspecies subspecies, RaceStage raceStage, Occupation occupation, boolean canBeHalfDemon, boolean canBeTaur, NPCGenerationFlag... generationFlags) {
		Map<AbstractSubspecies, Integer> availableSubspecies = new HashMap<>();
		availableSubspecies.put(subspecies, 1);
		setupGambler(availableSubspecies,
				raceStage,
				occupation,
				canBeHalfDemon,
				canBeTaur,
				generationFlags);
	}
	
	protected void setupGambler(Map<AbstractSubspecies, Integer> subspeciesAvailable, RaceStage raceStage, Occupation occupation, boolean canBeHalfDemon, boolean canBeTaur, NPCGenerationFlag... generationFlags) {
		setupNPC(subspeciesAvailable,
				raceStage,
				occupation,
				true,
				canBeHalfDemon,
				canBeTaur,
				false,
				true,
				true,
				false,
				generationFlags);
	}
	
	protected void setupEnforcer(AbstractSubspecies subspecies, RaceStage raceStage, Occupation occupation, boolean canBeHalfDemon, boolean canBeTaur, NPCGenerationFlag... generationFlags) {
		Map<AbstractSubspecies, Integer> availableSubspecies = new HashMap<>();
		availableSubspecies.put(subspecies, 1);
		setupEnforcer(availableSubspecies,
				raceStage,
				occupation,
				canBeHalfDemon,
				canBeTaur,
				generationFlags);
	}
	
	protected void setupEnforcer(Map<AbstractSubspecies, Integer> subspeciesAvailable, RaceStage raceStage, Occupation occupation, boolean canBeHalfDemon, boolean canBeTaur, NPCGenerationFlag... generationFlags) {
		setupNPC(subspeciesAvailable,
				raceStage,
				occupation,
				false,
				canBeHalfDemon,
				canBeTaur,
				false,
				true,
				true,
				false,
				generationFlags);
		
		// Remove Fetishes & Desires enforcers can't have
		if (hasFetish(Fetish.FETISH_CROSS_DRESSER)) {
			removeFetish(Fetish.FETISH_CROSS_DRESSER);
		}
		if (hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
			removeFetish(Fetish.FETISH_EXHIBITIONIST);
		}
		List<AbstractFetish> fetishesForNonNegative = Util.newArrayListOfValues(
				Fetish.FETISH_ANAL_GIVING,
				Fetish.FETISH_ORAL_RECEIVING,
				Fetish.FETISH_VAGINAL_GIVING,
				Fetish.FETISH_VAGINAL_RECEIVING,
				Fetish.FETISH_PENIS_GIVING,
				Fetish.FETISH_PENIS_RECEIVING,
				Fetish.FETISH_DOMINANT);
		for (AbstractFetish fetish : fetishesForNonNegative) {
			if (this.getFetishDesire(fetish).isNegative()) {
				this.setFetishDesire(fetish, FetishDesire.TWO_NEUTRAL);
			}
		}
		this.setCombatBehaviour(CombatBehaviour.ATTACK);
		this.removePersonalityTrait(PersonalityTrait.MUTE);
		this.setEssenceCount(100);
	}
	
	protected void setupAlleyAttacker(AbstractSubspecies subspecies, RaceStage raceStage, boolean canBeHalfDemon, NPCGenerationFlag... generationFlags) {
		Map<AbstractSubspecies, Integer> availableSubspecies = new HashMap<>();
		availableSubspecies.put(subspecies, 1);
		setupAlleyAttacker(availableSubspecies, raceStage, canBeHalfDemon, generationFlags);
	}
	
	protected void setupAlleyAttacker(Map<AbstractSubspecies, Integer> subspeciesAvailable, RaceStage raceStage, boolean canBeHalfDemon, NPCGenerationFlag... generationFlags) {
		setupNPC(subspeciesAvailable,
				raceStage,
				null,
				true,
				canBeHalfDemon,
				true,
				true,
				true,
				true,
				true,
				generationFlags);
		setLowlifeOccupation();
	}
	
	/**
	 * setupMarauder is a unique use case, it handles the pre and post setup for LunetteMelee and LunetteRanged NPCs
	 *
	 * @param ranged          false for LunetteMelee & true for LunetteRanged
	 * @param generationFlags Pass NPCGenerationFlags
	 */
	protected void setupMarauder(boolean ranged, List<String> namePrefixes, String name, NPCGenerationFlag... generationFlags) {
		// Pre-setup
		this.setLevel(25 + Util.random.nextInt(11));
		
		Gender gender;
		if (ranged) {
			gender = Util.randomItemFrom(new Gender[] {
					Gender.F_P_V_B_FUTANARI,
					Gender.F_P_V_B_FUTANARI,
					Gender.F_P_B_SHEMALE,
					Gender.F_V_B_FEMALE,
					Gender.F_V_B_FEMALE,
					Gender.F_V_B_FEMALE,
			});
		} else {
			gender = Util.randomItemFrom(new Gender[] {
					Gender.F_P_V_B_FUTANARI,
					Gender.F_P_V_B_FUTANARI,
					Gender.F_P_B_SHEMALE,
					Gender.F_P_B_SHEMALE,
					Gender.F_V_B_FEMALE,
					Gender.F_V_B_FEMALE,
			});
		}
		this.setGenderIdentity(gender);
		
		// Setup
		setupNPC(Subspecies.DEMON,
				RaceStage.GREATER,
				Occupation.NPC_LUNETTE_HERD,
				false,
				false,
				false,
				false,
				true,
				true,
				true,
				generationFlags);
		
		// Post-setup
		this.setGenericName(Util.randomItemFrom(namePrefixes)+" "+name);
		this.setSurname("Lunettemartu");
		
		this.setWingType(WingType.NONE);
		this.setLegType(LegType.DEMON_HORSE_HOOFED);
		this.setLegConfiguration(LegType.DEMON_HORSE_HOOFED, LegConfiguration.QUADRUPEDAL, true);
		if(!gender.getGenderName().isHasVagina()) {
			this.setBreastCrotchType(BreastType.NONE);
		}
		this.setPersonalityTraits(
				PersonalityTrait.BRAVE,
				PersonalityTrait.CONFIDENT,
				PersonalityTrait.SELFISH,
				PersonalityTrait.LEWD);
		
		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		this.clearFetishes();
		this.clearFetishDesires();
		
		boolean oral = Math.random()<0.25f;
		boolean anal = Math.random()<0.25f && Main.game.isAnalContentEnabled();
		
		this.setAssVirgin(!anal);
		this.setPenisVirgin(false);
		this.setVaginaVirgin(false);
		this.setFaceVirgin(!oral);
		
		this.addFetish(Fetish.FETISH_DOMINANT);
		this.addFetish(Fetish.FETISH_NON_CON_DOM);
		this.addFetish(Fetish.FETISH_SADIST);
		this.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.FOUR_LOVE);
		this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.FOUR_LOVE);
		this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ZERO_HATE);
		this.setFetishDesire(Fetish.FETISH_NON_CON_SUB, FetishDesire.ZERO_HATE);
		
		if(anal) {
			this.addFetish(Fetish.FETISH_ANAL_RECEIVING);
		} else if (Main.game.isAnalContentEnabled()) {
			this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
		}
		
		if(oral) {
			this.addFetish(Fetish.FETISH_ORAL_GIVING);
		}
		
		if(gender.getGenderName().isHasPenis()) {
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.FOUR_LOVE);
			this.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
		}
		if(gender.getGenderName().isHasVagina()) {
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.FOUR_LOVE);
		}
	}
	
	protected void setupOffspring(Body body, NPCGenerationFlag... generationFlags) {
		// Set location
		setLocation(Main.game.getPlayer(), true);
		
		// Set base body then reset race & orientation
		this.body = body;
		this.body.calculateRace(this);
		setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(getGender()));
		setPlayerKnowsName(true);
		
		// Personality & Background
		setLowlifeOccupation();
		
		// Fetishes
		Main.game.getCharacterUtils().addFetishes(this);
		
		// Randomise Body
		Main.game.getCharacterUtils().randomiseBody(this);
		
		// Inventory
		resetInventory(true);
		inventory.setMoney(10+Util.random.nextInt(getLevel()*10)+1);
		Main.game.getCharacterUtils().generateItemsInInventory(this, generateExtraItems, generateDisposableClothing, generateExtraClothing);
		if(!Arrays.asList(generationFlags).contains(NPCGenerationFlag.NO_CLOTHING_EQUIP)) {
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		Main.game.getCharacterUtils().applyMakeup(this, true);
		Main.game.getCharacterUtils().applyTattoos(this, true);
		if((Arrays.asList(generationFlags).contains(NPCGenerationFlag.DIRTY) || hasFetish(Fetish.FETISH_CUM_ADDICT)) && Math.random() < 0.1) {
			Main.game.getCharacterUtils().applyDirtiness(this);
		}
		
		// Final touches
		setStartingCombatMoves();
		loadImages();
		initHealthAndManaToMax();
		setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
	}
	
	/**
	 * Main method of RandomNPC, performs setup functions required by all NPCs
	 * <p>
	 * Home and current location, body, history, personality, fetishes, body, age, inventory, makeup, tattoos, dirtiness, combat moves, images, health, mana and enslavement status
	 *
	 * @param subspecies                 Subspecies of NPC
	 * @param raceStage                  Race stage (or null to randomise
	 * @param occupation                 Allow low life histories and personality
	 * @param canBeHuman                 Allow human spawn chance
	 * @param canBeHalfDemon             Allow half demon spawn chance
	 * @param canBeTaur                  Allow taur spawn chance
	 * @param canBeEnslaved              Can character be enslaved
	 * @param generateExtraItems         See CharacterUtils.generateItemsInInventory
	 * @param generateDisposableClothing See CharacterUtils.generateItemsInInventory
	 * @param generateExtraClothing      See CharacterUtils.generateItemsInInventory
	 * @param generationFlags            Pass NPCGenerationFlags
	 */
	protected void setupNPC(AbstractSubspecies subspecies, RaceStage raceStage, Occupation occupation, boolean canBeHuman, boolean canBeHalfDemon, boolean canBeTaur, boolean canBeEnslaved, boolean generateExtraItems, boolean generateDisposableClothing, boolean generateExtraClothing, NPCGenerationFlag... generationFlags) {
		Map<AbstractSubspecies, Integer> availableSubspecies = new HashMap<>();
		availableSubspecies.put(subspecies, 1);
		setupNPC(availableSubspecies,
				raceStage,
				occupation,
				canBeHuman,
				canBeHalfDemon,
				canBeTaur,
				canBeEnslaved,
				generateExtraItems,
				generateDisposableClothing,
				generateExtraClothing,
				generationFlags);
	}
	
	/**
	 * Main method of RandomNPC, performs setup functions required by all NPCs
	 * <p>
	 * Home and current location, body, history, personality, fetishes, body, age, inventory, makeup, tattoos, dirtiness, combat moves, images, health, mana and enslavement status
	 * @param availableSubspecies Weighted map of subspecies
	 * @param raceStage Race stage (or null to randomise
	 * @param occupation Assign specific occupation
	 * @param canBeHuman Allow human spawn chance
	 * @param canBeHalfDemon Allow half demon spawn chance
	 * @param canBeTaur Allow taur spawn chance
	 * @param canBeEnslaved Can character be enslaved
	 * @param generateExtraItems See CharacterUtils.generateItemsInInventory
	 * @param generateDisposableClothing See CharacterUtils.generateItemsInInventory
	 * @param generateExtraClothing See CharacterUtils.generateItemsInInventory
	 * @param generationFlags Pass NPCGenerationFlags
	 */
	protected void setupNPC(Map<AbstractSubspecies, Integer> availableSubspecies, RaceStage raceStage, Occupation occupation, boolean canBeHuman, boolean canBeHalfDemon, boolean canBeTaur, boolean canBeEnslaved, boolean generateExtraItems, boolean generateDisposableClothing, boolean generateExtraClothing, NPCGenerationFlag... generationFlags) {
		// Set location
		setLocation(Main.game.getPlayer(), true);
		
		// Race, Orientation & Name
		setBodyFromSubspeciesPreference(getGenderIdentity(), availableSubspecies, true, canBeHuman);
		setPlayerKnowsName(false);
		
		// Personality & Background
		if (occupation == null) {
			setLegalOccupation();
		} else {
			setHistory(occupation);
		}
		
		// Fetishes
		Main.game.getCharacterUtils().addFetishes(this);
		
		// Randomise Body
		if (raceStage == null) {
			Main.game.getCharacterUtils().randomiseBody(this);
		} else {
			setBody(getGender(), getTrueSubspecies(), raceStage, false);
		}
		Main.game.getCharacterUtils().randomiseAge(this);
		if (canBeHalfDemon && this.getSubspecies() != Subspecies.SLIME
				&& Math.random()<Main.getProperties().halfDemonSpawnRate/100f) {
			setBody(Main.game.getCharacterUtils().generateHalfDemonBody(this, getGender(), this.getBody().getFleshSubspecies(), true), true);
		}
		if (canBeTaur && getLegConfiguration() != LegConfiguration.QUADRUPEDAL
				&& getHalfDemonSubspecies().getRace().getRacialBody().getLegType().isLegConfigurationAvailable(LegConfiguration.QUADRUPEDAL)
				&& Math.random()<Main.getProperties().taurSpawnRate/100f) {
			setLegType(this.getHalfDemonSubspecies().getRace().getRacialBody().getLegType());
			Main.game.getCharacterUtils().applyTaurConversion(this);
		}
		
		// Inventory
		resetInventory(true);
		inventory.setMoney(10+Util.random.nextInt(getLevel()*10)+1);
		Main.game.getCharacterUtils().generateItemsInInventory(this, generateExtraItems, generateDisposableClothing, generateExtraClothing);
		if(!Arrays.asList(generationFlags).contains(NPCGenerationFlag.NO_CLOTHING_EQUIP)) {
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		Main.game.getCharacterUtils().applyMakeup(this, true);
		Main.game.getCharacterUtils().applyTattoos(this, true);
		if((Arrays.asList(generationFlags).contains(NPCGenerationFlag.DIRTY) || hasFetish(Fetish.FETISH_CUM_ADDICT)) && Math.random() < 0.1) {
			Main.game.getCharacterUtils().applyDirtiness(this);
		}
		
		// Final touches
		setStartingCombatMoves();
		loadImages();
		initHealthAndManaToMax();
		if (canBeEnslaved) {
			setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
		}
	}
	
	public void setLegalOccupation() {
		List<Occupation> histories = Occupation.getAvailableHistories(this);
		histories.removeIf(Occupation::isLowlife);
		setHistory(Util.randomItemFrom(histories));
	}
	
	private void setLowlifeOccupation() {
		if (Math.random() < 0.25) {
			this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
		}
		
		double prostituteChance = 0.15f; // Base 0.15% chance for any random to be a prostitute.
		
		if (this.isFeminine()) {
			prostituteChance += 0.10f; // Bonus for femininity
		}
		prostituteChance += Math.min((this.body.getBreast().getRawSizeValue()-7)*0.02f, 0.35f); // Compare breast size to average.
		if (this.hasPenis()) {
			prostituteChance += Math.min((this.body.getPenis().getRawLengthValue()-5)*0.01f, 0.10f); // Scaling based off of cock size. Very small cocks are a penalty.
		}
		if (this.hasVagina()) {
			prostituteChance += 0.15f; // Bonus for vagina.
		}
		if (this.body.getBreast().getNipples().getOrificeNipples().getRawCapacityValue()>=4) {
			prostituteChance += 0.05f; //Bonus for fuckable nipples.
		}
		if (this.hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
			prostituteChance = 0.03f; // addFetishes() can be called before or after this method. This is a catch for the case where addFetishes() is called before.
		}
		
		if (Math.random()<Math.min(prostituteChance, 0.3f)) { // Prostitutes can only ever spawn at a maximum of a 30% chance.
			this.setHistory(Occupation.NPC_PROSTITUTE);
			this.removePersonalityTrait(PersonalityTrait.PRUDE);
			this.removePersonalityTrait(PersonalityTrait.INNOCENT);
			this.removePersonalityTrait(PersonalityTrait.MUTE);
			
			this.setAssVirgin(false);
			this.setAssCapacity(this.getAssRawCapacityValue()*1.2f, true);
			
			if (this.hasVagina()) {
				this.setVaginaVirgin(false);
				this.setVaginaCapacity(this.getVaginaRawCapacityValue()*1.2f, true);
			}
			
			if (this.hasPenis()) {
				this.setPenisVirgin(false);
			}
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			this.setName(Name.getRandomProstituteTriplet());
			this.useItem(Main.game.getItemGen().generateItem("innoxia_pills_sterility"), this, false);
		} else {
			this.setHistory(Occupation.NPC_MUGGER);
		}
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		// Do nothing
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public void changeFurryLevel() {
		// Not yet implemented
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	public boolean isClothingStealable() {
		return true;
	}
	
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void endSex() {
		if(!isSlave()) {
			setPendingClothingDressing(true);
		}
	}
}
