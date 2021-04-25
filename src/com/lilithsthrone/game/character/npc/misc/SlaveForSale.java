package com.lilithsthrone.game.character.npc.misc;

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
import com.lilithsthrone.game.character.race.*;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 0.4.0
 * @version 0.4.0
 * @author AceXp
 */
public class SlaveForSale extends NPC {
	public SlaveForSale(Gender gender, boolean isImported) {
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
				if(Subspecies.getWorldSpecies(WorldType.DOMINION, null, false).containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (10000 * Subspecies.getWorldSpecies(WorldType.DOMINION, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
				}
				if(Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false).containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (10000 * Subspecies.getWorldSpecies(WorldType.SUBMISSION, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces, true, true);
			
			if(Math.random()<Main.getProperties().halfDemonSpawnRate/100f && this.getSubspecies()!=Subspecies.SLIME) {
				this.setBody(Main.game.getCharacterUtils().generateHalfDemonBody(this, gender, this.getFleshSubspecies(), true), true);
			}
			
			if(Math.random()<Main.getProperties().taurSpawnRate/100f
					&& this.getLegConfiguration()!= LegConfiguration.QUADRUPEDAL) { // Do not reset this charatcer's taur body if they spawned as a taur (as otherwise subspecies-specific settings get overridden by global taur settings)
				// Check for race's leg type as taur, otherwise NPCs which sapwn with human legs won't be affected by taur conversion rate:
				if(this.getRace().getRacialBody().getLegType().isLegConfigurationAvailable(LegConfiguration.QUADRUPEDAL)) {
					this.setLegType(this.getRace().getRacialBody().getLegType());
					Main.game.getCharacterUtils().applyTaurConversion(this);
				}
			}
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
			
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(true);
			
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 0);
			
			// PERSONALITY & BACKGROUND:
			
			this.setHistory(Occupation.NPC_SLAVE);
			
			// ADDING FETISHES:
		
			this.clearFetishDesires();
			this.clearFetishes();
			this.clearPersonalityTraits();
			this.clearTattoosAndScars();
			
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
		}
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
		return UtilText.parse(this, "For one reason or another, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property.");
	}
	
}
