package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.95
 * @version 0.3.5.5
 * @author Innoxia
 */
public class SlaveInStocks extends NPC {

	public SlaveInStocks() {
		this(Gender.getGenderFromUserPreferences(false, false), false);
	}
	
	public SlaveInStocks(Gender gender) {
		this(gender, false);
	}
	
	public SlaveInStocks(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public SlaveInStocks(Gender gender, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3,
				null, null, null,
				new CharacterInventory(10), WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS, false);

		if(!isImported) {
			// Set random level from 1 to 3:
			setLevel(Util.random.nextInt(3) + 1);
			
			// RACE & NAME:
			
			Map<AbstractSubspecies, Integer> availableRaces = new HashMap<>();
			for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
				if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
					continue;
				}
				if(s==Subspecies.REINDEER_MORPH
						&& Main.game.getSeason()==Season.WINTER
						&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
					AbstractSubspecies.addToSubspeciesMap(50, gender, s, availableRaces);
					
				} else if(Subspecies.getWorldSpecies(WorldType.DOMINION, null, false).containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (1000 * Subspecies.getWorldSpecies(WorldType.DOMINION, null, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
					
				} else if(Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false).containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (1000 * Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
					
				} else if(Subspecies.getWorldSpecies(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS, false).containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (1000 * Subspecies.getWorldSpecies(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces, true, true);
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(this.getSubspecies()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"[npc.Name] is a slave, who, for one reason or another, has been locked into the stocks for public use."));
			
			// PERSONALITY & BACKGROUND:

			this.setHistory(Occupation.NPC_SLAVE);
			
			// ADDING FETISHES:
			
			Main.game.getCharacterUtils().addFetishes(this);
			
			// BODY RANDOMISATION:
			
			Main.game.getCharacterUtils().randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);

			equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			Main.game.getCharacterUtils().applyMakeup(this, true);
			Main.game.getCharacterUtils().applyTattoos(this, true);

			this.setPlayerKnowsName(true);

			initHealthAndManaToMax();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		return (UtilText.parse(this, "[npc.Name] is a slave, who, for one reason or another, has been locked into the stocks for public use."));
	}

	@Override
	public boolean isClothingStealable() {
		return false;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null; //TODO
	}
	
	public void initSlavePermissions() {
		this.clearSlaveJobSettings(SlaveJob.PUBLIC_STOCKS);
		
		if(Math.random()<0.8f) {
			this.addSlaveJobSettings(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ORAL);
			this.setFaceVirgin(false);
		}
		
		if(Math.random()<0.6f) {
			this.addSlaveJobSettings(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ANAL);
			this.setAssVirgin(false);
		}
		
		if(!this.hasVagina()) {
			this.addSlaveJobSettings(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ANAL);
			this.setAssVirgin(false);
		} else {
			if(Math.random()<0.6f) {
				this.addSlaveJobSettings(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_VAGINAL);
				this.setVaginaVirgin(false);
			}
		}
		
		this.removeSlavePermissionSetting(SlavePermission.CLEANLINESS, SlavePermissionSetting.CLEANLINESS_WASH_BODY);
		this.removeSlavePermissionSetting(SlavePermission.CLEANLINESS, SlavePermissionSetting.CLEANLINESS_WASH_CLOTHES);
	}
}
