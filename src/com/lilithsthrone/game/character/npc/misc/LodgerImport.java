package com.lilithsthrone.game.character.npc.misc;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class LodgerImport extends NPC {

	public LodgerImport() {
		this(false);
	}
	
	public LodgerImport(boolean isImported) {
		super(isImported, new NameTriplet("Lodger"), "", "-",
				18, Month.JUNE, 10,
				1, Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(0), WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		this.setLastTimeEncountered(DEFAULT_TIME_START_VALUE);
		
		if(!this.getId().endsWith("LodgerImport")) {
			this.setId(Main.game.getNextNPCId(LodgerImport.class));
		}
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		// Not needed
	}
	
	public void applyNewlyImportedLodgerVariables() {
		this.endPregnancy(false);

		this.washAllOrifices(true);
		this.calculateStatusEffects(0);
		this.cleanAllDirtySlots(true);
		this.cleanAllClothing(true, false);
		
		this.clearAffectionMap();
		
		this.getSlavesOwned().clear();
		
		this.setPlayerKnowsName(true);
		
		this.setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_WAITING_AREA, true);
	}
	
	@Override
	public String getDescription() {
		return UtilText.parse(this, "You first met [npc.name] in Dominion's city hall, where [npc.she] was waiting for someone to offer [npc.herHim] lodgings...");
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