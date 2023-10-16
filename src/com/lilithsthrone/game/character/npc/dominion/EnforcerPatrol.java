package com.lilithsthrone.game.character.npc.dominion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.combat.moves.CombatMoveType;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.EnforcerAlleywayDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.sex.sexActions.dominion.EnforcerPatrolSA;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;

/**
 * A randomly-generated Patrol Enforcer. Spawns with the 'patrol' outfit (ATHLETIC).
 * 
 * @since 0.3.6.2
 * @version 0.3.8.3
 * @author Innoxia
 */
public class EnforcerPatrol extends RandomNPC {

	public EnforcerPatrol(NPCGenerationFlag... generationFlags) {
		this(false, Occupation.NPC_ENFORCER_PATROL_CONSTABLE, generationFlags);
	}
	
	public EnforcerPatrol(boolean isImported) {
		this(isImported, null);
	}

	public EnforcerPatrol(Occupation occupation, NPCGenerationFlag... generationFlags) {
		this(false, occupation, generationFlags);
	}
	
	public EnforcerPatrol(boolean isImported, Occupation occupation, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		// Pre-setup
		this.setLevel(Util.random.nextInt(5)+3);

		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
			if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
				continue;
			}
			if(Subspecies.getWorldSpecies(WorldType.DOMINION, null, false, Subspecies.SLIME).containsKey(s)) {
				AbstractSubspecies.addToSubspeciesMap((int) (5000 * Subspecies.getWorldSpecies(WorldType.DOMINION, null, false, Subspecies.SLIME).get(s).getChanceMultiplier()), this.getGenderIdentity(), s, subspeciesMap);
				
			} else if(Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false, Subspecies.SLIME).containsKey(s)) { // Add Submission races at only 20% of the chance of Dominion races
				AbstractSubspecies.addToSubspeciesMap((int) (1000 * Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false, Subspecies.SLIME).get(s).getChanceMultiplier()), this.getGenderIdentity(), s, subspeciesMap);
			}
		}
		
		// Setup
		this.setupEnforcer(subspeciesMap, null, occupation, true, true, generationFlags);
		
		// Post-setup
		this.addClothing(Main.game.getItemGen().generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_PURPLE_DARK, false), 5, false, false);
		
		for(AbstractCombatMove move : new ArrayList<>(this.getEquippedMoves())) {
			if(move.getType()==CombatMoveType.TEASE) {
				this.unequipMove(move.getIdentifier());
			}
		}
		
		this.setDescription("A member of the Enforcers' Frontline Patrol division, [npc.name] is expected to carry out a wide variety of day-to-day policing duties.");
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9.9")) {
			AbstractClothing vest = this.getClothingInSlot(InventorySlot.TORSO_OVER);
			if(vest!=null) {
				this.unequipClothingIntoVoid(vest, true, this);
				AbstractClothing newVest = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_stpvest", false);
				newVest.setSticker("name_plate", "enforcer");
				this.equipClothingFromNowhere(newVest, true, this);
			}
		}
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.ATHLETIC, settings);
		
		// Do not wear gas masks:
		AbstractClothing mouthClothing = this.getClothingInSlot(InventorySlot.MOUTH);
		if(mouthClothing!=null && mouthClothing.getClothingType().equals(ClothingType.getClothingTypeFromId("dsg_eep_tacequipset_gmask"))) {
			this.unequipClothingIntoVoid(mouthClothing, true, this);
		}
	}
	
	/**
	 * Equips this Enforcer with a baton in the primary slot, and the weapon as defined by offhandWeaponID in the offhand slot.
	 */
	public void setWeapons(String offhandWeaponID) {
		this.unequipAllWeaponsIntoVoid(false);
		this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("dsg_eep_enbaton_enbaton"));
		this.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(offhandWeaponID));
	}
	
	@Override
	public String getGenericName() {
		if(this.getHistory()==Occupation.NPC_ENFORCER_PATROL_CONSTABLE) {
			return UtilText.parse(this, "Constable [npc.surname]");
		} else if(this.getHistory()==Occupation.NPC_ENFORCER_PATROL_SERGEANT) {
			return UtilText.parse(this, "Sergeant [npc.surname]");
		} else if(this.getHistory()==Occupation.NPC_ENFORCER_PATROL_INSPECTOR) {
			return UtilText.parse(this, "Inspector [npc.surname]");
		}
		return UtilText.parse(this, "Enforcer");
	}
	
	// Combat:
	@Override
	public void applyEscapeCombatEffects() {
		EnforcerAlleywayDialogue.banishEnforcers(false);
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", EnforcerAlleywayDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", EnforcerAlleywayDialogue.AFTER_COMBAT_DEFEAT);
		}
	}
	
	// Sex:
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(EnforcerPatrolSA.class);
	}
	
	@Override
	public Value<AbstractItem, String> getSexItemToUse(GameCharacter partner) {
		return null; // Do not want Enforcers using items during sex
	}
}
