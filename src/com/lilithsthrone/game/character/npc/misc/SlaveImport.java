package com.lilithsthrone.game.character.npc.misc;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Finch;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.90
 * @version 0.2.11
 * @author Innoxia
 */
public class SlaveImport extends NPC {

	public SlaveImport() {
		this(false);
	}
	
	public SlaveImport(boolean isImported) {
		super(isImported, new NameTriplet("Slave"), "", "-",
				18, Month.JUNE, 10,
				1, Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(0), WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		this.setLastTimeEncountered(DEFAULT_TIME_START_VALUE);
		
		if(!this.getId().endsWith("SlaveImport")) {
			this.setId(Main.game.getNextNPCId(SlaveImport.class));
		}
		
//		this.clearAllCompanionVariables();
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		// Not needed
	}
	
	public void applyNewlyImportedSlaveVariables() {
		// If the slave has only just been imported:
//		if(this.getOwnerId().isEmpty()) {
			Main.game.getNpc(Finch.class).addSlave(this);
			this.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_AUCTIONING_BLOCK, true);
			
			this.endPregnancy(false);

			this.washAllOrifices(true);
			this.calculateStatusEffects(0);
			this.cleanAllDirtySlots(true);
			this.cleanAllClothing(true, false);
			
			this.clearNonEquippedInventory(true);
			if(this.getClothingInSlot(InventorySlot.NECK)!=null) {
				this.getClothingInSlot(InventorySlot.NECK).setSealed(false);
				this.unequipClothingIntoInventory(this.getClothingInSlot(InventorySlot.NECK), true, this);
			}
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", false), true, this);
			this.getClothingInSlot(InventorySlot.NECK).setSealed(true);
			
			
			this.clearAffectionMap();
			this.setObedience((float) Math.round((-25+(Math.random()*50))));
			
			this.getSlavesOwned().clear();
			
			this.setPlayerKnowsName(true);
			
			this.setHistory(Occupation.NPC_SLAVE);
//		}
	}
	
	@Override
	public String getDescription() {
		return UtilText.parse(this, "As a slave, [npc.name] is no more than someone's property. The first time you saw [npc.herHim], [npc.she] was being sold in Slaver Alley.");
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

}