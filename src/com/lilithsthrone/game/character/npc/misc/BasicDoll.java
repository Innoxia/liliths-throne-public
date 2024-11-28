package com.lilithsthrone.game.character.npc.misc;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * This class is used for dolls created at Lovienne's Luxuries.
 * <br/>Requires initialisation, mainly setBodyMaterial()
 * 
 * @since 0.4.9.1
 * @version 0.4.9.1
 * @author Innoxia
 */
public class BasicDoll extends NPC {

	public BasicDoll() {
		this(Gender.F_V_B_FEMALE, false);
	}
	
	public BasicDoll(Gender gender) {
		this(gender, false);
	}
	
	public BasicDoll(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public BasicDoll(Gender gender, boolean isImported) {
		super(isImported,
				new NameTriplet("Doll"), "",
				"",
				18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(27),
				1,
				null, null, null,
				new CharacterInventory(0),
				WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL,
				false);

		if(!isImported) {
			this.setBody(gender, Subspecies.HUMAN, RaceStage.HUMAN, false);
			
			setSexualOrientation(SexualOrientation.AMBIPHILIC);

			this.setPlayerKnowsName(true);
			this.setSurname("");
			
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 0);
			
			// PERSONALITY & BACKGROUND:
			
			this.setHistory(Occupation.NPC_SEX_DOLL);

			this.clearFetishDesires();
			this.clearFetishes();
			this.clearPersonalityTraits();
			this.clearTattoosAndScars();
			
			this.setObedience(100);
			this.setAffection(Main.game.getPlayer(), 100);
			
			
			// BODY RANDOMISATION:
			
			this.setStartingBody(true);
			
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(0);
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			
			// MISC.:
			
			initHealthAndManaToMax();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setStartingBody(boolean setPersona) {
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		return UtilText.parse(this, "[npc.Name] [npc.is] [npc.a_race] who was created at Lovienne's Luxuries.");
	}

	@Override
	public boolean isClothingStealable() {
		return true;
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
}
