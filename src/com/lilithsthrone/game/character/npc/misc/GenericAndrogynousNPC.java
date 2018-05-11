package com.lilithsthrone.game.character.npc.misc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.79
 * @version 0.1.89
 * @author Innoxia
 */
public class GenericAndrogynousNPC extends NPC {

	private static final long serialVersionUID = 1L;

	public GenericAndrogynousNPC() {
		this(false);
	}
	
	public GenericAndrogynousNPC(boolean isImported) {
		super(new NameTriplet("Androgynous"), "Generic androgynous.",
				1, Gender.N_P_V_HERMAPHRODITE, RacialBody.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(0), WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true);
		
		this.setFemininity(50);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
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
	

}