package com.lilithsthrone.game.character.npc.fields;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.combat.moves.CombatMoveType;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * Cribbed from ElisAlleyAttacker
 * 
 * @since 0.4.8.6
 * @version 0.4.8.6
 * @author DSG
 */

public class SillyModeLARPAttacker extends RandomNPC {
	
    public SillyModeLARPAttacker(NPCGenerationFlag... generationFlags) {
		this(false, generationFlags);
	}
	
	public SillyModeLARPAttacker(boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		// Pre-setup
		setLevel(Util.random.nextInt(6) + 1);

		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
			if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
				continue;
			}
			Map<AbstractSubspecies, SubspeciesSpawnRarity> subMap = Subspecies.getWorldSpecies(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley"), false);
			if(subMap.containsKey(s)) {
				AbstractSubspecies.addToSubspeciesMap((int) (10000 * subMap.get(s).getChanceMultiplier()), this.getGenderIdentity(), s, subspeciesMap);
			}
		}
		
		// Setup
		setupNPC(subspeciesMap,
				null,
				Occupation.NPC_UNEMPLOYED,
				true,
				true,
				true,
				true,
				true,
				true,
				true,
				generationFlags);
		
		// Post-setup
		if(Math.random()<0.25f) {
			this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
		}
		//An appropriately dorky physique
		this.setMuscle(Util.random.nextInt(40));
	}
	
	// Do not add tease...just...don't do it
	@Override
	public void setStartingCombatMoves() {
	    this.clearEquippedMoves();
	    this.equipMove("strike");
	    this.equipMove("twin-strike");
	}
	
	// Ace continues to be a lifesaver here
	@Override
	public float getMoveWeight(AbstractCombatMove move, List<GameCharacter> enemies, List<GameCharacter> allies) {
	    if (move.getType() == CombatMoveType.TEASE || move.getType() == CombatMoveType.DEFEND) {
	    	return 0;
	    } else {
	    	return super.getMoveWeight(move, enemies, allies);
	    }
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue() * 1f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
		
		//Start out with a base labourer outfit
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.JOB_LABOUR, settings);
		
		//Equip cardboardium armor and weapon
		if(this.getClothingInSlot(InventorySlot.TORSO_OVER) != null) {
		    this.unequipClothingIntoVoid(InventorySlot.TORSO_OVER, true, this);
		}
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_sm_cboard_cbarmor", false), InventorySlot.TORSO_OVER, true, this);
		this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("dsg_sm_cboard_cbsword"));
	}
	
	@Override
	public String getDescription() {
	    return (UtilText.parse(this, "[npc.Name] is a resident of Elis who has an affinity for live-action roleplay."));
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return DialogueManager.getDialogueFromId("dsg_encounters_fields_elis_eisek_sillymode_dungeon_combat");
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", DialogueManager.getDialogueFromId("dsg_encounters_fields_elis_eisek_sillymode_dungeon_combat_won"));
		} else {
			return new Response ("", "", DialogueManager.getDialogueFromId("dsg_encounters_fields_elis_eisek_sillymode_dungeon_combat_lost"));
		}
	}
	
	@Override
	public boolean isLootingPlayerAfterCombat() {
		return false; // This is not high-stakes LARPing
	}
}
