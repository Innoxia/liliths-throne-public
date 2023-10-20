package com.lilithsthrone.game.character.npc.dominion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
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
public class SlaveInStocks extends RandomNPC {
	
	public SlaveInStocks(NPCGenerationFlag... generationFlags) {
		this(false, generationFlags);
	}
	
	public SlaveInStocks(boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		// Pre-setup
		this.setLevel(Util.random.nextInt(3)+1);
		
		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		for (AbstractSubspecies s : Subspecies.getAllSubspecies()) {
			if (s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
				continue;
			}
			if (s == Subspecies.REINDEER_MORPH
					&& Main.game.getSeason() == Season.WINTER
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
				AbstractSubspecies.addToSubspeciesMap(50, this.getGenderIdentity(), s, subspeciesMap);
			} else if (Subspecies.getWorldSpecies(WorldType.DOMINION, null, false).containsKey(s)) {
				AbstractSubspecies.addToSubspeciesMap((int) (1000*Subspecies.getWorldSpecies(WorldType.DOMINION, null, false).get(s).getChanceMultiplier()), this.getGenderIdentity(), s, subspeciesMap);
			} else if (Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false).containsKey(s)) {
				AbstractSubspecies.addToSubspeciesMap((int) (1000*Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false).get(s).getChanceMultiplier()), this.getGenderIdentity(), s, subspeciesMap);
			} else if (Subspecies.getWorldSpecies(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS, false).containsKey(s)) {
				AbstractSubspecies.addToSubspeciesMap((int) (1000*Subspecies.getWorldSpecies(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS, false).get(s).getChanceMultiplier()), this.getGenderIdentity(), s, subspeciesMap);
			}
		}
		
		// Setup
		this.setupNPC(subspeciesMap,
				null,
				Occupation.NPC_SLAVE,
				true,
				false,
				false,
				false,
				false,
				false,
				false,
				generationFlags);
		
		// Post-setup
		this.setPlayerKnowsName(true);
		this.setDescription("[npc.Name] is a slave, who, for one reason or another, has been locked into the stocks for public use.");
		this.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS, true);
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", false), true, this);
	}
	
	@Override
	public boolean isClothingStealable() {
		return false;
	}
	
	public void initSlavePermissions() {
		this.clearSlaveJobSettings(SlaveJob.PUBLIC_STOCKS);
		
		if (Math.random()<0.8f) {
			this.addSlaveJobSettings(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ORAL);
			this.setFaceVirgin(false);
		}
		
		if (Math.random()<0.6f) {
			this.addSlaveJobSettings(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ANAL);
			this.setAssVirgin(false);
		}
		
		if (!this.hasVagina()) {
			this.addSlaveJobSettings(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ANAL);
			this.setAssVirgin(false);
		} else {
			if (Math.random()<0.6f) {
				this.addSlaveJobSettings(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_VAGINAL);
				this.setVaginaVirgin(false);
			}
		}
		
		this.removeSlavePermissionSetting(SlavePermission.CLEANLINESS, SlavePermissionSetting.CLEANLINESS_WASH_BODY);
		this.removeSlavePermissionSetting(SlavePermission.CLEANLINESS, SlavePermissionSetting.CLEANLINESS_WASH_CLOTHES);
	}
}
