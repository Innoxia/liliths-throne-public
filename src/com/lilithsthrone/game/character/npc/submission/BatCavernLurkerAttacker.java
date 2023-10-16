package com.lilithsthrone.game.character.npc.submission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.BatCavernDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.3
 * @version 0.3.5.5
 * @author Innoxia
 */
public class BatCavernLurkerAttacker extends RandomNPC {

	public BatCavernLurkerAttacker(NPCGenerationFlag... generationFlags) {
		this(false, generationFlags);
	}
	
	public BatCavernLurkerAttacker(boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		// Pre-setup
		this.setLevel(8 + Util.random.nextInt(5));
		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
			if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
				continue;
			}
			if(Subspecies.getWorldSpecies(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_DARK, false, false, Subspecies.SLIME).containsKey(s)) {
				AbstractSubspecies.addToSubspeciesMap((int) (10000 * Subspecies.getWorldSpecies(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_DARK, false, false, Subspecies.SLIME).get(s).getChanceMultiplier()), getGenderIdentity(), s, subspeciesMap);
			}
		}
		AbstractSubspecies species = AbstractSubspecies.getRandomSubspeciesFromWeightedMap(subspeciesMap, Subspecies.BAT_MORPH);
		RaceStage stage = Main.game.getCharacterUtils().getRaceStageFromPreferences(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(species), getGenderIdentity(), species);
		if(!getGenderIdentity().isFeminine()) {
			stage = Main.game.getCharacterUtils().getRaceStageFromPreferences(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(species), getGenderIdentity(), species);
		}
		if(species.getRace()==Race.BAT_MORPH && !stage.isArmFurry()) {
			stage = RaceStage.LESSER;
		}
		
		// Setup
		this.setupNPC(species,
				stage,
				Occupation.NPC_MUGGER,
				true,
				true,
				true,
				true,
				true,
				true,
				true,
				generationFlags);
		
		// Post-setup
		// Nothing here
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue() * 0.5f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
		
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.MUGGER, settings);
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return (UtilText.parse(this,
					"[npc.NamePos] days of attacking innocent travellers in the Bat Caverns are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
		} else if(Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())){
			return (UtilText.parse(this,
					"[npc.NamePos] days of attacking innocent travellers in the Bat Caverns are now over. Having befriended [npc.herHim], you invited [npc.name] to move in with you and helped [npc.herHim] to start a new life."));
		} else {
			return (UtilText.parse(this,
					"[npc.Name] is a resident of the Bat Caverns, and enjoys nothing more than attacking innocent travellers that pass by [npc.her] roost."));
		}
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return BatCavernDialogue.CAVERN_ATTACK;
	}

	// Combat:

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", BatCavernDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", BatCavernDialogue.AFTER_COMBAT_DEFEAT);
		}
	}

}
