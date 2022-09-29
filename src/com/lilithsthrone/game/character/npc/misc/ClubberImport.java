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
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.nightlife.NightlifeDistrict;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.5.7
 * @version 0.4.5.7
 * @author Innoxia
 */
public class ClubberImport extends NPC {

	public ClubberImport() {
		this(false);
	}
	
	public ClubberImport(boolean isImported) {
		super(isImported, new NameTriplet("Clubber"), "", "-",
				18, Month.JUNE, 10,
				1, Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(0), WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(!this.getId().endsWith("ClubberImport")) {
			this.setId(Main.game.getNextNPCId(ClubberImport.class));
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
	
	public void applyNewlyImportedClubberVariables() {
		this.endPregnancy(false);

		this.washAllOrifices(true);
		this.calculateStatusEffects(0);
		this.cleanAllDirtySlots(true);
		this.cleanAllClothing(true, false);
		
		this.clearAffectionMap();
		
		this.getSlavesOwned().clear();
		
		this.setPlayerKnowsName(true);
		
		if(NightlifeDistrict.isSearchingForASub()) {
			this.removePersonalityTrait(PersonalityTrait.CONFIDENT);
		} else {
			this.addPersonalityTrait(PersonalityTrait.CONFIDENT);
		}
		
		this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_CLUB_HOLDING_CELL, true);
	}
	
	@Override
	public String getDescription() {
		return UtilText.parse(this, "[npc.Name] is a resident of Dominion, who you met in one of the clubs in the city's Nightlife district.");
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}

//	@Override
//	public Vector2i getLocation() {
//		if(this.getWorldLocation()==WorldType.NIGHTLIFE_CLUB
//				&& Main.game.getPlayer().getWorldLocation()==WorldType.NIGHTLIFE_CLUB) {
//			return Main.game.getPlayer().getLocation();
//		}
//		return location;
//	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

}