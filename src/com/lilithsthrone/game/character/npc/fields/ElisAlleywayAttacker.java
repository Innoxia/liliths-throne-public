package com.lilithsthrone.game.character.npc.fields;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.4
 * @version 0.4.4
 * @author Innoxia
 */
public class ElisAlleywayAttacker extends RandomNPC {
	
	public ElisAlleywayAttacker(NPCGenerationFlag... generationFlags) {
		this(false, generationFlags);
	}
	
	public ElisAlleywayAttacker(boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		// Pre-setup
		this.setLevel(Util.random.nextInt(6)+10);
		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		for (AbstractSubspecies s : Subspecies.getAllSubspecies()) {
			if (s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
				continue;
			}
			Map<AbstractSubspecies, SubspeciesSpawnRarity> subMap = Subspecies.getWorldSpecies(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley"), false);
			if (subMap.containsKey(s)) {
				AbstractSubspecies.addToSubspeciesMap((int) (10000*subMap.get(s).getChanceMultiplier()), this.getGenderIdentity(), s, subspeciesMap);
			}
		}
		
		// Setup
		this.setupAlleyAttacker(subspeciesMap,
				null,
				true,
				generationFlags);
		
		// Post-setup
		if (this.getHistory() == Occupation.NPC_PROSTITUTE) {
			this.setPlayerKnowsName(true);
		}
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue()*1f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
		
		if (this.getHistory() == Occupation.NPC_PROSTITUTE) {
			Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.PROSTITUTE, settings);
		} else {
			Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.MUGGER, settings);
		}
	}
	
	@Override
	public void turnUpdate() {
		if (!this.isSlave()
				&& !Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())
				&& this.getCell() != Main.game.getPlayerCell()
				&& this.getHomeCell().getPlace().getPlaceType() != PlaceType.ANGELS_KISS_BEDROOM) {
			if (Main.game.isDayTime()) {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
			} else {
				this.returnToHome();
			}
		}
	}
	
	@Override
	public String getDescription() {
		if (this.getHistory() == Occupation.NPC_PROSTITUTE) {
			if (this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos] days of whoring [npc.herself] out in the back alleys of Elis are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
			} else if (this.getLocationPlace().getPlaceType().equals(PlaceType.ANGELS_KISS_BEDROOM)) {
				return (UtilText.parse(this,
						"You first found [npc.name] in the alleyways of Elis, where [npc.she] was illegally selling [npc.her] body. You offered [npc.herHim] the chance to move and work out of Angel's Kiss; an offer which [npc.she] happily accepted."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a prostitute who whores [npc.herself] out in the backalleys of Elis."));
			}
			
		} else {
			if (this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos] days of prowling the back alleys of Elis and mugging innocent travellers are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
			} else if (Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())) {
				return (UtilText.parse(this,
						"[npc.NamePos] days of prowling the back alleys of Elis and mugging innocent travellers are now over. Having befriended [npc.herHim], you invited [npc.name] to move in with you and helped [npc.herHim] to start a new life."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a resident of Elis, who prowls the back alleys in search of innocent travellers to prey upon."));
			}
		}
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		if (this.getHistory() == Occupation.NPC_PROSTITUTE) {
			return DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_prostitute_start");
		} else {
			return DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_start");
		}
	}
	
	// Combat:
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (this.getHistory() == Occupation.NPC_PROSTITUTE) {
			if (victory) {
				return new Response("", "", DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_prostitute_after_combat_victory"));
			} else {
				return new Response("", "", DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_prostitute_after_combat_defeat"));
			}
			
		} else {
			if (victory) {
				return new Response("", "", DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_after_combat_victory"));
			} else {
				return new Response("", "", DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_after_combat_defeat"));
			}
		}
	}
}
