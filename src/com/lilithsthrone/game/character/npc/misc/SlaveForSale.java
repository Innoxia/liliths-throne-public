package com.lilithsthrone.game.character.npc.misc;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Finch;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.0
 * @version 0.4.0
 * @author AceXp
 */
public class SlaveForSale extends NPC {

	public SlaveForSale() {
		this(Gender.getGenderFromUserPreferences(false, false), true, false);
	}

	public SlaveForSale(boolean isImported) {
		this(Gender.getGenderFromUserPreferences(false, false), true, isImported);
	}

	public SlaveForSale(Gender gender, boolean isImported) {
		this(Gender.getGenderFromUserPreferences(false, false), true, isImported);
	}
	
	public SlaveForSale(Gender gender, boolean allowTaurSpawns, boolean isImported) {
		super(isImported,
				new NameTriplet("Slave"), "",
				"",
				21, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(27),
				1,
				null, null, null,
				new CharacterInventory(0),
				WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL,
				false);
		
		if(!isImported) {
			// Set random level from 1 to 3:
			setLevel(Util.random.nextInt(3) + 1);
			
			// RACE & NAME:
			
			Map<AbstractSubspecies, Integer> availableRaces = new HashMap<>();
			for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
				if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
					continue;
				}
				if(s.isNonBiped() && !allowTaurSpawns) {
					continue;
				}
				if(Subspecies.getWorldSpecies(WorldType.DOMINION, null, false).containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (10000 * Subspecies.getWorldSpecies(WorldType.DOMINION, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
				}
				if(Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false).containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (10000 * Subspecies.getWorldSpecies(WorldType.SUBMISSION, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces, true, true);
			
			if(Math.random()<Main.getProperties().halfDemonSpawnRate/100f && this.getSubspecies()!=Subspecies.SLIME) {
				this.setBody(Main.game.getCharacterUtils().generateHalfDemonBody(this, gender, this.getBody().getFleshSubspecies(), true), true);
			}
			
			if(allowTaurSpawns
					&& Math.random()<Main.getProperties().taurSpawnRate/100f
					&& this.getLegConfiguration()!= LegConfiguration.QUADRUPEDAL) { // Do not reset this character's taur body if they spawned as a taur (as otherwise subspecies-specific settings get overridden by global taur settings)
				// Check for race's leg type as taur, otherwise NPCs which spawn with human legs won't be affected by taur conversion rate:
				if(this.getRace().getRacialBody().getLegType().isLegConfigurationAvailable(LegConfiguration.QUADRUPEDAL)) {
					this.setLegType(this.getRace().getRacialBody().getLegType());
					Main.game.getCharacterUtils().applyTaurConversion(this);
				}
			}
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
			
			setName(Name.getRandomTriplet(this.getSubspecies()));
			this.setPlayerKnowsName(true);
			
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 0);
			
			// PERSONALITY & BACKGROUND:

//			this.clearPersonalityTraits();
			Main.game.getCharacterUtils().setHistoryAndPersonality(this, true);
			this.setHistory(Occupation.NPC_SLAVE);
			
			// ADDING FETISHES:
		
			this.clearFetishDesires();
			this.clearFetishes();
			Main.game.getCharacterUtils().addFetishes(this);
			
//			this.clearTattoosAndScars();
			
			this.setObedience(100);
			
			// BODY RANDOMISATION:
			
			this.setStartingBody(true);
			Main.game.getCharacterUtils().randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(0);
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			// MISC.:
			
			initHealthAndManaToMax();
			
			this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
		}
	}

	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public void changeFurryLevel() {
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_STEEL, false), true, Main.game.getNpc(Finch.class));
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return UtilText.parse(this, "For one reason or another, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property.");
			
		} else {
			return UtilText.parse(this, "After a period of being your slave, [npc.nameIsFull] now your trusted friend.");
		}
	}
	
}
