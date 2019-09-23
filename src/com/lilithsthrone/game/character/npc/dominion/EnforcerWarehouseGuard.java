package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.EnforcerWarehouse;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.OutfitType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStocks;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5
 * @version 0.3.5
 * @author Innoxia
 */
public class EnforcerWarehouseGuard extends NPC {

	public EnforcerWarehouseGuard() {
		this(Occupation.NPC_ENFORCER_SWORD_SERGEANT, Subspecies.WOLF_MORPH, RaceStage.GREATER, Gender.F_V_B_FEMALE, false);
	}
	
	public EnforcerWarehouseGuard(boolean isImported) {
		this(Occupation.NPC_ENFORCER_SWORD_SERGEANT, Subspecies.WOLF_MORPH, RaceStage.GREATER, Gender.F_V_B_FEMALE, isImported);
	}
	
	public EnforcerWarehouseGuard(Occupation occupation, Subspecies subspecies, RaceStage raceStage, Gender gender, boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				5, gender, subspecies, raceStage,
				new CharacterInventory(10), WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_ENFORCER_GUARD_POST, false,
				generationFlags);

		if(!isImported) {
			setLevel(Util.random.nextInt(6) + 5);
			
			setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			setName(Name.getRandomTriplet(this.getRace()));
			
			this.setPlayerKnowsName(false);
			
			this.setHistory(occupation);
			
			CharacterUtils.addFetishes(this, Fetish.FETISH_CROSS_DRESSER); // Do not allow cross-dressing, as otherwise it will mess with uniforms.
			
			this.addFetish(Fetish.FETISH_SADIST);
			
			CharacterUtils.randomiseBody(this, true);
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			CharacterUtils.generateItemsInInventory(this);
			
			if(!Arrays.asList(generationFlags).contains(NPCGenerationFlag.NO_CLOTHING_EQUIP)) {
				this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			}
			CharacterUtils.applyMakeup(this, true);
			
			initPerkTreeAndBackgroundPerks(); // Set starting perks based on the character's race
			
			this.setEssenceCount(TFEssence.ARCANE, 100);
			
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
		CharacterUtils.equipClothingFromOutfitType(this, OutfitType.ATHLETIC, settings);
		
		// Do not wear gas masks in the warehouse:
		AbstractClothing mouthClothing = this.getClothingInSlot(InventorySlot.MOUTH);
		if(mouthClothing!=null && mouthClothing.getClothingType().equals(ClothingType.getClothingTypeFromId("dsg_eep_tacequipset_gmask"))) {
			this.unequipClothingIntoVoid(mouthClothing, true, this);
		}
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public void hourlyUpdate() {
	}
	
	@Override
	public String getDescription() {
		return UtilText.parse(this, "[npc.Name]."); //TODO
	}
	
	@Override
	public void endSex() {
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
		return null;
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
				return new Response("Victory", "The enforcer slumps to the floor before you, defeated.", EnforcerWarehouse.AFTER_GUARD_COMBAT_VICTORY) {
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
				return new Response("Victory", "The enforcers slump to the floor before you, defeated.", EnforcerWarehouse.AFTER_ENTRANCE_VICTORY);
				
			} else {
				return new Response ("Defeated...", "You collapse to the floor, completely defeated.", EnforcerWarehouse.AFTER_ENTRANCE_DEFEAT);
			}
		}
	}
	
	// Sex:
	
	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Sex.getSexManager().getPosition() == SexPosition.STOCKS) {
			if(Sex.getSexPositionSlot(this)==SexSlotStocks.BEHIND_STOCKS) {
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
		if(Sex.getSexManager().getPosition() == SexPosition.STOCKS) {
			return getForeplayPreference(target);
		}

		return super.getMainSexPreference(target);
	}
}
