package com.lilithsthrone.game.character.npc.misc;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.1
 * @version 0.4.1
 * @author Innoxia
 */
public class GenericTrader extends NPC {

	public GenericTrader() {
		this(false);
	}
	
	public GenericTrader(boolean isImported) {
		super(isImported, new NameTriplet("Someone"), null, "A trader.",
				25, Month.JUNE, 15,
				1, Gender.N_P_V_HERMAPHRODITE, Subspecies.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(0), WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
		
		this.setFemininity(50);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		this.setRaceConcealed(true);
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		// Not needed
	}
	
	@Override
	public Map<InventorySlot, List<AbstractClothing>> getInventorySlotsConcealed(GameCharacter characterViewing) {
		Map<InventorySlot, List<AbstractClothing>> concealed = new HashMap<>();
		
		for(InventorySlot slot : InventorySlot.values()) {
			concealed.put(slot, new ArrayList<>());
		}
		
		return concealed;
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	// Trade:
	
	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return false;
	}

	@Override
	public boolean isTrader() {
		return true;
	}
	
	/**
	 * Resets this character's body and completely clears inventory.
	 */
	public void initTrader(Gender gender, AbstractSubspecies subspecies, RaceStage stage) {
		this.setBody(gender, subspecies, stage, false);
		this.unequipAllClothingIntoVoid(true, true);
		clearNonEquippedInventory(false);
	}
	
}