package com.lilithsthrone.game.character.npc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.79
 * @version 0.1.89
 * @author Innoxia
 */
public class GenericMaleNPC extends NPC {

	private static final long serialVersionUID = 1L;

	public GenericMaleNPC() {
		this(false);
	}
	
	public GenericMaleNPC(boolean isImported) {
		super(new NameTriplet("unknown male"), "Unknown.",
				1, Gender.M_P_MALE, RacialBody.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(0), WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		this.setName(new NameTriplet("unknown male"));
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	@Override
	public void endSex(boolean applyEffects) {
	}
	
	@Override
	public String getCombatDescription() {
		return null;
	}
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		return null;
	}
	@Override
	public Attack attackType() {
		return null;
	}
	@Override
	public int getExperienceFromVictory() {
		return 0;
	}

}