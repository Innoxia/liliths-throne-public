package com.lilithsthrone.game.character.npc.misc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.npc.dominion.Finch;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.utils.UtilText;
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
public class SlaveForSale extends RandomNPC {

	public SlaveForSale() {
		this(false);
	}

	public SlaveForSale(boolean isImported) {
		this(isImported, Gender.getGenderFromUserPreferences(false, false), false);
	}

	public SlaveForSale(Gender gender, boolean allowTaurSpawns, NPCGenerationFlag... generationFlags) {
		this(false, gender, allowTaurSpawns, generationFlags);
	}
	
	public SlaveForSale(boolean isImported, Gender gender, boolean allowTaurSpawns, NPCGenerationFlag... generationFlags) {
		super(isImported,false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		// Pre-setup
		this.setLevel(Util.random.nextInt(3) + 1);
		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
			if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
				continue;
			}
			if(s.isNonBiped() && !allowTaurSpawns) {
				continue;
			}
			if(Subspecies.getWorldSpecies(WorldType.DOMINION, null, false).containsKey(s)) {
				AbstractSubspecies.addToSubspeciesMap((int) (10000 * Subspecies.getWorldSpecies(WorldType.DOMINION, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, false).get(s).getChanceMultiplier()), gender, s, subspeciesMap);
			}
			if(Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false).containsKey(s)) {
				AbstractSubspecies.addToSubspeciesMap((int) (10000 * Subspecies.getWorldSpecies(WorldType.SUBMISSION, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, false).get(s).getChanceMultiplier()), gender, s, subspeciesMap);
			}
		}
		
		// Setup
		this.setupNPC(subspeciesMap,
				null,
				Occupation.NPC_SLAVE,
				true,
				true,
				allowTaurSpawns,
				true,
				false,
				false,
				false,
				generationFlags);
		
		// Post-setup
		this.setPlayerKnowsName(true);
		this.setAttribute(Attribute.MAJOR_CORRUPTION, 0);
		this.setObedience(100);
		this.setMoney(0);
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
