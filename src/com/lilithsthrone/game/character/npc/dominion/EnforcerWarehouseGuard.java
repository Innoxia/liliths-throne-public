package com.lilithsthrone.game.character.npc.dominion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.places.dominion.EnforcerWarehouse;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStocks;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5
 * @version 0.3.5
 * @author Innoxia
 */
public class EnforcerWarehouseGuard extends RandomNPC {

	public EnforcerWarehouseGuard(NPCGenerationFlag... generationFlags) {
		this(Occupation.NPC_ENFORCER_SWORD_SERGEANT, false);
	}
	
	public EnforcerWarehouseGuard(boolean isImported) {
		this(Occupation.NPC_ENFORCER_SWORD_SERGEANT, isImported);
	}
	
	public EnforcerWarehouseGuard(Occupation occupation) {
		this(occupation, false);
	}
	
	public EnforcerWarehouseGuard(Occupation occupation, boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		// Pre-setup
		this.setLevel(Util.random.nextInt(6) + 5);
		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		// Make SWORD guards a predator subspecies:
		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		for (AbstractSubspecies subspecies : Util.newArrayListOfValues(
				Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_tiger"),
				Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_lion"),
				Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_leopard"),
				Subspecies.DOG_MORPH_DOBERMANN,
				Subspecies.DOG_MORPH_GERMAN_SHEPHERD,
				Subspecies.FOX_MORPH,
				Subspecies.WOLF_MORPH)) {
			subspeciesMap.put(subspecies, 1);
		}
		
		// Setup
		this.setupEnforcer(subspeciesMap, null, occupation, false, false, generationFlags);
		
		// Enforcers can't be human so force lesser from the valid species
		if (this.getRace() == Race.HUMAN) {
			this.setBody(getGenderIdentity(), Util.getRandomObjectFromWeightedMap(subspeciesMap), RaceStage.LESSER, true);
		}

		// Post-setup
		this.addFetish(Fetish.FETISH_SADIST);
		this.setDescription("One of the SWORD Enforcers tasked with guarding [npc.his] division's warehouse, [npc.name] is more than prepared to use an unreasonable amount of force to detain anyone [npc.she] catches...");
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.ATHLETIC, settings);
		
		// Do not wear gas masks in the warehouse:
		AbstractClothing mouthClothing = this.getClothingInSlot(InventorySlot.MOUTH);
		if(mouthClothing!=null && mouthClothing.getClothingType().equals(ClothingType.getClothingTypeFromId("dsg_eep_tacequipset_gmask"))) {
			this.unequipClothingIntoVoid(mouthClothing, true, this);
		}
	}
	
	@Override
	public boolean isClothingStealable() {
		return false;
	}
	
	// Combat:
	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if(Main.game.getPlayer().getCell().getPlace().getPlaceType()==PlaceType.ENFORCER_WAREHOUSE_ENFORCER_GUARD_POST) {
			if(victory) {
				return new Response("Victory", "The Enforcer slumps to the floor before you, defeated.", EnforcerWarehouse.AFTER_GUARD_COMBAT_VICTORY) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().warehouseDefeatedIDs.add(EnforcerWarehouseGuard.this.getId());
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
					}
				};
				
			} else {
				return new Response ("Defeated...", "You collapse to the floor, completely defeated.", EnforcerWarehouse.AFTER_GUARD_COMBAT_DEFEAT);
			}
			
		} else { // Main entrance:
			if(victory) {
				return new Response("Victory", "The Enforcers slump to the floor before you, defeated.", EnforcerWarehouse.AFTER_ENTRANCE_VICTORY);
				
			} else {
				return new Response ("Defeated...", "You collapse to the floor, completely defeated.", EnforcerWarehouse.AFTER_ENTRANCE_DEFEAT);
			}
		}
	}
	
	// Sex:
	
	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Main.sex.getSexManager().getPosition() == SexPosition.STOCKS) {
			if(Main.sex.getSexPositionSlot(this)==SexSlotStocks.BEHIND_STOCKS) {
				if(this.hasPenis()) {
					if(target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && target.hasVagina()) {
						return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
					} else {
						return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
					}
				} else {
					if(target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && target.hasVagina()) {
						return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA);
					}
				}
				
			} else {
				if(this.hasPenis()) {
					if(target.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
					}
				} else if(this.hasVagina()) {
					if(target.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
					}
				}
			}
		}
		
		return super.getForeplayPreference(target);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Main.sex.getSexManager().getPosition() == SexPosition.STOCKS) {
			return getForeplayPreference(target);
		}

		return super.getMainSexPreference(target);
	}
}
